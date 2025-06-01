package com.hzbhd.canbus.car._350;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u001e\n\u0002\u0010\t\n\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010(\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u0006\u0010*\u001a\u00020)J\b\u0010+\u001a\u00020)H\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002J\b\u0010/\u001a\u00020)H\u0016J\u0006\u00100\u001a\u00020)J\u001c\u00101\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020)H\u0002Jv\u00105\u001a\u00020)2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020-2\u0006\u00109\u001a\u00020-2\u0006\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020-2\u0006\u0010<\u001a\u00020-2\u0006\u0010=\u001a\u00020-2\u0006\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u00020-2\b\u0010A\u001a\u0004\u0018\u00010\u00172\b\u0010B\u001a\u0004\u0018\u00010\u00172\b\u0010C\u001a\u0004\u0018\u00010\u0017H\u0016J\u0006\u0010D\u001a\u00020)J\u0010\u0010E\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0006\u0010F\u001a\u00020)JÄ\u0001\u0010G\u001a\u00020)2\u0006\u0010H\u001a\u0002072\u0006\u0010I\u001a\u0002072\u0006\u0010J\u001a\u00020-2\u0006\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u0002072\u0006\u0010M\u001a\u0002072\u0006\u0010N\u001a\u0002072\u0006\u0010O\u001a\u0002072\u0006\u0010P\u001a\u0002072\u0006\u0010Q\u001a\u0002072\b\u0010R\u001a\u0004\u0018\u00010\u00172\b\u0010S\u001a\u0004\u0018\u00010\u00172\b\u0010T\u001a\u0004\u0018\u00010\u00172\u0006\u0010U\u001a\u00020V2\u0006\u00106\u001a\u0002072\u0006\u0010W\u001a\u00020-2\u0006\u0010?\u001a\u00020\u00142\u0006\u0010X\u001a\u00020V2\b\u0010Y\u001a\u0004\u0018\u00010\u00172\b\u0010Z\u001a\u0004\u0018\u00010\u00172\b\u0010[\u001a\u0004\u0018\u00010\u00172\u0006\u0010\\\u001a\u00020\u0014H\u0016J\b\u0010]\u001a\u00020)H\u0002J\b\u0010^\u001a\u00020)H\u0002J6\u0010_\u001a\u00020)2\u0006\u0010`\u001a\u00020-2\b\u0010a\u001a\u0004\u0018\u00010\u00172\b\u0010b\u001a\u0004\u0018\u00010\u00172\b\u0010c\u001a\u0004\u0018\u00010\u00172\u0006\u0010d\u001a\u00020-H\u0016J\u0006\u0010e\u001a\u00020)J\u0006\u0010f\u001a\u00020)J\u0006\u0010g\u001a\u00020)J\b\u0010h\u001a\u00020)H\u0002J\u0006\u0010i\u001a\u00020)J\b\u0010j\u001a\u00020)H\u0002J\b\u0010k\u001a\u00020)H\u0002J\u0006\u0010l\u001a\u00020)R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016j\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018`\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR>\u0010\u001e\u001a&\u0012\u0004\u0012\u00020\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0\u0016j\u0012\u0012\u0004\u0012\u00020\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f`\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\u001dR\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006m"},
   d2 = {"Lcom/hzbhd/canbus/car/_350/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frameData", "", "getFrameData", "()[I", "setFrameData", "([I)V", "lastListForFrontAirConditioner", "", "Ljava/io/Serializable;", "lastListForRearAirConditioner", "lastValue", "", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "getMDriveItemIndexHashMap", "()Ljava/util/HashMap;", "setMDriveItemIndexHashMap", "(Ljava/util/HashMap;)V", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "getMSettingItemIndexHashMap", "setMSettingItemIndexHashMap", "mUiMgr", "Lcom/hzbhd/canbus/car/_350/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_350/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_350/UiMgr;)V", "afterServiceNormalSetting", "", "airConditioner", "amplifierInfo", "assignRadarProgress", "", "value", "auxInInfoChange", "basicInfo", "canbusInfoChange", "canbusInfo", "", "controlModeInfo", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "album", "artist", "frontRadar", "initItemsIndexHashMap", "mediaInfo", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "past15minFuelConsumption", "pastFuelConsumption", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "rearRadar", "rockerInfo", "steerAngle", "steerBtn", "systemInfo", "vehicleInfo", "vehicleSettings", "versionInfo", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frameData;
   private List lastListForFrontAirConditioner = (List)(new ArrayList());
   private List lastListForRearAirConditioner = (List)(new ArrayList());
   private boolean lastValue;
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private HashMap mSettingItemIndexHashMap = new HashMap();
   public UiMgr mUiMgr;

   private static final void airConditioner$updateWithNotPop(MsgMgr var0, int var1) {
      Bundle var2 = new Bundle();
      var2.putInt("bundle_air_what", var1);
      var0.updateActivity(var2);
   }

   private static final void airConditioner$updateWithPop(MsgMgr var0, int var1) {
      var0.updateAirActivity(var0.getContext(), var1);
   }

   private final void amplifierInfo() {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 4, 4);
      byte var1 = 0;
      GeneralAmplifierData.frontRear = MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(var2, 0, 14), 0, 2, (Object)null) - 7;
      GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 0, 4), 0, 14) - 7;
      GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 0, 4), 2, 12) - 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(this.getFrameData()[5], 0, 63);
      this.updateAmplifierActivity((Bundle)null);
      Object var3 = this.mSettingItemIndexHashMap.get("_350_s_6_0");
      Intrinsics.checkNotNull(var3);
      SettingPageUiSet.ListBean.ItemListBean var4 = (SettingPageUiSet.ListBean.ItemListBean)var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 0, 4) == 8) {
         var1 = 1;
      }

      var4.setValue(Integer.valueOf(var1));
      var3 = this.mSettingItemIndexHashMap.get("_350_s_6_1");
      Intrinsics.checkNotNull(var3);
      ((SettingPageUiSet.ListBean.ItemListBean)var3).setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(this.getFrameData()[6])));
      this.updateSettingActivity((Bundle)null);
   }

   private final int assignRadarProgress(int var1) {
      byte var2 = 4;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  var2 = 0;
               } else {
                  var2 = 10;
               }
            } else {
               var2 = 7;
            }
         }
      } else {
         var2 = 1;
      }

      return var2;
   }

   private final void controlModeInfo() {
      Object var1 = this.mSettingItemIndexHashMap.get("_350_s_7_0_0");
      Intrinsics.checkNotNull(var1);
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)var1;
      var2.setValue(var2.getValueSrnArray().get(this.getFrameData()[2]));
   }

   private final void past15minFuelConsumption() {
      int var16 = this.getFrameData()[2];
      int var8 = this.getFrameData()[3];
      int var26 = this.getFrameData()[4];
      int var31 = this.getFrameData()[5];
      int var6 = this.getFrameData()[6];
      int var27 = this.getFrameData()[7];
      int var12 = this.getFrameData()[8];
      int var7 = this.getFrameData()[9];
      int var1 = this.getFrameData()[10];
      int var21 = this.getFrameData()[11];
      int var19 = this.getFrameData()[12];
      int var13 = this.getFrameData()[13];
      int var18 = this.getFrameData()[14];
      int var25 = this.getFrameData()[15];
      int var10 = this.getFrameData()[16];
      int var22 = this.getFrameData()[17];
      int var2 = this.getFrameData()[18];
      int var9 = this.getFrameData()[19];
      int var11 = this.getFrameData()[20];
      int var17 = this.getFrameData()[21];
      int var28 = this.getFrameData()[22];
      int var20 = this.getFrameData()[23];
      int var14 = this.getFrameData()[24];
      int var4 = this.getFrameData()[25];
      int var23 = this.getFrameData()[26];
      int var29 = this.getFrameData()[27];
      int var5 = this.getFrameData()[28];
      int var15 = this.getFrameData()[29];
      int var30 = this.getFrameData()[30];
      int var3 = this.getFrameData()[31];
      int var24 = this.getFrameData()[32];
      Object var32 = this.mDriveItemIndexHashMap.get("_350_d_1_0");
      Intrinsics.checkNotNull(var32);
      DriverDataPageUiSet.Page.Item var33 = (DriverDataPageUiSet.Page.Item)var32;
      String var34;
      if (var16 != 0) {
         if (var16 != 1) {
            if (var16 != 2) {
               var34 = CommUtil.getStrByResId(this.getContext(), "set_default");
            } else {
               var34 = "L/100KM";
            }
         } else {
            var34 = "KM/L";
         }
      } else {
         var34 = "MPG";
      }

      var33.setValue(var34);
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_1");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var8 * 256 + var26) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_2");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var31 * 256 + var6) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_3");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var27 * 256 + var12) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_4");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var7 * 256 + var1) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_5");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var21 * 256 + var19) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_6");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var13 * 256 + var18) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_7");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var25 * 256 + var10) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_8");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var22 * 256 + var2) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_9");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var9 * 256 + var11) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_10");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var17 * 256 + var28) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_11");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var20 * 256 + var14) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_12");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var4 * 256 + var23) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_13");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var29 * 256 + var5) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_14");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var15 * 256 + var30) / 10.0F));
      var32 = this.mDriveItemIndexHashMap.get("_350_d_1_15");
      Intrinsics.checkNotNull(var32);
      ((DriverDataPageUiSet.Page.Item)var32).setValue(String.valueOf((float)(var3 * 256 + var24) / 10.0F));
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void pastFuelConsumption() {
      int var10 = this.getFrameData()[2];
      int var1 = this.getFrameData()[3];
      int var9 = this.getFrameData()[4];
      int var3 = this.getFrameData()[5];
      int var8 = this.getFrameData()[6];
      int var13 = this.getFrameData()[7];
      int var6 = this.getFrameData()[8];
      int var5 = this.getFrameData()[9];
      int var7 = this.getFrameData()[10];
      int var4 = this.getFrameData()[11];
      int var12 = this.getFrameData()[12];
      int var11 = this.getFrameData()[13];
      int var2 = this.getFrameData()[14];
      Object var14 = this.mDriveItemIndexHashMap.get("_350_d_0_0");
      Intrinsics.checkNotNull(var14);
      DriverDataPageUiSet.Page.Item var15 = (DriverDataPageUiSet.Page.Item)var14;
      String var16;
      if (var10 != 0) {
         if (var10 != 1) {
            if (var10 != 2) {
               var16 = CommUtil.getStrByResId(this.getContext(), "set_default");
            } else {
               var16 = "L/100KM";
            }
         } else {
            var16 = "KM/L";
         }
      } else {
         var16 = "MPG";
      }

      var15.setValue(var16);
      var14 = this.mDriveItemIndexHashMap.get("_350_d_0_1");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var1 * 256 + var9) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("_350_d_0_2");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var3 * 256 + var8) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("_350_d_0_3");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var13 * 256 + var6) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("_350_d_0_4");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var5 * 256 + var7) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("_350_d_0_5");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var4 * 256 + var12) / 10.0F));
      var14 = this.mDriveItemIndexHashMap.get("_350_d_0_6");
      Intrinsics.checkNotNull(var14);
      ((DriverDataPageUiSet.Page.Item)var14).setValue(String.valueOf((float)(var11 * 256 + var2) / 10.0F));
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void steerBtn() {
      int var1 = this.getFrameData()[2];
      if (var1 != 15) {
         if (var1 != 129) {
            if (var1 != 130) {
               if (var1 != 135) {
                  if (var1 != 136) {
                     switch (var1) {
                        case 1:
                           this.realKeyLongClick1(this.getContext(), 7, this.getFrameData()[3]);
                           break;
                        case 2:
                           this.realKeyLongClick1(this.getContext(), 8, this.getFrameData()[3]);
                           break;
                        case 3:
                           this.realKeyLongClick1(this.getContext(), 48, this.getFrameData()[3]);
                           break;
                        case 4:
                           this.realKeyLongClick1(this.getContext(), 47, this.getFrameData()[3]);
                           break;
                        case 5:
                           this.realKeyLongClick1(this.getContext(), 2, this.getFrameData()[3]);
                           break;
                        case 6:
                           this.realKeyLongClick1(this.getContext(), 3, this.getFrameData()[3]);
                           break;
                        case 7:
                           this.realKeyLongClick1(this.getContext(), 187, this.getFrameData()[3]);
                           break;
                        case 8:
                           this.realKeyLongClick1(this.getContext(), 14, this.getFrameData()[3]);
                           break;
                        case 9:
                           this.realKeyLongClick1(this.getContext(), 15, this.getFrameData()[3]);
                           break;
                        case 10:
                           this.realKeyLongClick1(this.getContext(), 52, this.getFrameData()[3]);
                           break;
                        default:
                           switch (var1) {
                              case 17:
                                 this.realKeyLongClick1(this.getContext(), 53, this.getFrameData()[3]);
                                 break;
                              case 18:
                                 this.realKeyLongClick1(this.getContext(), 151, this.getFrameData()[3]);
                                 break;
                              case 19:
                                 this.realKeyLongClick1(this.getContext(), 45, this.getFrameData()[3]);
                                 break;
                              case 20:
                                 this.realKeyLongClick1(this.getContext(), 46, this.getFrameData()[3]);
                                 break;
                              case 21:
                                 this.realKeyLongClick1(this.getContext(), 50, this.getFrameData()[3]);
                                 break;
                              case 22:
                                 this.realKeyLongClick1(this.getContext(), 49, this.getFrameData()[3]);
                                 break;
                              case 23:
                                 DataHandleUtils.knob(this.getContext(), 45, this.getFrameData()[3]);
                                 break;
                              case 24:
                                 DataHandleUtils.knob(this.getContext(), 46, this.getFrameData()[3]);
                           }
                     }
                  } else {
                     this.realKeyLongClick1(this.getContext(), 2, this.getFrameData()[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.getContext(), 134, this.getFrameData()[3]);
               }
            } else {
               this.realKeyLongClick1(this.getContext(), 8, this.getFrameData()[3]);
            }
         } else {
            this.realKeyLongClick1(this.getContext(), 7, this.getFrameData()[3]);
         }
      } else {
         this.realKeyLongClick1(this.getContext(), 128, this.getFrameData()[3]);
      }

   }

   private final void vehicleInfo() {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrameData()[5], this.getFrameData()[6]));
      boolean var13 = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      boolean var12 = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      boolean var15 = DataHandleUtils.getBoolBit5(this.getFrameData()[2]);
      boolean var16 = DataHandleUtils.getBoolBit4(this.getFrameData()[2]);
      boolean var17 = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      boolean var14 = DataHandleUtils.getBoolBit2(this.getFrameData()[2]);
      int var5 = this.getFrameData()[3];
      int var2 = this.getFrameData()[4];
      int var11 = this.getFrameData()[5];
      int var8 = this.getFrameData()[6];
      int var3 = this.getFrameData()[7];
      int var10 = this.getFrameData()[8];
      int var1 = this.getFrameData()[9];
      int var6 = this.getFrameData()[10];
      int var4 = this.getFrameData()[11];
      int var9 = this.getFrameData()[12];
      int var7 = this.getFrameData()[13];
      Object var18 = this.mDriveItemIndexHashMap.get("_350_d_2_0_0");
      Intrinsics.checkNotNull(var18);
      DriverDataPageUiSet.Page.Item var19 = (DriverDataPageUiSet.Page.Item)var18;
      String var20;
      if (var13) {
         var20 = CommUtil.getStrByResId(this.getContext(), "_350_u_1");
      } else {
         var20 = CommUtil.getStrByResId(this.getContext(), "_350_u_0");
      }

      var19.setValue(var20);
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_0_1");
      Intrinsics.checkNotNull(var18);
      var19 = (DriverDataPageUiSet.Page.Item)var18;
      Context var21 = this.getContext();
      if (var12) {
         var20 = CommUtil.getStrByResId(var21, "_350_u_1");
      } else {
         var20 = CommUtil.getStrByResId(var21, "_350_u_0");
      }

      var19.setValue(var20);
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_0_2");
      Intrinsics.checkNotNull(var18);
      var19 = (DriverDataPageUiSet.Page.Item)var18;
      var21 = this.getContext();
      if (var15) {
         var20 = CommUtil.getStrByResId(var21, "_350_u_1");
      } else {
         var20 = CommUtil.getStrByResId(var21, "_350_u_0");
      }

      var19.setValue(var20);
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_0_3");
      Intrinsics.checkNotNull(var18);
      var19 = (DriverDataPageUiSet.Page.Item)var18;
      var21 = this.getContext();
      if (var16) {
         var20 = CommUtil.getStrByResId(var21, "_350_u_1");
      } else {
         var20 = CommUtil.getStrByResId(var21, "_350_u_0");
      }

      var19.setValue(var20);
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_0_4");
      Intrinsics.checkNotNull(var18);
      var19 = (DriverDataPageUiSet.Page.Item)var18;
      var21 = this.getContext();
      if (var17) {
         var20 = CommUtil.getStrByResId(var21, "_350_u_1");
      } else {
         var20 = CommUtil.getStrByResId(var21, "_350_u_0");
      }

      var19.setValue(var20);
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_0_5");
      Intrinsics.checkNotNull(var18);
      var19 = (DriverDataPageUiSet.Page.Item)var18;
      var21 = this.getContext();
      if (var14) {
         var20 = CommUtil.getStrByResId(var21, "_350_u_1");
      } else {
         var20 = CommUtil.getStrByResId(var21, "_350_u_0");
      }

      var19.setValue(var20);
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_1");
      Intrinsics.checkNotNull(var18);
      ((DriverDataPageUiSet.Page.Item)var18).setValue(DataHandleUtils.getMsbLsbResult(var5, var2) + " rpm");
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_2");
      Intrinsics.checkNotNull(var18);
      ((DriverDataPageUiSet.Page.Item)var18).setValue(DataHandleUtils.getMsbLsbResult(var11, var8) + " km/h");
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_3");
      Intrinsics.checkNotNull(var18);
      ((DriverDataPageUiSet.Page.Item)var18).setValue(String.valueOf((var3 << 16) + (var10 << 8) + var1));
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_4");
      Intrinsics.checkNotNull(var18);
      ((DriverDataPageUiSet.Page.Item)var18).setValue(String.valueOf((var6 << 8) + var4));
      Context var22 = this.getContext();
      if (var9 == 0) {
         var20 = "--";
      } else {
         var20 = (byte)var9 + " °C";
      }

      this.updateOutDoorTemp(var22, var20);
      var18 = this.mDriveItemIndexHashMap.get("_350_d_2_6");
      Intrinsics.checkNotNull(var18);
      var19 = (DriverDataPageUiSet.Page.Item)var18;
      if (var7 != 1) {
         if (var7 != 2) {
            var20 = CommUtil.getStrByResId(this.getContext(), "set_default");
         } else {
            var20 = CommUtil.getStrByResId(this.getContext(), "_350_d_2_6_1");
         }
      } else {
         var20 = CommUtil.getStrByResId(this.getContext(), "_350_d_2_6_0");
      }

      var19.setValue(var20);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void vehicleSettings() {
      byte var16 = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 4, 3);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 2, 2);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[2], 0, 2);
      byte var20 = DataHandleUtils.getBoolBit7(this.getFrameData()[3]);
      byte var7 = DataHandleUtils.getBoolBit6(this.getFrameData()[3]);
      byte var5 = DataHandleUtils.getBoolBit5(this.getFrameData()[3]);
      byte var4 = DataHandleUtils.getBoolBit4(this.getFrameData()[3]);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 0, 3);
      byte var9 = DataHandleUtils.getBoolBit7(this.getFrameData()[4]);
      byte var21 = DataHandleUtils.getBoolBit6(this.getFrameData()[4]);
      byte var12 = DataHandleUtils.getBoolBit5(this.getFrameData()[4]);
      byte var17 = DataHandleUtils.getBoolBit4(this.getFrameData()[4]);
      byte var6 = DataHandleUtils.getBoolBit3(this.getFrameData()[4]);
      int var22 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[4], 0, 3);
      byte var8 = DataHandleUtils.getBoolBit7(this.getFrameData()[5]);
      byte var2 = DataHandleUtils.getBoolBit6(this.getFrameData()[5]);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[5], 2, 2);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[5], 0, 2);
      int var15 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 5, 2);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 2, 3);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 0, 2);
      Object var23 = this.mSettingItemIndexHashMap.get("_350_s_0_0");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var16));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_0_1");
      Intrinsics.checkNotNull(var23);
      SettingPageUiSet.ListBean.ItemListBean var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setProgress(var18);
      var24.setValue(String.valueOf(var18));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_0_2");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var1));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_0_3");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var13));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_1_0");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var20));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_1_1");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var7));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_1_2");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var5));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_1_3");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var4));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_1_4");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setProgress(var11);
      var24.setValue(String.valueOf(var24.getProgress()));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_2_0");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var9));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_2_1");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var21));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_2_2");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var12));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_2_3");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var17));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_2_4");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var6));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_2_5");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var22));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_3_0");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var8));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_3_1");
      Intrinsics.checkNotNull(var23);
      ((SettingPageUiSet.ListBean.ItemListBean)var23).setValue(Integer.valueOf(var2));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_3_2");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var19));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_3_3");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var10));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_4_0");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var15));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_4_1");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setProgress(var14);
      var24.setValue(String.valueOf(var24.getProgress()));
      var23 = this.mSettingItemIndexHashMap.get("_350_s_4_2");
      Intrinsics.checkNotNull(var23);
      var24 = (SettingPageUiSet.ListBean.ItemListBean)var23;
      var24.setValue(var24.getValueSrnArray().get(var3));
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._350.UiMgr");
      this.setMUiMgr((UiMgr)var2);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initItemsIndexHashMap(var1);
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isShowLittleLight = true;
   }

   public final void airConditioner() {
      boolean var32 = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      boolean var12 = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      boolean var29 = DataHandleUtils.getBoolBit5(this.getFrameData()[2]) ^ true;
      boolean var25 = DataHandleUtils.getBoolBit4(this.getFrameData()[2]);
      boolean var14 = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      boolean var27 = DataHandleUtils.getBoolBit2(this.getFrameData()[2]);
      boolean var18 = DataHandleUtils.getBoolBit1(this.getFrameData()[2]);
      boolean var23 = DataHandleUtils.getBoolBit0(this.getFrameData()[2]);
      boolean var11 = DataHandleUtils.getBoolBit7(this.getFrameData()[3]);
      boolean var31 = DataHandleUtils.getBoolBit6(this.getFrameData()[3]);
      boolean var17 = DataHandleUtils.getBoolBit5(this.getFrameData()[3]);
      boolean var22 = DataHandleUtils.getBoolBit4(this.getFrameData()[3]);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[3], 0, 4);
      int var8 = this.getFrameData()[4];
      int var7 = this.getFrameData()[5];
      boolean var24 = DataHandleUtils.getBoolBit7(this.getFrameData()[6]);
      DataHandleUtils.getBoolBit6(this.getFrameData()[6]);
      boolean var20 = DataHandleUtils.getBoolBit5(this.getFrameData()[6]);
      boolean var16 = DataHandleUtils.getBoolBit4(this.getFrameData()[6]);
      DataHandleUtils.getBoolBit3(this.getFrameData()[6]);
      boolean var15 = DataHandleUtils.getBoolBit2(this.getFrameData()[6]);
      boolean var26 = DataHandleUtils.getBoolBit0(this.getFrameData()[6]);
      int var6 = this.getFrameData()[7];
      boolean var30 = DataHandleUtils.getBoolBit7(this.getFrameData()[8]);
      boolean var28 = DataHandleUtils.getBoolBit6(this.getFrameData()[8]);
      boolean var19 = DataHandleUtils.getBoolBit5(this.getFrameData()[8]);
      boolean var21 = DataHandleUtils.getBoolBit4(this.getFrameData()[8]);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[8], 0, 4);
      int var4 = this.getFrameData()[9];
      boolean var10 = DataHandleUtils.getBoolBit7(this.getFrameData()[10]);
      boolean var13 = DataHandleUtils.getBoolBit6(this.getFrameData()[10]);
      boolean var9 = DataHandleUtils.getBoolBit5(this.getFrameData()[10]);
      DataHandleUtils.getBoolBit4(this.getFrameData()[10]);
      DataHandleUtils.getBoolBit3(this.getFrameData()[10]);
      DataHandleUtils.getBoolBit2(this.getFrameData()[10]);
      List var35 = CollectionsKt.listOf(new Serializable[]{(Serializable)var32, (Serializable)var12, (Serializable)var29, (Serializable)var25, (Serializable)var14, (Serializable)var27, (Serializable)var18, (Serializable)var23, (Serializable)var11, (Serializable)var31, (Serializable)var17, (Serializable)var3, (Serializable)var8, (Serializable)var7, (Serializable)var24, (Serializable)var20, (Serializable)var16, (Serializable)var10, (Serializable)var13, (Serializable)var9});
      List var34 = CollectionsKt.listOf(new Serializable[]{(Serializable)var15, (Serializable)var6, (Serializable)var30, (Serializable)var28, (Serializable)var28, (Serializable)var19, (Serializable)var21, (Serializable)var5, (Serializable)var4});
      GeneralAirData.power = var32;
      GeneralAirData.ac = var12;
      GeneralAirData.in_out_cycle = var29;
      GeneralAirData.negative_ion = var25;
      GeneralAirData.auto = var14;
      GeneralAirData.dual = var27;
      GeneralAirData.front_window_heat = var23;
      GeneralAirData.front_left_blow_window = var11;
      GeneralAirData.front_left_blow_head = var31;
      GeneralAirData.front_left_blow_foot = var17;
      GeneralAirData.front_wind_level = var3;
      double var1;
      String var33;
      boolean var36;
      if (var8 == 0) {
         var33 = "LO";
      } else if (var8 == 31) {
         var33 = "HI";
      } else {
         if (1 <= var8 && var8 < 30) {
            var36 = true;
         } else {
            var36 = false;
         }

         if (var36) {
            var1 = (double)18;
            if (var26) {
               var33 = MsgMgrKt.transToF(var1 + (double)(var8 - 1) * 0.5);
            } else {
               var33 = MsgMgrKt.transToC(var1 + (double)(var8 - 1) * 0.5);
            }
         } else {
            if (33 <= var8 && var8 < 39) {
               var36 = true;
            } else {
               var36 = false;
            }

            if (var36) {
               var1 = (double)15;
               if (var26) {
                  var33 = MsgMgrKt.transToF(var1 + (double)(var8 - 33) * 0.5);
               } else {
                  var33 = MsgMgrKt.transToC(var1 + (double)(var8 - 33) * 0.5);
               }
            } else {
               var33 = CommUtil.getStrByResId(this.getContext(), "set_default");
            }
         }
      }

      GeneralAirData.front_left_temperature = var33;
      if (var7 == 0) {
         var33 = "LO";
      } else if (var7 == 31) {
         var33 = "HI";
      } else {
         if (1 <= var7 && var7 < 30) {
            var36 = true;
         } else {
            var36 = false;
         }

         if (var36) {
            var1 = (double)18;
            var3 = var7 - 1;
            if (var26) {
               var33 = MsgMgrKt.transToF(var1 + (double)var3 * 0.5);
            } else {
               var33 = MsgMgrKt.transToC(var1 + (double)var3 * 0.5);
            }
         } else {
            if (33 <= var7 && var7 < 39) {
               var36 = true;
            } else {
               var36 = false;
            }

            if (var36) {
               var1 = (double)15;
               if (var26) {
                  var33 = MsgMgrKt.transToF(var1 + (double)(var7 - 33) * 0.5);
               } else {
                  var33 = MsgMgrKt.transToC(var1 + (double)(var7 - 33) * 0.5);
               }
            } else {
               var33 = CommUtil.getStrByResId(this.getContext(), "set_default");
            }
         }
      }

      GeneralAirData.front_right_temperature = var33;
      GeneralAirData.pollrn_removal = var24;
      GeneralAirData.aqs = var20;
      GeneralAirData.clean_air = var16;
      GeneralAirData.rear_lock = var15;
      if (var6 == 0) {
         var33 = "LO";
      } else if (var6 == 31) {
         var33 = "HI";
      } else {
         if (1 <= var6 && var6 < 30) {
            var36 = true;
         } else {
            var36 = false;
         }

         if (var36) {
            if (var26) {
               var33 = MsgMgrKt.transToF((double)18 + (double)(var6 - 1) * 0.5);
            } else {
               var33 = MsgMgrKt.transToC((double)18 + (double)(var6 - 1) * 0.5);
            }
         } else {
            if (33 <= var6 && var6 < 39) {
               var36 = true;
            } else {
               var36 = false;
            }

            if (var36) {
               if (var26) {
                  var33 = MsgMgrKt.transToF((double)15 + (double)(var6 - 33) * 0.5);
               } else {
                  var33 = MsgMgrKt.transToC((double)15 + (double)(var6 - 33) * 0.5);
               }
            } else {
               var33 = CommUtil.getStrByResId(this.getContext(), "set_default");
            }
         }
      }

      GeneralAirData.rear_left_temperature = var33;
      GeneralAirData.rear_power = var30;
      GeneralAirData.rear_left_blow_head = var28;
      GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
      GeneralAirData.rear_left_blow_foot = var19;
      GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
      GeneralAirData.rear_auto = var21;
      GeneralAirData.rear_wind_level = var5;
      if (var4 == 0) {
         var33 = "LO";
      } else if (var4 == 31) {
         var33 = "HI";
      } else {
         if (1 <= var4 && var4 < 30) {
            var36 = true;
         } else {
            var36 = false;
         }

         if (var36) {
            var1 = (double)18 + (double)(var4 - 1) * 0.5;
            if (var26) {
               var33 = MsgMgrKt.transToF(var1);
            } else {
               var33 = MsgMgrKt.transToC(var1);
            }
         } else {
            if (33 <= var4 && var4 < 39) {
               var36 = true;
            } else {
               var36 = false;
            }

            if (var36) {
               var1 = (double)15 + (double)(var4 - 33) * 0.5;
               if (var26) {
                  var33 = MsgMgrKt.transToF(var1);
               } else {
                  var33 = MsgMgrKt.transToC(var1);
               }
            } else {
               var33 = CommUtil.getStrByResId(this.getContext(), "set_default");
            }
         }
      }

      GeneralAirData.rear_right_temperature = var33;
      GeneralAirData.front_right_blow_window = var10;
      GeneralAirData.front_right_blow_head = var13;
      GeneralAirData.front_right_blow_foot = var9;
      if (SystemUtil.isForeground(this.getContext(), AirActivity.class.getName())) {
         if (!MsgMgrKt.isEqual(var35, this.lastListForFrontAirConditioner)) {
            airConditioner$updateWithNotPop(this, 1001);
         } else if (!MsgMgrKt.isEqual(var34, this.lastListForRearAirConditioner)) {
            airConditioner$updateWithNotPop(this, 1002);
         }
      } else if (!MsgMgrKt.isEqual(var35, this.lastListForFrontAirConditioner) && var22) {
         airConditioner$updateWithPop(this, 1001);
      } else if (!MsgMgrKt.isEqual(var34, this.lastListForRearAirConditioner) && var22) {
         airConditioner$updateWithPop(this, 1002);
      }

      if (var26 != this.lastValue) {
         if (AirActivity.page == 0) {
            airConditioner$updateWithNotPop(this, 1001);
         } else if (AirActivity.page == 1) {
            airConditioner$updateWithNotPop(this, 1002);
         }

         this.lastValue = var26;
      }

      this.lastListForFrontAirConditioner.clear();
      this.lastListForFrontAirConditioner.addAll((Collection)var35);
      this.lastListForRearAirConditioner.clear();
      this.lastListForRearAirConditioner.addAll((Collection)var34);
   }

   public void auxInInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 0, 0, 0, 0});
   }

   public final void basicInfo() {
      int[] var4 = this.getFrameData();
      byte var1 = 2;
      int var2 = var4[2];
      int var3 = this.getFrameData()[3];
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var2);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(var2);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(var2);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(var2);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var2);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(var2);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(var2);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(var3);
      GeneralDoorData.isLittleLightOn = DataHandleUtils.getBoolBit2(var3);
      if (!DataHandleUtils.getBoolBit3(var3)) {
         var1 = 0;
      }

      GeneralDoorData.skyWindowOpenLevel = var1;
      this.updateDoorView(this.getContext());
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      if (var2 != null && var1 != null) {
         int[] var4 = this.getByteArrayToIntArray(var2);
         Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
         this.setFrameData(var4);
         this.setContext(var1);
         int var3 = this.getFrameData()[1];
         if (var3 != 29) {
            if (var3 != 30) {
               if (var3 != 32) {
                  if (var3 != 42) {
                     if (var3 != 53) {
                        if (var3 != 96) {
                           if (var3 != 98) {
                              if (var3 != 35) {
                                 if (var3 != 36) {
                                    if (var3 != 49) {
                                       if (var3 != 50) {
                                          switch (var3) {
                                             case 38:
                                                this.vehicleSettings();
                                                break;
                                             case 39:
                                                this.past15minFuelConsumption();
                                                break;
                                             case 40:
                                                this.airConditioner();
                                          }
                                       } else {
                                          this.systemInfo();
                                       }
                                    } else {
                                       this.amplifierInfo();
                                    }
                                 } else {
                                    this.basicInfo();
                                 }
                              } else {
                                 this.pastFuelConsumption();
                              }
                           } else {
                              this.mediaInfo();
                           }
                        } else {
                           this.controlModeInfo();
                        }
                     } else {
                        this.steerAngle();
                     }
                  } else {
                     this.vehicleInfo();
                  }
               } else {
                  this.steerBtn();
               }
            } else {
               this.rearRadar();
            }
         } else {
            this.frontRadar();
         }
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 0, 0, 0, 0});
   }

   public final void frontRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(10, this.assignRadarProgress(this.getFrameData()[2]), this.assignRadarProgress(this.getFrameData()[3]), this.assignRadarProgress(this.getFrameData()[4]), this.assignRadarProgress(this.getFrameData()[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
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

   public final UiMgr getMUiMgr() {
      UiMgr var1 = this.mUiMgr;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         return null;
      }
   }

   public final void initItemsIndexHashMap(Context var1) {
      UiMgr var7 = this.getMUiMgr();
      List var6 = var7.getSettingUiSet(var1).getList();
      Iterator var8 = var6.iterator();

      int var2;
      int var3;
      for(var2 = 0; var8.hasNext(); ++var2) {
         Iterator var4 = ((SettingPageUiSet.ListBean)var8.next()).getItemList().iterator();

         for(var3 = 0; var4.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var9 = (SettingPageUiSet.ListBean.ItemListBean)var4.next();
            Map var5 = (Map)this.mSettingItemIndexHashMap;
            String var18 = var9.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var18, "itemListBean.titleSrn");
            Object var10 = ((SettingPageUiSet.ListBean)var6.get(var2)).getItemList().get(var3);
            Intrinsics.checkNotNullExpressionValue(var10, "this[left].itemList[right]");
            var5.put(var18, var10);
         }
      }

      List var11 = var7.getDriverDataPageUiSet(var1).getList();
      Iterator var14 = var11.iterator();

      for(var2 = 0; var14.hasNext(); ++var2) {
         Iterator var13 = ((DriverDataPageUiSet.Page)var14.next()).getItemList().iterator();

         for(var3 = 0; var13.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var15 = (DriverDataPageUiSet.Page.Item)var13.next();
            Map var12 = (Map)this.mDriveItemIndexHashMap;
            String var16 = var15.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var16, "item.titleSrn");
            Object var17 = ((DriverDataPageUiSet.Page)var11.get(var2)).getItemList().get(var3);
            Intrinsics.checkNotNullExpressionValue(var17, "this[pageIndex].itemList[itemIndex]");
            var12.put(var16, var17);
         }
      }

   }

   public final void mediaInfo() {
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 != 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)4, (byte)var17, 0, 0, 0});
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      List var9 = (List)(new ArrayList());
      if (var2 != null) {
         byte var10;
         label42: {
            switch (var2.hashCode()) {
               case 64901:
                  if (!var2.equals("AM1")) {
                     return;
                  }
                  break;
               case 64902:
                  if (!var2.equals("AM2")) {
                     return;
                  }
                  break;
               case 69706:
                  if (!var2.equals("FM1")) {
                     return;
                  }

                  var10 = 1;
                  break label42;
               case 69707:
                  if (var2.equals("FM2")) {
                     var10 = 2;
                     break label42;
                  }

                  return;
               default:
                  return;
            }

            var10 = 16;
         }

         int var6;
         int var7;
         if (var10 != 1 && var10 != 2) {
            if (var10 == 16) {
               Intrinsics.checkNotNull(var3);
               var7 = Integer.parseInt(var3);
               var6 = DataHandleUtils.getMsb(var7);
               var7 = DataHandleUtils.getLsb(var7);
               var9.add((byte)var10);
               var9.add((byte)var6);
               var9.add((byte)var7);
            }
         } else {
            Intrinsics.checkNotNull(var3);
            var7 = (int)(Double.parseDouble(var3) * (double)100);
            var6 = DataHandleUtils.getMsb(var7);
            var7 = DataHandleUtils.getLsb(var7);
            var9.add((byte)var10);
            var9.add((byte)var6);
            var9.add((byte)var7);
         }

         var9.add((byte)var1);
         byte[] var8 = CollectionsKt.toByteArray((Collection)var9);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -64, 1}, var8));
      }

   }

   public final void rearRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(10, this.assignRadarProgress(this.getFrameData()[2]), this.assignRadarProgress(this.getFrameData()[3]), this.assignRadarProgress(this.getFrameData()[4]), this.assignRadarProgress(this.getFrameData()[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
      Object var1 = this.mSettingItemIndexHashMap.get("_350_s_5_0");
      Intrinsics.checkNotNull(var1);
      ((SettingPageUiSet.ListBean.ItemListBean)var1).setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrameData()[6])));
      var1 = this.mSettingItemIndexHashMap.get("_350_s_5_1");
      Intrinsics.checkNotNull(var1);
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)var1;
      var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getBoolBit6(this.getFrameData()[6])));
      var1 = this.mSettingItemIndexHashMap.get("_350_s_5_2");
      Intrinsics.checkNotNull(var1);
      ((SettingPageUiSet.ListBean.ItemListBean)var1).setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(this.getFrameData()[6])));
      var1 = this.mSettingItemIndexHashMap.get("_350_s_5_3");
      Intrinsics.checkNotNull(var1);
      var2 = (SettingPageUiSet.ListBean.ItemListBean)var1;
      var2.setProgress(DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[6], 0, 3));
      var2.setValue(String.valueOf(var2.getProgress()));
      this.updateSettingActivity((Bundle)null);
   }

   public final void rockerInfo() {
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

   public final void setMUiMgr(UiMgr var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mUiMgr = var1;
   }

   public final void steerAngle() {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)this.getFrameData()[3], (byte)this.getFrameData()[2], 0, 380, 12);
      this.updateParkUi((Bundle)null, this.getContext());
   }

   public final void systemInfo() {
      boolean var2 = DataHandleUtils.getBoolBit0(this.getFrameData()[2]);
      boolean var5 = DataHandleUtils.getBoolBit1(this.getFrameData()[2]);
      boolean var4 = DataHandleUtils.getBoolBit2(this.getFrameData()[2]);
      boolean var1 = DataHandleUtils.getBoolBit3(this.getFrameData()[2]);
      boolean var6 = DataHandleUtils.getBoolBit6(this.getFrameData()[2]);
      boolean var3 = DataHandleUtils.getBoolBit7(this.getFrameData()[2]);
      Object var7 = this.mDriveItemIndexHashMap.get("_350_d_3_0");
      Intrinsics.checkNotNull(var7);
      DriverDataPageUiSet.Page.Item var8 = (DriverDataPageUiSet.Page.Item)var7;
      String var9;
      if (var2) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_1");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var5) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_2");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      Context var10 = this.getContext();
      if (var4) {
         var9 = CommUtil.getStrByResId(var10, "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(var10, "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_3");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      var10 = this.getContext();
      if (var1) {
         var9 = CommUtil.getStrByResId(var10, "_350_u_2");
      } else {
         var9 = CommUtil.getStrByResId(var10, "_350_u_3");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_4");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var6) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_1");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_0");
      }

      var8.setValue(var9);
      var7 = this.mDriveItemIndexHashMap.get("_350_d_3_5");
      Intrinsics.checkNotNull(var7);
      var8 = (DriverDataPageUiSet.Page.Item)var7;
      if (var3) {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_1");
      } else {
         var9 = CommUtil.getStrByResId(this.getContext(), "_350_u_0");
      }

      var8.setValue(var9);
      this.updateDriveDataActivity((Bundle)null);
      if (var1) {
         this.forceReverse(this.getContext(), true);
      } else {
         this.forceReverse(this.getContext(), false);
      }

   }

   public final void versionInfo() {
   }
}
