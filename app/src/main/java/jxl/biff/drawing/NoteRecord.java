package jxl.biff.drawing;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Logger;
import jxl.read.biff.Record;

public class NoteRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(NoteRecord.class);
   private int column;
   private byte[] data;
   private int objectId;
   private int row;

   public NoteRecord(int var1, int var2, int var3) {
      super(Type.NOTE);
      this.row = var2;
      this.column = var1;
      this.objectId = var3;
   }

   public NoteRecord(Record var1) {
      super(var1);
      byte[] var2 = this.getRecord().getData();
      this.data = var2;
      this.row = IntegerHelper.getInt(var2[0], var2[1]);
      var2 = this.data;
      this.column = IntegerHelper.getInt(var2[2], var2[3]);
      var2 = this.data;
      this.objectId = IntegerHelper.getInt(var2[6], var2[7]);
   }

   public NoteRecord(byte[] var1) {
      super(Type.NOTE);
      this.data = var1;
   }

   int getColumn() {
      return this.column;
   }

   public byte[] getData() {
      byte[] var1 = this.data;
      if (var1 != null) {
         return var1;
      } else {
         var1 = new byte[12];
         this.data = var1;
         IntegerHelper.getTwoBytes(this.row, var1, 0);
         IntegerHelper.getTwoBytes(this.column, this.data, 2);
         IntegerHelper.getTwoBytes(this.objectId, this.data, 6);
         IntegerHelper.getTwoBytes(0, this.data, 8);
         return this.data;
      }
   }

   public int getObjectId() {
      return this.objectId;
   }

   int getRow() {
      return this.row;
   }
}
