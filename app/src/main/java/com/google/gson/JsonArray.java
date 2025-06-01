package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement implements Iterable {
   private final List elements;

   public JsonArray() {
      this.elements = new ArrayList();
   }

   public JsonArray(int var1) {
      this.elements = new ArrayList(var1);
   }

   public void add(JsonElement var1) {
      Object var2 = var1;
      if (var1 == null) {
         var2 = JsonNull.INSTANCE;
      }

      this.elements.add(var2);
   }

   public void add(Boolean var1) {
      List var2 = this.elements;
      Object var3;
      if (var1 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var1);
      }

      var2.add(var3);
   }

   public void add(Character var1) {
      List var2 = this.elements;
      Object var3;
      if (var1 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var1);
      }

      var2.add(var3);
   }

   public void add(Number var1) {
      List var2 = this.elements;
      Object var3;
      if (var1 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var1);
      }

      var2.add(var3);
   }

   public void add(String var1) {
      List var2 = this.elements;
      Object var3;
      if (var1 == null) {
         var3 = JsonNull.INSTANCE;
      } else {
         var3 = new JsonPrimitive(var1);
      }

      var2.add(var3);
   }

   public void addAll(JsonArray var1) {
      this.elements.addAll(var1.elements);
   }

   public boolean contains(JsonElement var1) {
      return this.elements.contains(var1);
   }

   public JsonArray deepCopy() {
      if (this.elements.isEmpty()) {
         return new JsonArray();
      } else {
         JsonArray var2 = new JsonArray(this.elements.size());
         Iterator var1 = this.elements.iterator();

         while(var1.hasNext()) {
            var2.add(((JsonElement)var1.next()).deepCopy());
         }

         return var2;
      }
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 == this || var1 instanceof JsonArray && ((JsonArray)var1).elements.equals(this.elements)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public JsonElement get(int var1) {
      return (JsonElement)this.elements.get(var1);
   }

   public BigDecimal getAsBigDecimal() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsBigDecimal();
      } else {
         throw new IllegalStateException();
      }
   }

   public BigInteger getAsBigInteger() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsBigInteger();
      } else {
         throw new IllegalStateException();
      }
   }

   public boolean getAsBoolean() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsBoolean();
      } else {
         throw new IllegalStateException();
      }
   }

   public byte getAsByte() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsByte();
      } else {
         throw new IllegalStateException();
      }
   }

   public char getAsCharacter() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsCharacter();
      } else {
         throw new IllegalStateException();
      }
   }

   public double getAsDouble() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsDouble();
      } else {
         throw new IllegalStateException();
      }
   }

   public float getAsFloat() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsFloat();
      } else {
         throw new IllegalStateException();
      }
   }

   public int getAsInt() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsInt();
      } else {
         throw new IllegalStateException();
      }
   }

   public long getAsLong() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsLong();
      } else {
         throw new IllegalStateException();
      }
   }

   public Number getAsNumber() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsNumber();
      } else {
         throw new IllegalStateException();
      }
   }

   public short getAsShort() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsShort();
      } else {
         throw new IllegalStateException();
      }
   }

   public String getAsString() {
      if (this.elements.size() == 1) {
         return ((JsonElement)this.elements.get(0)).getAsString();
      } else {
         throw new IllegalStateException();
      }
   }

   public int hashCode() {
      return this.elements.hashCode();
   }

   public Iterator iterator() {
      return this.elements.iterator();
   }

   public JsonElement remove(int var1) {
      return (JsonElement)this.elements.remove(var1);
   }

   public boolean remove(JsonElement var1) {
      return this.elements.remove(var1);
   }

   public JsonElement set(int var1, JsonElement var2) {
      return (JsonElement)this.elements.set(var1, var2);
   }

   public int size() {
      return this.elements.size();
   }
}
