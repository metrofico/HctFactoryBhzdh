package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;

public class ActionBarContainer extends FrameLayout {
   private View mActionBarView;
   Drawable mBackground;
   private View mContextView;
   private int mHeight;
   boolean mIsSplit;
   boolean mIsStacked;
   private boolean mIsTransitioning;
   Drawable mSplitBackground;
   Drawable mStackedBackground;
   private View mTabContainer;

   public ActionBarContainer(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public ActionBarContainer(Context var1, AttributeSet var2) {
      super(var1, var2);
      ViewCompat.setBackground(this, new ActionBarBackgroundDrawable(this));
      TypedArray var6 = var1.obtainStyledAttributes(var2, R.styleable.ActionBar);
      this.mBackground = var6.getDrawable(R.styleable.ActionBar_background);
      this.mStackedBackground = var6.getDrawable(R.styleable.ActionBar_backgroundStacked);
      this.mHeight = var6.getDimensionPixelSize(R.styleable.ActionBar_height, -1);
      int var4 = this.getId();
      int var3 = R.id.split_action_bar;
      boolean var5 = true;
      if (var4 == var3) {
         this.mIsSplit = true;
         this.mSplitBackground = var6.getDrawable(R.styleable.ActionBar_backgroundSplit);
      }

      label20: {
         var6.recycle();
         if (this.mIsSplit) {
            if (this.mSplitBackground == null) {
               break label20;
            }
         } else if (this.mBackground == null && this.mStackedBackground == null) {
            break label20;
         }

         var5 = false;
      }

      this.setWillNotDraw(var5);
   }

   private int getMeasuredHeightWithMargins(View var1) {
      FrameLayout.LayoutParams var2 = (FrameLayout.LayoutParams)var1.getLayoutParams();
      return var1.getMeasuredHeight() + var2.topMargin + var2.bottomMargin;
   }

   private boolean isCollapsed(View var1) {
      boolean var2;
      if (var1 != null && var1.getVisibility() != 8 && var1.getMeasuredHeight() != 0) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      Drawable var1 = this.mBackground;
      if (var1 != null && var1.isStateful()) {
         this.mBackground.setState(this.getDrawableState());
      }

      var1 = this.mStackedBackground;
      if (var1 != null && var1.isStateful()) {
         this.mStackedBackground.setState(this.getDrawableState());
      }

      var1 = this.mSplitBackground;
      if (var1 != null && var1.isStateful()) {
         this.mSplitBackground.setState(this.getDrawableState());
      }

   }

   public View getTabContainer() {
      return this.mTabContainer;
   }

   public void jumpDrawablesToCurrentState() {
      super.jumpDrawablesToCurrentState();
      Drawable var1 = this.mBackground;
      if (var1 != null) {
         var1.jumpToCurrentState();
      }

      var1 = this.mStackedBackground;
      if (var1 != null) {
         var1.jumpToCurrentState();
      }

      var1 = this.mSplitBackground;
      if (var1 != null) {
         var1.jumpToCurrentState();
      }

   }

   public void onFinishInflate() {
      super.onFinishInflate();
      this.mActionBarView = this.findViewById(R.id.action_bar);
      this.mContextView = this.findViewById(R.id.action_context_bar);
   }

   public boolean onHoverEvent(MotionEvent var1) {
      super.onHoverEvent(var1);
      return true;
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      boolean var2;
      if (!this.mIsTransitioning && !super.onInterceptTouchEvent(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      View var8 = this.mTabContainer;
      boolean var12 = true;
      boolean var11 = false;
      boolean var6 = false;
      if (var8 != null && var8.getVisibility() != 8) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (var8 != null && var8.getVisibility() != 8) {
         int var7 = this.getMeasuredHeight();
         FrameLayout.LayoutParams var9 = (FrameLayout.LayoutParams)var8.getLayoutParams();
         var8.layout(var2, var7 - var8.getMeasuredHeight() - var9.bottomMargin, var4, var7 - var9.bottomMargin);
      }

      boolean var10;
      label48: {
         if (this.mIsSplit) {
            Drawable var13 = this.mSplitBackground;
            var11 = var6;
            if (var13 != null) {
               var13.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
               var10 = var12;
               break label48;
            }
         } else {
            var10 = var11;
            if (this.mBackground != null) {
               if (this.mActionBarView.getVisibility() == 0) {
                  this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
               } else {
                  View var14 = this.mContextView;
                  if (var14 != null && var14.getVisibility() == 0) {
                     this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
                  } else {
                     this.mBackground.setBounds(0, 0, 0, 0);
                  }
               }

               var10 = true;
            }

            this.mIsStacked = var1;
            var11 = var10;
            if (var1) {
               Drawable var15 = this.mStackedBackground;
               var11 = var10;
               if (var15 != null) {
                  var15.setBounds(var8.getLeft(), var8.getTop(), var8.getRight(), var8.getBottom());
                  var10 = var12;
                  break label48;
               }
            }
         }

         var10 = var11;
      }

      if (var10) {
         this.invalidate();
      }

   }

   public void onMeasure(int var1, int var2) {
      int var3 = var2;
      if (this.mActionBarView == null) {
         var3 = var2;
         if (MeasureSpec.getMode(var2) == Integer.MIN_VALUE) {
            int var4 = this.mHeight;
            var3 = var2;
            if (var4 >= 0) {
               var3 = MeasureSpec.makeMeasureSpec(Math.min(var4, MeasureSpec.getSize(var2)), Integer.MIN_VALUE);
            }
         }
      }

      super.onMeasure(var1, var3);
      if (this.mActionBarView != null) {
         var2 = MeasureSpec.getMode(var3);
         View var5 = this.mTabContainer;
         if (var5 != null && var5.getVisibility() != 8 && var2 != 1073741824) {
            if (!this.isCollapsed(this.mActionBarView)) {
               var1 = this.getMeasuredHeightWithMargins(this.mActionBarView);
            } else if (!this.isCollapsed(this.mContextView)) {
               var1 = this.getMeasuredHeightWithMargins(this.mContextView);
            } else {
               var1 = 0;
            }

            if (var2 == Integer.MIN_VALUE) {
               var2 = MeasureSpec.getSize(var3);
            } else {
               var2 = Integer.MAX_VALUE;
            }

            this.setMeasuredDimension(this.getMeasuredWidth(), Math.min(var1 + this.getMeasuredHeightWithMargins(this.mTabContainer), var2));
         }

      }
   }

   public boolean onTouchEvent(MotionEvent var1) {
      super.onTouchEvent(var1);
      return true;
   }

   public void setPrimaryBackground(Drawable var1) {
      Drawable var4 = this.mBackground;
      if (var4 != null) {
         var4.setCallback((Drawable.Callback)null);
         this.unscheduleDrawable(this.mBackground);
      }

      this.mBackground = var1;
      if (var1 != null) {
         var1.setCallback(this);
         View var5 = this.mActionBarView;
         if (var5 != null) {
            this.mBackground.setBounds(var5.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
         }
      }

      boolean var2;
      label28: {
         boolean var3 = this.mIsSplit;
         var2 = true;
         if (var3) {
            if (this.mSplitBackground == null) {
               break label28;
            }
         } else if (this.mBackground == null && this.mStackedBackground == null) {
            break label28;
         }

         var2 = false;
      }

      this.setWillNotDraw(var2);
      this.invalidate();
      if (VERSION.SDK_INT >= 21) {
         this.invalidateOutline();
      }

   }

   public void setSplitBackground(Drawable var1) {
      Drawable var4 = this.mSplitBackground;
      if (var4 != null) {
         var4.setCallback((Drawable.Callback)null);
         this.unscheduleDrawable(this.mSplitBackground);
      }

      this.mSplitBackground = var1;
      boolean var3 = false;
      if (var1 != null) {
         var1.setCallback(this);
         if (this.mIsSplit) {
            var1 = this.mSplitBackground;
            if (var1 != null) {
               var1.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
         }
      }

      boolean var2;
      label30: {
         if (this.mIsSplit) {
            var2 = var3;
            if (this.mSplitBackground != null) {
               break label30;
            }
         } else {
            var2 = var3;
            if (this.mBackground != null) {
               break label30;
            }

            var2 = var3;
            if (this.mStackedBackground != null) {
               break label30;
            }
         }

         var2 = true;
      }

      this.setWillNotDraw(var2);
      this.invalidate();
      if (VERSION.SDK_INT >= 21) {
         this.invalidateOutline();
      }

   }

   public void setStackedBackground(Drawable var1) {
      Drawable var4 = this.mStackedBackground;
      if (var4 != null) {
         var4.setCallback((Drawable.Callback)null);
         this.unscheduleDrawable(this.mStackedBackground);
      }

      this.mStackedBackground = var1;
      if (var1 != null) {
         var1.setCallback(this);
         if (this.mIsStacked) {
            var1 = this.mStackedBackground;
            if (var1 != null) {
               var1.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
            }
         }
      }

      boolean var2;
      label30: {
         boolean var3 = this.mIsSplit;
         var2 = true;
         if (var3) {
            if (this.mSplitBackground == null) {
               break label30;
            }
         } else if (this.mBackground == null && this.mStackedBackground == null) {
            break label30;
         }

         var2 = false;
      }

      this.setWillNotDraw(var2);
      this.invalidate();
      if (VERSION.SDK_INT >= 21) {
         this.invalidateOutline();
      }

   }

   public void setTabContainer(ScrollingTabContainerView var1) {
      View var2 = this.mTabContainer;
      if (var2 != null) {
         this.removeView(var2);
      }

      this.mTabContainer = var1;
      if (var1 != null) {
         this.addView(var1);
         ViewGroup.LayoutParams var3 = var1.getLayoutParams();
         var3.width = -1;
         var3.height = -2;
         var1.setAllowCollapse(false);
      }

   }

   public void setTransitioning(boolean var1) {
      this.mIsTransitioning = var1;
      int var2;
      if (var1) {
         var2 = 393216;
      } else {
         var2 = 262144;
      }

      this.setDescendantFocusability(var2);
   }

   public void setVisibility(int var1) {
      super.setVisibility(var1);
      boolean var2;
      if (var1 == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      Drawable var3 = this.mBackground;
      if (var3 != null) {
         var3.setVisible(var2, false);
      }

      var3 = this.mStackedBackground;
      if (var3 != null) {
         var3.setVisible(var2, false);
      }

      var3 = this.mSplitBackground;
      if (var3 != null) {
         var3.setVisible(var2, false);
      }

   }

   public ActionMode startActionModeForChild(View var1, ActionMode.Callback var2) {
      return null;
   }

   public ActionMode startActionModeForChild(View var1, ActionMode.Callback var2, int var3) {
      return var3 != 0 ? super.startActionModeForChild(var1, var2, var3) : null;
   }

   protected boolean verifyDrawable(Drawable var1) {
      boolean var2;
      if ((var1 != this.mBackground || this.mIsSplit) && (var1 != this.mStackedBackground || !this.mIsStacked) && (var1 != this.mSplitBackground || !this.mIsSplit) && !super.verifyDrawable(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }
}
