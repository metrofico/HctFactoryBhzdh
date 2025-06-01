package org.apache.log4j;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class PatternLayout extends Layout {
   public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";
   public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %x - %m%n";
   protected final int BUF_SIZE;
   protected final int MAX_CAPACITY;
   private PatternConverter head;
   private String pattern;
   private StringBuffer sbuf;
   private String timezone;

   public PatternLayout() {
      this("%m%n");
   }

   public PatternLayout(String var1) {
      this.BUF_SIZE = 256;
      this.MAX_CAPACITY = 1024;
      this.sbuf = new StringBuffer(256);
      this.pattern = var1;
      String var2 = var1;
      if (var1 == null) {
         var2 = "%m%n";
      }

      this.head = this.createPatternParser(var2).parse();
   }

   public void activateOptions() {
   }

   protected PatternParser createPatternParser(String var1) {
      return new PatternParser(var1);
   }

   public String format(LoggingEvent var1) {
      if (this.sbuf.capacity() > 1024) {
         this.sbuf = new StringBuffer(256);
      } else {
         this.sbuf.setLength(0);
      }

      for(PatternConverter var2 = this.head; var2 != null; var2 = var2.next) {
         var2.format(this.sbuf, var1);
      }

      return this.sbuf.toString();
   }

   public String getConversionPattern() {
      return this.pattern;
   }

   public boolean ignoresThrowable() {
      return true;
   }

   public void setConversionPattern(String var1) {
      this.pattern = var1;
      this.head = this.createPatternParser(var1).parse();
   }
}
