package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.util.SparseArray;
import androidx.core.graphics.drawable.DrawableCompat;

class DrawableContainer extends Drawable implements Drawable.Callback {
   private static final boolean DEBUG = false;
   private static final boolean DEFAULT_DITHER = true;
   private static final String TAG = "DrawableContainer";
   private int mAlpha = 255;
   private Runnable mAnimationRunnable;
   private BlockInvalidateCallback mBlockInvalidateCallback;
   private int mCurIndex = -1;
   private Drawable mCurrDrawable;
   private DrawableContainerState mDrawableContainerState;
   private long mEnterAnimationEnd;
   private long mExitAnimationEnd;
   private boolean mHasAlpha;
   private Rect mHotspotBounds;
   private Drawable mLastDrawable;
   private boolean mMutated;

   private void initializeDrawableForDisplay(Drawable var1) {
      if (this.mBlockInvalidateCallback == null) {
         this.mBlockInvalidateCallback = new BlockInvalidateCallback();
      }

      var1.setCallback(this.mBlockInvalidateCallback.wrap(var1.getCallback()));

      label735: {
         Throwable var10000;
         label741: {
            boolean var10001;
            try {
               if (this.mDrawableContainerState.mEnterFadeDuration <= 0 && this.mHasAlpha) {
                  var1.setAlpha(this.mAlpha);
               }
            } catch (Throwable var74) {
               var10000 = var74;
               var10001 = false;
               break label741;
            }

            label743: {
               try {
                  if (this.mDrawableContainerState.mHasColorFilter) {
                     var1.setColorFilter(this.mDrawableContainerState.mColorFilter);
                     break label743;
                  }
               } catch (Throwable var73) {
                  var10000 = var73;
                  var10001 = false;
                  break label741;
               }

               try {
                  if (this.mDrawableContainerState.mHasTintList) {
                     DrawableCompat.setTintList(var1, this.mDrawableContainerState.mTintList);
                  }
               } catch (Throwable var72) {
                  var10000 = var72;
                  var10001 = false;
                  break label741;
               }

               try {
                  if (this.mDrawableContainerState.mHasTintMode) {
                     DrawableCompat.setTintMode(var1, this.mDrawableContainerState.mTintMode);
                  }
               } catch (Throwable var71) {
                  var10000 = var71;
                  var10001 = false;
                  break label741;
               }
            }

            try {
               var1.setVisible(this.isVisible(), true);
               var1.setDither(this.mDrawableContainerState.mDither);
               var1.setState(this.getState());
               var1.setLevel(this.getLevel());
               var1.setBounds(this.getBounds());
               if (VERSION.SDK_INT >= 23) {
                  DrawableCompat.setLayoutDirection(var1, DrawableCompat.getLayoutDirection(this));
               }
            } catch (Throwable var70) {
               var10000 = var70;
               var10001 = false;
               break label741;
            }

            try {
               if (VERSION.SDK_INT >= 19) {
                  DrawableCompat.setAutoMirrored(var1, this.mDrawableContainerState.mAutoMirrored);
               }
            } catch (Throwable var69) {
               var10000 = var69;
               var10001 = false;
               break label741;
            }

            Rect var2;
            try {
               var2 = this.mHotspotBounds;
               if (VERSION.SDK_INT < 21) {
                  break label735;
               }
            } catch (Throwable var68) {
               var10000 = var68;
               var10001 = false;
               break label741;
            }

            if (var2 == null) {
               break label735;
            }

            label706:
            try {
               DrawableCompat.setHotspotBounds(var1, var2.left, var2.top, var2.right, var2.bottom);
               break label735;
            } catch (Throwable var67) {
               var10000 = var67;
               var10001 = false;
               break label706;
            }
         }

         Throwable var75 = var10000;
         var1.setCallback(this.mBlockInvalidateCallback.unwrap());
         throw var75;
      }

      var1.setCallback(this.mBlockInvalidateCallback.unwrap());
   }

   private boolean needsMirroring() {
      boolean var2 = this.isAutoMirrored();
      boolean var1 = true;
      if (!var2 || DrawableCompat.getLayoutDirection(this) != 1) {
         var1 = false;
      }

      return var1;
   }

   static int resolveDensity(Resources var0, int var1) {
      if (var0 != null) {
         var1 = var0.getDisplayMetrics().densityDpi;
      }

      int var2 = var1;
      if (var1 == 0) {
         var2 = 160;
      }

      return var2;
   }

