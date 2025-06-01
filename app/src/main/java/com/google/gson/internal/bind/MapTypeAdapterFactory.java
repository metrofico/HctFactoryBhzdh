package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class MapTypeAdapterFactory implements TypeAdapterFactory {
   final boolean complexMapKeySerialization;
   private final ConstructorConstructor constructorConstructor;

   public MapTypeAdapterFactory(ConstructorConstructor var1, boolean var2) {
      this.constructorConstructor = var1;
      this.complexMapKeySerialization = var2;
   }

   private TypeAdapter getKeyAdapter(Gson var1, Type var2) {
      TypeAdapter var3;
      if (var2 != Boolean.TYPE && var2 != Boolean.class) {
         var3 = var1.getAdapter(TypeToken.get(var2));
      } else {
         var3 = TypeAdapters.BOOLEAN_AS_STRING;
      }

      return var3;
   }

   public TypeAdapter create(Gson var1, TypeToken var2) {
      Type var3 = var2.getType();
      if (!Map.class.isAssignableFrom(var2.getRawType())) {
         return null;
      } else {
         Type[] var7 = $Gson$Types.getMapKeyAndValueTypes(var3, $Gson$Types.getRawType(var3));
         TypeAdapter var4 = this.getKeyAdapter(var1, var7[0]);
         TypeAdapter var5 = var1.getAdapter(TypeToken.get(var7[1]));
         ObjectConstructor var6 = this.constructorConstructor.get(var2);
         return new Adapter(this, var1, var7[0], var4, var7[1], var5, var6);
      }
   }

   private final class Adapter extends TypeAdapter {
      private final ObjectConstructor constructor;
      private final TypeAdapter keyTypeAdapter;
      final MapTypeAdapterFactory this$0;
      private final TypeAdapter valueTypeAdapter;

      public Adapter(MapTypeAdapterFactory var1, Gson var2, Type var3, TypeAdapter var4, Type var5, TypeAdapter var6, ObjectConstructor var7) {
         this.this$0 = var1;
         this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(var2, var4, var3);
         this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(var2, var6, var5);
         this.constructor = var7;
      }

      private String keyToString(JsonElement var1) {
         if (var1.isJsonPrimitive()) {
            JsonPrimitive var2 = var1.getAsJsonPrimitive();
            if (var2.isNumber()) {
               return String.valueOf(var2.getAsNumber());
            } else if (var2.isBoolean()) {
               return Boolean.toString(var2.getAsBoolean());
            } else if (var2.isString()) {
               return var2.getAsString();
            } else {
               throw new AssertionError();
            }
         } else if (var1.isJsonNull()) {
            return "null";
         } else {
            throw new AssertionError();
         }
      }

      public Map read(JsonReader var1) throws IOException {
         JsonToken var3 = var1.peek();
         if (var3 == JsonToken.NULL) {
            var1.nextNull();
            return null;
         } else {
            Map var2 = (Map)this.constructor.construct();
            Object var4;
            if (var3 == JsonToken.BEGIN_ARRAY) {
               var1.beginArray();

               while(var1.hasNext()) {
                  var1.beginArray();
                  var4 = this.keyTypeAdapter.read(var1);
                  if (var2.put(var4, this.valueTypeAdapter.read(var1)) != null) {
                     throw new JsonSyntaxException("duplicate key: " + var4);
                  }

                  var1.endArray();
               }

               var1.endArray();
            } else {
               var1.beginObject();

               while(var1.hasNext()) {
                  JsonReaderInternalAccess.INSTANCE.promoteNameToValue(var1);
                  var4 = this.keyTypeAdapter.read(var1);
                  if (var2.put(var4, this.valueTypeAdapter.read(var1)) != null) {
                     throw new JsonSyntaxException("duplicate key: " + var4);
                  }
               }

               var1.endObject();
            }

            return var2;
         }
      }

      public void write(JsonWriter var1, Map var2) throws IOException {
         if (var2 == null) {
            var1.nullValue();
         } else {
            Iterator var11;
            if (!this.this$0.complexMapKeySerialization) {
               var1.beginObject();
               var11 = var2.entrySet().iterator();

               while(var11.hasNext()) {
                  Map.Entry var14 = (Map.Entry)var11.next();
                  var1.name(String.valueOf(var14.getKey()));
                  this.valueTypeAdapter.write(var1, var14.getValue());
               }

               var1.endObject();
            } else {
               ArrayList var8 = new ArrayList(var2.size());
               ArrayList var7 = new ArrayList(var2.size());
               var11 = var2.entrySet().iterator();
               byte var5 = 0;
               byte var6 = 0;

               boolean var3;
               boolean var4;
               for(var3 = false; var11.hasNext(); var3 |= var4) {
                  Map.Entry var9 = (Map.Entry)var11.next();
                  JsonElement var10 = this.keyTypeAdapter.toJsonTree(var9.getKey());
                  var8.add(var10);
                  var7.add(var9.getValue());
                  if (!var10.isJsonArray() && !var10.isJsonObject()) {
                     var4 = false;
                  } else {
                     var4 = true;
                  }
               }

               int var12;
               int var13;
               if (var3) {
                  var1.beginArray();
                  var13 = var8.size();

                  for(var12 = var6; var12 < var13; ++var12) {
                     var1.beginArray();
                     Streams.write((JsonElement)var8.get(var12), var1);
                     this.valueTypeAdapter.write(var1, var7.get(var12));
                     var1.endArray();
                  }

                  var1.endArray();
               } else {
                  var1.beginObject();
                  var13 = var8.size();

                  for(var12 = var5; var12 < var13; ++var12) {
                     var1.name(this.keyToString((JsonElement)var8.get(var12)));
                     this.valueTypeAdapter.write(var1, var7.get(var12));
                  }

                  var1.endObject();
               }

            }
         }
      }
   }
}
