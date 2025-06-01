package com.hzbhd.ui.life;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.hzbhd.ui.base.BhdRelativeLayout;
import com.hzbhd.ui.base.BhdViewUtil;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001bH\u0014J\b\u0010!\u001a\u00020\u001bH\u0014J\b\u0010\"\u001a\u00020\u001bH\u0014J\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\nH\u0014J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\nH\u0014R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006+"},
   d2 = {"Lcom/hzbhd/ui/life/BaseLifeRelativeLayout;", "Lcom/hzbhd/ui/base/BhdRelativeLayout;", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getMLifecycleRegistry", "()Landroidx/lifecycle/LifecycleRegistry;", "mVisibility", "getMVisibility", "()I", "setMVisibility", "(I)V", "mWindowVisibility", "getMWindowVisibility", "setMWindowVisibility", "addOrRemoveView", "", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "onWindowVisibilityChanged", "lifeview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public class BaseLifeRelativeLayout extends BhdRelativeLayout implements LifecycleOwner {
   private final LifecycleRegistry mLifecycleRegistry;
   private int mVisibility;
   private int mWindowVisibility;

   public BaseLifeRelativeLayout(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseLifeRelativeLayout(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseLifeRelativeLayout(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseLifeRelativeLayout(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public void addOrRemoveView() {
      BhdViewUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final BaseLifeRelativeLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (LogUtil.log3()) {
               LogUtil.d("addOrRemoveView: " + this.this$0.getClass().getSimpleName() + "  " + this.this$0.isAttachedToWindow() + " , " + this.this$0.getWindowVisibility() + " , " + this.this$0.getVisibility() + " , " + this.this$0.getMVisibility() + ", " + this.this$0.getMWindowVisibility());
            }

            if (this.this$0.isAttachedToWindow() && this.this$0.getWindowVisibility() == 0 && this.this$0.getVisibility() == 0 && this.this$0.getMVisibility() == 0 && this.this$0.getMWindowVisibility() == 0) {
               this.this$0.onLifeCycleChange(Lifecycle.State.RESUMED);
            } else {
               this.this$0.onLifeCycleChange(Lifecycle.State.CREATED);
            }

         }
      }));
   }

   protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
      return (ViewGroup.LayoutParams)(new LayoutParams(-1, -1));
   }

   public Lifecycle getLifecycle() {
      return (Lifecycle)this.getMLifecycleRegistry();
   }

   public LifecycleRegistry getMLifecycleRegistry() {
      return this.mLifecycleRegistry;
   }

   protected final int getMVisibility() {
      return this.mVisibility;
   }

   protected final int getMWindowVisibility() {
      return this.mWindowVisibility;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.addOrRemoveView();
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.addOrRemoveView();
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.addOrRemoveView();
   }

   public void onLifeCycleChange(Lifecycle.State var1) {
      Intrinsics.checkNotNullParameter(var1, "state");
      this.getMLifecycleRegistry().setCurrentState(var1);
   }

   protected void onVisibilityChanged(View var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "changedView");
      super.onVisibilityChanged(var1, var2);
      this.mVisibility = var2;
      this.addOrRemoveView();
   }

   protected void onWindowVisibilityChanged(int var1) {
      super.onWindowVisibilityChanged(var1);
      this.mWindowVisibility = var1;
      this.addOrRemoveView();
   }

   protected final void setMVisibility(int var1) {
      this.mVisibility = var1;
   }

   protected final void setMWindowVisibility(int var1) {
      this.mWindowVisibility = var1;
   }
}
