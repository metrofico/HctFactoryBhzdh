package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LogFactor5InputDialog$3 implements ActionListener {
   private final LogFactor5InputDialog this$0;

   LogFactor5InputDialog$3(LogFactor5InputDialog var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.hide();
      LogFactor5InputDialog.access$000(this.this$0).setText("");
   }
}
