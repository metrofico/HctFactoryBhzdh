package org.apache.log4j.helpers;

public class FormattingInfo {
   boolean leftAlign = false;
   int max = Integer.MAX_VALUE;
   int min = -1;

   void dump() {
      LogLog.debug("min=" + this.min + ", max=" + this.max + ", leftAlign=" + this.leftAlign);
   }

   void reset() {
      this.min = -1;
      this.max = Integer.MAX_VALUE;
      this.leftAlign = false;
   }
}
