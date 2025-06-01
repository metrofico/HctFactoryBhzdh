package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class TypeAdapters {
   public static final TypeAdapter ATOMIC_BOOLEAN;
   public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY;
   public static final TypeAdapter ATOMIC_INTEGER;
   public static final TypeAdapter ATOMIC_INTEGER_ARRAY;
   public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY;
   public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY;
   public static final TypeAdapter BIG_DECIMAL;
   public static final TypeAdapter BIG_INTEGER;
   public static final TypeAdapter BIT_SET;
   public static final TypeAdapterFactory BIT_SET_FACTORY;
   public static final TypeAdapter BOOLEAN;
   public static final TypeAdapter BOOLEAN_AS_STRING;
   public static final TypeAdapterFactory BOOLEAN_FACTORY;
   public static final TypeAdapter BYTE;
   public static final TypeAdapterFactory BYTE_FACTORY;
   public static final TypeAdapter CALENDAR;
   public static final TypeAdapterFactory CALENDAR_FACTORY;
   public static final TypeAdapter CHARACTER;
   public static final TypeAdapterFactory CHARACTER_FACTORY;
   public static final TypeAdapter CLASS;
   public static final TypeAdapterFactory CLASS_FACTORY;
   public static final TypeAdapter CURRENCY;
   public static final TypeAdapterFactory CURRENCY_FACTORY;
   public static final TypeAdapter DOUBLE;
   public static final TypeAdapterFactory ENUM_FACTORY;
   public static final TypeAdapter FLOAT;
   public static final TypeAdapter INET_ADDRESS;
   public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
   public static final TypeAdapter INTEGER;
   public static final TypeAdapterFactory INTEGER_FACTORY;
   public static final TypeAdapter JSON_ELEMENT;
   public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
   public static final TypeAdapter LOCALE;
   public static final TypeAdapterFactory LOCALE_FACTORY;
   public static final TypeAdapter LONG;
   public static final TypeAdapter NUMBER;
   public static final TypeAdapterFactory NUMBER_FACTORY;
   public static final TypeAdapter SHORT;
   public static final TypeAdapterFactory SHORT_FACTORY;
   public static final TypeAdapter STRING;
   public static final TypeAdapter STRING_BUFFER;
   public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
   public static final TypeAdapter STRING_BUILDER;
   public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
   public static final TypeAdapterFactory STRING_FACTORY;
   public static final TypeAdapterFactory TIMESTAMP_FACTORY;
   public static final TypeAdapter URI;
   public static final TypeAdapterFactory URI_FACTORY;
   public static final TypeAdapter URL;
   public static final TypeAdapterFactory URL_FACTORY;
   public static final TypeAdapter UUID;
   public static final TypeAdapterFactory UUID_FACTORY;

   static {
      TypeAdapter var0 = (new TypeAdapter() {
         public Class read(JsonReader var1) throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
         }

         public void write(JsonWriter var1, Class var2) throws IOException {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + var2.getName() + ". Forgot to register a type adapter?");
         }
      }).nullSafe();
      CLASS = var0;
      CLASS_FACTORY = newFactory(Class.class, var0);
      var0 = (new TypeAdapter() {
         public BitSet read(JsonReader var1) throws IOException {
            BitSet var6 = new BitSet();
            var1.beginArray();
            JsonToken var5 = var1.peek();

            for(int var2 = 0; var5 != JsonToken.END_ARRAY; var5 = var1.peek()) {
               boolean var4;
               label39: {
                  int var3 = null.$SwitchMap$com$google$gson$stream$JsonToken[var5.ordinal()];
                  var4 = true;
                  if (var3 != 1) {
                     if (var3 == 2) {
                        var4 = var1.nextBoolean();
                        break label39;
                     }

                     if (var3 != 3) {
                        throw new JsonSyntaxException("Invalid bitset value type: " + var5);
                     }

                     String var8 = var1.nextString();

                     try {
                        var3 = Integer.parseInt(var8);
                     } catch (NumberFormatException var7) {
                        throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + var8);
                     }

                     if (var3 != 0) {
                        break label39;
                     }
                  } else if (var1.nextInt() != 0) {
                     break label39;
                  }

                  var4 = false;
               }

               if (var4) {
                  var6.set(var2);
               }

               ++var2;
            }

            var1.endArray();
            return var6;
         }

         public void write(JsonWriter var1, BitSet var2) throws IOException {
            var1.beginArray();
            int var4 = var2.length();

            for(int var3 = 0; var3 < var4; ++var3) {
               var1.value((long)var2.get(var3));
            }

            var1.endArray();
         }
      }).nullSafe();
      BIT_SET = var0;
      BIT_SET_FACTORY = newFactory(BitSet.class, var0);
      var0 = new TypeAdapter() {
         public Boolean read(JsonReader var1) throws IOException {
            JsonToken var2 = var1.peek();
            if (var2 == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return var2 == JsonToken.STRING ? Boolean.parseBoolean(var1.nextString()) : var1.nextBoolean();
            }
         }

         public void write(JsonWriter var1, Boolean var2) throws IOException {
            var1.value(var2);
         }
      };
      BOOLEAN = var0;
      BOOLEAN_AS_STRING = new TypeAdapter() {
         public Boolean read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return Boolean.valueOf(var1.nextString());
            }
         }

         public void write(JsonWriter var1, Boolean var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = "null";
            } else {
               var3 = var2.toString();
            }

            var1.value(var3);
         }
      };
      BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, var0);
      var0 = new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               byte var2;
               try {
                  var2 = (byte)var1.nextInt();
               } catch (NumberFormatException var3) {
                  throw new JsonSyntaxException(var3);
               }

               return var2;
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            var1.value(var2);
         }
      };
      BYTE = var0;
      BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, var0);
      var0 = new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               short var2;
               try {
                  var2 = (short)var1.nextInt();
               } catch (NumberFormatException var3) {
                  throw new JsonSyntaxException(var3);
               }

               return var2;
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            var1.value(var2);
         }
      };
      SHORT = var0;
      SHORT_FACTORY = newFactory(Short.TYPE, Short.class, var0);
      var0 = new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               int var2;
               try {
                  var2 = var1.nextInt();
               } catch (NumberFormatException var3) {
                  throw new JsonSyntaxException(var3);
               }

               return var2;
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            var1.value(var2);
         }
      };
      INTEGER = var0;
      INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, var0);
      var0 = (new TypeAdapter() {
         public AtomicInteger read(JsonReader var1) throws IOException {
            try {
               AtomicInteger var3 = new AtomicInteger(var1.nextInt());
               return var3;
            } catch (NumberFormatException var2) {
               throw new JsonSyntaxException(var2);
            }
         }

         public void write(JsonWriter var1, AtomicInteger var2) throws IOException {
            var1.value((long)var2.get());
         }
      }).nullSafe();
      ATOMIC_INTEGER = var0;
      ATOMIC_INTEGER_FACTORY = newFactory(AtomicInteger.class, var0);
      var0 = (new TypeAdapter() {
         public AtomicBoolean read(JsonReader var1) throws IOException {
            return new AtomicBoolean(var1.nextBoolean());
         }

         public void write(JsonWriter var1, AtomicBoolean var2) throws IOException {
            var1.value(var2.get());
         }
      }).nullSafe();
      ATOMIC_BOOLEAN = var0;
      ATOMIC_BOOLEAN_FACTORY = newFactory(AtomicBoolean.class, var0);
      var0 = (new TypeAdapter() {
         public AtomicIntegerArray read(JsonReader var1) throws IOException {
            ArrayList var4 = new ArrayList();
            var1.beginArray();

            while(var1.hasNext()) {
               try {
                  var4.add(var1.nextInt());
               } catch (NumberFormatException var5) {
                  throw new JsonSyntaxException(var5);
               }
            }

            var1.endArray();
            int var3 = var4.size();
            AtomicIntegerArray var6 = new AtomicIntegerArray(var3);

            for(int var2 = 0; var2 < var3; ++var2) {
               var6.set(var2, (Integer)var4.get(var2));
            }

            return var6;
         }

         public void write(JsonWriter var1, AtomicIntegerArray var2) throws IOException {
            var1.beginArray();
            int var4 = var2.length();

            for(int var3 = 0; var3 < var4; ++var3) {
               var1.value((long)var2.get(var3));
            }

            var1.endArray();
         }
      }).nullSafe();
      ATOMIC_INTEGER_ARRAY = var0;
      ATOMIC_INTEGER_ARRAY_FACTORY = newFactory(AtomicIntegerArray.class, var0);
      LONG = new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               long var2;
               try {
                  var2 = var1.nextLong();
               } catch (NumberFormatException var4) {
                  throw new JsonSyntaxException(var4);
               }

               return var2;
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            var1.value(var2);
         }
      };
      FLOAT = new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return (float)var1.nextDouble();
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            var1.value(var2);
         }
      };
      DOUBLE = new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return var1.nextDouble();
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            var1.value(var2);
         }
      };
      var0 = new TypeAdapter() {
         public Number read(JsonReader var1) throws IOException {
            JsonToken var3 = var1.peek();
            int var2 = null.$SwitchMap$com$google$gson$stream$JsonToken[var3.ordinal()];
            if (var2 != 1 && var2 != 3) {
               if (var2 == 4) {
                  var1.nextNull();
                  return null;
               } else {
                  throw new JsonSyntaxException("Expecting number, got: " + var3);
               }
            } else {
               return new LazilyParsedNumber(var1.nextString());
            }
         }

         public void write(JsonWriter var1, Number var2) throws IOException {
            var1.value(var2);
         }
      };
      NUMBER = var0;
      NUMBER_FACTORY = newFactory(Number.class, var0);
      var0 = new TypeAdapter() {
         public Character read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               String var2 = var1.nextString();
               if (var2.length() == 1) {
                  return var2.charAt(0);
               } else {
                  throw new JsonSyntaxException("Expecting character, got: " + var2);
               }
            }
         }

         public void write(JsonWriter var1, Character var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = String.valueOf(var2);
            }

            var1.value(var3);
         }
      };
      CHARACTER = var0;
      CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, var0);
      var0 = new TypeAdapter() {
         public String read(JsonReader var1) throws IOException {
            JsonToken var2 = var1.peek();
            if (var2 == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return var2 == JsonToken.BOOLEAN ? Boolean.toString(var1.nextBoolean()) : var1.nextString();
            }
         }

         public void write(JsonWriter var1, String var2) throws IOException {
            var1.value(var2);
         }
      };
      STRING = var0;
      BIG_DECIMAL = new TypeAdapter() {
         public BigDecimal read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               try {
                  BigDecimal var3 = new BigDecimal(var1.nextString());
                  return var3;
               } catch (NumberFormatException var2) {
                  throw new JsonSyntaxException(var2);
               }
            }
         }

         public void write(JsonWriter var1, BigDecimal var2) throws IOException {
            var1.value((Number)var2);
         }
      };
      BIG_INTEGER = new TypeAdapter() {
         public BigInteger read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               try {
                  BigInteger var3 = new BigInteger(var1.nextString());
                  return var3;
               } catch (NumberFormatException var2) {
                  throw new JsonSyntaxException(var2);
               }
            }
         }

         public void write(JsonWriter var1, BigInteger var2) throws IOException {
            var1.value((Number)var2);
         }
      };
      STRING_FACTORY = newFactory(String.class, var0);
      var0 = new TypeAdapter() {
         public StringBuilder read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return new StringBuilder(var1.nextString());
            }
         }

         public void write(JsonWriter var1, StringBuilder var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = var2.toString();
            }

            var1.value(var3);
         }
      };
      STRING_BUILDER = var0;
      STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, var0);
      var0 = new TypeAdapter() {
         public StringBuffer read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return new StringBuffer(var1.nextString());
            }
         }

         public void write(JsonWriter var1, StringBuffer var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = var2.toString();
            }

            var1.value(var3);
         }
      };
      STRING_BUFFER = var0;
      STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, var0);
      var0 = new TypeAdapter() {
         public URL read(JsonReader var1) throws IOException {
            JsonToken var4 = var1.peek();
            JsonToken var3 = JsonToken.NULL;
            Object var2 = null;
            if (var4 == var3) {
               var1.nextNull();
               return null;
            } else {
               String var5 = var1.nextString();
               URL var6;
               if ("null".equals(var5)) {
                  var6 = (URL)var2;
               } else {
                  var6 = new URL(var5);
               }

               return var6;
            }
         }

         public void write(JsonWriter var1, URL var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = var2.toExternalForm();
            }

            var1.value(var3);
         }
      };
      URL = var0;
      URL_FACTORY = newFactory(URL.class, var0);
      var0 = new TypeAdapter() {
         public URI read(JsonReader var1) throws IOException {
            JsonToken var3 = var1.peek();
            JsonToken var4 = JsonToken.NULL;
            Object var2 = null;
            if (var3 == var4) {
               var1.nextNull();
               return null;
            } else {
               URI var7;
               try {
                  String var6 = var1.nextString();
                  if (!"null".equals(var6)) {
                     var7 = new URI(var6);
                     return var7;
                  }
               } catch (URISyntaxException var5) {
                  throw new JsonIOException(var5);
               }

               var7 = (URI)var2;
               return var7;
            }
         }

         public void write(JsonWriter var1, URI var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = var2.toASCIIString();
            }

            var1.value(var3);
         }
      };
      URI = var0;
      URI_FACTORY = newFactory(URI.class, var0);
      var0 = new TypeAdapter() {
         public InetAddress read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return InetAddress.getByName(var1.nextString());
            }
         }

         public void write(JsonWriter var1, InetAddress var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = var2.getHostAddress();
            }

            var1.value(var3);
         }
      };
      INET_ADDRESS = var0;
      INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, var0);
      var0 = new TypeAdapter() {
         public UUID read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               return java.util.UUID.fromString(var1.nextString());
            }
         }

         public void write(JsonWriter var1, UUID var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = var2.toString();
            }

            var1.value(var3);
         }
      };
      UUID = var0;
      UUID_FACTORY = newFactory(UUID.class, var0);
      var0 = (new TypeAdapter() {
         public Currency read(JsonReader var1) throws IOException {
            return Currency.getInstance(var1.nextString());
         }

         public void write(JsonWriter var1, Currency var2) throws IOException {
            var1.value(var2.getCurrencyCode());
         }
      }).nullSafe();
      CURRENCY = var0;
      CURRENCY_FACTORY = newFactory(Currency.class, var0);
      TIMESTAMP_FACTORY = new TypeAdapterFactory() {
         public TypeAdapter create(Gson var1, TypeToken var2) {
            return var2.getRawType() != Timestamp.class ? null : new TypeAdapter(this, var1.getAdapter(Date.class)) {
               final <undefinedtype> this$0;
               final TypeAdapter val$dateTypeAdapter;

               {
                  this.this$0 = var1;
                  this.val$dateTypeAdapter = var2;
               }

               public Timestamp read(JsonReader var1) throws IOException {
                  Date var2 = (Date)this.val$dateTypeAdapter.read(var1);
                  Timestamp var3;
                  if (var2 != null) {
                     var3 = new Timestamp(var2.getTime());
                  } else {
                     var3 = null;
                  }

                  return var3;
               }

               public void write(JsonWriter var1, Timestamp var2) throws IOException {
                  this.val$dateTypeAdapter.write(var1, var2);
               }
            };
         }
      };
      var0 = new TypeAdapter() {
         private static final String DAY_OF_MONTH = "dayOfMonth";
         private static final String HOUR_OF_DAY = "hourOfDay";
         private static final String MINUTE = "minute";
         private static final String MONTH = "month";
         private static final String SECOND = "second";
         private static final String YEAR = "year";

         public Calendar read(JsonReader var1) throws IOException {
            if (var1.peek() == JsonToken.NULL) {
               var1.nextNull();
               return null;
            } else {
               var1.beginObject();
               int var8 = 0;
               int var7 = 0;
               int var3 = var7;
               int var4 = var7;
               int var5 = var7;
               int var6 = var7;

               while(var1.peek() != JsonToken.END_OBJECT) {
                  String var9 = var1.nextName();
                  int var2 = var1.nextInt();
                  if ("year".equals(var9)) {
                     var8 = var2;
                  } else if ("month".equals(var9)) {
                     var7 = var2;
                  } else if ("dayOfMonth".equals(var9)) {
                     var6 = var2;
                  } else if ("hourOfDay".equals(var9)) {
                     var3 = var2;
                  } else if ("minute".equals(var9)) {
                     var4 = var2;
                  } else if ("second".equals(var9)) {
                     var5 = var2;
                  }
               }

               var1.endObject();
               return new GregorianCalendar(var8, var7, var6, var3, var4, var5);
            }
         }

         public void write(JsonWriter var1, Calendar var2) throws IOException {
            if (var2 == null) {
               var1.nullValue();
            } else {
               var1.beginObject();
               var1.name("year");
               var1.value((long)var2.get(1));
               var1.name("month");
               var1.value((long)var2.get(2));
               var1.name("dayOfMonth");
               var1.value((long)var2.get(5));
               var1.name("hourOfDay");
               var1.value((long)var2.get(11));
               var1.name("minute");
               var1.value((long)var2.get(12));
               var1.name("second");
               var1.value((long)var2.get(13));
               var1.endObject();
            }
         }
      };
      CALENDAR = var0;
      CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, var0);
      var0 = new TypeAdapter() {
         public Locale read(JsonReader var1) throws IOException {
            JsonToken var4 = var1.peek();
            JsonToken var2 = JsonToken.NULL;
            String var3 = null;
            if (var4 == var2) {
               var1.nextNull();
               return null;
            } else {
               StringTokenizer var7 = new StringTokenizer(var1.nextString(), "_");
               String var5;
               if (var7.hasMoreElements()) {
                  var5 = var7.nextToken();
               } else {
                  var5 = null;
               }

               String var6;
               if (var7.hasMoreElements()) {
                  var6 = var7.nextToken();
               } else {
                  var6 = null;
               }

               if (var7.hasMoreElements()) {
                  var3 = var7.nextToken();
               }

               if (var6 == null && var3 == null) {
                  return new Locale(var5);
               } else {
                  return var3 == null ? new Locale(var5, var6) : new Locale(var5, var6, var3);
               }
            }
         }

         public void write(JsonWriter var1, Locale var2) throws IOException {
            String var3;
            if (var2 == null) {
               var3 = null;
            } else {
               var3 = var2.toString();
            }

            var1.value(var3);
         }
      };
      LOCALE = var0;
      LOCALE_FACTORY = newFactory(Locale.class, var0);
      var0 = new TypeAdapter() {
         public JsonElement read(JsonReader var1) throws IOException {
            switch (null.$SwitchMap$com$google$gson$stream$JsonToken[var1.peek().ordinal()]) {
               case 1:
                  return new JsonPrimitive(new LazilyParsedNumber(var1.nextString()));
               case 2:
                  return new JsonPrimitive(var1.nextBoolean());
               case 3:
                  return new JsonPrimitive(var1.nextString());
               case 4:
                  var1.nextNull();
                  return JsonNull.INSTANCE;
               case 5:
                  JsonArray var3 = new JsonArray();
                  var1.beginArray();

                  while(var1.hasNext()) {
                     var3.add(this.read(var1));
                  }

                  var1.endArray();
                  return var3;
               case 6:
                  JsonObject var2 = new JsonObject();
                  var1.beginObject();

                  while(var1.hasNext()) {
                     var2.add(var1.nextName(), this.read(var1));
                  }

                  var1.endObject();
                  return var2;
               default:
                  throw new IllegalArgumentException();
            }
         }

         public void write(JsonWriter var1, JsonElement var2) throws IOException {
            if (var2 != null && !var2.isJsonNull()) {
               if (var2.isJsonPrimitive()) {
                  JsonPrimitive var4 = var2.getAsJsonPrimitive();
                  if (var4.isNumber()) {
                     var1.value(var4.getAsNumber());
                  } else if (var4.isBoolean()) {
                     var1.value(var4.getAsBoolean());
                  } else {
                     var1.value(var4.getAsString());
                  }
               } else if (var2.isJsonArray()) {
                  var1.beginArray();
                  Iterator var5 = var2.getAsJsonArray().iterator();

                  while(var5.hasNext()) {
                     this.write(var1, (JsonElement)var5.next());
                  }

                  var1.endArray();
               } else {
                  if (!var2.isJsonObject()) {
                     throw new IllegalArgumentException("Couldn't write " + var2.getClass());
                  }

                  var1.beginObject();
                  Iterator var3 = var2.getAsJsonObject().entrySet().iterator();

                  while(var3.hasNext()) {
                     Map.Entry var6 = (Map.Entry)var3.next();
                     var1.name((String)var6.getKey());
                     this.write(var1, (JsonElement)var6.getValue());
                  }

                  var1.endObject();
               }
            } else {
               var1.nullValue();
            }

         }
      };
      JSON_ELEMENT = var0;
      JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, var0);
      ENUM_FACTORY = new TypeAdapterFactory() {
         public TypeAdapter create(Gson var1, TypeToken var2) {
            Class var4 = var2.getRawType();
            if (Enum.class.isAssignableFrom(var4) && var4 != Enum.class) {
               Class var3 = var4;
               if (!var4.isEnum()) {
                  var3 = var4.getSuperclass();
               }

               return new EnumTypeAdapter(var3);
            } else {
               return null;
            }
         }
      };
   }

   private TypeAdapters() {
      throw new UnsupportedOperationException();
   }

   public static TypeAdapterFactory newFactory(TypeToken var0, TypeAdapter var1) {
      return new TypeAdapterFactory(var0, var1) {
         final TypeToken val$type;
         final TypeAdapter val$typeAdapter;

         {
            this.val$type = var1;
            this.val$typeAdapter = var2;
         }

         public TypeAdapter create(Gson var1, TypeToken var2) {
            TypeAdapter var3;
            if (var2.equals(this.val$type)) {
               var3 = this.val$typeAdapter;
            } else {
               var3 = null;
            }

            return var3;
         }
      };
   }

   public static TypeAdapterFactory newFactory(Class var0, TypeAdapter var1) {
      return new TypeAdapterFactory(var0, var1) {
         final Class val$type;
         final TypeAdapter val$typeAdapter;

         {
            this.val$type = var1;
            this.val$typeAdapter = var2;
         }

         public TypeAdapter create(Gson var1, TypeToken var2) {
            TypeAdapter var3;
            if (var2.getRawType() == this.val$type) {
               var3 = this.val$typeAdapter;
            } else {
               var3 = null;
            }

            return var3;
         }

         public String toString() {
            return "Factory[type=" + this.val$type.getName() + ",adapter=" + this.val$typeAdapter + "]";
         }
      };
   }

   public static TypeAdapterFactory newFactory(Class var0, Class var1, TypeAdapter var2) {
      return new TypeAdapterFactory(var0, var1, var2) {
         final Class val$boxed;
         final TypeAdapter val$typeAdapter;
         final Class val$unboxed;

         {
            this.val$unboxed = var1;
            this.val$boxed = var2;
            this.val$typeAdapter = var3;
         }

         public TypeAdapter create(Gson var1, TypeToken var2) {
            Class var3 = var2.getRawType();
            TypeAdapter var4;
            if (var3 != this.val$unboxed && var3 != this.val$boxed) {
               var4 = null;
            } else {
               var4 = this.val$typeAdapter;
            }

            return var4;
         }

         public String toString() {
            return "Factory[type=" + this.val$boxed.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + this.val$typeAdapter + "]";
         }
      };
   }

   public static TypeAdapterFactory newFactoryForMultipleTypes(Class var0, Class var1, TypeAdapter var2) {
      return new TypeAdapterFactory(var0, var1, var2) {
         final Class val$base;
         final Class val$sub;
         final TypeAdapter val$typeAdapter;

         {
            this.val$base = var1;
            this.val$sub = var2;
            this.val$typeAdapter = var3;
         }

         public TypeAdapter create(Gson var1, TypeToken var2) {
            Class var3 = var2.getRawType();
            TypeAdapter var4;
            if (var3 != this.val$base && var3 != this.val$sub) {
               var4 = null;
            } else {
               var4 = this.val$typeAdapter;
            }

            return var4;
         }

         public String toString() {
            return "Factory[type=" + this.val$base.getName() + "+" + this.val$sub.getName() + ",adapter=" + this.val$typeAdapter + "]";
         }
      };
   }

   public static TypeAdapterFactory newTypeHierarchyFactory(Class var0, TypeAdapter var1) {
      return new TypeAdapterFactory(var0, var1) {
         final Class val$clazz;
         final TypeAdapter val$typeAdapter;

         {
            this.val$clazz = var1;
            this.val$typeAdapter = var2;
         }

         public TypeAdapter create(Gson var1, TypeToken var2) {
            Class var3 = var2.getRawType();
            return !this.val$clazz.isAssignableFrom(var3) ? null : new TypeAdapter(this, var3) {
               final <undefinedtype> this$0;
               final Class val$requestedType;

               {
                  this.this$0 = var1;
                  this.val$requestedType = var2;
               }

               public Object read(JsonReader var1) throws IOException {
                  Object var2 = this.this$0.val$typeAdapter.read(var1);
                  if (var2 != null && !this.val$requestedType.isInstance(var2)) {
                     throw new JsonSyntaxException("Expected a " + this.val$requestedType.getName() + " but was " + var2.getClass().getName());
                  } else {
                     return var2;
                  }
               }

               public void write(JsonWriter var1, Object var2) throws IOException {
                  this.this$0.val$typeAdapter.write(var1, var2);
               }
            };
         }

         public String toString() {
            return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + this.val$typeAdapter + "]";
         }
      };
   }

   private static final class EnumTypeAdapter extends TypeAdapter {
      private final Map constantToName = new HashMap();
      private final Map nameToConstant = new HashMap();

      public EnumTypeAdapter(Class var1) {
         NoSuchFieldException var10000;
         label62: {
            int var4;
            Enum[] var8;
            boolean var10001;
            try {
               var8 = (Enum[])var1.getEnumConstants();
               var4 = var8.length;
            } catch (NoSuchFieldException var15) {
               var10000 = var15;
               var10001 = false;
               break label62;
            }

            int var2 = 0;

            label53:
            while(true) {
               if (var2 >= var4) {
                  return;
               }

               Enum var9 = var8[var2];

               String var6;
               SerializedName var10;
               try {
                  var6 = var9.name();
                  var10 = (SerializedName)var1.getField(var6).getAnnotation(SerializedName.class);
               } catch (NoSuchFieldException var14) {
                  var10000 = var14;
                  var10001 = false;
                  break;
               }

               if (var10 != null) {
                  int var5;
                  String var7;
                  String[] var17;
                  try {
                     var7 = var10.value();
                     var17 = var10.alternate();
                     var5 = var17.length;
                  } catch (NoSuchFieldException var13) {
                     var10000 = var13;
                     var10001 = false;
                     break;
                  }

                  int var3 = 0;

                  while(true) {
                     var6 = var7;
                     if (var3 >= var5) {
                        break;
                     }

                     var6 = var17[var3];

                     try {
                        this.nameToConstant.put(var6, var9);
                     } catch (NoSuchFieldException var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label53;
                     }

                     ++var3;
                  }
               }

               try {
                  this.nameToConstant.put(var6, var9);
                  this.constantToName.put(var9, var6);
               } catch (NoSuchFieldException var11) {
                  var10000 = var11;
                  var10001 = false;
                  break;
               }

               ++var2;
            }
         }

         NoSuchFieldException var16 = var10000;
         throw new AssertionError(var16);
      }

      public Enum read(JsonReader var1) throws IOException {
         if (var1.peek() == JsonToken.NULL) {
            var1.nextNull();
            return null;
         } else {
            return (Enum)this.nameToConstant.get(var1.nextString());
         }
      }

      public void write(JsonWriter var1, Enum var2) throws IOException {
         String var3;
         if (var2 == null) {
            var3 = null;
         } else {
            var3 = (String)this.constantToName.get(var2);
         }

         var1.value(var3);
      }
   }
}
