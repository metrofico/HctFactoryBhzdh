package org.apache.log4j.lf5.viewer;

import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

class LogBrokerMonitor$4 implements LogRecordFilter {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$4(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public boolean passes(LogRecord var1) {
      String var5 = var1.getNDC();
      CategoryPath var4 = new CategoryPath(var1.getCategory());
      boolean var3 = false;
      boolean var2 = var3;
      if (var5 != null) {
         if (this.this$0._NDCTextFilter == null) {
            var2 = var3;
         } else {
            if (var5.toLowerCase().indexOf(this.this$0._NDCTextFilter.toLowerCase()) == -1) {
               return false;
            }

            var2 = var3;
            if (this.this$0.getMenuItem(var1.getLevel()).isSelected()) {
               var2 = var3;
               if (this.this$0._categoryExplorerTree.getExplorerModel().isCategoryPathActive(var4)) {
                  var2 = true;
               }
            }
         }
      }

      return var2;
   }
}
