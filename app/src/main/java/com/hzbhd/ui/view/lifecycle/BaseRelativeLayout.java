package com.hzbhd.ui.view.lifecycle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0004J\b\u0010\u001b\u001a\u00020\u000fH&J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010 \u001a\u00020\u0019J\n\u0010!\u001a\u0004\u0018\u00010\u0017H&J\b\u0010\"\u001a\u00020\u0019H\u0014J\b\u0010#\u001a\u00020\u0019H\u0014J\b\u0010$\u001a\u00020\u0019H\u0014J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016J\u0018\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\nH\u0014J\u0010\u0010,\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\nH\u0014R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"},
   d2 = {"Lcom/hzbhd/ui/view/lifecycle/BaseRelativeLayout;", "Landroid/widget/RelativeLayout;", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isAdd", "", "isInit", "isNeedAdd", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "mVisibility", "mWindowVisibility", "viewChild", "Landroid/view/View;", "addBaseChild", "", "addOrRemoveView", "autoInitChild", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "initChild", "initView", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onViewAdd", "onVisibilityChanged", "changedView", "visibility", "onWindowVisibilityChanged", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class BaseRelativeLayout extends RelativeLayout implements LifecycleOwner {
   private boolean isAdd;
   private boolean isInit;
   private boolean isNeedAdd;
   private final LifecycleRegistry mLifecycleRegistry;
   private int mVisibility;
   private int mWindowVisibility;
   private View viewChild;

   public BaseRelativeLayout(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseRelativeLayout(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseRelativeLayout(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseRelativeLayout(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.mLifecycleRegistry = new LifecycleRegistry((LifecycleOwner)this);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   private final void addBaseChild() {
      BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final BaseRelativeLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (this.this$0.isNeedAdd) {
               BaseUtil.logTime("viewVisible " + this.this$0.getClass().getSimpleName() + " , " + this.this$0.viewChild);
               this.this$0.removeAllViews();
               View var2 = this.this$0.viewChild;
               if (var2 != null) {
                  BaseRelativeLayout var1 = this.this$0;
                  var1.addView(var2);
                  var1.onViewAdd();
               }
            }

         }
      }));
   }

   protected final void addOrRemoveView() {
      BaseUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final BaseRelativeLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (LogUtil.log3()) {
               LogUtil.d("addOrRemoveView: " + this.this$0.getClass().getSimpleName() + "  " + this.this$0.isAttachedToWindow() + " , " + this.this$0.getWindowVisibility() + " , " + this.this$0.getVisibility() + " , " + this.this$0.isAdd + " , " + this.this$0.isNeedAdd + " , " + this.this$0.mWindowVisibility + ", " + this.this$0.mVisibility);
            }

            if (this.this$0.isAttachedToWindow() && this.this$0.getWindowVisibility() == 0 && this.this$0.getVisibility() == 0 && this.this$0.mWindowVisibility == 0 && this.this$0.mVisibility == 0) {
               if (!this.this$0.isAdd) {
                  this.this$0.isAdd = true;
                  this.this$0.isNeedAdd = true;
                  this.this$0.onLifeCycleChange(Lifecycle.State.RESUMED);
                  if (!this.this$0.isInit && this.this$0.autoInitChild()) {
                     this.this$0.isInit = true;
                     this.this$0.initChild();
                  } else {
                     this.this$0.addBaseChild();
                  }
               }
            } else if (this.this$0.isAdd) {
               this.this$0.isAdd = false;
               this.this$0.isNeedAdd = false;
               this.this$0.onLifeCycleChange(Lifecycle.State.CREATED);
               BaseUtil.logTime("viewHide " + this.this$0.getClass().getSimpleName());
               this.this$0.removeAllViews();
            }

         }
      }));
   }

   public abstract boolean autoInitChild();

   protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
      return (ViewGroup.LayoutParams)(new LayoutParams(-1, -1));
   }

   public Lifecycle getLifecycle() {
      return (Lifecycle)this.mLifecycleRegistry;
   }

   public final void initChild() {
      BaseUtil.INSTANCE.runBack((Function0)(new Function0(this) {
         final BaseRelativeLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            BaseRelativeLayout var1 = this.this$0;
            var1.viewChild = var1.initView();
            this.this$0.addBaseChild();
         }
      }));
   }

   public abstract View initView();

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
      this.mLifecycleRegistry.setCurrentState(var1);
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
}
