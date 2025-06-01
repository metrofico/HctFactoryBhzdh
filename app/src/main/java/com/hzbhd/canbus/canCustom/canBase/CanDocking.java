package com.hzbhd.canbus.canCustom.canBase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b4\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0019\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J&\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\"\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0010H\u0016J\"\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\"\u0010 \u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\"\u0010!\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016JT\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u00172\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\"\u0010(\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J*\u0010)\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u0017H\u0016J\b\u0010+\u001a\u00020\u0004H\u0016J\b\u0010,\u001a\u00020\u0004H\u0016J\u001c\u0010-\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010.\u001a\u0004\u0018\u00010\u001cH\u0016J\u0018\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u00020\u001eH\u0016J\u001a\u00102\u001a\u00020\u001e2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u00103\u001a\u00020\u0017H\u0016J\u001a\u00104\u001a\u00020\u001e2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u00103\u001a\u00020\u0017H\u0016Jp\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u00172\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u00172\u0006\u0010=\u001a\u00020\u00172\u0006\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\u0017H\u0016J\b\u0010C\u001a\u00020\u0004H\u0016Jh\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u00172\u0006\u0010I\u001a\u00020\u00172\u0006\u0010J\u001a\u00020\u00172\u0006\u0010K\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u00172\u0006\u0010M\u001a\u00020\u00172\u0006\u0010N\u001a\u00020\u00172\u0006\u0010O\u001a\u00020\u00172\u0006\u0010P\u001a\u00020\u0017H\u0016Jv\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u00172\u0006\u0010U\u001a\u00020\u00172\u0006\u0010V\u001a\u00020\u00172\u0006\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020\u00172\u0006\u0010Y\u001a\u00020\u00172\u0006\u0010Z\u001a\u00020\u001e2\u0006\u0010[\u001a\u00020\u001e2\u0006\u0010\\\u001a\u00020\u00172\b\u0010]\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010^\u001a\u00020\u0004H\u0016J\u0012\u0010_\u001a\u00020\u00042\b\u0010`\u001a\u0004\u0018\u00010aH\u0016J\u0010\u0010b\u001a\u00020c2\b\u0010d\u001a\u0004\u0018\u00010\u001cJ\b\u0010e\u001a\u00020\u0001H\u0016J\u0012\u0010f\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010g\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010h\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u0010i\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010j\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u0010k\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010l\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010m\u001a\u00020\u0004H\u0016JÈ\u0001\u0010n\u001a\u00020\u00042\u0006\u0010o\u001a\u00020S2\u0006\u0010p\u001a\u00020S2\u0006\u0010q\u001a\u00020\u00172\u0006\u0010r\u001a\u00020\u00172\u0006\u0010s\u001a\u00020S2\u0006\u0010t\u001a\u00020S2\u0006\u0010u\u001a\u00020S2\u0006\u0010v\u001a\u00020S2\u0006\u0010w\u001a\u00020S2\u0006\u0010x\u001a\u00020S2\b\u0010y\u001a\u0004\u0018\u00010\u00102\b\u0010z\u001a\u0004\u0018\u00010\u00102\b\u0010{\u001a\u0004\u0018\u00010\u00102\u0006\u0010|\u001a\u00020}2\u0006\u0010R\u001a\u00020S2\u0006\u0010~\u001a\u00020\u00172\u0006\u0010[\u001a\u00020\u001e2\u0006\u0010\u007f\u001a\u00020}2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00102\u0007\u0010\u0083\u0001\u001a\u00020\u001eH\u0016J\u0014\u0010\u0084\u0001\u001a\u00020\u00042\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0010H\u0016J\t\u0010\u0086\u0001\u001a\u00020\u0004H\u0016J\u0015\u0010\u0087\u0001\u001a\u00020\u00042\n\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u0001H\u0016J\t\u0010\u008a\u0001\u001a\u00020\u0004H\u0016J\t\u0010\u008b\u0001\u001a\u00020\u0004H\u0016J\u0013\u0010\u008c\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J.\u0010\u008d\u0001\u001a\u00020\u00042\u0007\u0010\u008e\u0001\u001a\u00020\u00172\u0007\u0010\u008f\u0001\u001a\u00020\u00172\u0007\u0010\u0090\u0001\u001a\u00020\u00172\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\t\u0010\u0091\u0001\u001a\u00020\u0004H\u0016J\t\u0010\u0092\u0001\u001a\u00020\u0004H\u0016J\t\u0010\u0093\u0001\u001a\u00020\u0004H\u0016J<\u0010\u0094\u0001\u001a\u00020\u00042\u0007\u0010\u0095\u0001\u001a\u00020\u00172\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u00102\u0007\u0010\u0099\u0001\u001a\u00020\u0017H\u0016J\u001c\u0010\u009a\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0007\u0010\u009b\u0001\u001a\u00020\u0017H\u0014J%\u0010\u009c\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0007\u0010\u009b\u0001\u001a\u00020\u00172\u0007\u0010\u009d\u0001\u001a\u00020\u0017H\u0014J\u001e\u0010\u009e\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u001cH\u0016J\u0015\u0010 \u0001\u001a\u00020\u00042\n\u0010¡\u0001\u001a\u0005\u0018\u00010¢\u0001H\u0016J\u0014\u0010£\u0001\u001a\u00020\u00042\t\u0010¤\u0001\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010¥\u0001\u001a\u00020\u00042\u0007\u0010¦\u0001\u001a\u00020\u001eH\u0016J\u001d\u0010§\u0001\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\n\u0010¨\u0001\u001a\u0005\u0018\u00010©\u0001H\u0014J\u001c\u0010ª\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0007\u0010«\u0001\u001a\u00020\u0010H\u0016J\t\u0010¬\u0001\u001a\u00020\u0004H\u0016J\u009b\u0001\u0010\u00ad\u0001\u001a\u00020\u00042\u0006\u0010o\u001a\u00020S2\u0006\u0010p\u001a\u00020S2\u0006\u0010q\u001a\u00020\u00172\u0006\u0010r\u001a\u00020\u00172\u0006\u0010s\u001a\u00020S2\u0006\u0010t\u001a\u00020S2\u0006\u0010u\u001a\u00020S2\b\u0010v\u001a\u0004\u0018\u00010\u00102\u0006\u0010w\u001a\u00020S2\u0006\u0010x\u001a\u00020S2\b\u0010y\u001a\u0004\u0018\u00010\u00102\b\u0010z\u001a\u0004\u0018\u00010\u00102\b\u0010{\u001a\u0004\u0018\u00010\u00102\u0006\u0010|\u001a\u00020\u00172\u0007\u0010®\u0001\u001a\u00020S2\u0006\u0010[\u001a\u00020\u001e2\u0007\u0010¯\u0001\u001a\u00020\u0017H\u0016J\u0014\u0010°\u0001\u001a\u00020\u00042\t\u0010±\u0001\u001a\u0004\u0018\u00010\u0010H\u0016¨\u0006²\u0001"},
   d2 = {"Lcom/hzbhd/canbus/canCustom/canBase/CanDocking;", "Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "()V", "aMapCallBack", "", "bundle", "Landroid/os/Bundle;", "afterServiceNormalSetting", "context", "Landroid/content/Context;", "atvDestdroy", "atvInfoChange", "auxInDestdroy", "auxInInfoChange", "btMusicId3InfoChange", "title", "", "artist", "album", "btMusicInfoChange", "btMusiceDestdroy", "btPhoneCallLogInfoChange", "index", "", "type", "number", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "btPhoneTalkingInfoChange", "btPhoneTalkingWithTimeInfoChange", "time", "cameraDestroy", "cameraInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "customLongClick", "key", "customShortClick", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "destroyCommand", "deviceStatusInfoChange", "btOn", "discRadom", "discRepeat", "discExsit", "sdCardIn", "usbIn", "radioSt", "mute", "singleCycle", "fullCurve", "folderLoop", "randomFolder", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "dtvInfoChange", "getBackCameraUiService", "backCameraUiService", "Lcom/hzbhd/canbus/park/BackCameraUiService;", "getByteArrayToIntArray", "", "mCanbusInfo", "getInstance", "initCommand", "linInfoChange", "linInfo", "mcuErrorState", "errorInfo", "medianCalibration", "calibrationInfo", "musicDestroy", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "musicLrcInfoChange", "lrc", "notifyBtStatusChange", "onAMapCallBack", "aMapEntity", "Lcom/hzbhd/canbus/util/amap/AMapEntity;", "onAccOff", "onAccOn", "onHandshake", "onKeyEvent", "hotKey", "keyStateOrArg1", "beepOrArg2", "onPowerOff", "onSleep", "radioDestroy", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "realKeyClick4", "whatKey", "realKeyLongClick1", "keyState", "serialDataChange", "serialData", "setMgrRefreshUiInterface", "mgrRefreshUiInterface", "Lcom/hzbhd/canbus/activity/AbstractBaseActivity;", "sourceSwitchChange", "source", "sourceSwitchNoMediaInfoChange", "isPowerOff", "startOtherActivity", "componentName", "Landroid/content/ComponentName;", "updateVersionInfo", "version", "videoDestroy", "videoInfoChange", "playMode", "duration", "voiceControlInfo", "action", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class CanDocking implements MsgMgrInterface {
   public void aMapCallBack(Bundle var1) {
   }

   public void afterServiceNormalSetting(Context var1) {
   }

   public void atvDestdroy() {
   }

   public void atvInfoChange() {
   }

   public void auxInDestdroy() {
   }

   public void auxInInfoChange() {
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
   }

   public void btMusicInfoChange() {
   }

   public void btMusiceDestdroy() {
   }

   public void btPhoneCallLogInfoChange(int var1, int var2, String var3) {
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
   }

   public void cameraDestroy() {
   }

   public void cameraInfoChange() {
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
   }

   public boolean customLongClick(Context var1, int var2) {
      return false;
   }

   public boolean customShortClick(Context var1, int var2) {
      return false;
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
   }

   public void destroyCommand() {
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
   }

   public void dtvInfoChange() {
   }

   public void getBackCameraUiService(BackCameraUiService var1) {
   }

   public final int[] getByteArrayToIntArray(byte[] var1) {
      int[] var5;
      if (var1 != null) {
         var5 = new int[var1.length];
      } else {
         var5 = null;
      }

      Intrinsics.checkNotNull(var5);
      int var2 = 0;

      for(int var3 = var1.length; var2 < var3; ++var2) {
         byte var4 = var1[var2];
         if ((var4 & 128) == 0) {
            var5[var2] = var4;
         } else {
            var5[var2] = var4 & 255;
         }
      }

      return var5;
   }

   public MsgMgrInterface getInstance() {
      return (MsgMgrInterface)this;
   }

   public void initCommand(Context var1) {
   }

   public void linInfoChange(Context var1, byte[] var2) {
   }

   public void mcuErrorState(Context var1, byte[] var2) {
   }

   public void medianCalibration(Context var1, byte[] var2) {
   }

   public void musicDestroy() {
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
   }

   public void musicLrcInfoChange(String var1) {
   }

   public void notifyBtStatusChange() {
   }

   public void onAMapCallBack(AMapEntity var1) {
   }

   public void onAccOff() {
   }

   public void onAccOn() {
   }

   public void onHandshake(Context var1) {
   }

   public void onKeyEvent(int var1, int var2, int var3, Bundle var4) {
   }

   public void onPowerOff() {
   }

   public void onSleep() {
   }

   public void radioDestroy() {
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
   }

   protected void realKeyClick4(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, 1);
      this.realKeyLongClick1(var1, var2, 0);
   }

   protected void realKeyLongClick1(Context var1, int var2, int var3) {
      if (!RealKeyUtil.isCompoundKey(var1, var2, var3)) {
         RealKeyUtil.realKeyLongClick(var1, var2, var3);
      }

   }

   public void serialDataChange(Context var1, byte[] var2) {
   }

   public void setMgrRefreshUiInterface(AbstractBaseActivity var1) {
   }

   public void sourceSwitchChange(String var1) {
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
   }

   protected void startOtherActivity(Context var1, ComponentName var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intent var3 = new Intent();
      var3.setComponent(var2);
      var3.setFlags(268435456);
      var1.startActivity(var3);
   }

   public void updateVersionInfo(Context var1, String var2) {
      Intrinsics.checkNotNullParameter(var2, "version");
      if (LogUtil.log5()) {
         LogUtil.d("updateVersionInfo: " + var2);
      }

      ShareDataServiceImpl.setString("canbus.canVersion", var2);
      GeneralData.INSTANCE.setCanVersion(var2);
   }

   public void videoDestroy() {
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
   }

   public void voiceControlInfo(String var1) {
   }
}
