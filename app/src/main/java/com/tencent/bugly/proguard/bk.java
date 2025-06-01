package com.tencent.bugly.proguard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class bk implements bi {
   public final byte[] a(byte[] var1) throws Exception {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      ZipOutputStream var3 = new ZipOutputStream(var2);
      ZipEntry var4 = new ZipEntry("zip");
      var4.setSize((long)var1.length);
      var3.putNextEntry(var4);
      var3.write(var1);
      var3.closeEntry();
      var3.close();
      var1 = var2.toByteArray();
      var2.close();
      return var1;
   }

   public final byte[] b(byte[] var1) throws Exception {
      ByteArrayInputStream var3 = new ByteArrayInputStream(var1);
      ZipInputStream var4 = new ZipInputStream(var3);
      var1 = null;

      while(var4.getNextEntry() != null) {
         var1 = new byte[1024];
         ByteArrayOutputStream var5 = new ByteArrayOutputStream();

         while(true) {
            int var2 = var4.read(var1, 0, 1024);
            if (var2 == -1) {
               var1 = var5.toByteArray();
               var5.flush();
               var5.close();
               break;
            }

            var5.write(var1, 0, var2);
         }
      }

      var4.close();
      var3.close();
      return var1;
   }
}
