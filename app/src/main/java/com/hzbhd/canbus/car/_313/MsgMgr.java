package com.hzbhd.canbus.car._313;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private int[] mCanBusInfoInt;
   private byte[] mCanbusInfoByte;
   private Context mContext;
   int now0x24Data0 = 0;

   private void set0x20WheelKeyData(Context var1) {
      int[] var4 = this.mCanBusInfoInt;
      short var2 = 2;
      int var3 = var4[2];
      if (var3 != 21) {
         if (var3 != 22) {
            switch (var3) {
               case 0:
               default:
                  var2 = 0;
                  break;
               case 1:
                  var2 = 7;
                  break;
               case 2:
                  var2 = 8;
                  break;
               case 3:
                  var2 = 14;
                  break;
               case 4:
                  var2 = 3;
                  break;
               case 5:
                  var2 = 46;
                  break;
               case 6:
                  var2 = 45;
               case 7:
                  break;
               case 8:
                  var2 = 187;
                  break;
               case 9:
               case 10:
                  var2 = 15;
            }
         } else {
            var2 = 49;
         }
      } else {
         var2 = 50;
      }

      this.realKeyLongClick1(var1, var2, var4[3]);
   }

   private void set0x24DoorInfo() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         int var2 = this.now0x24Data0;
         int var1 = this.mCanBusInfoInt[2];
         if (var2 != var1) {
            this.now0x24Data0 = var1;
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            this.updateDoorView(this.mContext);
         }
      }
   }

   private void set0x26VehicleInfo(Context var1) {
      int[] var6 = this.mCanBusInfoInt;
      int var3 = var6[2] << 8 | var6[3];
      int var4 = var6[4];
      int var5 = var6[5];
      int var2 = var6[6];
      String var7;
      if (var2 == 0) {
         var7 = CommUtil.getStrByResId(var1, "geely_e6_item_0");
      } else if (var2 == 1) {
         var7 = CommUtil.getStrByResId(var1, "_313_battery_too_low");
      } else {
         var7 = "";
      }

      ArrayList var8 = new ArrayList();
      var8.add(new DriverUpdateEntity(0, 0, var3 + " KM/H"));
      var8.add(new DriverUpdateEntity(0, 1, (var4 << 8 | var5) + " RPM"));
      var8.add(new DriverUpdateEntity(0, 2, var7));
      this.updateGeneralDriveData(var8);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(var3);
   }

   private void set0x35VehicleInfo(Context var1) {
      int[] var5 = this.mCanBusInfoInt;
      int var3 = var5[2];
      int var2 = var5[3];
      int var4 = var5[4];
      var4 = var5[5];
      var4 = var5[6];
      if (var4 == 0) {
         CommUtil.getStrByResId(var1, "geely_e6_item_0");
      } else if (var4 == 1) {
         CommUtil.getStrByResId(var1, "_313_battery_too_low");
      }

      ArrayList var6 = new ArrayList();
      var6.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
      StringBuilder var8 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var6.add(new DriverUpdateEntity(0, 1, var8.append(DataHandleUtils.getMsbLsbResult(var7[6], var7[7])).append(" RPM").toString()));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(var3 << 8 | var2);
   }

   private void set0x39AmpInfo() {
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralAmplifierData.frontRear = -this.mCanBusInfoInt[3] + 10;
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 10;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      this.updateAmplifierActivity((Bundle)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoByte = var2;
      byte var3 = var2[1];
      if (var3 != 32) {
         if (var3 != 36) {
            if (var3 != 53) {
               if (var3 == 57) {
                  this.set0x39AmpInfo();
               }
            } else {
               this.set0x35VehicleInfo(var1);
            }
         } else {
            this.set0x24DoorInfo();
         }
      } else {
         this.set0x20WheelKeyData(var1);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }
}
