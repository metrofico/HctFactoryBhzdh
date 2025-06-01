package com.hzbhd.canbus.car._323;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String DEVICE_WORK_MODE_AUX = "AUX";
   static final String DEVICE_WORK_MODE_CD = "CD";
   static final String DEVICE_WORK_MODE_MEDIA_OFF = "Media Off";
   static final String DEVICE_WORK_MODE_OTHERS = "Others";
   static final String DEVICE_WORK_MODE_RADIO = "Radio";
   private static final String SHARE_323_AMPLIFIER_BALANCE = "share_323_amplifier_balance";
   private static final String SHARE_323_AMPLIFIER_BASS = "share_323_amplifier_bass";
   private static final String SHARE_323_AMPLIFIER_FADE = "share_323_amplifier_fade";
   private static final String SHARE_323_AMPLIFIER_MIDDLE = "share_323_amplifier_middle";
   private static final String SHARE_323_AMPLIFIER_TREBLE = "share_323_amplifier_treble";
   private static final String SHARE_323_AMPLIFIER_VOLUME = "share_323_amplifier_volume";
   static final int VEHICLE_TYPE_ES = 5;
   static final int VEHICLE_TYPE_IS = 1;
   static final int VEHICLE_TYPE_RX = 18;
   static final int _323_AMPLIFIER_BAND_MAX = 2;
   static final int _323_AMPLIFIER_HALF_MAX = 7;
   private static boolean isOriginalCdFirst;
   public static List mList;
   private final int DATA_TYPE = 1;
   private String _0x61runningStatus = " ";
   int currentDisc;
   int currentPtore;
   private String isCdSongList = "";
   private boolean isOriginalCd = true;
   private int[] m0x1dData;
   private int[] m0x1eData;
   private byte[] m0x31Command = new byte[7];
   private byte[] mAirData;
   private int[] mAmplifierDataNow;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private int mCurrentClick;
   private int mCurrentStatus;
   DecimalFormat mDecimalFormat = new DecimalFormat("00");
   private String mDeviceStatusNow;
   private int mDifferentId;
   private boolean mFrontStatus;
   private int mKeyStatus;
   private int mLastDataCd = 0;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private List mList1 = new ArrayList();
   private List mList2 = new ArrayList();
   private OriginalCarDevicePageUiSet mOriginalSet;
   private int mPanel;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private int mRockKey;
   private HashMap mSettingItemIndeHashMap = new HashMap();
   private byte[] mTrackData;
   private UiMgr mUiMgr;

   private void changeOriginalDevicePage(List var1, String[] var2, String[] var3, boolean var4, boolean var5) {
      OriginalCarDevicePageUiSet var6 = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      if (var6 != null) {
         var6.setItems(var1);
         var6.setRowTopBtnAction(var2);
         var6.setRowBottomBtnAction(var3);
         var6.setHaveSongList(var4);
         var6.setHavePlayTimeSeekBar(var5);
         Bundle var7 = new Bundle();
         var7.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var7);
      }
   }

   private void cleanDevice() {
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.am_st = false;
      GeneralOriginalCarDeviceData.st = false;
      GeneralOriginalCarDeviceData.scan = false;
      GeneralOriginalCarDeviceData.disc_scan = false;
      GeneralOriginalCarDeviceData.rpt = false;
      GeneralOriginalCarDeviceData.rpt_disc = false;
      GeneralOriginalCarDeviceData.rdm_off = false;
      GeneralOriginalCarDeviceData.rdm_disc = false;
      GeneralOriginalCarDeviceData.startTime = "     ";
      GeneralOriginalCarDeviceData.endTime = "     ";
      GeneralOriginalCarDeviceData.progress = 0;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void cleanSongList() {
      if (GeneralOriginalCarDeviceData.songList != null) {
         GeneralOriginalCarDeviceData.songList.clear();
      }

   }

   private String disStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "Have Disk";
      } else {
         var2 = "No Disk";
      }

      return var2;
   }

   private boolean equal(Object var1, Object... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1.equals(var2[var3])) {
            return true;
         }
      }

      return false;
   }

   private String getCdStatus(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : this.mContext.getString(2131762493);
         } else {
            return this.mContext.getString(2131762492);
         }
      } else {
         return this.mContext.getString(2131762491);
      }
   }

   private String getChangerStatus(int var1) {
      return CommUtil.getStrByResId(this.mContext, "_288_divice_status_" + var1);
   }

   private String getDeviceWorkMode(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 255) {
                     var2 = "";
                  } else {
                     var2 = "Others";
                  }
               } else {
                  var2 = "AUX";
               }
            } else {
               var2 = "CD";
            }
         } else {
            var2 = "Radio";
         }
      } else {
         var2 = "Media Off";
      }

      return var2;
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private double getMinute() {
      int[] var1 = this.mCanBusInfoInt;
      return (double)(var1[4] + var1[5]) / ((double)var1[11] * 0.01) / 60.0 % 60.0;
   }

   private String getRunStatus(int var1) {
      switch (var1) {
         case 0:
            return this.mContext.getString(2131762484);
         case 1:
            return this.mContext.getString(2131762485);
         case 2:
            return this.mContext.getString(2131762486);
         case 3:
            return this.mContext.getString(2131762487);
         case 4:
            return this.mContext.getString(2131762488);
         case 5:
            return this.mContext.getString(2131762489);
         case 6:
            return this.mContext.getString(2131762490);
         default:
            return "";
      }
   }

   private double getSecond() {
      int[] var1 = this.mCanBusInfoInt;
      return (double)(var1[4] + var1[5]) / ((double)var1[11] * 0.01) % 60.0;
   }

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         this.mSettingItemIndeHashMap.put(var1, new SettingUpdateEntity(-1, -1, (Object)null));
         return this.getSettingUpdateEntity(var1);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_323_amplifier_balance", GeneralAmplifierData.leftRight);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_323_amplifier_fade", GeneralAmplifierData.frontRear);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_323_amplifier_bass", GeneralAmplifierData.bandBass);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_323_amplifier_middle", GeneralAmplifierData.bandMiddle);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_323_amplifier_treble", GeneralAmplifierData.bandTreble);
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_323_amplifier_volume", GeneralAmplifierData.volume);
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            this.iterator = Arrays.stream(new byte[][]{{22, -127, 1}, {22, -124, 7, (byte)GeneralAmplifierData.volume}, {22, -124, 2, (byte)(GeneralAmplifierData.leftRight + 7)}, {22, -124, 1, (byte)(GeneralAmplifierData.frontRear + 7)}, {22, -124, 4, (byte)(GeneralAmplifierData.bandBass + 2)}, {22, -124, 6, (byte)(GeneralAmplifierData.bandMiddle + 2)}, {22, -124, 5, (byte)(GeneralAmplifierData.bandTreble + 2)}}).iterator();
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

   private void initSettingsItem(Context var1) {
      this.mSettingItemIndeHashMap = new HashMap();
      SparseArray var4 = new SparseArray();
      List var6 = this.getUiMgr(var1).getSettingUiSet(var1).getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var7 = ((SettingPageUiSet.ListBean.ItemListBean)var5.get(var3)).getTitleSrn();
            var4.append(var2 << 8 | var3, var7);
            this.mSettingItemIndeHashMap.put(var7, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private boolean is0x1dDataChange() {
      if (Arrays.equals(this.m0x1dData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x1dData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x1eDataChange() {
      if (Arrays.equals(this.m0x1eData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x1eData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAmplifierDataChange(int[] var1) {
      if (Arrays.equals(var1, this.mAmplifierDataNow)) {
         return false;
      } else {
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isCurrentStatus(String var1) {
      return var1.equals(GeneralOriginalCarDeviceData.cdStatus);
   }

   private boolean isDeviceStatusSame(String var1) {
      return this.isCurrentStatus(var1);
   }

   private boolean isDoorChange() {
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

   private boolean isOriginalCdChange() {
      int var1 = this.mCurrentStatus;
      if (var1 == this.mLastDataCd) {
         return false;
      } else {
         this.mLastDataCd = var1;
         if (isOriginalCdFirst) {
            isOriginalCdFirst = false;
            return false;
         } else {
            return true;
         }
      }
   }

   private boolean isTrackDataChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
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

   private void openAuxIn() {
      ComponentName var1 = new ComponentName("com.hzbhd.misc", "com.hzbhd.misc.auxin.MainActivity");
      Intent var2 = new Intent();
      var2.setComponent(var1);
      var2.setFlags(268435456);
      this.mContext.startActivity(var2);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveTime(int var1, int var2, int var3) {
      String var4;
      if (var1 != 255 && var2 != 255 && var3 != 255) {
         var4 = this.mDecimalFormat.format((long)var1) + ":" + this.mDecimalFormat.format((long)var2) + ":" + this.mDecimalFormat.format((long)var3);
      } else {
         var4 = "--";
      }

      return var4;
   }

   private String resolverAirTemperature(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 31) {
         return "HI";
      } else {
         return 1 <= var1 && var1 <= 29 ? (double)((float)(var1 + 36) * 0.5F) - 0.5 + this.getTempUnitC(this.mContext) : "";
      }
   }

   private String resolverOutdoorTemperature(Context var1) {
      double var2 = (double)this.mCanBusInfoInt[7];
      return (var2 - 80.0) * 0.5 + this.getTempUnitC(var1);
   }

   private void set0x16CarSpeedInfo() {
      ArrayList var4 = new ArrayList();
      int[] var5 = this.mCanBusInfoInt;
      int var3 = var5[3];
      double var1 = (double)(var5[2] | var3 << 8);
      var4.add(new DriverUpdateEntity(0, 0, (new DecimalFormat("0.0")).format(var1) + "KM/H"));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var6[3], var6[2]));
   }

   private void set0x1dRearRadarData(Context var1) {
      if (this.is0x1dDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x1eFrontRadarData(Context var1) {
      if (this.is0x1eDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x20WheelKey(Context var1) {
      int var2 = this.mKeyStatus;
      int var3 = this.mCanBusInfoInt[2];
      if (var2 != var3) {
         this.mKeyStatus = var3;
      }

      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 19) {
                  if (var3 != 20) {
                     switch (var3) {
                        case 7:
                           this.realKeyLongClick1(var1, 2);
                           break;
                        case 8:
                           this.realKeyLongClick1(var1, 187);
                           break;
                        case 9:
                           this.realKeyLongClick1(var1, 14);
                           break;
                        case 10:
                           this.realKeyLongClick1(var1, 15);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 46);
                  }
               } else {
                  this.realKeyLongClick1(var1, 45);
               }
            } else {
               this.realKeyLongClick1(var1, 8);
            }
         } else {
            this.realKeyLongClick1(var1, 7);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private void set0x21OriginalCarUsb() {
      DecimalFormat var5 = new DecimalFormat("00");
      GeneralOriginalCarDeviceData.cdStatus = this.getCdStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
      GeneralOriginalCarDeviceData.runningState = this.getRunStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
      GeneralOriginalCarDeviceData.progress = this.mCanBusInfoInt[11];
      GeneralOriginalCarDeviceData.startTime = var5.format((long)this.mCanBusInfoInt[4]) + ":" + var5.format((long)this.mCanBusInfoInt[5]);
      GeneralOriginalCarDeviceData.endTime = "";
      String var7 = this.mCanBusInfoInt[4] + ":" + this.mCanBusInfoInt[5];
      int[] var6 = this.mCanBusInfoInt;
      int var4 = var6[6];
      int var2 = var6[7];
      int var1 = var6[8];
      int var3 = var6[9];
      ArrayList var8 = new ArrayList();
      var8.add(new OriginalCarDeviceUpdateEntity(0, var7));
      var8.add(new OriginalCarDeviceUpdateEntity(1, (var4 << 8 | var2) + ""));
      var8.add(new OriginalCarDeviceUpdateEntity(2, (var3 | var1 << 8) + ""));
      GeneralOriginalCarDeviceData.mList = var8;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void set0x24CarBaseInfo(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      if (this.isDoorChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x28AirData(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      var2[3] = (byte)(var2[3] & 239);
      if (this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolverAirTemperature(this.mCanBusInfoByte[4]);
         GeneralAirData.front_right_temperature = this.resolverAirTemperature(this.mCanBusInfoByte[5]);
         GeneralAirData.front_right_auto_wind = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_auto_wind = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         this.updateOutDoorTemp(var1, this.resolverOutdoorTemperature(var1));
         this.updateAirActivity(var1, 1001);
      }

   }

   private void set0x29TrackData(Context var1) {
      if (this.isTrackDataChange()) {
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[2], (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4), 0, 380, 12);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x30VersionData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x31Amplifier(Context var1) {
      byte[] var9 = this.mCanBusInfoByte;
      this.m0x31Command = Arrays.copyOf(var9, var9.length);
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
         int var4 = this.mCanBusInfoInt[2];
         byte var2 = 0;
         int var6 = DataHandleUtils.getIntFromByteWithBit(var4, 0, 4);
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
         int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
         int var8 = this.mCanBusInfoInt[5];
         GeneralAmplifierData.bandBass = var5 - 2;
         GeneralAmplifierData.bandMiddle = var4 - 2;
         GeneralAmplifierData.bandTreble = var7 - 2;
         GeneralAmplifierData.volume = var8;
         GeneralAmplifierData.frontRear = 7 - var3;
         GeneralAmplifierData.leftRight = var6 - 7;
         this.updateAmplifierActivity((Bundle)null);
         this.saveAmplifierData(var1, this.mCanId);
         ArrayList var11 = new ArrayList();
         SettingUpdateEntity var10 = this.getSettingUpdateEntity("_323_amplifier_setting_1");
         if (this.mCanBusInfoInt[4] == 8) {
            var2 = 1;
         }

         var11.add(var10.setValue(Integer.valueOf(var2)));
         var11.add(this.getSettingUpdateEntity("_323_amplifier_setting_2").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]))));
         var11.add(this.getSettingUpdateEntity("_323_amplifier_setting_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2)));
         this.updateGeneralSettingData(var11);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x32SystemData() {
      ArrayList var1 = new ArrayList();
      var1.add(this.getSettingUpdateEntity("_323_amplifier_setting_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      var1.add(this.getSettingUpdateEntity("_323_amplifier_setting_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x50RotationInfo() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[3];
      int var2 = var3[2];
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 1, (var2 | var1 << 8) + " rpm"));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x60WheelKey() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 8)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x61CdStatus() {
      this.mCurrentClick = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      this.mCurrentStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      String var2 = this.disStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
      String var5 = this.disStatus(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
      String var1 = this.disStatus(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
      String var4 = this.disStatus(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
      String var7 = this.disStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
      String var6 = this.disStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
      ArrayList var3 = new ArrayList();
      var3.add(new SongListEntity("1. " + var2));
      var3.add(new SongListEntity("2. " + var5));
      var3.add(new SongListEntity("3. " + var1));
      var3.add(new SongListEntity("4. " + var4));
      var3.add(new SongListEntity("5. " + var7));
      var3.add(new SongListEntity("6. " + var6));
      GeneralOriginalCarDeviceData.runningState = this.getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4));
      this._0x61runningStatus = GeneralOriginalCarDeviceData.runningState;
      GeneralOriginalCarDeviceData.songList = var3;
      this.updateOriginalCarDeviceActivity((Bundle)null);
      ArrayList var8 = new ArrayList();
      var8.add(new OriginalCarDeviceUpdateEntity(5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + ""));
      this.mList1.clear();
      this.mList1.addAll(var8);
      GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1);
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void set0x62DiscInfo() {
      GeneralOriginalCarDeviceData.cdStatus = this.getDeviceWorkMode(this.mCanBusInfoInt[2]);
      if (!GeneralOriginalCarDeviceData.cdStatus.equals(this.mDeviceStatusNow)) {
         this.mDeviceStatusNow = GeneralOriginalCarDeviceData.cdStatus;
         this.cleanDevice();
         this.cleanSongList();
         this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      }

      if (this.isDeviceStatusSame("CD")) {
         if (!this.isOriginalCd && this.mCurrentClick != 0) {
            if (this.isOriginalCdChange()) {
               this.openAuxIn();
            }

            Log.i("cbc", "set0x62DiscInfo2: " + this.mCurrentClick);
         } else {
            this.setOriginalCdPage();
            this.setCdInfo();
            Log.i("cbc", "set0x62DiscInfo: " + this.mCurrentClick);
         }
      }

      if (this.isDeviceStatusSame("Radio")) {
         this.setOriginalRadioPage();
         this.setRadioInfo();
      }

      if (!"Radio".equals(GeneralOriginalCarDeviceData.cdStatus) && !"CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
         this.setOriginalOtherPage();
      }

   }

   private void set0x63VehicleInfo() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 32) {
         if (var1 != 33) {
            if (var1 != 48) {
               if (var1 != 49) {
                  if (var1 != 80) {
                     if (var1 == 81) {
                        var2.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(2131762481)));
                     }
                  } else {
                     var2.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(2131762480)));
                  }
               } else {
                  var2.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(2131762479)));
               }
            } else {
               var2.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(2131762478)));
            }
         } else {
            var2.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(2131762477)));
         }
      } else {
         var2.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(2131762476)));
      }

      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x64PanelKey() {
      int var1 = this.mPanel;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.mPanel = var2;
      }

      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 32) {
                        this.realKeyLongClick1(this.mContext, 4);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 130);
                  }
               } else {
                  this.changeBandFm2();
               }
            } else {
               this.changeBandFm1();
            }
         } else {
            this.changeBandAm1();
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void set0x65RockerData(Context var1) {
      int var3 = this.mRockKey;
      int var2 = this.mCanBusInfoInt[2];
      if (var3 != var2) {
         this.mRockKey = var2;
      }

      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 3) {
               if (var2 != 5) {
                  if (var2 != 7) {
                     switch (var2) {
                        case 16:
                           this.realKeyLongClick1(var1, 7);
                           break;
                        case 17:
                           this.realKeyLongClick1(var1, 8);
                           break;
                        case 18:
                           this.realKeyLongClick1(var1, 49);
                           break;
                        case 19:
                           this.realKeyLongClick1(var1, 50);
                           break;
                        case 20:
                           this.realKeyLongClick1(var1, 52);
                           break;
                        case 21:
                           this.realKeyLongClick1(var1, 59);
                           break;
                        case 22:
                           this.realKeyLongClick1(var1, 128);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 47);
                  }
               } else {
                  this.realKeyLongClick1(var1, 46);
               }
            } else {
               this.realKeyLongClick1(var1, 48);
            }
         } else {
            this.realKeyLongClick1(var1, 45);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private String setBand(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 16) {
               var2 = "";
            } else {
               var2 = "AM";
            }
         } else {
            var2 = "FM2";
         }
      } else {
         var2 = "FM1";
      }

      return var2;
   }

   private String setByteHighLow(int var1, int var2) {
      var1 = var1 * 256 + var2;
      String var3;
      if (var1 == 255) {
         var3 = "--";
      } else {
         var3 = var1 + "";
      }

      return var3;
   }

   private void setCdInfo() {
      ArrayList var4 = new ArrayList();
      this.currentDisc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      var4.add(new OriginalCarDeviceUpdateEntity(0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + ""));
      var4.add(new OriginalCarDeviceUpdateEntity(1, this.setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
      var4.add(new OriginalCarDeviceUpdateEntity(2, this.setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))));
      int[] var5 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(3, this.setByteHighLow(var5[7], var5[6])));
      var5 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(4, this.setByteHighLow(var5[9], var5[8])));
      var5 = this.mCanBusInfoInt;
      GeneralOriginalCarDeviceData.endTime = this.resolveTime(var5[10], var5[11], var5[12]);
      var5 = this.mCanBusInfoInt;
      int var2 = var5[13];
      int var1 = var5[14];
      GeneralOriginalCarDeviceData.startTime = this.resolveTime(var2, var1, var1);
      var5 = this.mCanBusInfoInt;
      int var3 = var5[10];
      var2 = var5[11];
      var1 = var5[12];
      if (var3 * 3600 + var2 * 60 + var1 != 0) {
         GeneralOriginalCarDeviceData.progress = (var5[13] * 3600 + var5[14] * 60 + var5[15]) * 100 / (var3 * 3600 + var2 * 60 + var1);
      }

      this.mList2.clear();
      this.mList2.addAll(var4);
      GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private String setCycleStatus(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = this.mContext.getResources().getString(2131761251);
            }
         } else {
            var2 = this.mContext.getResources().getString(2131761250);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131761249);
      }

      return var2;
   }

   private String setFreq(int var1, int var2) {
      String var3;
      if (this.mCanBusInfoInt[4] == 16) {
         var3 = var1 + var2 * 256 + "KHz";
      } else {
         var3 = (float)(var1 + var2 * 256) / 100.0F + "MHz";
      }

      return var3;
   }

   private void setOriginalCdPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_disc", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
      this.changeOriginalDevicePage(var1, new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux", "rpt", "rdm", "scan"}, new String[]{"prev_disc", "left", "up", "down", "right", "next_disc"}, true, true);
   }

   private void setOriginalOtherPage() {
      GeneralOriginalCarDeviceData.mList = null;
      this.changeOriginalDevicePage(new ArrayList(), new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux"}, new String[0], false, false);
   }

   private void setOriginalRadioPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
      this.changeOriginalDevicePage(var1, new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux"}, new String[]{"up", "left", "radio_scan", "right", "down"}, true, false);
   }

   private String setPlayStatus(int var1) {
      Resources var2;
      if (var1 == 1) {
         var2 = this.mContext.getResources();
         var1 = 2131761248;
      } else {
         var2 = this.mContext.getResources();
         var1 = 2131761247;
      }

      return var2.getString(var1);
   }

   private String setPreset(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "";
      } else {
         var2 = "P" + var1;
      }

      return var2;
   }

   private void setPresetData() {
      ArrayList var4;
      label28: {
         var4 = new ArrayList();
         int var3 = this.mCanBusInfoInt[4];
         int var2 = 2;
         int var1 = var2;
         int[] var5;
         if (var3 != 0) {
            var1 = var2;
            if (var3 != 1) {
               var1 = var2;
               if (var3 != 2) {
                  var1 = var2;
                  switch (var3) {
                     case 16:
                     case 17:
                     case 18:
                        while(var1 < 8) {
                           var5 = this.mCanBusInfoInt;
                           var3 = var1 * 2;
                           var2 = var5[var3 + 2];
                           var3 = var5[var3 + 1];
                           var4.add(new SongListEntity(var1 - 1 + ". " + (var2 * 256 + var3) + "KHz"));
                           ++var1;
                        }
                     default:
                        break label28;
                  }
               }
            }
         }

         while(var1 < 8) {
            var5 = this.mCanBusInfoInt;
            var3 = var1 * 2;
            var2 = var5[var3 + 2];
            var3 = var5[var3 + 1];
            var4.add(new SongListEntity(var1 - 1 + ". " + (float)(var2 * 256 + var3) / 100.0F + "MHz"));
            ++var1;
         }
      }

      GeneralOriginalCarDeviceData.songList = var4;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setRadioInfo() {
      if (this.mCanBusInfoInt[3] == 0) {
         ArrayList var2 = new ArrayList();
         var2.add(new OriginalCarDeviceUpdateEntity(0, this.setBand(this.mCanBusInfoInt[4])));
         var2.add(new OriginalCarDeviceUpdateEntity(1, this.setState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))));
         var2.add(new OriginalCarDeviceUpdateEntity(2, this.setPreset(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
         int[] var1 = this.mCanBusInfoInt;
         var2.add(new OriginalCarDeviceUpdateEntity(3, this.setFreq(var1[6], var1[7])));
         this.currentPtore = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
         String var3;
         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
            var3 = this.mContext.getResources().getString(2131768339);
         } else {
            var3 = this.mContext.getResources().getString(2131768338);
         }

         GeneralOriginalCarDeviceData.runningState = var3;
         GeneralOriginalCarDeviceData.mList = var2;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      } else {
         this.setPresetData();
      }

   }

   private String setState(boolean var1) {
      int var2;
      Resources var3;
      if (var1) {
         var3 = this.mContext.getResources();
         var2 = 2131770004;
      } else {
         var3 = this.mContext.getResources();
         var2 = 2131761252;
      }

      return var3.getString(var2);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mUiMgr = this.getUiMgr(var1);
      this.initSettingsItem(var1);
      this.mDifferentId = this.getCurrentCanDifferentId();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var5 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var5;
      Integer var4 = 1;
      int var3 = var5[1];
      if (var3 != 22) {
         if (var3 != 32) {
            if (var3 != 36) {
               if (var3 != 80) {
                  if (var3 != 29) {
                     if (var3 != 30) {
                        if (var3 != 40) {
                           if (var3 != 41) {
                              switch (var3) {
                                 case 48:
                                    this.set0x30VersionData(var1);
                                    break;
                                 case 49:
                                    this.set0x31Amplifier(var1);
                                    break;
                                 case 50:
                                    this.set0x32SystemData();
                                    break;
                                 default:
                                    switch (var3) {
                                       case 96:
                                          if (this.equal(this.mDifferentId, 18, var4)) {
                                             this.set0x60WheelKey();
                                          }
                                          break;
                                       case 97:
                                          if (this.equal(this.mDifferentId, 18, var4)) {
                                             this.set0x61CdStatus();
                                          }
                                          break;
                                       case 98:
                                          if (this.equal(this.mDifferentId, 18, var4)) {
                                             this.set0x62DiscInfo();
                                          }
                                          break;
                                       case 99:
                                          this.set0x63VehicleInfo();
                                          break;
                                       case 100:
                                          this.set0x64PanelKey();
                                          break;
                                       case 101:
                                          if (this.equal(this.mDifferentId, 18)) {
                                             this.set0x65RockerData(var1);
                                          }
                                    }
                              }
                           } else {
                              this.set0x29TrackData(var1);
                           }
                        } else {
                           this.set0x28AirData(var1);
                        }
                     } else {
                        this.set0x1eFrontRadarData(var1);
                     }
                  } else {
                     this.set0x1dRearRadarData(var1);
                  }
               } else if (this.equal(this.mDifferentId, 5)) {
                  this.set0x50RotationInfo();
               }
            } else {
               this.set0x24CarBaseInfo(var1);
            }
         } else {
            this.set0x20WheelKey(var1);
         }
      } else {
         this.set0x16CarSpeedInfo();
      }

   }

   byte[] get0x31Data() {
      return this.m0x31Command;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mOriginalSet = UiMgrFactory.getCanUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      this.initAmplifierData(var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void turnToRearCdPage() {
      this.cleanDevice();
      this.cleanSongList();
      this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
      this.changeOriginalDevicePage(var1, new String[]{"fm1", "fm2", "am", "cdc", "rear_cdc", "aux", "rpt", "rdm", "scan"}, new String[]{"prev_disc", "left", "up", "down", "right", "next_disc"}, false, true);
   }
}
