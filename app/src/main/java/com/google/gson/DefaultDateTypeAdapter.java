package com.google.gson;

import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

final class DefaultDateTypeAdapter extends TypeAdapter {
   private static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
   private final List dateFormats;
   private final Class dateType;

   public DefaultDateTypeAdapter(int var1, int var2) {
      this(Date.class, var1, var2);
   }

   DefaultDateTypeAdapter(Class var1) {
      ArrayList var2 = new ArrayList();
      this.dateFormats = var2;
      this.dateType = verifyDateType(var1);
      var2.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
      if (!Locale.getDefault().equals(Locale.US)) {
         var2.add(DateFormat.getDateTimeInstance(2, 2));
      }

      if (JavaVersion.isJava9OrLater()) {
         var2.add(PreJava9DateFormatProvider.getUSDateTimeFormat(2, 2));
      }

   }

   DefaultDateTypeAdapter(Class var1, int var2) {
      ArrayList var3 = new ArrayList();
      this.dateFormats = var3;
      this.dateType = verifyDateType(var1);
      var3.add(DateFormat.getDateInstance(var2, Locale.US));
      if (!Locale.getDefault().equals(Locale.US)) {
         var3.add(DateFormat.getDateInstance(var2));
      }

      if (JavaVersion.isJava9OrLater()) {
         var3.add(PreJava9DateFormatProvider.getUSDateFormat(var2));
      }

   }

   public DefaultDateTypeAdapter(Class var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      this.dateFormats = var4;
      this.dateType = verifyDateType(var1);
      var4.add(DateFormat.getDateTimeInstance(var2, var3, Locale.US));
      if (!Locale.getDefault().equals(Locale.US)) {
         var4.add(DateFormat.getDateTimeInstance(var2, var3));
      }

      if (JavaVersion.isJava9OrLater()) {
         var4.add(PreJava9DateFormatProvider.getUSDateTimeFormat(var2, var3));
      }

   }

   DefaultDateTypeAdapter(Class var1, String var2) {
      ArrayList var3 = new ArrayList();
      this.dateFormats = var3;
      this.dateType = verifyDateType(var1);
      var3.add(new SimpleDateFormat(var2, Locale.US));
      if (!Locale.getDefault().equals(Locale.US)) {
         var3.add(new SimpleDateFormat(var2));
      }

   }

   private Date deserializeToDate(String param1) {
      // $FF: Couldn't be decompiled
   }

   private static Class verifyDateType(Class var0) {
      if (var0 != Date.class && var0 != java.sql.Date.class && var0 != Timestamp.class) {
         throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + var0);
      } else {
         return var0;
      }
   }

   public Date read(JsonReader var1) throws IOException {
      if (var1.peek() == JsonToken.NULL) {
         var1.nextNull();
         return null;
      } else {
         Date var2 = this.deserializeToDate(var1.nextString());
         Class var3 = this.dateType;
         if (var3 == Date.class) {
            return var2;
         } else if (var3 == Timestamp.class) {
            return new Timestamp(var2.getTime());
         } else if (var3 == java.sql.Date.class) {
            return new java.sql.Date(var2.getTime());
         } else {
            throw new AssertionError();
         }
      }
   }

   public String toString() {
      DateFormat var1 = (DateFormat)this.dateFormats.get(0);
      return var1 instanceof SimpleDateFormat ? "DefaultDateTypeAdapter(" + ((SimpleDateFormat)var1).toPattern() + ')' : "DefaultDateTypeAdapter(" + var1.getClass().getSimpleName() + ')';
   }

   public void write(JsonWriter param1, Date param2) throws IOException {
      // $FF: Couldn't be decompiled
   }
}
