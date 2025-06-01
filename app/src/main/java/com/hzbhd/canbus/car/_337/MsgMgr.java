package com.hzbhd.canbus.car._337;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SetAlertView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
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
   String model = "NOT YET";
   private int nowValue = -1;
   String song = "NOT YET";
   String songTitle = "NOT YET";

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void adjustBrightness() {
      int var1 = FutureUtil.instance.getBrightness();
      if (var1 == 5) {
         FutureUtil.instance.setBrightness(0);
      } else {
         FutureUtil.instance.setBrightness(var1 + 1);
      }

   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private String get0x38Result() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 128) {
                  var2 = "-1";
               } else {
                  var2 = this.mContext.getString(2131763806);
               }
            } else {
               var2 = this.mContext.getString(2131763805);
            }
         } else {
            var2 = this.mContext.getString(2131763804);
         }
      } else {
         var2 = this.mContext.getString(2131763803);
      }

      StringBuilder var3 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      String var5 = var3.append(this.getMsbLsbResult(var4[3], var4[4])).append("").toString();
      var1 = 5;

      String var6;
      for(var6 = "%"; var1 < this.mCanBusInfoInt.length; ++var1) {
         String var7 = var6;
         if (var1 != 5) {
            var7 = var6 + "%";
         }

         var6 = var7 + String.format("%02x", this.mCanBusInfoInt[var1]);
      }

      if (!var6.equals("%")) {
         var6 = this.getUTF8Result(var6);
      } else {
         var6 = "-1";
      }

      if (var2.equals("-1") && var5.equals("-1") && var6.equals("-1")) {
         return "-1";
      } else if (var5.equals("-1")) {
         return var2 + "-" + var6;
      } else if (var2.equals("-1")) {
         return var5 + "." + var6;
      } else if (var6.equals("-1")) {
         return var5 + "." + var2;
      } else {
         return var5 + "." + var2 + "-" + var6;
      }
   }

   private boolean getAirSettingItemIsVisibility(String var1) {
      return this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", var1) != -1;
   }

   private String getAlertContent() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 21) {
         switch (var1) {
            case 1:
               return this.mContext.getString(2131763757);
            case 2:
               return this.mContext.getString(2131763763);
            case 3:
               return this.mContext.getString(2131763764);
            case 4:
               return this.mContext.getString(2131763765);
            case 5:
               return this.mContext.getString(2131763766);
            case 6:
               return this.mContext.getString(2131763767);
            case 7:
               return this.mContext.getString(2131763768);
            case 8:
               return this.mContext.getString(2131763769);
            case 9:
               return this.mContext.getString(2131763770);
            default:
               switch (var1) {
                  case 16:
                     return this.mContext.getString(2131763758);
                  case 17:
                     return this.mContext.getString(2131763759);
                  case 18:
                     return this.mContext.getString(2131763760);
                  case 19:
                     return this.mContext.getString(2131763761);
                  default:
                     return this.mContext.getString(2131763756);
               }
         }
      } else {
         return this.mContext.getString(2131763762);
      }
   }

   private String getAmplifierState() {
      return DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131763794) : this.mContext.getString(2131763793);
   }

   private String getAmplifierTemperature() {
      return this.mCanBusInfoInt[11] == 255 ? this.mContext.getString(2131763828) : this.mCanBusInfoInt[11] - 40 + this.getTempUnitC(this.mContext);
   }

   private Object getAmplifierVoltage() {
      return this.mCanBusInfoInt[10] == 255 ? this.mContext.getString(2131763828) : (double)this.mCanBusInfoInt[10] * 0.25 + "V";
   }

   private boolean getCarConfigInfoSettingItemIsVisibility(String var1) {
      return this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_config", var1) != -1;
   }

   private boolean getCarSettingItemIsVisibility(String var1) {
      return this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", var1) != -1;
   }

   private String getCdState() {
      return DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? this.mContext.getString(2131763797) : this.mContext.getString(2131763796);
   }

   private String getDipAngle() {
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var1 = this.mContext.getString(2131763829);
      } else {
         var1 = this.mContext.getString(2131763830);
      }

      return var1 + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7) + "°";
   }

   private int getEspResult(int var1, int var2) {
      return (int)((double)((var1 & 255) << 8) + (double)var2 * 0.05);
   }

   private String getJobState(int var1) {
      switch (var1) {
         case 1:
            return this.mContext.getString(2131763808);
         case 2:
            return this.mContext.getString(2131763809);
         case 3:
            return this.mContext.getString(2131763810);
         case 4:
            return this.mContext.getString(2131763811);
         case 5:
            return this.mContext.getString(2131763812);
         case 6:
            return this.mContext.getString(2131763813);
         case 7:
         default:
            return this.mContext.getString(2131763799);
         case 8:
            return this.mContext.getString(2131763814);
         case 9:
            return this.mContext.getString(2131763815);
      }
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
         this.mOriginalCarDevicePageUiSet = this.getUigMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private List getOriginalDeviceCdDvdUsbUpdateEntityList() {
      ArrayList var2 = new ArrayList();
      int[] var3 = this.mCanBusInfoInt;
      if (var3[1] == 56) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(var3[2], 4, 2);
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.model = this.mContext.getString(2131763802);
               }
            } else {
               this.model = this.mContext.getString(2131763801);
            }
         } else {
            this.model = this.mContext.getString(2131763800);
         }

         StringBuilder var5 = new StringBuilder();
         int[] var4 = this.mCanBusInfoInt;
         StringBuilder var6 = var5.append(this.getMsbLsbResult(var4[5], var4[6])).append("/");
         var3 = this.mCanBusInfoInt;
         this.song = var6.append(this.getMsbLsbResult(var3[3], var3[4])).toString();
         var2.add(new OriginalCarDeviceUpdateEntity(0, this.songTitle));
         var2.add(new OriginalCarDeviceUpdateEntity(1, this.model));
         var2.add(new OriginalCarDeviceUpdateEntity(2, this.song));
      } else {
         this.songTitle = this.get0x38Result();
         var2.add(new OriginalCarDeviceUpdateEntity(0, this.songTitle));
         var2.add(new OriginalCarDeviceUpdateEntity(1, this.model));
         var2.add(new OriginalCarDeviceUpdateEntity(2, this.song));
      }

      return var2;
   }

   private String getPanoramaInfo1() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      if (var1 != 1) {
         return var1 != 2 ? this.mContext.getString(2131763837) : this.mContext.getString(2131763839);
      } else {
         return this.mContext.getString(2131763838);
      }
   }

   private String getPanoramaInfo2() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? this.mContext.getString(2131763841) : this.mContext.getString(2131763844);
         } else {
            return this.mContext.getString(2131763843);
         }
      } else {
         return this.mContext.getString(2131763842);
      }
   }

   private String getPanoramaInfo3() {
      return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) != 1 ? this.mContext.getString(2131763846) : this.mContext.getString(2131763847);
   }

   private String getPanoramaInfo4() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? this.mContext.getString(2131763849) : this.mContext.getString(2131763853);
            } else {
               return this.mContext.getString(2131763852);
            }
         } else {
            return this.mContext.getString(2131763851);
         }
      } else {
         return this.mContext.getString(2131763850);
      }
   }

   private String getPanoramaInfo5() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
      if (var1 != 1) {
         return var1 != 2 ? this.mContext.getString(2131763855) : this.mContext.getString(2131763857);
      } else {
         return this.mContext.getString(2131763856);
      }
   }

   private String getPanoramaInfo6() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? this.mContext.getString(2131763841) : this.mContext.getString(2131763844);
         } else {
            return this.mContext.getString(2131763843);
         }
      } else {
         return this.mContext.getString(2131763842);
      }
   }

   private int getRadarDistance(int var1) {
      if (var1 == 1) {
         return 10;
      } else if (var1 == 2) {
         return 7;
      } else if (var1 == 3) {
         return 4;
      } else {
         return var1 == 4 ? 1 : 0;
      }
   }

   private String getSlopeData() {
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1 = this.mContext.getString(2131763834);
      } else {
         var1 = this.mContext.getString(2131763833);
      }

      return var1 + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°";
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

   private int getTenData(int var1) {
      if (var1 == 16) {
         return 10;
      } else if (var1 == 17) {
         return 11;
      } else if (var1 == 18) {
         return 12;
      } else if (var1 == 19) {
         return 13;
      } else if (var1 == 20) {
         return 14;
      } else if (var1 == 21) {
         return 15;
      } else if (var1 == 22) {
         return 16;
      } else if (var1 == 23) {
         return 17;
      } else if (var1 == 24) {
         return 18;
      } else if (var1 == 25) {
         return 19;
      } else {
         return var1 == 32 ? 20 : var1;
      }
   }

   private String getTorqueState() {
      return this.mCanBusInfoInt[3] == 255 ? this.mContext.getString(2131763828) : this.mCanBusInfoInt[3] + "%";
   }

   private String getTrailer() {
      return DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]) ? this.mContext.getString(2131763832) : this.mContext.getString(2131763831);
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

   private UiMgr getUigMgr(Context var1) {
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
      ArrayList var3 = new ArrayList();
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_337_Original_equipment2", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_337_Original_equipment0", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_337_Original_equipment1", (String)null));
      SparseArray var2 = new SparseArray();
      this.mOriginalDeviceDataArray = var2;
      var2.put(0, new OriginalDeviceData(var3, new String[0]));
      this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(var1, new String[]{"prev_disc", "left", "random", "play_pause", "stop", "right", "next_disc"}));
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
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
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

   private void sei0x39CarInfo() {
      OriginalDeviceData var1 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(1);
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList();
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var2.setItems(var1.getItemList());
      var2.setRowBottomBtnAction(var1.getBottomBtns());
      Bundle var3 = new Bundle();
      var3.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var3);
   }

   private void sendRadioInfo(String var1, String var2, int var3) {
      int var4;
      int var5;
      byte var6;
      if (var2.trim().equals("FM1")) {
         var6 = 1;
         var4 = this.getLsb((int)(Double.parseDouble(var1) * 100.0));
         var5 = this.getMsb((int)(Double.parseDouble(var1) * 100.0));
         MyLog.temporaryTracking(var4 + ":" + var5);
      } else if (var2.trim().equals("FM2")) {
         var6 = 2;
         var4 = this.getLsb((int)(Double.parseDouble(var1) * 100.0));
         var5 = this.getMsb((int)(Double.parseDouble(var1) * 100.0));
      } else if (var2.trim().equals("FM3")) {
         var6 = 3;
         var4 = this.getLsb((int)(Double.parseDouble(var1) * 100.0));
         var5 = this.getMsb((int)(Double.parseDouble(var1) * 100.0));
      } else if (var2.trim().equals("AM")) {
         var6 = 16;
         var4 = this.getLsb(Integer.parseInt(var1));
         var5 = this.getMsb(Integer.parseInt(var1));
      } else if (var2.trim().equals("AM1")) {
         var6 = 17;
         var4 = this.getLsb(Integer.parseInt(var1));
         var5 = this.getMsb(Integer.parseInt(var1));
      } else if (var2.trim().equals("AM2")) {
         var6 = 18;
         var4 = this.getLsb(Integer.parseInt(var1));
         var5 = this.getMsb(Integer.parseInt(var1));
      } else if (var2.trim().equals("AM3")) {
         var6 = 19;
         var4 = this.getLsb(Integer.parseInt(var1));
         var5 = this.getMsb(Integer.parseInt(var1));
      } else {
         var6 = 0;
         var4 = this.getLsb((int)(Double.parseDouble(var1) * 100.0));
         var5 = this.getMsb((int)(Double.parseDouble(var1) * 100.0));
      }

      this.getUigMgr(this.mContext).sendRadioInfo(var6, var4, var5, var3);
   }

   private void sendSourceInfo1Line(String var1, String var2) {
      byte[] var3 = var1.getBytes(StandardCharsets.UTF_8);
      if (var2.trim().equals("FM1")) {
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 1}, var3));
      } else if (var2.trim().equals("FM2")) {
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 2}, var3));
      } else if (var2.trim().equals("FM3")) {
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 3}, var3));
      } else if (var2.trim().equals("AM1")) {
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 4}, var3));
      } else if (var2.trim().equals("AM2")) {
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 5}, var3));
      }

   }

   private void sendSourceInfo2Line(String var1) {
      byte[] var2 = var1.getBytes(StandardCharsets.UTF_8);
      this.getUigMgr(this.mContext).sendSourceInfo2Line(this.SplicingByte(new byte[]{22, -58, 3}, var2));
   }

   private void sendSourceInfo2LineOFF() {
      byte[] var1 = "OFF".getBytes(StandardCharsets.UTF_8);
      this.getUigMgr(this.mContext).sendSourceInfo2Line(this.SplicingByte(new byte[]{22, -58, 0}, var1));
   }

   private void sendSourceInfo2LineUnknown() {
      byte[] var1 = "unknown".getBytes(StandardCharsets.UTF_8);
      this.getUigMgr(this.mContext).sendSourceInfo2Line(this.SplicingByte(new byte[]{22, -58, 3}, var1));
   }

   private void set0x03FConfigInfo() {
      ArrayList var1 = new ArrayList();
      if (!this.is404("_337_car_config", "_337_car_config0")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_config"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_config", "_337_car_config0"), this.getAmplifierState())).setValueStr(true));
      }

      if (!this.is404("_337_car_config", "_337_car_config1")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_config"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_config", "_337_car_config1"), this.getCdState())).setValueStr(true));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x21WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 7) {
                  switch (var1) {
                     case 9:
                        this.buttonKey(14);
                        break;
                     case 10:
                        this.buttonKey(15);
                        break;
                     case 11:
                        this.buttonKey(45);
                        break;
                     case 12:
                        this.buttonKey(46);
                        break;
                     case 13:
                        this.buttonKey(187);
                        break;
                     case 14:
                        this.buttonKey(3);
                  }
               } else {
                  this.buttonKey(2);
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

   private void set0x23AirInfo() {
      ArrayList var2 = new ArrayList();
      if (!this.is404("_337_air", "_337_air_1")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_1"), ""));
      }

      if (!this.is404("_337_air", "_337_air_2")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_2"), ""));
      }

      if (!this.is404("_337_air", "_337_air_3")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_3"), ""));
      }

      if (!this.is404("_337_air", "_337_air_4")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_4"), ""));
      }

      if (!this.is404("_337_air", "_337_air_5")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_5"), ""));
      }

      if (!this.is404("_337_air", "_337_air_6")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_6"), ""));
      }

      if (!this.is404("_337_air", "_337_air_7")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_7"), ""));
      }

      if (!this.is404("_337_air", "_337_air_8")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_8"), ""));
      }

      if (!this.is404("_337_air", "_337_air_9")) {
         var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_9"), ""));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      if (this.mCanBusInfoInt[7] != 255) {
         this.updateOutDoorTemp(this.mContext, (double)this.mCanBusInfoInt[7] * 0.5 - 40.0 + this.getTempUnitC(this.mContext));
      }

      this.mCanBusInfoInt[7] = 0;
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         switch (this.mCanBusInfoInt[3]) {
            case 1:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_foot = false;
               break;
            case 2:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 3:
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 4:
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 5:
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_foot = false;
               break;
            case 6:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_foot = false;
               break;
            case 7:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         int var1 = this.mCanBusInfoInt[5];
         if (var1 == 0) {
            GeneralAirData.front_right_temperature = "LOW";
         } else if (var1 == 255) {
            GeneralAirData.front_right_temperature = "HI";
         } else if (var1 < 112) {
            GeneralAirData.front_left_temperature = this.mCanBusInfoInt[5] + this.mContext.getString(2131763755);
         } else {
            GeneralAirData.front_left_temperature = (double)(this.mCanBusInfoInt[5] - 112) * 0.5 + 16.0 + this.getTempUnitC(this.mContext);
         }

         var1 = this.mCanBusInfoInt[6];
         if (var1 == 0) {
            GeneralAirData.front_right_temperature = "LOW";
         } else if (var1 == 255) {
            GeneralAirData.front_right_temperature = "HI";
         } else if (var1 < 112) {
            GeneralAirData.front_right_temperature = this.mCanBusInfoInt[6] + this.mContext.getString(2131763755);
         } else {
            GeneralAirData.front_right_temperature = (double)(this.mCanBusInfoInt[6] - 112) * 0.5 + 16.0 + this.getTempUnitC(this.mContext);
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x26RearRadarInfo() {
      if (!this.isRearRadarDataChange()) {
         if (this.eachId == 3) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, this.getRadarDistance(this.mCanBusInfoInt[2]), this.getRadarDistance(this.mCanBusInfoInt[3]), this.getRadarDistance(this.mCanBusInfoInt[4]), this.getRadarDistance(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            RadarInfoUtil.mMinIsClose = true;
            int[] var1 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.updateParkUi((Bundle)null, this.mContext);
         }

      }
   }

   private void set0x27FrontRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x28BasicInfo() {
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isShowHandBrake = true;
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x29CarInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 0, this.df_2Decimal.format((double)this.mCanBusInfoInt[2] * 0.75 - 48.0) + this.getTempUnitC(this.mContext)));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 1, this.df_2Decimal.format((long)(this.mCanBusInfoInt[3] - 40)) + this.getTempUnitC(this.mContext)));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 2, this.df_2Decimal.format((double)this.mCanBusInfoInt[4] * 0.25) + "V"));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 3, this.df_2Decimal.format((double)this.mCanBusInfoInt[5] * 0.59) + "Kpa"));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 4, this.mCanBusInfoInt[6] + "%"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x30EspInfo() {
      if (this.isTrackInfoChange()) {
         int var1 = this.eachId;
         byte[] var2;
         if (var1 != 4 && var1 != 6 && var1 != 8 && var1 != 9 && var1 != 16) {
            var2 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 0, 5500, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
               var2 = this.mCanBusInfoByte;
               GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 0, 9994, 16);
            } else {
               var2 = this.mCanBusInfoByte;
               GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 0, 9994, 16);
            }

            this.updateParkUi((Bundle)null, this.mContext);
         }
      }

   }

   private void set0x31SettingInfo() {
      int var1 = this.mCanBusInfoInt.length;
      ArrayList var2 = new ArrayList();
      if (var1 < 3) {
         this.updateSetting(var2);
      } else {
         if (!this.is404("_337_car_setting", "_337_setting_0")) {
            var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 1));
         }

         if (!this.is404("_337_car_setting", "_337_setting_1")) {
            var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 1));
         }

         if (var1 < 4) {
            this.updateSetting(var2);
         } else {
            if (!this.is404("_337_car_setting", "_337_setting_2")) {
               var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 1));
            }

            if (!this.is404("_337_car_setting", "_337_setting_3")) {
               var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 4));
            }

            if (!this.is404("_337_car_setting", "_337_setting_4")) {
               var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
            }

            if (!this.is404("_337_car_setting", "_337_setting_5")) {
               var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
            }

            if (!this.is404("_337_car_setting", "_337_setting_6")) {
               var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            }

            if (!this.is404("_337_car_setting", "_337_setting_7")) {
               var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            }

            if (var1 < 5) {
               this.updateSetting(var2);
            } else {
               if (!this.is404("_337_car_setting", "_337_setting_8")) {
                  var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
               }

               if (!this.is404("_337_car_setting", "_337_setting_9")) {
                  var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_9"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
               }

               if (!this.is404("_337_car_setting", "_337_setting_10")) {
                  var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_10"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
               }

               if (!this.is404("_337_car_setting", "_337_setting_11")) {
                  var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_11"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
               }

               if (!this.is404("_337_car_setting", "_337_setting_12")) {
                  var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_12"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
               }

               if (!this.is404("_337_car_setting", "_337_setting_13")) {
                  var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_13"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
               }

               if (!this.is404("_337_car_setting", "_337_setting_14")) {
                  var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_14"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
               }

               if (var1 < 6) {
                  this.updateSetting(var2);
               } else {
                  if (!this.is404("_337_car_setting", "_337_setting_15")) {
                     var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_15"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
                  }

                  if (!this.is404("_337_car_setting", "_337_setting_16")) {
                     var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_16"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
                  }

                  if (!this.is404("_337_car_setting", "_337_setting_17")) {
                     var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_17"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
                  }

                  if (!this.is404("_337_car_setting", "_337_setting_18")) {
                     var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_18"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
                  }

                  if (!this.is404("_337_car_setting", "_337_setting_19")) {
                     var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_19"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
                  }

                  if (!this.is404("_337_car_setting", "_337_setting_20")) {
                     var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_20"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
                  }

                  if (var1 < 7) {
                     this.updateSetting(var2);
                  } else {
                     if (!this.is404("_337_car_setting", "_337_setting_21")) {
                        var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_21"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
                     }

                     if (!this.is404("_337_car_setting", "_337_setting_22")) {
                        var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_22"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
                     }

                     if (!this.is404("_337_car_setting", "_337_setting_23")) {
                        var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_23"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2)));
                     }

                     if (!this.is404("_337_car_setting", "_337_setting_24")) {
                        var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_24"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)));
                     }

                     if (!this.is404("_337_car_setting", "_337_setting_25")) {
                        var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_25"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)));
                     }

                     if (!this.is404("_337_car_setting", "_337_setting_26")) {
                        var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_26"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)));
                     }

                     if (!this.is404("_337_car_setting", "_337_setting_26_on_27")) {
                        var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_26_on_27"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
                     }

                     if (var1 < 8) {
                        this.updateSetting(var2);
                     } else {
                        if (!this.is404("_337_car_setting", "_337_setting_27")) {
                           var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_27"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
                        }

                        if (!this.is404("_337_car_setting", "_337_setting_28")) {
                           var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_28"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1)));
                        }

                        if (!this.is404("_337_car_setting", "_337_setting_29")) {
                           var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_29"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1)));
                        }

                        if (!this.is404("_337_car_setting", "_337_setting_30")) {
                           var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_30"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)));
                        }

                        if (!this.is404("_337_car_setting", "_337_setting_31")) {
                           var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_31"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)));
                        }

                        if (!this.is404("_337_car_setting", "_337_setting_32")) {
                           var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_32"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3)));
                        }

                        if (var1 < 9) {
                           this.updateSetting(var2);
                        } else {
                           if (!this.is404("_337_car_setting", "_337_setting_33")) {
                              var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_33"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
                           }

                           if (!this.is404("_337_car_setting", "_337_setting_34")) {
                              var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_34"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)));
                           }

                           if (!this.is404("_337_car_setting", "_337_setting_35")) {
                              var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_35"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1)));
                           }

                           if (!this.is404("_337_car_setting", "_337_setting_36")) {
                              var2.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_36"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 5) - 10 + "")).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 5)));
                           }

                           if (var1 < 10) {
                              this.updateSetting(var2);
                           } else {
                              if (!this.is404("_337_car_setting", "_337_setting_37")) {
                                 var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_37"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)));
                              }

                              if (!this.is404("_337_car_setting", "_337_setting_38")) {
                                 var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_38"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1)));
                              }

                              if (!this.is404("_337_car_setting", "_337_setting_39")) {
                                 var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_39"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1)));
                              }

                              if (!this.is404("_337_car_setting", "_337_setting_40")) {
                                 var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_40"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1)));
                              }

                              if (!this.is404("_337_car_setting", "_337_setting_41")) {
                                 var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_41"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2)));
                              }

                              if (!this.is404("_337_car_setting", "_337_setting_42")) {
                                 var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_42"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2)));
                              }

                              if (var1 < 11) {
                                 this.updateSetting(var2);
                              } else {
                                 if (!this.is404("_337_car_setting", "_337_setting_43")) {
                                    var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_43"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1)));
                                 }

                                 if (!this.is404("_337_car_setting", "_337_setting_44")) {
                                    var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_44"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1)));
                                 }

                                 if (!this.is404("_337_car_setting", "_337_setting_45")) {
                                    var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_45"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2)));
                                 }

                                 if (!this.is404("_337_car_setting", "_337_setting_46")) {
                                    var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_46"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2)));
                                 }

                                 if (!this.is404("_337_car_setting", "_337_setting_47")) {
                                    var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_47"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1)));
                                 }

                                 if (var1 < 12) {
                                    this.updateSetting(var2);
                                 } else {
                                    if (!this.is404("_337_car_setting", "_337_setting_48")) {
                                       var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_48"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 1)));
                                    }

                                    if (!this.is404("_337_car_setting", "_337_setting_49")) {
                                       var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_49"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 1)));
                                    }

                                    if (!this.is404("_337_car_setting", "_337_setting_50")) {
                                       var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_50"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 1)));
                                    }

                                    if (!this.is404("_337_car_setting", "_337_setting_51")) {
                                       var2.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_51"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4)));
                                    }

                                    this.updateGeneralSettingData(var2);
                                    this.updateSettingActivity((Bundle)null);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void set0x32ScreenBrightness() {
      int var1;
      if (this.getUigMgr(this.mContext).isH9()) {
         var1 = this.mCanBusInfoInt[2];
         if (var1 >= 15 && var1 < 19) {
            FutureUtil.instance.setBrightness(1);
         } else if (var1 >= 19 && var1 < 38) {
            FutureUtil.instance.setBrightness(2);
         } else if (var1 >= 38 && var1 < 57) {
            FutureUtil.instance.setBrightness(3);
         } else if (var1 >= 57 && var1 < 76) {
            FutureUtil.instance.setBrightness(4);
         } else if (var1 >= 76 && var1 < 95) {
            FutureUtil.instance.setBrightness(5);
         }
      }

      if (this.getUigMgr(this.mContext).isCarModel3() || this.getUigMgr(this.mContext).isGreatWall() || this.getUigMgr(this.mContext).isH7()) {
         var1 = this.mCanBusInfoInt[2];
         if (var1 >= 0 && var1 < 51) {
            FutureUtil.instance.setBrightness(1);
         } else if (var1 >= 51 && var1 < 102) {
            FutureUtil.instance.setBrightness(2);
         } else if (var1 >= 102 && var1 < 153) {
            FutureUtil.instance.setBrightness(3);
         } else if (var1 >= 153 && var1 < 204) {
            FutureUtil.instance.setBrightness(4);
         } else if (var1 >= 204 && var1 < 255) {
            FutureUtil.instance.setBrightness(5);
         }
      }

   }

   private void set0x33IDriveInfo() {
      int var1 = this.nowValue;
      if (var1 == -1) {
         this.nowValue = this.mCanBusInfoInt[2];
      } else {
         int[] var3 = this.mCanBusInfoInt;
         int[] var4 = new int[var3.length + 1];
         var4[0] = var3[0];
         var4[1] = var3[1];
         int var2 = var3[2];
         var4[2] = var2;
         var4[3] = 1;
         if (var1 > var2) {
            var4[3] = 1;
            this.realKeyLongClick1(this.mContext, 8, 1);
            var4[3] = 0;
            this.realKeyLongClick1(this.mContext, 0, 0);
         } else if (var1 < var2) {
            var4[3] = 1;
            this.realKeyLongClick1(this.mContext, 7, 1);
            var4[3] = 0;
            this.realKeyLongClick1(this.mContext, 0, 0);
         }

         this.nowValue = var4[2];
      }
   }

   private void set0x34RearAirInfo() {
      if (this.getUigMgr(this.mContext).isH9()) {
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_dual = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         int var1 = this.mCanBusInfoInt[3];
         if (var1 == 1) {
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_right_blow_head = true;
         } else if (var1 == 2) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_right_blow_head = true;
         } else if (var1 == 3) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_right_blow_head = false;
         }

         GeneralAirData.rear_wind_level = this.mCanBusInfoInt[4];
         var1 = this.mCanBusInfoInt[5];
         if (var1 == 0) {
            GeneralAirData.rear_temperature = "LOW";
         } else if (var1 == 255) {
            GeneralAirData.rear_temperature = "HIGH";
         } else {
            GeneralAirData.rear_temperature = this.df_2Decimal.format((double)(this.mCanBusInfoInt[5] - 116) * 0.5 + 18.0) + this.getTempUnitC(this.mContext);
         }

         this.updateAirActivity(this.mContext, 1002);
      }
   }

   private void set0x35SeatInfo() {
      ArrayList var1 = new ArrayList();
      if (!this.is404("_337_air", "_337_air_2")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_3")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_4")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_5")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_6")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_7")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_8")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_9")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_9"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      if (!this.is404("_337_air", "_337_air_12")) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_12"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + this.mContext.getString(2131763970))).setValueStr(true));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x36CarInfo() {
      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 0, this.getSlopeData()));
      var3.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 1, this.getTorqueState()));
      var3.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 2, this.getDipAngle()));
      var3.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 3, this.getTrailer()));
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow");
      StringBuilder var2 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, 4, var2.append(this.getMsbLsbResult(var4[6], var4[7])).append("").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x37AmplifierInfo() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.bandTreble = this.getTenData(this.mCanBusInfoInt[3]);
      GeneralAmplifierData.bandMiddle = this.getTenData(this.mCanBusInfoInt[4]);
      GeneralAmplifierData.bandBass = this.getTenData(this.mCanBusInfoInt[5]);
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[6] - 10;
      GeneralAmplifierData.frontRear = -this.mCanBusInfoInt[7] + 10;
      this.updateAmplifierActivity(new Bundle());
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2) - 1));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info4"), this.mCanBusInfoInt[9]));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info5"), this.getAmplifierVoltage())).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info6"), this.getAmplifierTemperature())).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x38CDInfo() {
      OriginalDeviceData var4;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2) == 0) {
         var4 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(0);
      } else {
         var4 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(1);
      }

      GeneralOriginalCarDeviceData.mList = null;
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  GeneralOriginalCarDeviceData.cdStatus = "MISC";
                  GeneralOriginalCarDeviceData.runningState = this.getJobState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
                  GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList();
               }
            } else {
               GeneralOriginalCarDeviceData.cdStatus = "AUX";
               GeneralOriginalCarDeviceData.runningState = this.getJobState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 42));
               GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList();
            }
         } else {
            GeneralOriginalCarDeviceData.cdStatus = "CD DISC";
            GeneralOriginalCarDeviceData.runningState = this.getJobState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
            GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList();
         }
      } else {
         GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
         GeneralOriginalCarDeviceData.runningState = "OFF";
      }

      OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var5.setItems(var4.getItemList());
      var5.setRowBottomBtnAction(var4.getBottomBtns());
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2) == 0) {
         var5.setHavePlayTimeSeekBar(false);
      } else {
         int[] var6 = this.mCanBusInfoInt;
         int var2 = this.getMsbLsbResult(var6[7], var6[8]);
         var6 = this.mCanBusInfoInt;
         var1 = this.getMsbLsbResult(var6[9], var6[10]);
         var5.setHavePlayTimeSeekBar(true);
         StringBuilder var7 = new StringBuilder();
         DecimalFormat var8 = this.df_2Integer;
         int var3 = var1 / 60;
         GeneralOriginalCarDeviceData.startTime = var7.append(var8.format((long)(var3 / 60))).append(":").append(this.df_2Integer.format((long)(var3 % 60))).append(":").append(this.df_2Integer.format((long)(var1 % 60))).toString();
         StringBuilder var10 = new StringBuilder();
         DecimalFormat var9 = this.df_2Integer;
         var3 = var2 / 60;
         GeneralOriginalCarDeviceData.endTime = var10.append(var9.format((long)(var3 / 60))).append(":").append(this.df_2Integer.format((long)(var3 % 60))).append(":").append(this.df_2Integer.format((long)(var2 % 60))).toString();
         if (var1 == 0) {
            GeneralOriginalCarDeviceData.progress = 0;
         } else {
            GeneralOriginalCarDeviceData.progress = var1 * 100 / var2;
         }
      }

      Bundle var11 = new Bundle();
      var11.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var11);
   }

   private void set0x42PanoramaInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 0, this.getPanoramaInfo1()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 1, this.getPanoramaInfo2()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 2, this.getPanoramaInfo3()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 3, this.getPanoramaInfo4()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 4, this.getPanoramaInfo5()));
      var1.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 5, this.getPanoramaInfo6()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x43AlertInfo() {
      this.startMainActivity(this.mContext);
      if (!this.getAlertContent().equals(this.mContext.getString(2131763756))) {
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               (new SetAlertView()).showDialog(this.this$0.getActivity(), this.this$0.getAlertContent());
            }
         });
      }
   }

   private void set0x7FVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set360PanoramaInfo() {
      if (this.isPanoramicInfoChange()) {
         ArrayList var2 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         if (var1 == 0) {
            var2.add(new PanoramicBtnUpdateEntity(0, false));
            var2.add(new PanoramicBtnUpdateEntity(1, false));
         } else if (var1 == 1) {
            var2.add(new PanoramicBtnUpdateEntity(0, true));
            var2.add(new PanoramicBtnUpdateEntity(1, false));
         }

         GeneralParkData.dataList = var2;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setOx22PanelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 7) {
               if (var1 != 9) {
                  if (var1 != 41) {
                     if (var1 != 33) {
                        if (var1 != 34) {
                           switch (var1) {
                              case 48:
                                 this.buttonKey(20);
                                 break;
                              case 49:
                                 this.buttonKey(2);
                                 break;
                              case 50:
                                 this.buttonKey(151);
                                 break;
                              case 51:
                                 this.buttonKey(14);
                                 break;
                              case 52:
                                 this.buttonKey(15);
                                 break;
                              case 53:
                                 this.buttonKey(58);
                                 break;
                              case 54:
                                 this.buttonKey(128);
                                 break;
                              case 55:
                                 if (this.getUigMgr(this.mContext).isPortrait()) {
                                    this.getUigMgr(this.mContext).sendPortraitAirInfo(10);
                                    return;
                                 }

                                 this.getUigMgr(this.mContext).sendAirInfo(28);
                                 break;
                              case 56:
                                 if (this.getUigMgr(this.mContext).isPortrait()) {
                                    this.getUigMgr(this.mContext).sendPortraitAirInfo(9);
                                    return;
                                 }

                                 this.getUigMgr(this.mContext).sendAirInfo(29);
                                 break;
                              case 57:
                                 if (this.getUigMgr(this.mContext).isPortrait()) {
                                    this.getUigMgr(this.mContext);
                                    if (UiMgr.seatStatePortrait == 0) {
                                       this.getUigMgr(this.mContext).sendPortraitAirInfo(7);
                                       this.getUigMgr(this.mContext);
                                       UiMgr.seatStatePortrait = 1;
                                    } else {
                                       this.getUigMgr(this.mContext);
                                       if (UiMgr.seatStatePortrait == 1) {
                                          this.getUigMgr(this.mContext).sendPortraitAirInfo(8);
                                          this.getUigMgr(this.mContext);
                                          UiMgr.seatStatePortrait = 2;
                                       } else {
                                          this.getUigMgr(this.mContext);
                                          if (UiMgr.seatStatePortrait == 2) {
                                             this.getUigMgr(this.mContext).sendPortraitAirInfo(32);
                                             this.getUigMgr(this.mContext);
                                             UiMgr.seatStatePortrait = 3;
                                          } else {
                                             this.getUigMgr(this.mContext);
                                             if (UiMgr.seatStatePortrait == 3) {
                                                this.getUigMgr(this.mContext).sendPortraitAirInfo(33);
                                                this.getUigMgr(this.mContext);
                                                UiMgr.seatStatePortrait = 4;
                                             }
                                          }
                                       }
                                    }

                                    return;
                                 }

                                 this.getUigMgr(this.mContext);
                                 if (UiMgr.seatState == 0) {
                                    this.getUigMgr(this.mContext).sendAirInfo(21);
                                    this.getUigMgr(this.mContext);
                                    UiMgr.seatState = 1;
                                 } else {
                                    this.getUigMgr(this.mContext);
                                    if (UiMgr.seatState == 1) {
                                       this.getUigMgr(this.mContext).sendAirInfo(24);
                                       this.getUigMgr(this.mContext);
                                       UiMgr.seatState = 2;
                                    } else {
                                       this.getUigMgr(this.mContext);
                                       if (UiMgr.seatState == 2) {
                                          this.getUigMgr(this.mContext).sendAirInfo(25);
                                          this.getUigMgr(this.mContext);
                                          UiMgr.seatState = 3;
                                       } else {
                                          this.getUigMgr(this.mContext);
                                          if (UiMgr.seatState == 3) {
                                             this.getUigMgr(this.mContext).sendAirInfo(26);
                                             this.getUigMgr(this.mContext);
                                             UiMgr.seatState = 4;
                                          } else {
                                             this.getUigMgr(this.mContext);
                                             if (UiMgr.seatState == 4) {
                                                this.getUigMgr(this.mContext).sendAirInfo(27);
                                                this.getUigMgr(this.mContext);
                                                UiMgr.seatState = 0;
                                             }
                                          }
                                       }
                                    }
                                 }
                                 break;
                              case 58:
                                 this.buttonKey(50);
                                 break;
                              case 59:
                                 this.buttonKey(27);
                                 break;
                              case 60:
                                 this.buttonKey(4);
                                 break;
                              default:
                                 switch (var1) {
                                    case 64:
                                       this.buttonKey(45);
                                       break;
                                    case 65:
                                       this.buttonKey(46);
                                       break;
                                    case 66:
                                       this.buttonKey(47);
                                       break;
                                    case 67:
                                       this.buttonKey(48);
                                       break;
                                    case 68:
                                       this.buttonKey(49);
                                       break;
                                    case 69:
                                       this.buttonKey(45);
                                       break;
                                    case 70:
                                       this.buttonKey(45);
                                       break;
                                    case 71:
                                       this.buttonKey(46);
                                       break;
                                    case 72:
                                       this.buttonKey(46);
                                       break;
                                    case 73:
                                       this.buttonKey(2);
                                 }
                           }
                        } else {
                           this.buttonKey(8);
                        }
                     } else {
                        this.buttonKey(7);
                     }
                  } else {
                     this.buttonKey(21);
                  }
               } else {
                  this.buttonKey(3);
               }
            } else {
               this.buttonKey(129);
            }
         } else {
            this.buttonKey(134);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void setRightCameraInfo() {
      if (this.isPanoramicInfoChange()) {
         ArrayList var2 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         if (var1 == 0) {
            var2.add(new PanoramicBtnUpdateEntity(0, false));
            var2.add(new PanoramicBtnUpdateEntity(1, false));
         } else if (var1 == 1) {
            var2.add(new PanoramicBtnUpdateEntity(0, false));
            var2.add(new PanoramicBtnUpdateEntity(1, true));
         }

         GeneralParkData.dataList = var2;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void updateSetting(List var1) {
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void atvDestdroy() {
      super.atvDestdroy();
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(0, 33);
         byte[] var1 = "ATV OFF".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 0}, var1));
         this.sendSourceInfo2LineOFF();
      }
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(3, 34);
         this.getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
         byte[] var1 = "ATV Playing".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 12}, var1));
         this.sendSourceInfo2LineUnknown();
      }
   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(0, 48);
         byte[] var1 = "Aux OFF".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 0}, var1));
         this.sendSourceInfo2LineOFF();
      }
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(7, 48);
         this.getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
         byte[] var1 = "Aux Playing".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 8}, var1));
         this.sendSourceInfo2LineUnknown();
      }
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(11, 48);
         this.getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
         byte[] var1 = "A2DP Playing".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 11}, var1));
         this.sendSourceInfo2LineUnknown();
      }
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         UiMgr var5 = this.getUigMgr(this.mContext);
         int var4 = 0;
         var5.sendSourceCmd(0, 64);

         String var6;
         for(var6 = ""; var4 < var1.length; ++var4) {
            var6 = var6 + (var1[var4] - 48);
         }

         var1 = ("Hang Up" + var6).getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 12}, var1));
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
            final MsgMgr this$0;
            final byte[] val$mSongTitleByte;

            {
               this.this$0 = var1;
               this.val$mSongTitleByte = var2;
            }

            public void callback() {
               (new CountDownTimer(this, 3000L, 1000L) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void onFinish() {
                     UiMgr var3 = this.this$1.this$0.getUigMgr(this.this$1.this$0.mContext);
                     MsgMgr var1 = this.this$1.this$0;
                     byte[] var2 = this.this$1.val$mSongTitleByte;
                     var3.sendPhoneInfo(var1.SplicingByte(new byte[]{22, -59, 6, 1}, var2));
                  }

                  public void onTick(long var1) {
                     UiMgr var4 = this.this$1.this$0.getUigMgr(this.this$1.this$0.mContext);
                     MsgMgr var5 = this.this$1.this$0;
                     byte[] var3 = this.this$1.val$mSongTitleByte;
                     var4.sendPhoneInfo(var5.SplicingByte(new byte[]{22, -59, 6, 1}, var3));
                  }
               }).start();
            }
         });
      }
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(5, 64);
         int var4 = 0;

         String var5;
         for(var5 = ""; var4 < var1.length; ++var4) {
            var5 = var5 + (var1[var4] - 48);
         }

         var1 = ("Incoming" + var5).getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 12}, var1));
         this.getUigMgr(this.mContext).sendPhoneInfo(this.SplicingByte(new byte[]{22, -59, 1, 1}, var1));
      }
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(5, 64);
         int var4 = 0;

         String var5;
         for(var5 = ""; var4 < var1.length; ++var4) {
            var5 = var5 + (var1[var4] - 48);
         }

         var1 = ("Dial" + var5).getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 12}, var1));
         this.getUigMgr(this.mContext).sendPhoneInfo(this.SplicingByte(new byte[]{22, -59, 2, 1}, var1));
      }
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(5, 64);
         this.getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, var4 / 60, var4 % 60);
         var4 = 0;

         String var5;
         for(var5 = ""; var4 < var1.length; ++var4) {
            var5 = var5 + (var1[var4] - 48);
         }

         var1 = ("Talking With" + var5).getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 12}, var1));
         this.getUigMgr(this.mContext).sendPhoneInfo(this.SplicingByte(new byte[]{22, -59, 4, 1}, var1));
      }
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
      if (var3 != 127) {
         switch (var3) {
            case 33:
               this.set0x21WheelKeyInfo();
               break;
            case 34:
               this.setOx22PanelKeyInfo();
               break;
            case 35:
               this.set0x23AirInfo();
               break;
            default:
               switch (var3) {
                  case 38:
                     this.set0x26RearRadarInfo();
                     break;
                  case 39:
                     this.set0x27FrontRadarInfo();
                     break;
                  case 40:
                     this.set0x28BasicInfo();
                     break;
                  case 41:
                     this.set0x29CarInfo();
                     break;
                  default:
                     switch (var3) {
                        case 48:
                           this.set0x30EspInfo();
                           break;
                        case 49:
                           this.set0x31SettingInfo();
                           break;
                        case 50:
                           this.set0x32ScreenBrightness();
                           break;
                        case 51:
                           this.set0x33IDriveInfo();
                           break;
                        case 52:
                           this.set0x34RearAirInfo();
                           break;
                        case 53:
                           this.set0x35SeatInfo();
                           break;
                        case 54:
                           this.set0x36CarInfo();
                           break;
                        case 55:
                           this.set0x37AmplifierInfo();
                           break;
                        case 56:
                           this.set0x38CDInfo();
                           break;
                        case 57:
                           this.sei0x39CarInfo();
                           break;
                        default:
                           switch (var3) {
                              case 63:
                                 this.set0x03FConfigInfo();
                                 break;
                              case 64:
                                 this.setRightCameraInfo();
                                 break;
                              case 65:
                                 this.set360PanoramaInfo();
                                 break;
                              case 66:
                                 this.set0x42PanoramaInfo();
                                 break;
                              case 67:
                                 this.set0x43AlertInfo();
                           }
                     }
               }
         }
      } else {
         this.set0x7FVersionInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUigMgr(this.mContext).send0x83CarSettingInfo(3, var5, var6);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(2, 16);
         this.getUigMgr(this.mContext).sendSourceInfo3Line(var5, var3, 0, 0, var2 / 60, var2 % 60);
         byte[] var14 = var11.getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 6}, var14));
         this.sendSourceInfo2Line(var13);
      }
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(3, 33);
         this.getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
         byte[] var1 = "DTV Playing".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 12}, var1));
         this.sendSourceInfo2LineUnknown();
      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUigMgr(this.mContext).makeConnection();
      this.getUigMgr(this.mContext).selectCarModel();
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalCarDevice();
   }

   public boolean is404(String var1, String var2) {
      return this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         byte[] var25;
         if (var1 == 9) {
            this.getUigMgr(this.mContext).sendSourceCmd(9, 16);
            var25 = var21.getBytes(StandardCharsets.UTF_8);
            this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 10}, var25));
         } else {
            this.getUigMgr(this.mContext).sendSourceCmd(8, 16);
            var25 = var21.getBytes(StandardCharsets.UTF_8);
            this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 9}, var25));
         }

         UiMgr var26 = this.getUigMgr(this.mContext);
         var17 = (int)var19 / 1000;
         var26.sendSourceInfo3Line(var4, var3, var17 / 60, var17 % 60, var6, var7);
         this.sendSourceInfo2Line(var23);
      }
   }

   public void radioDestroy() {
      super.radioDestroy();
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(0, 1);
         byte[] var1 = "Radio OFF".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 0}, var1));
         this.sendSourceInfo2LineOFF();
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(1, 1);
         this.getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
         this.sendSourceInfo1Line(var3, var2);
         this.sendSourceInfo2LineUnknown();
         this.sendRadioInfo(var3, var2, var1);
      }
   }

   public void showDialogAndRestartApp(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$content;

         {
            this.this$0 = var1;
            this.val$content = var2;
         }

         public void callback() {
            (new SetAlertView()).showDialog(this.this$0.getActivity(), this.val$content);
            (new CountDownTimer(this, 3000L, 1000L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  System.exit(0);
               }

               public void onTick(long var1) {
               }
            }).start();
         }
      });
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         this.getUigMgr(this.mContext).sendSourceCmd(0, 16);
         byte[] var2 = " switchiSourceng...".getBytes(StandardCharsets.UTF_8);
         this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 0}, var2));
         this.sendSourceInfo2LineOFF();
      }
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (this.getUigMgr(this.mContext).isCarModel8()) {
         byte[] var18;
         if (var1 == 9) {
            this.getUigMgr(this.mContext).sendSourceCmd(9, 17);
            var18 = "Video Playing".getBytes(StandardCharsets.UTF_8);
            this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 10}, var18));
         } else {
            this.getUigMgr(this.mContext).sendSourceCmd(8, 17);
            var18 = "Video Playing".getBytes(StandardCharsets.UTF_8);
            this.getUigMgr(this.mContext).sendSourceInfo1Line(this.SplicingByte(new byte[]{22, -60, 9}, var18));
         }

         this.getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, var3, 0, var6, var7);
         this.sendSourceInfo2LineUnknown();
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
