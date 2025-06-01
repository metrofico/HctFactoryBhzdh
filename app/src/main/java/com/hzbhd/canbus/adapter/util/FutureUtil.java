package com.hzbhd.canbus.adapter.util;

import android.app.Application;
import android.content.Context;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.proxy.mcu.core.MCUMainManager;

public class FutureUtil {
   public static FutureUtil instance = new FutureUtil();
   int btOn;
   int discExsit;
   int discRadom;
   int discRepeat;
   int folderLoop;
   int fullCurve;
   int micOn;
   int mute;
   int radioSt;
   int randomFolder;
   int sdCardIn;
   int singleCycle;
   int usbIn;

   public void DevicesStutasRepCanbus(Context var1) {
   }

   public void audioChannelRequest(SourceConstantsDef.SOURCE_ID var1) {
   }

   public void detectParkPanoramic(boolean var1, int var2) {
   }

   public void discGoto(MpegConstantsDef.K_SELECT var1, int var2) {
   }

   public boolean getAutoSearchStatus() {
      return false;
   }

   public int getBrightness() {
      return 0;
   }

   public int getCanIsAppHandleKey() {
      return 0;
   }

   public SourceConstantsDef.SOURCE_ID getCurrentValidSource() {
      return SourceConstantsDef.SOURCE_ID.NULL;
   }

   public boolean getPSSwitchStatus() {
      return false;
   }

   public boolean getSeekDownStatus() {
      return false;
   }

   public boolean getSeekUpStatus() {
      return false;
   }

   public int getStereoStatus() {
      return 0;
   }

   public String[] getValidFreqList(int var1) {
      return new String[0];
   }

   public int getVolumeValue() {
      return 0;
   }

   public void gotoTrack(int var1) {
   }

   public void initCanbusAmp(Application var1) {
   }

   public int is360External() {
      return 0;
   }

   public boolean isDiscExist() {
      return false;
   }

   public boolean isHaveAtv() {
      return false;
   }

   public boolean isHaveDtv() {
      return false;
   }

   public boolean isShowBackCameraTips() {
      return false;
   }

   public void nextMpeg() {
   }

   public void pauseMpeg() {
   }

   public void playMpeg() {
   }

   public void playPresetFreq(int var1) {
   }

   public void prevMpeg() {
   }

   public void repeatMpeg() {
   }

   public void reportCanbusInfo(byte[] var1) {
      try {
         MCUMainManager.getInstance().sendMCUCanboxData(39, var1);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void reportCanbusInfoOther(byte var1, byte[] var2) {
      MCUMainManager.getInstance().sendMCUCanboxData(var1, var2);
   }

   public void reportMcuDataExtra(byte[] var1, Context var2) {
      if (Util.isCanbusCmdPorting(var2)) {
         this.sndCanCmd(var1);
      } else {
         this.reportCanbusInfo(var1);
      }

   }

   public void reportSmartPowerInfo(byte[] var1) {
   }

   public void reqMcuKey(byte[] var1) {
   }

   public void sendAppSelect(String var1) {
   }

   public void sendDtvPosition(int var1, int var2) {
   }

   public void sendPositionExtra(int var1, byte[] var2, int var3, int var4) {
   }

   public void setBrightness(int var1) {
   }

   public void setCanIsAppHandleKey(int var1) {
   }

   public void setCurrentFreq(String var1) {
   }

   public void setIsOneKey(int var1) {
   }

   public void setParking(boolean var1) {
   }

   public void setReversingBaseline(boolean var1) {
   }

   public void setReversingTrackSwitch(boolean var1) {
   }

   public void setVolumeVal(int var1) {
   }

   public void showBackCamera() {
   }

   public void showCenterCamera() {
   }

   public void showFrontCamera() {
   }

   public void shuffleMpeg() {
   }

   public void sndCanCmd(byte[] var1) {
      this.reportCanbusInfo(Util.byteMerger(new byte[]{22}, var1));
   }

   public byte[] str2Bcd(String var1) {
      int var3 = var1.length();
      int var2 = var3;
      String var6 = var1;
      if (var3 % 2 != 0) {
         var6 = var1 + " ";
         var2 = var6.length();
      }

      byte[] var8 = new byte[var2];
      var3 = var2;
      if (var2 >= 2) {
         var3 = var2 / 2;
      }

      var8 = new byte[var3];
      byte[] var7 = var6.getBytes();

      for(int var4 = 0; var4 < var6.length() / 2; ++var4) {
         int var5 = var4 * 2;
         byte var9 = var7[var5];
         var3 = 11;
         if (var9 >= 48 && var9 <= 57) {
            var2 = var9 - 48;
         } else if (var9 == 42) {
            var2 = 10;
         } else if (var9 == 35) {
            var2 = 11;
         } else {
            var2 = 15;
         }

         byte var10 = var7[var5 + 1];
         if (var10 >= 48 && var10 <= 57) {
            var3 = var10 - 48;
         } else if (var10 == 42) {
            var3 = 10;
         } else if (var10 != 35) {
            var3 = 15;
         }

         var8[var4] = (byte)((var2 << 4) + var3);
      }

      return var8;
   }

   public boolean supportDisc() {
      return false;
   }
}
