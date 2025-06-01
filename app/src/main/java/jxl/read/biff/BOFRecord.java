package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

public class BOFRecord extends RecordData {
   private static final int Biff7 = 1280;
   private static final int Biff8 = 1536;
   private static final int Chart = 32;
   private static final int MacroSheet = 64;
   private static final int WorkbookGlobals = 5;
   private static final int Worksheet = 16;
   private static Logger logger = Logger.getLogger(BOFRecord.class);
   private int substreamType;
   private int version;

   BOFRecord(Record var1) {
      super(var1);
      byte[] var2 = this.getRecord().getData();
      this.version = IntegerHelper.getInt(var2[0], var2[1]);
      this.substreamType = IntegerHelper.getInt(var2[2], var2[3]);
   }

   int getLength() {
      return this.getRecord().getLength();
   }

   public boolean isBiff7() {
      boolean var1;
      if (this.version == 1280) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isBiff8() {
      boolean var1;
      if (this.version == 1536) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isChart() {
      boolean var1;
      if (this.substreamType == 32) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isMacroSheet() {
      boolean var1;
      if (this.substreamType == 64) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   boolean isWorkbookGlobals() {
      boolean var1;
      if (this.substreamType == 5) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isWorksheet() {
      boolean var1;
      if (this.substreamType == 16) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }
}
