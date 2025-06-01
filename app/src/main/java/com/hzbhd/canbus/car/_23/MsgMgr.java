package com.hzbhd.canbus.car._23;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_23_AMPLIFIER_ASL = "share_23_amplifier_asl";
   static final int SHARE_23_AMPLIFIER_BAND_OFFSET = 5;
   static final String SHARE_23_AMPLIFIER_SURROUND = "share_23_amplifier_surround";
   static final String SHARE_23_IS_SUPPORT_PANORAMIC = "share_23_is_support_panoramic";
   static final String SHARE_23_LANGUAGE = "share_23_language";
   private static int mKnobNow;
   private boolean isFinish = false;
   private int m0x72WheelKeyData;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private int[] mAmplifierDataNow;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private int mDifferent;
   private int[] mDriveDataNow;
   private int mEachId;
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private int mMediaStatusNow;
   private int mOutDoorTempDataNow;
   private int mPanoramicStatusNow;
   private MyPanoramicView mPanoramicView;
   private int[] mRadarDataNow;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private TimerHelper mTimerHelper;
   private byte[] mTrackDataNow;
   private String mUsbSongArtistNow;
   private String mUsbSongTitleNow;
   private byte[] mVersionInfoNow;

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

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_auto_wind = false;
      GeneralAirData.front_right_auto_wind = false;
      GeneralAirData.rear_left_blow_window = false;
      GeneralAirData.rear_left_blow_head = false;
      GeneralAirData.rear_left_blow_foot = false;
      GeneralAirData.rear_right_blow_window = false;
      GeneralAirData.rear_right_blow_head = false;
      GeneralAirData.rear_right_blow_foot = false;
      GeneralAirData.rear_left_auto_wind = false;
      GeneralAirData.rear_right_auto_wind = false;
   }

   private int getAirWhat() {
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      int var3 = var5[3];
      int[] var4 = new int[8];
      short var1 = 0;
      var4[0] = var2 & 239;
      var4[1] = var3 & 127;
      var4[2] = var5[4];
      var4[3] = var5[5];
      var4[4] = var5[6];
      var4[5] = var5[7];
      var4[6] = var5[8];
      var4[7] = var5[9];
      int[] var6 = new int[]{var2 & 16, var3 & 128, var5[10], var5[11], var5[12]};
      if (!Arrays.equals(this.mAirFrontDataNow, var4)) {
         var1 = 1001;
      } else if (!Arrays.equals(this.mAirRearDataNow, var6)) {
         var1 = 1002;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var4, 8);
      this.mAirRearDataNow = Arrays.copyOf(var6, 5);
      return var1;
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
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 2;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 3;
            }
      }

      switch (var2) {
         case 0:
            return 4;
         case 1:
            return 5;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            return 1;
      }
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

   private String getBandUnit(String var1) {
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
         case 1:
            return "KHz";
         case 2:
         case 3:
         case 4:
            return "MHz";
         default:
            return "";
      }
   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private TimerHelper getTimerHelper() {
      if (this.mTimerHelper == null) {
         this.mTimerHelper = new TimerHelper(this);
      }

      return this.mTimerHelper;
   }

   private void initAmplifier(Context var1) {
      this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
   }

   private boolean isAmplifierDataChange() {
      if (Arrays.equals(this.mAmplifierDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isDriveDataChange() {
      int[] var1 = new int[4];
      int[] var2 = this.mCanBusInfoInt;
      var1[0] = var2[2];
      var1[1] = var2[3];
      var1[2] = var2[10];
      var1[3] = var2[11];
      if (Arrays.equals(this.mDriveDataNow, var1)) {
         return false;
      } else {
         this.mDriveDataNow = Arrays.copyOf(var1, 4);
         return true;
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return !GeneralAirData.power;
      } else {
         return false;
      }
   }

   private boolean isMediaStatusChange() {
      int var1 = this.mCanBusInfoInt[2];
      if (this.mMediaStatusNow == var1) {
         return false;
      } else {
         this.mMediaStatusNow = var1;
         return true;
      }
   }

   private boolean isOutDoorTempChange() {
      int var1 = this.mOutDoorTempDataNow;
      int var2 = this.mCanBusInfoInt[13];
      if (var1 == var2) {
         return false;
      } else {
         this.mOutDoorTempDataNow = var2;
         return true;
      }
   }

   private boolean isPanoramicStatusChange() {
      int var1 = this.mCanBusInfoInt[2];
      if (this.mPanoramicStatusNow == var1) {
         return false;
      } else {
         this.mPanoramicStatusNow = var1;
         return true;
      }
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackDataChange() {
      byte[] var1 = new byte[2];
      byte[] var2 = this.mCanBusInfoByte;
      var1[0] = var2[6];
      var1[1] = var2[7];
      if (Arrays.equals(this.mTrackDataNow, var1)) {
         return false;
      } else {
         this.mTrackDataNow = Arrays.copyOf(var1, 2);
         return true;
      }
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

   private void myRealKeyLongClick2(Context var1, int var2) {
      if (var2 == 2 && System.getInt(var1.getContentResolver(), "gpioLevel", 1) == 0) {
         var2 = this.mDifferent;
         if (var2 != 13 && var2 != 16) {
            CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -3, 2, 0});
         }

      } else {
         this.realKeyLongClick2(var1, var2);
      }
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private void reportID3Info(byte var1, byte var2, String var3, String var4, boolean var5, String var6) {
      (new Thread(this, var5, var3, var4, var1, var6, var2) {
         final MsgMgr this$0;
         final String val$artist;
         final byte val$artistHead;
         final String val$charsetName;
         final boolean val$isClean;
         final String val$title;
         final byte val$titleHead;

         {
            this.this$0 = var1;
            this.val$isClean = var2;
            this.val$title = var3;
            this.val$artist = var4;
            this.val$titleHead = var5;
            this.val$charsetName = var6;
            this.val$artistHead = var7;
         }

         public void run() {
            super.run();

            try {
               if (this.val$isClean) {
                  sleep(1000L);
               }

               if (!this.val$title.equals(this.this$0.mUsbSongTitleNow) || !this.val$artist.equals(this.this$0.mUsbSongArtistNow)) {
                  this.this$0.mUsbSongTitleNow = this.val$title;
                  this.this$0.mUsbSongArtistNow = this.val$artist;
                  sleep(100L);
                  this.this$0.reportID3InfoFinal(this.val$titleHead, this.val$title, this.val$charsetName, 34);
                  sleep(100L);
                  this.this$0.reportID3InfoFinal(this.val$artistHead, this.val$artist, this.val$charsetName, 18);
               }
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private void reportID3InfoFinal(byte var1, String var2, String var3, int var4) {
      try {
         byte[] var6 = DataHandleUtils.exceptBOMHead(var2.getBytes(var3));
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, var1}, var6), var4));
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private String resolveFrontAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void resolveKnob() {
      int var2 = FutureUtil.instance.getVolumeValue();
      int var1 = this.mCanBusInfoInt[3];
      int var3 = var1 - mKnobNow;
      if (var3 != 0) {
         mKnobNow = var1;
         Log.i("Ljq", "resolveKnob: mKnobNow: " + mKnobNow);
         byte var4;
         if (var3 > 0) {
            var4 = 7;
            if (var2 >= 40) {
               return;
            }
         } else {
            var4 = 8;
            if (var2 <= 0) {
               return;
            }
         }

         for(var2 = 0; var2 < Math.abs(var3); ++var2) {
            this.realKeyClick4(var4);
         }

      }
   }

   private String resolveOutDoorTem() {
      return (float)this.mCanBusInfoInt[13] * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private String resolveRearAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else if (var1 >= 34 && var1 <= 64) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = " ";
      }

      return var2;
   }

   private void sendAmplifierData(int var1, int var2) {
      if (var2 != 0) {
         this.isFinish = false;
         this.getTimerHelper().startTimer(new TimerTask(this, var2, var1) {
            int i;
            int step;
            final MsgMgr this$0;
            final int val$cmd;
            final int val$data;

            {
               this.this$0 = var1;
               this.val$data = var2;
               this.val$cmd = var3;
               this.i = 0;
               this.step = var2 / Math.abs(var2);
            }

            public void run() {
               int var1 = this.i++;
               if (var1 < Math.abs(this.val$data)) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte)this.val$cmd, (byte)this.step});
               } else {
                  this.this$0.isFinish = true;
                  this.this$0.getTimerHelper().stopTimer();
               }

            }
         }, 0L, 50L);
      }

   }

   private void sendAmplifierVol(int var1) {
      this.isFinish = false;
      this.getTimerHelper().startTimer(new TimerTask(this, var1) {
         final MsgMgr this$0;
         final int val$data;
         int vol;

         {
            this.this$0 = var1;
            this.val$data = var2;
            this.vol = var2;
         }

         public void run() {
            int var1 = this.vol - 5;
            this.vol = var1;
            if (var1 > 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, 5});
            } else {
               this.this$0.getTimerHelper().stopTimer();
               var1 = this.vol + 5;
               this.vol = var1;
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var1});
               this.this$0.isFinish = true;
            }

         }
      }, 0L, 50L);
   }

   private void setAirData0x31() {
      int var1 = this.getAirWhat();
      byte[] var3 = this.bytesExpectOneByte(this.mCanBusInfoByte, 13);
      this.setOutDoorTem();
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var3)) {
         if (!this.isFirst()) {
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
               GeneralAirData.in_out_auto_cycle = 2;
            } else {
               GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            }

            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
            this.cleanAllBlow();
            int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 != 5) {
                        if (var2 != 6) {
                           switch (var2) {
                              case 11:
                                 GeneralAirData.front_left_blow_window = true;
                                 break;
                              case 12:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_left_blow_foot = true;
                                 break;
                              case 13:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 break;
                              case 14:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 GeneralAirData.front_left_blow_foot = true;
                           }
                        } else {
                           GeneralAirData.front_left_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_auto_wind = true;
            }

            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 != 5) {
                        if (var2 != 6) {
                           switch (var2) {
                              case 11:
                                 GeneralAirData.front_right_blow_window = true;
                                 break;
                              case 12:
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_right_blow_foot = true;
                                 break;
                              case 13:
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_right_blow_head = true;
                                 break;
                              case 14:
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_right_blow_head = true;
                                 GeneralAirData.front_right_blow_foot = true;
                           }
                        } else {
                           GeneralAirData.front_right_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_window = true;
               }
            } else {
               GeneralAirData.front_right_auto_wind = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[9]);
            var2 = this.mCanBusInfoInt[10];
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 == 3) {
                     GeneralAirData.rear_left_blow_head = true;
                     GeneralAirData.rear_left_blow_foot = true;
                     GeneralAirData.rear_right_blow_head = true;
                     GeneralAirData.rear_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_right_blow_head = true;
               }
            } else {
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_right_blow_foot = true;
            }

            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
            GeneralAirData.rear_temperature = this.resolveRearAirTemp(this.mCanBusInfoInt[12]);
            this.updateAirActivity(this.mContext, var1);
         }
      }
   }

   private void setAmplifierData0xA6() {
      if (this.isAmplifierDataChange()) {
         GeneralAmplifierData.volume = this.mCanBusInfoByte[2];
         GeneralAmplifierData.leftRight = this.mCanBusInfoByte[3];
         GeneralAmplifierData.frontRear = this.mCanBusInfoByte[4];
         GeneralAmplifierData.bandBass = this.mCanBusInfoByte[5] + 5;
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoByte[6] + 5;
         GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[7] + 5;
         this.saveAmplifierData(this.mContext, this.mCanId);
         this.updateAmplifierActivity((Bundle)null);
         SharePreUtil.setStringValue(this.mContext, "23_0xA6", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1)));
         var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1)));
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 3);
         var2.add((new SettingUpdateEntity(0, 2, var1 + "")).setValueStr(true));
         var2.add((new SettingUpdateEntity(0, 3, var1 + "")).setValueStr(true));
         var2.add((new SettingUpdateEntity(0, 4, this.mCanBusInfoByte[9] + "")).setValueStr(true));
         var2.add((new SettingUpdateEntity(0, 5, this.mCanBusInfoByte[9] + "")).setValueStr(true));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDoorData0x12() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.mLeftFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      this.mRightFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      this.mLeftRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      this.mRightRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setKeyKonb0x74() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick4(0);
            break;
         case 1:
            this.realKeyClick4(45);
            break;
         case 2:
            this.realKeyClick4(46);
            break;
         case 3:
            this.realKeyClick4(47);
            break;
         case 4:
            this.realKeyClick4(48);
            break;
         case 5:
            this.realKeyClick4(4);
            break;
         case 6:
            this.realKeyClick4(59);
            break;
         case 7:
            this.realKeyClick4(141);
            break;
         case 8:
            this.realKeyClick4(140);
            break;
         case 9:
            this.realKeyClick4(49);
            break;
         case 10:
            this.realKeyClick4(50);
            break;
         case 11:
            this.realKeyClick4(52);
            break;
         case 12:
            this.realKeyClick4(128);
      }

      this.resolveKnob();
   }

   private void setKeyTrack0x72(Context var1) {
      if (this.isDriveDataChange()) {
         ArrayList var6 = new ArrayList();
         var6.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " km/h"));
         StringBuilder var5 = new StringBuilder();
         int[] var4 = this.mCanBusInfoInt;
         var6.add(new DriverUpdateEntity(0, 1, var5.append(var4[10] * 256 + var4[11]).append(" rpm").toString()));
         this.updateGeneralDriveData(var6);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

      int var2 = this.m0x72WheelKeyData;
      int var3 = this.mCanBusInfoInt[4];
      if (var2 != var3) {
         this.m0x72WheelKeyData = var3;
         if (var3 != 0) {
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 4) {
                     if (var3 != 5) {
                        if (var3 != 6) {
                           if (var3 != 10) {
                              switch (var3) {
                                 case 13:
                                    var2 = this.mDifferent;
                                    if (var2 != 5 && var2 != 11 && var2 != 12 && var2 != 13 && var2 != 15 && var2 != 16) {
                                       this.realKeyLongClick2(var1, 45);
                                    } else {
                                       this.realKeyLongClick2(var1, 46);
                                    }
                                    break;
                                 case 14:
                                    var2 = this.mDifferent;
                                    if (var2 != 5 && var2 != 11 && var2 != 12 && var2 != 13 && var2 != 15 && var2 != 16) {
                                       this.realKeyLongClick2(var1, 46);
                                    } else {
                                       this.realKeyLongClick2(var1, 45);
                                    }
                                    break;
                                 case 15:
                                    this.realKeyLongClick2(var1, 49);
                                    break;
                                 case 16:
                                    this.realKeyLongClick2(var1, 50);
                              }
                           } else {
                              this.myRealKeyLongClick2(var1, 2);
                           }
                        } else {
                           var2 = this.mDifferent;
                           if (var2 != 1 && var2 != 2 && var2 != 5 && var2 != 11 && var2 != 12 && var2 != 14) {
                              this.realKeyLongClick2(var1, 15);
                           } else {
                              this.realKeyLongClick2(var1, 188);
                           }
                        }
                     } else {
                        var2 = this.mDifferent;
                        if (var2 != 3 && var2 != 11 && var2 != 12 && var2 != 13 && var2 != 16) {
                           if (var2 != 5 && var2 != 14 && var2 != 15) {
                              this.realKeyLongClick2(var1, 14);
                           } else {
                              this.realKeyLongClick2(var1, 187);
                           }
                        } else {
                           this.realKeyLongClick2(var1, 201);
                        }
                     }
                  } else {
                     this.realKeyLongClick2(var1, 187);
                  }
               } else {
                  this.realKeyLongClick2(var1, 8);
               }
            } else {
               this.realKeyLongClick2(var1, 7);
            }
         } else {
            this.realKeyLongClick2(var1, 0);
         }
      }

      if (this.isTrackDataChange()) {
         byte[] var7 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var7[7], var7[6], 0, 500, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setMediaStatus0xE0() {
      if (this.isMediaStatusChange()) {
         int var1 = this.mCanBusInfoInt[2];
         if (var1 != 0) {
            switch (var1) {
               case 32:
                  this.changeBandAm1();
                  break;
               case 33:
                  this.changeBandFm1();
                  break;
               case 34:
                  this.changeBandFm2();
                  break;
               case 35:
                  this.realKeyClick4(130);
                  break;
               case 36:
                  this.realKeyClick4(59);
                  break;
               case 37:
                  this.realKeyClick4(140);
                  break;
               case 38:
                  this.realKeyClick4(141);
                  break;
               case 39:
                  this.realKeyClick4(61);
            }
         } else {
            this.enterNoSource();
            this.realKeyClick4(52);
         }
      }

   }

   private void setOutDoorTem() {
      if (this.isOutDoorTempChange()) {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      }

   }

   private void setParkAssistance0xE8() {
      MyPanoramicView var1 = (MyPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      this.mPanoramicView = var1;
      var1.mTvTipsStatus = this.mCanBusInfoInt[2];
      this.mPanoramicView.mPageNumber = this.mCanBusInfoInt[6];
      this.mPanoramicView.mBtnStartStatus = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIbPaStatus = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIbLeftDownStatus = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIbRightDownStatus = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIbRightStatus = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIbLeftStatus = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIbDownStatus = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIbUpStatus = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
      this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[8];
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

   private void setRadarData0x41() {
      if (this.isRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mDisableData = 255;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationDataType2(3, var1[2], 4, var1[3], 4, var1[4], 3, var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationDataType2(3, var1[6], 4, var1[7], 4, var1[8], 3, var1[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = "ATV         ".getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -46, 8}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "AUX         ".getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -46, 12}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var1);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "            ".getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      var2 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, var2));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var16 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var16;
      this.mContext = var1;
      boolean var4 = true;
      int var3 = var16[1];
      Exception var10000;
      boolean var10001;
      if (var3 != 18) {
         if (var3 != 49) {
            if (var3 != 65) {
               if (var3 != 114) {
                  if (var3 != 116) {
                     if (var3 != 166) {
                        if (var3 != 224) {
                           if (var3 != 232) {
                              if (var3 != 240) {
                                 if (var3 != 242) {
                                    return;
                                 }

                                 if (var16[2] != 1) {
                                    var4 = false;
                                 }

                                 try {
                                    this.forceReverse(var1, var4);
                                    return;
                                 } catch (Exception var5) {
                                    var10000 = var5;
                                    var10001 = false;
                                 }
                              } else {
                                 try {
                                    this.setVersionInfo0xF0();
                                    return;
                                 } catch (Exception var6) {
                                    var10000 = var6;
                                    var10001 = false;
                                 }
                              }
                           } else {
                              try {
                                 this.setParkAssistance0xE8();
                                 return;
                              } catch (Exception var7) {
                                 var10000 = var7;
                                 var10001 = false;
                              }
                           }
                        } else {
                           try {
                              this.setMediaStatus0xE0();
                              return;
                           } catch (Exception var8) {
                              var10000 = var8;
                              var10001 = false;
                           }
                        }
                     } else {
                        try {
                           this.setAmplifierData0xA6();
                           return;
                        } catch (Exception var9) {
                           var10000 = var9;
                           var10001 = false;
                        }
                     }
                  } else {
                     try {
                        this.setKeyKonb0x74();
                        return;
                     } catch (Exception var10) {
                        var10000 = var10;
                        var10001 = false;
                     }
                  }
               } else {
                  try {
                     this.setKeyTrack0x72(var1);
                     return;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                  }
               }
            } else {
               try {
                  this.setRadarData0x41();
                  return;
               } catch (Exception var12) {
                  var10000 = var12;
                  var10001 = false;
               }
            }
         } else {
            try {
               this.setAirData0x31();
               return;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
            }
         }
      } else {
         try {
            this.setDoorData0x12();
            return;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
         }
      }

      Exception var15 = var10000;
      Log.e("CanBusError", var15.toString());
   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 != 2) {
         return false;
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
         return true;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var19 = 1;
      byte var15 = (byte)var8;
      byte var17 = (byte)var6;
      byte var18;
      if (var10) {
         var18 = 1;
      } else {
         var18 = 0;
      }

      byte var14 = (byte)var18;
      if (var11) {
         var18 = var19;
      } else {
         var18 = 0;
      }

      byte var16 = (byte)var18;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -53}, new byte[]{0, 0, 0, var15, var17, var14, var16, 0, 0, 0}));
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var16 = (byte)(var2 / 60 % 60);
      byte var15 = (byte)(var2 % 60);
      var11 = (new DecimalFormat("00")).format((long)var16);
      var12 = (new DecimalFormat("00")).format((long)var15);
      byte var14 = 7;
      if (var7 != 6 && var7 != 7) {
         var11 = (new DecimalFormat("000")).format((long)var5) + " " + var11 + var12 + "    ";
      } else {
         var11 = (new DecimalFormat("000")).format((long)var4) + " " + var11 + var12 + "    ";
      }

      var1 = var14;
      if (var7 != 1) {
         var1 = var14;
         if (var7 != 2) {
            var1 = var14;
            if (var7 != 3) {
               if (var7 != 6 && var7 != 7) {
                  var1 = 0;
               } else {
                  var1 = 6;
               }
            }
         }
      }

      byte[] var17 = var11.getBytes();
      var17 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var17);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var17);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = "DTV         ".getBytes();
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -46, 8}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), var1);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      int var2 = this.mEachId;
      if (var2 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 0, 0});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)var2, 4});
      }

      this.initAmplifier(var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.reportID3Info((byte)-44, (byte)-45, "", "", true, "Unicode");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = (new DecimalFormat("00")).format((long)(var6 & 255));
      var12 = (new DecimalFormat("00")).format((long)(var7 & 255));
      var11 = (new DecimalFormat("000")).format((long)(var9 * 256 + var3)) + " " + var11 + var12 + "    ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var25 = var11.getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
      this.reportID3Info((byte)-44, (byte)-45, var21, var23, false, "Unicode");
   }

   public void onKeyEvent(int var1, int var2, int var3, Bundle var4) {
      if (var1 == 182) {
         CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 3) {
            var2 = "0" + var1 + " " + var3 + "      ";
         } else {
            var2 = "0" + var1 + " " + var3 + "     ";
         }
      } else if (var3.length() == 5) {
         if (var3.charAt(0) == '1') {
            var2 = "0" + var1 + " " + var3 + "   ";
         } else {
            var2 = "0" + var1 + "  " + var3 + "   ";
         }
      } else {
         var2 = "0" + var1 + " " + var3 + "   ";
      }

      byte[] var7 = var2.getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -46, var6}, var7);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      byte[] var3 = new byte[15];
      var3[0] = 22;
      var3[1] = -46;
      var3[2] = 0;

      for(int var2 = 3; var2 < 15; ++var2) {
         var3[var2] = 32;
      }

      CanbusMsgSender.sendMsg(var3);
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var5 = new ArrayList();
      var5.add(new SettingUpdateEntity(var1, var2, var3));
      if (var1 == 2 && var2 == 0) {
         boolean var4 = true;
         SettingUpdateEntity var6 = new SettingUpdateEntity(2, 1, (Object)null);
         if (var3 != 1) {
            var4 = false;
         }

         var5.add(var6.setEnable(var4));
      }

      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var11 = (new DecimalFormat("00")).format((long)(var6 & 255));
      var8 = (new DecimalFormat("00")).format((long)(var7 & 255));
      var8 = (new DecimalFormat("000")).format((long)(var9 * 256 + var3)) + " " + var11 + var8 + "    ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var18 = var8.getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
   }

   private class TimerHelper {
      private Timer mTimer;
      private TimerTask mTimerTask;
      final MsgMgr this$0;

      private TimerHelper(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      TimerHelper(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void startTimer(TimerTask var1, long var2, long var4) {
         Log.i("TimerUtil", "startTimer: " + this);
         if (var1 != null) {
            this.mTimerTask = var1;
            if (this.mTimer == null) {
               this.mTimer = new Timer();
            }

            this.mTimer.schedule(this.mTimerTask, var2, var4);
         }
      }

      public void stopTimer() {
         Log.i("TimerUtil", "stopTimer: " + this);
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
   }
}
