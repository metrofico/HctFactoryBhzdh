package jxl.read.biff;

public class BiffRecordReader {
   private File file;
   private Record record;

   public BiffRecordReader(File var1) {
      this.file = var1;
   }

   public int getPos() {
      return this.file.getPos() - this.record.getLength() - 4;
   }

   public boolean hasNext() {
      return this.file.hasNext();
   }

   public Record next() {
      Record var1 = this.file.next();
      this.record = var1;
      return var1;
   }
}
