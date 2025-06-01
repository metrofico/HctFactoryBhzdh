package jxl.biff;

import jxl.read.biff.Record;

public class ContinueRecord extends WritableRecordData {
   private byte[] data;

   public ContinueRecord(Record var1) {
      super(var1);
      this.data = var1.getData();
   }

   public ContinueRecord(byte[] var1) {
      super(Type.CONTINUE);
      this.data = var1;
   }

   public byte[] getData() {
      return this.data;
   }

   public Record getRecord() {
      return super.getRecord();
   }
}
