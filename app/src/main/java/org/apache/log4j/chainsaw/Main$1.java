package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Main$1 extends WindowAdapter {
   private final Main this$0;

   Main$1(Main var1) {
      this.this$0 = var1;
   }

   public void windowClosing(WindowEvent var1) {
      ExitAction.INSTANCE.actionPerformed((ActionEvent)null);
   }
}
