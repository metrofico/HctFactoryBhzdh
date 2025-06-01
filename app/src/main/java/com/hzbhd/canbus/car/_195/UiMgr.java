package com.hzbhd.canbus.car._195;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class UiMgr extends AbstractUiMgr {
   protected static final int CAR_ID_360_2015_2017 = 0;
   protected static final int CAR_ID_360_PLUS_2018 = 1;
   protected static final int CAR_ID_EI5_2019_LOW = 11;
   protected static final int CAR_ID_EI5_2019_MID = 12;
   protected static final int CAR_ID_I5_2019 = 10;
   protected static final int CAR_ID_I6_INTERNET_17_18 = 6;
   protected static final int CAR_ID_I6_INTERNET_19 = 8;
   protected static final int CAR_ID_I6_UNINTERNET_17_18 = 7;
   protected static final int CAR_ID_I6_UNINTERNET_19 = 9;
   protected static final int CAR_ID_RX3_2018 = 2;
   protected static final int CAR_ID_RX5_INTERNET_16_18 = 4;
   protected static final int CAR_ID_RX5_UNINTERNET_16_18 = 5;
   protected static final int CAR_ID_RX5_VERTICAL = 3;
   private static int mFrontWindMode;
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private int mDifferent;
   private FrontArea mFrontArea;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAcCmd(AIR_CMD.REAR_DEFOG);
            }
         } else {
            this.this$0.sendAcCmd(AIR_CMD.FRONT_DEFOG);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendAcCmd(AIR_CMD.AUTO);
                  }
               } else {
                  this.this$0.sendAcCmd(AIR_CMD.LOOP);
               }
            } else {
               this.this$0.sendAcCmd(AIR_CMD.AC);
            }
         } else {
            this.this$0.sendAcCmd(AIR_CMD.POWER);
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
      }

      public void onClickUp() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
      }

      public void onClickUp() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
      }

      public void onClickPlus() {
         this.this$0.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
      }

      public void onClickPlus() {
         this.this$0.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      this.airPageUiSet = var3;
      this.mFrontArea = var3.getFrontArea();
      this.mDifferent = this.getCurrentCarId();
      this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAcCmd(AIR_CMD.MODE);
         }

         public void onRightSeatClick() {
            this.this$0.sendAcCmd(AIR_CMD.MODE);
         }
      });
      this.mFrontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, null, null, this.mOnAirBtnClickListenerFrontBottom});
      this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight, null, null});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAcCmd(AIR_CMD.WIND_DN);
         }

         public void onClickRight() {
            this.this$0.sendAcCmd(AIR_CMD.WIND_UP);
         }
      });
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      if (this.isHaveCam360()) {
         boolean var2;
         if (SharePreUtil.getIntValue(var1, "__195_SAVE_RADAR_DISP", 0) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setShowRadar(var2);
         if (SharePreUtil.getIntValue(var1, "__195_SAVE_RADAR_DISP", 0) == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setShowCusPanoramicView(var2);
      } else {
         this.resetRadarAnd360();
      }

      var4.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var4) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void addViewToWindows() {
            if (this.val$parkPageUiSet.getPanoramicBtnList() != null) {
               this.val$parkPageUiSet.getPanoramicBtnList().clear();
            }

            ArrayList var2 = new ArrayList();
            if (this.this$0.mDifferent == 10) {
               var2.add(new ParkPageUiSet.Bean(0, "_194_low_speed_auto_open_360", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_car_line_off", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_2d", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_front", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_rear", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_left", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_right", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_front_left", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_front_right", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_rear_left", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_rear_right", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
               int var1 = MsgMgr.cam360Guideline;
               if (var1 != 0) {
                  if (var1 != 1) {
                     if (var1 != 2) {
                        if (var1 == 3) {
                           ((ParkPageUiSet.Bean)var2.get(1)).setTitleSrn("_194_car_line_static_dynamic");
                        }
                     } else {
                        ((ParkPageUiSet.Bean)var2.get(1)).setTitleSrn("_194_car_line_dynamic");
                     }
                  } else {
                     ((ParkPageUiSet.Bean)var2.get(1)).setTitleSrn("_194_car_line_static");
                  }
               } else {
                  ((ParkPageUiSet.Bean)var2.get(1)).setTitleSrn("_194_car_line_off");
               }

               var1 = MsgMgr.cam3603dMode;
               if (var1 != 0) {
                  if (var1 == 1) {
                     ((ParkPageUiSet.Bean)var2.get(2)).setTitleSrn("_194_3d");
                  }
               } else {
                  ((ParkPageUiSet.Bean)var2.get(2)).setTitleSrn("_194_2d");
                  var2.remove(7);
                  var2.remove(7);
                  var2.remove(7);
                  var2.remove(7);
               }
            } else {
               var2 = null;
               this.val$parkPageUiSet.setShowPanoramic(false);
            }

            this.val$parkPageUiSet.setPanoramicBtnList(var2);
         }
      });
      var4.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var4) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var3 = ((ParkPageUiSet.Bean)this.val$parkPageUiSet.getPanoramicBtnList().get(var1)).getTitleSrn();
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1875869257:
                  if (var3.equals("_194_front")) {
                     var4 = 0;
                  }
                  break;
               case -1865062998:
                  if (var3.equals("_194_right")) {
                     var4 = 1;
                  }
                  break;
               case -1257714884:
                  if (var3.equals("_194_car_line_static")) {
                     var4 = 2;
                  }
                  break;
               case -607519439:
                  if (var3.equals("_194_car_line_dynamic")) {
                     var4 = 3;
                  }
                  break;
               case -578563085:
                  if (var3.equals("_194_low_speed_auto_open_360")) {
                     var4 = 4;
                  }
                  break;
               case -366505392:
                  if (var3.equals("_194_rear_left")) {
                     var4 = 5;
                  }
                  break;
               case -129385052:
                  if (var3.equals("_194_2d")) {
                     var4 = 6;
                  }
                  break;
               case -129385021:
                  if (var3.equals("_194_3d")) {
                     var4 = 7;
                  }
                  break;
               case -87125151:
                  if (var3.equals("_194_car_line_off")) {
                     var4 = 8;
                  }
                  break;
               case 216558544:
                  if (var3.equals("_194_exit")) {
                     var4 = 9;
                  }
                  break;
               case 216748729:
                  if (var3.equals("_194_left")) {
                     var4 = 10;
                  }
                  break;
               case 216927318:
                  if (var3.equals("_194_rear")) {
                     var4 = 11;
                  }
                  break;
               case 691290516:
                  if (var3.equals("_194_front_right")) {
                     var4 = 12;
                  }
                  break;
               case 1011668540:
                  if (var3.equals("_194_car_line_static_dynamic")) {
                     var4 = 13;
                  }
                  break;
               case 1528895731:
                  if (var3.equals("_194_rear_right")) {
                     var4 = 14;
                  }
                  break;
               case 1823232399:
                  if (var3.equals("_194_front_left")) {
                     var4 = 15;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 14});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 15});
                  break;
               case 4:
                  if (MsgMgr.isCam360LowSpeedOpen) {
                     var4 = 16;
                  } else {
                     var4 = 17;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var4});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 7});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 13});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                  MsgMgr.mLast360st = false;
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 12});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 9});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
            }

         }
      });
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 0 && var2 == 12) {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, 12, 0});
               Context var3 = this.val$context;
               Toast.makeText(var3, var3.getText(2131769831), 0).show();
            }

         }
      });
      if (this.isHaveCam360()) {
         this.mMsgMgr.initRadarDisp(this.mContext);
      }

      if (this.getCurrentCarId() != 2 && this.getCurrentCarId() != 9 && this.getCurrentCarId() != 8) {
         this.airPageUiSet.getFrontArea().setShowCenterWheel(false);
      } else {
         this.airPageUiSet.getFrontArea().setShowCenterWheel(true);
      }

      var5.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var5) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (!var3.equals("_194_tire_pressure_reset")) {
               if (var3.equals("str_250_0_4")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
            }

         }
      });
      var5.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            this.this$0.setItemsCmds(var1, var2, var3);
         }
      });
   }

   protected static void sendAirCommandFrontWindMode() {
      int var0 = mFrontWindMode;
      CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, (new byte[]{1, 2, 3, 4})[var0]});
      CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, 0});
      var0 = mFrontWindMode + 1;
      mFrontWindMode = var0;
      if (var0 >= 4) {
         mFrontWindMode = 0;
      }

   }

   private void setCarIdCmd() {
      switch (this.getCurrentCarId()) {
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
            this.items_i6_2016_2017();
            break;
         case 8:
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
            this.items_i6_2019();
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 4});
            this.items_i5();
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 2});
            this.items_Ei5();
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 3});
            this.items_Ei5();
            break;
         default:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 0});
            this.items_rx3_2018();
      }

   }

   boolean isHaveCam360() {
      return this.getCurrentCarId() == 10;
   }

   void items_Ei5() {
      this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control", "_194_advanced_driver_assistance", "_194_light_set", "_194_window", "_194_comfortable_and_convenient_2", "_194_driving_maintenance_2", "_194_restore_factory_settings_2", "panorama_setting"});
      this.removeSettingRightItemByNameList(this.mContext, new String[]{"_194_steering_wheel_feels", "_194_driving_lock", "_194_unlock_mode", "_194_smart_unlock_the_car_near", "_194_driving_luosuo", "_194_partition_temperature_settings", "_194_tire_pressure_reset", "_194_ecodriving", "_194_a_foldable_outside_mirror_automatically"});
   }

   void items_i5() {
      this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control", "_194_comfortable_and_convenient_2", "_194_driving_maintenance_2", "_194_restore_factory_settings_2", "_194_reset", "_194_driving_assistance", "_194_instrument_brightness"});
      this.removeSettingRightItemByNameList(this.mContext, new String[]{"_194_a_foldable_outside_mirror_automatically", "_194_partition_temperature_settings", "_194_tire_pressure_reset"});
   }

   void items_i6_2016_2017() {
      this.removeSettingLeftItemByIndexRange(this.mContext, 0, 10);
      this.removeSettingLeftItemByNameList(this.mContext, new String[]{"panorama_setting"});
   }

   void items_i6_2019() {
      this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control", "_194_airconditioning_settings", "_194_driving_assistance", "_194_comfortable_and_convenient_2", "_194_driving_maintenance_2", "_194_restore_factory_settings_2", "_194_reset", "_194_advanced_driver_assistance", "_194_instrument_brightness", "_194_window", "panorama_setting"});
      this.removeSettingRightItemByNameList(this.mContext, new String[]{"_194_steering_wheel_feels", "_194_driving_luosuo", "_194_a_foldable_outside_mirror_automatically", "_194_vehicle_stability_control", "_194_welcome_light", "_194_welcome_lighting_time_of_the_lamp_holder", "_194_driving_lock"});
   }

   void items_rx3_2018() {
      this.removeSettingLeftItem(this.mContext, 12, 12);
      this.removeSettingLeftItem(this.mContext, 11, 11);
      this.removeSettingLeftItem(this.mContext, 10, 10);
      this.removeSettingLeftItem(this.mContext, 9, 9);
      this.removeSettingLeftItem(this.mContext, 8, 8);
      this.removeSettingLeftItem(this.mContext, 7, 7);
      this.removeSettingLeftItem(this.mContext, 6, 6);
      this.removeSettingLeftItem(this.mContext, 0, 5);
      this.removeSettingLeftItem(this.mContext, 0, 4);
      this.removeSettingRightItem(this.mContext, 0, 6, 6);
      this.removeSettingRightItem(this.mContext, 0, 5, 5);
      this.removeSettingRightItem(this.mContext, 2, 2, 2);
      this.removeSettingRightItem(this.mContext, 2, 1, 1);
      this.removeSettingLeftItemByNameList(this.mContext, new String[]{"panorama_setting"});
   }

   void resetRadarAnd360() {
      ParkPageUiSet var1 = this.getParkPageUiSet(this.mContext);
      var1.setShowRadar(true);
      var1.setShowCusPanoramicView(false);
   }

   void sendAcCmd(AIR_CMD var1) {
      switch (null.$SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[var1.ordinal()]) {
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 0});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 0});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 0});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 0});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 0});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 0});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 0});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 0});
            break;
         case 13:
            sendAirCommandFrontWindMode();
      }

   }

   void setItemsCmds(int var1, int var2, int var3) {
      switch (this.getCurrentCarId()) {
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
            this.setItems_i6_2016_2017(var1, var2, var3);
            break;
         case 8:
         case 9:
            this.setItems_i6_2019(var1, var2, var3);
            break;
         case 10:
            this.setItems_i5(var1, var2, var3);
            break;
         case 11:
         case 12:
            this.setItems_Ei5(var1, var2, var3);
            break;
         default:
            this.setItemsRx3_2018(var1, var2, var3);
      }

   }

   void setItemsRx3_2018(int var1, int var2, int var3) {
      if (var1 != 0) {
         if (var1 == 1) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 3, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte)var3});
            }
         }
      } else if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 3, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte)var3});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte)var3});
      }

   }

   void setItems_Ei5(int var1, int var2, int var3) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 == 5 && var2 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, 1, (byte)var3});
                  }
               } else if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 1, (byte)var3});
               }
            } else if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 2, (byte)var3});
            }
         } else if (var2 != 0) {
            if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte)var3});
         }
      } else if (var2 != 0) {
         if (var2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte)var3});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte)var3});
      }

   }

   void setItems_i5(int var1, int var2, int var3) {
      boolean var5 = false;
      switch (var1) {
         case 0:
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 4) {
                           if (var2 == 5) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 3, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 2, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1, (byte)var3});
            }
            break;
         case 1:
            if (var2 != 0) {
               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte)var3});
            }
            break;
         case 2:
            if (var2 != 0) {
               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 3, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 2, (byte)var3});
            }
            break;
         case 3:
            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 2, (byte)var3});
                  return;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 3, (byte)var3});
                  return;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 4, (byte)var3});
                  return;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 5, (byte)var3});
                  return;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 6, (byte)var3});
                  return;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 7, (byte)var3});
                  return;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 8, (byte)var3});
                  return;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 9, (byte)var3});
                  return;
               default:
                  return;
            }
         case 4:
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte)((byte)var3 + 2)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 4, (byte)((byte)var3 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 4, (byte)var3});
            }
            break;
         case 5:
            if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1, (byte)var3});
            }
            break;
         case 6:
            if (var2 != 0) {
               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
               }
            } else {
               SharePreUtil.setIntValue(this.mContext, "__195_SAVE_RADAR_DISP", var3);
               this.mMsgMgr.initRadarDisp(this.mContext);
               ParkPageUiSet var6 = this.getParkPageUiSet(this.mContext);
               boolean var4;
               if (var3 == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var6.setShowRadar(var4);
               var4 = var5;
               if (var3 == 0) {
                  var4 = true;
               }

               var6.setShowCusPanoramicView(var4);
            }
      }

   }

   void setItems_i6_2016_2017(int var1, int var2, int var3) {
      if (var1 != 0) {
         if (var1 == 1 && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
         }
      } else if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte)var3});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte)var3});
      }

   }

   void setItems_i6_2019(int var1, int var2, int var3) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2 && var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte)((byte)var3 + 2)});
            }
         } else if (var2 != 0) {
            if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 3, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
         }
      } else if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte)var3});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte)var3});
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.setCarIdCmd();
   }

   static enum AIR_CMD {
      private static final AIR_CMD[] $VALUES;
      AC,
      AUTO,
      FRONT_DEFOG,
      LEFT_SEAT_HEAT,
      LEFT_TEMP_DN,
      LEFT_TEMP_UP,
      LOOP,
      MODE,
      POWER,
      REAR_DEFOG,
      RIGHT_SEAT_HEAT,
      WIND_DN,
      WIND_UP;

      static {
         AIR_CMD var10 = new AIR_CMD("POWER", 0);
         POWER = var10;
         AIR_CMD var12 = new AIR_CMD("AC", 1);
         AC = var12;
         AIR_CMD var7 = new AIR_CMD("LOOP", 2);
         LOOP = var7;
         AIR_CMD var5 = new AIR_CMD("AUTO", 3);
         AUTO = var5;
         AIR_CMD var8 = new AIR_CMD("LEFT_SEAT_HEAT", 4);
         LEFT_SEAT_HEAT = var8;
         AIR_CMD var9 = new AIR_CMD("RIGHT_SEAT_HEAT", 5);
         RIGHT_SEAT_HEAT = var9;
         AIR_CMD var0 = new AIR_CMD("LEFT_TEMP_UP", 6);
         LEFT_TEMP_UP = var0;
         AIR_CMD var4 = new AIR_CMD("LEFT_TEMP_DN", 7);
         LEFT_TEMP_DN = var4;
         AIR_CMD var1 = new AIR_CMD("WIND_DN", 8);
         WIND_DN = var1;
         AIR_CMD var3 = new AIR_CMD("WIND_UP", 9);
         WIND_UP = var3;
         AIR_CMD var6 = new AIR_CMD("FRONT_DEFOG", 10);
         FRONT_DEFOG = var6;
         AIR_CMD var11 = new AIR_CMD("REAR_DEFOG", 11);
         REAR_DEFOG = var11;
         AIR_CMD var2 = new AIR_CMD("MODE", 12);
         MODE = var2;
         $VALUES = new AIR_CMD[]{var10, var12, var7, var5, var8, var9, var0, var4, var1, var3, var6, var11, var2};
      }
   }
}
