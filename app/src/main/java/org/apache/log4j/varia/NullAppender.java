package org.apache.log4j.varia;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class NullAppender extends AppenderSkeleton {
   private static NullAppender instance = new NullAppender();

   public void activateOptions() {
   }

   protected void append(LoggingEvent var1) {
   }

   public void close() {
   }

   public void doAppend(LoggingEvent var1) {
   }

   public NullAppender getInstance() {
      return instance;
   }

   public boolean requiresLayout() {
      return false;
   }
}
