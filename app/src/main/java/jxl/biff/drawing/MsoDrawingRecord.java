package jxl.biff.drawing;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Logger;
import jxl.read.biff.Record;

public class MsoDrawingRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(MsoDrawingRecord.class);
   private byte[] data;
   private boolean first;

   public MsoDrawingRecord(Record var1) {
      super(var1);
      this.data = this.getRecord().getData();
      this.first = false;
   }

   public MsoDrawingRecord(byte[] var1) {
      super(Type.MSODRAWING);
      this.data = var1;
      this.first = false;
   }

   public byte[] getData() {
      return this.data;
   }

   public Record getRecord() {
      return super.getRecord();
   }

   public boolean isFirst() {
      return this.first;
   }

   public void setFirst() {
      this.first = true;
   }
}
