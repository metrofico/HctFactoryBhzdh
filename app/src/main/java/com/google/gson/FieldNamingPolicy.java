package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;

public enum FieldNamingPolicy implements FieldNamingStrategy {
   private static final FieldNamingPolicy[] $VALUES;
   IDENTITY,
   LOWER_CASE_WITH_DASHES,
   LOWER_CASE_WITH_DOTS,
   LOWER_CASE_WITH_UNDERSCORES,
   UPPER_CAMEL_CASE,
   UPPER_CAMEL_CASE_WITH_SPACES;

   static {
      FieldNamingPolicy var0 = new FieldNamingPolicy("IDENTITY", 0) {
         public String translateName(Field var1) {
            return var1.getName();
         }
      };
      IDENTITY = var0;
      FieldNamingPolicy var3 = new FieldNamingPolicy("UPPER_CAMEL_CASE", 1) {
         public String translateName(Field var1) {
            return upperCaseFirstLetter(var1.getName());
         }
      };
      UPPER_CAMEL_CASE = var3;
      FieldNamingPolicy var2 = new FieldNamingPolicy("UPPER_CAMEL_CASE_WITH_SPACES", 2) {
         public String translateName(Field var1) {
            return upperCaseFirstLetter(separateCamelCase(var1.getName(), " "));
         }
      };
      UPPER_CAMEL_CASE_WITH_SPACES = var2;
      FieldNamingPolicy var1 = new FieldNamingPolicy("LOWER_CASE_WITH_UNDERSCORES", 3) {
         public String translateName(Field var1) {
            return separateCamelCase(var1.getName(), "_").toLowerCase(Locale.ENGLISH);
         }
      };
      LOWER_CASE_WITH_UNDERSCORES = var1;
      FieldNamingPolicy var5 = new FieldNamingPolicy("LOWER_CASE_WITH_DASHES", 4) {
         public String translateName(Field var1) {
            return separateCamelCase(var1.getName(), "-").toLowerCase(Locale.ENGLISH);
         }
      };
      LOWER_CASE_WITH_DASHES = var5;
      FieldNamingPolicy var4 = new FieldNamingPolicy("LOWER_CASE_WITH_DOTS", 5) {
         public String translateName(Field var1) {
            return separateCamelCase(var1.getName(), ".").toLowerCase(Locale.ENGLISH);
         }
      };
      LOWER_CASE_WITH_DOTS = var4;
      $VALUES = new FieldNamingPolicy[]{var0, var3, var2, var1, var5, var4};
   }

   private FieldNamingPolicy() {
   }

   // $FF: synthetic method
   FieldNamingPolicy(Object var3) {
      this();
   }

   static String separateCamelCase(String var0, String var1) {
      StringBuilder var5 = new StringBuilder();
      int var4 = var0.length();

      for(int var3 = 0; var3 < var4; ++var3) {
         char var2 = var0.charAt(var3);
         if (Character.isUpperCase(var2) && var5.length() != 0) {
            var5.append(var1);
         }

         var5.append(var2);
      }

      return var5.toString();
   }

   static String upperCaseFirstLetter(String var0) {
      int var3 = var0.length();

      int var2;
      for(var2 = 0; !Character.isLetter(var0.charAt(var2)) && var2 < var3 - 1; ++var2) {
      }

      char var1 = var0.charAt(var2);
      if (Character.isUpperCase(var1)) {
         return var0;
      } else {
         var1 = Character.toUpperCase(var1);
         return var2 == 0 ? var1 + var0.substring(1) : var0.substring(0, var2) + var1 + var0.substring(var2 + 1);
      }
   }
}
