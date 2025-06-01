package com.hzbhd.canbus.car._271;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
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
   private static int _271_up_dn_btn_data;
   private static int _271_voice_btn_data;
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private static int outDoorTemp;
   private final String _271_IS_OUT_DOOR_TEMP = "_271_is_out_door_temp";
   public boolean isShowRadar = true;
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferentId = this.getCurrentCanDifferentId();
   private int[] mRadarDataNow;

   private void carBodyInfo() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var3.append(var2[4] * 256 + var2[5]).append("rpm").toString()));
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var5.append(var6[6] * 256 + var6[7]).append("km/h").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[6], var4[7]));
   }

   private int getIntByBoolean(boolean var1) {
      return var1 ? 1 : 0;
   }

   private int getItemData(int var1, int var2, int var3) {
      return DataHandleUtils.getIntFromByteWithBit(var1, var2, var3);
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

   private boolean isOnlyOutDoorDataChange() {
      return SharePreUtil.getFloatValue(this.mContext, "_271_is_out_door_temp", 0.0F) != (float)outDoorTemp;
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void keyControl0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
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
                     this.realKeyClick1(201);
                  }
               } else {
                  this.realKeyClick1(184);
               }
            } else {
               this.realKeyClick1(8);
            }
         } else {
            this.realKeyClick1(7);
         }
      } else {
         this.realKeyClick1(0);
      }

      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[9], var2[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void radarInfo() {
      boolean var1;
      if (this.mCanBusInfoInt[12] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.isShowRadar = var1;
      if (this.isRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(6, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void realKeyClick1(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private void realKeyLongClick1(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
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

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      return (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
   }

   private void setAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2);
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
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      outDoorTemp = this.mCanBusInfoInt[13];
      if (this.isOnlyOutDoorDataChange()) {
         SharePreUtil.setFloatValue(this.mContext, "_271_is_out_door_temp", (float)outDoorTemp);
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      } else {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setCar1SetData() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(0, 0, this.getItemData(this.mCanBusInfoInt[2], 4, 4))).setProgress(this.getItemData(this.mCanBusInfoInt[2], 4, 4)));
      var1.add(new SettingUpdateEntity(0, 1, this.getItemData(this.mCanBusInfoInt[2], 2, 2)));
      var1.add(new SettingUpdateEntity(1, 0, this.getIntByBoolean(DataHandleUtils.getBoolBit(7, this.mCanBusInfoInt[3]))));
      var1.add(new SettingUpdateEntity(1, 1, this.getIntByBoolean(DataHandleUtils.getBoolBit(6, this.mCanBusInfoInt[3]))));
      var1.add(new SettingUpdateEntity(1, 2, this.getItemData(this.mCanBusInfoInt[3], 4, 2)));
      var1.add(new SettingUpdateEntity(2, 0, this.getItemData(this.mCanBusInfoInt[4], 6, 2)));
      var1.add(new SettingUpdateEntity(2, 1, this.getIntByBoolean(DataHandleUtils.getBoolBit(5, this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(2, 2, this.getIntByBoolean(DataHandleUtils.getBoolBit(4, this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(2, 3, this.getItemData(this.mCanBusInfoInt[4], 1, 3)));
      var1.add((new SettingUpdateEntity(2, 4, this.getItemData(this.mCanBusInfoInt[5], 5, 3))).setProgress(this.getItemData(this.mCanBusInfoInt[5], 5, 3) - 1));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCar2SetData() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(0, 0, this.getItemData(this.mCanBusInfoInt[2], 4, 4))).setProgress(this.getItemData(this.mCanBusInfoInt[2], 4, 4)));
      var1.add(new SettingUpdateEntity(0, 1, this.getItemData(this.mCanBusInfoInt[2], 2, 2)));
      var1.add(new SettingUpdateEntity(0, 2, this.getItemData(this.mCanBusInfoInt[2], 0, 2)));
      var1.add(new SettingUpdateEntity(1, 0, this.getIntByBoolean(DataHandleUtils.getBoolBit(6, this.mCanBusInfoInt[3]))));
      var1.add(new SettingUpdateEntity(1, 1, this.getItemData(this.mCanBusInfoInt[3], 4, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setOriginalPanel() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 1) {
         if (var1[3] > _271_voice_btn_data) {
            this.realKeyClick4(7);
            _271_voice_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < _271_voice_btn_data) {
            this.realKeyClick4(8);
            _271_voice_btn_data = this.mCanBusInfoInt[3];
         }
      } else {
         if (var1[3] > _271_up_dn_btn_data) {
            this.realKeyClick4(46);
            _271_up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < _271_up_dn_btn_data) {
            this.realKeyClick4(45);
            _271_up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      }

   }

   private void setPanelButton() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 6) {
            if (var1 != 9) {
               if (var1 != 40) {
                  if (var1 != 43) {
                     if (var1 != 55) {
                        if (var1 != 84) {
                           if (var1 != 2) {
                              if (var1 == 3) {
                                 this.realKeyLongClick1(20);
                              }
                           } else {
                              this.realKeyLongClick1(21);
                           }
                        } else {
                           this.realKeyLongClick1(2);
                        }
                     } else {
                        this.realKeyLongClick1(58);
                     }
                  } else {
                     this.realKeyLongClick1(52);
                  }
               } else {
                  this.realKeyLongClick1(188);
               }
            } else {
               this.realKeyLongClick1(3);
            }
         } else {
            this.realKeyLongClick1(50);
         }
      } else {
         this.realKeyLongClick1(0);
      }

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
                  if (var3 != 49) {
                     if (var3 != 50) {
                        if (var3 != 65) {
                           if (var3 != 97) {
                              if (var3 == 240) {
                                 this.setVersionInfo();
                              }
                           } else if (this.mDifferentId == 0) {
                              this.setCar1SetData();
                           } else {
                              this.setCar2SetData();
                           }
                        } else {
                           this.radarInfo();
                        }
                     } else {
                        this.carBodyInfo();
                     }
                  } else if (this.getCurrentCanDifferentId() == 1) {
                     if (this.isAirMsgReturn(var2)) {
                        return;
                     }

                     this.setAirData();
                  }
               } else if (this.getCurrentCanDifferentId() == 1) {
                  this.setOriginalPanel();
               }
            } else if (this.getCurrentCanDifferentId() == 1) {
               this.setPanelButton();
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setDoorData();
         }
      } else {
         this.keyControl0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, (byte)var10, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
