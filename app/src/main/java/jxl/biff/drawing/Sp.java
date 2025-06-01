package jxl.biff.drawing;

import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class Sp extends EscherAtom {
   private static Logger logger = Logger.getLogger(Sp.class);
   private byte[] data;
   private int persistenceFlags;
   private int shapeId;
   private int shapeType;

   public Sp(EscherRecordData var1) {
      super(var1);
      this.shapeType = this.getInstance();
      byte[] var2 = this.getBytes();
      this.shapeId = IntegerHelper.getInt(var2[0], var2[1], var2[2], var2[3]);
      this.persistenceFlags = IntegerHelper.getInt(var2[4], var2[5], var2[6], var2[7]);
   }

   public Sp(ShapeType var1, int var2, int var3) {
      super(EscherRecordType.SP);
      this.setVersion(2);
      int var4 = var1.getValue();
      this.shapeType = var4;
      this.shapeId = var2;
      this.persistenceFlags = var3;
      this.setInstance(var4);
   }

   byte[] getData() {
      byte[] var1 = new byte[8];
      this.data = var1;
      IntegerHelper.getFourBytes(this.shapeId, var1, 0);
      IntegerHelper.getFourBytes(this.persistenceFlags, this.data, 4);
      return this.setHeaderData(this.data);
   }

   int getShapeId() {
      return this.shapeId;
   }

   int getShapeType() {
      return this.shapeType;
   }
}
