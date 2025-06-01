package com.hzbhd.canbus.car._163;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\t\n\u0002\b+\u0018\u0000 \u008e\u00012\u00020\u0001:\u0002\u008e\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\"\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010$\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010%\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016JP\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\n2\u0006\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-H\u0016J\"\u0010.\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0018\u0010/\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\nH\u0016J\u0018\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\"H\u0016Jp\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00132\u0006\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\"2\u0006\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020\"2\u0006\u0010A\u001a\u00020\u0013H\u0016J\b\u0010B\u001a\u00020\u001cH\u0002J\b\u0010C\u001a\u00020\u001cH\u0002J8\u0010D\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u001a2\u0006\u0010H\u001a\u00020\u001a2\u0006\u0010I\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020\u001a2\u0006\u0010K\u001a\u00020\u001aH\u0002J\u000e\u0010L\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010M\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\"H\u0002J\u000e\u0010O\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010P\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0016J\b\u0010Q\u001a\u00020\"H\u0002J\u0010\u0010R\u001a\u00020\"2\u0006\u00100\u001a\u00020\nH\u0002J\u0010\u0010S\u001a\u00020\"2\u0006\u00100\u001a\u00020\nH\u0002J\u0006\u0010T\u001a\u00020\u001cJ¸\u0001\u0010U\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020\u001a2\u0006\u0010[\u001a\u00020\u001a2\u0006\u0010\\\u001a\u00020\u001a2\u0006\u0010]\u001a\u00020\u001a2\u0006\u0010^\u001a\u00020\u001a2\u0006\u0010_\u001a\u00020\u001a2\u0006\u0010`\u001a\u00020F2\u0006\u0010a\u001a\u00020F2\u0006\u0010b\u001a\u00020F2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u001a2\u0006\u0010f\u001a\u00020\u00132\u0006\u0010g\u001a\u00020\"2\u0006\u0010h\u001a\u00020d2\u0006\u0010i\u001a\u00020F2\u0006\u0010j\u001a\u00020F2\u0006\u0010k\u001a\u00020F2\u0006\u0010l\u001a\u00020\"H\u0016J\b\u0010m\u001a\u00020\u001cH\u0002J\u0010\u0010n\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\nH\u0002J0\u0010o\u001a\u00020\u001c2\u0006\u0010p\u001a\u00020\u00132\u0006\u0010E\u001a\u00020F2\u0006\u0010q\u001a\u00020F2\u0006\u0010r\u001a\u00020F2\u0006\u0010s\u001a\u00020\u0013H\u0016J\u0010\u0010t\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010u\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010v\u001a\u00020F2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001a\u0010w\u001a\u00020\u001c2\u0006\u0010x\u001a\u00020\u00132\b\u0010 \u001a\u0004\u0018\u00010\nH\u0002J\u001a\u0010y\u001a\u00020\u001c2\u0006\u0010x\u001a\u00020\u00132\b\b\u0002\u0010z\u001a\u00020\nH\u0002J\u0010\u0010{\u001a\u00020\u001c2\u0006\u0010|\u001a\u00020\nH\u0002J\b\u0010}\u001a\u00020\u001cH\u0002J\b\u0010~\u001a\u00020\u001cH\u0002J\b\u0010\u007f\u001a\u00020\u001cH\u0002J\t\u0010\u0080\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0081\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0082\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0083\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0084\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0085\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0086\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0087\u0001\u001a\u00020\u001cH\u0002J\u0012\u0010\u0088\u0001\u001a\u00020\u001c2\u0007\u0010\u0089\u0001\u001a\u00020\"H\u0016J\u0007\u0010\u008a\u0001\u001a\u00020\u001cJ\u0093\u0001\u0010\u008b\u0001\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020\u001a2\u0006\u0010[\u001a\u00020\u001a2\u0006\u0010\\\u001a\u00020\u001a2\u0006\u0010]\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001a2\u0006\u0010_\u001a\u00020\u001a2\u0006\u0010`\u001a\u00020F2\u0006\u0010a\u001a\u00020F2\u0006\u0010b\u001a\u00020F2\u0006\u0010c\u001a\u00020\u00132\u0007\u0010\u008c\u0001\u001a\u00020\u001a2\u0006\u0010g\u001a\u00020\"2\u0007\u0010\u008d\u0001\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u008f\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_163/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mAirData", "", "getMAirData", "()[I", "setMAirData", "([I)V", "mCanBusInfoByte", "", "mCanBusInfoInt", "mCanbusAirInfoCopy", "mCanbusDoorInfoCopy", "mContext", "Landroid/content/Context;", "mUiMgr", "Lcom/hzbhd/canbus/car/_163/UiMgr;", "value", "", "mVolume", "getMVolume", "()I", "setMVolume", "(I)V", "x22D1", "", "afterServiceNormalSetting", "", "context", "btMusicInfoChange", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "driveData0", "driveData1", "getAllBandTypeData", "currBand", "", "fm1", "fm2", "fm3", "am1", "am2", "getBits", "getBoolResult", "data", "getTens", "initCommand", "isAirDataNoChange", "isAirMsgRepeatRenault", "isDoorMsgRepeatRenault", "languageSet", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "originalStatus", "panelKnob", "radioInfoChange", "currClickPresetIndex", "currentFreq", "psName", "isStereo", "realKeyClick", "realKeyClick2", "resolveLeftAndRightTemp", "sendBtPhoneData", "d0", "sendMediaSource", "d1t12", "sendTextData", "byteArray", "set0x23Data", "setAirData0x31", "setCarData", "setCarInfo", "setCarInfoData2", "setDoorData0x12", "setPanelBtnKey", "setRadar", "setSwc", "setTrack", "setVersionInfo", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettingData", "videoInfoChange", "playMode", "duation", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static boolean isAirFirst = true;
   private static boolean isDoorFirst = true;
   private int[] mAirData = new int[0];
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private UiMgr mUiMgr;
   private int mVolume = this.getVolume();
   private byte x22D1;

   private final void driveData0() {
      int[] var3 = this.mCanBusInfoInt;
      int[] var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var2 = null;
      }

      int var1 = var2[10];
      boolean var6;
      if (128 <= var1 && var1 < 159) {
         var6 = true;
      } else {
         var6 = false;
      }

      StringBuilder var4;
      Context var7;
      Context var8;
      String var9;
      if (var6) {
         var8 = this.mContext;
         var7 = var8;
         if (var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var7 = null;
         }

         var9 = var7.getResources().getString(2131758082);
         Intrinsics.checkNotNullExpressionValue(var9, "mContext.resources.getSt….string._163_drive_data7)");
         var4 = (new StringBuilder()).append(var9);
         var3 = this.mCanBusInfoInt;
         var2 = var3;
         if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var2 = null;
         }

         var9 = var4.append(var2[10] - 128).append('°').toString();
      } else {
         var4 = new StringBuilder();
         var8 = this.mContext;
         var7 = var8;
         if (var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var7 = null;
         }

         var4 = var4.append(var7.getResources().getString(2131758081));
         var3 = this.mCanBusInfoInt;
         var2 = var3;
         if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var2 = null;
         }

         var9 = var4.append(var2[10]).append('°').toString();
      }

      int[] var10 = this.mCanBusInfoInt;
      var3 = var10;
      if (var10 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      var1 = var3[11];
      if (128 <= var1 && var1 < 159) {
         var6 = true;
      } else {
         var6 = false;
      }

      StringBuilder var5;
      Context var11;
      String var12;
      if (var6) {
         var11 = this.mContext;
         var8 = var11;
         if (var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var8 = null;
         }

         var12 = var8.getResources().getString(2131758083);
         Intrinsics.checkNotNullExpressionValue(var12, "mContext.resources.getSt….string._163_drive_data8)");
         var5 = (new StringBuilder()).append(var12);
         var10 = this.mCanBusInfoInt;
         var3 = var10;
         if (var10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         var12 = var5.append(var3[11] - 128).append('°').toString();
      } else {
         var5 = new StringBuilder();
         var11 = this.mContext;
         var8 = var11;
         if (var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var8 = null;
         }

         var5 = var5.append(var8.getResources().getString(2131758084));
         var10 = this.mCanBusInfoInt;
         var3 = var10;
         if (var10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         var12 = var5.append(var3[11]).append('°').toString();
      }

      List var13 = (List)(new ArrayList());
      var13.add(new DriverUpdateEntity(0, 0, var9));
      var13.add(new DriverUpdateEntity(0, 1, var12));
      this.updateGeneralDriveData(var13);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void driveData1() {
      List var11 = (List)(new ArrayList());
      int[] var4 = this.mCanBusInfoInt;
      int[] var3 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      int var1 = var3[2];
      var4 = this.mCanBusInfoInt;
      var3 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      int var2 = var3[3];
      String var9 = "----";
      String var12;
      if (var1 * 256 + var2 == 65535) {
         var12 = "----";
      } else {
         StringBuilder var5 = new StringBuilder();
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         var1 = var3[2];
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         var12 = var5.append((float)((double)(var1 * 256 + var3[3]) * 0.1)).append(" L/100Km").toString();
      }

      int[] var13 = this.mCanBusInfoInt;
      var4 = var13;
      if (var13 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var1 = var4[4];
      var13 = this.mCanBusInfoInt;
      var4 = var13;
      if (var13 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      String var15;
      if (var1 * 256 + var4[5] == 65535) {
         var15 = "----";
      } else {
         StringBuilder var6 = new StringBuilder();
         var13 = this.mCanBusInfoInt;
         var4 = var13;
         if (var13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var1 = var4[4];
         var13 = this.mCanBusInfoInt;
         var4 = var13;
         if (var13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var15 = var6.append((float)((double)(var1 * 256 + var4[5]) * 0.1)).append(" Km/h").toString();
      }

      int[] var14 = this.mCanBusInfoInt;
      var13 = var14;
      if (var14 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var13 = null;
      }

      var2 = var13[6];
      var14 = this.mCanBusInfoInt;
      var13 = var14;
      if (var14 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var13 = null;
      }

      var1 = var13[7];
      var14 = this.mCanBusInfoInt;
      var13 = var14;
      if (var14 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var13 = null;
      }

      String var19;
      if (var2 * 256 * 256 + var1 * 256 + var13[8] == 16777215) {
         var19 = "----";
      } else {
         StringBuilder var7 = new StringBuilder();
         var14 = this.mCanBusInfoInt;
         var13 = var14;
         if (var14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var13 = null;
         }

         var1 = var13[6];
         var14 = this.mCanBusInfoInt;
         var13 = var14;
         if (var14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var13 = null;
         }

         var2 = var13[7];
         var14 = this.mCanBusInfoInt;
         var13 = var14;
         if (var14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var13 = null;
         }

         var19 = var7.append((float)((double)(var1 * 256 * 256 + var2 * 256 + var13[8]) * 0.1)).append(" Km").toString();
      }

      int[] var16 = this.mCanBusInfoInt;
      var14 = var16;
      if (var16 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      var1 = var14[10];
      var16 = this.mCanBusInfoInt;
      var14 = var16;
      if (var16 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      String var21;
      label144: {
         if (var1 * 256 + var14[11] != 65535) {
            var16 = this.mCanBusInfoInt;
            var14 = var16;
            if (var16 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
               var14 = null;
            }

            if (var14[9] != 255) {
               StringBuilder var8 = new StringBuilder();
               var16 = this.mCanBusInfoInt;
               var14 = var16;
               if (var16 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                  var14 = null;
               }

               var1 = var14[10];
               var16 = this.mCanBusInfoInt;
               var14 = var16;
               if (var16 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                  var14 = null;
               }

               var8 = var8.append(var1 * 256 + var14[11]).append(" : ");
               var16 = this.mCanBusInfoInt;
               var14 = var16;
               if (var16 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                  var14 = null;
               }

               var21 = var8.append(var14[9]).toString();
               break label144;
            }
         }

         var21 = "----";
      }

      int[] var17 = this.mCanBusInfoInt;
      var16 = var17;
      if (var17 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var16 = null;
      }

      var1 = var16[12];
      var17 = this.mCanBusInfoInt;
      var16 = var17;
      if (var17 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var16 = null;
      }

      StringBuilder var10;
      String var22;
      if (var1 * 256 + var16[13] == 65535) {
         var22 = "----";
      } else {
         var10 = new StringBuilder();
         var17 = this.mCanBusInfoInt;
         var16 = var17;
         if (var17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var16 = null;
         }

         var1 = var16[12];
         var17 = this.mCanBusInfoInt;
         var16 = var17;
         if (var17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var16 = null;
         }

         var22 = var10.append((float)((double)(var1 * 256 + var16[13]) * 0.1)).append(" L").toString();
      }

      int[] var20 = this.mCanBusInfoInt;
      var17 = var20;
      if (var20 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var17 = null;
      }

      var1 = var17[14];
      var20 = this.mCanBusInfoInt;
      var17 = var20;
      if (var20 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var17 = null;
      }

      String var23;
      if (var1 * 256 + var17[15] == 65535) {
         var23 = var9;
      } else {
         var10 = new StringBuilder();
         int[] var18 = this.mCanBusInfoInt;
         var17 = var18;
         if (var18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var17 = null;
         }

         var1 = var17[14];
         var18 = this.mCanBusInfoInt;
         var17 = var18;
         if (var18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var17 = null;
         }

         var23 = var10.append((float)((double)(var1 * 256 + var17[15]) * 0.1)).append(" Km").toString();
      }

      for(var1 = 0; var1 < 6; ++var1) {
         var11.add(new DriverUpdateEntity(1, var1, (new String[]{var12, var15, var19, var21, var22, var23})[var1]));
      }

      this.updateGeneralDriveData(var11);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      switch (var1) {
         case "LW":
            return var5;
         case "MW":
            return var6;
         case "AM1":
            return var5;
         case "AM2":
            return var6;
         case "FM1":
            return var2;
         case "FM2":
            return var3;
         case "FM3":
            return var4;
         case "OIRT":
            return var4;
      }

      return 0;
   }

   private final int getBoolResult(boolean var1) {
      return var1;
   }

   private final boolean isAirDataNoChange() {
      int[] var4 = this.mAirData;
      int[] var3 = this.mCanBusInfoInt;
      Object var2 = null;
      int[] var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (Arrays.equals(var4, var1)) {
         return true;
      } else {
         var1 = this.mCanBusInfoInt;
         if (var1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var1 = (int[])var2;
         }

         this.mAirData = var1;
         return false;
      }
   }

   private final boolean isAirMsgRepeatRenault(byte[] var1) {
      byte[] var5 = this.mCanbusAirInfoCopy;
      byte[] var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanbusAirInfoCopy");
         var4 = null;
      }

      boolean var3 = Arrays.equals(var1, var4);
      boolean var2 = false;
      if (!var3) {
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(this, newSize)");
         this.mCanbusAirInfoCopy = var1;
         if (!isAirFirst) {
            return var2;
         }

         isAirFirst = false;
      }

      var2 = true;
      return var2;
   }

   private final boolean isDoorMsgRepeatRenault(byte[] var1) {
      byte[] var5 = this.mCanbusDoorInfoCopy;
      byte[] var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanbusDoorInfoCopy");
         var4 = null;
      }

      boolean var3 = Arrays.equals(var1, var4);
      boolean var2 = false;
      if (!var3) {
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(this, newSize)");
         this.mCanbusDoorInfoCopy = var1;
         if (!isDoorFirst) {
            return var2;
         }

         isDoorFirst = false;
      }

      var2 = true;
      return var2;
   }

   private final void originalStatus() {
      List var7 = (List)(new ArrayList());
      Context var5 = this.mContext;
      Context var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var4 = null;
      }

      int[] var6 = this.mCanBusInfoInt;
      int[] var9 = var6;
      if (var6 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var9 = null;
      }

      int var1 = var9[5];
      boolean var3 = false;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.forceReverse(var4, var2);
      var9 = this.mCanBusInfoInt;
      int[] var8 = var9;
      if (var9 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var8 = null;
      }

      if (var8[3] == 5) {
         var2 = true;
      } else {
         var2 = false;
      }

      var7.add(new PanoramicBtnUpdateEntity(0, var2));
      var9 = this.mCanBusInfoInt;
      var8 = var9;
      if (var9 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var8 = null;
      }

      if (var8[3] == 6) {
         var2 = true;
      } else {
         var2 = false;
      }

      var7.add(new PanoramicBtnUpdateEntity(1, var2));
      var9 = this.mCanBusInfoInt;
      var8 = var9;
      if (var9 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var8 = null;
      }

      if (var8[3] == 7) {
         var2 = true;
      } else {
         var2 = false;
      }

      var7.add(new PanoramicBtnUpdateEntity(2, var2));
      var9 = this.mCanBusInfoInt;
      var8 = var9;
      if (var9 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var8 = null;
      }

      var2 = var3;
      if (var8[3] == 8) {
         var2 = true;
      }

      var7.add(new PanoramicBtnUpdateEntity(3, var2));
      GeneralParkData.dataList = var7;
      var5 = this.mContext;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var4 = null;
      }

      this.updateParkUi((Bundle)null, var4);
   }

   private final void panelKnob(byte[] var1) {
      byte var5 = var1[3];
      byte var3 = this.x22D1;
      int var2 = Math.abs(var5 - var3);
      int[] var11 = this.mCanBusInfoInt;
      Object var8 = null;
      Object var10 = null;
      Object var7 = null;
      Object var9 = null;
      int[] var6 = var11;
      if (var11 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var6 = null;
      }

      int var4 = var6[2];
      Context var12;
      if (var4 != 1) {
         if (var4 == 2) {
            if (var5 > var3) {
               var12 = this.mContext;
               if (var12 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("mContext");
                  var12 = (Context)var9;
               }

               DataHandleUtils.knob(var12, 46, var2);
            } else if (var5 < var3) {
               var12 = this.mContext;
               if (var12 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("mContext");
                  var12 = (Context)var8;
               }

               DataHandleUtils.knob(var12, 45, var2);
            }
         }
      } else if (var5 > var3) {
         var12 = this.mContext;
         if (var12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var12 = (Context)var10;
         }

         DataHandleUtils.knob(var12, 7, var2);
      } else if (var5 < var3) {
         var12 = this.mContext;
         if (var12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var12 = (Context)var7;
         }

         DataHandleUtils.knob(var12, 8, var2);
      }

      this.x22D1 = var1[3];
   }

   private final void realKeyClick(int var1) {
      Context var4 = this.mContext;
      int[] var3 = null;
      Context var2 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var2 = null;
      }

      int[] var5 = this.mCanBusInfoInt;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
      } else {
         var3 = var5;
      }

      this.realKeyLongClick1(var2, var1, var3[5]);
   }

   private final void realKeyClick2(int var1) {
      Context var4 = this.mContext;
      int[] var3 = null;
      Context var2 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var2 = null;
      }

      int[] var5 = this.mCanBusInfoInt;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
      } else {
         var3 = var5;
      }

      this.realKeyLongClick1(var2, var1, var3[3]);
   }

   private final String resolveLeftAndRightTemp(int var1) {
      String var5;
      if (var1 != 254) {
         if (var1 != 255) {
            StringBuilder var4 = (new StringBuilder()).append((float)var1 * 0.5F);
            Context var3 = this.mContext;
            Context var2 = var3;
            if (var3 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mContext");
               var2 = null;
            }

            var5 = var4.append(this.getTempUnitC(var2)).toString();
         } else {
            var5 = "HI";
         }
      } else {
         var5 = "LO";
      }

      return var5;
   }

   private final void sendBtPhoneData(int var1, byte[] var2) {
      List var4 = (List)(new ArrayList());
      var4.add((byte)var1);
      Byte var3 = 0;
      var4.add(var3);
      var4.add(var3);
      byte[] var5 = CollectionsKt.toByteArray((Collection)var4);
      if (var2 != null) {
         var2 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(ArraysKt.plus(var5, var2), 27, 0, 4, (Object)null);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -51}, var2));
      }
   }

   private final void sendMediaSource(int var1, byte[] var2) {
      var2 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict(ArraysKt.plus(new byte[]{(byte)var1}, var2), 13, 32);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -31}, var2));
   }

   // $FF: synthetic method
   static void sendMediaSource$default(MsgMgr var0, int var1, byte[] var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = new byte[0];
      }

      var0.sendMediaSource(var1, var2);
   }

   private final void sendTextData(byte[] var1) {
      var1 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(var1, 32, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -28}, var1));
   }

   private final void set0x23Data() {
      SettingPageUiSet.ListBean.ItemListBean var3 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_x2A_1");
      if (var3 != null) {
         List var4 = var3.getValueSrnArray();
         int[] var2 = this.mCanBusInfoInt;
         int[] var1 = var2;
         if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var1 = null;
         }

         var3.setValue(var4.get(var1[2]));
      }

      this.updateSettingData();
   }

   private final void setAirData0x31() {
      if (!this.isAirDataNoChange()) {
         int[] var4 = this.mCanBusInfoInt;
         Object var5 = null;
         int[] var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.power = DataHandleUtils.getBoolBit6(var3[2]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.rear = DataHandleUtils.getBoolBit4(var3[2]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var3[2]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.sync = DataHandleUtils.getBoolBit2(var3[2]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         int var1 = var3[2];
         boolean var2 = false;
         if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 2) == 1) {
            var2 = true;
         }

         GeneralAirData.ac = var2;
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(var3[3]) ^ true;
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(var3[3]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(var3[4]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var3[4]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.fast = DataHandleUtils.getBoolBit1(var3[5]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.soft = DataHandleUtils.getBoolBit0(var3[5]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.front_wind_level = var3[7];
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         var1 = var3[6];
         com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        switch (var1) {
                           case 11:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              break;
                           case 12:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_right_blow_foot = true;
                              break;
                           case 13:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_right_blow_head = true;
                              break;
                           case 14:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_left_blow_head = true;
                              GeneralAirData.front_right_blow_head = true;
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_right_blow_foot = true;
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
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
         }

         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(var3[8]);
         var4 = this.mCanBusInfoInt;
         var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var3 = null;
         }

         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(var3[9]);
         Context var8 = this.mContext;
         Context var9 = var8;
         if (var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var9 = null;
         }

         StringBuilder var7 = new StringBuilder();
         int[] var6 = this.mCanBusInfoInt;
         var4 = var6;
         if (var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         this.updateOutDoorTemp(var9, var7.append((float)((double)var4[13] * 0.5 - (double)40)).append(" °C").toString());
         var9 = this.mContext;
         if (var9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var9 = (Context)var5;
         }

         this.updateAirActivity(var9, 1004);
      }
   }

   private final void setCarData() {
      SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S18_Car_0");
      if (var6 != null) {
         int[] var5 = this.mCanBusInfoInt;
         int[] var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         int var3 = var4[3];
         boolean var2 = false;
         boolean var1 = var2;
         if (17 <= var3) {
            var1 = var2;
            if (var3 < 30) {
               var1 = true;
            }
         }

         if (var1) {
            List var7 = var6.getValueSrnArray();
            var5 = this.mCanBusInfoInt;
            var4 = var5;
            if (var5 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
               var4 = null;
            }

            var6.setValue(var7.get(var4[3] - 16));
         }
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void setCarInfo() {
      int[] var3 = this.mCanBusInfoInt;
      int[] var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var2 = null;
      }

      SettingPageUiSet.ListBean.ItemListBean var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_1");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[2], 5, 3)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_2");
      int var1;
      if (var5 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var2[2], 0, 5);
         var5.setProgress(var1);
         var5.setValue(String.valueOf(var1));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_3");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(var2[3])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_4");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit6(var2[3])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_5");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit5(var2[3])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_6");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit4(var2[3])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_7");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[4], 5, 3)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_8");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[4], 3, 2)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_9");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[4], 0, 3)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_10");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[5], 5, 3)));
      }

      SettingPageUiSet.ListBean.ItemListBean var4 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_11");
      if (var4 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var2[5], 2, 3);
         List var6 = var4.getValueSrnArray();
         if (var1 != 0) {
            var1 -= 2;
         } else {
            var1 = 0;
         }

         var4.setValue(var6.get(var1));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_13");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[6], 6, 2)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_14");
      if (var5 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var2[6], 3, 3);
         var5.setProgress(var1 - 1);
         var5.setValue(String.valueOf(var1));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_15");
      if (var5 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var2[6], 0, 3);
         var5.setProgress(var1 - 1);
         var5.setValue(String.valueOf(var1));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_12");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(var2[7])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_16");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(var2[8])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_17");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit6(var2[8])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_18");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit5(var2[8])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_19");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit4(var2[8])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_20");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[8], 2, 2)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_21");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit1(var2[8])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_22");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit0(var2[8])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_23");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(var2[9])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_24");
      if (var5 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var2[9], 0, 5);
         var5.setProgress(var1);
         var5.setValue(String.valueOf(var1));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_26");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(var2[10])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_27");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[10], 5, 2)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_28");
      if (var5 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var2[10], 0, 5);
         var5.setProgress(var1);
         var5.setValue(String.valueOf(var1));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_29");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(var2[11])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_30");
      if (var5 != null) {
         var5.setValue(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit6(var2[11])));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_31");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[11], 4, 2)));
      }

      var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_163_setting_32");
      if (var5 != null) {
         var5.setValue(var5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var2[11], 2, 2)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void setCarInfoData2() {
      int[] var15 = this.mCanBusInfoInt;
      int[] var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var10 = DataHandleUtils.getIntFromByteWithBit(var14[2], 7, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var7 = DataHandleUtils.getIntFromByteWithBit(var14[2], 6, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var4 = DataHandleUtils.getIntFromByteWithBit(var14[2], 5, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var6 = DataHandleUtils.getIntFromByteWithBit(var14[2], 4, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var3 = DataHandleUtils.getIntFromByteWithBit(var14[2], 3, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var12 = DataHandleUtils.getIntFromByteWithBit(var14[2], 2, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(var14[2], 1, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var11 = DataHandleUtils.getIntFromByteWithBit(var14[2], 0, 1);
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var8 = var14[3];
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var9 = var14[4];
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var13 = var14[5];
      var15 = this.mCanBusInfoInt;
      var14 = var15;
      if (var15 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var2 = DataHandleUtils.getIntFromByteWithBit(var14[6], 7, 1);
      var14 = this.mCanBusInfoInt;
      if (var14 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var14 = null;
      }

      int var5 = DataHandleUtils.getIntFromByteWithBit(var14[6], 4, 3);
      SettingPageUiSet.ListBean.ItemListBean var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_1");
      if (var16 != null) {
         var16.setValue(var10);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_2");
      if (var16 != null) {
         var16.setValue(var7);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_3");
      if (var16 != null) {
         var16.setValue(var4);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_4");
      if (var16 != null) {
         var16.setValue(var6);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_5");
      if (var16 != null) {
         var16.setValue(var3);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_6");
      if (var16 != null) {
         var16.setValue(var12);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_7");
      if (var16 != null) {
         var16.setValue(var16.getValueSrnArray().get(var1));
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_8");
      if (var16 != null) {
         var16.setValue(var11);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_9");
      if (var16 != null) {
         var16.setValue(var8);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_10");
      if (var16 != null) {
         var16.setValue(var9);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_11");
      if (var16 != null) {
         var16.setValue(var13);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_12");
      if (var16 != null) {
         var16.setValue(var2);
      }

      var16 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_13");
      if (var16 != null) {
         var16.setValue(var16.getValueSrnArray().get(var5));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void setDoorData0x12() {
      int[] var3 = this.mCanBusInfoInt;
      Object var2 = null;
      int[] var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[4]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[4]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(var1[4]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(var1[4]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[4]);
      Context var4 = this.mContext;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var4 = (Context)var2;
      }

      this.updateDoorView(var4);
   }

   private final void setPanelBtnKey() {
      int[] var4 = this.mCanBusInfoInt;
      Object var3 = null;
      int[] var2 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var2 = null;
      }

      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 4) {
            if (var1 != 6) {
               if (var1 == 32) {
                  this.realKeyClick2(128);
                  return;
               }

               if (var1 == 33) {
                  this.realKeyClick2(128);
                  return;
               }

               if (var1 == 69) {
                  this.realKeyClick2(7);
                  return;
               }

               if (var1 == 70) {
                  this.realKeyClick2(8);
                  return;
               }

               if (var1 == 91) {
                  this.realKeyClick2(59);
                  return;
               }

               if (var1 == 92) {
                  this.realKeyClick2(2);
                  return;
               }

               switch (var1) {
                  case 6:
                     break;
                  case 9:
                     this.realKeyClick2(3);
                     return;
                  case 18:
                     Context var5 = this.mContext;
                     if (var5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        var5 = (Context)var3;
                     }

                     this.startMainActivity(var5);
                     return;
                  case 37:
                     this.realKeyClick2(27);
                     return;
                  case 43:
                     this.realKeyClick2(52);
                     return;
                  case 49:
                     this.realKeyClick2(57);
                     return;
                  case 55:
                     this.realKeyClick2(58);
                     return;
                  case 57:
                     this.realKeyClick2(57);
                     return;
                  case 84:
                     this.realKeyClick2(128);
                     return;
                  default:
                     switch (var1) {
                        case 22:
                           this.realKeyClick2(49);
                           return;
                        case 23:
                           this.realKeyClick2(45);
                           return;
                        case 24:
                           this.realKeyClick2(46);
                           return;
                        case 25:
                           this.realKeyClick2(47);
                           return;
                        case 26:
                           this.realKeyClick2(48);
                           return;
                        default:
                           return;
                     }
               }
            }

            this.realKeyClick2(50);
         } else {
            this.realKeyClick2(151);
         }
      } else {
         this.realKeyClick2(0);
      }

   }

   private final void setRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = false;
      RadarInfoUtil.mDisableData = 255;
      int[] var5 = this.mCanBusInfoInt;
      int[] var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var1 = var4[2];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var2 = var4[3];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var3 = var4[4];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      RadarInfoUtil.setRearRadarLocationData(4, var1, var2, var3, var4[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      Context var6 = this.mContext;
      Context var7 = var6;
      if (var6 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var7 = null;
      }

      this.updateParkUi((Bundle)null, var7);
   }

   private final void setSwc() {
      int[] var3 = this.mCanBusInfoInt;
      int[] var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var2 = null;
      }

      int var1 = var2[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 != 13) {
                                 if (var1 != 14) {
                                    if (var1 != 16) {
                                       if (var1 != 17) {
                                          if (var1 == 24) {
                                             this.realKeyClick(187);
                                          }
                                       } else {
                                          this.realKeyClick(2);
                                       }
                                    } else {
                                       this.realKeyClick(199);
                                    }
                                 } else {
                                    this.realKeyClick(46);
                                 }
                              } else {
                                 this.realKeyClick(45);
                              }
                           } else {
                              this.realKeyClick(47);
                           }
                        } else {
                           this.realKeyClick(48);
                        }
                     } else {
                        this.realKeyClick(95);
                     }
                  } else {
                     this.realKeyClick(128);
                  }
               } else {
                  this.realKeyClick(464);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private final void setTrack() {
      byte[] var3 = this.mCanBusInfoByte;
      byte[] var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
         var2 = null;
      }

      byte var1 = var2[9];
      var3 = this.mCanBusInfoByte;
      var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
         var2 = null;
      }

      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1, var2[8], 0, 5400, 16);
      Context var4 = this.mContext;
      Context var5 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var5 = null;
      }

      this.updateParkUi((Bundle)null, var5);
   }

   private final void setVersionInfo() {
      Context var3 = this.mContext;
      byte[] var2 = null;
      Context var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var1 = null;
      }

      byte[] var4 = this.mCanBusInfoByte;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
      } else {
         var2 = var4;
      }

      this.updateVersionInfo(var1, this.getVersionStr(var2));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._163.UiMgr");
      UiMgr var3 = (UiMgr)var2;
      this.mUiMgr = var3;
      UiMgr var4 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)var4, (HashMap)null, 4, (Object)null);
   }

   public void btMusicInfoChange() {
      sendMediaSource$default(this, 133, (byte[])null, 2, (Object)null);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendBtPhoneData(5, var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendBtPhoneData(1, var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendBtPhoneData(2, var1);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      Intrinsics.checkNotNullParameter(var2, "phoneNumber");
      Intrinsics.checkNotNullParameter(var9, "bundle");
      sendMediaSource$default(this, 10, (byte[])null, 2, (Object)null);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendBtPhoneData(4, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int[] var5 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var5 = null;
      }

      int var3 = var5[1];
      if (var3 != 38) {
         if (var3 != 49) {
            if (var3 != 65) {
               if (var3 != 232) {
                  if (var3 != 240) {
                     if (var3 != 97) {
                        if (var3 != 98) {
                           switch (var3) {
                              case 17:
                                 this.setSwc();
                                 this.setTrack();
                                 break;
                              case 18:
                                 this.setDoorData0x12();
                                 break;
                              case 19:
                                 this.driveData0();
                                 break;
                              case 20:
                                 this.driveData1();
                                 break;
                              default:
                                 switch (var3) {
                                    case 33:
                                       this.setPanelBtnKey();
                                       break;
                                    case 34:
                                       this.panelKnob(var2);
                                       break;
                                    case 35:
                                       this.set0x23Data();
                                 }
                           }
                        } else {
                           this.setCarInfoData2();
                        }
                     } else {
                        this.setCarInfo();
                     }
                  } else {
                     this.setVersionInfo();
                  }
               } else {
                  this.originalStatus();
               }
            } else {
               this.setRadar();
            }
         } else {
            this.setAirData0x31();
         }
      } else {
         this.setCarData();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      this.setMVolume(var1);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      List var14 = (List)(new ArrayList());
      var14.add((byte)DataHandleUtils.setIntByteWithBit(0, 7, var12 ^ true));
      if (var10 != 0) {
         var5 = var8;
      }

      var14.add((byte)var5);
      var14.add((byte)var6);
      var14.add((byte)0);
      var14.add((byte)0);
      var14.add((byte)var10);
      var14.add((byte)var2);
      var14.add((byte)var3);
      var14.add((byte)var4);
      var14.add((byte)1);
      byte[] var15 = CollectionsKt.toByteArray((Collection)var14);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -53}, var15));
   }

   public final int getBits(int var1) {
      return var1 / 1 % 10;
   }

   public final int[] getMAirData() {
      return this.mAirData;
   }

   public final int getMVolume() {
      return this.mVolume;
   }

   public final int getTens(int var1) {
      return var1 / 10 % 10;
   }

   public void initCommand(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.initCommand(var1);
      this.mContext = var1;
      this.languageSet();
   }

   public final void languageSet() {
      List var3 = (List)(new ArrayList());
      Context var2 = this.mContext;
      Context var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var1 = null;
      }

      var3.add(new SettingUpdateEntity(0, 0, SharePreUtil.getIntValue(var1, "_163_language_item", 0)));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Intrinsics.checkNotNullParameter(var11, "currentHourStr");
      Intrinsics.checkNotNullParameter(var12, "currentMinuteStr");
      Intrinsics.checkNotNullParameter(var13, "currentSecondStr");
      Intrinsics.checkNotNullParameter(var21, "songTitle");
      Intrinsics.checkNotNullParameter(var22, "songAlbum");
      Intrinsics.checkNotNullParameter(var23, "songArtist");
      if (var1 != 9) {
         byte[] var25 = var21.getBytes(Charsets.UTF_16BE);
         Intrinsics.checkNotNullExpressionValue(var25, "this as java.lang.String).getBytes(charset)");
         this.sendTextData(var25);
         sendMediaSource$default(this, 13, (byte[])null, 2, (Object)null);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      Intrinsics.checkNotNullParameter(var2, "currBand");
      Intrinsics.checkNotNullParameter(var3, "currentFreq");
      Intrinsics.checkNotNullParameter(var4, "psName");
      byte var6;
      switch (var2.hashCode()) {
         case 64901:
            if (!var2.equals("AM1")) {
               return;
            }

            var6 = 4;
            break;
         case 64902:
            if (!var2.equals("AM2")) {
               return;
            }

            var6 = 5;
            break;
         case 69706:
            if (!var2.equals("FM1")) {
               return;
            }

            var6 = 1;
            break;
         case 69707:
            if (!var2.equals("FM2")) {
               return;
            }

            var6 = 2;
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var6 = 3;
               break;
            }

            return;
         default:
            return;
      }

      this.sendMediaSource(var6, com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(var1, var2, 8, var3));
   }

   public final void setMAirData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData = var1;
   }

   public final void setMVolume(int var1) {
      if (var1 != this.mVolume) {
         this.sendMediaSource(32, new byte[]{86, 79, 76, 32, (byte)this.getTens(var1), (byte)this.getBits(var1)});
      }

      this.mVolume = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      sendMediaSource$default(this, 0, (byte[])null, 2, (Object)null);
   }

   public final void updateSettingData() {
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      Intrinsics.checkNotNullParameter(var8, "currentAllMinuteStr");
      Intrinsics.checkNotNullParameter(var11, "currentHourStr");
      Intrinsics.checkNotNullParameter(var12, "currentMinuteStr");
      Intrinsics.checkNotNullParameter(var13, "currentSecondStr");
      if (var1 != 9) {
         sendMediaSource$default(this, 13, (byte[])null, 2, (Object)null);
      }
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/canbus/car/_163/MsgMgr$Companion;", "", "()V", "isAirFirst", "", "isDoorFirst", "CanBusInfo_release"},
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
