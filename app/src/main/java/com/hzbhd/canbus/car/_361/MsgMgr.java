package com.hzbhd.canbus.car._361;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u001d\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\"\u001a\u00020#2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010$\u001a\u00020#H\u0002J\b\u0010%\u001a\u00020#H\u0016J\b\u0010&\u001a\u00020#H\u0002J\b\u0010'\u001a\u00020#H\u0002JT\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u00020*2\u0006\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u001c\u00105\u001a\u00020#2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u00106\u001a\u0004\u0018\u00010,H\u0016J\b\u00107\u001a\u00020#H\u0002J\b\u00108\u001a\u00020#H\u0002J\b\u00109\u001a\u00020#H\u0002J\b\u0010:\u001a\u00020#H\u0002J\b\u0010;\u001a\u00020#H\u0002J\b\u0010<\u001a\u00020#H\u0002J\u0010\u0010=\u001a\u00020#2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\b\u0010>\u001a\u00020#H\u0002JÄ\u0001\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020A2\u0006\u0010F\u001a\u00020A2\u0006\u0010G\u001a\u00020A2\u0006\u0010H\u001a\u00020A2\u0006\u0010I\u001a\u00020A2\u0006\u0010J\u001a\u00020A2\b\u0010K\u001a\u0004\u0018\u00010\u00112\b\u0010L\u001a\u0004\u0018\u00010\u00112\b\u0010M\u001a\u0004\u0018\u00010\u00112\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020A2\u0006\u0010Q\u001a\u00020*2\u0006\u0010R\u001a\u00020\u001d2\u0006\u0010S\u001a\u00020O2\b\u0010T\u001a\u0004\u0018\u00010\u00112\b\u0010U\u001a\u0004\u0018\u00010\u00112\b\u0010V\u001a\u0004\u0018\u00010\u00112\u0006\u0010W\u001a\u00020\u001dH\u0016J\b\u0010X\u001a\u00020#H\u0002J\b\u0010Y\u001a\u00020#H\u0002J\b\u0010Z\u001a\u00020#H\u0002J6\u0010[\u001a\u00020#2\u0006\u0010\\\u001a\u00020*2\b\u0010]\u001a\u0004\u0018\u00010\u00112\b\u0010^\u001a\u0004\u0018\u00010\u00112\b\u0010_\u001a\u0004\u0018\u00010\u00112\u0006\u0010`\u001a\u00020*H\u0016J\b\u0010a\u001a\u00020#H\u0002J\b\u0010b\u001a\u00020#H\u0002J\b\u0010c\u001a\u00020#H\u0002J\u0006\u0010d\u001a\u00020#J\"\u0010e\u001a\u00020#2\u0006\u0010f\u001a\u00020*2\u0006\u0010g\u001a\u00020*2\b\u0010h\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010i\u001a\u00020#H\u0002J\b\u0010j\u001a\u00020#H\u0002J\b\u0010k\u001a\u00020#H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR6\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R>\u0010\u0018\u001a&\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u0010j\u0012\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006l"},
   d2 = {"Lcom/hzbhd/canbus/car/_361/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frameData", "", "getFrameData", "()[I", "setFrameData", "([I)V", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "getMDriveItemIndexHashMap", "()Ljava/util/HashMap;", "setMDriveItemIndexHashMap", "(Ljava/util/HashMap;)V", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "getMSettingItemIndexHashMap", "setMSettingItemIndexHashMap", "powerSwitcher", "", "getPowerSwitcher", "()Z", "setPowerSwitcher", "(Z)V", "afterServiceNormalSetting", "", "airConditionerInfo", "auxInInfoChange", "basicInfoOfJourney", "bodyState", "btPhoneStatusInfoChange", "callStatus", "", "phoneNumber", "", "isHfpConnected", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "centralControlEnableBit", "centralCtrlInfo", "drivingInfo", "essentialInfo", "frontRadarInfo", "fuelConsumptionInPast15Min", "initItemsIndexHashMap", "instantaneousFuelConsumptionInfo", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "oilAndElectricHybridInfo", "powerAmplifierIndicationInfo", "powerAmplifierInfo", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "rangeFuelConsumptionInfo", "rearRadarInfo", "rearSeatAirConditioner", "returnClick", "sendTextInfo", "dataType", "date0", "data2_25", "steeringWheelAngle", "steeringWheelKeys", "tirePressureInfo", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frameData;
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private HashMap mSettingItemIndexHashMap = new HashMap();
   private boolean powerSwitcher;

   private final void airConditionerInfo() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.getFrameData()[2]);
      GeneralAirData.small_wind_light = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.getFrameData()[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.getFrameData()[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.getFrameData()[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.getFrameData()[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.getFrameData()[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.getFrameData()[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 0, 4);
      int var2 = this.getFrameData()[4];
      boolean var1;
      String var3;
      if (var2 == 0) {
         var3 = "LO";
      } else if (var2 == 31) {
         var3 = "HI";
      } else {
         if (32 <= var2 && var2 < 36) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            var3 = (double)16 + (double)(this.getFrameData()[4] - 32) * 0.5 + " °C";
         } else {
            if (1 <= var2 && var2 < 30) {
               var1 = true;
            } else {
               var1 = false;
            }

            if (var1) {
               var3 = (double)18 + (double)(this.getFrameData()[4] - 1) * 0.5 + " °C";
            } else {
               var3 = "--";
            }
         }
      }

      GeneralAirData.front_left_temperature = var3;
      var2 = this.getFrameData()[5];
      if (var2 == 0) {
         var3 = "LO";
      } else if (var2 == 31) {
         var3 = "HI";
      } else {
         if (32 <= var2 && var2 < 36) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            var3 = (double)16 + (double)(this.getFrameData()[5] - 32) * 0.5 + " °C";
         } else {
            if (1 <= var2 && var2 < 30) {
               var1 = true;
            } else {
               var1 = false;
            }

            if (var1) {
               var3 = (double)18 + (double)(this.getFrameData()[5] - 1) * 0.5 + " °C";
            } else {
               var3 = "--";
            }
         }
      }

      GeneralAirData.front_right_temperature = var3;
      GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit7(this.getFrameData()[6]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 2, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 0, 2);
      this.updateAirActivity(this.getContext(), 1001);
   }

   private final void basicInfoOfJourney() {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrameData()[2], this.getFrameData()[3]));
      int var2 = this.getFrameData()[2];
      int var7 = this.getFrameData()[3];
      int var3 = this.getFrameData()[4];
      int var1 = this.getFrameData()[5];
      int var4 = this.getFrameData()[6];
      int var6 = this.getFrameData()[7];
      int var5 = this.getFrameData()[6];
      Object var8 = this.mDriveItemIndexHashMap.get("D361_d0t1");
      Intrinsics.checkNotNull(var8);
      ((DriverDataPageUiSet.Page.Item)var8).setValue((float)(var2 * 256 + var7) / 10.0F + " Km/Mile");
      var8 = this.mDriveItemIndexHashMap.get("D361_d2t3");
      Intrinsics.checkNotNull(var8);
      ((DriverDataPageUiSet.Page.Item)var8).setValue(var3 * 256 + var1 + " Min");
      var8 = this.mDriveItemIndexHashMap.get("D361_d4t5");
      Intrinsics.checkNotNull(var8);
      ((DriverDataPageUiSet.Page.Item)var8).setValue(var4 * 256 + var6 + " Km/Mile");
      var8 = this.mDriveItemIndexHashMap.get("D361_d6");
      Intrinsics.checkNotNull(var8);
      DriverDataPageUiSet.Page.Item var9 = (DriverDataPageUiSet.Page.Item)var8;
      String var10;
      if (var5 != 1) {
         if (var5 != 2) {
            var10 = "----";
         } else {
            var10 = "公制 KM";
         }
      } else {
         var10 = "英制 MILE";
      }

      var9.setValue(var10);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void bodyState() {
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.getFrameData()[2]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_1");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 4, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_2");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 3, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_3");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 2, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_4");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 1, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_5");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 0, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_6");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 7, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_7");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 6, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_8");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 5, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_9");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 4, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_10");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 3, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_11");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 7, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_12");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 6, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_13");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 5, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_14");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 4, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_15");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 3, 1)));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D361_State_16");
      if (var1 != null) {
         var1.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 2, 1)));
      }

      this.updateDriveDataActivity((Bundle)null);
      this.updateDoorView(this.getContext());
   }

   private static final String bodyState$getString(MsgMgr var0, int var1) {
      Context var3 = var0.getContext();
      String var2;
      String var4;
      if (var1 == 1) {
         var4 = var3.getString(2131764037);
         var2 = "context.getString(R.string._341_setting_0_0_0)";
      } else {
         var4 = var3.getString(2131764038);
         var2 = "context.getString(R.string._341_setting_0_0_1)";
      }

      Intrinsics.checkNotNullExpressionValue(var4, var2);
      return var4;
   }

   private final void centralControlEnableBit() {
      boolean var5 = DataHandleUtils.getBoolBit0(this.getFrameData()[2]);
      boolean var14 = DataHandleUtils.getBoolBit1(this.getFrameData()[2]);
      boolean var11 = DataHandleUtils.getBoolBit2(this.getFrameData()[2]);
      boolean var13 = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      boolean var12 = DataHandleUtils.getBoolBit4(this.getFrameData()[2]);
      boolean var7 = DataHandleUtils.getBoolBit5(this.getFrameData()[2]);
      boolean var8 = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      boolean var4 = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      boolean var9 = DataHandleUtils.getBoolBit0(this.getFrameData()[3]);
      boolean var1 = DataHandleUtils.getBoolBit1(this.getFrameData()[3]);
      boolean var3 = DataHandleUtils.getBoolBit2(this.getFrameData()[3]);
      boolean var2 = DataHandleUtils.getBoolBit3(this.getFrameData()[3]);
      boolean var17 = DataHandleUtils.getBoolBit4(this.getFrameData()[3]);
      boolean var6 = DataHandleUtils.getBoolBit5(this.getFrameData()[3]);
      boolean var16 = DataHandleUtils.getBoolBit6(this.getFrameData()[3]);
      boolean var15 = DataHandleUtils.getBoolBit7(this.getFrameData()[3]);
      boolean var10 = DataHandleUtils.getBoolBit0(this.getFrameData()[4]);
      boolean var18 = DataHandleUtils.getBoolBit1(this.getFrameData()[4]);
      String var19 = CommUtil.getStrByResId(this.getContext(), "_341_setting_0_0_0");
      Intrinsics.checkNotNullExpressionValue(var19, "getStrByResId(context, \"_341_setting_0_0_0\")");
      String var20 = CommUtil.getStrByResId(this.getContext(), "_341_setting_0_0_1");
      Intrinsics.checkNotNullExpressionValue(var20, "getStrByResId(context, \"_341_setting_0_0_1\")");
      Object var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b0");
      Intrinsics.checkNotNull(var21);
      DriverDataPageUiSet.Page.Item var22 = (DriverDataPageUiSet.Page.Item)var21;
      String var23;
      if (var5) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b1");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var14) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b2");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var11) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b3");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var13) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b4");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var12) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b5");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var7) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b6");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var8) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d0b7");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var4) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b0");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var9) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b1");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var1) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b2");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var3) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b3");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var2) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b4");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var17) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b5");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var6) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b6");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var16) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d1b7");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var15) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d2b0");
      Intrinsics.checkNotNull(var21);
      var22 = (DriverDataPageUiSet.Page.Item)var21;
      if (var10) {
         var23 = var20;
      } else {
         var23 = var19;
      }

      var22.setValue(var23);
      var21 = this.mDriveItemIndexHashMap.get("S361_1_d2b1");
      Intrinsics.checkNotNull(var21);
      DriverDataPageUiSet.Page.Item var24 = (DriverDataPageUiSet.Page.Item)var21;
      if (var18) {
         var19 = var20;
      }

      var24.setValue(var19);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void centralCtrlInfo() {
      byte var4 = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 4, 3);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 2, 2);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 0, 2);
      byte var5 = DataHandleUtils.getBoolBit7(this.getFrameData()[3]);
      byte var15 = DataHandleUtils.getBoolBit6(this.getFrameData()[3]);
      byte var3 = DataHandleUtils.getBoolBit5(this.getFrameData()[3]);
      byte var8 = DataHandleUtils.getBoolBit4(this.getFrameData()[3]);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 0, 3);
      byte var16 = DataHandleUtils.getBoolBit7(this.getFrameData()[4]);
      byte var17 = DataHandleUtils.getBoolBit6(this.getFrameData()[4]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 5, 1);
      byte var1 = DataHandleUtils.getBoolBit4(this.getFrameData()[4]);
      byte var18 = DataHandleUtils.getBoolBit3(this.getFrameData()[4]);
      byte var13 = DataHandleUtils.getBoolBit7(this.getFrameData()[5]);
      byte var14 = DataHandleUtils.getBoolBit6(this.getFrameData()[5]);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[5], 0, 2);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 4, 2);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 0, 4);
      Object var20;
      SettingPageUiSet.ListBean.ItemListBean var21;
      if (!UiMgrKt.isRemove2()) {
         var20 = this.mSettingItemIndexHashMap.get("S361_d0b7");
         Intrinsics.checkNotNull(var20);
         ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var4));
         var20 = this.mSettingItemIndexHashMap.get("S361_d1b0t2");
         Intrinsics.checkNotNull(var20);
         var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
         var21.setProgress(var11);
         var21.setValue(String.valueOf(var21.getProgress()));
         var20 = this.mSettingItemIndexHashMap.get("S361_d0b0t1");
         Intrinsics.checkNotNull(var20);
         var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
         var21.setValue(var21.getValueSrnArray().get(var9));
         var20 = this.mSettingItemIndexHashMap.get("S361_d2b7");
         Intrinsics.checkNotNull(var20);
         ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var16));
         var20 = this.mSettingItemIndexHashMap.get("S361_d2b5");
         Intrinsics.checkNotNull(var20);
         var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
         var21.setValue(var21.getValueSrnArray().get(var2));
      }

      var20 = this.mSettingItemIndexHashMap.get("S361_d0b6t4");
      Intrinsics.checkNotNull(var20);
      var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
      var21.setProgress(var19);
      var21.setValue(String.valueOf(var21.getProgress()));
      var20 = this.mSettingItemIndexHashMap.get("S361_d0b2t3");
      Intrinsics.checkNotNull(var20);
      var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
      var21.setValue(var21.getValueSrnArray().get(var7));
      if (!UiMgrKt.isRemove1()) {
         var20 = this.mSettingItemIndexHashMap.get("S361_d1b7");
         Intrinsics.checkNotNull(var20);
         ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var5));
         var20 = this.mSettingItemIndexHashMap.get("S361_d1b6");
         Intrinsics.checkNotNull(var20);
         ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var15));
         var20 = this.mSettingItemIndexHashMap.get("S361_d1b5");
         Intrinsics.checkNotNull(var20);
         ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var3));
         var20 = this.mSettingItemIndexHashMap.get("S361_d1b4");
         Intrinsics.checkNotNull(var20);
         ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var8));
         var20 = this.mSettingItemIndexHashMap.get("S361_d2b6");
         Intrinsics.checkNotNull(var20);
         ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var17));
      }

      var20 = this.mSettingItemIndexHashMap.get("S361_d2b4");
      Intrinsics.checkNotNull(var20);
      ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var1));
      var20 = this.mSettingItemIndexHashMap.get("S361_d2b3");
      Intrinsics.checkNotNull(var20);
      ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var18));
      var20 = this.mSettingItemIndexHashMap.get("S361_d3b7");
      Intrinsics.checkNotNull(var20);
      ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var13));
      var20 = this.mSettingItemIndexHashMap.get("S361_d3b6");
      Intrinsics.checkNotNull(var20);
      ((SettingPageUiSet.ListBean.ItemListBean)var20).setValue(Integer.valueOf(var14));
      if (!UiMgrKt.isRemove0()) {
         var20 = this.mSettingItemIndexHashMap.get("S361_d30t1");
         Intrinsics.checkNotNull(var20);
         var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
         var21.setValue(var21.getValueSrnArray().get(var6));
      }

      if (!UiMgrKt.isRemove4()) {
         var20 = this.mSettingItemIndexHashMap.get("S361_d4b4t5");
         Intrinsics.checkNotNull(var20);
         var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
         var21.setValue(var21.getValueSrnArray().get(var12));
         var20 = this.mSettingItemIndexHashMap.get("S361_d4b0t3");
         Intrinsics.checkNotNull(var20);
         var21 = (SettingPageUiSet.ListBean.ItemListBean)var20;
         var21.setValue(var21.getValueSrnArray().get(var10));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void drivingInfo() {
      int var5 = this.getFrameData()[2];
      int var8 = DataHandleUtils.getMsbLsbResult(this.getFrameData()[3], this.getFrameData()[4]);
      int var3 = DataHandleUtils.getMsbLsbResult(this.getFrameData()[5], this.getFrameData()[6]);
      int var6 = MsgMgrKt.getAnotherMsbLsbResult(this.getFrameData()[5], this.getFrameData()[6], this.getFrameData()[7]);
      int var7 = DataHandleUtils.getMsbLsbResult(this.getFrameData()[10], this.getFrameData()[11]);
      byte var2 = (byte)this.getFrameData()[12];
      int var1 = MsgMgrKt.getAnotherMsbLsbResult(this.getFrameData()[13], this.getFrameData()[14], this.getFrameData()[15]);
      int var4 = MsgMgrKt.getAnotherMsbLsbResult(this.getFrameData()[14], this.getFrameData()[15], this.getFrameData()[16]);
      Object var9 = this.mDriveItemIndexHashMap.get("d0");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var5 / 10 + " V");
      var9 = this.mDriveItemIndexHashMap.get("d1t2");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var8 / 10 + " L");
      var9 = this.mDriveItemIndexHashMap.get("d3t4");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var3 + " r/min");
      var9 = this.mDriveItemIndexHashMap.get("d5t7");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var6 + " km");
      var9 = this.mDriveItemIndexHashMap.get("d8t9");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var7 + " km/h");
      this.updateOutDoorTemp(this.getContext(), var2 + " °C");
      var9 = this.mDriveItemIndexHashMap.get("d11t13");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var1 / 10 + " km");
      var9 = this.mDriveItemIndexHashMap.get("d14t16");
      Intrinsics.checkNotNull(var9);
      ((DriverDataPageUiSet.Page.Item)var9).setValue(var4 / 10 + " km");
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void essentialInfo() {
      int[] var2 = this.getFrameData();
      byte var1 = 2;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var2[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrameData()[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrameData()[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      if (!DataHandleUtils.getBoolBit1(this.getFrameData()[2])) {
         var1 = 0;
      }

      GeneralDoorData.skyWindowOpenLevel = var1;
      this.updateDoorView(this.getContext());
   }

   private final void frontRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(4, this.getFrameData()[2], this.getFrameData()[3], this.getFrameData()[4], this.getFrameData()[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void fuelConsumptionInPast15Min() {
      int var15 = this.getFrameData()[2];
      int var18 = this.getFrameData()[3];
      int var27 = this.getFrameData()[4];
      int var28 = this.getFrameData()[5];
      int var17 = this.getFrameData()[6];
      int var8 = this.getFrameData()[7];
      int var9 = this.getFrameData()[8];
      int var30 = this.getFrameData()[9];
      int var14 = this.getFrameData()[10];
      int var11 = this.getFrameData()[11];
      int var19 = this.getFrameData()[12];
      int var29 = this.getFrameData()[13];
      int var31 = this.getFrameData()[14];
      int var7 = this.getFrameData()[15];
      int var12 = this.getFrameData()[16];
      int var25 = this.getFrameData()[17];
      int var20 = this.getFrameData()[18];
      int var21 = this.getFrameData()[19];
      int var5 = this.getFrameData()[20];
      int var1 = this.getFrameData()[21];
      int var6 = this.getFrameData()[22];
      int var22 = this.getFrameData()[23];
      int var4 = this.getFrameData()[24];
      int var24 = this.getFrameData()[25];
      int var3 = this.getFrameData()[26];
      int var10 = this.getFrameData()[27];
      int var23 = this.getFrameData()[28];
      int var26 = this.getFrameData()[29];
      int var13 = this.getFrameData()[30];
      int var2 = this.getFrameData()[31];
      int var16 = this.getFrameData()[32];
      Object var32 = this.mDriveItemIndexHashMap.get("D361_3_d0");
      Intrinsics.checkNotNull(var32);
      DriverDataPageUiSet.Page.Item var33 = (DriverDataPageUiSet.Page.Item)var32;
      String var34;
      if (var15 != 0) {
         if (var15 != 1) {
            if (var15 != 2) {
               var34 = "无效";
            } else {
               var34 = "L/100Km";
            }
         } else {
            var34 = "Km/L";
         }
      } else {
         var34 = "MPG";
      }

      var33.setValue(var34);
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d1t2");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var18 * 256 + var27) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d3t4");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var28 * 256 + var17) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d5t6");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var8 * 256 + var9) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d7t8");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var30 * 256 + var14) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d9t10");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var11 * 256 + var19) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d11t12");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var29 * 256 + var31) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d13t14");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var7 * 256 + var12) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d15t16");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var25 * 256 + var20) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d17t18");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var21 * 256 + var5) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d19t20");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var1 * 256 + var6) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d21t22");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var22 * 256 + var4) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d23t24");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var24 * 256 + var3) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d25t26");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var10 * 256 + var23) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d27t28");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var26 * 256 + var13) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("D361_3_d29t30");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var2 * 256 + var16) / 10.0F));
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void instantaneousFuelConsumptionInfo() {
      int var1 = this.getFrameData()[2];
      int var3 = this.getFrameData()[3];
      int var2 = this.getFrameData()[4];
      Object var4 = this.mDriveItemIndexHashMap.get("D361_1_d0");
      Intrinsics.checkNotNull(var4);
      DriverDataPageUiSet.Page.Item var5 = (DriverDataPageUiSet.Page.Item)var4;
      String var6;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var6 = "----";
            } else {
               var6 = "L/100Km";
            }
         } else {
            var6 = "Km/L";
         }
      } else {
         var6 = "MPG";
      }

      var5.setValue(var6);
      var4 = this.mDriveItemIndexHashMap.get("D361_1_d1t2");
      Intrinsics.checkNotNull(var4);
      ((DriverDataPageUiSet.Page.Item)var4).setValue(String.valueOf((float)(var3 * 256 + var2) / 10.0F));
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void oilAndElectricHybridInfo() {
      GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 0, 3);
      GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.getFrameData()[3]);
      GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.getFrameData()[3]);
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.getFrameData()[3]);
      GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.getFrameData()[3]);
      GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.getFrameData()[3]);
      GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.getFrameData()[3]);
      GeneralHybirdData.title = "动力回收值";
      GeneralHybirdData.valueList = CollectionsKt.listOf(this.getFrameData()[4] + " wh");
      this.updateHybirdActivity((Bundle)null);
   }

   private final void powerAmplifierIndicationInfo() {
      boolean var1 = DataHandleUtils.getBoolBit0(this.getFrameData()[2]);
      boolean var6 = DataHandleUtils.getBoolBit1(this.getFrameData()[2]);
      boolean var2 = DataHandleUtils.getBoolBit2(this.getFrameData()[2]);
      boolean var5 = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      boolean var3 = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      boolean var4 = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      Object var7 = this.mDriveItemIndexHashMap.get("_350_d_3_0");
      Intrinsics.checkNotNull(var7);
      DriverDataPageUiSet.Page.Item var8 = (DriverDataPageUiSet.Page.Item)var7;
      String var9;
      if (var1) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_1");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var6) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_2");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      Context var10 = this.getContext();
      if (var2) {
         var9 = CommUtil.getStrByResId(var10, "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(var10, "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_3");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      var10 = this.getContext();
      if (var5) {
         var9 = CommUtil.getStrByResId(var10, "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(var10, "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_4");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      var10 = this.getContext();
      if (var3) {
         var9 = CommUtil.getStrByResId(var10, "_350_u_1");
      } else {
         var9 = CommUtil.getStrByResId(var10, "_350_u_0");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_5");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var4) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_1");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_0");
      }

      var8.setValue(var9);
      this.updateDriveDataActivity((Bundle)null);
      this.powerSwitcher = var3;
   }

   private final void powerAmplifierInfo() {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 4, 4);
      byte var1 = 0;
      GeneralAmplifierData.frontRear = com.hzbhd.canbus.car._350.MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(var2, 0, 14), 0, 2, (Object)null) - 7;
      GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 0, 4), 0, 14) - 7;
      GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 0, 4), 2, 12) - 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(this.getFrameData()[5], 0, 63);
      this.updateAmplifierActivity((Bundle)null);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 0, 4);
      byte var5 = DataHandleUtils.getBoolBit0(this.getFrameData()[6]);
      Object var4 = this.mSettingItemIndexHashMap.get("S361_2_d2b0t3");
      Intrinsics.checkNotNull(var4);
      SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var4;
      if (var3 == 8) {
         var1 = 1;
      }

      var6.setValue(Integer.valueOf(var1));
      if (!UiMgrKt.isRemove0()) {
         var4 = this.mSettingItemIndexHashMap.get("S361_2_d4b0");
         Intrinsics.checkNotNull(var4);
         ((SettingPageUiSet.ListBean.ItemListBean)var4).setValue(Integer.valueOf(var5));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void rangeFuelConsumptionInfo() {
      int var8 = this.getFrameData()[2];
      int var12 = this.getFrameData()[3];
      int var10 = this.getFrameData()[4];
      int var7 = this.getFrameData()[5];
      int var11 = this.getFrameData()[6];
      int var4 = this.getFrameData()[7];
      int var3 = this.getFrameData()[8];
      int var5 = this.getFrameData()[9];
      int var1 = this.getFrameData()[10];
      int var2 = this.getFrameData()[11];
      int var6 = this.getFrameData()[12];
      int var13 = this.getFrameData()[13];
      int var9 = this.getFrameData()[14];
      Object var14 = this.mDriveItemIndexHashMap.get("D361_2_d0");
      Intrinsics.checkNotNull(var14);
      DriverDataPageUiSet.Page.Item var15 = (DriverDataPageUiSet.Page.Item)var14;
      String var16;
      if (var8 != 0) {
         if (var8 != 1) {
            if (var8 != 2) {
               var16 = "----";
            } else {
               var16 = "L/100Km";
            }
         } else {
            var16 = "Km/L";
         }
      } else {
         var16 = "MPG";
      }

      var15.setValue(var16);
      var14 = this.mDriveItemIndexHashMap.get("D361_2_d1t2");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var12 * 256 + var10) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("D361_2_d3t4");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var7 * 256 + var11) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("D361_2_d5t6");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var4 * 256 + var3) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("D361_2_d7t8");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var5 * 256 + var1) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("D361_2_d9t10");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var2 * 256 + var6) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("D361_2_d11t12");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var13 * 256 + var9) / 10.0F));
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void rearRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(4, this.getFrameData()[2], this.getFrameData()[3], this.getFrameData()[4], this.getFrameData()[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void rearSeatAirConditioner() {
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      GeneralAirData.rear_lock = DataHandleUtils.getBoolBit4(this.getFrameData()[2]);
      GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(this.getFrameData()[3]);
      GeneralAirData.rear_right_blow_window = GeneralAirData.rear_left_blow_window;
      GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.getFrameData()[3]);
      GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
      GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.getFrameData()[3]);
      GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
      int var1 = this.getFrameData()[3];
      boolean var2 = false;
      GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(var1, 0, 4);
      int var3 = this.getFrameData()[5];
      String var4;
      if (var3 == 0) {
         var4 = "LO";
      } else if (var3 == 31) {
         var4 = "HI";
      } else {
         boolean var5;
         if (32 <= var3 && var3 < 36) {
            var5 = true;
         } else {
            var5 = false;
         }

         if (var5) {
            var4 = (double)16 + (double)(this.getFrameData()[5] - 32) * 0.5 + " °C";
         } else {
            var5 = var2;
            if (1 <= var3) {
               var5 = var2;
               if (var3 < 30) {
                  var5 = true;
               }
            }

            if (var5) {
               var4 = (double)18 + (double)(this.getFrameData()[5] - 1) * 0.5 + " °C";
            } else {
               var4 = "--";
            }
         }
      }

      GeneralAirData.rear_temperature = var4;
      this.updateAirActivity(this.getContext(), 1002);
   }

   private final void sendTextInfo(int var1, int var2, String var3) {
      String var6 = var3;
      if (var3 == null) {
         var6 = "Unknown";
      }

      byte[] var7;
      if (var6.length() > 10) {
         var7 = StringsKt.substring(var6, new IntRange(0, 10)).getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var7, "this as java.lang.String).getBytes(charset)");
      } else {
         var7 = var6.getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var7, "this as java.lang.String).getBytes(charset)");
      }

      byte var4 = (byte)var1;
      byte var5 = (byte)var2;
      var7 = MsgMgrKt.restrict$default(var7, 24, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, var4, var5, 3}, var7));
   }

   private final void steeringWheelAngle() {
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrameData()[3], (byte)this.getFrameData()[4], 0, 352, 12);
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void steeringWheelKeys() {
      int var2 = this.getFrameData()[3];
      int var1 = this.getFrameData()[2];
      if (var1 != 0) {
         label182: {
            label183: {
               label184: {
                  label185: {
                     label186: {
                        label187: {
                           if (var1 != 1) {
                              if (var1 == 2) {
                                 break label187;
                              }

                              if (var1 == 3) {
                                 break label186;
                              }

                              if (var1 == 4) {
                                 break label185;
                              }

                              switch (var1) {
                                 case 7:
                                    break label184;
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
                                    switch (var1) {
                                       case 19:
                                          break label183;
                                       case 20:
                                          break label182;
                                       case 21:
                                          this.realKeyLongClick1(this.getContext(), 50, var2);
                                          return;
                                       case 22:
                                          this.realKeyLongClick1(this.getContext(), 49, var2);
                                          return;
                                       default:
                                          switch (var1) {
                                             case 32:
                                                this.realKeyLongClick1(this.getContext(), 77, var2);
                                                return;
                                             case 33:
                                                this.realKeyLongClick1(this.getContext(), 76, var2);
                                                return;
                                             case 34:
                                                this.realKeyLongClick1(this.getContext(), 139, var2);
                                                return;
                                             case 35:
                                                this.realKeyLongClick1(this.getContext(), 140, var2);
                                                return;
                                             case 36:
                                                this.realKeyLongClick1(this.getContext(), 141, var2);
                                                return;
                                             case 37:
                                                this.realKeyLongClick1(this.getContext(), 0, var2);
                                                return;
                                             default:
                                                switch (var1) {
                                                   case 129:
                                                      break;
                                                   case 130:
                                                      break label187;
                                                   case 131:
                                                      break label183;
                                                   case 132:
                                                      break label182;
                                                   case 133:
                                                      break label186;
                                                   case 134:
                                                      break label185;
                                                   case 135:
                                                      this.realKeyLongClick1(this.getContext(), 134, var2);
                                                      return;
                                                   case 136:
                                                      break label184;
                                                   default:
                                                      return;
                                                }
                                          }
                                    }
                              }
                           }

                           this.realKeyLongClick1(this.getContext(), 7, var2);
                           return;
                        }

                        this.realKeyLongClick1(this.getContext(), 8, var2);
                        return;
                     }

                     this.realKeyLongClick1(this.getContext(), 48, var2);
                     return;
                  }

                  this.realKeyLongClick1(this.getContext(), 47, var2);
                  return;
               }

               this.realKeyLongClick1(this.getContext(), 2, var2);
               return;
            }

            this.realKeyLongClick1(this.getContext(), 45, var2);
            return;
         }

         this.realKeyLongClick1(this.getContext(), 46, var2);
      } else {
         this.realKeyLongClick1(this.getContext(), 0, var2);
      }

   }

   private final void tirePressureInfo() {
      byte var2 = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 0, 2);
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var3 = "无效";
            } else {
               var3 = "2.5 KPA";
            }
         } else {
            var3 = "PSI";
         }
      } else {
         var3 = "0.1 BAR";
      }

      GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(this.getFrameData()[2]);
      List var4 = (List)(new ArrayList());
      var4.add(new TireUpdateEntity(0, var2, new String[]{String.valueOf(this.getFrameData()[3]), var3}));
      var4.add(new TireUpdateEntity(1, var2, new String[]{String.valueOf(this.getFrameData()[4]), var3}));
      var4.add(new TireUpdateEntity(2, var2, new String[]{String.valueOf(this.getFrameData()[5]), var3}));
      var4.add(new TireUpdateEntity(3, var2, new String[]{String.valueOf(this.getFrameData()[6]), var3}));
      if (GeneralTireData.isHaveSpareTire) {
         var4.add(new TireUpdateEntity(4, var2, new String[]{String.valueOf(this.getFrameData()[7]), var3}));
      }

      GeneralTireData.dataList = var4;
      this.updateTirePressureActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowHandBrake = true;
      this.initItemsIndexHashMap(var1);
      ((Thread)(new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            while(true) {
               Thread.sleep(1000L);
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)this.this$0.getPowerSwitcher()});
            }
         }
      })).start();
   }

   public void auxInInfoChange() {
      MsgMgrKt.sendMediaInfo$default(7, 0, (byte[])null, 4, (Object)null);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      byte var12 = 2;
      short var10;
      if (!var3) {
         var10 = 0;
      } else if (!var4 && var3) {
         var10 = 1;
      } else if (var1 == 1) {
         var10 = var12;
      } else if (var1 == 2) {
         var10 = 3;
      } else if (var1 == 4) {
         var10 = 5;
      } else {
         var10 = 255;
      }

      byte[] var13 = new byte[6];

      for(var7 = 0; var7 < 6; ++var7) {
         var13[var7] = 0;
      }

      var13[0] = (byte)var10;
      MsgMgrKt.sendMediaInfo(5, 3, var13);
      String var11 = Arrays.toString(var2);
      Intrinsics.checkNotNullExpressionValue(var11, "toString(this)");
      this.sendTextInfo(137, 3, var11);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      if (var1 != null && var2 != null) {
         this.setContext(var1);
         int[] var4 = this.getByteArrayToIntArray(var2);
         Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
         this.setFrameData(var4);
         int var3 = this.getFrameData()[1];
         if (var3 != 26) {
            if (var3 != 58) {
               if (var3 != 49) {
                  if (var3 != 50) {
                     if (var3 != 253) {
                        if (var3 != 254) {
                           switch (var3) {
                              case 29:
                                 this.frontRadarInfo();
                                 break;
                              case 30:
                                 this.rearRadarInfo();
                                 break;
                              case 31:
                                 this.oilAndElectricHybridInfo();
                                 break;
                              case 32:
                                 this.steeringWheelKeys();
                                 break;
                              case 33:
                                 this.basicInfoOfJourney();
                                 break;
                              case 34:
                                 this.instantaneousFuelConsumptionInfo();
                                 break;
                              case 35:
                                 this.rangeFuelConsumptionInfo();
                                 break;
                              case 36:
                                 this.essentialInfo();
                                 break;
                              case 37:
                                 this.tirePressureInfo();
                                 break;
                              case 38:
                                 this.centralCtrlInfo();
                                 break;
                              case 39:
                                 this.fuelConsumptionInPast15Min();
                                 break;
                              case 40:
                                 this.airConditionerInfo();
                                 break;
                              case 41:
                                 this.steeringWheelAngle();
                           }
                        } else {
                           this.drivingInfo();
                        }
                     } else {
                        this.bodyState();
                     }
                  } else {
                     this.powerAmplifierIndicationInfo();
                  }
               } else {
                  this.powerAmplifierInfo();
               }
            } else {
               this.rearSeatAirConditioner();
            }
         } else {
            this.centralControlEnableBit();
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

   public final int[] getFrameData() {
      int[] var1 = this.frameData;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("frameData");
         return null;
      }
   }

   public final HashMap getMDriveItemIndexHashMap() {
      return this.mDriveItemIndexHashMap;
   }

   public final HashMap getMSettingItemIndexHashMap() {
      return this.mSettingItemIndexHashMap;
   }

   public final boolean getPowerSwitcher() {
      return this.powerSwitcher;
   }

   public final void initItemsIndexHashMap(Context var1) {
      UiMgrInterface var4 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type com.hzbhd.canbus.car._361.UiMgr");
      UiMgr var8 = (UiMgr)var4;
      List var12 = var8.getDriverDataPageUiSet(var1).getList();
      Iterator var7 = var12.iterator();

      int var2;
      int var3;
      for(var2 = 0; var7.hasNext(); ++var2) {
         Iterator var5 = ((DriverDataPageUiSet.Page)var7.next()).getItemList().iterator();

         for(var3 = 0; var5.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var9 = (DriverDataPageUiSet.Page.Item)var5.next();
            Map var6 = (Map)this.mDriveItemIndexHashMap;
            String var19 = var9.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var19, "item.titleSrn");
            Object var10 = ((DriverDataPageUiSet.Page)var12.get(var2)).getItemList().get(var3);
            Intrinsics.checkNotNullExpressionValue(var10, "this[pageIndex].itemList[itemIndex]");
            var6.put(var19, var10);
         }
      }

      List var15 = var8.getSettingUiSet(var1).getList();
      Iterator var11 = var15.iterator();

      for(var2 = 0; var11.hasNext(); ++var2) {
         Iterator var13 = ((SettingPageUiSet.ListBean)var11.next()).getItemList().iterator();

         for(var3 = 0; var13.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var16 = (SettingPageUiSet.ListBean.ItemListBean)var13.next();
            Map var14 = (Map)this.mSettingItemIndexHashMap;
            String var17 = var16.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var17, "itemListBean.titleSrn");
            Object var18 = ((SettingPageUiSet.ListBean)var15.get(var2)).getItemList().get(var3);
            Intrinsics.checkNotNullExpressionValue(var18, "this[left].itemList[right]");
            var14.put(var17, var18);
         }
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      var2 = 9;
      if (var1 == 9) {
         var1 = var2;
      } else {
         var1 = 8;
      }

      List var25 = (List)(new ArrayList());
      var25.add((byte)var3);
      var25.add(var9);
      var25.add((byte)DataHandleUtils.getLsb(var4));
      var25.add((byte)DataHandleUtils.getMsb(var4));
      var25.add(var6);
      var25.add(var7);
      MsgMgrKt.sendMediaInfo(var1, 2, CollectionsKt.toByteArray((Collection)var25));
      this.sendTextInfo(135, 1, var21);
      this.sendTextInfo(135, 2, var23);
      this.sendTextInfo(135, 3, var22);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      boolean var6;
      Integer var7;
      List var8;
      label160: {
         var8 = (List)(new ArrayList());
         var6 = false;
         if (var2 != null) {
            var5 = var2.hashCode();
            if (var5 != 2092) {
               if (var5 != 2247) {
                  switch (var5) {
                     case 64901:
                        if (var2.equals("AM1")) {
                           var7 = 17;
                           break label160;
                        }
                        break;
                     case 64902:
                        if (var2.equals("AM2")) {
                           var7 = 18;
                           break label160;
                        }
                        break;
                     case 64903:
                        if (var2.equals("AM3")) {
                           var7 = 19;
                           break label160;
                        }
                        break;
                     default:
                        switch (var5) {
                           case 69706:
                              if (var2.equals("FM1")) {
                                 var7 = 1;
                                 break label160;
                              }
                              break;
                           case 69707:
                              if (var2.equals("FM2")) {
                                 var7 = 2;
                                 break label160;
                              }
                              break;
                           case 69708:
                              if (var2.equals("FM3")) {
                                 var7 = 3;
                                 break label160;
                              }
                        }
                  }
               } else if (var2.equals("FM")) {
                  var7 = 0;
                  break label160;
               }
            } else if (var2.equals("AM")) {
               var7 = 16;
               break label160;
            }
         }

         var7 = null;
      }

      boolean var9;
      if ((var7 == null || var7 != 0) && (var7 == null || var7 != 1)) {
         var9 = false;
      } else {
         var9 = true;
      }

      if (!var9 && (var7 == null || var7 != 2)) {
         var9 = false;
      } else {
         var9 = true;
      }

      if (!var9 && (var7 == null || var7 != 3)) {
         var9 = false;
      } else {
         var9 = true;
      }

      int var10;
      if (var9) {
         Intrinsics.checkNotNull(var3);
         var10 = (int)(Double.parseDouble(var3) * (double)100);
         var5 = DataHandleUtils.getLsb(var10);
         var10 = DataHandleUtils.getMsb(var10);
         var8.add((byte)var7);
         var8.add((byte)var5);
         var8.add((byte)var10);
      } else {
         if (var7 != null && var7 == 16 || var7 != null && var7 == 17) {
            var9 = true;
         } else {
            var9 = false;
         }

         if (!var9 && (var7 == null || var7 != 18)) {
            var9 = false;
         } else {
            var9 = true;
         }

         label91: {
            if (!var9) {
               if (var7 == null) {
                  var9 = var6;
                  break label91;
               }

               var9 = var6;
               if (var7 != 19) {
                  break label91;
               }
            }

            var9 = true;
         }

         if (var9) {
            Intrinsics.checkNotNull(var3);
            var10 = Integer.parseInt(var3);
            var5 = DataHandleUtils.getLsb(var10);
            var10 = DataHandleUtils.getMsb(var10);
            var8.add((byte)var7);
            var8.add((byte)var5);
            var8.add((byte)var10);
         }
      }

      var8.add((byte)var1);
      MsgMgrKt.sendMediaInfo(1, 1, CollectionsKt.toByteArray((Collection)var8));
   }

   public final void returnClick() {
      this.startMainActivity(this.getContext());
   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrameData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frameData = var1;
   }

   public final void setMDriveItemIndexHashMap(HashMap var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mDriveItemIndexHashMap = var1;
   }

   public final void setMSettingItemIndexHashMap(HashMap var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mSettingItemIndexHashMap = var1;
   }

   public final void setPowerSwitcher(boolean var1) {
      this.powerSwitcher = var1;
   }
}