   void animate(boolean var1) {
      int var2;
      boolean var3;
      long var4;
      long var6;
      Drawable var8;
      boolean var9;
      label33: {
         var3 = true;
         this.mHasAlpha = true;
         var4 = SystemClock.uptimeMillis();
         var8 = this.mCurrDrawable;
         if (var8 != null) {
            var6 = this.mEnterAnimationEnd;
            if (var6 != 0L) {
               if (var6 > var4) {
                  var2 = (int)((var6 - var4) * 255L) / this.mDrawableContainerState.mEnterFadeDuration;
                  this.mCurrDrawable.setAlpha((255 - var2) * this.mAlpha / 255);
                  var9 = true;
                  break label33;
               }

               var8.setAlpha(this.mAlpha);
               this.mEnterAnimationEnd = 0L;
            }
         } else {
            this.mEnterAnimationEnd = 0L;
         }

         var9 = false;
      }

      var8 = this.mLastDrawable;
      if (var8 != null) {
         var6 = this.mExitAnimationEnd;
         if (var6 != 0L) {
            if (var6 <= var4) {
               var8.setVisible(false, false);
               this.mLastDrawable = null;
               this.mExitAnimationEnd = 0L;
            } else {
               var2 = (int)((var6 - var4) * 255L) / this.mDrawableContainerState.mExitFadeDuration;
               this.mLastDrawable.setAlpha(var2 * this.mAlpha / 255);
               var9 = var3;
            }
         }
      } else {
         this.mExitAnimationEnd = 0L;
      }

      if (var1 && var9) {
         this.scheduleSelf(this.mAnimationRunnable, var4 + 16L);
      }

   }

   public void applyTheme(Resources.Theme var1) {
      this.mDrawableContainerState.applyTheme(var1);
   }

   public boolean canApplyTheme() {
      return this.mDrawableContainerState.canApplyTheme();
   }

   void clearMutated() {
      this.mDrawableContainerState.clearMutated();
      this.mMutated = false;
   }

   DrawableContainerState cloneConstantState() {
      return this.mDrawableContainerState;
   }

   public void draw(Canvas var1) {
      Drawable var2 = this.mCurrDrawable;
      if (var2 != null) {
         var2.draw(var1);
      }

      var2 = this.mLastDrawable;
      if (var2 != null) {
         var2.draw(var1);
      }

   }

   public int getAlpha() {
      return this.mAlpha;
   }

   public int getChangingConfigurations() {
      return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
   }

   public final Drawable.ConstantState getConstantState() {
      if (this.mDrawableContainerState.canConstantState()) {
         this.mDrawableContainerState.mChangingConfigurations = this.getChangingConfigurations();
         return this.mDrawableContainerState;
      } else {
         return null;
      }
   }

   public Drawable getCurrent() {
      return this.mCurrDrawable;
   }

   int getCurrentIndex() {
      return this.mCurIndex;
   }

   public void getHotspotBounds(Rect var1) {
      Rect var2 = this.mHotspotBounds;
      if (var2 != null) {
         var1.set(var2);
      } else {
         super.getHotspotBounds(var1);
      }

   }

   public int getIntrinsicHeight() {
      if (this.mDrawableContainerState.isConstantSize()) {
         return this.mDrawableContainerState.getConstantHeight();
      } else {
         Drawable var2 = this.mCurrDrawable;
         int var1;
         if (var2 != null) {
            var1 = var2.getIntrinsicHeight();
         } else {
            var1 = -1;
         }

         return var1;
      }
   }

   public int getIntrinsicWidth() {
      if (this.mDrawableContainerState.isConstantSize()) {
         return this.mDrawableContainerState.getConstantWidth();
      } else {
         Drawable var2 = this.mCurrDrawable;
         int var1;
         if (var2 != null) {
            var1 = var2.getIntrinsicWidth();
         } else {
            var1 = -1;
         }

         return var1;
      }
   }

   public int getMinimumHeight() {
      if (this.mDrawableContainerState.isConstantSize()) {
         return this.mDrawableContainerState.getConstantMinimumHeight();
      } else {
         Drawable var2 = this.mCurrDrawable;
         int var1;
         if (var2 != null) {
            var1 = var2.getMinimumHeight();
         } else {
            var1 = 0;
         }

         return var1;
      }
   }

   public int getMinimumWidth() {
      if (this.mDrawableContainerState.isConstantSize()) {
         return this.mDrawableContainerState.getConstantMinimumWidth();
      } else {
         Drawable var2 = this.mCurrDrawable;
         int var1;
         if (var2 != null) {
            var1 = var2.getMinimumWidth();
         } else {
            var1 = 0;
         }

         return var1;
      }
   }

