package com.hzbhd.canbus.car._288;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String DEVICE_WORK_MODE_AUX = "AUX";
   static final String DEVICE_WORK_MODE_CD = "CD";
   static final String DEVICE_WORK_MODE_MEDIA_OFF = "Media Off";
   static final String DEVICE_WORK_MODE_RADIO = "Radio";
   static final String DEVICE_WORK_MODE_REAR_DISC = "Rear CD";
   private static final int SEND_NORMAL_MESSAGE = 2;
   static final int SHARE_288_AMPLIFIER_BAND_OFFSET = 2;
   static final int SHARE_288_AMPLIFIER_FADE_OFFSET = 7;
   private static boolean isOriginalCdFirst;
   private static boolean isOriginalDvdFirst;
   private static boolean m0x62First;
   private static boolean m0x65First;
   private String _0x61runningStatus = " ";
   int currentDisc;
   int currentPtore;
   private String isCdSongList = "";
   private boolean isOriginalCd = true;
   private byte[] m0x61InfoCopy;
   private byte[] m0x62InfoCopy;
   private byte[] m0x65InfoCopy;
   private int[] m0xD1Data;
   private int[] mCanBusInfo;
   private byte[] mCanBusInfoByte;
   private int mCanId;
   private Context mContext;
   private int mCurrentClick;
   private int mCurrentStatus;
   DecimalFormat mDecimalFormat = new DecimalFormat("00");
   private String mDeviceStatusNow;
   private Handler mHandler;
   private HandlerThread mHandlerThread;
   private Intent mIntent1;
   private Intent mIntent2;
   private int mLastDataCd = 0;
   private int mLastDataDvd = 0;
   private List mList1 = new ArrayList();
   private List mList2 = new ArrayList();
   private int outDoorTemp;

   private void changeOriginalDevicePage(List var1, String[] var2, String[] var3, boolean var4, boolean var5) {
      OriginalCarDevicePageUiSet var6 = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      if (var6 != null) {
         var6.setItems(var1);
         var6.setRowTopBtnAction(var2);
         var6.setRowBottomBtnAction(var3);
         var6.setHaveSongList(var4);
         var6.setHavePlayTimeSeekBar(var5);
         Bundle var7 = new Bundle();
         var7.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var7);
      }
   }

   private void cleanDevice() {
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.am_st = false;
      GeneralOriginalCarDeviceData.st = false;
      GeneralOriginalCarDeviceData.scan = false;
      GeneralOriginalCarDeviceData.disc_scan = false;
      GeneralOriginalCarDeviceData.rpt = false;
      GeneralOriginalCarDeviceData.rpt_disc = false;
      GeneralOriginalCarDeviceData.rdm_off = false;
      GeneralOriginalCarDeviceData.rdm_disc = false;
      GeneralOriginalCarDeviceData.startTime = "     ";
      GeneralOriginalCarDeviceData.endTime = "     ";
      GeneralOriginalCarDeviceData.progress = 0;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void cleanSongList() {
      if (GeneralOriginalCarDeviceData.songList != null) {
         GeneralOriginalCarDeviceData.songList.clear();
      }

   }

   private String discInfo(boolean var1, boolean var2) {
      if (var1) {
         String var3;
         if (var2) {
            var3 = "DVD";
         } else {
            var3 = "CD";
         }

         return var3;
      } else {
         return " ";
      }
   }

   private String discStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "Have Disk";
      } else {
         var2 = "No Disk";
      }

      return var2;
   }

   private String getChangerStatus(int var1) {
      return CommUtil.getStrByResId(this.mContext, "_288_divice_status_" + var1);
   }

   private String getDeviceWorkModle(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var2 = " ";
               } else {
                  var2 = "AUX";
               }
            } else {
               var2 = "CD";
            }
         } else {
            var2 = "Radio";
         }
      } else {
         var2 = "Media Off";
      }

      return var2;
   }

   private String getStringRes(int var1) {
      return this.mContext.getResources().getString(var1);
   }

   private void initAmplifierData(Context var1) {
      int var2 = 0;
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
         var2 = SharePreUtil.getIntValue(var1, "share_288_language", 0);
      }

      TimerUtil var3 = new TimerUtil();
      var3.startTimer(new TimerTask(this, var2, var3) {
         final Iterator iterator;
         final MsgMgr this$0;
         final int val$finalLanguage;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$finalLanguage = var2;
            this.val$util = var3;
            byte[] var12 = new byte[]{22, -127, 1, 1};
            byte var4 = (byte)var2;
            byte[] var11 = new byte[]{22, -124, 8, 1};
            byte var5 = (byte)(7 - GeneralAmplifierData.frontRear);
            byte var6 = (byte)(GeneralAmplifierData.leftRight + 7);
            byte var7 = (byte)(GeneralAmplifierData.bandBass + 2);
            byte var9 = (byte)(GeneralAmplifierData.bandTreble + 2);
            byte var10 = (byte)(GeneralAmplifierData.bandMiddle + 2);
            byte var8 = (byte)GeneralAmplifierData.volume;
            this.iterator = Arrays.stream(new byte[][]{{22, -127, 1, 1}, var12, {22, -125, 36, var4}, var11, {22, -124, 1, var5}, {22, -124, 2, var6}, {22, -124, 4, var7}, {22, -124, 5, var9}, {22, -124, 6, var10}, {22, -124, 7, var8}}).iterator();
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

   private void initHandler() {
      HandlerThread var1 = new HandlerThread("MyApplication");
      this.mHandlerThread = var1;
      var1.start();
      this.mHandler = new Handler(this, this.mHandlerThread.getLooper()) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 2) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   private boolean is0x61Repeat(byte[] var1) {
      if (Arrays.equals(var1, this.m0x61InfoCopy)) {
         return true;
      } else {
         this.m0x61InfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean is0x62Repeat(byte[] var1) {
      if (Arrays.equals(var1, this.m0x62InfoCopy)) {
         return true;
      } else {
         this.m0x62InfoCopy = Arrays.copyOf(var1, var1.length);
         if (m0x62First) {
            m0x62First = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean is0x65Repeat(byte[] var1) {
      if (Arrays.equals(var1, this.m0x65InfoCopy)) {
         return true;
      } else {
         this.m0x65InfoCopy = Arrays.copyOf(var1, var1.length);
         if (m0x65First) {
            m0x65First = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean is0xD1DataChange() {
      if (Arrays.equals(this.m0xD1Data, this.mCanBusInfo)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfo;
         this.m0xD1Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isCurrentStatus(String var1) {
      return var1.equals(GeneralOriginalCarDeviceData.cdStatus);
   }

   private boolean isDeviceStatusSame(String var1) {
      return this.isCurrentStatus(var1);
   }

   private boolean isOnlyAirData1Bit4Change() {
      Context var2 = this.mContext;
      boolean var1 = false;
      if (SharePreUtil.getBoolValue(var2, "_288_0x28_data1_bit4", false) != DataHandleUtils.getBoolBit4(this.mCanBusInfo[3])) {
         var1 = true;
      }

      return var1;
   }

   private boolean isOnlyOutDoorDataChange() {
      boolean var1;
      if (SharePreUtil.getFloatValue(this.mContext, "_288_out_door_temp", 0.0F) != (float)this.outDoorTemp) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isOriginalCdChange() {
      int var1 = this.mCurrentStatus;
      if (var1 == this.mLastDataCd) {
         return false;
      } else {
         this.mLastDataCd = var1;
         if (isOriginalCdFirst) {
            isOriginalCdFirst = false;
            return false;
         } else {
            return true;
         }
      }
   }

   private boolean isOriginalDvdChange() {
      int var1 = this.mCanBusInfo[3];
      if (var1 == this.mLastDataDvd) {
         return false;
      } else {
         this.mLastDataDvd = var1;
         if (isOriginalDvdFirst) {
            isOriginalDvdFirst = false;
            return false;
         } else {
            return true;
         }
      }
   }

   private static List mergeLists(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private void openAuxIn() {
      ComponentName var1 = new ComponentName("com.hzbhd.misc", "com.hzbhd.misc.auxin.MainActivity");
      Intent var2 = new Intent();
      var2.setComponent(var1);
      var2.setFlags(268435456);
      this.mContext.startActivity(var2);
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfo;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private int resolveAmpData(int var1, int var2) {
      return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[var1], var2, 4);
   }

   private String resolveOutDoorTem(int var1) {
      return (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
   }

   private String resolveTime(int var1, int var2, int var3) {
      String var4;
      if (var1 != 255 && var2 != 255 && var3 != 255) {
         var4 = this.mDecimalFormat.format((long)var1) + ":" + this.mDecimalFormat.format((long)var2) + ":" + this.mDecimalFormat.format((long)var3);
      } else {
         var4 = "--";
      }

      return var4;
   }

   private void sendDvdBoardCast() {
      Intent var1 = new Intent();
      this.mIntent2 = var1;
      var1.setAction("com.hzbhd.canbus.car._288.MsgMgr.dvd");
      Bundle var4 = new Bundle();
      var4.putString("_288_current_disc", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4) + "");
      var4.putString("_288_play_mode2", this.setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4)));
      var4.putString("_288_work_status2", this.setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 4, 4)));
      var4.putString("_288_run_status2", this._0x61runningStatus);
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfo;
      StringBuilder var5 = var3.append(this.setByteHighLow(var2[9], var2[8])).append("/");
      int[] var6 = this.mCanBusInfo;
      var4.putString("_288_track2", var5.append(this.setByteHighLow(var6[7], var6[6])).toString());
      var3 = new StringBuilder();
      var2 = this.mCanBusInfo;
      var3 = var3.append(this.resolveTime(var2[13], var2[14], var2[15])).append("/");
      var2 = this.mCanBusInfo;
      var4.putString("_288_play_time2", var3.append(this.resolveTime(var2[10], var2[11], var2[12])).toString());
      this.mIntent2.putExtras(var4);
      this.mContext.sendBroadcast(this.mIntent2);
   }

   private void sendNormalMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = Message.obtain();
         var4.what = 2;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void sendRearDvdBoardCast() {
      Intent var1 = new Intent();
      this.mIntent1 = var1;
      var1.setAction("com.hzbhd.canbus.car._288.MsgMgr.rear_dvd");
      Bundle var4 = new Bundle();
      var4.putString("_288_play_mode1", this.setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4)));
      var4.putString("_288_work_status1", this.setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 4, 4)));
      var4.putString("_288_run_status1", this.getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4)));
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfo;
      var2 = var2.append(this.setByteHighLow(var3[8], var3[7])).append("/");
      var3 = this.mCanBusInfo;
      var4.putString("_288_track1", var2.append(this.setByteHighLow(var3[6], var3[5])).toString());
      StringBuilder var6 = new StringBuilder();
      int[] var5 = this.mCanBusInfo;
      var2 = var6.append(this.resolveTime(var5[12], var5[13], var5[14])).append("/");
      var3 = this.mCanBusInfo;
      var4.putString("_288_play_time1", var2.append(this.resolveTime(var3[9], var3[10], var3[11])).toString());
      this.mIntent1.putExtras(var4);
      this.mContext.sendBroadcast(this.mIntent1);
   }

   private void setAir() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfo[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfo[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfo[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfo[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfo[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfo[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfo[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfo[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfo[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfo[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfo[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfo[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 0, 4);
      GeneralAirData.front_left_temperature = this.setLeftRightTemp(this.mCanBusInfo[4]);
      GeneralAirData.front_right_temperature = this.setLeftRightTemp(this.mCanBusInfo[5]);
      GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfo[6]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfo[6]);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfo[6]);
      this.outDoorTemp = this.mCanBusInfo[7];
      if (this.isOnlyOutDoorDataChange()) {
         SharePreUtil.setFloatValue(this.mContext, "_288_out_door_temp", (float)this.outDoorTemp);
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem(this.outDoorTemp));
      } else if (this.getCurrentEachCanId() == 64) {
         if (DataHandleUtils.getBoolBit4(this.mCanBusInfo[3])) {
            this.updateAirActivity(this.mContext, 1001);
         }
      } else if (this.isOnlyAirData1Bit4Change()) {
         SharePreUtil.setBoolValue(this.mContext, "_288_0x28_data1_bit4", DataHandleUtils.getBoolBit4(this.mCanBusInfo[3]));
      } else {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setAmplifier() {
      GeneralAmplifierData.frontRear = 7 - this.resolveAmpData(2, 4);
      GeneralAmplifierData.leftRight = this.resolveAmpData(2, 0) - 7;
      GeneralAmplifierData.bandBass = this.resolveAmpData(3, 4) - 2;
      GeneralAmplifierData.bandTreble = this.resolveAmpData(3, 0) - 2;
      GeneralAmplifierData.bandMiddle = this.resolveAmpData(4, 4) - 2;
      GeneralAmplifierData.volume = this.mCanBusInfo[5];
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData(this.mContext, this.mCanId);
      SharePreUtil.setStringValue(this.mContext, "_288_0x31", Base64.encodeToString(this.mCanBusInfoByte, 0));
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4)));
      String var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[6], 0, 1) == 1) {
         var1 = "_288_0x31_setting7";
      } else {
         var1 = "_288_0x31_setting8";
      }

      var2.add(new SettingUpdateEntity(0, 1, var1));
      var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[6], 4, 2)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private String setBand(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 16) {
               var2 = "";
            } else {
               var2 = "AM";
            }
         } else {
            var2 = "FM2";
         }
      } else {
         var2 = "FM1";
      }

      return var2;
   }

   private String setByteHighLow(int var1, int var2) {
      var1 = var1 * 256 + var2;
      String var3;
      if (var1 == 255) {
         var3 = "--";
      } else {
         var3 = var1 + "";
      }

      return var3;
   }

   private void setCdInfo() {
      ArrayList var4 = new ArrayList();
      this.currentDisc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4);
      var4.add(new OriginalCarDeviceUpdateEntity(0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4) + ""));
      var4.add(new OriginalCarDeviceUpdateEntity(1, this.setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4))));
      var4.add(new OriginalCarDeviceUpdateEntity(2, this.setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 4, 4))));
      int[] var5 = this.mCanBusInfo;
      var4.add(new OriginalCarDeviceUpdateEntity(3, this.setByteHighLow(var5[7], var5[6])));
      var5 = this.mCanBusInfo;
      var4.add(new OriginalCarDeviceUpdateEntity(4, this.setByteHighLow(var5[9], var5[8])));
      var5 = this.mCanBusInfo;
      GeneralOriginalCarDeviceData.endTime = this.resolveTime(var5[10], var5[11], var5[12]);
      var5 = this.mCanBusInfo;
      GeneralOriginalCarDeviceData.startTime = this.resolveTime(var5[13], var5[14], var5[15]);
      var5 = this.mCanBusInfo;
      int var2 = var5[10];
      int var3 = var5[11];
      int var1 = var5[12];
      if (var2 * 3600 + var3 * 60 + var1 != 0) {
         GeneralOriginalCarDeviceData.progress = (var5[13] * 3600 + var5[14] * 60 + var5[15]) * 100 / (var2 * 3600 + var3 * 60 + var1);
      }

      this.mList2.clear();
      this.mList2.addAll(var4);
      GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private String setCycleStatus(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = this.mContext.getResources().getString(2131761251);
            }
         } else {
            var2 = this.mContext.getResources().getString(2131761250);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131761249);
      }

      return var2;
   }

   private void setDiscInfo() {
      GeneralOriginalCarDeviceData.cdStatus = this.getDeviceWorkModle(this.mCanBusInfo[2]);
      if (!GeneralOriginalCarDeviceData.cdStatus.equals(this.mDeviceStatusNow)) {
         this.mDeviceStatusNow = GeneralOriginalCarDeviceData.cdStatus;
         this.cleanDevice();
         this.cleanSongList();
         this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      }

      if (this.isDeviceStatusSame("CD")) {
         if (!this.isOriginalCd && this.mCurrentClick != 0) {
            if (this.isOriginalCdChange()) {
               this.openAuxIn();
            }

            this.sendDvdBoardCast();
            Log.d("cwh", "0x62 dvd");
         } else {
            this.setOriginalCdPage();
            this.setCdInfo();
            Log.d("cwh", "0x62 cd");
         }
      }

      if (this.isDeviceStatusSame("Radio")) {
         this.setOriginalRadioPage();
         this.setRadioInfo();
      }

      if (!"Radio".equals(GeneralOriginalCarDeviceData.cdStatus) && !"CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
         this.setOriginalOtherPage();
      }

   }

   private void setDiscStatus() {
      this.mCurrentClick = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4);
      this.mCurrentStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4);
      boolean var7 = DataHandleUtils.getBoolBit0(this.mCanBusInfo[5]);
      boolean var6 = DataHandleUtils.getBoolBit1(this.mCanBusInfo[5]);
      boolean var4 = DataHandleUtils.getBoolBit2(this.mCanBusInfo[5]);
      boolean var2 = DataHandleUtils.getBoolBit3(this.mCanBusInfo[5]);
      boolean var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfo[5]);
      boolean var5 = DataHandleUtils.getBoolBit5(this.mCanBusInfo[5]);
      int var1 = this.mCurrentClick;
      if ((var1 != 1 || var7) && (var1 != 2 || var6) && (var1 != 3 || var4) && (var1 != 4 || var2) && (var1 != 5 || var3) && (var1 != 6 || var5)) {
         var2 = false;
      } else {
         var2 = true;
      }

      this.isOriginalCd = var2;
      String var12 = this.discStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfo[2]));
      String var14 = this.discStatus(DataHandleUtils.getBoolBit1(this.mCanBusInfo[2]));
      String var9 = this.discStatus(DataHandleUtils.getBoolBit2(this.mCanBusInfo[2]));
      String var15 = this.discStatus(DataHandleUtils.getBoolBit3(this.mCanBusInfo[2]));
      String var11 = this.discStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfo[2]));
      String var10 = this.discStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfo[2]));
      String var13 = this.discInfo(DataHandleUtils.getBoolBit0(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit0(this.mCanBusInfo[5]));
      this.discInfo(DataHandleUtils.getBoolBit1(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit1(this.mCanBusInfo[5]));
      this.discInfo(DataHandleUtils.getBoolBit2(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit2(this.mCanBusInfo[5]));
      this.discInfo(DataHandleUtils.getBoolBit3(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit3(this.mCanBusInfo[5]));
      this.discInfo(DataHandleUtils.getBoolBit4(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit4(this.mCanBusInfo[5]));
      this.discInfo(DataHandleUtils.getBoolBit5(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit5(this.mCanBusInfo[5]));
      ArrayList var8 = new ArrayList();
      var8.add(new SongListEntity("1. " + var12));
      var8.add(new SongListEntity("2. " + var14));
      var8.add(new SongListEntity("3. " + var9));
      var8.add(new SongListEntity("4. " + var15));
      var8.add(new SongListEntity("5. " + var11));
      var8.add(new SongListEntity("6. " + var10));
      GeneralOriginalCarDeviceData.runningState = this.getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4));
      this._0x61runningStatus = GeneralOriginalCarDeviceData.runningState;
      GeneralOriginalCarDeviceData.isSongListChange = this.isCdSongList.equals(var12 + "              " + var13) ^ true;
      this.isCdSongList.equals(var12 + "              " + var13);
      GeneralOriginalCarDeviceData.songList = var8;
      this.updateOriginalCarDeviceActivity((Bundle)null);
      var8 = new ArrayList();
      var8.add(new OriginalCarDeviceUpdateEntity(5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 0, 4) + ""));
      this.mList1.clear();
      this.mList1.addAll(var8);
      GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1);
      this.updateOriginalCarDeviceActivity((Bundle)null);
      if (!this.isOriginalCd && this.mCurrentClick != 0) {
         Log.d("cwh", "dvd界面发广播");
         this.sendDvdBoardCast();
      }

   }

   private String setFreq(int var1, int var2) {
      String var3;
      if (this.mCanBusInfo[4] == 16) {
         var3 = var1 + var2 * 256 + "KHz";
      } else {
         var3 = (float)(var1 + var2 * 256) / 100.0F + "MHz";
      }

      return var3;
   }

   private String setLeftRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 31) {
         var2 = "HI";
      } else if (var1 >= 1 && var1 <= 29) {
         StringBuilder var3 = (new StringBuilder()).append((float)(var1 + 35) * 0.5F);
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfo[6])) {
            var2 = "°F";
         } else {
            var2 = "℃";
         }

         var2 = var3.append(var2).toString();
      } else {
         var2 = "--";
      }

      return var2;
   }

   private void setOriginalCdPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_disc", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
      this.changeOriginalDevicePage(var1, new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux", "rpt", "rdm", "scan"}, new String[]{"prev_disc", "left", "up", "down", "right", "next_disc"}, true, true);
   }

   private void setOriginalOtherPage() {
      GeneralOriginalCarDeviceData.mList = null;
      this.changeOriginalDevicePage(new ArrayList(), new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux"}, new String[0], false, false);
   }

   private void setOriginalRadioPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
      this.changeOriginalDevicePage(var1, new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux"}, new String[]{"up", "left", "radio_scan", "right", "down"}, true, false);
   }

   private String setPlayStatus(int var1) {
      Resources var2;
      if (var1 == 1) {
         var2 = this.mContext.getResources();
         var1 = 2131761248;
      } else {
         var2 = this.mContext.getResources();
         var1 = 2131761247;
      }

      return var2.getString(var1);
   }

   private String setPreset(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "";
      } else {
         var2 = "P" + var1;
      }

      return var2;
   }

   private void setPresetData() {
      ArrayList var4;
      label28: {
         var4 = new ArrayList();
         int var3 = this.mCanBusInfo[4];
         int var2 = 2;
         int var1 = var2;
         int[] var5;
         if (var3 != 0) {
            var1 = var2;
            if (var3 != 1) {
               var1 = var2;
               if (var3 != 2) {
                  var1 = var2;
                  switch (var3) {
                     case 16:
                     case 17:
                     case 18:
                        while(var1 < 8) {
                           var5 = this.mCanBusInfo;
                           var3 = var1 * 2;
                           var2 = var5[var3 + 2];
                           var3 = var5[var3 + 1];
                           var4.add(new SongListEntity(var1 - 1 + ". " + (var2 * 256 + var3) + "KHz"));
                           ++var1;
                        }
                     default:
                        break label28;
                  }
               }
            }
         }

         while(var1 < 8) {
            var5 = this.mCanBusInfo;
            var3 = var1 * 2;
            var2 = var5[var3 + 2];
            var3 = var5[var3 + 1];
            var4.add(new SongListEntity(var1 - 1 + ". " + (float)(var2 * 256 + var3) / 100.0F + "MHz"));
            ++var1;
         }
      }

      GeneralOriginalCarDeviceData.songList = var4;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setRadioInfo() {
      if (this.mCanBusInfo[3] == 0) {
         ArrayList var2 = new ArrayList();
         var2.add(new OriginalCarDeviceUpdateEntity(0, this.setBand(this.mCanBusInfo[4])));
         var2.add(new OriginalCarDeviceUpdateEntity(1, this.setState(DataHandleUtils.getBoolBit7(this.mCanBusInfo[5]))));
         var2.add(new OriginalCarDeviceUpdateEntity(2, this.setPreset(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4))));
         int[] var1 = this.mCanBusInfo;
         var2.add(new OriginalCarDeviceUpdateEntity(3, this.setFreq(var1[6], var1[7])));
         this.currentPtore = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4);
         String var3;
         if (DataHandleUtils.getBoolBit5(this.mCanBusInfo[5])) {
            var3 = this.mContext.getResources().getString(2131768339);
         } else {
            var3 = this.mContext.getResources().getString(2131768338);
         }

         GeneralOriginalCarDeviceData.runningState = var3;
         GeneralOriginalCarDeviceData.mList = var2;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      } else {
         this.setPresetData();
      }

   }

   private void setRearDiscInfo() {
      this.turnToRearCdPage();
      GeneralOriginalCarDeviceData.cdStatus = "Rear CD";
      GeneralOriginalCarDeviceData.runningState = this.getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4));
      ArrayList var4 = new ArrayList();
      this.currentDisc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4);
      var4.add(new OriginalCarDeviceUpdateEntity(0, this.setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4))));
      var4.add(new OriginalCarDeviceUpdateEntity(1, this.setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 4, 4))));
      int[] var5 = this.mCanBusInfo;
      var4.add(new OriginalCarDeviceUpdateEntity(2, this.setByteHighLow(var5[6], var5[5])));
      var5 = this.mCanBusInfo;
      var4.add(new OriginalCarDeviceUpdateEntity(3, this.setByteHighLow(var5[8], var5[7])));
      var4.add(new OriginalCarDeviceUpdateEntity(4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 0, 4) + ""));
      var5 = this.mCanBusInfo;
      GeneralOriginalCarDeviceData.endTime = this.resolveTime(var5[9], var5[10], var5[11]);
      var5 = this.mCanBusInfo;
      GeneralOriginalCarDeviceData.startTime = this.resolveTime(var5[12], var5[13], var5[14]);
      var5 = this.mCanBusInfo;
      int var3 = var5[9];
      int var1 = var5[10];
      int var2 = var5[11];
      if (var3 * 3600 + var1 * 60 + var2 != 0) {
         GeneralOriginalCarDeviceData.progress = (var5[12] * 3600 + var5[13] * 60 + var5[14]) * 100 / (var3 * 3600 + var1 * 60 + var2);
      }

      GeneralOriginalCarDeviceData.mList = var4;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private String setState(boolean var1) {
      int var2;
      Resources var3;
      if (var1) {
         var3 = this.mContext.getResources();
         var2 = 2131770004;
      } else {
         var3 = this.mContext.getResources();
         var2 = 2131761252;
      }

      return var3.getString(var2);
   }

   private void setSwc() {
      int var1 = this.mCanBusInfo[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 19) {
                  if (var1 != 20) {
                     switch (var1) {
                        case 7:
                           this.realKeyClick(2);
                           break;
                        case 8:
                           this.realKeyClick(187);
                           break;
                        case 9:
                           this.realKeyClick(14);
                           break;
                        case 10:
                           this.realKeyClick(15);
                     }
                  } else {
                     this.realKeyClick(46);
                  }
               } else {
                  this.realKeyClick(45);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfo = var4;
      int var3 = var4[1];
      if (var3 != 22) {
         if (var3 != 32) {
            if (var3 != 40) {
               if (var3 != 101) {
                  if (var3 != 48) {
                     if (var3 != 49) {
                        if (var3 != 97) {
                           if (var3 == 98) {
                              if (this.is0x62Repeat(var2)) {
                                 return;
                              }

                              this.setDiscInfo();
                           }
                        } else {
                           if (this.is0x61Repeat(var2)) {
                              return;
                           }

                           this.setDiscStatus();
                        }
                     } else {
                        this.setAmplifier();
                     }
                  } else {
                     this.updateVersionInfo(this.mContext, this.getVersionStr(var2));
                  }
               } else if (DataHandleUtils.getBoolBit0(var4[2])) {
                  if (!DataHandleUtils.getBoolBit1(this.mCanBusInfo[2])) {
                     if (this.is0x65Repeat(var2)) {
                        return;
                     }

                     this.setRearDiscInfo();
                  } else {
                     if (this.isOriginalDvdChange()) {
                        this.openAuxIn();
                     }

                     this.sendRearDvdBoardCast();
                  }
               }
            } else {
               if (this.isAirMsgRepeat(this.mCanBusInfoByte)) {
                  return;
               }

               this.setAir();
            }
         } else {
            this.setSwc();
         }
      } else {
         this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[3], var4[2]));
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.initHandler();
      this.initAmplifierData(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)this.getCurrentEachCanId()});
      this.updateAmplifier();
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void turnToRearCdPage() {
      this.cleanDevice();
      this.cleanSongList();
      this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
      this.changeOriginalDevicePage(var1, new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux", "rpt", "rdm", "scan"}, new String[]{"prev_disc", "left", "up", "down", "right", "next_disc"}, false, true);
   }

   void updateAmplifier() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 3, SharePreUtil.getIntValue(this.mContext, "_288_amplifier_on_off", 0)));
      var1.add(new SettingUpdateEntity(0, 4, SharePreUtil.getIntValue(this.mContext, "_288_amplifier_mute", 0)));
      var1.add(new SettingUpdateEntity(0, 5, SharePreUtil.getIntValue(this.mContext, "_288_amplifier_round", 0)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSettingData() {
      ArrayList var2 = new ArrayList();
      String var1 = SharePreUtil.getStringValue(this.mContext, "_288_0x31", (String)null);
      if (!TextUtils.isEmpty(var1)) {
         byte[] var3 = Base64.decode(var1, 0);
         var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(var3[4], 0, 4)));
         if (DataHandleUtils.getIntFromByteWithBit(var3[6], 0, 1) == 1) {
            var1 = "_288_0x31_setting7";
         } else {
            var1 = "_288_0x31_setting8";
         }

         var2.add(new SettingUpdateEntity(0, 1, var1));
         var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(var3[6], 4, 2)));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }
}
