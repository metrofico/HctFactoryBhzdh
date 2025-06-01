package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;
import jxl.common.Logger;

public class ExternalNameRecord extends RecordData {
   private static Logger logger = Logger.getLogger(ExternalNameRecord.class);
   private boolean addInFunction;
   private String name;

   ExternalNameRecord(Record var1, WorkbookSettings var2) {
      super(var1);
      byte[] var5 = this.getRecord().getData();
      boolean var3 = false;
      if (IntegerHelper.getInt(var5[0], var5[1]) == 0) {
         this.addInFunction = true;
      }

      if (this.addInFunction) {
         byte var4 = var5[6];
         if (var5[7] != 0) {
            var3 = true;
         }

         if (var3) {
            this.name = StringHelper.getUnicodeString(var5, var4, 8);
         } else {
            this.name = StringHelper.getString(var5, var4, 8, var2);
         }

      }
   }

   public String getName() {
      return this.name;
   }

   public boolean isAddInFunction() {
      return this.addInFunction;
   }
}
