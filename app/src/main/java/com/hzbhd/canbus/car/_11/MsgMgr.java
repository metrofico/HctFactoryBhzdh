package com.hzbhd.canbus.car._11;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
   private static final int SEND_NORMAL_MESSAGE = 2;
   static final int SHARE_11_AMPLIFIER_BAND_OFFSET = 2;
   static final int SHARE_11_AMPLIFIER_FADE_OFFSET = 7;
   static final String SHARE_11_IS_HAVE_SPARE_TIRE = "share_11_is_have_spare_tire";
   static final String SHARE_11_IS_SUPPOT_HYBRID = "share_11_is_suppot_hybrid";
   static final String SHARE_11_IS_SUPPOT_TIRE = "share_11_is_suppot_tire";
   private byte freqHi;
   private byte freqLo;
   int frontRadarStatus;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private int mDifferent;
   private int[] mFrontRadarDataNow;
   private boolean mFrontStatus;
   private Handler mHandler;
   private HandlerThread mHandlerThread;
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private ID3[] mMusicId3s;
   private boolean mPanoramicStatusNow;
   private int[] mRearRadarDataNow;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private int mSkyWindowStatus;
   private UiMgr mUiMgr;
   private String mUsbSongAlbumNow;
   private String mUsbSongArtistNow;
   private String mUsbSongTitleNow;
   private byte[] mVersionInfoNow;
   int nowAmplifierModel = 1;

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var6 = new byte[var4];
      byte[] var5;
      if (var2 == var4) {
         var5 = Arrays.copyOf(var1, var4);
      } else {
         int var3 = 0;

         while(true) {
            var5 = var6;
            if (var3 >= var4) {
               break;
            }

            if (var3 < var2) {
               var6[var3] = var1[var3];
            } else {
               var6[var3] = var1[var3 + 1];
            }

            ++var3;
         }
      }

      return var5;
   }

   private int getAirWhat() {
      int[] var5 = new int[6];
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      short var1 = 0;
      var5[0] = var2;
      var5[1] = var3[3] & 239;
      var5[2] = var3[4];
      var5[3] = var3[5];
      var5[4] = var3[6] & 241;
      var5[5] = var3[7];
      int[] var4 = new int[]{var3[8], var3[9]};
      if (!Arrays.equals(this.mAirFrontDataNow, var5)) {
         var1 = 1001;
      } else if (!Arrays.equals(this.mAirRearDataNow, var4)) {
         var1 = 1002;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var5, 6);
      this.mAirRearDataNow = Arrays.copyOf(var4, 2);
      return var1;
   }

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

   private int getDriveData(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private String getDriveData(int var1, int var2, String var3) {
      var1 = var1 * 256 + var2;
      return var1 == 65535 ? " " : var1 + var3;
   }

   private String getDriveTime() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = this.getDriveData(var3[8], var3[9]);
      int var2 = var1 / 60;
      return (new DecimalFormat("00")).format((long)var2) + ":" + (new DecimalFormat("00")).format((long)(var1 % 60));
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private String getFuelUnit(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = " MPG(US)";
      } else if (var1 == 1) {
         var2 = " KM/L";
      } else if (var1 == 2) {
         var2 = " L/100KM";
      } else if (var1 == 3) {
         var2 = " MPG(UK)";
      } else {
         var2 = "";
      }

      return var2;
   }

   private String getMileageUnit(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = " MILE";
      } else if (var1 == 2) {
         var2 = " KM";
      } else {
         var2 = "";
      }

      return var2;
   }

   private float getTireRule(int var1) {
      if (var1 != 0) {
         return var1 != 2 ? 1.0F : 0.4F;
      } else {
         return 10.0F;
      }
   }

   private String getTireUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : " KPA";
         } else {
            return " PSI";
         }
      } else {
         return " BAR";
      }
   }

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData(Context var1) {
      ArrayList var3 = new ArrayList();
      int var2 = 0;
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
         var3.add(new byte[]{22, -125, 36, (byte)SharePreUtil.getIntValue(var1, "share_11_language", 0)});
      }

      var3.add(new byte[]{22, -127, 1, 1});
      var3.add(new byte[]{22, -124, 8, 1});
      var3.add(new byte[]{22, -124, 1, (byte)(GeneralAmplifierData.frontRear + 7)});
      var3.add(new byte[]{22, -124, 2, (byte)(GeneralAmplifierData.leftRight + 7)});
      var3.add(new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.bandBass + 2)});
      var3.add(new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandTreble + 2)});
      var3.add(new byte[]{22, -124, 6, (byte)(GeneralAmplifierData.bandMiddle + 2)});
      var3.add(new byte[]{22, -124, 7, (byte)GeneralAmplifierData.volume});

      while(var2 < var3.size()) {
         this.sendNormalMessage(var3.get(var2), (long)(var2 * 80));
         ++var2;
      }

   }

   private void initHandler(Context var1) {
      HandlerThread var2 = new HandlerThread("MyApplication");
      this.mHandlerThread = var2;
      var2.start();
      this.mHandler = new Handler(this, this.mHandlerThread.getLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 1) {
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 2) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   private void initID3() {
      this.mMusicId3s = new ID3[]{new ID3(this, 3), new ID3(this, 4), new ID3(this, 5)};
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus && GeneralDoorData.skyWindowOpenLevel == this.mSkyWindowStatus) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mSkyWindowStatus = GeneralDoorData.skyWindowOpenLevel;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen && GeneralDoorData.skyWindowOpenLevel == 0) {
            return true;
         }
      }

      return false;
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return GeneralAirData.power ^ true;
      } else {
         return false;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicStatusChange() {
      boolean var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (this.mPanoramicStatusNow == var1) {
         return false;
      } else {
         this.mPanoramicStatusNow = var1;
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isSupportAmplifier() {
      boolean var1;
      if (this.mDifferent == 3) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private byte[] phoneNum2UnicodeLittleExtra(byte[] var1) {
      byte[] var4 = new byte[var1.length * 2];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         int var3 = var2 * 2;
         var4[var3] = (byte)(var1[var2] & 255);
         var4[var3 + 1] = 0;
      }

      return var4;
   }

   private void realKeyClick2(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void reportID3Info(ID3[] var1) {
      int var4 = var1.length;
      byte var3 = 0;

      for(int var2 = 0; var2 < var4; ++var2) {
         if (var1[var2].isId3Change()) {
            var4 = var1.length;

            for(var2 = var3; var2 < var4; ++var2) {
               ID3 var5 = var1[var2];
               var5.recordId3Info();
               this.reportID3InfoFinal(var5.command, var5.info);
            }

            return;
         }
      }

   }

   private void reportID3InfoFinal(int var1, String var2) {
      try {
         byte[] var4 = DataHandleUtils.exceptBOMHead(var2.getBytes("unicodeLittle"));
         this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -56, 16, (byte)var1}, var4), 68), (long)(var1 * 80));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private String resolveAirTemp(int var1) {
      String var3;
      label70: {
         boolean var2 = GeneralAirData.fahrenheit_celsius;
         var3 = "HIGH";
         if (var2) {
            if (var1 != 0) {
               if (var1 == 255) {
                  return var3;
               }

               if (var1 >= 1 && var1 <= 254) {
                  var3 = var1 + this.getTempUnitF(this.mContext);
                  return var3;
               }
               break label70;
            }
         } else if (var1 != 0) {
            if (var1 == 31) {
               return var3;
            }

            if (var1 >= 1 && var1 <= 29) {
               var3 = (float)(var1 - 1) * 0.5F + 18.0F + this.getTempUnitC(this.mContext);
               return var3;
            }

            if (var1 >= 32 && var1 <= 35) {
               var3 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
               return var3;
            }

            if (var1 >= 36 && var1 <= 37) {
               var3 = (float)var1 * 0.5F - 3.0F + this.getTempUnitC(this.mContext);
               return var3;
            }
            break label70;
         }

         var3 = "LOW";
         return var3;
      }

      var3 = " ";
      return var3;
   }

   private int resolveAmpData(int var1, int var2) {
      return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var1], var2, 4);
   }

   private String resolveOutDoorTem() {
      return this.mCanBusInfoByte[9] + this.getTempUnitC(this.mContext);
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var5 = Message.obtain();
         var5.what = 1;
         var5.arg1 = var1;
         var5.obj = var2;
         this.mHandler.sendMessageDelayed(var5, var3);
      }
   }

   private void sendNormalMessage(Object var1) {
      this.sendNormalMessage(var1, 0L);
   }

   private void sendNormalMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = Message.obtain();
         var4.what = 2;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void setAirData0x28() {
      int var1 = this.getAirWhat();
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      byte[] var2 = this.mCanBusInfoByte;
      var2 = Arrays.copyOf(var2, var2.length);
      var2[3] = (byte)(var2[3] & 239);
      if (!this.isAirMsgRepeat(var2)) {
         if (!this.isFirst()) {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[5]);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2);
            GeneralAirData.rear_temperature = this.resolveAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
            this.updateAirActivity(this.mContext, var1);
         }
      }
   }

   private void setAmplifierData0x31() {
      GeneralAmplifierData.frontRear = this.resolveAmpData(2, 4) - 7;
      GeneralAmplifierData.leftRight = this.resolveAmpData(2, 0) - 7;
      GeneralAmplifierData.bandBass = this.resolveAmpData(3, 4) - 2;
      GeneralAmplifierData.bandTreble = this.resolveAmpData(3, 0) - 2;
      GeneralAmplifierData.bandMiddle = this.resolveAmpData(4, 4) - 2;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData(this.mContext, this.mCanId);
      SharePreUtil.setStringValue(this.mContext, "11_0x31", Base64.encodeToString(this.mCanBusInfoByte, 0));
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var1.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var2;
      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var2 = false;
      } else {
         var2 = true;
      }

      GeneralDoorData.isBackOpen = var2;
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               GeneralDoorData.skyWindowOpenLevel = 1;
            }
         } else {
            GeneralDoorData.skyWindowOpenLevel = 0;
         }
      } else {
         GeneralDoorData.skyWindowOpenLevel = 2;
      }

      this.mRightFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      this.mLeftFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      this.mRightRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      this.mLeftRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadarData0x1D() {
      int[] var2;
      if (this.isFrontRadarDataChange()) {
         label47: {
            label52: {
               var2 = this.mCanBusInfoInt;
               RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.frontRadarStatus = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
               Log.d("frontRadarStatus", this.frontRadarStatus + " " + this.mCanBusInfoInt[2] + " " + this.mCanBusInfoInt[3] + " " + this.mCanBusInfoInt[4] + " " + this.mCanBusInfoInt[5]);
               int var1;
               if (this.frontRadarStatus == 1) {
                  var1 = this.mCanBusInfoInt[2];
                  if (var1 <= 2 && var1 != 0) {
                     break label52;
                  }
               }

               var2 = this.mCanBusInfoInt;
               var1 = var2[3];
               if (var1 > 2 || var1 == 0) {
                  var1 = var2[4];
                  if (var1 > 2 || var1 == 0) {
                     var1 = var2[5];
                     if (var1 > 2 || var1 == 0) {
                        this.forceReverse(this.mContext, false);
                        break label47;
                     }
                  }
               }
            }

            this.forceReverse(this.mContext, true);
         }

         this.updateParkUi((Bundle)null, this.mContext);
      }

      var2 = this.mCanBusInfoInt;
      if (var2[2] == 0 && var2[3] == 0 && var2[4] == 0 && var2[5] == 0) {
         GeneralParkData.pKeyRadarState = false;
         this.updatePKeyRadar();
      } else {
         GeneralParkData.pKeyRadarState = true;
         this.updatePKeyRadar();
         CountDownTimer.getInstance().reset(5000, new ActionCallback(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void toDo(Object var1) {
               GeneralParkData.pKeyRadarState = false;
               this.this$0.updatePKeyRadar();
            }
         });
      }

   }

   private void setHistoricalFuel0x23() {
      String var5 = this.getFuelUnit(this.mCanBusInfoInt[2]);
      ArrayList var4 = new ArrayList();

      for(int var1 = 0; var1 < 6; ++var1) {
         int[] var3 = this.mCanBusInfoInt;
         int var2 = var1 * 2;
         var2 = this.getDriveData(var3[var2 + 3], var3[var2 + 4]);
         String var6 = (float)var2 / 10.0F + var5;
         if (var2 == 65535) {
            var6 = "";
         }

         var4.add(new DriverUpdateEntity(1, var1, var6));
      }

      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setHybrid0x1F() {
      SharePreUtil.setBoolValue(this.mContext, "share_11_is_suppot_hybrid", DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      this.updateHybirdActivity((Bundle)null);
   }

   private void setMediaCommand0x2F() {
      int var1 = this.mCanBusInfoInt[2];
      switch (var1) {
         case 1:
            this.realKeyClick4(77);
            break;
         case 2:
            this.realKeyClick4(76);
            break;
         case 3:
            this.realKeyClick4(130);
            break;
         case 4:
            this.realKeyClick4(141);
            break;
         case 5:
            this.realKeyClick4(139);
            break;
         case 6:
            this.realKeyClick4(39);
            break;
         case 7:
            this.realKeyClick4(140);
            break;
         case 8:
            this.realKeyClick4(142);
            break;
         case 9:
            this.realKeyClick4(40);
            break;
         default:
            switch (var1) {
               case 17:
                  this.realKeyClick4(14);
                  break;
               case 18:
                  this.realKeyClick4(15);
                  break;
               case 19:
                  this.realKeyClick4(45);
                  break;
               case 20:
                  this.realKeyClick4(46);
                  break;
               case 21:
                  this.realKeyClick4(12);
                  break;
               case 22:
                  this.realKeyClick4(4110);
                  break;
               default:
                  switch (var1) {
                     case 33:
                        this.realKeyClick4(33);
                        break;
                     case 34:
                        this.realKeyClick4(34);
                        break;
                     case 35:
                        this.realKeyClick4(35);
                        break;
                     case 36:
                        this.realKeyClick4(36);
                        break;
                     case 37:
                        this.realKeyClick4(37);
                  }
            }
      }

   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setRearRadarData0x1E() {
      if (this.isRearRadarDataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
         SharePreUtil.setStringValue(this.mContext, "11_0x1E", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
         var2.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
         var2.add(new SettingUpdateEntity(4, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
         var2.add((new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3) - 1));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setSystemInfo0x32() {
      if (this.isPanoramicStatusChange()) {
         this.forceReverse(this.mContext, this.mPanoramicStatusNow);
      }

      SharePreUtil.setStringValue(this.mContext, "11_0x32", Base64.encodeToString(this.mCanBusInfoByte, 0));
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      if (this.nowAmplifierModel != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)) {
         if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            this.nowAmplifierModel = 0;
            this.getUigMgr(this.mContext).removeAmplifierModel();
         } else {
            this.nowAmplifierModel = 1;
         }
      }

   }

   private void setTireData0x25() {
      SharePreUtil.setBoolValue(this.mContext, "share_11_is_suppot_tire", DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         SharePreUtil.setBoolValue(this.mContext, "share_11_is_have_spare_tire", DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
         String var4 = this.getTireUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
         float var1 = this.getTireRule(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
         GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         ArrayList var3 = new ArrayList();
         var3.add(new TireUpdateEntity(0, var2, new String[]{(float)this.mCanBusInfoInt[3] / var1 + var4}));
         var3.add(new TireUpdateEntity(1, var2, new String[]{(float)this.mCanBusInfoInt[4] / var1 + var4}));
         var3.add(new TireUpdateEntity(2, var2, new String[]{(float)this.mCanBusInfoInt[5] / var1 + var4}));
         var3.add(new TireUpdateEntity(3, var2, new String[]{(float)this.mCanBusInfoInt[6] / var1 + var4}));
         if (GeneralTireData.isHaveSpareTire) {
            var3.add(new TireUpdateEntity(4, var2, new String[]{(float)this.mCanBusInfoInt[7] / var1 + var4}));
         }

         GeneralTireData.dataList = var3;
         this.updateTirePressureActivity((Bundle)null);
      }
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 380, 12);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTripInfoOne0x21() {
      String var3 = this.getMileageUnit(this.mCanBusInfoInt[8]);
      ArrayList var2 = new ArrayList();
      int[] var4 = this.mCanBusInfoInt;
      int var1 = DataHandleUtils.rangeNumber(this.getDriveData(var4[2], var4[3]), 0, 9999);
      var2.add(new DriverUpdateEntity(0, 0, (float)var1 / 10.0F + var3));
      var4 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(this.getDriveData(var4[4], var4[5]), 0, 5999);
      var2.add(new DriverUpdateEntity(0, 1, var1 / 60 + " H " + var1 % 60 + " M"));
      var4 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(this.getDriveData(var4[6], var4[7]), 0, 9999);
      var2.add(new DriverUpdateEntity(0, 2, var1 + var3));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTripInfoThree0x27() {
      String var5 = this.getFuelUnit(this.mCanBusInfoInt[2]);
      ArrayList var6 = new ArrayList();

      for(int var1 = 0; var1 < 15; ++var1) {
         int var3 = var1 * 2;
         int var2 = 32 - var3;
         int[] var4 = this.mCanBusInfoInt;
         if (var2 >= var4.length) {
            return;
         }

         var2 = this.getDriveData(var4[31 - var3], var4[var2]);
         String var7 = (float)var2 / 10.0F + var5;
         if (var2 == 65535) {
            var7 = "";
         }

         var6.add(new DriverUpdateEntity(2, var1, var7));
      }

      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTripInfoTwo0x22() {
      String var2 = this.getFuelUnit(this.mCanBusInfoInt[2]);
      ArrayList var3 = new ArrayList();
      int[] var4 = this.mCanBusInfoInt;
      int var1 = this.getDriveData(var4[3], var4[4]);
      var3.add(new DriverUpdateEntity(0, 3, (float)var1 / 10.0F + var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVehicleData0x41() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 1) {
         int[] var4;
         if (var1 != 2) {
            if (var1 == 3) {
               ArrayList var3 = new ArrayList();
               StringBuilder var5 = new StringBuilder();
               var4 = this.mCanBusInfoInt;
               var3.add(new DriverUpdateEntity(0, 9, var5.append(var4[3] * 256 + var4[4]).append(" RPM").toString()));
               this.updateGeneralDriveData(var3);
               this.updateDriveDataActivity((Bundle)null);
               this.setOutDoorTem();
            }
         } else {
            ArrayList var6 = new ArrayList();
            StringBuilder var7 = new StringBuilder();
            var4 = this.mCanBusInfoInt;
            var6.add(new DriverUpdateEntity(0, 3, var7.append(var4[3] * 256 * 256 + var4[4] * 256 + var4[5]).append(" km").toString()));
            StringBuilder var9 = new StringBuilder();
            int[] var8 = this.mCanBusInfoInt;
            var6.add(new DriverUpdateEntity(0, 4, var9.append(var8[6] * 256 + var8[7]).append(" km").toString()));
            var9 = new StringBuilder();
            var8 = this.mCanBusInfoInt;
            var6.add(new DriverUpdateEntity(0, 5, var9.append((float)(var8[8] * 256 * 256 + var8[9] * 256 + var8[10]) / 10.0F).append(" km").toString()));
            var9 = new StringBuilder();
            var8 = this.mCanBusInfoInt;
            var6.add(new DriverUpdateEntity(0, 6, var9.append((float)(var8[11] * 256 * 256 + var8[12] * 256 + var8[13]) / 10.0F).append(" km").toString()));
            var9 = new StringBuilder();
            var8 = this.mCanBusInfoInt;
            var6.add(new DriverUpdateEntity(0, 7, var9.append((float)(var8[14] * 256 + var8[15]) / 100.0F).append(" km/h").toString()));
            var7 = new StringBuilder();
            var4 = this.mCanBusInfoInt;
            var6.add(new DriverUpdateEntity(0, 8, var7.append((float)(var4[16] * 256 + var4[17]) / 10.0F).append(" km/h").toString()));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else {
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(var2[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 2);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  GeneralDoorData.skyWindowOpenLevel = 1;
               }
            } else {
               GeneralDoorData.skyWindowOpenLevel = 0;
            }
         } else {
            GeneralDoorData.skyWindowOpenLevel = 2;
         }

         if (this.isDoorDataChange() && !this.isDoorFirst()) {
            this.updateDoorView(this.mContext);
         }
      }

   }

   private void setVehicleSettings0x26() {
      SharePreUtil.setStringValue(this.mContext, "11_0x26", Base64.encodeToString(this.mCanBusInfoByte, 0));
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3)));
      var1.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)));
      var1.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)));
      var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var1.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
      var1.add((new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      var1.add((new SettingUpdateEntity(1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)));
      var1.add(new SettingUpdateEntity(1, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(1, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var1.add(new SettingUpdateEntity(1, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var1.add(new SettingUpdateEntity(1, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var1.add(new SettingUpdateEntity(1, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3)));
      var1.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      var1.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var1.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2)));
      var1.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)));
      var1.add(new SettingUpdateEntity(1, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
      var1.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2)));
      var1.add(new SettingUpdateEntity(1, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
      var1.add(new SettingUpdateEntity(1, 13, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2)));
      var1.add(new SettingUpdateEntity(1, 14, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2)));
      var1.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2)));
      if (this.mDifferent != 1) {
         var1.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4)));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     switch (var1) {
                        case 7:
                           this.realKeyClick2(2);
                           break;
                        case 8:
                           this.realKeyClick2(187);
                           break;
                        case 9:
                           this.realKeyClick2(14);
                           break;
                        case 10:
                           this.realKeyClick2(15);
                           break;
                        default:
                           switch (var1) {
                              case 19:
                                 this.realKeyClick2(45);
                                 break;
                              case 20:
                                 this.realKeyClick2(46);
                                 break;
                              case 21:
                                 this.realKeyClick2(50);
                                 break;
                              case 22:
                                 this.realKeyClick2(49);
                                 break;
                              default:
                                 switch (var1) {
                                    case 129:
                                       this.realKeyClick2(7);
                                       break;
                                    case 130:
                                       this.realKeyClick2(8);
                                       break;
                                    case 131:
                                       this.realKeyClick2(45);
                                       break;
                                    case 132:
                                       this.realKeyClick2(46);
                                       break;
                                    case 133:
                                       this.realKeyClick2(21);
                                       break;
                                    case 134:
                                       this.realKeyClick2(20);
                                       break;
                                    case 135:
                                       this.realKeyClick2(134);
                                       break;
                                    case 136:
                                       this.realKeyClick2(2);
                                 }
                           }
                     }
                  } else {
                     this.realKeyClick2(47);
                  }
               } else {
                  this.realKeyClick2(48);
               }
            } else {
               this.realKeyClick2(8);
            }
         } else {
            this.realKeyClick2(7);
         }
      } else {
         this.realKeyClick2(0);
      }

   }

   private void sndToyotaSimplePhone(byte[] var1) {
      var1 = this.phoneNum2UnicodeLittleExtra(var1);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -56, 16, 1}, var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.frontRadarStatus = SharePreUtil.getIntValue(var1, "FRONT_RADAR_KEY", 0);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneCallLogInfoChange(int var1, int var2, String var3) {
      super.btPhoneCallLogInfoChange(var1, var2, var3);
      if (var1 < 5) {
         byte var4 = (byte)(var1 + 1);
         byte var5 = (byte)var2;
         byte[] var6 = Util.phoneNum2UnicodeLittleExtra(var3.getBytes());
         CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -57, 16, var4, var5}, var6));
      }
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 4, 0, 0, 0, 0, 0});
      this.sndToyotaSimplePhone(var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 1, 0, 0, 0, 0, 0});
      this.sndToyotaSimplePhone(var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 3, 0, 0, 0, 0, 0});
      this.sndToyotaSimplePhone(var1);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (!var4 && var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 4});
      } else if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0});
         (new Thread(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               super.run();

               for(int var1 = 0; var1 < 5; ++var1) {
                  try {
                     sleep(100L);
                     this.this$0.btPhoneCallLogInfoChange(var1, 0, "");
                  } catch (InterruptedException var3) {
                     var3.printStackTrace();
                     break;
                  }
               }

            }
         }).start();
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 2, 0, 0, 0, 0, 0});
      this.sndToyotaSimplePhone(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var22 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var22;
      int var3 = var22[1];
      Exception var10000;
      boolean var10001;
      if (var3 != 65) {
         switch (var3) {
            case 29:
               try {
                  this.setFrontRadarData0x1D();
                  return;
               } catch (Exception var16) {
                  var10000 = var16;
                  var10001 = false;
                  break;
               }
            case 30:
               try {
                  this.setRearRadarData0x1E();
                  return;
               } catch (Exception var15) {
                  var10000 = var15;
                  var10001 = false;
                  break;
               }
            case 31:
               try {
                  this.setHybrid0x1F();
                  return;
               } catch (Exception var14) {
                  var10000 = var14;
                  var10001 = false;
                  break;
               }
            case 32:
               try {
                  this.setWheelKey0x20();
                  return;
               } catch (Exception var13) {
                  var10000 = var13;
                  var10001 = false;
                  break;
               }
            case 33:
               try {
                  this.setTripInfoOne0x21();
                  return;
               } catch (Exception var12) {
                  var10000 = var12;
                  var10001 = false;
                  break;
               }
            case 34:
               try {
                  this.setTripInfoTwo0x22();
                  return;
               } catch (Exception var11) {
                  var10000 = var11;
                  var10001 = false;
                  break;
               }
            case 35:
               try {
                  this.setHistoricalFuel0x23();
                  return;
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break;
               }
            case 36:
               try {
                  this.setDoorData0x24();
                  return;
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break;
               }
            case 37:
               try {
                  this.setTireData0x25();
                  return;
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break;
               }
            case 38:
               try {
                  this.setVehicleSettings0x26();
                  return;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break;
               }
            case 39:
               try {
                  this.setTripInfoThree0x27();
                  return;
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
                  break;
               }
            case 40:
               try {
                  this.setAirData0x28();
                  return;
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break;
               }
            case 41:
               try {
                  this.setTrackData0x29();
                  return;
               } catch (Exception var4) {
                  var10000 = var4;
                  var10001 = false;
                  break;
               }
            default:
               switch (var3) {
                  case 47:
                     try {
                        this.setMediaCommand0x2F();
                        return;
                     } catch (Exception var20) {
                        var10000 = var20;
                        var10001 = false;
                        break;
                     }
                  case 48:
                     try {
                        this.setVersionInfo0x30();
                        return;
                     } catch (Exception var19) {
                        var10000 = var19;
                        var10001 = false;
                        break;
                     }
                  case 49:
                     try {
                        this.setAmplifierData0x31();
                        return;
                     } catch (Exception var18) {
                        var10000 = var18;
                        var10001 = false;
                        break;
                     }
                  case 50:
                     try {
                        this.setSystemInfo0x32();
                        return;
                     } catch (Exception var17) {
                        var10000 = var17;
                        var10001 = false;
                        break;
                     }
                  default:
                     return;
               }
         }
      } else {
         try {
            this.setVehicleData0x41();
            return;
         } catch (Exception var21) {
            var10000 = var21;
            var10001 = false;
         }
      }

      Exception var23 = var10000;
      Log.e("CanBusError", var23.toString());
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var1});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var10 == 240) {
         var11 = System.getString(this.mContext.getContentResolver(), "currentReport_disc");
         if (!TextUtils.isEmpty(var11)) {
            byte[] var15 = Base64.decode(var11, 0);
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var15);
         }
      } else if (var10 == 32) {
         byte var14;
         if (var7 == 1) {
            var14 = (byte)(var5 / 256);
            var1 = (byte)(var5 % 256);
         } else {
            var14 = (byte)(var4 >> 8 & 255);
            var1 = (byte)(var4 & 255);
         }

         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, var1, var14, 0, 0, 0, 0});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      this.initHandler(var1);
      this.initAmplifierData(var1);
      this.initID3();
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mMusicId3s[0].info = " ";
      this.mMusicId3s[1].info = " ";
      this.mMusicId3s[2].info = " ";
      this.reportID3Info(this.mMusicId3s);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var1 = (byte)(var4 & 255);
      var5 = (byte)(var4 >> 8 & 255);
      var2 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 16, var2, var9, var1, var5, var6, var7});
      this.mMusicId3s[0].info = var21;
      this.mMusicId3s[1].info = var22;
      this.mMusicId3s[2].info = var23;
      this.reportID3Info(this.mMusicId3s);
   }

   public void radioDestroy() {
      super.radioDestroy();
      byte[] var1 = Util.phoneNum2UnicodeLittleExtra("".getBytes());
      CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -56, 16, 6}, var1));
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte[] var10 = Util.phoneNum2UnicodeLittleExtra(var4.getBytes());
      var10 = Util.byteMerger(new byte[]{22, -56, 16, 6}, var10);
      System.putString(this.mContext.getContentResolver(), "currentReportPrev", Base64.encodeToString(var10, 0));
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var10);
      this.getFreqByteHiLo(var2, var3);
      byte var6 = this.getAllBandTypeData(var2, (byte)0, (byte)0, (byte)0, (byte)16, (byte)16);
      byte var9 = this.freqLo;
      byte var7 = this.freqHi;
      byte var8 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, var6, var9, var7, var8});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSettingData(int var1) {
      LogUtil.showLog("updateSettingData:" + var1);
      ArrayList var2 = new ArrayList();
      String var3;
      byte[] var5;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        var3 = SharePreUtil.getStringValue(this.mContext, "11_0x31", (String)null);
                        String var4 = SharePreUtil.getStringValue(this.mContext, "11_0x32", (String)null);
                        if (!TextUtils.isEmpty(var3) && !TextUtils.isEmpty(var4)) {
                           var5 = Base64.decode(var3, 0);
                           byte[] var6 = Base64.decode(var4, 0);
                           var2.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(var5[4], 3, 1)));
                           var2.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(var5[6], 0, 1)));
                           var2.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(var6[2], 6, 1)));
                        }
                     }
                  } else {
                     var3 = SharePreUtil.getStringValue(this.mContext, "11_0x1E", (String)null);
                     if (!TextUtils.isEmpty(var3)) {
                        var5 = Base64.decode(var3, 0);
                        var2.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(var5[6], 7, 1)));
                        var2.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(var5[6], 6, 1)));
                        var2.add(new SettingUpdateEntity(4, 3, DataHandleUtils.getIntFromByteWithBit(var5[6], 4, 1)));
                        var2.add((new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(var5[6], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(var5[6], 0, 3) - 1));
                     }
                  }
               } else {
                  var3 = SharePreUtil.getStringValue(this.mContext, "11_0x26", (String)null);
                  if (!TextUtils.isEmpty(var3)) {
                     var5 = Base64.decode(var3, 0);
                     var2.add((new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(var5[3], 3, 1))).setProgress(DataHandleUtils.getIntFromByteWithBit(var5[3], 3, 1)));
                     var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(var5[5], 4, 2)));
                     var2.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(var5[5], 2, 2)));
                     var2.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(var5[6], 5, 2)));
                     var2.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(var5[7], 4, 2)));
                     if (this.mDifferent != 1) {
                        var2.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(var5[7], 0, 4)));
                     }
                  }
               }
            } else {
               var3 = SharePreUtil.getStringValue(this.mContext, "11_0x26", (String)null);
               if (!TextUtils.isEmpty(var3)) {
                  var5 = Base64.decode(var3, 0);
                  var2.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(var5[5], 7, 1)));
                  var2.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(var5[5], 6, 1)));
               }
            }
         } else {
            var3 = SharePreUtil.getStringValue(this.mContext, "11_0x26", (String)null);
            if (!TextUtils.isEmpty(var3)) {
               var5 = Base64.decode(var3, 0);
               var2.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(var5[3], 7, 1)));
               var2.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(var5[3], 6, 1)));
               var2.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(var5[3], 5, 1)));
               var2.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(var5[3], 4, 1)));
               var2.add((new SettingUpdateEntity(1, 4, DataHandleUtils.getIntFromByteWithBit(var5[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(var5[3], 0, 3)));
               var2.add(new SettingUpdateEntity(1, 5, DataHandleUtils.getIntFromByteWithBit(var5[4], 7, 1)));
               var2.add(new SettingUpdateEntity(1, 6, DataHandleUtils.getIntFromByteWithBit(var5[4], 6, 1)));
               var2.add(new SettingUpdateEntity(1, 7, DataHandleUtils.getIntFromByteWithBit(var5[4], 5, 1)));
               var2.add(new SettingUpdateEntity(1, 8, DataHandleUtils.getIntFromByteWithBit(var5[4], 4, 1)));
               var2.add(new SettingUpdateEntity(1, 9, DataHandleUtils.getIntFromByteWithBit(var5[4], 3, 1)));
               var2.add(new SettingUpdateEntity(1, 10, DataHandleUtils.getIntFromByteWithBit(var5[4], 0, 3)));
            }
         }
      } else {
         var3 = SharePreUtil.getStringValue(this.mContext, "11_0x26", (String)null);
         if (!TextUtils.isEmpty(var3)) {
            var5 = Base64.decode(var3, 0);
            var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(var5[2], 7, 1)));
            var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(var5[2], 4, 3)));
            var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(var5[2], 2, 2)));
            var2.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(var5[2], 0, 2)));
         }
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var2 = (byte)(var4 & 255);
      var5 = (byte)(var4 >> 8 & 255);
      var1 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 16, var1, var9, var2, var5, var6, var7});
   }

   private class ID3 {
      private int command;
      private String info;
      private String record;
      final MsgMgr this$0;

      private ID3(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.command = var2;
         this.info = "";
         this.record = "";
      }

      // $FF: synthetic method
      ID3(MsgMgr var1, int var2, Object var3) {
         this(var1, var2);
      }

      private boolean isId3Change() {
         if (this.record.equals(this.info)) {
            return false;
         } else {
            this.recordId3Info();
            return true;
         }
      }

      private void recordId3Info() {
         this.record = this.info;
      }
   }
}
