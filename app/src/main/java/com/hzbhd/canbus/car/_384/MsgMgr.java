package com.hzbhd.canbus.car._384;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   int cdc0x86Tag = -1;
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   String disc_1_have = "";
   String disc_1_select = "";
   String disc_1_type = "";
   String disc_2_have = "";
   String disc_2_select = "";
   String disc_2_type = "";
   String disc_3_have = "";
   String disc_3_select = "";
   String disc_3_type = "";
   String disc_4_have = "";
   String disc_4_select = "";
   String disc_4_type = "";
   String disc_5_have = "";
   String disc_5_select = "";
   String disc_5_type = "";
   String disc_66_select = "";
   String disc_6_have = "";
   String disc_6_select = "";
   String disc_6_type = "";
   int doorInfo = 0;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   int media0x83Tag = -1;
   int media0x84Tag = -1;
   int nowKnowValue = -1;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private List get0x83List() {
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var1 = "ON";
      } else {
         var1 = "OFF";
      }

      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDeviceUpdateEntity(0, var1));
      var2.add(new OriginalCarDeviceUpdateEntity(1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7) + " Lever"));
      return var2;
   }

   private List get0x84List() {
      int var1 = this.mCanBusInfoInt[2];
      int[] var4;
      String var8;
      if (var1 == 1) {
         StringBuilder var3 = new StringBuilder();
         var4 = this.mCanBusInfoInt;
         var8 = var3.append(this.getMsbLsbResult(var4[4], var4[3])).append("KHz").toString();
      } else if (var1 != 0 && var1 != 2 && var1 != 3) {
         var8 = "NO SUPPORT";
      } else {
         StringBuilder var5 = new StringBuilder();
         DecimalFormat var9 = this.df_2Decimal;
         var4 = this.mCanBusInfoInt;
         var8 = var5.append(var9.format((double)this.getMsbLsbResult(var4[4], var4[3]) * 0.01)).append("MHz").toString();
      }

      boolean var2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      String var11 = "ON";
      String var10;
      if (var2) {
         var10 = "ON";
      } else {
         var10 = "OFF";
      }

      if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
         var11 = "OFF";
      }

      String var6;
      if (this.mCanBusInfoInt[5] == 0) {
         var6 = "NO INFO";
      } else {
         var6 = this.mCanBusInfoInt[5] + "";
      }

      ArrayList var7 = new ArrayList();
      var7.add(new OriginalCarDeviceUpdateEntity(0, var8));
      var7.add(new OriginalCarDeviceUpdateEntity(1, var10));
      var7.add(new OriginalCarDeviceUpdateEntity(2, var11));
      var7.add(new OriginalCarDeviceUpdateEntity(3, var6));
      return var7;
   }

   private String getChangerState() {
      switch (this.mCanBusInfoInt[9]) {
         case 0:
            return "Reading TOC";
         case 1:
            return "PAUSE";
         case 2:
            return "Play";
         case 3:
            return "Fast";
         case 4:
            return "User Search";
         case 5:
            return "Intemal search";
         case 6:
            return "Stop";
         case 7:
            return "Rom read";
         case 8:
            return "Rom search";
         case 9:
            return "Retrieving";
         case 10:
            return "Disc changing(user)";
         case 11:
            return "Disc changing(Intemal)";
         case 12:
            return "Eject";
         default:
            return "Invalid";
      }
   }

   private boolean getCycleState(int var1) {
      return var1 == 2;
   }

   private String getDeviceState(int var1) {
      switch (var1) {
         case 0:
            return "Radio";
         case 1:
            return "CD";
         case 2:
            return "AUX/NAVI";
         case 3:
            return "USB";
         case 4:
            return "TEL";
         case 5:
            return "IPOD";
         case 6:
            return "TV";
         default:
            return "NO MEDIA";
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private String getPlayingModel() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      String var2 = "";
      if (var1) {
         var2 = "" + "【Scan ON】";
      }

      String var3 = var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
         var3 = var2 + "【Disc Scan ON】";
      }

      var2 = var3;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
         var2 = var3 + "【Repeat ON】";
      }

      var3 = var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
         var3 = var2 + "【Disc Repeat ON】";
      }

      var2 = var3;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8])) {
         var2 = var3 + "【Random ON】";
      }

      var3 = var2;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8])) {
         var3 = var2 + "【Disc Random ON】";
      }

      return var3;
   }

   private String getRadiaState() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 0) {
         return "FM";
      } else if (var1 == 1) {
         return "AM";
      } else if (var1 == 2) {
         return "FM1";
      } else if (var1 == 3) {
         return "FM2";
      } else if (var1 == 4) {
         return "MW";
      } else {
         return var1 == 5 ? "LW" : "NO STATE";
      }
   }

   private String getRadioRuningState() {
      switch (this.mCanBusInfoInt[7]) {
         case 1:
            return "SEEK+";
         case 2:
            return "SEEK-";
         case 3:
            return "AUTO SEEK";
         case 4:
            return "Tune+";
         case 5:
            return "Tune-";
         case 6:
            return "SCAN";
         default:
            return "Invalid";
      }
   }

   private String getRunningState() {
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1 = "Power ON";
      } else {
         var1 = "Power OFF";
      }

      String var2;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         var2 = "(Show Menu)";
      } else {
         var2 = "(No Menu)";
      }

      return var1 + var2;
   }

   private String getTemperature(int var1) {
      if (var1 == 1) {
         return "LO";
      } else if (var1 == 255) {
         return "HI";
      } else {
         return var1 == 0 ? "No Display" : (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      }
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initOriginalCarDevice() {
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

   private void initOriginalDeviceDataArray() {
      ArrayList var4 = new ArrayList();
      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data1", (String)null));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data2", (String)null));
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data3", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data4", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data5", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data6", (String)null));
      SparseArray var2 = new SparseArray();
      this.mOriginalDeviceDataArray = var2;
      var2.put(0, new OriginalDeviceData(var4, new String[0]));
      this.mOriginalDeviceDataArray.put(131, new OriginalDeviceData(var3, new String[0]));
      this.mOriginalDeviceDataArray.put(132, new OriginalDeviceData(var1, new String[0]));
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isNotDoorInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private String resolveRuntime(int var1) {
      DecimalFormat var2 = new DecimalFormat("00");
      return var1 / 60 + ":" + var2.format((long)(var1 % 60));
   }

   private void set0x1ADoor() {
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != this.doorInfo) {
         this.doorInfo = var1;
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x1ASpeed() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_driver_data");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_driver_data2");
      StringBuilder var5 = new StringBuilder();
      DecimalFormat var4 = this.df_2Decimal;
      int[] var6 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(var4.format((double)this.getMsbLsbResult(var6[5], var6[6]) * 0.1)).append("KM/H").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_driver_data");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_driver_data3");
      var5 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(this.getMsbLsbResult(var7[11], var7[12])).append("RPM").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x1ATrack() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x21PanelButton() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 32) {
               if (var1 != 36) {
                  if (var1 != 57) {
                     if (var1 != 66) {
                        if (var1 != 47) {
                           if (var1 == 48) {
                              this.realKeyLongClick1(this.mContext, 68, var2[3]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 151, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 4, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 134, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 53, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 128, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 1, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void set0x22PanelKnob() {
      if (this.nowKnowValue == -1) {
         this.nowKnowValue = this.mCanBusInfoInt[3];
      }

      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 == 1) {
         if (var2[3] < this.nowKnowValue) {
            this.realKeyClick4(this.mContext, 8);
         } else {
            this.realKeyClick4(this.mContext, 7);
         }
      } else if (var1 == 2) {
         if (var2[3] < this.nowKnowValue) {
            this.realKeyClick4(this.mContext, 45);
         } else {
            this.realKeyClick4(this.mContext, 46);
         }
      }

      this.nowKnowValue = this.mCanBusInfoInt[3];
   }

   private void set0x81SWC() {
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 != 12) {
                                 if (var1 == 32) {
                                    this.realKeyClick4(this.mContext, 58);
                                 }
                              } else {
                                 this.realKeyClick4(this.mContext, 2);
                              }
                           } else {
                              this.realKeyClick4(this.mContext, 46);
                           }
                        } else {
                           this.realKeyClick4(this.mContext, 45);
                        }
                     } else {
                        this.realKeyClick4(this.mContext, 15);
                     }
                  } else {
                     this.realKeyClick4(this.mContext, 14);
                  }
               } else {
                  this.realKeyClick4(this.mContext, 187);
               }
            } else {
               this.realKeyClick4(this.mContext, 8);
            }
         } else {
            this.realKeyClick4(this.mContext, 7);
         }
      } else {
         this.realKeyClick4(this.mContext, 0);
      }

   }

   private void set0x81Speed() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_driver_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_driver_data1"), this.mCanBusInfoInt[4] + "KM/H"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x81Track() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
         GeneralParkData.trackAngle = -DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) / 5;
      } else {
         GeneralParkData.trackAngle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) / 5;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x82HVACInfo() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = this.getCycleState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.swing = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[4]);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_right_blow_head = false;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_window = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
         }
      } else {
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
      }

      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
      GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void set0x83MediaType() {
      if (this.media0x83Tag == -1) {
         this.startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
         this.media0x83Tag = 1;
      }

      OriginalDeviceData var1 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(131);
      GeneralOriginalCarDeviceData.cdStatus = this.getDeviceState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
      GeneralOriginalCarDeviceData.runningState = this.getRunningState();
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.mList = this.get0x83List();
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var2.setItems(var1.getItemList());
      var2.setRowBottomBtnAction(var1.getBottomBtns());
      Bundle var3 = new Bundle();
      var3.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var3);
   }

   private void set0x84Tuner() {
      if (this.media0x84Tag == -1) {
         this.startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
         this.media0x84Tag = 1;
      }

      OriginalDeviceData var2 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(132);
      GeneralOriginalCarDeviceData.cdStatus = this.getRadiaState();
      GeneralOriginalCarDeviceData.runningState = this.getRadioRuningState();
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.mList = this.get0x84List();
      OriginalCarDevicePageUiSet var1 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var1.setItems(var2.getItemList());
      var1.setRowBottomBtnAction(var2.getBottomBtns());
      Bundle var3 = new Bundle();
      var3.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var3);
   }

   private void set0x86CDCInfo() {
      if (this.cdc0x86Tag == -1) {
         this.startDrivingDataActivity(this.mContext, 0);
         this.cdc0x86Tag = 1;
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 1) {
         this.disc_1_select = "☑select";
         this.disc_2_select = "□select";
         this.disc_3_select = "□select";
         this.disc_4_select = "□select";
         this.disc_5_select = "□select";
         this.disc_6_select = "□select";
         this.disc_66_select = "□select";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 2) {
         this.disc_1_select = "□select";
         this.disc_2_select = "☑select";
         this.disc_3_select = "□select";
         this.disc_4_select = "□select";
         this.disc_5_select = "□select";
         this.disc_6_select = "□select";
         this.disc_66_select = "□select";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 3) {
         this.disc_1_select = "□select";
         this.disc_2_select = "□select";
         this.disc_3_select = "☑select";
         this.disc_4_select = "□select";
         this.disc_5_select = "□select";
         this.disc_6_select = "□select";
         this.disc_66_select = "□select";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 4) {
         this.disc_1_select = "□select";
         this.disc_2_select = "□select";
         this.disc_3_select = "□select";
         this.disc_4_select = "☑select";
         this.disc_5_select = "□select";
         this.disc_6_select = "□select";
         this.disc_66_select = "□select";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 5) {
         this.disc_1_select = "□select";
         this.disc_2_select = "□select";
         this.disc_3_select = "□select";
         this.disc_4_select = "□select";
         this.disc_5_select = "☑select";
         this.disc_6_select = "□select";
         this.disc_66_select = "□select";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 6) {
         this.disc_1_select = "□select";
         this.disc_2_select = "□select";
         this.disc_3_select = "□select";
         this.disc_4_select = "□select";
         this.disc_5_select = "□select";
         this.disc_6_select = "☑select";
         this.disc_66_select = "□select";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 15) {
         this.disc_1_select = "□select";
         this.disc_2_select = "□select";
         this.disc_3_select = "□select";
         this.disc_4_select = "□select";
         this.disc_5_select = "□select";
         this.disc_6_select = "□select";
         this.disc_66_select = "☑select";
      } else {
         this.disc_1_select = "□select";
         this.disc_2_select = "□select";
         this.disc_3_select = "□select";
         this.disc_4_select = "□select";
         this.disc_5_select = "□select";
         this.disc_6_select = "□select";
         this.disc_66_select = "□select";
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         this.disc_6_have = "☑Have Disc";
      } else {
         this.disc_6_have = "□Have Disc";
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         this.disc_5_have = "☑Have Disc";
      } else {
         this.disc_5_have = "□Have Disc";
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         this.disc_4_have = "☑Have Disc";
      } else {
         this.disc_4_have = "□Have Disc";
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         this.disc_3_have = "☑Have Disc";
      } else {
         this.disc_3_have = "□Have Disc";
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         this.disc_2_have = "☑Have Disc";
      } else {
         this.disc_2_have = "□Have Disc";
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         this.disc_1_have = "☑Have Disc";
      } else {
         this.disc_1_have = "□Have Disc";
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
         this.disc_6_type = "☑Rom Disc";
      } else {
         this.disc_6_type = "☑CD Disc";
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
         this.disc_5_type = "☑Rom Disc";
      } else {
         this.disc_5_type = "☑CD Disc";
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
         this.disc_4_type = "☑Rom Disc";
      } else {
         this.disc_4_type = "☑CD Disc";
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
         this.disc_3_type = "☑Rom Disc";
      } else {
         this.disc_3_type = "☑CD Disc";
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
         this.disc_2_type = "☑Rom Disc";
      } else {
         this.disc_2_type = "☑CD Disc";
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
         this.disc_1_type = "☑Rom Disc";
      } else {
         this.disc_1_type = "☑CD Disc";
      }

      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC1"), this.disc_1_select + "          " + this.disc_1_have + "          " + this.disc_1_type));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC2"), this.disc_2_select + "          " + this.disc_2_have + "          " + this.disc_2_type));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC3"), this.disc_3_select + "          " + this.disc_3_have + "          " + this.disc_3_type));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC4"), this.disc_4_select + "          " + this.disc_4_have + "          " + this.disc_4_type));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC5"), this.disc_5_select + "          " + this.disc_5_have + "          " + this.disc_5_type));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC6"), this.disc_6_select + "          " + this.disc_6_have + "          " + this.disc_6_type));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC66"), this.disc_66_select));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC7"), this.mCanBusInfoInt[5] + ""));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC8"), this.df_2Integer.format((long)this.mCanBusInfoInt[6]) + ":" + this.df_2Integer.format((long)this.mCanBusInfoInt[7])));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC9"), this.getPlayingModel()));
      var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC10"), this.getChangerState()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0xF5VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setCarInfo() {
      String var4;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13])) {
         var4 = "mile";
      } else {
         var4 = "km";
      }

      int var1 = this.mCanBusInfoInt[12];
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            var3 = "L/100KM";
         } else {
            var3 = "KM/L";
         }
      } else {
         var3 = "MPG";
      }

      ArrayList var5 = new ArrayList();
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item1");
      StringBuilder var7 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var7.append((float)((var6[2] << 8) + var6[3]) / 10.0F).append(var3).toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item2");
      StringBuilder var10 = new StringBuilder();
      int[] var11 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var2, var1, var10.append((var11[4] << 8) + var11[5]).append(var4).toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item3");
      var7 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var2, var1, var7.append((float)((var6[6] << 8) + var6[7]) / 10.0F).append(var3).toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item4");
      int[] var8 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, this.resolveRuntime((var8[8] << 8) + var8[9])));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item5");
      StringBuilder var9 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var2, var1, var9.append((var6[10] << 8) + var6[11]).append("KM/h").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item6");
      var10 = new StringBuilder();
      var8 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(var1, var2, var10.append((float)((var8[16] << 8) + var8[17]) / 10.0F).append(var4).toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 19) {
         if (var3 != 26) {
            if (var3 != 49) {
               if (var3 != 134) {
                  if (var3 != 240) {
                     if (var3 != 33) {
                        if (var3 != 34) {
                           switch (var3) {
                              case 129:
                                 this.set0x81Speed();
                                 this.set0x81SWC();
                                 this.set0x81Track();
                                 break;
                              case 130:
                                 this.set0x82HVACInfo();
                                 break;
                              case 131:
                                 this.set0x83MediaType();
                                 break;
                              case 132:
                                 this.set0x84Tuner();
                           }
                        } else {
                           this.set0x22PanelKnob();
                        }
                     } else {
                        this.set0x21PanelButton();
                     }
                  } else {
                     this.set0xF5VersionInfo();
                  }
               } else {
                  this.set0x86CDCInfo();
               }
            } else {
               this.updateOutDoorTemp(this.mContext, (double)this.mCanBusInfoInt[13] * 0.5 - 40.0 + this.getTempUnitC(this.mContext));
            }
         } else {
            this.set0x1ADoor();
            this.set0x1ASpeed();
            this.set0x1ATrack();
            var4 = this.mCanBusInfoInt;
            this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[5], var4[6]));
         }
      } else {
         this.setCarInfo();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).sendMediaInfo0xF3(4);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalCarDevice();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (var2.equals("FM1")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xF3(2);
      } else if (var2.equals("FM2")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xF3(3);
      } else if (var2.equals("FM3")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xF3(3);
      } else if (var2.equals("AM1") || var2.equals("AM2")) {
         this.getUiMgr(this.mContext).sendMediaInfo0xF3(1);
      }

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
