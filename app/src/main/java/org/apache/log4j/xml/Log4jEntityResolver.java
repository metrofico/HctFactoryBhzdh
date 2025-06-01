package org.apache.log4j.xml;

import java.io.InputStream;
import org.apache.log4j.helpers.LogLog;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class Log4jEntityResolver implements EntityResolver {
   public InputSource resolveEntity(String var1, String var2) {
      if (var2.endsWith("log4j.dtd")) {
         Class var3 = this.getClass();
         InputStream var4 = var3.getResourceAsStream("/org/apache/log4j/xml/log4j.dtd");
         if (var4 == null) {
            LogLog.error("Could not find [log4j.dtd]. Used [" + var3.getClassLoader() + "] class loader in the search.");
            return null;
         } else {
            return new InputSource(var4);
         }
      } else {
         return null;
      }
   }
}
