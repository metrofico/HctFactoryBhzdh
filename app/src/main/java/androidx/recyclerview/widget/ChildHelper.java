package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

class ChildHelper {
   private static final boolean DEBUG = false;
   private static final String TAG = "ChildrenHelper";
   final Bucket mBucket;
   final Callback mCallback;
   final List mHiddenViews;

   ChildHelper(Callback var1) {
      this.mCallback = var1;
      this.mBucket = new Bucket();
      this.mHiddenViews = new ArrayList();
   }

   private int getOffset(int var1) {
      if (var1 < 0) {
         return -1;
      } else {
         int var3 = this.mCallback.getChildCount();

         int var4;
         for(int var2 = var1; var2 < var3; var2 += var4) {
            var4 = var1 - (var2 - this.mBucket.countOnesBefore(var2));
            if (var4 == 0) {
               while(this.mBucket.get(var2)) {
                  ++var2;
               }

               return var2;
            }
         }

         return -1;
      }
   }

   private void hideViewInternal(View var1) {
      this.mHiddenViews.add(var1);
      this.mCallback.onEnteredHiddenState(var1);
   }

   private boolean unhideViewInternal(View var1) {
      if (this.mHiddenViews.remove(var1)) {
         this.mCallback.onLeftHiddenState(var1);
         return true;
      } else {
         return false;
      }
   }

   void addView(View var1, int var2, boolean var3) {
      if (var2 < 0) {
         var2 = this.mCallback.getChildCount();
      } else {
         var2 = this.getOffset(var2);
      }

      this.mBucket.insert(var2, var3);
      if (var3) {
         this.hideViewInternal(var1);
      }

      this.mCallback.addView(var1, var2);
   }

   void addView(View var1, boolean var2) {
      this.addView(var1, -1, var2);
   }

   void attachViewToParent(View var1, int var2, ViewGroup.LayoutParams var3, boolean var4) {
      if (var2 < 0) {
         var2 = this.mCallback.getChildCount();
      } else {
         var2 = this.getOffset(var2);
      }

      this.mBucket.insert(var2, var4);
      if (var4) {
         this.hideViewInternal(var1);
      }

      this.mCallback.attachViewToParent(var1, var2, var3);
   }

   void detachViewFromParent(int var1) {
      var1 = this.getOffset(var1);
      this.mBucket.remove(var1);
      this.mCallback.detachViewFromParent(var1);
   }

   View findHiddenNonRemovedView(int var1) {
      int var3 = this.mHiddenViews.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         View var4 = (View)this.mHiddenViews.get(var2);
         RecyclerView.ViewHolder var5 = this.mCallback.getChildViewHolder(var4);
         if (var5.getLayoutPosition() == var1 && !var5.isInvalid() && !var5.isRemoved()) {
            return var4;
         }
      }

