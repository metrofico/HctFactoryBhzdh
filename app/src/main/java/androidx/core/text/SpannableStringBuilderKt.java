package androidx.core.text;

import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a0\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\t2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a&\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a0\u0010\b\u001a\u00020\u0004*\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\t2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a.\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a?\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u000f\"\u00020\r2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b¢\u0006\u0002\u0010\u0010\u001a&\u0010\u0011\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a.\u0010\u0012\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a&\u0010\u0015\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a&\u0010\u0016\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a&\u0010\u0017\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b\u001a&\u0010\u0018\u001a\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\b¨\u0006\u0019"},
   d2 = {"buildSpannedString", "Landroid/text/SpannedString;", "builderAction", "Lkotlin/Function1;", "Landroid/text/SpannableStringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "backgroundColor", "color", "", "bold", "inSpans", "span", "", "spans", "", "(Landroid/text/SpannableStringBuilder;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroid/text/SpannableStringBuilder;", "italic", "scale", "proportion", "", "strikeThrough", "subscript", "superscript", "underline", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SpannableStringBuilderKt {
   public static final SpannableStringBuilder backgroundColor(SpannableStringBuilder var0, int var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$backgroundColor");
      Intrinsics.checkParameterIsNotNull(var2, "builderAction");
      BackgroundColorSpan var3 = new BackgroundColorSpan(var1);
      var1 = var0.length();
      var2.invoke(var0);
      var0.setSpan(var3, var1, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder bold(SpannableStringBuilder var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$bold");
      Intrinsics.checkParameterIsNotNull(var1, "builderAction");
      StyleSpan var3 = new StyleSpan(1);
      int var2 = var0.length();
      var1.invoke(var0);
      var0.setSpan(var3, var2, var0.length(), 17);
      return var0;
   }

   public static final SpannedString buildSpannedString(Function1 var0) {
      Intrinsics.checkParameterIsNotNull(var0, "builderAction");
      SpannableStringBuilder var1 = new SpannableStringBuilder();
      var0.invoke(var1);
      return new SpannedString((CharSequence)var1);
   }

   public static final SpannableStringBuilder color(SpannableStringBuilder var0, int var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$color");
      Intrinsics.checkParameterIsNotNull(var2, "builderAction");
      ForegroundColorSpan var3 = new ForegroundColorSpan(var1);
      var1 = var0.length();
      var2.invoke(var0);
      var0.setSpan(var3, var1, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder inSpans(SpannableStringBuilder var0, Object var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$inSpans");
      Intrinsics.checkParameterIsNotNull(var1, "span");
      Intrinsics.checkParameterIsNotNull(var2, "builderAction");
      int var3 = var0.length();
      var2.invoke(var0);
      var0.setSpan(var1, var3, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder inSpans(SpannableStringBuilder var0, Object[] var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$inSpans");
      Intrinsics.checkParameterIsNotNull(var1, "spans");
      Intrinsics.checkParameterIsNotNull(var2, "builderAction");
      int var4 = var0.length();
      var2.invoke(var0);
      int var5 = var1.length;

      for(int var3 = 0; var3 < var5; ++var3) {
         var0.setSpan(var1[var3], var4, var0.length(), 17);
      }

      return var0;
   }

   public static final SpannableStringBuilder italic(SpannableStringBuilder var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$italic");
      Intrinsics.checkParameterIsNotNull(var1, "builderAction");
      StyleSpan var3 = new StyleSpan(2);
      int var2 = var0.length();
      var1.invoke(var0);
      var0.setSpan(var3, var2, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder scale(SpannableStringBuilder var0, float var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$scale");
      Intrinsics.checkParameterIsNotNull(var2, "builderAction");
      RelativeSizeSpan var4 = new RelativeSizeSpan(var1);
      int var3 = var0.length();
      var2.invoke(var0);
      var0.setSpan(var4, var3, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder strikeThrough(SpannableStringBuilder var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$strikeThrough");
      Intrinsics.checkParameterIsNotNull(var1, "builderAction");
      StrikethroughSpan var3 = new StrikethroughSpan();
      int var2 = var0.length();
      var1.invoke(var0);
      var0.setSpan(var3, var2, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder subscript(SpannableStringBuilder var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$subscript");
      Intrinsics.checkParameterIsNotNull(var1, "builderAction");
      SubscriptSpan var3 = new SubscriptSpan();
      int var2 = var0.length();
      var1.invoke(var0);
      var0.setSpan(var3, var2, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder superscript(SpannableStringBuilder var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$superscript");
      Intrinsics.checkParameterIsNotNull(var1, "builderAction");
      SuperscriptSpan var3 = new SuperscriptSpan();
      int var2 = var0.length();
      var1.invoke(var0);
      var0.setSpan(var3, var2, var0.length(), 17);
      return var0;
   }

   public static final SpannableStringBuilder underline(SpannableStringBuilder var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$underline");
      Intrinsics.checkParameterIsNotNull(var1, "builderAction");
      UnderlineSpan var3 = new UnderlineSpan();
      int var2 = var0.length();
      var1.invoke(var0);
      var0.setSpan(var3, var2, var0.length(), 17);
      return var0;
   }
}
