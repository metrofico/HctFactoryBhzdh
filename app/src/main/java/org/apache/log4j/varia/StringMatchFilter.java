package org.apache.log4j.varia;

import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class StringMatchFilter extends Filter {
   public static final String ACCEPT_ON_MATCH_OPTION = "AcceptOnMatch";
   public static final String STRING_TO_MATCH_OPTION = "StringToMatch";
   boolean acceptOnMatch = true;
   String stringToMatch;

   public int decide(LoggingEvent var1) {
      String var2 = var1.getRenderedMessage();
      if (var2 != null) {
         String var3 = this.stringToMatch;
         if (var3 != null) {
            if (var2.indexOf(var3) == -1) {
               return 0;
            }

            if (this.acceptOnMatch) {
               return 1;
            }

            return -1;
         }
      }

      return 0;
   }

   public boolean getAcceptOnMatch() {
      return this.acceptOnMatch;
   }

   public String[] getOptionStrings() {
      return new String[]{"StringToMatch", "AcceptOnMatch"};
   }

   public String getStringToMatch() {
      return this.stringToMatch;
   }

   public void setAcceptOnMatch(boolean var1) {
      this.acceptOnMatch = var1;
   }

   public void setOption(String var1, String var2) {
      if (var1.equalsIgnoreCase("StringToMatch")) {
         this.stringToMatch = var2;
      } else if (var1.equalsIgnoreCase("AcceptOnMatch")) {
         this.acceptOnMatch = OptionConverter.toBoolean(var2, this.acceptOnMatch);
      }

   }

   public void setStringToMatch(String var1) {
      this.stringToMatch = var1;
   }
}
