package com.hzbhd.canbus.car._112;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.AppEnableUtil;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u0005\n\u0002\b\u001f\n\u0002\u0010\t\n\u0002\b\u001d\u0018\u0000 v2\u00020\u0001:\u0003vwxB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000bH\u0016J\b\u0010!\u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020\u001fH\u0016J\"\u0010#\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016J \u0010(\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016J \u0010)\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016J\u001a\u0010*\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000b2\u0006\u0010+\u001a\u00020\u0007H\u0016Jp\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020&2\u0006\u00107\u001a\u00020&2\u0006\u00108\u001a\u00020&2\u0006\u00109\u001a\u00020\u0004H\u0016Jv\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020&2\u0006\u0010E\u001a\u00020\u00042\b\u0010F\u001a\u0004\u0018\u00010\u000f2\b\u0010G\u001a\u0004\u0018\u00010\u000f2\b\u0010H\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010I\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010J\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010K\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010L\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000bH\u0002J¾\u0001\u0010M\u001a\u00020\u001f2\u0006\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020<2\u0006\u0010S\u001a\u00020<2\u0006\u0010T\u001a\u00020<2\u0006\u0010U\u001a\u00020<2\u0006\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u00020<2\b\u0010X\u001a\u0004\u0018\u00010\u000f2\b\u0010Y\u001a\u0004\u0018\u00010\u000f2\b\u0010Z\u001a\u0004\u0018\u00010\u000f2\u0006\u0010[\u001a\u00020\\2\u0006\u0010;\u001a\u00020<2\u0006\u0010]\u001a\u00020\u00042\u0006\u0010D\u001a\u00020&2\u0006\u0010^\u001a\u00020\\2\u0006\u0010_\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020&H\u0016J\u000e\u0010c\u001a\u00020\u001f2\u0006\u0010d\u001a\u00020\u000fJ0\u0010e\u001a\u00020\u001f2\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u000f2\u0006\u0010h\u001a\u00020\u000f2\u0006\u0010i\u001a\u00020\u000f2\u0006\u0010j\u001a\u00020\u0004H\u0016J\u0018\u0010k\u001a\u00020\u001f2\u0006\u0010l\u001a\u00020\u00042\u0006\u0010m\u001a\u00020\u0007H\u0002J\u000e\u0010n\u001a\u00020\u001f2\u0006\u0010o\u001a\u00020\u0004J\u0012\u0010p\u001a\u00020\u001f2\b\u0010l\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010q\u001a\u00020\u001f2\u0006\u0010r\u001a\u00020&H\u0016J\u0098\u0001\u0010s\u001a\u00020\u001f2\u0006\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020<2\u0006\u0010S\u001a\u00020<2\u0006\u0010T\u001a\u00020<2\b\u0010U\u001a\u0004\u0018\u00010\u000f2\u0006\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u00020<2\b\u0010X\u001a\u0004\u0018\u00010\u000f2\b\u0010Y\u001a\u0004\u0018\u00010\u000f2\b\u0010Z\u001a\u0004\u0018\u00010\u000f2\u0006\u0010[\u001a\u00020\u00042\u0006\u0010t\u001a\u00020<2\u0006\u0010D\u001a\u00020&2\u0006\u0010u\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0015R\u00020\u00000\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006y"},
   d2 = {"Lcom/hzbhd/canbus/car/_112/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mAmplifierSwitch", "", "mCanId", "mCanbusInfoByte", "", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "mDifferent", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_112/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mTimerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "mUiMgr", "Lcom/hzbhd/canbus/car/_112/UiMgr;", "mUnits", "afterServiceNormalSetting", "", "context", "auxInInfoChange", "btMusicInfoChange", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "album", "artist", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "myToast", "content", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendPhoneCommand", "source", "phone", "setAmplifierSwitch", "value", "sourceSwitchChange", "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoInfoChange", "playMode", "duration", "Companion", "Parser", "VehicleType", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_DATA_MID = 10;
   public static final int AMPLIFIER_DATA_MIN = 1;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String SHARE_112_IS_HAVE_AMPLIFIER = "share_112_is_have_amplifier";
   public static final String TAG = "_112_MsgMgr";
   private int mAmplifierSwitch;
   private int mCanId = -1;
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private Context mContext;
   private final int mDifferent = this.getCurrentCanDifferentId();
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private final SparseArray mParserArray = new SparseArray();
   private HashMap mSettingItemIndexHashMap = new HashMap();
   private final TimerUtil mTimerUtil = new TimerUtil();
   private UiMgr mUiMgr;
   private int mUnits;

   // $FF: synthetic method
   public static void $r8$lambda$1bK4YNCpzFhuKyRM_9V26S1Xd2s() {
      dateTimeRepCanbus$lambda_4();
   }

   // $FF: synthetic method
   public static void $r8$lambda$BWHWWdJqazgtxUdQZmPV_RnCnFI(MsgMgr var0, String var1) {
      myToast$lambda_13(var0, var1);
   }

   private static final void dateTimeRepCanbus$lambda_4() {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0, 0, 0});
   }

   private final void initAmplifier(Context var1) {
      if (var1 != null) {
         int var2 = this.mCanId;
         UiMgr var4 = this.mUiMgr;
         UiMgr var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
            var3 = null;
         }

         this.getAmplifierData(var1, var2, var3.getAmplifierPageUiSet(var1));
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      byte[][] var6 = (byte[][])(new byte[][]{{22, -124, 1, 1}, {22, -124, 2, (byte)GeneralAmplifierData.volume}, {22, -124, 3, (byte)(10 - GeneralAmplifierData.frontRear)}, {22, -124, 4, (byte)(GeneralAmplifierData.leftRight + 10)}, {22, -124, 5, (byte)(GeneralAmplifierData.bandBass + 1)}, {22, -124, 6, (byte)(GeneralAmplifierData.bandTreble + 1)}, {22, -124, 7, (byte)(GeneralAmplifierData.bandMiddle + 1)}});
      TimerUtil var5 = new TimerUtil();
      var5.startTimer((TimerTask)(new TimerTask(var6, var5) {
         final byte[][] $this_run;
         final TimerUtil $this_run$1;
         private int i;

         {
            this.$this_run = var1;
            this.$this_run$1 = var2;
         }

         public final int getI() {
            return this.i;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.$this_run;
            if (var1 < ((Object[])var2).length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.i;
            } else {
               this.$this_run$1.stopTimer();
            }

         }

         public final void setI(int var1) {
            this.i = var1;
         }
      }), 100L, 133L);
      this.mAmplifierSwitch = 1;
      this.mTimerUtil.stopTimer();
      this.mTimerUtil.startTimer((TimerTask)(new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)this.this$0.mAmplifierSwitch});
         }
      }), 100L, 1000L);
   }

   private final void initItemsIndexHashMap(Context var1) {
      Log.i("_112_MsgMgr", "initItems: ");
      UiMgr var5 = this.mUiMgr;
      UiMgr var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      Iterator var7 = var4.getSettingUiSet(var1).getList().iterator();

      int var2;
      int var3;
      for(var2 = 0; var7.hasNext(); ++var2) {
         Iterator var6 = ((SettingPageUiSet.ListBean)var7.next()).getItemList().iterator();

         for(var3 = 0; var6.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)var6.next();
            Map var11 = (Map)this.mSettingItemIndexHashMap;
            String var15 = var8.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var15, "itemListBean.titleSrn");
            var11.put(var15, new SettingUpdateEntity(var2, var3));
         }
      }

      Iterator var10 = var4.getDriverDataPageUiSet(var1).getList().iterator();

      for(var2 = 0; var10.hasNext(); ++var2) {
         Iterator var12 = ((DriverDataPageUiSet.Page)var10.next()).getItemList().iterator();

         for(var3 = 0; var12.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var13 = (DriverDataPageUiSet.Page.Item)var12.next();
            Map var9 = (Map)this.mDriveItemIndexHashMap;
            String var14 = var13.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var14, "item.titleSrn");
            var9.put(var14, new DriverUpdateEntity(var2, var3, "null_value"));
         }
      }

   }

   private final void initParsers(Context var1) {
      SparseArray var2 = this.mParserArray;
      var2.put(7, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         private final String getBcdHigh(int var1) {
            int var3 = var1 >> 4 & 15;
            boolean var2 = false;
            boolean var5 = var2;
            if (10 <= var3) {
               var5 = var2;
               if (var3 < 16) {
                  var5 = true;
               }
            }

            String var4;
            if (var5) {
               var4 = String.valueOf((char)(var3 + 55));
            } else {
               var4 = String.valueOf(var3);
            }

            return var4;
         }

         private final String getBcdLow(int var1) {
            int var3 = var1 & 15;
            boolean var2 = false;
            boolean var5 = var2;
            if (10 <= var3) {
               var5 = var2;
               if (var3 < 16) {
                  var5 = true;
               }
            }

            String var4;
            if (var5) {
               var4 = String.valueOf((char)(var3 + 55));
            } else {
               var4 = String.valueOf(var3);
            }

            return var4;
         }

         private final String getResult(int[] var1, Function1 var2) {
            int var4 = var1.length;

            for(int var3 = 0; var3 < var4; ++var3) {
               if (var1[var3] != 255) {
                  return (String)var2.invoke(var1);
               }
            }

            return "--";
         }

         private final int toInteger(int[] var1) {
            Log.i("_112_MsgMgr", "toInteger: " + var1.length);
            int var5 = var1.length;
            int var3 = 0;
            int var4 = 0;

            for(int var2 = 0; var3 < var5; ++var2) {
               var4 |= var1[var3] << var2 * 8;
               ++var3;
            }

            return var4;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var6 = this.this$0;
               ArrayList var7 = new ArrayList();
               MsgMgr var8 = this.this$0;
               String var5;
               if (var8.mUnits == 1) {
                  var5 = "KM";
               } else {
                  var5 = "MP";
               }

               int[] var11 = var8.mCanbusInfoInt;
               var1 = var11[2];
               int var2;
               int var3;
               Function1 var10;
               switch (var1) {
                  case 1:
                     var5 = "" + this.toInteger(new int[]{var11[4], var11[3]}) + ' ' + var5;
                     break;
                  case 2:
                  case 3:
                  case 7:
                     var2 = var11[4];
                     var3 = var11[3];
                     var10 = (Function1)(new Function1(this, var5) {
                        final String $unit;
                        final <undefinedtype> this$0;

                        {
                           this.this$0 = var1;
                           this.$unit = var2;
                        }

                        public final String invoke(int[] var1) {
                           Intrinsics.checkNotNullParameter(var1, "$this$getResult");
                           return this.this$0.getBcdHigh(var1[1]) + this.this$0.getBcdLow(var1[1]) + '.' + this.this$0.getBcdHigh(var1[0]) + this.this$0.getBcdLow(var1[0]) + " L/100" + this.$unit;
                        }
                     });
                     var5 = this.getResult(new int[]{var2, var3}, var10);
                     break;
                  case 4:
                  case 8:
                     var3 = var11[4];
                     var2 = var11[3];
                     var10 = (Function1)(new Function1(this, var5) {
                        final String $unit;
                        final <undefinedtype> this$0;

                        {
                           this.this$0 = var1;
                           this.$unit = var2;
                        }

                        public final String invoke(int[] var1) {
                           Intrinsics.checkNotNullParameter(var1, "$this$getResult");
                           return "" + this.this$0.toInteger(var1) + ' ' + this.$unit + "/H";
                        }
                     });
                     var5 = this.getResult(new int[]{var3, var2}, var10);
                     break;
                  case 5:
                  case 9:
                     var2 = var11[5];
                     var3 = var11[4];
                     int var4 = var11[3];
                     var10 = (Function1)(new Function1(this, var5) {
                        final String $unit;
                        final <undefinedtype> this$0;

                        {
                           this.this$0 = var1;
                           this.$unit = var2;
                        }

                        public final String invoke(int[] var1) {
                           Intrinsics.checkNotNullParameter(var1, "$this$getResult");
                           return "" + (float)this.this$0.toInteger(var1) / (float)10 + ' ' + this.$unit;
                        }
                     });
                     var5 = this.getResult(new int[]{var2, var3, var4}, var10);
                     break;
                  case 6:
                  case 10:
                     StringCompanionObject var9 = StringCompanionObject.INSTANCE;
                     var5 = String.format("%d:%02d", Arrays.copyOf(new Object[]{var11[3] << 8 | var11[4], var11[5]}, 2));
                     Intrinsics.checkNotNullExpressionValue(var5, "format(format, *args)");
                     break;
                  default:
                     var5 = " ";
               }

               var7.add(new DriverUpdateEntity(1, var1 - 1, var5));
               var6.updateGeneralDriveData((List)var7);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      var2.append(23, new Parser(this, var1) {
         final Context $context;
         private final ArrayList list;
         private int[] mAmplifierDatas;
         private int[] mSettingDatas;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$084KFLRbUJVfD6G9TBY3ZW4V6C8(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$4E4nl7PN32Pd_bkL_GqrmDeMDH8(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$602al_DI0suuul0XdQDJmkxuoHc(Context var0, MsgMgr var1, Object var2) {
            setOnParseListeners$lambda_12(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$EI3O2OeZx2j4xs4kVVbMc7LQhvg(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$J68qCXNcS5Yys7nXMU5f9zGysdE(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$WpNLu8RNhbF_LOaZihURb3QgP2k(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$sVcYQSIFHnB8ilmI3tUeUGV9CZg(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$wB6uLMnv8g_NQ9usJpw2oelKB1E(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         private final boolean isAmplifierDatasChange() {
            int[] var1 = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 3, 9);
            if (Arrays.equals(this.mSettingDatas, var1)) {
               return false;
            } else {
               var1 = Arrays.copyOf(var1, var1.length);
               Intrinsics.checkNotNullExpressionValue(var1, "copyOf(this, size)");
               this.mAmplifierDatas = var1;
               return true;
            }
         }

         private final boolean isSettingDatasChange() {
            int[] var1 = new int[]{this.this$0.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[9] & 127};
            if (Arrays.equals(this.mSettingDatas, var1)) {
               return false;
            } else {
               var1 = Arrays.copyOf(var1, 2);
               Intrinsics.checkNotNullExpressionValue(var1, "copyOf(this, size)");
               this.mSettingDatas = var1;
               return true;
            }
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("amplifier_switch");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2]));
            }

         }

         private static final void setOnParseListeners$lambda_12(Context var0, MsgMgr var1, Object var2) {
            Intrinsics.checkNotNullParameter(var0, "$context");
            Intrinsics.checkNotNullParameter(var1, "this$0");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            boolean var4;
            if ((var1.mCanbusInfoInt[9] >> 7 & 1) == 1) {
               var4 = true;
            } else {
               var4 = false;
            }

            Log.i("_112_MsgMgr", "setOnParseListeners: SHARE_112_IS_HAVE_AMPLIFIER " + var4);
            Unit var5 = Unit.INSTANCE;
            SharePreUtil.setBoolValue(var0, "share_112_is_have_amplifier", var4);
            SettingUpdateEntity var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("_118_setting_title_96");
            if (var6 != null) {
               var2.list.add(var6.setValue(1 & var1.mCanbusInfoInt[9] >> 6));
            }

            var6 = (SettingUpdateEntity)var1.mSettingItemIndexHashMap.get("speed_linkage_volume_adjustment");
            if (var6 != null) {
               int var3 = var1.mCanbusInfoInt[9] & 15;
               var2.list.add(var6.setValue(var3).setProgress(var3));
            }

         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.volume = var0.mCanbusInfoInt[3];
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.frontRear = -(var0.mCanbusInfoInt[4] - 10);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.leftRight = var0.mCanbusInfoInt[5] - 10;
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandBass = var0.mCanbusInfoInt[6] - 1;
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandTreble = var0.mCanbusInfoInt[7] - 1;
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandMiddle = var0.mCanbusInfoInt[8] - 1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               super.parse(var1);
               if (this.isAmplifierDatasChange()) {
                  this.this$0.updateAmplifierActivity((Bundle)null);
                  MsgMgr var2 = this.this$0;
                  var2.saveAmplifierData(this.$context, var2.mCanId);
               }

               if (this.isSettingDatasChange()) {
                  this.this$0.updateGeneralSettingData((List)this.list);
                  this.this$0.updateSettingActivity((Bundle)null);
               }
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$2$$ExternalSyntheticLambda7(this.$context, this.this$0, this)};
         }
      });
      var2.append(39, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var7 = this.this$0;
               Context var6 = this.$context;
               int var3 = var7.mCanbusInfoInt[2];
               String var5 = " ";
               String var4;
               if (var3 == 0) {
                  var4 = "--";
               } else {
                  boolean var2 = false;
                  boolean var8 = var2;
                  if (1 <= var3) {
                     var8 = var2;
                     if (var3 < 256) {
                        var8 = true;
                     }
                  }

                  var4 = var5;
                  if (var8) {
                     var1 = this.this$0.mCanbusInfoInt[3];
                     if (var1 != 0) {
                        if (var1 != 1) {
                           var4 = var5;
                        } else {
                           var4 = this.this$0.mCanbusInfoInt[2] - 40 + this.this$0.getTempUnitF(this.$context);
                        }
                     } else {
                        var4 = (float)(this.this$0.mCanbusInfoInt[2] + 1) / (float)2 - (float)40 + this.this$0.getTempUnitC(this.$context);
                     }
                  }
               }

               var7.updateOutDoorTemp(var6, var4);
            }

         }
      });
      var2.append(32, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void realKeyClick31(int var1) {
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               MsgMgr var2 = this.this$0;
               var2.realKeyClick3_1(this.$context, var1, 0, var2.mCanbusInfoInt[3]);
            }
         }

         private final void realKeyClick32(int var1) {
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               MsgMgr var2 = this.this$0;
               var2.realKeyClick3_2(this.$context, var1, 0, var2.mCanbusInfoInt[3]);
            }
         }

         private final void realKeyLongClick1(int var1) {
            MsgMgr var2 = this.this$0;
            var2.realKeyLongClick1(this.$context, var1, var2.mCanbusInfoInt[3]);
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[2];
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           switch (var1) {
                              case 6:
                                 this.realKeyLongClick1(3);
                                 break;
                              case 7:
                                 this.realKeyLongClick1(2);
                                 break;
                              case 8:
                                 this.realKeyLongClick1(187);
                                 break;
                              case 9:
                                 this.realKeyLongClick1(14);
                                 break;
                              case 10:
                                 this.realKeyLongClick1(15);
                                 break;
                              default:
                                 switch (var1) {
                                    case 18:
                                       this.realKeyLongClick1(49);
                                       break;
                                    case 19:
                                       this.realKeyLongClick1(3);
                                       break;
                                    case 20:
                                       this.realKeyLongClick1(152);
                                       break;
                                    case 21:
                                       this.realKeyLongClick1(50);
                                       break;
                                    case 22:
                                       this.realKeyLongClick1(49);
                                       break;
                                    case 23:
                                       this.realKeyClick31(7);
                                       break;
                                    case 24:
                                       this.realKeyClick31(8);
                                       break;
                                    case 25:
                                       this.realKeyClick32(48);
                                       break;
                                    case 26:
                                       this.realKeyClick32(47);
                                       break;
                                    case 27:
                                       this.realKeyLongClick1(134);
                                 }
                           }
                        } else {
                           this.realKeyLongClick1(45);
                        }
                     } else {
                        this.realKeyLongClick1(46);
                     }
                  } else {
                     this.realKeyLongClick1(8);
                  }
               } else {
                  this.realKeyLongClick1(7);
               }
            } else {
               this.realKeyLongClick1(0);
            }

         }
      });
      var2.append(33, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$CuNn7VpDHsCm0YfiXO7YQfJUlyk(MsgMgr var0) {
            setOnParseListeners$lambda_9(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$W_jYsqySM8QjbIH_KLAd7NndDNg(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$XsaNXX9L3jitzDKyynrZu3XtsiY(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ZfxafaFqI2AnGHTFT29gRGkLc1w(MsgMgr var0, Context var1) {
            setOnParseListeners$lambda_4(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$bLyOWjUE0B9EYpajkbM5Cm84DOo(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$rnE91_detmA5_Zadww_XyNP62do(MsgMgr var0, Context var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ycokkSL5BnTpHs7Grn0gkiLlNA0(MsgMgr var0) {
            setOnParseListeners$lambda_11(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final String setOnParseListeners$getTemperature(MsgMgr var0, Context var1, int var2) {
            String var3;
            if (var2 != 0) {
               if (var2 != 255) {
                  if (GeneralAirData.fahrenheit_celsius) {
                     var3 = var2 + var0.getTempUnitF(var1);
                  } else {
                     var3 = (float)var2 / (float)2 + var0.getTempUnitC(var1);
                  }
               } else {
                  var3 = "HIGH";
               }
            } else {
               var3 = "LOW";
            }

            return var3;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
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
            if ((var1 >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac = var2;
            if ((var1 >> 5 & 1) == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.in_out_cycle = var2;
            if ((var1 >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.auto = var2;
            if ((var1 >> 2 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.sync = var2;
         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[9];
            boolean var2 = true;
            if ((var1 >> 7 & 1) != 1) {
               var2 = false;
            }

            GeneralAirData.steering_wheel_heating = var2;
            GeneralAirData.front_left_seat_cold_level = var1 >> 4 & 7;
            GeneralAirData.front_right_seat_cold_level = var1 & 7;
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
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
            if ((var1 >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_head = var2;
            if ((var1 >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_foot = var2;
            GeneralAirData.front_wind_level = var1 & 15;
            if (GeneralAirData.front_wind_level == 15) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_auto_wind_speed = var2;
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0, Context var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            GeneralAirData.front_left_temperature = setOnParseListeners$getTemperature(var0, var1, var0.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0, Context var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            GeneralAirData.front_right_temperature = setOnParseListeners$getTemperature(var0, var1, var0.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
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
            if ((var1 >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_defog = var2;
            if ((var1 >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac_max = var2;
            if ((var1 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.fahrenheit_celsius = var2;
         }

         private static final void setOnParseListeners$lambda_9(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[7];
            GeneralAirData.front_left_seat_heat_level = var1 >> 4 & 7;
            GeneralAirData.front_right_seat_heat_level = var1 & 7;
         }

         public void parse(int var1) {
            this.this$0.mCanbusInfoInt[3] |= 16;
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
               GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
               GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
               this.this$0.updateAirActivity(this.$context, 1001);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2(this.this$0, this.$context), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda3(this.this$0, this.$context), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda5(this.this$0), null, new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda6(this.this$0)};
         }
      });
      var2.append(34, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$_PMs2B81_JFLyrsL9TzpTYe0MBM(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$0_JTVoJJdKDSEo_Yxqc43O5TGZU(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$JiO2d_mVQFvIkrgWHn7EdnTDHtg(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$RDu4fCMreRWN6A1yFE6u9Ioxj0k(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$WcUpf1_KnQgqW0E7PA4CqU_zcXk(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$v_gLKt8IZ8Cjf5nsxTt96n4mdt4(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            RadarInfoUtil.mMinIsClose = true;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, var0.mCanbusInfoInt[2], 2);
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, var0.mCanbusInfoInt[3], 6);
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, var0.mCanbusInfoInt[4], 6);
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, var0.mCanbusInfoInt[5], 2);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_LEFT_PROBE, var0.mCanbusInfoInt[6], 2);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT_PROBE, var0.mCanbusInfoInt[7], 2);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda5(this.this$0)};
         }
      });
      var2.append(35, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$_rs5FfdkKMqeIOzsGfuKDn4_ErI(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$19ZI6G0JqtdyV0t_oByuZ7BthaA(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$39RSqzgRdQm81UEiW3qQ3lqEKUg(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$7CR4HW3JR46F7a_ZwikYiY1omHs(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$a71I5LEKPBFvS0W12Ws6ooyhFX8(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$l7dZPJk6xQCEgAN2GX_G_Pav_6M(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, var0.mCanbusInfoInt[2], 2);
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, var0.mCanbusInfoInt[3], 4);
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, var0.mCanbusInfoInt[4], 4);
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, var0.mCanbusInfoInt[5], 2);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT_PROBE, var0.mCanbusInfoInt[6], 2);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT_PROBE, var0.mCanbusInfoInt[7], 2);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda5(this.this$0)};
         }
      });
      var2.append(36, new Parser(this, var1) {
         final Context $context;
         private int doorStatus;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final boolean isDoorStatusChange() {
            int var1 = this.this$0.mCanbusInfoInt[2] & 254;
            if (Integer.valueOf(var1).equals(this.doorStatus)) {
               return false;
            } else {
               this.doorStatus = var1;
               return true;
            }
         }

         public void parse(int var1) {
            if (this.isDoorStatusChange()) {
               var1 = this.this$0.mCanbusInfoInt[2];
               boolean var3 = true;
               boolean var2;
               if ((var1 >> 7 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightFrontDoorOpen = var2;
               if ((var1 >> 6 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftFrontDoorOpen = var2;
               if ((var1 >> 5 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightRearDoorOpen = var2;
               if ((var1 >> 4 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftRearDoorOpen = var2;
               if ((var1 & 10) != 0) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isBackOpen = var2;
               if ((var1 >> 2 & 1) == 1) {
                  var2 = var3;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isFrontOpen = var2;
               this.this$0.updateDoorView(this.$context);
            }

         }
      });
      var2.append(41, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 4600, 16);
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var2.append(48, new Parser(this, var1) {
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
      var2.append(64, new Parser(this, var1) {
         final Context $context;
         private int brakeMessage;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         private final void updateItem(String var1, Object var2) {
            SettingUpdateEntity var3 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get(var1);
            if (var3 != null) {
               this.list.add(var3.setValue(var2));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               int[] var5 = this.this$0.mCanbusInfoInt;
               MsgMgr var7 = this.this$0;
               Context var6 = this.$context;
               int var2 = 2;
               int var4 = var5[2];
               boolean var8 = true;
               if (var4 == 0) {
                  this.updateItem("language_setup", var5[3] - 1);
               } else if (var4 != 1) {
                  if (var4 == 3) {
                     this.updateItem("_276_setting_0", var5[3] & 1);
                  } else if (var4 != 48) {
                     int var3 = 0;
                     byte var9;
                     if (var4 == 64) {
                        var1 = var5[3];
                        if (var1 != 30) {
                           if (var1 != 60) {
                              if (var1 != 90) {
                                 var9 = 0;
                              } else {
                                 var9 = 3;
                              }
                           } else {
                              var9 = 2;
                           }
                        } else {
                           var9 = 1;
                        }

                        this.updateItem("_118_setting_title_9", Integer.valueOf(var9));
                        var3 = var5[4];
                        if (var3 != 3) {
                           var9 = (byte)var2;
                           if (var3 != 20) {
                              if (var3 != 40) {
                                 var9 = 0;
                              } else {
                                 var9 = 3;
                              }
                           }
                        } else {
                           var9 = 1;
                        }

                        this.updateItem("hiworld_jeep_123_0x62_data2_54", Integer.valueOf(var9));
                        this.updateItem("_118_setting_title_19", var5[5] & 1);
                        this.updateItem("_112_headunit_power_off_mode", var5[5] >> 1 & 1);
                     } else if (var4 != 96) {
                        if (var4 == 144) {
                           this.updateItem("seat_auto_heat", var5[3] & 3);
                        } else if (var4 != 192) {
                           if (var4 != 208) {
                              if (var4 != 32) {
                                 if (var4 != 33) {
                                    if (var4 != 160) {
                                       if (var4 == 161) {
                                          this.updateItem("_118_setting_title_34", var5[3] >> 6 & 3);
                                          this.updateItem("_118_setting_title_30", var5[3] >> 3 & 1);
                                          this.updateItem("_118_setting_title_29", var5[3] & 3);
                                          this.updateItem("_112_offset_dist_alarm", var5[4] >> 6 & 3);
                                          this.updateItem("_112_offset_dist_alarm_vol", var5[4] >> 4 & 3);
                                       }
                                    } else {
                                       this.updateItem("hiworld_jeep_123_0x43_data3_76", var5[3] & 1);
                                       this.updateItem("hiworld_jeep_123_0x43_data5_4", var5[3] >> 1 & 1);
                                       this.updateItem("hiworld_jeep_123_0x43_data4_3", var5[3] >> 2 & 1);
                                       this.updateItem("hiworld_jeep_123_0x43_data4_2", var5[3] >> 3 & 1);
                                       this.updateItem("hiworld_jeep_123_0x43_data4_6", var5[3] >> 4 & 1);
                                       this.updateItem("hiworld_jeep_123_0x43_data4_10", var5[3] >> 6 & 3);
                                       this.updateItem("hiworld_jeep_123_0x43_data5_10", var5[4] & 3);
                                       this.updateItem("hiworld_jeep_123_0x43_data5_32", var5[4] >> 2 & 3);
                                       this.updateItem("hiworld_jeep_123_0x43_data3_0", var5[4] >> 4 & 1);
                                       this.updateItem("hiworld_jeep_123_0x43_data3_1", var5[4] >> 5 & 3);
                                       this.updateItem("_118_setting_title_4", var5[4] >> 7 & 1);
                                       this.updateItem("hiworld_jeep_123_0x43_data3_54", var5[5] & 3);
                                       this.updateItem("hiworld_jeep_123_0x43_data4_5", var5[5] >> 2 & 1);
                                       this.updateItem("_112_rear_park_assist", var5[5] >> 3 & 1);
                                       this.updateItem("_112_auto_park_assist", var5[5] >> 4 & 1);
                                       this.updateItem("_118_setting_title_65", var5[5] >> 5 & 1);
                                       this.updateItem("_176_car_setting_title_22", var5[5] >> 6 & 3);
                                    }
                                 } else {
                                    this.updateItem("hiworld_jeep_123_0x62_data3_765", var5[3] & 15);
                                 }
                              } else {
                                 this.updateItem("_112_adaptive_high_beam", var5[3] >> 7 & 1);
                                 var1 = var5[3] & 127;
                                 if (var1 != 30) {
                                    if (var1 != 60) {
                                       if (var1 != 90) {
                                          var9 = 0;
                                       } else {
                                          var9 = 3;
                                       }
                                    } else {
                                       var9 = 2;
                                    }
                                 } else {
                                    var9 = 1;
                                 }

                                 this.updateItem("hiworld_jeep_123_0x62_data2_10", Integer.valueOf(var9));
                                 this.updateItem("hiworld_jeep_123_0x62_data3_4", var5[4] >> 7 & 1);
                                 var1 = var5[4] & 127;
                                 if (var1 != 30) {
                                    if (var1 != 60) {
                                       if (var1 != 90) {
                                          var9 = (byte)var3;
                                       } else {
                                          var9 = 3;
                                       }
                                    } else {
                                       var9 = 2;
                                    }
                                 } else {
                                    var9 = 1;
                                 }

                                 this.updateItem("hiworld_jeep_123_0x62_data2_32", Integer.valueOf(var9));
                                 this.updateItem("hiworld_jeep_123_0x62_data3_3", var5[5] & 1);
                                 this.updateItem("_112_cornering_lights", var5[5] >> 1 & 1);
                                 this.updateItem("hiworld_jeep_123_0x62_data3_0", var5[5] >> 2 & 1);
                                 this.updateItem("_112_rear_mirror_dimmer", var5[5] >> 3 & 1);
                                 this.updateItem("_112_auto_anti_glare", var5[5] >> 4 & 1);
                                 this.updateItem("hiworld_jeep_123_0x62_data2_76", var5[5] >> 5 & 3);
                                 SettingUpdateEntity var11 = (SettingUpdateEntity)var7.mSettingItemIndexHashMap.get("hiworld_jeep_123_0x62_data2_76");
                                 if (var11 != null) {
                                    var1 = var5[5] >> 5 & 3;
                                    this.list.add(var11.setValue(var1 + 1).setProgress(var1));
                                 }

                                 this.updateItem("_250_welcome_light", var5[5] >> 7 & 1);
                              }
                           } else {
                              this.updateItem("_118_setting_title_24", var5[3] & 1);
                              this.updateItem("_118_setting_title_26", var5[3] >> 1 & 1);
                              this.updateItem("_118_setting_title_27", var5[3] >> 2 & 1);
                              this.updateItem("_118_setting_title_28", var5[3] >> 3 & 1);
                              this.updateItem("_118_setting_title_25", var5[3] >> 4 & 1);
                              this.updateItem("_112_auto_suspension", var5[3] >> 5 & 1);
                           }
                        } else {
                           this.updateItem("_118_setting_title_37", var5[3] & 1);
                           this.updateItem("_118_setting_title_38", var5[3] >> 1 & 1);
                           var2 = this.brakeMessage;
                           var3 = var5[4];
                           if (var2 != var3) {
                              this.brakeMessage = var3;
                              if (1 > var3 || var3 >= 9) {
                                 var8 = false;
                              }

                              if (var8) {
                                 GeneralDisplayMsgData.displayMsg = CommUtil.getStrByResId(var6, "_112_brake_message_" + this.brakeMessage);
                              }

                              var7.sendDisplayMsgView(var6);
                           }
                        }
                     } else {
                        this.updateItem("_112_aux1_type", var5[3] >> 7 & 1);
                        this.updateItem("_112_aux1_power", var5[3] >> 6 & 1);
                        this.updateItem("_112_aux1_recalled", var5[3] >> 5 & 1);
                        this.updateItem("_112_aux2_type", var5[3] >> 3 & 3);
                        this.updateItem("_112_aux2_power", var5[3] >> 2 & 1);
                        this.updateItem("_112_aux2_recalled", var5[3] >> 1 & 1);
                        this.updateItem("_112_aux3_type", var5[4] >> 7 & 1);
                        this.updateItem("_112_aux3_power", var5[4] >> 6 & 1);
                        this.updateItem("_112_aux3_recalled", var5[4] >> 5 & 1);
                        this.updateItem("_112_aux4_type", var5[4] >> 3 & 3);
                        this.updateItem("_112_aux4_power", var5[4] >> 2 & 1);
                        this.updateItem("_112_aux4_recalled", var5[4] >> 1 & 1);
                     }
                  } else {
                     this.updateItem("hiworld_jeep_123_0x60_data1_0", var5[3] & 1);
                     this.updateItem("hiworld_jeep_123_0x60_data1_1", var5[3] >> 1 & 1);
                     this.updateItem("_118_setting_title_74", var5[3] >> 2 & 1);
                     this.updateItem("hiworld_jeep_123_0x60_data1_65", var5[3] >> 3 & 3);
                     this.updateItem("_118_setting_title_18", var5[3] >> 5 & 1);
                     this.updateItem("hiworld_jeep_123_0x60_data1_4", var5[3] >> 7 & 1);
                     this.updateItem("_118_setting_title_16", var5[4] & 1);
                     this.updateItem("_213_car_set14_2_0", var5[4] >> 1 & 1);
                     this.updateItem("hiworld_jeep_123_0x60_data1_3", var5[4] >> 2 & 1);
                     this.updateItem("_112_horn_remote_start", var5[5] & 1);
                     this.updateItem("remote_door_unlock", var5[5] >> 1 & 1);
                  }
               } else {
                  Integer var10 = var5[3] & 1;
                  var7.mUnits = ((Number)var10).intValue();
                  Unit var12 = Unit.INSTANCE;
                  this.updateItem("hiworld_jeep_123_0xC1_data3", var10);
                  this.updateItem("hiworld_jeep_123_0xC1_data2", var5[3] >> 1 & 3);
                  this.updateItem("hiworld_jeep_123_0xCA_0X01", var5[3] >> 3 & 1);
                  this.updateItem("hiworld_jeep_123_0xC1_data1", var5[3] >> 4 & 1);
                  this.updateItem("hiworld_jeep_123_0xCA_0X08", var5[3] >> 5 & 3);
                  this.updateItem("_118_setting_title_81", var5[3] >> 7 & 1);
               }

               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var2.append(66, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            GeneralOriginalCarDeviceData.cdStatus = "CD";
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               Context var5 = this.$context;
               var1 = this.this$0.mCanbusInfoInt[2];
               boolean var3 = true;
               String var4;
               if (var1 != 0) {
                  if (var1 != 1) {
                     if (var1 != 2) {
                        if (var1 != 3) {
                           if (var1 != 4) {
                              if (var1 != 5) {
                                 var4 = "null_value";
                              } else {
                                 var4 = "_118_setting_value_93";
                              }
                           } else {
                              var4 = "_118_setting_value_94";
                           }
                        } else {
                           var4 = "_118_setting_value_90";
                        }
                     } else {
                        var4 = "_118_setting_value_92";
                     }
                  } else {
                     var4 = "_118_setting_value_91";
                  }
               } else {
                  var4 = "_118_setting_value_89";
               }

               GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var5, var4);
               boolean var2;
               if ((this.this$0.mCanbusInfoInt[3] & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralOriginalCarDeviceData.rpt = var2;
               if ((this.this$0.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                  var2 = var3;
               } else {
                  var2 = false;
               }

               GeneralOriginalCarDeviceData.rdm = var2;
               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      var2.append(67, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               ArrayList var4 = new ArrayList();
               MsgMgr var2 = this.this$0;
               StringBuilder var3 = (new StringBuilder()).append(var2.mCanbusInfoInt[2] << 8 | var2.mCanbusInfoInt[3]).append(" / ");
               var1 = var2.mCanbusInfoInt[10];
               var4.add(new OriginalCarDeviceUpdateEntity(3, var3.append(var2.mCanbusInfoInt[11] | var1 << 8).toString()));
               GeneralOriginalCarDeviceData.mList = (List)var4;
               StringCompanionObject var5 = StringCompanionObject.INSTANCE;
               String var6 = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{this.this$0.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5], this.this$0.mCanbusInfoInt[6]}, 3));
               Intrinsics.checkNotNullExpressionValue(var6, "format(format, *args)");
               GeneralOriginalCarDeviceData.endTime = var6;
               var5 = StringCompanionObject.INSTANCE;
               var6 = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{this.this$0.mCanbusInfoInt[7], this.this$0.mCanbusInfoInt[8], this.this$0.mCanbusInfoInt[9]}, 3));
               Intrinsics.checkNotNullExpressionValue(var6, "format(format, *args)");
               GeneralOriginalCarDeviceData.startTime = var6;
               var1 = this.this$0.mCanbusInfoInt[4] * 60 * 60 + this.this$0.mCanbusInfoInt[5] * 60 + this.this$0.mCanbusInfoInt[6];
               if (var1 == 0) {
                  GeneralOriginalCarDeviceData.progress = 0;
               } else {
                  GeneralOriginalCarDeviceData.progress = (this.this$0.mCanbusInfoInt[7] * 60 * 60 + this.this$0.mCanbusInfoInt[8] * 60 + this.this$0.mCanbusInfoInt[9]) * 100 / var1;
               }

               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      var2.append(68, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            GeneralOriginalCarDeviceData.songList = (List)(new ArrayList());
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               byte[] var2 = this.this$0.mCanbusInfoByte;
               var2 = ArraysKt.copyOfRange(var2, 6, var2.length);
               Charset var3 = StandardCharsets.UTF_16;
               Intrinsics.checkNotNullExpressionValue(var3, "UTF_16");
               String var5 = new String(var2, var3);
               if (this.this$0.mCanbusInfoInt[2] == 4) {
                  List var4 = GeneralOriginalCarDeviceData.songList;
                  MsgMgr var6 = this.this$0;
                  var1 = var6.mCanbusInfoInt[4];
                  var1 = (var6.mCanbusInfoInt[5] | var1 << 8) - 1;
                  if (var1 < var4.size()) {
                     ((SongListEntity)var4.get(var1)).setTitle(var5);
                  } else {
                     var4.add(new SongListEntity(var5));
                  }
               } else {
                  ArrayList var7 = new ArrayList();
                  var7.add(new OriginalCarDeviceUpdateEntity(this.this$0.mCanbusInfoInt[2] - 1, var5));
                  GeneralOriginalCarDeviceData.mList = (List)var7;
               }

               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      var2.append(40, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            var2.updateSpeedInfo(var2.mCanbusInfoInt[3] << 8 | this.this$0.mCanbusInfoInt[2]);
            if (this.isDataChange()) {
               var2 = this.this$0;
               ArrayList var4 = new ArrayList();
               MsgMgr var3 = this.this$0;
               var4.add(new DriverUpdateEntity(0, 0, (var3.mCanbusInfoInt[3] << 8 | var3.mCanbusInfoInt[2]) + " KM/H"));
               StringBuilder var5 = new StringBuilder();
               var1 = var3.mCanbusInfoInt[5];
               var4.add(new DriverUpdateEntity(0, 1, var5.append(var3.mCanbusInfoInt[4] | var1 << 8).append(" RPM").toString()));
               var2.updateGeneralDriveData((List)var4);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
   }

   private static final void myToast$lambda_13(MsgMgr var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$content");
      Toast.makeText(var0.mContext, (CharSequence)var1, 0).show();
   }

   private final void sendPhoneCommand(int var1, byte[] var2) {
      byte var3 = (byte)var1;
      var2 = ArraysKt.plus(new byte[]{22, 112, 1}, var2);
      byte[][] var5 = (byte[][])(new byte[][]{{22, -64, 5, -1, 0, 0, 0, 0, 0, 0}, {22, -64, var3, -1, 0, 0, 0, 0, 0, 0}, var2});
      TimerUtil var4 = new TimerUtil();
      var4.startTimer((TimerTask)(new TimerTask(var5, var4) {
         final byte[][] $this_run;
         final TimerUtil $this_run$1;
         private int count;

         {
            this.$this_run = var1;
            this.$this_run$1 = var2;
         }

         public final int getCount() {
            return this.count;
         }

         public void run() {
            int var1 = this.count;
            byte[][] var2 = this.$this_run;
            if (var1 < ((Object[])var2).length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.count;
            } else {
               this.$this_run$1.stopTimer();
            }

         }

         public final void setCount(int var1) {
            this.count = var1;
         }
      }), 0L, 100L);
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      AppEnableUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.UiMgr");
      this.mUiMgr = (UiMgr)var2;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
   }

   public void auxInInfoChange() {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, -1, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneCommand(67, new byte[]{32});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      this.sendPhoneCommand(65, var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      this.sendPhoneCommand(66, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "getByteArrayToIntArray(canbusInfo)");
      this.mCanbusInfoInt = var3;
      this.mCanbusInfoByte = var2;
      Parser var4 = (Parser)this.mParserArray.get(var3[1]);
      if (var4 != null) {
         var4.parse(this.mCanbusInfoInt.length - 2);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)var2, (byte)var3, (byte)var4, (byte)DataHandleUtils.setOneBit(var8, 7, var10 ^ 1), (byte)var6, 0});
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda1(), 100L);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      if (var7 == 1 || var7 == 5) {
         var4 = var5;
      }

      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 16, 17, (byte)(var4 >> 8 & 255), (byte)(var4 & 255), (byte)(var6 >> 8 & 255), (byte)(var6 & 255), (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Intrinsics.checkNotNullParameter(var21, "songTitle");
      Intrinsics.checkNotNullParameter(var22, "songAlbum");
      Intrinsics.checkNotNullParameter(var23, "songArtist");
      Context var25 = this.mContext;
      var12 = SourceConstantsDef.SOURCE_ID.MUSIC.name();
      if (var1 == 9) {
         var1 = 9;
      } else {
         var1 = 8;
      }

      this.sendMediaMsg(var25, var12, new byte[]{22, -64, (byte)var1, 17, (byte)var3, var9, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), var6, var7});
   }

   public final void myToast(String var1) {
      Intrinsics.checkNotNullParameter(var1, "content");
      this.runOnUi(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      Intrinsics.checkNotNullParameter(var2, "currBand");
      Intrinsics.checkNotNullParameter(var3, "currentFreq");
      Intrinsics.checkNotNullParameter(var4, "psName");
      CharSequence var8 = (CharSequence)var2;
      int var6;
      Triple var7;
      byte var9;
      if (StringsKt.contains$default(var8, (CharSequence)"FM", false, 2, (Object)null)) {
         label40: {
            var6 = (int)(Float.parseFloat(var3) * (float)100);
            switch (var2) {
               case "FM1":
                  var9 = 1;
                  break label40;
               case "FM2":
                  var9 = 2;
                  break label40;
               case "FM3":
                  var9 = 3;
                  break label40;
            }

            var9 = 0;
         }

         var7 = new Triple(Integer.valueOf(var9), var6 & 255, var6 >> 8 & 255);
      } else {
         if (!StringsKt.contains$default(var8, (CharSequence)"AM", false, 2, (Object)null)) {
            return;
         }

         var6 = Integer.parseInt(var3);
         if (Intrinsics.areEqual((Object)var2, (Object)"AM1")) {
            var9 = 17;
         } else if (Intrinsics.areEqual((Object)var2, (Object)"AM2")) {
            var9 = 18;
         } else {
            var9 = 0;
         }

         var7 = new Triple(Integer.valueOf(var9), var6 & 255, var6 >> 8 & 255);
      }

      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, (byte)((Number)var7.getFirst()).intValue(), (byte)((Number)var7.getSecond()).intValue(), (byte)((Number)var7.getThird()).intValue(), (byte)var1, 0, 0});
   }

   public final void setAmplifierSwitch(int var1) {
      this.mAmplifierSwitch = var1;
   }

   public void sourceSwitchChange(String var1) {
      Context var2 = this.mContext;
      ContentResolver var3;
      if (var2 != null) {
         var3 = var2.getContentResolver();
      } else {
         var3 = null;
      }

      System.putString(var3, "reportAfterHangUp", "");
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      Context var18 = this.mContext;
      var8 = SourceConstantsDef.SOURCE_ID.VIDEO.name();
      if (var1 == 9) {
         var1 = 9;
      } else {
         var1 = 8;
      }

      this.sendMediaMsg(var18, var8, new byte[]{22, -64, (byte)var1, 17, (byte)var3, var9, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), var6, var7});
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/car/_112/MsgMgr$Companion;", "", "()V", "AMPLIFIER_DATA_MID", "", "AMPLIFIER_DATA_MIN", "SHARE_112_IS_HAVE_AMPLIFIER", "", "TAG", "CanBusInfo_release"},
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
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/canbus/car/_112/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_112/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
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
         Log.i("_112_MsgMgr", "Parser: " + var2 + " length " + var3);
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
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"},
      d2 = {"Lcom/hzbhd/canbus/car/_112/MsgMgr$VehicleType;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "JEEP_CHEROKEE_2016", "JEEP_CHEROKEE_2018", "JEEP_RENEGADE_2016", "FIAT_AEGEA_2016", "FIAT_AEGEA_2018", "JEEP_COMPASS_2017", "JEEP_COMPASS_2018", "FIAT_DOBLO_2015", "JEEP_GRAND_CHEROKEE_2014", "JEEP_RENEGADE_2018", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum VehicleType {
      private static final VehicleType[] $VALUES = $values();
      FIAT_AEGEA_2016(3),
      FIAT_AEGEA_2018(13),
      FIAT_DOBLO_2015(5),
      JEEP_CHEROKEE_2016(1),
      JEEP_CHEROKEE_2018(11),
      JEEP_COMPASS_2017(4),
      JEEP_COMPASS_2018(14),
      JEEP_GRAND_CHEROKEE_2014(6),
      JEEP_RENEGADE_2016(2),
      JEEP_RENEGADE_2018(7);

      private final int value;

      // $FF: synthetic method
      private static final VehicleType[] $values() {
         return new VehicleType[]{JEEP_CHEROKEE_2016, JEEP_CHEROKEE_2018, JEEP_RENEGADE_2016, FIAT_AEGEA_2016, FIAT_AEGEA_2018, JEEP_COMPASS_2017, JEEP_COMPASS_2018, FIAT_DOBLO_2015, JEEP_GRAND_CHEROKEE_2014, JEEP_RENEGADE_2018};
      }

      private VehicleType(int var3) {
         this.value = var3;
      }

      public final int getValue() {
         return this.value;
      }
   }
}
