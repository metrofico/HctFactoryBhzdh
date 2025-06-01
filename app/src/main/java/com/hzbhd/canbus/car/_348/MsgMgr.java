package com.hzbhd.canbus.car._348;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

@Metadata(
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000f\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u001a\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020'H\u0016J\u000e\u0010:\u001a\u0002082\u0006\u0010;\u001a\u00020\u0004J\u0018\u0010<\u001a\u0002082\u0006\u00109\u001a\u00020'2\u0006\u0010=\u001a\u00020\u001eH\u0016Jp\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u000b2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u0004H\u0016J\u0010\u0010L\u001a\u00020-2\u0006\u0010M\u001a\u00020NH\u0002J\u0018\u0010O\u001a\u0002082\u0006\u0010M\u001a\u00020N2\u0006\u0010P\u001a\u00020NH\u0002J8\u0010Q\u001a\u00020N2\u0006\u0010R\u001a\u00020\u00042\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020T2\u0006\u0010V\u001a\u00020N2\u0006\u0010W\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u0004H\u0002J\u0014\u0010Y\u001a\u0004\u0018\u0001062\b\u00109\u001a\u0004\u0018\u00010'H\u0002J\u0010\u0010Z\u001a\u0002082\u0006\u00109\u001a\u00020'H\u0016J\b\u0010[\u001a\u00020\u000bH\u0002J\b\u0010\\\u001a\u00020\u000bH\u0002J\b\u0010]\u001a\u00020\u000bH\u0002J\b\u0010^\u001a\u00020\u000bH\u0002J\u0006\u0010_\u001a\u00020\u0004J¸\u0001\u0010`\u001a\u0002082\u0006\u0010a\u001a\u00020-2\u0006\u0010b\u001a\u00020-2\u0006\u0010c\u001a\u00020\u00042\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020-2\u0006\u0010f\u001a\u00020-2\u0006\u0010g\u001a\u00020-2\u0006\u0010h\u001a\u00020-2\u0006\u0010i\u001a\u00020-2\u0006\u0010j\u001a\u00020-2\u0006\u0010k\u001a\u00020N2\u0006\u0010l\u001a\u00020N2\u0006\u0010m\u001a\u00020N2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020-2\u0006\u0010q\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020o2\u0006\u0010t\u001a\u00020N2\u0006\u0010u\u001a\u00020N2\u0006\u0010v\u001a\u00020N2\u0006\u0010w\u001a\u00020\u000bH\u0016J0\u0010x\u001a\u0002082\u0006\u0010y\u001a\u00020\u00042\u0006\u0010M\u001a\u00020N2\u0006\u0010z\u001a\u00020N2\u0006\u0010{\u001a\u00020N2\u0006\u0010|\u001a\u00020\u0004H\u0016J\n\u0010}\u001a\u0004\u0018\u00010NH\u0002J\u0017\u0010~\u001a\u0002082\r\u0010\u007f\u001a\t\u0012\u0004\u0012\u00020-0\u0080\u0001H\u0002J\t\u0010\u0081\u0001\u001a\u000208H\u0002J\t\u0010\u0082\u0001\u001a\u000208H\u0002J\t\u0010\u0083\u0001\u001a\u000208H\u0002J\t\u0010\u0084\u0001\u001a\u000208H\u0002J\t\u0010\u0085\u0001\u001a\u000208H\u0002J\t\u0010\u0086\u0001\u001a\u000208H\u0002J\t\u0010\u0087\u0001\u001a\u000208H\u0002J\t\u0010\u0088\u0001\u001a\u000208H\u0002J\t\u0010\u0089\u0001\u001a\u000208H\u0002J\t\u0010\u008a\u0001\u001a\u000208H\u0002J\t\u0010\u008b\u0001\u001a\u000208H\u0002J\u0012\u0010\u008c\u0001\u001a\u0002082\u0007\u0010\u008d\u0001\u001a\u00020\u000bH\u0016J\"\u0010\u008e\u0001\u001a\u0002082\u0007\u0010\u008f\u0001\u001a\u00020\u00042\u0007\u0010\u0090\u0001\u001a\u00020\u00042\u0007\u0010\u0091\u0001\u001a\u00020\u0004J\u0093\u0001\u0010\u0092\u0001\u001a\u0002082\u0006\u0010a\u001a\u00020-2\u0006\u0010b\u001a\u00020-2\u0006\u0010c\u001a\u00020\u00042\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020-2\u0006\u0010f\u001a\u00020-2\u0006\u0010g\u001a\u00020-2\u0006\u0010h\u001a\u00020N2\u0006\u0010i\u001a\u00020-2\u0006\u0010j\u001a\u00020-2\u0006\u0010k\u001a\u00020N2\u0006\u0010l\u001a\u00020N2\u0006\u0010m\u001a\u00020N2\u0006\u0010n\u001a\u00020\u00042\u0007\u0010\u0093\u0001\u001a\u00020-2\u0006\u0010r\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u0012R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0095\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_348/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "eachId", "", "i", "getI", "()I", "setI", "(I)V", "isDoorChange", "", "()Z", "m0x24FrontRadarData", "", "getM0x24FrontRadarData", "()[I", "setM0x24FrontRadarData", "([I)V", "m0x25FrontRadarData", "getM0x25FrontRadarData", "setM0x25FrontRadarData", "m0x26FrontRadarData", "getM0x26FrontRadarData", "setM0x26FrontRadarData", "mAirData", "getMAirData", "setMAirData", "mBackStatus", "mCanBusInfoByte", "", "getMCanBusInfoByte", "()[B", "setMCanBusInfoByte", "([B)V", "mCanBusInfoInt", "getMCanBusInfoInt", "setMCanBusInfoInt", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mFreqHi", "", "mFreqLo", "mFrontStatus", "mHandBrake", "mLeftFrontStatus", "mLeftRearStatus", "mRightFrontStatus", "mRightRearStatus", "uiMgr", "Lcom/hzbhd/canbus/car/_348/UiMgr;", "afterServiceNormalSetting", "", "context", "buttonKey", "whatKey", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "getAllBandTypeData", "currBand", "", "getFreqByteHiLo", "mCurrentFreq", "getTemperature", "temperatureData", "stepping", "", "temperatureBase", "unit", "LO", "HI", "getUiMgr", "initCommand", "is0x24RadarDataNoChange", "is0x25RadarDataNoChange", "is0x26RadarDataNoChange", "isAirDataNoChange", "isMute", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currentFreq", "psName", "isStereo", "resolveOutDoorTem", "sendMediaSourceData", "list", "", "set0x26RadarInfo", "setAirdata0x23", "setDoorData0x28", "setFrontRadar0x25", "setOutdoortemp0x36", "setPanelKeyInfo0x22", "setRearRadar0x24", "setSettingState0x40", "setTrackData0x30", "setVersionInfo0x7F", "setWheelKeyInfo0x21", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSetting", "leftPos", "rightPos", "selectPos", "videoInfoChange", "playMode", "duation", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private int eachId;
   private int i;
   private int[] m0x24FrontRadarData = new int[0];
   private int[] m0x25FrontRadarData = new int[0];
   private int[] m0x26FrontRadarData = new int[0];
   private int[] mAirData = new int[0];
   private boolean mBackStatus;
   public byte[] mCanBusInfoByte;
   public int[] mCanBusInfoInt;
   private Context mContext;
   private byte mFreqHi;
   private byte mFreqLo;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private UiMgr uiMgr;

   private final byte getAllBandTypeData(String var1) {
      int var3 = var1.hashCode();
      byte var2;
      if (var3 != 2092) {
         switch (var3) {
            case 69706:
               if (var1.equals("FM1")) {
                  var2 = 1;
                  return var2;
               }
               break;
            case 69707:
               if (var1.equals("FM2")) {
                  var2 = 2;
                  return var2;
               }
         }
      } else if (var1.equals("AM")) {
         var2 = 3;
         return var2;
      }

      var2 = 0;
      return var2;
   }

   private final void getFreqByteHiLo(String var1, String var2) {
      int var3 = var1.hashCode();
      if (var3 != 2092) {
         switch (var3) {
            case 69706:
               if (!var1.equals("FM1")) {
                  return;
               }
               break;
            case 69707:
               if (!var1.equals("FM2")) {
                  return;
               }
               break;
            default:
               return;
         }

         var3 = (int)(Double.parseDouble(var2) * (double)100);
         this.mFreqHi = (byte)(var3 >> 8);
         this.mFreqLo = (byte)(var3 & 255);
      } else if (var1.equals("AM")) {
         this.mFreqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.mFreqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private final String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else if (var1 == 254) {
         return "无效";
      } else {
         CharSequence var11 = (CharSequence)var6;
         var7 = var11.length() - 1;
         var8 = 0;
         boolean var9 = false;

         while(var8 <= var7) {
            int var10;
            if (!var9) {
               var10 = var8;
            } else {
               var10 = var7;
            }

            boolean var12;
            if (Intrinsics.compare(var11.charAt(var10), 32) <= 0) {
               var12 = true;
            } else {
               var12 = false;
            }

            if (!var9) {
               if (!var12) {
                  var9 = true;
               } else {
                  ++var8;
               }
            } else {
               if (!var12) {
                  break;
               }

               --var7;
            }
         }

         if (Intrinsics.areEqual((Object)var11.subSequence(var8, var7 + 1).toString(), (Object)"C")) {
            var6 = (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext);
         } else {
            var6 = (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
         }

         return var6;
      }
   }

   private final UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._348.UiMgr");
         this.uiMgr = (UiMgr)var2;
      }

      return this.uiMgr;
   }

   private final boolean is0x24RadarDataNoChange() {
      if (Arrays.equals(this.m0x24FrontRadarData, this.getMCanBusInfoInt())) {
         return true;
      } else {
         this.m0x24FrontRadarData = this.getMCanBusInfoInt();
         return false;
      }
   }

   private final boolean is0x25RadarDataNoChange() {
      if (Arrays.equals(this.m0x25FrontRadarData, this.getMCanBusInfoInt())) {
         return true;
      } else {
         this.m0x25FrontRadarData = this.getMCanBusInfoInt();
         return false;
      }
   }

   private final boolean is0x26RadarDataNoChange() {
      if (Arrays.equals(this.m0x26FrontRadarData, this.getMCanBusInfoInt())) {
         return true;
      } else {
         this.m0x26FrontRadarData = this.getMCanBusInfoInt();
         return false;
      }
   }

   private final boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.getMCanBusInfoInt())) {
         return true;
      } else {
         this.mAirData = this.getMCanBusInfoInt();
         return false;
      }
   }

   private final boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mHandBrake = GeneralDoorData.isHandBrakeUp;
         return true;
      }
   }

   private final String resolveOutDoorTem() {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getMCanBusInfoInt()[2], 0, 7);
      int var1 = var2;
      if (DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[2])) {
         var1 = var2 * -1;
      }

      String var3;
      if (var1 <= 120 && var1 >= -40) {
         var3 = var1 + this.getTempUnitC(this.mContext);
      } else {
         this.updateOutDoorTemp(this.mContext, " ");
         var3 = null;
      }

      return var3;
   }

   private final void sendMediaSourceData(List var1) {
      byte[] var2 = CollectionsKt.toByteArray((Collection)var1);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 117}, var2));
   }

   private final void set0x26RadarInfo() {
      if (!this.is0x26RadarDataNoChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationDataType2(3, this.getMCanBusInfoInt()[2], 4, this.getMCanBusInfoInt()[2], 4, this.getMCanBusInfoInt()[3], 3, this.getMCanBusInfoInt()[3]);
         RadarInfoUtil.setRearRadarLocationDataType2(3, this.getMCanBusInfoInt()[4], 4, this.getMCanBusInfoInt()[4], 4, this.getMCanBusInfoInt()[5], 3, this.getMCanBusInfoInt()[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private final void setAirdata0x23() {
      if (!this.isAirDataNoChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getMCanBusInfoInt()[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.getMCanBusInfoInt()[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit4(this.getMCanBusInfoInt()[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getMCanBusInfoInt()[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.getMCanBusInfoInt()[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.getMCanBusInfoInt()[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.getMCanBusInfoInt()[2]);
         int var1 = this.getMCanBusInfoInt()[3];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        GeneralAirData.front_left_blow_foot = false;
                        GeneralAirData.front_right_blow_foot = false;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_head = true;
         }

         GeneralAirData.front_wind_level = this.getMCanBusInfoInt()[4];
         GeneralAirData.front_left_temperature = this.getTemperature(this.getMCanBusInfoInt()[5], 1.0, 0.0, "C", 0, 255);
         GeneralAirData.front_right_temperature = this.getTemperature(this.getMCanBusInfoInt()[6], 1.0, 0.0, "C", 0, 255);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private final void setDoorData0x28() {
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getMCanBusInfoInt()[2]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private final void setFrontRadar0x25() {
      if (!this.is0x25RadarDataNoChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, this.getMCanBusInfoInt()[2], 3);
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, this.getMCanBusInfoInt()[3], 4);
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, this.getMCanBusInfoInt()[4], 4);
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, this.getMCanBusInfoInt()[5], 3);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private final void setOutdoortemp0x36() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private final void setPanelKeyInfo0x22() {
      switch (this.getMCanBusInfoInt()[2]) {
         case 0:
            this.buttonKey(0);
            break;
         case 1:
            this.buttonKey(1);
            break;
         case 2:
            this.buttonKey(220);
            break;
         case 3:
            this.buttonKey(3);
            break;
         case 4:
            this.buttonKey(188);
            break;
         case 5:
            this.buttonKey(182);
            break;
         case 6:
            this.buttonKey(151);
            break;
         case 7:
            this.buttonKey(50);
            break;
         case 8:
            this.buttonKey(76);
            break;
         case 9:
            this.buttonKey(139);
            break;
         case 10:
            this.buttonKey(49);
            break;
         case 11:
            this.buttonKey(47);
            break;
         case 12:
            this.buttonKey(48);
            break;
         case 13:
            this.buttonKey(4);
            break;
         case 14:
            this.buttonKey(8);
            break;
         case 15:
            this.buttonKey(7);
            break;
         case 16:
            this.buttonKey(58);
            break;
         case 17:
            this.buttonKey(21);
            break;
         case 18:
            this.buttonKey(20);
            break;
         case 19:
            this.buttonKey(128);
            break;
         case 20:
            this.buttonKey(182);
            break;
         case 21:
            this.buttonKey(12);
            break;
         case 22:
            this.buttonKey(2);
            break;
         case 23:
            this.buttonKey(75);
         case 24:
         case 29:
         default:
            break;
         case 25:
            this.buttonKey(4);
            break;
         case 26:
            this.buttonKey(70);
            break;
         case 27:
            this.buttonKey(30);
            break;
         case 28:
            this.buttonKey(470);
            break;
         case 30:
            this.buttonKey(47);
            break;
         case 31:
            this.buttonKey(48);
      }

   }

   private final void setRearRadar0x24() {
      if (!this.is0x24RadarDataNoChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, this.getMCanBusInfoInt()[2], 3);
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, this.getMCanBusInfoInt()[3], 4);
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, this.getMCanBusInfoInt()[4], 4);
         RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, this.getMCanBusInfoInt()[5], 3);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private final void setSettingState0x40() {
      List var2 = (List)(new ArrayList());
      UiMgr var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      int var1 = var3.getSettingLeftIndexes(this.mContext, "car_light_set");
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var2.add(new SettingUpdateEntity(var1, var3.getSettingRightIndex(this.mContext, "car_light_set", "ceiling_light_delay"), DataHandleUtils.getIntFromByteWithBit(this.getMCanBusInfoInt()[2], 6, 2)));
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var1 = var3.getSettingLeftIndexes(this.mContext, "car_light_set");
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var2.add(new SettingUpdateEntity(var1, var3.getSettingRightIndex(this.mContext, "car_light_set", "power_saving_time"), DataHandleUtils.getIntFromByteWithBit(this.getMCanBusInfoInt()[2], 4, 2)));
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var1 = var3.getSettingLeftIndexes(this.mContext, "car_lock_set");
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var2.add(new SettingUpdateEntity(var1, var3.getSettingRightIndex(this.mContext, "car_lock_set", "speed_lock"), DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[3])));
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var1 = var3.getSettingLeftIndexes(this.mContext, "car_lock_set");
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var2.add(new SettingUpdateEntity(var1, var3.getSettingRightIndex(this.mContext, "car_lock_set", "automatic_relock"), DataHandleUtils.getBoolBit6(this.getMCanBusInfoInt()[3])));
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var1 = var3.getSettingLeftIndexes(this.mContext, "car_lock_set");
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var2.add(new SettingUpdateEntity(var1, var3.getSettingRightIndex(this.mContext, "car_lock_set", "remote_lock_feedback"), DataHandleUtils.getBoolBit5(this.getMCanBusInfoInt()[3])));
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var1 = var3.getSettingLeftIndexes(this.mContext, "car_lock_set");
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var2.add(new SettingUpdateEntity(var1, var3.getSettingRightIndex(this.mContext, "car_lock_set", "_282_07_0_6"), DataHandleUtils.getBoolBit4(this.getMCanBusInfoInt()[3])));
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var1 = var3.getSettingLeftIndexes(this.mContext, "car_lock_set");
      var3 = this.getUiMgr(this.mContext);
      Intrinsics.checkNotNull(var3);
      var2.add(new SettingUpdateEntity(var1, var3.getSettingRightIndex(this.mContext, "car_lock_set", "_1193_setting_1_8"), DataHandleUtils.getBoolBit3(this.getMCanBusInfoInt()[3])));
      this.updateGeneralSettingData(var2);
      this.updateActivity((Bundle)null);
   }

   private final void setTrackData0x30() {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.getMCanBusInfoByte()[3], this.getMCanBusInfoByte()[2], 7784, 13784, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final void setVersionInfo0x7F() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.getMCanBusInfoByte()));
   }

   private final void setWheelKeyInfo0x21() {
      switch (this.getMCanBusInfoInt()[2]) {
         case 0:
            this.buttonKey(0);
            break;
         case 1:
            this.buttonKey(7);
            break;
         case 2:
            this.buttonKey(8);
            break;
         case 3:
            this.buttonKey(21);
            break;
         case 4:
            this.buttonKey(20);
         case 5:
         case 13:
         case 14:
         default:
            break;
         case 6:
            this.buttonKey(3);
            break;
         case 7:
            this.buttonKey(2);
            break;
         case 8:
            this.buttonKey(201);
            break;
         case 9:
            this.buttonKey(188);
            break;
         case 10:
            this.buttonKey(15);
            break;
         case 11:
            this.buttonKey(207);
            break;
         case 12:
            this.buttonKey(206);
            break;
         case 15:
            this.buttonKey(464);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public final void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.getMCanBusInfoInt()[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      this.setMCanBusInfoByte(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.setMCanBusInfoInt(var4);
      int var3 = this.getMCanBusInfoInt()[1];
      if (var3 != 40) {
         if (var3 != 48) {
            if (var3 != 54) {
               if (var3 != 64) {
                  if (var3 != 127) {
                     switch (var3) {
                        case 33:
                           if (this.eachId == 2) {
                              return;
                           }

                           this.setWheelKeyInfo0x21();
                           break;
                        case 34:
                           this.setPanelKeyInfo0x22();
                           break;
                        case 35:
                           if (this.eachId == 2) {
                              return;
                           }

                           this.setAirdata0x23();
                           break;
                        case 36:
                           this.setRearRadar0x24();
                           break;
                        case 37:
                           if (this.eachId != 3) {
                              return;
                           }

                           this.setFrontRadar0x25();
                           break;
                        case 38:
                           this.set0x26RadarInfo();
                     }
                  } else {
                     this.setVersionInfo0x7F();
                  }
               } else {
                  var3 = this.eachId;
                  if (var3 != 1 && var3 != 3) {
                     return;
                  }

                  this.setSettingState0x40();
               }
            } else {
               var3 = this.eachId;
               if (var3 != 1 && var3 != 3) {
                  return;
               }

               this.setOutdoortemp0x36();
            }
         } else {
            this.setTrackData0x30();
         }
      } else {
         this.setDoorData0x28();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, 118, (byte)var8, (byte)var6, (byte)var7});
   }

   public final int getI() {
      return this.i;
   }

   public final int[] getM0x24FrontRadarData() {
      return this.m0x24FrontRadarData;
   }

   public final int[] getM0x25FrontRadarData() {
      return this.m0x25FrontRadarData;
   }

   public final int[] getM0x26FrontRadarData() {
      return this.m0x26FrontRadarData;
   }

   public final int[] getMAirData() {
      return this.mAirData;
   }

   public final byte[] getMCanBusInfoByte() {
      byte[] var1 = this.mCanBusInfoByte;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
         return null;
      }
   }

   public final int[] getMCanBusInfoInt() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         return null;
      }
   }

   public final Context getMContext() {
      return this.mContext;
   }

   public void initCommand(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      int var2 = this.getCurrentEachCanId();
      byte var3;
      if (var2 != 4) {
         if (var2 != 5) {
            if (var2 != 6) {
               return;
            }

            var3 = 3;
         } else {
            var3 = 2;
         }
      } else {
         var3 = 1;
      }

      BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(var3, (Continuation)null) {
         final int $d0;
         int label;

         {
            this.$d0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            return (Continuation)(new <anonymous constructor>(this.$d0, var2));
         }

         public final Object invoke(CoroutineScope var1, Continuation var2) {
            return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            Continuation var4;
            <undefinedtype> var5;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ResultKt.throwOnFailure(var1);
               var5 = this;
            } else {
               ResultKt.throwOnFailure(var1);
               var5 = this;
               var4 = (Continuation)this;
               this.label = 1;
               if (DelayKt.delay(2000L, var4) == var3) {
                  return var3;
               }
            }

            do {
               CanbusMsgSender.sendMsg(new byte[]{22, -18, (byte)var5.$d0});
               var4 = (Continuation)var5;
               var5.label = 1;
            } while(DelayKt.delay(2000L, var4) != var3);

            return var3;
         }
      }), 3, (Object)null);
   }

   public final int isMute() {
      byte var1;
      if (this.getVolume() == 0) {
         var1 = 2;
      } else {
         var1 = 1;
      }

      return var1;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      Intrinsics.checkNotNullParameter(var11, "currentHourStr");
      Intrinsics.checkNotNullParameter(var12, "currentMinuteStr");
      Intrinsics.checkNotNullParameter(var13, "currentSecondStr");
      Intrinsics.checkNotNullParameter(var21, "songTitle");
      Intrinsics.checkNotNullParameter(var22, "songAlbum");
      Intrinsics.checkNotNullParameter(var23, "songArtist");
      List var26 = (List)(new ArrayList());
      byte var25;
      if (var1 == 9) {
         var25 = 4;
      } else {
         var25 = 3;
      }

      var26.add(var25);
      var26.add((byte)0);
      var26.add((byte)0);
      var26.add((byte)0);
      var26.add((byte)0);
      var26.add((byte)0);
      var26.add((byte)0);
      var26.add((byte)this.isMute());
      this.sendMediaSourceData(var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var6;
      List var9;
      label65: {
         label64: {
            Intrinsics.checkNotNullParameter(var2, "currBand");
            Intrinsics.checkNotNullParameter(var3, "currentFreq");
            Intrinsics.checkNotNullParameter(var4, "psName");
            var9 = (List)(new ArrayList());
            var9.add((byte)1);
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
                  break label64;
               case 69707:
                  if (!var2.equals("FM2")) {
                     return;
                  }

                  var6 = 2;
                  break label65;
               case 69708:
                  if (!var2.equals("FM3")) {
                     return;
                  }
                  break label64;
               default:
                  return;
            }

            var6 = 3;
            break label65;
         }

         var6 = 1;
      }

      label47: {
         label46: {
            var9.add(var6);
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
                  break label46;
               case 69707:
                  if (!var2.equals("FM2")) {
                     return;
                  }
                  break label46;
               case 69708:
                  if (!var2.equals("FM3")) {
                     return;
                  }
                  break label46;
               default:
                  return;
            }

            var1 = Integer.parseInt(var3);
            break label47;
         }

         CharSequence var8 = (CharSequence)var3;
         String var7 = var3.substring(0, StringsKt.indexOf$default(var8, ".", 0, false, 6, (Object)null));
         Intrinsics.checkNotNullExpressionValue(var7, "this as java.lang.String…ing(startIndex, endIndex)");
         var1 = Integer.parseInt(var7);
         var2 = var3.substring(StringsKt.indexOf$default(var8, ".", 0, false, 6, (Object)null) + 1, StringsKt.getLastIndex(var8));
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
         var1 = var1 * 10 + Integer.parseInt(var2);
      }

      Log.i("lyn", String.valueOf(var1));
      var9.add((byte)DataHandleUtils.getMsb(var1));
      var9.add((byte)DataHandleUtils.getLsb(var1));
      var9.add((byte)0);
      var9.add((byte)0);
      var9.add((byte)0);
      var9.add((byte)this.isMute());
      this.sendMediaSourceData(var9);
   }

   public final void setI(int var1) {
      this.i = var1;
   }

   public final void setM0x24FrontRadarData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.m0x24FrontRadarData = var1;
   }

   public final void setM0x25FrontRadarData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.m0x25FrontRadarData = var1;
   }

   public final void setM0x26FrontRadarData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.m0x26FrontRadarData = var1;
   }

   public final void setMAirData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData = var1;
   }

   public final void setMCanBusInfoByte(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mCanBusInfoByte = var1;
   }

   public final void setMCanBusInfoInt(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mCanBusInfoInt = var1;
   }

   public final void setMContext(Context var1) {
      this.mContext = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 117, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public final void updateSetting(int var1, int var2, int var3) {
      List var4 = (List)(new ArrayList());
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      Intrinsics.checkNotNullParameter(var8, "currentAllMinuteStr");
      Intrinsics.checkNotNullParameter(var11, "currentHourStr");
      Intrinsics.checkNotNullParameter(var12, "currentMinuteStr");
      Intrinsics.checkNotNullParameter(var13, "currentSecondStr");
      List var19 = (List)(new ArrayList());
      byte var18;
      if (var1 == 9) {
         var18 = 4;
      } else {
         var18 = 3;
      }

      var19.add(var18);
      var19.add((byte)0);
      var19.add((byte)0);
      var19.add((byte)0);
      var19.add((byte)0);
      var19.add((byte)0);
      var19.add((byte)0);
      var19.add((byte)this.isMute());
      this.sendMediaSourceData(var19);
   }
}
