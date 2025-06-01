package jxl.biff.drawing;

import java.io.IOException;
import jxl.biff.IntegerHelper;
import jxl.common.Assert;
import jxl.common.Logger;

class BlipStoreEntry extends EscherAtom {
   private static final int IMAGE_DATA_OFFSET = 61;
   private static Logger logger = Logger.getLogger(BlipStoreEntry.class);
   private byte[] data;
   private int imageDataLength;
   private int referenceCount;
   private BlipType type;
   private boolean write;

   public BlipStoreEntry(Drawing var1) throws IOException {
      super(EscherRecordType.BSE);
      this.type = BlipType.PNG;
      this.setVersion(2);
      this.setInstance(this.type.getValue());
      byte[] var3 = var1.getImageBytes();
      int var2 = var3.length;
      this.imageDataLength = var2;
      byte[] var4 = new byte[var2 + 61];
      this.data = var4;
      System.arraycopy(var3, 0, var4, 61, var2);
      this.referenceCount = var1.getReferenceCount();
      this.write = true;
   }

   public BlipStoreEntry(EscherRecordData var1) {
      super(var1);
      this.type = BlipType.getType(this.getInstance());
      this.write = false;
      byte[] var2 = this.getBytes();
      this.referenceCount = IntegerHelper.getInt(var2[24], var2[25], var2[26], var2[27]);
   }

   void dereference() {
      int var1 = this.referenceCount;
      boolean var2 = true;
      --var1;
      this.referenceCount = var1;
      if (var1 < 0) {
         var2 = false;
      }

      Assert.verify(var2);
   }

   public BlipType getBlipType() {
      return this.type;
   }

   public byte[] getData() {
      if (this.write) {
         this.data[0] = (byte)this.type.getValue();
         this.data[1] = (byte)this.type.getValue();
         IntegerHelper.getFourBytes(this.imageDataLength + 8 + 17, this.data, 20);
         IntegerHelper.getFourBytes(this.referenceCount, this.data, 24);
         IntegerHelper.getFourBytes(0, this.data, 28);
         byte[] var1 = this.data;
         var1[32] = 0;
         var1[33] = 0;
         var1[34] = 126;
         var1[35] = 1;
         var1[36] = 0;
         var1[37] = 110;
         IntegerHelper.getTwoBytes(61470, var1, 38);
         IntegerHelper.getFourBytes(this.imageDataLength + 17, this.data, 40);
      } else {
         this.data = this.getBytes();
      }

      return this.setHeaderData(this.data);
   }

   byte[] getImageData() {
      byte[] var2 = this.getBytes();
      int var1 = var2.length - 61;
      byte[] var3 = new byte[var1];
      System.arraycopy(var2, 61, var3, 0, var1);
      return var3;
   }

   int getReferenceCount() {
      return this.referenceCount;
   }
}
