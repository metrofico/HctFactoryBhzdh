package androidx.core.graphics;

import android.graphics.Color;
import android.graphics.ColorSpace;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\r\u0010\u0018\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u0018\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u0018\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001a\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001a\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001a\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001b\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001b\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001b\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\r\u0010\u001c\u001a\u00020\u0004*\u00020\u0019H\u0087\n\u001a\r\u0010\u001c\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\r\u0010\u001c\u001a\u00020\u0004*\u00020\u0005H\u0087\n\u001a\u001d\u0010\u001d\u001a\n \u001e*\u0004\u0018\u00010\u00190\u0019*\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u001d\u0010\u001d\u001a\n \u001e*\u0004\u0018\u00010\u00190\u0019*\u00020\u00192\u0006\u0010\t\u001a\u00020\u001fH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\t\u001a\u00020\u001fH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0087\f\u001a\u0015\u0010\u001d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\u001fH\u0087\f\u001a\u0015\u0010 \u001a\u00020\u0019*\u00020\u00192\u0006\u0010!\u001a\u00020\u0019H\u0087\u0002\u001a\r\u0010\"\u001a\u00020\u0019*\u00020\u0001H\u0087\b\u001a\r\u0010\"\u001a\u00020\u0019*\u00020\u0005H\u0087\b\u001a\r\u0010#\u001a\u00020\u0001*\u00020\u0005H\u0087\b\u001a\r\u0010#\u001a\u00020\u0001*\u00020$H\u0087\b\u001a\r\u0010%\u001a\u00020\u0005*\u00020\u0001H\u0087\b\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0000\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0003\"\u0016\u0010\u0007\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\"\u0016\u0010\t\u001a\u00020\n*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0016\u0010\r\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006\"\u0016\u0010\u000f\u001a\u00020\u0010*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011\"\u0016\u0010\u0012\u001a\u00020\u0010*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011\"\u0016\u0010\u0013\u001a\u00020\u0004*\u00020\u00018Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u0016\u0010\u0013\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006\"\u0016\u0010\u0016\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0003\"\u0016\u0010\u0016\u001a\u00020\u0004*\u00020\u00058Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0006¨\u0006&"},
   d2 = {"alpha", "", "getAlpha", "(I)I", "", "", "(J)F", "blue", "getBlue", "colorSpace", "Landroid/graphics/ColorSpace;", "getColorSpace", "(J)Landroid/graphics/ColorSpace;", "green", "getGreen", "isSrgb", "", "(J)Z", "isWideGamut", "luminance", "getLuminance", "(I)F", "red", "getRed", "component1", "Landroid/graphics/Color;", "component2", "component3", "component4", "convertTo", "kotlin.jvm.PlatformType", "Landroid/graphics/ColorSpace$Named;", "plus", "c", "toColor", "toColorInt", "", "toColorLong", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class ColorKt {
   public static final float component1(long var0) {
      return Color.red(var0);
   }

   public static final float component1(Color var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component1");
      return var0.getComponent(0);
   }

   public static final int component1(int var0) {
      return var0 >> 24 & 255;
   }

   public static final float component2(long var0) {
      return Color.green(var0);
   }

   public static final float component2(Color var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component2");
      return var0.getComponent(1);
   }

   public static final int component2(int var0) {
      return var0 >> 16 & 255;
   }

   public static final float component3(long var0) {
      return Color.blue(var0);
   }

   public static final float component3(Color var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component3");
      return var0.getComponent(2);
   }

   public static final int component3(int var0) {
      return var0 >> 8 & 255;
   }

   public static final float component4(long var0) {
      return Color.alpha(var0);
   }

   public static final float component4(Color var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$component4");
      return var0.getComponent(3);
   }

   public static final int component4(int var0) {
      return var0 & 255;
   }

   public static final long convertTo(int var0, ColorSpace.Named var1) {
      Intrinsics.checkParameterIsNotNull(var1, "colorSpace");
      return Color.convert(var0, ColorSpace.get(var1));
   }

   public static final long convertTo(int var0, ColorSpace var1) {
      Intrinsics.checkParameterIsNotNull(var1, "colorSpace");
      return Color.convert(var0, var1);
   }

   public static final long convertTo(long var0, ColorSpace.Named var2) {
      Intrinsics.checkParameterIsNotNull(var2, "colorSpace");
      return Color.convert(var0, ColorSpace.get(var2));
   }

   public static final long convertTo(long var0, ColorSpace var2) {
      Intrinsics.checkParameterIsNotNull(var2, "colorSpace");
      return Color.convert(var0, var2);
   }

   public static final Color convertTo(Color var0, ColorSpace.Named var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$convertTo");
      Intrinsics.checkParameterIsNotNull(var1, "colorSpace");
      return var0.convert(ColorSpace.get(var1));
   }

   public static final Color convertTo(Color var0, ColorSpace var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$convertTo");
      Intrinsics.checkParameterIsNotNull(var1, "colorSpace");
      return var0.convert(var1);
   }

   public static final float getAlpha(long var0) {
      return Color.alpha(var0);
   }

   public static final int getAlpha(int var0) {
      return var0 >> 24 & 255;
   }

   public static final float getBlue(long var0) {
      return Color.blue(var0);
   }

   public static final int getBlue(int var0) {
      return var0 & 255;
   }

   public static final ColorSpace getColorSpace(long var0) {
      ColorSpace var2 = Color.colorSpace(var0);
      Intrinsics.checkExpressionValueIsNotNull(var2, "Color.colorSpace(this)");
      return var2;
   }

   public static final float getGreen(long var0) {
      return Color.green(var0);
   }

   public static final int getGreen(int var0) {
      return var0 >> 8 & 255;
   }

   public static final float getLuminance(int var0) {
      return Color.luminance(var0);
   }

   public static final float getLuminance(long var0) {
      return Color.luminance(var0);
   }

   public static final float getRed(long var0) {
      return Color.red(var0);
   }

   public static final int getRed(int var0) {
      return var0 >> 16 & 255;
   }

   public static final boolean isSrgb(long var0) {
      return Color.isSrgb(var0);
   }

   public static final boolean isWideGamut(long var0) {
      return Color.isWideGamut(var0);
   }

   public static final Color plus(Color var0, Color var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "c");
      var0 = ColorUtils.compositeColors(var1, var0);
      Intrinsics.checkExpressionValueIsNotNull(var0, "ColorUtils.compositeColors(c, this)");
      return var0;
   }

   public static final Color toColor(int var0) {
      Color var1 = Color.valueOf(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Color.valueOf(this)");
      return var1;
   }

   public static final Color toColor(long var0) {
      Color var2 = Color.valueOf(var0);
      Intrinsics.checkExpressionValueIsNotNull(var2, "Color.valueOf(this)");
      return var2;
   }

   public static final int toColorInt(long var0) {
      return Color.toArgb(var0);
   }

   public static final int toColorInt(String var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toColorInt");
      return Color.parseColor(var0);
   }

   public static final long toColorLong(int var0) {
      return Color.pack(var0);
   }
}