   public int getOpacity() {
      Drawable var2 = this.mCurrDrawable;
      int var1;
      if (var2 != null && var2.isVisible()) {
         var1 = this.mDrawableContainerState.getOpacity();
      } else {
         var1 = -2;
      }

      return var1;
   }

   public void getOutline(Outline var1) {
      Drawable var2 = this.mCurrDrawable;
      if (var2 != null) {
         var2.getOutline(var1);
      }

   }

   public boolean getPadding(Rect var1) {
      Rect var6 = this.mDrawableContainerState.getConstantPadding();
      int var2;
      boolean var5;
      if (var6 != null) {
         var1.set(var6);
         var2 = var6.left;
         int var4 = var6.top;
         int var3 = var6.bottom;
         if ((var6.right | var2 | var4 | var3) != 0) {
            var5 = true;
         } else {
            var5 = false;
         }
      } else {
         Drawable var7 = this.mCurrDrawable;
         if (var7 != null) {
            var5 = var7.getPadding(var1);
         } else {
            var5 = super.getPadding(var1);
         }
      }

      if (this.needsMirroring()) {
         var2 = var1.left;
         var1.left = var1.right;
         var1.right = var2;
      }

      return var5;
   }

   public void invalidateDrawable(Drawable var1) {
      DrawableContainerState var2 = this.mDrawableContainerState;
      if (var2 != null) {
         var2.invalidateCache();
      }

      if (var1 == this.mCurrDrawable && this.getCallback() != null) {
         this.getCallback().invalidateDrawable(this);
      }

   }

   public boolean isAutoMirrored() {
      return this.mDrawableContainerState.mAutoMirrored;
   }

   public boolean isStateful() {
      return this.mDrawableContainerState.isStateful();
   }

   public void jumpToCurrentState() {
      Drawable var3 = this.mLastDrawable;
      boolean var2 = true;
      boolean var1;
      if (var3 != null) {
         var3.jumpToCurrentState();
         this.mLastDrawable = null;
         var1 = true;
      } else {
         var1 = false;
      }

      var3 = this.mCurrDrawable;
      if (var3 != null) {
         var3.jumpToCurrentState();
         if (this.mHasAlpha) {
            this.mCurrDrawable.setAlpha(this.mAlpha);
         }
      }

      if (this.mExitAnimationEnd != 0L) {
         this.mExitAnimationEnd = 0L;
         var1 = true;
      }

      if (this.mEnterAnimationEnd != 0L) {
         this.mEnterAnimationEnd = 0L;
         var1 = var2;
      }

      if (var1) {
         this.invalidateSelf();
      }

   }

   public Drawable mutate() {
      if (!this.mMutated && super.mutate() == this) {
         DrawableContainerState var1 = this.cloneConstantState();
         var1.mutate();
         this.setConstantState(var1);
         this.mMutated = true;
      }

      return this;
   }

   protected void onBoundsChange(Rect var1) {
      Drawable var2 = this.mLastDrawable;
      if (var2 != null) {
         var2.setBounds(var1);
      }

      var2 = this.mCurrDrawable;
      if (var2 != null) {
         var2.setBounds(var1);
      }

   }

   public boolean onLayoutDirectionChanged(int var1) {
      return this.mDrawableContainerState.setLayoutDirection(var1, this.getCurrentIndex());
   }

   protected boolean onLevelChange(int var1) {
      Drawable var2 = this.mLastDrawable;
      if (var2 != null) {
         return var2.setLevel(var1);
      } else {
         var2 = this.mCurrDrawable;
         return var2 != null ? var2.setLevel(var1) : false;
      }
   }

   protected boolean onStateChange(int[] var1) {
      Drawable var2 = this.mLastDrawable;
      if (var2 != null) {
         return var2.setState(var1);
      } else {
         var2 = this.mCurrDrawable;
         return var2 != null ? var2.setState(var1) : false;
      }
   }

   public void scheduleDrawable(Drawable var1, Runnable var2, long var3) {
      if (var1 == this.mCurrDrawable && this.getCallback() != null) {
         this.getCallback().scheduleDrawable(this, var2, var3);
      }

   }

