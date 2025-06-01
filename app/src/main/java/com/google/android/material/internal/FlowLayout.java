package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;

public class FlowLayout extends ViewGroup {
   private int itemSpacing;
   private int lineSpacing;
   private boolean singleLine;

   public FlowLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FlowLayout(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FlowLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.singleLine = false;
      this.loadFromAttributes(var1, var2);
   }

   public FlowLayout(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      this.singleLine = false;
      this.loadFromAttributes(var1, var2);
   }

   private static int getMeasuredDimension(int var0, int var1, int var2) {
      if (var1 != Integer.MIN_VALUE) {
         return var1 != 1073741824 ? var2 : var0;
      } else {
         return Math.min(var2, var0);
      }
   }

   private void loadFromAttributes(Context var1, AttributeSet var2) {
      TypedArray var3 = var1.getTheme().obtainStyledAttributes(var2, R.styleable.FlowLayout, 0, 0);
      this.lineSpacing = var3.getDimensionPixelSize(R.styleable.FlowLayout_lineSpacing, 0);
      this.itemSpacing = var3.getDimensionPixelSize(R.styleable.FlowLayout_itemSpacing, 0);
      var3.recycle();
   }

   protected int getItemSpacing() {
      return this.itemSpacing;
   }

   protected int getLineSpacing() {
      return this.lineSpacing;
   }

   protected boolean isSingleLine() {
      return this.singleLine;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      if (this.getChildCount() != 0) {
         var3 = ViewCompat.getLayoutDirection(this);
         boolean var6 = true;
         if (var3 != 1) {
            var6 = false;
         }

         if (var6) {
            var3 = this.getPaddingRight();
         } else {
            var3 = this.getPaddingLeft();
         }

         if (var6) {
            var5 = this.getPaddingLeft();
         } else {
            var5 = this.getPaddingRight();
         }

         int var8 = this.getPaddingTop();
         int var12 = var4 - var2 - var5;
         var4 = var3;
         int var7 = 0;

         for(var2 = var8; var7 < this.getChildCount(); ++var7) {
            View var14 = this.getChildAt(var7);
            if (var14.getVisibility() != 8) {
               ViewGroup.LayoutParams var15 = var14.getLayoutParams();
               int var9;
               int var10;
               if (var15 instanceof ViewGroup.MarginLayoutParams) {
                  ViewGroup.MarginLayoutParams var16 = (ViewGroup.MarginLayoutParams)var15;
                  var9 = MarginLayoutParamsCompat.getMarginStart(var16);
                  var10 = MarginLayoutParamsCompat.getMarginEnd(var16);
               } else {
                  var10 = 0;
                  var9 = 0;
               }

               int var13 = var14.getMeasuredWidth();
               int var11 = var4;
               var5 = var2;
               if (!this.singleLine) {
                  var11 = var4;
                  var5 = var2;
                  if (var4 + var9 + var13 > var12) {
                     var5 = this.lineSpacing + var8;
                     var11 = var3;
                  }
               }

               var2 = var11 + var9;
               var4 = var14.getMeasuredWidth() + var2;
               var8 = var14.getMeasuredHeight() + var5;
               if (var6) {
                  var14.layout(var12 - var4, var5, var12 - var11 - var9, var8);
               } else {
                  var14.layout(var2, var5, var4, var8);
               }

               var4 = var11 + var9 + var10 + var14.getMeasuredWidth() + this.itemSpacing;
               var2 = var5;
            }
         }

      }
   }

   protected void onMeasure(int var1, int var2) {
      int var11 = MeasureSpec.getSize(var1);
      int var15 = MeasureSpec.getMode(var1);
      int var14 = MeasureSpec.getSize(var2);
      int var13 = MeasureSpec.getMode(var2);
      int var6;
      if (var15 != Integer.MIN_VALUE && var15 != 1073741824) {
         var6 = Integer.MAX_VALUE;
      } else {
         var6 = var11;
      }

      int var5 = this.getPaddingLeft();
      int var9 = this.getPaddingTop();
      int var16 = this.getPaddingRight();
      int var4 = var9;
      int var7 = 0;

      int var3;
      for(var3 = 0; var7 < this.getChildCount(); ++var7) {
         View var18 = this.getChildAt(var7);
         if (var18.getVisibility() != 8) {
            this.measureChild(var18, var1, var2);
            ViewGroup.LayoutParams var19 = var18.getLayoutParams();
            int var8;
            int var10;
            if (var19 instanceof ViewGroup.MarginLayoutParams) {
               ViewGroup.MarginLayoutParams var20 = (ViewGroup.MarginLayoutParams)var19;
               var8 = var20.leftMargin + 0;
               var10 = var20.rightMargin + 0;
            } else {
               var8 = 0;
               var10 = 0;
            }

            if (var5 + var8 + var18.getMeasuredWidth() > var6 - var16 && !this.isSingleLine()) {
               var5 = this.getPaddingLeft();
               var4 = this.lineSpacing + var9;
               var9 = var5;
            } else {
               var9 = var5;
            }

            int var12 = var9 + var8 + var18.getMeasuredWidth();
            int var17 = var18.getMeasuredHeight();
            var5 = var3;
            if (var12 > var3) {
               var5 = var12;
            }

            var8 = var9 + var8 + var10 + var18.getMeasuredWidth() + this.itemSpacing;
            var9 = var4 + var17;
            var3 = var5;
            var5 = var8;
         }
      }

      this.setMeasuredDimension(getMeasuredDimension(var11, var15, var3), getMeasuredDimension(var14, var13, var9));
   }

   protected void setItemSpacing(int var1) {
      this.itemSpacing = var1;
   }

   protected void setLineSpacing(int var1) {
      this.lineSpacing = var1;
   }

   public void setSingleLine(boolean var1) {
      this.singleLine = var1;
   }
}
