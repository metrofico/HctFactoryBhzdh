package com.hzbhd.canbus.car._86;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   int[] mRearRadarData;
   private UiMgr mUiMgr;

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getRadarDistance(int var1) {
      return var1 == 0 ? 0 : var1 / 3 + 1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void set0x14BachLightInfo() {
      this.setBacklightLevel(this.mCanBusInfoInt[2] / 25 + 1);
   }

   private void set0x20WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 7) {
                           if (var1 != 10) {
                              if (var1 == 11) {
                                 this.buttonKey(3);
                              }
                           } else {
                              this.buttonKey(15);
                           }
                        } else {
                           this.buttonKey(2);
                        }
                     } else {
                        this.buttonKey(14);
                     }
                  } else {
                     this.buttonKey(46);
                  }
               } else {
                  this.buttonKey(45);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void set0x21AirInfo() {
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         int var1 = this.mCanBusInfoInt[4];
         if (var1 == 31) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var1 == 57) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = (double)this.mCanBusInfoInt[4] * 0.5 + this.getTempUnitC(this.mContext);
         }

         var1 = this.mCanBusInfoInt[5];
         if (var1 == 31) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var1 == 57) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = (double)this.mCanBusInfoInt[5] * 0.5 + this.getTempUnitC(this.mContext);
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x22RearRadarInfo() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.getRadarDistance(this.mCanBusInfoInt[2]), this.getRadarDistance(this.mCanBusInfoInt[3]), this.getRadarDistance(this.mCanBusInfoInt[4]), this.getRadarDistance(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23FrontRadarInfo() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, this.getRadarDistance(this.mCanBusInfoInt[2]), this.getRadarDistance(this.mCanBusInfoInt[3]), this.getRadarDistance(this.mCanBusInfoInt[4]), this.getRadarDistance(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x25ParkSytem() {
      String var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1 = "ON";
      } else {
         var1 = "OFF";
      }

      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_paring_info"), var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x40MediaTypeInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.startOtherActivity(this.mContext, HzbhdComponentName.AuxMainActivity);
            }
         } else {
            this.startOtherActivity(this.mContext, HzbhdComponentName.MediaMusic);
         }
      } else {
         this.startOtherActivity(this.mContext, HzbhdComponentName.RadioActivity);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).sendSourceInfo0xC0(7, 48);
      this.getUiMgr(this.mContext).sendMediaInfo0xC3(48, 0, 0, 0, 0, 0);
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 37) {
            if (var3 != 48) {
               if (var3 != 64) {
                  switch (var3) {
                     case 32:
                        this.set0x20WheelKeyInfo();
                        break;
                     case 33:
                        this.set0x21AirInfo();
                        break;
                     case 34:
                        this.set0x22RearRadarInfo();
                        break;
                     case 35:
                        this.set0x23FrontRadarInfo();
                  }
               } else {
                  this.set0x40MediaTypeInfo();
               }
            } else {
               this.set0x30VersionInfo();
            }
         } else {
            this.set0x25ParkSytem();
         }
      } else {
         this.set0x14BachLightInfo();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.getUiMgr(this.mContext).sendVolInfo0xC4(var1);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).sendSourceInfo0xC0(2, 48);
      this.getUiMgr(this.mContext).sendMediaInfo0xC3(48, 0, 0, 0, 0, 0);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).makeConnection();
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.getUiMgr(this.mContext).sendSourceInfo0xC0(1, 1);
      float var6;
      byte var9;
      if (!var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3")) {
         var9 = 16;
         var6 = Float.parseFloat(var3);
      } else {
         var9 = 0;
         var6 = Float.parseFloat(var3) * 100.0F;
      }

      UiMgr var8 = this.getUiMgr(this.mContext);
      int var7 = (int)var6;
      var8.sendRadioInfo0xC2(var9, this.getLsb(var7), this.getMsb(var7), var1);
      this.getUiMgr(this.mContext).sendMediaInfo0xC3(1, 0, 0, 0, 0, 0);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.getUiMgr(this.mContext).sendSourceInfo0xC0(0, 48);
   }
}
