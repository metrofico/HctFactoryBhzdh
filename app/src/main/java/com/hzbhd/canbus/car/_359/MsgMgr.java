package com.hzbhd.canbus.car._359;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
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
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   public static String mediaTag;
   private final String MEDIA_REAR_DISC = "后排DISC";
   private final String MEDIA_TYPE_AUX = "AUX";
   private final String MEDIA_TYPE_CD_DVD = "CD/DVD";
   private final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
   private final String MEDIA_TYPE_RADIO = "RADIO";
   private final String MEDIA_USB = "USB";
   int data2FrontLeft = 0;
   int data3FrontRight = 0;
   int data5OurDoorTem = 0;
   int data6RearLeft = 0;
   int data8RearRight = 0;
   DecimalFormat decimalFormat;
   private int eachId;
   int[] mAirData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mMediaType;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   private UiMgr uiMgr;

   private void ControlMode0x60() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_Mode_selection"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_Mode_selection", "_330_Steering_wheel_control_mode"), this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private String getAirTemperatureC(Context var1, int var2) {
      if (var2 == 0) {
         return "LO";
      } else if (var2 == 31) {
         return "HI";
      } else {
         return var2 > 0 && var2 < 30 ? (double)(var2 - 1) * 0.5 + 18.0 + this.getTempUnitC(var1) : "";
      }
   }

   private String getAirTemperatureF(Context var1, int var2) {
      this.decimalFormat = new DecimalFormat("00.00");
      String var3;
      if (var2 == 0) {
         var3 = "LO";
      } else if (var2 == 31) {
         var3 = "HI";
      } else if (var2 > 0 && var2 < 30) {
         var3 = this.decimalFormat.format(((double)(var2 - 1) * 0.5 + 18.0) * 1.8 + 32.0) + this.getTempUnitF(var1);
      } else {
         var3 = "";
      }

      return var3;
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
      return var1 == 32 ? 0 : 1;
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
            return this.mContext.getResources().getString(2131768993);
         case 2:
            return this.mContext.getResources().getString(2131768970);
         case 3:
            return this.mContext.getResources().getString(2131768988);
         case 4:
            return this.mContext.getResources().getString(2131768971);
         case 5:
            return this.mContext.getResources().getString(2131768977);
         case 6:
            return this.mContext.getResources().getString(2131768978);
         default:
            return this.mContext.getResources().getString(2131768994);
      }
   }

   private String getDiscState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     return var1 != 15 ? this.mContext.getResources().getString(2131761253) : this.mContext.getResources().getString(2131761256);
                  } else {
                     return this.mContext.getResources().getString(2131761260);
                  }
               } else {
                  return this.mContext.getResources().getString(2131761259);
               }
            } else {
               return this.mContext.getResources().getString(2131761258);
            }
         } else {
            return this.mContext.getResources().getString(2131761257);
         }
      } else {
         return this.mContext.getResources().getString(2131761254);
      }
   }

   private String getDiscTypoe(boolean var1) {
      return !var1 ? "CD" : "DVD";
   }

   private String getDiscYesOrNo(boolean var1) {
      return var1 ? this.mContext.getResources().getString(2131763631) : this.mContext.getResources().getString(2131763642);
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
            return this.mContext.getResources().getString(2131756671);
      }
   }

   private String getNullHaveState(int var1) {
      return var1 == 0 ? this.mContext.getString(2131762844) : this.mContext.getString(2131762873);
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private List getOriginalDeviceCdDvdUsbUpdateEntityList(int[] var1) {
      String var4 = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) + "";
      String var6 = this.getCdOrDvdSate(DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4), DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4));
      String var2 = this.getMsbLsbResult(var1[6], var1[7]) + "";
      String var5 = this.getMsbLsbResult(var1[8], var1[9]) + "";
      String var3 = var1[10] + ":" + var1[11] + ":" + var1[12];
      String var7 = var1[13] + ":" + var1[14] + ":" + var1[15];
      ArrayList var8 = new ArrayList();
      if (var4 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(0, var4));
      }

      if (var6 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(1, var6));
      }

      if (var2 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(2, var2));
      }

      if (var5 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(3, var5));
      }

      if (var3 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(4, var3));
      }

      if (var7 != null) {
         var8.add(new OriginalCarDeviceUpdateEntity(5, var7));
      }

      GeneralOriginalCarDeviceData.runningState = "CD/DVD运行正常";
      return var8;
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
      String var4 = this.getRearMediaState(DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4));
      String var3 = this.getRearLockState(DataHandleUtils.getIntFromByteWithBit(var1[6], 6, 1));
      String var2 = this.getMsbLsbResult(var1[7], var1[8]) + "";
      String var6 = var1[9] + ":" + var1[10] + ":" + var1[11];
      ArrayList var5 = new ArrayList();
      if (var4 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(0, var4));
      }

      if (var3 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(1, var3));
      }

      if (var2 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(2, var2));
      }

      if (var6 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(3, var6));
      }

      GeneralOriginalCarDeviceData.runningState = "DISC运行正常";
      return var5;
   }

   private List getOriginalDeviceUsbUpdateEntityList(int[] var1) {
      String var2 = this.getUsbState1(DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4));
      String var4 = this.getUsbState2(DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 2));
      String var3 = this.getMsbLsbResult(var1[7], var1[6]) + "";
      String var7 = this.getMsbLsbResult(var1[9], var1[8]) + "";
      String var5 = var1[10] + "";
      String var6 = var1[4] + ":" + var1[5];
      String var8 = var1[11] + "%";
      ArrayList var9 = new ArrayList();
      if (var2 != null) {
         var9.add(new OriginalCarDeviceUpdateEntity(0, var2));
      }

      if (var4 != null) {
         var9.add(new OriginalCarDeviceUpdateEntity(1, var4));
      }

      if (var3 != null) {
         var9.add(new OriginalCarDeviceUpdateEntity(2, var3));
      }

      if (var7 != null) {
         var9.add(new OriginalCarDeviceUpdateEntity(3, var7));
      }

      if (var5 != null) {
         var9.add(new OriginalCarDeviceUpdateEntity(4, var5));
      }

      if (var6 != null) {
         var9.add(new OriginalCarDeviceUpdateEntity(5, var6));
      }

      if (var8 != null) {
         var9.add(new OriginalCarDeviceUpdateEntity(6, var8));
      }

      GeneralOriginalCarDeviceData.runningState = "SUB运行正常";
      return var9;
   }

   private String getPresetStationNumber(int var1) {
      return var1 == 0 ? "无预设电台" : "预设电台" + var1;
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

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
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

   private void initOriginalDeviceDataArray() {
      OriginalDeviceData var6 = new OriginalDeviceData(new ArrayList());
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
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_currently_selected_disc", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_time", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", (String)null));
      ArrayList var4 = new ArrayList();
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Back_row_media_status", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Rear_lock_status", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", (String)null));
      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_sub_status", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_media_status", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_folaer_status", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_play_time", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Playback_progress", (String)null));
      SparseArray var2 = new SparseArray();
      this.mOriginalDeviceDataArray = var2;
      var2.put(0, var6);
      this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(var5, new String[]{"left", "up", "play_pause", "down", "right"}));
      this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(var1, new String[]{"prev_disc", "left", "up", "random", "repeat", "down", "right", "next_disc"}));
      this.mOriginalDeviceDataArray.put(3, var6);
      this.mOriginalDeviceDataArray.put(4, new OriginalDeviceData(var4));
      this.mOriginalDeviceDataArray.put(33, new OriginalDeviceData(var3, new String[]{"prev_disc", "play_pause", "next_disc"}));
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return true;
      }
   }

   private String resolveAirTemperature(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 31) {
         var2 = "HI";
      } else if (var1 >= 1 && var1 <= 29) {
         var2 = (double)(var1 - 1) * 0.5 + 18.0 + this.getTempUnitC(this.mContext);
      } else {
         var2 = null;
      }

      return var2;
   }

   private String resolveIGAndAcc(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131763005);
      } else {
         var2 = this.mContext.getResources().getString(2131763014);
      }

      return var2;
   }

   private void setAirInfo0x28() {
      int[] var4 = this.mCanBusInfoInt;
      int var2 = var4[2];
      int var1 = var4[3];
      int var3 = var4[6];
      GeneralAirData.power = DataHandleUtils.getBoolBit7(var2);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(var2);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(var2) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(var2);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(var2);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(var2);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit7(var1);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(var1);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(var1);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(var1);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var1);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1, 0, 4);
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
         GeneralAirData.front_left_temperature = this.getAirTemperatureF(this.mContext, this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getAirTemperatureF(this.mContext, this.mCanBusInfoInt[5]);
      } else if (SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0) == 1) {
         GeneralAirData.front_left_temperature = this.getAirTemperatureC(this.mContext, this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getAirTemperatureC(this.mContext, this.mCanBusInfoInt[5]);
      } else {
         GeneralAirData.front_left_temperature = this.getAirTemperatureF(this.mContext, this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getAirTemperatureF(this.mContext, this.mCanBusInfoInt[5]);
      }

      GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(var3);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(var3);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(var3);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(var3);
      if (this.isAirDataChange()) {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setAmplifierInfo0x31() {
      ArrayList var1 = new ArrayList();
      GeneralAmplifierData.frontRear = -DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) + 7;
      GeneralAmplifierData.leftRight = -(-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) + 7);
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 2;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_asl"), this.getAslState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_Vehicle_volume_follows_ASL", "_330_asl"), this.getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)))).setValueStr(true));
      this.updateAmplifierActivity((Bundle)null);
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarTypeInfo0x63() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_setting_info"), 0, this.getCarType(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorInfo0x24() {
      int var1 = this.mCanBusInfoInt[2];
      new ArrayList();
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1);
      this.updateDoorView(this.mContext);
   }

   private void setMediaSourceInfo0x62() {
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

   private void setMediaStatus0x61() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_1"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_2"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_3"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_4"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_5"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_6"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_status"), this.getDiscState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_currently_selected_disc"), this.getNowDisc(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_1"), this.getDiscTypoe(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_2"), this.getDiscTypoe(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_3"), this.getDiscTypoe(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_4"), this.getDiscTypoe(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_5"), this.getDiscTypoe(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_6"), this.getDiscTypoe(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Subtitle_language"), this.getDiscLanguage(this.mCanBusInfoInt[6])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Voice_language"), this.getDiscLanguage(this.mCanBusInfoInt[7])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_dvd_language"), this.getDiscLanguage(this.mCanBusInfoInt[8])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSystemInfo0x32() {
      int var1 = this.mCanBusInfoInt[2];
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_Original_power_amplifier_node"), this.getNullHaveState(DataHandleUtils.getIntFromByteWithBit(var1, 0, 1)))).setValueStr(true));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_amplifier_switch"), DataHandleUtils.getBoolBit6(var1)));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_Power_amplifier_mute"), DataHandleUtils.getBoolBit7(var1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackInfo0x29() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1;
         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            var1 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 4046, 3696, 16);
         } else {
            var1 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 380, 16);
         }

         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 7) {
                  if (var1 != 19) {
                     if (var1 == 20) {
                        this.buttonKey(46);
                     }
                  } else {
                     this.buttonKey(45);
                  }
               } else {
                  this.buttonKey(2);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
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

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 36) {
            if (var3 != 40) {
               if (var3 != 41) {
                  switch (var3) {
                     case 48:
                        this.setVersionInfo0x30();
                        break;
                     case 49:
                        this.setAmplifierInfo0x31();
                        break;
                     case 50:
                        this.setSystemInfo0x32();
                        break;
                     default:
                        switch (var3) {
                           case 96:
                              this.ControlMode0x60();
                              break;
                           case 97:
                              this.setMediaStatus0x61();
                              break;
                           case 98:
                              this.setMediaSourceInfo0x62();
                              break;
                           case 99:
                              this.setCarTypeInfo0x63();
                        }
                  }
               } else {
                  this.setTrackInfo0x29();
               }
            } else {
               this.setAirInfo0x28();
            }
         } else {
            this.setDoorInfo0x24();
         }
      } else {
         this.setWheelKey0x20();
      }

   }

   public void updateAirInfo() {
      int var1 = SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCanDifferentId() + "M" + this.getCurrentEachCanId() + "L" + this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_unit_selection") + "R" + 0, 0);
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
         var1 = this.data6RearLeft;
         if (var1 != 0) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureC(this.mContext, var1);
         }

         var1 = this.data8RearRight;
         if (var1 != 0) {
            GeneralAirData.rear_right_temperature = this.getAirTemperatureC(this.mContext, var1);
         }
      } else {
         var1 = this.data6RearLeft;
         if (var1 != 0) {
            GeneralAirData.rear_left_temperature = this.getAirTemperatureF(this.mContext, var1);
         }

         var1 = this.data8RearRight;
         if (var1 != 0) {
            GeneralAirData.rear_right_temperature = this.getAirTemperatureF(this.mContext, var1);
         }
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
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
