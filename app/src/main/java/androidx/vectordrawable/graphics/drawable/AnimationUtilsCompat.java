package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimationUtilsCompat {
   private AnimationUtilsCompat() {
   }

   private static Interpolator createInterpolatorFromXml(Context var0, Resources var1, Resources.Theme var2, XmlPullParser var3) throws XmlPullParserException, IOException {
      int var5 = var3.getDepth();
      Object var6 = null;

      while(true) {
         int var4 = var3.next();
         if (var4 == 3 && var3.getDepth() <= var5 || var4 == 1) {
            return (Interpolator)var6;
         }

         if (var4 == 2) {
            AttributeSet var8 = Xml.asAttributeSet(var3);
            String var7 = var3.getName();
            if (var7.equals("linearInterpolator")) {
               var6 = new LinearInterpolator();
            } else if (var7.equals("accelerateInterpolator")) {
               var6 = new AccelerateInterpolator(var0, var8);
            } else if (var7.equals("decelerateInterpolator")) {
               var6 = new DecelerateInterpolator(var0, var8);
            } else if (var7.equals("accelerateDecelerateInterpolator")) {
               var6 = new AccelerateDecelerateInterpolator();
            } else if (var7.equals("cycleInterpolator")) {
               var6 = new CycleInterpolator(var0, var8);
            } else if (var7.equals("anticipateInterpolator")) {
               var6 = new AnticipateInterpolator(var0, var8);
            } else if (var7.equals("overshootInterpolator")) {
               var6 = new OvershootInterpolator(var0, var8);
            } else if (var7.equals("anticipateOvershootInterpolator")) {
               var6 = new AnticipateOvershootInterpolator(var0, var8);
            } else if (var7.equals("bounceInterpolator")) {
               var6 = new BounceInterpolator();
            } else {
               if (!var7.equals("pathInterpolator")) {
                  throw new RuntimeException("Unknown interpolator name: " + var3.getName());
               }

               var6 = new PathInterpolatorCompat(var0, var8, var3);
            }
         }
      }
   }

   public static Interpolator loadInterpolator(Context param0, int param1) throws Resources.NotFoundException {
      // $FF: Couldn't be decompiled
   }
}
