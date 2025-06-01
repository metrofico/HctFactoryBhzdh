package com.hzbhd.canbus.car._326;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private byte[] mAirData;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private int mEachId;
   private boolean mFrontStatus;
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap = new HashMap();
   private byte[] mTrackData;
   private UiMgr mUiMgr;

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         this.mSettingItemIndeHashMap.put(var1, new SettingUpdateEntity(-1, -1, (Object)null));
         return this.getSettingUpdateEntity(var1);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initSettingsItem(Context var1) {
      this.mSettingItemIndeHashMap = new HashMap();
      SparseArray var4 = new SparseArray();
      List var5 = this.getUiMgr(var1).getSettingUiSet(var1).getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var7 = ((SettingPageUiSet.ListBean)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var7.size(); ++var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)var7.get(var3)).getTitleSrn();
            var4.append(var2 << 8 | var3, var6);
            this.mSettingItemIndeHashMap.put(var6, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorChange() {
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

   private boolean isTrackDataChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolverAirTemperature(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 127) {
         return "HI";
      } else {
         return 31 <= var1 && var1 <= 59 ? (float)var1 * 0.5F + this.getTempUnitC(this.mContext) : "";
      }
   }

   private void set0x20WheelKey(Context var1) {
      int var2 = this.mKeyStatus;
      int var3 = this.mCanBusInfoInt[2];
      if (var2 != var3) {
         this.mKeyStatus = var3;
      }

      if (var3 != 63) {
         if (var3 != 90) {
            if (var3 != 82) {
               if (var3 != 83) {
                  switch (var3) {
                     case 0:
                        this.realKeyLongClick1(var1, 0);
                        break;
                     case 1:
                        this.realKeyLongClick1(var1, 7);
                        break;
                     case 2:
                        this.realKeyLongClick1(var1, 8);
                        break;
                     case 3:
                        this.realKeyLongClick1(var1, 20);
                        break;
                     case 4:
                        this.realKeyLongClick1(var1, 21);
                        break;
                     case 5:
                        this.realKeyLongClick1(var1, 68);
                        break;
                     case 6:
                        this.realKeyLongClick1(var1, 3);
                        break;
                     case 7:
                        this.realKeyLongClick1(var1, 2);
                        break;
                     default:
                        switch (var3) {
                           case 14:
                              this.realKeyLongClick1(var1, 45);
                              break;
                           case 15:
                              this.realKeyLongClick1(var1, 46);
                              break;
                           case 16:
                              this.realKeyLongClick1(var1, 47);
                              break;
                           case 17:
                              this.realKeyLongClick1(var1, 48);
                              break;
                           case 18:
                              this.realKeyLongClick1(var1, 49);
                              break;
                           default:
                              switch (var3) {
                                 case 32:
                                    this.realKeyLongClick1(var1, 32);
                                    break;
                                 case 33:
                                    this.realKeyLongClick1(var1, 33);
                                    break;
                                 case 34:
                                    this.realKeyLongClick1(var1, 34);
                                    break;
                                 case 35:
                                    this.realKeyLongClick1(var1, 35);
                                    break;
                                 case 36:
                                    this.realKeyLongClick1(var1, 36);
                                    break;
                                 case 37:
                                    this.realKeyLongClick1(var1, 37);
                                    break;
                                 case 38:
                                    this.realKeyLongClick1(var1, 38);
                                    break;
                                 case 39:
                                    this.realKeyLongClick1(var1, 39);
                                    break;
                                 case 40:
                                    this.realKeyLongClick1(var1, 40);
                                    break;
                                 case 41:
                                    this.realKeyLongClick1(var1, 41);
                                    break;
                                 default:
                                    switch (var3) {
                                       case 52:
                                          this.realKeyLongClick1(var1, 129);
                                          break;
                                       case 53:
                                          this.realKeyLongClick1(var1, 59);
                                          break;
                                       case 54:
                                          this.realKeyLongClick1(var1, 141);
                                          break;
                                       case 55:
                                          this.realKeyLongClick1(var1, 52);
                                          break;
                                       case 56:
                                          this.realKeyLongClick1(var1, 4);
                                          break;
                                       case 57:
                                          this.realKeyLongClick1(var1, 68);
                                          break;
                                       default:
                                          switch (var3) {
                                             case 72:
                                                this.realKeyLongClick1(var1, 49);
                                                break;
                                             case 73:
                                                this.realKeyLongClick1(var1, 47);
                                                break;
                                             case 74:
                                                this.realKeyLongClick1(var1, 48);
                                                break;
                                             case 75:
                                                this.realKeyLongClick1(var1, 45);
                                                break;
                                             case 76:
                                                this.realKeyLongClick1(var1, 46);
                                          }
                                    }
                              }
                        }
                  }
               } else {
                  this.realKeyLongClick1(var1, 15);
               }
            } else {
               this.realKeyLongClick1(var1, 14);
            }
         } else {
            this.realKeyLongClick1(var1, 50);
         }
      } else {
         this.realKeyLongClick1(var1, 134);
      }

   }

   private void set0x21AirData() {
      if (this.isAirDataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         var1[3] = (byte)(var1[3] & 239);
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
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
         GeneralAirData.front_left_temperature = this.resolverAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolverAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x22RearRadarInfo(Context var1) {
      if (this.is0x22DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(31, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x23FrontRadarInfo(Context var1) {
      if (this.is0x23DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(31, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24BaseInfo(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x25ParkAssistant() {
      ArrayList var1 = new ArrayList();
      var1.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
      var1.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
      GeneralParkData.dataList = var1;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x26TrackDate(Context var1) {
      if (this.isTrackDataChange()) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 10000, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x30VersionData() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x68SeatHeat() {
      Log.i("cbc", "set0x68SeatHeat: ");
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2), 4);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2), 4);
         Log.i("cbc", "value : " + GeneralAirData.front_left_seat_heat_level);
         Log.i("cbc", "key : " + GeneralAirData.front_right_seat_heat_level);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 48) {
         if (var3 != 107) {
            switch (var3) {
               case 32:
                  this.set0x20WheelKey(var1);
                  break;
               case 33:
                  this.set0x21AirData();
                  break;
               case 34:
                  this.set0x22RearRadarInfo(var1);
                  break;
               case 35:
                  this.set0x23FrontRadarInfo(var1);
                  break;
               case 36:
                  this.set0x24BaseInfo(var1);
                  break;
               case 37:
                  this.set0x25ParkAssistant();
                  break;
               case 38:
                  this.set0x26TrackDate(var1);
            }
         } else {
            this.set0x68SeatHeat();
         }
      } else {
         this.set0x30VersionData();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initSettingsItem(var1);
   }
}
