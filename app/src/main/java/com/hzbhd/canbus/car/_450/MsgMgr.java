package com.hzbhd.canbus.car._450;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private String[] arr0 = new String[1];
   private String[] arr1 = new String[1];
   private String[] arr2 = new String[1];
   private String[] arr3 = new String[1];
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   private UiMgr mUiMgr;
   private List tyreInfoList = new ArrayList();

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private int optionRadarRange(int var1) {
      if (var1 >= 0 && var1 <= 51) {
         return 1;
      } else if (var1 >= 52 && var1 <= 150) {
         return 6;
      } else {
         return var1 > 150 && var1 <= 252 ? 10 : 0;
      }
   }

   private void set0X24(int[] var1) {
      if (DataHandleUtils.getBoolBit7(var1[2])) {
         GeneralParkData.trackAngle = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 7) / 2;
      } else {
         GeneralParkData.trackAngle = -var1[2] / 2;
      }

      this.updateTrack();
   }

   private void set0x10(int[] var1) {
      var1[2] = 0;
      var1[4] = this.blockBit(var1[4], 4);
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(var1[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[3]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[3]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSubShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit7(var1[4]);
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit6(var1[4]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x11(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_61"), DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) + "rpm"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_60"), DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) + "km/h"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.getMsbLsbResult(var1[4], var1[5]));
   }

   private void set0x12(int[] var1) {
      ArrayList var6 = new ArrayList();
      int var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      int var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_62");
      int var2;
      Context var5;
      if (DataHandleUtils.getBoolBit7(var1[2])) {
         var5 = this.mContext;
         var2 = 2131766189;
      } else {
         var5 = this.mContext;
         var2 = 2131766188;
      }

      var6.add(new DriverUpdateEntity(var3, var4, var5.getString(var2)));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_63");
      if (DataHandleUtils.getBoolBit6(var1[2])) {
         var5 = this.mContext;
         var2 = 2131766192;
      } else {
         var5 = this.mContext;
         var2 = 2131766191;
      }

      var6.add(new DriverUpdateEntity(var3, var4, var5.getString(var2)));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_64");
      String var7;
      if (DataHandleUtils.getBoolBit7(var1[3])) {
         var7 = this.mContext.getString(2131766195);
      } else {
         var7 = this.mContext.getString(2131766194);
      }

      var6.add(new DriverUpdateEntity(var3, var2, var7));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_65");
      if (DataHandleUtils.getBoolBit6(var1[3])) {
         var7 = this.mContext.getString(2131766195);
      } else {
         var7 = this.mContext.getString(2131766194);
      }

      var6.add(new DriverUpdateEntity(var3, var2, var7));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_66"), var1[4] + "L"));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
      if (DataHandleUtils.getBoolBit7(var1[5])) {
         this.updateOutDoorTemp(this.mContext, "-" + (double)DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 7) * 0.5 + this.getTempUnitC(this.mContext));
      } else {
         this.updateOutDoorTemp(this.mContext, (double)DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 7) * 0.5 + this.getTempUnitC(this.mContext));
      }

   }

   private void set0x14(int[] var1) {
      this.setBacklightLevel(var1[2] / 51 + 1);
   }

   private void set0x21(int[] var1) {
      int var2 = var1[2];
      if (var2 != 16) {
         if (var2 != 129) {
            if (var2 != 130) {
               switch (var2) {
                  case 0:
                     this.realKeyLongClick1(this.mContext, 0, var1[3]);
                     break;
                  case 1:
                     this.realKeyLongClick1(this.mContext, 7, var1[3]);
                     break;
                  case 2:
                     this.realKeyLongClick1(this.mContext, 8, var1[3]);
                     break;
                  case 3:
                     this.realKeyLongClick1(this.mContext, 2, var1[3]);
                     break;
                  case 4:
                     this.realKeyLongClick1(this.mContext, 14, var1[3]);
                     break;
                  case 5:
                     this.realKeyLongClick1(this.mContext, 187, var1[3]);
                     break;
                  case 6:
                     this.realKeyLongClick1(this.mContext, 49, var1[3]);
                     break;
                  case 7:
                     this.realKeyLongClick1(this.mContext, 45, var1[3]);
                     break;
                  case 8:
                     this.realKeyLongClick1(this.mContext, 46, var1[3]);
               }
            } else {
               this.realKeyClick4(this.mContext, 48);
            }
         } else {
            this.realKeyClick4(this.mContext, 47);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 49, var1[3]);
      }

   }

   private void set0x22(int[] var1) {
      int var2 = var1[2];
      if (var2 != 129) {
         if (var2 != 130) {
            switch (var2) {
               case 0:
                  this.realKeyLongClick1(this.mContext, 0, var1[3]);
                  break;
               case 1:
                  this.realKeyLongClick1(this.mContext, 59, var1[3]);
                  break;
               case 2:
                  this.realKeyLongClick1(this.mContext, 62, var1[3]);
                  break;
               case 3:
                  this.realKeyLongClick1(this.mContext, 14, var1[3]);
                  break;
               case 4:
                  this.realKeyLongClick1(this.mContext, 128, var1[3]);
                  break;
               case 5:
                  this.realKeyLongClick1(this.mContext, 151, var1[3]);
                  break;
               case 6:
                  this.realKeyLongClick1(this.mContext, 50, var1[3]);
                  break;
               case 7:
                  this.realKeyLongClick1(this.mContext, 58, var1[3]);
                  break;
               default:
                  switch (var2) {
                     case 16:
                        this.realKeyLongClick1(this.mContext, 49, var1[3]);
                        break;
                     case 17:
                        this.realKeyLongClick1(this.mContext, 45, var1[3]);
                        break;
                     case 18:
                        this.realKeyLongClick1(this.mContext, 46, var1[3]);
                        break;
                     case 19:
                        this.realKeyLongClick1(this.mContext, 47, var1[3]);
                        break;
                     case 20:
                        this.realKeyLongClick1(this.mContext, 48, var1[3]);
                  }
            }
         } else {
            this.realKeyClick4(this.mContext, 48);
         }
      } else {
         this.realKeyClick4(this.mContext, 47);
      }

   }

   private void set0x28(int[] var1) {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(10, this.optionRadarRange(var1[3]), this.optionRadarRange(var1[4]), this.optionRadarRange(var1[5]), this.optionRadarRange(var1[6]));
      RadarInfoUtil.setRearRadarLocationData(10, this.optionRadarRange(var1[7]), this.optionRadarRange(var1[8]), this.optionRadarRange(var1[9]), this.optionRadarRange(var1[10]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x30(byte[] var1) {
      this.updateVersionInfo(this.mContext, this.getVersionStr(var1));
   }

   private void set0x40(int[] var1) {
      ArrayList var6 = new ArrayList();
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_1"), ((var1[2] & 255) << 16 | (var1[3] & 255) << 8 | var1[4] & 255) + "km"));
      int var4 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      int var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_2");
      StringBuilder var5 = new StringBuilder();
      int var2 = var1[5];
      var6.add(new DriverUpdateEntity(var4, var3, var5.append(var1[6] & 255 | (var2 & 255) << 8).append("km").toString()));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x46(int[] var1) {
      UiMgr.data0bit7 = DataHandleUtils.getIntFromByteWithBit(var1[2], 7, 1);
      UiMgr.data0bit6 = DataHandleUtils.getIntFromByteWithBit(var1[2], 6, 1);
      UiMgr.data0bit5 = DataHandleUtils.getIntFromByteWithBit(var1[2], 5, 1);
      UiMgr.data0bit4 = DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 1);
      UiMgr.data0bit3 = DataHandleUtils.getIntFromByteWithBit(var1[2], 3, 1);
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_11"), DataHandleUtils.getIntFromByteWithBit(var1[2], 7, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_12"), DataHandleUtils.getIntFromByteWithBit(var1[2], 6, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_13"), DataHandleUtils.getIntFromByteWithBit(var1[2], 5, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_14"), DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_15"), DataHandleUtils.getIntFromByteWithBit(var1[2], 3, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_19"), DataHandleUtils.getIntFromByteWithBit(var1[3], 7, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_20"), DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_21"), DataHandleUtils.getIntFromByteWithBit(var1[4], 4, 4) - 1));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_22"), DataHandleUtils.getIntFromByteWithBit(var1[4], 2, 2)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_23"), DataHandleUtils.getIntFromByteWithBit(var1[4], 1, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_24"), DataHandleUtils.getIntFromByteWithBit(var1[5], 7, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_25"), DataHandleUtils.getIntFromByteWithBit(var1[5], 6, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_26"), DataHandleUtils.getIntFromByteWithBit(var1[5], 5, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_27"), DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 1)));
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_28"), var1[6])).setProgress(var1[6]));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_29"), DataHandleUtils.getIntFromByteWithBit(var1[7], 7, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_30"), DataHandleUtils.getIntFromByteWithBit(var1[7], 6, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_31"), DataHandleUtils.getIntFromByteWithBit(var1[7], 5, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_32"), DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_33"), DataHandleUtils.getIntFromByteWithBit(var1[7], 3, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_34"), DataHandleUtils.getIntFromByteWithBit(var1[7], 2, 1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_35"), DataHandleUtils.getIntFromByteWithBit(var1[8], 7, 1)));
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_36"), DataHandleUtils.getMsbLsbResult(DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 4), var1[9]))).setProgress(DataHandleUtils.getMsbLsbResult(DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 4), var1[9])));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x48(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_40"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_37"), DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) + "KM"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_40"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_38"), (float)DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) * 0.1F + "L/100KM "));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_40"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_39"), DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) + "KM/H"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x4A(int[] var1) {
      ArrayList var6 = new ArrayList();
      int var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49");
      int var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_42");
      int var2;
      Context var5;
      if (DataHandleUtils.getBoolBit7(var1[2])) {
         var5 = this.mContext;
         var2 = 2131766147;
      } else {
         var5 = this.mContext;
         var2 = 2131766146;
      }

      var6.add(new DriverUpdateEntity(var3, var4, var5.getString(var2)));
      var2 = DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 3);
      String var8;
      if (var2 == 0) {
         var8 = this.mContext.getString(2131766149);
      } else if (var2 == 1) {
         var8 = this.mContext.getString(2131766150);
      } else if (var2 == 4) {
         var8 = this.mContext.getString(2131766151);
      } else {
         var8 = "";
      }

      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_45"), var8));
      switch (var1[3]) {
         case 0:
            var8 = "0%";
            break;
         case 1:
            var8 = "16%";
            break;
         case 2:
            var8 = "32%";
            break;
         case 3:
            var8 = "48%";
            break;
         case 4:
            var8 = "64%";
            break;
         case 5:
            var8 = "80%";
            break;
         case 6:
            var8 = "100%";
            break;
         case 7:
            var8 = "100% +";
            break;
         default:
            var8 = "--";
      }

      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_49"), var8));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_48_888");
      String var7;
      if (DataHandleUtils.getBoolBit0(var1[2])) {
         var7 = "Yes";
      } else {
         var7 = "NO";
      }

      var6.add(new DriverUpdateEntity(var3, var2, var7));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x4B(int[] var1) {
      ArrayList var8 = new ArrayList();
      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_50"), var1[2] + ""));
      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_51"), var1[3] + ""));
      int var2 = var1[4];
      String var7 = "--";
      String var6;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 4) {
                  if (var2 != 5) {
                     if (var2 != 130) {
                        if (var2 != 134) {
                           if (var2 != 137) {
                              if (var2 != 149) {
                                 var6 = "--";
                              } else {
                                 var6 = this.mContext.getString(2131766166);
                              }
                           } else {
                              var6 = this.mContext.getString(2131766165);
                           }
                        } else {
                           var6 = this.mContext.getString(2131766164);
                        }
                     } else {
                        var6 = this.mContext.getString(2131766163);
                     }
                  } else {
                     var6 = this.mContext.getString(2131766162);
                  }
               } else {
                  var6 = this.mContext.getString(2131766161);
               }
            } else {
               var6 = this.mContext.getString(2131766160);
            }
         } else {
            var6 = this.mContext.getString(2131766159);
         }
      } else {
         var6 = this.mContext.getString(2131766158);
      }

      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_52"), var6));
      var2 = var1[5];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               var6 = "--";
            } else {
               var6 = this.mContext.getString(2131766170);
            }
         } else {
            var6 = this.mContext.getString(2131766169);
         }
      } else {
         var6 = this.mContext.getString(2131766168);
      }

      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_53"), var6));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57");
      int var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_54_0");
      if (DataHandleUtils.getBoolBit7(var1[6])) {
         var6 = this.mContext.getString(2131766174);
      } else {
         var6 = this.mContext.getString(2131766173);
      }

      var8.add(new DriverUpdateEntity(var2, var3, var6));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_54_1");
      if (DataHandleUtils.getBoolBit6(var1[6])) {
         var6 = this.mContext.getString(2131766174);
      } else {
         var6 = this.mContext.getString(2131766173);
      }

      var8.add(new DriverUpdateEntity(var3, var2, var6));
      if (DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 2) == 1) {
         var6 = "km";
      } else {
         var6 = var7;
         if (DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 2) == 2) {
            var6 = "mi";
         }
      }

      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_54_2"), var6));
      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_55"), var1[7] + 2000 + "-" + var1[8]));
      if (DataHandleUtils.getBoolBit7(var1[9])) {
         var6 = this.mContext.getString(2131766183);
      } else {
         var6 = this.mContext.getString(2131766182);
      }

      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57");
      int var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_56");
      StringBuilder var9 = (new StringBuilder()).append(var6);
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[9], 0, 4);
      var2 = var1[10];
      var8.add(new DriverUpdateEntity(var3, var4, var9.append(var1[11] & 255 | (var5 & 255) << 16 | (var2 & 255) << 8).toString()));
      this.updateGeneralDriveData(var8);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrive0x41(int[] var1) {
      ArrayList var7 = new ArrayList();
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) != 1) {
      }

      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_3"), "--"));
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      int var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_4");
      boolean var4 = DataHandleUtils.getBoolBit7(var1[5]);
      String var6 = "ON";
      String var5;
      if (var4) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_5");
      if (DataHandleUtils.getBoolBit6(var1[5])) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_6");
      if (DataHandleUtils.getBoolBit3(var1[5])) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_7");
      if (DataHandleUtils.getBoolBit3(var1[6])) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_8");
      if (DataHandleUtils.getBoolBit2(var1[6])) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var3, var2, var5));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_9");
      if (DataHandleUtils.getBoolBit1(var1[6])) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_10");
      String var8;
      if (DataHandleUtils.getBoolBit0(var1[6])) {
         var8 = var6;
      } else {
         var8 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var8));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTire0x49(int[] var1) {
      int var2 = var1[6];
      String var3;
      if (var2 == 1) {
         var3 = "BAR";
      } else if (var2 == 2) {
         var3 = "KPA";
      } else if (var2 == 3) {
         var3 = "PSI";
      } else {
         var3 = "";
      }

      this.arr0[0] = this.mContext.getString(2131766144) + (float)DataHandleUtils.getMsbLsbResult(var1[8], var1[7]) * 0.1F + var3;
      this.arr1[0] = this.mContext.getString(2131766144) + (float)DataHandleUtils.getMsbLsbResult(var1[10], var1[9]) * 0.1F + var3;
      this.arr2[0] = this.mContext.getString(2131766144) + (float)DataHandleUtils.getMsbLsbResult(var1[12], var1[11]) * 0.1F + var3;
      this.arr3[0] = this.mContext.getString(2131766144) + (float)DataHandleUtils.getMsbLsbResult(var1[14], var1[13]) * 0.1F + var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 6, 2) == 2) {
         this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 2) == 2) {
         this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 2, 2) == 2) {
         this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 2) == 2) {
         this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
      } else {
         this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
      }

      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 36) {
            if (var3 != 40) {
               if (var3 != 48) {
                  if (var3 != 70) {
                     if (var3 != 33) {
                        if (var3 != 34) {
                           if (var3 != 64) {
                              if (var3 != 65) {
                                 switch (var3) {
                                    case 16:
                                       this.set0x10(var4);
                                       break;
                                    case 17:
                                       this.set0x11(var4);
                                       break;
                                    case 18:
                                       this.set0x12(var4);
                                       break;
                                    default:
                                       switch (var3) {
                                          case 72:
                                             this.set0x48(var4);
                                             break;
                                          case 73:
                                             this.setTire0x49(var4);
                                             break;
                                          case 74:
                                             this.set0x4A(var4);
                                             break;
                                          case 75:
                                             this.set0x4B(var4);
                                       }
                                 }
                              } else {
                                 this.setDrive0x41(var4);
                              }
                           } else {
                              this.set0x40(var4);
                           }
                        } else {
                           this.set0x22(var4);
                        }
                     } else {
                        this.set0x21(var4);
                     }
                  } else {
                     this.set0x46(var4);
                  }
               } else {
                  this.set0x30(this.mCanBusInfoByte);
               }
            } else {
               this.set0x28(var4);
            }
         } else {
            this.set0X24(var4);
         }
      } else {
         this.set0x14(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      GeneralTireData.isHaveSpareTire = false;
   }
}
