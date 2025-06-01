package com.hzbhd.canbus.car._155;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mFrontStatus;
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 10.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveOutDoorTem() {
      int[] var3 = this.mCanBusInfoInt;
      double var1 = (double)var3[2];
      if (var1 >= 1.0 && var1 <= 128.0) {
         String var4;
         if (var3[3] == 1) {
            var4 = this.tempCToTempF(var1 - 40.0) + this.getTempUnitF(this.mContext);
         } else {
            var4 = var1 - 40.0 + this.getTempUnitC(this.mContext);
         }

         return var4;
      } else {
         return "---";
      }
   }

   private void set0x11WheelKey() {
      int var1 = this.mKeyStatus;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.mKeyStatus = var2;
      }

      if (var2 != 0) {
         if (var2 != 3) {
            if (var2 != 4) {
               if (var2 != 5) {
                  if (var2 != 6) {
                     switch (var2) {
                        case 8:
                           this.realKeyClick(45);
                           break;
                        case 9:
                           this.realKeyClick(46);
                           break;
                        case 10:
                           this.realKeyClick(129);
                     }
                  } else {
                     this.realKeyClick(3);
                  }
               } else {
                  this.realKeyClick(8);
               }
            } else {
               this.realKeyClick(7);
            }
         } else {
            this.realKeyClick(2);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void set0x12BtUsbData() {
      int var1 = this.mCanBusInfoInt[2];
      if ((var1 >> 3 & 1) == 1) {
         GeneralOriginalCarDeviceData.cdStatus = "VOICE";
      } else if ((var1 >> 2 & 1) == 1) {
         GeneralOriginalCarDeviceData.cdStatus = "PHONE";
      } else if ((var1 & 3) == 2) {
         GeneralOriginalCarDeviceData.cdStatus = "Media Player";
      } else {
         GeneralOriginalCarDeviceData.cdStatus = " ";
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void set0x14DoorData(Context var1) {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x16OutDoorTemp() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void set0x17OrigUsbPlayTimeData() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDeviceUpdateEntity(0, String.format("%02d:%02d", this.mCanBusInfoInt[2], this.mCanBusInfoInt[3])));
      GeneralOriginalCarDeviceData.mList = var1;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void set0x71VersionData() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[3];
      String var2;
      if (var1 == 0) {
         var2 = "兼容 ";
      } else if (var1 == 1) {
         var2 = "brave ";
      } else {
         var2 = "punto ";
      }

      String var5 = String.format("20%d%d.%d%d.%d%d ", var3[4] >> 4, this.mCanBusInfoInt[4] & 15, this.mCanBusInfoInt[5] >> 4, this.mCanBusInfoInt[5] & 15, this.mCanBusInfoInt[6] >> 4, this.mCanBusInfoInt[6] & 15);
      String var4 = "V" + (char)this.mCanBusInfoInt[8] + "." + (char)this.mCanBusInfoInt[9] + "." + (char)this.mCanBusInfoInt[10];
      var2 = "FIAT " + var2 + var5 + var4;
      this.updateVersionInfo(this.mContext, var2);
   }

   private double tempCToTempF(double var1) {
      LogUtil.showLog("tempCToTempF:" + var1);

      try {
         DecimalFormat var3 = new DecimalFormat("0.0");
         var1 = Double.valueOf(var3.format(var1 * 1.8 + 32.0));
         return var1;
      } catch (Exception var4) {
         LogUtil.showLog("Exception:" + var4);
         return 0.0;
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -109, 3, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 20) {
               if (var3 != 113) {
                  if (var3 != 22) {
                     if (var3 == 23) {
                        this.set0x17OrigUsbPlayTimeData();
                     }
                  } else {
                     this.set0x16OutDoorTemp();
                  }
               } else {
                  this.set0x71VersionData();
               }
            } else {
               this.set0x14DoorData(var1);
            }
         } else {
            this.set0x12BtUsbData();
         }
      } else {
         this.set0x11WheelKey();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var14;
      if (var9) {
         var14 = 2;
      } else {
         var14 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -109, 2, 0, (byte)DataHandleUtils.rangeNumber(var5, 1, 99), (byte)var14, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 5;
      } else {
         var25 = 4;
      }

      byte var26;
      if (var18) {
         var26 = 2;
      } else {
         var26 = 0;
      }

      var3 = DataHandleUtils.rangeNumber(var9 << 8 | var3, 255);
      CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte)var25, 0, (byte)var3, (byte)var26, 0, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6;
      if (var2.contains("FM2")) {
         var6 = 1;
      } else if (var2.contains("FM3")) {
         var6 = 2;
      } else if (var2.contains("LW")) {
         var6 = 4;
      } else if (var2.contains("MW")) {
         var6 = 5;
      } else if (var2.contains("AM")) {
         var6 = 6;
      } else {
         var6 = 0;
      }

      int[] var7 = this.getFreqByteHiLo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -109, 1, (byte)var6, 0, 0, (byte)var7[0], (byte)var7[1]});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -109, 0, 0, 0, 0, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 5;
      } else {
         var18 = 4;
      }

      byte var19;
      if (var16) {
         var19 = 2;
      } else {
         var19 = 0;
      }

      var3 = DataHandleUtils.rangeNumber(var9 << 8 | var3, 255);
      CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte)var18, 0, (byte)var3, (byte)var19, 0, 0});
   }
}
