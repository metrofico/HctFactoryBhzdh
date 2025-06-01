package com.hzbhd.canbus.car._260;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private int FreqInt;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_FRONT_OPEN = "is_front_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private byte freqHi;
   private byte freqLo;
   private boolean isMute;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private boolean mIsHfpConnected;
   private UiMgr uiMgr = null;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void emergencyCallStatusInfo(Context var1) {
      if (this.mCanBusInfoInt[2] == 1) {
         if (!this.isMute) {
            this.realKeyClick(this.mContext, 3);
         }
      } else if (this.isMute) {
         this.realKeyClick(this.mContext, 3);
      }

   }

   private void frontRadarInfo() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
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

   private String getCloseOpenStr(int var1) {
      if (var1 == 0) {
         return this.mContext.getResources().getString(2131768042);
      } else {
         return var1 == 1 ? this.mContext.getResources().getString(2131769504) : "set_default";
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

   private int getHsbMsbLsbResult(int var1, int var2, int var3) {
      return (var1 & '\uffff') << 16 | (var2 & 255) << 8 | var3 & 255;
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
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
         return SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) != GeneralDoorData.isFrontOpen;
      }
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void realKeyControl() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 != 7) {
                           if (var1 != 21) {
                              if (var1 != 22) {
                                 switch (var1) {
                                    case 9:
                                       this.realKeyClick(14);
                                       break;
                                    case 10:
                                       this.realKeyClick(188);
                                       break;
                                    case 11:
                                       this.realKeyClick(187);
                                 }
                              } else {
                                 this.realKeyClick(49);
                              }
                           } else {
                              this.realKeyClick(50);
                           }
                        } else {
                           this.realKeyClick(2);
                        }
                     } else {
                        this.realKeyClick(3);
                     }
                  } else {
                     this.realKeyClick3(this.mContext, 46, var1, var2[3]);
                  }
               } else {
                  this.realKeyClick3(this.mContext, 45, var1, var2[3]);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[2];
      String var2 = "";
      if (1 <= var1 && var1 <= 254) {
         var2 = (double)((float)this.mCanBusInfoInt[2] * 0.5F) - 39.5 + "" + this.getTempUnitC(this.mContext);
      } else if (var1 == 0) {
         var2 = "--";
      }

      return var2;
   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var3 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      byte var1 = 1;
      GeneralAirData.in_out_cycle = var3 ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2);
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 == 2) {
               GeneralAirData.center_wheel = "Fast";
            }
         } else {
            GeneralAirData.center_wheel = "Normal";
         }
      } else {
         GeneralAirData.center_wheel = "Soft";
      }

      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         this.updateAirActivity(this.mContext, 1001);
      }

      if (this.getCurrentCanDifferentId() == 1 || this.getCurrentCanDifferentId() == 2) {
         ArrayList var4 = new ArrayList();
         var2 = (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_0_0");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2) == 0) {
            var1 = 0;
         }

         var4.add(new SettingUpdateEntity(0, var2, Integer.valueOf(var1)));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarSetData0x14() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769315);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769174);
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 2, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (GeneralDoorData.isShowCarDoor && this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
         this.updateDoorView(this.mContext);
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = "D";
            }
         } else {
            var2 = "R";
         }
      } else {
         var2 = "P";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var2 = this.mContext.getResources().getString(2131769504);
      } else {
         var2 = this.mContext.getResources().getString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 1, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData0x28() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 3, var2.append(var3[2] + var3[3] * 256).append("Km/h").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 4, var2.append(var3[4] + var3[5] * 256).append("RPM").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(var4[2] + var4[3] * 256);
   }

   private void setRadarInfo() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5376, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void tripComputerInfo() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 0, var2.append((double)this.getMsbLsbResult(var3[2], var3[3]) / 10.0).append("L/100Km").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 1, var2.append((double)this.getMsbLsbResult(var3[4], var3[5]) / 10.0).append("Km/H").toString()));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 2, var5.append((double)this.getHsbMsbLsbResult(var4[6], var4[7], var4[8]) / 10.0).append("Km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void vehicleSettingFeedbackStatus() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_260_settingInfo_0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_260_settingInfo_0", "_260_settingInfo_0_1"), this.mCanBusInfoInt[2]));
      var1.add(new SettingUpdateEntity(1, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_1_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var1.add(new SettingUpdateEntity(1, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_1_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
      var1.add(new SettingUpdateEntity(2, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_2_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)));
      var1.add(new SettingUpdateEntity(2, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_2_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
      var1.add(new SettingUpdateEntity(2, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_2_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var1.add(new SettingUpdateEntity(2, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_2_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
      var1.add(new SettingUpdateEntity(2, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_2_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      var1.add(new SettingUpdateEntity(3, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_3_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
      var1.add(new SettingUpdateEntity(3, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_3_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
      var1.add(new SettingUpdateEntity(4, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_4_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
      var1.add(new SettingUpdateEntity(4, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_4_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1)));
      var1.add(new SettingUpdateEntity(4, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_4_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
      var1.add(new SettingUpdateEntity(5, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_5_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
      var1.add(new SettingUpdateEntity(5, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_5_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1)));
      if (this.getCurrentCanDifferentId() == 5 || this.getCurrentCanDifferentId() == 6) {
         var1.add(new SettingUpdateEntity(2, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_2_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
         var1.add(new SettingUpdateEntity(3, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_3_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
         var1.add(new SettingUpdateEntity(3, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_3_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
         var1.add(new SettingUpdateEntity(1, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_1_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2)));
         var1.add(new SettingUpdateEntity(1, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_1_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
         var1.add(new SettingUpdateEntity(1, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_1_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
         var1.add(new SettingUpdateEntity(1, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_1_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
         var1.add(new SettingUpdateEntity(3, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_3_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1)));
         var1.add(new SettingUpdateEntity(4, (Integer)this.uiMgr.settingPageIndex.get("_260_settingInfo_4_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      MyLog.temporaryTracking("刷新设置");
   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).sendMediaInfo(11, 0);

      byte[] var7;
      try {
         var7 = Util.exceptBOMHead(var1.getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 2}, var7));
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
      }

      try {
         var7 = Util.exceptBOMHead(var3.getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 3}, var7));
      } catch (UnsupportedEncodingException var5) {
         var5.printStackTrace();
      }

      try {
         var7 = Util.exceptBOMHead(var2.getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 4}, var7));
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
      }

   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 11});
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new CountDownTimer(this, 2000L, 500L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  try {
                     byte[] var1 = Util.exceptBOMHead("Tel Off".getBytes("unicode"));
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPhoneNumberState(this.this$1.this$0.SplicingByte(new byte[]{22, -53, 6}, var1));
                  } catch (UnsupportedEncodingException var2) {
                     var2.printStackTrace();
                  }

               }

               public void onTick(long var1) {
                  try {
                     byte[] var3 = Util.exceptBOMHead("Tel Off".getBytes("unicode"));
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPhoneNumberState(this.this$1.this$0.SplicingByte(new byte[]{22, -53, 6}, var3));
                  } catch (UnsupportedEncodingException var4) {
                     var4.printStackTrace();
                  }

               }
            }).start();
         }
      });
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -53, 1}, var1));

      try {
         var1 = Util.exceptBOMHead("There's a call".getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 6}, var1));
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -53, 1}, var1));

      try {
         byte[] var4 = Util.exceptBOMHead("Make a call".getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 6}, var4));
      } catch (UnsupportedEncodingException var5) {
         var5.printStackTrace();
      }

      this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -53, 1}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      byte var10;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 4) {
               var10 = 0;
            } else {
               var10 = 2;
            }
         } else {
            var10 = 3;
         }
      } else {
         var10 = 1;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, var10, 0, 0, 0, 0, 0});
      if (var4) {
         var2 = DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var2);
      } else {
         var2 = DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, new byte[]{0});
      }

      CanbusMsgSender.sendMsg(var2);
      if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.getUiMgr(this.mContext).sendPhoneNumber(this.SplicingByte(new byte[]{22, -53, 1}, var1));

      try {
         var1 = Util.exceptBOMHead("Talking".getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 6}, var1));
      } catch (UnsupportedEncodingException var5) {
         var5.printStackTrace();
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 48) {
            if (var3 != 51) {
               if (var3 != 64) {
                  if (var3 != 149) {
                     switch (var3) {
                        case 32:
                           this.realKeyControl();
                           break;
                        case 33:
                           if (this.isAirMsgReturn(var2)) {
                              return;
                           }

                           this.setAirData0x21();
                           break;
                        case 34:
                           this.setRadarInfo();
                           break;
                        case 35:
                           this.frontRadarInfo();
                           break;
                        case 36:
                           if (this.isDoorMsgReturn(var2)) {
                              return;
                           }

                           this.setDoorData0x24();
                           break;
                        default:
                           switch (var3) {
                              case 39:
                                 this.setAirData0x27();
                                 break;
                              case 40:
                                 this.setDriveData0x28();
                                 break;
                              case 41:
                                 this.setTrackInfo();
                           }
                     }
                  } else {
                     this.emergencyCallStatusInfo(var1);
                  }
               } else {
                  this.vehicleSettingFeedbackStatus();
               }
            } else {
               this.tripComputerInfo();
            }
         } else {
            this.setVersionInfo();
         }
      } else {
         this.setCarSetData0x14();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      MyLog.temporaryTracking("当前状态：" + var1 + "___" + var1);
      this.isMute = var2;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.uiMgr = this.getUiMgr(var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -53, 2}, new byte[]{0}));
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUiMgr(this.mContext).sendMediaInfo(8, 0);

      byte[] var28;
      try {
         var28 = Util.exceptBOMHead(var21.getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 2}, var28));
      } catch (UnsupportedEncodingException var27) {
         var27.printStackTrace();
      }

      try {
         var28 = Util.exceptBOMHead(var22.getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 3}, var28));
      } catch (UnsupportedEncodingException var26) {
         var26.printStackTrace();
      }

      try {
         var28 = Util.exceptBOMHead(var23.getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 4}, var28));
      } catch (UnsupportedEncodingException var25) {
         var25.printStackTrace();
      }

   }

   public void radioDestroy() {
      super.radioDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2.hashCode();
      int var7 = var2.hashCode();
      byte var11 = -1;
      switch (var7) {
         case 64901:
            if (var2.equals("AM1")) {
               var11 = 0;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var11 = 1;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var11 = 2;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var11 = 3;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var11 = 4;
            }
      }

      byte var6;
      switch (var11) {
         case 0:
         case 1:
            var6 = 16;
            break;
         case 2:
         default:
            var6 = 1;
            break;
         case 3:
            var6 = 2;
            break;
         case 4:
            var6 = 3;
      }

      this.getFreqByteHiLo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, var6, this.freqLo, this.freqHi, (byte)var1, 0, 0});

      try {
         StringBuilder var10 = new StringBuilder();
         byte[] var9 = Util.exceptBOMHead(var10.append("NOW BAND: ").append(var2).toString().getBytes("unicode"));
         this.getUiMgr(this.mContext).sendPhoneNumberState(this.SplicingByte(new byte[]{22, -53, 5}, var9));
      } catch (UnsupportedEncodingException var8) {
         var8.printStackTrace();
      }

   }

   public void videoDestroy() {
      super.videoDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = new byte[]{22, -64, 8, 19, (byte)var3, var9, 0, 0, 0, 0};
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(var18, var18));
      this.getUiMgr(this.mContext).sendMediaInfo(8, 0);
   }
}
