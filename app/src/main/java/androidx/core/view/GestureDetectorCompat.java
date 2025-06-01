package androidx.core.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class GestureDetectorCompat {
   private final GestureDetectorCompatImpl mImpl;

   public GestureDetectorCompat(Context var1, GestureDetector.OnGestureListener var2) {
      this(var1, var2, (Handler)null);
   }

   public GestureDetectorCompat(Context var1, GestureDetector.OnGestureListener var2, Handler var3) {
      if (VERSION.SDK_INT > 17) {
         this.mImpl = new GestureDetectorCompatImplJellybeanMr2(var1, var2, var3);
      } else {
         this.mImpl = new GestureDetectorCompatImplBase(var1, var2, var3);
      }

   }

   public boolean isLongpressEnabled() {
      return this.mImpl.isLongpressEnabled();
   }

   public boolean onTouchEvent(MotionEvent var1) {
      return this.mImpl.onTouchEvent(var1);
   }

   public void setIsLongpressEnabled(boolean var1) {
      this.mImpl.setIsLongpressEnabled(var1);
   }

   public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener var1) {
      this.mImpl.setOnDoubleTapListener(var1);
   }

   interface GestureDetectorCompatImpl {
      boolean isLongpressEnabled();

      boolean onTouchEvent(MotionEvent var1);

      void setIsLongpressEnabled(boolean var1);

      void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener var1);
   }

   static class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
      private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
      private static final int LONG_PRESS = 2;
      private static final int SHOW_PRESS = 1;
      private static final int TAP = 3;
      private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
      private boolean mAlwaysInBiggerTapRegion;
      private boolean mAlwaysInTapRegion;
      MotionEvent mCurrentDownEvent;
      boolean mDeferConfirmSingleTap;
      GestureDetector.OnDoubleTapListener mDoubleTapListener;
      private int mDoubleTapSlopSquare;
      private float mDownFocusX;
      private float mDownFocusY;
      private final Handler mHandler;
      private boolean mInLongPress;
      private boolean mIsDoubleTapping;
      private boolean mIsLongpressEnabled;
      private float mLastFocusX;
      private float mLastFocusY;
      final GestureDetector.OnGestureListener mListener;
      private int mMaximumFlingVelocity;
      private int mMinimumFlingVelocity;
      private MotionEvent mPreviousUpEvent;
      boolean mStillDown;
      private int mTouchSlopSquare;
      private VelocityTracker mVelocityTracker;

      GestureDetectorCompatImplBase(Context var1, GestureDetector.OnGestureListener var2, Handler var3) {
         if (var3 != null) {
            this.mHandler = new GestureHandler(this, var3);
         } else {
            this.mHandler = new GestureHandler(this);
         }

         this.mListener = var2;
         if (var2 instanceof GestureDetector.OnDoubleTapListener) {
            this.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener)var2);
         }

         this.init(var1);
      }

      private void cancel() {
         this.mHandler.removeMessages(1);
         this.mHandler.removeMessages(2);
         this.mHandler.removeMessages(3);
         this.mVelocityTracker.recycle();
         this.mVelocityTracker = null;
         this.mIsDoubleTapping = false;
         this.mStillDown = false;
         this.mAlwaysInTapRegion = false;
         this.mAlwaysInBiggerTapRegion = false;
         this.mDeferConfirmSingleTap = false;
         if (this.mInLongPress) {
            this.mInLongPress = false;
         }

      }

      private void cancelTaps() {
         this.mHandler.removeMessages(1);
         this.mHandler.removeMessages(2);
         this.mHandler.removeMessages(3);
         this.mIsDoubleTapping = false;
         this.mAlwaysInTapRegion = false;
         this.mAlwaysInBiggerTapRegion = false;
         this.mDeferConfirmSingleTap = false;
         if (this.mInLongPress) {
            this.mInLongPress = false;
         }

      }

      private void init(Context var1) {
         if (var1 != null) {
            if (this.mListener != null) {
               this.mIsLongpressEnabled = true;
               ViewConfiguration var4 = ViewConfiguration.get(var1);
               int var2 = var4.getScaledTouchSlop();
               int var3 = var4.getScaledDoubleTapSlop();
               this.mMinimumFlingVelocity = var4.getScaledMinimumFlingVelocity();
               this.mMaximumFlingVelocity = var4.getScaledMaximumFlingVelocity();
               this.mTouchSlopSquare = var2 * var2;
               this.mDoubleTapSlopSquare = var3 * var3;
            } else {
               throw new IllegalArgumentException("OnGestureListener must not be null");
            }
         } else {
            throw new IllegalArgumentException("Context must not be null");
         }
      }

      private boolean isConsideredDoubleTap(MotionEvent var1, MotionEvent var2, MotionEvent var3) {
         boolean var7 = this.mAlwaysInBiggerTapRegion;
         boolean var6 = false;
         if (!var7) {
            return false;
         } else if (var3.getEventTime() - var2.getEventTime() > (long)DOUBLE_TAP_TIMEOUT) {
            return false;
         } else {
            int var5 = (int)var1.getX() - (int)var3.getX();
            int var4 = (int)var1.getY() - (int)var3.getY();
            if (var5 * var5 + var4 * var4 < this.mDoubleTapSlopSquare) {
               var6 = true;
            }

            return var6;
         }
      }

      void dispatchLongPress() {
         this.mHandler.removeMessages(3);
         this.mDeferConfirmSingleTap = false;
         this.mInLongPress = true;
         this.mListener.onLongPress(this.mCurrentDownEvent);
      }

      public boolean isLongpressEnabled() {
         return this.mIsLongpressEnabled;
      }

      public boolean onTouchEvent(MotionEvent var1) {
         int var6 = var1.getAction();
         if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
         }

         this.mVelocityTracker.addMovement(var1);
         int var10 = var6 & 255;
         boolean var12 = false;
         boolean var16;
         if (var10 == 6) {
            var16 = true;
         } else {
            var16 = false;
         }

         int var7;
         if (var16) {
            var7 = var1.getActionIndex();
         } else {
            var7 = -1;
         }

         int var9 = var1.getPointerCount();
         int var8 = 0;
         float var3 = 0.0F;

         float var2;
         for(var2 = 0.0F; var8 < var9; ++var8) {
            if (var7 != var8) {
               var3 += var1.getX(var8);
               var2 += var1.getY(var8);
            }
         }

         if (var16) {
            var6 = var9 - 1;
         } else {
            var6 = var9;
         }

         float var4 = (float)var6;
         var3 /= var4;
         var2 /= var4;
         boolean var11;
         MotionEvent var13;
         if (var10 != 0) {
            if (var10 != 1) {
               if (var10 != 2) {
                  if (var10 != 3) {
                     if (var10 != 5) {
                        if (var10 != 6) {
                           var11 = var12;
                        } else {
                           this.mLastFocusX = var3;
                           this.mDownFocusX = var3;
                           this.mLastFocusY = var2;
                           this.mDownFocusY = var2;
                           this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaximumFlingVelocity);
                           var7 = var1.getActionIndex();
                           var6 = var1.getPointerId(var7);
                           var3 = this.mVelocityTracker.getXVelocity(var6);
                           var2 = this.mVelocityTracker.getYVelocity(var6);
                           var6 = 0;

                           while(true) {
                              var11 = var12;
                              if (var6 >= var9) {
                                 return var11;
                              }

                              if (var6 != var7) {
                                 var8 = var1.getPointerId(var6);
                                 if (this.mVelocityTracker.getXVelocity(var8) * var3 + this.mVelocityTracker.getYVelocity(var8) * var2 < 0.0F) {
                                    this.mVelocityTracker.clear();
                                    var11 = var12;
                                    return var11;
                                 }
                              }

                              ++var6;
                           }
                        }
                     } else {
                        this.mLastFocusX = var3;
                        this.mDownFocusX = var3;
                        this.mLastFocusY = var2;
                        this.mDownFocusY = var2;
                        this.cancelTaps();
                        var11 = var12;
                     }

                     return var11;
                  } else {
                     this.cancel();
                     var11 = var12;
                     return var11;
                  }
               }

               if (this.mInLongPress) {
                  var11 = var12;
                  return var11;
               }

               float var5 = this.mLastFocusX - var3;
               var4 = this.mLastFocusY - var2;
               if (this.mIsDoubleTapping) {
                  var11 = false | this.mDoubleTapListener.onDoubleTapEvent(var1);
                  return var11;
               }

               if (!this.mAlwaysInTapRegion) {
                  if (!(Math.abs(var5) >= 1.0F)) {
                     var11 = var12;
                     if (!(Math.abs(var4) >= 1.0F)) {
                        return var11;
                     }
                  }

                  var11 = this.mListener.onScroll(this.mCurrentDownEvent, var1, var5, var4);
                  this.mLastFocusX = var3;
                  this.mLastFocusY = var2;
                  return var11;
               }

               var7 = (int)(var3 - this.mDownFocusX);
               var6 = (int)(var2 - this.mDownFocusY);
               var6 = var7 * var7 + var6 * var6;
               if (var6 > this.mTouchSlopSquare) {
                  var11 = this.mListener.onScroll(this.mCurrentDownEvent, var1, var5, var4);
                  this.mLastFocusX = var3;
                  this.mLastFocusY = var2;
                  this.mAlwaysInTapRegion = false;
                  this.mHandler.removeMessages(3);
                  this.mHandler.removeMessages(1);
                  this.mHandler.removeMessages(2);
               } else {
                  var11 = false;
               }

               var12 = var11;
               if (var6 > this.mTouchSlopSquare) {
                  this.mAlwaysInBiggerTapRegion = false;
                  var12 = var11;
               }
            } else {
               this.mStillDown = false;
               var13 = MotionEvent.obtain(var1);
               if (this.mIsDoubleTapping) {
                  var11 = this.mDoubleTapListener.onDoubleTapEvent(var1) | false;
               } else {
                  label180: {
                     if (this.mInLongPress) {
                        this.mHandler.removeMessages(3);
                        this.mInLongPress = false;
                     } else {
                        if (this.mAlwaysInTapRegion) {
                           var11 = this.mListener.onSingleTapUp(var1);
                           if (this.mDeferConfirmSingleTap) {
                              GestureDetector.OnDoubleTapListener var17 = this.mDoubleTapListener;
                              if (var17 != null) {
                                 var17.onSingleTapConfirmed(var1);
                              }
                           }
                           break label180;
                        }

                        VelocityTracker var14 = this.mVelocityTracker;
                        var6 = var1.getPointerId(0);
                        var14.computeCurrentVelocity(1000, (float)this.mMaximumFlingVelocity);
                        var3 = var14.getYVelocity(var6);
                        var2 = var14.getXVelocity(var6);
                        if (Math.abs(var3) > (float)this.mMinimumFlingVelocity || Math.abs(var2) > (float)this.mMinimumFlingVelocity) {
                           var11 = this.mListener.onFling(this.mCurrentDownEvent, var1, var2, var3);
                           break label180;
                        }
                     }

                     var11 = false;
                  }
               }

               var1 = this.mPreviousUpEvent;
               if (var1 != null) {
                  var1.recycle();
               }

               this.mPreviousUpEvent = var13;
               VelocityTracker var15 = this.mVelocityTracker;
               if (var15 != null) {
                  var15.recycle();
                  this.mVelocityTracker = null;
               }

               this.mIsDoubleTapping = false;
               this.mDeferConfirmSingleTap = false;
               this.mHandler.removeMessages(1);
               this.mHandler.removeMessages(2);
               var12 = var11;
            }

            var11 = var12;
         } else {
            label117: {
               if (this.mDoubleTapListener != null) {
                  var11 = this.mHandler.hasMessages(3);
                  if (var11) {
                     this.mHandler.removeMessages(3);
                  }

                  var13 = this.mCurrentDownEvent;
                  if (var13 != null) {
                     MotionEvent var18 = this.mPreviousUpEvent;
                     if (var18 != null && var11 && this.isConsideredDoubleTap(var13, var18, var1)) {
                        this.mIsDoubleTapping = true;
                        var16 = this.mDoubleTapListener.onDoubleTap(this.mCurrentDownEvent) | false | this.mDoubleTapListener.onDoubleTapEvent(var1);
                        break label117;
                     }
                  }

                  this.mHandler.sendEmptyMessageDelayed(3, (long)DOUBLE_TAP_TIMEOUT);
               }

               var16 = false;
            }

            this.mLastFocusX = var3;
            this.mDownFocusX = var3;
            this.mLastFocusY = var2;
            this.mDownFocusY = var2;
            var13 = this.mCurrentDownEvent;
            if (var13 != null) {
               var13.recycle();
            }

            this.mCurrentDownEvent = MotionEvent.obtain(var1);
            this.mAlwaysInTapRegion = true;
            this.mAlwaysInBiggerTapRegion = true;
            this.mStillDown = true;
            this.mInLongPress = false;
            this.mDeferConfirmSingleTap = false;
            if (this.mIsLongpressEnabled) {
               this.mHandler.removeMessages(2);
               this.mHandler.sendEmptyMessageAtTime(2, this.mCurrentDownEvent.getDownTime() + (long)TAP_TIMEOUT + (long)ViewConfiguration.getLongPressTimeout());
            }

            this.mHandler.sendEmptyMessageAtTime(1, this.mCurrentDownEvent.getDownTime() + (long)TAP_TIMEOUT);
            var11 = var16 | this.mListener.onDown(var1);
         }

         return var11;
      }

      public void setIsLongpressEnabled(boolean var1) {
         this.mIsLongpressEnabled = var1;
      }

      public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener var1) {
         this.mDoubleTapListener = var1;
      }

      private class GestureHandler extends Handler {
         final GestureDetectorCompatImplBase this$0;

         GestureHandler(GestureDetectorCompatImplBase var1) {
            this.this$0 = var1;
         }

         GestureHandler(GestureDetectorCompatImplBase var1, Handler var2) {
            super(var2.getLooper());
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            int var2 = var1.what;
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     throw new RuntimeException("Unknown message " + var1);
                  }

                  if (this.this$0.mDoubleTapListener != null) {
                     if (!this.this$0.mStillDown) {
                        this.this$0.mDoubleTapListener.onSingleTapConfirmed(this.this$0.mCurrentDownEvent);
                     } else {
                        this.this$0.mDeferConfirmSingleTap = true;
                     }
                  }
               } else {
                  this.this$0.dispatchLongPress();
               }
            } else {
               this.this$0.mListener.onShowPress(this.this$0.mCurrentDownEvent);
            }

         }
      }
   }

   static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
      private final GestureDetector mDetector;

      GestureDetectorCompatImplJellybeanMr2(Context var1, GestureDetector.OnGestureListener var2, Handler var3) {
         this.mDetector = new GestureDetector(var1, var2, var3);
      }

      public boolean isLongpressEnabled() {
         return this.mDetector.isLongpressEnabled();
      }

      public boolean onTouchEvent(MotionEvent var1) {
         return this.mDetector.onTouchEvent(var1);
      }

      public void setIsLongpressEnabled(boolean var1) {
         this.mDetector.setIsLongpressEnabled(var1);
      }

      public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener var1) {
         this.mDetector.setOnDoubleTapListener(var1);
      }
   }
}
