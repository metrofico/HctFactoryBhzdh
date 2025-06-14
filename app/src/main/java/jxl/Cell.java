package jxl;

public interface Cell {
   CellFeatures getCellFeatures();

   jxl.format.CellFormat getCellFormat();

   int getColumn();

   String getContents();

   int getRow();

   CellType getType();

   boolean isHidden();
}
