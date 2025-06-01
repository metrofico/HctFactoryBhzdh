package jxl.write.biff;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import jxl.CellType;
import jxl.Hyperlink;
import jxl.Range;
import jxl.biff.CellReferenceHelper;
import jxl.biff.IntegerHelper;
import jxl.biff.SheetRangeImpl;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;

public class HyperlinkRecord extends WritableRecordData {
   private static final LinkType fileLink = new LinkType();
   private static Logger logger = Logger.getLogger(HyperlinkRecord.class);
   private static final LinkType uncLink = new LinkType();
   private static final LinkType unknown = new LinkType();
   private static final LinkType urlLink = new LinkType();
   private static final LinkType workbookLink = new LinkType();
   private String contents;
   private byte[] data;
   private java.io.File file;
   private int firstColumn;
   private int firstRow;
   private int lastColumn;
   private int lastRow;
   private LinkType linkType;
   private String location;
   private boolean modified;
   private Range range;
   private WritableSheet sheet;
   private URL url;

   protected HyperlinkRecord(int var1, int var2, int var3, int var4, java.io.File var5, String var6) {
      super(Type.HLINK);
      this.firstColumn = var1;
      this.firstRow = var2;
      this.lastColumn = Math.max(var1, var3);
      this.lastRow = Math.max(this.firstRow, var4);
      this.contents = var6;
      this.file = var5;
      if (var5.getPath().startsWith("\\\\")) {
         this.linkType = uncLink;
      } else {
         this.linkType = fileLink;
      }

      this.modified = true;
   }

   protected HyperlinkRecord(int var1, int var2, int var3, int var4, String var5, WritableSheet var6, int var7, int var8, int var9, int var10) {
      super(Type.HLINK);
      this.firstColumn = var1;
      this.firstRow = var2;
      this.lastColumn = Math.max(var1, var3);
      this.lastRow = Math.max(this.firstRow, var4);
      this.setLocation(var6, var7, var8, var9, var10);
      this.contents = var5;
      this.linkType = workbookLink;
      this.modified = true;
   }

   protected HyperlinkRecord(int var1, int var2, int var3, int var4, URL var5, String var6) {
      super(Type.HLINK);
      this.firstColumn = var1;
      this.firstRow = var2;
      this.lastColumn = Math.max(var1, var3);
      this.lastRow = Math.max(this.firstRow, var4);
      this.url = var5;
      this.contents = var6;
      this.linkType = urlLink;
      this.modified = true;
   }

   protected HyperlinkRecord(Hyperlink var1, WritableSheet var2) {
      super(Type.HLINK);
      if (var1 instanceof jxl.read.biff.HyperlinkRecord) {
         this.copyReadHyperlink(var1, var2);
      } else {
         this.copyWritableHyperlink(var1, var2);
      }

   }

   private void copyReadHyperlink(Hyperlink var1, WritableSheet var2) {
      jxl.read.biff.HyperlinkRecord var3 = (jxl.read.biff.HyperlinkRecord)var1;
      this.data = var3.getRecord().getData();
      this.sheet = var2;
      this.firstRow = var3.getRow();
      this.firstColumn = var3.getColumn();
      this.lastRow = var3.getLastRow();
      this.lastColumn = var3.getLastColumn();
      this.range = new SheetRangeImpl(var2, this.firstColumn, this.firstRow, this.lastColumn, this.lastRow);
      this.linkType = unknown;
      if (var3.isFile()) {
         this.linkType = fileLink;
         this.file = var3.getFile();
      } else if (var3.isURL()) {
         this.linkType = urlLink;
         this.url = var3.getURL();
      } else if (var3.isLocation()) {
         this.linkType = workbookLink;
         this.location = var3.getLocation();
      }

      this.modified = false;
   }

   private void copyWritableHyperlink(Hyperlink var1, WritableSheet var2) {
      HyperlinkRecord var5 = (HyperlinkRecord)var1;
      this.firstRow = var5.firstRow;
      this.lastRow = var5.lastRow;
      this.firstColumn = var5.firstColumn;
      this.lastColumn = var5.lastColumn;
      if (var5.url != null) {
         try {
            URL var3 = new URL(var5.url.toString());
            this.url = var3;
         } catch (MalformedURLException var4) {
            Assert.verify(false);
         }
      }

      if (var5.file != null) {
         this.file = new java.io.File(var5.file.getPath());
      }

      this.location = var5.location;
      this.contents = var5.contents;
      this.linkType = var5.linkType;
      this.modified = true;
      this.sheet = var2;
      this.range = new SheetRangeImpl(var2, this.firstColumn, this.firstRow, this.lastColumn, this.lastRow);
   }

