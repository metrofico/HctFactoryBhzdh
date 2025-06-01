package com.hzbhd.canbus.car._434.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import com.hzbhd.proxy.sourcemanager.SourceManager;

public class MsAbstractMsgMgr implements MsgMgrInterface {
   private String airJsonStr;
   private boolean isMute = false;

   private String intArrayToJsonStr(int[] var1) {
      this.airJsonStr = "{";

      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var2 == var1.length - 1) {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + "}";
         } else {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + ",";
         }
      }

      return this.airJsonStr;
   }

   private void registerMute() {
      int var1 = ShareDataManager.getInstance().registerShareIntListener("audio.mute", new IShareIntListener(this) {
         final MsAbstractMsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onInt(int var1) {
            MsAbstractMsgMgr var3 = this.this$0;
            boolean var2 = true;
            if (var1 != 1) {
               var2 = false;
            }

            var3.isMute = var2;
            var3 = this.this$0;
            var3.muteStateChange(var3.isMute);
         }
      });
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      this.isMute = var2;
   }

   public static void releaseVoiceSource() {
      SourceManager.getInstance().notifyAppAudio(SourceConstantsDef.SOURCE_ID.VOICE, "com.hzbhd.canbus.car._434.impl", 3, false);
   }

   protected void ShareBasicInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportMsBasicInfo(this.intArrayToJsonStr(var1));
   }

   protected void SharedAirData(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportMsAirInfo(this.intArrayToJsonStr(var1));
   }

   public void aMapCallBack(Bundle var1) {
   }

   public void afterServiceNormalSetting(Context var1) {
   }

   public void atvDestdroy() {
   }

   public void atvInfoChange() {
   }

   public void auxInDestdroy() {
   }

   public void auxInInfoChange() {
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
   }

   public void btMusicInfoChange() {
   }

   public void btMusiceDestdroy() {
   }

   public void btPhoneCallLogInfoChange(int var1, int var2, String var3) {
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
   }

   public void cameraDestroy() {
   }

   public void cameraInfoChange() {
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
   }

   public boolean customLongClick(Context var1, int var2) {
      return false;
   }

   public boolean customShortClick(Context var1, int var2) {
      return false;
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
   }

   public void destroyCommand() {
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
   }

   public void dtvInfoChange() {
   }

   public void getBackCameraUiService(BackCameraUiService var1) {
   }

   protected int[] getByteArrayToIntArray(byte[] var1) {
      int[] var4 = new int[var1.length];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         byte var3 = var1[var2];
         if ((var3 & 128) == 0) {
            var4[var2] = var3;
         } else {
            var4[var2] = var3 & 255;
         }
      }

      return var4;
   }

   protected int getMsDataType(int[] var1) {
      int var2 = var1[2];
      int var4 = var1[3];
      int var3 = var1[4];
      return var1[5] & 255 | (var2 & 255) << 24 | (var4 & 255) << 16 | (var3 & 255) << 8;
   }

   public void initCommand(Context var1) {
      this.registerMute();
   }

   public boolean isMute() {
      return this.isMute;
   }

   public void linInfoChange(Context var1, byte[] var2) {
   }

   public void mcuErrorState(Context var1, byte[] var2) {
   }

   public void medianCalibration(Context var1, byte[] var2) {
   }

   public void musicDestroy() {
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
   }

   public void musicLrcInfoChange(String var1) {
   }

   public void muteStateChange(boolean var1) {
   }

   public void notifyBtStatusChange() {
   }

   public void onAMapCallBack(AMapEntity var1) {
   }

   public void onAccOff() {
   }

   public void onAccOn() {
   }

   public void onHandshake(Context var1) {
   }

   public void onKeyEvent(int var1, int var2, int var3, Bundle var4) {
   }

   public void onPowerOff() {
   }

   public void onSleep() {
   }

   public void radioDestroy() {
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
   }

   protected void realKeyLongClick1(Context var1, int var2, int var3) {
      if (!RealKeyUtil.isCompoundKey(var1, var2, var3)) {
         RealKeyUtil.realKeyLongClick(var1, var2, var3);
      }

   }

   protected void requestVoiceSource() {
      SourceManager.getInstance().notifyAppAudio(SourceConstantsDef.SOURCE_ID.VOICE, "com.hzbhd.canbus.car._434.impl", 3, true);
   }

   public void serialDataChange(Context var1, byte[] var2) {
   }

   public void setMgrRefreshUiInterface(AbstractBaseActivity var1) {
   }

   public void sourceSwitchChange(String var1) {
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
   }

   protected void startOtherActivity(Context var1, ComponentName var2) {
      Intent var3 = new Intent();
      var3.setComponent(var2);
      var3.setFlags(268435456);
      var1.startActivity(var3);
   }

   protected void updateSpeedInfo(int var1) {
      CanbusInfoChangeListener.getInstance().reportSpeedInfo(var1);
   }

   public void updateVersionInfo(Context var1, String var2) {
      ShareDataServiceImpl.setString("canbus.canVersion", var2);
      GeneralData.INSTANCE.setCanVersion(var2);
   }

   public void videoDestroy() {
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
   }

   public void voiceControlInfo(String var1) {
   }
}
