package com.hzbhd.canbus.car._197;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final int AMPLIFIER_PARAM_BASS_OFFSET = 2;
   static final int AMPLIFIER_PARAM_HALF_FAD = 7;
   static final String SHARE_197_IS_HYBIRD_VEHICLE = "share_197_is_hybird_vehicle";
   static final int VEHICLE_TYPE_CIVIC_AUTO_AC = 17;
   static final int VEHICLE_TYPE_CIVIC_MANUAL_AC = 16;
   static final int VEHICLE_TYPE_ELANTRA_AUTO_AC = 33;
   static final int VEHICLE_TYPE_ELANTRA_MANUAL_AC = 32;
   static final int VEHICLE_TYPE_TOYOTA = 1;
   private final int DRIVE_DATA_PAGE_0X21_0x22 = 0;
   private final int DRIVE_DATA_PAGE_0X23 = 1;
   private final int DRIVE_DATA_PAGE_0X27 = 2;
   private final int DRIVE_DATA_PAGE_0X35_0X34 = 3;
   private final int INVAILE_VALUE = -1;
   private final int MEDIA_SOURCE_ID_A2DP = 11;
   private final int MEDIA_SOURCE_ID_AUX = 7;
   private final int MEDIA_SOURCE_ID_DISC = 2;
   private final int MEDIA_SOURCE_ID_DVBT = 10;
   private final int MEDIA_SOURCE_ID_OFF = 0;
   private final int MEDIA_SOURCE_ID_PHONE = 5;
   private final int MEDIA_SOURCE_ID_SD = 9;
   private final int MEDIA_SOURCE_ID_TUNER = 1;
   private final int MEDIA_SOURCE_ID_TV = 3;
   private final int MEDIA_SOURCE_ID_USB = 8;
   private final int SEND_GIVEN_MEDIA_MESSAGE = 101;
   private final int SEND_NORMAL_MESSAGE = 102;
   private final String TAG = "_197_MsgMgr";
   private final String TAG_MEDIA_INFO_DEBUG = "_197_media_debug";
   private String[] m0x26ItemTitleArray;
   private String[] m0x31ItemTitleArray;
   private String[] m0x32ItemTitleArray;
   private String[] m0x37ItemTitleArray;
   private int[] m0x56Data;
   private int m0xC1Data0;
   private int m0xC1Data0Record;
   private boolean mBackStatus;
   private boolean mBeltStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private DecimalFormat mDecimalFormat0p0;
   private int mDifferentId;
   private int mEachId;
   private boolean mHandBreakStatus;
   private Handler mHandler;
   private boolean mIsLeftRudder;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private ID3[] mMeidaInfos;
   private int mOutDoorTemperature;
   private boolean mRaerLockStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap = new HashMap();
   private UiMgr mUiMgr;

   private void dealRightBlowMode() {
      GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
      int var1 = this.mDifferentId;
      boolean var3 = false;
      boolean var4 = false;
      boolean var2;
      if (var1 == 1) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
         var2 = var4;
         if (var1 == 0) {
            var2 = var4;
            if (!GeneralAirData.front_right_blow_window) {
               var2 = true;
            }
         }

         GeneralAirData.front_right_auto_wind = var2;
         GeneralAirData.front_right_blow_head = this.isWindModeMatch(var1, 1, 2);
         GeneralAirData.front_right_blow_foot = this.isWindModeMatch(var1, 2, 3);
      } else {
         var2 = var3;
         if (GeneralAirData.front_left_auto_wind) {
            var2 = var3;
            if (!GeneralAirData.front_right_blow_window) {
               var2 = true;
            }
         }

         GeneralAirData.front_right_auto_wind = var2;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
      }

   }

   private int getData(int... var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var1.length; ++var2) {
         var3 += var1[var2] << var2 * 8;
      }

      return (double)var3 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var3;
   }

   private String getFuelUnit(int var1) {
      if (var1 == 0) {
         return "mpg";
      } else if (var1 == 1) {
         return "km/l";
      } else {
         return var1 == 2 ? "l/100km" : "";
      }
   }

   private String getInfo(int var1, float var2, String var3) {
      return var1 == -1 ? "" : this.mDecimalFormat0p0.format((double)((float)var1 * var2)) + var3;
   }

   private String getMileageUnit(int var1) {
      if (var1 == 1) {
         return "mile";
      } else {
         return var1 == 2 ? "km" : "";
      }
   }

   private int getRadioBandData(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else if ("FM3".equals(var1)) {
         return 3;
      } else if ("AM1".equals(var1)) {
         return 17;
      } else {
         return "AM2".equals(var1) ? 18 : 0;
      }
   }

   private int[] getRedioFrequencyHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
   }

   private String getSpeedUnit(String var1) {
      return TextUtils.isEmpty(var1) ? "" : var1 + "/h";
   }

   private String getTimeInfo(int[] var1) {
      boolean var2 = ArrayUtils.isEmpty(var1);
      String var4 = "";
      if (var2) {
         return "";
      } else {
         if (var1.length > 0) {
            var4 = "" + var1[0] + CommUtil.getStrByResId(this.mContext, "_197_hour");
         }

         String var3 = var4;
         if (var1.length > 1) {
            var3 = var4 + var1[1] + CommUtil.getStrByResId(this.mContext, "_197_minute");
         }

         var4 = var3;
         if (var1.length > 2) {
            var4 = var3 + var1[1] + CommUtil.getStrByResId(this.mContext, "_197_second");
         }

         return var4;
      }
   }

   private int[] getTimeWithMinutes(int var1) {
      return new int[]{var1 / 60, var1 % 60};
   }

   private int[] getTimeWithSeconds(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private UiMgr getUiMgr() {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
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
            byte[] var5 = new byte[]{22, -127, 1};
            byte[] var7 = new byte[]{22, -124, 1, (byte)(GeneralAmplifierData.frontRear + 7)};
            byte var3 = (byte)(GeneralAmplifierData.leftRight + 7);
            byte[] var6 = new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.bandBass + 2)};
            byte[] var8 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandTreble + 2)};
            byte[] var9 = new byte[]{22, -124, 6, (byte)(GeneralAmplifierData.bandMiddle + 2)};
            byte[] var4 = new byte[]{22, -124, 7, (byte)GeneralAmplifierData.volume};
            this.iterator = Arrays.stream(new byte[][]{var5, var7, {22, -124, 2, var3}, var6, var8, var9, var4}).iterator();
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

   private void initData() {
      this.m0x26ItemTitleArray = new String[]{"geely_daytime_running_lights", "_55_0x67_data1_bit64", "_18_vehicle_setting_item_3_43", "_55_0x67_data1_bit32", "_197_autolock_by_speed", "_197_autolock_by_shift_from_p", "_197_autolock_by_shift_to_p", "_197_remote_2_press_unlock", "_197_lock_unlcok_feedback_tone", "_197_unlock_when_operating_key_twice", "_197_Driver_seat_open_door_linkage_unlock", "_197_smart_car_door_unlock", "_197_smart_car_lock_and_one_button_start", "_197_emergency_flashing_light_response_when_unlocking_locking", "_197_automatic_relock_time", "_197_linkage_of_air_conditioner_and_auto_key", "_197_linkage_between_internal_and_external_circulation_and_auto_key", "_197_rear_camera_guide"};
      this.m0x31ItemTitleArray = new String[]{"_186_asl", "_118_setting_title_96"};
      this.m0x32ItemTitleArray = new String[]{"amplifier_switch", "_197_amplifier_mute"};
      this.m0x37ItemTitleArray = new String[]{"_197_rear_system", "_197_rear_system_lock"};
      this.m0xC1Data0 = 35;
      this.m0xC1Data0Record = 35;
      this.mDecimalFormat0p0 = new DecimalFormat("0.0");
   }

   private void initHandler(Context var1) {
      this.mHandler = new Handler(this, Looper.getMainLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 101) {
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 102) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   private void initID3() {
      this.mMeidaInfos = new ID3[]{new ID3(112), new ID3(113), new ID3(114)};
   }

   private void initSettingsItem(SettingPageUiSet var1) {
      Log.i("ljq", "initSettingsItem: ");

      for(int var2 = 0; var2 < var1.getList().size(); ++var2) {
         List var4 = ((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)var4.get(var3)).getTitleSrn();
            this.mSettingItemIndeHashMap.put(var5, var2 << 8 & '\uff00' | var3);
         }
      }

   }

   private boolean is0x56DataChange() {
      if (Arrays.equals(this.m0x56Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x56Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mHandBreakStatus == GeneralDoorData.isHandBrakeUp && this.mBeltStatus == GeneralDoorData.isSeatBeltTie) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mHandBreakStatus = GeneralDoorData.isHandBrakeUp;
         this.mBeltStatus = GeneralDoorData.isSeatBeltTie;
         return true;
      }
   }

   private boolean isWindModeMatch(int var1, int... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1 == var2[var3]) {
            return true;
         }
      }

      return false;
   }

   private void realKeyClick1(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick1(var3, var1, var2[2], var2[3]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void reportID3Info(int var1, ID3[] var2) {
      int var5 = var2.length;
      byte var4 = 0;

      for(int var3 = 0; var3 < var5; ++var3) {
         if (var2[var3].isId3Change()) {
            var5 = var2.length;

            for(var3 = var4; var3 < var5; ++var3) {
               ID3 var6 = var2[var3];
               var6.recordId3Info();
               this.reportID3InfoFinal(var1, var6.dataType, var6.info);
            }

            return;
         }
      }

   }

   private void reportID3InfoFinal(int var1, int var2, String var3) {
      byte[] var4 = new byte[0];

      byte[] var6;
      try {
         var6 = DataHandleUtils.exceptBOMHead(var3.getBytes("UnicodeLittle"));
      } catch (UnsupportedEncodingException var5) {
         var5.printStackTrace();
         var6 = var4;
      }

      var6 = DataHandleUtils.byteMerger(new byte[]{22, (byte)var2, 17}, var6);
      if (var1 == SourceConstantsDef.SOURCE_ID.NULL.ordinal()) {
         this.sendNormalMessage(var6);
      } else {
         this.sendMediaMessage(var1, var6);
      }

   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 16) {
         return "LOW";
      } else {
         return var1 == 80 ? "HIGH" : (float)DataHandleUtils.rangeNumber(var1, 32, 64) / 2.0F + this.getTempUnitC(this.mContext);
      }
   }

   private String resolveOutDoorTemperature(int var1) {
      return var1 == 255 ? " " : var1 - 40 + this.getTempUnitC(this.mContext);
   }

   private void send0xC1Data() {
      boolean var2 = FutureUtil.instance.isHaveAtv();
      boolean var3 = FutureUtil.instance.isHaveDtv();
      int var1 = this.m0xC1Data0;
      if (!var2 && !var3) {
         var2 = false;
      } else {
         var2 = true;
      }

      var1 = DataHandleUtils.setIntByteWithBit(var1, 6, var2);
      this.m0xC1Data0 = var1;
      if (this.m0xC1Data0Record != var1) {
         this.m0xC1Data0Record = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte)var1});
      }

   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var5 = Message.obtain();
         var5.what = 101;
         var5.arg1 = var1;
         var5.obj = var2;
         this.mHandler.sendMessageDelayed(var5, var3);
      }
   }

   private void sendNormalMessage(Object var1) {
      this.sendNormalMessage(var1, 0L);
   }

   private void sendNormalMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = Message.obtain();
         var4.what = 102;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void sendVehicleTypeCommand() {
      Log.i("CanbusMsgService", "sendVehicleTypeCommand: mDifferentId:" + this.mDifferentId);
      int var1 = this.mDifferentId;
      if (var1 == 32 || var1 == 33) {
         Log.i("CanbusMsgService", "sendVehicleTypeCommand: mEachId:" + this.mEachId);
         CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)this.mEachId});
      }

   }

   private void setAirData0x55() {
      this.setOutDoorTemperature();
      byte[] var4 = this.mCanBusInfoByte;
      boolean var3 = false;
      var4[7] = 0;
      if (!this.isAirMsgRepeat(var4)) {
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[2]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
         boolean var2;
         if (var1 == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralAirData.front_left_auto_wind = var2;
         GeneralAirData.front_left_blow_window = this.isWindModeMatch(var1, 4, 6);
         GeneralAirData.front_left_blow_head = this.isWindModeMatch(var1, 1, 2);
         GeneralAirData.front_left_blow_foot = this.isWindModeMatch(var1, 2, 3, 4);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
         GeneralAirData.windshield_deicing = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]) ^ true;
         GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         this.dealRightBlowMode();
         var2 = var3;
         if (GeneralAirData.front_wind_level != 0) {
            var2 = true;
         }

         GeneralAirData.power = var2;
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAmplifierData0x31() {
      GeneralAmplifierData.frontRear = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      GeneralAmplifierData.frontRear -= 7;
      GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralAmplifierData.leftRight -= 7;
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      GeneralAmplifierData.bandBass -= 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAmplifierData.bandTreble -= 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
      GeneralAmplifierData.bandMiddle -= 2;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      this.saveAmplifierData(this.mContext, this.mCanId);
      this.updateAmplifierActivity((Bundle)null);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) / 8;
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1);
      ArrayList var4 = new ArrayList();
      int var1 = 0;

      while(true) {
         String[] var5 = this.m0x31ItemTitleArray;
         if (var1 >= var5.length) {
            this.updateGeneralSettingData(var4);
            this.updateSettingActivity((Bundle)null);
            return;
         }

         int[] var6 = this.getLeftAndRight(var5[var1]);
         Object var7 = (new Object[]{var2, var3})[var1];
         var4.add(new SettingUpdateEntity(var6[0], var6[1], var7));
         ++var1;
      }
   }

   private void setBaseInfo0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var1;
      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1 = false;
      } else {
         var1 = true;
      }

      GeneralDoorData.isBackOpen = var1;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setCeilingScreenData0x37() {
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
      ArrayList var4 = new ArrayList();
      int var1 = 0;

      while(true) {
         String[] var5 = this.m0x37ItemTitleArray;
         if (var1 >= var5.length) {
            this.updateGeneralSettingData(var4);
            this.updateSettingActivity((Bundle)null);
            return;
         }

         int[] var6 = this.getLeftAndRight(var5[var1]);
         Object var7 = (new Object[]{var3, var2})[var1];
         var4.add(new SettingUpdateEntity(var6[0], var6[1], var7));
         ++var1;
      }
   }

   private void setFrontRadarData0x1D() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setHybirdData0x1F() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      SharePreUtil.setBoolValue(this.mContext, "share_197_is_hybird_vehicle", var1);
      this.getUiMgr().updateMainActivityItem(this.mContext, "hybird", var1);
      if (var1) {
         GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         this.updateHybirdActivity((Bundle)null);
      }

   }

   private void setMediaSwitch0x36() {
      try {
         switch (this.mCanBusInfoInt[2]) {
            case 0:
               this.realKeyClick4(77);
               break;
            case 1:
               this.realKeyClick4(76);
               break;
            case 2:
               this.realKeyClick4(130);
               break;
            case 3:
               this.realKeyClick4(139);
               break;
            case 4:
               this.realKeyClick4(140);
               break;
            case 5:
               this.realKeyClick4(141);
               break;
            case 6:
               this.realKeyClick4(142);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private void setOdoMileageData0x34() {
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(3, 2, var3.append(this.getData(var1[4], var1[3], var1[2])).append(" km").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOutDoorTemperature() {
      int var2 = this.mOutDoorTemperature;
      int var1 = this.mCanBusInfoInt[7];
      if (var2 != var1) {
         this.mOutDoorTemperature = var1;
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTemperature(var1));
      }

   }

   private void setRearAirInfo0x56() {
      int[] var4 = this.mCanBusInfoInt;
      var4[6] &= 251;
      if (this.is0x56DataChange()) {
         GeneralAirData.rear_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_right_temperature = GeneralAirData.rear_left_temperature;
         int var1 = this.mCanBusInfoInt[4];
         boolean var3 = false;
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(var1, 0, 4);
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
         boolean var2;
         if (var1 == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralAirData.rear_left_auto_wind = var2;
         if (var1 == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralAirData.rear_right_auto_wind = var2;
         if (var1 != 1 && var1 != 2) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.rear_left_blow_head = var2;
         if (var1 != 1 && var1 != 2) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.rear_right_blow_head = var2;
         if (var1 != 2 && var1 != 3) {
            var2 = false;
         } else {
            var2 = true;
         }

         label37: {
            GeneralAirData.rear_left_blow_foot = var2;
            if (var1 != 2) {
               var2 = var3;
               if (var1 != 3) {
                  break label37;
               }
            }

            var2 = true;
         }

         GeneralAirData.rear_right_blow_foot = var2;
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         this.updateAirActivity(this.mContext, 1002);
      }

   }

   private void setRearRadarData0x1E() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSystemInfo0x32() {
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      ArrayList var4 = new ArrayList();
      int var1 = 0;

      while(true) {
         String[] var5 = this.m0x32ItemTitleArray;
         if (var1 >= var5.length) {
            this.updateGeneralSettingData(var4);
            this.updateSettingActivity((Bundle)null);
            return;
         }

         int[] var6 = this.getLeftAndRight(var5[var1]);
         Object var7 = (new Object[]{var3, var2})[var1];
         var4.add(new SettingUpdateEntity(var6[0], var6[1], var7));
         ++var1;
      }
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 360, 12);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTripInfo0x21() {
      String var3 = this.getMileageUnit(this.mCanBusInfoInt[8]);
      int[] var2 = this.mCanBusInfoInt;
      int var1 = DataHandleUtils.rangeNumber(this.getData(var2[3], var2[2]), 0, 9999);
      String var6 = (float)var1 * 0.1F + " " + this.getSpeedUnit(var3);
      int[] var4 = this.mCanBusInfoInt;
      String var8 = this.getTimeInfo(this.getTimeWithMinutes(DataHandleUtils.rangeNumber(this.getData(var4[5], var4[4]), 0, 5999)));
      int[] var5 = this.mCanBusInfoInt;
      var1 = DataHandleUtils.rangeNumber(this.getData(var5[7], var5[6]), 0, 9999);
      String var9 = var1 + " " + var3;
      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(0, 0, var6));
      var7.add(new DriverUpdateEntity(0, 1, var8));
      var7.add(new DriverUpdateEntity(0, 2, var9));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTripInfo0x22() {
      String var2 = " " + this.getFuelUnit(this.mCanBusInfoInt[2]);
      int[] var3 = this.mCanBusInfoInt;
      int var1 = this.getData(var3[4], var3[3]);
      String var5 = (float)var1 * 0.1F + var2;
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 3, var5));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTripInfo0x23() {
      String var2 = " " + this.getFuelUnit(this.mCanBusInfoInt[2]);
      int[] var1 = this.mCanBusInfoInt;
      String var8 = this.getInfo(this.getData(var1[4], var1[3]), 0.1F, var2);
      int[] var3 = this.mCanBusInfoInt;
      String var9 = this.getInfo(this.getData(var3[6], var3[5]), 0.1F, var2);
      int[] var4 = this.mCanBusInfoInt;
      String var10 = this.getInfo(this.getData(var4[8], var4[7]), 0.1F, var2);
      int[] var5 = this.mCanBusInfoInt;
      String var11 = this.getInfo(this.getData(var5[10], var5[9]), 0.1F, var2);
      int[] var6 = this.mCanBusInfoInt;
      String var12 = this.getInfo(this.getData(var6[12], var6[11]), 0.1F, var2);
      int[] var7 = this.mCanBusInfoInt;
      var2 = this.getInfo(this.getData(var7[14], var7[13]), 0.1F, var2);
      ArrayList var13 = new ArrayList();
      var13.add(new DriverUpdateEntity(1, 0, var8));
      var13.add(new DriverUpdateEntity(1, 1, var9));
      var13.add(new DriverUpdateEntity(1, 2, var10));
      var13.add(new DriverUpdateEntity(1, 3, var11));
      var13.add(new DriverUpdateEntity(1, 4, var12));
      var13.add(new DriverUpdateEntity(1, 5, var2));
      this.updateGeneralDriveData(var13);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTripInfo0x27() {
      String var4 = this.getFuelUnit(this.mCanBusInfoInt[2]);
      ArrayList var5 = new ArrayList();

      for(int var1 = 0; var1 < 15; ++var1) {
         int[] var3 = this.mCanBusInfoInt;
         int var2 = var1 * 2;
         var5.add(new DriverUpdateEntity(2, 14 - var1, this.getInfo(this.getData(var3[var2 + 4], var3[var2 + 3]), 0.1F, " " + var4)));
      }

      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVehicleInfo0x35() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(3, 0, var3.append(this.getData(var2[3], var2[2])).append(" rpm").toString()));
      var1.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[4] + " km/h"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVehicleSettings0x26() {
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
      int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
      int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
      int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2);
      ArrayList var20 = new ArrayList();
      int var1 = 0;

      while(true) {
         String[] var21 = this.m0x26ItemTitleArray;
         if (var1 >= var21.length) {
            this.updateGeneralSettingData(var20);
            this.updateSettingActivity((Bundle)null);
            return;
         }

         String var24 = var21[var1];
         int[] var23 = this.getLeftAndRight(var24);
         if (var23[0] != -1 && var23[1] != -1) {
            Object var22 = (new Object[]{var8, var14, var4, var11, var2, var13, var12, var15, var18, var9, var5, var10, var19, var6, var17, var7, var3, var16})[var1];
            var24.hashCode();
            if (!var24.equals("_55_0x67_data1_bit64") && !var24.equals("_197_lock_unlcok_feedback_tone")) {
               var20.add(new SettingUpdateEntity(var23[0], var23[1], var22));
            } else {
               var20.add((new SettingUpdateEntity(var23[0], var23[1], var22)).setProgress((Integer)var22));
            }
         }

         ++var1;
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
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 9) {
                        if (var1 != 10) {
                           if (var1 != 136) {
                              switch (var1) {
                                 case 19:
                                    this.realKeyClick1(45);
                                    break;
                                 case 20:
                                    this.realKeyClick1(46);
                                    break;
                                 case 21:
                                    this.realKeyClick1(50);
                                    break;
                                 case 22:
                                    this.realKeyClick1(49);
                              }
                           } else {
                              this.realKeyClick1(2);
                           }
                        } else {
                           this.realKeyClick1(15);
                        }
                     } else {
                        this.realKeyClick1(14);
                     }
                  } else {
                     this.realKeyClick1(47);
                  }
               } else {
                  this.realKeyClick1(48);
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

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      Log.i("_197_MsgMgr", "CanbusMsgService afterServiceNormalSetting: ");
      this.mContext = var1;
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      boolean var2;
      if ((this.mDifferentId & 240) == 32 && (this.mEachId & 128) == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.mIsLeftRudder = var2;
      this.initID3();
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowHandBrake = true;
      this.initData();
      this.getUiMgr().updateUiByDifferentCar(var1);
      this.initSettingsItem(this.getUiMgr().getSettingUiSet(var1));
      RadarInfoUtil.mMinIsClose = true;
      this.initHandler(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      Log.i("_197_media_debug", "atvInfoChange: ");
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 3, (byte)34}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), this.mMeidaInfos);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      Log.i("_197_media_debug", "auxInInfoChange: ");
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 7, (byte)48}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), this.mMeidaInfos);
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      Log.i("_197_media_debug", "btMusicId3InfoChange: ");
      this.mMeidaInfos[0].setInfo(var1);
      this.mMeidaInfos[1].setInfo(var2);
      this.mMeidaInfos[2].setInfo(var3);
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), this.mMeidaInfos);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      Log.i("_197_media_debug", "btMusicInfoChange: ");
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 11, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), var1);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      Log.i("_197_media_debug", "btMusiceDestdroy: ");
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      Log.i("_197_media_debug", "btPhoneHangUpInfoChange: ");
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      Log.i("_197_media_debug", "btPhoneIncomingInfoChange: ");
      byte[] var4 = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), var4);
      this.mMeidaInfos[0].setInfo(new String(var1));
      this.mMeidaInfos[1].setInfo(CommUtil.getStrByResId(this.mContext, "incoming") + "...");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      Log.i("_197_media_debug", "btPhoneOutGoingInfoChange: ");
      byte[] var4 = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), var4);
      this.mMeidaInfos[0].setInfo(new String(var1));
      this.mMeidaInfos[1].setInfo(CommUtil.getStrByResId(this.mContext, "outgoing") + "...");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.m0xC1Data0 = DataHandleUtils.setIntByteWithBit(this.m0xC1Data0, 4, var3);
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      Log.i("_197_media_debug", "btPhoneTalkingWithTimeInfoChange: ");
      byte[] var5 = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), var5);
      this.mMeidaInfos[0].setInfo(new String(var1));
      this.mMeidaInfos[1].setInfo(CommUtil.getStrByResId(this.mContext, "talking") + "...");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 38) {
         if (var3 != 39) {
            if (var3 != 41) {
               if (var3 != 85) {
                  switch (var3) {
                     case 29:
                        this.setFrontRadarData0x1D();
                        break;
                     case 30:
                        this.setRearRadarData0x1E();
                        break;
                     case 31:
                        this.setHybirdData0x1F();
                        break;
                     case 32:
                        this.setWheelKey0x20();
                        break;
                     case 33:
                        this.setTripInfo0x21();
                        break;
                     case 34:
                        this.setTripInfo0x22();
                        break;
                     case 35:
                        this.setTripInfo0x23();
                        break;
                     case 36:
                        this.setBaseInfo0x24();
                        break;
                     default:
                        switch (var3) {
                           case 48:
                              this.setVersionInfo0x30();
                              break;
                           case 49:
                              this.setAmplifierData0x31();
                              break;
                           case 50:
                              this.setSystemInfo0x32();
                              break;
                           default:
                              switch (var3) {
                                 case 52:
                                    this.setOdoMileageData0x34();
                                    break;
                                 case 53:
                                    this.setVehicleInfo0x35();
                                    break;
                                 case 54:
                                    this.setMediaSwitch0x36();
                                    break;
                                 case 55:
                                    this.setCeilingScreenData0x37();
                              }
                        }
                  }
               } else {
                  this.setAirData0x55();
               }
            } else {
               this.setTrackData0x29();
            }
         } else {
            this.setTripInfo0x27();
         }
      } else {
         this.setVehicleSettings0x26();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      var1 = DataHandleUtils.setIntFromByteWithBit(this.m0xC1Data0, var4, 2, 1);
      this.m0xC1Data0 = var1;
      this.m0xC1Data0 = DataHandleUtils.setIntFromByteWithBit(var1, var6, 3, 1);
      this.send0xC1Data();
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.i("_197_media_debug", "discInfoChange: ");
      if (var7 == 1) {
         var4 = var5;
      }

      int[] var18 = this.getTimeWithSeconds(var2);
      byte var17 = (byte)var4;
      var1 = (byte)var6;
      byte var14 = (byte)var18[0];
      byte var16 = (byte)var18[1];
      byte var15 = (byte)var18[2];
      byte[] var19 = DataHandleUtils.byteMerger(new byte[]{22, -64, 2, (byte)255}, new byte[]{0, var17, var1, var14, var16, var15});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var19);
      this.mMeidaInfos[0].setInfo(var11);
      this.mMeidaInfos[1].setInfo(var12);
      this.mMeidaInfos[2].setInfo(var13);
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), this.mMeidaInfos);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      Log.i("_197_media_debug", "dtvInfoChange: ");
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 10, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), this.mMeidaInfos);
   }

   int[] getLeftAndRight(String var1) {
      int[] var3 = new int[2];
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         int var2 = (Integer)this.mSettingItemIndeHashMap.get(var1);
         var3[0] = var2 >> 8;
         var3[1] = var2 & 255;
      } else {
         var3[0] = -1;
         var3[1] = -1;
      }

      Log.i("ljq", "getLeftAndRigth: left:" + var3[0] + ", right:" + var3[1]);
      return var3;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      Log.i("_197_MsgMgr", "CanbusMsgService initCommand: 197");
      this.initAmplifier(var1);
      this.sendVehicleTypeCommand();
   }

   public void musicDestroy() {
      super.musicDestroy();
      Log.i("_197_media_debug", "musicDestroy: ");
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      Log.i("_197_media_debug", "musicInfoChange: ");
      byte var25 = 9;
      if (var1 != 9) {
         var25 = 8;
      }

      var1 = (byte)var3;
      byte[] var26 = DataHandleUtils.byteMerger(new byte[]{22, -64, (byte)var25, (byte)255}, new byte[]{var1, var9, 0, var5, var6, var7});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), var26);
      this.mMeidaInfos[0].setInfo(var21);
      this.mMeidaInfos[1].setInfo(var22);
      this.mMeidaInfos[2].setInfo(var23);
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), this.mMeidaInfos);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      Log.i("_197_media_debug", "radioInfoChange: ");
      int[] var11 = this.getRedioFrequencyHiLo(var2, var3);
      byte var8 = (byte)this.getRadioBandData(var2);
      byte var9 = (byte)var11[1];
      byte var6 = (byte)var11[0];
      byte var7 = (byte)var1;
      byte[] var10 = DataHandleUtils.byteMerger(new byte[]{22, -64, 1, (byte)1}, new byte[]{var8, var9, var6, var7, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), var10);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.FM.ordinal(), this.mMeidaInfos);
   }

   protected void sendDiscEjectMsg(Context var1) {
      super.sendDiscEjectMsg(var1);
      Log.i("_197_media_debug", "sendDiscEjectMsg: ");
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         Log.i("_197_media_debug", "sourceSwitchNoMediaInfoChange: ");
         this.sendNormalMessage(DataHandleUtils.byteMerger(new byte[]{22, -64, 0, (byte)0}, new byte[]{0, 0, 0, 0, 0, 0}));
         this.mMeidaInfos[0].setInfo(" ");
         this.mMeidaInfos[1].setInfo(" ");
         this.mMeidaInfos[2].setInfo(" ");
         this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      Log.i("_197_media_debug", "videoInfoChange: ");
      byte var18 = 9;
      if (var1 != 9) {
         var18 = 8;
      }

      var1 = (byte)var3;
      byte[] var19 = DataHandleUtils.byteMerger(new byte[]{22, -64, (byte)var18, (byte)19}, new byte[]{var1, var9, 0, var5, var6, var7});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), var19);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), this.mMeidaInfos);
   }

   private static class ID3 {
      private final int ID3_INFO_LENGTH;
      private int dataType;
      private String info;
      private String record;

      private ID3(int var1) {
         this.ID3_INFO_LENGTH = 24;
         this.dataType = var1;
         this.info = "";
         this.record = "";
      }

      // $FF: synthetic method
      ID3(int var1, Object var2) {
         this(var1);
      }

      private boolean isId3Change() {
         if (this.record.equals(this.info)) {
            return false;
         } else {
            this.record = this.info;
            return true;
         }
      }

      private void recordId3Info() {
         this.record = this.info;
      }

      public void setInfo(String var1) {
         this.info = var1;
         if (var1.length() > 24) {
            this.info = this.info.substring(0, 21) + "...";
         }

      }
   }
}
