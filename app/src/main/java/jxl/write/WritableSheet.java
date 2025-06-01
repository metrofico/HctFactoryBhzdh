package jxl.write;

import jxl.CellView;
import jxl.Range;
import jxl.Sheet;
import jxl.format.CellFormat;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.write.biff.RowsExceededException;

public interface WritableSheet extends Sheet {
   void addCell(WritableCell var1) throws WriteException, RowsExceededException;

   void addColumnPageBreak(int var1);

   void addHyperlink(WritableHyperlink var1) throws WriteException, RowsExceededException;

   void addImage(WritableImage var1);

   void addRowPageBreak(int var1);

   void applySharedDataValidation(WritableCell var1, int var2, int var3) throws WriteException;

   WritableImage getImage(int var1);

   int getNumberOfImages();

   WritableCell getWritableCell(int var1, int var2);

   WritableCell getWritableCell(String var1);

   WritableHyperlink[] getWritableHyperlinks();

   void insertColumn(int var1);

   void insertRow(int var1);

   Range mergeCells(int var1, int var2, int var3, int var4) throws WriteException, RowsExceededException;

   void removeColumn(int var1);

   void removeHyperlink(WritableHyperlink var1);

   void removeHyperlink(WritableHyperlink var1, boolean var2);

   void removeImage(WritableImage var1);

   void removeRow(int var1);

   void removeSharedDataValidation(WritableCell var1) throws WriteException;

   void setColumnGroup(int var1, int var2, boolean var3) throws WriteException, RowsExceededException;

   void setColumnView(int var1, int var2);

   void setColumnView(int var1, int var2, CellFormat var3);

   void setColumnView(int var1, CellView var2);

   void setFooter(String var1, String var2, String var3);

   void setHeader(String var1, String var2, String var3);

   void setHidden(boolean var1);

   void setName(String var1);

   void setPageSetup(PageOrientation var1);

   void setPageSetup(PageOrientation var1, double var2, double var4);

   void setPageSetup(PageOrientation var1, PaperSize var2, double var3, double var5);

   void setProtected(boolean var1);

   void setRowGroup(int var1, int var2, boolean var3) throws WriteException, RowsExceededException;

   void setRowView(int var1, int var2) throws RowsExceededException;

   void setRowView(int var1, int var2, boolean var3) throws RowsExceededException;

   void setRowView(int var1, CellView var2) throws RowsExceededException;

   void setRowView(int var1, boolean var2) throws RowsExceededException;

   void unmergeCells(Range var1);

   void unsetColumnGroup(int var1, int var2) throws WriteException, RowsExceededException;

   void unsetRowGroup(int var1, int var2) throws WriteException, RowsExceededException;
}
