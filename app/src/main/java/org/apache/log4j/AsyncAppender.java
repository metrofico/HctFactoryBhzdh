package org.apache.log4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AsyncAppender extends AppenderSkeleton implements AppenderAttachable {
   public static final int DEFAULT_BUFFER_SIZE = 128;
   AppenderAttachableImpl aai;
   private final AppenderAttachableImpl appenders;
   private boolean blocking;
   private final List buffer;
   private int bufferSize;
   private final Map discardMap;
   private final Thread dispatcher;
   private boolean locationInfo;

   public AsyncAppender() {
      ArrayList var3 = new ArrayList();
      this.buffer = var3;
      HashMap var2 = new HashMap();
      this.discardMap = var2;
      this.bufferSize = 128;
      this.locationInfo = false;
      this.blocking = true;
      AppenderAttachableImpl var1 = new AppenderAttachableImpl();
      this.appenders = var1;
      this.aai = var1;
      Thread var4 = new Thread(new Dispatcher(this, var3, var2, var1));
      this.dispatcher = var4;
      var4.setDaemon(true);
      var4.setName("Dispatcher-" + var4.getName());
      var4.start();
   }

   public void addAppender(Appender var1) {
      AppenderAttachableImpl var2 = this.appenders;
      synchronized(var2) {
         this.appenders.addAppender(var1);
      }
   }

   public void append(LoggingEvent param1) {
      // $FF: Couldn't be decompiled
   }

   public void close() {
      List var2 = this.buffer;
      synchronized(var2) {
         super.closed = true;
         this.buffer.notifyAll();
      }

      try {
         this.dispatcher.join();
      } catch (InterruptedException var28) {
         Thread.currentThread().interrupt();
         LogLog.error("Got an InterruptedException while waiting for the dispatcher to finish.", var28);
      }

      AppenderAttachableImpl var1 = this.appenders;
      synchronized(var1){}

      Throwable var10000;
      label288: {
         Enumeration var34;
         boolean var10001;
         try {
            var34 = this.appenders.getAllAppenders();
         } catch (Throwable var33) {
            var10000 = var33;
            var10001 = false;
            break label288;
         }

         if (var34 != null) {
            while(true) {
               try {
                  if (!var34.hasMoreElements()) {
                     break;
                  }
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label288;
               }

               try {
                  Object var3 = var34.nextElement();
                  if (var3 instanceof Appender) {
                     ((Appender)var3).close();
                  }
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label288;
               }
            }
         }

         label260:
         try {
            return;
         } catch (Throwable var30) {
            var10000 = var30;
            var10001 = false;
            break label260;
         }
      }

      Throwable var35 = var10000;
      throw var35;
   }

   public Enumeration getAllAppenders() {
      AppenderAttachableImpl var1 = this.appenders;
      synchronized(var1) {
         Enumeration var2 = this.appenders.getAllAppenders();
         return var2;
      }
   }

   public Appender getAppender(String var1) {
      AppenderAttachableImpl var2 = this.appenders;
      synchronized(var2) {
         Appender var4 = this.appenders.getAppender(var1);
         return var4;
      }
   }

   public boolean getBlocking() {
      return this.blocking;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public boolean isAttached(Appender var1) {
      AppenderAttachableImpl var3 = this.appenders;
      synchronized(var3) {
         boolean var2 = this.appenders.isAttached(var1);
         return var2;
      }
   }

   public void removeAllAppenders() {
      AppenderAttachableImpl var1 = this.appenders;
      synchronized(var1) {
         this.appenders.removeAllAppenders();
      }
   }

   public void removeAppender(String var1) {
      AppenderAttachableImpl var2 = this.appenders;
      synchronized(var2) {
         this.appenders.removeAppender(var1);
      }
   }

   public void removeAppender(Appender var1) {
      AppenderAttachableImpl var2 = this.appenders;
      synchronized(var2) {
         this.appenders.removeAppender(var1);
      }
   }

   public boolean requiresLayout() {
      return false;
   }

   public void setBlocking(boolean var1) {
      List var2 = this.buffer;
      synchronized(var2) {
         this.blocking = var1;
         this.buffer.notifyAll();
      }
   }

   public void setBufferSize(int var1) {
      if (var1 >= 0) {
         List var3 = this.buffer;
         synchronized(var3){}
         int var2 = var1;
         if (var1 < 1) {
            var2 = 1;
         }

         try {
            this.bufferSize = var2;
            this.buffer.notifyAll();
         } finally {
            ;
         }
      } else {
         throw new NegativeArraySizeException("size");
      }
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   private static final class DiscardSummary {
      private int count;
      private LoggingEvent maxEvent;

      public DiscardSummary(LoggingEvent var1) {
         this.maxEvent = var1;
         this.count = 1;
      }

      public void add(LoggingEvent var1) {
         if (var1.getLevel().toInt() > this.maxEvent.getLevel().toInt()) {
            this.maxEvent = var1;
         }

         ++this.count;
      }

      public LoggingEvent createEvent() {
         String var1 = MessageFormat.format("Discarded {0} messages due to full event buffer including: {1}", new Integer(this.count), this.maxEvent.getMessage());
         return new LoggingEvent((String)null, Logger.getLogger(this.maxEvent.getLoggerName()), this.maxEvent.getLevel(), var1, (Throwable)null);
      }
   }

   private static class Dispatcher implements Runnable {
      private final AppenderAttachableImpl appenders;
      private final List buffer;
      private final Map discardMap;
      private final AsyncAppender parent;

      public Dispatcher(AsyncAppender var1, List var2, Map var3, AppenderAttachableImpl var4) {
         this.parent = var1;
         this.buffer = var2;
         this.appenders = var4;
         this.discardMap = var3;
      }

      public void run() {
         // $FF: Couldn't be decompiled
      }
   }
}
