package jxl.biff.drawing;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Logger;
import jxl.read.biff.Record;

public class TextObjectRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(TextObjectRecord.class);
   private byte[] data;
   private int textLength;

   TextObjectRecord(String var1) {
      super(Type.TXO);
      this.textLength = var1.length();
   }

   public TextObjectRecord(Record var1) {
      super(var1);
      byte[] var2 = this.getRecord().getData();
      this.data = var2;
      this.textLength = IntegerHelper.getInt(var2[10], var2[11]);
   }

   public TextObjectRecord(byte[] var1) {
      super(Type.TXO);
      this.data = var1;
   }

   public byte[] getData() {
      byte[] var1 = this.data;
      if (var1 != null) {
         return var1;
      } else {
         var1 = new byte[18];
         this.data = var1;
         IntegerHelper.getTwoBytes(530, var1, 0);
         IntegerHelper.getTwoBytes(this.textLength, this.data, 10);
         IntegerHelper.getTwoBytes(16, this.data, 12);
         return this.data;
      }
   }

   public int getTextLength() {
      return this.textLength;
   }
}
