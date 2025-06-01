package com.hzbhd.canbus.car._400;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   int mDifferentID;

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
            return 4;
         case 1:
            return 5;
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

   private String resolveOutTemp(int var1) {
      return var1 - 40 + this.getTempUnitC(this.mContext);
   }

   private void setAirInfo0x73() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
         this.updateOutDoorTemp(this.mContext, this.resolveOutTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7)));
      }

   }

   private void setRadarInfo0x72() {
      GeneralParkData.radar_distance_disable = new int[]{0, 255};
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[8], var1[9], var1[10], var1[11]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
   }

   public void cameraInfoChange() {
      super.cameraInfoChange();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mDifferentID = this.getCurrentCanDifferentId();
      Log.d("lai", "canbusInfoChange: 1234");
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 114) {
         if (var3 != 115) {
            if (var3 == 240) {
               this.setVersionInfo0xF0();
            }
         } else {
            this.setAirInfo0x73();
         }
      } else {
         this.setRadarInfo0x72();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 != 0 && var7 != 15) {
         byte var14 = 6;
         if (var7 == 1) {
            var14 = 7;
         } else if (var7 == 5) {
            var14 = 24;
         } else if (var7 != 6) {
            var14 = 0;
         }

         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)var14}, 15));
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      Log.d("lai", "canbusInfoChange: 123");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 14;
      } else {
         var25 = 13;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)var25}, 15));
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, this.getAllBandTypeData(var2)}, 15));
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 0}, 15));
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 14;
      } else {
         var18 = 22;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)var18}, 15));
   }
}
