package com.hzbhd.canbus.car._322;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0006\u001d\u001e\u001f !\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\n\u0010\u0013\u001a\u00060\u0006R\u00020\u0000J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cR\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\u0006R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"},
   d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mActiveParkView", "Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkView;", "mBackActiveParkView", "mIsActiveViewOpen", "", "mLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "kotlin.jvm.PlatformType", "mWindowManager", "Landroid/view/WindowManager;", "addActiveParkView", "", "getActiveParkView", "removeActiveParkView", "setActiveParkActive", "isActive", "updateMessageAndImage", "beam", "Lcom/hzbhd/canbus/car/_322/ActivePark$UpdateBeam;", "updateParkTypeStatus", "value", "", "ActiveParkElement", "ActiveParkView", "Companion", "Option", "UiOption", "UpdateBeam", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ActivePark {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String TAG = "_322_ActivePark";
   private final ActiveParkView mActiveParkView;
   private final ActiveParkView mBackActiveParkView;
   private boolean mIsActiveViewOpen;
   private final WindowManager.LayoutParams mLayoutParams;
   private final ParkPageUiSet mParkPageUiSet;
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
      this.mActiveParkView = new ActiveParkView(this, var1);
      this.mBackActiveParkView = new ActiveParkView(this, var1);
      this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(var1).getParkPageUiSet(var1);
   }

   private final void addActiveParkView() {
      if (!this.mIsActiveViewOpen) {
         this.mWindowManager.addView((View)this.mActiveParkView, (ViewGroup.LayoutParams)this.mLayoutParams);
         this.mIsActiveViewOpen = true;
      }

   }

   private final void removeActiveParkView() {
      if (this.mIsActiveViewOpen) {
         this.mWindowManager.removeView((View)this.mActiveParkView);
         this.mIsActiveViewOpen = false;
      }

   }

   public final ActiveParkView getActiveParkView() {
      return this.mBackActiveParkView;
   }

   public final void setActiveParkActive(boolean var1) {
      if (var1) {
         this.addActiveParkView();
         this.mParkPageUiSet.setShowCusPanoramicView(true);
      } else {
         this.removeActiveParkView();
         this.mParkPageUiSet.setShowCusPanoramicView(false);
      }

   }

   public final void updateMessageAndImage(UpdateBeam var1) {
      Intrinsics.checkNotNullParameter(var1, "beam");
      this.mActiveParkView.clean();
      this.mActiveParkView.updateParkType(var1.getParkType());
      this.mActiveParkView.updateMessage(var1.getMessage1ResId(), var1.getMessage2ResId());
      this.mBackActiveParkView.clean();
      this.mBackActiveParkView.updateParkType(var1.getParkType());
      this.mBackActiveParkView.updateMessage(var1.getMessage1ResId(), var1.getMessage2ResId());
      ActiveParkElement[] var2 = var1.getElements();
      if (var2 != null) {
         this.mActiveParkView.updateImage(var2);
         this.mBackActiveParkView.updateImage(var2);
      }

   }

   public final void updateParkTypeStatus(int var1) {
      this.mActiveParkView.setParkTypeBtnStatus(var1);
      this.mBackActiveParkView.setParkTypeBtnStatus(var1);
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u001b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006\u001c"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;", "", "(Ljava/lang/String;I)V", "WARNING", "FLAG", "STOP", "ARROW_FRONT", "ARROW_BACK", "ARROW_LEFTT", "ARROW_RIGHT", "RADAR_LEFTT", "RADAR_RIGHT", "WALL_PARALLEL_RIGHT", "WALL_PARALLEL_LEFTT", "WALL_VERTICAL_RIGHT_FORWARD", "WALL_VERTICAL_LEFTT_FORWARD", "WALL_VERTICAL_RIGHT_STOP_LINE", "WALL_VERTICAL_LEFTT_STOP_LINE", "SPACE_PARALLEL_RIGHT", "SPACE_PARALLEL_LEFTT", "SPACE_VERTICAL_RIGHT", "SPACE_VERTICAL_LEFTT", "FIND_RIGHT", "FIND_LEFTT", "LINE_PARALLEL_RIGHT", "LINE_PARALLEL_LEFTT", "LINE_VERTICAL_RIGHT", "LINE_VERTICAL_LEFTT", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum ActiveParkElement {
      private static final ActiveParkElement[] $VALUES = $values();
      ARROW_BACK,
      ARROW_FRONT,
      ARROW_LEFTT,
      ARROW_RIGHT,
      FIND_LEFTT,
      FIND_RIGHT,
      FLAG,
      LINE_PARALLEL_LEFTT,
      LINE_PARALLEL_RIGHT,
      LINE_VERTICAL_LEFTT,
      LINE_VERTICAL_RIGHT,
      RADAR_LEFTT,
      RADAR_RIGHT,
      SPACE_PARALLEL_LEFTT,
      SPACE_PARALLEL_RIGHT,
      SPACE_VERTICAL_LEFTT,
      SPACE_VERTICAL_RIGHT,
      STOP,
      WALL_PARALLEL_LEFTT,
      WALL_PARALLEL_RIGHT,
      WALL_VERTICAL_LEFTT_FORWARD,
      WALL_VERTICAL_LEFTT_STOP_LINE,
      WALL_VERTICAL_RIGHT_FORWARD,
      WALL_VERTICAL_RIGHT_STOP_LINE,
      WARNING;

      // $FF: synthetic method
      private static final ActiveParkElement[] $values() {
         return new ActiveParkElement[]{WARNING, FLAG, STOP, ARROW_FRONT, ARROW_BACK, ARROW_LEFTT, ARROW_RIGHT, RADAR_LEFTT, RADAR_RIGHT, WALL_PARALLEL_RIGHT, WALL_PARALLEL_LEFTT, WALL_VERTICAL_RIGHT_FORWARD, WALL_VERTICAL_LEFTT_FORWARD, WALL_VERTICAL_RIGHT_STOP_LINE, WALL_VERTICAL_LEFTT_STOP_LINE, SPACE_PARALLEL_RIGHT, SPACE_PARALLEL_LEFTT, SPACE_VERTICAL_RIGHT, SPACE_VERTICAL_LEFTT, FIND_RIGHT, FIND_LEFTT, LINE_PARALLEL_RIGHT, LINE_PARALLEL_LEFTT, LINE_VERTICAL_RIGHT, LINE_VERTICAL_LEFTT};
      }
   }

   @Metadata(
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0006J\u0019\u0010\u0018\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006J\u000e\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Lcom/hzbhd/canbus/car/_322/ActivePark;Landroid/content/Context;)V", "dp100", "", "dp187", "dp28", "dp32", "dp42", "dp88", "dpMinus119", "dpMinus16", "dpMinus54", "map", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;", "Lcom/hzbhd/canbus/car/_322/ActivePark$UiOption;", "Lkotlin/collections/HashMap;", "clean", "", "setParkTypeBtnStatus", "value", "updateImage", "elements", "", "([Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;)V", "updateMessage", "message1ResId", "message2ResId", "updateParkType", "resId", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class ActiveParkView extends LinearLayout {
      public Map _$_findViewCache;
      private final int dp100;
      private final int dp187;
      private final int dp28;
      private final int dp32;
      private final int dp42;
      private final int dp88;
      private final int dpMinus119;
      private final int dpMinus16;
      private final int dpMinus54;
      private final HashMap map;
      final ActivePark this$0;

      // $FF: synthetic method
      public static void $r8$lambda$cNAcYvZtKdP1_u1RhB4rFCweMug(ActiveParkView var0, View var1) {
         _init_$lambda_1(var0, var1);
      }

      public ActiveParkView(ActivePark var1, Context var2) {
         Intrinsics.checkNotNullParameter(var2, "context");
         this.this$0 = var1;
         this._$_findViewCache = (Map)(new LinkedHashMap());
         super(var2);
         int var3 = var2.getResources().getDimensionPixelOffset(2131171676);
         this.dpMinus119 = var3;
         int var7 = var2.getResources().getDimensionPixelOffset(2131177152);
         this.dpMinus54 = var7;
         int var9 = var2.getResources().getDimensionPixelOffset(2131172587);
         this.dpMinus16 = var9;
         int var4 = var2.getResources().getDimensionPixelOffset(2131169744);
         this.dp32 = var4;
         int var8 = var2.getResources().getDimensionPixelOffset(2131169654);
         this.dp28 = var8;
         int var5 = var2.getResources().getDimensionPixelOffset(2131169966);
         this.dp42 = var5;
         int var6 = var2.getResources().getDimensionPixelOffset(2131170986);
         this.dp88 = var6;
         int var11 = var2.getResources().getDimensionPixelOffset(2131167416);
         this.dp100 = var11;
         int var10 = var2.getResources().getDimensionPixelOffset(2131169346);
         this.dp187 = var10;
         HashMap var13 = new HashMap();
         this.map = var13;
         LayoutInflater.from(var2).inflate(2131558745, (ViewGroup)this);
         ActiveParkElement var12 = ActivePark.ActiveParkElement.WARNING;
         ImageView var15 = (ImageView)this._$_findCachedViewById(R.id.iv_warning);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_warning");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         ActiveParkElement var16 = ActivePark.ActiveParkElement.FLAG;
         ImageView var18 = (ImageView)this._$_findCachedViewById(R.id.iv_flag);
         Intrinsics.checkNotNullExpressionValue(var18, "iv_flag");
         var13.put(var16, new UiOption((View)var18, (Option[])null, 2, (DefaultConstructorMarker)null));
         var16 = ActivePark.ActiveParkElement.STOP;
         var18 = (ImageView)this._$_findCachedViewById(R.id.iv_stop);
         Intrinsics.checkNotNullExpressionValue(var18, "iv_stop");
         var13.put(var16, new UiOption((View)var18, (Option[])null, 2, (DefaultConstructorMarker)null));
         var12 = ActivePark.ActiveParkElement.ARROW_FRONT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_front_arrow);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_front_arrow");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var12 = ActivePark.ActiveParkElement.ARROW_BACK;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_back_arrow);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_back_arrow");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var12 = ActivePark.ActiveParkElement.ARROW_LEFTT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_left_arrow);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_left_arrow");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var12 = ActivePark.ActiveParkElement.ARROW_RIGHT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_right_arrow);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_right_arrow");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var12 = ActivePark.ActiveParkElement.RADAR_RIGHT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_right_radar);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_right_radar");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var12 = ActivePark.ActiveParkElement.RADAR_LEFTT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_left_radar);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_left_radar");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var16 = ActivePark.ActiveParkElement.WALL_PARALLEL_RIGHT;
         LinearLayout var19 = (LinearLayout)this._$_findCachedViewById(R.id.ll_right_wall);
         Intrinsics.checkNotNullExpressionValue(var19, "ll_right_wall");
         var13.put(var16, (new UiOption((View)var19, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Width(var11))).plus((Option)(new Option.MarginTop(var4))));
         var12 = ActivePark.ActiveParkElement.WALL_PARALLEL_LEFTT;
         LinearLayout var17 = (LinearLayout)this._$_findCachedViewById(R.id.ll_left_wall);
         Intrinsics.checkNotNullExpressionValue(var17, "ll_left_wall");
         var13.put(var12, (new UiOption((View)var17, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Width(var11))).plus((Option)(new Option.MarginTop(var4))));
         var12 = ActivePark.ActiveParkElement.WALL_VERTICAL_RIGHT_FORWARD;
         var17 = (LinearLayout)this._$_findCachedViewById(R.id.ll_right_wall);
         Intrinsics.checkNotNullExpressionValue(var17, "ll_right_wall");
         var13.put(var12, (new UiOption((View)var17, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Width(var10))).plus((Option)(new Option.MarginTop(var9))));
         var16 = ActivePark.ActiveParkElement.WALL_VERTICAL_LEFTT_FORWARD;
         var19 = (LinearLayout)this._$_findCachedViewById(R.id.ll_left_wall);
         Intrinsics.checkNotNullExpressionValue(var19, "ll_left_wall");
         var13.put(var16, (new UiOption((View)var19, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Width(var10))).plus((Option)(new Option.MarginTop(var9))));
         var12 = ActivePark.ActiveParkElement.WALL_VERTICAL_RIGHT_STOP_LINE;
         var17 = (LinearLayout)this._$_findCachedViewById(R.id.ll_right_wall);
         Intrinsics.checkNotNullExpressionValue(var17, "ll_right_wall");
         var13.put(var12, (new UiOption((View)var17, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Width(var10))).plus((Option)(new Option.MarginTop(var6))));
         var12 = ActivePark.ActiveParkElement.WALL_VERTICAL_LEFTT_STOP_LINE;
         var17 = (LinearLayout)this._$_findCachedViewById(R.id.ll_left_wall);
         Intrinsics.checkNotNullExpressionValue(var17, "ll_left_wall");
         var13.put(var12, (new UiOption((View)var17, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Width(var10))).plus((Option)(new Option.MarginTop(var6))));
         var16 = ActivePark.ActiveParkElement.SPACE_PARALLEL_RIGHT;
         var19 = (LinearLayout)this._$_findCachedViewById(R.id.ll_right_park_space);
         Intrinsics.checkNotNullExpressionValue(var19, "ll_right_park_space");
         var13.put(var16, (new UiOption((View)var19, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Height(var10))));
         var16 = ActivePark.ActiveParkElement.SPACE_PARALLEL_LEFTT;
         var19 = (LinearLayout)this._$_findCachedViewById(R.id.ll_left_park_space);
         Intrinsics.checkNotNullExpressionValue(var19, "ll_left_park_space");
         var13.put(var16, (new UiOption((View)var19, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Height(var10))));
         var12 = ActivePark.ActiveParkElement.SPACE_VERTICAL_RIGHT;
         var17 = (LinearLayout)this._$_findCachedViewById(R.id.ll_right_park_space);
         Intrinsics.checkNotNullExpressionValue(var17, "ll_right_park_space");
         var13.put(var12, (new UiOption((View)var17, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Height(var11))));
         var16 = ActivePark.ActiveParkElement.SPACE_VERTICAL_LEFTT;
         var19 = (LinearLayout)this._$_findCachedViewById(R.id.ll_left_park_space);
         Intrinsics.checkNotNullExpressionValue(var19, "ll_left_park_space");
         var13.put(var16, (new UiOption((View)var19, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.Height(var11))));
         var12 = ActivePark.ActiveParkElement.FIND_RIGHT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_right_find);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_right_find");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var12 = ActivePark.ActiveParkElement.FIND_LEFTT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_left_find);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_left_find");
         var13.put(var12, new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null));
         var16 = ActivePark.ActiveParkElement.LINE_PARALLEL_RIGHT;
         var18 = (ImageView)this._$_findCachedViewById(R.id.iv_parallel_line);
         Intrinsics.checkNotNullExpressionValue(var18, "iv_parallel_line");
         var13.put(var16, (new UiOption((View)var18, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.MarginEnd(var7))).plus((Option)(new Option.RotationY(0.0F))));
         var16 = ActivePark.ActiveParkElement.LINE_PARALLEL_LEFTT;
         var18 = (ImageView)this._$_findCachedViewById(R.id.iv_parallel_line);
         Intrinsics.checkNotNullExpressionValue(var18, "iv_parallel_line");
         var13.put(var16, (new UiOption((View)var18, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.MarginEnd(var5))).plus((Option)(new Option.RotationY(180.0F))));
         var12 = ActivePark.ActiveParkElement.LINE_VERTICAL_RIGHT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_vertical_line);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_vertical_line");
         var13.put(var12, (new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.MarginEnd(var3))).plus((Option)(new Option.RotationY(180.0F))));
         var12 = ActivePark.ActiveParkElement.LINE_VERTICAL_LEFTT;
         var15 = (ImageView)this._$_findCachedViewById(R.id.iv_vertical_line);
         Intrinsics.checkNotNullExpressionValue(var15, "iv_vertical_line");
         var13.put(var12, (new UiOption((View)var15, (Option[])null, 2, (DefaultConstructorMarker)null)).plus((Option)(new Option.MarginEnd(var8))).plus((Option)(new Option.RotationY(0.0F))));
         ActivePark$ActiveParkView$$ExternalSyntheticLambda0 var14 = new ActivePark$ActiveParkView$$ExternalSyntheticLambda0(this);
         ((TextView)this._$_findCachedViewById(R.id.btn_parallel_park)).setOnClickListener(var14);
         ((TextView)this._$_findCachedViewById(R.id.btn_vertical_park)).setOnClickListener(var14);
         ((TextView)this._$_findCachedViewById(R.id.btn_parallel_park_out)).setOnClickListener(var14);
         ((TextView)this._$_findCachedViewById(R.id.btn_close)).setOnClickListener(var14);
      }

      private static final void _init_$lambda_1(ActiveParkView var0, View var1) {
         Intrinsics.checkNotNullParameter(var0, "this$0");
         if (Intrinsics.areEqual((Object)var1, (Object)((TextView)var0._$_findCachedViewById(R.id.btn_parallel_park)))) {
            CanbusMsgSender.sendMsg(new byte[]{22, -48, 2, 0});
         } else if (Intrinsics.areEqual((Object)var1, (Object)((TextView)var0._$_findCachedViewById(R.id.btn_vertical_park)))) {
            CanbusMsgSender.sendMsg(new byte[]{22, -48, 3, 0});
         } else if (Intrinsics.areEqual((Object)var1, (Object)((TextView)var0._$_findCachedViewById(R.id.btn_parallel_park_out)))) {
            CanbusMsgSender.sendMsg(new byte[]{22, -48, 4, 0});
         } else if (Intrinsics.areEqual((Object)var1, (Object)((TextView)var0._$_findCachedViewById(R.id.btn_close)))) {
            CanbusMsgSender.sendMsg(new byte[]{22, -48, 0, 0});
         }

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

      public final void clean() {
         View var14 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_warning));
         int var1 = 0;
         View var3 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_flag));
         View var5 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_stop));
         View var2 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_front_arrow));
         View var8 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_back_arrow));
         View var7 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_left_arrow));
         View var9 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_right_arrow));
         View var10 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_left_radar));
         View var12 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_right_radar));
         View var15 = (View)((LinearLayout)this._$_findCachedViewById(R.id.ll_left_wall));
         View var11 = (View)((LinearLayout)this._$_findCachedViewById(R.id.ll_right_wall));
         View var4 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_left_find));
         View var13 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_right_find));
         View var6 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_vertical_line));

         for(View var16 = (View)((ImageView)this._$_findCachedViewById(R.id.iv_parallel_line)); var1 < 15; ++var1) {
            (new View[]{var14, var3, var5, var2, var8, var7, var9, var10, var12, var15, var11, var4, var13, var6, var16})[var1].setVisibility(4);
         }

      }

      public final void setParkTypeBtnStatus(int var1) {
         LinearLayout var5 = (LinearLayout)this._$_findCachedViewById(R.id.ll_park_type_btns);
         boolean var4 = false;
         byte var2;
         if (var1 == 0) {
            var2 = 4;
         } else {
            var2 = 0;
         }

         var5.setVisibility(var2);
         TextView var6 = (TextView)this._$_findCachedViewById(R.id.btn_parallel_park);
         boolean var3;
         if (var1 == 2) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setSelected(var3);
         var6 = (TextView)this._$_findCachedViewById(R.id.btn_vertical_park);
         if (var1 == 3) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setSelected(var3);
         var6 = (TextView)this._$_findCachedViewById(R.id.btn_parallel_park_out);
         var3 = var4;
         if (var1 == 4) {
            var3 = true;
         }

         var6.setSelected(var3);
      }

      public final void updateImage(ActiveParkElement[] var1) {
         Intrinsics.checkNotNullParameter(var1, "elements");
         int var4 = var1.length;

         for(int var2 = 0; var2 < var4; ++var2) {
            ActiveParkElement var6 = var1[var2];
            UiOption var7 = (UiOption)this.map.get(var6);
            if (var7 != null) {
               Option[] var11 = var7.getOpts();
               int var5 = var11.length;

               for(int var3 = 0; var3 < var5; ++var3) {
                  Option var8 = var11[var3];
                  if (Intrinsics.areEqual((Object)var8, (Object)ActivePark.Option.Show.INSTANCE)) {
                     var7.getView().setVisibility(0);
                  } else {
                     ViewGroup.LayoutParams var9;
                     View var10;
                     if (var8 instanceof Option.Width) {
                        var10 = var7.getView();
                        var9 = var7.getView().getLayoutParams();
                        var9.width = ((Option.Width)var8).getValue();
                        var10.setLayoutParams(var9);
                     } else if (var8 instanceof Option.Height) {
                        var10 = var7.getView();
                        var9 = var7.getView().getLayoutParams();
                        var9.height = ((Option.Height)var8).getValue();
                        var10.setLayoutParams(var9);
                     } else {
                        View var12;
                        ViewGroup.LayoutParams var13;
                        ViewGroup.MarginLayoutParams var14;
                        if (var8 instanceof Option.MarginTop) {
                           var12 = var7.getView();
                           var13 = var7.getView().getLayoutParams();
                           Intrinsics.checkNotNull(var13, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                           var14 = (ViewGroup.MarginLayoutParams)var13;
                           var14.topMargin = ((Option.MarginTop)var8).getValue();
                           var12.setLayoutParams((ViewGroup.LayoutParams)var14);
                        } else if (var8 instanceof Option.MarginEnd) {
                           var12 = var7.getView();
                           var13 = var7.getView().getLayoutParams();
                           Intrinsics.checkNotNull(var13, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                           var14 = (ViewGroup.MarginLayoutParams)var13;
                           var14.setMarginEnd(((Option.MarginEnd)var8).getValue());
                           var12.setLayoutParams((ViewGroup.LayoutParams)var14);
                        } else if (var8 instanceof Option.RotationY) {
                           var7.getView().setRotationY(((Option.RotationY)var8).getValue());
                        }
                     }
                  }
               }
            }
         }

      }

      public final void updateMessage(int var1, int var2) {
         ((TextView)this._$_findCachedViewById(R.id.tv_message_1)).setText(var1);
         ((TextView)this._$_findCachedViewById(R.id.tv_message_2)).setText(var2);
      }

      public final void updateParkType(int var1) {
         ((TextView)this._$_findCachedViewById(R.id.tv_park_type)).setText(var1);
      }
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Companion;", "", "()V", "TAG", "", "CanBusInfo_release"},
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

   @Metadata(
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0006\t\n\u000b\f\r\u000e¨\u0006\u000f"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "", "()V", "Height", "MarginEnd", "MarginTop", "RotationY", "Show", "Width", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option$Height;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option$MarginEnd;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option$MarginTop;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option$RotationY;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option$Show;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option$Width;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public abstract static class Option {
      private Option() {
      }

      // $FF: synthetic method
      public Option(DefaultConstructorMarker var1) {
         this();
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Option$Height;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "value", "", "(I)V", "getValue", "()I", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class Height extends Option {
         private final int value;

         public Height(int var1) {
            super((DefaultConstructorMarker)null);
            this.value = var1;
         }

         public final int getValue() {
            return this.value;
         }
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Option$MarginEnd;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "value", "", "(I)V", "getValue", "()I", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class MarginEnd extends Option {
         private final int value;

         public MarginEnd(int var1) {
            super((DefaultConstructorMarker)null);
            this.value = var1;
         }

         public final int getValue() {
            return this.value;
         }
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Option$MarginTop;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "value", "", "(I)V", "getValue", "()I", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class MarginTop extends Option {
         private final int value;

         public MarginTop(int var1) {
            super((DefaultConstructorMarker)null);
            this.value = var1;
         }

         public final int getValue() {
            return this.value;
         }
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Option$RotationY;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "value", "", "(F)V", "getValue", "()F", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class RotationY extends Option {
         private final float value;

         public RotationY(float var1) {
            super((DefaultConstructorMarker)null);
            this.value = var1;
         }

         public final float getValue() {
            return this.value;
         }
      }

      @Metadata(
         d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"},
         d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Option$Show;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "()V", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class Show extends Option {
         public static final Show INSTANCE = new Show();

         private Show() {
            super((DefaultConstructorMarker)null);
         }
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
         d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$Option$Width;", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "value", "", "(I)V", "getValue", "()I", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class Width extends Option {
         private final int value;

         public Width(int var1) {
            super((DefaultConstructorMarker)null);
            this.value = var1;
         }

         public final int getValue() {
            return this.value;
         }
      }
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006H\u0086\u0002R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$UiOption;", "", "view", "Landroid/view/View;", "opts", "", "Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "(Landroid/view/View;[Lcom/hzbhd/canbus/car/_322/ActivePark$Option;)V", "getOpts", "()[Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "[Lcom/hzbhd/canbus/car/_322/ActivePark$Option;", "getView", "()Landroid/view/View;", "plus", "opt", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class UiOption {
      private final Option[] opts;
      private final View view;

      public UiOption(View var1, Option[] var2) {
         Intrinsics.checkNotNullParameter(var1, "view");
         Intrinsics.checkNotNullParameter(var2, "opts");
         super();
         this.view = var1;
         this.opts = var2;
      }

      // $FF: synthetic method
      public UiOption(View var1, Option[] var2, int var3, DefaultConstructorMarker var4) {
         if ((var3 & 2) != 0) {
            var2 = new Option[]{(Option)ActivePark.Option.Show.INSTANCE};
         }

         this(var1, var2);
      }

      public final Option[] getOpts() {
         return this.opts;
      }

      public final View getView() {
         return this.view;
      }

      public final UiOption plus(Option var1) {
         Intrinsics.checkNotNullParameter(var1, "opt");
         return new UiOption(this.view, (Option[])ArraysKt.plus(this.opts, var1));
      }
   }

   @Metadata(
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\tB%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\u0010\fB\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\rB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000b¢\u0006\u0002\u0010\u000eJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0010J>\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u001b\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006\""},
      d2 = {"Lcom/hzbhd/canbus/car/_322/ActivePark$UpdateBeam;", "", "parkType", "", "message1ResId", "message2ResId", "element", "Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;", "(IIILcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;)V", "(II)V", "elements", "", "(II[Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;)V", "(IILcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;)V", "(III[Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;)V", "getElements", "()[Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;", "[Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;", "getMessage1ResId", "()I", "getMessage2ResId", "getParkType", "component1", "component2", "component3", "component4", "copy", "(III[Lcom/hzbhd/canbus/car/_322/ActivePark$ActiveParkElement;)Lcom/hzbhd/canbus/car/_322/ActivePark$UpdateBeam;", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class UpdateBeam {
      private final ActiveParkElement[] elements;
      private final int message1ResId;
      private final int message2ResId;
      private final int parkType;

      public UpdateBeam(int var1, int var2) {
         this(var1, var2, 2131769421, (ActiveParkElement[])null);
      }

      public UpdateBeam(int var1, int var2, int var3, ActiveParkElement var4) {
         Intrinsics.checkNotNullParameter(var4, "element");
         this(var1, var2, var3, new ActiveParkElement[]{var4});
      }

      public UpdateBeam(int var1, int var2, int var3, ActiveParkElement[] var4) {
         this.parkType = var1;
         this.message1ResId = var2;
         this.message2ResId = var3;
         this.elements = var4;
      }

      public UpdateBeam(int var1, int var2, ActiveParkElement var3) {
         Intrinsics.checkNotNullParameter(var3, "element");
         this(var1, var2, new ActiveParkElement[]{var3});
      }

      public UpdateBeam(int var1, int var2, ActiveParkElement[] var3) {
         Intrinsics.checkNotNullParameter(var3, "elements");
         this(var1, var2, 2131769421, var3);
      }

      // $FF: synthetic method
      public static UpdateBeam copy$default(UpdateBeam var0, int var1, int var2, int var3, ActiveParkElement[] var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = var0.parkType;
         }

         if ((var5 & 2) != 0) {
            var2 = var0.message1ResId;
         }

         if ((var5 & 4) != 0) {
            var3 = var0.message2ResId;
         }

         if ((var5 & 8) != 0) {
            var4 = var0.elements;
         }

         return var0.copy(var1, var2, var3, var4);
      }

      public final int component1() {
         return this.parkType;
      }

      public final int component2() {
         return this.message1ResId;
      }

      public final int component3() {
         return this.message2ResId;
      }

      public final ActiveParkElement[] component4() {
         return this.elements;
      }

      public final UpdateBeam copy(int var1, int var2, int var3, ActiveParkElement[] var4) {
         return new UpdateBeam(var1, var2, var3, var4);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof UpdateBeam)) {
            return false;
         } else {
            UpdateBeam var2 = (UpdateBeam)var1;
            if (this.parkType != var2.parkType) {
               return false;
            } else if (this.message1ResId != var2.message1ResId) {
               return false;
            } else if (this.message2ResId != var2.message2ResId) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.elements, (Object)var2.elements);
            }
         }
      }

      public final ActiveParkElement[] getElements() {
         return this.elements;
      }

      public final int getMessage1ResId() {
         return this.message1ResId;
      }

      public final int getMessage2ResId() {
         return this.message2ResId;
      }

      public final int getParkType() {
         return this.parkType;
      }

      public int hashCode() {
         int var4 = Integer.hashCode(this.parkType);
         int var3 = Integer.hashCode(this.message1ResId);
         int var2 = Integer.hashCode(this.message2ResId);
         ActiveParkElement[] var5 = this.elements;
         int var1;
         if (var5 == null) {
            var1 = 0;
         } else {
            var1 = Arrays.hashCode(var5);
         }

         return ((var4 * 31 + var3) * 31 + var2) * 31 + var1;
      }

      public String toString() {
         return "UpdateBeam(parkType=" + this.parkType + ", message1ResId=" + this.message1ResId + ", message2ResId=" + this.message2ResId + ", elements=" + Arrays.toString(this.elements) + ')';
      }
   }
}
