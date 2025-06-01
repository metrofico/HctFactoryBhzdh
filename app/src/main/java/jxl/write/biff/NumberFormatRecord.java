package jxl.write.biff;

import jxl.biff.FormatRecord;
import jxl.common.Logger;

public class NumberFormatRecord extends FormatRecord {
   private static Logger logger = Logger.getLogger(NumberFormatRecord.class);

   protected NumberFormatRecord(String var1) {
      this.setFormatString(this.trimInvalidChars(this.replace(var1, "E0", "E+0")));
   }

   protected NumberFormatRecord(String var1, NonValidatingFormat var2) {
      this.setFormatString(this.replace(var1, "E0", "E+0"));
   }

   private String trimInvalidChars(String var1) {
      int var3 = var1.indexOf(35);
      int var4 = var1.indexOf(48);
      if (var3 == -1 && var4 == -1) {
         return "#.###";
      } else {
         String var5 = var1;
         int var2;
         if (var3 != 0) {
            var5 = var1;
            if (var4 != 0) {
               var5 = var1;
               if (var3 != 1) {
                  var5 = var1;
                  if (var4 != 1) {
                     var2 = var3;
                     if (var3 == -1) {
                        var2 = Integer.MAX_VALUE;
                     }

                     var3 = var4;
                     if (var4 == -1) {
                        var3 = Integer.MAX_VALUE;
                     }

                     var2 = Math.min(var2, var3);
                     StringBuffer var6 = new StringBuffer();
                     var6.append(var1.charAt(0));
                     var6.append(var1.substring(var2));
                     var5 = var6.toString();
                  }
               }
            }
         }

         var3 = var5.lastIndexOf(35);
         var2 = var5.lastIndexOf(48);
         var1 = var5;
         if (var3 != var5.length()) {
            if (var2 == var5.length()) {
               var1 = var5;
            } else {
               var2 = Math.max(var3, var2);

               while(true) {
                  var4 = var5.length();
                  var3 = var2 + 1;
                  if (var4 <= var3) {
                     break;
                  }

                  var2 = var3;
                  if (var5.charAt(var3) != ')') {
                     if (var5.charAt(var3) != '%') {
                        break;
                     }

                     var2 = var3;
                  }
               }

               var1 = var5.substring(0, var3);
            }
         }

         return var1;
      }
   }

   protected static class NonValidatingFormat {
      public NonValidatingFormat() {
      }
   }
}
