package com.hzbhd.ui.view.paged;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.ui.view.lifecycle.BaseLifeFrameLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 :2\u00020\u0001:\u0001:B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ&\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tJ\b\u0010-\u001a\u00020.H\u0014J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020.H\u0014J\u000e\u00103\u001a\u00020.2\u0006\u00100\u001a\u000201J\u0016\u00104\u001a\u00020.2\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\tJ\u0010\u00105\u001a\u00020.2\u0006\u00106\u001a\u00020\u0011H\u0016J\u0016\u00107\u001a\u00020.2\u0006\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR$\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0018\"\u0004\b!\u0010\u001aR$\u0010\"\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0018\"\u0004\b$\u0010\u001aR$\u0010%\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010\u001a¨\u0006;"},
   d2 = {"Lcom/hzbhd/ui/view/paged/CellChild;", "Lcom/hzbhd/ui/view/lifecycle/BaseLifeFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "downX", "", "downY", "event_move", "", "isLongClick", "isThisTouch", "longClickRunnable", "Ljava/lang/Runnable;", "page", "getPage", "()I", "setPage", "(I)V", "<set-?>", "pageX", "getPageX", "setPageX", "pageY", "getPageY", "setPageY", "xsize", "getXsize", "setXsize", "ysize", "getYsize", "setYsize", "isCover", "x", "y", "xSize", "ySize", "onClick", "", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "onLongClick", "onTouch", "setLoc", "setPressed", "pressed", "setSize", "x_size", "y_size", "Companion", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class CellChild extends BaseLifeFrameLayout {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "CellChild";
   private float downX;
   private float downY;
   private boolean event_move;
   private boolean isLongClick;
   private boolean isThisTouch;
   private final Runnable longClickRunnable;
   private int page;
   private int pageX;
   private int pageY;
   private int xsize;
   private int ysize;

   // $FF: synthetic method
   public static void $r8$lambda$eqoxFADIU2VjIBoOwWBEbP0wS0M(CellChild var0) {
      longClickRunnable$lambda_0(var0);
   }

   public CellChild(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.xsize = 1;
      this.ysize = 1;
      this.longClickRunnable = new CellChild$$ExternalSyntheticLambda0(this);
   }

   public CellChild(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.xsize = 1;
      this.ysize = 1;
      this.longClickRunnable = new CellChild$$ExternalSyntheticLambda0(this);
   }

   public CellChild(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2, var3);
      this.xsize = 1;
      this.ysize = 1;
      this.longClickRunnable = new CellChild$$ExternalSyntheticLambda0(this);
   }

   public CellChild(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2, var3, var4);
      this.xsize = 1;
      this.ysize = 1;
      this.longClickRunnable = new CellChild$$ExternalSyntheticLambda0(this);
   }

   private static final void longClickRunnable$lambda_0(CellChild var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.isLongClick = true;
      var0.onLongClick();
   }

   public final int getPage() {
      return this.page;
   }

   public final int getPageX() {
      return this.pageX;
   }

   public final int getPageY() {
      return this.pageY;
   }

   public final int getXsize() {
      return this.xsize;
   }

   public final int getYsize() {
      return this.ysize;
   }

   public final boolean isCover(int var1, int var2, int var3, int var4) {
      int var6 = this.pageX;
      int var5 = this.xsize;
      boolean var8 = false;
      boolean var7 = var8;
      if (var5 + var6 >= var1 + 1) {
         if (var1 + var3 < var6 + 1) {
            var7 = var8;
         } else {
            var1 = this.pageY;
            var7 = var8;
            if (this.ysize + var1 >= var2 + 1) {
               if (var2 + var4 < var1 + 1) {
                  var7 = var8;
               } else {
                  var7 = true;
               }
            }
         }
      }

      return var7;
   }

   protected void onClick() {
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "ev");
      this.isThisTouch = true;
      this.isLongClick = false;
      this.setPressed(true);
      this.postDelayed(this.longClickRunnable, 500L);
      return super.onInterceptTouchEvent(var1);
   }

   protected void onLongClick() {
   }

   public final void onTouch(MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var1, "ev");
      if (var1.getAction() == 0) {
         this.isThisTouch = false;
         this.event_move = false;
         this.downX = var1.getRawX();
         this.downY = var1.getRawY();
      } else if (this.isThisTouch) {
         if (var1.getAction() != 1 && var1.getAction() != 3) {
            boolean var2;
            if (var1.getRawX() == this.downX) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (!var2) {
               if (var1.getRawY() == this.downY) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (!var2) {
                  this.setPressed(false);
                  this.event_move = true;
                  this.removeCallbacks(this.longClickRunnable);
               }
            }
         } else {
            this.setPressed(false);
            this.removeCallbacks(this.longClickRunnable);
            if (!this.event_move && this.isThisTouch) {
               if (!this.isLongClick) {
                  this.onClick();
               }

               this.isThisTouch = false;
            }
         }
      }

   }

   public final void setLoc(int var1, int var2) {
      this.pageX = var1;
      this.pageY = var2;
   }

   public final void setPage(int var1) {
      this.page = var1;
   }

   protected final void setPageX(int var1) {
      this.pageX = var1;
   }

   protected final void setPageY(int var1) {
      this.pageY = var1;
   }

   public void setPressed(boolean var1) {
      super.setPressed(var1);
      Log.d("CellChild", "setPressed: " + var1);
   }

   public final void setSize(int var1, int var2) {
      this.xsize = var1;
      this.ysize = var2;
   }

   protected final void setXsize(int var1) {
      this.xsize = var1;
   }

   protected final void setYsize(int var1) {
      this.ysize = var1;
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/ui/view/paged/CellChild$Companion;", "", "()V", "TAG", "", "commonview-base_release"},
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
   }
}
