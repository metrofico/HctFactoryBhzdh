package com.hzbhd.canbus.car._178;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private int mKeyStatus;

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveOutDoorTem() {
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[4];
      double var1 = (double)(var4[5] | var3 << 8);
      String var5;
      if (!DataHandleUtils.getBoolBit7(var3)) {
         var5 = var1 / 10.0 + this.getTempUnitC(this.mContext);
      } else {
         var4 = this.mCanBusInfoInt;
         var3 = var4[4];
         var1 = (double)(var4[5] | (var3 & 15) << 8);
         var5 = "-" + var1 / 10.0 + this.getTempUnitC(this.mContext);
      }

      return var5;
   }

   private void set0x01WheelKey() {
      int var1 = this.mKeyStatus;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.mKeyStatus = var2;
      }

      switch (var2) {
         case 1:
            this.realKeyClick(2);
            break;
         case 2:
            this.realKeyClick(46);
            break;
         case 3:
            this.realKeyClick(45);
            break;
         case 4:
            this.realKeyClick(7);
            break;
         case 5:
            this.realKeyClick(8);
            break;
         case 6:
            this.realKeyClick(3);
            break;
         case 7:
            this.realKeyClick(14);
            break;
         case 8:
            this.realKeyClick(15);
      }

   }

   private void set0x02CarData() {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      int var1 = var3[3];
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 0, (var1 | var2 << 8) + " KM"));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x05TrackData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte)this.mCanBusInfoInt[2], (byte)0, 0, 50, 8);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x71VersionData() {
      String var2 = String.format("20%d%d.%d%d.%d%d ", this.mCanBusInfoInt[4] >> 4, this.mCanBusInfoInt[4] & 15, this.mCanBusInfoInt[5] >> 4, this.mCanBusInfoInt[5] & 15, this.mCanBusInfoInt[6] >> 4, this.mCanBusInfoInt[6] & 15);
      String var1 = "V" + (this.mCanBusInfoInt[8] - 78) + (this.mCanBusInfoInt[9] - 78) + (this.mCanBusInfoInt[10] - 78);
      var1 = "PORSCHE-Cayenne " + var2 + var1;
      this.updateVersionInfo(this.mContext, var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -126, 11, -1, -1, -1});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -126, 9, -1, -1, -1});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 10, -1, -1, -1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 10, -1, -1, -1});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 5) {
               if (var3 == 113) {
                  this.set0x71VersionData();
               }
            } else {
               this.set0x05TrackData(var1);
            }
         } else {
            this.set0x02CarData();
         }
      } else {
         this.set0x01WheelKey();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 != 0 && var7 != 15) {
         byte var16;
         if (var7 == 1) {
            var16 = 6;
         } else {
            var16 = 7;
         }

         int[] var17 = new int[2];
         if (var7 == 6) {
            var17[0] = var4 >> 8;
            var17[1] = var4 & 255;
         } else {
            var17[0] = var5 >> 8;
            var17[1] = var5 & 255;
         }

         byte var15 = (byte)var16;
         var1 = (byte)var17[0];
         byte var14 = (byte)var17[1];
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -126, var15, var1, var14, -1});
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -126, 11, -1, -1, -1});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 3;
      } else {
         var25 = 4;
      }

      var1 = (byte)var25;
      var2 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -126, var1, var9, var2, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      int var6;
      if (!var2.equals("FM") && !var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3")) {
         if (!var2.equals("AM") && !var2.equals("AM1") && !var2.equals("AM2") && !var2.equals("MW")) {
            var1 = 255;
            var5 = 255;
            var6 = var5;
         } else {
            var1 = Integer.parseInt(var3);
            var5 = DataHandleUtils.getMsb(var1);
            var1 = DataHandleUtils.getLsb(var1);
            var6 = 2;
         }
      } else {
         var1 = (int)(Float.parseFloat(var3) * 10.0F);
         var5 = DataHandleUtils.getMsb(var1);
         var1 = DataHandleUtils.getLsb(var1);
         var6 = 1;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var6, (byte)var5, (byte)var1, (byte)255});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -126, 0, -1, -1, -1});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 3;
      } else {
         var18 = 4;
      }

      var1 = (byte)var18;
      var2 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -126, var1, var9, var2, 0});
   }
}
