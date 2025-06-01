package org.apache.log4j;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class AppenderSkeleton implements Appender, OptionHandler {
   protected boolean closed = false;
   protected ErrorHandler errorHandler = new OnlyOnceErrorHandler();
   protected Filter headFilter;
   protected Layout layout;
   protected String name;
   protected Filter tailFilter;
   protected Priority threshold;

   public void activateOptions() {
   }

   public void addFilter(Filter var1) {
      if (this.headFilter == null) {
         this.tailFilter = var1;
         this.headFilter = var1;
      } else {
         this.tailFilter.next = var1;
         this.tailFilter = var1;
      }

   }

   protected abstract void append(LoggingEvent var1);

   public void clearFilters() {
      this.tailFilter = null;
      this.headFilter = null;
   }

   public abstract void close();

   public void doAppend(LoggingEvent var1) {
      synchronized(this){}

      Throwable var10000;
      label464: {
         boolean var10001;
         try {
            if (this.closed) {
               StringBuffer var48 = new StringBuffer();
               LogLog.error(var48.append("Attempted to append to closed appender named [").append(this.name).append("].").toString());
               return;
            }
         } catch (Throwable var46) {
            var10000 = var46;
            var10001 = false;
            break label464;
         }

         boolean var3;
         try {
            var3 = this.isAsSevereAsThreshold(var1.getLevel());
         } catch (Throwable var45) {
            var10000 = var45;
            var10001 = false;
            break label464;
         }

         if (!var3) {
            return;
         }

         Filter var4;
         try {
            var4 = this.headFilter;
         } catch (Throwable var44) {
            var10000 = var44;
            var10001 = false;
            break label464;
         }

         while(var4 != null) {
            int var2;
            try {
               var2 = var4.decide(var1);
            } catch (Throwable var43) {
               var10000 = var43;
               var10001 = false;
               break label464;
            }

            if (var2 == -1) {
               return;
            }

            if (var2 != 0) {
               if (var2 != 1) {
                  continue;
               }
               break;
            } else {
               try {
                  var4 = var4.next;
               } catch (Throwable var42) {
                  var10000 = var42;
                  var10001 = false;
                  break label464;
               }
            }
         }

         try {
            this.append(var1);
         } catch (Throwable var41) {
            var10000 = var41;
            var10001 = false;
            break label464;
         }

         return;
      }

      Throwable var47 = var10000;
      throw var47;
   }

   public void finalize() {
      if (!this.closed) {
         LogLog.debug("Finalizing appender named [" + this.name + "].");
         this.close();
      }
   }

   public ErrorHandler getErrorHandler() {
      return this.errorHandler;
   }

   public Filter getFilter() {
      return this.headFilter;
   }

   public final Filter getFirstFilter() {
      return this.headFilter;
   }

   public Layout getLayout() {
      return this.layout;
   }

   public final String getName() {
      return this.name;
   }

   public Priority getThreshold() {
      return this.threshold;
   }

   public boolean isAsSevereAsThreshold(Priority var1) {
      Priority var3 = this.threshold;
      boolean var2;
      if (var3 != null && !var1.isGreaterOrEqual(var3)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public abstract boolean requiresLayout();

   public void setErrorHandler(ErrorHandler var1) {
      synchronized(this){}
      Throwable var10000;
      boolean var10001;
      if (var1 == null) {
         label53:
         try {
            LogLog.warn("You have tried to set a null error-handler.");
            return;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label53;
         }
      } else {
         label55:
         try {
            this.errorHandler = var1;
            return;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label55;
         }
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public void setLayout(Layout var1) {
      this.layout = var1;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public void setThreshold(Priority var1) {
      this.threshold = var1;
   }
}
