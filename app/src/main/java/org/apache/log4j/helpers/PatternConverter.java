package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public abstract class PatternConverter {
   static String[] SPACES = new String[]{" ", "  ", "    ", "        ", "                ", "                                "};
   boolean leftAlign = false;
   int max = Integer.MAX_VALUE;
   int min = -1;
   public PatternConverter next;

   protected PatternConverter() {
   }

   protected PatternConverter(FormattingInfo var1) {
      this.min = var1.min;
      this.max = var1.max;
      this.leftAlign = var1.leftAlign;
   }

   protected abstract String convert(LoggingEvent var1);

   public void format(StringBuffer var1, LoggingEvent var2) {
      String var5 = this.convert(var2);
      int var3;
      if (var5 == null) {
         var3 = this.min;
         if (var3 > 0) {
            this.spacePad(var1, var3);
         }

      } else {
         var3 = var5.length();
         int var4 = this.max;
         if (var3 > var4) {
            var1.append(var5.substring(var3 - var4));
         } else {
            var4 = this.min;
            if (var3 < var4) {
               if (this.leftAlign) {
                  var1.append(var5);
                  this.spacePad(var1, this.min - var3);
               } else {
                  this.spacePad(var1, var4 - var3);
                  var1.append(var5);
               }
            } else {
               var1.append(var5);
            }
         }

      }
   }

   public void spacePad(StringBuffer var1, int var2) {
      while(var2 >= 32) {
         var1.append(SPACES[5]);
         var2 -= 32;
      }

      for(int var3 = 4; var3 >= 0; --var3) {
         if ((1 << var3 & var2) != 0) {
            var1.append(SPACES[var3]);
         }
      }

   }
}
