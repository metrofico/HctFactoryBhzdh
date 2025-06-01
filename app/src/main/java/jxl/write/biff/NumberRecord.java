package jxl.write.biff;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jxl.CellType;
import jxl.NumberCell;
import jxl.biff.DoubleHelper;
import jxl.biff.Type;
import jxl.biff.XFRecord;
import jxl.format.CellFormat;

public abstract class NumberRecord extends CellValue {
   private static DecimalFormat defaultFormat = new DecimalFormat("#.###");
   private NumberFormat format;
   private double value;

   protected NumberRecord(int var1, int var2, double var3) {
      super(Type.NUMBER, var1, var2);
      this.value = var3;
   }

   protected NumberRecord(int var1, int var2, double var3, CellFormat var5) {
      super(Type.NUMBER, var1, var2, var5);
      this.value = var3;
   }

   protected NumberRecord(int var1, int var2, NumberRecord var3) {
      super(Type.NUMBER, var1, var2, (CellValue)var3);
      this.value = var3.value;
   }

   protected NumberRecord(NumberCell var1) {
      super(Type.NUMBER, var1);
      this.value = var1.getValue();
   }

   public String getContents() {
      if (this.format == null) {
         NumberFormat var1 = ((XFRecord)this.getCellFormat()).getNumberFormat();
         this.format = var1;
         if (var1 == null) {
            this.format = defaultFormat;
         }
      }

      return this.format.format(this.value);
   }

   public byte[] getData() {
      byte[] var1 = super.getData();
      byte[] var2 = new byte[var1.length + 8];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      DoubleHelper.getIEEEBytes(this.value, var2, var1.length);
      return var2;
   }

   public NumberFormat getNumberFormat() {
      return null;
   }

   public CellType getType() {
      return CellType.NUMBER;
   }

   public double getValue() {
      return this.value;
   }

   public void setValue(double var1) {
      this.value = var1;
   }
}
