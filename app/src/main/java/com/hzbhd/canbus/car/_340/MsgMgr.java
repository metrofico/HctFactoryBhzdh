package com.hzbhd.canbus.car._340;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MsgMgr extends AbstractMsgMgr {
   public static int[] index;
   private final int currentCanDifferentId = this.getCurrentCanDifferentId();
   private boolean flagIn = false;
   private boolean flagOut = false;
   private int lastParam = 0;
   int[] mAirData;
   public byte[] mCanBusInfoByte;
   public int[] mCanBusInfoInt;
   private UiMgr mUiMgr = null;
   public int nowLeftValue = 40;
   public int nowRightValue = 40;
   public final Map settingPageIndex = new HashMap();

   private void airInfo(Context var1) {
      if (!this.isAirDataNoChange()) {
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         boolean var3;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.in_out_cycle = var3;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         int var2 = this.mCanBusInfoInt[3];
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 != 4) {
                        if (var2 == 5) {
                           GeneralAirData.front_left_blow_head = false;
                           GeneralAirData.front_left_blow_foot = false;
                           GeneralAirData.front_defog = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = false;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_defog = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_defog = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_defog = false;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_defog = false;
            }
         } else {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_defog = false;
         }

         if (this.mCanBusInfoInt[4] == 0) {
            GeneralAirData.front_wind_level = this.mCanBusInfoByte[4];
            GeneralAirData.power = false;
         } else {
            GeneralAirData.front_wind_level = this.mCanBusInfoByte[4];
         }

         var2 = this.mCanBusInfoInt[5];
         String var4;
         if (var2 == 254) {
            GeneralAirData.front_left_temperature = "Low";
            this.nowLeftValue = 254;
         } else if (var2 == 255) {
            GeneralAirData.front_left_temperature = "High";
            this.nowLeftValue = 255;
         } else if (var2 != 0) {
            this.nowLeftValue = var2;
            GeneralAirData.front_left_temperature = (double)this.mCanBusInfoInt[5] / 2.0 + "°C";
            var4 = GeneralAirData.front_left_temperature;
         } else {
            this.nowLeftValue = 20;
            GeneralAirData.front_left_temperature = "--";
         }

         var2 = this.mCanBusInfoInt[6];
         if (var2 == 254) {
            GeneralAirData.front_right_temperature = "Low";
            this.nowRightValue = 254;
         } else if (var2 == 255) {
            GeneralAirData.front_right_temperature = "High";
            this.nowRightValue = 255;
         } else if (var2 != 0) {
            this.nowRightValue = var2;
            GeneralAirData.front_right_temperature = (double)this.mCanBusInfoInt[6] / 2.0 + "°C";
            var4 = GeneralAirData.front_right_temperature;
         } else {
            this.nowRightValue = 20;
            GeneralAirData.front_right_temperature = "--";
         }

         this.updateAirActivity(var1, 1001);
      }
   }

   private void espInfo(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 8600, 16);
      this.updateParkUi((Bundle)null, var1);
   }

   private void frontRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void generalInfo(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      this.updateDoorView(var1);
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private void knob(Context var1, int var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         this.realKeyClick4(var1, var2);
      }

   }

   private void rearRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void sendPhoneData(int var1, byte[] var2) {
      String var3 = Arrays.toString(var2);
      Log.i("lyn", var3 + "");
      byte[] var4 = var3.getBytes(StandardCharsets.UTF_8);
      Log.i("lyn", var4 + "");
      CanbusMsgSender.sendMsg(this.byteMerger(new byte[]{22, -64, 5, (byte)var1, 18, 2, (byte)var2.length}, var2));
   }

   private void steeringWheelKeyInfo(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 6) {
                  label225: {
                     if (var2 != 7) {
                        switch (var2) {
                           case 9:
                              this.realKeyLongClick1(var1, 14, var3[3]);
                              return;
                           case 10:
                              this.realKeyLongClick1(var1, 15, var3[3]);
                              return;
                           case 11:
                              this.realKeyLongClick1(var1, 45, var3[3]);
                              return;
                           case 12:
                              this.realKeyLongClick1(var1, 46, var3[3]);
                              return;
                           case 13:
                              this.realKeyLongClick1(var1, 187, var3[3]);
                              return;
                           case 14:
                              break label225;
                           case 15:
                              this.realKeyLongClick1(var1, 188, var3[3]);
                              return;
                           default:
                              switch (var2) {
                                 case 32:
                                    this.realKeyClick4(var1, 1);
                                    return;
                                 case 33:
                                    this.knob(var1, 7, var3[3]);
                                    return;
                                 case 34:
                                    this.knob(var1, 8, var3[3]);
                                    return;
                                 case 35:
                                    this.realKeyLongClick1(var1, 49, var3[3]);
                                    return;
                                 case 36:
                                    this.knob(var1, 45, var3[3]);
                                    return;
                                 case 37:
                                    this.knob(var1, 46, var3[3]);
                                    return;
                                 case 38:
                                    this.realKeyLongClick1(var1, 77, var3[3]);
                                    return;
                                 case 39:
                                    this.realKeyLongClick1(var1, 76, var3[3]);
                                    return;
                                 case 40:
                                    break;
                                 case 41:
                                    break label225;
                                 case 42:
                                    this.realKeyLongClick1(var1, 134, var3[3]);
                                    return;
                                 case 43:
                                    this.realKeyLongClick1(var1, 58, var3[3]);
                                    return;
                                 case 44:
                                    this.realKeyLongClick1(var1, 151, var3[3]);
                                    return;
                                 case 45:
                                    this.realKeyLongClick1(var1, 52, var3[3]);
                                    return;
                                 case 46:
                                    this.realKeyLongClick1(var1, 21, var3[3]);
                                    return;
                                 case 47:
                                    this.realKeyLongClick1(var1, 20, var3[3]);
                                    return;
                                 case 48:
                                    this.realKeyLongClick1(var1, 128, var3[3]);
                                    return;
                                 case 49:
                                    this.realKeyLongClick1(var1, 50, var3[3]);
                                    return;
                                 case 50:
                                    this.realKeyLongClick1(var1, 30, var3[3]);
                                    return;
                                 default:
                                    return;
                              }
                        }
                     }

                     this.realKeyLongClick1(var1, 2, var3[3]);
                     return;
                  }

                  this.realKeyLongClick1(var1, 188, var3[3]);
               } else {
                  this.realKeyLongClick1(var1, 3, var3[3]);
               }
            } else {
               this.realKeyLongClick1(var1, 8, var3[3]);
            }
         } else {
            this.realKeyLongClick1(var1, 7, var3[3]);
         }
      } else {
         this.realKeyLongClick1(var1, 0, var3[3]);
      }

   }

   private void vehicleSettingInfo() {
      ArrayList var1 = new ArrayList();
      switch (this.mCanBusInfoInt[2]) {
         case 1:
            var1.add(new SettingUpdateEntity(0, index[1], this.mCanBusInfoInt[3] - 1));
         case 2:
         default:
            break;
         case 3:
            var1.add(new SettingUpdateEntity(0, index[3], this.mCanBusInfoInt[3] - 1));
            break;
         case 4:
            var1.add(new SettingUpdateEntity(0, index[4], this.mCanBusInfoInt[3] - 1));
            break;
         case 5:
            var1.add(new SettingUpdateEntity(0, index[5], this.mCanBusInfoInt[3] - 1));
            break;
         case 6:
            var1.add(new SettingUpdateEntity(0, index[6], this.mCanBusInfoInt[3] - 1));
            break;
         case 7:
            var1.add(new SettingUpdateEntity(0, index[7], this.mCanBusInfoInt[3] - 1));
            break;
         case 8:
            var1.add(new SettingUpdateEntity(0, index[8], this.mCanBusInfoInt[3] - 1));
            break;
         case 9:
            var1.add(new SettingUpdateEntity(0, index[9], this.mCanBusInfoInt[3] - 1));
            break;
         case 10:
            var1.add(new SettingUpdateEntity(0, index[10], this.mCanBusInfoInt[3] - 1));
            break;
         case 11:
            var1.add(new SettingUpdateEntity(0, index[11], this.mCanBusInfoInt[3] - 1));
            break;
         case 12:
            var1.add(new SettingUpdateEntity(0, index[12], this.mCanBusInfoInt[3] - 1));
            break;
         case 13:
            var1.add(new SettingUpdateEntity(0, index[13], this.mCanBusInfoInt[3] - 1));
            break;
         case 14:
            var1.add(new SettingUpdateEntity(0, index[14], this.mCanBusInfoInt[3] - 1));
            break;
         case 15:
            var1.add(new SettingUpdateEntity(0, index[15], this.mCanBusInfoInt[3] - 1));
            break;
         case 16:
            var1.add(new SettingUpdateEntity(0, index[16], this.mCanBusInfoInt[3] - 1));
            break;
         case 17:
            var1.add(new SettingUpdateEntity(0, index[17], this.mCanBusInfoInt[3] - 1));
            break;
         case 18:
            var1.add((new SettingUpdateEntity(0, index[18], (this.mCanBusInfoInt[3] - 1) * 5 + 30)).setProgress(this.mCanBusInfoInt[3] - 1));
            break;
         case 19:
            var1.add(new SettingUpdateEntity(0, index[19], this.mCanBusInfoInt[3] - 1));
            break;
         case 20:
            var1.add(new SettingUpdateEntity(0, index[20], this.mCanBusInfoInt[3] - 1));
            break;
         case 21:
            var1.add(new SettingUpdateEntity(0, index[21], this.mCanBusInfoInt[3] - 1));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void versionInfo(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void auxInInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, 7});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      byte[] var4 = var1.getBytes();
      byte[] var5 = var2.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 11, 18, 1, (byte)var4.length}, var4));
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 11, 18, 2, (byte)var5.length}, var5));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneData(0, var1);
      this.flagIn = false;
      this.flagOut = false;
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneData(1, var1);
      this.flagIn = true;
      this.flagOut = false;
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneData(3, var1);
      this.flagIn = false;
      this.flagOut = true;
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      if (this.lastParam != var3) {
         if (var3 != 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, -127});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, -128});
         }

         this.lastParam = var3;
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      if (this.flagIn) {
         this.sendPhoneData(2, var1);
      } else if (this.flagOut) {
         this.sendPhoneData(4, var1);
      }

   }

   public byte[] byteMerger(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 33) {
            if (var3 != 48) {
               if (var3 != 82) {
                  if (var3 != 127) {
                     switch (var3) {
                        case 38:
                           this.rearRadarInfo(var1);
                           break;
                        case 39:
                           this.frontRadarInfo(var1);
                           break;
                        case 40:
                           this.generalInfo(var1);
                     }
                  } else {
                     this.versionInfo(var1);
                  }
               } else if (this.currentCanDifferentId == 8) {
                  this.vehicleSettingInfo();
               }
            } else {
               this.espInfo(var1);
            }
         } else {
            this.steeringWheelKeyInfo(var1);
         }
      } else {
         var3 = this.currentCanDifferentId;
         if (var3 == 8 || var3 == 9) {
            this.airInfo(var1);
         }
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var1 = this.currentCanDifferentId;
      if (var1 == 8) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 1});
      } else if (var1 == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 2});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 0});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      GeneralDoorData.isShowHandBrake = true;
      this.initSettingPageIndex(var1);
      index = new int[]{(Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_restore_setting")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_automatic_folding")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_tire_pressure")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_scenarios_rain_snow_mode")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_scenarios_smoking_mode")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_scenarios_cool_mode")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_scenarios_warm_mode")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_remote_control_window_lowering")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_remote_control_active_entry")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_turn_off_the_engine_and_unlock_the_door_automatically")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_locking_tips")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_automatic_trip_lock")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_lane_change_flashing_light")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_indoor_light_delay")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_accompany_me_home")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_accompany_me_home_delay")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_looking_for_my_car")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_wiper_maintenance_function")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_adaptive_cruise_speed_setting")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_anti_collision_warning")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_anti_collision_warning_sensitivity")), (Integer)Objects.requireNonNull((Integer)this.settingPageIndex.get("_340_automatic_emergency_braking_system"))};
   }

   public Map initSettingPageIndex(Context var1) {
      List var4 = this.getUigMgr(var1).getSettingUiSet(var1).getList();
      Iterator var8 = var4.iterator();

      for(int var2 = 0; var2 < var4.size(); ++var2) {
         SettingPageUiSet.ListBean var5 = (SettingPageUiSet.ListBean)var8.next();
         this.settingPageIndex.put(var5.getTitleSrn(), var2);
         List var9 = var5.getItemList();
         Iterator var7 = var9.iterator();

         for(int var3 = 0; var3 < var9.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var7.next();
            this.settingPageIndex.put(var6.getTitleSrn(), var3);
         }
      }

      return this.settingPageIndex;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 != 9) {
         byte[] var25 = var21.getBytes();
         byte[] var26 = var23.getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, (byte)var25.length}, var25));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 2, (byte)var26.length}, var26));
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      Log.i("lyn", var2);
      var2.hashCode();
      int var6 = var2.hashCode();
      byte var8 = -1;
      switch (var6) {
         case 2092:
            if (var2.equals("AM")) {
               var8 = 0;
            }
            break;
         case 2247:
            if (var2.equals("FM")) {
               var8 = 1;
            }
            break;
         case 64901:
            if (var2.equals("AM1")) {
               var8 = 2;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var8 = 3;
            }
            break;
         case 64903:
            if (var2.equals("AM3")) {
               var8 = 4;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var8 = 5;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var8 = 6;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var8 = 7;
            }
      }

      int var7;
      switch (var8) {
         case 0:
            var8 = 16;
            var6 = Integer.parseInt(var3);
            var7 = this.getLsb(var6);
            var6 = this.getMsb(var6);
            break;
         case 1:
            var5 = (int)(Double.parseDouble(var3) * 100.0);
            var7 = this.getLsb(var5);
            var6 = this.getMsb(var5);
            var8 = 0;
            break;
         case 2:
            var8 = 17;
            var6 = Integer.parseInt(var3);
            var7 = this.getLsb(var6);
            var6 = this.getMsb(var6);
            break;
         case 3:
            var8 = 18;
            var6 = Integer.parseInt(var3);
            var7 = this.getLsb(var6);
            var6 = this.getMsb(var6);
            break;
         case 4:
            var8 = 19;
            var6 = Integer.parseInt(var3);
            var7 = this.getLsb(var6);
            var6 = this.getMsb(var6);
            break;
         case 5:
            var5 = (int)(Double.parseDouble(var3) * 100.0);
            var7 = this.getLsb(var5);
            var6 = this.getMsb(var5);
            var8 = 1;
            break;
         case 6:
            var5 = (int)(Double.parseDouble(var3) * 100.0);
            var7 = this.getLsb(var5);
            var6 = this.getMsb(var5);
            var8 = 2;
            break;
         case 7:
            var5 = (int)(Double.parseDouble(var3) * 100.0);
            var7 = this.getLsb(var5);
            var6 = this.getMsb(var5);
            var8 = 3;
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + var2);
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte)var8, (byte)var7, (byte)var6, (byte)var1});
   }
}
