package jxl.read.biff;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jxl.CellType;
import jxl.NumberCell;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class RKRecord extends CellValue implements NumberCell {
   private static DecimalFormat defaultFormat = new DecimalFormat("#.###");
   private static Logger logger = Logger.getLogger(RKRecord.class);
   private NumberFormat format;
   private double value;

   public RKRecord(Record var1, FormattingRecords var2, SheetImpl var3) {
      super(var1, var2, var3);
      byte[] var4 = this.getRecord().getData();
      this.value = RKHelper.getDouble(IntegerHelper.getInt(var4[6], var4[7], var4[8], var4[9]));
      NumberFormat var5 = var2.getNumberFormat(this.getXFIndex());
      this.format = var5;
      if (var5 == null) {
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
