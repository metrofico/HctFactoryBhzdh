package org.apache.log4j.lf5.viewer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class LogFactor5InputDialog$1 extends KeyAdapter {
   private final LogFactor5InputDialog this$0;

   LogFactor5InputDialog$1(LogFactor5InputDialog var1) {
      this.this$0 = var1;
   }

   public void keyPressed(KeyEvent var1) {
      if (var1.getKeyCode() == 10) {
         this.this$0.hide();
      }

   }
}
