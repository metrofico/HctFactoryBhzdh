package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.R;
import androidx.core.math.MathUtils;
import androidx.core.os.BuildCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ColorStateListInflaterCompat {
   private static final ThreadLocal sTempTypedValue = new ThreadLocal();

   private ColorStateListInflaterCompat() {
   }

   public static ColorStateList createFromXml(Resources var0, XmlPullParser var1, Resources.Theme var2) throws XmlPullParserException, IOException {
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

   public static ColorStateList createFromXmlInner(Resources var0, XmlPullParser var1, AttributeSet var2, Resources.Theme var3) throws XmlPullParserException, IOException {
      String var4 = var1.getName();
      if (var4.equals("selector")) {
         return inflate(var0, var1, var2, var3);
      } else {
         throw new XmlPullParserException(var1.getPositionDescription() + ": invalid color state list tag " + var4);
      }
   }

   private static TypedValue getTypedValue() {
      ThreadLocal var2 = sTempTypedValue;
      TypedValue var1 = (TypedValue)var2.get();
      TypedValue var0 = var1;
      if (var1 == null) {
         var0 = new TypedValue();
         var2.set(var0);
      }

      return var0;
   }

   public static ColorStateList inflate(Resources var0, int var1, Resources.Theme var2) {
      try {
         ColorStateList var4 = createFromXml(var0, var0.getXml(var1), var2);
         return var4;
      } catch (Exception var3) {
         Log.e("CSLCompat", "Failed to inflate ColorStateList.", var3);
         return null;
      }
   }

   private static ColorStateList inflate(Resources var0, XmlPullParser var1, AttributeSet var2, Resources.Theme var3) throws XmlPullParserException, IOException {
      int var12 = var1.getDepth() + 1;
      int[][] var14 = new int[20][];
      int[] var15 = new int[20];
      int var7 = 0;

      while(true) {
         int var9 = var1.next();
         if (var9 == 1) {
            break;
         }

         int var8 = var1.getDepth();
         if (var8 < var12 && var9 == 3) {
            break;
         }

         int[] var17 = var15;
         int[][] var16 = var14;
         int var6 = var7;
         if (var9 == 2) {
            var17 = var15;
            var16 = var14;
            var6 = var7;
            if (var8 <= var12) {
               if (!var1.getName().equals("item")) {
                  var17 = var15;
                  var16 = var14;
                  var6 = var7;
               } else {
                  TypedArray var22 = obtainAttributes(var0, var3, var2, R.styleable.ColorStateListItem);
                  var6 = var22.getResourceId(R.styleable.ColorStateListItem_android_color, -1);
                  if (var6 != -1 && !isColorInt(var0, var6)) {
                     try {
                        var6 = createFromXml(var0, var0.getXml(var6), var3).getDefaultColor();
                     } catch (Exception var18) {
                        var6 = var22.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                     }
                  } else {
                     var6 = var22.getColor(R.styleable.ColorStateListItem_android_color, -65281);
                  }

                  float var4 = 1.0F;
                  if (var22.hasValue(R.styleable.ColorStateListItem_android_alpha)) {
                     var4 = var22.getFloat(R.styleable.ColorStateListItem_android_alpha, 1.0F);
                  } else if (var22.hasValue(R.styleable.ColorStateListItem_alpha)) {
                     var4 = var22.getFloat(R.styleable.ColorStateListItem_alpha, 1.0F);
                  }

                  float var5;
                  if (BuildCompat.isAtLeastS() && var22.hasValue(R.styleable.ColorStateListItem_android_lStar)) {
                     var5 = var22.getFloat(R.styleable.ColorStateListItem_android_lStar, -1.0F);
                  } else {
                     var5 = var22.getFloat(R.styleable.ColorStateListItem_lStar, -1.0F);
                  }

                  var22.recycle();
                  int var13 = var2.getAttributeCount();
                  int[] var21 = new int[var13];
                  var8 = 0;

                  int var10;
                  for(var9 = 0; var8 < var13; var9 = var10) {
                     int var11 = var2.getAttributeNameResource(var8);
                     var10 = var9;
                     if (var11 != 16843173) {
                        var10 = var9;
                        if (var11 != 16843551) {
                           var10 = var9;
                           if (var11 != R.attr.alpha) {
                              var10 = var9;
                              if (var11 != R.attr.lStar) {
                                 if (var2.getAttributeBooleanValue(var8, false)) {
                                    var10 = var11;
                                 } else {
                                    var10 = -var11;
                                 }

                                 var21[var9] = var10;
                                 var10 = var9 + 1;
                              }
                           }
                        }
                     }

                     ++var8;
                  }

                  var21 = StateSet.trimStateSet(var21, var9);
                  var17 = GrowingArrayUtils.append(var15, var7, modulateColorAlpha(var6, var4, var5));
                  var16 = (int[][])GrowingArrayUtils.append(var14, var7, var21);
                  var6 = var7 + 1;
               }
            }
         }

         var15 = var17;
         var14 = var16;
         var7 = var6;
      }

      int[] var20 = new int[var7];
      int[][] var19 = new int[var7][];
      System.arraycopy(var15, 0, var20, 0, var7);
      System.arraycopy(var14, 0, var19, 0, var7);
      return new ColorStateList(var19, var20);
   }

   private static boolean isColorInt(Resources var0, int var1) {
      TypedValue var3 = getTypedValue();
      boolean var2 = true;
      var0.getValue(var1, var3, true);
      if (var3.type < 28 || var3.type > 31) {
         var2 = false;
      }

      return var2;
   }

   private static int modulateColorAlpha(int var0, float var1, float var2) {
      boolean var3;
      if (var2 >= 0.0F && var2 <= 100.0F) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var1 == 1.0F && !var3) {
         return var0;
      } else {
         int var5 = MathUtils.clamp((int)((float)Color.alpha(var0) * var1 + 0.5F), 0, 255);
         int var4 = var0;
         if (var3) {
            CamColor var6 = CamColor.fromColor(var0);
            var4 = CamColor.toColor(var6.getHue(), var6.getChroma(), var2);
         }

         return var4 & 16777215 | var5 << 24;
      }
   }

   private static TypedArray obtainAttributes(Resources var0, Resources.Theme var1, AttributeSet var2, int[] var3) {
      TypedArray var4;
      if (var1 == null) {
         var4 = var0.obtainAttributes(var2, var3);
      } else {
         var4 = var1.obtainStyledAttributes(var2, var3, 0, 0);
      }

      return var4;
   }
}
