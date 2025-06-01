package com.hzbhd.ui.view.lifecycle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0006\u0010#\u001a\u00020$J\b\u0010%\u001a\u00020$H\u0016J\b\u0010&\u001a\u00020\u000fH&J\b\u0010'\u001a\u00020(H\u0014J\b\u0010)\u001a\u00020*H\u0016J\u0006\u0010+\u001a\u00020$J\n\u0010,\u001a\u0004\u0018\u00010\"H&J\b\u0010-\u001a\u00020$H\u0014J\b\u0010.\u001a\u00020$H\u0014J\b\u0010/\u001a\u00020$H\u0014J\u0010\u00100\u001a\u00020$2\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020$H\u0016J\u0018\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020\nH\u0014J\u0010\u00107\u001a\u00020$2\u0006\u00106\u001a\u00020\nH\u0014R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"},
   d2 = {"Lcom/hzbhd/ui/view/lifecycle/BaseFrameLayout;", "Landroid/widget/FrameLayout;", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isAdd", "", "()Z", "setAdd", "(Z)V", "isInit", "setInit", "isNeedAdd", "setNeedAdd", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "mVisibility", "getMVisibility", "()I", "setMVisibility", "(I)V", "mWindowVisibility", "getMWindowVisibility", "setMWindowVisibility", "viewChild", "Landroid/view/View;", "addBaseChild", "", "addOrRemoveView", "autoInitChild", "generateDefaultLayoutParams", "Landroid/widget/FrameLayout$LayoutParams;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "initChild", "initView", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onViewAdd", "onVisibilityChanged", "changedView", "visibility", "onWindowVisibilityChanged", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class BaseFrameLayout extends FrameLayout implements LifecycleOwner {
   private boolean isAdd;
   private boolean isInit;
   private boolean isNeedAdd;
   private final LifecycleRegistry mLifecycleRegistry;
   private int mVisibility;
   private int mWindowVisibility;
   private View viewChild;

   public BaseFrameLayout(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseFrameLayout(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseFrameLayout(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseFrameLayout(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public final void addBaseChild() {
      BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final BaseFrameLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (this.this$0.isNeedAdd()) {
               BaseUtil.logTime("viewVisible " + this.this$0.getClass().getSimpleName() + " , " + this.this$0.viewChild);
               this.this$0.removeAllViews();
               View var1 = this.this$0.viewChild;
               if (var1 != null) {
                  BaseFrameLayout var2 = this.this$0;
                  var2.addView(var1);
                  var2.onViewAdd();
               }
            }

         }
      }));
   }

   public void addOrRemoveView() {
      BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final BaseFrameLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (LogUtil.log3()) {
               LogUtil.d("addOrRemoveView: " + this.this$0.getClass().getSimpleName() + "  " + this.this$0.isAttachedToWindow() + " , " + this.this$0.getWindowVisibility() + " , " + this.this$0.getVisibility() + " , " + this.this$0.isAdd() + " , " + this.this$0.isNeedAdd() + " , " + this.this$0.getMWindowVisibility() + ", " + this.this$0.getMVisibility());
            }

            if (this.this$0.isAttachedToWindow() && this.this$0.getWindowVisibility() == 0 && this.this$0.getVisibility() == 0 && this.this$0.getMWindowVisibility() == 0 && this.this$0.getMVisibility() == 0) {
               if (!this.this$0.isAdd()) {
                  this.this$0.setAdd(true);
                  this.this$0.setNeedAdd(true);
                  this.this$0.onLifeCycleChange(Lifecycle.State.RESUMED);
                  if (!this.this$0.isInit() && this.this$0.autoInitChild()) {
                     this.this$0.setInit(true);
                     this.this$0.initChild();
                  } else {
                     this.this$0.addBaseChild();
                  }
               }
            } else if (this.this$0.isAdd()) {
               this.this$0.setAdd(false);
               this.this$0.setNeedAdd(false);
               this.this$0.onLifeCycleChange(Lifecycle.State.CREATED);
               BaseUtil.logTime("viewHide " + this.this$0.getClass().getSimpleName());
               this.this$0.removeAllViews();
            }

         }
      }));
   }

   public abstract boolean autoInitChild();

   protected LayoutParams generateDefaultLayoutParams() {
      return new LayoutParams(-1, -1);
   }

   public Lifecycle getLifecycle() {
      return (Lifecycle)this.mLifecycleRegistry;
   }

   public final int getMVisibility() {
      return this.mVisibility;
   }

   public final int getMWindowVisibility() {
      return this.mWindowVisibility;
   }

   public final void initChild() {
      BaseUtil.INSTANCE.runBack((Function0)(new Function0(this) {
         final BaseFrameLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            BaseFrameLayout var1 = this.this$0;
            var1.viewChild = var1.initView();
            this.this$0.addBaseChild();
         }
      }));
   }

   public abstract View initView();

   public final boolean isAdd() {
      return this.isAdd;
   }

   public final boolean isInit() {
      return this.isInit;
   }

   public final boolean isNeedAdd() {
      return this.isNeedAdd;
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
      this.mLifecycleRegistry.markState(var1);
   }

   public void onViewAdd() {
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

   public final void setAdd(boolean var1) {
      this.isAdd = var1;
   }

   public final void setInit(boolean var1) {
      this.isInit = var1;
   }

   public final void setMVisibility(int var1) {
      this.mVisibility = var1;
   }

   public final void setMWindowVisibility(int var1) {
      this.mWindowVisibility = var1;
   }

   public final void setNeedAdd(boolean var1) {
      this.isNeedAdd = var1;
   }
}
