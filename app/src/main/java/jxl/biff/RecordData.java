package jxl.biff;

import jxl.read.biff.Record;

public abstract class RecordData {
   private int code;
   private Record record;

   protected RecordData(Type var1) {
      this.code = var1.value;
   }

   protected RecordData(Record var1) {
      this.record = var1;
      this.code = var1.getCode();
   }

   protected final int getCode() {
      return this.code;
   }

   protected Record getRecord() {
      return this.record;
   }
}
