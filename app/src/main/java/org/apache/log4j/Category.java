package org.apache.log4j;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

public class Category implements AppenderAttachable {
   private static final String FQCN;
   static Class class$org$apache$log4j$Category;
   AppenderAttachableImpl aai;
   protected boolean additive = true;
   protected volatile Level level;
   protected String name;
   protected volatile Category parent;
   protected LoggerRepository repository;
   protected ResourceBundle resourceBundle;

   static {
      Class var1 = class$org$apache$log4j$Category;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.Category");
         class$org$apache$log4j$Category = var0;
      }

      FQCN = var0.getName();
   }

   protected Category(String var1) {
      this.name = var1;
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

   public static Logger exists(String var0) {
      return LogManager.exists(var0);
   }

   public static Enumeration getCurrentCategories() {
      return LogManager.getCurrentLoggers();
   }

   public static LoggerRepository getDefaultHierarchy() {
      return LogManager.getLoggerRepository();
   }

   public static Category getInstance(Class var0) {
      return LogManager.getLogger(var0);
   }

   public static Category getInstance(String var0) {
      return LogManager.getLogger(var0);
   }

   public static final Category getRoot() {
      return LogManager.getRootLogger();
   }

   public static void shutdown() {
      LogManager.shutdown();
   }

   public void addAppender(Appender var1) {
      synchronized(this){}

      try {
         if (this.aai == null) {
            AppenderAttachableImpl var2 = new AppenderAttachableImpl();
            this.aai = var2;
         }

         this.aai.addAppender(var1);
         this.repository.fireAddAppenderEvent(this, var1);
      } finally {
         ;
      }

   }

   public void assertLog(boolean var1, String var2) {
      if (!var1) {
         this.error(var2);
      }

   }

   public void callAppenders(LoggingEvent var1) {
      int var2 = 0;
      Category var4 = this;

      while(var4 != null) {
         synchronized(var4){}

         Throwable var10000;
         label268: {
            AppenderAttachableImpl var5;
            boolean var10001;
            try {
               var5 = var4.aai;
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label268;
            }

            int var3 = var2;
            if (var5 != null) {
               try {
                  var3 = var2 + var5.appendLoopOnAppenders(var1);
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label268;
               }
            }

            label257: {
               try {
                  if (var4.additive) {
                     break label257;
                  }
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label268;
               }

               var2 = var3;
               break;
            }

            try {
               ;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label268;
            }

            var4 = var4.parent;
            var2 = var3;
            continue;
         }

         Throwable var26 = var10000;
         throw var26;
      }

      if (var2 == 0) {
         this.repository.emitNoAppenderWarning(this);
      }

   }

   void closeNestedAppenders() {
      synchronized(this){}

      Throwable var10000;
      label148: {
         Enumeration var1;
         boolean var10001;
         try {
            var1 = this.getAllAppenders();
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label148;
         }

         if (var1 == null) {
            return;
         }

         while(true) {
            try {
               if (!var1.hasMoreElements()) {
                  return;
               }
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break;
            }

            try {
               Appender var2 = (Appender)var1.nextElement();
               if (var2 instanceof AppenderAttachable) {
                  var2.close();
               }
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break;
            }
         }
      }

      Throwable var15 = var10000;
      throw var15;
   }

   public void debug(Object var1) {
      if (!this.repository.isDisabled(10000)) {
         if (Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.DEBUG, var1, (Throwable)null);
         }

      }
   }

   public void debug(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(10000)) {
         if (Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.DEBUG, var1, var2);
         }

      }
   }

   public void error(Object var1) {
      if (!this.repository.isDisabled(40000)) {
         if (Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.ERROR, var1, (Throwable)null);
         }

      }
   }

   public void error(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(40000)) {
         if (Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.ERROR, var1, var2);
         }

      }
   }

   public void fatal(Object var1) {
      if (!this.repository.isDisabled(50000)) {
         if (Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.FATAL, var1, (Throwable)null);
         }

      }
   }

   public void fatal(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(50000)) {
         if (Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.FATAL, var1, var2);
         }

      }
   }

   protected void forcedLog(String var1, Priority var2, Object var3, Throwable var4) {
      this.callAppenders(new LoggingEvent(var1, this, var2, var3, var4));
   }

   public boolean getAdditivity() {
      return this.additive;
   }

   public Enumeration getAllAppenders() {
      synchronized(this){}

      Throwable var10000;
      label113: {
         AppenderAttachableImpl var1;
         boolean var10001;
         try {
            var1 = this.aai;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label113;
         }

         if (var1 == null) {
            label106: {
               NullEnumeration var14;
               try {
                  var14 = NullEnumeration.getInstance();
               } catch (Throwable var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label106;
               }

               return var14;
            }
         } else {
            label109: {
               Enumeration var16;
               try {
                  var16 = var1.getAllAppenders();
               } catch (Throwable var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label109;
               }

               return var16;
            }
         }
      }

      Throwable var15 = var10000;
      throw var15;
   }

   public Appender getAppender(String var1) {
      synchronized(this){}

      Throwable var10000;
      label94: {
         boolean var10001;
         AppenderAttachableImpl var2;
         try {
            var2 = this.aai;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label94;
         }

         if (var2 == null || var1 == null) {
            return null;
         }

         Appender var10;
         try {
            var10 = var2.getAppender(var1);
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label94;
         }

         return var10;
      }

      Throwable var9 = var10000;
      throw var9;
   }

   public Priority getChainedPriority() {
      for(Category var1 = this; var1 != null; var1 = var1.parent) {
         if (var1.level != null) {
            return var1.level;
         }
      }

      return null;
   }

   public Level getEffectiveLevel() {
      for(Category var1 = this; var1 != null; var1 = var1.parent) {
         if (var1.level != null) {
            return var1.level;
         }
      }

      return null;
   }

   public LoggerRepository getHierarchy() {
      return this.repository;
   }

   public final Level getLevel() {
      return this.level;
   }

   public LoggerRepository getLoggerRepository() {
      return this.repository;
   }

   public final String getName() {
      return this.name;
   }

   public final Category getParent() {
      return this.parent;
   }

   public final Level getPriority() {
      return this.level;
   }

   public ResourceBundle getResourceBundle() {
      for(Category var1 = this; var1 != null; var1 = var1.parent) {
         ResourceBundle var2 = var1.resourceBundle;
         if (var2 != null) {
            return var2;
         }
      }

      return null;
   }

   protected String getResourceBundleString(String var1) {
      ResourceBundle var2 = this.getResourceBundle();
      if (var2 == null) {
         return null;
      } else {
         try {
            String var4 = var2.getString(var1);
            return var4;
         } catch (MissingResourceException var3) {
            this.error("No resource is associated with key \"" + var1 + "\".");
            return null;
         }
      }
   }

   public void info(Object var1) {
      if (!this.repository.isDisabled(20000)) {
         if (Level.INFO.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.INFO, var1, (Throwable)null);
         }

      }
   }

   public void info(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(20000)) {
         if (Level.INFO.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.INFO, var1, var2);
         }

      }
   }

   public boolean isAttached(Appender var1) {
      if (var1 != null) {
         AppenderAttachableImpl var2 = this.aai;
         if (var2 != null) {
            return var2.isAttached(var1);
         }
      }

      return false;
   }

   public boolean isDebugEnabled() {
      return this.repository.isDisabled(10000) ? false : Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel());
   }

   public boolean isEnabledFor(Priority var1) {
      return this.repository.isDisabled(var1.level) ? false : var1.isGreaterOrEqual(this.getEffectiveLevel());
   }

   public boolean isInfoEnabled() {
      return this.repository.isDisabled(20000) ? false : Level.INFO.isGreaterOrEqual(this.getEffectiveLevel());
   }

   public void l7dlog(Priority var1, String var2, Throwable var3) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            String var4 = this.getResourceBundleString(var2);
            if (var4 != null) {
               var2 = var4;
            }

            this.forcedLog(FQCN, var1, var2, var3);
         }

      }
   }

   public void l7dlog(Priority var1, String var2, Object[] var3, Throwable var4) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            String var5 = this.getResourceBundleString(var2);
            if (var5 != null) {
               var2 = MessageFormat.format(var5, var3);
            }

            this.forcedLog(FQCN, var1, var2, var4);
         }

      }
   }

   public void log(String var1, Priority var2, Object var3, Throwable var4) {
      if (!this.repository.isDisabled(var2.level)) {
         if (var2.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(var1, var2, var3, var4);
         }

      }
   }

   public void log(Priority var1, Object var2) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, var1, var2, (Throwable)null);
         }

      }
   }

   public void log(Priority var1, Object var2, Throwable var3) {
      if (!this.repository.isDisabled(var1.level)) {
         if (var1.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, var1, var2, var3);
         }

      }
   }

   public void removeAllAppenders() {
      synchronized(this){}

      Throwable var10000;
      label75: {
         AppenderAttachableImpl var1;
         boolean var10001;
         try {
            var1 = this.aai;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label75;
         }

         if (var1 == null) {
            return;
         }

         label66:
         try {
            var1.removeAllAppenders();
            this.aai = null;
            return;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label66;
         }
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public void removeAppender(String var1) {
      synchronized(this){}
      if (var1 != null) {
         Throwable var10000;
         label85: {
            boolean var10001;
            AppenderAttachableImpl var2;
            try {
               var2 = this.aai;
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label85;
            }

            if (var2 == null) {
               return;
            }

            try {
               var2.removeAppender(var1);
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label85;
            }

            return;
         }

         Throwable var9 = var10000;
         throw var9;
      }
   }

   public void removeAppender(Appender var1) {
      synchronized(this){}
      if (var1 != null) {
         Throwable var10000;
         label85: {
            boolean var10001;
            AppenderAttachableImpl var2;
            try {
               var2 = this.aai;
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label85;
            }

            if (var2 == null) {
               return;
            }

            try {
               var2.removeAppender(var1);
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label85;
            }

            return;
         }

         Throwable var9 = var10000;
         throw var9;
      }
   }

   public void setAdditivity(boolean var1) {
      this.additive = var1;
   }

   final void setHierarchy(LoggerRepository var1) {
      this.repository = var1;
   }

   public void setLevel(Level var1) {
      this.level = var1;
   }

   public void setPriority(Priority var1) {
      this.level = (Level)var1;
   }

   public void setResourceBundle(ResourceBundle var1) {
      this.resourceBundle = var1;
   }

   public void warn(Object var1) {
      if (!this.repository.isDisabled(30000)) {
         if (Level.WARN.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.WARN, var1, (Throwable)null);
         }

      }
   }

   public void warn(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(30000)) {
         if (Level.WARN.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.WARN, var1, var2);
         }

      }
   }
}
