package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

public class Logger extends Category {
   private static final String FQCN;
   static Class class$org$apache$log4j$Logger;

   static {
      Class var1 = class$org$apache$log4j$Logger;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.Logger");
         class$org$apache$log4j$Logger = var0;
      }

      FQCN = var0.getName();
   }

   protected Logger(String var1) {
      super(var1);
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

   public static Logger getLogger(Class var0) {
      return LogManager.getLogger(var0.getName());
   }

   public static Logger getLogger(String var0) {
      return LogManager.getLogger(var0);
   }

   public static Logger getLogger(String var0, LoggerFactory var1) {
      return LogManager.getLogger(var0, var1);
   }

   public static Logger getRootLogger() {
      return LogManager.getRootLogger();
   }

   public boolean isTraceEnabled() {
      return super.repository.isDisabled(5000) ? false : Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel());
   }

   public void trace(Object var1) {
      if (!super.repository.isDisabled(5000)) {
         if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.TRACE, var1, (Throwable)null);
         }

      }
   }

   public void trace(Object var1, Throwable var2) {
      if (!super.repository.isDisabled(5000)) {
         if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.TRACE, var1, var2);
         }

      }
   }
}
