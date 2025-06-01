package org.apache.log4j.spi;

public class DefaultRepositorySelector implements RepositorySelector {
   final LoggerRepository repository;

   public DefaultRepositorySelector(LoggerRepository var1) {
      this.repository = var1;
   }

   public LoggerRepository getLoggerRepository() {
      return this.repository;
   }
}
