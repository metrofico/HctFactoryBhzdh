package jxl.biff;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.Colour;
import jxl.format.RGB;
import jxl.write.biff.File;

public class FormattingRecords {
   private static final int customFormatStartIndex = 164;
   private static Logger logger = Logger.getLogger(FormattingRecords.class);
   private static final int maxFormatRecordsIndex = 441;
   private static final int minXFRecords = 21;
   private Fonts fonts;
   private HashMap formats = new HashMap(10);
   private ArrayList formatsList = new ArrayList(10);
   private int nextCustomIndexNumber;
   private PaletteRecord palette;
   private ArrayList xfRecords = new ArrayList(10);

   public FormattingRecords(Fonts var1) {
      this.fonts = var1;
      this.nextCustomIndexNumber = 164;
   }

   public final void addFormat(DisplayFormat var1) throws NumFormatRecordsException {
      if (var1.isInitialized() && var1.getFormatIndex() >= 441) {
         logger.warn("Format index exceeds Excel maximum - assigning custom number");
         var1.initialize(this.nextCustomIndexNumber);
         ++this.nextCustomIndexNumber;
      }

      if (!var1.isInitialized()) {
         var1.initialize(this.nextCustomIndexNumber);
         ++this.nextCustomIndexNumber;
      }

      if (this.nextCustomIndexNumber <= 441) {
         if (var1.getFormatIndex() >= this.nextCustomIndexNumber) {
            this.nextCustomIndexNumber = var1.getFormatIndex() + 1;
         }

         if (!var1.isBuiltIn()) {
            this.formatsList.add(var1);
            this.formats.put(new Integer(var1.getFormatIndex()), var1);
         }

      } else {
         this.nextCustomIndexNumber = 441;
         throw new NumFormatRecordsException();
      }
   }

   public final void addStyle(XFRecord var1) throws NumFormatRecordsException {
      if (!var1.isInitialized()) {
         var1.initialize(this.xfRecords.size(), this, this.fonts);
         this.xfRecords.add(var1);
      } else if (var1.getXFIndex() >= this.xfRecords.size()) {
         this.xfRecords.add(var1);
      }

   }

   public RGB getColourRGB(Colour var1) {
      PaletteRecord var2 = this.palette;
      return var2 == null ? var1.getDefaultRGB() : var2.getColourRGB(var1);
   }

   public final DateFormat getDateFormat(int var1) {
      XFRecord var2 = (XFRecord)this.xfRecords.get(var1);
      if (var2.isDate()) {
         return var2.getDateFormat();
      } else {
         FormatRecord var3 = (FormatRecord)this.formats.get(new Integer(var2.getFormatRecord()));
         DateFormat var4 = null;
         if (var3 == null) {
            return null;
         } else {
            if (var3.isDate()) {
               var4 = var3.getDateFormat();
            }

            return var4;
         }
      }
   }

   protected final Fonts getFonts() {
      return this.fonts;
   }

   FormatRecord getFormatRecord(int var1) {
      return (FormatRecord)this.formats.get(new Integer(var1));
   }

   public final NumberFormat getNumberFormat(int var1) {
      XFRecord var2 = (XFRecord)this.xfRecords.get(var1);
      if (var2.isNumber()) {
         return var2.getNumberFormat();
      } else {
         FormatRecord var3 = (FormatRecord)this.formats.get(new Integer(var2.getFormatRecord()));
         NumberFormat var4 = null;
         if (var3 == null) {
            return null;
         } else {
            if (var3.isNumber()) {
               var4 = var3.getNumberFormat();
            }

            return var4;
         }
      }
   }

   protected final int getNumberOfFormatRecords() {
      return this.formatsList.size();
   }

   public PaletteRecord getPalette() {
      return this.palette;
   }

   public final XFRecord getXFRecord(int var1) {
      return (XFRecord)this.xfRecords.get(var1);
   }

   public final boolean isDate(int var1) {
      XFRecord var3 = (XFRecord)this.xfRecords.get(var1);
      if (var3.isDate()) {
         return true;
      } else {
         FormatRecord var4 = (FormatRecord)this.formats.get(new Integer(var3.getFormatRecord()));
         boolean var2;
         if (var4 == null) {
            var2 = false;
         } else {
            var2 = var4.isDate();
         }

         return var2;
      }
   }

