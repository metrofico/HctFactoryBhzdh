package com.hzbhd.canbus.car._319;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private boolean mBackStatus;
   private SparseArray mCanbusDataArray = new SparseArray();
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private Context mContext;
   private boolean mFrontStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         return true;
      }
   }

   private void set0x20WheelKeyInfo(Context var1) {
      int[] var3 = this.mCanbusInfoInt;
      short var2 = 2;
      switch (var3[2]) {
         case 1:
            var2 = 7;
            break;
         case 2:
            var2 = 8;
            break;
         case 3:
            var2 = 46;
            break;
         case 4:
            var2 = 45;
            break;
         case 5:
         case 6:
         default:
            var2 = 0;
         case 7:
            break;
         case 8:
            var2 = 187;
            break;
         case 9:
            var2 = 14;
            break;
         case 10:
            var2 = 15;
      }

      this.realKeyLongClick1(var1, var2, var3[3]);
   }

   private void set0x24BaseData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x29TrackData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         byte[] var2 = this.mCanbusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 11776, 16);
         Log.i("cbc", "set0x29TackAngle =" + GeneralParkData.trackAngle);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x30VersionData() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanbusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanbusInfoInt = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoByte = var2;
      byte var3 = var2[1];
      if (var3 != 32) {
         if (var3 != 36) {
            if (var3 != 41) {
               if (var3 == 48) {
                  this.set0x30VersionData();
               }
            } else {
               this.set0x29TrackData(var1);
            }
         } else {
            if (this.isDoorMsgRepeat(var2)) {
               return;
            }

            this.set0x24BaseData(var1);
         }
      } else {
         this.set0x20WheelKeyInfo(var1);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 36, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 0});
   }
}
