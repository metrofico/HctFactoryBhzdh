package jxl.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.write.biff.File;

public class ConditionalFormat {
   private ArrayList conditions;
   private ConditionalFormatRangeRecord range;

   public ConditionalFormat(ConditionalFormatRangeRecord var1) {
      this.range = var1;
      this.conditions = new ArrayList();
   }

   public void addCondition(ConditionalFormatRecord var1) {
      this.conditions.add(var1);
   }

   public void insertColumn(int var1) {
      this.range.insertColumn(var1);
   }

   public void insertRow(int var1) {
      this.range.insertRow(var1);
   }

   public void removeColumn(int var1) {
      this.range.removeColumn(var1);
   }

   public void removeRow(int var1) {
      this.range.removeRow(var1);
   }

   public void write(File var1) throws IOException {
      var1.write(this.range);
      Iterator var2 = this.conditions.iterator();

      while(var2.hasNext()) {
         var1.write((ConditionalFormatRecord)var2.next());
      }

   }
}
