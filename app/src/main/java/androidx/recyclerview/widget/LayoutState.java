package androidx.recyclerview.widget;

import android.view.View;

class LayoutState {
   static final int INVALID_LAYOUT = Integer.MIN_VALUE;
   static final int ITEM_DIRECTION_HEAD = -1;
   static final int ITEM_DIRECTION_TAIL = 1;
   static final int LAYOUT_END = 1;
   static final int LAYOUT_START = -1;
   int mAvailable;
   int mCurrentPosition;
   int mEndLine = 0;
   boolean mInfinite;
   int mItemDirection;
   int mLayoutDirection;
   boolean mRecycle = true;
   int mStartLine = 0;
   boolean mStopInFocusable;

   boolean hasMore(RecyclerView.State var1) {
      int var2 = this.mCurrentPosition;
      boolean var3;
      if (var2 >= 0 && var2 < var1.getItemCount()) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   View next(RecyclerView.Recycler var1) {
      View var2 = var1.getViewForPosition(this.mCurrentPosition);
      this.mCurrentPosition += this.mItemDirection;
      return var2;
   }

   public String toString() {
      return "LayoutState{mAvailable=" + this.mAvailable + ", mCurrentPosition=" + this.mCurrentPosition + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + ", mStartLine=" + this.mStartLine + ", mEndLine=" + this.mEndLine + '}';
   }
}
