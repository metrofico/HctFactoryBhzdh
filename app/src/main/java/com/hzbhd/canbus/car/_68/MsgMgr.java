package com.hzbhd.canbus.car._68;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.fragment.OnStartPhoneMoreInfoFragment;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.FgeString;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private String ParkingProgress = "";
   private String ParkingType = "";
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x25Data;
   private int[] m0x26Data;
   private int[] m0x30Data;
   private int[] m0x32Data;
   private int[] m0x33DataIndexOne;
   private int[] m0x33DataIndexTwo;
   private int[] m0xD1Data;
   private int[] m0xD2Data;
   private SparseArray mActiveParkItemArray;
   private ActiveParkView mActiveParkView;
   int[] mAirData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mIsActiveViewOpen;
   private WindowManager.LayoutParams mLayoutParams;
   private MyPanoramicView mPanoramicView;
   private ParkPageUiSet mParkPageUiSet;
   private UiMgr mUiMgr;
   private WindowManager mWindowManager;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void closeActiveParkView() {
      WindowManager var2 = this.mWindowManager;
      if (var2 != null) {
         ActiveParkView var1 = this.mActiveParkView;
         if (var1 != null && this.mIsActiveViewOpen) {
            var2.removeView(var1);
            this.mIsActiveViewOpen = false;
         }
      }

   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIndexBy3Bit(int var1) {
      LogUtil.showLog("getIndexBy3Bit:" + var1);
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

   private MyPanoramicView getMyPanoramicView() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (MyPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      return this.mPanoramicView;
   }

   private String getSimpleGmOnstarWirelessInfo(int[] var1, byte[] var2, int var3) {
      if (var1[1] == var3) {
         byte[] var5 = Arrays.copyOfRange(var2, 2, var2.length);

         try {
            String var6 = new String(var5, "ASCII");
            return var6;
         } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
         }
      }

      return "";
   }

   private String getSimpleGmOnstarWirelessPassWord(int[] var1, byte[] var2, int var3) {
      if (var1[1] == var3) {
         byte[] var5 = Arrays.copyOfRange(var2, 2, var2.length);

         try {
            String var6 = new String(var5, "ASCII");
            return var6;
         } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
         }
      }

      return "";
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, String var4) {
      boolean var6 = TextUtils.isEmpty(var2);
      byte var5 = 0;
      String[] var7;
      if (var6) {
         var7 = new String[]{"", var3, var4};
      } else {
         var7 = new String[]{var2, var3, var4};
         var5 = 1;
      }

      return new TireUpdateEntity(var1, var5, var7);
   }

   private String getTirePressure(int var1) {
      return String.valueOf(var1 * 4);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initActivePark(Context var1) {
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var2 = new WindowManager.LayoutParams();
      this.mLayoutParams = var2;
      var2.type = 2002;
      this.mLayoutParams.gravity = 17;
      this.mLayoutParams.width = -1;
      this.mLayoutParams.height = -1;
      this.mActiveParkView = new ActiveParkView(this, var1);
      SparseArray var3 = new SparseArray();
      this.mActiveParkItemArray = var3;
      var3.put(0, new ActiveParkItem(this, 2131766923));
      this.mActiveParkItemArray.append(1, new ActiveParkItem(this, 2131233621, 2131233416, 2131766930, "NULL"));
      this.mActiveParkItemArray.append(2, new ActiveParkItem(this, 2131233417, 2131233621, 2131766947, "NULL"));
      this.mActiveParkItemArray.append(3, new ActiveParkItem(this, 2131233621, 2131233416, 2131766959, "NULL"));
      this.mActiveParkItemArray.append(4, new ActiveParkItem(this, 2131233417, 2131233621, 2131766960, "NULL"));
      this.mActiveParkItemArray.put(5, new ActiveParkItem(this, 2131766961));
      this.mActiveParkItemArray.put(6, new ActiveParkItem(this, 2131766962));
      this.mActiveParkItemArray.put(7, new ActiveParkItem(this, 2131766963));
      this.mActiveParkItemArray.put(8, new ActiveParkItem(this, 2131766964));
      this.mActiveParkItemArray.append(9, new ActiveParkItem(this, 2131233621, 2131233428, 2131766965, "NULL"));
      this.mActiveParkItemArray.put(10, new ActiveParkItem(this, 2131766924));
      this.mActiveParkItemArray.put(11, new ActiveParkItem(this, 2131766925));
      this.mActiveParkItemArray.put(12, new ActiveParkItem(this, 2131766926));
      this.mActiveParkItemArray.append(13, new ActiveParkItem(this, 2131233427, 2131233621, 2131766927, "NULL"));
      this.mActiveParkItemArray.put(14, new ActiveParkItem(this, 2131766928));
      this.mActiveParkItemArray.put(15, new ActiveParkItem(this, 2131766929));
      this.mActiveParkItemArray.put(16, new ActiveParkItem(this, 2131766931));
      this.mActiveParkItemArray.append(17, new ActiveParkItem(this, 2131233621, 2131233416, 2131766932, "NULL"));
      this.mActiveParkItemArray.append(18, new ActiveParkItem(this, 2131233417, 2131233621, 2131766933, "NULL"));
      this.mActiveParkItemArray.append(19, new ActiveParkItem(this, 2131233621, 2131233430, 2131766934, "NULL"));
      this.mActiveParkItemArray.put(20, new ActiveParkItem(this, 2131766935));
      this.mActiveParkItemArray.put(21, new ActiveParkItem(this, 2131766936));
      this.mActiveParkItemArray.append(22, new ActiveParkItem(this, 2131233429, 2131233621, 2131766937, "NULL"));
      this.mActiveParkItemArray.put(23, new ActiveParkItem(this, 2131766938));
      this.mActiveParkItemArray.put(24, new ActiveParkItem(this, 2131766939));
      this.mActiveParkItemArray.put(25, new ActiveParkItem(this, 2131766940));
      this.mActiveParkItemArray.put(26, new ActiveParkItem(this, 2131766941));
      this.mActiveParkItemArray.append(27, new ActiveParkItem(this, 2131233434, 2131233621, 2131766942, "NULL"));
      this.mActiveParkItemArray.append(28, new ActiveParkItem(this, 2131233621, 2131233435, 2131766943, "NULL"));
      this.mActiveParkItemArray.append(29, new ActiveParkItem(this, 2131766944));
      this.mActiveParkItemArray.append(30, new ActiveParkItem(this, 2131766945));
      this.mActiveParkItemArray.append(31, new ActiveParkItem(this, 2131766946));
      this.mActiveParkItemArray.append(32, new ActiveParkItem(this, 2131766948));
      this.mActiveParkItemArray.append(33, new ActiveParkItem(this, 2131766949));
      this.mActiveParkItemArray.append(34, new ActiveParkItem(this, 2131766950));
      this.mActiveParkItemArray.append(35, new ActiveParkItem(this, 2131233621, 2131233420, 2131766951, "NULL"));
      this.mActiveParkItemArray.append(36, new ActiveParkItem(this, 2131233419, 2131233621, 2131766952, "NULL"));
      this.mActiveParkItemArray.append(37, new ActiveParkItem(this, 2131233621, 2131233420, 2131766953, "NULL"));
      this.mActiveParkItemArray.append(38, new ActiveParkItem(this, 2131233419, 2131233621, 2131766954, "NULL"));
      this.mActiveParkItemArray.append(39, new ActiveParkItem(this, 2131233621, 2131233422, 2131766955, "NULL"));
      this.mActiveParkItemArray.append(40, new ActiveParkItem(this, 2131233421, 2131233621, 2131766956, "NULL"));
      this.mActiveParkItemArray.append(41, new ActiveParkItem(this, 2131233621, 2131233422, 2131766957, "NULL"));
      this.mActiveParkItemArray.append(42, new ActiveParkItem(this, 2131233421, 2131233621, 2131766958, "NULL"));
   }

   private void initData(Context var1) {
      this.mParkPageUiSet = this.getUiMgr(var1).getParkPageUiSet(var1);
   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x26DataChange() {
      if (Arrays.equals(this.m0x26Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x26Data = Arrays.copyOf(var1, var1.length);
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

   private void openActiveParView() {
      if (!this.mIsActiveViewOpen) {
         this.mWindowManager.addView(this.mActiveParkView, this.mLayoutParams);
         this.mIsActiveViewOpen = true;
      }

   }

   private void openOnStarPhoneMoreInfoFragment() {
      if (!SystemUtil.isForeground(this.mContext, Constant.OnStarActivity.getClassName())) {
         Intent var1 = new Intent();
         var1.setComponent(Constant.OnStarActivity);
         var1.setFlags(268435456);
         var1.putExtra("bundle_open_fragment", OnStartPhoneMoreInfoFragment.class);
         this.mContext.startActivity(var1);
      }
   }

   private void realKeyClick2(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick2(var3, var1, var2[2], var2[3]);
   }

   private void realKeyLongClick1(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (30 == var1) {
         var2 = "HI";
      } else if (255 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (29 == var1) {
         var2 = "16" + this.getTempUnitC(this.mContext);
      } else if (31 == var1) {
         var2 = "16.5" + this.getTempUnitC(this.mContext);
      } else if (32 == var1) {
         var2 = "15" + this.getTempUnitC(this.mContext);
      } else if (33 == var1) {
         var2 = "15.5" + this.getTempUnitC(this.mContext);
      } else if (34 == var1) {
         var2 = "31" + this.getTempUnitC(this.mContext);
      } else if (1 <= var1 && 28 >= var1) {
         var2 = (double)(var1 - 1) * 0.5 + 17.0 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void setAirData0x03() {
      this.updateOutDoorTemp(this.mContext, this.mCanBusInfoByte[7] + this.getTempUnitC(this.mContext));
      this.mCanBusInfoByte[7] = 0;
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.front_wind_level = (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
         GeneralAirData.sync_temperature = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_defog = false;
         switch (var1) {
            case 1:
               GeneralAirData.front_auto_wind_model = true;
               break;
            case 2:
               GeneralAirData.front_defog = true;
               break;
            case 3:
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 4:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 5:
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               break;
            case 6:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               break;
            case 7:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               break;
            case 8:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               break;
            case 9:
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
         }

         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         GeneralAirData.climate = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
         GeneralAirData.ac_auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]);
         GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAirData0x44() {
      ArrayList var1 = new ArrayList();
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_heat"), 2));
      } else if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_heat"), 1));
      } else {
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_heat"), 0));
      }

      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_cold"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_air"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_rear_air"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAirData0x45() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (129 == var1) {
         var2 = "HI";
      } else if (255 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (1 <= var1 && 128 >= var1) {
         var2 = (double)(var1 - 1) * 0.5 + 0.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      GeneralAirData.rear_temperature = var2;
      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      if (var1 != 0 && var1 != 1 && var1 != 2 && var1 != 3 && var1 != 4 && var1 != 5 && var1 != 6) {
         if (var1 == 15) {
            GeneralAirData.rear_auto_wind_speed = true;
         } else {
            GeneralAirData.rear_auto_wind_speed = false;
         }
      } else {
         GeneralAirData.rear_wind_level = var1;
         GeneralAirData.rear_auto_wind_speed = false;
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.rear_left_blow_window = false;
      GeneralAirData.rear_left_blow_head = false;
      GeneralAirData.rear_left_blow_foot = false;
      GeneralAirData.rear_right_blow_window = false;
      GeneralAirData.rear_right_blow_head = false;
      GeneralAirData.rear_right_blow_foot = false;
      GeneralAirData.rear_auto_wind_model = false;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_foot = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_right_blow_head = true;
               GeneralAirData.rear_right_blow_foot = true;
            }
         } else {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
         }
      } else {
         GeneralAirData.rear_left_auto_wind = true;
         GeneralAirData.rear_right_auto_wind = true;
      }

      this.updateAirActivity(this.mContext, 1002);
   }

   private void setAirData0x46() {
      int var1 = this.mCanBusInfoInt[2];
      boolean var2 = false;
      if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 7) == 1) {
         var2 = true;
      }

      if (GeneralAirData.climate != var2) {
         GeneralAirData.climate = var2;
         if (!GeneralAirData.climate) {
            if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
               this.finishActivity();
            }
         } else {
            AirActivity.mIsClickOpen = true;
            Intent var3 = new Intent(this.mContext, AirActivity.class);
            var3.setFlags(268435456);
            this.mContext.startActivity(var3);
         }
      }

   }

   private void setCarLanguage0x0C() {
      StringBuilder var2 = (new StringBuilder()).append("setCarLanguage0x0C ");
      int[] var1 = this.mCanBusInfoInt;
      Log.d("mww", var2.append(FgeString.bytes2HexString(var1, var1.length)).toString());
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7)));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarSettings0x43() {
      StringBuilder var1 = (new StringBuilder()).append("setCarSettings0x43 ");
      int[] var2 = this.mCanBusInfoInt;
      Log.d("cbc", var1.append(FgeString.bytes2HexString(var2, var2.length)).toString());
      this.getMyPanoramicView().mRearCarStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 0, 2);
      this.runOnUi(new MsgMgr$$ExternalSyntheticLambda0(this));
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(1, 13, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var3.add(new SettingUpdateEntity(1, 14, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var3.add(new SettingUpdateEntity(1, 15, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var3.add(new SettingUpdateEntity(1, 16, this.mCanBusInfoInt[3]));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarSpeed0x0B() {
      StringBuilder var1 = (new StringBuilder()).append("setCarSpeed0x0B ");
      int[] var2 = this.mCanBusInfoInt;
      Log.d("mww", var1.append(FgeString.bytes2HexString(var2, var2.length)).toString());
      ArrayList var5 = new ArrayList();
      var1 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var5.add((new SettingUpdateEntity(3, 0, var1.append((var3[2] * 256 + var3[3]) / 16).append("km/h").toString())).setValueStr(true));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo((var4[2] * 256 + var4[3]) / 16);
   }

   private void setCarVolume0x0D() {
      StringBuilder var1 = (new StringBuilder()).append("setCarVolume0x0D ");
      int[] var2 = this.mCanBusInfoInt;
      Log.d("mww", var1.append(FgeString.bytes2HexString(var2, var2.length)).toString());
      ArrayList var3 = new ArrayList();
      var3.add((new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7)));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarWind0x13() {
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setControl0x0A() {
      StringBuilder var2 = (new StringBuilder()).append("setControl0x0A ");
      int[] var1 = this.mCanBusInfoInt;
      Log.d("mww", var2.append(FgeString.bytes2HexString(var1, var1.length)).toString());
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var3.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var3.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var3.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var3.add(new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      var3.add(new SettingUpdateEntity(2, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)));
      var3.add(new SettingUpdateEntity(2, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2)));
      var3.add(new SettingUpdateEntity(2, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var3.add(new SettingUpdateEntity(2, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
      var3.add(new SettingUpdateEntity(2, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      var3.add(new SettingUpdateEntity(2, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      var3.add(new SettingUpdateEntity(2, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
      var3.add(new SettingUpdateEntity(2, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var3.add(new SettingUpdateEntity(2, 13, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var3.add(new SettingUpdateEntity(2, 14, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var3.add(new SettingUpdateEntity(2, 15, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var3.add(new SettingUpdateEntity(2, 16, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)));
      var3.add(new SettingUpdateEntity(2, 17, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      var3.add(new SettingUpdateEntity(2, 18, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
      var3.add(new SettingUpdateEntity(2, 19, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
      var3.add(new SettingUpdateEntity(2, 20, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
      var3.add(new SettingUpdateEntity(2, 21, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
      var3.add(new SettingUpdateEntity(2, 22, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
      var3.add(new SettingUpdateEntity(2, 23, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
      var3.add(new SettingUpdateEntity(2, 24, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
      var3.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_body_center_control_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_body_center_control_2", "_68_Automatically_unlock_near_car"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2)));
      var3.add(new SettingUpdateEntity(2, 26, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
      var3.add(new SettingUpdateEntity(2, 27, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)));
      var3.add(new SettingUpdateEntity(2, 28, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)));
      var3.add(new SettingUpdateEntity(2, 29, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)));
      var3.add(new SettingUpdateEntity(2, 30, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRadarData0x23() {
      if (this.is0x23DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setFuelData0x4A() {
      StringBuilder var1 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      String var4 = var1.append(this.calculateSum(var2[3], var2[4])).append("km").toString();
      StringBuilder var5 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      String var6 = var5.append((double)this.calculateSum(var3[5], var3[6]) * 0.1).append("L/100km").toString();
      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(0, 0, var6));
      var7.add(new DriverUpdateEntity(0, 1, var4));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setHybirdData0x47(Context var1) {
      GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
      GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralHybirdData.title = var1.getString(2131768208);
      ArrayList var2 = new ArrayList();
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var2.add(var1.getString(2131768204));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
         var2.add(var1.getString(2131767861));
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
         var2.add(var1.getString(2131768211));
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
         var2.add(var1.getString(2131769795));
      }

      if (var2.size() != 0) {
         GeneralHybirdData.valueList = var2;
      }

      this.updateHybirdActivity((Bundle)null);
   }

   private void setOnStartData0x08() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralOnStartData.mOnStarPhoneNum = DataHandleUtils.bcd2Str(Arrays.copyOfRange(var1, 2, var1.length));
      this.updateOnStarActivity(1001);
   }

   private void setOnStartData0x09() {
      GeneralOnStartData.mOnStarStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      this.updateOnStarActivity(1001);
   }

   private void setOnStartData0x41() {
      GeneralOnStartData.mOnStarWirelessPoint = this.getSimpleGmOnstarWirelessInfo(this.mCanBusInfoInt, this.mCanBusInfoByte, 65);
      this.updateOnStarActivity(1002);
   }

   private void setOnStartData0x42() {
      GeneralOnStartData.mOnStarWirelessPassword = this.getSimpleGmOnstarWirelessPassWord(this.mCanBusInfoInt, this.mCanBusInfoByte, 66);
      this.updateOnStarActivity(1002);
   }

   private void setPanelKey0x02() {
      StringBuilder var1 = (new StringBuilder()).append("setWheelKey0x01 ");
      int[] var2 = this.mCanBusInfoInt;
      HzbhdLog.d("mww", var1.append(FgeString.bytes2HexString(var2, var2.length)).toString());
      if (this.getCurrentEachCanId() != 24 && this.getCurrentEachCanId() != 23) {
         if (this.getCurrentEachCanId() != 17 && this.getCurrentEachCanId() != 99) {
            if (ConstantHashMap.panelKeyMaps.containsKey(this.mCanBusInfoInt[2])) {
               this.realKeyClick2((Integer)ConstantHashMap.panelKeyMaps.get(this.mCanBusInfoInt[2]));
            }
         } else {
            this.realKeyClick2((Integer)ConstantHashMap.panelKeyMaps_Encore_.get(this.mCanBusInfoInt[2]));
         }
      } else if (ConstantHashMap.panelKeyMaps_Gl8.containsKey(this.mCanBusInfoInt[2])) {
         this.realKeyClick2((Integer)ConstantHashMap.panelKeyMaps_Gl8.get(this.mCanBusInfoInt[2]));
      }

   }

   private void setPanoramic0x48() {
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         private String getTextViewContent(int var1) {
            String var2;
            if (!DataHandleUtils.getBoolBit7(this.this$0.mCanBusInfoInt[2])) {
               var2 = this.this$0.mContext.getString(2131766971);
            } else {
               var2 = this.this$0.mContext.getString(2131766972);
            }

            String var3;
            if (!DataHandleUtils.getBoolBit6(this.this$0.mCanBusInfoInt[2])) {
               var3 = this.this$0.mContext.getString(2131766966);
            } else {
               var3 = this.this$0.mContext.getString(2131766968);
            }

            String var4 = this.this$0.mCanBusInfoInt[3] + "%";
            return this.this$0.mContext.getString(2131766970) + var2 + "\n" + this.this$0.mContext.getString(2131766967) + var3 + "\n" + this.this$0.mContext.getString(2131766969) + var4 + "\n\n" + this.this$0.mContext.getString(var1);
         }

         public void callback() {
            if (DataHandleUtils.getBoolBit5(this.this$0.mCanBusInfoInt[2])) {
               this.this$0.openActiveParView();
               this.this$0.mParkPageUiSet.setShowCusPanoramicView(true);
               this.this$0.mActiveParkView.hideAlert();
               ActiveParkItem var2 = (ActiveParkItem)this.this$0.mActiveParkItemArray.get(this.this$0.mCanBusInfoInt[4]);
               this.this$0.mActiveParkView.setLeftSideImage(var2.getLeftImageResId());
               this.this$0.mActiveParkView.setRightSideImage(var2.getRightImageResId());
               this.this$0.mActiveParkView.setViewText(this.getTextViewContent(var2.getMessageResId()));
            } else {
               this.this$0.closeActiveParkView();
               this.this$0.mParkPageUiSet.setShowCusPanoramicView(false);
               MsgMgr var1 = this.this$0;
               var1.updateParkUi((Bundle)null, var1.mContext);
            }
         }
      });
   }

   private void setPanoramic0x49() {
      Context var3 = this.mContext;
      int var1 = this.mCanBusInfoInt[2];
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      this.forceReverse(var3, var2);
   }

   private void setRadar0x07() {
      StringBuilder var3 = (new StringBuilder()).append("setWheelKey0x01 ");
      int[] var2 = this.mCanBusInfoInt;
      Log.d("mww", var3.append(FgeString.bytes2HexString(var2, var2.length)).toString());
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(3, 3, var1));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearRadar0x22() {
      if (this.is0x22DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSettingData0x05() {
      int var5 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2));
      int var7 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
      int var6 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[2], 1));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[2], 0));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[3], 5));
      int var1 = this.getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[3], 4));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[3], 3));
      int var9 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2));
      ArrayList var11 = new ArrayList();
      var11.add(new SettingUpdateEntity(0, 0, var5));
      var11.add(new SettingUpdateEntity(0, 1, var7));
      var11.add(new SettingUpdateEntity(0, 2, var6));
      var11.add(new SettingUpdateEntity(0, 3, var8));
      var11.add(new SettingUpdateEntity(0, 4, var3));
      var11.add(new SettingUpdateEntity(0, 5, var1));
      var11.add(new SettingUpdateEntity(0, 6, var2));
      var11.add(new SettingUpdateEntity(0, 7, var9));
      var11.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "_68_Pollution_control"), var4));
      boolean var10;
      if (var5 == 1 && var7 == 1 && var6 == 1 && var8 == 0 && var3 == 0 && var4 == 0 && var1 == 1 && var2 == 1 && var9 == 1) {
         var10 = true;
      } else {
         var10 = false;
      }

      var11.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "_68_lianaidexiniu"), "恋爱的犀牛")).setEnable(var10).setValueStr(true));
      this.updateGeneralSettingData(var11);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData0x06() {
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
      boolean var14;
      if (var13 == 1) {
         var14 = true;
      } else {
         var14 = false;
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1);
      ArrayList var15 = new ArrayList();
      var15.add(new SettingUpdateEntity(1, 0, var4));
      var15.add(new SettingUpdateEntity(1, 1, var9));
      var15.add(new SettingUpdateEntity(1, 2, var13));
      var15.add(new SettingUpdateEntity(1, 3, var1));
      var15.add(new SettingUpdateEntity(1, 4, var7));
      var15.add((new SettingUpdateEntity(1, 5, var8)).setEnable(var14));
      var15.add(new SettingUpdateEntity(1, 6, var2));
      var15.add(new SettingUpdateEntity(1, 7, var10));
      var15.add(new SettingUpdateEntity(1, 8, var12));
      if (var5 == 1) {
         var15.add((new SettingUpdateEntity(1, 9, this.mContext.getString(2131767919))).setValueStr(true));
      } else {
         var15.add((new SettingUpdateEntity(1, 9, this.mContext.getString(2131767918))).setValueStr(true));
      }

      var15.add(new SettingUpdateEntity(1, 10, var6));
      var15.add(new SettingUpdateEntity(1, 11, var11));
      var15.add(new SettingUpdateEntity(1, 12, var3));
      this.updateGeneralSettingData(var15);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTireData0x4A() {
      StringBuilder var1 = (new StringBuilder()).append("0x4A ");
      int[] var2 = this.mCanBusInfoInt;
      Log.d("setTireData0x4A", var1.append(FgeString.bytes2HexString(var2, var2.length)).toString());
      ArrayList var3 = new ArrayList();
      var3.add(this.getTireEntity(0, "", this.getTirePressure(this.mCanBusInfoInt[4]), ""));
      var3.add(this.getTireEntity(1, "", this.getTirePressure(this.mCanBusInfoInt[5]), ""));
      var3.add(this.getTireEntity(2, "", this.getTirePressure(this.mCanBusInfoInt[6]), ""));
      var3.add(this.getTireEntity(3, "", this.getTirePressure(this.mCanBusInfoInt[7]), ""));
      GeneralTireData.dataList = var3;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setTrackData0x26() {
      if (this.is0x26DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo() {
      String var1 = CommUtil.subArrayToString(this.mCanBusInfoByte, 0, 16);
      this.updateVersionInfo(this.mContext, var1);
   }

   private void setWheelKey0x01() {
      StringBuilder var1 = (new StringBuilder()).append("setWheelKey0x01 ");
      int[] var2 = this.mCanBusInfoInt;
      Log.d("mww", var1.append(FgeString.bytes2HexString(var2, var2.length)).toString());
      switch (this.mCanBusInfoInt[2]) {
         case 1:
            this.realKeyLongClick1(7);
            break;
         case 2:
            this.realKeyLongClick1(8);
            break;
         case 3:
            this.realKeyLongClick1(45);
            break;
         case 4:
            this.realKeyLongClick1(46);
            break;
         case 5:
            this.realKeyLongClick1(2);
            break;
         case 6:
            this.realKeyLongClick1(201);
            break;
         case 7:
            this.realKeyLongClick1(184);
            break;
         case 8:
            this.realKeyLongClick1(48);
            break;
         case 9:
            this.realKeyLongClick1(47);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OnStarActivity);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "Aux playing".getBytes();
      this.getUiMgr(this.mContext).send0xC0Info(this.SplicingByte(new byte[]{22, -64, 5}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "Bluetooth music".getBytes();
      this.getUiMgr(this.mContext).send0xC0Info(this.SplicingByte(new byte[]{22, -64, 5}, var1));
   }

   public int calculateSum(int var1, int var2) {
      return var1 * 256 + var2;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 19) {
         if (var3 != 38) {
            if (var3 != 48) {
               switch (var3) {
                  case 1:
                     this.setWheelKey0x01();
                     break;
                  case 2:
                     this.setPanelKey0x02();
                     break;
                  case 3:
                     this.setAirData0x03();
                     break;
                  case 4:
                     this.setPanelKey0x02();
                     break;
                  case 5:
                     this.setSettingData0x05();
                     break;
                  case 6:
                     this.setSettingData0x06();
                     break;
                  case 7:
                     this.setRadar0x07();
                     break;
                  case 8:
                     this.setOnStartData0x08();
                     break;
                  case 9:
                     this.setOnStartData0x09();
                     break;
                  case 10:
                     this.setControl0x0A();
                     break;
                  case 11:
                     this.setCarSpeed0x0B();
                     break;
                  case 12:
                     this.setCarLanguage0x0C();
                     break;
                  case 13:
                     this.setCarVolume0x0D();
                     break;
                  default:
                     switch (var3) {
                        case 34:
                           this.setRearRadar0x22();
                           break;
                        case 35:
                           this.setFrontRadarData0x23();
                           break;
                        case 36:
                           if (this.isDoorMsgRepeat(var2)) {
                              return;
                           }

                           this.setDoorData0x24();
                           break;
                        default:
                           switch (var3) {
                              case 65:
                                 this.setOnStartData0x41();
                                 break;
                              case 66:
                                 this.setOnStartData0x42();
                                 break;
                              case 67:
                                 this.setCarSettings0x43();
                                 break;
                              case 68:
                                 this.setAirData0x44();
                                 break;
                              case 69:
                                 this.setAirData0x45();
                                 break;
                              case 70:
                                 this.setAirData0x46();
                                 break;
                              case 71:
                                 this.setHybirdData0x47(var1);
                                 break;
                              case 72:
                                 this.setPanoramic0x48();
                                 break;
                              case 73:
                                 this.setPanoramic0x49();
                                 break;
                              case 74:
                                 StringBuilder var6 = (new StringBuilder()).append("0x4A ");
                                 int[] var5 = this.mCanBusInfoInt;
                                 Log.d("mww", var6.append(FgeString.bytes2HexString(var5, var5.length)).toString());
                                 var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
                                 if (var3 == 1) {
                                    this.setTireData0x4A();
                                 } else if (var3 == 2) {
                                    this.setFuelData0x4A();
                                 }
                           }
                     }
               }
            } else {
               this.setVersionInfo();
            }
         } else {
            this.setTrackData0x26();
         }
      } else {
         this.setCarWind0x13();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      byte var16 = (byte)var8;
      byte var14 = (byte)var6;
      byte var15 = (byte)(var10 ^ 1);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -119}, new byte[]{var16, var14, var15}));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.getUiMgr(this.mContext).send0xE2CarModelInfo(this.getCurrentEachCanId());
      this.initData(var1);
      this.initActivePark(var1);
   }

   // $FF: synthetic method
   void lambda$setCarSettings0x43$0$com_hzbhd_canbus_car__68_MsgMgr() {
      this.mPanoramicView.updatePanoramicView();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         byte[] var25 = ("Music Progress:" + var11 + ":" + var12 + ":" + var13).getBytes();
         this.getUiMgr(this.mContext).send0xC0Info(this.SplicingByte(new byte[]{22, -64, 6}, var25));
      } else {
         var11 = "Music Progress:" + var11 + ":" + var12 + ":" + var13;
         byte[] var26 = var11.getBytes();
         this.getUiMgr(this.mContext).send0xC0Info(this.SplicingByte(new byte[]{22, -64, 4}, var26));
         MyLog.temporaryTracking(var11);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var8;
      if (var2.contains("FM")) {
         var4 = "MHz";
         var8 = 1;
      } else {
         var4 = "KHz";
         var8 = 2;
      }

      var2 = var2 + " P" + var1 + " " + var3 + var4;
      byte var6 = (byte)var8;
      byte[] var7 = var2.getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -64, var6}, var7);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18;
      if (var1 == 9) {
         var18 = ("Video Progress:" + var11 + ":" + var12 + ":" + var13).getBytes();
         this.getUiMgr(this.mContext).send0xC0Info(this.SplicingByte(new byte[]{22, -64, 6}, var18));
      } else {
         var18 = ("Video Progress:" + var11 + ":" + var12 + ":" + var13).getBytes();
         this.getUiMgr(this.mContext).send0xC0Info(this.SplicingByte(new byte[]{22, -64, 4}, var18));
      }

   }

   private class ActiveParkItem {
      private int leftImageResId;
      private int messageResId;
      private String reverseIcon;
      private int rightImageResId;
      final MsgMgr this$0;

      public ActiveParkItem(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.messageResId = var2;
      }

      public ActiveParkItem(MsgMgr var1, int var2, int var3, int var4, String var5) {
         this.this$0 = var1;
         this.leftImageResId = var2;
         this.rightImageResId = var3;
         this.messageResId = var4;
         this.reverseIcon = var5;
      }

      public int getLeftImageResId() {
         return this.leftImageResId;
      }

      public int getMessageResId() {
         return this.messageResId;
      }

      public String getReverseIcon() {
         return this.reverseIcon;
      }

      public int getRightImageResId() {
         return this.rightImageResId;
      }
   }

   private class ActiveParkView extends LinearLayout {
      private ImageView mIvLeftSide;
      private ImageView mIvRightSide;
      private LinearLayout mLlAlert;
      private TextView mTvAlertMessage;
      private TextView mTvMessage;
      final MsgMgr this$0;

      public ActiveParkView(MsgMgr var1, Context var2) {
         super(var2);
         this.this$0 = var1;
         LayoutInflater.from(var2).inflate(2131558746, this);
         this.findeView();
         this.initView();
      }

      private void findeView() {
         this.mIvLeftSide = (ImageView)this.findViewById(2131362599);
         this.mIvRightSide = (ImageView)this.findViewById(2131362629);
         this.mTvMessage = (TextView)this.findViewById(2131363650);
         this.mLlAlert = (LinearLayout)this.findViewById(2131362770);
         this.mTvAlertMessage = (TextView)this.findViewById(2131363589);
      }

      private void initView() {
      }

      private void setLeftSideImage(int var1) {
         this.mIvLeftSide.setImageResource(var1);
      }

      private void setRightSideImage(int var1) {
         this.mIvRightSide.setImageResource(var1);
      }

      private void setViewText(String var1) {
         this.mTvMessage.setText(var1);
      }

      public void hideAlert() {
         this.mLlAlert.setVisibility(8);
      }

      public void setAlertText(int var1) {
         this.mTvAlertMessage.setText(var1);
      }

      public void setAlertText(String var1) {
         this.mTvAlertMessage.setText(var1);
      }

      public void showAlert() {
         this.mLlAlert.setVisibility(0);
      }
   }
}
