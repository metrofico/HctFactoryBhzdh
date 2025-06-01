package com.hzbhd.canbus.car._355;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\u0019H\u0016JT\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0018\u0010)\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u001fH\u0016J\u0018\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020!H\u0016Jp\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020!2\u0006\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;H\u0016J\b\u0010>\u001a\u00020\u0019H\u0016JÄ\u0001\u0010?\u001a\u00020\u00192\u0006\u0010@\u001a\u0002002\u0006\u0010A\u001a\u0002002\u0006\u0010B\u001a\u00020\u00102\u0006\u0010C\u001a\u00020\u00102\u0006\u0010D\u001a\u0002002\u0006\u0010E\u001a\u0002002\u0006\u0010F\u001a\u0002002\u0006\u0010G\u001a\u0002002\u0006\u0010H\u001a\u0002002\u0006\u0010I\u001a\u0002002\b\u0010J\u001a\u0004\u0018\u00010;2\b\u0010K\u001a\u0004\u0018\u00010;2\b\u0010L\u001a\u0004\u0018\u00010;2\u0006\u0010M\u001a\u00020N2\u0006\u0010/\u001a\u0002002\u0006\u0010O\u001a\u00020\u00102\u0006\u00108\u001a\u00020!2\u0006\u0010P\u001a\u00020N2\b\u0010Q\u001a\u0004\u0018\u00010;2\b\u0010R\u001a\u0004\u0018\u00010;2\b\u0010S\u001a\u0004\u0018\u00010;2\u0006\u0010T\u001a\u00020!H\u0016J0\u0010U\u001a\u00020\u00192\u0006\u0010V\u001a\u00020\u00102\u0006\u0010W\u001a\u00020;2\u0006\u0010X\u001a\u00020;2\u0006\u0010Y\u001a\u00020;2\u0006\u0010Z\u001a\u00020\u0010H\u0016J\u000e\u0010[\u001a\u00020\u001f2\u0006\u0010\\\u001a\u00020\u001fJ\u001e\u0010]\u001a\u00020\u00192\u0006\u0010^\u001a\u00020\u00102\u0006\u0010_\u001a\u00020\u00102\u0006\u0010`\u001a\u00020\u001fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006a"},
   d2 = {"Lcom/hzbhd/canbus/car/_355/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frameData", "", "getFrameData", "()[I", "setFrameData", "([I)V", "vol", "", "getVol", "()I", "setVol", "(I)V", "volSource", "getVolSource", "setVolSource", "atvInfoChange", "", "auxInInfoChange", "btMusicInfoChange", "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "", "album", "artist", "dtvInfoChange", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "restrict", "param", "sendFrame", "d0", "d1", "d3", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frameData;
   private int vol;
   private int volSource;

   public void atvInfoChange() {
      byte[] var1 = "ATV".getBytes(Charsets.US_ASCII);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).getBytes(charset)");
      this.sendFrame(81, this.vol, var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "AUX".getBytes(Charsets.US_ASCII);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).getBytes(charset)");
      this.sendFrame(83, this.vol, var1);
   }

   public void btMusicInfoChange() {
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      var2 = "PHONE".getBytes(Charsets.US_ASCII);
      Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String).getBytes(charset)");
      this.sendFrame(64, this.vol, var2);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "getByteArrayToIntArray(canbusInfo)");
      this.setFrameData(var3);
      switch (this.getFrameData()[4]) {
         case 0:
            this.realKeyClick4(var1, 0);
            break;
         case 1:
            this.realKeyClick4(var1, 7);
            break;
         case 2:
            this.realKeyClick4(var1, 8);
            break;
         case 3:
            this.realKeyClick4(var1, 188);
            break;
         case 4:
            this.realKeyClick4(var1, 3);
            break;
         case 5:
            this.realKeyClick4(var1, 45);
            break;
         case 6:
            this.realKeyClick4(var1, 46);
            break;
         case 7:
            this.realKeyClick4(var1, 2);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.vol = var1;
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      Intrinsics.checkNotNullParameter(var11, "song");
      Intrinsics.checkNotNullParameter(var12, "album");
      Intrinsics.checkNotNullParameter(var13, "artist");
      byte[] var14 = "DISC".getBytes(Charsets.US_ASCII);
      Intrinsics.checkNotNullExpressionValue(var14, "this as java.lang.String).getBytes(charset)");
      this.sendFrame(32, this.vol, var14);
   }

   public void dtvInfoChange() {
      byte[] var1 = "DTV".getBytes(Charsets.US_ASCII);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).getBytes(charset)");
      this.sendFrame(87, this.vol, var1);
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

   public final int getVol() {
      return this.vol;
   }

   public final int getVolSource() {
      return this.volSource;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var4 != 0) {
         if (var1 == 9) {
            var11 = "SD";
         } else {
            var11 = "USB";
         }

         byte[] var25 = var11.getBytes(Charsets.US_ASCII);
         Intrinsics.checkNotNullExpressionValue(var25, "this as java.lang.String).getBytes(charset)");
         if (var1 == 9) {
            var1 = 85;
         } else {
            var1 = 84;
         }

         this.sendFrame(var1, this.vol, var25);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var6;
      label32: {
         Intrinsics.checkNotNullParameter(var2, "currBand");
         Intrinsics.checkNotNullParameter(var3, "currentFreq");
         Intrinsics.checkNotNullParameter(var4, "psName");
         switch (var2) {
            case "AM1":
               var6 = 20;
               break label32;
            case "AM2":
               var6 = 21;
               break label32;
            case "FM1":
               var6 = 17;
               break label32;
            case "FM2":
               var6 = 18;
               break label32;
            case "FM3":
               var6 = 19;
               break label32;
         }

         var6 = 0;
      }

      this.volSource = var6;
      var1 = this.vol;
      byte[] var7 = var2.getBytes(Charsets.US_ASCII);
      Intrinsics.checkNotNullExpressionValue(var7, "this as java.lang.String).getBytes(charset)");
      this.sendFrame(this.volSource, var1, var7);
   }

   public final byte[] restrict(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "param");
      if (var1.length > 8) {
         var1 = ArraysKt.copyOfRange(var1, 0, 7);
      } else {
         int var3 = 8 - var1.length;
         byte[] var4 = new byte[var3];

         for(int var2 = 0; var2 < var3; ++var2) {
            var4[var2] = 0;
         }

         var1 = ArraysKt.plus(var1, var4);
      }

      return var1;
   }

   public final void sendFrame(int var1, int var2, byte[] var3) {
      Intrinsics.checkNotNullParameter(var3, "d3");
      byte var4 = (byte)var1;
      byte var5 = (byte)var2;
      var3 = this.restrict(var3);
      CanbusMsgSender.sendMsg(ArraysKt.plus(ArraysKt.plus(new byte[]{22, 12, var4, var5, 8}, var3), (byte)0));
   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrameData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frameData = var1;
   }

   public final void setVol(int var1) {
      this.vol = var1;
   }

   public final void setVolSource(int var1) {
      this.volSource = var1;
   }
}