   boolean selectDrawable(int var1) {
      if (var1 == this.mCurIndex) {
         return false;
      } else {
         long var2 = SystemClock.uptimeMillis();
         Drawable var4;
         if (this.mDrawableContainerState.mExitFadeDuration > 0) {
            var4 = this.mLastDrawable;
            if (var4 != null) {
               var4.setVisible(false, false);
            }

            var4 = this.mCurrDrawable;
            if (var4 != null) {
               this.mLastDrawable = var4;
               this.mExitAnimationEnd = (long)this.mDrawableContainerState.mExitFadeDuration + var2;
            } else {
               this.mLastDrawable = null;
               this.mExitAnimationEnd = 0L;
            }
         } else {
            var4 = this.mCurrDrawable;
            if (var4 != null) {
               var4.setVisible(false, false);
            }
         }

         if (var1 >= 0 && var1 < this.mDrawableContainerState.mNumChildren) {
            var4 = this.mDrawableContainerState.getChild(var1);
            this.mCurrDrawable = var4;
            this.mCurIndex = var1;
            if (var4 != null) {
               if (this.mDrawableContainerState.mEnterFadeDuration > 0) {
                  this.mEnterAnimationEnd = var2 + (long)this.mDrawableContainerState.mEnterFadeDuration;
               }

               this.initializeDrawableForDisplay(var4);
            }
         } else {
            this.mCurrDrawable = null;
            this.mCurIndex = -1;
         }

         if (this.mEnterAnimationEnd != 0L || this.mExitAnimationEnd != 0L) {
            Runnable var5 = this.mAnimationRunnable;
            if (var5 == null) {
               this.mAnimationRunnable = new Runnable(this) {
                  final DrawableContainer this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void run() {
                     this.this$0.animate(true);
                     this.this$0.invalidateSelf();
                  }
               };
            } else {
               this.unscheduleSelf(var5);
            }

            this.animate(true);
         }

         this.invalidateSelf();
         return true;
      }
   }

   public void setAlpha(int var1) {
      if (!this.mHasAlpha || this.mAlpha != var1) {
         this.mHasAlpha = true;
         this.mAlpha = var1;
         Drawable var2 = this.mCurrDrawable;
         if (var2 != null) {
            if (this.mEnterAnimationEnd == 0L) {
               var2.setAlpha(var1);
            } else {
               this.animate(false);
            }
         }
      }

   }

   public void setAutoMirrored(boolean var1) {
      if (this.mDrawableContainerState.mAutoMirrored != var1) {
         this.mDrawableContainerState.mAutoMirrored = var1;
         Drawable var2 = this.mCurrDrawable;
         if (var2 != null) {
            DrawableCompat.setAutoMirrored(var2, this.mDrawableContainerState.mAutoMirrored);
         }
      }

   }

   public void setColorFilter(ColorFilter var1) {
      this.mDrawableContainerState.mHasColorFilter = true;
      if (this.mDrawableContainerState.mColorFilter != var1) {
         this.mDrawableContainerState.mColorFilter = var1;
         Drawable var2 = this.mCurrDrawable;
         if (var2 != null) {
            var2.setColorFilter(var1);
         }
      }

   }

   void setConstantState(DrawableContainerState var1) {
      this.mDrawableContainerState = var1;
      int var2 = this.mCurIndex;
      if (var2 >= 0) {
         Drawable var3 = var1.getChild(var2);
         this.mCurrDrawable = var3;
         if (var3 != null) {
            this.initializeDrawableForDisplay(var3);
         }
      }

      this.mLastDrawable = null;
   }

   void setCurrentIndex(int var1) {
      this.selectDrawable(var1);
   }

   public void setDither(boolean var1) {
      if (this.mDrawableContainerState.mDither != var1) {
         this.mDrawableContainerState.mDither = var1;
         Drawable var2 = this.mCurrDrawable;
         if (var2 != null) {
            var2.setDither(this.mDrawableContainerState.mDither);
         }
      }

   }

   public void setEnterFadeDuration(int var1) {
      this.mDrawableContainerState.mEnterFadeDuration = var1;
   }

   public void setExitFadeDuration(int var1) {
      this.mDrawableContainerState.mExitFadeDuration = var1;
   }

   public void setHotspot(float var1, float var2) {
      Drawable var3 = this.mCurrDrawable;
      if (var3 != null) {
         DrawableCompat.setHotspot(var3, var1, var2);
      }

   }

   public void setHotspotBounds(int var1, int var2, int var3, int var4) {
      Rect var5 = this.mHotspotBounds;
      if (var5 == null) {
         this.mHotspotBounds = new Rect(var1, var2, var3, var4);
      } else {
         var5.set(var1, var2, var3, var4);
      }

      Drawable var6 = this.mCurrDrawable;
      if (var6 != null) {
         DrawableCompat.setHotspotBounds(var6, var1, var2, var3, var4);
      }

   }

   public void setTintList(ColorStateList var1) {
      this.mDrawableContainerState.mHasTintList = true;
      if (this.mDrawableContainerState.mTintList != var1) {
         this.mDrawableContainerState.mTintList = var1;
         DrawableCompat.setTintList(this.mCurrDrawable, var1);
      }

   }

