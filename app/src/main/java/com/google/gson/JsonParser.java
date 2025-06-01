package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser {
   public static JsonElement parseReader(JsonReader var0) throws JsonIOException, JsonSyntaxException {
      boolean var1 = var0.isLenient();
      var0.setLenient(true);

      JsonElement var11;
      try {
         JsonParseException var4;
         try {
            var11 = Streams.parse(var0);
         } catch (StackOverflowError var8) {
            StringBuilder var3 = new StringBuilder();
            var4 = new JsonParseException(var3.append("Failed parsing JSON source: ").append(var0).append(" to Json").toString(), var8);
            throw var4;
         } catch (OutOfMemoryError var9) {
            StringBuilder var2 = new StringBuilder();
            var4 = new JsonParseException(var2.append("Failed parsing JSON source: ").append(var0).append(" to Json").toString(), var9);
            throw var4;
         }
      } finally {
         var0.setLenient(var1);
      }

      return var11;
   }

   public static JsonElement parseReader(Reader var0) throws JsonIOException, JsonSyntaxException {
      try {
         JsonReader var1 = new JsonReader(var0);
         JsonElement var5 = parseReader(var1);
         if (!var5.isJsonNull() && var1.peek() != JsonToken.END_DOCUMENT) {
            JsonSyntaxException var6 = new JsonSyntaxException("Did not consume the entire document.");
            throw var6;
         } else {
            return var5;
         }
      } catch (MalformedJsonException var2) {
         throw new JsonSyntaxException(var2);
      } catch (IOException var3) {
         throw new JsonIOException(var3);
      } catch (NumberFormatException var4) {
         throw new JsonSyntaxException(var4);
      }
   }

   public static JsonElement parseString(String var0) throws JsonSyntaxException {
      return parseReader((Reader)(new StringReader(var0)));
   }

   @Deprecated
   public JsonElement parse(JsonReader var1) throws JsonIOException, JsonSyntaxException {
      return parseReader(var1);
   }

   @Deprecated
   public JsonElement parse(Reader var1) throws JsonIOException, JsonSyntaxException {
      return parseReader(var1);
   }

   @Deprecated
   public JsonElement parse(String var1) throws JsonSyntaxException {
      return parseString(var1);
   }
}
