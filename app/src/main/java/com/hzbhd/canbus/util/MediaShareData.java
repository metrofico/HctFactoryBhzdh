package com.hzbhd.canbus.util;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.adapter.BtAdapter;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.SystemStatusDef;
import com.hzbhd.constant.audio.AudioConstants;
import com.hzbhd.constant.media.MeidaConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.ui.util.BaseUtil;
import java.util.Arrays;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONObject;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\t\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"},
   d2 = {"Lcom/hzbhd/canbus/util/MediaShareData;", "", "()V", "DEFAULT_INT", "", "DEFAULT_STRING", "", "TAG", "msgMgrInterface", "Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "registerModuleListener", "", "context", "Landroid/content/Context;", "Bluetooth", "Misc", "Music", "Radio", "Screen", "Source", "System", "Video", "Volume", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MediaShareData {
   private static final int DEFAULT_INT = -1;
   private static final String DEFAULT_STRING = "";
   public static final MediaShareData INSTANCE = new MediaShareData();
   private static final String TAG = "MediaShareData";
   private static final MsgMgrInterface msgMgrInterface;

   static {
      msgMgrInterface = MsgMgrFactory.getCanMsgMgr(BaseUtil.INSTANCE.getContext());
   }

   private MediaShareData() {
   }

   public final void registerModuleListener(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      MediaShareData.System.INSTANCE.registerListener();
      MediaShareData.Misc.INSTANCE.registerListener();
      MediaShareData.Source.INSTANCE.registerListener(var1);
      MediaShareData.Screen.INSTANCE.registerListener();
      MediaShareData.Radio.INSTANCE.registerListener();
      MediaShareData.Bluetooth.INSTANCE.registerListener();
      MediaShareData.Music.INSTANCE.registerListener();
      MediaShareData.Video.INSTANCE.registerListener();
      MediaShareData.Volume.INSTANCE.registerListener();
   }

   @Metadata(
      d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016J\u001e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u001e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u001e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ6\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u001e\u0010!\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ&\u0010\"\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010#\u001a\u00020\bJ\u0006\u0010$\u001a\u00020\u0016J\u0006\u0010%\u001a\u00020\u0014J\u0006\u0010&\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Bluetooth;", "", "()V", "POST_DELAY", "", "btStatusRunnable", "Ljava/lang/Runnable;", "callStatus", "", "handler", "Landroid/os/Handler;", "isAudioTransferTowardsAG", "", "isCallingFlag", "isHfpConnected", "isMicMute", "notifyBtStatusRunnable", "phoneNumber", "", "btMusicId3InfoChange", "", "title", "", "artist", "album", "btPhoneHangUpInfoChange", "number", "isAudioAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "isHfpConn", "isCalling", "btPhoneTalkingInfoChange", "btPhoneTalkingWithTimeInfoChange", "time", "getCallState", "notifyBtStatusChange", "registerListener", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Bluetooth {
      public static final Bluetooth INSTANCE = new Bluetooth();
      private static final long POST_DELAY = 300L;
      private static final Runnable btStatusRunnable = new MediaShareData$Bluetooth$$ExternalSyntheticLambda0();
      private static int callStatus;
      private static final Handler handler = new Handler(Looper.getMainLooper());
      private static boolean isAudioTransferTowardsAG;
      private static boolean isCallingFlag;
      private static boolean isHfpConnected;
      private static boolean isMicMute;
      private static final Runnable notifyBtStatusRunnable = new MediaShareData$Bluetooth$$ExternalSyntheticLambda1();
      private static byte[] phoneNumber = new byte[0];

      // $FF: synthetic method
      public static void $r8$lambda$Ju3bvER_Fdt0yj4baSU9vn0rCnw() {
         btStatusRunnable$lambda_0();
      }

      // $FF: synthetic method
      public static void $r8$lambda$lnJUTfVqxe2Revy_pAnEO9gxDGY() {
         notifyBtStatusRunnable$lambda_1();
      }

      private Bluetooth() {
      }

      private static final void btStatusRunnable$lambda_0() {
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTPHONE) {
            MsgMgrInterface var0 = MediaShareData.msgMgrInterface;
            if (var0 != null) {
               var0.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG, 0, 0, (Bundle)null);
            }
         }

      }

      private static final void notifyBtStatusRunnable$lambda_1() {
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTPHONE) {
            MsgMgrInterface var0 = MediaShareData.msgMgrInterface;
            if (var0 != null) {
               var0.notifyBtStatusChange();
            }
         }

      }

      public final void btMusicId3InfoChange(String var1, String var2, String var3) {
         Intrinsics.checkNotNullParameter(var1, "title");
         Intrinsics.checkNotNullParameter(var2, "artist");
         Intrinsics.checkNotNullParameter(var3, "album");
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTAUDIO) {
            MsgMgrInterface var4 = MediaShareData.msgMgrInterface;
            if (var4 != null) {
               var4.btMusicId3InfoChange(var1, var2, var3);
            }
         }

      }

      public final void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
         Intrinsics.checkNotNullParameter(var1, "number");
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTPHONE) {
            MsgMgrInterface var4 = MediaShareData.msgMgrInterface;
            if (var4 != null) {
               var4.btPhoneHangUpInfoChange(var1, var2, var3);
            }
         }

      }

      public final void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
         Intrinsics.checkNotNullParameter(var1, "number");
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTPHONE) {
            MsgMgrInterface var4 = MediaShareData.msgMgrInterface;
            if (var4 != null) {
               var4.btPhoneIncomingInfoChange(var1, var2, var3);
            }
         }

      }

      public final void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
         Intrinsics.checkNotNullParameter(var1, "number");
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTPHONE) {
            MsgMgrInterface var4 = MediaShareData.msgMgrInterface;
            if (var4 != null) {
               var4.btPhoneOutGoingInfoChange(var1, var2, var3);
            }
         }

      }

      public final void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6) {
         Intrinsics.checkNotNullParameter(var2, "number");
         callStatus = callStatus;
         phoneNumber = var2;
         isHfpConnected = var3;
         isCallingFlag = var4;
         isMicMute = isMicMute;
         isAudioTransferTowardsAG = var6;
         Handler var8 = handler;
         Runnable var7 = btStatusRunnable;
         var8.removeCallbacks(var7);
         var8.postDelayed(var7, 300L);
      }

      public final void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
         Intrinsics.checkNotNullParameter(var1, "number");
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTPHONE) {
            MsgMgrInterface var4 = MediaShareData.msgMgrInterface;
            if (var4 != null) {
               var4.btPhoneTalkingInfoChange(var1, var2, var3);
            }
         }

      }

      public final void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
         Intrinsics.checkNotNullParameter(var1, "number");
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.BTPHONE) {
            MsgMgrInterface var5 = MediaShareData.msgMgrInterface;
            if (var5 != null) {
               var5.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
            }
         }

      }

      public final String getCallState() {
         int var1 = callStatus;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  return var1 != 5 ? "NO_CALL" : "END_CALL";
               } else {
                  return "CALLING";
               }
            } else {
               return "OUT_CALLING";
            }
         } else {
            return "IN_CALLING";
         }
      }

      public final void notifyBtStatusChange() {
         Handler var1 = handler;
         Runnable var2 = notifyBtStatusRunnable;
         var1.removeCallbacks(var2);
         var1.postDelayed(var2, 300L);
      }

      public final void registerListener() {
         BtAdapter.INSTANCE.registerListener();
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Misc;", "", "()V", "<set-?>", "", "miscReverse", "getMiscReverse", "()I", "registerListener", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Misc {
      public static final Misc INSTANCE = new Misc();
      private static int miscReverse;

      // $FF: synthetic method
      public static void $r8$lambda$BhNusKe9XbXXsw0rBaPMkmBd_hk(int var0) {
         registerListener$lambda_0(var0);
      }

      private Misc() {
      }

      private static final void registerListener$lambda_0(int var0) {
         miscReverse = var0;
      }

      public final int getMiscReverse() {
         return miscReverse;
      }

      public final void registerListener() {
         ShareDataManager.getInstance().registerShareIntListener("misc.Reverse", new MediaShareData$Misc$$ExternalSyntheticLambda0());
      }
   }

   @Metadata(
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b-\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010F\u001a\u00020GH\u0002J\u0006\u0010H\u001a\u00020GR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010!\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR$\u0010#\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR$\u0010&\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0007\"\u0004\b(\u0010\tR$\u0010)\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR$\u0010,\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001fR\u000e\u0010/\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00100\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0007\"\u0004\b2\u0010\tR$\u00103\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0007\"\u0004\b5\u0010\tR$\u00106\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0007\"\u0004\b8\u0010\tR$\u00109\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u000e\u0010>\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Music;", "", "()V", "value", "", "aPlayIndex", "getAPlayIndex", "()I", "setAPlayIndex", "(I)V", "currentAllMinute", "", "currentAllMinuteStr", "currentHour", "currentHourStr", "", "currentMinute", "currentMinuteStr", "currentPlayingIndexHigh", "currentPlayingIndexLow", "currentPos", "", "currentSecond", "currentSecondStr", "isPlaying", "", "isReportFromPlay", "mAlbumName", "getMAlbumName", "()Ljava/lang/String;", "setMAlbumName", "(Ljava/lang/String;)V", "<set-?>", "mAlbumPath", "getMAlbumPath", "mArtist", "getMArtist", "setMArtist", "mDuration", "getMDuration", "setMDuration", "mName", "getMName", "setMName", "mPath", "getMPath", "setMPath", "mPlayIndex", "mPlayMode", "getMPlayMode", "setMPlayMode", "mPlaySize", "getMPlaySize", "setMPlaySize", "mPlayState", "getMPlayState", "setMPlayState", "mPos", "getMPos", "()J", "setMPos", "(J)V", "playModel", "playRatio", "songAlbum", "songArtist", "songTitle", "storagePath", "totalCount", "totalTime", "infoChange", "", "registerListener", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Music {
      public static final Music INSTANCE = new Music();
      private static int aPlayIndex;
      private static byte currentAllMinute;
      private static byte currentAllMinuteStr;
      private static byte currentHour;
      private static String currentHourStr = "";
      private static byte currentMinute;
      private static String currentMinuteStr = "";
      private static byte currentPlayingIndexHigh;
      private static int currentPlayingIndexLow;
      private static long currentPos;
      private static byte currentSecond;
      private static String currentSecondStr = "";
      private static boolean isPlaying;
      private static boolean isReportFromPlay;
      private static String mAlbumName = "";
      private static String mAlbumPath = "";
      private static String mArtist = "";
      private static int mDuration;
      private static String mName = "";
      private static String mPath = "";
      private static int mPlayIndex;
      private static int mPlayMode;
      private static int mPlaySize;
      private static int mPlayState;
      private static long mPos;
      private static byte playModel;
      private static byte playRatio;
      private static String songAlbum = "";
      private static String songArtist = "";
      private static String songTitle = "";
      private static byte storagePath;
      private static int totalCount;
      private static long totalTime;

      // $FF: synthetic method
      public static void $r8$lambda$HHlxp0hjq3545RUabTfum66EgJ8(String var0) {
         registerListener$lambda_9(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$SdceMqTIzzFT7pFSeeNORI1XAb0(int var0) {
         registerListener$lambda_0(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$Sl_f9Gxmw_HmTvmj0K3VjGPwYAQ(String var0) {
         registerListener$lambda_2(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$kGv1csIe6HySfq2_zOURts_fW9Q(String var0) {
         registerListener$lambda_4(var0);
      }

      private Music() {
      }

      private final void infoChange() {
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.MUSIC) {
            MsgMgrInterface var1 = MediaShareData.msgMgrInterface;
            if (var1 != null) {
               var1.musicInfoChange(storagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playModel, mPlayIndex, isPlaying, totalTime, songTitle, songAlbum, songArtist, isReportFromPlay);
            }
         }

      }

      private static final void registerListener$lambda_0(int var0) {
         Log.i("MediaShareData", "Music: SHARE_MUSIC_PLAY_STATE [" + var0 + ']');
         Music var1 = INSTANCE;
         var1.setMPlayState(var0);
         var1.infoChange();
      }

      private static final void registerListener$lambda_2(String var0) {
         Log.i("MediaShareData", "Music: SHARE_MUSIC_SONG_INFO [" + var0 + ']');
         JSONObject var1 = new JSONObject(var0);
         Music var3 = INSTANCE;
         String var2 = var1.optString("path");
         Intrinsics.checkNotNullExpressionValue(var2, "optString(\"path\")");
         var3.setMPath(var2);
         var2 = var1.optString("name");
         Intrinsics.checkNotNullExpressionValue(var2, "optString(\"name\")");
         var3.setMName(var2);
         var2 = var1.optString("artist");
         Intrinsics.checkNotNullExpressionValue(var2, "optString(\"artist\")");
         var3.setMArtist(var2);
         var2 = var1.optString("albumName");
         Intrinsics.checkNotNullExpressionValue(var2, "optString(\"albumName\")");
         var3.setMAlbumName(var2);
         String var4 = var1.optString("albumPath");
         Intrinsics.checkNotNullExpressionValue(var4, "optString(\"albumPath\")");
         mAlbumPath = var4;
         var3.infoChange();
      }

      private static final void registerListener$lambda_4(String var0) {
         Log.i("MediaShareData", "Music: SHARE_MUSIC_TIME_INFO [" + var0 + ']');
         JSONObject var2 = new JSONObject(var0);
         Music var1 = INSTANCE;
         var1.setMPos(var2.optLong("pos"));
         var1.setMDuration(var2.optInt("duration"));
         var1.infoChange();
      }

      private static final void registerListener$lambda_9(String var0) {
         Log.i("MediaShareData", "Music: SHARE_MUSIC_NUM_INFO [" + var0 + ']');
         JSONObject var3 = new JSONObject(var0);
         Integer var2 = -1;
         int var1 = var3.optInt("playIndex", -1);
         if (!Integer.valueOf(var1).equals(var2)) {
            INSTANCE.setAPlayIndex(var1);
         }

         var1 = var3.optInt("playSize", -1);
         if (!Integer.valueOf(var1).equals(var2)) {
            INSTANCE.setMPlaySize(var1);
         }

         var1 = var3.optInt("playMode", -1);
         if (!Integer.valueOf(var1).equals(var2)) {
            INSTANCE.setMPlayMode(var1);
         }

         INSTANCE.infoChange();
      }

      private final void setAPlayIndex(int var1) {
         aPlayIndex = var1++;
         currentPlayingIndexLow = var1 & 255;
         currentPlayingIndexHigh = (byte)(var1 >> 8 & 255);
         mPlayIndex = var1;
      }

      private final void setMAlbumName(String var1) {
         mAlbumName = var1;
         songAlbum = var1;
      }

      private final void setMArtist(String var1) {
         mArtist = var1;
         songArtist = var1;
      }

      private final void setMDuration(int var1) {
         mDuration = var1;
         byte var2;
         if (var1 == 0) {
            var2 = 0;
         } else {
            var2 = (byte)((int)(mPos * (long)100 / (long)var1));
         }

         playRatio = var2;
         totalTime = (long)var1;
      }

      private final void setMName(String var1) {
         mName = var1;
         songTitle = var1;
      }

      private final void setMPath(String var1) {
         mPath = var1;
         CharSequence var3 = (CharSequence)var1;
         CharSequence var4 = (CharSequence)"emulated";
         byte var2 = 0;
         if (StringsKt.contains$default(var3, var4, false, 2, (Object)null)) {
            var2 = 9;
         }

         storagePath = var2;
      }

      private final void setMPlayMode(int var1) {
         mPlayMode = var1;
         playModel = (byte)var1;
      }

      private final void setMPlaySize(int var1) {
         mPlaySize = var1;
         totalCount = var1;
      }

      private final void setMPlayState(int var1) {
         mPlayState = var1;
         boolean var2;
         if (var1 == MeidaConstants.PLAY_STATE.STARTED.ordinal()) {
            var2 = true;
         } else {
            var2 = false;
         }

         isPlaying = var2;
      }

      private final void setMPos(long var1) {
         mPos = var1;
         long var4 = var1 / (long)1000;
         long var6 = (long)60;
         var1 = var4 / var6;
         currentHour = (byte)((int)(var1 / var6));
         currentMinute = (byte)((int)(var1 % var6));
         currentSecond = (byte)((int)(var4 % var6));
         byte var3 = (byte)((int)var1);
         currentAllMinuteStr = var3;
         currentAllMinute = var3;
         StringCompanionObject var8 = StringCompanionObject.INSTANCE;
         String var9 = String.format("%02d", Arrays.copyOf(new Object[]{currentHour}, 1));
         Intrinsics.checkNotNullExpressionValue(var9, "format(format, *args)");
         currentHourStr = var9;
         var8 = StringCompanionObject.INSTANCE;
         var9 = String.format("%02d", Arrays.copyOf(new Object[]{currentMinute}, 1));
         Intrinsics.checkNotNullExpressionValue(var9, "format(format, *args)");
         currentMinuteStr = var9;
         var8 = StringCompanionObject.INSTANCE;
         var9 = String.format("%02d", Arrays.copyOf(new Object[]{currentSecond}, 1));
         Intrinsics.checkNotNullExpressionValue(var9, "format(format, *args)");
         currentSecondStr = var9;
         currentPos = mPos;
      }

      public final int getAPlayIndex() {
         return aPlayIndex;
      }

      public final String getMAlbumName() {
         return mAlbumName;
      }

      public final String getMAlbumPath() {
         return mAlbumPath;
      }

      public final String getMArtist() {
         return mArtist;
      }

      public final int getMDuration() {
         return mDuration;
      }

      public final String getMName() {
         return mName;
      }

      public final String getMPath() {
         return mPath;
      }

      public final int getMPlayMode() {
         return mPlayMode;
      }

      public final int getMPlaySize() {
         return mPlaySize;
      }

      public final int getMPlayState() {
         return mPlayState;
      }

      public final long getMPos() {
         return mPos;
      }

      public final void registerListener() {
         ShareDataManager.getInstance().registerShareIntListener("music.PlayState", new MediaShareData$Music$$ExternalSyntheticLambda0());
         ShareDataManager.getInstance().registerShareStringListener("music.SongInfo", new MediaShareData$Music$$ExternalSyntheticLambda1());
         ShareDataManager.getInstance().registerShareStringListener("music.SongInfo.time", new MediaShareData$Music$$ExternalSyntheticLambda2());
         ShareDataManager.getInstance().registerShareStringListener("music.SongInfo.num", new MediaShareData$Music$$ExternalSyntheticLambda3());
      }
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0004J\u0006\u0010'\u001a\u00020%R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0012\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R$\u0010\u001a\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\f\"\u0004\b\u001c\u0010\u000eR\u001e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\fR\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000e¨\u0006("},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Radio;", "", "()V", "currBand", "", "currClickPresetIndex", "", "currentFreq", "isStereo", "value", "mBand", "getMBand", "()Ljava/lang/String;", "setMBand", "(Ljava/lang/String;)V", "mFreq", "getMFreq", "setMFreq", "mIsStereo", "getMIsStereo", "()I", "setMIsStereo", "(I)V", "mPresetIndex", "getMPresetIndex", "setMPresetIndex", "mPsName", "getMPsName", "setMPsName", "<set-?>", "mUnit", "getMUnit", "psName", "radioInfoSave", "getRadioInfoSave", "setRadioInfoSave", "notifyRadioInfo", "", "it", "registerListener", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Radio {
      public static final Radio INSTANCE = new Radio();
      private static String currBand = "";
      private static int currClickPresetIndex;
      private static String currentFreq = "";
      private static int isStereo;
      private static String mBand = "";
      private static String mFreq = "";
      private static int mIsStereo;
      private static int mPresetIndex;
      private static String mPsName = "";
      private static String mUnit = "";
      private static String psName = "";
      private static String radioInfoSave = "{}";

      // $FF: synthetic method
      public static void $r8$lambda$q_44nmyTqp_bKzZICDxdWL30GDM(String var0) {
         registerListener$lambda_0(var0);
      }

      private Radio() {
      }

      private static final void registerListener$lambda_0(String var0) {
         Log.i("MediaShareData", "Radio: SHARE_RADIO_INFO [" + var0 + ']');
         if (var0 != null) {
            INSTANCE.notifyRadioInfo(var0);
         }

      }

      private final void setMBand(String var1) {
         mBand = var1;
         currBand = var1;
      }

      private final void setMFreq(String var1) {
         mFreq = var1;
         currentFreq = var1;
      }

      private final void setMIsStereo(int var1) {
         mIsStereo = var1;
         isStereo = var1;
      }

      private final void setMPresetIndex(int var1) {
         mPresetIndex = var1;
         currClickPresetIndex = var1;
      }

      private final void setMPsName(String var1) {
         mPsName = var1;
         psName = var1;
      }

      public final String getMBand() {
         return mBand;
      }

      public final String getMFreq() {
         return mFreq;
      }

      public final int getMIsStereo() {
         return mIsStereo;
      }

      public final int getMPresetIndex() {
         return mPresetIndex;
      }

      public final String getMPsName() {
         return mPsName;
      }

      public final String getMUnit() {
         return mUnit;
      }

      public final String getRadioInfoSave() {
         return radioInfoSave;
      }

      public final void notifyRadioInfo(String var1) {
         JSONObject var4 = new JSONObject(var1);
         Radio var2 = INSTANCE;
         String var3 = var4.optString("band");
         Intrinsics.checkNotNullExpressionValue(var3, "optString(\"band\")");
         var2.setMBand(var3);
         var3 = var4.optString("freq");
         Intrinsics.checkNotNullExpressionValue(var3, "optString(\"freq\")");
         var2.setMFreq(var3);
         var3 = var4.optString("unit");
         Intrinsics.checkNotNullExpressionValue(var3, "optString(\"unit\")");
         mUnit = var3;
         var2.setMPresetIndex(var4.optInt("presetIndex"));
         var3 = var4.optString("psName");
         Intrinsics.checkNotNullExpressionValue(var3, "optString(\"psName\")");
         var2.setMPsName(var3);
         var2.setMIsStereo(var4.optInt("isStereo"));
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.FM) {
            MsgMgrInterface var5 = MediaShareData.msgMgrInterface;
            if (var5 != null) {
               var5.radioInfoChange(currClickPresetIndex, currBand, currentFreq, psName, isStereo);
            }
         }

      }

      public final void registerListener() {
         String var1 = ShareDataManager.getInstance().registerShareStringListener("radio.RadioInfo", new MediaShareData$Radio$$ExternalSyntheticLambda0());
         Intrinsics.checkNotNullExpressionValue(var1, "getInstance().registerSh…dioInfo(it)\n            }");
         radioInfoSave = var1;
         if (var1 != null) {
            this.notifyRadioInfo(var1);
         }

      }

      public final void setRadioInfoSave(String var1) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         radioInfoSave = var1;
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Screen;", "", "()V", "<set-?>", "", "screenBacklight", "getScreenBacklight", "()I", "registerListener", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Screen {
      public static final Screen INSTANCE = new Screen();
      private static int screenBacklight;

      // $FF: synthetic method
      public static void $r8$lambda$SCeToMu65_CvcEXPNXOxhVqnjyc(int var0) {
         registerListener$lambda_0(var0);
      }

      private Screen() {
      }

      private static final void registerListener$lambda_0(int var0) {
         Log.i("MediaShareData", "Screen: SHARE_SCREEN_BACKLIGHT [" + var0 + ']');
         screenBacklight = var0;
      }

      public final int getScreenBacklight() {
         return screenBacklight;
      }

      public final void registerListener() {
         screenBacklight = ShareDataManager.getInstance().registerShareIntListener("screen.backlight", new MediaShareData$Screen$$ExternalSyntheticLambda0());
      }
   }

   @Metadata(
      d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0007R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R6\u0010\u001a\u001a*\u0012\u0004\u0012\u00020\u0014\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c0\u001b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Source;", "", "()V", "<set-?>", "", "clsName", "getClsName", "()Ljava/lang/String;", "", "currentCamera", "getCurrentCamera", "()I", "id", "getId", "originalDeviceMap", "", "Landroid/content/ComponentName;", "pkgName", "getPkgName", "preSource", "Lcom/hzbhd/commontools/SourceConstantsDef$SOURCE_ID;", "source", "getSource", "()Lcom/hzbhd/commontools/SourceConstantsDef$SOURCE_ID;", "setSource", "(Lcom/hzbhd/commontools/SourceConstantsDef$SOURCE_ID;)V", "sourceMap", "Lkotlin/Pair;", "Lkotlin/Function0;", "", "parseSourceInfo", "it", "registerListener", "context", "Landroid/content/Context;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Source {
      public static final Source INSTANCE = new Source();
      private static String clsName = "";
      private static int currentCamera;
      private static int id;
      private static final Map originalDeviceMap;
      private static String pkgName = "";
      private static SourceConstantsDef.SOURCE_ID preSource;
      private static SourceConstantsDef.SOURCE_ID source;
      private static final Map sourceMap;

      // $FF: synthetic method
      public static void $r8$lambda$72On505OS0AFqHxt8a_x8_h9Jmk(String var0) {
         registerListener$lambda_0(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$di9qYmWcuvfA7haThhj8HZdD9d0(int var0) {
         registerListener$lambda_1(var0);
      }

      static {
         originalDeviceMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(Constant.OriginalDeviceActivity.getClassName(), Constant.OriginalDeviceActivity), TuplesKt.to(Constant.SyncActivity.getClassName(), Constant.SyncActivity), TuplesKt.to(Constant.OnStarActivity.getClassName(), Constant.OnStarActivity)});
         source = SourceConstantsDef.SOURCE_ID.NULL;
         preSource = SourceConstantsDef.SOURCE_ID.NULL;
         sourceMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(SourceConstantsDef.SOURCE_ID.FM, new Pair((Object)null, null.INSTANCE)), TuplesKt.to(SourceConstantsDef.SOURCE_ID.VIDEO, new Pair((Object)null, null.INSTANCE)), TuplesKt.to(SourceConstantsDef.SOURCE_ID.MUSIC, new Pair((Object)null, null.INSTANCE)), TuplesKt.to(SourceConstantsDef.SOURCE_ID.BTAUDIO, new Pair(null.INSTANCE, null.INSTANCE)), TuplesKt.to(SourceConstantsDef.SOURCE_ID.AUX1, new Pair(null.INSTANCE, null.INSTANCE)), TuplesKt.to(SourceConstantsDef.SOURCE_ID.ATV, new Pair(null.INSTANCE, null.INSTANCE)), TuplesKt.to(SourceConstantsDef.SOURCE_ID.DTV, new Pair(null.INSTANCE, (Object)null))});
      }

      private Source() {
      }

      private final void parseSourceInfo(String var1) {
         Log.i("MediaShareData", "Source: SHARE_CURRENT_SOURCE_INFO [" + var1 + ']');
         JSONObject var3 = new JSONObject(var1);
         id = var3.optInt("id");
         String var2 = var3.optString("pkgName");
         Intrinsics.checkNotNullExpressionValue(var2, "optString(\"pkgName\")");
         pkgName = var2;
         var1 = var3.optString("clsName");
         Intrinsics.checkNotNullExpressionValue(var1, "optString(\"clsName\")");
         clsName = var1;
         SourceConstantsDef.SOURCE_ID var4 = SourceConstantsDef.SOURCE_ID.getType(id);
         Intrinsics.checkNotNullExpressionValue(var4, "getType(id)");
         source = var4;
         Log.i("MediaShareData", "Source: SHARE_CURRENT_SOURCE_INFO source[" + source + "]  preSource[" + preSource + ']');
         if (source == SourceConstantsDef.SOURCE_ID.FM && !MediaShareData.Radio.INSTANCE.getRadioInfoSave().equals("{}")) {
            MediaShareData.Radio.INSTANCE.notifyRadioInfo(MediaShareData.Radio.INSTANCE.getRadioInfoSave());
         }

         MsgMgrInterface var5 = MediaShareData.msgMgrInterface;
         if (var5 != null) {
            var5.sourceSwitchChange(source.name());
         }

         SourceConstantsDef.SOURCE_ID var6 = source;
         var4 = preSource;
         Pair var7;
         Function0 var8;
         if (var6 != var4) {
            var7 = (Pair)sourceMap.get(var4);
            if (var7 != null) {
               var8 = (Function0)var7.getSecond();
               if (var8 != null) {
                  var8.invoke();
               }
            }
         }

         var7 = (Pair)sourceMap.get(source);
         if (var7 != null) {
            var8 = (Function0)var7.getFirst();
            if (var8 != null) {
               var8.invoke();
            }
         }

         preSource = source;
      }

      private static final void registerListener$lambda_0(String var0) {
         INSTANCE.parseSourceInfo(var0);
      }

      private static final void registerListener$lambda_1(int var0) {
         Log.i("MediaShareData", "Source: SHARE_CURRENT_CAMERA [" + var0 + ']');
         currentCamera = var0;
         MsgMgrInterface var1;
         if (var0 == SourceConstantsDef.SOURCE_ID.FRONTCAMERA.getValue()) {
            var1 = MediaShareData.msgMgrInterface;
            if (var1 != null) {
               var1.cameraInfoChange();
            }
         } else {
            var1 = MediaShareData.msgMgrInterface;
            if (var1 != null) {
               var1.cameraDestroy();
            }
         }

      }

      public final String getClsName() {
         return clsName;
      }

      public final int getCurrentCamera() {
         return currentCamera;
      }

      public final int getId() {
         return id;
      }

      public final String getPkgName() {
         return pkgName;
      }

      public final SourceConstantsDef.SOURCE_ID getSource() {
         return source;
      }

      public final void registerListener(Context var1) {
         Intrinsics.checkNotNullParameter(var1, "context");
         String var2 = ShareDataManager.getInstance().registerShareStringListener("sourcemanager.CurrentSource", new MediaShareData$Source$$ExternalSyntheticLambda0());
         if (var2 != null) {
            this.parseSourceInfo(var2);
         }

         ShareDataManager.getInstance().registerShareIntListener("sourcemanager.CurrentCamera", new MediaShareData$Source$$ExternalSyntheticLambda1());
      }

      public final void setSource(SourceConstantsDef.SOURCE_ID var1) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         source = var1;
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\f"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$System;", "", "()V", "<set-?>", "", "backLight", "getBackLight", "()I", "mSystemStatusPower", "getMSystemStatusPower", "registerListener", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class System {
      public static final System INSTANCE = new System();
      private static int backLight;
      private static int mSystemStatusPower;

      // $FF: synthetic method
      public static void $r8$lambda$QATmXQlPb8iuZ5hL8dT7CTvD8Uk(int var0) {
         registerListener$lambda_1(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$lG2UqnzRhKZcbOhTkLgH_VMDJZ8(int var0) {
         registerListener$lambda_0(var0);
      }

      private System() {
      }

      private static final void registerListener$lambda_0(int var0) {
         mSystemStatusPower = var0;
         MsgMgrInterface var2 = MediaShareData.msgMgrInterface;
         if (var2 != null) {
            boolean var1;
            if (var0 == SystemStatusDef.POWER_STATUS.ACC_OFF.ordinal()) {
               var1 = true;
            } else {
               var1 = false;
            }

            var2.sourceSwitchNoMediaInfoChange(var1);
         }

      }

      private static final void registerListener$lambda_1(int var0) {
         backLight = var0;
      }

      public final int getBackLight() {
         return backLight;
      }

      public final int getMSystemStatusPower() {
         return mSystemStatusPower;
      }

      public final void registerListener() {
         ShareDataManager.getInstance().registerShareIntListener("sys.Power", new MediaShareData$System$$ExternalSyntheticLambda0());
         ShareDataManager.getInstance().registerShareIntListener("sys.backlight.status", new MediaShareData$System$$ExternalSyntheticLambda1());
      }
   }

   @Metadata(
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010>\u001a\u00020?H\u0002J\u0006\u0010@\u001a\u00020?R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R$\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R$\u0010$\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0017\"\u0004\b&\u0010'R$\u0010(\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!R$\u0010+\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001f\"\u0004\b-\u0010!R$\u0010.\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001f\"\u0004\b0\u0010!R$\u00101\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001f\"\u0004\b3\u0010!R$\u00105\u001a\u0002042\u0006\u0010\u001c\u001a\u000204@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010:\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Video;", "", "()V", "currentAllMinute", "", "currentAllMinuteStr", "", "currentHour", "currentHourStr", "currentMinute", "currentMinuteStr", "currentPlayingIndexHigh", "currentPlayingIndexLow", "", "currentPos", "currentSecond", "currentSecondStr", "duration", "isPlaying", "", "<set-?>", "mAlbumName", "getMAlbumName", "()Ljava/lang/String;", "mAlbumPath", "getMAlbumPath", "mArtist", "getMArtist", "value", "mDuration", "getMDuration", "()I", "setMDuration", "(I)V", "mName", "getMName", "mPath", "getMPath", "setMPath", "(Ljava/lang/String;)V", "mPlayIndex", "getMPlayIndex", "setMPlayIndex", "mPlayMode", "getMPlayMode", "setMPlayMode", "mPlaySize", "getMPlaySize", "setMPlaySize", "mPlayState", "getMPlayState", "setMPlayState", "", "mPos", "getMPos", "()J", "setMPos", "(J)V", "playMode", "playRatio", "storagePath", "totalCount", "infoChange", "", "registerListener", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Video {
      public static final Video INSTANCE = new Video();
      private static byte currentAllMinute;
      private static String currentAllMinuteStr = "";
      private static byte currentHour;
      private static String currentHourStr = "";
      private static byte currentMinute;
      private static String currentMinuteStr = "";
      private static byte currentPlayingIndexHigh;
      private static int currentPlayingIndexLow;
      private static int currentPos;
      private static byte currentSecond;
      private static String currentSecondStr = "";
      private static int duration;
      private static boolean isPlaying;
      private static String mAlbumName = "";
      private static String mAlbumPath = "";
      private static String mArtist = "";
      private static int mDuration;
      private static String mName = "";
      private static String mPath = "";
      private static int mPlayIndex;
      private static int mPlayMode;
      private static int mPlaySize;
      private static int mPlayState;
      private static long mPos;
      private static byte playMode;
      private static byte playRatio;
      private static byte storagePath;
      private static int totalCount;

      // $FF: synthetic method
      public static void $r8$lambda$HYJOcmEp9PLvwXWNXM8xAryL6HU(int var0) {
         registerListener$lambda_0(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$Rt3fqutVkWR2HJOWfMO56wCeRpM(String var0) {
         registerListener$lambda_4(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$Ubu1gTO7wace7lorJV43LvHEwZ0(String var0) {
         registerListener$lambda_2(var0);
      }

      // $FF: synthetic method
      public static void $r8$lambda$UeEYDtQBmUU9Pc0yJE0uWEF5Yyk(String var0) {
         registerListener$lambda_9(var0);
      }

      private Video() {
      }

      private final void infoChange() {
         if (SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()) == SourceConstantsDef.SOURCE_ID.VIDEO) {
            MsgMgrInterface var1 = MediaShareData.msgMgrInterface;
            if (var1 != null) {
               var1.videoInfoChange(storagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playMode, isPlaying, duration);
            }
         }

      }

      private static final void registerListener$lambda_0(int var0) {
         Log.i("MediaShareData", "Video: SHARE_VIDEO_PLAY_STATE [" + var0 + ']');
         Video var1 = INSTANCE;
         var1.setMPlayState(var0);
         var1.infoChange();
      }

      private static final void registerListener$lambda_2(String var0) {
         Log.i("MediaShareData", "Video: SHARE_VIDEO_MOVIE_INFO [" + var0 + ']');
         JSONObject var1 = new JSONObject(var0);
         Video var2 = INSTANCE;
         String var3 = var1.optString("path");
         Intrinsics.checkNotNullExpressionValue(var3, "optString(\"path\")");
         var2.setMPath(var3);
         var2.infoChange();
      }

      private static final void registerListener$lambda_4(String var0) {
         Log.i("MediaShareData", "Video: SHARE_VIDEO_TIME_INFO [" + var0 + ']');
         JSONObject var1 = new JSONObject(var0);
         Video var2 = INSTANCE;
         var2.setMPos(var1.optLong("pos"));
         var2.setMDuration(var1.optInt("duration"));
         var2.infoChange();
      }

      private static final void registerListener$lambda_9(String var0) {
         Log.i("MediaShareData", "Video: SHARE_VIDEO_NUM_INFO [" + var0 + ']');
         JSONObject var3 = new JSONObject(var0);
         Integer var2 = -1;
         int var1 = var3.optInt("playIndex", -1);
         if (!Integer.valueOf(var1).equals(var2)) {
            INSTANCE.setMPlayIndex(var1);
         }

         var1 = var3.optInt("playSize", -1);
         if (!Integer.valueOf(var1).equals(var2)) {
            INSTANCE.setMPlaySize(var1);
         }

         var1 = var3.optInt("playMode", -1);
         if (!Integer.valueOf(var1).equals(var2)) {
            INSTANCE.setMPlayMode(var1);
         }

         INSTANCE.infoChange();
      }

      private final void setMDuration(int var1) {
         mDuration = var1;
         playRatio = (byte)((int)(mPos * (long)100 / (long)var1));
         duration = var1;
      }

      private final void setMPath(String var1) {
         mPath = var1;
         CharSequence var3 = (CharSequence)var1;
         CharSequence var4 = (CharSequence)"emulated";
         byte var2 = 0;
         if (StringsKt.contains$default(var3, var4, false, 2, (Object)null)) {
            var2 = 9;
         }

         storagePath = var2;
      }

      private final void setMPlayIndex(int var1) {
         mPlayIndex = var1++;
         currentPlayingIndexLow = var1 & 255;
         currentPlayingIndexHigh = (byte)(var1 >> 8 & 255);
      }

      private final void setMPlayMode(int var1) {
         mPlayMode = var1;
         playMode = (byte)var1;
      }

      private final void setMPlaySize(int var1) {
         mPlaySize = var1;
         totalCount = var1;
      }

      private final void setMPlayState(int var1) {
         mPlayState = var1;
         boolean var2;
         if (var1 == MeidaConstants.PLAY_STATE.STARTED.ordinal()) {
            var2 = true;
         } else {
            var2 = false;
         }

         isPlaying = var2;
      }

      private final void setMPos(long var1) {
         mPos = var1;
         long var3 = var1 / (long)1000;
         long var7 = (long)60;
         long var5 = var3 / var7;
         currentHour = (byte)((int)(var5 / var7));
         currentMinute = (byte)((int)(var5 % var7));
         currentSecond = (byte)((int)(var3 % var7));
         currentAllMinute = (byte)((int)var5);
         StringCompanionObject var9 = StringCompanionObject.INSTANCE;
         String var10 = String.format("%02d", Arrays.copyOf(new Object[]{currentAllMinute}, 1));
         Intrinsics.checkNotNullExpressionValue(var10, "format(format, *args)");
         currentAllMinuteStr = var10;
         var9 = StringCompanionObject.INSTANCE;
         var10 = String.format("%02d", Arrays.copyOf(new Object[]{currentHour}, 1));
         Intrinsics.checkNotNullExpressionValue(var10, "format(format, *args)");
         currentHourStr = var10;
         var9 = StringCompanionObject.INSTANCE;
         var10 = String.format("%02d", Arrays.copyOf(new Object[]{currentMinute}, 1));
         Intrinsics.checkNotNullExpressionValue(var10, "format(format, *args)");
         currentMinuteStr = var10;
         var9 = StringCompanionObject.INSTANCE;
         var10 = String.format("%02d", Arrays.copyOf(new Object[]{currentSecond}, 1));
         Intrinsics.checkNotNullExpressionValue(var10, "format(format, *args)");
         currentSecondStr = var10;
         currentPos = (int)var1;
      }

      public final String getMAlbumName() {
         return mAlbumName;
      }

      public final String getMAlbumPath() {
         return mAlbumPath;
      }

      public final String getMArtist() {
         return mArtist;
      }

      public final int getMDuration() {
         return mDuration;
      }

      public final String getMName() {
         return mName;
      }

      public final String getMPath() {
         return mPath;
      }

      public final int getMPlayIndex() {
         return mPlayIndex;
      }

      public final int getMPlayMode() {
         return mPlayMode;
      }

      public final int getMPlaySize() {
         return mPlaySize;
      }

      public final int getMPlayState() {
         return mPlayState;
      }

      public final long getMPos() {
         return mPos;
      }

      public final void registerListener() {
         ShareDataManager.getInstance().registerShareIntListener("video.PlayState", new MediaShareData$Video$$ExternalSyntheticLambda0());
         ShareDataManager.getInstance().registerShareStringListener("video.MovieInfo", new MediaShareData$Video$$ExternalSyntheticLambda1());
         ShareDataManager.getInstance().registerShareStringListener("video.SongInfo.time", new MediaShareData$Video$$ExternalSyntheticLambda2());
         ShareDataManager.getInstance().registerShareStringListener("video.SongInfo.num", new MediaShareData$Video$$ExternalSyntheticLambda3());
      }
   }

   @Metadata(
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0002J\u0006\u0010\u000e\u001a\u00020\fR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"},
      d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Volume;", "", "()V", "<set-?>", "", "isMute", "()Z", "", "volume", "getVolume", "()I", "infoChange", "", "info", "registerListener", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Volume {
      public static final Volume INSTANCE = new Volume();
      private static boolean isMute;
      private static int volume;

      // $FF: synthetic method
      public static void $r8$lambda$xLMKz_1D0pPjA2wKnLRozGdP5Lc(int var0) {
         registerListener$lambda_0(var0);
      }

      private Volume() {
      }

      private final void infoChange(int var1) {
         volume = AudioConstants.getVolume(var1);
         isMute = AudioConstants.isMute(var1);
         if (volume != 127) {
            MsgMgrInterface var2 = MediaShareData.msgMgrInterface;
            if (var2 != null) {
               var2.currentVolumeInfoChange(volume, isMute);
            }

         }
      }

      private static final void registerListener$lambda_0(int var0) {
         Log.i("MediaShareData", "Volume: on SHARE_AUDIO_VOLUME [" + var0 + ']');
         INSTANCE.infoChange(var0);
      }

      public final int getVolume() {
         return volume;
      }

      public final boolean isMute() {
         return isMute;
      }

      public final void registerListener() {
         int var1 = ShareDataManager.getInstance().registerShareIntListener("audio.Volume", new MediaShareData$Volume$$ExternalSyntheticLambda0());
         Log.i("MediaShareData", "Volume: register SHARE_AUDIO_VOLUME [" + var1 + ']');
         this.infoChange(var1);
         if (volume == 127) {
            TimerUtil var2 = new TimerUtil();
            var2.startTimer((TimerTask)(new TimerTask(var2) {
               final TimerUtil $this_run;

               {
                  this.$this_run = var1;
               }

               public void run() {
                  if (MediaShareData.Volume.INSTANCE.getVolume() == 127) {
                     Volume var2 = MediaShareData.Volume.INSTANCE;
                     int var1 = ShareDataManager.getInstance().getInt("audio.Volume");
                     Log.i("MediaShareData", "Volume: get SHARE_AUDIO_VOLUME [" + var1 + ']');
                     var2.infoChange(var1);
                  } else {
                     this.$this_run.stopTimer();
                  }

               }
            }), 0L, 1000L);
         }

      }
   }
}
