package com.hzbhd.canbus.car._123;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
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
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_123_AMPLIFIER_BALANCE = "share_123_amplifier_balance";
   static final String SHARE_123_AMPLIFIER_BASS = "share_123_amplifier_bass";
   static final String SHARE_123_AMPLIFIER_FADE = "share_123_amplifier_fade";
   static final String SHARE_123_AMPLIFIER_MIDDLE = "share_123_amplifier_middle";
   static final String SHARE_123_AMPLIFIER_TREBLE = "share_123_amplifier_treble";
   static final String SHARE_123_AMPLIFIER_VOLUME = "share_123_amplifier_volume";
   static final String SHARE_123_IS_RADAR_ENABLE = "share_123_is_radar_enable";
   static final String SHARE_123_OUTDOOR_TEMPERATURE_UNIT = "share_123_outdoor_temperature_unit";
   static final int _123_AMPLIFIER_OFFSET = 9;
   private static boolean isAirFirst;
   private static int tuneKnobValue;
   private static int volKnobValue;
   private int FreqInt;
   private byte freqHi;
   private byte freqLo;
   private int[] m0x2EData;
   private int[] m0x32Data;
   private int[] m0x41Data;
   private int[] m0x43Data;
   private int[] m0x60Data;
   private int[] m0x62Data;
   private int[] m0x9CData;
   private int[] m0xA5Data;
   private int[] m0xA6Data;
   private int[] m0xAEData;
   private int[] m0xC1Data;
   private int[] m0xF0Data;
   int[] mAirData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCompassDeviation = 1;
   private boolean mCompassDeviationEnable = true;
   private Context mContext;
   private HashMap mDriveItemHashMap;
   private boolean mIsBackOpen;
   private boolean mIsBackOpenNow;
   private boolean mIsBeltTie;
   private boolean mIsBeltTieNow;
   private boolean mIsFrontLeftOpen;
   private boolean mIsFrontLeftOpenNow;
   private boolean mIsFrontRightOpen;
   private boolean mIsFrontRightOpenNow;
   private boolean mIsRearLeftOpen;
   private boolean mIsRearLeftOpenNow;
   private boolean mIsRearRightOpen;
   private boolean mIsRearRightOpenNow;
   private boolean mIsSubBeltTie;
   private boolean mIsSubBeltTieNow;
   private ParkPageUiSet mParkPageUiSet;
   private int mPlayingIndex = -1;
   private HashMap mSettingItemHashMap;
   private List mSongList;
   private byte[] mTrackDataNow;
   private UiMgr mUiMgr;
   boolean rdm;
   private long realtime;
   boolean rpt;
   private String songAlbumNow = "";
   private String songArtistNow = "";
   private String songTitleNow = "";

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
   }

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var5 = new byte[var4];

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var3 < var2) {
            var5[var3] = var1[var3];
         } else {
            var5[var3] = var1[var3 + 1];
         }
      }

      return var5;
   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
   }

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
   }

   private byte getAllBandTypeData(String var1) {
      if (var1.contains("FM")) {
         return 1;
      } else {
         return (byte)(var1.contains("AM") ? 4 : 1);
      }
   }

   private String getBandUnit(String var1) {
      if (var1.contains("FM")) {
         return " MKz";
      } else {
         return var1.contains("AM") ? " KKz" : " MKz";
      }
   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private String getRunStatus(int var1) {
      String var2 = "_123_divice_status_" + var1;
      return CommUtil.getStrByResId(this.mContext, var2);
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private String getServiceStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "hiworld_jeep_123_item_38";
      } else {
         var2 = "hiworld_jeep_123_item_39";
      }

      return var2;
   }

   private List getSongList() {
      if (this.mSongList == null) {
         this.mSongList = new ArrayList();
      }

      return this.mSongList;
   }

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getVehicleSeries(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getString(2131769421);
      } else if (var1 == 19) {
         var2 = this.mContext.getString(2131770267);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String getVehicleType(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var2 = "";
               } else {
                  var2 = this.mContext.getString(2131770274);
               }
            } else {
               var2 = this.mContext.getString(2131770275);
            }
         } else {
            var2 = this.mContext.getString(2131770273);
         }
      } else {
         var2 = this.mContext.getString(2131769421);
      }

      return var2;
   }

   private ParkPageUiSet getmParkPageUiSet() {
      if (this.mParkPageUiSet == null) {
         this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getParkPageUiSet(this.mContext);
      }

      return this.mParkPageUiSet;
   }

   private DriverUpdateEntity helperSetDriveDataValue(String var1, String var2) {
      if (!this.mDriveItemHashMap.containsKey(var1)) {
         this.mDriveItemHashMap.put(var1, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
      }

      return ((DriveDataUpdateHelper)this.mDriveItemHashMap.get(var1)).setValue(var2);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2, boolean var3) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper((new SettingUpdateEntity(-1, -1, "null_value")).setEnable(false)));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2).setEnable(var3);
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_123_amplifier_volume", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_123_amplifier_balance", 0);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_123_amplifier_fade", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_123_amplifier_bass", 0);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_123_amplifier_middle", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_123_amplifier_treble", 0);
      }

      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)GeneralAmplifierData.leftRight});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)GeneralAmplifierData.frontRear});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(GeneralAmplifierData.bandBass - 9)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(GeneralAmplifierData.bandTreble - 9)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(GeneralAmplifierData.bandMiddle - 9)});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private void initDriveItem(DriverDataPageUiSet var1) {
      this.mDriveItemHashMap = new HashMap();
      List var4 = var1.getList();

      for(int var2 = 0; var2 < var4.size(); ++var2) {
         List var5 = ((DriverDataPageUiSet.Page)var4.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var6 = ((DriverDataPageUiSet.Page.Item)var5.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var6, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
         }
      }

   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemHashMap = new HashMap();
      List var5 = var1.getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var7 = ((SettingPageUiSet.ListBean)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var7.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var7.get(var3);
            String var4 = var6.getTitleSrn();
            this.mSettingItemHashMap.put(var4, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var6.getMin()));
         }
      }

   }

   private boolean is0x2EDataChange() {
      if (Arrays.equals(this.m0x2EData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x2EData = Arrays.copyOf(var1, var1.length);
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

   private boolean is0x41DataChange() {
      if (Arrays.equals(this.m0x41Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x41Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x43DataChange() {
      if (Arrays.equals(this.m0x43Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x43Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x60DataChange() {
      if (Arrays.equals(this.m0x60Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x60Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x62DataChange() {
      if (Arrays.equals(this.m0x62Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x62Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x9CDataChange() {
      if (Arrays.equals(this.m0x9CData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x9CData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xA5DataChange() {
      if (Arrays.equals(this.m0xA5Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xA5Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xA6DataChange() {
      if (Arrays.equals(this.m0xA6Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xA6Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xAEDataChange() {
      if (Arrays.equals(this.m0xAEData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xAEData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xC1DataChange() {
      if (Arrays.equals(this.m0xC1Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xC1Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0xF0DataChange() {
      if (Arrays.equals(this.m0xF0Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0xF0Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isDoorDataChange() {
      boolean var1 = this.mIsFrontLeftOpen;
      if (var1 == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsBeltTie == this.mIsBeltTieNow && this.mIsSubBeltTie == this.mIsSubBeltTieNow) {
         return false;
      } else {
         this.mIsFrontLeftOpenNow = var1;
         this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
         this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
         this.mIsRearRightOpenNow = this.mIsRearRightOpen;
         this.mIsBackOpenNow = this.mIsBackOpen;
         this.mIsBeltTieNow = this.mIsBeltTie;
         this.mIsSubBeltTieNow = this.mIsSubBeltTie;
         return true;
      }
   }

   private boolean isFirst() {
      if (isAirFirst) {
         isAirFirst = false;
         if (!GeneralAirData.power) {
            return true;
         }
      }

      return false;
   }

   private boolean isTrackDataChange(byte[] var1) {
      if (Arrays.equals(this.mTrackDataNow, var1)) {
         return false;
      } else {
         this.mTrackDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void panelKey0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 9) {
                  if (var1 != 49) {
                     if (var1 == 50) {
                        this.realKeyShortClick(188);
                     }
                  } else {
                     this.realKeyShortClick(152);
                  }
               } else {
                  this.realKeyShortClick(3);
               }
            } else {
               this.realKeyShortClick(50);
            }
         } else {
            this.realKeyShortClick(134);
         }
      } else {
         this.realKeyShortClick(0);
      }

   }

   private void panelKnob0x22() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1) {
         if (var1 == 2) {
            var1 = tuneKnobValue - this.mCanBusInfoByte[3];
            if (var1 < 0) {
               this.sendKnob(46, Math.abs(var1));
            } else if (var1 > 0) {
               this.sendKnob(45, Math.abs(var1));
            }

            tuneKnobValue = this.mCanBusInfoByte[3];
         }
      } else {
         var1 = volKnobValue - this.mCanBusInfoByte[3];
         if (var1 < 0) {
            this.sendKnob_1(7, Math.abs(var1));
         } else if (var1 > 0) {
            this.sendKnob_1(8, Math.abs(var1));
         }

         volKnobValue = this.mCanBusInfoByte[3];
      }

   }

   private void realKeyControl0x11() {
      this.getmParkPageUiSet();
      ParkPageUiSet var2 = this.mParkPageUiSet;
      if (var2 != null) {
         var2.setShowRadar(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
      }

      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 8) {
         if (var1 != 9) {
            if (var1 != 12) {
               if (var1 != 24) {
                  switch (var1) {
                     case 0:
                        this.realKeyLongClick1(0);
                        break;
                     case 1:
                        this.realKeyLongClick1(7);
                        break;
                     case 2:
                        this.realKeyLongClick1(8);
                        break;
                     case 3:
                        this.realKeyLongClick1(3);
                        break;
                     case 4:
                        this.realKeyLongClick1(187);
                        break;
                     case 5:
                        this.realKeyLongClick1(14);
                        break;
                     case 6:
                        this.realKeyLongClick1(15);
                  }
               } else {
                  this.realKeyLongClick1(33);
               }
            } else {
               this.realKeyLongClick1(2);
            }
         } else {
            this.realKeyLongClick1(48);
         }
      } else {
         this.realKeyLongClick1(47);
      }

      byte[] var3 = this.mCanBusInfoByte;
      if (this.isTrackDataChange(new byte[]{var3[8], var3[9]})) {
         var3 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var3[9], var3[8], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void realKeyLongClick1(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyShortClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveDriveData(int var1, int var2, String var3) {
      String var4 = "---" + var3;
      var1 = var1 * 256 + var2;
      if (var1 != 65535) {
         var4 = var1 + var3;
      }

      return var4;
   }

   private String resolveLeftAndRightTemp(int var1) {
      boolean var2 = GeneralAirData.fahrenheit_celsius;
      String var3 = "HI";
      if (var2) {
         if (254 != var1) {
            if (255 != var1) {
               var3 = (new DecimalFormat("0.0")).format((double)((float)var1 * 0.5F * 9.0F / 5.0F + 32.0F)) + this.getTempUnitF(this.mContext);
            }

            return var3;
         }
      } else if (254 != var1) {
         if (255 != var1) {
            var3 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
         }

         return var3;
      }

      var3 = "LO";
      return var3;
   }

   private String resolveOutDoorTem() {
      GeneralAirData.fahrenheit_celsius = SharePreUtil.getBoolValue(this.mContext, "share_123_outdoor_temperature_unit", false);
      int var1 = this.mCanBusInfoInt[13];
      String var2;
      if (GeneralAirData.fahrenheit_celsius) {
         var2 = (new DecimalFormat("0.0")).format((double)(((float)var1 * 0.5F - 40.0F) * 9.0F / 5.0F + 32.0F)) + this.getTempUnitF(this.mContext);
      } else {
         var2 = (float)var1 * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void saveAmplifierData() {
      SharePreUtil.setIntValue(this.mContext, "share_123_amplifier_volume", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "share_123_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_123_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_123_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_123_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_123_amplifier_middle", GeneralAmplifierData.bandMiddle);
   }

   private void sendKnob(int var1, int var2) {
      this.realKeyClick3(this.mContext, var1, 0, var2);
   }

   private void sendKnob_1(int var1, int var2) {
      this.realKeyClick3_1(this.mContext, var1, 0, var2);
   }

   private void set0x31RearMirror() {
      ArrayList var1 = new ArrayList();
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x62_title"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x62_title", "_123_rear_mirror"), "ON")).setValueStr(true));
      } else {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x62_title"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x62_title", "_123_rear_mirror"), "OFF")).setValueStr(true));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAirData0x31() {
      int[] var5 = this.mCanBusInfoInt;
      var5[3] = this.blockBit(var5[3], 0);
      this.setOutDoorTem();
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      if (!this.isAirDataNoChange()) {
         if (!this.isFirst()) {
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            int var1 = var2;
            if (var2 > 2) {
               var1 = 2;
            }

            var2 = var3;
            if (var3 > 2) {
               var2 = 2;
            }

            GeneralAirData.front_right_seat_heat_level = var1;
            GeneralAirData.front_left_seat_heat_level = var2;
            this.cleanAllBlow();
            var1 = this.mCanBusInfoInt[6];
            if (var1 != 1) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 11) {
                           if (var1 == 12) {
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_right_blow_foot = true;
                           }
                        } else {
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
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
               GeneralAirData.front_auto_wind_model = true;
            }

            boolean var4;
            if (this.mCanBusInfoInt[7] == 19) {
               var4 = true;
            } else {
               var4 = false;
            }

            GeneralAirData.front_auto_wind_speed = var4;
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
            var1 = this.mCanBusInfoInt[10];
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        GeneralAirData.rear_right_blow_foot = true;
                        GeneralAirData.rear_right_blow_head = true;
                        GeneralAirData.rear_left_blow_foot = true;
                        GeneralAirData.rear_left_blow_head = true;
                     }
                  } else {
                     GeneralAirData.rear_right_blow_foot = true;
                     GeneralAirData.rear_right_blow_head = true;
                     GeneralAirData.rear_left_blow_foot = false;
                     GeneralAirData.rear_left_blow_head = false;
                  }
               } else {
                  GeneralAirData.rear_left_blow_head = false;
                  GeneralAirData.rear_right_blow_head = false;
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_foot = true;
               }
            } else {
               GeneralAirData.rear_right_blow_foot = false;
               GeneralAirData.rear_right_blow_head = false;
               GeneralAirData.rear_left_blow_foot = false;
               GeneralAirData.rear_left_blow_head = false;
            }

            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
            GeneralAirData.rear_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[12]);
            this.updateAirActivity(this.mContext, 1004);
         }
      }
   }

   private void setAmplifierData0xA6() {
      if (this.is0xA6DataChange()) {
         GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
         GeneralAmplifierData.leftRight = this.mCanBusInfoByte[3];
         GeneralAmplifierData.frontRear = this.mCanBusInfoByte[4];
         GeneralAmplifierData.bandBass = this.mCanBusInfoByte[5] + 9;
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoByte[6] + 9;
         GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[7] + 9;
         this.updateAmplifierActivity((Bundle)null);
         this.saveAmplifierData();
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(6, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2)));
         var1.add(new SettingUpdateEntity(6, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setCarSetting0x43() {
      if (this.is0x43DataChange()) {
         for(int var1 = 0; var1 < GeneralSettingData.dataList.size(); ++var1) {
            if (((SettingUpdateEntity)GeneralSettingData.dataList.get(var1)).getLeftListIndex() == 5) {
               if (((SettingUpdateEntity)GeneralSettingData.dataList.get(var1)).getRightListIndex() == 0) {
                  ((SettingUpdateEntity)GeneralSettingData.dataList.get(var1)).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
               } else if (((SettingUpdateEntity)GeneralSettingData.dataList.get(var1)).getRightListIndex() == 1) {
                  ((SettingUpdateEntity)GeneralSettingData.dataList.get(var1)).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
               } else if (((SettingUpdateEntity)GeneralSettingData.dataList.get(var1)).getRightListIndex() == 2) {
                  ((SettingUpdateEntity)GeneralSettingData.dataList.get(var1)).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
               }
            }
         }

         this.mCompassDeviationEnable = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         ArrayList var2 = new ArrayList();
         var2.add((new SettingUpdateEntity(5, 0, SharePreUtil.getIntValue(this.mContext, "share_123_compass_direct", 0))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(5, 1, this.mCompassDeviation)).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(5, 2, 0)).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])));
         var2.add((new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
         var2.add((new SettingUpdateEntity(0, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])));
         var2.add((new SettingUpdateEntity(0, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])));
         var2.add((new SettingUpdateEntity(0, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])));
         var2.add((new SettingUpdateEntity(0, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])));
         var2.add((new SettingUpdateEntity(0, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
         var2.add((new SettingUpdateEntity(0, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])));
         var2.add((new SettingUpdateEntity(0, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])));
         var2.add((new SettingUpdateEntity(0, 13, this.getServiceStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])));
         var2.add((new SettingUpdateEntity(0, 14, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])));
         var2.add((new SettingUpdateEntity(0, 15, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])));
         var2.add((new SettingUpdateEntity(0, 16, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
         var2.add((new SettingUpdateEntity(0, 17, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setCarSetting0x45() {
      ArrayList var15 = new ArrayList();
      boolean var9 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      boolean var12 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var8 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      boolean var13 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var14 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      boolean var10 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var11 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1);
      var15.add(this.checkEntity(this.helperSetValue("hiworld_jeep_123_item_46", var3, Boolean.valueOf(var9))));
      var15.add(this.checkEntity(this.helperSetValue("hiworld_jeep_123_item_47", var6, Boolean.valueOf(var12))));
      var15.add(this.checkEntity(this.helperSetValue("hiworld_jeep_123_item_48", var1, Boolean.valueOf(var8))));
      var15.add(this.checkEntity(this.helperSetValue("hiworld_jeep_123_item_49", var5, Boolean.valueOf(var13))));
      var15.add(this.checkEntity(this.helperSetValue("hiworld_jeep_123_item_50", var2, Boolean.valueOf(var14))));
      var15.add(this.checkEntity(this.helperSetValue("hiworld_jeep_123_item_51", var7, Boolean.valueOf(var10))));
      var15.add(this.checkEntity(this.helperSetValue("hiworld_jeep_123_item_52", var4, Boolean.valueOf(var11))));
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralSettingData(var15);
   }

   private void setCarSetting0x60() {
      if (this.is0x60DataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(2, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(2, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setCarSetting0x62() {
      if (this.is0x62DataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 1, DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) - 1, 0, 2))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 3)).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])));
         var1.add((new SettingUpdateEntity(1, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])));
         var1.add((new SettingUpdateEntity(1, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
         var1.add((new SettingUpdateEntity(1, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])));
         var1.add((new SettingUpdateEntity(1, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
         var1.add((new SettingUpdateEntity(1, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setCarSetting0xC1() {
      if (this.is0xC1DataChange()) {
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)));
         var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.rangeNumber(this.mCanBusInfoInt[3] - 1, 0, 1)));
         var2.add(new SettingUpdateEntity(3, 2, this.mCanBusInfoInt[4] - 1));
         var2.add(new SettingUpdateEntity(3, 3, this.mCanBusInfoInt[5] - 1));
         var2.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 1));
         int var1 = this.mCanBusInfoInt[3];
         if (var1 == 1) {
            SharePreUtil.setBoolValue(this.mContext, "share_123_outdoor_temperature_unit", false);
         } else if (var1 == 2) {
            SharePreUtil.setBoolValue(this.mContext, "share_123_outdoor_temperature_unit", true);
         }

         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setCompass0x9C() {
      if (this.is0x9CDataChange()) {
         ArrayList var1 = new ArrayList();
         this.mCompassDeviation = this.mCanBusInfoInt[3];
         var1.add((new SettingUpdateEntity(5, 1, this.mCompassDeviation)).setProgress(this.mCompassDeviation - 1).setEnable(this.mCompassDeviationEnable));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setDriveData0x32() {
      if (this.is0x32DataChange()) {
         ArrayList var1 = new ArrayList();
         int[] var2 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 0, this.resolveDriveData(var2[4], var2[5], " r/min")));
         var2 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 1, this.resolveDriveData(var2[6], var2[7], " km/h")));
         var1.add(new DriverUpdateEntity(0, 2, (float)this.mCanBusInfoInt[8] / 10.0F + "V"));
         var1.add(new DriverUpdateEntity(0, 3, this.mCanBusInfoInt[10] - 40 + this.getTempUnitC(this.mContext)));
         var1.add(new DriverUpdateEntity(0, 4, this.mCanBusInfoInt[11] - 40 + this.getTempUnitC(this.mContext)));
         StringBuilder var5 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 5, var5.append((float)((var3[12] << 8) + var3[13]) / 10.0F).append("").toString()));
         var1.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[14] - 40 + this.getTempUnitC(this.mContext)));
         var1.add(new DriverUpdateEntity(0, 7, this.mCanBusInfoInt[15] - 40 + this.getTempUnitC(this.mContext)));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
         int[] var4 = this.mCanBusInfoInt;
         this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[6], var4[7]));
      }

   }

   private void setOriginalDeviceData0xAE() {
      if (this.is0xAEDataChange()) {
         DecimalFormat var3 = new DecimalFormat("00");
         GeneralOriginalCarDeviceData.cdStatus = "CD";
         GeneralOriginalCarDeviceData.runningState = this.getRunStatus(this.mCanBusInfoInt[5]);
         if (this.mCanBusInfoInt[5] == 4 && GeneralOriginalCarDeviceData.songList != null) {
            GeneralOriginalCarDeviceData.songList.clear();
         }

         GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         this.rpt = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         this.rdm = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         int[] var4 = this.mCanBusInfoInt;
         int var1 = var4[13] * 256 + var4[14];
         GeneralOriginalCarDeviceData.startTime = var3.format((long)this.getMinute(var1)) + ":" + var3.format((long)this.getSecond(var1));
         var4 = this.mCanBusInfoInt;
         int var2 = var4[11] * 256 + var4[12];
         GeneralOriginalCarDeviceData.endTime = var3.format((long)this.getMinute(var2)) + ":" + var3.format((long)this.getSecond(var2));
         if (var2 == 0) {
            GeneralOriginalCarDeviceData.progress = 0;
         } else {
            GeneralOriginalCarDeviceData.progress = var1 * 100 / var2;
         }

         ArrayList var6 = new ArrayList();
         StringBuilder var7 = new StringBuilder();
         int[] var5 = this.mCanBusInfoInt;
         var6.add(new OriginalCarDeviceUpdateEntity(0, var7.append(var5[7] * 256 + var5[8]).append("").toString()));
         StringBuilder var8 = new StringBuilder();
         var4 = this.mCanBusInfoInt;
         var6.add(new OriginalCarDeviceUpdateEntity(1, var8.append(var4[9] * 256 + var4[10]).append("").toString()));
         GeneralOriginalCarDeviceData.mList = var6;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setOriginalDeviceInfo0xA5() {
      if (this.is0xA5DataChange()) {
         this.getSongList();

         Exception var10000;
         label83: {
            int[] var3;
            boolean var10001;
            try {
               var3 = this.mCanBusInfoInt;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               break label83;
            }

            int var1 = var3[2];
            boolean var2 = true;
            String var14;
            if (var1 != 1 && var1 != 2 && var1 != 3) {
               if (var1 == 4) {
                  var1 = var3[3] * 256 + var3[4];

                  try {
                     byte[] var16 = this.mCanBusInfoByte;
                     var14 = new String(var16, 5, var16.length - 5, "UNICODE");
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                     break label83;
                  }

                  try {
                     this.mSongList.remove(var1);
                  } catch (Exception var10) {
                     Exception var17 = var10;

                     try {
                        var17.printStackTrace();
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label83;
                     }
                  }

                  List var18;
                  SongListEntity var20;
                  label61: {
                     try {
                        var18 = this.mSongList;
                        var20 = new SongListEntity(var14);
                        if (var1 == this.mPlayingIndex) {
                           break label61;
                        }
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label83;
                     }

                     var2 = false;
                  }

                  try {
                     var18.add(var1, var20.setSelected(var2));
                     GeneralOriginalCarDeviceData.songList = this.mSongList;
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label83;
                  }
               }
            } else {
               try {
                  this.songListSetSelected();
                  ArrayList var4 = new ArrayList();
                  byte[] var5 = this.mCanBusInfoByte;
                  var14 = new String(var5, 5, var5.length - 5, "UNICODE");
                  OriginalCarDeviceUpdateEntity var19 = new OriginalCarDeviceUpdateEntity(this.mCanBusInfoInt[2] + 1, var14);
                  var4.add(var19);
                  GeneralOriginalCarDeviceData.mList = var4;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label83;
               }
            }

            try {
               this.updateOriginalCarDeviceActivity((Bundle)null);
               return;
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
            }
         }

         Exception var15 = var10000;
         var15.printStackTrace();
      }

   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setRadar0x41() {
      if (this.is0x41DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationDataType2(2, var1[2], 6, var1[3], 6, var1[4], 2, var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationDataType2(2, var1[6], 4, var1[7], 4, var1[8], 2, var1[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSRTinfo0x34() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 0, var2.append((var3[2] << 8) + var3[3]).append("NM").toString()));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 1, var5.append((var4[4] << 8) + var4[5]).append("KW").toString()));
      var1.add(new DriverUpdateEntity(1, 2, (float)this.mCanBusInfoInt[13] / 10.0F + ""));
      var1.add(new DriverUpdateEntity(1, 3, (float)this.mCanBusInfoInt[14] / 10.0F + ""));
      var1.add(new DriverUpdateEntity(1, 4, (float)this.mCanBusInfoInt[15] / 10.0F + ""));
      var1.add(new DriverUpdateEntity(1, 5, (float)this.mCanBusInfoInt[16] / 10.0F + ""));
      var1.add(new DriverUpdateEntity(1, 6, (float)this.mCanBusInfoInt[21] / 10.0F + ""));
      var1.add(new DriverUpdateEntity(1, 7, (float)this.mCanBusInfoInt[22] / 10.0F + ""));
      var1.add(new DriverUpdateEntity(1, 8, (float)this.mCanBusInfoInt[23] / 10.0F + ""));
      var1.add(new DriverUpdateEntity(1, 9, (float)this.mCanBusInfoInt[24] / 10.0F + ""));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVehicleDoorInfo0x12() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      this.mIsFrontLeftOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      this.mIsFrontRightOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      this.mIsRearLeftOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      this.mIsRearRightOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      this.mIsBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.mIsBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      this.mIsSubBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setVehicleType0x2E() {
      if (this.is0x2EDataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add((new SettingUpdateEntity(7, 0, this.getVehicleSeries(this.mCanBusInfoInt[2]))).setValueStr(true));
         var1.add((new SettingUpdateEntity(7, 1, this.getVehicleType(this.mCanBusInfoInt[3]))).setValueStr(true));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVersionInfo() {
      if (this.is0xF0DataChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void songListSetSelected() {
      int[] var1 = this.mCanBusInfoInt;
      this.mPlayingIndex = var1[3] * 256 + var1[4];
      if (GeneralOriginalCarDeviceData.songList != null) {
         Iterator var2 = GeneralOriginalCarDeviceData.songList.iterator();

         while(var2.hasNext()) {
            ((SongListEntity)var2.next()).setSelected(false);
         }

         ((SongListEntity)GeneralOriginalCarDeviceData.songList.get(this.mPlayingIndex)).setSelected(true);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      String var2 = DataHandleUtils.makeMediaInfoCenteredInBytes(18, "AUX");
      byte[] var1 = new byte[36];

      label13: {
         byte[] var4;
         try {
            var4 = var2.getBytes("unicode");
         } catch (Exception var3) {
            var3.printStackTrace();
            break label13;
         }

         var1 = var4;
      }

      var1 = DataHandleUtils.exceptBOMHead(var1);
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -107, 12}, var1);
      CanbusMsgSender.sendMsg(var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var1);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      String var2 = DataHandleUtils.makeMediaInfoCenteredInBytes(18, "BT MUISC");
      byte[] var1 = new byte[36];

      label13: {
         byte[] var4;
         try {
            var4 = var2.getBytes("unicode");
         } catch (Exception var3) {
            var3.printStackTrace();
            break label13;
         }

         var1 = var4;
      }

      var1 = DataHandleUtils.exceptBOMHead(var1);
      var1 = DataHandleUtils.byteMerger(new byte[]{22, -107, 10}, var1);
      CanbusMsgSender.sendMsg(var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), var1);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      var2 = DataHandleUtils.phoneNum2UnicodeBig(var2);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -107, 10}, var2), 39));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      switch (var3[1]) {
         case 17:
            this.realKeyControl0x11();
            break;
         case 18:
            this.setVehicleDoorInfo0x12();
            break;
         case 33:
            this.panelKey0x21();
            break;
         case 34:
            this.panelKnob0x22();
            break;
         case 46:
            this.setVehicleType0x2E();
            break;
         case 49:
            this.set0x31RearMirror();
            this.setAirData0x31();
            break;
         case 50:
            this.setDriveData0x32();
            break;
         case 52:
            this.setSRTinfo0x34();
            break;
         case 65:
            this.setRadar0x41();
            break;
         case 67:
            this.setCarSetting0x43();
            break;
         case 69:
            this.setCarSetting0x45();
            break;
         case 96:
            this.setCarSetting0x60();
            break;
         case 98:
            this.setCarSetting0x62();
            break;
         case 156:
            this.setCompass0x9C();
            break;
         case 165:
            this.setOriginalDeviceInfo0xA5();
            break;
         case 166:
            this.setAmplifierData0xA6();
            break;
         case 174:
            this.setOriginalDeviceData0xAE();
            break;
         case 193:
            this.setCarSetting0xC1();
            break;
         case 240:
            this.setVersionInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var8, (byte)var6, 0, 0, (byte)var10, (byte)var2, (byte)var3, (byte)var4, (byte)var9});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      DecimalFormat var15 = new DecimalFormat("00");
      var12 = var5 + "/" + var6 + "  " + var15.format((long)this.getHour(var2)) + ":" + var15.format((long)this.getMinute(var2)) + ":" + var15.format((long)this.getSecond(var2));
      byte[] var16 = new byte[]{22, -107, 6};

      label13: {
         byte[] var17;
         try {
            var17 = DataHandleUtils.byteMerger(var16, var12.getBytes("Unicode"));
         } catch (Exception var14) {
            var14.printStackTrace();
            break label13;
         }

         var16 = var17;
      }

      var16 = DataHandleUtils.makeBytesFixedLength(var16, 39);
      CanbusMsgSender.sendMsg(var16);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var16);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(var1);
      this.initSettingsItem(this.getUigMgr(var1).getSettingUiSet(var1));
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var26;
      if (var1 == 9) {
         var26 = new byte[]{22, -107, 14};
      } else {
         var26 = new byte[]{22, -107, 13};
      }

      var12 = (var9 & 255) * 256 + var3 + "/" + var4 + "  " + var12 + ":" + var13;

      label18: {
         byte[] var27;
         try {
            var27 = DataHandleUtils.byteMerger(var26, DataHandleUtils.exceptBOMHead(var12.getBytes("Unicode")));
         } catch (Exception var25) {
            var25.printStackTrace();
            break label18;
         }

         var26 = var27;
      }

      var26 = DataHandleUtils.makeBytesFixedLength(var26, 39);
      CanbusMsgSender.sendMsg(var26);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2);
      var3 = var2 + " " + var3 + this.getBandUnit(var2);
      byte[] var8 = new byte[]{22, -107, var6};

      label13: {
         byte[] var9;
         try {
            var9 = DataHandleUtils.byteMerger(var8, DataHandleUtils.exceptBOMHead(var3.getBytes("Unicode")));
         } catch (Exception var7) {
            var7.printStackTrace();
            break label13;
         }

         var8 = var9;
      }

      var8 = DataHandleUtils.makeBytesFixedLength(var8, 39);
      CanbusMsgSender.sendMsg(var8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var8);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void updateSettingActivity(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var19;
      if (var1 == 9) {
         var19 = new byte[]{22, -107, 14};
      } else {
         var19 = new byte[]{22, -107, 13};
      }

      var11 = (var9 & 255) * 256 + var3 + "/" + var4 + "  " + var11 + ":" + var12 + ":" + var13;

      label18: {
         byte[] var20;
         try {
            var20 = Util.byteMerger(var19, Util.exceptBOMHead(var11.getBytes("Unicode")));
         } catch (UnsupportedEncodingException var18) {
            var18.printStackTrace();
            break label18;
         }

         var19 = var20;
      }

      var19 = Util.makeBytesFixedLength(var19, 39);
      CanbusMsgSender.sendMsg(var19);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var19);
   }

   private static class DriveDataUpdateHelper {
      private DriverUpdateEntity entity;

      public DriveDataUpdateHelper(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity getEntity() {
         return this.entity;
      }

      public void setEntity(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity setValue(String var1) {
         this.entity.setValue(var1);
         return this.entity;
      }
   }

   private static class SettingUpdateHelper {
      private SettingUpdateEntity entity;
      private int progressMin;

      public SettingUpdateHelper(SettingUpdateEntity var1) {
         this(var1, 0);
      }

      public SettingUpdateHelper(SettingUpdateEntity var1, int var2) {
         this.entity = var1;
         this.progressMin = var2;
      }

      public SettingUpdateEntity getEntity() {
         return this.entity;
      }

      public SettingUpdateEntity setEnable(boolean var1) {
         this.entity.setEnable(var1);
         return this.entity;
      }

      public SettingUpdateEntity setValue(Object var1) {
         if (var1 instanceof Integer) {
            SettingUpdateEntity var2 = this.entity;
            Integer var3 = (Integer)var1;
            var2.setValue(var3 + this.progressMin);
            this.entity.setProgress(var3);
         } else {
            this.entity.setValue(var1);
         }

         return this.entity;
      }
   }
}