   private byte[] getFileData(byte[] var1) {
      ArrayList var8 = new ArrayList();
      ArrayList var7 = new ArrayList();
      var8.add(this.file.getName());
      var7.add(this.getShortName(this.file.getName()));

      for(java.io.File var6 = this.file.getParentFile(); var6 != null; var6 = var6.getParentFile()) {
         var8.add(var6.getName());
         var7.add(this.getShortName(var6.getName()));
      }

      int var4 = var8.size() - 1;
      boolean var5 = true;

      int var3;
      for(var3 = 0; var5; --var4) {
         if (((String)var8.get(var4)).equals("..")) {
            ++var3;
            var8.remove(var4);
            var7.remove(var4);
         } else {
            var5 = false;
         }
      }

      StringBuffer var12 = new StringBuffer();
      StringBuffer var9 = new StringBuffer();
      if (this.file.getPath().charAt(1) == ':') {
         char var2 = this.file.getPath().charAt(0);
         if (var2 != 'C' && var2 != 'c') {
            var12.append(var2);
            var12.append(':');
            var9.append(var2);
            var9.append(':');
         }
      }

      for(var4 = var8.size() - 1; var4 >= 0; --var4) {
         var12.append((String)var8.get(var4));
         var9.append((String)var7.get(var4));
         if (var4 != 0) {
            var12.append("\\");
            var9.append("\\");
         }
      }

      String var13 = var12.toString();
      String var14 = var9.toString();
      int var11 = var1.length + 4 + var14.length() + 1 + 16 + 2 + 8 + (var13.length() + 1) * 2 + 24;
      String var15 = this.contents;
      var4 = var11;
      if (var15 != null) {
         var4 = var11 + (var15.length() + 1) * 2 + 4;
      }

      byte[] var16 = new byte[var4];
      System.arraycopy(var1, 0, var16, 0, var1.length);
      var11 = var1.length;
      String var10 = this.contents;
      var4 = var11;
      if (var10 != null) {
         IntegerHelper.getFourBytes(var10.length() + 1, var16, var11);
         StringHelper.getUnicodeBytes(this.contents, var16, var11 + 4);
         var4 = var11 + (this.contents.length() + 1) * 2 + 4;
      }

      var16[var4] = 3;
      var16[var4 + 1] = 3;
      var16[var4 + 2] = 0;
      var16[var4 + 3] = 0;
      var16[var4 + 4] = 0;
      var16[var4 + 5] = 0;
      var16[var4 + 6] = 0;
      var16[var4 + 7] = 0;
      var16[var4 + 8] = -64;
      var16[var4 + 9] = 0;
      var16[var4 + 10] = 0;
      var16[var4 + 11] = 0;
      var16[var4 + 12] = 0;
      var16[var4 + 13] = 0;
      var16[var4 + 14] = 0;
      var16[var4 + 15] = 70;
      var4 += 16;
      IntegerHelper.getTwoBytes(var3, var16, var4);
      var3 = var4 + 2;
      IntegerHelper.getFourBytes(var14.length() + 1, var16, var3);
      StringHelper.getBytes(var14, var16, var3 + 4);
      var3 += var14.length() + 1 + 4;
      var16[var3] = -1;
      var16[var3 + 1] = -1;
      var16[var3 + 2] = -83;
      var16[var3 + 3] = -34;
      var16[var3 + 4] = 0;
      var16[var3 + 5] = 0;
      var16[var3 + 6] = 0;
      var16[var3 + 7] = 0;
      var16[var3 + 8] = 0;
      var16[var3 + 9] = 0;
      var16[var3 + 10] = 0;
      var16[var3 + 11] = 0;
      var16[var3 + 12] = 0;
      var16[var3 + 13] = 0;
      var16[var3 + 14] = 0;
      var16[var3 + 15] = 0;
      var16[var3 + 16] = 0;
      var16[var3 + 17] = 0;
      var16[var3 + 18] = 0;
      var16[var3 + 19] = 0;
      var16[var3 + 20] = 0;
      var16[var3 + 21] = 0;
      var16[var3 + 22] = 0;
      var16[var3 + 23] = 0;
      var3 += 24;
      IntegerHelper.getFourBytes(var13.length() * 2 + 6, var16, var3);
      var3 += 4;
      IntegerHelper.getFourBytes(var13.length() * 2, var16, var3);
      var3 += 4;
      var16[var3] = 3;
      var16[var3 + 1] = 0;
      StringHelper.getUnicodeBytes(var13, var16, var3 + 2);
      var13.length();
      return var16;
   }

