package com.hzbhd.canbus.car._259;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_FRONT_OPEN = "is_front_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private int mCurrentCarId;
   private int mPreKeyState;
   private UiMgr mUiMgr;
   private byte[] mediaDataNow;

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

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
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
         return SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) != GeneralDoorData.isFrontOpen;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyControl() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 == 12) {
                                 if (this.mCurrentCarId == 1 && this.mPreKeyState == var2[5]) {
                                    LogUtil.showLog("send speech");
                                    this.realKeyClick(187);
                                 } else {
                                    this.mPreKeyState = var2[5];
                                    this.realKeyClick(2);
                                 }
                              }
                           } else {
                              this.realKeyClick(46);
                           }
                        } else {
                           this.realKeyClick(45);
                        }
                     } else if (this.mCurrentCarId == 1) {
                        this.realKeyClick(188);
                     } else {
                        this.realKeyClick(14);
                     }
                  } else if (this.mCurrentCarId == 0) {
                     this.realKeyClick(201);
                  } else {
                     this.realKeyClick(187);
                  }
               } else {
                  this.realKeyClick(3);
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

   private int resultRadar0(int var1) {
      int var2 = var1;
      if (var1 == 255) {
         var2 = 0;
      }

      return var2;
   }

   private int resultRadar2(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? 0 : 4;
      } else {
         return 1;
      }
   }

   private int resultRadar4(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? 0 : 7;
            } else {
               return 5;
            }
         } else {
            return 3;
         }
      } else {
         return 1;
      }
   }

   private void sendMediaMsg1(Context var1, String var2, byte[] var3) {
      Log.i("AbstractMsgMgr", "sendMediaMsg: context: " + var1 + ", media: " + var2);
      if (var1 != null) {
         String var4 = FutureUtil.instance.getCurrentValidSource().toString();
         Log.i("AbstractMsgMgr", "sendMediaMsg: frontSeat:" + var4);
         if (System.getInt(var1.getContentResolver(), "btState", 1) == 1 && (var2.equals(var4) || SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name().equals(var2)) && !Arrays.equals(var3, this.mediaDataNow)) {
            CanbusMsgSender.sendMsg(var3);
            this.mediaDataNow = Arrays.copyOf(var3, var3.length);
            if (!SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(var2)) {
               System.putString(var1.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(var3, 0));
               if (!SourceConstantsDef.SOURCE_ID.MPEG.toString().equals(var4)) {
                  System.putString(var1.getContentResolver(), "reportForDiscEject", Base64.encodeToString(var3, 0));
               }
            }
         }

      }
   }

   private void setAirData0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.ion = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         GeneralAirData.in_out_auto_cycle = 1;
      } else {
         GeneralAirData.in_out_auto_cycle = 0;
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         GeneralAirData.in_out_auto_cycle = 2;
      }

      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 6) {
               if (var1 != 11) {
                  if (var1 == 12) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      if (this.mCurrentCarId == 0) {
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      } else {
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarBodyInfo() {
      GeneralAirData.pm_value_level_in_car = this.mCanBusInfoInt[8] + "";
      GeneralAirData.pm_value_level_out_car = this.mCanBusInfoInt[9] + "";
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarSetData0x12() {
      ArrayList var1 = new ArrayList();
      if (this.mCurrentCarId == 0) {
         var1.add(new SettingUpdateEntity(1, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))));
         var1.add(new SettingUpdateEntity(1, 1, this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]))));
         var1.add(new SettingUpdateEntity(1, 2, this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))));
         var1.add(new SettingUpdateEntity(1, 3, this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]))));
         var1.add(new SettingUpdateEntity(1, 4, this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]))));
      } else {
         var1.add(new SettingUpdateEntity(1, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))));
         var1.add(new SettingUpdateEntity(1, 1, this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]))));
         var1.add(new SettingUpdateEntity(1, 2, this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))));
         var1.add(new SettingUpdateEntity(1, 3, this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]))));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarSetData0x61() {
      ArrayList var1 = new ArrayList();
      if (this.mCurrentCarId == 1) {
         var1.add(new SettingUpdateEntity(0, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]))));
         var1.add(new SettingUpdateEntity(0, 1, this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]))));
         var1.add(new SettingUpdateEntity(0, 2, this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]))));
         var1.add(new SettingUpdateEntity(0, 3, this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]))));
         var1.add(new SettingUpdateEntity(0, 4, this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]))));
      } else {
         var1.add(new SettingUpdateEntity(0, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData0x1a() {
      GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowRev = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowSteeringWheeAngle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowGearPosition = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowSpeed = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowLeftRightLight = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowAcc = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      if (GeneralDoorData.isShowCarDoor && this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
         this.updateDoorView(this.mContext);
      }

      int var1 = this.mCanBusInfoInt[7];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     var2 = "";
                  } else {
                     var2 = "D";
                  }
               } else {
                  var2 = "R";
               }
            } else {
               var2 = "N";
            }
         } else {
            var2 = "P";
         }
      } else {
         var2 = this.mContext.getResources().getString(2131768909);
      }

      ArrayList var4 = new ArrayList();
      String var3;
      if (GeneralDoorData.isShowRev) {
         if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            var3 = this.getCloseOpenStr(1);
         } else {
            var3 = this.getCloseOpenStr(0);
         }

         var4.add(new DriverUpdateEntity(0, 0, var3));
      }

      if (GeneralDoorData.isShowAcc) {
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            var3 = this.getCloseOpenStr(1);
         } else {
            var3 = this.getCloseOpenStr(0);
         }

         var4.add(new DriverUpdateEntity(0, 1, var3));
      }

      if (GeneralDoorData.isShowLeftRightLight) {
         if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
            var3 = this.getCloseOpenStr(1);
         } else {
            var3 = this.getCloseOpenStr(0);
         }

         var4.add(new DriverUpdateEntity(0, 2, var3));
         if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
            var3 = this.getCloseOpenStr(1);
         } else {
            var3 = this.getCloseOpenStr(0);
         }

         var4.add(new DriverUpdateEntity(0, 3, var3));
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
            var3 = this.getCloseOpenStr(1);
         } else {
            var3 = this.getCloseOpenStr(0);
         }

         var4.add(new DriverUpdateEntity(0, 4, var3));
      }

      int[] var9;
      if (GeneralDoorData.isShowSpeed) {
         StringBuilder var5 = new StringBuilder();
         var9 = this.mCanBusInfoInt;
         var4.add(new DriverUpdateEntity(0, 5, var5.append((float)(var9[6] + var9[5] * 256) / 10.0F).append(" km/h").toString()));
      }

      if (GeneralDoorData.isShowGearPosition) {
         var4.add(new DriverUpdateEntity(0, 6, var2));
      }

      StringBuilder var6 = new StringBuilder();
      var9 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(0, 7, var6.append(var9[12] + var9[11] * 256).append(" rps").toString()));
      Resources var7;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10])) {
         var7 = this.mContext.getResources();
         var1 = 2131768917;
      } else {
         var7 = this.mContext.getResources();
         var1 = 2131768916;
      }

      var4.add(new DriverUpdateEntity(0, 8, var7.getString(var1)));
      this.forceReverse(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      if (GeneralDoorData.isShowSteeringWheeAngle) {
         byte[] var8 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var8[9], var8[8], 0, 540, 16);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRadar0x41() {
      if (this.mCanBusInfoInt[12] == 1) {
         RadarInfoUtil.mMinIsClose = true;
         GeneralParkData.isShowDistanceNotShowLocationUi = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13]);
         if (GeneralParkData.isShowDistanceNotShowLocationUi) {
            int[] var1 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
            var1 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarDistanceData(var1[6], var1[7], var1[8], var1[9]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         } else {
            RadarInfoUtil.setRearRadarLocationData(7, this.resultRadar4(this.mCanBusInfoInt[2]), this.resultRadar0(this.mCanBusInfoInt[3]), this.resultRadar0(this.mCanBusInfoInt[4]), this.resultRadar4(this.mCanBusInfoInt[5]));
            RadarInfoUtil.setFrontRadarLocationData(4, this.resultRadar2(this.mCanBusInfoInt[6]), this.resultRadar0(this.mCanBusInfoInt[7]), this.resultRadar0(this.mCanBusInfoInt[8]), this.resultRadar2(this.mCanBusInfoInt[9]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         }

         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void atvDestdroy() {
      super.atvDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(DataHandleUtils.setReportHiworldSrcInfoData((byte)-111, (byte)8));
      }

   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 12});
      }

      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-111, (byte)12));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      if (this.mCurrentCarId == 1) {
         byte[] var1 = "0000256 6666".getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, -123}, var1));
      }

   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneCallLogInfoChange(int var1, int var2, String var3) {
      super.btPhoneCallLogInfoChange(var1, var2, var3);
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 26) {
               if (var3 != 65) {
                  if (var3 != 97) {
                     if (var3 != 240) {
                        if (var3 != 49) {
                           if (var3 == 50 && this.mCurrentCarId == 0) {
                              this.setCarBodyInfo();
                           }
                        } else {
                           if (this.isAirMsgReturn(var2)) {
                              return;
                           }

                           this.setAirData0x31();
                        }
                     } else {
                        this.setVersionInfo();
                     }
                  } else {
                     this.setCarSetData0x61();
                  }
               } else if (this.mCurrentCarId == 1) {
                  this.setRadar0x41();
               }
            } else {
               if (this.isDoorMsgReturn(var2)) {
                  return;
               }

               this.setDoorData0x1a();
               int[] var5 = this.mCanBusInfoInt;
               this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var5[5], var5[6]));
            }
         } else {
            this.setCarSetData0x12();
         }
      } else {
         this.realKeyControl();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (this.mCurrentCarId == 1) {
         var12 = (new DecimalFormat("00")).format((long)(var2 / 60 % 60));
         var11 = (new DecimalFormat("00")).format((long)(var2 % 60));
         var12 = (new DecimalFormat("000")).format((long)var5) + " " + var12 + ":" + var11 + "   ";
         byte var14 = 7;
         var1 = var14;
         if (var7 != 1) {
            var1 = var14;
            if (var7 != 2) {
               var1 = var14;
               if (var7 != 3) {
                  if (var7 != 6 && var7 != 7) {
                     var1 = 0;
                  } else {
                     var1 = 6;
                  }
               }
            }
         }

         byte[] var15 = new byte[]{22, -111, var1};
         CanbusMsgSender.sendMsg(var15);
         this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), DataHandleUtils.byteMerger(var15, var12.getBytes()));
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(DataHandleUtils.setReportHiworldSrcInfoData((byte)-111, (byte)8));
      }

      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-111, (byte)8));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mCurrentCarId = this.getCurrentCanDifferentId();
      Log.d("cwh", "mCurrentCarId   " + this.mCurrentCarId);
      this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      if (this.mCurrentCarId != 1) {
         Log.d("cwh", "0");
         this.mUiMgr.setShowRadar(false);
      } else {
         Log.d("cwh", "1");
         this.mUiMgr.setShowRadar(true);
      }

   }

   public void musicDestroy() {
      super.musicDestroy();
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (this.mCurrentCarId == 1) {
         var11 = (new DecimalFormat("00")).format((long)(var7 & 255));
         var11 = (new DecimalFormat("000")).format((long)(var9 * 256 + var3)) + " 00:" + var11 + "   ";
         if (var1 == 9) {
            var1 = 14;
         } else {
            var1 = 13;
         }

         byte[] var25 = var11.getBytes();
         var25 = DataHandleUtils.byteMerger(new byte[]{22, -111, var1}, var25);
         CanbusMsgSender.sendMsg(var25);
         this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var25);
      }

   }

   public void radioDestroy() {
      super.radioDestroy();
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.mCurrentCarId == 1) {
         byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
         if (this.isBandFm(var2)) {
            if (var3.length() == 5) {
               var2 = var3.substring(0, 4);
               var2 = "0" + var1 + " 0" + var2 + "    ";
            } else if (var3.length() == 4) {
               var2 = var3.substring(0, 4);
               var2 = "0" + var1 + "  " + var2 + "    ";
            } else {
               var2 = var3.substring(0, 5);
               var2 = "0" + var1 + " " + var2 + "    ";
            }
         } else if (var3.length() == 3) {
            var2 = "0" + var1 + " " + var3 + "      ";
         } else {
            var2 = "0" + var1 + " " + var3 + "     ";
         }

         byte[] var7 = var2.getBytes();
         var7 = DataHandleUtils.byteMerger(new byte[]{22, -111, var6}, var7);
         CanbusMsgSender.sendMsg(var7);
         this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), var7);
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

      this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.toString(), new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void videoDestroy() {
      super.videoDestroy();
      if (this.mCurrentCarId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (this.mCurrentCarId == 1) {
         var8 = (new DecimalFormat("00")).format((long)(var7 & 255));
         var11 = (new DecimalFormat("000")).format((long)(var9 * 256 + var3)) + " 00:" + var8 + "   ";
         if (var1 == 9) {
            var1 = 14;
         } else {
            var1 = 13;
         }

         byte[] var18 = new byte[]{22, -111, var1};
         CanbusMsgSender.sendMsg(var18);
         this.sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.byteMerger(var18, var11.getBytes()));
      }

   }
}