   public void setTintMode(PorterDuff.Mode var1) {
      this.mDrawableContainerState.mHasTintMode = true;
      if (this.mDrawableContainerState.mTintMode != var1) {
         this.mDrawableContainerState.mTintMode = var1;
         DrawableCompat.setTintMode(this.mCurrDrawable, var1);
      }

   }

   public boolean setVisible(boolean var1, boolean var2) {
      boolean var3 = super.setVisible(var1, var2);
      Drawable var4 = this.mLastDrawable;
      if (var4 != null) {
         var4.setVisible(var1, var2);
      }

      var4 = this.mCurrDrawable;
      if (var4 != null) {
         var4.setVisible(var1, var2);
      }

      return var3;
   }

   public void unscheduleDrawable(Drawable var1, Runnable var2) {
      if (var1 == this.mCurrDrawable && this.getCallback() != null) {
         this.getCallback().unscheduleDrawable(this, var2);
      }

   }

   final void updateDensity(Resources var1) {
      this.mDrawableContainerState.updateDensity(var1);
   }

   private static class Api21Impl {
      public static boolean canApplyTheme(Drawable.ConstantState var0) {
         return var0.canApplyTheme();
      }

      public static void getOutline(Drawable var0, Outline var1) {
         var0.getOutline(var1);
      }

      public static Resources getResources(Resources.Theme var0) {
         return var0.getResources();
      }
   }

   static class BlockInvalidateCallback implements Drawable.Callback {
      private Drawable.Callback mCallback;

      public void invalidateDrawable(Drawable var1) {
      }

      public void scheduleDrawable(Drawable var1, Runnable var2, long var3) {
         Drawable.Callback var5 = this.mCallback;
         if (var5 != null) {
            var5.scheduleDrawable(var1, var2, var3);
         }

      }

      public void unscheduleDrawable(Drawable var1, Runnable var2) {
         Drawable.Callback var3 = this.mCallback;
         if (var3 != null) {
            var3.unscheduleDrawable(var1, var2);
         }

      }

      public Drawable.Callback unwrap() {
         Drawable.Callback var1 = this.mCallback;
         this.mCallback = null;
         return var1;
      }

      public BlockInvalidateCallback wrap(Drawable.Callback var1) {
         this.mCallback = var1;
         return this;
      }
   }

   abstract static class DrawableContainerState extends Drawable.ConstantState {
      boolean mAutoMirrored;
      boolean mCanConstantState;
      int mChangingConfigurations;
      boolean mCheckedConstantSize;
      boolean mCheckedConstantState;
      boolean mCheckedOpacity;
      boolean mCheckedPadding;
      boolean mCheckedStateful;
      int mChildrenChangingConfigurations;
      ColorFilter mColorFilter;
      int mConstantHeight;
      int mConstantMinimumHeight;
      int mConstantMinimumWidth;
      Rect mConstantPadding;
      boolean mConstantSize;
      int mConstantWidth;
      int mDensity;
      boolean mDither;
      SparseArray mDrawableFutures;
      Drawable[] mDrawables;
      int mEnterFadeDuration;
      int mExitFadeDuration;
      boolean mHasColorFilter;
      boolean mHasTintList;
      boolean mHasTintMode;
      int mLayoutDirection;
      boolean mMutated;
      int mNumChildren;
      int mOpacity;
      final DrawableContainer mOwner;
      Resources mSourceRes;
      boolean mStateful;
      ColorStateList mTintList;
      PorterDuff.Mode mTintMode;
      boolean mVariablePadding;

