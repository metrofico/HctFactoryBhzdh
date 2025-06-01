package jxl.read.biff;

import java.util.ArrayList;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.common.Logger;

public final class Record {
   private static final Logger logger = Logger.getLogger(Record.class);
   private int code;
   private ArrayList continueRecords;
   private byte[] data;
   private int dataPos;
   private File file;
   private int length;
   private Type type;

   Record(byte[] var1, int var2, File var3) {
      this.code = IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
      this.length = IntegerHelper.getInt(var1[var2 + 2], var1[var2 + 3]);
      this.file = var3;
      var3.skip(4);
      this.dataPos = var3.getPos();
      this.file.skip(this.length);
      this.type = Type.getType(this.code);
   }

   public void addContinueRecord(Record var1) {
      if (this.continueRecords == null) {
         this.continueRecords = new ArrayList();
      }

      this.continueRecords.add(var1);
   }

   public int getCode() {
      return this.code;
   }

   public byte[] getData() {
      if (this.data == null) {
         this.data = this.file.read(this.dataPos, this.length);
      }

      ArrayList var4 = this.continueRecords;
      if (var4 != null) {
         int var3 = var4.size();
         byte[][] var7 = new byte[var3][];
         int var2 = 0;

         int var1;
         byte[] var5;
         for(var1 = 0; var2 < this.continueRecords.size(); ++var2) {
            var5 = ((Record)this.continueRecords.get(var2)).getData();
            var7[var2] = var5;
            var1 += var5.length;
         }

         byte[] var6 = this.data;
         var5 = new byte[var6.length + var1];
         System.arraycopy(var6, 0, var5, 0, var6.length);
         var2 = this.data.length;

         for(var1 = 0; var1 < var3; ++var1) {
            var6 = var7[var1];
            System.arraycopy(var6, 0, var5, var2, var6.length);
            var2 += var6.length;
         }

         this.data = var5;
      }

      return this.data;
   }

   public int getLength() {
      return this.length;
   }

   public Type getType() {
      return this.type;
   }

   void setType(Type var1) {
      this.type = var1;
   }
}
