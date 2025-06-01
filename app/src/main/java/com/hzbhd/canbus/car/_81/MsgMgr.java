package com.hzbhd.canbus.car._81;

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
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
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
   static final String IS_ACTIVE_PARK_OPEN = "is_active_park_open";
   static int[] mLanguageMapArray;
   static SYNC_VERSION mSyncVersion;
   private final int BACKLIGHT_DATA_MAX = 256;
   private final int BACKLIGHT_SEGMENTS = 5;
   private final long DATA_HANDLE_PERIOD = 32L;
   private final int DATA_TYPE = 1;
   private final int INVAILE_VALUE = -1;
   private final int MSG_ACTIVE_PARK_0X1C_COUNT_DOWN = 10;
   private final int MSG_DISMISS_WARNING_ACTIVITY = 11;
   private final String TAG = "_81_MSG";
   DecimalFormat df_2Integer = new DecimalFormat("00");
   private String[] m0x05ItemTitleArray;
   private SparseArray mActiveParkItemArray;
   private ActiveParkView mActiveParkView;
   private String mAirUnit;
   private boolean mBackStatus;
   private int mBacklight;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray;
   private DataHandler mDataHandler;
   private DecimalFormat mDecimalFormat00;
   private DecimalFormat mDecimalFormat0p0;
   private DecimalFormat mDecimalFormat0p00;
   private int mDiiferentId = this.getCurrentCanDifferentId();
   private int mEachId = this.getCurrentEachCanId();
   private Handler mHandler;
   private boolean mIsActiveViewOpen;
   private int mIsDiscExsit;
   private boolean mIsSyncNeedShowDialog;
   private int mKeyStatus;
   private WindowManager.LayoutParams mLayoutParams;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private MyPanoramicView mPanoramicView;
   private ParkPageUiSet mParkPageUiSet;
   private int[] mPreWarningData = new int[18];
   private String mRadioCurrentBand;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private SparseIntArray mSyncMenuIconResIdArray;
   TimerUtil mTimerUtil;
   private SparseIntArray mTopIconArray;
   private UiMgr mUiMgr;
   private String mVersionInfo;
   private WindowManager mWindowManager;

   public MsgMgr() {
      this.mPreWarningData = new int[18];
      this.mTimerUtil = new TimerUtil();
      this.mCanbusDataArray = new SparseArray();
      this.mDecimalFormat0p00 = new DecimalFormat("0.00");
      this.mDecimalFormat0p0 = new DecimalFormat("0.0");
      this.mDecimalFormat00 = new DecimalFormat("00");
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
      GeneralSyncData.mInfoLineShowAmount = 5;
      GeneralSyncData.mIsShowSoftKey = true;
      GeneralSyncData.mSyncTopIconCount = 9;
      GeneralSyncData.mSyncTopIconResIdArray = new int[GeneralSyncData.mSyncTopIconCount];
      RadarInfoUtil.mMinIsClose = true;
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

   private int getData(int... var1) {
      int var3 = 0;

      int var2;
      for(var2 = 0; var3 < var1.length; ++var3) {
         var2 += var1[var3] << var3 * 8;
      }

      return (double)var2 == Math.pow(2.0, (double)(var1.length * 8)) - 1.0 ? -1 : var2;
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
      this.mPanoramicView = (MyPanoramicView)this.getUiMgr(var1).getCusPanoramicView(var1);
      this.mHandler = new Handler(this, Looper.getMainLooper()) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            int var2 = var1.what;
            if (var2 != 10) {
               if (var2 == 11 && SystemUtil.isForeground((Context)var1.obj, WarningActivity.class.getName())) {
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

   private void initSettingsItem(SettingPageUiSet var1) {
      Log.i("ljq", "initSettingsItem: ");
      this.mSettingItemIndeHashMap = new HashMap();

      for(int var2 = 0; var2 < var1.getList().size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)var5.get(var3)).getTitleSrn();
            this.mSettingItemIndeHashMap.put(var4, var2 << 8 & '\uff00' | var3);
         }
      }

   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
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

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LOW";
      } else if (var1 == 127) {
         return "HIGH";
      } else if (var1 >= 31 && var1 <= 59) {
         float var2 = (float)var1 / 2.0F;
         return GeneralAirData.fahrenheit_celsius ? this.mDecimalFormat0p0.format((double)(var2 * 9.0F / 5.0F + 32.0F)) + this.mAirUnit : var2 + this.mAirUnit;
      } else {
         return "";
      }
   }

   private String resolveOutdoorTemperature(int var1) {
      return GeneralAirData.fahrenheit_celsius ? this.mDecimalFormat0p0.format((double)((float)(var1 * 9) / 5.0F + 32.0F)) + this.mAirUnit : var1 + this.mAirUnit;
   }

   private void set0x14Backlight() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = (int)((float)this.mCanBusInfoInt[2] / 51.2F);
         if (this.mBacklight != var1) {
            this.mBacklight = var1;
            this.setBacklightLevel(5 - var1);
         }
      }

   }

   private void set0x16VehicleSpeed() {
   }

   private void set0x20WheelKey(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 != 7) {
                        if (var2 != 61) {
                           if (var2 != 63) {
                              if (var2 != 111) {
                                 if (var2 != 134) {
                                    if (var2 != 86) {
                                       if (var2 != 87) {
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
                                                            this.realKeyClick2(var1, 52);
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
                                                                  this.realKeyClick2(var1, 49);
                                                                  break;
                                                               case 73:
                                                                  this.realKeyClick2(var1, 47);
                                                                  break;
                                                               case 74:
                                                                  this.realKeyClick2(var1, 48);
                                                                  break;
                                                               case 75:
                                                                  this.realKeyClick2(var1, 45);
                                                                  break;
                                                               case 76:
                                                                  this.realKeyClick2(var1, 46);
                                                                  break;
                                                               default:
                                                                  switch (var2) {
                                                                     case 82:
                                                                        this.realKeyClick2(var1, 206);
                                                                        break;
                                                                     case 83:
                                                                        this.realKeyClick2(var1, 207);
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
                                                                           default:
                                                                              switch (var2) {
                                                                                 case 240:
                                                                                    this.realKeyClick3_1(var1, 7);
                                                                                    break;
                                                                                 case 241:
                                                                                    this.realKeyClick3_1(var1, 8);
                                                                                    break;
                                                                                 case 242:
                                                                                    this.realKeyClick3_2(var1, 48);
                                                                                    break;
                                                                                 case 243:
                                                                                    this.realKeyClick3_2(var1, 47);
                                                                              }
                                                                        }
                                                                  }
                                                            }
                                                      }
                                                }
                                          }
                                       } else {
                                          this.startMainActivity(var1);
                                       }
                                    } else {
                                       this.realKeyClick2(var1, 94);
                                    }
                                 } else {
                                    this.realKeyClick2(var1, 17);
                                 }
                              } else {
                                 this.realKeyClick2(var1, 59);
                              }
                           } else {
                              this.realKeyClick2(var1, 134);
                           }
                        } else {
                           this.realKeyClick2(var1, 196);
                        }
                     } else {
                        this.realKeyClick2(var1, 2);
                     }
                  } else {
                     this.realKeyClick2(var1, 21);
                  }
               } else {
                  this.realKeyClick2(var1, 20);
               }
            } else {
               this.realKeyClick2(var1, 8);
            }
         } else {
            this.realKeyClick2(var1, 7);
         }
      } else {
         this.realKeyClick2(var1, 0);
      }

   }

   private void set0x21AirInfo(Context var1) {
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      if (GeneralAirData.fahrenheit_celsius) {
         this.mAirUnit = this.getTempUnitF(var1);
      } else {
         this.mAirUnit = this.getTempUnitC(var1);
      }

      this.updateOutDoorTemp(var1, this.resolveOutdoorTemperature(this.mCanBusInfoByte[7]));
      byte[] var2 = this.mCanBusInfoByte;
      var2[3] = (byte)(var2[3] & 239);
      var2[7] = 0;
      if (!this.isAirMsgRepeat(var2)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         this.updateAirActivity(var1, 1001);
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
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

      int[] var14 = this.mCanBusInfoInt;
      var14[2] = 0;
      var14[3] &= 192;
      if (this.isDataChange(var14)) {
         String var21;
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
            var21 = "str_ok";
         } else {
            var21 = "null_value";
         }

         int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1);
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1);
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3);
         int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
         int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
         int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 1);
         int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1);
         int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1);
         int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1);
         int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1);
         this.setSettingData(new String[]{"_81_traction_control_system", "_81_turn_signals_setup", "ford_message_tone", "ford_alert_tone", "_81_current_voice_mode", "ford_range_unit", "brightness", "_81_tyre_monitor", "_81_rain_sensor", "_81_interior_lighting", "_81_park_lock_ctrl", "_81_hill_start_assist", "parking_assistance"}, new Object[]{var8, var5, var4, var3, var2, var10, var6, var21, var13, var7, var12, var11, var9});
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            var21 = "_81_max_vol_half";
         } else {
            var21 = "_81_unlimit";
         }

         String var15;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            var15 = "_103_car_setting_value_7_1";
         } else {
            var15 = "_103_car_setting_value_7_0";
         }

         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3);
         String var17 = "";
         String var16;
         if (var2 < 6) {
            var16 = Integer.toString(var2);
         } else {
            var16 = "";
         }

         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3);
         if (var2 < 6) {
            var17 = Integer.toString(var2);
         }

         String var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7) + "%";
         String var18;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            var18 = "mazda_binary_car_set71_1";
         } else {
            var18 = "energy_no_display";
         }

         ArrayList var20 = new ArrayList();
         var20.add(new DriverUpdateEntity(0, 3, CommUtil.getStrByResId(var1, var21)));
         var20.add(new DriverUpdateEntity(0, 4, CommUtil.getStrByResId(var1, var15)));
         var20.add(new DriverUpdateEntity(0, 5, var16));
         var20.add(new DriverUpdateEntity(0, 6, var17));
         var20.add(new DriverUpdateEntity(0, 7, var19));
         var20.add(new DriverUpdateEntity(0, 8, CommUtil.getStrByResId(var1, var18)));
         this.updateGeneralDriveData(var20);
         this.updateDriveDataActivity((Bundle)null);
         ArrayList var22 = new ArrayList();
         var22.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]) ^ true));
         var22.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])));
         GeneralParkData.dataList = var22;
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
         var5.add(new DriverUpdateEntity(0, 9, CommUtil.getStrByResId(var1, var3)));
         var5.add(new DriverUpdateEntity(0, 10, CommUtil.getStrByResId(var1, var4)));
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

   private void set0x29VehicleInfo() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 2 && this.isDataChange(var1)) {
         String var6;
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            var6 = "mph";
         } else {
            var6 = "km/h";
         }

         ArrayList var2 = new ArrayList();
         StringBuilder var4 = new StringBuilder();
         DecimalFormat var5 = this.mDecimalFormat0p00;
         int[] var3 = this.mCanBusInfoInt;
         var2.add(new DriverUpdateEntity(0, 0, var4.append(var5.format((double)((float)this.getData(var3[4], var3[5]) / 100.0F))).append(" ").append(var6).toString()));
         StringBuilder var8 = new StringBuilder();
         var1 = this.mCanBusInfoInt;
         var2.add(new DriverUpdateEntity(0, 1, var8.append(this.getData(var1[6], var1[7])).append(" rpm").toString()));
         var8 = new StringBuilder();
         DecimalFormat var9 = this.mDecimalFormat0p00;
         var1 = this.mCanBusInfoInt;
         var2.add(new DriverUpdateEntity(0, 2, var8.append(var9.format((double)((float)this.getData(var1[8], var1[9]) / 100.0F))).append(" rad/s").toString()));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
         DecimalFormat var7;
         if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            var7 = this.df_2Integer;
            var1 = this.mCanBusInfoInt;
            this.updateSpeedInfo(Integer.parseInt(var7.format((double)(this.getData(var1[4], var1[5]) / 100) * 1.6)));
         } else {
            var7 = this.df_2Integer;
            var1 = this.mCanBusInfoInt;
            this.updateSpeedInfo(Integer.parseInt(var7.format((long)(this.getData(var1[4], var1[5]) / 100))));
         }
      }

   }

   private void set0x2AWarningInfo(Context var1) {
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

         boolean var3;
         boolean var5;
         for(var3 = false; var2 < 16; ++var2) {
            for(int var4 = 0; var4 < 8; var3 = var5) {
               int[] var10 = this.mCanBusInfoInt;
               int var6 = var2 + 2;
               int var8 = var10[var6];
               int var7 = 1 << var4;
               var5 = var3;
               if ((var8 & var7) != 0) {
                  var12.add(new WarningEntity(CommUtil.getStrByResId(var1, "_81_alert_info_" + var2 + "_" + var4)));
                  var5 = var3;
                  if ((this.mPreWarningData[var6] & var7) == 0) {
                     var5 = true;
                  }
               }

               ++var4;
            }
         }

         if (var3) {
            this.showWarningActivity(var1);
         }

         int[] var11 = this.mCanBusInfoInt;
         this.mPreWarningData = Arrays.copyOf(var11, var11.length);
         GeneralWarningDataData.dataList = var12;
         this.updateWarningActivity((Bundle)null);
      }

   }

   private void set0x30VersionInfo(Context var1) {
      String var2 = this.getVersionStr(this.mCanBusInfoByte);
      this.mVersionInfo = var2;
      this.updateVersionInfo(var1, var2);
   }

   private void set0x35TrackData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 10000, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x40SyncVersion() {
      if (mSyncVersion != SYNC_VERSION.values()[this.mCanBusInfoInt[2]]) {
         mSyncVersion = SYNC_VERSION.values()[this.mCanBusInfoInt[2]];
         int var1 = null.$SwitchMap$com$hzbhd$canbus$car$_81$MsgMgr$SYNC_VERSION[mSyncVersion.ordinal()];
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

   private void set0x48VehicleSetting() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      this.setSettingData(new String[]{"_176_car_setting_title_21"}, new Object[]{var1});
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
         Iterator var12;
         if (var3 >= 1 && var3 <= 10) {
            var4 = (var3 - 1) % 5;
            int var5 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[4] & 255));
            var3 = this.getSyncIconResId(var1, "ford_sync_icon_" + (var2[5] & 255));
            var9 = this.getSyncInfo(var2);
            var6 = DataHandleUtils.getBoolBit4(var2[3]);
            var12 = GeneralSyncData.mInfoUpdateEntityList.iterator();

            while(var12.hasNext()) {
               SyncListUpdateEntity var11 = (SyncListUpdateEntity)var12.next();
               if (var11.getIndex() == var4) {
                  var11.setLeftIconResId(var5).setRightIconResId(var3).setInfo(var9).setEnable(var6);
                  this.updateSyncActivity((Bundle)null);
                  return;
               }
            }

            GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(var4)).setLeftIconResId(var5).setRightIconResId(var3).setInfo(var9).setEnable(var6));
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
            var12 = GeneralSyncData.mSoftKeyUpdateEntityList.iterator();

            while(var12.hasNext()) {
               SyncSoftKeyUpdateEntity var10 = (SyncSoftKeyUpdateEntity)var12.next();
               if (var10.getIndex() == var4) {
                  var10.setName(var9).setImageResId(var3).setSelected(var7).setVisible(var6);
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
         String var4 = new String(Arrays.copyOfRange(var1, 2, var1.length));
         Iterator var2 = GeneralSyncData.mInfoUpdateEntityList.iterator();

         while(var2.hasNext()) {
            SyncListUpdateEntity var3 = (SyncListUpdateEntity)var2.next();
            if (var3.getIndex() == 0) {
               var3.setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var4).setEnable(false);
               this.updateSyncActivity((Bundle)null);
               return;
            }
         }

         GeneralSyncData.mInfoUpdateEntityList.add((new SyncListUpdateEntity(0)).setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var4).setEnable(false));
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void set0x71SyncSrtDown() {
      if (this.compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
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

   private void set0x72SyncSrtShort() {
      if (this.compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
         GeneralSyncData.mInfoLineShowAmount = 3;
         GeneralSyncData.mIsShowSoftKey = false;
         byte[] var1 = this.mCanBusInfoByte;
         String var4 = new String(Arrays.copyOfRange(var1, 2, var1.length));
         Iterator var3 = GeneralSyncData.mInfoUpdateEntityList.iterator();

         while(var3.hasNext()) {
            SyncListUpdateEntity var2 = (SyncListUpdateEntity)var3.next();
            if (var2.getIndex() == 2) {
               var2.setLeftIconResId(2131233649).setRightIconResId(2131233649).setInfo(var4).setEnable(false);
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
      ArrayList var5 = new ArrayList();

      for(int var3 = 0; var3 < var1.length; ++var3) {
         int[] var4 = this.getLeftAndRight(var1[var3]);
         if (var4[0] != -1 && var4[1] != -1) {
            Object var6 = var2[var3];
            var5.add(new SettingUpdateEntity(var4[0], var4[1], var6));
         }
      }

      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
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
      SelectCanTypeUtil.enableApp(var1, Constant.SyncActivity);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var5 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var5;
      int var3 = var5[1];
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 48) {
               if (var3 != 53) {
                  if (var3 != 64) {
                     if (var3 != 72) {
                        if (var3 != 86) {
                           if (var3 != 120) {
                              if (var3 != 121) {
                                 switch (var3) {
                                    case 32:
                                       this.set0x20WheelKey(var1);
                                       break;
                                    case 33:
                                       this.set0x21AirInfo(var1);
                                       break;
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
                                             this.set0x29VehicleInfo();
                                             break;
                                          case 42:
                                             this.set0x2AWarningInfo(var1);
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
                           this.set0x56SyncHostControl(var1);
                        }
                     } else {
                        this.set0x48VehicleSetting();
                     }
                  } else {
                     this.set0x40SyncVersion();
                  }
               } else {
                  this.set0x35TrackData(var1);
               }
            } else {
               this.set0x30VersionInfo(var1);
            }
         } else {
            this.set0x16VehicleSpeed();
         }
      } else {
         Log.i("_81_MSG", "canbusInfoChange: 0x14");
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.i("_81_MSG", "dateTimeRepCanbus: " + this.mVersionInfo);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      if (this.mIsDiscExsit != var4) {
         this.mIsDiscExsit = var4;
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)(7 - var4)});
      }

      if (var4 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initData(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 6});
      this.initSettingsItem(UiMgrFactory.getCanUiMgr(var1).getSettingUiSet(var1));
      this.initActivePark(var1);
   }

   // $FF: synthetic method
   void lambda$set0x28ActiveParkData$0$com_hzbhd_canbus_car__81_MsgMgr(Context var1) {
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

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.mRadioCurrentBand = var2;
      var2.hashCode();
      var5 = var2.hashCode();
      byte var6 = -1;
      switch (var5) {
         case 64901:
            if (var2.equals("AM1")) {
               var6 = 0;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var6 = 1;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var6 = 2;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var6 = 3;
            }
      }

      switch (var6) {
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

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSync(Bundle var1) {
      this.updateSyncActivity(var1);
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
         SYNC_KEY_STATE var1 = new SYNC_KEY_STATE("ICON", 1);
         ICON = var1;
         SYNC_KEY_STATE var0 = new SYNC_KEY_STATE("HIGHLIGHT_ICON", 2);
         HIGHLIGHT_ICON = var0;
         SYNC_KEY_STATE var2 = new SYNC_KEY_STATE("TEXT", 3);
         TEXT = var2;
         SYNC_KEY_STATE var4 = new SYNC_KEY_STATE("HIGHTLIGHT_TEXT", 4);
         HIGHTLIGHT_TEXT = var4;
         $VALUES = new SYNC_KEY_STATE[]{var3, var1, var0, var2, var4};
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
         SYNC_LINE_TEXT_ATT var3 = new SYNC_LINE_TEXT_ATT("NOR_TEXT", 0);
         NOR_TEXT = var3;
         SYNC_LINE_TEXT_ATT var2 = new SYNC_LINE_TEXT_ATT("NOR_TEXT_GRAY_BKG", 1);
         NOR_TEXT_GRAY_BKG = var2;
         SYNC_LINE_TEXT_ATT var0 = new SYNC_LINE_TEXT_ATT("GRAY_TEXT", 2);
         GRAY_TEXT = var0;
         SYNC_LINE_TEXT_ATT var4 = new SYNC_LINE_TEXT_ATT("GRAY_TEXT_GRAY_BKG", 3);
         GRAY_TEXT_GRAY_BKG = var4;
         SYNC_LINE_TEXT_ATT var1 = new SYNC_LINE_TEXT_ATT("DEEP_GRAY_TEXT", 4);
         DEEP_GRAY_TEXT = var1;
         SYNC_LINE_TEXT_ATT var5 = new SYNC_LINE_TEXT_ATT("HIDDEN", 5);
         HIDDEN = var5;
         $VALUES = new SYNC_LINE_TEXT_ATT[]{var3, var2, var0, var4, var1, var5};
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
         SYNC_MESSAGE_TYPE var0 = new SYNC_MESSAGE_TYPE("NONE", 0);
         NONE = var0;
         SYNC_MESSAGE_TYPE var1 = new SYNC_MESSAGE_TYPE("_1_LINE_NO_BUTTON", 1);
         _1_LINE_NO_BUTTON = var1;
         SYNC_MESSAGE_TYPE var3 = new SYNC_MESSAGE_TYPE("_1_LINE_4_BUTTON", 2);
         _1_LINE_4_BUTTON = var3;
         SYNC_MESSAGE_TYPE var2 = new SYNC_MESSAGE_TYPE("_2_LINE_NO_BUTTON", 3);
         _2_LINE_NO_BUTTON = var2;
         SYNC_MESSAGE_TYPE var6 = new SYNC_MESSAGE_TYPE("_2_LINE_4_BUTTON", 4);
         _2_LINE_4_BUTTON = var6;
         SYNC_MESSAGE_TYPE var4 = new SYNC_MESSAGE_TYPE("_3_LINE_NO_BUTTON", 5);
         _3_LINE_NO_BUTTON = var4;
         SYNC_MESSAGE_TYPE var8 = new SYNC_MESSAGE_TYPE("_3_LINE_4_BUTTON", 6);
         _3_LINE_4_BUTTON = var8;
         SYNC_MESSAGE_TYPE var5 = new SYNC_MESSAGE_TYPE("DIAL_REDIAL", 7);
         DIAL_REDIAL = var5;
         SYNC_MESSAGE_TYPE var9 = new SYNC_MESSAGE_TYPE("SPEECH", 8);
         SPEECH = var9;
         SYNC_MESSAGE_TYPE var7 = new SYNC_MESSAGE_TYPE("DIAL_A_NUMBER", 9);
         DIAL_A_NUMBER = var7;
         $VALUES = new SYNC_MESSAGE_TYPE[]{var0, var1, var3, var2, var6, var4, var8, var5, var9, var7};
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
         SYNC_VERSION var1 = new SYNC_VERSION("VERSION_V2", 2);
         VERSION_V2 = var1;
         SYNC_VERSION var0 = new SYNC_VERSION("VERSION_V3", 3);
         VERSION_V3 = var0;
         $VALUES = new SYNC_VERSION[]{var2, var3, var1, var0};
      }
   }
}
