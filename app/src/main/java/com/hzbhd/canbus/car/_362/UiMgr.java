package com.hzbhd.canbus.car._362;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\u001c\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00020\u0018\"\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0014H\u0002J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"},
   d2 = {"Lcom/hzbhd/canbus/car/_362/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "amplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getAmplifierPageUiSet", "()Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "send83", "", "data0", "", "data1", "send84", "send8B", "", "", "sendE0", "d0", "sendE2", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet airPageUiSet;
   private final AmplifierPageUiSet amplifierPageUiSet;
   private final SettingPageUiSet settingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$13VFSvRYYeHGLuDMUuNzqlQ3wVo(UiMgr var0, int var1, int var2, int var3) {
      _init_$lambda_6(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$506hh7Tc_GUg9JCtdx4TK5QJMV8(UiMgr var0, int var1, int var2, int var3) {
      _init_$lambda_5(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$6uiB3j5pFcHWtqssW7kbJPil_3c(UiMgr var0, int var1) {
      _init_$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$H_AfvQ5umH7DfoMjxO0wydXIATU(UiMgr var0, int var1) {
      _init_$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$_cejZLiXBfa9ZUsbAmfU2ixEd54(UiMgr var0, Context var1, int var2, int var3) {
      _init_$lambda_4(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$m5dlD9qhK9QxNe94meNHef4LIZ0(UiMgr var0, int var1, int var2, int var3) {
      _init_$lambda_2(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$txNfjhL7vW85iru9MHlA_oxFkt4(UiMgr var0, int var1, int var2) {
      _init_$lambda_3(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNull(var2);
      this.settingPageUiSet = var2;
      AirPageUiSet var4 = this.getAirUiSet(var1);
      Intrinsics.checkNotNull(var4);
      this.airPageUiSet = var4;
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getAmplifierPageUiSet(context)");
      this.amplifierPageUiSet = var3;
      this.send8B(1);
      FrontArea var5 = var4.getFrontArea();
      if (var5 != null) {
         var5.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this), null, null, new UiMgr$$ExternalSyntheticLambda1(this)});
      }

      var5 = var4.getFrontArea();
      if (var5 != null) {
         var5.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickLeft() {
               this.this$0.sendE0(9);
            }

            public void onClickRight() {
               this.this$0.sendE0(10);
            }
         }));
      }

      var5 = var4.getFrontArea();
      if (var5 != null) {
         var5.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.sendE0(2);
            }

            public void onClickUp() {
               this.this$0.sendE0(3);
            }
         }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.sendE0(4);
            }

            public void onClickUp() {
               this.this$0.sendE0(5);
            }
         })});
      }

      var5 = var4.getFrontArea();
      if (var5 != null) {
         var5.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener() {
            private int i;
            private final int[] mode = new int[]{32, 33};

            public final int getI() {
               return this.i;
            }

            public final int[] getMode() {
               return this.mode;
            }

            public void onLeftSeatClick() {
            }

            public void onRightSeatClick() {
            }

            public final void setI(int var1) {
               this.i = var1;
            }
         }));
      }

      FrontArea var6 = var4.getFrontArea();
      if (var6 != null) {
         var6.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.sendE0(11);
            }
         }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.sendE0(13);
            }
         })});
      }

      var2.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda2(this));
      var2.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda3(this));
      var2.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda4(this, var1));
      var2.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda5(this));
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda6(this));
      var3.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            var2 = DataHandleUtils.rangeNumber(var2 + 7, 0, 14);
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  this.this$0.send84(2, var2);
               }
            } else {
               this.this$0.send84(1, MsgMgrKt.reverse$default(var2, 0, 2, (Object)null));
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
      var3.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = DataHandleUtils.rangeNumber(var2 + 2, 2, 12);
            int var4 = DataHandleUtils.rangeNumber(var2, 0, 63);
            var2 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 == 4) {
                        this.this$0.send84(7, var4);
                     }
                  } else {
                     this.this$0.send84(6, var3);
                  }
               } else {
                  this.this$0.send84(5, var3);
               }
            } else {
               this.this$0.send84(4, var3);
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
   }

   private static final void _init_$lambda_0(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var2 != null) {
         switch (var2) {
            case "front_window_heat":
               var0.sendE0(22);
               break;
            case "power":
               var0.sendE0(1);
               break;
            case "negative_ion":
               var0.sendE0(17);
               break;
            case "in_out_cycle":
               var0.sendE0(25);
         }
      }

   }

   private static final void _init_$lambda_1(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var2 = var0.airPageUiSet.getFrontArea().getLineBtnAction()[3][var1];
      if (var2 != null) {
         var1 = var2.hashCode();
         if (var1 != -246396018) {
            if (var1 != 3106) {
               if (var1 == 3005871 && var2.equals("auto")) {
                  var0.sendE0(21);
               }
            } else if (var2.equals("ac")) {
               var0.sendE0(23);
            }
         } else if (var2.equals("max_front")) {
            var0.sendE0(19);
         }
      }

   }

   private static final void _init_$lambda_2(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var4 != null) {
         var2 = var4.hashCode();
         byte var5 = 8;
         switch (var2) {
            case -1417652657:
               if (var4.equals("S362_powerAmplifierInfo_1")) {
                  if (var3 != 1) {
                     var5 = 1;
                  }

                  var0.send84(3, var5);
               }
               break;
            case 213507944:
               if (var4.equals("S362_drivingModeAndAssistanceInfo_1")) {
                  var0.send8B(3);
               }
               break;
            case 213507946:
               if (var4.equals("S362_drivingModeAndAssistanceInfo_3")) {
                  var0.send8B(4);
               }
               break;
            case 213507947:
               if (var4.equals("S362_drivingModeAndAssistanceInfo_4")) {
                  var0.send8B(5);
               }
               break;
            case 213507948:
               if (var4.equals("S362_drivingModeAndAssistanceInfo_5")) {
                  var0.send8B(9, 1);
               }
               break;
            case 213507950:
               if (var4.equals("S362_drivingModeAndAssistanceInfo_7")) {
                  var0.send8B(8);
               }
               break;
            case 465785566:
               if (var4.equals("S362_vehicleSettings_1")) {
                  var0.send83(16, var3);
               }
               break;
            case 465785567:
               if (var4.equals("S362_vehicleSettings_2")) {
                  var0.send83(17, var3);
               }
               break;
            case 465785568:
               if (var4.equals("S362_vehicleSettings_3")) {
                  var0.send83(18, var3);
               }
               break;
            case 465785569:
               if (var4.equals("S362_vehicleSettings_4")) {
                  var0.send83(19, var3);
               }
               break;
            case 1168298533:
               if (var4.equals("S361_d0b7")) {
                  var0.send83(4, var3);
               }
               break;
            case 1168299491:
               if (var4.equals("S361_d1b4")) {
                  var0.send83(3, var3);
               }
               break;
            case 1168299492:
               if (var4.equals("S361_d1b5")) {
                  var0.send83(2, var3);
               }
               break;
            case 1168299493:
               if (var4.equals("S361_d1b6")) {
                  var0.send83(1, var3);
               }
               break;
            case 1168299494:
               if (var4.equals("S361_d1b7")) {
                  var0.send83(0, var3);
               }
               break;
            case 1168300454:
               if (var4.equals("S361_d2b6")) {
                  var0.send83(14, var3);
               }
               break;
            case 1168300455:
               if (var4.equals("S361_d2b7")) {
                  var0.send83(13, var3);
               }
         }
      }

   }

   private static final void _init_$lambda_3(UiMgr var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
   }

   private static final void _init_$lambda_4(UiMgr var0, Context var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$context");
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.settingPageUiSet.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var4, (Object)"S362_car_1")) {
         var0.sendE2(0);
      } else if (Intrinsics.areEqual((Object)var4, (Object)"S362_car_2")) {
         var0.sendE2(1);
      }

      var0.removeSettingLeftItemByNameList(var1, new String[]{"S361_car_title"});
      MsgMgrInterface var5 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type com.hzbhd.canbus.car._362.MsgMgr");
      ((MsgMgr)var5).returnClick();
   }

   private static final void _init_$lambda_5(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var4 != null) {
         switch (var4) {
            case "S362_vehicleSpeedInfo_1":
               CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)(var3 * 10)});
               break;
            case "S362_carSpeedInfo_1":
               CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)(var3 * 10)});
               break;
            case "S361_d0b6t4":
               var0.send83(6, var3);
               break;
            case "S361_d1b0t2":
               var0.send83(5, var3);
         }
      }

   }

   private static final void _init_$lambda_6(UiMgr var0, int var1, int var2, int var3) {
      // $FF: Couldn't be decompiled
   }

   private final void send83(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   private final void send84(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   private final void send8B(int var1, byte... var2) {
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -117, (byte)var1}, var2));
   }

   private final void sendE0(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      Thread.sleep(100L);
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 0});
   }

   private final void sendE2(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1});
   }

   public final AirPageUiSet getAirPageUiSet() {
      return this.airPageUiSet;
   }

   public final AmplifierPageUiSet getAmplifierPageUiSet() {
      return this.amplifierPageUiSet;
   }

   public final SettingPageUiSet getSettingPageUiSet() {
      return this.settingPageUiSet;
   }
}
