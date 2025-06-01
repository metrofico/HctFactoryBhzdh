package com.hzbhd.canbus.car._18;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0010\t\n\u0002\b0\u0018\u0000 ²\u00012\u00020\u0001:\u0002²\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010B\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010D\u001a\u00020CH\u0002J\b\u0010E\u001a\u00020CH\u0016J&\u0010F\u001a\u00020C2\b\u00102\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\"\u0010G\u001a\u00020C2\b\u0010H\u001a\u0004\u0018\u00010 2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\rH\u0016JT\u0010K\u001a\u00020C2\u0006\u0010L\u001a\u00020:2\b\u0010H\u001a\u0004\u0018\u00010 2\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\r2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\r2\u0006\u0010O\u001a\u00020:2\u0006\u0010P\u001a\u00020:2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0016J\u0010\u0010S\u001a\u00020C2\u0006\u0010T\u001a\u00020:H\u0002J\u001c\u0010U\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010V\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010W\u001a\u00020C2\u0006\u0010X\u001a\u00020:2\u0006\u0010Y\u001a\u00020\rH\u0016Jp\u0010Z\u001a\u00020C2\u0006\u0010[\u001a\u00020:2\u0006\u0010\\\u001a\u00020:2\u0006\u0010]\u001a\u00020:2\u0006\u0010^\u001a\u00020:2\u0006\u0010_\u001a\u00020:2\u0006\u0010`\u001a\u00020:2\u0006\u0010a\u001a\u00020:2\u0006\u0010b\u001a\u00020:2\u0006\u0010c\u001a\u00020:2\u0006\u0010d\u001a\u00020\r2\u0006\u0010e\u001a\u00020\r2\u0006\u0010f\u001a\u00020\r2\u0006\u0010g\u001a\u00020:H\u0016J\u0010\u0010h\u001a\u00020C2\u0006\u0010i\u001a\u00020:H\u0002J\b\u0010j\u001a\u00020CH\u0002J\b\u0010k\u001a\u00020CH\u0002J\u0012\u0010l\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010m\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010n\u001a\u00020\r2\u0006\u0010o\u001a\u00020\u0011H\u0002J\u0010\u0010p\u001a\u00020\r2\u0006\u0010o\u001a\u00020\u0011H\u0002J\u0010\u0010q\u001a\u00020\r2\u0006\u0010o\u001a\u00020\u0011H\u0002J\b\u0010r\u001a\u00020CH\u0002J\b\u0010s\u001a\u00020CH\u0002JÑ\u0001\u0010t\u001a\u00020C2\u0006\u0010u\u001a\u00020\u001d2\u0006\u0010v\u001a\u00020\u001d2\u0006\u0010w\u001a\u00020:2\u0006\u0010x\u001a\u00020:2\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010z\u001a\u00020\u001d2\u0006\u0010{\u001a\u00020\u001d2\u0006\u0010|\u001a\u00020\u001d2\u0006\u0010}\u001a\u00020\u001d2\u0006\u0010~\u001a\u00020\u001d2\b\u0010\u007f\u001a\u0004\u0018\u00010\u00042\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00042\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\u001d2\u0007\u0010\u0085\u0001\u001a\u00020:2\u0007\u0010\u0086\u0001\u001a\u00020\r2\b\u0010\u0087\u0001\u001a\u00030\u0083\u00012\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u008b\u0001\u001a\u00020\rH\u0016J\t\u0010\u008c\u0001\u001a\u00020CH\u0002J\t\u0010\u008d\u0001\u001a\u00020CH\u0002J\t\u0010\u008e\u0001\u001a\u00020CH\u0002J<\u0010\u008f\u0001\u001a\u00020C2\u0007\u0010\u0090\u0001\u001a\u00020:2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u0094\u0001\u001a\u00020:H\u0016J\u001b\u0010\u0095\u0001\u001a\u00020 2\u0007\u0010\u0096\u0001\u001a\u00020 2\u0007\u0010\u0097\u0001\u001a\u00020:H\u0002J\u0012\u0010\u0098\u0001\u001a\u00020C2\u0007\u0010\u0099\u0001\u001a\u00020:H\u0002J\u001c\u0010\u009a\u0001\u001a\u00020C2\u0007\u0010\u009b\u0001\u001a\u00020:2\b\u0010H\u001a\u0004\u0018\u00010 H\u0002J\t\u0010\u009c\u0001\u001a\u00020CH\u0002J\t\u0010\u009d\u0001\u001a\u00020CH\u0002J\t\u0010\u009e\u0001\u001a\u00020CH\u0002J\t\u0010\u009f\u0001\u001a\u00020CH\u0002J\t\u0010 \u0001\u001a\u00020CH\u0002J\t\u0010¡\u0001\u001a\u00020CH\u0002J\t\u0010¢\u0001\u001a\u00020CH\u0002J\t\u0010£\u0001\u001a\u00020CH\u0002J\u0011\u0010¤\u0001\u001a\u00020C2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010¥\u0001\u001a\u00020C2\u0007\u0010\u009b\u0001\u001a\u00020:H\u0002J\u0012\u0010¦\u0001\u001a\u00020C2\u0007\u0010§\u0001\u001a\u00020\rH\u0016J\t\u0010¨\u0001\u001a\u00020CH\u0002J\"\u0010©\u0001\u001a\u00020C2\u0007\u0010ª\u0001\u001a\u00020:2\u0007\u0010«\u0001\u001a\u00020:2\u0007\u0010¬\u0001\u001a\u00020:J\t\u0010\u00ad\u0001\u001a\u00020CH\u0002J\u0011\u0010®\u0001\u001a\u00020C2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001b\u0010¯\u0001\u001a\u00020C2\u0007\u0010°\u0001\u001a\u00020:2\u0007\u0010±\u0001\u001a\u00020:H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R\u001a\u0010$\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0013\"\u0004\b&\u0010\u0015R\u001a\u0010'\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R2\u0010.\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u0003000/j\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u000300`1X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000204X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0012\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010<\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010=\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010>\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010?\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010@\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0010\u0010A\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006³\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_18/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "album", "", "artist", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "first0x31Data", "", "first0x37Data", "first0x82Data", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "isInit", "()Z", "setInit", "(Z)V", "lastAlbum", "lastArtist", "lastKnobValue", "", "lastTitle", "lastX91", "", "mAirData0x31", "getMAirData0x31", "setMAirData0x31", "mAirData0x37", "getMAirData0x37", "setMAirData0x37", "mAirData0x82", "getMAirData0x82", "setMAirData0x82", "mOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "getMOriginalCarDevicePageUiSet", "()Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "mSettingItemsIndex", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "Lkotlin/collections/HashMap;", "title", "uiMgr", "Lcom/hzbhd/canbus/car/_18/UiMgr;", "getUiMgr", "()Lcom/hzbhd/canbus/car/_18/UiMgr;", "setUiMgr", "(Lcom/hzbhd/canbus/car/_18/UiMgr;)V", "x11D0", "", "Ljava/lang/Integer;", "x11D2", "x11D4", "x11D6", "x11D7", "x21D0", "x91", "afterServiceNormalSetting", "", "amplifier", "auxInInfoChange", "btMusicId3InfoChange", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "isAudioTransferTowardsAG", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "button", "d2", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "door", "d4", "horizontalAir", "hybrid", "initCommand", "initRestValue", "isAirNoDataChange0x31", "mCanBusInfoInt", "isAirNoDataChange0x37", "isAirNoDataChange0x82", "language", "media", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panel", "panorama", "radar", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "restrict", "param", "max", "selectCarMode", "currentCanDifferentId", "sendPhoneNumber", "d0", "sendSrcInfo", "sendText", "set0x17Data", "set0x86Data", "setCarInfoData0", "setCarInfoData1", "setCarInfoData2", "setCarModelData", "settings", "signal", "sourceSwitchNoMediaInfoChange", "isPowerOff", "tyre", "updateSettings", "leftListIndex", "rightListIndex", "value", "verticalAir", "verticalAirSupplement", "wheel", "d6", "d7", "CONST", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_RANGE_MAX = 7;
   public static final CONST CONST = new CONST((DefaultConstructorMarker)null);
   private String album;
   private String artist;
   public Context context;
   private boolean first0x31Data;
   private boolean first0x37Data;
   private boolean first0x82Data;
   public int[] frame;
   private boolean isInit;
   private String lastAlbum;
   private String lastArtist;
   private byte lastKnobValue;
   private String lastTitle;
   private byte[] lastX91;
   private int[] mAirData0x31;
   private int[] mAirData0x37;
   private int[] mAirData0x82;
   private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private final HashMap mSettingItemsIndex = new HashMap();
   private String title;
   public UiMgr uiMgr;
   private Integer x11D0;
   private Integer x11D2;
   private Integer x11D4;
   private Integer x11D6;
   private Integer x11D7;
   private Integer x21D0;
   private byte[] x91;

   // $FF: synthetic method
   public static void $r8$lambda$bUAsUS59XxawI0WDCh0PbGsmLmQ(MsgMgr var0) {
      initCommand$lambda_2(var0);
   }

   public MsgMgr() {
      OriginalCarDevicePageUiSet var1 = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getOriginalCarDevicePageUiSet(BaseUtil.INSTANCE.getContext());
      Intrinsics.checkNotNullExpressionValue(var1, "getCanUiMgr(BaseUtil.con…geUiSet(BaseUtil.context)");
      this.mOriginalCarDevicePageUiSet = var1;
      this.first0x37Data = true;
      this.first0x82Data = true;
      this.first0x31Data = true;
      this.mAirData0x31 = new int[30];
      this.mAirData0x82 = new int[30];
      this.mAirData0x37 = new int[30];
   }

   private final void amplifier() {
      GeneralAmplifierData.volume = this.getFrame()[2];
      GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(this.getFrame()[3], 0, 14) - 7;
      GeneralAmplifierData.frontRear = MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(this.getFrame()[4], 0, 14), 0, 2, (Object)null) - 7;
      GeneralAmplifierData.bandBass = this.getFrame()[5];
      GeneralAmplifierData.bandMiddle = this.getFrame()[6];
      GeneralAmplifierData.bandTreble = this.getFrame()[7];
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18xA61");
      if (var1 != null) {
         var1.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18xA62");
      if (var1 != null) {
         var1.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(this.getFrame()[8])));
      }

      this.updateAmplifierActivity((Bundle)null);
      this.updateSettingActivity((Bundle)null);
   }

   private final void button(int var1) {
      Integer var3 = this.x11D2;
      if ((var3 == null || var3 != var1) && this.getFrame()[5] != 0) {
         switch (var1) {
            case 0:
               this.realKeyClick4(this.getContext(), 0);
               break;
            case 1:
               this.realKeyClick4(this.getContext(), 7);
               break;
            case 2:
               this.realKeyClick4(this.getContext(), 8);
            case 3:
            case 7:
            case 10:
            case 11:
            default:
               break;
            case 4:
               this.realKeyClick4(this.getContext(), 187);
               break;
            case 5:
               Context var4 = this.getContext();
               short var2;
               if (this.getCurrentCanDifferentId() == 6) {
                  var2 = 188;
               } else {
                  var2 = 14;
               }

               this.realKeyClick4(var4, var2);
               break;
            case 6:
               this.realKeyClick4(this.getContext(), 15);
               break;
            case 8:
               this.realKeyClick4(this.getContext(), 21);
               break;
            case 9:
               this.realKeyClick4(this.getContext(), 20);
               break;
            case 12:
               this.realKeyClick4(this.getContext(), 2);
               break;
            case 13:
               this.realKeyClick4(this.getContext(), 45);
               break;
            case 14:
               this.realKeyClick4(this.getContext(), 46);
               break;
            case 15:
               this.realKeyClick4(this.getContext(), 49);
               break;
            case 16:
               this.realKeyClick4(this.getContext(), 50);
         }
      }

      this.x11D2 = var1;
   }

   private final void door(int var1) {
      Integer var2 = this.x11D4;
      if (var2 == null || var2 != var1) {
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
         this.updateDoorView(this.getContext());
      }

      this.x11D4 = var1;
   }

   private final void horizontalAir() {
      if (this.first0x82Data) {
         this.first0x82Data = false;
         this.mAirData0x82 = this.getFrame();
      } else if (!this.isAirNoDataChange0x82(this.getFrame())) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(this.getFrame()[2]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.getFrame()[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit3(this.getFrame()[3]) ^ true;
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.getFrame()[3]);
         GeneralAirData.eco = DataHandleUtils.getBoolBit1(this.getFrame()[3]);
         if (DataHandleUtils.getBoolBit0(this.getFrame()[3])) {
            GeneralAirData.in_out_auto_cycle = 2;
         } else if (GeneralAirData.in_out_cycle) {
            GeneralAirData.in_out_auto_cycle = 0;
         } else {
            GeneralAirData.in_out_auto_cycle = 1;
         }

         int var1 = this.getFrame()[4];
         String var3 = "HIGH";
         String var2;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 - (double)40 + " °C";
               } else {
                  var2 = "HIGH";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.front_left_temperature = var2;
         var1 = this.getFrame()[5];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 - (double)40 + " °C";
               } else {
                  var2 = "HIGH";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.front_right_temperature = var2;
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.getFrame()[6]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.getFrame()[6]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.getFrame()[6]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.getFrame()[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.getFrame()[6]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.getFrame()[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 0, 4);
         GeneralAirData.rear_left_blow_head = false;
         GeneralAirData.rear_right_blow_head = false;
         GeneralAirData.rear_left_blow_foot = false;
         GeneralAirData.rear_right_blow_foot = false;
         var1 = this.getFrame()[7];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_right_blow_head = true;
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_foot = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_right_blow_head = true;
            }
         } else {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
         }

         GeneralAirData.rear_wind_level = this.getFrame()[8];
         var1 = this.getFrame()[9];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 - (double)40 + " °C";
               } else {
                  var2 = "HIGH";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.rear_temperature = var2;
         var1 = this.getFrame()[9];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 - (double)40 + " °C";
               } else {
                  var2 = "HIGH";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.rear_right_temperature = var2;
         var1 = this.getFrame()[9];
         if (var1 != 0) {
            if (var1 != 1) {
               var2 = var3;
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 - (double)40 + " °C";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.rear_left_temperature = var2;
         this.updateAirActivity(this.getContext(), 1004);
         this.updateAirActivity(this.getContext(), 1003);
      }
   }

   private final void hybrid() {
      GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 4);
      GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.getFrame()[3]);
      GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.getFrame()[3]);
      GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.getFrame()[3]);
      GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.getFrame()[3]);
      this.updateHybirdActivity((Bundle)null);
   }

   private static final void initCommand$lambda_2(MsgMgr var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.getUiMgr().sendPanoramaCmd(6);
   }

   private final void initRestValue(Context var1) {
      if (!this.isInit) {
         this.isInit = true;
         List var6 = (List)(new ArrayList());
         int var2 = this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset");
         int var3 = this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_1");
         Object var5 = null;
         String var4;
         if (var1 != null) {
            var4 = var1.getString(2131758767);
         } else {
            var4 = null;
         }

         SettingUpdateEntity var8 = (new SettingUpdateEntity(var2, var3, var4)).setValueStr(true);
         Intrinsics.checkNotNullExpressionValue(var8, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
         var6.add(var8);
         var2 = this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset");
         var3 = this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_2");
         if (var1 != null) {
            var4 = var1.getString(2131758767);
         } else {
            var4 = null;
         }

         var8 = (new SettingUpdateEntity(var2, var3, var4)).setValueStr(true);
         Intrinsics.checkNotNullExpressionValue(var8, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
         var6.add(var8);
         var2 = this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset");
         var3 = this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_3");
         if (var1 != null) {
            var4 = var1.getString(2131758767);
         } else {
            var4 = null;
         }

         var8 = (new SettingUpdateEntity(var2, var3, var4)).setValueStr(true);
         Intrinsics.checkNotNullExpressionValue(var8, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
         var6.add(var8);
         var3 = this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset");
         var2 = this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_4");
         if (var1 != null) {
            var4 = var1.getString(2131758767);
         } else {
            var4 = null;
         }

         var8 = (new SettingUpdateEntity(var3, var2, var4)).setValueStr(true);
         Intrinsics.checkNotNullExpressionValue(var8, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
         var6.add(var8);
         var2 = this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset");
         var3 = this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_5");
         var4 = (String)var5;
         if (var1 != null) {
            var4 = var1.getString(2131758767);
         }

         SettingUpdateEntity var7 = (new SettingUpdateEntity(var2, var3, var4)).setValueStr(true);
         Intrinsics.checkNotNullExpressionValue(var7, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
         var6.add(var7);
         this.updateGeneralSettingData(var6);
      }
   }

   private final boolean isAirNoDataChange0x31(int[] var1) {
      if (Arrays.equals(this.mAirData0x31, var1)) {
         return true;
      } else {
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
         this.mAirData0x31 = var1;
         return false;
      }
   }

   private final boolean isAirNoDataChange0x37(int[] var1) {
      if (Arrays.equals(this.mAirData0x37, var1)) {
         return true;
      } else {
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
         this.mAirData0x37 = var1;
         return false;
      }
   }

   private final boolean isAirNoDataChange0x82(int[] var1) {
      if (Arrays.equals(this.mAirData0x82, var1)) {
         return true;
      } else {
         var1 = Arrays.copyOf(var1, var1.length);
         Intrinsics.checkNotNullExpressionValue(var1, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
         this.mAirData0x82 = var1;
         return false;
      }
   }

   private final void language() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("language-settings");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(this.getFrame()[3] - 1));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void media() {
      int var1 = this.getFrame()[2];
      if (var1 != 0) {
         if (var1 != 38) {
            if (var1 != 39) {
               switch (var1) {
                  case 32:
                     this.realKeyClick4(this.getContext(), 77);
                     break;
                  case 33:
                     this.realKeyClick4(this.getContext(), 76);
                     break;
                  case 34:
                     this.realKeyClick4(this.getContext(), 59);
                     break;
                  case 35:
                     this.realKeyClick4(this.getContext(), 140);
               }
            } else {
               this.realKeyClick4(this.getContext(), 141);
            }
         } else {
            this.realKeyClick4(this.getContext(), 130);
         }
      } else {
         this.enterNoSource();
      }

   }

   private final void panel() {
      Integer var2 = this.x21D0;
      int var1 = this.getFrame()[2];
      if ((var2 == null || var2 != var1) && this.getFrame()[3] != 0) {
         var1 = this.getFrame()[2];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 6) {
                     if (var1 != 16) {
                        if (var1 != 18) {
                           if (var1 != 36) {
                              if (var1 != 40) {
                                 if (var1 != 57) {
                                    if (var1 != 59) {
                                       if (var1 != 75) {
                                          if (var1 != 97) {
                                             if (var1 != 8) {
                                                if (var1 != 9) {
                                                   if (var1 != 32) {
                                                      if (var1 != 33) {
                                                         if (var1 != 47) {
                                                            if (var1 != 48) {
                                                               if (var1 != 69) {
                                                                  if (var1 != 70) {
                                                                     switch (var1) {
                                                                        case 42:
                                                                           this.realKeyClick4(this.getContext(), 49);
                                                                           break;
                                                                        case 43:
                                                                           this.realKeyClick4(this.getContext(), 52);
                                                                           break;
                                                                        case 44:
                                                                           this.realKeyClick4(this.getContext(), 2);
                                                                           break;
                                                                        default:
                                                                           switch (var1) {
                                                                              case 51:
                                                                                 this.realKeyClick4(this.getContext(), 76);
                                                                                 break;
                                                                              case 52:
                                                                                 this.realKeyClick4(this.getContext(), 14);
                                                                                 break;
                                                                              case 53:
                                                                                 this.realKeyClick4(this.getContext(), 15);
                                                                           }
                                                                     }
                                                                  } else {
                                                                     this.realKeyClick4(this.getContext(), 8);
                                                                  }
                                                               } else {
                                                                  this.realKeyClick4(this.getContext(), 7);
                                                               }
                                                            } else {
                                                               this.realKeyClick4(this.getContext(), 68);
                                                            }
                                                         } else {
                                                            this.realKeyClick4(this.getContext(), 151);
                                                         }
                                                      } else {
                                                         this.realKeyClick4(this.getContext(), 39);
                                                      }
                                                   } else {
                                                      this.realKeyClick4(this.getContext(), 128);
                                                   }
                                                } else {
                                                   this.realKeyClick4(this.getContext(), 3);
                                                }
                                             } else {
                                                this.realKeyClick4(this.getContext(), 141);
                                             }
                                          } else {
                                             this.updateAirActivity(this.getContext(), 1001);
                                          }
                                       } else {
                                          this.realKeyClick4(this.getContext(), 62);
                                       }
                                    } else {
                                       this.realKeyClick4(this.getContext(), 2);
                                    }
                                 } else {
                                    this.realKeyClick4(this.getContext(), 57);
                                 }
                              } else {
                                 this.realKeyClick4(this.getContext(), 188);
                              }
                           } else {
                              this.realKeyClick4(this.getContext(), 2);
                           }
                        } else {
                           this.realKeyClick4(this.getContext(), 58);
                        }
                     } else {
                        this.realKeyClick4(this.getContext(), 95);
                     }
                  } else {
                     this.realKeyClick4(this.getContext(), 50);
                  }
               } else {
                  this.realKeyClick4(this.getContext(), 20);
               }
            } else {
               this.realKeyClick4(this.getContext(), 21);
            }
         } else {
            this.realKeyClick4(this.getContext(), 134);
         }
      }

      this.x21D0 = this.getFrame()[2];
   }

   private final void panorama() {
      Context var3 = this.getContext();
      int var1 = this.getFrame()[5];
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      this.forceReverse(var3, var2);
   }

   private final void radar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 255;
      RadarInfoUtil.setRearRadarLocationData(4, this.getFrame()[2], this.getFrame()[3], this.getFrame()[4], this.getFrame()[5]);
      RadarInfoUtil.setFrontRadarLocationData(4, this.getFrame()[6], this.getFrame()[7], this.getFrame()[7], this.getFrame()[9]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final byte[] restrict(byte[] var1, int var2) {
      if (var1.length > var2) {
         var1 = ArraysKt.copyOfRange(var1, 0, var2);
      } else {
         int var3 = var2 - var1.length;
         byte[] var4 = new byte[var3];

         for(var2 = 0; var2 < var3; ++var2) {
            var4[var2] = 0;
         }

         var1 = ArraysKt.plus(var1, var4);
      }

      return var1;
   }

   private final void selectCarMode(int var1) {
      if (var1 != 32) {
         switch (var1) {
            case 1:
               this.getUiMgr().sendCarModelData(3);
               break;
            case 2:
               this.getUiMgr().sendCarModelData(4);
               break;
            case 3:
               this.getUiMgr().sendCarModelData(27);
               break;
            case 4:
               this.getUiMgr().sendCarModelData(112);
               break;
            case 5:
               this.getUiMgr().sendCarModelData(2);
               break;
            case 6:
               this.getUiMgr().sendCarModelData(24);
               break;
            case 7:
               this.getUiMgr().sendCarModelData(12);
               break;
            case 8:
               this.getUiMgr().sendCarModelData(20);
               break;
            case 9:
               this.getUiMgr().sendCarModelData(17);
               break;
            case 10:
               this.getUiMgr().sendCarModelData(112);
               break;
            case 11:
               this.getUiMgr().sendCarModelData(112);
               break;
            case 12:
               this.getUiMgr().sendCarModelData(112);
               break;
            case 13:
               this.getUiMgr().sendCarModelData(25);
               break;
            case 14:
               this.getUiMgr().sendCarModelData(26);
               break;
            case 15:
               this.getUiMgr().sendCarModelData(18);
               break;
            default:
               this.getUiMgr().sendCarModelData(var1 + 131 - 128);
         }
      } else {
         this.getUiMgr().sendCarModelData(112);
      }

   }

   private final void sendPhoneNumber(int var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      var2 = this.restrict(var2, 24);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -51, (byte)var1, 0, 0}, var2));
   }

   private final void sendSrcInfo() {
      if (!Arrays.equals(this.x91, this.lastX91)) {
         byte[] var1 = this.x91;
         if (var1 == null) {
            return;
         }

         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, var1));
      }

      this.lastX91 = this.x91;
   }

   private final void sendText() {
      boolean var1 = Intrinsics.areEqual((Object)this.lastTitle, (Object)this.title);
      String var3 = "Unknown";
      String var2;
      String var4;
      byte[] var5;
      if (!var1) {
         var4 = this.title;
         var2 = var4;
         if (var4 == null) {
            var2 = "Unknown";
         }

         var5 = var2.getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).getBytes(charset)");
         var5 = this.restrict(var5, 32);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -110}, var5));
      }

      if (!Intrinsics.areEqual((Object)this.lastAlbum, (Object)this.album)) {
         var4 = this.album;
         var2 = var4;
         if (var4 == null) {
            var2 = "Unknown";
         }

         var5 = var2.getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).getBytes(charset)");
         var5 = this.restrict(var5, 32);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -109}, var5));
      }

      if (!Intrinsics.areEqual((Object)this.lastArtist, (Object)this.artist)) {
         var2 = this.artist;
         if (var2 == null) {
            var2 = var3;
         }

         var5 = var2.getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).getBytes(charset)");
         var5 = this.restrict(var5, 32);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -108}, var5));
      }

      this.lastTitle = this.title;
      this.lastAlbum = this.album;
      this.lastArtist = this.artist;
   }

   private final void set0x17Data() {
      if (this.getFrame().length >= 63) {
         DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_1");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[2], this.getFrame()[3]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_2");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_3");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_4");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[9]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_5");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[11]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_6");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[12], this.getFrame()[13]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_7");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[14], this.getFrame()[15]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_8");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[16], this.getFrame()[17]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_9");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[18], this.getFrame()[19]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_10");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[20], this.getFrame()[21]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_11");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[22], this.getFrame()[23]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_12");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[24], this.getFrame()[25]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_13");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[26], this.getFrame()[27]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_14");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[28], this.getFrame()[29]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_15");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[30], this.getFrame()[31]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_16");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[32], this.getFrame()[33]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_17");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[34], this.getFrame()[35]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_18");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[36], this.getFrame()[37]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_19");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[38], this.getFrame()[39]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_20");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[40], this.getFrame()[41]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_21");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[42], this.getFrame()[43]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_22");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[44], this.getFrame()[45]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_23");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[46], this.getFrame()[47]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_24");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[48], this.getFrame()[49]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_25");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[50], this.getFrame()[51]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_26");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[52], this.getFrame()[53]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_27");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[54], this.getFrame()[55]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_28");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[56], this.getFrame()[57]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_29");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[58], this.getFrame()[59]) / 10.0));
         }

         var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_30");
         if (var2 != null) {
            var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[60], this.getFrame()[61]) / 10.0));
         }

         DriverDataPageUiSet.Page.Item var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_x17_31");
         if (var3 != null) {
            int var1 = this.getFrame()[62];
            String var4;
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     return;
                  }

                  var4 = "L/100Km";
               } else {
                  var4 = "Km/L";
               }
            } else {
               var4 = "MPG";
            }

            var3.setValue(var4);
         }

         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private final void set0x86Data() {
      int var1 = this.getFrame()[8];
      String var4;
      if (var1 != 4) {
         if (var1 != 8) {
            if (var1 != 16) {
               if (var1 != 32) {
                  if (var1 != 64) {
                     if (var1 != 128) {
                        var4 = "More";
                     } else {
                        var4 = "Scan";
                     }
                  } else {
                     var4 = "Disc Scan";
                  }
               } else {
                  var4 = "Repeat";
               }
            } else {
               var4 = "Disc Repeat";
            }
         } else {
            var4 = "Random";
         }
      } else {
         var4 = "Disc Random";
      }

      GeneralOriginalCarDeviceData.cdStatus = var4;
      switch (this.getFrame()[9]) {
         case 2:
            var4 = "Play";
            break;
         case 3:
            var4 = "Fast";
            break;
         case 4:
            var4 = "User search";
            break;
         case 5:
            var4 = "Internal search";
            break;
         case 6:
            var4 = "Stop";
            break;
         case 7:
            var4 = "Rom read";
            break;
         case 8:
            var4 = "Rom search";
            break;
         case 9:
            var4 = "Retrieving";
            break;
         case 10:
            var4 = "Disc changing(User)";
            break;
         case 11:
            var4 = "Disc changing(Internal)";
            break;
         case 12:
            var4 = "Eject";
            break;
         default:
            var4 = "Other";
      }

      GeneralOriginalCarDeviceData.runningState = var4;
      List var5 = this.mOriginalCarDevicePageUiSet.getItems();
      var5.clear();
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 4);
      boolean var2 = false;
      boolean var6 = var2;
      if (1 <= var3) {
         var6 = var2;
         if (var3 < 7) {
            var6 = true;
         }
      }

      if (var6) {
         var4 = "Disk " + var3;
      } else {
         var4 = "Single";
      }

      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_disc", var4));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_track", String.valueOf(this.getFrame()[5])));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_play_time", this.getFrame()[6] + " : " + this.getFrame()[7]));
      Bundle var7 = new Bundle();
      var7.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var7);
   }

   private final void setCarInfoData0() {
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_6");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[2], this.getFrame()[3]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_7");
      if (var2 != null) {
         var2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_8");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_9");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[9]) + " Min");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_10");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[11]) + " Km/H");
      }

      DriverDataPageUiSet.Page.Item var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_11");
      int var1;
      String var4;
      if (var3 != null) {
         var1 = this.getFrame()[12];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  return;
               }

               var4 = "L/100Km";
            } else {
               var4 = "Km/L";
            }
         } else {
            var4 = "MPG";
         }

         var3.setValue(var4);
      }

      var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_12");
      if (var3 != null) {
         var1 = this.getFrame()[13];
         if (var1 != 0) {
            if (var1 != 1) {
               return;
            }

            var4 = "Mile";
         } else {
            var4 = "Km";
         }

         var3.setValue(var4);
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setCarInfoData1() {
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_1");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[2], this.getFrame()[3]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_2");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_3");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_4");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[9]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_5");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[11]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_6");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[12], this.getFrame()[13]) / 10.0F));
      }

      DriverDataPageUiSet.Page.Item var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_7");
      if (var3 != null) {
         int var1 = this.getFrame()[14];
         String var4;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  return;
               }

               var4 = "L/100Km";
            } else {
               var4 = "Km/L";
            }
         } else {
            var4 = "MPG";
         }

         var3.setValue(var4);
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setCarInfoData2() {
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_1");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_2");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7])));
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setCarModelData() {
      Exception var10000;
      label30: {
         SettingPageUiSet.ListBean.ItemListBean var1;
         boolean var10001;
         try {
            var1 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_Car_0");
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label30;
         }

         if (var1 != null) {
            try {
               var1.setValue(var1.getValueSrnArray().get(this.getFrame()[3] - 128));
            } catch (Exception var3) {
               var10000 = var3;
               var10001 = false;
               break label30;
            }
         }

         try {
            this.updateSettingActivity((Bundle)null);
            return;
         } catch (Exception var2) {
            var10000 = var2;
            var10001 = false;
         }
      }

      Exception var5 = var10000;
      Log.e("CanBusError", var5.toString());
   }

   private final void settings(int[] var1) {
      List var6 = (List)(new ArrayList());
      var6.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "S18Title1"), this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "S18Title1", "_18_park_0"), DataHandleUtils.getIntFromByteWithBit(var1[2], 2, 2)));
      var6.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "S18Title1"), this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "S18Title1", "_18_park_1"), DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 2)));
      var6.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings"), this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_0"), DataHandleUtils.getIntFromByteWithBit(var1[8], 6, 2)));
      var6.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings"), this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_1"), DataHandleUtils.getIntFromByteWithBit(var1[8], 4, 2)));
      int var4 = this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings");
      int var2 = this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_2");
      int var5 = var1[8];
      byte var3 = 1;
      var6.add(new SettingUpdateEntity(var4, var2, DataHandleUtils.getIntFromByteWithBit(var5, 3, 1)));
      var6.add(new SettingUpdateEntity(this.getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings"), this.getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_3"), DataHandleUtils.getIntFromByteWithBit(var1[8], 2, 1)));
      this.updateGeneralSettingData(var6);

      label514: {
         Exception var10000;
         label518: {
            boolean var10001;
            SettingPageUiSet.ListBean.ItemListBean var71;
            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_park_1");
            } catch (Exception var68) {
               var10000 = var68;
               var10001 = false;
               break label518;
            }

            byte var70;
            if (var71 != null) {
               label507: {
                  label506: {
                     try {
                        if (DataHandleUtils.getBoolBit7(var1[2])) {
                           break label506;
                        }
                     } catch (Exception var67) {
                        var10000 = var67;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label507;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var66) {
                  var10000 = var66;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_park_2");
            } catch (Exception var65) {
               var10000 = var65;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setProgress(DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 3) - 1);
                  var71.setValue(String.valueOf(var71.getProgress() + 1));
               } catch (Exception var64) {
                  var10000 = var64;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_park_3");
            } catch (Exception var63) {
               var10000 = var63;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setProgress(DataHandleUtils.getIntFromByteWithBit(var1[2], 2, 2));
                  var71.setValue(String.valueOf(var71.getProgress()));
               } catch (Exception var62) {
                  var10000 = var62;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_park_4");
            } catch (Exception var61) {
               var10000 = var61;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setProgress(DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 2));
                  var71.setValue(String.valueOf(var71.getProgress()));
               } catch (Exception var60) {
                  var10000 = var60;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_lock_1");
            } catch (Exception var59) {
               var10000 = var59;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label472: {
                  label471: {
                     try {
                        if (DataHandleUtils.getBoolBit6(var1[3])) {
                           break label471;
                        }
                     } catch (Exception var58) {
                        var10000 = var58;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label472;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var57) {
                  var10000 = var57;
                  var10001 = false;
                  break label518;
               }
            }

            SettingPageUiSet.ListBean.ItemListBean var7;
            try {
               var7 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_lock_2");
            } catch (Exception var56) {
               var10000 = var56;
               var10001 = false;
               break label518;
            }

            if (var7 != null) {
               label458: {
                  label457: {
                     try {
                        var6 = var7.getValueSrnArray();
                        if (DataHandleUtils.getBoolBit5(var1[3])) {
                           break label457;
                        }
                     } catch (Exception var55) {
                        var10000 = var55;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label458;
                  }

                  var70 = 1;
               }

               try {
                  var7.setValue(var6.get(var70));
               } catch (Exception var54) {
                  var10000 = var54;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_lock_3");
            } catch (Exception var53) {
               var10000 = var53;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label444: {
                  label443: {
                     try {
                        if (DataHandleUtils.getBoolBit4(var1[3])) {
                           break label443;
                        }
                     } catch (Exception var52) {
                        var10000 = var52;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label444;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var51) {
                  var10000 = var51;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_lock_4");
            } catch (Exception var50) {
               var10000 = var50;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label430: {
                  label429: {
                     try {
                        if (DataHandleUtils.getBoolBit3(var1[3])) {
                           break label429;
                        }
                     } catch (Exception var49) {
                        var10000 = var49;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label430;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var48) {
                  var10000 = var48;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_lock_5");
            } catch (Exception var47) {
               var10000 = var47;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label416: {
                  label415: {
                     try {
                        if (DataHandleUtils.getBoolBit2(var1[3])) {
                           break label415;
                        }
                     } catch (Exception var46) {
                        var10000 = var46;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label416;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var45) {
                  var10000 = var45;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_lock_6");
            } catch (Exception var44) {
               var10000 = var44;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label402: {
                  label401: {
                     try {
                        if (DataHandleUtils.getBoolBit1(var1[3])) {
                           break label401;
                        }
                     } catch (Exception var43) {
                        var10000 = var43;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label402;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var42) {
                  var10000 = var42;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_lock_7");
            } catch (Exception var41) {
               var10000 = var41;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label388: {
                  label387: {
                     try {
                        if (!DataHandleUtils.getBoolBit0(var1[3])) {
                           break label387;
                        }
                     } catch (Exception var40) {
                        var10000 = var40;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 1;
                     break label388;
                  }

                  var70 = 0;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var39) {
                  var10000 = var39;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_remote_1");
            } catch (Exception var38) {
               var10000 = var38;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label374: {
                  label373: {
                     try {
                        if (DataHandleUtils.getBoolBit7(var1[4])) {
                           break label373;
                        }
                     } catch (Exception var37) {
                        var10000 = var37;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label374;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var36) {
                  var10000 = var36;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_remote_2");
            } catch (Exception var35) {
               var10000 = var35;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label360: {
                  label359: {
                     try {
                        if (DataHandleUtils.getBoolBit6(var1[4])) {
                           break label359;
                        }
                     } catch (Exception var34) {
                        var10000 = var34;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label360;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_remote_3");
            } catch (Exception var32) {
               var10000 = var32;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label346: {
                  label345: {
                     try {
                        if (DataHandleUtils.getBoolBit5(var1[4])) {
                           break label345;
                        }
                     } catch (Exception var31) {
                        var10000 = var31;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label346;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_remote_4");
            } catch (Exception var29) {
               var10000 = var29;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label332: {
                  label331: {
                     try {
                        if (DataHandleUtils.getBoolBit4(var1[4])) {
                           break label331;
                        }
                     } catch (Exception var28) {
                        var10000 = var28;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label332;
                  }

                  var70 = 1;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var27) {
                  var10000 = var27;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_remote_5");
            } catch (Exception var26) {
               var10000 = var26;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setProgress(DataHandleUtils.getIntFromByteWithBit(var1[4], 1, 3));
                  var71.setValue(String.valueOf(var71.getProgress()));
               } catch (Exception var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_light_1");
            } catch (Exception var24) {
               var10000 = var24;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               label311: {
                  label310: {
                     try {
                        if (DataHandleUtils.getBoolBit7(var1[5])) {
                           break label310;
                        }
                     } catch (Exception var23) {
                        var10000 = var23;
                        var10001 = false;
                        break label518;
                     }

                     var70 = 0;
                     break label311;
                  }

                  var70 = var3;
               }

               try {
                  var71.setValue(Integer.valueOf(var70));
               } catch (Exception var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_light_2");
            } catch (Exception var21) {
               var10000 = var21;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setValue(var71.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var1[5], 5, 2)));
               } catch (Exception var20) {
                  var10000 = var20;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_light_3");
            } catch (Exception var19) {
               var10000 = var19;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setValue(var71.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var1[5], 3, 2)));
               } catch (Exception var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_light_4");
            } catch (Exception var17) {
               var10000 = var17;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setProgress(DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 3));
                  var71.setValue(String.valueOf(var71.getProgress()));
               } catch (Exception var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_air_1");
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setProgress(DataHandleUtils.getIntFromByteWithBit(var1[6], 5, 3));
                  var71.setValue(String.valueOf(var71.getProgress()));
               } catch (Exception var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_air_2");
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setProgress(DataHandleUtils.getIntFromByteWithBit(var1[6], 2, 3));
                  var71.setValue(String.valueOf(var71.getProgress()));
               } catch (Exception var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_air_3");
            } catch (Exception var11) {
               var10000 = var11;
               var10001 = false;
               break label518;
            }

            if (var71 != null) {
               try {
                  var71.setValue(var71.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 2)));
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label518;
               }
            }

            try {
               var71 = (SettingPageUiSet.ListBean.ItemListBean)this.mSettingItemsIndex.get("S18_air_4");
            } catch (Exception var9) {
               var10000 = var9;
               var10001 = false;
               break label518;
            }

            if (var71 == null) {
               break label514;
            }

            try {
               var71.setProgress(var1[7]);
               var71.setValue(String.valueOf(var71.getProgress()));
               break label514;
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
            }
         }

         Exception var69 = var10000;
         Log.e("18Msg", Log.getStackTraceString((Throwable)var69));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void signal(int var1) {
      Integer var2 = this.x11D0;
      if (var2 == null || var2 != var1) {
         if (DataHandleUtils.getBoolBit7(var1)) {
            if (this.getVolume() != 0) {
               this.realKeyLongClick2(this.getContext(), 3);
            }
         } else if (this.getVolume() == 0) {
            this.realKeyLongClick2(this.getContext(), 3);
         }

         this.getUiMgr().getParkPageUiSet(this.getContext()).setShowRadar(DataHandleUtils.getBoolBit5(var1));
         GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(var1);
      }

      this.x11D0 = var1;
   }

   private final void tyre() {
      int var1;
      if (DataHandleUtils.getBoolBit7(this.getFrame()[2])) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 6, 1);
      } else {
         var1 = 0;
      }

      List var3 = (List)(new ArrayList());

      for(int var2 = 0; var2 < 5; ++var2) {
         var3.add(new TireUpdateEntity(var2, var1, new String[]{this.getFrame()[var2 + 4] + this.getFrame()[var2 + 9] + "kPa"}));
      }

      GeneralTireData.dataList = var3;
      this.updateTirePressureActivity((Bundle)null);
   }

   private final void verticalAir() {
      this.updateOutDoorTemp(this.getContext(), (double)this.getFrame()[13] * 0.5 - (double)40 + " °C");
      this.getFrame()[13] = 0;
      if (this.first0x31Data) {
         this.mAirData0x31 = this.getFrame();
         this.first0x31Data = false;
      } else if (!this.isAirNoDataChange0x31(this.getFrame())) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.getFrame()[2]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.getFrame()[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]) ^ true;
         if (DataHandleUtils.getBoolBit3(this.getFrame()[3])) {
            GeneralAirData.in_out_auto_cycle = 2;
         } else if (GeneralAirData.in_out_cycle) {
            GeneralAirData.in_out_auto_cycle = 0;
         } else {
            GeneralAirData.in_out_auto_cycle = 1;
         }

         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.getFrame()[3]);
         GeneralAirData.eco = DataHandleUtils.getBoolBit1(this.getFrame()[3]);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.getFrame()[3]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
         GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 6, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 4, 2);
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_left_auto_wind = false;
         int var1 = this.getFrame()[6];
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     switch (var1) {
                        case 12:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_foot = true;
                           break;
                        case 13:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           break;
                        case 14:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_left_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_auto_wind = true;
         }

         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_right_auto_wind = false;
         var1 = this.getFrame()[6];
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     switch (var1) {
                        case 12:
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_foot = true;
                           break;
                        case 13:
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_head = true;
                           break;
                        case 14:
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_right_auto_wind = true;
         }

         GeneralAirData.front_wind_level = this.getFrame()[7];
         var1 = this.getFrame()[8];
         String var3 = "HIGH";
         String var2;
         if (var1 != 0) {
            if (var1 != 254) {
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 + " °C";
               } else {
                  var2 = "HIGH";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.front_left_temperature = var2;
         var1 = this.getFrame()[9];
         if (var1 != 0) {
            if (var1 != 254) {
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 + " °C";
               } else {
                  var2 = "HIGH";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.front_right_temperature = var2;
         GeneralAirData.rear_left_blow_head = false;
         GeneralAirData.rear_left_blow_foot = false;
         GeneralAirData.rear_right_blow_head = false;
         GeneralAirData.rear_right_blow_foot = false;
         var1 = this.getFrame()[10];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_head = true;
                  GeneralAirData.rear_right_blow_foot = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_right_blow_head = true;
            }
         } else {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
         }

         GeneralAirData.rear_wind_level = this.getFrame()[11];
         var1 = this.getFrame()[12];
         if (var1 != 0) {
            if (var1 != 254) {
               var2 = var3;
               if (var1 != 255) {
                  var2 = (double)var1 * 0.5 + " °C";
               }
            } else {
               var2 = "LOW";
            }
         } else {
            var2 = "--";
         }

         GeneralAirData.rear_left_temperature = var2;
         this.updateAirActivity(this.getContext(), 1004);
         this.updateAirActivity(this.getContext(), 1003);
      }
   }

   private final void verticalAirSupplement(int[] var1) {
      if (this.first0x37Data) {
         this.first0x37Data = false;
         this.mAirData0x37 = var1;
      } else if (!this.isAirNoDataChange0x37(var1)) {
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_foot = false;
         int var2 = var1[2];
         if (var2 != 3) {
            if (var2 != 5) {
               if (var2 != 6) {
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = false;
               } else {
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_right_blow_foot = true;
         }

         var2 = var1[3];
         String var3;
         if (var2 != 0) {
            if (var2 != 254) {
               if (var2 != 255) {
                  var3 = (double)var2 * 0.5 + " °C";
               } else {
                  var3 = "HIGH";
               }
            } else {
               var3 = "LOW";
            }
         } else {
            var3 = "--";
         }

         GeneralAirData.rear_right_temperature = var3;
         GeneralAirData.rear_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 6, 2);
         GeneralAirData.rear_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 4, 2);
         GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 2, 2);
         GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 2);
         this.updateAirActivity(this.getContext(), 1004);
         this.updateAirActivity(this.getContext(), 1003);
      }
   }

   private final void wheel(int var1, int var2) {
      label17: {
         Integer var3 = this.x11D6;
         if (var3 != null && var3 == var1) {
            var3 = this.x11D7;
            if (var3 != null && var3 == var2) {
               break label17;
            }
         }

         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var2, (byte)var1, 0, 500, 16);
         this.updateParkUi((Bundle)null, this.getContext());
      }

      this.x11D6 = var1;
      this.x11D7 = var2;
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._18.UiMgr");
      this.setUiMgr((UiMgr)var2);
      GeneralDoorData.isShowHandBrake = true;
      InitUtilsKt.initSettingItemsIndexHashMap(var1, (AbstractUiMgr)this.getUiMgr(), this.mSettingItemsIndex);
      InitUtilsKt.initDrivingItemsIndexHashMap(var1, (AbstractUiMgr)this.getUiMgr(), InitUtilsKt.getMDrivingItemIndex());
   }

   public void auxInInfoChange() {
      byte[] var2 = new byte[12];

      for(int var1 = 0; var1 < 12; ++var1) {
         var2[var1] = 0;
      }

      this.x91 = ArraysKt.plus(new byte[]{12}, var2);
      this.sendSrcInfo();
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      byte[] var5 = new byte[12];

      for(int var4 = 0; var4 < 12; ++var4) {
         var5[var4] = 0;
      }

      this.x91 = ArraysKt.plus(new byte[]{-123}, var5);
      this.sendSrcInfo();
      this.title = var1;
      this.artist = var2;
      this.album = var3;
      this.sendText();
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      byte[] var5 = new byte[12];

      for(int var4 = 0; var4 < 12; ++var4) {
         var5[var4] = 0;
      }

      this.x91 = ArraysKt.plus(new byte[]{10}, var5);
      this.sendSrcInfo();
      this.sendPhoneNumber(0, var1);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      byte var11 = 1;
      byte[] var12 = new byte[12];

      for(var7 = 0; var7 < 12; ++var7) {
         var12[var7] = 0;
      }

      this.x91 = ArraysKt.plus(new byte[]{10}, var12);
      this.sendSrcInfo();
      byte var10 = var11;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 4) {
               if (var3) {
                  var10 = 7;
               } else {
                  var10 = 6;
               }
            } else {
               var10 = 3;
            }
         } else {
            var10 = 2;
         }
      }

      this.sendPhoneNumber(var10, var2);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      if (var1 != null && var2 != null) {
         Exception var10000;
         label263: {
            boolean var10001;
            label262: {
               label261: {
                  label268: {
                     label259: {
                        label258: {
                           label257: {
                              label256: {
                                 label255: {
                                    label254: {
                                       label253: {
                                          label252: {
                                             label251: {
                                                label250: {
                                                   label249: {
                                                      label248: {
                                                         label247: {
                                                            label246: {
                                                               label245: {
                                                                  label244: {
                                                                     try {
                                                                        this.setContext(var1);
                                                                        int[] var7 = this.getByteArrayToIntArray(var2);
                                                                        Intrinsics.checkNotNullExpressionValue(var7, "getByteArrayToIntArray(canbusInfo)");
                                                                        this.setFrame(var7);
                                                                        switch (this.getFrame()[1]) {
                                                                           case 17:
                                                                              break label247;
                                                                           case 19:
                                                                              break label249;
                                                                           case 22:
                                                                              break label251;
                                                                           case 23:
                                                                              break label253;
                                                                           case 31:
                                                                              break label255;
                                                                           case 33:
                                                                              break label257;
                                                                           case 34:
                                                                              break label259;
                                                                           case 38:
                                                                              break label248;
                                                                           case 49:
                                                                              break label250;
                                                                           case 50:
                                                                              break label252;
                                                                           case 55:
                                                                              break label254;
                                                                           case 65:
                                                                              break label256;
                                                                           case 72:
                                                                              break label258;
                                                                           case 98:
                                                                              break label268;
                                                                           case 130:
                                                                              break label261;
                                                                           case 134:
                                                                              break label262;
                                                                           case 154:
                                                                              break;
                                                                           case 166:
                                                                              break label244;
                                                                           case 224:
                                                                              break label245;
                                                                           case 232:
                                                                              break label246;
                                                                           default:
                                                                              return;
                                                                        }
                                                                     } catch (Exception var33) {
                                                                        var10000 = var33;
                                                                        var10001 = false;
                                                                        break label263;
                                                                     }

                                                                     try {
                                                                        this.language();
                                                                        return;
                                                                     } catch (Exception var11) {
                                                                        var10000 = var11;
                                                                        var10001 = false;
                                                                        break label263;
                                                                     }
                                                                  }

                                                                  try {
                                                                     this.amplifier();
                                                                     return;
                                                                  } catch (Exception var10) {
                                                                     var10000 = var10;
                                                                     var10001 = false;
                                                                     break label263;
                                                                  }
                                                               }

                                                               try {
                                                                  this.media();
                                                                  return;
                                                               } catch (Exception var9) {
                                                                  var10000 = var9;
                                                                  var10001 = false;
                                                                  break label263;
                                                               }
                                                            }

                                                            try {
                                                               this.panorama();
                                                               return;
                                                            } catch (Exception var8) {
                                                               var10000 = var8;
                                                               var10001 = false;
                                                               break label263;
                                                            }
                                                         }

                                                         try {
                                                            this.signal(this.getFrame()[2]);
                                                            this.button(this.getFrame()[4]);
                                                            this.door(this.getFrame()[6]);
                                                            this.wheel(this.getFrame()[8], this.getFrame()[9]);
                                                            return;
                                                         } catch (Exception var32) {
                                                            var10000 = var32;
                                                            var10001 = false;
                                                            break label263;
                                                         }
                                                      }

                                                      try {
                                                         this.setCarModelData();
                                                         return;
                                                      } catch (Exception var20) {
                                                         var10000 = var20;
                                                         var10001 = false;
                                                         break label263;
                                                      }
                                                   }

                                                   try {
                                                      this.setCarInfoData0();
                                                      return;
                                                   } catch (Exception var31) {
                                                      var10000 = var31;
                                                      var10001 = false;
                                                      break label263;
                                                   }
                                                }

                                                try {
                                                   this.verticalAir();
                                                   return;
                                                } catch (Exception var19) {
                                                   var10000 = var19;
                                                   var10001 = false;
                                                   break label263;
                                                }
                                             }

                                             try {
                                                this.setCarInfoData1();
                                                return;
                                             } catch (Exception var30) {
                                                var10000 = var30;
                                                var10001 = false;
                                                break label263;
                                             }
                                          }

                                          try {
                                             this.setCarInfoData2();
                                             return;
                                          } catch (Exception var18) {
                                             var10000 = var18;
                                             var10001 = false;
                                             break label263;
                                          }
                                       }

                                       try {
                                          this.set0x17Data();
                                          return;
                                       } catch (Exception var29) {
                                          var10000 = var29;
                                          var10001 = false;
                                          break label263;
                                       }
                                    }

                                    try {
                                       this.verticalAirSupplement(this.getFrame());
                                       return;
                                    } catch (Exception var17) {
                                       var10000 = var17;
                                       var10001 = false;
                                       break label263;
                                    }
                                 }

                                 try {
                                    this.hybrid();
                                    return;
                                 } catch (Exception var28) {
                                    var10000 = var28;
                                    var10001 = false;
                                    break label263;
                                 }
                              }

                              try {
                                 this.radar();
                                 return;
                              } catch (Exception var16) {
                                 var10000 = var16;
                                 var10001 = false;
                                 break label263;
                              }
                           }

                           try {
                              this.panel();
                              return;
                           } catch (Exception var27) {
                              var10000 = var27;
                              var10001 = false;
                              break label263;
                           }
                        }

                        try {
                           this.tyre();
                           return;
                        } catch (Exception var15) {
                           var10000 = var15;
                           var10001 = false;
                           break label263;
                        }
                     }

                     byte var4 = var2[3];

                     int var3;
                     byte var5;
                     int var6;
                     try {
                        var5 = this.lastKnobValue;
                        var6 = Math.abs(var4 - var5);
                        var3 = this.getFrame()[2];
                     } catch (Exception var26) {
                        var10000 = var26;
                        var10001 = false;
                        break label263;
                     }

                     if (var3 != 1) {
                        if (var3 == 2) {
                           if (var4 > var5) {
                              try {
                                 DataHandleUtils.knob(var1, 46, var6);
                              } catch (Exception var25) {
                                 var10000 = var25;
                                 var10001 = false;
                                 break label263;
                              }
                           } else if (var4 < var5) {
                              try {
                                 DataHandleUtils.knob(var1, 45, var6);
                              } catch (Exception var24) {
                                 var10000 = var24;
                                 var10001 = false;
                                 break label263;
                              }
                           }
                        }
                     } else if (var4 > var5) {
                        try {
                           DataHandleUtils.knob(var1, 7, var6);
                        } catch (Exception var23) {
                           var10000 = var23;
                           var10001 = false;
                           break label263;
                        }
                     } else if (var4 < var5) {
                        try {
                           DataHandleUtils.knob(var1, 8, var6);
                        } catch (Exception var22) {
                           var10000 = var22;
                           var10001 = false;
                           break label263;
                        }
                     }

                     try {
                        this.lastKnobValue = var2[3];
                        return;
                     } catch (Exception var21) {
                        var10000 = var21;
                        var10001 = false;
                        break label263;
                     }
                  }

                  try {
                     this.settings(this.getFrame());
                     return;
                  } catch (Exception var14) {
                     var10000 = var14;
                     var10001 = false;
                     break label263;
                  }
               }

               try {
                  this.horizontalAir();
                  return;
               } catch (Exception var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label263;
               }
            }

            try {
               this.set0x86Data();
               return;
            } catch (Exception var12) {
               var10000 = var12;
               var10001 = false;
            }
         }

         Exception var34 = var10000;
         Log.e("CanBusError", var34.toString());
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      byte var3 = (byte)(var1 / 10 + 48);
      byte var4 = (byte)(var1 % 10 + 48);
      byte[] var5 = new byte[10];

      for(var1 = 0; var1 < 10; ++var1) {
         var5[var1] = 0;
      }

      this.x91 = ArraysKt.plus(new byte[]{32, var3, var4}, var5);
      this.sendSrcInfo();
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      if (var10 != 0) {
         var5 = var8;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, (byte)var10, (byte)var2, (byte)var3, (byte)var4, 0});
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

   public final int[] getMAirData0x31() {
      return this.mAirData0x31;
   }

   public final int[] getMAirData0x37() {
      return this.mAirData0x37;
   }

   public final int[] getMAirData0x82() {
      return this.mAirData0x82;
   }

   public final OriginalCarDevicePageUiSet getMOriginalCarDevicePageUiSet() {
      return this.mOriginalCarDevicePageUiSet;
   }

   public final UiMgr getUiMgr() {
      UiMgr var1 = this.uiMgr;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("uiMgr");
         return null;
      }
   }

   public void initCommand(Context var1) {
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initRestValue(var1);
      if (this.getCurrentCanDifferentId() == 28 || this.getCurrentCanDifferentId() == 29 || this.getCurrentCanDifferentId() == 30) {
         (new P360ButtonWindow(var1, new MsgMgr$$ExternalSyntheticLambda0(this))).show();
      }

      this.selectCarMode(this.getCurrentCanDifferentId());
   }

   public final boolean isInit() {
      return this.isInit;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      byte var25;
      if (var1 == 8) {
         var25 = 14;
      } else {
         var25 = 13;
      }

      byte[] var26 = new byte[12];

      for(var1 = 0; var1 < 12; ++var1) {
         var26[var1] = 0;
      }

      this.x91 = ArraysKt.plus(new byte[]{var25}, var26);
      this.sendSrcInfo();
      this.title = var21;
      this.album = var22;
      this.artist = var23;
      this.sendText();
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      if (var2 != null) {
         byte var6;
         label88: {
            label79: {
               switch (var2.hashCode()) {
                  case 64901:
                     if (!var2.equals("AM1")) {
                        return;
                     }
                     break;
                  case 64902:
                     if (!var2.equals("AM2")) {
                        return;
                     }
                     break;
                  case 69706:
                     if (!var2.equals("FM1")) {
                        return;
                     }
                     break label79;
                  case 69707:
                     if (!var2.equals("FM2")) {
                        return;
                     }
                     break label79;
                  case 69708:
                     if (!var2.equals("FM3")) {
                        return;
                     }
                     break label79;
                  default:
                     return;
               }

               var6 = 4;
               break label88;
            }

            var6 = 1;
         }

         byte var12;
         switch (var1) {
            case 1:
               var12 = 49;
               break;
            case 2:
               var12 = 50;
               break;
            case 3:
               var12 = 51;
               break;
            case 4:
               var12 = 52;
               break;
            case 5:
               var12 = 53;
               break;
            case 6:
               var12 = 54;
               break;
            default:
               var12 = 48;
         }

         int var11;
         byte[] var14;
         byte[] var15;
         if (var6 != 1 && var6 != 2 && var6 != 3) {
            if (var6 != 4 && var6 != 5) {
               return;
            }

            var14 = String.valueOf(var3).getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(var14, "this as java.lang.String).getBytes(charset)");
            var11 = 10 - var14.length;
            var15 = new byte[var11];

            for(var5 = 0; var5 < var11; ++var5) {
               var15[var5] = 32;
            }

            var14 = ArraysKt.plus(var15, var14);
         } else {
            Intrinsics.checkNotNull(var3);
            Double var13 = StringsKt.toDoubleOrNull(var3);
            Intrinsics.checkNotNull(var13);
            double var7 = var13;
            double var9 = (double)10;
            var15 = String.valueOf(Math.rint(var7 * var9) / var9).getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(var15, "this as java.lang.String).getBytes(charset)");
            var11 = 10 - var15.length;
            var14 = new byte[var11];

            for(var5 = 0; var5 < var11; ++var5) {
               var14[var5] = 32;
            }

            var14 = ArraysKt.plus(var14, var15);
         }

         this.x91 = ArraysKt.plus(new byte[]{var6, 48, (byte)var12}, var14);
         this.sendSrcInfo();
      }
   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public final void setInit(boolean var1) {
      this.isInit = var1;
   }

   public final void setMAirData0x31(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData0x31 = var1;
   }

   public final void setMAirData0x37(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData0x37 = var1;
   }

   public final void setMAirData0x82(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData0x82 = var1;
   }

   public final void setUiMgr(UiMgr var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.uiMgr = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      byte[] var3 = new byte[13];

      for(int var2 = 0; var2 < 13; ++var2) {
         var3[var2] = 0;
      }

      this.x91 = var3;
      this.sendSrcInfo();
   }

   public final void updateSettings(int var1, int var2, int var3) {
      List var4 = (List)(new ArrayList());
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/car/_18/MsgMgr$CONST;", "", "()V", "AMPLIFIER_RANGE_MAX", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class CONST {
      private CONST() {
      }

      // $FF: synthetic method
      public CONST(DefaultConstructorMarker var1) {
         this();
      }
   }
}
