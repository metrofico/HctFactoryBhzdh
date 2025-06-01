package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003*+,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u000eJ\b\u0010\u001c\u001a\u00020\u0017H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\nJ\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001aH\u0002J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001aH\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\u0006\u0010&\u001a\u00020\u0017J\u000e\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"},
   d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mActiveParkView", "Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkView;", "mHandler", "Landroid/os/Handler;", "mIsActiveViewOpen", "", "mLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "mPanoramicView", "Lcom/hzbhd/canbus/car/_94/ActivePark$MyPanoramicView;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "kotlin.jvm.PlatformType", "mTimerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "mWindowManager", "Landroid/view/WindowManager;", "closeActiveParkView", "", "countDown", "messageResId", "", "getPanoramicView", "hideAlertWindow", "openActiveParkView", "setActiveParkActive", "isActive", "setAlertMessage", "resid", "text", "", "setTipMessage", "showAlertWindow", "stopTimer", "update", "beam", "Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkBeam;", "ActiveParkBeam", "ActiveParkView", "MyPanoramicView", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ActivePark {
   private final ActiveParkView mActiveParkView;
   private final Handler mHandler;
   private boolean mIsActiveViewOpen;
   private final WindowManager.LayoutParams mLayoutParams;
   private final MyPanoramicView mPanoramicView;
   private final ParkPageUiSet mParkPageUiSet;
   private final TimerUtil mTimerUtil;
   private final WindowManager mWindowManager;

   public ActivePark(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      Object var2 = var1.getSystemService("window");
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type android.view.WindowManager");
      this.mWindowManager = (WindowManager)var2;
      WindowManager.LayoutParams var3 = new WindowManager.LayoutParams();
      var3.type = 2002;
      var3.gravity = 17;
      var3.width = -1;
      var3.height = -1;
      this.mLayoutParams = var3;
      this.mActiveParkView = new ActiveParkView(var1);
      this.mPanoramicView = new MyPanoramicView(var1);
      this.mHandler = new Handler(Looper.getMainLooper());
      this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(var1).getParkPageUiSet(var1);
      this.mTimerUtil = new TimerUtil();
   }

   private final void closeActiveParkView() {
      if (this.mIsActiveViewOpen) {
         this.mWindowManager.removeView((View)this.mActiveParkView);
         this.mIsActiveViewOpen = false;
      }

   }

   private final void hideAlertWindow() {
      this.mActiveParkView.hideAlertWindow();
      this.mPanoramicView.hideAlertWindow();
   }

   private final void openActiveParkView() {
      if (!this.mIsActiveViewOpen) {
         this.mWindowManager.addView((View)this.mActiveParkView, (ViewGroup.LayoutParams)this.mLayoutParams);
         this.mIsActiveViewOpen = true;
      }

   }

   private final void setAlertMessage(int var1) {
      this.mActiveParkView.setAlertMessage(var1);
      this.mPanoramicView.setAlertMessage(var1);
   }

   private final void setAlertMessage(String var1) {
      this.mActiveParkView.setAlertMessage(var1);
      this.mPanoramicView.setAlertMessage(var1);
   }

   private final void setTipMessage(int var1) {
      this.mActiveParkView.setTipMessage(var1);
      this.mPanoramicView.setTipMessage(var1);
   }

   private final void showAlertWindow() {
      this.mActiveParkView.showAlertWindow();
      this.mPanoramicView.showAlertWindow();
   }

   public final void countDown(Context var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      TimerUtil var3 = this.mTimerUtil;
      var3.startTimer((TimerTask)(new TimerTask(var1, var2, var3, this) {
         final TimerUtil $this_run;
         private int count;
         private final String second;
         private final String text;
         final ActivePark this$0;

         // $FF: synthetic method
         public static void $r8$lambda$mlKmNvz6FiJQUUjh_6lISTw9lLQ(ActivePark var0, String var1) {
            run$lambda_1$lambda_0(var0, var1);
         }

         {
            this.$this_run = var3;
            this.this$0 = var4;
            this.text = var1.getString(var2);
            this.count = 10;
            this.second = CommUtil.getStrByResId(var1, "_197_second");
         }

         private static final void run$lambda_1$lambda_0(ActivePark var0, String var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$it");
            var0.setAlertMessage(var1);
         }

         public final int getCount() {
            return this.count;
         }

         public final String getSecond() {
            return this.second;
         }

         public final String getText() {
            return this.text;
         }

         public void run() {
            if (this.count >= 0) {
               String var2 = this.text + '\n' + this.count + this.second;
               ActivePark var1 = this.this$0;
               var1.mHandler.post(new ActivePark$countDown$1$1$$ExternalSyntheticLambda0(var1, var2));
               --this.count;
            } else {
               this.$this_run.stopTimer();
            }

         }

         public final void setCount(int var1) {
            this.count = var1;
         }
      }), 0L, 1000L);
   }

   public final MyPanoramicView getPanoramicView() {
      return this.mPanoramicView;
   }

   public final void setActiveParkActive(boolean var1) {
      if (var1) {
         this.openActiveParkView();
         this.mParkPageUiSet.setShowCusPanoramicView(true);
      } else {
         this.closeActiveParkView();
         this.mParkPageUiSet.setShowCusPanoramicView(false);
      }

   }

   public final void stopTimer() {
      this.mTimerUtil.stopTimer();
   }

   public final void update(ActiveParkBeam var1) {
      Intrinsics.checkNotNullParameter(var1, "beam");
      this.hideAlertWindow();
      if (var1.getLeftImageResId() == null && var1.getRightImageResId() == null) {
         this.showAlertWindow();
         this.setAlertMessage(var1.getMessageResId());
      } else {
         ActiveParkView var4 = this.mActiveParkView;
         Integer var5 = var1.getLeftImageResId();
         int var3 = 2131233621;
         int var2;
         if (var5 != null) {
            var2 = var5;
         } else {
            var2 = 2131233621;
         }

         var4.setLeftSideImage(var2);
         var4 = this.mActiveParkView;
         var5 = var1.getRightImageResId();
         var2 = var3;
         if (var5 != null) {
            var2 = var5;
         }

         var4.setRightSideImage(var2);
         MyPanoramicView var6 = this.mPanoramicView;
         String var8 = var1.getReverseIcon();
         String var7 = var8;
         if (var8 == null) {
            var7 = "";
         }

         var6.refreshIcon(var7);
         this.setTipMessage(var1.getMessageResId());
      }

   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J<\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\bHÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001d"},
      d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkBeam;", "", "messageResId", "", "(I)V", "leftImageResId", "rightImageResId", "reverseIcon", "", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getLeftImageResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessageResId", "()I", "getReverseIcon", "()Ljava/lang/String;", "getRightImageResId", "component1", "component2", "component3", "component4", "copy", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkBeam;", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class ActiveParkBeam {
      private final Integer leftImageResId;
      private final int messageResId;
      private final String reverseIcon;
      private final Integer rightImageResId;

      public ActiveParkBeam(int var1) {
         this(var1, (Integer)null, (Integer)null, (String)null);
      }

      public ActiveParkBeam(int var1, Integer var2, Integer var3, String var4) {
         this.messageResId = var1;
         this.leftImageResId = var2;
         this.rightImageResId = var3;
         this.reverseIcon = var4;
      }

      // $FF: synthetic method
      public static ActiveParkBeam copy$default(ActiveParkBeam var0, int var1, Integer var2, Integer var3, String var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = var0.messageResId;
         }

         if ((var5 & 2) != 0) {
            var2 = var0.leftImageResId;
         }

         if ((var5 & 4) != 0) {
            var3 = var0.rightImageResId;
         }

         if ((var5 & 8) != 0) {
            var4 = var0.reverseIcon;
         }

         return var0.copy(var1, var2, var3, var4);
      }

      public final int component1() {
         return this.messageResId;
      }

      public final Integer component2() {
         return this.leftImageResId;
      }

      public final Integer component3() {
         return this.rightImageResId;
      }

      public final String component4() {
         return this.reverseIcon;
      }

      public final ActiveParkBeam copy(int var1, Integer var2, Integer var3, String var4) {
         return new ActiveParkBeam(var1, var2, var3, var4);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof ActiveParkBeam)) {
            return false;
         } else {
            ActiveParkBeam var2 = (ActiveParkBeam)var1;
            if (this.messageResId != var2.messageResId) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.leftImageResId, (Object)var2.leftImageResId)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.rightImageResId, (Object)var2.rightImageResId)) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.reverseIcon, (Object)var2.reverseIcon);
            }
         }
      }

      public final Integer getLeftImageResId() {
         return this.leftImageResId;
      }

      public final int getMessageResId() {
         return this.messageResId;
      }

      public final String getReverseIcon() {
         return this.reverseIcon;
      }

      public final Integer getRightImageResId() {
         return this.rightImageResId;
      }

      public int hashCode() {
         int var4 = Integer.hashCode(this.messageResId);
         Integer var5 = this.leftImageResId;
         int var3 = 0;
         int var1;
         if (var5 == null) {
            var1 = 0;
         } else {
            var1 = var5.hashCode();
         }

         var5 = this.rightImageResId;
         int var2;
         if (var5 == null) {
            var2 = 0;
         } else {
            var2 = var5.hashCode();
         }

         String var6 = this.reverseIcon;
         if (var6 != null) {
            var3 = var6.hashCode();
         }

         return ((var4 * 31 + var1) * 31 + var2) * 31 + var3;
      }

      public String toString() {
         return "ActiveParkBeam(messageResId=" + this.messageResId + ", leftImageResId=" + this.leftImageResId + ", rightImageResId=" + this.rightImageResId + ", reverseIcon=" + this.reverseIcon + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\u000f\u001a\u00020\u0006¨\u0006\u0010"},
      d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark$ActiveParkView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hideAlertWindow", "", "setAlertMessage", "resid", "", "text", "", "setLeftSideImage", "setRightSideImage", "setTipMessage", "showAlertWindow", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class ActiveParkView extends LinearLayout {
      public Map _$_findViewCache;

      public ActiveParkView(Context var1) {
         this._$_findViewCache = (Map)(new LinkedHashMap());
         super(var1);
         LayoutInflater.from(var1).inflate(2131558746, (ViewGroup)this);
      }

      public void _$_clearFindViewByIdCache() {
         this._$_findViewCache.clear();
      }

      public View _$_findCachedViewById(int var1) {
         Map var4 = this._$_findViewCache;
         View var3 = (View)var4.get(var1);
         View var2 = var3;
         if (var3 == null) {
            var2 = this.findViewById(var1);
            if (var2 != null) {
               var4.put(var1, var2);
            } else {
               var2 = null;
            }
         }

         return var2;
      }

      public final void hideAlertWindow() {
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_alert)).setVisibility(8);
      }

      public final void setAlertMessage(int var1) {
         ((TextView)this._$_findCachedViewById(R.id.tv_alert_message)).setText(var1);
      }

      public final void setAlertMessage(String var1) {
         Intrinsics.checkNotNullParameter(var1, "text");
         ((TextView)this._$_findCachedViewById(R.id.tv_alert_message)).setText((CharSequence)var1);
      }

      public final void setLeftSideImage(int var1) {
         ((ImageView)this._$_findCachedViewById(R.id.iv_left_side)).setImageResource(var1);
      }

      public final void setRightSideImage(int var1) {
         ((ImageView)this._$_findCachedViewById(R.id.iv_right_side)).setImageResource(var1);
      }

      public final void setTipMessage(int var1) {
         ((TextView)this._$_findCachedViewById(R.id.tv_message)).setText(var1);
      }

      public final void showAlertWindow() {
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_alert)).setVisibility(0);
      }
   }

   @Metadata(
      d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0006¨\u0006\u0016"},
      d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark$MyPanoramicView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hideAlertWindow", "", "refreshIcon", "name", "", "setAlertMessage", "resid", "", "text", "setTipMessage", "setVisible", "view", "Landroid/view/View;", "visible", "", "showAlertWindow", "Companion", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class MyPanoramicView extends RelativeLayout {
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      public static final String LEFT_BACKWARD = "left_backward";
      public static final String LEFT_FORWARD = "left_forward";
      public static final String LEFT_RADAR = "left_radar";
      public static final String LEFT_REVERSE = "left_reverse";
      public static final String LEFT_STOP = "left_stop";
      public static final String RIGHT_RADAR = "right_radar";
      public Map _$_findViewCache;

      public MyPanoramicView(Context var1) {
         this._$_findViewCache = (Map)(new LinkedHashMap());
         super(var1);
         LayoutInflater.from(var1).inflate(2131558808, (ViewGroup)this);
      }

      public void _$_clearFindViewByIdCache() {
         this._$_findViewCache.clear();
      }

      public View _$_findCachedViewById(int var1) {
         Map var4 = this._$_findViewCache;
         View var3 = (View)var4.get(var1);
         View var2 = var3;
         if (var3 == null) {
            var2 = this.findViewById(var1);
            if (var2 != null) {
               var4.put(var1, var2);
            } else {
               var2 = null;
            }
         }

         return var2;
      }

      public final void hideAlertWindow() {
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_alert)).setVisibility(8);
      }

      public final void refreshIcon(String var1) {
         Intrinsics.checkNotNullParameter(var1, "name");
         ImageView var2 = (ImageView)this._$_findCachedViewById(R.id.iv_left_radar);
         Intrinsics.checkNotNullExpressionValue(var2, "iv_left_radar");
         this.setVisible((View)var2, Intrinsics.areEqual((Object)"left_radar", (Object)var1));
         var2 = (ImageView)this._$_findCachedViewById(R.id.iv_right_radar);
         Intrinsics.checkNotNullExpressionValue(var2, "iv_right_radar");
         this.setVisible((View)var2, Intrinsics.areEqual((Object)"right_radar", (Object)var1));
         var2 = (ImageView)this._$_findCachedViewById(R.id.iv_left_forward);
         Intrinsics.checkNotNullExpressionValue(var2, "iv_left_forward");
         this.setVisible((View)var2, Intrinsics.areEqual((Object)"left_forward", (Object)var1));
         var2 = (ImageView)this._$_findCachedViewById(R.id.iv_left_backward);
         Intrinsics.checkNotNullExpressionValue(var2, "iv_left_backward");
         this.setVisible((View)var2, Intrinsics.areEqual((Object)"left_backward", (Object)var1));
         var2 = (ImageView)this._$_findCachedViewById(R.id.iv_left_stop);
         Intrinsics.checkNotNullExpressionValue(var2, "iv_left_stop");
         this.setVisible((View)var2, Intrinsics.areEqual((Object)"left_stop", (Object)var1));
         TextView var3 = (TextView)this._$_findCachedViewById(R.id.tv_left_reverse);
         Intrinsics.checkNotNullExpressionValue(var3, "tv_left_reverse");
         this.setVisible((View)var3, Intrinsics.areEqual((Object)"left_reverse", (Object)var1));
      }

      public final void setAlertMessage(int var1) {
         ((TextView)this._$_findCachedViewById(R.id.tv_alert_message)).setText(var1);
      }

      public final void setAlertMessage(String var1) {
         Intrinsics.checkNotNullParameter(var1, "text");
         ((TextView)this._$_findCachedViewById(R.id.tv_alert_message)).setText((CharSequence)var1);
      }

      public final void setTipMessage(int var1) {
         ((TextView)this._$_findCachedViewById(R.id.tv_message)).setText(var1);
      }

      public final void setVisible(View var1, boolean var2) {
         Intrinsics.checkNotNullParameter(var1, "view");
         if (var2) {
            var1.setVisibility(0);
         } else {
            var1.setVisibility(8);
         }

      }

      public final void showAlertWindow() {
         ((LinearLayout)this._$_findCachedViewById(R.id.ll_alert)).setVisibility(0);
      }

      @Metadata(
         d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"},
         d2 = {"Lcom/hzbhd/canbus/car/_94/ActivePark$MyPanoramicView$Companion;", "", "()V", "LEFT_BACKWARD", "", "LEFT_FORWARD", "LEFT_RADAR", "LEFT_REVERSE", "LEFT_STOP", "RIGHT_RADAR", "CanBusInfo_release"},
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
}
