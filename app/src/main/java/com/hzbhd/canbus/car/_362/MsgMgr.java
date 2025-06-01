package com.hzbhd.canbus.car._362;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\n\n\u0002\b\u0012\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010 \u001a\u00020\u001fH\u0002J\u001c\u0010!\u001a\u00020\u001f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020'H\u0002J\b\u0010+\u001a\u00020\u001fH\u0002J\b\u0010,\u001a\u00020\u001fH\u0002J\b\u0010-\u001a\u00020\u001fH\u0002J\b\u0010.\u001a\u00020\u001fH\u0002J\b\u0010/\u001a\u00020\u001fH\u0002J\b\u00100\u001a\u00020\u001fH\u0002J\b\u00101\u001a\u00020\u001fH\u0002J\b\u00102\u001a\u00020\u001fH\u0002J\b\u00103\u001a\u00020\u001fH\u0002J\u0006\u00104\u001a\u00020\u001fJ\b\u00105\u001a\u00020\u001fH\u0002J\b\u00106\u001a\u00020\u001fH\u0002J\b\u00107\u001a\u00020\u001fH\u0002J\b\u00108\u001a\u00020\u001fH\u0002J\b\u00109\u001a\u00020\u001fH\u0002J\b\u0010:\u001a\u00020\u001fH\u0002J\u0013\u0010;\u001a\u0004\u0018\u00010<*\u00020\u0013H\u0002¢\u0006\u0002\u0010=R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u0016\u001a&\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0012j\u0012\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006>"},
   d2 = {"Lcom/hzbhd/canbus/car/_362/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastDeviceStatus", "", "mDrivingItemIndex", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "mSettingItemIndex", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "mUiMgr", "Lcom/hzbhd/canbus/car/_362/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_362/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_362/UiMgr;)V", "afterServiceNormalSetting", "", "airConditioningInfo", "canbusInfoChange", "canbusInfo", "", "carSpeedInfo", "corneringLampInfo", "culTrackAngle", "", "track", "", "max", "drivingModeAndAssistanceInfo", "electricPSInfo", "essentialInfo", "frontRadarInfo", "fuelConsumptionPerMin", "historicalFuelConsumption", "instantaneousFuelConsumption", "powerAmplifierInfo", "rearRadarInfo", "returnClick", "seatHeatingStatus", "steeringWheelKeys", "systemInfo", "tirePMSInfo", "vehicleSettings", "vehicleSpeedInfo", "toDoubleOrStringSelf", "", "(Ljava/lang/String;)Ljava/lang/Double;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frame;
   private boolean lastDeviceStatus;
   private HashMap mDrivingItemIndex = new HashMap();
   private HashMap mSettingItemIndex = new HashMap();
   public UiMgr mUiMgr;

   private final void airConditioningInfo() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.getFrame()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.getFrame()[2]);
      GeneralAirData.negative_ion = DataHandleUtils.getBoolBit4(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
      GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit0(this.getFrame()[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 4);
      int var2 = this.getFrame()[4];
      String var4 = "--";
      boolean var1;
      String var3;
      if (var2 == 0) {
         var3 = "LO";
      } else if (var2 == 31) {
         var3 = "HI";
      } else {
         if (1 <= var2 && var2 < 30) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            var3 = String.valueOf((double)18 + (double)(this.getFrame()[4] - 1) * 0.5);
         } else {
            if (33 <= var2 && var2 < 39) {
               var1 = true;
            } else {
               var1 = false;
            }

            if (var1) {
               var3 = String.valueOf((double)15 + (double)(this.getFrame()[4] - 33) * 0.5);
            } else {
               var3 = "--";
            }
         }
      }

      GeneralAirData.front_left_temperature = var3;
      var2 = this.getFrame()[5];
      if (var2 == 0) {
         var3 = "LO";
      } else if (var2 == 31) {
         var3 = "HI";
      } else {
         if (1 <= var2 && var2 < 30) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            var3 = String.valueOf((double)18 + (double)(this.getFrame()[5] - 1) * 0.5);
         } else {
            if (33 <= var2 && var2 < 39) {
               var1 = true;
            } else {
               var1 = false;
            }

            var3 = var4;
            if (var1) {
               var3 = String.valueOf((double)15 + (double)(this.getFrame()[5] - 33) * 0.5);
            }
         }
      }

      GeneralAirData.front_right_temperature = var3;
      GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit7(this.getFrame()[6]);
      Double var5;
      if (DataHandleUtils.getBoolBit0(this.getFrame()[6])) {
         var3 = GeneralAirData.front_left_temperature;
         Intrinsics.checkNotNullExpressionValue(var3, "front_left_temperature");
         var5 = StringsKt.toDoubleOrNull(var3);
         if (var5 != null) {
            GeneralAirData.front_left_temperature = MsgMgrKt.transToF(var5);
         }

         var3 = GeneralAirData.front_right_temperature;
         Intrinsics.checkNotNullExpressionValue(var3, "front_right_temperature");
         var5 = StringsKt.toDoubleOrNull(var3);
         if (var5 != null) {
            GeneralAirData.front_right_temperature = MsgMgrKt.transToF(var5);
         }
      } else {
         var3 = GeneralAirData.front_left_temperature;
         Intrinsics.checkNotNullExpressionValue(var3, "front_left_temperature");
         var5 = StringsKt.toDoubleOrNull(var3);
         if (var5 != null) {
            GeneralAirData.front_left_temperature = MsgMgrKt.transToC(var5);
         }

         var3 = GeneralAirData.front_right_temperature;
         Intrinsics.checkNotNullExpressionValue(var3, "front_right_temperature");
         var5 = StringsKt.toDoubleOrNull(var3);
         if (var5 != null) {
            GeneralAirData.front_right_temperature = MsgMgrKt.transToC(var5);
         }
      }

      this.updateAirActivity(this.getContext(), 1001);
   }

   private final void carSpeedInfo() {
      int var1 = DataHandleUtils.getMsbLsbResult(this.getFrame()[3], this.getFrame()[2]);
      int var2 = this.getFrame()[4];
      Object var3 = this.mDrivingItemIndex.get("D362_carSpeedInfo_1");
      Intrinsics.checkNotNull(var3);
      ((DriverDataPageUiSet.Page.Item)var3).setValue(var1 + " rp");
      var3 = this.mSettingItemIndex.get("S362_carSpeedInfo_1");
      Intrinsics.checkNotNull(var3);
      SettingPageUiSet.ListBean.ItemListBean var4 = (SettingPageUiSet.ListBean.ItemListBean)var3;
      var4.setProgress(var2 / 10);
      String var5;
      if (var4.getProgress() == 0) {
         var5 = "OFF";
      } else {
         var5 = var4.getProgress() + " s";
      }

      var4.setValue(var5);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSettingActivity((Bundle)null);
   }

   private final void corneringLampInfo() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.getFrame()[2]);
      boolean var2 = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      Object var3 = this.mDrivingItemIndex.get("D362_corneringLampInfo_2");
      Intrinsics.checkNotNull(var3);
      DriverDataPageUiSet.Page.Item var5 = (DriverDataPageUiSet.Page.Item)var3;
      String var4 = "ON";
      String var6;
      if (var1) {
         var6 = "ON";
      } else {
         var6 = "OFF";
      }

      var5.setValue(var6);
      var3 = this.mDrivingItemIndex.get("D362_corneringLampInfo_1");
      Intrinsics.checkNotNull(var3);
      var5 = (DriverDataPageUiSet.Page.Item)var3;
      if (var2) {
         var6 = var4;
      } else {
         var6 = "OFF";
      }

      var5.setValue(var6);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final int culTrackAngle(short var1, int var2) {
      float var3 = (float)var2 / (float)25;
      return DataHandleUtils.rangeNumber((int)((float)var1 / var3), -25, 25);
   }

   private final void drivingModeAndAssistanceInfo() {
      byte var1 = DataHandleUtils.getBoolBit7(this.getFrame()[2]);
      int var3 = this.getFrame()[2];
      int var2 = 0;
      var3 = DataHandleUtils.getIntFromByteWithBit(var3, 0, 2);
      byte var7 = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
      byte var8 = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      byte var5 = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      byte var4 = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      byte var6 = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      Object var9;
      SettingPageUiSet.ListBean.ItemListBean var12;
      if (var1 != 0) {
         var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
         Intrinsics.checkNotNull(var9);
         if (((SettingPageUiSet.ListBean.ItemListBean)var9).getSelectIndex() == 2) {
            var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
            Intrinsics.checkNotNull(var9);
            var12 = (SettingPageUiSet.ListBean.ItemListBean)var9;
            var12.setValue(var12.getValueSrnArray().get(0));
         }

         var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
         Intrinsics.checkNotNull(var9);
         ((SettingPageUiSet.ListBean.ItemListBean)var9).getValueSrnArray().remove("S362_drivingModeAndAssistanceInfo_2_2");
      } else {
         var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
         Intrinsics.checkNotNull(var9);
         if (!((SettingPageUiSet.ListBean.ItemListBean)var9).getValueSrnArray().contains("S362_drivingModeAndAssistanceInfo_2_2")) {
            var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
            Intrinsics.checkNotNull(var9);
            ((SettingPageUiSet.ListBean.ItemListBean)var9).getValueSrnArray().add("S362_drivingModeAndAssistanceInfo_2_2");
         }
      }

      var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_1");
      Intrinsics.checkNotNull(var9);
      ((SettingPageUiSet.ListBean.ItemListBean)var9).setValue(Integer.valueOf(var1));
      var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
      Intrinsics.checkNotNull(var9);
      SettingPageUiSet.ListBean.ItemListBean var10 = (SettingPageUiSet.ListBean.ItemListBean)var9;
      List var13 = var10.getValueSrnArray();
      boolean var11;
      if (var3 >= 0 && var3 < 3) {
         var11 = true;
      } else {
         var11 = false;
      }

      if (var11) {
         var2 = var3;
      }

      var10.setValue(var13.get(var2));
      var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_3");
      Intrinsics.checkNotNull(var9);
      ((SettingPageUiSet.ListBean.ItemListBean)var9).setValue(Integer.valueOf(var7));
      var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_4");
      Intrinsics.checkNotNull(var9);
      ((SettingPageUiSet.ListBean.ItemListBean)var9).setValue(Integer.valueOf(var8));
      var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_5");
      Intrinsics.checkNotNull(var9);
      ((SettingPageUiSet.ListBean.ItemListBean)var9).setValue(Integer.valueOf(var5));
      var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_6");
      Intrinsics.checkNotNull(var9);
      var12 = (SettingPageUiSet.ListBean.ItemListBean)var9;
      var12.setValue(var12.getValueSrnArray().get(var4));
      var9 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_7");
      Intrinsics.checkNotNull(var9);
      ((SettingPageUiSet.ListBean.ItemListBean)var9).setValue(Integer.valueOf(var6));
      this.updateSettingActivity((Bundle)null);
   }

   private final void electricPSInfo() {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[2], (byte)this.getFrame()[3], 0, 380, 12);
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void essentialInfo() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      this.updateDoorView(this.getContext());
   }

   private final void frontRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(4, this.getFrame()[2], this.getFrame()[3], this.getFrame()[4], this.getFrame()[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void fuelConsumptionPerMin() {
      int var2 = this.getFrame()[2];
      int var3 = this.getFrame()[3];
      int var5 = this.getFrame()[4] * 256 + this.getFrame()[5];
      int var6 = this.getFrame()[6];
      int var4 = this.getFrame()[7];
      int var1 = this.getFrame()[8];
      String var8;
      if (var1 != 1) {
         if (var1 != 2) {
            var8 = "None";
         } else {
            var8 = "Km";
         }
      } else {
         var8 = "Mile";
      }

      Object var9 = this.mDrivingItemIndex.get("D362_fuelConsumptionPerMin_1");
      Intrinsics.checkNotNull(var9);
      DriverDataPageUiSet.Page.Item var11 = (DriverDataPageUiSet.Page.Item)var9;
      boolean var7 = Intrinsics.areEqual((Object)var8, (Object)"None");
      String var10 = "----";
      String var12;
      if (var7) {
         var12 = "----";
      } else {
         var12 = "" + (var2 * 256 + var3) / 10 + ' ' + var8;
      }

      var11.setValue(var12);
      var9 = this.mDrivingItemIndex.get("D362_fuelConsumptionPerMin_2");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var5 / 60 + " : " + var5 % 60);
      var9 = this.mDrivingItemIndex.get("D362_fuelConsumptionPerMin_3");
      Intrinsics.checkNotNull(var9);
      DriverDataPageUiSet.Page.Item var13 = (DriverDataPageUiSet.Page.Item)var9;
      if (Intrinsics.areEqual((Object)var8, (Object)"None")) {
         var8 = var10;
      } else {
         var8 = "" + (var6 * 256 + var4) / 10 + ' ' + var8;
      }

      var13.setValue(var8);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void historicalFuelConsumption() {
      int var2 = this.getFrame()[2];
      int var9 = this.getFrame()[3];
      int var1 = this.getFrame()[4];
      int var7 = this.getFrame()[5];
      int var10 = this.getFrame()[6];
      int var11 = this.getFrame()[7];
      int var6 = this.getFrame()[8];
      int var4 = this.getFrame()[9];
      int var12 = this.getFrame()[10];
      int var13 = this.getFrame()[11];
      int var8 = this.getFrame()[12];
      int var3 = this.getFrame()[13];
      int var5 = this.getFrame()[14];
      String var14;
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               var14 = "None";
            } else {
               var14 = "L/100Km";
            }
         } else {
            var14 = "Km/L";
         }
      } else {
         var14 = "MPG";
      }

      Object var15 = this.mDrivingItemIndex.get("D361_2_d1t2");
      Intrinsics.checkNotNull(var15);
      ((DriverDataPageUiSet.Page.Item)var15).setValue("" + (var9 * 256 + var1) / 10 + ' ' + var14);
      var15 = this.mDrivingItemIndex.get("D361_2_d3t4");
      Intrinsics.checkNotNull(var15);
      ((DriverDataPageUiSet.Page.Item)var15).setValue("" + (var7 * 256 + var10) / 10 + ' ' + var14);
      var15 = this.mDrivingItemIndex.get("D361_2_d5t6");
      Intrinsics.checkNotNull(var15);
      ((DriverDataPageUiSet.Page.Item)var15).setValue("" + (var11 * 256 + var6) / 10 + ' ' + var14);
      var15 = this.mDrivingItemIndex.get("D361_2_d7t8");
      Intrinsics.checkNotNull(var15);
      ((DriverDataPageUiSet.Page.Item)var15).setValue("" + (var4 * 256 + var12) / 10 + ' ' + var14);
      var15 = this.mDrivingItemIndex.get("D361_2_d9t10");
      Intrinsics.checkNotNull(var15);
      ((DriverDataPageUiSet.Page.Item)var15).setValue("" + (var13 * 256 + var8) / 10 + ' ' + var14);
      var15 = this.mDrivingItemIndex.get("D361_2_d11t12");
      Intrinsics.checkNotNull(var15);
      ((DriverDataPageUiSet.Page.Item)var15).setValue("" + (var3 * 256 + var5) / 10 + ' ' + var14);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void instantaneousFuelConsumption() {
      int var1 = this.getFrame()[2];
      int var2 = this.getFrame()[3];
      int var3 = this.getFrame()[4];
      String var4;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var4 = "None";
            } else {
               var4 = "L/100Km";
            }
         } else {
            var4 = "Km/L";
         }
      } else {
         var4 = "MPG";
      }

      Object var5 = this.mDrivingItemIndex.get("D361_1_d1t2");
      Intrinsics.checkNotNull(var5);
      ((DriverDataPageUiSet.Page.Item)var5).setValue("" + (var2 * 256 + var3) / 10 + ' ' + var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void powerAmplifierInfo() {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 4);
      byte var1 = 0;
      GeneralAmplifierData.frontRear = MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(var2, 0, 14), 0, 2, (Object)null) - 7;
      GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 4), 0, 14) - 7;
      GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 4), 2, 12) - 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(this.getFrame()[5], 0, 63);
      this.updateAmplifierActivity((Bundle)null);
      Object var4 = this.mSettingItemIndex.get("S362_powerAmplifierInfo_1");
      Intrinsics.checkNotNull(var4);
      SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var4;
      if (DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 4) == 8) {
         var1 = 1;
      }

      var7.setValue(Integer.valueOf(var1));
      var4 = this.mDrivingItemIndex.get("D362_powerAmplifierInfo_1");
      Intrinsics.checkNotNull(var4);
      DriverDataPageUiSet.Page.Item var6 = (DriverDataPageUiSet.Page.Item)var4;
      boolean var3 = DataHandleUtils.getBoolBit0(this.getFrame()[6]);
      String var5 = "有";
      String var8;
      if (var3) {
         var8 = "有";
      } else {
         var8 = "无";
      }

      var6.setValue(var8);
      var4 = this.mDrivingItemIndex.get("D362_powerAmplifierInfo_2");
      Intrinsics.checkNotNull(var4);
      var6 = (DriverDataPageUiSet.Page.Item)var4;
      if (DataHandleUtils.getBoolBit1(this.getFrame()[6])) {
         var8 = var5;
      } else {
         var8 = "无";
      }

      var6.setValue(var8);
      this.updateSettingActivity((Bundle)null);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void rearRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(4, this.getFrame()[2], this.getFrame()[3], this.getFrame()[4], this.getFrame()[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
      byte var3 = DataHandleUtils.getBoolBit7(this.getFrame()[6]);
      byte var5 = DataHandleUtils.getBoolBit6(this.getFrame()[6]);
      byte var1 = DataHandleUtils.getBoolBit5(this.getFrame()[6]);
      byte var4 = DataHandleUtils.getBoolBit4(this.getFrame()[6]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 0, 3);
      Object var6 = this.mSettingItemIndex.get("S362_rearRadarInfo_1");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var3));
      var6 = this.mSettingItemIndex.get("S362_rearRadarInfo_2");
      Intrinsics.checkNotNull(var6);
      SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var7.setValue(var7.getValueSrnArray().get(var5));
      var6 = this.mSettingItemIndex.get("S362_rearRadarInfo_3");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var1));
      var6 = this.mSettingItemIndex.get("S362_rearRadarInfo_4");
      Intrinsics.checkNotNull(var6);
      var7 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var7.setValue(var7.getValueSrnArray().get(var4));
      var6 = this.mSettingItemIndex.get("S362_rearRadarInfo_5");
      Intrinsics.checkNotNull(var6);
      var7 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var7.setProgress(var2);
      var7.setValue(String.valueOf(var7.getProgress()));
      this.updateSettingActivity((Bundle)null);
   }

   private final void seatHeatingStatus() {
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 3);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 3);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 4, 3);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 3);
      this.updateAirActivity(this.getContext(), 1001);
   }

   private final void steeringWheelKeys() {
      int var2 = this.getFrame()[3];
      int var1 = this.getFrame()[2];
      if (var1 != 0) {
         label125: {
            if (var1 != 1) {
               if (var1 == 2) {
                  break label125;
               }

               if (var1 == 3) {
                  this.realKeyLongClick1(this.getContext(), 48, var2);
                  return;
               }

               if (var1 == 4) {
                  this.realKeyLongClick1(this.getContext(), 47, var2);
                  return;
               }

               if (var1 != 129) {
                  if (var1 != 130) {
                     label127: {
                        switch (var1) {
                           case 7:
                              break;
                           case 8:
                              this.realKeyLongClick1(this.getContext(), 187, var2);
                              return;
                           case 9:
                              this.realKeyLongClick1(this.getContext(), 14, var2);
                              return;
                           case 10:
                              this.realKeyLongClick1(this.getContext(), 15, var2);
                              return;
                           default:
                              label45:
                              switch (var1) {
                                 case 19:
                                    break label127;
                                 case 21:
                                    this.realKeyLongClick1(this.getContext(), 50, var2);
                                    return;
                                 case 22:
                                    this.realKeyLongClick1(this.getContext(), 49, var2);
                                    return;
                                 default:
                                    switch (var1) {
                                       case 133:
                                          break label127;
                                       case 134:
                                          break;
                                       case 135:
                                          this.realKeyLongClick1(this.getContext(), 134, var2);
                                          return;
                                       case 136:
                                          break label45;
                                       default:
                                          return;
                                    }
                                 case 20:
                                    this.realKeyLongClick1(this.getContext(), 46, var2);
                                    return;
                              }
                        }

                        this.realKeyLongClick1(this.getContext(), 2, var2);
                        return;
                     }

                     this.realKeyLongClick1(this.getContext(), 45, var2);
                     return;
                  }
                  break label125;
               }
            }

            this.realKeyLongClick1(this.getContext(), 7, var2);
            return;
         }

         this.realKeyLongClick1(this.getContext(), 8, var2);
      } else {
         this.realKeyLongClick1(this.getContext(), 0, var2);
      }

   }

   private final void systemInfo() {
      boolean var6 = DataHandleUtils.getBoolBit0(this.getFrame()[2]);
      boolean var4 = DataHandleUtils.getBoolBit1(this.getFrame()[2]);
      boolean var3 = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
      boolean var5 = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      boolean var1 = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      boolean var2 = DataHandleUtils.getBoolBit7(this.getFrame()[2]);
      Object var7 = this.mDrivingItemIndex.get("_350_d_3_0");
      Intrinsics.checkNotNull(var7);
      DriverDataPageUiSet.Page.Item var8 = (DriverDataPageUiSet.Page.Item)var7;
      String var9;
      if (var6) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDrivingItemIndex.get("_350_d_3_1");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var4) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDrivingItemIndex.get("_350_d_3_2");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var3) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDrivingItemIndex.get("_350_d_3_3");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var5) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDrivingItemIndex.get("_350_d_3_4");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var1) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_1");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_0");
      }

      var8.setValue(var9);
      var7 = this.mDrivingItemIndex.get("_350_d_3_5");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var2) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_1");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_0");
      }

      var8.setValue(var9);
      this.updateDriveDataActivity((Bundle)null);
      if (var5) {
         this.forceReverse(this.getContext(), true);
      } else {
         this.forceReverse(this.getContext(), false);
      }

   }

   private final void tirePMSInfo() {
      boolean var3 = DataHandleUtils.getBoolBit7(this.getFrame()[2]);
      if (this.lastDeviceStatus != var3) {
         this.lastDeviceStatus = var3;
         if (!var3) {
            this.getMUiMgr().getMainUiSet(this.getContext()).getBtnAction().remove("tire_info");
         } else if (!this.getMUiMgr().getMainUiSet(this.getContext()).getBtnAction().contains("tire_info")) {
            this.getMUiMgr().getMainUiSet(this.getContext()).getBtnAction().add("tire_info");
         }
      }

      byte var1 = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      var3 = DataHandleUtils.getBoolBit5(this.getFrame()[2]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 2);
      String var4;
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               var4 = "None";
            } else {
               var4 = "2.5 KPA";
            }
         } else {
            var4 = "PSI";
         }
      } else {
         var4 = "0.1 BAR";
      }

      GeneralTireData.isHaveSpareTire = var3;
      List var5 = (List)(new ArrayList());
      var5.add(new TireUpdateEntity(0, var1, new String[]{String.valueOf(this.getFrame()[3]), var4}));
      var5.add(new TireUpdateEntity(1, var1, new String[]{String.valueOf(this.getFrame()[4]), var4}));
      var5.add(new TireUpdateEntity(2, var1, new String[]{String.valueOf(this.getFrame()[5]), var4}));
      var5.add(new TireUpdateEntity(3, var1, new String[]{String.valueOf(this.getFrame()[6]), var4}));
      if (GeneralTireData.isHaveSpareTire) {
         var5.add(new TireUpdateEntity(4, var1, new String[]{String.valueOf(this.getFrame()[7]), var4}));
      }

      GeneralTireData.dataList = var5;
      this.updateTirePressureActivity((Bundle)null);
   }

   private final Double toDoubleOrStringSelf(String var1) {
      return StringsKt.toDoubleOrNull(var1);
   }

   private final void vehicleSettings() {
      byte var4 = DataHandleUtils.getBoolBit7(this.getFrame()[2]);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 3);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 2, 2);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 2);
      Object var6 = this.mSettingItemIndex.get("S361_d0b7");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var4));
      var6 = this.mSettingItemIndex.get("S361_d0b6t4");
      Intrinsics.checkNotNull(var6);
      SettingPageUiSet.ListBean.ItemListBean var10 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var10.setProgress(var3);
      var10.setValue(String.valueOf(var10.getProgress()));
      var6 = this.mSettingItemIndex.get("S361_d0b2t3");
      Intrinsics.checkNotNull(var6);
      var10 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var10.setValue(var10.getValueSrnArray().get(var1));
      var6 = this.mSettingItemIndex.get("S361_d0b0t1");
      Intrinsics.checkNotNull(var6);
      var10 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var10.setValue(var10.getValueSrnArray().get(var2));
      var4 = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
      byte var8 = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      byte var7 = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      byte var5 = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 3);
      var6 = this.mSettingItemIndex.get("S361_d1b7");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var4));
      var6 = this.mSettingItemIndex.get("S361_d1b6");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var8));
      var6 = this.mSettingItemIndex.get("S361_d1b5");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var7));
      var6 = this.mSettingItemIndex.get("S361_d1b4");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var5));
      var6 = this.mSettingItemIndex.get("S361_d1b0t2");
      Intrinsics.checkNotNull(var6);
      var10 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var10.setProgress(var3);
      var10.setValue(String.valueOf(var10.getProgress()));
      var5 = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      var8 = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      byte var9 = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      var4 = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      var7 = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      var6 = this.mSettingItemIndex.get("S361_d2b7");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var5));
      var6 = this.mSettingItemIndex.get("S361_d2b6");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var8));
      var6 = this.mSettingItemIndex.get("S361_d2b5");
      Intrinsics.checkNotNull(var6);
      var10 = (SettingPageUiSet.ListBean.ItemListBean)var6;
      var10.setValue(var10.getValueSrnArray().get(var9));
      var6 = this.mSettingItemIndex.get("S362_vehicleSettings_1");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var4));
      var6 = this.mSettingItemIndex.get("S362_vehicleSettings_2");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var7));
      var7 = DataHandleUtils.getBoolBit7(this.getFrame()[5]);
      var8 = DataHandleUtils.getBoolBit6(this.getFrame()[5]);
      var6 = this.mSettingItemIndex.get("S362_vehicleSettings_3");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var7));
      var6 = this.mSettingItemIndex.get("S362_vehicleSettings_4");
      Intrinsics.checkNotNull(var6);
      ((SettingPageUiSet.ListBean.ItemListBean)var6).setValue(Integer.valueOf(var8));
      this.updateSettingActivity((Bundle)null);
   }

   private final void vehicleSpeedInfo() {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[3], this.getFrame()[2]) / 16);
      int var2 = DataHandleUtils.getMsbLsbResult(this.getFrame()[3], this.getFrame()[2]) / 16;
      int var1 = this.getFrame()[4];
      Object var3 = this.mDrivingItemIndex.get("D362_vehicleSpeedInfo_1");
      Intrinsics.checkNotNull(var3);
      ((DriverDataPageUiSet.Page.Item)var3).setValue(var2 + " Km/H");
      var3 = this.mSettingItemIndex.get("S362_vehicleSpeedInfo_1");
      Intrinsics.checkNotNull(var3);
      SettingPageUiSet.ListBean.ItemListBean var4 = (SettingPageUiSet.ListBean.ItemListBean)var3;
      var4.setProgress(var1 / 10);
      String var5;
      if (var4.getProgress() == 0) {
         var5 = "OFF";
      } else {
         var5 = var4.getProgress() + " s";
      }

      var4.setValue(var5);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._362.UiMgr");
      this.setMUiMgr((UiMgr)var2);
      GeneralDoorData.isSubShowSeatBelt = true;
      InitUtilsKt.initDrivingItemsIndexHashMap(var1, (AbstractUiMgr)this.getMUiMgr(), this.mDrivingItemIndex);
      InitUtilsKt.initSettingItemsIndexHashMap(var1, (AbstractUiMgr)this.getMUiMgr(), this.mSettingItemIndex);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      if (var1 != null && var2 != null) {
         this.setContext(var1);
         int[] var4 = this.getByteArrayToIntArray(var2);
         Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
         this.setFrame(var4);
         int var3 = this.getFrame()[1];
         if (var3 != 22) {
            if (var3 != 53) {
               if (var3 != 80) {
                  if (var3 != 29) {
                     if (var3 != 30) {
                        if (var3 != 40) {
                           if (var3 != 41) {
                              if (var3 != 49) {
                                 if (var3 != 50) {
                                    if (var3 != 82) {
                                       if (var3 != 83) {
                                          switch (var3) {
                                             case 32:
                                                this.steeringWheelKeys();
                                                break;
                                             case 33:
                                                this.fuelConsumptionPerMin();
                                                break;
                                             case 34:
                                                this.instantaneousFuelConsumption();
                                                break;
                                             case 35:
                                                this.historicalFuelConsumption();
                                                break;
                                             case 36:
                                                this.essentialInfo();
                                                break;
                                             case 37:
                                                this.tirePMSInfo();
                                                break;
                                             case 38:
                                                this.vehicleSettings();
                                          }
                                       } else {
                                          this.drivingModeAndAssistanceInfo();
                                       }
                                    } else {
                                       this.corneringLampInfo();
                                    }
                                 } else {
                                    this.systemInfo();
                                 }
                              } else {
                                 this.powerAmplifierInfo();
                              }
                           } else {
                              this.electricPSInfo();
                           }
                        } else {
                           this.airConditioningInfo();
                        }
                     } else {
                        this.rearRadarInfo();
                     }
                  } else {
                     this.frontRadarInfo();
                  }
               } else {
                  this.carSpeedInfo();
               }
            } else {
               this.seatHeatingStatus();
            }
         } else {
            this.vehicleSpeedInfo();
         }
      }

   }

   public final Context getContext() {
      Context var1 = this.context;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("context");
         return null;
      }
   }

   public final int[] getFrame() {
      int[] var1 = this.frame;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("frame");
         return null;
      }
   }

   public final UiMgr getMUiMgr() {
      UiMgr var1 = this.mUiMgr;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         return null;
      }
   }

   public final void returnClick() {
      this.startMainActivity(this.getContext());
   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public final void setMUiMgr(UiMgr var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mUiMgr = var1;
   }
}
