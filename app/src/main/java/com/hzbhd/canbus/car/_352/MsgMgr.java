package com.hzbhd.canbus.car._352;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   List list;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private CountDownTimer panoramaTimer;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void adjustBrightness() {
      int var1 = this.getBrightness();
      if (var1 == 5) {
         this.setBacklightLevel(0);
      } else {
         this.setBacklightLevel(var1 + 1);
      }

   }

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

   private String get0x21Media(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? this.mContext.getString(2131764342) : this.mContext.getString(2131764345);
      } else {
         return this.mContext.getString(2131764344);
      }
   }

   private Object getCompassState(boolean var1) {
      return var1 ? this.mContext.getString(2131764338) : this.mContext.getString(2131764339);
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getUsbStateInfo(int var1) {
      switch (var1) {
         case 1:
            return ":" + this.mContext.getString(2131764347);
         case 2:
            return ":" + this.mContext.getString(2131764348);
         case 3:
            return ":" + this.mContext.getString(2131764349);
         case 4:
            return ":" + this.mContext.getString(2131764350);
         case 5:
            return ":" + this.mContext.getString(2131764351);
         case 6:
            return ":" + this.mContext.getString(2131764343);
         default:
            return ":" + this.mContext.getString(2131764346);
      }
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
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_1", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_2", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_3", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_4", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_5", (String)null));
      SparseArray var1 = new SparseArray();
      this.mOriginalDeviceDataArray = var1;
      var1.put(33, new OriginalDeviceData(var2));
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
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

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
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

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void send0xC2(int var1, String var2, String var3) {
      byte var4;
      if (!var2.equals("FM") && !var2.equals("FM3")) {
         if (var2.equals("FM1")) {
            var4 = 1;
         } else if (var2.equals("FM2")) {
            var4 = 2;
         } else {
            var4 = 16;
         }
      } else {
         var4 = 0;
      }

      int var5;
      int var6;
      if (var4 == 16) {
         var6 = this.getLsb(Integer.parseInt(var3));
         var5 = this.getMsb(Integer.parseInt(var3));
      } else {
         var6 = this.getLsb(Integer.parseInt(var3) * 100);
         var5 = this.getMsb(Integer.parseInt(var3) * 100);
      }

      this.getUiMgr(this.mContext).send0xC2(var4, var6, var5, var1);
   }

   private void set0x14backLightInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 <= 50) {
         this.setBacklightLevel(1);
      } else if (var1 > 50 && var1 <= 100) {
         this.setBacklightLevel(2);
      } else if (var1 > 100 && var1 <= 150) {
         this.setBacklightLevel(3);
      } else if (var1 > 150 && var1 <= 200) {
         this.setBacklightLevel(4);
      } else if (var1 > 200 && var1 <= 255) {
         this.setBacklightLevel(5);
      }

   }

   private void set0x16SpeedInfo() {
      ArrayList var5 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_352_car_info");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_352_car_speed");
      StringBuilder var4 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var2, var1, var4.append(this.getMsbLsbResult(var3[3], var3[2])).append("km/h").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      var3 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var3[3], var3[2]));
   }

   private void set0x20WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 23) {
                        if (var1 != 24) {
                           switch (var1) {
                              case 7:
                                 this.buttonKey(2);
                                 break;
                              case 8:
                                 this.buttonKey(187);
                                 break;
                              case 9:
                                 this.buttonKey(14);
                                 break;
                              case 10:
                                 this.buttonKey(15);
                                 break;
                              case 11:
                                 this.buttonKey(187);
                                 break;
                              case 12:
                                 this.buttonKey(14);
                                 break;
                              case 13:
                                 this.buttonKey(15);
                           }
                        } else {
                           this.buttonKey(182);
                        }
                     } else {
                        this.buttonKey(151);
                     }
                  } else {
                     this.buttonKey(45);
                  }
               } else {
                  this.buttonKey(46);
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

   private void set0x21MediaInfo() {
      OriginalDeviceData var2 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(33);
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.cdStatus = this.get0x21Media(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
      GeneralOriginalCarDeviceData.runningState = this.getUsbStateInfo(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
      GeneralOriginalCarDeviceData.mList = this.setInfo(this.mCanBusInfoInt);
      OriginalCarDevicePageUiSet var1 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var1.setItems(var2.getItemList());
      var1.setRowBottomBtnAction(var2.getBottomBtns());
      Bundle var3 = new Bundle();
      var3.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var3);
   }

   private void set0x22RearRadar() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23FrontRadar() {
      if (this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x24BasicInfo() {
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x29EPSInfo() {
      if (this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x70RightCameraInfo() {
      if (this.isPanoramicInfoChange()) {
         boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         boolean var2 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         if (this.list == null) {
            this.list = new ArrayList();
         }

         this.list.add(new PanoramicBtnUpdateEntity(0, var1));
         this.list.add(new PanoramicBtnUpdateEntity(1, var2));
         GeneralParkData.dataList = this.list;
         this.updateParkUi((Bundle)null, this.mContext);
         if (var2) {
            this.forceReverse(this.mContext, true);
            CountDownTimer var3 = this.panoramaTimer;
            if (var3 != null) {
               var3.cancel();
               this.panoramaTimer = null;
            }
         } else {
            this.forceReverse(this.mContext, false);
         }
      }

   }

   private void set0x71SpeedInfo() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_352_car_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_352_car_tire_speed");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[3], var4[2])).append("rpm").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0xD2CompassInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_352_setting_zhi_nan"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan1"), this.getCompassState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_352_setting_zhi_nan"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 1));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_352_setting_zhi_nan"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan3"), this.mCanBusInfoInt[3] * 360 / 256));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private List setInfo(int[] var1) {
      String var5 = var1[4] + ":" + var1[5];
      String var3 = this.getMsbLsbResult(var1[6], var1[7]) + "";
      String var2 = this.getMsbLsbResult(var1[8], var1[9]) + "";
      String var4 = var1[10] + "";
      String var6 = var1[11] + "%";
      ArrayList var7 = new ArrayList();
      if (var5 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(0, var5));
      }

      if (var3 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(1, var3));
      }

      if (var2 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(2, var2));
      }

      if (var4 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(3, var4));
      }

      if (var6 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(4, var6));
      }

      return var7;
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.getUiMgr(this.mContext).send0xC0(3, 34);
      this.getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).send0xC0(7, 48);
      this.getUiMgr(this.mContext).send0xC3(48, 0, 0, 0, 0, 0, 0);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.getUiMgr(this.mContext).send0xC0(6, 34);
      this.getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC0(5, 64);
      this.getUiMgr(this.mContext).send0xC3(64, 0, 0, 0, 0, 0, 0);
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
         if (var3 != 22) {
            if (var3 != 41) {
               if (var3 != 48) {
                  if (var3 != 210) {
                     if (var3 != 112) {
                        if (var3 != 113) {
                           switch (var3) {
                              case 32:
                                 this.set0x20WheelKeyInfo();
                                 break;
                              case 33:
                                 this.set0x21MediaInfo();
                                 break;
                              case 34:
                                 this.set0x22RearRadar();
                                 break;
                              case 35:
                                 this.set0x23FrontRadar();
                                 break;
                              case 36:
                                 this.set0x24BasicInfo();
                           }
                        } else {
                           this.set0x71SpeedInfo();
                        }
                     } else {
                        this.set0x70RightCameraInfo();
                     }
                  } else {
                     this.set0xD2CompassInfo();
                  }
               } else {
                  this.set0x30VersionInfo();
               }
            } else {
               this.set0x29EPSInfo();
            }
         } else {
            this.set0x16SpeedInfo();
         }
      } else {
         this.set0x14backLightInfo();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.getUiMgr(this.mContext).send0xC4(var1);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0xC8(0, var10 ^ 1, var5, var6);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0xC0(2, 34);
      this.getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.getUiMgr(this.mContext).send0xC0(3, 34);
      this.getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).makeConnetion();
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
      this.initOriginalCarDevice();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUiMgr(this.mContext).send0xC0(8, 19);
      this.getUiMgr(this.mContext).send0xC3(19, var4, var3, 0, 0, var6, var7);
   }

   public void panoramicSwitch(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.getUiMgr(this.mContext).send0xC0(1, 1);
      this.send0xC2(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC3(16, 0, 0, 0, 0, 0, 0);
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

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      this.getUiMgr(this.mContext).send0xC0(8, 19);
      this.getUiMgr(this.mContext).send0xC3(19, var4, var3, 0, 0, var6, var7);
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
