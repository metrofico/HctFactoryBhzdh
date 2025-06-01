package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.os.CancellationSignal;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.InputStream;

public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
   public Typeface createFromFontFamilyFilesResourceEntry(Context param1, FontResourcesParserCompat.FontFamilyFilesResourceEntry param2, Resources param3, int param4) {
      // $FF: Couldn't be decompiled
   }

   public Typeface createFromFontInfo(Context param1, CancellationSignal param2, FontsContractCompat.FontInfo[] param3, int param4) {
      // $FF: Couldn't be decompiled
   }

   protected Typeface createFromInputStream(Context var1, InputStream var2) {
      throw new RuntimeException("Do not use this function in API 29 or later.");
   }

   public Typeface createFromResourcesFontFile(Context var1, Resources var2, int var3, String var4, int var5) {
      try {
         Font.Builder var7 = new Font.Builder(var2, var3);
         Font var8 = var7.build();
         FontFamily.Builder var10 = new FontFamily.Builder(var8);
         FontFamily var12 = var10.build();
         Typeface.CustomFallbackBuilder var11 = new Typeface.CustomFallbackBuilder(var12);
         Typeface var9 = var11.setStyle(var8.getStyle()).build();
         return var9;
      } catch (Exception var6) {
         return null;
      }
   }

   protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] var1, int var2) {
      throw new RuntimeException("Do not use this function in API 29 or later.");
   }
}
