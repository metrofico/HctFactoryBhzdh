package com.hzbhd.canbus.car._111;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final int _1111_AMPLIFIER_BAND_MAX = 1;
   static final int _1111_AMPLIFIER_HALF_MAX = 10;
   private final int DATA_TYPE = 1;
   private int[] mAmplifierDataNow;
   private int mAmplifierSwitch;
   private boolean mBackStatus;
   private int mCanId;
   private SparseArray mCanbusDataArray = new SparseArray();
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private Context mContext;
   private boolean mFrontStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private TimerUtil mTimerUtil = new TimerUtil();
   private UiMgr mUiMgr;

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("AM") && !var1.equals("AM3") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
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

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         AmplifierPageUiSet var4 = this.getUiMgr(var1).getAmplifierPageUiSet(var1);
         this.getAmplifierData(var1, this.mCanId, var4);
      }

      byte var3 = (byte)GeneralAmplifierData.volume;
      byte var2 = (byte)(10 - GeneralAmplifierData.frontRear);
      byte[] var5 = new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.leftRight + 10)};
      byte[] var8 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandBass + 1)};
      byte[] var6 = new byte[]{22, -124, 6, (byte)(GeneralAmplifierData.bandTreble + 1)};
      byte[] var9 = new byte[]{22, -124, 7, (byte)(GeneralAmplifierData.bandMiddle + 1)};
      TimerUtil var7 = new TimerUtil();
      var7.startTimer(new TimerTask(this, new byte[][]{{22, -124, 2, var3}, {22, -124, 3, var2}, var5, var8, var6, var9}, var7) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$array;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$array = var2;
            this.val$timerUtil = var3;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$array;
            if (var1 < var2.length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.i;
            } else {
               this.val$timerUtil.stopTimer();
            }

         }
      }, 100L, 133L);
      this.mAmplifierSwitch = 1;
      this.mTimerUtil.stopTimer();
      this.mTimerUtil.startTimer(new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)this.this$0.mAmplifierSwitch});
         }
      }, 100L, 1000L);
   }

   private boolean isAmplifierDataChange(int[] var1) {
      if (Arrays.equals(var1, this.mAmplifierDataNow)) {
         return false;
      } else {
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
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

   private void set0x17Amplifier() {
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 1);
      int[] var8 = this.mCanbusInfoInt;
      int var4 = var8[3];
      int var5 = var8[4];
      int var1 = var8[5];
      int var2 = var8[6];
      int var7 = var8[7];
      int var3 = var8[8];
      ArrayList var9 = new ArrayList();
      var9.add(new SettingUpdateEntity(0, 0, var6));
      this.updateGeneralSettingData(var9);
      this.updateSettingActivity((Bundle)null);
      GeneralAmplifierData.volume = var4;
      GeneralAmplifierData.frontRear = 10 - var5;
      GeneralAmplifierData.leftRight = var1 - 10;
      GeneralAmplifierData.bandBass = var2 - 1;
      GeneralAmplifierData.bandTreble = var7 - 1;
      GeneralAmplifierData.bandMiddle = var3 - 1;
      if (this.isAmplifierDataChange(new int[]{GeneralAmplifierData.leftRight, GeneralAmplifierData.frontRear, GeneralAmplifierData.bandBass, GeneralAmplifierData.bandMiddle, GeneralAmplifierData.bandTreble, GeneralAmplifierData.volume})) {
         this.updateAmplifierActivity((Bundle)null);
         this.saveAmplifierData(this.mContext, this.mCanId);
      }

   }

   private void set0x20WheelKeyData(Context var1) {
      int[] var3 = this.mCanbusInfoInt;
      short var2 = 2;
      switch (var3[2]) {
         case 1:
            var2 = 7;
            break;
         case 2:
            var2 = 8;
            break;
         case 3:
            var2 = 20;
            break;
         case 4:
            var2 = 21;
            break;
         case 5:
            var2 = 3;
            break;
         case 6:
         default:
            var2 = 0;
         case 7:
            break;
         case 8:
            var2 = 187;
            break;
         case 9:
            var2 = 14;
      }

      this.realKeyLongClick1(var1, var2, var3[3]);
   }

   private void set0x24DoorData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x29TrackData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         byte[] var2 = this.mCanbusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 1000, 16);
         Log.i("cbc", "set0x29TrackData: GeneralParkData.trackAngle <--> " + GeneralParkData.trackAngle);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanbusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 0, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanbusInfoInt = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoByte = var2;
      byte var3 = var2[1];
      if (var3 != 23) {
         if (var3 != 32) {
            if (var3 != 36) {
               if (var3 != 41) {
                  if (var3 == 48) {
                     this.set0x30VersionInfo();
                  }
               } else {
                  this.set0x29TrackData(var1);
               }
            } else {
               this.set0x24DoorData(var1);
            }
         } else {
            this.set0x20WheelKeyData(var1);
         }
      } else {
         this.set0x17Amplifier();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         var4 = var5;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, 16, 0, 1, (byte)DataHandleUtils.rangeNumber(var4, 255), 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, 1});
      Log.d("fxHouAcc", "初始化");
      this.initAmplifier(var1);
   }

   public void onAccOn() {
      super.onAccOn();
      Context var1 = this.mContext;
      if (var1 != null) {
         this.initAmplifier(var1);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var7;
      if (var2.contains("FM1")) {
         var7 = 1;
      } else if (var2.contains("FM2")) {
         var7 = 2;
      } else if (var2.contains("FM3")) {
         var7 = 3;
      } else if (var2.contains("AM1")) {
         var7 = 17;
      } else if (var2.contains("AM2")) {
         var7 = 18;
      } else if (var2.contains("AM3")) {
         var7 = 19;
      } else {
         var7 = 0;
      }

      int[] var6 = this.getFreqByteHiLo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 0, (byte)var7, (byte)var6[1], (byte)var6[0], (byte)var1, 0, 0});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      Log.d("fxHouAcc", "" + var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   void updateAmplifierSwitch(int var1) {
      this.mAmplifierSwitch = var1;
   }
}