      DrawableContainerState(DrawableContainerState var1, DrawableContainer var2, Resources var3) {
         byte var5 = 0;
         this.mVariablePadding = false;
         this.mConstantSize = false;
         this.mDither = true;
         this.mEnterFadeDuration = 0;
         this.mExitFadeDuration = 0;
         this.mOwner = var2;
         Object var7 = null;
         Resources var11;
         if (var3 != null) {
            var11 = var3;
         } else if (var1 != null) {
            var11 = var1.mSourceRes;
         } else {
            var11 = null;
         }

         this.mSourceRes = var11;
         int var4;
         if (var1 != null) {
            var4 = var1.mDensity;
         } else {
            var4 = 0;
         }

         var4 = DrawableContainer.resolveDensity(var3, var4);
         this.mDensity = var4;
         if (var1 != null) {
            this.mChangingConfigurations = var1.mChangingConfigurations;
            this.mChildrenChangingConfigurations = var1.mChildrenChangingConfigurations;
            this.mCheckedConstantState = true;
            this.mCanConstantState = true;
            this.mVariablePadding = var1.mVariablePadding;
            this.mConstantSize = var1.mConstantSize;
            this.mDither = var1.mDither;
            this.mMutated = var1.mMutated;
            this.mLayoutDirection = var1.mLayoutDirection;
            this.mEnterFadeDuration = var1.mEnterFadeDuration;
            this.mExitFadeDuration = var1.mExitFadeDuration;
            this.mAutoMirrored = var1.mAutoMirrored;
            this.mColorFilter = var1.mColorFilter;
            this.mHasColorFilter = var1.mHasColorFilter;
            this.mTintList = var1.mTintList;
            this.mTintMode = var1.mTintMode;
            this.mHasTintList = var1.mHasTintList;
            this.mHasTintMode = var1.mHasTintMode;
            if (var1.mDensity == var4) {
               if (var1.mCheckedPadding) {
                  Rect var12 = (Rect)var7;
                  if (var1.mConstantPadding != null) {
                     var12 = new Rect(var1.mConstantPadding);
                  }

                  this.mConstantPadding = var12;
                  this.mCheckedPadding = true;
               }

               if (var1.mCheckedConstantSize) {
                  this.mConstantWidth = var1.mConstantWidth;
                  this.mConstantHeight = var1.mConstantHeight;
                  this.mConstantMinimumWidth = var1.mConstantMinimumWidth;
                  this.mConstantMinimumHeight = var1.mConstantMinimumHeight;
                  this.mCheckedConstantSize = true;
               }
            }

            if (var1.mCheckedOpacity) {
               this.mOpacity = var1.mOpacity;
               this.mCheckedOpacity = true;
            }

            if (var1.mCheckedStateful) {
               this.mStateful = var1.mStateful;
               this.mCheckedStateful = true;
            }

            Drawable[] var13 = var1.mDrawables;
            this.mDrawables = new Drawable[var13.length];
            this.mNumChildren = var1.mNumChildren;
            SparseArray var8 = var1.mDrawableFutures;
            if (var8 != null) {
               this.mDrawableFutures = var8.clone();
            } else {
               this.mDrawableFutures = new SparseArray(this.mNumChildren);
            }

            int var6 = this.mNumChildren;

            for(var4 = var5; var4 < var6; ++var4) {
               Drawable var9 = var13[var4];
               if (var9 != null) {
                  Drawable.ConstantState var10 = var9.getConstantState();
                  if (var10 != null) {
                     this.mDrawableFutures.put(var4, var10);
                  } else {
                     this.mDrawables[var4] = var13[var4];
                  }
               }
            }
         } else {
            this.mDrawables = new Drawable[10];
            this.mNumChildren = 0;
         }

      }

      private void createAllFutures() {
         SparseArray var4 = this.mDrawableFutures;
         if (var4 != null) {
            int var2 = var4.size();

            for(int var1 = 0; var1 < var2; ++var1) {
               int var3 = this.mDrawableFutures.keyAt(var1);
               Drawable.ConstantState var5 = (Drawable.ConstantState)this.mDrawableFutures.valueAt(var1);
               this.mDrawables[var3] = this.prepareDrawable(var5.newDrawable(this.mSourceRes));
            }

            this.mDrawableFutures = null;
         }

      }

      private Drawable prepareDrawable(Drawable var1) {
         if (VERSION.SDK_INT >= 23) {
            DrawableCompat.setLayoutDirection(var1, this.mLayoutDirection);
         }

         var1 = var1.mutate();
         var1.setCallback(this.mOwner);
         return var1;
      }

      public final int addChild(Drawable var1) {
         int var2 = this.mNumChildren;
         if (var2 >= this.mDrawables.length) {
            this.growArray(var2, var2 + 10);
         }

         var1.mutate();
         var1.setVisible(false, true);
         var1.setCallback(this.mOwner);
         this.mDrawables[var2] = var1;
         ++this.mNumChildren;
         int var3 = this.mChildrenChangingConfigurations;
         this.mChildrenChangingConfigurations = var1.getChangingConfigurations() | var3;
         this.invalidateCache();
         this.mConstantPadding = null;
         this.mCheckedPadding = false;
         this.mCheckedConstantSize = false;
         this.mCheckedConstantState = false;
         return var2;
      }

