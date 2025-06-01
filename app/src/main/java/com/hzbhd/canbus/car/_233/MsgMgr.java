package com.hzbhd.canbus.car._233;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int[] mAirData;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private int mEachId;
   private boolean mFrontStatus;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private int[] mRadarDataNow;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private String resolveAirTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void setAirData0x31() {
      if (!this.isAirDataNoChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         boolean var2;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralAirData.ac = var2;
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
         }

         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[9]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setBaseInfo0x12() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadarData0x23() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setIllumination0x67() {
   }

   private void setPanelButton0x21() {
      switch (this.mCanBusInfoInt[2]) {
         case 6:
            this.realKeyClick(50);
            break;
         case 9:
            this.realKeyClick(3);
            break;
         case 32:
            this.realKeyClick(128);
            break;
         case 36:
            this.realKeyClick(59);
            break;
         case 40:
            this.realKeyClick(15);
            break;
         case 42:
            this.realKeyClick(49);
            break;
         case 47:
            this.realKeyClick(52);
            break;
         case 51:
            this.realKeyClick(76);
            break;
         case 55:
            this.realKeyClick(58);
            break;
         case 64:
            this.realKeyClick(185);
            break;
         case 66:
            this.realKeyClick(4);
      }

   }

   private void setRadarData0x41(Context var1) {
      int[] var2;
      if (this.mCanBusInfoInt[13] == 0) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(3, var2[6], var2[7], var2[8], var2[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         GeneralParkData.radar_distance_disable = new int[]{0, 255};
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var2[2], var2[3], var2[4], var2[5]);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarDistanceData(var2[6], var2[7], var2[8], var2[9]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRearRadarData0x22() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSpeedData0x26() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var2.append(var3[5] + var3[4] * 256).append(" rpm").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var2.append(var3[6] * 256 + var3[7]).append(" km/h").toString()));
      var1.add(new DriverUpdateEntity(0, 2, (double)this.mCanBusInfoInt[8] * 0.1 + "V"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[6], var4[7]));
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1[3], var1[2], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 8) {
         if (var1 != 9) {
            if (var1 != 12) {
               if (var1 != 44) {
                  switch (var1) {
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
                        this.realKeyClick(3);
                        break;
                     case 4:
                        this.realKeyClick(187);
                        break;
                     case 5:
                        this.realKeyClick(14);
                        break;
                     case 6:
                        this.realKeyClick(15);
                  }
               } else {
                  this.realKeyClick(2);
               }
            } else {
               this.realKeyClick(2);
            }
         } else {
            this.realKeyClick(46);
         }
      } else {
         this.realKeyClick(45);
      }

      byte[] var2 = this.mCanBusInfoByte;
      if (DataHandleUtils.getMsbLsbResult(var2[8], var2[9]) <= 5500) {
         var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = DataHandleUtils.getMsbLsbResult(var2[8], var2[9]) / 220 + 1;
      } else {
         var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = (DataHandleUtils.getMsbLsbResult(var2[8], var2[9]) - 65536) / 220 + 1;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setpanelrotary0x22() {
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 41) {
               if (var3 != 65) {
                  if (var3 != 103) {
                     if (var3 != 240) {
                        if (var3 != 49) {
                           if (var3 != 50) {
                              switch (var3) {
                                 case 33:
                                    this.setPanelButton0x21();
                                    break;
                                 case 34:
                                    this.setpanelrotary0x22();
                                    break;
                                 case 35:
                                    this.setFrontRadarData0x23();
                              }
                           } else {
                              this.setSpeedData0x26();
                           }
                        } else {
                           this.setAirData0x31();
                        }
                     } else {
                        this.setVersionInfo0xF0();
                     }
                  } else {
                     this.setIllumination0x67();
                  }
               } else {
                  this.setRadarData0x41(var1);
               }
            } else {
               this.setTrackData0x29();
            }
         } else {
            this.setBaseInfo0x12();
         }
      } else {
         this.setWheelKey0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
