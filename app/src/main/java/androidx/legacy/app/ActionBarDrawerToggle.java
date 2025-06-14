package androidx.legacy.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import java.lang.reflect.Method;

@Deprecated
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {
   private static final int ID_HOME = 16908332;
   private static final String TAG = "ActionBarDrawerToggle";
   private static final int[] THEME_ATTRS = new int[]{16843531};
   private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334F;
   final Activity mActivity;
   private final Delegate mActivityImpl;
   private final int mCloseDrawerContentDescRes;
   private Drawable mDrawerImage;
   private final int mDrawerImageResource;
   private boolean mDrawerIndicatorEnabled;
   private final DrawerLayout mDrawerLayout;
   private boolean mHasCustomUpIndicator;
   private Drawable mHomeAsUpIndicator;
   private final int mOpenDrawerContentDescRes;
   private SetIndicatorInfo mSetIndicatorInfo;
   private SlideDrawable mSlider;

   public ActionBarDrawerToggle(Activity var1, DrawerLayout var2, int var3, int var4, int var5) {
      this(var1, var2, assumeMaterial(var1) ^ true, var3, var4, var5);
   }

   public ActionBarDrawerToggle(Activity var1, DrawerLayout var2, boolean var3, int var4, int var5, int var6) {
      this.mDrawerIndicatorEnabled = true;
      this.mActivity = var1;
      if (var1 instanceof DelegateProvider) {
         this.mActivityImpl = ((DelegateProvider)var1).getDrawerToggleDelegate();
      } else {
         this.mActivityImpl = null;
      }

      this.mDrawerLayout = var2;
      this.mDrawerImageResource = var4;
      this.mOpenDrawerContentDescRes = var5;
      this.mCloseDrawerContentDescRes = var6;
      this.mHomeAsUpIndicator = this.getThemeUpIndicator();
      this.mDrawerImage = ContextCompat.getDrawable(var1, var4);
      SlideDrawable var8 = new SlideDrawable(this, this.mDrawerImage);
      this.mSlider = var8;
      float var7;
      if (var3) {
         var7 = 0.33333334F;
      } else {
         var7 = 0.0F;
      }

      var8.setOffset(var7);
   }

   private static boolean assumeMaterial(Context var0) {
      boolean var1;
      if (var0.getApplicationInfo().targetSdkVersion >= 21 && VERSION.SDK_INT >= 21) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private Drawable getThemeUpIndicator() {
      Delegate var1 = this.mActivityImpl;
      if (var1 != null) {
         return var1.getThemeUpIndicator();
      } else if (VERSION.SDK_INT >= 18) {
         ActionBar var4 = this.mActivity.getActionBar();
         Object var5;
         if (var4 != null) {
            var5 = var4.getThemedContext();
         } else {
            var5 = this.mActivity;
         }

         TypedArray var6 = ((Context)var5).obtainStyledAttributes((AttributeSet)null, THEME_ATTRS, 16843470, 0);
         Drawable var7 = var6.getDrawable(0);
         var6.recycle();
         return var7;
      } else {
         TypedArray var3 = this.mActivity.obtainStyledAttributes(THEME_ATTRS);
         Drawable var2 = var3.getDrawable(0);
         var3.recycle();
         return var2;
      }
   }

   private void setActionBarDescription(int var1) {
      Delegate var2 = this.mActivityImpl;
      if (var2 != null) {
         var2.setActionBarDescription(var1);
      } else {
         ActionBar var4;
         if (VERSION.SDK_INT >= 18) {
            var4 = this.mActivity.getActionBar();
            if (var4 != null) {
               var4.setHomeActionContentDescription(var1);
            }
         } else {
            if (this.mSetIndicatorInfo == null) {
               this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
            }

            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
               try {
                  var4 = this.mActivity.getActionBar();
                  this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(var4, var1);
                  var4.setSubtitle(var4.getSubtitle());
               } catch (Exception var3) {
                  Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", var3);
               }
            }
         }

      }
   }

   private void setActionBarUpIndicator(Drawable var1, int var2) {
      Delegate var3 = this.mActivityImpl;
      if (var3 != null) {
         var3.setActionBarUpIndicator(var1, var2);
      } else {
         ActionBar var5;
         if (VERSION.SDK_INT >= 18) {
            var5 = this.mActivity.getActionBar();
            if (var5 != null) {
               var5.setHomeAsUpIndicator(var1);
               var5.setHomeActionContentDescription(var2);
            }
         } else {
            if (this.mSetIndicatorInfo == null) {
               this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
            }

            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
               try {
                  var5 = this.mActivity.getActionBar();
                  this.mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(var5, var1);
                  this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(var5, var2);
               } catch (Exception var4) {
                  Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", var4);
               }
            } else if (this.mSetIndicatorInfo.mUpIndicatorView != null) {
               this.mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(var1);
            } else {
               Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
            }
         }

      }
   }

   public boolean isDrawerIndicatorEnabled() {
      return this.mDrawerIndicatorEnabled;
   }

   public void onConfigurationChanged(Configuration var1) {
      if (!this.mHasCustomUpIndicator) {
         this.mHomeAsUpIndicator = this.getThemeUpIndicator();
      }

      this.mDrawerImage = ContextCompat.getDrawable(this.mActivity, this.mDrawerImageResource);
      this.syncState();
   }

   public void onDrawerClosed(View var1) {
      this.mSlider.setPosition(0.0F);
      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarDescription(this.mOpenDrawerContentDescRes);
      }

   }

   public void onDrawerOpened(View var1) {
      this.mSlider.setPosition(1.0F);
      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarDescription(this.mCloseDrawerContentDescRes);
      }

   }

   public void onDrawerSlide(View var1, float var2) {
      float var3 = this.mSlider.getPosition();
      if (var2 > 0.5F) {
         var2 = Math.max(var3, Math.max(0.0F, var2 - 0.5F) * 2.0F);
      } else {
         var2 = Math.min(var3, var2 * 2.0F);
      }

      this.mSlider.setPosition(var2);
   }

   public void onDrawerStateChanged(int var1) {
   }

   public boolean onOptionsItemSelected(MenuItem var1) {
      if (var1 != null && var1.getItemId() == 16908332 && this.mDrawerIndicatorEnabled) {
         if (this.mDrawerLayout.isDrawerVisible(8388611)) {
            this.mDrawerLayout.closeDrawer(8388611);
         } else {
            this.mDrawerLayout.openDrawer(8388611);
         }

         return true;
      } else {
         return false;
      }
   }

   public void setDrawerIndicatorEnabled(boolean var1) {
      if (var1 != this.mDrawerIndicatorEnabled) {
         if (var1) {
            SlideDrawable var3 = this.mSlider;
            int var2;
            if (this.mDrawerLayout.isDrawerOpen(8388611)) {
               var2 = this.mCloseDrawerContentDescRes;
            } else {
               var2 = this.mOpenDrawerContentDescRes;
            }

            this.setActionBarUpIndicator(var3, var2);
         } else {
            this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
         }

         this.mDrawerIndicatorEnabled = var1;
      }

   }

   public void setHomeAsUpIndicator(int var1) {
      Drawable var2;
      if (var1 != 0) {
         var2 = ContextCompat.getDrawable(this.mActivity, var1);
      } else {
         var2 = null;
      }

      this.setHomeAsUpIndicator(var2);
   }

   public void setHomeAsUpIndicator(Drawable var1) {
      if (var1 == null) {
         this.mHomeAsUpIndicator = this.getThemeUpIndicator();
         this.mHasCustomUpIndicator = false;
      } else {
         this.mHomeAsUpIndicator = var1;
         this.mHasCustomUpIndicator = true;
      }

      if (!this.mDrawerIndicatorEnabled) {
         this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
      }

   }

   public void syncState() {
      if (this.mDrawerLayout.isDrawerOpen(8388611)) {
         this.mSlider.setPosition(1.0F);
      } else {
         this.mSlider.setPosition(0.0F);
      }

      if (this.mDrawerIndicatorEnabled) {
         SlideDrawable var2 = this.mSlider;
         int var1;
         if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            var1 = this.mCloseDrawerContentDescRes;
         } else {
            var1 = this.mOpenDrawerContentDescRes;
         }

         this.setActionBarUpIndicator(var2, var1);
      }

   }

   @Deprecated
   public interface Delegate {
      Drawable getThemeUpIndicator();

      void setActionBarDescription(int var1);

      void setActionBarUpIndicator(Drawable var1, int var2);
   }

   @Deprecated
   public interface DelegateProvider {
      Delegate getDrawerToggleDelegate();
   }

   private static class SetIndicatorInfo {
      Method mSetHomeActionContentDescription;
      Method mSetHomeAsUpIndicator;
      ImageView mUpIndicatorView;

      SetIndicatorInfo(Activity var1) {
         try {
            this.mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
            this.mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
         } catch (NoSuchMethodException var4) {
            View var5 = var1.findViewById(16908332);
            if (var5 != null) {
               ViewGroup var6 = (ViewGroup)var5.getParent();
               if (var6.getChildCount() == 2) {
                  View var2 = var6.getChildAt(0);
                  View var3 = var6.getChildAt(1);
                  var5 = var2;
                  if (var2.getId() == 16908332) {
                     var5 = var3;
                  }

                  if (var5 instanceof ImageView) {
                     this.mUpIndicatorView = (ImageView)var5;
                  }

               }
            }
         }
      }
   }

   private class SlideDrawable extends InsetDrawable implements Drawable.Callback {
      private final boolean mHasMirroring;
      private float mOffset;
      private float mPosition;
      private final Rect mTmpRect;
      final ActionBarDrawerToggle this$0;

      SlideDrawable(ActionBarDrawerToggle var1, Drawable var2) {
         this.this$0 = var1;
         boolean var3 = false;
         super(var2, 0);
         if (VERSION.SDK_INT > 18) {
            var3 = true;
         }

         this.mHasMirroring = var3;
         this.mTmpRect = new Rect();
      }

      public void draw(Canvas var1) {
         this.copyBounds(this.mTmpRect);
         var1.save();
         int var4 = ViewCompat.getLayoutDirection(this.this$0.mActivity.getWindow().getDecorView());
         byte var5 = 1;
         boolean var7;
         if (var4 == 1) {
            var7 = true;
         } else {
            var7 = false;
         }

         if (var7) {
            var5 = -1;
         }

         int var6 = this.mTmpRect.width();
         float var2 = -this.mOffset;
         float var3 = (float)var6;
         var1.translate(var2 * var3 * this.mPosition * (float)var5, 0.0F);
         if (var7 && !this.mHasMirroring) {
            var1.translate(var3, 0.0F);
            var1.scale(-1.0F, 1.0F);
         }

         super.draw(var1);
         var1.restore();
      }

      public float getPosition() {
         return this.mPosition;
      }

      public void setOffset(float var1) {
         this.mOffset = var1;
         this.invalidateSelf();
      }

      public void setPosition(float var1) {
         this.mPosition = var1;
         this.invalidateSelf();
      }
   }
}
