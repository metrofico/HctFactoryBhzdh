package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public class DataValidityListRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(DataValidityListRecord.class);
   private byte[] data;
   private DValParser dvalParser;
   private int numSettings;
   private int objectId;

   public DataValidityListRecord(DValParser var1) {
      super(Type.DVAL);
      this.dvalParser = var1;
   }

   DataValidityListRecord(DataValidityListRecord var1) {
      super(Type.DVAL);
      this.data = var1.getData();
   }

   public DataValidityListRecord(Record var1) {
      super(var1);
      byte[] var2 = this.getRecord().getData();
      this.data = var2;
      this.objectId = IntegerHelper.getInt(var2[10], var2[11], var2[12], var2[13]);
      var2 = this.data;
      this.numSettings = IntegerHelper.getInt(var2[14], var2[15], var2[16], var2[17]);
   }

   void dvAdded() {
      if (this.dvalParser == null) {
         this.dvalParser = new DValParser(this.data);
      }

      this.dvalParser.dvAdded();
   }

   void dvRemoved() {
      if (this.dvalParser == null) {
         this.dvalParser = new DValParser(this.data);
      }

      this.dvalParser.dvRemoved();
   }

   public byte[] getData() {
      DValParser var1 = this.dvalParser;
      return var1 == null ? this.data : var1.getData();
   }

   int getNumberOfSettings() {
      return this.numSettings;
   }

   public int getObjectId() {
      DValParser var1 = this.dvalParser;
      return var1 == null ? this.objectId : var1.getObjectId();
   }

   public boolean hasDVRecords() {
      DValParser var2 = this.dvalParser;
      boolean var1 = true;
      if (var2 == null) {
         return true;
      } else {
         if (var2.getNumberOfDVRecords() <= 0) {
            var1 = false;
         }

         return var1;
      }
   }
}
