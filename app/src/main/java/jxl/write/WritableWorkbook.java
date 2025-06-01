package jxl.write;

import java.io.File;
import java.io.IOException;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;

public abstract class WritableWorkbook {
   public static final WritableFont ARIAL_10_PT;
   public static final WritableCellFormat HIDDEN_STYLE;
   public static final WritableFont HYPERLINK_FONT;
   public static final WritableCellFormat HYPERLINK_STYLE;
   public static final WritableCellFormat NORMAL_STYLE;

   static {
      WritableFont var1 = new WritableFont(WritableFont.ARIAL);
      ARIAL_10_PT = var1;
      WritableFont var0 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.SINGLE, jxl.format.Colour.BLUE);
      HYPERLINK_FONT = var0;
      NORMAL_STYLE = new WritableCellFormat(var1, NumberFormats.DEFAULT);
      HYPERLINK_STYLE = new WritableCellFormat(var0);
      HIDDEN_STYLE = new WritableCellFormat(new DateFormat(";;;"));
   }

   protected WritableWorkbook() {
   }

   public abstract void addNameArea(String var1, WritableSheet var2, int var3, int var4, int var5, int var6);

   public abstract void close() throws IOException, WriteException;

   public void copy(Workbook var1) {
   }

   public abstract void copySheet(int var1, String var2, int var3);

   public abstract void copySheet(String var1, String var2, int var3);

   public abstract WritableSheet createSheet(String var1, int var2);

   public abstract Range[] findByName(String var1);

   public abstract WritableCell findCellByName(String var1);

   public abstract int getNumberOfSheets();

   public abstract String[] getRangeNames();

   public abstract WritableSheet getSheet(int var1) throws IndexOutOfBoundsException;

   public abstract WritableSheet getSheet(String var1);

   public abstract String[] getSheetNames();

   public abstract WritableSheet[] getSheets();

   public abstract WritableCell getWritableCell(String var1);

   public abstract WritableSheet importSheet(String var1, int var2, Sheet var3);

   public abstract WritableSheet moveSheet(int var1, int var2);

   public abstract void removeRangeName(String var1);

   public abstract void removeSheet(int var1);

   public abstract void setColourRGB(jxl.format.Colour var1, int var2, int var3, int var4);

   public abstract void setOutputFile(File var1) throws IOException;

   public abstract void setProtected(boolean var1);

   public abstract void write() throws IOException;
}
