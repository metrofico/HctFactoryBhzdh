package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class Gson {
   static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
   static final boolean DEFAULT_ESCAPE_HTML = true;
   static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
   static final boolean DEFAULT_LENIENT = false;
   static final boolean DEFAULT_PRETTY_PRINT = false;
   static final boolean DEFAULT_SERIALIZE_NULLS = false;
   static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
   private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
   private static final TypeToken NULL_KEY_SURROGATE = TypeToken.get(Object.class);
   final List builderFactories;
   final List builderHierarchyFactories;
   private final ThreadLocal calls;
   final boolean complexMapKeySerialization;
   private final ConstructorConstructor constructorConstructor;
   final String datePattern;
   final int dateStyle;
   final Excluder excluder;
   final List factories;
   final FieldNamingStrategy fieldNamingStrategy;
   final boolean generateNonExecutableJson;
   final boolean htmlSafe;
   final Map instanceCreators;
   private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
   final boolean lenient;
   final LongSerializationPolicy longSerializationPolicy;
   final boolean prettyPrinting;
   final boolean serializeNulls;
   final boolean serializeSpecialFloatingPointValues;
   final int timeStyle;
   private final Map typeTokenCache;

   public Gson() {
      this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, (String)null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
   }

   Gson(Excluder var1, FieldNamingStrategy var2, Map var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8, boolean var9, boolean var10, LongSerializationPolicy var11, String var12, int var13, int var14, List var15, List var16, List var17) {
      this.calls = new ThreadLocal();
      this.typeTokenCache = new ConcurrentHashMap();
      this.excluder = var1;
      this.fieldNamingStrategy = var2;
      this.instanceCreators = var3;
      ConstructorConstructor var21 = new ConstructorConstructor(var3);
      this.constructorConstructor = var21;
      this.serializeNulls = var4;
      this.complexMapKeySerialization = var5;
      this.generateNonExecutableJson = var6;
      this.htmlSafe = var7;
      this.prettyPrinting = var8;
      this.lenient = var9;
      this.serializeSpecialFloatingPointValues = var10;
      this.longSerializationPolicy = var11;
      this.datePattern = var12;
      this.dateStyle = var13;
      this.timeStyle = var14;
      this.builderFactories = var15;
      this.builderHierarchyFactories = var16;
      ArrayList var20 = new ArrayList();
      var20.add(TypeAdapters.JSON_ELEMENT_FACTORY);
      var20.add(ObjectTypeAdapter.FACTORY);
      var20.add(var1);
      var20.addAll(var17);
      var20.add(TypeAdapters.STRING_FACTORY);
      var20.add(TypeAdapters.INTEGER_FACTORY);
      var20.add(TypeAdapters.BOOLEAN_FACTORY);
      var20.add(TypeAdapters.BYTE_FACTORY);
      var20.add(TypeAdapters.SHORT_FACTORY);
      TypeAdapter var18 = longAdapter(var11);
      var20.add(TypeAdapters.newFactory(Long.TYPE, Long.class, var18));
      var20.add(TypeAdapters.newFactory(Double.TYPE, Double.class, this.doubleAdapter(var10)));
      var20.add(TypeAdapters.newFactory(Float.TYPE, Float.class, this.floatAdapter(var10)));
      var20.add(TypeAdapters.NUMBER_FACTORY);
      var20.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
      var20.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
      var20.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(var18)));
      var20.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(var18)));
      var20.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
      var20.add(TypeAdapters.CHARACTER_FACTORY);
      var20.add(TypeAdapters.STRING_BUILDER_FACTORY);
      var20.add(TypeAdapters.STRING_BUFFER_FACTORY);
      var20.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
      var20.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
      var20.add(TypeAdapters.URL_FACTORY);
      var20.add(TypeAdapters.URI_FACTORY);
      var20.add(TypeAdapters.UUID_FACTORY);
      var20.add(TypeAdapters.CURRENCY_FACTORY);
      var20.add(TypeAdapters.LOCALE_FACTORY);
      var20.add(TypeAdapters.INET_ADDRESS_FACTORY);
      var20.add(TypeAdapters.BIT_SET_FACTORY);
      var20.add(DateTypeAdapter.FACTORY);
      var20.add(TypeAdapters.CALENDAR_FACTORY);
      var20.add(TimeTypeAdapter.FACTORY);
      var20.add(SqlDateTypeAdapter.FACTORY);
      var20.add(TypeAdapters.TIMESTAMP_FACTORY);
      var20.add(ArrayTypeAdapter.FACTORY);
      var20.add(TypeAdapters.CLASS_FACTORY);
      var20.add(new CollectionTypeAdapterFactory(var21));
      var20.add(new MapTypeAdapterFactory(var21, var5));
      JsonAdapterAnnotationTypeAdapterFactory var19 = new JsonAdapterAnnotationTypeAdapterFactory(var21);
      this.jsonAdapterFactory = var19;
      var20.add(var19);
      var20.add(TypeAdapters.ENUM_FACTORY);
      var20.add(new ReflectiveTypeAdapterFactory(var21, var2, var1, var19));
      this.factories = Collections.unmodifiableList(var20);
   }

   private static void assertFullConsumption(Object var0, JsonReader var1) {
      if (var0 != null) {
         try {
            if (var1.peek() != JsonToken.END_DOCUMENT) {
               JsonIOException var4 = new JsonIOException("JSON document was not fully consumed.");
               throw var4;
            }
         } catch (MalformedJsonException var2) {
            throw new JsonSyntaxException(var2);
         } catch (IOException var3) {
            throw new JsonIOException(var3);
         }
      }

   }

   private static TypeAdapter atomicLongAdapter(TypeAdapter var0) {
      return (new TypeAdapter(var0) {
         final TypeAdapter val$longAdapter;

         {
            this.val$longAdapter = var1;
         }

         public AtomicLong read(JsonReader var1) throws IOException {
            return new AtomicLong(((Number)this.val$longAdapter.read(var1)).longValue());
         }

         public void write(JsonWriter var1, AtomicLong var2) throws IOException {
            this.val$longAdapter.write(var1, var2.get());
         }
      }).nullSafe();
   }

   private static TypeAdapter atomicLongArrayAdapter(TypeAdapter var0) {
      return (new TypeAdapter(var0) {
         final TypeAdapter val$longAdapter;

         {
            this.val$longAdapter = var1;
         }

         public AtomicLongArray read(JsonReader var1) throws IOException {
            ArrayList var4 = new ArrayList();
            var1.beginArray();

            while(var1.hasNext()) {
               var4.add(((Number)this.val$longAdapter.read(var1)).longValue());
            }

            var1.endArray();
            int var3 = var4.size();
            AtomicLongArray var5 = new AtomicLongArray(var3);

            for(int var2 = 0; var2 < var3; ++var2) {
               var5.set(var2, (Long)var4.get(var2));
            }

            return var5;
         }

         public void write(JsonWriter var1, AtomicLongArray var2) throws IOException {
            var1.beginArray();
            int var4 = var2.length();

            for(int var3 = 0; var3 < var4; ++var3) {
               this.val$longAdapter.write(var1, var2.get(var3));
            }

            var1.endArray();
         }
      }).nullSafe();
   }

   static void checkValidFloatingPoint(double var0) {
      if (Double.isNaN(var0) || Double.isInfinite(var0)) {
         throw new IllegalArgumentException(var0 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
      }
   }

   private TypeAdapter doubleAdapter(boolean var1) {
      return var1 ? TypeAdapters.DOUBLE : new TypeAdapter(this) {
         final Gson this$0;

         {
            this.this$0 = var1;
         }

         public Double read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return var1.nextDouble();
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            if (var2 == null) {
               var1.nullValue();
            } else {
               Gson.checkValidFloatingPoint(var2.doubleValue());
               var1.value(var2);
            }
         }
      };
   }

   private TypeAdapter floatAdapter(boolean var1) {
      return var1 ? TypeAdapters.FLOAT : new TypeAdapter(this) {
         final Gson this$0;

         {
            this.this$0 = var1;
         }

         public Float read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return (float)var1.nextDouble();
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            if (var2 == null) {
               var1.nullValue();
            } else {
               Gson.checkValidFloatingPoint((double)var2.floatValue());
               var1.value(var2);
            }
         }
      };
   }

   private static TypeAdapter longAdapter(LongSerializationPolicy var0) {
      return var0 == LongSerializationPolicy.DEFAULT ? TypeAdapters.LONG : new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return var1.nextLong();
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            if (var2 == null) {
               var1.nullValue();
            } else {
               var1.value(var2.toString());
            }
         }
      };
   }

   public Excluder excluder() {
      return this.excluder;
   }

   public FieldNamingStrategy fieldNamingStrategy() {
      return this.fieldNamingStrategy;
   }

   public Object fromJson(JsonElement var1, Class var2) throws JsonSyntaxException {
      Object var3 = this.fromJson((JsonElement)var1, (Type)var2);
      return Primitives.wrap(var2).cast(var3);
   }

   public Object fromJson(JsonElement var1, Type var2) throws JsonSyntaxException {
      return var1 == null ? null : this.fromJson((JsonReader)(new JsonTreeReader(var1)), (Type)var2);
   }

   public Object fromJson(JsonReader param1, Type param2) throws JsonIOException, JsonSyntaxException {
      // $FF: Couldn't be decompiled
   }

   public Object fromJson(Reader var1, Class var2) throws JsonSyntaxException, JsonIOException {
      JsonReader var4 = this.newJsonReader(var1);
      Object var3 = this.fromJson((JsonReader)var4, (Type)var2);
      assertFullConsumption(var3, var4);
      return Primitives.wrap(var2).cast(var3);
   }

   public Object fromJson(Reader var1, Type var2) throws JsonIOException, JsonSyntaxException {
      JsonReader var3 = this.newJsonReader(var1);
      Object var4 = this.fromJson(var3, var2);
      assertFullConsumption(var4, var3);
      return var4;
   }

   public Object fromJson(String var1, Class var2) throws JsonSyntaxException {
      Object var3 = this.fromJson((String)var1, (Type)var2);
      return Primitives.wrap(var2).cast(var3);
   }

   public Object fromJson(String var1, Type var2) throws JsonSyntaxException {
      return var1 == null ? null : this.fromJson((Reader)(new StringReader(var1)), (Type)var2);
   }

   public TypeAdapter getAdapter(TypeToken var1) {
      Map var4 = this.typeTokenCache;
      TypeToken var3;
      if (var1 == null) {
         var3 = NULL_KEY_SURROGATE;
      } else {
         var3 = var1;
      }

      TypeAdapter var27 = (TypeAdapter)var4.get(var3);
      if (var27 != null) {
         return var27;
      } else {
         var4 = (Map)this.calls.get();
         boolean var2 = false;
         Object var28 = var4;
         if (var4 == null) {
            var28 = new HashMap();
            this.calls.set(var28);
            var2 = true;
         }

         FutureTypeAdapter var29 = (FutureTypeAdapter)((Map)var28).get(var1);
         if (var29 != null) {
            return var29;
         } else {
            Throwable var10000;
            label314: {
               Iterator var5;
               boolean var10001;
               try {
                  var29 = new FutureTypeAdapter();
                  ((Map)var28).put(var1, var29);
                  var5 = this.factories.iterator();
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label314;
               }

               while(true) {
                  TypeAdapter var6;
                  try {
                     if (!var5.hasNext()) {
                        break;
                     }

                     var6 = ((TypeAdapterFactory)var5.next()).create(this, var1);
                  } catch (Throwable var26) {
                     var10000 = var26;
                     var10001 = false;
                     break label314;
                  }

                  if (var6 != null) {
                     try {
                        var29.setDelegate(var6);
                        this.typeTokenCache.put(var1, var6);
                     } catch (Throwable var23) {
                        var10000 = var23;
                        var10001 = false;
                        break label314;
                     }

                     ((Map)var28).remove(var1);
                     if (var2) {
                        this.calls.remove();
                     }

                     return var6;
                  }
               }

               label293:
               try {
                  StringBuilder var31 = new StringBuilder();
                  IllegalArgumentException var32 = new IllegalArgumentException(var31.append("GSON (2.8.6) cannot handle ").append(var1).toString());
                  throw var32;
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label293;
               }
            }

            Throwable var30 = var10000;
            ((Map)var28).remove(var1);
            if (var2) {
               this.calls.remove();
            }

            throw var30;
         }
      }
   }

   public TypeAdapter getAdapter(Class var1) {
      return this.getAdapter(TypeToken.get(var1));
   }

   public TypeAdapter getDelegateAdapter(TypeAdapterFactory var1, TypeToken var2) {
      Object var4 = var1;
      if (!this.factories.contains(var1)) {
         var4 = this.jsonAdapterFactory;
      }

      boolean var3 = false;
      Iterator var6 = this.factories.iterator();

      while(var6.hasNext()) {
         TypeAdapterFactory var5 = (TypeAdapterFactory)var6.next();
         if (!var3) {
            if (var5 == var4) {
               var3 = true;
            }
         } else {
            TypeAdapter var7 = var5.create(this, var2);
            if (var7 != null) {
               return var7;
            }
         }
      }

      throw new IllegalArgumentException("GSON cannot serialize " + var2);
   }

   public boolean htmlSafe() {
      return this.htmlSafe;
   }

   public GsonBuilder newBuilder() {
      return new GsonBuilder(this);
   }

   public JsonReader newJsonReader(Reader var1) {
      JsonReader var2 = new JsonReader(var1);
      var2.setLenient(this.lenient);
      return var2;
   }

   public JsonWriter newJsonWriter(Writer var1) throws IOException {
      if (this.generateNonExecutableJson) {
         var1.write(")]}'\n");
      }

      JsonWriter var2 = new JsonWriter(var1);
      if (this.prettyPrinting) {
         var2.setIndent("  ");
      }

      var2.setSerializeNulls(this.serializeNulls);
      return var2;
   }

   public boolean serializeNulls() {
      return this.serializeNulls;
   }

   public String toJson(JsonElement var1) {
      StringWriter var2 = new StringWriter();
      this.toJson((JsonElement)var1, (Appendable)var2);
      return var2.toString();
   }

   public String toJson(Object var1) {
      return var1 == null ? this.toJson((JsonElement)JsonNull.INSTANCE) : this.toJson((Object)var1, (Type)var1.getClass());
   }

   public String toJson(Object var1, Type var2) {
      StringWriter var3 = new StringWriter();
      this.toJson(var1, var2, (Appendable)var3);
      return var3.toString();
   }

   public void toJson(JsonElement var1, JsonWriter var2) throws JsonIOException {
      boolean var4 = var2.isLenient();
      var2.setLenient(true);
      boolean var5 = var2.isHtmlSafe();
      var2.setHtmlSafe(this.htmlSafe);
      boolean var3 = var2.getSerializeNulls();
      var2.setSerializeNulls(this.serializeNulls);

      try {
         Streams.write(var1, var2);
      } catch (IOException var11) {
         JsonIOException var15 = new JsonIOException(var11);
         throw var15;
      } catch (AssertionError var12) {
         StringBuilder var14 = new StringBuilder();
         AssertionError var6 = new AssertionError(var14.append("AssertionError (GSON 2.8.6): ").append(var12.getMessage()).toString());
         var6.initCause(var12);
         throw var6;
      } finally {
         var2.setLenient(var4);
         var2.setHtmlSafe(var5);
         var2.setSerializeNulls(var3);
      }

   }

   public void toJson(JsonElement var1, Appendable var2) throws JsonIOException {
      try {
         this.toJson(var1, this.newJsonWriter(Streams.writerForAppendable(var2)));
      } catch (IOException var3) {
         throw new JsonIOException(var3);
      }
   }

   public void toJson(Object var1, Appendable var2) throws JsonIOException {
      if (var1 != null) {
         this.toJson(var1, var1.getClass(), (Appendable)var2);
      } else {
         this.toJson((JsonElement)JsonNull.INSTANCE, (Appendable)var2);
      }

   }

   public void toJson(Object var1, Type var2, JsonWriter var3) throws JsonIOException {
      TypeAdapter var14 = this.getAdapter(TypeToken.get(var2));
      boolean var6 = var3.isLenient();
      var3.setLenient(true);
      boolean var5 = var3.isHtmlSafe();
      var3.setHtmlSafe(this.htmlSafe);
      boolean var4 = var3.getSerializeNulls();
      var3.setSerializeNulls(this.serializeNulls);

      try {
         var14.write(var3, var1);
      } catch (IOException var11) {
         JsonIOException var16 = new JsonIOException(var11);
         throw var16;
      } catch (AssertionError var12) {
         StringBuilder var15 = new StringBuilder();
         AssertionError var7 = new AssertionError(var15.append("AssertionError (GSON 2.8.6): ").append(var12.getMessage()).toString());
         var7.initCause(var12);
         throw var7;
      } finally {
         var3.setLenient(var6);
         var3.setHtmlSafe(var5);
         var3.setSerializeNulls(var4);
      }

   }

   public void toJson(Object var1, Type var2, Appendable var3) throws JsonIOException {
      try {
         this.toJson(var1, var2, this.newJsonWriter(Streams.writerForAppendable(var3)));
      } catch (IOException var4) {
         throw new JsonIOException(var4);
      }
   }

   public JsonElement toJsonTree(Object var1) {
      return (JsonElement)(var1 == null ? JsonNull.INSTANCE : this.toJsonTree(var1, var1.getClass()));
   }

   public JsonElement toJsonTree(Object var1, Type var2) {
      JsonTreeWriter var3 = new JsonTreeWriter();
      this.toJson(var1, var2, (JsonWriter)var3);
      return var3.get();
   }

   public String toString() {
      return "{serializeNulls:" + this.serializeNulls + ",factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
   }

   static class FutureTypeAdapter extends TypeAdapter {
      private TypeAdapter delegate;

      public Object read(JsonReader var1) throws IOException {
         TypeAdapter var2 = this.delegate;
         if (var2 != null) {
            return var2.read(var1);
         } else {
            throw new IllegalStateException();
         }
      }

      public void setDelegate(TypeAdapter var1) {
         if (this.delegate == null) {
            this.delegate = var1;
         } else {
            throw new AssertionError();
         }
      }

      public void write(JsonWriter var1, Object var2) throws IOException {
         TypeAdapter var3 = this.delegate;
         if (var3 != null) {
            var3.write(var1, var2);
         } else {
            throw new IllegalStateException();
         }
      }
   }
}