      final void applyTheme(Resources.Theme var1) {
         if (var1 != null) {
            this.createAllFutures();
            int var3 = this.mNumChildren;
            Drawable[] var5 = this.mDrawables;

            for(int var2 = 0; var2 < var3; ++var2) {
               Drawable var4 = var5[var2];
               if (var4 != null && DrawableCompat.canApplyTheme(var4)) {
                  DrawableCompat.applyTheme(var5[var2], var1);
                  this.mChildrenChangingConfigurations |= var5[var2].getChangingConfigurations();
               }
            }

            this.updateDensity(var1.getResources());
         }

      }

      public boolean canApplyTheme() {
         int var2 = this.mNumChildren;
         Drawable[] var3 = this.mDrawables;

         for(int var1 = 0; var1 < var2; ++var1) {
            Drawable var4 = var3[var1];
            if (var4 != null) {
               if (DrawableCompat.canApplyTheme(var4)) {
                  return true;
               }
            } else {
               Drawable.ConstantState var5 = (Drawable.ConstantState)this.mDrawableFutures.get(var1);
               if (var5 != null && var5.canApplyTheme()) {
                  return true;
               }
            }
         }

         return false;
      }

      public boolean canConstantState() {
         if (this.mCheckedConstantState) {
            return this.mCanConstantState;
         } else {
            this.createAllFutures();
            this.mCheckedConstantState = true;
            int var2 = this.mNumChildren;
            Drawable[] var3 = this.mDrawables;

            for(int var1 = 0; var1 < var2; ++var1) {
               if (var3[var1].getConstantState() == null) {
                  this.mCanConstantState = false;
                  return false;
               }
            }

            this.mCanConstantState = true;
            return true;
         }
      }

      final void clearMutated() {
         this.mMutated = false;
      }

      protected void computeConstantSize() {
         this.mCheckedConstantSize = true;
         this.createAllFutures();
         int var2 = this.mNumChildren;
         Drawable[] var5 = this.mDrawables;
         this.mConstantHeight = -1;
         this.mConstantWidth = -1;
         int var1 = 0;
         this.mConstantMinimumHeight = 0;

         for(this.mConstantMinimumWidth = 0; var1 < var2; ++var1) {
            Drawable var4 = var5[var1];
            int var3 = var4.getIntrinsicWidth();
            if (var3 > this.mConstantWidth) {
               this.mConstantWidth = var3;
            }

            var3 = var4.getIntrinsicHeight();
            if (var3 > this.mConstantHeight) {
               this.mConstantHeight = var3;
            }

            var3 = var4.getMinimumWidth();
            if (var3 > this.mConstantMinimumWidth) {
               this.mConstantMinimumWidth = var3;
            }

            var3 = var4.getMinimumHeight();
            if (var3 > this.mConstantMinimumHeight) {
               this.mConstantMinimumHeight = var3;
            }
         }

      }

      final int getCapacity() {
         return this.mDrawables.length;
      }

      public int getChangingConfigurations() {
         return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
      }

      public final Drawable getChild(int var1) {
         Drawable var3 = this.mDrawables[var1];
         if (var3 != null) {
            return var3;
         } else {
            SparseArray var4 = this.mDrawableFutures;
            if (var4 != null) {
               int var2 = var4.indexOfKey(var1);
               if (var2 >= 0) {
                  var3 = this.prepareDrawable(((Drawable.ConstantState)this.mDrawableFutures.valueAt(var2)).newDrawable(this.mSourceRes));
                  this.mDrawables[var1] = var3;
                  this.mDrawableFutures.removeAt(var2);
                  if (this.mDrawableFutures.size() == 0) {
                     this.mDrawableFutures = null;
                  }

                  return var3;
               }
            }

            return null;
         }
      }

      public final int getChildCount() {
         return this.mNumChildren;
      }

      public final int getConstantHeight() {
         if (!this.mCheckedConstantSize) {
            this.computeConstantSize();
         }

         return this.mConstantHeight;
      }

      public final int getConstantMinimumHeight() {
         if (!this.mCheckedConstantSize) {
            this.computeConstantSize();
         }

         return this.mConstantMinimumHeight;
      }

      public final int getConstantMinimumWidth() {
         if (!this.mCheckedConstantSize) {
            this.computeConstantSize();
         }

         return this.mConstantMinimumWidth;
      }

