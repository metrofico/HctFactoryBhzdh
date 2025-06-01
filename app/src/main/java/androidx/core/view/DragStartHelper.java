package androidx.core.view;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class DragStartHelper {
   private boolean mDragging;
   private int mLastTouchX;
   private int mLastTouchY;
   private final OnDragStartListener mListener;
   private final View.OnLongClickListener mLongClickListener = new View.OnLongClickListener(this) {
      final DragStartHelper this$0;

      {
         this.this$0 = var1;
      }

      public boolean onLongClick(View var1) {
         return this.this$0.onLongClick(var1);
      }
   };
   private final View.OnTouchListener mTouchListener = new View.OnTouchListener(this) {
      final DragStartHelper this$0;

      {
         this.this$0 = var1;
      }

      public boolean onTouch(View var1, MotionEvent var2) {
         return this.this$0.onTouch(var1, var2);
      }
   };
   private final View mView;

   public DragStartHelper(View var1, OnDragStartListener var2) {
      this.mView = var1;
      this.mListener = var2;
   }

   public void attach() {
      this.mView.setOnLongClickListener(this.mLongClickListener);
      this.mView.setOnTouchListener(this.mTouchListener);
   }

   public void detach() {
      this.mView.setOnLongClickListener((View.OnLongClickListener)null);
      this.mView.setOnTouchListener((View.OnTouchListener)null);
   }

   public void getTouchPosition(Point var1) {
      var1.set(this.mLastTouchX, this.mLastTouchY);
   }

   public boolean onLongClick(View var1) {
      return this.mListener.onDragStart(var1, this);
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var3 = (int)var2.getX();
      int var4 = (int)var2.getY();
      int var5 = var2.getAction();
      if (var5 != 0) {
         if (var5 != 1) {
            if (var5 == 2) {
               if (!MotionEventCompat.isFromSource(var2, 8194) || (var2.getButtonState() & 1) == 0 || this.mDragging || this.mLastTouchX == var3 && this.mLastTouchY == var4) {
                  return false;
               }

               this.mLastTouchX = var3;
               this.mLastTouchY = var4;
               boolean var6 = this.mListener.onDragStart(var1, this);
               this.mDragging = var6;
               return var6;
            }

            if (var5 != 3) {
               return false;
            }
         }

         this.mDragging = false;
      } else {
         this.mLastTouchX = var3;
         this.mLastTouchY = var4;
      }

      return false;
   }

   public interface OnDragStartListener {
      boolean onDragStart(View var1, DragStartHelper var2);
   }
}
