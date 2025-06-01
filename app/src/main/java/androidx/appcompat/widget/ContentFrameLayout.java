package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;

public class ContentFrameLayout extends FrameLayout {
   private OnAttachListener mAttachListener;
   private final Rect mDecorPadding;
   private TypedValue mFixedHeightMajor;
   private TypedValue mFixedHeightMinor;
   private TypedValue mFixedWidthMajor;
   private TypedValue mFixedWidthMinor;
   private TypedValue mMinWidthMajor;
   private TypedValue mMinWidthMinor;

   public ContentFrameLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public ContentFrameLayout(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public ContentFrameLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mDecorPadding = new Rect();
   }

   public void dispatchFitSystemWindows(Rect var1) {
      this.fitSystemWindows(var1);
   }

   public TypedValue getFixedHeightMajor() {
      if (this.mFixedHeightMajor == null) {
         this.mFixedHeightMajor = new TypedValue();
      }

      return this.mFixedHeightMajor;
   }

   public TypedValue getFixedHeightMinor() {
      if (this.mFixedHeightMinor == null) {
         this.mFixedHeightMinor = new TypedValue();
      }

      return this.mFixedHeightMinor;
   }

   public TypedValue getFixedWidthMajor() {
      if (this.mFixedWidthMajor == null) {
         this.mFixedWidthMajor = new TypedValue();
      }

      return this.mFixedWidthMajor;
   }

   public TypedValue getFixedWidthMinor() {
      if (this.mFixedWidthMinor == null) {
         this.mFixedWidthMinor = new TypedValue();
      }

      return this.mFixedWidthMinor;
   }

   public TypedValue getMinWidthMajor() {
      if (this.mMinWidthMajor == null) {
         this.mMinWidthMajor = new TypedValue();
      }

      return this.mMinWidthMajor;
   }

   public TypedValue getMinWidthMinor() {
      if (this.mMinWidthMinor == null) {
         this.mMinWidthMinor = new TypedValue();
      }

      return this.mMinWidthMinor;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      OnAttachListener var1 = this.mAttachListener;
      if (var1 != null) {
         var1.onAttachedFromWindow();
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      OnAttachListener var1 = this.mAttachListener;
      if (var1 != null) {
         var1.onDetachedFromWindow();
      }

   }

   protected void onMeasure(int var1, int var2) {
      DisplayMetrics var11 = this.getContext().getResources().getDisplayMetrics();
      int var5 = var11.widthPixels;
      int var4 = var11.heightPixels;
      boolean var8 = true;
      boolean var13;
      if (var5 < var4) {
         var13 = true;
      } else {
         var13 = false;
      }

      float var3;
      int var6;
      int var7;
      int var9;
      TypedValue var10;
      boolean var12;
      label104: {
         var9 = MeasureSpec.getMode(var1);
         var7 = MeasureSpec.getMode(var2);
         if (var9 == Integer.MIN_VALUE) {
            if (var13) {
               var10 = this.mFixedWidthMinor;
            } else {
               var10 = this.mFixedWidthMajor;
            }

            if (var10 != null && var10.type != 0) {
               label98: {
                  if (var10.type == 5) {
                     var3 = var10.getDimension(var11);
                  } else {
                     if (var10.type != 6) {
                        var5 = 0;
                        break label98;
                     }

                     var3 = var10.getFraction((float)var11.widthPixels, (float)var11.widthPixels);
                  }

                  var5 = (int)var3;
               }

               if (var5 > 0) {
                  var6 = MeasureSpec.makeMeasureSpec(Math.min(var5 - (this.mDecorPadding.left + this.mDecorPadding.right), MeasureSpec.getSize(var1)), 1073741824);
                  var12 = true;
                  break label104;
               }
            }
         }

         boolean var14 = false;
         var6 = var1;
         var12 = var14;
      }

      var5 = var2;
      if (var7 == Integer.MIN_VALUE) {
         if (var13) {
            var10 = this.mFixedHeightMajor;
         } else {
            var10 = this.mFixedHeightMinor;
         }

         var5 = var2;
         if (var10 != null) {
            var5 = var2;
            if (var10.type != 0) {
               label85: {
                  if (var10.type == 5) {
                     var3 = var10.getDimension(var11);
                  } else {
                     if (var10.type != 6) {
                        var7 = 0;
                        break label85;
                     }

                     var3 = var10.getFraction((float)var11.heightPixels, (float)var11.heightPixels);
                  }

                  var7 = (int)var3;
               }

               var5 = var2;
               if (var7 > 0) {
                  var5 = MeasureSpec.makeMeasureSpec(Math.min(var7 - (this.mDecorPadding.top + this.mDecorPadding.bottom), MeasureSpec.getSize(var2)), 1073741824);
               }
            }
         }
      }

      label79: {
         super.onMeasure(var6, var5);
         var7 = this.getMeasuredWidth();
         var6 = MeasureSpec.makeMeasureSpec(var7, 1073741824);
         if (!var12 && var9 == Integer.MIN_VALUE) {
            if (var13) {
               var10 = this.mMinWidthMinor;
            } else {
               var10 = this.mMinWidthMajor;
            }

            if (var10 != null && var10.type != 0) {
               label72: {
                  if (var10.type == 5) {
                     var3 = var10.getDimension(var11);
                  } else {
                     if (var10.type != 6) {
                        var1 = 0;
                        break label72;
                     }

                     var3 = var10.getFraction((float)var11.widthPixels, (float)var11.widthPixels);
                  }

                  var1 = (int)var3;
               }

               var2 = var1;
               if (var1 > 0) {
                  var2 = var1 - (this.mDecorPadding.left + this.mDecorPadding.right);
               }

               if (var7 < var2) {
                  var2 = MeasureSpec.makeMeasureSpec(var2, 1073741824);
                  var12 = var8;
                  break label79;
               }
            }
         }

         var12 = false;
         var2 = var6;
      }

      if (var12) {
         super.onMeasure(var2, var5);
      }

   }

   public void setAttachListener(OnAttachListener var1) {
      this.mAttachListener = var1;
   }

   public void setDecorPadding(int var1, int var2, int var3, int var4) {
      this.mDecorPadding.set(var1, var2, var3, var4);
      if (ViewCompat.isLaidOut(this)) {
         this.requestLayout();
      }

   }

   public interface OnAttachListener {
      void onAttachedFromWindow();

      void onDetachedFromWindow();
   }
}
