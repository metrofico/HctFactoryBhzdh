package jxl.write.biff;

import java.io.IOException;
import java.io.OutputStream;
import jxl.WorkbookSettings;
import jxl.biff.ByteData;
import jxl.common.Logger;

public final class File {
   private static Logger logger = Logger.getLogger(File.class);
   private int arrayGrowSize;
   private ExcelDataOutput data;
   private int initialFileSize;
   private OutputStream outputStream;
   private int pos;
   jxl.read.biff.CompoundFile readCompoundFile;
   private WorkbookSettings workbookSettings;

   File(OutputStream var1, WorkbookSettings var2, jxl.read.biff.CompoundFile var3) throws IOException {
      this.outputStream = var1;
      this.workbookSettings = var2;
      this.readCompoundFile = var3;
      this.createDataOutput();
   }

   private void createDataOutput() throws IOException {
      if (this.workbookSettings.getUseTemporaryFileDuringWrite()) {
         this.data = new FileDataOutput(this.workbookSettings.getTemporaryFileDuringWriteDirectory());
      } else {
         this.initialFileSize = this.workbookSettings.getInitialFileSize();
         this.arrayGrowSize = this.workbookSettings.getArrayGrowSize();
         this.data = new MemoryDataOutput(this.initialFileSize, this.arrayGrowSize);
      }

   }

   void close(boolean var1) throws IOException, JxlWriteException {
      ExcelDataOutput var2 = this.data;
      (new CompoundFile(var2, var2.getPosition(), this.outputStream, this.readCompoundFile)).write();
      this.outputStream.flush();
      this.data.close();
      if (var1) {
         this.outputStream.close();
      }

      this.data = null;
      if (!this.workbookSettings.getGCDisabled()) {
         System.gc();
      }

   }

   int getPos() throws IOException {
      return this.data.getPosition();
   }

   void setData(byte[] var1, int var2) throws IOException {
      this.data.setData(var1, var2);
   }

   public void setOutputFile(OutputStream var1) throws IOException {
      if (this.data != null) {
         logger.warn("Rewriting a workbook with non-empty data");
      }

      this.outputStream = var1;
      this.createDataOutput();
   }

   public void write(ByteData var1) throws IOException {
      byte[] var2 = var1.getBytes();
      this.data.write(var2);
   }
}
