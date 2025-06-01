package com.hzbhd.canbus.car._85;

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
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
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
   static final int AMPLIFIER_BALANCE_OFFSET = 7;
   static final String SHARE_85_AIR_AUTO_MANUAL_STATUS = "share_85_air_auto_manual_status";
   static int[] mLanguageMapArray;
   static SYNC_VERSION mSyncVersion;
   private final int BACKLIGHT_DATA_MAX = 256;
   private final int BACKLIGHT_SEGMENTS = 5;
   private final long DATA_HANDLE_PERIOD = 32L;
   private final int DATA_TYPE = 1;
   private final int INVAILE_VALUE = -1;
   private final int MSG_ACTIVE_PARK_0X1C_COUNT_DOWN = 10;
   private final int MSG_DISMISS_WARNING_ACTIVITY = 11;
   private final int SEND_GIVEN_MEDIA_MESSAGE = 101;
   private final int SEND_NORMAL_MESSAGE = 102;
   private final String TAG = "_85_MSG";
   private SparseArray mActiveParkItemArray;
   private ActiveParkView mActiveParkView;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private String mAirUnit;
   private boolean mAutoManualStatus;
   private boolean mBackStatus;
   private int mBacklight;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private DataHandler mDataHandler;
   private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");
   private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
   private DecimalFormat mDecimalFormat0p00 = new DecimalFormat("0.00");
   private int mDiiferentId = this.getCurrentCanDifferentId();
   private int mEachId = this.getCurrentEachCanId();
   private boolean mFrontStatus;
   private Handler mHandler;
   private ID3[] mId3s;
   private boolean mIsActiveViewOpen;
   private int mIsDiscExsit;
   private boolean mIsSyncNeedShowDialog;
   private int mKeyStatus;
   private WindowManager.LayoutParams mLayoutParams;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mPanoramicStatus;
   private MyPanoramicView mPanoramicView;
   private ParkPageUiSet mParkPageUiSet;
   private int mPreWindLevel;
   private String mRadioCurrentBand;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private SparseIntArray mSyncMenuIconResIdArray;
   TimerUtil mTimerUtil = new TimerUtil();
   private SparseIntArray mTopIconArray;
   private UiMgr mUiMgr;
   private String mVersionInfo;
   private WindowManager mWindowManager;

   public MsgMgr() {
      mLanguageMapArray = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 18, 22, 27};
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
      GeneralSyncData.mInfoLineShowAmount = 5;
      GeneralSyncData.mIsShowSoftKey = true;
      GeneralSyncData.mSyncTopIconCount = 9;
      GeneralSyncData.mSyncTopIconResIdArray = new int[GeneralSyncData.mSyncTopIconCount];
      RadarInfoUtil.mMinIsClose = true;
      this.initID3();
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
         this.realKeyClick2(var1, var4);
      }

      this.mKeyStatus = this.mCanBusInfoInt[3];
   }

   private int getAirWhat() {
      int[] var4 = new int[6];
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      short var1 = 0;
      var4[0] = var2;
      var4[1] = var3[3] & 240;
      var4[2] = var3[4];
      var2 = var3[6];
      var4[3] = var2 & 246;
      var4[4] = var3[8];
      var4[5] = var3[9];
      int[] var5 = new int[]{var3[5] & 143, var2 & 1};
      if (!Arrays.equals(this.mAirFrontDataNow, var4)) {
         var1 = 1001;
      } else if (!Arrays.equals(this.mAirRearDataNow, var5)) {
         var1 = 1002;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var4, 6);
      this.mAirRearDataNow = Arrays.copyOf(var5, 2);
      return var1;
   }

   private int getData(int... var1) {
      int var3 = 0;

      int var2;
      for(var2 = 0; var3 < var1.length; ++var3) {
         var2 += var1[var3] << var3 * 8;
      }

      return (double)var2 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var2;
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
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

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private int getband(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else {
         return var1.contains("AM") ? 16 : 1;
      }
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
            byte[] var6 = new byte[]{22, -127, 1, 1};
            byte var5 = (byte)GeneralAmplifierData.bandTreble;
            byte[] var7 = new byte[]{22, -58, -63, (byte)GeneralAmplifierData.bandMiddle};
            byte var4 = (byte)GeneralAmplifierData.bandBass;
            byte var3 = (byte)(GeneralAmplifierData.frontRear + 7);
            byte[] var8 = new byte[]{22, -58, -60, (byte)(GeneralAmplifierData.leftRight + 7)};
            this.iterator = Arrays.stream(new byte[][]{var6, {22, -58, -94, 6}, {22, -58, -64, var5}, var7, {22, -58, -62, var4}, {22, -58, -61, var3}, var8}).iterator();
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

   private void initData(Context var1) {
      this.mAirUnit = this.getTempUnitC(var1);
      this.mParkPageUiSet = this.getUiMgr(var1).getParkPageUiSet(var1);
      this.mPanoramicView = (MyPanoramicView)this.getUiMgr(var1).getCusPanoramicView(var1);
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
   }

   private void initID3() {
      this.mId3s = new ID3[]{new ID3(this, 3), new ID3(this, 4), new ID3(this, 5)};
   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemIndeHashMap = new HashMap();
      List var5 = var1.getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var6 = ((SettingPageUiSet.ListBean)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var6.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var6.get(var3);
            String var4 = var7.getTitleSrn();
            if (var7.getStyle() == 2) {
               this.mSettingItemIndeHashMap.put(var4, new SettingProgressEntityProvider(this, var4, var2, var3, var7.getMin()));
            } else {
               this.mSettingItemIndeHashMap.put(var4, new SettingNormalEntityProvider(this, var4, var2, var3));
            }
         }
      }

   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
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

   private void realKeyClick3_1(Context var1, int var2) {
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[3];
      if (var3 != 0) {
         this.realKeyClick3_1(var1, var2, var4[2], var3);
      }
   }

   private void realKeyClick3_2(Context var1, int var2) {
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[3];
      if (var3 != 0) {
         this.realKeyClick3_2(var1, var2, var4[2], var3);
      }
   }

   private void reportID3Info(ID3[] var1) {
      int var4 = var1.length;
      byte var3 = 0;

      for(int var2 = 0; var2 < var4; ++var2) {
         if (var1[var2].isId3Change()) {
            var4 = var1.length;

            for(var2 = var3; var2 < var4; ++var2) {
               ID3 var5 = var1[var2];
               var5.recordId3Info();
               this.reportID3InfoFinal(var5.command, var5.info);
            }

            return;
         }
      }

   }

   private void reportID3InfoFinal(int var1, String var2) {
      try {
         byte[] var4 = DataHandleUtils.exceptBOMHead(var2.getBytes("unicode"));
         this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 17, (byte)var1}, var4), 34), (long)((var1 - 3) * 80));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private String resolveAirTemperature(Context var1, int var2) {
      if (var2 != 30 && var2 != 119) {
         if (var2 != 60 && var2 != 171) {
            if (var2 > 30 && var2 < 60) {
               return (float)var2 / 2.0F + this.getTempUnitC(var1);
            } else {
               return var2 > 119 && var2 < 171 ? var2 / 2 + this.getTempUnitF(var1) : "";
            }
         } else {
            return "HI";
         }
      } else {
         return "LO";
      }
   }

   private String resolveManualAirTemperature(Context var1, int var2) {
      if (var2 == 0) {
         return CommUtil.getStrByResId(var1, "_85_coldest");
      } else if (var2 == 15) {
         return CommUtil.getStrByResId(var1, "middle");
      } else if (var2 == 30) {
         return CommUtil.getStrByResId(var1, "_85_hottest");
      } else if (var2 > 0 && var2 < 15) {
         return CommUtil.getStrByResId(var1, "refrigeration") + " " + (15 - var2);
      } else {
         return var2 > 15 && var2 < 30 ? CommUtil.getStrByResId(var1, "heating") + " " + (var2 - 15) : "";
      }
   }

   private String resolveOutdoorTemperature(int var1) {
      return GeneralAirData.fahrenheit_celsius ? this.mDecimalFormat0p0.format((double)((float)(var1 * 9) / 5.0F + 32.0F)) + this.mAirUnit : var1 + this.mAirUnit;
   }

   private String resolveRearAirTemperature(Context var1, int var2) {
      if (var2 == 9) {
         return "";
      } else if (var2 < 4) {
         return var2 == 0 ? CommUtil.getStrByResId(var1, "_85_coldest") : CommUtil.getStrByResId(var1, "refrigeration") + " " + (4 - var2);
      } else if (var2 == 4) {
         return CommUtil.getStrByResId(var1, "middle");
      } else if (var2 > 4) {
         return var2 == 8 ? CommUtil.getStrByResId(var1, "_85_hottest") : CommUtil.getStrByResId(var1, "heating") + " " + (var2 - 4);
      } else {
         return "";
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

   private void set0x14Backlight() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = (int)((float)this.mCanBusInfoInt[2] / 51.2F);
         if (this.mBacklight != var1) {
            this.mBacklight = var1;
            this.setBacklightLevel(var1 + 1);
         }
      }

   }

   private void set0x16VehicleSpeed() {
      ArrayList var2 = new ArrayList();
      StringBuilder var1 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(0, 0, var1.append(var3[3] * 256 + var3[2]).append(" km/h").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(var4[3] * 256 + var4[2]);
   }

   private void set0x20WheelKey(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 9) {
         if (var2 != 10) {
            if (var2 != 86) {
               if (var2 != 87) {
                  if (var2 != 240) {
                     if (var2 != 241) {
                        switch (var2) {
                           case 0:
                              this.realKeyClick2(var1, 0);
                              break;
                           case 1:
                              this.realKeyClick2(var1, 7);
                              break;
                           case 2:
                              this.realKeyClick2(var1, 8);
                              break;
                           case 3:
                              this.realKeyClick2(var1, 46);
                              break;
                           case 4:
                              this.realKeyClick2(var1, 45);
                              break;
                           case 5:
                              this.realKeyClick2(var1, 14);
                              break;
                           case 6:
                              this.realKeyClick2(var1, 3);
                              break;
                           case 7:
                              this.realKeyClick2(var1, 2);
                              break;
                           default:
                              switch (var2) {
                                 case 14:
                                    this.realKeyClick2(var1, 45);
                                    break;
                                 case 15:
                                    this.realKeyClick2(var1, 46);
                                    break;
                                 case 16:
                                    this.realKeyClick2(var1, 47);
                                    break;
                                 case 17:
                                    this.realKeyClick2(var1, 48);
                                    break;
                                 case 18:
                                    this.realKeyClick2(var1, 49);
                                    break;
                                 default:
                                    switch (var2) {
                                       case 32:
                                          this.dealSyncKey(var1, 13, 48, 32);
                                          break;
                                       case 33:
                                          this.dealSyncKey(var1, 14, 49, 33);
                                          break;
                                       case 34:
                                          this.dealSyncKey(var1, 15, 50, 34);
                                          break;
                                       case 35:
                                          this.dealSyncKey(var1, 16, 51, 35);
                                          break;
                                       case 36:
                                          this.dealSyncKey(var1, 17, 52, 36);
                                          break;
                                       case 37:
                                          this.dealSyncKey(var1, 18, 53, 37);
                                          break;
                                       case 38:
                                          this.dealSyncKey(var1, 19, 54, 38);
                                          break;
                                       case 39:
                                          this.dealSyncKey(var1, 20, 55, 39);
                                          break;
                                       case 40:
                                          this.dealSyncKey(var1, 21, 56, 40);
                                          break;
                                       case 41:
                                          this.dealSyncKey(var1, 22, 57, 41);
                                          break;
                                       case 42:
                                          this.dealSyncKey(var1, 23);
                                          break;
                                       case 43:
                                          this.dealSyncKey(var1, 24);
                                          break;
                                       case 61:
                                          this.realKeyClick2(var1, 196);
                                          break;
                                       case 63:
                                          this.realKeyClick2(var1, 134);
                                          break;
                                       case 104:
                                          this.realKeyClick2(var1, 50);
                                          break;
                                       case 106:
                                          this.realKeyClick2(var1, 58);
                                          break;
                                       case 111:
                                          this.realKeyClick2(var1, 59);
                                          break;
                                       default:
                                          switch (var2) {
                                             case 51:
                                                this.switchSyncActivity(var1);
                                                break;
                                             case 52:
                                                this.realKeyClick2(var1, 76);
                                                break;
                                             case 53:
                                                this.realKeyClick2(var1, 130);
                                                break;
                                             case 54:
                                                this.realKeyClick2(var1, 141);
                                                break;
                                             case 55:
                                                this.dealSyncKey(var1, 2, -1, 52);
                                                break;
                                             case 56:
                                                this.realKeyClick2(var1, 4);
                                                break;
                                             case 57:
                                                this.realKeyClick2(var1, 14);
                                                break;
                                             default:
                                                switch (var2) {
                                                   case 72:
                                                      this.dealSyncKey(var1, 12, -1, 49);
                                                      break;
                                                   case 73:
                                                      this.dealSyncKey(var1, 25, -1, 47);
                                                      break;
                                                   case 74:
                                                      this.dealSyncKey(var1, 26, -1, 48);
                                                      break;
                                                   case 75:
                                                      this.dealSyncKey(var1, 10, -1, 45);
                                                      break;
                                                   case 76:
                                                      this.dealSyncKey(var1, 11, -1, 46);
                                                      break;
                                                   default:
                                                      switch (var2) {
                                                         case 82:
                                                            this.realKeyClick2(var1, 465);
                                                            break;
                                                         case 83:
                                                            this.realKeyClick2(var1, 466);
                                                            break;
                                                         case 84:
                                                            this.realKeyClick2(var1, 31);
                                                            break;
                                                         default:
                                                            switch (var2) {
                                                               case 89:
                                                                  this.realKeyClick2(var1, 4);
                                                                  break;
                                                               case 90:
                                                                  this.realKeyClick2(var1, 3);
                                                                  break;
                                                               case 91:
                                                                  this.realKeyClick2(var1, 152);
                                                                  break;
                                                               case 92:
                                                                  this.dealSyncKey(var1, 28);
                                                                  break;
                                                               case 93:
                                                                  this.dealSyncKey(var1, 29);
                                                                  break;
                                                               case 94:
                                                                  this.dealSyncKey(var1, 30);
                                                                  break;
                                                               case 95:
                                                                  this.dealSyncKey(var1, 31);
                                                                  break;
                                                               case 96:
                                                                  this.realKeyClick3_2(var1, 48);
                                                                  break;
                                                               case 97:
                                                                  this.realKeyClick3_2(var1, 47);
                                                                  break;
                                                               case 98:
                                                                  this.realKeyClick2(var1, 17);
                                                            }
                                                      }
                                                }
                                          }
                                    }
                              }
                        }
                     } else {
                        this.realKeyClick3_1(var1, 8);
                     }
                  } else {
                     this.realKeyClick3_1(var1, 7);
                  }
               } else {
                  this.dealSyncKey(var1, 6, -1, 185);
               }
            } else {
               this.realKeyClick2(var1, 94);
            }
         } else {
            this.realKeyClick2(var1, 15);
         }
      } else {
         this.realKeyClick2(var1, 14);
      }

   }

   private void set0x22RearRadar(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(31, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x23FrontRadar(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(31, var2[2], var2[3], var2[4], var2[5]);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setProbeRadarLocationData(31, var2[7], var2[6], 0, 0);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24BaseInfo(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

      int[] var3 = this.mCanBusInfoInt;
      var3[2] = 0;
      var3[3] &= 224;
      if (this.isDataChange(var3)) {
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
         this.setSettingData(new String[]{"ford_range_unit"}, new Object[]{var2});
         String var7;
         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            var7 = "open";
         } else {
            var7 = "close";
         }

         String var4;
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            var4 = "_81_max_vol_half";
         } else {
            var4 = "_81_unlimit";
         }

         String var5;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            var5 = "_103_car_setting_value_7_1";
         } else {
            var5 = "_103_car_setting_value_7_0";
         }

         ArrayList var6 = new ArrayList();
         var6.add(new DriverUpdateEntity(0, 1, CommUtil.getStrByResId(var1, var7)));
         var6.add(new DriverUpdateEntity(0, 2, CommUtil.getStrByResId(var1, var4)));
         var6.add(new DriverUpdateEntity(0, 3, CommUtil.getStrByResId(var1, var5)));
         this.updateGeneralDriveData(var6);
         this.updateDriveDataActivity((Bundle)null);
         ArrayList var8 = new ArrayList();
         var8.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]) ^ true));
         var8.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])));
         GeneralParkData.dataList = var8;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x25RadarStatus(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         boolean var2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         String var4 = "open";
         String var3;
         if (var2) {
            var3 = "open";
         } else {
            var3 = "close";
         }

         if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            var4 = "close";
         }

         ArrayList var5 = new ArrayList();
         var5.add(new DriverUpdateEntity(0, 4, CommUtil.getStrByResId(var1, var3)));
         var5.add(new DriverUpdateEntity(0, 5, CommUtil.getStrByResId(var1, var4)));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0x27Language() {
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

   private void set0x28ActiveParkData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         this.mTimerUtil.stopTimer();
         this.runOnUi(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
      }

   }

   private void set0x29AirInfo(Context var1) {
      int var3 = this.getAirWhat();
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      if (GeneralAirData.fahrenheit_celsius) {
         this.mAirUnit = this.getTempUnitF(var1);
      } else {
         this.mAirUnit = this.getTempUnitC(var1);
      }

      GeneralAirData.auto_manual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      this.updateOutDoorTemp(var1, this.resolveOutdoorTemperature(this.mCanBusInfoByte[7]));
      if (this.mAutoManualStatus ^ GeneralAirData.auto_manual) {
         boolean var4 = GeneralAirData.auto_manual;
         this.mAutoManualStatus = var4;
         SharePreUtil.setBoolValue(var1, "share_85_air_auto_manual_status", var4);
         this.getUiMgr(var1).getOnAirAutoManualChangeListener().onChange(this.mAutoManualStatus);
      }

      byte[] var5 = this.mCanBusInfoByte;
      var5[3] = (byte)(var5[3] & 239);
      var5[6] = (byte)(var5[6] & 247);
      var5[7] = 0;
      if (!this.isAirMsgRepeat(var5)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         if (GeneralAirData.auto) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
         } else {
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         }

         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         if (GeneralAirData.auto_manual) {
            GeneralAirData.front_left_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[8]);
         } else {
            GeneralAirData.front_left_temperature = this.resolveManualAirTemperature(var1, this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
         }

         GeneralAirData.rear_temperature = this.resolveRearAirTemperature(var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4));
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 3);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_auto_wind = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2);
         int var2 = var3;
         if (var3 == 0) {
            var2 = var3;
            if (this.mPreWindLevel != GeneralAirData.front_wind_level) {
               var2 = 3;
            }
         }

         this.mPreWindLevel = GeneralAirData.front_wind_level;
         this.updateAirActivity(var1, var2);
      }
   }

   private void set0x30VersionInfo(Context var1) {
      Log.i("_85_MSG", "set0x30VersionInfo: ");
      String var2 = this.getVersionStr(this.mCanBusInfoByte);
      this.mVersionInfo = var2;
      this.updateVersionInfo(var1, var2);
   }

   private void set0x40SyncVersion() {
      if (mSyncVersion != SYNC_VERSION.values()[this.mCanBusInfoInt[2]]) {
         mSyncVersion = SYNC_VERSION.values()[this.mCanBusInfoInt[2]];
         int var1 = null.$SwitchMap$com$hzbhd$canbus$car$_85$MsgMgr$SYNC_VERSION[mSyncVersion.ordinal()];
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

   private void set0x48VehicleSetting(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
         int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
         int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
         int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
         int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
         int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
         int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
         int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
         int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
         int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         this.setSettingData(new String[]{"_176_car_setting_title_21", "_85_driver_seat_massage_backrest", "_85_driver_seat_massage_cushion", "_85_driver_seat_adjust_upper_plus", "_85_driver_seat_adjust_upper_minus", "_85_driver_seat_adjust_middle_plus", "_85_driver_seat_adjust_middle_minus", "_85_driver_seat_adjust_lower_plus", "_85_driver_seat_adjust_lower_minus", "_85_passenger_seat_massage_backrest", "_85_passenger_seat_massage_cushion", "_85_passenger_seat_adjust_upper_plus", "_85_passenger_seat_adjust_upper_minus", "_85_passenger_seat_adjust_middle_plus", "_85_passenger_seat_adjust_middle_minus", "_85_passenger_seat_adjust_lower_plus", "_85_passenger_seat_adjust_lower_minus"}, new Object[]{var9, var14, var7, var8, var15, var4, var13, var2, var5, var11, var12, var17, var3, var10, var18, var6, var16});
         int[] var19 = this.mCanBusInfoInt;
         if (var19.length > 10) {
            var3 = DataHandleUtils.getIntFromByteWithBit(var19[10], 6, 2);
            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1);
            this.setSettingData(new String[]{"speed_linkage_volume_adjustment", "_85_sound_mode"}, new Object[]{var3, var2});
            GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
            GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
            GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4);
            GeneralAmplifierData.frontRear = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4) - 7;
            GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4) - 7;
            this.saveAmplifierData(var1, this.mCanId);
            this.updateAmplifierActivity((Bundle)null);
         }
      }

   }

   private void set0x49PanoramicStatus() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         boolean var1;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (this.mPanoramicStatus != var1) {
            this.mPanoramicStatus = var1;
            this.forceReverse(this.mContext, var1);
         }
      }

   }

   private void set0x50SyncMenuInfo() {
      if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         GeneralSyncData.mSelectedLineIndex = this.mCanBusInfoInt[3] - 1;
         int[] var2 = this.mCanBusInfoInt;
         boolean var1;
         if (var2[5] != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         this.mIsSyncNeedShowDialog = var1;
         if (var1) {
            GeneralSyncData.mSelectedLineIndex = var2[6] - 1;
         }

         GeneralSyncData.mSyncTopIconResIdArray[0] = this.mSyncMenuIconResIdArray.get(this.mCanBusInfoInt[7]);
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void set0x51SyncMenuItemInfo(Context var1, byte[] var2) {
      if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         int var3 = var2[3];
         boolean var6 = false;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var3, 0, 4);
         var3 = var2[2];
         boolean var7 = true;
         String var9;
         if (var3 >= 1 && var3 <= 10) {
            int var5 = (var3 - 1) % 5;
            var3 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[4] & 255));
            var4 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[5] & 255));
            var9 = this.getSyncInfo(var2);
            var6 = DataHandleUtils.getBoolBit4(var2[3]);
            Iterator var13 = GeneralSyncData.mInfoUpdateEntityList.iterator();

            while(var13.hasNext()) {
               SyncListUpdateEntity var11 = (SyncListUpdateEntity)var13.next();
               if (var11.getIndex() == var5) {
                  var11.setLeftIconResId(var3).setRightIconResId(var4).setInfo(var9).setEnable(var6);
                  this.updateSyncActivity((Bundle)null);
                  return;
               }
            }

            GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(var5)).setLeftIconResId(var3).setRightIconResId(var4).setInfo(var9).setEnable(var6));
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
            Iterator var10 = GeneralSyncData.mSoftKeyUpdateEntityList.iterator();

            while(var10.hasNext()) {
               SyncSoftKeyUpdateEntity var12 = (SyncSoftKeyUpdateEntity)var10.next();
               if (var12.getIndex() == var4) {
                  var12.setName(var9).setImageResId(var3).setSelected(var7).setVisible(var6);
                  this.updateSyncActivity((Bundle)null);
                  return;
               }
            }

            GeneralSyncData.mSoftKeyUpdateEntityList.add((new SyncSoftKeyUpdateEntity(var4)).setName(var9).setImageResId(var3).setSelected(var7).setVisible(var6));
            this.updateSyncActivity((Bundle)null);
         }
      }

   }

   private void set0x52SyncMediaTime() {
      if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         GeneralSyncData.mSyncTime = this.mDecimalFormat00.format((long)this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[4]);
         GeneralSyncData.mIsSyncTimeChange = true;
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void set0x53SyncPhoneTime() {
      if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
         GeneralSyncData.mSyncTime = this.mDecimalFormat00.format((long)this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[4]);
         GeneralSyncData.mIsSyncTimeChange = true;
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void set0x56SyncHostControl(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var2 != 32) {
         if (var2 != 34 && var2 != 35) {
            switch (var2) {
               case 16:
                  this.realKeyClick(var1, 130);
                  break;
               case 17:
                  switch (var3[3]) {
                     case 1:
                     case 2:
                        FutureUtil.instance.playMpeg();
                        return;
                     case 3:
                        FutureUtil.instance.shuffleMpeg();
                        return;
                     case 4:
                        FutureUtil.instance.repeatMpeg();
                        return;
                     case 5:
                        FutureUtil.instance.prevMpeg();
                        return;
                     case 6:
                        FutureUtil.instance.nextMpeg();
                        return;
                     default:
                        return;
                  }
               case 18:
                  FutureUtil var13 = FutureUtil.instance;
                  MpegConstantsDef.K_SELECT var11 = MpegConstantsDef.K_SELECT.TITLE_SELECT;
                  int[] var4 = this.mCanBusInfoInt;
                  var13.discGoto(var11, this.getData(var4[3], var4[4]));
            }
         } else {
            this.realKeyClick4(var1, DataHandleUtils.rangeNumber(var3[3], 1, 6) + 32);
         }
      } else {
         var2 = var3[4];
         NullPointerException var10000;
         boolean var10001;
         if (var2 == 0 && var3[5] == 0) {
            var2 = var3[3];
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        return;
                     }

                     try {
                        this.changeBandAm2();
                        return;
                     } catch (NullPointerException var5) {
                        var10000 = var5;
                        var10001 = false;
                     }
                  } else {
                     try {
                        this.changeBandAm1();
                        return;
                     } catch (NullPointerException var6) {
                        var10000 = var6;
                        var10001 = false;
                     }
                  }
               } else {
                  try {
                     this.changeBandFm2();
                     return;
                  } catch (NullPointerException var7) {
                     var10000 = var7;
                     var10001 = false;
                  }
               }
            } else {
               try {
                  this.changeBandFm1();
                  return;
               } catch (NullPointerException var8) {
                  var10000 = var8;
                  var10001 = false;
               }
            }
         } else {
            label94: {
               try {
                  var2 = this.getData(var2, var3[5]);
                  if (this.isBandFm(this.mRadioCurrentBand)) {
                     var2 = DataHandleUtils.rangeNumber(var2, 8750, 10800);
                     FutureUtil.instance.setCurrentFreq(Float.toString((float)var2 / 100.0F));
                     return;
                  }
               } catch (NullPointerException var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label94;
               }

               try {
                  var2 = DataHandleUtils.rangeNumber(var2, 522, 1710);
                  FutureUtil.instance.setCurrentFreq(Integer.toString(var2));
                  return;
               } catch (NullPointerException var9) {
                  var10000 = var9;
                  var10001 = false;
               }
            }
         }

         NullPointerException var12 = var10000;
         var12.printStackTrace();
      }

   }

   private void set0x70SyncSrtUp() {
      if (this.compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
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

   private void set0x71SyncSrtDown() {
      if (this.compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
         GeneralSyncData.mInfoLineShowAmount = 3;
         GeneralSyncData.mIsShowSoftKey = false;
         byte[] var1 = this.mCanBusInfoByte;
         String var4 = new String(Arrays.copyOfRange(var1, 2, var1.length));
         Iterator var2 = GeneralSyncData.mInfoUpdateEntityList.iterator();

         while(var2.hasNext()) {
            SyncListUpdateEntity var3 = (SyncListUpdateEntity)var2.next();
            if (var3.getIndex() == 1) {
               var3.setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var4).setEnable(false);
               this.updateSyncActivity((Bundle)null);
               return;
            }
         }

         GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(1)).setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var4).setEnable(false));
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void set0x72SyncSrtShort() {
      if (this.compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
         GeneralSyncData.mInfoLineShowAmount = 3;
         GeneralSyncData.mIsShowSoftKey = false;
         byte[] var1 = this.mCanBusInfoByte;
         String var4 = new String(Arrays.copyOfRange(var1, 2, var1.length));
         Iterator var2 = GeneralSyncData.mInfoUpdateEntityList.iterator();

         while(var2.hasNext()) {
            SyncListUpdateEntity var3 = (SyncListUpdateEntity)var2.next();
            if (var3.getIndex() == 2) {
               var3.setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var4).setEnable(false);
               this.updateSyncActivity((Bundle)null);
               return;
            }
         }

         GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(2)).setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var4).setEnable(false));
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void set0x78SyncStatus(Context var1) {
      int var3 = 0;

      int var2;
      for(var2 = 0; var2 < 2; ++var2) {
         if ((this.mCanBusInfoInt[3] & 1 << var2) == 0) {
            GeneralSyncData.mSyncTopIconResIdArray[var2 + 1] = 2131233649;
         } else {
            GeneralSyncData.mSyncTopIconResIdArray[var2 + 1] = this.mTopIconArray.get(var2);
         }
      }

      for(var2 = 3; var2 < 7; ++var2) {
         if ((this.mCanBusInfoInt[3] & 1 << var2) == 0) {
            GeneralSyncData.mSyncTopIconResIdArray[var2] = 2131233649;
         } else {
            GeneralSyncData.mSyncTopIconResIdArray[var2] = this.mTopIconArray.get(var2);
         }
      }

      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + 103;
      var2 = var4;
      if (var4 > 107) {
         var2 = 0;
      }

      var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) + 97;
      if (var4 <= 101) {
         var3 = var4;
      }

      GeneralSyncData.mSyncTopIconResIdArray[7] = this.getSyncIconResId(var1, "ford_sync_icon_" + var2);
      GeneralSyncData.mSyncTopIconResIdArray[8] = this.getSyncIconResId(var1, "ford_sync_icon_" + var3);
      this.updateSyncActivity((Bundle)null);
   }

   private void set0x79SyncSwitchRequest(Context var1) {
      if (this.compare(this.mCanBusInfoInt[2], 1, 3, 4)) {
         this.startSyncActivity(var1);
      } else if (this.compare(this.mCanBusInfoInt[2], 0, 2, 5)) {
         if (SystemUtil.isForeground(var1, SyncActivity.class.getName())) {
            this.realKeyClick(var1, 50);
         } else {
            this.enterNoSource();
         }
      }

   }

   private void setSettingData(String[] var1, Object[] var2) {
      if (var1.length == var2.length) {
         ArrayList var4 = new ArrayList();

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (this.mSettingItemIndeHashMap.containsKey(var1[var3])) {
               var4.add(((AbstractSettingEntityProvider)this.mSettingItemIndeHashMap.get(var1[var3])).getEntity(var2[var3]));
            }
         }

         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      } else {
         Log.i("_85_MSG", "setSettingData: Unequal length");
      }

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
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.SyncActivity);
      this.initData(var1);
      this.initSettingsItem(this.getUiMgr(var1).getSettingUiSet(var1));
      this.initActivePark(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mAutoManualStatus = SharePreUtil.getBoolValue(var1, "share_85_air_auto_manual_status", false);
      this.getUiMgr(var1).getOnAirAutoManualChangeListener().onChange(this.mAutoManualStatus);
   }

   public void auxInInfoChange() {
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, 48});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0}, 80L);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendNormalMessage(new byte[]{22, -59, 102, 4, 0, 0});
      var1 = " ".getBytes();
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1, 1}, var1), 34), 160L);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendNormalMessage(new byte[]{22, -64, 11, 0});
      this.sendNormalMessage(new byte[]{22, -59, 102, 1, 0, 0}, 80L);
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1, 1}, var1), 34), 160L);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendNormalMessage(new byte[]{22, -64, 11, 0});
      this.sendNormalMessage(new byte[]{22, -59, 102, 3, 0, 0}, 80L);
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1, 1}, var1), 34), 160L);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      if (var3) {
         if (var1 == 0) {
            this.sendNormalMessage(new byte[]{22, -59, 102, 4, 0, 0});
         }
      } else {
         this.sendNormalMessage(new byte[]{22, -59, 102, 0, 0, 0});
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      this.sendNormalMessage(new byte[]{22, -64, 11, 0});
      int[] var5 = this.getTime(var4);
      this.sendNormalMessage(new byte[]{22, -59, 102, 2, (byte)var5[1], (byte)var5[2]}, 80L);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var5 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var5;
      int var3 = var5[1];
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 32) {
               if (var3 != 48) {
                  if (var3 != 64) {
                     if (var3 != 86) {
                        if (var3 != 72) {
                           if (var3 != 73) {
                              if (var3 != 120) {
                                 if (var3 != 121) {
                                    switch (var3) {
                                       case 34:
                                          this.set0x22RearRadar(var1);
                                          break;
                                       case 35:
                                          this.set0x23FrontRadar(var1);
                                          break;
                                       case 36:
                                          this.set0x24BaseInfo(var1);
                                          break;
                                       case 37:
                                          this.set0x25RadarStatus(var1);
                                          break;
                                       default:
                                          switch (var3) {
                                             case 39:
                                                this.set0x27Language();
                                                break;
                                             case 40:
                                                this.set0x28ActiveParkData(var1);
                                                break;
                                             case 41:
                                                this.set0x29AirInfo(var1);
                                                break;
                                             default:
                                                switch (var3) {
                                                   case 80:
                                                      this.set0x50SyncMenuInfo();
                                                      break;
                                                   case 81:
                                                      DataHandler var4 = this.mDataHandler;
                                                      if (var4 != null) {
                                                         var4.push(this.mCanBusInfoByte);
                                                      }
                                                      break;
                                                   case 82:
                                                      this.set0x52SyncMediaTime();
                                                      break;
                                                   case 83:
                                                      this.set0x53SyncPhoneTime();
                                                      break;
                                                   default:
                                                      switch (var3) {
                                                         case 112:
                                                            this.set0x70SyncSrtUp();
                                                            break;
                                                         case 113:
                                                            this.set0x71SyncSrtDown();
                                                            break;
                                                         case 114:
                                                            this.set0x72SyncSrtShort();
                                                      }
                                                }
                                          }
                                    }
                                 } else {
                                    this.set0x79SyncSwitchRequest(var1);
                                 }
                              } else {
                                 this.set0x78SyncStatus(var1);
                              }
                           } else {
                              this.set0x49PanoramicStatus();
                           }
                        } else {
                           this.set0x48VehicleSetting(var1);
                        }
                     } else {
                        this.set0x56SyncHostControl(var1);
                     }
                  } else {
                     this.set0x40SyncVersion();
                  }
               } else {
                  this.set0x30VersionInfo(var1);
               }
            } else {
               this.set0x20WheelKey(var1);
            }
         } else {
            this.set0x16VehicleSpeed();
         }
      } else {
         Log.i("_85_MSG", "canbusInfoChange: 0x14");
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.i("_85_MSG", "dateTimeRepCanbus: " + this.mVersionInfo);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      if (var11) {
         var1 = 128;
      } else {
         var1 = 0;
      }

      if (var10) {
         var5 = var8;
      } else {
         var1 |= 64;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var5, (byte)var6, (byte)var1});
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      if (this.mIsDiscExsit != var4) {
         this.mIsDiscExsit = var4;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)(7 - var4)});
         if (var4 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0});
         }
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -64, 2, 16});
      if (var7 == 1) {
         var4 = var5;
      }

      var1 = DataHandleUtils.rangeNumber(var4, 99);
      int[] var17 = this.getTime(var2);
      byte var15 = (byte)var1;
      byte var14 = (byte)var17[1];
      byte var16 = (byte)var17[2];
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -61, 0, var15, 0, 0, var14, var16}, 40L);
      this.mId3s[0].info = var11;
      this.mId3s[1].info = var12;
      this.mId3s[2].info = var13;
      this.reportID3Info(this.mId3s);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   // $FF: synthetic method
   void lambda$set0x28ActiveParkData$0$com_hzbhd_canbus_car__85_MsgMgr(Context var1) {
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

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -64, 8, 16});
      byte var25 = (byte)DataHandleUtils.rangeNumber(var9 * 256 + var3, 99);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -61, 0, var25, 0, 0, var6, var7}, 40L);
      this.mId3s[0].info = var21;
      this.mId3s[1].info = var22;
      this.mId3s[2].info = var23;
      this.reportID3Info(this.mId3s);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var11;
      label36: {
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -64, 1, 1});
         int var10 = this.getband(var2);
         int[] var12 = this.getFreqByteHiLo(var2, var3);
         byte var13 = 0;
         byte var6 = (byte)var10;
         byte var8 = (byte)var12[1];
         byte var9 = (byte)var12[0];
         byte var7 = (byte)var1;
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -62, var6, var8, var9, var7});
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
         this.mRadioCurrentBand = var2;
         var2.hashCode();
         switch (var2) {
            case "AM1":
               var11 = var13;
            case "AM2":
               var11 = 1;
               break label36;
            case "FM1":
               var11 = 2;
               break label36;
            case "FM2":
               var11 = 3;
               break label36;
         }

         var11 = -1;
      }

      switch (var11) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 3});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 4});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 1});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 2});
      }

   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.sendNormalMessage(new byte[]{22, -64, 0, 0});
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

   void updateSync(Bundle var1) {
      this.updateSyncActivity(var1);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, 8, 16});
      byte var18 = (byte)DataHandleUtils.rangeNumber(var9 * 256 + var3, 99);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -61, 0, var18, 0, 0, var6, var7}, 80L);
   }

   private abstract class AbstractSettingEntityProvider {
      private int left;
      private int right;
      final MsgMgr this$0;
      private String title;

      public AbstractSettingEntityProvider(MsgMgr var1, String var2, int var3, int var4) {
         this.this$0 = var1;
         this.title = var2;
         this.left = var3;
         this.right = var4;
      }

      public abstract SettingUpdateEntity getEntity(Object var1);

      public int getLeft() {
         return this.left;
      }

      public int getRight() {
         return this.right;
      }

      public String getTitle() {
         return this.title;
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
            this.this$0.set0x51SyncMenuItemInfo(var1, (byte[])this.bytes.get(0));
         }

         this.bytes.remove(0);
         this.size = this.bytes.size();
      }

      private void push(byte[] var1) {
         this.bytes.add(var1);
         this.size = this.bytes.size();
      }
   }

   private class ID3 {
      private int command;
      private String info;
      private String record;
      final MsgMgr this$0;

      private ID3(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.command = var2;
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

   public static enum SYNC_KEY_STATE {
      private static final SYNC_KEY_STATE[] $VALUES;
      HIGHLIGHT_ICON,
      HIGHTLIGHT_TEXT,
      ICON,
      NONE,
      TEXT;

      static {
         SYNC_KEY_STATE var3 = new SYNC_KEY_STATE("NONE", 0);
         NONE = var3;
         SYNC_KEY_STATE var2 = new SYNC_KEY_STATE("ICON", 1);
         ICON = var2;
         SYNC_KEY_STATE var4 = new SYNC_KEY_STATE("HIGHLIGHT_ICON", 2);
         HIGHLIGHT_ICON = var4;
         SYNC_KEY_STATE var0 = new SYNC_KEY_STATE("TEXT", 3);
         TEXT = var0;
         SYNC_KEY_STATE var1 = new SYNC_KEY_STATE("HIGHTLIGHT_TEXT", 4);
         HIGHTLIGHT_TEXT = var1;
         $VALUES = new SYNC_KEY_STATE[]{var3, var2, var4, var0, var1};
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
         SYNC_LINE_TEXT_ATT var4 = new SYNC_LINE_TEXT_ATT("NOR_TEXT", 0);
         NOR_TEXT = var4;
         SYNC_LINE_TEXT_ATT var3 = new SYNC_LINE_TEXT_ATT("NOR_TEXT_GRAY_BKG", 1);
         NOR_TEXT_GRAY_BKG = var3;
         SYNC_LINE_TEXT_ATT var0 = new SYNC_LINE_TEXT_ATT("GRAY_TEXT", 2);
         GRAY_TEXT = var0;
         SYNC_LINE_TEXT_ATT var1 = new SYNC_LINE_TEXT_ATT("GRAY_TEXT_GRAY_BKG", 3);
         GRAY_TEXT_GRAY_BKG = var1;
         SYNC_LINE_TEXT_ATT var5 = new SYNC_LINE_TEXT_ATT("DEEP_GRAY_TEXT", 4);
         DEEP_GRAY_TEXT = var5;
         SYNC_LINE_TEXT_ATT var2 = new SYNC_LINE_TEXT_ATT("HIDDEN", 5);
         HIDDEN = var2;
         $VALUES = new SYNC_LINE_TEXT_ATT[]{var4, var3, var0, var1, var5, var2};
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
         SYNC_MESSAGE_TYPE var8 = new SYNC_MESSAGE_TYPE("NONE", 0);
         NONE = var8;
         SYNC_MESSAGE_TYPE var1 = new SYNC_MESSAGE_TYPE("_1_LINE_NO_BUTTON", 1);
         _1_LINE_NO_BUTTON = var1;
         SYNC_MESSAGE_TYPE var5 = new SYNC_MESSAGE_TYPE("_1_LINE_4_BUTTON", 2);
         _1_LINE_4_BUTTON = var5;
         SYNC_MESSAGE_TYPE var6 = new SYNC_MESSAGE_TYPE("_2_LINE_NO_BUTTON", 3);
         _2_LINE_NO_BUTTON = var6;
         SYNC_MESSAGE_TYPE var7 = new SYNC_MESSAGE_TYPE("_2_LINE_4_BUTTON", 4);
         _2_LINE_4_BUTTON = var7;
         SYNC_MESSAGE_TYPE var3 = new SYNC_MESSAGE_TYPE("_3_LINE_NO_BUTTON", 5);
         _3_LINE_NO_BUTTON = var3;
         SYNC_MESSAGE_TYPE var4 = new SYNC_MESSAGE_TYPE("_3_LINE_4_BUTTON", 6);
         _3_LINE_4_BUTTON = var4;
         SYNC_MESSAGE_TYPE var0 = new SYNC_MESSAGE_TYPE("DIAL_REDIAL", 7);
         DIAL_REDIAL = var0;
         SYNC_MESSAGE_TYPE var2 = new SYNC_MESSAGE_TYPE("SPEECH", 8);
         SPEECH = var2;
         SYNC_MESSAGE_TYPE var9 = new SYNC_MESSAGE_TYPE("DIAL_A_NUMBER", 9);
         DIAL_A_NUMBER = var9;
         $VALUES = new SYNC_MESSAGE_TYPE[]{var8, var1, var5, var6, var7, var3, var4, var0, var2, var9};
      }
   }

   public static enum SYNC_VERSION {
      private static final SYNC_VERSION[] $VALUES;
      NONE,
      VERSION_V1,
      VERSION_V2,
      VERSION_V3;

      static {
         SYNC_VERSION var0 = new SYNC_VERSION("NONE", 0);
         NONE = var0;
         SYNC_VERSION var1 = new SYNC_VERSION("VERSION_V1", 1);
         VERSION_V1 = var1;
         SYNC_VERSION var2 = new SYNC_VERSION("VERSION_V2", 2);
         VERSION_V2 = var2;
         SYNC_VERSION var3 = new SYNC_VERSION("VERSION_V3", 3);
         VERSION_V3 = var3;
         $VALUES = new SYNC_VERSION[]{var0, var1, var2, var3};
      }
   }

   private class SettingNormalEntityProvider extends AbstractSettingEntityProvider {
      final MsgMgr this$0;

      public SettingNormalEntityProvider(MsgMgr var1, String var2, int var3, int var4) {
         super(var1, var2, var3, var4);
         this.this$0 = var1;
      }

      public SettingUpdateEntity getEntity(Object var1) {
         return new SettingUpdateEntity(this.getLeft(), this.getRight(), var1);
      }
   }

   private class SettingProgressEntityProvider extends AbstractSettingEntityProvider {
      private int min;
      final MsgMgr this$0;

      public SettingProgressEntityProvider(MsgMgr var1, String var2, int var3, int var4, int var5) {
         super(var1, var2, var3, var4);
         this.this$0 = var1;
         this.min = var5;
      }

      public SettingUpdateEntity getEntity(Object var1) {
         return (new SettingUpdateEntity(this.getLeft(), this.getRight(), var1)).setProgress((Integer)var1 - this.min);
      }
   }
}
