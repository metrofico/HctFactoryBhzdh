package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnBubbleClickListener;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_mgr.UiMgrInterfaceUtil;
import com.hzbhd.canbus.ui_set.BubbleUiSet;
import com.hzbhd.canbus.util.CommUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0006\u0010\u0010\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"},
   d2 = {"Lcom/hzbhd/canbus/view/Bubble;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mIsShowing", "", "mView", "Landroid/view/View;", "mWindowManager", "Landroid/view/WindowManager;", "mWmParam", "Landroid/view/WindowManager$LayoutParams;", "hideBubble", "", "showBubble", "updateBubble", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class Bubble {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final int MOVE_ICON_COUNT = 16;
   private static final String TAG = "Bubble";
   private boolean mIsShowing;
   private View mView;
   private WindowManager mWindowManager;
   private WindowManager.LayoutParams mWmParam;

   // $FF: synthetic method
   public static boolean $r8$lambda$h_9_jp7leCQehsOtSe7yOFCa9xE(Ref.IntRef var0, View var1, Bubble var2, BubbleUiSet var3, int var4, View var5, MotionEvent var6) {
      return lambda_4$lambda_3$lambda_2(var0, var1, var2, var3, var4, var5, var6);
   }

   public Bubble(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      Log.i("Bubble", "init");
      UiMgrInterfaceUtil var3 = UiMgrFactory.getCanUiMgrUtil(var1);
      if (var3 != null) {
         BubbleUiSet var5 = var3.getBubbleUiSet(var1);
         if (var5 != null) {
            Log.i("Bubble", "have BubbleUiSet");
            Object var4 = var1.getSystemService("window");
            Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type android.view.WindowManager");
            this.mWindowManager = (WindowManager)var4;
            WindowManager.LayoutParams var6 = new WindowManager.LayoutParams();
            var6.type = 2001;
            var6.format = 1;
            var6.flags = 8;
            var6.gravity = 8388659;
            var6.width = -2;
            var6.height = -2;
            var6.y = 300;
            this.mWmParam = var6;
            View var7 = LayoutInflater.from(var1).inflate(2131558754, (ViewGroup)null);
            ((ImageView)var7.findViewById(R.id.iv_bubble_icon)).setImageResource(CommUtil.getImgIdByResId(var1, var5.getIconDrawable()));
            int var2 = (int)var7.getResources().getDimension(2131178338);
            var7.setOnTouchListener(new Bubble$$ExternalSyntheticLambda0(new Ref.IntRef(), var7, this, var5, var2));
            this.mView = var7;
         }
      }

   }

   private final void hideBubble() {
      Log.i("Bubble", "hideBubble: mIsShowing " + this.mIsShowing);
      if (this.mIsShowing) {
         Log.i("Bubble", "hideBubble: mWindowManager ----{}" + this.mWindowManager);
         WindowManager var1 = this.mWindowManager;
         if (var1 != null) {
            var1.removeView(this.mView);
         }

         this.mIsShowing = false;
      }

   }

   private static final boolean lambda_4$lambda_3$lambda_2(Ref.IntRef var0, View var1, Bubble var2, BubbleUiSet var3, int var4, View var5, MotionEvent var6) {
      Intrinsics.checkNotNullParameter(var0, "$notActionUpCount");
      Intrinsics.checkNotNullParameter(var2, "this$0");
      Intrinsics.checkNotNullParameter(var3, "$this_run");
      if (var6.getAction() != 1) {
         ++var0.element;
      }

      int var7 = var6.getAction();
      if (var7 != 0) {
         if (var7 != 1) {
            if (var7 == 2 && var0.element > 16) {
               WindowManager var11 = var2.mWindowManager;
               if (var11 != null) {
                  WindowManager.LayoutParams var8 = var2.mWmParam;
                  if (var8 != null) {
                     var8.x = (int)var6.getRawX() - var5.getWidth() / 2;
                     var8.y = (int)var6.getRawY() - var5.getHeight() / 2 - var4;
                     Unit var10 = Unit.INSTANCE;
                  } else {
                     var8 = null;
                  }

                  var11.updateViewLayout(var1, (ViewGroup.LayoutParams)var8);
               }
            }
         } else {
            ((ImageView)var1.findViewById(R.id.iv_bubble_icon)).setPressed(false);
            if (var0.element <= 16) {
               OnBubbleClickListener var9 = var3.getOnBubbleClickListener();
               if (var9 != null) {
                  var9.onClick();
               }
            }

            var0.element = 0;
         }
      } else {
         ((ImageView)var1.findViewById(R.id.iv_bubble_icon)).setPressed(true);
      }

      return true;
   }

   private final void showBubble() {
      Log.i("Bubble", "showBubble: mIsShowing " + this.mIsShowing);
      if (!this.mIsShowing) {
         Log.i("Bubble", "showBubble: mWindowManager ----{}" + this.mWindowManager);
         WindowManager var1 = this.mWindowManager;
         if (var1 != null) {
            var1.addView(this.mView, (ViewGroup.LayoutParams)this.mWmParam);
         }

         this.mIsShowing = true;
      }

   }

   public final void updateBubble() {
      if (GeneralBubbleData.isShowBubble) {
         this.showBubble();
      } else {
         this.hideBubble();
      }

   }

   @Metadata(
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"},
      d2 = {"Lcom/hzbhd/canbus/view/Bubble$Companion;", "", "()V", "MOVE_ICON_COUNT", "", "TAG", "", "CanBusInfo_release"},
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
