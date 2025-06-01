package jxl.biff.drawing;

import jxl.biff.IntegerHelper;

class Dg extends EscherAtom {
   private byte[] data;
   private int drawingId;
   private int seed;
   private int shapeCount;

   public Dg(int var1) {
      super(EscherRecordType.DG);
      this.drawingId = 1;
      ++var1;
      this.shapeCount = var1;
      this.seed = var1 + 1024 + 1;
      this.setInstance(1);
   }

   public Dg(EscherRecordData var1) {
      super(var1);
      this.drawingId = this.getInstance();
      byte[] var2 = this.getBytes();
      this.shapeCount = IntegerHelper.getInt(var2[0], var2[1], var2[2], var2[3]);
      this.seed = IntegerHelper.getInt(var2[4], var2[5], var2[6], var2[7]);
   }

   byte[] getData() {
      byte[] var1 = new byte[8];
      this.data = var1;
      IntegerHelper.getFourBytes(this.shapeCount, var1, 0);
      IntegerHelper.getFourBytes(this.seed, this.data, 4);
      return this.setHeaderData(this.data);
   }

   public int getDrawingId() {
      return this.drawingId;
   }

   int getShapeCount() {
      return this.shapeCount;
   }
}
