package org.apache.log4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.DefaultRepositorySelector;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RepositorySelector;
import org.apache.log4j.spi.RootLogger;

public class LogManager {
   public static final String CONFIGURATOR_CLASS_KEY = "log4j.configuratorClass";
   public static final String DEFAULT_CONFIGURATION_FILE = "log4j.properties";
   public static final String DEFAULT_CONFIGURATION_KEY = "log4j.configuration";
   public static final String DEFAULT_INIT_OVERRIDE_KEY = "log4j.defaultInitOverride";
   static final String DEFAULT_XML_CONFIGURATION_FILE = "log4j.xml";
   private static Object guard;
   private static RepositorySelector repositorySelector;

   static {
      repositorySelector = new DefaultRepositorySelector(new Hierarchy(new RootLogger(Level.DEBUG)));
      String var0 = OptionConverter.getSystemProperty("log4j.defaultInitOverride", (String)null);
      if (var0 == null || "false".equalsIgnoreCase(var0)) {
         String var3 = OptionConverter.getSystemProperty("log4j.configuration", (String)null);
         String var2 = OptionConverter.getSystemProperty("log4j.configuratorClass", (String)null);
         URL var5;
         if (var3 == null) {
            URL var1 = Loader.getResource("log4j.xml");
            var5 = var1;
            if (var1 == null) {
               var5 = Loader.getResource("log4j.properties");
            }
         } else {
            try {
               var5 = new URL(var3);
            } catch (MalformedURLException var4) {
               var5 = Loader.getResource(var3);
            }
         }

         if (var5 != null) {
            LogLog.debug("Using URL [" + var5 + "] for automatic log4j configuration.");
            OptionConverter.selectAndConfigure(var5, var2, getLoggerRepository());
         } else {
            LogLog.debug("Could not find resource: [" + var3 + "].");
         }
      }

   }

   public static Logger exists(String var0) {
      return repositorySelector.getLoggerRepository().exists(var0);
   }

   public static Enumeration getCurrentLoggers() {
      return repositorySelector.getLoggerRepository().getCurrentLoggers();
   }

   public static Logger getLogger(Class var0) {
      return repositorySelector.getLoggerRepository().getLogger(var0.getName());
   }

   public static Logger getLogger(String var0) {
      return repositorySelector.getLoggerRepository().getLogger(var0);
   }

   public static Logger getLogger(String var0, LoggerFactory var1) {
      return repositorySelector.getLoggerRepository().getLogger(var0, var1);
   }

   public static LoggerRepository getLoggerRepository() {
      return repositorySelector.getLoggerRepository();
   }

   public static Logger getRootLogger() {
      return repositorySelector.getLoggerRepository().getRootLogger();
   }

   public static void resetConfiguration() {
      repositorySelector.getLoggerRepository().resetConfiguration();
   }

   public static void setRepositorySelector(RepositorySelector var0, Object var1) throws IllegalArgumentException {
      Object var2 = guard;
      if (var2 != null && var2 != var1) {
         throw new IllegalArgumentException("Attempted to reset the LoggerFactory without possessing the guard.");
      } else if (var0 != null) {
         guard = var1;
         repositorySelector = var0;
      } else {
         throw new IllegalArgumentException("RepositorySelector must be non-null.");
      }
   }

   public static void shutdown() {
      repositorySelector.getLoggerRepository().shutdown();
   }
}