   private byte[] getLocationData(byte[] var1) {
      byte[] var3 = new byte[var1.length + 4 + (this.location.length() + 1) * 2];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      int var2 = var1.length;
      IntegerHelper.getFourBytes(this.location.length() + 1, var3, var2);
      StringHelper.getUnicodeBytes(this.location, var3, var2 + 4);
      return var3;
   }

   private String getShortName(String var1) {
      int var2 = var1.indexOf(46);
      String var3;
      String var4;
      if (var2 == -1) {
         var4 = "";
         var3 = var1;
      } else {
         var3 = var1.substring(0, var2);
         var4 = var1.substring(var2 + 1);
      }

      var1 = var3;
      if (var3.length() > 8) {
         var1 = (var3.substring(0, 6) + "~" + (var3.length() - 8)).substring(0, 8);
      }

      var4 = var4.substring(0, Math.min(3, var4.length()));
      var3 = var1;
      if (var4.length() > 0) {
         var3 = var1 + '.' + var4;
      }

      return var3;
   }

   private byte[] getUNCData(byte[] var1) {
      String var3 = this.file.getPath();
      byte[] var4 = new byte[var1.length + var3.length() * 2 + 2 + 4];
      System.arraycopy(var1, 0, var4, 0, var1.length);
      int var2 = var1.length;
      IntegerHelper.getFourBytes(var3.length() + 1, var4, var2);
      StringHelper.getUnicodeBytes(var3, var4, var2 + 4);
      return var4;
   }

   private byte[] getURLData(byte[] var1) {
      String var4 = this.url.toString();
      int var3 = var1.length + 20 + (var4.length() + 1) * 2;
      String var5 = this.contents;
      int var2 = var3;
      if (var5 != null) {
         var2 = var3 + (var5.length() + 1) * 2 + 4;
      }

      byte[] var7 = new byte[var2];
      System.arraycopy(var1, 0, var7, 0, var1.length);
      var3 = var1.length;
      String var6 = this.contents;
      var2 = var3;
      if (var6 != null) {
         IntegerHelper.getFourBytes(var6.length() + 1, var7, var3);
         StringHelper.getUnicodeBytes(this.contents, var7, var3 + 4);
         var2 = var3 + (this.contents.length() + 1) * 2 + 4;
      }

      var7[var2] = -32;
      var7[var2 + 1] = -55;
      var7[var2 + 2] = -22;
      var7[var2 + 3] = 121;
      var7[var2 + 4] = -7;
      var7[var2 + 5] = -70;
      var7[var2 + 6] = -50;
      var7[var2 + 7] = 17;
      var7[var2 + 8] = -116;
      var7[var2 + 9] = -126;
      var7[var2 + 10] = 0;
      var7[var2 + 11] = -86;
      var7[var2 + 12] = 0;
      var7[var2 + 13] = 75;
      var7[var2 + 14] = -87;
      var7[var2 + 15] = 11;
      IntegerHelper.getFourBytes((var4.length() + 1) * 2, var7, var2 + 16);
      StringHelper.getUnicodeBytes(var4, var7, var2 + 20);
      return var7;
   }

   private void setLocation(WritableSheet var1, int var2, int var3, int var4, int var5) {
      StringBuffer var8 = new StringBuffer();
      var8.append('\'');
      if (var1.getName().indexOf(39) == -1) {
         var8.append(var1.getName());
      } else {
         String var9 = var1.getName();
         int var6 = 0;

         for(int var7 = var9.indexOf(39, 0); var7 != -1 && var6 < var9.length(); var7 = var9.indexOf(39, var6)) {
            var8.append(var9.substring(var6, var7));
            var8.append("''");
            var6 = var7 + 1;
         }

         var8.append(var9.substring(var6));
      }

      var8.append('\'');
      var8.append('!');
      var4 = Math.max(var2, var4);
      var5 = Math.max(var3, var5);
      CellReferenceHelper.getCellReference(var2, var3, var8);
      var8.append(':');
      CellReferenceHelper.getCellReference(var4, var5, var8);
      this.location = var8.toString();
   }

