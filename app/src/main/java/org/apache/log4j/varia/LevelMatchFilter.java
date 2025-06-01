package org.apache.log4j.varia;

import org.apache.log4j.Level;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class LevelMatchFilter extends Filter {
   boolean acceptOnMatch = true;
   Level levelToMatch;

   public int decide(LoggingEvent var1) {
      Level var2 = this.levelToMatch;
      if (var2 == null) {
         return 0;
      } else if (var2.equals(var1.getLevel())) {
         return this.acceptOnMatch ? 1 : -1;
      } else {
         return 0;
      }
   }

   public boolean getAcceptOnMatch() {
      return this.acceptOnMatch;
   }

   public String getLevelToMatch() {
      Level var1 = this.levelToMatch;
      String var2;
      if (var1 == null) {
         var2 = null;
      } else {
         var2 = var1.toString();
      }

      return var2;
   }

   public void setAcceptOnMatch(boolean var1) {
      this.acceptOnMatch = var1;
   }

   public void setLevelToMatch(String var1) {
      this.levelToMatch = OptionConverter.toLevel(var1, (Level)null);
   }
}
