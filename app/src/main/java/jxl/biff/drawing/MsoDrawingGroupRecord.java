package jxl.biff.drawing;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.read.biff.Record;

public class MsoDrawingGroupRecord extends WritableRecordData {
   private byte[] data;

   public MsoDrawingGroupRecord(Record var1) {
      super(var1);
      this.data = var1.getData();
   }

   MsoDrawingGroupRecord(byte[] var1) {
      super(Type.MSODRAWINGGROUP);
      this.data = var1;
   }

   public byte[] getData() {
      return this.data;
   }
}
