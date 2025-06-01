package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

public class ExternalSheetRecord extends RecordData {
   public static Biff7 biff7 = new Biff7();
   private static Logger logger = Logger.getLogger(ExternalSheetRecord.class);
   private XTI[] xtiArray;

   ExternalSheetRecord(Record var1, WorkbookSettings var2) {
      super(var1);
      byte[] var9 = this.getRecord().getData();
      int var3 = 0;
      int var5 = IntegerHelper.getInt(var9[0], var9[1]);
      int var6 = var9.length;
      int var4 = 2;
      if (var6 < var5 * 6 + 2) {
         this.xtiArray = new XTI[0];
         logger.warn("Could not process external sheets.  Formulas may be compromised.");
      } else {
         for(this.xtiArray = new XTI[var5]; var3 < var5; ++var3) {
            var6 = IntegerHelper.getInt(var9[var4], var9[var4 + 1]);
            int var7 = IntegerHelper.getInt(var9[var4 + 2], var9[var4 + 3]);
            int var8 = IntegerHelper.getInt(var9[var4 + 4], var9[var4 + 5]);
            this.xtiArray[var3] = new XTI(var6, var7, var8);
            var4 += 6;
         }

      }
   }

   ExternalSheetRecord(Record var1, WorkbookSettings var2, Biff7 var3) {
      super(var1);
      logger.warn("External sheet record for Biff 7 not supported");
   }

   public byte[] getData() {
      return this.getRecord().getData();
   }

   public int getFirstTabIndex(int var1) {
      return this.xtiArray[var1].firstTab;
   }

   public int getLastTabIndex(int var1) {
      return this.xtiArray[var1].lastTab;
   }

   public int getNumRecords() {
      XTI[] var2 = this.xtiArray;
      int var1;
      if (var2 != null) {
         var1 = var2.length;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int getSupbookIndex(int var1) {
      return this.xtiArray[var1].supbookIndex;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }

   private static class XTI {
      int firstTab;
      int lastTab;
      int supbookIndex;

      XTI(int var1, int var2, int var3) {
         this.supbookIndex = var1;
         this.firstTab = var2;
         this.lastTab = var3;
      }
   }
}
