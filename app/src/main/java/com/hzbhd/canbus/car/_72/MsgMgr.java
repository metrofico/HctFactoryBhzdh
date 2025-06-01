package com.hzbhd.canbus.car._72;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private final String TAG = "_72_MsgMgr";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray;
   private boolean mHoodStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private boolean mTrunkSataus;
   private String mVersionInfo;

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mTrunkSataus == GeneralDoorData.isBackOpen && this.mHoodStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mTrunkSataus = GeneralDoorData.isBackOpen;
         this.mHoodStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void realKeyClick2(Context var1, int var2) {
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var1, var2, var3[2], var3[3]);
   }

   private void setBacklightData0x14() {
   }

   private void setDoorData0x24(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void setOnStarData0x39(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var2 = this.mCanBusInfoInt[2];
         if (var2 == 0) {
            this.exitAuxIn2();
         } else if (var2 == 1) {
            this.enterAuxIn2(var1, Constant.OnStarActivity);
            Intent var3 = new Intent();
            var3.setAction("song.title.change.action");
            var3.putExtra("SongTitle", var1.getString(2131769486));
            var1.sendBroadcast(var3);
         }
      }

   }

   private void setOutDoorTemperaure0x27(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         String var4 = this.getTempUnitC(var1);
         boolean var2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         String var5 = "";
         String var3 = var5;
         if (var2) {
            String var6 = this.getTempUnitF(var1);
            var4 = var6;
            var3 = var5;
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
               var3 = "-";
               var4 = var6;
            }
         }

         this.updateOutDoorTemp(var1, var3 + this.mCanBusInfoByte[2] + var4);
      }

   }

   private void setTrackData0x29(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 8500, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void setVehicleSpeedData0x16() {
      int[] var1 = this.mCanBusInfoInt;
      this.updateSpeedInfo((var1[3] * 256 + var1[2]) / 16);
   }

   private void setVersionInfo0x30(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         String var2 = this.getVersionStr(this.mCanBusInfoByte);
         this.mVersionInfo = var2;
         this.updateVersionInfo(var1, var2);
      }

   }

   private void setWheelKeyData0x20(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 7) {
                  if (var2 != 9) {
                     if (var2 != 24) {
                        switch (var2) {
                           case 18:
                              this.realKeyClick2(var1, 49);
                              break;
                           case 19:
                              this.realKeyClick2(var1, 45);
                              break;
                           case 20:
                              this.realKeyClick2(var1, 46);
                        }
                     } else {
                        this.realKeyClick2(var1, 75);
                     }
                  } else {
                     this.realKeyClick2(var1, 14);
                  }
               } else {
                  this.realKeyClick2(var1, 2);
               }
            } else {
               this.realKeyClick2(var1, 8);
            }
         } else {
            this.realKeyClick2(var1, 7);
         }
      } else {
         this.realKeyClick2(var1, 0);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mCanbusDataArray = new SparseArray();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 32) {
               if (var3 != 36) {
                  if (var3 != 39) {
                     if (var3 != 41) {
                        if (var3 != 48) {
                           if (var3 == 57) {
                              this.setOnStarData0x39(var1);
                           }
                        } else {
                           this.setVersionInfo0x30(var1);
                        }
                     } else {
                        this.setTrackData0x29(var1);
                     }
                  } else {
                     this.setOutDoorTemperaure0x27(var1);
                  }
               } else {
                  this.setDoorData0x24(var1);
               }
            } else {
               this.setWheelKeyData0x20(var1);
            }
         } else {
            this.setVehicleSpeedData0x16();
         }
      } else {
         this.setBacklightData0x14();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.i("_72_MsgMgr", "dateTimeRepCanbus: " + this.mVersionInfo);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
