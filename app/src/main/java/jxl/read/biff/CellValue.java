package jxl.read.biff;

import jxl.Cell;
import jxl.CellFeatures;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.XFRecord;
import jxl.common.Logger;
import jxl.format.CellFormat;

public abstract class CellValue extends RecordData implements Cell, CellFeaturesAccessor {
   private static Logger logger = Logger.getLogger(CellValue.class);
   private int column;
   private CellFeatures features;
   private XFRecord format;
   private FormattingRecords formattingRecords;
   private boolean initialized;
   private int row;
   private SheetImpl sheet;
   private int xfIndex;

   protected CellValue(Record var1, FormattingRecords var2, SheetImpl var3) {
      super(var1);
      byte[] var4 = this.getRecord().getData();
      this.row = IntegerHelper.getInt(var4[0], var4[1]);
      this.column = IntegerHelper.getInt(var4[2], var4[3]);
      this.xfIndex = IntegerHelper.getInt(var4[4], var4[5]);
      this.sheet = var3;
      this.formattingRecords = var2;
      this.initialized = false;
   }

   public CellFeatures getCellFeatures() {
      return this.features;
   }

   public CellFormat getCellFormat() {
      if (!this.initialized) {
         this.format = this.formattingRecords.getXFRecord(this.xfIndex);
         this.initialized = true;
      }

      return this.format;
   }

   public final int getColumn() {
      return this.column;
   }

   public final int getRow() {
      return this.row;
   }

   protected SheetImpl getSheet() {
      return this.sheet;
   }

   public final int getXFIndex() {
      return this.xfIndex;
   }

   public boolean isHidden() {
      ColumnInfoRecord var1 = this.sheet.getColumnInfo(this.column);
      if (var1 == null || var1.getWidth() != 0 && !var1.getHidden()) {
         RowRecord var2 = this.sheet.getRowInfo(this.row);
         return var2 != null && (var2.getRowHeight() == 0 || var2.isCollapsed());
      } else {
         return true;
      }
   }

   public void setCellFeatures(CellFeatures var1) {
      if (this.features != null) {
         logger.warn("current cell features not null - overwriting");
      }

      this.features = var1;
   }
}
