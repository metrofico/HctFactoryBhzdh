package jxl.write.biff;

import java.util.List;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.write.Number;

class MulRKRecord extends WritableRecordData {
   private int colFirst;
   private int colLast;
   private int[] rknumbers;
   private int row;
   private int[] xfIndices;

   public MulRKRecord(List var1) {
      super(Type.MULRK);
      int var2 = 0;
      this.row = ((Number)var1.get(0)).getRow();
      int var3 = ((Number)var1.get(0)).getColumn();
      this.colFirst = var3;
      this.colLast = var3 + var1.size() - 1;
      this.rknumbers = new int[var1.size()];

      for(this.xfIndices = new int[var1.size()]; var2 < var1.size(); ++var2) {
         this.rknumbers[var2] = (int)((Number)var1.get(var2)).getValue();
         this.xfIndices[var2] = ((CellValue)var1.get(var2)).getXFIndex();
      }

   }

   public byte[] getData() {
      byte[] var3 = new byte[this.rknumbers.length * 6 + 6];
      int var2 = this.row;
      int var1 = 0;
      IntegerHelper.getTwoBytes(var2, var3, 0);
      IntegerHelper.getTwoBytes(this.colFirst, var3, 2);

      for(var2 = 4; var1 < this.rknumbers.length; ++var1) {
         IntegerHelper.getTwoBytes(this.xfIndices[var1], var3, var2);
         IntegerHelper.getFourBytes(this.rknumbers[var1] << 2 | 2, var3, var2 + 2);
         var2 += 6;
      }

      IntegerHelper.getTwoBytes(this.colLast, var3, var2);
      return var3;
   }
}
