package com.hzbhd.canbus.car._206;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SetTimeView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.VoiceControlData;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static final String CAROUTTEMCHANGEACTION2 = "com.android.systemui.statusbar.action.CAROUTTEMCHANGE2";
   static int carSpeedWarning;
   static int frontWindLv;
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   static boolean isPanoramicRadarClose;
   static float leftFrontTemp;
   private static int outDoorTemp;
   static float rearTemp;
   private static int rearWindBlow;
   static float rightFrontTemp;
   private static boolean settingFirstMsg;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_BATTERY_WARNING = "is_battery_warning";
   private final String IS_FRONT_OPEN = "is_front_open";
   private final String IS_FUEL_WARNING = "is_fuel_warning";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
   private final String IS_REAR_AIR_POWER = "is_rear_air_power";
   private final String IS_REAR_AIR_TEMP_CHANGE_MQB = "is_rear_air_temp_change_mqb";
   private final String IS_REAR_AIR_WIND_BLOW = "is_rear_air_wind_blow";
   private final String IS_REAR_AIR_WIND_LV_CHANGE = "is_rear_air_wind_lv_change";
   private final String IS_REAR_AUTO = "is_rear_auto";
   private final String IS_REAR_LEFT_HEAT = "is_rear_left_heat";
   private final String IS_REAR_RIGHT_HEAT = "is_rear_right_heat";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private final String IS_SEATBELT_TIE = "is_seatbelt_tie";
   private final String IS_WASHING_FLUID_WARNING = "is_washing_fluid_warning";
   private int[] arrConvConsumers = new int[]{2131770297, 2131770307, 2131770308, 2131770309, 2131770310, 2131770311, 2131770312, 2131770313, 2131770314, 2131770298, 2131770299, 2131770300, 2131770301, 2131770302, 2131770303, 2131770304, 2131770305, 2131770306};
   private int[] arrReportsHw = $d2j$hex$b770e98c$decode_I("c13c107f303d107f4a3d107f553d107f603d107f6b3d107f763d107fee3c107ff93c107f043d107f0f3d107f1a3d107f253d107f313d107f3c3d107f423d107f433d107f703d107f713d107f723d107f733d107f743d107f753d107f773d107f783d107f793d107f7a3d107f7b3d107f7c3d107f7d3d107f7e3d107f7f3d107f803d107f823d107f833d107f843d107f853d107f863d107f873d107f883d107f893d107f8a3d107f8b3d107f8d3d107f8e3d107f8f3d107f903d107f913d107f923d107f933d107f943d107f953d107f963d107fc33c107fc43c107fc53c107fc63c107fc73c107fc83c107fc93c107fca3c107fcb3c107fcc3c107fce3c107fcf3c107fd03c107fd13c107fd23c107fd33c107fd43c107fd53c107fd63c107fd73c107fd93c107fda3c107fdb3c107fdc3c107fdd3c107fde3c107fdf3c107fe03c107fe13c107fff3c107f003d107f013d107f023d107f033d107f053d107f063d107f073d107f083d107f093d107f0a3d107f0b3d107f0c3d107f0d3d107f0e3d107f103d107f113d107f123d107f133d107f143d107f153d107f163d107f173d107f443d107f453d107f463d107f473d107f483d107f493d107f4b3d107f4c3d107f4d3d107f4e3d107f4f3d107f503d107f513d107f523d107f533d107f543d107f563d107f573d107f583d107f593d107f5a3d107f5b3d107f5c3d107f5d3d107f5e3d107f5f3d107f613d107f623d107f633d107f643d107f653d107f663d107f673d107f683d107f693d107f6a3d107f6c3d107f6d3d107f6e3d107f6f3d107fe23c107fe43c107fe53c107fe63c107fe73c107fe83c107fe93c107fea3c107feb3c107fec3c107fed3c107fef3c107ff03c107ff13c107ff23c107ff33c107ff43c107ff53c107ff63c107ff73c107ff83c107f183d107f193d107f1b3d107f1c3d107f1d3d107f1e3d107f1f3d107f203d107f213d107f223d107f233d107f243d107f263d107f273d107f283d107f293d107f2a3d107f2b3d107f2c3d107f2d3d107f2e3d107f2f3d107f323d107f333d107f343d107f353d107f363d107f373d107f383d107f393d107f3a3d107f3b3d107f3d3d107f3e3d107f3f3d107f403d107f413d107f");
   private int[] arrStartStopHw = new int[]{2131770786, 2131770797, 2131770808, 2131770819, 2131770820, 2131770821, 2131770822, 2131770823, 2131770824, 2131770787, 2131770788, 2131770789, 2131770812, 2131770790, 2131770808, 2131770794, 2131770795, 2131770813, 2131770814, 2131770815, 2131770791, 2131770792, 2131770793, 2131770794, 2131770795, 2131770796, 2131770798, 2131770799, 2131770800, 2131770801, 2131770802, 2131770803, 2131770804, 2131770805, 2131770806, 2131770807, 2131770809, 2131770810, 2131770811, 2131770794, 2131770817, 2131770818, 2131770776, 2131770777, 2131770778, 2131770784, 2131770779, 2131770780, 2131770781, 2131770782, 2131770783};
   int bDay = 0;
   int bHours = 0;
   int bMins = 0;
   int bMonth = 0;
   int bYear2Dig = 0;
   int[] carStateIntArray;
   private CountDownTimer countDownTimer;
   private boolean isFirst = true;
   boolean isFormat24H = true;
   boolean isGpsTime = true;
   private boolean isLockAirViewToShow = false;
   private boolean lastAccState = false;
   int[] mAirData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusSwc0x0cInfoCopy;
   private byte[] mCanBusSwc0x0dInfoCopy;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private boolean mCurrentCaseC;
   private boolean mCurrentCaseD;
   DecimalFormat mDecimalFormat = new DecimalFormat("#####00");
   private List mList1 = new ArrayList();
   private List mList2 = new ArrayList();
   private List mList3 = new ArrayList();
   private UiMgr mUiMgr;
   byte[] nowWayNameByte;
   byte[] otherInfoByte;
   int result;
   int systemDateFormat = 0;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void airSet() {
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]));
      int var4 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      int var1 = 0;

      ArrayList var5;
      for(var5 = new ArrayList(); var1 <= 2; ++var1) {
         var5.add(new SettingUpdateEntity(14, var1, (new int[]{var3, var2, var4})[var1]));
      }

      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
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

   private void dccOffOnCtrl() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[4] == 12 && var1[5] == 0) {
         if (!this.mCurrentCaseC) {
            this.startSettingActivity(this.mContext, 0, 0);
            this.mCurrentCaseC = true;
            this.mCurrentCaseD = false;
         } else {
            this.finishActivity();
            this.mCurrentCaseC = false;
         }
      }

   }

   private int distanceToLocation(int var1) {
      if (var1 >= 0 && var1 <= 15) {
         return 1;
      } else if (var1 >= 16 && var1 <= 30) {
         return 2;
      } else if (var1 >= 31 && var1 <= 45) {
         return 3;
      } else if (var1 >= 46 && var1 <= 60) {
         return 4;
      } else if (var1 >= 61 && var1 <= 75) {
         return 5;
      } else if (var1 >= 76 && var1 <= 90) {
         return 6;
      } else if (var1 >= 91 && var1 <= 105) {
         return 7;
      } else if (var1 >= 106 && var1 <= 120) {
         return 8;
      } else if (var1 >= 121 && var1 <= 135) {
         return 9;
      } else if (var1 >= 136 && var1 <= 150) {
         return 10;
      } else if (var1 >= 151 && var1 <= 165) {
         return 11;
      } else {
         return var1 >= 166 ? 12 : 0;
      }
   }

   private String get0x11Data9State() {
      int var1 = this.mCanBusInfoInt[11];
      if (var1 == 1) {
         return this.mContext.getString(2131759104);
      } else {
         return var1 == 2 ? this.mContext.getString(2131759105) : this.mContext.getString(2131759103);
      }
   }

   private String getBandUnit(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
         case 1:
            return "KHz";
         case 2:
         case 3:
         case 4:
            return "MHz";
         default:
            return "";
      }
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIndexBy3Bit(int var1) {
      byte var2 = 2;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private int getIndexBy4Bit(int var1) {
      byte var2 = 3;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            var2 = 2;
            return var2;
         }

         if (var1 == 3) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private int getIndexBy5Bit(int var1) {
      byte var2 = 4;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            var2 = 2;
            return var2;
         }

         if (var1 == 3) {
            var2 = 3;
            return var2;
         }

         if (var1 == 4) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   public static int getLsb(int var0) {
      return (var0 & -1) >> 0 & 255;
   }

   public static int getMidLsb(int var0) {
      return (var0 & -1) >> 8 & 255;
   }

   public static int getMidMsb(int var0) {
      return (var0 & -1) >> 16 & 255;
   }

   public static int getMsb(int var0) {
      return (var0 & -1) >> 24 & 255;
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, String var4) {
      return new TireUpdateEntity(var1, var2.equals(this.mContext.getResources().getString(2131767766)) ^ 1, new String[]{var2, var3, var4});
   }

   private String getTirePressure(int var1, int var2) {
      StringBuilder var4 = (new StringBuilder()).append((float)((double)(var1 * 256 + var2) * 0.1));
      String var3 = "";
      String var5 = var4.append("").toString();
      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var3 = "psi";
            }
         } else {
            var3 = "bar";
         }
      } else {
         var3 = "kPa";
      }

      return var5 + var3;
   }

   private String getTisWarmMsg(int var1) {
      return var1 == 0 ? this.mContext.getResources().getString(2131767766) : this.mContext.getResources().getString(2131767772);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initLock() {
      Log.e("ooo", "初始化时钟");
      CountDownTimer var1 = this.countDownTimer;
      if (var1 != null) {
         var1.cancel();
      }

      var1 = new CountDownTimer(this, 20000L, 1000L) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            this.this$0.isLockAirViewToShow = false;
            Log.e("ooo", "倒计时结束" + this.this$0.isLockAirViewToShow);
         }

         public void onTick(long var1) {
            Log.e("ooo", "倒计时中..." + this.this$0.isLockAirViewToShow);
         }
      };
      this.countDownTimer = var1;
      var1.start();
   }

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_battery_warning", false) != GeneralDoorData.isBatteryWarning) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_seatbelt_tie", false) != GeneralDoorData.isSeatBeltTie) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_washing_fluid_warning", false) != GeneralDoorData.isWashingFluidWarning) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) != GeneralDoorData.isFrontOpen;
      }
   }

   private boolean isOnlyOutDoorDataChange() {
      return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0F) != (float)outDoorTemp;
   }

   private boolean isOnlyRearAirDataChange() {
      if (SharePreUtil.getFloatValue(this.mContext, "is_rear_air_temp_change_mqb", 0.0F) != rearTemp) {
         return true;
      } else if (SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_lv_change", 0) != GeneralAirData.rear_wind_level) {
         return true;
      } else if (SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_blow", 0) != rearWindBlow) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_rear_air_power", false) != GeneralAirData.rear_power) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_rear_auto", false) != GeneralAirData.rear_auto) {
         return true;
      } else if (SharePreUtil.getIntValue(this.mContext, "is_rear_left_heat", 0) != GeneralAirData.rear_left_seat_heat_level) {
         return true;
      } else {
         return SharePreUtil.getIntValue(this.mContext, "is_rear_right_heat", 0) != GeneralAirData.rear_right_seat_heat_level;
      }
   }

   private boolean isOtherInfoChange(byte[] var1) {
      if (Arrays.equals(this.otherInfoByte, var1)) {
         return false;
      } else {
         this.otherInfoByte = var1;
         return true;
      }
   }

   private boolean isSwc0x0cReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwc0x0cInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwc0x0cInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isSwc0x0dReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwc0x0dInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwc0x0dInfoCopy = Arrays.copyOf(var1, var1.length);
         if (settingFirstMsg) {
            settingFirstMsg = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isWayNameChange(byte[] var1) {
      if (Arrays.equals(this.nowWayNameByte, var1)) {
         return false;
      } else {
         this.nowWayNameByte = var1;
         return true;
      }
   }

   private void keyControl0x11() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.realKeyLongClick(0);
            break;
         case 1:
            this.realKeyLongClick(7);
            break;
         case 2:
            this.realKeyLongClick(8);
            break;
         case 3:
            Log.d("fzy", "MENU");
            this.realKeyLongClick(3);
            break;
         case 4:
            this.realKeyLongClick(187);
            break;
         case 5:
            this.realKeyLongClick(188);
         case 6:
         case 7:
         default:
            break;
         case 8:
            this.realKeyLongClick(46);
            break;
         case 9:
            this.realKeyLongClick(45);
            break;
         case 10:
            this.realKeyLongClick(52);
            break;
         case 11:
            this.realKeyLongClick(2);
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

   private void realKeyLongClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (GeneralAirData.fahrenheit_celsius) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = (int)((float)var1 * 0.5F) + this.getTempUnitF(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      String var2;
      if (GeneralAirData.fahrenheit_celsius) {
         var2 = (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
      } else {
         var2 = (float)(((double)var1 * 0.5 - 40.0) * 1.8 + 32.0) + this.getTempUnitF(this.mContext);
      }

      return var2;
   }

   private void saveAccState() {
      if (this.lastAccState != DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         this.isLockAirViewToShow = true;
         this.initLock();
         this.lastAccState = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      }

      Log.e("ooo", "上锁" + this.isLockAirViewToShow);
   }

   private void sendGdOtherInfo(byte[] var1) {
      if (this.isOtherInfoChange(var1)) {
         CanbusMsgSender.sendMsg(var1);
      }

   }

   private void sendSourceMsg1(String var1) {
      byte[] var2 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, var2), 28));
   }

   private void sendSourceMsg2(String var1, int var2) {
      byte[] var3 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, (byte)var2, 0}, var3), 27));
   }

   private void sendWayName(byte[] var1) {
      if (this.isWayNameChange(var1)) {
         CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -27, 0}, var1));
      }

   }

   private void serDriveStatusStart() {
      int[] var11 = this.mCanBusInfoInt;
      double var7 = (double)(var11[2] * 256 + var11[3]);
      double var5 = (double)(var11[4] * 256 + var11[5]);
      double var1 = (double)(var11[6] * 256 + var11[7]);
      double var9 = (double)(var11[8] * 256 + var11[9]);
      double var3 = (double)var11[10];
      ArrayList var12 = new ArrayList();
      var12.add(new DriverUpdateEntity(0, 0, (float)(var7 * 0.1) + "l/km"));
      var12.add(new DriverUpdateEntity(0, 1, (float)var5 + "km"));
      var12.add(new DriverUpdateEntity(0, 2, (float)(var1 * 0.1) + "km"));
      var12.add(new DriverUpdateEntity(0, 3, (int)(var9 / 60.0) + " h " + (int)(var9 % 60.0) + "min"));
      var12.add(new DriverUpdateEntity(0, 4, (int)var3 + "km/h"));
      this.updateGeneralDriveData(var12);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarDetailedInfo() {
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(3, 0, var3.append(var1[10] * 256 + var1[11]).append("RPM").toString()));
      var2.add(new DriverUpdateEntity(3, 4, this.mCanBusInfoInt[8] - 40 + "℃"));
      var2.add(new DriverUpdateEntity(3, 5, this.mCanBusInfoInt[9] - 40 + "℃"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarDoorInfo() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowBatteryWarning = true;
      GeneralDoorData.isShowFuelWarning = true;
      GeneralDoorData.isShowWashingFluidWarning = true;
      GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      GeneralDoorData.isSeatBeltTie = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
      GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      if (this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_battery_warning", GeneralDoorData.isBatteryWarning);
         SharePreUtil.setBoolValue(this.mContext, "is_seatbelt_tie", GeneralDoorData.isSeatBeltTie);
         SharePreUtil.setBoolValue(this.mContext, "is_washing_fluid_warning", GeneralDoorData.isWashingFluidWarning);
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
         this.updateDoorView(this.mContext);
      }

   }

   private void setCarLightInfo() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getResources().getString(2131769504);
      } else {
         var1 = this.mContext.getResources().getString(2131768042);
      }

      var2.add(new DriverUpdateEntity(3, 6, var1));
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getResources().getString(2131769504);
      } else {
         var1 = this.mContext.getResources().getString(2131768042);
      }

      var2.add(new DriverUpdateEntity(3, 7, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarMaintenance() {
      int[] var9 = this.mCanBusInfoInt;
      int var1 = var9[4] * 256 + var9[5];
      int var6 = var9[6] * 256 + var9[7];
      int var2 = var9[8] * 256 + var9[9];
      int var3 = var9[10] * 256 + var9[11];
      int var4 = DataHandleUtils.getIntFromByteWithBit(var9[3], 6, 2);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      String var13;
      if (this.mCanBusInfoInt[2] == 0) {
         var13 = this.mContext.getResources().getString(2131770212);
      } else {
         var13 = this.mContext.getResources().getString(2131770222);
      }

      String var12 = "";
      String var15;
      if (var4 != 0) {
         if (var4 != 1) {
            if (var4 != 2) {
               var15 = "";
            } else {
               var15 = String.format(this.mContext.getResources().getString(2131770510), var1);
            }
         } else {
            var15 = String.format(this.mContext.getResources().getString(2131770505), var1);
         }
      } else {
         var15 = this.mContext.getResources().getString(2131769909);
      }

      String var10;
      if (var8 != 0) {
         if (var8 != 1) {
            if (var8 != 2) {
               var10 = "";
            } else {
               var10 = String.format(this.mContext.getResources().getString(2131770511), var6);
            }
         } else {
            var10 = String.format(this.mContext.getResources().getString(2131770506), var6);
         }
      } else {
         var10 = this.mContext.getResources().getString(2131769909);
      }

      String var11;
      if (var5 != 0) {
         if (var5 != 1) {
            if (var5 != 2) {
               var11 = "";
            } else {
               var11 = String.format(this.mContext.getResources().getString(2131770510), var2);
            }
         } else {
            var11 = String.format(this.mContext.getResources().getString(2131770505), var2);
         }
      } else {
         var11 = this.mContext.getResources().getString(2131769909);
      }

      if (var7 != 0) {
         if (var7 != 1) {
            if (var7 == 2) {
               var12 = String.format(this.mContext.getResources().getString(2131770511), var3);
            }
         } else {
            var12 = String.format(this.mContext.getResources().getString(2131770506), var3);
         }
      } else {
         var12 = this.mContext.getResources().getString(2131769909);
      }

      ArrayList var14 = new ArrayList();
      var14.add(new DriverUpdateEntity(4, 0, var13));
      var14.add(new DriverUpdateEntity(4, 1, var15));
      var14.add(new DriverUpdateEntity(4, 2, var10));
      var14.add(new DriverUpdateEntity(4, 3, var11));
      var14.add(new DriverUpdateEntity(4, 4, var12));
      this.updateGeneralDriveData(var14);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarNumber() {
      ArrayList var2 = new ArrayList();
      byte[] var1 = this.mCanBusInfoByte;
      var2.add(new DriverUpdateEntity(3, 8, new String(Arrays.copyOfRange(var1, 2, var1.length))));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarStatusInfo() {
      int[] var1 = this.mCanBusInfoInt;
      var1[8] = this.blockBit(var1[8], 6);
      int[] var2 = this.carStateIntArray;
      var1 = this.mCanBusInfoInt;
      if (var2 != var1) {
         this.carStateIntArray = var1;
         ArrayList var3 = new ArrayList();
         var3.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6] + "L/100km"));
         var3.add(new DriverUpdateEntity(3, 2, this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11] + "V"));
         var3.add(new DriverUpdateEntity(3, 3, this.mCanBusInfoInt[9] + "L"));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private void setCarWarningInfo() {
      ArrayList var6 = new ArrayList();
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      int var1 = var2;
      if (var2 > 16) {
         var1 = 16;
      }

      String[] var4 = new String[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         int var3 = var2 + 3;
         if (var5[var3] >= this.arrReportsHw.length) {
            var4[var2] = " NULL ";
         } else {
            var4[var2] = this.mContext.getResources().getString(this.arrReportsHw[var5[var3]]);
            var6.add(new WarningEntity(var4[var2]));
         }
      }

      this.mList1.clear();
      this.mList1.addAll(var6);
      GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
      this.updateWarningActivity((Bundle)null);
   }

   private void setCarWarningInfo0x75() {
      ArrayList var6 = new ArrayList();
      int[] var4 = this.mCanBusInfoInt;
      int var2 = var4[2];
      int var1 = var2;
      if (var2 > 7) {
         var1 = 7;
      }

      String[] var5 = new String[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         int var3 = var2 + 3;
         if (var4[var3] >= this.arrStartStopHw.length) {
            var5[var2] = " NULL ";
         } else {
            var5[var2] = this.mContext.getResources().getString(this.arrStartStopHw[var4[var3]]);
            var6.add(new WarningEntity(var5[var2]));
         }
      }

      this.mList2.clear();
      this.mList2.addAll(var6);
      GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
      this.updateWarningActivity((Bundle)null);
   }

   private void setCarWarningInfo0x77() {
      ArrayList var4 = new ArrayList();
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      int var1 = var2;
      if (var2 > 7) {
         var1 = 7;
      }

      String[] var3 = new String[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         if (var5[var2 + 2] >= this.arrConvConsumers.length) {
            var3[var2] = " NULL ";
         } else {
            var3[var2] = this.mContext.getResources().getString(this.arrConvConsumers[var5[var2 + 3]]);
            var4.add(new WarningEntity(var3[var2]));
         }
      }

      this.mList3.clear();
      this.mList3.addAll(var4);
      GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
      this.updateWarningActivity((Bundle)null);
   }

   private void setConvenienceConsumers() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[2];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        var2 = "";
                     } else {
                        var2 = "two";
                     }
                  } else {
                     var2 = "three_two";
                  }
               } else {
                  var2 = "one";
               }
            } else {
               var2 = "half";
            }
         } else {
            var2 = "three_eighths";
         }
      } else {
         var2 = "quarter";
      }

      var1 = var3[3];
      String var5;
      if (var3[11] == 0) {
         var5 = "feul_unit_1";
      } else {
         var5 = "feul_unit_2";
      }

      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(17, 0, var2));
      var4.add(new SettingUpdateEntity(17, 1, var1));
      var4.add(new SettingUpdateEntity(17, 2, var5));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorOrWindowState(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131768144);
      } else if (var1 <= 10 && var1 >= 1) {
         var2 = var1 * 10 + "%";
      } else {
         var2 = this.mContext.getResources().getString(2131770860);
      }

      ArrayList var3 = new ArrayList();
      var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "airSetting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "airSetting", "door_windows_open_state"), var2)).setValueStr(true));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDriveModeOffload() {
      int var3 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]));
      int var4 = this.mCanBusInfoInt[4];
      int var1 = 0;
      int var5 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(var4, 0, 2));
      int var8 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]));
      var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]));
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]));

      ArrayList var11;
      for(var11 = new ArrayList(); var1 <= 8; ++var1) {
         var11.add(new SettingUpdateEntity(2, var1, (new int[]{var3, var2, var5, var8, var10, var6, var4, var7, var9})[var1]));
      }

      this.updateGeneralSettingData(var11);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDriveModeSelect() {
      int var1 = this.mCanBusInfoInt[3];
      byte var2 = 0;
      byte var10;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 4) {
               if (var1 != 8) {
                  if (var1 != 16) {
                     if (var1 != 32) {
                        if (var1 != 64) {
                           var10 = 0;
                        } else {
                           var10 = 1;
                        }
                     } else {
                        var10 = 2;
                     }
                  } else {
                     var10 = 3;
                  }
               } else {
                  var10 = 4;
               }
            } else {
               var10 = 5;
            }
         } else {
            var10 = 6;
         }
      } else {
         var10 = 7;
      }

      ArrayList var9 = new ArrayList();
      var9.add(new SettingUpdateEntity(0, 0, Integer.valueOf(var10)));
      int var3 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      int var6 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2));
      int var7 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2));
      int var5 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]));

      for(var1 = var2; var1 <= 5; ++var1) {
         var9.add(new SettingUpdateEntity(1, var1, (new int[]{var3, var6, var7, var5, var4, var8})[var1]));
      }

      LogUtil.showLog("0x86");
      this.updateGeneralSettingData(var9);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDriveStatusHybrid() {
      byte[] var14 = this.mCanBusInfoByte;
      double var3 = (double)(var14[2] << 8 & -256 | var14[3] & 255);
      int[] var13 = this.mCanBusInfoInt;
      int var9 = var13[4];
      int var10 = var13[5];
      double var5 = (double)(var14[6] << 8 & -256 | var14[7] & 255);
      int var8 = var13[8];
      int var7 = var13[9];
      int var11 = var14[10];
      double var1 = (double)(var14[11] & 255 | var11 << 8 & -256);
      var11 = var13[12];
      int var12 = var13[13];
      ArrayList var15 = new ArrayList();
      var15.add(new DriverUpdateEntity(0, 5, (float)(var3 * 0.1) + " kWh/100km"));
      var15.add(new DriverUpdateEntity(0, 6, (float)(var9 * 256 + var10) + "km"));
      var15.add(new DriverUpdateEntity(2, 5, (float)(var5 * 0.1) + " kWh/100km"));
      var15.add(new DriverUpdateEntity(2, 6, (float)(var8 * 256 + var7) + "km"));
      var15.add(new DriverUpdateEntity(1, 5, (float)(var1 * 0.1) + " kWh/100km"));
      var15.add(new DriverUpdateEntity(1, 6, (float)(var11 * 256 + var12) + "km"));
      this.updateGeneralDriveData(var15);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveStatusLongTime() {
      int[] var11 = this.mCanBusInfoInt;
      double var9 = (double)(var11[2] * 256 + var11[3]);
      double var3 = (double)(var11[4] * 256 + var11[5]);
      double var7 = (double)(var11[6] * 256 + var11[7]);
      double var5 = (double)(var11[8] * 256 + var11[9]);
      double var1 = (double)var11[10];
      ArrayList var12 = new ArrayList();
      var12.add(new DriverUpdateEntity(2, 0, (float)(var9 * 0.1) + "l/km"));
      var12.add(new DriverUpdateEntity(2, 1, (float)var3 + "km"));
      var12.add(new DriverUpdateEntity(2, 2, (float)(var7 * 0.1) + "km"));
      var12.add(new DriverUpdateEntity(2, 3, (int)(var5 / 60.0) + " h " + (int)(var5 % 60.0) + "min"));
      var12.add(new DriverUpdateEntity(2, 4, (int)var1 + "km/h"));
      this.updateGeneralDriveData(var12);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveStatusRefuelling() {
      int[] var11 = this.mCanBusInfoInt;
      double var3 = (double)(var11[2] * 256 + var11[3]);
      double var7 = (double)(var11[4] * 256 + var11[5]);
      double var9 = (double)(var11[6] * 256 + var11[7]);
      double var1 = (double)(var11[8] * 256 + var11[9]);
      double var5 = (double)var11[10];
      ArrayList var12 = new ArrayList();
      var12.add(new DriverUpdateEntity(1, 0, (float)(var3 * 0.1) + "l/km"));
      var12.add(new DriverUpdateEntity(1, 1, (float)var7 + "km"));
      var12.add(new DriverUpdateEntity(1, 2, (float)(var9 * 0.1) + "km"));
      var12.add(new DriverUpdateEntity(1, 3, (int)(var1 / 60.0) + " h " + (int)(var1 % 60.0) + "min"));
      var12.add(new DriverUpdateEntity(1, 4, (int)var5 + "km/h"));
      this.updateGeneralDriveData(var12);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriverAssistant() {
      int var2 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]));
      int var4 = this.mCanBusInfoInt[9];
      int var1 = 0;
      int var13 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(var4, 0, 5));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]));
      int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]));
      var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]));
      int var11 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2));
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13]));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14]));
      int var14 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]));

      ArrayList var15;
      for(var15 = new ArrayList(); var1 <= 12; ++var1) {
         var15.add(new SettingUpdateEntity(5, var1, (new int[]{var2, var3, var13, var8, var12, var4, var11, var9, var6, var7, var10, var5, var14})[var1]));
      }

      this.updateGeneralSettingData(var15);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDyVoiceAction(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1950015300:
            if (var1.equals("air_preset_mode_on_to_ac_max")) {
               var2 = 0;
            }
            break;
         case -1785642657:
            if (var1.equals("air_rear_power_on")) {
               var2 = 1;
            }
            break;
         case -1607857989:
            if (var1.equals("air_preset_mode_on_to_manual")) {
               var2 = 2;
            }
            break;
         case -1585736007:
            if (var1.equals("air_rear_right_seat_heat_to")) {
               var2 = 3;
            }
            break;
         case -1567175431:
            if (var1.equals("air_wind_blow_foot")) {
               var2 = 4;
            }
            break;
         case -1567125909:
            if (var1.equals("air_wind_blow_head")) {
               var2 = 5;
            }
            break;
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 6;
            }
            break;
         case -1412208249:
            if (var1.equals("air.ac.on")) {
               var2 = 7;
            }
            break;
         case -1409945485:
            if (var1.equals("air_left_seat_heat_to")) {
               var2 = 8;
            }
            break;
         case -1326915554:
            if (var1.equals("ac.temperature.max")) {
               var2 = 9;
            }
            break;
         case -1326915316:
            if (var1.equals("ac.temperature.min")) {
               var2 = 10;
            }
            break;
         case -1256523009:
            if (var1.equals("air_left_seat_cold_to")) {
               var2 = 11;
            }
            break;
         case -1226270570:
            if (var1.equals("ac.open")) {
               var2 = 12;
            }
            break;
         case -1092999012:
            if (var1.equals("air.in.out.cycle.auto")) {
               var2 = 13;
            }
            break;
         case -940325874:
            if (var1.equals("air_sync_on")) {
               var2 = 14;
            }
            break;
         case -866529054:
            if (var1.equals("air.in.out.cycle.off")) {
               var2 = 15;
            }
            break;
         case -854978899:
            if (var1.equals("air_rear_lock_on")) {
               var2 = 16;
            }
            break;
         case -828782905:
            if (var1.equals("air.ac.off")) {
               var2 = 17;
            }
            break;
         case -734542239:
            if (var1.equals("air_rear_lock_off")) {
               var2 = 18;
            }
            break;
         case -553279150:
            if (var1.equals("air_steering_wheel_off")) {
               var2 = 19;
            }
            break;
         case -112216342:
            if (var1.equals("air_clean_on")) {
               var2 = 20;
            }
            break;
         case 103110984:
            if (var1.equals("air_right_seat_heat_to")) {
               var2 = 21;
            }
            break;
         case 256533460:
            if (var1.equals("air_right_seat_cold_to")) {
               var2 = 22;
            }
            break;
         case 479652335:
            if (var1.equals("air_rear_power_off")) {
               var2 = 23;
            }
            break;
         case 629126444:
            if (var1.equals("ac.close")) {
               var2 = 24;
            }
            break;
         case 816260548:
            if (var1.equals("air_clean_off")) {
               var2 = 25;
            }
            break;
         case 890880226:
            if (var1.equals("air_rear_left_seat_heat_to")) {
               var2 = 26;
            }
            break;
         case 914668832:
            if (var1.equals("air_sync_off")) {
               var2 = 27;
            }
            break;
         case 956867879:
            if (var1.equals("air_rear_wind_level_to")) {
               var2 = 28;
            }
            break;
         case 1218099172:
            if (var1.equals("air_preset_mode_on_to_auto")) {
               var2 = 29;
            }
            break;
         case 1225772921:
            if (var1.equals("ac.windlevel.to")) {
               var2 = 30;
            }
            break;
         case 1377338037:
            if (var1.equals("air_rear_auto")) {
               var2 = 31;
            }
            break;
         case 1481217153:
            if (var1.equals("ac.temperature.to")) {
               var2 = 32;
            }
            break;
         case 1496068108:
            if (var1.equals("air.in.out.cycle.on")) {
               var2 = 33;
            }
            break;
         case 1733069433:
            if (var1.equals("air_preset_mode_on_to_max_front")) {
               var2 = 34;
            }
            break;
         case 1921814940:
            if (var1.equals("air_steering_wheel_on")) {
               var2 = 35;
            }
            break;
         case 1959044539:
            if (var1.equals("air_wind_blow_window")) {
               var2 = 36;
            }
      }

      switch (var2) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 1});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 3});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 44, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 4:
            if (GeneralAirData.front_left_blow_foot) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 1});
            }
            break;
         case 5:
            if (GeneralAirData.front_left_blow_head) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 1});
            }
            break;
         case 6:
            if (GeneralAirData.front_defog) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 1});
            }
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 1});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -1});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -1});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -1});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -2});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -2});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -2});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 1});
            break;
         case 13:
            if (GeneralAirData.in_out_auto_cycle == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 1});
            }
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 1});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 2});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 1});
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 0});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 0});
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 0});
            break;
         case 20:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 1});
            break;
         case 21:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 23:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 0});
            break;
         case 24:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 0});
            break;
         case 25:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 0});
            break;
         case 26:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 43, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 27:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 0});
            break;
         case 28:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 29:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
            break;
         case 30:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 31:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 40, 1});
            break;
         case 32:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte)(Integer.parseInt(VoiceControlData.voiceValue) * 2)});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte)(Integer.parseInt(VoiceControlData.voiceValue) * 2)});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte)(Integer.parseInt(VoiceControlData.voiceValue) * 2)});
            break;
         case 33:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 1});
            break;
         case 34:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 2});
            break;
         case 35:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 1});
            break;
         case 36:
            if (GeneralAirData.front_left_blow_window) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 1});
            }
      }

   }

   private void setElectricDrive() {
      int var1;
      int var3;
      int var4;
      int[] var6;
      byte var7;
      label35: {
         var6 = this.mCanBusInfoInt;
         var4 = var6[6];
         var3 = var6[5];
         var1 = var6[3];
         if (var1 != 5) {
            if (var1 == 10) {
               var7 = 1;
               break label35;
            }

            if (var1 == 13) {
               var7 = 2;
               break label35;
            }

            if (var1 == 255) {
               var7 = 3;
               break label35;
            }
         }

         var7 = 0;
      }

      int var2;
      label26: {
         int var5 = var6[4];
         var2 = 15;
         if (var5 != 254) {
            if (var5 == 255) {
               break label26;
            }

            if (var5 >= 32 && var5 < 60) {
               var2 = var5 / 2 - 15;
               break label26;
            }
         }

         var2 = 0;
      }

      ArrayList var8 = new ArrayList();
      var8.add(new SettingUpdateEntity(19, 0, Integer.valueOf(var7)));
      var8.add(new SettingUpdateEntity(19, 1, var2));
      var1 = var4 * 10;
      var8.add((new SettingUpdateEntity(19, 2, var1)).setProgress(var1));
      var8.add(new SettingUpdateEntity(19, 3, var3));
      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void setEnvironmentLighting() {
      int[] var9 = this.mCanBusInfoInt;
      int var5 = var9[4];
      int var6 = var9[7];
      byte var3 = 3;
      int var4 = var9[3];
      int var8 = var9[5];
      int var7 = var9[6];
      int var1 = var9[8];
      int var2 = 0;
      int[] var10 = new int[]{var8, var7, var1};
      ArrayList var11 = new ArrayList();

      while(true) {
         var1 = var3;
         if (var2 > 2) {
            while(var1 <= 5) {
               var2 = var1 - 3;
               var11.add((new SettingUpdateEntity(8, var1, var10[var2])).setProgress(var10[var2]));
               ++var1;
            }

            this.updateGeneralSettingData(var11);
            this.updateSettingActivity((Bundle)null);
            return;
         }

         var11.add(new SettingUpdateEntity(8, var2, (new int[]{var5, var6 - 1, var4})[var2]));
         ++var2;
      }
   }

   private void setEscSystem() {
      int var1 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2));
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(3, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setFrontAirData(byte[] var1) {
      this.setDoorOrWindowState(this.mCanBusInfoInt[10]);
      this.mCanBusInfoInt[10] = 0;
      if (!this.isAirDataNoChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
         GeneralAirData.seat_steering_wheel_synchronization = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
         GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
         int[] var5 = this.mCanBusInfoInt;
         int var2 = var5[6];
         frontWindLv = var5[7];
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_auto_wind_model = false;
         GeneralAirData.front_defog = false;
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 5) {
                     if (var2 != 6) {
                        switch (var2) {
                           case 11:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              break;
                           case 12:
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_right_blow_foot = true;
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              break;
                           case 13:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_right_blow_head = true;
                              break;
                           case 14:
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_right_blow_foot = true;
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_right_blow_head = true;
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
            } else {
               GeneralAirData.front_defog = true;
            }
         } else {
            GeneralAirData.front_auto_wind_model = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         var5 = this.mCanBusInfoInt;
         var2 = var5[8];
         leftFrontTemp = (float)var2;
         rightFrontTemp = (float)var5[9];
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(var2);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
         var5 = this.mCanBusInfoInt;
         var2 = var5[12];
         rearTemp = (float)var2 * 1.0F;
         outDoorTemp = var5[13];
         GeneralAirData.rear_temperature = this.resolveLeftAndRightTemp(var2);
         GeneralAirData.rear_left_blow_window = false;
         GeneralAirData.rear_left_blow_head = false;
         GeneralAirData.rear_left_blow_foot = false;
         GeneralAirData.rear_right_blow_window = false;
         GeneralAirData.rear_right_blow_head = false;
         GeneralAirData.rear_right_blow_foot = false;
         rearWindBlow = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 4);
         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
         if (var2 != 3) {
            if (var2 != 5) {
               if (var2 == 6) {
                  GeneralAirData.rear_right_blow_head = true;
                  GeneralAirData.rear_left_blow_head = true;
               }
            } else {
               GeneralAirData.rear_right_blow_head = true;
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_right_blow_foot = true;
            }
         } else {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
         }

         boolean var4 = this.isOnlyRearAirDataChange();
         boolean var3 = this.isOnlyOutDoorDataChange();
         if (this.getCurrentEachCanId() == 99) {
            Log.e("ooo", "车型匹配" + this.isLockAirViewToShow);
            if (this.isLockAirViewToShow) {
               return;
            }
         }

         if (!var4 && !var3) {
            this.updateAirActivity(this.mContext, 1001);
         } else if (var4 && !this.isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "is_rear_air_temp_change_mqb", rearTemp);
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_lv_change", GeneralAirData.rear_wind_level);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_air_power", GeneralAirData.rear_power);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_auto", GeneralAirData.rear_auto);
            SharePreUtil.setIntValue(this.mContext, "is_rear_left_heat", GeneralAirData.rear_left_seat_heat_level);
            SharePreUtil.setIntValue(this.mContext, "is_rear_right_heat", GeneralAirData.rear_right_seat_heat_level);
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_blow", rearWindBlow);
            this.updateAirActivity(this.mContext, 1003);
         }

         if (var3) {
            SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", (float)outDoorTemp);
            this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
         }

      }
   }

   private void setFrontRearRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mDisableData = 12;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(11, this.distanceToLocation(this.mCanBusInfoInt[2]), this.distanceToLocation(this.mCanBusInfoInt[3]), this.distanceToLocation(this.mCanBusInfoInt[4]), this.distanceToLocation(this.mCanBusInfoInt[5]));
      RadarInfoUtil.setFrontRadarLocationData(11, this.distanceToLocation(this.mCanBusInfoInt[6]), this.distanceToLocation(this.mCanBusInfoInt[7]), this.distanceToLocation(this.mCanBusInfoInt[8]), this.distanceToLocation(this.mCanBusInfoInt[9]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setHybrid() {
      byte var1;
      label26: {
         Intent var4 = new Intent("com.android.systemui.statusbar.action.CAROUTTEMCHANGE2");
         this.mContext.sendBroadcast(var4);
         int[] var3 = this.mCanBusInfoInt;
         var1 = 3;
         int var2 = var3[3];
         if (var2 != 8) {
            if (var2 == 16) {
               var4.putExtra("Gte", "");
               this.mContext.sendBroadcast(var4);
               break label26;
            }

            if (var2 == 32) {
               var1 = 2;
               var4.putExtra("Gte", "");
               this.mContext.sendBroadcast(var4);
               break label26;
            }

            if (var2 == 64) {
               var1 = 1;
               var4.putExtra("Gte", "");
               this.mContext.sendBroadcast(var4);
               break label26;
            }

            if (var2 == 128) {
               this.startSettingActivity(this.mContext, 16, 0);
            }
         } else {
            var4.putExtra("Gte", "GTE");
            this.mContext.sendBroadcast(var4);
         }

         var1 = 0;
      }

      ArrayList var5 = new ArrayList();
      var5.add(new SettingUpdateEntity(16, 0, Integer.valueOf(var1)));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private void setHybridInfo() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[4] == 13 && var1[5] == 0) {
         if (!this.mCurrentCaseD) {
            this.startSettingActivity(this.mContext, 16, 0);
            this.mCurrentCaseD = true;
            this.mCurrentCaseC = false;
         } else {
            this.mCurrentCaseD = false;
         }
      }

   }

   private void setHybridView() {
      int var1;
      if (this.mCanBusInfoInt[4] == 0) {
         GeneralHybirdData.isShowMotor = false;
         GeneralHybirdData.isShowBattery = false;
         GeneralHybirdData.energyDirection = 0;
         GeneralHybirdData.wheelTrack = 0;
      } else {
         GeneralHybirdData.isShowBattery = true;
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 1 && var1 != 2 && var1 != 3 && var1 != 7) {
            GeneralHybirdData.isShowMotor = false;
         } else {
            GeneralHybirdData.isShowMotor = true;
         }

         var1 = this.mCanBusInfoInt[4];
         if (var1 == 4) {
            GeneralHybirdData.energyDirection = 0;
         } else if (var1 != 1 && var1 != 3 && var1 != 6) {
            GeneralHybirdData.energyDirection = 2;
         } else {
            GeneralHybirdData.energyDirection = 1;
         }

         switch (this.mCanBusInfoInt[4]) {
            case 1:
            case 4:
               GeneralHybirdData.wheelTrack = 0;
               break;
            case 2:
               GeneralHybirdData.wheelTrack = 1;
               break;
            case 3:
            case 6:
               GeneralHybirdData.wheelTrack = 2;
               break;
            case 5:
               GeneralHybirdData.wheelTrack = 3;
               break;
            case 7:
               GeneralHybirdData.wheelTrack = 4;
         }
      }

      GeneralHybirdData.title = this.mContext.getString(2131768208);
      var1 = this.mCanBusInfoInt[10];
      if (var1 == 0) {
         GeneralHybirdData.powerBatteryLevel = 0;
      } else if (var1 > 0 && var1 <= 10) {
         GeneralHybirdData.powerBatteryLevel = 1;
      } else if (var1 > 10 && var1 <= 20) {
         GeneralHybirdData.powerBatteryLevel = 2;
      } else if (var1 > 20 && var1 <= 30) {
         GeneralHybirdData.powerBatteryLevel = 3;
      } else if (var1 > 30 && var1 <= 40) {
         GeneralHybirdData.powerBatteryLevel = 4;
      } else if (var1 > 40 && var1 <= 50) {
         GeneralHybirdData.powerBatteryLevel = 5;
      } else if (var1 > 50 && var1 <= 60) {
         GeneralHybirdData.powerBatteryLevel = 6;
      } else if (var1 > 60 && var1 <= 70) {
         GeneralHybirdData.powerBatteryLevel = 7;
      } else if (var1 > 70 && var1 <= 80) {
         GeneralHybirdData.powerBatteryLevel = 8;
      } else if (var1 > 80 && var1 <= 90) {
         GeneralHybirdData.powerBatteryLevel = 9;
      } else if (var1 > 90 && var1 <= 100) {
         GeneralHybirdData.powerBatteryLevel = 10;
      }

      GeneralHybirdData.powerBatteryValue = this.mCanBusInfoInt[10];
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = (new StringBuilder()).append(this.mContext.getString(2131769326)).append(":");
      int[] var4 = this.mCanBusInfoInt;
      var2.add(var3.append(var4[2] * 256 + var4[3]).append("km").toString());
      var3 = (new StringBuilder()).append(this.mContext.getString(2131768158)).append(":");
      var4 = this.mCanBusInfoInt;
      var2.add(var3.append(var4[5] * 256 + var4[6]).append("km").toString());
      StringBuilder var7 = (new StringBuilder()).append(this.mContext.getString(2131770875)).append(":");
      int[] var6 = this.mCanBusInfoInt;
      var2.add(var7.append(var6[7] * 256 + var6[8]).append("km").toString());
      var2.add(this.mContext.getString(2131770876) + ":" + this.mCanBusInfoInt[9] + "%");
      if (var2.size() != 0) {
         GeneralHybirdData.valueList = var2;
      }

      Bundle var5 = new Bundle();
      var5.putString("head", Integer.toHexString(24));
      this.updateMqbHybirdActivity(var5);
   }

   private void setLeftRightRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mDisableData = 12;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRightRadarLocationData(11, this.distanceToLocation(this.mCanBusInfoInt[2]), this.distanceToLocation(this.mCanBusInfoInt[3]), this.distanceToLocation(this.mCanBusInfoInt[4]), this.distanceToLocation(this.mCanBusInfoInt[5]));
      RadarInfoUtil.setLeftRadarLocationData(11, this.distanceToLocation(this.mCanBusInfoInt[6]), this.distanceToLocation(this.mCanBusInfoInt[7]), this.distanceToLocation(this.mCanBusInfoInt[8]), this.distanceToLocation(this.mCanBusInfoInt[9]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLightInfo() {
      int var5 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]));
      int[] var13 = this.mCanBusInfoInt;
      int var11 = var13[9];
      int var12 = var13[10];
      int var9 = var13[6];
      int var1 = var13[7];
      int var10 = var13[8];
      var13 = new int[]{var11, var12, var9, var1, var10};
      ArrayList var14 = new ArrayList();

      for(var1 = 0; var1 <= 6; ++var1) {
         var14.add(new SettingUpdateEntity(7, var1, (new int[]{var5, var6, var8, var7, var2, var3, var4})[var1]));
      }

      for(var1 = 7; var1 <= 11; ++var1) {
         var2 = var1 - 7;
         var14.add((new SettingUpdateEntity(7, var1, var13[var2])).setProgress(var13[var2]));
      }

      this.updateGeneralSettingData(var14);
      this.updateSettingActivity((Bundle)null);
   }

   private void setMultifunctionalDisplay() {
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
      int var11 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]));
      int var1 = 0;

      ArrayList var12;
      for(var12 = new ArrayList(); var1 <= 9; ++var1) {
         var12.add(new SettingUpdateEntity(11, var1, (new int[]{var9, var10, var3, var11, var6, var2, var5, var8, var4, var7})[var1]));
      }

      this.updateGeneralSettingData(var12);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOffRoadData() {
      String var1;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
         var1 = "open";
      } else {
         var1 = "close";
      }

      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(6, 9, var1));
      var2.add(new SettingUpdateEntity(6, 10, this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]))));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOpenAndClose() {
      int var6 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      int var2 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]));
      int var1 = 0;

      ArrayList var8;
      for(var8 = new ArrayList(); var1 <= 5; ++var1) {
         var8.add(new SettingUpdateEntity(10, var1, (new int[]{var6, var2, var7, var5, var3, var4})[var1]));
      }

      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalCameraStatusInfo() {
      isPanoramicRadarClose = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      ArrayList var3 = new ArrayList();
      boolean var1 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      boolean var2 = false;
      var3.add(new PanoramicBtnUpdateEntity(0, var1));
      if (this.mCanBusInfoInt[3] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new PanoramicBtnUpdateEntity(1, var1));
      if (this.mCanBusInfoInt[3] == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new PanoramicBtnUpdateEntity(2, var1));
      if (this.mCanBusInfoInt[3] == 3) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new PanoramicBtnUpdateEntity(3, var1));
      var1 = var2;
      if (this.mCanBusInfoInt[3] == 9) {
         var1 = true;
      }

      var3.add(new PanoramicBtnUpdateEntity(4, var1));
      GeneralParkData.dataList = var3;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setParkingAndManoeuvring() {
      int var9 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
      int[] var11 = this.mCanBusInfoInt;
      int var10 = var11[4];
      int var8 = var11[5];
      int var6 = var11[6];
      int var7 = var11[7];
      int var1 = 1;
      int[] var12 = new int[]{var10, var8, var6, var7};
      ArrayList var13 = new ArrayList();
      var13.add(new SettingUpdateEntity(6, 0, var9));

      while(var1 <= 4) {
         var13.add(new SettingUpdateEntity(6, var1, (new int[]{var5, var4, var2, var3})[var1 - 1]));
         ++var1;
      }

      for(var1 = 5; var1 <= 8; ++var1) {
         var2 = var1 - 5;
         var13.add((new SettingUpdateEntity(6, var1, var12[var2])).setProgress(var12[var2]));
      }

      var13.add(new SettingUpdateEntity(6, 11, Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))));
      this.updateGeneralSettingData(var13);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearviewMirrorAndWiper() {
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]));
      int var1 = 0;

      ArrayList var7;
      for(var7 = new ArrayList(); var1 <= 4; ++var1) {
         var7.add(new SettingUpdateEntity(9, var1, (new int[]{var3, var5, var2, var6, var4})[var1]));
      }

      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   private void setReversingVideo() {
      int[] var8 = this.mCanBusInfoInt;
      int var5 = var8[4];
      int var4 = 0;
      int var1;
      if (var5 <= 70 && var5 >= 30) {
         var1 = var5;
      } else {
         var1 = 0;
      }

      int var2 = var8[5];
      if (var2 > 70 || var2 < 30) {
         var2 = 0;
      }

      int var3 = var8[6];
      if (var3 > 70 || var3 < 30) {
         var3 = 0;
      }

      int var7 = this.mCanBusInfoInt[5];
      int var6 = this.mCanBusInfoInt[6];

      ArrayList var9;
      for(var9 = new ArrayList(); var4 <= 2; ++var4) {
         var9.add((new SettingUpdateEntity(15, var4, (new String[]{String.valueOf(var5), String.valueOf(var7), String.valueOf(var6)})[var4])).setProgress((new int[]{var1, var2, var3})[var4] - 30));
      }

      this.updateGeneralSettingData(var9);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingInfo() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "electric_drive_charging_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "electric_drive_charging_settings", "_206_new_function"), this.get0x11Data9State())).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setType() {
      int var1 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[4];
      int var2 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(var4[3], 5, 2));
      carSpeedWarning = var1;
      ArrayList var5 = new ArrayList();
      var5.add(new SettingUpdateEntity(4, 1, var1));
      var5.add((new SettingUpdateEntity(4, 2, var3)).setProgress(var3 - 30));
      var5.add(new SettingUpdateEntity(4, 3, var2));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTypeDataInfo() {
      ArrayList var5 = new ArrayList();
      byte var2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      byte var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      byte var4 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      byte var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      String var6 = this.getTisWarmMsg(var2);
      int[] var7 = this.mCanBusInfoInt;
      String var8 = this.getTirePressure(var7[12], var7[13]);
      var7 = this.mCanBusInfoInt;
      var5.add(this.getTireEntity(0, var6, var8, this.getTirePressure(var7[4], var7[5])));
      var6 = this.getTisWarmMsg(var1);
      var7 = this.mCanBusInfoInt;
      String var9 = this.getTirePressure(var7[14], var7[15]);
      int[] var10 = this.mCanBusInfoInt;
      var5.add(this.getTireEntity(1, var6, var9, this.getTirePressure(var10[6], var10[7])));
      var6 = this.getTisWarmMsg(var4);
      var7 = this.mCanBusInfoInt;
      var8 = this.getTirePressure(var7[16], var7[17]);
      var7 = this.mCanBusInfoInt;
      var5.add(this.getTireEntity(2, var6, var8, this.getTirePressure(var7[8], var7[9])));
      var6 = this.getTisWarmMsg(var3);
      var7 = this.mCanBusInfoInt;
      var8 = this.getTirePressure(var7[18], var7[19]);
      var7 = this.mCanBusInfoInt;
      var5.add(this.getTireEntity(3, var6, var8, this.getTirePressure(var7[10], var7[11])));
      GeneralTireData.dataList = var5;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setUnit() {
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
      int var8 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2));
      int var4 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
      int var2 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      int var1 = 0;

      ArrayList var9;
      for(var9 = new ArrayList(); var1 <= 6; ++var1) {
         var9.add(new SettingUpdateEntity(12, var1, (new int[]{var3, var6, var7, var8, var4, var5, var2})[var1]));
      }

      this.updateGeneralSettingData(var9);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void updateMqbHybirdActivity(Bundle var1) {
      if (this.getActivity() != null && this.getActivity() instanceof MqbHybirdActivity) {
         this.updateActivity(var1);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      AMapUtils.getInstance().startAMap(var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendSourceMsg1("AUX");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendSourceMsg1("BT MUSIC");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      var1 = DataHandleUtils.makeBytesFixedLength(new byte[]{0}, 25);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -107}, var1));
      var1 = DataHandleUtils.makeBytesFixedLength(new byte[]{0}, 25);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -106}, var1));
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      String var5 = new String(var1);
      var1 = new byte[0];

      byte[] var9;
      label23: {
         try {
            var9 = var5.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            break label23;
         }

         var1 = var9;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -107, 0}, var1), 27));
      MyLog.temporaryTracking("时间数据：" + var4);
      StringBuilder var8 = new StringBuilder();
      var4 /= 1000;
      var5 = var8.append(var4 / 60).append(":").append(var4 % 60).toString();
      var1 = new byte[0];

      label18: {
         try {
            var9 = var5.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
            break label18;
         }

         var1 = var9;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -106, 0}, var1), 27));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      Exception var10000;
      boolean var10001;
      if (var3 == 30) {
         try {
            this.setCarMaintenance();
            return;
         } catch (Exception var40) {
            var10000 = var40;
            var10001 = false;
         }
      } else if (var3 != 31) {
         if (var3 == 49) {
            try {
               this.setFrontAirData(var2);
               return;
            } catch (Exception var38) {
               var10000 = var38;
               var10001 = false;
            }
         } else if (var3 != 100) {
            if (var3 != 193) {
               if (var3 != 232) {
                  if (var3 == 240) {
                     try {
                        this.setVersionInfo();
                        return;
                     } catch (Exception var34) {
                        var10000 = var34;
                        var10001 = false;
                     }
                  } else if (var3 != 53) {
                     if (var3 == 54) {
                        try {
                           this.setCarLightInfo();
                           return;
                        } catch (Exception var32) {
                           var10000 = var32;
                           var10001 = false;
                        }
                     } else if (var3 != 65) {
                        if (var3 == 66) {
                           try {
                              this.setLeftRightRadar();
                              return;
                           } catch (Exception var30) {
                              var10000 = var30;
                              var10001 = false;
                           }
                        } else {
                           switch (var3) {
                              case 17:
                                 try {
                                    this.saveAccState();
                                    this.keyControl0x11();
                                    this.setTrackInfo();
                                    this.setSettingInfo();
                                    if (this.isSwc0x0cReturn(var2)) {
                                       return;
                                    }
                                 } catch (Exception var43) {
                                    var10000 = var43;
                                    var10001 = false;
                                    break;
                                 }

                                 try {
                                    this.dccOffOnCtrl();
                                    this.setHybridInfo();
                                    this.updateSpeedInfo(this.mCanBusInfoInt[3]);
                                    return;
                                 } catch (Exception var13) {
                                    var10000 = var13;
                                    var10001 = false;
                                    break;
                                 }
                              case 18:
                                 try {
                                    if (this.isDoorMsgReturn(var2)) {
                                       return;
                                    }
                                 } catch (Exception var42) {
                                    var10000 = var42;
                                    var10001 = false;
                                    break;
                                 }

                                 try {
                                    this.setCarDoorInfo();
                                    this.setOffRoadData();
                                    this.setCarStatusInfo();
                                    return;
                                 } catch (Exception var12) {
                                    var10000 = var12;
                                    var10001 = false;
                                    break;
                                 }
                              case 19:
                                 try {
                                    this.serDriveStatusStart();
                                    return;
                                 } catch (Exception var11) {
                                    var10000 = var11;
                                    var10001 = false;
                                    break;
                                 }
                              case 20:
                                 try {
                                    this.setDriveStatusLongTime();
                                    return;
                                 } catch (Exception var10) {
                                    var10000 = var10;
                                    var10001 = false;
                                    break;
                                 }
                              case 21:
                                 try {
                                    this.setDriveStatusRefuelling();
                                    return;
                                 } catch (Exception var9) {
                                    var10000 = var9;
                                    var10001 = false;
                                    break;
                                 }
                              case 22:
                                 try {
                                    if (!this.isDataChange(var4)) {
                                       return;
                                    }
                                 } catch (Exception var41) {
                                    var10000 = var41;
                                    var10001 = false;
                                    break;
                                 }

                                 try {
                                    this.setConvenienceConsumers();
                                    return;
                                 } catch (Exception var8) {
                                    var10000 = var8;
                                    var10001 = false;
                                    break;
                                 }
                              case 23:
                                 try {
                                    this.setDriveStatusHybrid();
                                    return;
                                 } catch (Exception var7) {
                                    var10000 = var7;
                                    var10001 = false;
                                    break;
                                 }
                              case 24:
                                 try {
                                    this.setHybridView();
                                    return;
                                 } catch (Exception var6) {
                                    var10000 = var6;
                                    var10001 = false;
                                    break;
                                 }
                              case 25:
                                 try {
                                    this.setCarDetailedInfo();
                                    return;
                                 } catch (Exception var5) {
                                    var10000 = var5;
                                    var10001 = false;
                                    break;
                                 }
                              default:
                                 switch (var3) {
                                    case 69:
                                       try {
                                          if (!this.isDataChange(var4)) {
                                             return;
                                          }
                                       } catch (Exception var47) {
                                          var10000 = var47;
                                          var10001 = false;
                                          break;
                                       }

                                       try {
                                          this.setParkingAndManoeuvring();
                                          return;
                                       } catch (Exception var18) {
                                          var10000 = var18;
                                          var10001 = false;
                                          break;
                                       }
                                    case 70:
                                       try {
                                          if (!this.isDataChange(var4)) {
                                             return;
                                          }
                                       } catch (Exception var46) {
                                          var10000 = var46;
                                          var10001 = false;
                                          break;
                                       }

                                       try {
                                          this.setType();
                                          return;
                                       } catch (Exception var17) {
                                          var10000 = var17;
                                          var10001 = false;
                                          break;
                                       }
                                    case 71:
                                       try {
                                          if (!this.isDataChange(var4)) {
                                             return;
                                          }
                                       } catch (Exception var45) {
                                          var10000 = var45;
                                          var10001 = false;
                                          break;
                                       }

                                       try {
                                          this.setDriverAssistant();
                                          return;
                                       } catch (Exception var16) {
                                          var10000 = var16;
                                          var10001 = false;
                                          break;
                                       }
                                    case 72:
                                       try {
                                          this.setTypeDataInfo();
                                          return;
                                       } catch (Exception var15) {
                                          var10000 = var15;
                                          var10001 = false;
                                          break;
                                       }
                                    case 73:
                                       try {
                                          if (!this.isDataChange(var4)) {
                                             return;
                                          }
                                       } catch (Exception var44) {
                                          var10000 = var44;
                                          var10001 = false;
                                          break;
                                       }

                                       try {
                                          this.setElectricDrive();
                                          return;
                                       } catch (Exception var14) {
                                          var10000 = var14;
                                          var10001 = false;
                                          break;
                                       }
                                    default:
                                       switch (var3) {
                                          case 103:
                                             try {
                                                if (!this.isDataChange(var4)) {
                                                   return;
                                                }
                                             } catch (Exception var50) {
                                                var10000 = var50;
                                                var10001 = false;
                                                break;
                                             }

                                             try {
                                                this.setEnvironmentLighting();
                                                return;
                                             } catch (Exception var21) {
                                                var10000 = var21;
                                                var10001 = false;
                                                break;
                                             }
                                          case 104:
                                             try {
                                                if (!this.isDataChange(var4)) {
                                                   return;
                                                }
                                             } catch (Exception var49) {
                                                var10000 = var49;
                                                var10001 = false;
                                                break;
                                             }

                                             try {
                                                this.setLightInfo();
                                                return;
                                             } catch (Exception var20) {
                                                var10000 = var20;
                                                var10001 = false;
                                                break;
                                             }
                                          case 105:
                                             try {
                                                if (!this.isDataChange(var4)) {
                                                   return;
                                                }
                                             } catch (Exception var48) {
                                                var10000 = var48;
                                                var10001 = false;
                                                break;
                                             }

                                             try {
                                                this.setRearviewMirrorAndWiper();
                                                return;
                                             } catch (Exception var19) {
                                                var10000 = var19;
                                                var10001 = false;
                                                break;
                                             }
                                          default:
                                             switch (var3) {
                                                case 116:
                                                   try {
                                                      this.setCarWarningInfo();
                                                      return;
                                                   } catch (Exception var25) {
                                                      var10000 = var25;
                                                      var10001 = false;
                                                      break;
                                                   }
                                                case 117:
                                                   try {
                                                      this.setCarWarningInfo0x75();
                                                      return;
                                                   } catch (Exception var24) {
                                                      var10000 = var24;
                                                      var10001 = false;
                                                      break;
                                                   }
                                                case 118:
                                                   try {
                                                      if (!this.isDataChange(var4)) {
                                                         return;
                                                      }
                                                   } catch (Exception var51) {
                                                      var10000 = var51;
                                                      var10001 = false;
                                                      break;
                                                   }

                                                   try {
                                                      this.setMultifunctionalDisplay();
                                                      return;
                                                   } catch (Exception var23) {
                                                      var10000 = var23;
                                                      var10001 = false;
                                                      break;
                                                   }
                                                case 119:
                                                   try {
                                                      this.setCarWarningInfo0x77();
                                                      return;
                                                   } catch (Exception var22) {
                                                      var10000 = var22;
                                                      var10001 = false;
                                                      break;
                                                   }
                                                default:
                                                   switch (var3) {
                                                      case 133:
                                                         try {
                                                            if (!this.isDataChange(var4)) {
                                                               return;
                                                            }
                                                         } catch (Exception var55) {
                                                            var10000 = var55;
                                                            var10001 = false;
                                                            break;
                                                         }

                                                         try {
                                                            this.setEscSystem();
                                                            return;
                                                         } catch (Exception var29) {
                                                            var10000 = var29;
                                                            var10001 = false;
                                                            break;
                                                         }
                                                      case 134:
                                                         try {
                                                            if (!this.isDataChange(var4)) {
                                                               return;
                                                            }
                                                         } catch (Exception var54) {
                                                            var10000 = var54;
                                                            var10001 = false;
                                                            break;
                                                         }

                                                         try {
                                                            this.setDriveModeSelect();
                                                            return;
                                                         } catch (Exception var28) {
                                                            var10000 = var28;
                                                            var10001 = false;
                                                            break;
                                                         }
                                                      case 135:
                                                         try {
                                                            if (!this.isDataChange(var4)) {
                                                               return;
                                                            }
                                                         } catch (Exception var53) {
                                                            var10000 = var53;
                                                            var10001 = false;
                                                            break;
                                                         }

                                                         try {
                                                            CommUtil.printHexStringByTag("fang", "head", this.mCanBusInfoByte);
                                                            this.setDriveModeOffload();
                                                            return;
                                                         } catch (Exception var27) {
                                                            var10000 = var27;
                                                            var10001 = false;
                                                            break;
                                                         }
                                                      case 136:
                                                         try {
                                                            if (this.isSwc0x0dReturn(var2)) {
                                                               return;
                                                            }
                                                         } catch (Exception var52) {
                                                            var10000 = var52;
                                                            var10001 = false;
                                                            break;
                                                         }

                                                         try {
                                                            this.setHybrid();
                                                            return;
                                                         } catch (Exception var26) {
                                                            var10000 = var26;
                                                            var10001 = false;
                                                            break;
                                                         }
                                                      default:
                                                         return;
                                                   }
                                             }
                                       }
                                 }
                           }
                        }
                     } else {
                        try {
                           this.setFrontRearRadar();
                           return;
                        } catch (Exception var31) {
                           var10000 = var31;
                           var10001 = false;
                        }
                     }
                  } else {
                     label491: {
                        try {
                           if (!this.isDataChange(var4)) {
                              return;
                           }
                        } catch (Exception var56) {
                           var10000 = var56;
                           var10001 = false;
                           break label491;
                        }

                        try {
                           this.airSet();
                           return;
                        } catch (Exception var33) {
                           var10000 = var33;
                           var10001 = false;
                        }
                     }
                  }
               } else {
                  label489: {
                     try {
                        if (!this.isDataChange(var4)) {
                           return;
                        }
                     } catch (Exception var57) {
                        var10000 = var57;
                        var10001 = false;
                        break label489;
                     }

                     try {
                        this.setReversingVideo();
                        this.setOriginalCameraStatusInfo();
                        return;
                     } catch (Exception var35) {
                        var10000 = var35;
                        var10001 = false;
                     }
                  }
               }
            } else {
               label488: {
                  try {
                     if (!this.isDataChange(var4)) {
                        return;
                     }
                  } catch (Exception var58) {
                     var10000 = var58;
                     var10001 = false;
                     break label488;
                  }

                  try {
                     this.setUnit();
                     return;
                  } catch (Exception var36) {
                     var10000 = var36;
                     var10001 = false;
                  }
               }
            }
         } else {
            label487: {
               try {
                  if (!this.isDataChange(var4)) {
                     return;
                  }
               } catch (Exception var59) {
                  var10000 = var59;
                  var10001 = false;
                  break label487;
               }

               try {
                  this.setOpenAndClose();
                  return;
               } catch (Exception var37) {
                  var10000 = var37;
                  var10001 = false;
               }
            }
         }
      } else {
         try {
            this.setCarNumber();
            return;
         } catch (Exception var39) {
            var10000 = var39;
            var10001 = false;
         }
      }

      Exception var60 = var10000;
      Log.e("CanBusError", var60.toString());
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.isGpsTime = var12;
      this.bHours = var5;
      this.bMins = var6;
      this.isFormat24H = var10;
      this.bYear2Dig = var2;
      this.bMonth = var3;
      this.bDay = var4;
      this.systemDateFormat = var9;
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      boolean var15 = true;
      if (var7 != 1) {
         var15 = false;
      }

      byte var17 = (byte)(var2 / 3600);
      byte var16 = (byte)(var2 / 60 % 60);
      byte var14 = (byte)(var2 % 60);
      StringBuilder var18 = new StringBuilder();
      if (var15) {
         var11 = "  DVD  ";
      } else {
         var11 = "  CD  ";
      }

      byte[] var19 = var18.append(var11).append(Util.numCompensateZero(var17)).append(":").append(Util.numCompensateZero(var16)).append(":").append(Util.numCompensateZero(var14)).toString().getBytes();
      CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -111}, var19), 28));
      if (this.isFirst) {
         this.isFirst = false;
         CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(new byte[]{22, -110}, 27));
         CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(new byte[]{22, -109}, 27));
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendSourceMsg1("DTV");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   public void initCommand(Context var1) {
      GeneralTireData.isHaveSpareTire = false;
      super.initCommand(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var28 = new byte[0];

      byte[] var29;
      label33: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var21.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var27) {
            var27.printStackTrace();
            break label33;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var28), 28);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label28: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var23.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var26) {
            var26.printStackTrace();
            break label28;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, var28), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label23: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var22.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var25) {
            var25.printStackTrace();
            break label23;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -109, 0}, var28), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
   }

   public void onAMapCallBack(AMapEntity var1) {
      super.onAMapCallBack(var1);
      if (var1.getDestinationDistance() != 0) {
         int var2 = var1.getCarDirection();
         byte var4 = 0;
         byte var5 = 0;
         byte var8;
         if (var2 == 7) {
            var8 = 7;
         } else if (var1.getCarDirection() == 8) {
            var8 = 8;
         } else if (var1.getCarDirection() == 1) {
            var8 = 1;
         } else if (var1.getCarDirection() == 2) {
            var8 = 2;
         } else if (var1.getCarDirection() == 3) {
            var8 = 3;
         } else if (var1.getCarDirection() == 4) {
            var8 = 4;
         } else if (var1.getCarDirection() == 5) {
            var8 = 5;
         } else if (var1.getCarDirection() == 6) {
            var8 = 6;
         } else {
            var8 = 0;
         }

         int var3;
         if (var1.getIcon() == 9) {
            var3 = 1;
         } else if (var1.getIcon() == 5) {
            var3 = 2;
         } else if (var1.getIcon() == 3) {
            var3 = 3;
         } else if (var1.getIcon() == 7) {
            var3 = 4;
         } else if (var1.getIcon() == 6) {
            var3 = 6;
         } else if (var1.getIcon() == 2) {
            var3 = 7;
         } else if (var1.getIcon() == 4) {
            var3 = 8;
         } else {
            var3 = 0;
         }

         if (var1.getDestinationDistance() == 0) {
            this.result = 0;
         } else {
            this.result = Integer.parseInt(this.mDecimalFormat.format((long)(var1.getNextDistance() * 100 / var1.getDestinationDistance())));
         }

         this.sendGdOtherInfo(new byte[]{22, -28, -128, -128, (byte)getMsb(var1.getNextDistance() * 10), (byte)getMidMsb(var1.getNextDistance() * 10), (byte)getMidLsb(var1.getNextDistance() * 10), (byte)getLsb(var1.getNextDistance() * 10), (byte)this.result, (byte)getMsb(var1.getDestinationDistance() * 10), (byte)getMidMsb(var1.getDestinationDistance() * 10), (byte)getMidLsb(var1.getDestinationDistance() * 10), (byte)getLsb(var1.getDestinationDistance() * 10), (byte)var8, (byte)Integer.parseInt(var1.getPlanTime().substring(0, var1.getPlanTime().indexOf(":"))), (byte)Integer.parseInt(var1.getPlanTime().substring(var1.getPlanTime().indexOf(":") + 1)), (byte)Integer.parseInt(var1.getSurplusAllTimeStr().substring(0, var1.getSurplusAllTimeStr().indexOf(":"))), (byte)Integer.parseInt(var1.getSurplusAllTimeStr().substring(var1.getSurplusAllTimeStr().indexOf(":") + 1)), (byte)var3, 0, 0});
         byte[] var7 = var1.getNextWayName().trim().getBytes(StandardCharsets.UTF_8);
         byte[] var6 = new byte[30];
         if (var7.length > 30) {
            for(var2 = var5; var2 < 30; ++var2) {
               var6[var2] = var7[var2];
            }

            this.sendWayName(var6);
         } else if (var7.length < 30) {
            var3 = var7.length;

            for(var2 = var4; var2 < 30 - var3; ++var2) {
               var7 = this.SplicingByte(var7, " ".getBytes(StandardCharsets.UTF_8));
            }

            this.sendWayName(var7);
         } else {
            this.sendWayName(var7);
         }

      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte[] var7 = (var3 + " " + this.getBandUnit(var2)).getBytes();
      var7 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var7), 28);
      byte[] var6 = var2.getBytes();
      var6 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, var6), 27);
      CanbusMsgSender.sendMsg(var7);
      CanbusMsgSender.sendMsg(var6);
      this.sendSourceMsg2(" ", 147);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendSourceMsg1("OFF");
      this.sendSourceMsg2(" ", 146);
      this.sendSourceMsg2(" ", 147);
   }

   public void syncTime() {
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new SetTimeView()).showDialog(this.this$0.getActivity(), this.this$0.getActivity().getString(2131759106), true, true, true, true, true, new SetTimeView.TimeResultListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void result(int var1, int var2, int var3, int var4, int var5) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -53, 1, (byte)var4, (byte)var5, 0, 0, (byte)this.this$1.this$0.isFormat24H, (byte)(var1 - 2000), (byte)var2, (byte)var3, (byte)this.this$1.this$0.systemDateFormat});
                  AbstractBaseActivity var7 = this.this$1.this$0.getActivity();
                  StringBuilder var8 = (new StringBuilder()).append(var1).append("-").append(var2).append("-").append(var3).append("  ").append(var4).append(":").append(var5).append("(");
                  String var6;
                  if (this.this$1.this$0.isFormat24H) {
                     var6 = "24H";
                  } else {
                     var6 = "12H";
                  }

                  Toast.makeText(var7, var8.append(var6).append(")").toString(), 0).show();
               }
            });
         }
      });
   }

   void updateAirSet(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   void updateLanguageSet(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = (var6 + ":" + var7 + "   ").getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, var18), 28);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      var18 = ((var9 & 255) * 256 + (var3 & 255) + "/" + (var4 & 255)).getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -110, 0}, var18), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      this.sendSourceMsg2(" ", 147);
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      this.setDyVoiceAction(var1);
   }

   private static long[] $d2j$hex$b770e98c$decode_J(String src) {
      byte[] d = $d2j$hex$b770e98c$decode_B(src);
      ByteBuffer b = ByteBuffer.wrap(d);
      b.order(ByteOrder.LITTLE_ENDIAN);
      LongBuffer s = b.asLongBuffer();
      long[] data = new long[d.length / 8];
      s.get(data);
      return data;
   }

   private static int[] $d2j$hex$b770e98c$decode_I(String src) {
      byte[] d = $d2j$hex$b770e98c$decode_B(src);
      ByteBuffer b = ByteBuffer.wrap(d);
      b.order(ByteOrder.LITTLE_ENDIAN);
      IntBuffer s = b.asIntBuffer();
      int[] data = new int[d.length / 4];
      s.get(data);
      return data;
   }

   private static short[] $d2j$hex$b770e98c$decode_S(String src) {
      byte[] d = $d2j$hex$b770e98c$decode_B(src);
      ByteBuffer b = ByteBuffer.wrap(d);
      b.order(ByteOrder.LITTLE_ENDIAN);
      ShortBuffer s = b.asShortBuffer();
      short[] data = new short[d.length / 2];
      s.get(data);
      return data;
   }

   private static byte[] $d2j$hex$b770e98c$decode_B(String src) {
      char[] d = src.toCharArray();
      byte[] ret = new byte[src.length() / 2];

      for(int i = 0; i < ret.length; ++i) {
         char h = d[2 * i];
         char l = d[2 * i + 1];
         int hh;
         if (h >= '0' && h <= '9') {
            hh = h - 48;
         } else if (h >= 'a' && h <= 'f') {
            hh = h - 97 + 10;
         } else {
            if (h < 'A' || h > 'F') {
               throw new RuntimeException();
            }

            hh = h - 65 + 10;
         }

         int ll;
         if (l >= '0' && l <= '9') {
            ll = l - 48;
         } else if (l >= 'a' && l <= 'f') {
            ll = l - 97 + 10;
         } else {
            if (l < 'A' || l > 'F') {
               throw new RuntimeException();
            }

            ll = l - 65 + 10;
         }

         ret[i] = (byte)(hh << 4 | ll);
      }

      return ret;
   }
}
