package org.apache.log4j;

import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public interface Appender {
   void addFilter(Filter var1);

   void clearFilters();

   void close();

   void doAppend(LoggingEvent var1);

   ErrorHandler getErrorHandler();

   Filter getFilter();

   Layout getLayout();

   String getName();

   boolean requiresLayout();

   void setErrorHandler(ErrorHandler var1);

   void setLayout(Layout var1);

   void setName(String var1);
}
