package com.hzbhd.canbus.car._223;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private final int INVAILE_VALUE = -1;
   private final String TAG = "_223_MsgMgr";
   private String mAirUnit;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
   private int mDiscExsit;
   private boolean mFrontStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;

   private int getData(int... var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var1.length; ++var2) {
         var3 += var1[var2] << var2 * 8;
      }

      return (double)var3 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var3;
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void realKeyClick2(Context var1, int var2) {
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var1, var2, var3[2], var3[3]);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LOW";
      } else if (var1 == 127) {
         return "HIGH";
      } else if (var1 >= 31 && var1 <= 59) {
         float var2 = (float)var1 / 2.0F;
         return GeneralAirData.fahrenheit_celsius ? this.mDecimalFormat0p0.format((double)(var2 * 9.0F / 5.0F + 32.0F)) + this.mAirUnit : var2 + this.mAirUnit;
      } else {
         return "";
      }
   }

   private String resolveOutdoorTemperature(int var1) {
      return GeneralAirData.fahrenheit_celsius ? this.mDecimalFormat0p0.format((double)((float)(var1 * 9) / 5.0F + 32.0F)) + this.mAirUnit : var1 + this.mAirUnit;
   }

   private void set0x20WheelKeyData(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 != 7) {
                        if (var2 != 63) {
                           if (var2 != 86) {
                              if (var2 != 87) {
                                 if (var2 != 240) {
                                    if (var2 != 241) {
                                       switch (var2) {
                                          case 32:
                                             this.realKeyLongClick1(var1, 32);
                                             break;
                                          case 33:
                                             this.realKeyLongClick1(var1, 33);
                                             break;
                                          case 34:
                                             this.realKeyLongClick1(var1, 34);
                                             break;
                                          case 35:
                                             this.realKeyLongClick1(var1, 35);
                                             break;
                                          case 36:
                                             this.realKeyLongClick1(var1, 36);
                                             break;
                                          case 37:
                                             this.realKeyLongClick1(var1, 37);
                                             break;
                                          case 38:
                                             this.realKeyLongClick1(var1, 38);
                                             break;
                                          case 39:
                                             this.realKeyLongClick1(var1, 39);
                                             break;
                                          case 40:
                                             this.realKeyLongClick1(var1, 40);
                                             break;
                                          case 41:
                                             this.realKeyLongClick1(var1, 41);
                                             break;
                                          default:
                                             switch (var2) {
                                                case 52:
                                                   this.realKeyLongClick1(var1, 129);
                                                   break;
                                                case 53:
                                                   this.realKeyLongClick1(var1, 130);
                                                   break;
                                                case 54:
                                                   this.realKeyLongClick1(var1, 141);
                                                   break;
                                                case 55:
                                                   this.realKeyLongClick1(var1, 151);
                                                   break;
                                                case 56:
                                                   this.realKeyLongClick1(var1, 4);
                                                   break;
                                                case 57:
                                                   this.realKeyLongClick1(var1, 14);
                                                   break;
                                                default:
                                                   switch (var2) {
                                                      case 72:
                                                         this.realKeyLongClick1(var1, 49);
                                                         break;
                                                      case 73:
                                                         this.realKeyLongClick1(var1, 47);
                                                         break;
                                                      case 74:
                                                         this.realKeyLongClick1(var1, 48);
                                                         break;
                                                      case 75:
                                                         this.realKeyLongClick1(var1, 45);
                                                         break;
                                                      case 76:
                                                         this.realKeyLongClick1(var1, 46);
                                                         break;
                                                      default:
                                                         switch (var2) {
                                                            case 82:
                                                               this.realKeyLongClick1(var1, 465);
                                                               break;
                                                            case 83:
                                                               this.realKeyLongClick1(var1, 466);
                                                               break;
                                                            case 84:
                                                               this.realKeyLongClick1(var1, 31);
                                                         }
                                                   }
                                             }
                                       }
                                    } else {
                                       this.realKeyClick2(var1, 8);
                                    }
                                 } else {
                                    this.realKeyClick2(var1, 7);
                                 }
                              } else if (var3[3] == 1) {
                                 this.startMainActivity(var1);
                              }
                           } else {
                              this.realKeyLongClick1(var1, 94);
                           }
                        } else {
                           this.realKeyLongClick1(var1, 134);
                        }
                     } else {
                        this.realKeyLongClick1(var1, 2);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 45);
                  }
               } else {
                  this.realKeyLongClick1(var1, 46);
               }
            } else {
               this.realKeyLongClick1(var1, 8);
            }
         } else {
            this.realKeyLongClick1(var1, 7);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private void set0x21AirData(Context var1) {
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      if (GeneralAirData.fahrenheit_celsius) {
         this.mAirUnit = this.getTempUnitF(var1);
      } else {
         this.mAirUnit = this.getTempUnitC(var1);
      }

      this.updateOutDoorTemp(var1, this.resolveOutdoorTemperature(this.mCanBusInfoByte[7]));
      byte[] var2 = this.mCanBusInfoByte;
      var2[3] = (byte)(var2[3] & 239);
      var2[7] = 0;
      if (!this.isAirMsgRepeat(var2)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         this.updateAirActivity(var1, 1001);
      }
   }

   private void set0x22RearRadar(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(31, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24BaseData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x29VehicleData() {
      if (this.mCanBusInfoInt[2] == 2) {
         ArrayList var3 = new ArrayList();
         StringBuilder var1 = new StringBuilder();
         int[] var2 = this.mCanBusInfoInt;
         var3.add(new DriverUpdateEntity(0, 0, var1.append(this.getData(var2[6], var2[7])).append(" rpm").toString()));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0x30VersionData() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 36) {
         if (var3 != 41) {
            if (var3 != 48) {
               switch (var3) {
                  case 32:
                     this.set0x20WheelKeyData(var1);
                     break;
                  case 33:
                     this.set0x21AirData(var1);
                     break;
                  case 34:
                     this.set0x22RearRadar(var1);
               }
            } else {
               this.set0x30VersionData();
            }
         } else {
            this.set0x29VehicleData();
         }
      } else {
         this.set0x24BaseData(var1);
      }

   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      if (this.mDiscExsit != var4) {
         this.mDiscExsit = var4;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)(1 - var4 + 6)});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 36, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 6});
   }
}
