package com.hzbhd.canbus.car._307;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private boolean isFirstAir = true;
   private boolean isFirstDoor = true;
   private int mCallStatus = -1;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean rearPower;
   private String rearTemperature;
   private int rearWind;

   private void airInfo0x31() {
      boolean var3 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var9 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      boolean var6 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var7 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      boolean var4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      boolean var5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      boolean var8 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.power = var3;
      GeneralAirData.rear_power = var2;
      GeneralAirData.auto = var9;
      GeneralAirData.sync = var6;
      GeneralAirData.ac = var7;
      GeneralAirData.in_out_cycle = var4 ^ true;
      GeneralAirData.rear_defog = var5;
      GeneralAirData.front_defog = var8;
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_foot = false;
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 6) {
               switch (var1) {
                  case 11:
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     break;
                  case 12:
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     break;
                  case 13:
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                     break;
                  case 14:
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[9]);
      GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
      GeneralAirData.rear_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[12]);
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      if (this.isFirstAir) {
         this.isFirstAir = false;
         this.rearPower = GeneralAirData.rear_power;
         this.rearWind = GeneralAirData.rear_wind_level;
         this.rearTemperature = GeneralAirData.rear_temperature;
      } else {
         if (this.rearPower == var2 && this.rearWind == this.mCanBusInfoInt[11] && GeneralAirData.rear_temperature.equals(this.rearTemperature)) {
            this.updateAirActivity(this.mContext, 1001);
         } else {
            this.updateAirActivity(this.mContext, 1002);
         }

         this.rearPower = GeneralAirData.rear_power;
         this.rearWind = GeneralAirData.rear_wind_level;
         this.rearTemperature = GeneralAirData.rear_temperature;
      }
   }

   private void carInfo0x32() {
      int[] var4 = this.mCanBusInfoInt;
      int var1 = var4[4];
      int var2 = var4[5];
      int var3 = var4[6] * 256 + var4[7];
      ArrayList var5 = new ArrayList();
      var5.add(new DriverUpdateEntity(0, 7, var1 * 256 + var2 + " rmp/min"));
      var5.add(new DriverUpdateEntity(0, 8, var3 + " km/h"));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(var3);
   }

   private void carSettings0x61() {
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1);
      ArrayList var15 = new ArrayList();
      var15.add(new SettingUpdateEntity(0, 0, var11));
      var15.add(new SettingUpdateEntity(0, 1, var1));
      var15.add(new SettingUpdateEntity(0, 2, var8));
      var15.add(new SettingUpdateEntity(0, 3, var5));
      var15.add(new SettingUpdateEntity(0, 4, var2));
      var15.add(new SettingUpdateEntity(0, 5, var12));
      var15.add(new SettingUpdateEntity(0, 6, var4));
      var15.add(new SettingUpdateEntity(0, 7, var9));
      var15.add(new SettingUpdateEntity(0, 8, var10));
      var15.add(new SettingUpdateEntity(0, 9, var3));
      var15.add(new SettingUpdateEntity(0, 10, var6));
      var15.add(new SettingUpdateEntity(0, 11, var14));
      var15.add(new SettingUpdateEntity(0, 12, var7));
      var15.add(new SettingUpdateEntity(0, 13, var13));
      this.updateGeneralSettingData(var15);
      this.updateSettingActivity((Bundle)null);
   }

   private void doorStatus0x12() {
      GeneralDoorData.isShowMasterDriverSeatBelt = true;
      GeneralDoorData.isShowCoPilotSeatBelt = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private String getBigLight(int var1) {
      if (var1 == 1) {
         return this.mContext.getString(2131762110);
      } else {
         return var1 == 2 ? this.mContext.getString(2131762119) : this.mContext.getString(2131762099);
      }
   }

   private String getLight(boolean var1, boolean var2) {
      if (var2 && var1) {
         return this.mContext.getString(2131762122);
      } else if (var1) {
         return this.mContext.getString(2131762120);
      } else {
         return var2 ? this.mContext.getString(2131762121) : this.mContext.getString(2131758332);
      }
   }

   private String getLockStatus(int var1) {
      if (var1 == 1) {
         return this.mContext.getString(2131762124);
      } else {
         return var1 == 2 ? this.mContext.getString(2131762125) : this.mContext.getString(2131762123);
      }
   }

   private int getRadarInt(int var1) {
      if (var1 == 1) {
         return 7;
      } else if (var1 == 2) {
         return 6;
      } else if (var1 == 3) {
         return 5;
      } else if (var1 == 4) {
         return 4;
      } else if (var1 == 5) {
         return 3;
      } else if (var1 == 6) {
         return 2;
      } else {
         return var1 == 7 ? 1 : 255;
      }
   }

   private int getRadioData1(String var1) {
      if (var1.contains("FM2")) {
         return 2;
      } else if (var1.contains("FM3")) {
         return 3;
      } else if (var1.contains("AM1")) {
         return 4;
      } else {
         return var1.contains("AM2") ? 5 : 1;
      }
   }

   private String getRadioUnit(String var1) {
      return var1.contains("AM") ? "KHz" : "MHz";
   }

   private void keyEvent0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 8) {
         if (var1 != 9) {
            if (var1 != 24) {
               if (var1 != 34) {
                  switch (var1) {
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
                        this.realKeyClick(3);
                        break;
                     case 4:
                        this.realKeyClick(187);
                        break;
                     case 5:
                        this.realKeyClick(14);
                        break;
                     case 6:
                        this.realKeyClick(15);
                        break;
                     default:
                        switch (var1) {
                           case 13:
                              this.realKeyClick(45);
                              break;
                           case 14:
                              this.realKeyClick(46);
                              break;
                           case 15:
                              this.realKeyClick(49);
                              break;
                           case 16:
                              this.realKeyClick(50);
                        }
                  }
               } else {
                  this.realKeyClick(52);
               }
            } else {
               this.realKeyClick(4);
            }
         } else {
            this.realKeyClick(46);
         }
      } else {
         this.realKeyClick(45);
      }

   }

   private void panelEvent0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 6) {
                     if (var1 != 40) {
                        if (var1 != 43) {
                           if (var1 != 44) {
                              if (var1 != 69) {
                                 if (var1 == 70) {
                                    this.panelKeyClick(8);
                                 }
                              } else {
                                 this.panelKeyClick(7);
                              }
                           } else {
                              this.panelKeyClick(2);
                           }
                        } else {
                           this.panelKeyClick(52);
                        }
                     } else {
                        this.panelKeyClick(68);
                     }
                  } else {
                     this.panelKeyClick(50);
                  }
               } else {
                  this.startActivity(Constant.CanBusMainActivity);
               }
            } else {
               this.panelKeyClick(46);
            }
         } else {
            this.panelKeyClick(134);
         }
      } else {
         this.panelKeyClick(0);
      }

   }

   private void panelKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void radarInfo0x41() {
      RadarInfoUtil.setRearRadarLocationData(7, this.getRadarInt(this.mCanBusInfoInt[2]), this.getRadarInt(this.mCanBusInfoInt[3]), this.getRadarInt(this.mCanBusInfoInt[4]), this.getRadarInt(this.mCanBusInfoInt[5]));
      RadarInfoUtil.setFrontRadarLocationData(7, this.getRadarInt(this.mCanBusInfoInt[6]), this.getRadarInt(this.mCanBusInfoInt[7]), this.getRadarInt(this.mCanBusInfoInt[8]), this.getRadarInt(this.mCanBusInfoInt[9]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      String var2;
      if (var1 >= 0 && var1 <= 250) {
         var2 = (float)var1 * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void send0xE0(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 0, 0});
   }

   private void sendMsg1(int var1, String var2) {
      byte var3 = (byte)var1;
      byte[] var4 = DataHandleUtils.makeBytesFixedLength(var2.getBytes(), 30);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 1, var3}, var4));
   }

   private void sendMsg1_off(int var1, String var2) {
      byte var3 = (byte)var1;
      byte[] var4 = DataHandleUtils.makeBytesFixedLength(var2.getBytes(), 30);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, var3}, var4));
   }

   private void sendMsg2(String var1) {
      byte[] var2 = DataHandleUtils.makeBytesFixedLength(var1.getBytes(), 32);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -30}, var2));
   }

   private void sendMsg3(String var1) {
      byte[] var2 = DataHandleUtils.makeBytesFixedLength(var1.getBytes(), 32);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -29}, var2));
   }

   private void sendPhone(int var1, byte[] var2) {
      byte var3 = (byte)var1;
      var2 = DataHandleUtils.makeBytesFixedLength(var2, 24);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -51, var3, 0, 0}, var2));
   }

   private void setMedia0xE0() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 32) {
            if (var1 == 33) {
               this.realKeyClick(this.mContext, 76);
            }
         } else {
            this.realKeyClick(this.mContext, 77);
         }
      } else {
         this.enterNoSource();
         this.realKeyClick(this.mContext, 52);
      }

   }

   private void setPhoneState0x45() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 0 || var1 == 9 || var1 == 10) {
         this.realKeyClick4(this.mContext, 14);
      }

   }

   private void setPhoneState0xC5() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 5) {
                  this.realKeyClick(this.mContext, 15);
               }
            } else {
               this.realKeyClick(this.mContext, 15);
            }
         } else {
            this.realKeyClick(this.mContext, 14);
         }
      } else {
         this.realKeyClick4(this.mContext, 0);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void signalInfo0x11() {
      boolean var9 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      boolean var8 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var4 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      boolean var7 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2);
      boolean var6 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10]);
      boolean var5 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
      ArrayList var11 = new ArrayList();
      int var1;
      Context var10;
      if (var9) {
         var10 = this.mContext;
         var1 = 2131768701;
      } else {
         var10 = this.mContext;
         var1 = 2131768699;
      }

      var11.add(new DriverUpdateEntity(0, 0, var10.getString(var1)));
      if (var8) {
         var10 = this.mContext;
         var1 = 2131769841;
      } else {
         var10 = this.mContext;
         var1 = 2131769410;
      }

      var11.add(new DriverUpdateEntity(0, 1, var10.getString(var1)));
      var10 = this.mContext;
      if (var4) {
         var1 = 2131768711;
      } else {
         var1 = 2131768710;
      }

      var11.add(new DriverUpdateEntity(0, 2, var10.getString(var1)));
      var10 = this.mContext;
      if (var7) {
         var1 = 2131767736;
      } else {
         var1 = 2131767735;
      }

      var11.add(new DriverUpdateEntity(0, 3, var10.getString(var1)));
      var11.add(new DriverUpdateEntity(0, 4, this.getBigLight(var3)));
      var11.add(new DriverUpdateEntity(0, 5, this.getLight(var6, var5)));
      var11.add(new DriverUpdateEntity(0, 6, this.getLockStatus(var2)));
      this.updateGeneralDriveData(var11);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void startActivity(ComponentName var1) {
      Intent var2 = new Intent();
      var2.setComponent(var1);
      var2.setFlags(268435456);
      this.mContext.startActivity(var2);
   }

   private void track0x11() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   public void atvDestdroy() {
      super.atvDestdroy();
      this.sendMsg1(0, " ");
      this.sendMsg2(" ");
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMsg1(8, " ");
      this.sendMsg2(" ");
   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      this.sendMsg1(0, " ");
      this.sendMsg2(" ");
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMsg1(12, " ");
      this.sendMsg2(" ");
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      this.sendMsg1(10, var1);
      this.sendMsg2(var2);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.sendMsg1(0, " ");
      this.sendMsg2(" ");
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.mCallStatus = -1;
      this.sendPhone(5, var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.mCallStatus = 1;
      this.sendPhone(1, var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.mCallStatus = 2;
      this.sendPhone(2, var1);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var3) {
         if (this.mCallStatus != -1) {
            this.sendPhone(0, var2);
         }
      } else {
         this.sendPhone(6, var2);
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      this.sendPhone(4, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 65) {
                  if (var3 != 69) {
                     if (var3 != 97) {
                        if (var3 != 197) {
                           if (var3 != 224) {
                              if (var3 != 240) {
                                 if (var3 != 49) {
                                    if (var3 == 50) {
                                       this.carInfo0x32();
                                    }
                                 } else {
                                    if (this.isAirMsgRepeat(var2)) {
                                       return;
                                    }

                                    this.airInfo0x31();
                                 }
                              } else {
                                 this.setVersionInfo();
                              }
                           } else {
                              this.setMedia0xE0();
                           }
                        } else {
                           this.setPhoneState0xC5();
                        }
                     } else {
                        this.carSettings0x61();
                     }
                  } else {
                     this.setPhoneState0x45();
                  }
               } else {
                  this.radarInfo0x41();
               }
            } else {
               this.panelEvent0x21();
            }
         } else {
            if (this.isDoorMsgRepeat(var2)) {
               return;
            }

            if (this.isFirstDoor) {
               this.isFirstDoor = false;
               return;
            }

            this.doorStatus0x12();
         }
      } else {
         this.signalInfo0x11();
         this.keyEvent0x11();
         this.track0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var8, (byte)var6, 0, 0, (byte)var10, (byte)var2, (byte)var3, (byte)var4, (byte)var9});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 != 1 && var7 != 5) {
         this.sendMsg1(6, " ");
      } else {
         this.sendMsg1(7, " ");
      }

      this.sendMsg2(" ");
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMsg1(8, " ");
      this.sendMsg2(" ");
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.sendMsg1(0, " ");
      this.sendMsg2(" ");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.sendMsg1(13, var21);
      this.sendMsg2(var23);
   }

   public void radioDestroy() {
      super.radioDestroy();
      this.sendMsg1(0, " ");
      this.sendMsg2(" ");
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.sendMsg1(this.getRadioData1(var2), var3 + " " + this.getRadioUnit(var2));
      this.sendMsg2(" ");
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.stringGetBytes(var1, "UTF-8"), 20));
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (var1) {
         this.sendMsg1_off(0, " ");
      } else {
         this.sendMsg1(0, " ");
      }

      this.sendMsg2(" ");
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoDestroy() {
      super.videoDestroy();
      this.sendMsg1(0, " ");
      this.sendMsg2(" ");
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      this.sendMsg1(13, var3 + "/" + var4);
      this.sendMsg2(var11 + ":" + var8 + ":" + var13);
   }
}
