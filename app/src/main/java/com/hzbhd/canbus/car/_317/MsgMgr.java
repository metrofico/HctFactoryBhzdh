package com.hzbhd.canbus.car._317;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u001e\u0018\u0000 F2\u00020\u0001:\u0001FB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u001c\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000bH\u0016Jp\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\u0006H\u0016J\u001a\u0010-\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\u00152\u0006\u0010.\u001a\u00020\u0004H\u0002J\u0012\u0010/\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u00100\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u00101\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0006\u00102\u001a\u00020)J\u0006\u00103\u001a\u00020)J\u001a\u00104\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u00105\u001a\u00020\u0006H\u0002J\u001a\u00106\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u00107\u001a\u00020\u0006H\u0002J\u0012\u00108\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u00109\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010:\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010;\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010<\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010=\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010>\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010?\u001a\u00020\u001aH\u0002J\u0012\u0010@\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010A\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010B\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010C\u001a\u00020\u001a2\u0006\u0010D\u001a\u00020)H\u0016J\u0016\u0010E\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"},
   d2 = {"Lcom/hzbhd/canbus/car/_317/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "TAG", "", "mCanId", "", "mCanbusDataArray", "Landroid/util/SparseArray;", "", "mCanbusInfoByte", "", "mCanbusInfoInt", "mContext", "Landroid/content/Context;", "mDecimalFormat0p0", "Ljava/text/DecimalFormat;", "mDifferentId", "mEachId", "mSettingItemIndeHashMap", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_317/UiMgr;", "afterServiceNormalSetting", "", "context", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "getSettingUpdateEntity", "title", "initAmplifier", "initCommand", "initSettingsItem", "is2016PajeroSport", "is2020PajeroSport", "realKeyLongClick1", "key", "resolveAirTemperature", "value", "set0x1DFrontRadarData", "set0x1ERearRadarData", "set0x20WheelKeyData", "set0x24BaseData", "set0x29TrackData", "set0x30VersionData", "set0x35VehicleData", "set0x40Vehicle2Data", "set0x41AmplifierData", "set0x55AirData", "set0x60TireData", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettingItem", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_POSITION_OFFSET = 11;
   public static final int AMPLIFIER_PROGRESS_OFFSET = 2;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String SHARE_317_LEFT_RIGHT_HAND = "share_317_left_right_hand";
   public static final int VEHICLE_TYPE_2016_PAJERO_SPORT = 2;
   public static final int VEHICLE_TYPE_2020_PAJERO_SPORT = 1;
   private final String TAG = "_317_MsgMgr";
   private int mCanId;
   private SparseArray mCanbusDataArray = new SparseArray();
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private Context mContext;
   private final DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
   private int mDifferentId;
   private int mEachId;
   private HashMap mSettingItemIndeHashMap = new HashMap();
   private UiMgr mUiMgr;

   private final SettingUpdateEntity getSettingUpdateEntity(String var1) {
      HashMap var2 = this.mSettingItemIndeHashMap;
      Intrinsics.checkNotNull(var2);
      SettingUpdateEntity var3;
      if (var2.containsKey(var1)) {
         var2 = this.mSettingItemIndeHashMap;
         Intrinsics.checkNotNull(var2);
         var3 = (SettingUpdateEntity)var2.get(var1);
      } else {
         var2 = this.mSettingItemIndeHashMap;
         Intrinsics.checkNotNull(var2);
         ((Map)var2).put(var1, new SettingUpdateEntity(-1, -1, (Object)null));
         var3 = this.getSettingUpdateEntity(var1);
      }

      return var3;
   }

   private final void initAmplifier(Context var1) {
      int var8;
      if (var1 != null) {
         var8 = SharePreUtil.getIntValue(var1, "share_317_left_right_hand", 0);
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      } else {
         var8 = 0;
      }

      byte[] var9 = new byte[]{22, -127, 1, 0};
      byte var3 = (byte)this.mEachId;
      byte var2 = (byte)var8;
      byte[] var11 = new byte[]{22, -18, var2, 0};
      byte[] var12 = new byte[]{22, -124, 1, (byte)(GeneralAmplifierData.frontRear + 11)};
      byte var6 = (byte)(GeneralAmplifierData.leftRight + 11);
      byte var4 = (byte)(GeneralAmplifierData.bandBass + 2);
      byte[] var10 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandTreble + 2)};
      byte var7 = (byte)(GeneralAmplifierData.bandMiddle + 2);
      byte var5 = (byte)GeneralAmplifierData.frontRear;
      Iterator var13 = ArrayIteratorKt.iterator((Object[])(new byte[][]{var9, {22, -30, var3}, {22, -18, var2, 0}, var11, {22, -18, var2, 0}, {22, -124, 9, 1}, var12, {22, -124, 2, var6}, {22, -124, 4, var4}, var10, {22, -124, 6, var7}, {22, -124, 8, var5}}));
      TimerUtil var14 = new TimerUtil();
      var14.startTimer((TimerTask)(new TimerTask(var13, var14) {
         final Iterator $this_run;
         final TimerUtil $this_run$1;

         {
            this.$this_run = var1;
            this.$this_run$1 = var2;
         }

         public void run() {
            if (this.$this_run.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.$this_run.next());
            } else {
               this.$this_run$1.stopTimer();
            }

         }
      }), 0L, 100L);
   }

   private final void initSettingsItem(Context var1) {
      Log.i(this.TAG, "initSettingsItem: ");
      UiMgr var4 = this.mUiMgr;
      Intrinsics.checkNotNull(var4);
      SettingPageUiSet var7 = var4.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var7, "mUiMgr!!.getSettingUiSet(context)");
      Iterator var8 = var7.getList().iterator();

      for(int var2 = 0; var8.hasNext(); ++var2) {
         Iterator var9 = ((SettingPageUiSet.ListBean)var8.next()).getItemList().iterator();

         for(int var3 = 0; var9.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var9.next();
            HashMap var5 = this.mSettingItemIndeHashMap;
            Intrinsics.checkNotNull(var5);
            Map var10 = (Map)var5;
            String var11 = var6.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var11, "itemListBean.titleSrn");
            var10.put(var11, new SettingUpdateEntity(var2, var3));
         }
      }

   }

   private final void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanbusInfoInt[3]);
   }

   private final String resolveAirTemperature(Context var1, int var2) {
      String var5;
      if (var2 == 16) {
         var5 = "LO";
      } else if (var2 == 80) {
         var5 = "HI";
      } else {
         boolean var4 = false;
         boolean var3 = var4;
         if (30 <= var2) {
            var3 = var4;
            if (var2 < 65) {
               var3 = true;
            }
         }

         if (var3) {
            var5 = "" + (float)var2 / 2.0F + ' ' + this.getTempUnitC(var1);
         } else {
            var5 = "";
         }
      }

      return var5;
   }

   private final void set0x1DFrontRadarData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanbusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(7, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private final void set0x1ERearRadarData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanbusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(7, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private final void set0x20WheelKeyData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         switch (this.mCanbusInfoInt[2]) {
            case 0:
               this.realKeyLongClick1(var1, 0);
               break;
            case 1:
               this.realKeyLongClick1(var1, 7);
               break;
            case 2:
               this.realKeyLongClick1(var1, 8);
               break;
            case 3:
               this.realKeyLongClick1(var1, 46);
               break;
            case 4:
               this.realKeyLongClick1(var1, 45);
            case 5:
            case 6:
            default:
               break;
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
      }

   }

   private final void set0x24BaseData(Context var1) {
      int[] var2 = this.mCanbusInfoInt;
      var2[2] &= 249;
      var2[3] &= 16;
      if (this.isDataChange(var2)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[3]);
         this.updateDoorView(var1);
      }

   }

   private final void set0x29TrackData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         byte[] var2 = this.mCanbusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[2], (byte)(var2[3] & 127), 0, 360, 12);
         Log.i(this.TAG, "set0x29TrackData: track " + GeneralParkData.trackAngle);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private final void set0x30VersionData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanbusInfoByte));
   }

   private final void set0x35VehicleData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         Log.i(this.TAG, "set0x35VehicleData: " + var1);
      }

      this.updateSpeedInfo(this.mCanbusInfoInt[4]);
   }

   private final void set0x40Vehicle2Data() {
      if (this.is2016PajeroSport() && this.isDataChange(this.mCanbusInfoInt)) {
         String var1;
         if (this.mCanbusInfoInt[5] == 255) {
            var1 = "";
         } else {
            var1 = "" + (float)this.mCanbusInfoInt[5] / 10.0F + 'V';
         }

         this.updateGeneralDriveData((List)CollectionsKt.arrayListOf(new DriverUpdateEntity[]{new DriverUpdateEntity(0, 0, var1)}));
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private final void set0x41AmplifierData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         GeneralAmplifierData.frontRear = this.mCanbusInfoInt[2] - 11;
         GeneralAmplifierData.leftRight = this.mCanbusInfoInt[3] - 11;
         GeneralAmplifierData.bandBass = this.mCanbusInfoInt[5] - 2;
         GeneralAmplifierData.bandTreble = this.mCanbusInfoInt[6] - 2;
         GeneralAmplifierData.bandMiddle = this.mCanbusInfoInt[7] - 2;
         GeneralAmplifierData.volume = this.mCanbusInfoInt[9];
         this.updateAmplifierActivity((Bundle)null);
         this.saveAmplifierData(var1, this.mCanId);
         SettingUpdateEntity var9 = this.getSettingUpdateEntity("outlander_simple_car_set_17");
         Intrinsics.checkNotNull(var9);
         var9 = var9.setValue(this.mCanbusInfoInt[4]);
         SettingUpdateEntity var2 = this.getSettingUpdateEntity("_103_punch");
         Intrinsics.checkNotNull(var2);
         var2 = var2.setValue(this.mCanbusInfoInt[8] - 2 - 3);
         Intrinsics.checkNotNull(var2);
         var2 = var2.setProgress(this.mCanbusInfoInt[8] - 2);
         SettingUpdateEntity var3 = this.getSettingUpdateEntity("outlander_simple_car_set_8");
         Intrinsics.checkNotNull(var3);
         var3 = var3.setValue(this.mCanbusInfoInt[10]);
         SettingUpdateEntity var4 = this.getSettingUpdateEntity("outlander_simple_car_set_9");
         Intrinsics.checkNotNull(var4);
         var4 = var4.setValue(this.mCanbusInfoInt[11]);
         SettingUpdateEntity var5 = this.getSettingUpdateEntity("outlander_simple_car_set_10");
         Intrinsics.checkNotNull(var5);
         var5 = var5.setValue(this.mCanbusInfoInt[12]);
         SettingUpdateEntity var6 = this.getSettingUpdateEntity("outlander_simple_car_set_11");
         Intrinsics.checkNotNull(var6);
         var6 = var6.setValue(this.mCanbusInfoInt[13]);
         SettingUpdateEntity var7 = this.getSettingUpdateEntity("outlander_simple_car_set_12");
         Intrinsics.checkNotNull(var7);
         SettingUpdateEntity var8 = var7.setValue(this.mCanbusInfoInt[14]);
         var7 = this.getSettingUpdateEntity("amplifier_switch");
         Intrinsics.checkNotNull(var7);
         this.updateGeneralSettingData((List)CollectionsKt.arrayListOf(new SettingUpdateEntity[]{var9, var2, var3, var4, var5, var6, var8, var7.setValue(this.mCanbusInfoInt[15])}));
         this.updateSettingActivity((Bundle)null);
      }

   }

   private final void set0x55AirData(Context var1) {
      boolean var3 = this.is2020PajeroSport();
      Integer var5 = 5;
      boolean var4 = false;
      if (!var3) {
         this.mCanbusInfoInt[5] = 0;
      }

      if (this.isDataChange(this.mCanbusInfoInt)) {
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(var1, this.mCanbusInfoInt[2]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(var1, this.mCanbusInfoInt[3]);
         GeneralAirData.front_wind_level = this.mCanbusInfoInt[4] & 15;
         int var2 = this.mCanbusInfoInt[4] >>> 4 & 15;
         if (var2 == 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.front_left_auto_wind = var3;
         GeneralAirData.front_left_blow_head = ArraysKt.contains(new Integer[]{1, 2, var5, 7}, var2);
         GeneralAirData.front_left_blow_foot = ArraysKt.contains(new Integer[]{2, 3, 4, 7}, var2);
         GeneralAirData.front_left_blow_window = ArraysKt.contains(new Integer[]{4, 6, var5, 7}, var2);
         if ((this.mCanbusInfoInt[5] >>> 2 & 1) == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.rear = var3;
         if ((this.mCanbusInfoInt[6] >>> 0 & 1) == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.auto = var3;
         if ((this.mCanbusInfoInt[6] >>> 1 & 1) == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.ac = var3;
         if ((this.mCanbusInfoInt[6] >>> 2 & 1) == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.sync = var3;
         if ((this.mCanbusInfoInt[6] >>> 3 & 1) == 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.in_out_cycle = var3;
         if ((this.mCanbusInfoInt[6] >>> 6 & 1) == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.rear_defog = var3;
         var3 = var4;
         if ((this.mCanbusInfoInt[6] >>> 7 & 1) == 1) {
            var3 = true;
         }

         GeneralAirData.max_front = var3;
         GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         this.updateAirActivity(var1, 1001);
      }

   }

   private final void set0x60TireData(Context var1) {
      if (this.is2016PajeroSport() && this.isDataChange(this.mCanbusInfoInt) && this.mCanbusInfoInt[2] > 0) {
         int[] var4 = this.mCanbusInfoInt;
         int var3 = var4[2];
         int var2 = var4[4];
         String var5 = "";
         String var7;
         if (var2 == 255) {
            var7 = "";
         } else {
            var7 = this.mDecimalFormat0p0.format((float)this.mCanbusInfoInt[4] / 2.94F) + " PSI";
         }

         String var6;
         if (this.mCanbusInfoInt[5] == 255) {
            var6 = var5;
         } else {
            var6 = "" + (this.mCanbusInfoInt[5] - 50) + ' ' + this.getTempUnitC(var1);
         }

         GeneralTireData.dataList = (List)CollectionsKt.arrayListOf(new TireUpdateEntity[]{new TireUpdateEntity(var3 - 1, 0, new String[]{var7, var6, "" + (float)this.mCanbusInfoInt[6] / 4.0F * (float)100 + '%'})});
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._317.UiMgr");
      this.mUiMgr = (UiMgr)var2;
      this.initSettingsItem(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.mCanbusInfoInt = var4;
      Intrinsics.checkNotNull(var2);
      this.mCanbusInfoByte = var2;
      int var3 = this.mCanbusInfoInt[1];
      if (var3 != 29) {
         if (var3 != 30) {
            if (var3 != 32) {
               if (var3 != 36) {
                  if (var3 != 41) {
                     if (var3 != 53) {
                        if (var3 != 85) {
                           if (var3 != 96) {
                              if (var3 != 48 && var3 != 49) {
                                 if (var3 != 64) {
                                    if (var3 == 65) {
                                       this.set0x41AmplifierData(var1);
                                    }
                                 } else {
                                    this.set0x40Vehicle2Data();
                                 }
                              } else {
                                 this.set0x30VersionData(var1);
                              }
                           } else {
                              this.set0x60TireData(var1);
                           }
                        } else {
                           this.set0x55AirData(var1);
                        }
                     } else {
                        this.set0x35VehicleData(var1);
                     }
                  } else {
                     this.set0x29TrackData(var1);
                  }
               } else {
                  this.set0x24BaseData(var1);
               }
            } else {
               this.set0x20WheelKeyData(var1);
            }
         } else {
            this.set0x1ERearRadarData(var1);
         }
      } else {
         this.set0x1DFrontRadarData(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      switch (this.mEachId) {
         case 0:
            Log.i(this.TAG, "initCommand: 13 款欧蓝德高配");
            break;
         case 1:
            Log.i(this.TAG, "initCommand: 17 款欧蓝德高配");
            break;
         case 2:
            Log.i(this.TAG, "initCommand: 16 款帕杰罗劲畅高配");
            break;
         case 3:
            Log.i(this.TAG, "initCommand: 13 款欧蓝德低配");
            break;
         case 4:
            Log.i(this.TAG, "initCommand: 17 款欧蓝德低配");
            break;
         case 5:
            Log.i(this.TAG, "initCommand: 16 款帕杰罗劲畅低配");
            break;
         case 6:
            Log.i(this.TAG, "initCommand: 帕杰罗 V97 高配（空调控制为可选功能）");
            break;
         case 7:
            Log.i(this.TAG, "initCommand: 帕杰罗 V97 低配（空调控制为可选功能）");
            break;
         case 8:
            Log.i(this.TAG, "initCommand: 20 款帕杰罗劲畅高配");
            break;
         case 9:
            Log.i(this.TAG, "initCommand: 20 款帕杰罗劲畅低配");
      }

      this.initAmplifier(var1);
   }

   public final boolean is2016PajeroSport() {
      boolean var1;
      if (this.mDifferentId == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean is2020PajeroSport() {
      int var1 = this.mDifferentId;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public final void updateSettingItem(String var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "value");
      SettingUpdateEntity var3 = this.getSettingUpdateEntity(var1);
      if (var3 != null) {
         var3 = var3.setValue(var2);
      } else {
         var3 = null;
      }

      this.updateGeneralSettingData((List)CollectionsKt.arrayListOf(new SettingUpdateEntity[]{var3}));
      this.updateSettingActivity((Bundle)null);
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"},
      d2 = {"Lcom/hzbhd/canbus/car/_317/MsgMgr$Companion;", "", "()V", "AMPLIFIER_POSITION_OFFSET", "", "AMPLIFIER_PROGRESS_OFFSET", "SHARE_317_LEFT_RIGHT_HAND", "", "VEHICLE_TYPE_2016_PAJERO_SPORT", "VEHICLE_TYPE_2020_PAJERO_SPORT", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }
}
