package com.hzbhd.canbus.car._236;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   int[] m0x22RearRadarData;
   int[] m0x23FrontRadarData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   int[] mTireInfo;
   private List tyreInfoList = new ArrayList();

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIndexBy3Bit(int var1) {
      byte var2 = 2;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private int getRadarData(int var1) {
      return var1 == 0 ? 0 : -var1 + 7;
   }

   private boolean is0x26DataChange() {
      if (Arrays.equals(this.m0x23FrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23FrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x27DataChange() {
      if (Arrays.equals(this.m0x22RearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22RearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (18 == var1) {
         var2 = "HI";
      } else if (255 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (3 <= var1 && 17 >= var1) {
         var2 = var1 + 15 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      int var1 = var2;
      if (var2 >= 127) {
         var1 = 127;
      }

      String var3;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var3 = "-" + var1;
      } else {
         var3 = var1 + "";
      }

      return var3 + this.getTempUnitC(this.mContext);
   }

   private void set0x25TireInfo() {
      if (!this.isTireInfoChange()) {
         if (this.mCanBusInfoInt[2] == 255) {
            this.arr0[0] = this.mContext.getString(2131759556);
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
         } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
            this.arr0[0] = (double)this.mCanBusInfoInt[2] * 2.75 + "kPa";
         }

         if (this.mCanBusInfoInt[3] == 255) {
            this.arr1[0] = this.mContext.getString(2131759556);
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
         } else {
            this.arr1[0] = (double)this.mCanBusInfoInt[3] * 2.75 + "kPa";
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
         }

         if (this.mCanBusInfoInt[5] == 255) {
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
            this.arr2[0] = this.mContext.getString(2131759556);
         } else {
            this.arr2[0] = (double)this.mCanBusInfoInt[5] * 2.75 + "kPa";
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
         }

         if (this.mCanBusInfoInt[4] == 255) {
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
            this.arr3[0] = this.mContext.getString(2131759556);
         } else {
            this.arr3[0] = (double)this.mCanBusInfoInt[4] * 2.75 + "kPa";
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
         }

         GeneralTireData.dataList = this.tyreInfoList;
         this.updateTirePressureActivity((Bundle)null);
      }
   }

   private void set0x26FrontRadarInfo() {
      if (this.is0x26DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(6, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x27RearRadarInfo() {
      if (this.is0x27DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(6, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
      if (var1 == 9) {
         GeneralAirData.front_wind_level = 0;
         GeneralAirData.front_auto_wind_speed = true;
      } else {
         GeneralAirData.front_wind_level = var1;
         GeneralAirData.front_auto_wind_speed = false;
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_auto_wind_model = false;
      switch (var1) {
         case 1:
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            break;
         case 2:
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            break;
         case 3:
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            break;
         case 4:
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            break;
         case 5:
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            break;
         case 6:
            GeneralAirData.front_auto_wind_model = true;
      }

      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x36() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setSettingData0x51() {
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      int var2 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2));
      int var1 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2));
      int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var11 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
      int[] var15 = this.mCanBusInfoInt;
      int var6 = var15[4];
      int var13 = DataHandleUtils.getIntFromByteWithBit(var15[5], 7, 1);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
      ArrayList var16 = new ArrayList();
      var16.add(new SettingUpdateEntity(0, 0, var9));
      var16.add(new SettingUpdateEntity(0, 1, var2));
      var16.add(new SettingUpdateEntity(0, 2, var1));
      var16.add(new SettingUpdateEntity(0, 3, var12));
      var16.add(new SettingUpdateEntity(0, 4, var10));
      var16.add(new SettingUpdateEntity(0, 5, var8));
      var16.add(new SettingUpdateEntity(0, 6, var7));
      var16.add(new SettingUpdateEntity(0, 7, var11));
      var16.add(new SettingUpdateEntity(0, 8, var3));
      var16.add(new SettingUpdateEntity(0, 9, var4));
      var16.add((new SettingUpdateEntity(0, 10, var6)).setProgress(var6 - 30));
      Boolean var17 = false;
      if (var13 == 1) {
         var17 = true;
      }

      var16.add(new SettingUpdateEntity(0, 12, var13));
      var16.add((new SettingUpdateEntity(0, 13, var14)).setEnable(var17));
      var16.add((new SettingUpdateEntity(0, 14, var5)).setEnable(var17));
      this.updateGeneralSettingData(var16);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKekInfo() {
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 16) {
                        if (var1 != 128) {
                           if (var1 != 18) {
                              if (var1 != 19) {
                                 switch (var1) {
                                    case 6:
                                       this.realKeyLongClick1(this.mContext, 3, var1);
                                       break;
                                    case 7:
                                       this.realKeyLongClick1(this.mContext, 2, var1);
                                       break;
                                    case 8:
                                       this.realKeyLongClick1(this.mContext, 14, var1);
                                       break;
                                    case 9:
                                       this.realKeyLongClick1(this.mContext, 14, var1);
                                       break;
                                    case 10:
                                       this.realKeyLongClick1(this.mContext, 15, var1);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 32:
                                             this.realKeyLongClick1(this.mContext, 52, var1);
                                             break;
                                          case 33:
                                             this.realKeyLongClick1(this.mContext, 198, var1);
                                             break;
                                          case 34:
                                             this.realKeyLongClick1(this.mContext, 59, var1);
                                             break;
                                          case 35:
                                             this.realKeyLongClick1(this.mContext, 2, var1);
                                             break;
                                          case 36:
                                             this.realKeyLongClick1(this.mContext, 139, var1);
                                             break;
                                          case 37:
                                             this.realKeyLongClick1(this.mContext, 141, var1);
                                             break;
                                          case 38:
                                             this.realKeyLongClick1(this.mContext, 62, var1);
                                             break;
                                          case 39:
                                             this.realKeyLongClick1(this.mContext, 50, var1);
                                             break;
                                          case 40:
                                             this.realKeyLongClick1(this.mContext, 151, var1);
                                             break;
                                          case 41:
                                             this.realKeyLongClick1(this.mContext, 58, var1);
                                             break;
                                          case 42:
                                             this.realKeyLongClick1(this.mContext, 49, var1);
                                             break;
                                          case 43:
                                             this.realKeyLongClick1(this.mContext, 47, var1);
                                             break;
                                          case 44:
                                             this.realKeyLongClick1(this.mContext, 48, var1);
                                             break;
                                          case 45:
                                             this.realKeyLongClick1(this.mContext, 53, var1);
                                             break;
                                          case 46:
                                             this.realKeyLongClick1(this.mContext, 45, var1);
                                             break;
                                          case 47:
                                             this.realKeyLongClick1(this.mContext, 46, var1);
                                       }
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 2, var1);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 184, var1);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 1, var1);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 187, var1);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 21, var1);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 20, var1);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var1);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var1);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var1);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 54) {
            if (var3 != 81) {
               if (var3 != 127) {
                  switch (var3) {
                     case 35:
                        var3 = this.mDifferent;
                        if (var3 == 1 || var3 == 2) {
                           return;
                        }

                        if (this.isAirMsgRepeat(var2)) {
                           return;
                        }

                        this.setAirData0x23();
                        break;
                     case 36:
                        if (this.isDoorMsgRepeat(var2)) {
                           return;
                        }

                        this.setDoorData0x24();
                        break;
                     case 37:
                        this.set0x25TireInfo();
                        break;
                     case 38:
                        this.set0x26FrontRadarInfo();
                        break;
                     case 39:
                        this.set0x27RearRadarInfo();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setSettingData0x51();
            }
         } else {
            var3 = this.mDifferent;
            if (var3 == 1 || var3 == 2) {
               return;
            }

            this.setAirData0x36();
         }
      } else {
         this.setWheelKekInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      byte var14 = (byte)(var5 & 127 | (var10 ^ 1) << 7 & 128);
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var2, (byte)var3, (byte)var4, var14, (byte)var6, (byte)var7});
   }

   public void initCommand(Context var1) {
      this.mDifferent = this.getCurrentCanDifferentId();
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 127});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
      int var2 = this.mDifferent;
      if (var2 != 0) {
         if (var2 != 3) {
            if (var2 != 4) {
               if (var2 != 5) {
                  if (var2 != 6) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, -128});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 4});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 2});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 0});
      }

      GeneralTireData.isHaveSpareTire = false;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
