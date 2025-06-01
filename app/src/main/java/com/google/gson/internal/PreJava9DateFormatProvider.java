package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider {
   private static String getDateFormatPattern(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 == 3) {
                  return "M/d/yy";
               } else {
                  throw new IllegalArgumentException("Unknown DateFormat style: " + var0);
               }
            } else {
               return "MMM d, y";
            }
         } else {
            return "MMMM d, y";
         }
      } else {
         return "EEEE, MMMM d, y";
      }
   }

   private static String getDatePartOfDateTimePattern(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 == 3) {
                  return "M/d/yy";
               } else {
                  throw new IllegalArgumentException("Unknown DateFormat style: " + var0);
               }
            } else {
               return "MMM d, yyyy";
            }
         } else {
            return "MMMM d, yyyy";
         }
      } else {
         return "EEEE, MMMM d, yyyy";
      }
   }

   private static String getTimePartOfDateTimePattern(int var0) {
      if (var0 != 0 && var0 != 1) {
         if (var0 != 2) {
            if (var0 == 3) {
               return "h:mm a";
            } else {
               throw new IllegalArgumentException("Unknown DateFormat style: " + var0);
            }
         } else {
            return "h:mm:ss a";
         }
      } else {
         return "h:mm:ss a z";
      }
   }

   public static DateFormat getUSDateFormat(int var0) {
      return new SimpleDateFormat(getDateFormatPattern(var0), Locale.US);
   }

   public static DateFormat getUSDateTimeFormat(int var0, int var1) {
      return new SimpleDateFormat(getDatePartOfDateTimePattern(var0) + " " + getTimePartOfDateTimePattern(var1), Locale.US);
   }
}
