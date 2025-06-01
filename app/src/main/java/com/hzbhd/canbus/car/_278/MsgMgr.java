package com.hzbhd.canbus.car._278;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   protected static final byte AMP_BAL_OFFSET = 0;
   protected static final byte AMP_EQ_OFFSET = 1;
   protected static final byte AMP_FRONT_REAR_OFFSET = 10;
   protected static final byte AMP_VOL_OFFSET = 22;
   protected static final String CAN_278_SAVE_AMP_BASS = "__278_SAVE_AMP_BASS";
   protected static final String CAN_278_SAVE_AMP_FR = "__278_SAVE_AMP_FR";
   protected static final String CAN_278_SAVE_AMP_LR = "__278_SAVE_AMP_LR";
   protected static final String CAN_278_SAVE_AMP_MID = "__278_SAVE_AMP_MID";
   protected static final String CAN_278_SAVE_AMP_TRE = "__278_SAVE_AMP_TRE";
   protected static final String CAN_278_SAVE_RADAR_DISP = "__278_SAVE_RADAR_DISP";
   private static final String SOURCE_REAL_CHANGE_ACTION = "com.hzbhd.action.sourcerealchange";
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private static boolean isSeatFirst;
   private static boolean isWarnFirst;
   private static boolean mIsKonbClockwise;
   protected static int maxRadarLen;
   protected static int trackType;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusBtnPanelInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusRadarInfoCopy;
   private byte[] mCanBusSwcDataCopy;
   private byte[] mCanBusSwcInfoCopy;
   private byte[] mCanBusWarnInfoCopy;
   private Context mContext;
   private String mCurSource = "";
   private int mDifferentId;
   private int mFrontCameraStatusNow;
   private int[] mFrontSeatNew;
   private boolean mIsAirFirst = true;
   private boolean mIsAirRearFirst = true;
   private boolean mIsPowerOff = false;
   private boolean mIsRightCameraFirst = true;
   private int mKonbCount = 0;
   private int mLackClickSt = 0;
   private int mLastKonbCount = 0;
   private int mMediaStatusNow;
   private int mPanoramicStatusNow;
   private int[] mRearSeatNew;
   private int mSelKonbCount = 0;
   byte[] mcuByteArrayNoByte1;
   private ArrayList panelKeyLists = new ArrayList();
   private int panleMuteKeyId = 0;
   private int panleMuteKeySt = 0;
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
   private int seatRearData = 0;
   int[] stringLevIds = new int[]{2131760289, 2131760290, 2131760291, 2131760292, 2131760293, 2131760294};
   private ArrayList swcKeyLists = new ArrayList();

   // $FF: synthetic method
   static int access$410(MsgMgr var0) {
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
      this.panelKeyLists.add(new PanelKeyList(this, 2, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 3, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 4, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 5, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 6, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 7, 129));
      this.panelKeyLists.add(new PanelKeyList(this, 8, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 9, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 10, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 11, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 12, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 13, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 14, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 15, -16));
      this.panelKeyLists.add(new PanelKeyList(this, 16, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 17, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 18, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 19, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 20, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 21, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 22, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 23, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 24, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 25, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 26, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 27, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 28, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 29, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 30, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 31, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 32, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 33, 7));
      this.panelKeyLists.add(new PanelKeyList(this, 34, 8));
      this.panelKeyLists.add(new PanelKeyList(this, 35, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 36, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 37, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 38, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 39, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 40, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 41, 47));
      this.panelKeyLists.add(new PanelKeyList(this, 42, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 43, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 44, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 45, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 46, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 47, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 48, 48));
      this.panelKeyLists.add(new PanelKeyList(this, 49, 2));
      this.panelKeyLists.add(new PanelKeyList(this, 50, 52));
      this.panelKeyLists.add(new PanelKeyList(this, 51, 14));
      this.panelKeyLists.add(new PanelKeyList(this, 52, 15));
      this.panelKeyLists.add(new PanelKeyList(this, 53, 58));
      this.panelKeyLists.add(new PanelKeyList(this, 54, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 55, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 56, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 57, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 58, 50));
      this.panelKeyLists.add(new PanelKeyList(this, 59, 27));
      this.panelKeyLists.add(new PanelKeyList(this, 60, 4));
      this.panelKeyLists.add(new PanelKeyList(this, 61, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 62, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 63, -1));
      this.panelKeyLists.add(new PanelKeyList(this, 64, 45));
      this.panelKeyLists.add(new PanelKeyList(this, 65, 46));
      this.panelKeyLists.add(new PanelKeyList(this, 66, 47));
      this.panelKeyLists.add(new PanelKeyList(this, 67, 48));
      this.panelKeyLists.add(new PanelKeyList(this, 68, 17));
   }

   private void addSwcKeyList() {
      ArrayList var1 = this.swcKeyLists;
      if (var1 != null) {
         var1.clear();
      }

      this.swcKeyLists.add(new PanelKeyList(this, 0, -1));
      this.swcKeyLists.add(new PanelKeyList(this, 1, 7));
      this.swcKeyLists.add(new PanelKeyList(this, 2, 8));
      this.swcKeyLists.add(new PanelKeyList(this, 3, -1));
      this.swcKeyLists.add(new PanelKeyList(this, 4, -1));
      this.swcKeyLists.add(new PanelKeyList(this, 5, -1));
      this.swcKeyLists.add(new PanelKeyList(this, 6, -1));
      this.swcKeyLists.add(new PanelKeyList(this, 7, 2));
      this.swcKeyLists.add(new PanelKeyList(this, 8, -1));
      this.swcKeyLists.add(new PanelKeyList(this, 9, 14));
      this.swcKeyLists.add(new PanelKeyList(this, 10, 15));
      this.swcKeyLists.add(new PanelKeyList(this, 11, 21));
      this.swcKeyLists.add(new PanelKeyList(this, 12, 20));
      this.swcKeyLists.add(new PanelKeyList(this, 13, 3));
      this.swcKeyLists.add(new PanelKeyList(this, 14, 3));
   }

   private void answerAndPhoneOffIsOneKey(Context var1) {
      byte var2;
      if (this.mDifferentId == 4) {
         var2 = 1;
      } else {
         var2 = 0;
      }

      FutureUtil.instance.setIsOneKey(var2);
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
   }

   private void cleanRearBlow() {
      GeneralAirData.rear_left_blow_window = false;
      GeneralAirData.rear_left_blow_head = false;
      GeneralAirData.rear_left_blow_foot = false;
      GeneralAirData.rear_right_blow_window = false;
      GeneralAirData.rear_right_blow_head = false;
      GeneralAirData.rear_right_blow_foot = false;
      GeneralAirData.rear_left_auto_wind = false;
      GeneralAirData.rear_right_auto_wind = false;
   }

   private void enterRightCam() {
      CommUtil.isMiscReverse();
      if (this.isFrontCameraStatusChange()) {
         Context var3 = this.mContext;
         int var1 = this.mCanBusInfoInt[2];
         boolean var2 = true;
         if (var1 != 1) {
            var2 = false;
         }

         this.switchFCamera(var3, var2);
      }

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
      int[] var4 = this.mCanBusInfoInt;
      int var2 = var4[2];
      int var1 = var4[3];
      int[] var3 = new int[]{var2 & 223, var1 & 248, var4[4], var4[5], var4[6], var4[7], var4[8], var4[9]};
      short var5;
      if (!Arrays.equals(this.mAirFrontDataNow, var3)) {
         var5 = 1001;
      } else {
         var5 = -1;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var3, 8);
      return var5;
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIntByBoolean(boolean var1) {
      return var1 ? 1 : 0;
   }

   private float getRound(float var1) {
      float var2 = DataHandleUtils.getRound(var1, 3);
      int var3 = (int)(100.0F * var2);
      var1 = var2;
      if (Math.abs(var3) % 10 >= 5) {
         if (var3 > 0) {
            var1 = var2 + 0.05F;
         } else {
            var1 = var2 - 0.05F;
         }
      }

      return DataHandleUtils.getRound(var1, 1);
   }

   private double getRoundHalfUp(double var1, int var3) {
      return (new BigDecimal(var1)).setScale(var3, RoundingMode.HALF_DOWN).doubleValue();
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "__278_SAVE_AMP_FR", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "__278_SAVE_AMP_LR", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "__278_SAVE_AMP_BASS", 0);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "__278_SAVE_AMP_MID", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "__278_SAVE_AMP_TRE", 0);
      }

      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 6, (byte)(GeneralAmplifierData.frontRear + 10)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 5, (byte)(GeneralAmplifierData.leftRight + 10)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 4, (byte)(GeneralAmplifierData.bandBass + 0)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, (byte)(GeneralAmplifierData.bandTreble + 0)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 2, (byte)(GeneralAmplifierData.bandMiddle + 0)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 1, 32});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusAirInfoCopy)) {
         return true;
      } else {
         this.mCanBusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isBtnPanelMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusBtnPanelInfoCopy)) {
         return true;
      } else {
         this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanBusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
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

   private boolean isFrontCameraStatusChange() {
      int var1 = this.mCanBusInfoInt[2];
      if (this.mFrontCameraStatusNow == var1) {
         if (this.mIsRightCameraFirst) {
            this.mIsRightCameraFirst = false;
         }

         return false;
      } else {
         this.mFrontCameraStatusNow = var1;
         return true;
      }
   }

   private boolean isRadarMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusRadarInfoCopy)) {
         return true;
      } else {
         this.mCanBusRadarInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isRearFirst() {
      if (this.mIsAirRearFirst) {
         this.mIsAirRearFirst = false;
         return !GeneralAirData.rear_power;
      } else {
         return false;
      }
   }

   private boolean isSwcDataMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcDataCopy)) {
         return true;
      } else {
         this.mCanBusSwcDataCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isSwcMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
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

   private void panelKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick5(var3, var1, var2[2], var2[3]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private String resolveFrontAirTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else if (var1 >= 1 && var1 <= 17) {
         var2 = "LEV: " + var1;
      } else if (var1 >= 112 && var1 <= 144) {
         var2 = (float)(var1 - 116) * 0.5F + 18.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "LEV: " + var1;
      }

      return var2;
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[7];
      String var2;
      if (var1 >= 0 && var1 <= 250) {
         var2 = (float)this.mCanBusInfoInt[7] * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveRearAirTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = (float)(var1 - 116) * 0.5F + 18.0F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void saveAmplifierData() {
      SharePreUtil.setIntValue(this.mContext, "__278_SAVE_AMP_FR", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "__278_SAVE_AMP_LR", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "__278_SAVE_AMP_BASS", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "__278_SAVE_AMP_TRE", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "__278_SAVE_AMP_MID", GeneralAmplifierData.bandMiddle);
   }

   private void sendPanelAirKey() {
      if (this.mLackClickSt == 0) {
         int[] var1 = this.mCanBusInfoInt;
         if (var1[3] == 1) {
            switch (var1[2]) {
               case 55:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 28, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 28, 0});
                  break;
               case 56:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 29, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 29, 0});
                  break;
               case 57:
                  UiMgr.sendAirCommandFrontWindMode();
            }
         }
      }

      this.mLackClickSt = this.mCanBusInfoInt[3];
   }

   private void sendPanelInitCmds() {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.mDifferentId});
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
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 54 && var1[3] == 1) {
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

   private void setAirData_0x23() {
      byte[] var1 = this.bytesExpectOneByte(this.mCanBusInfoByte, 7);
      this.setOutDoorTem();
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var1)) {
         if (!this.isFirst()) {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            this.cleanAllBlow();
            switch (this.mCanBusInfoInt[3]) {
               case 1:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  break;
               case 2:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 3:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 4:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 5:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 6:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 7:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
            GeneralAirData.front_left_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[5]);
            GeneralAirData.front_right_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[6]);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setAmplifierData0x37() {
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[7] - 10;
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[6] - 10;
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[3];
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[4];
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData();
      Log.i("setAmplifierData0x37", "setAmplifierData0x37  mCanBusInfoInt[9]=" + this.mCanBusInfoInt[9]);
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[8];
      if (var1 - 1 >= 0) {
         --var1;
      } else {
         var1 = 0;
      }

      var2.add(new SettingUpdateEntity(1, 0, var1));
      var2.add(new SettingUpdateEntity(1, 1, this.mCanBusInfoInt[9]));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarInfo_1_0x29() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.getRound((float)this.mCanBusInfoInt[2] * 0.75F - 48.0F) + "℃"));
      var1.add(new DriverUpdateEntity(0, 1, this.getRound((float)(this.mCanBusInfoInt[3] - 40)) + " ℃"));
      var1.add(new DriverUpdateEntity(0, 2, this.getRound((float)this.mCanBusInfoInt[4] * 0.25F) + "V"));
      var1.add(new DriverUpdateEntity(0, 3, this.getRound((float)this.mCanBusInfoInt[5] * 0.59F) + "Kpa"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarInfo_2_0x36() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1 = this.mContext.getResources().getString(2131760281);
      } else {
         var1 = this.mContext.getResources().getString(2131760315);
      }

      var2.add(new DriverUpdateEntity(1, 0, var1 + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°"));
      if (this.mCanBusInfoInt[3] == 255) {
         var1 = "--";
      } else {
         var1 = this.mCanBusInfoInt[3] + "%";
      }

      var2.add(new DriverUpdateEntity(1, 1, var1));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var1 = this.mContext.getResources().getString(2131760304);
      } else {
         var1 = this.mContext.getResources().getString(2131760288);
      }

      var2.add(new DriverUpdateEntity(1, 2, var1 + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7) + "°"));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         var1 = this.mContext.getResources().getString(2131760297);
      } else {
         var1 = this.mContext.getResources().getString(2131760314);
      }

      var2.add(new DriverUpdateEntity(1, 3, var1));
      StringBuilder var4 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(1, 4, var4.append(var3[6] * 256 + var3[7]).append(" KM").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarSet_0x31() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, this.getLimitVal(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4))));
      var1.add(new SettingUpdateEntity(0, 1, this.getLimitVal(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))));
      var1.add(new SettingUpdateEntity(0, 2, this.getLimitVal(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
      var1.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      var1.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
      var1.add(new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
      var1.add(new SettingUpdateEntity(0, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)));
      var1.add(new SettingUpdateEntity(0, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
      var1.add(new SettingUpdateEntity(0, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(0, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)));
      var1.add(new SettingUpdateEntity(0, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(0, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var1.add(new SettingUpdateEntity(0, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
      var1.add(new SettingUpdateEntity(0, 13, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      var1.add(new SettingUpdateEntity(0, 14, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
      var1.add(new SettingUpdateEntity(0, 15, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      var1.add(new SettingUpdateEntity(0, 16, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var1.add(new SettingUpdateEntity(0, 17, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)));
      var1.add(new SettingUpdateEntity(0, 18, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRadar() {
      GeneralParkData.radar_distance_disable = new int[]{0, 255};
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setOutDoorTem() {
      if (this.mCanBusInfoInt[7] == 255) {
         this.updateOutDoorTemp(this.mContext, " ");
      } else {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      }

   }

   private void setPanelKnob() {
      int var1 = this.mCanBusInfoInt[2];
      int var2 = this.mLastKonbCount;
      boolean var3;
      if (var1 > var2) {
         var3 = true;
      } else {
         var3 = false;
      }

      mIsKonbClockwise = var3;
      if (var2 >= 255 && var1 > 0) {
         mIsKonbClockwise = true;
      }

      if (var2 == 1 && var1 == 255) {
         mIsKonbClockwise = false;
      }

      var3 = mIsKonbClockwise;
      if (!var3) {
         var1 = 255 - var1 + 1;
      }

      this.mKonbCount = var1;
      if (var1 != 0) {
         if (var1 != 0) {
            this.mSelKonbCount = var1;
         }

         if (var3) {
            this.startSelKonbIncrease();
         } else {
            this.startSelKonbDecrease();
         }

         this.mLastKonbCount = this.mCanBusInfoInt[2];
      }
   }

   private void setRearAirDatas_0x34() {
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isRearFirst()) {
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            this.cleanRearBlow();
            int var1 = this.mCanBusInfoInt[2];
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     GeneralAirData.rear_left_blow_foot = true;
                     GeneralAirData.rear_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_right_blow_head = true;
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_foot = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_right_blow_head = true;
            }

            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[3];
            GeneralAirData.rear_temperature = this.resolveRearAirTemp(this.mCanBusInfoInt[12]);
            if (!Arrays.equals(this.mAirRearDataNow, this.mCanBusInfoInt)) {
               this.updateAirActivity(this.mContext, 1002);
            }

            int[] var2 = this.mCanBusInfoInt;
            this.mAirRearDataNow = Arrays.copyOf(var2, var2.length);
         }
      }
   }

   private void setRearRadar() {
      GeneralParkData.radar_distance_disable = new int[]{0, 255};
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int var1 = maxRadarLen;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(var1, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSeatSet_0x35() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(2, 0, this.getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4)]))).setValueStr(true));
      var1.add((new SettingUpdateEntity(2, 1, this.getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)]))).setValueStr(true));
      var1.add((new SettingUpdateEntity(2, 2, this.getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4)]))).setValueStr(true));
      var1.add((new SettingUpdateEntity(2, 3, this.getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)]))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      if (GeneralAirData.front_left_seat_heat_level > 3) {
         GeneralAirData.front_left_seat_heat_level = 0;
      }

      if (GeneralAirData.front_right_seat_heat_level > 3) {
         GeneralAirData.front_right_seat_heat_level = 0;
      }

      if (GeneralAirData.rear_left_seat_heat_level > 3) {
         GeneralAirData.rear_left_seat_heat_level = 0;
      }

      if (GeneralAirData.front_left_seat_cold_level > 3) {
         GeneralAirData.front_left_seat_cold_level = 0;
      }

      if (GeneralAirData.front_right_seat_cold_level > 3) {
         GeneralAirData.front_right_seat_cold_level = 0;
      }

      this.mFrontSeatNew = new int[]{GeneralAirData.front_left_seat_heat_level, GeneralAirData.front_right_seat_heat_level, GeneralAirData.front_left_seat_cold_level, GeneralAirData.front_right_seat_cold_level};
      int[] var2 = new int[]{GeneralAirData.front_left_seat_heat_level, GeneralAirData.front_right_seat_heat_level, GeneralAirData.front_left_seat_cold_level, GeneralAirData.front_right_seat_cold_level};
      if (isSeatFirst) {
         this.mAirFrontDataNow = Arrays.copyOf(var2, 4);
         isSeatFirst = false;
      } else if (!Arrays.equals(this.mAirFrontDataNow, var2)) {
         this.mAirFrontDataNow = Arrays.copyOf(var2, 4);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setTrackInfo() {
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[2];
      int var1 = var4[3];
      int var2 = var3 * 256 + var1;
      if (trackType != 0) {
         var2 = (var3 * 256 + var1) / 20;
         if (DataHandleUtils.getBoolBit0(var1)) {
            GeneralParkData.trackAngle = var2 * 40 / 510;
         } else {
            GeneralParkData.trackAngle = -(var2 * 40 / 510);
         }

         if (GeneralParkData.trackAngle <= -40) {
            GeneralParkData.trackAngle = -40;
         }

         if (GeneralParkData.trackAngle >= 40) {
            GeneralParkData.trackAngle = 40;
         }

         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         if (var2 > 0 && var2 <= 5500) {
            GeneralParkData.trackAngle = var2 * 40 / 5500;
         } else {
            if (var2 <= 60036 || var2 >= 65535) {
               if (var2 == 0) {
                  GeneralParkData.trackAngle = 0;
                  this.updateParkUi((Bundle)null, this.mContext);
               }

               return;
            }

            GeneralParkData.trackAngle = -('\uffff' - var2) * 40 / 5500;
         }

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

   private void sndPanelMuteKey(int var1, int var2) {
      if (this.mIsPowerOff) {
         if (this.panleMuteKeyId == 9 && this.panleMuteKeySt == 1 && var2 == 0) {
            this.realKeyClick(this.mContext, 134);
         }
      } else if (this.panleMuteKeyId == 9 && this.panleMuteKeySt == 1 && var2 == 0) {
         this.realKeyClick(this.mContext, 3);
      } else if (var1 == 9 && var2 == 2 && this.panleMuteKeySt != 2) {
         this.realKeyClick(this.mContext, 134);
      }

      this.panleMuteKeyId = var1;
      this.panleMuteKeySt = var2;
   }

   private void startSelKonbDecrease() {
      (new Thread(new Runnable(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            while(true) {
               try {
                  if (this.this$0.mCurSource.equals("FM")) {
                     Thread.sleep(20L);
                  } else {
                     Thread.sleep(1200L);
                  }
               } catch (InterruptedException var2) {
                  var2.printStackTrace();
               }

               if (MsgMgr.access$410(this.this$0) == 0) {
                  return;
               }

               this.this$0.realKeyClick4(47);
            }
         }
      })).start();
   }

   private void startSelKonbIncrease() {
      (new Thread(new Runnable(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            while(true) {
               try {
                  if (this.this$0.mCurSource.equals("FM")) {
                     Thread.sleep(20L);
                  } else {
                     Thread.sleep(1200L);
                  }
               } catch (InterruptedException var2) {
                  var2.printStackTrace();
               }

               if (MsgMgr.access$410(this.this$0) <= 0) {
                  return;
               }

               this.this$0.realKeyClick4(48);
            }
         }
      })).start();
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mcuByteArrayNoByte1 = new byte[this.mCanBusInfoByte.length - 2];
      int var3 = 0;

      while(true) {
         byte[] var4 = this.mCanBusInfoByte;
         if (var3 >= var4.length - 2) {
            int[] var5 = this.mCanBusInfoInt;
            var3 = var5[1];
            if (var3 != 48) {
               if (var3 != 49) {
                  if (var3 != 64) {
                     if (var3 != 127) {
                        switch (var3) {
                           case 33:
                              this.sendSwcKey(var5[2], var5[3]);
                              break;
                           case 34:
                              this.sendPanelKey(var5[2], var5[3]);
                              this.sendPanelNaviKey();
                              this.sendPanelAirKey();
                              var5 = this.mCanBusInfoInt;
                              this.sndPanelMuteKey(var5[2], var5[3]);
                              break;
                           case 35:
                              this.setAirData_0x23();
                              break;
                           default:
                              switch (var3) {
                                 case 38:
                                    if (!this.isDataChange(var5)) {
                                       return;
                                    }

                                    this.setRearRadar();
                                    break;
                                 case 39:
                                    if (this.isRadarMsgReturn(var4)) {
                                       return;
                                    }

                                    this.setFrontRadar();
                                    break;
                                 case 40:
                                    if (this.isDoorMsgReturn(var2)) {
                                       return;
                                    }

                                    this.setDoorData();
                                    break;
                                 case 41:
                                    if (!this.isDataChange(var5)) {
                                       return;
                                    }

                                    this.setCarInfo_1_0x29();
                                    break;
                                 default:
                                    switch (var3) {
                                       case 53:
                                          if (!this.isDataChange(var5)) {
                                             return;
                                          }

                                          this.setSeatSet_0x35();
                                          break;
                                       case 54:
                                          if (!this.isDataChange(var5)) {
                                             return;
                                          }

                                          this.setCarInfo_2_0x36();
                                          break;
                                       case 55:
                                          if (!this.isDataChange(var5)) {
                                             return;
                                          }

                                          this.setAmplifierData0x37();
                                    }
                              }
                        }
                     } else {
                        if (!this.isDataChange(var5)) {
                           return;
                        }

                        this.setVersionInfo();
                     }
                  } else {
                     this.enterRightCam();
                  }
               } else {
                  if (!this.isDataChange(var5)) {
                     return;
                  }

                  this.setCarSet_0x31();
               }
            } else {
               if (!this.isDataChange(var5)) {
                  return;
               }

               this.setTrackInfo();
            }

            return;
         }

         this.mcuByteArrayNoByte1[var3] = var4[var3 + 2];
         ++var3;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var8, (byte)var6});
   }

   int getLimitVal(int var1) {
      --var1;
      return var1 >= 0 ? var1 : 0;
   }

   String getResString(int var1) {
      return this.mContext.getResources().getString(var1);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      int var2 = this.getCurrentCanDifferentId();
      this.mDifferentId = var2;
      if (var2 == 3 || var2 == 1 || var2 == 2 || var2 == 4) {
         maxRadarLen = 4;
      }

      if (var2 == 4 || var2 == 8 || var2 == 12) {
         trackType = 1;
      }

      this.initAmplifierData(var1);
      this.initRadarDisp(var1);
      this.answerAndPhoneOffIsOneKey(var1);
      this.sendPanelInitCmds();
      this.addPanelKeyList();
      this.addSwcKeyList();
      IntentFilter var3 = new IntentFilter();
      var3.addAction("com.hzbhd.action.sourcerealchange");
      var1.registerReceiver(this.receiver, var3);
   }

   void initRadarDisp(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, "__278_SAVE_RADAR_DISP", 0);
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(0, 20, var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
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
      this.mIsPowerOff = var1;
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

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
         if (var3 != -1 && var2 != 0) {
            if (var3 != 7 && var3 != 8) {
               if (var3 != 45 && var3 != 46) {
                  this.this$0.realKeyClick5(var1, var3, this.canCmdId, var2);
               } else {
                  this.this$0.realKeyClick4(var3);
               }
            } else if (var2 >= 1) {
               this.this$0.realKeyClick(var1, var3);
            }
         }

      }
   }
}
