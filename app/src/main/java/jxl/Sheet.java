package jxl;

import java.util.regex.Pattern;

public interface Sheet {
   Cell findCell(String var1);

   Cell findCell(String var1, int var2, int var3, int var4, int var5, boolean var6);

   Cell findCell(Pattern var1, int var2, int var3, int var4, int var5, boolean var6);

   LabelCell findLabelCell(String var1);

   Cell getCell(int var1, int var2);

   Cell getCell(String var1);

   Cell[] getColumn(int var1);

   jxl.format.CellFormat getColumnFormat(int var1);

   int[] getColumnPageBreaks();

   CellView getColumnView(int var1);

   int getColumnWidth(int var1);

   int getColumns();

   Image getDrawing(int var1);

   Hyperlink[] getHyperlinks();

   Range[] getMergedCells();

   String getName();

   int getNumberOfImages();

   Cell[] getRow(int var1);

   int getRowHeight(int var1);

   int[] getRowPageBreaks();

   CellView getRowView(int var1);

   int getRows();

   SheetSettings getSettings();

   boolean isHidden();

   boolean isProtected();
}
