package com.hzbhd.canbus.car._424;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final boolean $assertionsDisabled = false;
   private static int outDoorTemp;
   private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
   List MTire0 = new ArrayList();
   List MTire1 = new ArrayList();
   List MTire2 = new ArrayList();
   List MTire3 = new ArrayList();
   Boolean PressureAlarm1;
   Boolean PressureAlarm2;
   Boolean PressureAlarm3;
   Boolean PressureAlarm4;
   Boolean PressureCoefficient1;
   Boolean PressureCoefficient2;
   Boolean PressureCoefficient3;
   Boolean PressureCoefficient4;
   Boolean QuickLeakAlarm1;
   Boolean QuickLeakAlarm2;
   Boolean QuickLeakAlarm3;
   Boolean QuickLeakAlarm4;
   Boolean TemperatureAlarm1;
   Boolean TemperatureAlarm2;
   Boolean TemperatureAlarm3;
   Boolean TemperatureAlarm4;
   private int TirePressure0001;
   private int TirePressure0010;
   private int TirePressure0100;
   private int TirePressure1000;
   private int TireTempture0001;
   private int TireTempture0010;
   private int TireTempture0100;
   private int TireTempture1000;
   DecimalFormat decimalFormat = new DecimalFormat("0.0000");
   Boolean errorAlarm1;
   Boolean errorAlarm2;
   Boolean errorAlarm3;
   Boolean errorAlarm4;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   int mDifferentID;

   public MsgMgr() {
      Boolean var1 = false;
      this.TirePressure1000 = 0;
      this.TirePressure0100 = 0;
      this.TirePressure0010 = 0;
      this.TirePressure0001 = 0;
      this.TireTempture1000 = 0;
      this.TireTempture0100 = 0;
      this.TireTempture0010 = 0;
      this.TireTempture0001 = 0;
      this.PressureCoefficient1 = var1;
      this.errorAlarm1 = var1;
      this.PressureAlarm1 = var1;
      this.QuickLeakAlarm1 = var1;
      this.TemperatureAlarm1 = var1;
      this.PressureCoefficient2 = var1;
      this.errorAlarm2 = var1;
      this.PressureAlarm2 = var1;
      this.QuickLeakAlarm2 = var1;
      this.TemperatureAlarm2 = var1;
      this.PressureCoefficient3 = var1;
      this.errorAlarm3 = var1;
      this.PressureAlarm3 = var1;
      this.QuickLeakAlarm3 = var1;
      this.TemperatureAlarm3 = var1;
      this.PressureCoefficient4 = var1;
      this.errorAlarm4 = var1;
      this.PressureAlarm4 = var1;
      this.QuickLeakAlarm4 = var1;
      this.TemperatureAlarm4 = var1;
   }

   private String ResolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      if (var1 == 254) {
         var2 = "LOW_TEMP";
      }

      if (var1 == 255) {
         var2 = "HIGH_TEMP";
      }

      return var2;
   }

   private TireUpdateEntity getTireEntity(int var1, int var2, int var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8) {
      double var9;
      String var12;
      if (var4) {
         var9 = 3.137;
         var12 = "3.137";
      } else {
         var9 = 1.3725;
         var12 = "1.3725";
      }

      byte var11;
      String var13;
      if (var5) {
         var13 = CommUtil.getStrByResId(this.mContext, "_424_Warning_2");
         var11 = 1;
      } else {
         var13 = CommUtil.getStrByResId(this.mContext, "_424_Warning_1");
         var11 = 0;
      }

      String var14;
      if (var6) {
         var14 = CommUtil.getStrByResId(this.mContext, "_424_Warning_4");
         var11 = 1;
      } else {
         var14 = CommUtil.getStrByResId(this.mContext, "_424_Warning_3");
      }

      String var15;
      if (var7) {
         var15 = CommUtil.getStrByResId(this.mContext, "_424_Warning_6");
         var11 = 1;
      } else {
         var15 = CommUtil.getStrByResId(this.mContext, "_424_Warning_5");
      }

      String var16;
      if (var8) {
         var16 = CommUtil.getStrByResId(this.mContext, "_424_Warning_8");
         var11 = 1;
      } else {
         var16 = CommUtil.getStrByResId(this.mContext, "_424_Warning_7");
      }

      return new TireUpdateEntity(var1, var11, new String[]{this.decimalFormat.format((double)var2 * var9) + "kpa", var3 - 50 + this.getTempUnitC(this.mContext), var12, var13, var14, var15, var16});
   }

   private boolean isOnlyOutDoorDataChange() {
      return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0F) != (float)outDoorTemp;
   }

   protected static List mergeList(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void setAirInfo0x31() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 0) {
            if (var1 != 3) {
               if (var1 != 12) {
                  if (var1 != 5) {
                     if (var1 == 6) {
                        GeneralAirData.front_left_blow_foot = false;
                        GeneralAirData.front_right_blow_foot = false;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_window = false;
                        GeneralAirData.front_right_blow_window = false;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
            }
         } else {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.center_wheel = (double)this.mCanBusInfoInt[12] * 0.5 - 40.0 + this.getTempUnitC(this.mContext);
         outDoorTemp = this.mCanBusInfoInt[13];
         if (this.isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", (float)outDoorTemp);
            this.updateOutDoorTemp(this.mContext, (double)this.mCanBusInfoInt[13] * 0.5 - 40.0 + this.getTempUnitC(this.mContext));
         } else {
            this.updateAirActivity(this.mContext, 1001);
         }

      }
   }

   private void setRadarInfo0x41() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 255;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(6, var1[2], var1[3], var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(6, var1[6], var1[7], var1[8], var1[9]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTireInfo0x48() {
      GeneralTireData.isHaveSpareTire = false;
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[3];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  this.TirePressure0001 = var2[4];
                  this.TireTempture0001 = var2[5];
                  this.PressureCoefficient4 = DataHandleUtils.getBoolBit4(var2[6]);
                  this.errorAlarm4 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
                  this.PressureAlarm4 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
                  this.QuickLeakAlarm4 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
                  this.TemperatureAlarm4 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
               }
            } else {
               this.TirePressure0010 = var2[4];
               this.TireTempture0010 = var2[5];
               this.PressureCoefficient3 = DataHandleUtils.getBoolBit4(var2[6]);
               this.errorAlarm3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
               this.PressureAlarm3 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
               this.QuickLeakAlarm3 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
               this.TemperatureAlarm3 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            }
         } else {
            this.TirePressure0100 = var2[4];
            this.TireTempture0100 = var2[5];
            this.PressureCoefficient2 = DataHandleUtils.getBoolBit4(var2[6]);
            this.errorAlarm2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
            this.PressureAlarm2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
            this.QuickLeakAlarm2 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
            this.TemperatureAlarm2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         }
      } else {
         this.TirePressure1000 = var2[4];
         this.TireTempture1000 = var2[5];
         this.PressureCoefficient1 = DataHandleUtils.getBoolBit4(var2[6]);
         this.errorAlarm1 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         this.PressureAlarm1 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         this.QuickLeakAlarm1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         this.TemperatureAlarm1 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      }

      this.MTire0.add(this.getTireEntity(0, this.TirePressure1000, this.TireTempture1000, this.PressureCoefficient1, this.errorAlarm1, this.PressureAlarm1, this.QuickLeakAlarm1, this.TemperatureAlarm1));
      this.MTire1.add(this.getTireEntity(1, this.TirePressure0100, this.TireTempture0100, this.PressureCoefficient2, this.errorAlarm2, this.PressureAlarm2, this.QuickLeakAlarm2, this.TemperatureAlarm2));
      this.MTire2.add(this.getTireEntity(2, this.TirePressure0010, this.TireTempture0010, this.PressureCoefficient3, this.errorAlarm3, this.PressureAlarm3, this.QuickLeakAlarm3, this.TemperatureAlarm3));
      this.MTire3.add(this.getTireEntity(3, this.TirePressure0001, this.TireTempture0001, this.PressureCoefficient4, this.errorAlarm4, this.PressureAlarm4, this.QuickLeakAlarm4, this.TemperatureAlarm4));
      GeneralTireData.dataList = mergeList(this.MTire0, this.MTire1, this.MTire2, this.MTire3);
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 == 12) {
                                 this.realKeyClick0x11(2);
                              }
                           } else {
                              this.realKeyClick0x11(46);
                           }
                        } else {
                           this.realKeyClick0x11(45);
                        }
                     } else {
                        this.realKeyClick0x11(15);
                     }
                  } else {
                     this.realKeyClick0x11(14);
                  }
               } else {
                  this.realKeyClick0x11(3);
               }
            } else {
               this.realKeyClick0x11(8);
            }
         } else {
            this.realKeyClick0x11(7);
         }
      } else {
         this.realKeyClick0x11(0);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mDifferentID = this.getCurrentCanDifferentId();
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 49) {
            if (var3 != 65) {
               if (var3 != 72) {
                  if (var3 == 240) {
                     this.setVersionInfo0xF0();
                  }
               } else {
                  this.setTireInfo0x48();
               }
            } else {
               this.setRadarInfo0x41();
            }
         } else {
            this.setAirInfo0x31();
         }
      } else {
         this.setWheelKey0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
