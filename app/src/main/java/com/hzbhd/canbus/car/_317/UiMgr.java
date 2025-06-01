package com.hzbhd.canbus.car._317;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"},
   d2 = {"Lcom/hzbhd/canbus/car/_317/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirAddition", "", "mMsgMgr", "Lcom/hzbhd/canbus/car/_317/MsgMgr;", "getAdditionalParam", "sendAirCommand", "", "event", "action", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private int mAirAddition;
   private MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static void $r8$lambda$Dv8lC6onWtdRsG_KQ1amRBoIwh8(UiMgr var0, FrontArea var1, int var2) {
      lambda_11$lambda_10$lambda_7(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$J_UZMRDAmKKZR1O713mqQBY7iSc(SettingPageUiSet var0, int var1, int var2) {
      lambda_5$lambda_4(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$LsWhuWm0MLgDu_YFzntyIthrkZ4(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4, int var5) {
      lambda_5$lambda_1(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static void $r8$lambda$N2iLH4E1GDd3x1XdFjqcIc2_38c(UiMgr var0, FrontArea var1, int var2) {
      lambda_11$lambda_10$lambda_6(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$T58mMWHzjXHZGCsD6GuKjPD3VXw(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_5$lambda_2(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$eSiQ7xyxC87OFYa4J_OhcGXO2ts(UiMgr var0, FrontArea var1, int var2) {
      lambda_11$lambda_10$lambda_8(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$eToPU5Xu1k1lafXEqBTiP_LwfQA(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_5$lambda_3(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$nCARdVnIuWz0Qpx1oTKNrdw7GUU(UiMgr var0, FrontArea var1, int var2) {
      lambda_11$lambda_10$lambda_9(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      this.mAirAddition = 204;
      MsgMgrInterface var3 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._317.MsgMgr");
      this.mMsgMgr = (MsgMgr)var3;
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 11)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var2 + 11)});
            }

         }

         @Metadata(
            k = 3,
            mv = {1, 7, 1},
            xi = 48
         )
         public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
               int[] var0 = new int[AmplifierActivity.AmplifierPosition.values().length];
               var0[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
               var0[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      var4.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
            }

         }

         @Metadata(
            k = 3,
            mv = {1, 7, 1},
            xi = 48
         )
         public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
               int[] var0 = new int[AmplifierActivity.AmplifierBand.values().length];
               var0[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
               var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
               var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
               var0[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0(var5, this, var1));
      var5.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda1(var5));
      var5.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda2(var5));
      var5.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda3(var5));
      FrontArea var6 = this.getAirUiSet(var1).getFrontArea();
      var6.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda4(this, var6), new UiMgr$$ExternalSyntheticLambda5(this, var6), new UiMgr$$ExternalSyntheticLambda6(this, var6), new UiMgr$$ExternalSyntheticLambda7(this, var6)});
      var6.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(10);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(9);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(12);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(11);
         }
      })});
      var6.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(21);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(20);
         }
      }));
      int var2 = this.getCurrentCarId();
      if (var2 != 1) {
         this.removeFrontAirButtonByName(var1, "rear");
      }

      if (var2 != 2) {
         this.removeMainPrjBtnByName(var1, "tire_info");
         this.removeMainPrjBtnByName(var1, "drive_data");
      }

   }

   private final int getAdditionalParam() {
      int var1 = this.mAirAddition;
      if (var1 == 34) {
         this.mAirAddition = 204;
      } else if (var1 == 204) {
         this.mAirAddition = 34;
      }

      return this.mAirAddition;
   }

   private static final void lambda_11$lambda_10$lambda_6(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "it.lineBtnAction[0][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_11$lambda_10$lambda_7(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "it.lineBtnAction[1][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_11$lambda_10$lambda_8(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "it.lineBtnAction[2][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_11$lambda_10$lambda_9(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "it.lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_5$lambda_1(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4, int var5) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$context");
      if (var6 != null) {
         switch (var6) {
            case "outlander_simple_car_set_10":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, (byte)var5});
               break;
            case "outlander_simple_car_set_11":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 13, (byte)var5});
               break;
            case "outlander_simple_car_set_12":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 14, (byte)var5});
               break;
            case "outlander_simple_car_set_17":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)var5});
               break;
            case "_283_choose":
               CanbusMsgSender.sendMsg(new byte[]{22, -18, (byte)var5, 0});
               MsgMgr var7 = var1.mMsgMgr;
               Intrinsics.checkNotNull(var7);
               var7.updateSettingItem("_283_choose", var5);
               SharePreUtil.setIntValue(var2, "share_317_left_right_hand", var5);
               break;
            case "outlander_simple_car_set_8":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte)var5});
               break;
            case "outlander_simple_car_set_9":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte)var5});
         }
      }

   }

   private static final void lambda_5$lambda_2(SettingPageUiSet var0, int var1, int var2, int var3) {
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"amplifier_switch")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
      }

   }

   private static final void lambda_5$lambda_3(SettingPageUiSet var0, int var1, int var2, int var3) {
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"_103_punch")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)(var3 + 3 + 2)});
      }

   }

   private static final void lambda_5$lambda_4(SettingPageUiSet var0, int var1, int var2) {
      if (var3 != null) {
         switch (var3) {
            case "outlander_simple_car_set_14":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 16, 0});
               break;
            case "outlander_simple_car_set_15":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, 0});
               break;
            case "outlander_simple_car_set_16":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 18, 0});
         }
      }

   }

   private final void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte)var1, (byte)this.getAdditionalParam(), 0, 0});
   }

   private final void sendAirCommand(String var1) {
      switch (var1) {
         case "rear_defog":
            this.sendAirCommand(4);
            break;
         case "blow_positive":
            this.sendAirCommand(17);
            break;
         case "max_front":
            this.sendAirCommand(38);
            break;
         case "ac":
            this.sendAirCommand(16);
            break;
         case "auto":
            this.sendAirCommand(2);
            break;
         case "rear":
            this.sendAirCommand(37);
            break;
         case "sync":
            this.sendAirCommand(8);
            break;
         case "power":
            this.sendAirCommand(1);
            break;
         case "in_out_cycle":
            this.sendAirCommand(35);
      }

   }
}
