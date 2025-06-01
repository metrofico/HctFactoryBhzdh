package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.core.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class GradientColorInflaterCompat {
   private static final int TILE_MODE_CLAMP = 0;
   private static final int TILE_MODE_MIRROR = 2;
   private static final int TILE_MODE_REPEAT = 1;

   private GradientColorInflaterCompat() {
   }

   private static ColorStops checkColors(ColorStops var0, int var1, int var2, boolean var3, int var4) {
      if (var0 != null) {
         return var0;
      } else {
         return var3 ? new ColorStops(var1, var4, var2) : new ColorStops(var1, var2);
      }
   }

   static Shader createFromXml(Resources var0, XmlPullParser var1, Resources.Theme var2) throws XmlPullParserException, IOException {
      AttributeSet var4 = Xml.asAttributeSet(var1);

      int var3;
      do {
         var3 = var1.next();
      } while(var3 != 2 && var3 != 1);

      if (var3 == 2) {
         return createFromXmlInner(var0, var1, var4, var2);
      } else {
         throw new XmlPullParserException("No start tag found");
      }
   }

   static Shader createFromXmlInner(Resources var0, XmlPullParser var1, AttributeSet var2, Resources.Theme var3) throws IOException, XmlPullParserException {
      String var17 = var1.getName();
      if (var17.equals("gradient")) {
         TypedArray var19 = TypedArrayUtils.obtainAttributes(var0, var3, var2, R.styleable.GradientColor);
         float var4 = TypedArrayUtils.getNamedFloat(var19, var1, "startX", R.styleable.GradientColor_android_startX, 0.0F);
         float var7 = TypedArrayUtils.getNamedFloat(var19, var1, "startY", R.styleable.GradientColor_android_startY, 0.0F);
         float var6 = TypedArrayUtils.getNamedFloat(var19, var1, "endX", R.styleable.GradientColor_android_endX, 0.0F);
         float var5 = TypedArrayUtils.getNamedFloat(var19, var1, "endY", R.styleable.GradientColor_android_endY, 0.0F);
         float var9 = TypedArrayUtils.getNamedFloat(var19, var1, "centerX", R.styleable.GradientColor_android_centerX, 0.0F);
         float var10 = TypedArrayUtils.getNamedFloat(var19, var1, "centerY", R.styleable.GradientColor_android_centerY, 0.0F);
         int var13 = TypedArrayUtils.getNamedInt(var19, var1, "type", R.styleable.GradientColor_android_type, 0);
         int var15 = TypedArrayUtils.getNamedColor(var19, var1, "startColor", R.styleable.GradientColor_android_startColor, 0);
         boolean var16 = TypedArrayUtils.hasAttribute(var1, "centerColor");
         int var11 = TypedArrayUtils.getNamedColor(var19, var1, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
         int var12 = TypedArrayUtils.getNamedColor(var19, var1, "endColor", R.styleable.GradientColor_android_endColor, 0);
         int var14 = TypedArrayUtils.getNamedInt(var19, var1, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
         float var8 = TypedArrayUtils.getNamedFloat(var19, var1, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0F);
         var19.recycle();
         ColorStops var18 = checkColors(inflateChildElements(var0, var1, var2, var3), var15, var12, var16, var11);
         if (var13 != 1) {
            return (Shader)(var13 != 2 ? new LinearGradient(var4, var7, var6, var5, var18.mColors, var18.mOffsets, parseTileMode(var14)) : new SweepGradient(var9, var10, var18.mColors, var18.mOffsets));
         } else if (!(var8 <= 0.0F)) {
            return new RadialGradient(var9, var10, var8, var18.mColors, var18.mOffsets, parseTileMode(var14));
         } else {
            throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
         }
      } else {
         throw new XmlPullParserException(var1.getPositionDescription() + ": invalid gradient color tag " + var17);
      }
   }

   private static ColorStops inflateChildElements(Resources var0, XmlPullParser var1, AttributeSet var2, Resources.Theme var3) throws XmlPullParserException, IOException {
      int var5 = var1.getDepth() + 1;
      ArrayList var11 = new ArrayList(20);
      ArrayList var12 = new ArrayList(20);

      while(true) {
         int var6 = var1.next();
         if (var6 == 1) {
            break;
         }

         int var7 = var1.getDepth();
         if (var7 < var5 && var6 == 3) {
            break;
         }

         if (var6 == 2 && var7 <= var5 && var1.getName().equals("item")) {
            TypedArray var10 = TypedArrayUtils.obtainAttributes(var0, var3, var2, R.styleable.GradientColorItem);
            boolean var8 = var10.hasValue(R.styleable.GradientColorItem_android_color);
            boolean var9 = var10.hasValue(R.styleable.GradientColorItem_android_offset);
            if (!var8 || !var9) {
               throw new XmlPullParserException(var1.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
            }

            var6 = var10.getColor(R.styleable.GradientColorItem_android_color, 0);
            float var4 = var10.getFloat(R.styleable.GradientColorItem_android_offset, 0.0F);
            var10.recycle();
            var12.add(var6);
            var11.add(var4);
         }
      }

      return var12.size() > 0 ? new ColorStops(var12, var11) : null;
   }

   private static Shader.TileMode parseTileMode(int var0) {
      if (var0 != 1) {
         return var0 != 2 ? TileMode.CLAMP : TileMode.MIRROR;
      } else {
         return TileMode.REPEAT;
      }
   }

   static final class ColorStops {
      final int[] mColors;
      final float[] mOffsets;

      ColorStops(int var1, int var2) {
         this.mColors = new int[]{var1, var2};
         this.mOffsets = new float[]{0.0F, 1.0F};
      }

      ColorStops(int var1, int var2, int var3) {
         this.mColors = new int[]{var1, var2, var3};
         this.mOffsets = new float[]{0.0F, 0.5F, 1.0F};
      }

      ColorStops(List var1, List var2) {
         int var4 = var1.size();
         this.mColors = new int[var4];
         this.mOffsets = new float[var4];

         for(int var3 = 0; var3 < var4; ++var3) {
            this.mColors[var3] = (Integer)var1.get(var3);
            this.mOffsets[var3] = (Float)var2.get(var3);
         }

      }
   }
}
