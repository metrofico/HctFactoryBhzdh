package jxl.biff;

import jxl.WorkbookSettings;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.Colour;
import jxl.format.Font;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.read.biff.Record;

public class FontRecord extends WritableRecordData implements Font {
   private static final int EXCEL_UNITS_PER_POINT = 20;
   public static final Biff7 biff7 = new Biff7();
   private static Logger logger = Logger.getLogger(FontRecord.class);
   private int boldWeight;
   private byte characterSet;
   private int colourIndex;
   private byte fontFamily;
   private int fontIndex;
   private boolean initialized;
   private boolean italic;
   private String name;
   private int pointHeight;
   private int scriptStyle;
   private boolean struckout;
   private int underlineStyle;

   protected FontRecord(String var1, int var2, int var3, boolean var4, int var5, int var6, int var7) {
      super(Type.FONT);
      this.boldWeight = var3;
      this.underlineStyle = var5;
      this.name = var1;
      this.pointHeight = var2;
      this.italic = var4;
      this.scriptStyle = var7;
      this.colourIndex = var6;
      this.initialized = false;
      this.struckout = false;
   }

   protected FontRecord(Font var1) {
      super(Type.FONT);
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      this.pointHeight = var1.getPointSize();
      this.colourIndex = var1.getColour().getValue();
      this.boldWeight = var1.getBoldWeight();
      this.scriptStyle = var1.getScriptStyle().getValue();
      this.underlineStyle = var1.getUnderlineStyle().getValue();
      this.italic = var1.isItalic();
      this.name = var1.getName();
      this.struckout = var1.isStruckout();
      this.initialized = false;
   }

   public FontRecord(Record var1, WorkbookSettings var2) {
      super(var1);
      byte[] var5 = this.getRecord().getData();
      this.pointHeight = IntegerHelper.getInt(var5[0], var5[1]) / 20;
      this.colourIndex = IntegerHelper.getInt(var5[4], var5[5]);
      this.boldWeight = IntegerHelper.getInt(var5[6], var5[7]);
      this.scriptStyle = IntegerHelper.getInt(var5[8], var5[9]);
      this.underlineStyle = var5[10];
      this.fontFamily = var5[11];
      this.characterSet = var5[12];
      this.initialized = false;
      byte var3 = var5[2];
      if ((var3 & 2) != 0) {
         this.italic = true;
      }

      if ((var3 & 8) != 0) {
         this.struckout = true;
      }

      byte var4 = var5[14];
      var3 = var5[15];
      if (var3 == 0) {
         this.name = StringHelper.getString(var5, var4, 16, var2);
      } else if (var3 == 1) {
         this.name = StringHelper.getUnicodeString(var5, var4, 16);
      } else {
         this.name = StringHelper.getString(var5, var4, 15, var2);
      }

   }

   public FontRecord(Record var1, WorkbookSettings var2, Biff7 var3) {
      super(var1);
      byte[] var5 = this.getRecord().getData();
      this.pointHeight = IntegerHelper.getInt(var5[0], var5[1]) / 20;
      this.colourIndex = IntegerHelper.getInt(var5[4], var5[5]);
      this.boldWeight = IntegerHelper.getInt(var5[6], var5[7]);
      this.scriptStyle = IntegerHelper.getInt(var5[8], var5[9]);
      this.underlineStyle = var5[10];
      this.fontFamily = var5[11];
      this.initialized = false;
      byte var4 = var5[2];
      if ((var4 & 2) != 0) {
         this.italic = true;
      }

      if ((var4 & 8) != 0) {
         this.struckout = true;
      }

      this.name = StringHelper.getString(var5, var5[14], 15, var2);
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof FontRecord)) {
         return false;
      } else {
         FontRecord var2 = (FontRecord)var1;
         return this.pointHeight == var2.pointHeight && this.colourIndex == var2.colourIndex && this.boldWeight == var2.boldWeight && this.scriptStyle == var2.scriptStyle && this.underlineStyle == var2.underlineStyle && this.italic == var2.italic && this.struckout == var2.struckout && this.fontFamily == var2.fontFamily && this.characterSet == var2.characterSet && this.name.equals(var2.name);
      }
   }

   public int getBoldWeight() {
      return this.boldWeight;
   }

   public Colour getColour() {
      return Colour.getInternalColour(this.colourIndex);
   }

   public byte[] getData() {
      byte[] var1 = new byte[this.name.length() * 2 + 16];
      IntegerHelper.getTwoBytes(this.pointHeight * 20, var1, 0);
      if (this.italic) {
         var1[2] = (byte)(var1[2] | 2);
      }

      if (this.struckout) {
         var1[2] = (byte)(var1[2] | 8);
      }

      IntegerHelper.getTwoBytes(this.colourIndex, var1, 4);
      IntegerHelper.getTwoBytes(this.boldWeight, var1, 6);
      IntegerHelper.getTwoBytes(this.scriptStyle, var1, 8);
      var1[10] = (byte)this.underlineStyle;
      var1[11] = this.fontFamily;
      var1[12] = this.characterSet;
      var1[13] = 0;
      var1[14] = (byte)this.name.length();
      var1[15] = 1;
      StringHelper.getUnicodeBytes(this.name, var1, 16);
      return var1;
   }

   public final int getFontIndex() {
      return this.fontIndex;
   }

   public String getName() {
      return this.name;
   }

   public int getPointSize() {
      return this.pointHeight;
   }

   public ScriptStyle getScriptStyle() {
      return ScriptStyle.getStyle(this.scriptStyle);
   }

   public UnderlineStyle getUnderlineStyle() {
      return UnderlineStyle.getStyle(this.underlineStyle);
   }

   public int hashCode() {
      return this.name.hashCode();
   }

   public final void initialize(int var1) {
      this.fontIndex = var1;
      this.initialized = true;
   }

   public final boolean isInitialized() {
      return this.initialized;
   }

   public boolean isItalic() {
      return this.italic;
   }

   public boolean isStruckout() {
      return this.struckout;
   }

   protected void setFontBoldStyle(int var1) {
      Assert.verify(this.initialized ^ true);
      this.boldWeight = var1;
   }

   protected void setFontColour(int var1) {
      Assert.verify(this.initialized ^ true);
      this.colourIndex = var1;
   }

   protected void setFontItalic(boolean var1) {
      Assert.verify(this.initialized ^ true);
      this.italic = var1;
   }

   protected void setFontPointSize(int var1) {
      Assert.verify(this.initialized ^ true);
      this.pointHeight = var1;
   }

   protected void setFontScriptStyle(int var1) {
      Assert.verify(this.initialized ^ true);
      this.scriptStyle = var1;
   }

   protected void setFontStruckout(boolean var1) {
      this.struckout = var1;
   }

   protected void setFontUnderlineStyle(int var1) {
      Assert.verify(this.initialized ^ true);
      this.underlineStyle = var1;
   }

   public final void uninitialize() {
      this.initialized = false;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }
}
