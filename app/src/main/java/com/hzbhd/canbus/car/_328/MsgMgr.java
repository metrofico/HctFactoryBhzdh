package com.hzbhd.canbus.car._328;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
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
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b%\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0019\u0018\u0000 n2\u00020\u0001:\u0002noB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000fH\u0016J \u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\bH\u0016J \u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J \u0010-\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J \u0010.\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J \u0010/\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J\u001a\u00100\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000f2\u0006\u00101\u001a\u00020\u000bH\u0016J\u001a\u00102\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010\u000f2\u0006\u00103\u001a\u00020\u0006H\u0016Jp\u00104\u001a\u00020#2\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u00162\u0006\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u0006H\u0016J\u0012\u0010B\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010C\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010D\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010E\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000fH\u0002J\b\u0010F\u001a\u00020#H\u0016J¾\u0001\u0010G\u001a\u00020#2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020I2\u0006\u0010N\u001a\u00020I2\u0006\u0010O\u001a\u00020I2\u0006\u0010P\u001a\u00020I2\u0006\u0010Q\u001a\u00020I2\u0006\u0010R\u001a\u00020I2\b\u0010S\u001a\u0004\u0018\u00010\b2\b\u0010T\u001a\u0004\u0018\u00010\b2\b\u0010U\u001a\u0004\u0018\u00010\b2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020I2\u0006\u0010Y\u001a\u00020\u00062\u0006\u0010Z\u001a\u00020\u00162\u0006\u0010[\u001a\u00020W2\u0006\u0010\\\u001a\u00020\b2\u0006\u0010]\u001a\u00020\b2\u0006\u0010^\u001a\u00020\b2\u0006\u0010_\u001a\u00020\u0016H\u0016J0\u0010`\u001a\u00020#2\u0006\u0010a\u001a\u00020\u00062\u0006\u0010b\u001a\u00020\b2\u0006\u0010c\u001a\u00020\b2\u0006\u0010d\u001a\u00020\b2\u0006\u0010e\u001a\u00020\u0006H\u0016J\u000e\u0010f\u001a\u00020#2\u0006\u0010g\u001a\u00020\u0006J\u0010\u0010h\u001a\u00020#2\u0006\u0010i\u001a\u00020\u0016H\u0016J\u0016\u0010j\u001a\u00020#2\u0006\u0010&\u001a\u00020\b2\u0006\u0010g\u001a\u00020\u001eJ\u0098\u0001\u0010k\u001a\u00020#2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020I2\u0006\u0010N\u001a\u00020I2\u0006\u0010O\u001a\u00020I2\b\u0010P\u001a\u0004\u0018\u00010\b2\u0006\u0010Q\u001a\u00020I2\u0006\u0010R\u001a\u00020I2\b\u0010S\u001a\u0004\u0018\u00010\b2\b\u0010T\u001a\u0004\u0018\u00010\b2\b\u0010U\u001a\u0004\u0018\u00010\b2\u0006\u0010V\u001a\u00020\u00062\u0006\u0010l\u001a\u00020I2\u0006\u0010Z\u001a\u00020\u00162\u0006\u0010m\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u001aR\u00020\u00000\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006p"},
   d2 = {"Lcom/hzbhd/canbus/car/_328/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mAmpTimerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "mAmplifierSwitch", "", "mBtMusicSongTitleRecord", "", "mCanId", "mCanbusInfoByte", "", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mIsPhoneIncoming", "", "mMeidaSongTitleRecord", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_328/MsgMgr$Parser;", "mRadioInfoCommand", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_328/UiMgr;", "mVersionReqCommand", "afterServiceNormalSetting", "", "context", "btMusicId3InfoChange", "title", "artist", "album", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "customLongClick", "key", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "musicDestroy", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "setAmplifierSwitch", "value", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettings", "videoInfoChange", "playMode", "duration", "Companion", "Parser", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_DATA_BASS_MIN = 2;
   public static final int AMPLIFIER_DATA_FAD_MID = 11;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_330_MsgMgr";
   private final TimerUtil mAmpTimerUtil = new TimerUtil();
   private int mAmplifierSwitch;
   private String mBtMusicSongTitleRecord = "";
   private int mCanId = -1;
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private Context mContext;
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private boolean mIsPhoneIncoming;
   private String mMeidaSongTitleRecord = "";
   private final SparseArray mParserArray = new SparseArray();
   private final byte[] mRadioInfoCommand = new byte[]{22, -64, 1, 0, 0, 0, 0};
   private HashMap mSettingItemIndexHashMap = new HashMap();
   private UiMgr mUiMgr;
   private final byte[] mVersionReqCommand = new byte[]{22, -112, 127, 0};

   // $FF: synthetic method
   public static void $r8$lambda$aep0vbvVoSBJK7mCn4tVj2Yh32E(MsgMgr var0, byte[] var1, byte[] var2) {
      musicInfoChange$lambda_13$lambda_12$lambda_11(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$hE2IO8zaaDrk4_9aVLJ7_iwrlJA(MsgMgr var0) {
      dateTimeRepCanbus$lambda_9(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$o5eI1SP64NhFuHsH9kgt2rZlA1g(MsgMgr var0, byte[] var1, byte[] var2) {
      btMusicId3InfoChange$lambda_17$lambda_16$lambda_15(var0, var1, var2);
   }

   private static final void btMusicId3InfoChange$lambda_17$lambda_16$lambda_15(MsgMgr var0, byte[] var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$this_run");
      Intrinsics.checkNotNullParameter(var2, "$it");
      var0.sendMediaMsg(var0.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), ArraysKt.plus(var1, ArraysKt.copyOfRange(var2, 2, var2.length)));
   }

   private static final void dateTimeRepCanbus$lambda_9(MsgMgr var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      CanbusMsgSender.sendMsg(var0.mVersionReqCommand);
   }

   private final void initAmplifier(Context var1) {
      if (var1 != null) {
         int var5 = this.mCanId;
         UiMgr var7 = this.mUiMgr;
         UiMgr var6 = var7;
         if (var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
            var6 = null;
         }

         this.getAmplifierData(var1, var5, var6.getAmplifierPageUiSet(var1));
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.setAmplifierSwitch(1);
      this.mAmpTimerUtil.stopTimer();
      this.mAmpTimerUtil.startTimer((TimerTask)(new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)this.this$0.mAmplifierSwitch});
         }
      }), 100L, 1000L);
      byte[] var8 = new byte[]{22, -124, 1, (byte)(11 - GeneralAmplifierData.frontRear)};
      byte var4 = (byte)(GeneralAmplifierData.leftRight + 11);
      byte[] var10 = new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.bandBass + 2)};
      byte[] var11 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandTreble + 2)};
      byte var3 = (byte)(GeneralAmplifierData.bandMiddle + 2);
      byte var2 = (byte)GeneralAmplifierData.volume;
      Iterator var12 = ArrayIteratorKt.iterator((Object[])(new byte[][]{var8, {22, -124, 2, var4}, var10, var11, {22, -124, 6, var3}, {22, -124, 8, var2}}));
      TimerUtil var9 = new TimerUtil();
      var9.startTimer((TimerTask)(new TimerTask(var12, var9) {
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
      }), 200L, 100L);
   }

   private final void initItemsIndexHashMap(Context var1) {
      Log.i("_330_MsgMgr", "initItems: ");
      UiMgr var5 = this.mUiMgr;
      UiMgr var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      Iterator var7 = var4.getSettingUiSet(var1).getList().iterator();

      int var2;
      int var3;
      Map var11;
      for(var2 = 0; var7.hasNext(); ++var2) {
         Iterator var6 = ((SettingPageUiSet.ListBean)var7.next()).getItemList().iterator();

         for(var3 = 0; var6.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)var6.next();
            var11 = (Map)this.mSettingItemIndexHashMap;
            String var14 = var8.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var14, "itemListBean.titleSrn");
            var11.put(var14, new SettingUpdateEntity(var2, var3));
         }
      }

      Iterator var9 = var4.getDriverDataPageUiSet(var1).getList().iterator();

      for(var2 = 0; var9.hasNext(); ++var2) {
         Iterator var10 = ((DriverDataPageUiSet.Page)var9.next()).getItemList().iterator();

         for(var3 = 0; var10.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var12 = (DriverDataPageUiSet.Page.Item)var10.next();
            var11 = (Map)this.mDriveItemIndexHashMap;
            String var13 = var12.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var13, "item.titleSrn");
            var11.put(var13, new DriverUpdateEntity(var2, var3, "null_value"));
         }
      }

   }

   private final void initParsers(Context var1) {
      SparseArray var2 = this.mParserArray;
      var2.put(32, new Parser(this, var1) {
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
            if (var1 != 18) {
               if (var1 != 21) {
                  if (var1 != 22) {
                     switch (var1) {
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
                           this.realKeyLongClick1(45);
                           break;
                        case 4:
                           this.realKeyLongClick1(46);
                           break;
                        case 5:
                           this.realKeyLongClick1(45);
                           break;
                        case 6:
                           this.realKeyLongClick1(46);
                           break;
                        case 7:
                           this.realKeyLongClick1(2);
                           break;
                        case 8:
                           this.realKeyLongClick1(3);
                           break;
                        case 9:
                           this.realKeyLongClick1(14);
                           break;
                        case 10:
                           this.realKeyLongClick1(15);
                           break;
                        default:
                           switch (var1) {
                              case 129:
                                 this.realKeyLongClick1(134);
                                 break;
                              case 130:
                                 this.realKeyClick31(7);
                                 break;
                              case 131:
                                 this.realKeyClick31(8);
                                 break;
                              case 132:
                                 this.realKeyLongClick1(49);
                                 break;
                              case 133:
                                 this.realKeyClick32(48);
                                 break;
                              case 134:
                                 this.realKeyClick32(47);
                                 break;
                              case 135:
                                 this.realKeyLongClick1(76);
                                 break;
                              case 136:
                                 this.realKeyLongClick1(4);
                                 break;
                              case 137:
                                 this.realKeyLongClick1(128);
                                 break;
                              case 138:
                                 this.realKeyLongClick1(52);
                                 break;
                              case 139:
                                 this.realKeyLongClick1(52);
                                 break;
                              case 140:
                                 this.realKeyLongClick1(58);
                                 break;
                              case 141:
                                 if (this.this$0.mCanbusInfoInt[3] == 1) {
                                    this.this$0.startMainActivity(this.$context);
                                 }
                                 break;
                              case 142:
                                 this.realKeyLongClick1(152);
                                 break;
                              case 143:
                                 if (this.this$0.mCanbusInfoInt[3] == 1) {
                                    Context var3 = this.$context;
                                    Intent var2 = new Intent();
                                    var2.setComponent(Constant.Launcher);
                                    var2.setFlags(268435456);
                                    var2.putExtra("keycode", 284);
                                    var3.startActivity(var2);
                                 }
                           }
                     }
                  } else {
                     this.realKeyLongClick1(49);
                  }
               } else {
                  this.realKeyLongClick1(50);
               }
            } else {
               this.realKeyLongClick1(187);
            }

         }
      });
      var2.append(23, new Parser(this, var1) {
         final Context $context;
         private final int[] amplifierData;
         private final ArrayList list;
         private final int[] settingData;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
            this.amplifierData = new int[6];
            this.settingData = new int[8];
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               int[] var5 = ArraysKt.plus(ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 2, 4), ArraysKt.plus(ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 5, 8), this.this$0.mCanbusInfoInt[9]));
               MsgMgr var2 = this.this$0;
               Context var3 = this.$context;
               int[] var4;
               if (!Arrays.equals(var5, this.amplifierData)) {
                  var4 = this.amplifierData;
                  ArraysKt.copyInto$default(var5, var4, 0, 0, var4.length, 6, (Object)null);
                  GeneralAmplifierData.frontRear = 11 - var2.mCanbusInfoInt[2];
                  GeneralAmplifierData.leftRight = var2.mCanbusInfoInt[3] - 11;
                  GeneralAmplifierData.bandBass = var2.mCanbusInfoInt[5] - 2;
                  GeneralAmplifierData.bandTreble = var2.mCanbusInfoInt[6] - 2;
                  GeneralAmplifierData.bandMiddle = var2.mCanbusInfoInt[7] - 2;
                  GeneralAmplifierData.volume = var2.mCanbusInfoInt[9];
                  var2.updateAmplifierActivity((Bundle)null);
                  var2.saveAmplifierData(var3, var2.mCanId);
               }

               var1 = this.this$0.mCanbusInfoInt[4];
               var4 = ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(new int[0], var1), this.this$0.mCanbusInfoInt[8]), ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 10, this.this$0.mCanbusInfoInt.length));
               var2 = this.this$0;
               if (!Arrays.equals(var4, this.settingData)) {
                  int[] var6 = this.settingData;
                  ArraysKt.copyInto$default(var4, var6, 0, 0, var6.length, 6, (Object)null);
                  this.list.clear();
                  SettingUpdateEntity var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("outlander_simple_car_set_17");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[4]));
                  }

                  var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("_103_punch");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[8] - 2 - 3).setProgress(var2.mCanbusInfoInt[8] - 2));
                  }

                  var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("outlander_simple_car_set_8");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[10]));
                  }

                  var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("outlander_simple_car_set_9");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[11]));
                  }

                  var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("outlander_simple_car_set_10");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[12]));
                  }

                  var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("outlander_simple_car_set_11");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[13]));
                  }

                  var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("outlander_simple_car_set_12");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[14]));
                  }

                  var7 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("amplifier_switch");
                  if (var7 != null) {
                     this.list.add(var7.setValue(var2.mCanbusInfoInt[15]));
                  }

                  var2.updateGeneralSettingData((List)this.list);
                  var2.updateSettingActivity((Bundle)null);
               }
            }

         }
      });
      var2.append(64, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;
         private final SparseArray unitArray;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
            SparseArray var2 = new SparseArray();
            var2.put(0, new Pair("km", "km/L"));
            var2.append(1, new Pair("km", "L/100km"));
            var2.append(2, new Pair("mile", "mpg(US)"));
            var2.append(3, new Pair("mile", "mpg(UK)"));
            this.unitArray = var2;
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
            int var4 = 0;
            int var3 = 0;

            for(int var2 = 0; var4 < var5; ++var2) {
               var3 |= var1[var4] << var2 * 8;
               ++var4;
            }

            return var3;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               Pair var5 = (Pair)this.unitArray.get(this.this$0.mCanbusInfoInt[2]);
               MsgMgr var3 = this.this$0;
               ArrayList var4 = this.list;
               var4.clear();
               int[] var6 = var3.mCanbusInfoInt;
               DriverUpdateEntity var7 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_207_data_3");
               int var2;
               Function1 var8;
               if (var7 != null) {
                  var2 = var6[4];
                  var1 = var6[3];
                  var8 = (Function1)(new Function1(this, var5) {
                     final Pair $unit;
                     final <undefinedtype> this$0;

                     {
                        this.this$0 = var1;
                        this.$unit = var2;
                     }

                     public final String invoke(int[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$getResult");
                        return "" + this.this$0.toInteger(var1) + ' ' + (String)this.$unit.getFirst();
                     }
                  });
                  var4.add(var7.setValue(this.getResult(new int[]{var2, var1}, var8)));
               }

               var7 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_18_current_fuel");
               if (var7 != null) {
                  var2 = var6[6];
                  var1 = var6[5];
                  var8 = (Function1)(new Function1(this, var5) {
                     final Pair $unit;
                     final <undefinedtype> this$0;

                     {
                        this.this$0 = var1;
                        this.$unit = var2;
                     }

                     public final String invoke(int[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$getResult");
                        return "" + (float)this.this$0.toInteger(var1) / 10.0F + ' ' + (String)this.$unit.getSecond();
                     }
                  });
                  var4.add(var7.setValue(this.getResult(new int[]{var2, var1}, var8)));
               }

               var7 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_328_avg_fuel_cons_auto");
               if (var7 != null) {
                  var2 = var6[8];
                  var1 = var6[7];
                  var8 = (Function1)(new Function1(this, var5) {
                     final Pair $unit;
                     final <undefinedtype> this$0;

                     {
                        this.this$0 = var1;
                        this.$unit = var2;
                     }

                     public final String invoke(int[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$getResult");
                        return "" + (float)this.this$0.toInteger(var1) / 10.0F + ' ' + (String)this.$unit.getSecond();
                     }
                  });
                  var4.add(var7.setValue(this.getResult(new int[]{var2, var1}, var8)));
               }

               var7 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_328_avg_fuel_cons_mual");
               if (var7 != null) {
                  var1 = var6[10];
                  var2 = var6[9];
                  Function1 var9 = (Function1)(new Function1(this, var5) {
                     final Pair $unit;
                     final <undefinedtype> this$0;

                     {
                        this.this$0 = var1;
                        this.$unit = var2;
                     }

                     public final String invoke(int[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$getResult");
                        return "" + (float)this.this$0.toInteger(var1) / 10.0F + ' ' + (String)this.$unit.getSecond();
                     }
                  });
                  var4.add(var7.setValue(this.getResult(new int[]{var1, var2}, var9)));
               }

               var3.updateGeneralDriveData((List)var4);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      var2.append(80, new Parser(this) {
         private final ArrayList list;
         private final SparseArray mHelperArray;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            SparseArray var2 = new SparseArray();
            var2.put(0, new MsgMgr$initParsers$1$SettingsParseHelper("_328_00", (Function1)null.INSTANCE));
            var2.append(1, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_1", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(2, new MsgMgr$initParsers$1$SettingsParseHelper("_328_02", (Function1)null.INSTANCE));
            var2.append(3, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_2", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(4, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_3", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(5, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_4", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(6, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_5", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(7, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_6", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(8, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_7", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(9, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_8", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(10, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_10", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(11, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_11", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(12, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_12", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(13, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_13", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(14, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_14", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(15, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_15", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(16, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_16", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(17, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_17", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(18, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_18", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(19, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_19", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(20, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_20", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(21, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_21", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(22, new MsgMgr$initParsers$1$SettingsParseHelper("_165_eco_mode", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(23, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_22", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(24, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_23", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(25, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_24", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(26, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_25", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(27, new MsgMgr$initParsers$1$SettingsParseHelper("_103_car_setting_title_26", (Function1)null, 2, (DefaultConstructorMarker)null));
            var2.append(28, new MsgMgr$initParsers$1$SettingsParseHelper("_328_1c", (Function1)null.INSTANCE));
            this.mHelperArray = var2;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            MsgMgr$initParsers$1$SettingsParseHelper var8 = (MsgMgr$initParsers$1$SettingsParseHelper)this.mHelperArray.get(this.this$0.mCanbusInfoInt[2]);
            if (var8 != null) {
               MsgMgr var6 = this.this$0;
               SettingUpdateEntity var4 = (SettingUpdateEntity)var6.mSettingItemIndexHashMap.get(var8.getTitle());
               if (var4 != null) {
                  this.list.clear();
                  ArrayList var5 = this.list;
                  Object var7 = var8.getParse().invoke(var6.mCanbusInfoInt[3]);
                  var1 = ((Number)var7).intValue();
                  if (var8.getTitle().equals("_328_1c")) {
                     SettingUpdateEntity var10 = (SettingUpdateEntity)var6.mSettingItemIndexHashMap.get("_328_1e");
                     boolean var3 = false;
                     boolean var2;
                     if (var10 != null) {
                        ArrayList var9 = this.list;
                        if (var1 == 0) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var9.add(var10.setEnable(var2));
                     }

                     SettingUpdateEntity var13 = (SettingUpdateEntity)var6.mSettingItemIndexHashMap.get("_328_1d");
                     if (var13 != null) {
                        ArrayList var11 = this.list;
                        var2 = var3;
                        if (var1 == 1) {
                           var2 = true;
                        }

                        var11.add(var13.setEnable(var2));
                     }
                  }

                  Unit var12 = Unit.INSTANCE;
                  var5.add(var4.setValue(var7));
                  var6.updateGeneralSettingData((List)this.list);
                  var6.updateSettingActivity((Bundle)null);
               }
            }

         }
      });
      var2.append(127, new Parser(this, var1) {
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
   }

   private static final void musicInfoChange$lambda_13$lambda_12$lambda_11(MsgMgr var0, byte[] var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$this_run");
      Intrinsics.checkNotNullParameter(var2, "$it");
      var0.sendMediaMsg(var0.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), ArraysKt.plus(var1, ArraysKt.copyOfRange(var2, 2, var2.length)));
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._328.UiMgr");
      this.mUiMgr = (UiMgr)var2;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
      this.updateSettings("support_panorama", SharePreUtil.getIntValue(var1, "share_is_support_panoramic", 0));
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "artist");
      Intrinsics.checkNotNullParameter(var3, "album");
      byte[] var7 = new byte[]{22, -64, 11, 16, 1, 0};
      Charset var4 = StandardCharsets.UTF_16;
      Intrinsics.checkNotNullExpressionValue(var4, "UTF_16");
      byte[] var5 = var1.getBytes(var4);
      Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).getBytes(charset)");
      ArraysKt.plus(var7, (byte)(var5.length - 2));
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), ArraysKt.plus(var7, ArraysKt.copyOfRange(var5, 2, var5.length)));
      var7[4] = 2;
      Charset var6 = StandardCharsets.UTF_16;
      Intrinsics.checkNotNullExpressionValue(var6, "UTF_16");
      var5 = var2.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).getBytes(charset)");
      var7[5] = (byte)(var5.length - 2);
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda0(this, var7, var5), 333L);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 0, 16, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      this.mIsPhoneIncoming = true;
      byte var4 = (byte)var1.length;
      String var5 = new String(var1, Charsets.UTF_8);
      Charset var6 = StandardCharsets.UTF_16;
      Intrinsics.checkNotNullExpressionValue(var6, "UTF_16");
      var1 = var5.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).getBytes(charset)");
      var1 = ArraysKt.copyOfRange(var1, 2, var1.length);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -64, 5, 1, 16, 2, var4}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      this.mIsPhoneIncoming = false;
      byte var4 = (byte)var1.length;
      String var5 = new String(var1, Charsets.UTF_8);
      Charset var6 = StandardCharsets.UTF_16;
      Intrinsics.checkNotNullExpressionValue(var6, "UTF_16");
      var1 = var5.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).getBytes(charset)");
      var1 = ArraysKt.copyOfRange(var1, 2, var1.length);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -64, 5, 3, 16, 2, var4}, var1));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      byte var4;
      if (this.mIsPhoneIncoming) {
         var4 = 2;
      } else {
         var4 = 4;
      }

      byte var5 = (byte)var1.length;
      String var7 = new String(var1, Charsets.UTF_8);
      Charset var6 = StandardCharsets.UTF_16;
      Intrinsics.checkNotNullExpressionValue(var6, "UTF_16");
      var1 = var7.getBytes(var6);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).getBytes(charset)");
      var1 = ArraysKt.copyOfRange(var1, 2, var1.length);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -64, 5, var4, 16, 2, var5}, var1));
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

   public boolean customLongClick(Context var1, int var2) {
      if (SharePreUtil.getIntValue(var1, "share_is_support_panoramic", 0) == 1 && var2 == 49) {
         CanbusMsgSender.sendMsg(UiMgr.Companion.getPanoramicCommand());
         return true;
      } else {
         return false;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var6, (byte)var8});
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda2(this), 100L);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   public void musicDestroy() {
      this.mMeidaSongTitleRecord = "";
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Intrinsics.checkNotNullParameter(var21, "songTitle");
      Intrinsics.checkNotNullParameter(var22, "songAlbum");
      Intrinsics.checkNotNullParameter(var23, "songArtist");
      var2 = 9;
      if (var1 != 9) {
         var2 = 8;
      }

      if (!Intrinsics.areEqual((Object)this.mMeidaSongTitleRecord, (Object)var21)) {
         this.mMeidaSongTitleRecord = var21;
         byte[] var25 = new byte[]{22, -64, (byte)var2, 16, 1, 0};
         Charset var26 = StandardCharsets.UTF_16;
         Intrinsics.checkNotNullExpressionValue(var26, "UTF_16");
         byte[] var27 = var21.getBytes(var26);
         Intrinsics.checkNotNullExpressionValue(var27, "this as java.lang.String).getBytes(charset)");
         ArraysKt.plus(var25, (byte)(var27.length - 2));
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), ArraysKt.plus(var25, ArraysKt.copyOfRange(var27, 2, var27.length)));
         var25[4] = 2;
         var26 = StandardCharsets.UTF_16;
         Intrinsics.checkNotNullExpressionValue(var26, "UTF_16");
         var27 = var23.getBytes(var26);
         Intrinsics.checkNotNullExpressionValue(var27, "this as java.lang.String).getBytes(charset)");
         var25[5] = (byte)(var27.length - 2);
         this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda1(this, var25, var27), 333L);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var6;
      String var7;
      Context var8;
      byte[] var10;
      label37: {
         Intrinsics.checkNotNullParameter(var2, "currBand");
         Intrinsics.checkNotNullParameter(var3, "currentFreq");
         Intrinsics.checkNotNullParameter(var4, "psName");
         var8 = this.mContext;
         var7 = SourceConstantsDef.SOURCE_ID.FM.name();
         var10 = this.mRadioInfoCommand;
         switch (var2) {
            case "AM1":
               var6 = 17;
               break label37;
            case "AM2":
               var6 = 18;
               break label37;
            case "FM1":
               var6 = 1;
               break label37;
            case "FM2":
               var6 = 2;
               break label37;
            case "FM3":
               var6 = 3;
               break label37;
         }

         var6 = 0;
      }

      var10[3] = var6;
      if (StringsKt.contains$default((CharSequence)var2, (CharSequence)"FM", false, 2, (Object)null)) {
         var5 = (int)(Float.parseFloat(var3) * (float)100);
      } else {
         var5 = Integer.parseInt(var3);
      }

      var10[4] = (byte)(var5 & 255);
      var10[5] = (byte)(var5 >> 8 & 255);
      var10[6] = (byte)var1;
      Unit var9 = Unit.INSTANCE;
      this.sendMediaMsg(var8, var7, var10);
   }

   public final void setAmplifierSwitch(int var1) {
      this.mAmplifierSwitch = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (var1) {
         this.mAmpTimerUtil.stopTimer();
      } else {
         this.initAmplifier(this.mContext);
      }

   }

   public final void updateSettings(String var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "value");
      SettingUpdateEntity var4 = (SettingUpdateEntity)this.mSettingItemIndexHashMap.get(var1);
      if (var4 != null) {
         ArrayList var3 = new ArrayList();
         var3.add(var4.setValue(var2));
         Log.i("_330_MsgMgr", "updateSettings: " + var4.getLeftListIndex() + "  " + var4.getRightListIndex() + "  " + var2);
         if (Intrinsics.areEqual((Object)var1, (Object)"support_panorama")) {
            SettingUpdateEntity var5 = (SettingUpdateEntity)this.mSettingItemIndexHashMap.get("_23_enter_panoramic");
            if (var5 != null) {
               var3.add(var5.setEnable(Intrinsics.areEqual((Object)var2, (int)1)));
            }
         }

         this.updateGeneralSettingData((List)var3);
         this.updateSettingActivity((Bundle)null);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 16, 0});
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"},
      d2 = {"Lcom/hzbhd/canbus/car/_328/MsgMgr$Companion;", "", "()V", "AMPLIFIER_DATA_BASS_MIN", "", "AMPLIFIER_DATA_FAD_MID", "TAG", "", "CanBusInfo_release"},
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
      d2 = {"Lcom/hzbhd/canbus/car/_328/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_328/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
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
         Log.i("_330_MsgMgr", "Parser: " + var2 + " length " + var3);
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
}
