package com.hzbhd.canbus.car._332;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private int RearDefogState = 0;
   private String[] arr0 = new String[3];
   private String[] arr1 = new String[3];
   private String[] arr2 = new String[3];
   private String[] arr3 = new String[3];
   private boolean data1Bite3 = true;
   DecimalFormat df1 = new DecimalFormat("###0.0");
   DecimalFormat df2 = new DecimalFormat("###0.00");
   int differentId;
   int eachId;
   private int frontDefogState = 0;
   boolean isFrontLeftAlert = false;
   boolean isFrontRightAlert = false;
   boolean isRearLeftAlert = false;
   boolean isRearRightAlert = false;
   private int[] m0x11AirData;
   private int[] m0x22RearRadarData;
   private int[] m0x23FrontRadarData;
   private int[] m0x28Data;
   int[] m0x38TireAlert;
   int[] m0x38TireInfo;
   private int[] m0x3BData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private byte[] mTrackData;
   private UiMgr mUiMgr;
   private List tyreInfoList = new ArrayList();

   private boolean getAirAutoState() {
      return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1) == 1;
   }

   private String getDrivingMode(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? "S" : "L";
      } else {
         return "E";
      }
   }

   private String getEnergyFlow(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? this.mContext.getString(2131762887) : this.mContext.getString(2131762949);
      } else {
         return this.mContext.getString(2131762945);
      }
   }

   private String getGearInformation(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? "R" : "D";
      } else {
         return "N";
      }
   }

   private int getMonth(int var1, int var2) {
      var1 = DataHandleUtils.getIntFromByteWithBit(var1, 8, 4);
      var2 = DataHandleUtils.getIntFromByteWithBit(var2, 0, 4);
      return Integer.parseInt(var2 + "" + var1);
   }

   public static int getMyIntFromByteWithBit(int var0, int var1, int var2) {
      return (var0 & '\uffff') >> var1 & (1 << var2) - 1;
   }

   private String getOutdoorTemperature() {
      return this.mCanBusInfoInt[7] - 40 + this.getTempUnitC(this.mContext);
   }

   private int getRadarDistance(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? 0 : 10;
            } else {
               return 7;
            }
         } else {
            return 4;
         }
      } else {
         return 1;
      }
   }

   private String getTachographState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 243) {
               return var1 != 255 ? this.mContext.getString(2131762968) : this.mContext.getString(2131762965);
            } else {
               return this.mContext.getString(2131762966);
            }
         } else {
            return this.mContext.getString(2131762964);
         }
      } else {
         return this.mContext.getString(2131762963);
      }
   }

   private String getTachographStateInfo(int var1, boolean var2) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var2 ? this.mContext.getString(2131762973) : this.mContext.getString(2131762974);
         } else {
            return var2 ? this.mContext.getString(2131762971) : this.mContext.getString(2131762972);
         }
      } else {
         return var2 ? this.mContext.getString(2131762969) : this.mContext.getString(2131762970);
      }
   }

   private String getTemperature(int var1) {
      return (double)var1 * 0.5 + 17.0 + this.getTempUnitC(this.mContext);
   }

   private String getTireAlertInfo(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? this.mContext.getString(2131762924) : this.mContext.getString(2131762923);
         } else {
            return this.mContext.getString(2131762922);
         }
      } else {
         return this.mContext.getString(2131762915);
      }
   }

   private boolean getTireAlertState(int var1) {
      return var1 != 0;
   }

   private UiMgr getmUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is0x11DataChange() {
      if (Arrays.equals(this.m0x11AirData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.m0x11AirData = this.mCanBusInfoInt;
         return true;
      }
   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22RearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22RearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23FrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23FrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x28DataChange() {
      if (Arrays.equals(this.m0x28Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x28Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x38TireAlertChange() {
      if (Arrays.equals(this.m0x38TireAlert, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x38TireAlert = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x38TireInfoChange() {
      if (Arrays.equals(this.m0x38TireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x38TireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicDataChange() {
      if (Arrays.equals(this.m0x3BData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x3BData = Arrays.copyOf(var1, var1.length);
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

   private void set0x28BaseInfo() {
      if (this.data1Bite3 != DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         this.data1Bite3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

      this.mCanBusInfoInt[3] = 0;
      if (this.is0x28DataChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x30TrackData() {
      if (this.isTrackDataChange()) {
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 8), (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 0, 7), 0, 12000, 16);
         } else {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 8), (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 0, 7), 0, -12000, 16);
         }

         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x38TireInfo() {
      if (this.is0x38TireInfoChange()) {
         this.arr0[0] = this.mContext.getString(2131762912) + (this.mCanBusInfoInt[2] - 40) + this.getTempUnitC(this.mContext);
         this.arr0[1] = this.mContext.getString(2131762911) + this.df1.format((double)this.mCanBusInfoInt[6] * 0.02745) + "bar";
         this.arr1[0] = this.mContext.getString(2131762912) + (this.mCanBusInfoInt[3] - 40) + this.getTempUnitC(this.mContext);
         this.arr1[1] = this.mContext.getString(2131762911) + this.df1.format((double)this.mCanBusInfoInt[7] * 0.02745) + "bar";
         this.arr2[0] = this.mContext.getString(2131762912) + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
         this.arr2[1] = this.mContext.getString(2131762911) + this.df1.format((double)this.mCanBusInfoInt[8] * 0.02745) + "bar";
         this.arr3[0] = this.mContext.getString(2131762912) + (this.mCanBusInfoInt[5] - 40) + this.getTempUnitC(this.mContext);
         this.arr3[1] = this.mContext.getString(2131762911) + this.df1.format((double)this.mCanBusInfoInt[9] * 0.02745) + "bar";
         if (this.isFrontLeftAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
         }

         if (this.isFrontRightAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
         }

         if (this.isRearLeftAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
         }

         if (this.isRearRightAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
         }

         GeneralTireData.dataList = this.tyreInfoList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setAirInfo0x11() {
      this.updateOutDoorTemp(this.mContext, this.getOutdoorTemperature());
      this.mCanBusInfoInt[7] = 0;
      if (this.is0x11DataChange()) {
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = this.getAirAutoState() ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         switch (this.mCanBusInfoInt[3]) {
            case 1:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_foot = false;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               break;
            case 2:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               break;
            case 3:
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               break;
            case 4:
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               break;
            case 5:
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_foot = false;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               break;
            case 6:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_foot = false;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               break;
            case 7:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         if (this.mCanBusInfoInt[4] == 0) {
            GeneralAirData.power = false;
         } else {
            GeneralAirData.power = true;
         }

         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[6]);
         this.updateAirActivity(this.mContext, 1001);
         MyLog.e("Air", "Coming...Air");
      }

   }

   private void setChargeState0x3D() {
      ArrayList var4 = new ArrayList();
      int var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_332_charge");
      if (var1 != -1) {
         var4.add((new SettingUpdateEntity(var1, 1, this.df1.format((double)this.mCanBusInfoInt[2] * 0.1) + "A")).setValueStr(true));
         StringBuilder var2 = new StringBuilder();
         DecimalFormat var5 = this.df1;
         int[] var3 = this.mCanBusInfoInt;
         var4.add((new SettingUpdateEntity(var1, 2, var2.append(var5.format((double)(var3[3] * 256 + var3[4]) * 0.1)).append("V").toString())).setValueStr(true));
      }

      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDriverData0x3C() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_driverData_1"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_driverData_2"), this.getDrivingMode(this.mCanBusInfoInt[2])));
      var1.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_driverData_1"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_driverData_3"), this.getGearInformation(this.mCanBusInfoInt[3])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setEnergyInfo0x3E() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information");
      int var1 = this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Total_distance");
      StringBuilder var5 = new StringBuilder();
      DecimalFormat var4 = this.df1;
      int[] var6 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(var4.format((double)(var6[2] * 65536 + var6[3] * 256 + var6[4]) * 0.1)).append("km").toString()));
      var2 = this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information");
      var1 = this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Reference_remaining_mileage");
      var5 = new StringBuilder();
      var4 = this.df1;
      var6 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(var4.format((long)(var6[5] * 256 + var6[6]))).append("km").toString()));
      var3.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Percentage_of_charge"), this.mCanBusInfoInt[7] + "%"));
      var3.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_flow"), this.getEnergyFlow(this.mCanBusInfoInt[8])));
      var3.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Average_energy_consumption"), this.df1.format((double)this.mCanBusInfoInt[9] * 0.1) + "km/kmh"));
      var3.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Instantaneous_energy_consumption"), this.df1.format((double)this.mCanBusInfoInt[10] * 0.1) + "km/kmh"));
      var3.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_distribution_1"), this.df1.format((double)this.mCanBusInfoInt[11] * 0.1) + "km"));
      var3.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_distribution_2"), this.df1.format((double)this.mCanBusInfoInt[12] * 0.1) + "km"));
      var3.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_distribution_3"), this.df2.format((double)this.mCanBusInfoInt[13] * 0.75) + "km"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadar0x23() {
      if (this.is0x23DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, this.getRadarDistance(this.mCanBusInfoInt[2]), this.getRadarDistance(this.mCanBusInfoInt[3]), this.getRadarDistance(this.mCanBusInfoInt[4]), this.getRadarDistance(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setPanoramicData0x3B() {
      if (this.isPanoramicDataChange()) {
         ArrayList var2 = new ArrayList();
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            this.panoramicSwitch(true);
            var2.add(new PanoramicBtnUpdateEntity(0, true));
         } else {
            this.panoramicSwitch(false);
            var2.add(new PanoramicBtnUpdateEntity(0, false));
         }

         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 7) {
                  if (var1 == 9) {
                     var2.add(new PanoramicBtnUpdateEntity(1, false));
                     var2.add(new PanoramicBtnUpdateEntity(2, false));
                     var2.add(new PanoramicBtnUpdateEntity(3, false));
                     var2.add(new PanoramicBtnUpdateEntity(4, true));
                  }
               } else {
                  var2.add(new PanoramicBtnUpdateEntity(1, false));
                  var2.add(new PanoramicBtnUpdateEntity(2, false));
                  var2.add(new PanoramicBtnUpdateEntity(3, true));
                  var2.add(new PanoramicBtnUpdateEntity(4, false));
               }
            } else {
               var2.add(new PanoramicBtnUpdateEntity(1, false));
               var2.add(new PanoramicBtnUpdateEntity(2, true));
               var2.add(new PanoramicBtnUpdateEntity(3, false));
               var2.add(new PanoramicBtnUpdateEntity(4, false));
            }
         } else {
            var2.add(new PanoramicBtnUpdateEntity(1, true));
            var2.add(new PanoramicBtnUpdateEntity(2, false));
            var2.add(new PanoramicBtnUpdateEntity(3, false));
            var2.add(new PanoramicBtnUpdateEntity(4, false));
         }

         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  var2.add(new PanoramicBtnUpdateEntity(5, false));
                  var2.add(new PanoramicBtnUpdateEntity(6, false));
                  var2.add(new PanoramicBtnUpdateEntity(7, true));
               }
            } else {
               var2.add(new PanoramicBtnUpdateEntity(5, false));
               var2.add(new PanoramicBtnUpdateEntity(6, true));
               var2.add(new PanoramicBtnUpdateEntity(7, false));
            }
         } else {
            var2.add(new PanoramicBtnUpdateEntity(5, true));
            var2.add(new PanoramicBtnUpdateEntity(6, false));
            var2.add(new PanoramicBtnUpdateEntity(7, false));
         }

         GeneralParkData.dataList = var2;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRearRadar0x22() {
      if (this.is0x22DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.getRadarDistance(this.mCanBusInfoInt[2]), this.getRadarDistance(this.mCanBusInfoInt[3]), this.getRadarDistance(this.mCanBusInfoInt[4]), this.getRadarDistance(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSettingsInfo0x3A() {
      if (this.eachId == 7) {
         int var1 = this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_332_setting_1");
         if (var1 != -1) {
            ArrayList var2 = new ArrayList();
            switch (this.mCanBusInfoInt[2]) {
               case 1:
                  var2.add(new SettingUpdateEntity(var1, 0, this.mCanBusInfoInt[3]));
                  break;
               case 2:
                  var2.add(new SettingUpdateEntity(var1, 1, this.mCanBusInfoInt[3]));
                  break;
               case 3:
                  var2.add(new SettingUpdateEntity(var1, 2, this.mCanBusInfoInt[3]));
                  break;
               case 4:
                  var2.add(new SettingUpdateEntity(var1, 3, this.mCanBusInfoInt[3]));
                  break;
               case 5:
                  var2.add(new SettingUpdateEntity(var1, 4, this.mCanBusInfoInt[3]));
                  break;
               case 6:
                  var2.add(new SettingUpdateEntity(var1, 5, this.mCanBusInfoInt[3]));
                  break;
               case 7:
                  var2.add(new SettingUpdateEntity(var1, 6, this.mCanBusInfoInt[3]));
                  break;
               case 8:
                  var2.add(new SettingUpdateEntity(var1, 7, this.mCanBusInfoInt[3]));
                  break;
               case 9:
                  var2.add(new SettingUpdateEntity(var1, 8, this.mCanBusInfoInt[3]));
                  break;
               case 10:
                  var2.add(new SettingUpdateEntity(var1, 9, this.mCanBusInfoInt[3]));
                  break;
               case 11:
                  var2.add(new SettingUpdateEntity(var1, 10, this.mCanBusInfoInt[3]));
            }

            this.updateGeneralSettingData(var2);
            this.updateSettingActivity((Bundle)null);
         }

      }
   }

   private void setTachographInfo0x50() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 2) {
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_current_state"), this.getTachographState(this.mCanBusInfoInt[2])));
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_1"), this.getTachographStateInfo(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_2"), this.getTachographStateInfo(2, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]))));
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_3"), this.getTachographStateInfo(3, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
      } else if (var1 != 255 && var1 != 243) {
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_current_state"), this.getTachographState(this.mCanBusInfoInt[2])));
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_1"), this.mContext.getString(2131762975)));
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_2"), this.mContext.getString(2131762975)));
         var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_3"), this.mContext.getString(2131762975)));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setTireAlert0x39() {
      if (this.is0x38TireAlertChange()) {
         this.arr0[2] = this.getTireAlertInfo(this.mCanBusInfoInt[2]);
         this.arr1[2] = this.getTireAlertInfo(this.mCanBusInfoInt[3]);
         this.arr2[2] = this.getTireAlertInfo(this.mCanBusInfoInt[4]);
         this.arr3[2] = this.getTireAlertInfo(this.mCanBusInfoInt[5]);
         this.isFrontLeftAlert = this.getTireAlertState(this.mCanBusInfoInt[2]);
         this.isFrontRightAlert = this.getTireAlertState(this.mCanBusInfoInt[3]);
         this.isRearLeftAlert = this.getTireAlertState(this.mCanBusInfoInt[4]);
         this.isRearRightAlert = this.getTireAlertState(this.mCanBusInfoInt[5]);
         if (this.isFrontLeftAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
         }

         if (this.isFrontRightAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
         }

         if (this.isRearLeftAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
         }

         if (this.isRearRightAlert) {
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
         }

         GeneralTireData.dataList = this.tyreInfoList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setVersionInfo0x7F() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x02() {
      if (this.eachId == 6) {
         switch (this.mCanBusInfoInt[2]) {
            case 0:
               this.buttonKey(0);
               break;
            case 1:
               this.buttonKey(134);
               this.getmUigMgr(this.mContext).sendMediaSourcesPower();
               break;
            case 2:
               this.knobKey(7);
               break;
            case 3:
               this.knobKey(8);
               break;
            case 4:
               this.buttonKey(58);
               break;
            case 5:
               this.buttonKey(2);
               break;
            case 6:
               this.buttonKey(3);
               break;
            case 7:
               this.buttonKey(75);
               break;
            case 8:
               this.buttonKey(21);
               break;
            case 9:
               this.buttonKey(20);
               break;
            case 10:
               this.buttonKey(2);
               break;
            case 11:
               this.buttonKey(185);
         }

      }
   }

   private void setWheelKey0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     if (var1 != 18) {
                        switch (var1) {
                           case 9:
                              if (this.eachId == 2) {
                                 this.buttonKey(467);
                              } else {
                                 this.buttonKey(201);
                              }

                              return;
                           case 10:
                              if (this.eachId == 2) {
                                 this.buttonKey(468);
                              } else {
                                 this.buttonKey(184);
                              }

                              return;
                           case 11:
                              this.buttonKey(45);
                              return;
                           case 12:
                              this.buttonKey(46);
                              return;
                           default:
                              switch (var1) {
                                 case 128:
                                    break;
                                 case 129:
                                    var1 = this.eachId;
                                    if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9) {
                                       this.buttonKey(129);
                                    } else {
                                       this.buttonKey(151);
                                    }

                                    return;
                                 case 130:
                                    var1 = this.eachId;
                                    if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9) {
                                       this.buttonKey(47);
                                    } else {
                                       this.buttonKey(50);
                                    }

                                    return;
                                 case 131:
                                    var1 = this.eachId;
                                    if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9) {
                                       this.buttonKey(48);
                                    } else {
                                       this.buttonKey(2);
                                    }

                                    return;
                                 case 132:
                                    var1 = this.eachId;
                                    if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9) {
                                       this.buttonKey(50);
                                    } else {
                                       this.buttonKey(129);
                                    }

                                    return;
                                 case 133:
                                    var1 = this.eachId;
                                    if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9) {
                                       this.buttonKey(68);
                                    } else {
                                       this.buttonKey(21);
                                    }

                                    return;
                                 case 134:
                                    var1 = this.eachId;
                                    if (var1 == 4 || var1 == 6 || var1 == 8 || var1 == 9) {
                                       this.buttonKey(128);
                                    }

                                    return;
                                 case 135:
                                    var1 = this.eachId;
                                    if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9) {
                                       this.buttonKey(2);
                                    } else {
                                       this.buttonKey(3);
                                    }

                                    return;
                                 case 136:
                                    var1 = this.eachId;
                                    if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9) {
                                       this.buttonKey(58);
                                    } else {
                                       this.buttonKey(182);
                                    }

                                    return;
                                 case 137:
                                    var1 = this.eachId;
                                    if (var1 == 4 || var1 == 6 || var1 == 8 || var1 == 9) {
                                       this.buttonKey(14);
                                    }

                                    return;
                                 case 138:
                                    var1 = this.eachId;
                                    if (var1 == 4 || var1 == 6 || var1 == 8 || var1 == 9) {
                                       this.buttonKey(20);
                                    }

                                    return;
                                 case 139:
                                    if (this.eachId == 7) {
                                       this.buttonKey(7);
                                    }

                                    return;
                                 case 140:
                                    if (this.eachId == 7) {
                                       this.buttonKey(8);
                                    }

                                    return;
                                 case 141:
                                    if (this.eachId == 7) {
                                       this.buttonKey(198);
                                    }

                                    return;
                                 case 142:
                                    if (this.eachId == 7) {
                                       this.buttonKey(220);
                                    }

                                    return;
                                 default:
                                    return;
                              }
                        }
                     } else {
                        this.buttonKey(187);
                     }

                     var1 = this.eachId;
                     if (var1 == 4 || var1 == 6 || var1 == 8 || var1 == 9) {
                        this.buttonKey(134);
                        this.getmUigMgr(this.mContext).sendMediaSourcesPower();
                     }
                  } else if (this.eachId == 2) {
                     this.buttonKey(187);
                  } else {
                     this.buttonKey(2);
                  }
               } else {
                  this.buttonKey(3);
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

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      this.getmUigMgr(var1).makeConnection();
      GeneralTireData.isHaveSpareTire = false;
      this.getmUigMgr(this.mContext).sendTime();
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getmUigMgr(this.mContext).sendMediaSourcesAux();
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getmUigMgr(this.mContext).sendMediaSourcesBluetooth();
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      byte var3 = var2[1];
      if (var3 != 2) {
         if (var3 != 17) {
            if (var3 != 40) {
               if (var3 != 48) {
                  if (var3 != 80) {
                     if (var3 != 127) {
                        switch (var3) {
                           case 33:
                              this.setWheelKey0x21();
                              break;
                           case 34:
                              this.setRearRadar0x22();
                              break;
                           case 35:
                              this.setFrontRadar0x23();
                              break;
                           default:
                              switch (var3) {
                                 case 56:
                                    this.set0x38TireInfo();
                                    break;
                                 case 57:
                                    this.setTireAlert0x39();
                                    break;
                                 case 58:
                                    this.setSettingsInfo0x3A();
                                    break;
                                 case 59:
                                    this.setPanoramicData0x3B();
                                    break;
                                 case 60:
                                    this.setDriverData0x3C();
                                    break;
                                 case 61:
                                    this.setChargeState0x3D();
                                    break;
                                 case 62:
                                    this.setEnergyInfo0x3E();
                              }
                        }
                     } else {
                        this.setVersionInfo0x7F();
                     }
                  } else {
                     this.setTachographInfo0x50();
                  }
               } else {
                  this.set0x30TrackData();
               }
            } else {
               this.set0x28BaseInfo();
            }
         } else {
            this.setAirInfo0x11();
         }
      } else {
         this.setWheelKey0x02();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getmUigMgr(this.mContext).sendNowTime(getMyIntFromByteWithBit(var1, 0, 8), Integer.parseInt(getMyIntFromByteWithBit(var1, 0, 4) + "" + getMyIntFromByteWithBit(var1, 8, 4)), var4, var5, var6);
      this.getmUigMgr(this.mContext).sendCarType();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getmUigMgr(this.mContext).sendMediaSourcesMusic(var6, var7);
   }

   public void panoramicSwitch(boolean var1) {
      long var2 = System.currentTimeMillis();
      this.forceReverse(this.mContext, var1);
      long var4 = System.currentTimeMillis();
      MyLog.e("运行时间CD", Long.valueOf(var4) - Long.valueOf(var2) + "");
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.getmUigMgr(this.mContext).sendMediaSourcesRadio(var2);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      this.getmUigMgr(this.mContext).sendMediaSourcesNoSRC();
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      int var3 = this.eachId;
      byte var2 = 7;
      if (var3 == 7) {
         label145: {
            var1.hashCode();
            switch (var1) {
               case "rear.right.window.close":
                  var2 = 0;
                  break label145;
               case "front_defog":
                  var2 = 1;
                  break label145;
               case "air.ac.on":
                  var2 = 2;
                  break label145;
               case "skylight.open":
                  var2 = 3;
                  break label145;
               case "front.right.window.close":
                  var2 = 4;
                  break label145;
               case "ac.open":
                  var2 = 5;
                  break label145;
               case "rear.right.window.open":
                  var2 = 6;
                  break label145;
               case "air.in.out.cycle.off":
               case "air.ac.off":
                  var2 = 8;
                  break label145;
               case "air_right_temperature_up":
                  var2 = 9;
                  break label145;
               case "rear_defog":
                  var2 = 10;
                  break label145;
               case "skylight.close":
                  var2 = 11;
                  break label145;
               case "air_left_temperature_down":
                  var2 = 12;
                  break label145;
               case "rear.left.window.close":
                  var2 = 13;
                  break label145;
               case "front.left.window.open":
                  var2 = 14;
                  break label145;
               case "ac.close":
                  var2 = 15;
                  break label145;
               case "front.right.window.open":
                  var2 = 16;
                  break label145;
               case "ac.windlevel.down":
                  var2 = 17;
                  break label145;
               case "air_left_temperature_up":
                  var2 = 18;
                  break label145;
               case "ac.windlevel.up":
                  var2 = 19;
                  break label145;
               case "air.in.out.cycle.on":
                  var2 = 20;
                  break label145;
               case "rear.left.window.open":
                  var2 = 21;
                  break label145;
               case "air_right_temperature_down":
                  var2 = 22;
                  break label145;
               case "front.left.window.close":
                  var2 = 23;
                  break label145;
            }

            var2 = -1;
         }

         switch (var2) {
            case 0:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(1, 1);
               break;
            case 1:
               if (this.frontDefogState == 0) {
                  this.frontDefogState = 1;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(71, 1);
               } else {
                  this.frontDefogState = 0;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(71, 0);
               }
               break;
            case 2:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(65, 1);
               break;
            case 3:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x01(1);
               break;
            case 4:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(0, 1);
               break;
            case 5:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(64, 1);
               break;
            case 6:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(1, 11);
               break;
            case 7:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(66, 0);
               break;
            case 8:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(65, 0);
               break;
            case 9:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(69, 1, 128);
               break;
            case 10:
               if (this.RearDefogState == 0) {
                  this.RearDefogState = 1;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(72, 1);
               } else {
                  this.RearDefogState = 0;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(72, 0);
               }
               break;
            case 11:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x01(0);
               break;
            case 12:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(69, 0, 129);
               break;
            case 13:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(3, 1);
               break;
            case 14:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(2, 11);
               break;
            case 15:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(64, 0);
               break;
            case 16:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(0, 11);
               break;
            case 17:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(68, 129);
               break;
            case 18:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(69, 0, 128);
               break;
            case 19:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(68, 128);
               break;
            case 20:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(66, 1);
               break;
            case 21:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(3, 11);
               break;
            case 22:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(69, 1, 129);
               break;
            case 23:
               this.getmUigMgr(this.mContext).sendVoiceControlInfo0x02(2, 1);
         }

      }
   }
}
