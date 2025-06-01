package com.hzbhd.canbus.car._380;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.TrackInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr uiMgr;

   private void RealKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean resolveRadar(int var1) {
      return var1 != 0;
   }

   private void setBacklight0x11() {
      this.setBacklightLevel(this.mCanBusInfoInt[7] / 20 + 1);
   }

   private void setRadarSwitch0x11() {
      this.getUiMgr(this.mContext).getParkPageUiSet(this.mContext).setShowRadar(this.resolveRadar(this.mCanBusInfoInt[6]));
   }

   private void setTrackDate0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setWheelKey0x11() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.RealKeyClick(0);
            break;
         case 1:
            this.RealKeyClick(7);
            break;
         case 2:
            this.RealKeyClick(8);
            break;
         case 3:
            this.RealKeyClick(3);
            break;
         case 4:
            this.RealKeyClick(187);
            break;
         case 5:
            this.RealKeyClick(188);
         case 6:
         case 7:
         default:
            break;
         case 8:
            this.RealKeyClick(46);
            break;
         case 9:
            this.RealKeyClick(45);
            break;
         case 10:
            this.RealKeyClick(151);
            break;
         case 11:
            this.RealKeyClick(2);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      if (var3[1] == 17) {
         this.setWheelKey0x11();
         this.setRadarSwitch0x11();
         this.setBacklight0x11();
         this.setTrackDate0x11();
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = var5 + ":" + var12 + ":" + var13;
      var4 = var11.length();

      for(var3 = 0; var3 < 24 - var4; ++var3) {
         var11 = var11 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(22, var11.getBytes());
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      boolean var7 = var2.equals("FM1");
      byte var8 = 1;
      if (var7) {
         var2 = var3 + "MHz";
      } else if (var2.equals("FM2")) {
         var8 = 2;
         var2 = var3 + "MHz";
      } else if (var2.equals("FM3")) {
         var8 = 3;
         var2 = var3 + "MHz";
      } else if (var2.equals("AM1")) {
         var8 = 4;
         var2 = var3 + "KHz";
      } else if (var2.equals("AM2")) {
         var8 = 5;
         var2 = var3 + "KHz";
      } else {
         var2 = "nothing";
      }

      int var6 = var2.length();

      for(var5 = 0; var5 < 24 - var6; ++var5) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(var8, var2.getBytes());
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = var5 + ":" + var12 + ":" + var13;
      var4 = var8.length();

      for(var3 = 0; var3 < 24 - var4; ++var3) {
         var8 = var8 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(22, var8.getBytes());
   }
}
