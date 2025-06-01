package com.hzbhd.canbus.car._329;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnBubbleClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/canbus/car/_329/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mHandler", "Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_329/MsgMgr;", "sendAirCommand", "", "command", "", "title", "", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String SHARE_329_IS_SUPPORT_PANORAMIC = "share_329_is_support_panoramic";
   private static final String TAG = "_329_UiMgr";
   private final Handler mHandler;
   private final MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static void $r8$lambda$1_Ce2so9SsKeU7nYKkxx5nEol04(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_4$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$6dV_EOJwytJSUoREalzOpURLmwc(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_4$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$EMZ5iZ4A3xTdNDteK_OPcAh1_jg(ParkPageUiSet var0, byte[] var1, int var2) {
      lambda_36$lambda_32(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$TaBNvmmTTKDNERTU0GrxbtEP4MU(SettingPageUiSet var0, int var1, int var2) {
      lambda_24$lambda_21(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$aDcjLHIQvtg8MvubGFstEGpScdE(SettingPageUiSet var0, byte[] var1, int var2, int var3, int var4) {
      lambda_24$lambda_20(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$jokPJX41lqYVWGD7CcgXoD7XAZg(SettingPageUiSet var0, byte[] var1, int var2, int var3, int var4) {
      lambda_24$lambda_17(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$oHL0Uc2LKD_I4VTXQSJA0mJmTPw(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_4$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$oMle8bTOnnmJ5DanI_bb9MUZhds(SettingPageUiSet var0, byte[] var1, Context var2, UiMgr var3, int var4, int var5, int var6) {
      lambda_24$lambda_12(var0, var1, var2, var3, var4, var5, var6);
   }

   // $FF: synthetic method
   public static void $r8$lambda$q4H3TVTIRI6xRNs0tMFxe_f8hGo(int var0, int var1, byte[] var2, MotionEvent var3) {
      lambda_36$lambda_35(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$sNKy1_y0asuApr4nLpXhAoibsTc(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_4$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$u1z4nVGcYruaj13ymNOOezyV8u0(SettingPageUiSet var0, byte[] var1, int var2, int var3) {
      lambda_24$lambda_23(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      this.mHandler = new Handler(Looper.getMainLooper());
      MsgMgrInterface var3 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._329.MsgMgr");
      this.mMsgMgr = (MsgMgr)var3;
      byte[] var6 = new byte[]{22, -3, 0, 0};
      FrontArea var4 = this.getAirUiSet(var1).getFrontArea();
      UiMgr$$ExternalSyntheticLambda0 var5 = new UiMgr$$ExternalSyntheticLambda0(this, var4);
      boolean var2 = false;
      var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{var5, new UiMgr$$ExternalSyntheticLambda3(this, var4), new UiMgr$$ExternalSyntheticLambda4(this, var4), new UiMgr$$ExternalSyntheticLambda5(this, var4)});
      var4.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(14);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(13);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(16);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(15);
         }
      })});
      var4.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(12);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(11);
         }
      }));
      var4.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(17);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(18);
         }
      }), null, null});
      SettingPageUiSet var7 = this.getSettingUiSet(var1);
      byte[] var9 = new byte[]{22, -116, 0, 0};
      var7.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda6(var7, var9, var1, this));
      var7.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda7(var7, var9));
      var7.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda8(var7, var9));
      var7.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda9(var7));
      var7.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda10(var7, var6));
      ParkPageUiSet var8 = this.getParkPageUiSet(var1);
      if (SharePreUtil.getIntValue(var1, "share_329_is_support_panoramic", 0) == 1) {
         var2 = true;
      }

      var8.setShowPanoramic(var2);
      if (var8.isShowPanoramic()) {
         var8.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda1(var8, var6));
         var8.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda2(var1.getResources().getDisplayMetrics().widthPixels, var1.getResources().getDisplayMetrics().heightPixels, new byte[]{22, 44, 0, 0, 0, 0, 0, 2}));
      }

      this.getBubbleUiSet(var1).setOnBubbleClickListener((OnBubbleClickListener)(new OnBubbleClickListener(var6) {
         final byte[] $m0xFDCommand;

         {
            this.$m0xFDCommand = var1;
         }

         public void onClick() {
            byte[] var1 = this.$m0xFDCommand;
            var1[2] = 1;
            var1[3] = 1;
            CanbusMsgSender.sendMsg(var1);
         }
      }));
   }

   private static final void lambda_24$lambda_12(SettingPageUiSet var0, byte[] var1, Context var2, UiMgr var3, int var4, int var5, int var6) {
      Intrinsics.checkNotNullParameter(var1, "$m0x8CCommand");
      Intrinsics.checkNotNullParameter(var2, "$context");
      Intrinsics.checkNotNullParameter(var3, "this$0");
      String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var4)).getItemList().get(var5)).getTitleSrn();
      if (var8 != null) {
         var4 = var8.hashCode();
         boolean var7 = true;
         switch (var4) {
            case -463273934:
               if (var8.equals("_329_setting_10")) {
                  var1[2] = 3;
                  var1[3] = (byte)(var6 + 1);
                  CanbusMsgSender.sendMsg(var1);
               }
               break;
            case -14944322:
               if (var8.equals("_329_setting_1")) {
                  var1[2] = 7;
                  var1[3] = (byte)var6;
                  CanbusMsgSender.sendMsg(var1);
               }
               break;
            case -14944321:
               if (var8.equals("_329_setting_2")) {
                  var1[2] = 8;
                  var1[3] = (byte)var6;
                  CanbusMsgSender.sendMsg(var1);
               }
               break;
            case -14944320:
               if (var8.equals("_329_setting_3")) {
                  var1[2] = 9;
                  var1[3] = (byte)var6;
                  CanbusMsgSender.sendMsg(var1);
               }
               break;
            case -14944318:
               if (var8.equals("_329_setting_5")) {
                  var1[2] = 11;
                  var1[3] = (byte)var6;
                  CanbusMsgSender.sendMsg(var1);
               }
               break;
            case -14944314:
               if (var8.equals("_329_setting_9")) {
                  var1[2] = 6;
                  var1[3] = (byte)(var6 + 1);
                  CanbusMsgSender.sendMsg(var1);
               }
               break;
            case 712683749:
               if (var8.equals("support_panorama")) {
                  SharePreUtil.setIntValue(var2, "share_329_is_support_panoramic", var6);
                  var3.mMsgMgr.updateSettingItem("support_panorama", var6);
                  MsgMgr var9 = var3.mMsgMgr;
                  if (var6 != 1) {
                     var7 = false;
                  }

                  var9.updateBubble(var2, var7);
               }
         }
      }

   }

   private static final void lambda_24$lambda_17(SettingPageUiSet var0, byte[] var1, int var2, int var3, int var4) {
      // $FF: Couldn't be decompiled
   }

   private static final void lambda_24$lambda_20(SettingPageUiSet var0, byte[] var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "$m0x8CCommand");
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var5, (Object)"_329_setting_11")) {
         var1[2] = 4;
         var1[3] = (byte)var4;
         CanbusMsgSender.sendMsg(var1);
      } else if (Intrinsics.areEqual((Object)var5, (Object)"_329_setting_12")) {
         var1[2] = 5;
         var1[3] = (byte)var4;
         CanbusMsgSender.sendMsg(var1);
      }

   }

   private static final void lambda_24$lambda_21(SettingPageUiSet var0, int var1, int var2) {
      ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
   }

   private static final void lambda_24$lambda_23(SettingPageUiSet var0, byte[] var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "$m0xFDCommand");
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn(), (Object)"_55_0xE8_data4")) {
         var1[2] = 1;
         var1[3] = 1;
         CanbusMsgSender.sendMsg(var1);
      }

   }

   private static final void lambda_36$lambda_32(ParkPageUiSet var0, byte[] var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "$m0xFDCommand");
      if (var3 != null) {
         switch (var3) {
            case "_194_front":
               var1[2] = 3;
               var1[3] = 4;
               CanbusMsgSender.sendMsg(var1);
               break;
            case "_194_right":
               var1[2] = 3;
               var1[3] = 7;
               CanbusMsgSender.sendMsg(var1);
               break;
            case "_194_2d":
               var1[2] = 4;
               var1[3] = 1;
               CanbusMsgSender.sendMsg(var1);
               break;
            case "_194_3d":
               var1[2] = 4;
               var1[3] = 2;
               CanbusMsgSender.sendMsg(var1);
               break;
            case "_194_exit":
               var1[2] = 1;
               var1[3] = 0;
               CanbusMsgSender.sendMsg(var1);
               break;
            case "_194_left":
               var1[2] = 3;
               var1[3] = 6;
               CanbusMsgSender.sendMsg(var1);
               break;
            case "_194_rear":
               var1[2] = 3;
               var1[3] = 5;
               CanbusMsgSender.sendMsg(var1);
         }
      }

   }

   private static final void lambda_36$lambda_35(int var0, int var1, byte[] var2, MotionEvent var3) {
      Intrinsics.checkNotNullParameter(var2, "$mTouchCommand");
      Pair var4 = new Pair(var3.getX() * (float)800 / (float)var0, var3.getY() * (float)480 / (float)var1);
      var1 = var3.getAction();
      byte var5 = 1;
      if (var1 != 0) {
         if (var1 != 1) {
            return;
         }
      } else {
         var5 = 2;
      }

      var2[2] = (byte)var5;
      var2[3] = (byte)((int)((Number)var4.getFirst()).floatValue() >> 8 & 255);
      var2[4] = (byte)((int)((Number)var4.getFirst()).floatValue() & 255);
      var2[5] = (byte)((int)((Number)var4.getSecond()).floatValue() >> 8 & 255);
      var2[6] = (byte)((int)((Number)var4.getSecond()).floatValue() & 255);
      CanbusMsgSender.sendMsg(var2);
   }

   private static final void lambda_5$lambda_4$lambda_0(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[0][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_5$lambda_4$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[1][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_5$lambda_4$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[2][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_5$lambda_4$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private final void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)var1, 1});
   }

   private final void sendAirCommand(String var1) {
      switch (var1) {
         case "front_defog":
            this.sendAirCommand(5);
            break;
         case "rear_defog":
            this.sendAirCommand(6);
            break;
         case "blow_positive":
            this.sendAirCommand(21);
            break;
         case "blow_negative":
            this.sendAirCommand(22);
            break;
         case "ac":
            this.sendAirCommand(2);
            break;
         case "auto":
            this.sendAirCommand(4);
            break;
         case "dual":
            this.sendAirCommand(41);
            break;
         case "power":
            this.sendAirCommand(1);
            break;
         case "in_out_cycle":
            this.sendAirCommand(7);
      }

   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/canbus/car/_329/UiMgr$Companion;", "", "()V", "SHARE_329_IS_SUPPORT_PANORAMIC", "", "TAG", "CanBusInfo_release"},
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
