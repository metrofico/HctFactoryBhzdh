package com.hzbhd.canbus.car._455;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private String[] arr0 = new String[2];
   private String[] arr1 = new String[2];
   private String[] arr2 = new String[2];
   private String[] arr3 = new String[2];
   DecimalFormat df = new DecimalFormat("###0.0");
   private int leftFrontError = 0;
   private int leftRearError = 0;
   int[] mAirData;
   int[] mCarDoorData;
   Context mContext;
   int[] mTireErrorInfo;
   int[] mTireInfo;
   int[] mTrackData;
   private int rightFrontError = 0;
   private int rightRearError = 0;
   private List tyreInfoList = new ArrayList();

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = var1;
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange(int[] var1) {
      if (Arrays.equals(this.mTireInfo, var1)) {
         return false;
      } else {
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoErrorChange(int[] var1) {
      if (Arrays.equals(this.mTireErrorInfo, var1)) {
         return false;
      } else {
         this.mTireErrorInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setAir0x11(int[] var1) {
      var1[7] = 0;
      if (this.isAirDataChange(var1)) {
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(var1[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(var1[2]);
         int var2 = var1[3];
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_foot = false;
         if (var2 == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         } else if (var2 == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         } else if (var2 == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         } else if (var2 == 4) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
         } else if (var2 == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
         }

         GeneralAirData.front_wind_level = var1[4];
         var2 = var1[5];
         if (var2 == 0) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var2 == 30) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = (float)var1[5] / 2.0F + 17.0F + this.getTempUnitC(this.mContext);
         }

         var2 = var1[6];
         if (var2 == 0) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var2 == 30) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = (float)var1[6] / 2.0F + 17.0F + this.getTempUnitC(this.mContext);
         }

         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setDoor0x28(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setSwc0x21(int[] var1) {
      int var2 = var1[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 6) {
                  if (var2 != 7) {
                     if (var2 != 18) {
                        switch (var2) {
                           case 9:
                              this.realKeyLongClick1(this.mContext, 14, var1[3]);
                              break;
                           case 10:
                              this.realKeyLongClick1(this.mContext, 15, var1[3]);
                              break;
                           case 11:
                              this.realKeyLongClick1(this.mContext, 46, var1[3]);
                              break;
                           case 12:
                              this.realKeyLongClick1(this.mContext, 45, var1[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 187, var1[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 2, var1[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 3, var1[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var1[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var1[3]);
      }

   }

   private void setTireErrorInfo0x39(int[] var1) {
      if (this.isTireInfoErrorChange(var1)) {
         this.leftFrontError = 0;
         this.rightFrontError = 0;
         this.leftRearError = 0;
         this.rightRearError = 0;
         if (var1[2] != 0) {
            this.leftFrontError = 1;
         }

         if (var1[3] != 0) {
            this.rightFrontError = 1;
         }

         if (var1[4] != 0) {
            this.leftRearError = 1;
         }

         if (var1[5] != 0) {
            this.rightRearError = 1;
         }

         this.updateDateTire();
      }

   }

   private void setTireInfo0x38(int[] var1) {
      if (this.isTireInfoChange(var1)) {
         this.arr0[0] = this.mContext.getString(2131765670) + ":" + (var1[2] - 40) + this.getTempUnitC(this.mContext);
         this.arr0[1] = this.mContext.getString(2131765669) + ":" + this.df.format((double)var1[6] * 0.02745) + "bar";
         this.arr1[0] = this.mContext.getString(2131765670) + ":" + (var1[3] - 40) + this.getTempUnitC(this.mContext);
         this.arr1[1] = this.mContext.getString(2131765669) + ":" + this.df.format((double)var1[7] * 0.02745) + "bar";
         this.arr2[0] = this.mContext.getString(2131765670) + ":" + (var1[4] - 40) + this.getTempUnitC(this.mContext);
         this.arr2[1] = this.mContext.getString(2131765669) + ":" + this.df.format((double)var1[8] * 0.02745) + "bar";
         this.arr3[0] = this.mContext.getString(2131765670) + ":" + (var1[5] - 40) + this.getTempUnitC(this.mContext);
         this.arr3[1] = this.mContext.getString(2131765669) + ":" + this.df.format((double)var1[9] * 0.02745) + "bar";
         this.updateDateTire();
      }

   }

   private void setTrack0x30(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         int var2 = var1[3];
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 7);
         if (DataHandleUtils.getBoolBit7(var1[2])) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var2, (byte)var3, 0, 12000, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)var2, (byte)var3, 0, 12000, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         }
      }

   }

   private void updateDateTire() {
      List var1 = this.tyreInfoList;
      if (var1 != null) {
         var1.clear();
      }

      this.tyreInfoList.add(new TireUpdateEntity(0, this.leftFrontError, this.arr0));
      this.tyreInfoList.add(new TireUpdateEntity(1, this.rightFrontError, this.arr1));
      this.tyreInfoList.add(new TireUpdateEntity(2, this.leftRearError, this.arr2));
      this.tyreInfoList.add(new TireUpdateEntity(3, this.rightRearError, this.arr3));
      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 33) {
            if (var3 != 40) {
               if (var3 != 48) {
                  if (var3 != 127) {
                     if (var3 != 56) {
                        if (var3 == 57) {
                           this.setTireErrorInfo0x39(var4);
                        }
                     } else {
                        this.setTireInfo0x38(var4);
                     }
                  } else {
                     this.updateVersionInfo(this.mContext, this.getVersionStr(var2));
                  }
               } else {
                  this.setTrack0x30(var4);
               }
            } else {
               this.setDoor0x28(var4);
            }
         } else {
            this.setSwc0x21(var4);
         }
      } else {
         this.setAir0x11(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      GeneralTireData.isHaveSpareTire = false;
   }

   public void onAccOff() {
      super.onAccOff();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
