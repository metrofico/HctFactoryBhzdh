package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

class DefaultCategoryFactory implements LoggerFactory {
   public Logger makeNewLoggerInstance(String var1) {
      return new Logger(var1);
   }
}