      return null;
   }

   View getChildAt(int var1) {
      var1 = this.getOffset(var1);
      return this.mCallback.getChildAt(var1);
   }

   int getChildCount() {
      return this.mCallback.getChildCount() - this.mHiddenViews.size();
   }

   View getUnfilteredChildAt(int var1) {
      return this.mCallback.getChildAt(var1);
   }

   int getUnfilteredChildCount() {
      return this.mCallback.getChildCount();
   }

   void hide(View var1) {
      int var2 = this.mCallback.indexOfChild(var1);
      if (var2 >= 0) {
         this.mBucket.set(var2);
         this.hideViewInternal(var1);
      } else {
         throw new IllegalArgumentException("view is not a child, cannot hide " + var1);
      }
   }

   int indexOfChild(View var1) {
      int var2 = this.mCallback.indexOfChild(var1);
      if (var2 == -1) {
         return -1;
      } else {
         return this.mBucket.get(var2) ? -1 : var2 - this.mBucket.countOnesBefore(var2);
      }
   }

   boolean isHidden(View var1) {
      return this.mHiddenViews.contains(var1);
   }

   void removeAllViewsUnfiltered() {
      this.mBucket.reset();

      for(int var1 = this.mHiddenViews.size() - 1; var1 >= 0; --var1) {
         this.mCallback.onLeftHiddenState((View)this.mHiddenViews.get(var1));
         this.mHiddenViews.remove(var1);
      }

      this.mCallback.removeAllViews();
   }

   void removeView(View var1) {
      int var2 = this.mCallback.indexOfChild(var1);
      if (var2 >= 0) {
         if (this.mBucket.remove(var2)) {
            this.unhideViewInternal(var1);
         }

         this.mCallback.removeViewAt(var2);
      }
   }

   void removeViewAt(int var1) {
      var1 = this.getOffset(var1);
      View var2 = this.mCallback.getChildAt(var1);
      if (var2 != null) {
         if (this.mBucket.remove(var1)) {
            this.unhideViewInternal(var2);
         }

         this.mCallback.removeViewAt(var1);
      }
   }

   boolean removeViewIfHidden(View var1) {
      int var2 = this.mCallback.indexOfChild(var1);
      if (var2 == -1) {
         this.unhideViewInternal(var1);
         return true;
      } else if (this.mBucket.get(var2)) {
         this.mBucket.remove(var2);
         this.unhideViewInternal(var1);
         this.mCallback.removeViewAt(var2);
         return true;
      } else {
         return false;
      }
   }

   public String toString() {
      return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
   }

   void unhide(View var1) {
      int var2 = this.mCallback.indexOfChild(var1);
      if (var2 >= 0) {
         if (this.mBucket.get(var2)) {
            this.mBucket.clear(var2);
            this.unhideViewInternal(var1);
         } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + var1);
         }
      } else {
         throw new IllegalArgumentException("view is not a child, cannot hide " + var1);
      }
   }

   static class Bucket {
      static final int BITS_PER_WORD = 64;
      static final long LAST_BIT = Long.MIN_VALUE;
      long mData = 0L;
      Bucket mNext;

      private void ensureNext() {
         if (this.mNext == null) {
            this.mNext = new Bucket();
         }

      }

      void clear(int var1) {
         if (var1 >= 64) {
            Bucket var2 = this.mNext;
            if (var2 != null) {
               var2.clear(var1 - 64);
            }
         } else {
            this.mData &= ~(1L << var1);
         }

      }

      int countOnesBefore(int var1) {
         Bucket var2 = this.mNext;
         if (var2 == null) {
            return var1 >= 64 ? Long.bitCount(this.mData) : Long.bitCount(this.mData & (1L << var1) - 1L);
         } else {
            return var1 < 64 ? Long.bitCount(this.mData & (1L << var1) - 1L) : var2.countOnesBefore(var1 - 64) + Long.bitCount(this.mData);
         }
      }

      boolean get(int var1) {
         if (var1 >= 64) {
            this.ensureNext();
            return this.mNext.get(var1 - 64);
         } else {
            boolean var2;
            if ((this.mData & 1L << var1) != 0L) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }
      }

      void insert(int var1, boolean var2) {
         if (var1 >= 64) {
            this.ensureNext();
            this.mNext.insert(var1 - 64, var2);
         } else {
            long var4 = this.mData;
            boolean var3;
            if ((Long.MIN_VALUE & var4) != 0L) {
               var3 = true;
            } else {
               var3 = false;
            }

            long var6 = (1L << var1) - 1L;
            this.mData = (var4 & ~var6) << 1 | var4 & var6;
            if (var2) {
               this.set(var1);
            } else {
               this.clear(var1);
            }

            if (var3 || this.mNext != null) {
               this.ensureNext();
               this.mNext.insert(0, var3);
            }
         }

      }

      boolean remove(int var1) {
         if (var1 >= 64) {
            this.ensureNext();
            return this.mNext.remove(var1 - 64);
         } else {
            long var5 = 1L << var1;
            long var3 = this.mData;
            boolean var2;
            if ((var3 & var5) != 0L) {
               var2 = true;
            } else {
               var2 = false;
            }

            var3 &= ~var5;
            this.mData = var3;
            --var5;
            this.mData = var3 & var5 | Long.rotateRight(~var5 & var3, 1);
            Bucket var7 = this.mNext;
            if (var7 != null) {
               if (var7.get(0)) {
                  this.set(63);
               }

               this.mNext.remove(0);
            }

            return var2;
         }
      }

      void reset() {
         this.mData = 0L;
         Bucket var1 = this.mNext;
         if (var1 != null) {
            var1.reset();
         }

      }

      void set(int var1) {
         if (var1 >= 64) {
            this.ensureNext();
            this.mNext.set(var1 - 64);
         } else {
            this.mData |= 1L << var1;
         }

      }

      public String toString() {
         String var1;
         if (this.mNext == null) {
            var1 = Long.toBinaryString(this.mData);
         } else {
            var1 = this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
         }

         return var1;
      }
   }

   interface Callback {
      void addView(View var1, int var2);

      void attachViewToParent(View var1, int var2, ViewGroup.LayoutParams var3);

      void detachViewFromParent(int var1);

      View getChildAt(int var1);

      int getChildCount();

      RecyclerView.ViewHolder getChildViewHolder(View var1);

      int indexOfChild(View var1);

      void onEnteredHiddenState(View var1);

      void onLeftHiddenState(View var1);

      void removeAllViews();

      void removeViewAt(int var1);
   }
}
