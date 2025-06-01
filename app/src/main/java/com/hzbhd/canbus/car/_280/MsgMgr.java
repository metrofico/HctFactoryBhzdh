package com.hzbhd.canbus.car._280;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private final String _280_ISBACK_OPEN = "_280_isback_open";
   private final String _280_ISLEFT_FRONT_DOOR_OPEN = "_280_isleft_front_door_open";
   private final String _280_ISLEFT_REAR_DOOR_OPEN = "_280_isleft_rear_door_open";
   private final String _280_ISRIGHT_FRONT_DOOR_OPEN = "_280_isright_front_door_open";
   private final String _280_ISRIGHT_REAR_DOOR_OPEN = "_280_isright_rear_door_open";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "_280_isleft_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_280_isright_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_280_isright_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_280_isleft_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "_280_isback_open", false) != GeneralDoorData.isBackOpen;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyClick2(this.mContext, var1, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
   }

   private void realKeyClick2(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (30 == var1) {
         var2 = "HI";
      } else if (1 <= var1 && var1 <= 29) {
         var2 = (float)var1 * 0.5F + 17.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      byte var1 = this.mCanBusInfoByte[2];
      String var2;
      if (var1 >= 0 && var1 <= 87) {
         var2 = this.mCanBusInfoByte[2] + this.getTempUnitC(this.mContext);
      } else if (var1 < 0) {
         var2 = this.mCanBusInfoByte[2] + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarSpeed() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 2, var3.append(var2[2] + var2[3] * 256).append("Km/h").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[3], var4[2]));
   }

   private void setDoorData0x24() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      if (this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "_280_isleft_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_280_isright_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_280_isright_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_280_isleft_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_280_isback_open", GeneralDoorData.isBackOpen);
         this.updateDoorView(this.mContext);
      }

      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1 = this.mContext.getResources().getString(2131769504);
      } else {
         var1 = this.mContext.getResources().getString(2131768042);
      }

      var2.add(new DriverUpdateEntity(0, 0, var1));
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1 = "Not P";
      } else {
         var1 = "P";
      }

      var2.add(new DriverUpdateEntity(0, 1, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setEngine() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 3, var2.append(var3[2] + var3[3] * 256).append("rpm").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSwc() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 18) {
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
               this.realKeyClick(45);
               break;
            case 4:
               this.realKeyClick(46);
               break;
            case 5:
               this.realKeyClick(14);
               break;
            case 6:
            case 8:
               this.realKeyClick(3);
               break;
            case 7:
               this.realKeyClick(2);
               break;
            case 9:
               this.realKeyClick(152);
               break;
            case 10:
            case 11:
               this.realKeyClick(183);
               break;
            case 12:
               this.realKeyClick(50);
               break;
            case 13:
               this.realKeyClick(20);
               break;
            case 14:
               this.realKeyClick(21);
               break;
            case 15:
               this.realKeyClick(1);
               break;
            case 16:
               this.realKeyClick(15);
               break;
            default:
               label87:
               switch (var1) {
                  case 48:
                     this.realKeyClick(133);
                     return;
                  case 49:
                  case 50:
                  case 51:
                     this.realKeyClick(128);
                     return;
                  case 52:
                     this.realKeyClick(58);
                     return;
                  case 53:
                     this.realKeyClick(49);
                     return;
                  case 54:
                     this.realKeyClick(62);
                     return;
                  case 55:
                     this.realKeyClick(59);
                     return;
                  case 56:
                     this.realKeyClick(14);
                     return;
                  case 57:
                     this.realKeyClick(21);
                     return;
                  case 58:
                     this.realKeyClick(20);
                     return;
                  case 59:
                     this.realKeyClick(134);
                     return;
                  case 61:
                     break;
                  case 62:
                     this.setWheel(var2[3], 46);
                     return;
                  case 63:
                     this.setWheel(var2[3], 45);
                     return;
                  default:
                     switch (var1) {
                        case 129:
                           break;
                        case 130:
                           break label87;
                        case 131:
                           this.realKeyClick(46);
                           return;
                        case 132:
                           this.realKeyClick(45);
                           return;
                        case 133:
                           this.realKeyClick(20);
                           return;
                        case 134:
                           this.realKeyClick(21);
                           return;
                        case 135:
                           this.realKeyClick(134);
                           return;
                        case 136:
                           this.realKeyClick(2);
                           return;
                        case 137:
                           this.realKeyClick(3);
                           return;
                        case 138:
                           this.setWheel(var2[3], 7);
                           return;
                        case 139:
                           this.setWheel(var2[3], 8);
                           return;
                        default:
                           return;
                     }
                  case 60:
                     this.setWheel(var2[3], 7);
                     return;
               }

               this.setWheel(var2[3], 8);
         }
      } else {
         this.realKeyClick(15);
      }

   }

   private void setTrack() {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[2], (byte)0, 0, 100, 8);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheel(int var1, int var2) {
      for(int var3 = 0; var3 < var1; ++var3) {
         this.realKeyClick2(var2);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 36) {
         if (var3 != 48) {
            if (var3 != 104) {
               if (var3 != 106) {
                  if (var3 != 38) {
                     if (var3 != 39) {
                        switch (var3) {
                           case 32:
                              this.setSwc();
                              break;
                           case 33:
                              if (this.isAirMsgRepeat(var2)) {
                                 return;
                              }

                              this.setAirData0x21();
                              break;
                           case 34:
                              this.setRearRadar();
                        }
                     } else {
                        this.setAirData0x27();
                     }
                  } else {
                     this.setTrack();
                  }
               } else {
                  this.setCarSpeed();
               }
            } else {
               this.setEngine();
            }
         } else {
            this.setVersionInfo();
         }
      } else {
         if (this.isDoorMsgRepeat(var2)) {
            return;
         }

         this.setDoorData0x24();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   void initAmplifierData(Context var1) {
      GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "_280_amplifier_fade", 0) - 10;
      GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "_280_amplifier_balance", 0) - 10;
      GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "_280_amplifier_bass", 0) - 10;
      GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "_280_amplifier_middle", 0) - 10;
      GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "_280_amplifier_treble", 0) - 10;
      this.updateAmplifierActivity((Bundle)null);
      SharePreUtil.setIntValue(var1, "frontRear", SharePreUtil.getIntValue(var1, "_280_amplifier_fade", 0));
      SharePreUtil.setIntValue(var1, "leftRight", SharePreUtil.getIntValue(var1, "_280_amplifier_balance", 0));
      SharePreUtil.setIntValue(var1, "bandBass", SharePreUtil.getIntValue(var1, "_280_amplifier_bass", 0));
      SharePreUtil.setIntValue(var1, "bandMiddle", SharePreUtil.getIntValue(var1, "_280_amplifier_middle", 0));
      SharePreUtil.setIntValue(var1, "bandTreble", SharePreUtil.getIntValue(var1, "_280_amplifier_treble", 0));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)SharePreUtil.getIntValue(var1, "frontRear", 0), (byte)SharePreUtil.getIntValue(var1, "leftRight", 0), (byte)SharePreUtil.getIntValue(var1, "bandBass", 0), (byte)SharePreUtil.getIntValue(var1, "bandMiddle", 0), (byte)SharePreUtil.getIntValue(var1, "bandTreble", 0)});
   }

   void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.climate = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }
}
