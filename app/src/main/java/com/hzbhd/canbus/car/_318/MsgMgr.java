package com.hzbhd.canbus.car._318;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SystemUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private final String TAG = "_318_MsgMgr";
   private SparseArray mCanbusDataArray;
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private int mPm25AlarmCurrentLevel;
   private int mPm25AlarmSetLevel;
   private UiMgr mUiMgr;

   private void realKeyClick3_1(Context var1, int var2) {
      int[] var3 = this.mCanbusInfoInt;
      this.realKeyClick3_1(var1, var2, var3[2], var3[3]);
   }

   private void realKeyClick3_2(Context var1, int var2) {
      int[] var3 = this.mCanbusInfoInt;
      this.realKeyClick3_2(var1, var2, var3[2], var3[3]);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanbusInfoInt[3]);
   }

   private String resolveTemperature(Context var1, int var2) {
      if (var2 == 0) {
         return "LO";
      } else if (var2 == 31) {
         return "HI";
      } else {
         return var2 >= 1 && var2 <= 30 ? (var2 + 31) / 2 + this.getTempUnitC(var1) : "";
      }
   }

   private void set0x20WheelKeyData(Context var1) {
      int var2 = this.mCanbusInfoInt[2];
      if (var2 != 20) {
         if (var2 != 21) {
            switch (var2) {
               case 0:
                  this.realKeyLongClick1(var1, 0);
                  break;
               case 1:
                  this.realKeyLongClick1(var1, 7);
                  break;
               case 2:
                  this.realKeyLongClick1(var1, 8);
                  break;
               case 3:
                  this.realKeyLongClick1(var1, 46);
                  break;
               case 4:
                  this.realKeyLongClick1(var1, 45);
                  break;
               case 5:
                  this.realKeyLongClick1(var1, 14);
                  break;
               case 6:
                  this.realKeyLongClick1(var1, 3);
                  break;
               case 7:
                  this.realKeyLongClick1(var1, 2);
                  break;
               case 8:
                  this.realKeyLongClick1(var1, 187);
                  break;
               default:
                  switch (var2) {
                     case 10:
                        this.realKeyLongClick1(var1, 33);
                        break;
                     case 11:
                        this.realKeyLongClick1(var1, 34);
                        break;
                     case 12:
                        this.realKeyLongClick1(var1, 35);
                        break;
                     case 13:
                        this.realKeyLongClick1(var1, 36);
                        break;
                     case 14:
                        this.realKeyLongClick1(var1, 37);
                        break;
                     case 15:
                        this.realKeyLongClick1(var1, 38);
                        break;
                     case 16:
                        this.realKeyLongClick1(var1, 134);
                        break;
                     default:
                        switch (var2) {
                           case 41:
                              this.realKeyClick3_2(var1, 47);
                              break;
                           case 42:
                              this.realKeyClick3_2(var1, 48);
                              break;
                           case 43:
                              this.realKeyLongClick1(var1, 47);
                              break;
                           case 44:
                              this.realKeyLongClick1(var1, 48);
                              break;
                           case 45:
                              this.realKeyLongClick1(var1, 45);
                              break;
                           case 46:
                              this.realKeyLongClick1(var1, 46);
                              break;
                           case 47:
                              this.realKeyLongClick1(var1, 49);
                              break;
                           case 48:
                              this.realKeyLongClick1(var1, 59);
                              break;
                           case 49:
                              this.realKeyLongClick1(var1, 129);
                              break;
                           case 50:
                              this.realKeyLongClick1(var1, 52);
                              break;
                           case 51:
                              this.realKeyLongClick1(var1, 141);
                              break;
                           case 52:
                              this.realKeyLongClick1(var1, 58);
                              break;
                           case 53:
                              this.realKeyLongClick1(var1, 50);
                              break;
                           default:
                              switch (var2) {
                                 case 70:
                                    this.realKeyLongClick1(var1, 27);
                                    break;
                                 case 71:
                                    this.realKeyLongClick1(var1, 29);
                                    break;
                                 case 72:
                                    this.realKeyLongClick1(var1, 74);
                                    break;
                                 case 73:
                                    this.realKeyLongClick1(var1, 75);
                                    break;
                                 case 74:
                                    this.realKeyLongClick1(var1, 128);
                              }
                        }
                  }
            }
         } else {
            this.realKeyClick3_1(var1, 8);
         }
      } else {
         this.realKeyClick3_1(var1, 7);
      }

   }

   private void set0x21AirData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]) ^ true;
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveTemperature(var1, this.mCanbusInfoInt[5]);
         GeneralAirData.front_right_temperature = this.resolveTemperature(var1, this.mCanbusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 4, 3);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 0, 3);
         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         this.updateAirActivity(var1, 1001);
      }

   }

   private void set0x27Pm25InfoData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         int[] var4 = this.mCanbusInfoInt;
         int var2 = var4[3];
         int var3 = var4[2] | var2 << 8;
         String var5 = "";
         String var7;
         if (var3 >= 0 && var3 <= 35) {
            var7 = CommUtil.getStrByResId(var1, "pm_excellent");
         } else if (var3 >= 36 && var3 <= 75) {
            var7 = CommUtil.getStrByResId(var1, "pm_good");
         } else if (var3 >= 76 && var3 <= 115) {
            var7 = CommUtil.getStrByResId(var1, "mild");
         } else if (var3 >= 116 && var3 <= 150) {
            var7 = CommUtil.getStrByResId(var1, "moderate");
         } else if (var3 >= 151 && var3 <= 250) {
            var7 = CommUtil.getStrByResId(var1, "severe");
         } else if (var3 >= 251 && var3 <= 999) {
            var7 = CommUtil.getStrByResId(var1, "serious");
         } else {
            var7 = "";
         }

         var2 = this.mCanbusInfoInt[4];
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 == 4) {
                        var5 = CommUtil.getStrByResId(var1, "serious") + CommUtil.getStrByResId(var1, "_31_tire_date3");
                     }
                  } else {
                     var5 = CommUtil.getStrByResId(var1, "severe") + CommUtil.getStrByResId(var1, "_31_tire_date3");
                  }
               } else {
                  var5 = CommUtil.getStrByResId(var1, "moderate") + CommUtil.getStrByResId(var1, "_31_tire_date3");
               }
            } else {
               var5 = CommUtil.getStrByResId(var1, "mild") + CommUtil.getStrByResId(var1, "_31_tire_date3");
            }
         } else {
            var5 = CommUtil.getStrByResId(var1, "_103_car_setting_value_38") + CommUtil.getStrByResId(var1, "_31_tire_date3");
         }

         ArrayList var6 = new ArrayList();
         var6.add(new DriverUpdateEntity(0, 0, var3 + " " + CommUtil.getStrByResId(var1, "ug_m3")));
         var6.add(new DriverUpdateEntity(0, 1, var7));
         var6.add(new DriverUpdateEntity(0, 2, var5));
         this.updateGeneralDriveData(var6);
         this.updateDriveDataActivity((Bundle)null);
         var3 = this.mPm25AlarmCurrentLevel;
         var2 = this.mCanbusInfoInt[4];
         if (var3 != var2) {
            this.mPm25AlarmCurrentLevel = var2;
            if (var2 > this.mPm25AlarmSetLevel && !SystemUtil.isForeground(var1, DriveDataActivity.class.getName())) {
               Intent var8 = new Intent(var1, DriveDataActivity.class);
               var8.setFlags(268435456);
               var1.startActivity(var8);
            }
         }
      }

   }

   private void set0x28Pm25SetupData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 1)));
         var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 2)));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
         this.mPm25AlarmSetLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 2);
      }

   }

   private void set0x30VersionData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanbusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mCanbusDataArray = new SparseArray();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoInt = var4;
      this.mCanbusInfoByte = var2;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 33) {
            if (var3 != 39) {
               if (var3 != 40) {
                  if (var3 == 48) {
                     this.set0x30VersionData(var1);
                  }
               } else {
                  this.set0x28Pm25SetupData(var1);
               }
            } else {
               this.set0x27Pm25InfoData(var1);
            }
         } else {
            this.set0x21AirData(var1);
         }
      } else {
         this.set0x20WheelKeyData(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
