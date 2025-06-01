package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;

public class FooterRecord extends RecordData {
   public static Biff7 biff7 = new Biff7();
   private String footer;

   FooterRecord(Record var1, WorkbookSettings var2) {
      super(var1);
      byte[] var5 = this.getRecord().getData();
      if (var5.length != 0) {
         boolean var3 = false;
         int var4 = IntegerHelper.getInt(var5[0], var5[1]);
         if (var5[2] == 1) {
            var3 = true;
         }

         if (var3) {
            this.footer = StringHelper.getUnicodeString(var5, var4, 3);
         } else {
            this.footer = StringHelper.getString(var5, var4, 3, var2);
         }

      }
   }

   FooterRecord(Record var1, WorkbookSettings var2, Biff7 var3) {
      super(var1);
      byte[] var4 = this.getRecord().getData();
      if (var4.length != 0) {
         this.footer = StringHelper.getString(var4, var4[0], 1, var2);
      }
   }

   String getFooter() {
      return this.footer;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }
}
