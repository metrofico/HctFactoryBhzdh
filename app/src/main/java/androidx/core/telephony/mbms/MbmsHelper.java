package androidx.core.telephony.mbms;

import android.content.Context;
import android.os.LocaleList;
import android.os.Build.VERSION;
import android.telephony.mbms.ServiceInfo;
import java.util.Iterator;
import java.util.Locale;

public final class MbmsHelper {
   private MbmsHelper() {
   }

   public static CharSequence getBestNameForService(Context var0, ServiceInfo var1) {
      int var2 = VERSION.SDK_INT;
      Object var3 = null;
      if (var2 < 28) {
         return null;
      } else {
         LocaleList var6 = var0.getResources().getConfiguration().getLocales();
         var2 = var1.getNamedContentLocales().size();
         if (var2 == 0) {
            return null;
         } else {
            String[] var5 = new String[var2];
            var2 = 0;

            for(Iterator var4 = var1.getNamedContentLocales().iterator(); var4.hasNext(); ++var2) {
               var5[var2] = ((Locale)var4.next()).toLanguageTag();
            }

            Locale var7 = var6.getFirstMatch(var5);
            CharSequence var8;
            if (var7 == null) {
               var8 = (CharSequence)var3;
            } else {
               var8 = var1.getNameForLocale(var7);
            }

            return var8;
         }
      }
   }
}
