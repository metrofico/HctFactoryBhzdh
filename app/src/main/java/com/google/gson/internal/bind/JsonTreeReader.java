package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public final class JsonTreeReader extends JsonReader {
   private static final Object SENTINEL_CLOSED = new Object();
   private static final Reader UNREADABLE_READER = new Reader() {
      public void close() throws IOException {
         throw new AssertionError();
      }

      public int read(char[] var1, int var2, int var3) throws IOException {
         throw new AssertionError();
      }
   };
   private int[] pathIndices = new int[32];
   private String[] pathNames = new String[32];
   private Object[] stack = new Object[32];
   private int stackSize = 0;

   public JsonTreeReader(JsonElement var1) {
      super(UNREADABLE_READER);
      this.push(var1);
   }

   private void expect(JsonToken var1) throws IOException {
      if (this.peek() != var1) {
         throw new IllegalStateException("Expected " + var1 + " but was " + this.peek() + this.locationString());
      }
   }

   private String locationString() {
      return " at path " + this.getPath();
   }

   private Object peekStack() {
      return this.stack[this.stackSize - 1];
   }

   private Object popStack() {
      Object[] var2 = this.stack;
      int var1 = this.stackSize - 1;
      this.stackSize = var1;
      Object var3 = var2[var1];
      var2[var1] = null;
      return var3;
   }

   private void push(Object var1) {
      int var2 = this.stackSize;
      Object[] var3 = this.stack;
      if (var2 == var3.length) {
         var2 *= 2;
         this.stack = Arrays.copyOf(var3, var2);
         this.pathIndices = Arrays.copyOf(this.pathIndices, var2);
         this.pathNames = (String[])Arrays.copyOf(this.pathNames, var2);
      }

      var3 = this.stack;
      var2 = this.stackSize++;
      var3[var2] = var1;
   }

   public void beginArray() throws IOException {
      this.expect(JsonToken.BEGIN_ARRAY);
      this.push(((JsonArray)this.peekStack()).iterator());
      this.pathIndices[this.stackSize - 1] = 0;
   }

   public void beginObject() throws IOException {
      this.expect(JsonToken.BEGIN_OBJECT);
      this.push(((JsonObject)this.peekStack()).entrySet().iterator());
   }

   public void close() throws IOException {
      this.stack = new Object[]{SENTINEL_CLOSED};
      this.stackSize = 1;
   }

   public void endArray() throws IOException {
      this.expect(JsonToken.END_ARRAY);
      this.popStack();
      this.popStack();
      int var1 = this.stackSize;
      if (var1 > 0) {
         int[] var2 = this.pathIndices;
         --var1;
         int var10002 = var2[var1]++;
      }

   }

   public void endObject() throws IOException {
      this.expect(JsonToken.END_OBJECT);
      this.popStack();
      this.popStack();
      int var1 = this.stackSize;
      if (var1 > 0) {
         int[] var2 = this.pathIndices;
         --var1;
         int var10002 = var2[var1]++;
      }

   }

   public String getPath() {
      StringBuilder var3 = (new StringBuilder()).append('$');

      int var1;
      for(int var2 = 0; var2 < this.stackSize; var2 = var1 + 1) {
         Object[] var4 = this.stack;
         Object var5 = var4[var2];
         if (var5 instanceof JsonArray) {
            ++var2;
            var1 = var2;
            if (var4[var2] instanceof Iterator) {
               var3.append('[').append(this.pathIndices[var2]).append(']');
               var1 = var2;
            }
         } else {
            var1 = var2;
            if (var5 instanceof JsonObject) {
               ++var2;
               var1 = var2;
               if (var4[var2] instanceof Iterator) {
                  var3.append('.');
                  String var6 = this.pathNames[var2];
                  var1 = var2;
                  if (var6 != null) {
                     var3.append(var6);
                     var1 = var2;
                  }
               }
            }
         }
      }

      return var3.toString();
   }

   public boolean hasNext() throws IOException {
      JsonToken var2 = this.peek();
      boolean var1;
      if (var2 != JsonToken.END_OBJECT && var2 != JsonToken.END_ARRAY) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean nextBoolean() throws IOException {
      this.expect(JsonToken.BOOLEAN);
      boolean var2 = ((JsonPrimitive)this.popStack()).getAsBoolean();
      int var1 = this.stackSize;
      if (var1 > 0) {
         int[] var3 = this.pathIndices;
         --var1;
         int var10002 = var3[var1]++;
      }

      return var2;
   }

   public double nextDouble() throws IOException {
      JsonToken var4 = this.peek();
      if (var4 != JsonToken.NUMBER && var4 != JsonToken.STRING) {
         throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + var4 + this.locationString());
      } else {
         double var1 = ((JsonPrimitive)this.peekStack()).getAsDouble();
         if (!this.isLenient() && (Double.isNaN(var1) || Double.isInfinite(var1))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + var1);
         } else {
            this.popStack();
            int var3 = this.stackSize;
            if (var3 > 0) {
               int[] var5 = this.pathIndices;
               --var3;
               int var10002 = var5[var3]++;
            }

            return var1;
         }
      }
   }

   public int nextInt() throws IOException {
      JsonToken var3 = this.peek();
      if (var3 != JsonToken.NUMBER && var3 != JsonToken.STRING) {
         throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + var3 + this.locationString());
      } else {
         int var1 = ((JsonPrimitive)this.peekStack()).getAsInt();
         this.popStack();
         int var2 = this.stackSize;
         if (var2 > 0) {
            int[] var4 = this.pathIndices;
            --var2;
            int var10002 = var4[var2]++;
         }

         return var1;
      }
   }

   public long nextLong() throws IOException {
      JsonToken var4 = this.peek();
      if (var4 != JsonToken.NUMBER && var4 != JsonToken.STRING) {
         throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + var4 + this.locationString());
      } else {
         long var2 = ((JsonPrimitive)this.peekStack()).getAsLong();
         this.popStack();
         int var1 = this.stackSize;
         if (var1 > 0) {
            int[] var5 = this.pathIndices;
            --var1;
            int var10002 = var5[var1]++;
         }

         return var2;
      }
   }

   public String nextName() throws IOException {
      this.expect(JsonToken.NAME);
      Map.Entry var1 = (Map.Entry)((Iterator)this.peekStack()).next();
      String var2 = (String)var1.getKey();
      this.pathNames[this.stackSize - 1] = var2;
      this.push(var1.getValue());
      return var2;
   }

   public void nextNull() throws IOException {
      this.expect(JsonToken.NULL);
      this.popStack();
      int var1 = this.stackSize;
      if (var1 > 0) {
         int[] var2 = this.pathIndices;
         --var1;
         int var10002 = var2[var1]++;
      }

   }

   public String nextString() throws IOException {
      JsonToken var2 = this.peek();
      if (var2 != JsonToken.STRING && var2 != JsonToken.NUMBER) {
         throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + var2 + this.locationString());
      } else {
         String var4 = ((JsonPrimitive)this.popStack()).getAsString();
         int var1 = this.stackSize;
         if (var1 > 0) {
            int[] var3 = this.pathIndices;
            --var1;
            int var10002 = var3[var1]++;
         }

         return var4;
      }
   }

   public JsonToken peek() throws IOException {
      if (this.stackSize == 0) {
         return JsonToken.END_DOCUMENT;
      } else {
         Object var2 = this.peekStack();
         if (var2 instanceof Iterator) {
            boolean var1 = this.stack[this.stackSize - 2] instanceof JsonObject;
            Iterator var4 = (Iterator)var2;
            if (var4.hasNext()) {
               if (var1) {
                  return JsonToken.NAME;
               } else {
                  this.push(var4.next());
                  return this.peek();
               }
            } else {
               JsonToken var5;
               if (var1) {
                  var5 = JsonToken.END_OBJECT;
               } else {
                  var5 = JsonToken.END_ARRAY;
               }

               return var5;
            }
         } else if (var2 instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
         } else if (var2 instanceof JsonArray) {
            return JsonToken.BEGIN_ARRAY;
         } else if (var2 instanceof JsonPrimitive) {
            JsonPrimitive var3 = (JsonPrimitive)var2;
            if (var3.isString()) {
               return JsonToken.STRING;
            } else if (var3.isBoolean()) {
               return JsonToken.BOOLEAN;
            } else if (var3.isNumber()) {
               return JsonToken.NUMBER;
            } else {
               throw new AssertionError();
            }
         } else if (var2 instanceof JsonNull) {
            return JsonToken.NULL;
         } else if (var2 == SENTINEL_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
         } else {
            throw new AssertionError();
         }
      }
   }

   public void promoteNameToValue() throws IOException {
      this.expect(JsonToken.NAME);
      Map.Entry var1 = (Map.Entry)((Iterator)this.peekStack()).next();
      this.push(var1.getValue());
      this.push(new JsonPrimitive((String)var1.getKey()));
   }

   public void skipValue() throws IOException {
      int var1;
      if (this.peek() == JsonToken.NAME) {
         this.nextName();
         this.pathNames[this.stackSize - 2] = "null";
      } else {
         this.popStack();
         var1 = this.stackSize;
         if (var1 > 0) {
            this.pathNames[var1 - 1] = "null";
         }
      }

      var1 = this.stackSize;
      if (var1 > 0) {
         int[] var2 = this.pathIndices;
         --var1;
         int var10002 = var2[var1]++;
      }

   }

   public String toString() {
      return this.getClass().getSimpleName();
   }
}
