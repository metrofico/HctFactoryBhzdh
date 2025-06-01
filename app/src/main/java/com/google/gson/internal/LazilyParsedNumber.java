package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber extends Number {
   private final String value;

   public LazilyParsedNumber(String var1) {
      this.value = var1;
   }

   private Object writeReplace() throws ObjectStreamException {
      return new BigDecimal(this.value);
   }

   public double doubleValue() {
      return Double.parseDouble(this.value);
   }

   public boolean equals(Object var1) {
      boolean var3 = true;
      if (this == var1) {
         return true;
      } else if (var1 instanceof LazilyParsedNumber) {
         LazilyParsedNumber var4 = (LazilyParsedNumber)var1;
         String var5 = this.value;
         String var6 = var4.value;
         boolean var2 = var3;
         if (var5 != var6) {
            if (var5.equals(var6)) {
               var2 = var3;
            } else {
               var2 = false;
            }
         }

         return var2;
      } else {
         return false;
      }
   }

   public float floatValue() {
      return Float.parseFloat(this.value);
   }

   public int hashCode() {
      return this.value.hashCode();
   }

   public int intValue() {
      try {
         int var1 = Integer.parseInt(this.value);
         return var1;
      } catch (NumberFormatException var6) {
         long var2;
         try {
            var2 = Long.parseLong(this.value);
         } catch (NumberFormatException var5) {
            return (new BigDecimal(this.value)).intValue();
         }

         return (int)var2;
      }
   }

   public long longValue() {
      try {
         long var1 = Long.parseLong(this.value);
         return var1;
      } catch (NumberFormatException var4) {
         return (new BigDecimal(this.value)).longValue();
      }
   }

   public String toString() {
      return this.value;
   }
}
