package jxl.read.biff;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jxl.CellType;
import jxl.NumberCell;
import jxl.biff.DoubleHelper;
import jxl.biff.FormattingRecords;
import jxl.common.Logger;

class NumberRecord extends CellValue implements NumberCell {
   private static DecimalFormat defaultFormat = new DecimalFormat("#.###");
   private static Logger logger = Logger.getLogger(NumberRecord.class);
   private NumberFormat format;
   private double value = DoubleHelper.getIEEEDouble(this.getRecord().getData(), 6);

   public NumberRecord(Record var1, FormattingRecords var2, SheetImpl var3) {
      super(var1, var2, var3);
      NumberFormat var4 = var2.getNumberFormat(this.getXFIndex());
      this.format = var4;
      if (var4 == null) {
         this.format = defaultFormat;
      }

   }

   public String getContents() {
      return this.format.format(this.value);
   }

   public NumberFormat getNumberFormat() {
      return this.format;
   }

   public CellType getType() {
      return CellType.NUMBER;
   }

   public double getValue() {
      return this.value;
   }
}
