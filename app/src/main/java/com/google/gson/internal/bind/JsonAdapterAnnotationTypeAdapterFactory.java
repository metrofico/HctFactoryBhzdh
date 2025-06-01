package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
   private final ConstructorConstructor constructorConstructor;

   public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor var1) {
      this.constructorConstructor = var1;
   }

   public TypeAdapter create(Gson var1, TypeToken var2) {
      JsonAdapter var3 = (JsonAdapter)var2.getRawType().getAnnotation(JsonAdapter.class);
      return var3 == null ? null : this.getTypeAdapter(this.constructorConstructor, var1, var2, var3);
   }

   TypeAdapter getTypeAdapter(ConstructorConstructor var1, Gson var2, TypeToken var3, JsonAdapter var4) {
      Object var7 = var1.get(TypeToken.get(var4.value())).construct();
      Object var8;
      if (var7 instanceof TypeAdapter) {
         var8 = (TypeAdapter)var7;
      } else if (var7 instanceof TypeAdapterFactory) {
         var8 = ((TypeAdapterFactory)var7).create(var2, var3);
      } else {
         boolean var5 = var7 instanceof JsonSerializer;
         if (!var5 && !(var7 instanceof JsonDeserializer)) {
            throw new IllegalArgumentException("Invalid attempt to bind an instance of " + var7.getClass().getName() + " as a @JsonAdapter for " + var3.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
         }

         JsonDeserializer var6 = null;
         JsonSerializer var9;
         if (var5) {
            var9 = (JsonSerializer)var7;
         } else {
            var9 = null;
         }

         if (var7 instanceof JsonDeserializer) {
            var6 = (JsonDeserializer)var7;
         }

         var8 = new TreeTypeAdapter(var9, var6, var2, var3, (TypeAdapterFactory)null);
      }

      Object var10 = var8;
      if (var8 != null) {
         var10 = var8;
         if (var4.nullSafe()) {
            var10 = ((TypeAdapter)var8).nullSafe();
         }
      }

      return (TypeAdapter)var10;
   }
}
