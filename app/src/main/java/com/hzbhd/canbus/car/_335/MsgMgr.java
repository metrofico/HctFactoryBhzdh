package com.hzbhd.canbus.car._335;

import android.content.Context;
import android.os.Bundle;
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
import com.hzbhd.canbus.util.SelectCanTypeUtil;
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
   private int AmTag = 1;
   public final String MEDIA_OTHERS = "OTHERS";
   public final String MEDIA_TYPE_AUX = "AUX";
   public final String MEDIA_TYPE_CD_DVD = "CD/DVD";
   public final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
   public final String MEDIA_TYPE_RADIO0 = "RADIO0";
   public final String MEDIA_TYPE_RADIO1 = "RADIO1";
   public final String MEDIA_USB = "USB";
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   private int mMediaType;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int radioMediaType;
   private int setUsbInfo0x21Data0 = 0;

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
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
                     return this.mContext.getString(2131763632);
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
                  return var1 != 80 ? 5 : 4;
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
         var3 = this.mContext.getString(2131763633);
      } else if (var1 == 2) {
         var3 = this.mContext.getString(2131763634);
      } else {
         var3 = this.mContext.getString(2131763635);
      }

      String var4;
      if (var2 == 1) {
         var4 = this.mContext.getString(2131763636);
      } else {
         var4 = this.mContext.getString(2131763637);
      }

      return var3 + "/" + var4;
   }

   private String getCdOrDvdSate1(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = this.mContext.getString(2131763633);
      } else if (var1 == 2) {
         var2 = this.mContext.getString(2131763634);
      } else {
         var2 = this.mContext.getString(2131763635);
      }

      return var2;
   }

   private String getCdOrDvdSate2(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = this.mContext.getString(2131763636);
      } else {
         var2 = this.mContext.getString(2131763637);
      }

      return var2;
   }

   private String getCdStatu(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? this.mContext.getString(2131763662) : "USB";
      } else {
         return "IPOD";
      }
   }

   private String getDiscState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     return var1 != 15 ? this.mContext.getString(2131763651) : this.mContext.getString(2131763650) + "(CODE:" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + ")";
                  } else {
                     return this.mContext.getString(2131763649);
                  }
               } else {
                  return this.mContext.getString(2131763648);
               }
            } else {
               return this.mContext.getString(2131763647);
            }
         } else {
            return this.mContext.getString(2131763646);
         }
      } else {
         return this.mContext.getString(2131763645);
      }
   }

   private String getDiscYesOrNo(boolean var1) {
      return var1 ? this.mContext.getString(2131763631) : this.mContext.getString(2131763642);
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
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
            return this.mContext.getString(2131763632);
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
      String var6 = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) + "";
      String var2 = this.getCdOrDvdSate(DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4), DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4));
      String var5 = this.getMsbLsbResult(var1[7], var1[6]) + "";
      String var4 = this.getMsbLsbResult(var1[9], var1[8]) + "";
      String var3 = var1[10] + ":" + var1[11] + ":" + var1[12];
      String var7 = var1[13] + ":" + var1[14] + ":" + var1[15];
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

      GeneralOriginalCarDeviceData.runningState = "CD/DVD运行正常";
      MyLog.temporaryTracking("CD/DVD运行正常");
      return var8;
   }

   private List getOriginalDeviceUsbUpdateEntityList() {
      StringBuilder var2 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      StringBuilder var6 = var2.append(this.getMsbLsbResult(var1[6], var1[7])).append("/");
      int[] var8 = this.mCanBusInfoInt;
      String var3 = var6.append(this.getMsbLsbResult(var8[8], var8[9])).toString();
      String var4 = this.df_2Integer.format((long)this.mCanBusInfoInt[4]) + ":" + this.df_2Integer.format((long)this.mCanBusInfoInt[5]);
      String var9 = this.mCanBusInfoInt[11] + "%";
      String var5 = this.mCanBusInfoInt[10] + "";
      ArrayList var7 = new ArrayList();
      if (var3 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(0, var3));
      }

      if (var4 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(1, var4));
      }

      if (var9 != null) {
         var7.add(new OriginalCarDeviceUpdateEntity(2, var9));
      }

      if (var5 != null && DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) != 1) {
         var7.add(new OriginalCarDeviceUpdateEntity(3, var5));
      }

      return var7;
   }

   private String getPresetStationNumber(int var1) {
      return var1 == 0 ? this.mContext.getString(2131763643) : this.mContext.getString(2131763644) + var1;
   }

   private String getRunningState(int var1) {
      switch (var1) {
         case 1:
            return "LOADING";
         case 2:
            return this.mContext.getString(2131763667);
         case 3:
            return this.mContext.getString(2131763668);
         case 4:
            return this.mContext.getString(2131763669);
         case 5:
            return this.mContext.getString(2131763670);
         case 6:
            return this.mContext.getString(2131763671);
         default:
            return this.mContext.getString(2131763666);
      }
   }

   private String getScanState(boolean var1) {
      return var1 ? this.mContext.getString(2131763640) : this.mContext.getString(2131763641);
   }

   private String getSoundType(int var1) {
      switch (var1) {
         case 1:
            return "BOSS";
         case 2:
            return "MID";
         case 3:
            return "TER";
         case 4:
            return "FAD";
         case 5:
            return "BAL";
         case 6:
            return "LOUD";
         case 7:
            return "ASL";
         default:
            return this.mContext.getString(2131763632);
      }
   }

   private String getStereoState(boolean var1) {
      return var1 ? this.mContext.getString(2131763638) : this.mContext.getString(2131763639);
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private int getUiModel() {
      if (!GeneralOriginalCarDeviceData.runningState.equals(this.mContext.getString(2131763666)) && !GeneralOriginalCarDeviceData.runningState.equals(this.mContext.getString(2131763667))) {
         return GeneralOriginalCarDeviceData.cdStatus.equals("USB") ? 8449 : 8451;
      } else {
         return 8450;
      }
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
            byte var3 = (byte)(GeneralAmplifierData.bandBass + 2);
            byte var7 = (byte)(GeneralAmplifierData.bandTreble + 2);
            byte var4 = (byte)(GeneralAmplifierData.bandMiddle + 2);
            byte var8 = (byte)GeneralAmplifierData.volume;
            this.iterator = Arrays.stream(new byte[][]{{22, -127, 1}, {22, -124, 8, 1}, {22, -124, 1, var5}, {22, -124, 2, var6}, {22, -124, 4, var3}, {22, -124, 5, var7}, {22, -124, 6, var4}, {22, -124, 7, var8}}).iterator();
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
      ArrayList var3 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data8", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data9", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data10", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data11", (String)null));
      ArrayList var7 = new ArrayList();
      var7.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data8", (String)null));
      var7.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data9", (String)null));
      var7.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data10", (String)null));
      ArrayList var5 = new ArrayList();
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_band", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", (String)null));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_frequency", (String)null));
      ArrayList var4 = new ArrayList();
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_band", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_1", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_2", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_3", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_4", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_5", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_6", (String)null));
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_currently_selected_disc", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_time", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", (String)null));
      SparseArray var6 = new SparseArray();
      this.mOriginalDeviceDataArray = var6;
      var6.put(8449, new OriginalDeviceData(var2, new String[]{"prev_disc", "play_pause", "next_disc"}));
      this.mOriginalDeviceDataArray.put(8451, new OriginalDeviceData(var7, new String[]{"prev_disc", "play_pause", "next_disc"}));
      this.mOriginalDeviceDataArray.put(8450, new OriginalDeviceData(var3, new String[0]));
      this.mOriginalDeviceDataArray.put(0, new OriginalDeviceData(var3, new String[0]));
      this.mOriginalDeviceDataArray.put(256, new OriginalDeviceData(var5, new String[]{"left", "up", "play_pause", "down", "right"}));
      this.mOriginalDeviceDataArray.put(257, new OriginalDeviceData(var4, new String[0]));
      this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(var1, new String[]{"prev_disc", "left", "up", "down", "right", "next_disc"}));
      this.mOriginalDeviceDataArray.put(3, new OriginalDeviceData(var3, new String[0]));
      this.mOriginalDeviceDataArray.put(255, new OriginalDeviceData(var3, new String[0]));
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
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

   private void set0x24BaseInfo() {
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x63ModelSetting() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_car_data0"), 0, this.getCarType(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x64WheelKey() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 32) {
                        this.realKeyLongClick1(this.mContext, 4, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 141, var2[3]);
                  }
               } else {
                  this.changeBandFm2();
               }
            } else {
               this.changeBandFm1();
            }
         } else {
            this.realKeyLongClick1(this.mContext, 77, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void set0x65RockerData(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 3) {
               if (var2 != 5) {
                  if (var2 != 7) {
                     switch (var2) {
                        case 16:
                           this.realKeyLongClick1(var1, 7, var3[3]);
                           break;
                        case 17:
                           this.realKeyLongClick1(var1, 8, var3[3]);
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
                           break;
                        case 23:
                           this.realKeyLongClick1(var1, 52, var3[3]);
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

   private void setAirInfo0x28() {
      int[] var1 = this.mCanBusInfoInt;
      var1[3] = this.blockBit(var1[3], 4);
      if (!this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
            GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 0.5, 17.5, "F", 0, 31);
            GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 0.5, 17.5, "F", 0, 31);
         } else {
            GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 0.5, 17.5, "C", 0, 31);
            GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 0.5, 17.5, "C", 0, 31);
         }

         GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAmplifierInfo0x31(Context var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 3, this.getAslState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 4, this.getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)))).setValueStr(true));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      GeneralAmplifierData.frontRear = -DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) + 7;
      GeneralAmplifierData.leftRight = -(-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) + 7);
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 2;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      MyLog.temporaryTracking(GeneralAmplifierData.volume + "");
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData(var1, this.getCanId());
   }

   private void setEPSInfo0x29() {
      if (this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 380, 12);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setMediaSourceInfo0x62() {
      ArrayList var3 = new ArrayList();
      if (this.mCanBusInfoInt.length >= 6) {
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_cd_data"), 0, this.getCdOrDvdSate1(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4)))).setValueStr(true));
      }

      if (this.mCanBusInfoInt.length >= 6) {
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_cd_data"), 1, this.getCdOrDvdSate2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)))).setValueStr(true));
      }

      this.updateGeneralSettingData(var3);
      Bundle var4 = null;
      this.updateSettingActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      int var1 = var6[2];
      OriginalDeviceData var7;
      if (var1 == 1) {
         if (var6[3] == 0) {
            var7 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(256);
         } else {
            var7 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(257);
         }
      } else {
         var7 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(var1);
      }

      GeneralOriginalCarDeviceData.mList = null;
      int[] var5 = this.mCanBusInfoInt;
      var1 = var5[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 255) {
                     mediaTag = "OTHERS";
                     GeneralOriginalCarDeviceData.cdStatus = "OTHERS";
                     GeneralOriginalCarDeviceData.runningState = "OTHERS";
                  }
               } else {
                  mediaTag = "AUX";
                  GeneralOriginalCarDeviceData.cdStatus = "AUX";
                  GeneralOriginalCarDeviceData.runningState = "AUX";
               }
            } else {
               mediaTag = "CD/DVD";
               GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
               GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanBusInfoInt);
            }
         } else if (var5[3] == 0) {
            mediaTag = "RADIO0";
            GeneralOriginalCarDeviceData.cdStatus = "RADIO0";
            GeneralOriginalCarDeviceData.mList = this.setNowFrequencyInfo(this.mCanBusInfoInt);
         } else {
            mediaTag = "RADIO1";
            GeneralOriginalCarDeviceData.cdStatus = "RADIO1";
            GeneralOriginalCarDeviceData.mList = this.setPresuppositionFrequencyInfo(this.mCanBusInfoInt);
         }
      } else {
         mediaTag = "MEDIA OFF";
         GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
         GeneralOriginalCarDeviceData.runningState = "MEDIA OFF";
      }

      var5 = this.mCanBusInfoInt;
      var1 = var5[2];
      OriginalCarDevicePageUiSet var8;
      if (var1 == 1) {
         int var2 = this.radioMediaType;
         var1 = var5[3];
         if (var2 != var1) {
            this.radioMediaType = var1;
            this.mMediaType = 9;
            var8 = this.getOriginalCarDevicePageUiSet(this.mContext);
            var8.setItems(var7.getItemList());
            var8.setRowBottomBtnAction(var7.getBottomBtns());
            var4 = new Bundle();
            var4.putBoolean("bundle_key_orinal_init_view", true);
         }
      } else {
         this.radioMediaType = 9;
         if (this.mMediaType != var1) {
            this.mMediaType = var1;
            var8 = this.getOriginalCarDevicePageUiSet(this.mContext);
            var8.setItems(var7.getItemList());
            var8.setRowBottomBtnAction(var7.getBottomBtns());
            var4 = new Bundle();
            var4.putBoolean("bundle_key_orinal_init_view", true);
         }
      }

      this.updateOriginalCarDeviceActivity(var4);
   }

   private void setMediaState0x61() {
      if (this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state") != -1) {
         ArrayList var1 = new ArrayList();
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_1"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_2"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_3"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_4"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_5"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_6"), this.getDiscYesOrNo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_status"), this.getDiscState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
         var1.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_currently_selected_disc"), this.getNowDisc(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private List setNowFrequencyInfo(int[] var1) {
      String var2;
      String var3;
      String var5;
      if (var1[4] == 16) {
         var2 = this.getStereoState(DataHandleUtils.getBoolBit7(var1[5])) + "/" + this.getScanState(DataHandleUtils.getBoolBit5(var1[5])) + "/" + this.getPresetStationNumber(DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4));
         var3 = this.getMsbLsbResult(var1[7], var1[6]) + "KHz";
         String var4 = "AM";
         var5 = var2;
         var2 = var4;
      } else {
         var2 = "FM" + var1[4];
         var3 = this.df_2Decimal.format((double)this.getMsbLsbResult(var1[7], var1[6]) * 0.01) + "MHz";
         var5 = null;
      }

      ArrayList var6 = new ArrayList();
      if (var2 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(0, var2));
      }

      if (var5 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(1, var5));
      }

      if (var3 != null) {
         var6.add(new OriginalCarDeviceUpdateEntity(2, var3));
      }

      GeneralOriginalCarDeviceData.runningState = "Radio运行正常";
      return var6;
   }

   private List setPresuppositionFrequencyInfo(int[] var1) {
      int var2 = var1[4];
      String var3;
      String var4;
      String var5;
      String var6;
      String var7;
      String var8;
      String var9;
      String var10;
      if (var2 != 16 && var2 != 17 && var2 != 18) {
         var3 = this.getAppointmentBand(var2);
         var7 = this.df_2Decimal.format((double)this.getMsbLsbResult(var1[6], var1[5]) * 0.01) + "MHz";
         var8 = this.df_2Decimal.format((double)this.getMsbLsbResult(var1[8], var1[7]) * 0.01) + "MHz";
         var9 = this.df_2Decimal.format((double)this.getMsbLsbResult(var1[10], var1[9]) * 0.01) + "MHz";
         var6 = this.df_2Decimal.format((double)this.getMsbLsbResult(var1[12], var1[11]) * 0.01) + "MHz";
         var5 = this.df_2Decimal.format((double)this.getMsbLsbResult(var1[14], var1[13]) * 0.01) + "MHz";
         var4 = this.df_2Decimal.format((double)this.getMsbLsbResult(var1[16], var1[15]) * 0.01) + "MHz";
         var10 = var9;
      } else {
         var3 = this.getAppointmentBand(var2);
         var7 = this.getMsbLsbResult(var1[6], var1[5]) + "KHz";
         var8 = this.getMsbLsbResult(var1[8], var1[7]) + "KHz";
         var9 = this.getMsbLsbResult(var1[10], var1[9]) + "KHz";
         var6 = this.getMsbLsbResult(var1[12], var1[11]) + "KHz";
         var5 = this.getMsbLsbResult(var1[14], var1[13]) + "KHz";
         var4 = this.getMsbLsbResult(var1[16], var1[15]) + "KHz";
         var10 = var9;
      }

      ArrayList var11 = new ArrayList();
      if (var3 != null) {
         var11.add(new OriginalCarDeviceUpdateEntity(0, var3));
      }

      if (var7 != null) {
         var11.add(new OriginalCarDeviceUpdateEntity(1, var7));
      }

      if (var8 != null) {
         var11.add(new OriginalCarDeviceUpdateEntity(2, var8));
      }

      if (var10 != null) {
         var11.add(new OriginalCarDeviceUpdateEntity(3, var10));
      }

      if (var6 != null) {
         var11.add(new OriginalCarDeviceUpdateEntity(4, var6));
      }

      if (var5 != null) {
         var11.add(new OriginalCarDeviceUpdateEntity(5, var5));
      }

      if (var4 != null) {
         var11.add(new OriginalCarDeviceUpdateEntity(6, var4));
      }

      GeneralOriginalCarDeviceData.runningState = "Radio运行正常";
      return var11;
   }

   private void setSoundEffectsInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_vol_data0"), 0, this.getSoundType(this.mCanBusInfoInt[2]))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSpeedInfo0x16() {
      ArrayList var2 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed");
      StringBuilder var3 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var2.add((new SettingUpdateEntity(var1, 0, var3.append(this.getMsbLsbResult(var4[3], var4[2])).append("km/h").toString())).setValueStr(true));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed"), 1, this.mCanBusInfoInt[4] * 100));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      int[] var5 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var5[3], var5[2]));
   }

   private void setSpeedInfo0x50() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed");
      StringBuilder var2 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add((new SettingUpdateEntity(var1, 2, var2.append(this.getMsbLsbResult(var4[3], var4[2])).append("km/h").toString())).setValueStr(true));
      var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed"), 3, this.mCanBusInfoInt[4] * 100));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSteeringWheelControlMode0x60() {
      ArrayList var1 = new ArrayList();
      if (this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_Mode_selection") != -1) {
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_Mode_selection"), 0, this.mCanBusInfoInt[2]));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSystemInfo0x32() {
      ArrayList var1 = new ArrayList();
      if (this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info") != -1) {
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
         var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 2, this.getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)))).setValueStr(true));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setUsbInfo0x21() {
      mediaTag = "USB";
      GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceUsbUpdateEntityList();
      Bundle var3 = null;
      this.updateOriginalCarDeviceActivity((Bundle)null);
      int var1 = this.setUsbInfo0x21Data0;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.setUsbInfo0x21Data0 = var2;
         GeneralOriginalCarDeviceData.runningState = this.getRunningState(DataHandleUtils.getIntFromByteWithBit(var2, 0, 4));
         GeneralOriginalCarDeviceData.cdStatus = this.getCdStatu(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
         OriginalDeviceData var4 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(this.getUiModel());
         OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(this.mContext);
         var5.setItems(var4.getItemList());
         var5.setRowBottomBtnAction(var4.getBottomBtns());
         var3 = new Bundle();
         var3.putBoolean("bundle_key_orinal_init_view", true);
      }

      this.updateOriginalCarDeviceActivity(var3);
   }

   private void setWheelKeyInfo0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 19) {
                  if (var1 != 20) {
                     switch (var1) {
                        case 7:
                           this.buttonKey(2);
                           break;
                        case 8:
                           this.buttonKey(3);
                        case 9:
                           this.buttonKey(467);
                           break;
                        case 10:
                           this.buttonKey(468);
                     }
                  } else {
                     this.buttonKey(46);
                  }
               } else {
                  this.buttonKey(45);
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
      if (var3 != 22) {
         if (var3 != 36) {
            if (var3 != 80) {
               if (var3 != 32) {
                  if (var3 != 33) {
                     if (var3 != 40) {
                        if (var3 != 41) {
                           switch (var3) {
                              case 48:
                                 this.set0x30VersionInfo();
                                 break;
                              case 49:
                                 this.setAmplifierInfo0x31(var1);
                                 break;
                              case 50:
                                 this.setSystemInfo0x32();
                                 break;
                              default:
                                 switch (var3) {
                                    case 96:
                                       if (this.eachId == 2) {
                                          return;
                                       }

                                       this.setSteeringWheelControlMode0x60();
                                       break;
                                    case 97:
                                       if (this.eachId == 2) {
                                          return;
                                       }

                                       this.setMediaState0x61();
                                       break;
                                    case 98:
                                       if (this.eachId == 2) {
                                          return;
                                       }

                                       this.setMediaSourceInfo0x62();
                                       break;
                                    case 99:
                                       this.set0x63ModelSetting();
                                       break;
                                    case 100:
                                       if (this.eachId == 2) {
                                          return;
                                       }

                                       this.set0x64WheelKey();
                                       break;
                                    case 101:
                                       if (this.eachId != 3) {
                                          return;
                                       }

                                       this.set0x65RockerData(this.mContext);
                                       break;
                                    case 102:
                                       if (this.eachId != 3) {
                                          return;
                                       }

                                       this.setSoundEffectsInfo();
                                 }
                           }
                        } else {
                           this.setEPSInfo0x29();
                        }
                     } else {
                        this.setAirInfo0x28();
                     }
                  } else {
                     if (this.eachId == 3) {
                        return;
                     }

                     this.setUsbInfo0x21();
                  }
               } else {
                  this.setWheelKeyInfo0x20();
               }
            } else {
               if (this.eachId != 2) {
                  return;
               }

               this.setSpeedInfo0x50();
            }
         } else {
            this.set0x24BaseInfo();
         }
      } else {
         if (this.eachId != 2) {
            return;
         }

         this.setSpeedInfo0x16();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalCarDevice();
      this.initAmplifier(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
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
