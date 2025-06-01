package com.tencent.bugly.proguard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class bj implements bi {
   public final byte[] a(byte[] var1) throws Exception {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      GZIPOutputStream var3 = new GZIPOutputStream(var2);
      var3.write(var1);
      var3.finish();
      var3.close();
      var1 = var2.toByteArray();
      var2.close();
      return var1;
   }

   public final byte[] b(byte[] var1) throws Exception {
      ByteArrayInputStream var3 = new ByteArrayInputStream(var1);
      GZIPInputStream var4 = new GZIPInputStream(var3);
      byte[] var5 = new byte[1024];
      ByteArrayOutputStream var6 = new ByteArrayOutputStream();

      while(true) {
         int var2 = var4.read(var5, 0, 1024);
         if (var2 == -1) {
            var5 = var6.toByteArray();
            var6.flush();
            var6.close();
            var4.close();
            var3.close();
            return var5;
         }

         var6.write(var5, 0, var2);
      }
   }
}
