package androidx.core.net;

import android.net.Uri;

public final class UriCompat {
   private UriCompat() {
   }

   public static String toSafeString(Uri var0) {
      String var5 = var0.getScheme();
      String var4 = var0.getSchemeSpecificPart();
      String var3 = var4;
      StringBuilder var7;
      if (var5 != null) {
         label83: {
            if (var5.equalsIgnoreCase("tel") || var5.equalsIgnoreCase("sip") || var5.equalsIgnoreCase("sms") || var5.equalsIgnoreCase("smsto") || var5.equalsIgnoreCase("mailto") || var5.equalsIgnoreCase("nfc")) {
               var7 = new StringBuilder(64);
               var7.append(var5);
               var7.append(':');
               if (var4 != null) {
                  for(int var2 = 0; var2 < var4.length(); ++var2) {
                     char var1 = var4.charAt(var2);
                     if (var1 != '-' && var1 != '@' && var1 != '.') {
                        var7.append('x');
                     } else {
                        var7.append(var1);
                     }
                  }
               }

               return var7.toString();
            }

            if (!var5.equalsIgnoreCase("http") && !var5.equalsIgnoreCase("https") && !var5.equalsIgnoreCase("ftp")) {
               var3 = var4;
               if (!var5.equalsIgnoreCase("rtsp")) {
                  break label83;
               }
            }

            StringBuilder var6 = (new StringBuilder()).append("//");
            var3 = var0.getHost();
            var4 = "";
            if (var3 != null) {
               var3 = var0.getHost();
            } else {
               var3 = "";
            }

            var6 = var6.append(var3);
            var3 = var4;
            if (var0.getPort() != -1) {
               var3 = ":" + var0.getPort();
            }

            var3 = var6.append(var3).append("/...").toString();
         }
      }

      var7 = new StringBuilder(64);
      if (var5 != null) {
         var7.append(var5);
         var7.append(':');
      }

      if (var3 != null) {
         var7.append(var3);
      }

      return var7.toString();
   }
}
