package com.hzbhd.canbus.car._284;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private final long MEDIA_MESSAGE_SEND_DELAY = 80L;
   private final int SEND_GIVEN_MEDIA_MESSAGE = 16;
   private final int SEND_NORMAL_MESSAGE = 17;
   private final String SHARE_IS_FRONT_RADAR_ENTER_RIGHTVIEW = "share_is_front_radar_enter_rightview";
   private final int _284_BT_STATUS_INCOMING = 1;
   private final int _284_BT_STATUS_OUTGOING = 2;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x24Data;
   private int[] m0x25Data;
   private int[] m0x26Data;
   private int[] m0x27Data;
   private int[] m0x29Data;
   private int m0x30Data;
   private int m0x31Data;
   private int[] m0x32Data;
   private int[] m0x38Data;
   private int[] m0x39Data;
   private int[] m0x3AData;
   private int[] m0x52Data;
   private int[] m0xFFData;
   private boolean mBackStatus;
   private int mBtStatus;
   byte[] mBytes0x75;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mEachId;
   private boolean mFrontRadarEnterRightViewStatsu;
   private boolean mFrontStatus;
   private Handler mHandler;
   private int mIsMute;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private ID3[] mMusicId3s;
   private int mMusicSource;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private SparseArray mSettingItemSpareArray;
   private int mSettingLeftIndex;
   private int mSettingRightIndex;
   private int mSkyWindowStatus;
   private TimerUtil mTimerUtil;
   private List mTireInfoList;
   private List mTireUpdateEntityList;
   private List mTireWarningList;
   private UiMgr mUiMgr;

   public MsgMgr() {
      byte[] var1 = new byte[10];
      this.mBytes0x75 = var1;
      var1[0] = 22;
      var1[1] = 117;
   }

   private int getBandData(String var1) {
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

   private int[] getFreqByteHiLo(int var1, String var2, String var3) {
      int[] var4 = new int[2];
      if (!var2.equals("AM2") && !var2.equals("MW") && !var2.equals("AM1") && !var2.equals("LW")) {
         if (var2.equals("FM1") || var2.equals("FM2") || var2.equals("FM3") || var2.equals("OIRT")) {
            var1 = (int)(Double.parseDouble(var3) * (double)var1);
            var4[0] = (byte)(var1 >> 8);
            var4[1] = (byte)(var1 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var3) >> 8);
         var4[1] = (byte)(Integer.parseInt(var3) & 255);
      }

      return var4;
   }

   private String getItem(int var1) {
      return (String)this.mSettingItemSpareArray.get(var1, "null_value");
   }

   private void getLeftAndRight(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         int var2 = (Integer)this.mSettingItemIndeHashMap.get(var1);
         this.mSettingLeftIndex = var2 >> 8;
         this.mSettingRightIndex = var2 & 255;
         Log.i("ljq", "getLeftAndRigth: left:" + this.mSettingLeftIndex + ", right:" + this.mSettingRightIndex);
      } else {
         this.mSettingLeftIndex = -1;
         this.mSettingRightIndex = -1;
      }

   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   private UiMgr getUiMgr() {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

      return this.mUiMgr;
   }

   private void initID3() {
      this.mMusicId3s = new ID3[]{new ID3(this, 1), new ID3(this, 2)};
   }

   private void initSettingItemIndexSparseArray() {
      SparseArray var1 = new SparseArray();
      this.mSettingItemSpareArray = var1;
      var1.put(1, "_250_automatic_folding_mirror");
      this.mSettingItemSpareArray.append(2, "_284_setting_item_02");
      this.mSettingItemSpareArray.append(3, "_284_setting_item_03");
      this.mSettingItemSpareArray.append(4, "auto_lock_when_drive");
      this.mSettingItemSpareArray.append(5, "auto_lock_when_stop_car");
      this.mSettingItemSpareArray.append(6, "_284_setting_item_06");
      this.mSettingItemSpareArray.append(7, "_284_setting_item_07");
      this.mSettingItemSpareArray.append(8, "_284_setting_item_08");
      this.mSettingItemSpareArray.append(9, "_284_setting_item_09");
      this.mSettingItemSpareArray.append(10, "_284_setting_item_0A");
      this.mSettingItemSpareArray.append(11, "_284_setting_item_0B");
      this.mSettingItemSpareArray.append(13, "_284_setting_item_0D");
      this.mSettingItemSpareArray.append(14, "_284_setting_item_0E");
      this.mSettingItemSpareArray.append(15, "_284_setting_item_0F");
      this.mSettingItemSpareArray.append(16, "auto_door_unlock");
      this.mSettingItemSpareArray.append(17, "_284_setting_item_11");
      this.mSettingItemSpareArray.append(18, "_284_setting_item_12");
      this.mSettingItemSpareArray.append(19, "_284_setting_item_13");
      this.mSettingItemSpareArray.append(20, "_284_setting_item_14");
      this.mSettingItemSpareArray.append(21, "_284_setting_item_15");
      this.mSettingItemSpareArray.append(22, "_284_setting_item_16");
      this.mSettingItemSpareArray.append(23, "_284_setting_item_17");
      this.mSettingItemSpareArray.append(24, "light_ctrl_3");
      this.mSettingItemSpareArray.append(25, "_163_setting_6");
      this.mSettingItemSpareArray.append(26, "_176_car_setting_title_25");
      this.mSettingItemSpareArray.append(27, "_284_setting_item_1B");
      this.mSettingItemSpareArray.append(28, "_284_setting_item_1C");
      this.mSettingItemSpareArray.append(29, "_284_setting_item_1D");
      this.mSettingItemSpareArray.append(30, "_284_setting_item_1E");
      this.mSettingItemSpareArray.append(31, "geely_emergency_brake_auto");
      this.mSettingItemSpareArray.append(32, "_284_setting_item_20");
      this.mSettingItemSpareArray.append(33, "_284_setting_item_21");
      this.mSettingItemSpareArray.append(34, "_284_setting_item_22");
      this.mSettingItemSpareArray.append(35, "_284_setting_item_23");
      this.mSettingItemSpareArray.append(36, "_284_setting_item_24");
      this.mSettingItemSpareArray.append(37, "_284_setting_item_25");
      this.mSettingItemSpareArray.append(38, "_284_setting_item_26");
      this.mSettingItemSpareArray.append(39, "_250_ambient_light_brightness");
      this.mSettingItemSpareArray.append(40, "_284_setting_item_28");
      this.mSettingItemSpareArray.append(41, "_284_setting_item_29");
      this.mSettingItemSpareArray.append(42, "_284_setting_item_2A");
      this.mSettingItemSpareArray.append(43, "_284_setting_item_2B");
      this.mSettingItemSpareArray.append(44, "_284_setting_item_2C");
      this.mSettingItemSpareArray.append(45, "_284_setting_item_2D");
      this.mSettingItemSpareArray.append(46, "_284_setting_item_2E");
      this.mSettingItemSpareArray.append(47, "_284_setting_item_2F");
      this.mSettingItemSpareArray.append(48, "_284_setting_item_30");
      this.mSettingItemSpareArray.append(49, "_284_setting_item_31");
      this.mSettingItemSpareArray.append(50, "_163_setting_1");
      this.mSettingItemSpareArray.append(51, "_284_setting_item_33");
      this.mSettingItemSpareArray.append(52, "_284_setting_item_34");
      this.mSettingItemSpareArray.append(53, "_284_setting_item_35");
      this.mSettingItemSpareArray.append(54, "_284_setting_item_36");
      this.mSettingItemSpareArray.append(55, "_284_setting_item_37");
      this.mSettingItemSpareArray.append(56, "_284_setting_item_38");
      this.mSettingItemSpareArray.append(57, "_250_language");
      this.mSettingItemSpareArray.append(58, "_284_setting_item_3A");
      this.mSettingItemSpareArray.append(59, "_284_setting_item_3B");
      this.mSettingItemSpareArray.append(60, "_284_setting_item_3C");
      this.mSettingItemSpareArray.append(61, "_284_setting_item_3D");
      this.mSettingItemSpareArray.append(62, "_284_setting_item_3E");
      this.mSettingItemSpareArray.append(63, "_284_setting_item_3F");
      this.mSettingItemSpareArray.append(64, "_220_steering_modes");
      this.mSettingItemSpareArray.append(65, "_194_window");
      this.mSettingItemSpareArray.append(66, "seatDriveProfile");
      this.mSettingItemSpareArray.append(67, "_284_setting_item_43");
      this.mSettingItemSpareArray.append(68, "_284_setting_item_44");
      this.mSettingItemSpareArray.append(69, "_284_setting_item_45");
      this.mSettingItemSpareArray.append(70, "_284_setting_item_46");
      this.mSettingItemSpareArray.append(71, "_284_setting_item_47");
      this.mSettingItemSpareArray.append(72, "_284_setting_item_48");
      this.mSettingItemSpareArray.append(73, "_284_setting_item_49");
      Log.i("ljq", "initSettingItemIndexSparseArray: ");
   }

   private void initSettingsItem(SettingPageUiSet var1) {
      Log.i("ljq", "initSettingsItem: ");
      this.mSettingItemIndeHashMap = new HashMap();

      for(int var2 = 0; var2 < var1.getList().size(); ++var2) {
         List var4 = ((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)var4.get(var3)).getTitleSrn();
            this.mSettingItemIndeHashMap.put(var5, var2 << 8 & '\uff00' | var3);
         }
      }

   }

   private void initSettingsPnormaicAndRightViewItem() {
      Context var5 = this.mContext;
      boolean var4 = false;
      int var1 = SharePreUtil.getIntValue(var5, "share_284_support_panoramic", 0);
      int var2 = SharePreUtil.getIntValue(this.mContext, "share_284_support_rightview", 0);
      ArrayList var6 = new ArrayList();
      this.getLeftAndRight("support_panorama");
      if (this.mSettingLeftIndex != -1 && this.mSettingRightIndex != -1) {
         var6.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, var1));
      }

      this.getLeftAndRight("_284_support_right_view");
      if (this.mSettingLeftIndex != -1 && this.mSettingRightIndex != -1) {
         var6.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, var2));
      }

      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
      boolean var3;
      if (var1 == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.updatePanoramicItem(var3);
      var3 = var4;
      if (var2 == 1) {
         var3 = true;
      }

      this.updateRightViewItem(var3);
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

   private boolean is0x24DataChange() {
      if (Arrays.equals(this.m0x24Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x24Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x25DataChange() {
      if (Arrays.equals(this.m0x25Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x25Data = Arrays.copyOf(var1, var1.length);
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

   private boolean is0x27DataChange() {
      if (Arrays.equals(this.m0x27Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x27Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x29DataChange() {
      if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x29Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x30DataChange() {
      int var1 = this.m0x30Data;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 == var2) {
         return false;
      } else {
         this.m0x30Data = var2;
         return true;
      }
   }

   private boolean is0x31DataChange() {
      int var1 = this.m0x31Data;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 == var2) {
         return false;
      } else {
         this.m0x31Data = var2;
         return true;
      }
   }

   private boolean is0x32DataChange() {
      if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x32Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x38DataChange() {
      if (Arrays.equals(this.m0x38Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x38Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x39DataChange() {
      if (Arrays.equals(this.m0x39Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x39Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x3ADataChange() {
      if (Arrays.equals(this.m0x3AData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x3AData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x52DataChange() {
      if (Arrays.equals(this.m0x52Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x52Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xFFDataChange() {
      if (Arrays.equals(this.m0xFFData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xFFData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mSkyWindowStatus == GeneralDoorData.skyWindowOpenLevel && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mSkyWindowStatus = GeneralDoorData.skyWindowOpenLevel;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void openEasyConnect() {
   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick3_1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick3_2(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_2(var2, var1, var3[2], var3[3]);
   }

   private void reportBtPhoneInfo(int var1, byte[] var2, int var3, int var4) {
      var2 = (new String(var2) + "  ").getBytes(StandardCharsets.UTF_8);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte)var1, (byte)var3, (byte)var4, 1, (byte)var2.length}, var2));
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
               this.reportID3InfoFinal(var1, var6.line, var6.info);
            }

            return;
         }
      }

   }

   private void reportID3InfoFinal(int var1, int var2, String var3) {
      byte[] var4 = DataHandleUtils.exceptBOMHead((var3 + "  ").getBytes(StandardCharsets.UTF_8));
      this.sendNormalMessage(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte)var1, -1, 18, (byte)var2, (byte)var4.length}, var4), (long)var2 * 80L);
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LOW";
      } else {
         return var1 == 30 ? "HIGH" : (float)(var1 + 35) / 2.0F + this.getTempUnitC(this.mContext);
      }
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      Handler var5 = this.mHandler;
      if (var5 == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         var5.sendMessageDelayed(var5.obtainMessage(16, var1, 0, var2), var3);
      }
   }

   private void sendNormalMessage(Object var1) {
      this.sendNormalMessage(var1, 0L);
   }

   private void sendNormalMessage(Object var1, long var2) {
      Handler var4 = this.mHandler;
      if (var4 == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         var4.sendMessageDelayed(var4.obtainMessage(17, var1), var2);
      }
   }

   private void setAirData0x21() {
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         GeneralAirData.rear = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         boolean var2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         boolean var3 = true;
         GeneralAirData.in_out_cycle = var2 ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_wind_level = this.mCanBusInfoInt[3];
         int var1 = this.mCanBusInfoInt[4];
         if (var1 != 3 && var1 != 4) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.front_left_blow_window = var2;
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 3 && var1 != 4) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.front_right_blow_window = var2;
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 0 && var1 != 1) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.front_left_blow_head = var2;
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 0 && var1 != 1) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.front_right_blow_head = var2;
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 1 && var1 != 2 && var1 != 4) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.front_left_blow_foot = var2;
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 1 && var1 != 2 && var1 != 4) {
            var2 = false;
         } else {
            var2 = true;
         }

         GeneralAirData.front_right_blow_foot = var2;
         String var4 = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_temperature = var4;
         GeneralAirData.front_right_temperature = var4;
         if (GeneralAirData.front_wind_level != 0) {
            var2 = var3;
         } else {
            var2 = false;
         }

         GeneralAirData.power = var2;
         GeneralAirData.front_defog = GeneralAirData.front_left_blow_window;
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setFrontRadarData0x23() {
      if (this.is0x23DataChange()) {
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
         if (CommUtil.isPanoramic(this.mContext)) {
            Context var3 = this.mContext;
            boolean var1 = false;
            if (SharePreUtil.getBoolValue(var3, "share_is_front_radar_enter_rightview", false)) {
               if (this.mCanBusInfoInt[5] != 0) {
                  var1 = true;
               }

               if (this.mFrontRadarEnterRightViewStatsu ^ var1) {
                  this.mFrontRadarEnterRightViewStatsu = var1;
                  this.switchFCamera(this.mContext, var1);
               }
            }
         }
      }

   }

   private void setFrontRadarDistanceData0x27() {
      if (this.is0x27DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setLeftRadarData0x24() {
      if (this.is0x24DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setLeftRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setPanoramicSwtich0x31() {
      if (this.is0x31DataChange()) {
         this.forceReverse(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      }

   }

   private void setPanoramicViewStatus0x32() {
      if (this.is0x32DataChange()) {
         ArrayList var4 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = false;
         boolean var2;
         if (var1 != 1 && var1 != 5) {
            var2 = false;
         } else {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var2));
         var1 = this.mCanBusInfoInt[2];
         if (var1 != 4 && var1 != 8) {
            var2 = false;
         } else {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var2));
         if (this.mCanBusInfoInt[2] == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var2));
         if (this.mCanBusInfoInt[2] == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(3, var2));
         if (this.mCanBusInfoInt[2] == 7) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(4, var2));
         var1 = this.mCanBusInfoInt[2];
         if (var1 != 5 && var1 != 8) {
            var2 = false;
         } else {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(5, var2));
         if (this.mCanBusInfoInt[2] == 6) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(6, var2));
         var2 = var3;
         if (this.mCanBusInfoInt[2] == 9) {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(7, var2));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRearRadarData0x22() {
      if (this.is0x22DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRearRadarDistanceData0x26() {
      if (this.is0x26DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRightRadarData0x25() {
      if (this.is0x25DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRightRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRightViewSwtich0x30() {
      if (this.is0x30DataChange()) {
         this.switchFCamera(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      }

   }

   private void setTirePressureData0x38() {
      if (this.is0x38DataChange()) {
         Iterator var4 = this.mTireUpdateEntityList.iterator();

         while(var4.hasNext()) {
            int var1 = ((TireUpdateEntity)var4.next()).getWhichTire();
            List var5 = (List)this.mTireInfoList.get(var1);
            var5.clear();
            int var2 = this.mCanBusInfoInt[var1 + 2];
            String var3 = CommUtil.getStrByResId(this.mContext, "_284_tire_temperature_default");
            if (var2 != 255) {
               var3 = var2 - 40 + this.getTempUnitC(this.mContext);
            }

            var5.add(var3);
            var2 = this.mCanBusInfoInt[var1 + 6];
            var3 = CommUtil.getStrByResId(this.mContext, "_284_tire_pressure_default");
            if (var2 != 255) {
               var3 = (new DecimalFormat("0.0")).format((double)((float)DataHandleUtils.rangeNumber(var2, 7, 252) / 70.0F - 0.1F)) + "bar";
            }

            var5.add(var3);
            var5.addAll((Collection)this.mTireWarningList.get(var1));
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setList(var5);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setTirePressureWarnning0x39() {
      if (this.is0x39DataChange()) {
         Iterator var4 = this.mTireUpdateEntityList.iterator();

         while(var4.hasNext()) {
            TireUpdateEntity var5 = (TireUpdateEntity)var4.next();
            int var3 = var5.getWhichTire();
            ArrayList var6 = new ArrayList();
            var6.addAll((Collection)this.mTireInfoList.get(var3));
            List var7 = (List)this.mTireWarningList.get(var3);
            var7.clear();
            byte var2 = 0;
            var5.setTireStatus(0);

            int var1;
            for(var1 = 0; var1 < 8; ++var1) {
               if ((this.mCanBusInfoInt[var3 + 2] & 1 << var1) != 0) {
                  var7.add(CommUtil.getStrByResId(this.mContext, "_284_tire_warning_" + var1));
                  var5.setTireStatus(1);
               }
            }

            var3 = var7.size();

            for(var1 = var2; var1 < 8 - var3; ++var1) {
               var7.add("");
            }

            var6.addAll(var7);
            var5.setList(var6);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setTrackData0x29() {
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 5500, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleBaseInfo0x3A() {
      if (this.is0x3ADataChange()) {
         int var1;
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            var1 = 2;
         } else {
            var1 = 0;
         }

         GeneralDoorData.skyWindowOpenLevel = var1;
         boolean var2 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         this.mLeftFrontRec = var2;
         GeneralDoorData.isLeftFrontDoorOpen = var2;
         var2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mRightFrontRec = var2;
         GeneralDoorData.isRightFrontDoorOpen = var2;
         var2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         this.mLeftRearRec = var2;
         GeneralDoorData.isLeftRearDoorOpen = var2;
         var2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         this.mRightRearRec = var2;
         GeneralDoorData.isRightRearDoorOpen = var2;
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         if (this.isDoorDataChange()) {
            this.updateDoorView(this.mContext);
         }

         ArrayList var3 = new ArrayList();
         int[] var4 = this.mCanBusInfoInt;
         var1 = var4[3] * 256 + var4[4];
         if (var1 > 0 && var1 < 8190) {
            var3.add(new DriverUpdateEntity(0, 0, var1 + " km"));
         }

         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setVehicleSetup0x52() {
      if (this.is0x52DataChange()) {
         this.getLeftAndRight(this.getItem(this.mCanBusInfoInt[2]));
         if (this.mSettingLeftIndex != -1 && this.mSettingRightIndex != -1) {
            ArrayList var3;
            label45: {
               var3 = new ArrayList();
               int[] var5 = this.mCanBusInfoInt;
               int var1 = var5[2];
               if (var1 != 18 && var1 != 28 && var1 != 30 && var1 != 32) {
                  boolean var2 = false;
                  Context var4;
                  if (var1 == 52) {
                     var4 = this.mContext;
                     if (var5[3] == 1) {
                        var2 = true;
                     }

                     SharePreUtil.setBoolValue(var4, "share_is_front_radar_enter_rightview", var2);
                     var3.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, this.mCanBusInfoInt[3] - 1));
                     break label45;
                  }

                  switch (var1) {
                     case 10:
                     case 11:
                        break;
                     case 12:
                        try {
                           var4 = this.mContext;
                           StringBuilder var7 = new StringBuilder();
                           Toast.makeText(var4, CommUtil.getStrByResId(var4, var7.append("_284_tire_reset_result_").append(this.mCanBusInfoInt[3]).toString()), 0).show();
                        } catch (Exception var6) {
                           Log.e("CanBusError", var6.toString());
                        }
                        break label45;
                     default:
                        switch (var1) {
                           case 39:
                              var3.add((new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
                              break label45;
                           case 40:
                              break;
                           case 41:
                              var3.add((new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, this.mCanBusInfoInt[3] - 10)).setProgress(this.mCanBusInfoInt[3]));
                              break label45;
                           default:
                              var3.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, this.mCanBusInfoInt[3] - 1));
                              break label45;
                        }
                  }
               }

               var3.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, this.mCanBusInfoInt[3]));
            }

            this.updateGeneralSettingData(var3);
            this.updateSettingActivity((Bundle)null);
         }
      }

   }

   private void setVersionInfo0xFF() {
      if (this.is0xFFDataChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 136) {
         switch (var1) {
            case 0:
               this.realKeyClick1(0);
               break;
            case 1:
               this.realKeyClick1(7);
               break;
            case 2:
               this.realKeyClick1(8);
               break;
            case 3:
               this.realKeyClick1(45);
               break;
            case 4:
               this.realKeyClick1(46);
               break;
            case 5:
               this.realKeyClick1(3);
               break;
            case 6:
               this.realKeyClick1(2);
               break;
            case 7:
               this.realKeyClick1(14);
               break;
            case 8:
               this.realKeyClick1(15);
               break;
            case 9:
               this.realKeyClick1(129);
               break;
            case 10:
               this.realKeyClick1(134);
               break;
            default:
               switch (var1) {
                  case 17:
                     this.realKeyClick1(7);
                     break;
                  case 18:
                     this.realKeyClick1(8);
                     break;
                  case 19:
                     this.realKeyClick1(52);
                     break;
                  case 20:
                     this.realKeyClick1(59);
                     break;
                  default:
                     switch (var1) {
                        case 26:
                           this.realKeyClick1(467);
                           break;
                        case 27:
                           this.realKeyClick1(468);
                           break;
                        case 28:
                           this.realKeyClick1(469);
                           break;
                        case 29:
                           this.realKeyClick1(470);
                           break;
                        case 30:
                           this.realKeyClick1(471);
                           break;
                        case 31:
                           this.realKeyClick1(472);
                           break;
                        default:
                           switch (var1) {
                              case 33:
                                 this.realKeyClick3_1(7);
                                 break;
                              case 34:
                                 this.realKeyClick3_1(8);
                                 break;
                              case 35:
                                 this.realKeyClick1(49);
                                 break;
                              case 36:
                                 this.realKeyClick3_2(48);
                                 break;
                              case 37:
                                 this.realKeyClick3_2(47);
                                 break;
                              case 38:
                                 this.realKeyClick1(45);
                                 break;
                              case 39:
                                 this.realKeyClick1(46);
                                 break;
                              case 40:
                                 this.realKeyClick1(58);
                                 break;
                              case 41:
                                 this.realKeyClick1(128);
                                 break;
                              case 42:
                                 this.realKeyClick1(152);
                                 break;
                              case 43:
                                 this.realKeyClick1(30);
                                 break;
                              case 44:
                                 this.realKeyClick1(68);
                                 break;
                              case 45:
                                 this.realKeyClick1(14);
                                 break;
                              case 46:
                                 this.openEasyConnect();
                                 break;
                              case 47:
                                 this.realKeyClick1(4);
                                 break;
                              case 48:
                                 this.realKeyClick1(52);
                                 break;
                              case 49:
                                 this.realKeyClick1(49);
                           }
                     }
               }
         }
      } else {
         this.realKeyClick1(187);
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, -1, 18, 0}, 80L);
      byte[] var1 = this.mBytes0x75;
      var1[2] = 5;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = 0;
      var1[6] = 0;
      var1[7] = 0;
      var1[8] = 0;
      var1[9] = (byte)this.mIsMute;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), this.mBytes0x75, 160L);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -64, 11, -1, 18, 0}, 80L);
      byte[] var1 = this.mBytes0x75;
      var1[2] = 6;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = 0;
      var1[6] = 0;
      var1[7] = 0;
      var1[8] = 0;
      var1[9] = (byte)this.mIsMute;
      this.sendNormalMessage(var1, 160L);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.reportBtPhoneInfo(0, var1, 0, 0);
      var1 = this.mBytes0x75;
      var1[2] = 6;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = 0;
      var1[6] = 0;
      var1[7] = 0;
      var1[8] = 0;
      var1[9] = (byte)this.mIsMute;
      this.sendNormalMessage(var1, 80L);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.mBtStatus = 1;
      this.reportBtPhoneInfo(1, var1, 0, 0);
      var1 = this.mBytes0x75;
      var1[2] = 6;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = 0;
      var1[6] = 0;
      var1[7] = 0;
      var1[8] = 0;
      var1[9] = (byte)this.mIsMute;
      this.sendNormalMessage(var1, 80L);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.mBtStatus = 2;
      this.reportBtPhoneInfo(3, var1, 0, 0);
      var1 = this.mBytes0x75;
      var1[2] = 6;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = 0;
      var1[6] = 0;
      var1[7] = 0;
      var1[8] = 0;
      var1[9] = (byte)this.mIsMute;
      this.sendNormalMessage(var1, 80L);
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      byte var5;
      if (this.mBtStatus == 2) {
         var5 = 4;
      } else {
         var5 = 2;
      }

      this.reportBtPhoneInfo(var5, var1, (byte)(var4 >> 8), (byte)(var4 & 255));
      var1 = this.mBytes0x75;
      var1[2] = 6;
      var1[3] = 0;
      var1[4] = 0;
      var1[5] = 0;
      var1[6] = 0;
      var1[7] = 0;
      var1[8] = 0;
      var1[9] = (byte)this.mIsMute;
      this.sendNormalMessage(var1, 80L);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 41) {
         if (var3 != 82) {
            if (var3 != 255) {
               switch (var3) {
                  case 32:
                     this.setWheelKey0x20();
                     break;
                  case 33:
                     this.setAirData0x21();
                     break;
                  case 34:
                     this.setRearRadarData0x22();
                     break;
                  case 35:
                     this.setFrontRadarData0x23();
                     break;
                  case 36:
                     this.setLeftRadarData0x24();
                     break;
                  case 37:
                     this.setRightRadarData0x25();
                     break;
                  case 38:
                     this.setRearRadarDistanceData0x26();
                     break;
                  case 39:
                     this.setFrontRadarDistanceData0x27();
                     break;
                  default:
                     switch (var3) {
                        case 48:
                           this.setRightViewSwtich0x30();
                           break;
                        case 49:
                           this.setPanoramicSwtich0x31();
                           break;
                        case 50:
                           this.setPanoramicViewStatus0x32();
                           break;
                        default:
                           switch (var3) {
                              case 56:
                                 this.setTirePressureData0x38();
                                 break;
                              case 57:
                                 this.setTirePressureWarnning0x39();
                                 break;
                              case 58:
                                 this.setVehicleBaseInfo0x3A();
                           }
                     }
               }
            } else {
               this.setVersionInfo0xFF();
            }
         } else {
            this.setVehicleSetup0x52();
         }
      } else {
         this.setTrackData0x29();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      byte var4;
      if (var2) {
         var4 = 2;
      } else {
         var4 = 1;
      }

      this.mIsMute = var4;
      byte[] var3 = this.mBytes0x75;
      var3[9] = (byte)var4;
      this.sendNormalMessage(var3);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var6, (byte)var5, (byte)var10});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mEachId = this.getCurrentEachCanId();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 82});
      int var2 = this.mEachId;
      if (var2 != 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 3, (byte)var2});
      }

      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      GeneralParkData.radar_distance_disable = new int[]{255};
      GeneralTireData.isHaveSpareTire = false;
      this.mTireUpdateEntityList = new ArrayList();
      this.mTireInfoList = new ArrayList();
      this.mTireWarningList = new ArrayList();

      for(var2 = 0; var2 < 4; ++var2) {
         this.mTireUpdateEntityList.add(new TireUpdateEntity(var2, 0, new String[0]));
         ArrayList var3 = new ArrayList();
         var3.add(CommUtil.getStrByResId(var1, "_284_tire_temperature_default"));
         var3.add(CommUtil.getStrByResId(var1, "_284_tire_pressure_default"));
         this.mTireInfoList.add(var3);
         this.mTireWarningList.add(new ArrayList());
      }

      this.initID3();
      this.initSettingItemIndexSparseArray();
      this.getUiMgr().initSettingItem();
      this.initSettingsItem(this.getUiMgr().getSettingUiSet(var1));
      GeneralSettingData.dataList = new ArrayList();
      this.initSettingsPnormaicAndRightViewItem();
      this.mHandler = new Handler(this, Looper.getMainLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 16) {
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 17) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mMusicId3s[0].info = "";
      this.mMusicId3s[1].info = "";
      this.reportID3Info(this.mMusicSource, this.mMusicId3s);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var33;
      if (var1 == 9) {
         var33 = 9;
      } else {
         var33 = 8;
      }

      this.mMusicSource = var33;
      var14 = var19 / 1000L;
      int var28 = (int)(var14 >> 8);
      int var27 = (int)(var14 & 255L);
      var14 = (long)(var5 * 60 * 60 + var6 * 60 + var7);
      int var29 = (int)(var14 >> 8);
      int var30 = (int)(255L & var14);
      var10 = (byte)var33;
      var2 = (byte)(var4 >> 8);
      byte var26 = (byte)(var4 & 255);
      byte var25 = (byte)var3;
      var5 = (byte)var28;
      var6 = (byte)var27;
      var7 = (byte)var29;
      var8 = (byte)var30;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -64, var10, 0, var2, var26, var9, var25, var5, var6, var7, var8});
      this.mMusicId3s[0].info = var21;
      this.mMusicId3s[1].info = var23;
      this.reportID3Info(this.mMusicSource, this.mMusicId3s);
      byte var31;
      if (var1 == 8) {
         var31 = 3;
      } else {
         var31 = 4;
      }

      byte[] var32 = this.mBytes0x75;
      var32[2] = (byte)var31;
      var32[3] = 0;
      var32[4] = 0;
      var32[5] = 0;
      var32[6] = 0;
      var32[7] = (byte)(var16 + 1);
      var32[8] = 0;
      var32[9] = (byte)this.mIsMute;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), this.mBytes0x75, 240L);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var15 = (byte)this.getBandData(var2);
      int[] var14 = this.getFreqByteHiLo(100, var2, var3);
      byte var9 = (byte)var15;
      byte var6 = (byte)var14[1];
      byte var7 = (byte)var14[0];
      byte var8 = (byte)var1;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -64, 1, var9, var6, var7, var8});
      int[] var13 = this.getFreqByteHiLo(10, var2, var3);
      byte var11;
      if (var2.contains("AM")) {
         var11 = 3;
      } else {
         var11 = 1;
      }

      int var10 = var13[0];
      var5 = var13[1];
      byte[] var12 = this.mBytes0x75;
      var12[2] = 1;
      var12[3] = (byte)var11;
      var12[4] = (byte)var10;
      var12[5] = (byte)var5;
      var12[6] = var8;
      var12[7] = 0;
      var12[8] = 0;
      var12[9] = (byte)this.mIsMute;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), this.mBytes0x75, 80L);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendNormalMessage(new byte[]{22, -64, 0});
      byte[] var2 = this.mBytes0x75;
      var2[2] = 0;
      var2[3] = 0;
      var2[4] = 0;
      var2[5] = 0;
      var2[6] = 0;
      var2[7] = 0;
      var2[8] = 0;
      var2[9] = (byte)this.mIsMute;
      this.sendNormalMessage(var2, 80L);
   }

   void updatePanoramicItem(boolean var1) {
      ArrayList var5 = new ArrayList();
      this.getLeftAndRight("_284_setting_item_25");
      int var2 = this.mSettingLeftIndex;
      int var3 = this.mSettingRightIndex;
      Integer var4 = 0;
      var5.add((new SettingUpdateEntity(var2, var3, var4)).setEnable(var1));
      this.getLeftAndRight("_284_setting_item_26");
      var5.add((new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, var4)).setEnable(var1));
      this.getLeftAndRight("_55_0xE8_data4");
      var5.add((new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, "null_value")).setEnable(var1));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   void updateRightViewItem(boolean var1) {
      ArrayList var3 = new ArrayList();
      this.getLeftAndRight("_284_setting_item_33");
      var3.add((new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, 0)).setEnable(var1));
      byte var2 = SharePreUtil.getBoolValue(this.mContext, "share_is_front_radar_enter_rightview", false);
      this.getLeftAndRight("_284_setting_item_34");
      var3.add((new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, var2 ^ 1)).setEnable(var1));
      this.getLeftAndRight("_284_open_right_view");
      var3.add((new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, "null_value")).setEnable(var1));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var22;
      if (var1 == 9) {
         var22 = 9;
      } else {
         var22 = 8;
      }

      int var20 = var5 * 60 * 60 + var6 * 60 + var7;
      var17 /= 1000;
      var10 = (byte)var22;
      var7 = (byte)(var4 >> 8);
      var6 = (byte)(var4 & 255);
      var2 = (byte)var3;
      var5 = (byte)(var17 >> 8);
      byte var18 = (byte)(var17 & 255);
      var1 = (byte)(var20 >> 8);
      byte var19 = (byte)(var20 & 255);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, var10, 0, var7, var6, var9, var2, var5, var18, var1, var19});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, var10, -1, 18, 0}, 80L);
      byte[] var21 = this.mBytes0x75;
      var21[2] = 3;
      var21[3] = 0;
      var21[4] = 0;
      var21[5] = 0;
      var21[6] = 0;
      var21[7] = (byte)(var15 + 1);
      var21[8] = 0;
      var21[9] = (byte)this.mIsMute;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), this.mBytes0x75, 160L);
   }

   private class ID3 {
      private String info;
      private int line;
      private String record;
      final MsgMgr this$0;

      private ID3(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.line = var2;
         this.info = "";
         this.record = "";
      }

      // $FF: synthetic method
      ID3(MsgMgr var1, int var2, Object var3) {
         this(var1, var2);
      }

      private boolean isId3Change() {
         if (this.record.equals(this.info)) {
            return false;
         } else {
            this.recordId3Info();
            return true;
         }
      }

      private void recordId3Info() {
         this.record = this.info;
      }
   }
}
