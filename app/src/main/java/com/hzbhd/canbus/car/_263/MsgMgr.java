package com.hzbhd.canbus.car._263;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.activity.MainActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_FRONT_OPEN = "is_front_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
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
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) != GeneralDoorData.isFrontOpen;
      }
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void realKeyControl() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        if (var1 != 128) {
                           if (var1 != 19) {
                              if (var1 == 20) {
                                 this.realKeyClick(46);
                              }
                           } else {
                              this.realKeyClick(45);
                           }
                        } else {
                           Intent var3 = new Intent(this.mContext, MainActivity.class);
                           var3.addFlags(268435456);
                           this.mContext.startActivity(var3);
                        }
                     } else {
                        this.realKeyClick(2);
                     }
                  } else {
                     this.realKeyClick3(this.mContext, 45, var1, var2[3]);
                  }
               } else {
                  this.realKeyClick3(this.mContext, 46, var1, var2[3]);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private String resolveOutDoorTem() {
      double var3 = (double)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      double var1 = var3;
      if (var3 >= 127.0) {
         var1 = 127.0;
      }

      String var5;
      if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var5 = 0.5 * var1 + "";
      } else {
         var5 = "-" + 0.5 * var1;
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var5 = this.tempCToTempF(var1) + "";
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var5 = var5 + this.getTempUnitF(this.mContext);
      } else {
         var5 = var5 + this.getTempUnitC(this.mContext);
      }

      return var5;
   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarSetData0x14() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131769315);
      } else if (var1 == 7) {
         var2 = this.mContext.getResources().getString(2131769174);
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 3, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (GeneralDoorData.isShowCarDoor && this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
         this.updateDoorView(this.mContext);
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = "D";
            }
         } else {
            var2 = "R";
         }
      } else {
         var2 = "P";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var2 = this.mContext.getResources().getString(2131769504);
      } else {
         var2 = this.mContext.getResources().getString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 1, var2));
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var2 = this.mContext.getResources().getString(2131769504);
      } else {
         var2 = this.mContext.getResources().getString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 2, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveDataInfo() {
      int[] var2 = this.mCanBusInfoInt;
      float var1 = (float)((double)(var2[3] * 256 + var2[4]) * 0.1 * 10.0) / 10.0F;
      String var6;
      if (var2[2] == 255) {
         var6 = "--KM/h";
      } else {
         var6 = this.mCanBusInfoInt[2] + "KM/h";
      }

      int[] var3 = this.mCanBusInfoInt;
      String var7;
      if (var3[3] == 255 && var3[4] == 255) {
         var7 = "----.-KM";
      } else {
         var7 = var1 + "KM";
      }

      int[] var4 = this.mCanBusInfoInt;
      String var8;
      if (var4[5] == 255 && var4[6] == 255) {
         var8 = "----";
      } else {
         var8 = this.mCanBusInfoInt[5] + "h : " + this.mCanBusInfoInt[6] + "m";
      }

      ArrayList var5 = new ArrayList();
      var5.add(new DriverUpdateEntity(1, 0, var6));
      var5.add(new DriverUpdateEntity(1, 1, var7));
      var5.add(new DriverUpdateEntity(1, 2, var8));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 32) {
            if (var3 != 34) {
               if (var3 != 36) {
                  if (var3 != 39) {
                     if (var3 != 48) {
                        if (var3 == 51) {
                           this.setDriveDataInfo();
                           var3 = this.mCanBusInfoInt[2];
                           if (var3 != 255) {
                              this.updateSpeedInfo(var3);
                           }
                        }
                     } else {
                        this.setVersionInfo();
                     }
                  } else {
                     this.setAirData0x27();
                  }
               } else {
                  if (this.isDoorMsgReturn(var2)) {
                     return;
                  }

                  this.setDoorData0x24();
               }
            } else {
               this.setRadarInfo();
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
