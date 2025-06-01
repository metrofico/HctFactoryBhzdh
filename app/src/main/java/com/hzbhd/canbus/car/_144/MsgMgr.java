package com.hzbhd.canbus.car._144;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private static boolean isWarnFirst;
   static int language;
   private static int outDoorTemp;
   private static int swc_data3;
   private static int up_dn_btn_data;
   private static int voice_btn_data;
   private final int DISMISS_PANORAMIC_DELAY = 5000;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private final String IS_SEAT_BELT_TIE = "is_seat_belt_tie";
   private final String IS_SEAT_CO_PILOT_BELT_TIE = "is_seat_co_pilot_belt_tie";
   private final String IS_SEAT_MASTER_DRIVER_BELT_TIE = "is_seat_master_driver_belt_tie";
   private final String IS_SEAT_R_L_BELT_TIE = "is_seat_r_l_belt_tie";
   private final String IS_SEAT_R_M_BELT_TIE = "is_seat_r_m_belt_tie";
   private final String IS_SEAT_R_R_BELT_TIE = "is_seat_r_r_belt_tie";
   private final String SWC_BTN_DATA_3 = "swc_btn_data_3";
   private final String SWC_DATA_3 = "swc_data_3";
   private final String TAG = "_144_MsgMgr";
   private int currentItem = 0;
   private boolean isDGear;
   private byte[] m0x41RadarData;
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusBtnPanelInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusSwcDataCopy;
   private byte[] mCanBusSwcInfoCopy;
   private byte[] mCanBusWarnInfoCopy;
   private Context mContext;
   TimerUtil mTimerUtil = new TimerUtil();
   private int nextItem;
   BroadcastReceiver receiver = new BroadcastReceiver(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         int var3 = var2.getIntExtra("selectPos1", 0);
         if ("REVERSING_SOUND".equals(var2.getAction())) {
            this.this$0.settingLeft0Right12Data(var3);
         }

      }
   };

   private void closePanoramic() {
      this.mTimerUtil.stopTimer();
      this.forceReverse(this.mContext, false);
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIntByBoolean(boolean var1) {
      return var1 ? 1 : 0;
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusAirInfoCopy)) {
         return true;
      } else {
         this.mCanBusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isBtnPanelMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusBtnPanelInfoCopy)) {
         return true;
      } else {
         this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanBusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_seat_belt_tie", false) != GeneralDoorData.isSeatBeltTie;
      }
   }

   private boolean isOnlyOutDoorDataChange() {
      return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0F) != (float)outDoorTemp;
   }

   private boolean isSwcDataMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcDataCopy)) {
         return true;
      } else {
         this.mCanBusSwcDataCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isSwcMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isWarningMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusWarnInfoCopy)) {
         return true;
      } else {
         this.mCanBusWarnInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isWarnFirst) {
            isWarnFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private void keyControl0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 21) {
                           if (var1 != 64) {
                              label58: {
                                 label41:
                                 switch (var1) {
                                    case 9:
                                       break;
                                    case 10:
                                       this.realKeyClick1(52);
                                       break label58;
                                    case 11:
                                       this.realKeyClick1(2);
                                       break label58;
                                    default:
                                       switch (var1) {
                                          case 13:
                                          case 17:
                                             break label41;
                                          case 14:
                                          case 18:
                                             break;
                                          case 15:
                                             this.realKeyClick1(49);
                                             break label58;
                                          case 16:
                                             this.realKeyClick1(50);
                                             break label58;
                                          case 19:
                                             this.realKeyClick1(185);
                                          default:
                                             break label58;
                                       }
                                    case 8:
                                       this.realKeyClick1(46);
                                       break label58;
                                 }

                                 this.realKeyClick1(45);
                              }
                           } else {
                              this.realKeyClick1(68);
                           }
                        } else {
                           Intent var2 = new Intent(this.mContext, WarningActivity.class);
                           var2.addFlags(268435456);
                           this.mContext.startActivity(var2);
                        }
                     } else {
                        this.realKeyClick1(14);
                     }
                  } else {
                     this.realKeyClick1(187);
                  }
               } else {
                  this.realKeyClick1(3);
               }
            } else {
               this.realKeyClick1(8);
            }
         } else {
            this.realKeyClick1(7);
         }
      } else {
         this.realKeyClick1(0);
      }

      byte[] var3 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var3[9], var3[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void openPanoramic() {
      this.forceReverse(this.mContext, true);
      this.mTimerUtil.stopTimer();
      this.mTimerUtil.startTimer(new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            MsgMgr var1 = this.this$0;
            var1.forceReverse(var1.mContext, false);
         }
      }, 5000L);
   }

   private void panelButtonClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick5(var2, var1, var3[4], var3[5]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      String var2;
      if (var1 >= 0 && var1 <= 250) {
         var2 = (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void setAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.mono = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.ac = var2;
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      int var1 = this.mCanBusInfoInt[6];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_auto_wind_model = false;
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 6) {
               switch (var1) {
                  case 11:
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     break;
                  case 12:
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     break;
                  case 13:
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     break;
                  case 14:
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
      outDoorTemp = this.mCanBusInfoInt[13];
      if (this.isOnlyOutDoorDataChange()) {
         SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", (float)outDoorTemp);
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      } else {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setCarDoorInfo() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      if (this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_seat_belt_tie", GeneralDoorData.isSeatBeltTie);
         this.updateDoorView(this.mContext);
      }

   }

   private void setCarStatusInfo() {
      int var1 = this.mCanBusInfoInt[3];
      boolean var2 = true;
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     var3 = "";
                  } else {
                     var3 = "D";
                  }
               } else {
                  var3 = "R";
               }
            } else {
               var3 = "N";
            }
         } else {
            var3 = "P";
         }
      } else {
         var3 = this.mContext.getResources().getString(2131768909);
      }

      if (this.mCanBusInfoInt[3] != 4) {
         var2 = false;
      }

      this.isDGear = var2;
      Log.i("_144_MsgMgr", "setCarStatusInfo: isDGear <-> " + this.isDGear);
      if (!this.isDGear) {
         this.closePanoramic();
      }

      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 0, var3));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingComputerInfo0() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 0, var2.append((float)((double)(var3[2] * 256 + var3[3]) * 0.1)).append("L/100km").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 1, var2.append(var3[4] * 256 + var3[5]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingComputerInfo1() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(2, 0, var3.append((float)((double)(var2[2] * 256 + var2[3]) * 0.1)).append("L/100km").toString()));
      var1.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[5] + "km/h"));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(2, 2, var4.append(var5[6] * 256 + var5[7]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.mCanBusInfoInt[5]);
   }

   private void setDrivingComputerInfo2() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(3, 0, var2.append((float)((double)(var3[2] * 256 + var3[3]) * 0.1)).append("L/100km").toString()));
      var1.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "km/h"));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(3, 2, var5.append(var4[6] * 256 + var4[7]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.mCanBusInfoInt[5]);
   }

   private void setDrivingPage(byte[] var1) {
      if (!this.isSwcDataMsgReturn(var1)) {
         int var2 = this.mCanBusInfoInt[4];
         if (var2 == 20 || var2 == 22) {
            this.startDrivingPage();
         }
      }

   }

   private void setFrontRearRadar() {
      if (!Arrays.equals(this.m0x41RadarData, this.mCanBusInfoByte)) {
         byte[] var1 = this.mCanBusInfoByte;
         this.m0x41RadarData = Arrays.copyOf(var1, var1.length);
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 8;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(7, var2[2] + 1, var2[3] + 1, var2[4] + 1, var2[5] + 1);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(7, var2[6] + 1, var2[7] + 1, var2[8] + 1, var2[9] + 1);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
         Log.i("_144_MsgMgr", "setFrontRearRadar: isDGear <-> " + this.isDGear);
         if (this.isDGear) {
            this.openPanoramic();
         }

      }
   }

   private void setLanguage() {
      int var2 = this.mCanBusInfoInt[2];
      int var1 = 16;
      if (var2 <= 16 && var2 != 0) {
         var1 = var2 - 1;
      } else {
         switch (var2) {
            case 18:
               break;
            case 19:
            case 21:
            case 24:
            case 27:
            default:
               var1 = 0;
               break;
            case 20:
               var1 = 17;
               break;
            case 22:
               var1 = 18;
               break;
            case 23:
               var1 = 19;
               break;
            case 25:
               var1 = 21;
               break;
            case 26:
               var1 = 20;
               break;
            case 28:
               var1 = 22;
               break;
            case 29:
               var1 = 23;
               break;
            case 30:
               var1 = 24;
               break;
            case 31:
               var1 = 25;
               break;
            case 32:
               var1 = 26;
               break;
            case 33:
               var1 = 27;
         }
      }

      language = var2;
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(1, 0, var1));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalPanel() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 1) {
         if (var1[3] > voice_btn_data) {
            this.realKeyClick4(7);
            voice_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < voice_btn_data) {
            this.realKeyClick4(8);
            voice_btn_data = this.mCanBusInfoInt[3];
         }
      } else {
         if (var1[3] > up_dn_btn_data) {
            this.realKeyClick4(46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      }

   }

   private void setPanelButton() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 17) {
               if (var1 != 64) {
                  if (var1 != 36) {
                     if (var1 != 37) {
                        switch (var1) {
                           case 6:
                              this.panelButtonClick(50);
                              break;
                           case 7:
                              this.panelButtonClick(52);
                              break;
                           case 8:
                              this.panelButtonClick(2);
                              break;
                           case 9:
                              this.panelButtonClick(3);
                              break;
                           case 10:
                              this.panelButtonClick(33);
                              break;
                           case 11:
                              this.panelButtonClick(34);
                              break;
                           case 12:
                              this.panelButtonClick(35);
                              break;
                           case 13:
                              this.panelButtonClick(36);
                              break;
                           case 14:
                              this.panelButtonClick(37);
                              break;
                           case 15:
                              this.panelButtonClick(38);
                              break;
                           default:
                              switch (var1) {
                                 case 22:
                                    this.panelButtonClick(129);
                                    break;
                                 case 23:
                                    this.panelButtonClick(45);
                                    break;
                                 case 24:
                                    this.panelButtonClick(46);
                                    break;
                                 case 25:
                                    this.panelButtonClick(45);
                                    break;
                                 case 26:
                                    this.panelButtonClick(46);
                                    break;
                                 default:
                                    switch (var1) {
                                       case 39:
                                          this.startDrivingPage();
                                          break;
                                       case 40:
                                          Intent var2 = new Intent(this.mContext, AirActivity.class);
                                          var2.addFlags(268435456);
                                          this.mContext.startActivity(var2);
                                          break;
                                       case 41:
                                          this.panelButtonClick(185);
                                          break;
                                       case 42:
                                          this.panelButtonClick(49);
                                          break;
                                       case 43:
                                          this.panelButtonClick(14);
                                          break;
                                       case 44:
                                          this.panelButtonClick(2);
                                          break;
                                       case 45:
                                          this.panelButtonClick(59);
                                          break;
                                       case 46:
                                          this.panelButtonClick(185);
                                          break;
                                       case 47:
                                          this.panelButtonClick(129);
                                          break;
                                       default:
                                          switch (var1) {
                                             case 49:
                                                this.panelButtonClick(59);
                                                break;
                                             case 50:
                                                this.panelButtonClick(134);
                                                break;
                                             case 51:
                                                this.panelButtonClick(128);
                                          }
                                    }
                              }
                        }
                     } else {
                        this.panelButtonClick(50);
                     }
                  } else {
                     this.panelButtonClick(49);
                  }
               } else {
                  this.startDrivingPage();
               }
            } else {
               this.panelButtonClick(31);
            }
         } else {
            this.panelButtonClick(134);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void setUnit() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]))));
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2) != 3) {
         var1.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2)));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVehicleSet() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(0, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
      var3.add(new SettingUpdateEntity(0, 1, this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))));
      var3.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
      var3.add(new SettingUpdateEntity(0, 3, this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var3.add((new SettingUpdateEntity(0, 4, var1)).setProgress(var1));
      var3.add(new SettingUpdateEntity(0, 5, this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]))));
      var3.add(new SettingUpdateEntity(0, 6, this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]))));
      var3.add(new SettingUpdateEntity(0, 7, this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]))));
      var3.add(new SettingUpdateEntity(0, 8, this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]))));
      var3.add(new SettingUpdateEntity(0, 9, this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]))));
      var3.add(new SettingUpdateEntity(0, 10, this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))));
      var3.add(new SettingUpdateEntity(0, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
      System.putInt(this.mContext.getContentResolver(), "left0right3", var2);
      System.putInt(this.mContext.getContentResolver(), "left0right4", var1);
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWarningInfo() {
      String var2 = Integer.toHexString(this.mCanBusInfoInt[2]);
      String var1 = var2;
      if (var2.length() == 1) {
         var1 = "0" + var2;
      }

      String var3 = Integer.toHexString(this.mCanBusInfoInt[3]);
      var2 = var3;
      if (var3.length() == 1) {
         var2 = "0" + var3;
      }

      var2 = "psa_hiword_" + var1 + var2;
      ArrayList var4 = new ArrayList();
      var4.add(new WarningEntity(CommUtil.getStrByResId(this.mContext, var2)));
      GeneralWarningDataData.dataList = var4;
      this.updateWarningActivity((Bundle)null);
   }

   private void settingLeft0Right12Data(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 12, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void startDrivingPage() {
      Log.d("cwh", "startDrivingPage");
      int var1 = this.nextItem;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.currentItem = 3;
               this.nextItem = 0;
               this.startDrivingDataActivity(this.mContext, 3);
            }
         } else {
            this.currentItem = 2;
            this.nextItem = 2;
            this.startDrivingDataActivity(this.mContext, 2);
         }
      } else {
         this.currentItem = 1;
         this.nextItem = 1;
         this.startDrivingDataActivity(this.mContext, 1);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 33) {
         if (var3 != 34) {
            if (var3 != 49) {
               if (var3 != 118) {
                  if (var3 != 148) {
                     if (var3 != 193) {
                        if (var3 != 240) {
                           if (var3 != 65) {
                              if (var3 != 66) {
                                 switch (var3) {
                                    case 17:
                                       this.keyControl0x11();
                                       this.setDrivingPage(var2);
                                       break;
                                    case 18:
                                       if (this.isDoorMsgReturn(var2)) {
                                          return;
                                       }

                                       this.setCarDoorInfo();
                                       this.setCarStatusInfo();
                                       break;
                                    case 19:
                                       this.setDrivingComputerInfo0();
                                       break;
                                    case 20:
                                       this.setDrivingComputerInfo1();
                                       break;
                                    case 21:
                                       this.setDrivingComputerInfo2();
                                 }
                              } else {
                                 if (this.isWarningMsgReturn(var2)) {
                                    return;
                                 }

                                 this.setWarningInfo();
                              }
                           } else {
                              this.setFrontRearRadar();
                           }
                        } else {
                           this.setVersionInfo();
                        }
                     } else {
                        this.setUnit();
                     }
                  } else {
                     this.setLanguage();
                  }
               } else {
                  this.setVehicleSet();
               }
            } else {
               if (this.isAirMsgReturn(var2)) {
                  return;
               }

               this.setAirData();
            }
         } else {
            if (this.isBtnPanelMsgReturn(var2)) {
               return;
            }

            this.setOriginalPanel();
         }
      } else {
         if (this.isSwcMsgReturn(var2)) {
            return;
         }

         this.setPanelButton();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var10, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      IntentFilter var2 = new IntentFilter();
      var2.addAction("REVERSING_SOUND");
      var1.registerReceiver(this.receiver, var2);
   }
}
