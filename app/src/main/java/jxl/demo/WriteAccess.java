package jxl.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.WorkbookSettings;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.read.biff.BiffException;
import jxl.read.biff.BiffRecordReader;
import jxl.read.biff.Record;

class WriteAccess {
   private BiffRecordReader reader;

   public WriteAccess(File var1) throws IOException, BiffException {
      WorkbookSettings var2 = new WorkbookSettings();
      FileInputStream var3 = new FileInputStream(var1);
      this.reader = new BiffRecordReader(new jxl.read.biff.File(var3, var2));
      this.display(var2);
      var3.close();
   }

   private void display(WorkbookSettings var1) throws IOException {
      Record var3 = null;
      boolean var2 = false;

      while(this.reader.hasNext() && !var2) {
         Record var4 = this.reader.next();
         var3 = var4;
         if (var4.getType() == Type.WRITEACCESS) {
            var2 = true;
            var3 = var4;
         }
      }

      if (!var2) {
         System.err.println("Warning:  could not find write access record");
      } else {
         byte[] var6 = var3.getData();
         String var5 = StringHelper.getString(var6, var6.length, 0, var1);
         System.out.println(var5);
      }
   }
}
