package com.hzbhd.canbus.car._330;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
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
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   public static String mediaTag;
   private final String MEDIA_REAR_DISC = "后排DISC";
   private final String MEDIA_TYPE_AUX = "AUX";
   private final String MEDIA_TYPE_CD_DVD = "CD/DVD";
   private final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
   private final String MEDIA_TYPE_RADIO = "RADIO";
   private final String MEDIA_USB = "USB";
   private int airData6 = 0;
   private int airData7 = 0;
   private int airData8 = 0;
   int data2FrontLeft = 0;
   int data3FrontRight = 0;
   int data5OurDoorTem = 0;
   int data6RearLeft = 0;
   int data8RearRight = 0;
   DecimalFormat decimalFormat;
   int differentId;
   int eachCanId;
   int hiState = 0;
   int lightAndBacklight = 0;
   int lowSate = 0;
   private int[] m0x1DData;
   private int[] m0x1EData;
   private int[] m0x21AirData;
   private int[] m0x24Data;
   private int[] mAmplifierDataInt;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private int mData1Info;
   private int mMediaType;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   private int mOutdoorTemperature;
   private byte[] mTrackData;
   private UiMgr mUiMgr;
   private int[] speedInfo;
   private int[] tireTurnSpeed;
   private int[] trafficInfo;

   private void airOfNewVersion(int[] var1) {
      int var3 = this.mOutdoorTemperature;
      int var2 = var1[7];
      if (var3 != var2) {
         this.mOutdoorTemperature = var2;
         Context var4;
         if (DataHandleUtils.getBoolBit0(var1[6])) {
            MyLog.temporaryTracking("外温：直接走了华氏度");
            var4 = this.mContext;
            this.updateOutDoorTemp(var4, this.getOutDoorTemperatureF(var4, var1[7]));
         } else {
            MyLog.temporaryTracking("外温：走了摄氏度");
            if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0) == 1) {
               MyLog.temporaryTracking("外温：还是走了摄氏度");
               var4 = this.mContext;
               this.updateOutDoorTemp(var4, this.getOutDoorTemperatureC(var4, var1[7]));
            } else {
               MyLog.temporaryTracking("外温：然后走了华氏度");
               var4 = this.mContext;
               this.updateOutDoorTemp(var4, this.getOutDoorTemperatureF(var4, var1[7]));
            }
         }
      }

      var1[7] = 0;
      this.mData1Info = var1[3];
      this.emptyAirData1Bit4();
      if (this.is0x28DataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]) ^ true;
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mData1Info);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mData1Info);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mData1Info);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mData1Info, 0, 4);
         if (DataHandleUtils.getBoolBit0(var1[6])) {
            MyLog.temporaryTracking("内温：直接走了华氏度");
            GeneralAirData.front_left_temperature = this.getAirTemperatureF(this.mContext, var1[4]);
            GeneralAirData.front_right_temperature = this.getAirTemperatureF(this.mContext, var1[5]);
         } else {
            MyLog.temporaryTracking("内温：走了摄氏度");
            if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0) == 1) {
               MyLog.temporaryTracking("内温：还是走了摄氏度");
               GeneralAirData.front_left_temperature = this.getAirTemperatureC(this.mContext, var1[4]);
               GeneralAirData.front_right_temperature = this.getAirTemperatureC(this.mContext, var1[5]);
            } else {
               MyLog.temporaryTracking("内温：然后走了华氏度");
               GeneralAirData.front_left_temperature = this.getAirTemperatureF(this.mContext, var1[4]);
               GeneralAirData.front_right_temperature = this.getAirTemperatureF(this.mContext, var1[5]);
            }
         }

         GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(var1[6]);
         GeneralAirData.rear_auto_wind_model = DataHandleUtils.getBoolBit7(var1[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var1[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit2(var1[6]);
         GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(var1[6]);
         if (DataHandleUtils.getBoolBit0(var1[6])) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureF(this.mContext, var1[8]);
            GeneralAirData.rear_right_temperature = this.getAirTemperatureF(this.mContext, var1[10]);
         } else if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0) == 1) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureC(this.mContext, var1[8]);
            GeneralAirData.rear_right_temperature = this.getAirTemperatureC(this.mContext, var1[10]);
         } else {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureF(this.mContext, var1[8]);
            GeneralAirData.rear_right_temperature = this.getAirTemperatureF(this.mContext, var1[10]);
         }

         GeneralAirData.rear_temperature = this.modeIsLowOrHi();
         GeneralAirData.rear_ac = DataHandleUtils.getBoolBit6(var1[9]);
         GeneralAirData.manual = this.judgeManual();
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(var1[9]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[9], 0, 3);
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(var1[9]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(var1[11]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var1[11]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1[11]);
         var3 = DataHandleUtils.getIntFromByteWithBit(var1[12], 4, 4);
         var2 = DataHandleUtils.getIntFromByteWithBit(var1[12], 0, 4);
         if (var3 != 1 && var3 != 2 && var3 != 3) {
            if (var3 != 13 && var3 != 14 && var3 != 15) {
               if (var3 == 0) {
                  GeneralAirData.front_left_seat_heat_level = 0;
                  GeneralAirData.rear_left_seat_heat_level = 0;
                  GeneralAirData.front_left_seat_cold_level = 0;
                  GeneralAirData.rear_left_seat_cold_level = 0;
               }
            } else {
               GeneralAirData.front_left_seat_heat_level = 0;
               GeneralAirData.rear_left_seat_heat_level = 0;
               var3 = -(var3 - 16);
               GeneralAirData.front_left_seat_cold_level = var3;
               GeneralAirData.rear_left_seat_cold_level = var3;
            }
         } else {
            GeneralAirData.front_left_seat_cold_level = 0;
            GeneralAirData.rear_left_seat_cold_level = 0;
            GeneralAirData.rear_left_seat_heat_level = var3;
            GeneralAirData.front_left_seat_heat_level = var3;
         }

         if (var2 != 1 && var2 != 2 && var2 != 3) {
            if (var2 != 13 && var2 != 14 && var2 != 15) {
               if (var2 == 0) {
                  GeneralAirData.front_right_seat_heat_level = 0;
                  GeneralAirData.rear_right_seat_heat_level = 0;
                  GeneralAirData.front_right_seat_cold_level = 0;
                  GeneralAirData.rear_right_seat_cold_level = 0;
               }
            } else {
               GeneralAirData.front_right_seat_heat_level = 0;
               GeneralAirData.rear_right_seat_heat_level = 0;
               var2 = -(var2 - 16);
               GeneralAirData.front_right_seat_cold_level = var2;
               GeneralAirData.rear_right_seat_cold_level = var2;
            }
         } else {
            GeneralAirData.front_right_seat_cold_level = 0;
            GeneralAirData.rear_right_seat_cold_level = 0;
            GeneralAirData.front_right_seat_heat_level = var2;
            GeneralAirData.rear_right_seat_heat_level = var2;
         }

         var2 = this.airData6;
         var3 = var1[8];
         if (var2 == var3 && this.airData7 == var1[9] && this.airData8 == var1[10]) {
            this.updateAirActivity(this.mContext, 1001);
         } else {
            this.airData6 = var3;
            this.airData7 = var1[9];
            this.airData8 = var1[10];
            this.updateAirActivity(this.mContext, 1002);
         }
      }

   }

   private void airOfOldVersion(int[] var1) {
      int var2 = this.mOutdoorTemperature;
      int var3 = var1[7];
      if (var2 != var3) {
         this.mOutdoorTemperature = var3;
         Context var4;
         if (DataHandleUtils.getBoolBit0(var1[6])) {
            MyLog.temporaryTracking("外温：直接走了华氏度");
            var4 = this.mContext;
            this.updateOutDoorTemp(var4, this.getOutDoorTemperatureF(var4, var1[7]));
         } else {
            MyLog.temporaryTracking("外温：走了摄氏度");
            if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0) == 1) {
               MyLog.temporaryTracking("外温：还是走了摄氏度");
               var4 = this.mContext;
               this.updateOutDoorTemp(var4, this.getOutDoorTemperatureC(var4, var1[7]));
            } else {
               MyLog.temporaryTracking("外温：然后走了华氏度");
               var4 = this.mContext;
               this.updateOutDoorTemp(var4, this.getOutDoorTemperatureF(var4, var1[7]));
            }
         }
      }

      var1[7] = 0;
      this.mData1Info = var1[3];
      this.emptyAirData1Bit4();
      if (this.is0x28DataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]) ^ true;
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(var1[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mData1Info);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mData1Info);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mData1Info);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mData1Info, 0, 4);
         if (DataHandleUtils.getBoolBit0(var1[6])) {
            MyLog.temporaryTracking("内温：直接走了华氏度");
            GeneralAirData.front_left_temperature = this.getAirTemperatureF(this.mContext, var1[4]);
            GeneralAirData.front_right_temperature = this.getAirTemperatureF(this.mContext, var1[5]);
         } else {
            MyLog.temporaryTracking("内温：走了摄氏度");
            if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0) == 1) {
               MyLog.temporaryTracking("内温：还是走了摄氏度");
               GeneralAirData.front_left_temperature = this.getAirTemperatureC(this.mContext, var1[4]);
               GeneralAirData.front_right_temperature = this.getAirTemperatureC(this.mContext, var1[5]);
            } else {
               MyLog.temporaryTracking("内温：然后走了华氏度");
               GeneralAirData.front_left_temperature = this.getAirTemperatureF(this.mContext, var1[4]);
               GeneralAirData.front_right_temperature = this.getAirTemperatureF(this.mContext, var1[5]);
            }
         }

         GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(var1[6]);
         GeneralAirData.rear_auto_wind_model = DataHandleUtils.getBoolBit7(var1[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var1[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit2(var1[6]);
         GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(var1[6]);
         if (DataHandleUtils.getBoolBit0(var1[6])) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureF(this.mContext, var1[8]);
         } else if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0) == 1) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureC(this.mContext, var1[8]);
         } else {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureF(this.mContext, var1[8]);
         }

         GeneralAirData.rear_temperature = this.modeIsLowOrHi();
         GeneralAirData.rear_ac = DataHandleUtils.getBoolBit6(var1[9]);
         GeneralAirData.manual = this.judgeManual();
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(var1[9]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[9], 0, 3);
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(var1[9]);
         var3 = this.airData6;
         var2 = var1[8];
         if (var3 == var2 && this.airData7 == var1[9]) {
            this.updateAirActivity(this.mContext, 1001);
         } else {
            this.airData6 = var2;
            this.airData7 = var1[9];
            this.updateAirActivity(this.mContext, 1002);
         }
      }

   }

   private int assignRadarProgress(int var1) {
      if (var1 == 1) {
         return 1;
      } else if (var1 == 2) {
         return 4;
      } else if (var1 == 3) {
         return 7;
      } else {
         return var1 == 4 ? 10 : 0;
      }
   }

   private void emptyAirData1Bit4() {
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1);
      this.mCanBusInfoInt[3] = Integer.parseInt(var6 + "" + var2 + "" + var1 + "" + 0 + var5 + "" + var7 + "" + var3 + "" + var4 + "");
   }

   private String getAirTemperatureC(Context var1, int var2) {
      if (var2 == 0) {
         return "LO";
      } else if (var2 == 31) {
         return "HI";
      } else {
         return var2 > 0 && var2 < 30 ? this.decimalFormat.format((double)(var2 - 1) * 0.5 + 18.0) + this.getTempUnitC(var1) : "";
      }
   }

   private String getAirTemperatureF(Context var1, int var2) {
      if (var2 == 0) {
         return "LO";
      } else if (var2 == 31) {
         return "HI";
      } else {
         return var2 > 0 && var2 < 30 ? this.decimalFormat.format(((double)(var2 - 1) * 0.5 + 18.0) * 1.8 + 32.0) + this.getTempUnitF(var1) : "";
      }
   }

   private String getAppointmentBand(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               switch (var1) {
                  case 16:
                     return "AM";
                  case 17:
                     return "AM1";
                  case 18:
                     return "AM2";
                  default:
                     return "无";
               }
            } else {
               return "FM2";
            }
         } else {
            return "FM1";
         }
      } else {
         return "FM";
      }
   }

   private int getAslState(int var1) {
      return var1 == 8 ? 1 : 0;
   }

   private int getCarType(int var1) {
      if (var1 != 32) {
         if (var1 != 33) {
            if (var1 != 48) {
               if (var1 != 49) {
                  if (var1 != 64) {
                     if (var1 != 65) {
                        if (var1 != 80) {
                           if (var1 != 81) {
                              switch (var1) {
                                 case 96:
                                    return 8;
                                 case 97:
                                    return 9;
                                 case 98:
                                    return 10;
                                 case 99:
                                    return 11;
                                 case 100:
                                    return 12;
                                 default:
                                    return 13;
                              }
                           } else {
                              return 7;
                           }
                        } else {
                           return 6;
                        }
                     } else {
                        return 5;
                     }
                  } else {
                     return 4;
                  }
               } else {
                  return 3;
               }
            } else {
               return 2;
            }
         } else {
            return 1;
         }
      } else {
         return 0;
      }
   }

   private String getCdOrDvdSate(int var1, int var2) {
      String var3;
      if (var1 == 1) {
         var3 = "碟循环";
      } else if (var1 == 2) {
         var3 = "单曲循环";
      } else {
         var3 = "循环关";
      }

      String var4;
      if (var2 == 1) {
         var4 = "乱序";
      } else {
         var4 = "顺序";
      }

      return var3 + "/" + var4;
   }

   private String getDiscLanguage(int var1) {
      switch (var1) {
         case 1:
            return "英语";
         case 2:
            return "法语";
         case 3:
            return "西班牙语";
         case 4:
            return "德语";
         case 5:
            return "意大利语";
         case 6:
            return "日语";
         default:
            return "中文";
      }
   }

   private String getDiscState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     return var1 != 15 ? "空闲状态" : "ERROR 状态";
                  } else {
                     return "EJECT状态";
                  }
               } else {
                  return "播放状态";
               }
            } else {
               return "DISC READING状态";
            }
         } else {
            return "WAIT状态";
         }
      } else {
         return "LOAD状态";
      }
   }

   private String getDiscTypoe(boolean var1) {
      return !var1 ? "CD" : "DVD";
   }

   private String getDiscYesOrNo(boolean var1) {
      return var1 ? "有碟" : "无碟";
   }

   private int getMsbLsbResult(int var1, int var2) {
      return var1 & 255 | (var2 & 255) << 8;
   }

   private String getNowDisc(int var1) {
      switch (var1) {
         case 1:
            return "DISC 1";
         case 2:
            return "DISC 2";
         case 3:
            return "DISC 3";
         case 4:
            return "DISC 4";
         case 5:
            return "DISC 5";
         case 6:
            return "DISC 6";
         default:
            return "无";
      }
   }

   private String getNullHaveState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131762844) : this.mContext.getString(2131762873);
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getmUigMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private List getOriginalDeviceCdDvdUsbUpdateEntityList(int[] var1) {
      String var6 = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) + "";
      String var2 = this.getCdOrDvdSate(DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4), DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4));
      String var3 = this.getMsbLsbResult(var1[6], var1[7]) + "";
      String var4 = this.getMsbLsbResult(var1[8], var1[9]) + "";
      String var5 = var1[10] + ":" + var1[11] + ":" + var1[12];
      String var8 = var1[13] + ":" + var1[14] + ":" + var1[15];
      ArrayList var7 = new ArrayList();
      if (var6 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(0, var6));
      }

      if (var2 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(1, var2));
      }

      if (var3 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(2, var3));
      }

      if (var4 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(3, var4));
      }

      if (var5 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(4, var5));
      }

      if (var8 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(5, var8));
      }

      GeneralOriginalCarDeviceData.runningState = "CD/DVD运行正常";
      return var7;
   }

   private List getOriginalDeviceRadioUpdateEntityList(int[] var1) {
      String var3;
      String var4;
      String var5;
      String var6;
      String var7;
      String var8;
      String var9;
      String var10;
      String var11;
      String var13;
      if (var1[3] == 0) {
         if (var1[4] == 16) {
            var3 = this.getStereoState(DataHandleUtils.getBoolBit7(var1[5])) + "/" + this.getScanState(DataHandleUtils.getBoolBit5(var1[5])) + "/" + this.getPresetStationNumber(DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4));
            var7 = this.getMsbLsbResult(var1[6], var1[7]) + "KHz";
            var13 = var3;
            var3 = "AM";
         } else {
            var3 = "FM" + var1[4];
            var7 = this.decimalFormat.format((double)this.getMsbLsbResult(var1[6], var1[7]) * 0.01) + "MHz";
            var13 = null;
         }

         var4 = null;
         var5 = null;
         var6 = null;
         var8 = null;
         var9 = null;
         var10 = null;
         var11 = null;
      } else {
         int var2 = var1[4];
         if (var2 != 16 && var2 != 17 && var2 != 18) {
            var4 = this.getAppointmentBand(var2);
            var6 = this.decimalFormat.format((double)this.getMsbLsbResult(var1[5], var1[6]) * 0.01) + "MHz";
            var8 = this.decimalFormat.format((double)this.getMsbLsbResult(var1[7], var1[8]) * 0.01) + "MHz";
            var5 = this.decimalFormat.format((double)this.getMsbLsbResult(var1[9], var1[10]) * 0.01) + "MHz";
            var9 = this.decimalFormat.format((double)this.getMsbLsbResult(var1[11], var1[12]) * 0.01) + "MHz";
            var10 = this.decimalFormat.format((double)this.getMsbLsbResult(var1[13], var1[14]) * 0.01) + "MHz";
            var11 = this.decimalFormat.format((double)this.getMsbLsbResult(var1[15], var1[16]) * 0.01) + "MHz";
            var3 = null;
            var7 = null;
            var13 = null;
         } else {
            var4 = this.getAppointmentBand(var2);
            var6 = this.getMsbLsbResult(var1[5], var1[6]) + "KHz";
            var8 = this.getMsbLsbResult(var1[7], var1[8]) + "KHz";
            var5 = this.getMsbLsbResult(var1[9], var1[10]) + "KHz";
            var9 = this.getMsbLsbResult(var1[11], var1[12]) + "KHz";
            var10 = this.getMsbLsbResult(var1[13], var1[14]) + "KHz";
            var11 = this.getMsbLsbResult(var1[15], var1[16]) + "KHz";
            var3 = null;
            var13 = null;
            var7 = null;
         }
      }

      ArrayList var12 = new ArrayList();
      if (var3 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(0, var3));
      }

      if (var13 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(1, var13));
      }

      if (var7 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(2, var7));
      }

      if (var4 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(3, var4));
      }

      if (var6 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(4, var6));
      }

      if (var8 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(5, var8));
      }

      if (var5 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(6, var5));
      }

      if (var9 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(7, var9));
      }

      if (var10 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(8, var10));
      }

      if (var11 != null) {
         var12.add(new OriginalCarDeviceUpdateEntity(9, var11));
      }

      GeneralOriginalCarDeviceData.runningState = "Radio运行正常";
      return var12;
   }

   private List getOriginalDeviceRearDiscUpdateEntityList(int[] var1) {
      String var2 = this.getRearMediaState(DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4));
      String var4 = this.getRearLockState(DataHandleUtils.getIntFromByteWithBit(var1[6], 6, 1));
      String var3 = this.getMsbLsbResult(var1[7], var1[8]) + "";
      String var6 = var1[9] + ":" + var1[10] + ":" + var1[11];
      ArrayList var5 = new ArrayList();
      if (var2 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(0, var2));
      }

      if (var4 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(1, var4));
      }

      if (var3 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(2, var3));
      }

      if (var6 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(3, var6));
      }

      GeneralOriginalCarDeviceData.runningState = "DISC运行正常";
      return var5;
   }

   private List getOriginalDeviceUsbUpdateEntityList(int[] var1) {
      String var6 = this.getUsbState1(DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4));
      String var2 = this.getUsbState2(DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 2));
      String var5 = this.getMsbLsbResult(var1[7], var1[6]) + "";
      String var4 = this.getMsbLsbResult(var1[9], var1[8]) + "";
      String var3 = var1[10] + "";
      String var7 = var1[4] + ":" + var1[5];
      String var9 = var1[11] + "%";
      ArrayList var8 = new ArrayList();
      if (var6 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(0, var6));
      }

      if (var2 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(1, var2));
      }

      if (var5 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(2, var5));
      }

      if (var4 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(3, var4));
      }

      if (var3 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(4, var3));
      }

      if (var7 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(5, var7));
      }

      if (var9 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(6, var9));
      }

      GeneralOriginalCarDeviceData.runningState = "SUB运行正常";
      return var8;
   }

   private String getOutDoorTemperatureC(Context var1, int var2) {
      return this.decimalFormat.format((double)var2 * 0.5 - 40.0) + this.getTempUnitC(var1);
   }

   private String getOutDoorTemperatureF(Context var1, int var2) {
      return this.decimalFormat.format(((double)var2 * 0.5 - 40.0) * 1.8 + 32.0) + this.getTempUnitF(var1);
   }

   private String getPresetStationNumber(int var1) {
      return var1 == 0 ? "无预设电台" : "预设电台" + var1;
   }

   private String getRadarDisplayState(boolean var1) {
      return var1 ? "打开" : "关闭";
   }

   private String getRadarDistance(boolean var1) {
      return var1 ? "远" : "近";
   }

   private String getRadarSwitch(boolean var1) {
      return var1 ? "开" : "关";
   }

   private String getRearLockState(int var1) {
      return var1 != 1 ? "未锁止" : "锁止";
   }

   private String getRearMediaState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     return var1 != 15 ? "空闲状态" : "ERROR状态";
                  } else {
                     return "EJECT状态";
                  }
               } else {
                  return "播放状态";
               }
            } else {
               return "DISC READING ";
            }
         } else {
            return "WAIT状态";
         }
      } else {
         return "LOAD状态";
      }
   }

   private String getScanState(boolean var1) {
      return var1 ? "扫描状态" : "非扫描状态";
   }

   private String getStereoState(boolean var1) {
      return var1 ? "立体声" : "非立体声";
   }

   private String getUsbState1(int var1) {
      String var2;
      switch (var1) {
         case 1:
            var2 = "LAODING";
            break;
         case 2:
            var2 = "没有连接 USB 设备";
            break;
         case 3:
            var2 = "设备已经连接";
            break;
         case 4:
            var2 = "播放中";
            break;
         case 5:
            var2 = "暂停(LOAD IMAGE)";
            break;
         case 6:
            var2 = "其它状态";
            break;
         default:
            var2 = "关闭/停止状态";
      }

      return var2;
   }

   private String getUsbState2(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            var2 = "无媒体信息";
         } else {
            var2 = "USB";
         }
      } else {
         var2 = "IPOD";
      }

      return var2;
   }

   private UiMgr getmUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            byte var5 = (byte)(-GeneralAmplifierData.frontRear + 7);
            byte var6 = (byte)(GeneralAmplifierData.leftRight + 7);
            byte var7 = (byte)GeneralAmplifierData.volume;
            byte var8 = (byte)(GeneralAmplifierData.bandTreble + 2);
            byte var4 = (byte)(GeneralAmplifierData.bandMiddle + 2);
            byte var3 = (byte)(GeneralAmplifierData.bandBass + 2);
            this.iterator = Arrays.stream(new byte[][]{{22, -127, 1}, {22, -124, 1, var5}, {22, -124, 2, var6}, {22, -124, 7, var7}, {22, -124, 5, var8}, {22, -124, 6, var4}, {22, -124, 4, var3}}).iterator();
         }

         public void run() {
            if (this.iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.iterator.next());
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private void initOriginalDeviceDataArray() {
      OriginalDeviceData var1 = new OriginalDeviceData(new ArrayList());
      ArrayList var5 = new ArrayList();
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_band", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_frequency", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_band", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_1", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_2", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_3", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_4", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_5", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_6", (String)null));
      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_currently_selected_disc", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_time", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", (String)null));
      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Back_row_media_status", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Rear_lock_status", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", (String)null));
      ArrayList var6 = new ArrayList();
      var6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_sub_status", (String)null));
      var6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_media_status", (String)null));
      var6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", (String)null));
      var6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", (String)null));
      var6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_folaer_status", (String)null));
      var6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_play_time", (String)null));
      var6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Playback_progress", (String)null));
      SparseArray var4 = new SparseArray();
      this.mOriginalDeviceDataArray = var4;
      var4.put(0, var1);
      this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(var5, new String[]{"left", "up", "play_pause", "down", "right"}));
      this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(var2, new String[]{"prev_disc", "left", "up", "random", "repeat", "down", "right", "next_disc"}));
      this.mOriginalDeviceDataArray.put(3, var1);
      this.mOriginalDeviceDataArray.put(4, new OriginalDeviceData(var3));
      this.mOriginalDeviceDataArray.put(33, new OriginalDeviceData(var6, new String[]{"prev_disc", "play_pause", "next_disc"}));
   }

   private boolean is0x1DDataChange() {
      if (Arrays.equals(this.m0x1DData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x1DData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x1EDataChange() {
      if (Arrays.equals(this.m0x1EData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x1EData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x24DataChange() {
      if (Arrays.equals(this.m0x24Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x24Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x28DataChange() {
      if (Arrays.equals(this.m0x21AirData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.m0x21AirData = this.mCanBusInfoInt;
         return true;
      }
   }

   private boolean isTrackDataChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean judgeManual() {
      return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3) != 0;
   }

   private String modeIsLowOrHi() {
      String var1;
      if (this.lowSate != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1) && DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1) != 0) {
         var1 = "LOW";
      } else {
         var1 = "";
      }

      String var2 = var1;
      if (this.hiState != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1)) {
         var2 = var1;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1) != 0) {
            var2 = "HI";
         }
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
         this.lowSate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
      } else {
         this.lowSate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
         this.hiState = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
      } else {
         this.hiState = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
      }

      return var2;
   }

   private void set0x16Speed(int[] var1) {
      int var2 = this.eachCanId;
      if (var2 == 2 || var2 == 4 || var2 == 11) {
         this.speedInfo = var1;
         ArrayList var3 = new ArrayList();
         if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 1, 0) == 1) {
            var3.add(new DriverUpdateEntity(0, 0, this.decimalFormat.format((long)((var1[2] + var1[3] * 256) / 16)) + " KM/h"));
         } else {
            var3.add(new DriverUpdateEntity(0, 0, this.decimalFormat.format((double)((var1[2] + var1[3] * 256) / 16) * 0.6213712) + " MPH"));
         }

         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[3], var1[2]));
      }
   }

   private void set0x1ERearRadarInfo() {
      int var1 = this.eachCanId;
      if (var1 == 1 || var1 == 2 || var1 == 4 || var1 == 5 || var1 == 6 || var1 == 7 || var1 == 11) {
         if (this.is0x1EDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, this.assignRadarProgress(this.mCanBusInfoInt[2]), this.assignRadarProgress(this.mCanBusInfoInt[3]), this.assignRadarProgress(this.mCanBusInfoInt[4]), this.assignRadarProgress(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.updateParkUi((Bundle)null, this.mContext);
         }

         if (this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status") != -1) {
            ArrayList var2 = new ArrayList();
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Display_switch_status"), this.getRadarDisplayState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_distance"), this.getRadarDistance(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Radar_switch_status"), this.getRadarSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Volume_intensity"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3) + ""));
            this.updateGeneralDriveData(var2);
            this.updateDriveDataActivity((Bundle)null);
         }

      }
   }

   private void set0x1dFrontRadarInfo() {
      int var1 = this.eachCanId;
      if (var1 == 1 || var1 == 2 || var1 == 4 || var1 == 5 || var1 == 6 || var1 == 7 || var1 == 11) {
         if (this.is0x1DDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(10, this.assignRadarProgress(this.mCanBusInfoInt[2]), this.assignRadarProgress(this.mCanBusInfoInt[3]), this.assignRadarProgress(this.mCanBusInfoInt[4]), this.assignRadarProgress(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.updateParkUi((Bundle)null, this.mContext);
         }

      }
   }

   private void set0x20WheelKey() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 19) {
                  if (var1 != 20) {
                     if (var1 != 135) {
                        switch (var1) {
                           case 7:
                              this.realKeyClick2(this.mContext, 2, var1, var2[3]);
                              break;
                           case 8:
                              this.realKeyClick2(this.mContext, 3, var1, var2[3]);
                              break;
                           case 9:
                              this.realKeyLongClick1(this.mContext, 14, var2[3]);
                              break;
                           case 10:
                              this.realKeyClick2(this.mContext, 15, var1, var2[3]);
                        }
                     } else {
                        this.realKeyClick2(this.mContext, 3, var1, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void set0x21UsbInfo() {
      int var1 = this.eachCanId;
      if (var1 == 2 || var1 == 5 || var1 == 11) {
         mediaTag = "USB";
         OriginalDeviceData var4 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(this.mCanBusInfoInt[1]);
         Bundle var3 = null;
         GeneralOriginalCarDeviceData.mList = null;
         GeneralOriginalCarDeviceData.cdStatus = "USB";
         GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceUsbUpdateEntityList(this.mCanBusInfoInt);
         int var2 = this.mMediaType;
         var1 = this.mCanBusInfoInt[2];
         if (var2 != var1) {
            this.mMediaType = var1;
            OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(this.mContext);
            var5.setItems(var4.getItemList());
            var5.setRowBottomBtnAction(var4.getBottomBtns());
            var3 = new Bundle();
            var3.putBoolean("bundle_key_orinal_init_view", true);
         }

         this.updateOriginalCarDeviceActivity(var3);
      }
   }

   private void set0x24BaseInfo() {
      if (this.is0x24DataChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x28AirInfo(int[] var1) {
      if (var1 != null) {
         MyLog.temporaryTracking("更新了Air数据");
         int[] var2 = this.mCanBusInfoInt;
         this.data2FrontLeft = var2[4];
         this.data3FrontRight = var2[5];
         this.data6RearLeft = var2[8];
         this.data8RearRight = var2[10];
         this.data5OurDoorTem = var2[7];
         this.airOfNewVersion(var1);
      }
   }

   private void set0x29TrackData() {
      int var1 = this.eachCanId;
      if (var1 != 8 && var1 != 9 && this.isTrackDataChange()) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 380, 12);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x31AmplifierInfo() {
      GeneralAmplifierData.frontRear = -DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) + 7;
      GeneralAmplifierData.leftRight = -(-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) + 7);
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 2;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      this.updateAmplifierActivity(new Bundle());
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 3, this.getAslState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
      var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 4, this.getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)))).setValueStr(true));
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x32SystemInfo() {
      ArrayList var1 = new ArrayList();
      if (this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info") != -1) {
         var1.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 2, this.getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)))).setValueStr(true));
         var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x35TrafficInfo(int[] var1) {
      this.trafficInfo = var1;
      int var2 = this.eachCanId;
      if (var2 == 3 || var2 == 6 || var2 == 7 || var2 == 8 || var2 == 9 || var2 == 10 || var2 == 11) {
         ArrayList var3 = new ArrayList();
         if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 1, 0) == 1) {
            var3.add(new DriverUpdateEntity(0, 1, this.decimalFormat.format((double)this.getMsbLsbResult(var1[3], var1[2]) * 0.1) + " L/100Km"));
            var3.add(new DriverUpdateEntity(0, 2, this.decimalFormat.format((double)this.getMsbLsbResult(var1[5], var1[4]) * 0.1) + " L/100Km"));
            var3.add(new DriverUpdateEntity(0, 3, this.decimalFormat.format((long)this.getMsbLsbResult(var1[7], var1[6])) + " KM"));
            var3.add(new DriverUpdateEntity(0, 4, this.decimalFormat.format((long)this.getMsbLsbResult(var1[9], var1[8])) + " KM/h"));
            var3.add(new DriverUpdateEntity(0, 5, this.decimalFormat.format((long)this.getMsbLsbResult(var1[11], var1[10])) + " KM"));
         } else {
            var3.add(new DriverUpdateEntity(0, 1, this.decimalFormat.format((double)this.getMsbLsbResult(var1[3], var1[2]) * 0.1 * 1.609344) + " L/100MI"));
            var3.add(new DriverUpdateEntity(0, 2, this.decimalFormat.format((double)this.getMsbLsbResult(var1[5], var1[4]) * 0.1 * 1.609344) + " L/100MI"));
            var3.add(new DriverUpdateEntity(0, 3, this.decimalFormat.format((long)(this.getMsbLsbResult(var1[7], var1[6]) * 1000)) + " M"));
            var3.add(new DriverUpdateEntity(0, 4, this.decimalFormat.format((double)this.getMsbLsbResult(var1[9], var1[8]) * 0.6213712) + " MPH"));
            var3.add(new DriverUpdateEntity(0, 5, this.decimalFormat.format((long)(this.getMsbLsbResult(var1[11], var1[10]) * 1000)) + " M"));
         }

         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private void set0x50CarSpeed(int[] var1) {
      int var2 = this.eachCanId;
      if (var2 == 2 || var2 == 3 || var2 == 4 || var2 == 6 || var2 == 7 || var2 == 10 || var2 == 11) {
         this.tireTurnSpeed = var1;
         if (this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_rotate_speed") != -1) {
            ArrayList var3 = new ArrayList();
            var3.add(new DriverUpdateEntity(0, this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_rotate_speed"), var1[3] * 256 + var1[2] + " RPM"));
            this.updateGeneralDriveData(var3);
            this.updateDriveDataActivity((Bundle)null);
         }

      }
   }

   private void set0x60SteeringWheelControlMode() {
      int var1 = this.eachCanId;
      if (var1 == 1 || var1 == 3 || var1 == 4 || var1 == 5 || var1 == 6 || var1 == 8 || var1 != 11) {
         ArrayList var2 = new ArrayList();
         if (this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_Mode_selection") != -1) {
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_Mode_selection"), 0, this.mCanBusInfoInt[2]));
         }

         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void set0x61MediaState() {
      int var1 = this.eachCanId;
      if (var1 == 1 || var1 == 3 || var1 == 4 || var1 == 6 || var1 == 8 || var1 == 9 || var1 == 11) {
         if (this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state") != -1) {
            ArrayList var2 = new ArrayList();
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_1"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_2"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_3"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_4"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_5"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_6"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_status"), this.getDiscState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_currently_selected_disc"), this.getNowDisc(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_1"), this.getDiscTypoe(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_2"), this.getDiscTypoe(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_3"), this.getDiscTypoe(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_4"), this.getDiscTypoe(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_5"), this.getDiscTypoe(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_6"), this.getDiscTypoe(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Subtitle_language"), this.getDiscLanguage(this.mCanBusInfoInt[6])));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Voice_language"), this.getDiscLanguage(this.mCanBusInfoInt[7])));
            var2.add(new DriverUpdateEntity(this.getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_dvd_language"), this.getDiscLanguage(this.mCanBusInfoInt[8])));
            this.updateGeneralDriveData(var2);
            this.updateDriveDataActivity((Bundle)null);
         }

      }
   }

   private void set0x62MediaSourceInfo() {
      OriginalDeviceData var4 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(this.mCanBusInfoInt[2]);
      Bundle var3 = null;
      GeneralOriginalCarDeviceData.mList = null;
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     mediaTag = "DISC";
                     GeneralOriginalCarDeviceData.cdStatus = "后排DISC";
                     GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceRearDiscUpdateEntityList(this.mCanBusInfoInt);
                  }
               } else {
                  mediaTag = "AUX";
                  GeneralOriginalCarDeviceData.cdStatus = "AUX";
                  GeneralOriginalCarDeviceData.runningState = "AUX运行正常";
               }
            } else {
               mediaTag = "CD";
               GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
               GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanBusInfoInt);
            }
         } else {
            mediaTag = "RADIO";
            GeneralOriginalCarDeviceData.cdStatus = "RADIO";
            GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceRadioUpdateEntityList(this.mCanBusInfoInt);
         }
      } else {
         mediaTag = "MEDIA OFF";
         GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
         GeneralOriginalCarDeviceData.runningState = "MEDIA OFF";
      }

      var1 = this.mMediaType;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.mMediaType = var2;
         OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(this.mContext);
         var5.setItems(var4.getItemList());
         var5.setRowBottomBtnAction(var4.getBottomBtns());
         var3 = new Bundle();
         var3.putBoolean("bundle_key_orinal_init_view", true);
      }

      this.updateOriginalCarDeviceActivity(var3);
   }

   private void set0x63ModelSetting() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_setting_info"), 0, this.getCarType(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x64WheelKey() {
      int var1 = this.eachCanId;
      if (var1 == 1 || var1 == 4 || var1 == 6 || var1 == 8 || var1 == 11) {
         int[] var2 = this.mCanBusInfoInt;
         var1 = var2[2];
         if (var1 != 16) {
            if (var1 != 17) {
               switch (var1) {
                  case 0:
                     this.realKeyLongClick1(this.mContext, 0, var2[3]);
                     break;
                  case 1:
                     this.realKeyLongClick1(this.mContext, 77, var2[3]);
                     break;
                  case 2:
                     this.changeBandFm1();
                     break;
                  case 3:
                     this.changeBandFm2();
                     break;
                  case 4:
                     this.realKeyLongClick1(this.mContext, 141, var2[3]);
                     break;
                  case 5:
                     this.realKeyLongClick1(this.mContext, 128, var2[3]);
                     break;
                  case 6:
                     this.realKeyLongClick1(this.mContext, 4, var2[3]);
                     break;
                  case 7:
                     this.realKeyLongClick1(this.mContext, 151, var2[3]);
                     break;
                  case 8:
                     this.realKeyLongClick1(this.mContext, 68, var2[3]);
                     break;
                  case 9:
                     this.realKeyLongClick1(this.mContext, 57, var2[3]);
               }
            } else {
               this.updateAirActivity(this.mContext, 0);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 53, var2[3]);
         }

      }
   }

   private void set0x65RockerData(Context var1) {
      int var2 = this.eachCanId;
      if (var2 == 3 || var2 == 11) {
         int[] var3 = this.mCanBusInfoInt;
         var2 = var3[2];
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 3) {
                  if (var2 != 5) {
                     if (var2 != 7) {
                        switch (var2) {
                           case 16:
                              if (var3[3] == 0) {
                                 return;
                              }

                              this.realKeyClick(var1, 7);
                              break;
                           case 17:
                              if (var3[3] == 0) {
                                 return;
                              }

                              this.realKeyClick(var1, 8);
                              break;
                           case 18:
                              this.realKeyLongClick1(var1, 49, var3[3]);
                              break;
                           case 19:
                              this.realKeyLongClick1(var1, 50, var3[3]);
                              break;
                           case 20:
                              this.realKeyLongClick1(var1, 151, var3[3]);
                              break;
                           case 21:
                              this.realKeyLongClick1(var1, 4, var3[3]);
                              break;
                           case 22:
                              this.realKeyLongClick1(var1, 128, var3[3]);
                        }
                     } else {
                        this.realKeyLongClick1(var1, 47, var3[3]);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 46, var3[3]);
                  }
               } else {
                  this.realKeyLongClick1(var1, 48, var3[3]);
               }
            } else {
               this.realKeyLongClick1(var1, 45, var3[3]);
            }
         } else {
            this.realKeyLongClick1(var1, 0, var3[3]);
         }

      }
   }

   public void SendBroadcast() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new CountDownTimer(this, 5000L, 1000L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  Intent var1 = new Intent();
                  var1.setAction("CAN_ID330_GX470_UNIT_TYPE");
                  var1.putExtra("TYPE", SharePreUtil.getIntValue(this.this$1.this$0.mContext, "C" + this.this$1.this$0.getCurrentCanDifferentId() + "M" + this.this$1.this$0.getCurrentEachCanId() + "L" + this.this$1.this$0.getmUigMgr(this.this$1.this$0.mContext).getLeftIndexes(this.this$1.this$0.mContext, "_330_unit_selection") + "R" + 1, 0));
                  this.this$1.this$0.mContext.sendBroadcast(var1);
                  MyLog.temporaryTracking("单位设置：发出了单位广播");
               }

               public void onTick(long var1) {
                  Intent var3 = new Intent();
                  var3.setAction("CAN_ID330_GX470_UNIT_TYPE");
                  var3.putExtra("TYPE", SharePreUtil.getIntValue(this.this$1.this$0.mContext, "C" + this.this$1.this$0.getCurrentCanDifferentId() + "M" + this.this$1.this$0.getCurrentEachCanId() + "L" + this.this$1.this$0.getmUigMgr(this.this$1.this$0.mContext).getLeftIndexes(this.this$1.this$0.mContext, "_330_unit_selection") + "R" + 1, 0));
                  this.this$1.this$0.mContext.sendBroadcast(var3);
                  MyLog.temporaryTracking("单位设置：发出了单位广播");
               }
            }).start();
         }
      });
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.decimalFormat = new DecimalFormat("########00.00");
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();
            this.this$0.initOriginalDeviceDataArray();
         }
      }).start();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 22) {
         if (var3 != 36) {
            if (var3 != 53) {
               if (var3 != 80) {
                  if (var3 != 29) {
                     if (var3 != 30) {
                        if (var3 != 32) {
                           if (var3 != 33) {
                              if (var3 != 40) {
                                 if (var3 != 41) {
                                    switch (var3) {
                                       case 48:
                                          this.set0x30VersionInfo();
                                          break;
                                       case 49:
                                          this.set0x31AmplifierInfo();
                                          break;
                                       case 50:
                                          this.set0x32SystemInfo();
                                          break;
                                       default:
                                          switch (var3) {
                                             case 96:
                                                this.set0x60SteeringWheelControlMode();
                                                break;
                                             case 97:
                                                this.set0x61MediaState();
                                                break;
                                             case 98:
                                                this.set0x62MediaSourceInfo();
                                                break;
                                             case 99:
                                                this.set0x63ModelSetting();
                                                break;
                                             case 100:
                                                this.set0x64WheelKey();
                                                break;
                                             case 101:
                                                this.set0x65RockerData(this.mContext);
                                          }
                                    }
                                 } else {
                                    this.set0x29TrackData();
                                 }
                              } else {
                                 this.set0x28AirInfo(var4);
                              }
                           } else {
                              this.set0x21UsbInfo();
                           }
                        } else {
                           this.set0x20WheelKey();
                        }
                     } else {
                        this.set0x1ERearRadarInfo();
                     }
                  } else {
                     this.set0x1dFrontRadarInfo();
                  }
               } else {
                  this.set0x50CarSpeed(var4);
               }
            } else {
               this.set0x35TrafficInfo(var4);
            }
         } else {
            this.set0x24BaseInfo();
         }
      } else {
         this.set0x16Speed(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachCanId = this.getCurrentEachCanId();
      this.initAmplifier(var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public void startSettingActivity(int var1, int var2) {
      this.startSettingActivity(this.mContext, var1, var2);
   }

   public void uadateTrafficInfo() {
      int[] var1 = this.trafficInfo;
      if (var1 != null) {
         this.set0x35TrafficInfo(var1);
      }

      var1 = this.speedInfo;
      if (var1 != null) {
         this.set0x16Speed(var1);
      }

      var1 = this.tireTurnSpeed;
      if (var1 != null) {
         this.set0x50CarSpeed(var1);
      }

   }

   public void updateAirInfo() {
      int var1 = SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0);
      int var2;
      if (var1 == 1) {
         var2 = this.data2FrontLeft;
         if (var2 != 0) {
            GeneralAirData.front_left_temperature = this.getAirTemperatureC(this.mContext, var2);
         }

         var2 = this.data3FrontRight;
         if (var2 != 0) {
            GeneralAirData.front_right_temperature = this.getAirTemperatureC(this.mContext, var2);
         }
      } else {
         var2 = this.data2FrontLeft;
         if (var2 != 0) {
            GeneralAirData.front_left_temperature = this.getAirTemperatureF(this.mContext, var2);
         }

         var2 = this.data3FrontRight;
         if (var2 != 0) {
            GeneralAirData.front_right_temperature = this.getAirTemperatureF(this.mContext, var2);
         }
      }

      if (var1 == 1) {
         var2 = this.data6RearLeft;
         if (var2 != 0) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureC(this.mContext, var2);
         }

         var2 = this.data8RearRight;
         if (var2 != 0) {
            GeneralAirData.rear_right_temperature = this.getAirTemperatureC(this.mContext, var2);
         }
      } else {
         var2 = this.data6RearLeft;
         if (var2 != 0) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureF(this.mContext, var2);
         }

         var2 = this.data8RearRight;
         if (var2 != 0) {
            GeneralAirData.rear_right_temperature = this.getAirTemperatureF(this.mContext, var2);
         }
      }

      Context var3;
      if (var1 == 1) {
         MyLog.temporaryTracking("摄氏度数据1：" + this.data5OurDoorTem);
         MyLog.temporaryTracking("摄氏度数据2：" + this.getOutDoorTemperatureC(this.mContext, this.data5OurDoorTem));
         var3 = this.mContext;
         this.updateOutDoorTemp(var3, this.getOutDoorTemperatureC(var3, this.data5OurDoorTem));
      } else {
         MyLog.temporaryTracking("华氏度数据1：" + this.data5OurDoorTem);
         MyLog.temporaryTracking("华氏度数据2：" + this.getOutDoorTemperatureF(this.mContext, this.data5OurDoorTem));
         var3 = this.mContext;
         this.updateOutDoorTemp(var3, this.getOutDoorTemperatureF(var3, this.data5OurDoorTem));
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var3, var4, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   private static class OriginalDeviceData {
      private final String[] bottomBtns;
      private final List list;

      public OriginalDeviceData(List var1) {
         this(var1, (String[])null);
      }

      public OriginalDeviceData(List var1, String[] var2) {
         this.list = var1;
         this.bottomBtns = var2;
      }

      public String[] getBottomBtns() {
         return this.bottomBtns;
      }

      public List getItemList() {
         return this.list;
      }
   }
}
