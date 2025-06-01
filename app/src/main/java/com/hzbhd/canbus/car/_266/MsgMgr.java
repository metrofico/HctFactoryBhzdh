package com.hzbhd.canbus.car._266;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private static int swc_data3;
   private static int up_dn_btn_data;
   private static int voice_btn_data;
   private final String SWC_DATA_3 = "swc_data_3";
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusBtnPanelInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusSwcInfoCopy;
   private Context mContext;

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIntByBoolean(boolean var1) {
      return var1 ? 1 : 0;
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusAirInfoCopy)) {
         return true;
      } else {
         this.mCanBusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isBtnPanelMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusBtnPanelInfoCopy)) {
         return true;
      } else {
         this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isData3Change() {
      Context var2 = this.mContext;
      boolean var1 = false;
      if (SharePreUtil.getIntValue(var2, "swc_data_3", 0) != swc_data3) {
         var1 = true;
      }

      return var1;
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanBusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isSwcMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void keyControl0x11() {
      swc_data3 = this.mCanBusInfoInt[5];
      if (this.isData3Change()) {
         label35: {
            SharePreUtil.setIntValue(this.mContext, "swc_data_3", swc_data3);
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
                                       this.realKeyClick1(2);
                                    }
                                 } else {
                                    this.realKeyClick1(46);
                                 }
                              } else {
                                 this.realKeyClick1(45);
                              }
                           } else {
                              this.realKeyClick1(15);
                           }
                        } else {
                           this.realKeyClick1(14);
                        }
                     } else {
                        this.realKeyClick1(3);
                     }
                  } else {
                     this.realKeyClick1(8);
                  }
                  break label35;
               }
            } else {
               this.realKeyClick1(0);
            }

            this.realKeyClick1(7);
         }
      }

      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[9], var2[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[4], var3[5]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private String resolveLeftAndRightTemp(int var1) {
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

   private int return10(int var1) {
      return var1 == 255 ? 255 : (new int[]{4, 3, 2, 1})[var1 - 1];
   }

   private void sendMsgWhenInit() {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 120});
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 18});
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentEachCanId(), 33});
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -102});
   }

   private void setAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      int var1 = this.mCanBusInfoInt[6];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_auto_wind_model = false;
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 6) {
               if (var1 != 11) {
                  if (var1 == 12) {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
      this.updateOutDoorTemp(this.mContext, (double)this.mCanBusInfoInt[13] * 0.5 - 40.0 + this.getTempUnitC(this.mContext));
      if (this.getCurrentCanDifferentId() != 2) {
         this.updateAirActivity(this.mContext, 1004);
      }

   }

   private void setCarSetData() {
      ArrayList var3 = new ArrayList();

      int var2;
      for(int var1 = 0; var1 < 7; var1 = var2) {
         var2 = var1 + 1;
         var3.add((new SettingUpdateEntity(0, var1, this.getIntByBoolean(DataHandleUtils.getBoolBit(var2, this.mCanBusInfoInt[9])))).setEnable(DataHandleUtils.getBoolBit(var2, this.mCanBusInfoInt[4])));
      }

      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarType() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (this.mCanBusInfoInt[3] == 2) {
         var1 = "_266_car_type";
      } else {
         var1 = "invalid";
      }

      var2.add(new SettingUpdateEntity(0, 8, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setOriginalPanel() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 1) {
         if (var1[3] > voice_btn_data) {
            this.realKeyClick4(this.mContext, 7);
            voice_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < voice_btn_data) {
            this.realKeyClick4(this.mContext, 8);
            voice_btn_data = this.mCanBusInfoInt[3];
         }
      } else {
         if (var1[3] > up_dn_btn_data) {
            this.realKeyClick4(this.mContext, 46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(this.mContext, 45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      }

   }

   private void setPanelButton() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 36) {
               if (var1 != 43) {
                  if (var1 != 51) {
                     if (var1 == 76) {
                        this.realKeyLongClick1(this.mContext, 14, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 129, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 52, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 59, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 134, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void setRadarData0x41() {
      RadarInfoUtil.setRearRadarLocationData(4, this.return10(this.mCanBusInfoInt[2]), this.return10(this.mCanBusInfoInt[3]), this.return10(this.mCanBusInfoInt[4]), this.return10(this.mCanBusInfoInt[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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
               if (var3 != 34) {
                  if (var3 != 38) {
                     if (var3 != 49) {
                        if (var3 != 65) {
                           if (var3 != 120) {
                              if (var3 == 240) {
                                 this.setVersionInfo();
                              }
                           } else {
                              this.setCarSetData();
                           }
                        } else {
                           this.setRadarData0x41();
                        }
                     } else {
                        if (this.isAirMsgReturn(var2)) {
                           return;
                        }

                        this.setAirData();
                     }
                  } else {
                     this.setCarType();
                  }
               } else {
                  if (this.isBtnPanelMsgReturn(var2)) {
                     return;
                  }

                  this.setOriginalPanel();
               }
            } else {
               this.setPanelButton();
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setDoorData();
         }
      } else {
         if (this.isSwcMsgReturn(var2)) {
            return;
         }

         this.keyControl0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, (byte)var10, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.sendMsgWhenInit();
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentEachCanId(), 33});
   }

   void languageSet(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 7, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }
}