      public final Rect getConstantPadding() {
         boolean var3 = this.mVariablePadding;
         Rect var5 = null;
         if (var3) {
            return null;
         } else {
            Rect var4 = this.mConstantPadding;
            if (var4 == null && !this.mCheckedPadding) {
               this.createAllFutures();
               Rect var8 = new Rect();
               int var2 = this.mNumChildren;
               Drawable[] var7 = this.mDrawables;

               Rect var6;
               for(int var1 = 0; var1 < var2; var5 = var6) {
                  var6 = var5;
                  if (var7[var1].getPadding(var8)) {
                     var4 = var5;
                     if (var5 == null) {
                        var4 = new Rect(0, 0, 0, 0);
                     }

                     if (var8.left > var4.left) {
                        var4.left = var8.left;
                     }

                     if (var8.top > var4.top) {
                        var4.top = var8.top;
                     }

                     if (var8.right > var4.right) {
                        var4.right = var8.right;
                     }

                     var6 = var4;
                     if (var8.bottom > var4.bottom) {
                        var4.bottom = var8.bottom;
                        var6 = var4;
                     }
                  }

                  ++var1;
               }

               this.mCheckedPadding = true;
               this.mConstantPadding = var5;
               return var5;
            } else {
               return var4;
            }
         }
      }

      public final int getConstantWidth() {
         if (!this.mCheckedConstantSize) {
            this.computeConstantSize();
         }

         return this.mConstantWidth;
      }

      public final int getEnterFadeDuration() {
         return this.mEnterFadeDuration;
      }

      public final int getExitFadeDuration() {
         return this.mExitFadeDuration;
      }

      public final int getOpacity() {
         if (this.mCheckedOpacity) {
            return this.mOpacity;
         } else {
            this.createAllFutures();
            int var3 = this.mNumChildren;
            Drawable[] var4 = this.mDrawables;
            int var1;
            if (var3 > 0) {
               var1 = var4[0].getOpacity();
            } else {
               var1 = -2;
            }

            for(int var2 = 1; var2 < var3; ++var2) {
               var1 = Drawable.resolveOpacity(var1, var4[var2].getOpacity());
            }

            this.mOpacity = var1;
            this.mCheckedOpacity = true;
            return var1;
         }
      }

      public void growArray(int var1, int var2) {
         Drawable[] var4 = new Drawable[var2];
         Drawable[] var3 = this.mDrawables;
         if (var3 != null) {
            System.arraycopy(var3, 0, var4, 0, var1);
         }

         this.mDrawables = var4;
      }

      void invalidateCache() {
         this.mCheckedOpacity = false;
         this.mCheckedStateful = false;
      }

      public final boolean isConstantSize() {
         return this.mConstantSize;
      }

      public final boolean isStateful() {
         if (this.mCheckedStateful) {
            return this.mStateful;
         } else {
            this.createAllFutures();
            int var2 = this.mNumChildren;
            Drawable[] var5 = this.mDrawables;
            boolean var4 = false;
            int var1 = 0;

            boolean var3;
            while(true) {
               var3 = var4;
               if (var1 >= var2) {
                  break;
               }

               if (var5[var1].isStateful()) {
                  var3 = true;
                  break;
               }

               ++var1;
            }

            this.mStateful = var3;
            this.mCheckedStateful = true;
            return var3;
         }
      }

      void mutate() {
         int var2 = this.mNumChildren;
         Drawable[] var4 = this.mDrawables;

         for(int var1 = 0; var1 < var2; ++var1) {
            Drawable var3 = var4[var1];
            if (var3 != null) {
               var3.mutate();
            }
         }

         this.mMutated = true;
      }

      public final void setConstantSize(boolean var1) {
         this.mConstantSize = var1;
      }

      public final void setEnterFadeDuration(int var1) {
         this.mEnterFadeDuration = var1;
      }

      public final void setExitFadeDuration(int var1) {
         this.mExitFadeDuration = var1;
      }

      final boolean setLayoutDirection(int var1, int var2) {
         int var4 = this.mNumChildren;
         Drawable[] var8 = this.mDrawables;
         int var3 = 0;

         boolean var6;
         boolean var7;
         for(var6 = false; var3 < var4; var6 = var7) {
            var7 = var6;
            if (var8[var3] != null) {
               boolean var5;
               if (VERSION.SDK_INT >= 23) {
                  var5 = DrawableCompat.setLayoutDirection(var8[var3], var1);
               } else {
                  var5 = false;
               }

               var7 = var6;
               if (var3 == var2) {
                  var7 = var5;
               }
            }

            ++var3;
         }

         this.mLayoutDirection = var1;
         return var6;
      }

      public final void setVariablePadding(boolean var1) {
         this.mVariablePadding = var1;
      }

      final void updateDensity(Resources var1) {
         if (var1 != null) {
            this.mSourceRes = var1;
            int var3 = DrawableContainer.resolveDensity(var1, this.mDensity);
            int var2 = this.mDensity;
            this.mDensity = var3;
            if (var2 != var3) {
               this.mCheckedConstantSize = false;
               this.mCheckedPadding = false;
            }
         }

      }
   }
}
