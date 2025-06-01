package jxl.write.biff;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import jxl.common.Logger;

class FileDataOutput implements ExcelDataOutput {
   private static Logger logger = Logger.getLogger(FileDataOutput.class);
   private RandomAccessFile data;
   private java.io.File temporaryFile;

   public FileDataOutput(java.io.File var1) throws IOException {
      var1 = java.io.File.createTempFile("jxl", ".tmp", var1);
      this.temporaryFile = var1;
      var1.deleteOnExit();
      this.data = new RandomAccessFile(this.temporaryFile, "rw");
   }

   public void close() throws IOException {
      this.data.close();
      this.temporaryFile.delete();
   }

   public int getPosition() throws IOException {
      return (int)this.data.getFilePointer();
   }

   public void setData(byte[] var1, int var2) throws IOException {
      long var3 = this.data.getFilePointer();
      this.data.seek((long)var2);
      this.data.write(var1);
      this.data.seek(var3);
   }

   public void write(byte[] var1) throws IOException {
      this.data.write(var1);
   }

   public void writeData(OutputStream var1) throws IOException {
      byte[] var3 = new byte[1024];
      this.data.seek(0L);

      while(true) {
         int var2 = this.data.read(var3);
         if (var2 == -1) {
            return;
         }

         var1.write(var3, 0, var2);
      }
   }
}
