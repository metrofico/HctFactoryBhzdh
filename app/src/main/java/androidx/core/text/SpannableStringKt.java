package androidx.core.text;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a%\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0086\n\u001a\u001d\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0086\n\u001a\r\u0010\u000b\u001a\u00020\u0002*\u00020\fH\u0086\b¨\u0006\r"},
   d2 = {"clearSpans", "", "Landroid/text/Spannable;", "set", "start", "", "end", "span", "", "range", "Lkotlin/ranges/IntRange;", "toSpannable", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SpannableStringKt {
   public static final void clearSpans(Spannable var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$clearSpans");
      Spanned var3 = (Spanned)var0;
      int var2 = var3.length();
      int var1 = 0;
      Object[] var4 = var3.getSpans(0, var2, Object.class);
      Intrinsics.checkExpressionValueIsNotNull(var4, "getSpans(start, end, T::class.java)");

      for(var2 = var4.length; var1 < var2; ++var1) {
         var0.removeSpan(var4[var1]);
      }

   }

   public static final void set(Spannable var0, int var1, int var2, Object var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$set");
      Intrinsics.checkParameterIsNotNull(var3, "span");
      var0.setSpan(var3, var1, var2, 17);
   }

   public static final void set(Spannable var0, IntRange var1, Object var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$set");
      Intrinsics.checkParameterIsNotNull(var1, "range");
      Intrinsics.checkParameterIsNotNull(var2, "span");
      var0.setSpan(var2, var1.getStart(), var1.getEndInclusive(), 17);
   }

   public static final Spannable toSpannable(CharSequence var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toSpannable");
      SpannableString var1 = SpannableString.valueOf(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "SpannableString.valueOf(this)");
      return (Spannable)var1;
   }
}
