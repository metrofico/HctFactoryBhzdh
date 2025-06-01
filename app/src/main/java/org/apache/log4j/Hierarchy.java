package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.or.ObjectRenderer;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;

public class Hierarchy implements LoggerRepository, RendererSupport {
   private LoggerFactory defaultFactory;
   boolean emittedNoAppenderWarning = false;
   boolean emittedNoResourceBundleWarning = false;
   Hashtable ht = new Hashtable();
   private Vector listeners = new Vector(1);
   RendererMap rendererMap;
   Logger root;
   Level threshold;
   int thresholdInt;

   public Hierarchy(Logger var1) {
      this.root = var1;
      this.setThreshold(Level.ALL);
      this.root.setHierarchy(this);
      this.rendererMap = new RendererMap();
      this.defaultFactory = new DefaultCategoryFactory();
   }

   private final void updateChildren(ProvisionNode var1, Logger var2) {
      int var4 = var1.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         Logger var5 = (Logger)var1.elementAt(var3);
         if (!var5.parent.name.startsWith(var2.name)) {
            var2.parent = var5.parent;
            var5.parent = var2;
         }
      }

   }

   private final void updateParents(Logger var1) {
      String var5 = var1.name;
      int var2 = var5.length();
      boolean var3 = true;
      var2 = var5.lastIndexOf(46, var2 - 1);

      boolean var7;
      while(true) {
         if (var2 < 0) {
            var7 = false;
            break;
         }

         CategoryKey var4 = new CategoryKey(var5.substring(0, var2));
         Object var6 = this.ht.get(var4);
         if (var6 == null) {
            ProvisionNode var8 = new ProvisionNode(var1);
            this.ht.put(var4, var8);
         } else {
            if (var6 instanceof Category) {
               var1.parent = (Category)var6;
               var7 = var3;
               break;
            }

            if (var6 instanceof ProvisionNode) {
               ((ProvisionNode)var6).addElement(var1);
            } else {
               (new IllegalStateException("unexpected object type " + var6.getClass() + " in ht.")).printStackTrace();
            }
         }

         var2 = var5.lastIndexOf(46, var2 - 1);
      }

      if (!var7) {
         var1.parent = this.root;
      }

   }

   public void addHierarchyEventListener(HierarchyEventListener var1) {
      if (this.listeners.contains(var1)) {
         LogLog.warn("Ignoring attempt to add an existent listener.");
      } else {
         this.listeners.addElement(var1);
      }

   }

   public void addRenderer(Class var1, ObjectRenderer var2) {
      this.rendererMap.put(var1, var2);
   }

   public void clear() {
      this.ht.clear();
   }

   public void emitNoAppenderWarning(Category var1) {
      if (!this.emittedNoAppenderWarning) {
         LogLog.warn("No appenders could be found for logger (" + var1.getName() + ").");
         LogLog.warn("Please initialize the log4j system properly.");
         this.emittedNoAppenderWarning = true;
      }

   }

   public Logger exists(String var1) {
      Object var2 = this.ht.get(new CategoryKey(var1));
      return var2 instanceof Logger ? (Logger)var2 : null;
   }

   public void fireAddAppenderEvent(Category var1, Appender var2) {
      Vector var5 = this.listeners;
      if (var5 != null) {
         int var4 = var5.size();

         for(int var3 = 0; var3 < var4; ++var3) {
            ((HierarchyEventListener)this.listeners.elementAt(var3)).addAppenderEvent(var1, var2);
         }
      }

   }

   void fireRemoveAppenderEvent(Category var1, Appender var2) {
      Vector var5 = this.listeners;
      if (var5 != null) {
         int var4 = var5.size();

         for(int var3 = 0; var3 < var4; ++var3) {
            ((HierarchyEventListener)this.listeners.elementAt(var3)).removeAppenderEvent(var1, var2);
         }
      }

   }

   public Enumeration getCurrentCategories() {
      return this.getCurrentLoggers();
   }

   public Enumeration getCurrentLoggers() {
      Vector var1 = new Vector(this.ht.size());
      Enumeration var3 = this.ht.elements();

      while(var3.hasMoreElements()) {
         Object var2 = var3.nextElement();
         if (var2 instanceof Logger) {
            var1.addElement(var2);
         }
      }

      return var1.elements();
   }

   public Logger getLogger(String var1) {
      return this.getLogger(var1, this.defaultFactory);
   }

   public Logger getLogger(String var1, LoggerFactory var2) {
      CategoryKey var4 = new CategoryKey(var1);
      Hashtable var3 = this.ht;
      synchronized(var3){}

      Throwable var10000;
      label256: {
         Object var5;
         boolean var10001;
         try {
            var5 = this.ht.get(var4);
         } catch (Throwable var34) {
            var10000 = var34;
            var10001 = false;
            break label256;
         }

         Logger var36;
         if (var5 == null) {
            label237:
            try {
               var36 = var2.makeNewLoggerInstance(var1);
               var36.setHierarchy(this);
               this.ht.put(var4, var36);
               this.updateParents(var36);
               return var36;
            } catch (Throwable var31) {
               var10000 = var31;
               var10001 = false;
               break label237;
            }
         } else {
            label257: {
               try {
                  if (var5 instanceof Logger) {
                     var36 = (Logger)var5;
                     return var36;
                  }
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label257;
               }

               try {
                  if (var5 instanceof ProvisionNode) {
                     var36 = var2.makeNewLoggerInstance(var1);
                     var36.setHierarchy(this);
                     this.ht.put(var4, var36);
                     this.updateChildren((ProvisionNode)var5, var36);
                     this.updateParents(var36);
                     return var36;
                  }
               } catch (Throwable var35) {
                  var10000 = var35;
                  var10001 = false;
                  break label257;
               }

               label239:
               try {
                  return null;
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label239;
               }
            }
         }
      }

      Throwable var37 = var10000;
      throw var37;
   }

   public RendererMap getRendererMap() {
      return this.rendererMap;
   }

   public Logger getRootLogger() {
      return this.root;
   }

   public Level getThreshold() {
      return this.threshold;
   }

   public boolean isDisabled(int var1) {
      boolean var2;
      if (this.thresholdInt > var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public void overrideAsNeeded(String var1) {
      LogLog.warn("The Hiearchy.overrideAsNeeded method has been deprecated.");
   }

   public void resetConfiguration() {
      this.getRootLogger().setLevel(Level.DEBUG);
      this.root.setResourceBundle((ResourceBundle)null);
      this.setThreshold(Level.ALL);
      Hashtable var1 = this.ht;
      synchronized(var1){}

      label128: {
         Throwable var10000;
         label127: {
            boolean var10001;
            Enumeration var3;
            try {
               this.shutdown();
               var3 = this.getCurrentLoggers();
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label127;
            }

            while(true) {
               try {
                  if (!var3.hasMoreElements()) {
                     break label128;
                  }
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break;
               }

               try {
                  Logger var16 = (Logger)var3.nextElement();
                  var16.setLevel((Level)null);
                  var16.setAdditivity(true);
                  var16.setResourceBundle((ResourceBundle)null);
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break;
               }
            }
         }

         Throwable var2 = var10000;
         throw var2;
      }

      this.rendererMap.clear();
   }

   public void setDisableOverride(String var1) {
      LogLog.warn("The Hiearchy.setDisableOverride method has been deprecated.");
   }

   public void setRenderer(Class var1, ObjectRenderer var2) {
      this.rendererMap.put(var1, var2);
   }

   public void setThreshold(String var1) {
      Level var2 = Level.toLevel(var1, (Level)null);
      if (var2 != null) {
         this.setThreshold(var2);
      } else {
         LogLog.warn("Could not convert [" + var1 + "] to Level.");
      }

   }

   public void setThreshold(Level var1) {
      if (var1 != null) {
         this.thresholdInt = var1.level;
         this.threshold = var1;
      }

   }

   public void shutdown() {
      Logger var3 = this.getRootLogger();
      var3.closeNestedAppenders();
      Hashtable var1 = this.ht;
      synchronized(var1){}

      Throwable var10000;
      label299: {
         boolean var10001;
         Enumeration var2;
         try {
            var2 = this.getCurrentLoggers();
         } catch (Throwable var32) {
            var10000 = var32;
            var10001 = false;
            break label299;
         }

         while(true) {
            try {
               if (!var2.hasMoreElements()) {
                  var3.removeAllAppenders();
                  var2 = this.getCurrentLoggers();
                  break;
               }
            } catch (Throwable var33) {
               var10000 = var33;
               var10001 = false;
               break label299;
            }

            try {
               ((Logger)var2.nextElement()).closeNestedAppenders();
            } catch (Throwable var31) {
               var10000 = var31;
               var10001 = false;
               break label299;
            }
         }

         while(true) {
            try {
               if (!var2.hasMoreElements()) {
                  return;
               }
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               break;
            }

            try {
               ((Logger)var2.nextElement()).removeAllAppenders();
            } catch (Throwable var29) {
               var10000 = var29;
               var10001 = false;
               break;
            }
         }
      }

      Throwable var34 = var10000;
      throw var34;
   }
}
