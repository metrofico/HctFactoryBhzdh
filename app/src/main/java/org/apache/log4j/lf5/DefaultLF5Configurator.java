package org.apache.log4j.lf5;

import java.io.IOException;
import java.net.URL;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class DefaultLF5Configurator implements Configurator {
   static Class class$org$apache$log4j$lf5$DefaultLF5Configurator;

   private DefaultLF5Configurator() {
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

   public static void configure() throws IOException {
      Class var1 = class$org$apache$log4j$lf5$DefaultLF5Configurator;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.lf5.DefaultLF5Configurator");
         class$org$apache$log4j$lf5$DefaultLF5Configurator = var0;
      }

      URL var2 = var0.getResource("/org/apache/log4j/lf5/config/defaultconfig.properties");
      if (var2 != null) {
         PropertyConfigurator.configure(var2);
      } else {
         throw new IOException("Error: Unable to open the resource" + "/org/apache/log4j/lf5/config/defaultconfig.properties");
      }
   }

   public void doConfigure(URL var1, LoggerRepository var2) {
      throw new IllegalStateException("This class should NOT be instantiated!");
   }
}
