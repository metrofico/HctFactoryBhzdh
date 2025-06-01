package com.hzbhd.canbus.car._186;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static final String DEVICE_STATUS_AUX = "AUX";
   private static final String DEVICE_STATUS_CD = "CD";
   private static final String DEVICE_STATUS_POWER_OFF = "Power Off";
   private static final String DEVICE_STATUS_POWER_ON = "Power On";
   private static final String DEVICE_STATUS_RADIO = "RADIO";
   private static final String DEVICE_STATUS_TV = "TV";
   private static final String SHARE_AMPLIFIER_ASL = "share_amplifier_asl";
   private static final String SHARE_AMPLIFIER_BALANCE = "share_amplifier_balance";
   private static final String SHARE_AMPLIFIER_BASS = "share_amplifier_bass";
   private static final String SHARE_AMPLIFIER_BOSE = "share_amplifier_bose";
   private static final String SHARE_AMPLIFIER_FADE = "share_amplifier_fade";
   private static final String SHARE_AMPLIFIER_FIELD = "share_amplifier_field";
   private static final String SHARE_AMPLIFIER_SURROUND = "share_amplifier_surroubd";
   private static final String SHARE_AMPLIFIER_TREBLE = "share_amplifier_treble";
   private static final String SHARE_AMPLIFIER_VOLUME = "share_amplifier_volume";
   private static final int _187_AMPLIFIER_BAND_OFFSET = 5;
   private static final int _187_AMPLIFIER_VOLUME_OFFSET = 20;
   int a = 0;
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   int b = 0;
   int c = 0;
   int cycle = 1;
   int d = 0;
   private DecimalFormat df = new DecimalFormat("0.00");
   int e = 0;
   private int eachId;
   boolean enable = false;
   int f = 0;
   private int[] m0x10DataNow;
   private boolean mAirFirst = true;
   int mAmpAslValueNow;
   int mAmpBoseValueNow;
   int mAmpFieldValueNow;
   int mAmpSurroundValueNow;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mCurrentDisc;
   private String mDeviceStatusNow = "";
   private int mDifferentId;
   private byte mFreqHi;
   private int mFreqInt;
   private byte mFreqLo;
   private String mOriDeviFreq;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private MyPanoramicView mPanoramicView;
   private String mRunningState;
   private boolean mShowMessage;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private byte[] mTrackData;
   private UiMgr mUiMgr;
   DecimalFormat timeFormat = new DecimalFormat("00");
   private List tyreInfoList = new ArrayList();
   private UiMgr uiMgr;

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

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var5 = new byte[var4];

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var3 < var2) {
            var5[var3] = var1[var3];
         } else {
            var5[var3] = var1[var3 + 1];
         }
      }

      return var5;
   }

   private void changeAmplifierSettings() {
      Log.i("ljq", "changeAmplifierSettings: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), this.getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_asl"), this.mAmpAslValueNow)).setProgress(this.mAmpAslValueNow));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), this.getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_bose_centerpoint"), this.mAmpBoseValueNow));
      var1.add((new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), this.getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_surround_volume"), this.mAmpSurroundValueNow)).setProgress(this.mAmpSurroundValueNow + 5));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), this.getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_driver_sound_field"), this.mAmpFieldValueNow));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void changeOriginalDevicePage(List var1, String[] var2, boolean var3) {
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet();
      var4.setItems(var1);
      var4.setRowTopBtnAction(var2);
      var4.setHaveSongList(var3);
      this.updateOriginalDeviceWithInit();
      this.cleanDevice();
   }

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
   }

   private void cleanDevice() {
      this.mRunningState = "";
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.folder = false;
      GeneralOriginalCarDeviceData.wma = false;
      GeneralOriginalCarDeviceData.mp3 = false;
      GeneralOriginalCarDeviceData.scane = false;
      GeneralOriginalCarDeviceData.rds = false;
      GeneralOriginalCarDeviceData.st = false;
      GeneralOriginalCarDeviceData.auto_p = false;
      GeneralOriginalCarDeviceData.mList = null;
      Iterator var1 = this.getOriginalCarDevicePageUiSet().getItems().iterator();

      while(var1.hasNext()) {
         ((OriginalCarDevicePageUiSet.Item)var1.next()).setValue("");
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void finishTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

   }

   private byte getAllBandTypeData(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
            return 17;
         case 1:
            return 18;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 0;
      }
   }

   private String getAmpType(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  return var1 != 5 ? "" : "Beep";
               } else {
                  return "Balance";
               }
            } else {
               return "Fade";
            }
         } else {
            return "Treble";
         }
      } else {
         return "Bass";
      }
   }

   private String getAmpValue(int var1, int var2) {
      if (var1 == 5) {
         var1 = var2;
         if (var2 > 1) {
            var1 = 1;
         }

         if (var1 == 0) {
            return "OFF";
         } else {
            return var1 == 1 ? "ON" : "";
         }
      } else {
         var1 = var2;
         if (var2 < 2) {
            var1 = 2;
         }

         return Integer.toString(var1 - 7);
      }
   }

   private String getCdWorkModle(int var1) {
      String var2;
      switch (var1) {
         case 0:
            var2 = "Play";
            break;
         case 1:
            var2 = "Reading disc " + this.mCurrentDisc;
            break;
         case 2:
            var2 = "Loading disc " + this.mCurrentDisc;
            break;
         case 3:
            var2 = "Insert disc";
            break;
         case 4:
            var2 = "Busy";
            break;
         case 5:
            var2 = "Ejecting disc " + this.mCurrentDisc;
            break;
         case 6:
            var2 = "Select disc to load";
            break;
         case 7:
            var2 = "Select disc to eject";
            break;
         case 8:
            var2 = "Disc error";
            break;
         default:
            var2 = "";
      }

      return var2;
   }

   private String getDeviceWorkModle(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  var2 = "";
               } else {
                  var2 = "TV";
               }
            } else {
               var2 = "AUX";
            }
         } else {
            var2 = "CD";
         }
      } else {
         var2 = "RADIO";
      }

      return var2;
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getIntWithThreeByte(int var1, int var2, int var3) {
      return var1 * 256 * 256 + var2 * 256 + var3;
   }

   private int getIntWithTwoByte(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet() {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr().getOriginalCarDevicePageUiSet(this.mContext);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private String getPlayStatus(int var1) {
      String var2;
      switch (var1) {
         case 0:
            var2 = this.mContext.getResources().getString(2131767779);
            break;
         case 1:
            var2 = this.mContext.getResources().getString(2131769490);
            break;
         case 2:
            var2 = this.mContext.getResources().getString(2131769493);
            break;
         case 3:
            var2 = this.mContext.getResources().getString(2131769489);
            break;
         case 4:
            var2 = this.mContext.getResources().getString(2131767778);
            break;
         case 5:
            var2 = this.mContext.getResources().getString(2131769766);
            break;
         case 6:
            var2 = this.mContext.getResources().getString(2131769861);
            break;
         case 7:
            var2 = this.mContext.getResources().getString(2131767766);
            break;
         default:
            var2 = "";
      }

      return var2;
   }

   private String getPowerStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "Power On";
      } else {
         var2 = "Power Off";
      }

      return var2;
   }

   private String getRadioBand(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     var2 = "";
                  } else {
                     var2 = "FMAP";
                  }
               } else {
                  var2 = "FM2";
               }
            } else {
               var2 = "FM1";
            }
         } else {
            var2 = "AMAP";
         }
      } else {
         var2 = "AM";
      }

      return var2;
   }

   private String getRadioFreq(int var1, int var2, int var3) {
      int var5 = DataHandleUtils.getIntFromByteWithBit(var2, 7, 1);
      var2 = (var2 & 255 >> var5) * 256 + var3;
      String var6;
      if (var1 != 1 && var1 != 2) {
         if (var1 != 3 && var1 != 4 && var1 != 5) {
            var6 = "";
         } else {
            if (var2 > 409) {
               return this.mOriDeviFreq;
            }

            float var4 = (new BigDecimal((double)((float)(var2 - 1) * 0.05F) + 87.5)).setScale(1, 4).floatValue();
            var6 = var4 + "MHz";
         }
      } else {
         if (var2 > 120) {
            return this.mOriDeviFreq;
         }

         var6 = (var2 - 1) * (var5 + 9) + 531 - var5 + "KHz";
      }

      return var6;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private UiMgr getUiMgr() {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

      return this.mUiMgr;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void initAmplifierCmd() {
      Log.i("ljq", "initAmplifierCmd: ");
      byte var2 = (byte)(GeneralAmplifierData.volume - 20);
      byte var1 = (byte)(GeneralAmplifierData.bandBass - 5);
      byte[] var10 = new byte[]{22, -125, 35, (byte)(GeneralAmplifierData.bandTreble - 5)};
      byte var4 = (byte)GeneralAmplifierData.leftRight;
      byte var3 = (byte)GeneralAmplifierData.leftRight;
      byte var5 = (byte)GeneralAmplifierData.frontRear;
      byte var6 = (byte)this.mAmpAslValueNow;
      byte var9 = (byte)this.mAmpBoseValueNow;
      byte var7 = (byte)this.mAmpSurroundValueNow;
      byte var8 = (byte)this.mAmpFieldValueNow;
      this.mTimerTask = new TimerTask(this, new byte[][]{{22, -125, 33, var2}, {22, -125, 34, var1}, var10, {22, -125, 36, var4}, {22, -125, 36, var3}, {22, -125, 37, var5}, {22, -125, 38, var6}, {22, -125, 39, var9}, {22, -125, 40, var7}, {22, -125, 41, var8}}) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$ampCmdArrays;

         {
            this.this$0 = var1;
            this.val$ampCmdArrays = var2;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$ampCmdArrays;
            if (var1 < var2.length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.this$0.finishTimer();
            }

         }
      };
   }

   private void initAmplifierData() {
      GeneralAmplifierData.volume = SharePreUtil.getIntValue(this.mContext, "share_amplifier_volume", 0);
      GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(this.mContext, "share_amplifier_bass", 0);
      GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(this.mContext, "share_amplifier_treble", 0);
      GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(this.mContext, "share_amplifier_balance", 0);
      GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(this.mContext, "share_amplifier_fade", 0);
      this.mAmpAslValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_asl", 0);
      this.mAmpBoseValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_bose", 0);
      this.mAmpSurroundValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_surroubd", 0);
      this.mAmpFieldValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_field", 0);
      Log.i("ljq", "initAmplifierData: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
   }

   private boolean is0x10DataChange() {
      if (Arrays.equals(this.m0x10DataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x10DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDeviceStatusConform(String var1) {
      return "Power Off".equals(GeneralOriginalCarDeviceData.cdStatus) ? false : var1.equals(GeneralOriginalCarDeviceData.discStatus);
   }

   private boolean isDeviceStatusSame(String var1) {
      if (var1.equals(GeneralOriginalCarDeviceData.discStatus) && !var1.equals(this.mDeviceStatusNow)) {
         this.mDeviceStatusNow = var1;
         return true;
      } else {
         return false;
      }
   }

   private boolean isFirst() {
      if (this.mAirFirst) {
         this.mAirFirst = false;
         if (!GeneralAirData.power || GeneralAirData.front_wind_level == 0) {
            return true;
         }
      }

      return false;
   }

   private boolean isTrackDataNoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return true;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyClick6(this.mContext, var1);
   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private String resolveCarSpeed() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = this.getIntWithTwoByte(var2[3], var2[2]);
      return var1 + " km/h";
   }

   private String resolveEnduranceMileage() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = this.getIntWithTwoByte(var2[6], var2[7]);
      String var4;
      if (var1 != 65535) {
         StringBuilder var3 = (new StringBuilder()).append(var1 / 10);
         if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            var4 = " mile";
         } else {
            var4 = " km";
         }

         var4 = var3.append(var4).toString();
      } else {
         var4 = "";
      }

      return var4;
   }

   private String resolveEngineSpeed() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = this.getIntWithTwoByte(var2[3], var2[2]);
      return var1 + " rpm";
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 >= 1 && var1 <= 15) {
         var2 = "LO";
      } else if (var1 >= 49 && var1 <= 63) {
         var2 = "HI";
      } else if (var1 >= 16 && var1 <= 48) {
         var2 = (float)var1 / 2.0F + 8.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "---";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[6];
      String var2;
      if (var1 >= 1 && var1 <= 254) {
         var2 = (float)var1 * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveTotalMileage() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = this.getIntWithThreeByte(var2[3], var2[4], var2[5]);
      String var4;
      if (var1 != 16777215) {
         StringBuilder var3 = (new StringBuilder()).append(var1);
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            var4 = " mile";
         } else {
            var4 = " km";
         }

         var4 = var3.append(var4).toString();
      } else {
         var4 = "";
      }

      return var4;
   }

   private String resolvetime(int[] var1) {
      return var1[2] + ":" + var1[3];
   }

   private void saveAmplifierData() {
      Log.i("ljq", "saveAmplifierData: ");
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_volume", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_asl", this.mAmpAslValueNow);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_bose", this.mAmpBoseValueNow);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_surroubd", this.mAmpSurroundValueNow);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_field", this.mAmpFieldValueNow);
   }

   private void sendKnob_1(int var1) {
      this.realKeyClick3_1(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
   }

   private void sendKnob_2(int var1) {
      this.realKeyClick3_2(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
   }

   private void setAirData0x21() {
      // $FF: Couldn't be decompiled
   }

   private void setAmplifierInfo0x09() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
      String var2 = this.getAmpType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3));
      String var3 = this.getAmpValue(var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 5));
      var2 = var2 + " " + var3;
      if (var1 != 0) {
         GeneralOriginalCarDeviceData.runningState = var2;
         this.updateOriginalCarDeviceActivity((Bundle)null);
         this.mTimerTask = new TimerTask(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               GeneralOriginalCarDeviceData.runningState = this.this$0.mRunningState;
               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
               this.this$0.finishTimer();
            }
         };
         this.startTimer();
      }

   }

   private void setAmplifierInfo0x93() {
      Log.i("ljq", "setAmplifierInfo0x93: ");
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.bandBass = this.mCanBusInfoByte[3] + 5;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[4] + 5;
      GeneralAmplifierData.leftRight = this.mCanBusInfoByte[5];
      GeneralAmplifierData.frontRear = this.mCanBusInfoByte[6];
      this.updateAmplifierActivity((Bundle)null);
      this.mAmpAslValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[7], 0, 5);
      this.mAmpBoseValueNow = this.mCanBusInfoInt[8];
      this.mAmpSurroundValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[9], -5, 5);
      this.mAmpFieldValueNow = this.mCanBusInfoInt[10];
      this.changeAmplifierSettings();
      this.saveAmplifierData();
   }

   private void setCarSpeed0x6A() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 3, this.resolveCarSpeed()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var2 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var2[3], var2[2]));
   }

   private void setCdInfo0x07() {
      if (this.isDeviceStatusConform("CD")) {
         this.mCurrentDisc = this.mCanBusInfoInt[2];
         if (GeneralOriginalCarDeviceData.songList != null) {
            Iterator var2 = GeneralOriginalCarDeviceData.songList.iterator();

            while(var2.hasNext()) {
               SongListEntity var1 = (SongListEntity)var2.next();
               var1.setSelected(var1.getTitle().substring(var1.getTitle().length() - 1).equals(Integer.toString(this.mCurrentDisc)));
            }
         }

         ArrayList var3 = new ArrayList();
         var3.add(new OriginalCarDeviceUpdateEntity(1, "Disc " + this.mCurrentDisc));
         var3.add(new OriginalCarDeviceUpdateEntity(2, Integer.toString(this.mCanBusInfoInt[3])));
         var3.add(new OriginalCarDeviceUpdateEntity(3, (new DecimalFormat("00")).format((long)this.mCanBusInfoInt[4]) + ":" + (new DecimalFormat("00")).format((long)this.mCanBusInfoInt[5])));
         GeneralOriginalCarDeviceData.mList = var3;
         String var4 = this.getCdWorkModle(this.mCanBusInfoInt[6]);
         this.mRunningState = var4;
         GeneralOriginalCarDeviceData.runningState = var4;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setCdMessage0x08() {
      if (this.isDeviceStatusConform("CD")) {
         ArrayList var2 = new ArrayList();
         if (this.mShowMessage) {
            byte[] var1 = this.mCanBusInfoByte;
            var2.add(new OriginalCarDeviceUpdateEntity(4, new String(Arrays.copyOfRange(var1, 2, var1.length))));
         } else {
            var2.add(new OriginalCarDeviceUpdateEntity(4, ""));
         }

         GeneralOriginalCarDeviceData.mList = var2;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setCdStatus0x06() {
      if (this.isDeviceStatusConform("CD")) {
         GeneralOriginalCarDeviceData.folder = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.wma = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.mp3 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mShowMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         ArrayList var4 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = false;
         var4.add(new OriginalCarDeviceUpdateEntity(0, this.getPlayStatus(DataHandleUtils.getIntFromByteWithBit(var1, 0, 3))));
         if (!this.mShowMessage) {
            var4.add(new OriginalCarDeviceUpdateEntity(4, ""));
         }

         GeneralOriginalCarDeviceData.mList = var4;
         var4 = new ArrayList();
         SongListEntity var5 = new SongListEntity("Disc 1");
         boolean var2;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 2");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 3");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 4");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 4) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 5");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 5) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 6");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         var2 = var3;
         if (this.mCurrentDisc == 6) {
            var2 = true;
         }

         var4.add(var5.setSelected(var2));
         GeneralOriginalCarDeviceData.songList = var4;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setDrivingAids0x95() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_driving_aids"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_driving_aids", "_186_moving_object_detection"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_driving_aids"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_driving_aids", "_186_lane_departure_detection"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_driving_aids"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_driving_aids", "_186_blind_spot_detection"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Unlock_and_turn_on_the_lights"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Auto_light_sensitivity"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Vehicle_speed_linkage_intermittent_wiper"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Auto_light_off_time_setting"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings2"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings2", "_186_Smart_key_unlock"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings2"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings2", "_186_auto_retreat"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings2"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings2", "_186_switch_Unlock"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setEngineSpeed0x68() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 2, this.resolveEngineSpeed()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadar0x23() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setInstrument0x60() {
      if (this.mCanBusInfoInt[2] == 49) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "vm_golf7_language_setup"), this.getUiMgr().getSettingRightIndex(this.mContext, "vm_golf7_language_setup", "vm_golf7_language_setup"), this.mCanBusInfoInt[3]));
         GeneralSettingData.dataList = var1;
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void setMediaSwitchCommand0x40() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.enterNoSource();
            this.realKeyClick(52);
            break;
         case 1:
            this.changeBandAm1();
            break;
         case 2:
            this.changeBandFm1();
            break;
         case 3:
            this.changeBandFm2();
            break;
         case 4:
            this.realKeyClick(130);
            break;
         case 5:
            this.realKeyClick(59);
            break;
         case 6:
            this.realKeyClick(61);
            break;
         case 7:
            this.realKeyClick(140);
            break;
         case 8:
            this.realKeyClick(141);
            break;
         case 9:
            this.realKeyClick(129);
            break;
         case 10:
            this.realKeyClick(139);
            break;
         case 11:
            this.realKeyClick(52);
      }

   }

   private void setMileageData0x27() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.resolveTotalMileage()));
      var1.add(new DriverUpdateEntity(0, 1, this.resolveEnduranceMileage()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginalCarWorkModle0x10() {
      if (this.is0x10DataChange()) {
         GeneralOriginalCarDeviceData.cdStatus = this.getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
         GeneralOriginalCarDeviceData.discStatus = this.getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3));
         this.updateOriginalCarDeviceActivity((Bundle)null);
         if ("Power Off".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            this.cleanDevice();
            return;
         }

         if (this.isDeviceStatusSame("RADIO")) {
            this.cleanDevice();
            this.setOriginalRadioPage();
         } else if (this.isDeviceStatusSame("CD")) {
            this.cleanDevice();
            this.setOriginalCdPage();
         } else {
            if (!this.isDeviceStatusSame("TV")) {
               if (this.isDeviceStatusSame("AUX")) {
                  this.exitAuxIn2();
                  this.runOnUi(new CallBackInterface(this) {
                     final MsgMgr this$0;

                     {
                        this.this$0 = var1;
                     }

                     public void callback() {
                        CommUtil.showToast(this.this$0.mContext, CommUtil.getStrByResId(this.this$0.mContext, "_272_toast_text18"));
                     }
                  });
                  this.setOriginalOtherPage();
               }

               return;
            }

            this.setOriginalOtherPage();
         }

         if ("Power On".equals(GeneralOriginalCarDeviceData.cdStatus) && !"".equals(GeneralOriginalCarDeviceData.discStatus)) {
            this.showOriginalDevice();
         }
      }

   }

   private void setOriginalCdPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_artist", "_186_play_modle", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_disc", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_play_time", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, new String[]{"folder", "wma", "mp3", "scane"}, true);
   }

   private void setOriginalOtherPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, (String[])null, false);
   }

   private void setOriginalRadioPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, new String[]{"rds", "scane", "st", "auto_p"}, false);
   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setParkAssistance0x94() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (MyPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      this.mPanoramicView.mPageNumber = this.mCanBusInfoInt[2];
      this.mPanoramicView.mIbUpStatus = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbDownStatus = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbLeftStatus = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbRightStatus = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbRightDownStatus = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbLeftDownStatus = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mTvTipsStatus = this.mCanBusInfoInt[4];
      this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[5];
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mPanoramicView.updatePanoramicView(this.this$0.mContext);
         }
      });
   }

   private void setPhoneCommand0x50() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               this.realKeyClick(15);
            }
         } else {
            this.realKeyClick(15);
         }
      } else {
         this.realKeyClick(14);
      }

   }

   private void setRadioInfo0x04() {
      if (this.isDeviceStatusConform("RADIO")) {
         ArrayList var1 = new ArrayList();
         var1.add(new OriginalCarDeviceUpdateEntity(0, this.getRadioBand(this.mCanBusInfoInt[2])));
         var1.add(new OriginalCarDeviceUpdateEntity(1, "P" + this.mCanBusInfoInt[3]));
         int[] var2 = this.mCanBusInfoInt;
         String var3 = this.getRadioFreq(var2[2], var2[4], var2[5]);
         this.mOriDeviFreq = var3;
         var1.add(new OriginalCarDeviceUpdateEntity(2, var3));
         GeneralOriginalCarDeviceData.mList = var1;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setRadioMessage0x05() {
      if (this.isDeviceStatusConform("RADIO")) {
         ArrayList var1 = new ArrayList();
         var1.add(new OriginalCarDeviceUpdateEntity(3, new String(this.mCanBusInfoByte)));
         GeneralOriginalCarDeviceData.mList = var1;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setRadioStatus0x03() {
      if (this.isDeviceStatusConform("RADIO")) {
         GeneralOriginalCarDeviceData.rds = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.auto_p = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mShowMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         ArrayList var1 = new ArrayList();
         if (!this.mShowMessage) {
            var1.add(new OriginalCarDeviceUpdateEntity(3, ""));
         }

         GeneralOriginalCarDeviceData.mList = var1;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setRealKeyControl0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 != 7) {
                           if (var1 != 9) {
                              if (var1 != 10) {
                                 if (var1 != 21) {
                                    if (var1 != 22) {
                                       if (var1 != 64) {
                                          if (var1 != 66) {
                                             if (var1 != 68) {
                                                if (var1 != 74) {
                                                   if (var1 != 78) {
                                                      if (var1 != 96) {
                                                         if (var1 != 98) {
                                                            if (var1 != 100) {
                                                               if (var1 != 102) {
                                                                  if (var1 != 135) {
                                                                     if (var1 != 80) {
                                                                        if (var1 != 81) {
                                                                           switch (var1) {
                                                                              case 32:
                                                                                 this.realKeyClick1(183);
                                                                                 break;
                                                                              case 33:
                                                                                 this.realKeyClick1(183);
                                                                                 break;
                                                                              case 34:
                                                                                 this.realKeyClick1(128);
                                                                                 break;
                                                                              case 35:
                                                                                 this.realKeyClick1(128);
                                                                                 break;
                                                                              case 36:
                                                                                 this.realKeyClick1(76);
                                                                                 break;
                                                                              case 37:
                                                                                 this.realKeyClick1(130);
                                                                                 break;
                                                                              case 38:
                                                                                 this.realKeyClick1(77);
                                                                                 break;
                                                                              case 39:
                                                                                 this.realKeyClick1(33);
                                                                                 break;
                                                                              case 40:
                                                                                 this.realKeyClick1(34);
                                                                                 break;
                                                                              case 41:
                                                                                 this.realKeyClick1(35);
                                                                                 break;
                                                                              case 42:
                                                                                 this.realKeyClick1(36);
                                                                                 break;
                                                                              case 43:
                                                                                 this.realKeyClick1(37);
                                                                                 break;
                                                                              case 44:
                                                                                 this.realKeyClick1(38);
                                                                                 break;
                                                                              case 45:
                                                                                 this.sendKnob_1(8);
                                                                                 break;
                                                                              case 46:
                                                                                 this.sendKnob_1(7);
                                                                                 break;
                                                                              case 47:
                                                                                 this.sendKnob_2(47);
                                                                                 break;
                                                                              case 48:
                                                                                 this.sendKnob_2(48);
                                                                                 break;
                                                                              case 49:
                                                                                 this.realKeyClick1(45);
                                                                                 break;
                                                                              case 50:
                                                                                 this.realKeyClick1(46);
                                                                                 break;
                                                                              case 51:
                                                                                 this.realKeyClick1(3);
                                                                                 break;
                                                                              default:
                                                                                 switch (var1) {
                                                                                    case 70:
                                                                                       this.wheelKeyClick(56);
                                                                                       break;
                                                                                    case 71:
                                                                                       this.wheelKeyClick(151);
                                                                                       break;
                                                                                    case 72:
                                                                                       this.wheelKeyClick(56);
                                                                                       break;
                                                                                    default:
                                                                                       switch (var1) {
                                                                                          case 112:
                                                                                             this.realKeyClick1(49);
                                                                                             break;
                                                                                          case 113:
                                                                                             this.realKeyClick1(50);
                                                                                             break;
                                                                                          case 114:
                                                                                             this.realKeyClick1(128);
                                                                                             break;
                                                                                          case 115:
                                                                                             this.realKeyClick1(52);
                                                                                             break;
                                                                                          case 116:
                                                                                             this.sendKnob_1(7);
                                                                                             break;
                                                                                          case 117:
                                                                                             this.sendKnob_1(8);
                                                                                             break;
                                                                                          default:
                                                                                             switch (var1) {
                                                                                                case 145:
                                                                                                   this.realKeyClick1(18);
                                                                                                   break;
                                                                                                case 146:
                                                                                                   this.realKeyClick1(20);
                                                                                                   break;
                                                                                                case 147:
                                                                                                   this.realKeyClick1(21);
                                                                                                   break;
                                                                                                case 148:
                                                                                                   this.realKeyClick1(19);
                                                                                                   break;
                                                                                                case 149:
                                                                                                   this.realKeyClick1(90);
                                                                                                   break;
                                                                                                case 150:
                                                                                                   this.realKeyClick1(91);
                                                                                             }
                                                                                       }
                                                                                 }
                                                                           }
                                                                        } else {
                                                                           this.wheelKeyClick(31);
                                                                        }
                                                                     } else {
                                                                        this.wheelKeyClick(53);
                                                                     }
                                                                  } else {
                                                                     this.realKeyClick1(134);
                                                                  }
                                                               } else {
                                                                  this.realKeyClick1(47);
                                                               }
                                                            } else {
                                                               this.realKeyClick1(46);
                                                            }
                                                         } else {
                                                            this.realKeyClick1(48);
                                                         }
                                                      } else {
                                                         this.realKeyClick1(45);
                                                      }
                                                   } else {
                                                      this.wheelKeyClick(222);
                                                   }
                                                } else {
                                                   this.wheelKeyClick(221);
                                                }
                                             } else {
                                                this.wheelKeyClick(182);
                                             }
                                          } else {
                                             this.wheelKeyClick(30);
                                          }
                                       } else {
                                          this.wheelKeyClick(141);
                                       }
                                    } else {
                                       this.realKeyClick1(49);
                                    }
                                 } else {
                                    this.realKeyClick1(50);
                                 }
                              } else {
                                 this.realKeyClick1(15);
                              }
                           } else {
                              this.realKeyClick1(14);
                           }
                        } else {
                           this.realKeyClick1(2);
                        }
                     } else {
                        this.realKeyClick1(3);
                     }
                  } else {
                     this.realKeyClick1(46);
                  }
               } else {
                  this.realKeyClick1(45);
               }
            } else {
               this.realKeyClick1(8);
            }
         } else {
            this.realKeyClick1(7);
         }
      } else {
         this.realKeyClick1(0);
      }

   }

   private void setRearAir0x11() {
      GeneralAirData.rear_power = true;
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      if (var1 != 11) {
         if (var1 == 27) {
            GeneralAirData.rear_auto = true;
         }
      } else {
         GeneralAirData.rear_auto = false;
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        GeneralAirData.rear_wind_level = 5;
                     }
                  } else {
                     GeneralAirData.rear_wind_level = 4;
                  }
               } else {
                  GeneralAirData.rear_wind_level = 3;
               }
            } else {
               GeneralAirData.rear_wind_level = 2;
            }
         } else {
            GeneralAirData.rear_wind_level = 1;
         }
      } else {
         GeneralAirData.rear_power = false;
         GeneralAirData.rear_wind_level = 0;
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 6);
      if (var1 == 0) {
         GeneralAirData.rear_temperature = "";
      }

      if (var1 >= 1 && var1 <= 15) {
         GeneralAirData.rear_temperature = "LO";
      } else if (var1 >= 16 && var1 <= 48) {
         GeneralAirData.rear_temperature = (float)(var1 - 16) / 2.0F + 16.0F + " ℃";
      } else if (var1 >= 49 && var1 <= 63) {
         GeneralAirData.rear_temperature = "HI";
      }

      this.updateAirActivity(this.mContext, 1002);
   }

   private void setRearEntertainmentSystem0x96() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 50) {
         if (var1[3] == 1) {
            this.enable = true;
         } else {
            this.enable = false;
         }
      }

      Log.d("Lai", this.enable + "");
      ArrayList var2 = new ArrayList();
      var1 = this.mCanBusInfoInt;
      switch (var1[2]) {
         case 50:
            this.a = var1[3];
            break;
         case 51:
            this.b = var1[3];
            break;
         case 52:
            this.c = var1[3];
            break;
         case 53:
            this.d = var1[3];
            break;
         case 54:
            this.e = var1[3];
            break;
         case 55:
            this.f = var1[3];
      }

      var2.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Rear_Entertainment_System_Interface"), this.a));
      var2.add((new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Left_monitor_power_supply"), this.b)).setEnable(this.enable));
      var2.add((new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Left_speaker_output"), this.c)).setEnable(this.enable));
      var2.add((new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Right_monitor_power_supply"), this.d)).setEnable(this.enable));
      var2.add((new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Right_speaker_output"), this.e)).setEnable(this.enable));
      var2.add((new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), this.getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Automatically_power_on_the_monitor"), this.f)).setEnable(this.enable));
      GeneralSettingData.dataList = var2;
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearRadar0x22() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTimeInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr().getDrivingPageIndexes(this.mContext, "vehicle_info"), this.getUiMgr().getDrivingItemIndexes(this.mContext, "_186_time"), this.resolvetime(this.mCanBusInfoInt)));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTireInfo() {
      if (!this.isTrackDataNoChange()) {
         if (this.mCanBusInfoInt[2] == 255) {
            this.arr0[0] = this.mContext.getString(2131758650) + "：--" + this.getTempUnitC(this.mContext);
         } else {
            this.arr0[0] = this.mContext.getString(2131758650) + "：" + (this.mCanBusInfoInt[2] - 40) + this.getTempUnitC(this.mContext);
         }

         if (this.mCanBusInfoInt[3] == 255) {
            this.arr1[0] = this.mContext.getString(2131758650) + "：--" + this.getTempUnitC(this.mContext);
         } else {
            this.arr1[0] = this.mContext.getString(2131758650) + "：" + (this.mCanBusInfoInt[3] - 40) + this.getTempUnitC(this.mContext);
         }

         if (this.mCanBusInfoInt[4] == 255) {
            this.arr2[0] = this.mContext.getString(2131758650) + "：--" + this.getTempUnitC(this.mContext);
         } else {
            this.arr2[0] = this.mContext.getString(2131758650) + "：" + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
         }

         if (this.mCanBusInfoInt[5] == 255) {
            this.arr3[0] = this.mContext.getString(2131758650) + "：--" + this.getTempUnitC(this.mContext);
         } else {
            this.arr3[0] = this.mContext.getString(2131758650) + "：" + (this.mCanBusInfoInt[5] - 40) + this.getTempUnitC(this.mContext);
         }

         if (this.mCanBusInfoInt[6] == 255) {
            this.arr0[1] = this.mContext.getString(2131758649) + "：--Kpa";
         } else {
            this.arr0[1] = this.mContext.getString(2131758649) + "：" + this.mCanBusInfoInt[6] * 2 + "Kpa";
         }

         if (this.mCanBusInfoInt[7] == 255) {
            this.arr1[1] = this.mContext.getString(2131758649) + "：--Kpa";
         } else {
            this.arr1[1] = this.mContext.getString(2131758649) + "：" + this.mCanBusInfoInt[7] * 2 + "Kpa";
         }

         if (this.mCanBusInfoInt[8] == 255) {
            this.arr2[1] = this.mContext.getString(2131758649) + "：--Kpa";
         } else {
            this.arr2[1] = this.mContext.getString(2131758649) + "：" + this.mCanBusInfoInt[8] * 2 + "Kpa";
         }

         if (this.mCanBusInfoInt[9] == 255) {
            this.arr3[1] = this.mContext.getString(2131758649) + "：--Kpa";
         } else {
            this.arr3[1] = this.mContext.getString(2131758649) + "：" + this.mCanBusInfoInt[9] * 2 + "Kpa";
         }

         this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
         this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
         this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
         this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
         GeneralTireData.dataList = this.tyreInfoList;
         this.updateTirePressureActivity((Bundle)null);
      }
   }

   private void setTrackData0x29() {
      int[] var1;
      DecimalFormat var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1 = this.mCanBusInfoInt;
         var1[2] = this.blockBit(var1[2], 7);
         var2 = this.timeFormat;
         var1 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = Integer.parseInt(var2.format((double)this.getMsbLsbResult(var1[2], var1[3]) * 0.1 / 21.0));
      } else {
         var1 = this.mCanBusInfoInt;
         var1[2] = this.blockBit(var1[2], 7);
         var2 = this.timeFormat;
         var1 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = Integer.parseInt(var2.format(-((double)this.getMsbLsbResult(var1[2], var1[3]) * 0.1) / 21.0));
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVehicleDoorInfo0x28() {
      if (!this.isDoorMsgRepeat(new byte[]{this.mCanBusInfoByte[2]})) {
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSubShowSeatBelt = true;
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setVolumeInfo0x0A() {
      boolean var3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
      int var1 = var2 >> 4;
      var2 &= 15;
      String var4;
      if (var3) {
         var4 = Integer.toString(var1) + Integer.toString(var2);
      } else {
         var4 = "";
      }

      if (var1 <= 9 && var2 <= 9 || !var3) {
         ArrayList var5 = new ArrayList();
         var5.add(new OriginalCarDeviceUpdateEntity(this.getOriginalCarDevicePageUiSet().getItems().size() - 1, var4));
         GeneralOriginalCarDeviceData.mList = var5;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void showOriginalDevice() {
      Intent var1 = new Intent();
      var1.setComponent(Constant.OriginalDeviceActivity);
      var1.setFlags(268435456);
      this.mContext.startActivity(var1);
   }

   private void startTimer() {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, 1000L);
      }
   }

   private void updateOriginalDeviceWithInit() {
      Bundle var1 = new Bundle();
      var1.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var1);
   }

   private void wheelKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, -1, 1, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var3 && var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1, 32});
      } else if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 32});
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 48) {
            if (var3 != 64) {
               if (var3 != 80) {
                  if (var3 == 96) {
                     this.setInstrument0x60();
                     return;
                  }

                  if (var3 != 104) {
                     if (var3 != 106) {
                        if (var3 != 16) {
                           if (var3 != 17) {
                              switch (var3) {
                                 case 3:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setRadioStatus0x03();
                                    return;
                                 case 4:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setRadioInfo0x04();
                                    return;
                                 case 5:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setRadioMessage0x05();
                                    return;
                                 case 6:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setCdStatus0x06();
                                    return;
                                 case 7:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setCdInfo0x07();
                                    return;
                                 case 8:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setCdMessage0x08();
                                    return;
                                 case 9:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setAmplifierInfo0x09();
                                    return;
                                 case 10:
                                    if (this.eachId != 5) {
                                       return;
                                    }

                                    this.setVolumeInfo0x0A();
                                    return;
                                 default:
                                    switch (var3) {
                                       case 32:
                                          this.setRealKeyControl0x20();
                                          return;
                                       case 33:
                                          var3 = this.eachId;
                                          if (var3 != 4 && var3 != 5 && var3 != 6 && var3 != 7 && var3 != 10 && var3 != 17 && var3 != 18) {
                                             return;
                                          }

                                          this.setAirData0x21();
                                          return;
                                       case 34:
                                          this.setRearRadar0x22();
                                          return;
                                       case 35:
                                          this.setFrontRadar0x23();
                                          return;
                                       default:
                                          switch (var3) {
                                             case 39:
                                                var3 = this.eachId;
                                                if (var3 != 5 && var3 != 7 && var3 != 13) {
                                                   return;
                                                }

                                                this.setMileageData0x27();
                                                return;
                                             case 40:
                                                var3 = this.eachId;
                                                if (var3 != 5 && var3 != 7 && var3 != 13) {
                                                   return;
                                                }

                                                this.setVehicleDoorInfo0x28();
                                                return;
                                             case 41:
                                                var3 = this.eachId;
                                                if (var3 != 2 && var3 != 5 && var3 != 7 && var3 != 13) {
                                                   return;
                                                }

                                                this.setTrackData0x29();
                                                return;
                                             default:
                                                switch (var3) {
                                                   case 146:
                                                      this.setTireInfo();
                                                      return;
                                                   case 147:
                                                      this.setAmplifierInfo0x93();
                                                      return;
                                                   case 148:
                                                      this.setParkAssistance0x94();
                                                      return;
                                                   case 149:
                                                      this.setDrivingAids0x95();
                                                      return;
                                                   case 150:
                                                      if (this.eachId != 15) {
                                                         return;
                                                      }

                                                      this.setRearEntertainmentSystem0x96();
                                                }
                                          }
                                    }
                              }
                           } else {
                              var3 = this.eachId;
                              if (var3 != 17 && var3 != 18) {
                                 return;
                              }

                              this.setRearAir0x11();
                           }

                           return;
                        } else {
                           if (this.eachId != 5) {
                              return;
                           }

                           this.setOriginalCarWorkModle0x10();
                           return;
                        }
                     } else {
                        var3 = this.eachId;
                        if (var3 != 5 && var3 != 7) {
                           return;
                        }

                        this.setCarSpeed0x6A();
                        return;
                     }
                  }
               } else {
                  this.setPhoneCommand0x50();
               }

               var3 = this.eachId;
               if (var3 != 5 && var3 != 7 && var3 != 13) {
                  return;
               }

               this.setEngineSpeed0x68();
            } else {
               this.setMediaSwitchCommand0x40();
            }
         } else {
            this.setVersionInfo0x30();
         }
      } else {
         this.setTimeInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var6, (byte)var8, (byte)var10});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var10 == 240) {
         this.sendDiscEjectMsg(this.mContext);
      } else if (var10 == 32) {
         var3 = this.getHour(var2);
         var10 = this.getMinute(var2);
         var2 = this.getSecond(var2);
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         var1 = (byte)var4;
         byte var15 = (byte)var6;
         byte var17 = (byte)var3;
         byte var14 = (byte)var10;
         byte var16 = (byte)var2;
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 16, 0, var1, var15, var17, var14, var16});
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 16, 0, var1, var15, var17, var14, var16});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.initAmplifierData();
      this.initAmplifierCmd();
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, -1});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -118, -1});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
      int var2 = this.eachId;
      if (var2 != 17 && var2 != 18) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 89, 0});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 89, (byte)(var2 - 16)});
      }

      GeneralTireData.isHaveSpareTire = false;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var2 = (byte)(var4 & 255);
         var5 = (byte)(var4 >> 8 & 255);
         var1 = (byte)var3;
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, var2, var5, var1, var9, var6, var7});
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, var2, var5, var1, var9, var6, var7});
         byte[] var25 = var21.getBytes(StandardCharsets.UTF_16LE);
         var25 = DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, 112, 17}, var25), 32), new byte[]{0, 0});
         byte[] var26 = var23.getBytes(StandardCharsets.UTF_16LE);
         var26 = DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, 113, 17}, var26), 32), new byte[]{0, 0});
         CanbusMsgSender.sendMsg(var25);
         CanbusMsgSender.sendMsg(var26);
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var7 = this.getAllBandTypeData(var2);
      int[] var6 = this.getFreqByteHiLo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, (byte)var7, (byte)var6[1], (byte)var6[0], (byte)var1, 0, 0});
   }

   protected void realKeyClick6(Context var1, int var2) {
      RealKeyUtil.realKeyClick(var1, var2);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      Log.i("ljq", "sourceSwitchChange: " + var1);
      if (SourceConstantsDef.SOURCE_ID.ATV.name().equals(var1) || SourceConstantsDef.SOURCE_ID.AUX1.name().equals(var1) || SourceConstantsDef.SOURCE_ID.BTAUDIO.name().equals(var1) || SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(var1) || SourceConstantsDef.SOURCE_ID.MPEG.name().equals(var1) || SourceConstantsDef.SOURCE_ID.DTV.name().equals(var1) || SourceConstantsDef.SOURCE_ID.MUSIC.name().equals(var1) || SourceConstantsDef.SOURCE_ID.FM.name().equals(var1) || SourceConstantsDef.SOURCE_ID.VIDEO.name().equals(var1) || SourceConstantsDef.SOURCE_ID.NAVIAUDIO.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   protected void updateBubble(Context var1, boolean var2) {
      GeneralBubbleData.isShowBubble = var2;
      this.updateBubble(var1);
   }

   public void updateSettingActivity(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettingActivity(int var1, int var2, Object var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      var5.add((new SettingUpdateEntity(var1, var2, var3)).setEnable(var4));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
      }
   }
}
