package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R;
import java.util.ArrayList;
import java.util.List;

public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
   static final int ACTION_MODE_DRAG_MASK = 16711680;
   private static final int ACTION_MODE_IDLE_MASK = 255;
   static final int ACTION_MODE_SWIPE_MASK = 65280;
   public static final int ACTION_STATE_DRAG = 2;
   public static final int ACTION_STATE_IDLE = 0;
   public static final int ACTION_STATE_SWIPE = 1;
   private static final int ACTIVE_POINTER_ID_NONE = -1;
   public static final int ANIMATION_TYPE_DRAG = 8;
   public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
   public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
   private static final boolean DEBUG = false;
   static final int DIRECTION_FLAG_COUNT = 8;
   public static final int DOWN = 2;
   public static final int END = 32;
   public static final int LEFT = 4;
   private static final int PIXELS_PER_SECOND = 1000;
   public static final int RIGHT = 8;
   public static final int START = 16;
   private static final String TAG = "ItemTouchHelper";
   public static final int UP = 1;
   private int mActionState = 0;
   int mActivePointerId = -1;
   Callback mCallback;
   private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;
   private List mDistances;
   private long mDragScrollStartTimeInMs;
   float mDx;
   float mDy;
   GestureDetectorCompat mGestureDetector;
   float mInitialTouchX;
   float mInitialTouchY;
   private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
   private float mMaxSwipeVelocity;
   private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener(this) {
      final ItemTouchHelper this$0;

      {
         this.this$0 = var1;
      }

      public boolean onInterceptTouchEvent(RecyclerView var1, MotionEvent var2) {
         this.this$0.mGestureDetector.onTouchEvent(var2);
         int var3 = var2.getActionMasked();
         boolean var5 = true;
         if (var3 == 0) {
            this.this$0.mActivePointerId = var2.getPointerId(0);
            this.this$0.mInitialTouchX = var2.getX();
            this.this$0.mInitialTouchY = var2.getY();
            this.this$0.obtainVelocityTracker();
            if (this.this$0.mSelected == null) {
               RecoverAnimation var7 = this.this$0.findAnimation(var2);
               if (var7 != null) {
                  ItemTouchHelper var6 = this.this$0;
                  var6.mInitialTouchX -= var7.mX;
                  var6 = this.this$0;
                  var6.mInitialTouchY -= var7.mY;
                  this.this$0.endRecoverAnimation(var7.mViewHolder, true);
                  if (this.this$0.mPendingCleanup.remove(var7.mViewHolder.itemView)) {
                     this.this$0.mCallback.clearView(this.this$0.mRecyclerView, var7.mViewHolder);
                  }

                  this.this$0.select(var7.mViewHolder, var7.mActionState);
                  ItemTouchHelper var8 = this.this$0;
                  var8.updateDxDy(var2, var8.mSelectedFlags, 0);
               }
            }
         } else if (var3 != 3 && var3 != 1) {
            if (this.this$0.mActivePointerId != -1) {
               int var4 = var2.findPointerIndex(this.this$0.mActivePointerId);
               if (var4 >= 0) {
                  this.this$0.checkSelectForSwipe(var3, var2, var4);
               }
            }
         } else {
            this.this$0.mActivePointerId = -1;
            this.this$0.select((RecyclerView.ViewHolder)null, 0);
         }

         if (this.this$0.mVelocityTracker != null) {
            this.this$0.mVelocityTracker.addMovement(var2);
         }

         if (this.this$0.mSelected == null) {
            var5 = false;
         }

         return var5;
      }

      public void onRequestDisallowInterceptTouchEvent(boolean var1) {
         if (var1) {
            this.this$0.select((RecyclerView.ViewHolder)null, 0);
         }
      }

      public void onTouchEvent(RecyclerView var1, MotionEvent var2) {
         this.this$0.mGestureDetector.onTouchEvent(var2);
         if (this.this$0.mVelocityTracker != null) {
            this.this$0.mVelocityTracker.addMovement(var2);
         }

         if (this.this$0.mActivePointerId != -1) {
            int var4 = var2.getActionMasked();
            int var5 = var2.findPointerIndex(this.this$0.mActivePointerId);
            if (var5 >= 0) {
               this.this$0.checkSelectForSwipe(var4, var2, var5);
            }

            RecyclerView.ViewHolder var6 = this.this$0.mSelected;
            if (var6 != null) {
               byte var3 = 0;
               if (var4 != 1) {
                  ItemTouchHelper var7;
                  if (var4 == 2) {
                     if (var5 >= 0) {
                        var7 = this.this$0;
                        var7.updateDxDy(var2, var7.mSelectedFlags, var5);
                        this.this$0.moveIfNecessary(var6);
                        this.this$0.mRecyclerView.removeCallbacks(this.this$0.mScrollRunnable);
                        this.this$0.mScrollRunnable.run();
                        this.this$0.mRecyclerView.invalidate();
                     }

                     return;
                  }

                  if (var4 != 3) {
                     if (var4 == 6) {
                        var4 = var2.getActionIndex();
                        if (var2.getPointerId(var4) == this.this$0.mActivePointerId) {
                           if (var4 == 0) {
                              var3 = 1;
                           }

                           this.this$0.mActivePointerId = var2.getPointerId(var3);
                           var7 = this.this$0;
                           var7.updateDxDy(var2, var7.mSelectedFlags, var4);
                           return;
                        }
                     }

                     return;
                  }

                  if (this.this$0.mVelocityTracker != null) {
                     this.this$0.mVelocityTracker.clear();
                  }
               }

               this.this$0.select((RecyclerView.ViewHolder)null, 0);
               this.this$0.mActivePointerId = -1;
            }
         }
      }
   };
   View mOverdrawChild = null;
   int mOverdrawChildPosition = -1;
   final List mPendingCleanup = new ArrayList();
   List mRecoverAnimations = new ArrayList();
   RecyclerView mRecyclerView;
   final Runnable mScrollRunnable = new Runnable(this) {
      final ItemTouchHelper this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         if (this.this$0.mSelected != null && this.this$0.scrollIfNecessary()) {
            if (this.this$0.mSelected != null) {
               ItemTouchHelper var1 = this.this$0;
               var1.moveIfNecessary(var1.mSelected);
            }

            this.this$0.mRecyclerView.removeCallbacks(this.this$0.mScrollRunnable);
            ViewCompat.postOnAnimation(this.this$0.mRecyclerView, this);
         }

      }
   };
   RecyclerView.ViewHolder mSelected = null;
   int mSelectedFlags;
   private float mSelectedStartX;
   private float mSelectedStartY;
   private int mSlop;
   private List mSwapTargets;
   private float mSwipeEscapeVelocity;
   private final float[] mTmpPosition = new float[2];
   private Rect mTmpRect;
   VelocityTracker mVelocityTracker;

   public ItemTouchHelper(Callback var1) {
      this.mCallback = var1;
   }

   private void addChildDrawingOrderCallback() {
      if (VERSION.SDK_INT < 21) {
         if (this.mChildDrawingOrderCallback == null) {
            this.mChildDrawingOrderCallback = new RecyclerView.ChildDrawingOrderCallback(this) {
               final ItemTouchHelper this$0;

               {
                  this.this$0 = var1;
               }

               public int onGetChildDrawingOrder(int var1, int var2) {
                  if (this.this$0.mOverdrawChild == null) {
                     return var2;
                  } else {
                     int var4 = this.this$0.mOverdrawChildPosition;
                     int var3 = var4;
                     if (var4 == -1) {
                        var3 = this.this$0.mRecyclerView.indexOfChild(this.this$0.mOverdrawChild);
                        this.this$0.mOverdrawChildPosition = var3;
                     }

                     if (var2 == var1 - 1) {
                        return var3;
                     } else {
                        if (var2 >= var3) {
                           ++var2;
                        }

                        return var2;
                     }
                  }
               }
            };
         }

         this.mRecyclerView.setChildDrawingOrderCallback(this.mChildDrawingOrderCallback);
      }
   }

   private int checkHorizontalSwipe(RecyclerView.ViewHolder var1, int var2) {
      if ((var2 & 12) != 0) {
         float var3 = this.mDx;
         byte var6 = 8;
         byte var5;
         if (var3 > 0.0F) {
            var5 = 8;
         } else {
            var5 = 4;
         }

         VelocityTracker var7 = this.mVelocityTracker;
         float var4;
         if (var7 != null && this.mActivePointerId > -1) {
            var7.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            var4 = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            var3 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            if (!(var4 > 0.0F)) {
               var6 = 4;
            }

            var4 = Math.abs(var4);
            if ((var6 & var2) != 0 && var5 == var6 && var4 >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && var4 > Math.abs(var3)) {
               return var6;
            }
         }

         var3 = (float)this.mRecyclerView.getWidth();
         var4 = this.mCallback.getSwipeThreshold(var1);
         if ((var2 & var5) != 0 && Math.abs(this.mDx) > var3 * var4) {
            return var5;
         }
      }

      return 0;
   }

   private int checkVerticalSwipe(RecyclerView.ViewHolder var1, int var2) {
      if ((var2 & 3) != 0) {
         float var3 = this.mDy;
         byte var6 = 2;
         byte var5;
         if (var3 > 0.0F) {
            var5 = 2;
         } else {
            var5 = 1;
         }

         VelocityTracker var7 = this.mVelocityTracker;
         float var4;
         if (var7 != null && this.mActivePointerId > -1) {
            var7.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            var3 = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            var4 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            if (!(var4 > 0.0F)) {
               var6 = 1;
            }

            var4 = Math.abs(var4);
            if ((var6 & var2) != 0 && var6 == var5 && var4 >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && var4 > Math.abs(var3)) {
               return var6;
            }
         }

         var3 = (float)this.mRecyclerView.getHeight();
         var4 = this.mCallback.getSwipeThreshold(var1);
         if ((var2 & var5) != 0 && Math.abs(this.mDy) > var3 * var4) {
            return var5;
         }
      }

      return 0;
   }

   private void destroyCallbacks() {
      this.mRecyclerView.removeItemDecoration(this);
      this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
      this.mRecyclerView.removeOnChildAttachStateChangeListener(this);

      for(int var1 = this.mRecoverAnimations.size() - 1; var1 >= 0; --var1) {
         RecoverAnimation var2 = (RecoverAnimation)this.mRecoverAnimations.get(0);
         var2.cancel();
         this.mCallback.clearView(this.mRecyclerView, var2.mViewHolder);
      }

      this.mRecoverAnimations.clear();
      this.mOverdrawChild = null;
      this.mOverdrawChildPosition = -1;
      this.releaseVelocityTracker();
      this.stopGestureDetection();
   }

   private List findSwapTargets(RecyclerView.ViewHolder var1) {
      List var14 = this.mSwapTargets;
      if (var14 == null) {
         this.mSwapTargets = new ArrayList();
         this.mDistances = new ArrayList();
      } else {
         var14.clear();
         this.mDistances.clear();
      }

      int var3 = this.mCallback.getBoundingBoxMargin();
      int var6 = Math.round(this.mSelectedStartX + this.mDx) - var3;
      int var5 = Math.round(this.mSelectedStartY + this.mDy) - var3;
      int var2 = var1.itemView.getWidth();
      var3 *= 2;
      int var7 = var2 + var6 + var3;
      int var11 = var1.itemView.getHeight() + var5 + var3;
      int var9 = (var6 + var7) / 2;
      int var10 = (var5 + var11) / 2;
      RecyclerView.LayoutManager var15 = this.mRecyclerView.getLayoutManager();
      int var8 = var15.getChildCount();

      for(var2 = 0; var2 < var8; ++var2) {
         View var17 = var15.getChildAt(var2);
         if (var17 != var1.itemView && var17.getBottom() >= var5 && var17.getTop() <= var11 && var17.getRight() >= var6 && var17.getLeft() <= var7) {
            RecyclerView.ViewHolder var16 = this.mRecyclerView.getChildViewHolder(var17);
            if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, var16)) {
               int var4 = Math.abs(var9 - (var17.getLeft() + var17.getRight()) / 2);
               var3 = Math.abs(var10 - (var17.getTop() + var17.getBottom()) / 2);
               int var13 = var4 * var4 + var3 * var3;
               int var12 = this.mSwapTargets.size();
               var4 = 0;

               for(var3 = 0; var4 < var12 && var13 > (Integer)this.mDistances.get(var4); ++var4) {
                  ++var3;
               }

               this.mSwapTargets.add(var3, var16);
               this.mDistances.add(var3, var13);
            }
         }
      }

      return this.mSwapTargets;
   }

   private RecyclerView.ViewHolder findSwipedView(MotionEvent var1) {
      RecyclerView.LayoutManager var7 = this.mRecyclerView.getLayoutManager();
      int var6 = this.mActivePointerId;
      if (var6 == -1) {
         return null;
      } else {
         var6 = var1.findPointerIndex(var6);
         float var5 = var1.getX(var6);
         float var4 = this.mInitialTouchX;
         float var2 = var1.getY(var6);
         float var3 = this.mInitialTouchY;
         var4 = Math.abs(var5 - var4);
         var2 = Math.abs(var2 - var3);
         var6 = this.mSlop;
         if (var4 < (float)var6 && var2 < (float)var6) {
            return null;
         } else if (var4 > var2 && var7.canScrollHorizontally()) {
            return null;
         } else if (var2 > var4 && var7.canScrollVertically()) {
            return null;
         } else {
            View var8 = this.findChildView(var1);
            return var8 == null ? null : this.mRecyclerView.getChildViewHolder(var8);
         }
      }
   }

   private void getSelectedDxDy(float[] var1) {
      if ((this.mSelectedFlags & 12) != 0) {
         var1[0] = this.mSelectedStartX + this.mDx - (float)this.mSelected.itemView.getLeft();
      } else {
         var1[0] = this.mSelected.itemView.getTranslationX();
      }

      if ((this.mSelectedFlags & 3) != 0) {
         var1[1] = this.mSelectedStartY + this.mDy - (float)this.mSelected.itemView.getTop();
      } else {
         var1[1] = this.mSelected.itemView.getTranslationY();
      }

   }

   private static boolean hitTest(View var0, float var1, float var2, float var3, float var4) {
      boolean var5;
      if (var1 >= var3 && var1 <= var3 + (float)var0.getWidth() && var2 >= var4 && var2 <= var4 + (float)var0.getHeight()) {
         var5 = true;
      } else {
         var5 = false;
      }

      return var5;
   }

   private void releaseVelocityTracker() {
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 != null) {
         var1.recycle();
         this.mVelocityTracker = null;
      }

   }

   private void setupCallbacks() {
      this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
      this.mRecyclerView.addItemDecoration(this);
      this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
      this.mRecyclerView.addOnChildAttachStateChangeListener(this);
      this.startGestureDetection();
   }

   private void startGestureDetection() {
      this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener(this);
      this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
   }

   private void stopGestureDetection() {
      ItemTouchHelperGestureListener var1 = this.mItemTouchHelperGestureListener;
      if (var1 != null) {
         var1.doNotReactToLongPress();
         this.mItemTouchHelperGestureListener = null;
      }

      if (this.mGestureDetector != null) {
         this.mGestureDetector = null;
      }

   }

   private int swipeIfNecessary(RecyclerView.ViewHolder var1) {
      if (this.mActionState == 2) {
         return 0;
      } else {
         int var3 = this.mCallback.getMovementFlags(this.mRecyclerView, var1);
         int var2 = (this.mCallback.convertToAbsoluteDirection(var3, ViewCompat.getLayoutDirection(this.mRecyclerView)) & '\uff00') >> 8;
         if (var2 == 0) {
            return 0;
         } else {
            int var4 = (var3 & '\uff00') >> 8;
            if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
               var3 = this.checkHorizontalSwipe(var1, var2);
               if (var3 > 0) {
                  if ((var4 & var3) == 0) {
                     return ItemTouchHelper.Callback.convertToRelativeDirection(var3, ViewCompat.getLayoutDirection(this.mRecyclerView));
                  }

                  return var3;
               }

               var2 = this.checkVerticalSwipe(var1, var2);
               if (var2 > 0) {
                  return var2;
               }
            } else {
               var3 = this.checkVerticalSwipe(var1, var2);
               if (var3 > 0) {
                  return var3;
               }

               var3 = this.checkHorizontalSwipe(var1, var2);
               if (var3 > 0) {
                  var2 = var3;
                  if ((var4 & var3) == 0) {
                     var2 = ItemTouchHelper.Callback.convertToRelativeDirection(var3, ViewCompat.getLayoutDirection(this.mRecyclerView));
                  }

                  return var2;
               }
            }

            return 0;
         }
      }
   }

   public void attachToRecyclerView(RecyclerView var1) {
      RecyclerView var2 = this.mRecyclerView;
      if (var2 != var1) {
         if (var2 != null) {
            this.destroyCallbacks();
         }

         this.mRecyclerView = var1;
         if (var1 != null) {
            Resources var3 = var1.getResources();
            this.mSwipeEscapeVelocity = var3.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
            this.mMaxSwipeVelocity = var3.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
            this.setupCallbacks();
         }

      }
   }

   void checkSelectForSwipe(int var1, MotionEvent var2, int var3) {
      if (this.mSelected == null && var1 == 2 && this.mActionState != 2 && this.mCallback.isItemViewSwipeEnabled()) {
         if (this.mRecyclerView.getScrollState() == 1) {
            return;
         }

         RecyclerView.ViewHolder var8 = this.findSwipedView(var2);
         if (var8 == null) {
            return;
         }

         var1 = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, var8) & '\uff00') >> 8;
         if (var1 == 0) {
            return;
         }

         float var4 = var2.getX(var3);
         float var5 = var2.getY(var3);
         var4 -= this.mInitialTouchX;
         float var6 = var5 - this.mInitialTouchY;
         float var7 = Math.abs(var4);
         var5 = Math.abs(var6);
         var3 = this.mSlop;
         if (var7 < (float)var3 && var5 < (float)var3) {
            return;
         }

         if (var7 > var5) {
            if (var4 < 0.0F && (var1 & 4) == 0) {
               return;
            }

            if (var4 > 0.0F && (var1 & 8) == 0) {
               return;
            }
         } else {
            if (var6 < 0.0F && (var1 & 1) == 0) {
               return;
            }

            if (var6 > 0.0F && (var1 & 2) == 0) {
               return;
            }
         }

         this.mDy = 0.0F;
         this.mDx = 0.0F;
         this.mActivePointerId = var2.getPointerId(0);
         this.select(var8, 1);
      }

   }

   void endRecoverAnimation(RecyclerView.ViewHolder var1, boolean var2) {
      for(int var3 = this.mRecoverAnimations.size() - 1; var3 >= 0; --var3) {
         RecoverAnimation var4 = (RecoverAnimation)this.mRecoverAnimations.get(var3);
         if (var4.mViewHolder == var1) {
            var4.mOverridden |= var2;
            if (!var4.mEnded) {
               var4.cancel();
            }

            this.mRecoverAnimations.remove(var3);
            return;
         }
      }

   }

   RecoverAnimation findAnimation(MotionEvent var1) {
      if (this.mRecoverAnimations.isEmpty()) {
         return null;
      } else {
         View var3 = this.findChildView(var1);

         for(int var2 = this.mRecoverAnimations.size() - 1; var2 >= 0; --var2) {
            RecoverAnimation var4 = (RecoverAnimation)this.mRecoverAnimations.get(var2);
            if (var4.mViewHolder.itemView == var3) {
               return var4;
            }
         }

         return null;
      }
   }

   View findChildView(MotionEvent var1) {
      float var2 = var1.getX();
      float var3 = var1.getY();
      RecyclerView.ViewHolder var6 = this.mSelected;
      if (var6 != null) {
         View var7 = var6.itemView;
         if (hitTest(var7, var2, var3, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
            return var7;
         }
      }

      for(int var4 = this.mRecoverAnimations.size() - 1; var4 >= 0; --var4) {
         RecoverAnimation var8 = (RecoverAnimation)this.mRecoverAnimations.get(var4);
         View var5 = var8.mViewHolder.itemView;
         if (hitTest(var5, var2, var3, var8.mX, var8.mY)) {
            return var5;
         }
      }

      return this.mRecyclerView.findChildViewUnder(var2, var3);
   }

   public void getItemOffsets(Rect var1, View var2, RecyclerView var3, RecyclerView.State var4) {
      var1.setEmpty();
   }

   boolean hasRunningRecoverAnim() {
      int var2 = this.mRecoverAnimations.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         if (!((RecoverAnimation)this.mRecoverAnimations.get(var1)).mEnded) {
            return true;
         }
      }

      return false;
   }

   void moveIfNecessary(RecyclerView.ViewHolder var1) {
      if (!this.mRecyclerView.isLayoutRequested()) {
         if (this.mActionState == 2) {
            float var2 = this.mCallback.getMoveThreshold(var1);
            int var5 = (int)(this.mSelectedStartX + this.mDx);
            int var4 = (int)(this.mSelectedStartY + this.mDy);
            if (!((float)Math.abs(var4 - var1.itemView.getTop()) < (float)var1.itemView.getHeight() * var2) || !((float)Math.abs(var5 - var1.itemView.getLeft()) < (float)var1.itemView.getWidth() * var2)) {
               List var7 = this.findSwapTargets(var1);
               if (var7.size() != 0) {
                  RecyclerView.ViewHolder var8 = this.mCallback.chooseDropTarget(var1, var7, var5, var4);
                  if (var8 == null) {
                     this.mSwapTargets.clear();
                     this.mDistances.clear();
                  } else {
                     int var3 = var8.getAbsoluteAdapterPosition();
                     int var6 = var1.getAbsoluteAdapterPosition();
                     if (this.mCallback.onMove(this.mRecyclerView, var1, var8)) {
                        this.mCallback.onMoved(this.mRecyclerView, var1, var6, var8, var3, var5, var4);
                     }

                  }
               }
            }
         }
      }
   }

   void obtainVelocityTracker() {
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 != null) {
         var1.recycle();
      }

      this.mVelocityTracker = VelocityTracker.obtain();
   }

   public void onChildViewAttachedToWindow(View var1) {
   }

   public void onChildViewDetachedFromWindow(View var1) {
      this.removeChildDrawingOrderCallbackIfNecessary(var1);
      RecyclerView.ViewHolder var3 = this.mRecyclerView.getChildViewHolder(var1);
      if (var3 != null) {
         RecyclerView.ViewHolder var2 = this.mSelected;
         if (var2 != null && var3 == var2) {
            this.select((RecyclerView.ViewHolder)null, 0);
         } else {
            this.endRecoverAnimation(var3, false);
            if (this.mPendingCleanup.remove(var3.itemView)) {
               this.mCallback.clearView(this.mRecyclerView, var3);
            }
         }

      }
   }

   public void onDraw(Canvas var1, RecyclerView var2, RecyclerView.State var3) {
      this.mOverdrawChildPosition = -1;
      float var4;
      float var5;
      if (this.mSelected != null) {
         this.getSelectedDxDy(this.mTmpPosition);
         float[] var6 = this.mTmpPosition;
         var5 = var6[0];
         var4 = var6[1];
      } else {
         var5 = 0.0F;
         var4 = 0.0F;
      }

      this.mCallback.onDraw(var1, var2, this.mSelected, this.mRecoverAnimations, this.mActionState, var5, var4);
   }

   public void onDrawOver(Canvas var1, RecyclerView var2, RecyclerView.State var3) {
      float var4;
      float var5;
      if (this.mSelected != null) {
         this.getSelectedDxDy(this.mTmpPosition);
         float[] var6 = this.mTmpPosition;
         var5 = var6[0];
         var4 = var6[1];
      } else {
         var5 = 0.0F;
         var4 = 0.0F;
      }

      this.mCallback.onDrawOver(var1, var2, this.mSelected, this.mRecoverAnimations, this.mActionState, var5, var4);
   }

   void postDispatchSwipe(RecoverAnimation var1, int var2) {
      this.mRecyclerView.post(new Runnable(this, var1, var2) {
         final ItemTouchHelper this$0;
         final RecoverAnimation val$anim;
         final int val$swipeDir;

         {
            this.this$0 = var1;
            this.val$anim = var2;
            this.val$swipeDir = var3;
         }

         public void run() {
            if (this.this$0.mRecyclerView != null && this.this$0.mRecyclerView.isAttachedToWindow() && !this.val$anim.mOverridden && this.val$anim.mViewHolder.getAbsoluteAdapterPosition() != -1) {
               RecyclerView.ItemAnimator var1 = this.this$0.mRecyclerView.getItemAnimator();
               if ((var1 == null || !var1.isRunning((RecyclerView.ItemAnimator.ItemAnimatorFinishedListener)null)) && !this.this$0.hasRunningRecoverAnim()) {
                  this.this$0.mCallback.onSwiped(this.val$anim.mViewHolder, this.val$swipeDir);
               } else {
                  this.this$0.mRecyclerView.post(this);
               }
            }

         }
      });
   }

   void removeChildDrawingOrderCallbackIfNecessary(View var1) {
      if (var1 == this.mOverdrawChild) {
         this.mOverdrawChild = null;
         if (this.mChildDrawingOrderCallback != null) {
            this.mRecyclerView.setChildDrawingOrderCallback((RecyclerView.ChildDrawingOrderCallback)null);
         }
      }

   }

   boolean scrollIfNecessary() {
      if (this.mSelected == null) {
         this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
         return false;
      } else {
         long var7 = System.currentTimeMillis();
         long var5 = this.mDragScrollStartTimeInMs;
         if (var5 == Long.MIN_VALUE) {
            var5 = 0L;
         } else {
            var5 = var7 - var5;
         }

         RecyclerView.LayoutManager var9 = this.mRecyclerView.getLayoutManager();
         if (this.mTmpRect == null) {
            this.mTmpRect = new Rect();
         }

         float var1;
         int var2;
         int var3;
         label66: {
            var9.calculateItemDecorationsForChild(this.mSelected.itemView, this.mTmpRect);
            if (var9.canScrollHorizontally()) {
               var3 = (int)(this.mSelectedStartX + this.mDx);
               var2 = var3 - this.mTmpRect.left - this.mRecyclerView.getPaddingLeft();
               var1 = this.mDx;
               if (var1 < 0.0F && var2 < 0) {
                  break label66;
               }

               if (var1 > 0.0F) {
                  var2 = var3 + this.mSelected.itemView.getWidth() + this.mTmpRect.right - (this.mRecyclerView.getWidth() - this.mRecyclerView.getPaddingRight());
                  if (var2 > 0) {
                     break label66;
                  }
               }
            }

            var2 = 0;
         }

         int var4;
         label57: {
            if (var9.canScrollVertically()) {
               var4 = (int)(this.mSelectedStartY + this.mDy);
               var3 = var4 - this.mTmpRect.top - this.mRecyclerView.getPaddingTop();
               var1 = this.mDy;
               if (var1 < 0.0F && var3 < 0) {
                  break label57;
               }

               if (var1 > 0.0F) {
                  var3 = var4 + this.mSelected.itemView.getHeight() + this.mTmpRect.bottom - (this.mRecyclerView.getHeight() - this.mRecyclerView.getPaddingBottom());
                  if (var3 > 0) {
                     break label57;
                  }
               }
            }

            var3 = 0;
         }

         var4 = var2;
         if (var2 != 0) {
            var4 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getWidth(), var2, this.mRecyclerView.getWidth(), var5);
         }

         if (var3 != 0) {
            var3 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getHeight(), var3, this.mRecyclerView.getHeight(), var5);
         }

         if (var4 == 0 && var3 == 0) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            return false;
         } else {
            if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
               this.mDragScrollStartTimeInMs = var7;
            }

            this.mRecyclerView.scrollBy(var4, var3);
            return true;
         }
      }
   }

   void select(RecyclerView.ViewHolder var1, int var2) {
      if (var1 != this.mSelected || var2 != this.mActionState) {
         this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
         int var9 = this.mActionState;
         this.endRecoverAnimation(var1, true);
         this.mActionState = var2;
         if (var2 == 2) {
            if (var1 == null) {
               throw new IllegalArgumentException("Must pass a ViewHolder when dragging");
            }

            this.mOverdrawChild = var1.itemView;
            this.addChildDrawingOrderCallback();
         }

         RecyclerView.ViewHolder var11 = this.mSelected;
         boolean var7;
         if (var11 != null) {
            if (var11.itemView.getParent() != null) {
               int var8;
               if (var9 == 2) {
                  var8 = 0;
               } else {
                  var8 = this.swipeIfNecessary(var11);
               }

               this.releaseVelocityTracker();
               float var3;
               float var4;
               float var5;
               if (var8 != 1 && var8 != 2) {
                  if (var8 != 4 && var8 != 8 && var8 != 16 && var8 != 32) {
                     var3 = 0.0F;
                     var4 = 0.0F;
                  } else {
                     var5 = Math.signum(this.mDx);
                     var3 = (float)this.mRecyclerView.getWidth();
                     var4 = 0.0F;
                     var3 = var5 * var3;
                  }
               } else {
                  var4 = Math.signum(this.mDy);
                  var5 = (float)this.mRecyclerView.getHeight();
                  var3 = 0.0F;
                  var4 *= var5;
               }

               byte var14;
               if (var9 == 2) {
                  var14 = 8;
               } else if (var8 > 0) {
                  var14 = 2;
               } else {
                  var14 = 4;
               }

               this.getSelectedDxDy(this.mTmpPosition);
               float[] var12 = this.mTmpPosition;
               float var6 = var12[0];
               var5 = var12[1];
               RecoverAnimation var15 = new RecoverAnimation(this, var11, var14, var9, var6, var5, var3, var4, var8, var11) {
                  final ItemTouchHelper this$0;
                  final RecyclerView.ViewHolder val$prevSelected;
                  final int val$swipeDir;

                  {
                     this.this$0 = var1;
                     this.val$swipeDir = var9;
                     this.val$prevSelected = var10;
                  }

                  public void onAnimationEnd(Animator var1) {
                     super.onAnimationEnd(var1);
                     if (!this.mOverridden) {
                        if (this.val$swipeDir <= 0) {
                           this.this$0.mCallback.clearView(this.this$0.mRecyclerView, this.val$prevSelected);
                        } else {
                           this.this$0.mPendingCleanup.add(this.val$prevSelected.itemView);
                           this.mIsPendingCleanup = true;
                           int var2 = this.val$swipeDir;
                           if (var2 > 0) {
                              this.this$0.postDispatchSwipe(this, var2);
                           }
                        }

                        if (this.this$0.mOverdrawChild == this.val$prevSelected.itemView) {
                           this.this$0.removeChildDrawingOrderCallbackIfNecessary(this.val$prevSelected.itemView);
                        }

                     }
                  }
               };
               var15.setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, var14, var3 - var6, var4 - var5));
               this.mRecoverAnimations.add(var15);
               var15.start();
               var7 = true;
            } else {
               this.removeChildDrawingOrderCallbackIfNecessary(var11.itemView);
               this.mCallback.clearView(this.mRecyclerView, var11);
               var7 = false;
            }

            this.mSelected = null;
         } else {
            var7 = false;
         }

         if (var1 != null) {
            this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, var1) & (1 << var2 * 8 + 8) - 1) >> this.mActionState * 8;
            this.mSelectedStartX = (float)var1.itemView.getLeft();
            this.mSelectedStartY = (float)var1.itemView.getTop();
            this.mSelected = var1;
            if (var2 == 2) {
               var1.itemView.performHapticFeedback(0);
            }
         }

         ViewParent var13 = this.mRecyclerView.getParent();
         if (var13 != null) {
            boolean var10;
            if (this.mSelected != null) {
               var10 = true;
            } else {
               var10 = false;
            }

            var13.requestDisallowInterceptTouchEvent(var10);
         }

         if (!var7) {
            this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
         }

         this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
         this.mRecyclerView.invalidate();
      }
   }

   public void startDrag(RecyclerView.ViewHolder var1) {
      if (!this.mCallback.hasDragFlag(this.mRecyclerView, var1)) {
         Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
      } else if (var1.itemView.getParent() != this.mRecyclerView) {
         Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
      } else {
         this.obtainVelocityTracker();
         this.mDy = 0.0F;
         this.mDx = 0.0F;
         this.select(var1, 2);
      }
   }

   public void startSwipe(RecyclerView.ViewHolder var1) {
      if (!this.mCallback.hasSwipeFlag(this.mRecyclerView, var1)) {
         Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
      } else if (var1.itemView.getParent() != this.mRecyclerView) {
         Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
      } else {
         this.obtainVelocityTracker();
         this.mDy = 0.0F;
         this.mDx = 0.0F;
         this.select(var1, 1);
      }
   }

   void updateDxDy(MotionEvent var1, int var2, int var3) {
      float var5 = var1.getX(var3);
      float var4 = var1.getY(var3);
      var5 -= this.mInitialTouchX;
      this.mDx = var5;
      this.mDy = var4 - this.mInitialTouchY;
      if ((var2 & 4) == 0) {
         this.mDx = Math.max(0.0F, var5);
      }

      if ((var2 & 8) == 0) {
         this.mDx = Math.min(0.0F, this.mDx);
      }

      if ((var2 & 1) == 0) {
         this.mDy = Math.max(0.0F, this.mDy);
      }

      if ((var2 & 2) == 0) {
         this.mDy = Math.min(0.0F, this.mDy);
      }

   }

   public abstract static class Callback {
      private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
      public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
      public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
      private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
      static final int RELATIVE_DIR_FLAGS = 3158064;
      private static final Interpolator sDragScrollInterpolator = new Interpolator() {
         public float getInterpolation(float var1) {
            return var1 * var1 * var1 * var1 * var1;
         }
      };
      private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() {
         public float getInterpolation(float var1) {
            --var1;
            return var1 * var1 * var1 * var1 * var1 + 1.0F;
         }
      };
      private int mCachedMaxScrollSpeed = -1;

      public static int convertToRelativeDirection(int var0, int var1) {
         int var2 = var0 & 789516;
         if (var2 == 0) {
            return var0;
         } else {
            var0 &= ~var2;
            if (var1 == 0) {
               var1 = var2 << 2;
            } else {
               var1 = var2 << 1;
               var0 |= -789517 & var1;
               var1 = (var1 & 789516) << 2;
            }

            return var0 | var1;
         }
      }

      public static ItemTouchUIUtil getDefaultUIUtil() {
         return ItemTouchUIUtilImpl.INSTANCE;
      }

      private int getMaxDragScroll(RecyclerView var1) {
         if (this.mCachedMaxScrollSpeed == -1) {
            this.mCachedMaxScrollSpeed = var1.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
         }

         return this.mCachedMaxScrollSpeed;
      }

      public static int makeFlag(int var0, int var1) {
         return var1 << var0 * 8;
      }

      public static int makeMovementFlags(int var0, int var1) {
         int var2 = makeFlag(0, var1 | var0);
         var1 = makeFlag(1, var1);
         return makeFlag(2, var0) | var1 | var2;
      }

      public boolean canDropOver(RecyclerView var1, RecyclerView.ViewHolder var2, RecyclerView.ViewHolder var3) {
         return true;
      }

      public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder var1, List var2, int var3, int var4) {
         int var11 = var1.itemView.getWidth();
         int var12 = var1.itemView.getHeight();
         int var9 = var3 - var1.itemView.getLeft();
         int var10 = var4 - var1.itemView.getTop();
         int var13 = var2.size();
         RecyclerView.ViewHolder var16 = null;
         int var6 = -1;

         for(int var7 = 0; var7 < var13; ++var7) {
            RecyclerView.ViewHolder var14 = (RecyclerView.ViewHolder)var2.get(var7);
            RecyclerView.ViewHolder var15 = var16;
            int var5 = var6;
            int var8;
            if (var9 > 0) {
               var8 = var14.itemView.getRight() - (var3 + var11);
               var15 = var16;
               var5 = var6;
               if (var8 < 0) {
                  var15 = var16;
                  var5 = var6;
                  if (var14.itemView.getRight() > var1.itemView.getRight()) {
                     var8 = Math.abs(var8);
                     var15 = var16;
                     var5 = var6;
                     if (var8 > var6) {
                        var15 = var14;
                        var5 = var8;
                     }
                  }
               }
            }

            var16 = var15;
            var6 = var5;
            if (var9 < 0) {
               var8 = var14.itemView.getLeft() - var3;
               var16 = var15;
               var6 = var5;
               if (var8 > 0) {
                  var16 = var15;
                  var6 = var5;
                  if (var14.itemView.getLeft() < var1.itemView.getLeft()) {
                     var8 = Math.abs(var8);
                     var16 = var15;
                     var6 = var5;
                     if (var8 > var5) {
                        var16 = var14;
                        var6 = var8;
                     }
                  }
               }
            }

            var15 = var16;
            var5 = var6;
            if (var10 < 0) {
               var8 = var14.itemView.getTop() - var4;
               var15 = var16;
               var5 = var6;
               if (var8 > 0) {
                  var15 = var16;
                  var5 = var6;
                  if (var14.itemView.getTop() < var1.itemView.getTop()) {
                     var8 = Math.abs(var8);
                     var15 = var16;
                     var5 = var6;
                     if (var8 > var6) {
                        var15 = var14;
                        var5 = var8;
                     }
                  }
               }
            }

            var16 = var15;
            var6 = var5;
            if (var10 > 0) {
               var8 = var14.itemView.getBottom() - (var4 + var12);
               var16 = var15;
               var6 = var5;
               if (var8 < 0) {
                  var16 = var15;
                  var6 = var5;
                  if (var14.itemView.getBottom() > var1.itemView.getBottom()) {
                     var8 = Math.abs(var8);
                     var16 = var15;
                     var6 = var5;
                     if (var8 > var5) {
                        var6 = var8;
                        var16 = var14;
                     }
                  }
               }
            }
         }

         return var16;
      }

      public void clearView(RecyclerView var1, RecyclerView.ViewHolder var2) {
         ItemTouchUIUtilImpl.INSTANCE.clearView(var2.itemView);
      }

      public int convertToAbsoluteDirection(int var1, int var2) {
         int var4 = var1 & 3158064;
         if (var4 == 0) {
            return var1;
         } else {
            int var3 = var1 & ~var4;
            if (var2 == 0) {
               var1 = var4 >> 2;
               var2 = var3;
            } else {
               var1 = var4 >> 1;
               var2 = var3 | -3158065 & var1;
               var1 = (var1 & 3158064) >> 2;
            }

            return var2 | var1;
         }
      }

      final int getAbsoluteMovementFlags(RecyclerView var1, RecyclerView.ViewHolder var2) {
         return this.convertToAbsoluteDirection(this.getMovementFlags(var1, var2), ViewCompat.getLayoutDirection(var1));
      }

      public long getAnimationDuration(RecyclerView var1, int var2, float var3, float var4) {
         RecyclerView.ItemAnimator var7 = var1.getItemAnimator();
         long var5;
         if (var7 == null) {
            if (var2 == 8) {
               var5 = 200L;
            } else {
               var5 = 250L;
            }

            return var5;
         } else {
            if (var2 == 8) {
               var5 = var7.getMoveDuration();
            } else {
               var5 = var7.getRemoveDuration();
            }

            return var5;
         }
      }

      public int getBoundingBoxMargin() {
         return 0;
      }

      public float getMoveThreshold(RecyclerView.ViewHolder var1) {
         return 0.5F;
      }

      public abstract int getMovementFlags(RecyclerView var1, RecyclerView.ViewHolder var2);

      public float getSwipeEscapeVelocity(float var1) {
         return var1;
      }

      public float getSwipeThreshold(RecyclerView.ViewHolder var1) {
         return 0.5F;
      }

      public float getSwipeVelocityThreshold(float var1) {
         return var1;
      }

      boolean hasDragFlag(RecyclerView var1, RecyclerView.ViewHolder var2) {
         boolean var3;
         if ((this.getAbsoluteMovementFlags(var1, var2) & 16711680) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      boolean hasSwipeFlag(RecyclerView var1, RecyclerView.ViewHolder var2) {
         boolean var3;
         if ((this.getAbsoluteMovementFlags(var1, var2) & '\uff00') != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      public int interpolateOutOfBoundsScroll(RecyclerView var1, int var2, int var3, int var4, long var5) {
         var4 = this.getMaxDragScroll(var1);
         int var9 = Math.abs(var3);
         int var10 = (int)Math.signum((float)var3);
         float var8 = (float)var9;
         float var7 = 1.0F;
         var8 = Math.min(1.0F, var8 * 1.0F / (float)var2);
         var2 = (int)((float)(var10 * var4) * sDragViewScrollCapInterpolator.getInterpolation(var8));
         if (var5 <= 2000L) {
            var7 = (float)var5 / 2000.0F;
         }

         var4 = (int)((float)var2 * sDragScrollInterpolator.getInterpolation(var7));
         var2 = var4;
         if (var4 == 0) {
            if (var3 > 0) {
               var2 = 1;
            } else {
               var2 = -1;
            }
         }

         return var2;
      }

      public boolean isItemViewSwipeEnabled() {
         return true;
      }

      public boolean isLongPressDragEnabled() {
         return true;
      }

      public void onChildDraw(Canvas var1, RecyclerView var2, RecyclerView.ViewHolder var3, float var4, float var5, int var6, boolean var7) {
         ItemTouchUIUtilImpl.INSTANCE.onDraw(var1, var2, var3.itemView, var4, var5, var6, var7);
      }

      public void onChildDrawOver(Canvas var1, RecyclerView var2, RecyclerView.ViewHolder var3, float var4, float var5, int var6, boolean var7) {
         ItemTouchUIUtilImpl.INSTANCE.onDrawOver(var1, var2, var3.itemView, var4, var5, var6, var7);
      }

      void onDraw(Canvas var1, RecyclerView var2, RecyclerView.ViewHolder var3, List var4, int var5, float var6, float var7) {
         int var9 = var4.size();

         int var8;
         for(var8 = 0; var8 < var9; ++var8) {
            RecoverAnimation var11 = (RecoverAnimation)var4.get(var8);
            var11.update();
            int var10 = var1.save();
            this.onChildDraw(var1, var2, var11.mViewHolder, var11.mX, var11.mY, var11.mActionState, false);
            var1.restoreToCount(var10);
         }

         if (var3 != null) {
            var8 = var1.save();
            this.onChildDraw(var1, var2, var3, var6, var7, var5, true);
            var1.restoreToCount(var8);
         }

      }

      void onDrawOver(Canvas var1, RecyclerView var2, RecyclerView.ViewHolder var3, List var4, int var5, float var6, float var7) {
         int var10 = var4.size();
         boolean var9 = false;

         int var8;
         for(var8 = 0; var8 < var10; ++var8) {
            RecoverAnimation var12 = (RecoverAnimation)var4.get(var8);
            int var11 = var1.save();
            this.onChildDrawOver(var1, var2, var12.mViewHolder, var12.mX, var12.mY, var12.mActionState, false);
            var1.restoreToCount(var11);
         }

         if (var3 != null) {
            var8 = var1.save();
            this.onChildDrawOver(var1, var2, var3, var6, var7, var5, true);
            var1.restoreToCount(var8);
         }

         var5 = var10 - 1;

         boolean var14;
         for(var14 = var9; var5 >= 0; --var5) {
            RecoverAnimation var13 = (RecoverAnimation)var4.get(var5);
            if (var13.mEnded && !var13.mIsPendingCleanup) {
               var4.remove(var5);
            } else if (!var13.mEnded) {
               var14 = true;
            }
         }

         if (var14) {
            var2.invalidate();
         }

      }

      public abstract boolean onMove(RecyclerView var1, RecyclerView.ViewHolder var2, RecyclerView.ViewHolder var3);

      public void onMoved(RecyclerView var1, RecyclerView.ViewHolder var2, int var3, RecyclerView.ViewHolder var4, int var5, int var6, int var7) {
         RecyclerView.LayoutManager var8 = var1.getLayoutManager();
         if (var8 instanceof ViewDropHandler) {
            ((ViewDropHandler)var8).prepareForDrop(var2.itemView, var4.itemView, var6, var7);
         } else {
            if (var8.canScrollHorizontally()) {
               if (var8.getDecoratedLeft(var4.itemView) <= var1.getPaddingLeft()) {
                  var1.scrollToPosition(var5);
               }

               if (var8.getDecoratedRight(var4.itemView) >= var1.getWidth() - var1.getPaddingRight()) {
                  var1.scrollToPosition(var5);
               }
            }

            if (var8.canScrollVertically()) {
               if (var8.getDecoratedTop(var4.itemView) <= var1.getPaddingTop()) {
                  var1.scrollToPosition(var5);
               }

               if (var8.getDecoratedBottom(var4.itemView) >= var1.getHeight() - var1.getPaddingBottom()) {
                  var1.scrollToPosition(var5);
               }
            }

         }
      }

      public void onSelectedChanged(RecyclerView.ViewHolder var1, int var2) {
         if (var1 != null) {
            ItemTouchUIUtilImpl.INSTANCE.onSelected(var1.itemView);
         }

      }

      public abstract void onSwiped(RecyclerView.ViewHolder var1, int var2);
   }

   private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
      private boolean mShouldReactToLongPress;
      final ItemTouchHelper this$0;

      ItemTouchHelperGestureListener(ItemTouchHelper var1) {
         this.this$0 = var1;
         this.mShouldReactToLongPress = true;
      }

      void doNotReactToLongPress() {
         this.mShouldReactToLongPress = false;
      }

      public boolean onDown(MotionEvent var1) {
         return true;
      }

      public void onLongPress(MotionEvent var1) {
         if (this.mShouldReactToLongPress) {
            View var5 = this.this$0.findChildView(var1);
            if (var5 != null) {
               RecyclerView.ViewHolder var7 = this.this$0.mRecyclerView.getChildViewHolder(var5);
               if (var7 != null) {
                  if (!this.this$0.mCallback.hasDragFlag(this.this$0.mRecyclerView, var7)) {
                     return;
                  }

                  if (var1.getPointerId(0) == this.this$0.mActivePointerId) {
                     int var4 = var1.findPointerIndex(this.this$0.mActivePointerId);
                     float var2 = var1.getX(var4);
                     float var3 = var1.getY(var4);
                     this.this$0.mInitialTouchX = var2;
                     this.this$0.mInitialTouchY = var3;
                     ItemTouchHelper var6 = this.this$0;
                     var6.mDy = 0.0F;
                     var6.mDx = 0.0F;
                     if (this.this$0.mCallback.isLongPressDragEnabled()) {
                        this.this$0.select(var7, 2);
                     }
                  }
               }
            }

         }
      }
   }

   static class RecoverAnimation implements Animator.AnimatorListener {
      final int mActionState;
      final int mAnimationType;
      boolean mEnded = false;
      private float mFraction;
      boolean mIsPendingCleanup;
      boolean mOverridden = false;
      final float mStartDx;
      final float mStartDy;
      final float mTargetX;
      final float mTargetY;
      final ValueAnimator mValueAnimator;
      final RecyclerView.ViewHolder mViewHolder;
      float mX;
      float mY;

      RecoverAnimation(RecyclerView.ViewHolder var1, int var2, int var3, float var4, float var5, float var6, float var7) {
         this.mActionState = var3;
         this.mAnimationType = var2;
         this.mViewHolder = var1;
         this.mStartDx = var4;
         this.mStartDy = var5;
         this.mTargetX = var6;
         this.mTargetY = var7;
         ValueAnimator var8 = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});
         this.mValueAnimator = var8;
         var8.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            final RecoverAnimation this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationUpdate(ValueAnimator var1) {
               this.this$0.setFraction(var1.getAnimatedFraction());
            }
         });
         var8.setTarget(var1.itemView);
         var8.addListener(this);
         this.setFraction(0.0F);
      }

      public void cancel() {
         this.mValueAnimator.cancel();
      }

      public void onAnimationCancel(Animator var1) {
         this.setFraction(1.0F);
      }

      public void onAnimationEnd(Animator var1) {
         if (!this.mEnded) {
            this.mViewHolder.setIsRecyclable(true);
         }

         this.mEnded = true;
      }

      public void onAnimationRepeat(Animator var1) {
      }

      public void onAnimationStart(Animator var1) {
      }

      public void setDuration(long var1) {
         this.mValueAnimator.setDuration(var1);
      }

      public void setFraction(float var1) {
         this.mFraction = var1;
      }

      public void start() {
         this.mViewHolder.setIsRecyclable(false);
         this.mValueAnimator.start();
      }

      public void update() {
         float var1 = this.mStartDx;
         float var2 = this.mTargetX;
         if (var1 == var2) {
            this.mX = this.mViewHolder.itemView.getTranslationX();
         } else {
            this.mX = var1 + this.mFraction * (var2 - var1);
         }

         var2 = this.mStartDy;
         var1 = this.mTargetY;
         if (var2 == var1) {
            this.mY = this.mViewHolder.itemView.getTranslationY();
         } else {
            this.mY = var2 + this.mFraction * (var1 - var2);
         }

      }
   }

   public abstract static class SimpleCallback extends Callback {
      private int mDefaultDragDirs;
      private int mDefaultSwipeDirs;

      public SimpleCallback(int var1, int var2) {
         this.mDefaultSwipeDirs = var2;
         this.mDefaultDragDirs = var1;
      }

      public int getDragDirs(RecyclerView var1, RecyclerView.ViewHolder var2) {
         return this.mDefaultDragDirs;
      }

      public int getMovementFlags(RecyclerView var1, RecyclerView.ViewHolder var2) {
         return makeMovementFlags(this.getDragDirs(var1, var2), this.getSwipeDirs(var1, var2));
      }

      public int getSwipeDirs(RecyclerView var1, RecyclerView.ViewHolder var2) {
         return this.mDefaultSwipeDirs;
      }

      public void setDefaultDragDirs(int var1) {
         this.mDefaultDragDirs = var1;
      }

      public void setDefaultSwipeDirs(int var1) {
         this.mDefaultSwipeDirs = var1;
      }
   }

   public interface ViewDropHandler {
      void prepareForDrop(View var1, View var2, int var3, int var4);
   }
}
