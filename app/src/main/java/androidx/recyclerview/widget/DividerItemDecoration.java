package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
   private static final int[] ATTRS = new int[]{16843284};
   public static final int HORIZONTAL = 0;
   private static final String TAG = "DividerItem";
   public static final int VERTICAL = 1;
   private final Rect mBounds = new Rect();
   private Drawable mDivider;
   private int mOrientation;

   public DividerItemDecoration(Context var1, int var2) {
      TypedArray var3 = var1.obtainStyledAttributes(ATTRS);
      Drawable var4 = var3.getDrawable(0);
      this.mDivider = var4;
      if (var4 == null) {
         Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
      }

      var3.recycle();
      this.setOrientation(var2);
   }

   private void drawHorizontal(Canvas var1, RecyclerView var2) {
      var1.save();
      boolean var9 = var2.getClipToPadding();
      int var5 = 0;
      int var3;
      int var4;
      if (var9) {
         var4 = var2.getPaddingTop();
         var3 = var2.getHeight() - var2.getPaddingBottom();
         var1.clipRect(var2.getPaddingLeft(), var4, var2.getWidth() - var2.getPaddingRight(), var3);
      } else {
         var3 = var2.getHeight();
         var4 = 0;
      }

      for(int var6 = var2.getChildCount(); var5 < var6; ++var5) {
         View var10 = var2.getChildAt(var5);
         var2.getLayoutManager().getDecoratedBoundsWithMargins(var10, this.mBounds);
         int var8 = this.mBounds.right + Math.round(var10.getTranslationX());
         int var7 = this.mDivider.getIntrinsicWidth();
         this.mDivider.setBounds(var8 - var7, var4, var8, var3);
         this.mDivider.draw(var1);
      }

      var1.restore();
   }

   private void drawVertical(Canvas var1, RecyclerView var2) {
      var1.save();
      boolean var9 = var2.getClipToPadding();
      int var5 = 0;
      int var3;
      int var4;
      if (var9) {
         var4 = var2.getPaddingLeft();
         var3 = var2.getWidth() - var2.getPaddingRight();
         var1.clipRect(var4, var2.getPaddingTop(), var3, var2.getHeight() - var2.getPaddingBottom());
      } else {
         var3 = var2.getWidth();
         var4 = 0;
      }

      for(int var6 = var2.getChildCount(); var5 < var6; ++var5) {
         View var10 = var2.getChildAt(var5);
         var2.getDecoratedBoundsWithMargins(var10, this.mBounds);
         int var8 = this.mBounds.bottom + Math.round(var10.getTranslationY());
         int var7 = this.mDivider.getIntrinsicHeight();
         this.mDivider.setBounds(var4, var8 - var7, var3, var8);
         this.mDivider.draw(var1);
      }

      var1.restore();
   }

   public Drawable getDrawable() {
      return this.mDivider;
   }

   public void getItemOffsets(Rect var1, View var2, RecyclerView var3, RecyclerView.State var4) {
      Drawable var5 = this.mDivider;
      if (var5 == null) {
         var1.set(0, 0, 0, 0);
      } else {
         if (this.mOrientation == 1) {
            var1.set(0, 0, 0, var5.getIntrinsicHeight());
         } else {
            var1.set(0, 0, var5.getIntrinsicWidth(), 0);
         }

      }
   }

   public void onDraw(Canvas var1, RecyclerView var2, RecyclerView.State var3) {
      if (var2.getLayoutManager() != null && this.mDivider != null) {
         if (this.mOrientation == 1) {
            this.drawVertical(var1, var2);
         } else {
            this.drawHorizontal(var1, var2);
         }
      }

   }

   public void setDrawable(Drawable var1) {
      if (var1 != null) {
         this.mDivider = var1;
      } else {
         throw new IllegalArgumentException("Drawable cannot be null.");
      }
   }

   public void setOrientation(int var1) {
      if (var1 != 0 && var1 != 1) {
         throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
      } else {
         this.mOrientation = var1;
      }
   }
}
