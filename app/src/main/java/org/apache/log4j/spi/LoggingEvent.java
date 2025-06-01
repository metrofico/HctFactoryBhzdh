package org.apache.log4j.spi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Hashtable;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;

public class LoggingEvent implements Serializable {
   static final Integer[] PARAM_ARRAY = new Integer[1];
   static final String TO_LEVEL = "toLevel";
   static final Class[] TO_LEVEL_PARAMS;
   static Class class$org$apache$log4j$Level;
   static final Hashtable methodCache;
   static final long serialVersionUID = -868428216207166145L;
   private static long startTime = System.currentTimeMillis();
   public final String categoryName;
   public final transient String fqnOfCategoryClass;
   public transient Priority level;
   private LocationInfo locationInfo;
   private transient Category logger;
   private Hashtable mdcCopy;
   private boolean mdcCopyLookupRequired = true;
   private transient Object message;
   private String ndc;
   private boolean ndcLookupRequired = true;
   private String renderedMessage;
   private String threadName;
   private ThrowableInformation throwableInfo;
   public final long timeStamp;

   static {
      TO_LEVEL_PARAMS = new Class[]{Integer.TYPE};
      methodCache = new Hashtable(3);
   }

   public LoggingEvent(String var1, Category var2, long var3, Priority var5, Object var6, Throwable var7) {
      this.fqnOfCategoryClass = var1;
      this.logger = var2;
      this.categoryName = var2.getName();
      this.level = var5;
      this.message = var6;
      if (var7 != null) {
         this.throwableInfo = new ThrowableInformation(var7);
      }

      this.timeStamp = var3;
   }

   public LoggingEvent(String var1, Category var2, Priority var3, Object var4, Throwable var5) {
      this.fqnOfCategoryClass = var1;
      this.logger = var2;
      this.categoryName = var2.getName();
      this.level = var3;
      this.message = var4;
      if (var5 != null) {
         this.throwableInfo = new ThrowableInformation(var5);
      }

      this.timeStamp = System.currentTimeMillis();
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

   public static long getStartTime() {
      return startTime;
   }

   private void readLevel(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      int var2 = var1.readInt();

      Exception var10000;
      label50: {
         String var4;
         boolean var10001;
         try {
            var4 = (String)var1.readObject();
         } catch (Exception var11) {
            var10000 = var11;
            var10001 = false;
            break label50;
         }

         if (var4 == null) {
            try {
               this.level = Level.toLevel(var2);
               return;
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
            }
         } else {
            label55: {
               Method var3;
               Hashtable var5;
               try {
                  var5 = methodCache;
                  var3 = (Method)var5.get(var4);
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label55;
               }

               Method var12 = var3;
               if (var3 == null) {
                  try {
                     var12 = Loader.loadClass(var4).getDeclaredMethod("toLevel", TO_LEVEL_PARAMS);
                     var5.put(var4, var12);
                  } catch (Exception var9) {
                     var10000 = var9;
                     var10001 = false;
                     break label55;
                  }
               }

               Integer[] var14;
               Integer var15;
               try {
                  var14 = PARAM_ARRAY;
                  var15 = new Integer(var2);
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label55;
               }

               var14[0] = var15;

               try {
                  this.level = (Level)var12.invoke((Object)null, var14);
                  return;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
               }
            }
         }
      }

      Exception var13 = var10000;
      LogLog.warn("Level deserialization failed, reverting to default.", var13);
      this.level = Level.toLevel(var2);
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      this.readLevel(var1);
      if (this.locationInfo == null) {
         this.locationInfo = new LocationInfo((Throwable)null, (String)null);
      }

   }

   private void writeLevel(ObjectOutputStream var1) throws IOException {
      var1.writeInt(this.level.toInt());
      Class var4 = this.level.getClass();
      Class var3 = class$org$apache$log4j$Level;
      Class var2 = var3;
      if (var3 == null) {
         var2 = class$("org.apache.log4j.Level");
         class$org$apache$log4j$Level = var2;
      }

      if (var4 == var2) {
         var1.writeObject((Object)null);
      } else {
         var1.writeObject(var4.getName());
      }

   }

   private void writeObject(ObjectOutputStream var1) throws IOException {
      this.getThreadName();
      this.getRenderedMessage();
      this.getNDC();
      this.getMDCCopy();
      this.getThrowableStrRep();
      var1.defaultWriteObject();
      this.writeLevel(var1);
   }

   public Level getLevel() {
      return (Level)this.level;
   }

   public LocationInfo getLocationInformation() {
      if (this.locationInfo == null) {
         this.locationInfo = new LocationInfo(new Throwable(), this.fqnOfCategoryClass);
      }

      return this.locationInfo;
   }

   public String getLoggerName() {
      return this.categoryName;
   }

   public Object getMDC(String var1) {
      Hashtable var2 = this.mdcCopy;
      if (var2 != null) {
         Object var3 = var2.get(var1);
         if (var3 != null) {
            return var3;
         }
      }

      return MDC.get(var1);
   }

   public void getMDCCopy() {
      if (this.mdcCopyLookupRequired) {
         this.mdcCopyLookupRequired = false;
         Hashtable var1 = MDC.getContext();
         if (var1 != null) {
            this.mdcCopy = (Hashtable)var1.clone();
         }
      }

   }

   public Object getMessage() {
      Object var1 = this.message;
      return var1 != null ? var1 : this.getRenderedMessage();
   }

   public String getNDC() {
      if (this.ndcLookupRequired) {
         this.ndcLookupRequired = false;
         this.ndc = NDC.get();
      }

      return this.ndc;
   }

   public String getRenderedMessage() {
      if (this.renderedMessage == null) {
         Object var1 = this.message;
         if (var1 != null) {
            if (var1 instanceof String) {
               this.renderedMessage = (String)var1;
            } else {
               LoggerRepository var2 = this.logger.getLoggerRepository();
               if (var2 instanceof RendererSupport) {
                  this.renderedMessage = ((RendererSupport)var2).getRendererMap().findAndRender(this.message);
               } else {
                  this.renderedMessage = this.message.toString();
               }
            }
         }
      }

      return this.renderedMessage;
   }

   public String getThreadName() {
      if (this.threadName == null) {
         this.threadName = Thread.currentThread().getName();
      }

      return this.threadName;
   }

   public ThrowableInformation getThrowableInformation() {
      return this.throwableInfo;
   }

   public String[] getThrowableStrRep() {
      ThrowableInformation var1 = this.throwableInfo;
      return var1 == null ? null : var1.getThrowableStrRep();
   }
}
