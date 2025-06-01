package jxl.write;

import jxl.Cell;
import jxl.format.CellFormat;

public interface WritableCell extends Cell {
   WritableCell copyTo(int var1, int var2);

   WritableCellFeatures getWritableCellFeatures();

   void setCellFeatures(WritableCellFeatures var1);

   void setCellFormat(CellFormat var1);
}
