package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter extends TypeAdapter {
   public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
      public TypeAdapter create(Gson var1, TypeToken var2) {
         SqlDateTypeAdapter var3;
         if (var2.getRawType() == Date.class) {
            var3 = new SqlDateTypeAdapter();
         } else {
            var3 = null;
         }

         return var3;
      }
   };
   private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");

   public Date read(JsonReader var1) throws IOException {
      synchronized(this){}

      try {
         if (var1.peek() != JsonToken.NULL) {
            try {
               Date var7 = new Date(this.format.parse(var1.nextString()).getTime());
               return var7;
            } catch (ParseException var5) {
               JsonSyntaxException var2 = new JsonSyntaxException(var5);
               throw var2;
            }
         }

         var1.nextNull();
      } finally {
         ;
      }

      return null;
   }

   public void write(JsonWriter var1, Date var2) throws IOException {
      Throwable var10000;
      label61: {
         synchronized(this){}
         boolean var10001;
         String var10;
         if (var2 == null) {
            var10 = null;
         } else {
            try {
               var10 = this.format.format(var2);
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label61;
            }
         }

         label57:
         try {
            var1.value(var10);
            return;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label57;
         }
      }

      Throwable var9 = var10000;
      throw var9;
   }
}
