package androidx.core.net;

import android.net.Uri;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public final class MailTo {
   private static final String BCC = "bcc";
   private static final String BODY = "body";
   private static final String CC = "cc";
   private static final String MAILTO = "mailto";
   public static final String MAILTO_SCHEME = "mailto:";
   private static final String SUBJECT = "subject";
   private static final String TO = "to";
   private HashMap mHeaders = new HashMap();

   private MailTo() {
   }

   public static boolean isMailTo(Uri var0) {
      boolean var1;
      if (var0 != null && "mailto".equals(var0.getScheme())) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean isMailTo(String var0) {
      boolean var1;
      if (var0 != null && var0.startsWith("mailto:")) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static MailTo parse(Uri var0) throws ParseException {
      return parse(var0.toString());
   }

   public static MailTo parse(String var0) throws ParseException {
      Preconditions.checkNotNull(var0);
      if (!isMailTo(var0)) {
         throw new ParseException("Not a mailto scheme");
      } else {
         int var1 = var0.indexOf(35);
         String var3 = var0;
         if (var1 != -1) {
            var3 = var0.substring(0, var1);
         }

         var1 = var3.indexOf(63);
         if (var1 == -1) {
            var0 = Uri.decode(var3.substring(7));
            var3 = null;
         } else {
            var0 = Uri.decode(var3.substring(7, var1));
            var3 = var3.substring(var1 + 1);
         }

         MailTo var4 = new MailTo();
         String var5;
         if (var3 != null) {
            String[] var6 = var3.split("&");
            int var2 = var6.length;

            for(var1 = 0; var1 < var2; ++var1) {
               String[] var7 = var6[var1].split("=", 2);
               if (var7.length != 0) {
                  var5 = Uri.decode(var7[0]).toLowerCase(Locale.ROOT);
                  if (var7.length > 1) {
                     var3 = Uri.decode(var7[1]);
                  } else {
                     var3 = null;
                  }

                  var4.mHeaders.put(var5, var3);
               }
            }
         }

         var5 = var4.getTo();
         var3 = var0;
         if (var5 != null) {
            var3 = var0 + ", " + var5;
         }

         var4.mHeaders.put("to", var3);
         return var4;
      }
   }

   public String getBcc() {
      return (String)this.mHeaders.get("bcc");
   }

   public String getBody() {
      return (String)this.mHeaders.get("body");
   }

   public String getCc() {
      return (String)this.mHeaders.get("cc");
   }

   public Map getHeaders() {
      return this.mHeaders;
   }

   public String getSubject() {
      return (String)this.mHeaders.get("subject");
   }

   public String getTo() {
      return (String)this.mHeaders.get("to");
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder("mailto:");
      var1.append('?');
      Iterator var3 = this.mHeaders.entrySet().iterator();

      while(var3.hasNext()) {
         Map.Entry var2 = (Map.Entry)var3.next();
         var1.append(Uri.encode((String)var2.getKey()));
         var1.append('=');
         var1.append(Uri.encode((String)var2.getValue()));
         var1.append('&');
      }

      return var1.toString();
   }
}
