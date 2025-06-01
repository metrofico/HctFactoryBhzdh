package com.hzbhd.canbus.car._42;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private String ONCE_Tag = "once.tag";
   private byte freqHi;
   private byte freqLo;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private long realtime;
   private String songAlbumNow = "";
   private String songArtistNow = "";
   private String songTitleNow = "";

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
      }

      switch (var2) {
         case 0:
         case 1:
            return 16;
         case 2:
            return 1;
         case 3:
            return 2;
         default:
            return 0;
      }
   }

   private String getCalibrationStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "compass_calibrating";
      } else {
         var2 = "compass_calibration_finish";
      }

      return var2;
   }

   private String getDeviceStatus(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = "IPOD";
      } else if (var1 == 2) {
         var2 = "USB";
      } else {
         var2 = " ";
      }

      return var2;
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.FreqInt = Integer.parseInt(var2);
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private String getRunStatus(int var1) {
      String var2 = "device_run_status_" + var1;
      return CommUtil.getStrByResId(this.mContext, var2);
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private void initBt(Context var1) {
      boolean var4 = SharePreUtil.getBoolValue(var1, this.ONCE_Tag, true);
      byte var3 = 0;
      int var2 = var3;
      if (var4) {
         SharePreUtil.setBoolValue(var1, this.ONCE_Tag, false);
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, 2});
         var2 = var3;
      }

      while(var2 < 10) {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 2});
         ++var2;
      }

   }

   private void realKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick2(var3, var1, var2[2], var2[3]);
   }

   private void realKeyControl() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(7);
            break;
         case 2:
            this.realKeyClick(8);
            break;
         case 3:
            this.realKeyClick(48);
            break;
         case 4:
            this.realKeyClick(47);
         case 5:
         case 6:
         default:
            break;
         case 7:
            this.realKeyClick(2);
            break;
         case 8:
            this.realKeyClick(187);
            break;
         case 9:
            this.realKeyClick(14);
            break;
         case 10:
            this.realKeyClick(189);
            break;
         case 11:
            this.realKeyClick(39);
            break;
         case 12:
            this.realKeyClick(40);
            break;
         case 13:
            this.realKeyClick(41);
      }

   }

   private void reportID3InfoSimple(byte[] var1, byte var2, byte var3, byte var4, String var5, String var6, String var7, boolean var8, String var9, int var10) {
      (new Thread(this, var8, var5, var6, var7, var1, var2, var9, var10, var3, var4) {
         final MsgMgr this$0;
         final String val$album;
         final byte val$albumCmd;
         final String val$artist;
         final byte val$artistCmd;
         final String val$charsetName;
         final int val$fixLength;
         final byte[] val$haed;
         final boolean val$isClean;
         final String val$title;
         final byte val$titleCmd;

         {
            this.this$0 = var1;
            this.val$isClean = var2;
            this.val$title = var3;
            this.val$album = var4;
            this.val$artist = var5;
            this.val$haed = var6;
            this.val$titleCmd = var7;
            this.val$charsetName = var8;
            this.val$fixLength = var9;
            this.val$albumCmd = var10;
            this.val$artistCmd = var11;
         }

         public void run() {
            super.run();

            try {
               if (this.val$isClean) {
                  sleep(1000L);
               }

               if (!this.val$title.equals(this.this$0.songTitleNow) || !this.val$album.equals(this.this$0.songAlbumNow) || !this.val$artist.equals(this.this$0.songArtistNow)) {
                  this.this$0.songTitleNow = this.val$title;
                  this.this$0.songAlbumNow = this.val$album;
                  this.this$0.songArtistNow = this.val$artist;
                  sleep(100L);
                  this.this$0.reportID3InfoSimpleFinal(this.val$haed, this.val$titleCmd, this.val$title, this.val$charsetName, this.val$fixLength);
                  sleep(100L);
                  this.this$0.reportID3InfoSimpleFinal(this.val$haed, this.val$albumCmd, this.val$album, this.val$charsetName, this.val$fixLength);
                  sleep(100L);
                  this.this$0.reportID3InfoSimpleFinal(this.val$haed, this.val$artistCmd, this.val$artist, this.val$charsetName, this.val$fixLength);
               }
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private void reportID3InfoSimpleFinal(byte[] var1, byte var2, String var3, String var4, int var5) {
      try {
         CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.byteMerger(Util.addBytes(var1, var2), Util.exceptBOMHead(var3.getBytes(var4))), var5));
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private void setCompass0xD2() {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, this.getCalibrationStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      var2.add((new SettingUpdateEntity(0, 1, var1)).setProgress(var1));
      var2.add(new SettingUpdateEntity(0, 2, (float)(this.mCanBusInfoInt[3] * 3) / 2.0F));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalDevice0x21() {
      String var2 = this.getDeviceStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
      String var3 = this.getRunStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
      if (CommUtil.getStrByResId(this.mContext, "device_run_status_4").equals(var3) && var3 != GeneralOriginalCarDeviceData.runningState) {
         Log.i("ljq", "setOriginalDevice0x21:  enter");
         this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      } else if (CommUtil.getStrByResId(this.mContext, "device_run_status_2").equals(var3) && var3 != GeneralOriginalCarDeviceData.runningState) {
         Log.i("ljq", "setOriginalDevice0x21:  exit");
         this.enterNoSource();
      }

      DecimalFormat var1 = new DecimalFormat("00");
      GeneralOriginalCarDeviceData.cdStatus = var2;
      GeneralOriginalCarDeviceData.runningState = var3;
      GeneralOriginalCarDeviceData.startTime = var1.format((long)this.mCanBusInfoInt[4]) + ":" + var1.format((long)this.mCanBusInfoInt[5]);
      GeneralOriginalCarDeviceData.endTime = this.mCanBusInfoInt[11] + "%";
      GeneralOriginalCarDeviceData.progress = this.mCanBusInfoInt[11];
      ArrayList var4 = new ArrayList();
      var4.add(new OriginalCarDeviceUpdateEntity(0, Integer.toString(this.mCanBusInfoInt[10])));
      StringBuilder var5 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(1, var5.append(var7[8] * 256 + var7[9]).append("").toString()));
      StringBuilder var8 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(2, var8.append(var6[6] * 256 + var6[7]).append("").toString()));
      if ("IPOD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
         var4.add(new OriginalCarDeviceUpdateEntity(0, ""));
      }

      if (CommUtil.getStrByResId(this.mContext, "device_run_status_0").equals(GeneralOriginalCarDeviceData.runningState) || CommUtil.getStrByResId(this.mContext, "device_run_status_2").equals(GeneralOriginalCarDeviceData.runningState)) {
         var4.add(new OriginalCarDeviceUpdateEntity(0, ""));
         var4.add(new OriginalCarDeviceUpdateEntity(1, ""));
         var4.add(new OriginalCarDeviceUpdateEntity(2, ""));
      }

      GeneralOriginalCarDeviceData.mList = var4;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setTrack0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5120, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVehicleDoorInfo0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      this.updateDoorView(this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), new byte[]{22, -64, 3, 34});
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -64, 7, 48});
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -64, 11, 34});
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
      CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.addBytes(Util.byteMerger(new byte[]{22, -53, 1}, var1), (byte)-1), 53));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
      CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.addBytes(Util.byteMerger(new byte[]{22, -53, 1}, var1), (byte)-1), 53));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
      CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.addBytes(Util.byteMerger(new byte[]{22, -53, 1}, var1), (byte)-1), 53));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 33) {
            if (var3 != 36) {
               if (var3 != 41) {
                  if (var3 != 48) {
                     if (var3 == 210) {
                        this.setCompass0xD2();
                     }
                  } else {
                     this.setVersionInfo();
                  }
               } else {
                  this.setTrack0x29();
               }
            } else {
               this.setVehicleDoorInfo0x24();
            }
         } else {
            this.setOriginalDevice0x21();
         }
      } else {
         this.realKeyControl();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var1});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      if (this.getCurrentCanDifferentId() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, (byte)(var10 ^ 1), (byte)var8, (byte)var6});
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.i("_42_MsgMgr", "discInfoChange: mContext = " + this.mContext);
      if (this.mContext != null) {
         this.getHour(var2);
         var3 = this.getMinute(var2);
         var2 = this.getSecond(var2);
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         if (var10 == 240) {
            if (SystemClock.elapsedRealtime() - this.realtime < 1000L) {
               return;
            }

            String var16 = System.getString(this.mContext.getContentResolver(), "reportForDiscEject");
            if (!TextUtils.isEmpty(var16)) {
               this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Base64.decode(var16, 0));
            }

            this.realtime = SystemClock.elapsedRealtime();
            this.reportID3InfoSimple(new byte[]{22, -53}, (byte)2, (byte)3, (byte)4, var11, var12, var13, true, "unicodeLittle", 53);
         } else if (var10 == 32) {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -64, 2, 16});
            byte var14 = (byte)var4;
            var1 = (byte)var3;
            byte var15 = (byte)var2;
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -61, 0, var14, 0, 0, var1, var15});
            this.reportID3InfoSimple(new byte[]{22, -53}, (byte)2, (byte)3, (byte)4, var11, var12, var13, false, "unicodeLittle", 53);
         }

      }
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), new byte[]{22, -64, 10, 34});
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               sleep(2000L);
               CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

         }
      }).start();
      this.initBt(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var2 = (byte)(var4 & 255);
      var5 = (byte)(var4 >> 8 & 255);
      byte var25 = 9;
      if (var1 != 9) {
         var25 = 8;
      }

      var1 = (byte)var25;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -64, var1, 17});
      var1 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -61, var2, var5, var1, var9, var6, var7});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var10 = this.getAllBandTypeData(var2);
      this.getFreqByteHiLo(var2, var3);
      byte var9 = (byte)var10;
      byte var7 = this.freqLo;
      byte var6 = this.freqHi;
      byte var8 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -62, var9, var7, var6, var8});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var5 = (byte)(var4 & 255);
      var2 = (byte)(var4 >> 8 & 255);
      byte var18 = 9;
      if (var1 != 9) {
         var18 = 8;
      }

      var1 = (byte)var18;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, var1, 17});
      var1 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -61, var5, var2, var1, var9, var6, var7});
   }
}
