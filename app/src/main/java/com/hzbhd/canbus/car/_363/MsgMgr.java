package com.hzbhd.canbus.car._363;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b#\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J&\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0014H\u0016J\"\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\"\u0010\u001d\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\"\u0010\u001e\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\"\u0010\u001f\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u001c\u0010 \u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\u0019H\u0016JÄ\u0001\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\b\u0010/\u001a\u0004\u0018\u00010\u00142\b\u00100\u001a\u0004\u0018\u00010\u00142\b\u00101\u001a\u0004\u0018\u00010\u00142\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u0002032\b\u00108\u001a\u0004\u0018\u00010\u00142\b\u00109\u001a\u0004\u0018\u00010\u00142\b\u0010:\u001a\u0004\u0018\u00010\u00142\u0006\u0010;\u001a\u00020\u001bH\u0016J6\u0010<\u001a\u00020\u00102\u0006\u0010=\u001a\u00020'2\b\u0010>\u001a\u0004\u0018\u00010\u00142\b\u0010?\u001a\u0004\u0018\u00010\u00142\b\u0010@\u001a\u0004\u0018\u00010\u00142\u0006\u0010A\u001a\u00020'H\u0016J\u0010\u0010B\u001a\u00020\u00102\u0006\u0010C\u001a\u00020'H\u0002J\u0016\u0010D\u001a\u00020\u00102\u0006\u0010C\u001a\u00020'2\u0006\u0010E\u001a\u00020\u0019J\u001a\u0010F\u001a\u00020\u00102\u0006\u0010G\u001a\u00020'2\b\u0010H\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010I\u001a\u00020\u0010H\u0002J\b\u0010J\u001a\u00020\u0010H\u0002J\b\u0010K\u001a\u00020\u0010H\u0002J\b\u0010L\u001a\u00020\u0010H\u0002J\b\u0010M\u001a\u00020\u0010H\u0002J\b\u0010N\u001a\u00020\u0010H\u0002J\u0010\u0010O\u001a\u00020\u00102\u0006\u0010P\u001a\u00020\u0019H\u0002J\u0010\u0010Q\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u001bH\u0016J\u0098\u0001\u0010S\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020$2\b\u0010,\u001a\u0004\u0018\u00010\u00142\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\b\u0010/\u001a\u0004\u0018\u00010\u00142\b\u00100\u001a\u0004\u0018\u00010\u00142\b\u00101\u001a\u0004\u0018\u00010\u00142\u0006\u00102\u001a\u00020'2\u0006\u0010T\u001a\u00020$2\u0006\u00106\u001a\u00020\u001b2\u0006\u0010U\u001a\u00020'H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006V"},
   d2 = {"Lcom/hzbhd/canbus/car/_363/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "auxInInfoChange", "btMusicId3InfoChange", "title", "", "artist", "album", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaSourceData", "d0", "sendPhoneNumber", "d3t26", "sendTextData", "comId", "text", "set0x11Data", "set0x12Data", "set0x26Data", "set0x32Data", "set0x41Data", "set0x87Data", "set0xF0Data", "bytes", "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoInfoChange", "playMode", "duration", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frame;

   private final void sendMediaSourceData(int var1) {
      byte[] var2 = MsgMgrKt.restrict$default(new byte[]{(byte)var1}, 13, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, var2));
   }

   private final void sendTextData(int var1, byte[] var2) {
      if (var2 != null) {
         byte var3 = (byte)var1;
         var2 = MsgMgrKt.restrict$default(var2, 32, 0, 4, (Object)null);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, var3}, var2));
      }
   }

   private final void set0x11Data() {
      switch (this.getFrame()[4]) {
         case 0:
            this.realKeyLongClick1(this.getContext(), 0, this.getFrame()[5]);
            break;
         case 1:
            this.realKeyLongClick1(this.getContext(), 7, this.getFrame()[5]);
            break;
         case 2:
            this.realKeyLongClick1(this.getContext(), 8, this.getFrame()[5]);
            break;
         case 3:
            this.realKeyLongClick1(this.getContext(), 3, this.getFrame()[5]);
            break;
         case 4:
            this.realKeyLongClick1(this.getContext(), 3, this.getFrame()[5]);
            break;
         case 5:
            this.realKeyLongClick1(this.getContext(), 14, this.getFrame()[5]);
            break;
         case 6:
            this.realKeyLongClick1(this.getContext(), 15, this.getFrame()[5]);
            break;
         case 7:
            this.realKeyLongClick1(this.getContext(), 0, this.getFrame()[5]);
            break;
         case 8:
            this.realKeyLongClick1(this.getContext(), 45, this.getFrame()[5]);
            break;
         case 9:
            this.realKeyLongClick1(this.getContext(), 46, this.getFrame()[5]);
            break;
         case 10:
            this.realKeyLongClick1(this.getContext(), 2, this.getFrame()[5]);
      }

      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void set0x12Data() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      this.updateDoorView(this.getContext());
   }

   private final void set0x26Data() {
   }

   private final void set0x32Data() {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]));
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5]) + " rpm");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]) + " km/h");
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x41Data() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(4, DataHandleUtils.rangeNumber(this.getFrame()[2], 2), this.getFrame()[3], this.getFrame()[4], DataHandleUtils.rangeNumber(this.getFrame()[5], 2));
      RadarInfoUtil.setFrontRadarLocationData(2, this.getFrame()[6], 0, 0, this.getFrame()[9]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void set0x87Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("_340_automatic_folding");
      if (var1 != null) {
         var1.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[4])));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      if (var1 != null) {
         this.setContext(var1);
         UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._363.UiMgr");
         AbstractUiMgr var3 = (AbstractUiMgr)((UiMgr)var2);
         InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, var3, (HashMap)null, 4, (Object)null);
         InitUtilsKt.initSettingItemsIndexHashMap$default(var1, var3, (HashMap)null, 4, (Object)null);
         CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCanDifferentId(), 0});
      }
   }

   public void auxInInfoChange() {
      this.sendMediaSourceData(12);
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      this.sendMediaSourceData(133);
      var3 = null;
      byte[] var4;
      if (var1 != null) {
         var4 = var1.getBytes(Charsets.UTF_8);
         Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      } else {
         var4 = null;
      }

      this.sendTextData(146, var4);
      var4 = (byte[])var3;
      if (var2 != null) {
         var4 = var2.getBytes(Charsets.UTF_8);
         Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      }

      this.sendTextData(148, var4);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      if (var1 != null) {
         this.sendPhoneNumber(0, var1);
      }
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      if (var1 != null) {
         this.sendPhoneNumber(1, var1);
      }
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      if (var1 != null) {
         this.sendPhoneNumber(2, var1);
      }
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      if (var1 != null) {
         this.sendPhoneNumber(4, var1);
      }
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var1);
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 38) {
               if (var3 != 50) {
                  if (var3 != 65) {
                     if (var3 != 135) {
                        if (var3 != 240) {
                           return;
                        }

                        this.set0xF0Data(var2);
                     } else {
                        this.set0x87Data();
                     }
                  } else {
                     this.set0x41Data();
                  }
               } else {
                  this.set0x32Data();
               }
            } else {
               this.set0x26Data();
            }
         } else {
            this.set0x12Data();
         }
      } else {
         this.set0x11Data();
      }

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

   public final int[] getFrame() {
      int[] var1 = this.frame;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("frame");
         return null;
      }
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      this.sendMediaSourceData(var1);
      var12 = null;
      byte[] var25;
      if (var21 != null) {
         var25 = var21.getBytes(Charsets.UTF_8);
         Intrinsics.checkNotNullExpressionValue(var25, "this as java.lang.String).getBytes(charset)");
      } else {
         var25 = null;
      }

      this.sendTextData(146, var25);
      var25 = (byte[])var12;
      if (var23 != null) {
         var25 = var23.getBytes(Charsets.UTF_8);
         Intrinsics.checkNotNullExpressionValue(var25, "this as java.lang.String).getBytes(charset)");
      }

      this.sendTextData(148, var25);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      if (var2 != null) {
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

         this.sendMediaSourceData(var6);
      }

   }

   public final void sendPhoneNumber(int var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var2, "d3t26");
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -51, (byte)var1, 0, 0}, var2));
   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      this.sendMediaSourceData(0);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      this.sendMediaSourceData(var1);
   }
}
