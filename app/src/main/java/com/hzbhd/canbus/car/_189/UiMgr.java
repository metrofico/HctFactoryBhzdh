package com.hzbhd.canbus.car._189;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Global;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;

public class UiMgr extends AbstractUiMgr {
   private int eachId;
   private AirActivity mActivity;
   private Context mContext;
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.airBtnClick(5, 0);
      }

      public void onClickPlus() {
         this.this$0.airBtnClick(5, 0);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.airBtnClick(5, 1);
      }

      public void onClickPlus() {
         this.this$0.airBtnClick(5, 1);
      }
   };
   private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        this.this$0.airBtnClick(5, 7);
                     }
                  } else {
                     this.this$0.airBtnClick(1, 3);
                  }
               } else {
                  this.this$0.airBtnClick(2, 0);
               }
            } else {
               this.this$0.airBtnClick(0, 5);
            }
         } else {
            this.this$0.airBtnClick(0, 1);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.airBtnClick(0, 0);
         } else if (var1 == 1) {
            this.this$0.airBtnClick(5, 6);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.airBtnClick(0, 7);
      }
   };
   private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (var1 == 1) {
            this.this$0.sendData(new byte[]{22, -112, 36});
            this.this$0.sendData(new byte[]{22, -112, 54});
         }

      }
   };
   private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        if (Global.getInt(this.this$0.mContext.getContentResolver(), "hzbhd_switch_ac_temperature", 0) == 1) {
                           this.this$0.airBtnClick(5, 0);
                        } else {
                           this.this$0.airBtnClick(5, 1);
                        }
                     }
                  } else {
                     this.this$0.airBtnClick(1, 2);
                  }
               } else {
                  this.this$0.airBtnClick(5, 5);
               }
            } else {
               this.this$0.airBtnClick(0, 4);
            }
         } else if (Global.getInt(this.this$0.mContext.getContentResolver(), "hzbhd_switch_ac_temperature", 0) == 1) {
            this.this$0.airBtnClick(5, 1);
         } else {
            this.this$0.airBtnClick(5, 0);
         }

      }
   };
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         var4.getMsgMgr(var4.mContext).updateSetting(var1, var2, var3);
         var4 = this.this$0;
         String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var4.getSettingUiSet(var4.mContext).getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var5.hashCode();
         if (!var5.equals("_189_car_setting_2")) {
            if (var5.equals("geely_title_9")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 37, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -128, 41, (byte)var3});
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var10 = this.this$0;
         var10.getMsgMgr(var10.mContext).updateSetting(var1, var2, var3);
         var10 = this.this$0;
         String var13 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var10.getSettingUiSet(var10.mContext).getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var13.hashCode();
         int var9 = var13.hashCode();
         byte var6 = 23;
         byte var7 = 21;
         byte var4 = 19;
         byte var8 = 17;
         byte var5 = 11;
         byte var12 = 9;
         byte var11 = -1;
         switch (var9) {
            case -2038413889:
               if (var13.equals("geely_auto_close_the_window_after_locking")) {
                  var11 = 0;
               }
               break;
            case -1803284089:
               if (var13.equals("_189_3d_view")) {
                  var11 = 1;
               }
               break;
            case -1782367495:
               if (var13.equals("front_collision_warning")) {
                  var11 = 2;
               }
               break;
            case -1532616569:
               if (var13.equals("_189_front_view")) {
                  var11 = 3;
               }
               break;
            case -1531228540:
               if (var13.equals("fisheye_correction")) {
                  var11 = 4;
               }
               break;
            case -1503791253:
               if (var13.equals("geely_double_flash_tips_when_door_open")) {
                  var11 = 5;
               }
               break;
            case -1313804765:
               if (var13.equals("lock_auto_close_position_light")) {
                  var11 = 6;
               }
               break;
            case -1101576519:
               if (var13.equals("language_status")) {
                  var11 = 7;
               }
               break;
            case -890108588:
               if (var13.equals("_189_rear_view")) {
                  var11 = 8;
               }
               break;
            case -607138901:
               if (var13.equals("baojun_car_set")) {
                  var11 = 9;
               }
               break;
            case -581860367:
               if (var13.equals("geely_theme_colors")) {
                  var11 = 10;
               }
               break;
            case -576028092:
               if (var13.equals("single_reversing_image")) {
                  var11 = 11;
               }
               break;
            case -574355914:
               if (var13.equals("geely_parking_unlock")) {
                  var11 = 12;
               }
               break;
            case -445499202:
               if (var13.equals("geely_daytime_running_lights")) {
                  var11 = 13;
               }
               break;
            case -436118719:
               if (var13.equals("geely_auto_close_position_light_after_locking")) {
                  var11 = 14;
               }
               break;
            case -260900821:
               if (var13.equals("geely_remote_lock_feedback")) {
                  var11 = 15;
               }
               break;
            case -168434144:
               if (var13.equals("auxiliary_follow_up_steering")) {
                  var11 = 16;
               }
               break;
            case -91583442:
               if (var13.equals("geely_speed_lock")) {
                  var11 = 17;
               }
               break;
            case -48647515:
               if (var13.equals("_189_track_view")) {
                  var11 = 18;
               }
               break;
            case 100725:
               if (var13.equals("esc")) {
                  var11 = 19;
               }
               break;
            case 77591115:
               if (var13.equals("_189_car_setting_1")) {
                  var11 = 20;
               }
               break;
            case 77591117:
               if (var13.equals("_189_car_setting_3")) {
                  var11 = 21;
               }
               break;
            case 77591118:
               if (var13.equals("_189_car_setting_4")) {
                  var11 = 22;
               }
               break;
            case 77591131:
               if (var13.equals("_189_car_setting_A")) {
                  var11 = 23;
               }
               break;
            case 77591132:
               if (var13.equals("_189_car_setting_B")) {
                  var11 = 24;
               }
               break;
            case 77591133:
               if (var13.equals("_189_car_setting_C")) {
                  var11 = 25;
               }
               break;
            case 77591134:
               if (var13.equals("_189_car_setting_D")) {
                  var11 = 26;
               }
               break;
            case 77591135:
               if (var13.equals("_189_car_setting_E")) {
                  var11 = 27;
               }
               break;
            case 77591136:
               if (var13.equals("_189_car_setting_F")) {
                  var11 = 28;
               }
               break;
            case 78151973:
               if (var13.equals("geely_low_speed_cue")) {
                  var11 = 29;
               }
               break;
            case 79766017:
               if (var13.equals("geely_electronic_assist_mode")) {
                  var11 = 30;
               }
               break;
            case 468255189:
               if (var13.equals("geely_breath_model")) {
                  var11 = 31;
               }
               break;
            case 536949215:
               if (var13.equals("geely_title_1")) {
                  var11 = 32;
               }
               break;
            case 536949216:
               if (var13.equals("geely_title_2")) {
                  var11 = 33;
               }
               break;
            case 536949217:
               if (var13.equals("geely_title_3")) {
                  var11 = 34;
               }
               break;
            case 536949218:
               if (var13.equals("geely_title_4")) {
                  var11 = 35;
               }
               break;
            case 536949219:
               if (var13.equals("geely_title_5")) {
                  var11 = 36;
               }
               break;
            case 536949220:
               if (var13.equals("geely_title_6")) {
                  var11 = 37;
               }
               break;
            case 536949221:
               if (var13.equals("geely_title_7")) {
                  var11 = 38;
               }
               break;
            case 536949222:
               if (var13.equals("geely_title_8")) {
                  var11 = 39;
               }
               break;
            case 649954453:
               if (var13.equals("geely_close_the_sunroof_after_locking")) {
                  var11 = 40;
               }
               break;
            case 846874949:
               if (var13.equals("dynamic_trace")) {
                  var11 = 41;
               }
               break;
            case 1037260512:
               if (var13.equals("geely_power_down_auo_unlock")) {
                  var11 = 42;
               }
               break;
            case 1043320734:
               if (var13.equals("geely_remote_lock_tune")) {
                  var11 = 43;
               }
               break;
            case 1152847106:
               if (var13.equals("voice_skylight")) {
                  var11 = 44;
               }
               break;
            case 1411874120:
               if (var13.equals("r_gear_position_exits_five_second_delay")) {
                  var11 = 45;
               }
               break;
            case 1564493009:
               if (var13.equals("_189_left_view")) {
                  var11 = 46;
               }
               break;
            case 1589098083:
               if (var13.equals("geely_smart_cornering_light")) {
                  var11 = 47;
               }
               break;
            case 1614664871:
               if (var13.equals("_189_steering_view")) {
                  var11 = 48;
               }
               break;
            case 1683822644:
               if (var13.equals("static_trace")) {
                  var11 = 49;
               }
               break;
            case 1698741364:
               if (var13.equals("_189_right_view")) {
                  var11 = 50;
               }
               break;
            case 1708548990:
               if (var13.equals("geely_fortification_prompt_type")) {
                  var11 = 51;
               }
               break;
            case 1720570364:
               if (var13.equals("geely_emergency_brake_auto")) {
                  var11 = 52;
               }
               break;
            case 1788772482:
               if (var13.equals("open_door_light_sign")) {
                  var11 = 53;
               }
               break;
            case 1790874862:
               if (var13.equals("_189_open_big_light")) {
                  var11 = 54;
               }
               break;
            case 1918412771:
               if (var13.equals("geely_boyue_1")) {
                  var11 = 55;
               }
               break;
            case 1918412772:
               if (var13.equals("geely_boyue_2")) {
                  var11 = 56;
               }
               break;
            case 1918412773:
               if (var13.equals("geely_boyue_3")) {
                  var11 = 57;
               }
               break;
            case 1918412774:
               if (var13.equals("geely_boyue_4")) {
                  var11 = 58;
               }
         }

         switch (var11) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 8, (byte)var3});
               break;
            case 1:
               var11 = var12;
               if (var3 == 1) {
                  var11 = 8;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 22, (byte)var3});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
               break;
            case 4:
               var11 = var8;
               if (var3 == 1) {
                  var11 = 16;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 5:
            case 53:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 2, (byte)var3});
               break;
            case 6:
            case 14:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 4, (byte)var3});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 26, (byte)var3});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
               break;
            case 9:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 20, (byte)var3});
               break;
            case 10:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 28, (byte)var3});
               break;
            case 11:
               var11 = var7;
               if (var3 == 1) {
                  var11 = 20;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 12:
            case 42:
            case 58:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 1, (byte)var3});
               break;
            case 13:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 10, (byte)var3});
               break;
            case 15:
            case 43:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 3, (byte)var3});
               break;
            case 16:
               var11 = var6;
               if (var3 == 1) {
                  var11 = 22;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 17:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 0, (byte)var3});
               break;
            case 18:
               if (var3 == 1) {
                  var11 = 6;
               } else {
                  var11 = 7;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 19:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 19, (byte)var3});
               break;
            case 20:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 40, (byte)var3});
               break;
            case 21:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 42, (byte)var3});
               break;
            case 22:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 43, (byte)var3});
               break;
            case 23:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 44, (byte)var3});
               break;
            case 24:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 45, (byte)var3});
               break;
            case 25:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 46, (byte)var3});
               break;
            case 26:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 47, (byte)var3});
               break;
            case 27:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 48, (byte)var3});
               break;
            case 28:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, -128, (byte)var3});
               break;
            case 29:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 27, (byte)var3});
               break;
            case 30:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 6, (byte)var3});
               break;
            case 31:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 18, (byte)var3});
               break;
            case 32:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 29, (byte)var3});
               break;
            case 33:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 30, (byte)var3});
               break;
            case 34:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 31, (byte)var3});
               break;
            case 35:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 32, (byte)var3});
               break;
            case 36:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 33, (byte)var3});
               break;
            case 37:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 34, (byte)var3});
               break;
            case 38:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 35, (byte)var3});
               break;
            case 39:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 36, (byte)var3});
               break;
            case 40:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 9, (byte)var3});
               break;
            case 41:
               if (var3 == 1) {
                  var11 = 14;
               } else {
                  var11 = 15;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 44:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, (byte)(var3 + 11), 0});
               break;
            case 45:
               var11 = var4;
               if (var3 == 1) {
                  var11 = 18;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 46:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
               break;
            case 47:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 5, (byte)var3});
               break;
            case 48:
               var11 = var5;
               if (var3 == 1) {
                  var11 = 10;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 49:
               if (var3 == 1) {
                  var11 = 12;
               } else {
                  var11 = 13;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var11});
               break;
            case 50:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
               break;
            case 51:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 17, (byte)var3});
               break;
            case 52:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 21, (byte)var3});
               break;
            case 54:
               if (var3 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 39, -1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 39, (byte)var3});
               }
               break;
            case 55:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 23, (byte)var3});
               break;
            case 56:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 24, (byte)var3});
               break;
            case 57:
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 25, (byte)var3});
         }

      }
   };
   private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (var1 == 0) {
            this.this$0.sendData(new byte[]{22, -112, 127});
            this.this$0.mContext.sendBroadcast(new Intent(com.hzbhd.canbus.car._237.MsgMgr.UPDATE_SETTING_ACTION));
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         int var2 = GeneralAirData.front_wind_level - 1;
         int var1 = var2;
         if (var2 < 0) {
            var1 = 0;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte)(var1 << 4 & 240), 0, 0, 0, 0});
      }

      public void onClickRight() {
         int var2 = GeneralAirData.front_wind_level + 1;
         int var1 = var2;
         if (var2 > 8) {
            var1 = 8;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte)(var1 << 4 & 240), 0, 0, 0, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.airBtnClick(5, 5);
      }

      public void onClickUp() {
         this.this$0.airBtnClick(5, 5);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.airBtnClick(3, 0);
      }

      public void onClickUp() {
         this.this$0.airBtnClick(3, 1);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.airBtnClick(4, 0);
      }

      public void onClickUp() {
         this.this$0.airBtnClick(4, 1);
      }
   };
   private MsgMgr msgMgr;
   private UiMgr uiMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.carInfo();
      int var2 = this.getCurrentCarId();
      if (var2 == 4 || var2 == 7 || var2 == 15 || var2 == 20 || var2 == 22 || var2 == 23 || var2 == 24 || var2 == 25 || var2 == 27 || var2 == 28 || var2 == 29) {
         this.getParkPageUiSet(var1).setShowRadar(true);
      }

      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var3.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
      var3.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      if (var2 == 4 || var2 == 6 || var2 == 7 || var2 == 9 || var2 == 10 || var2 == 11 || var2 == 12 || var2 == 13 || var2 == 15 || var2 == 16 || var2 == 17 || var2 == 18 || var2 == 19 || var2 == 21 || var2 == 22 || var2 == 23 || var2 == 24 || var2 == 25 || var2 == 27 || var2 == 28 || var2 == 29) {
         AirPageUiSet var4 = this.getAirUiSet(var1);
         var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
         var4.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
         var4.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatColdListener, this.mFrontRightSeatHeatColdListener});
         var4.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
         var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, this.mTempSetViewOnUpDownClickListenerCenter, this.mTempSetViewOnUpDownClickListenerRight});
         if (this.getCurrentCarId() != 11 && this.getCurrentCarId() != 12 && this.getCurrentCarId() != 18) {
            var4.getFrontArea().setShowPmValue(false);
         } else {
            var4.getFrontArea().setShowPmValue(true);
         }

         var4.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onLeftSeatClick() {
               this.this$0.airBtnClick(0, 6);
            }

            public void onRightSeatClick() {
               this.this$0.airBtnClick(0, 6);
            }
         });
      }

      this.getTireUiSet(var1).setOnTirePageStatusListener(new OnTirePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -112, 96});
            }

         }
      });
   }

   private void airBtnClick(int var1, int var2) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false)});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0});
                     CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0, 0});
                  CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0, 0, 0});
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0, 0, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0, 0, 0, 0});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0, 0, 0, 0, 0});
         CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0, 0, 0, 0, 0});
      }

   }

   private void carInfo() {
      int var1 = this.eachId;
      if (var1 != 1 && var1 != 5 && var1 != 6 && var1 != 21) {
         if (var1 == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 0});
         }

         if (this.eachId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 0});
         }

         if (this.eachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 1});
         }

         if (this.eachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 1});
         }

         if (this.eachId == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 3});
         }

         if (this.eachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 4});
         }

         if (this.eachId == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 5});
         }

         if (this.eachId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 6});
         }

         if (this.eachId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 7});
         }

         if (this.eachId == 14) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 8});
         }

         if (this.eachId == 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 9});
         }

         if (this.eachId == 16) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 10});
         }

         if (this.eachId == 17) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 11});
         }

         if (this.eachId == 18) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 12});
         }

         if (this.eachId == 19) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 13});
         }

         if (this.eachId == 22) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 13});
         }

         if (this.eachId == 20) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 14});
         }

         if (this.eachId == 23) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 15});
         }

         if (this.eachId == 24) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 16});
         }

         if (this.eachId == 29) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 16});
         }

         if (this.eachId == 25) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 17});
         }

         if (this.eachId == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 18});
         }

         if (this.eachId == 26) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 19});
         }

         if (this.eachId == 28) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 19});
         }

         if (this.eachId == 27) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 20});
         }

         if (this.eachId == 30) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, 21});
         }
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.getCurrentCarId() != 15 && this.getCurrentCarId() != 9) {
         this.removeMainPrjBtnByName(this.mContext, "drive_data");
      }

      switch (this.getCurrentCarId()) {
         case 0:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 1:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 2:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 3:
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 4:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 5:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 6:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 7:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.removeSettingRightItem(this.mContext, 1, 5, 11);
            this.removeSettingRightItemByNameList(this.mContext, new String[]{"geely_emergency_brake_auto", "front_collision_warning", "geely_boyue_1", "geely_boyue_2", "geely_boyue_3", "geely_boyue_4", "geely_auto_close_position_light_after_locking", "language_status", "voice_skylight"});
            break;
         case 8:
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 9:
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            this.removeDriveData(this.mContext, 0, 1);
            break;
         case 10:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.removeSettingRightItem(this.mContext, 1, 0, 13);
            this.removeSettingRightItem(this.mContext, 1, 7, 7);
            break;
         case 11:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 12:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 13:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 14:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 15:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            this.removeDriveData(this.mContext, 1, 1);
            break;
         case 16:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 17:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 18:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 19:
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 20:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.removeMainPrjBtnByName(this.mContext, "setting");
            break;
         case 21:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 22:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 23:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 24:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 25:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 26:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 27:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 28:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set18");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
            break;
         case 29:
            this.removeMainPrjBtnByName(this.mContext, "tire_info");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set1");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set2");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set3");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set4");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set5");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set6");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set7");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set8");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set9");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set10");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set11");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set12");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set13");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set14");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set16");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set17");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set15");
            this.remvoeSettingLeftItemByName(this.mContext, "car_set");
            this.remvoeSettingLeftItemByName(this.mContext, "panorama_setting");
      }

   }
}
