package jxl.biff.drawing;

import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class ClientAnchor extends EscherAtom {
   private static final Logger logger = Logger.getLogger(ClientAnchor.class);
   private byte[] data;
   private int properties;
   private double x1;
   private double x2;
   private double y1;
   private double y2;

   public ClientAnchor(double var1, double var3, double var5, double var7, int var9) {
      super(EscherRecordType.CLIENT_ANCHOR);
      this.x1 = var1;
      this.y1 = var3;
      this.x2 = var5;
      this.y2 = var7;
      this.properties = var9;
   }

   public ClientAnchor(EscherRecordData var1) {
      super(var1);
      byte[] var4 = this.getBytes();
      this.properties = IntegerHelper.getInt(var4[0], var4[1]);
      int var3 = IntegerHelper.getInt(var4[2], var4[3]);
      int var2 = IntegerHelper.getInt(var4[4], var4[5]);
      this.x1 = (double)var3 + (double)var2 / 1024.0;
      var2 = IntegerHelper.getInt(var4[6], var4[7]);
      var3 = IntegerHelper.getInt(var4[8], var4[9]);
      this.y1 = (double)var2 + (double)var3 / 256.0;
      var2 = IntegerHelper.getInt(var4[10], var4[11]);
      var3 = IntegerHelper.getInt(var4[12], var4[13]);
      this.x2 = (double)var2 + (double)var3 / 1024.0;
      var2 = IntegerHelper.getInt(var4[14], var4[15]);
      var3 = IntegerHelper.getInt(var4[16], var4[17]);
      this.y2 = (double)var2 + (double)var3 / 256.0;
   }

   byte[] getData() {
      byte[] var3 = new byte[18];
      this.data = var3;
      IntegerHelper.getTwoBytes(this.properties, var3, 0);
      IntegerHelper.getTwoBytes((int)this.x1, this.data, 2);
      double var1 = this.x1;
      IntegerHelper.getTwoBytes((int)((var1 - (double)((int)var1)) * 1024.0), this.data, 4);
      IntegerHelper.getTwoBytes((int)this.y1, this.data, 6);
      var1 = this.y1;
      IntegerHelper.getTwoBytes((int)((var1 - (double)((int)var1)) * 256.0), this.data, 8);
      IntegerHelper.getTwoBytes((int)this.x2, this.data, 10);
      var1 = this.x2;
      IntegerHelper.getTwoBytes((int)((var1 - (double)((int)var1)) * 1024.0), this.data, 12);
      IntegerHelper.getTwoBytes((int)this.y2, this.data, 14);
      var1 = this.y2;
      IntegerHelper.getTwoBytes((int)((var1 - (double)((int)var1)) * 256.0), this.data, 16);
      return this.setHeaderData(this.data);
   }

   int getProperties() {
      return this.properties;
   }

   double getX1() {
      return this.x1;
   }

   double getX2() {
      return this.x2;
   }

   double getY1() {
      return this.y1;
   }

   double getY2() {
      return this.y2;
   }
}
