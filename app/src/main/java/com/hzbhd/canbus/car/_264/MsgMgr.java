package com.hzbhd.canbus.car._264;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen;
      }
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void realKeyControl() {
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         switch (this.mCanBusInfoInt[2]) {
            case 0:
               this.realKeyClick(0);
               break;
            case 1:
               this.realKeyClick(7);
               break;
            case 2:
               this.realKeyClick(8);
               break;
            case 3:
               this.realKeyClick(128);
               break;
            case 4:
               this.realKeyClick(2);
               break;
            case 5:
               this.realKeyClick(14);
               break;
            case 6:
               this.realKeyClick(3);
         }

      }
   }

   private void setCarSetData0x14() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (this.mCanBusInfoInt[2] == 1) {
         var1 = this.mContext.getResources().getString(2131769504);
      } else {
         var1 = this.mContext.getResources().getString(2131768042);
      }

      var2.add(new DriverUpdateEntity(0, 0, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      if (this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         this.updateDoorView(this.mContext);
      }

      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getResources().getString(2131769410);
      } else {
         var1 = this.mContext.getResources().getString(2131769841);
      }

      var2.add(new DriverUpdateEntity(0, 1, var1));
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getResources().getString(2131769741);
      } else {
         var1 = this.mContext.getResources().getString(2131769742);
      }

      var2.add(new DriverUpdateEntity(0, 2, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTrackInfo() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte)var1[3], (byte)var1[2], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 32) {
            if (var3 != 36) {
               if (var3 != 38) {
                  if (var3 == 255) {
                     this.setVersionInfo();
                  }
               } else {
                  this.setTrackInfo();
               }
            } else {
               if (this.isDoorMsgReturn(var2)) {
                  return;
               }

               this.setDoorData0x24();
            }
         } else {
            this.realKeyControl();
         }
      } else {
         this.setCarSetData0x14();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
