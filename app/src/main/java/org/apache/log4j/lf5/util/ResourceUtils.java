package org.apache.log4j.lf5.util;

import java.io.InputStream;
import java.net.URL;

public class ResourceUtils {
   public static InputStream getResourceAsStream(Object var0, Resource var1) {
      ClassLoader var2 = var0.getClass().getClassLoader();
      InputStream var3;
      if (var2 != null) {
         var3 = var2.getResourceAsStream(var1.getName());
      } else {
         var3 = ClassLoader.getSystemResourceAsStream(var1.getName());
      }

      return var3;
   }

   public static URL getResourceAsURL(Object var0, Resource var1) {
      ClassLoader var2 = var0.getClass().getClassLoader();
      URL var3;
      if (var2 != null) {
         var3 = var2.getResource(var1.getName());
      } else {
         var3 = ClassLoader.getSystemResource(var1.getName());
      }

      return var3;
   }
}
