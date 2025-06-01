package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public final class TreeTypeAdapter extends TypeAdapter {
   private final GsonContextImpl context = new GsonContextImpl(this);
   private TypeAdapter delegate;
   private final JsonDeserializer deserializer;
   final Gson gson;
   private final JsonSerializer serializer;
   private final TypeAdapterFactory skipPast;
   private final TypeToken typeToken;

   public TreeTypeAdapter(JsonSerializer var1, JsonDeserializer var2, Gson var3, TypeToken var4, TypeAdapterFactory var5) {
      this.serializer = var1;
      this.deserializer = var2;
      this.gson = var3;
      this.typeToken = var4;
      this.skipPast = var5;
   }

   private TypeAdapter delegate() {
      TypeAdapter var1 = this.delegate;
      if (var1 == null) {
         var1 = this.gson.getDelegateAdapter(this.skipPast, this.typeToken);
         this.delegate = var1;
      }

      return var1;
   }

   public static TypeAdapterFactory newFactory(TypeToken var0, Object var1) {
      return new SingleTypeFactory(var1, var0, false, (Class)null);
   }

   public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken var0, Object var1) {
      boolean var2;
      if (var0.getType() == var0.getRawType()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return new SingleTypeFactory(var1, var0, var2, (Class)null);
   }

   public static TypeAdapterFactory newTypeHierarchyFactory(Class var0, Object var1) {
      return new SingleTypeFactory(var1, (TypeToken)null, false, var0);
   }

   public Object read(JsonReader var1) throws IOException {
      if (this.deserializer == null) {
         return this.delegate().read(var1);
      } else {
         JsonElement var2 = Streams.parse(var1);
         return var2.isJsonNull() ? null : this.deserializer.deserialize(var2, this.typeToken.getType(), this.context);
      }
   }

   public void write(JsonWriter var1, Object var2) throws IOException {
      JsonSerializer var3 = this.serializer;
      if (var3 == null) {
         this.delegate().write(var1, var2);
      } else if (var2 == null) {
         var1.nullValue();
      } else {
         Streams.write(var3.serialize(var2, this.typeToken.getType(), this.context), var1);
      }
   }

   private final class GsonContextImpl implements JsonSerializationContext, JsonDeserializationContext {
      final TreeTypeAdapter this$0;

      private GsonContextImpl(TreeTypeAdapter var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      GsonContextImpl(TreeTypeAdapter var1, Object var2) {
         this(var1);
      }

      public Object deserialize(JsonElement var1, Type var2) throws JsonParseException {
         return this.this$0.gson.fromJson(var1, var2);
      }

      public JsonElement serialize(Object var1) {
         return this.this$0.gson.toJsonTree(var1);
      }

      public JsonElement serialize(Object var1, Type var2) {
         return this.this$0.gson.toJsonTree(var1, var2);
      }
   }

   private static final class SingleTypeFactory implements TypeAdapterFactory {
      private final JsonDeserializer deserializer;
      private final TypeToken exactType;
      private final Class hierarchyType;
      private final boolean matchRawType;
      private final JsonSerializer serializer;

      SingleTypeFactory(Object var1, TypeToken var2, boolean var3, Class var4) {
         boolean var5 = var1 instanceof JsonSerializer;
         JsonDeserializer var7 = null;
         JsonSerializer var6;
         if (var5) {
            var6 = (JsonSerializer)var1;
         } else {
            var6 = null;
         }

         this.serializer = var6;
         if (var1 instanceof JsonDeserializer) {
            var7 = (JsonDeserializer)var1;
         }

         this.deserializer = var7;
         if (var6 == null && var7 == null) {
            var5 = false;
         } else {
            var5 = true;
         }

         $Gson$Preconditions.checkArgument(var5);
         this.exactType = var2;
         this.matchRawType = var3;
         this.hierarchyType = var4;
      }

      public TypeAdapter create(Gson var1, TypeToken var2) {
         TypeToken var4 = this.exactType;
         boolean var3;
         if (var4 != null) {
            if (var4.equals(var2) || this.matchRawType && this.exactType.getType() == var2.getRawType()) {
               var3 = true;
            } else {
               var3 = false;
            }
         } else {
            var3 = this.hierarchyType.isAssignableFrom(var2.getRawType());
         }

         TreeTypeAdapter var5;
         if (var3) {
            var5 = new TreeTypeAdapter(this.serializer, this.deserializer, var1, var2, this);
         } else {
            var5 = null;
         }

         return var5;
      }
   }
}
