package com.android.common;

import android.text.format.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class LegacyHttpDateTime {
   private static final Pattern HTTP_DATE_ANSIC_PATTERN = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");
   private static final String HTTP_DATE_ANSIC_REGEXP = "[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})";
   private static final Pattern HTTP_DATE_RFC_PATTERN = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
   private static final String HTTP_DATE_RFC_REGEXP = "([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])";

   private static int getDate(String var0) {
      return var0.length() == 2 ? (var0.charAt(0) - 48) * 10 + (var0.charAt(1) - 48) : var0.charAt(0) - 48;
   }

   private static int getMonth(String var0) {
      int var1 = Character.toLowerCase(var0.charAt(0)) + Character.toLowerCase(var0.charAt(1)) + Character.toLowerCase(var0.charAt(2)) - 291;
      if (var1 != 9) {
         if (var1 != 10) {
            if (var1 != 22) {
               if (var1 != 26) {
                  if (var1 != 29) {
                     if (var1 != 32) {
                        if (var1 != 40) {
                           if (var1 != 42) {
                              if (var1 != 48) {
                                 switch (var1) {
                                    case 35:
                                       return 9;
                                    case 36:
                                       return 4;
                                    case 37:
                                       return 8;
                                    default:
                                       throw new IllegalArgumentException();
                                 }
                              } else {
                                 return 10;
                              }
                           } else {
                              return 5;
                           }
                        } else {
                           return 6;
                        }
                     } else {
                        return 3;
                     }
                  } else {
                     return 2;
                  }
               } else {
                  return 7;
               }
            } else {
               return 0;
            }
         } else {
            return 1;
         }
      } else {
         return 11;
      }
   }

   private static TimeOfDay getTime(String var0) {
      int var3 = 0 + 1;
      int var4 = var0.charAt(0) - 48;
      int var1 = var4;
      int var2 = var3;
      if (var0.charAt(var3) != ':') {
         var1 = var4 * 10 + (var0.charAt(var3) - 48);
         var2 = var3 + 1;
      }

      ++var2;
      var4 = var2 + 1;
      char var6 = var0.charAt(var2);
      char var7 = var0.charAt(var4);
      int var5 = var4 + 1 + 1;
      var4 = var5 + 1;
      return new TimeOfDay(var1, (var6 - 48) * 10 + (var7 - 48), (var0.charAt(var5) - 48) * 10 + (var0.charAt(var4) - 48));
   }

   private static int getYear(String var0) {
      if (var0.length() == 2) {
         int var1 = (var0.charAt(0) - 48) * 10 + (var0.charAt(1) - 48);
         return var1 >= 70 ? var1 + 1900 : var1 + 2000;
      } else if (var0.length() == 3) {
         return (var0.charAt(0) - 48) * 100 + (var0.charAt(1) - 48) * 10 + (var0.charAt(2) - 48) + 1900;
      } else {
         return var0.length() == 4 ? (var0.charAt(0) - 48) * 1000 + (var0.charAt(1) - 48) * 100 + (var0.charAt(2) - 48) * 10 + (var0.charAt(3) - 48) : 1970;
      }
   }

   public static long parse(String var0) throws IllegalArgumentException {
      Matcher var5 = HTTP_DATE_RFC_PATTERN.matcher(var0);
      int var1;
      int var2;
      int var3;
      TimeOfDay var6;
      if (var5.find()) {
         var3 = getDate(var5.group(1));
         var2 = getMonth(var5.group(2));
         var1 = getYear(var5.group(3));
         var6 = getTime(var5.group(4));
      } else {
         var5 = HTTP_DATE_ANSIC_PATTERN.matcher(var0);
         if (!var5.find()) {
            throw new IllegalArgumentException();
         }

         var2 = getMonth(var5.group(1));
         var3 = getDate(var5.group(2));
         var6 = getTime(var5.group(3));
         var1 = getYear(var5.group(4));
      }

      int var4 = var3;
      var3 = var2;
      var2 = var1;
      if (var1 >= 2038) {
         var2 = 2038;
         var3 = 0;
         var4 = 1;
      }

      Time var7 = new Time("UTC");
      var7.set(var6.second, var6.minute, var6.hour, var4, var3, var2);
      return var7.toMillis(false);
   }

   private static class TimeOfDay {
      int hour;
      int minute;
      int second;

      TimeOfDay(int var1, int var2, int var3) {
         this.hour = var1;
         this.minute = var2;
         this.second = var3;
      }
   }
}
