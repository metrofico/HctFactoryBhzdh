package com.hzbhd.canbus.car._2;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.interfaces.OnWarningStatusChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private String FRONT_RADAR_KEY = "FRONT_RADAR_KEY";
   private Context mContext;
   OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         if (GeneralAirData.front_left_seat_heat_level == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 1});
         }

         if (GeneralAirData.front_left_seat_heat_level == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 2});
         }

         if (GeneralAirData.front_left_seat_heat_level == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 3});
         }

         if (GeneralAirData.front_left_seat_heat_level == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, 0});
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         if (GeneralAirData.front_right_seat_heat_level == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 1});
         }

         if (GeneralAirData.front_right_seat_heat_level == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 2});
         }

         if (GeneralAirData.front_right_seat_heat_level == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 3});
         }

         if (GeneralAirData.front_right_seat_heat_level == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, 0});
         }

      }
   };
   MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     if (GeneralAirData.in_out_cycle) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, 0});
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, 1});
                     }
                  }
               } else {
                  boolean var2 = GeneralAirData.max_front;
               }
            } else if (GeneralAirData.aqs) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 1});
            }
         } else if (GeneralAirData.clean_air) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, 1});
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 1) {
            if (GeneralAirData.front_defog) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, 1});
            }
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               if (GeneralAirData.steering_wheel_heating) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 1});
               }
            }
         } else if (GeneralAirData.power) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, 1});
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
                     if (GeneralAirData.auto_wind_lv == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 1});
                     }

                     if (GeneralAirData.auto_wind_lv == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 2});
                     }

                     if (GeneralAirData.auto_wind_lv == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, 0});
                     }
                  }
               } else if (!GeneralAirData.auto) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 1});
               }
            } else if (!GeneralAirData.ac_max) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 2});
            }
         } else if (GeneralAirData.ac) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, 1});
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               if (GeneralAirData.rear_lock) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, 0});
               }
            }
         } else if (GeneralAirData.rear_power) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 0});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (GeneralAirData.front_wind_level > 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte)(GeneralAirData.front_wind_level - 1)});
         }

      }

      public void onClickRight() {
         if (GeneralAirData.front_wind_level < 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte)(GeneralAirData.front_wind_level + 1)});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (GeneralAirData.rear_wind_level > 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte)(GeneralAirData.rear_wind_level - 1)});
         }

      }

      public void onClickRight() {
         if (GeneralAirData.rear_wind_level < 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte)(GeneralAirData.rear_wind_level + 1)});
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, 1});
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 1});
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 1});
      }
   };
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_2_setting_1_4")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, 1, 0, 0, 0});
               UiMgr var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).tireSetting();
            }

         }
      });
      var2.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(64, 255);
         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -2115760223:
                  if (var4.equals("_2_set_language")) {
                     var5 = 0;
                  }
                  break;
               case 386686829:
                  if (var4.equals("_2_setting_11_1")) {
                     var5 = 1;
                  }
                  break;
               case 386686830:
                  if (var4.equals("_2_setting_11_2")) {
                     var5 = 2;
                  }
                  break;
               case 386686831:
                  if (var4.equals("_2_setting_11_3")) {
                     var5 = 3;
                  }
                  break;
               case 386686832:
                  if (var4.equals("_2_setting_11_4")) {
                     var5 = 4;
                  }
                  break;
               case 386686833:
                  if (var4.equals("_2_setting_11_5")) {
                     var5 = 5;
                  }
                  break;
               case 386686834:
                  if (var4.equals("_2_setting_11_6")) {
                     var5 = 6;
                  }
                  break;
               case 386687792:
                  if (var4.equals("_2_setting_12_3")) {
                     var5 = 7;
                  }
                  break;
               case 386687793:
                  if (var4.equals("_2_setting_12_4")) {
                     var5 = 8;
                  }
                  break;
               case 386687794:
                  if (var4.equals("_2_setting_12_5")) {
                     var5 = 9;
                  }
                  break;
               case 386687795:
                  if (var4.equals("_2_setting_12_6")) {
                     var5 = 10;
                  }
                  break;
               case 386687796:
                  if (var4.equals("_2_setting_12_7")) {
                     var5 = 11;
                  }
                  break;
               case 386688751:
                  if (var4.equals("_2_setting_13_1")) {
                     var5 = 12;
                  }
                  break;
               case 386688752:
                  if (var4.equals("_2_setting_13_2")) {
                     var5 = 13;
                  }
                  break;
               case 386688753:
                  if (var4.equals("_2_setting_13_3")) {
                     var5 = 14;
                  }
                  break;
               case 386688754:
                  if (var4.equals("_2_setting_13_4")) {
                     var5 = 15;
                  }
                  break;
               case 386688758:
                  if (var4.equals("_2_setting_13_8")) {
                     var5 = 16;
                  }
                  break;
               case 386690673:
                  if (var4.equals("_2_setting_15_1")) {
                     var5 = 17;
                  }
                  break;
               case 386691634:
                  if (var4.equals("_2_setting_16_1")) {
                     var5 = 18;
                  }
                  break;
               case 982305510:
                  if (var4.equals("_2_setting_0_0")) {
                     var5 = 19;
                  }
                  break;
               case 982306471:
                  if (var4.equals("_2_setting_1_0")) {
                     var5 = 20;
                  }
                  break;
               case 982306473:
                  if (var4.equals("_2_setting_1_2")) {
                     var5 = 21;
                  }
                  break;
               case 982307432:
                  if (var4.equals("_2_setting_2_0")) {
                     var5 = 22;
                  }
                  break;
               case 982307433:
                  if (var4.equals("_2_setting_2_1")) {
                     var5 = 23;
                  }
                  break;
               case 982307434:
                  if (var4.equals("_2_setting_2_2")) {
                     var5 = 24;
                  }
                  break;
               case 982307435:
                  if (var4.equals("_2_setting_2_3")) {
                     var5 = 25;
                  }
                  break;
               case 982308394:
                  if (var4.equals("_2_setting_3_1")) {
                     var5 = 26;
                  }
                  break;
               case 982308395:
                  if (var4.equals("_2_setting_3_2")) {
                     var5 = 27;
                  }
                  break;
               case 982308396:
                  if (var4.equals("_2_setting_3_3")) {
                     var5 = 28;
                  }
                  break;
               case 982308397:
                  if (var4.equals("_2_setting_3_4")) {
                     var5 = 29;
                  }
                  break;
               case 982308398:
                  if (var4.equals("_2_setting_3_5")) {
                     var5 = 30;
                  }
                  break;
               case 982308399:
                  if (var4.equals("_2_setting_3_6")) {
                     var5 = 31;
                  }
                  break;
               case 982310316:
                  if (var4.equals("_2_setting_5_1")) {
                     var5 = 32;
                  }
                  break;
               case 982311276:
                  if (var4.equals("_2_setting_6_0")) {
                     var5 = 33;
                  }
                  break;
               case 982312240:
                  if (var4.equals("_2_setting_7_3")) {
                     var5 = 34;
                  }
                  break;
               case 982314160:
                  if (var4.equals("_2_setting_9_1")) {
                     var5 = 35;
                  }
                  break;
               case 982314161:
                  if (var4.equals("_2_setting_9_2")) {
                     var5 = 36;
                  }
            }

            switch (var5) {
               case 0:
                  switch (var3) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 0});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 2});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 3});
                        return;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 4});
                        return;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 5});
                        return;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 6});
                        return;
                     case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 7});
                        return;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 8});
                        return;
                     case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 9});
                        return;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 10});
                        return;
                     case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 11});
                        return;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 13});
                        return;
                     case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 16});
                        return;
                     case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 17});
                        return;
                     case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 18});
                        return;
                     case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 22});
                        return;
                     case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 23});
                        return;
                     case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 29});
                        return;
                     case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 30});
                        return;
                     default:
                        return;
                  }
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -111, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -110, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -109, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -108, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -107, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -46, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -43, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -45, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -42, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -41, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -40, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -39, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -35, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var3});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte)var3});
                  break;
               case 25:
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)var3});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte)var3});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte)var3});
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte)var3});
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte)(var3 + 1)});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 86, (byte)var3});
                  break;
               case 34:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 94, (byte)var3});
                  break;
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 112, (byte)var3});
                  break;
               case 36:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 113, (byte)var3});
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case 386691635:
                  if (var4.equals("_2_setting_16_2")) {
                     var5 = 0;
                  }
                  break;
               case 386691636:
                  if (var4.equals("_2_setting_16_3")) {
                     var5 = 1;
                  }
                  break;
               case 386691637:
                  if (var4.equals("_2_setting_16_4")) {
                     var5 = 2;
                  }
                  break;
               case 982306472:
                  if (var4.equals("_2_setting_1_1")) {
                     var5 = 3;
                  }
                  break;
               case 982309357:
                  if (var4.equals("_2_setting_4_3")) {
                     var5 = 4;
                  }
                  break;
               case 982309358:
                  if (var4.equals("_2_setting_4_4")) {
                     var5 = 5;
                  }
                  break;
               case 982309359:
                  if (var4.equals("_2_setting_4_5")) {
                     var5 = 6;
                  }
                  break;
               case 982309360:
                  if (var4.equals("_2_setting_4_6")) {
                     var5 = 7;
                  }
                  break;
               case 982310320:
                  if (var4.equals("_2_setting_5_5")) {
                     var5 = 8;
                  }
                  break;
               case 982310321:
                  if (var4.equals("_2_setting_5_6")) {
                     var5 = 9;
                  }
                  break;
               case 982310322:
                  if (var4.equals("_2_setting_5_7")) {
                     var5 = 10;
                  }
                  break;
               case 982311277:
                  if (var4.equals("_2_setting_6_1")) {
                     var5 = 11;
                  }
                  break;
               case 982311278:
                  if (var4.equals("_2_setting_6_2")) {
                     var5 = 12;
                  }
                  break;
               case 982311279:
                  if (var4.equals("_2_setting_6_3")) {
                     var5 = 13;
                  }
                  break;
               case 982312238:
                  if (var4.equals("_2_setting_7_1")) {
                     var5 = 14;
                  }
                  break;
               case 982312239:
                  if (var4.equals("_2_setting_7_2")) {
                     var5 = 15;
                  }
                  break;
               case 982312241:
                  if (var4.equals("_2_setting_7_4")) {
                     var5 = 16;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 71, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 72, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 73, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 83, (byte)(var3 * 10)});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 84, (byte)(var3 * 5)});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte)(var3 * 5)});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 87, (byte)(var3 * 10)});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 88, (byte)(var3 * 10)});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 95, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 91, (byte)(var3 * 10)});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 92, (byte)(var3 * 10)});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 93, (byte)(var3 * 10)});
            }

         }
      });
      var2.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            int var5 = var6.hashCode();
            byte var4 = -1;
            switch (var5) {
               case -1562518496:
                  if (var6.equals("_326_radar_info_1")) {
                     var4 = 0;
                  }
                  break;
               case -897639932:
                  if (var6.equals("_2_setting_10_10")) {
                     var4 = 1;
                  }
                  break;
               case 386685868:
                  if (var6.equals("_2_setting_10_1")) {
                     var4 = 2;
                  }
                  break;
               case 386685869:
                  if (var6.equals("_2_setting_10_2")) {
                     var4 = 3;
                  }
                  break;
               case 386685870:
                  if (var6.equals("_2_setting_10_3")) {
                     var4 = 4;
                  }
                  break;
               case 386685871:
                  if (var6.equals("_2_setting_10_4")) {
                     var4 = 5;
                  }
                  break;
               case 386685872:
                  if (var6.equals("_2_setting_10_5")) {
                     var4 = 6;
                  }
                  break;
               case 386685873:
                  if (var6.equals("_2_setting_10_6")) {
                     var4 = 7;
                  }
                  break;
               case 386685874:
                  if (var6.equals("_2_setting_10_7")) {
                     var4 = 8;
                  }
                  break;
               case 386685875:
                  if (var6.equals("_2_setting_10_8")) {
                     var4 = 9;
                  }
                  break;
               case 386685876:
                  if (var6.equals("_2_setting_10_9")) {
                     var4 = 10;
                  }
                  break;
               case 386688755:
                  if (var6.equals("_2_setting_13_5")) {
                     var4 = 11;
                  }
                  break;
               case 386688756:
                  if (var6.equals("_2_setting_13_6")) {
                     var4 = 12;
                  }
                  break;
               case 386688757:
                  if (var6.equals("_2_setting_13_7")) {
                     var4 = 13;
                  }
                  break;
               case 386689712:
                  if (var6.equals("_2_setting_14_1")) {
                     var4 = 14;
                  }
                  break;
               case 386690674:
                  if (var6.equals("_2_setting_15_2")) {
                     var4 = 15;
                  }
                  break;
               case 386690675:
                  if (var6.equals("_2_setting_15_3")) {
                     var4 = 16;
                  }
                  break;
               case 386690676:
                  if (var6.equals("_2_setting_15_4")) {
                     var4 = 17;
                  }
                  break;
               case 982309355:
                  if (var6.equals("_2_setting_4_1")) {
                     var4 = 18;
                  }
                  break;
               case 982309356:
                  if (var6.equals("_2_setting_4_2")) {
                     var4 = 19;
                  }
                  break;
               case 982310317:
                  if (var6.equals("_2_setting_5_2")) {
                     var4 = 20;
                  }
                  break;
               case 982310318:
                  if (var6.equals("_2_setting_5_3")) {
                     var4 = 21;
                  }
                  break;
               case 982310319:
                  if (var6.equals("_2_setting_5_4")) {
                     var4 = 22;
                  }
                  break;
               case 982311280:
                  if (var6.equals("_2_setting_6_4")) {
                     var4 = 23;
                  }
                  break;
               case 982311281:
                  if (var6.equals("_2_setting_6_5")) {
                     var4 = 24;
                  }
                  break;
               case 982313199:
                  if (var6.equals("_2_setting_8_1")) {
                     var4 = 25;
                  }
                  break;
               case 982313200:
                  if (var6.equals("_2_setting_8_2")) {
                     var4 = 26;
                  }
                  break;
               case 982313201:
                  if (var6.equals("_2_setting_8_3")) {
                     var4 = 27;
                  }
                  break;
               case 982313202:
                  if (var6.equals("_2_setting_8_4")) {
                     var4 = 28;
                  }
                  break;
               case 982313203:
                  if (var6.equals("_2_setting_8_5")) {
                     var4 = 29;
                  }
                  break;
               case 982314162:
                  if (var6.equals("_2_setting_9_3")) {
                     var4 = 30;
                  }
                  break;
               case 982314163:
                  if (var6.equals("_2_setting_9_4")) {
                     var4 = 31;
                  }
                  break;
               case 982314164:
                  if (var6.equals("_2_setting_9_5")) {
                     var4 = 32;
                  }
                  break;
               case 982314165:
                  if (var6.equals("_2_setting_9_6")) {
                     var4 = 33;
                  }
            }

            switch (var4) {
               case 0:
                  UiMgr var7 = this.this$0;
                  var7.getMsgMgr(var7.mContext).updateSettings(this.this$0.mContext, "FRONT_RADAR_KEY", var1, var2, var3);
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -119, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -128, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -127, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -126, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -125, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -124, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -123, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -122, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -121, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -120, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -38, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -37, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -36, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -54, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -67, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 81, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 82, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -56, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 90, (byte)var3});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 89, (byte)var3});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)var3});
                  break;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte)var3});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 98, (byte)var3});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 99, (byte)var3});
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 100, (byte)var3});
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 114, (byte)var3});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 115, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 116, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -55, (byte)var3});
            }

         }
      });
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 == 1) {
               if (SharePreUtil.getBoolValue(this.this$0.mContext, "_2_park_voice", false)) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 1});
               }
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getRearArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerRear);
      var3.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerRearTop, null, null, null});
      var3.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontLeft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, null, null});
      var3.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(33, 0);
         }
      });
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(80, 0);
            this.this$0.activeRequest(22, 0);
         }
      });
      this.getTireUiSet(this.mContext).setOnTirePageStatusListener(new OnTirePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(101, 0);
            this.this$0.activeRequest(102, 0);
         }
      });
      this.getWarningPageUiSet(this.mContext).setOnWarningStatusChangeListener(new OnWarningStatusChangeListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(96, 0);
            this.this$0.activeRequest(97, 0);
            this.this$0.activeRequest(98, 0);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   public void activeRequest(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1, (byte)var2});
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }
}
