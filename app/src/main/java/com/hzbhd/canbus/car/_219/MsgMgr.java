package com.hzbhd.canbus.car._219;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MathUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.FgeString;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_123_OUTDOOR_TEMPERATURE_UNIT = "share_123_outdoor_temperature_unit";
   private static String TAG;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x25Data;
   private int[] m0x29Data;
   private int[] m0x30Data;
   private int[] m0x32Data;
   private int[] m0x33DataIndexOne;
   private int[] m0x33DataIndexTwo;
   private int[] m0xD1Data;
   private int[] m0xD2Data;
   private int[] m0xD8Data;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private DecimalFormat mDecimalFormat;
   private DecimalFormat mDecimalFormat0p0;
   private ID3[] mDiscId3s;
   private boolean mFrontStatus;
   private boolean mFrontViewBtnStatus;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private ID3[] mMusicId3s;
   private int mPageIndex = 0;
   private boolean mPanoramicStatusNow;
   private int mRadioBand;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private TimerUtil mTimerUtil;
   private boolean mTurnSignalStatus;

   private int getAirWhat() {
      int[] var3 = new int[6];
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      short var1 = 0;
      var3[0] = var2 & 254;
      var3[1] = var5[3];
      var3[2] = var5[4];
      var3[3] = var5[5];
      var2 = var5[6];
      var3[4] = var2 & 129;
      var3[5] = var5[7];
      int[] var4 = new int[]{var5[2] & 1, var2 & 4, var5[8], var5[9], var5[10]};
      if (!Arrays.equals(this.mAirFrontDataNow, var3)) {
         var1 = 1001;
      } else if (!Arrays.equals(this.mAirRearDataNow, var4)) {
         var1 = 1002;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var3, 6);
      this.mAirRearDataNow = Arrays.copyOf(var4, 5);
      return var1;
   }

   private int getBandData(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 2474:
            if (var1.equals("MW")) {
               var2 = 0;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 1;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
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
            return 4;
         case 1:
            return 3;
         case 2:
            return 4;
         case 3:
            return 1;
         case 4:
            return 2;
         default:
            return 0;
      }
   }

   private String getData(int[] var1, float var2, String var3) {
      int var6 = 0;
      int var5 = 0;

      int var4;
      for(var4 = 0; var5 < var1.length; ++var5) {
         var4 = (int)((double)var4 + (double)var1[var5] * Math.pow(2.0, (double)(var5 * 8)));
      }

      for(var5 = 1; var6 < var1.length * 8 - 1; ++var6) {
         var5 = (var5 << 1) + 1;
      }

      return var4 == var5 ? "- - -" : this.mDecimalFormat.format((double)((float)var4 * var2)) + var3;
   }

   private String getDistanceUnit(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? "" : " mile";
      } else {
         return " km";
      }
   }

   private int[] getFreqByte(String var1, String var2) {
      int[] var3 = new int[2];
      Log.d(TAG, "mCurrentFreq " + var2);
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            var3[0] = (byte)MathUtils.fm_thousands_hundreads(var2);
            var3[1] = (byte)MathUtils.tens_units(var2);
         }
      } else {
         var3[0] = (byte)MathUtils.am_thousands_hundreads(var2);
         var3[1] = (byte)MathUtils.am_tens_units(var2);
      }

      return var3;
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

   private String getFuelUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : " l/100km";
         } else {
            return " km/l";
         }
      } else {
         return " mpg";
      }
   }

   private String getOpenClose(boolean var1) {
      Context var3 = this.mContext;
      String var2;
      if (var1) {
         var2 = "open";
      } else {
         var2 = "close";
      }

      return CommUtil.getStrByResId(var3, var2);
   }

   private int getRange(int var1) {
      switch (var1) {
         case 0:
            return 60;
         case 1:
            return 10;
         case 2:
            return 12;
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
            return (var1 - 1) * 10;
         default:
            return 0;
      }
   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   private void initAmplifierData(Context var1) {
      boolean var2 = false;
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_VOL", 0);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_FR", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_LR", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_TRE", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_BASS", 0);
         var2 = SharePreUtil.getBoolValue(this.mContext, "CAN_162_LOUD", false);
      }

      TimerUtil var3 = new TimerUtil();
      var3.startTimer(new TimerTask(this, var2, var3) {
         Iterator iterator;
         final MsgMgr this$0;
         final boolean val$finalParamBool;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$finalParamBool = (boolean)var2;
            this.val$util = var3;
            byte var9 = (byte)GeneralAmplifierData.bandBass;
            byte var4 = (byte)(GeneralAmplifierData.frontRear + 7);
            byte var6 = (byte)(GeneralAmplifierData.leftRight + 7);
            byte var5 = (byte)GeneralAmplifierData.bandTreble;
            byte var7 = (byte)GeneralAmplifierData.volume;
            byte var8 = (byte)var2;
            this.iterator = Arrays.stream(new byte[][]{{22, 9, 19, var9, var4, var6, var5, 0}, {22, 9, 15, var7}, {22, 9, 17, var8}}).iterator();
         }

         public void run() {
            if (this.iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.iterator.next());
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private void initID3() {
      ID3[] var1 = new ID3[3];
      this.mDiscId3s = var1;
      var1[0] = new ID3(this, 2);
      this.mDiscId3s[1] = new ID3(this, 3);
      this.mDiscId3s[2] = new ID3(this, 4);
      var1 = new ID3[3];
      this.mMusicId3s = var1;
      var1[0] = new ID3(this, 2);
      this.mMusicId3s[1] = new ID3(this, 3);
      this.mMusicId3s[2] = new ID3(this, 4);
   }

   private boolean is0x08DataChange() {
      if (Arrays.equals(this.m0xD8Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD8Data = Arrays.copyOf(var1, var1.length);
         return true;
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

   private boolean is0x25DataChange() {
      if (Arrays.equals(this.m0x25Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x25Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x29DataChange() {
      if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x29Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x30DataChange() {
      if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x30Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x32DataChange() {
      if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x32Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33IndexOneDataChange() {
      if (Arrays.equals(this.m0x33DataIndexOne, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexOne = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33IndexTwoDataChange() {
      if (Arrays.equals(this.m0x33DataIndexTwo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexTwo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD1DataChange() {
      if (Arrays.equals(this.m0xD1Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD1Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xD2DataChange() {
      if (Arrays.equals(this.m0xD2Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xD2Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
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

   private void realKeyClick2(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private void realKeyClick20x96(int var1) {
      if (var1 != 17) {
         if (var1 != 65) {
            if (var1 != 34) {
               if (var1 == 35) {
                  this.realKeyClick4(this.mContext, 68);
               }
            } else {
               this.realKeyClick4(this.mContext, 139);
            }
         } else {
            this.realKeyClick4(this.mContext, 76);
         }
      } else {
         this.realKeyClick4(this.mContext, 77);
      }

   }

   private void reportID3Info(ID3[] var1, boolean var2) {
      (new Thread(this, var1, var2) {
         final MsgMgr this$0;
         final ID3[] val$id3s;
         final boolean val$isClean;

         {
            this.this$0 = var1;
            this.val$id3s = var2;
            this.val$isClean = var3;
         }

         public void run() {
            super.run();

            Exception var10000;
            label64: {
               int var3;
               ID3[] var4;
               boolean var10001;
               try {
                  var4 = this.val$id3s;
                  var3 = var4.length;
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break label64;
               }

               byte var2 = 0;
               int var1 = 0;

               while(true) {
                  if (var1 >= var3) {
                     return;
                  }

                  try {
                     if (var4[var1].isId3Change()) {
                        if (this.val$isClean) {
                           sleep(900L);
                        }
                        break;
                     }
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label64;
                  }

                  ++var1;
               }

               ID3[] var5;
               try {
                  var5 = this.val$id3s;
                  var3 = var5.length;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label64;
               }

               var1 = var2;

               while(true) {
                  if (var1 >= var3) {
                     return;
                  }

                  ID3 var10 = var5[var1];

                  try {
                     sleep(100L);
                     this.this$0.reportID3InfoFinal(var10.cmd, var10.id3);
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                     break;
                  }

                  ++var1;
               }
            }

            Exception var11 = var10000;
            var11.printStackTrace();
         }
      }).start();
   }

   private void reportID3InfoFinal(int var1, String var2) {
      byte var3 = (byte)var1;

      try {
         byte[] var5 = DataHandleUtils.exceptBOMHead(var2.getBytes("unicode"));
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, var3}, var5), 33));
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private void requestData() {
      this.getTimerUtil().startTimer(new TimerTask(this, new byte[][]{{22, -112, 50, 0}, {22, -112, 51, 0}, {22, -112, 37, 0}, {22, -112, -46, 0}}) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$requestCommands;

         {
            this.this$0 = var1;
            this.val$requestCommands = var2;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$requestCommands;
            if (var1 < var2.length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.this$0.getTimerUtil().stopTimer();
            }

         }
      }, 200L, 200L);
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LOW";
      } else if (var1 == 255) {
         return "HIGH";
      } else {
         return GeneralAirData.fahrenheit_celsius ? var1 + this.getTempUnitF(this.mContext) : (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }
   }

   private String resolveOutDoorTem() {
      GeneralAirData.fahrenheit_celsius = SharePreUtil.getBoolValue(this.mContext, "share_123_outdoor_temperature_unit", false);
      int var1 = this.mCanBusInfoInt[2];
      byte var2 = 1;
      boolean var5;
      if (DataHandleUtils.getIntFromByteWithBit(var1, 7, 1) == 1) {
         var5 = true;
      } else {
         var5 = false;
      }

      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
      StringBuilder var4 = new StringBuilder();
      if (var5) {
         var2 = -1;
      }

      return var4.append(var2 * var3).append(this.getTempUnitC(this.mContext)).toString();
   }

   private void sendMediaMsg(String var1, byte[][] var2) {
      (new Thread(this, var1, var2) {
         final MsgMgr this$0;
         final String val$mediaType;
         final byte[][] val$reports;

         {
            this.this$0 = var1;
            this.val$mediaType = var2;
            this.val$reports = var3;
         }

         public void run() {
            super.run();

            Exception var10000;
            label56: {
               String var4;
               boolean var10001;
               try {
                  var4 = this.val$mediaType;
               } catch (Exception var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label56;
               }

               int var1 = 0;
               int var2 = 0;
               if (var4 == null) {
                  label61: {
                     int var3;
                     byte[][] var12;
                     try {
                        var12 = this.val$reports;
                        var3 = var12.length;
                     } catch (Exception var8) {
                        var10000 = var8;
                        var10001 = false;
                        break label61;
                     }

                     var1 = var2;

                     while(true) {
                        if (var1 >= var3) {
                           return;
                        }

                        byte[] var5 = var12[var1];

                        try {
                           sleep(100L);
                           CanbusMsgSender.sendMsg(var5);
                        } catch (Exception var7) {
                           var10000 = var7;
                           var10001 = false;
                           break;
                        }

                        ++var1;
                     }
                  }
               } else {
                  label52: {
                     byte[][] var6;
                     try {
                        var6 = this.val$reports;
                        var2 = var6.length;
                     } catch (Exception var10) {
                        var10000 = var10;
                        var10001 = false;
                        break label52;
                     }

                     while(true) {
                        if (var1 >= var2) {
                           return;
                        }

                        byte[] var13 = var6[var1];

                        try {
                           sleep(100L);
                           MsgMgr var15 = this.this$0;
                           var15.sendMediaMsg(var15.mContext, this.val$mediaType, var13);
                        } catch (Exception var9) {
                           var10000 = var9;
                           var10001 = false;
                           break;
                        }

                        ++var1;
                     }
                  }
               }
            }

            Exception var14 = var10000;
            var14.printStackTrace();
         }
      }).start();
   }

   private void setAirData0x21() {
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      if (GeneralAirData.climate != DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
         GeneralAirData.climate = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         if (!GeneralAirData.climate) {
            if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
               this.finishActivity();
            }
         } else {
            AirActivity.mIsClickOpen = true;
            Intent var2 = new Intent(this.mContext, AirActivity.class);
            var2.setFlags(268435456);
            this.mContext.startActivity(var2);
         }
      }

      byte[] var3 = this.mCanBusInfoByte;
      var3[3] = (byte)(var3[3] & 239);
      var3[6] = (byte)(var3[6] & 191);
      if (!this.isAirMsgRepeat(var3)) {
         int var1 = this.getAirWhat();
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         if (this.isThree()) {
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
         }

         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[8]);
         GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
         this.updateAirActivity(this.mContext, var1);
      }
   }

   private void setBackLightStatus0x14() {
   }

   private void setCameraStatus0xD1() {
      if (this.is0xD1DataChange()) {
         Context var6 = this.mContext;
         String var7 = Constant.FCameraActivity.getClassName();
         boolean var3 = false;
         boolean var4 = SystemUtil.isForeground(var6, new String[]{var7, Constant.AuxInActivity.getClassName()});
         boolean var5 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         boolean var2 = var4;
         if (this.mFrontViewBtnStatus) {
            var2 = var4;
            if (!var5) {
               this.switchFCamera(this.mContext, var4 ^ true);
               var2 = var4 ^ true;
            }
         }

         this.mFrontViewBtnStatus = var5;
         byte var1;
         if (SharePreUtil.getBoolValue(this.mContext, "share_41_front_camera_switch", false)) {
            var1 = 3;
         } else {
            var1 = 7;
         }

         if ((1 << var1 & this.mCanBusInfoInt[3]) != 0) {
            var3 = true;
         }

         if (this.mTurnSignalStatus ^ var3) {
            this.mTurnSignalStatus = var3;
            if (var2 ^ var3) {
               this.switchFCamera(this.mContext, var3);
            }
         }
      }

   }

   private void setCarAmplifierStatus0x31() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.frontRear = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 8) - 9;
      GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8) - 9;
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
      GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 8);
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 8);
      GeneralAmplifierData.megaBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 8);
      this.saveAmplifierData(this.mContext, this.mCanId);
      this.updateAmplifierActivity((Bundle)null);
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(12, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 8)));
      var1.add(new SettingUpdateEntity(12, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 8)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarSpeedStatus0x16() {
      ArrayList var5 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      DecimalFormat var2 = this.mDecimalFormat0p0;
      int[] var4 = this.mCanBusInfoInt;
      int var1 = var4[3];
      var5.add(new DriverUpdateEntity(0, 6, var3.append(var2.format((long)(var4[2] | var1 << 8))).append(" km/h").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarSpeedStatus0x71() {
      ArrayList var5 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      DecimalFormat var4 = this.mDecimalFormat0p0;
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[3];
      var5.add(new DriverUpdateEntity(0, 7, var2.append(var4.format((long)(var3[2] | var1 << 8))).append(" rpm").toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarStatus0xD0() {
   }

   private void setCarSystemStatus0x40() {
      if (this.isPanoramicStatusChange()) {
         this.forceReverse(this.mContext, this.mPanoramicStatusNow);
      }

      boolean var1 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      String var3 = "_288_0x31_setting7";
      String var2;
      if (var1) {
         var2 = "_288_0x31_setting7";
      } else {
         var2 = "_288_0x31_setting8";
      }

      if (!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var3 = "_288_0x31_setting8";
      }

      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      String var5 = "open";
      String var4;
      if (var1) {
         var4 = "open";
      } else {
         var4 = "close";
      }

      if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var5 = "close";
      }

      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(6, 5, var2));
      var6.add(new SettingUpdateEntity(6, 6, var3));
      var6.add(new SettingUpdateEntity(6, 7, var4));
      var6.add(new SettingUpdateEntity(6, 8, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCompassData0xD2() {
      if (this.is0xD2DataChange()) {
         ArrayList var2 = new ArrayList();
         Context var3 = this.mContext;
         String var1;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            var1 = "compass_calibrating";
         } else {
            var1 = "compass_calibration_finish";
         }

         var1 = CommUtil.getStrByResId(var3, var1);
         String var5 = Integer.toString(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
         String var4 = (float)(this.mCanBusInfoInt[3] * 3) / 2.0F + "°";
         var2.add((new SettingUpdateEntity(8, 1, var1)).setValueStr(true));
         var2.add((new SettingUpdateEntity(8, 2, var5)).setValueStr(true).setProgress(Integer.valueOf(var5) - 1));
         var2.add((new SettingUpdateEntity(8, 3, var4)).setValueStr(true));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDoorData0x24() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      this.mRightFrontRec = var1;
      GeneralDoorData.isRightFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      this.mLeftFrontRec = var1;
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      this.mRightRearRec = var1;
      GeneralDoorData.isRightRearDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.mLeftRearRec = var1;
      GeneralDoorData.isLeftRearDoorOpen = var1;
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setDriveData0x33() {
      ArrayList var4 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      int var1 = DataHandleUtils.rangeNumber(var2[0] * 256 + var2[1], 0, 3000) / 10;
      var4.add(new DriverUpdateEntity(1, 0, (new DecimalFormat("0.0")).format((long)var1) + "L/100KM"));
      var2 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(var2[2] * 256 + var2[3], 0, 2000);
      String var5 = (new DecimalFormat("0.0")).format((long)var1) + "KM";
      String var3 = "--";
      if (var1 == 65535) {
         var5 = "--";
      }

      var4.add(new DriverUpdateEntity(1, 5, var5));
      var2 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(var2[4] * 256 + var2[5], 0, 6000);
      var5 = (new DecimalFormat("0.0")).format((long)var1) + "KM";
      if (var1 == 65535) {
         var5 = var3;
      }

      var4.add(new DriverUpdateEntity(1, 9, var5));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData0x34() {
      ArrayList var4 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      int var1 = DataHandleUtils.rangeNumber(var2[0] * 256 + var2[1], 0, 3000) / 10;
      var4.add(new DriverUpdateEntity(1, 3, (new DecimalFormat("0.0")).format((long)var1) + "L/100KM"));
      var2 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(var2[2] * 256 + var2[3], 0, 250);
      String var5 = (new DecimalFormat("0.0")).format((long)var1) + "KM/H";
      String var3 = "--";
      if (var1 == 65535) {
         var5 = "--";
      }

      var4.add(new DriverUpdateEntity(1, 8, var5));
      var2 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(var2[4] * 256 + var2[5], 0, 9999);
      var5 = (new DecimalFormat("0.0")).format((long)var1) + "KM";
      if (var1 == 65535) {
         var5 = var3;
      }

      var4.add(new DriverUpdateEntity(1, 4, var5));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      var2 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var2[4], var2[5]));
   }

   private void setDriveData0x35() {
      ArrayList var4 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      int var1 = DataHandleUtils.rangeNumber(var2[0] * 256 + var2[1], 0, 3000) / 10;
      var4.add(new DriverUpdateEntity(1, 3, (new DecimalFormat("0.0")).format((long)var1) + "L/100KM"));
      var2 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(var2[2] * 256 + var2[3], 0, 250);
      String var5 = (new DecimalFormat("0.0")).format((long)var1) + "KM/H";
      String var3 = "--";
      if (var1 == 65535) {
         var5 = "--";
      }

      var4.add(new DriverUpdateEntity(1, 8, var5));
      var2 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(var2[4] * 256 + var2[5], 0, 9999);
      var5 = (new DecimalFormat("0.0")).format((long)var1) + "KM";
      if (var1 == 65535) {
         var5 = var3;
      }

      var4.add(new DriverUpdateEntity(1, 4, var5));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadarData0x23() {
      if (this.is0x23DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setFuelData0x33() {
      int var2 = this.mCanBusInfoInt[2];
      String var6;
      int[] var7;
      int[] var12;
      if (var2 != 1) {
         if (var2 == 2 && this.is0x33IndexTwoDataChange()) {
            String var5 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 4, 2));
            var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 6, 1));
            ArrayList var4 = new ArrayList();
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 1, this.getData(new int[]{var7[5], var7[4], var7[3]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 2, this.getData(new int[]{var7[7], var7[6]}, 0.1F, var5)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 4, this.getData(new int[]{var7[10], var7[9], var7[8]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 5, this.getData(new int[]{var7[12], var7[11]}, 0.1F, var5)));
            var7 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 7, this.getData(new int[]{var7[15], var7[14], var7[13]}, 0.1F, var6)));
            var12 = this.mCanBusInfoInt;
            var4.add(new DriverUpdateEntity(1, 8, this.getData(new int[]{var12[17], var12[16]}, 0.1F, var5)));
            this.updateGeneralDriveData(var4);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else if (this.is0x33IndexOneDataChange()) {
         String var9 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2));
         String var8 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 2, 2));
         String var14 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2));
         var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1));
         String var10 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1));
         var2 = this.getRange(this.mCanBusInfoInt[16]);
         ArrayList var11 = new ArrayList();
         int var3 = this.mCanBusInfoInt[3];
         float var1 = (float)var2;
         var11.add(new DriverUpdateEntity(0, 0, this.getData(new int[]{var3}, var1 * 0.04761905F, var9)));
         int[] var15 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 1, this.getData(new int[]{var15[5], var15[4]}, 0.1F, var8)));
         var15 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 2, this.getData(new int[]{var15[7], var15[6]}, 0.1F, var8)));
         int[] var13 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 3, this.getData(new int[]{var13[9], var13[8]}, 0.1F, var14)));
         var7 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 4, this.getData(new int[]{var7[12], var7[11], var7[10]}, 0.1F, var6)));
         var12 = this.mCanBusInfoInt;
         var11.add(new DriverUpdateEntity(0, 5, this.getData(new int[]{var12[14], var12[13]}, 1.0F, var10)));
         this.updateGeneralDriveData(var11);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setParkAssistData0x25() {
      if (this.is0x25DataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(9, 0, this.getOpenClose(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])))).setValueStr(true));
         var1.add((new SettingUpdateEntity(9, 1, this.getOpenClose(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])))).setValueStr(true));
         var1.add((new SettingUpdateEntity(9, 2, this.getOpenClose(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])))).setValueStr(true));
         var1.add((new SettingUpdateEntity(9, 3, this.getOpenClose(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])))).setValueStr(true));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setRearRadarData0x22() {
      if (this.is0x22DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setTrackData0x29() {
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleSetupData0x32() {
      if (this.is0x32DataChange()) {
         ArrayList var2 = new ArrayList();
         switch (this.mCanBusInfoInt.length) {
            case 12:
               var2.add(new SettingUpdateEntity(5, 6, DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2), 0, 2)));
            case 10:
               var2.add(new SettingUpdateEntity(5, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2)));
               var2.add(new SettingUpdateEntity(5, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1)));
               var2.add(new SettingUpdateEntity(5, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1)));
               var2.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2)));
               var2.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1)));
               var2.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1)));
               var2.add(new SettingUpdateEntity(6, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
               var2.add(new SettingUpdateEntity(6, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)));
               var2.add(new SettingUpdateEntity(7, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1)));
               var2.add(new SettingUpdateEntity(7, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1)));
               var2.add(new SettingUpdateEntity(6, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1)));
               var2.add(new SettingUpdateEntity(6, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1)));
            case 8:
               var2.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
               var2.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1)));
               var2.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1)));
               var2.add(new SettingUpdateEntity(5, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)));
               var2.add(new SettingUpdateEntity(5, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2)));
               var2.add(new SettingUpdateEntity(5, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2)));
            case 7:
               var2.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2)));
               var2.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1)));
               var2.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
               var2.add(new SettingUpdateEntity(4, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)));
               var2.add(new SettingUpdateEntity(4, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)));
               var2.add(new SettingUpdateEntity(4, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)));
               var2.add(new SettingUpdateEntity(4, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
            case 6:
               var2.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
               var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
               var2.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
               var2.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
               var2.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3)));
            case 5:
               var2.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
               var2.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
               var2.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
               var2.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)));
               var2.add(new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)));
            case 4:
               var2.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3)));
               var2.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)));
               var2.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
            case 3:
               int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
               var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
               var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
               var2.add((new SettingUpdateEntity(0, 2, var1 - 5)).setProgress(var1));
            case 9:
            case 11:
            default:
               this.updateGeneralSettingData(var2);
               this.updateSettingActivity((Bundle)null);
         }
      }

   }

   private void setVersionInfo0x30() {
      if (this.is0x30DataChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      Log.d("mww", "setWheelKey0x20 " + FgeString.bytetoHexString((byte)this.mCanBusInfoInt[2]));
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 129) {
                              if (var1 != 130) {
                                 if (var1 != 134) {
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
                                          this.realKeyClick2(189);
                                          break;
                                       default:
                                          switch (var1) {
                                             case 22:
                                                this.realKeyClick2(49);
                                                break;
                                             case 23:
                                                this.realKeyClick2(52);
                                                break;
                                             case 24:
                                                this.realKeyClick2(21);
                                                break;
                                             case 25:
                                                this.realKeyClick2(20);
                                                break;
                                             default:
                                                switch (var1) {
                                                   case 145:
                                                      this.realKeyClick2(3);
                                                      break;
                                                   case 146:
                                                      this.realKeyClick2(45);
                                                      break;
                                                   case 147:
                                                      this.realKeyClick2(46);
                                                      break;
                                                   case 148:
                                                      this.realKeyClick2(47);
                                                      break;
                                                   case 149:
                                                      this.realKeyClick2(48);
                                                      break;
                                                   case 150:
                                                      Log.d("mww", "22 setWheelKey0x20 " + FgeString.bytetoHexString((byte)this.mCanBusInfoInt[2]));
                                                      this.realKeyClick20x96(this.mCanBusInfoInt[3]);
                                                }
                                          }
                                    }
                                 } else {
                                    this.realKeyClick2(3);
                                 }
                              } else {
                                 this.realKeyClick2(8);
                              }
                           } else {
                              this.realKeyClick2(7);
                           }
                        } else {
                           this.realKeyClick2(46);
                        }
                     } else {
                        this.realKeyClick2(45);
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

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      String var1 = SourceConstantsDef.SOURCE_ID.AUX1.name();
      byte[] var2 = new byte[]{22, -61, 0, 0, 0, 0, 0, 0};
      this.sendMediaMsg(var1, new byte[][]{{22, -64, 7, 48}, var2});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 1}, 35, 32));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      var1 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var1), 35, 32);
      byte[] var4 = new byte[]{22, -61, 0, 0, 0, 0, 0, 0};
      this.sendMediaMsg((String)null, new byte[][]{{22, -64, 5, 64}, var4, var1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      var1 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var1), 35, 32);
      byte[] var4 = new byte[]{22, -61, 0, 0, 0, 0, 0, 0};
      this.sendMediaMsg((String)null, new byte[][]{{22, -64, 5, 64}, var4, var1});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 2) {
         if (var3 != 8) {
            if (var3 != 41) {
               if (var3 != 56) {
                  switch (var3) {
                     case 51:
                        this.setDriveData0x33();
                        break;
                     case 52:
                        this.setDriveData0x34();
                        break;
                     case 53:
                        this.setDriveData0x35();
                        break;
                     case 54:
                        this.setData0x36();
                  }
               } else {
                  this.setData0x38();
               }
            } else {
               this.setData0x29();
            }
         } else {
            this.setData0x08();
         }
      } else {
         this.setData0x02();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      short var3;
      if (var2) {
         var3 = 128;
      } else {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 9, 15, (byte)(var1 | var3)});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (!var10) {
         var8 = var5 | 128;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte)var8, (byte)var6, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         var4 = var5;
      }

      var3 = DataHandleUtils.rangeNumber(var4, 0, 99);
      int[] var16 = this.getTime(var2);
      byte var14 = (byte)var3;
      var1 = (byte)var16[1];
      byte var15 = (byte)var16[2];
      this.sendMediaMsg(SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[][]{{22, 9, 1, var14, 0, 0, var1, var15}});
      this.mDiscId3s[0].id3 = var11;
      this.mDiscId3s[1].id3 = var12;
      this.mDiscId3s[2].id3 = var13;
      this.reportID3Info(this.mDiscId3s, false);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.mDecimalFormat0p0 = new DecimalFormat("0.0");
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mRadioBand = 1;
      this.mDecimalFormat = new DecimalFormat("0.0");
      this.requestData();
      this.initID3();
      this.initAmplifierData(var1);
   }

   public boolean isFour() {
      boolean var1;
      if (this.getCurrentCanDifferentId() == 3) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isOne() {
      boolean var1;
      if (this.getCurrentCanDifferentId() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isThree() {
      boolean var1;
      if (this.getCurrentCanDifferentId() == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isTwo() {
      int var1 = this.getCurrentCanDifferentId();
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mMusicId3s[0].id3 = "";
      this.mMusicId3s[1].id3 = "";
      this.mMusicId3s[2].id3 = "";
      this.reportID3Info(this.mMusicId3s, true);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Handler.INSTANCE.sendMusicOrVideoData(var1, var4, DataHandleUtils.getMsbLsbResult(var9, var3), var6, var7);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      try {
         Handler.INSTANCE.sendRadioData(var2, var3);
      } catch (Exception var6) {
         Log.e("radioInfoChange", Log.getStackTraceString(var6));
      }

   }

   protected void sendDiscEjectMsg(Context var1) {
      super.sendDiscEjectMsg(var1);
      this.mDiscId3s[0].id3 = "";
      this.mDiscId3s[1].id3 = "";
      this.mDiscId3s[2].id3 = "";
      this.reportID3Info(this.mDiscId3s, true);
   }

   public void setData0x01() {
      HzbhdLog.d(TAG, "setData0x01 " + this.mCanBusInfoInt[2]);
   }

   public void setData0x02() {
      HzbhdLog.d(TAG, KeysMap.keymaps.get(this.mCanBusInfoInt[2]) + " ,  " + this.mCanBusInfoInt[2]);
      if (KeysMap.keymaps.containsKey(this.mCanBusInfoInt[2])) {
         this.realKeyClick2((Integer)KeysMap.keymaps.get(this.mCanBusInfoInt[2]));
      }

   }

   public void setData0x08() {
      HzbhdLog.d(TAG, "setData0x08 " + this.mCanBusInfoInt[2]);
      if (this.is0x08DataChange()) {
         int[] var4 = this.mCanBusInfoInt;
         int var3 = var4[2];
         int var2 = var4[3];
         int var1 = var4[4];
         RadarInfoUtil.setFrontRadarLocationData(5, var3, var2, var1, var1);
         var4 = this.mCanBusInfoInt;
         var3 = var4[5];
         var2 = var4[6];
         var1 = var4[7];
         RadarInfoUtil.setRearRadarLocationData(5, var3, var2, var1, var1);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   public void setData0x29() {
      HzbhdLog.d(TAG, "setData0x29 " + this.mCanBusInfoInt[2]);
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5450, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   public void setData0x33() {
      HzbhdLog.d(TAG, "setData0x33 " + this.mCanBusInfoInt[2]);
   }

   public void setData0x34() {
      HzbhdLog.d(TAG, "setData0x34 " + this.mCanBusInfoInt[2]);
   }

   public void setData0x35() {
      HzbhdLog.d(TAG, "setData0x35 " + this.mCanBusInfoInt[2]);
   }

   public void setData0x36() {
      HzbhdLog.d(TAG, "setData0x36 " + this.mCanBusInfoInt[2]);
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   public void setData0x38() {
      HzbhdLog.d(TAG, "setData0x38 " + this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.mPageIndex = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      this.updateDoorView(this.mContext);
   }

   public void setSourceMode() {
      SourceConstantsDef.SOURCE_ID var2 = FutureUtil.instance.getCurrentValidSource();
      int var1 = null.$SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID[var2.ordinal()];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, 9, 12});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 9, 2});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 9, 1});
      }

   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void updateAmpUi(Context var1) {
      this.updateAmplifierActivity((Bundle)null);
   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      Handler.INSTANCE.sendMusicOrVideoData(var1, var4, DataHandleUtils.getMsbLsbResult(var9, var3), var6, var7);
   }

   private class ID3 {
      private int cmd;
      private String id3;
      private String record;
      final MsgMgr this$0;

      private ID3(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.cmd = var2;
         this.id3 = "";
         this.record = "";
      }

      // $FF: synthetic method
      ID3(MsgMgr var1, int var2, Object var3) {
         this(var1, var2);
      }

      private boolean isId3Change() {
         if (this.record.equals(this.id3)) {
            return false;
         } else {
            this.record = this.id3;
            return true;
         }
      }
   }
}
