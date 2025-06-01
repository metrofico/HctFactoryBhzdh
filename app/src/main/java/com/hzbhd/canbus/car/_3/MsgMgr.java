package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.car._206.MqbHybirdActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\b\u0012\u0018\u0000 \u008c\u00012\u00020\u0001:\b\u008c\u0001\u008d\u0001\u008e\u0001\u008f\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020)H\u0016J\b\u0010-\u001a\u00020)H\u0016J \u0010.\u001a\u00020)2\u0006\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\u001cH\u0016JT\u00102\u001a\u00020)2\u0006\u00103\u001a\u00020\u00162\b\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u00109\u001a\u0002062\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u00162\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J*\u0010>\u001a\u00020)2\b\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u00108\u001a\u0002062\u0006\u00109\u001a\u0002062\u0006\u0010?\u001a\u00020\u0016H\u0016J\u001a\u0010@\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010A\u001a\u00020\u0006H\u0016J\u0018\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u000206H\u0016Jp\u0010E\u001a\u00020)2\u0006\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u00162\u0006\u0010H\u001a\u00020\u00162\u0006\u0010I\u001a\u00020\u00162\u0006\u0010J\u001a\u00020\u00162\u0006\u0010K\u001a\u00020\u00162\u0006\u0010L\u001a\u00020\u00162\u0006\u0010M\u001a\u00020\u00162\u0006\u0010N\u001a\u00020\u00162\u0006\u0010O\u001a\u0002062\u0006\u0010P\u001a\u0002062\u0006\u0010Q\u001a\u0002062\u0006\u0010R\u001a\u00020\u0016H\u0016J\b\u0010S\u001a\u00020)H\u0016J\u0012\u0010T\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J\u0012\u0010U\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0012\u0010V\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J\u0010\u0010W\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010X\u001a\u000206H\u0002J\u0014\u0010Y\u001a\u00020\u00162\n\u0010Z\u001a\u00020\u000e\"\u00020\u0016H\u0002JÄ\u0001\u0010[\u001a\u00020)2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u00020\u00162\u0006\u0010`\u001a\u00020\u00162\u0006\u0010a\u001a\u00020]2\u0006\u0010b\u001a\u00020]2\u0006\u0010c\u001a\u00020]2\u0006\u0010d\u001a\u00020]2\u0006\u0010e\u001a\u00020]2\u0006\u0010f\u001a\u00020]2\b\u0010g\u001a\u0004\u0018\u00010\u001c2\b\u0010h\u001a\u0004\u0018\u00010\u001c2\b\u0010i\u001a\u0004\u0018\u00010\u001c2\u0006\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020]2\u0006\u0010m\u001a\u00020\u00162\u0006\u0010n\u001a\u0002062\u0006\u0010o\u001a\u00020k2\b\u0010p\u001a\u0004\u0018\u00010\u001c2\b\u0010q\u001a\u0004\u0018\u00010\u001c2\b\u0010r\u001a\u0004\u0018\u00010\u001c2\u0006\u0010s\u001a\u000206H\u0016J0\u0010t\u001a\u00020)2\u0006\u0010u\u001a\u00020\u00162\u0006\u0010v\u001a\u00020\u001c2\u0006\u0010w\u001a\u00020\u001c2\u0006\u0010x\u001a\u00020\u001c2\u0006\u0010y\u001a\u00020\u0016H\u0016J\u0010\u0010z\u001a\u00020)2\u0006\u0010{\u001a\u000206H\u0016J!\u0010|\u001a\u00020)2\u0012\u0010}\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060~\"\u00020\u0006H\u0002¢\u0006\u0002\u0010\u007fJ\u001d\u0010\u0080\u0001\u001a\u00020)2\u0007\u0010\u0081\u0001\u001a\u00020\u00162\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u001cH\u0002J\u0012\u0010\u0083\u0001\u001a\u00020)2\u0007\u0010\u0084\u0001\u001a\u000206H\u0016J\u0018\u0010\u0085\u0001\u001a\u00020)2\u0006\u0010/\u001a\u00020\u001c2\u0007\u0010\u0086\u0001\u001a\u00020%J\u009b\u0001\u0010\u0087\u0001\u001a\u00020)2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u00020\u00162\u0006\u0010`\u001a\u00020\u00162\u0006\u0010a\u001a\u00020]2\u0006\u0010b\u001a\u00020]2\u0006\u0010c\u001a\u00020]2\b\u0010d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010e\u001a\u00020]2\u0006\u0010f\u001a\u00020]2\b\u0010g\u001a\u0004\u0018\u00010\u001c2\b\u0010h\u001a\u0004\u0018\u00010\u001c2\b\u0010i\u001a\u0004\u0018\u00010\u001c2\u0006\u0010j\u001a\u00020\u00162\u0007\u0010\u0088\u0001\u001a\u00020]2\u0006\u0010n\u001a\u0002062\u0007\u0010\u0089\u0001\u001a\u00020\u0016H\u0016J\u0016\u0010\u008a\u0001\u001a\u00020\u001c*\u00020\u001c2\u0007\u0010\u008b\u0001\u001a\u00020\u0016H\u0002R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u0016\u0012\b\u0012\u00060\"R\u00020\u00000!X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0090\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "m0x70Sender", "Lcom/hzbhd/canbus/car/_3/MsgMgr$M0x70Sender;", "m0xA6Command", "", "m0xC0Command", "m0xC1Command", "m0xC4Command", "m0xC5Command", "m0xC7Command", "m0xCACommand", "mAirData", "", "getMAirData", "()[I", "setMAirData", "([I)V", "mCanbusInfoByte", "mCanbusInfoInt", "<set-?>", "", "mColour", "getMColour", "()I", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mParserMap", "", "Lcom/hzbhd/canbus/car/_3/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_3/UiMgr;", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "atvInfoChange", "auxInInfoChange", "btMusicId3InfoChange", "title", "artist", "album", "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "btPhoneTalkingWithTimeInfoChange", "time", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "dtvInfoChange", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "isAirDataChange", "mergeValue", "values", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "reverseStateChange", "isReverse", "sendMultiCommand", "commands", "", "([[B)V", "sendTextInfo", "dataType", "text", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettings", "value", "videoInfoChange", "playMode", "duration", "getInfo", "len", "Companion", "M0x70Sender", "Parser", "SpeedAlertHelper", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_BALANCE_DATA_HALF = 9;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final float MILE_TO_KILO_RATE = 1.609344F;
   private static final String TAG = "_3_MsgMgr";
   private static final ArrayList languageList = CollectionsKt.arrayListOf(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 22, 23, 26, 29, 30, 31, 32, 33, 37, 38, 39, 40, 41, 42, 43});
   private final M0x70Sender m0x70Sender = new M0x70Sender(this);
   private final byte[] m0xA6Command = new byte[]{22, -90, 0, 0, 0, 0, 0, 0, 1};
   private final byte[] m0xC0Command = new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0};
   private final byte[] m0xC1Command = new byte[]{22, -63, 0, 0};
   private final byte[] m0xC4Command = new byte[]{22, -60, 0};
   private final byte[] m0xC5Command = new byte[]{22, -59, 0, 0};
   private final byte[] m0xC7Command;
   private final byte[] m0xCACommand;
   private int[] mAirData;
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private int mColour = 1;
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private final Map mParserMap = (Map)(new LinkedHashMap());
   private HashMap mSettingItemIndexHashMap = new HashMap();
   private UiMgr mUiMgr;

   // $FF: synthetic method
   public static void $r8$lambda$HwHjzDNeS6EpsZT9dvE1DoxpydY(Object var0) {
      reverseStateChange$lambda_3(var0);
   }

   public MsgMgr() {
      byte[] var3 = new byte[15];

      for(int var2 = 0; var2 < 15; ++var2) {
         byte var1;
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     var1 = 0;
                  } else {
                     var1 = 1;
                  }
               } else {
                  var1 = 3;
               }
            } else {
               var1 = -54;
            }
         } else {
            var1 = 22;
         }

         var3[var2] = var1;
      }

      this.m0xCACommand = var3;
      this.m0xC7Command = new byte[]{22, -57, 0, 0, 0};
      this.mAirData = new int[0];
   }

   private final String getInfo(String var1, int var2) {
      String var3;
      if (var1.length() < var2) {
         var3 = this.getInfo(var1 + ' ', var2);
      } else {
         var3 = var1;
         if (var1.length() > var2) {
            StringBuilder var4 = new StringBuilder();
            var1 = var1.substring(0, var2 - 2);
            Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String…ing(startIndex, endIndex)");
            var3 = var4.append(var1).append("..").toString();
         }
      }

      return var3;
   }

   private final void initAmplifier(Context var1) {
      Log.i("_3_MsgMgr", "initAmplifier: context[" + var1 + ']');
      if (var1 != null) {
         int var5 = this.getCanId();
         UiMgr var7 = this.mUiMgr;
         UiMgr var6 = var7;
         if (var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
            var6 = null;
         }

         this.getAmplifierData(var1, var5, var6.getAmplifierPageUiSet(var1));
      }

      byte[] var12 = new byte[]{22, -127, 1};
      byte[] var9 = this.m0xC4Command;
      byte var2 = (byte)GeneralAmplifierData.volume;
      byte[] var10 = new byte[]{22, -88, 1, (byte)GeneralAmplifierData.bandTreble};
      byte[] var8 = new byte[]{22, -88, 2, (byte)GeneralAmplifierData.bandMiddle};
      byte[] var13 = new byte[]{22, -88, 3, (byte)GeneralAmplifierData.bandBass};
      byte var3 = (byte)(GeneralAmplifierData.frontRear + 9);
      byte var4 = (byte)(GeneralAmplifierData.leftRight + 9);
      Iterator var14 = ArrayIteratorKt.iterator((Object[])(new byte[][]{var12, var9, {22, -88, 0, var2}, var10, var8, var13, {22, -88, 4, var3}, {22, -88, 5, var4}}));
      TimerUtil var11 = new TimerUtil();
      var11.startTimer((TimerTask)(new TimerTask(var14, var11) {
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

   private final void initItemsIndexHashMap(Context var1) {
      Log.i("_3_MsgMgr", "initItems: ");
      UiMgr var5 = this.mUiMgr;
      UiMgr var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      Iterator var6 = var4.getSettingUiSet(var1).getList().iterator();

      int var2;
      int var3;
      for(var2 = 0; var6.hasNext(); ++var2) {
         Iterator var7 = ((SettingPageUiSet.ListBean)var6.next()).getItemList().iterator();

         for(var3 = 0; var7.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)var7.next();
            Map var11 = (Map)this.mSettingItemIndexHashMap;
            String var15 = var8.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var15, "itemListBean.titleSrn");
            var11.put(var15, new SettingUpdateEntity(var2, var3));
         }
      }

      Iterator var9 = var4.getDriverDataPageUiSet(var1).getList().iterator();

      for(var2 = 0; var9.hasNext(); ++var2) {
         Iterator var12 = ((DriverDataPageUiSet.Page)var9.next()).getItemList().iterator();

         for(var3 = 0; var12.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var13 = (DriverDataPageUiSet.Page.Item)var12.next();
            Map var10 = (Map)this.mDriveItemIndexHashMap;
            String var14 = var13.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var14, "item.titleSrn");
            var10.put(var14, new DriverUpdateEntity(var2, var3, "null_value"));
         }
      }

   }

   private final void initParsers(Context var1) {
      Map var3 = this.mParserMap;
      var3.put(32, new Parser(this, var1) {
         final Context $context;
         private int index;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void compoundKey(int... var1) {
            MsgMgr var2 = this.this$0;
            var2.compoundKey(this.$context, var1, var2.mCanbusInfoInt[3]);
         }

         private final void realKeyLongClick1(int var1) {
            MsgMgr var2 = this.this$0;
            var2.realKeyLongClick1(this.$context, var1, var2.mCanbusInfoInt[3]);
         }

         public void parse(int var1) {
            switch (this.this$0.mCanbusInfoInt[2]) {
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
                  this.realKeyLongClick1(20);
                  break;
               case 4:
                  this.realKeyLongClick1(21);
                  break;
               case 5:
                  this.realKeyLongClick1(188);
                  break;
               case 6:
                  this.realKeyLongClick1(3);
                  break;
               case 7:
                  this.realKeyLongClick1(2);
                  break;
               case 8:
                  this.realKeyLongClick1(187);
            }

         }
      });
      var3.put(33, new Parser(this, var1) {
         final Context $context;
         private int data8;
         private int[] frontData;
         private final ArrayList list;
         private int rearData;
         private int rearDataRecord;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$3OhHZjZQSubSD_KAousktMtcJwo(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$AxWkMsEbkyx_zhQMolQRBvEdLyI(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_2(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$BCcsuT7jTYPeLVgjVV6lHUs_lF8(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Co0xYp7jJzjgE8lrDLHqzbHmJGQ(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$SLOWKEYlue0nNsxZTvBp3WSuJ1o(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$T0bUOuaFrK_tIhI9nKXMMgdQX4Y(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$gRYoqT5e6SFEb3AsR3s0_gLug80(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_8(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$oh6JcKkSu3ar_rgGS9GjPw2vxT4(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
            this.frontData = new int[0];
         }

         private final int getWhat() {
            short var4;
            if (Arrays.equals(this.frontData, this.this$0.mCanbusInfoInt)) {
               int var1 = this.rearDataRecord;
               int var2 = this.rearData;
               if (var1 == var2) {
                  var4 = 0;
               } else {
                  this.rearDataRecord = var2;
                  var4 = 1003;
               }
            } else {
               int[] var3 = this.this$0.mCanbusInfoInt;
               var3 = Arrays.copyOf(var3, var3.length);
               Intrinsics.checkNotNullExpressionValue(var3, "copyOf(this, size)");
               this.frontData = var3;
               var4 = 1004;
            }

            return var4;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.power = var2;
            if ((var0.mCanbusInfoInt[2] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac = var2;
            if ((var0.mCanbusInfoInt[2] >> 5 & 1) == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.in_out_cycle = var2;
            GeneralAirData.auto_1_2 = var0.mCanbusInfoInt[2] >> 3 & 3;
            if ((var0.mCanbusInfoInt[2] >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.dual = var2;
            if ((var0.mCanbusInfoInt[2] >> 1 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.max_front = var2;
            if ((var0.mCanbusInfoInt[2] & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear = var2;
            if (GeneralAirData.auto_1_2 == 0 && !GeneralAirData.ac_max && !GeneralAirData.max_front) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.manual = var2;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[3];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_window = var2;
            if ((var0.mCanbusInfoInt[3] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_head = var2;
            if ((var0.mCanbusInfoInt[3] >> 5 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_foot = var2;
            GeneralAirData.front_wind_level = var0.mCanbusInfoInt[3] & 15;
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         }

         private static final void setOnParseListeners$lambda_2(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_left_temperature = var0.toTemperature(var1.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_3(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_right_temperature = var0.toTemperature(var1.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[6];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_defog = var2;
            if ((var0.mCanbusInfoInt[6] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_defog = var2;
            if ((var0.mCanbusInfoInt[6] >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.aqs = var2;
            if ((var0.mCanbusInfoInt[6] >> 4 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.eco = var2;
            if ((var0.mCanbusInfoInt[6] >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac_max = var2;
            if ((var0.mCanbusInfoInt[6] >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.clean_air = var2;
            if ((var0.mCanbusInfoInt[6] & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.fahrenheit_celsius = var2;
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[7];
            boolean var2 = true;
            if ((var1 >> 7 & 1) != 1) {
               var2 = false;
            }

            GeneralAirData.steering_wheel_heating = var2;
            GeneralAirData.front_left_seat_heat_level = var0.mCanbusInfoInt[7] >> 4 & 7;
            GeneralAirData.front_right_seat_heat_level = var0.mCanbusInfoInt[7] & 7;
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAirData.front_left_seat_cold_level = var0.mCanbusInfoInt[8] >> 6 & 3;
            GeneralAirData.front_right_seat_cold_level = var0.mCanbusInfoInt[8] >> 4 & 3;
            GeneralAirData.auto_wind_lv = var0.mCanbusInfoInt[8] & 3;
         }

         private static final void setOnParseListeners$lambda_8(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var1.mCanbusInfoInt[9];
            var0.rearData = var2;
            GeneralAirData.rear_temperature = var0.toTemperature(var2);
            var1.mCanbusInfoInt[9] = 0;
         }

         private final String toTemperature(int var1) {
            String var3;
            if (var1 == 0) {
               var3 = "LO";
            } else if (var1 == 31) {
               var3 = "HI";
            } else {
               boolean var2;
               if (1 <= var1 && var1 < 29) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  if (GeneralAirData.fahrenheit_celsius) {
                     var3 = "" + (var1 + 59) + '℉';
                  } else {
                     StringCompanionObject var4 = StringCompanionObject.INSTANCE;
                     var3 = String.format("%.1f℃", Arrays.copyOf(new Object[]{(float)(var1 + 31) / 2.0F}, 1));
                     Intrinsics.checkNotNullExpressionValue(var3, "format(format, *args)");
                  }
               } else {
                  var3 = "";
               }
            }

            return var3;
         }

         public void parse(int var1) {
            if (var1 > 8) {
               if (this.data8 != this.this$0.mCanbusInfoInt[10]) {
                  this.data8 = this.this$0.mCanbusInfoInt[10];
                  this.list.clear();
                  SettingUpdateEntity var3 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_21h_d8_b7");
                  MsgMgr var2;
                  if (var3 != null) {
                     var2 = this.this$0;
                     this.list.add(var3.setValue(var2.mCanbusInfoInt[10] >> 7 & 1));
                  }

                  var3 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_21h_d8_b65");
                  if (var3 != null) {
                     var2 = this.this$0;
                     this.list.add(var3.setValue(var2.mCanbusInfoInt[10] >> 5 & 3));
                  }

                  this.this$0.updateGeneralSettingData((List)this.list);
                  this.this$0.updateSettingActivity((Bundle)null);
               }

               this.this$0.mCanbusInfoInt[10] = 0;
            }

            this.this$0.mCanbusInfoInt[3] &= 239;
            if (this.this$0.isAirDataChange()) {
               super.parse(var1);
               this.this$0.updateAirActivity(this.$context, this.getWhat());
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda2(this, this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda3(this, this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda7(this, this.this$0)};
         }
      });
      var3.put(47, new Parser(this, var1) {
         final Context $context;
         private int preFast;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void realKeyClick4(int var1) {
            this.this$0.realKeyClick(this.$context, var1);
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[2];
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 != 5) {
                           switch (var1) {
                              case 17:
                                 this.realKeyClick4(14);
                                 break;
                              case 18:
                                 this.realKeyClick4(15);
                                 break;
                              case 19:
                                 this.realKeyClick4(137);
                                 break;
                              case 20:
                                 this.realKeyClick4(0);
                                 break;
                              case 21:
                                 this.realKeyClick4(0);
                                 break;
                              case 22:
                                 this.realKeyClick4(135);
                                 break;
                              case 23:
                                 this.realKeyClick4(136);
                                 break;
                              case 24:
                                 this.realKeyClick4(137);
                                 break;
                              case 25:
                                 this.realKeyClick4(138);
                           }
                        } else {
                           this.realKeyClick4(this.preFast);
                        }
                     } else {
                        this.preFast = 23;
                        this.realKeyClick4(23);
                     }
                  } else {
                     this.preFast = 22;
                     this.realKeyClick4(22);
                  }
               } else {
                  this.realKeyClick4(46);
               }
            } else {
               this.realKeyClick4(45);
            }

         }
      });
      var3.put(34, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            this.this$0.mCanbusInfoInt[6] = 0;
            this.this$0.mCanbusInfoInt[7] = 0;
            if (this.isDataChange()) {
               RadarInfoUtil.setRearRadarLocationData(10, MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[2], 60), MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[3], 165), MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[4], 165), MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[5], 60));
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var3.put(35, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            this.this$0.mCanbusInfoInt[6] = 0;
            this.this$0.mCanbusInfoInt[7] = 0;
            if (this.isDataChange()) {
               RadarInfoUtil.setFrontRadarLocationData(10, MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[2], 60), MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[3], 120), MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[4], 120), MsgMgr.initParsers$lambda_4$getRadarValue(this.this$0.mCanbusInfoInt[5], 60));
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var3.put(36, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            this.this$0.mCanbusInfoInt[2] &= 252;
            int[] var4 = this.this$0.mCanbusInfoInt;
            boolean var3 = false;
            var4[3] = 0;
            if (this.isDataChange()) {
               boolean var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 7 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 6 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 5 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightRearDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 4 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftRearDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 3 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isBackOpen = var2;
               var2 = var3;
               if ((this.this$0.mCanbusInfoInt[2] >> 2 & 1) == 1) {
                  var2 = true;
               }

               GeneralDoorData.isFrontOpen = var2;
               this.this$0.updateDoorView(this.$context);
            }

         }
      });
      var3.put(37, new Parser(this, var1) {
         final Context $context;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         private final String toSwitch(int var1) {
            Context var3 = this.$context;
            String var2;
            if (Integer.valueOf(var1).equals(1)) {
               var2 = "_103_car_setting_value_7_1";
            } else {
               var2 = "_103_car_setting_value_7_0";
            }

            return CommUtil.getStrByResId(var3, var2);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               DriverUpdateEntity var2 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_41_rear_radar");
               MsgMgr var3;
               if (var2 != null) {
                  var3 = this.this$0;
                  this.list.add(var2.setValue(this.toSwitch(var3.mCanbusInfoInt[2] >> 3 & 1)));
               }

               var2 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_41_front_radar");
               if (var2 != null) {
                  var3 = this.this$0;
                  this.list.add(var2.setValue(this.toSwitch(var3.mCanbusInfoInt[2] >> 2 & 1)));
               }

               var2 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_41_park_assist");
               if (var2 != null) {
                  var3 = this.this$0;
                  this.list.add(var2.setValue(this.toSwitch(var3.mCanbusInfoInt[2] >> 1 & 1)));
               }

               DriverUpdateEntity var5 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_41_radar_sound");
               if (var5 != null) {
                  MsgMgr var4 = this.this$0;
                  this.list.add(var5.setValue(this.toSwitch(var4.mCanbusInfoInt[2] & 1)));
               }

               this.this$0.updateGeneralDriveData((List)this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
               GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.this$0.mCanbusInfoInt[2]);
               this.this$0.updatePKeyRadar();
            }

         }
      });
      var3.put(39, new Parser(this, var1) {
         final Context $context;
         private String outdoorTemperature;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.outdoorTemperature = "";
         }

         public void parse(int var1) {
            short var4 = (short)(this.this$0.mCanbusInfoInt[4] << 8 | this.this$0.mCanbusInfoInt[3]);
            StringBuilder var3 = (new StringBuilder()).append(var4 / 10).append(' ');
            String var2;
            if ((this.this$0.mCanbusInfoInt[2] & 1) == 1) {
               var2 = "℉";
            } else {
               var2 = "℃";
            }

            var2 = var3.append(var2).toString();
            Log.i("_3_MsgMgr", "parse: value[" + var4 + "] result[" + var2 + "] outdoorTemperature[" + this.outdoorTemperature + ']');
            if (!Intrinsics.areEqual((Object)this.outdoorTemperature, (Object)var2)) {
               this.outdoorTemperature = var2;
               this.this$0.updateOutDoorTemp(this.$context, var2);
            }

         }
      });
      var3.put(40, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$A9pt_TWLTsPMs4peHAhctHs6D5w(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$EQtyXKo_U98I_vT8qzd_CCotraE(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$U7p3kdzwzp6vYJ8kxqsdNStJTUs(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_power = var2;
            if ((var0.mCanbusInfoInt[2] >> 3 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.auto_manual = var2;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[3];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_head = var2;
            if ((var0.mCanbusInfoInt[3] >> 5 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_foot = var2;
            GeneralAirData.rear_wind_level = var0.mCanbusInfoInt[3] & 15;
            GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
            GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAirData.rear_left_seat_heat_level = var0.mCanbusInfoInt[4] >> 4 & 7;
            GeneralAirData.rear_right_seat_heat_level = var0.mCanbusInfoInt[4] & 7;
         }

         public void parse(int var1) {
            if (this.this$0.isAirDataChange()) {
               super.parse(var1);
               this.this$0.updateAirActivity(this.$context, 1003);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda2(this.this$0)};
         }
      });
      var3.put(41, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 19918, 16);
               this.this$0.updateTrack();
            }

         }
      });
      var3.put(42, new Parser(this, var1) {
         final Context $context;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               int var3 = this.this$0.mCanbusInfoInt[2] << 8 | this.this$0.mCanbusInfoInt[3];
               boolean var2 = true;
               boolean var6;
               if (var3 >= 0 && var3 < 36) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               String var4;
               if (var6) {
                  var4 = "pm_excellent";
               } else {
                  if (36 <= var3 && var3 < 76) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  if (var6) {
                     var4 = "pm_good";
                  } else {
                     if (76 <= var3 && var3 < 116) {
                        var6 = true;
                     } else {
                        var6 = false;
                     }

                     if (var6) {
                        var4 = "pm_mild_pollution";
                     } else {
                        if (116 <= var3 && var3 < 151) {
                           var6 = true;
                        } else {
                           var6 = false;
                        }

                        if (var6) {
                           var4 = "pm_moderately_polluted";
                        } else {
                           if (151 <= var3 && var3 < 251) {
                              var6 = true;
                           } else {
                              var6 = false;
                           }

                           if (var6) {
                              var4 = "pm_heavy_pollution";
                           } else {
                              if (251 <= var3 && var3 < 1001) {
                                 var6 = var2;
                              } else {
                                 var6 = false;
                              }

                              if (var6) {
                                 var4 = "pm_severe_pollution";
                              } else {
                                 var4 = null;
                              }
                           }
                        }
                     }
                  }
               }

               label56: {
                  if (var4 != null) {
                     Context var5 = this.$context;
                     var4 = "" + var3 + ' ' + CommUtil.getStrByResId(var5, var4);
                     if (var4 != null) {
                        break label56;
                     }
                  }

                  var4 = "---";
               }

               DriverUpdateEntity var7 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_2ah_d1");
               if (var7 != null) {
                  this.list.add(var7.setValue(var4));
               }

               this.this$0.updateGeneralDriveData((List)this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      var3.put(48, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            var2.updateVersionInfo(this.$context, var2.getVersionStr(var2.mCanbusInfoByte));
         }
      });
      var3.put(50, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            this.this$0.mCanbusInfoInt[6] = 0;
            this.this$0.mCanbusInfoInt[7] = 0;
            if (this.isDataChange()) {
               RadarInfoUtil.setLeftRadarLocationData(7, this.this$0.mCanbusInfoInt[2] + 1, this.this$0.mCanbusInfoInt[3] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[5] + 1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var3.put(51, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            this.this$0.mCanbusInfoInt[6] = 0;
            this.this$0.mCanbusInfoInt[7] = 0;
            if (this.isDataChange()) {
               RadarInfoUtil.setRightRadarLocationData(7, this.this$0.mCanbusInfoInt[2] + 1, this.this$0.mCanbusInfoInt[3] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[5] + 1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var3.put(64, new Parser(this, var1) {
         final Context $context;
         private int driveModeButtonStatus;
         private int isShowDriveAssist;
         private int isShowDriveMode;
         private int isShowParkSetting;
         private final ArrayList list;
         private int m0x40Param4;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               int[] var6 = this.this$0.mCanbusInfoInt;
               byte var10 = 2;
               int var2 = var6[2];
               MsgMgr var7;
               SettingUpdateEntity var12;
               if (var2 == 0) {
                  var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_language_setup");
                  if (var12 != null) {
                     var7 = this.this$0;
                     this.list.add(var12.setValue(MsgMgr.Companion.getLanguageList().indexOf(var7.mCanbusInfoInt[3])));
                  }
               } else {
                  boolean var5 = true;
                  MsgMgr var13;
                  SettingUpdateEntity var15;
                  if (var2 != 16) {
                     if (var2 == 32) {
                        var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_20h_p1_b0");
                        if (var15 != null) {
                           var13 = this.this$0;
                           this.list.add(var15.setValue(var13.mCanbusInfoInt[3] & 1));
                        }

                        SpeedAlertHelper.INSTANCE.setValue(this.this$0.mCanbusInfoInt[4]);
                        var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_20h_p2");
                        if (var12 != null) {
                           var12.setValue(SpeedAlertHelper.INSTANCE.getValue());
                           var12.setProgress(SpeedAlertHelper.INSTANCE.getProgress());
                           this.list.add(var12);
                        }

                        var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_title_3");
                        if (var12 != null) {
                           var7 = this.this$0;
                           this.list.add(var12.setValue(var7.mCanbusInfoInt[5]));
                        }
                     } else {
                        String var14 = "english_on";
                        boolean var4;
                        MsgMgr var8;
                        MsgMgr var9;
                        ArrayList var16;
                        SettingUpdateEntity var17;
                        Unit var18;
                        if (var2 != 64) {
                           if (var2 == 96) {
                              var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_syncchronous_adjustment");
                              if (var15 != null) {
                                 var13 = this.this$0;
                                 this.list.add(var15.setValue(var13.mCanbusInfoInt[3] & 1));
                              }

                              var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_lower_while_reversing");
                              if (var15 != null) {
                                 var13 = this.this$0;
                                 this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 1 & 1));
                              }

                              var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_auto_wiping");
                              if (var12 != null) {
                                 var7 = this.this$0;
                                 this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 2 & 1));
                              }

                              var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_rear_window_wiping");
                              if (var15 != null) {
                                 var13 = this.this$0;
                                 this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 3 & 1));
                              }

                              var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_60h_p2_b0");
                              if (var12 != null) {
                                 var7 = this.this$0;
                                 this.list.add(var12.setValue(var7.mCanbusInfoInt[4]));
                              }
                           } else if (var2 != 112) {
                              if (var2 == 128) {
                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_current_consumption");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[3] & 1));
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_consumption");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 1 & 1));
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_con_consumers");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 2 & 1));
                                 }

                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_eco_tips");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 3 & 1));
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_travelling_time");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 4 & 1));
                                 }

                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_distance_travelled");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 5 & 1));
                                 }

                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_speed");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 6 & 1));
                                 }

                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_digital_speed_display");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 7 & 1));
                                 }

                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_speed_warning");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[4] & 1));
                                 }

                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_oil_temperature");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[4] >> 1 & 1));
                                 }
                              } else if (var2 != 144) {
                                 ArrayList var19;
                                 if (var2 == 160) {
                                    var1 = this.this$0.mCanbusInfoInt[3] >> 5 & 1;
                                    if (this.isShowDriveMode != var1) {
                                       this.isShowDriveMode = var1;
                                       if (var1 == 1) {
                                          var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_title_2");
                                          if (var12 != null) {
                                             this.this$0.startSettingActivity(this.$context, var12.getLeftListIndex(), var12.getRightListIndex());
                                             var18 = Unit.INSTANCE;
                                             var18 = Unit.INSTANCE;
                                          } else {
                                             var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_hybrid_emode");
                                             if (var12 != null) {
                                                this.this$0.startSettingActivity(this.$context, var12.getLeftListIndex(), var12.getRightListIndex());
                                                var18 = Unit.INSTANCE;
                                                var18 = Unit.INSTANCE;
                                             }
                                          }
                                       } else if (this.this$0.getActivity() instanceof SettingActivity && this.this$0.isActivityFront()) {
                                          this.this$0.finishActivity();
                                       }
                                    }

                                    var1 = this.this$0.mCanbusInfoInt[3] >> 4 & 1;
                                    if (var1 == 1 && this.driveModeButtonStatus == 0) {
                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_title_2");
                                       if (var12 != null) {
                                          this.this$0.startSettingActivity(this.$context, var12.getLeftListIndex(), var12.getRightListIndex());
                                          var18 = Unit.INSTANCE;
                                          var18 = Unit.INSTANCE;
                                       } else {
                                          var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_hybrid_emode");
                                          if (var12 != null) {
                                             this.this$0.startSettingActivity(this.$context, var12.getLeftListIndex(), var12.getRightListIndex());
                                             var18 = Unit.INSTANCE;
                                             var18 = Unit.INSTANCE;
                                          }
                                       }
                                    }

                                    this.driveModeButtonStatus = var1;
                                    var2 = this.this$0.mCanbusInfoInt[3] & 15;
                                    var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_title_2");
                                    if (var12 != null) {
                                       this.list.add(var12.setValue(var2));
                                    }

                                    var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_individual_engine");
                                    if (var17 != null) {
                                       var7 = this.this$0;
                                       var19 = this.list;
                                       var15 = var17.setValue(var7.mCanbusInfoInt[4] >> 4 & 15);
                                       if (var2 == 3) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_individual_steering");
                                    if (var15 != null) {
                                       var8 = this.this$0;
                                       var19 = this.list;
                                       var15 = var15.setValue(var8.mCanbusInfoInt[4] & 15);
                                       if (var2 == 3) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_individual_cornering_light");
                                    if (var17 != null) {
                                       var7 = this.this$0;
                                       var19 = this.list;
                                       var15 = var17.setValue(var7.mCanbusInfoInt[5] >> 4 & 15);
                                       if (var2 == 3) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_individual_air_conditioning");
                                    if (var17 != null) {
                                       var7 = this.this$0;
                                       var19 = this.list;
                                       if ((var7.mCanbusInfoInt[5] & 15) == 2) {
                                          var10 = 1;
                                       } else {
                                          var10 = 0;
                                       }

                                       var15 = var17.setValue(Integer.valueOf(var10));
                                       if (var2 == 3) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("individual_reset");
                                    if (var12 != null) {
                                       var16 = this.list;
                                       if (var2 == 3) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var16.add(var12.setEnable(var4));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_hybrid_emode");
                                    if (var15 != null) {
                                       var13 = this.this$0;
                                       this.list.add(var15.setValue(var13.mCanbusInfoInt[6] & 7));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_drive");
                                    if (var15 != null) {
                                       var19 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_steering");
                                    if (var12 != null) {
                                       var16 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var16.add(var12.setEnable(var4));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_four_wheel_drive");
                                    if (var15 != null) {
                                       var19 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_air_conditioning");
                                    if (var15 != null) {
                                       var19 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("hillDescentAssist");
                                    if (var15 != null) {
                                       var19 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("hillHoidAssist");
                                    if (var12 != null) {
                                       var16 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var16.add(var12.setEnable(var4));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("parkingAssist");
                                    if (var15 != null) {
                                       var19 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("off_road_reset");
                                    if (var15 != null) {
                                       var19 = this.list;
                                       if (var2 == 6) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }
                                 } else if (var2 != 48) {
                                    if (var2 == 49) {
                                       var1 = this.this$0.mCanbusInfoInt[3];
                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_last_distance_selected");
                                       if (var12 != null) {
                                          var7 = this.this$0;
                                          this.list.add(var12.setValue(var7.mCanbusInfoInt[3] & 1));
                                       }

                                       var2 = this.this$0.mCanbusInfoInt[3] >> 1 & 1;
                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_1");
                                       if (var12 != null) {
                                          this.list.add(var12.setValue(var2));
                                       }

                                       var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_2");
                                       if (var17 != null) {
                                          var7 = this.this$0;
                                          var19 = this.list;
                                          var15 = var17.setValue(var7.mCanbusInfoInt[3] >> 2 & 1);
                                          if (var2 == 1) {
                                             var4 = true;
                                          } else {
                                             var4 = false;
                                          }

                                          var19.add(var15.setEnable(var4));
                                       }

                                       var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_3");
                                       if (var17 != null) {
                                          var7 = this.this$0;
                                          var19 = this.list;
                                          var15 = var17.setValue(var7.mCanbusInfoInt[3] >> 3 & 1);
                                          if (var2 == 1) {
                                             var4 = true;
                                          } else {
                                             var4 = false;
                                          }

                                          var19.add(var15.setEnable(var4));
                                       }

                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_acc_drive_program");
                                       if (var12 != null) {
                                          var7 = this.this$0;
                                          this.list.add(var12.setValue(var7.mCanbusInfoInt[4] & 15));
                                       }

                                       var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_acc_distance");
                                       if (var15 != null) {
                                          var8 = this.this$0;
                                          var19 = this.list;
                                          var15 = var15.setValue(var8.mCanbusInfoInt[4] >> 4 & 15);
                                          if ((var1 & 1) == 0) {
                                             var4 = true;
                                          } else {
                                             var4 = false;
                                          }

                                          var19.add(var15.setEnable(var4));
                                       }

                                       var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_31h_p3_b0");
                                       if (var15 != null) {
                                          var13 = this.this$0;
                                          this.list.add(var15.setValue(var13.mCanbusInfoInt[5] & 1));
                                       }

                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_7");
                                       if (var12 != null) {
                                          var7 = this.this$0;
                                          this.list.add(var12.setValue(var7.mCanbusInfoInt[5] >> 1 & 1));
                                       }

                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_31h_p3_b32");
                                       if (var12 != null) {
                                          var7 = this.this$0;
                                          this.list.add(var12.setValue(var7.mCanbusInfoInt[5] >> 2 & 3));
                                       }

                                       var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("lane_assist_system_brightness");
                                       if (var15 != null) {
                                          var13 = this.this$0;
                                          var15.setValue((var13.mCanbusInfoInt[5] >> 4 & 15) + 1);
                                          var15.setProgress(var13.mCanbusInfoInt[5] >> 4 & 15);
                                          this.list.add(var15);
                                       }
                                    } else if (var2 != 192) {
                                       if (var2 == 193) {
                                          var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("dashboard_mode");
                                          if (var12 != null) {
                                             var8 = this.this$0;
                                             var16 = this.list;
                                             if (var8.mCanbusInfoInt[3] == 255) {
                                                var1 = 4;
                                             } else {
                                                var1 = var8.mCanbusInfoInt[3] - 2;
                                             }

                                             var16.add(var12.setValue(var1));
                                          }

                                          var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_29_left_side");
                                          if (var15 != null) {
                                             var13 = this.this$0;
                                             this.list.add(var15.setValue(var13.mCanbusInfoInt[4]));
                                          }

                                          var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_29_right_side");
                                          if (var15 != null) {
                                             var13 = this.this$0;
                                             this.list.add(var15.setValue(var13.mCanbusInfoInt[5]));
                                          }
                                       } else {
                                          label868: {
                                             switch (var2) {
                                                case 80:
                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_switch_on_time");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[3] & 15));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_auto_control");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 4 & 1));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_lane_change_flash");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 5 & 1));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_light_4");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 6 & 1));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_ins_swi_lighting");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setProgress(var7.mCanbusInfoInt[4] / 10).setValue(var12.getProgress()));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_coming_home");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setProgress(var7.mCanbusInfoInt[5] / 5).setValue(var12.getProgress()));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_leaving_home");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setProgress(var7.mCanbusInfoInt[6] / 5).setValue(var12.getProgress()));
                                                   }
                                                   break label868;
                                                case 81:
                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_travel_mode");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[3] & 1));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("headlight_illumination_distance_adjustment");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setProgress(var13.mCanbusInfoInt[3] >> 1 & 7).setValue(var15.getProgress()));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_door_ambient_light");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setProgress(var13.mCanbusInfoInt[4] / 10).setValue(var15.getProgress()));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_footwell_light");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setProgress(var13.mCanbusInfoInt[5] / 10).setValue(var15.getProgress()));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_follow");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[6] & 1));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_assist");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[6] >> 1 & 1));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_background_lighting");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[6] >> 3 & 1));
                                                   }

                                                   var13 = this.this$0;
                                                   var13.mColour = var13.mCanbusInfoInt[6] >> 4 & 15;
                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_environment_color");
                                                   if (var12 == null) {
                                                      break label868;
                                                   }

                                                   label764: {
                                                      var8 = this.this$0;
                                                      var16 = this.list;
                                                      var2 = var8.getMColour();
                                                      if (var2 != 1) {
                                                         if (var2 == 4) {
                                                            var10 = 1;
                                                            break label764;
                                                         }

                                                         if (var2 == 5) {
                                                            break label764;
                                                         }
                                                      }

                                                      var10 = 0;
                                                   }

                                                   var16.add(var12.setValue(Integer.valueOf(var10)));
                                                   break label868;
                                                case 82:
                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_roof_light");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setProgress(var13.mCanbusInfoInt[3] / 10).setValue(var15.getProgress()));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_front_light");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setProgress(var7.mCanbusInfoInt[4] / 10).setValue(var12.getProgress()));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_52h_p3_b76");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[5] >> 6 & 3));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_52h_p3_b50");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[5] & 63));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_all_area_light");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setProgress(var7.mCanbusInfoInt[6] / 10).setValue(var12.getProgress()));
                                                   }
                                                   break label868;
                                                case 83:
                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_handle_box_light");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setProgress(var13.mCanbusInfoInt[3] / 10).setValue(var15.getProgress()));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("two_color_sync");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(1 - (var7.mCanbusInfoInt[4] >> 7 & 1)));
                                                   }

                                                   var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("on_off_btn_txt_7");
                                                   if (var17 != null) {
                                                      var13 = this.this$0;
                                                      var16 = this.list;
                                                      if ((var13.mCanbusInfoInt[4] & 15) == 1) {
                                                         var1 = 0;
                                                      } else {
                                                         var1 = (var13.mCanbusInfoInt[4] & 15) - 2;
                                                      }

                                                      var16.add(var17.setValue(var1));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("first_color");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[5] & 63).setProgress((var7.mCanbusInfoInt[5] & 63) - 1));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("second_color");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[6] & 63).setProgress((var13.mCanbusInfoInt[6] & 63) - 1));
                                                   }
                                                   break label868;
                                             }

                                             switch (var2) {
                                                case 176:
                                                   var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_14_1");
                                                   if (var17 != null) {
                                                      var9 = this.this$0;
                                                      var16 = this.list;
                                                      if ((var9.mCanbusInfoInt[3] & 1) != 1) {
                                                         var14 = "english_off";
                                                      }

                                                      var16.add(var17.setValue(var14));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("seat_remote_key_memory_matching");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 1 & 1));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("driver_seat");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 2 & 1));
                                                   }

                                                   UiMgr var20 = this.this$0.mUiMgr;
                                                   UiMgr var21 = var20;
                                                   if (var20 == null) {
                                                      Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
                                                      var21 = null;
                                                   }

                                                   var21.getCusPanoramicView(this.$context).update(this.this$0.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5], this.this$0.mCanbusInfoInt[6]);
                                                   break;
                                                case 177:
                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_drive");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 4 & 15));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_steering");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[3] & 15));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_four_wheel_drive");
                                                   if (var15 != null) {
                                                      var13 = this.this$0;
                                                      this.list.add(var15.setValue(var13.mCanbusInfoInt[4] >> 4 & 15));
                                                   }

                                                   var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_offroad_air_conditioning");
                                                   if (var17 != null) {
                                                      var7 = this.this$0;
                                                      var19 = this.list;
                                                      if ((var7.mCanbusInfoInt[4] & 15) == 2) {
                                                         var10 = 1;
                                                      } else {
                                                         var10 = 0;
                                                      }

                                                      var19.add(var17.setValue(Integer.valueOf(var10)));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("hillDescentAssist");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[5] >> 7 & 1));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("hillHoidAssist");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[5] >> 6 & 1));
                                                   }

                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("parkingAssist");
                                                   if (var12 != null) {
                                                      var7 = this.this$0;
                                                      this.list.add(var12.setValue(var7.mCanbusInfoInt[5] >> 5 & 1));
                                                   }
                                                   break;
                                                case 178:
                                                   int var3 = this.this$0.mCanbusInfoInt[3] >> 7 & 1;
                                                   var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("individual");
                                                   if (var12 != null) {
                                                      this.list.add(var12.setValue(var3));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("user_account");
                                                   if (var15 != null) {
                                                      var8 = this.this$0;
                                                      var19 = this.list;
                                                      var15 = var15.setValue(var8.mCanbusInfoInt[3] >> 4 & 7);
                                                      if (var3 == 1) {
                                                         var4 = true;
                                                      } else {
                                                         var4 = false;
                                                      }

                                                      var19.add(var15.setEnable(var4));
                                                   }

                                                   var1 = this.this$0.mCanbusInfoInt[3] >> 3 & 1;
                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("key_assign");
                                                   if (var15 != null) {
                                                      var19 = this.list;
                                                      var15 = var15.setValue(var1);
                                                      if (var3 == 1) {
                                                         var4 = true;
                                                      } else {
                                                         var4 = false;
                                                      }

                                                      var19.add(var15.setEnable(var4));
                                                   }

                                                   var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("assign_key");
                                                   if (var17 != null) {
                                                      var13 = this.this$0;
                                                      var16 = this.list;
                                                      var2 = var13.mCanbusInfoInt[4] >> 6 & 3;
                                                      if (var2 != 1) {
                                                         if (var2 != 2) {
                                                            if (var2 != 3) {
                                                               var14 = "invalid";
                                                            } else {
                                                               var14 = "success";
                                                            }
                                                         } else {
                                                            var14 = "wait";
                                                         }
                                                      } else {
                                                         var14 = "failed";
                                                      }

                                                      var12 = var17.setValue(var14);
                                                      if (var3 == 1 && var1 == 1) {
                                                         var4 = true;
                                                      } else {
                                                         var4 = false;
                                                      }

                                                      var16.add(var12.setEnable(var4));
                                                   }

                                                   var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_307_value_26");
                                                   if (var15 != null) {
                                                      var19 = this.list;
                                                      if (var3 == 1) {
                                                         var4 = var5;
                                                      } else {
                                                         var4 = false;
                                                      }

                                                      var19.add(var15.setEnable(var4));
                                                   }
                                             }
                                          }
                                       }
                                    } else {
                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("maximum_charging_current");
                                       if (var12 != null) {
                                          var7 = this.this$0;
                                          this.list.add(var12.setValue(var7.mCanbusInfoInt[3] & 3));
                                       }

                                       var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vehicle_tem_in");
                                       if (var12 != null) {
                                          label806: {
                                             var7 = this.this$0;
                                             var1 = var7.mCanbusInfoInt[4];
                                             if (var1 != 0) {
                                                if (var1 == 255) {
                                                   var1 = 29;
                                                   break label806;
                                                }

                                                boolean var11;
                                                if (60 <= var1 && var1 < 196) {
                                                   var11 = true;
                                                } else {
                                                   var11 = false;
                                                }

                                                if (var11) {
                                                   var1 = (var7.mCanbusInfoInt[4] + 100) / 5 - 31;
                                                   break label806;
                                                }
                                             }

                                             var1 = 0;
                                          }

                                          var12.setProgress(var1);
                                          var12.setValue(var12.getProgress());
                                          this.list.add(var12);
                                       }

                                       var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("air_conditioning_is_powered_by_battery");
                                       if (var15 != null) {
                                          var13 = this.this$0;
                                          this.list.add(var15.setValue(var13.mCanbusInfoInt[5] >> 7 & 1));
                                       }

                                       var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("battery_charge_lower_limit");
                                       if (var15 != null) {
                                          var13 = this.this$0;
                                          this.list.add(var15.setProgress(var13.mCanbusInfoInt[6] / 10).setValue(var15.getProgress()));
                                       }
                                    }
                                 } else {
                                    var1 = this.this$0.mCanbusInfoInt[3] & 1;
                                    var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_2_0");
                                    if (var12 != null) {
                                       this.list.add(var12.setValue(var1));
                                    }

                                    var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_6");
                                    if (var15 != null) {
                                       var13 = this.this$0;
                                       this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 1 & 1));
                                    }

                                    var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_lane_assisit");
                                    if (var17 != null) {
                                       var7 = this.this$0;
                                       var19 = this.list;
                                       var15 = var17.setValue(var7.mCanbusInfoInt[4] & 1);
                                       if (var1 == 1) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var19.add(var15.setEnable(var4));
                                    }

                                    var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_8");
                                    if (var12 != null) {
                                       var7 = this.this$0;
                                       this.list.add(var12.setValue(var7.mCanbusInfoInt[4] >> 1 & 1));
                                    }

                                    var1 = this.this$0.mCanbusInfoInt[3] >> 7 & 1;
                                    if (this.isShowDriveAssist != var1) {
                                       this.isShowDriveAssist = var1;
                                       if (var1 == 1) {
                                          var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_2_0");
                                          if (var12 != null) {
                                             this.this$0.startSettingActivity(this.$context, var12.getLeftListIndex(), var12.getRightListIndex());
                                             var18 = Unit.INSTANCE;
                                             var18 = Unit.INSTANCE;
                                          }
                                       } else if (this.this$0.getActivity() instanceof SettingActivity && this.this$0.isActivityFront()) {
                                          this.this$0.finishActivity();
                                       }
                                    }
                                 }
                              } else {
                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_volume");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 4 & 15));
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_temperature");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 2 & 1));
                                 }

                                 var1 = this.this$0.mCanbusInfoInt[3] >> 1 & 1;
                                 SpeedAlertHelper.INSTANCE.setUnit(this.$context, var1);
                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_speed");
                                 if (var12 != null) {
                                    this.list.add(var12.setValue(var1));
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_20h_p2");
                                 if (var12 != null) {
                                    var12.setValue(SpeedAlertHelper.INSTANCE.getValue());
                                    var12.setProgress(SpeedAlertHelper.INSTANCE.getProgress());
                                    this.list.add(var12);
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_distance");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[3] & 1));
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_consumption");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[4] & 15));
                                 }

                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_pressure");
                                 if (var12 != null) {
                                    var7 = this.this$0;
                                    this.list.add(var12.setValue(var7.mCanbusInfoInt[4] >> 4 & 15));
                                 }

                                 var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_power_consumption");
                                 if (var15 != null) {
                                    var13 = this.this$0;
                                    this.list.add(var15.setValue(var13.mCanbusInfoInt[5] & 3));
                                 }
                              }
                           } else {
                              var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_70h_p1_b30");
                              if (var12 != null) {
                                 var7 = this.this$0;
                                 this.list.add(var12.setValue(var7.mCanbusInfoInt[3] & 15));
                              }

                              var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_door_unlocking");
                              if (var15 != null) {
                                 var13 = this.this$0;
                                 this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 4 & 15));
                              }

                              var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_auto_locking");
                              if (var12 != null) {
                                 var7 = this.this$0;
                                 this.list.add(var12.setValue(var7.mCanbusInfoInt[4] & 1));
                              }

                              var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("inductive_trunk_lid");
                              if (var12 != null) {
                                 var7 = this.this$0;
                                 this.list.add(var12.setValue(var7.mCanbusInfoInt[4] >> 1 & 1));
                              }

                              var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_11");
                              if (var12 != null) {
                                 var7 = this.this$0;
                                 this.list.add(var12.setValue(var7.mCanbusInfoInt[4] >> 2 & 1));
                              }

                              var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_12");
                              if (var15 != null) {
                                 var13 = this.this$0;
                                 this.list.add(var15.setValue(var13.mCanbusInfoInt[4] >> 3 & 1));
                              }

                              var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_13");
                              if (var15 != null) {
                                 var13 = this.this$0;
                                 this.list.add(var15.setValue(var13.mCanbusInfoInt[4] >> 4 & 1));
                              }

                              var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_9_5");
                              if (var12 != null) {
                                 var7 = this.this$0;
                                 this.list.add(var12.setValue(var7.mCanbusInfoInt[4] >> 5 & 1));
                              }

                              var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_9_6");
                              if (var15 != null) {
                                 var13 = this.this$0;
                                 this.list.add(var15.setValue(var13.mCanbusInfoInt[4] >> 6 & 1));
                              }
                           }
                        } else {
                           var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active_auto");
                           if (var15 != null) {
                              var8 = this.this$0;
                              this.list.add(var15.setValue(var8.mCanbusInfoInt[3] & 1));
                           }

                           var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active");
                           if (var17 != null) {
                              var9 = this.this$0;
                              var16 = this.list;
                              if ((var9.mCanbusInfoInt[3] >> 1 & 1) != 1) {
                                 var14 = "english_off";
                              }

                              var16.add(var17.setValue(var14));
                           }

                           var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("parking_brake_function");
                           if (var12 != null) {
                              var7 = this.this$0;
                              this.list.add(var12.setValue(var7.mCanbusInfoInt[3] >> 2 & 1));
                           }

                           var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("driving_out_of_the_parking_space");
                           if (var15 != null) {
                              var13 = this.this$0;
                              this.list.add(var15.setValue(var13.mCanbusInfoInt[3] >> 3 & 1));
                           }

                           var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_volume");
                           if (var12 != null) {
                              var7 = this.this$0;
                              var12.setValue(var7.mCanbusInfoInt[4] & 15);
                              var12.setProgress((var7.mCanbusInfoInt[4] & 15) - 1);
                              this.list.add(var12);
                           }

                           var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_tone_setting");
                           if (var12 != null) {
                              var7 = this.this$0;
                              var12.setValue(var7.mCanbusInfoInt[4] >> 4 & 15);
                              var12.setProgress((var7.mCanbusInfoInt[4] >> 4 & 15) - 1);
                              this.list.add(var12);
                           }

                           var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_volume");
                           if (var12 != null) {
                              var7 = this.this$0;
                              var12.setValue(var7.mCanbusInfoInt[5] & 15);
                              var12.setProgress((var7.mCanbusInfoInt[5] & 15) - 1);
                              this.list.add(var12);
                           }

                           var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_tone_setting");
                           if (var15 != null) {
                              var13 = this.this$0;
                              var15.setValue(var13.mCanbusInfoInt[5] >> 4 & 15);
                              var15.setProgress((var13.mCanbusInfoInt[5] >> 4 & 15) - 1);
                              this.list.add(var15);
                           }

                           var1 = this.this$0.mCanbusInfoInt[3] >> 7 & 1;
                           if (this.isShowParkSetting != var1) {
                              this.isShowParkSetting = var1;
                              if (var1 == 1) {
                                 var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active_auto");
                                 if (var12 != null) {
                                    this.this$0.startSettingActivity(this.$context, var12.getLeftListIndex(), var12.getRightListIndex());
                                    var18 = Unit.INSTANCE;
                                    var18 = Unit.INSTANCE;
                                 }
                              } else if (this.this$0.getActivity() instanceof SettingActivity && this.this$0.isActivityFront()) {
                                 this.this$0.finishActivity();
                              }
                           }

                           if (this.m0x40Param4 != this.this$0.mCanbusInfoInt[6]) {
                              this.m0x40Param4 = this.this$0.mCanbusInfoInt[6];
                              var1 = this.this$0.mCanbusInfoInt[6] & 15;
                              var2 = this.this$0.mCanbusInfoInt[6] >> 4 & 7;
                              var16 = new ArrayList();
                              if (var1 == 0) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(5, var4));
                              if (var1 == 1) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(6, var4));
                              if (var1 == 2) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(7, var4));
                              if (var1 == 3) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(8, var4));
                              if (var1 == 4) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(9, var4));
                              if (var1 == 5) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(10, var4));
                              if (var1 == 6) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(11, var4));
                              if (var2 == 0) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(1, var4));
                              if (var2 == 1) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(2, var4));
                              if (var2 == 3) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(3, var4));
                              if (var2 == 2) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var16.add(new PanoramicBtnUpdateEntity(4, var4));
                              var18 = Unit.INSTANCE;
                              GeneralParkData.dataList = (List)var16;
                              this.this$0.updateParkUi((Bundle)null, this.$context);
                           }
                        }
                     }
                  } else {
                     var12 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_0");
                     if (var12 != null) {
                        var7 = this.this$0;
                        this.list.add(var12.setValue(var7.mCanbusInfoInt[3]));
                     }

                     var15 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_10h_p2_b7");
                     if (var15 != null) {
                        var13 = this.this$0;
                        this.list.add(var15.setValue(var13.mCanbusInfoInt[4] >> 7 & 1));
                     }
                  }
               }

               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var3.put(65, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               var1 = this.this$0.mCanbusInfoInt[2];
               boolean var12 = false;
               boolean var3 = false;
               boolean var5 = false;
               boolean var8 = false;
               boolean var13 = false;
               boolean var9 = false;
               boolean var4 = false;
               boolean var10 = false;
               boolean var6 = false;
               boolean var7 = false;
               boolean var11 = false;
               boolean var14 = false;
               boolean var2 = false;
               MsgMgr var19;
               SettingUpdateEntity var21;
               ArrayList var23;
               if (var1 != 16) {
                  ArrayList var15;
                  MsgMgr var16;
                  SettingUpdateEntity var17;
                  SettingUpdateEntity var18;
                  ArrayList var20;
                  MsgMgr var22;
                  if (var1 != 32) {
                     if (var1 != 64) {
                        if (var1 != 96) {
                           if (var1 != 112) {
                              if (var1 != 128) {
                                 if (var1 != 144) {
                                    if (var1 != 48) {
                                       if (var1 != 49) {
                                          if (var1 != 80) {
                                             if (var1 != 81) {
                                                if (var1 != 176) {
                                                   if (var1 == 177) {
                                                      var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("individual");
                                                      if (var17 != null) {
                                                         var16 = this.this$0;
                                                         var15 = this.list;
                                                         if ((var16.mCanbusInfoInt[3] >> 7 & 1) == 1) {
                                                            var2 = true;
                                                         }

                                                         var15.add(var17.setEnable(var2));
                                                      }
                                                   }
                                                } else {
                                                   var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("seat_remote_key_memory_matching");
                                                   if (var18 != null) {
                                                      var22 = this.this$0;
                                                      var20 = this.list;
                                                      if ((var22.mCanbusInfoInt[3] & 1) == 1) {
                                                         var2 = true;
                                                      } else {
                                                         var2 = false;
                                                      }

                                                      var20.add(var18.setEnable(var2));
                                                   }

                                                   var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("driver_seat");
                                                   if (var17 != null) {
                                                      var16 = this.this$0;
                                                      var15 = this.list;
                                                      if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                                         var2 = true;
                                                      } else {
                                                         var2 = false;
                                                      }

                                                      var15.add(var17.setEnable(var2));
                                                   }

                                                   var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_14_1");
                                                   if (var21 != null) {
                                                      var22 = this.this$0;
                                                      var15 = this.list;
                                                      var2 = var12;
                                                      if ((var22.mCanbusInfoInt[3] & 3) != 0) {
                                                         var2 = true;
                                                      }

                                                      var15.add(var21.setEnable(var2));
                                                   }
                                                }
                                             } else {
                                                var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_travel_mode");
                                                if (var17 != null) {
                                                   var19 = this.this$0;
                                                   var20 = this.list;
                                                   if ((var19.mCanbusInfoInt[3] & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var20.add(var17.setEnable(var2));
                                                }

                                                var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_door_ambient_light");
                                                if (var17 != null) {
                                                   var16 = this.this$0;
                                                   var15 = this.list;
                                                   if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var15.add(var17.setEnable(var2));
                                                }

                                                var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_footwell_light");
                                                if (var17 != null) {
                                                   var16 = this.this$0;
                                                   var15 = this.list;
                                                   if ((var16.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var15.add(var17.setEnable(var2));
                                                }

                                                var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_roof_light");
                                                if (var21 != null) {
                                                   var19 = this.this$0;
                                                   var23 = this.list;
                                                   if ((var19.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var23.add(var21.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_front_light");
                                                if (var18 != null) {
                                                   var16 = this.this$0;
                                                   var23 = this.list;
                                                   if ((var16.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var23.add(var18.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_all_area_light");
                                                if (var18 != null) {
                                                   var22 = this.this$0;
                                                   var20 = this.list;
                                                   if ((var22.mCanbusInfoInt[3] >> 5 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var20.add(var18.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_assist");
                                                if (var18 != null) {
                                                   var22 = this.this$0;
                                                   var20 = this.list;
                                                   if ((var22.mCanbusInfoInt[3] >> 6 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var20.add(var18.setEnable(var2));
                                                }

                                                var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_follow");
                                                if (var21 != null) {
                                                   var22 = this.this$0;
                                                   var15 = this.list;
                                                   if ((var22.mCanbusInfoInt[3] >> 7 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var15.add(var21.setEnable(var2));
                                                }

                                                var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_11");
                                                if (var17 != null) {
                                                   var16 = this.this$0;
                                                   var15 = this.list;
                                                   if ((var16.mCanbusInfoInt[4] & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var15.add(var17.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_12");
                                                if (var18 != null) {
                                                   var22 = this.this$0;
                                                   var20 = this.list;
                                                   if ((var22.mCanbusInfoInt[4] >> 1 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var20.add(var18.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_13");
                                                if (var18 != null) {
                                                   var16 = this.this$0;
                                                   var23 = this.list;
                                                   if ((var16.mCanbusInfoInt[4] >> 2 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var23.add(var18.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_handle_box_light");
                                                if (var18 != null) {
                                                   var16 = this.this$0;
                                                   var23 = this.list;
                                                   if ((var16.mCanbusInfoInt[4] >> 3 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var23.add(var18.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("two_color_sync");
                                                if (var18 != null) {
                                                   var22 = this.this$0;
                                                   var20 = this.list;
                                                   if ((var22.mCanbusInfoInt[4] >> 4 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var20.add(var18.setEnable(var2));
                                                }

                                                var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("first_color");
                                                if (var21 != null) {
                                                   var19 = this.this$0;
                                                   var23 = this.list;
                                                   if ((var19.mCanbusInfoInt[4] >> 5 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var23.add(var21.setEnable(var2));
                                                }

                                                var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("second_color");
                                                if (var18 != null) {
                                                   var16 = this.this$0;
                                                   var23 = this.list;
                                                   if ((var16.mCanbusInfoInt[4] >> 6 & 1) == 1) {
                                                      var2 = true;
                                                   } else {
                                                      var2 = false;
                                                   }

                                                   var23.add(var18.setEnable(var2));
                                                }

                                                var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("on_off_btn_txt_7");
                                                if (var21 != null) {
                                                   var22 = this.this$0;
                                                   var15 = this.list;
                                                   var2 = var3;
                                                   if ((var22.mCanbusInfoInt[4] >> 7 & 1) == 1) {
                                                      var2 = true;
                                                   }

                                                   var15.add(var21.setEnable(var2));
                                                }
                                             }
                                          } else {
                                             var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_switch_on_time");
                                             if (var21 != null) {
                                                var22 = this.this$0;
                                                var15 = this.list;
                                                if ((var22.mCanbusInfoInt[3] & 1) == 1) {
                                                   var2 = true;
                                                } else {
                                                   var2 = false;
                                                }

                                                var15.add(var21.setEnable(var2));
                                             }

                                             var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_auto_control");
                                             if (var17 != null) {
                                                var16 = this.this$0;
                                                var15 = this.list;
                                                if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                                   var2 = true;
                                                } else {
                                                   var2 = false;
                                                }

                                                var15.add(var17.setEnable(var2));
                                             }

                                             var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_lane_change_flash");
                                             if (var18 != null) {
                                                var22 = this.this$0;
                                                var20 = this.list;
                                                if ((var22.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                                   var2 = true;
                                                } else {
                                                   var2 = false;
                                                }

                                                var20.add(var18.setEnable(var2));
                                             }

                                             var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_ins_swi_lighting");
                                             if (var21 != null) {
                                                var22 = this.this$0;
                                                var15 = this.list;
                                                if ((var22.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                                                   var2 = true;
                                                } else {
                                                   var2 = false;
                                                }

                                                var15.add(var21.setEnable(var2));
                                             }

                                             var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_coming_home");
                                             if (var17 != null) {
                                                var16 = this.this$0;
                                                var15 = this.list;
                                                if ((var16.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                                                   var2 = true;
                                                } else {
                                                   var2 = false;
                                                }

                                                var15.add(var17.setEnable(var2));
                                             }

                                             var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_leaving_home");
                                             if (var21 != null) {
                                                var22 = this.this$0;
                                                var15 = this.list;
                                                if ((var22.mCanbusInfoInt[3] >> 5 & 1) == 1) {
                                                   var2 = true;
                                                } else {
                                                   var2 = false;
                                                }

                                                var15.add(var21.setEnable(var2));
                                             }

                                             var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_light_4");
                                             if (var18 != null) {
                                                var22 = this.this$0;
                                                var20 = this.list;
                                                if ((var22.mCanbusInfoInt[3] >> 6 & 1) == 1) {
                                                   var2 = true;
                                                } else {
                                                   var2 = false;
                                                }

                                                var20.add(var18.setEnable(var2));
                                             }

                                             var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("headlight_illumination_distance_adjustment");
                                             if (var18 != null) {
                                                var16 = this.this$0;
                                                var23 = this.list;
                                                var2 = var5;
                                                if ((var16.mCanbusInfoInt[3] >> 7 & 1) == 1) {
                                                   var2 = true;
                                                }

                                                var23.add(var18.setEnable(var2));
                                             }
                                          }
                                       } else {
                                          var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_last_distance_selected");
                                          if (var21 != null) {
                                             var19 = this.this$0;
                                             var23 = this.list;
                                             if ((var19.mCanbusInfoInt[3] & 1) == 1) {
                                                var2 = true;
                                             } else {
                                                var2 = false;
                                             }

                                             var23.add(var21.setEnable(var2));
                                          }

                                          var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_1");
                                          if (var17 != null) {
                                             var16 = this.this$0;
                                             var15 = this.list;
                                             if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                                var2 = true;
                                             } else {
                                                var2 = false;
                                             }

                                             var15.add(var17.setEnable(var2));
                                          }

                                          var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_2");
                                          if (var18 != null) {
                                             var22 = this.this$0;
                                             var20 = this.list;
                                             if ((var22.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                                var2 = true;
                                             } else {
                                                var2 = false;
                                             }

                                             var20.add(var18.setEnable(var2));
                                          }

                                          var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_3");
                                          if (var18 != null) {
                                             var16 = this.this$0;
                                             var23 = this.list;
                                             if ((var16.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                                                var2 = true;
                                             } else {
                                                var2 = false;
                                             }

                                             var23.add(var18.setEnable(var2));
                                          }

                                          var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_acc_drive_program");
                                          if (var21 != null) {
                                             var19 = this.this$0;
                                             var23 = this.list;
                                             if ((var19.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                                                var2 = true;
                                             } else {
                                                var2 = false;
                                             }

                                             var23.add(var21.setEnable(var2));
                                          }

                                          var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_acc_distance");
                                          if (var17 != null) {
                                             var16 = this.this$0;
                                             var15 = this.list;
                                             var2 = var8;
                                             if ((var16.mCanbusInfoInt[3] >> 5 & 1) == 1) {
                                                var2 = true;
                                             }

                                             var15.add(var17.setEnable(var2));
                                          }
                                       }
                                    } else {
                                       var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_2_0");
                                       if (var17 != null) {
                                          var19 = this.this$0;
                                          var20 = this.list;
                                          if ((var19.mCanbusInfoInt[3] & 1) == 1) {
                                             var2 = true;
                                          } else {
                                             var2 = false;
                                          }

                                          var20.add(var17.setEnable(var2));
                                       }

                                       var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_6");
                                       if (var18 != null) {
                                          var16 = this.this$0;
                                          var23 = this.list;
                                          if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                             var2 = true;
                                          } else {
                                             var2 = false;
                                          }

                                          var23.add(var18.setEnable(var2));
                                       }

                                       var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_lane_assisit");
                                       if (var21 != null) {
                                          var19 = this.this$0;
                                          var23 = this.list;
                                          var2 = var13;
                                          if ((var19.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                             var2 = true;
                                          }

                                          var23.add(var21.setEnable(var2));
                                       }
                                    }
                                 } else {
                                    var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_distance");
                                    if (var21 != null) {
                                       var22 = this.this$0;
                                       var15 = this.list;
                                       if ((var22.mCanbusInfoInt[3] & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var15.add(var21.setEnable(var2));
                                    }

                                    var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_speed");
                                    if (var18 != null) {
                                       var16 = this.this$0;
                                       var23 = this.list;
                                       if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var23.add(var18.setEnable(var2));
                                    }

                                    var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_temperature");
                                    if (var21 != null) {
                                       var19 = this.this$0;
                                       var23 = this.list;
                                       if ((var19.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var23.add(var21.setEnable(var2));
                                    }

                                    var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_volume");
                                    if (var18 != null) {
                                       var16 = this.this$0;
                                       var23 = this.list;
                                       if ((var16.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var23.add(var18.setEnable(var2));
                                    }

                                    var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_consumption");
                                    if (var21 != null) {
                                       var22 = this.this$0;
                                       var15 = this.list;
                                       if ((var22.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var15.add(var21.setEnable(var2));
                                    }

                                    var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_pressure");
                                    if (var17 != null) {
                                       var16 = this.this$0;
                                       var15 = this.list;
                                       var2 = var9;
                                       if ((var16.mCanbusInfoInt[3] >> 5 & 1) == 1) {
                                          var2 = true;
                                       }

                                       var15.add(var17.setEnable(var2));
                                    }
                                 }
                              } else {
                                 var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_current_consumption");
                                 if (var18 != null) {
                                    var22 = this.this$0;
                                    var20 = this.list;
                                    if ((var22.mCanbusInfoInt[3] & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var20.add(var18.setEnable(var2));
                                 }

                                 var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_consumption");
                                 if (var21 != null) {
                                    var22 = this.this$0;
                                    var15 = this.list;
                                    if ((var22.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var15.add(var21.setEnable(var2));
                                 }

                                 var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_con_consumers");
                                 if (var18 != null) {
                                    var22 = this.this$0;
                                    var20 = this.list;
                                    if ((var22.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var20.add(var18.setEnable(var2));
                                 }

                                 var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_eco_tips");
                                 if (var17 != null) {
                                    var16 = this.this$0;
                                    var15 = this.list;
                                    if ((var16.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var15.add(var17.setEnable(var2));
                                 }

                                 var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_travelling_time");
                                 if (var18 != null) {
                                    var16 = this.this$0;
                                    var23 = this.list;
                                    if ((var16.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var23.add(var18.setEnable(var2));
                                 }

                                 var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_distance_travelled");
                                 if (var17 != null) {
                                    var19 = this.this$0;
                                    var20 = this.list;
                                    if ((var19.mCanbusInfoInt[3] >> 5 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var20.add(var17.setEnable(var2));
                                 }

                                 var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_speed");
                                 if (var18 != null) {
                                    var22 = this.this$0;
                                    var20 = this.list;
                                    if ((var22.mCanbusInfoInt[3] >> 6 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var20.add(var18.setEnable(var2));
                                 }

                                 var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_digital_speed_display");
                                 if (var18 != null) {
                                    var22 = this.this$0;
                                    var20 = this.list;
                                    if ((var22.mCanbusInfoInt[3] >> 7 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var20.add(var18.setEnable(var2));
                                 }

                                 var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_speed_warning");
                                 if (var21 != null) {
                                    var19 = this.this$0;
                                    var23 = this.list;
                                    if ((var19.mCanbusInfoInt[4] & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var23.add(var21.setEnable(var2));
                                 }

                                 var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_oil_temperature");
                                 if (var21 != null) {
                                    var22 = this.this$0;
                                    var15 = this.list;
                                    var2 = var4;
                                    if ((var22.mCanbusInfoInt[4] >> 1 & 1) == 1) {
                                       var2 = true;
                                    }

                                    var15.add(var21.setEnable(var2));
                                 }
                              }
                           } else {
                              var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_70h_p1_b30");
                              if (var21 != null) {
                                 var22 = this.this$0;
                                 var15 = this.list;
                                 if ((var22.mCanbusInfoInt[3] & 1) == 1) {
                                    var2 = true;
                                 } else {
                                    var2 = false;
                                 }

                                 var15.add(var21.setEnable(var2));
                              }

                              var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_door_unlocking");
                              if (var18 != null) {
                                 var16 = this.this$0;
                                 var23 = this.list;
                                 if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                    var2 = true;
                                 } else {
                                    var2 = false;
                                 }

                                 var23.add(var18.setEnable(var2));
                              }

                              var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_auto_locking");
                              if (var17 != null) {
                                 var16 = this.this$0;
                                 var15 = this.list;
                                 if ((var16.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                    var2 = true;
                                 } else {
                                    var2 = false;
                                 }

                                 var15.add(var17.setEnable(var2));
                              }

                              var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("inductive_trunk_lid");
                              if (var17 != null) {
                                 var16 = this.this$0;
                                 var15 = this.list;
                                 var2 = var10;
                                 if ((var16.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                                    var2 = true;
                                 }

                                 var15.add(var17.setEnable(var2));
                              }
                           }
                        } else {
                           var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_syncchronous_adjustment");
                           if (var17 != null) {
                              var19 = this.this$0;
                              var20 = this.list;
                              if ((var19.mCanbusInfoInt[3] & 1) == 1) {
                                 var2 = true;
                              } else {
                                 var2 = false;
                              }

                              var20.add(var17.setEnable(var2));
                           }

                           var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_settings_lower_while_reversing");
                           if (var21 != null) {
                              var19 = this.this$0;
                              var23 = this.list;
                              if ((var19.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                                 var2 = true;
                              } else {
                                 var2 = false;
                              }

                              var23.add(var21.setEnable(var2));
                           }

                           var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_auto_wiping");
                           if (var18 != null) {
                              var16 = this.this$0;
                              var23 = this.list;
                              if ((var16.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                                 var2 = true;
                              } else {
                                 var2 = false;
                              }

                              var23.add(var18.setEnable(var2));
                           }

                           var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_rear_window_wiping");
                           if (var18 != null) {
                              var16 = this.this$0;
                              var23 = this.list;
                              if ((var16.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                                 var2 = true;
                              } else {
                                 var2 = false;
                              }

                              var23.add(var18.setEnable(var2));
                           }

                           var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_60h_p2_b0");
                           if (var18 != null) {
                              var22 = this.this$0;
                              var20 = this.list;
                              if ((var22.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                                 var2 = true;
                              } else {
                                 var2 = false;
                              }

                              var20.add(var18.setEnable(var2));
                           }

                           var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_service_wiper_in_maintenance_position");
                           if (var18 != null) {
                              var16 = this.this$0;
                              var23 = this.list;
                              var2 = var6;
                              if ((var16.mCanbusInfoInt[3] >> 5 & 1) == 1) {
                                 var2 = true;
                              }

                              var23.add(var18.setEnable(var2));
                           }
                        }
                     } else {
                        var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active_auto");
                        if (var21 != null) {
                           var19 = this.this$0;
                           var23 = this.list;
                           if ((var19.mCanbusInfoInt[3] & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var23.add(var21.setEnable(var2));
                        }

                        var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_volume");
                        if (var18 != null) {
                           var16 = this.this$0;
                           var23 = this.list;
                           if ((var16.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var23.add(var18.setEnable(var2));
                        }

                        var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_tone_setting");
                        if (var21 != null) {
                           var22 = this.this$0;
                           var15 = this.list;
                           if ((var22.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var15.add(var21.setEnable(var2));
                        }

                        var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_volume");
                        if (var17 != null) {
                           var19 = this.this$0;
                           var20 = this.list;
                           if ((var19.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var20.add(var17.setEnable(var2));
                        }

                        var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_tone_setting");
                        if (var17 != null) {
                           var16 = this.this$0;
                           var15 = this.list;
                           if ((var16.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var15.add(var17.setEnable(var2));
                        }

                        var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active");
                        if (var17 != null) {
                           var19 = this.this$0;
                           var20 = this.list;
                           var2 = var7;
                           if ((var19.mCanbusInfoInt[3] >> 6 & 1) == 1) {
                              var2 = true;
                           }

                           var20.add(var17.setEnable(var2));
                        }
                     }
                  } else {
                     var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("tpms_set");
                     if (var17 != null) {
                        var16 = this.this$0;
                        var15 = this.list;
                        if ((var16.mCanbusInfoInt[3] & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var15.add(var17.setEnable(var2));
                     }

                     var18 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_20h_p1_b0");
                     if (var18 != null) {
                        var22 = this.this$0;
                        var20 = this.list;
                        if ((var22.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var20.add(var18.setEnable(var2));
                     }

                     var17 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_20h_p2");
                     if (var17 != null) {
                        var19 = this.this$0;
                        var20 = this.list;
                        var2 = var11;
                        if ((var19.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                           var2 = true;
                        }

                        var20.add(var17.setEnable(var2));
                     }
                  }
               } else {
                  var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_2_setting_0");
                  if (var21 != null) {
                     var19 = this.this$0;
                     var23 = this.list;
                     if ((var19.mCanbusInfoInt[3] & 1) == 1) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     var23.add(var21.setEnable(var2));
                  }

                  var21 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_3_40h_10h_p2_b7");
                  if (var21 != null) {
                     var19 = this.this$0;
                     var23 = this.list;
                     var2 = var14;
                     if ((var19.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                        var2 = true;
                     }

                     var23.add(var21.setEnable(var2));
                  }
               }

               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var3.put(80, new Parser(this, var1) {
         final Context $context;
         private final DecimalFormat df_2Integer;
         private final ArrayList list;
         private final Function1 parse0x20;
         private final Function1 parse0x30;
         private final Function1 parse0x40;
         private final Function1 parse0x50;
         private final Function1 parse0x70;
         final MsgMgr this$0;
         private final Function0 unit0x10;
         private int value0x10;
         private int value0x11;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
            this.unit0x10 = (Function0)(new Function0(var1) {
               final MsgMgr this$0;

               {
                  this.this$0 = var1;
               }

               public final String invoke() {
                  String var1;
                  if ((this.this$0.mCanbusInfoInt[3] & 1) == 1) {
                     var1 = "mi";
                  } else {
                     var1 = "km";
                  }

                  return var1;
               }
            });
            this.parse0x20 = (Function1)(new Function1(this, var1) {
               final <undefinedtype> this$0;
               final MsgMgr this$1;

               {
                  this.this$0 = var1;
                  this.this$1 = var2;
               }

               public final void invoke(DriverUpdateEntity var1) {
                  Intrinsics.checkNotNullParameter(var1, "$this$null");
                  ArrayList var3 = this.this$0.list;
                  StringBuilder var4 = new StringBuilder();
                  MsgMgr var2 = this.this$1;
                  var3.add(var1.setValue(var4.append((float)var2.mergeValue(var2.mCanbusInfoInt[4], this.this$1.mCanbusInfoInt[5], this.this$1.mCanbusInfoInt[6], this.this$1.mCanbusInfoInt[7]) / 10.0F).append(' ').append((String)this.this$0.unit0x10.invoke()).toString()));
               }
            });
            this.parse0x30 = (Function1)(new Function1(var1, this) {
               final MsgMgr this$0;
               final <undefinedtype> this$1;

               {
                  this.this$0 = var1;
                  this.this$1 = var2;
               }

               public final void invoke(DriverUpdateEntity var1) {
                  Intrinsics.checkNotNullParameter(var1, "$this$null");
                  MsgMgr var7 = this.this$0;
                  int var2 = var7.mergeValue(var7.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5], this.this$0.mCanbusInfoInt[6], this.this$0.mCanbusInfoInt[7]);
                  String var8 = "";
                  String var11;
                  switch (var2) {
                     case 65534:
                     case 65535:
                        var11 = "--";
                        break;
                     default:
                        var11 = String.valueOf((float)var2 / 10.0F);
                        int var6 = var11.length();
                        var2 = 0;
                        int var3 = 0;

                        while(true) {
                           if (var2 >= var6) {
                              var11 = "";
                              break;
                           }

                           int var4 = var11.charAt(var2);
                           boolean var5;
                           if (48 <= var4 && var4 < 58) {
                              var5 = true;
                           } else {
                              var5 = false;
                           }

                           var4 = var3;
                           if (var5) {
                              var4 = var3 + 1;
                           }

                           if (var4 == 3 || var2 == var11.length() - 1) {
                              var11 = var11.substring(0, var2 + 1);
                              Intrinsics.checkNotNullExpressionValue(var11, "this as java.lang.String…ing(startIndex, endIndex)");
                              break;
                           }

                           ++var2;
                           var3 = var4;
                        }
                  }

                  ArrayList var9 = this.this$1.list;
                  StringBuilder var10 = (new StringBuilder()).append(var11).append(' ');
                  var2 = this.this$0.mCanbusInfoInt[3];
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2 && var2 != 3) {
                           if (var2 != 4) {
                              switch (var2) {
                                 case 18:
                                    var11 = "kWh/100km";
                                    break;
                                 case 19:
                                    var11 = "km/kWh";
                                    break;
                                 case 20:
                                    var11 = "kWh/mi";
                                    break;
                                 case 21:
                                    var11 = "mi/kWh";
                                    break;
                                 default:
                                    var11 = var8;
                              }
                           } else {
                              var11 = "l/h";
                           }
                        } else {
                           var11 = "mpg";
                        }
                     } else {
                        var11 = "km/L";
                     }
                  } else {
                     var11 = "L/100km";
                  }

                  var9.add(var1.setValue(var10.append(var11).toString()));
               }
            });
            this.df_2Integer = new DecimalFormat("00");
            this.parse0x40 = (Function1)(new Function1(this, var1) {
               final <undefinedtype> this$0;
               final MsgMgr this$1;

               {
                  this.this$0 = var1;
                  this.this$1 = var2;
               }

               public final void invoke(DriverUpdateEntity var1) {
                  Intrinsics.checkNotNullParameter(var1, "$this$null");
                  ArrayList var2 = this.this$0.list;
                  StringBuilder var3 = new StringBuilder();
                  MsgMgr var4 = this.this$1;
                  var3 = var3.append((float)var4.mergeValue(var4.mCanbusInfoInt[4], this.this$1.mCanbusInfoInt[5]) / 10.0F).append(' ');
                  if ((this.this$1.mCanbusInfoInt[3] & 1) == 1) {
                     var4 = this.this$1;
                     String var5 = this.this$0.getDf_2Integer().format((double)((this.this$1.mCanbusInfoInt[4] + this.this$1.mCanbusInfoInt[5] * 256) / 10) * 1.6);
                     Intrinsics.checkNotNullExpressionValue(var5, "df_2Integer.format((mCan…InfoInt[5]*256)/10 * 1.6)");
                     var4.updateSpeedInfo(Integer.parseInt(var5));
                  } else {
                     MsgMgr var7 = this.this$1;
                     String var6 = this.this$0.getDf_2Integer().format((this.this$1.mCanbusInfoInt[4] + this.this$1.mCanbusInfoInt[5] * 256) / 10);
                     Intrinsics.checkNotNullExpressionValue(var6, "df_2Integer.format((mCan…CanbusInfoInt[5]*256)/10)");
                     var7.updateSpeedInfo(Integer.parseInt(var6));
                  }

                  var2.add(var1.setValue(var3.append(Unit.INSTANCE).toString()));
               }
            });
            this.parse0x50 = (Function1)(new Function1(var1, this) {
               final MsgMgr this$0;
               final <undefinedtype> this$1;

               {
                  this.this$0 = var1;
                  this.this$1 = var2;
               }

               public final void invoke(DriverUpdateEntity var1) {
                  Intrinsics.checkNotNullParameter(var1, "$this$null");
                  MsgMgr var3 = this.this$0;
                  int var2 = var3.mergeValue(var3.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5]);
                  ArrayList var6 = this.this$1.list;
                  StringBuilder var4 = (new StringBuilder()).append(var2 / 60).append(" : ");
                  StringCompanionObject var5 = StringCompanionObject.INSTANCE;
                  String var7 = String.format("%02d", Arrays.copyOf(new Object[]{var2 % 60}, 1));
                  Intrinsics.checkNotNullExpressionValue(var7, "format(format, *args)");
                  var6.add(var1.setValue(var4.append(var7).toString()));
               }
            });
            this.parse0x70 = (Function1)(new Function1(this, var1) {
               final <undefinedtype> this$0;
               final MsgMgr this$1;

               {
                  this.this$0 = var1;
                  this.this$1 = var2;
               }

               public final void invoke(DriverUpdateEntity var1) {
                  Intrinsics.checkNotNullParameter(var1, "$this$null");
                  ArrayList var3 = this.this$0.list;
                  StringBuilder var4 = new StringBuilder();
                  MsgMgr var2 = this.this$1;
                  var4 = var4.append((float)((short)var2.mergeValue(var2.mCanbusInfoInt[4], this.this$1.mCanbusInfoInt[5])) / 10.0F).append(' ');
                  String var5;
                  if ((this.this$1.mCanbusInfoInt[3] & 1) == 1) {
                     var5 = "km/kwh";
                  } else {
                     var5 = "kwh/100km";
                  }

                  var3.add(var1.setValue(var4.append(var5).toString()));
               }
            });
         }

         public final DecimalFormat getDf_2Integer() {
            return this.df_2Integer;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               MsgMgr var2;
               StringBuilder var5;
               DriverUpdateEntity var6;
               String var7;
               DriverUpdateEntity var9;
               DriverUpdateEntity var10;
               ArrayList var11;
               switch (this.this$0.mCanbusInfoInt[2]) {
                  case 16:
                     var9 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_10h");
                     if (var9 != null) {
                        var2 = this.this$0;
                        this.value0x10 = var2.mergeValue(var2.mCanbusInfoInt[4], var2.mCanbusInfoInt[5]);
                        this.list.add(var9.setValue("" + this.value0x10 + ' ' + (String)this.unit0x10.invoke()));
                     }
                     break;
                  case 17:
                     var9 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_11h");
                     if (var9 != null) {
                        var2 = this.this$0;
                        this.value0x11 = var2.mergeValue(var2.mCanbusInfoInt[4], var2.mCanbusInfoInt[5]);
                        this.list.add(var9.setValue("" + this.value0x11 + ' ' + (String)this.unit0x10.invoke()));
                     }
                     break;
                  case 18:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_12h");
                     if (var10 != null) {
                        this.list.add(var10.setValue("" + (this.value0x10 + this.value0x11) + ' ' + (String)this.unit0x10.invoke()));
                     }
                     break;
                  case 32:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_20h");
                     if (var10 != null) {
                        this.parse0x20.invoke(var10);
                     }
                     break;
                  case 33:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_21h");
                     if (var10 != null) {
                        this.parse0x20.invoke(var10);
                     }
                     break;
                  case 34:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_22h");
                     if (var10 != null) {
                        this.parse0x20.invoke(var10);
                     }
                     break;
                  case 48:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_30h");
                     if (var10 != null) {
                        this.parse0x30.invoke(var10);
                     }
                     break;
                  case 49:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_31h");
                     if (var10 != null) {
                        this.parse0x30.invoke(var10);
                     }
                     break;
                  case 50:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_32h");
                     if (var10 != null) {
                        this.parse0x30.invoke(var10);
                     }
                     break;
                  case 64:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_40h");
                     if (var10 != null) {
                        this.parse0x40.invoke(var10);
                     }
                     break;
                  case 65:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_41h");
                     if (var10 != null) {
                        this.parse0x40.invoke(var10);
                     }
                     break;
                  case 66:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_42h");
                     if (var10 != null) {
                        this.parse0x40.invoke(var10);
                     }
                     break;
                  case 80:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_50h");
                     if (var10 != null) {
                        this.parse0x50.invoke(var10);
                     }
                     break;
                  case 81:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_51h");
                     if (var10 != null) {
                        this.parse0x50.invoke(var10);
                     }
                     break;
                  case 82:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_52h");
                     if (var10 != null) {
                        this.parse0x50.invoke(var10);
                     }
                     break;
                  case 96:
                     var9 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_2_driver_15");
                     if (var9 != null) {
                        var2 = this.this$0;
                        var11 = this.list;
                        var5 = (new StringBuilder()).append(var2.mergeValue(var2.mCanbusInfoInt[4], var2.mCanbusInfoInt[5])).append(' ');
                        if ((var2.mCanbusInfoInt[3] & 1) == 1) {
                           var7 = "l/h";
                        } else {
                           var7 = "gal/h";
                        }

                        var11.add(var9.setValue(var5.append(var7).toString()));
                     }
                     break;
                  case 97:
                     var6 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_2_driver_16");
                     if (var6 != null) {
                        var2 = this.this$0;
                        var11 = this.list;
                        var5 = (new StringBuilder()).append((float)var2.mergeValue(var2.mCanbusInfoInt[4], var2.mCanbusInfoInt[5]) / 10.0F).append(' ');
                        var1 = var2.mCanbusInfoInt[3] & 3;
                        String var8 = "mpg";
                        if (var1 != 0) {
                           if (var1 != 1) {
                              var7 = var8;
                              if (var1 != 2) {
                                 var7 = var8;
                                 if (var1 != 3) {
                                    var7 = "";
                                 }
                              }
                           } else {
                              var7 = "km/L";
                           }
                        } else {
                           var7 = "L/100km";
                        }

                        var11.add(var6.setValue(var5.append(var7).toString()));
                     }
                     break;
                  case 112:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_70h");
                     if (var10 != null) {
                        this.parse0x70.invoke(var10);
                     }
                     break;
                  case 113:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_71h");
                     if (var10 != null) {
                        this.parse0x70.invoke(var10);
                     }
                     break;
                  case 114:
                     var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_72h");
                     if (var10 != null) {
                        this.parse0x70.invoke(var10);
                     }
                     break;
                  case 128:
                     var6 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_3_50h_80h");
                     if (var6 != null) {
                        var2 = this.this$0;
                        Context var4 = this.$context;
                        ArrayList var3 = this.list;
                        var5 = (new StringBuilder()).append(var2.mCanbusInfoInt[3]).append(' ');
                        var1 = var2.mCanbusInfoInt[4];
                        if (var1 != 1) {
                           if (var1 != 3) {
                              if (var1 != 5) {
                                 if (var1 != 9) {
                                    var7 = "_3_50h_80h_p2_e";
                                 } else {
                                    var7 = "_3_50h_80h_p2_9";
                                 }
                              } else {
                                 var7 = "_3_50h_80h_p2_5";
                              }
                           } else {
                              var7 = "_3_50h_80h_p2_3";
                           }
                        } else {
                           var7 = "_3_50h_80h_p2_1";
                        }

                        var3.add(var6.setValue(var5.append(CommUtil.getStrByResId(var4, var7)).toString()));
                     }
               }

               this.this$0.updateGeneralDriveData((List)this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      var3.put(81, new Parser(this, var1) {
         final Context $context;
         private final int[] amplifierData;
         private final ArrayList list;
         private int mData6;
         private int mData6Record;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$0qj5Xy5rAemcXBwmnA31HVrK6w0(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$QtTUqFMgE5ArLskjdNIN2aWJZvM(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Z0x4QhRiNct__jv0WYfVeCV1erc(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$_wZsI7nBDIeE7g3XR0IiLJ2qU8I(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$tgD66IVRJkzkjSSkQnLBnafSV6E(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$wnmmP7nACXafQQHmHXci_r_Nv5s(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$xqHwXf5nXHaYwCN6iDo_3wRHijs(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
            this.amplifierData = new int[6];
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.volume = var0.mCanbusInfoInt[2];
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandTreble = var0.mCanbusInfoInt[3];
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandMiddle = var0.mCanbusInfoInt[4];
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandBass = var0.mCanbusInfoInt[5];
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.frontRear = var0.mCanbusInfoInt[6] - 9;
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.leftRight = var0.mCanbusInfoInt[7] - 9;
         }

         private static final void setOnParseListeners$lambda_7(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.list.clear();
            var0.mData6 = var1.mCanbusInfoInt[8];
            SettingUpdateEntity var2 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_143_0xAD_setting6");
            if (var2 != null) {
               var0.list.add(var2.setValue(var0.mData6).setProgress(var0.mData6));
            }

         }

         public void parse(int var1) {
            super.parse(var1);
            int[] var4 = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 2, 8);
            MsgMgr var5 = this.this$0;
            Context var3 = this.$context;
            if (!Arrays.equals(var4, this.amplifierData)) {
               ArraysKt.copyInto$default(var4, this.amplifierData, 0, 0, 0, 14, (Object)null);
               var5.updateAmplifierActivity((Bundle)null);
               var5.saveAmplifierData(var3, var5.getCanId());
            }

            int var2 = this.mData6Record;
            var1 = this.mData6;
            if (var2 != var1) {
               this.mData6Record = var1;
               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$18$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$18$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$18$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$18$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$18$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$18$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$18$$ExternalSyntheticLambda6(this, this.this$0)};
         }
      });
      var3.put(82, new Parser(this) {
         private final ArrayList list;
         private int mData45;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[6] << 8 | this.this$0.mCanbusInfoInt[7];
            MsgMgr var3 = this.this$0;
            if (this.mData45 != var1) {
               this.mData45 = var1;
               GeneralHybirdData.energyDirection = var3.mCanbusInfoInt[6] >> 6 & 3;
               boolean var2;
               if ((var3.mCanbusInfoInt[6] >> 5 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralHybirdData.isShowMotor = var2;
               var1 = var3.mCanbusInfoInt[6] >> 2 & 7;
               byte var7;
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           var7 = 0;
                        } else {
                           var7 = 4;
                        }
                     } else {
                        var7 = 2;
                     }
                  } else {
                     var7 = 1;
                  }
               } else {
                  var7 = 3;
               }

               GeneralHybirdData.wheelTrack = var7;
               GeneralHybirdData.powerBatteryLevel = RangesKt.coerceAtMost(var3.mCanbusInfoInt[7] / 10 + 1, 10);
               GeneralHybirdData.powerBatteryValue = var3.mCanbusInfoInt[7];
               GeneralHybirdData.isShowBattery = true;
               if (var3.getActivity() instanceof MqbHybirdActivity) {
                  var3.updateActivity((Bundle)null);
               }
            }

            this.this$0.mCanbusInfoInt[6] = 0;
            this.this$0.mCanbusInfoInt[7] = 0;
            if (this.isDataChange()) {
               this.list.clear();
               DriverUpdateEntity var4 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("mqb_endurance_potential");
               if (var4 != null) {
                  var3 = this.this$0;
                  ArrayList var5 = this.list;
                  StringBuilder var6 = new StringBuilder();
                  var1 = var3.mergeValue(var3.mCanbusInfoInt[3], var3.mCanbusInfoInt[2]) / 10;
                  String var8;
                  if (var1 < 1) {
                     var8 = "< 1";
                  } else {
                     var8 = String.valueOf(var1);
                  }

                  var5.add(var4.setValue(var6.append(var8).append(" km").toString()));
               }

               var4 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("battery_life");
               if (var4 != null) {
                  var3 = this.this$0;
                  this.list.add(var4.setValue(var3.mergeValue(var3.mCanbusInfoInt[5], var3.mCanbusInfoInt[4]) / 10 + " km"));
               }

               var4 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("driven_distance");
               if (var4 != null) {
                  var3 = this.this$0;
                  this.list.add(var4.setValue(var3.mergeValue(var3.mCanbusInfoInt[9], var3.mCanbusInfoInt[8]) / 10 + " km"));
               }

               var4 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_hybrid_zero_emission");
               if (var4 != null) {
                  var3 = this.this$0;
                  this.list.add(var4.setValue(var3.mergeValue(var3.mCanbusInfoInt[11], var3.mCanbusInfoInt[10]) / 10 + " km"));
               }

               DriverUpdateEntity var10 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("proportion");
               if (var10 != null) {
                  MsgMgr var9 = this.this$0;
                  this.list.add(var10.setValue("" + var9.mCanbusInfoInt[12] + '%'));
               }

               this.this$0.updateGeneralDriveData((List)this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      var3.put(83, new Parser(this) {
         private final float barMaxValue;
         private final String fullBar;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
            this.fullBar = "==============================================================";
            this.barMaxValue = 100.0F;
         }

         private final void setEntity(String var1, int var2) {
            int var4 = var2 + 1;
            if (var4 < this.this$0.mCanbusInfoInt.length) {
               DriverUpdateEntity var5 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get(var1);
               if (var5 != null) {
                  MsgMgr var7 = this.this$0;
                  float var3 = (float)var7.mergeValue(var7.mCanbusInfoInt[var2], var7.mCanbusInfoInt[var4]) / 10.0F;
                  ArrayList var6 = this.list;
                  var2 = (int)var3;
                  if (var2 == 0) {
                     var1 = "";
                  } else {
                     var1 = var2 + " kWh";
                  }

                  var6.add(var5.setValue(var1));
               }

            }
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               int var2 = this.this$0.mCanbusInfoInt[2];
               if (var2 != 0) {
                  var1 = 16;
                  if (var2 != 16) {
                     if (var2 == 17) {
                        while(var1 < 31) {
                           this.setEntity(String.valueOf(var1), (var1 - 15) * 2 + 1);
                           ++var1;
                        }
                     }
                  } else {
                     for(var1 = 1; var1 < 16; ++var1) {
                        this.setEntity(String.valueOf(var1), var1 * 2 + 1);
                     }
                  }
               } else {
                  this.setEntity("_3_53h_00", 3);
               }

               this.this$0.updateGeneralDriveData((List)this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      ArrayList var5 = new ArrayList();
      ArrayList var4 = new ArrayList();
      var3.put(96, new Parser(this, var5, var1, var4) {
         final Context $context;
         final ArrayList $m0x60List;
         final ArrayList $m0x62List;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$m0x60List = var2;
            this.$context = var3;
            this.$m0x62List = var4;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.$m0x60List.clear();
               this.$m0x60List.add(new WarningEntity(CommUtil.getStrByResId(this.$context, "_2_information_title_1")));
               String var3;
               ArrayList var7;
               if (this.this$0.mCanbusInfoInt[2] == 0) {
                  var7 = this.$m0x60List;
                  StringBuilder var10 = (new StringBuilder()).append("\t\t");
                  Context var8 = this.$context;
                  if ((this.this$0.mCanbusInfoInt[9] & 1) == 1) {
                     var3 = "vm_golf7_vehicle_status_start_stop_0";
                  } else {
                     var3 = "_3_60h_d7_1";
                  }

                  var7.add(new WarningEntity(var10.append(CommUtil.getStrByResId(var8, var3)).toString()));
               } else {
                  var1 = 0;

                  label60:
                  for(int var2 = RangesKt.coerceAtMost(this.this$0.mCanbusInfoInt[2], 6); var1 < var2; ++var1) {
                     ArrayList var6 = this.$m0x60List;
                     StringBuilder var5 = (new StringBuilder()).append("\t\t");
                     Context var4 = this.$context;
                     switch (this.this$0.mCanbusInfoInt[var1 + 3]) {
                        case 0:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_0";
                           break;
                        case 1:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_1";
                           break;
                        case 2:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_2";
                           break;
                        case 3:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_3";
                           break;
                        case 4:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_4";
                           break;
                        case 5:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_5";
                           break;
                        case 6:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_6";
                           break;
                        case 7:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_7";
                           break;
                        case 8:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_8";
                           break;
                        case 9:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_9";
                           break;
                        case 10:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_10";
                           break;
                        case 11:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_11";
                           break;
                        case 12:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_12";
                           break;
                        case 13:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_13";
                           break;
                        case 14:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_14";
                           break;
                        case 15:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_15";
                           break;
                        case 16:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_16";
                           break;
                        case 17:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_17";
                           break;
                        case 18:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_18";
                           break;
                        case 19:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_19";
                           break;
                        case 20:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_20";
                           break;
                        case 21:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_21";
                           break;
                        case 22:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_22";
                           break;
                        case 23:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_23";
                           break;
                        case 24:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_24";
                           break;
                        case 25:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_25";
                           break;
                        case 26:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_26";
                           break;
                        case 27:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_27";
                           break;
                        case 28:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_28";
                           break;
                        case 29:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_29";
                           break;
                        case 30:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_30";
                           break;
                        case 31:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_31";
                           break;
                        case 32:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_32";
                           break;
                        case 33:
                           var3 = "vm_golf7_vehicle_status_start_stop_prompt_33";
                           break;
                        default:
                           break label60;
                     }

                     var6.add(new WarningEntity(var5.append(CommUtil.getStrByResId(var4, var3)).toString()));
                  }
               }

               ArrayList var9 = this.list;
               var7 = this.$m0x60List;
               ArrayList var11 = this.$m0x62List;
               var9.clear();
               var9.addAll((Collection)var7);
               var9.addAll((Collection)var11);
               GeneralWarningDataData.dataList = (List)var9;
               this.this$0.updateWarningActivity((Bundle)null);
            }

         }
      });
      var3.put(98, new Parser(this, var4, var1, var5) {
         final Context $context;
         final ArrayList $m0x60List;
         final ArrayList $m0x62List;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$m0x62List = var2;
            this.$context = var3;
            this.$m0x60List = var4;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            this.$m0x62List.clear();
            this.$m0x62List.add(new WarningEntity(CommUtil.getStrByResId(this.$context, "_2_information_title_4")));
            int var2 = RangesKt.coerceAtMost(this.this$0.mCanbusInfoInt[2], 3);

            ArrayList var4;
            label36:
            for(var1 = 0; var1 < var2; ++var1) {
               var4 = this.$m0x62List;
               StringBuilder var6 = (new StringBuilder()).append("\t\t");
               Context var5 = this.$context;
               String var3;
               switch (this.this$0.mCanbusInfoInt[var1 + 3]) {
                  case 0:
                     var3 = "vm_golf7_Conv_consumers_prompt_0";
                     break;
                  case 1:
                     var3 = "vm_golf7_Conv_consumers_prompt_1";
                     break;
                  case 2:
                     var3 = "vm_golf7_Conv_consumers_prompt_2";
                     break;
                  case 3:
                     var3 = "vm_golf7_Conv_consumers_prompt_3";
                     break;
                  case 4:
                     var3 = "vm_golf7_Conv_consumers_prompt_4";
                     break;
                  case 5:
                     var3 = "vm_golf7_Conv_consumers_prompt_5";
                     break;
                  case 6:
                     var3 = "vm_golf7_Conv_consumers_prompt_6";
                     break;
                  case 7:
                     var3 = "vm_golf7_Conv_consumers_prompt_7";
                     break;
                  case 8:
                     var3 = "vm_golf7_Conv_consumers_prompt_8";
                     break;
                  case 9:
                     var3 = "vm_golf7_Conv_consumers_prompt_9";
                     break;
                  case 10:
                     var3 = "vm_golf7_Conv_consumers_prompt_10";
                     break;
                  case 11:
                     var3 = "vm_golf7_Conv_consumers_prompt_11";
                     break;
                  case 12:
                     var3 = "vm_golf7_Conv_consumers_prompt_12";
                     break;
                  case 13:
                     var3 = "vm_golf7_Conv_consumers_prompt_13";
                     break;
                  case 14:
                     var3 = "vm_golf7_Conv_consumers_prompt_14";
                     break;
                  case 15:
                     var3 = "vm_golf7_Conv_consumers_prompt_15";
                     break;
                  case 16:
                     var3 = "vm_golf7_Conv_consumers_prompt_16";
                     break;
                  case 17:
                     var3 = "vm_golf7_Conv_consumers_prompt_17";
                     break;
                  case 18:
                     var3 = "vm_golf7_Conv_consumers_prompt_18";
                     break;
                  default:
                     break label36;
               }

               var4.add(new WarningEntity(var6.append(CommUtil.getStrByResId(var5, var3)).toString()));
            }

            ArrayList var7 = this.list;
            var4 = this.$m0x60List;
            ArrayList var8 = this.$m0x62List;
            var7.clear();
            var7.addAll((Collection)var4);
            var7.addAll((Collection)var8);
            GeneralWarningDataData.dataList = (List)var7;
            this.this$0.updateWarningActivity((Bundle)null);
         }
      });
      var3.put(99, new Parser(this, var1) {
         final Context $context;
         private final ArrayList driList;
         private final ArrayList setList;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.setList = new ArrayList();
            this.driList = new ArrayList();
         }

         private final void setDay(DriverUpdateEntity var1) {
            MsgMgr var4 = this.this$0;
            ArrayList var5 = this.driList;
            Context var6 = this.$context;
            var5.clear();
            int var2 = var4.mCanbusInfoInt[3] & 15;
            String var3;
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     return;
                  }

                  var3 = "vm_golf7_vehicle_setup_service_overdue_days";
               } else {
                  var3 = "vm_golf7_vehicle_setup_service_days";
               }
            } else {
               var3 = null;
            }

            label23: {
               if (var3 != null) {
                  var3 = CommUtil.getStrByResId(var6, var3);
                  Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context, it)");
                  var3 = String.format(var3, Arrays.copyOf(new Object[]{var4.mergeValue(var4.mCanbusInfoInt[4], var4.mCanbusInfoInt[5])}, 1));
                  Intrinsics.checkNotNullExpressionValue(var3, "format(this, *args)");
                  if (var3 != null) {
                     break label23;
                  }
               }

               var3 = "--";
            }

            var5.add(var1.setValue(var3));
            var4.updateGeneralDriveData((List)var5);
            this.this$0.updateDriveDataActivity((Bundle)null);
         }

         private final void setDistance(DriverUpdateEntity var1) {
            MsgMgr var4 = this.this$0;
            ArrayList var5 = this.driList;
            Context var3 = this.$context;
            var5.clear();
            int var2 = var4.mCanbusInfoInt[3] & 15;
            String var7;
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     return;
                  }

                  var7 = CommUtil.getStrByResId(var3, "vm_golf7_vehicle_setup_service_overdue") + ' ';
               } else {
                  var7 = "";
               }
            } else {
               var7 = null;
            }

            label28: {
               if (var7 != null) {
                  StringBuilder var6 = (new StringBuilder()).append(var7).append(var4.mergeValue(var4.mCanbusInfoInt[4], var4.mCanbusInfoInt[5]) * 100).append(' ');
                  if ((var4.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                     var7 = "mi";
                  } else {
                     var7 = "km";
                  }

                  var7 = var6.append(var7).toString();
                  if (var7 != null) {
                     break label28;
                  }
               }

               var7 = "--";
            }

            var5.add(var1.setValue(var7));
            var4.updateGeneralDriveData((List)var5);
            this.this$0.updateDriveDataActivity((Bundle)null);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               var1 = this.this$0.mCanbusInfoInt[2];
               MsgMgr var2;
               ArrayList var3;
               if (var1 != 0) {
                  DriverUpdateEntity var5;
                  if (var1 != 16) {
                     if (var1 != 17) {
                        switch (var1) {
                           case 32:
                              var5 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_oil_change_service_days");
                              if (var5 != null) {
                                 this.setDay(var5);
                              }
                              break;
                           case 33:
                              var5 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_oil_change_service_distance");
                              if (var5 != null) {
                                 this.setDistance(var5);
                              }
                              break;
                           case 34:
                              SettingUpdateEntity var4 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_service_wiper_in_maintenance_position");
                              if (var4 != null) {
                                 var2 = this.this$0;
                                 var3 = this.setList;
                                 var3.clear();
                                 var3.add(var4.setValue(var2.mCanbusInfoInt[3] & 1));
                                 var2.updateGeneralSettingData((List)var3);
                                 var2.updateSettingActivity((Bundle)null);
                              }
                        }
                     } else {
                        var5 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_volkswagen_inspection_distance");
                        if (var5 != null) {
                           this.setDistance(var5);
                        }
                     }
                  } else {
                     var5 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_volkswagen_inspection_days");
                     if (var5 != null) {
                        this.setDay(var5);
                     }
                  }
               } else {
                  DriverUpdateEntity var6 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_vehicle_no");
                  if (var6 != null) {
                     var2 = this.this$0;
                     var3 = this.driList;
                     var3.clear();
                     var3.add(var6.setValue(new String(ArraysKt.copyOfRange(var2.mCanbusInfoByte, 3, var2.mCanbusInfoByte.length), Charsets.UTF_8)));
                     var2.updateGeneralDriveData((List)var3);
                     var2.updateDriveDataActivity((Bundle)null);
                  }
               }
            }

         }
      });
      var4 = new ArrayList();
      String[] var6 = new String[3];

      int var2;
      for(var2 = 0; var2 < 3; ++var2) {
         var6[var2] = "";
      }

      var4.add(new TireUpdateEntity(0, 0, var6));
      var6 = new String[3];

      for(var2 = 0; var2 < 3; ++var2) {
         var6[var2] = "";
      }

      var4.add(new TireUpdateEntity(1, 0, var6));
      var6 = new String[3];

      for(var2 = 0; var2 < 3; ++var2) {
         var6[var2] = "";
      }

      var4.add(new TireUpdateEntity(2, 0, var6));
      var6 = new String[3];

      for(var2 = 0; var2 < 3; ++var2) {
         var6[var2] = "";
      }

      var4.add(new TireUpdateEntity(3, 0, var6));
      GeneralTireData.isHaveSpareTire = false;
      var3.put(102, new Parser(this, var4) {
         final ArrayList $tireInfoList;
         final MsgMgr this$0;
         private final Pair[] units;

         {
            this.this$0 = var1;
            this.$tireInfoList = var2;
            this.units = new Pair[]{TuplesKt.to("bar", null.INSTANCE), TuplesKt.to("psi", null.INSTANCE), TuplesKt.to("kPa", null.INSTANCE)};
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               Pair var5 = this.units[RangesKt.coerceIn(this.this$0.mCanbusInfoInt[7], (ClosedRange)(new IntRange(0, 2)))];
               Iterable var6 = (Iterable)this.$tireInfoList;
               MsgMgr var4 = this.this$0;
               Iterator var9 = var6.iterator();

               for(var1 = 0; var9.hasNext(); ++var1) {
                  Object var7 = var9.next();
                  if (var1 < 0) {
                     CollectionsKt.throwIndexOverflow();
                  }

                  List var10 = ((TireUpdateEntity)var7).getList();
                  int var2 = RangesKt.coerceIn(var4.mCanbusInfoInt[2], (ClosedRange)(new IntRange(0, 1)));
                  StringBuilder var8 = new StringBuilder();
                  int var3 = var4.mCanbusInfoInt[var1 + 3];
                  var10.set(var2, var8.append(((Number)((Function1)var5.getSecond()).invoke(var3)).floatValue()).append(' ').append((String)var5.getFirst()).toString());
               }

               GeneralTireData.dataList = (List)this.$tireInfoList;
               this.this$0.updateTirePressureActivity((Bundle)null);
            }

         }
      });
      var3.put(104, new Parser(this, var4, var1) {
         final Context $context;
         final ArrayList $tireInfoList;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$tireInfoList = var2;
            this.$context = var3;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               var1 = this.this$0.mCanbusInfoInt[3];
               String var2;
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        var2 = "null_value";
                     } else {
                        var2 = "vm_golf7__direct_tpms_warn_lose_pressure";
                     }
                  } else {
                     var2 = "vm_golf7__direct_tpms_warn_low_pressure";
                  }
               } else {
                  var2 = "vm_golf7__direct_tpms_warn_check_pressure";
               }

               Iterable var3 = (Iterable)this.$tireInfoList;
               MsgMgr var4 = this.this$0;
               Context var5 = this.$context;
               var1 = 0;

               for(Iterator var6 = var3.iterator(); var6.hasNext(); ++var1) {
                  Object var8 = var6.next();
                  if (var1 < 0) {
                     CollectionsKt.throwIndexOverflow();
                  }

                  TireUpdateEntity var9 = (TireUpdateEntity)var8;
                  var9.setTireStatus(var4.mCanbusInfoInt[2] >> var1 & 1);
                  List var7 = var9.getList();
                  String var10;
                  if (var9.getTireStatus() == 0) {
                     var10 = "vm_golf7__direct_tpms_warn_normal";
                  } else {
                     var10 = var2;
                  }

                  var7.set(2, CommUtil.getStrByResId(var5, var10));
               }

               GeneralTireData.dataList = (List)this.$tireInfoList;
               this.this$0.updateTirePressureActivity((Bundle)null);
            }

         }
      });
   }

   private static final int initParsers$lambda_4$getRadarValue(int var0, int var1) {
      byte var2 = 0;
      if (Integer.valueOf(var0).equals(0)) {
         var0 = var2;
      } else {
         var0 = (int)((float)var0 / (float)var1 * (float)9 + (float)1);
      }

      return var0;
   }

   private final boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanbusInfoInt)) {
         return false;
      } else {
         this.mAirData = this.mCanbusInfoInt;
         return true;
      }
   }

   private final int mergeValue(int... var1) {
      int var5 = var1.length;
      int var3 = 0;
      int var4 = 0;

      for(int var2 = 0; var3 < var5; ++var2) {
         var4 |= var1[var3] << var2 * 8;
         ++var3;
      }

      return var4;
   }

   private static final void reverseStateChange$lambda_3(Object var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 41});
      CycleRequest.getInstance().reset(100);
   }

   private final void sendMultiCommand(byte[]... var1) {
      TimerUtil var2 = new TimerUtil();
      var2.startTimer((TimerTask)(new TimerTask(var1, var2) {
         final byte[][] $commands;
         final TimerUtil $this_run;
         private int i;

         {
            this.$commands = var1;
            this.$this_run = var2;
         }

         public final int getI() {
            return this.i;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.$commands;
            if (var1 < ((Object[])var2).length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.$this_run.stopTimer();
            }

         }

         public final void setI(int var1) {
            this.i = var1;
         }
      }), 0L, 100L);
   }

   private final void sendTextInfo(int var1, String var2) {
      String var3 = var2;
      if (var2 == null) {
         var3 = "Unknown";
      }

      byte[] var4;
      if (var3.length() > 10) {
         var4 = StringsKt.substring(var3, new IntRange(0, 10)).getBytes(Charsets.UTF_16BE);
         Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      } else {
         var4 = var3.getBytes(Charsets.UTF_16BE);
         Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      }

      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, (byte)var1, 16}, var4));
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._3.UiMgr");
      this.mUiMgr = (UiMgr)var2;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 255;
      RadarInfoUtil.mDisableData2 = 255;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
   }

   public void atvInfoChange() {
      byte[] var1 = this.m0xC0Command;
      var1[2] = 3;
      var1[3] = -1;
      ArraysKt.fill(var1, (byte)0, 4, 10);
      CanbusMsgSender.sendMsg(this.m0xC0Command);
      M0x70Sender.sendInfo$default(this.m0x70Sender, "ATV", (String)null, (String)null, (String)null, 14, (Object)null);
   }

   public void auxInInfoChange() {
      byte[] var1 = this.m0xC0Command;
      var1[2] = 7;
      var1[3] = -1;
      ArraysKt.fill(var1, (byte)0, 4, 10);
      CanbusMsgSender.sendMsg(this.m0xC0Command);
      M0x70Sender.sendInfo$default(this.m0x70Sender, "AUX", (String)null, (String)null, (String)null, 14, (Object)null);
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "artist");
      Intrinsics.checkNotNullParameter(var3, "album");
      byte[] var4 = this.m0xC0Command;
      var4[2] = 11;
      var4[3] = -1;
      var4[4] = 0;
      var4[5] = 0;
      var4[6] = 0;
      var4[7] = 0;
      var4[8] = 0;
      var4[9] = 0;
      CanbusMsgSender.sendMsg(var4);
      M0x70Sender.sendInfo$default(this.m0x70Sender, var1, var2, var3, (String)null, 8, (Object)null);
      this.sendTextInfo(112, var1);
      this.sendTextInfo(113, var2);
      this.sendTextInfo(114, var3);
      this.sendTextInfo(115, " ");
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      byte[] var12 = this.m0xC5Command;
      var12[2] = (byte)(var8 & 240 | var7 & 15);
      byte var10;
      if (var6) {
         var10 = 0;
      } else {
         var10 = 64;
      }

      byte var11;
      if (var5) {
         var11 = 0;
      } else {
         var11 = 32;
      }

      if (!var3) {
         var1 = 6;
      }

      var12[3] = (byte)(var10 | var11 | 16 | var1 & 15);
      if (var2 != null) {
         ArraysKt.copyInto$default(var2, this.m0xCACommand, 4, 0, 0, 12, (Object)null);
      }

      this.sendMultiCommand(this.m0xC5Command, this.m0xCACommand);
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      var1 = this.m0xC7Command;
      int var5 = var4 / 60;
      var1[2] = (byte)(var5 / 60 / 60);
      var1[3] = (byte)(var5 % 60);
      var1[4] = (byte)(var4 % 60);
      CanbusMsgSender.sendMsg(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      int[] var5 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var5, "getByteArrayToIntArray(canbusInfo)");
      this.mCanbusInfoInt = var5;
      this.mCanbusInfoByte = var2;

      Exception var10000;
      label28: {
         boolean var10001;
         Parser var6;
         try {
            var6 = (Parser)this.mParserMap.get(var5[1]);
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label28;
         }

         if (var6 == null) {
            return;
         }

         try {
            var6.parse(this.mCanbusInfoInt.length - 2);
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var7 = var10000;
      Log.e("CanBusError", var7.toString());
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      MediaShareData.Volume var4 = MediaShareData.Volume.INSTANCE;
      byte[] var3 = this.m0xC4Command;
      short var5;
      if (var4.getVolume() == 0) {
         var5 = 128;
      } else {
         var5 = 0;
      }

      var3[2] = (byte)(var4.getVolume() | var5);
      CanbusMsgSender.sendMsg(var3);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      byte[] var14 = this.m0xA6Command;
      var14[2] = (byte)var2;
      var14[3] = (byte)var3;
      var14[4] = (byte)var4;
      short var15;
      if (var10) {
         var15 = 0;
      } else {
         var15 = 128;
      }

      var14[5] = (byte)(var15 | var8);
      var14[6] = (byte)var6;
      CanbusMsgSender.sendMsg(var14);
   }

   public void dtvInfoChange() {
      byte[] var1 = this.m0xC0Command;
      var1[2] = 10;
      var1[3] = -1;
      ArraysKt.fill(var1, (byte)0, 4, 10);
      CanbusMsgSender.sendMsg(this.m0xC0Command);
      M0x70Sender.sendInfo$default(this.m0x70Sender, "DTV", (String)null, (String)null, (String)null, 14, (Object)null);
   }

   public final int[] getMAirData() {
      return this.mAirData;
   }

   public final int getMColour() {
      return this.mColour;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      Log.i("_3_MsgMgr", "initCommand: ");
      this.initAmplifier(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      MediaShareData.Music var30 = MediaShareData.Music.INSTANCE;
      var3 = var7 % 10;
      boolean var29 = true;
      boolean var28;
      if (var3 >= 0 && var3 < 5) {
         var28 = true;
      } else {
         var28 = false;
      }

      if (var28) {
         byte[] var31 = this.m0xC0Command;
         byte var25;
         if (StringsKt.contains$default((CharSequence)var30.getMPath(), (CharSequence)"emulated", false, 2, (Object)null)) {
            var25 = 9;
         } else {
            var25 = 8;
         }

         var31[2] = var25;
         var31 = this.m0xC0Command;
         var31[3] = 17;
         var31[4] = (byte)(var30.getMPlaySize() & 255);
         this.m0xC0Command[5] = (byte)(var30.getMPlaySize() >> 8 & 255);
         this.m0xC0Command[6] = (byte)(var30.getAPlayIndex() & 255);
         this.m0xC0Command[7] = (byte)(var30.getAPlayIndex() >> 8 & 255);
         var31 = this.m0xC0Command;
         var19 = var30.getMPos();
         var14 = (long)1000;
         var19 /= var14;
         long var26 = (long)60;
         var31[8] = (byte)((int)(var19 / var26 % var26));
         this.m0xC0Command[9] = (byte)((int)(var30.getMPos() / var14 % var26));
         CanbusMsgSender.sendMsg(this.m0xC0Command);
      } else {
         if (5 <= var3 && var3 < 10) {
            var28 = var29;
         } else {
            var28 = false;
         }

         if (var28) {
            this.sendTextInfo(112, var21);
            this.sendTextInfo(113, var23);
            this.sendTextInfo(114, var22);
            this.sendTextInfo(115, " ");
         }
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var6;
      MediaShareData.Radio var7;
      byte[] var8;
      label39: {
         Intrinsics.checkNotNullParameter(var2, "currBand");
         Intrinsics.checkNotNullParameter(var3, "currentFreq");
         Intrinsics.checkNotNullParameter(var4, "psName");
         var7 = MediaShareData.Radio.INSTANCE;
         var8 = this.m0xC0Command;
         var8[2] = 1;
         var6 = 3;
         var8[3] = 1;
         var4 = var7.getMBand();
         switch (var4) {
            case "AM1":
               var6 = 17;
               break label39;
            case "AM2":
               var6 = 18;
               break label39;
            case "FM1":
               var6 = 1;
               break label39;
            case "FM2":
               var6 = 2;
               break label39;
            case "FM3":
         }

         var6 = 0;
      }

      var8[4] = var6;
      if (StringsKt.contains$default((CharSequence)var7.getMBand(), (CharSequence)"FM", false, 2, (Object)null)) {
         var1 = (int)(Float.parseFloat(var7.getMFreq()) * (float)100);
      } else if (StringsKt.contains$default((CharSequence)var7.getMBand(), (CharSequence)"AM", false, 2, (Object)null)) {
         var1 = Integer.parseInt(var7.getMFreq());
      } else {
         var1 = 0;
      }

      var8 = this.m0xC0Command;
      var8[5] = (byte)(var1 & 255);
      var8[6] = (byte)(var1 >> 8 & 255);
      var8[7] = (byte)var7.getMPresetIndex();
      var8 = this.m0xC0Command;
      var8[8] = 0;
      var8[9] = 0;
      var8 = this.m0xC1Command;
      var8[2] = (byte)DataHandleUtils.setOneBit(var8[2], 6, var7.getMIsStereo());
      this.sendMultiCommand(this.m0xC0Command, this.m0xC1Command);
   }

   public void reverseStateChange(boolean var1) {
      super.reverseStateChange(var1);
      if (var1) {
         CycleRequest.getInstance().start(100, new MsgMgr$$ExternalSyntheticLambda0());
      } else {
         CycleRequest.getInstance().stop();
      }

   }

   public final void setMAirData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      Log.i("_3_MsgMgr", "sourceSwitchNoMediaInfoChange: isPowerOff[" + var1 + ']');
      if (!var1) {
         this.initAmplifier((Context)null);
      }

   }

   public final void updateSettings(String var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "value");
      SettingUpdateEntity var3 = (SettingUpdateEntity)this.mSettingItemIndexHashMap.get(var1);
      if (var3 != null) {
         this.updateGeneralSettingData((List)CollectionsKt.arrayListOf(new SettingUpdateEntity[]{var3.setValue(var2)}));
         this.updateSettingActivity((Bundle)null);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      MediaShareData.Video var25 = MediaShareData.Video.INSTANCE;
      byte[] var26 = this.m0xC0Command;
      byte var18;
      if (StringsKt.contains$default((CharSequence)var25.getMPath(), (CharSequence)"emulated", false, 2, (Object)null)) {
         var18 = 9;
      } else {
         var18 = 8;
      }

      var26[2] = var18;
      var26 = this.m0xC0Command;
      var26[3] = 17;
      var26[4] = (byte)(var25.getMPlaySize() & 255);
      this.m0xC0Command[5] = (byte)(var25.getMPlaySize() >> 8 & 255);
      this.m0xC0Command[6] = (byte)(var25.getMPlayIndex() & 255);
      this.m0xC0Command[7] = (byte)(var25.getMPlayIndex() >> 8 & 255);
      var26 = this.m0xC0Command;
      long var21 = var25.getMPos();
      long var19 = (long)1000;
      long var23 = var21 / var19;
      var21 = (long)60;
      var26[8] = (byte)((int)(var23 / var21 % var21));
      this.m0xC0Command[9] = (byte)((int)(var25.getMPos() / var19 % var21));
      CanbusMsgSender.sendMsg(this.m0xC0Command);
   }

   @Metadata(
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00040\nj\b\u0012\u0004\u0012\u00020\u0004`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"},
      d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$Companion;", "", "()V", "AMPLIFIER_BALANCE_DATA_HALF", "", "MILE_TO_KILO_RATE", "", "TAG", "", "languageList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getLanguageList", "()Ljava/util/ArrayList;", "CanBusInfo_release"},
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

      public final ArrayList getLanguageList() {
         return MsgMgr.languageList;
      }
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\rR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"},
      d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$M0x70Sender;", "", "(Lcom/hzbhd/canbus/car/_3/MsgMgr;)V", "commands", "", "", "[[B", "emptyInfo", "timerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "sendInfo", "", "line1", "", "line2", "line3", "line4", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class M0x70Sender {
      private final byte[][] commands;
      private final byte[] emptyInfo;
      final MsgMgr this$0;
      private final TimerUtil timerUtil;

      public M0x70Sender(MsgMgr var1) {
         this.this$0 = var1;
         byte[][] var6 = new byte[4][];

         for(int var3 = 0; var3 < 4; ++var3) {
            byte[] var5 = new byte[23];

            for(int var4 = 0; var4 < 23; ++var4) {
               byte var2;
               if (var4 != 0) {
                  if (var4 != 1) {
                     if (var4 != 2) {
                        var2 = 0;
                     } else {
                        var2 = 16;
                     }
                  } else {
                     var2 = (byte)(var3 + 112);
                  }
               } else {
                  var2 = 22;
               }

               var5[var4] = var2;
            }

            var6[var3] = var5;
         }

         this.commands = var6;
         byte[] var7 = "          ".getBytes(Charsets.UTF_16);
         Intrinsics.checkNotNullExpressionValue(var7, "this as java.lang.String).getBytes(charset)");
         this.emptyInfo = var7;
         this.timerUtil = new TimerUtil();
      }

      // $FF: synthetic method
      public static void sendInfo$default(M0x70Sender var0, String var1, String var2, String var3, String var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = "";
         }

         if ((var5 & 2) != 0) {
            var2 = "";
         }

         if ((var5 & 4) != 0) {
            var3 = "";
         }

         if ((var5 & 8) != 0) {
            var4 = "";
         }

         var0.sendInfo(var1, var2, var3, var4);
      }

      public final void sendInfo(String var1, String var2, String var3, String var4) {
         Intrinsics.checkNotNullParameter(var1, "line1");
         Intrinsics.checkNotNullParameter(var2, "line2");
         Intrinsics.checkNotNullParameter(var3, "line3");
         Intrinsics.checkNotNullParameter(var4, "line4");
         Pair var6 = TuplesKt.to(var1, this.commands[0]);
         Pair var7 = TuplesKt.to(var2, this.commands[1]);
         Pair var8 = TuplesKt.to(var3, this.commands[2]);
         Pair var5 = TuplesKt.to(var4, this.commands[3]);
         MsgMgr var9 = this.this$0;
         this.timerUtil.startTimer((TimerTask)(new TimerTask(new Pair[]{var6, var7, var8, var5}, this, var9) {
            final Pair[] $this_run;
            private int i;
            final M0x70Sender this$0;
            final MsgMgr this$1;

            {
               this.$this_run = var1;
               this.this$0 = var2;
               this.this$1 = var3;
            }

            public final int getI() {
               return this.i;
            }

            public void run() {
               int var1 = this.i;
               Pair[] var2 = this.$this_run;
               if (var1 < var2.length) {
                  byte[] var3;
                  if (Intrinsics.areEqual((Object)var2[var1].getFirst(), (Object)"")) {
                     var3 = this.this$0.emptyInfo;
                  } else {
                     var3 = this.this$1.getInfo((String)this.$this_run[this.i].getFirst(), 10).getBytes(Charsets.UTF_16);
                     Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
                  }

                  ArraysKt.copyInto$default(var3, (byte[])this.$this_run[this.i].getSecond(), 3, 2, 0, 8, (Object)null);
                  CanbusMsgSender.sendMsg((byte[])this.$this_run[this.i].getSecond());
                  ++this.i;
               } else {
                  this.this$0.timerUtil.stopTimer();
               }

            }

            public final void setI(int var1) {
               this.i = var1;
            }
         }), 100L, 100L);
      }
   }

   @Metadata(
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_3/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private abstract class Parser {
      private final int PARSE_LISTENERS_LENGTH;
      private int[] canbusInfo;
      private final OnParseListener[] onParseListeners;
      final MsgMgr this$0;

      public Parser(MsgMgr var1, String var2) {
         Intrinsics.checkNotNullParameter(var2, "msg");
         this.this$0 = var1;
         super();
         OnParseListener[] var4 = this.setOnParseListeners();
         this.onParseListeners = var4;
         int var3 = var4.length;
         this.PARSE_LISTENERS_LENGTH = var3;
         Log.i("_3_MsgMgr", "Parser: " + var2 + " length " + var3);
      }

      public final int[] getCanbusInfo() {
         return this.canbusInfo;
      }

      public final boolean isDataChange() {
         boolean var1;
         if (Arrays.equals(this.canbusInfo, this.this$0.mCanbusInfoInt)) {
            var1 = false;
         } else {
            int[] var2 = this.this$0.mCanbusInfoInt;
            var2 = Arrays.copyOf(var2, var2.length);
            Intrinsics.checkNotNullExpressionValue(var2, "copyOf(this, size)");
            this.canbusInfo = var2;
            var1 = true;
         }

         return var1;
      }

      public void parse(int var1) {
         for(var1 = Math.min(var1, this.PARSE_LISTENERS_LENGTH); var1 > 0; --var1) {
            OnParseListener var2 = this.onParseListeners[var1 - 1];
            if (var2 != null) {
               var2.onParse();
            }
         }

      }

      public final void setCanbusInfo(int[] var1) {
         this.canbusInfo = var1;
      }

      public OnParseListener[] setOnParseListeners() {
         return (OnParseListener[])((Object[])(new OnParseListener[0]));
      }
   }

   @Metadata(
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bJ\n\u0010\u0012\u001a\u00020\u000f*\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"},
      d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper;", "", "()V", "kmh", "Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper$SpeedUnit;", "mph", "<set-?>", "speedUnit", "getSpeedUnit", "()Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper$SpeedUnit;", "value", "", "getProgress", "getValue", "setUnit", "", "context", "Landroid/content/Context;", "setValue", "SpeedUnit", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SpeedAlertHelper {
      public static final SpeedAlertHelper INSTANCE = new SpeedAlertHelper();
      private static final SpeedUnit kmh;
      private static final SpeedUnit mph;
      private static SpeedUnit speedUnit;
      private static int value;

      static {
         SpeedUnit var0 = new SpeedUnit(10, 30, 240, "_2_setting_1_3_1", (Function1)null.INSTANCE);
         kmh = var0;
         mph = new SpeedUnit(5, 20, 150, "_2_setting_1_3_2", (Function1)null.INSTANCE);
         speedUnit = var0;
      }

      private SpeedAlertHelper() {
      }

      public final int getProgress() {
         SpeedUnit var1 = speedUnit;
         return (value - var1.getMin()) / var1.getStep();
      }

      public final SpeedUnit getSpeedUnit() {
         return speedUnit;
      }

      public final int getValue() {
         return value / speedUnit.getStep();
      }

      public final void setUnit(Context var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "context");
         SpeedUnit var3;
         if (var2 == 0) {
            var3 = kmh;
         } else {
            var3 = mph;
         }

         if (!Intrinsics.areEqual((Object)speedUnit, (Object)var3)) {
            speedUnit = var3;
            List var5 = UiMgrFactory.getCanUiMgr(var1).getSettingUiSet(var1).getList();
            Intrinsics.checkNotNullExpressionValue(var5, "getCanUiMgr(context)\n   …ettingUiSet(context).list");
            Iterable var8 = (Iterable)var5;
            Collection var6 = (Collection)(new ArrayList());
            Iterator var4 = var8.iterator();

            while(var4.hasNext()) {
               List var9 = ((SettingPageUiSet.ListBean)var4.next()).getItemList();
               Intrinsics.checkNotNullExpressionValue(var9, "it.itemList");
               CollectionsKt.addAll(var6, (Iterable)var9);
            }

            var8 = (Iterable)((List)var6);
            var6 = (Collection)(new ArrayList());
            Iterator var11 = var8.iterator();

            while(var11.hasNext()) {
               Object var10 = var11.next();
               if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)var10).getTitleSrn(), (Object)"_3_40h_20h_p2")) {
                  var6.add(var10);
               }
            }

            var5 = (List)var6;
            if (var5.size() > 0) {
               SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var5.get(0);
               SpeedAlertHelper var12 = INSTANCE;
               var7.setMin(speedUnit.getMin() / speedUnit.getStep());
               var7.setMax(speedUnit.getMax() / speedUnit.getStep());
               var7.setUnit(speedUnit.getUnitStrName());
               var12.setValue((int)((Number)speedUnit.getConversion().invoke(value)).floatValue());
            }
         }

      }

      public final void setValue(int var1) {
         SpeedUnit var2 = speedUnit;
         value = RangesKt.coerceIn(var1, var2.getMin(), var2.getMax());
      }

      @Metadata(
         d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B>\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\u001a\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bHÆ\u0003JL\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0007HÖ\u0001R\"\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "},
         d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper$SpeedUnit;", "", "step", "", "min", "max", "unitStrName", "", "conversion", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(IIILjava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getConversion", "()Lkotlin/jvm/functions/Function1;", "getMax", "()I", "getMin", "getStep", "getUnitStrName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      public static final class SpeedUnit {
         private final Function1 conversion;
         private final int max;
         private final int min;
         private final int step;
         private final String unitStrName;

         public SpeedUnit(int var1, int var2, int var3, String var4, Function1 var5) {
            Intrinsics.checkNotNullParameter(var4, "unitStrName");
            Intrinsics.checkNotNullParameter(var5, "conversion");
            super();
            this.step = var1;
            this.min = var2;
            this.max = var3;
            this.unitStrName = var4;
            this.conversion = var5;
         }

         // $FF: synthetic method
         public static SpeedUnit copy$default(SpeedUnit var0, int var1, int var2, int var3, String var4, Function1 var5, int var6, Object var7) {
            if ((var6 & 1) != 0) {
               var1 = var0.step;
            }

            if ((var6 & 2) != 0) {
               var2 = var0.min;
            }

            if ((var6 & 4) != 0) {
               var3 = var0.max;
            }

            if ((var6 & 8) != 0) {
               var4 = var0.unitStrName;
            }

            if ((var6 & 16) != 0) {
               var5 = var0.conversion;
            }

            return var0.copy(var1, var2, var3, var4, var5);
         }

         public final int component1() {
            return this.step;
         }

         public final int component2() {
            return this.min;
         }

         public final int component3() {
            return this.max;
         }

         public final String component4() {
            return this.unitStrName;
         }

         public final Function1 component5() {
            return this.conversion;
         }

         public final SpeedUnit copy(int var1, int var2, int var3, String var4, Function1 var5) {
            Intrinsics.checkNotNullParameter(var4, "unitStrName");
            Intrinsics.checkNotNullParameter(var5, "conversion");
            return new SpeedUnit(var1, var2, var3, var4, var5);
         }

         public boolean equals(Object var1) {
            if (this == var1) {
               return true;
            } else if (!(var1 instanceof SpeedUnit)) {
               return false;
            } else {
               SpeedUnit var2 = (SpeedUnit)var1;
               if (this.step != var2.step) {
                  return false;
               } else if (this.min != var2.min) {
                  return false;
               } else if (this.max != var2.max) {
                  return false;
               } else if (!Intrinsics.areEqual((Object)this.unitStrName, (Object)var2.unitStrName)) {
                  return false;
               } else {
                  return Intrinsics.areEqual((Object)this.conversion, (Object)var2.conversion);
               }
            }
         }

         public final Function1 getConversion() {
            return this.conversion;
         }

         public final int getMax() {
            return this.max;
         }

         public final int getMin() {
            return this.min;
         }

         public final int getStep() {
            return this.step;
         }

         public final String getUnitStrName() {
            return this.unitStrName;
         }

         public int hashCode() {
            return (((Integer.hashCode(this.step) * 31 + Integer.hashCode(this.min)) * 31 + Integer.hashCode(this.max)) * 31 + this.unitStrName.hashCode()) * 31 + this.conversion.hashCode();
         }

         public String toString() {
            return "SpeedUnit(step=" + this.step + ", min=" + this.min + ", max=" + this.max + ", unitStrName=" + this.unitStrName + ", conversion=" + this.conversion + ')';
         }
      }
   }
}
