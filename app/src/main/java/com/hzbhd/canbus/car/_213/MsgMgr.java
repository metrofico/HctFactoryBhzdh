package com.hzbhd.canbus.car._213;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TouchpadEvents;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.proxy.service.ServiceConstants;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u0005\n\u0002\b%\n\u0002\u0010\t\n\u0002\b8\u0018\u0000 \u0090\u00012\u00020\u0001:\u0004\u0090\u0001\u0091\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\fH\u0016J\b\u0010$\u001a\u00020\"H\u0016J\b\u0010%\u001a\u00020\"H\u0016J\b\u0010&\u001a\u00020\"H\u0016J \u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u0012H\u0016J \u0010+\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u0012H\u0016J\u0018\u0010,\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u0004H\u0016J\u0010\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\bH\u0002J\u0018\u00100\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\f2\u0006\u00101\u001a\u00020\bH\u0016Jp\u00102\u001a\u00020\"2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\b2\u0006\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u00020\b2\u0006\u0010;\u001a\u00020\u00122\u0006\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\b2\u0006\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u00172\u0006\u0010@\u001a\u00020\u0017H\u0016J\b\u0010A\u001a\u00020\"H\u0016J\u0010\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u0012H\u0002J\u0018\u0010D\u001a\u00020\b2\u0006\u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\bH\u0002J\u0012\u0010G\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010H\u001a\u00020\"2\u0006\u0010#\u001a\u00020\fH\u0016J\u0010\u0010I\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0004H\u0002J\u0010\u0010J\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0004H\u0002J¸\u0001\u0010K\u001a\u00020\"2\u0006\u0010L\u001a\u0002042\u0006\u0010M\u001a\u0002042\u0006\u0010N\u001a\u00020\b2\u0006\u0010O\u001a\u00020\b2\u0006\u0010P\u001a\u0002042\u0006\u0010Q\u001a\u0002042\u0006\u0010R\u001a\u0002042\u0006\u0010S\u001a\u0002042\u0006\u0010T\u001a\u0002042\u0006\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020\u00172\u0006\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020\u00172\u0006\u0010Y\u001a\u00020Z2\u0006\u00103\u001a\u0002042\u0006\u0010[\u001a\u00020\b2\u0006\u0010<\u001a\u00020\u00122\u0006\u0010\\\u001a\u00020Z2\u0006\u0010]\u001a\u00020\u00172\u0006\u0010^\u001a\u00020\u00172\u0006\u0010_\u001a\u00020\u00172\u0006\u0010`\u001a\u00020\u0012H\u0016J\u0010\u0010a\u001a\u00020\"2\u0006\u0010b\u001a\u00020\bH\u0002J0\u0010c\u001a\u00020\"2\u0006\u0010d\u001a\u00020\b2\u0006\u0010e\u001a\u00020\u00172\u0006\u0010f\u001a\u00020\u00172\u0006\u0010g\u001a\u00020\u00172\u0006\u0010h\u001a\u00020\bH\u0016J\u0018\u0010i\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0006\u00101\u001a\u00020\bH\u0002J \u0010j\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0006\u00101\u001a\u00020\b2\u0006\u0010k\u001a\u00020\bH\u0002J \u0010l\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0006\u00101\u001a\u00020\b2\u0006\u0010k\u001a\u00020\bH\u0002J\u0018\u0010m\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0006\u00101\u001a\u00020\bH\u0002J\u0010\u0010n\u001a\u00020\u00172\u0006\u0010b\u001a\u00020\bH\u0002J \u0010o\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0006\u00101\u001a\u00020\b2\u0006\u0010p\u001a\u00020\bH\u0002J\u0010\u0010q\u001a\u00020\"2\u0006\u0010#\u001a\u00020\fH\u0002J\u0010\u0010r\u001a\u00020\"2\u0006\u0010#\u001a\u00020\fH\u0002J\b\u0010s\u001a\u00020\"H\u0002J\b\u0010t\u001a\u00020\"H\u0002J\b\u0010u\u001a\u00020\"H\u0002J\b\u0010v\u001a\u00020\"H\u0002J\b\u0010w\u001a\u00020\"H\u0002J\b\u0010x\u001a\u00020\"H\u0002J\b\u0010y\u001a\u00020\"H\u0002J\u000e\u0010z\u001a\u00020\"2\u0006\u0010b\u001a\u00020\bJ\b\u0010{\u001a\u00020\"H\u0002J\b\u0010|\u001a\u00020\"H\u0002J\b\u0010}\u001a\u00020\"H\u0002J\b\u0010~\u001a\u00020\"H\u0002J\b\u0010\u007f\u001a\u00020\"H\u0002J\t\u0010\u0080\u0001\u001a\u00020\"H\u0002J\t\u0010\u0081\u0001\u001a\u00020\"H\u0002J\t\u0010\u0082\u0001\u001a\u00020\"H\u0002J\t\u0010\u0083\u0001\u001a\u00020\"H\u0002J\u0012\u0010\u0084\u0001\u001a\u00020\"2\u0007\u0010\u0085\u0001\u001a\u00020\u0017H\u0016J\u0012\u0010\u0086\u0001\u001a\u00020\"2\u0007\u0010\u0087\u0001\u001a\u00020\u0012H\u0016J\t\u0010\u0088\u0001\u001a\u00020\"H\u0002J\u0007\u0010\u0089\u0001\u001a\u00020\"J!\u0010\u008a\u0001\u001a\u00020\"2\u0007\u0010\u008b\u0001\u001a\u00020\b2\u0007\u0010\u008c\u0001\u001a\u00020\b2\u0006\u0010b\u001a\u00020\bJ\u0093\u0001\u0010\u008d\u0001\u001a\u00020\"2\u0006\u0010L\u001a\u0002042\u0006\u0010M\u001a\u0002042\u0006\u0010N\u001a\u00020\b2\u0006\u0010O\u001a\u00020\b2\u0006\u0010P\u001a\u0002042\u0006\u0010Q\u001a\u0002042\u0006\u0010R\u001a\u0002042\u0006\u0010S\u001a\u00020\u00172\u0006\u0010T\u001a\u0002042\u0006\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020\u00172\u0006\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020\u00172\u0006\u0010Y\u001a\u00020\b2\u0007\u0010\u008e\u0001\u001a\u0002042\u0006\u0010<\u001a\u00020\u00122\u0007\u0010\u008f\u0001\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0018\u00010\u001dR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0092\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_213/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mCanBusInfoByte", "", "mCanBusInfoInt", "", "mCanId", "", "mCanbusAirInfoCopy", "mCanbusDoorInfoCopy", "mContext", "Landroid/content/Context;", "mData0_26", "mData0_7", "mData2_03", "mData2_47", "mEnable06_1", "", "mEnable06_2", "mEnable06_3", "mEnable06_4", "mFuelUnit", "", "mKonbCount", "mKonbSelCount", "mLastKonbCount", "mLastSelKonbCount", "mRequestTimer", "Lcom/hzbhd/canbus/car/_213/MsgMgr$TimerHelper;", "mSelKnobValue", "mStartTime", "mVolKnobValue", "afterServiceNormalSetting", "", "context", "atvInfoChange", "auxInInfoChange", "btMusicInfoChange", "btPhoneIncomingInfoChange", "phoneNumber", "isMicMute", "isAudioTransferTowardsAG", "btPhoneOutGoingInfoChange", "canbusInfoChange", "canbusInfo", "carType", "data", "customLongClick", "key", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "album", "artist", "dtvInfoChange", "getIndexBy2Bit", "bit", "getMsbLsbResult", "MSB", "LSB", "initAmplifierData", "initCommand", "isAirMsgReturn", "isDoorMsgReturn", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panelBtnKeyClick", "value", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "realKeyClick1", "realKeyClick3_1", "time", "realKeyClick3_2", "realKeyLongClick1", "resolveFrontAirTemp", "sendAppIconSelect", "different", "set0x21PanelKeyData", "set0x22PanelKnobData", "setAirData0x31", "setAmplifierData", "setCarInfo", "setCarType", "setDoorData", "setDriveData", "setDriveData2", "setFuelUnit", "setHudData", "setHudData0x79", "setOriginalCarDeviceInfo", "setOriginalCarDeviceInfo2", "setRadar", "setSwcKey", "setTrack", "setVersionInfo", "settingInfo", "sourceSwitchChange", "source", "sourceSwitchNoMediaInfoChange", "isPowerOff", "touch0x25", "touchpadView", "updateSettings", "left", "right", "videoInfoChange", "playMode", "duation", "Companion", "TimerHelper", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static boolean isAirFirst = true;
   private static boolean isDoorFirst = true;
   private static final boolean isLastLongClick = false;
   private static final int lastPanelSt = 0;
   private static final int lastPanelkey = 0;
   private static final int lastSwcKey = 0;
   private static final int lastSwcSt = 0;
   private static final int longClickCount = 0;
   private static final boolean mIsKonbClockwise = true;
   private static final boolean mIsKonbSelClockwise = true;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private int mData0_26 = 15;
   private int mData0_7;
   private int mData2_03 = 5;
   private int mData2_47 = 5;
   private boolean mEnable06_1;
   private boolean mEnable06_2;
   private boolean mEnable06_3;
   private boolean mEnable06_4;
   private String mFuelUnit = "mpg";
   private final int mKonbCount;
   private final int mKonbSelCount;
   private final int mLastKonbCount;
   private final int mLastSelKonbCount;
   private TimerHelper mRequestTimer;
   private int mSelKnobValue;
   private int mStartTime;
   private int mVolKnobValue;

   private final String carType(int var1) {
      Context var2 = this.mContext;
      Intrinsics.checkNotNull(var2);
      String var3 = var2.getString(CommUtil.getStrIdByResId(this.mContext, "_213_car" + var1));
      Intrinsics.checkNotNullExpressionValue(var3, "mContext!!.getString(Com…ontext, \"_213_car$data\"))");
      return var3;
   }

   private final int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private final int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private final void initAmplifierData(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      byte[] var7 = new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume};
      byte[] var5 = new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 16)};
      byte[] var4 = new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 16)};
      byte[] var3 = new byte[]{22, -83, 4, (byte)(GeneralAmplifierData.bandBass + 10)};
      byte[] var2 = new byte[]{22, -83, 6, (byte)(GeneralAmplifierData.bandTreble + 10)};
      TimerHelper var6 = new TimerHelper(this);
      var6.startTimer((TimerTask)(new TimerTask(new byte[][]{var7, var5, var4, var3, var2}, var6) {
         final byte[][] $amplifierData;
         final TimerHelper $timerHelper;
         private int i;

         {
            this.$amplifierData = var1;
            this.$timerHelper = var2;
         }

         public final int getI() {
            return this.i;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.$amplifierData;
            if (var1 < ((Object[])var2).length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.$timerHelper.stopTimer();
            }

         }

         public final void setI(int var1) {
            this.i = var1;
         }
      }), 0L, 100L);
   }

   private final boolean isAirMsgReturn(byte[] var1) {
      byte[] var3 = this.mCanbusAirInfoCopy;
      byte[] var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanbusAirInfoCopy");
         var2 = null;
      }

      if (Arrays.equals(var1, var2)) {
         return true;
      } else {
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(canbusInfo, canbusInfo.size)");
         this.mCanbusAirInfoCopy = var1;
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private final boolean isDoorMsgReturn(byte[] var1) {
      byte[] var3 = this.mCanbusDoorInfoCopy;
      byte[] var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanbusDoorInfoCopy");
         var2 = null;
      }

      if (Arrays.equals(var1, var2)) {
         return true;
      } else {
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(canbusInfo, canbusInfo.size)");
         this.mCanbusDoorInfoCopy = var1;
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private final void panelBtnKeyClick(int var1) {
      Context var4 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      int[] var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var2 = null;
      }

      this.realKeyLongClick1(var4, var1, var2[5]);
   }

   private final void realKeyClick1(Context var1, int var2) {
      int[] var6 = this.mCanBusInfoInt;
      Object var5 = null;
      int[] var4 = var6;
      if (var6 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var3 = var4[2];
      var4 = this.mCanBusInfoInt;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = (int[])var5;
      }

      this.realKeyClick1(var1, var2, var3, var4[3]);
   }

   private final void realKeyClick3_1(Context var1, int var2, int var3) {
      int[] var5 = this.mCanBusInfoInt;
      int[] var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      this.realKeyClick3_1(var1, var2, var4[2], var3);
   }

   private final void realKeyClick3_2(Context var1, int var2, int var3) {
      int[] var5 = this.mCanBusInfoInt;
      int[] var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      this.realKeyClick3_2(var1, var2, var4[2], var3);
   }

   private final void realKeyLongClick1(Context var1, int var2) {
      int[] var4 = this.mCanBusInfoInt;
      int[] var3 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      this.realKeyLongClick1(var1, var2, var3[3]);
   }

   private final String resolveFrontAirTemp(int var1) {
      String var2;
      if (var1 != 254) {
         if (var1 != 255) {
            var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
         } else {
            var2 = "HI";
         }
      } else {
         var2 = "LO";
      }

      return var2;
   }

   private final void sendAppIconSelect(Context var1, int var2, int var3) {
      String var4 = ConfigUtil.getDeviceId();
      Intrinsics.checkNotNullExpressionValue(var4, "getDeviceId()");
      if (StringsKt.contains$default((CharSequence)var4, (CharSequence)"P0R", false, 2, (Object)null)) {
         var4 = System.getString(var1.getContentResolver(), ServiceConstants.KEY_CURRENT_TOP_PACKAGENAME);
         Intrinsics.checkNotNullExpressionValue(var4, "getString(\n             …PACKAGENAME\n            )");
         CharSequence var6 = (CharSequence)var4;
         Object[] var7 = ((Collection)(new Regex(":;:")).split(var6, 0)).toArray(new String[0]);
         Intrinsics.checkNotNull(var7, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
         var6 = (CharSequence)((String[])var7)[1];
         if (!TextUtils.isEmpty(var6) && StringsKt.contains$default(var6, (CharSequence)"com.android.launcher3", false, 2, (Object)null)) {
            String var5;
            switch (var2) {
               case 47:
                  var5 = "KEY_APP_SELECT_PREV";
                  break;
               case 48:
                  var5 = "KEY_APP_SELECT_NEXT";
                  break;
               case 49:
                  var5 = "KEY_APP_SELECT_ENTER";
                  break;
               default:
                  return;
            }

            FutureUtil.instance.sendAppSelect(var5);
         } else if (var2 == 49) {
            this.realKeyClick(var1, var2);
         } else {
            this.realKeyClick3_2(var1, var2, var3);
         }
      }

   }

   private final void set0x21PanelKeyData(Context var1) {
      int[] var4 = this.mCanBusInfoInt;
      int[] var3 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      int var2 = var3[2];
      if (var2 != 0) {
         if (var2 != 6) {
            if (var2 != 9) {
               if (var2 != 32) {
                  if (var2 != 43) {
                     if (var2 != 45) {
                        if (var2 != 84) {
                           if (var2 != 2) {
                              if (var2 != 3) {
                                 switch (var2) {
                                    case 22:
                                       this.realKeyLongClick1(var1, 49);
                                       break;
                                    case 23:
                                       this.realKeyLongClick1(var1, 45);
                                       break;
                                    case 24:
                                       this.realKeyLongClick1(var1, 46);
                                       break;
                                    case 25:
                                       this.realKeyLongClick1(var1, 47);
                                       break;
                                    case 26:
                                       this.realKeyLongClick1(var1, 48);
                                       break;
                                    case 27:
                                       this.realKeyLongClick1(var1, 128);
                                 }
                              } else {
                                 this.realKeyLongClick1(var1, 20);
                              }
                           } else {
                              this.realKeyLongClick1(var1, 21);
                           }
                        } else {
                           try {
                              Intent var7 = new Intent();
                              ComponentName var6 = new ComponentName("com.google.android.youtube", "com.google.android.youtube.app.honeycomb.Shell$HomeActivity");
                              var7.setComponent(var6);
                              var7.setFlags(268435456);
                              var1.startActivity(var7);
                           } catch (Exception var5) {
                              this.realKeyLongClick1(var1, 79);
                           }
                        }
                     } else {
                        this.realKeyLongClick1(var1, 59);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 52);
                  }
               } else {
                  this.realKeyLongClick1(var1, 128);
               }
            } else {
               this.realKeyLongClick1(var1, 3);
            }
         } else {
            this.realKeyLongClick1(var1, 50);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private final void set0x22PanelKnobData(Context var1) {
      byte[] var6 = this.mCanBusInfoByte;
      Object var4 = null;
      Object var5 = null;
      byte[] var3 = var6;
      if (var6 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
         var3 = null;
      }

      int var2;
      byte[] var7;
      if (var3[2] == 1) {
         var2 = this.mVolKnobValue;
         byte[] var8 = this.mCanBusInfoByte;
         var3 = var8;
         if (var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
            var3 = null;
         }

         var2 -= var3[3];
         if (var2 > 0) {
            this.realKeyClick3_1(var1, 8, Math.abs(var2));
         } else if (var2 < 0) {
            this.realKeyClick3_1(var1, 7, Math.abs(var2));
         }

         var7 = this.mCanBusInfoByte;
         if (var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
            var7 = (byte[])var5;
         }

         this.mVolKnobValue = var7[3];
      } else {
         byte[] var9 = this.mCanBusInfoByte;
         var3 = var9;
         if (var9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
            var3 = null;
         }

         if (var3[2] == 2) {
            var2 = this.mSelKnobValue;
            var9 = this.mCanBusInfoByte;
            var3 = var9;
            if (var9 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
               var3 = null;
            }

            var2 -= var3[3];
            if (var2 > 0) {
               this.realKeyClick3_1(var1, 45, Math.abs(var2));
            } else if (var2 < 0) {
               this.realKeyClick3_1(var1, 46, Math.abs(var2));
            }

            var7 = this.mCanBusInfoByte;
            if (var7 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
               var7 = (byte[])var4;
            }

            this.mSelKnobValue = var7[3];
         }
      }

   }

   private final void setAirData0x31() {
      int[] var3 = this.mCanBusInfoInt;
      Object var2 = null;
      int[] var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.power = DataHandleUtils.getBoolBit6(var1[2]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(var1[3], 6, 2);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(var1[3]) ^ true;
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(var1[3]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.dual = DataHandleUtils.getBoolBit2(var1[3]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(var1[4]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var1[4]);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 2);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 2, 2);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (DataHandleUtils.getBoolBit6(var1[6])) {
         GeneralAirData.front_left_blow_foot = true;
      }

      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (DataHandleUtils.getBoolBit5(var1[6])) {
         GeneralAirData.front_left_blow_head = true;
      }

      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (DataHandleUtils.getBoolBit4(var1[6])) {
         GeneralAirData.front_left_blow_window = true;
      }

      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (DataHandleUtils.getBoolBit6(var1[7])) {
         GeneralAirData.front_right_blow_foot = true;
      }

      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (DataHandleUtils.getBoolBit5(var1[7])) {
         GeneralAirData.front_right_blow_head = true;
      }

      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (DataHandleUtils.getBoolBit4(var1[7])) {
         GeneralAirData.front_right_blow_window = true;
      }

      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 4);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 4);
      var3 = this.mCanBusInfoInt;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      GeneralAirData.front_left_temperature = this.resolveFrontAirTemp(var1[8]);
      var1 = this.mCanBusInfoInt;
      if (var1 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = (int[])var2;
      }

      GeneralAirData.front_right_temperature = this.resolveFrontAirTemp(var1[9]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private final void setAmplifierData() {
      int[] var2 = this.mCanBusInfoInt;
      int[] var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      if (this.isDataChange(var1)) {
         var2 = this.mCanBusInfoInt;
         var1 = var2;
         if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var1 = null;
         }

         GeneralAmplifierData.volume = var1[2];
         var2 = this.mCanBusInfoInt;
         var1 = var2;
         if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var1 = null;
         }

         GeneralAmplifierData.leftRight = var1[3] - 16;
         var2 = this.mCanBusInfoInt;
         var1 = var2;
         if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var1 = null;
         }

         GeneralAmplifierData.frontRear = var1[4] - 16;
         var2 = this.mCanBusInfoInt;
         var1 = var2;
         if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var1 = null;
         }

         GeneralAmplifierData.bandBass = var1[5] - 10;
         var2 = this.mCanBusInfoInt;
         var1 = var2;
         if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var1 = null;
         }

         GeneralAmplifierData.bandTreble = var1[7] - 10;
         this.saveAmplifierData(this.mContext, this.mCanId);
         this.updateAmplifierActivity(new Bundle());
      }

      List var3 = (List)(new ArrayList());
      var2 = this.mCanBusInfoInt;
      var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      var3.add(new SettingUpdateEntity(3, 0, var1[9]));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private final void setCarInfo() {
      DriverDataPageUiSet.Page.Item var4 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
      int var1;
      int[] var2;
      int[] var3;
      if (var4 != null) {
         var3 = this.mCanBusInfoInt;
         var2 = var3;
         if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var2 = null;
         }

         var1 = var2[4];
         var3 = this.mCanBusInfoInt;
         var2 = var3;
         if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var2 = null;
         }

         var4.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(var1, var2[5])));
      }

      var4 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
      if (var4 != null) {
         var3 = this.mCanBusInfoInt;
         var2 = var3;
         if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var2 = null;
         }

         var1 = var2[6];
         var3 = this.mCanBusInfoInt;
         var2 = var3;
         if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var2 = null;
         }

         var4.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(var1, var2[7])));
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setCarType() {
      List var3 = (List)(new ArrayList());
      int[] var2 = this.mCanBusInfoInt;
      int[] var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      var3.add(new DriverUpdateEntity(2, 2, this.carType(var1[3])));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setDoorData() {
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
      var1 = this.mCanBusInfoInt;
      if (var1 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = (int[])var2;
      }

      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[4]);
      this.updateDoorView(this.mContext);
   }

   private final void setDriveData() {
      List var7 = (List)(new ArrayList());
      int var1 = 0;

      while(true) {
         Object var5 = null;
         if (var1 >= 8) {
            this.updateGeneralDriveData(var7);
            this.updateDriveDataActivity((Bundle)null);
            return;
         }

         StringBuilder var8 = new StringBuilder();
         int[] var6 = this.mCanBusInfoInt;
         int[] var4 = var6;
         if (var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         int var3 = var1 * 2;
         int var2 = var4[var3 + 2];
         var4 = this.mCanBusInfoInt;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = (int[])var5;
         }

         var7.add(new DriverUpdateEntity(0, var1, var8.append((float)((double)(var2 * 256 + var4[var3 + 3]) * 0.1 * (double)10) / (float)10).append(' ').append(this.mFuelUnit).toString()));
         ++var1;
      }
   }

   private final void setDriveData2() {
      List var7 = (List)(new ArrayList());
      int var1 = 0;

      while(true) {
         Object var5 = null;
         if (var1 >= 15) {
            this.updateGeneralDriveData(var7);
            this.updateDriveDataActivity((Bundle)null);
            return;
         }

         StringBuilder var8 = new StringBuilder();
         int[] var6 = this.mCanBusInfoInt;
         int[] var4 = var6;
         if (var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         int var2 = var1 * 2;
         int var3 = var4[var2 + 2];
         var4 = this.mCanBusInfoInt;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = (int[])var5;
         }

         var7.add(new DriverUpdateEntity(1, var1, var8.append((float)((double)(var3 * 256 + var4[var2 + 3]) * 0.1 * (double)10) / (float)10).append(' ').append(this.mFuelUnit).toString()));
         ++var1;
      }
   }

   private final void setHudData() {
      List var1 = (List)(new ArrayList());
      SettingUpdateEntity var2 = (new SettingUpdateEntity(5, 0, this.mData0_7)).setEnable(this.mEnable06_4);
      Intrinsics.checkNotNullExpressionValue(var2, "SettingUpdateEntity<Any?…7).setEnable(mEnable06_4)");
      var1.add(var2);
      var2 = (new SettingUpdateEntity(5, 1, this.mData0_26 - 15)).setProgress(this.mData0_26).setEnable(this.mEnable06_3);
      Intrinsics.checkNotNullExpressionValue(var2, "SettingUpdateEntity<Any?…  .setEnable(mEnable06_3)");
      var1.add(var2);
      var2 = (new SettingUpdateEntity(5, 2, this.mData2_47 - 5)).setProgress(this.mData2_47).setEnable(this.mEnable06_2);
      Intrinsics.checkNotNullExpressionValue(var2, "SettingUpdateEntity<Any?…  .setEnable(mEnable06_2)");
      var1.add(var2);
      var2 = (new SettingUpdateEntity(5, 3, this.mData2_03 - 5)).setProgress(this.mData2_03).setEnable(this.mEnable06_1);
      Intrinsics.checkNotNullExpressionValue(var2, "SettingUpdateEntity<Any?…  .setEnable(mEnable06_1)");
      var1.add(var2);
      this.updateGeneralSettingData(var1);
   }

   private final void setHudData0x79() {
      int[] var2 = this.mCanBusInfoInt;
      int[] var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      this.mData0_7 = DataHandleUtils.getIntFromByteWithBit(var1[2], 7, 1);
      var2 = this.mCanBusInfoInt;
      var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      this.mData0_26 = DataHandleUtils.getIntFromByteWithBit(var1[2], 2, 5);
      var2 = this.mCanBusInfoInt;
      var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      this.mData2_47 = DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4);
      var2 = this.mCanBusInfoInt;
      var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      this.mData2_03 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4);
      this.setHudData();
      this.updateSettingActivity((Bundle)null);
   }

   private final void setOriginalCarDeviceInfo() {
      List var6 = (List)(new ArrayList());
      int[] var5 = this.mCanBusInfoInt;
      int[] var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var1 = var4[7];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var6.add(new OriginalCarDeviceUpdateEntity(0, String.valueOf(var1 * 256 + var4[8])));
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var1 = var4[15];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var6.add(new OriginalCarDeviceUpdateEntity(1, String.valueOf(var1 * 256 + var4[16])));
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var1 = var4[9];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var6.add(new OriginalCarDeviceUpdateEntity(2, String.valueOf(var1 * 256 + var4[10])));
      GeneralOriginalCarDeviceData.mList = var6;
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      Context var12;
      if (DataHandleUtils.getBoolBit1(var4[6])) {
         var12 = this.mContext;
         Intrinsics.checkNotNull(var12);
         GeneralOriginalCarDeviceData.cdStatus = var12.getResources().getString(2131769504);
      } else {
         var12 = this.mContext;
         Intrinsics.checkNotNull(var12);
         GeneralOriginalCarDeviceData.cdStatus = var12.getResources().getString(2131768042);
      }

      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      GeneralOriginalCarDeviceData.cd = DataHandleUtils.getBoolBit1(var4[6]);
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      boolean var3;
      if (DataHandleUtils.getIntFromByteWithBit(var4[6], 5, 3) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_off = var3;
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      if (DataHandleUtils.getIntFromByteWithBit(var4[6], 5, 3) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_fold = var3;
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      if (DataHandleUtils.getIntFromByteWithBit(var4[6], 5, 3) == 2) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_track = var3;
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      if (DataHandleUtils.getIntFromByteWithBit(var4[6], 2, 3) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_off = var3;
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      if (DataHandleUtils.getIntFromByteWithBit(var4[6], 2, 3) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_fold = var3;
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      if (DataHandleUtils.getIntFromByteWithBit(var4[6], 2, 3) == 2) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_disc = var3;
      Context var9 = this.mContext;
      Intrinsics.checkNotNull(var9);
      Context var8 = this.mContext;
      StringBuilder var7 = (new StringBuilder()).append("_123_divice_status_");
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      GeneralOriginalCarDeviceData.runningState = var9.getString(CommUtil.getStrIdByResId(var8, var7.append(var4[5]).toString()));
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var1 = var4[13];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var1 = var1 * 256 + var4[14];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var2 = var4[11];
      var5 = this.mCanBusInfoInt;
      var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      var2 = var2 * 256 + var4[12];
      if (var1 == 0 && this.mStartTime != 0) {
         if (this.mRequestTimer == null) {
            this.mRequestTimer = new TimerHelper(this);
         }

         byte[] var14 = new byte[]{22, -14, 10, 0};
         byte[] var11 = new byte[]{22, -14, 10, 1};
         byte[] var13 = new byte[]{22, -14, 10, 2};
         TimerHelper var10 = this.mRequestTimer;
         Intrinsics.checkNotNull(var10);
         var10.startTimer((TimerTask)(new TimerTask(new byte[][]{var14, var11, var13}, this) {
            final byte[][] $rquestCmdArray;
            private int i;
            final MsgMgr this$0;

            {
               this.$rquestCmdArray = var1;
               this.this$0 = var2;
            }

            public final int getI() {
               return this.i;
            }

            public void run() {
               int var1 = this.i;
               byte[][] var2 = this.$rquestCmdArray;
               if (var1 < ((Object[])var2).length) {
                  this.i = var1 + 1;
                  CanbusMsgSender.sendMsg(var2[var1]);
               } else {
                  TimerHelper var3 = this.this$0.mRequestTimer;
                  Intrinsics.checkNotNull(var3);
                  var3.stopTimer();
               }

            }

            public final void setI(int var1) {
               this.i = var1;
            }
         }), 0L, 150L);
      }

      this.mStartTime = var1;
      if (var2 > 0 && var1 <= var2) {
         GeneralOriginalCarDeviceData.startTime = this.startEndTimeMethod(var1);
         GeneralOriginalCarDeviceData.endTime = this.startEndTimeMethod(var2);
         GeneralOriginalCarDeviceData.progress = var1 * 100 / var2;
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private final void setOriginalCarDeviceInfo2() {
      String var9;
      label34: {
         UnsupportedEncodingException var10000;
         label37: {
            byte[] var2;
            boolean var10001;
            try {
               var2 = this.mCanBusInfoByte;
            } catch (UnsupportedEncodingException var7) {
               var10000 = var7;
               var10001 = false;
               break label37;
            }

            byte[] var1 = var2;
            if (var2 == null) {
               try {
                  Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
               } catch (UnsupportedEncodingException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label37;
               }

               var1 = null;
            }

            try {
               byte[] var3 = DataHandleUtils.getBytesEndWithAssign(var1, 3, (byte)0);
               Intrinsics.checkNotNullExpressionValue(var3, "getBytesEndWithAssign(mC…foByte, 3, 0x00.toByte())");
               Charset var10 = Charset.forName("GB2312");
               Intrinsics.checkNotNullExpressionValue(var10, "forName(\"GB2312\")");
               var9 = new String(var3, var10);
               break label34;
            } catch (UnsupportedEncodingException var5) {
               var10000 = var5;
               var10001 = false;
            }
         }

         UnsupportedEncodingException var8 = var10000;
         var8.printStackTrace();
         var9 = null;
      }

      List var4 = (List)(new ArrayList());
      int[] var12 = this.mCanBusInfoInt;
      int[] var11 = var12;
      if (var12 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var11 = null;
      }

      var4.add(new OriginalCarDeviceUpdateEntity(var11[2] + 2, var9));
      GeneralOriginalCarDeviceData.mList = var4;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private final void setRadar() {
      int[] var5 = this.mCanBusInfoInt;
      int[] var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var1;
      int var2;
      int var3;
      if (var4[13] == 0) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var2 = var4[2];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var1 = var4[3];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var3 = var4[4];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         RadarInfoUtil.setRearRadarLocationData(3, var2, var1, var3, var4[5]);
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var2 = var4[6];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var3 = var4[7];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var1 = var4[8];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         RadarInfoUtil.setFrontRadarLocationData(3, var2, var3, var1, var4[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         GeneralParkData.radar_distance_disable = new int[]{0, 255};
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var2 = var4[2];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var1 = var4[3];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var3 = var4[4];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         RadarInfoUtil.setRearRadarDistanceData(var2, var1, var3, var4[5]);
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var1 = var4[6];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var2 = var4[7];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         var3 = var4[8];
         var5 = this.mCanBusInfoInt;
         var4 = var5;
         if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var4 = null;
         }

         RadarInfoUtil.setFrontRadarDistanceData(var1, var2, var3, var4[9]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private final void setSwcKey() {
      int[] var2 = this.mCanBusInfoInt;
      int[] var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var1 = null;
      }

      switch (var1[4]) {
         case 0:
            this.panelBtnKeyClick(0);
            break;
         case 1:
            this.panelBtnKeyClick(7);
            break;
         case 2:
            this.panelBtnKeyClick(8);
            break;
         case 3:
            this.panelBtnKeyClick(3);
            break;
         case 4:
            this.panelBtnKeyClick(187);
            break;
         case 5:
            this.panelBtnKeyClick(14);
            break;
         case 6:
            this.panelBtnKeyClick(15);
         case 7:
         default:
            break;
         case 8:
            this.panelBtnKeyClick(45);
            break;
         case 9:
            this.panelBtnKeyClick(46);
            break;
         case 10:
            this.panelBtnKeyClick(2);
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

      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1, var2[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
      List var4 = (List)(new ArrayList());
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      int[] var7 = var6;
      if (var6 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var7 = null;
      }

      StringBuilder var9 = var5.append(var7[3]);
      Context var8 = this.mContext;
      Intrinsics.checkNotNull(var8);
      var4.add(new DriverUpdateEntity(2, 0, var9.append(var8.getResources().getString(2131770215)).toString()));
      var6 = this.mCanBusInfoInt;
      var7 = var6;
      if (var6 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var7 = null;
      }

      label52: {
         if (var7[7] > 0) {
            var6 = this.mCanBusInfoInt;
            var7 = var6;
            if (var6 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
               var7 = null;
            }

            if (100 > var7[7]) {
               var5 = new StringBuilder();
               var6 = this.mCanBusInfoInt;
               var7 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                  var7 = null;
               }

               var4.add(new DriverUpdateEntity(2, 1, var5.append(var7[7]).append("").toString()));
               break label52;
            }
         }

         var6 = this.mCanBusInfoInt;
         var7 = var6;
         if (var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var7 = null;
         }

         if (var7[7] == 0) {
            var4.add(new DriverUpdateEntity(2, 1, "OFF"));
         } else {
            var6 = this.mCanBusInfoInt;
            var7 = var6;
            if (var6 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
               var7 = null;
            }

            if (var7[7] == 100) {
               var4.add(new DriverUpdateEntity(2, 1, "MAX"));
            }
         }
      }

      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setVersionInfo() {
      Context var3 = this.mContext;
      byte[] var2 = this.mCanBusInfoByte;
      byte[] var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
         var1 = null;
      }

      this.updateVersionInfo(var3, this.getVersionStr(var1));
   }

   private final void settingInfo() {
      int[] var88 = this.mCanBusInfoInt;
      int[] var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var11 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(var87[10]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var65 = DataHandleUtils.getBoolBit7(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var2 = DataHandleUtils.getIntFromByteWithBit(var87[9], 5, 3);
      int var1 = var2;
      if (var2 > 5) {
         var1 = 5;
      }

      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var72 = DataHandleUtils.getBoolBit7(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var26 = DataHandleUtils.getIntFromByteWithBit(var87[9], 0, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var80 = DataHandleUtils.getBoolBit4(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var35 = DataHandleUtils.getIntFromByteWithBit(var87[10], 6, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var55 = DataHandleUtils.getBoolBit3(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var45 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(var87[10]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var69 = DataHandleUtils.getBoolBit2(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var13 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(var87[9]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var79 = DataHandleUtils.getBoolBit5(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var31 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(var87[10]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var73 = DataHandleUtils.getBoolBit1(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var42 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(var87[10]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var81 = DataHandleUtils.getBoolBit0(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var14 = DataHandleUtils.getIntFromByteWithBit(var87[9], 3, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var68 = DataHandleUtils.getBoolBit6(var87[4]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var25 = DataHandleUtils.getIntFromByteWithBit(var87[10], 0, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var77 = DataHandleUtils.getBoolBit6(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var50 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(var87[11]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var54 = DataHandleUtils.getBoolBit3(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var21 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(var87[12]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var83 = DataHandleUtils.getBoolBit1(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(var87[12]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(var87[12]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var74 = DataHandleUtils.getBoolBit2(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(var87[12]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var28 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(var87[12]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var84 = DataHandleUtils.getBoolBit0(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var36 = DataHandleUtils.getIntFromByteWithBit(var87[12], 0, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var58 = DataHandleUtils.getBoolBit7(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var44 = DataHandleUtils.getIntFromByteWithBit(var87[11], 2, 3);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var57 = DataHandleUtils.getBoolBit4(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var24 = DataHandleUtils.getIntFromByteWithBit(var87[13], 5, 3);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var63 = DataHandleUtils.getBoolBit6(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var38 = DataHandleUtils.getIntFromByteWithBit(var87[11], 5, 3);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var61 = DataHandleUtils.getBoolBit5(var87[5]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(var87[13]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var78 = DataHandleUtils.getBoolBit5(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(var87[13]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var66 = DataHandleUtils.getBoolBit4(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var8 = DataHandleUtils.getIntFromByteWithBit(var87[13], 1, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var75 = DataHandleUtils.getBoolBit3(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(var87[14]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var60 = DataHandleUtils.getBoolBit2(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var9 = DataHandleUtils.getIntFromByteWithBit(var87[14], 5, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var62 = DataHandleUtils.getBoolBit1(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var32 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(var87[11]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var85 = DataHandleUtils.getBoolBit3(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var41 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(var87[12]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var17 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(var87[13]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var70 = DataHandleUtils.getBoolBit2(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var18 = DataHandleUtils.getIntFromByteWithBit(var87[14], 3, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var16 = DataHandleUtils.getIntFromByteWithBit(var87[14], 1, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var22 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(var87[14]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var48 = DataHandleUtils.getIntFromByteWithBit(var87[15], 6, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var37 = DataHandleUtils.getIntFromByteWithBit(var87[15], 4, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var43 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(var87[15]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var47 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(var87[15]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var46 = DataHandleUtils.getIntFromByteWithBit(var87[15], 0, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var19 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(var87[16]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var40 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(var87[16]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var23 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(var87[16]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var39 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(var87[16]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var49 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(var87[16]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var86 = DataHandleUtils.getBoolBit1(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var51 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(var87[16]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var71 = DataHandleUtils.getBoolBit0(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var20 = DataHandleUtils.getIntFromByteWithBit(var87[16], 0, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var82 = DataHandleUtils.getBoolBit6(var87[8]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var30 = DataHandleUtils.getIntFromByteWithBit(var87[17], 6, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var53 = DataHandleUtils.getBoolBit0(var87[6]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(var87[17]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var52 = DataHandleUtils.getBoolBit7(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var33 = DataHandleUtils.getIntFromByteWithBit(var87[17], 3, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var76 = DataHandleUtils.getBoolBit6(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var27 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(var87[17]));
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var59 = DataHandleUtils.getBoolBit5(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var15 = DataHandleUtils.getIntFromByteWithBit(var87[17], 0, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var56 = DataHandleUtils.getBoolBit4(var87[7]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var29 = DataHandleUtils.getIntFromByteWithBit(var87[18], 5, 3);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var67 = DataHandleUtils.getBoolBit7(var87[8]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      int var34 = DataHandleUtils.getIntFromByteWithBit(var87[18], 3, 2);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      boolean var64 = DataHandleUtils.getBoolBit5(var87[8]);
      List var89 = (List)(new ArrayList());

      SettingUpdateEntity var90;
      for(var2 = 0; var2 < 42; ++var2) {
         var90 = (new SettingUpdateEntity(0, var2, (new int[]{var11, var1, var26, var35, var45, var13, var31, var42, var14, var25, var50, var21, var28, var36, var44, var24, var38, var32, var41, var17, var22, var16, var18, var48, var37, var43, var47, var46, var19, var40, var23, var39, var49, var51, var20, var30, var12, var33, var27, var15, var29, var34})[var2])).setEnable((new boolean[]{var65, var72, var80, var55, var69, var79, var73, var81, var68, var77, var54, var83, var84, var58, var57, var63, var61, var85, true, var70, true, true, true, true, true, true, true, true, true, true, true, true, var86, var71, var82, var53, var52, var76, var59, var56, var67, var64})[var2]);
         Intrinsics.checkNotNullExpressionValue(var90, "SettingUpdateEntity(0, i…i]).setEnable(enable1[i])");
         var89.add(var90);
      }

      for(var1 = 0; var1 < 5; ++var1) {
         var90 = (new SettingUpdateEntity(1, var1, (new int[]{var6, var7, var8, var10, var9})[var1])).setEnable((new boolean[]{var78, var66, var75, var60, var62})[var1]);
         Intrinsics.checkNotNullExpressionValue(var90, "SettingUpdateEntity(1, i…i]).setEnable(enable2[i])");
         var89.add(var90);
      }

      for(var1 = 0; var1 < 3; ++var1) {
         var90 = (new SettingUpdateEntity(2, var1, (new int[]{var4, var5, var3})[var1])).setEnable((new boolean[]{true, var74, true})[var1]);
         Intrinsics.checkNotNullExpressionValue(var90, "SettingUpdateEntity(2, i…]).setEnable(enable03[i])");
         var89.add(var90);
      }

      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      this.mEnable06_4 = DataHandleUtils.getBoolBit4(var87[8]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      this.mEnable06_3 = DataHandleUtils.getBoolBit3(var87[8]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      this.mEnable06_2 = DataHandleUtils.getBoolBit2(var87[8]);
      var88 = this.mCanBusInfoInt;
      var87 = var88;
      if (var88 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var87 = null;
      }

      this.mEnable06_1 = DataHandleUtils.getBoolBit1(var87[8]);
      this.setHudData();
      this.updateGeneralSettingData(var89);
      this.updateSettingActivity((Bundle)null);
   }

   private final void touch0x25() {
      Context var6 = this.mContext;
      int[] var5 = this.mCanBusInfoInt;
      Object var4 = null;
      int[] var3 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      int var1 = var3[3];
      var5 = this.mCanBusInfoInt;
      var3 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      var1 = this.getMsbLsbResult(var1, var3[4]);
      var5 = this.mCanBusInfoInt;
      var3 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = null;
      }

      int var2 = var3[5];
      var3 = this.mCanBusInfoInt;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var3 = (int[])var4;
      }

      TouchpadEvents.putXY(var6, var1, this.getMsbLsbResult(var2, var3[6]), (TouchpadEvents.Events)(new TouchpadEvents.Events(this) {
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$tVzM5pfceiIMr8XcoMJM_wGEkBI(MsgMgr var0) {
            goUp$lambda_0(var0);
         }

         {
            this.this$0 = var1;
         }

         private static final void goUp$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");

            for(int var1 = 0; var1 < 5; ++var1) {
               try {
                  var0.realKeyClick4(var0.mContext, 7);
                  Thread.sleep(500L);
               } catch (InterruptedException var3) {
                  var3.printStackTrace();
               }
            }

         }

         public void enter() {
            MsgMgr var1 = this.this$0;
            var1.realKeyClick4(var1.mContext, 49);
         }

         public void goDown() {
            (new Thread((Runnable)(new Runnable(this.this$0) {
               final MsgMgr this$0;

               {
                  this.this$0 = var1;
               }

               public void run() {
                  for(int var1 = 0; var1 < 5; ++var1) {
                     try {
                        MsgMgr var2 = this.this$0;
                        var2.realKeyClick4(var2.mContext, 8);
                        Thread.sleep(500L);
                     } catch (InterruptedException var3) {
                        var3.printStackTrace();
                     }
                  }

               }
            }))).start();
         }

         public void goLeft() {
            MsgMgr var1 = this.this$0;
            var1.realKeyClick4(var1.mContext, 45);
         }

         public void goRight() {
            MsgMgr var1 = this.this$0;
            var1.realKeyClick4(var1.mContext, 46);
         }

         public void goUp() {
            (new Thread(new MsgMgr$touch0x25$1$$ExternalSyntheticLambda0(this.this$0))).start();
         }
      }));
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._213.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      MsgMgr var1 = (MsgMgr)this;
      byte[] var2 = new byte[12];
      Arrays.fill(var2, (byte)32);
      byte[] var3 = "TV".getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
      byte[] var4 = "TV".getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      java.lang.System.arraycopy(var3, 0, var2, 0, var4.length);
      var4 = Util.byteMerger(new byte[]{22, -111, 8}, var2);
      Intrinsics.checkNotNullExpressionValue(var4, "byteMerger(byteMediaStart, dataPackage)");
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var4);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      MsgMgr var1 = (MsgMgr)this;
      byte[] var2 = new byte[12];
      Arrays.fill(var2, (byte)32);
      byte[] var4 = "AUX".getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      byte[] var3 = "AUX".getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
      java.lang.System.arraycopy(var4, 0, var2, 0, var3.length);
      var4 = Util.byteMerger(new byte[]{22, -111, 12}, var2);
      Intrinsics.checkNotNullExpressionValue(var4, "byteMerger(byteMediaStart, dataPackage)");
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var4);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var6 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var6, "getByteArrayToIntArray(canbusInfo)");
      this.mCanBusInfoInt = var6;
      Object var5 = null;
      int[] var4 = var6;
      if (var6 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         var4 = null;
      }

      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 20) {
               if (var3 != 21) {
                  if (var3 != 33) {
                     if (var3 != 34) {
                        if (var3 != 37) {
                           if (var3 != 38) {
                              if (var3 != 49) {
                                 if (var3 != 50) {
                                    if (var3 != 65) {
                                       if (var3 != 174) {
                                          if (var3 != 240) {
                                             if (var3 != 120) {
                                                if (var3 != 121) {
                                                   if (var3 != 165) {
                                                      if (var3 == 166) {
                                                         this.setAmplifierData();
                                                      }
                                                   } else {
                                                      this.setOriginalCarDeviceInfo2();
                                                   }
                                                } else {
                                                   this.setHudData0x79();
                                                }
                                             } else {
                                                this.settingInfo();
                                             }
                                          } else {
                                             this.setVersionInfo();
                                          }
                                       } else {
                                          this.setOriginalCarDeviceInfo();
                                       }
                                    } else {
                                       this.setRadar();
                                    }
                                 } else {
                                    this.setCarInfo();
                                 }
                              } else {
                                 if (this.isAirMsgReturn(var2)) {
                                    return;
                                 }

                                 this.setAirData0x31();
                              }
                           } else {
                              this.setCarType();
                           }
                        } else {
                           this.touch0x25();
                        }
                     } else {
                        this.set0x22PanelKnobData(var1);
                     }
                  } else {
                     this.set0x21PanelKeyData(var1);
                  }
               } else {
                  this.setDriveData2();
               }
            } else {
               this.setDriveData();
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setDoorData();
         }
      } else {
         this.setTrack();
         this.setSwcKey();
         int[] var7 = this.mCanBusInfoInt;
         if (var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            var7 = (int[])var5;
         }

         this.updateSpeedInfo(var7[3]);
      }

   }

   public boolean customLongClick(Context var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      if (var2 == 3) {
         this.realKeyClick(var1, 134);
         return true;
      } else {
         return false;
      }
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      Intrinsics.checkNotNullParameter(var11, "song");
      Intrinsics.checkNotNullParameter(var12, "album");
      Intrinsics.checkNotNullParameter(var13, "artist");
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var1 = (byte)(var2 / 60 % 60);
      byte var14 = (byte)(var2 % 60);
      var11 = (new DecimalFormat("00")).format(var1);
      Intrinsics.checkNotNullExpressionValue(var11, "DecimalFormat(\"00\").format(curMinute)");
      var12 = (new DecimalFormat("00")).format(var14);
      Intrinsics.checkNotNullExpressionValue(var12, "DecimalFormat(\"00\").format(curSecond)");
      var11 = (new DecimalFormat("000")).format((long)var5) + ' ' + var11 + var12 + "    ";
      var14 = 7;
      var1 = var14;
      if (var7 != 1) {
         var1 = var14;
         if (var7 != 2) {
            var1 = var14;
            if (var7 != 3) {
               if (var7 != 6 && var7 != 7) {
                  var1 = 0;
               } else {
                  var1 = 6;
               }
            }
         }
      }

      byte[] var15 = var11.getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var15, "this as java.lang.String).getBytes(charset)");
      var15 = Util.byteMerger(new byte[]{22, -111, var1}, var15);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var15);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      MsgMgr var1 = (MsgMgr)this;
      byte[] var4 = new byte[12];
      Arrays.fill(var4, (byte)32);
      byte[] var3 = "TV".getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
      byte[] var2 = "TV".getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String).getBytes(charset)");
      java.lang.System.arraycopy(var3, 0, var4, 0, var2.length);
      var4 = Util.byteMerger(new byte[]{22, -111, 8}, var4);
      Intrinsics.checkNotNullExpressionValue(var4, "byteMerger(byteMediaStart, dataPackage)");
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), var4);
   }

   public void initCommand(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.initCommand(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      int var2 = this.getCurrentEachCanId();
      if (var2 != 10) {
         if (var2 == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 14, 10});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 8, 10});
      }

      this.initAmplifierData(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Intrinsics.checkNotNullParameter(var11, "currentHourStr");
      Intrinsics.checkNotNullParameter(var12, "currentMinuteStr");
      Intrinsics.checkNotNullParameter(var13, "currentSecondStr");
      Intrinsics.checkNotNullParameter(var21, "songTitle");
      Intrinsics.checkNotNullParameter(var22, "songAlbum");
      Intrinsics.checkNotNullParameter(var23, "songArtist");
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      MsgMgr var27 = (MsgMgr)this;
      byte var26 = (byte)(var9 & -1);
      var11 = (new DecimalFormat("00")).format(var6);
      Intrinsics.checkNotNullExpressionValue(var11, "DecimalFormat(\"00\").format(currentMinute)");
      var12 = (new DecimalFormat("00")).format(var7);
      Intrinsics.checkNotNullExpressionValue(var12, "DecimalFormat(\"00\").format(currentSecond)");
      StringBuilder var29 = new StringBuilder();
      StringCompanionObject var25 = StringCompanionObject.INSTANCE;
      var21 = (new DecimalFormat("000")).format((long)(var26 * 256 + (var3 & 255)));
      Intrinsics.checkNotNullExpressionValue(var21, "DecimalFormat(\"000\").for…urrentPlayIndex.toLong())");
      var21 = String.format(var21, Arrays.copyOf(new Object[0], 0));
      Intrinsics.checkNotNullExpressionValue(var21, "format(format, *args)");
      var11 = var29.append(var21).append(' ').append(var11).append(':').append(var12).append("   ").toString();
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var28 = var11.getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var28, "this as java.lang.String).getBytes(charset)");
      var28 = Util.byteMerger(new byte[]{22, -111, var1}, var28);
      Intrinsics.checkNotNullExpressionValue(var28, "byteMerger(byteMediaStar… mediaInfo.toByteArray())");
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var28);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      Intrinsics.checkNotNullParameter(var2, "currBand");
      Intrinsics.checkNotNullParameter(var3, "currentFreq");
      Intrinsics.checkNotNullParameter(var4, "psName");
      super.radioInfoChange(var1, var2, var3, var4, var5);
      MsgMgr var9 = (MsgMgr)this;
      var5 = var1;
      if (var1 > 6) {
         var5 = 0;
      }

      byte var7 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = (new DecimalFormat("00")).format((long)var5) + ' ' + var3 + "  Khz";
         } else {
            var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3 + "  Khz";
         }
      } else {
         StringBuilder var8;
         if (var3.length() == 5) {
            var8 = (new StringBuilder()).append((new DecimalFormat("00")).format((long)var5)).append("  ");
            var3 = var3.substring(0, var3.length());
            Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String…ing(startIndex, endIndex)");
            var2 = var8.append(var3).append("Mhz").toString();
         } else {
            var8 = (new StringBuilder()).append((new DecimalFormat("00")).format((long)var5)).append(' ');
            var3 = var3.substring(0, var3.length());
            Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String…ing(startIndex, endIndex)");
            var2 = var8.append(var3).append("Mhz").toString();
         }
      }

      byte var6 = (byte)var7;
      byte[] var10 = var2.getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var10, "this as java.lang.String).getBytes(charset)");
      var10 = Util.byteMerger(new byte[]{22, -111, var6}, var10);
      Intrinsics.checkNotNullExpressionValue(var10, "byteMerger(byteMediaStart, temp.toByteArray())");
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var10);
   }

   public final void setFuelUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.mFuelUnit = "l/100km";
            }
         } else {
            this.mFuelUnit = "km/l";
         }
      } else {
         this.mFuelUnit = "mpg";
      }

   }

   public void sourceSwitchChange(String var1) {
      Intrinsics.checkNotNullParameter(var1, "source");
      super.sourceSwitchChange(var1);
      if (Intrinsics.areEqual((Object)SourceConstantsDef.SOURCE_ID.NULL.name(), (Object)var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   public final void touchpadView() {
      this.runOnUi((CallBackInterface)(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new TouchpadEvents()).showAdjustView((Context)this.this$0.getActivity());
         }
      }));
   }

   public final void updateSettings(int var1, int var2, int var3) {
      List var4 = (List)(new ArrayList());
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      Intrinsics.checkNotNullParameter(var8, "currentAllMinuteStr");
      Intrinsics.checkNotNullParameter(var11, "currentHourStr");
      Intrinsics.checkNotNullParameter(var12, "currentMinuteStr");
      Intrinsics.checkNotNullParameter(var13, "currentSecondStr");
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18 = (byte)(var9 & -1);
      StringBuilder var19 = new StringBuilder();
      StringCompanionObject var21 = StringCompanionObject.INSTANCE;
      var11 = (new DecimalFormat("000")).format((long)(var18 * 256 + (var3 & 255)));
      Intrinsics.checkNotNullExpressionValue(var11, "DecimalFormat(\"000\").for…urrentPlayIndex.toLong())");
      var11 = String.format(var11, Arrays.copyOf(new Object[0], 0));
      Intrinsics.checkNotNullExpressionValue(var11, "format(format, *args)");
      var8 = var19.append(var11).append("         ").toString();
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var20 = var8.getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var20, "this as java.lang.String).getBytes(charset)");
      var20 = Util.byteMerger(new byte[]{22, -111, var1}, var20);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var20);
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f"},
      d2 = {"Lcom/hzbhd/canbus/car/_213/MsgMgr$Companion;", "", "()V", "isAirFirst", "", "isDoorFirst", "isLastLongClick", "lastPanelSt", "", "lastPanelkey", "lastSwcKey", "lastSwcSt", "longClickCount", "mIsKonbClockwise", "mIsKonbSelClockwise", "CanBusInfo_release"},
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

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"},
      d2 = {"Lcom/hzbhd/canbus/car/_213/MsgMgr$TimerHelper;", "", "(Lcom/hzbhd/canbus/car/_213/MsgMgr;)V", "mTimer", "Ljava/util/Timer;", "mTimerTask", "Ljava/util/TimerTask;", "startTimer", "", "timerTask", "delay", "", "period", "stopTimer", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private final class TimerHelper {
      private Timer mTimer;
      private TimerTask mTimerTask;
      final MsgMgr this$0;

      public TimerHelper(MsgMgr var1) {
         this.this$0 = var1;
      }

      public final void startTimer(TimerTask var1, long var2, long var4) {
         Log.i("TimerUtil", "startTimer: " + this);
         if (var1 != null) {
            this.mTimerTask = var1;
            if (this.mTimer == null) {
               this.mTimer = new Timer();
            }

            Timer var6 = this.mTimer;
            Intrinsics.checkNotNull(var6);
            var6.schedule(this.mTimerTask, var2, var4);
         }
      }

      public final void stopTimer() {
         Log.i("TimerUtil", "stopTimer: " + this);
         TimerTask var1 = this.mTimerTask;
         if (var1 != null) {
            Intrinsics.checkNotNull(var1);
            var1.cancel();
            this.mTimerTask = null;
         }

         Timer var2 = this.mTimer;
         if (var2 != null) {
            Intrinsics.checkNotNull(var2);
            var2.cancel();
            this.mTimer = null;
         }

      }
   }
}