   public IndexMapping rationalize(IndexMapping var1, IndexMapping var2) {
      XFRecord var6;
      for(Iterator var7 = this.xfRecords.iterator(); var7.hasNext(); var6.setFontIndex(var1.getNewIndex(var6.getFontIndex()))) {
         var6 = (XFRecord)var7.next();
         if (var6.getFormatRecord() >= 164) {
            var6.setFormatIndex(var2.getNewIndex(var6.getFormatRecord()));
         }
      }

      int var4 = 21;
      ArrayList var9 = new ArrayList(21);
      var1 = new IndexMapping(this.xfRecords.size());
      int var5 = Math.min(21, this.xfRecords.size());

      int var3;
      for(var3 = 0; var3 < var5; ++var3) {
         var9.add(this.xfRecords.get(var3));
         var1.setMapping(var3, var3);
      }

      if (var5 < 21) {
         logger.warn("There are less than the expected minimum number of XF records");
         return var1;
      } else {
         byte var10 = 0;
         var3 = var4;

         for(var4 = var10; var3 < this.xfRecords.size(); ++var3) {
            XFRecord var13 = (XFRecord)this.xfRecords.get(var3);
            Iterator var8 = var9.iterator();
            boolean var11 = false;

            while(var8.hasNext() && !var11) {
               var6 = (XFRecord)var8.next();
               if (var6.equals(var13)) {
                  var1.setMapping(var3, var1.getNewIndex(var6.getXFIndex()));
                  ++var4;
                  var11 = true;
               }
            }

            if (!var11) {
               var9.add(var13);
               var1.setMapping(var3, var3 - var4);
            }
         }

         Iterator var12 = this.xfRecords.iterator();

         while(var12.hasNext()) {
            ((XFRecord)var12.next()).rationalize(var1);
         }

         this.xfRecords = var9;
         return var1;
      }
   }

   public IndexMapping rationalizeDisplayFormats() {
      ArrayList var9 = new ArrayList();
      IndexMapping var4 = new IndexMapping(this.nextCustomIndexNumber);
      Iterator var5 = this.formatsList.iterator();
      int var1 = 0;

      DisplayFormat var6;
      while(var5.hasNext()) {
         var6 = (DisplayFormat)var5.next();
         Assert.verify(var6.isBuiltIn() ^ true);
         Iterator var8 = var9.iterator();
         boolean var3 = false;
         int var2 = var1;

         while(var8.hasNext() && !var3) {
            DisplayFormat var7 = (DisplayFormat)var8.next();
            if (var7.equals(var6)) {
               var4.setMapping(var6.getFormatIndex(), var4.getNewIndex(var7.getFormatIndex()));
               ++var2;
               var3 = true;
            }
         }

         var1 = var2;
         if (!var3) {
            var9.add(var6);
            if (var6.getFormatIndex() - var2 > 441) {
               logger.warn("Too many number formats - using default format.");
            }

            var4.setMapping(var6.getFormatIndex(), var6.getFormatIndex() - var2);
            var1 = var2;
         }
      }

      this.formatsList = var9;
      var5 = var9.iterator();

      while(var5.hasNext()) {
         var6 = (DisplayFormat)var5.next();
         var6.initialize(var4.getNewIndex(var6.getFormatIndex()));
      }

      return var4;
   }

   public IndexMapping rationalizeFonts() {
      return this.fonts.rationalize();
   }

   public void setColourRGB(Colour var1, int var2, int var3, int var4) {
      if (this.palette == null) {
         this.palette = new PaletteRecord();
      }

      this.palette.setColourRGB(var1, var2, var3, var4);
   }

   public void setPalette(PaletteRecord var1) {
      this.palette = var1;
   }

   public void write(File var1) throws IOException {
      Iterator var2 = this.formatsList.iterator();

      while(var2.hasNext()) {
         var1.write((FormatRecord)var2.next());
      }

      var2 = this.xfRecords.iterator();

      while(var2.hasNext()) {
         var1.write((XFRecord)var2.next());
      }

      var1.write(new BuiltInStyle(16, 3));
      var1.write(new BuiltInStyle(17, 6));
      var1.write(new BuiltInStyle(18, 4));
      var1.write(new BuiltInStyle(19, 7));
      var1.write(new BuiltInStyle(0, 0));
      var1.write(new BuiltInStyle(20, 5));
   }
}
