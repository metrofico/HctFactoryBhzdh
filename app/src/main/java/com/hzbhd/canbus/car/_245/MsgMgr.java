package com.hzbhd.canbus.car._245;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MsgMgr extends AbstractMsgMgr {
   private double avgFuelConsumption;
   private int avgSpeed;
   public int currentCanDifferentId;
   private double currentFuelConsumption;
   private final DecimalFormat df0 = new DecimalFormat("0.0");
   private final DecimalFormat df00 = new DecimalFormat("0.00");
   public final Map differentIdMap = new HashMap();
   private String distance;
   private int kmOrMi = 0;
   public int[] leftIndex;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int range;
   public int[] rightIndex;
   public final Map settingPageIndex = new HashMap();
   private double travelDistance;
   private String travelTime;
   private UiMgr uiMgr = null;

   private void backLightInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, String.valueOf(this.mCanBusInfoInt[2])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.setBacklightLevel(this.mCanBusInfoInt[2] / 51 + 1);
   }

   private void cornerInfo(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 0, 540, 16);
      this.updateParkUi((Bundle)null, var1);
   }

   private void frontRadar(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      int var4 = var5[3];
      int var3 = var5[4];
      RadarInfoUtil.setFrontRadarLocationData(10, var2, var4, var5[5], var3);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void generalInfo(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      this.updateDoorView(var1);
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private int getLsb(int var1) {
      return var1 & '\uffff' & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private UiMgr getUigMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void initIdMap() {
      ArrayList var3 = new ArrayList();
      var3.add(4);
      Integer var2 = 5;
      var3.add(var2);
      Integer var1 = 7;
      var3.add(var1);
      this.differentIdMap.put(64, var3);
      this.differentIdMap.put(128, var3);
      var3 = new ArrayList();
      var3.add(var2);
      this.differentIdMap.put(148, var3);
      var3 = new ArrayList();
      var3.add(var2);
      var3.add(6);
      var3.add(var1);
      var3.add(8);
      var3.add(9);
      this.differentIdMap.put(192, var3);
      ArrayList var4 = new ArrayList();
      var4.add(var1);
      this.differentIdMap.put(197, var4);
   }

   private void instructTimeInfo() {
      ArrayList var4 = new ArrayList();
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_245_driveInfo_0");
      int var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_245_driveInfo_0_1");
      StringBuilder var5 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var3[2], var3[3])).append("/").append(Integer.toHexString(this.mCanBusInfoByte[4] & 255)).append("/").append(Integer.toHexString(this.mCanBusInfoByte[5] & 255)).append("  ").append(Integer.toHexString(this.mCanBusInfoByte[6] & 255)).append(":").append(Integer.toHexString(this.mCanBusInfoByte[7] & 255)).append(":").append(Integer.toHexString(this.mCanBusInfoByte[8] & 255)).toString()));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      this.setBacklightLevel(this.mCanBusInfoInt[2] / 51 + 1);
   }

   private void keyClick() {
      int var1 = this.mCanBusInfoInt[2];
      switch (var1) {
         case 0:
            this.reaKeyClick1(0);
            break;
         case 1:
            this.reaKeyClick1(7);
            break;
         case 2:
            this.reaKeyClick1(8);
            break;
         case 3:
            this.reaKeyClick1(45);
            break;
         case 4:
            this.reaKeyClick1(46);
            break;
         case 5:
            this.reaKeyClick1(14);
            break;
         case 6:
            this.reaKeyClick1(3);
            break;
         case 7:
            this.reaKeyClick1(2);
            break;
         case 8:
            this.reaKeyClick1(49);
            break;
         case 9:
            this.reaKeyClick1(14);
            break;
         case 10:
            this.reaKeyClick1(15);
            break;
         default:
            switch (var1) {
               case 16:
                  this.reaKeyClick1(187);
                  break;
               case 17:
                  this.reaKeyClick1(129);
                  break;
               case 18:
                  this.reaKeyClick1(151);
            }
      }

   }

   private void outdoorTemp(Context var1) {
      if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         this.updateOutDoorTemp(var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°C");
      } else {
         this.updateOutDoorTemp(var1, "-" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°C");
      }

   }

   private void reaKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private void rearRadar(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(10, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (31 == var1) {
         var2 = "HI";
      } else if (1 <= var1 && 17 >= var1) {
         var2 = (double)var1 * 0.5 + 17.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void sendPhoneNumber(byte[] var1) {
      int[] var2 = this.getByteArrayToIntArray(var1);
      if (var2.length == 11) {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 16, 0, (byte)var2[0], 0, (byte)var2[1], 0, (byte)var2[2], 0, (byte)var2[3], 0, (byte)var2[4], 0, (byte)var2[5], 0, (byte)var2[6], 0, (byte)var2[7], 0, (byte)var2[8], 0, (byte)var2[9], 0, (byte)var2[10]});
      }

      if (var2.length == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 16, 0, (byte)var2[0], 0, (byte)var2[1], 0, (byte)var2[2], 0, (byte)var2[3], 0, (byte)var2[4]});
      }

      if (var2.length == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 16, 0, (byte)var2[0], 0, (byte)var2[1], 0, (byte)var2[2], 0, (byte)var2[3], 0, (byte)var2[4], 0, (byte)var2[5], 0, (byte)var2[6]});
      }

   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.big_wind_light = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.small_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      if (var1 == 9) {
         GeneralAirData.front_wind_level = 0;
      } else {
         GeneralAirData.front_wind_level = var1;
      }

      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_window = true;
         GeneralAirData.front_right_blow_window = true;
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void settingItem(Context var1) {
      ArrayList var3 = new ArrayList();
      int[] var4 = this.mCanBusInfoInt;
      int var2 = var4[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 != 32) {
                        if (var2 != 33) {
                           if (var2 != 57) {
                              switch (var2) {
                                 case 16:
                                    var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_0"), this.mCanBusInfoInt[3]));
                                    break;
                                 case 17:
                                    var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_1"), this.mCanBusInfoInt[3]));
                                    break;
                                 case 18:
                                    var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_2"), this.mCanBusInfoInt[3]));
                                    break;
                                 case 19:
                                    var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_3"), this.mCanBusInfoInt[3]));
                                    break;
                                 case 20:
                                    var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_4"), this.mCanBusInfoInt[3]));
                                    break;
                                 case 21:
                                    var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_5"), this.mCanBusInfoInt[3]));
                                    break;
                                 default:
                                    switch (var2) {
                                       case 48:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_0"), this.mCanBusInfoInt[3]));
                                          break;
                                       case 49:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_1"), this.mCanBusInfoInt[3]));
                                          break;
                                       case 50:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_2"), this.mCanBusInfoInt[3]));
                                          break;
                                       case 51:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_3"), this.mCanBusInfoInt[3]));
                                          break;
                                       case 52:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_4"), this.mCanBusInfoInt[3]));
                                          break;
                                       case 53:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_5"), this.mCanBusInfoInt[3]));
                                          break;
                                       case 54:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_6"), this.mCanBusInfoInt[3]));
                                          break;
                                       case 55:
                                          var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_7"), this.mCanBusInfoInt[3]));
                                          break;
                                       default:
                                          switch (var2) {
                                             case 240:
                                                var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_0"), this.mCanBusInfoInt[3]));
                                                break;
                                             case 241:
                                                var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_1"), this.mCanBusInfoInt[3]));
                                                break;
                                             case 242:
                                                var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_2"), this.mCanBusInfoInt[3]));
                                                break;
                                             case 243:
                                                var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_3"), this.mCanBusInfoInt[3]));
                                                break;
                                             case 244:
                                                var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_4"), this.mCanBusInfoInt[3]));
                                          }
                                    }
                              }
                           } else {
                              var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_5"), this.mCanBusInfoInt[3]));
                           }
                        } else {
                           var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_3"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_3", "_245_setting_3_1"), this.mCanBusInfoInt[3]));
                        }
                     } else {
                        var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_3"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_3", "_245_setting_3_0"), this.mCanBusInfoInt[3]));
                     }
                  } else {
                     var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_3"), this.mCanBusInfoInt[3]));
                  }
               } else {
                  var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_2"), this.mCanBusInfoInt[3]));
               }
            } else {
               var2 = var4[3];
               if (var2 != 0) {
                  if (var2 == 1) {
                     var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_1"), 1));
                     this.distance = "MI";
                  }
               } else {
                  var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_1"), 0));
                  this.distance = "KM";
               }
            }
         } else {
            var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_0"), this.mCanBusInfoInt[3]));
         }
      } else {
         var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_0"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_0", "_245_setting_0_0"), this.mCanBusInfoInt[3]));
      }

      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void tripInfo(Context var1) {
      ArrayList var3 = new ArrayList();
      int[] var4 = this.mCanBusInfoInt;
      int var2 = var4[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 == 2) {
               this.avgFuelConsumption = (double)(var4[3] * 256 + var4[4]) * 0.1;
               this.avgSpeed = var4[5];
               this.travelDistance = (double)(var4[6] * 256 * 256 + var4[7] * 256 + var4[8]) * 0.1;
               StringBuilder var6 = new StringBuilder();
               int[] var5 = this.mCanBusInfoInt;
               this.travelTime = var6.append(var5[11] * 256 + var5[9]).append(":").append(this.mCanBusInfoInt[10]).toString();
               var3.add(new DriverUpdateEntity(3, 0, CommUtil.getStrByResId(var1, "_245_driveInfo_1_02")));
               var3.add(new DriverUpdateEntity(3, 1, this.df0.format(this.avgFuelConsumption) + "km/l"));
               var3.add(new DriverUpdateEntity(3, 2, this.df0.format((long)this.avgSpeed) + "km/h"));
               var3.add(new DriverUpdateEntity(3, 3, this.df0.format(this.travelDistance) + "km"));
               var3.add(new DriverUpdateEntity(3, 4, this.travelTime));
            }
         } else {
            this.avgFuelConsumption = (double)(var4[3] * 256 + var4[4]) * 0.1;
            this.avgSpeed = var4[5];
            this.travelDistance = (double)(var4[6] * 256 * 256 + var4[7] * 256 + var4[8]) * 0.1;
            StringBuilder var7 = new StringBuilder();
            var4 = this.mCanBusInfoInt;
            this.travelTime = var7.append(var4[11] * 256 + var4[9]).append(":").append(this.mCanBusInfoInt[10]).toString();
            var3.add(new DriverUpdateEntity(2, 0, CommUtil.getStrByResId(var1, "_245_driveInfo_1_01")));
            var3.add(new DriverUpdateEntity(2, 1, this.df0.format(this.avgFuelConsumption) + "km/l"));
            var3.add(new DriverUpdateEntity(2, 2, this.df0.format((long)this.avgSpeed) + "km/h"));
            var3.add(new DriverUpdateEntity(2, 3, this.df0.format(this.travelDistance) + "km"));
            var3.add(new DriverUpdateEntity(2, 4, this.travelTime));
         }
      } else {
         this.range = var4[3] * 256 + var4[4];
         this.currentFuelConsumption = (double)(var4[5] * 256 + var4[6]) * 0.1;
         var3.add(new DriverUpdateEntity(1, 0, CommUtil.getStrByResId(var1, "_245_driveInfo_1_00")));
         var3.add(new DriverUpdateEntity(1, 1, this.range + "km"));
         var3.add(new DriverUpdateEntity(1, 2, this.df0.format(this.currentFuelConsumption)));
      }

      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void version(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 0, 0, 0, 0, 1});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)this.getDecimalFrom8Bit(0, 0, 0, 1, 0, 1, 0, 1)});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)this.getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 0, 1)});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)this.getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 1, 0)});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.sendPhoneNumber(var2);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      this.sendPhoneNumber(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)this.getDecimalFrom8Bit(0, 0, 0, 1, 0, 1, 0, 0)});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 == 38) {
            this.instructTimeInfo();
            return;
         }

         if (var3 == 48) {
            this.version(var1);
            return;
         }

         if (var3 == 64) {
            this.settingItem(var1);
            return;
         }

         if (var3 == 80) {
            this.tripInfo(var1);
            return;
         }

         if (var3 == 40) {
            this.outdoorTemp(var1);
            return;
         }

         if (var3 == 41) {
            this.cornerInfo(var1);
            return;
         }

         switch (var3) {
            case 32:
               break;
            case 33:
               if (this.isAirMsgRepeat(var2)) {
                  return;
               }

               this.setAirData0x21();
               return;
            case 34:
               this.rearRadar(var1);
               return;
            case 35:
               this.frontRadar(var1);
               return;
            case 36:
               this.generalInfo(var1);
               return;
            default:
               return;
         }
      } else {
         this.backLightInfo();
      }

      this.keyClick();
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var2 = this.getMsb(Integer.parseInt(String.valueOf(var1), 16));
      var1 = this.getLsb(Integer.parseInt(String.valueOf(var1), 16));
      var3 = Integer.parseInt(String.valueOf(var3), 16);
      var4 = Integer.parseInt(String.valueOf(var4), 16);
      var5 = Integer.parseInt(String.valueOf(var8), 16);
      var6 = Integer.parseInt(String.valueOf(var6), 16);
      var7 = Integer.parseInt(String.valueOf(var7), 16);
      CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte)var2, (byte)var1, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, (byte)this.getLsb(var6), (byte)this.getMsb(var6), (byte)this.getLsb(var5), (byte)this.getMsb(var5), 1});
   }

   public int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.currentCanDifferentId = this.getCurrentCanDifferentId();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, (boolean)var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 6, (byte)this.getMsb(var4), (byte)this.getLsb(var4), (byte)this.getMsb(var17), (byte)this.getLsb(var17), (byte)var18});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 4, (byte)this.getMsb(var4), (byte)this.getLsb(var4), (byte)this.getMsb(var17), (byte)this.getLsb(var17), (byte)var18});
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      int var6 = (int)(Double.parseDouble(var3) * 100.0);
      int var7 = (int)Double.parseDouble(var3);
      var2.hashCode();
      int var8 = var2.hashCode();
      byte var9 = -1;
      switch (var8) {
         case 2092:
            if (var2.equals("AM")) {
               var9 = 0;
            }
            break;
         case 2247:
            if (var2.equals("FM")) {
               var9 = 1;
            }
            break;
         case 64901:
            if (var2.equals("AM1")) {
               var9 = 2;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var9 = 3;
            }
            break;
         case 64903:
            if (var2.equals("AM3")) {
               var9 = 4;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var9 = 5;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var9 = 6;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var9 = 7;
            }
      }

      byte var10;
      byte var11;
      label77: {
         label67: {
            label66: {
               switch (var9) {
                  case 0:
                     var5 = this.getLsb(var7);
                     var6 = this.getMsb(var7);
                     var11 = 0;
                     break;
                  case 1:
                     var5 = this.getLsb(var6);
                     var6 = this.getMsb(var6);
                     var11 = 0;
                     break label66;
                  case 2:
                     var5 = this.getLsb(var7);
                     var6 = this.getMsb(var7);
                     var10 = 2;
                     var11 = 1;
                     break label77;
                  case 3:
                     var5 = this.getLsb(var7);
                     var6 = this.getMsb(var7);
                     var10 = 2;
                     break label67;
                  case 4:
                     var5 = this.getLsb(var7);
                     var6 = this.getMsb(var7);
                     var11 = 3;
                     break;
                  case 5:
                     var5 = this.getLsb(var6);
                     var6 = this.getMsb(var6);
                     var10 = 1;
                     break label67;
                  case 6:
                     var5 = this.getLsb(var6);
                     var6 = this.getMsb(var6);
                     var11 = 2;
                     break label66;
                  case 7:
                     var5 = this.getLsb(var6);
                     var6 = this.getMsb(var6);
                     var11 = 3;
                     break label66;
                  default:
                     throw new IllegalStateException("Unexpected value: " + var2);
               }

               var10 = 2;
               break label77;
            }

            var10 = 1;
            break label77;
         }

         var11 = var10;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var10, (byte)var6, (byte)var5, (byte)var1, (byte)var11, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, (boolean)var16, var17);
      if (var1 == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 6, (byte)this.getMsb(var4), (byte)this.getLsb(var4), var9, (byte)var3, (byte)var16});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 4, (byte)this.getMsb(var4), (byte)this.getLsb(var4), var9, (byte)var3, (byte)var16});
      }

   }
}
