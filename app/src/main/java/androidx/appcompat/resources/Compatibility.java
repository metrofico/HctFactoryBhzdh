package androidx.appcompat.resources;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class Compatibility {
   private Compatibility() {
   }

   public static class Api15Impl {
      private Api15Impl() {
      }

      public static void getValueForDensity(Resources var0, int var1, int var2, TypedValue var3, boolean var4) {
         var0.getValueForDensity(var1, var2, var3, var4);
      }
   }

   public static class Api18Impl {
      private Api18Impl() {
      }

      public static void setAutoCancel(ObjectAnimator var0, boolean var1) {
         var0.setAutoCancel(var1);
      }
   }

   public static class Api21Impl {
      private Api21Impl() {
      }

      public static Drawable createFromXmlInner(Resources var0, XmlPullParser var1, AttributeSet var2, Resources.Theme var3) throws IOException, XmlPullParserException {
         return Drawable.createFromXmlInner(var0, var1, var2, var3);
      }

      public static int getChangingConfigurations(TypedArray var0) {
         return var0.getChangingConfigurations();
      }

      public static void inflate(Drawable var0, Resources var1, XmlPullParser var2, AttributeSet var3, Resources.Theme var4) throws IOException, XmlPullParserException {
         var0.inflate(var1, var2, var3, var4);
      }
   }
}
