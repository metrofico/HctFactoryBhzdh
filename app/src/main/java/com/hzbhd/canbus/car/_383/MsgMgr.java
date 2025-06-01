package com.hzbhd.canbus.car._383;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SetAlertView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static final String TAG = "_306_MsgMgr";
   public static final ComponentName componentNameBtMusic = new ComponentName("com.hzbhd.btui", "com.hzbhd.btui.A2dpMainActivity");
   public static final ComponentName componentNameBtPhone = new ComponentName("com.hzbhd.btui", "com.hzbhd.btui.HfpMainActivity");
   public static final ComponentName componentNameEasyconn = new ComponentName("net.easyconn", "net.easyconn.ui.Bhd07MainActivity");
   public static final ComponentName componentNameMusic = new ComponentName("com.hzbhd.ui.media", "com.hzbhd.ui.activity.MusicActivity");
   public static final ComponentName componentNameNavi = new ComponentName("com.android.launcher3", "com.android.launcher3.firstscreen.NaviActivity");
   public static final ComponentName componentNameRadio = new ComponentName("com.hzbhd.radioui", "com.hzbhd.radioui.RadioMainActivity");
   public static final ComponentName componentNameVidio = new ComponentName("com.hzbhd.ui.media", "com.hzbhd.ui.activity.VideoActivity");
   public static int hour;
   public static int language;
   public static String mLanguage;
   public static int minute;
   private final int CUSTOM_KEY_0X21_0X15 = 65301;
   public String ID306_AVM_CONGIF_TAG = "ID306_AVM_CONGIF_TAG";
   private final String PATH_VOICE_306 = "voice/_306";
   private byte carInfoData0 = 0;
   private boolean carInfoFirst = false;
   private LanguageReceiver languageReceiver;
   private SparseArray mAutoParkVoiceArray;
   private AutoParkVoiceManger mAutoParkVoiceManger;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private MyPanoramicView mPanoramicView;
   int nowMedia = 0;
   int nowSource = 0;

   private void airInfo0x28() {
      if (!this.getUiMgr().isDx5Low()) {
         if (!this.getUiMgr().isDx3()) {
            GeneralAirData.dual = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ^ true;
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[3];
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            int var1 = this.mCanBusInfoInt[4];
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }

            GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
            if (GeneralAirData.front_wind_level != 0) {
               this.updateAirActivity(this.mContext, 1001);
            }

         }
      }
   }

   private void carInfo0x24() {
      if (!this.isDX7()) {
         if (this.carInfoData0 != this.mCanBusInfoByte[2]) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            if (this.carInfoFirst) {
               this.updateDoorView(this.mContext);
            } else {
               this.carInfoFirst = true;
            }

            this.carInfoData0 = this.mCanBusInfoByte[2];
         }

      }
   }

   private void carSettingInfo0x33() {
      int[] var10 = this.mCanBusInfoInt;
      float var2 = (float)((double)(var10[2] * 256 + var10[3]) * 0.1);
      int var7 = var10[4];
      int var5 = var10[5];
      int var3 = var10[6] * 256 + var10[7];
      int var9 = var10[8];
      short var4 = (short)(var10[9] << 8 & '\uff00' | var10[10] & 255);
      int var8 = var10[11];
      int var6 = var10[12];
      float var1 = (float)((double)var10[13] * 0.1);
      ArrayList var11 = new ArrayList();
      var11.add(new DriverUpdateEntity(0, 0, var2 + "L/100km"));
      var11.add(new DriverUpdateEntity(0, 1, var7 * 256 + var5 + "KM"));
      if (var3 >= 1023) {
         var11.add(new DriverUpdateEntity(0, 2, "--KM"));
      } else {
         var11.add(new DriverUpdateEntity(0, 2, var3 + "KM"));
      }

      var11.add(new DriverUpdateEntity(0, 3, var9 + "%"));
      var11.add(new DriverUpdateEntity(0, 4, var8 * 256 + var6 + "KM"));
      var11.add(new DriverUpdateEntity(0, 5, var1 + "V"));
      var11.add(new DriverUpdateEntity(0, 6, var4 + "°"));
      Context var12;
      if (this.mCanBusInfoInt[14] == 1) {
         var12 = this.mContext;
         var3 = 2131762070;
      } else {
         var12 = this.mContext;
         var3 = 2131762071;
      }

      var11.add(new DriverUpdateEntity(0, 7, var12.getString(var3)));
      this.updateGeneralDriveData(var11);
      this.updateDriveDataActivity((Bundle)null);
      List var13 = GeneralTireData.dataList;
      if (var13 != null) {
         var3 = this.mCanBusInfoInt[14];
         ((TireUpdateEntity)var13.get(0)).setTireStatus(var3);
         ((TireUpdateEntity)var13.get(1)).setTireStatus(var3);
         ((TireUpdateEntity)var13.get(2)).setTireStatus(var3);
         ((TireUpdateEntity)var13.get(3)).setTireStatus(var3);
         GeneralTireData.dataList = var13;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void carSettingInfo0x34() {
      ArrayList var6 = new ArrayList();
      boolean var5 = this.isDX7();
      int var4 = 7;
      int var3 = 5;
      byte var2 = 6;
      if (!var5) {
         var6.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
         var6.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var6.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      } else {
         byte var1;
         if (this.isDX7()) {
            var1 = 0;
         } else {
            var1 = 3;
         }

         var6.add(new SettingUpdateEntity(0, var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
         if (this.isDX7()) {
            var1 = 1;
         } else {
            var1 = 4;
         }

         var6.add(new SettingUpdateEntity(0, var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         if (this.isDX7()) {
            var1 = 2;
         } else {
            var1 = 5;
         }

         var6.add(new SettingUpdateEntity(0, var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         if (this.isDX7()) {
            var1 = 3;
         } else {
            var1 = 6;
         }

         var6.add(new SettingUpdateEntity(0, var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
         var1 = (byte)var4;
         if (this.isDX7()) {
            var1 = 4;
         }

         var6.add(new SettingUpdateEntity(0, var1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
         var4 = this.mCanBusInfoInt[3];
         if (var4 >= 1 && var4 <= 3) {
            if (this.isDX7()) {
               var1 = (byte)var3;
            } else {
               var1 = 8;
            }

            var6.add(new SettingUpdateEntity(0, var1, var4 - 1));
         }

         var3 = this.mCanBusInfoInt[4];
         if (var3 >= 0 && var3 <= 1) {
            if (this.isDX7()) {
               var1 = var2;
            } else {
               var1 = 9;
            }

            var6.add(new SettingUpdateEntity(0, var1, var3));
         }
      }

      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   private void frontRadar0x23() {
      int var1 = this.getLeftAndRightRadar(this.mCanBusInfoInt[2]);
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1, var2[3], var2[4], this.getLeftAndRightRadar(var2[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private int getLeftAndRightRadar(int var1) {
      if (var1 == 1) {
         return 2;
      } else {
         return var1 == 2 ? 4 : 0;
      }
   }

   private MyPanoramicView getMyPanoramicView() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (MyPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      return this.mPanoramicView;
   }

   private UiMgr getUiMgr() {
      return (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
   }

   private void initAutoParkVoice() {
      this.mAutoParkVoiceManger = new AutoParkVoiceManger();
      SparseArray var1 = new SparseArray();
      this.mAutoParkVoiceArray = var1;
      var1.put(10, "_0x0a.mp3");
      this.mAutoParkVoiceArray.put(11, "_0x0b.mp3");
      this.mAutoParkVoiceArray.put(12, "_0x0c.mp3");
      this.mAutoParkVoiceArray.put(13, "_0x0d.mp3");
      this.mAutoParkVoiceArray.put(14, "_0x0e.mp3");
      this.mAutoParkVoiceArray.put(15, "_0x0f.mp3");
      this.mAutoParkVoiceArray.put(1, "_0x01_0x02.mp3");
      this.mAutoParkVoiceArray.put(2, "_0x01_0x02.mp3");
      this.mAutoParkVoiceArray.put(3, "_0x03_0x04.mp3");
      this.mAutoParkVoiceArray.put(4, "_0x03_0x04.mp3");
      this.mAutoParkVoiceArray.put(5, "_0x05_0x06.mp3");
      this.mAutoParkVoiceArray.put(6, "_0x05_0x06.mp3");
      this.mAutoParkVoiceArray.put(7, "_0x07_0x08.mp3");
      this.mAutoParkVoiceArray.put(8, "_0x07_0x08.mp3");
      this.mAutoParkVoiceArray.put(9, "_0x09.mp3");
      this.mAutoParkVoiceArray.put(16, "_0x10.mp3");
      this.mAutoParkVoiceArray.put(17, "_0x11.mp3");
      this.mAutoParkVoiceArray.put(18, "_0x12.mp3");
      this.mAutoParkVoiceArray.put(19, "_0x13.mp3");
      this.mAutoParkVoiceArray.put(20, "_0x14.mp3");
      this.mAutoParkVoiceArray.put(21, "_0x15.mp3");
      this.mAutoParkVoiceArray.put(22, "_0x16.mp3");
      this.mAutoParkVoiceArray.put(23, "_0x17.mp3");
   }

   private boolean isDX7() {
      return this.getUiMgr().isDX7();
   }

   private boolean isLauncher3() {
      return SystemUtil.isForeground(this.mContext, "com.android.launcher3.activity.MainActivity");
   }

   private void keyEvent0x20() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(7);
            break;
         case 2:
            this.realKeyClick(8);
            break;
         case 3:
            this.realKeyClick(45);
            break;
         case 4:
            this.realKeyClick(46);
         case 5:
         default:
            break;
         case 6:
            this.realKeyClick(3);
            break;
         case 7:
            this.realKeyClick(2);
            break;
         case 8:
            this.realKeyClick(3);
            break;
         case 9:
            this.realKeyClick(188);
            break;
         case 10:
            this.realKeyClick(15);
      }

   }

   private void panelEvent0x21() {
      int var1 = this.mCanBusInfoInt[2];
      switch (var1) {
         case 0:
            this.realKeyLongClick(0);
            break;
         case 1:
            this.realKeyClick(52);
            break;
         case 2:
         case 4:
            this.realKeyClick(128);
            break;
         case 3:
            var1 = this.nowMedia;
            if (var1 == 0) {
               this.realKeyClick(62);
               this.nowMedia = 1;
               this.startOtherActivity(this.mContext, componentNameRadio);
            } else if (var1 == 1) {
               this.realKeyClick(59);
               this.nowMedia = 2;
            } else if (var1 == 2) {
               this.realKeyClick(140);
               this.nowMedia = 3;
            } else if (var1 == 3) {
               this.realKeyClick(61);
               this.nowMedia = 0;
            }
            break;
         case 5:
            this.realKeyClick(50);
            break;
         case 6:
            this.realKeyClick(58);
            break;
         case 7:
            if (this.isLauncher3()) {
               this.realKeyClick3(this.mContext, 49, 0, this.mCanBusInfoInt[3]);
            }
            break;
         case 8:
            if (this.isLauncher3()) {
               this.realKeyClick3(this.mContext, 47, 0, this.mCanBusInfoInt[3]);
            } else {
               if (this.mCanBusInfoInt[3] == 0) {
                  return;
               }

               this.realKeyClick4(this.mContext, 8);
            }
            break;
         case 9:
            if (this.isLauncher3()) {
               this.realKeyClick3(this.mContext, 48, 0, this.mCanBusInfoInt[3]);
            } else {
               if (this.mCanBusInfoInt[3] == 0) {
                  return;
               }

               this.realKeyClick4(this.mContext, 7);
            }
            break;
         default:
            switch (var1) {
               case 19:
                  this.realKeyClick(45);
                  break;
               case 20:
                  this.realKeyClick(46);
                  break;
               case 21:
                  this.realKeyLongClick(65301);
                  break;
               case 22:
                  this.realKeyClick(8);
                  break;
               case 23:
                  this.realKeyClick(7);
                  break;
               case 24:
                  this.realKeyClick(3);
            }
      }

   }

   private void realKeyClick(int var1) {
      int var2 = this.mCanBusInfoInt[3];
      if (var2 == 1 || var2 == 2) {
         this.realKeyClick4(this.mContext, var1);
      }

   }

   private void realKeyLongClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void rearRadar0x22() {
      int var1 = this.getLeftAndRightRadar(this.mCanBusInfoInt[2]);
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1, var2[3], var2[4], this.getLeftAndRightRadar(var2[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (31 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)(var1 - 1) * 0.5F + 18.0F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void set0x37AutoParkVoice(Context var1) {
      if (!TextUtils.isEmpty(mLanguage) && !TextUtils.isEmpty((CharSequence)this.mAutoParkVoiceArray.get(this.mCanBusInfoInt[2]))) {
         String var2 = "voice/_306" + mLanguage + (String)this.mAutoParkVoiceArray.get(this.mCanBusInfoInt[2]);
         Log.i("_306_MsgMgr", "set0x37AutoParkVoice: fileName \"" + var2 + "\"");
         this.enterNoSource();
         this.mAutoParkVoiceManger.play(var1, var2);
      }

   }

   private void set360camera0x35() {
      int var1 = this.mCanBusInfoInt[2];
      this.getMyPanoramicView().refreshUi(var1);
   }

   static void setLanguage(Context var0) {
      String var1 = var0.getResources().getConfiguration().getLocales().get(0).getCountry();
      Log.i("_306_MsgMgr", "setLanguage: country " + var1);
      if (var1.endsWith("CN")) {
         mLanguage = "_zh";
      } else {
         mLanguage = "_en";
      }

      Log.i("_306_MsgMgr", "setLanguage: mLanguage " + mLanguage);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void tpmsInfo0x25() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new TireUpdateEntity(0, 0, new String[]{var3.append(var2[3] + var2[2] * 256).append("KPA").toString()}));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      var1.add(new TireUpdateEntity(1, 0, new String[]{var3.append(var2[5] + var2[4] * 256).append("KPA").toString()}));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(new TireUpdateEntity(2, 0, new String[]{var4.append(var5[7] + var5[6] * 256).append("KPA").toString()}));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      var1.add(new TireUpdateEntity(3, 0, new String[]{var3.append(var2[9] + var2[8] * 256).append("KPA").toString()}));
      GeneralTireData.dataList = var1;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void trackInfo0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, -540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void voice0x36() {
      if (this.mCanBusInfoInt[2] == 1) {
         CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.ORIGAUX, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
      } else {
         CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.ORIGAUX, SourceConstantsDef.DISP_ID.disp_main);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      GeneralTireData.isHaveSpareTire = false;
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 36, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 37, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 40, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 50, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 53, 0});
      this.languageReceiver = new LanguageReceiver();
      IntentFilter var2 = new IntentFilter();
      var2.addAction("android.intent.action.LOCALE_CHANGED");
      var1.registerReceiver(this.languageReceiver, var2);
      this.initAutoParkVoice();
   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 1});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 40) {
         if (var3 != 41) {
            if (var3 != 48) {
               switch (var3) {
                  case 32:
                     this.keyEvent0x20();
                     break;
                  case 33:
                     this.panelEvent0x21();
                     break;
                  case 34:
                     this.rearRadar0x22();
                     break;
                  case 35:
                     this.frontRadar0x23();
                     break;
                  case 36:
                     this.carInfo0x24();
                     break;
                  case 37:
                     this.tpmsInfo0x25();
                     break;
                  default:
                     switch (var3) {
                        case 51:
                           this.carSettingInfo0x33();
                           break;
                        case 52:
                           this.carSettingInfo0x34();
                           break;
                        case 53:
                           this.set360camera0x35();
                           break;
                        case 54:
                           this.voice0x36();
                           break;
                        case 55:
                           this.set0x37AutoParkVoice(var1);
                           break;
                        case 56:
                           if (SharePreUtil.getIntValue(this.mContext, this.ID306_AVM_CONGIF_TAG, 0) == 0) {
                              this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
                                 final MsgMgr this$0;

                                 {
                                    this.this$0 = var1;
                                 }

                                 public void callback() {
                                    this.this$0.getMyPanoramicView().avmCanBusInfoChange(this.this$0.mContext, this.this$0.mCanBusInfoInt);
                                 }
                              });
                           }
                     }
               }
            } else {
               this.setVersionInfo();
            }
         } else {
            this.trackInfo0x29();
         }
      } else {
         if (this.getUiMgr().isDX7()) {
            return;
         }

         if (this.isAirMsgRepeat(var2)) {
            return;
         }

         this.airInfo0x28();
      }

   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 != 65301) {
         return false;
      } else {
         this.realKeyClick(var1, 134);
         return true;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      hour = var5;
      minute = var6;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var5, (byte)var6, (byte)LanguageReceiver.getLanguage(this.mContext)});
   }

   public void destroyCommand() {
      super.destroyCommand();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
      this.mContext.unregisterReceiver(this.languageReceiver);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void showDialogAndRestartApp(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$content;

         {
            this.this$0 = var1;
            this.val$content = var2;
         }

         public void callback() {
            (new SetAlertView()).showDialog(this.this$0.getActivity(), this.val$content);
            (new CountDownTimer(this, 3000L, 1000L) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onFinish() {
                  System.exit(0);
               }

               public void onTick(long var1) {
               }
            }).start();
         }
      });
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
