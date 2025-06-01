package com.hzbhd.ui.life;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.ui.base.BhdRelativeLayout;
import com.hzbhd.ui.base.BhdViewUtil;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\b\u0010\u001e\u001a\u00020\u0010H\u0016J\b\u0010\u001f\u001a\u00020 H\u0014J\b\u0010!\u001a\u00020\u000eH&J\u0006\u0010\"\u001a\u00020\u001bJ\b\u0010#\u001a\u00020\u001bH\u0014J\b\u0010$\u001a\u00020\u001bH\u0014J\b\u0010%\u001a\u00020\u001bH\u0014J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u001bH\u0016J\u0018\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\tH\u0014J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\tH\u0014R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006/"},
   d2 = {"Lcom/hzbhd/ui/life/BaseRelativeLayout;", "Lcom/hzbhd/ui/base/BhdRelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "emptyBinding", "Lcom/hzbhd/ui/life/EmptyBinding;", "isAdd", "", "isNeedAdd", "mVisibility", "mWindowVisibility", "myLifeCycleOwner", "Lcom/hzbhd/ui/life/MyLifeCycleOwner;", "getMyLifeCycleOwner", "()Lcom/hzbhd/ui/life/MyLifeCycleOwner;", "setMyLifeCycleOwner", "(Lcom/hzbhd/ui/life/MyLifeCycleOwner;)V", "addBaseChild", "", "addOrRemoveView", "autoInitChild", "autoRemoveChild", "generateDefaultLayoutParams", "Landroid/widget/FrameLayout$LayoutParams;", "getBinding", "initChild", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onViewAdd", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "onWindowVisibilityChanged", "lifeview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public abstract class BaseRelativeLayout extends BhdRelativeLayout {
   private EmptyBinding emptyBinding;
   private boolean isAdd;
   private boolean isNeedAdd;
   private int mVisibility;
   private int mWindowVisibility;
   private MyLifeCycleOwner myLifeCycleOwner;

   public BaseRelativeLayout(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseRelativeLayout(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseRelativeLayout(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   public BaseRelativeLayout(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.mWindowVisibility = 8;
      this.mVisibility = 8;
   }

   private final void addBaseChild() {
      BhdViewUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final BaseRelativeLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (this.this$0.isNeedAdd) {
               this.this$0.removeAllViews();
               EmptyBinding var1 = this.this$0.emptyBinding;
               if (var1 != null) {
                  View var3 = var1.getRoot();
                  if (var3 != null) {
                     BaseRelativeLayout var2 = this.this$0;
                     var2.addView(var3);
                     var2.onViewAdd();
                  }
               }
            }

         }
      }));
   }

   private final void addOrRemoveView() {
      BhdViewUtil.INSTANCE.runUi((Function0)(new Function0(this) {
         final BaseRelativeLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (LogUtil.log3()) {
               LogUtil.d("addOrRemoveView: " + this.this$0.getClass().getSimpleName() + "  " + this.this$0.isAttachedToWindow() + " , " + this.this$0.getWindowVisibility() + " , " + this.this$0.getVisibility() + " , " + this.this$0.isAdd + " , " + this.this$0.isNeedAdd + " , " + this.this$0.mWindowVisibility + ", " + this.this$0.mVisibility);
            }

            boolean var1 = this.this$0.isAttachedToWindow();
            Lifecycle.State var2 = null;
            MyLifeCycleOwner var3 = null;
            BaseRelativeLayout var4;
            MyLifeCycleOwner var5;
            Lifecycle var6;
            Lifecycle.State var8;
            if (var1 && this.this$0.getWindowVisibility() == 0 && this.this$0.getVisibility() == 0 && this.this$0.mWindowVisibility == 0 && this.this$0.mVisibility == 0) {
               if (!this.this$0.isAdd) {
                  this.this$0.isAdd = true;
                  this.this$0.isNeedAdd = true;
                  if (this.this$0.getMyLifeCycleOwner() == null) {
                     this.this$0.setMyLifeCycleOwner(new MyLifeCycleOwner());
                     if (this.this$0.autoInitChild()) {
                        this.this$0.initChild();
                     }
                  }

                  if (!this.this$0.autoInitChild()) {
                     this.this$0.addBaseChild();
                  }

                  var5 = this.this$0.getMyLifeCycleOwner();
                  if (var5 != null) {
                     var5.onLifeCycleChange(Lifecycle.State.RESUMED);
                  }

                  var4 = this.this$0;
                  var5 = var4.getMyLifeCycleOwner();
                  if (var5 == null) {
                     var2 = var3;
                  } else {
                     var6 = var5.getLifecycle();
                     if (var6 == null) {
                        var2 = var3;
                     } else {
                        var2 = var6.getCurrentState();
                     }
                  }

                  var8 = var2;
                  if (var2 == null) {
                     var8 = Lifecycle.State.CREATED;
                  }

                  Intrinsics.checkNotNullExpressionValue(var8, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                  var4.onLifeCycleChange(var8);
               }
            } else if (this.this$0.isAdd) {
               this.this$0.isAdd = false;
               this.this$0.isNeedAdd = false;
               this.this$0.removeAllViews();
               if (this.this$0.autoInitChild() && this.this$0.autoRemoveChild()) {
                  var5 = this.this$0.getMyLifeCycleOwner();
                  if (var5 != null) {
                     var5.onLifeCycleChange(Lifecycle.State.DESTROYED);
                  }

                  label81: {
                     var4 = this.this$0;
                     var5 = var4.getMyLifeCycleOwner();
                     if (var5 != null) {
                        var6 = var5.getLifecycle();
                        if (var6 != null) {
                           var2 = var6.getCurrentState();
                           break label81;
                        }
                     }

                     var2 = null;
                  }

                  var8 = var2;
                  if (var2 == null) {
                     var8 = Lifecycle.State.CREATED;
                  }

                  Intrinsics.checkNotNullExpressionValue(var8, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                  var4.onLifeCycleChange(var8);
                  this.this$0.emptyBinding = null;
                  this.this$0.setMyLifeCycleOwner((MyLifeCycleOwner)null);
               } else {
                  var3 = this.this$0.getMyLifeCycleOwner();
                  if (var3 != null) {
                     var3.onLifeCycleChange(Lifecycle.State.CREATED);
                  }

                  var4 = this.this$0;
                  var3 = var4.getMyLifeCycleOwner();
                  if (var3 != null) {
                     Lifecycle var7 = var3.getLifecycle();
                     if (var7 != null) {
                        var2 = var7.getCurrentState();
                     }
                  }

                  var8 = var2;
                  if (var2 == null) {
                     var8 = Lifecycle.State.CREATED;
                  }

                  Intrinsics.checkNotNullExpressionValue(var8, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                  var4.onLifeCycleChange(var8);
               }
            }

         }
      }));
   }

   public boolean autoInitChild() {
      return true;
   }

   public boolean autoRemoveChild() {
      return true;
   }

   protected FrameLayout.LayoutParams generateDefaultLayoutParams() {
      return new FrameLayout.LayoutParams(-1, -1);
   }

   public abstract EmptyBinding getBinding();

   public final MyLifeCycleOwner getMyLifeCycleOwner() {
      return this.myLifeCycleOwner;
   }

   public final void initChild() {
      BhdViewUtil.INSTANCE.runBack((Function0)(new Function0(this) {
         final BaseRelativeLayout this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            if (!this.this$0.autoRemoveChild() && this.this$0.emptyBinding != null) {
               this.this$0.addBaseChild();
            } else {
               BaseRelativeLayout var1 = this.this$0;
               var1.emptyBinding = var1.getBinding();
               EmptyBinding var2 = this.this$0.emptyBinding;
               if (var2 != null) {
                  MyLifeCycleOwner var3 = this.this$0.getMyLifeCycleOwner();
                  Intrinsics.checkNotNull(var3);
                  var2.addObserver((LifecycleOwner)var3);
               }

               EmptyBinding var4 = this.this$0.emptyBinding;
               if (var4 != null) {
                  var4.bindAction();
               }

               this.this$0.addBaseChild();
            }
         }
      }));
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

   public final void setMyLifeCycleOwner(MyLifeCycleOwner var1) {
      this.myLifeCycleOwner = var1;
   }
}