   public int getColumn() {
      return this.firstColumn;
   }

   String getContents() {
      return this.contents;
   }

   public byte[] getData() {
      if (!this.modified) {
         return this.data;
      } else {
         byte[] var3 = new byte[32];
         int var2 = this.firstRow;
         short var1 = 0;
         IntegerHelper.getTwoBytes(var2, var3, 0);
         IntegerHelper.getTwoBytes(this.lastRow, var3, 2);
         IntegerHelper.getTwoBytes(this.firstColumn, var3, 4);
         IntegerHelper.getTwoBytes(this.lastColumn, var3, 6);
         var3[8] = -48;
         var3[9] = -55;
         var3[10] = -22;
         var3[11] = 121;
         var3[12] = -7;
         var3[13] = -70;
         var3[14] = -50;
         var3[15] = 17;
         var3[16] = -116;
         var3[17] = -126;
         var3[18] = 0;
         var3[19] = -86;
         var3[20] = 0;
         var3[21] = 75;
         var3[22] = -87;
         var3[23] = 11;
         var3[24] = 2;
         var3[25] = 0;
         var3[26] = 0;
         var3[27] = 0;
         if (this.isURL()) {
            var1 = 3;
            if (this.contents != null) {
               var1 = 23;
            }
         } else if (this.isFile()) {
            var1 = 1;
            if (this.contents != null) {
               var1 = 21;
            }
         } else if (this.isLocation()) {
            var1 = 8;
         } else if (this.isUNC()) {
            var1 = 259;
         }

         IntegerHelper.getFourBytes(var1, var3, 28);
         if (this.isURL()) {
            this.data = this.getURLData(var3);
         } else if (this.isFile()) {
            this.data = this.getFileData(var3);
         } else if (this.isLocation()) {
            this.data = this.getLocationData(var3);
         } else if (this.isUNC()) {
            this.data = this.getUNCData(var3);
         }

         return this.data;
      }
   }

   public java.io.File getFile() {
      return this.file;
   }

   public int getLastColumn() {
      return this.lastColumn;
   }

   public int getLastRow() {
      return this.lastRow;
   }

   public Range getRange() {
      return this.range;
   }

   public int getRow() {
      return this.firstRow;
   }

   public URL getURL() {
      return this.url;
   }

   void initialize(WritableSheet var1) {
      this.sheet = var1;
      this.range = new SheetRangeImpl(var1, this.firstColumn, this.firstRow, this.lastColumn, this.lastRow);
   }

   void insertColumn(int var1) {
      boolean var4;
      if (this.sheet != null && this.range != null) {
         var4 = true;
      } else {
         var4 = false;
      }

      Assert.verify(var4);
      int var3 = this.lastColumn;
      if (var1 <= var3) {
         int var2 = this.firstColumn;
         if (var1 <= var2) {
            this.firstColumn = var2 + 1;
            this.modified = true;
         }

         if (var1 <= var3) {
            this.lastColumn = var3 + 1;
            this.modified = true;
         }

         if (this.modified) {
            this.range = new SheetRangeImpl(this.sheet, this.firstColumn, this.firstRow, this.lastColumn, this.lastRow);
         }

      }
   }

   void insertRow(int var1) {
      boolean var4;
      if (this.sheet != null && this.range != null) {
         var4 = true;
      } else {
         var4 = false;
      }

      Assert.verify(var4);
      int var2 = this.lastRow;
      if (var1 <= var2) {
         int var3 = this.firstRow;
         if (var1 <= var3) {
            this.firstRow = var3 + 1;
            this.modified = true;
         }

         if (var1 <= var2) {
            this.lastRow = var2 + 1;
            this.modified = true;
         }

         if (this.modified) {
            this.range = new SheetRangeImpl(this.sheet, this.firstColumn, this.firstRow, this.lastColumn, this.lastRow);
         }

      }
   }

