package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.R;
import java.util.ArrayList;

public final class FragmentContainerView extends FrameLayout {
   private View.OnApplyWindowInsetsListener mApplyWindowInsetsListener;
   private ArrayList mDisappearingFragmentChildren;
   private boolean mDrawDisappearingViewsFirst;
   private ArrayList mTransitioningFragmentViews;

   public FragmentContainerView(Context var1) {
      super(var1);
      this.mDrawDisappearingViewsFirst = true;
   }

   public FragmentContainerView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FragmentContainerView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mDrawDisappearingViewsFirst = true;
      if (var2 != null) {
         String var4 = var2.getClassAttribute();
         TypedArray var5 = var1.obtainStyledAttributes(var2, R.styleable.FragmentContainerView);
         String var6;
         String var7;
         if (var4 == null) {
            var6 = var5.getString(R.styleable.FragmentContainerView_android_name);
            var7 = "android:name";
         } else {
            var7 = "class";
            var6 = var4;
         }

         var5.recycle();
         if (var6 != null && !this.isInEditMode()) {
            throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to use " + var7 + "=\"" + var6 + "\"");
         }
      }

   }

   FragmentContainerView(Context var1, AttributeSet var2, FragmentManager var3) {
      super(var1, var2);
      this.mDrawDisappearingViewsFirst = true;
      String var6 = var2.getClassAttribute();
      TypedArray var7 = var1.obtainStyledAttributes(var2, R.styleable.FragmentContainerView);
      String var5 = var6;
      if (var6 == null) {
         var5 = var7.getString(R.styleable.FragmentContainerView_android_name);
      }

      var6 = var7.getString(R.styleable.FragmentContainerView_android_tag);
      var7.recycle();
      int var4 = this.getId();
      Fragment var10 = var3.findFragmentById(var4);
      if (var5 != null && var10 == null) {
         if (var4 <= 0) {
            String var8;
            if (var6 != null) {
               var8 = " with tag " + var6;
            } else {
               var8 = "";
            }

            throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + var5 + var8);
         }

         Fragment var9 = var3.getFragmentFactory().instantiate(var1.getClassLoader(), var5);
         var9.onInflate((Context)var1, var2, (Bundle)null);
         var3.beginTransaction().setReorderingAllowed(true).add((ViewGroup)this, (Fragment)var9, var6).commitNowAllowingStateLoss();
      }

      var3.onContainerAvailable(this);
   }

   private void addDisappearingFragmentView(View var1) {
      ArrayList var2 = this.mTransitioningFragmentViews;
      if (var2 != null && var2.contains(var1)) {
         if (this.mDisappearingFragmentChildren == null) {
            this.mDisappearingFragmentChildren = new ArrayList();
         }

         this.mDisappearingFragmentChildren.add(var1);
      }

   }

   public void addView(View var1, int var2, ViewGroup.LayoutParams var3) {
      if (FragmentManager.getViewFragment(var1) != null) {
         super.addView(var1, var2, var3);
      } else {
         throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + var1 + " is not associated with a Fragment.");
      }
   }

   protected boolean addViewInLayout(View var1, int var2, ViewGroup.LayoutParams var3, boolean var4) {
      if (FragmentManager.getViewFragment(var1) != null) {
         return super.addViewInLayout(var1, var2, var3, var4);
      } else {
         throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + var1 + " is not associated with a Fragment.");
      }
   }

   public WindowInsets dispatchApplyWindowInsets(WindowInsets var1) {
      WindowInsetsCompat var5 = WindowInsetsCompat.toWindowInsetsCompat(var1);
      View.OnApplyWindowInsetsListener var4 = this.mApplyWindowInsetsListener;
      WindowInsetsCompat var6;
      if (var4 != null) {
         var6 = WindowInsetsCompat.toWindowInsetsCompat(var4.onApplyWindowInsets(this, var1));
      } else {
         var6 = ViewCompat.onApplyWindowInsets(this, var5);
      }

      if (!var6.isConsumed()) {
         int var3 = this.getChildCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            ViewCompat.dispatchApplyWindowInsets(this.getChildAt(var2), var6);
         }
      }

      return var1;
   }

   protected void dispatchDraw(Canvas var1) {
      if (this.mDrawDisappearingViewsFirst && this.mDisappearingFragmentChildren != null) {
         for(int var2 = 0; var2 < this.mDisappearingFragmentChildren.size(); ++var2) {
            super.drawChild(var1, (View)this.mDisappearingFragmentChildren.get(var2), this.getDrawingTime());
         }
      }

      super.dispatchDraw(var1);
   }

   protected boolean drawChild(Canvas var1, View var2, long var3) {
      if (this.mDrawDisappearingViewsFirst) {
         ArrayList var5 = this.mDisappearingFragmentChildren;
         if (var5 != null && var5.size() > 0 && this.mDisappearingFragmentChildren.contains(var2)) {
            return false;
         }
      }

      return super.drawChild(var1, var2, var3);
   }

   public void endViewTransition(View var1) {
      ArrayList var2 = this.mTransitioningFragmentViews;
      if (var2 != null) {
         var2.remove(var1);
         var2 = this.mDisappearingFragmentChildren;
         if (var2 != null && var2.remove(var1)) {
            this.mDrawDisappearingViewsFirst = true;
         }
      }

      super.endViewTransition(var1);
   }

   public WindowInsets onApplyWindowInsets(WindowInsets var1) {
      return var1;
   }

   public void removeAllViewsInLayout() {
      for(int var1 = this.getChildCount() - 1; var1 >= 0; --var1) {
         this.addDisappearingFragmentView(this.getChildAt(var1));
      }

      super.removeAllViewsInLayout();
   }

   protected void removeDetachedView(View var1, boolean var2) {
      if (var2) {
         this.addDisappearingFragmentView(var1);
      }

      super.removeDetachedView(var1, var2);
   }

   public void removeView(View var1) {
      this.addDisappearingFragmentView(var1);
      super.removeView(var1);
   }

   public void removeViewAt(int var1) {
      this.addDisappearingFragmentView(this.getChildAt(var1));
      super.removeViewAt(var1);
   }

   public void removeViewInLayout(View var1) {
      this.addDisappearingFragmentView(var1);
      super.removeViewInLayout(var1);
   }

   public void removeViews(int var1, int var2) {
      for(int var3 = var1; var3 < var1 + var2; ++var3) {
         this.addDisappearingFragmentView(this.getChildAt(var3));
      }

      super.removeViews(var1, var2);
   }

   public void removeViewsInLayout(int var1, int var2) {
      for(int var3 = var1; var3 < var1 + var2; ++var3) {
         this.addDisappearingFragmentView(this.getChildAt(var3));
      }

      super.removeViewsInLayout(var1, var2);
   }

   void setDrawDisappearingViewsLast(boolean var1) {
      this.mDrawDisappearingViewsFirst = var1;
   }

   public void setLayoutTransition(LayoutTransition var1) {
      if (VERSION.SDK_INT < 18) {
         super.setLayoutTransition(var1);
      } else {
         throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
      }
   }

   public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener var1) {
      this.mApplyWindowInsetsListener = var1;
   }

   public void startViewTransition(View var1) {
      if (var1.getParent() == this) {
         if (this.mTransitioningFragmentViews == null) {
            this.mTransitioningFragmentViews = new ArrayList();
         }

         this.mTransitioningFragmentViews.add(var1);
      }

      super.startViewTransition(var1);
   }
}
