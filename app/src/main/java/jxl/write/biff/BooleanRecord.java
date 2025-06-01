package jxl.write.biff;

import jxl.BooleanCell;
import jxl.CellType;
import jxl.biff.Type;
import jxl.format.CellFormat;

public abstract class BooleanRecord extends CellValue {
   private boolean value;

   protected BooleanRecord(int var1, int var2, BooleanRecord var3) {
      super(Type.BOOLERR, var1, var2, (CellValue)var3);
      this.value = var3.value;
   }

   protected BooleanRecord(int var1, int var2, boolean var3) {
      super(Type.BOOLERR, var1, var2);
      this.value = var3;
   }

   protected BooleanRecord(int var1, int var2, boolean var3, CellFormat var4) {
      super(Type.BOOLERR, var1, var2, var4);
      this.value = var3;
   }

   protected BooleanRecord(BooleanCell var1) {
      super(Type.BOOLERR, var1);
      this.value = var1.getValue();
   }

   public String getContents() {
      return (new Boolean(this.value)).toString();
   }

   public byte[] getData() {
      byte[] var1 = super.getData();
      byte[] var2 = new byte[var1.length + 2];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      if (this.value) {
         var2[var1.length] = 1;
      }

      return var2;
   }

   public CellType getType() {
      return CellType.BOOLEAN;
   }

   public boolean getValue() {
      return this.value;
   }

   protected void setValue(boolean var1) {
      this.value = var1;
   }
}
