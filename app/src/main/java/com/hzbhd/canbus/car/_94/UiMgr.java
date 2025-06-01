package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"},
   d2 = {"Lcom/hzbhd/canbus/car/_94/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "mDiagitalKeyboardActions", "", "[[Ljava/lang/String;", "mDifferentId", "", "mEachId", "mFeaturesKeyboardActions", "mIsFeaturesKeyboard", "", "mMsgMgr", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "getCusPanoramicView", "Landroid/view/View;", "resolveVolume", "selectpos", "send0xC5Command", "", "dataIndex", "value", "sendAirCommand", "command", "title", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final String TAG;
   private final String[][] mDiagitalKeyboardActions;
   private int mDifferentId;
   private int mEachId;
   private final String[][] mFeaturesKeyboardActions;
   private boolean mIsFeaturesKeyboard;
   private final MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static void $r8$lambda$17GUKCcoMw79DqfhYmbhWHCJRMc(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$4jLb0xsgplmTLi0_rl21BsCtu8U(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$6IzEUIJonl_ki4CDEESYyUuAXPo(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      lambda_22$lambda_18(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$KEKXvTyt7BITFQAYddtrSGdSXFc(SettingPageUiSet var0, int var1, int var2) {
      lambda_22$lambda_21(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$R88K15fT_fviIOleVh_t0rdbmNY(ParkPageUiSet var0, UiMgr var1, int var2) {
      lambda_31$lambda_30(var0, var1, var2);
   }

   // $FF: synthetic method
   public static String $r8$lambda$ZGrtduWKrVKU4t3EbSuCHLTNykE(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      return lambda_22$lambda_20(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$c3wmB2zUrtwybfwW1brtwtmfIDQ(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$h_j0HBg_Bi1gFm6c_lKyTUsIYak(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4, int var5) {
      lambda_22$lambda_10(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static void $r8$lambda$kn0oxcwvcNZ0TDFn_N_zP7tQRBY(SettingPageUiSet var0, int var1, int var2, View var3, MotionEvent var4) {
      lambda_22$lambda_19(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$rmaEXzJ6V6M8j0SsptMsl88TUMs(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$wTc2OAtrgLFoyesTcjCYLMtkFGI(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_22$lambda_11(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$zUUisGSxZ7GUNpYd9mjFdCTgEQA(UiMgr var0, RearArea var1, int var2) {
      lambda_9$lambda_8$lambda_5(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      this.TAG = "_1094_UiMgr";
      MsgMgrInterface var3 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._94.MsgMgr");
      MsgMgr var9 = (MsgMgr)var3;
      this.mMsgMgr = var9;
      String[] var5 = new String[]{"number_1", "number_2", "number_3"};
      String[] var4 = new String[]{"star", "number_0", "well"};
      String[] var6 = new String[]{"pickup", "hangup", "null"};
      this.mDiagitalKeyboardActions = new String[][]{var5, {"number_4", "number_5", "number_6"}, {"number_7", "number_8", "number_9"}, var4, var6};
      var5 = new String[]{"null", "down", "null"};
      var4 = new String[]{"prev", "shuff", "next"};
      this.mFeaturesKeyboardActions = new String[][]{{"null", "up", "null"}, {"left", "ok", "right"}, var5, {"info", "menu", "device"}, var4};
      this.mIsFeaturesKeyboard = true;
      this.removeSettingRightItemByNameList(var1, new String[]{"_94_distance_prompt"});
      this.removeSettingRightItemByNameList(var1, new String[]{"_94_atmosphere_lamp"});
      var9.getM0x61DataRecord();
      this.mDifferentId = this.getCurrentCarId();
      this.mEachId = this.getEachId();
      Log.i("_1094_UiMgr", "mDifferentId: " + this.mDifferentId + "    mEachId: " + this.mEachId);
      if (this.mEachId == 3) {
         this.removeMainPrjBtnByName(var1, "sync");
      }

      if (this.mDifferentId != 19) {
         this.removeMainPrjBtnByName(var1, "hybird");
      }

      if (this.mDifferentId == 14) {
         this.removeMainPrjBtnByName(var1, "panel_key");
      }

      AirPageUiSet var10 = this.getAirUiSet(var1);
      if (this.mDifferentId == 13) {
         var10.setHaveRearArea(false);
         this.removeFrontAirButtonByName(var1, "auto");
         this.removeFrontAirButtonByName(var1, "dual");
         this.removeFrontAirButtonByName(var1, "steering_wheel_heating");
         this.removeFrontAirButtonByName(var1, "auto_wind_lv");
      }

      FrontArea var13 = var10.getFrontArea();
      var13.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var13), new UiMgr$$ExternalSyntheticLambda5(this, var13), new UiMgr$$ExternalSyntheticLambda6(this, var13), new UiMgr$$ExternalSyntheticLambda7(this, var13)});
      var13.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(27);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(26);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(29);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(28);
         }
      })});
      var13.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(31);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(30);
         }
      }));
      var13.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(7);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(9);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(8);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(10);
         }
      })});
      Unit var14 = Unit.INSTANCE;
      RearArea var11 = var10.getRearArea();
      var11.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new UiMgr$$ExternalSyntheticLambda8(this, var11)});
      var14 = Unit.INSTANCE;
      var11.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(19);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(20);
         }
      }, null}));
      var14 = Unit.INSTANCE;
      var11.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(21);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(22);
         }
      }));
      Unit var12 = Unit.INSTANCE;
      var12 = Unit.INSTANCE;
      SettingPageUiSet var15 = this.getSettingUiSet(var1);
      if (this.mDifferentId == 13) {
         ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var15.getList().get(9)).getItemList().get(1)).setValueSrnArray((List)CollectionsKt.arrayListOf(new String[]{"close", "_94_khaki", "_250_red", "_250_pink", "ford_raise_color_light_purple", "_250_blue", "_250_green", "_94_azure"}));
      }

      int var2 = this.mDifferentId;
      if (var2 != 22 && var2 != 24 && var2 != 29) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"drive_assist", "_94_safety_warning", "light_settings", "_94_electric_mirror_settings", "_94_key_lock", "_94_wiper", "_94_general_settings", "airSetting", "other_set"});
      }

      if (this.mDifferentId == 24) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_94_safety_warning", "other_set", "_94_electric_mirror_settings", "_94_general_settings", "airSetting"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_94_lane_keeping_mode", "_94_warning_intensity", "_94_reverse_gear_incoming_car_warning", "_94_tsc_control", "_94_cruise_control", "_94_automatic_engine_shutdown", "_94_headlight_delay", "_94_automatic_high_beam", "_94_switch_prohibited", "_94_voice_feedback", "_94_false_lock_warning", "_94_remote_unlock", "_94_automatically_unlock", "_94_remote_control_on", "_94_remote_control_off", "_94_activate_remote_start", "_94_air_conditioning_control", "_94_cycle", "_94_rain_sensing_wiper", "_94_repeat_wiper_once", "_94_rear_wiper"});
      }

      if (this.mDifferentId == 22) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_94_esp_state", "_94_remote_control", "_334_day_light", "_250_i_went_home_with", "_94_indoor_lamp", "_94_speed_lock", "_94_one_click", "_94_auto_wiper", "_94_automatic_maintenance"});
      }

      if (this.mDifferentId != 14) {
         this.removeSettingRightItemByNameList(var1, new String[]{"seat_set"});
      }

      if (this.mDifferentId != 4) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_94_ambient_light", "_94_active_city", "_94_powerfold_mirrors"});
      }

      if (this.mDifferentId == 29) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_94_tsc_control", "_94_cruise_control", "_94_esp_state", "_94_remote_control", "_334_day_light", "_250_i_went_home_with", "_94_speed_lock", "_94_one_click", "_94_auto_wiper", "_94_automatic_maintenance"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"airSetting", "_94_general_settings"});
      } else {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_94_passengerSideSecureAirbag", "_94_cross_country", "_94_panoramic_and_video"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_94_auto_hold", "_94_lane_centring", "_94_sensitivity", "_94_pre_collision_assist", "_94_evasive_steering_assist", "_94_reverse_brake_assist", "_94_parking_sensirs", "_94_key_detection_alert", "_94_auto_lock", "_94_key_free", "_94_park_lock_control", "_94_hands_free"});
      }

      var15.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda9(var15, this, var1));
      var15.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda10(var15));
      var15.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda11(var15, this));
      var15.setOnSettingItemTouchListener(new UiMgr$$ExternalSyntheticLambda1(var15));
      var15.setOnSettingItemSeekbarSetTextListener(new UiMgr$$ExternalSyntheticLambda2(var15, var1));
      var15.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda3(var15));
      var12 = Unit.INSTANCE;
      ParkPageUiSet var16 = this.getParkPageUiSet(var1);
      var16.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda4(var16, this));
      var12 = Unit.INSTANCE;
      AmplifierPageUiSet var17 = this.getAmplifierPageUiSet(var1);
      var17.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        return;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var2});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -61, 2, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -61, 1, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, (byte)var2});
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
               var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 1;
               var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
               var0[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
               var0[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      var17.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -61, 4, (byte)(var2 + 7)});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -61, 3, (byte)(var2 + 7)});
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
      var12 = Unit.INSTANCE;
      SyncPageUiSet var18 = this.getSyncPageUiSet(var1);
      var18.setOnSyncStateChangeListener((OnSyncStateChangeListener)(new OnSyncStateChangeListener() {
         public void onResume() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
         }

         public void onStop() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
         }
      }));
      var18.setOnSyncItemClickListener((OnSyncItemClickListener)(new OnSyncItemClickListener(var18, this) {
         final SyncPageUiSet $this_run;
         final UiMgr this$0;

         {
            this.$this_run = var1;
            this.this$0 = var2;
         }

         public void onKeyboardBtnClick(String var1) {
            if (var1 != null) {
               int var3 = var1.hashCode();
               int var2 = 27;
               switch (var3) {
                  case -1886439238:
                     if (!var1.equals("number_0")) {
                        return;
                     }

                     var2 = 13;
                     break;
                  case -1886439237:
                     if (!var1.equals("number_1")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum1().getShort();
                     break;
                  case -1886439236:
                     if (!var1.equals("number_2")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum2().getShort();
                     break;
                  case -1886439235:
                     if (!var1.equals("number_3")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum3().getShort();
                     break;
                  case -1886439234:
                     if (!var1.equals("number_4")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum4().getShort();
                     break;
                  case -1886439233:
                     if (!var1.equals("number_5")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum5().getShort();
                     break;
                  case -1886439232:
                     if (!var1.equals("number_6")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum6().getShort();
                     break;
                  case -1886439231:
                     if (!var1.equals("number_7")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum7().getShort();
                     break;
                  case -1886439230:
                     if (!var1.equals("number_8")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum8().getShort();
                     break;
                  case -1886439229:
                     if (!var1.equals("number_9")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum9().getShort();
                     break;
                  default:
                     switch (var3) {
                        case -1335157162:
                           if (!var1.equals("device")) {
                              return;
                           }
                           break;
                        case -1224574323:
                           if (!var1.equals("hangup")) {
                              return;
                           }

                           var2 = 4;
                           break;
                        case -988476804:
                           if (!var1.equals("pickup")) {
                              return;
                           }

                           var2 = 5;
                           break;
                        case 3548:
                           if (!var1.equals("ok")) {
                              return;
                           }

                           var2 = 12;
                           break;
                        case 3739:
                           if (!var1.equals("up")) {
                              return;
                           }

                           var2 = 10;
                           break;
                        case 96964:
                           if (!var1.equals("aux")) {
                              return;
                           }
                           break;
                        case 3089570:
                           if (!var1.equals("down")) {
                              return;
                           }

                           var2 = 11;
                           break;
                        case 3237038:
                           if (!var1.equals("info")) {
                              return;
                           }

                           var2 = 6;
                           break;
                        case 3317767:
                           if (!var1.equals("left")) {
                              return;
                           }

                           var2 = 25;
                           break;
                        case 3347807:
                           if (!var1.equals("menu")) {
                              return;
                           }

                           var2 = 2;
                           break;
                        case 3377907:
                           if (!var1.equals("next")) {
                              return;
                           }

                           var2 = 9;
                           break;
                        case 3449395:
                           if (!var1.equals("prev")) {
                              return;
                           }

                           var2 = 8;
                           break;
                        case 3540562:
                           if (!var1.equals("star")) {
                              return;
                           }

                           var2 = MsgMgr.SyncKey.Companion.getStar().getShort();
                           break;
                        case 3645646:
                           if (!var1.equals("well")) {
                              return;
                           }

                           var2 = MsgMgr.SyncKey.Companion.getWell().getShort();
                           break;
                        case 108511772:
                           if (!var1.equals("right")) {
                              return;
                           }

                           var2 = 26;
                           break;
                        case 109418880:
                           if (var1.equals("shuff")) {
                              var2 = 7;
                              break;
                           }

                           return;
                        default:
                           return;
                     }
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var2});
            }

         }

         public void onKeyboardBtnLongClick(String var1) {
            if (var1 != null) {
               int var2;
               switch (var1.hashCode()) {
                  case -1886439238:
                     if (!var1.equals("number_0")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum0().getLong();
                     break;
                  case -1886439237:
                     if (!var1.equals("number_1")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum1().getLong();
                     break;
                  case -1886439236:
                     if (!var1.equals("number_2")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum2().getLong();
                     break;
                  case -1886439235:
                     if (!var1.equals("number_3")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum3().getLong();
                     break;
                  case -1886439234:
                     if (!var1.equals("number_4")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum4().getLong();
                     break;
                  case -1886439233:
                     if (!var1.equals("number_5")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum5().getLong();
                     break;
                  case -1886439232:
                     if (!var1.equals("number_6")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum6().getLong();
                     break;
                  case -1886439231:
                     if (!var1.equals("number_7")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum7().getLong();
                     break;
                  case -1886439230:
                     if (!var1.equals("number_8")) {
                        return;
                     }

                     var2 = MsgMgr.SyncKey.Companion.getNum8().getLong();
                     break;
                  case -1886439229:
                     if (var1.equals("number_9")) {
                        var2 = MsgMgr.SyncKey.Companion.getNum9().getLong();
                        break;
                     }

                     return;
                  default:
                     return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var2});
            }

         }

         public void onLeftIconClick(String var1) {
            if (var1 != null) {
               byte var2;
               switch (var1.hashCode()) {
                  case 103772132:
                     if (!var1.equals("media")) {
                        return;
                     }

                     var2 = 2;
                     break;
                  case 106642798:
                     if (!var1.equals("phone")) {
                        return;
                     }

                     var2 = 3;
                     break;
                  case 112386354:
                     if (!var1.equals("voice")) {
                        return;
                     }

                     var2 = 1;
                     break;
                  case 503739367:
                     if (var1.equals("keyboard")) {
                        SyncPageUiSet var3 = this.$this_run;
                        String[][] var4;
                        if (this.this$0.mIsFeaturesKeyboard) {
                           this.this$0.mIsFeaturesKeyboard = false;
                           var4 = this.this$0.mDiagitalKeyboardActions;
                        } else {
                           this.this$0.mIsFeaturesKeyboard = true;
                           var4 = this.this$0.mFeaturesKeyboardActions;
                        }

                        var3.setKeyboardActions(var4);
                        this.this$0.mMsgMgr.updateSync((Bundle)null);
                        return;
                     }

                     return;
                  default:
                     return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var2});
            }

         }

         public void onListItemClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)(var1 + 145)});
         }

         public void onSoftKeyClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)(MsgMgr.SyncKey.Companion.getK1().getShort() + var1)});
         }
      }));
      var12 = Unit.INSTANCE;
      PanelKeyPageUiSet var7 = this.getPanelKeyPageUiSet(var1);
      var7.setOnPanelKeyPositionListener((OnPanelKeyPositionListener)(new OnPanelKeyPositionListener(var7) {
         final PanelKeyPageUiSet $this_apply;

         {
            this.$this_apply = var1;
         }

         public void click(int var1) {
            List var2 = this.$this_apply.getList();
            String var3;
            if (var2 != null) {
               var3 = (String)var2.get(var1);
            } else {
               var3 = null;
            }

            if (var3 != null) {
               switch (var3) {
                  case "_94_parallel_parking":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 1});
                     break;
                  case "_94_parallel_berth_out":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 3});
                     break;
                  case "_94_vertical_parking":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 2});
                     break;
                  case "close":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 0});
               }
            }

         }
      }));
      Unit var8 = Unit.INSTANCE;
   }

   private static final void lambda_22$lambda_10(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4, int var5) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$context");
      String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      if (var6 != null) {
         var4 = var6.hashCode();
         byte var7 = 2;
         switch (var4) {
            case -2088909201:
               if (var6.equals("_94_driver_cushion")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 7, (byte)var5});
               }
               break;
            case -1966526692:
               if (var6.equals("_94_sound_effect_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -61, 6, (byte)var5});
               }
               break;
            case -1928055775:
               if (var6.equals("_94_cycle")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 90, (byte)var5});
               }
               break;
            case -1895666868:
               if (var6.equals("_94_tire_pressure_unit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 112, (byte)var5});
               }
               break;
            case -1693206700:
               if (var6.equals("_94_temperature_unit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 114, (byte)var5});
               }
               break;
            case -1495583937:
               if (var6.equals("_94_indoor_lamp")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 37, (byte)var5});
               }
               break;
            case -1430029131:
               if (var6.equals("_94_headlight_delay")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 33, (byte)MsgMgr.Companion.getM0xC90x21ItemValues()[var5]});
               }
               break;
            case -1206262650:
               if (var6.equals("_94_mirror_fold_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 6, (byte)var5});
               }
               break;
            case -1093208114:
               if (var6.equals("_81_turn_signals_setup")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var5 + 3), 0});
               }
               break;
            case -984400282:
               if (var6.equals("_94_charging_indicator")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var5 + 25), 0});
               }
               break;
            case -922538678:
               if (var6.equals("ford_range_unit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var5 + 14), 0});
               }
               break;
            case -916760944:
               if (var6.equals("_94_go_home_with_me_duration")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 9, (byte)var5});
               }
               break;
            case -903809344:
               if (var6.equals("speed_linkage_volume_adjustment")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -61, 5, (byte)var5});
               }
               break;
            case -807237109:
               if (var6.equals("_94_warning_intensity")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, (byte)var5});
               }
               break;
            case -759500786:
               if (var6.equals("_94_position")) {
                  if (var5 != 1) {
                     if (var5 != 2) {
                        var7 = 0;
                     } else {
                        var7 = 1;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -61, 7, (byte)var7});
               }
               break;
            case -711254215:
               if (var6.equals("_94_welcome_lamp_duration")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 2, (byte)var5});
               }
               break;
            case -688412993:
               if (var6.equals("_94_air_conditioning_control")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 88, (byte)var5});
               }
               break;
            case -426649003:
               if (var6.equals("_94_passenger")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 11, (byte)var5});
               }
               break;
            case -205537133:
               if (var6.equals("_94_lane_keeping_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 0, (byte)var5});
               }
               break;
            case 72545322:
               if (var6.equals("_94_measure_unit")) {
                  if (var5 != 1) {
                     if (var5 != 2) {
                        var7 = 0;
                     } else {
                        var7 = 3;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 113, (byte)var7});
               }
               break;
            case 102584022:
               if (var6.equals("language_setup")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte)MsgMgr.Companion.getMLanguageIndexs()[var5], 0});
               }
               break;
            case 108612084:
               if (var6.equals("_18_vehicle_setting_item_3_210")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0, (byte)var5});
               }
               break;
            case 301736346:
               if (var6.equals("_94_sensitivity")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 18, (byte)var5});
               }
               break;
            case 341432570:
               if (var6.equals("_94_welcome_lamp")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 38, (byte)MsgMgr.Companion.getM0xC90x26ItemValues()[var5]});
               }
               break;
            case 378909815:
               if (var6.equals("_94_passenger_cushion")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 9, (byte)var5});
               }
               break;
            case 382166093:
               if (var6.equals("_94_driver")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 10, (byte)var5});
               }
               break;
            case 388217829:
               if (var6.equals("_94_passenger_backrest")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 8, (byte)var5});
               }
               break;
            case 697133026:
               if (var6.equals("_250_i_went_home_with")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 36, (byte)var5});
               }
               break;
            case 768246420:
               if (var6.equals("_94_indoor_lamp_duration")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 5, (byte)var5});
               }
               break;
            case 946029336:
               if (var6.equals("_94_cruise_control")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 4, (byte)var5});
               }
               break;
            case 1195239661:
               if (var6.equals("_94_driver_backrest")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 6, (byte)var5});
               }
               break;
            case 1274456812:
               if (var6.equals("_94_rear_defog_duration")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 7, (byte)var5});
               }
               break;
            case 1456389983:
               if (var6.equals("_279_vehicle_config")) {
                  var1.mMsgMgr.setConfiguration(var2, var5);
                  var1.mMsgMgr.updateSettings("_279_vehicle_config", var5);
               }
               break;
            case 1698388376:
               if (var6.equals("_94_remote_unlock")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 83, (byte)var5});
               }
               break;
            case 1708492483:
               if (var6.equals("_270_setting_12")) {
                  if (var1.mDifferentId != 13) {
                     ++var5;
                  }

                  var1.send0xC5Command(0, var5);
               }
         }
      }

   }

   private static final void lambda_22$lambda_11(SettingPageUiSet var0, int var1, int var2, int var3) {
      if (var4 != null) {
         switch (var4) {
            case "_94_blind_spot_monitoring":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 19, (byte)var3});
               break;
            case "_94_auto_hold":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 8, (byte)var3});
               break;
            case "_94_auto_lock":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 94, (byte)var3});
               break;
            case "_118_setting_title_49":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte)var3, 0});
               break;
            case "_81_hill_start_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(22 - var3), 0});
               break;
            case "_94_remote_control":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 7, (byte)var3});
               break;
            case "_94_automatic_maintenance":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 100, (byte)var3});
               break;
            case "_94_powerfold_mirrors":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(35 - var3), 0});
               break;
            case "_94_one_click":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 92, (byte)var3});
               break;
            case "remote_window_control":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 3, (byte)var3});
               break;
            case "_94_remote_control_on":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 85, (byte)var3});
               break;
            case "_94_rear_wiper":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 98, (byte)var3});
               break;
            case "_94_steering_air":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 89, (byte)var3});
               break;
            case "_94_automatically_unlock":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 84, (byte)var3});
               break;
            case "ford_alert_tone":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 7), 0});
               break;
            case "_94_distance_prompt":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 16, (byte)var3});
               break;
            case "_94_reversing_video_hold":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, (byte)var3, 0});
               break;
            case "_94_park_lock_control":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -112, (byte)var3});
               break;
            case "_94_voice_feedback":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 81, (byte)var3});
               break;
            case "_94_active_city":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(31 - var3), 0});
               break;
            case "_334_day_light":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 35, (byte)var3});
               break;
            case "_94_reverse_gear_incoming_car_warning":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, (byte)var3});
               break;
            case "_94_auto_wiper":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 99, (byte)var3});
               break;
            case "_94_key_free":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 95, (byte)var3});
               break;
            case "_94_pre_collision_assist_drive":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 11, (byte)var3});
               break;
            case "_94_sensitivity_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 10, (byte)var3});
               break;
            case "_94_evasive_steering_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 12, (byte)var3});
               break;
            case "_94_ambient_light":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(33 - var3), 0});
               break;
            case "_94_electric_trunk":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 48, (byte)var3});
               break;
            case "_94_speed_lock":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 91, (byte)var3});
               break;
            case "_94_repeat_wiper_once":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 97, (byte)var3});
               break;
            case "_94_instrument_direction_key":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(24 - var3), 0});
               break;
            case "_94_lane_centring":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 9, (byte)var3});
               break;
            case "_94_fatigue_driving_warning":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 20, (byte)var3});
               break;
            case "_284_setting_item_49":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 4, (byte)var3});
               break;
            case "_81_rain_sensor":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte)var3, 0});
               break;
            case "_94_switch_prohibited":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 80, (byte)var3});
               break;
            case "_94_esp_state":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 6, (byte)var3});
               break;
            case "_94_active_braking":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 17, (byte)var3});
               break;
            case "_94_automatic_folding":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 64, (byte)var3});
               break;
            case "_94_intelligent_unlock_lock":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 1, (byte)var3});
               break;
            case "speed_lock":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 8, (byte)var3});
               break;
            case "_94_hill_descent_control":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -80, (byte)var3});
               break;
            case "_94_rear_drive_acceleration_lock":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -79, (byte)var3});
               break;
            case "_94_key_detection_alert":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 93, (byte)var3});
               break;
            case "_94_false_lock_warning":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 82, (byte)var3});
               break;
            case "_94_parking_sensirs":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 14, (byte)var3});
               break;
            case "_94_tsc_control":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 3, (byte)var3});
               break;
            case "_94_reverse_brake_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 13, (byte)var3});
               break;
            case "_94_automatic_high_beam":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 34, (byte)var3});
               break;
            case "_81_traction_control_system":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(2 - var3), 0});
               break;
            case "_94_automatic_engine_shutdown":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 5, (byte)var3});
               break;
            case "_94_passenger_airbag":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -96, (byte)var3});
               break;
            case "_94_fog_light_steering_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 39, (byte)var3});
               break;
            case "_94_remote_control_off":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 86, (byte)var3});
               break;
            case "parking_assistance":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte)var3, 0});
               break;
            case "_94_activate_remote_start":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 87, (byte)var3});
               break;
            case "ford_message_tone":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 5), 0});
               break;
            case "_94_rain_sensing_wiper":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 96, (byte)var3});
               break;
            case "_218_setting_0_4":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(20 - var3), 0});
         }
      }

   }

   private static final void lambda_22$lambda_18(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (var5 != null) {
         var3 = var5.hashCode();
         byte var6 = 2;
         switch (var3) {
            case -1315315632:
               if (var5.equals("_94_atmosphere_lamp")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 32, (byte)var4});
               }
               break;
            case -522237854:
               if (var5.equals("_94_driver_low")) {
                  var3 = var1.mMsgMgr.getDriver().getLow() - var4;
                  if (var3 <= 0) {
                     if (var3 < 0) {
                        var6 = 1;
                     } else {
                        var6 = 0;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 2, (byte)var6});
               }
               break;
            case -522237098:
               if (var5.equals("_94_driver_mid")) {
                  var3 = var1.mMsgMgr.getDriver().getMiddle() - var4;
                  if (var3 <= 0) {
                     if (var3 < 0) {
                        var6 = 1;
                     } else {
                        var6 = 0;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 1, (byte)var6});
               }
               break;
            case 587065676:
               if (var5.equals("_94_passenger_high")) {
                  var3 = var1.mMsgMgr.getPassenger().getHigh() - var4;
                  if (var3 <= 0) {
                     if (var3 < 0) {
                        var6 = 1;
                     } else {
                        var6 = 0;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 3, (byte)var6});
               }
               break;
            case 988772970:
               if (var5.equals("_94_passenger_low")) {
                  var3 = var1.mMsgMgr.getPassenger().getLow() - var4;
                  if (var3 <= 0) {
                     if (var3 < 0) {
                        var6 = 1;
                     } else {
                        var6 = 0;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 5, (byte)var6});
               }
               break;
            case 988773726:
               if (var5.equals("_94_passenger_mid")) {
                  var3 = var1.mMsgMgr.getPassenger().getMiddle() - var4;
                  if (var3 <= 0) {
                     if (var3 < 0) {
                        var6 = 1;
                     } else {
                        var6 = 0;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 4, (byte)var6});
               }
               break;
            case 990370388:
               if (var5.equals("_94_driver_high")) {
                  var3 = var1.mMsgMgr.getDriver().getHigh() - var4;
                  if (var3 <= 0) {
                     if (var3 < 0) {
                        var6 = 1;
                     } else {
                        var6 = 0;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 0, (byte)var6});
               }
               break;
            case 1708492484:
               if (var5.equals("_270_setting_13")) {
                  var1.send0xC5Command(1, var4 * 5);
               }
         }
      }

   }

   private static final void lambda_22$lambda_19(SettingPageUiSet var0, int var1, int var2, View var3, MotionEvent var4) {
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var5, (Object)"_94_cockpit_fresh_air")) {
         var1 = var4.getAction();
         if (var1 != 0) {
            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -128, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -55, -128, 1});
         }
      } else if (Intrinsics.areEqual((Object)var5, (Object)"_94_tire_reset") && var4.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, 18, 0});
      }

   }

   private static final String lambda_22$lambda_20(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "$context");
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (var5 != null) {
         label55: {
            switch (var5.hashCode()) {
               case -522237854:
                  if (!var5.equals("_94_driver_low")) {
                     break label55;
                  }
                  break;
               case -522237098:
                  if (!var5.equals("_94_driver_mid")) {
                     break label55;
                  }
                  break;
               case 587065676:
                  if (!var5.equals("_94_passenger_high")) {
                     break label55;
                  }
                  break;
               case 988772970:
                  if (!var5.equals("_94_passenger_low")) {
                     break label55;
                  }
                  break;
               case 988773726:
                  if (!var5.equals("_94_passenger_mid")) {
                     break label55;
                  }
                  break;
               case 990370388:
                  if (!var5.equals("_94_driver_high")) {
                     break label55;
                  }
                  break;
               case 1708492484:
                  if (var5.equals("_270_setting_13")) {
                     var5 = String.valueOf(var4 * 5);
                     return var5;
                  }
               default:
                  break label55;
            }

            if (var4 == 0) {
               var5 = CommUtil.getStrByResId(var1, "close");
            } else {
               var5 = String.valueOf(var4);
            }

            return var5;
         }
      }

      var5 = String.valueOf(var4);
      return var5;
   }

   private static final void lambda_22$lambda_21(SettingPageUiSet var0, int var1, int var2) {
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"_94_reset_all")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -61, -1, 0});
      }

   }

   private static final void lambda_31$lambda_30(ParkPageUiSet var0, UiMgr var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var3 != null) {
         switch (var3) {
            case "_94_left_back":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -58, (byte)(1 - ((Number)var1.mMsgMgr.getListGeneralParkBigData().get(5)).intValue())});
               break;
            case "_94_front":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -62, (byte)(1 - ((Number)var1.mMsgMgr.getListGeneralParkBigData().get(1)).intValue())});
               break;
            case "_94_rear_view_split_screen":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, (byte)(1 - (var1.mMsgMgr.getMReversingValues() >> 3 & 1)), 0});
               break;
            case "_94_front_left":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -63, (byte)(1 - ((Number)var1.mMsgMgr.getListGeneralParkBigData().get(0)).intValue())});
               break;
            case "_94_back":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -59, (byte)(1 - ((Number)var1.mMsgMgr.getListGeneralParkBigData().get(4)).intValue())});
               break;
            case "_94_right_front":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -61, (byte)(1 - ((Number)var1.mMsgMgr.getListGeneralParkBigData().get(2)).intValue())});
               break;
            case "_94_whole_car":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 0});
               break;
            case "_81_video_restore":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 18, 0});
               break;
            case "_94_front_and_left":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 2});
               break;
            case "_94_front_panoramic":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 1});
               break;
            case "_94_back_dip":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 4});
               break;
            case "_81_video_enlarge":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 19, 0});
               break;
            case "_94_right_back":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -60, (byte)(1 - ((Number)var1.mMsgMgr.getListGeneralParkBigData().get(3)).intValue())});
               break;
            case "_94_back_panoramic":
               CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 3});
         }
      }

   }

   private static final void lambda_9$lambda_4$lambda_0(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[0][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_4$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[1][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_4$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[2][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_4$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_8$lambda_5(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private final int resolveVolume(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? 9 : 13;
            } else {
               return 12;
            }
         } else {
            return 11;
         }
      } else {
         return 10;
      }
   }

   private final void send0xC5Command(int var1, int var2) {
      Log.i(this.TAG, "send0xC5Command: " + var1 + "  " + var2);
      byte[] var3 = this.mMsgMgr.getM0x61DataRecord();
      var3[0] = 22;
      var3[1] = -59;
      var3[var1 + 2] = (byte)var2;
      CanbusMsgSender.sendMsg(var3);
   }

   private final void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)var1, 0});
   }

   private final void sendAirCommand(String var1) {
      byte var2;
      label72: {
         switch (var1) {
            case "steering_wheel_heating":
               var2 = 11;
               break label72;
            case "front_defog":
               var2 = 5;
               break label72;
            case "ac_max":
               var2 = 4;
               break label72;
            case "rear_lock":
               var2 = 18;
               break label72;
            case "rear_defog":
               var2 = 6;
               break label72;
            case "rear_power":
               var2 = 17;
               break label72;
            case "max_front":
               var2 = 25;
               break label72;
            case "ac":
               var2 = 2;
               break label72;
            case "auto":
               var2 = 23;
               break label72;
            case "dual":
               var2 = 24;
               break label72;
            case "power":
               var2 = 1;
               break label72;
            case "blow_window":
               var2 = 32;
               break label72;
            case "in_out_cycle":
               var2 = 3;
               break label72;
            case "blow_foot":
               var2 = 34;
               break label72;
            case "blow_head":
               var2 = 33;
               break label72;
         }

         var2 = 0;
      }

      this.sendAirCommand(var2);
   }

   public View getCusPanoramicView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      return (View)this.mMsgMgr.getActivePark(var1).getPanoramicView();
   }
}
