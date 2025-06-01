package com.hzbhd.canbus.car._309;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.PanelKeyActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private byte carInfoData0;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private void airInfo0x2A() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void amplifierInfo0x31() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      GeneralAmplifierData.frontRear = -(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 7);
      GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 7;
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
      this.updateAmplifierActivity((Bundle)null);
      ArrayList var3 = new ArrayList();
      int var1;
      Context var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[7])) {
         var2 = this.mContext;
         var1 = 2131762199;
      } else {
         var2 = this.mContext;
         var1 = 2131762198;
      }

      var3.add(new DriverUpdateEntity(0, 3, var2.getString(var1)));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void carInfo0x24() {
      if (this.carInfoData0 != this.mCanBusInfoByte[2]) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
         this.carInfoData0 = this.mCanBusInfoByte[2];
      }

      ArrayList var3 = new ArrayList();
      int var1;
      Context var2;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoByte[3])) {
         var2 = this.mContext;
         var1 = 2131769841;
      } else {
         var2 = this.mContext;
         var1 = 2131769410;
      }

      var3.add(new DriverUpdateEntity(0, 0, var2.getString(var1)));
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoByte[3])) {
         var2 = this.mContext;
         var1 = 2131762196;
      } else {
         var2 = this.mContext;
         var1 = 2131762197;
      }

      var3.add(new DriverUpdateEntity(0, 1, var2.getString(var1)));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoByte[3])) {
         var2 = this.mContext;
         var1 = 2131768216;
      } else {
         var2 = this.mContext;
         var1 = 2131768215;
      }

      var3.add(new DriverUpdateEntity(0, 2, var2.getString(var1)));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private String getCDStatus() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 1) {
         return "TUNER";
      } else {
         return var1 == 2 ? "DISC(CD DVD)" : "OFF";
      }
   }

   private void keyEvent0x11() {
      byte[] var3;
      if (SystemUtil.isForeground(this.mContext, PanelKeyActivity.class.getName())) {
         var3 = this.mCanBusInfoByte;
         CanbusMsgSender.sendMsg(new byte[]{22, 116, var3[2], var3[3]});
      } else {
         int[] var2 = this.mCanBusInfoInt;
         int var1 = var2[2];
         switch (var1) {
            case 0:
               this.realKeyClick(0);
               break;
            case 1:
               this.realKeyClick(134);
               break;
            case 2:
               this.realKeyClick(7);
               break;
            case 3:
               this.realKeyClick(8);
               break;
            case 4:
               this.realKeyClick(77);
               break;
            case 5:
               this.realKeyClick(76);
               break;
            case 6:
               this.realKeyClick(2);
               break;
            case 7:
               this.realKeyClick(130);
               break;
            case 8:
               this.realKeyClick(46);
               break;
            case 9:
               this.realKeyClick(45);
               break;
            default:
               switch (var1) {
                  case 16:
                     this.realKeyClick(3);
                     break;
                  case 17:
                     this.realKeyClick3_2(this.mContext, 48, var1, var2[3]);
                     break;
                  case 18:
                     this.realKeyClick3_2(this.mContext, 47, var1, var2[3]);
                     break;
                  case 19:
                     if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                        this.realKeyClick(33);
                     } else {
                        this.realKeyClick(45);
                     }
                     break;
                  case 20:
                     if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                        this.realKeyClick(34);
                     } else {
                        this.realKeyClick(91);
                     }
                     break;
                  case 21:
                     if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                        this.realKeyClick(35);
                     } else {
                        this.realKeyClick(29);
                     }
                     break;
                  case 22:
                     if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                        this.realKeyClick(36);
                     } else {
                        this.realKeyClick(46);
                     }
                     break;
                  case 23:
                     if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                        this.realKeyClick(37);
                     } else {
                        this.realKeyClick(90);
                     }
                     break;
                  case 24:
                     if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                        this.realKeyClick(38);
                     } else {
                        this.realKeyClick(27);
                     }
                     break;
                  default:
                     switch (var1) {
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                           break;
                        default:
                           return;
                     }
                  case 25:
                     var3 = this.mCanBusInfoByte;
                     CanbusMsgSender.sendMsg(new byte[]{22, 116, var3[2], var3[3]});
               }
         }

      }
   }

   private void keyEvent0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        if (var1 != 135) {
                           if (var1 != 9) {
                              if (var1 != 10) {
                                 if (var1 != 21) {
                                    if (var1 == 22) {
                                       this.realKeyClick(49);
                                    }
                                 } else {
                                    this.realKeyClick(50);
                                 }
                              } else {
                                 this.realKeyClick(15);
                              }
                           } else {
                              this.realKeyClick(14);
                           }
                        } else {
                           this.realKeyClick(134);
                        }
                     } else {
                        this.realKeyClick(2);
                     }
                  } else {
                     this.realKeyClick(46);
                  }
               } else {
                  this.realKeyClick(45);
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

   private void mediaInfo0x1c() {
      ArrayList var6 = new ArrayList();
      GeneralOriginalCarDeviceData.cdStatus = this.getCDStatus();
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[2];
      String var2 = "";
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               if (var3[4] > 0) {
                  var2 = this.mCanBusInfoInt[4] + "";
               }

               GeneralOriginalCarDeviceData.runningState = "DVD " + var2;
               var6.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131762193) + this.mCanBusInfoInt[5]));
               StringBuilder var7 = (new StringBuilder()).append(this.mContext.getString(2131762190));
               if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7])) {
                  var2 = this.mContext.getString(2131768216);
               } else {
                  var2 = this.mContext.getString(2131768215);
               }

               var6.add(new OriginalCarDeviceUpdateEntity(1, var7.append(var2).toString()));
               var7 = (new StringBuilder()).append(this.mContext.getString(2131762191));
               if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7])) {
                  var2 = this.mContext.getString(2131768216);
               } else {
                  var2 = this.mContext.getString(2131768215);
               }

               var6.add(new OriginalCarDeviceUpdateEntity(2, var7.append(var2).toString()));
               var6.add(new OriginalCarDeviceUpdateEntity(3, this.mContext.getString(2131762192) + this.mCanBusInfoInt[8] + ":" + this.mCanBusInfoInt[9]));
            }
         } else {
            var1 = var3[4];
            String var8 = "MHz";
            if (var1 == 17) {
               var8 = "kHz";
               var2 = "AM";
            } else if (var1 == 2) {
               var2 = "FM2";
            } else {
               var2 = "FM";
            }

            GeneralOriginalCarDeviceData.runningState = var2;
            int[] var4 = this.mCanBusInfoInt;
            var1 = var4[7];
            String var5 = String.valueOf((float)(var4[5] * 256 + var4[6]) / 10.0F);
            String var9 = var5;
            if (var2.contains("AM")) {
               var9 = var5.split("\\.")[0];
            }

            var6.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131762188) + var9 + var8));
            if (var1 >= 1 && var1 <= 6) {
               var6.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(2131762189) + var1));
            } else {
               var6.add(new OriginalCarDeviceUpdateEntity(1, ""));
            }

            var6.add(new OriginalCarDeviceUpdateEntity(2, ""));
            var6.add(new OriginalCarDeviceUpdateEntity(3, ""));
         }
      } else {
         GeneralOriginalCarDeviceData.runningState = "";
         var6.add(new OriginalCarDeviceUpdateEntity(0, ""));
         var6.add(new OriginalCarDeviceUpdateEntity(1, ""));
         var6.add(new OriginalCarDeviceUpdateEntity(2, ""));
         var6.add(new OriginalCarDeviceUpdateEntity(3, ""));
      }

      GeneralOriginalCarDeviceData.mList = var6;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void realKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick1(var3, var1, var2[2], var2[3]);
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

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 49});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 42});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      CommUtil.printHexString("scyscyscy309：", var2);
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 28) {
            if (var3 != 32) {
               if (var3 != 36) {
                  if (var3 != 42) {
                     if (var3 != 48) {
                        if (var3 == 49) {
                           this.amplifierInfo0x31();
                        }
                     } else {
                        this.updateVersionInfo(var1, this.getVersionStr(var2));
                     }
                  } else {
                     if (this.isAirMsgRepeat(var2)) {
                        return;
                     }

                     this.airInfo0x2A();
                  }
               } else {
                  this.carInfo0x24();
               }
            } else {
               this.keyEvent0x20();
            }
         } else {
            this.mediaInfo0x1c();
         }
      } else {
         this.keyEvent0x11();
      }

   }

   public void destroyCommand() {
      super.destroyCommand();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
