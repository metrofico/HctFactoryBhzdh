package com.hzbhd.canbus.car._29;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   static final int SHARE_29_AMPLIFIER_HALF_MAX = 10;
   static final int SHARE_29_AMPLIFIER_START = 6;
   static final String SHARE_29_IS_REVERSING = "share_29_is_reversing";
   private int[] mAmplifierDataNow;
   private boolean mBackStatus;
   private CallBackInterface mCallBackInterface;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   private int mDifferent;
   private int mEachId;
   private boolean mFrontStatus;
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private int[] mKnobNow;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private int mOutDoorTempNow;
   private int[] mPanelKeyNow;
   private int[] mPanoramicNow;
   private int mPanoramicStatusNow;
   private int[] mRadarDataNow;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private int mSeatStatusNow;
   private SeatStatusView mSeatStatusView;
   private int[] mSettingDataNow;
   private int[] mTrackDataNow;
   private int mTuneKnobNow;
   private UiMgr mUiMgr;
   private int[] mVehicleDataNow;
   private byte[] mVersionInfoNow;
   private int mVolumeKnobNow;
   private int[] mWheelKeyNow;
   private boolean nowIsMute = false;

   public static byte[] byteMerger(byte[] var0, byte[] var1) {
      byte[] var2 = new byte[var0.length + var1.length];
      System.arraycopy(var0, 0, var2, 0, var0.length);
      System.arraycopy(var1, 0, var2, var0.length, var1.length);
      return var2;
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
      GeneralAirData.front_auto_wind_model = false;
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_foot = false;
   }

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private String getInOutCycleState(boolean var1) {
      return var1 ? "ON" : "OFF";
   }

   private String getNowCarName(int var1) {
      switch (var1) {
         case 1:
            return this.mContext.getString(2131761318);
         case 2:
            return this.mContext.getString(2131761319);
         case 3:
            return this.mContext.getString(2131761320);
         case 4:
            return this.mContext.getString(2131761321);
         case 5:
            return this.mContext.getString(2131761322);
         case 6:
            return this.mContext.getString(2131761323);
         case 7:
            return this.mContext.getString(2131761324);
         default:
            return this.mContext.getString(2131761317);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData() {
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 6 + 10)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 6 + 10)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(GeneralAmplifierData.bandBass + 6)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(GeneralAmplifierData.bandMiddle + 6)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(GeneralAmplifierData.bandTreble + 6)});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private boolean isAmplifierDataChange() {
      if (Arrays.equals(this.mAmplifierDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return GeneralAirData.power ^ true;
      } else {
         return false;
      }
   }

   private boolean isKnobChange() {
      if (Arrays.equals(this.mKnobNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mKnobNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isOutDoorTempChange() {
      int var2 = this.mOutDoorTempNow;
      int var1 = this.mCanBusInfoInt[13];
      if (var2 == var1) {
         return false;
      } else {
         this.mOutDoorTempNow = var1;
         return true;
      }
   }

   private boolean isPanelKeyChange() {
      if (Arrays.equals(this.mPanelKeyNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanelKeyNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicDataChange() {
      if (Arrays.equals(this.mPanoramicNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicStatusChange() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      if (this.mPanoramicStatusNow == var1) {
         return false;
      } else {
         this.mPanoramicStatusNow = var1;
         return true;
      }
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRadarDataNotZero() {
      int var1 = 2;

      while(true) {
         int[] var2 = this.mCanBusInfoInt;
         if (var1 >= var2.length) {
            return false;
         }

         if (var2[var1] != 0) {
            return true;
         }

         ++var1;
      }
   }

   private boolean isSeatStatusChange() {
      int var2 = this.mSeatStatusNow;
      int var1 = this.mCanBusInfoInt[15];
      if (var2 == var1) {
         return false;
      } else {
         this.mSeatStatusNow = var1;
         return true;
      }
   }

   private boolean isSettingDataChange() {
      if (Arrays.equals(this.mSettingDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mSettingDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackDataChange() {
      int[] var2 = new int[2];
      int[] var1 = this.mCanBusInfoInt;
      var2[0] = var1[8];
      var2[1] = var1[9];
      if (Arrays.equals(this.mTrackDataNow, var2)) {
         return false;
      } else {
         this.mTrackDataNow = Arrays.copyOf(var2, 2);
         return true;
      }
   }

   private boolean isVehicleDataChange() {
      if (Arrays.equals(this.mVehicleDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mVehicleDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void panelKeyClick2(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick2(var3, var1, var2[2], var2[3]);
   }

   private void realKeyClick5(int var1) {
      this.realKeyClick5(this.mContext, var1, 0, this.mCanBusInfoInt[5]);
   }

   private void reportHiworldSrcInfo(byte var1, byte var2) {
      byte[] var4 = new byte[15];
      var4[0] = 22;
      var4[1] = var1;
      var4[2] = var2;

      for(int var3 = 3; var3 < 15; ++var3) {
         var4[var3] = 32;
      }

      CanbusMsgSender.sendMsg(var4);
   }

   private String resolveDriveData(int var1, int var2) {
      var1 = var1 * 256 + var2;
      String var3;
      if (var1 != 65535) {
         var3 = Integer.toString(var1);
      } else {
         var3 = " ";
      }

      return var3;
   }

   private String resolveFrontAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else if (var1 >= 35 && var1 <= 63) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = " ";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      return (float)this.mCanBusInfoInt[13] * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private boolean resolvePanoramicBtnStatus(int var1) {
      Context var4 = this.mContext;
      boolean var3 = false;
      byte var2;
      if (SharePreUtil.getBoolValue(var4, "share_29_is_reversing", false)) {
         var2 = 6;
      } else {
         var2 = 1;
      }

      if (var1 + var2 == DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)) {
         var3 = true;
      }

      return var3;
   }

   private void sendCarDifferent() {
      int var1;
      switch (this.mDifferent) {
         case 0:
            var1 = 32;
            break;
         case 1:
            var1 = 33;
            break;
         case 2:
            var1 = 43;
            break;
         case 3:
            var1 = 34;
            break;
         case 4:
            var1 = 42;
            break;
         case 5:
            var1 = 45;
            break;
         case 6:
            var1 = 46;
            break;
         case 7:
            var1 = 35;
            break;
         case 8:
            var1 = 47;
            break;
         case 9:
            var1 = 36;
            break;
         case 10:
            var1 = 38;
            break;
         case 11:
            var1 = 37;
            break;
         case 12:
            var1 = 49;
            break;
         case 13:
            var1 = 39;
            break;
         case 14:
            var1 = 40;
            int var2 = this.mEachId;
            if (var2 != 0) {
               var1 = var2;
            }
            break;
         case 15:
            var1 = 44;
            break;
         case 16:
            var1 = 41;
            break;
         case 17:
            var1 = 48;
            break;
         case 18:
            var1 = 51;
            break;
         case 19:
            var1 = 52;
            break;
         case 20:
            var1 = 53;
            break;
         default:
            var1 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)var1, 9});
   }

   private void set0xPanoramic0xE9() {
      if (this.isPanoramicDataChange()) {
         SharePreUtil.setStringValue(this.mContext, "29_0xE9", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
         var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
         var1.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
         var1.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
         if (this.isPanoramicStatusChange()) {
            this.forceReverse(this.mContext, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
         }

         this.updatePanoramicStatus(true);
         this.updatePanoramicStatus(false);
      }

   }

   private void setAirData0x31() {
      byte[] var3 = this.bytesExpectOneByte(this.mCanBusInfoByte, 13);
      if (this.isOutDoorTempChange()) {
         this.setOutDoorTem();
      }

      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var3)) {
         if (!this.isFirst()) {
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
            ArrayList var4 = new ArrayList();
            var4.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "air_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "air_setting", "_29_air_in_out_auto_cycle"), this.getInOutCycleState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])))).setValueStr(true));
            this.updateGeneralSettingData(var4);
            this.updateSettingActivity((Bundle)null);
            boolean var2;
            if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]) && this.mCanBusInfoInt[6] != 2) {
               var2 = false;
            } else {
               var2 = true;
            }

            GeneralAirData.front_defog = var2;
            this.cleanAllBlow();
            int var1 = this.mCanBusInfoInt[6];
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 5) {
                           if (var1 != 6) {
                              switch (var1) {
                                 case 11:
                                    GeneralAirData.front_left_auto_wind = false;
                                    GeneralAirData.front_right_auto_wind = false;
                                    GeneralAirData.front_left_blow_window = true;
                                    GeneralAirData.front_right_blow_window = true;
                                    break;
                                 case 12:
                                    GeneralAirData.front_left_auto_wind = false;
                                    GeneralAirData.front_right_auto_wind = false;
                                    GeneralAirData.front_left_blow_window = true;
                                    GeneralAirData.front_left_blow_foot = true;
                                    GeneralAirData.front_right_blow_window = true;
                                    GeneralAirData.front_right_blow_foot = true;
                                    break;
                                 case 13:
                                    GeneralAirData.front_left_auto_wind = false;
                                    GeneralAirData.front_right_auto_wind = false;
                                    GeneralAirData.front_left_blow_window = true;
                                    GeneralAirData.front_left_blow_head = true;
                                    GeneralAirData.front_right_blow_window = true;
                                    GeneralAirData.front_right_blow_head = true;
                                    break;
                                 case 14:
                                    GeneralAirData.front_left_auto_wind = false;
                                    GeneralAirData.front_right_auto_wind = false;
                                    GeneralAirData.front_left_blow_window = true;
                                    GeneralAirData.front_left_blow_head = true;
                                    GeneralAirData.front_left_blow_foot = true;
                                    GeneralAirData.front_right_blow_window = true;
                                    GeneralAirData.front_right_blow_head = true;
                                    GeneralAirData.front_right_blow_foot = true;
                              }
                           } else {
                              GeneralAirData.front_left_auto_wind = false;
                              GeneralAirData.front_right_auto_wind = false;
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_right_blow_head = true;
                           }
                        } else {
                           GeneralAirData.front_left_auto_wind = false;
                           GeneralAirData.front_right_auto_wind = false;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_right_blow_foot = true;
                        }
                     } else {
                        GeneralAirData.front_left_auto_wind = false;
                        GeneralAirData.front_right_auto_wind = false;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_auto_wind = false;
                     GeneralAirData.front_right_auto_wind = false;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_auto_wind = true;
                  GeneralAirData.front_right_auto_wind = true;
               }
            } else {
               GeneralAirData.front_left_auto_wind = false;
               GeneralAirData.front_right_auto_wind = false;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_blow_foot = false;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[9]);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setAmplifierData0xA6() {
      if (this.isAmplifierDataChange()) {
         GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
         GeneralAmplifierData.leftRight = this.mCanBusInfoByte[3] - 6 - 10;
         GeneralAmplifierData.frontRear = this.mCanBusInfoByte[4] - 6 - 10;
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 6;
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 6;
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 6;
         this.saveAmplifierData(this.mContext, this.mCanId);
         int var1 = this.mCanBusInfoInt[8];
         if (var1 == 1) {
            if (!this.nowIsMute) {
               this.realKeyClick4(this.mContext, 3);
            }
         } else if (var1 == 0 && this.nowIsMute) {
            this.realKeyClick4(this.mContext, 3);
         }

         this.updateAmplifierActivity((Bundle)null);
      }

   }

   private void setDoorData0x12() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      this.mLeftFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      this.mRightFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      this.mLeftRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      this.mRightRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setPanelKey0x21() {
      if (this.isPanelKeyChange()) {
         int var1 = this.mCanBusInfoInt[2];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 6) {
                        if (var1 != 22) {
                           if (var1 != 36) {
                              if (var1 != 40) {
                                 if (var1 != 43) {
                                    if (var1 != 47) {
                                       if (var1 != 91) {
                                          if (var1 != 92) {
                                             switch (var1) {
                                                case 53:
                                                   this.panelKeyClick2(183);
                                                   break;
                                                case 54:
                                                   this.panelKeyClick2(58);
                                                   break;
                                                case 55:
                                                   this.panelKeyClick2(21);
                                                   break;
                                                case 56:
                                                   this.panelKeyClick2(20);
                                                   break;
                                                case 57:
                                                   this.panelKeyClick2(128);
                                                   break;
                                                default:
                                                   switch (var1) {
                                                      case 71:
                                                         this.panelKeyClick2(76);
                                                         break;
                                                      case 72:
                                                         this.panelKeyClick2(77);
                                                         break;
                                                      case 73:
                                                         this.panelKeyClick2(139);
                                                         break;
                                                      case 74:
                                                         this.panelKeyClick2(185);
                                                         break;
                                                      case 75:
                                                         this.panelKeyClick2(129);
                                                   }
                                             }
                                          } else {
                                             this.panelKeyClick2(46);
                                          }
                                       } else {
                                          this.panelKeyClick2(45);
                                       }
                                    } else {
                                       this.panelKeyClick2(52);
                                    }
                                 } else {
                                    this.panelKeyClick2(52);
                                 }
                              } else {
                                 this.panelKeyClick2(14);
                              }
                           } else {
                              this.panelKeyClick2(59);
                           }
                        } else {
                           this.panelKeyClick2(49);
                        }
                     } else {
                        this.panelKeyClick2(50);
                     }
                  } else {
                     this.panelKeyClick2(20);
                  }
               } else {
                  this.panelKeyClick2(21);
               }
            } else {
               this.panelKeyClick2(134);
            }
         } else {
            this.panelKeyClick2(0);
         }
      }

   }

   private void setPanelKnob0x22() {
      if (this.isKnobChange()) {
         int var1 = this.mCanBusInfoInt[2];
         int var2;
         if (var1 == 1) {
            var2 = this.mCanBusInfoByte[3];
            var1 = var2 - this.mVolumeKnobNow;
            if (var1 == 0) {
               return;
            }

            this.mVolumeKnobNow = var2;
            var2 = this.getVolume();
            if (var1 > 0 && var2 <= 40) {
               this.turnKnob3_1(7, Math.abs(var1));
            }

            if (var1 < 0 && var2 >= 0) {
               this.turnKnob3_1(8, Math.abs(var1));
            }
         } else if (var1 == 2) {
            byte var3 = this.mCanBusInfoByte[3];
            var2 = var3 - this.mTuneKnobNow;
            if (var2 == 0) {
               return;
            }

            this.mTuneKnobNow = var3;
            if (var2 > 0) {
               var3 = 48;
            } else {
               var3 = 47;
            }

            this.turnKnob3_2(var3, Math.abs(var2));
         }
      }

   }

   private void setRadarData0x41() {
      if (this.isRadarDataChange()) {
         int var1 = this.mCanBusInfoInt[13];
         int[] var2;
         if (var1 == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            var2 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
            var2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(3, var2[6], var2[7], var2[8], var2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         } else if (var1 == 1) {
            GeneralParkData.isShowDistanceNotShowLocationUi = true;
            GeneralParkData.radar_distance_disable = new int[]{0};
            var2 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(var2[2], var2[3], var2[4], var2[5]);
            var2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarDistanceData(var2[6], var2[7], var2[8], var2[9]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         }

         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSettingData0x61() {
      if (this.isSettingDataChange()) {
         SharePreUtil.setStringValue(this.mContext, "29_0x61", Base64.encodeToString(this.mCanBusInfoByte, 0));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 7, 1)));
         var1.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 7, 1)));
         var1.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 6, 1)));
         var1.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 5, 1)));
         var1.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 4, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVehicleData0x32() {
      if (this.isVehicleDataChange()) {
         ArrayList var2 = new ArrayList();
         StringBuilder var4 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         var2.add(new DriverUpdateEntity(0, 0, var4.append(this.resolveDriveData(var3[4], var3[5])).append(" rpm").toString()));
         StringBuilder var6 = new StringBuilder();
         int[] var7 = this.mCanBusInfoInt;
         var2.add(new DriverUpdateEntity(0, 1, var6.append(this.resolveDriveData(var7[6], var7[7])).append(" km/h").toString()));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
         int[] var5 = this.mCanBusInfoInt;
         int var1 = var5[4] * 256 + var5[5];
         if (var1 != 65535) {
            this.updateSpeedInfo(var1);
         }
      }

      if (this.isSeatStatusChange()) {
         if (this.mSeatStatusView == null) {
            this.mSeatStatusView = new SeatStatusView(this, this.mContext);
         }

         this.mSeatStatusView.mSeatStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 5, 3);
         this.mSeatStatusView.mBackrestStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 3, 2);
         if (this.mCallBackInterface == null) {
            this.mCallBackInterface = new CallBackInterface(this) {
               final MsgMgr this$0;

               {
                  this.this$0 = var1;
               }

               public void callback() {
                  this.this$0.mSeatStatusView.refreshUi();
               }
            };
         }

         this.runOnUi(this.mCallBackInterface);
      }

   }

   private void setVehicleType0x26() {
      if (this.mCanBusInfoInt[2] == 9) {
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_29_about"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_29_about", "_29_car_info"), this.getNowCarName(this.mCanBusInfoInt[3]))).setValueStr(true));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      CarState.setPARKState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
      CarState.setREVState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
      CarState.setILLState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
      CarState.setACCState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 32) {
         if (var1 != 33) {
            switch (var1) {
               case 0:
                  this.realKeyClick5(0);
                  break;
               case 1:
                  this.realKeyClick5(7);
                  break;
               case 2:
                  this.realKeyClick5(8);
                  break;
               case 3:
                  this.realKeyClick5(3);
                  break;
               case 4:
                  this.realKeyClick5(187);
                  break;
               case 5:
                  this.realKeyClick5(14);
                  break;
               case 6:
                  this.realKeyClick5(15);
                  break;
               default:
                  switch (var1) {
                     case 8:
                        this.realKeyClick5(45);
                        break;
                     case 9:
                        this.realKeyClick5(46);
                        break;
                     case 10:
                        this.realKeyClick5(2);
                        break;
                     case 11:
                        this.realKeyClick5(2);
                        break;
                     default:
                        switch (var1) {
                           case 69:
                              this.realKeyClick5(185);
                              break;
                           case 70:
                              this.realKeyClick5(128);
                              break;
                           case 71:
                              this.realKeyClick5(14);
                        }
                  }
            }
         } else {
            this.realKeyClick5(20);
         }
      } else {
         this.realKeyClick5(21);
      }

      if (this.isTrackDataChange()) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[9], var2[8], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void turnKnob3_1(int var1, int var2) {
      this.realKeyClick3_1(this.mContext, var1, 0, var2);
   }

   private void turnKnob3_2(int var1, int var2) {
      this.realKeyClick3_2(this.mContext, var1, 0, var2);
   }

   private void updatePanoramicStatus(boolean var1) {
      ArrayList var4 = new ArrayList();

      for(int var2 = 0; var2 < 5; ++var2) {
         boolean var3;
         if (!var1 && this.resolvePanoramicBtnStatus(var2)) {
            var3 = true;
         } else {
            var3 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(var2, var3));
      }

      GeneralParkData.dataList = var4;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -46, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var2 = new byte[15];
      var2[0] = 22;
      var2[1] = -46;
      var2[2] = 12;

      for(int var1 = 3; var1 < 15; ++var1) {
         var2[var1] = 32;
      }

      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var2);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -46, 21, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      var2 = "            ".getBytes();
      CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, var2));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 38) {
                     if (var3 != 65) {
                        if (var3 != 97) {
                           if (var3 != 166) {
                              if (var3 != 233) {
                                 if (var3 != 240) {
                                    if (var3 != 49) {
                                       if (var3 == 50) {
                                          this.setVehicleData0x32();
                                       }
                                    } else {
                                       this.setAirData0x31();
                                    }
                                 } else {
                                    this.setVersionInfo0xF0();
                                 }
                              } else {
                                 this.set0xPanoramic0xE9();
                              }
                           } else {
                              this.setAmplifierData0xA6();
                           }
                        } else {
                           this.setSettingData0x61();
                        }
                     } else {
                        this.setRadarData0x41();
                     }
                  } else {
                     this.setVehicleType0x26();
                  }
               } else {
                  this.setPanelKnob0x22();
               }
            } else {
               this.setPanelKey0x21();
            }
         } else {
            this.setDoorData0x12();
         }
      } else {
         this.setWheelKey0x11();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.nowIsMute = var2;
      if (var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, 1});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, 0});
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var16 = 1;
      byte var15 = (byte)var8;
      byte var14 = (byte)var6;
      if (!var10) {
         var16 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, var15, var14, 0, 0, (byte)var16, 0, 0, 0, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var16 = (byte)(var2 / 60 % 60);
      byte var15 = (byte)(var2 % 60);
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

      var11 = (new DecimalFormat("00")).format((long)(var16 & 255));
      var12 = (new DecimalFormat("00")).format((long)(var15 & 255));
      byte[] var17 = ("0" + var11 + var12 + (new DecimalFormat("0000")).format((long)var5) + "   ").getBytes();
      var17 = byteMerger(new byte[]{22, -46, var1}, var17);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var17);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -46, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mEachId = this.getCurrentEachCanId();
      this.sendCarDifferent();
      this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, 0});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = (new DecimalFormat("00")).format((long)(var6 & 255));
      var12 = (new DecimalFormat("00")).format((long)(var7 & 255));
      var12 = "0" + var11 + var12 + (new DecimalFormat("0000")).format((long)((var9 * 256 + var3) % 10000)) + "   ";
      byte[] var25;
      if (var1 == 9) {
         var25 = new byte[]{22, -46, 14};
      } else {
         var25 = new byte[]{22, -46, 13};
      }

      var25 = byteMerger(var25, var12.getBytes());
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = " " + var3 + "KHZ    ";
         } else {
            var2 = "  " + var3 + "KHZ    ";
         }
      } else if (var3.length() == 5) {
         var2 = " " + var3 + "MHZ   ";
      } else {
         var2 = var3 + "MHZ   ";
      }

      byte[] var7 = var2.getBytes();
      var7 = byteMerger(new byte[]{22, -46, var6}, var7);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.reportHiworldSrcInfo((byte)-46, (byte)0);
   }

   void updateSettingData(int var1) {
      LogUtil.showLog("updateSettingData:" + var1);
      ArrayList var2 = new ArrayList();
      String var3;
      byte[] var4;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     var3 = SharePreUtil.getStringValue(this.mContext, "29_0xA6", (String)null);
                     if (!TextUtils.isEmpty(var3)) {
                        var2.add((new SettingUpdateEntity(4, 0, Base64.decode(var3, 0)[2])).setProgress(this.mCanBusInfoInt[2]));
                     }
                  }
               } else {
                  var3 = SharePreUtil.getStringValue(this.mContext, "29_0x61", (String)null);
                  if (!TextUtils.isEmpty(var3)) {
                     var4 = Base64.decode(var3, 0);
                     var2.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(var4[3], 5, 1)));
                     var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(var4[3], 4, 1)));
                  }
               }
            } else {
               var3 = SharePreUtil.getStringValue(this.mContext, "29_0x61", (String)null);
               if (!TextUtils.isEmpty(var3)) {
                  var4 = Base64.decode(var3, 0);
                  var2.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(var4[3], 7, 1)));
                  var2.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(var4[3], 6, 1)));
               }
            }
         } else {
            var3 = SharePreUtil.getStringValue(this.mContext, "29_0x61", (String)null);
            if (!TextUtils.isEmpty(var3)) {
               var2.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(Base64.decode(var3, 0)[2], 7, 1)));
            }
         }
      } else {
         var3 = SharePreUtil.getStringValue(this.mContext, "29_0xE9", (String)null);
         if (!TextUtils.isEmpty(var3)) {
            var4 = Base64.decode(var3, 0);
            var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(var4[2], 6, 2)));
            var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(var4[2], 4, 2)));
            var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(var4[3], 5, 1)));
            var2.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(var4[3], 4, 1)));
         }
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = (new DecimalFormat("00")).format((long)(var6 & 255));
      var11 = (new DecimalFormat("00")).format((long)(var7 & 255));
      var11 = "0" + var8 + var11 + (new DecimalFormat("0000")).format((long)((var9 * 256 + var3) % 10000)) + "   ";
      byte[] var18;
      if (var1 == 9) {
         var18 = new byte[]{22, -46, 14};
      } else {
         var18 = new byte[]{22, -46, 22};
      }

      var18 = byteMerger(var18, var11.getBytes());
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
   }

   private class SeatStatusView {
      private boolean isShowing;
      private int mBackrestStatus;
      private RelativeLayout mFloatView;
      private ImageView mIvBackrestBack;
      private ImageView mIvBackrestForward;
      private ImageView mIvBackrestSelelct;
      private ImageView mIvSeatBack;
      private ImageView mIvSeatDown;
      private ImageView mIvSeatForward;
      private ImageView mIvSeatSelect;
      private ImageView mIvSeatUp;
      private WindowManager.LayoutParams mLayoutParams;
      private Runnable mRunnable;
      private int mSeatStatus;
      private WindowManager mWindowManager;
      final MsgMgr this$0;

      public SeatStatusView(MsgMgr var1, Context var2) {
         this.this$0 = var1;
         var1.mContext = var2;
         this.mWindowManager = (WindowManager)var1.mContext.getSystemService("window");
         this.mRunnable = new Runnable(this, var1) {
            final SeatStatusView this$1;
            final MsgMgr val$this$0;

            {
               this.this$1 = var1;
               this.val$this$0 = var2;
            }

            public void run() {
               this.this$1.dismissView();
            }
         };
         this.findView();
         this.initView();
      }

      private void addViewToWindow() {
         if (this.mLayoutParams == null) {
            WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
            this.mLayoutParams = var1;
            var1.type = 2002;
            this.mLayoutParams.gravity = 17;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
         }

         if (!this.isShowing) {
            this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
            this.isShowing = true;
         }

         this.mFloatView.removeCallbacks(this.mRunnable);
         this.mFloatView.postDelayed(this.mRunnable, 5000L);
      }

      private void dismissView() {
         WindowManager var2 = this.mWindowManager;
         if (var2 != null) {
            RelativeLayout var1 = this.mFloatView;
            if (var1 != null) {
               var2.removeView(var1);
               this.isShowing = false;
            }
         }

      }

      private void findView() {
         RelativeLayout var1 = (RelativeLayout)LayoutInflater.from(this.this$0.mContext).inflate(2131558743, (ViewGroup)null);
         this.mFloatView = var1;
         this.mIvSeatSelect = (ImageView)var1.findViewById(2131362638);
         this.mIvBackrestSelelct = (ImageView)this.mFloatView.findViewById(2131362539);
         this.mIvSeatForward = (ImageView)this.mFloatView.findViewById(2131362637);
         this.mIvSeatBack = (ImageView)this.mFloatView.findViewById(2131362635);
         this.mIvSeatUp = (ImageView)this.mFloatView.findViewById(2131362639);
         this.mIvSeatDown = (ImageView)this.mFloatView.findViewById(2131362636);
         this.mIvBackrestForward = (ImageView)this.mFloatView.findViewById(2131362538);
         this.mIvBackrestBack = (ImageView)this.mFloatView.findViewById(2131362536);
      }

      private void initView() {
         this.mFloatView.setOnClickListener(new View.OnClickListener(this) {
            final SeatStatusView this$1;

            {
               this.this$1 = var1;
            }

            public void onClick(View var1) {
               this.this$1.mFloatView.removeCallbacks(this.this$1.mRunnable);
               this.this$1.mFloatView.post(this.this$1.mRunnable);
            }
         });
      }

      private void refreshUi() {
         ImageView var4 = this.mIvSeatSelect;
         int var1 = this.mSeatStatus;
         boolean var3 = false;
         boolean var2;
         if (var1 != 1 && var1 != 2 && var1 != 3 && var1 != 4) {
            var2 = false;
         } else {
            var2 = true;
         }

         this.setVisible(var4, var2);
         var4 = this.mIvSeatForward;
         if (this.mSeatStatus == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.setVisible(var4, var2);
         var4 = this.mIvSeatBack;
         if (this.mSeatStatus == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.setVisible(var4, var2);
         var4 = this.mIvSeatUp;
         if (this.mSeatStatus == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.setVisible(var4, var2);
         var4 = this.mIvSeatDown;
         if (this.mSeatStatus == 4) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.setVisible(var4, var2);
         var4 = this.mIvBackrestSelelct;
         var1 = this.mBackrestStatus;
         if (var1 != 1 && var1 != 2) {
            var2 = false;
         } else {
            var2 = true;
         }

         this.setVisible(var4, var2);
         var4 = this.mIvBackrestForward;
         if (this.mBackrestStatus == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.setVisible(var4, var2);
         var4 = this.mIvBackrestBack;
         var2 = var3;
         if (this.mBackrestStatus == 2) {
            var2 = true;
         }

         this.setVisible(var4, var2);
         this.addViewToWindow();
      }

      private void setVisible(View var1, boolean var2) {
         byte var3;
         if (var2) {
            var3 = 0;
         } else {
            var3 = 4;
         }

         var1.setVisibility(var3);
      }
   }
}
