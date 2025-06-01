package jxl.read.biff;

import jxl.Cell;
import jxl.CellFeatures;
import jxl.CellType;
import jxl.biff.FormattingRecords;
import jxl.common.Logger;
import jxl.format.CellFormat;

class MulBlankCell implements Cell, CellFeaturesAccessor {
   private static Logger logger = Logger.getLogger(MulBlankCell.class);
   private CellFormat cellFormat;
   private int column;
   private CellFeatures features;
   private FormattingRecords formattingRecords;
   private boolean initialized;
   private int row;
   private SheetImpl sheet;
   private int xfIndex;

   public MulBlankCell(int var1, int var2, int var3, FormattingRecords var4, SheetImpl var5) {
      this.row = var1;
      this.column = var2;
      this.xfIndex = var3;
      this.formattingRecords = var4;
      this.sheet = var5;
      this.initialized = false;
   }

   public CellFeatures getCellFeatures() {
      return this.features;
   }

   public CellFormat getCellFormat() {
      if (!this.initialized) {
         this.cellFormat = this.formattingRecords.getXFRecord(this.xfIndex);
         this.initialized = true;
      }

      return this.cellFormat;
   }

   public final int getColumn() {
      return this.column;
   }

   public String getContents() {
      return "";
   }

   public final int getRow() {
      return this.row;
   }

   public CellType getType() {
      return CellType.EMPTY;
   }

   public boolean isHidden() {
      ColumnInfoRecord var1 = this.sheet.getColumnInfo(this.column);
      if (var1 != null && var1.getWidth() == 0) {
         return true;
      } else {
         RowRecord var2 = this.sheet.getRowInfo(this.row);
         return var2 != null && (var2.getRowHeight() == 0 || var2.isCollapsed());
      }
   }

   public void setCellFeatures(CellFeatures var1) {
      if (this.features != null) {
         logger.warn("current cell features not null - overwriting");
      }

      this.features = var1;
   }
}
