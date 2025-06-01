package com.hzbhd.canbus.car._265;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private int BackVideo = 1;
   private int FreqInt;
   private byte bandType;
   private int eachId;
   private byte freqHi;
   private byte freqLo;
   int[] mAirData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private int[] mTrackDataNow;
   private UiMgr uiMgr;

   private void OriginalCarTime0x28() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_Time");
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, this.resolveTime(var4[2], var4[3], var4[4], var4[5], var4[6], var4[7])));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isSendSourceState() {
      if (this.getCurrentCanDifferentId() != 2 && this.getCurrentCanDifferentId() != 3 && this.getCurrentCanDifferentId() != 4 && this.getCurrentCanDifferentId() != 5 && this.getCurrentCanDifferentId() != 6) {
         int var1 = this.eachId;
         if (var1 != 10 && var1 != 13 && var1 != 14 && var1 != 15 && var1 != 17 && this.getCurrentCanDifferentId() != 12 && this.getCurrentCanDifferentId() != 13 && this.getCurrentCanDifferentId() != 14) {
            return false;
         }
      }

      return true;
   }

   private boolean isTrackDataSame() {
      if (Arrays.equals(this.mTrackDataNow, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTrackDataNow = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private static boolean noNeedRepBtStatus(boolean var0, int var1) {
      return !var0 || var1 == 0;
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private void realKeyControl() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(7);
            break;
         case 2:
            this.realKeyClick(8);
            break;
         case 3:
            this.realKeyClick(45);
            break;
         case 4:
            this.realKeyClick(46);
            break;
         case 5:
            this.realKeyClick(14);
            break;
         case 6:
            this.realKeyClick(15);
            break;
         case 7:
            this.realKeyClick(2);
            break;
         case 8:
            this.realKeyClick(187);
            break;
         case 9:
            this.realKeyClick(50);
            break;
         case 10:
            this.realKeyClick(3);
            break;
         case 11:
            this.realKeyClick(204);
            break;
         case 12:
            this.realKeyClick(45);
            break;
         case 13:
            this.realKeyClick(46);
            break;
         case 14:
            this.realKeyClick(47);
            break;
         case 15:
            this.realKeyClick(48);
            break;
         case 16:
            this.realKeyClick(52);
            break;
         case 17:
            this.realKeyClick(49);
            break;
         case 18:
            this.realKeyClick(39);
      }

   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (254 == var1) {
         var2 = "HI";
      } else if (var1 >= 32 && var1 <= 56) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = (int)((double)(var2[3] * 256 + var2[4]) * 0.1);
      String var3;
      if (var1 >= 127) {
         var3 = "-" + (var1 - 6553) + this.getTempUnitC(this.mContext);
      } else {
         var3 = var1 + this.getTempUnitC(this.mContext);
      }

      return var3;
   }

   private String resolveParkingSystem(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131763005);
      } else {
         var2 = this.mContext.getResources().getString(2131763014);
      }

      return var2;
   }

   private String resolveTime(int var1, int var2, int var3, int var4, int var5, int var6) {
      int[] var7 = new int[]{var1, var2, var3, var4, var5, var6};
      String[] var8 = new String[]{"", "", "", "", "", ""};
      if (DataHandleUtils.getBoolBit7(var4)) {
         var1 = var7[3];
         if (var1 > 23) {
            var7[3] = var1 - 128;
         }
      }

      for(var1 = 0; var1 < 6; ++var1) {
         if (var7[var1] < 10) {
            var8[var1] = "0" + var7[var1];
         } else {
            var8[var1] = var7[var1] + "";
         }
      }

      return "20" + var8[0] + "-" + var8[1] + "-" + var8[2] + "-" + var8[3] + "-" + var8[4] + "-" + var8[5];
   }

   private void sendCarType() {
      if (this.eachId == 13) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
      }

      if (this.eachId == 14) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
      }

      if (this.eachId == 15) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
      }

   }

   private void setAUXInfo() {
      ArrayList var5 = new ArrayList();
      int var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_setting_car_info22");
      int var1;
      Resources var4;
      if (this.mCanBusInfoInt[2] == 0) {
         var4 = this.mContext.getResources();
         var1 = 2131759743;
      } else {
         var4 = this.mContext.getResources();
         var1 = 2131759744;
      }

      var5.add(new DriverUpdateEntity(var3, var2, var4.getString(var1)));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      var1 = this.mCanBusInfoInt[2];
      if (var1 == 0) {
         this.enterAuxIn2();
      } else if (var1 == 1) {
         this.exitAuxIn2();
      }

   }

   private void setAirData0x21() {
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2);
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  GeneralAirData.center_wheel = "FOCUS";
               }
            } else {
               GeneralAirData.center_wheel = "MEDIUM";
            }
         } else {
            GeneralAirData.center_wheel = "DIFFUSE";
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setCarSetData0x14() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 >= 0 && var1 < 51) {
         this.setBacklightLevel(5);
      } else if (var1 >= 51 && var1 < 102) {
         this.setBacklightLevel(4);
      } else if (var1 >= 102 && var1 < 153) {
         this.setBacklightLevel(3);
      } else if (var1 >= 153 && var1 < 204) {
         this.setBacklightLevel(2);
      } else if (var1 >= 204 && var1 <= 255) {
         this.setBacklightLevel(1);
      }

      var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769315);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769174);
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "keyboard_backlight_adjustment"), var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarSetting0x39() {
      ArrayList var2 = new ArrayList();
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[2];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               var1 = var3[3];
               if (var1 != 1) {
                  if (var1 == 2) {
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_189_car_setting_A"), this.mCanBusInfoInt[4]));
                  }
               } else {
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_337_setting_42"), this.mCanBusInfoInt[4]));
               }
            }
         } else {
            var1 = var3[3];
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_332_setting_5"), this.mCanBusInfoInt[4]));
                  }
               } else {
                  var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_332_setting_6"), this.mCanBusInfoInt[4]));
               }
            } else {
               var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_332_setting_9"), this.mCanBusInfoInt[4]));
            }
         }
      } else if (var3[3] == 1) {
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "language_setup"), this.mCanBusInfoInt[4]));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarSpeed() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(var4[2] * 256 + var4[3]).append("KM/h").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(var6[2] * 256 + var6[3]);
   }

   private void setDoorData0x41() {
      ArrayList var4 = new ArrayList();
      ArrayList var6 = new ArrayList();
      int[] var5 = this.mCanBusInfoInt;
      int var3 = var5[2];
      int var1 = 0;
      int var2 = 0;
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 128) {
               if (var3 == 129) {
                  var1 = var5[5];
                  byte var7;
                  if (var1 != 1 && var1 == 2) {
                     var7 = 1;
                  } else {
                     var7 = 0;
                  }

                  var3 = var5[6];
                  if (var3 != 6 && var3 == 7) {
                     var2 = 1;
                  }

                  var4 = new ArrayList();
                  var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_265_oreginal_camera_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_265_oreginal_camera_setting", "_265_oreginal_camera_setting1"), Integer.valueOf(var7)));
                  var4.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_265_oreginal_camera_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_265_oreginal_camera_setting", "_265_oreginal_camera_setting2"), var2));
                  this.updateGeneralSettingData(var4);
                  this.updateSettingActivity((Bundle)null);
               }
            } else {
               var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set1", "_246_setting_car_info28"), this.mCanBusInfoInt[4]));
               this.updateGeneralSettingData(var6);
               this.updateSettingActivity((Bundle)null);
            }
         } else {
            this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
            var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
            var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
            StringBuilder var8 = new StringBuilder();
            int[] var9 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(var2, var1, var8.append(var9[5] * 65536 + var9[6] * 256 + var9[7]).append(" Km").toString()));
            this.updateGeneralDriveData(var4);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else {
         if (var5.length > 3) {
            var1 = var5[3];
         }

         GeneralDoorData.isShowHandBrake = true;
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSubShowSeatBelt = true;
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(var1);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(var1);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(var1);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(var1);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(var1);
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit7(var1) ^ true;
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit6(var1) ^ true;
         GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit5(var1) ^ true;
         this.updateDoorView(this.mContext);
      }

   }

   private void setDriveData0x24() {
      ArrayList var5 = new ArrayList();
      int var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "light_info");
      int var1;
      Resources var4;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var4 = this.mContext.getResources();
         var1 = 2131769504;
      } else {
         var4 = this.mContext.getResources();
         var1 = 2131768042;
      }

      var5.add(new DriverUpdateEntity(var3, var2, var4.getString(var1)));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "gear_position");
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var4 = this.mContext.getResources();
         var1 = 2131768424;
      } else {
         var4 = this.mContext.getResources();
         var1 = 2131768423;
      }

      var5.add(new DriverUpdateEntity(var2, var3, var4.getString(var1)));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "reverse_state");
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var4 = this.mContext.getResources();
         var1 = 2131769841;
      } else {
         var4 = this.mContext.getResources();
         var1 = 2131769410;
      }

      var5.add(new DriverUpdateEntity(var2, var3, var4.getString(var1)));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setEngineSpeed() {
      ArrayList var4 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
      StringBuilder var5 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var1, var2, var5.append(var3[2] * 256 + var3[3]).append("RPM").toString()));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setIDriverInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 32) {
         if (var1 == 33) {
            DataHandleUtils.knob(this.mContext, 8, var2[3]);
            return;
         }

         switch (var1) {
            case 0:
               this.realKeyClick(0);
               return;
            case 1:
               this.realKeyClick(21);
               return;
            case 2:
               this.realKeyClick(20);
               return;
            case 3:
               this.realKeyClick(45);
               return;
            case 4:
               this.realKeyClick(46);
               return;
            case 5:
               this.realKeyClick(47);
               return;
            case 6:
               this.realKeyClick(48);
               return;
            case 7:
               this.realKeyClick(49);
               return;
            case 8:
               this.realKeyClick(151);
               return;
            case 9:
               this.realKeyClick(50);
               return;
            case 10:
               this.realKeyClick(1);
               return;
            case 11:
               this.realKeyClick(43);
               return;
            default:
               switch (var1) {
                  case 16:
                  case 17:
                  case 18:
                  case 19:
                     break;
                  default:
                     return;
               }
         }
      }

      DataHandleUtils.knob(this.mContext, 7, var2[3]);
   }

   private void setMousebuttoninfo0x30() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 == 1 && var1 == 1) {
         switch (var2[3]) {
            case 0:
               this.realKeyClick(0);
               break;
            case 1:
               this.realKeyClick(7);
               break;
            case 2:
               this.realKeyClick(8);
               break;
            case 3:
               this.realKeyClick(20);
               break;
            case 4:
               this.realKeyClick(21);
               break;
            case 5:
               this.realKeyClick(188);
            case 6:
            case 12:
            case 15:
            default:
               break;
            case 7:
               this.realKeyClick(128);
               break;
            case 8:
               this.realKeyClick(76);
               break;
            case 9:
               this.realKeyClick(1);
               break;
            case 10:
               this.realKeyClick(3);
               break;
            case 11:
               if (this.BackVideo == 1) {
                  this.forceReverse(this.mContext, true);
                  this.BackVideo = 2;
               } else {
                  this.forceReverse(this.mContext, false);
                  this.BackVideo = 1;
               }
               break;
            case 13:
               Intent var3 = new Intent();
               var3.setClass(this.mContext, SettingActivity.class);
               this.mContext.startActivity(var3);
               break;
            case 14:
               this.realKeyClick(57);
               break;
            case 16:
               this.realKeyClick(45);
               break;
            case 17:
               this.realKeyClick(46);
               break;
            case 18:
               this.realKeyClick(47);
               break;
            case 19:
               this.realKeyClick(48);
               break;
            case 20:
               this.realKeyClick(49);
               break;
            case 21:
               this.realKeyClick(50);
               break;
            case 22:
               this.realKeyClick(52);
         }
      }

      var2 = this.mCanBusInfoInt;
      if (var2[2] == 2) {
         if (this.mCanBusInfoByte[3] > 0) {
            DataHandleUtils.knob(this.mContext, 7, var2[3]);
         }

         if (this.mCanBusInfoByte[3] < 0) {
            DataHandleUtils.knob(this.mContext, 8, 256 - this.mCanBusInfoInt[3]);
         }
      }

   }

   private void setParkingState() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set1", "_246_paring_info2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_41_park_assist_system_status"), this.resolveParkingSystem(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearAirInfo() {
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.rear_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      this.updateAirActivity(this.mContext, 1002);
   }

   private void setRearRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrackInfo() {
      if (!this.isTrackDataSame()) {
         int[] var1 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte)var1[2], (byte)var1[3], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setVwRadioInfo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW")) {
         if (!var1.equals("AM1") && !var1.equals("LW")) {
            if (var1.equals("FM1")) {
               this.bandType = 1;
            } else if (var1.equals("FM2")) {
               this.bandType = 2;
            } else if (var1.equals("FM3") || var1.equals("OIRT")) {
               this.bandType = 3;
            }
         } else {
            int var3 = Integer.parseInt(var2);
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
            this.bandType = 17;
         }
      } else {
         this.bandType = 18;
      }

      this.getFreqByteHiLo(var1, var2);
   }

   private boolean telephoneStatus() {
      int var1 = this.eachId;
      return var1 == 5 || var1 == 6 || var1 == 7 || var1 == 10 || var1 == 12 || var1 == 16 || var1 == 17;
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      if (this.isSendSourceState()) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), new byte[]{22, -64, 3, 34});
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      if (this.isSendSourceState()) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -64, 7, 48});
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      } else {
         int var1 = this.eachId;
         if (var1 == 2 || var1 == 12 || var1 == 16) {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 8}, 36));
         }
      }

   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      if (this.isSendSourceState()) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -64, 11, 64});
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      } else {
         int var1 = this.eachId;
         if (var1 == 2 || var1 == 12 || var1 == 16) {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 11}, 36));
         }
      }

   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      if (this.telephoneStatus()) {
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 0, 1}, var1));
      }

   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      if (this.telephoneStatus()) {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 1, 1}, var1));
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      if (this.telephoneStatus()) {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 2, 1}, var1));
      }

   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (this.telephoneStatus()) {
         if (noNeedRepBtStatus(var3, var1)) {
            return;
         }

         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      if (this.telephoneStatus()) {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 3, 1}, var1));
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 6) {
         if (var3 == 7) {
            this.setCarSpeed();
         } else if (var3 != 8) {
            if (var3 == 20) {
               var3 = this.eachId;
               if (var3 == 12 || var3 == 13 || var3 == 14 || var3 == 15) {
                  return;
               }

               this.setCarSetData0x14();
            } else {
               if (var3 != 48) {
                  label247: {
                     label244: {
                        if (var3 != 57) {
                           if (var3 != 64) {
                              if (var3 != 65) {
                                 switch (var3) {
                                    case 32:
                                       this.realKeyControl();
                                       return;
                                    case 33:
                                       if (this.isAirMsgReturn(var2)) {
                                          return;
                                       }

                                       var3 = this.eachId;
                                       if (var3 == 1 || var3 == 2 || var3 == 10 || var3 == 11 || var3 == 12 || var3 == 13 || var3 == 14 || var3 == 15 || this.getCurrentCanDifferentId() == 12 || this.getCurrentCanDifferentId() == 13 || this.getCurrentCanDifferentId() == 14) {
                                          this.setAirData0x21();
                                       }

                                       return;
                                    case 34:
                                       var3 = this.eachId;
                                       if (var3 == 1 || var3 == 2 || var3 == 10 || var3 == 11 || var3 == 12 || var3 == 13 || var3 == 14 || var3 == 15 || this.getCurrentCanDifferentId() == 12 || this.getCurrentCanDifferentId() == 13 || this.getCurrentCanDifferentId() == 14) {
                                          this.setRearRadarInfo();
                                       }

                                       return;
                                    case 35:
                                       var3 = this.eachId;
                                       if (var3 == 1 || var3 == 2 || var3 == 10 || var3 == 11 || var3 == 12 || var3 == 13 || var3 == 14 || var3 == 15 || this.getCurrentCanDifferentId() == 12 || this.getCurrentCanDifferentId() == 13 || this.getCurrentCanDifferentId() == 14) {
                                          this.setFrontRadarInfo();
                                       }

                                       return;
                                    case 36:
                                       var3 = this.eachId;
                                       if (var3 == 13 || var3 == 14 || var3 == 15) {
                                          return;
                                       }

                                       this.setDriveData0x24();
                                       return;
                                    case 37:
                                       if (this.eachId == 12) {
                                          return;
                                       }

                                       this.setParkingState();
                                       return;
                                    case 38:
                                       this.setTrackInfo();
                                       return;
                                    case 39:
                                       var3 = this.eachId;
                                       if (var3 == 2 || var3 == 12 || var3 == 13 || var3 == 14 || var3 == 15 || this.getCurrentCanDifferentId() == 12 || this.getCurrentCanDifferentId() == 13 || this.getCurrentCanDifferentId() == 14) {
                                          this.setIDriverInfo();
                                       }

                                       return;
                                    case 40:
                                       var3 = this.eachId;
                                       if (var3 == 10 || var3 == 12 || var3 == 13 || var3 == 14 || var3 == 15 || this.getCurrentCanDifferentId() == 12 || this.getCurrentCanDifferentId() == 13 || this.getCurrentCanDifferentId() == 14) {
                                          this.OriginalCarTime0x28();
                                       }

                                       return;
                                    case 41:
                                       if (this.eachId == 11) {
                                          this.setMousebuttoninfo0x30();
                                       }
                                       break label247;
                                    default:
                                       return;
                                 }
                              }
                              break label244;
                           }
                        } else if (this.eachId == 12) {
                           this.setCarSetting0x39();
                        }

                        var3 = this.eachId;
                        if (var3 == 13 || var3 == 14 || var3 == 15 || this.getCurrentCanDifferentId() == 12 || this.getCurrentCanDifferentId() == 13 || this.getCurrentCanDifferentId() == 14) {
                           this.setAUXInfo();
                        }
                     }

                     if (this.isDoorMsgReturn(var2)) {
                        return;
                     }

                     var3 = this.eachId;
                     if (var3 != 1 && var3 != 12) {
                        this.setDoorData0x41();
                        return;
                     }

                     return;
                  }
               }

               this.setVersionInfo();
            }
         } else {
            this.setEngineSpeed();
         }
      } else {
         var3 = this.eachId;
         if (var3 == 1 || var3 == 2 || var3 == 10) {
            this.setRearAirInfo();
         }
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      var1 = this.eachId;
      if (var1 == 13 || var1 == 14 || var1 == 15) {
         this.sendCarType();
      }

      if (this.getCurrentCanDifferentId() != 0 && this.getCurrentCanDifferentId() != 1) {
         var1 = this.eachId;
         if (var1 != 10 && var1 != 12) {
            return;
         }
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte)(1 ^ var10), (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (this.isSendSourceState()) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -64, 2, 33});
         var3 = this.getMinute(var2);
         var2 = this.getSecond(var2);
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -61, (byte)var4, (byte)var6, 0, 0, (byte)var3, (byte)var2});
      } else {
         var2 = this.eachId;
         if (var2 == 2 || var2 == 12 || var2 == 16) {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Util.makeBytesFixedLength(new byte[]{22, -60, 6}, 36));
         }
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      if (this.isSendSourceState()) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), new byte[]{22, -64, 3, 34});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      int var2 = this.eachId;
      if (var2 == 13 || var2 == 14 || var2 == 15) {
         this.sendCarType();
         Log.d("Lai", this.eachId + "");
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (this.isSendSourceState()) {
         if (var1 == 9) {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -64, 9, 17});
         } else {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -64, 8, 17});
         }

         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
      } else {
         var3 = this.eachId;
         if (var3 == 2 || var3 == 12 || var3 == 16) {
            byte[] var25 = var23.getBytes();
            var25 = DataHandleUtils.byteMerger(new byte[]{22, -60, 9}, var25);
            byte[] var26 = var21.getBytes();
            var26 = DataHandleUtils.byteMerger(new byte[]{32, 45, 32}, var26);
            var25 = DataHandleUtils.byteMerger(var25, var26);
            byte[] var27 = var23.getBytes();
            var26 = DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -60, 10}, var27), var26);
            if (var1 == 9) {
               this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.limitDataLength(var26, 36));
            } else {
               this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.limitDataLength(var25, 36));
            }
         }
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.isSendSourceState()) {
         this.setVwRadioInfo(var2, var3);
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1});
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte)var1});
      } else {
         var1 = this.eachId;
         if (var1 == 2 || var1 == 12 || var1 == 16) {
            byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
            Context var8 = this.mContext;
            var2 = SourceConstantsDef.SOURCE_ID.FM.toString();
            byte[] var7 = var3.getBytes();
            this.sendMediaMsg(var8, var2, DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -60, var6}, var7), 36));
         }
      }

   }

   public void sourceSwitchDestroy() {
      if (this.isSendSourceState()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 34});
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      } else {
         int var1 = this.eachId;
         if (var1 == 2 || var1 == 12 || var1 == 16) {
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -60, 0}, 36));
         }
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (this.isSendSourceState()) {
         if (var1 == 9) {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, 9, 17});
         } else {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, 16, 17});
         }

         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
      } else {
         var14 = this.eachId;
         if (var14 == 2 || var14 == 12 || var14 == 16) {
            if (var1 == 9) {
               this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 10, (byte)(var4 >> 8 & 255), (byte)var3, var9}, 36));
            } else {
               this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 9, (byte)(var4 >> 8 & 255), (byte)var3, var9}, 36));
            }
         }
      }

   }
}