   public boolean isFile() {
      boolean var1;
      if (this.linkType == fileLink) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isLocation() {
      boolean var1;
      if (this.linkType == workbookLink) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isUNC() {
      boolean var1;
      if (this.linkType == uncLink) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isURL() {
      boolean var1;
      if (this.linkType == urlLink) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   void removeColumn(int var1) {
      WritableSheet var6 = this.sheet;
      boolean var5 = false;
      boolean var4;
      if (var6 != null && this.range != null) {
         var4 = true;
      } else {
         var4 = false;
      }

      Assert.verify(var4);
      int var3 = this.lastColumn;
      if (var1 <= var3) {
         int var2 = this.firstColumn;
         if (var1 < var2) {
            this.firstColumn = var2 - 1;
            this.modified = true;
         }

         if (var1 < var3) {
            this.lastColumn = var3 - 1;
            this.modified = true;
         }

         if (this.modified) {
            var4 = var5;
            if (this.range != null) {
               var4 = true;
            }

            Assert.verify(var4);
            this.range = new SheetRangeImpl(this.sheet, this.firstColumn, this.firstRow, this.lastColumn, this.lastRow);
         }

      }
   }

   void removeRow(int var1) {
      WritableSheet var6 = this.sheet;
      boolean var5 = false;
      boolean var4;
      if (var6 != null && this.range != null) {
         var4 = true;
      } else {
         var4 = false;
      }

      Assert.verify(var4);
      int var3 = this.lastRow;
      if (var1 <= var3) {
         int var2 = this.firstRow;
         if (var1 < var2) {
            this.firstRow = var2 - 1;
            this.modified = true;
         }

         if (var1 < var3) {
            this.lastRow = var3 - 1;
            this.modified = true;
         }

         if (this.modified) {
            var4 = var5;
            if (this.range != null) {
               var4 = true;
            }

            Assert.verify(var4);
            this.range = new SheetRangeImpl(this.sheet, this.firstColumn, this.firstRow, this.lastColumn, this.lastRow);
         }

      }
   }

   protected void setContents(String var1) {
      this.contents = var1;
      this.modified = true;
   }

   public void setFile(java.io.File var1) {
      this.linkType = fileLink;
      this.url = null;
      this.location = null;
      this.contents = null;
      this.file = var1;
      boolean var2 = true;
      this.modified = true;
      WritableSheet var3 = this.sheet;
      if (var3 != null) {
         WritableCell var4 = var3.getWritableCell(this.firstColumn, this.firstRow);
         if (var4.getType() != CellType.LABEL) {
            var2 = false;
         }

         Assert.verify(var2);
         ((Label)var4).setString(var1.toString());
      }
   }

   protected void setLocation(String var1, WritableSheet var2, int var3, int var4, int var5, int var6) {
      this.linkType = workbookLink;
      this.url = null;
      this.file = null;
      boolean var7 = true;
      this.modified = true;
      this.contents = var1;
      this.setLocation(var2, var3, var4, var5, var6);
      if (var2 != null) {
         WritableCell var8 = var2.getWritableCell(this.firstColumn, this.firstRow);
         if (var8.getType() != CellType.LABEL) {
            var7 = false;
         }

         Assert.verify(var7);
         ((Label)var8).setString(var1);
      }
   }

   public void setURL(URL var1) {
      URL var2 = this.url;
      this.linkType = urlLink;
      this.file = null;
      this.location = null;
      this.contents = null;
      this.url = var1;
      this.modified = true;
      WritableSheet var3 = this.sheet;
      if (var3 != null) {
         WritableCell var6 = var3.getWritableCell(this.firstColumn, this.firstRow);
         if (var6.getType() == CellType.LABEL) {
            Label var7 = (Label)var6;
            String var4 = var2.toString();
            String var5;
            if (var4.charAt(var4.length() - 1) != '/' && var4.charAt(var4.length() - 1) != '\\') {
               var5 = "";
            } else {
               var5 = var4.substring(0, var4.length() - 1);
            }

            if (var7.getString().equals(var4) || var7.getString().equals(var5)) {
               var7.setString(var1.toString());
            }
         }

      }
   }

   public String toString() {
      if (this.isFile()) {
         return this.file.toString();
      } else if (this.isURL()) {
         return this.url.toString();
      } else {
         return this.isUNC() ? this.file.toString() : "";
      }
   }

   private static class LinkType {
      private LinkType() {
      }

      // $FF: synthetic method
      LinkType(Object var1) {
         this();
      }
   }
}
