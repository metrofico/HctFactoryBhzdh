package com.hzbhd.canbus.car._286;

import android.content.Context;
import android.os.Bundle;
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
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static final int RADAR_PARAM_FORNT_MID_MAX = 120;
   private static final int RADAR_PARAM_MID_RANGE = 10;
   private static final int RADAR_PARAM_REAR_MID_MAX = 165;
   private static final int RADAR_PARAM_SIDE_MAX = 60;
   private static final int RADAR_PARAM_SIDE_RANGE = 5;
   DecimalFormat df_2Integer = new DecimalFormat("00");
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x27Data;
   private int[] m0x29Data;
   private int[] m0x30Data;
   private int[] m0x3AData;
   private int[] m0x40Data;
   private int[] m0x66Data;
   private int[] m0x68Data;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private DecimalFormat mDecimalFormat;
   private int mDiiferentId;
   private int mEachId;
   private boolean mFrontStatus;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private ID3[] mMusicId3s;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private SparseArray mSettingItemSpareArray;
   private int mSettingLeftIndex;
   private int mSettingRightIndex;
   private String[] mTireInfoArray;
   private TirePageUiSet mTirePageUiSet;
   private List mTireResolveList;
   private List mTireUpdateEntityList;
   private String[] mTireWarningArray;
   private UiMgr mUiMgr;

   private int getBandData(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else if ("FM3".equals(var1)) {
         return 3;
      } else if ("AM1".equals(var1)) {
         return 17;
      } else {
         return "AM2".equals(var1) ? 18 : 0;
      }
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

   private String getItem(int var1) {
      return (String)this.mSettingItemSpareArray.get(var1, "null_value");
   }

   private void getLeftAndRight(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         int var2 = (Integer)this.mSettingItemIndeHashMap.get(var1);
         this.mSettingLeftIndex = var2 >> 8;
         this.mSettingRightIndex = var2 & 255;
         Log.i("ljq", "getLeftAndRigth: left:" + this.mSettingLeftIndex + ", right:" + this.mSettingRightIndex);
      } else {
         this.mSettingLeftIndex = -1;
         this.mSettingRightIndex = -1;
      }

   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private TirePageUiSet getTirePageUiSet() {
      if (this.mTirePageUiSet == null) {
         this.mTirePageUiSet = this.getUiMgr().getTireUiSet(this.mContext);
      }

      return this.mTirePageUiSet;
   }

   private UiMgr getUiMgr() {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

      return this.mUiMgr;
   }

   private void initID3() {
      this.mMusicId3s = new ID3[0];
   }

   private void initSettingItemIndexSparseArray() {
      SparseArray var1 = new SparseArray();
      this.mSettingItemSpareArray = var1;
      var1.append(5, "_286_settings_item_5");
      this.mSettingItemSpareArray.append(6, "_286_settings_item_6");
      this.mSettingItemSpareArray.append(7, "_286_settings_item_7");
      this.mSettingItemSpareArray.append(8, "_286_settings_item_8");
      this.mSettingItemSpareArray.append(9, "_286_settings_item_9");
      this.mSettingItemSpareArray.append(10, "_286_settings_item_a");
      this.mSettingItemSpareArray.append(11, "_286_settings_item_b");
      this.mSettingItemSpareArray.append(12, "_286_settings_item_c");
      this.mSettingItemSpareArray.append(13, "_286_settings_item_d");
      this.mSettingItemSpareArray.append(14, "_286_settings_item_e");
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

   private void initTireResolveTool() {
      this.mTireUpdateEntityList = new ArrayList();

      for(int var1 = 0; var1 < 4; ++var1) {
         this.mTireUpdateEntityList.add(new TireUpdateEntity(var1, 0, new String[0]));
      }

      ArrayList var2 = new ArrayList();
      this.mTireResolveList = var2;
      var2.add(new TireResolve(this, "bar", 0.1F));
      this.mTireResolveList.add(new TireResolve(this, "psi", 0.5F));
      this.mTireResolveList.add(new TireResolve(this, "kPa", 10.0F));
      this.mTireInfoArray = new String[4];
      this.mTireWarningArray = new String[4];
   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x27DataChange() {
      if (Arrays.equals(this.m0x27Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x27Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x29DataChange() {
      if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x29Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x30DataChange() {
      if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x30Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x3ADataChange() {
      if (Arrays.equals(this.m0x3AData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x3AData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x40DataChange() {
      if (Arrays.equals(this.m0x40Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x40Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x66DataChange() {
      if (Arrays.equals(this.m0x66Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x66Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x68DataChange() {
      if (Arrays.equals(this.m0x68Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x68Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
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

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private void reportID3Info(int var1, ID3[] var2, boolean var3) {
      (new Thread(this, var2, var3, var1) {
         final MsgMgr this$0;
         final ID3[] val$id3s;
         final boolean val$isClean;
         final int val$source;

         {
            this.this$0 = var1;
            this.val$id3s = var2;
            this.val$isClean = var3;
            this.val$source = var4;
         }

         public void run() {
            super.run();

            Exception var10000;
            label64: {
               int var3;
               ID3[] var4;
               boolean var10001;
               try {
                  var4 = this.val$id3s;
                  var3 = var4.length;
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break label64;
               }

               byte var2 = 0;
               int var1 = 0;

               while(true) {
                  if (var1 >= var3) {
                     return;
                  }

                  try {
                     if (var4[var1].isId3Change()) {
                        if (this.val$isClean) {
                           sleep(900L);
                        }
                        break;
                     }
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label64;
                  }

                  ++var1;
               }

               try {
                  var4 = this.val$id3s;
                  var3 = var4.length;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label64;
               }

               var1 = var2;

               while(true) {
                  if (var1 >= var3) {
                     return;
                  }

                  ID3 var5 = var4[var1];

                  try {
                     sleep(100L);
                     this.this$0.reportID3InfoFinal(this.val$source, var5.line, var5.info);
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                     break;
                  }

                  ++var1;
               }
            }

            Exception var10 = var10000;
            var10.printStackTrace();
         }
      }).start();
   }

   private void reportID3InfoFinal(int var1, int var2, String var3) throws UnsupportedEncodingException {
      byte[] var4 = DataHandleUtils.exceptBOMHead(var3.getBytes("utf-8"));
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte)var1, -1, 18, (byte)var2, (byte)var4.length}, var4));
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 31) {
         return "HI";
      } else if (var1 >= 1 && var1 <= 28) {
         return GeneralAirData.fahrenheit_celsius ? var1 + 59 + this.getTempUnitF(this.mContext) : (float)(var1 + 31) / 2.0F + this.getTempUnitC(this.mContext);
      } else {
         return "";
      }
   }

   private int resolveFrontMidRadarData(int var1) {
      return (int)((float)var1 / 120.0F * 10.0F) + 1;
   }

   private int resolveRearMidRadarData(int var1) {
      return (int)((float)var1 / 165.0F * 10.0F) + 1;
   }

   private int resolveSideRadarData(int var1) {
      return (int)((float)var1 / 60.0F * 5.0F) + 1;
   }

   private void set0x01WheelKey(Context var1) {
      int[] var2 = this.mCanBusInfoInt;
      switch (var2[2]) {
         case 0:
            this.realKeyLongClick1(var1, 0, var2[3]);
            break;
         case 1:
            this.realKeyLongClick1(var1, 2, var2[3]);
            break;
         case 2:
            this.realKeyLongClick1(var1, 46, var2[3]);
            break;
         case 3:
            this.realKeyLongClick1(var1, 45, var2[3]);
            break;
         case 4:
            this.realKeyLongClick1(var1, 7, var2[3]);
            break;
         case 5:
            this.realKeyLongClick1(var1, 8, var2[3]);
            break;
         case 6:
            this.realKeyLongClick1(var1, 3, var2[3]);
            break;
         case 7:
            this.realKeyLongClick1(var1, 14, var2[3]);
            break;
         case 8:
            this.realKeyLongClick1(var1, 15, var2[3]);
      }

   }

   private void setAirData0x21() {
      byte[] var3 = this.mCanBusInfoByte;
      var3[3] = (byte)DataHandleUtils.setIntByteWithBit(var3[3], 4, false);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         boolean var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         boolean var2 = true;
         GeneralAirData.in_out_cycle = var1 ^ true;
         var1 = var2;
         if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
               var1 = var2;
            } else {
               var1 = false;
            }
         }

         GeneralAirData.auto = var1;
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setDoorDatat0x24() {
      if (this.is0x3ADataChange()) {
         boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         this.mRightFrontRec = var1;
         GeneralDoorData.isRightFrontDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         this.mLeftFrontRec = var1;
         GeneralDoorData.isLeftFrontDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         this.mRightRearRec = var1;
         GeneralDoorData.isRightRearDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mLeftRearRec = var1;
         GeneralDoorData.isLeftRearDoorOpen = var1;
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         if (this.isDoorDataChange()) {
            this.updateDoorView(this.mContext);
         }
      }

   }

   private void setFrontRadarData0x23() {
      if (this.is0x23DataChange()) {
         RadarInfoUtil.setFrontRadarLocationDataType2(5, this.resolveSideRadarData(this.mCanBusInfoInt[2]), 10, this.resolveFrontMidRadarData(this.mCanBusInfoInt[3]), 10, this.resolveFrontMidRadarData(this.mCanBusInfoInt[4]), 5, this.resolveSideRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setOutDoorTemperatureData0x27() {
      if (this.is0x27DataChange()) {
         int[] var2 = this.mCanBusInfoInt;
         short var1 = (short)(var2[4] << 8 | var2[3]);
         if (var1 >= -580 && var1 <= 1710) {
            var1 = (short)(var1 / 10);
            if (DataHandleUtils.getBoolBit0(var2[2])) {
               this.updateOutDoorTemp(this.mContext, var1 + this.getTempUnitF(this.mContext));
            } else {
               this.updateOutDoorTemp(this.mContext, var1 + this.getTempUnitC(this.mContext));
            }
         }
      }

   }

   private void setRearRadarData0x22() {
      if (this.is0x22DataChange()) {
         RadarInfoUtil.setRearRadarLocationDataType2(5, this.resolveSideRadarData(this.mCanBusInfoInt[2]), 10, this.resolveRearMidRadarData(this.mCanBusInfoInt[3]), 10, this.resolveRearMidRadarData(this.mCanBusInfoInt[4]), 5, this.resolveSideRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setTireInfo0x66() {
      if (this.is0x66DataChange()) {
         TireResolve var2 = (TireResolve)this.mTireResolveList.get(this.mCanBusInfoInt[7]);

         for(int var1 = 0; var1 < this.mTireUpdateEntityList.size(); ++var1) {
            ArrayList var3 = new ArrayList();
            this.mTireInfoArray[var1] = this.mDecimalFormat.format((double)((float)this.mCanBusInfoInt[var1 + 3] * var2.calculate)) + var2.unit;
            var3.add(this.mTireInfoArray[var1]);
            var3.add(this.mTireWarningArray[var1]);
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setList(var3);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setTireWarning0x68() {
      if (this.is0x68DataChange()) {
         for(int var1 = 0; var1 < this.mTireUpdateEntityList.size(); ++var1) {
            int var3 = this.mCanBusInfoInt[2];
            byte var2 = 1;
            if ((var3 & 1 << var1) == 0) {
               var2 = 0;
            }

            ArrayList var4 = new ArrayList();
            var4.add(this.mTireInfoArray[var1]);
            String[] var5 = this.mTireWarningArray;
            var5[var1] = " ";
            if (var2 != 0) {
               var3 = this.mCanBusInfoInt[3];
               if (var3 >= 2 && var3 <= 4) {
                  var5[var1] = CommUtil.getStrByResId(this.mContext, "_286_tire_warning_" + this.mCanBusInfoInt[3]);
               }
            }

            var4.add(this.mTireWarningArray[var1]);
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setList(var4);
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setTireStatus(var2);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setTrackData0x29() {
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 19918, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleData0x40() {
      Log.i("ljq", "setVehicleData0x40: " + Arrays.toString(this.mCanBusInfoInt));
      if (this.is0x40DataChange()) {
         int var2 = this.mCanBusInfoByte[2];
         StringBuilder var7;
         int[] var8;
         if (var2 != 1) {
            if (var2 != 2) {
               int[] var5;
               if (var2 != 3) {
                  if (var2 == 4) {
                     StringBuilder var6 = new StringBuilder();
                     var5 = this.mCanBusInfoInt;
                     var2 = var5[3];
                     this.updateDriverData(0, 4, var6.append(var5[4] | var2 << 8).append(" km/h").toString());
                  }
               } else {
                  var5 = this.mCanBusInfoInt;
                  var2 = var5[3];
                  float var1 = (float)(var5[4] | var2 << 8) / 10.0F;
                  this.updateDriverData(0, 3, var1 + " l/100km");
               }
            } else {
               var7 = new StringBuilder();
               var8 = this.mCanBusInfoInt;
               var2 = var8[3];
               this.updateDriverData(0, 2, var7.append(var8[4] | var2 << 8).append(" km").toString());
            }
         } else {
            var7 = new StringBuilder();
            var8 = this.mCanBusInfoInt;
            var2 = var8[3];
            this.updateDriverData(0, 1, var7.append(var8[4] | var2 << 8).append(" ").append(CommUtil.getStrByResId(this.mContext, "unit_hour")).toString());
         }

         this.getLeftAndRight(this.getItem(this.mCanBusInfoByte[2]));
         int var3 = this.mSettingLeftIndex;
         if (var3 != -1) {
            var2 = this.mSettingRightIndex;
            if (var2 != -1) {
               int var4;
               switch (this.mCanBusInfoByte[2]) {
                  case 5:
                  case 9:
                  case 10:
                  case 11:
                  case 12:
                  case 13:
                  case 14:
                     this.updateSettingData(var3, var2, this.mCanBusInfoInt[3]);
                     break;
                  case 6:
                     var4 = this.mCanBusInfoInt[3];
                     this.updateSettingData(var3, var2, var4 * 10, var4 - 1);
                     break;
                  case 7:
                  case 8:
                     var4 = this.mCanBusInfoInt[3];
                     this.updateSettingData(var3, var2, var4 * 10, var4);
               }
            }
         }
      }

   }

   private void setVehicleSpeed0x16() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = (var2[3] << 8 | var2[2]) / 16;
      String var4;
      if (DataHandleUtils.getBoolBit0(var2[4])) {
         var4 = "mph";
      } else {
         var4 = "km/h";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var1 + " " + var4));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
         this.updateSpeedInfo(Integer.parseInt(this.df_2Integer.format((double)var1 * 1.6)));
      } else {
         this.updateSpeedInfo(Integer.parseInt(this.df_2Integer.format((long)var1)));
      }

   }

   private void setVersionInfo0x30() {
      if (this.is0x30DataChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick1(0);
            break;
         case 1:
            this.realKeyClick1(7);
            break;
         case 2:
            this.realKeyClick1(8);
            break;
         case 3:
            this.realKeyClick1(20);
            break;
         case 4:
            this.realKeyClick1(21);
            break;
         case 5:
            this.realKeyClick1(14);
            break;
         case 6:
            this.realKeyClick1(3);
            break;
         case 7:
            this.realKeyClick1(2);
            break;
         case 8:
            this.realKeyClick1(187);
            break;
         case 9:
            this.realKeyClick1(15);
      }

   }

   private void updateDriverData(int var1, int var2, String var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(var1, var2, var3));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void updateSettingData(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void updateSettingData(int var1, int var2, int var3, int var4) {
      ArrayList var5 = new ArrayList();
      var5.add((new SettingUpdateEntity(var1, var2, var3)).setProgress(var4));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 34, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 32, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(1, 6, var3), 5, var2), 4, true)});
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -54, 1, 1}, var1), 15, 32));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(2, 6, var3), 5, var2), 4, true)});
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -54, 1, 1}, var1), 15, 32));
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      int[] var5 = this.getTime(var4);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, (byte)var5[1], (byte)var5[2], 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(4, 6, var3), 5, var2), 4, true)});
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -54, 1, 1}, var1), 15, 32));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 22) {
            if (var3 != 39) {
               if (var3 != 41) {
                  if (var3 != 48) {
                     if (var3 != 64) {
                        if (var3 != 102) {
                           if (var3 != 104) {
                              switch (var3) {
                                 case 32:
                                    this.setWheelKey0x20();
                                    break;
                                 case 33:
                                    this.setAirData0x21();
                                    break;
                                 case 34:
                                    this.setRearRadarData0x22();
                                    break;
                                 case 35:
                                    this.setFrontRadarData0x23();
                                    break;
                                 case 36:
                                    this.setDoorDatat0x24();
                              }
                           } else {
                              this.setTireWarning0x68();
                           }
                        } else {
                           this.setTireInfo0x66();
                        }
                     } else {
                        this.setVehicleData0x40();
                     }
                  } else {
                     this.setVersionInfo0x30();
                  }
               } else {
                  this.setTrackData0x29();
               }
            } else {
               this.setOutDoorTemperatureData0x27();
            }
         } else {
            this.setVehicleSpeed0x16();
         }
      } else {
         this.set0x01WheelKey(var1);
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

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(DataHandleUtils.rangeNumber(var1, 0, 30) | var3)});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      short var14;
      if (var10) {
         var14 = 0;
      } else {
         var14 = 128;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var2, (byte)var3, (byte)var4, (byte)(var5 | var14), (byte)var6, (byte)var7, (byte)(var9 - 1)});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1 || var7 == 5) {
         var4 = var5;
      }

      int[] var18 = this.getTime(var2);
      byte var16 = (byte)var4;
      byte var17 = (byte)var6;
      byte var14 = (byte)var18[0];
      var1 = (byte)var18[1];
      byte var15 = (byte)var18[2];
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, var16, var17, var14, var1, var15});
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -64, 10, 0, 0, 0, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.mDiiferentId = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      this.initSettingItemIndexSparseArray();
      this.initSettingsItem(this.getUiMgr().getSettingUiSet(var1));
      RadarInfoUtil.mDisableData = 255;
      RadarInfoUtil.mMinIsClose = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralTireData.isHaveSpareTire = false;
      this.initID3();
      this.initTireResolveTool();
      this.mDecimalFormat = new DecimalFormat("0.0");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 9;
      } else {
         var25 = 8;
      }

      var1 = (byte)var25;
      var2 = (byte)var3;
      var6 = (byte)(var4 & 255);
      var5 = (byte)(var4 >> 8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, var1, 18, var2, var9, var6, var5, 0, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = this.getBandData(var2);
      int[] var10 = this.getFreqByteHiLo(var2, var3);
      byte var8 = (byte)var5;
      byte var7 = (byte)var10[1];
      byte var6 = (byte)var10[0];
      byte var9 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, var8, var7, var6, var9, 0, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 9;
      } else {
         var18 = 8;
      }

      var2 = (byte)var18;
      var5 = (byte)var3;
      var1 = (byte)(var4 & 255);
      var6 = (byte)(var4 >> 8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, var2, 18, var5, var9, var1, var6, 0, 0});
   }

   private class ID3 {
      private String info;
      private int line;
      private String record;
      final MsgMgr this$0;

      private ID3(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.line = var2;
         this.info = "";
         this.record = "";
      }

      private boolean isId3Change() {
         if (this.record.equals(this.info)) {
            return false;
         } else {
            this.record = this.info;
            return true;
         }
      }
   }

   private class TireResolve {
      float calculate;
      final MsgMgr this$0;
      String unit;

      public TireResolve(MsgMgr var1, String var2, float var3) {
         this.this$0 = var1;
         this.unit = var2;
         this.calculate = var3;
      }
   }
}
