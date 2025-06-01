package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class ArrayTypeAdapter extends TypeAdapter {
   public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
      public TypeAdapter create(Gson var1, TypeToken var2) {
         Type var3 = var2.getType();
         if (var3 instanceof GenericArrayType || var3 instanceof Class && ((Class)var3).isArray()) {
            var3 = $Gson$Types.getArrayComponentType(var3);
            return new ArrayTypeAdapter(var1, var1.getAdapter(TypeToken.get(var3)), $Gson$Types.getRawType(var3));
         } else {
            return null;
         }
      }
   };
   private final Class componentType;
   private final TypeAdapter componentTypeAdapter;

   public ArrayTypeAdapter(Gson var1, TypeAdapter var2, Class var3) {
      this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(var1, var2, var3);
      this.componentType = var3;
   }

   public Object read(JsonReader var1) throws IOException {
      if (var1.peek() == JsonToken.NULL) {
         var1.nextNull();
         return null;
      } else {
         ArrayList var4 = new ArrayList();
         var1.beginArray();

         while(var1.hasNext()) {
            var4.add(this.componentTypeAdapter.read(var1));
         }

         var1.endArray();
         int var3 = var4.size();
         Object var5 = Array.newInstance(this.componentType, var3);

         for(int var2 = 0; var2 < var3; ++var2) {
            Array.set(var5, var2, var4.get(var2));
         }

         return var5;
      }
   }

   public void write(JsonWriter var1, Object var2) throws IOException {
      if (var2 == null) {
         var1.nullValue();
      } else {
         var1.beginArray();
         int var3 = 0;

         for(int var4 = Array.getLength(var2); var3 < var4; ++var3) {
            Object var5 = Array.get(var2, var3);
            this.componentTypeAdapter.write(var1, var5);
         }

         var1.endArray();
      }
   }
}
