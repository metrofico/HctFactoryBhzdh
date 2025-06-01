package com.hzbhd.canbus.car._463;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mAirData;
   int[] mCarDoorData;
   Context mContext;
   int[] mRearRadarData;
   int[] mTrackData;
   private UiMgr mUiMgr;
   int selectValue = -1;
   int volValue = -1;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void set0x03Radar(int[] var1) {
      if (this.isRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         RadarInfoUtil.setRearRadarLocationData(4, var1[6], var1[7], var1[8], var1[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setAir0x55(int[] var1) {
      if (var1[9] == 255) {
         this.updateOutDoorTemp(this.mContext, "");
      } else {
         this.updateOutDoorTemp(this.mContext, var1[9] - 40 + "℃");
      }

      var1[9] = 0;
      if (this.isAirDataChange(var1)) {
         if (var1[2] < 10) {
            GeneralAirData.front_left_temperature = "";
         } else {
            GeneralAirData.front_left_temperature = (float)var1[2] / 2.0F + "℃";
         }

         if (var1[3] < 10) {
            GeneralAirData.front_right_temperature = "";
         } else {
            GeneralAirData.front_right_temperature = (float)var1[3] / 2.0F + "℃";
         }

         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4);
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_auto_wind_model = false;
         int var2 = DataHandleUtils.getIntFromByteWithBit(var1[4], 4, 4);
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 != 4) {
                        if (var2 == 6) {
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_left_blow_window = true;
                        }
                     } else {
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_foot = true;
               }
            } else {
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_head = true;
            }
         } else {
            GeneralAirData.front_auto_wind_model = true;
         }

         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[5], 2, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[5], 5, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit0(var1[6]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit1(var1[6]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[6]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var1[6]);
         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2) == 1) {
            GeneralAirData.in_out_cycle = true;
         } else if (DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2) == 2) {
            GeneralAirData.in_out_cycle = false;
         }

         GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit2(var1[7]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit3(var1[7]);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setDoor0x24(int[] var1) {
      var1[3] = 0;
      int var2 = DataHandleUtils.blockBit(var1[2], 6);
      var1[2] = var2;
      var1[2] = DataHandleUtils.blockBit(var2, 7);
      if (this.isBasicInfoChange(var1)) {
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setEsp0x26(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         int var2;
         if (DataHandleUtils.getBoolBit3(var1[3])) {
            var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 3);
            GeneralParkData.trackAngle = (var1[2] & 255 | (var2 & 255) << 8) / 15;
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 3);
            GeneralParkData.trackAngle = -(var1[2] & 255 | (var2 & 255) << 8) / 15;
            this.updateParkUi((Bundle)null, this.mContext);
         }
      }

   }

   private void setSpeed0x41(int[] var1) {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[4], var1[5]));
   }

   private void setSwc0x21(int[] var1) {
      int var2 = var1[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 6) {
                     if (var2 != 9) {
                        if (var2 != 32) {
                           if (var2 != 51) {
                              if (var2 != 57) {
                                 if (var2 != 99) {
                                    if (var2 != 43) {
                                       if (var2 != 44) {
                                          if (var2 != 47) {
                                             if (var2 == 48) {
                                                this.realKeyLongClick1(this.mContext, 68, var1[3]);
                                             }
                                          } else {
                                             this.realKeyLongClick1(this.mContext, 151, var1[3]);
                                          }
                                       } else {
                                          this.realKeyLongClick1(this.mContext, 2, var1[3]);
                                       }
                                    } else {
                                       this.realKeyLongClick1(this.mContext, 52, var1[3]);
                                    }
                                 } else {
                                    this.realKeyLongClick1(this.mContext, 187, var1[3]);
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 4105, var1[3]);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 62, var1[3]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 128, var1[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 3, var1[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 50, var1[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 46, var1[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 45, var1[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 1, var1[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var1[3]);
      }

   }

   private void setSwc0x22(int[] var1) {
      int var2 = var1[2];
      if (var2 != 1) {
         if (var2 == 2) {
            var2 = this.selectValue;
            if (var2 != -1) {
               if (var1[3] > var2) {
                  this.realKeyClick4(this.mContext, 48);
               } else {
                  this.realKeyClick4(this.mContext, 47);
               }
            }

            this.selectValue = var1[3];
         }
      } else {
         var2 = this.volValue;
         if (var2 != -1) {
            if (var1[3] > var2) {
               this.realKeyClick4(this.mContext, 7);
            } else {
               this.realKeyClick4(this.mContext, 8);
            }
         }

         this.volValue = var1[3];
      }

   }

   private void setVersion0x57(int[] var1) {
      int var2 = var1[3];
      String var3;
      if (var2 == 3) {
         var3 = "Accord7";
      } else if (var2 == 4) {
         var3 = "AccordB";
      } else {
         var3 = "";
      }

      var2 = var1[4];
      String var4 = this.formatInteger2.format((long)var1[5]);
      String var5 = this.formatInteger2.format((long)var1[6]);
      String var6 = "V" + var1[7];
      var6 = "Honda" + "_" + var3 + "_" + (var2 + 2000) + var4 + var5 + "_" + var6;
      this.updateVersionInfo(this.mContext, var6);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 3) {
         if (var3 != 36) {
            if (var3 != 38) {
               if (var3 != 65) {
                  if (var3 != 85) {
                     if (var3 != 87) {
                        if (var3 != 33) {
                           if (var3 == 34) {
                              this.setSwc0x22(var4);
                           }
                        } else {
                           this.setSwc0x21(var4);
                        }
                     } else {
                        this.setVersion0x57(var4);
                     }
                  } else {
                     this.setAir0x55(var4);
                  }
               } else {
                  this.setSpeed0x41(var4);
               }
            } else {
               this.setEsp0x26(var4);
            }
         } else {
            this.setDoor0x24(var4);
         }
      } else {
         this.set0x03Radar(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }
}
