package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

class ExitAction extends AbstractAction {
   public static final ExitAction INSTANCE;
   private static final Logger LOG;
   static Class class$org$apache$log4j$chainsaw$ExitAction;

   static {
      Class var1 = class$org$apache$log4j$chainsaw$ExitAction;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.chainsaw.ExitAction");
         class$org$apache$log4j$chainsaw$ExitAction = var0;
      }

      LOG = Logger.getLogger(var0);
      INSTANCE = new ExitAction();
   }

   private ExitAction() {
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public void actionPerformed(ActionEvent var1) {
      LOG.info("shutting down");
      System.exit(0);
   }
}
