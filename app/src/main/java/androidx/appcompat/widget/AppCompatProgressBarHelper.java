package androidx.appcompat.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.core.graphics.drawable.WrappedDrawable;

class AppCompatProgressBarHelper {
   private static final int[] TINT_ATTRS = new int[]{16843067, 16843068};
   private Bitmap mSampleTile;
   private final ProgressBar mView;

   AppCompatProgressBarHelper(ProgressBar var1) {
      this.mView = var1;
   }

   private Shape getDrawableShape() {
      return new RoundRectShape(new float[]{5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F}, (RectF)null, (float[])null);
   }

   private Drawable tileifyIndeterminate(Drawable var1) {
      Object var4 = var1;
      if (var1 instanceof AnimationDrawable) {
         AnimationDrawable var6 = (AnimationDrawable)var1;
         int var3 = var6.getNumberOfFrames();
         var4 = new AnimationDrawable();
         ((AnimationDrawable)var4).setOneShot(var6.isOneShot());

         for(int var2 = 0; var2 < var3; ++var2) {
            Drawable var5 = this.tileify(var6.getFrame(var2), true);
            var5.setLevel(10000);
            ((AnimationDrawable)var4).addFrame(var5, var6.getDuration(var2));
         }

         ((AnimationDrawable)var4).setLevel(10000);
      }

      return (Drawable)var4;
   }

   Bitmap getSampleTile() {
      return this.mSampleTile;
   }

   void loadFromAttributes(AttributeSet var1, int var2) {
      TintTypedArray var4 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), var1, TINT_ATTRS, var2, 0);
      Drawable var3 = var4.getDrawableIfKnown(0);
      if (var3 != null) {
         this.mView.setIndeterminateDrawable(this.tileifyIndeterminate(var3));
      }

      var3 = var4.getDrawableIfKnown(1);
      if (var3 != null) {
         this.mView.setProgressDrawable(this.tileify(var3, false));
      }

      var4.recycle();
   }

   Drawable tileify(Drawable var1, boolean var2) {
      if (var1 instanceof WrappedDrawable) {
         WrappedDrawable var8 = (WrappedDrawable)var1;
         Drawable var7 = var8.getWrappedDrawable();
         if (var7 != null) {
            var8.setWrappedDrawable(this.tileify(var7, var2));
         }
      } else {
         if (var1 instanceof LayerDrawable) {
            LayerDrawable var11 = (LayerDrawable)var1;
            int var5 = var11.getNumberOfLayers();
            Drawable[] var13 = new Drawable[var5];
            byte var4 = 0;

            int var3;
            for(var3 = 0; var3 < var5; ++var3) {
               int var6 = var11.getId(var3);
               Drawable var17 = var11.getDrawable(var3);
               if (var6 != 16908301 && var6 != 16908303) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               var13[var3] = this.tileify(var17, var2);
            }

            LayerDrawable var14 = new LayerDrawable(var13);

            for(var3 = var4; var3 < var5; ++var3) {
               var14.setId(var3, var11.getId(var3));
               if (VERSION.SDK_INT >= 23) {
                  AppCompatProgressBarHelper.Api23Impl.transferLayerProperties(var11, var14, var3);
               }
            }

            return var14;
         }

         if (var1 instanceof BitmapDrawable) {
            BitmapDrawable var9 = (BitmapDrawable)var1;
            Bitmap var15 = var9.getBitmap();
            if (this.mSampleTile == null) {
               this.mSampleTile = var15;
            }

            ShapeDrawable var12 = new ShapeDrawable(this.getDrawableShape());
            BitmapShader var16 = new BitmapShader(var15, TileMode.REPEAT, TileMode.CLAMP);
            var12.getPaint().setShader(var16);
            var12.getPaint().setColorFilter(var9.getPaint().getColorFilter());
            Object var10 = var12;
            if (var2) {
               var10 = new ClipDrawable(var12, 3, 1);
            }

            return (Drawable)var10;
         }
      }

      return var1;
   }

   private static class Api23Impl {
      public static void transferLayerProperties(LayerDrawable var0, LayerDrawable var1, int var2) {
         var1.setLayerGravity(var2, var0.getLayerGravity(var2));
         var1.setLayerWidth(var2, var0.getLayerWidth(var2));
         var1.setLayerHeight(var2, var0.getLayerHeight(var2));
         var1.setLayerInsetLeft(var2, var0.getLayerInsetLeft(var2));
         var1.setLayerInsetRight(var2, var0.getLayerInsetRight(var2));
         var1.setLayerInsetTop(var2, var0.getLayerInsetTop(var2));
         var1.setLayerInsetBottom(var2, var0.getLayerInsetBottom(var2));
         var1.setLayerInsetStart(var2, var0.getLayerInsetStart(var2));
         var1.setLayerInsetEnd(var2, var0.getLayerInsetEnd(var2));
      }
   }
}
