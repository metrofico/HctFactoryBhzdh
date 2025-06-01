package com.hzbhd.canbus.car._24;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static final String DEVICE_STATUS_AUX = "AUX";
   private static final String DEVICE_STATUS_CD = "CD";
   private static final String DEVICE_STATUS_POWER_OFF = "Power Off";
   private static final String DEVICE_STATUS_POWER_ON = "Power On";
   private static final String DEVICE_STATUS_RADIO = "RADIO";
   private static final String DEVICE_STATUS_TV = "TV";
   private int[] m0x10DataNow;
   private int[] mAmplifierData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private String mCdMessage;
   private Context mContext;
   private int mCurrentDisc;
   private String mDeviceStatusNow = "";
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private String mRadioMessage;
   private String mRunningState;
   private boolean mShowCdMessage;
   private boolean mShowRadioMessage;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private UiMgr mUiMgr;

   private void changeOriginalDevicePage(List var1, String[] var2, boolean var3) {
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet();
      var4.setItems(var1);
      var4.setRowTopBtnAction(var2);
      var4.setHaveSongList(var3);
      this.cleanDevice();
      this.updateOriginalDeviceWithInit();
   }

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
   }

   private void cleanDevice() {
      this.mRunningState = "";
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.folder = false;
      GeneralOriginalCarDeviceData.wma = false;
      GeneralOriginalCarDeviceData.mp3 = false;
      GeneralOriginalCarDeviceData.scane = false;
      GeneralOriginalCarDeviceData.rds = false;
      GeneralOriginalCarDeviceData.st = false;
      GeneralOriginalCarDeviceData.auto_p = false;
      GeneralOriginalCarDeviceData.mList = null;
      Iterator var1 = this.getOriginalCarDevicePageUiSet().getItems().iterator();

      while(var1.hasNext()) {
         ((OriginalCarDevicePageUiSet.Item)var1.next()).setValue("");
      }

   }

   private String getAmpType(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  return var1 != 5 ? "" : "Beep";
               } else {
                  return "Balance";
               }
            } else {
               return "Fade";
            }
         } else {
            return "Treble";
         }
      } else {
         return "Bass";
      }
   }

   private String getAmpValue(int var1, int var2) {
      String var4 = "";
      if (var1 == 5) {
         var1 = var2;
         if (var2 > 1) {
            var1 = 1;
         }

         if (var1 == 0) {
            return "OFF";
         } else {
            return var1 == 1 ? "ON" : "";
         }
      } else {
         int var3 = var2;
         if (var2 < 2) {
            var3 = 2;
         }

         var2 = var3 - 7;
         if (var2 < 0) {
            if (var1 == 1 || var1 == 2) {
               var4 = "-";
               return var4 + Math.abs(var2);
            }

            if (var1 != 3) {
               if (var1 == 4) {
                  var4 = "L";
               }

               return var4 + Math.abs(var2);
            }
         } else {
            if (var2 <= 0) {
               return "0";
            }

            if (var1 == 1 || var1 == 2) {
               var4 = "+";
               return var4 + Math.abs(var2);
            }

            if (var1 == 3) {
               var4 = "F";
               return var4 + Math.abs(var2);
            }

            if (var1 != 4) {
               return var4 + Math.abs(var2);
            }
         }

         var4 = "R";
         return var4 + Math.abs(var2);
      }
   }

   private String getBcdWithByte(int var1) {
      return Integer.toString(var1 >> 4) + Integer.toString(var1 & 15);
   }

   private String getCdWorkModle(int var1) {
      switch (var1) {
         case 0:
            return "Play";
         case 1:
            return "Reading disc";
         case 2:
            return "Loading disc";
         case 3:
            return "Insert disc";
         case 4:
            return "Busy";
         case 5:
            return "Ejecting disc";
         case 6:
            return "Select disc to load";
         case 7:
            return "Select disc to eject";
         case 8:
            return "Disc error";
         default:
            return "";
      }
   }

   private String getDeviceWorkModle(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? "" : "TV";
            } else {
               return "AUX";
            }
         } else {
            return "CD";
         }
      } else {
         return "RADIO";
      }
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet() {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr().getOriginalCarDevicePageUiSet(this.mContext);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private String getPlayStatus(int var1) {
      switch (var1) {
         case 0:
            return this.mContext.getResources().getString(2131767779);
         case 1:
            return this.mContext.getResources().getString(2131769490);
         case 2:
            return this.mContext.getResources().getString(2131769493);
         case 3:
            return this.mContext.getResources().getString(2131769489);
         case 4:
            return this.mContext.getResources().getString(2131767778);
         case 5:
            return this.mContext.getResources().getString(2131769766);
         case 6:
            return this.mContext.getResources().getString(2131769861);
         case 7:
            return this.mContext.getResources().getString(2131767766);
         default:
            return "";
      }
   }

   private String getPowerStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "Power On";
      } else {
         var2 = "Power Off";
      }

      return var2;
   }

   private String getRadioBand(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  return var1 != 5 ? "" : "FMAP";
               } else {
                  return "FM2";
               }
            } else {
               return "FM1";
            }
         } else {
            return "AMAP";
         }
      } else {
         return "AM";
      }
   }

   private String getRadioFreq(int var1, int var2, int var3) {
      int var6 = DataHandleUtils.getIntFromByteWithBit(var2, 7, 1);
      var2 = (var2 & 255 >> var6) * 256 + var3;
      String var7;
      if (var1 != 1 && var1 != 2) {
         if (var1 != 3 && var1 != 4 && var1 != 5) {
            var7 = "";
         } else {
            double var4 = (double)((float)(DataHandleUtils.rangeNumber(var2, 1, 409) - 1) * 0.05F) + 87.5;
            (new BigDecimal(var4)).setScale(2, 4).floatValue();
            var7 = (new DecimalFormat("0.00")).format(var4) + "MHz";
         }
      } else {
         var1 = DataHandleUtils.rangeNumber(var2, 1, 120);
         var7 = (var1 - 1) * (var6 + 9) + 531 - var6 + "KHz";
      }

      return var7;
   }

   private UiMgr getUiMgr() {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

      return this.mUiMgr;
   }

   private boolean is0x10DataChange() {
      if (Arrays.equals(this.m0x10DataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x10DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAmpDataChange() {
      if (Arrays.equals(this.mAmplifierData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mAmplifierData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDeviceStatusConform(String var1) {
      return "Power Off".equals(GeneralOriginalCarDeviceData.cdStatus) ? false : var1.equals(GeneralOriginalCarDeviceData.discStatus);
   }

   private boolean isDeviceStatusSame(String var1) {
      if (var1.equals(GeneralOriginalCarDeviceData.discStatus) && !var1.equals(this.mDeviceStatusNow)) {
         this.mDeviceStatusNow = var1;
         return true;
      } else {
         return false;
      }
   }

   private void realKeyClick4(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   private String resolveTemperature(int var1) {
      String var2;
      if (var1 >= 1 && var1 <= 29) {
         var2 = (float)(var1 + 35) / 2.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void setAirData0x02() {
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         GeneralAirData.front_left_temperature = this.resolveTemperature(this.mCanBusInfoInt[2]);
         GeneralAirData.front_right_temperature = this.resolveTemperature(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 3);
         this.cleanAllBlow();
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }

         GeneralAirData.rear = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])) {
            GeneralAirData.in_out_cycle = false;
         } else if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5])) {
            GeneralAirData.in_out_cycle = true;
         }

         GeneralAirData.auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAmplifierInfo0x09() {
      if (this.isAmpDataChange()) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
         String var2 = this.getAmpType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3));
         String var3 = this.getAmpValue(var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 5));
         var2 = var2 + "  " + var3;
         if (var1 != 0) {
            GeneralOriginalCarDeviceData.runningState = var2;
            this.updateOriginalCarDeviceActivity((Bundle)null);
            this.stopTimer();
            this.mTimerTask = new TimerTask(this) {
               final MsgMgr this$0;

               {
                  this.this$0 = var1;
               }

               public void run() {
                  GeneralOriginalCarDeviceData.runningState = this.this$0.mRunningState;
                  this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
                  this.this$0.stopTimer();
               }
            };
            this.startTimer();
         }
      }

   }

   private void setCdInfo0x07() {
      if (this.isDeviceStatusConform("CD")) {
         this.mCurrentDisc = this.mCanBusInfoInt[2];
         if (GeneralOriginalCarDeviceData.songList != null) {
            Iterator var2 = GeneralOriginalCarDeviceData.songList.iterator();

            while(var2.hasNext()) {
               SongListEntity var1 = (SongListEntity)var2.next();
               var1.setSelected(var1.getTitle().substring(var1.getTitle().length() - 1).equals(Integer.toString(this.mCurrentDisc)));
            }
         }

         ArrayList var3 = new ArrayList();
         var3.add(new OriginalCarDeviceUpdateEntity(1, "Disc " + this.mCurrentDisc));
         var3.add(new OriginalCarDeviceUpdateEntity(2, this.getBcdWithByte(this.mCanBusInfoInt[3])));
         var3.add(new OriginalCarDeviceUpdateEntity(3, this.getBcdWithByte(this.mCanBusInfoInt[4]) + ":" + this.getBcdWithByte(this.mCanBusInfoInt[5])));
         GeneralOriginalCarDeviceData.mList = var3;
         String var4 = this.getCdWorkModle(this.mCanBusInfoInt[6]);
         this.mRunningState = var4;
         GeneralOriginalCarDeviceData.runningState = var4;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setCdMessage0x08() {
      if (this.isDeviceStatusConform("CD")) {
         ArrayList var2 = new ArrayList();
         byte[] var1 = this.mCanBusInfoByte;
         this.mCdMessage = new String(Arrays.copyOfRange(var1, 2, var1.length));
         var2.add(new OriginalCarDeviceUpdateEntity(4, this.mCdMessage));
         GeneralOriginalCarDeviceData.mList = var2;
         if (this.mShowCdMessage) {
            this.updateOriginalCarDeviceActivity((Bundle)null);
         }
      }

   }

   private void setCdStatus0x06() {
      if (this.isDeviceStatusConform("CD")) {
         GeneralOriginalCarDeviceData.folder = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.wma = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.mp3 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mShowCdMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         ArrayList var4 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = false;
         var4.add(new OriginalCarDeviceUpdateEntity(0, this.getPlayStatus(DataHandleUtils.getIntFromByteWithBit(var1, 0, 3))));
         if (this.mShowCdMessage) {
            var4.add(new OriginalCarDeviceUpdateEntity(4, this.mCdMessage));
         } else {
            var4.add(new OriginalCarDeviceUpdateEntity(4, ""));
         }

         GeneralOriginalCarDeviceData.mList = var4;
         var4 = new ArrayList();
         SongListEntity var5 = new SongListEntity("Disc 1");
         boolean var2;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 2");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 3");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 4");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 4) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 5");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         if (this.mCurrentDisc == 5) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(var5.setSelected(var2));
         var5 = new SongListEntity("Disc 6");
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5 = var5.setEnable(var2);
         var2 = var3;
         if (this.mCurrentDisc == 6) {
            var2 = true;
         }

         var4.add(var5.setSelected(var2));
         GeneralOriginalCarDeviceData.songList = var4;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setOriginalAuxPage() {
      Log.i("ljq", "setOriginalAuxPage: ");
      this.exitAuxIn2();
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            CommUtil.showToast(this.this$0.mContext, CommUtil.getStrByResId(this.this$0.mContext, "_272_toast_text18"));
         }
      });
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, (String[])null, false);
   }

   private void setOriginalCarWorkModle0x10() {
      GeneralOriginalCarDeviceData.cdStatus = this.getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      GeneralOriginalCarDeviceData.discStatus = this.getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3));
      this.updateOriginalCarDeviceActivity((Bundle)null);
      if ("Power Off".equals(GeneralOriginalCarDeviceData.cdStatus)) {
         this.cleanDevice();
         this.updateOriginalCarDeviceActivity((Bundle)null);
      } else {
         if (this.isDeviceStatusSame("RADIO")) {
            this.showOriginalDevice();
            this.setOriginalRadioPage();
         } else if (this.isDeviceStatusSame("CD")) {
            this.showOriginalDevice();
            this.setOriginalCdPage();
         } else if (this.isDeviceStatusSame("AUX")) {
            this.setOriginalAuxPage();
         }

      }
   }

   private void setOriginalCdPage() {
      Log.i("ljq", "setOriginalCdPage: ");
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_artist", "_186_play_modle", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_disc", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_play_time", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, new String[]{"folder", "wma", "mp3", "scane"}, true);
   }

   private void setOriginalRadioPage() {
      Log.i("ljq", "setOriginalRadioPage: ");
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, new String[]{"rds", "scane", "st", "auto_p"}, false);
   }

   private void setRadioInfo0x04() {
      if (this.isDeviceStatusConform("RADIO")) {
         ArrayList var2 = new ArrayList();
         var2.add(new OriginalCarDeviceUpdateEntity(0, this.getRadioBand(this.mCanBusInfoInt[2])));
         var2.add(new OriginalCarDeviceUpdateEntity(1, "P" + this.mCanBusInfoInt[3]));
         int[] var1 = this.mCanBusInfoInt;
         var2.add(new OriginalCarDeviceUpdateEntity(2, this.getRadioFreq(var1[2], var1[4], var1[5])));
         GeneralOriginalCarDeviceData.mList = var2;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setRadioMessage0x05() {
      if (this.isDeviceStatusConform("RADIO")) {
         ArrayList var1 = new ArrayList();
         byte[] var2 = this.mCanBusInfoByte;
         this.mRadioMessage = new String(Arrays.copyOfRange(var2, 2, var2.length));
         var1.add(new OriginalCarDeviceUpdateEntity(3, this.mRadioMessage));
         GeneralOriginalCarDeviceData.mList = var1;
         if (this.mShowRadioMessage) {
            this.updateOriginalCarDeviceActivity((Bundle)null);
         }
      }

   }

   private void setRadioStatus0x03() {
      if (this.isDeviceStatusConform("RADIO")) {
         GeneralOriginalCarDeviceData.rds = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.auto_p = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mShowRadioMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         ArrayList var1 = new ArrayList();
         if (this.mShowRadioMessage) {
            var1.add(new OriginalCarDeviceUpdateEntity(3, this.mRadioMessage));
         } else {
            var1.add(new OriginalCarDeviceUpdateEntity(3, ""));
         }

         GeneralOriginalCarDeviceData.mList = var1;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setVolumeInfo0x0A() {
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1 = this.getBcdWithByte(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6));
      } else {
         var1 = "";
      }

      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDeviceUpdateEntity(this.getOriginalCarDevicePageUiSet().getItems().size() - 1, var1));
      GeneralOriginalCarDeviceData.mList = var2;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1) {
         if (var1 == 2) {
            this.realKeyClick4(46);
         }
      } else {
         this.realKeyClick4(45);
      }

   }

   private void showOriginalDevice() {
      Intent var1 = new Intent();
      var1.setComponent(Constant.OriginalDeviceActivity);
      var1.setFlags(268435456);
      this.mContext.startActivity(var1);
   }

   private void startTimer() {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, 1000L);
      }
   }

   private void stopTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

   }

   private void updateOriginalDeviceWithInit() {
      Bundle var1 = new Bundle();
      var1.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var16 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var16;
      this.mContext = var1;
      int var3 = var16[1];
      Exception var10000;
      boolean var10001;
      if (var3 != 16) {
         if (var3 != 17) {
            switch (var3) {
               case 2:
                  try {
                     this.setAirData0x02();
                     return;
                  } catch (Exception var12) {
                     var10000 = var12;
                     var10001 = false;
                     break;
                  }
               case 3:
                  try {
                     this.setRadioStatus0x03();
                     return;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                     break;
                  }
               case 4:
                  try {
                     this.setRadioInfo0x04();
                     return;
                  } catch (Exception var10) {
                     var10000 = var10;
                     var10001 = false;
                     break;
                  }
               case 5:
                  try {
                     this.setRadioMessage0x05();
                     return;
                  } catch (Exception var9) {
                     var10000 = var9;
                     var10001 = false;
                     break;
                  }
               case 6:
                  try {
                     this.setCdStatus0x06();
                     return;
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break;
                  }
               case 7:
                  try {
                     this.setCdInfo0x07();
                     return;
                  } catch (Exception var7) {
                     var10000 = var7;
                     var10001 = false;
                     break;
                  }
               case 8:
                  try {
                     this.setCdMessage0x08();
                     return;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                     break;
                  }
               case 9:
                  try {
                     this.setAmplifierInfo0x09();
                     return;
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                     break;
                  }
               case 10:
                  try {
                     this.setVolumeInfo0x0A();
                     return;
                  } catch (Exception var4) {
                     var10000 = var4;
                     var10001 = false;
                     break;
                  }
               default:
                  return;
            }
         } else {
            try {
               this.setWheelKey0x11();
               return;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
            }
         }
      } else {
         try {
            this.setOriginalCarWorkModle0x10();
            return;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
         }
      }

      Exception var15 = var10000;
      Log.e("CanBusError", var15.toString());
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }
}
