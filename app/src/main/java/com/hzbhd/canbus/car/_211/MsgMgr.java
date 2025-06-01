package com.hzbhd.canbus.car._211;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static byte[] mFrontDataLast;
   private static byte[] mFrontDataRec;
   private static byte[] mRearDataLast;
   private static byte[] mRearDataRec;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private ContentResolver mContentResolver;
   private Context mContext;
   int[] mRearRadarData;

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var5 = new byte[var4];

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var3 < var2) {
            var5[var3] = var1[var3];
         } else {
            var5[var3] = var1[var3 + 1];
         }
      }

      return var5;
   }

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
   }

   private int getAirWhat() {
      mFrontDataRec = Arrays.copyOfRange(this.mCanBusInfoByte, 2, 10);
      mRearDataRec = Arrays.copyOfRange(this.mCanBusInfoByte, 10, 13);
      boolean var2 = Arrays.equals(mFrontDataRec, mFrontDataLast);
      short var1 = 0;
      if (!var2) {
         System.putString(this.mContentResolver, "211_LastFrontData", Base64.encodeToString(mFrontDataRec, 0));
         var1 = 1001;
      } else if (!Arrays.equals(mRearDataRec, mRearDataLast)) {
         System.putString(this.mContentResolver, "211_LastRearData", Base64.encodeToString(mRearDataRec, 0));
         var1 = 1002;
      }

      return var1;
   }

   private void getLastAirData() {
      try {
         String var1 = System.getString(this.mContentResolver, "211_LastFrontData");
         String var2 = System.getString(this.mContentResolver, "211_LastRearData");
         if (!TextUtils.isEmpty(var1)) {
            mFrontDataLast = Base64.decode(var1, 0);
         }

         if (!TextUtils.isEmpty(var2)) {
            mRearDataLast = Base64.decode(var2, 0);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private String getLightStatus() {
      String var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1 = "headlights_on";
      } else {
         var1 = "headlights_off";
      }

      return CommUtil.getStrByResId(this.mContext, var1);
   }

   private String getLightValue() {
      int var1 = this.mCanBusInfoInt[7];
      String var2;
      if (var1 == 100) {
         var2 = CommUtil.getStrByResId(this.mContext, "light_max");
      } else {
         var2 = Integer.toString(var1);
      }

      return var2;
   }

   private int getRadarData(int var1) {
      return var1 == 255 ? 0 : var1 + 1;
   }

   private boolean isFirst() {
      if (isAirFirst) {
         isAirFirst = false;
         if (!GeneralAirData.power) {
            return true;
         }
      }

      return false;
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var3;
      label26: {
         boolean var2 = GeneralAirData.fahrenheit_celsius;
         var3 = "HI";
         if (var2) {
            if (119 != var1) {
               if (171 != var1) {
                  var3 = (float)var1 / 2.0F + this.getTempUnitF(this.mContext);
               }
               break label26;
            }
         } else if (30 != var1) {
            if (60 != var1) {
               var3 = (float)var1 / 2.0F + this.getTempUnitC(this.mContext);
            }
            break label26;
         }

         var3 = "LO";
      }

      if (var1 == 0) {
         var3 = " ";
      }

      return var3;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      return (float)var1 / 2.0F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private String resolveRearTemp(int var1) {
      String var2;
      if (1 == var1) {
         var2 = "LO";
      } else if (9 == var1) {
         var2 = "HI";
      } else if (2 <= var1 && var1 <= 8) {
         var2 = var1 + "";
      } else {
         var2 = "OFF";
      }

      return var2;
   }

   private void set0x41RadarInfo() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(8, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setAirData0x31() {
      byte[] var2 = this.bytesExpectOneByte(this.mCanBusInfoByte, 13);
      this.setOutDoorTem();
      this.getLastAirData();
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var2)) {
         if (!this.isFirst()) {
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
            this.cleanAllBlow();
            int var1 = this.mCanBusInfoInt[6];
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
            GeneralAirData.rear_temperature = this.resolveRearTemp(this.mCanBusInfoInt[12]);
            this.updateAirActivity(this.mContext, this.getAirWhat());
         }
      }
   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVehicleInfo0x11() {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(0, 0, this.getLightStatus()));
      var2.add(new DriverUpdateEntity(0, 1, this.getLightValue()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 8) {
                     if (var1 != 9) {
                        if (var1 != 11) {
                           if (var1 != 22) {
                              if (var1 != 23) {
                                 switch (var1) {
                                    case 13:
                                       this.realKeyLongClick1(this.mContext, 45, var3[5]);
                                       break;
                                    case 14:
                                       this.realKeyLongClick1(this.mContext, 46, var3[5]);
                                       break;
                                    case 15:
                                       this.realKeyLongClick1(this.mContext, 49, var3[5]);
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 48, var3[5]);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 47, var3[5]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 2, var3[5]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 21, var3[5]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 20, var3[5]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 0, var3[5]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var3[5]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var3[5]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var3[5]);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      this.mContentResolver = var1.getContentResolver();
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 49) {
            if (var3 != 65) {
               if (var3 == 240) {
                  this.setVersionInfo();
               }
            } else {
               this.set0x41RadarInfo();
            }
         } else {
            this.setAirData0x31();
         }
      } else {
         this.setVehicleInfo0x11();
         this.setTrackInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
   }
}
