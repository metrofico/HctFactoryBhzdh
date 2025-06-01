package com.hzbhd.canbus.car._393;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   String Disc1Type = "□CD Disc";
   String Disc2Type = "□CD Disc";
   String Disc3Type = "□CD Disc";
   String Disc4Type = "□CD Disc";
   String Disc5Type = "□CD Disc";
   String Disc6Type = "□CD Disc";
   int cdcTag = -1;
   String cdcTextInfo = "Text information is empty";
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   String haveDisc1 = "○Have";
   String haveDisc2 = "○Have";
   String haveDisc3 = "○Have";
   String haveDisc4 = "○Have";
   String haveDisc5 = "○Have";
   String haveDisc6 = "○Have";
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
   int radioInfoTag = -1;
   int[] save0x84Data = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   int[] saveCdcIntArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   private String textContent = "Text information is empty";

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
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
   }

   private List get0x84List(int[] var1) {
      String var2;
      if (var1[5] == 0) {
         var2 = this.mContext.getString(2131765180);
      } else {
         var2 = var1[5] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDeviceUpdateEntity(0, var2));
      var3.add(new OriginalCarDeviceUpdateEntity(1, this.getSwitchState(DataHandleUtils.getBoolBit7(var1[7]))));
      var3.add(new OriginalCarDeviceUpdateEntity(2, this.getSwitchState(DataHandleUtils.getBoolBit6(var1[7]))));
      var3.add(new OriginalCarDeviceUpdateEntity(3, this.getSwitchState(DataHandleUtils.getBoolBit5(var1[7]))));
      var3.add(new OriginalCarDeviceUpdateEntity(4, this.getSwitchState(DataHandleUtils.getBoolBit4(var1[7]))));
      var3.add(new OriginalCarDeviceUpdateEntity(5, this.getTextState(DataHandleUtils.getBoolBit3(var1[7]))));
      return var3;
   }

   private List get0x86List(int[] var1) {
      this.haveDisc1 = this.getHaveState(DataHandleUtils.getBoolBit0(var1[3]));
      this.haveDisc2 = this.getHaveState(DataHandleUtils.getBoolBit1(var1[3]));
      this.haveDisc3 = this.getHaveState(DataHandleUtils.getBoolBit2(var1[3]));
      this.haveDisc4 = this.getHaveState(DataHandleUtils.getBoolBit3(var1[3]));
      this.haveDisc5 = this.getHaveState(DataHandleUtils.getBoolBit4(var1[3]));
      this.haveDisc6 = this.getHaveState(DataHandleUtils.getBoolBit5(var1[3]));
      this.Disc6Type = this.getCDType(DataHandleUtils.getBoolBit5(var1[4]));
      this.Disc5Type = this.getCDType(DataHandleUtils.getBoolBit4(var1[4]));
      this.Disc4Type = this.getCDType(DataHandleUtils.getBoolBit3(var1[4]));
      this.Disc3Type = this.getCDType(DataHandleUtils.getBoolBit2(var1[4]));
      this.Disc2Type = this.getCDType(DataHandleUtils.getBoolBit1(var1[4]));
      this.Disc1Type = this.getCDType(DataHandleUtils.getBoolBit0(var1[4]));
      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDeviceUpdateEntity(0, this.haveDisc1 + "    " + this.Disc1Type));
      var2.add(new OriginalCarDeviceUpdateEntity(1, this.haveDisc2 + "    " + this.Disc2Type));
      var2.add(new OriginalCarDeviceUpdateEntity(2, this.haveDisc3 + "    " + this.Disc3Type));
      var2.add(new OriginalCarDeviceUpdateEntity(3, this.haveDisc4 + "    " + this.Disc4Type));
      var2.add(new OriginalCarDeviceUpdateEntity(4, this.haveDisc5 + "    " + this.Disc5Type));
      var2.add(new OriginalCarDeviceUpdateEntity(5, this.haveDisc6 + "    " + this.Disc6Type));
      if (var1[5] == 255) {
         var2.add(new OriginalCarDeviceUpdateEntity(6, "Invalid"));
      } else {
         var2.add(new OriginalCarDeviceUpdateEntity(6, var1[5] + ""));
      }

      if (var1[6] != 255 && var1[7] != 255) {
         var2.add(new OriginalCarDeviceUpdateEntity(7, var1[6] + ":" + var1[7]));
      } else {
         var2.add(new OriginalCarDeviceUpdateEntity(7, "Invalid"));
      }

      var2.add(new OriginalCarDeviceUpdateEntity(8, this.getScanState(DataHandleUtils.getIntFromByteWithBit(var1[8], 6, 2))));
      var2.add(new OriginalCarDeviceUpdateEntity(9, this.getRepeatState(DataHandleUtils.getIntFromByteWithBit(var1[8], 4, 2))));
      var2.add(new OriginalCarDeviceUpdateEntity(10, this.getRandomState(DataHandleUtils.getIntFromByteWithBit(var1[8], 2, 2))));
      var2.add(new OriginalCarDeviceUpdateEntity(11, this.getFloderState(DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 2))));
      var2.add(new OriginalCarDeviceUpdateEntity(12, this.getCdcText(DataHandleUtils.getBoolBit7(var1[3]))));
      return var2;
   }

   private String getCDCState(int var1) {
      if (var1 != 15) {
         switch (var1) {
            case 1:
               return "Disc 1";
            case 2:
               return "Disc 2";
            case 3:
               return "Disc 3";
            case 4:
               return "Disc 4";
            case 5:
               return "Disc 5";
            case 6:
               return "Disc 6";
            default:
               return "NO MEDIA";
         }
      } else {
         return "Single CD";
      }
   }

   private String getCDType(boolean var1) {
      return var1 ? "☑Rom Disc" : "☑CD Disc";
   }

   private String getCdcText(boolean var1) {
      return var1 ? this.cdcTextInfo : "OFF";
   }

   private String getChangerState(int var1) {
      if (var1 != 0) {
         if (var1 != 2) {
            if (var1 != 6) {
               if (var1 != 12) {
                  if (var1 != 255) {
                     switch (var1) {
                        case 17:
                           return "Loading disc ";
                        case 18:
                           return "Insert disc";
                        case 19:
                           return "wait";
                        case 20:
                           return "Busy";
                        case 21:
                           return "Select disc to load";
                        case 22:
                           return "Select disc to eject";
                        case 23:
                           return "Disc Error";
                        default:
                           return "NO STATE";
                     }
                  } else {
                     return "Invalid";
                  }
               } else {
                  return "Eject";
               }
            } else {
               return "Stop";
            }
         } else {
            return "Play";
         }
      } else {
         return "reading disc";
      }
   }

   private String getFloderState(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? "OFF" : "RDM FLODER";
      } else {
         return "RPT FLODER";
      }
   }

   private String getHaveState(boolean var1) {
      return var1 ? "●Have" : "○Have";
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

   private String getRadioState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 6) {
                     return var1 != 7 ? "NO MEDIA" : "AMAP";
                  } else {
                     return "FMAP";
                  }
               } else {
                  return "FM2";
               }
            } else {
               return "FM1";
            }
         } else {
            return "AM";
         }
      } else {
         return "FM";
      }
   }

   private String getRandomState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? "OFF" : "All Disc Random";
         } else {
            return "Disc Random";
         }
      } else {
         return "Random";
      }
   }

   private String getRepeatState(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? "OFF" : "All Disc Repeat";
         } else {
            return "Disc Repeat";
         }
      } else {
         return "Track Repeat";
      }
   }

   private String getRunningState() {
      int var1 = this.mCanBusInfoInt[2];
      int[] var2;
      StringBuilder var3;
      if (var1 != 1 && var1 != 7) {
         var3 = new StringBuilder();
         DecimalFormat var4 = this.df_2Decimal;
         var2 = this.mCanBusInfoInt;
         return var3.append(var4.format((double)this.getMsbLsbResult(var2[4], var2[3]) * 0.01)).append("MHz").toString();
      } else {
         var3 = new StringBuilder();
         var2 = this.mCanBusInfoInt;
         return var3.append(this.getMsbLsbResult(var2[4], var2[3])).append("KHz").toString();
      }
   }

   private String getScanState(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? "OFF" : "Disc Scan";
      } else {
         return "Scan";
      }
   }

   private String getSwitchState(boolean var1) {
      return var1 ? "ON" : "OFF";
   }

   private String getTemperature(int var1) {
      if (var1 == 254) {
         return "LO";
      } else {
         return var1 == 255 ? "HI" : this.df_2Decimal.format((double)var1 * 0.5) + this.getTempUnitC(this.mContext);
      }
   }

   private String getTextState(boolean var1) {
      return var1 ? this.textContent : "OFF";
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

   private Object gteMediaType(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? " " : "AUX";
         } else {
            return "CD";
         }
      } else {
         return "Radio";
      }
   }

   private Object gteShowType(int var1) {
      switch (var1) {
         case 1:
            return "Volume";
         case 2:
            return "Balance";
         case 3:
            return "Fade";
         case 4:
            return "Bass";
         case 5:
            return "Treble";
         case 6:
            return "Beep";
         default:
            return " ";
      }
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
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio1", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio2", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio3", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio4", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio5", (String)null));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio6", (String)null));
      ArrayList var4 = new ArrayList();
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc1", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc2", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc3", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc4", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc5", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc6", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc7", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc8", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc9", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc10", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc11", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc12", (String)null));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc13", (String)null));
      SparseArray var3 = new SparseArray();
      this.mOriginalDeviceDataArray = var3;
      var3.put(0, new OriginalDeviceData(var1, new String[0]));
      this.mOriginalDeviceDataArray.put(132, new OriginalDeviceData(var2, new String[0]));
      this.mOriginalDeviceDataArray.put(134, new OriginalDeviceData(var4, new String[0]));
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
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

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
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

   private void set0x26CarInfo() {
      ArrayList var1 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      if (var2[2] == 4 && var2[3] == 10) {
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_car"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_car", "_393_car1"), 1));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x86CDCInfo(int[] var1) {
      if (this.cdcTag == -1) {
         this.startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
         this.cdcTag = 1;
      }

      this.saveCdcIntArray = var1;
      OriginalDeviceData var2 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(134);
      GeneralOriginalCarDeviceData.cdStatus = this.getCDCState(DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 4));
      GeneralOriginalCarDeviceData.runningState = this.getChangerState(var1[9]);
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.mList = this.get0x86List(var1);
      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var3.setItems(var2.getItemList());
      var3.setRowBottomBtnAction(var2.getBottomBtns());
      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private void set0xA8TextInfo() {
      if (this.cdcTag == -1) {
         this.startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
         this.cdcTag = 1;
      }

      int var1 = 2;

      String var2;
      for(var2 = "%"; var1 < this.mCanBusInfoInt.length; ++var1) {
         String var3 = var2;
         if (var1 != 2) {
            var3 = var2 + "%";
         }

         var2 = var3 + String.format("%02x", this.mCanBusInfoInt[var1]);
      }

      this.cdcTextInfo = this.getUTF8Result(var2);
      this.set0x86CDCInfo(this.saveCdcIntArray);
   }

   private void set0xA9MediaInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_media"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_media", "_393_media1"), this.getSwitchState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_media"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_media", "_393_media2"), this.gteMediaType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3)))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xF0VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setAir0x21() {
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 0) {
            if (var1 != 3) {
               if (var1 != 12) {
                  if (var1 != 5) {
                     if (var1 == 6) {
                        GeneralAirData.front_left_blow_foot = false;
                        GeneralAirData.front_left_blow_window = false;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_foot = false;
                        GeneralAirData.front_right_blow_window = false;
                        GeneralAirData.front_right_blow_head = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_right_blow_window = false;
                     GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_right_blow_head = false;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = false;
            }
         } else {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[9]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAmp0xA6() {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 5;
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 5;
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
      this.updateAmplifierActivity((Bundle)null);
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_apm"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_apm", "_393_apm1"), this.getSwitchState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])))).setValueStr(true));
      SettingUpdateEntity var3 = new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_apm"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_apm", "_393_apm2"), this.gteShowType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 3)));
      boolean var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 3) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var2.add(var3.setEnable(var1).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRadio0x84(int[] var1) {
      if (this.radioInfoTag == -1) {
         this.startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
         this.radioInfoTag = 1;
      }

      for(int var2 = 0; var2 < var1.length; ++var2) {
         this.save0x84Data[var2] = var1[var2];
      }

      OriginalDeviceData var3 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(132);
      GeneralOriginalCarDeviceData.cdStatus = this.getRadioState(var1[2]);
      GeneralOriginalCarDeviceData.runningState = this.getRunningState();
      GeneralOriginalCarDeviceData.mList = null;
      GeneralOriginalCarDeviceData.mList = this.get0x84List(var1);
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var4.setItems(var3.getItemList());
      var4.setRowBottomBtnAction(var3.getBottomBtns());
      Bundle var5 = new Bundle();
      var5.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var5);
   }

   private void setRadioTextInfo0xA7() {
      if (this.radioInfoTag == -1) {
         this.startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
         this.radioInfoTag = 1;
      }

      int var1 = 2;

      String var2;
      for(var2 = "%"; var1 < this.mCanBusInfoInt.length; ++var1) {
         String var3 = var2;
         if (var1 != 2) {
            var3 = var2 + "%";
         }

         var2 = var3 + String.format("%02x", this.mCanBusInfoInt[var1]);
      }

      this.textContent = this.getUTF8Result(var2);
      this.setRadio0x84(this.save0x84Data);
   }

   private void setSWC0x11() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 8) {
                  if (var1 != 9) {
                     if (var1 == 11) {
                        this.realKeyLongClick1(this.mContext, 2, var2[5]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46, var2[5]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45, var2[5]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[5]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[5]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[5]);
      }

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
      if (var3 != 17) {
         if (var3 != 38) {
            if (var3 != 49) {
               if (var3 != 132) {
                  if (var3 != 134) {
                     if (var3 != 240) {
                        switch (var3) {
                           case 166:
                              this.setAmp0xA6();
                              break;
                           case 167:
                              this.setRadioTextInfo0xA7();
                              break;
                           case 168:
                              this.set0xA8TextInfo();
                              break;
                           case 169:
                              this.set0xA9MediaInfo();
                        }
                     } else {
                        this.set0xF0VersionInfo();
                     }
                  } else {
                     this.set0x86CDCInfo(var4);
                  }
               } else {
                  this.setRadio0x84(var4);
               }
            } else {
               this.setAir0x21();
            }
         } else {
            this.set0x26CarInfo();
         }
      } else {
         this.setSWC0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).send0x24CarSelect(10, 4);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalCarDevice();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
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
