package com.hzbhd.canbus.car._168;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.fragment.OnStartPhoneMoreInfoFragment;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MsgMgr extends AbstractMsgMgr {
   private static final String SOURCE_REAL_CHANGE_ACTION = "com.hzbhd.action.sourcerealchange";
   private static boolean isAirFirst;
   private static byte isDiscExist;
   private static boolean isDoorFirst;
   private static boolean isLeftSeatCool;
   private static boolean isRightSeatCool;
   private static boolean mIsKonbClockwise;
   private static int up_dn_btn_data;
   private static int voice_btn_data;
   int[] mAirData;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private boolean mBackStatus;
   private boolean mBatteryStatus;
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private String mCurSource = "";
   private int mDifferentId;
   private boolean mFrontStatus;
   private boolean mFuelStatus;
   private boolean mIsAirFirst = true;
   private int mKonbCount = 0;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private int mSelKonbCount = 0;
   private int[] mTrackData;
   byte[] mcuByteArrayNoByte1;
   private ArrayList panelKeyLists = new ArrayList();
   BroadcastReceiver receiver = new BroadcastReceiver(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         if ("com.hzbhd.action.sourcerealchange".equals(var2.getAction())) {
            String var3 = var2.getExtras().getString("toSourceID");
            this.this$0.mCurSource = var3;
         }

      }
   };
   private ArrayList swcKeyLists = new ArrayList();

   private void OnstarInfo_0xb1() {
      int var1 = GeneralOnStartData.mOnStarStatus;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         if (var2 != 1 && var2 != 2) {
            this.startMainActivity(this.mContext);
            this.exitAuxIn2();
         } else {
            this.enterAuxIn2(this.mContext, Constant.OnStarActivity);
            this.openOnStarPhoneMoreInfoFragment();
         }
      }

      GeneralOnStartData.mOnStarStatus = this.mCanBusInfoInt[2];
      GeneralOnStartData.mOnStarCallType = this.getOnStarCallType(this.mCanBusInfoInt[3]);
      GeneralOnStartData.mOnStarCallMuteSt = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1);
      this.updateOnStarActivity(1004);
   }

   // $FF: synthetic method
   static int access$210(MsgMgr var0) {
      int var1 = var0.mSelKonbCount--;
      return var1;
   }

   private void addPanelKeyList() {
      ArrayList var1 = this.panelKeyLists;
      if (var1 != null) {
         var1.clear();
      }

      this.panelKeyLists.add(new PanelKeyList(this, 0, 0));
      this.panelKeyLists.add(new PanelKeyList(this, 1, 134));
      this.panelKeyLists.add(new PanelKeyList(this, 2, 21));
      this.panelKeyLists.add(new PanelKeyList(this, 3, 20));
      this.panelKeyLists.add(new PanelKeyList(this, 4, 58));
      this.panelKeyLists.add(new PanelKeyList(this, 5, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 6, 50));
      this.panelKeyLists.add(new PanelKeyList(this, 7, 129));
      this.panelKeyLists.add(new PanelKeyList(this, 8, 141));
      this.panelKeyLists.add(new PanelKeyList(this, 9, 3));
      this.panelKeyLists.add(new PanelKeyList(this, 10, 33));
      this.panelKeyLists.add(new PanelKeyList(this, 11, 34));
      this.panelKeyLists.add(new PanelKeyList(this, 12, 35));
      this.panelKeyLists.add(new PanelKeyList(this, 13, 36));
      this.panelKeyLists.add(new PanelKeyList(this, 14, 37));
      this.panelKeyLists.add(new PanelKeyList(this, 15, 38));
      this.panelKeyLists.add(new PanelKeyList(this, 16, 17));
      this.panelKeyLists.add(new PanelKeyList(this, 17, 31));
      this.panelKeyLists.add(new PanelKeyList(this, 18, 185));
      this.panelKeyLists.add(new PanelKeyList(this, 19, 196));
      this.panelKeyLists.add(new PanelKeyList(this, 20, 61));
      this.panelKeyLists.add(new PanelKeyList(this, 21, 75));
      this.panelKeyLists.add(new PanelKeyList(this, 22, 49));
      this.panelKeyLists.add(new PanelKeyList(this, 23, 45));
      this.panelKeyLists.add(new PanelKeyList(this, 24, 46));
      this.panelKeyLists.add(new PanelKeyList(this, 25, 47));
      this.panelKeyLists.add(new PanelKeyList(this, 26, 48));
      this.panelKeyLists.add(new PanelKeyList(this, 27, 45));
      this.panelKeyLists.add(new PanelKeyList(this, 28, 46));
      this.panelKeyLists.add(new PanelKeyList(this, 29, 47));
      this.panelKeyLists.add(new PanelKeyList(this, 30, 48));
      this.panelKeyLists.add(new PanelKeyList(this, 31, 141));
      this.panelKeyLists.add(new PanelKeyList(this, 32, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 33, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 34, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 35, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 36, 139));
      this.panelKeyLists.add(new PanelKeyList(this, 37, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 38, 139));
      this.panelKeyLists.add(new PanelKeyList(this, 39, 4));
      this.panelKeyLists.add(new PanelKeyList(this, 40, 68));
      this.panelKeyLists.add(new PanelKeyList(this, 41, 94));
      this.panelKeyLists.add(new PanelKeyList(this, 42, 49));
      this.panelKeyLists.add(new PanelKeyList(this, 43, 52));
      this.panelKeyLists.add(new PanelKeyList(this, 44, 2));
      this.panelKeyLists.add(new PanelKeyList(this, 45, 52));
   }

   private void addSwcKeyList() {
      ArrayList var1 = this.swcKeyLists;
      if (var1 != null) {
         var1.clear();
      }

      this.swcKeyLists.add(new PanelKeyList(this, 0, 0));
      this.swcKeyLists.add(new PanelKeyList(this, 1, 7));
      this.swcKeyLists.add(new PanelKeyList(this, 2, 8));
      this.swcKeyLists.add(new PanelKeyList(this, 3, 3));
      this.swcKeyLists.add(new PanelKeyList(this, 4, 187));
      this.swcKeyLists.add(new PanelKeyList(this, 5, 14));
      this.swcKeyLists.add(new PanelKeyList(this, 6, 15));
      this.swcKeyLists.add(new PanelKeyList(this, 7, -1));
      this.swcKeyLists.add(new PanelKeyList(this, 8, 21));
      this.swcKeyLists.add(new PanelKeyList(this, 9, 20));
      this.swcKeyLists.add(new PanelKeyList(this, 10, 2));
   }

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var6 = new byte[var4];
      byte[] var5;
      if (var2 == var4) {
         var5 = Arrays.copyOf(var1, var4);
      } else {
         int var3 = 0;

         while(true) {
            var5 = var6;
            if (var3 >= var4) {
               break;
            }

            if (var3 < var2) {
               var6[var3] = var1[var3];
            } else {
               var6[var3] = var1[var3 + 1];
            }

            ++var3;
         }
      }

      return var5;
   }

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_auto_wind = false;
      GeneralAirData.front_right_auto_wind = false;
      GeneralAirData.rear_left_blow_window = false;
      GeneralAirData.rear_left_blow_head = false;
      GeneralAirData.rear_left_blow_foot = false;
      GeneralAirData.rear_right_blow_window = false;
      GeneralAirData.rear_right_blow_head = false;
      GeneralAirData.rear_right_blow_foot = false;
      GeneralAirData.rear_left_auto_wind = false;
      GeneralAirData.rear_right_auto_wind = false;
      GeneralAirData.rear_auto = false;
   }

   private int getAirWhat() {
      int[] var6 = this.mCanBusInfoInt;
      int var2 = var6[2];
      int var3 = var6[3];
      int[] var4 = new int[8];
      short var1 = 0;
      var4[0] = var2 & 223;
      var4[1] = var3 & 248;
      var4[2] = var6[4];
      var4[3] = var6[5];
      var4[4] = var6[6];
      var4[5] = var6[7];
      var4[6] = var6[8];
      var4[7] = var6[9];
      int[] var5 = new int[]{var2 & 16, var3 & 128, var6[10], var6[11], var6[12]};
      if (!Arrays.equals(this.mAirFrontDataNow, var4)) {
         var1 = 1001;
      } else if (!Arrays.equals(this.mAirRearDataNow, var5)) {
         var1 = 1002;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var4, 8);
      this.mAirRearDataNow = Arrays.copyOf(var5, 5);
      return var1;
   }

   private int getAirWhatFrontOnly() {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      int var1 = var3[3];
      int[] var4 = new int[]{var2 & 223, var1 & 248, var3[4], var3[5], var3[6], var3[7], var3[8], var3[9]};
      short var5;
      if (!Arrays.equals(this.mAirFrontDataNow, var4)) {
         var5 = 1001;
      } else {
         var5 = -1;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var4, 8);
      return var5;
   }

   private String getCalibrationStatus(boolean var1) {
      int var2;
      Resources var3;
      if (var1) {
         var3 = this.mContext.getResources();
         var2 = 2131768060;
      } else {
         var3 = this.mContext.getResources();
         var2 = 2131768061;
      }

      return var3.getString(var2);
   }

   private String getOnStarCallTime() {
      return this.mCanBusInfoInt[2] + ":" + this.getTwoDigitStr(this.mCanBusInfoInt[3]) + ":" + this.getTwoDigitStr(this.mCanBusInfoInt[4]);
   }

   private String getOnStarCallType(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 3 ? "" : this.mContext.getResources().getString(2131769851);
            } else {
               return this.mContext.getResources().getString(2131768200);
            }
         } else {
            return this.mContext.getResources().getString(2131768053);
         }
      } else {
         return this.mContext.getResources().getString(2131769412);
      }
   }

   private String getOnStarEffectTime() {
      StringBuilder var2 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      String var3 = var2.append(var1[7] * 256 + var1[8]).append("").toString();
      String var5 = this.mCanBusInfoInt[9] + "";
      String var4 = this.mCanBusInfoInt[10] + "";
      return var3 + this.mContext.getResources().getString(2131770120) + var5 + this.mContext.getResources().getString(2131770119) + var4 + this.mContext.getResources().getString(2131770118);
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

   private TireUpdateEntity getTireEntitySpare(String var1) {
      return new TireUpdateEntity(4, 0, new String[]{var1});
   }

   private String getTpmsCheckWarningStr(int var1) {
      String var2;
      if (DataHandleUtils.getBoolBit2(var1)) {
         var2 = this.mContext.getResources().getString(2131770315);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String getTpmsPressureNumStr(int var1, int var2) {
      return var1 * 256 + var2 + "";
   }

   private String getTpmsWarningStr(int var1) {
      boolean var2 = DataHandleUtils.getBoolBit1(var1);
      String var3;
      if (DataHandleUtils.getBoolBit0(var1)) {
         var3 = this.mContext.getResources().getString(2131768720);
      } else if (var2) {
         var3 = this.mContext.getResources().getString(2131769160);
      } else {
         var3 = "";
      }

      return var3;
   }

   private String getTwoDigitStr(int var1) {
      return var1 < 10 ? "0" + var1 : var1 + "";
   }

   private boolean isAirDataNoChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return true;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorMsgReturn() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mBatteryStatus == GeneralDoorData.isBatteryWarning && this.mFuelStatus == GeneralDoorData.isFuelWarning) {
         return true;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mBatteryStatus = GeneralDoorData.isBatteryWarning;
         this.mFuelStatus = GeneralDoorData.isFuelWarning;
         return false;
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return !GeneralAirData.power;
      } else {
         return false;
      }
   }

   private boolean isTrackDataChange() {
      if (Arrays.equals(this.mCanBusInfoInt, this.mTrackData)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCanBusInfoInt = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void konbKeySel(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         if (mIsKonbClockwise) {
            this.realKeyClick4(46);
         } else {
            this.realKeyClick4(45);
         }
      }

   }

   private void konbKeyVol(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         if (mIsKonbClockwise) {
            this.realKeyClick4(7);
         } else {
            this.realKeyClick4(8);
         }
      }

   }

   private void onStarCallInfo_0xb2() {
      GeneralOnStartData.mOnStarCallTime = this.getOnStarCallTime();
      StringBuilder var1 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      GeneralOnStartData.mOnStarRemainTime = var1.append(var2[5] * 256 + var2[6]).append("").toString();
      GeneralOnStartData.mOnStarEffectTime = this.getOnStarEffectTime();
      this.updateOnStarActivity(1004);
   }

   private void onStarMyNum_0xbd() {
      String var1;
      try {
         var1 = new String(this.mcuByteArrayNoByte1, "ASCII");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      GeneralOnStartData.mOnStarMyNumber = var1;
      this.updateOnStarActivity(1004);
   }

   private void onStartCurrentNum_0xb4() {
      String var1;
      try {
         var1 = new String(this.mcuByteArrayNoByte1, "ASCII");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      GeneralOnStartData.mOnStarPhoneNum = var1;
      this.updateOnStarActivity(1004);
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

   private void panelKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick5(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private String resolveFrontAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      return (float)this.mCanBusInfoInt[13] * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private String resolveRearAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else if (var1 >= 34 && var1 <= 64) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = " ";
      }

      return var2;
   }

   private void sendPanelInitCmds() {
      int var1 = this.mDifferentId;
      if (var1 == 255) {
         CanbusMsgSender.sendMsg(new byte[]{22, 42, 0, 0});
      } else if (var1 == 254) {
         CanbusMsgSender.sendMsg(new byte[]{22, 42, 1, 0});
      } else if (var1 == 253) {
         CanbusMsgSender.sendMsg(new byte[]{22, 42, 2, 0});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte)var1, 0});
      }

   }

   private void sendPanelKey(int var1, int var2) {
      ArrayList var3 = this.panelKeyLists;
      if (var3 != null) {
         ((PanelKeyList)var3.get(var1)).sendPanelKey(this.mContext, var2);
      } else {
         this.addPanelKeyList();
         ((PanelKeyList)this.panelKeyLists.get(var1)).sendPanelKey(this.mContext, var2);
      }

   }

   private void sendPanelNaviKey() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 32 || var1 == 33 || var1 == 37) {
         this.realKeyClick4(128);
      }

   }

   private void sendSwcKey(int var1, int var2) {
      ArrayList var3 = this.swcKeyLists;
      if (var3 != null) {
         ((PanelKeyList)var3.get(var1)).sendPanelKey(this.mContext, var2);
      } else {
         this.addSwcKeyList();
         ((PanelKeyList)this.swcKeyLists.get(var1)).sendPanelKey(this.mContext, var2);
      }

   }

   private void sendToneKey() {
      if (this.mCanBusInfoInt[2] == 5) {
         if (this.mCurSource.equals("FM")) {
            this.panelKeyClick(75);
         } else {
            this.panelKeyClick(17);
         }
      }

   }

   private void setAcSystem_0x35() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)));
      var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
      var1.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)));
      var1.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)));
      var1.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      var1.add(new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var1.add(new SettingUpdateEntity(0, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
      var1.add(new SettingUpdateEntity(0, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
      var1.add(new SettingUpdateEntity(0, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)));
      var1.add(new SettingUpdateEntity(0, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1)));
      var1.add(new SettingUpdateEntity(0, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
      var1.add(new SettingUpdateEntity(0, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
      var1.add(new SettingUpdateEntity(0, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2)));
      var1.add(new SettingUpdateEntity(0, 13, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAirData_0x31() {
      if (this.mDifferentId == 254) {
         this.getAirWhatFrontOnly();
      } else {
         this.getAirWhat();
      }

      byte[] var5 = this.bytesExpectOneByte(this.mCanBusInfoByte, 13);
      this.setOutDoorTem();
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var5)) {
         if (!this.isFirst()) {
            GeneralAirData.ac_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
            boolean var4 = false;
            boolean var3 = false;
            boolean var2;
            if (var1 == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.sync = var2;
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) > 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac = var2;
            String var6;
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) >= 2) {
               var6 = "Hybrid";
            } else {
               var6 = "";
            }

            GeneralAirData.center_wheel = var6;
            isRightSeatCool = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            isLeftSeatCool = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.clean_air = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
            GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            if (isRightSeatCool) {
               GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
               GeneralAirData.front_right_seat_heat_level = 0;
            } else {
               GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
               GeneralAirData.front_right_seat_cold_level = 0;
            }

            if (isLeftSeatCool) {
               GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
               GeneralAirData.front_left_seat_heat_level = 0;
            } else {
               GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
               GeneralAirData.front_left_seat_cold_level = 0;
            }

            this.cleanAllBlow();
            var1 = this.mCanBusInfoInt[6];
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 5) {
                        if (var1 != 6) {
                           switch (var1) {
                              case 11:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 break;
                              case 12:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_left_blow_foot = true;
                                 GeneralAirData.front_right_blow_foot = true;
                                 break;
                              case 13:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 GeneralAirData.front_right_blow_head = true;
                                 break;
                              case 14:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 GeneralAirData.front_right_blow_head = true;
                                 GeneralAirData.front_left_blow_foot = true;
                                 GeneralAirData.front_right_blow_foot = true;
                           }
                        } else {
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_auto_wind = true;
               GeneralAirData.front_right_auto_wind = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            if (UiMgr.mDiffid == 255) {
               var2 = var3;
               if (GeneralAirData.front_wind_level == 19) {
                  var2 = var3;
                  if (GeneralAirData.front_left_auto_wind) {
                     var2 = var3;
                     if (GeneralAirData.aqs) {
                        var2 = var3;
                        if (GeneralAirData.ac_auto) {
                           var2 = true;
                        }
                     }
                  }
               }

               GeneralAirData.auto = var2;
            } else {
               var2 = var4;
               if (GeneralAirData.front_wind_level == 19) {
                  var2 = true;
               }

               GeneralAirData.auto = var2;
            }

            GeneralAirData.front_left_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[9]);
         }
      }
   }

   private void setAirInfo(int[] var1) {
      this.updateOutDoorTemp(this.mContext, (double)var1[13] * 0.5 - 40.0 + this.getTempUnitC(this.mContext));
      var1[13] = 0;
      if (!this.isAirDataNoChange(var1)) {
         SingletonForKt.INSTANCE.set0x31Data(var1);
         this.updateAirActivity(this.mContext, 1004);
         this.updateAirActivity(this.mContext, 1003);
      }
   }

   private void setBtPhoneName_0xc3() {
      String var1;
      try {
         var1 = new String(this.mcuByteArrayNoByte1, "ASCII");
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
         var1 = "";
      }

      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(10, 1, var1)).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setBtPincode_0xc2() {
      String var1;
      try {
         var1 = new String(this.mcuByteArrayNoByte1, "ASCII");
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
         var1 = "";
      }

      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(10, 0, var1)).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarInfo_0x32() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var3.append(var2[4] * 256 + var2[5]).append(" RPM").toString()));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var4.append(var5[6] * 256 + var5[7]).append(" KM/H").toString()));
      var1.add(new DriverUpdateEntity(0, 2, (float)this.mCanBusInfoInt[8] * 0.1F + " V"));
      var1.add(new DriverUpdateEntity(0, 3, this.mCanBusInfoInt[9] + " %"));
      var1.add(new DriverUpdateEntity(0, 4, this.mCanBusInfoInt[10] + " L"));
      var1.add(new DriverUpdateEntity(0, 5, this.mCanBusInfoInt[11] + " °C"));
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 6, var4.append(var5[12] * 256 + var5[13]).append(" °KPa").toString()));
      var1.add(new DriverUpdateEntity(0, 7, this.mCanBusInfoInt[14] + " Pa"));
      var1.add(new DriverUpdateEntity(0, 8, this.mCanBusInfoInt[15] + " %"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarStatusInfo() {
      int var1 = this.mCanBusInfoInt[3];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     var2 = "";
                  } else {
                     var2 = "D";
                  }
               } else {
                  var2 = "R";
               }
            } else {
               var2 = "N";
            }
         } else {
            var2 = "P";
         }
      } else {
         var2 = this.mContext.getResources().getString(2131768909);
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      this.updateGeneralDriveData(var3);
   }

   private void setCollisionDetetion_0x45() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)));
      var1.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var1.add(new SettingUpdateEntity(1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      var1.add(new SettingUpdateEntity(1, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCollisionDetetion_0x46() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(1, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var1.add(new SettingUpdateEntity(1, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)));
      var1.add(new SettingUpdateEntity(1, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setComfort_0x55() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
      var1.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
      var1.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      var1.add(new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      var1.add(new SettingUpdateEntity(2, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
      var1.add(new SettingUpdateEntity(2, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setComfort_0x56() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      SingletonForKt.INSTANCE.set0x56Data(this.mCanBusInfoInt);
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCompass_0x69() {
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(6, 0, this.getCalibrationStatus(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])))).setValueStr(true));
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      var2.add((new SettingUpdateEntity(6, 1, var1 + "")).setValueStr(true));
      var2.add(new SettingUpdateEntity(6, 2, (float)(this.mCanBusInfoInt[3] * 3) / 2.0F));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setConsumptionTrip_0x34() {
      ArrayList var4 = new ArrayList();
      String var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[24], 2, 1) != 1) {
         var3 = " " + this.mContext.getResources().getString(2131770212);
      } else {
         var3 = " " + this.mContext.getResources().getString(2131767389);
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[24], 0, 2);
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var2 = "";
               } else {
                  var2 = " " + this.mContext.getResources().getString(2131770221);
               }
            } else {
               var2 = " " + this.mContext.getResources().getString(2131770220);
            }
         } else {
            var2 = " " + this.mContext.getResources().getString(2131770216);
         }
      } else {
         var2 = " " + this.mContext.getResources().getString(2131770226);
      }

      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 0, var5.append(DataHandleUtils.getRound((float)(var6[2] * 256 + var6[3]) * 0.1F, 1)).append(var2).toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 1, var5.append(DataHandleUtils.getRound((float)(var6[4] * 256 + var6[5]) * 0.1F, 1)).append(var3).toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 2, var5.append(DataHandleUtils.getRound((float)(var6[6] * 256 * 256 + var6[7] * 256 + var6[8]) * 0.1F, 1)).append(var3).toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 3, var5.append(DataHandleUtils.getRound((float)(var6[9] * 256 + var6[10]) * 0.1F, 1)).append(var2).toString()));
      StringBuilder var9 = new StringBuilder();
      int[] var8 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 6, var9.append(DataHandleUtils.getRound((float)(var8[11] * 256 * 256 + var8[12] * 256 + var8[13]) * 0.1F, 1)).append(var3).toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 4, var5.append(DataHandleUtils.getRound((float)(var6[14] * 256 + var6[15]) * 0.1F, 1)).append(var2).toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 7, var5.append(DataHandleUtils.getRound((float)(var6[16] * 256 * 256 + var6[17] * 256 + var6[18]) * 0.1F, 1)).append(var3).toString()));
      var9 = new StringBuilder();
      var8 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 5, var9.append(DataHandleUtils.getRound((float)(var8[19] * 256 + var8[20]) * 0.1F, 1)).append(var2).toString()));
      StringBuilder var7 = new StringBuilder();
      var8 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(1, 8, var7.append(DataHandleUtils.getRound((float)(var8[21] * 256 * 256 + var8[22] * 256 + var8[23]) * 0.1F, 1)).append(var3).toString()));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDashboardDisplay_0x75() {
      if (this.mDifferentId != 253) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(7, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
         var1.add(new SettingUpdateEntity(7, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
         var1.add(new SettingUpdateEntity(7, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDoorData() {
      GeneralDoorData.isShowBatteryWarning = true;
      GeneralDoorData.isShowFuelWarning = true;
      boolean var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      this.mRightFrontRec = var1;
      GeneralDoorData.isRightFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      this.mLeftFrontRec = var1;
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      this.mRightRearRec = var1;
      GeneralDoorData.isRightRearDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      this.mLeftRearRec = var1;
      GeneralDoorData.isLeftRearDoorOpen = var1;
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
      GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      if (!this.isDoorMsgReturn()) {
         this.updateDoorView(this.mContext);
      }
   }

   private void setDrivingComputerInfo0() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 0, var2.append((float)((double)(var3[2] * 256 + var3[3]) * 0.1)).append("L/100km").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 1, var2.append(var3[4] * 256 + var3[5]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingComputerInfo1() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(2, 0, var3.append((float)((double)(var2[2] * 256 + var2[3]) * 0.1)).append("L/100km").toString()));
      var1.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[5] + "km/h"));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(2, 2, var3.append(var2[6] * 256 + var2[7]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingComputerInfo2() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(3, 0, var2.append((float)((double)(var3[2] * 256 + var3[3]) * 0.1)).append("L/100km").toString()));
      var1.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "km/h"));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(3, 2, var5.append(var4[6] * 256 + var4[7]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRearRadar() {
      GeneralParkData.radar_distance_disable = new int[]{0, 255};
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var1[6], var1[7], var1[8], var1[9]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLight_0x67() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
      var1.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setLock_0x65() {
      ArrayList var4 = new ArrayList();
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
      int var1 = this.mCanBusInfoInt[3];
      Integer var5 = 0;
      var1 = DataHandleUtils.getIntFromByteWithBit(var1, 0, 2);
      int var3 = this.mCanBusInfoInt[3];
      Integer var6 = 1;
      var4.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(var3, 7, 1)));
      var4.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var3 = this.mDifferentId;
      if (var3 != 252 && var3 != 251) {
         var4.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2)));
      } else if (var2 == 2) {
         var4.add(new SettingUpdateEntity(3, 2, var6));
      } else if (var2 == 0) {
         var4.add(new SettingUpdateEntity(3, 2, var5));
      }

      var4.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      var2 = this.mDifferentId;
      if (var2 != 252 && var2 != 251) {
         var4.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
      } else if (var1 == 2) {
         var4.add(new SettingUpdateEntity(3, 4, var6));
      } else if (var1 == 0) {
         var4.add(new SettingUpdateEntity(3, 4, var5));
      }

      var4.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalPanel() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 1) {
         if (var1[3] > voice_btn_data) {
            this.realKeyClick4(7);
            voice_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < voice_btn_data) {
            this.realKeyClick4(8);
            voice_btn_data = this.mCanBusInfoInt[3];
         }
      } else {
         if (var1[3] > up_dn_btn_data) {
            this.realKeyClick4(46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      }

   }

   private void setOutDoorTem() {
      if (UiMgr.mDiffid == 254) {
         this.updateOutDoorTemp(this.mContext, " ");
      } else {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      }

   }

   private void setPanelKnob() {
      boolean var3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ^ true;
      mIsKonbClockwise = var3;
      int var1;
      if (var3) {
         var1 = this.mCanBusInfoInt[3];
      } else {
         var1 = 255 - this.mCanBusInfoInt[3] + 1;
      }

      this.mKonbCount = var1;
      if (var1 != 0) {
         int var2 = this.mCanBusInfoInt[2];
         if (var2 != 1) {
            if (var2 == 2 || var2 == 3) {
               if (var1 != 0) {
                  this.mSelKonbCount = var1;
               }

               if (var3) {
                  this.startSelKonbIncrease();
               } else {
                  this.startSelKonbDecrease();
               }
            }
         } else {
            this.konbKeyVol(var1);
         }

      }
   }

   private void setRemote_0x66() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)));
      var1.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var1.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var1.add(new SettingUpdateEntity(4, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var1.add(new SettingUpdateEntity(4, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
      var1.add(new SettingUpdateEntity(4, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      var1.add(new SettingUpdateEntity(4, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
      var1.add(new SettingUpdateEntity(4, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      var1.add(new SettingUpdateEntity(4, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var1.add(new SettingUpdateEntity(4, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2)));
      var1.add(new SettingUpdateEntity(4, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
      var1.add(new SettingUpdateEntity(4, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSportMode_0x85() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(8, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(8, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTpms_0x68() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         ArrayList var1 = new ArrayList();
         String var3 = this.getTpmsWarningStr(this.mCanBusInfoInt[13]);
         int[] var2 = this.mCanBusInfoInt;
         var1.add(this.getTireEntity(0, var3, this.getTpmsPressureNumStr(var2[3], var2[4]), this.getTpmsCheckWarningStr(this.mCanBusInfoInt[13])));
         String var4 = this.getTpmsWarningStr(this.mCanBusInfoInt[14]);
         int[] var5 = this.mCanBusInfoInt;
         var1.add(this.getTireEntity(1, var4, this.getTpmsPressureNumStr(var5[5], var5[6]), this.getTpmsCheckWarningStr(this.mCanBusInfoInt[14])));
         var4 = this.getTpmsWarningStr(this.mCanBusInfoInt[15]);
         var5 = this.mCanBusInfoInt;
         var1.add(this.getTireEntity(2, var4, this.getTpmsPressureNumStr(var5[7], var5[8]), this.getTpmsCheckWarningStr(this.mCanBusInfoInt[15])));
         var4 = this.getTpmsWarningStr(this.mCanBusInfoInt[16]);
         var5 = this.mCanBusInfoInt;
         var1.add(this.getTireEntity(3, var4, this.getTpmsPressureNumStr(var5[9], var5[10]), this.getTpmsCheckWarningStr(this.mCanBusInfoInt[16])));
         StringBuilder var6 = new StringBuilder();
         var5 = this.mCanBusInfoInt;
         var1.add(this.getTireEntitySpare(var6.append(var5[11] * 256 + var5[12]).append("").toString()));
         GeneralTireData.dataList = var1;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setTrackInfo() {
      if (this.isTrackDataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void settingLeft0Right12Data(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 12, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void startSelKonbDecrease() {
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            while(true) {
               try {
                  if (this.this$0.mCurSource.equals("FM")) {
                     sleep(20L);
                  } else {
                     sleep(1200L);
                  }
               } catch (InterruptedException var2) {
                  var2.printStackTrace();
               }

               if (MsgMgr.access$210(this.this$0) == 0) {
                  return;
               }

               this.this$0.realKeyClick4(47);
            }
         }
      }).start();
   }

   private void startSelKonbIncrease() {
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            while(true) {
               try {
                  if (this.this$0.mCurSource.equals("FM")) {
                     sleep(20L);
                  } else {
                     sleep(1200L);
                  }
               } catch (InterruptedException var2) {
                  var2.printStackTrace();
               }

               if (MsgMgr.access$210(this.this$0) <= 0) {
                  return;
               }

               this.this$0.realKeyClick4(48);
            }
         }
      }).start();
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OnStarActivity);
      SingletonForKt.INSTANCE.init(var1, (UiMgr)UiMgrFactory.getCanUiMgr(var1));
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -111, 8, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -111, 12, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -111, 10, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, isDiscExist}, var1), 16, 32));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, isDiscExist}, var1), 16, 32));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, isDiscExist}, var1), 16, 32));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      byte[] var5 = this.mCanBusInfoByte;
      byte[] var4 = new byte[var5.length - 2];
      this.mcuByteArrayNoByte1 = var4;
      if (var5.length - 2 >= 0) {
         System.arraycopy(var5, 2, var4, 0, var5.length - 2);
      }

      this.mContext = var1;
      int[] var6 = this.mCanBusInfoInt;
      int var3 = var6[1];
      if (var3 != 33) {
         if (var3 != 34) {
            if (var3 != 49) {
               if (var3 != 50) {
                  if (var3 != 52) {
                     if (var3 != 53) {
                        if (var3 != 65) {
                           if (var3 != 117) {
                              if (var3 != 133) {
                                 if (var3 != 180) {
                                    if (var3 != 189) {
                                       if (var3 != 240) {
                                          if (var3 != 69) {
                                             if (var3 != 70) {
                                                if (var3 != 85) {
                                                   if (var3 != 86) {
                                                      if (var3 != 177) {
                                                         if (var3 != 178) {
                                                            if (var3 != 194) {
                                                               if (var3 != 195) {
                                                                  switch (var3) {
                                                                     case 17:
                                                                        this.sendSwcKey(var6[4], var6[5]);
                                                                        this.setTrackInfo();
                                                                        SingletonForKt.INSTANCE.set0x11Data(this.mCanBusInfoInt, (MsgMgr)Objects.requireNonNull(MsgMgrFactory.getCanMsgMgr(var1)));
                                                                        break;
                                                                     case 18:
                                                                        SingletonForKt.INSTANCE.set0x12Data(this.mCanBusInfoInt);
                                                                        this.setDoorData();
                                                                        this.setCarStatusInfo();
                                                                        this.updateDriveDataActivity((Bundle)null);
                                                                        break;
                                                                     case 19:
                                                                        if (!this.isDataChange(var6)) {
                                                                           return;
                                                                        }

                                                                        this.setDrivingComputerInfo0();
                                                                        break;
                                                                     case 20:
                                                                        if (!this.isDataChange(var6)) {
                                                                           return;
                                                                        }

                                                                        this.setDrivingComputerInfo1();
                                                                        break;
                                                                     case 21:
                                                                        if (!this.isDataChange(var6)) {
                                                                           return;
                                                                        }

                                                                        this.setDrivingComputerInfo2();
                                                                        break;
                                                                     default:
                                                                        switch (var3) {
                                                                           case 101:
                                                                              if (!this.isDataChange(var6)) {
                                                                                 return;
                                                                              }

                                                                              this.setLock_0x65();
                                                                              break;
                                                                           case 102:
                                                                              if (!this.isDataChange(var6)) {
                                                                                 return;
                                                                              }

                                                                              this.setRemote_0x66();
                                                                              break;
                                                                           case 103:
                                                                              if (!this.isDataChange(var6)) {
                                                                                 return;
                                                                              }

                                                                              this.setLight_0x67();
                                                                              SingletonForKt.INSTANCE.set0x67Data(this.mCanBusInfoInt);
                                                                              this.updateSettingActivity((Bundle)null);
                                                                              break;
                                                                           case 104:
                                                                              if (!this.isDataChange(var6)) {
                                                                                 return;
                                                                              }

                                                                              this.setTpms_0x68();
                                                                              break;
                                                                           case 105:
                                                                              if (!this.isDataChange(var6)) {
                                                                                 return;
                                                                              }

                                                                              this.setCompass_0x69();
                                                                        }
                                                                  }
                                                               } else {
                                                                  if (!this.isDataChange(var6)) {
                                                                     return;
                                                                  }

                                                                  this.setBtPhoneName_0xc3();
                                                               }
                                                            } else {
                                                               if (!this.isDataChange(var6)) {
                                                                  return;
                                                               }

                                                               this.setBtPincode_0xc2();
                                                            }
                                                         } else {
                                                            this.onStarCallInfo_0xb2();
                                                         }
                                                      } else {
                                                         if (!this.isDataChange(var6)) {
                                                            return;
                                                         }

                                                         this.OnstarInfo_0xb1();
                                                      }
                                                   } else {
                                                      if (!this.isDataChange(var6)) {
                                                         return;
                                                      }

                                                      this.setComfort_0x56();
                                                   }
                                                } else {
                                                   if (!this.isDataChange(var6)) {
                                                      return;
                                                   }

                                                   this.setComfort_0x55();
                                                }
                                             } else {
                                                if (!this.isDataChange(var6)) {
                                                   return;
                                                }

                                                this.setCollisionDetetion_0x46();
                                             }
                                          } else {
                                             if (!this.isDataChange(var6)) {
                                                return;
                                             }

                                             this.setCollisionDetetion_0x45();
                                          }
                                       } else {
                                          if (!this.isDataChange(var6)) {
                                             return;
                                          }

                                          this.setVersionInfo();
                                       }
                                    } else {
                                       if (!this.isDataChange(var6)) {
                                          return;
                                       }

                                       this.onStarMyNum_0xbd();
                                    }
                                 } else {
                                    if (!this.isDataChange(var6)) {
                                       return;
                                    }

                                    this.onStartCurrentNum_0xb4();
                                 }
                              } else {
                                 if (!this.isDataChange(var6)) {
                                    return;
                                 }

                                 this.setSportMode_0x85();
                              }
                           } else {
                              if (!this.isDataChange(var6)) {
                                 return;
                              }

                              this.setDashboardDisplay_0x75();
                           }
                        } else {
                           if (!this.isDataChange(var6)) {
                              return;
                           }

                           this.setFrontRearRadar();
                        }
                     } else {
                        if (!this.isDataChange(var6)) {
                           return;
                        }

                        this.setAcSystem_0x35();
                        SingletonForKt.INSTANCE.set0x35Data(this.mCanBusInfoInt);
                        this.updateSettingActivity((Bundle)null);
                     }
                  } else {
                     if (!this.isDataChange(var6)) {
                        return;
                     }

                     this.setConsumptionTrip_0x34();
                  }
               } else {
                  if (!this.isDataChange(var6)) {
                     return;
                  }

                  this.setCarInfo_0x32();
               }
            } else {
               this.setAirInfo(var6);
            }
         } else {
            SingletonForKt.INSTANCE.set0x22Data(var2, var1);
         }
      } else {
         this.realKeyLongClick1(var1, SingletonForKt.INSTANCE.set0x21Data(this.mCanBusInfoInt), this.mCanBusInfoInt[3]);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var10, 0, 0, 0, 0});
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      isDiscExist = (byte)var4;
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var11 = (new DecimalFormat("00")).format((long)(var2 / 60 % 60));
      var12 = (new DecimalFormat("00")).format((long)(var2 % 60));
      var13 = (new DecimalFormat("000")).format((long)var5) + " " + var11 + ":" + var12 + "   ";
      byte var14 = 7;
      var1 = var14;
      if (var7 != 1) {
         var1 = var14;
         if (var7 != 2) {
            var1 = var14;
            if (var7 != 3) {
               if (var7 != 6 && var7 != 7) {
                  var1 = 0;
               } else {
                  var1 = 6;
               }
            }
         }
      }

      byte var17 = isDiscExist;
      Context var15 = this.mContext;
      var12 = SourceConstantsDef.SOURCE_ID.MPEG.name();
      byte[] var16 = var13.getBytes();
      this.sendMediaMsg(var15, var12, DataHandleUtils.byteMerger(new byte[]{22, -111, var1, var17}, var16));
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -111, 8, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.mContext = var1;
      this.addPanelKeyList();
      this.addSwcKeyList();
      IntentFilter var2 = new IntentFilter();
      var2.addAction("com.hzbhd.action.sourcerealchange");
      var1.registerReceiver(this.receiver, var2);
      SingletonForKt.INSTANCE.sendCarData(this.mDifferentId);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = (new DecimalFormat("00")).format((long)(var7 & 255));
      var11 = (new DecimalFormat("000")).format((long)(var9 * 256 + (var3 & 255))) + " " + var12 + ":" + var11 + "   ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      var2 = isDiscExist;
      byte[] var25 = var11.getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -111, var1, var2}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandFm(var2)) {
         if (var3.length() == 5) {
            var2 = var3.substring(0, 4);
            var2 = "0" + var1 + " 0" + var2 + " MHz";
         } else if (var3.length() == 4) {
            var2 = var3.substring(0, 4);
            var2 = "0" + var1 + "  " + var2 + " MHz";
         } else {
            var2 = var3.substring(0, 5);
            var2 = "0" + var1 + " " + var2 + " MHz";
         }
      } else if (var3.length() == 3) {
         var2 = "0" + var1 + " " + var3 + " KHz  ";
      } else {
         var2 = "0" + var1 + " " + var3 + " KHz ";
      }

      byte var7 = isDiscExist;
      Context var8 = this.mContext;
      var4 = SourceConstantsDef.SOURCE_ID.FM.name();
      byte[] var9 = var2.getBytes();
      this.sendMediaMsg(var8, var4, DataHandleUtils.byteMerger(new byte[]{22, -111, var6, var7}, var9));
   }

   public void setLanguage_recNull(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, "__1168_SAVE_LANGUAGE", 0);
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(9, 0, var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = (new DecimalFormat("00")).format((long)(var7 & 255));
      var8 = (new DecimalFormat("000")).format((long)(var9 * 256 + (var3 & 255))) + " " + var12 + ":" + var8 + "   ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      var2 = isDiscExist;
      byte[] var18 = var8.getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -111, var1, var2}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
   }

   private class PanelKeyList {
      private static final int INVALID_KEY_ID = -1;
      private int canCmdId;
      private int panelKeyId;
      final MsgMgr this$0;

      PanelKeyList(MsgMgr var1, int var2, int var3) {
         this.this$0 = var1;
         this.canCmdId = var2;
         this.panelKeyId = var3;
      }

      void sendPanelKey(Context var1, int var2) {
         int var3 = this.panelKeyId;
         if (var3 != -1) {
            this.this$0.realKeyLongClick1(var1, var3, var2);
         }

      }
   }
}
