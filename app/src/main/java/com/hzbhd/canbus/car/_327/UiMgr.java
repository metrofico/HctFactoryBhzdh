package com.hzbhd.canbus.car._327;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   public static final String L0R0 = "Share_327_Settings_L0R0";
   public static final String L1R0 = "Share_327_Settings_L1R0";
   public static final String L2R0 = "Share_327_Settings_L2R0";
   public static final String L2R1 = "Share_327_Settings_L2R1";
   public static final String L3R0 = "Share_327_Settings_L3R0";
   public static final String L3R1 = "Share_327_Settings_L3R1";
   public static final String L4R0 = "Share_327_Settings_L4R0";
   public static final String L5R0 = "Share_327_Settings_L5R0";
   public static final String L6R0 = "Share_327_Settings_L6R0";
   public static final String L6R1 = "Share_327_Settings_L6R1";
   public static final String L6R2 = "Share_327_Settings_L6R2";
   public static final String L7R0 = "Share_327_Settings_L7R0";
   private static CountDownTimer mCountDownTimer;
   public static int outWinState;
   String CAR_TYPE = "car_type";
   int appointmentDay = 1;
   int appointmentHours = 0;
   int appointmentMins = 0;
   int appointmentMonth = 1;
   int appointmentYear = 2021;
   private OnConfirmDialogListener confirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         UiMgr var8 = this.this$0;
         if (var1 == var8.getLeftIndexes(var8.mContext, "_327_Reserve_charging") && var2 == 5 && this.this$0.compareTime()) {
            var8 = this.this$0;
            byte var7 = (byte)var8.getmMsgMgr(var8.mContext).nowYear;
            var8 = this.this$0;
            byte var3 = (byte)var8.getmMsgMgr(var8.mContext).nowMonth;
            var8 = this.this$0;
            byte var6 = (byte)var8.getmMsgMgr(var8.mContext).nowDay;
            var8 = this.this$0;
            byte var4 = (byte)var8.getmMsgMgr(var8.mContext).nowHours;
            var8 = this.this$0;
            byte var5 = (byte)var8.getmMsgMgr(var8.mContext).nowMins;
            var8 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -90, var7, var3, var6, var4, var5, (byte)var8.getmMsgMgr(var8.mContext).nowSecond});
            CanbusMsgSender.sendMsg(new byte[]{22, -122, 4, (byte)(this.this$0.appointmentYear - 2000), (byte)this.this$0.appointmentMonth, (byte)this.this$0.appointmentDay, (byte)this.this$0.appointmentHours, (byte)this.this$0.appointmentMins});
            Toast.makeText(this.this$0.mContext, "预约成功！时间：" + this.this$0.appointmentYear + "年" + this.this$0.appointmentMonth + "月" + this.this$0.appointmentDay + "日" + this.this$0.appointmentHours + ":" + this.this$0.appointmentMins, 0).show();
         }

      }
   };
   private int differentCanId;
   private int eachCanId;
   private Context mContext;
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 4) {
               this.this$0.sendModuleOne(4);
            }
         } else {
            this.this$0.sendModuleZero(16);
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
               this.this$0.sendModuleZero(1);
            }
         } else {
            this.this$0.sendModuleZero(2);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.this$0.sendModuleOne(4);
               }
            } else {
               this.this$0.sendModuleZero(128);
            }
         } else {
            this.this$0.sendModuleTwo(1);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendModuleZero(32);
            }
         } else {
            this.this$0.sendModuleZero(32);
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendModuleFive(1);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendModuleFive(2);
      }
   };
   private OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
      }
   };
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         byte var5;
         String var6;
         byte var8;
         label137: {
            String var7 = ((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getTitleSrn();
            var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var7.hashCode();
            int var4 = var7.hashCode();
            var5 = -1;
            switch (var4) {
               case -1608878688:
                  if (var7.equals("_327_setting_atmosphere_lamp")) {
                     var8 = 0;
                     break label137;
                  }
                  break;
               case 727030388:
                  if (var7.equals("_327_function_level_setting")) {
                     var8 = 1;
                     break label137;
                  }
                  break;
               case 918830394:
                  if (var7.equals("_327_Reserve_charging")) {
                     var8 = 2;
                     break label137;
                  }
                  break;
               case 1942215220:
                  if (var7.equals("_327_setting_the_light_that_accompanies_me_home")) {
                     var8 = 3;
                     break label137;
                  }
            }

            var8 = -1;
         }

         switch (var8) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -107, (byte)var3});
               break;
            case 1:
               var6.hashCode();
               if (!var6.equals("_327_Setting_SOC_target_value_of_charging_management")) {
                  if (var6.equals("_327_Energy_feedback_level")) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, (byte)var3});
               }
               break;
            case 2:
               var6.hashCode();
               switch (var6.hashCode()) {
                  case -1565752729:
                     if (!var6.equals("_327_day_setting")) {
                        var8 = var5;
                     } else {
                        var8 = 0;
                     }
                     break;
                  case 95947:
                     if (!var6.equals("_327_month_setting")) {
                        var8 = var5;
                     } else {
                        var8 = 1;
                     }
                     break;
                  case 263212090:
                     if (!var6.equals("_327_Hours_setting")) {
                        var8 = var5;
                     } else {
                        var8 = 2;
                     }
                     break;
                  case 1412554937:
                     if (!var6.equals("_327_branch_setting")) {
                        var8 = var5;
                     } else {
                        var8 = 3;
                     }
                     break;
                  case 1873199572:
                     if (!var6.equals("_327_year_setting")) {
                        var8 = var5;
                     } else {
                        var8 = 4;
                     }
                     break;
                  default:
                     var8 = var5;
               }

               UiMgr var9;
               switch (var8) {
                  case 0:
                     this.this$0.appointmentDay = var3;
                     var9 = this.this$0;
                     var9.getmMsgMgr(var9.mContext).updateAppointmentSettings(var1, var2, var3);
                     return;
                  case 1:
                     this.this$0.appointmentMonth = var3;
                     var9 = this.this$0;
                     var9.getmMsgMgr(var9.mContext).updateAppointmentSettings(var1, var2, var3);
                     return;
                  case 2:
                     this.this$0.appointmentHours = var3;
                     var9 = this.this$0;
                     var9.getmMsgMgr(var9.mContext).updateAppointmentSettings(var1, var2, var3);
                     return;
                  case 3:
                     var9 = this.this$0;
                     var9.getmMsgMgr(var9.mContext).updateAppointmentSettings(var1, var2, var3);
                     this.this$0.appointmentMins = var3;
                     return;
                  case 4:
                     this.this$0.appointmentYear = var3;
                     var9 = this.this$0;
                     var9.getmMsgMgr(var9.mContext).updateAppointmentSettings(var1, var2, var3);
                     return;
                  default:
                     return;
               }
            case 3:
               var6.hashCode();
               if (var6.equals("_327_setting_the_light_that_accompanies_me_home")) {
                  if (var3 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, 0});
                     this.this$0.MyCountDownTimer(var1, var2, 1000, 1000);
                  }

                  if (var3 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -127});
                     this.this$0.MyCountDownTimer(var1, var2, 20000, 1000);
                  }

                  if (var3 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -126});
                     this.this$0.MyCountDownTimer(var1, var2, 40000, 1000);
                  }

                  if (var3 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -125});
                     this.this$0.MyCountDownTimer(var1, var2, 60000, 1000);
                  }

                  if (var3 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -124});
                     this.this$0.MyCountDownTimer(var1, var2, 80000, 1000);
                  }

                  if (var3 == 5) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -123});
                     this.this$0.MyCountDownTimer(var1, var2, 100000, 1000);
                  }

                  if (var3 == 6) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -122});
                     this.this$0.MyCountDownTimer(var1, var2, 120000, 1000);
                  }

                  if (var3 == 7) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -121});
                     this.this$0.MyCountDownTimer(var1, var2, 140000, 1000);
                  }

                  if (var3 == 8) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -120});
                     this.this$0.MyCountDownTimer(var1, var2, 160000, 1000);
                  }

                  if (var3 == 9) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -119});
                     this.this$0.MyCountDownTimer(var1, var2, 180000, 1000);
                  }

                  if (var3 == 10) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -118});
                     this.this$0.MyCountDownTimer(var1, var2, 200000, 1000);
                  }

                  if (var3 == 11) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -117});
                     this.this$0.MyCountDownTimer(var1, var2, 220000, 1000);
                  }

                  if (var3 == 12) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -116});
                     this.this$0.MyCountDownTimer(var1, var2, 240000, 1000);
                  }

                  if (var3 == 13) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -115});
                     this.this$0.MyCountDownTimer(var1, var2, 260000, 1000);
                  }

                  if (var3 == 14) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -114});
                     this.this$0.MyCountDownTimer(var1, var2, 280000, 1000);
                  }
               }
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var6 = ((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getTitleSrn();
         var6.hashCode();
         int var5 = var6.hashCode();
         byte var4 = -1;
         switch (var5) {
            case -1608878688:
               if (var6.equals("_327_setting_atmosphere_lamp")) {
                  var4 = 0;
               }
               break;
            case -1608167093:
               if (var6.equals("_327_car_type")) {
                  var4 = 1;
               }
               break;
            case -1190553038:
               if (var6.equals("_327_source_setting")) {
                  var4 = 2;
               }
               break;
            case -1091443727:
               if (var6.equals("_327_function_mode_selection")) {
                  var4 = 3;
               }
               break;
            case -601302456:
               if (var6.equals("_327_setting_company")) {
                  var4 = 4;
               }
               break;
            case -595528139:
               if (var6.equals("_327_function_switch")) {
                  var4 = 5;
               }
               break;
            case -336041136:
               if (var6.equals("_327_setting_chair")) {
                  var4 = 6;
               }
               break;
            case -262995208:
               if (var6.equals("_327_setting_the_headlamps")) {
                  var4 = 7;
               }
               break;
            case 633894454:
               if (var6.equals("_327_setting_lane_departure")) {
                  var4 = 8;
               }
               break;
            case 994013058:
               if (var6.equals("_327_setting_environmental_lighting")) {
                  var4 = 9;
               }
               break;
            case 1230058363:
               if (var6.equals("_327_Face_recognition")) {
                  var4 = 10;
               }
               break;
            case 1942215220:
               if (var6.equals("_327_setting_the_light_that_accompanies_me_home")) {
                  var4 = 11;
               }
         }

         UiMgr var7;
         switch (var4) {
            case 0:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -108, (byte)var3});
               }
               break;
            case 1:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -18, -112, (byte)var3});
                  SharePreUtil.setIntValue(this.this$0.mContext, this.this$0.CAR_TYPE, var3);
                  var7 = this.this$0;
                  var7.getmMsgMgr(var7.mContext).updateSettings(this.this$0.mContext, "Share_327_Settings_L7R0", var1, var2, var3);
               }
               break;
            case 2:
               if (var2 == 0) {
                  if (var3 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -48, 0, 0});
                     Toast.makeText(this.this$0.mContext, "播放非原车CD", 0).show();
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 0});
                     Toast.makeText(this.this$0.mContext, "播放原车CD音源", 0).show();
                  }
               }
               break;
            case 3:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var3});
               }

               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte)(var3 + 1)});
               }

               if (var2 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, (byte)var3});
               }

               if (var2 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, (byte)var3});
               }

               if (var2 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, (byte)var3});
               }

               if (var2 == 5) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, (byte)var3});
               }

               if (var2 == 6) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, (byte)var3});
               }
               break;
            case 4:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -110, (byte)var3});
                  var7 = this.this$0;
                  var7.getmMsgMgr(var7.mContext).updateSettings(this.this$0.mContext, "Share_327_Settings_L0R0", var1, var2, var3);
               }
               break;
            case 5:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)(var3 + 1)});
               }

               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 1)});
               }

               if (var2 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte)(var3 + 1)});
               }

               if (var2 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte)(var3 + 1)});
               }

               if (var2 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte)(var3 + 1)});
               }

               if (var2 == 5) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte)(var3 + 1)});
               }

               if (var2 == 6) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte)(var3 + 1)});
               }

               if (var2 == 7) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, (byte)(var3 + 1)});
               }

               if (var2 == 8) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte)(var3 + 1)});
               }

               if (var2 == 9) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)(var3 + 1)});
               }

               if (var2 == 10) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte)(var3 + 1)});
               }

               if (var2 == 11) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, (byte)(var3 + 1)});
               }

               if (var2 == 12) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, (byte)(var3 + 1)});
               }

               if (var2 == 13) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, (byte)(var3 + 1)});
               }

               if (var2 == 14) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte)(var3 + 1)});
               }

               if (var2 == 15) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, (byte)(var3 + 1)});
               }

               if (var2 == 16) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, (byte)(var3 + 1)});
               }

               if (var2 == 17) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte)(var3 + 1)});
               }

               if (var2 == 18) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, (byte)(var3 + 1)});
               }

               if (var2 == 19) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -57, (byte)(var3 + 1)});
               }

               if (var2 == 20) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, (byte)(var3 + 1)});
               }

               if (var2 == 21) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, (byte)(var3 + 1)});
               }

               if (var2 == 22) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -67, (byte)(var3 + 1)});
               }

               if (var2 == 23) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, (byte)(var3 + 1)});
               }

               if (var2 == 24) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, (byte)(var3 + 1)});
               }

               if (var2 == 25) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte)(var3 + 1)});
               }

               if (var2 == 26) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -62, (byte)(var3 + 1)});
               }

               if (var2 == 27) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -61, (byte)(var3 + 1)});
               }
               break;
            case 6:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -109, (byte)var3});
               }
               break;
            case 7:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -104, (byte)var3});
               }
               break;
            case 8:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -105, (byte)var3});
               }
               break;
            case 9:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -103, (byte)var3});
               }

               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -102, (byte)(var3 + 1)});
               }

               if (var2 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -101, (byte)(var3 + 1)});
               }
               break;
            case 10:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -60, (byte)(var3 + 1)});
               }

               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -59, (byte)var3});
               }
               break;
            case 11:
               if (var2 == 0) {
                  if (var3 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, -122});
                     this.this$0.MyCountDownTimer(var1, var2 + 1, 120000, 1000);
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, 0});
                     this.this$0.MyCountDownTimer(var1, var2 + 1, 1000, 1000);
                  }
               }
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.frontLeftTempAdjust(1);
      }

      public void onClickUp() {
         this.this$0.frontLeftTempAdjust(2);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.frontRightTempAdjust(1);
      }

      public void onClickUp() {
         this.this$0.frontRightTempAdjust(2);
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         int var1 = UiMgr.outWinState;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.sendModuleTwo(192);
                           UiMgr.outWinState = 0;
                        }
                     } else {
                        this.this$0.sendModuleTwo(160);
                        UiMgr.outWinState = 5;
                     }
                  } else {
                     this.this$0.sendModuleTwo(128);
                     UiMgr.outWinState = 4;
                  }
               } else {
                  this.this$0.sendModuleTwo(96);
                  UiMgr.outWinState = 3;
               }
            } else {
               this.this$0.sendModuleTwo(64);
               UiMgr.outWinState = 2;
            }
         } else {
            this.this$0.sendModuleTwo(32);
            UiMgr.outWinState = 1;
         }

      }

      public void onRightSeatClick() {
         int var1 = UiMgr.outWinState;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.sendModuleTwo(192);
                           UiMgr.outWinState = 0;
                        }
                     } else {
                        this.this$0.sendModuleTwo(160);
                        UiMgr.outWinState = 5;
                     }
                  } else {
                     this.this$0.sendModuleTwo(128);
                     UiMgr.outWinState = 4;
                  }
               } else {
                  this.this$0.sendModuleTwo(96);
                  UiMgr.outWinState = 3;
               }
            } else {
               this.this$0.sendModuleTwo(64);
               UiMgr.outWinState = 2;
            }
         } else {
            this.this$0.sendModuleTwo(32);
            UiMgr.outWinState = 1;
         }

      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendModuleOne(1);
      }

      public void onClickRight() {
         this.this$0.sendModuleOne(2);
      }
   };
   OnPanelKeyPositionListener panelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 2});
                     Toast.makeText(this.this$0.mContext, "音量 -", 0).show();
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 1});
                  Toast.makeText(this.this$0.mContext, "音量 +", 0).show();
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -48, 0, 0});
               Toast.makeText(this.this$0.mContext, "播放非原车CD", 0).show();
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -48, 1, 0});
            Toast.makeText(this.this$0.mContext, "播放原车CD音源", 0).show();
         }

      }
   };
   OnPanoramicItemClickListener panoramicItemClickListener = new OnPanoramicItemClickListener(this) {
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
                        if (this.this$0.panoramicState != 8) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -89, 8});
                           this.this$0.panoramicState = 8;
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                           this.this$0.panoramicState = 0;
                        }
                     }
                  } else if (this.this$0.panoramicState != 7) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -89, 7});
                     this.this$0.panoramicState = 7;
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                     this.this$0.panoramicState = 0;
                  }
               } else if (this.this$0.panoramicState != 6) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 6});
                  this.this$0.panoramicState = 6;
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
                  this.this$0.panoramicState = 0;
               }
            } else if (this.this$0.panoramicState != 5) {
               CanbusMsgSender.sendMsg(new byte[]{22, -89, 5});
               this.this$0.panoramicState = 5;
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
               this.this$0.panoramicState = 0;
            }
         } else if (this.this$0.panoramicState != 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -89, 4});
            this.this$0.panoramicState = 4;
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -89, 0});
            this.this$0.panoramicState = 0;
         }

      }
   };
   private int panoramicState = 0;
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.differentCanId = this.getCurrentCarId();
      this.eachCanId = this.getEachId();
      this.CartypeSend();
      if (this.differentCanId == 3) {
         this.getAirUiSet(var1).setHaveRearArea(true);
      }

      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      var2.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
      this.settingPageUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      this.settingPageUiSet.setOnSettingConfirmDialogListener(this.confirmDialogListener);
      this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 65});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 66});
         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, null, null});
      var3.setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
         }
      });
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(this.panoramicItemClickListener);
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(this.panelKeyPositionListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 56});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 57});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 64});
         }
      });
   }

   private void frontLeftTempAdjust(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte)var1, 0, 0});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda0(), 100L);
   }

   private void frontRightTempAdjust(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, (byte)var1, 0});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda3(), 100L);
   }

   private MsgMgr getmMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initSettingsData(Context var1) {
      if (this.getLeftIndexes(var1, "_327_setting_company") != -1) {
         this.getmMsgMgr(this.mContext).updateSettings(this.mContext, "Share_327_Settings_L0R0", this.getLeftIndexes(var1, "_327_setting_company"), 0, SharePreUtil.getIntValue(this.mContext, "Share_327_Settings_L0R0", 0));
      }

      int var2 = this.getLeftIndexes(var1, "_327_setting_chair");
      MsgMgr var3;
      Context var4;
      if (var2 != -1) {
         var3 = this.getmMsgMgr(this.mContext);
         var4 = this.mContext;
         var3.updateSettings(var4, "Share_327_Settings_L1R0", var2, 0, SharePreUtil.getIntValue(var4, "Share_327_Settings_L1R0", 0));
      }

      var2 = this.getLeftIndexes(var1, "_327_setting_atmosphere_lamp");
      if (var2 != -1) {
         var3 = this.getmMsgMgr(this.mContext);
         var4 = this.mContext;
         var3.updateSettings(var4, "Share_327_Settings_L2R1", var2, 1, SharePreUtil.getIntValue(var4, "Share_327_Settings_L2R1", 0));
      }

      var2 = this.getLeftIndexes(var1, "_327_setting_the_light_that_accompanies_me_home");
      if (var2 != -1) {
         var3 = this.getmMsgMgr(this.mContext);
         var4 = this.mContext;
         var3.updateSettings(var4, "Share_327_Settings_L3R1", var2, 1, SharePreUtil.getIntValue(var4, "Share_327_Settings_L3R1", 0));
      }

      this.getLeftIndexes(var1, "_327_setting_lane_departure");
      var2 = this.getLeftIndexes(var1, "_327_setting_the_headlamps");
      Context var5;
      MsgMgr var6;
      if (var2 != -1) {
         var6 = this.getmMsgMgr(this.mContext);
         var5 = this.mContext;
         var6.updateSettings(var5, "Share_327_Settings_L5R0", var2, 0, SharePreUtil.getIntValue(var5, "Share_327_Settings_L5R0", 0));
      }

      var2 = this.getLeftIndexes(var1, "_327_setting_environmental_lighting");
      if (var2 != -1) {
         var6 = this.getmMsgMgr(this.mContext);
         var5 = this.mContext;
         var6.updateSettings(var5, "Share_327_Settings_L6R1", var2, 1, SharePreUtil.getIntValue(var5, "Share_327_Settings_L6R1", 0));
         var3 = this.getmMsgMgr(this.mContext);
         var4 = this.mContext;
         var3.updateSettings(var4, "Share_327_Settings_L6R2", var2, 2, SharePreUtil.getIntValue(var4, "Share_327_Settings_L6R2", 0));
      }

      var2 = this.getLeftIndexes(var1, "_327_car_type");
      if (var2 != -1) {
         if (this.getEachId() == 0) {
            var6 = this.getmMsgMgr(this.mContext);
            var5 = this.mContext;
            var6.updateSettings(var5, "Share_327_Settings_L7R0", var2, 0, SharePreUtil.getIntValue(var5, "Share_327_Settings_L7R0", 2));
         } else if (this.getEachId() == 3) {
            var3 = this.getmMsgMgr(this.mContext);
            var4 = this.mContext;
            var3.updateSettings(var4, "Share_327_Settings_L7R0", var2, 0, SharePreUtil.getIntValue(var4, "Share_327_Settings_L7R0", 1));
         } else if (this.getEachId() == 4) {
            var3 = this.getmMsgMgr(this.mContext);
            var4 = this.mContext;
            var3.updateSettings(var4, "Share_327_Settings_L7R0", var2, 0, SharePreUtil.getIntValue(var4, "Share_327_Settings_L7R0", 0));
         }
      }

      var2 = this.getLeftIndexes(this.mContext, "_327_Reserve_charging");
      if (var2 != -1) {
         String var7 = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date(System.currentTimeMillis()));
         this.appointmentYear = Integer.parseInt(var7.substring(0, 4));
         this.appointmentMonth = Integer.parseInt(var7.substring(4, 6));
         this.appointmentDay = Integer.parseInt(var7.substring(6, 8));
         this.appointmentHours = Integer.parseInt(var7.substring(8, 10));
         this.appointmentMins = Integer.parseInt(var7.substring(10, 12));
         this.getmMsgMgr(var1).updateAppointmentSettings(var2, 0, Integer.parseInt(var7.substring(0, 4)));
         this.getmMsgMgr(var1).updateAppointmentSettings(var2, 1, Integer.parseInt(var7.substring(4, 6)));
         this.getmMsgMgr(var1).updateAppointmentSettings(var2, 2, Integer.parseInt(var7.substring(6, 8)));
         this.getmMsgMgr(var1).updateAppointmentSettings(var2, 3, Integer.parseInt(var7.substring(8, 10)));
         this.getmMsgMgr(var1).updateAppointmentSettings(var2, 4, Integer.parseInt(var7.substring(10, 12)));
      }

   }

   private void initSettingsUi(Context var1) {
      if (this.eachCanId == 14) {
         this.removeMainPrjBtnByName(this.mContext, "tire_info");
      }

      if (this.eachCanId != 5) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_lane_departure"});
      }

      int var2 = this.eachCanId;
      if (var2 != 7 && var2 != 14) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_the_headlamps"});
      }

      if (this.differentCanId != 3) {
         this.removeMainPrjBtnByName(this.mContext, "panel_key");
      }

      var2 = this.eachCanId;
      if (var2 != 2 && var2 != 3 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 12 && var2 != 13 && var2 != 14) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_environmental_lighting"});
      }

      var2 = this.eachCanId;
      if (var2 != 8 && var2 != 11) {
         this.removeMainPrjBtn(var1, 3, 3);
      } else {
         if (var2 == 8) {
            this.removeDriveData(var1, "_327_Vehicle_information");
         }

         if (this.eachCanId == 11) {
            this.removeDriveData(var1, "_327_operation_information");
         }
      }

      if (this.eachCanId != 11) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_function_switch", "_327_function_level_setting", "_327_function_mode_selection", "_327_Face_recognition", "_327_Reserve_charging"});
      } else {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_327_setting_company", "_327_setting_chair", "_327_setting_atmosphere_lamp", "_327_setting_the_light_that_accompanies_me_home", "_327_setting_lane_departure", "_327_setting_the_headlamps", "_327_setting_environmental_lighting", "_327_car_type"});
      }

      if (this.eachCanId == 5) {
         this.getParkPageUiSet(var1).setShowPanoramic(true);
      }

   }

   // $FF: synthetic method
   static void lambda$frontLeftTempAdjust$3() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
   }

   // $FF: synthetic method
   static void lambda$frontRightTempAdjust$4() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
   }

   // $FF: synthetic method
   static void lambda$sendModuleFive$5() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
   }

   // $FF: synthetic method
   static void lambda$sendModuleOne$1() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
   }

   // $FF: synthetic method
   static void lambda$sendModuleTwo$2() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
   }

   // $FF: synthetic method
   static void lambda$sendModuleZero$0() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, 0});
   }

   private void sendModuleFive(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, 0, 0, (byte)var1});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda4(), 100L);
   }

   private void sendModuleOne(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)var1, 0, 0, 0, 0});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda2(), 100L);
   }

   private void sendModuleTwo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte)var1, 0, 0, 0});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda5(), 100L);
   }

   private void sendModuleZero(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)var1, 0, 0, 0, 0, 0});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda1(), 100L);
   }

   public void CartypeSend() {
      CanbusMsgSender.sendMsg(new byte[]{22, -18, -112, (byte)SharePreUtil.getIntValue(this.mContext, this.CAR_TYPE, 0)});
   }

   public void MyCountDownTimer(int var1, int var2, int var3, int var4) {
      CountDownTimer var5 = mCountDownTimer;
      if (var5 != null) {
         var5.cancel();
      }

      mCountDownTimer = (new CountDownTimer(this, (long)var3, (long)var4, var1, var2) {
         final UiMgr this$0;
         final int val$leftListIndex;
         final int val$rightListIndex;

         {
            this.this$0 = var1;
            this.val$leftListIndex = var6;
            this.val$rightListIndex = var7;
         }

         public void onFinish() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, 0});
         }

         public void onTick(long var1) {
            UiMgr var3 = this.this$0;
            var3.getmMsgMgr(var3.mContext).countDownTimeUpdateSettings(this.val$leftListIndex, this.val$rightListIndex, var1 / 1000L + 1L);
         }
      }).start();
   }

   public boolean compareTime() {
      if (this.appointmentYear == this.getmMsgMgr(this.mContext).nowYear && this.appointmentMonth == this.getmMsgMgr(this.mContext).nowMonth) {
         if ((this.appointmentDay - 1) * 24 * 60 + (this.appointmentHours - 1) * 60 + this.appointmentMins - ((this.getmMsgMgr(this.mContext).nowDay - 1) * 24 * 60 + (this.getmMsgMgr(this.mContext).nowHours - 1) * 60 + this.getmMsgMgr(this.mContext).nowMins) <= 2880) {
            return true;
         } else {
            Toast.makeText(this.mContext, "预约充电时间不能超过当前时间48小时", 0).show();
            return false;
         }
      } else {
         if (this.appointmentYear < this.getmMsgMgr(this.mContext).nowYear) {
            Toast.makeText(this.mContext, "指定时间不在预约范围内", 0).show();
         } else {
            Toast.makeText(this.mContext, "预约充电时间不能超过当前时间48小时", 0).show();
         }

         return false;
      }
   }

   protected int getLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initSettingsUi(var1);
      this.initSettingsData(var1);
   }
}
