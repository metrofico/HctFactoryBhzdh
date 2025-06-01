package com.hzbhd.canbus.msg_mgr;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.util.amap.AMapEntity;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b4\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016J&\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001a\u001a\u00020\rH\u0016J\b\u0010\u001b\u001a\u00020\rH\u0016J\"\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010!\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016J\"\u0010'\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016J\"\u0010(\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016JT\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020%2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\"\u0010/\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016J*\u00100\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u00101\u001a\u00020\u001eH\u0016J\b\u00102\u001a\u00020\rH\u0016J\b\u00103\u001a\u00020\rH\u0016J\u001c\u00104\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u00105\u001a\u0004\u0018\u00010#H\u0016J\u0018\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u00020%H\u0016J\u001a\u00109\u001a\u00020%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010:\u001a\u00020\u001eH\u0016J\u001a\u0010;\u001a\u00020%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010:\u001a\u00020\u001eH\u0016Jp\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\u001e2\u0006\u0010C\u001a\u00020\u001e2\u0006\u0010D\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020\u001e2\u0006\u0010F\u001a\u00020%2\u0006\u0010G\u001a\u00020%2\u0006\u0010H\u001a\u00020%2\u0006\u0010I\u001a\u00020\u001eH\u0016J\b\u0010J\u001a\u00020\rH\u0016Jh\u0010K\u001a\u00020\r2\u0006\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020\u001e2\u0006\u0010O\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020\u001e2\u0006\u0010Q\u001a\u00020\u001e2\u0006\u0010R\u001a\u00020\u001e2\u0006\u0010S\u001a\u00020\u001e2\u0006\u0010T\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020\u001e2\u0006\u0010V\u001a\u00020\u001e2\u0006\u0010W\u001a\u00020\u001eH\u0016Jv\u0010X\u001a\u00020\r2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u001e2\u0006\u0010\\\u001a\u00020\u001e2\u0006\u0010]\u001a\u00020\u001e2\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010_\u001a\u00020\u001e2\u0006\u0010`\u001a\u00020\u001e2\u0006\u0010a\u001a\u00020%2\u0006\u0010b\u001a\u00020%2\u0006\u0010c\u001a\u00020\u001e2\b\u0010d\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010e\u001a\u00020\rH\u0016J\u0012\u0010f\u001a\u00020\r2\b\u0010g\u001a\u0004\u0018\u00010hH\u0016J\b\u0010i\u001a\u00020\u0001H\u0016J\u0012\u0010j\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u001c\u0010k\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010l\u001a\u0004\u0018\u00010#H\u0016J\u001c\u0010m\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010n\u001a\u0004\u0018\u00010#H\u0016J\u001c\u0010o\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010p\u001a\u0004\u0018\u00010#H\u0016J\b\u0010q\u001a\u00020\rH\u0016JÍ\u0001\u0010r\u001a\u00020\r2\u0006\u0010s\u001a\u00020Z2\u0006\u0010t\u001a\u00020Z2\u0006\u0010u\u001a\u00020\u001e2\u0006\u0010v\u001a\u00020\u001e2\u0006\u0010w\u001a\u00020Z2\u0006\u0010x\u001a\u00020Z2\u0006\u0010y\u001a\u00020Z2\u0006\u0010z\u001a\u00020Z2\u0006\u0010{\u001a\u00020Z2\u0006\u0010|\u001a\u00020Z2\b\u0010}\u001a\u0004\u0018\u00010\u00172\b\u0010~\u001a\u0004\u0018\u00010\u00172\b\u0010\u007f\u001a\u0004\u0018\u00010\u00172\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u0010Y\u001a\u00020Z2\u0007\u0010\u0082\u0001\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020%2\b\u0010\u0083\u0001\u001a\u00030\u0081\u00012\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00172\u0007\u0010\u0087\u0001\u001a\u00020%H\u0016J\u0014\u0010\u0088\u0001\u001a\u00020\r2\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0017H\u0016J\t\u0010\u008a\u0001\u001a\u00020\rH\u0016J\u0015\u0010\u008b\u0001\u001a\u00020\r2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001H\u0016J\t\u0010\u008e\u0001\u001a\u00020\rH\u0016J\t\u0010\u008f\u0001\u001a\u00020\rH\u0016J\u0013\u0010\u0090\u0001\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J.\u0010\u0091\u0001\u001a\u00020\r2\u0007\u0010\u0092\u0001\u001a\u00020\u001e2\u0007\u0010\u0093\u0001\u001a\u00020\u001e2\u0007\u0010\u0094\u0001\u001a\u00020\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010\u0095\u0001\u001a\u00020\rH\u0016J\t\u0010\u0096\u0001\u001a\u00020\rH\u0016J\t\u0010\u0097\u0001\u001a\u00020\rH\u0016J<\u0010\u0098\u0001\u001a\u00020\r2\u0007\u0010\u0099\u0001\u001a\u00020\u001e2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u00172\u0007\u0010\u009d\u0001\u001a\u00020\u001eH\u0016J\u001e\u0010\u009e\u0001\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010#H\u0016J\u0015\u0010 \u0001\u001a\u00020\r2\n\u0010¡\u0001\u001a\u0005\u0018\u00010¢\u0001H\u0016J\u0014\u0010£\u0001\u001a\u00020\r2\t\u0010¤\u0001\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010¥\u0001\u001a\u00020\r2\u0007\u0010¦\u0001\u001a\u00020%H\u0016J\t\u0010§\u0001\u001a\u00020\rH\u0016J\u009c\u0001\u0010¨\u0001\u001a\u00020\r2\u0006\u0010s\u001a\u00020Z2\u0006\u0010t\u001a\u00020Z2\u0006\u0010u\u001a\u00020\u001e2\u0006\u0010v\u001a\u00020\u001e2\u0006\u0010w\u001a\u00020Z2\u0006\u0010x\u001a\u00020Z2\u0006\u0010y\u001a\u00020Z2\b\u0010z\u001a\u0004\u0018\u00010\u00172\u0006\u0010{\u001a\u00020Z2\u0006\u0010|\u001a\u00020Z2\b\u0010}\u001a\u0004\u0018\u00010\u00172\b\u0010~\u001a\u0004\u0018\u00010\u00172\b\u0010\u007f\u001a\u0004\u0018\u00010\u00172\u0007\u0010\u0080\u0001\u001a\u00020\u001e2\u0007\u0010©\u0001\u001a\u00020Z2\u0006\u0010b\u001a\u00020%2\u0007\u0010ª\u0001\u001a\u00020\u001eH\u0016J\u0014\u0010«\u0001\u001a\u00020\r2\t\u0010¬\u0001\u001a\u0004\u0018\u00010\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u00ad\u0001"},
   d2 = {"Lcom/hzbhd/canbus/msg_mgr/MsgMgrInterfaceUtil;", "Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mMsgMgrInterface", "getMMsgMgrInterface", "()Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "mMsgMgrInterface$delegate", "Lkotlin/Lazy;", "aMapCallBack", "", "bundle", "Landroid/os/Bundle;", "afterServiceNormalSetting", "atvDestdroy", "atvInfoChange", "auxInDestdroy", "auxInInfoChange", "btMusicId3InfoChange", "title", "", "artist", "album", "btMusicInfoChange", "btMusiceDestdroy", "btPhoneCallLogInfoChange", "index", "", "type", "number", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "btPhoneTalkingInfoChange", "btPhoneTalkingWithTimeInfoChange", "time", "cameraDestroy", "cameraInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "customLongClick", "key", "customShortClick", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "destroyCommand", "deviceStatusInfoChange", "btOn", "discRadom", "discRepeat", "discExsit", "sdCardIn", "usbIn", "radioSt", "mute", "singleCycle", "fullCurve", "folderLoop", "randomFolder", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "dtvInfoChange", "getBackCameraUiService", "backCameraUiService", "Lcom/hzbhd/canbus/park/BackCameraUiService;", "getInstance", "initCommand", "linInfoChange", "linInfo", "mcuErrorState", "errorInfo", "medianCalibration", "calibrationInfo", "musicDestroy", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "musicLrcInfoChange", "lrc", "notifyBtStatusChange", "onAMapCallBack", "aMapEntity", "Lcom/hzbhd/canbus/util/amap/AMapEntity;", "onAccOff", "onAccOn", "onHandshake", "onKeyEvent", "hotKey", "keyStateOrArg1", "beepOrArg2", "onPowerOff", "onSleep", "radioDestroy", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "serialDataChange", "serialData", "setMgrRefreshUiInterface", "mgrRefreshUiInterface", "Lcom/hzbhd/canbus/activity/AbstractBaseActivity;", "sourceSwitchChange", "source", "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoDestroy", "videoInfoChange", "playMode", "duration", "voiceControlInfo", "action", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrInterfaceUtil implements MsgMgrInterface {
   private final Context context;
   private final Lazy mMsgMgrInterface$delegate;

   public MsgMgrInterfaceUtil(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      this.context = var1;
      this.mMsgMgrInterface$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   public void aMapCallBack(Bundle param1) {
      // $FF: Couldn't be decompiled
   }

   public void afterServiceNormalSetting(Context param1) {
      // $FF: Couldn't be decompiled
   }

   public void atvDestdroy() {
      // $FF: Couldn't be decompiled
   }

   public void atvInfoChange() {
      // $FF: Couldn't be decompiled
   }

   public void auxInDestdroy() {
      // $FF: Couldn't be decompiled
   }

   public void auxInInfoChange() {
      // $FF: Couldn't be decompiled
   }

   public void btMusicId3InfoChange(String param1, String param2, String param3) {
      // $FF: Couldn't be decompiled
   }

   public void btMusicInfoChange() {
      // $FF: Couldn't be decompiled
   }

   public void btMusiceDestdroy() {
      // $FF: Couldn't be decompiled
   }

   public void btPhoneCallLogInfoChange(int param1, int param2, String param3) {
      // $FF: Couldn't be decompiled
   }

   public void btPhoneHangUpInfoChange(byte[] param1, boolean param2, boolean param3) {
      // $FF: Couldn't be decompiled
   }

   public void btPhoneIncomingInfoChange(byte[] param1, boolean param2, boolean param3) {
      // $FF: Couldn't be decompiled
   }

   public void btPhoneOutGoingInfoChange(byte[] param1, boolean param2, boolean param3) {
      // $FF: Couldn't be decompiled
   }

   public void btPhoneStatusInfoChange(int param1, byte[] param2, boolean param3, boolean param4, boolean param5, boolean param6, int param7, int param8, Bundle param9) {
      // $FF: Couldn't be decompiled
   }

   public void btPhoneTalkingInfoChange(byte[] param1, boolean param2, boolean param3) {
      // $FF: Couldn't be decompiled
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] param1, boolean param2, boolean param3, int param4) {
      // $FF: Couldn't be decompiled
   }

   public void cameraDestroy() {
      // $FF: Couldn't be decompiled
   }

   public void cameraInfoChange() {
      // $FF: Couldn't be decompiled
   }

   public void canbusInfoChange(Context param1, byte[] param2) {
      // $FF: Couldn't be decompiled
   }

   public void currentVolumeInfoChange(int param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   public boolean customLongClick(Context var1, int var2) {
      boolean var4 = false;

      boolean var3;
      boolean var5;
      label27: {
         Exception var10000;
         label32: {
            boolean var10001;
            MsgMgrInterface var6;
            try {
               var6 = this.getMMsgMgrInterface();
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
               break label32;
            }

            var3 = var4;
            if (var6 == null) {
               return var3;
            }

            try {
               var5 = var6.customLongClick(var1, var2);
               break label27;
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
            }
         }

         Exception var9 = var10000;
         var9.printStackTrace();
         return false;
      }

      var3 = var4;
      if (var5) {
         var3 = true;
      }

      return var3;
   }

   public boolean customShortClick(Context var1, int var2) {
      boolean var4 = false;

      boolean var3;
      boolean var5;
      label27: {
         Exception var10000;
         label32: {
            boolean var10001;
            MsgMgrInterface var6;
            try {
               var6 = this.getMMsgMgrInterface();
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
               break label32;
            }

            var3 = var4;
            if (var6 == null) {
               return var3;
            }

            try {
               var5 = var6.customShortClick(var1, var2);
               break label27;
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
            }
         }

         Exception var9 = var10000;
         var9.printStackTrace();
         return false;
      }

      var3 = var4;
      if (var5) {
         var3 = true;
      }

      return var3;
   }

   public void dateTimeRepCanbus(int param1, int param2, int param3, int param4, int param5, int param6, int param7, int param8, int param9, boolean param10, boolean param11, boolean param12, int param13) {
      // $FF: Couldn't be decompiled
   }

   public void destroyCommand() {
      // $FF: Couldn't be decompiled
   }

   public void deviceStatusInfoChange(int param1, int param2, int param3, int param4, int param5, int param6, int param7, int param8, int param9, int param10, int param11, int param12) {
      // $FF: Couldn't be decompiled
   }

   public void discInfoChange(byte param1, int param2, int param3, int param4, int param5, int param6, int param7, boolean param8, boolean param9, int param10, String param11, String param12, String param13) {
      // $FF: Couldn't be decompiled
   }

   public void dtvInfoChange() {
      // $FF: Couldn't be decompiled
   }

   public void getBackCameraUiService(BackCameraUiService param1) {
      // $FF: Couldn't be decompiled
   }

   public final Context getContext() {
      return this.context;
   }

   public MsgMgrInterface getInstance() {
      return (MsgMgrInterface)this;
   }

   public final MsgMgrInterface getMMsgMgrInterface() {
      return (MsgMgrInterface)this.mMsgMgrInterface$delegate.getValue();
   }

   public void initCommand(Context param1) {
      // $FF: Couldn't be decompiled
   }

   public void linInfoChange(Context param1, byte[] param2) {
      // $FF: Couldn't be decompiled
   }

   public void mcuErrorState(Context param1, byte[] param2) {
      // $FF: Couldn't be decompiled
   }

   public void medianCalibration(Context param1, byte[] param2) {
      // $FF: Couldn't be decompiled
   }

   public void musicDestroy() {
      // $FF: Couldn't be decompiled
   }

   public void musicInfoChange(byte param1, byte param2, int param3, int param4, byte param5, byte param6, byte param7, byte param8, byte param9, byte param10, String param11, String param12, String param13, long param14, byte param16, int param17, boolean param18, long param19, String param21, String param22, String param23, boolean param24) {
      // $FF: Couldn't be decompiled
   }

   public void musicLrcInfoChange(String param1) {
      // $FF: Couldn't be decompiled
   }

   public void notifyBtStatusChange() {
      // $FF: Couldn't be decompiled
   }

   public void onAMapCallBack(AMapEntity param1) {
      // $FF: Couldn't be decompiled
   }

   public void onAccOff() {
      // $FF: Couldn't be decompiled
   }

   public void onAccOn() {
      // $FF: Couldn't be decompiled
   }

   public void onHandshake(Context param1) {
      // $FF: Couldn't be decompiled
   }

   public void onKeyEvent(int param1, int param2, int param3, Bundle param4) {
      // $FF: Couldn't be decompiled
   }

   public void onPowerOff() {
      // $FF: Couldn't be decompiled
   }

   public void onSleep() {
      // $FF: Couldn't be decompiled
   }

   public void radioDestroy() {
      // $FF: Couldn't be decompiled
   }

   public void radioInfoChange(int param1, String param2, String param3, String param4, int param5) {
      // $FF: Couldn't be decompiled
   }

   public void serialDataChange(Context param1, byte[] param2) {
      // $FF: Couldn't be decompiled
   }

   public void setMgrRefreshUiInterface(AbstractBaseActivity param1) {
      // $FF: Couldn't be decompiled
   }

   public void sourceSwitchChange(String param1) {
      // $FF: Couldn't be decompiled
   }

   public void sourceSwitchNoMediaInfoChange(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void videoDestroy() {
      // $FF: Couldn't be decompiled
   }

   public void videoInfoChange(byte param1, byte param2, int param3, int param4, byte param5, byte param6, byte param7, String param8, byte param9, byte param10, String param11, String param12, String param13, int param14, byte param15, boolean param16, int param17) {
      // $FF: Couldn't be decompiled
   }

   public void voiceControlInfo(String param1) {
      // $FF: Couldn't be decompiled
   }
}
