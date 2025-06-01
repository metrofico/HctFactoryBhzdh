package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class DateTypeAdapter extends TypeAdapter {
   public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
      public TypeAdapter create(Gson var1, TypeToken var2) {
         DateTypeAdapter var3;
         if (var2.getRawType() == Date.class) {
            var3 = new DateTypeAdapter();
         } else {
            var3 = null;
         }

         return var3;
      }
   };
   private final List dateFormats;

   public DateTypeAdapter() {
      ArrayList var1 = new ArrayList();
      this.dateFormats = var1;
      var1.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
      if (!Locale.getDefault().equals(Locale.US)) {
         var1.add(DateFormat.getDateTimeInstance(2, 2));
      }

      if (JavaVersion.isJava9OrLater()) {
         var1.add(PreJava9DateFormatProvider.getUSDateTimeFormat(2, 2));
      }

   }

   private Date deserializeToDate(String var1) {
      synchronized(this){}

      try {
         Iterator var2 = this.dateFormats.iterator();

         while(var2.hasNext()) {
            DateFormat var3 = (DateFormat)var2.next();

            try {
               Date var12 = var3.parse(var1);
               return var12;
            } catch (ParseException var8) {
            }
         }

         try {
            ParsePosition var10 = new ParsePosition(0);
            Date var11 = ISO8601Utils.parse(var1, var10);
            return var11;
         } catch (ParseException var7) {
            JsonSyntaxException var13 = new JsonSyntaxException(var1, var7);
            throw var13;
         }
      } finally {
         ;
      }
   }

   public Date read(JsonReader var1) throws IOException {
      if (var1.peek() == JsonToken.NULL) {
         var1.nextNull();
         return null;
      } else {
         return this.deserializeToDate(var1.nextString());
      }
   }

   public void write(JsonWriter var1, Date var2) throws IOException {
      synchronized(this){}
      Throwable var10000;
      boolean var10001;
      if (var2 == null) {
         label55: {
            try {
               var1.nullValue();
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label55;
            }

            return;
         }
      } else {
         label58: {
            try {
               var1.value(((DateFormat)this.dateFormats.get(0)).format(var2));
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label58;
            }

            return;
         }
      }

      Throwable var9 = var10000;
      throw var9;
   }
}
