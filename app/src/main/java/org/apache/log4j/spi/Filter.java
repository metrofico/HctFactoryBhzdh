package org.apache.log4j.spi;

public abstract class Filter implements OptionHandler {
   public static final int ACCEPT = 1;
   public static final int DENY = -1;
   public static final int NEUTRAL = 0;
   public Filter next;

   public void activateOptions() {
   }

   public abstract int decide(LoggingEvent var1);

   public Filter getNext() {
      return this.next;
   }

   public void setNext(Filter var1) {
      this.next = var1;
   }
}
