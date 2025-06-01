package jxl.write.biff;

import java.util.ArrayList;
import java.util.Iterator;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class DBCellRecord extends WritableRecordData {
   private int cellOffset;
   private ArrayList cellRowPositions;
   private int position;
   private int rowPos;

   public DBCellRecord(int var1) {
      super(Type.DBCELL);
      this.rowPos = var1;
      this.cellRowPositions = new ArrayList(10);
   }

   void addCellRowPosition(int var1) {
      this.cellRowPositions.add(new Integer(var1));
   }

   protected byte[] getData() {
      int var2 = this.cellRowPositions.size();
      int var1 = 4;
      byte[] var4 = new byte[var2 * 2 + 4];
      IntegerHelper.getFourBytes(this.position - this.rowPos, var4, 0);
      var2 = this.cellOffset;

      int var3;
      for(Iterator var5 = this.cellRowPositions.iterator(); var5.hasNext(); var2 = var3) {
         var3 = (Integer)var5.next();
         IntegerHelper.getTwoBytes(var3 - var2, var4, var1);
         var1 += 2;
      }

      return var4;
   }

   void setCellOffset(int var1) {
      this.cellOffset = var1;
   }

   void setPosition(int var1) {
      this.position = var1;
   }
}
