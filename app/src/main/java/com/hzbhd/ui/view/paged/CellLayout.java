package com.hzbhd.ui.view.paged;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\u001e\u0010%\u001a\u00020)2\u0006\u0010'\u001a\u00020(2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005J&\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005R\u001a\u0010\u000f\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\u001a\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001a\u0010\u001d\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R\u001a\u0010 \u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0011\"\u0004\b\"\u0010\u0013R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0011¨\u00061"},
   d2 = {"Lcom/hzbhd/ui/view/paged/CellLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "page", "", "(Landroid/content/Context;I)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mCellHeight", "getMCellHeight", "()I", "setMCellHeight", "(I)V", "mCellWidth", "getMCellWidth", "setMCellWidth", "mMaxHeight", "getMMaxHeight", "setMMaxHeight", "mMaxWidth", "getMMaxWidth", "setMMaxWidth", "mXSize", "getMXSize", "setMXSize", "mYSize", "getMYSize", "setMYSize", "<set-?>", "getPage", "addChild", "", "child", "Lcom/hzbhd/ui/view/paged/CellChild;", "", "x", "y", "setGridSize", "xSize", "ySize", "maxWidth", "maxHeight", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class CellLayout extends FrameLayout {
   private int mCellHeight;
   private int mCellWidth;
   private int mMaxHeight;
   private int mMaxWidth;
   private int mXSize;
   private int mYSize;
   private int page;

   public CellLayout(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mXSize = 5;
      this.mYSize = 2;
      this.mMaxWidth = 1920;
      this.mMaxHeight = 1080;
      this.mCellWidth = 1920 / 5;
      this.mCellHeight = 1080 / 2;
   }

   public CellLayout(Context var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mXSize = 5;
      this.mYSize = 2;
      this.mMaxWidth = 1920;
      this.mMaxHeight = 1080;
      this.mCellWidth = 1920 / 5;
      this.mCellHeight = 1080 / 2;
      this.page = var2;
   }

   public CellLayout(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2);
      this.mXSize = 5;
      this.mYSize = 2;
      this.mMaxWidth = 1920;
      this.mMaxHeight = 1080;
      this.mCellWidth = 1920 / 5;
      this.mCellHeight = 1080 / 2;
   }

   public CellLayout(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.mXSize = 5;
      this.mYSize = 2;
      this.mMaxWidth = 1920;
      this.mMaxHeight = 1080;
      this.mCellWidth = 1920 / 5;
      this.mCellHeight = 1080 / 2;
   }

   public CellLayout(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.mXSize = 5;
      this.mYSize = 2;
      this.mMaxWidth = 1920;
      this.mMaxHeight = 1080;
      this.mCellWidth = 1920 / 5;
      this.mCellHeight = 1080 / 2;
   }

   public final void addChild(CellChild var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "child");
      var1.setLoc(var2, var3);
      this.addChild(var1);
   }

   public final boolean addChild(CellChild var1) {
      Intrinsics.checkNotNullParameter(var1, "child");
      if (var1.getPageX() + var1.getXsize() <= this.mXSize && var1.getPageY() + var1.getYsize() <= this.mYSize) {
         LayoutParams var2 = new LayoutParams(this.mCellWidth * var1.getXsize(), this.mCellHeight * var1.getYsize());
         var2.leftMargin = this.mCellWidth * var1.getPageX();
         var2.topMargin = this.mCellHeight * var1.getPageY();
         this.addView((View)var1, (ViewGroup.LayoutParams)var2);
         return true;
      } else {
         if (LogUtil.log5()) {
            LogUtil.d("addChild: " + var1.getPageX() + ',' + var1.getXsize() + ',' + this.mXSize + "::" + var1.getPageY() + ',' + var1.getYsize() + ',' + this.mYSize + " == return false");
         }

         return false;
      }
   }

   protected final int getMCellHeight() {
      return this.mCellHeight;
   }

   protected final int getMCellWidth() {
      return this.mCellWidth;
   }

   protected final int getMMaxHeight() {
      return this.mMaxHeight;
   }

   protected final int getMMaxWidth() {
      return this.mMaxWidth;
   }

   protected final int getMXSize() {
      return this.mXSize;
   }

   protected final int getMYSize() {
      return this.mYSize;
   }

   public final int getPage() {
      return this.page;
   }

   public final void setGridSize(int var1, int var2, int var3, int var4) {
      this.mXSize = var1;
      this.mYSize = var2;
      this.mMaxWidth = var3;
      this.mMaxHeight = var4;
      this.mCellWidth = var3 / var1;
      this.mCellHeight = var4 / var2;
   }

   protected final void setMCellHeight(int var1) {
      this.mCellHeight = var1;
   }

   protected final void setMCellWidth(int var1) {
      this.mCellWidth = var1;
   }

   protected final void setMMaxHeight(int var1) {
      this.mMaxHeight = var1;
   }

   protected final void setMMaxWidth(int var1) {
      this.mMaxWidth = var1;
   }

   protected final void setMXSize(int var1) {
      this.mXSize = var1;
   }

   protected final void setMYSize(int var1) {
      this.mYSize = var1;
   }
}
