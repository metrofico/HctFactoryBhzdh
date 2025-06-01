package com.google.gson;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive extends JsonElement {
   private final Object value;

   public JsonPrimitive(Boolean var1) {
      this.value = $Gson$Preconditions.checkNotNull(var1);
   }

   public JsonPrimitive(Character var1) {
      this.value = ((Character)$Gson$Preconditions.checkNotNull(var1)).toString();
   }

   public JsonPrimitive(Number var1) {
      this.value = $Gson$Preconditions.checkNotNull(var1);
   }

   public JsonPrimitive(String var1) {
      this.value = $Gson$Preconditions.checkNotNull(var1);
   }

   private static boolean isIntegral(JsonPrimitive var0) {
      Object var4 = var0.value;
      boolean var3 = var4 instanceof Number;
      boolean var2 = false;
      boolean var1 = var2;
      if (var3) {
         Number var5 = (Number)var4;
         if (!(var5 instanceof BigInteger) && !(var5 instanceof Long) && !(var5 instanceof Integer) && !(var5 instanceof Short)) {
            var1 = var2;
            if (!(var5 instanceof Byte)) {
               return var1;
            }
         }

         var1 = true;
      }

      return var1;
   }

   public JsonPrimitive deepCopy() {
      return this;
   }

   public boolean equals(Object var1) {
      boolean var8 = true;
      boolean var7 = true;
      boolean var6 = true;
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         JsonPrimitive var10 = (JsonPrimitive)var1;
         if (this.value == null) {
            if (var10.value != null) {
               var6 = false;
            }

            return var6;
         } else if (isIntegral(this) && isIntegral(var10)) {
            if (this.getAsNumber().longValue() == var10.getAsNumber().longValue()) {
               var6 = var8;
            } else {
               var6 = false;
            }

            return var6;
         } else {
            Object var9 = this.value;
            if (var9 instanceof Number && var10.value instanceof Number) {
               double var2 = this.getAsNumber().doubleValue();
               double var4 = var10.getAsNumber().doubleValue();
               var6 = var7;
               if (var2 != var4) {
                  if (Double.isNaN(var2) && Double.isNaN(var4)) {
                     var6 = var7;
                  } else {
                     var6 = false;
                  }
               }

               return var6;
            } else {
               return var9.equals(var10.value);
            }
         }
      } else {
         return false;
      }
   }

   public BigDecimal getAsBigDecimal() {
      Object var1 = this.value;
      BigDecimal var2;
      if (var1 instanceof BigDecimal) {
         var2 = (BigDecimal)var1;
      } else {
         var2 = new BigDecimal(this.value.toString());
      }

      return var2;
   }

   public BigInteger getAsBigInteger() {
      Object var1 = this.value;
      BigInteger var2;
      if (var1 instanceof BigInteger) {
         var2 = (BigInteger)var1;
      } else {
         var2 = new BigInteger(this.value.toString());
      }

      return var2;
   }

   public boolean getAsBoolean() {
      return this.isBoolean() ? (Boolean)this.value : Boolean.parseBoolean(this.getAsString());
   }

   public byte getAsByte() {
      byte var1;
      if (this.isNumber()) {
         var1 = this.getAsNumber().byteValue();
      } else {
         var1 = Byte.parseByte(this.getAsString());
      }

      return var1;
   }

   public char getAsCharacter() {
      return this.getAsString().charAt(0);
   }

   public double getAsDouble() {
      double var1;
      if (this.isNumber()) {
         var1 = this.getAsNumber().doubleValue();
      } else {
         var1 = Double.parseDouble(this.getAsString());
      }

      return var1;
   }

   public float getAsFloat() {
      float var1;
      if (this.isNumber()) {
         var1 = this.getAsNumber().floatValue();
      } else {
         var1 = Float.parseFloat(this.getAsString());
      }

      return var1;
   }

   public int getAsInt() {
      int var1;
      if (this.isNumber()) {
         var1 = this.getAsNumber().intValue();
      } else {
         var1 = Integer.parseInt(this.getAsString());
      }

      return var1;
   }

   public long getAsLong() {
      long var1;
      if (this.isNumber()) {
         var1 = this.getAsNumber().longValue();
      } else {
         var1 = Long.parseLong(this.getAsString());
      }

      return var1;
   }

   public Number getAsNumber() {
      Object var1 = this.value;
      if (var1 instanceof String) {
         var1 = new LazilyParsedNumber((String)this.value);
      } else {
         var1 = (Number)var1;
      }

      return (Number)var1;
   }

   public short getAsShort() {
      short var1;
      if (this.isNumber()) {
         var1 = this.getAsNumber().shortValue();
      } else {
         var1 = Short.parseShort(this.getAsString());
      }

      return var1;
   }

   public String getAsString() {
      if (this.isNumber()) {
         return this.getAsNumber().toString();
      } else {
         return this.isBoolean() ? ((Boolean)this.value).toString() : (String)this.value;
      }
   }

   public int hashCode() {
      if (this.value == null) {
         return 31;
      } else {
         long var1;
         if (isIntegral(this)) {
            var1 = this.getAsNumber().longValue();
         } else {
            Object var3 = this.value;
            if (!(var3 instanceof Number)) {
               return var3.hashCode();
            }

            var1 = Double.doubleToLongBits(this.getAsNumber().doubleValue());
         }

         return (int)(var1 >>> 32 ^ var1);
      }
   }

   public boolean isBoolean() {
      return this.value instanceof Boolean;
   }

   public boolean isNumber() {
      return this.value instanceof Number;
   }

   public boolean isString() {
      return this.value instanceof String;
   }
}
