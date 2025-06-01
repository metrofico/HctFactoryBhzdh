package com.hzbhd.canbus.car._349;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.AppEnableUtil;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
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
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String IS_ACTIVE_PARK_OPEN = "is_active_park_open";
   static int[] mLanguageMapArray;
   static SYNC_VERSION mSyncVersion;
   private final long DATA_HANDLE_PERIOD = 32L;
   private final int INVAILE_VALUE = -1;
   private final int MEDIA_SOURCE_ID_A2DP = 11;
   private final int MEDIA_SOURCE_ID_AUX = 7;
   private final int MEDIA_SOURCE_ID_DISC = 2;
   private final int MEDIA_SOURCE_ID_DVBT = 10;
   private final int MEDIA_SOURCE_ID_OFF = 0;
   private final int MEDIA_SOURCE_ID_SD = 9;
   private final int MEDIA_SOURCE_ID_TUNER = 1;
   private final int MEDIA_SOURCE_ID_TV = 3;
   private final int MEDIA_SOURCE_ID_USB = 8;
   private final int MSG_ACTIVE_PARK_0X1C_COUNT_DOWN = 10;
   private final int MSG_DISMISS_WARNING_ACTIVITY = 11;
   private final int SEND_GIVEN_MEDIA_MESSAGE = 101;
   private final int SEND_NORMAL_MESSAGE = 102;
   private int eachId;
   private String[] m0x05ItemTitleArray;
   private SparseArray mActiveParkItemArray;
   private ActiveParkView mActiveParkView;
   private String mAirUnit;
   int[] mBackLightInt;
   private boolean mBackStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private DataHandler mDataHandler;
   private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");
   private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
   private DecimalFormat mDecimalFormat0p00 = new DecimalFormat("0.00");
   int[] mFrontRadarData;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private Handler mHandler;
   private boolean mIsActiveViewOpen;
   private boolean mIsSyncNeedShowDialog;
   private int mKeyStatus;
   private WindowManager.LayoutParams mLayoutParams;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private ID3[] mMeidaInfos;
   private MyPanoramicView mPanoramicView;
   private ParkPageUiSet mParkPageUiSet;
   private int[] mPreWarningData;
   private String mRadioCurrentBand;
   int[] mRearRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private SparseIntArray mSyncMenuIconResIdArray;
   TimerUtil mTimerUtil;
   private SparseIntArray mTopIconArray;
   private WindowManager mWindowManager;
   String resulttemp;
   private UiMgr uiMgr;

   public MsgMgr() {
      mLanguageMapArray = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 18, 22, 23, 27, 28};
      SparseIntArray var1 = new SparseIntArray();
      this.mSyncMenuIconResIdArray = var1;
      var1.put(0, 2131233621);
      this.mSyncMenuIconResIdArray.append(2, 2131233573);
      this.mSyncMenuIconResIdArray.append(10, 2131233461);
      this.mSyncMenuIconResIdArray.append(8, 2131233611);
      this.mSyncMenuIconResIdArray.append(5, 2131233586);
      this.mSyncMenuIconResIdArray.append(12, 2131233478);
      var1 = new SparseIntArray();
      this.mTopIconArray = var1;
      var1.put(0, 2131233453);
      this.mTopIconArray.append(1, 2131233549);
      this.mTopIconArray.append(3, 2131233615);
      this.mTopIconArray.append(4, 2131233479);
      this.mTopIconArray.append(5, 2131233584);
      this.mTopIconArray.append(6, 2131233633);
   }

   private void adjustBrightness() {
      this.setBacklightLevel(this.mCanBusInfoInt[2] / 51 + 1);
   }

   private void closeActiveParkView() {
      WindowManager var1 = this.mWindowManager;
      if (var1 != null) {
         ActiveParkView var2 = this.mActiveParkView;
         if (var2 != null && this.mIsActiveViewOpen) {
            var1.removeView(var2);
            this.mIsActiveViewOpen = false;
         }
      }

   }

   private boolean compare(Object var1, Object... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var2[var3].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   private void dealSyncKey(Context var1, int var2) {
      this.dealSyncKey(var1, var2, -1, 0);
   }

   private void dealSyncKey(Context var1, int var2, int var3, int var4) {
      if (SystemUtil.isForeground(var1, SyncActivity.class.getName())) {
         if (this.mKeyStatus == 1) {
            var4 = this.mCanBusInfoInt[3];
            if (var4 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var2});
            } else if (var4 == 2 && var3 != -1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var3});
            }
         }
      } else {
         this.realKeyClick2(this.mContext, var4);
      }

      this.mKeyStatus = this.mCanBusInfoInt[3];
   }

   private int getData(int... var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var1.length; ++var2) {
         var3 += var1[var2] << var2 * 8;
      }

      return (double)var3 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var3;
   }

   private int[] getLeftAndRight(String var1) {
      int[] var3 = new int[]{-1, -1};
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         int var2 = (Integer)this.mSettingItemIndeHashMap.get(var1);
         var3[0] = var2 >> 8;
         var3[1] = var2 & 255;
         Log.i("ljq", "getLeftAndRigth: left:" + var3[0] + ", right:" + var3[1]);
      }

      return var3;
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

   private int getSyncIconResId(Context var1, String var2) {
      int var4 = CommUtil.getImgIdByResId(var1, var2);
      int var3 = var4;
      if (var4 == 2131234410) {
         var3 = 2131233621;
      }

      return var3;
   }

   private String getSyncInfo(byte[] var1) {
      int var2 = var1.length;
      String var5 = "";
      if (var2 < 8) {
         return "";
      } else {
         int var4 = var1.length;
         var2 = 7;

         int var3;
         while(true) {
            var3 = var4;
            if (var2 >= var1.length) {
               break;
            }

            var3 = var2 - 1;
            if (var1[var3] == 0 && var1[var2] == 0) {
               break;
            }

            var2 += 2;
         }

         String var8;
         try {
            byte[] var6 = Arrays.copyOfRange(var1, 6, var3);
            var8 = new String(var6, "unicode");
         } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            var8 = var5;
         }

         return var8;
      }
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

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
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
      var3.put(2, new ActiveParkItem(this, 2131767018));
      this.mActiveParkItemArray.append(3, new ActiveParkItem(this, 2131767019));
      this.mActiveParkItemArray.append(4, new ActiveParkItem(this, 2131233621, 2131233416, 2131767020, "right_radar"));
      this.mActiveParkItemArray.append(5, new ActiveParkItem(this, 2131233417, 2131233621, 2131767021, "left_radar"));
      this.mActiveParkItemArray.append(6, new ActiveParkItem(this, 2131767022));
      this.mActiveParkItemArray.append(7, new ActiveParkItem(this, 2131231352, 2131233621, 2131767023, "left_forward"));
      this.mActiveParkItemArray.append(8, new ActiveParkItem(this, 2131233621, 2131231353, 2131767024, "left_forward"));
      this.mActiveParkItemArray.append(9, new ActiveParkItem(this, 2131231352, 2131233621, 2131767025, "left_forward"));
      this.mActiveParkItemArray.append(10, new ActiveParkItem(this, 2131233621, 2131231353, 2131767026, "left_forward"));
      this.mActiveParkItemArray.append(11, new ActiveParkItem(this, 2131231354, 2131233621, 2131767027, "left_stop"));
      this.mActiveParkItemArray.append(12, new ActiveParkItem(this, 2131233621, 2131231355, 2131767028, "left_stop"));
      this.mActiveParkItemArray.append(13, new ActiveParkItem(this, 2131231354, 2131233621, 2131767029, "left_REVERSE"));
      this.mActiveParkItemArray.append(14, new ActiveParkItem(this, 2131233621, 2131231355, 2131767030, "left_REVERSE"));
      this.mActiveParkItemArray.append(15, new ActiveParkItem(this, 2131233427, 2131233621, 2131767031, "left_backward"));
      this.mActiveParkItemArray.append(16, new ActiveParkItem(this, 2131233621, 2131233428, 2131767032, "left_backward"));
      this.mActiveParkItemArray.append(17, new ActiveParkItem(this, 2131233427, 2131233621, 2131767033, "left_backward"));
      this.mActiveParkItemArray.append(18, new ActiveParkItem(this, 2131233621, 2131233428, 2131767034, "left_backward"));
      this.mActiveParkItemArray.append(19, new ActiveParkItem(this, 2131231356, 2131233621, 2131767035, "left_stop"));
      this.mActiveParkItemArray.append(20, new ActiveParkItem(this, 2131233621, 2131231357, 2131767036, "left_stop"));
      this.mActiveParkItemArray.append(21, new ActiveParkItem(this, 2131231358, 2131233621, 2131767037, "left_forward"));
      this.mActiveParkItemArray.append(22, new ActiveParkItem(this, 2131233621, 2131231359, 2131767038, "left_forward"));
      this.mActiveParkItemArray.append(23, new ActiveParkItem(this, 2131231360, 2131233621, 2131767039, "left_stop"));
      this.mActiveParkItemArray.append(24, new ActiveParkItem(this, 2131233621, 2131231361, 2131767040, "left_stop"));
      this.mActiveParkItemArray.append(25, new ActiveParkItem(this, 2131231362, 2131233621, 2131767041, "left_backward"));
      this.mActiveParkItemArray.append(26, new ActiveParkItem(this, 2131233621, 2131231363, 2131767042, "left_backward"));
      this.mActiveParkItemArray.append(27, new ActiveParkItem(this, 2131767043));
      this.mActiveParkItemArray.append(28, new ActiveParkItem(this, 2131767044));
      this.mActiveParkItemArray.append(29, new ActiveParkItem(this, 2131767045));
      this.mActiveParkItemArray.append(30, new ActiveParkItem(this, 2131767046));
      this.mActiveParkItemArray.append(31, new ActiveParkItem(this, 2131767047));
      this.mActiveParkItemArray.append(32, new ActiveParkItem(this, 2131767048));
      this.mActiveParkItemArray.append(33, new ActiveParkItem(this, 2131767049));
      this.mActiveParkItemArray.append(34, new ActiveParkItem(this, 2131767050));
      this.mActiveParkItemArray.append(35, new ActiveParkItem(this, 2131231358, 2131233621, 2131767037, "left_forward"));
      this.mActiveParkItemArray.append(36, new ActiveParkItem(this, 2131233621, 2131231359, 2131767038, "left_forward"));
      this.mActiveParkItemArray.append(37, new ActiveParkItem(this, 2131231362, 2131233621, 2131767041, "left_backward"));
      this.mActiveParkItemArray.append(38, new ActiveParkItem(this, 2131233621, 2131231363, 2131767042, "left_backward"));
      this.mActiveParkItemArray.append(39, new ActiveParkItem(this, 2131767045));
   }

   private void initData(Context var1) {
      this.mAirUnit = this.getTempUnitC(var1);
      this.mParkPageUiSet = this.getUiMgr(var1).getParkPageUiSet(var1);
      this.mPanoramicView = (MyPanoramicView)this.getUiMgr(var1).getCusPanoramicView(this.mContext);
      this.mHandler = new Handler(this, Looper.getMainLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            int var2 = var1.what;
            if (var2 != 10) {
               if (var2 != 11) {
                  if (var2 != 101) {
                     if (var2 == 102) {
                        CanbusMsgSender.sendMsg((byte[])var1.obj);
                     }
                  } else {
                     this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
                  }
               } else if (SystemUtil.isForeground((Context)var1.obj, WarningActivity.class.getName())) {
                  this.this$0.finishActivity();
               }
            } else {
               this.this$0.mActiveParkView.setAlertText((String)var1.obj);
               this.this$0.mPanoramicView.setAlertMessage((String)var1.obj);
            }

         }
      };
      this.mDataHandler = new DataHandler(this, var1, 0L, 32L);
      this.m0x05ItemTitleArray = new String[0];
   }

   private boolean isBackLightIntChange() {
      if (Arrays.equals(this.mBackLightInt, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mBackLightInt = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mHandBrake = GeneralDoorData.isHandBrakeUp;
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void openActiveParView() {
      if (!this.mIsActiveViewOpen) {
         this.mWindowManager.addView(this.mActiveParkView, this.mLayoutParams);
         this.mIsActiveViewOpen = true;
      }

   }

   private void realKeyClick2(Context var1, int var2) {
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var1, var2, var3[2], var3[3]);
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
      String var2;
      if (GeneralAirData.fahrenheit_celsius) {
         if (var1 == 119) {
            this.resulttemp = "LOW";
         }

         if (var1 == 171) {
            this.resulttemp = "HIGH";
         } else if (var1 >= 120 && var1 <= 170) {
            var2 = (float)var1 / 2.0F + this.mAirUnit;
            this.resulttemp = var2;
            return var2;
         }
      } else if (var1 == 0) {
         this.resulttemp = "LOW";
      } else if (var1 == 127) {
         this.resulttemp = "HIGH";
      } else if (var1 >= 31 && var1 <= 59) {
         var2 = (float)var1 / 2.0F + this.mAirUnit;
         this.resulttemp = var2;
         return var2;
      }

      return this.resulttemp;
   }

   private String resolveOutDoorTem() {
      int var2 = this.mCanBusInfoInt[7];
      int var1 = var2;
      if (var2 > 86) {
         var1 = var2 - 256;
      }

      return var1 + this.getTempUnitC(this.mContext);
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      Handler var5 = this.mHandler;
      if (var5 != null) {
         var5.sendMessageDelayed(var5.obtainMessage(101, var1, 0, var2), var3);
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
         var4.sendMessageDelayed(var4.obtainMessage(102, var1), var2);
      }
   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      if (GeneralAirData.fahrenheit_celsius) {
         this.mAirUnit = this.getTempUnitF(this.mContext);
      } else {
         this.mAirUnit = this.getTempUnitC(this.mContext);
      }

      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData20x6B() {
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      String var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2) == 0) {
         var1 = this.mContext.getResources().getString(2131769219);
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2) == 1) {
         var1 = this.mContext.getResources().getString(2131769794);
      } else {
         var1 = this.mContext.getResources().getString(2131768712);
      }

      GeneralAirData.center_wheel = var1;
      this.updateAirActivity(this.mContext, 0);
   }

   private void setAmbientLightState() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (this.mCanBusInfoInt[2] == 0) {
         var1 = "关闭氛围灯";
      } else {
         var1 = this.mCanBusInfoInt[2] + "";
      }

      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_1193_setting_5_3"), var1)).setProgress(this.mCanBusInfoInt[2]));
      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_176_car_setting_title_21"), this.mCanBusInfoInt[3] - 1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAutoParkStateInfo(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         SharePreUtil.setBoolValue(var1, "is_active_park_open", DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
         this.mTimerUtil.stopTimer();
         this.runOnUi(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
      }

   }

   private void setBackLightInfo0x14() {
      ArrayList var3 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = "Min";
      } else if (var1 == 255) {
         var2 = "Max";
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "keyboard_backlight_adjustment"), var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      this.adjustBrightness();
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      ArrayList var6 = new ArrayList();
      ArrayList var5 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "reverse_state");
      String var3;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131769841);
      } else {
         var3 = this.mContext.getResources().getString(2131769410);
      }

      var6.add(new DriverUpdateEntity(var2, var1, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_309_title_7");
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131762196);
      } else {
         var3 = this.mContext.getResources().getString(2131762197);
      }

      var6.add(new DriverUpdateEntity(var1, var2, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "light_info");
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131769504);
      } else {
         var3 = this.mContext.getResources().getString(2131768042);
      }

      var6.add(new DriverUpdateEntity(var1, var2, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "hand_brake_status");
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131769741);
      } else {
         var3 = this.mContext.getResources().getString(2131769742);
      }

      var6.add(new DriverUpdateEntity(var1, var2, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_85_passenger_airbag_sataus");
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131769504);
      } else {
         var3 = this.mContext.getResources().getString(2131768042);
      }

      var6.add(new DriverUpdateEntity(var2, var1, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_my_key_vol_limit");
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131767155);
      } else {
         var3 = this.mContext.getResources().getString(2131767143);
      }

      var6.add(new DriverUpdateEntity(var2, var1, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_my_key_mute_status");
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131764183);
      } else {
         var3 = this.mContext.getResources().getString(2131764184);
      }

      var6.add(new DriverUpdateEntity(var2, var1, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_engine_display");
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
         var3 = this.mContext.getResources().getString(2131769277);
      } else {
         var3 = this.mContext.getResources().getString(2131768209);
      }

      var6.add(new DriverUpdateEntity(var1, var2, var3));
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7);
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7) >= 100) {
         var3 = "100% mileage";
      } else {
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7) + "% 里程";
      }

      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_engine"), var3));
      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3);
      String var4 = "Full";
      if (var1 == 0) {
         var3 = "All empty";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3) >= 5) {
         var3 = "Full";
      } else {
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3) + "";
      }

      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_ford_vehicle_speed"), var3));
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3) == 0) {
         var3 = "All empty";
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3) >= 5) {
         var3 = var4;
      } else {
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3) + "";
      }

      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_ford_driving_plan"), var3));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_81_traction_control_system"), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_81_turn_signals_setup"), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "ford_message_tone"), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "ford_alert_tone"), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_81_current_voice_mode"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "ford_range_unit"), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "brightness"), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])));
      this.updateGeneralSettingData(var5);
      this.updateGeneralDriveData(var6);
      this.updateActivity((Bundle)null);
      this.updateDriveDataActivity((Bundle)null);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setFrontRadarInfo0x23() {
      if (!this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(15, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setLanguageInfo() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = 0;

         while(true) {
            int[] var2 = mLanguageMapArray;
            if (var1 >= var2.length) {
               break;
            }

            if (this.mCanBusInfoInt[2] == var2[var1]) {
               this.setSettingData(new String[]{"language_setup"}, new Object[]{var1});
               return;
            }

            ++var1;
         }
      }

   }

   private void setMultiFunctionSeatStatus0x6C() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_0"), this.mCanBusInfoInt[2]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_1"), this.mCanBusInfoInt[3]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_2"), this.mCanBusInfoInt[4]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_3"), this.mCanBusInfoInt[5]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_4"), this.mCanBusInfoInt[6]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_0"), this.mCanBusInfoInt[7]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_1"), this.mCanBusInfoInt[8]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_2"), this.mCanBusInfoInt[9]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_3"), this.mCanBusInfoInt[10]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_4"), this.mCanBusInfoInt[11]));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 2, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setPanoramicData0x25() {
      boolean var1 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      String var3 = "open";
      String var2;
      if (var1) {
         var2 = "open";
      } else {
         var2 = "close";
      }

      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var3 = "close";
      }

      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_41_front_radar"), CommUtil.getStrByResId(this.mContext, var2)));
      var4.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_41_rear_radar"), CommUtil.getStrByResId(this.mContext, var3)));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearRadarInfo0x22() {
      if (!this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(15, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setRotatingSpeed() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
      StringBuilder var6 = new StringBuilder();
      DecimalFormat var4 = this.mDecimalFormat0p00;
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var6.append(var4.format((long)this.getData(var5[2], var5[3]))).append("rpm").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSettingData(String[] var1, Object[] var2) {
      ArrayList var6 = new ArrayList();

      for(int var3 = 0; var3 < var1.length; ++var3) {
         int[] var4 = this.getLeftAndRight(var1[var3]);
         if (var4[0] != -1 && var4[1] != -1) {
            Object var5 = var2[var3];
            var6.add(new SettingUpdateEntity(var4[0], var4[1], var5));
         }
      }

      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSpeedState() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
      StringBuilder var6 = new StringBuilder();
      DecimalFormat var4 = this.mDecimalFormat0p00;
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var6.append(var4.format((long)this.getData(var5[2], var5[3]))).append(" km/h").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var7 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var7[3], var7[2]));
   }

   private void setSyncMediaTimeInfo() {
      if (MsgMgr.SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         GeneralSyncData.mSyncTime = this.mDecimalFormat00.format((long)this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[4]);
         GeneralSyncData.mIsSyncTimeChange = true;
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void setSyncMenuInfo() {
      SparseIntArray var2 = new SparseIntArray();
      this.mSyncMenuIconResIdArray = var2;
      var2.put(0, 2131233621);
      this.mSyncMenuIconResIdArray.append(2, 2131233573);
      this.mSyncMenuIconResIdArray.append(10, 2131233461);
      this.mSyncMenuIconResIdArray.append(8, 2131233611);
      this.mSyncMenuIconResIdArray.append(5, 2131233586);
      this.mSyncMenuIconResIdArray.append(12, 2131233478);
      if (MsgMgr.SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         GeneralSyncData.mSelectedLineIndex = this.mCanBusInfoInt[3] - 1;
         int[] var3 = this.mCanBusInfoInt;
         boolean var1;
         if (var3[5] != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         this.mIsSyncNeedShowDialog = var1;
         if (var1) {
            GeneralSyncData.mSelectedLineIndex = var3[6] - 1;
         }

         GeneralSyncData.mSyncTopIconResIdArray[0] = this.mSyncMenuIconResIdArray.get(this.mCanBusInfoInt[7]);
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void setSyncMenuItemInfo(Context var1, byte[] var2) {
      if (MsgMgr.SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         int var3 = var2[3];
         boolean var6 = false;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var3, 0, 4);
         var3 = var2[2];
         boolean var7 = true;
         String var9;
         Iterator var10;
         if (var3 >= 1 && var3 <= 10) {
            var3 = (var3 - 1) % 5;
            int var5 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[4] & 255));
            var4 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[5] & 255));
            var9 = this.getSyncInfo(var2);
            var6 = DataHandleUtils.getBoolBit4(var2[3]);
            var10 = GeneralSyncData.mInfoUpdateEntityList.iterator();

            while(var10.hasNext()) {
               SyncListUpdateEntity var12 = (SyncListUpdateEntity)var10.next();
               if (var12.getIndex() == var3) {
                  var12.setLeftIconResId(var5).setRightIconResId(var4).setInfo(var9).setEnable(var6);
                  this.updateSyncActivity((Bundle)null);
                  return;
               }
            }

            GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(var3)).setLeftIconResId(var5).setRightIconResId(var4).setInfo(var9).setEnable(var6));
            this.updateSyncActivity((Bundle)null);
         } else if (var3 >= 11 && var3 <= 18) {
            var3 = 2131233621;
            String var8 = "";
            if (var4 == 0) {
               var7 = false;
               var9 = var8;
            } else {
               label76: {
                  if (var4 != 2) {
                     label50: {
                        if (var4 != 3) {
                           if (var4 == 10) {
                              var9 = this.getSyncInfo(var2);
                              break label50;
                           }

                           if (var4 != 11) {
                              var9 = var8;
                              break label50;
                           }

                           var9 = this.getSyncInfo(var2);
                        } else {
                           var3 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[4] & 255));
                           var9 = var8;
                        }

                        var6 = true;
                        break label76;
                     }
                  } else {
                     var3 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[4] & 255));
                     var9 = var8;
                  }

                  var7 = false;
                  var6 = true;
               }
            }

            var4 = (var2[2] - 11) % 4;
            var10 = GeneralSyncData.mSoftKeyUpdateEntityList.iterator();

            while(var10.hasNext()) {
               SyncSoftKeyUpdateEntity var11 = (SyncSoftKeyUpdateEntity)var10.next();
               if (var11.getIndex() == var4) {
                  var11.setName(var9).setImageResId(var3).setSelected(var7).setVisible(var6);
                  this.updateSyncActivity((Bundle)null);
                  return;
               }
            }

            GeneralSyncData.mSoftKeyUpdateEntityList.add((new SyncSoftKeyUpdateEntity(var4)).setName(var9).setImageResId(var3).setSelected(var7).setVisible(var6));
            this.updateSyncActivity((Bundle)null);
         }
      }

   }

   private void setSyncSrtDown0x71() {
      if (this.compare(mSyncVersion, MsgMgr.SYNC_VERSION.VERSION_V1, MsgMgr.SYNC_VERSION.VERSION_V2)) {
         GeneralSyncData.mInfoLineShowAmount = 3;
         GeneralSyncData.mIsShowSoftKey = false;
         byte[] var1 = this.mCanBusInfoByte;
         String var2 = new String(Arrays.copyOfRange(var1, 2, var1.length));
         Iterator var3 = GeneralSyncData.mInfoUpdateEntityList.iterator();

         while(var3.hasNext()) {
            SyncListUpdateEntity var4 = (SyncListUpdateEntity)var3.next();
            if (var4.getIndex() == 1) {
               var4.setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var2).setEnable(false);
               this.updateSyncActivity((Bundle)null);
               return;
            }
         }

         GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(1)).setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var2).setEnable(false));
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void setSyncSrtShort0x72() {
      if (this.compare(mSyncVersion, MsgMgr.SYNC_VERSION.VERSION_V1, MsgMgr.SYNC_VERSION.VERSION_V2)) {
         GeneralSyncData.mInfoLineShowAmount = 3;
         GeneralSyncData.mIsShowSoftKey = false;
         byte[] var1 = this.mCanBusInfoByte;
         String var3 = new String(Arrays.copyOfRange(var1, 2, var1.length));
         Iterator var4 = GeneralSyncData.mInfoUpdateEntityList.iterator();

         while(var4.hasNext()) {
            SyncListUpdateEntity var2 = (SyncListUpdateEntity)var4.next();
            if (var2.getIndex() == 2) {
               var2.setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var3).setEnable(false);
               this.updateSyncActivity((Bundle)null);
               return;
            }
         }

         GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(2)).setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var3).setEnable(false));
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void setSyncSrtUp0x70() {
      if (this.compare(mSyncVersion, MsgMgr.SYNC_VERSION.VERSION_V1, MsgMgr.SYNC_VERSION.VERSION_V2)) {
         GeneralSyncData.mInfoLineShowAmount = 3;
         GeneralSyncData.mIsShowSoftKey = false;
         byte[] var1 = this.mCanBusInfoByte;
         String var2 = new String(Arrays.copyOfRange(var1, 2, var1.length));
         Iterator var4 = GeneralSyncData.mInfoUpdateEntityList.iterator();

         while(var4.hasNext()) {
            SyncListUpdateEntity var3 = (SyncListUpdateEntity)var4.next();
            if (var3.getIndex() == 0) {
               var3.setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var2).setEnable(false);
               this.updateSyncActivity((Bundle)null);
               return;
            }
         }

         GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(0)).setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var2).setEnable(false));
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void setSyncState0x78() {
      int var2 = 0;

      int var1;
      for(var1 = 0; var1 < 2; ++var1) {
         if ((this.mCanBusInfoInt[3] & 1 << var1) == 0) {
            GeneralSyncData.mSyncTopIconResIdArray[var1 + 1] = 2131233649;
         } else {
            GeneralSyncData.mSyncTopIconResIdArray[var1 + 1] = this.mTopIconArray.get(var1);
         }
      }

      for(var1 = 3; var1 < 7; ++var1) {
         if ((this.mCanBusInfoInt[3] & 1 << var1) == 0) {
            GeneralSyncData.mSyncTopIconResIdArray[var1] = 2131233649;
         } else {
            GeneralSyncData.mSyncTopIconResIdArray[var1] = this.mTopIconArray.get(var1);
         }
      }

      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + 103;
      var1 = var3;
      if (var3 > 107) {
         var1 = 0;
      }

      var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) + 97;
      if (var3 <= 101) {
         var2 = var3;
      }

      GeneralSyncData.mSyncTopIconResIdArray[7] = this.getSyncIconResId(this.mContext, "ford_sync_icon_" + var1);
      GeneralSyncData.mSyncTopIconResIdArray[8] = this.getSyncIconResId(this.mContext, "ford_sync_icon_" + var2);
      this.updateSyncActivity((Bundle)null);
   }

   private void setSyncSwitch0x79() {
      if (this.compare(this.mCanBusInfoInt[2], 1, 3, 4)) {
         this.startSyncActivity(this.mContext);
      } else if (this.compare(this.mCanBusInfoInt[2], 0, 2, 5)) {
         if (SystemUtil.isForeground(this.mContext, SyncActivity.class.getName())) {
            this.realKeyClick(this.mContext, 50);
         } else {
            this.enterNoSource();
         }
      }

   }

   private void setSyncTalkingTimeInfo() {
      if (MsgMgr.SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         GeneralSyncData.mSyncTime = this.mDecimalFormat00.format((long)this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[4]);
         GeneralSyncData.mIsSyncTimeChange = true;
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void setSyncVersion() {
      if (mSyncVersion != MsgMgr.SYNC_VERSION.values()[this.mCanBusInfoInt[2]]) {
         mSyncVersion = MsgMgr.SYNC_VERSION.values()[this.mCanBusInfoInt[2]];
         int var1 = null.$SwitchMap$com$hzbhd$canbus$car$_349$MsgMgr$SYNC_VERSION[mSyncVersion.ordinal()];
         if (var1 != 1 && var1 != 2) {
            if (var1 == 3) {
               GeneralSyncData.mInfoLineShowAmount = 5;
               GeneralSyncData.mIsShowSoftKey = true;
            }
         } else {
            GeneralSyncData.mInfoLineShowAmount = 3;
            GeneralSyncData.mIsShowSoftKey = false;
         }

         GeneralSyncData.mSyncTime = "";
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void setTableControl0x7A() {
   }

   private void setTrackData0x66() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4768, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setTurnSignalInfo() {
      ArrayList var2 = new ArrayList();
      String var1 = this.mContext.getResources().getString(2131769288);
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 0) {
         var1 = this.mContext.getResources().getString(2131769288);
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1) {
         var1 = this.mContext.getResources().getString(2131769289);
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 2) {
         var1 = this.mContext.getResources().getString(2131769290);
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 3) {
         var1 = this.mContext.getResources().getString(2131769291);
      }

      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "mazda_binary_car_set77"), var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWarningInfo(Context var1) {
      int[] var9 = this.mCanBusInfoInt;
      var9[2] &= 254;
      var9[3] &= 127;
      var9[4] &= 128;
      var9[5] &= 3;
      var9[11] &= 15;
      var9[12] &= 1;
      var9[13] &= 3;
      var9[14] &= 3;
      var9[15] &= 200;
      var9[16] &= 1;
      var9[17] &= 160;
      if (this.isDataChange(var9)) {
         ArrayList var12 = new ArrayList();
         int var2 = 0;

         boolean var4;
         boolean var5;
         for(var4 = false; var2 < 16; ++var2) {
            for(int var3 = 0; var3 < 8; var4 = var5) {
               int[] var10 = this.mCanBusInfoInt;
               int var7 = var2 + 2;
               int var6 = var10[var7];
               int var8 = 1 << var3;
               var5 = var4;
               if ((var6 & var8) != 0) {
                  var12.add(new WarningEntity(CommUtil.getStrByResId(var1, "_81_alert_info_" + var2 + "_" + var3)));
                  var5 = var4;
                  if ((this.mPreWarningData[var7] & var8) == 0) {
                     var5 = true;
                  }
               }

               ++var3;
            }
         }

         if (var4) {
            this.showWarningActivity(this.mContext);
         }

         int[] var11 = this.mCanBusInfoInt;
         this.mPreWarningData = Arrays.copyOf(var11, var11.length);
         GeneralWarningDataData.dataList = var12;
         this.updateWarningActivity((Bundle)null);
      }

   }

   private void setWarningVolumeState() {
      ArrayList var3 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = "Minimum volume";
      } else if (var1 == 15) {
         var2 = "Maximum volume";
      } else if (var1 == 6) {
         var2 = "ordinary";
      } else if (var1 == 12) {
         var2 = "high";
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_349_setting_5_0"), var2)).setProgress(this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setWheelKeyInfo0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 7) {
                           if (var1 != 24) {
                              if (var1 != 61) {
                                 if (var1 != 63) {
                                    if (var1 != 86) {
                                       if (var1 != 87) {
                                          if (var1 != 240) {
                                             if (var1 != 241) {
                                                switch (var1) {
                                                   case 14:
                                                      this.buttonKey(45);
                                                      break;
                                                   case 15:
                                                      this.buttonKey(46);
                                                      break;
                                                   case 16:
                                                      this.buttonKey(47);
                                                      break;
                                                   case 17:
                                                      this.buttonKey(48);
                                                      break;
                                                   case 18:
                                                      this.buttonKey(49);
                                                      break;
                                                   default:
                                                      switch (var1) {
                                                         case 32:
                                                            this.buttonKey(32);
                                                            break;
                                                         case 33:
                                                            this.buttonKey(33);
                                                            break;
                                                         case 34:
                                                            this.buttonKey(34);
                                                            break;
                                                         case 35:
                                                            this.buttonKey(35);
                                                            break;
                                                         case 36:
                                                            this.buttonKey(36);
                                                            break;
                                                         case 37:
                                                            this.buttonKey(37);
                                                            break;
                                                         case 38:
                                                            this.buttonKey(38);
                                                            break;
                                                         case 39:
                                                            this.buttonKey(39);
                                                            break;
                                                         case 40:
                                                            this.buttonKey(40);
                                                            break;
                                                         case 41:
                                                            this.buttonKey(41);
                                                            break;
                                                         case 42:
                                                            this.dealSyncKey(this.mContext, 23);
                                                            break;
                                                         case 43:
                                                            this.dealSyncKey(this.mContext, 24);
                                                            break;
                                                         default:
                                                            switch (var1) {
                                                               case 51:
                                                                  this.switchSyncActivity(this.mContext);
                                                                  break;
                                                               case 52:
                                                                  this.buttonKey(76);
                                                                  break;
                                                               case 53:
                                                                  this.buttonKey(130);
                                                                  break;
                                                               case 54:
                                                                  this.buttonKey(141);
                                                                  break;
                                                               case 55:
                                                                  this.buttonKey(151);
                                                                  break;
                                                               case 56:
                                                                  this.buttonKey(73);
                                                                  break;
                                                               case 57:
                                                                  this.buttonKey(188);
                                                                  break;
                                                               default:
                                                                  switch (var1) {
                                                                     case 72:
                                                                        this.buttonKey(49);
                                                                        break;
                                                                     case 73:
                                                                        this.buttonKey(47);
                                                                        break;
                                                                     case 74:
                                                                        this.buttonKey(48);
                                                                        break;
                                                                     case 75:
                                                                        this.buttonKey(45);
                                                                        break;
                                                                     case 76:
                                                                        this.buttonKey(46);
                                                                        break;
                                                                     default:
                                                                        switch (var1) {
                                                                           case 82:
                                                                              this.buttonKey(206);
                                                                              break;
                                                                           case 83:
                                                                              this.buttonKey(207);
                                                                              break;
                                                                           case 84:
                                                                              this.buttonKey(31);
                                                                              break;
                                                                           default:
                                                                              switch (var1) {
                                                                                 case 89:
                                                                                    this.buttonKey(4);
                                                                                    break;
                                                                                 case 90:
                                                                                    this.buttonKey(3);
                                                                                    break;
                                                                                 case 91:
                                                                                    this.buttonKey(57);
                                                                                    break;
                                                                                 case 92:
                                                                                    this.buttonKey(33);
                                                                                    break;
                                                                                 case 93:
                                                                                    this.buttonKey(34);
                                                                                    break;
                                                                                 case 94:
                                                                                    this.buttonKey(35);
                                                                                    break;
                                                                                 case 95:
                                                                                    this.buttonKey(36);
                                                                              }
                                                                        }
                                                                  }
                                                            }
                                                      }
                                                }
                                             } else {
                                                this.buttonKey(8);
                                             }
                                          } else {
                                             this.buttonKey(7);
                                          }
                                       } else {
                                          this.buttonKey(58);
                                       }
                                    } else {
                                       this.buttonKey(94);
                                    }
                                 } else {
                                    this.buttonKey(1);
                                 }
                              } else {
                                 this.buttonKey(196);
                              }
                           } else {
                              this.buttonKey(187);
                           }
                        } else {
                           this.buttonKey(2);
                        }
                     } else {
                        this.buttonKey(188);
                     }
                  } else {
                     this.buttonKey(21);
                  }
               } else {
                  this.buttonKey(20);
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

   private void showWarningActivity(Context var1) {
      this.mHandler.removeMessages(11);
      this.startWarningActivity(var1);
      Message var2 = new Message();
      var2.what = 11;
      var2.obj = var1;
      this.mHandler.sendMessageDelayed(var2, 5000L);
   }

   private void startSyncActivity(Context var1) {
      Intent var2 = new Intent(var1, SyncActivity.class);
      var2.setFlags(268435456);
      var1.startActivity(var2);
   }

   private void switchSyncActivity(Context var1) {
      if (this.mCanBusInfoInt[3] == 1) {
         if (SystemUtil.isForeground(var1, SyncActivity.class.getName())) {
            this.realKeyClick(var1, 50);
         } else {
            Intent var2 = new Intent();
            var2.setComponent(Constant.SyncActivity);
            var2.setFlags(268435456);
            var1.startActivity(var2);
         }
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      AppEnableUtil.enableApp(var1, Constant.SyncActivity);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 3, (byte)34}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), this.mMeidaInfos);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 7, (byte)48}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), this.mMeidaInfos);
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      this.mMeidaInfos[0].setInfo(var1);
      this.mMeidaInfos[1].setInfo(var2);
      this.mMeidaInfos[2].setInfo(var3);
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), this.mMeidaInfos);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 11, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), var1);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
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
      if (var3 != 13) {
         if (var3 != 20) {
            if (var3 != 42) {
               if (var3 != 48) {
                  if (var3 != 64) {
                     if (var3 != 80) {
                        if (var3 != 39) {
                           if (var3 != 40) {
                              if (var3 != 82) {
                                 if (var3 != 83) {
                                    switch (var3) {
                                       case 32:
                                          this.setWheelKeyInfo0x20();
                                          break;
                                       case 33:
                                          this.setAirData0x21();
                                          break;
                                       case 34:
                                          this.setRearRadarInfo0x22();
                                          break;
                                       case 35:
                                          this.setFrontRadarInfo0x23();
                                          break;
                                       case 36:
                                          this.setDoorData0x24();
                                          break;
                                       case 37:
                                          this.setPanoramicData0x25();
                                          break;
                                       default:
                                          switch (var3) {
                                             case 102:
                                                this.setTrackData0x66();
                                                break;
                                             case 103:
                                                this.setTurnSignalInfo();
                                                break;
                                             case 104:
                                                this.setRotatingSpeed();
                                                break;
                                             case 105:
                                                this.setAmbientLightState();
                                                break;
                                             case 106:
                                                this.setSpeedState();
                                                break;
                                             case 107:
                                                this.setAirData20x6B();
                                                break;
                                             case 108:
                                                this.setMultiFunctionSeatStatus0x6C();
                                                break;
                                             default:
                                                switch (var3) {
                                                   case 112:
                                                      this.setSyncSrtUp0x70();
                                                      break;
                                                   case 113:
                                                      this.setSyncSrtDown0x71();
                                                      break;
                                                   case 114:
                                                      this.setSyncSrtShort0x72();
                                                      break;
                                                   default:
                                                      switch (var3) {
                                                         case 120:
                                                            this.setSyncState0x78();
                                                            break;
                                                         case 121:
                                                            this.setSyncSwitch0x79();
                                                            break;
                                                         case 122:
                                                            this.setTableControl0x7A();
                                                      }
                                                }
                                          }
                                    }
                                 } else {
                                    this.setSyncTalkingTimeInfo();
                                 }
                              } else {
                                 this.setSyncMediaTimeInfo();
                              }
                           } else {
                              this.setAutoParkStateInfo(var1);
                           }
                        } else {
                           this.setLanguageInfo();
                        }
                     } else {
                        this.setSyncMenuInfo();
                     }
                  } else {
                     this.setSyncVersion();
                  }
               } else {
                  this.setVersionInfo0x30();
               }
            } else {
               this.setWarningInfo(var1);
            }
         } else {
            this.setBackLightInfo0x14();
         }
      } else {
         this.setWarningVolumeState();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)(var1 - 2000), (byte)var3, (byte)var4, (byte)var8, (byte)var6, (byte)var7});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         var4 = var5;
      }

      int[] var18 = this.getTimeWithSeconds(var2);
      var1 = (byte)var4;
      byte var16 = (byte)var6;
      byte var15 = (byte)var18[0];
      byte var14 = (byte)var18[1];
      byte var17 = (byte)var18[2];
      byte[] var19 = DataHandleUtils.byteMerger(new byte[]{22, -64, 2, (byte)255}, new byte[]{0, var1, var16, var15, var14, var17});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var19);
      this.mMeidaInfos[0].setInfo(var11);
      this.mMeidaInfos[1].setInfo(var12);
      this.mMeidaInfos[2].setInfo(var13);
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), this.mMeidaInfos);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = DataHandleUtils.byteMerger(new byte[]{22, -64, 10, (byte)255}, new byte[]{0, 0, 0, 0, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), this.mMeidaInfos);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initActivePark(var1);
      this.initData(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   // $FF: synthetic method
   void lambda$setAutoParkStateInfo$0$com_hzbhd_canbus_car__349_MsgMgr(Context var1) {
      if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         this.closeActiveParkView();
         this.mParkPageUiSet.setShowCusPanoramicView(false);
         this.updateParkUi((Bundle)null, var1);
      } else {
         this.openActiveParView();
         this.mParkPageUiSet.setShowCusPanoramicView(true);
         this.mActiveParkView.hideAlert();
         this.mPanoramicView.hideAlertWindow();
         ActiveParkItem var2 = (ActiveParkItem)this.mActiveParkItemArray.get(this.mCanBusInfoInt[3]);
         if (var2.getLeftImageResId() == 0 && var2.getRightImageResId() == 0) {
            this.mActiveParkView.showAlert();
            this.mPanoramicView.showAlertWindow();
            if (this.mCanBusInfoInt[3] == 28) {
               this.mTimerUtil.startTimer(new TimerTask(this, var1, var2) {
                  int countDown;
                  String text;
                  final MsgMgr this$0;
                  final ActiveParkItem val$activeParkItem;
                  final Context val$context;

                  {
                     this.this$0 = var1;
                     this.val$context = var2;
                     this.val$activeParkItem = var3;
                     this.text = var2.getString(var3.getMessageResId());
                     this.countDown = 10;
                  }

                  public void run() {
                     if (this.countDown >= 0) {
                        Message var1 = new Message();
                        var1.what = 10;
                        var1.obj = this.text + "\n" + this.countDown + CommUtil.getStrByResId(this.val$context, "_197_second");
                        this.this$0.mHandler.sendMessage(var1);
                        --this.countDown;
                     } else {
                        this.this$0.mTimerUtil.stopTimer();
                     }

                  }
               }, 0L, 1000L);
            } else {
               this.mActiveParkView.setAlertText(var2.getMessageResId());
               this.mPanoramicView.setAlertMessage(var2.getMessageResId());
            }
         } else {
            this.mActiveParkView.setLeftSideImage(var2.getLeftImageResId());
            this.mActiveParkView.setRightSideImage(var2.getRightImageResId());
            this.mActiveParkView.setViewText(var2.getMessageResId());
            this.mPanoramicView.refreshIcon(var2.getReverseIcon());
            this.mPanoramicView.setMessage(var2.getMessageResId());
         }

         this.updateParkUi((Bundle)null, var1);
      }
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
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
      int[] var11 = this.getRedioFrequencyHiLo(var2, var3);
      byte var7 = (byte)this.getRadioBandData(var2);
      byte var6 = (byte)var11[1];
      byte var9 = (byte)var11[0];
      byte var8 = (byte)var1;
      byte[] var10 = DataHandleUtils.byteMerger(new byte[]{22, -64, 1, (byte)1}, new byte[]{var7, var6, var9, var8, 0, 0});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), var10);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.FM.ordinal(), this.mMeidaInfos);
   }

   protected void sendDiscEjectMsg(Context var1) {
      super.sendDiscEjectMsg(var1);
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendNormalMessage(DataHandleUtils.byteMerger(new byte[]{22, -64, 0, (byte)0}, new byte[]{0, 0, 0, 0, 0, 0}));
      this.mMeidaInfos[0].setInfo(" ");
      this.mMeidaInfos[1].setInfo(" ");
      this.mMeidaInfos[2].setInfo(" ");
      this.reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add((new SettingUpdateEntity(var1, var2, var3)).setProgress(var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSync(Bundle var1) {
      this.updateSyncActivity(var1);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
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

      private void setViewText(int var1) {
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

   private class DataHandler {
      private List bytes;
      private int size;
      final MsgMgr this$0;

      public DataHandler(MsgMgr var1, Context var2, long var3, long var5) {
         this.this$0 = var1;
         this.bytes = new ArrayList();
         (new Timer()).schedule(new TimerTask(this, var1, var2) {
            final DataHandler this$1;
            final Context val$context;
            final MsgMgr val$this$0;

            {
               this.this$1 = var1;
               this.val$this$0 = var2;
               this.val$context = var3;
            }

            public void run() {
               if (this.this$1.size != 0) {
                  this.this$1.pop(this.val$context);
               }

            }
         }, var3, var5);
      }

      private void pop(Context var1) {
         if (((byte[])this.bytes.get(0))[1] == 81) {
            this.this$0.setSyncMenuItemInfo(var1, (byte[])this.bytes.get(0));
         }

         this.bytes.remove(0);
         this.size = this.bytes.size();
      }

      private void push(byte[] var1) {
         this.bytes.add(var1);
         this.size = this.bytes.size();
      }
   }

   private static class ID3 {
      private final int ID3_INFO_LENGTH = 24;
      private int dataType;
      private String info;
      private String record;

      private ID3(int var1) {
         this.dataType = var1;
         this.info = "";
         this.record = "";
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

   public static enum SYNC_KEY_STATE {
      private static final SYNC_KEY_STATE[] $VALUES;
      HIGHLIGHT_ICON,
      HIGHTLIGHT_TEXT,
      ICON,
      NONE,
      TEXT;

      static {
         SYNC_KEY_STATE var0 = new SYNC_KEY_STATE("NONE", 0);
         NONE = var0;
         SYNC_KEY_STATE var2 = new SYNC_KEY_STATE("ICON", 1);
         ICON = var2;
         SYNC_KEY_STATE var1 = new SYNC_KEY_STATE("HIGHLIGHT_ICON", 2);
         HIGHLIGHT_ICON = var1;
         SYNC_KEY_STATE var3 = new SYNC_KEY_STATE("TEXT", 3);
         TEXT = var3;
         SYNC_KEY_STATE var4 = new SYNC_KEY_STATE("HIGHTLIGHT_TEXT", 4);
         HIGHTLIGHT_TEXT = var4;
         $VALUES = new SYNC_KEY_STATE[]{var0, var2, var1, var3, var4};
      }
   }

   public static enum SYNC_LINE_TEXT_ATT {
      private static final SYNC_LINE_TEXT_ATT[] $VALUES;
      DEEP_GRAY_TEXT,
      GRAY_TEXT,
      GRAY_TEXT_GRAY_BKG,
      HIDDEN,
      NOR_TEXT,
      NOR_TEXT_GRAY_BKG;

      static {
         SYNC_LINE_TEXT_ATT var2 = new SYNC_LINE_TEXT_ATT("NOR_TEXT", 0);
         NOR_TEXT = var2;
         SYNC_LINE_TEXT_ATT var4 = new SYNC_LINE_TEXT_ATT("NOR_TEXT_GRAY_BKG", 1);
         NOR_TEXT_GRAY_BKG = var4;
         SYNC_LINE_TEXT_ATT var3 = new SYNC_LINE_TEXT_ATT("GRAY_TEXT", 2);
         GRAY_TEXT = var3;
         SYNC_LINE_TEXT_ATT var1 = new SYNC_LINE_TEXT_ATT("GRAY_TEXT_GRAY_BKG", 3);
         GRAY_TEXT_GRAY_BKG = var1;
         SYNC_LINE_TEXT_ATT var0 = new SYNC_LINE_TEXT_ATT("DEEP_GRAY_TEXT", 4);
         DEEP_GRAY_TEXT = var0;
         SYNC_LINE_TEXT_ATT var5 = new SYNC_LINE_TEXT_ATT("HIDDEN", 5);
         HIDDEN = var5;
         $VALUES = new SYNC_LINE_TEXT_ATT[]{var2, var4, var3, var1, var0, var5};
      }
   }

   public static enum SYNC_MESSAGE_TYPE {
      private static final SYNC_MESSAGE_TYPE[] $VALUES;
      DIAL_A_NUMBER,
      DIAL_REDIAL,
      NONE,
      SPEECH,
      _1_LINE_4_BUTTON,
      _1_LINE_NO_BUTTON,
      _2_LINE_4_BUTTON,
      _2_LINE_NO_BUTTON,
      _3_LINE_4_BUTTON,
      _3_LINE_NO_BUTTON;

      static {
         SYNC_MESSAGE_TYPE var9 = new SYNC_MESSAGE_TYPE("NONE", 0);
         NONE = var9;
         SYNC_MESSAGE_TYPE var6 = new SYNC_MESSAGE_TYPE("_1_LINE_NO_BUTTON", 1);
         _1_LINE_NO_BUTTON = var6;
         SYNC_MESSAGE_TYPE var4 = new SYNC_MESSAGE_TYPE("_1_LINE_4_BUTTON", 2);
         _1_LINE_4_BUTTON = var4;
         SYNC_MESSAGE_TYPE var0 = new SYNC_MESSAGE_TYPE("_2_LINE_NO_BUTTON", 3);
         _2_LINE_NO_BUTTON = var0;
         SYNC_MESSAGE_TYPE var2 = new SYNC_MESSAGE_TYPE("_2_LINE_4_BUTTON", 4);
         _2_LINE_4_BUTTON = var2;
         SYNC_MESSAGE_TYPE var7 = new SYNC_MESSAGE_TYPE("_3_LINE_NO_BUTTON", 5);
         _3_LINE_NO_BUTTON = var7;
         SYNC_MESSAGE_TYPE var8 = new SYNC_MESSAGE_TYPE("_3_LINE_4_BUTTON", 6);
         _3_LINE_4_BUTTON = var8;
         SYNC_MESSAGE_TYPE var3 = new SYNC_MESSAGE_TYPE("DIAL_REDIAL", 7);
         DIAL_REDIAL = var3;
         SYNC_MESSAGE_TYPE var5 = new SYNC_MESSAGE_TYPE("SPEECH", 8);
         SPEECH = var5;
         SYNC_MESSAGE_TYPE var1 = new SYNC_MESSAGE_TYPE("DIAL_A_NUMBER", 9);
         DIAL_A_NUMBER = var1;
         $VALUES = new SYNC_MESSAGE_TYPE[]{var9, var6, var4, var0, var2, var7, var8, var3, var5, var1};
      }
   }

   public static enum SYNC_VERSION {
      private static final SYNC_VERSION[] $VALUES;
      NONE,
      VERSION_V1,
      VERSION_V2,
      VERSION_V3;

      static {
         SYNC_VERSION var2 = new SYNC_VERSION("NONE", 0);
         NONE = var2;
         SYNC_VERSION var3 = new SYNC_VERSION("VERSION_V1", 1);
         VERSION_V1 = var3;
         SYNC_VERSION var0 = new SYNC_VERSION("VERSION_V2", 2);
         VERSION_V2 = var0;
         SYNC_VERSION var1 = new SYNC_VERSION("VERSION_V3", 3);
         VERSION_V3 = var1;
         $VALUES = new SYNC_VERSION[]{var2, var3, var0, var1};
      }
   }
}
