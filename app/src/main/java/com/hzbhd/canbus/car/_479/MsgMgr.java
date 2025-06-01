package com.hzbhd.canbus.car._479;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   String haveDisc1 = "";
   String haveDisc2 = "";
   String haveDisc3 = "";
   String haveDisc4 = "";
   String haveDisc5 = "";
   String haveDisc6 = "";
   int[] mAirData;
   int[] mCarDoorData;
   private Context mContext;
   int[] mDoorInfo;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   int[] mTrackData;
   private UiMgr mUiMgr;

   private String getCDCState(int var1) {
      switch (var1) {
         case 1:
            return "Disc 1";
         case 2:
            return "Disc 2";
         case 3:
            return "Disc 3";
         case 4:
            return "Disc 4";
         case 5:
            return "Disc 5";
         case 6:
            return "Disc 6";
         default:
            return "NO MEDIA";
      }
   }

   private String getChangerState(Context var1, int var2) {
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     return var2 != 5 ? "NO STATE" : var1.getString(2131763809);
                  } else {
                     return var1.getString(2131765772);
                  }
               } else {
                  return var1.getString(2131765771);
               }
            } else {
               return var1.getString(2131768117);
            }
         } else {
            return var1.getString(2131768116);
         }
      } else {
         return var1.getString(2131768338);
      }
   }

   private String getHaveState(boolean var1) {
      return var1 ? "有" : "无";
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private String getRandomState(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? "Random OFF" : "Disc Random";
      } else {
         return "All Disc Random";
      }
   }

   private String getRptState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? "Rpt" : "Disc Rpt";
         } else {
            return "Track Rpt";
         }
      } else {
         return "All Disc Rpt";
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initOriginalCarDevice() {
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();
            this.this$0.initOriginalDeviceDataArray();
         }
      }).start();
   }

   private void initOriginalDeviceDataArray() {
      new ArrayList();
      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc1", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc2", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc3", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc4", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc5", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc6", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_479_disc_track", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc8", (String)null));
      SparseArray var1 = new SparseArray();
      this.mOriginalDeviceDataArray = var1;
      var1.put(134, new OriginalDeviceData(var2, new String[0]));
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorInfoChange(int[] var1) {
      if (Arrays.equals(this.mDoorInfo, var1)) {
         return false;
      } else {
         this.mDoorInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private String resolveLeftAndRightTemp(Context var1, int var2) {
      String var3;
      if (var2 == 0) {
         var3 = "LO";
      } else if (32 == var2) {
         var3 = "HI";
      } else {
         if (1 <= var2 && 29 >= var2) {
            if (GeneralAirData.fahrenheit_celsius) {
               return (double)((float)var2 / 2.0F) + 17.5 + this.getTempUnitF(var1);
            }

            return (double)((float)var2 / 2.0F) + 17.5 + this.getTempUnitC(var1);
         }

         var3 = "";
      }

      return var3;
   }

   private void set0x20WheelKey(int[] var1) {
      switch (var1[2]) {
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
            this.realKeyLongClick1(this.mContext, 20, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 21, var1[3]);
         case 5:
         case 16:
         case 32:
         case 45:
         default:
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
            break;
         case 9:
            this.realKeyLongClick1(this.mContext, 15, var1[3]);
            break;
         case 10:
            this.realKeyLongClick1(this.mContext, 128, var1[3]);
            break;
         case 11:
            this.realKeyLongClick1(this.mContext, 3, var1[3]);
            break;
         case 12:
            this.startDrivingDataActivity(this.mContext, 0);
            break;
         case 13:
            this.startDrivingDataActivity(this.mContext, 1);
            break;
         case 14:
            this.realKeyLongClick1(this.mContext, 21, var1[3]);
            break;
         case 15:
            this.realKeyLongClick1(this.mContext, 23, var1[3]);
            break;
         case 17:
            this.realKeyLongClick1(this.mContext, 20, var1[3]);
            break;
         case 18:
            this.realKeyLongClick1(this.mContext, 22, var1[3]);
            break;
         case 19:
            this.realKeyLongClick1(this.mContext, 27, var1[3]);
            break;
         case 20:
            this.startDrivingDataActivity(this.mContext, 2);
            break;
         case 21:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 22:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 23:
            this.realKeyLongClick1(this.mContext, 1, var1[3]);
            break;
         case 24:
            this.realKeyLongClick1(this.mContext, 48, var1[3]);
            break;
         case 25:
            this.realKeyLongClick1(this.mContext, 47, var1[3]);
            break;
         case 26:
            this.realKeyLongClick1(this.mContext, 49, var1[3]);
            break;
         case 27:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 28:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 29:
            this.realKeyLongClick1(this.mContext, 76, var1[3]);
            break;
         case 30:
            this.realKeyLongClick1(this.mContext, 77, var1[3]);
            break;
         case 31:
            this.realKeyLongClick1(this.mContext, 130, var1[3]);
            break;
         case 33:
            this.realKeyLongClick1(this.mContext, 62, var1[3]);
            break;
         case 34:
            this.realKeyLongClick1(this.mContext, 130, var1[3]);
            break;
         case 35:
            this.realKeyLongClick1(this.mContext, 141, var1[3]);
            break;
         case 36:
            this.realKeyLongClick1(this.mContext, 65, var1[3]);
            break;
         case 37:
            this.realKeyLongClick1(this.mContext, 65, var1[3]);
            break;
         case 38:
            this.realKeyLongClick1(this.mContext, 65, var1[3]);
            break;
         case 39:
            this.realKeyLongClick1(this.mContext, 65, var1[3]);
            break;
         case 40:
            this.realKeyLongClick1(this.mContext, 65, var1[3]);
            break;
         case 41:
            this.realKeyLongClick1(this.mContext, 65, var1[3]);
            break;
         case 42:
            this.realKeyLongClick1(this.mContext, 30, var1[3]);
            break;
         case 43:
            this.realKeyLongClick1(this.mContext, 50, var1[3]);
            break;
         case 44:
            this.realKeyLongClick1(this.mContext, 151, var1[3]);
            break;
         case 46:
            this.realKeyLongClick1(this.mContext, 182, var1[3]);
            break;
         case 47:
            this.realKeyLongClick1(this.mContext, 185, var1[3]);
            break;
         case 48:
            this.realKeyLongClick1(this.mContext, 32, var1[3]);
            break;
         case 49:
            this.realKeyLongClick1(this.mContext, 33, var1[3]);
            break;
         case 50:
            this.realKeyLongClick1(this.mContext, 34, var1[3]);
            break;
         case 51:
            this.realKeyLongClick1(this.mContext, 35, var1[3]);
            break;
         case 52:
            this.realKeyLongClick1(this.mContext, 36, var1[3]);
            break;
         case 53:
            this.realKeyLongClick1(this.mContext, 37, var1[3]);
            break;
         case 54:
            this.realKeyLongClick1(this.mContext, 38, var1[3]);
            break;
         case 55:
            this.realKeyLongClick1(this.mContext, 39, var1[3]);
            break;
         case 56:
            this.realKeyLongClick1(this.mContext, 40, var1[3]);
            break;
         case 57:
            this.realKeyLongClick1(this.mContext, 41, var1[3]);
            break;
         case 58:
            this.realKeyLongClick1(this.mContext, 63, var1[3]);
            break;
         case 59:
            this.realKeyLongClick1(this.mContext, 64, var1[3]);
            break;
         case 60:
            this.realKeyLongClick1(this.mContext, 150, var1[3]);
            break;
         case 61:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 62:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 63:
            this.realKeyLongClick1(this.mContext, 4, var1[3]);
            break;
         case 64:
            this.realKeyLongClick1(this.mContext, 31, var1[3]);
            break;
         case 65:
            this.realKeyLongClick1(this.mContext, 183, var1[3]);
            break;
         case 66:
            this.realKeyLongClick1(this.mContext, 183, var1[3]);
            break;
         case 67:
            this.realKeyLongClick1(this.mContext, 58, var1[3]);
      }

   }

   private void set0x24BasicInfo(int[] var1) {
      if (DataHandleUtils.getBoolBit0(var1[2])) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         if (this.isDoorInfoChange(var1)) {
            this.updateDoorView(this.mContext);
         }
      }

      ArrayList var6 = new ArrayList();
      int var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      int var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_308_title_1");
      int var2;
      Context var5;
      if (DataHandleUtils.getBoolBit2(var1[3])) {
         var5 = this.mContext;
         var2 = 2131758985;
      } else {
         var5 = this.mContext;
         var2 = 2131759026;
      }

      var6.add(new DriverUpdateEntity(var3, var4, var5.getString(var2)));
      var4 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "gear_p");
      if (DataHandleUtils.getBoolBit1(var1[3])) {
         var5 = this.mContext;
         var2 = 2131768423;
      } else {
         var5 = this.mContext;
         var2 = 2131768424;
      }

      var6.add(new DriverUpdateEntity(var4, var3, var5.getString(var2)));
      var4 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "reverse_state");
      Context var7;
      if (DataHandleUtils.getBoolBit0(var1[3])) {
         var7 = this.mContext;
         var2 = 2131769841;
      } else {
         var7 = this.mContext;
         var2 = 2131769410;
      }

      var6.add(new DriverUpdateEntity(var4, var3, var7.getString(var2)));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x27AirInfo(int[] var1) {
      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mContext, var1[4]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mContext, var1[5]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(var1[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var1[6]);
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.front_wind_level = var1[8];
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x29Track(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[2], (byte)var1[3], 0, 1200, 16);
         Log.d("trackAngle", GeneralParkData.trackAngle + "");
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x35DashboardInfo2(int[] var1) {
      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data2"), var1[3] + this.mContext.getString(2131770215)));
      int var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data3");
      StringBuilder var8 = (new StringBuilder()).append(var1[4] * 256 + var1[5]);
      boolean var4 = DataHandleUtils.getBoolBit6(var1[12]);
      String var6 = "mil";
      String var5;
      if (var4) {
         var5 = "mil";
      } else {
         var5 = "km";
      }

      var7.add(new DriverUpdateEntity(var3, var2, var8.append(var5).toString()));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data4"), var1[6] * 256 + var1[7] + "rpm"));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data5");
      var8 = (new StringBuilder()).append((var1[9] << 16) + (var1[10] << 8) + var1[11]);
      if (DataHandleUtils.getBoolBit6(var1[12])) {
         var5 = var6;
      } else {
         var5 = "km";
      }

      var7.add(new DriverUpdateEntity(var3, var2, var8.append(var5).toString()));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(var1[3]);
      Context var11 = this.mContext;
      StringBuilder var10 = (new StringBuilder()).append(var1[8] - 40);
      if (DataHandleUtils.getBoolBit7(var1[12])) {
         var5 = this.getTempUnitF(this.mContext);
      } else {
         var5 = this.getTempUnitC(this.mContext);
      }

      this.updateOutDoorTemp(var11, var10.append(var5).toString());
      StringBuilder var12 = (new StringBuilder()).append(var1[8] - 40);
      String var9;
      if (DataHandleUtils.getBoolBit7(var1[12])) {
         var9 = this.getTempUnitF(this.mContext);
      } else {
         var9 = this.getTempUnitC(this.mContext);
      }

      Log.d("室外温度", var12.append(var9).toString());
   }

   private void set0x36TireInfo(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new TireUpdateEntity(0, DataHandleUtils.getIntFromByteWithBit(var1[6], 6, 1), new String[]{(DataHandleUtils.getIntFromByteWithBit(var1[6], 2, 1) << 8) + var1[3] + "kPa"}));
      var2.add(new TireUpdateEntity(1, DataHandleUtils.getIntFromByteWithBit(var1[6], 7, 1), new String[]{(DataHandleUtils.getIntFromByteWithBit(var1[6], 3, 1) << 8) + var1[2] + "kPa"}));
      var2.add(new TireUpdateEntity(2, DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 1), new String[]{(DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 1) << 8) + var1[5] + "kPa"}));
      var2.add(new TireUpdateEntity(3, DataHandleUtils.getIntFromByteWithBit(var1[6], 5, 1), new String[]{(DataHandleUtils.getIntFromByteWithBit(var1[6], 1, 1) << 8) + var1[4] + "kPa"}));
      GeneralTireData.dataList = var2;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x37FuelInfo(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "vm_golf7_vehicle_setup_mfd_avg_consumption"), this.formatDecimal1.format((double)DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) * 0.1) + this.mContext.getString(2131770534)));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "instaneous_fuel_consumption"), this.formatDecimal1.format((double)DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) * 0.1) + this.mContext.getString(2131770534)));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "driven_distance"), this.formatDecimal1.format((double)((var1[6] << 16) + (var1[7] << 8) + var1[8]) * 0.1) + this.mContext.getString(2131770538)));
      this.updateSpeedInfo(var1[3]);
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "average_speed"), this.formatDecimal1.format((double)DataHandleUtils.getMsbLsbResult(var1[9], var1[10]) * 0.1) + this.mContext.getString(2131770213)));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x39CDCInfo(int[] var1) {
      GeneralOriginalCarDeviceData.cdStatus = this.getCDCState(DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4));
      GeneralOriginalCarDeviceData.runningState = this.getChangerState(this.mContext, DataHandleUtils.getIntFromByteWithBit(var1[4], 4, 4));
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralOriginalCarDeviceData.rpt_off = var2;
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralOriginalCarDeviceData.rpt = var2;
      Log.d("rpt", GeneralOriginalCarDeviceData.rpt + "");
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralOriginalCarDeviceData.rpt_track = var2;
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4) == 3) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralOriginalCarDeviceData.rpt_disc = var2;
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4) == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralOriginalCarDeviceData.rdm_off = var2;
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralOriginalCarDeviceData.rdm = var2;
      if (DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4) == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralOriginalCarDeviceData.rdm_disc = var2;
      this.haveDisc1 = this.getHaveState(DataHandleUtils.getBoolBit0(var1[2]));
      this.haveDisc2 = this.getHaveState(DataHandleUtils.getBoolBit1(var1[2]));
      this.haveDisc3 = this.getHaveState(DataHandleUtils.getBoolBit2(var1[2]));
      this.haveDisc4 = this.getHaveState(DataHandleUtils.getBoolBit3(var1[2]));
      this.haveDisc5 = this.getHaveState(DataHandleUtils.getBoolBit4(var1[2]));
      this.haveDisc6 = this.getHaveState(DataHandleUtils.getBoolBit5(var1[2]));
      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDeviceUpdateEntity(0, this.haveDisc1 + ""));
      var3.add(new OriginalCarDeviceUpdateEntity(1, this.haveDisc2 + ""));
      var3.add(new OriginalCarDeviceUpdateEntity(2, this.haveDisc3 + ""));
      var3.add(new OriginalCarDeviceUpdateEntity(3, this.haveDisc4 + ""));
      var3.add(new OriginalCarDeviceUpdateEntity(4, this.haveDisc5 + ""));
      var3.add(new OriginalCarDeviceUpdateEntity(5, this.haveDisc6 + ""));
      var3.add(new OriginalCarDeviceUpdateEntity(6, var1[5] + ""));
      var3.add(new OriginalCarDeviceUpdateEntity(7, var1[6] + ":" + var1[7]));
      GeneralOriginalCarDeviceData.mList = var3;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 55});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 53});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 57});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 36) {
            if (var3 != 39) {
               if (var3 != 41) {
                  if (var3 != 48) {
                     if (var3 != 57) {
                        switch (var3) {
                           case 53:
                              this.set0x35DashboardInfo2(var4);
                              break;
                           case 54:
                              this.set0x36TireInfo(var4);
                              break;
                           case 55:
                              this.set0x37FuelInfo(var4);
                        }
                     } else {
                        this.set0x39CDCInfo(var4);
                     }
                  } else {
                     this.updateVersionInfo(this.mContext, this.getVersionStr(var2));
                  }
               } else {
                  this.set0x29Track(var4);
               }
            } else {
               this.set0x27AirInfo(var4);
            }
         } else {
            this.set0x24BasicInfo(var4);
         }
      } else {
         this.set0x20WheelKey(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalCarDevice();
      GeneralTireData.isHaveSpareTire = false;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void onAccOff() {
      super.onAccOff();
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.CDC.equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 1});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   private static class OriginalDeviceData {
      private final String[] bottomBtns;
      private final List list;

      public OriginalDeviceData(List var1) {
         this(var1, (String[])null);
      }

      public OriginalDeviceData(List var1, String[] var2) {
         this.list = var1;
         this.bottomBtns = var2;
      }

      public String[] getBottomBtns() {
         return this.bottomBtns;
      }

      public List getItemList() {
         return this.list;
      }
   }
}
