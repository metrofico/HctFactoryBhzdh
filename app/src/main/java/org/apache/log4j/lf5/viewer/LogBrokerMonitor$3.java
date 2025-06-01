package org.apache.log4j.lf5.viewer;

import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

class LogBrokerMonitor$3 implements LogRecordFilter {
   private final LogBrokerMonitor this$0;

   LogBrokerMonitor$3(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public boolean passes(LogRecord var1) {
      CategoryPath var3 = new CategoryPath(var1.getCategory());
      boolean var2;
      if (this.this$0.getMenuItem(var1.getLevel()).isSelected() && this.this$0._categoryExplorerTree.getExplorerModel().isCategoryPathActive(var3)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }
}
