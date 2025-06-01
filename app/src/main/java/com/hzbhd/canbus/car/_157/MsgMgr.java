package com.hzbhd.canbus.car._157;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final int AMPLIFIER_FADE_OFFSET = -9;
   static final int VEHICLE_TYPE_1 = 16;
   static final int VEHICLE_TYPE_2 = 32;
   static final int VEHICLE_TYPE_3 = 48;
   static final int VEHICLE_TYPE_4 = 64;
   static final int VEHICLE_TYPE_5 = 80;
   static final int VEHICLE_TYPE_ACCORD_GEN_10 = 35;
   static final int VEHICLE_TYPE_ACCORD_HIGH = 49;
   static final int VEHICLE_TYPE_AVANCIER = 33;
   static final int VEHICLE_TYPE_BREEZE_20 = 36;
   static final int VEHICLE_TYPE_CITY_15_17 = 17;
   static final int VEHICLE_TYPE_CRIDER_13_19 = 18;
   static final int VEHICLE_TYPE_CROSSTOUR = 19;
   static final int VEHICLE_TYPE_CRV_17 = 34;
   private final int AIR_DATA_MAX_LENGTH = 10;
   private final int BACKLIGHT_DATA_MAX = 256;
   private final int BACKLIGHT_SEGMENTS = 5;
   private final int DATA_0 = 2;
   private final int DATA_TYPE = 1;
   Button EXIT;
   final String ORIGINAL_STATUS_AUX = "original_status_aux";
   final String ORIGINAL_STATUS_CD = "original_status_cd";
   final String ORIGINAL_STATUS_NULL = "original_status_null";
   final String ORIGINAL_STATUS_RADIO = "original_status_radio";
   final String ORIGINAL_STATUS_USB = "original_status_usb";
   private final int SEND_GIVEN_MEDIA_MESSAGE = 1;
   private final int SEND_NORMAL_MESSAGE = 2;
   private final String TAG = "_157_MsgMgr";
   TextView content0;
   TextView content1;
   TextView content2;
   TextView content3;
   String contentOne = "No first line information";
   String contentThree = "No third line information";
   String contentTow = "No second line information";
   String contentZero = "Untitled";
   AlertDialog dialog;
   private SongListEntity[] disc;
   private String disc_status_off;
   private String disc_status_on;
   private int[] m0x33DataIndexOne;
   private int[] m0x33DataIndexTwo;
   private byte[] mAirData = new byte[12];
   int[] mAirDataInt;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private List mAmPresetList = new ArrayList();
   private SongListEntity[] mAmStorageArray = new SongListEntity[40];
   private List mAmStorageList = new ArrayList();
   private boolean mBackStatus;
   private int mBacklight;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private SparseArray mCanbusDataArray;
   private String mCharsetName = "utf-8";
   private Context mContext;
   private int mCurrentDisc;
   private DecimalFormat mDecimalFormat00;
   private DecimalFormat mDecimalFormat0p0;
   private int mDifferentId;
   private int mEachId;
   private List mFmPresetList = new ArrayList();
   private SongListEntity[] mFmStorageArray = new SongListEntity[40];
   private List mFmStorageList = new ArrayList();
   private boolean mFrontStatus;
   private boolean mFrontViewBtnStatus;
   private Handler mHandler;
   private boolean mIsFrontCameraOpen;
   private boolean mIsOriginalRadioBandAm;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private ID3[] mMusicId3s;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private String mOriginalStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemHashMap;
   private boolean mTurnSignalStatus;
   private int mTurnSignalStatus2;
   private UiMgr mUiMgr;
   View view;

   private void adjustBrightness() {
      int var2 = MediaShareData.Screen.INSTANCE.getScreenBacklight();
      int var1 = 1;
      ++var2;
      if (var2 != 6) {
         var1 = var2;
      }

      Log.i("_157_MsgMgr", "adjustBrightness: " + var1);
      this.setBacklightLevel(var1);
   }

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

   private void changeOriginalDevicePage(List var1, String[] var2, String[] var3, boolean var4) {
      this.mOriginalCarDevicePageUiSet.setItems(var1);
      this.mOriginalCarDevicePageUiSet.setRowTopBtnAction(var2);
      this.mOriginalCarDevicePageUiSet.setRowBottomBtnAction(var3);
      this.mOriginalCarDevicePageUiSet.setHaveSongList(var4);
      GeneralOriginalCarDeviceData.mList = null;
      this.updateOriginalDeviceWithInit();
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
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

   private int getAirWhat() {
      int[] var2 = new int[6];
      byte[] var3 = this.mAirData;
      var2[0] = var3[2];
      var2[1] = var3[3];
      var2[2] = var3[4];
      var2[3] = var3[5];
      short var1 = var3[6];
      var2[4] = var1 & 240;
      var2[5] = var3[11];
      int[] var4 = new int[]{var1 & 4, var3[8], var3[9], var3[10]};
      if (Arrays.equals(this.mAirFrontDataNow, var2)) {
         if (Arrays.equals(this.mAirRearDataNow, var4)) {
            return 0;
         }

         var1 = 1002;
      } else {
         var1 = 1001;
      }

      this.mAirFrontDataNow = Arrays.copyOf(var2, 6);
      this.mAirRearDataNow = Arrays.copyOf(var4, 4);
      return var1;
   }

   private List getAmList() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.mAmPresetList);
      var1.addAll(this.mAmStorageList);
      return var1;
   }

   private int getBandData(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else if ("FM3".equals(var1)) {
         return 0;
      } else if ("AM1".equals(var1)) {
         return 16;
      } else {
         return "AM2".equals(var1) ? 16 : 0;
      }
   }

   private String getCdStatus(Context var1, int var2) {
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               return var2 != 3 ? "" : CommUtil.getStrByResId(var1, "_157_scan_status");
            } else {
               return CommUtil.getStrByResId(var1, "_288_divice_cd_mode2");
            }
         } else {
            return CommUtil.getStrByResId(var1, "_288_divice_cd_mode5");
         }
      } else {
         return CommUtil.getStrByResId(var1, "_288_divice_cd_mode1");
      }
   }

   private String getData(int[] var1, float var2, String var3) {
      int var6 = 0;
      int var5 = 0;

      int var4;
      for(var4 = 0; var5 < var1.length; ++var5) {
         var4 = (int)((double)var4 + (double)var1[var5] * Math.pow(2.0, (double)(var5 * 8)));
      }

      for(var5 = 1; var6 < var1.length * 8 - 1; ++var6) {
         var5 = (var5 << 1) + 1;
      }

      return var4 == var5 ? "- - -" : this.mDecimalFormat0p0.format((double)((float)var4 * var2)) + var3;
   }

   private String getDistanceUnit(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? "" : " mile";
      } else {
         return " km";
      }
   }

   private List getFmList() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.mFmPresetList);
      var1.addAll(this.mFmStorageList);
      return var1;
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

   private String getFuelUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : " l/100km";
         } else {
            return " km/l";
         }
      } else {
         return " mpg";
      }
   }

   private String getOpenClose(Context var1, boolean var2) {
      String var3;
      if (var2) {
         var3 = "open";
      } else {
         var3 = "close";
      }

      return CommUtil.getStrByResId(var1, var3);
   }

   private int getRange(int var1) {
      switch (var1) {
         case 0:
            return 60;
         case 1:
            return 10;
         case 2:
            return 12;
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
            return (var1 - 1) * 10;
         default:
            return 0;
      }
   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2);
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      ArrayList var3 = new ArrayList();
      var3.add(new byte[]{22, -124, 1, (byte)(GeneralAmplifierData.frontRear + 9)});
      var3.add(new byte[]{22, -124, 2, (byte)(GeneralAmplifierData.leftRight + 9)});
      var3.add(new byte[]{22, -124, 3, (byte)GeneralAmplifierData.bandTreble});
      var3.add(new byte[]{22, -124, 4, (byte)GeneralAmplifierData.bandMiddle});
      var3.add(new byte[]{22, -124, 5, (byte)GeneralAmplifierData.bandBass});
      var3.add(new byte[]{22, -124, 6, (byte)GeneralAmplifierData.megaBass});
      var3.add(new byte[]{22, -124, 9, (byte)GeneralAmplifierData.volume});
      Iterator var4 = var3.iterator();
      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var4, var2) {
         final MsgMgr this$0;
         final Iterator val$iterator;
         final TimerUtil val$timer;

         {
            this.this$0 = var1;
            this.val$iterator = var2;
            this.val$timer = var3;
         }

         public void run() {
            if (this.val$iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.val$iterator.next());
            } else {
               this.val$timer.stopTimer();
            }

         }
      }, 0L, 80L);
   }

   private void initID3() {
      this.mMusicId3s = new ID3[]{new ID3(this, 2), new ID3(this, 3), new ID3(this, 4)};
   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemHashMap = new HashMap();
      List var6 = var1.getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var5.get(var3);
            String var4 = var7.getTitleSrn();
            this.mSettingItemHashMap.put(var4, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var7.getMin()));
         }
      }

   }

   private boolean is0x33IndexOneDataChange() {
      if (Arrays.equals(this.m0x33DataIndexOne, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexOne = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33IndexTwoDataChange() {
      if (Arrays.equals(this.m0x33DataIndexTwo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33DataIndexTwo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirDataInt, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.mAirDataInt = this.mCanBusInfoInt;
         return true;
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

   private boolean isPreCameraOrAuxForeground(Context var1) {
      return SystemUtil.isForeground(var1, Constant.FCameraActivity.getClassName());
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
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
         byte[] var4 = DataHandleUtils.exceptBOMHead(var2.getBytes(this.mCharsetName));
         this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, (byte)var1}, var4), 35), (long)(var1 * 80));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void resolve0x17KeyMenu(Context var1) {
      if (this.equal(this.mDifferentId, 17, 18)) {
         this.realKeyLongClick1(var1, 3);
      } else {
         this.realKeyLongClick1(var1, 52);
      }

   }

   private String resolveAirTemperature(Context var1, int var2) {
      if (var2 == 0) {
         return "LO";
      } else if (var2 == 255) {
         return "HI";
      } else {
         return GeneralAirData.fahrenheit_celsius ? var2 + this.getTempUnitF(var1) : (float)var2 / 2.0F + this.getTempUnitC(var1);
      }
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var5 = new Message();
         var5.what = 1;
         var5.arg1 = var1;
         var5.obj = var2;
         this.mHandler.sendMessageDelayed(var5, var3);
      }
   }

   private void sendNormalMessage(Object var1) {
      this.sendNormalMessage(var1, 0L);
   }

   private void sendNormalMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = new Message();
         var4.what = 2;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void set0x03RadioFreqData() {
      if ("original_status_radio".equals(this.mOriginalStatus) && this.isDataChange(this.mCanBusInfoInt)) {
         GeneralOriginalCarDeviceData.cdStatus = "Radio";
         GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralOriginalCarDeviceData.refresh = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mIsOriginalRadioBandAm = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         ArrayList var1 = new ArrayList();
         int[] var2;
         StringBuilder var3;
         if (this.mIsOriginalRadioBandAm) {
            var1.add(new OriginalCarDeviceUpdateEntity(0, "AM"));
            var3 = new StringBuilder();
            var2 = this.mCanBusInfoInt;
            var1.add(new OriginalCarDeviceUpdateEntity(2, var3.append(var2[3] << 8 | var2[4]).append(" KHz").toString()));
            GeneralOriginalCarDeviceData.songList = this.getAmList();
         } else {
            var1.add(new OriginalCarDeviceUpdateEntity(0, "FM"));
            var3 = new StringBuilder();
            var2 = this.mCanBusInfoInt;
            var1.add(new OriginalCarDeviceUpdateEntity(2, var3.append((float)(var2[3] << 8 | var2[4]) / 10.0F).append(" MHz").toString()));
            GeneralOriginalCarDeviceData.songList = this.getFmList();
         }

         var1.add(new OriginalCarDeviceUpdateEntity(1, "P" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)));
         GeneralOriginalCarDeviceData.mList = var1;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void set0x04RadioPresetData(Context var1) {
      if ("original_status_radio".equals(this.mOriginalStatus) && this.isDataChange(this.mCanBusInfoInt)) {
         boolean var6 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         int var3 = 0;
         int var4 = 0;
         int var5;
         ArrayList var7;
         int[] var8;
         String var10;
         if (var6) {
            var7 = new ArrayList();
            this.mAmPresetList = var7;
            var7.add(new SongListEntity(CommUtil.getStrByResId(var1, "_209_preset_station")));
            var3 = var4;

            while(var3 < 6) {
               var8 = this.mCanBusInfoInt;
               var5 = var3 * 2;
               var4 = var8[var5 + 1 + 2];
               var5 = var8[var5 + 2 + 2];
               StringBuilder var11 = new StringBuilder();
               DecimalFormat var13 = this.mDecimalFormat00;
               ++var3;
               var10 = var11.append(var13.format((long)var3)).append("#").toString();
               this.mAmPresetList.add(new SongListEntity("\t" + var10 + "\t" + (var5 | var4 << 8) + " KHz"));
            }

            if (var6 != this.mIsOriginalRadioBandAm) {
               return;
            }

            GeneralOriginalCarDeviceData.songList = this.getAmList();
         } else {
            var7 = new ArrayList();
            this.mFmPresetList = var7;
            var7.add(new SongListEntity(CommUtil.getStrByResId(var1, "_209_preset_station")));

            while(var3 < 12) {
               var8 = this.mCanBusInfoInt;
               var5 = var3 * 2;
               var4 = var8[var5 + 1 + 2];
               float var2 = (float)(var8[var5 + 2 + 2] | var4 << 8) / 10.0F;
               StringBuilder var12 = new StringBuilder();
               DecimalFormat var9 = this.mDecimalFormat00;
               ++var3;
               var10 = var12.append(var9.format((long)var3)).append("#").toString();
               this.mFmPresetList.add(new SongListEntity("\t" + var10 + "\t" + var2 + " MHz"));
            }

            if (var6 != this.mIsOriginalRadioBandAm) {
               return;
            }

            GeneralOriginalCarDeviceData.songList = this.getFmList();
         }

         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void set0x07RadioStorageData(Context var1) {
      if ("original_status_radio".equals(this.mOriginalStatus)) {
         if (this.isDataChange(this.mCanBusInfoInt)) {
            boolean var6 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            String var7 = this.mCanBusInfoInt[4] + 1 + "#";
            String var8 = var7;
            if (var7.length() == 2) {
               var8 = "0" + var7;
            }

            int var4 = 0;
            int var3 = 0;
            int var5;
            int[] var11;
            if (var6) {
               var11 = this.mCanBusInfoInt;
               var5 = var11[5];
               var4 = var11[6];
               this.mAmStorageArray[var11[4] + 1] = new SongListEntity("\t" + var8 + "\t" + (var4 | var5 << 8) + " KHz");
               this.mAmStorageList = new ArrayList();
               SongListEntity[] var13 = this.mAmStorageArray;

               for(var4 = var13.length; var3 < var4; ++var3) {
                  SongListEntity var10 = var13[var3];
                  if (var10 != null && !TextUtils.isEmpty(var10.getTitle())) {
                     this.mAmStorageList.add(var10);
                  }
               }

               if (var6 != this.mIsOriginalRadioBandAm) {
                  return;
               }

               GeneralOriginalCarDeviceData.songList = this.getAmList();
            } else {
               var11 = this.mCanBusInfoInt;
               var3 = var11[5];
               float var2 = (float)(var11[6] | var3 << 8) / 10.0F;
               this.mFmStorageArray[var11[4] + 1] = new SongListEntity("\t" + var8 + "\t" + var2 + " MHz");
               this.mFmStorageList = new ArrayList();
               SongListEntity[] var9 = this.mFmStorageArray;
               var5 = var9.length;

               for(var3 = var4; var3 < var5; ++var3) {
                  SongListEntity var12 = var9[var3];
                  if (var12 != null && !TextUtils.isEmpty(var12.getTitle())) {
                     this.mFmStorageList.add(var12);
                  }
               }

               if (var6 != this.mIsOriginalRadioBandAm) {
                  return;
               }

               GeneralOriginalCarDeviceData.songList = this.getFmList();
            }
         }

         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void set0x14Data() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = this.mCanBusInfoInt[2];
         if (var1 >= 0 && var1 < 51) {
            this.setBacklightLevel(1);
         } else if (var1 >= 51 && var1 < 102) {
            this.setBacklightLevel(2);
         } else if (var1 >= 102 && var1 < 153) {
            this.setBacklightLevel(3);
         } else if (var1 >= 153 && var1 < 204) {
            this.setBacklightLevel(4);
         } else if (var1 >= 204 && var1 < 255) {
            this.setBacklightLevel(5);
         }

         if (this.mCanBusInfoInt.length > 3) {
            ArrayList var3 = new ArrayList();
            int[] var2 = this.mCanBusInfoInt;
            switch (var2.length) {
               case 8:
                  var3.add(this.checkEntity(this.helperSetValue("_157_saturation", var2[7])));
               case 7:
                  var3.add(this.checkEntity(this.helperSetValue("_157_contrast", this.mCanBusInfoInt[6])));
               case 6:
                  var3.add(this.checkEntity(this.helperSetValue("_157_brightness", this.mCanBusInfoInt[5])));
               case 5:
                  var3.add(this.checkEntity(this.helperSetValue("_157_backlight_color", this.mCanBusInfoInt[4])));
               case 4:
                  var3.add(this.checkEntity(this.helperSetValue("_157_backlight_mode", this.mCanBusInfoInt[3])));
               default:
                  this.updateGeneralSettingData(var3);
                  this.updateSettingActivity((Bundle)null);
            }
         }
      }

   }

   private void set0x20WheelKeyData(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 != 19) {
                        if (var2 != 20) {
                           if (var2 != 129) {
                              if (var2 != 130) {
                                 if (var2 != 134) {
                                    switch (var2) {
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
                                          this.realKeyLongClick1(var1, 189);
                                          break;
                                       default:
                                          switch (var2) {
                                             case 22:
                                                this.realKeyLongClick1(var1, 49);
                                                break;
                                             case 23:
                                                this.resolve0x17KeyMenu(var1);
                                                break;
                                             case 24:
                                                this.realKeyLongClick1(var1, 52);
                                                break;
                                             default:
                                                switch (var2) {
                                                   case 32:
                                                      this.realKeyLongClick1(var1, 7);
                                                      break;
                                                   case 33:
                                                      this.realKeyLongClick1(var1, 8);
                                                      break;
                                                   case 34:
                                                      this.realKeyLongClick1(var1, 46);
                                                      break;
                                                   case 35:
                                                      this.realKeyLongClick1(var1, 45);
                                                      break;
                                                   case 36:
                                                      this.realKeyLongClick1(var1, 20);
                                                      break;
                                                   case 37:
                                                      this.realKeyLongClick1(var1, 21);
                                                      break;
                                                   case 38:
                                                      this.realKeyLongClick1(var1, 46);
                                                      break;
                                                   case 39:
                                                      this.realKeyLongClick1(var1, 45);
                                                      break;
                                                   default:
                                                      switch (var2) {
                                                         case 41:
                                                            this.switchFrontCamera();
                                                            break;
                                                         case 42:
                                                            this.realKeyLongClick1(var1, 17);
                                                            break;
                                                         case 43:
                                                            this.realKeyLongClick1(var1, 18);
                                                            break;
                                                         case 44:
                                                            this.realKeyLongClick1(var1, 19);
                                                            break;
                                                         default:
                                                            switch (var2) {
                                                               case 48:
                                                                  this.realKeyLongClick1(var1, 3);
                                                                  break;
                                                               case 49:
                                                                  this.realKeyLongClick1(var1, 134);
                                                                  break;
                                                               case 50:
                                                                  this.realKeyLongClick1(var1, 50);
                                                                  break;
                                                               case 51:
                                                                  this.realKeyLongClick1(var1, 76);
                                                                  break;
                                                               case 52:
                                                                  this.realKeyLongClick1(var1, 77);
                                                                  break;
                                                               case 53:
                                                                  this.realKeyLongClick1(var1, 130);
                                                                  break;
                                                               case 54:
                                                                  this.realKeyLongClick1(var1, 141);
                                                                  break;
                                                               case 55:
                                                                  this.realKeyLongClick1(var1, 14);
                                                                  break;
                                                               case 56:
                                                                  this.realKeyLongClick1(var1, 33);
                                                                  break;
                                                               case 57:
                                                                  this.realKeyLongClick1(var1, 34);
                                                                  break;
                                                               case 58:
                                                                  this.realKeyLongClick1(var1, 35);
                                                                  break;
                                                               case 59:
                                                                  this.realKeyLongClick1(var1, 36);
                                                                  break;
                                                               case 60:
                                                                  this.realKeyLongClick1(var1, 37);
                                                                  break;
                                                               case 61:
                                                                  this.realKeyLongClick1(var1, 38);
                                                                  break;
                                                               case 62:
                                                                  this.realKeyLongClick1(var1, 49);
                                                                  break;
                                                               case 63:
                                                                  this.adjustBrightness();
                                                                  break;
                                                               case 64:
                                                                  this.realKeyLongClick1(var1, 52);
                                                                  break;
                                                               case 65:
                                                                  this.realKeyLongClick1(var1, 17);
                                                                  break;
                                                               case 66:
                                                                  this.realKeyLongClick1(var1, 4);
                                                            }
                                                      }
                                                }
                                          }
                                    }
                                 } else {
                                    this.realKeyLongClick1(var1, 3);
                                 }
                              } else {
                                 this.realKeyLongClick1(var1, 8);
                              }
                           } else {
                              this.realKeyLongClick1(var1, 7);
                           }
                        } else {
                           this.realKeyLongClick1(var1, 46);
                        }
                     } else {
                        this.realKeyLongClick1(var1, 45);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 45);
                  }
               } else {
                  this.realKeyLongClick1(var1, 46);
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

   private void set0x21AirData(Context var1) {
      if (!GeneralAirData.climate && DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
         if (SystemUtil.isForeground(var1, AirActivity.class.getName())) {
            this.finishActivity();
         } else {
            AirActivity.mIsClickOpen = true;
            Intent var3 = new Intent(var1, AirActivity.class);
            var3.setFlags(268435456);
            var1.startActivity(var3);
         }
      }

      GeneralAirData.climate = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      int[] var4 = this.mCanBusInfoInt;
      var4[3] &= 239;
      var4[6] &= 191;
      var4[7] = 0;
      var4[9] &= 239;
      if (this.isAirDataChange()) {
         int var2 = this.getAirWhat();
         GeneralAirData.auto_manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[4] & 255);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[5] & 255);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[8] & 255);
         GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_right_blow_window = GeneralAirData.rear_left_blow_window;
         GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
         GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
         this.updateAirActivity(var1, var2);
      }

   }

   private void set0x22RearRadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x23FrontRadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24Data(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

      this.mTurnSignalStatus2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
   }

   private void set0x25Data(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         ArrayList var2 = new ArrayList();
         var2.add(this.checkEntity(this.helperSetValue("_41_rear_radar", this.getOpenClose(var1, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))).setValueStr(true)));
         var2.add(this.checkEntity(this.helperSetValue("_41_front_radar", this.getOpenClose(var1, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))).setValueStr(true)));
         var2.add(this.checkEntity(this.helperSetValue("_41_park_assist", this.getOpenClose(var1, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))).setValueStr(true)));
         var2.add(this.checkEntity(this.helperSetValue("_41_radar_sound", this.getOpenClose(var1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))).setValueStr(true)));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x29TrackData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x30VersionInfoData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x31AmplifierData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[3] - 9;
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 9;
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[5];
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[7];
         GeneralAmplifierData.megaBass = this.mCanBusInfoInt[8];
         this.updateAmplifierActivity((Bundle)null);
         this.saveAmplifierData(var1, this.mCanId);
         ArrayList var2 = new ArrayList();
         var2.add(this.helperSetValue("_301_volume_and_speed_linkage", this.mCanBusInfoInt[9]));
         var2.add(this.helperSetValue("_301_dts_audio", this.mCanBusInfoInt[10]));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x32VehicleSettingData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         ArrayList var1 = new ArrayList();
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data1_bit65", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data1_bit43", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data1_bit20", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x67_data1_bit64", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x67_data1_bit32", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x67_data1_bit10", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x65_data1_bit0", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_41_key_remote_unlock", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x65_data1_bit21", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x65_data1_bit76", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x65_data1_bit54", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x66_data1_bit3", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x66_data1_bit0", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_194_unlock_mode", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x66_data1_bit2", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x67_data0_bit20", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data0_bit10", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data0_bit2", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data0_bit4", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_41_speed_distance_units", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_41_tachometer", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x65_data1_bit3", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x67_data0_bit3", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x66_data1_bit1", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data0_bit3", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x68_data1_bit2", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x68_data1_bit3", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x68_data1_bit10", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x68_data1_bit54", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_41_tachometer_switch", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x68_data1_bit76", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 2))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x75_data1_bit0", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x75_data1_bit1", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data1_bit7", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x68_data0_bit0", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_157_memory_position", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x64_data2_bit0", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data6_bit0", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data6_bit1", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data6_bit2", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data6_bit3", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data5", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_157_rear_entertainment_system", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 1))));
         var1.add(this.checkEntity(this.helperSetValue("_55_0x69_data0_bit6", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 1))));
         int[] var2 = this.mCanBusInfoInt;
         if (var2.length > 10) {
            var1.add(this.checkEntity(this.helperSetValue("_157_auto_trunk", DataHandleUtils.getIntFromByteWithBit(var2[10], 7, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_157_seat_auto_move", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_157_Rear_seat_reminder", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1))));
         }

         var2 = this.mCanBusInfoInt;
         if (var2.length > 11) {
            var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data0_bit10", DataHandleUtils.getIntFromByteWithBit(var2[11], 6, 2))));
            var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data0_bit32", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2))));
            var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data6_bit4", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 3, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data6_bit5", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_157_revers_mode", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 1, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_55_0xE8_data0_bit4", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 1))));
         }

         var2 = this.mCanBusInfoInt;
         if (var2.length > 12) {
            var1.add(this.checkEntity(this.helperSetValue("_157_trunk_sense_switch", DataHandleUtils.getIntFromByteWithBit(var2[12], 7, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_157_back_mirror_fold", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 6, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_157_turn_guide_signal", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 5, 1))));
            var1.add(this.checkEntity(this.helperSetValue("_157_Straight_line_driving_assistance_reminder", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 1))));
         }

         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x33FuelComsuptionData() {
      int var2 = this.mCanBusInfoInt[2];
      ArrayList var4;
      String var6;
      int[] var7;
      int[] var12;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 == 3) {
               var4 = new ArrayList();
               var4.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[3] + "%"));
               this.updateGeneralDriveData(var4);
               this.updateDriveDataActivity((Bundle)null);
            }
         } else if (this.is0x33IndexTwoDataChange()) {
            String var10 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 4, 2));
            var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 6, 1));
            ArrayList var5 = new ArrayList();
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 1, this.getData(new int[]{var7[5], var7[4], var7[3]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 2, this.getData(new int[]{var7[7], var7[6]}, 0.1F, var10)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 4, this.getData(new int[]{var7[10], var7[9], var7[8]}, 0.1F, var6)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 5, this.getData(new int[]{var7[12], var7[11]}, 0.1F, var10)));
            var7 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 7, this.getData(new int[]{var7[15], var7[14], var7[13]}, 0.1F, var6)));
            var12 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 8, this.getData(new int[]{var12[17], var12[16]}, 0.1F, var10)));
            this.updateGeneralDriveData(var5);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else if (this.is0x33IndexOneDataChange()) {
         String var9 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2));
         String var8 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 2, 2));
         String var14 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2));
         var6 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1));
         String var11 = this.getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1));
         int var3 = this.getRange(this.mCanBusInfoInt[16]);
         var4 = new ArrayList();
         var2 = this.mCanBusInfoInt[3];
         float var1 = (float)var3;
         var4.add(new DriverUpdateEntity(0, 0, this.getData(new int[]{var2}, var1 * 0.04761905F, var9)));
         int[] var15 = this.mCanBusInfoInt;
         var4.add(new DriverUpdateEntity(0, 1, this.getData(new int[]{var15[5], var15[4]}, 0.1F, var8)));
         var15 = this.mCanBusInfoInt;
         var4.add(new DriverUpdateEntity(0, 2, this.getData(new int[]{var15[7], var15[6]}, 0.1F, var8)));
         int[] var13 = this.mCanBusInfoInt;
         var4.add(new DriverUpdateEntity(0, 3, this.getData(new int[]{var13[9], var13[8]}, 0.1F, var14)));
         var7 = this.mCanBusInfoInt;
         var4.add(new DriverUpdateEntity(0, 4, this.getData(new int[]{var7[12], var7[11], var7[10]}, 0.1F, var6)));
         var12 = this.mCanBusInfoInt;
         var4.add(new DriverUpdateEntity(0, 5, this.getData(new int[]{var12[14], var12[13]}, 1.0F, var11)));
         this.updateGeneralDriveData(var4);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0x50PanoramicData(Context var1) {
      if (!this.isShowOriginalRadio()) {
         if (this.mCanBusInfoInt[2] == 3) {
            this.enterAuxIn2();
         } else {
            this.exitAuxIn2();
         }
      }

   }

   private void set0x52Data() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralHybirdData.isEngineDriveMotor = var1;
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var2 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralHybirdData.isBatteryDriveMotor = var2;
      GeneralHybirdData.isMotorDriveWheel = var2;
      var2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralHybirdData.isWheelDriveMotor = var2;
      if (!var1 && !var2) {
         var1 = false;
      } else {
         var1 = true;
      }

      GeneralHybirdData.isMotorDriveBattery = var1;
      this.updateHybirdActivity((Bundle)null);
   }

   private void set0x77Data() {
      int var1 = this.mCanBusInfoInt[3];
      if (var1 == 0) {
         if (var1 == 0) {
            MyLog.temporaryTracking("ASCII码");
            var1 = 5;
            String var2 = "%";

            while(true) {
               int[] var3 = this.mCanBusInfoInt;
               if (var1 >= var3.length) {
                  var1 = var3[2];
                  if (var1 != 0) {
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 == 3) {
                              this.contentThree = this.getUTF8Result(var2);
                           }
                        } else {
                           this.contentTow = this.getUTF8Result(var2);
                        }
                     } else {
                        this.contentOne = this.getUTF8Result(var2);
                     }
                  } else {
                     this.contentZero = this.getUTF8Result(var2);
                  }
                  break;
               }

               String var4 = var2;
               if (var1 != 5) {
                  var4 = var2 + "%";
               }

               var2 = var4 + String.format("%02x", this.mCanBusInfoInt[var1]);
               ++var1;
            }
         }

         this.showDialog(this.getActivity(), this.contentZero, this.contentOne, this.contentTow, this.contentThree);
      }
   }

   private void set0x78OriginalVolumeData(Context var1) {
      GeneralOriginalCarDeviceData.discStatus = var1.getString(2131770834) + "\t" + this.mCanBusInfoInt[3];
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void set0x79Data() {
   }

   private void set0x7ASoundtrackData(Context var1) {
      ArrayList var2 = new ArrayList();
      if (this.mCanBusInfoInt[2] == 0) {
         var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "amplifier_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "amplifier_setting", "_157_sheng"), this.mContext.getString(2131757634))).setValueStr(true));
         this.updateGeneralSettingData(var2);
      } else {
         var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "amplifier_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "amplifier_setting", "_157_sheng"), this.mContext.getString(2131757635))).setValueStr(true));
         this.updateGeneralSettingData(var2);
      }

      this.updateSettingActivity((Bundle)null);
      if (this.isDataChange(this.mCanBusInfoInt)) {
         if (this.mCanBusInfoInt[2] == 0) {
            this.enterAuxIn2();
         } else {
            this.exitAuxIn2();
         }
      }

   }

   private void set0x7BOriginalStatusData(Context var1) {
      if (this.isShowOriginalRadio()) {
         int var2;
         ArrayList var3;
         switch (this.mCanBusInfoInt[2]) {
            case 0:
               GeneralOriginalCarDeviceData.cdStatus = "";
               this.mOriginalStatus = "original_status_null";
               this.setNullPage();
               this.exitAuxIn2();
               break;
            case 1:
            case 2:
               GeneralOriginalCarDeviceData.songList = this.getFmList();
               GeneralOriginalCarDeviceData.cdStatus = "RADIO";
               this.mOriginalStatus = "original_status_radio";
               this.setRadioPage();
               this.enterAuxIn2(var1, Constant.OriginalDeviceActivity);
               break;
            case 3:
               GeneralOriginalCarDeviceData.songList = this.getAmList();
               GeneralOriginalCarDeviceData.cdStatus = "RADIO";
               this.mOriginalStatus = "original_status_radio";
               this.setRadioPage();
               this.enterAuxIn2(var1, Constant.OriginalDeviceActivity);
               break;
            case 4:
               if (!"original_status_cd".equals(this.mOriginalStatus)) {
                  this.mOriginalStatus = "original_status_cd";
                  this.setCdPage();
                  this.disc_status_on = CommUtil.getStrByResId(var1, "_157_disc_status_on");
                  this.disc_status_off = CommUtil.getStrByResId(var1, "_157_disc_status_off");
               }

               GeneralOriginalCarDeviceData.cdStatus = "CD";
               var3 = new ArrayList();
               var3.add(new OriginalCarDeviceUpdateEntity(0, ": " + this.mCanBusInfoInt[3]));
               var3.add(new OriginalCarDeviceUpdateEntity(1, ": " + this.mCanBusInfoInt[4]));
               var3.add(new OriginalCarDeviceUpdateEntity(2, ": " + this.getCdStatus(var1, this.mCanBusInfoInt[5])));
               var3.add(new OriginalCarDeviceUpdateEntity(3, ": " + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[7]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[8])));
               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1) == 1) {
                  var3.add(new OriginalCarDeviceUpdateEntity(4, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_on")));
               } else {
                  var3.add(new OriginalCarDeviceUpdateEntity(4, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_off")));
               }

               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1) == 1) {
                  var3.add(new OriginalCarDeviceUpdateEntity(5, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_on")));
               } else {
                  var3.add(new OriginalCarDeviceUpdateEntity(5, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_off")));
               }

               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1) == 1) {
                  var3.add(new OriginalCarDeviceUpdateEntity(6, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_on")));
               } else {
                  var3.add(new OriginalCarDeviceUpdateEntity(6, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_off")));
               }

               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1) == 1) {
                  var3.add(new OriginalCarDeviceUpdateEntity(7, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_on")));
               } else {
                  var3.add(new OriginalCarDeviceUpdateEntity(7, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_off")));
               }

               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1) == 1) {
                  var3.add(new OriginalCarDeviceUpdateEntity(8, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_on")));
               } else {
                  var3.add(new OriginalCarDeviceUpdateEntity(8, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_off")));
               }

               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1) == 1) {
                  var3.add(new OriginalCarDeviceUpdateEntity(9, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_on")));
               } else {
                  var3.add(new OriginalCarDeviceUpdateEntity(9, ": " + CommUtil.getStrByResId(var1, "_157_disc_status_off")));
               }

               var2 = this.mCanBusInfoInt[9];
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_cd_status_eject");
                        }
                     } else {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_cd_status_changing");
                     }
                  } else {
                     GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_cd_status_reading");
                  }
               } else {
                  GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_cd_status_playing");
               }

               GeneralOriginalCarDeviceData.mList = var3;
               this.updateOriginalCarDeviceActivity((Bundle)null);
               this.enterAuxIn2(var1, Constant.OriginalDeviceActivity);
               break;
            case 5:
               GeneralOriginalCarDeviceData.cdStatus = "AUX";
               this.mOriginalStatus = "original_status_aux";
               this.setNullPage();
               this.exitAuxIn2();
               break;
            case 6:
               if (!"original_status_usb".equals(this.mOriginalStatus)) {
                  this.mOriginalStatus = "original_status_usb";
                  this.setUsbPage();
               }

               GeneralOriginalCarDeviceData.cdStatus = "USB";
               var3 = new ArrayList();
               var3.add(new OriginalCarDeviceUpdateEntity(0, ": " + this.mCanBusInfoInt[3]));
               var3.add(new OriginalCarDeviceUpdateEntity(1, ": " + this.mCanBusInfoInt[4]));
               var3.add(new OriginalCarDeviceUpdateEntity(2, ": " + this.getCdStatus(var1, this.mCanBusInfoInt[5])));
               var3.add(new OriginalCarDeviceUpdateEntity(3, ": " + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[7]) + ":" + this.mDecimalFormat00.format((long)this.mCanBusInfoInt[8])));
               var2 = this.mCanBusInfoInt[9];
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_usb_status_reading");
                        }
                     } else {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_usb_status_disconnected");
                     }
                  } else {
                     GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_usb_status_connected");
                  }
               } else {
                  GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var1, "_157_usb_status_playing");
               }

               GeneralOriginalCarDeviceData.mList = var3;
               this.updateOriginalCarDeviceActivity((Bundle)null);
               this.enterAuxIn2(var1, Constant.OriginalDeviceActivity);
         }

      }
   }

   private void set0xD0Data() {
   }

   private void set0xD1CameraData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         boolean var5 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         boolean var4 = this.mFrontViewBtnStatus;
         boolean var3 = true;
         if (var4 && !var5) {
            this.switchFCamera(this.mContext, this.isPreCameraOrAuxForeground(var1) ^ true);
         }

         this.mFrontViewBtnStatus = var5;
         byte var2;
         if (SharePreUtil.getBoolValue(var1, "share_157_front_camera_switch", false)) {
            var2 = 3;
         } else {
            var2 = 7;
         }

         if ((1 << var2 & this.mCanBusInfoInt[3]) == 0) {
            var3 = false;
         }

         if (this.mTurnSignalStatus ^ var3) {
            this.mTurnSignalStatus = var3;
            if (this.isPreCameraOrAuxForeground(var1) ^ var3) {
               this.switchFCamera(this.mContext, var3);
            }
         }
      }

   }

   private void set0xD2CompassData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         ArrayList var4 = new ArrayList();
         String var3;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            var3 = "compass_calibrating";
         } else {
            var3 = "compass_calibration_finish";
         }

         String var5 = CommUtil.getStrByResId(var1, var3);
         int var2 = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4), 1, 15);
         var3 = (float)(this.mCanBusInfoInt[3] * 3) / 2.0F + "°";
         var4.add(this.checkEntity(this.helperSetValue("compass_calibration_status", var5).setValueStr(true)));
         var4.add(this.checkEntity(this.helperSetValue("compass_zoom", Integer.toString(var2)).setProgress(var2 - 1).setValueStr(true)));
         var4.add(this.checkEntity(this.helperSetValue("magnetic_field_angle", var3).setValueStr(true)));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0xD3SourceSwitch(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var2 = this.mCanBusInfoInt[2];
         if (var2 != 0) {
            switch (var2) {
               case 32:
                  this.realKeyClick(var1, 129);
                  (new CountDownTimer(this, 500L, 100L, var1) {
                     final MsgMgr this$0;
                     final Context val$context;

                     {
                        this.this$0 = var1;
                        this.val$context = var6;
                     }

                     public void onFinish() {
                        this.this$0.realKeyClick(this.val$context, 77);
                     }

                     public void onTick(long var1) {
                     }
                  }).start();
                  break;
               case 33:
                  this.realKeyClick(var1, 129);
                  (new CountDownTimer(this, 500L, 100L, var1) {
                     final MsgMgr this$0;
                     final Context val$context;

                     {
                        this.this$0 = var1;
                        this.val$context = var6;
                     }

                     public void onFinish() {
                        this.this$0.realKeyClick(this.val$context, 76);
                     }

                     public void onTick(long var1) {
                     }
                  }).start();
                  break;
               case 34:
                  this.realKeyClick(var1, 59);
                  break;
               case 35:
                  this.realKeyClick(var1, 140);
                  break;
               case 36:
                  this.realKeyClick(var1, 141);
                  break;
               case 37:
                  this.realKeyClick(var1, 59);
            }
         } else {
            this.realKeyClick(var1, 52);
         }
      }

   }

   private void setCdPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_number_disc", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_cd_state", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "current_play_time", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_1", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_2", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_3", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_4", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_5", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_6", ""));
      this.changeOriginalDevicePage(var1, (String[])null, (String[])null, false);
   }

   private void setNullPage() {
      this.changeOriginalDevicePage(new ArrayList(), (String[])null, (String[])null, false);
   }

   private void setRadioPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", ""));
      this.changeOriginalDevicePage(var1, new String[]{"st", "scan", "refresh"}, new String[]{"prev_disc", "left", "up", "band", "down", "right", "next_disc"}, true);
   }

   private void setUsbPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_number_folder", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_usb_play_state", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "current_play_time", ""));
      this.changeOriginalDevicePage(var1, (String[])null, (String[])null, false);
   }

   private void switchFrontCamera() {
      this.switchFCamera(this.mContext, this.mIsFrontCameraOpen ^ true);
      this.mIsFrontCameraOpen ^= true;
   }

   private void updateOriginalDeviceWithInit() {
      Bundle var1 = new Bundle();
      var1.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      Log.i("_157_MsgMgr", "afterServiceNormalSetting: in");
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      Log.i("_157_MsgMgr", "afterServiceNormalSetting: 0x81");
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      Log.i("_157_MsgMgr", "afterServiceNormalSetting: diff <--> " + this.mDifferentId + ", each <--> " + this.mEachId + ", canId <--> " + this.mCanId);
      if (this.isShowOriginalRadio()) {
         Log.i("_157_MsgMgr", "afterServiceNormalSetting: enable original activity");
         SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      } else {
         Log.i("_157_MsgMgr", "afterServiceNormalSetting: disable original activity");
      }

      Log.i("_157_MsgMgr", "afterServiceNormalSetting: initSetting");
      this.initSettingsItem(this.getUiMgr(var1).getSettingUiSet(var1));
      this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      this.initID3();
      this.mDecimalFormat0p0 = new DecimalFormat("0.0");
      this.mDecimalFormat00 = new DecimalFormat("00");
      this.mCanbusDataArray = new SparseArray();
      this.mHandler = new Handler(this, Looper.getMainLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 1) {
               Log.i("_157_MsgMgr", "handleMessage: meida");
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 2) {
               Log.i("_157_MsgMgr", "handleMessage: normal");
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
      this.mFmStorageArray[0] = new SongListEntity(CommUtil.getStrByResId(var1, "_157_storage_station"));
      this.mAmStorageArray[0] = new SongListEntity(CommUtil.getStrByResId(var1, "_157_storage_station"));
      if (this.mDifferentId == 34) {
         this.mCharsetName = "utf-8";
      }

      if (var1.getResources().getConfiguration().orientation == 1) {
         int var2 = this.mEachId;
         if (var2 != 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)var2});
         }
      }

   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), new byte[]{22, -64, 3, 34});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -126, 3});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, 48});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      this.mMusicId3s[0].info = var1;
      this.mMusicId3s[1].info = var3;
      this.mMusicId3s[2].info = var2;
      this.reportID3Info(this.mMusicId3s);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -126, 6});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -64, 11, 17});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      this.mMusicId3s[0].info = " ";
      this.mMusicId3s[1].info = " ";
      this.mMusicId3s[2].info = " ";
      this.reportID3Info(this.mMusicId3s);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 1}, 35, 32));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendNormalMessage(new byte[]{22, -126, 5});
      this.sendNormalMessage(new byte[]{22, -64, 5, 64});
      this.sendNormalMessage(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var1), 35, 32));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.sendNormalMessage(new byte[]{22, -126, 5});
      this.sendNormalMessage(new byte[]{22, -64, 5, 64});
      this.sendNormalMessage(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var1), 35, 32));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var5 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var5;
      int var3 = var5[1];
      if (var3 != 3) {
         if (var3 != 4) {
            if (var3 != 7) {
               if (var3 != 20) {
                  if (var3 != 41) {
                     if (var3 != 80) {
                        if (var3 != 82) {
                           switch (var3) {
                              case 32:
                                 this.set0x20WheelKeyData(var1);
                                 break;
                              case 33:
                                 this.set0x21AirData(var1);
                                 break;
                              case 34:
                                 this.set0x22RearRadarData(var1);
                                 break;
                              case 35:
                                 this.set0x23FrontRadarData(var1);
                                 break;
                              case 36:
                                 this.set0x24Data(var1);
                                 break;
                              case 37:
                                 this.set0x25Data(var1);
                                 break;
                              default:
                                 switch (var3) {
                                    case 48:
                                       this.set0x30VersionInfoData(var1);
                                       break;
                                    case 49:
                                       this.set0x31AmplifierData(var1);
                                       break;
                                    case 50:
                                       this.set0x32VehicleSettingData();
                                       break;
                                    case 51:
                                       this.set0x33FuelComsuptionData();
                                       break;
                                    default:
                                       switch (var3) {
                                          case 119:
                                             this.set0x77Data();
                                             break;
                                          case 120:
                                             this.set0x78OriginalVolumeData(var1);
                                             break;
                                          case 121:
                                             this.set0x79Data();
                                             break;
                                          case 122:
                                             this.set0x7ASoundtrackData(var1);
                                             break;
                                          case 123:
                                             AlertDialog var6 = this.dialog;
                                             if (var6 != null) {
                                                var6.dismiss();
                                             }

                                             this.set0x7BOriginalStatusData(var1);
                                             break;
                                          default:
                                             switch (var3) {
                                                case 208:
                                                   this.set0xD0Data();
                                                   break;
                                                case 209:
                                                   this.set0xD1CameraData(var1);
                                                   break;
                                                case 210:
                                                   this.set0xD2CompassData(var1);
                                                   break;
                                                case 211:
                                                   this.set0xD3SourceSwitch(var1);
                                             }
                                       }
                                 }
                           }
                        } else {
                           this.set0x52Data();
                        }
                     } else {
                        this.set0x50PanoramicData(var1);
                     }
                  } else {
                     this.set0x29TrackData(var1);
                  }
               } else {
                  Log.i("_157_MsgMgr", "canbusInfoChange: 0x14");
                  this.set0x14Data();
               }
            } else {
               this.set0x07RadioStorageData(var1);
            }
         } else {
            this.set0x04RadioPresetData(var1);
         }
      } else {
         AlertDialog var4 = this.dialog;
         if (var4 != null) {
            var4.dismiss();
         }

         this.set0x03RadioFreqData();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      short var3;
      if (var2) {
         var3 = 128;
      } else {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(var1 | var3)});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (!var10) {
         var8 = var5 | 128;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte)var8, (byte)var6, (byte)var7});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -126, 2});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -64, 2, 33});
      if (var7 == 1 || var7 == 5) {
         var4 = var5;
      }

      var3 = var4;
      if (var4 > 255) {
         var3 = 255;
      }

      int[] var17 = this.getTime(var2);
      var1 = (byte)var3;
      byte var15 = (byte)var5;
      byte var14 = (byte)var17[1];
      byte var16 = (byte)var17[2];
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -61, var1, var15, 0, 0, var14, var16});
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), new byte[]{22, -64, 10, 34});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   int getOriginalRadioPresetSize() {
      byte var1;
      if (this.mIsOriginalRadioBandAm) {
         var1 = 6;
      } else {
         var1 = 12;
      }

      return var1;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(var1);
   }

   boolean isShowOriginalRadio() {
      return this.equal(this.mDifferentId, 19, 49, 80);
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mMusicId3s[0].info = " ";
      this.mMusicId3s[1].info = " ";
      this.mMusicId3s[2].info = " ";
      this.reportID3Info(this.mMusicId3s);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var26 = 9;
      if (var1 == 9) {
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -126, -1});
      } else {
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -126, 4});
         var26 = 8;
      }

      var1 = (byte)var26;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -64, var1, 19});
      byte var25 = 99;
      var17 = var4;
      var4 = var4;
      if (var17 > 99) {
         var4 = 99;
      }

      var3 += var9 * 256;
      if (var3 > 99) {
         var3 = var25;
      }

      var5 = (byte)var4;
      var1 = (byte)var3;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -61, var5, var2, var1, 0, var6, var7});
      this.mMusicId3s[0].info = var21;
      this.mMusicId3s[1].info = var22;
      this.mMusicId3s[2].info = var23;
      this.reportID3Info(this.mMusicId3s);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -126, 1});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -64, 1, 1});
      var5 = this.getBandData(var2);
      int[] var10 = this.getFreqByteHiLo(var2, var3);
      byte var9 = (byte)var5;
      byte var8 = (byte)var10[1];
      byte var7 = (byte)var10[0];
      byte var6 = (byte)var1;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -62, var9, var8, var7, var6});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void showDialog(Context var1, String var2, String var3, String var4, String var5) {
      if (this.view == null) {
         this.view = LayoutInflater.from(var1).inflate(2131558400, (ViewGroup)null, true);
      }

      if (this.dialog == null) {
         this.dialog = (new AlertDialog.Builder(var1)).setView(this.view).create();
      }

      if (this.content0 == null) {
         this.content0 = (TextView)this.view.findViewById(2131362152);
      }

      if (this.content1 == null) {
         this.content1 = (TextView)this.view.findViewById(2131362153);
      }

      if (this.content2 == null) {
         this.content2 = (TextView)this.view.findViewById(2131362154);
      }

      if (this.content3 == null) {
         this.content3 = (TextView)this.view.findViewById(2131362155);
      }

      if (this.EXIT == null) {
         this.EXIT = (Button)this.view.findViewById(2131361808);
      }

      this.content0.setText(var2);
      this.content1.setText(var3);
      this.content2.setText(var4);
      this.content3.setText(var5);
      this.EXIT.setOnClickListener(new View.OnClickListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.dialog.dismiss();
         }
      });
      this.dialog.setCancelable(false);
      this.dialog.getWindow().setBackgroundDrawableResource(17170445);
      this.dialog.show();
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.sendNormalMessage(new byte[]{22, -126, 0});
         this.sendNormalMessage(new byte[]{22, -64, 0, 48});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.initAmplifierData(this.mContext);
   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18 = 9;
      if (var1 != 9) {
         this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -126, 4});
         var18 = 8;
      }

      var1 = (byte)var18;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, var1, 19});
      byte var19 = 99;
      var14 = var4;
      var4 = var4;
      if (var14 > 99) {
         var4 = 99;
      }

      var3 += var9 * 256;
      if (var3 > 99) {
         var3 = var19;
      }

      var1 = (byte)var4;
      var5 = (byte)var3;
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -61, var1, var2, var5, 0, var6, var7});
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
            this.record = this.info;
            return true;
         }
      }

      private void recordId3Info() {
         this.record = this.info;
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
