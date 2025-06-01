package com.tencent.bugly.proguard;

import android.util.Pair;
import java.io.Closeable;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ad {
   private static Pair a(String param0, String param1, Map param2) {
      // $FF: Couldn't be decompiled
   }

   public static Pair a(List param0) {
      // $FF: Couldn't be decompiled
   }

   private static void a(Closeable var0) {
      if (var0 != null) {
         try {
            var0.close();
         } catch (Exception var1) {
            al.b(var1);
         }
      }
   }

   private static void a(HttpURLConnection var0, Map var1) {
      if (var0 != null && var1 != null && !var1.isEmpty()) {
         Iterator var3 = var1.entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry var2 = (Map.Entry)var3.next();
            var0.setRequestProperty((String)var2.getKey(), (String)var2.getValue());
         }
      }

   }
}
