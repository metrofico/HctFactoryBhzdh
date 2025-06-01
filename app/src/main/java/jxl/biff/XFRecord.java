package jxl.biff;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import jxl.WorkbookSettings;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.Font;
import jxl.format.Format;
import jxl.format.Orientation;
import jxl.format.Pattern;
import jxl.format.VerticalAlignment;
import jxl.read.biff.Record;

public class XFRecord extends WritableRecordData implements CellFormat {
   private static final int USE_ALIGNMENT = 16;
   private static final int USE_BACKGROUND = 64;
   private static final int USE_BORDER = 32;
   private static final int USE_DEFAULT_VALUE = 248;
   private static final int USE_FONT = 4;
   private static final int USE_FORMAT = 8;
   private static final int USE_PROTECTION = 128;
   public static final BiffType biff7 = new BiffType();
   public static final BiffType biff8 = new BiffType();
   protected static final XFType cell = new XFType();
   private static final int[] dateFormats = new int[]{14, 15, 16, 17, 18, 19, 20, 21, 22, 45, 46, 47};
   private static final DateFormat[] javaDateFormats = new DateFormat[]{SimpleDateFormat.getDateInstance(3), SimpleDateFormat.getDateInstance(2), new SimpleDateFormat("d-MMM"), new SimpleDateFormat("MMM-yy"), new SimpleDateFormat("h:mm a"), new SimpleDateFormat("h:mm:ss a"), new SimpleDateFormat("H:mm"), new SimpleDateFormat("H:mm:ss"), new SimpleDateFormat("M/d/yy H:mm"), new SimpleDateFormat("mm:ss"), new SimpleDateFormat("H:mm:ss"), new SimpleDateFormat("mm:ss.S")};
   private static NumberFormat[] javaNumberFormats = new NumberFormat[]{new DecimalFormat("0"), new DecimalFormat("0.00"), new DecimalFormat("#,##0"), new DecimalFormat("#,##0.00"), new DecimalFormat("$#,##0;($#,##0)"), new DecimalFormat("$#,##0;($#,##0)"), new DecimalFormat("$#,##0.00;($#,##0.00)"), new DecimalFormat("$#,##0.00;($#,##0.00)"), new DecimalFormat("0%"), new DecimalFormat("0.00%"), new DecimalFormat("0.00E00"), new DecimalFormat("#,##0;(#,##0)"), new DecimalFormat("#,##0;(#,##0)"), new DecimalFormat("#,##0.00;(#,##0.00)"), new DecimalFormat("#,##0.00;(#,##0.00)"), new DecimalFormat("#,##0;(#,##0)"), new DecimalFormat("$#,##0;($#,##0)"), new DecimalFormat("#,##0.00;(#,##0.00)"), new DecimalFormat("$#,##0.00;($#,##0.00)"), new DecimalFormat("##0.0E0")};
   private static Logger logger = Logger.getLogger(XFRecord.class);
   private static int[] numberFormats = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 37, 38, 39, 40, 41, 42, 43, 44, 48};
   protected static final XFType style = new XFType();
   private Alignment align;
   private Colour backgroundColour;
   private BiffType biffType;
   private BorderLineStyle bottomBorder;
   private Colour bottomBorderColour;
   private boolean copied;
   private boolean date;
   private DateFormat dateFormat;
   private Format excelFormat;
   private FontRecord font;
   private int fontIndex;
   private DisplayFormat format;
   public int formatIndex;
   private boolean formatInfoInitialized;
   private FormattingRecords formattingRecords;
   private boolean hidden;
   private int indentation;
   private boolean initialized;
   private BorderLineStyle leftBorder;
   private Colour leftBorderColour;
   private boolean locked;
   private boolean number;
   private NumberFormat numberFormat;
   private int options;
   private Orientation orientation;
   private int parentFormat;
   private Pattern pattern;
   private boolean read;
   private BorderLineStyle rightBorder;
   private Colour rightBorderColour;
   private boolean shrinkToFit;
   private BorderLineStyle topBorder;
   private Colour topBorderColour;
   private byte usedAttributes;
   private VerticalAlignment valign;
   private boolean wrap;
   private XFType xfFormatType;
   private int xfIndex;

   public XFRecord(FontRecord var1, DisplayFormat var2) {
      super(Type.XF);
      boolean var4 = false;
      this.initialized = false;
      this.locked = true;
      this.hidden = false;
      this.align = Alignment.GENERAL;
      this.valign = VerticalAlignment.BOTTOM;
      this.orientation = Orientation.HORIZONTAL;
      this.wrap = false;
      this.leftBorder = BorderLineStyle.NONE;
      this.rightBorder = BorderLineStyle.NONE;
      this.topBorder = BorderLineStyle.NONE;
      this.bottomBorder = BorderLineStyle.NONE;
      this.leftBorderColour = Colour.AUTOMATIC;
      this.rightBorderColour = Colour.AUTOMATIC;
      this.topBorderColour = Colour.AUTOMATIC;
      this.bottomBorderColour = Colour.AUTOMATIC;
      this.pattern = Pattern.NONE;
      this.backgroundColour = Colour.DEFAULT_BACKGROUND;
      this.indentation = 0;
      this.shrinkToFit = false;
      this.usedAttributes = 124;
      this.parentFormat = 0;
      this.xfFormatType = null;
      this.font = var1;
      this.format = var2;
      this.biffType = biff8;
      this.read = false;
      this.copied = false;
      this.formatInfoInitialized = true;
      boolean var3;
      if (var1 != null) {
         var3 = true;
      } else {
         var3 = false;
      }

      Assert.verify(var3);
      var3 = var4;
      if (this.format != null) {
         var3 = true;
      }

      Assert.verify(var3);
   }

   protected XFRecord(XFRecord var1) {
      super(Type.XF);
      this.initialized = false;
      this.locked = var1.locked;
      this.hidden = var1.hidden;
      this.align = var1.align;
      this.valign = var1.valign;
      this.orientation = var1.orientation;
      this.wrap = var1.wrap;
      this.leftBorder = var1.leftBorder;
      this.rightBorder = var1.rightBorder;
      this.topBorder = var1.topBorder;
      this.bottomBorder = var1.bottomBorder;
      this.leftBorderColour = var1.leftBorderColour;
      this.rightBorderColour = var1.rightBorderColour;
      this.topBorderColour = var1.topBorderColour;
      this.bottomBorderColour = var1.bottomBorderColour;
      this.pattern = var1.pattern;
      this.xfFormatType = var1.xfFormatType;
      this.indentation = var1.indentation;
      this.shrinkToFit = var1.shrinkToFit;
      this.parentFormat = var1.parentFormat;
      this.backgroundColour = var1.backgroundColour;
      this.font = var1.font;
      this.format = var1.format;
      this.fontIndex = var1.fontIndex;
      this.formatIndex = var1.formatIndex;
      this.formatInfoInitialized = var1.formatInfoInitialized;
      this.biffType = biff8;
      this.read = false;
      this.copied = true;
   }

   protected XFRecord(CellFormat var1) {
      super(Type.XF);
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      Assert.verify(var1 instanceof XFRecord);
      XFRecord var3 = (XFRecord)var1;
      if (!var3.formatInfoInitialized) {
         var3.initializeFormatInformation();
      }

      this.locked = var3.locked;
      this.hidden = var3.hidden;
      this.align = var3.align;
      this.valign = var3.valign;
      this.orientation = var3.orientation;
      this.wrap = var3.wrap;
      this.leftBorder = var3.leftBorder;
      this.rightBorder = var3.rightBorder;
      this.topBorder = var3.topBorder;
      this.bottomBorder = var3.bottomBorder;
      this.leftBorderColour = var3.leftBorderColour;
      this.rightBorderColour = var3.rightBorderColour;
      this.topBorderColour = var3.topBorderColour;
      this.bottomBorderColour = var3.bottomBorderColour;
      this.pattern = var3.pattern;
      this.xfFormatType = var3.xfFormatType;
      this.parentFormat = var3.parentFormat;
      this.indentation = var3.indentation;
      this.shrinkToFit = var3.shrinkToFit;
      this.backgroundColour = var3.backgroundColour;
      this.font = new FontRecord(var3.getFont());
      if (var3.getFormat() == null) {
         if (var3.format.isBuiltIn()) {
            this.format = var3.format;
         } else {
            this.format = new FormatRecord((FormatRecord)var3.format);
         }
      } else if (var3.getFormat() instanceof BuiltInFormat) {
         this.excelFormat = (BuiltInFormat)var3.excelFormat;
         this.format = (BuiltInFormat)var3.excelFormat;
      } else {
         Assert.verify(var3.formatInfoInitialized);
         Assert.verify(var3.excelFormat instanceof FormatRecord);
         FormatRecord var4 = new FormatRecord((FormatRecord)var3.excelFormat);
         this.excelFormat = var4;
         this.format = var4;
      }

      this.biffType = biff8;
      this.formatInfoInitialized = true;
      this.read = false;
      this.copied = false;
      this.initialized = false;
   }

   public XFRecord(Record var1, WorkbookSettings var2, BiffType var3) {
      super(var1);
      this.biffType = var3;
      byte[] var7 = this.getRecord().getData();
      this.fontIndex = IntegerHelper.getInt(var7[0], var7[1]);
      this.formatIndex = IntegerHelper.getInt(var7[2], var7[3]);
      this.date = false;
      this.number = false;
      int var4 = 0;

      while(true) {
         int[] var9 = dateFormats;
         if (var4 >= var9.length || this.date) {
            var4 = 0;

            while(true) {
               var9 = numberFormats;
               if (var4 >= var9.length || this.number) {
                  var4 = IntegerHelper.getInt(var7[4], var7[5]);
                  int var5 = ('\ufff0' & var4) >> 4;
                  this.parentFormat = var5;
                  XFType var8;
                  if ((var4 & 4) == 0) {
                     var8 = cell;
                  } else {
                     var8 = style;
                  }

                  this.xfFormatType = var8;
                  boolean var6;
                  if ((var4 & 1) != 0) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  this.locked = var6;
                  if ((var4 & 2) != 0) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  this.hidden = var6;
                  if (var8 == cell && (var5 & 4095) == 4095) {
                     this.parentFormat = 0;
                     logger.warn("Invalid parent format found - ignoring");
                  }

                  this.initialized = false;
                  this.read = true;
                  this.formatInfoInitialized = false;
                  this.copied = false;
                  return;
               }

               if (this.formatIndex == var9[var4]) {
                  this.number = true;
                  DecimalFormat var10 = (DecimalFormat)javaNumberFormats[var4].clone();
                  var10.setDecimalFormatSymbols(new DecimalFormatSymbols(var2.getLocale()));
                  this.numberFormat = var10;
               }

               ++var4;
            }
         }

         if (this.formatIndex == var9[var4]) {
            this.date = true;
            this.dateFormat = javaDateFormats[var4];
         }

         ++var4;
      }
   }

   private void initializeFormatInformation() {
      if (this.formatIndex < BuiltInFormat.builtIns.length && BuiltInFormat.builtIns[this.formatIndex] != null) {
         this.excelFormat = BuiltInFormat.builtIns[this.formatIndex];
      } else {
         this.excelFormat = this.formattingRecords.getFormatRecord(this.formatIndex);
      }

      this.font = this.formattingRecords.getFonts().getFont(this.fontIndex);
      byte[] var6 = this.getRecord().getData();
      int var2 = IntegerHelper.getInt(var6[4], var6[5]);
      int var1 = ('\ufff0' & var2) >> 4;
      this.parentFormat = var1;
      XFType var5;
      if ((var2 & 4) == 0) {
         var5 = cell;
      } else {
         var5 = style;
      }

      this.xfFormatType = var5;
      boolean var4 = false;
      boolean var3;
      if ((var2 & 1) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.locked = var3;
      if ((var2 & 2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.hidden = var3;
      if (var5 == cell && (var1 & 4095) == 4095) {
         this.parentFormat = 0;
         logger.warn("Invalid parent format found - ignoring");
      }

      var1 = IntegerHelper.getInt(var6[6], var6[7]);
      if ((var1 & 8) != 0) {
         this.wrap = true;
      }

      this.align = Alignment.getAlignment(var1 & 7);
      this.valign = VerticalAlignment.getAlignment(var1 >> 4 & 7);
      this.orientation = Orientation.getOrientation(var1 >> 8 & 255);
      var1 = IntegerHelper.getInt(var6[8], var6[9]);
      this.indentation = var1 & 15;
      var3 = var4;
      if ((var1 & 16) != 0) {
         var3 = true;
      }

      this.shrinkToFit = var3;
      BiffType var7 = this.biffType;
      BiffType var8 = biff8;
      if (var7 == var8) {
         this.usedAttributes = var6[9];
      }

      var1 = IntegerHelper.getInt(var6[10], var6[11]);
      this.leftBorder = BorderLineStyle.getStyle(var1 & 7);
      this.rightBorder = BorderLineStyle.getStyle(var1 >> 4 & 7);
      this.topBorder = BorderLineStyle.getStyle(var1 >> 8 & 7);
      this.bottomBorder = BorderLineStyle.getStyle(var1 >> 12 & 7);
      var1 = IntegerHelper.getInt(var6[12], var6[13]);
      this.leftBorderColour = Colour.getInternalColour(var1 & 127);
      this.rightBorderColour = Colour.getInternalColour((var1 & 16256) >> 7);
      var1 = IntegerHelper.getInt(var6[14], var6[15]);
      this.topBorderColour = Colour.getInternalColour(var1 & 127);
      this.bottomBorderColour = Colour.getInternalColour((var1 & 16256) >> 7);
      if (this.biffType == var8) {
         this.pattern = Pattern.getPattern((IntegerHelper.getInt(var6[16], var6[17]) & 'ﰀ') >> 10);
         Colour var9 = Colour.getInternalColour(IntegerHelper.getInt(var6[18], var6[19]) & 63);
         this.backgroundColour = var9;
         if (var9 == Colour.UNKNOWN || this.backgroundColour == Colour.DEFAULT_BACKGROUND1) {
            this.backgroundColour = Colour.DEFAULT_BACKGROUND;
         }
      } else {
         this.pattern = Pattern.NONE;
         this.backgroundColour = Colour.DEFAULT_BACKGROUND;
      }

      this.formatInfoInitialized = true;
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof XFRecord)) {
         return false;
      } else {
         XFRecord var2 = (XFRecord)var1;
         if (!this.formatInfoInitialized) {
            this.initializeFormatInformation();
         }

         if (!var2.formatInfoInitialized) {
            var2.initializeFormatInformation();
         }

         if (this.xfFormatType == var2.xfFormatType && this.parentFormat == var2.parentFormat && this.locked == var2.locked && this.hidden == var2.hidden && this.usedAttributes == var2.usedAttributes && this.align == var2.align && this.valign == var2.valign && this.orientation == var2.orientation && this.wrap == var2.wrap && this.shrinkToFit == var2.shrinkToFit && this.indentation == var2.indentation && this.leftBorder == var2.leftBorder && this.rightBorder == var2.rightBorder && this.topBorder == var2.topBorder && this.bottomBorder == var2.bottomBorder && this.leftBorderColour == var2.leftBorderColour && this.rightBorderColour == var2.rightBorderColour && this.topBorderColour == var2.topBorderColour && this.bottomBorderColour == var2.bottomBorderColour && this.backgroundColour == var2.backgroundColour && this.pattern == var2.pattern) {
            if (this.initialized && var2.initialized) {
               if (this.fontIndex != var2.fontIndex || this.formatIndex != var2.formatIndex) {
                  return false;
               }

               return true;
            }

            if (this.font.equals(var2.font) && this.format.equals(var2.format)) {
               return true;
            }
         }

         return false;
      }
   }

   public Alignment getAlignment() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.align;
   }

   public Colour getBackgroundColour() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.backgroundColour;
   }

   public BorderLineStyle getBorder(Border var1) {
      return this.getBorderLine(var1);
   }

   public Colour getBorderColour(Border var1) {
      if (var1 != Border.NONE && var1 != Border.ALL) {
         if (!this.formatInfoInitialized) {
            this.initializeFormatInformation();
         }

         if (var1 == Border.LEFT) {
            return this.leftBorderColour;
         } else if (var1 == Border.RIGHT) {
            return this.rightBorderColour;
         } else if (var1 == Border.TOP) {
            return this.topBorderColour;
         } else {
            return var1 == Border.BOTTOM ? this.bottomBorderColour : Colour.BLACK;
         }
      } else {
         return Colour.PALETTE_BLACK;
      }
   }

   public BorderLineStyle getBorderLine(Border var1) {
      if (var1 != Border.NONE && var1 != Border.ALL) {
         if (!this.formatInfoInitialized) {
            this.initializeFormatInformation();
         }

         if (var1 == Border.LEFT) {
            return this.leftBorder;
         } else if (var1 == Border.RIGHT) {
            return this.rightBorder;
         } else if (var1 == Border.TOP) {
            return this.topBorder;
         } else {
            return var1 == Border.BOTTOM ? this.bottomBorder : BorderLineStyle.NONE;
         }
      } else {
         return BorderLineStyle.NONE;
      }
   }

   public byte[] getData() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      byte[] var5 = new byte[20];
      IntegerHelper.getTwoBytes(this.fontIndex, var5, 0);
      IntegerHelper.getTwoBytes(this.formatIndex, var5, 2);
      int var2 = this.getLocked();
      int var1 = var2;
      if (this.getHidden()) {
         var1 = var2 | 2;
      }

      var2 = var1;
      if (this.xfFormatType == style) {
         var2 = var1 | 4;
         this.parentFormat = 65535;
      }

      IntegerHelper.getTwoBytes(var2 | this.parentFormat << 4, var5, 4);
      var2 = this.align.getValue();
      var1 = var2;
      if (this.wrap) {
         var1 = var2 | 8;
      }

      IntegerHelper.getTwoBytes(var1 | this.valign.getValue() << 4 | this.orientation.getValue() << 8, var5, 6);
      var5[9] = 16;
      var1 = this.leftBorder.getValue();
      var1 = this.rightBorder.getValue() << 4 | var1 | this.topBorder.getValue() << 8 | this.bottomBorder.getValue() << 12;
      IntegerHelper.getTwoBytes(var1, var5, 10);
      if (var1 != 0) {
         byte var6 = (byte)this.leftBorderColour.getValue();
         byte var7 = (byte)this.rightBorderColour.getValue();
         byte var3 = (byte)this.topBorderColour.getValue();
         byte var4 = (byte)this.bottomBorderColour.getValue();
         IntegerHelper.getTwoBytes(var6 & 127 | (var7 & 127) << 7, var5, 12);
         IntegerHelper.getTwoBytes(var3 & 127 | (var4 & 127) << 7, var5, 14);
      }

      IntegerHelper.getTwoBytes(this.pattern.getValue() << 10, var5, 16);
      IntegerHelper.getTwoBytes(this.backgroundColour.getValue() | 8192, var5, 18);
      var1 = this.options | this.indentation & 15;
      this.options = var1;
      if (this.shrinkToFit) {
         this.options = 16 | var1;
      } else {
         this.options = var1 & 239;
      }

      var5[8] = (byte)this.options;
      if (this.biffType == biff8) {
         var5[9] = this.usedAttributes;
      }

      return var5;
   }

   public DateFormat getDateFormat() {
      return this.dateFormat;
   }

   public Font getFont() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.font;
   }

   public int getFontIndex() {
      return this.fontIndex;
   }

   public Format getFormat() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.excelFormat;
   }

   public int getFormatRecord() {
      return this.formatIndex;
   }

   protected final boolean getHidden() {
      return this.hidden;
   }

   public int getIndentation() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.indentation;
   }

   protected final boolean getLocked() {
      return this.locked;
   }

   public NumberFormat getNumberFormat() {
      return this.numberFormat;
   }

   public Orientation getOrientation() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.orientation;
   }

   public Pattern getPattern() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.pattern;
   }

   public VerticalAlignment getVerticalAlignment() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.valign;
   }

   public boolean getWrap() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.wrap;
   }

   public final int getXFIndex() {
      return this.xfIndex;
   }

   public final boolean hasBorders() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.leftBorder != BorderLineStyle.NONE || this.rightBorder != BorderLineStyle.NONE || this.topBorder != BorderLineStyle.NONE || this.bottomBorder != BorderLineStyle.NONE;
   }

   public int hashCode() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      int var2 = (((629 + this.hidden) * 37 + this.locked) * 37 + this.wrap) * 37 + this.shrinkToFit;
      XFType var3 = this.xfFormatType;
      int var1;
      if (var3 == cell) {
         var1 = var2 * 37 + 1;
      } else {
         var1 = var2;
         if (var3 == style) {
            var1 = var2 * 37 + 2;
         }
      }

      return 37 * (((((((((((((var1 * 37 + this.align.getValue() + 1) * 37 + this.valign.getValue() + 1) * 37 + this.orientation.getValue() ^ this.leftBorder.getDescription().hashCode() ^ this.rightBorder.getDescription().hashCode() ^ this.topBorder.getDescription().hashCode() ^ this.bottomBorder.getDescription().hashCode()) * 37 + this.leftBorderColour.getValue()) * 37 + this.rightBorderColour.getValue()) * 37 + this.topBorderColour.getValue()) * 37 + this.bottomBorderColour.getValue()) * 37 + this.backgroundColour.getValue()) * 37 + this.pattern.getValue() + 1) * 37 + this.usedAttributes) * 37 + this.parentFormat) * 37 + this.fontIndex) * 37 + this.formatIndex) + this.indentation;
   }

   public final void initialize(int var1, FormattingRecords var2, Fonts var3) throws NumFormatRecordsException {
      this.xfIndex = var1;
      this.formattingRecords = var2;
      if (!this.read && !this.copied) {
         if (!this.font.isInitialized()) {
            var3.addFont(this.font);
         }

         if (!this.format.isInitialized()) {
            var2.addFormat(this.format);
         }

         this.fontIndex = this.font.getFontIndex();
         this.formatIndex = this.format.getFormatIndex();
         this.initialized = true;
      } else {
         this.initialized = true;
      }
   }

   public boolean isDate() {
      return this.date;
   }

   public final boolean isInitialized() {
      return this.initialized;
   }

   public boolean isLocked() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.locked;
   }

   public boolean isNumber() {
      return this.number;
   }

   public final boolean isRead() {
      return this.read;
   }

   public boolean isShrinkToFit() {
      if (!this.formatInfoInitialized) {
         this.initializeFormatInformation();
      }

      return this.shrinkToFit;
   }

   void rationalize(IndexMapping var1) {
      this.xfIndex = var1.getNewIndex(this.xfIndex);
      if (this.xfFormatType == cell) {
         this.parentFormat = var1.getNewIndex(this.parentFormat);
      }

   }

   public void setFont(FontRecord var1) {
      this.font = var1;
   }

   void setFontIndex(int var1) {
      this.fontIndex = var1;
   }

   void setFormatIndex(int var1) {
      this.formatIndex = var1;
   }

   protected void setXFAlignment(Alignment var1) {
      Assert.verify(this.initialized ^ true);
      this.align = var1;
      this.usedAttributes = (byte)(this.usedAttributes | 16);
   }

   protected void setXFBackground(Colour var1, Pattern var2) {
      Assert.verify(this.initialized ^ true);
      this.backgroundColour = var1;
      this.pattern = var2;
      this.usedAttributes = (byte)(this.usedAttributes | 64);
   }

   protected void setXFBorder(Border var1, BorderLineStyle var2, Colour var3) {
      Colour var4;
      label24: {
         Assert.verify(this.initialized ^ true);
         if (var3 != Colour.BLACK) {
            var4 = var3;
            if (var3 != Colour.UNKNOWN) {
               break label24;
            }
         }

         var4 = Colour.PALETTE_BLACK;
      }

      if (var1 == Border.LEFT) {
         this.leftBorder = var2;
         this.leftBorderColour = var4;
      } else if (var1 == Border.RIGHT) {
         this.rightBorder = var2;
         this.rightBorderColour = var4;
      } else if (var1 == Border.TOP) {
         this.topBorder = var2;
         this.topBorderColour = var4;
      } else if (var1 == Border.BOTTOM) {
         this.bottomBorder = var2;
         this.bottomBorderColour = var4;
      }

      this.usedAttributes = (byte)(this.usedAttributes | 32);
   }

   protected final void setXFCellOptions(int var1) {
      this.options |= var1;
   }

   protected void setXFDetails(XFType var1, int var2) {
      this.xfFormatType = var1;
      this.parentFormat = var2;
   }

   protected void setXFIndentation(int var1) {
      Assert.verify(this.initialized ^ true);
      this.indentation = var1;
      this.usedAttributes = (byte)(this.usedAttributes | 16);
   }

   final void setXFIndex(int var1) {
      this.xfIndex = var1;
   }

   protected final void setXFLocked(boolean var1) {
      this.locked = var1;
      this.usedAttributes = (byte)(this.usedAttributes | 128);
   }

   protected void setXFOrientation(Orientation var1) {
      Assert.verify(this.initialized ^ true);
      this.orientation = var1;
      this.usedAttributes = (byte)(this.usedAttributes | 16);
   }

   protected void setXFShrinkToFit(boolean var1) {
      Assert.verify(this.initialized ^ true);
      this.shrinkToFit = var1;
      this.usedAttributes = (byte)(this.usedAttributes | 16);
   }

   protected void setXFVerticalAlignment(VerticalAlignment var1) {
      Assert.verify(this.initialized ^ true);
      this.valign = var1;
      this.usedAttributes = (byte)(this.usedAttributes | 16);
   }

   protected void setXFWrap(boolean var1) {
      Assert.verify(this.initialized ^ true);
      this.wrap = var1;
      this.usedAttributes = (byte)(this.usedAttributes | 16);
   }

   public final void uninitialize() {
      if (this.initialized) {
         logger.warn("A default format has been initialized");
      }

      this.initialized = false;
   }

   private static class BiffType {
      private BiffType() {
      }

      // $FF: synthetic method
      BiffType(Object var1) {
         this();
      }
   }

   private static class XFType {
      private XFType() {
      }

      // $FF: synthetic method
      XFType(Object var1) {
         this();
      }
   }
}
