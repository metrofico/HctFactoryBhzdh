package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;

public class SidePropagation extends VisibilityPropagation {
   private float mPropagationSpeed = 3.0F;
   private int mSide = 80;

   private int distance(View var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      byte var11;
      int var14;
      label44: {
         label43: {
            int var13 = this.mSide;
            var11 = 0;
            boolean var12 = true;
            boolean var10 = true;
            if (var13 == 8388611) {
               if (ViewCompat.getLayoutDirection(var1) != 1) {
                  var10 = false;
               }

               if (var10) {
                  break label43;
               }
            } else {
               var14 = var13;
               if (var13 != 8388613) {
                  break label44;
               }

               if (ViewCompat.getLayoutDirection(var1) == 1) {
                  var10 = var12;
               } else {
                  var10 = false;
               }

               if (!var10) {
                  break label43;
               }
            }

            var14 = 3;
            break label44;
         }

         var14 = 5;
      }

      if (var14 != 3) {
         if (var14 != 5) {
            if (var14 != 48) {
               if (var14 != 80) {
                  var2 = var11;
               } else {
                  var2 = var3 - var7 + Math.abs(var4 - var2);
               }
            } else {
               var2 = var9 - var3 + Math.abs(var4 - var2);
            }
         } else {
            var2 = var2 - var6 + Math.abs(var5 - var3);
         }
      } else {
         var2 = var8 - var2 + Math.abs(var5 - var3);
      }

      return var2;
   }

   private int getMaxDistance(ViewGroup var1) {
      int var2 = this.mSide;
      return var2 != 3 && var2 != 5 && var2 != 8388611 && var2 != 8388613 ? var1.getHeight() : var1.getWidth();
   }

   public long getStartDelay(ViewGroup var1, Transition var2, TransitionValues var3, TransitionValues var4) {
      if (var3 == null && var4 == null) {
         return 0L;
      } else {
         Rect var20 = var2.getEpicenter();
         byte var6;
         if (var4 != null && this.getViewVisibility(var3) != 0) {
            var6 = 1;
            var3 = var4;
         } else {
            var6 = -1;
         }

         int var12 = this.getViewX(var3);
         int var14 = this.getViewY(var3);
         int[] var21 = new int[2];
         var1.getLocationOnScreen(var21);
         int var15 = var21[0] + Math.round(var1.getTranslationX());
         int var10 = var21[1] + Math.round(var1.getTranslationY());
         int var13 = var15 + var1.getWidth();
         int var11 = var10 + var1.getHeight();
         int var7;
         int var8;
         int var9;
         if (var20 != null) {
            var9 = var20.centerX();
            var7 = var20.centerY();
            var8 = var7;
         } else {
            var7 = (var15 + var13) / 2;
            var8 = (var10 + var11) / 2;
            var9 = var7;
         }

         float var5 = (float)this.distance(var1, var12, var14, var9, var8, var15, var10, var13, var11) / (float)this.getMaxDistance(var1);
         long var18 = var2.getDuration();
         long var16 = var18;
         if (var18 < 0L) {
            var16 = 300L;
         }

         return (long)Math.round((float)(var16 * (long)var6) / this.mPropagationSpeed * var5);
      }
   }

   public void setPropagationSpeed(float var1) {
      if (var1 != 0.0F) {
         this.mPropagationSpeed = var1;
      } else {
         throw new IllegalArgumentException("propagationSpeed may not be 0");
      }
   }

   public void setSide(int var1) {
      this.mSide = var1;
   }
}
