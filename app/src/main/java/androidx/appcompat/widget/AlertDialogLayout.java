package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import androidx.appcompat.R;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;

public class AlertDialogLayout extends LinearLayoutCompat {
   public AlertDialogLayout(Context var1) {
      super(var1);
   }

   public AlertDialogLayout(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void forceUniformWidth(int var1, int var2) {
      int var4 = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);

      for(int var3 = 0; var3 < var1; ++var3) {
         View var6 = this.getChildAt(var3);
         if (var6.getVisibility() != 8) {
            LinearLayoutCompat.LayoutParams var7 = (LinearLayoutCompat.LayoutParams)var6.getLayoutParams();
            if (var7.width == -1) {
               int var5 = var7.height;
               var7.height = var6.getMeasuredHeight();
               this.measureChildWithMargins(var6, var4, 0, var2, 0);
               var7.height = var5;
            }
         }
      }

   }

   private static int resolveMinimumHeight(View var0) {
      int var1 = ViewCompat.getMinimumHeight(var0);
      if (var1 > 0) {
         return var1;
      } else {
         if (var0 instanceof ViewGroup) {
            ViewGroup var2 = (ViewGroup)var0;
            if (var2.getChildCount() == 1) {
               return resolveMinimumHeight(var2.getChildAt(0));
            }
         }

         return 0;
      }
   }

   private void setChildFrame(View var1, int var2, int var3, int var4, int var5) {
      var1.layout(var2, var3, var4 + var2, var5 + var3);
   }

   private boolean tryOnMeasure(int var1, int var2) {
      int var12 = this.getChildCount();
      View var18 = null;
      View var17 = null;
      View var16 = null;

      int var3;
      int var4;
      View var15;
      for(var3 = 0; var3 < var12; ++var3) {
         var15 = this.getChildAt(var3);
         if (var15.getVisibility() != 8) {
            var4 = var15.getId();
            if (var4 == R.id.topPanel) {
               var18 = var15;
            } else if (var4 == R.id.buttonPanel) {
               var17 = var15;
            } else {
               if (var4 != R.id.contentPanel && var4 != R.id.customPanel) {
                  return false;
               }

               if (var16 != null) {
                  return false;
               }

               var16 = var15;
            }
         }
      }

      int var14 = MeasureSpec.getMode(var2);
      int var7 = MeasureSpec.getSize(var2);
      int var13 = MeasureSpec.getMode(var1);
      int var5 = this.getPaddingTop() + this.getPaddingBottom();
      if (var18 != null) {
         var18.measure(var1, 0);
         var5 += var18.getMeasuredHeight();
         var4 = View.combineMeasuredStates(0, var18.getMeasuredState());
      } else {
         var4 = 0;
      }

      int var9;
      if (var17 != null) {
         var17.measure(var1, 0);
         var3 = resolveMinimumHeight(var17);
         var9 = var17.getMeasuredHeight() - var3;
         var5 += var3;
         var4 = View.combineMeasuredStates(var4, var17.getMeasuredState());
      } else {
         var3 = 0;
         var9 = 0;
      }

      int var6;
      int var8;
      if (var16 != null) {
         if (var14 == 0) {
            var6 = 0;
         } else {
            var6 = MeasureSpec.makeMeasureSpec(Math.max(0, var7 - var5), var14);
         }

         var16.measure(var1, var6);
         var8 = var16.getMeasuredHeight();
         var5 += var8;
         var4 = View.combineMeasuredStates(var4, var16.getMeasuredState());
      } else {
         var8 = 0;
      }

      int var11 = var7 - var5;
      var7 = var4;
      int var10 = var11;
      var6 = var5;
      if (var17 != null) {
         var9 = Math.min(var11, var9);
         var7 = var11;
         var6 = var3;
         if (var9 > 0) {
            var7 = var11 - var9;
            var6 = var3 + var9;
         }

         var17.measure(var1, MeasureSpec.makeMeasureSpec(var6, 1073741824));
         var6 = var5 - var3 + var17.getMeasuredHeight();
         var3 = View.combineMeasuredStates(var4, var17.getMeasuredState());
         var10 = var7;
         var7 = var3;
      }

      var4 = var7;
      var3 = var6;
      if (var16 != null) {
         var4 = var7;
         var3 = var6;
         if (var10 > 0) {
            var16.measure(var1, MeasureSpec.makeMeasureSpec(var8 + var10, var14));
            var3 = var6 - var8 + var16.getMeasuredHeight();
            var4 = View.combineMeasuredStates(var7, var16.getMeasuredState());
         }
      }

      var6 = 0;

      for(var7 = 0; var6 < var12; var7 = var5) {
         var15 = this.getChildAt(var6);
         var5 = var7;
         if (var15.getVisibility() != 8) {
            var5 = Math.max(var7, var15.getMeasuredWidth());
         }

         ++var6;
      }

      this.setMeasuredDimension(View.resolveSizeAndState(var7 + this.getPaddingLeft() + this.getPaddingRight(), var1, var4), View.resolveSizeAndState(var3, var2, 0));
      if (var13 != 1073741824) {
         this.forceUniformWidth(var12, var2);
      }

      return true;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      int var7 = this.getPaddingLeft();
      int var9 = var4 - var2;
      int var11 = this.getPaddingRight();
      int var10 = this.getPaddingRight();
      var4 = this.getMeasuredHeight();
      int var8 = this.getChildCount();
      int var12 = this.getGravity();
      var2 = var12 & 112;
      if (var2 != 16) {
         if (var2 != 80) {
            var2 = this.getPaddingTop();
         } else {
            var2 = this.getPaddingTop() + var5 - var3 - var4;
         }
      } else {
         var2 = this.getPaddingTop() + (var5 - var3 - var4) / 2;
      }

      Drawable var15 = this.getDividerDrawable();
      if (var15 == null) {
         var3 = 0;
      } else {
         var3 = var15.getIntrinsicHeight();
      }

      for(var4 = 0; var4 < var8; var2 = var5) {
         View var17 = this.getChildAt(var4);
         var5 = var2;
         if (var17 != null) {
            var5 = var2;
            if (var17.getVisibility() != 8) {
               int var13 = var17.getMeasuredWidth();
               int var14 = var17.getMeasuredHeight();
               LinearLayoutCompat.LayoutParams var16 = (LinearLayoutCompat.LayoutParams)var17.getLayoutParams();
               int var6 = var16.gravity;
               var5 = var6;
               if (var6 < 0) {
                  var5 = var12 & 8388615;
               }

               label48: {
                  var5 = GravityCompat.getAbsoluteGravity(var5, ViewCompat.getLayoutDirection(this)) & 7;
                  if (var5 != 1) {
                     if (var5 != 5) {
                        var5 = var16.leftMargin + var7;
                        break label48;
                     }

                     var6 = var9 - var11 - var13;
                     var5 = var16.rightMargin;
                  } else {
                     var6 = (var9 - var7 - var10 - var13) / 2 + var7 + var16.leftMargin;
                     var5 = var16.rightMargin;
                  }

                  var5 = var6 - var5;
               }

               var6 = var2;
               if (this.hasDividerBeforeChildAt(var4)) {
                  var6 = var2 + var3;
               }

               var2 = var6 + var16.topMargin;
               this.setChildFrame(var17, var5, var2, var13, var14);
               var5 = var2 + var14 + var16.bottomMargin;
            }
         }

         ++var4;
      }

   }

   protected void onMeasure(int var1, int var2) {
      if (!this.tryOnMeasure(var1, var2)) {
         super.onMeasure(var1, var2);
      }

   }
}
