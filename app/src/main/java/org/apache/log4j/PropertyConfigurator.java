package org.apache.log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.RendererSupport;

public class PropertyConfigurator implements Configurator {
   static final String ADDITIVITY_PREFIX = "log4j.additivity.";
   static final String APPENDER_PREFIX = "log4j.appender.";
   static final String CATEGORY_PREFIX = "log4j.category.";
   static final String FACTORY_PREFIX = "log4j.factory";
   private static final String INTERNAL_ROOT_NAME = "root";
   public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";
   static final String LOGGER_PREFIX = "log4j.logger.";
   static final String RENDERER_PREFIX = "log4j.renderer.";
   static final String ROOT_CATEGORY_PREFIX = "log4j.rootCategory";
   static final String ROOT_LOGGER_PREFIX = "log4j.rootLogger";
   static final String THRESHOLD_PREFIX = "log4j.threshold";
   static Class class$org$apache$log4j$Appender;
   static Class class$org$apache$log4j$Layout;
   static Class class$org$apache$log4j$spi$LoggerFactory;
   protected LoggerFactory loggerFactory = new DefaultCategoryFactory();
   protected Hashtable registry = new Hashtable(11);

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public static void configure(String var0) {
      (new PropertyConfigurator()).doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void configure(URL var0) {
      (new PropertyConfigurator()).doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void configure(Properties var0) {
      (new PropertyConfigurator()).doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void configureAndWatch(String var0) {
      configureAndWatch(var0, 60000L);
   }

   public static void configureAndWatch(String var0, long var1) {
      PropertyWatchdog var3 = new PropertyWatchdog(var0);
      var3.setDelay(var1);
      var3.start();
   }

   protected void configureLoggerFactory(Properties var1) {
      String var4 = OptionConverter.findAndSubst("log4j.loggerFactory", var1);
      if (var4 != null) {
         LogLog.debug("Setting category factory to [" + var4 + "].");
         Class var3 = class$org$apache$log4j$spi$LoggerFactory;
         Class var2 = var3;
         if (var3 == null) {
            var2 = class$("org.apache.log4j.spi.LoggerFactory");
            class$org$apache$log4j$spi$LoggerFactory = var2;
         }

         LoggerFactory var5 = (LoggerFactory)OptionConverter.instantiateByClassName(var4, var2, this.loggerFactory);
         this.loggerFactory = var5;
         PropertySetter.setProperties(var5, var1, "log4j.factory.");
      }

   }

   void configureRootCategory(Properties var1, LoggerRepository var2) {
      String var4 = "log4j.rootLogger";
      String var5 = OptionConverter.findAndSubst("log4j.rootLogger", var1);
      String var3 = var5;
      if (var5 == null) {
         var3 = OptionConverter.findAndSubst("log4j.rootCategory", var1);
         var4 = "log4j.rootCategory";
      }

      if (var3 == null) {
         LogLog.debug("Could not find root logger information. Is this OK?");
      } else {
         Logger var7 = var2.getRootLogger();
         synchronized(var7) {
            this.parseCategory(var1, var7, var4, "root", var3);
         }
      }

   }

   public void doConfigure(String var1, LoggerRepository var2) {
      Properties var4 = new Properties();

      try {
         FileInputStream var3 = new FileInputStream(var1);
         var4.load(var3);
         var3.close();
      } catch (IOException var5) {
         LogLog.error("Could not read configuration file [" + var1 + "].", var5);
         LogLog.error("Ignoring configuration file [" + var1 + "].");
         return;
      }

      this.doConfigure(var4, var2);
   }

   public void doConfigure(URL var1, LoggerRepository var2) {
      Properties var3 = new Properties();
      LogLog.debug("Reading configuration from URL " + var1);

      try {
         var3.load(var1.openStream());
      } catch (IOException var4) {
         LogLog.error("Could not read configuration file from URL [" + var1 + "].", var4);
         LogLog.error("Ignoring configuration file [" + var1 + "].");
         return;
      }

      this.doConfigure(var3, var2);
   }

   public void doConfigure(Properties var1, LoggerRepository var2) {
      String var4 = var1.getProperty("log4j.debug");
      String var3 = var4;
      if (var4 == null) {
         var4 = var1.getProperty("log4j.configDebug");
         var3 = var4;
         if (var4 != null) {
            LogLog.warn("[log4j.configDebug] is deprecated. Use [log4j.debug] instead.");
            var3 = var4;
         }
      }

      if (var3 != null) {
         LogLog.setInternalDebugging(OptionConverter.toBoolean(var3, true));
      }

      var3 = OptionConverter.findAndSubst("log4j.threshold", var1);
      if (var3 != null) {
         var2.setThreshold(OptionConverter.toLevel(var3, Level.ALL));
         LogLog.debug("Hierarchy threshold set to [" + var2.getThreshold() + "].");
      }

      this.configureRootCategory(var1, var2);
      this.configureLoggerFactory(var1);
      this.parseCatsAndRenderers(var1, var2);
      LogLog.debug("Finished configuring.");
      this.registry.clear();
   }

   void parseAdditivityForLogger(Properties var1, Logger var2, String var3) {
      String var5 = OptionConverter.findAndSubst("log4j.additivity." + var3, var1);
      LogLog.debug("Handling log4j.additivity." + var3 + "=[" + var5 + "]");
      if (var5 != null && !var5.equals("")) {
         boolean var4 = OptionConverter.toBoolean(var5, true);
         LogLog.debug("Setting additivity for \"" + var3 + "\" to " + var4);
         var2.setAdditivity(var4);
      }

   }

   Appender parseAppender(Properties var1, String var2) {
      Appender var3 = this.registryGet(var2);
      if (var3 != null) {
         LogLog.debug("Appender \"" + var2 + "\" was already parsed.");
         return var3;
      } else {
         String var5 = "log4j.appender." + var2;
         String var6 = var5 + ".layout";
         Class var4 = class$org$apache$log4j$Appender;
         Class var8 = var4;
         if (var4 == null) {
            var8 = class$("org.apache.log4j.Appender");
            class$org$apache$log4j$Appender = var8;
         }

         Appender var7 = (Appender)OptionConverter.instantiateByKey(var1, var5, var8, (Object)null);
         if (var7 == null) {
            LogLog.error("Could not instantiate appender named \"" + var2 + "\".");
            return null;
         } else {
            var7.setName(var2);
            if (var7 instanceof OptionHandler) {
               if (var7.requiresLayout()) {
                  var4 = class$org$apache$log4j$Layout;
                  var8 = var4;
                  if (var4 == null) {
                     var8 = class$("org.apache.log4j.Layout");
                     class$org$apache$log4j$Layout = var8;
                  }

                  Layout var9 = (Layout)OptionConverter.instantiateByKey(var1, var6, var8, (Object)null);
                  if (var9 != null) {
                     var7.setLayout(var9);
                     LogLog.debug("Parsing layout options for \"" + var2 + "\".");
                     PropertySetter.setProperties(var9, var1, var6 + ".");
                     LogLog.debug("End of parsing for \"" + var2 + "\".");
                  }
               }

               PropertySetter.setProperties(var7, var1, var5 + ".");
               LogLog.debug("Parsed \"" + var2 + "\" options.");
            }

            this.registryPut(var7);
            return var7;
         }
      }
   }

   void parseCategory(Properties var1, Logger var2, String var3, String var4, String var5) {
      LogLog.debug("Parsing for [" + var4 + "] with value=[" + var5 + "].");
      StringTokenizer var6 = new StringTokenizer(var5, ",");
      if (!var5.startsWith(",") && !var5.equals("")) {
         if (!var6.hasMoreTokens()) {
            return;
         }

         var5 = var6.nextToken();
         LogLog.debug("Level token is [" + var5 + "].");
         if (!"inherited".equalsIgnoreCase(var5) && !"null".equalsIgnoreCase(var5)) {
            var2.setLevel(OptionConverter.toLevel(var5, Level.DEBUG));
         } else if (var4.equals("root")) {
            LogLog.warn("The root logger cannot be set to null.");
         } else {
            var2.setLevel((Level)null);
         }

         LogLog.debug("Category " + var4 + " set to " + var2.getLevel());
      }

      var2.removeAllAppenders();

      while(var6.hasMoreTokens()) {
         var4 = var6.nextToken().trim();
         if (var4 != null && !var4.equals(",")) {
            LogLog.debug("Parsing appender named \"" + var4 + "\".");
            Appender var7 = this.parseAppender(var1, var4);
            if (var7 != null) {
               var2.addAppender(var7);
            }
         }
      }

   }

   protected void parseCatsAndRenderers(Properties var1, LoggerRepository var2) {
      Enumeration var4 = var1.propertyNames();

      while(true) {
         while(var4.hasMoreElements()) {
            String var5 = (String)var4.nextElement();
            String var3;
            if (!var5.startsWith("log4j.category.") && !var5.startsWith("log4j.logger.")) {
               if (var5.startsWith("log4j.renderer.")) {
                  var3 = var5.substring(15);
                  var5 = OptionConverter.findAndSubst(var5, var1);
                  if (var2 instanceof RendererSupport) {
                     RendererMap.addRenderer((RendererSupport)var2, var3, var5);
                  }
               }
            } else {
               var3 = null;
               if (var5.startsWith("log4j.category.")) {
                  var3 = var5.substring(15);
               } else if (var5.startsWith("log4j.logger.")) {
                  var3 = var5.substring(13);
               }

               String var7 = OptionConverter.findAndSubst(var5, var1);
               Logger var6 = var2.getLogger(var3, this.loggerFactory);
               synchronized(var6) {
                  this.parseCategory(var1, var6, var5, var3, var7);
                  this.parseAdditivityForLogger(var1, var6, var3);
               }
            }
         }

         return;
      }
   }

   Appender registryGet(String var1) {
      return (Appender)this.registry.get(var1);
   }

   void registryPut(Appender var1) {
      this.registry.put(var1.getName(), var1);
   }
}
