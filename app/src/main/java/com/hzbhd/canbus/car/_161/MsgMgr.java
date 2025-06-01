package com.hzbhd.canbus.car._161;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u0005\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u0085\u00012\u00020\u0001:\u0006\u0085\u0001\u0086\u0001\u0087\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u000eH\u0016J\b\u0010)\u001a\u00020&H\u0016J\b\u0010*\u001a\u00020&H\u0016J \u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u0015H\u0016J \u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0016J \u00104\u001a\u00020&2\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0016J\u0018\u00105\u001a\u00020&2\u0006\u0010(\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u0004H\u0016J\u0018\u00107\u001a\u00020&2\u0006\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u000202H\u0016Jp\u0010:\u001a\u00020&2\u0006\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020\u000f2\u0006\u0010D\u001a\u0002022\u0006\u0010E\u001a\u0002022\u0006\u0010F\u001a\u0002022\u0006\u0010G\u001a\u00020\u000fH\u0016Jp\u0010H\u001a\u00020&2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u000f2\u0006\u0010L\u001a\u00020\u000f2\u0006\u0010M\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020\u000f2\u0006\u0010Q\u001a\u0002022\u0006\u0010R\u001a\u0002022\u0006\u0010S\u001a\u00020\u000f2\u0006\u0010T\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015H\u0016J\b\u0010U\u001a\u00020&H\u0016J\b\u0010V\u001a\u0004\u0018\u00010WJ\u0012\u0010X\u001a\u0004\u0018\u00010#2\u0006\u0010(\u001a\u00020\u000eH\u0002J\u0012\u0010Y\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010Z\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010[\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\\\u001a\u00020&2\u0006\u0010(\u001a\u00020\u000eH\u0002J¾\u0001\u0010]\u001a\u00020&2\u0006\u0010^\u001a\u00020J2\u0006\u0010_\u001a\u00020J2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020J2\u0006\u0010c\u001a\u00020J2\u0006\u0010d\u001a\u00020J2\u0006\u0010e\u001a\u00020J2\u0006\u0010f\u001a\u00020J2\u0006\u0010g\u001a\u00020J2\b\u0010h\u001a\u0004\u0018\u00010\u00152\b\u0010i\u001a\u0004\u0018\u00010\u00152\b\u0010j\u001a\u0004\u0018\u00010\u00152\u0006\u0010k\u001a\u00020l2\u0006\u0010I\u001a\u00020J2\u0006\u0010m\u001a\u00020\u000f2\u0006\u0010R\u001a\u0002022\u0006\u0010n\u001a\u00020l2\u0006\u0010o\u001a\u00020\u00152\u0006\u0010p\u001a\u00020\u00152\u0006\u0010q\u001a\u00020\u00152\u0006\u0010r\u001a\u000202H\u0016J0\u0010s\u001a\u00020&2\u0006\u0010t\u001a\u00020\u000f2\u0006\u0010u\u001a\u00020\u00152\u0006\u0010v\u001a\u00020\u00152\u0006\u0010w\u001a\u00020\u00152\u0006\u0010x\u001a\u00020\u000fH\u0016J\u0012\u0010y\u001a\u00020&2\b\u0010z\u001a\u0004\u0018\u00010\u0015H\u0016J\u0016\u0010{\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010|\u001a\u00020!J\u001e\u0010}\u001a\u00020&2\u0006\u0010~\u001a\u00020\u000f2\u0006\u0010\u007f\u001a\u00020\u000f2\u0006\u0010|\u001a\u00020\u000fJ\u009b\u0001\u0010\u0080\u0001\u001a\u00020&2\u0006\u0010^\u001a\u00020J2\u0006\u0010_\u001a\u00020J2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020J2\u0006\u0010c\u001a\u00020J2\u0006\u0010d\u001a\u00020J2\b\u0010e\u001a\u0004\u0018\u00010\u00152\u0006\u0010f\u001a\u00020J2\u0006\u0010g\u001a\u00020J2\b\u0010h\u001a\u0004\u0018\u00010\u00152\b\u0010i\u001a\u0004\u0018\u00010\u00152\b\u0010j\u001a\u0004\u0018\u00010\u00152\u0006\u0010k\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020J2\u0006\u0010R\u001a\u0002022\u0007\u0010\u0082\u0001\u001a\u00020\u000fH\u0016J\u0014\u0010\u0083\u0001\u001a\u00020&*\t\u0012\u0004\u0012\u00020\u00040\u0084\u0001H\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00000\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0088\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "<set-?>", "", "m0x3BDatas", "getM0x3BDatas", "()[B", "m0x3DDatas", "getM0x3DDatas", "mCanbusInfoByte", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "", "mDifferent", "getMDifferent", "()I", "mDiscCurrent", "mDiscTitle", "", "mDiscTotal", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_161/UiMgr;", "uiMgr", "Jump", "", "afterServiceNormalSetting", "context", "atvInfoChange", "auxInInfoChange", "btMusicId3InfoChange", "title", "artist", "album", "btPhoneIncomingInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneOutGoingInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "dtvInfoChange", "getCurrentActivity", "Landroid/app/Activity;", "getUiMgr", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sourceSwitchChange", "source", "updateSettingActivity", "value", "updateSettings", "leftListIndex", "rightListIndex", "videoInfoChange", "playMode", "duration", "sendCommands", "Ljava/util/ArrayList;", "Companion", "Parser", "VehicleType", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_1161_MsgMgr";
   public static final String _161_VEHICLE_CONFIG = "_161_vehicle_config";
   private byte[] m0x3BDatas = new byte[]{22, 59, 0, 0, 0, 0, 0, 0};
   private byte[] m0x3DDatas = new byte[]{22, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private Context mContext;
   private int mDifferent = this.getCurrentCanDifferentId();
   private int mDiscCurrent;
   private String mDiscTitle = " ";
   private int mDiscTotal;
   private final HashMap mDriveItemIndexHashMap = new HashMap();
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private final SparseArray mParserArray = new SparseArray();
   private final HashMap mSettingItemIndexHashMap = new HashMap();
   private UiMgr mUiMgr;
   private UiMgr uiMgr;

   // $FF: synthetic method
   public static void $r8$lambda$KtXX3igIC0UGU0bx_WmhvvDTwNY() {
      dateTimeRepCanbus$lambda_3();
   }

   private final void Jump() {
      Intent var4 = new Intent();
      Context var3 = this.mContext;
      Object var2 = null;
      Context var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var1 = null;
      }

      var4.setClass(var1, SettingActivity.class);
      var4.addFlags(268435456);
      var4.setAction("setting_open_target_page");
      var3 = this.mContext;
      var1 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var1 = null;
      }

      UiMgr var5 = this.getUiMgr(var1);
      Integer var6;
      if (var5 != null) {
         var3 = this.mContext;
         var1 = var3;
         if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            var1 = null;
         }

         var6 = var5.getSettingLeftIndexes(var1, "_161_hybird_Setting");
      } else {
         var6 = null;
      }

      var4.putExtra("left_index ", (Serializable)var6);
      var1 = this.mContext;
      if (var1 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         var1 = (Context)var2;
      }

      var1.startActivity(var4);
   }

   private static final void dateTimeRepCanbus$lambda_3() {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 127, 0});
   }

   private final UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.UiMgr");
         this.uiMgr = (UiMgr)var2;
      }

      return this.uiMgr;
   }

   private final void initAmplifier(Context var1) {
      if (var1 != null) {
         byte var5;
         label30: {
            this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
            CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)SharePreUtil.getIntValue(var1, "_161_lcd_mode_left", 0), (byte)SharePreUtil.getIntValue(var1, "_161_lcd_mode_right", 0)});
            int var2 = this.mDifferent;
            if (var2 != 13) {
               if (var2 == 25) {
                  var5 = 4;
                  break label30;
               }

               if (var2 == 70) {
                  var5 = 7;
                  break label30;
               }

               if (var2 != 22 && var2 != 23) {
                  switch (var2) {
                     case 16:
                        var5 = 0;
                        break label30;
                     case 17:
                        break;
                     case 18:
                        var5 = 2;
                        break label30;
                     default:
                        var5 = 3;
                        break label30;
                  }
               }
            }

            var5 = 1;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte)var5, (byte)SharePreUtil.getIntValue(var1, "_161_vehicle_config", 1), 0});
      }

      byte[][] var4 = (byte[][])(new byte[][]{{22, -59, (byte)(GeneralAmplifierData.frontRear + 9), (byte)(GeneralAmplifierData.leftRight + 9), (byte)GeneralAmplifierData.bandBass, (byte)GeneralAmplifierData.bandTreble, (byte)GeneralAmplifierData.bandMiddle, 0, 0, 2}});
      TimerUtil var3 = new TimerUtil();
      var3.startTimer((TimerTask)(new TimerTask(var4, var3) {
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
      }), 0L, 100L);
   }

   private final void initItemsIndexHashMap(Context var1) {
      Log.i("_1161_MsgMgr", "initItems: ");
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
      SparseArray var3 = this.mParserArray;
      var3.append(1, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$4Y95T850ICfMYCb0b5z3hrzxMN4(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[4];
            boolean var3 = true;
            boolean var5;
            if (1 <= var2 && var2 < 4) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (var5) {
               var0.setBacklightLevel(1);
            }

            var2 = var0.mCanbusInfoInt[4];
            if (4 <= var2 && var2 < 7) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (var5) {
               var0.setBacklightLevel(2);
            }

            var2 = var0.mCanbusInfoInt[4];
            if (7 <= var2 && var2 < 10) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (var5) {
               var0.setBacklightLevel(3);
            }

            var2 = var0.mCanbusInfoInt[4];
            if (10 <= var2 && var2 < 13) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (var5) {
               var0.setBacklightLevel(4);
            }

            var2 = var0.mCanbusInfoInt[4];
            if (13 <= var2 && var2 < 16) {
               var5 = var3;
            } else {
               var5 = false;
            }

            if (var5) {
               var0.setBacklightLevel(5);
            }

            SettingUpdateEntity var4 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("light_info");
            if (var4 != null) {
               var4.setValue(var0.mCanbusInfoInt[4]);
               var4.setProgress(var0.mCanbusInfoInt[4]);
               var1.list.add(var4.setValue(var4.getValue()));
               var0.updateGeneralSettingData((List)var1.list);
               var0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, null, new MsgMgr$initParsers$1$1$$ExternalSyntheticLambda0(this.this$0, this)};
         }
      });
      var3.put(2, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;
         private int tripComputerPage;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void realKeyLongClick2(int var1) {
            this.this$0.realKeyLongClick2(this.$context, var1);
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[2];
            if (var1 != 0) {
               if (var1 != 128) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           if (var1 != 7) {
                              if (var1 != 8) {
                                 if (var1 != 9) {
                                    if (var1 != 97) {
                                       if (var1 != 98) {
                                          switch (var1) {
                                             case 16:
                                                this.realKeyLongClick2(2);
                                                break;
                                             case 17:
                                                this.realKeyLongClick2(14);
                                                break;
                                             case 18:
                                                this.realKeyLongClick2(20);
                                                break;
                                             case 19:
                                                this.realKeyLongClick2(21);
                                                break;
                                             case 20:
                                                this.realKeyLongClick2(7);
                                                break;
                                             case 21:
                                                this.realKeyLongClick2(8);
                                                break;
                                             case 22:
                                                this.realKeyLongClick2(3);
                                                break;
                                             case 23:
                                                this.realKeyLongClick2(45);
                                                break;
                                             case 24:
                                                this.realKeyLongClick2(46);
                                                break;
                                             case 25:
                                                this.this$0.Jump();
                                                break;
                                             default:
                                                switch (var1) {
                                                   case 33:
                                                      this.realKeyLongClick2(52);
                                                      break;
                                                   case 34:
                                                      SettingUpdateEntity var2 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_161_speed_remember");
                                                      if (var2 != null) {
                                                         this.this$0.startSettingActivity(this.$context, var2.getLeftListIndex(), var2.getRightListIndex());
                                                      }
                                                      break;
                                                   case 35:
                                                      this.realKeyLongClick2(68);
                                                      break;
                                                   default:
                                                      switch (var1) {
                                                         case 41:
                                                            this.realKeyLongClick2(187);
                                                            break;
                                                         case 42:
                                                            this.this$0.startMainActivity(this.$context);
                                                            break;
                                                         case 43:
                                                            this.realKeyLongClick2(128);
                                                            break;
                                                         case 44:
                                                            this.realKeyLongClick2(2);
                                                            break;
                                                         case 45:
                                                            this.realKeyLongClick2(68);
                                                            break;
                                                         case 46:
                                                            this.realKeyLongClick2(58);
                                                            break;
                                                         case 47:
                                                            if (SystemUtil.isForeground(this.$context, Reflection.getOrCreateKotlinClass(AirActivity.class).getQualifiedName())) {
                                                               this.this$0.finishActivity();
                                                            } else {
                                                               Context var4 = this.$context;
                                                               Intent var3 = new Intent();
                                                               var3.setComponent(Constant.AirActivity);
                                                               var3.setFlags(268435456);
                                                               var4.startActivity(var3);
                                                            }
                                                            break;
                                                         case 48:
                                                            this.realKeyLongClick2(14);
                                                            break;
                                                         case 49:
                                                            this.realKeyLongClick2(15);
                                                            break;
                                                         case 50:
                                                            this.realKeyLongClick2(128);
                                                            break;
                                                         case 51:
                                                            this.realKeyLongClick2(76);
                                                            break;
                                                         case 52:
                                                            this.realKeyLongClick2(58);
                                                            break;
                                                         case 53:
                                                            this.realKeyLongClick2(68);
                                                            break;
                                                         case 54:
                                                            this.realKeyLongClick2(59);
                                                            break;
                                                         case 55:
                                                            this.realKeyLongClick2(128);
                                                            break;
                                                         case 56:
                                                            this.realKeyLongClick2(45);
                                                            break;
                                                         case 57:
                                                            this.realKeyLongClick2(46);
                                                            break;
                                                         default:
                                                            switch (var1) {
                                                               case 64:
                                                                  this.realKeyLongClick2(47);
                                                                  break;
                                                               case 65:
                                                                  this.realKeyLongClick2(48);
                                                                  break;
                                                               case 66:
                                                                  this.realKeyLongClick2(45);
                                                                  break;
                                                               case 67:
                                                                  this.realKeyLongClick2(46);
                                                                  break;
                                                               case 68:
                                                                  this.realKeyLongClick2(33);
                                                                  break;
                                                               case 69:
                                                                  this.realKeyLongClick2(34);
                                                                  break;
                                                               case 70:
                                                                  this.realKeyLongClick2(35);
                                                                  break;
                                                               case 71:
                                                                  this.realKeyLongClick2(36);
                                                                  break;
                                                               case 72:
                                                                  this.realKeyLongClick2(37);
                                                                  break;
                                                               case 73:
                                                                  this.realKeyLongClick2(38);
                                                                  break;
                                                               case 74:
                                                                  this.realKeyLongClick2(2);
                                                                  break;
                                                               default:
                                                                  switch (var1) {
                                                                     case 80:
                                                                        this.realKeyLongClick2(129);
                                                                        break;
                                                                     case 81:
                                                                        this.realKeyLongClick2(128);
                                                                        break;
                                                                     case 82:
                                                                        this.realKeyLongClick2(4);
                                                                        break;
                                                                     case 83:
                                                                        this.realKeyLongClick2(94);
                                                                        break;
                                                                     case 84:
                                                                        this.realKeyLongClick2(223);
                                                                        break;
                                                                     case 85:
                                                                        this.realKeyLongClick2(31);
                                                                        break;
                                                                     case 86:
                                                                        this.realKeyLongClick2(46);
                                                                        break;
                                                                     case 87:
                                                                        this.realKeyLongClick2(45);
                                                                        break;
                                                                     case 88:
                                                                        this.realKeyLongClick2(45);
                                                                        break;
                                                                     case 89:
                                                                        this.realKeyLongClick2(46);
                                                                  }
                                                            }
                                                      }
                                                }
                                          }
                                       } else {
                                          this.realKeyLongClick2(14);
                                       }
                                    } else {
                                       this.realKeyLongClick2(2);
                                    }
                                 } else {
                                    this.realKeyLongClick2(53);
                                 }
                              } else {
                                 this.realKeyLongClick2(50);
                              }
                           } else {
                              this.realKeyLongClick2(49);
                           }
                        } else {
                           this.realKeyLongClick2(46);
                        }
                     } else {
                        this.realKeyLongClick2(45);
                     }
                  } else {
                     this.realKeyLongClick2(52);
                  }
               } else {
                  this.realKeyLongClick2(134);
               }
            } else {
               this.realKeyLongClick2(0);
            }

         }
      });
      var3.append(33, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$2IxAVZw6417crIjwLwNtkG4uKE0(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$3Ikl090OORGdlaRnT1FGL2mcAf4(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_6(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$PadDegj_V4584l1YPCHRMVWXTCQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$SG35ZKXG4Y5ieuswH8VTzH2QS3E(MsgMgr var0) {
            setOnParseListeners$lambda_8(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ZIYXHMEgRWpJVAcEGq8KiUNKqL4(MsgMgr var0) {
            setOnParseListeners$lambda_9(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$cSrxJteC25Q9g3LzeG_H6daK_6A(MsgMgr var0) {
            setOnParseListeners$lambda_10(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$v4144_D0vgjye1lW3zMOwPDQZ_U(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final String getTemperature(int var1) {
            MsgMgr var4 = this.this$0;
            Context var3 = this.$context;
            String var7;
            if (Integer.valueOf(var1).equals(0)) {
               var7 = "LO";
            } else if (Integer.valueOf(var1).equals(255)) {
               var7 = "HI";
            } else {
               float var2 = (float)var1 / (float)2;
               if (GeneralAirData.fahrenheit_celsius) {
                  StringBuilder var5 = new StringBuilder();
                  StringCompanionObject var6 = StringCompanionObject.INSTANCE;
                  String var8 = String.format("%.1f", Arrays.copyOf(new Object[]{var2 * (float)9 / (float)5}, 1));
                  Intrinsics.checkNotNullExpressionValue(var8, "format(format, *args)");
                  var7 = var5.append(var8).append(var4.getTempUnitF(var3)).toString();
               } else {
                  var7 = var2 + var4.getTempUnitC(var3);
               }
            }

            return var7;
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
            if ((var1 >> 4 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.aqs = var2;
            if ((var1 >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.auto = var2;
            if ((var1 >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.dual = var2;
            if ((var1 >> 0 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_defog = var2;
         }

         private static final void setOnParseListeners$lambda_10(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAirData.auto_wind_lv = var0.mCanbusInfoInt[8] >> 6 & 3;
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[3];
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
            var1 &= 15;
            GeneralAirData.front_auto_wind_speed = Integer.valueOf(var1).equals(0);
            GeneralAirData.front_wind_level = var1 - 1;
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            if (var0.getCurrentCanDifferentId() == 31) {
               if (var0.mCanbusInfoInt[4] != 2 && var0.mCanbusInfoInt[4] != 0) {
                  if (var0.mCanbusInfoInt[4] != 253 && var0.mCanbusInfoInt[4] != 255) {
                     GeneralAirData.front_left_temperature = "" + ((float)var0.mCanbusInfoInt[4] / 2.0F - (float)2) + '℃';
                  } else {
                     GeneralAirData.front_left_temperature = "HI";
                  }
               } else {
                  GeneralAirData.front_left_temperature = "LO";
               }
            } else {
               GeneralAirData.front_left_temperature = var1.getTemperature(var0.mCanbusInfoInt[4]);
            }

         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            if (var0.getCurrentCanDifferentId() == 31) {
               if (var0.mCanbusInfoInt[5] != 2 && var0.mCanbusInfoInt[5] != 0) {
                  if (var0.mCanbusInfoInt[5] != 253 && var0.mCanbusInfoInt[5] != 255) {
                     GeneralAirData.front_right_temperature = "" + ((float)var0.mCanbusInfoInt[5] / 2.0F - (float)2) + '℃';
                  } else {
                     GeneralAirData.front_right_temperature = "HI";
                  }
               } else {
                  GeneralAirData.front_right_temperature = "LO";
               }
            } else {
               GeneralAirData.front_right_temperature = var1.getTemperature(var0.mCanbusInfoInt[5]);
            }

         }

         private static final void setOnParseListeners$lambda_8(MsgMgr var0) {
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
            if ((var1 >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac_max = var2;
            if ((var1 >> 0 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.fahrenheit_celsius = var2;
         }

         private static final void setOnParseListeners$lambda_9(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[7];
            boolean var2 = true;
            if ((var1 >> 7 & 1) != 1) {
               var2 = false;
            }

            GeneralAirData.rear = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
               GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
               GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
               this.this$0.updateAirActivity(this.$context, 1001);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda3(this.this$0, this), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$3$$ExternalSyntheticLambda6(this.this$0)};
         }
      });
      var3.append(34, new Parser(this, var1) {
         final Context $context;
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$jXbflPJ7_5j176cujeIGkA_EGJ0(MsgMgr var0, Object var1, Context var2) {
            setOnParseListeners$lambda_16(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$lzoycha142a4gMgIpJJsCq3NBU8(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_11(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[2];
            ArrayList var3 = var1.list;
            HashMap var4 = var0.mSettingItemIndexHashMap;
            SettingUpdateEntity var5 = (SettingUpdateEntity)var4.get("_161_Preset");
            if (var5 != null) {
               var3.add(var5.setValue(var2 >> 7 & 1));
            }

            var5 = (SettingUpdateEntity)var4.get("_161_Monday");
            if (var5 != null) {
               var3.add(var5.setValue(var2 >> 6 & 1));
            }

            var5 = (SettingUpdateEntity)var4.get("_161_Tuesday");
            if (var5 != null) {
               var3.add(var5.setValue(var2 >> 5 & 1));
            }

            var5 = (SettingUpdateEntity)var4.get("_161_Wednesday");
            if (var5 != null) {
               var3.add(var5.setValue(var2 >> 4 & 1));
            }

            var5 = (SettingUpdateEntity)var4.get("_161_Thursday");
            if (var5 != null) {
               var3.add(var5.setValue(var2 >> 3 & 1));
            }

            var5 = (SettingUpdateEntity)var4.get("_161_Friday");
            if (var5 != null) {
               var3.add(var5.setValue(var2 >> 2 & 1));
            }

            var5 = (SettingUpdateEntity)var4.get("_161_Saturday");
            if (var5 != null) {
               var3.add(var5.setValue(var2 >> 1 & 1));
            }

            SettingUpdateEntity var6 = (SettingUpdateEntity)var4.get("_161_Sunday");
            if (var6 != null) {
               var3.add(var6.setValue(var2 >> 0 & 1));
            }

            var0.updateGeneralSettingData((List)var1.list);
            var0.updateSettingActivity((Bundle)null);
         }

         private static final void setOnParseListeners$lambda_16(MsgMgr var0, Object var1, Context var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            Intrinsics.checkNotNullParameter(var2, "$context");
            int var3 = var0.mCanbusInfoInt[3];
            ArrayList var5 = var1.list;
            SettingUpdateEntity var4 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_Time");
            if (var4 != null) {
               var3 = var0.mCanbusInfoInt[3] * 256 + var0.mCanbusInfoInt[4];
               StringCompanionObject var6 = StringCompanionObject.INSTANCE;
               String var8 = String.format("%2d", Arrays.copyOf(new Object[]{MsgMgrKt.access$range(var3 / 60, 23)}, 1));
               Intrinsics.checkNotNullExpressionValue(var8, "format(format, *args)");
               StringCompanionObject var7 = StringCompanionObject.INSTANCE;
               String var9 = String.format("%02d", Arrays.copyOf(new Object[]{MsgMgrKt.access$range(var3 % 60, 59)}, 1));
               Intrinsics.checkNotNullExpressionValue(var9, "format(format, *args)");
               var4.setValue(var8 + CommUtil.getStrByResId(var2, "_161_driveInfo_0_2_1") + var9 + CommUtil.getStrByResId(var2, "_161_driveInfo_0_2_2"));
               var4.setValueStr(true);
               var5.add(var4.setValue(var4.getValue()));
            }

            var0.updateGeneralSettingData((List)var1.list);
            var0.updateSettingActivity((Bundle)null);
         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda1(this.this$0, this, this.$context)};
         }
      });
      var3.append(39, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               SettingUpdateEntity var3 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_220_language_settings");
               if (var3 != null) {
                  MsgMgr var2 = this.this$0;
                  var3.setValue(var2.mCanbusInfoInt[2]);
                  var2.updateGeneralSettingData((List)CollectionsKt.arrayListOf(new SettingUpdateEntity[]{var3}));
                  var2.updateSettingActivity((Bundle)null);
               }
            }

         }
      });
      var3.append(41, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               GeneralParkData.trackAngle = Math.negateExact(TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 5450, 16));
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var3.append(50, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 6;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               RadarInfoUtil.setRearRadarLocationData(5, this.this$0.mCanbusInfoInt[3] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[5] + 1);
               RadarInfoUtil.setFrontRadarLocationData(5, this.this$0.mCanbusInfoInt[6] + 1, this.this$0.mCanbusInfoInt[7] + 1, this.this$0.mCanbusInfoInt[7] + 1, this.this$0.mCanbusInfoInt[8] + 1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var3.append(51, new Parser(this) {
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$2lnNO3TF8v_ZVus4bo6wQiO8Aq0(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$JFp4k3unJpTirbDYkn1nzuiuDEQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_13(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$UytrfW__f4QA5ubX7j8drWF_pHY(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$aL3CoUZjU2LciBmaaIknD43ASXA(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_11(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$h6qvjBFKNxH3wZWv2usTbwoA8pc(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_17(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private final int range(int var1, int var2) {
            int var3 = var1;
            if (var1 > var2) {
               var3 = var2;
            }

            return var3;
         }

         private final int range(int var1, int var2, int var3) {
            if (var1 <= var3) {
               var3 = var1;
               if (var1 < var2) {
                  var3 = var2;
               }
            }

            return var3;
         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("cat_set_mileage");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[6];
               int var4 = var0.mCanbusInfoInt[7] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 6001) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = var4 + " KM";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         private static final void setOnParseListeners$lambda_13(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var2 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_start_stop_timing");
            if (var2 != null) {
               if (var0.mCanbusInfoInt[10] != 255 && var0.mCanbusInfoInt[9] != 255 && var0.mCanbusInfoInt[8] != 255) {
                  StringCompanionObject var3 = StringCompanionObject.INSTANCE;
                  String var4 = String.format("%d : %02d : %02d", Arrays.copyOf(new Object[]{var0.mCanbusInfoInt[8], var1.range(var0.mCanbusInfoInt[9], 59), var1.range(var0.mCanbusInfoInt[10], 59)}, 3));
                  Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
                  var2.setValue(var4);
               } else {
                  var2.setValue("--");
               }

               ArrayList var6 = var1.list;
               ArrayList var5 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var5 = null;
               }

               var5.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_17(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var5 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_CurrentSpeed");
            if (var5 != null) {
               int var2 = var0.mCanbusInfoInt[11];
               int var4 = var0.mCanbusInfoInt[12] | var2 << 8;
               if (var4 != 65535) {
                  boolean var3 = false;
                  boolean var8 = var3;
                  if (var4 >= 0) {
                     var8 = var3;
                     if (var4 < 65536) {
                        var8 = true;
                     }
                  }

                  if (!var8) {
                     return;
                  }

                  (new StringBuilder()).append(var4).append(" km/h").toString();
               }

               ArrayList var7 = var1.list;
               ArrayList var6 = var7;
               if (var7 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var6 = null;
               }

               var6.add(var5.setValue(var4 / 100 + " km/h"));
               Log.d("Lai", String.valueOf(var4));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            Log.d("_18_current_fuel", "----------------");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_18_current_fuel");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[2];
               int var4 = var0.mCanbusInfoInt[3] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 301) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = (float)var4 / (float)10 + " L/100KM";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_186_endurancee_mileage");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[4];
               int var4 = var0.mCanbusInfoInt[5] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 2001) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = var4 + " KM";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda0(this.this$0, this), null, new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda1(this.this$0, this), null, new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda2(this.this$0, this), null, null, new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda3(this.this$0, this), null, new MsgMgr$initParsers$1$8$$ExternalSyntheticLambda4(this.this$0, this)};
         }
      });
      var3.append(52, new Parser(this) {
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$SeSsvRYIvTb9n0l2Ne2dXWbDugI(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_11(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$oiQ7CFBlTcj9ESgMb2R5t2rsmZ8(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$pNWDgptcTrsllM_MAesGg4hD7u0(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("accumulated_mileage");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[6];
               int var4 = var0.mCanbusInfoInt[7] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 10000) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = var4 + " KM";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_55_0x17_data34");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[2];
               int var4 = var0.mCanbusInfoInt[3] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 301) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = (float)var4 / (float)10 + " L/100KM";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_speed");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[4];
               int var4 = var0.mCanbusInfoInt[5] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 251) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = var4 + " KM/H";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda0(this.this$0, this), null, new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda1(this.this$0, this), null, new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda2(this.this$0, this)};
         }
      });
      var3.append(53, new Parser(this) {
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$6WqmIiCt1a05qrZ71m6_KV55xb8(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Gw3aI3Rdv8Wo7FgCeT3HYErAGPg(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$K47rxu2DSWMzxf0ztAcY9RuqKSE(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_11(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_accumulated_mileage");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[6];
               int var4 = var0.mCanbusInfoInt[7] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 10000) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = var4 + " KM";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_283_meter_value_5");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[2];
               int var4 = var0.mCanbusInfoInt[3] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 301) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = (float)var4 / (float)10 + " L/100KM";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            DriverUpdateEntity var6 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_283_meter_value_23");
            if (var6 != null) {
               int var2 = var0.mCanbusInfoInt[4];
               int var4 = var0.mCanbusInfoInt[5] | var2 << 8;
               String var7;
               if (var4 == 65535) {
                  var7 = "--";
               } else {
                  boolean var3 = false;
                  boolean var9 = var3;
                  if (var4 >= 0) {
                     var9 = var3;
                     if (var4 < 251) {
                        var9 = true;
                     }
                  }

                  if (!var9) {
                     return;
                  }

                  var7 = var4 + " KM/H";
               }

               ArrayList var5 = var1.list;
               ArrayList var8 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var8 = null;
               }

               var8.add(var6.setValue(var7));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, new MsgMgr$initParsers$1$10$$ExternalSyntheticLambda0(this.this$0, this), null, new MsgMgr$initParsers$1$10$$ExternalSyntheticLambda1(this.this$0, this), null, new MsgMgr$initParsers$1$10$$ExternalSyntheticLambda2(this.this$0, this)};
         }
      });
      var3.append(54, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               if (this.this$0.mCanbusInfoInt[2] == 128) {
                  return;
               }

               MsgMgr var3 = this.this$0;
               Context var4 = this.$context;
               var1 = var3.mCanbusInfoInt[2] & 127;
               String var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 7 & 1) == 1) {
                  var2 = "" + '-' + var1;
               } else {
                  var2 = String.valueOf(var1);
               }

               var3.updateOutDoorTemp(var4, var2);
            }

         }
      });
      Popup var2 = new Popup(var1);
      Ref.ObjectRef var5 = new Ref.ObjectRef();
      var5.element = CollectionsKt.emptyList();
      Ref.ObjectRef var4 = new Ref.ObjectRef();
      var4.element = CollectionsKt.emptyList();
      var3.append(55, new Parser(this, var1, var5, var2, var4) {
         final Context $context;
         final Ref.ObjectRef $diagnosisList;
         final Popup $popUp;
         final Ref.ObjectRef $warningList;
         final MsgMgr this$0;
         private final SparseArray warningBeans;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.$warningList = var3;
            this.$popUp = var4;
            this.$diagnosisList = var5;
            SparseArray var6 = new SparseArray();
            SparseArray var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131757958));
            var7.append(1, new Popup.Ops.WarningBean(2131757959));
            var7.append(2, new Popup.Ops.WarningBean(2131757960));
            var7.append(3, new Popup.Ops.WarningBean(2131757961));
            var7.append(4, new Popup.Ops.WarningBean(2131757962));
            var7.append(5, new Popup.Ops.WarningBean(2131757963));
            var7.append(6, new Popup.Ops.WarningBean(2131757964));
            var7.append(7, new Popup.Ops.WarningBean(2131757965));
            Unit var9 = Unit.INSTANCE;
            var6.put(0, var7);
            SparseArray var10 = new SparseArray();
            var10.put(0, new Popup.Ops.WarningBean(2131757993));
            var10.append(1, new Popup.Ops.WarningBean(2131757994));
            var10.append(2, new Popup.Ops.WarningBean(2131757995));
            var10.append(3, new Popup.Ops.WarningBean(2131757996));
            var10.append(4, new Popup.Ops.WarningBean(2131757997));
            var10.append(5, new Popup.Ops.WarningBean(2131757998));
            var10.append(6, new Popup.Ops.WarningBean(2131757999));
            var10.append(7, new Popup.Ops.WarningBean(2131758000));
            Unit var8 = Unit.INSTANCE;
            var6.append(1, var10);
            var10 = new SparseArray();
            var10.put(0, new Popup.Ops.WarningBean(2131758001));
            var10.append(1, new Popup.Ops.WarningBean(2131758002));
            var10.append(2, new Popup.Ops.WarningBean(2131758003));
            var10.append(3, new Popup.Ops.WarningBean(2131758004));
            var10.append(4, new Popup.Ops.WarningBean(2131758005));
            var10.append(5, new Popup.Ops.WarningBean(2131758006));
            var10.append(6, new Popup.Ops.WarningBean(2131758007));
            var10.append(7, new Popup.Ops.WarningBean(2131758008));
            var8 = Unit.INSTANCE;
            var6.append(2, var10);
            var10 = new SparseArray();
            var10.put(1, new Popup.Ops.WarningBean(2131758009));
            var10.append(2, new Popup.Ops.WarningBean(2131758010));
            var10.append(3, new Popup.Ops.WarningBean(2131758011));
            var10.append(4, new Popup.Ops.WarningBean(2131758012));
            var10.append(5, new Popup.Ops.WarningBean(2131758013));
            var10.append(6, new Popup.Ops.WarningBean(2131758014));
            var10.append(7, new Popup.Ops.WarningBean(2131758015));
            var8 = Unit.INSTANCE;
            var6.append(3, var10);
            var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131758016));
            var7.append(1, new Popup.Ops.WarningBean(2131758017));
            var7.append(2, new Popup.Ops.WarningBean(2131758018));
            var7.append(3, new Popup.Ops.WarningBean(2131758019));
            var7.append(4, new Popup.Ops.WarningBean(2131758020));
            var7.append(5, new Popup.Ops.WarningBean(2131758021));
            var7.append(6, new Popup.Ops.WarningBean(2131758022));
            var9 = Unit.INSTANCE;
            var6.append(4, var7);
            var10 = new SparseArray();
            var10.put(0, new Popup.Ops.WarningBean(2131758023));
            var10.append(1, new Popup.Ops.WarningBean(2131758024));
            var10.append(2, new Popup.Ops.WarningBean(2131758025));
            var10.append(3, new Popup.Ops.WarningBean(2131758026));
            var10.append(4, new Popup.Ops.WarningBean(2131758027));
            var10.append(5, new Popup.Ops.WarningBean(2131758028));
            var10.append(6, new Popup.Ops.WarningBean(2131758029));
            var10.append(7, new Popup.Ops.WarningBean(2131758030));
            var8 = Unit.INSTANCE;
            var6.append(5, var10);
            var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131758031));
            var7.append(1, new Popup.Ops.WarningBean(2131758032));
            var7.append(2, new Popup.Ops.WarningBean(2131758033));
            var7.append(3, new Popup.Ops.WarningBean(2131758034));
            var7.append(4, new Popup.Ops.WarningBean(2131758035));
            var7.append(5, new Popup.Ops.WarningBean(2131758036));
            var7.append(6, new Popup.Ops.WarningBean(2131758037));
            var7.append(7, new Popup.Ops.WarningBean(2131758038));
            var9 = Unit.INSTANCE;
            var6.append(6, var7);
            var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131758039));
            var7.append(1, new Popup.Ops.WarningBean(2131758040));
            var7.append(2, new Popup.Ops.WarningBean(2131758041));
            var7.append(3, new Popup.Ops.WarningBean(2131758042));
            var7.append(4, new Popup.Ops.WarningBean(2131758043));
            var7.append(5, new Popup.Ops.WarningBean(2131758044));
            var7.append(6, new Popup.Ops.WarningBean(2131758045));
            var7.append(7, new Popup.Ops.WarningBean(2131758046));
            var9 = Unit.INSTANCE;
            var6.append(7, var7);
            var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131758047));
            var7.append(1, new Popup.Ops.WarningBean(2131758048));
            var7.append(2, new Popup.Ops.WarningBean(2131758049));
            var7.append(3, new Popup.Ops.WarningBean(2131758050));
            var7.append(4, new Popup.Ops.WarningBean(2131758051));
            var7.append(5, new Popup.Ops.WarningBean(2131758052));
            var7.append(6, new Popup.Ops.WarningBean(2131758053));
            var7.append(7, new Popup.Ops.WarningBean(2131758054));
            var9 = Unit.INSTANCE;
            var6.append(8, var7);
            var10 = new SparseArray();
            var10.put(0, new Popup.Ops.WarningBean(2131758055));
            var10.append(1, new Popup.Ops.WarningBean(2131758056));
            var10.append(2, new Popup.Ops.WarningBean(2131758057));
            var10.append(3, new Popup.Ops.WarningBean(2131758058));
            var10.append(4, new Popup.Ops.WarningBean(2131758059));
            var10.append(5, new Popup.Ops.WarningBean(2131758060));
            var10.append(6, new Popup.Ops.WarningBean(2131758061));
            var10.append(7, new Popup.Ops.WarningBean(2131758062));
            var8 = Unit.INSTANCE;
            var6.append(9, var10);
            var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131757966));
            var7.append(1, new Popup.Ops.WarningBean(2131757967));
            var7.append(2, new Popup.Ops.WarningBean(2131757968));
            var7.append(3, new Popup.Ops.WarningBean(2131757969));
            var7.append(4, new Popup.Ops.WarningBean(2131757970));
            var7.append(5, new Popup.Ops.WarningBean(2131757971));
            var7.append(6, new Popup.Ops.WarningBean(2131757972));
            var7.append(7, new Popup.Ops.WarningBean(2131757973));
            var9 = Unit.INSTANCE;
            var6.append(10, var7);
            var10 = new SparseArray();
            var10.put(0, new Popup.Ops.WarningBean(2131757974));
            var10.append(1, new Popup.Ops.WarningBean(2131757975));
            var10.append(2, new Popup.Ops.WarningBean(2131757976));
            var10.append(3, new Popup.Ops.WarningBean(2131757977));
            var10.append(4, new Popup.Ops.WarningBean(2131757978));
            var10.append(5, new Popup.Ops.WarningBean(2131757979));
            var10.append(6, new Popup.Ops.WarningBean(2131757980));
            var10.append(7, new Popup.Ops.WarningBean(2131757981));
            var8 = Unit.INSTANCE;
            var6.append(11, var10);
            var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131757982));
            var7.append(1, new Popup.Ops.WarningBean(2131757983));
            var7.append(2, new Popup.Ops.WarningBean(2131757984));
            var7.append(3, new Popup.Ops.WarningBean(2131757985));
            var7.append(4, new Popup.Ops.WarningBean(2131757986));
            var7.append(5, new Popup.Ops.WarningBean(2131757987));
            var7.append(6, new Popup.Ops.WarningBean(2131757988));
            var7.append(7, new Popup.Ops.WarningBean(2131757989));
            var9 = Unit.INSTANCE;
            var6.append(12, var7);
            var7 = new SparseArray();
            var7.put(0, new Popup.Ops.WarningBean(2131757990));
            var7.append(1, new Popup.Ops.WarningBean(2131757991));
            var7.append(2, new Popup.Ops.WarningBean(2131757992));
            var9 = Unit.INSTANCE;
            var6.append(13, var7);
            this.warningBeans = var6;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               ArrayList var10 = new ArrayList();
               MsgMgr var5 = this.this$0;
               Context var8 = this.$context;
               Ref.ObjectRef var7 = this.$warningList;
               Popup var9 = this.$popUp;
               Ref.ObjectRef var6 = this.$diagnosisList;
               int var3 = var5.mCanbusInfoInt.length;
               var1 = 2;

               while(true) {
                  int var2 = 0;
                  if (var1 >= var3) {
                     if (var10.size() != 0) {
                        var10.add(0, new WarningEntity(var8.getString(2131770841)));
                     }

                     var7.element = var10;
                     ArrayList var13 = new ArrayList();
                     var13.addAll((Collection)var6.element);
                     var13.addAll((Collection)var7.element);
                     GeneralWarningDataData.dataList = (List)var13;
                     var5.updateWarningActivity((Bundle)null);
                     break;
                  }

                  for(; var2 < 8; ++var2) {
                     SparseArray var11 = (SparseArray)this.warningBeans.get(var1 - 2);
                     if (var11 != null) {
                        Popup.Ops.WarningBean var12 = (Popup.Ops.WarningBean)var11.get(var2);
                        if (var12 != null) {
                           Intrinsics.checkNotNullExpressionValue(var12, "get(bit)");
                           Popup.InfoBean var14 = var12.getBean();
                           int var4 = var5.mCanbusInfoInt[var1] >> var2 & 1;
                           if (var4 == 1) {
                              var10.add(new WarningEntity('\t' + var8.getString(var12.getMessageResId())));
                              if (var12.getBean().isNeedShow() == 0) {
                                 var9.plus((Popup.Ops.Update)var12);
                              }
                           } else if (var12.getBean().isNeedShow() == 1) {
                              var9.minus((Popup.Ops.Update)var12);
                           }

                           var14.setNeedShow(var4);
                        }
                     }
                  }

                  ++var1;
               }
            }

         }
      });
      var3.append(56, new Parser(this, var1) {
         final Context $context;
         private int displayPage;
         private boolean isBackOpen;
         private boolean isLeftFrontDoorOpen;
         private boolean isLeftRearDoorOpen;
         private boolean isRightFrontDoorOpen;
         private boolean isRightRearDoorOpen;
         private final ArrayList list;
         private int[] settingsData;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$_wsdqxd2zv6CRcfFfsfwmVphz_g(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_46(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$6zW1NBvtbe8oS4EnHUWbt2JeCAI(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_21(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$TpdzZhyGyKQL37PEhWz_7F9vKcU(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_28(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$eTPPFfo4T6a3Nq5DePnDNdeFCV0(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$o3j3JvZDc_Avw7a_Vp8z_obHmok(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_37(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$rzrlrUZQ9MUR6zOdZq2jjgvEai8(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_59(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$uElph9G4gvJqpjzEr8hFRJ6wX3s(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_11(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$wlLhgJGtABQBRJfPh9q8ChRmicA(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_54(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         private final boolean isDoorStatusChange() {
            boolean var1;
            if (this.isLeftFrontDoorOpen == GeneralDoorData.isLeftFrontDoorOpen && this.isRightFrontDoorOpen == GeneralDoorData.isRightFrontDoorOpen && this.isLeftRearDoorOpen == GeneralDoorData.isLeftRearDoorOpen && this.isRightRearDoorOpen == GeneralDoorData.isRightRearDoorOpen && this.isBackOpen == GeneralDoorData.isBackOpen) {
               var1 = false;
            } else {
               this.isLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
               this.isRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
               this.isLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
               this.isRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
               this.isBackOpen = GeneralDoorData.isBackOpen;
               var1 = true;
            }

            return var1;
         }

         private final boolean isSettingsDataChange() {
            int[] var1 = this.this$0.mCanbusInfoInt;
            var1 = Arrays.copyOf(var1, var1.length);
            Intrinsics.checkNotNullExpressionValue(var1, "copyOf(this, size)");
            var1[2] = 0;
            var1[3] &= 252;
            var1[5] &= 248;
            var1[7] &= 240;
            if (Arrays.equals(this.settingsData, var1)) {
               return false;
            } else {
               var1 = Arrays.copyOf(var1, var1.length);
               Intrinsics.checkNotNullExpressionValue(var1, "copyOf(this, size)");
               this.settingsData = var1;
               return true;
            }
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

            GeneralDoorData.isLeftFrontDoorOpen = var2;
            if ((var1 >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralDoorData.isRightFrontDoorOpen = var2;
            if ((var1 >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralDoorData.isLeftRearDoorOpen = var2;
            if ((var1 >> 4 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralDoorData.isRightRearDoorOpen = var2;
            if ((var1 >> 3 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralDoorData.isBackOpen = var2;
         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[3];
            ArrayList var6 = var1.list;
            HashMap var3 = var0.mSettingItemIndexHashMap;
            SettingUpdateEntity var5 = (SettingUpdateEntity)var3.get("_161_rear_wiper");
            if (var5 != null) {
               var6.add(var5.setValue(var2 >> 7 & 1));
            }

            var5 = (SettingUpdateEntity)var3.get("_220_driving_assistance");
            if (var5 != null) {
               var6.add(var5.setValue(var2 >> 6 & 1));
            }

            var5 = (SettingUpdateEntity)var3.get("temperature_unit");
            if (var5 != null) {
               var6.add(var5.setValue(var2 >> 5 & 1));
            }

            SettingUpdateEntity var4 = (SettingUpdateEntity)var3.get("_161_door_auto_lock");
            String var7;
            if (var4 != null) {
               if ((var2 >> 4 & 1) == 1) {
                  var7 = "enable";
               } else {
                  var7 = "disable";
               }

               var6.add(var4.setValue(var7));
            }

            var5 = (SettingUpdateEntity)var3.get("parkingAssist");
            if (var5 != null) {
               var6.add(var5.setValue(var2 >> 3 & 1));
            }

            SettingUpdateEntity var9 = (SettingUpdateEntity)var3.get("_161_central_control_door_lock");
            if (var9 != null) {
               StringBuilder var8 = (new StringBuilder()).append("setOnParseListeners: ");
               var2 = var2 >> 2 & 1;
               Log.i("_1161_MsgMgr", var8.append(var2).toString());
               if (var2 == 1) {
                  var7 = "_161_lock";
               } else {
                  var7 = "_161_unlock";
               }

               var6.add(var9.setValue(var7));
               Log.i("_1161_MsgMgr", "setOnParseListeners: " + var9.getValue());
               Log.i("_1161_MsgMgr", "setOnParseListeners: " + var9.getLeftListIndex() + "  " + var9.getRightListIndex());
            }

         }

         private static final void setOnParseListeners$lambda_21(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[4];
            ArrayList var6 = var1.list;
            HashMap var4 = var0.mSettingItemIndexHashMap;
            SettingUpdateEntity var3 = (SettingUpdateEntity)var4.get("_283_car_setting_light_4");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 7 & 1));
            }

            var3 = (SettingUpdateEntity)var4.get("_143_setting_20");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 6 & 1));
            }

            var3 = (SettingUpdateEntity)var4.get("_143_setting_21");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 5 & 1));
            }

            var3 = (SettingUpdateEntity)var4.get("fuel_unit");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 3 & 3));
            }

            var3 = (SettingUpdateEntity)var4.get("_161_emergency_braking");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 1 & 3));
            }

            SettingUpdateEntity var5 = (SettingUpdateEntity)var4.get("_118_setting_title_13");
            if (var5 != null) {
               var6.add(var5.setValue(var2 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_28(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[5];
            ArrayList var7 = var1.list;
            HashMap var5 = var0.mSettingItemIndexHashMap;
            SettingUpdateEntity var4 = (SettingUpdateEntity)var5.get("on_off_btn_txt_7");
            SettingUpdateEntity var3;
            if (var4 != null) {
               var3 = var4.setValue(var2 >> 5 & 7);
               Object var8 = var4.getValue();
               Intrinsics.checkNotNull(var8, "null cannot be cast to non-null type kotlin.Int");
               var7.add(var3.setProgress((Integer)var8));
            }

            var3 = (SettingUpdateEntity)var5.get("_161_only_unlock_trunk");
            if (var3 != null) {
               var7.add(var3.setValue(var2 >> 4 & 1));
            }

            SettingUpdateEntity var6 = (SettingUpdateEntity)var5.get("_161_reversing_radar");
            if (var6 != null) {
               var7.add(var6.setValue(var2 >> 3 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_37(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[6];
            ArrayList var6 = var1.list;
            HashMap var4 = var0.mSettingItemIndexHashMap;
            SettingUpdateEntity var3 = (SettingUpdateEntity)var4.get("_143_0x76_d1_b01");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 6 & 3));
            }

            var3 = (SettingUpdateEntity)var4.get("_143_0x76_d0_b45");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 4 & 3));
            }

            var3 = (SettingUpdateEntity)var4.get("_161_rearview_mirror_adaptive");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 3 & 1));
            }

            var3 = (SettingUpdateEntity)var4.get("outlander_simple_car_set_17");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 1 & 3));
            }

            SettingUpdateEntity var5 = (SettingUpdateEntity)var4.get("_161_disable_rear_mirror");
            if (var5 != null) {
               var6.add(var5.setValue(var2 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_46(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var5 = var0.mCanbusInfoInt[8];
            ArrayList var10 = var1.list;
            HashMap var7 = var0.mSettingItemIndexHashMap;
            SettingUpdateEntity var8 = (SettingUpdateEntity)var7.get("_143_setting_7");
            if (var8 != null) {
               var10.add(var8.setValue(var5 >> 7 & 1));
            }

            var8 = (SettingUpdateEntity)var7.get("_81_traction_control_system");
            if (var8 != null) {
               var10.add(var8.setValue(var5 >> 6 & 1));
            }

            var8 = (SettingUpdateEntity)var7.get("geely_theme_colors");
            if (var8 != null) {
               int var6 = var0.getMDifferent();
               byte var4 = 0;
               int var3 = 0;
               int var2 = 0;
               if (var6 != 4) {
                  if (var6 != 24 && var6 != 18) {
                     if (var6 == 19) {
                        var3 = var5 >> 2 & 15;
                        if (var3 != 1) {
                           if (var3 == 2) {
                              var2 = 2;
                           }
                        } else {
                           var2 = 1;
                        }

                        var10.add(var8.setValue(var2));
                     }
                  } else {
                     var2 = var4;
                     if ((var5 >> 2 & 15) == 8) {
                        var2 = 1;
                     }

                     var10.add(var8.setValue(var2));
                  }
               } else {
                  var2 = var5 >> 2 & 15;
                  byte var11;
                  if (var2 != 6) {
                     if (var2 != 7) {
                        var11 = (byte)var3;
                     } else {
                        var11 = 2;
                     }
                  } else {
                     var11 = 1;
                  }

                  var10.add(var8.setValue(Integer.valueOf(var11)));
               }
            }

            SettingUpdateEntity var9 = (SettingUpdateEntity)var7.get("_143_setting_8");
            if (var9 != null) {
               var10.add(var9.setValue(var5 >> 1 & 1));
            }

            var9 = (SettingUpdateEntity)var7.get("_283_car_setting_pa_5");
            if (var9 != null) {
               var10.add(var9.setValue(var5 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_54(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[7];
            ArrayList var6 = var1.list;
            HashMap var4 = var0.mSettingItemIndexHashMap;
            SettingUpdateEntity var3 = (SettingUpdateEntity)var4.get("_94_blind_spot_monitoring");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 7 & 1));
            }

            var3 = (SettingUpdateEntity)var4.get("_161_start_stop_disabled");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 6 & 1));
            }

            var3 = (SettingUpdateEntity)var4.get("_143_setting_5");
            if (var3 != null) {
               var6.add(var3.setValue(var2 >> 5 & 1));
            }

            SettingUpdateEntity var5 = (SettingUpdateEntity)var4.get("_161_door_opening_options");
            if (var5 != null) {
               var6.add(var5.setValue(var2 >> 4 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_59(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[10];
            ArrayList var4 = var1.list;
            SettingUpdateEntity var3 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_start_stop_status");
            if (var3 != null) {
               var4.add(var3.setValue(var2 >> 6 & 1));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               super.parse(var1);
               if (this.isDoorStatusChange()) {
                  this.this$0.updateDoorView(this.$context);
               }

               var1 = this.this$0.mCanbusInfoInt[3] & 3;
               MsgMgr var3 = this.this$0;
               Context var2 = this.$context;
               if (this.displayPage != var1) {
                  this.displayPage = var1;
                  var3.startDrivingDataActivity(var2, var1);
               }

               if (this.isSettingsDataChange()) {
                  this.this$0.updateGeneralSettingData((List)this.list);
                  this.this$0.updateSettingActivity((Bundle)null);
               }
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda1(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda3(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda4(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda5(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda6(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda7(this.this$0, this)};
         }
      });
      var3.append(57, new Parser(this, var2) {
         final Popup $popUp;
         private final SparseArray functionBeans;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$popUp = var2;
            SparseArray var5 = new SparseArray();
            SparseArray var6 = new SparseArray();
            SparseArray var4 = new SparseArray();
            var4.put(1, new Popup.Ops.FunctionBean(2131232783, 2131757888));
            var4.append(2, new Popup.Ops.FunctionBean(2131232783, 2131757889));
            Unit var3 = Unit.INSTANCE;
            var6.put(16, var4);
            SparseArray var7 = new SparseArray();
            var7.put(1, new Popup.Ops.FunctionBean(2131234517, 2131757675));
            var7.append(2, new Popup.Ops.FunctionBean(2131234516, 2131757674));
            Unit var8 = Unit.INSTANCE;
            var6.append(50, var7);
            var4 = new SparseArray();
            var4.put(1, new Popup.Ops.FunctionBean(2131234507, 2131757672));
            var4.append(2, new Popup.Ops.FunctionBean(2131234506, 2131757671));
            var3 = Unit.INSTANCE;
            var6.append(84, var4);
            var4 = new SparseArray();
            var4.put(1, new Popup.Ops.FunctionBean(2131232783, 2131757694));
            var4.append(2, new Popup.Ops.FunctionBean(2131232783, 2131757693));
            var3 = Unit.INSTANCE;
            var6.append(118, var4);
            var3 = Unit.INSTANCE;
            var5.put(0, var6);
            var6 = new SparseArray();
            var7 = new SparseArray();
            var7.put(1, new Popup.Ops.FunctionBean(2131234503, 2131757680));
            var7.append(2, new Popup.Ops.FunctionBean(2131234502, 2131757679));
            var8 = Unit.INSTANCE;
            var6.put(16, var7);
            var7 = new SparseArray();
            var7.put(1, new Popup.Ops.FunctionBean(2131234505, 2131757767));
            var7.append(2, new Popup.Ops.FunctionBean(2131234504, 2131757766));
            var8 = Unit.INSTANCE;
            var6.append(50, var7);
            var4 = new SparseArray();
            var4.put(1, new Popup.Ops.FunctionBean(2131234509, 2131757750));
            var4.append(2, new Popup.Ops.FunctionBean(2131234508, 2131757749));
            var4.append(3, new Popup.Ops.FunctionBean(2131234511, 2131757752));
            var4.append(4, new Popup.Ops.FunctionBean(2131234510, 2131757751));
            var3 = Unit.INSTANCE;
            var6.append(100, var4);
            var3 = Unit.INSTANCE;
            var5.append(1, var6);
            var6 = new SparseArray();
            var7 = new SparseArray();
            var7.put(1, new Popup.Ops.FunctionBean(2131234513, 2131757949));
            var7.append(2, new Popup.Ops.FunctionBean(2131234512, 2131757947));
            var8 = Unit.INSTANCE;
            var6.put(16, var7);
            var7 = new SparseArray();
            var7.put(1, new Popup.Ops.FunctionBean(2131234515, 2131757955));
            var7.append(2, new Popup.Ops.FunctionBean(2131234514, 2131757954));
            var8 = Unit.INSTANCE;
            var6.append(50, var7);
            var3 = Unit.INSTANCE;
            var5.append(2, var6);
            this.functionBeans = var5;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               SparseArray var14 = this.functionBeans;
               MsgMgr var12 = this.this$0;
               Popup var15 = this.$popUp;
               int var4 = var14.size();

               for(var1 = 0; var1 < var4; ++var1) {
                  int var3 = var14.keyAt(var1);
                  Log.i("_1161_MsgMgr", "byteIndex  " + var3);
                  SparseArray var16 = (SparseArray)var14.get(var3);
                  int var6 = var16.size();

                  for(int var2 = 0; var2 < var6; ++var2) {
                     int var5 = var16.keyAt(var2);
                     int var7 = var5 & 15;
                     int var8 = var5 >> 4 & 15;
                     Log.i("_1161_MsgMgr", "    bitIndex " + var5 + "   end " + var8 + "  start " + var7);
                     SparseArray var13 = (SparseArray)var16.get(var5);
                     int var9 = var13.size();

                     for(var5 = 0; var5 < var9; ++var5) {
                        int var11 = var13.keyAt(var5);
                        Log.i("_1161_MsgMgr", "        valueIndex  " + var11);
                        Popup.Ops.FunctionBean var18 = (Popup.Ops.FunctionBean)var13.get(var11);
                        Popup.InfoBean var17 = var18.getBean();
                        int var10 = var12.mCanbusInfoInt[var3 + 2] >> var7 & (1 << var8 - var7 + 1) - 1;
                        Log.i("_1161_MsgMgr", "        value " + var10 + "  isNeedShow " + var18.getBean().isNeedShow());
                        if (var11 == var10 && var18.getBean().isNeedShow() != var10) {
                           Intrinsics.checkNotNullExpressionValue(var18, "this");
                           var15.plus((Popup.Ops.Update)var18);
                        }

                        var17.setNeedShow(var10);
                     }
                  }
               }
            }

         }
      });
      var3.append(58, new Parser(this, var4, var5, var1) {
         final Ref.ObjectRef $diagnosisList;
         final Ref.ObjectRef $warningList;
         private final SparseArray diagnosisInfoArray;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$diagnosisList = var2;
            this.$warningList = var3;
            SparseArray var5 = new SparseArray();
            var5.put(-1, new WarningEntity(CommUtil.getStrByResId(var4, "_161_diagnostic_info")));
            var5.append(0, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_000")));
            var5.append(1, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_001")));
            var5.append(2, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_002")));
            var5.append(3, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_003")));
            var5.append(4, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_004")));
            var5.append(5, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_005")));
            var5.append(6, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_006")));
            var5.append(7, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_007")));
            var5.append(8, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_008")));
            var5.append(9, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_009")));
            var5.append(10, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_010")));
            var5.append(11, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_011")));
            var5.append(12, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_012")));
            var5.append(13, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_013")));
            var5.append(14, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_014")));
            var5.append(15, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_015")));
            var5.append(16, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_016")));
            var5.append(17, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_017")));
            var5.append(18, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_018")));
            var5.append(19, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_019")));
            var5.append(20, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_020")));
            var5.append(21, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_021")));
            var5.append(22, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_022")));
            var5.append(23, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_023")));
            var5.append(24, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_024")));
            var5.append(25, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_025")));
            var5.append(26, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_026")));
            var5.append(27, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_027")));
            var5.append(28, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_028")));
            var5.append(29, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_029")));
            var5.append(30, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_030")));
            var5.append(31, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_031")));
            var5.append(32, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_032")));
            var5.append(33, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_033")));
            var5.append(34, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_034")));
            var5.append(35, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_035")));
            var5.append(36, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_036")));
            var5.append(37, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_037")));
            var5.append(38, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_038")));
            var5.append(39, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_039")));
            var5.append(40, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_040")));
            var5.append(41, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_041")));
            var5.append(42, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_042")));
            var5.append(43, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_043")));
            var5.append(44, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_044")));
            var5.append(45, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_045")));
            var5.append(46, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_046")));
            var5.append(47, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_047")));
            var5.append(48, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_048")));
            var5.append(49, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_049")));
            var5.append(50, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_050")));
            var5.append(51, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_051")));
            var5.append(52, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_052")));
            var5.append(53, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_053")));
            var5.append(54, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_054")));
            var5.append(55, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_055")));
            var5.append(56, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_056")));
            var5.append(57, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_057")));
            var5.append(58, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_058")));
            var5.append(59, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_059")));
            var5.append(60, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_060")));
            var5.append(61, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_061")));
            var5.append(62, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_062")));
            var5.append(63, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_063")));
            var5.append(64, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_064")));
            var5.append(65, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_065")));
            var5.append(66, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_066")));
            var5.append(67, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_067")));
            var5.append(68, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_068")));
            var5.append(69, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_069")));
            var5.append(70, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_070")));
            var5.append(71, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_071")));
            var5.append(72, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_072")));
            var5.append(73, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_073")));
            var5.append(74, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_074")));
            var5.append(75, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_075")));
            var5.append(76, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_076")));
            var5.append(77, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_077")));
            var5.append(78, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_078")));
            var5.append(79, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_079")));
            var5.append(80, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_080")));
            var5.append(81, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_081")));
            var5.append(82, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_082")));
            var5.append(83, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_083")));
            var5.append(84, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_084")));
            var5.append(85, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_085")));
            var5.append(86, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_086")));
            var5.append(87, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_087")));
            var5.append(88, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_088")));
            var5.append(89, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_089")));
            var5.append(90, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_090")));
            var5.append(91, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_091")));
            var5.append(92, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_092")));
            var5.append(93, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_093")));
            var5.append(94, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_094")));
            var5.append(95, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_095")));
            var5.append(96, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_096")));
            var5.append(97, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_097")));
            var5.append(98, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_098")));
            var5.append(99, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_099")));
            var5.append(100, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_100")));
            var5.append(101, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_101")));
            var5.append(102, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_102")));
            var5.append(103, new WarningEntity('\t' + CommUtil.getStrByResId(var4, "_161_info_103")));
            Unit var6 = Unit.INSTANCE;
            this.diagnosisInfoArray = var5;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               int var2 = this.this$0.mCanbusInfoInt.length;
               var1 = 3;
               if (var2 < 3) {
                  return;
               }

               ArrayList var7 = new ArrayList();
               MsgMgr var3 = this.this$0;
               Ref.ObjectRef var5 = this.$diagnosisList;
               Ref.ObjectRef var4 = this.$warningList;

               for(var2 = var3.mCanbusInfoInt.length; var1 < var2; ++var1) {
                  WarningEntity var6 = (WarningEntity)this.diagnosisInfoArray.get(var3.mCanbusInfoInt[var1]);
                  if (var6 != null) {
                     Intrinsics.checkNotNullExpressionValue(var6, "diagnosisInfoArray[mCanbusInfoInt[i]]");
                     var7.add(var6);
                  }
               }

               if (var7.size() != 0) {
                  var7.add(0, this.diagnosisInfoArray.get(-1));
               }

               var5.element = var7;
               ArrayList var8 = new ArrayList();
               var8.addAll((Collection)var5.element);
               var8.addAll((Collection)var4.element);
               GeneralWarningDataData.dataList = (List)var8;
               var3.updateWarningActivity((Bundle)null);
            }

         }
      });
      var3.append(59, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$HItcDhamni_89Q_VW73Br29gxsQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_8(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$L_I3Djb6QUJpR4_7ji5BUs2yjjo(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_10(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$UcfBlADLXogvo04bbtBR9n0QUCY(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_14(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Yp14kdL_WAblZvmOk8uWMCVck4k(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_12(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$lBBQHoM8X1KppJldaNe2537rLrE(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_6(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$vwiVy7gHUypm1rKSlnFGl7vRAgQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_16(var0, var1);
         }

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_10(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_remember_2");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[4]);
               var2.setProgress(var0.mCanbusInfoInt[4]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_12(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_remember_3");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[5]);
               var2.setProgress(var0.mCanbusInfoInt[5]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_14(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_remember_4");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[6]);
               var2.setProgress(var0.mCanbusInfoInt[6]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_16(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_remember_5");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[7]);
               var2.setProgress(var0.mCanbusInfoInt[7]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_remember");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 7 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_1_enable");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 6 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_2_enable");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 5 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_3_enable");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 4 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_4_enable");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 3 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_5_enable");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 2 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_8(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_remember_1");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[3]);
               var2.setProgress(var0.mCanbusInfoInt[3]);
               var1.list.add(var2);
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var2 = this.this$0;
               var2.m0x3BDatas = (byte[])var2.mCanbusInfoByte.clone();
               this.list.clear();
               super.parse(var1);
               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$16$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$16$$ExternalSyntheticLambda1(this.this$0, this), new MsgMgr$initParsers$1$16$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$16$$ExternalSyntheticLambda3(this.this$0, this), new MsgMgr$initParsers$1$16$$ExternalSyntheticLambda4(this.this$0, this), new MsgMgr$initParsers$1$16$$ExternalSyntheticLambda5(this.this$0, this)};
         }
      });
      var3.append(61, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$AfqkTmey80wJNoRPGyhmy9JWNuc(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ArJILeu3zzAZ3wwkE5DSBTdSCwA(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_15(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$G54xBHnSObLtcb8DBi_k5_mywlI(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_13(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$HjxxJmGxnnYh5NujJMftH26Ca_Q(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$SScqINYIQsd3SeqcF2rszHVKI_s(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_19(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Vhv39MJG4bWRLV97cXnTfIp4Umk(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_9(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$bPH59u5h_3JOfGARfs_ocbvSGx8(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_11(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$cd3bSTe_l6WeBTZYAM9mCDVsOkg(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_23(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$d2SFKC3vSgu9I2KLEAjYDnGX90U(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$fovTm4weHFktIzi4xz5Bs0WDMyA(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_17(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$koYfKJneDSHBxt04JeI26be6qtI(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_21(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$l1WzJTI6lyt73iH9GaChme5BvYA(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_cruise_1");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[2]);
               var2.setProgress(var0.mCanbusInfoInt[2]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_cruise_6");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[7]);
               var2.setProgress(var0.mCanbusInfoInt[7]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_13(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_limit_1");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[8]);
               var2.setProgress(var0.mCanbusInfoInt[8]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_15(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_limit_2");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[9]);
               var2.setProgress(var0.mCanbusInfoInt[9]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_17(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_limit_3");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[10]);
               var2.setProgress(var0.mCanbusInfoInt[10]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_19(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_limit_4");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[11]);
               var2.setProgress(var0.mCanbusInfoInt[11]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_21(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_limit_5");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[12]);
               var2.setProgress(var0.mCanbusInfoInt[12]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_23(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_limit_6");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[13]);
               var2.setProgress(var0.mCanbusInfoInt[13]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_cruise_2");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[3]);
               var2.setProgress(var0.mCanbusInfoInt[3]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_cruise_3");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[4]);
               var2.setProgress(var0.mCanbusInfoInt[4]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_cruise_4");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[5]);
               var2.setProgress(var0.mCanbusInfoInt[5]);
               var1.list.add(var2);
            }

         }

         private static final void setOnParseListeners$lambda_9(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_speed_cruise_5");
            if (var2 != null) {
               var2.setValue(var0.mCanbusInfoInt[6]);
               var2.setProgress(var0.mCanbusInfoInt[6]);
               var1.list.add(var2);
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var2 = this.this$0;
               var2.m0x3DDatas = (byte[])var2.mCanbusInfoByte.clone();
               this.list.clear();
               super.parse(var1);
               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda5(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda6(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda7(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda8(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda9(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda10(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda11(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda1(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda3(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda4(this.this$0, this)};
         }
      });
      var3.append(63, new Parser(this, var1) {
         final Context $context;
         private boolean isCruiseRecord;
         private boolean isLimitRecord;
         private boolean isPopup;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            boolean var2;
            if ((this.this$0.mCanbusInfoInt[2] >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            boolean var3;
            if ((this.this$0.mCanbusInfoInt[2] >> 6 & 1) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            SettingUpdateEntity var4;
            if (this.isCruiseRecord != var2 || !var3) {
               this.isCruiseRecord = var2;
               if (var2) {
                  var4 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_1");
                  if (var4 != null) {
                     this.this$0.startSettingActivity(this.$context, var4.getLeftListIndex(), var4.getRightListIndex());
                     this.isPopup = true;
                  }
               }
            }

            if (this.isLimitRecord != var3 || !var2) {
               this.isLimitRecord = var3;
               if (var3) {
                  var4 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_161_speed_limit_1");
                  if (var4 != null) {
                     this.this$0.startSettingActivity(this.$context, var4.getLeftListIndex(), var4.getRightListIndex());
                     this.isPopup = true;
                  }
               }
            }

            if (this.this$0.mCanbusInfoInt[2] == 0 && SystemUtil.isForeground(this.$context, Reflection.getOrCreateKotlinClass(SettingActivity.class).getQualifiedName()) && this.isPopup) {
               this.this$0.finishActivity();
               this.isPopup = false;
            }

         }
      });
      var3.append(64, new Parser(this, var1) {
         final Context $context;
         private boolean isPanoramicRecord;
         private final ArrayList list;
         private int mode;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               boolean var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 7 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (this.isPanoramicRecord != var2) {
                  this.isPanoramicRecord = var2;
                  this.this$0.forceReverse(this.$context, var2);
               }

               this.list.clear();
               var1 = this.this$0.mCanbusInfoInt[2];
               MsgMgr var3 = this.this$0;
               Context var4 = this.$context;
               if (!Integer.valueOf(var1).equals(this.mode)) {
                  this.mode = var1;
                  var1 = var3.getMDifferent();
                  if (var1 != 16) {
                     if (var1 != 17) {
                        return;
                     }

                     var1 = DataHandleUtils.getIntFromByteWithBit(var3.mCanbusInfoInt[2], 0, 4);
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 != 3) {
                              if (var1 != 4) {
                                 switch (var1) {
                                    case 8:
                                       this.list.add(new PanoramicBtnUpdateEntity(1, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(2, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(3, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(4, true));
                                       this.list.add(new PanoramicBtnUpdateEntity(5, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(6, false));
                                       break;
                                    case 9:
                                       this.list.add(new PanoramicBtnUpdateEntity(1, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(2, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(3, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(4, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(5, true));
                                       this.list.add(new PanoramicBtnUpdateEntity(6, false));
                                       break;
                                    case 10:
                                       this.list.add(new PanoramicBtnUpdateEntity(1, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(2, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(3, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(4, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(5, false));
                                       this.list.add(new PanoramicBtnUpdateEntity(6, true));
                                 }
                              } else {
                                 this.list.add(new PanoramicBtnUpdateEntity(0, false));
                                 this.list.add(new PanoramicBtnUpdateEntity(1, false));
                                 this.list.add(new PanoramicBtnUpdateEntity(2, false));
                                 this.list.add(new PanoramicBtnUpdateEntity(3, true));
                                 this.list.add(new PanoramicBtnUpdateEntity(4, false));
                                 this.list.add(new PanoramicBtnUpdateEntity(5, false));
                                 this.list.add(new PanoramicBtnUpdateEntity(6, false));
                              }
                           } else {
                              this.list.add(new PanoramicBtnUpdateEntity(0, false));
                              this.list.add(new PanoramicBtnUpdateEntity(1, false));
                              this.list.add(new PanoramicBtnUpdateEntity(2, true));
                              this.list.add(new PanoramicBtnUpdateEntity(3, false));
                              this.list.add(new PanoramicBtnUpdateEntity(4, false));
                              this.list.add(new PanoramicBtnUpdateEntity(5, false));
                              this.list.add(new PanoramicBtnUpdateEntity(6, false));
                           }
                        } else {
                           this.list.add(new PanoramicBtnUpdateEntity(0, false));
                           this.list.add(new PanoramicBtnUpdateEntity(1, true));
                           this.list.add(new PanoramicBtnUpdateEntity(2, false));
                           this.list.add(new PanoramicBtnUpdateEntity(3, false));
                           this.list.add(new PanoramicBtnUpdateEntity(4, false));
                           this.list.add(new PanoramicBtnUpdateEntity(5, false));
                           this.list.add(new PanoramicBtnUpdateEntity(6, false));
                        }
                     } else {
                        this.list.add(new PanoramicBtnUpdateEntity(0, true));
                        this.list.add(new PanoramicBtnUpdateEntity(1, false));
                        this.list.add(new PanoramicBtnUpdateEntity(2, false));
                        this.list.add(new PanoramicBtnUpdateEntity(3, false));
                        this.list.add(new PanoramicBtnUpdateEntity(4, false));
                        this.list.add(new PanoramicBtnUpdateEntity(5, false));
                        this.list.add(new PanoramicBtnUpdateEntity(6, false));
                     }

                     Log.d("Lai", String.valueOf(var3.getMDifferent()));
                  } else {
                     var1 = DataHandleUtils.getIntFromByteWithBit(var3.mCanbusInfoInt[2], 0, 4);
                     if (var1 != 5) {
                        if (var1 != 6) {
                           if (var1 == 7) {
                              this.list.add(new PanoramicBtnUpdateEntity(0, false));
                              this.list.add(new PanoramicBtnUpdateEntity(1, false));
                              this.list.add(new PanoramicBtnUpdateEntity(2, true));
                           }
                        } else {
                           this.list.add(new PanoramicBtnUpdateEntity(0, false));
                           this.list.add(new PanoramicBtnUpdateEntity(1, true));
                           this.list.add(new PanoramicBtnUpdateEntity(2, false));
                        }
                     } else {
                        this.list.add(new PanoramicBtnUpdateEntity(0, true));
                        this.list.add(new PanoramicBtnUpdateEntity(1, false));
                        this.list.add(new PanoramicBtnUpdateEntity(2, false));
                     }
                  }

                  GeneralParkData.dataList = (List)this.list;
                  var3.updateParkUi((Bundle)null, var4);
               }
            }

         }
      });
      var3.append(65, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               ArrayList var2 = this.list;
               MsgMgr var3 = this.this$0;
               var2.clear();
               var1 = var3.mCanbusInfoInt[2];
               SettingUpdateEntity var4 = (SettingUpdateEntity)var3.mSettingItemIndexHashMap.get("_284_setting_item_2B");
               if (var4 != null) {
                  var2.add(var4.setValue(var1 >> 7 & 1));
               }

               var4 = (SettingUpdateEntity)var3.mSettingItemIndexHashMap.get("_161_anti_collision");
               if (var4 != null) {
                  var2.add(var4.setValue(var1 >> 6 & 1));
               }

               var4 = (SettingUpdateEntity)var3.mSettingItemIndexHashMap.get("_161_decoder_voice");
               if (var4 != null) {
                  var2.add(var4.setValue(var1 >> 2 & 1));
               }

               var4 = (SettingUpdateEntity)var3.mSettingItemIndexHashMap.get("_118_setting_title_17");
               if (var4 != null) {
                  var2.add(var4.setValue(var1 >> 1 & 1));
               }

               SettingUpdateEntity var5 = (SettingUpdateEntity)var3.mSettingItemIndexHashMap.get("_161_light_assist");
               if (var5 != null) {
                  var2.add(var5.setValue(var1 & 1));
               }

               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var3.append(67, new Parser(this, var1) {
         final Context $context;
         private boolean isDriverSeatStatus;
         private boolean isPassengerSeatStatus;
         private boolean isPopup;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            boolean var2;
            if ((this.this$0.mCanbusInfoInt[2] >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            boolean var3;
            if ((this.this$0.mCanbusInfoInt[3] >> 7 & 1) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            SettingUpdateEntity var4;
            if (this.isDriverSeatStatus != var2 || !this.isPopup) {
               this.isDriverSeatStatus = var2;
               if (var2) {
                  var4 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_161_driver_massage_switch");
                  if (var4 != null) {
                     this.this$0.startSettingActivity(this.$context, var4.getLeftListIndex(), var4.getRightListIndex());
                     this.isPopup = true;
                  }
               }
            }

            if (this.isPassengerSeatStatus != var3 || !this.isPopup) {
               this.isPassengerSeatStatus = var3;
               if (var3) {
                  var4 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_161_passenger_massage_switch");
                  if (var4 != null) {
                     this.this$0.startSettingActivity(this.$context, var4.getLeftListIndex(), var4.getRightListIndex());
                     this.isPopup = true;
                  }
               }
            }

            if (!this.isDriverSeatStatus && !this.isPassengerSeatStatus && SystemUtil.isForeground(this.$context, Reflection.getOrCreateKotlinClass(SettingActivity.class).getQualifiedName()) && this.isPopup) {
               this.this$0.finishActivity();
               this.isPopup = false;
            }

            if (this.isDataChange()) {
               ArrayList var5 = this.list;
               MsgMgr var7 = this.this$0;
               var5.clear();
               var1 = var7.mCanbusInfoInt[2];
               SettingUpdateEntity var6 = (SettingUpdateEntity)var7.mSettingItemIndexHashMap.get("_161_driver_massage_switch");
               if (var6 != null) {
                  var5.add(var6.setValue(var1 >> 6 & 1));
               }

               var6 = (SettingUpdateEntity)var7.mSettingItemIndexHashMap.get("_161_driver_massage_intensity");
               if (var6 != null) {
                  var5.add(var6.setValue(var1 >> 4 & 3));
               }

               var6 = (SettingUpdateEntity)var7.mSettingItemIndexHashMap.get("_161_driver_massage_mode");
               if (var6 != null) {
                  var5.add(var6.setValue(DataHandleUtils.getIntFromByteWithBit(var7.mCanbusInfoInt[2], 0, 4) - 1));
               }

               var1 = var7.mCanbusInfoInt[3];
               var6 = (SettingUpdateEntity)var7.mSettingItemIndexHashMap.get("_161_passenger_massage_switch");
               if (var6 != null) {
                  var5.add(var6.setValue(var1 >> 6 & 1));
               }

               var6 = (SettingUpdateEntity)var7.mSettingItemIndexHashMap.get("_161_passenger_massage_intensity");
               if (var6 != null) {
                  var5.add(var6.setValue(var1 >> 4 & 3));
               }

               var6 = (SettingUpdateEntity)var7.mSettingItemIndexHashMap.get("_161_passenger_massage_mode");
               if (var6 != null) {
                  var5.add(var6.setValue(DataHandleUtils.getIntFromByteWithBit(var7.mCanbusInfoInt[3], 0, 4) - 1));
               }

               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var3.append(80, new Parser(this, var1) {
         final Context $context;
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$FoL3jEJyqr0_CrL2ORzDHucsH_8(MsgMgr var0, Context var1, Object var2) {
            setOnParseListeners$lambda_25(var0, var1, var2);
         }

         // $FF: synthetic method
         public static void $r8$lambda$g8Ru0P5JkKyp4q4oxs5JqnKCoJY(MsgMgr var0, Context var1, Object var2) {
            setOnParseListeners$lambda_3(var0, var1, var2);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_25(MsgMgr var0, Context var1, Object var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            DriverUpdateEntity var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_0");
            Object var5 = null;
            String var3;
            ArrayList var4;
            ArrayList var6;
            if (var7 != null) {
               if (DataHandleUtils.getBoolBit7(var0.mCanbusInfoInt[3])) {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               var6 = var2.list;
               var4 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var4 = null;
               }

               var4.add(var7.setValue(var3));
            }

            var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_1");
            if (var7 != null) {
               if (DataHandleUtils.getBoolBit6(var0.mCanbusInfoInt[3])) {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               var6 = var2.list;
               var4 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var4 = null;
               }

               var4.add(var7.setValue(var3));
            }

            var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_2");
            if (var7 != null) {
               if (DataHandleUtils.getBoolBit5(var0.mCanbusInfoInt[3])) {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               var6 = var2.list;
               var4 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var4 = null;
               }

               var4.add(var7.setValue(var3));
            }

            var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_3");
            if (var7 != null) {
               if (DataHandleUtils.getBoolBit4(var0.mCanbusInfoInt[3])) {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               var6 = var2.list;
               var4 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var4 = null;
               }

               var4.add(var7.setValue(var3));
            }

            var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_4");
            if (var7 != null) {
               if (DataHandleUtils.getBoolBit3(var0.mCanbusInfoInt[3])) {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               var6 = var2.list;
               var4 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var4 = null;
               }

               var4.add(var7.setValue(var3));
            }

            var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_5");
            if (var7 != null) {
               if (DataHandleUtils.getBoolBit2(var0.mCanbusInfoInt[3])) {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var3 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               var6 = var2.list;
               var4 = var6;
               if (var6 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var4 = null;
               }

               var4.add(var7.setValue(var3));
            }

            DriverUpdateEntity var10 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_6");
            if (var10 != null) {
               String var8;
               if (DataHandleUtils.getBoolBit1(var0.mCanbusInfoInt[3])) {
                  var8 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var8 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               ArrayList var9 = var2.list;
               if (var9 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var9 = (ArrayList)var5;
               }

               var9.add(var10.setValue(var8));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Context var1, Object var2) {
            // $FF: Couldn't be decompiled
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$22$$ExternalSyntheticLambda0(this.this$0, this.$context, this), new MsgMgr$initParsers$1$22$$ExternalSyntheticLambda1(this.this$0, this.$context, this)};
         }
      });
      var3.append(81, new Parser(this) {
         private ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$hthDaRZncMYVdWsUqbx7d0327XQ(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_4(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[3];
            ArrayList var4 = var1.list;
            ArrayList var3 = var4;
            if (var4 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var3 = null;
            }

            SettingUpdateEntity var6 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_Time_Interval");
            if (var6 != null) {
               var3.add(var6.setValue(var0.mCanbusInfoInt[3]));
            }

            var3 = var1.list;
            ArrayList var5 = var3;
            if (var3 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("list");
               var5 = null;
            }

            var0.updateGeneralSettingData((List)var5);
            var0.updateSettingActivity((Bundle)null);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralSettingData((List)var2);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{null, new MsgMgr$initParsers$1$23$$ExternalSyntheticLambda0(this.this$0, this)};
         }
      });
      var3.append(82, new Parser(this, var1) {
         final Context $context;
         private ArrayList list;
         private ArrayList list1;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$cX74yJvukTrha_JYVTcImEt_jAU(MsgMgr var0, Object var1, Context var2) {
            setOnParseListeners$lambda_3(var0, var1, var2);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1, Context var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            Intrinsics.checkNotNullParameter(var2, "$context");
            DriverUpdateEntity var7 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_2_0");
            Object var4 = null;
            if (var7 != null) {
               String var6 = (var0.mCanbusInfoInt[2] * 256 + var0.mCanbusInfoInt[3]) / 60 + " : " + (var0.mCanbusInfoInt[2] * 256 + var0.mCanbusInfoInt[3]) % 60;
               ArrayList var5 = var1.list;
               ArrayList var3 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var3 = null;
               }

               var3.add(var7.setValue(var6));
            }

            SettingUpdateEntity var10 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_Delay_Charge");
            if (var10 != null) {
               StringCompanionObject var11 = StringCompanionObject.INSTANCE;
               String var12 = String.format("%02d", Arrays.copyOf(new Object[]{MsgMgrKt.access$range((var0.mCanbusInfoInt[2] * 256 + var0.mCanbusInfoInt[3]) % 60, 59)}, 1));
               Intrinsics.checkNotNullExpressionValue(var12, "format(format, *args)");
               StringCompanionObject var13 = StringCompanionObject.INSTANCE;
               String var8 = String.format("%2d", Arrays.copyOf(new Object[]{MsgMgrKt.access$range((var0.mCanbusInfoInt[2] * 256 + var0.mCanbusInfoInt[3]) / 60, 23)}, 1));
               Intrinsics.checkNotNullExpressionValue(var8, "format(format, *args)");
               var10.setValue(var8 + CommUtil.getStrByResId(var2, "_161_driveInfo_0_2_1") + var12 + CommUtil.getStrByResId(var2, "_161_driveInfo_0_2_2"));
               var10.setValueStr(true);
               ArrayList var9 = var1.list1;
               if (var9 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list1");
                  var9 = (ArrayList)var4;
               }

               var9.add(var10.setValue(var10.getValue()));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               this.list1 = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
               var4 = this.this$0;
               var3 = this.list1;
               var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list1");
                  var2 = null;
               }

               var4.updateGeneralSettingData((List)var2);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$24$$ExternalSyntheticLambda0(this.this$0, this, this.$context), null};
         }
      });
      var3.append(83, new Parser(this, var1) {
         final Context $context;
         private ArrayList list;
         private ArrayList list1;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$fF5QFpJbyhF_XViCresA1ORFlb4(MsgMgr var0, Context var1, Object var2) {
            setOnParseListeners$lambda_6(var0, var1, var2);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0, Context var1, Object var2) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            DriverUpdateEntity var8 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_3_0");
            Object var6 = null;
            ArrayList var5;
            if (var8 != null) {
               String var4;
               if (DataHandleUtils.getBoolBit7(var0.mCanbusInfoInt[2])) {
                  var4 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_1");
               } else {
                  var4 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_0_0");
               }

               ArrayList var7 = var2.list;
               var5 = var7;
               if (var7 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var5 = null;
               }

               var5.add(var8.setValue(var4));
            }

            DriverUpdateEntity var15 = (DriverUpdateEntity)var0.mDriveItemIndexHashMap.get("_161_driveInfo_0_3_1");
            ArrayList var13;
            if (var15 != null) {
               int var3 = DataHandleUtils.getIntFromByteWithBit(var0.mCanbusInfoInt[2], 0, 2);
               String var10;
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        var10 = CommUtil.getStrByResId(var1, "set_default");
                     } else {
                        var10 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_1_2");
                     }
                  } else {
                     var10 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_1_1");
                  }
               } else {
                  var10 = CommUtil.getStrByResId(var1, "_161_driveInfo_0_3_1_0");
               }

               var5 = var2.list;
               var13 = var5;
               if (var5 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var13 = null;
               }

               var13.add(var15.setValue(var10));
            }

            SettingUpdateEntity var14 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_driveInfo_0_3_0");
            if (var14 != null) {
               var14.setValue(var0.mCanbusInfoInt[2] >> 7);
               var13 = var2.list1;
               ArrayList var11 = var13;
               if (var13 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list1");
                  var11 = null;
               }

               var11.add(var14.setValue(var14.getValue()));
            }

            SettingUpdateEntity var12 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_161_Power_Reserve");
            if (var12 != null) {
               if (var0.mCanbusInfoInt[2] >> 7 == 0) {
                  var12.setValue("OFF");
               } else {
                  var12.setValue(DataHandleUtils.getIntFromByteWithBit(var0.mCanbusInfoInt[2], 0, 2));
               }

               var12.setValueStr(true);
               ArrayList var9 = var2.list1;
               if (var9 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list1");
                  var9 = (ArrayList)var6;
               }

               var9.add(var12.setValue(var12.getValue()));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list = new ArrayList();
               this.list1 = new ArrayList();
               super.parse(var1);
               MsgMgr var4 = this.this$0;
               ArrayList var3 = this.list;
               ArrayList var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list");
                  var2 = null;
               }

               var4.updateGeneralDriveData((List)var2);
               this.this$0.updateDriveDataActivity((Bundle)null);
               var4 = this.this$0;
               var3 = this.list1;
               var2 = var3;
               if (var3 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("list1");
                  var2 = null;
               }

               var4.updateGeneralSettingData((List)var2);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$25$$ExternalSyntheticLambda0(this.this$0, this.$context, this)};
         }
      });
      var3.append(96, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            StringBuilder var4 = (new StringBuilder()).append("parse: 记录仪状态 ");
            var1 = this.this$0.mCanbusInfoInt[2] >> 7 & 1;
            String var3 = "";
            String var2;
            if (var1 != 0) {
               if (var1 != 1) {
                  var2 = "";
               } else {
                  var2 = "激活";
               }
            } else {
               var2 = "未激活";
            }

            Log.i("_1161_MsgMgr", var4.append(var2).toString());
            StringBuilder var5 = (new StringBuilder()).append("parse: 行车录像 ");
            var1 = this.this$0.mCanbusInfoInt[2] >> 6 & 1;
            String var6 = "ON";
            if (var1 != 0) {
               if (var1 != 1) {
                  var2 = "";
               } else {
                  var2 = "ON";
               }
            } else {
               var2 = "OFF";
            }

            Log.i("_1161_MsgMgr", var5.append(var2).toString());
            var5 = (new StringBuilder()).append("parse: 自动紧急录像 ");
            var1 = this.this$0.mCanbusInfoInt[2] >> 5 & 1;
            if (var1 != 0) {
               var2 = var6;
               if (var1 != 1) {
                  var2 = "";
               }
            } else {
               var2 = "OFF";
            }

            Log.i("_1161_MsgMgr", var5.append(var2).toString());
            var4 = (new StringBuilder()).append("parse: 保存状态 ");
            var1 = this.this$0.mCanbusInfoInt[2] & 3;
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        var2 = "";
                     } else {
                        var2 = "保存失败";
                     }
                  } else {
                     var2 = "保存成功";
                  }
               } else {
                  var2 = "保存中";
               }
            } else {
               var2 = "正常";
            }

            Log.i("_1161_MsgMgr", var4.append(var2).toString());
            var4 = (new StringBuilder()).append("parse: 录像播放状态 ");
            switch (this.this$0.mCanbusInfoInt[3]) {
               case 0:
                  var2 = "未播放";
                  break;
               case 1:
                  var2 = "播放";
                  break;
               case 2:
                  var2 = "暂停";
                  break;
               case 3:
                  var2 = "停止";
                  break;
               case 4:
                  var2 = "快退";
                  break;
               case 5:
                  var2 = "快进";
                  break;
               case 6:
                  var2 = "上一个录像";
                  break;
               case 7:
                  var2 = "下一个录像";
                  break;
               default:
                  var2 = "";
            }

            Log.i("_1161_MsgMgr", var4.append(var2).toString());
            var4 = (new StringBuilder()).append("parse: 记录仪状态2 ");
            var1 = this.this$0.mCanbusInfoInt[4];
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        var2 = var3;
                     } else {
                        var2 = "请插入SD卡并关闭卡门";
                     }
                  } else {
                     var2 = "行车录影功能不可用";
                  }
               } else {
                  var2 = "SD卡格式异常或损坏";
               }
            } else {
               var2 = "正常";
            }

            Log.i("_1161_MsgMgr", var4.append(var2).toString());
            Log.i("_1161_MsgMgr", "parse: SD卡内存占比 " + this.this$0.mCanbusInfoInt[5] + '%');
         }
      });
      var3.append(97, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            Log.i("_1161_MsgMgr", "parse: 列表号 " + this.this$0.mCanbusInfoInt[2]);
            StringBuilder var3 = (new StringBuilder()).append("parse: 字符格式 ");
            var1 = this.this$0.mCanbusInfoInt[3];
            String var2;
            if (var1 != 0) {
               if (var1 != 1) {
                  var2 = "";
               } else {
                  var2 = "ASKII";
               }
            } else {
               var2 = "Unicode";
            }

            Log.i("_1161_MsgMgr", var3.append(var2).toString());
            Log.i("_1161_MsgMgr", "parse: 总页码 " + (this.this$0.mCanbusInfoInt[2] << 8 & this.this$0.mCanbusInfoInt[3]));
            Log.i("_1161_MsgMgr", "parse: 当前页码 " + (this.this$0.mCanbusInfoInt[4] << 8 & this.this$0.mCanbusInfoInt[5]));
            var3 = (new StringBuilder()).append("parse: 字符 {");
            byte[] var4 = Arrays.copyOfRange(this.this$0.mCanbusInfoByte, 8, this.this$0.mCanbusInfoByte.length);
            Intrinsics.checkNotNullExpressionValue(var4, "copyOfRange(mCanbusInfoB… 8, mCanbusInfoByte.size)");
            Log.i("_1161_MsgMgr", var3.append(new String(var4, Charsets.UTF_8)).append('}').toString());
         }
      });
      var3.append(127, new Parser(this, var1) {
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
      var3.append(197, new Parser(this, var1) {
         final Context $context;
         private final int[] amplifierData;
         private final ArrayList list;
         private final int[] settingsData;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
            this.amplifierData = new int[3];
            this.settingsData = new int[2];
         }

         public void parse(int var1) {
            int[] var3 = this.this$0.mCanbusInfoInt;
            MsgMgr var2 = this.this$0;
            Context var4 = this.$context;
            if (!Arrays.equals(ArraysKt.copyOfRange(var3, 4, 7), this.amplifierData)) {
               int[] var5 = this.amplifierData;
               ArraysKt.copyInto$default(var3, var5, 0, 4, 7, 2, (Object)null);
               GeneralAmplifierData.frontRear = var3[2] - 9;
               GeneralAmplifierData.leftRight = var3[3] - 9;
               GeneralAmplifierData.bandBass = var3[4];
               GeneralAmplifierData.bandTreble = var3[5];
               GeneralAmplifierData.bandMiddle = var3[6];
               var2.updateAmplifierActivity((Bundle)null);
               var2.saveAmplifierData(var4, var2.getCanId());
            }

            if (!Arrays.equals(ArraysKt.copyOfRange(var3, 7, 9), this.settingsData)) {
               ArraysKt.copyInto$default(var3, this.settingsData, 0, 7, 9, 2, (Object)null);
               this.list.clear();
               SettingUpdateEntity var6 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("_143_0xAD_setting5");
               if (var6 != null) {
                  this.list.add(var6.setValue(var2.mCanbusInfoInt[7] >> 7 & 1));
               }

               var6 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("_143_0xAD_setting6");
               if (var6 != null) {
                  this.list.add(var6.setValue(var2.mCanbusInfoInt[7] >> 6 & 1));
               }

               var6 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("_143_0xAD_setting7");
               if (var6 != null) {
                  this.list.add(var6.setValue(var2.mCanbusInfoInt[7] & 15));
               }

               var6 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("_324_centrik_speaker");
               if (var6 != null) {
                  this.list.add(var6.setValue((var2.mCanbusInfoInt[8] >> 4 & 15) - 3).setProgress(var2.mCanbusInfoInt[8] >> 4 & 15));
               }

               var6 = (SettingUpdateEntity)var2.mSettingItemIndexHashMap.get("_55_0xa6_setting_1");
               if (var6 != null) {
                  this.list.add(var6.setValue((var2.mCanbusInfoInt[8] & 15) - 3).setProgress(var2.mCanbusInfoInt[8] & 15));
               }

               var2.updateGeneralSettingData((List)this.list);
               var2.updateSettingActivity((Bundle)null);
            }

         }
      });
      var3.append(198, new Parser(this) {
      });
   }

   private final void sendCommands(ArrayList var1) {
      int var2 = var1.size();
      if (var2 != 0) {
         if (var2 != 1) {
            TimerUtil var3 = new TimerUtil();
            var3.startTimer((TimerTask)(new TimerTask(var1, var3) {
               final TimerUtil $this_run;
               final ArrayList $this_sendCommands;
               private int i;

               {
                  this.$this_sendCommands = var1;
                  this.$this_run = var2;
               }

               public final int getI() {
                  return this.i;
               }

               public void run() {
                  if (this.i < this.$this_sendCommands.size()) {
                     CanbusMsgSender.sendMsg((byte[])this.$this_sendCommands.get(this.i));
                     ++this.i;
                  } else {
                     this.$this_run.stopTimer();
                  }

               }

               public final void setI(int var1) {
                  this.i = var1;
               }
            }), 0L, 100L);
         } else {
            CanbusMsgSender.sendMsg((byte[])CollectionsKt.first((List)var1));
         }
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.UiMgr");
      this.mUiMgr = (UiMgr)var2;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
      this.updateSettingActivity("_29_left_side", SharePreUtil.getIntValue(var1, "_161_lcd_mode_left", 0));
      this.updateSettingActivity("_29_right_side", SharePreUtil.getIntValue(var1, "_161_lcd_mode_right", 0));
   }

   public void atvInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 1});
   }

   public void auxInInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "artist");
      Intrinsics.checkNotNullParameter(var3, "album");
      ArrayList var5 = new ArrayList();
      var5.add(new byte[]{22, -64, 11, -1});
      byte[] var4 = var1.getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      var5.add(ArraysKt.plus(new byte[]{22, -61, 3}, var4));
      this.sendCommands(var5);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      ArrayList var4 = new ArrayList();
      var4.add(new byte[]{22, -64, 5, 1, 0});
      var4.add(new byte[]{22, -64, 5, -1});
      var4.add(ArraysKt.plus(new byte[]{22, -61, 3}, var1));
      this.sendCommands(var4);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "phoneNumber");
      ArrayList var4 = new ArrayList();
      var4.add(new byte[]{22, -64, 5, 1, 0});
      var4.add(new byte[]{22, -64, 5, -1});
      var4.add(ArraysKt.plus(new byte[]{22, -61, 3}, var1));
      this.sendCommands(var4);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "getByteArrayToIntArray(canbusInfo)");
      this.mCanbusInfoInt = var3;
      this.mCanbusInfoByte = var2;
      this.mContext = var1;
      Parser var4 = (Parser)this.mParserArray.get(var3[1]);
      if (var4 != null) {
         var4.parse(this.mCanbusInfoInt.length - 2);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      short var3 = 0;
      if (var2) {
         var3 = 128;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(var1 | var3)});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var2, (byte)var3, (byte)var4, (byte)var8, (byte)var6});
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda0(), 100L);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      Intrinsics.checkNotNullParameter(var11, "song");
      Intrinsics.checkNotNullParameter(var12, "album");
      Intrinsics.checkNotNullParameter(var13, "artist");
      ArrayList var15 = new ArrayList();
      if (var7 == 1 || var7 == 5) {
         var4 = var5;
      }

      if (this.mDiscCurrent != var4 || this.mDiscTotal != var6) {
         this.mDiscCurrent = var4;
         this.mDiscTotal = var6;
         var15.add(new byte[]{22, -64, 2, 1, (byte)(var6 >> 8 & 255), (byte)(var6 & 255), (byte)(var4 >> 8 & 255), (byte)(var4 & 255)});
      }

      if (!Intrinsics.areEqual((Object)this.mDiscTitle, (Object)var11)) {
         this.mDiscTitle = var11;
         var15.add(new byte[]{22, -64, 2, -1});
         byte[] var14 = var11.getBytes(Charsets.UTF_8);
         Intrinsics.checkNotNullExpressionValue(var14, "this as java.lang.String).getBytes(charset)");
         var15.add(ArraysKt.plus(new byte[]{22, -61, 3}, var14));
      }

      this.sendCommands(var15);
   }

   public void dtvInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, -1});
   }

   public final Activity getCurrentActivity() {
      return (Activity)this.getActivity();
   }

   public final byte[] getM0x3BDatas() {
      return this.m0x3BDatas;
   }

   public final byte[] getM0x3DDatas() {
      return this.m0x3DDatas;
   }

   public final int getMDifferent() {
      return this.mDifferent;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Intrinsics.checkNotNullParameter(var21, "songTitle");
      Intrinsics.checkNotNullParameter(var22, "songAlbum");
      Intrinsics.checkNotNullParameter(var23, "songArtist");
      if (var1 != 9) {
         ArrayList var25 = new ArrayList();
         var25.add(new byte[]{22, -64, 8, 1, (byte)(var4 >> 8 & 255), (byte)(var4 & 255), var9, (byte)var3});
         var25.add(new byte[]{22, -64, 8, -1});
         byte[] var26 = var21.getBytes(Charsets.UTF_8);
         Intrinsics.checkNotNullExpressionValue(var26, "this as java.lang.String).getBytes(charset)");
         var25.add(ArraysKt.plus(new byte[]{22, -61, 3}, var26));
         this.sendCommands(var25);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      Intrinsics.checkNotNullParameter(var2, "currBand");
      Intrinsics.checkNotNullParameter(var3, "currentFreq");
      Intrinsics.checkNotNullParameter(var4, "psName");
      Pair var6;
      if (StringsKt.contains$default((CharSequence)var2, (CharSequence)"FM", false, 2, (Object)null)) {
         var6 = new Pair(0, (int)(Float.parseFloat(var3) * (float)100));
      } else {
         var6 = new Pair(16, Integer.parseInt(var3));
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, (byte)((Number)var6.getFirst()).intValue(), (byte)(((Number)var6.getSecond()).intValue() >> 8 & 255), (byte)(((Number)var6.getSecond()).intValue() & 255), (byte)var1});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (var1 == null) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 0});
      } else if (Intrinsics.areEqual((Object)var1, (Object)"RADIO")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 0});
      } else if (Intrinsics.areEqual((Object)var1, (Object)"THIRD")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 0});
      }

   }

   public final void updateSettingActivity(String var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "value");
      SettingUpdateEntity var3 = (SettingUpdateEntity)this.mSettingItemIndexHashMap.get(var1);
      if (var3 != null) {
         ArrayList var4 = new ArrayList();
         var4.add(var3.setValue(var2).setProgress((Integer)var2));
         this.updateGeneralSettingData((List)var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   public final void updateSettings(int var1, int var2, int var3) {
      List var4 = (List)(new ArrayList());
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 1, (byte)(var4 >> 8 & 255), (byte)(var4 & 255), var9, (byte)var3});
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr$Companion;", "", "()V", "TAG", "", "_161_VEHICLE_CONFIG", "CanBusInfo_release"},
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
      d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_161/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
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
         Log.i("_1161_MsgMgr", "Parser: " + var2 + " length " + var3);
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
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"},
      d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr$VehicleType;", "", "()V", "Companion", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class VehicleType {
      public static final int AIRCROSS_4008 = 26;
      public static final int AIRPANEL = 32;
      public static final int BERLINGO = 27;
      public static final int BRAZILIAN_C4 = 25;
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      public static final int ORIGINAL_CAR_SMALL_SCREEN = 33;
      public static final int Opel = 70;
      public static final int V04_05_XSARA = 28;
      public static final int V04_07_307 = 30;
      public static final int V06_10_C_TRIOMPHE = 3;
      public static final int V06_206 = 29;
      public static final int V07_14_307 = 21;
      public static final int V09_17_C4 = 11;
      public static final int V10_16_C4 = 7;
      public static final int V10_16_C5 = 5;
      public static final int V10_18_C4L = 12;
      public static final int V11_16_508 = 15;
      public static final int V12_18_308 = 23;
      public static final int V13_18_C_ELYSEE = 6;
      public static final int V13_18_DS5 = 10;
      public static final int V13_19_3008 = 16;
      public static final int V14_16_DS5LS = 9;
      public static final int V14_17_DS6 = 8;
      public static final int V14_18_301 = 20;
      public static final int V14_19_408 = 17;
      public static final int V14_19_C3_XR = 13;
      public static final int V14_20_2008 = 19;
      public static final int V15_18_308S = 22;
      public static final int V15_18_C4 = 1;
      public static final int V16_19_4008 = 18;
      public static final int V17_19_5008 = 14;
      public static final int V17_C5_AIRCROSS = 4;
      public static final int V19_508L = 24;
      public static final int V19_C4L = 2;

      @Metadata(
         d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b!\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006%"},
         d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr$VehicleType$Companion;", "", "()V", "AIRCROSS_4008", "", "AIRPANEL", "BERLINGO", "BRAZILIAN_C4", "ORIGINAL_CAR_SMALL_SCREEN", "Opel", "V04_05_XSARA", "V04_07_307", "V06_10_C_TRIOMPHE", "V06_206", "V07_14_307", "V09_17_C4", "V10_16_C4", "V10_16_C5", "V10_18_C4L", "V11_16_508", "V12_18_308", "V13_18_C_ELYSEE", "V13_18_DS5", "V13_19_3008", "V14_16_DS5LS", "V14_17_DS6", "V14_18_301", "V14_19_408", "V14_19_C3_XR", "V14_20_2008", "V15_18_308S", "V15_18_C4", "V16_19_4008", "V17_19_5008", "V17_C5_AIRCROSS", "V19_508L", "V19_C4L", "CanBusInfo_release"},
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
}
