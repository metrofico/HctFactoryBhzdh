package org.apache.log4j.lf5.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class StreamUtils {
   public static final int DEFAULT_BUFFER_SIZE = 2048;

   public static void copy(InputStream var0, OutputStream var1) throws IOException {
      copy(var0, var1, 2048);
   }

   public static void copy(InputStream var0, OutputStream var1, int var2) throws IOException {
      byte[] var3 = new byte[var2];

      for(var2 = var0.read(var3); var2 != -1; var2 = var0.read(var3)) {
         var1.write(var3, 0, var2);
      }

      var1.flush();
   }

   public static void copyThenClose(InputStream var0, OutputStream var1) throws IOException {
      copy(var0, var1);
      var0.close();
      var1.close();
   }

   public static byte[] getBytes(InputStream var0) throws IOException {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      copy(var0, var1);
      var1.close();
      return var1.toByteArray();
   }
}
