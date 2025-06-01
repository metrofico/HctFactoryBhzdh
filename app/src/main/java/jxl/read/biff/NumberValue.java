package jxl.read.biff;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jxl.CellFeatures;
import jxl.CellType;
import jxl.NumberCell;
import jxl.biff.FormattingRecords;
import jxl.format.CellFormat;

class NumberValue implements NumberCell, CellFeaturesAccessor {
   private static DecimalFormat defaultFormat = new DecimalFormat("#.###");
   private CellFormat cellFormat;
   private int column;
   private CellFeatures features;
   private NumberFormat format;
   private FormattingRecords formattingRecords;
   private boolean initialized;
   private int row;
   private SheetImpl sheet;
   private double value;
   private int xfIndex;

   public NumberValue(int var1, int var2, double var3, int var5, FormattingRecords var6, SheetImpl var7) {
      this.row = var1;
      this.column = var2;
      this.value = var3;
      this.format = defaultFormat;
      this.xfIndex = var5;
      this.formattingRecords = var6;
      this.sheet = var7;
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
      return this.format.format(this.value);
   }

   public NumberFormat getNumberFormat() {
      return this.format;
   }

   public final int getRow() {
      return this.row;
   }

   public CellType getType() {
      return CellType.NUMBER;
   }

   public double getValue() {
      return this.value;
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
      this.features = var1;
   }

   final void setNumberFormat(NumberFormat var1) {
      if (var1 != null) {
         this.format = var1;
      }

   }
}
