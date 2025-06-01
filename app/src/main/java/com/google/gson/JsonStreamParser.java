package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser implements Iterator {
   private final Object lock;
   private final JsonReader parser;

   public JsonStreamParser(Reader var1) {
      JsonReader var2 = new JsonReader(var1);
      this.parser = var2;
      var2.setLenient(true);
      this.lock = new Object();
   }

   public JsonStreamParser(String var1) {
      this((Reader)(new StringReader(var1)));
   }

   public boolean hasNext() {
      Object var2 = this.lock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label297: {
         JsonToken var48;
         JsonToken var51;
         label292: {
            IOException var4;
            label291: {
               MalformedJsonException var47;
               try {
                  try {
                     var51 = this.parser.peek();
                     var48 = JsonToken.END_DOCUMENT;
                     break label292;
                  } catch (MalformedJsonException var44) {
                     var47 = var44;
                  } catch (IOException var45) {
                     var4 = var45;
                     break label291;
                  }
               } catch (Throwable var46) {
                  var10000 = var46;
                  var10001 = false;
                  break label297;
               }

               try {
                  JsonSyntaxException var50 = new JsonSyntaxException(var47);
                  throw var50;
               } catch (Throwable var42) {
                  var10000 = var42;
                  var10001 = false;
                  break label297;
               }
            }

            try {
               JsonIOException var3 = new JsonIOException(var4);
               throw var3;
            } catch (Throwable var41) {
               var10000 = var41;
               var10001 = false;
               break label297;
            }
         }

         boolean var1;
         if (var51 != var48) {
            var1 = true;
         } else {
            var1 = false;
         }

         label283:
         try {
            return var1;
         } catch (Throwable var43) {
            var10000 = var43;
            var10001 = false;
            break label283;
         }
      }

      while(true) {
         Throwable var49 = var10000;

         try {
            throw var49;
         } catch (Throwable var40) {
            var10000 = var40;
            var10001 = false;
            continue;
         }
      }
   }

   public JsonElement next() throws JsonParseException {
      if (this.hasNext()) {
         try {
            JsonElement var1 = Streams.parse(this.parser);
            return var1;
         } catch (StackOverflowError var3) {
            throw new JsonParseException("Failed parsing JSON source to Json", var3);
         } catch (OutOfMemoryError var4) {
            throw new JsonParseException("Failed parsing JSON source to Json", var4);
         } catch (JsonParseException var5) {
            Object var2 = var5;
            if (var5.getCause() instanceof EOFException) {
               var2 = new NoSuchElementException();
            }

            throw var2;
         }
      } else {
         throw new NoSuchElementException();
      }
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }
}
