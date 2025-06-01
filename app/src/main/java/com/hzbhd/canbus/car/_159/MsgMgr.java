package com.hzbhd.canbus.car._159;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private int mDriveDataNow;
   private int mEachId;
   private boolean mIsAirFirst = true;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private int[] mVersionInfoNow;
   private int mWheelKeyNow;

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 4;
            }
      }

      switch (var7) {
         case 0:
            return var5;
         case 1:
            return var6;
         case 2:
            return var2;
         case 3:
            return var3;
         case 4:
            return var4;
         default:
            return 0;
      }
   }

   private boolean isDriveDataChange() {
      int var1 = this.mDriveDataNow;
      int var2 = this.mCanBusInfoInt[3];
      if (var1 == var2) {
         return false;
      } else {
         this.mDriveDataNow = var2;
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

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isWheelKeyChange() {
      int var1 = this.mWheelKeyNow;
      int var2 = this.mCanBusInfoInt[4];
      if (var1 == var2) {
         return false;
      } else {
         this.mWheelKeyNow = var2;
         return true;
      }
   }

   private String resolveAirTemp(int var1) {
      if (var1 == 0) {
         return "";
      } else if (var1 == 1) {
         return "LO";
      } else {
         return var1 == 255 ? "HI" : (float)var1 * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
      }
   }

   private void setAirData0x73() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) ^ true;
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.mono = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[5]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setBaseInfo0x72() {
      if (this.isDriveDataChange()) {
         ArrayList var2 = new ArrayList();
         var2.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " km/h"));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

      if (this.isWheelKeyChange()) {
         int var1 = this.mCanBusInfoInt[4];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 5) {
                        if (var1 != 6) {
                           if (var1 != 32) {
                              switch (var1) {
                                 case 8:
                                    this.wheelKeyClick(45);
                                    break;
                                 case 9:
                                    this.wheelKeyClick(46);
                                    break;
                                 case 10:
                                    this.wheelKeyClick(2);
                              }
                           } else {
                              this.updateAirActivity(this.mContext, 1001);
                              this.playBeep();
                           }
                        } else {
                           this.wheelKeyClick(15);
                        }
                     } else {
                        this.wheelKeyClick(14);
                     }
                  } else {
                     this.wheelKeyClick(3);
                  }
               } else {
                  this.wheelKeyClick(8);
               }
            } else {
               this.wheelKeyClick(7);
            }
         } else {
            this.wheelKeyClick(0);
         }
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void startTimer(long var1) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1);
      }
   }

   private void stopTimer() {
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

   private void wheelKeyClick(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-46, (byte)8));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-46, (byte)12));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      byte var5;
      if (this.mDifferent == 1) {
         var5 = 23;
      } else {
         var5 = 10;
      }

      byte var4 = (byte)var5;

      String var6;
      for(var6 = " "; var6.length() < 12; var6 = var6 + " ") {
      }

      var1 = var6.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var4}, var1));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      byte var5;
      if (this.mDifferent == 1) {
         var5 = 23;
      } else {
         var5 = 10;
      }

      byte var4 = (byte)var5;

      String var6;
      for(var6 = new String(var1); var6.length() < 12; var6 = var6 + " ") {
      }

      var1 = var6.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var4}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      byte var5;
      if (this.mDifferent == 1) {
         var5 = 23;
      } else {
         var5 = 10;
      }

      byte var4 = (byte)var5;

      String var6;
      for(var6 = new String(var1); var6.length() < 12; var6 = var6 + " ") {
      }

      var1 = var6.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var4}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      Log.i("ljq", "btPhoneStatusInfoChange: " + var1);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      byte var5;
      if (this.mDifferent == 1) {
         var5 = 23;
      } else {
         var5 = 10;
      }

      byte var4 = (byte)var5;

      String var6;
      for(var6 = new String(var1); var6.length() < 12; var6 = var6 + " ") {
      }

      var1 = var6.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var4}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 114) {
         if (var3 != 115) {
            if (var3 == 240) {
               this.setVersionInfo0xF0();
            }
         } else {
            this.setAirData0x73();
         }
      } else {
         this.setBaseInfo0x72();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      byte[] var3 = ((new DecimalFormat("00")).format((long)var1) + "          ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 22}, var3));
      this.mTimerTask = new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            MsgMgr var1 = this.this$0;
            var1.sendAfterHangUpMsg(var1.mContext);
            this.this$0.stopTimer();
         }
      };
      this.startTimer(1000L);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      DecimalFormat var14 = new DecimalFormat("00");
      byte[] var15 = (var1 + var14.format((long)var3) + var14.format((long)var4) + var14.format((long)var8) + var14.format((long)var6)).getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 21}, var15));
      this.mTimerTask = new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            MsgMgr var1 = this.this$0;
            var1.sendAfterHangUpMsg(var1.mContext);
            this.this$0.stopTimer();
         }
      };
      this.startTimer(1000L);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var14;
      if (var7 == 1) {
         var14 = 7;
      } else {
         var14 = 6;
      }

      if (var7 == 1) {
         var4 = var5;
      }

      var1 = (byte)var14;
      byte[] var15 = ("TRA   " + (new DecimalFormat("000")).format((long)DataHandleUtils.rangeNumber(var4, 0, 999)) + "   ").getBytes();
      var15 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var15);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var15);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-46, (byte)20));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = "TRA   " + (new DecimalFormat("000")).format((long)DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 999)) + "   ";
      byte var25;
      if (var1 == 9) {
         var25 = 14;
      } else {
         var25 = 13;
      }

      var1 = (byte)var25;
      byte[] var26 = var11.getBytes();
      var26 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var26);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      boolean var7 = this.isBandAm(var2);
      var2 = " ";
      if (var7) {
         var2 = var3 + "KHZ    ";

         while(true) {
            var3 = var2;
            if (var2.length() >= 12) {
               break;
            }

            var2 = " " + var2;
         }
      } else {
         if (var3.length() != 5) {
            var2 = "";
         }

         var3 = var2 + var3 + "MHZ   ";
      }

      Log.i("ljq", "radioInfoChange: " + var3);
      byte[] var8 = var3.getBytes();
      var8 = DataHandleUtils.byteMerger(new byte[]{22, -46, var6}, var8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var8);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-46, (byte)0));
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = "TRA   " + (new DecimalFormat("000")).format((long)DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 999)) + "   ";
      byte var18;
      if (var1 == 9) {
         var18 = 14;
      } else {
         var18 = 13;
      }

      var1 = (byte)var18;
      byte[] var19 = var8.getBytes();
      var19 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var19);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var19);
   }
}
