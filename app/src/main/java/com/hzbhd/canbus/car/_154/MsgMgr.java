package com.hzbhd.canbus.car._154;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.DataHandleUtils;

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
      this.realKeyLongClick2(var1, var2);
   }

   private void set0x81CarBase(Context var1) {
      int var2 = this.mKeyStatus;
      int var3 = this.mCanBusInfoInt[4];
      if (var2 != var3) {
         this.mKeyStatus = var3;
         if (var3 != 0) {
            if (var3 != 1) {
               if (var3 != 2) {
                  switch (var3) {
                     case 7:
                        this.realKeyClick2(this.mContext, 46);
                        break;
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

   private void set0x82DoorData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
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
      if (var3 != 129) {
         if (var3 != 130) {
            if (var3 == 240) {
               this.set0xf0VersionInfo();
            }
         } else {
            this.set0x82DoorData(var1);
         }
      } else {
         this.set0x81CarBase(var1);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
