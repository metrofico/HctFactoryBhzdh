package androidx.core.text;

import android.text.Html;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0086\b\u001a\u0017\u0010\t\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0086\b¨\u0006\u000b"},
   d2 = {"parseAsHtml", "Landroid/text/Spanned;", "", "flags", "", "imageGetter", "Landroid/text/Html$ImageGetter;", "tagHandler", "Landroid/text/Html$TagHandler;", "toHtml", "option", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class HtmlKt {
   public static final Spanned parseAsHtml(String var0, int var1, Html.ImageGetter var2, Html.TagHandler var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$parseAsHtml");
      Spanned var4 = HtmlCompat.fromHtml(var0, var1, var2, var3);
      Intrinsics.checkExpressionValueIsNotNull(var4, "HtmlCompat.fromHtml(this… imageGetter, tagHandler)");
      return var4;
   }

   // $FF: synthetic method
   public static Spanned parseAsHtml$default(String var0, int var1, Html.ImageGetter var2, Html.TagHandler var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = (Html.ImageGetter)null;
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = (Html.TagHandler)null;
         var3 = null;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$parseAsHtml");
      Spanned var6 = HtmlCompat.fromHtml(var0, var1, var2, var3);
      Intrinsics.checkExpressionValueIsNotNull(var6, "HtmlCompat.fromHtml(this… imageGetter, tagHandler)");
      return var6;
   }

   public static final String toHtml(Spanned var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toHtml");
      String var2 = HtmlCompat.toHtml(var0, var1);
      Intrinsics.checkExpressionValueIsNotNull(var2, "HtmlCompat.toHtml(this, option)");
      return var2;
   }

   // $FF: synthetic method
   public static String toHtml$default(Spanned var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 0;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$toHtml");
      String var4 = HtmlCompat.toHtml(var0, var1);
      Intrinsics.checkExpressionValueIsNotNull(var4, "HtmlCompat.toHtml(this, option)");
      return var4;
   }
}
