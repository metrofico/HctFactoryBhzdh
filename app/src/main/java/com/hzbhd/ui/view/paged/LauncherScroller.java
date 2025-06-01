package com.hzbhd.ui.view.paged;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u000e\u0018\u0000 Y2\u00020\u0001:\u0001YB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010;\u001a\u00020<J\u0010\u0010=\u001a\u00020\n2\u0006\u0010>\u001a\u00020\nH\u0002J\u0006\u0010?\u001a\u00020\u0007J\u000e\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020\u000eJF\u0010B\u001a\u00020<2\u0006\u00107\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000e2\u0006\u0010E\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eJ\u000e\u0010I\u001a\u00020<2\u0006\u0010J\u001a\u00020\u0007J\u0010\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\nH\u0002J\u0010\u0010N\u001a\u00020L2\u0006\u0010M\u001a\u00020\nH\u0002J\u0010\u0010O\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\nH\u0002J\u0016\u0010P\u001a\u00020\u00072\u0006\u0010Q\u001a\u00020\n2\u0006\u0010R\u001a\u00020\nJ\u000e\u0010S\u001a\u00020<2\u0006\u0010>\u001a\u00020\nJ\u0010\u0010T\u001a\u00020<2\b\u0010\u0004\u001a\u0004\u0018\u00010,J2\u0010U\u001a\u00020<2\u0006\u00107\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\u000e2\u0006\u0010W\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u000eH\u0007J\u0006\u0010X\u001a\u00020\u000eR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R$\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u001aR\u001e\u0010\u001f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00107\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0011R\u001e\u00109\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0011¨\u0006Z"},
   d2 = {"Lcom/hzbhd/ui/view/paged/LauncherScroller;", "", "context", "Landroid/content/Context;", "interpolator", "Landroid/view/animation/Interpolator;", "flywheel", "", "(Landroid/content/Context;Landroid/view/animation/Interpolator;Z)V", "currVelocity", "", "getCurrVelocity", "()F", "<set-?>", "", "currX", "getCurrX", "()I", "currY", "getCurrY", "duration", "getDuration", "newX", "finalX", "getFinalX", "setFinalX", "(I)V", "newY", "finalY", "getFinalY", "setFinalY", "isFinished", "()Z", "mCurrVelocity", "mDeceleration", "mDeltaX", "mDeltaY", "mDistance", "mDurationReciprocal", "mFinalX", "mFinalY", "mFlingFriction", "mFlywheel", "mInterpolator", "Landroid/animation/TimeInterpolator;", "mMaxX", "mMaxY", "mMinX", "mMinY", "mMode", "mPhysicalCoeff", "mPpi", "mStartTime", "", "mVelocity", "startX", "getStartX", "startY", "getStartY", "abortAnimation", "", "computeDeceleration", "friction", "computeScrollOffset", "extendDuration", "extend", "fling", "velocityX", "velocityY", "minX", "maxX", "minY", "maxY", "forceFinished", "finished", "getSplineDeceleration", "", "velocity", "getSplineFlingDistance", "getSplineFlingDuration", "isScrollingInDirection", "xvel", "yvel", "setFriction", "setInterpolator", "startScroll", "dx", "dy", "timePassed", "Companion", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class LauncherScroller {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final float DECELERATION_RATE = (float)(Math.log(0.78) / Math.log(0.9));
   private static final int DEFAULT_DURATION = 250;
   private static final float END_TENSION = 1.0F;
   private static final int FLING_MODE = 1;
   private static final float INFLEXION = 0.35F;
   private static final int NB_SAMPLES = 100;
   private static final float P1 = 0.175F;
   private static final float P2 = 0.35000002F;
   private static final int SCROLL_MODE = 0;
   private static final float[] SPLINE_POSITION = new float[101];
   private static final float[] SPLINE_TIME = new float[101];
   private static final float START_TENSION = 0.5F;
   private static float sViscousFluidNormalize;
   private static float sViscousFluidScale;
   private int currX;
   private int currY;
   private int duration;
   private boolean isFinished;
   private float mCurrVelocity;
   private float mDeceleration;
   private float mDeltaX;
   private float mDeltaY;
   private int mDistance;
   private float mDurationReciprocal;
   private int mFinalX;
   private int mFinalY;
   private float mFlingFriction;
   private final boolean mFlywheel;
   private TimeInterpolator mInterpolator;
   private int mMaxX;
   private int mMaxY;
   private int mMinX;
   private int mMinY;
   private int mMode;
   private final float mPhysicalCoeff;
   private final float mPpi;
   private long mStartTime;
   private float mVelocity;
   private int startX;
   private int startY;

   static {
      float var0 = 0.0F;
      int var9 = 0;

      label35:
      for(float var1 = 0.0F; var9 < 100; ++var9) {
         float var4 = (float)var9 / (float)100;
         float var2 = 1.0F;

         while(true) {
            float var3 = (var2 - var0) / 2.0F + var0;
            float var7 = 1.0F - var3;
            float var8 = var3 * 3.0F * var7;
            float var6 = var3 * var3 * var3;
            float var5 = (var7 * 0.175F + var3 * 0.35000002F) * var8 + var6;
            if ((double)Math.abs(var5 - var4) < 1.0E-5) {
               SPLINE_POSITION[var9] = var8 * (var7 * 0.5F + var3) + var6;
               var2 = 1.0F;

               while(true) {
                  var3 = (var2 - var1) / 2.0F + var1;
                  var6 = 1.0F - var3;
                  var7 = var3 * 3.0F * var6;
                  var8 = var3 * var3 * var3;
                  var5 = (var6 * 0.5F + var3) * var7 + var8;
                  if ((double)Math.abs(var5 - var4) < 1.0E-5) {
                     SPLINE_TIME[var9] = var7 * (var6 * 0.175F + var3 * 0.35000002F) + var8;
                     continue label35;
                  }

                  if (var5 > var4) {
                     var2 = var3;
                  } else {
                     var1 = var3;
                  }
               }
            }

            if (var5 > var4) {
               var2 = var3;
            } else {
               var0 = var3;
            }
         }
      }

      SPLINE_TIME[100] = 1.0F;
      SPLINE_POSITION[100] = 1.0F;
      Companion var10 = Companion;
      sViscousFluidScale = 8.0F;
      sViscousFluidNormalize = 1.0F;
      sViscousFluidNormalize = 1.0F / var10.viscousFluid(1.0F);
   }

   public LauncherScroller(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this(var1, (Interpolator)null, false, 6, (DefaultConstructorMarker)null);
   }

   public LauncherScroller(Context var1, Interpolator var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this(var1, var2, false, 4, (DefaultConstructorMarker)null);
   }

   public LauncherScroller(Context var1, Interpolator var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      this.isFinished = true;
      this.mFlingFriction = ViewConfiguration.getScrollFriction();
      this.mInterpolator = (TimeInterpolator)var2;
      this.mPpi = var1.getResources().getDisplayMetrics().density * 160.0F;
      this.mDeceleration = this.computeDeceleration(ViewConfiguration.getScrollFriction());
      this.mFlywheel = var3;
      this.mPhysicalCoeff = this.computeDeceleration(0.84F);
   }

   // $FF: synthetic method
   public LauncherScroller(Context var1, Interpolator var2, boolean var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         if (var1.getApplicationInfo().targetSdkVersion >= 11) {
            var3 = true;
         } else {
            var3 = false;
         }
      }

      this(var1, var2, var3);
   }

   private final float computeDeceleration(float var1) {
      return this.mPpi * 386.0878F * var1;
   }

   private final double getSplineDeceleration(float var1) {
      return Math.log((double)(Math.abs(var1) * 0.35F / (this.mFlingFriction * this.mPhysicalCoeff)));
   }

   private final double getSplineFlingDistance(float var1) {
      double var2 = this.getSplineDeceleration(var1);
      var1 = DECELERATION_RATE;
      double var4 = (double)var1;
      return (double)(this.mFlingFriction * this.mPhysicalCoeff) * Math.exp((double)var1 / (var4 - 1.0) * var2);
   }

   private final int getSplineFlingDuration(float var1) {
      return (int)(Math.exp(this.getSplineDeceleration(var1) / ((double)DECELERATION_RATE - 1.0)) * 1000.0);
   }

   // $FF: synthetic method
   public static void startScroll$default(LauncherScroller var0, int var1, int var2, int var3, int var4, int var5, int var6, Object var7) {
      if ((var6 & 16) != 0) {
         var5 = 250;
      }

      var0.startScroll(var1, var2, var3, var4, var5);
   }

   public final void abortAnimation() {
      this.currX = this.mFinalX;
      this.currY = this.mFinalY;
      this.isFinished = true;
   }

   public final boolean computeScrollOffset() {
      if (this.isFinished) {
         return false;
      } else {
         int var5 = (int)(AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
         int var6 = this.duration;
         if (var5 < var6) {
            int var7 = this.mMode;
            float var1;
            if (var7 != 0) {
               if (var7 == 1) {
                  float var3 = (float)var5 / (float)var6;
                  float var4 = (float)100;
                  var5 = (int)(var4 * var3);
                  float var2 = 1.0F;
                  var1 = 0.0F;
                  if (var5 < 100) {
                     var2 = (float)var5 / var4;
                     var6 = var5 + 1;
                     var1 = (Float)var6 / var4;
                     float[] var8 = SPLINE_POSITION;
                     var4 = var8[var5];
                     var1 = (var8[var6] - var4) / (var1 - var2);
                     var2 = var4 + (var3 - var2) * var1;
                  }

                  this.mCurrVelocity = var1 * (float)this.mDistance / (float)this.duration * 1000.0F;
                  var5 = this.startX;
                  var5 += Math.round((float)(this.mFinalX - var5) * var2);
                  this.currX = var5;
                  var5 = Math.min(var5, this.mMaxX);
                  this.currX = var5;
                  this.currX = Math.max(var5, this.mMinX);
                  var5 = this.startY;
                  var5 += Math.round(var2 * (float)(this.mFinalY - var5));
                  this.currY = var5;
                  var5 = Math.min(var5, this.mMaxY);
                  this.currY = var5;
                  var5 = Math.max(var5, this.mMinY);
                  this.currY = var5;
                  if (this.currX == this.mFinalX && var5 == this.mFinalY) {
                     this.isFinished = true;
                  }
               }
            } else {
               var1 = (float)var5 * this.mDurationReciprocal;
               TimeInterpolator var9 = this.mInterpolator;
               if (var9 == null) {
                  var1 = Companion.viscousFluid(var1);
               } else {
                  Intrinsics.checkNotNull(var9);
                  var1 = var9.getInterpolation(var1);
               }

               this.currX = this.startX + Math.round(this.mDeltaX * var1);
               this.currY = this.startY + Math.round(var1 * this.mDeltaY);
            }
         } else {
            this.currX = this.mFinalX;
            this.currY = this.mFinalY;
            this.isFinished = true;
         }

         return true;
      }
   }

   public final void extendDuration(int var1) {
      var1 += this.timePassed();
      this.duration = var1;
      this.mDurationReciprocal = 1.0F / (float)var1;
      this.isFinished = false;
   }

   public final void fling(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int var15 = var3;
      boolean var19 = this.mFlywheel;
      boolean var18 = false;
      int var16 = var3;
      var3 = var4;
      float var11;
      float var12;
      float var13;
      if (var19) {
         var16 = var15;
         var3 = var4;
         if (!this.isFinished) {
            var11 = this.getCurrVelocity();
            float var14 = (float)(this.mFinalX - this.startX);
            var13 = (float)(this.mFinalY - this.startY);
            var12 = (float)Math.hypot((double)var14, (double)var13);
            var14 /= var12;
            var13 /= var12;
            var12 = var14 * var11;
            var11 = var13 * var11;
            boolean var17;
            if (Math.signum((float)var15) == Math.signum(var12)) {
               var17 = true;
            } else {
               var17 = false;
            }

            var16 = var15;
            var3 = var4;
            if (var17) {
               if (Math.signum((float)var4) == Math.signum(var11)) {
                  var17 = true;
               } else {
                  var17 = false;
               }

               var16 = var15;
               var3 = var4;
               if (var17) {
                  var16 = var15 + (int)var12;
                  var3 = var4 + (int)var11;
               }
            }
         }
      }

      this.mMode = 1;
      this.isFinished = false;
      var13 = (float)Math.hypot((double)var16, (double)var3);
      this.mVelocity = var13;
      this.duration = this.getSplineFlingDuration(var13);
      this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
      this.startX = var1;
      this.startY = var2;
      float var21;
      var15 = (var21 = var13 - 0.0F) == 0.0F ? 0 : (var21 < 0.0F ? -1 : 1);
      boolean var20;
      if (var15 == 0) {
         var20 = true;
      } else {
         var20 = false;
      }

      var12 = 1.0F;
      if (var20) {
         var11 = 1.0F;
      } else {
         var11 = (float)var16 / var13;
      }

      var20 = var18;
      if (var15 == 0) {
         var20 = true;
      }

      if (!var20) {
         var12 = (float)var3 / var13;
      }

      double var9 = this.getSplineFlingDistance(var13);
      this.mDistance = (int)((double)Math.signum(var13) * var9);
      this.mMinX = var5;
      this.mMaxX = var6;
      this.mMinY = var7;
      this.mMaxY = var8;
      var1 += (int)Math.round((double)var11 * var9);
      this.mFinalX = var1;
      var1 = Math.min(var1, this.mMaxX);
      this.mFinalX = var1;
      this.mFinalX = Math.max(var1, this.mMinX);
      var1 = (int)Math.round(var9 * (double)var12) + var2;
      this.mFinalY = var1;
      var1 = Math.min(var1, this.mMaxY);
      this.mFinalY = var1;
      this.mFinalY = Math.max(var1, this.mMinY);
   }

   public final void forceFinished(boolean var1) {
      this.isFinished = var1;
   }

   public final float getCurrVelocity() {
      float var1;
      if (this.mMode == 1) {
         var1 = this.mCurrVelocity;
      } else {
         var1 = this.mVelocity - this.mDeceleration * (float)this.timePassed() / 2000.0F;
      }

      return var1;
   }

   public final int getCurrX() {
      return this.currX;
   }

   public final int getCurrY() {
      return this.currY;
   }

   public final int getDuration() {
      return this.duration;
   }

   public final int getFinalX() {
      return this.mFinalX;
   }

   public final int getFinalY() {
      return this.mFinalY;
   }

   public final int getStartX() {
      return this.startX;
   }

   public final int getStartY() {
      return this.startY;
   }

   public final boolean isFinished() {
      return this.isFinished;
   }

   public final boolean isScrollingInDirection(float var1, float var2) {
      boolean var5 = this.isFinished;
      boolean var4 = true;
      if (!var5) {
         boolean var3;
         if (Math.signum(var1) == Math.signum((float)(this.mFinalX - this.startX))) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            if (Math.signum(var2) == Math.signum((float)(this.mFinalY - this.startY))) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (var3) {
               return var4;
            }
         }
      }

      var4 = false;
      return var4;
   }

   public final void setFinalX(int var1) {
      this.mFinalX = var1;
      this.mDeltaX = (float)(var1 - this.startX);
      this.isFinished = false;
   }

   public final void setFinalY(int var1) {
      this.mFinalY = var1;
      this.mDeltaY = (float)(var1 - this.startY);
      this.isFinished = false;
   }

   public final void setFriction(float var1) {
      this.mDeceleration = this.computeDeceleration(var1);
      this.mFlingFriction = var1;
   }

   public final void setInterpolator(TimeInterpolator var1) {
      this.mInterpolator = var1;
   }

   public final void startScroll(int var1, int var2, int var3, int var4) {
      startScroll$default(this, var1, var2, var3, var4, 0, 16, (Object)null);
   }

   public final void startScroll(int var1, int var2, int var3, int var4, int var5) {
      this.mMode = 0;
      this.isFinished = false;
      this.duration = var5;
      this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
      this.startX = var1;
      this.startY = var2;
      this.mFinalX = var1 + var3;
      this.mFinalY = var2 + var4;
      this.mDeltaX = (float)var3;
      this.mDeltaY = (float)var4;
      this.mDurationReciprocal = 1.0F / (float)this.duration;
   }

   public final int timePassed() {
      return (int)(AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"},
      d2 = {"Lcom/hzbhd/ui/view/paged/LauncherScroller$Companion;", "", "()V", "DECELERATION_RATE", "", "DEFAULT_DURATION", "", "END_TENSION", "FLING_MODE", "INFLEXION", "NB_SAMPLES", "P1", "P2", "SCROLL_MODE", "SPLINE_POSITION", "", "SPLINE_TIME", "START_TENSION", "sViscousFluidNormalize", "sViscousFluidScale", "viscousFluid", "x", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final float viscousFluid(float var1) {
         var1 *= LauncherScroller.sViscousFluidScale;
         if (var1 < 1.0F) {
            var1 -= 1.0F - (float)Math.exp(-((double)var1));
         } else {
            var1 = (1.0F - (float)Math.exp((double)(1.0F - var1))) * 0.63212055F + 0.36787945F;
         }

         return var1 * LauncherScroller.sViscousFluidNormalize;
      }
   }
}
