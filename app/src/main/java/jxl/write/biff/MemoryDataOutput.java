package jxl.write.biff;

import java.io.IOException;
import java.io.OutputStream;
import jxl.common.Logger;

class MemoryDataOutput implements ExcelDataOutput {
   private static Logger logger = Logger.getLogger(MemoryDataOutput.class);
   private byte[] data;
   private int growSize;
   private int pos;

   public MemoryDataOutput(int var1, int var2) {
      this.data = new byte[var1];
      this.growSize = var2;
      this.pos = 0;
   }

   public void close() throws IOException {
   }

   public int getPosition() {
      return this.pos;
   }

   public void setData(byte[] var1, int var2) {
      System.arraycopy(var1, 0, this.data, var2, var1.length);
   }

   public void write(byte[] var1) {
      while(true) {
         int var3 = this.pos;
         int var2 = var1.length;
         byte[] var4 = this.data;
         if (var2 + var3 <= var4.length) {
            System.arraycopy(var1, 0, var4, var3, var1.length);
            this.pos += var1.length;
            return;
         }

         byte[] var5 = new byte[var4.length + this.growSize];
         System.arraycopy(var4, 0, var5, 0, var3);
         this.data = var5;
      }
   }

   public void writeData(OutputStream var1) throws IOException {
      var1.write(this.data, 0, this.pos);
   }
}
