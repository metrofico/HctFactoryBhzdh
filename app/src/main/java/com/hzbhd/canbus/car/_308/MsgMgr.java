package com.hzbhd.canbus.car._308;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private byte carInfoData0 = 0;
   private byte carInfoData1 = 0;
   private boolean carInfoFirst = false;
   private boolean isFM;
   private boolean isNight = false;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private void airInfo0x05() {
      GeneralAirData.ac = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_foot = false;
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 == 5) {
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_window = true;
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

      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      this.updateAirActivity(this.mContext, 1001);
   }

   private void amplifierInfo0x13() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.frontRear = this.getSurroundVolFRprogress(this.mCanBusInfoInt[6]);
      GeneralAmplifierData.leftRight = this.getSurroundVolFRprogress(this.mCanBusInfoInt[5]);
      GeneralAmplifierData.bandBass = this.getSurroundVolProgress(this.mCanBusInfoInt[3]);
      GeneralAmplifierData.bandTreble = this.getSurroundVolProgress(this.mCanBusInfoInt[4]);
      GeneralAmplifierData.customBass = this.getSurroundVolProgress(this.mCanBusInfoInt[7]);
      GeneralAmplifierData.custom2Bass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 3);
      GeneralAmplifierData.bose_center_b = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      this.updateAmplifierActivity((Bundle)null);
      ArrayList var1 = new ArrayList();
      if (GeneralAmplifierData.custom2Bass >= 0 && GeneralAmplifierData.custom2Bass <= 5) {
         var1.add((new SettingUpdateEntity(1, 0, GeneralAmplifierData.custom2Bass + "")).setProgress(GeneralAmplifierData.custom2Bass));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void carInfo0x04() {
      byte var1 = this.carInfoData0;
      byte[] var2 = this.mCanBusInfoByte;
      if (var1 != var2[2] || this.carInfoData1 != var2[3]) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isShowMasterDriverSeatBelt = true;
         GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ^ true;
         if (this.carInfoFirst) {
            this.updateDoorView(this.mContext);
         } else {
            this.carInfoFirst = true;
         }

         var2 = this.mCanBusInfoByte;
         this.carInfoData0 = var2[2];
         this.carInfoData1 = var2[3];
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, this.getSwitchStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]))));
      var3.add(new DriverUpdateEntity(0, 1, this.getSwitchStatus(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]))));
      var3.add(new DriverUpdateEntity(0, 2, this.getSwitchStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]))));
      var3.add(new DriverUpdateEntity(0, 3, this.getSwitchStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]))));
      var3.add(new DriverUpdateEntity(0, 4, this.getLightStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void carMessageInfo0x0B() {
      ArrayList var3 = new ArrayList();
      StringBuilder var1 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 5, var1.append(var2[2] * 256 + var2[3]).append("Km/h").toString()));
      var3.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[4] * 100 + " r/min"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(var4[2] * 256 + var4[3]);
   }

   private void carMessageInfo0x33() {
      ArrayList var2 = new ArrayList();
      StringBuilder var1 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(0, 7, var1.append(var3[2] * 256 + var3[3]).append("KM").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void carSetting0x30() {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
      if (var1 >= 0 && var1 <= 3) {
         var2.add((new SettingUpdateEntity(0, 1, var1)).setProgress(var1));
      }

      var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
      var2.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var2.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void dvdData0x12() {
      int var1;
      Context var4;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var4 = this.mContext;
         var1 = 2131762150;
      } else {
         var4 = this.mContext;
         var1 = 2131762151;
      }

      GeneralOriginalCarDeviceData.cdStatus = var4.getString(var1);
      GeneralOriginalCarDeviceData.runningState = this.getDVDStatus();
      int[] var8 = this.mCanBusInfoInt;
      int var2 = var8[4];
      int var3 = var8[5];
      var1 = var8[6];
      String var7 = "";
      String var9;
      if (var2 != 0 && var2 != 255) {
         var9 = this.mCanBusInfoInt[4] + "";
      } else {
         var9 = "";
      }

      String var5;
      if (var3 >= 0 && var3 <= 59) {
         var5 = var3 + "";
      } else {
         var5 = "0";
      }

      String var6 = var7;
      if (var1 >= 0) {
         var6 = var7;
         if (var1 <= 59) {
            var6 = var1 + "";
         }
      }

      ArrayList var10 = new ArrayList();
      var10.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131762136) + var9));
      var10.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(2131762138) + var5 + ":" + var6));
      GeneralOriginalCarDeviceData.mList = var10;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private String getDVDStatus() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : this.mContext.getString(2131762153);
         } else {
            return this.mContext.getString(2131762154);
         }
      } else {
         return this.mContext.getString(2131762152);
      }
   }

   private String getLightStatus(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? this.mContext.getString(2131762146) : this.mContext.getString(2131762158);
         } else {
            return this.mContext.getString(2131762157);
         }
      } else {
         return this.mContext.getString(2131762156);
      }
   }

   private int getSurroundVolFRprogress(int var1) {
      int var2 = var1;
      if (var1 != 0) {
         var2 = var1;
         if (var1 != 1) {
            var2 = var1;
            if (var1 != 2) {
               var2 = var1;
               if (var1 != 3) {
                  var2 = var1;
                  if (var1 != 4) {
                     var2 = var1;
                     if (var1 != 5) {
                        switch (var1) {
                           case 251:
                              var2 = -5;
                              break;
                           case 252:
                              return -4;
                           case 253:
                              return -3;
                           case 254:
                              return -2;
                           case 255:
                              return -1;
                           default:
                              return 0;
                        }
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private int getSurroundVolProgress(int var1) {
      if (var1 != 0 && var1 != 1 && var1 != 2 && var1 != 3 && var1 != 4 && var1 != 5) {
         switch (var1) {
            case 252:
               return 1;
            case 253:
               return 2;
            case 254:
               return 3;
            case 255:
               return 4;
            default:
               return 0;
         }
      } else {
         return var1 + 5;
      }
   }

   private String getSurroundVolStr(int var1) {
      if (var1 != 0 && var1 != 1 && var1 != 2 && var1 != 3 && var1 != 4 && var1 != 5) {
         switch (var1) {
            case 251:
               return "-5";
            case 252:
               return "-4";
            case 253:
               return "-3";
            case 254:
               return "-2";
            case 255:
               return "-1";
            default:
               return "0";
         }
      } else {
         return var1 + "";
      }
   }

   private String getSwitchStatus(boolean var1) {
      return var1 ? this.mContext.getString(2131768216) : this.mContext.getString(2131768215);
   }

   private void keyEvent0x01() {
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
            this.realKeyClick(46);
            break;
         case 4:
            this.realKeyClick(45);
            break;
         case 5:
            this.realKeyClick(2);
            break;
         case 6:
            this.realKeyClick(14);
            break;
         case 7:
            this.realKeyClick(15);
      }

   }

   private void panelEvent0x02() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      switch (var1) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(33);
            break;
         case 2:
            this.realKeyClick(34);
            break;
         case 3:
            this.realKeyClick(35);
            break;
         case 4:
            this.realKeyClick(36);
            break;
         case 5:
            this.realKeyClick(37);
            break;
         case 6:
            this.realKeyClick(38);
            break;
         case 7:
            if (this.isFM) {
               this.realKeyClick(77);
            } else {
               this.realKeyClick(76);
            }
            break;
         case 8:
            this.realKeyClick(130);
            break;
         case 9:
            this.realKeyClick(141);
            break;
         case 10:
            this.realKeyClick(30);
            break;
         case 11:
            this.realKeyClick(90);
            break;
         case 12:
            this.realKeyClick(91);
            break;
         case 13:
            UiMgr.sendAirCmd(4);
            break;
         case 14:
            this.realKeyClick(134);
            break;
         case 15:
            this.realKeyClick(53);
            break;
         case 16:
         case 17:
            this.realKeyClick(31);
            break;
         case 18:
            this.realKeyClick(52);
            break;
         default:
            switch (var1) {
               case 32:
                  this.realKeyClick(49);
                  break;
               case 33:
                  this.realKeyClick(45);
                  break;
               case 34:
                  this.realKeyClick(46);
                  break;
               case 35:
                  this.realKeyClick(47);
                  break;
               case 36:
                  this.realKeyClick(48);
                  break;
               case 37:
               case 38:
                  this.startMainActivity(this.mContext);
                  break;
               case 39:
                  this.realKeyClick(58);
                  break;
               case 40:
                  if (this.isNight) {
                     this.setBacklightLevel(5);
                  } else {
                     this.setBacklightLevel(1);
                  }

                  this.isNight ^= true;
                  break;
               case 41:
                  if (MediaShareData.Screen.INSTANCE.getScreenBacklight() < 5) {
                     this.setBacklightLevel(MediaShareData.Screen.INSTANCE.getScreenBacklight() + 1);
                  }
                  break;
               case 42:
                  if (MediaShareData.Screen.INSTANCE.getScreenBacklight() > 1) {
                     this.setBacklightLevel(MediaShareData.Screen.INSTANCE.getScreenBacklight() - 1);
                  }
                  break;
               case 43:
               case 44:
                  this.realKeyClick(this.mContext, 50);
                  break;
               case 45:
                  this.realKeyClick3_2(this.mContext, 48, var1, var2[3]);
                  break;
               case 46:
                  this.realKeyClick3_2(this.mContext, 47, var1, var2[3]);
                  break;
               default:
                  switch (var1) {
                     case 48:
                        this.realKeyClick3_1(this.mContext, 7, var1, var2[3]);
                        break;
                     case 49:
                        this.realKeyClick3_1(this.mContext, 8, var1, var2[3]);
                        break;
                     case 50:
                        this.realKeyClick3_2(this.mContext, 48, var1, var2[3]);
                        break;
                     case 51:
                        this.realKeyClick3_2(this.mContext, 47, var1, var2[3]);
                  }
            }
      }

   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 >= 1 && var1 <= 29) {
         var2 = (float)(var1 - 1) * 0.5F + 18.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[6];
      String var2;
      if (var1 >= 0 && var1 <= 127) {
         var2 = var1 + this.getTempUnitC(this.mContext);
      } else if (var1 >= 129 && var1 <= 255) {
         var2 = -(256 - var1) + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void startActivityNow(ComponentName var1) {
      Intent var2 = new Intent();
      var2.setComponent(var1);
      var2.setFlags(268435456);
      this.mContext.startActivity(var2);
   }

   private void trackInfo0x09() {
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[2], (byte)0, 127, 255, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 4});
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 5});
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 19});
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 51});
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 113});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      CommUtil.printHexString("scyscyscy308：", var2);
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 4) {
               if (var3 != 5) {
                  if (var3 != 9) {
                     if (var3 != 11) {
                        if (var3 != 48) {
                           if (var3 != 51) {
                              if (var3 != 113) {
                                 if (var3 != 18) {
                                    if (var3 == 19) {
                                       this.amplifierInfo0x13();
                                    }
                                 } else {
                                    this.dvdData0x12();
                                 }
                              } else {
                                 this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
                              }
                           } else {
                              this.carMessageInfo0x33();
                           }
                        } else {
                           this.carSetting0x30();
                        }
                     } else {
                        this.carMessageInfo0x0B();
                     }
                  } else {
                     this.trackInfo0x09();
                  }
               } else {
                  if (this.isAirMsgRepeat(var2)) {
                     return;
                  }

                  this.airInfo0x05();
               }
            } else {
               this.carInfo0x04();
            }
         } else {
            this.panelEvent0x02();
         }
      } else {
         this.keyEvent0x01();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 1, (byte)var1, 0});
   }

   public void destroyCommand() {
      super.destroyCommand();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (var2.contains("FM")) {
         this.isFM = true;
      } else {
         this.isFM = false;
      }

   }
}
