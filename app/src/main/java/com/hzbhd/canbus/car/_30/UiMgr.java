package com.hzbhd.canbus.car._30;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;

public class UiMgr extends AbstractUiMgr {
   static final int AMPLIFIER_BALANCE_MID = 7;
   private static final String TAG = "_1030_UiMgr";
   static final String _30_IS_HYBRID_ELECTRIC_VEHICLE = "_30_is_hybrid_electric_vehicle";
   private final byte[] m0x07Command = new byte[]{22, 7, 0, 0};
   private final byte[] m0x08Command = new byte[]{22, 8, 0, 0, 0};
   private int mDifferent = this.getCurrentCarId();
   private MsgMgr mMsgMgr;
   private SparseArray mNewEnergyCommandArray;
   private SparseArray mSettingsItemArray;
   private int meachId = this.getEachId();
   private MyPanoramicView myPanoramicView;

   public UiMgr(Context var1) {
      this.initUi(var1);
      this.initNewEnergyItem();
      this.getMsgMgr(var1).sendCarInfo();
      Log.i("_1030_UiMgr", "UiMgr: mDifferent " + this.mDifferent);
      if (this.is19Santafe(var1)) {
         Log.i("_1030_UiMgr", "UiMgr: VEHICLE_TYPE_2019_IX45");
         ((MyPanoramicView)this.getCusPanoramicView(var1)).showIbRearDown();
      }

      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0(this, var1));
      var2.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda1(this, var1));
      var2.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda2(this, var1));
      var2.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda3(this));
      var2.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda4(this, var1));
      var2.setOnSettingItemSeekbarSetTextListener(new OnSettingItemSeekbarSetTextListener(this, var1) {
         final DecimalFormat df00;
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.df00 = new DecimalFormat("00");
         }

         public String onSetText(int var1, int var2, int var3) {
            String var4 = (String)this.this$0.mSettingsItemArray.get(var1 << 8 | var2);
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -707409785:
                  if (var4.equals("_30_new_energy_53_C_10")) {
                     var5 = 0;
                  }
                  break;
               case 494546837:
                  if (var4.equals("_30_set_charge_amount_1")) {
                     var5 = 1;
                  }
                  break;
               case 494546838:
                  if (var4.equals("_30_set_charge_amount_2")) {
                     var5 = 2;
                  }
                  break;
               case 524889158:
                  if (var4.equals("_30_end_time_1")) {
                     var5 = 3;
                  }
                  break;
               case 524889159:
                  if (var4.equals("_30_end_time_2")) {
                     var5 = 4;
                  }
                  break;
               case 1449172680:
                  if (var4.equals("_30_new_energy_53_4")) {
                     var5 = 5;
                  }
                  break;
               case 1449172682:
                  if (var4.equals("_30_new_energy_53_6")) {
                     var5 = 6;
                  }
                  break;
               case 2049801759:
                  if (var4.equals("_30_start_time_1")) {
                     var5 = 7;
                  }
                  break;
               case 2049801760:
                  if (var4.equals("_30_start_time_2")) {
                     var5 = 8;
                  }
                  break;
               case 2104599649:
                  if (var4.equals("_30_maximum_speed_limit")) {
                     var5 = 9;
                  }
            }

            switch (var5) {
               case 0:
                  if (var3 < 6) {
                     return "--";
                  }

                  return (float)(var3 + 29) / 2.0F + "℃";
               case 1:
               case 2:
                  return var3 * 10 + "%";
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
                  return String.format("%s:%s", this.df00.format((long)(var3 / 6)), this.df00.format((long)(var3 % 6 * 10)));
               case 9:
                  if (var3 == 8) {
                     return CommUtil.getStrByResId(this.val$context, "car_set_null");
                  }

                  return var3 * 10 + " KM/H";
               default:
                  return String.valueOf(var3);
            }
         }
      });
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      var3.setShowPanoramic(this.is19Santafe(var1));
      var3.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda5(var3));
      AirPageUiSet var4 = this.getAirUiSet(var1);
      if (this.is15Carnival(var1)) {
         var4.getFrontArea().setLineBtnAction((String[][])null);
      } else {
         var4.setHaveRearArea(false);
      }

      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda6(this, var1));
      var5.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda7(this, var1));
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initNewEnergyItem() {
      SparseArray var1 = new SparseArray();
      this.mNewEnergyCommandArray = var1;
      var1.append(2, new NewEnergyCommand(this, 83, 2, 3) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         byte[] getBytes(Context var1) {
            super.getBytes(var1);
            if (this.msgDatas != null) {
               this.command[3] = (byte)this.msgDatas[3];
               this.command[4] = (byte)this.msgDatas[4];
               this.command[5] = (byte)this.msgDatas[5];
               byte var2 = this.command[5];

               for(int var3 = 0; var3 < 6; ++var3) {
                  var2 = (byte)UiMgr.setIntByteWithBit(var2, 5 - var3, this.command[5] >> var3 & 1);
               }

               this.command[5] = var2;
            }

            return this.command;
         }
      });
      this.mNewEnergyCommandArray.append(3, new NewEnergyCommand(this, 83, 3, 1) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         byte[] getBytes(Context var1) {
            super.getBytes(var1);
            if (this.msgDatas != null) {
               this.command[3] = (byte)(this.msgDatas[12] & 15);
            }

            return this.command;
         }
      });
      this.mNewEnergyCommandArray.append(4, new NewEnergyCommand(this, 83, 4, 3) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         byte[] getBytes(Context var1) {
            super.getBytes(var1);
            if (this.msgDatas != null) {
               this.command[3] = (byte)(this.msgDatas[12] >> 4 & 3);
               this.command[4] = (byte)this.msgDatas[10];
               this.command[5] = (byte)this.msgDatas[11];
            }

            return this.command;
         }
      });
      this.mNewEnergyCommandArray.append(5, new NewEnergyCommand(this, 83, 5, 2) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         byte[] getBytes(Context var1) {
            super.getBytes(var1);
            if (this.msgDatas != null) {
               this.command[3] = (byte)this.msgDatas[6];
               this.command[4] = (byte)this.msgDatas[7];
            }

            return this.command;
         }
      });
      this.mNewEnergyCommandArray.append(6, new NewEnergyCommand(this, 83, 6, 2) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         byte[] getBytes(Context var1) {
            super.getBytes(var1);
            if (this.msgDatas != null) {
               this.command[3] = (byte)this.msgDatas[8];
               this.command[4] = (byte)this.msgDatas[9];
            }

            return this.command;
         }
      });
      this.mNewEnergyCommandArray.append(7, new NewEnergyCommand(this, 83, 7, 1) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         byte[] getBytes(Context var1) {
            super.getBytes(var1);
            if (this.msgDatas != null) {
               this.command[3] = (byte)(this.msgDatas[12] & 192 | this.msgDatas[13] & 31);
            }

            return this.command;
         }
      });
      this.mNewEnergyCommandArray.append(10, new NewEnergyCommand(this, 87, 10, 3) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         byte[] getBytes(Context var1) {
            super.getBytes(var1);
            if (this.msgDatas != null) {
               this.command[3] = (byte)UiMgr.setIntByteWithBit(this.command[3], 6, this.msgDatas[7] >> 5 & 1);
               this.command[3] = (byte)UiMgr.setIntByteWithBit(this.command[3], 4, this.msgDatas[7] >> 1 & 1);
               this.command[3] = (byte)UiMgr.setIntByteWithBit(this.command[3], 2, this.msgDatas[7] >> 3 & 1);
               this.command[4] = (byte)DataHandleUtils.setIntFromByteWithBit(this.command[4], DataHandleUtils.getIntFromByteWithBit(this.msgDatas[6], 6, 2), 4, 2);
               this.command[4] = (byte)DataHandleUtils.setIntFromByteWithBit(this.command[4], DataHandleUtils.getIntFromByteWithBit(this.msgDatas[6], 2, 2), 2, 2);
               this.command[4] = (byte)DataHandleUtils.setIntFromByteWithBit(this.command[4], DataHandleUtils.getIntFromByteWithBit(this.msgDatas[6], 4, 2), 0, 2);
               this.command[5] = (byte)this.msgDatas[5];
            }

            return this.command;
         }
      });
   }

   private void initUi(Context var1) {
      if (!this.getMsgMgr(var1).isK4() && this.getEachId() != 18) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"drive_assist"});
      }

      if (this.getEachId() != 18) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"instrument_settings"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"door"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"convenient_services"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"camera_panoramic_view"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"unit_settings"});
         this.removeSettingRightItemByNameList(var1, new String[]{"warning_volume"});
         this.removeSettingRightItemByNameList(var1, new String[]{"parking_safety"});
         this.removeSettingRightItemByNameList(var1, new String[]{"surround_view"});
         this.removeSettingRightItemByNameList(var1, new String[]{"pdw_auto_activation"});
         this.removeSettingRightItemByNameList(var1, new String[]{"easy_seat_access"});
         this.removeSettingRightItemByNameList(var1, new String[]{"dimmed_while_driving"});
         this.removeSettingRightItemByNameList(var1, new String[]{"one_touch_turn_indicator"});
         this.removeSettingRightItemByNameList(var1, new String[]{"headlight_time_out"});
         this.removeSettingRightItemByNameList(var1, new String[]{"headlight_time_out"});
      }

      if (this.getEachId() == 18) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_283_media_titls_2"});
      }

      if (this.getEachId() != 8 && this.getEachId() != 18 && this.getEachId() != 21 && this.getEachId() != 24) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_30_air_internal_circulation"});
         this.removeSettingRightItemByNameList(var1, new String[]{"automatic_dehumidification"});
         this.removeSettingRightItemByNameList(var1, new String[]{"automatic_defogging"});
      }

      if (!this.isSupport0x830x02(var1)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_30_auto_temp_circulation"});
      }

      if (!this.is19Santafe(var1) && this.getEachId() != 18) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"seat_set", "_29_heat_cold"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_30_back_camera_open_hold"});
      }

      if (!this.is20K3(var1) && this.getEachId() != 18) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_304_atoms_lamp_setup"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_30_air_internal_circulation"});
      }

      if (!this.isNewEnergyVehicle(var1)) {
         this.removeMainPrjBtnByName(var1, "drive_data");
         this.removeMainPrjBtnByName(var1, "hybird");
         this.removeSettingLeftItemByNameList(var1, new String[]{"_30_new_energy_53_A"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_30_new_energy_53_B"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_30_new_energy_53_C"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_143_setting_11"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_61_vehicle_type_setup"});
      } else {
         if (!this.is18SonataHybrid(var1) && !this.is19K5NewEnergy(var1)) {
            this.removeSettingLeftItemByNameList(var1, new String[]{"_30_new_energy_53_A"});
         }

         if (!this.is19K3NewEnergy(var1) && !this.is20EncinoElectrical(var1)) {
            this.removeSettingLeftItemByNameList(var1, new String[]{"_30_new_energy_53_B"});
         }

         if (!this.is20EncinoElectrical(var1)) {
            this.removeSettingLeftItemByNameList(var1, new String[]{"_30_new_energy_53_C"});
         }

         if (this.is20EncinoElectrical(var1)) {
            this.removeDriveDateItemForTitles(var1, new String[]{"_30_eco_level", "vm_golf7_vehicle_setup_mfd_avg_consumption", "_30_hybrid_fuel_consumption", "_30_motor_energy_consumption"});
         }

         if (this.is20EncinoElectrical(var1)) {
            this.removeMainPrjBtnByName(var1, "hybird");
         }

         if (!this.is20EncinoElectrical(var1)) {
            this.removeSettingRightItemByNameList(var1, new String[]{"_30_approximate_range_fast_charge", "_30_set_charge_amount_1", "_30_approximate_range_normal", "_30_set_charge_amount_2"});
            this.removeDriveDateItemForTitles(var1, new String[]{"_30_estimated_charging_time_fast_charging", "_30_traditional_system", "_30_air_conditioning_system", "_30_electronic_equipment", "_30_battery_maintenance"});
         }

         if (!this.is20EncinoElectrical(var1)) {
            this.removeSettingLeftItemByNameList(var1, new String[]{"_143_setting_11"});
            this.removeDriveDateItemForTitles(var1, new String[]{"_30_estimated_charging_time_fast_charging", "_30_traditional_system", "_30_air_conditioning_system", "_30_electronic_equipment", "_30_battery_maintenance"});
            this.removeDriveDataPagesByHeadTitles(var1, new String[]{"_143_setting_11"});
         }

      }
   }

   private boolean is15Carnival(Context var1) {
      return this.getMsgMgr(var1).is15Carnival();
   }

   private boolean is1819KiaSportage(Context var1) {
      return this.getMsgMgr(var1).is1819KiaSportage();
   }

   private boolean is18SonataHybrid(Context var1) {
      return this.getMsgMgr(var1).is18SonataHybrid();
   }

   private boolean is1920Tucson(Context var1) {
      return this.getMsgMgr(var1).is1920Tucson();
   }

   private boolean is19K3NewEnergy(Context var1) {
      return this.getMsgMgr(var1).is19K3NewEnergy();
   }

   private boolean is19K5NewEnergy(Context var1) {
      return this.getMsgMgr(var1).is19K5NewEnergy();
   }

   private boolean is19KiaK3(Context var1) {
      return this.getMsgMgr(var1).is19KiaK3();
   }

   private boolean is19Lafesta(Context var1) {
      return this.getMsgMgr(var1).is19Lafesta();
   }

   private boolean is19Santafe(Context var1) {
      return this.getMsgMgr(var1).is19Santafe();
   }

   private boolean is20EncinoElectrical(Context var1) {
      return this.getMsgMgr(var1).is20EncinoElectrical();
   }

   private boolean is20K3(Context var1) {
      return this.getMsgMgr(var1).is20K3();
   }

   private boolean isK4(Context var1) {
      return this.getMsgMgr(var1).isK4();
   }

   private boolean isKx5Top(Context var1) {
      return this.getMsgMgr(var1).isKx5Top();
   }

   private boolean isNewEnergyVehicle(Context var1) {
      boolean var2;
      if (!this.is18SonataHybrid(var1) && !this.is19K3NewEnergy(var1) && !this.is19K5NewEnergy(var1) && !this.is20EncinoElectrical(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private boolean isSupport0x830x02(Context var1) {
      boolean var2;
      if (!this.is19Lafesta(var1) && !this.is19Santafe(var1) && !this.is1920Tucson(var1) && !this.isKx5Top(var1) && !this.is19KiaK3(var1) && !this.is19K3NewEnergy(var1) && !this.is20EncinoElectrical(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   // $FF: synthetic method
   static void lambda$new$5(ParkPageUiSet var0, int var1) {
      String var2 = ((ParkPageUiSet.Bean)var0.getPanoramicBtnList().get(var1)).getTitleSrn();
      var2.hashCode();
      if (var2.equals("_250_swith_3d")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 1});
      }

   }

   private static int setIntByteWithBit(int var0, int var1, int var2) {
      return var0 ^ (var2 ^ var0 >> var1 & 1) << var1;
   }

   public View getCusPanoramicView(Context var1) {
      if (this.myPanoramicView == null) {
         this.myPanoramicView = new MyPanoramicView(var1);
      }

      return this.myPanoramicView;
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__30_UiMgr(Context var1, int var2, int var3, int var4) {
      String var5;
      byte var6;
      label267: {
         var5 = (String)this.mSettingsItemArray.get(var2 << 8 | var3);
         var5.hashCode();
         switch (var5) {
            case "welcome_mirror_light_on_door_unlock":
               var6 = 0;
               break label267;
            case "_30_third_right_backrest_fold":
               var6 = 1;
               break label267;
            case "service_interval":
               var6 = 2;
               break label267;
            case "_30_is_hybrid_vehicle":
               var6 = 3;
               break label267;
            case "wiper_lights_dispaly":
               var6 = 4;
               break label267;
            case "_30_third_left_backrest_fold":
               var6 = 5;
               break label267;
            case "smart_tailgate":
               var6 = 6;
               break label267;
            case "theme_selection_drive":
               var6 = 7;
               break label267;
            case "theme_selection":
               var6 = 8;
               break label267;
            case "_30_theme_color":
               var6 = 9;
               break label267;
            case "wireless_charging_system":
               var6 = 10;
               break label267;
            case "easy_seat_access":
               var6 = 11;
               break label267;
            case "vm_golf7_rear_mode":
               var6 = 12;
               break label267;
            case "warning_volume":
               var6 = 13;
               break label267;
            case "surround_view":
               var6 = 14;
               break label267;
            case "top_view_parking_guidance":
               var6 = 15;
               break label267;
            case "_30_single_color":
               var6 = 16;
               break label267;
            case "parking_safety":
               var6 = 17;
               break label267;
            case "_30_taxi_energy_regeneration_1":
               var6 = 18;
               break label267;
            case "_30_taxi_energy_regeneration_2":
               var6 = 19;
               break label267;
            case "_30_taxi_energy_regeneration_3":
               var6 = 20;
               break label267;
            case "_284_setting_item_47":
               var6 = 21;
               break label267;
            case "parking_guide_in_rear_view":
               var6 = 22;
               break label267;
            case "traffic_signs":
               var6 = 23;
               break label267;
            case "temperature":
               var6 = 24;
               break label267;
            case "auto_rear_wiper_reverse":
               var6 = 25;
               break label267;
            case "parking_distance_warning":
               var6 = 26;
               break label267;
            case "tyre_pressure":
               var6 = 27;
               break label267;
            case "fuel_efficiency_km":
               var6 = 28;
               break label267;
            case "fuel_efficiency_mi":
               var6 = 29;
               break label267;
            case "welcome_mirror_light_on_driver_approach":
               var6 = 30;
               break label267;
            case "headlight_time_out":
               var6 = 31;
               break label267;
            case "_306_value_11":
               var6 = 32;
               break label267;
            case "power_tailgate_opening_height":
               var6 = 33;
               break label267;
            case "power_tailgate":
               var6 = 34;
               break label267;
            case "_30_air_conditioning_control_1":
               var6 = 35;
               break label267;
            case "_30_air_conditioning_control_2":
               var6 = 36;
               break label267;
            case "_30_air_conditioning_control_3":
               var6 = 37;
               break label267;
            case "_30_new_energy_53_B_4":
               var6 = 38;
               break label267;
            case "_30_new_energy_53_B_5":
               var6 = 39;
               break label267;
            case "_30_new_energy_53_B_6":
               var6 = 40;
               break label267;
            case "power_tailgate_speed":
               var6 = 41;
               break label267;
            case "one_touch_turn_indicator":
               var6 = 42;
               break label267;
            case "vm_golf7_front_mode":
               var6 = 43;
               break label267;
            case "_30_new_energy_53_0":
               var6 = 44;
               break label267;
            case "pdw_auto_activation":
               var6 = 45;
               break label267;
            case "dimmed_while_driving":
               var6 = 46;
               break label267;
            case "blind_spot_view":
               var6 = 47;
               break label267;
            case "lcy_road_warning":
               var6 = 48;
               break label267;
            case "distance_and_speed":
               var6 = 49;
               break label267;
         }

         var6 = -1;
      }

      switch (var6) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, (byte)var4});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)(var4 + 1)});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 54, (byte)var4});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -87, -1, (byte)var4});
            SharePreUtil.setIntValue(var1, "_30_is_hybrid_electric_vehicle", var4);
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte)var4});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)(var4 + 1)});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 115, (byte)var4});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)var4});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte)var4});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)MsgMgr.THEME_COLOR_INDEXES[var4]});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -126, (byte)var4});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 80, (byte)var4});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 4, (byte)(var4 + 1)});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte)var4});
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var4});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -112, (byte)var4});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)(var4 + 1)});
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte)var4});
            break;
         case 18:
            NewEnergyMethod.sendMultiBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(10)).getBytes(var1), 2, 4, 2, var4);
            break;
         case 19:
            NewEnergyMethod.sendMultiBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(10)).getBytes(var1), 2, 0, 2, var4);
            break;
         case 20:
            NewEnergyMethod.sendMultiBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(10)).getBytes(var1), 2, 2, 2, var4);
            break;
         case 21:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)(var4 + 1)});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -111, (byte)var4});
            break;
         case 23:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 51, (byte)var4});
            break;
         case 24:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte)var4});
            break;
         case 25:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -125, (byte)var4});
            break;
         case 26:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -110, (byte)var4});
            break;
         case 27:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, (byte)var4});
            break;
         case 28:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, (byte)var4});
            break;
         case 29:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, (byte)var4});
            break;
         case 30:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -127, (byte)var4});
            break;
         case 31:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 97, (byte)var4});
            break;
         case 32:
            CanbusMsgSender.sendMsg(new byte[]{22, 3, 1, (byte)var4});
            this.getMsgMgr(var1).updateSettingItem(var5, var4);
            SharePreUtil.setIntValue(var1, "share_30_language", var4);
            break;
         case 33:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 114, (byte)var4});
            break;
         case 34:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 112, (byte)var4});
            break;
         case 35:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(10)).getBytes(var1), 1, 6, var4);
            break;
         case 36:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(10)).getBytes(var1), 1, 2, var4);
            break;
         case 37:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(10)).getBytes(var1), 1, 4, var4);
            break;
         case 38:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(4)).getBytes(var1), 1, 0, var4);
            break;
         case 39:
            NewEnergyMethod.sendMultiBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(3)).getBytes(var1), 1, 2, 2, var4);
            break;
         case 40:
            NewEnergyMethod.sendMultiBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(3)).getBytes(var1), 1, 0, 2, var4);
            break;
         case 41:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 113, (byte)var4});
            break;
         case 42:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 97, (byte)var4});
            break;
         case 43:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 3, (byte)(var4 + 1)});
            break;
         case 44:
            CanbusMsgSender.sendMsg(new byte[]{22, -87, 1, (byte)(var4 + 1)});
            break;
         case 45:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)var4});
            break;
         case 46:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 96, (byte)var4});
            break;
         case 47:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 52, (byte)var4});
            break;
         case 48:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 53, (byte)var4});
            break;
         case 49:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, (byte)var4});
      }

   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__30_UiMgr(Context var1, int var2, int var3, int var4) {
      String var5 = (String)this.mSettingsItemArray.get(var2 << 8 | var3);
      var5.hashCode();
      var3 = var5.hashCode();
      byte var6 = -1;
      switch (var3) {
         case -1944712214:
            if (var5.equals("_30_new_energy_tuesday_1")) {
               var6 = 0;
            }
            break;
         case -1944712213:
            if (var5.equals("_30_new_energy_tuesday_2")) {
               var6 = 1;
            }
            break;
         case -1944712212:
            if (var5.equals("_30_new_energy_tuesday_3")) {
               var6 = 2;
            }
            break;
         case -1838280586:
            if (var5.equals("_29_steering_wheel_heater")) {
               var6 = 3;
            }
            break;
         case -1381093690:
            if (var5.equals("_29_seat_heat_ventil")) {
               var6 = 4;
            }
            break;
         case -1228494769:
            if (var5.equals("_30_back_camera_open_hold")) {
               var6 = 5;
            }
            break;
         case -869063945:
            if (var5.equals("_30_new_energy_sunday_1")) {
               var6 = 6;
            }
            break;
         case -869063944:
            if (var5.equals("_30_new_energy_sunday_2")) {
               var6 = 7;
            }
            break;
         case -869063943:
            if (var5.equals("_30_new_energy_sunday_3")) {
               var6 = 8;
            }
            break;
         case -770255039:
            if (var5.equals("_30_new_energy_thursday_1")) {
               var6 = 9;
            }
            break;
         case -770255038:
            if (var5.equals("_30_new_energy_thursday_2")) {
               var6 = 10;
            }
            break;
         case -770255037:
            if (var5.equals("_30_new_energy_thursday_3")) {
               var6 = 11;
            }
            break;
         case -662544296:
            if (var5.equals("keep_rear_camera_on")) {
               var6 = 12;
            }
            break;
         case -644872082:
            if (var5.equals("_29_park_distance_warning")) {
               var6 = 13;
            }
            break;
         case -561451322:
            if (var5.equals("_30_new_energy_friday_1")) {
               var6 = 14;
            }
            break;
         case -561451321:
            if (var5.equals("_30_new_energy_friday_2")) {
               var6 = 15;
            }
            break;
         case -561451320:
            if (var5.equals("_30_new_energy_friday_3")) {
               var6 = 16;
            }
            break;
         case -150838531:
            if (var5.equals("_30_air_internal_circulation")) {
               var6 = 17;
            }
            break;
         case 52118889:
            if (var5.equals("_30_auto_temp_circulation")) {
               var6 = 18;
            }
            break;
         case 84609376:
            if (var5.equals("_30_new_energy_saturday_1")) {
               var6 = 19;
            }
            break;
         case 84609377:
            if (var5.equals("_30_new_energy_saturday_2")) {
               var6 = 20;
            }
            break;
         case 84609378:
            if (var5.equals("_30_new_energy_saturday_3")) {
               var6 = 21;
            }
            break;
         case 262601715:
            if (var5.equals("_30_new_energy_wednesday_1")) {
               var6 = 22;
            }
            break;
         case 262601716:
            if (var5.equals("_30_new_energy_wednesday_2")) {
               var6 = 23;
            }
            break;
         case 262601717:
            if (var5.equals("_30_new_energy_wednesday_3")) {
               var6 = 24;
            }
            break;
         case 528921143:
            if (var5.equals("_30_new_energy_monday_1")) {
               var6 = 25;
            }
            break;
         case 528921144:
            if (var5.equals("_30_new_energy_monday_2")) {
               var6 = 26;
            }
            break;
         case 528921145:
            if (var5.equals("_30_new_energy_monday_3")) {
               var6 = 27;
            }
            break;
         case 572844717:
            if (var5.equals("_283_media_titls_2")) {
               var6 = 28;
            }
            break;
         case 728378148:
            if (var5.equals("automatic_defogging")) {
               var6 = 29;
            }
            break;
         case 731324130:
            if (var5.equals("_30_seat_status_change_tip")) {
               var6 = 30;
            }
            break;
         case 914593453:
            if (var5.equals("_213_car_set15_76")) {
               var6 = 31;
            }
            break;
         case 1085558024:
            if (var5.equals("_30_new_energy_53_B_1")) {
               var6 = 32;
            }
            break;
         case 1085558025:
            if (var5.equals("_30_new_energy_53_B_2")) {
               var6 = 33;
            }
            break;
         case 1085558026:
            if (var5.equals("_30_new_energy_53_B_3")) {
               var6 = 34;
            }
            break;
         case 1085558985:
            if (var5.equals("_30_new_energy_53_C_1")) {
               var6 = 35;
            }
            break;
         case 1085558986:
            if (var5.equals("_30_new_energy_53_C_2")) {
               var6 = 36;
            }
            break;
         case 1876281142:
            if (var5.equals("_29_rear_view_parking_guide_line")) {
               var6 = 37;
            }
            break;
         case 2022671608:
            if (var5.equals("automatic_dehumidification")) {
               var6 = 38;
            }
      }

      switch (var6) {
         case 0:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 3, 1, var4);
            break;
         case 1:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 1, var4);
            break;
         case 2:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 1, var4);
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)(2 - var4)});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)(2 - var4)});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)(2 - var4)});
            break;
         case 6:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 3, 6, var4);
            break;
         case 7:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 6, var4);
            break;
         case 8:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 6, var4);
            break;
         case 9:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 3, 3, var4);
            break;
         case 10:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 3, var4);
            break;
         case 11:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 3, var4);
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)var4});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, (byte)var4});
            break;
         case 14:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 3, 4, var4);
            break;
         case 15:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 4, var4);
            break;
         case 16:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 4, var4);
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)(2 - var4)});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)(2 - var4)});
            break;
         case 19:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 3, 5, var4);
            break;
         case 20:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 5, var4);
            break;
         case 21:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 5, var4);
            break;
         case 22:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 3, 2, var4);
            break;
         case 23:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 2, var4);
            break;
         case 24:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 2, var4);
            break;
         case 25:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 3, 0, var4);
            break;
         case 26:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 0, var4);
            break;
         case 27:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 0, var4);
            break;
         case 28:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)(2 - var4)});
            break;
         case 29:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 65, (byte)var4});
            break;
         case 30:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)(2 - var4)});
            break;
         case 31:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)(2 - var4)});
            break;
         case 32:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 2, 7, var4);
            break;
         case 33:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 2, 7, var4);
            break;
         case 34:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(4)).getBytes(var1), 1, 1, var4);
            break;
         case 35:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(7)).getBytes(var1), 1, 7, var4);
            break;
         case 36:
            NewEnergyMethod.sendOneBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(7)).getBytes(var1), 1, 6, var4);
            break;
         case 37:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)var4});
            break;
         case 38:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 64, (byte)var4});
      }

   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__30_UiMgr(Context var1, int var2, int var3, int var4) {
      String var5 = (String)this.mSettingsItemArray.get(var2 << 8 | var3);
      var5.hashCode();
      var3 = var5.hashCode();
      byte var6 = -1;
      switch (var3) {
         case -707409785:
            if (var5.equals("_30_new_energy_53_C_10")) {
               var6 = 0;
            }
            break;
         case -680283355:
            if (var5.equals("service_interval_distance")) {
               var6 = 1;
            }
            break;
         case 494546837:
            if (var5.equals("_30_set_charge_amount_1")) {
               var6 = 2;
            }
            break;
         case 494546838:
            if (var5.equals("_30_set_charge_amount_2")) {
               var6 = 3;
            }
            break;
         case 524889158:
            if (var5.equals("_30_end_time_1")) {
               var6 = 4;
            }
            break;
         case 524889159:
            if (var5.equals("_30_end_time_2")) {
               var6 = 5;
            }
            break;
         case 648162385:
            if (var5.equals("brightness")) {
               var6 = 6;
            }
            break;
         case 1334211780:
            if (var5.equals("service_interval_duration")) {
               var6 = 7;
            }
            break;
         case 1449172680:
            if (var5.equals("_30_new_energy_53_4")) {
               var6 = 8;
            }
            break;
         case 1449172682:
            if (var5.equals("_30_new_energy_53_6")) {
               var6 = 9;
            }
            break;
         case 2049801759:
            if (var5.equals("_30_start_time_1")) {
               var6 = 10;
            }
            break;
         case 2049801760:
            if (var5.equals("_30_start_time_2")) {
               var6 = 11;
            }
            break;
         case 2104599649:
            if (var5.equals("_30_maximum_speed_limit")) {
               var6 = 12;
            }
      }

      switch (var6) {
         case 0:
            NewEnergyMethod.sendMultiBitChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(7)).getBytes(var1), 1, 0, 5, var4);
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte)DataHandleUtils.getMsb(var4), (byte)DataHandleUtils.getLsb(var4)});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -87, 8, (byte)(var4 * 10)});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -87, 9, (byte)(var4 * 10)});
            break;
         case 4:
            NewEnergyMethod.sendTimeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 2, var4);
            break;
         case 5:
            NewEnergyMethod.sendTimeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(4)).getBytes(var1), 3, var4);
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)(var4 + 1)});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 56, (byte)DataHandleUtils.getMsb(var4), (byte)DataHandleUtils.getLsb(var4)});
            break;
         case 8:
            NewEnergyMethod.sendTimeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(5)).getBytes(var1), 1, var4);
            break;
         case 9:
            NewEnergyMethod.sendTimeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(6)).getBytes(var1), 1, var4);
            break;
         case 10:
            NewEnergyMethod.sendTimeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(2)).getBytes(var1), 1, var4);
            break;
         case 11:
            NewEnergyMethod.sendTimeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(4)).getBytes(var1), 2, var4);
            break;
         case 12:
            var2 = var4 * 10;
            if (var4 == 8) {
               var2 = 254;
            }

            NewEnergyMethod.sendByteChangeCommand(((NewEnergyCommand)this.mNewEnergyCommandArray.get(10)).getBytes(var1), 3, var2);
      }

   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__30_UiMgr(int var1, int var2) {
      String var3 = (String)this.mSettingsItemArray.get(var1 << 8 | var2);
      var3.hashCode();
      if (var3.equals("_30_driving_mode_reset")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -102, 11, 1});
      }

   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__30_UiMgr(Context var1, int var2, int var3) {
      String var4 = (String)this.mSettingsItemArray.get(var2 << 8 | var3);
      var4.hashCode();
      var3 = var4.hashCode();
      byte var5 = -1;
      switch (var3) {
         case -1956782220:
            if (var4.equals("_30_eco_mode")) {
               var5 = 0;
            }
            break;
         case -1761460857:
            if (var4.equals("_30_approximate_range_fast_charge")) {
               var5 = 1;
            }
            break;
         case 118278321:
            if (var4.equals("_30_sport_mode")) {
               var5 = 2;
            }
            break;
         case 209300823:
            if (var4.equals("_30_approximate_range_normal")) {
               var5 = 3;
            }
            break;
         case 426113026:
            if (var4.equals("_30_new_energy_repeat_1")) {
               var5 = 4;
            }
            break;
         case 426113027:
            if (var4.equals("_30_new_energy_repeat_2")) {
               var5 = 5;
            }
            break;
         case 426113028:
            if (var4.equals("_30_new_energy_repeat_3")) {
               var5 = 6;
            }
            break;
         case 855415684:
            if (var4.equals("_30_com_mode")) {
               var5 = 7;
            }
      }

      switch (var5) {
         case 0:
            this.getMsgMgr(var1).pageSwitch("_30_eco_mode");
            break;
         case 1:
            this.getMsgMgr(var1).pageSwitch("_30_approximate_range_fast_charge");
            break;
         case 2:
            this.getMsgMgr(var1).pageSwitch("_30_sport_mode");
            break;
         case 3:
            this.getMsgMgr(var1).pageSwitch("_30_approximate_range_normal");
            break;
         case 4:
            this.getMsgMgr(var1).pageSwitch("_30_new_energy_53_0", "_30_new_energy_repeat_1");
            break;
         case 5:
            this.getMsgMgr(var1).pageSwitch("_30_new_energy_53_B_1", "_30_new_energy_repeat_2");
            break;
         case 6:
            this.getMsgMgr(var1).pageSwitch("_30_new_energy_53_B_2", "_30_new_energy_repeat_3");
            break;
         case 7:
            this.getMsgMgr(var1).pageSwitch("_30_com_mode");
      }

   }

   // $FF: synthetic method
   void lambda$new$6$com_hzbhd_canbus_car__30_UiMgr(Context var1, AmplifierActivity.AmplifierPosition var2, int var3) {
      int var4 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var2.ordinal()];
      byte[] var5;
      if (var4 != 1) {
         if (var4 == 2) {
            GeneralAmplifierData.leftRight = var3;
            var5 = this.m0x07Command;
            var5[3] = (byte)(var3 + 7);
            CanbusMsgSender.sendMsg(var5);
         }
      } else {
         GeneralAmplifierData.frontRear = var3;
         var5 = this.m0x07Command;
         var5[2] = (byte)(var3 + 7);
         CanbusMsgSender.sendMsg(var5);
      }

      this.getMsgMgr(var1).saveAmplifierData(var1);
   }

   // $FF: synthetic method
   void lambda$new$7$com_hzbhd_canbus_car__30_UiMgr(Context var1, AmplifierActivity.AmplifierBand var2, int var3) {
      int var4 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var2.ordinal()];
      byte[] var5;
      if (var4 != 1) {
         if (var4 != 2) {
            if (var4 == 3) {
               GeneralAmplifierData.bandTreble = var3;
               var5 = this.m0x08Command;
               var5[4] = (byte)var3;
               CanbusMsgSender.sendMsg(var5);
            }
         } else {
            GeneralAmplifierData.bandMiddle = var3;
            var5 = this.m0x08Command;
            var5[3] = (byte)var3;
            CanbusMsgSender.sendMsg(var5);
         }
      } else {
         GeneralAmplifierData.bandBass = var3;
         var5 = this.m0x08Command;
         var5[2] = (byte)var3;
         CanbusMsgSender.sendMsg(var5);
      }

      this.getMsgMgr(var1).saveAmplifierData(var1);
   }

   void setSettingsItemArray(SparseArray var1) {
      this.mSettingsItemArray = var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.meachId != 14) {
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

   }

   private class NewEnergyCommand {
      byte[] command;
      private final int msgDataType;
      int[] msgDatas;
      final UiMgr this$0;

      public NewEnergyCommand(UiMgr var1, int var2, int var3, int var4) {
         this.this$0 = var1;
         this.msgDataType = var2;
         byte[] var5 = new byte[var4 + 3];
         this.command = var5;
         var5[0] = 22;
         var5[1] = -87;
         var5[2] = (byte)var3;
      }

      byte[] getBytes(Context var1) {
         this.msgDatas = this.this$0.getMsgMgr(var1).getMsgDatas(this.msgDataType);
         return null;
      }
   }

   private static class NewEnergyMethod {
      public static void sendByteChangeCommand(byte[] var0, int var1, int var2) {
         var0[var1 + 2] = (byte)var2;
         CanbusMsgSender.sendMsg(var0);
      }

      public static void sendMultiBitChangeCommand(byte[] var0, int var1, int var2, int var3, int var4) {
         var1 += 2;
         var0[var1] = (byte)DataHandleUtils.setIntFromByteWithBit(var0[var1], var4, var2, var3);
         CanbusMsgSender.sendMsg(var0);
      }

      public static void sendOneBitChangeCommand(byte[] var0, int var1, int var2, int var3) {
         var1 += 2;
         var0[var1] = (byte)UiMgr.setIntByteWithBit(var0[var1], var2, var3);
         CanbusMsgSender.sendMsg(var0);
      }

      public static void sendTimeCommand(byte[] var0, int var1, int var2) {
         int var3 = var2 / 6;
         var2 %= 6;
         Log.i("_1030_UiMgr", "getTimeFromProgress: hour[" + var3 + "]  minute[" + var2 + "]");
         var0[var1 + 2] = (byte)((var2 & 7) << 5 | var3 & 31);
         CanbusMsgSender.sendMsg(var0);
      }
   }
}
