package com.hzbhd.canbus.car._151;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private boolean mFrontStatus;
   private int mKey;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

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

   private void realKeyClick2(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[5]);
   }

   private void set0x11CarBase(Context var1) {
      int var2 = this.mKey;
      int var3 = this.mCanBusInfoInt[4];
      if (var2 != var3) {
         this.mKey = var3;
         if (var3 != 0) {
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 5) {
                     if (var3 != 6) {
                        switch (var3) {
                           case 8:
                              this.realKeyClick2(this.mContext, 45);
                              break;
                           case 9:
                              this.realKeyClick2(this.mContext, 46);
                              break;
                           case 10:
                              this.realKeyClick2(this.mContext, 2);
                        }
                     } else {
                        this.realKeyClick2(this.mContext, 15);
                     }
                  } else {
                     this.realKeyClick2(this.mContext, 14);
                  }
               } else {
                  this.realKeyClick2(this.mContext, 8);
               }
            } else {
               this.realKeyClick2(this.mContext, 7);
            }
         } else {
            this.realKeyClick2(this.mContext, 0);
         }
      }

   }

   private void set0x12DoorData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x41RearRadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(255, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0xf0VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 65) {
               if (var3 == 240) {
                  this.set0xf0VersionInfo();
               }
            } else {
               this.set0x41RearRadarData(var1);
            }
         } else {
            this.set0x12DoorData(var1);
         }
      } else {
         this.set0x11CarBase(var1);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
