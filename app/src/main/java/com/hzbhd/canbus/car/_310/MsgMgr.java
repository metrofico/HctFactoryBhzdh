package com.hzbhd.canbus.car._310;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final int AMPLIFIER_BAND_OFFSET = 2;
   static final int AMPLIFIER_FAD_MIDDLE_VALUE = 7;
   static final String SHARE_IS_SUPPORT_PASSENGER_WIND_MODE = "share_is_support_passenger_wind_mode";
   private final int DATA_TYPE = 1;
   private final int DRI_PAGE_HISTORICAL_FUEL_CONSUMPTION = 0;
   private final int DRI_PAGE_LAST_15_MINUTE_FUEL_CONSUMPTION = 1;
   private final int DRI_PAGE_VEHICLE_INFO = 2;
   private final int INVAILE_VALUE = -1;
   private final String MEDIA_TYPE_AUX = "AUX";
   private final String MEDIA_TYPE_CD_DVD = "CD/DVD";
   private final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
   private final String MEDIA_TYPE_OTHER = "OTHER";
   private final String MEDIA_TYPE_RADIO = "RADIO";
   private final String MEDIA_TYPE_USB = "USB";
   private final String TAG = "_310_MsgMgr";
   private int m0x1EData4;
   private int[] mAirFrontData;
   private int[] mAirRearData;
   private int mAmplifierSettingData;
   private int mCanId;
   private SparseArray mCanbusDataArray;
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private Context mContext;
   private DecimalFormat mDecimalFormat00;
   private DecimalFormat mDecimalFormat0p0;
   private DecimalFormat mDecimalFormat0p00;
   private int mEachId;
   private boolean mIsBackOpen;
   private boolean mIsBeltTie;
   private boolean mIsFahrenheit;
   private boolean mIsFrontOpen;
   private boolean mIsLeftFrontOpen;
   private boolean mIsLeftRearOpen;
   private boolean mIsPanoramicStart;
   private boolean mIsRightFrontOpen;
   private boolean mIsRightRearOpen;
   private int mIsSkylightOpen;
   private int mMediaType;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   private byte mOutDoorTempValue;
   private HashMap mSettingItemIndeHashMap;
   private UiMgr mUiMgr;

   private String fahrenheit(Context var1, float var2) {
      return GeneralAirData.fahrenheit_celsius ? this.mDecimalFormat0p0.format((double)(var2 * 9.0F / 5.0F + 32.0F)) + this.getTempUnitF(var1) : var2 + this.getTempUnitC(var1);
   }

   private String getAirTemperature(Context var1, int var2) {
      if (var2 == 0) {
         return "LO";
      } else if (var2 == 31) {
         return "HI";
      } else if (var2 >= 1 && var2 <= 29) {
         return this.fahrenheit(var1, (float)(var2 + 35) * 0.5F);
      } else {
         return var2 >= 33 && var2 <= 38 ? this.fahrenheit(var1, (float)(var2 - 3) * 0.5F) : "";
      }
   }

   private int getAirWhat() {
      int[] var1 = new int[6];
      int[] var2 = this.mCanbusInfoInt;
      var1[0] = var2[2];
      var1[1] = var2[3];
      var1[2] = var2[4];
      var1[3] = var2[5];
      var1[4] = var2[6] & 254;
      var1[5] = var2[10];
      int[] var3 = new int[]{var2[7], var2[8], var2[9]};
      if (!Arrays.equals(this.mAirFrontData, var1)) {
         this.mAirFrontData = Arrays.copyOf(var1, 6);
         return 1001;
      } else if (!Arrays.equals(this.mAirRearData, var3)) {
         this.mAirRearData = Arrays.copyOf(var3, 3);
         return 1002;
      } else {
         return 0;
      }
   }

   private int getData(int... var1) {
      int var3 = 0;

      int var2;
      for(var2 = 0; var3 < var1.length; ++var3) {
         var2 += var1[var3] << var3 * 8;
      }

      return (double)var2 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var2;
   }

   private String getInfo(int var1, float var2, String var3) {
      if (var1 != -1) {
         var3 = this.mDecimalFormat0p0.format((double)((float)var1 * var2)) + var3;
      } else {
         var3 = "---";
      }

      return var3;
   }

   private String getMuteStatus(boolean var1) {
      return var1 ? "_309_value_4" : "_309_value_3";
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private List getOriginalDeviceCdDvdUsbUpdateEntityList(int[] var1) {
      String var4;
      String var6;
      label18: {
         int var2 = var1[3];
         var6 = "";
         if (var2 != 255) {
            int var3 = var1[4];
            if (var3 != 255) {
               var4 = Integer.toString(var2 | var3 << 8);
               break label18;
            }
         }

         var4 = "";
      }

      String var5 = var6;
      if (var1[5] != 255) {
         var5 = var6;
         if (var1[6] != 255) {
            var5 = this.mDecimalFormat00.format((long)var1[5]) + ":" + this.mDecimalFormat00.format((long)var1[6]);
         }
      }

      ArrayList var7 = new ArrayList();
      var7.add(new OriginalCarDeviceUpdateEntity(0, var4));
      var7.add(new OriginalCarDeviceUpdateEntity(1, var5));
      return var7;
   }

   private List getOriginalDeviceRadioUpdateEntityList(int[] var1) {
      int var2 = var1[5] << 8 | var1[4];
      String var3;
      String var5;
      if (var1[3] == 16) {
         var5 = var2 + " KHz";
         var3 = "AM";
      } else {
         var3 = "FM" + var1[3];
         var5 = this.mDecimalFormat0p00.format((double)((float)var2 / 100.0F)) + " MHz";
      }

      if (var2 == 65535) {
         var5 = "";
      }

      ArrayList var4 = new ArrayList();
      var4.add(new OriginalCarDeviceUpdateEntity(0, var3));
      var4.add(new OriginalCarDeviceUpdateEntity(1, var5));
      return var4;
   }

   private String getPowerStatus(boolean var1) {
      return var1 ? "_16_value_34" : "_16_value_33";
   }

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         SettingUpdateEntity var2 = new SettingUpdateEntity(-1, -1, (Object)null);
         this.mSettingItemIndeHashMap.put(var1, var2);
         return var2;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getYesOrNone(boolean var1) {
      return var1 ? "_16_value_30" : "_16_value_29";
   }

   private void initOriginalDeviceDataArray() {
      OriginalDeviceData var1 = new OriginalDeviceData(new ArrayList());
      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", (String)null));
      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_play_time", (String)null));
      SparseArray var4 = new SparseArray();
      this.mOriginalDeviceDataArray = var4;
      var4.put(0, var1);
      this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(var2));
      this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(var3, new String[]{"power"}));
      this.mOriginalDeviceDataArray.put(3, var1);
      this.mOriginalDeviceDataArray.put(4, new OriginalDeviceData(var3));
      this.mOriginalDeviceDataArray.put(255, var1);
   }

   private void initSettingsItem(Context var1) {
      Log.i("_310_MsgMgr", "initSettingsItem: ");
      this.mSettingItemIndeHashMap = new HashMap();
      SettingPageUiSet var4 = UiMgrFactory.getCanUiMgr(var1).getSettingUiSet(var1);

      for(int var2 = 0; var2 < var4.getList().size(); ++var2) {
         List var6 = ((SettingPageUiSet.ListBean)var4.getList().get(var2)).getItemList();

         for(int var3 = 0; var3 < var6.size(); ++var3) {
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)var6.get(var3)).getTitleSrn();
            this.mSettingItemIndeHashMap.put(var5, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private boolean isDoorStatusChange() {
      if (!(this.mIsLeftFrontOpen ^ GeneralDoorData.isLeftFrontDoorOpen) && !(this.mIsRightFrontOpen ^ GeneralDoorData.isRightFrontDoorOpen) && !(this.mIsLeftRearOpen ^ GeneralDoorData.isLeftRearDoorOpen) && !(this.mIsRightRearOpen ^ GeneralDoorData.isRightRearDoorOpen) && !(this.mIsBackOpen ^ GeneralDoorData.isBackOpen) && !(this.mIsBeltTie ^ GeneralDoorData.isSeatBeltTie) && !(this.mIsFrontOpen ^ GeneralDoorData.isFrontOpen) && this.mIsSkylightOpen == GeneralDoorData.skyWindowOpenLevel) {
         return false;
      } else {
         this.mIsLeftFrontOpen = GeneralDoorData.isLeftFrontDoorOpen;
         this.mIsRightFrontOpen = GeneralDoorData.isRightFrontDoorOpen;
         this.mIsLeftRearOpen = GeneralDoorData.isLeftRearDoorOpen;
         this.mIsRightRearOpen = GeneralDoorData.isRightRearDoorOpen;
         this.mIsBackOpen = GeneralDoorData.isBackOpen;
         this.mIsBeltTie = GeneralDoorData.isSeatBeltTie;
         this.mIsFrontOpen = GeneralDoorData.isFrontOpen;
         this.mIsSkylightOpen = GeneralDoorData.skyWindowOpenLevel;
         return true;
      }
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanbusInfoInt[3]);
   }

   private void sendAmplifierInit(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, this.getUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         byte[][] commands;
         int i;
         final MsgMgr this$0;
         final TimerUtil val$timer;

         {
            this.this$0 = var1;
            this.val$timer = var2;
            byte var6 = (byte)(GeneralAmplifierData.frontRear + 7);
            byte var8 = (byte)(GeneralAmplifierData.leftRight + 7);
            byte var4 = (byte)(GeneralAmplifierData.bandBass + 2);
            byte var7 = (byte)(GeneralAmplifierData.bandTreble + 2);
            byte var3 = (byte)(GeneralAmplifierData.bandMiddle + 2);
            byte var5 = (byte)GeneralAmplifierData.volume;
            this.commands = new byte[][]{{22, -124, 1, var6}, {22, -124, 2, var8}, {22, -124, 4, var4}, {22, -124, 5, var7}, {22, -124, 6, var3}, {22, -124, 7, var5}};
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.commands;
            if (var1 < var2.length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.i;
            } else {
               this.val$timer.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private void sendOutDoorTemperature(Context var1) {
      byte var2 = this.mOutDoorTempValue;
      String var3;
      if (var2 == 0) {
         var3 = " ";
      } else {
         var3 = this.fahrenheit(var1, (float)var2);
      }

      this.updateOutDoorTemp(var1, var3);
   }

   private void set0x1DData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanbusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x1EData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var4 = this.mCanbusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var4[2], var4[3], var4[4], var4[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
         int var2 = this.m0x1EData4;
         int var3 = this.mCanbusInfoInt[6];
         if (var2 != var3) {
            this.m0x1EData4 = var3;
            ArrayList var5 = new ArrayList();
            var5.add(this.getSettingUpdateEntity("radar_distance").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 6, 1)));
            var5.add(this.getSettingUpdateEntity("_283_radar_switch").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 5, 1)));
            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 3);
            var5.add(this.getSettingUpdateEntity("radar_volume").setValue(var2).setProgress(var2 - 1));
            this.updateGeneralSettingData(var5);
            this.updateSettingActivity((Bundle)null);
         }
      }

   }

   private void set0x20WheelKeyData(Context var1) {
      short var2;
      label80: {
         int[] var5 = this.mCanbusInfoInt;
         byte var3 = 2;
         int var4 = var5[2];
         var2 = var3;
         label63:
         switch (var4) {
            case 2:
               break;
            case 3:
               var2 = 20;
               break label80;
            case 4:
               var2 = 21;
            case 5:
               break label80;
            case 6:
               var2 = 3;
               break label80;
            case 7:
               var2 = 187;
               break label80;
            case 8:
               var2 = 14;
               break label80;
            case 9:
               var2 = 15;
               break label80;
            default:
               switch (var4) {
                  case 19:
                     var2 = 45;
                     break label80;
                  case 20:
                     var2 = 46;
                     break label80;
                  case 21:
                     var2 = 50;
                     break label80;
                  case 22:
                     var2 = 49;
                     break label80;
                  default:
                     var2 = var3;
                     switch (var4) {
                        case 129:
                           break;
                        case 130:
                           break label63;
                        case 131:
                           var2 = 65;
                           break label80;
                        case 132:
                           var2 = 66;
                           break label80;
                        case 133:
                           var2 = 47;
                           break label80;
                        case 134:
                           var2 = 48;
                           break label80;
                        case 135:
                           var2 = 134;
                        case 136:
                           break label80;
                        default:
                           var2 = 0;
                           break label80;
                     }
               }
            case 1:
               var2 = 7;
               break label80;
         }

         var2 = 8;
      }

      this.realKeyLongClick1(var1, var2);
   }

   private void set0x23HisFuelConsData() {
      int var1 = this.mCanbusInfoInt[2];
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var3 = "";
            } else {
               var3 = "L/100KM";
            }
         } else {
            var3 = "KM/L";
         }
      } else {
         var3 = "MPG";
      }

      ArrayList var5 = new ArrayList();

      for(var1 = 0; var1 < 6; ++var1) {
         int var2 = var1 * 2;
         int[] var4 = this.mCanbusInfoInt;
         var5.add(new DriverUpdateEntity(0, var1, this.getInfo(this.getData(var4[var2 + 2 + 2], var4[var2 + 1 + 2]), 0.1F, " " + var3)));
      }

      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x24BaseInfoData(Context var1) {
      int[] var4 = this.mCanbusInfoInt;
      byte var2 = 2;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var4[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
      boolean var3;
      if (!DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2])) {
         var3 = false;
      } else {
         var3 = true;
      }

      GeneralDoorData.isBackOpen = var3;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
      if (!DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[3])) {
         var2 = 0;
      }

      GeneralDoorData.skyWindowOpenLevel = var2;
      if (this.isDoorStatusChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x26VehicleSettingData() {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         ArrayList var2 = new ArrayList();
         var2.add(this.getSettingUpdateEntity("light_ctrl_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 7, 1)));
         var2.add(this.getSettingUpdateEntity("light_ctrl_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 3)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_3_43").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 2)));
         var2.add(this.getSettingUpdateEntity("_55_0x67_data1_bit32").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 2)));
         var2.add(this.getSettingUpdateEntity("_310_speed_auto_lock").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 7, 1)));
         var2.add(this.getSettingUpdateEntity("_310_shift_auto_lock").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 6, 1)));
         var2.add(this.getSettingUpdateEntity("_310_position_p_unlock").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 5, 1)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_2_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 4, 1)));
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 3);
         var2.add(this.getSettingUpdateEntity("hiworld_jeep_123_0x60_data1_65").setValue(var1).setProgress(var1));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_2_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 7, 1)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_1_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 6, 1)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_1_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 5, 1)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_2_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 4, 1)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_2_7").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 3, 1)));
         var2.add(this.getSettingUpdateEntity("_11_0x26_data2_bit20").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 0, 3)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_1_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 7, 1)));
         var2.add(this.getSettingUpdateEntity("_18_vehicle_setting_item_1_0").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 6, 1)));
         var2.add(this.getSettingUpdateEntity("_11_0x26_data3_bit32").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 2, 2)));
         var2.add(this.getSettingUpdateEntity("_55_0x65_data1_bit21").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 0, 2)));
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 2, 3);
         var2.add(this.getSettingUpdateEntity("_310_auto_circulation_sensitivity").setValue(var1 + 1).setProgress(var1));
         var2.add(this.getSettingUpdateEntity("_157_seat_auto_move").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 2)));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x27Last15FuelConsData() {
      int var1 = this.mCanbusInfoInt[2];
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var3 = "";
            } else {
               var3 = "L/100KM";
            }
         } else {
            var3 = "KM/L";
         }
      } else {
         var3 = "MPG";
      }

      ArrayList var5 = new ArrayList();

      for(var1 = 1; var1 <= 15; ++var1) {
         int var2 = var1 * 2;
         int[] var4 = this.mCanbusInfoInt;
         var5.add(new DriverUpdateEntity(1, var1 - 1, this.getInfo(this.getData(var4[32 - var2 + 2], var4[31 - var2 + 2]), 0.1F, var3)));
      }

      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x28AirData(Context var1) {
      int[] var2 = this.mCanbusInfoInt;
      var2[3] &= 239;
      if (this.isDataChange(var2)) {
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[6]);
         if (this.mIsFahrenheit ^ GeneralAirData.fahrenheit_celsius) {
            this.mIsFahrenheit = GeneralAirData.fahrenheit_celsius;
            this.sendOutDoorTemperature(var1);
         }

         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]) ^ true;
         GeneralAirData.negative_ion = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
         GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.getAirTemperature(var1, this.mCanbusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getAirTemperature(var1, this.mCanbusInfoInt[5]);
         GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[6]);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[6]);
         GeneralAirData.swing = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[6]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[6]);
         GeneralAirData.rear_left_temperature = this.getAirTemperature(var1, this.mCanbusInfoInt[7]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[8]);
         GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[8]);
         GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[8]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[8]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[8], 0, 4);
         GeneralAirData.rear_right_temperature = this.getAirTemperature(var1, this.mCanbusInfoInt[9]);
         if (SharePreUtil.getBoolValue(var1, "share_is_support_passenger_wind_mode", false)) {
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[10]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[10]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[10]);
         } else {
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         }

         GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
         GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
         this.updateAirActivity(var1, this.getAirWhat());
      }

   }

   private void set0x2AVehicleData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         String var4;
         String var5;
         label27: {
            int var3 = this.mCanbusInfoInt[13];
            var5 = "";
            if (var3 != 0) {
               if (var3 == 1) {
                  var4 = "MILE";
                  break label27;
               }

               if (var3 == 2) {
                  var4 = "KM";
                  break label27;
               }
            }

            var4 = "";
         }

         ArrayList var6 = new ArrayList();
         int[] var7 = this.mCanbusInfoInt;
         var6.add(new DriverUpdateEntity(2, 0, this.getInfo(this.getData(var7[4], var7[3]), 1.0F, " RPM")));
         if (!TextUtils.isEmpty(var4)) {
            var5 = var4 + "/H";
         }

         var7 = this.mCanbusInfoInt;
         var6.add(new DriverUpdateEntity(2, 1, this.getInfo(this.getData(var7[6], var7[5]), 1.0F, " " + var5)));
         int[] var10 = this.mCanbusInfoInt;
         var6.add(new DriverUpdateEntity(2, 2, this.getInfo(this.getData(var10[9], var10[8], var10[7]), 1.0F, "  " + var4)));
         var10 = this.mCanbusInfoInt;
         var6.add(new DriverUpdateEntity(2, 3, this.getInfo(this.getData(var10[11], var10[10]), 1.0F, "  " + var4)));
         this.updateGeneralDriveData(var6);
         this.updateDriveDataActivity((Bundle)null);
         byte var9 = this.mOutDoorTempValue;
         byte var2 = this.mCanbusInfoByte[12];
         if (var9 != var2) {
            this.mOutDoorTempValue = var2;
            this.sendOutDoorTemperature(var1);
         }

         int[] var8 = this.mCanbusInfoInt;
         this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var8[5], var8[6]));
      }

   }

   private void set0x30VersionData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanbusInfoByte));
   }

   private void set0x31AmpliferData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         GeneralAmplifierData.frontRear = 7 - DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 4);
         GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 4) - 7;
         GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 4, 4) - 2;
         GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4) - 2;
         GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 4, 4) - 2;
         GeneralAmplifierData.volume = this.mCanbusInfoInt[5];
         this.updateAmplifierActivity((Bundle)null);
         this.saveAmplifierData(var1, this.mCanId);
         int[] var3 = this.mCanbusInfoInt;
         int var2 = var3[4];
         var2 = var3[6] | (var2 & 15) << 8;
         if (this.mAmplifierSettingData != var2) {
            this.mAmplifierSettingData = var2;
            ArrayList var4 = new ArrayList();
            var4.add(this.getSettingUpdateEntity("_186_asl").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 3, 1)));
            var4.add(this.getSettingUpdateEntity("_16_title_23").setValue(this.mCanbusInfoInt[6]));
            this.updateGeneralSettingData(var4);
            this.updateSettingActivity((Bundle)null);
         }
      }

   }

   private void set0x32SystemInfoData() {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         ArrayList var2 = new ArrayList();
         var2.add(this.getSettingUpdateEntity("_301_original_car_power_amplifier").setValue(this.getYesOrNone(DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]))));
         var2.add(this.getSettingUpdateEntity("_301_original_car_rear_seat").setValue(this.getYesOrNone(DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]))));
         var2.add(this.getSettingUpdateEntity("_310_panoramic_node").setValue(this.getYesOrNone(DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]))));
         var2.add(this.getSettingUpdateEntity("_310_amplifier_status").setValue(this.getPowerStatus(DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]))));
         var2.add(this.getSettingUpdateEntity("_310_mute_status").setValue(this.getMuteStatus(DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]))));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
         boolean var1;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 2) == 3) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (this.mIsPanoramicStart ^ var1) {
            this.mIsPanoramicStart = var1;
            this.forceReverse(this.mContext, var1);
         }
      }

   }

   private void set0x35WheelTrackData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         byte[] var2 = this.mCanbusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 0, 384, 12);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x62OriginalDeviceData(Context var1) {
      OriginalDeviceData var5 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(this.mCanbusInfoInt[2]);
      Bundle var4 = null;
      GeneralOriginalCarDeviceData.mList = null;
      int var2 = this.mCanbusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 255) {
                        GeneralOriginalCarDeviceData.cdStatus = "OTHER";
                     }
                  } else {
                     GeneralOriginalCarDeviceData.cdStatus = "USB";
                     GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanbusInfoInt);
                  }
               } else {
                  GeneralOriginalCarDeviceData.cdStatus = "AUX";
               }
            } else {
               GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
               GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanbusInfoInt);
            }
         } else {
            GeneralOriginalCarDeviceData.cdStatus = "RADIO";
            GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceRadioUpdateEntityList(this.mCanbusInfoInt);
         }
      } else {
         GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
      }

      int var3 = this.mMediaType;
      var2 = this.mCanbusInfoInt[2];
      if (var3 != var2) {
         this.mMediaType = var2;
         OriginalCarDevicePageUiSet var6 = this.getOriginalCarDevicePageUiSet(var1);
         var6.setItems(var5.getItemList());
         var6.setRowBottomBtnAction(var5.getBottomBtns());
         var4 = new Bundle();
         var4.putBoolean("bundle_key_orinal_init_view", true);
      }

      this.updateOriginalCarDeviceActivity(var4);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      (new Thread(this, var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void run() {
            super.run();
            this.this$0.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(this.val$context);
            MsgMgr var2 = this.this$0;
            var2.mEachId = var2.getCurrentEachCanId();
            this.this$0.mCanbusDataArray = new SparseArray();
            this.this$0.mDecimalFormat0p0 = new DecimalFormat("0.0");
            this.this$0.mDecimalFormat0p00 = new DecimalFormat("0.00");
            this.this$0.mDecimalFormat00 = new DecimalFormat("00");
            this.this$0.mAirFrontData = new int[0];
            this.this$0.mAirRearData = new int[0];
            this.this$0.initSettingsItem(this.val$context);
            this.this$0.initOriginalDeviceDataArray();
            int var1 = this.val$context.getPackageManager().getComponentEnabledSetting(Constant.OriginalDeviceActivity);
            if (var1 == 2 || var1 == 3) {
               SelectCanTypeUtil.enableApp(this.val$context, Constant.OriginalDeviceActivity);
            }

         }
      }).start();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoInt = var4;
      this.mCanbusInfoByte = var2;
      int var3 = var4[1];
      if (var3 != 29) {
         if (var3 != 30) {
            if (var3 != 32) {
               if (var3 != 42) {
                  if (var3 != 53) {
                     if (var3 != 98) {
                        if (var3 != 35) {
                           if (var3 != 36) {
                              switch (var3) {
                                 case 38:
                                    this.set0x26VehicleSettingData();
                                    break;
                                 case 39:
                                    this.set0x27Last15FuelConsData();
                                    break;
                                 case 40:
                                    this.set0x28AirData(var1);
                                    break;
                                 default:
                                    switch (var3) {
                                       case 48:
                                          this.set0x30VersionData(var1);
                                          break;
                                       case 49:
                                          this.set0x31AmpliferData(var1);
                                          break;
                                       case 50:
                                          this.set0x32SystemInfoData();
                                    }
                              }
                           } else {
                              this.set0x24BaseInfoData(var1);
                           }
                        } else {
                           this.set0x23HisFuelConsData();
                        }
                     } else {
                        this.set0x62OriginalDeviceData(var1);
                     }
                  } else {
                     this.set0x35WheelTrackData(var1);
                  }
               } else {
                  this.set0x2AVehicleData(var1);
               }
            } else {
               this.set0x20WheelKeyData(var1);
            }
         } else {
            this.set0x1EData(var1);
         }
      } else {
         this.set0x1DData(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.i("_310_MsgMgr", "dateTimeRepCanbus: bHours" + var5 + ", bHours24H" + var8);
      CanbusMsgSender.sendMsg(new byte[]{22, -123, 0, 0, 0, (byte)(var5 % 12), (byte)var6, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      int var2 = this.mEachId;
      if (var2 != 255) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)var2});
      }

      this.sendAmplifierInit(var1);
   }

   void msgMgrUpdateSettingActivity(String var1, Object var2) {
      ArrayList var3 = new ArrayList();
      var3.add(this.getSettingUpdateEntity(var1).setValue(var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.sendAmplifierInit(this.mContext);
      }

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
