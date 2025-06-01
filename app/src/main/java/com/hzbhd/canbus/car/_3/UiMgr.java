package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0014\u0010\u0013\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J\f\u0010\u0015\u001a\u00020\u000f*\u00020\u0016H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"},
   d2 = {"Lcom/hzbhd/canbus/car/_3/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mHandler", "Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_3/MsgMgr;", "mPanoramicView", "Lcom/hzbhd/canbus/car/_3/MyPanoramicView;", "getCusPanoramicView", "sendAirCommand", "", "command", "", "param", "title", "", "cycle", "max", "switch", "", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_3_UiMgr";
   private final Handler mHandler;
   private final MsgMgr mMsgMgr;
   private final MyPanoramicView mPanoramicView;

   // $FF: synthetic method
   public static void $r8$lambda$DFxR2jhZtvOci9xJlpDdnxVYrB0(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_12$lambda_9(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$TP1gnnCO57pX7YcJqsVTlY7_1DI(UiMgr var0, RearArea var1, int var2) {
      lambda_6$lambda_5$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ZJ9wFQDmRUMn6ld23avd6FZpz_M(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_12$lambda_7(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$bbbWpC4NUvyVpDx9eZCgFg_R_dw(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      lambda_12$lambda_8(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$cw8Z_gsEmodgCgV77ZkFcFyu7Hc(SettingPageUiSet var0, int var1, int var2) {
      lambda_12$lambda_10(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$d53ksfo8nT9Ui3B2BA7xzGQE7Uc(UiMgr var0, FrontArea var1, int var2, int var3) {
      lambda_6$lambda_1$lambda_0(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$evPmH_awtUP3Lo3BydkcWD7llaU(ParkPageUiSet var0, UiMgr var1, Context var2, int var3) {
      lambda_14$lambda_13(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static String $r8$lambda$pmlxKU98Aocers0ls04YYqHUOe8(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      return lambda_12$lambda_11(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$xShlhAsSwTbdx_h9tirSFX_H3MM(UiMgr var0, RearArea var1, int var2) {
      lambda_6$lambda_5$lambda_2(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      MsgMgrInterface var3 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._3.MsgMgr");
      this.mMsgMgr = (MsgMgr)var3;
      this.mHandler = new Handler(Looper.getMainLooper());
      this.mPanoramicView = new MyPanoramicView(var1);
      AirPageUiSet var5 = this.getAirUiSet(var1);
      FrontArea var7 = var5.getFrontArea();
      OnAirBtnClickListener[] var4 = new OnAirBtnClickListener[4];

      for(int var2 = 0; var2 < 4; ++var2) {
         var4[var2] = new UiMgr$$ExternalSyntheticLambda0(this, var7, var2);
      }

      var7.setOnAirBtnClickListeners(var4);
      var7.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(184, 0);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(184, 1);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(185, 0);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(185, 1);
         }
      })});
      var7.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this, var7) {
         final FrontArea $this_apply;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.$this_apply = var2;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(183, RangesKt.coerceAtLeast(GeneralAirData.front_wind_level - 1, 0));
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(183, RangesKt.coerceAtMost(GeneralAirData.front_wind_level + 1, this.$this_apply.getWindMaxLevel()));
         }
      }));
      var7.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this, var7) {
         final FrontArea $this_apply;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.$this_apply = var2;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr var1 = this.this$0;
            var1.sendAirCommand(173, var1.cycle(GeneralAirData.front_left_seat_heat_level, this.$this_apply.getSeatHeatSrnArray().length - 1));
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this, var7) {
         final FrontArea $this_apply;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.$this_apply = var2;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr var1 = this.this$0;
            var1.sendAirCommand(174, var1.cycle(GeneralAirData.front_right_seat_heat_level, this.$this_apply.getSeatHeatSrnArray().length - 1));
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this, var7) {
         final FrontArea $this_apply;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.$this_apply = var2;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr var1 = this.this$0;
            var1.sendAirCommand(191, var1.cycle(GeneralAirData.front_left_seat_cold_level, this.$this_apply.getSeatColdSrnArray().length - 1));
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this, var7) {
         final FrontArea $this_apply;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.$this_apply = var2;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr var1 = this.this$0;
            var1.sendAirCommand(192, var1.cycle(GeneralAirData.front_right_seat_cold_level, this.$this_apply.getSeatColdSrnArray().length - 1));
         }
      })});
      RearArea var8 = var5.getRearArea();
      var8.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda1(this, var8), null, null, new UiMgr$$ExternalSyntheticLambda2(this, var8)});
      var8.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(186, 0);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(186, 1);
         }
      }, null}));
      var8.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this, var8) {
         final RearArea $this_apply;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.$this_apply = var2;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(40, RangesKt.coerceAtLeast(GeneralAirData.rear_wind_level - 1, 0));
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(40, RangesKt.coerceAtMost(GeneralAirData.rear_wind_level + 1, this.$this_apply.getWindMaxLevel()));
         }
      }));
      SettingPageUiSet var9 = this.getSettingUiSet(var1);
      var9.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda3(var9));
      var9.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda4(var9, this));
      var9.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda5(var9));
      var9.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda6(var9));
      var9.setOnSettingItemSeekbarSetTextListener(new UiMgr$$ExternalSyntheticLambda7(var9, var1));
      ParkPageUiSet var10 = this.getParkPageUiSet(var1);
      var10.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda8(var10, this, var1));
      AmplifierPageUiSet var6 = this.getAmplifierPageUiSet(var1);
      var6.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        return;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, (byte)var2});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, (byte)var2});
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
               var0[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
               var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
               var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
               var0[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 4;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      var6.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, (byte)(var2 + 9)});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, (byte)(var2 + 9)});
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
   }

   private final int cycle(int var1, int var2) {
      if (var1 >= var2) {
         var1 = 0;
      } else {
         ++var1;
      }

      return var1;
   }

   private static final void lambda_12$lambda_10(SettingPageUiSet var0, int var1, int var2) {
      if (var3 != null) {
         switch (var3) {
            case "vm_golf7_vehicle_setup_opening_and_closing":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -59, 1});
               break;
            case "vm_golf7_vehicle_setup_multifuction_display":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -58, 1});
               break;
            case "vm_golf7_vehicle_setup_mfd_reset_since_start":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -118, 1});
               break;
            case "vm_golf7_vehicle_setup_parking_and_manoeuvring":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -62, 1});
               break;
            case "electric_drive_charging_settings":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -54, 1});
               break;
            case "tpms_set":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, 1});
               break;
            case "vm_golf7_Conv_consumers_prompt_1":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, 1});
               break;
            case "background_lighting":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -49, 1});
               break;
            case "off_road_reset":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -24, 1});
               break;
            case "drive_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, 1});
               break;
            case "_307_value_26":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, 1});
               break;
            case "assign_key":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, 1});
               break;
            case "_2_setting_14":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -50, 1});
               break;
            case "vm_golf7_vehicle_setup_mirror_and_wipers":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -60, 1});
               break;
            case "individual_reset":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -44, 1});
               break;
            case "reset_all_setting":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -57, 1});
               break;
            case "vm_golf7_vehicle_setup_mfd_reset_long_term":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -117, 1});
               break;
            case "reset_maintenance_interval":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -115, 1});
               break;
            case "vm_golf7_vehicle_setup_light":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -61, 1});
         }
      }

   }

   private static final String lambda_12$lambda_11(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "$context");
      String var6;
      if (var5 != null) {
         switch (var5) {
            case "vm_golf7_vehicle_setup_light_coming_home":
               var6 = String.valueOf(var4 * 5);
               return var6;
            case "vm_golf7_vehicle_setup_light_ins_swi_lighting":
               var6 = String.valueOf(var4 * 10);
               return var6;
            case "first_color":
               var6 = CommUtil.getStrByResId(var1, "_270_setting_12") + ' ' + var4;
               return var6;
            case "vehicle_tem_in":
               if (var4 == 0) {
                  var6 = "LO";
                  return var6;
               } else {
                  if (var4 == ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getMax()) {
                     var6 = "HI";
                  } else {
                     var6 = "" + (float)(var4 + 31) / 2.0F + '℃';
                  }

                  return var6;
               }
               break;
            case "_3_40h_20h_p2":
               var6 = String.valueOf(var4 * MsgMgr.SpeedAlertHelper.INSTANCE.getSpeedUnit().getStep());
               return var6;
            case "second_color":
               var6 = CommUtil.getStrByResId(var1, "_270_setting_12") + ' ' + var4;
               return var6;
            case "vm_golf7_vehicle_setup_light_all_area_light":
               var6 = String.valueOf(var4 * 10);
               return var6;
            case "battery_charge_lower_limit":
               var6 = String.valueOf(var4 * 10);
               return var6;
            case "vm_golf7_vehicle_setup_light_door_ambient_light":
               var6 = String.valueOf(var4 * 10);
               return var6;
            case "vm_golf7_vehicle_setup_light_footwell_light":
               var6 = String.valueOf(var4 * 10);
               return var6;
            case "vm_golf7_vehicle_setup_light_car_front_light":
               var6 = String.valueOf(var4 * 10);
               return var6;
            case "vm_golf7_vehicle_setup_light_car_roof_light":
               var6 = String.valueOf(var4 * 10);
               return var6;
            case "vm_golf7_vehicle_setup_light_leaving_home":
               var6 = String.valueOf(var4 * 5);
               return var6;
            case "vm_golf7_vehicle_setup_light_handle_box_light":
               var6 = String.valueOf(var4 * 10);
               return var6;
         }
      }

      var6 = String.valueOf(var4);
      return var6;
   }

   private static final void lambda_12$lambda_7(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var7 != null) {
         int var6 = var7.hashCode();
         byte var5 = 4;
         byte var8 = 0;
         byte var4 = 0;
         var1 = 1;
         byte var9;
         switch (var6) {
            case -2043604067:
               if (var7.equals("vm_golf7_vehicle_setup_units_temperature")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -110, (byte)var3});
               }
               break;
            case -2014552680:
               if (var7.equals("_2_settings_individual_engine")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -46, (byte)var3});
               }
               break;
            case -1912662420:
               if (var7.equals("vm_golf7_vehicle_setup_units_distance")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte)var3});
               }
               break;
            case -1879314128:
               if (var7.equals("vm_golf7_vehicle_setup_environment_color")) {
                  if (var3 != 0) {
                     var9 = var5;
                     if (var3 != 1) {
                        if (var3 != 2) {
                           var9 = 0;
                        } else {
                           var9 = 5;
                        }
                     }
                  } else {
                     var9 = 1;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 76, (byte)var9});
               }
               break;
            case -1836094980:
               if (var7.equals("_2_settings_offroad_air_conditioning")) {
                  var9 = var8;
                  if (var3 == 1) {
                     var9 = 2;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -28, (byte)var9});
               }
               break;
            case -1500272463:
               if (var7.equals("_2_settings_offroad_steering")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -31, (byte)var3});
               }
               break;
            case -969556744:
               if (var7.equals("vm_golf7_vehicle_setup_light_travel_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 86, (byte)var3});
               }
               break;
            case -722743688:
               if (var7.equals("_3_40h_31h_p3_b32")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte)var3});
               }
               break;
            case -717170286:
               if (var7.equals("_2_settings_individual_air_conditioning")) {
                  var9 = var4;
                  if (var3 == 1) {
                     var9 = 2;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -45, (byte)var9});
               }
               break;
            case -433870697:
               if (var7.equals("_3_21h_d8_b65")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte)var3});
               }
               break;
            case -374590123:
               if (var7.equals("on_off_btn_txt_7")) {
                  if (var3 != 0) {
                     var1 = var3 + 2;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 101, (byte)var1});
               }
               break;
            case -264532014:
               if (var7.equals("vm_golf7_vehicle_setup_light_switch_on_time")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte)var3});
               }
               break;
            case -240318347:
               if (var7.equals("_2_setting_0")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte)var3});
               }
               break;
            case -189627282:
               if (var7.equals("dashboard_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -23, (byte)(var3 + 2)});
               }
               break;
            case -158538558:
               if (var7.equals("vm_golf7_vehicle_setup_open_close_door_unlocking")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 113, (byte)var3});
               }
               break;
            case 10536655:
               if (var7.equals("key_assign")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)var3});
               }
               break;
            case 64386538:
               if (var7.equals("maximum_charging_current")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -15, (byte)var3});
               }
               break;
            case 194708627:
               if (var7.equals("_29_right_side")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -21, (byte)var3});
               }
               break;
            case 296809711:
               if (var7.equals("_3_40h_70h_p1_b30")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 112, (byte)var3});
               }
               break;
            case 348332473:
               if (var7.equals("user_account")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)(var3 + 1)});
               }
               break;
            case 554554329:
               if (var7.equals("vm_power_consumption")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, (byte)var3});
               }
               break;
            case 606404938:
               if (var7.equals("_2_settings_offroad_drive")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -30, (byte)var3});
               }
               break;
            case 633766017:
               if (var7.equals("_2_settings_acc_drive_program")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte)var3});
               }
               break;
            case 643894855:
               if (var7.equals("_2_settings_individual_steering")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte)var3});
               }
               break;
            case 669958842:
               if (var7.equals("_283_title_2")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte)var3});
               }
               break;
            case 669958843:
               if (var7.equals("_283_title_3")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
               }
               break;
            case 817602748:
               if (var7.equals("vm_golf7_vehicle_setup_units_pressure")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -107, (byte)var3});
               }
               break;
            case 961699958:
               if (var7.equals("_29_left_side")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -22, (byte)var3});
               }
               break;
            case 1081491908:
               if (var7.equals("vm_golf7_vehicle_setup_units_consumption")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -108, (byte)var3});
               }
               break;
            case 1302419692:
               if (var7.equals("vm_golf7_vehicle_setup_hybrid_emode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -16, (byte)var3});
               }
               break;
            case 1349952337:
               if (var7.equals("vm_golf7_vehicle_setup_units_volume")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -109, (byte)var3});
               }
               break;
            case 1371742195:
               if (var7.equals("_3_40h_52h_p3_b50")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 94, (byte)var3});
               }
               break;
            case 1371742263:
               if (var7.equals("_3_40h_52h_p3_b76")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 74, (byte)var3});
               }
               break;
            case 1806178504:
               if (var7.equals("vm_golf7_language_setup")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)((Number)MsgMgr.Companion.getLanguageList().get(var3)).intValue()});
               }
               break;
            case 1835219203:
               if (var7.equals("_2_settings_acc_distance")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte)var3});
               }
               break;
            case 1841914160:
               if (var7.equals("vm_golf7_vehicle_setup_units_speed")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -111, (byte)var3});
               }
               break;
            case 2037033549:
               if (var7.equals("_2_settings_offroad_four_wheel_drive")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -29, (byte)var3});
               }
               break;
            case 2129434926:
               if (var7.equals("_2_settings_individual_cornering_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -43, (byte)var3});
               }
         }
      }

   }

   private static final void lambda_12$lambda_8(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var5 != null) {
         switch (var5) {
            case "vm_golf7_vehicle_setup_mirror_wipers_auto_wiping":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 98, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_travelling_time":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -124, (byte)var4});
               break;
            case "_2_settings_lower_while_reversing":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_eco_tips":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -125, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_distance_travelled":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -123, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_current_consumption":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -128, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_driver_assistance_lane_assisit":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte)var4});
               break;
            case "inductive_trunk_lid":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 116, (byte)var4});
               break;
            case "parking_brake_function":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte)var4});
               break;
            case "driving_out_of_the_parking_space":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_con_consumers":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -126, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_avg_consumption":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -127, (byte)var4});
               break;
            case "_303_setting_content_11":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 102, (byte)var4});
               break;
            case "_303_setting_content_12":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 103, (byte)var4});
               break;
            case "_303_setting_content_13":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 104, (byte)var4});
               break;
            case "hillDescentAssist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -27, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_avg_speed":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -122, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_open_close_auto_locking":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 114, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_background_lighting":
               if (var4 != 0) {
                  if (var4 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 76, (byte)var1.mMsgMgr.getMColour()});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 76, 0});
               }
               break;
            case "parkingAssist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -25, (byte)var4});
               break;
            case "_283_car_setting_light_4":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -56, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mirror_wipers_rear_window_wiping":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 99, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_driver_assistance_last_distance_selected":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_speed_warning":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -120, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_parking_active_auto":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte)var4});
               break;
            case "individual":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte)var4});
               break;
            case "_3_40h_31h_p3_b0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte)var4});
               break;
            case "_2_settings_syncchronous_adjustment":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)var4});
               break;
            case "_3_40h_20h_p1_b0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_light_auto_control":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 81, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_oil_temperature":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -119, (byte)var4});
               break;
            case "two_color_sync":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 79, (byte)(1 - var4)});
               break;
            case "vm_golf7_vehicle_setup_light_lane_change_flash":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 82, (byte)var4});
               break;
            case "_3_21h_d8_b7":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_mfd_digital_speed_display":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -121, (byte)var4});
               break;
            case "_2_setting_2_0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var4});
               break;
            case "_2_setting_9_5":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 115, (byte)var4});
               break;
            case "_2_setting_9_6":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 117, (byte)var4});
               break;
            case "air_conditioning_is_powered_by_battery":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -13, (byte)var4});
               break;
            case "driver_seat":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -55, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_light_dynamic_light_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 90, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_light_dynamic_light_follow":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 89, (byte)var4});
               break;
            case "_3_40h_60h_p2_b0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 100, (byte)var4});
               break;
            case "hillHoidAssist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -26, (byte)var4});
               break;
            case "vm_golf7_vehicle_setup_service_wiper_in_maintenance_position":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -116, (byte)var4});
               break;
            case "_3_40h_10h_p2_b7":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, (byte)var4});
               break;
            case "_283_car_setting_pa_1":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte)var4});
               break;
            case "_283_car_setting_pa_2":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte)var4});
               break;
            case "_283_car_setting_pa_3":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte)var4});
               break;
            case "_283_car_setting_pa_6":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var4});
               break;
            case "_283_car_setting_pa_7":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 61, (byte)var4});
               break;
            case "_283_car_setting_pa_8":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 62, (byte)var4});
               break;
            case "seat_remote_key_memory_matching":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -53, (byte)var4});
         }
      }

   }

   private static final void lambda_12$lambda_9(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var6 != null) {
         int var5 = var6.hashCode();
         byte var4 = 0;
         switch (var5) {
            case -2038625043:
               if (var6.equals("vm_golf7_vehicle_setup_light_coming_home")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 84, (byte)(var3 * 5)});
               }
               break;
            case -1595882384:
               if (var6.equals("vm_golf7_vehicle_setup_parking_rear_tone_setting")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte)var3});
               }
               break;
            case -1435138704:
               if (var6.equals("headlight_illumination_distance_adjustment")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 95, (byte)var3});
               }
               break;
            case -1158124569:
               if (var6.equals("vm_golf7_vehicle_setup_parking_front_tone_setting")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var3});
               }
               break;
            case -1087223577:
               if (var6.equals("vm_golf7_vehicle_setup_light_ins_swi_lighting")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 83, (byte)(var3 * 10)});
               }
               break;
            case -965356345:
               if (var6.equals("vm_golf7_vehicle_setup_parking_rear_volume")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte)var3});
               }
               break;
            case -705322732:
               if (var6.equals("first_color")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 78, (byte)var3});
               }
               break;
            case -702173029:
               if (var6.equals("vehicle_tem_in")) {
                  if (var3 == 0) {
                     var1 = var4;
                  } else if (var3 == ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getMax()) {
                     var1 = 255;
                  } else {
                     var1 = (var3 + 31) * 5 - 100;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -14, (byte)var1});
               }
               break;
            case -458021931:
               if (var6.equals("_3_40h_20h_p2")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)(var3 * MsgMgr.SpeedAlertHelper.INSTANCE.getSpeedUnit().getStep())});
               }
               break;
            case -376524840:
               if (var6.equals("second_color")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 78, (byte)(var3 & 127 | 128)});
               }
               break;
            case 53943538:
               if (var6.equals("vm_golf7_vehicle_setup_light_all_area_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 93, (byte)(var3 * 10)});
               }
               break;
            case 155644670:
               if (var6.equals("lane_assist_system_brightness")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 63, (byte)(var3 - 1)});
               }
               break;
            case 205955940:
               if (var6.equals("battery_charge_lower_limit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -12, (byte)(var3 * 10)});
               }
               break;
            case 332079758:
               if (var6.equals("vm_golf7_vehicle_setup_light_door_ambient_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 87, (byte)(var3 * 10)});
               }
               break;
            case 392307070:
               if (var6.equals("vm_golf7_vehicle_setup_parking_front_volume")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)var3});
               }
               break;
            case 433034755:
               if (var6.equals("vm_golf7_vehicle_setup_light_footwell_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 88, (byte)(var3 * 10)});
               }
               break;
            case 878703845:
               if (var6.equals("vm_golf7_vehicle_setup_light_car_front_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 92, (byte)(var3 * 10)});
               }
               break;
            case 1222207462:
               if (var6.equals("vm_golf7_vehicle_setup_light_car_roof_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 91, (byte)(var3 * 10)});
               }
               break;
            case 1233601850:
               if (var6.equals("vm_golf7_vehicle_setup_light_leaving_home")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte)(var3 * 5)});
               }
               break;
            case 1285394220:
               if (var6.equals("_143_0xAD_setting6")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, (byte)var3});
               }
               break;
            case 1415745787:
               if (var6.equals("vm_golf7_vehicle_setup_light_handle_box_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 77, (byte)(var3 * 10)});
               }
         }
      }

   }

   private static final void lambda_14$lambda_13(ParkPageUiSet var0, UiMgr var1, Context var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$context");
      String var4 = ((ParkPageUiSet.Bean)var0.getPanoramicBtnList().get(var3)).getTitleSrn();
      if (var4 != null) {
         var3 = var4.hashCode();
         switch (var3) {
            case -1932462487:
               if (var4.equals("_253_front_view")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 0});
               }
               break;
            case -1705162449:
               if (var4.equals("_250_exit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 105, 0});
               }
               break;
            case -1483394203:
               if (var4.equals("_94_parallel_parking")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 1});
               }
               break;
            case -1219351889:
               if (var4.equals("_253_left_view")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 1});
               }
               break;
            case -404582956:
               if (var4.equals("_94_vertical_parking")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 0});
               }
               break;
            case 621013810:
               if (var4.equals("_253_rear_view")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 2});
               }
               break;
            case 1298895446:
               if (var4.equals("_253_right_view")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 3});
               }
               break;
            case 1981264262:
               if (var4.equals("color_set")) {
                  var1.getCusPanoramicView(var2).showHideWindow();
               }
               break;
            default:
               switch (var3) {
                  case -1907444892:
                     if (var4.equals("_3_c6h_46h_2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 2});
                     }
                     break;
                  case -1907444891:
                     if (var4.equals("_3_c6h_46h_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 3});
                     }
                     break;
                  case -1907444890:
                     if (var4.equals("_3_c6h_46h_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 4});
                     }
                     break;
                  case -1907444889:
                     if (var4.equals("_3_c6h_46h_5")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 5});
                     }
                     break;
                  case -1907444888:
                     if (var4.equals("_3_c6h_46h_6")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 6});
                     }
               }
         }
      }

   }

   private static final void lambda_6$lambda_1$lambda_0(UiMgr var0, FrontArea var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var4 = var1.getLineBtnAction()[var2][var3];
      Intrinsics.checkNotNullExpressionValue(var4, "lineBtnAction[it][position]");
      var0.sendAirCommand(var4);
   }

   private static final void lambda_6$lambda_5$lambda_2(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[0][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_6$lambda_5$lambda_3(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private final void sendAirCommand(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2});
   }

   private final void sendAirCommand(String var1) {
      switch (var1) {
         case "steering_wheel_heating":
            this.sendAirCommand(172, this.switch(GeneralAirData.steering_wheel_heating));
            break;
         case "front_defog":
            this.sendAirCommand(205, this.switch(GeneralAirData.front_defog));
            break;
         case "auto_manual":
            this.sendAirCommand(37, this.switch(GeneralAirData.auto_manual));
            break;
         case "ac_max":
            this.sendAirCommand(187, 2);
            break;
         case "clean_air":
            this.sendAirCommand(175, this.switch(GeneralAirData.clean_air));
            break;
         case "manual":
            this.sendAirCommand(187, 0);
            break;
         case "auto_wind_lv":
            this.sendAirCommand(177, GeneralAirData.auto_wind_lv);
            break;
         case "rear_power":
            this.sendAirCommand(36, this.switch(GeneralAirData.rear_power));
            break;
         case "rear_blow_foot":
            this.sendAirCommand(39, this.switch(GeneralAirData.rear_left_blow_foot));
            break;
         case "rear_blow_head":
            this.sendAirCommand(38, this.switch(GeneralAirData.rear_left_blow_head));
            break;
         case "max_front":
            this.sendAirCommand(187, 3);
            break;
         case "ac":
            this.sendAirCommand(189, this.switch(GeneralAirData.ac));
            break;
         case "aqs":
            this.sendAirCommand(176, this.switch(GeneralAirData.aqs));
            break;
         case "dual":
            this.sendAirCommand(179, this.switch(GeneralAirData.dual));
            break;
         case "rear":
            this.sendAirCommand(188, this.switch(GeneralAirData.rear));
            break;
         case "power":
            this.sendAirCommand(178, this.switch(GeneralAirData.power));
            break;
         case "blow_window":
            this.sendAirCommand(182, this.switch(GeneralAirData.front_left_blow_window));
            break;
         case "in_out_cycle":
            this.sendAirCommand(190, this.switch(GeneralAirData.in_out_cycle ^ true));
            break;
         case "blow_foot":
            this.sendAirCommand(181, this.switch(GeneralAirData.front_left_blow_foot));
            break;
         case "blow_head":
            this.sendAirCommand(180, this.switch(GeneralAirData.front_left_blow_head));
            break;
         case "auto_1_2":
            this.sendAirCommand(187, 1);
      }

   }

   private final int switch(boolean var1) {
      return var1 ^ 1;
   }

   public MyPanoramicView getCusPanoramicView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      return this.mPanoramicView;
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/car/_3/UiMgr$Companion;", "", "()V", "TAG", "", "CanBusInfo_release"},
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
