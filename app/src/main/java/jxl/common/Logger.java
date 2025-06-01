package jxl.common;

public abstract class Logger {
   private static Logger logger;

   protected Logger() {
   }

   public static final Logger getLogger(Class var0) {
      if (logger == null) {
         initializeLogger();
      }

      return logger.getLoggerImpl(var0);
   }

   private static void initializeLogger() {
      // $FF: Couldn't be decompiled
   }

   public abstract void debug(Object var1);

   public abstract void debug(Object var1, Throwable var2);

   public abstract void error(Object var1);

   public abstract void error(Object var1, Throwable var2);

   public abstract void fatal(Object var1);

   public abstract void fatal(Object var1, Throwable var2);

   protected abstract Logger getLoggerImpl(Class var1);

   public abstract void info(Object var1);

   public abstract void info(Object var1, Throwable var2);

   public void setSuppressWarnings(boolean var1) {
   }

   public abstract void warn(Object var1);

   public abstract void warn(Object var1, Throwable var2);
}
