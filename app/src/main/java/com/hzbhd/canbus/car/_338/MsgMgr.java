package com.hzbhd.canbus.car._338;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u001c\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\"\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\"\u0010\u0014\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\"\u0010\u0015\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\"\u0010\u0016\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0016\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010J\u0018\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0012H\u0016Jp\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\u0004H\u0016J\u0010\u00101\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u00102\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0002J \u00106\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0004H\u0002JÄ\u0001\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020;2\u0006\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020;2\u0006\u0010B\u001a\u00020;2\u0006\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020;2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010F2\b\u0010H\u001a\u0004\u0018\u00010F2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020;2\u0006\u0010L\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00122\u0006\u0010N\u001a\u00020J2\b\u0010O\u001a\u0004\u0018\u00010F2\b\u0010P\u001a\u0004\u0018\u00010F2\b\u0010Q\u001a\u0004\u0018\u00010F2\u0006\u0010R\u001a\u00020\u0012H\u0016J\u0010\u0010S\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010T\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J6\u0010U\u001a\u00020\f2\u0006\u0010V\u001a\u00020\u00042\b\u0010W\u001a\u0004\u0018\u00010F2\b\u0010X\u001a\u0004\u0018\u00010F2\b\u0010Y\u001a\u0004\u0018\u00010F2\u0006\u0010Z\u001a\u00020\u0004H\u0016J\u0010\u0010[\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\\\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010]\u001a\u00020\f2\u0006\u0010^\u001a\u00020\u0004H\u0002J\u0012\u0010_\u001a\u00020\f2\b\u00108\u001a\u0004\u0018\u00010\u0010H\u0002J8\u0010`\u001a\u00020\f2\u0006\u0010a\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u00042\u0006\u0010b\u001a\u00020\u00042\u0006\u0010c\u001a\u00020\u00042\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020\u0004H\u0002J\u0010\u0010f\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010g\u001a\u00020\fH\u0002J \u0010h\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006j"},
   d2 = {"Lcom/hzbhd/canbus/car/_338/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "carId", "", "context", "Landroid/content/Context;", "mCanBusInfoInt", "", "mVolume", "staged", "afterServiceNormalSetting", "", "airInfo", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "byteMerger", "bt1", "bt2", "canbusInfoChange", "canbusInfo", "culTrackAngleLeft", "track", "", "culTrackAngleRight", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "frontRadarInfo", "generalInfo", "getMsbLsbResult", "MSB", "LSB", "knob", "whatKey", "number", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panelButtonInfo", "panoramicInfo", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "realKeyInfo", "rearRadarInfo", "sendPhoneState", "data1", "sendPhoneText", "sendTimeInfo", "data0", "data2", "data3", "data4", "data5", "steeringWheelAngleInfo", "vehicleSettingInfo", "volumeKnob", "keyValue", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private int carId;
   private Context context;
   private int[] mCanBusInfoInt = new int[0];
   private int mVolume;
   private int staged;

   // $FF: synthetic method
   public static void $r8$lambda$T1tlyy7J7YcTT_qxzejlCSZr50A(byte[] var0, byte[] var1) {
      musicInfoChange$lambda_0(var0, var1);
   }

   private final void airInfo(Context var1) {
      GeneralAirData.econ = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralAirData.in_out_cycle = var3;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      int var2 = this.mCanBusInfoInt[3];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 4) {
                  if (var2 == 5) {
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_left_blow_foot = false;
                     GeneralAirData.front_defog = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_defog = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_defog = false;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_defog = false;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_defog = false;
      }

      var2 = this.mCanBusInfoInt[4];
      if (var2 == 0) {
         GeneralAirData.front_wind_level = var2;
         GeneralAirData.power = false;
      } else {
         GeneralAirData.front_wind_level = var2;
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         var2 = this.mCanBusInfoInt[5];
         if (var2 == 241) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var2 == 242) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = (double)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5) / 2.0 + (double)18 + "°C";
         }
      } else {
         var2 = this.mCanBusInfoInt[5];
         if (var2 == 241) {
            GeneralAirData.front_left_temperature = "LO";
         } else if (var2 == 242) {
            GeneralAirData.front_left_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = "Level " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5);
         }
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
         var2 = this.mCanBusInfoInt[6];
         if (var2 == 241) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var2 == 242) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = (double)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 5) / 2.0 + (double)18 + "°C";
         }
      } else {
         var2 = this.mCanBusInfoInt[6];
         if (var2 == 241) {
            GeneralAirData.front_right_temperature = "LO";
         } else if (var2 == 242) {
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_right_temperature = "Level " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 5);
         }
      }

      this.updateAirActivity(var1, 1001);
   }

   private final int culTrackAngleLeft(short var1) {
      return -DataHandleUtils.rangeNumber((int)((float)var1 / 21.6F), 0, 25);
   }

   private final int culTrackAngleRight(short var1) {
      return DataHandleUtils.rangeNumber((int)((float)var1 / 21.6F), 0, 25);
   }

   private final void frontRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private final void generalInfo(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != this.staged) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var2);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         this.updateDoorView(var1);
      }

      int[] var3 = this.mCanBusInfoInt;
      this.staged = var3[2];
      var2 = var3[3];
      if (var2 == 255) {
         this.updateOutDoorTemp(var1, "--°C");
      } else if (var2 == 254) {
         this.updateOutDoorTemp(var1, " ");
      } else {
         this.updateOutDoorTemp(var1, this.mCanBusInfoInt[3] - 40 + "°C");
      }

   }

   private final int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private final void knob(Context var1, int var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         this.realKeyClick(var1, var2);
      }

   }

   private static final void musicInfoChange$lambda_0(byte[] var0, byte[] var1) {
      Intrinsics.checkNotNullParameter(var0, "$title");
      Intrinsics.checkNotNullParameter(var1, "$artist");
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 112, 16}, var0));
      Thread.sleep(500L);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 113, 16}, var1));
   }

   private final void panelButtonInfo(Context var1) {
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[2];
      short var2 = 182;
      switch (var3) {
         case 0:
            this.realKeyLongClick1(var1, 0, var4[3]);
            break;
         case 1:
            this.realKeyLongClick1(var1, 134, var4[3]);
            break;
         case 2:
            this.volumeKnob(var1, 2, var4[3]);
            break;
         case 3:
            this.volumeKnob(var1, 3, var4[3]);
            break;
         case 4:
            this.realKeyLongClick1(var1, 3, var4[3]);
            break;
         case 5:
            this.realKeyLongClick1(var1, 63, var4[3]);
            break;
         case 6:
            this.realKeyLongClick1(var1, 21, var4[3]);
            break;
         case 7:
            this.realKeyLongClick1(var1, 20, var4[3]);
            break;
         case 8:
            this.realKeyLongClick1(var1, 49, var4[3]);
            break;
         case 9:
            this.knob(var1, 45, var4[3]);
            break;
         case 10:
            this.knob(var1, 46, var4[3]);
            break;
         case 11:
            this.realKeyLongClick1(var1, 33, var4[3]);
            break;
         case 12:
            this.realKeyLongClick1(var1, 34, var4[3]);
            break;
         case 13:
            this.realKeyLongClick1(var1, 35, var4[3]);
            break;
         case 14:
            this.realKeyLongClick1(var1, 36, var4[3]);
            break;
         case 15:
            this.realKeyLongClick1(var1, 37, var4[3]);
            break;
         case 16:
            this.realKeyLongClick1(var1, 38, var4[3]);
            break;
         case 17:
            this.realKeyLongClick1(var1, 91, var4[3]);
            break;
         case 18:
            this.realKeyLongClick1(var1, 90, var4[3]);
            break;
         case 19:
            this.realKeyLongClick1(var1, 62, var4[3]);
            break;
         case 20:
            this.realKeyLongClick1(var1, 139, var4[3]);
         case 21:
         default:
            break;
         case 22:
            this.realKeyLongClick1(var1, 27, var4[3]);
            break;
         case 23:
            this.realKeyLongClick1(var1, 68, var4[3]);
            break;
         case 24:
            this.realKeyLongClick1(var1, 130, var4[3]);
            break;
         case 25:
            this.realKeyLongClick1(var1, 73, var4[3]);
            break;
         case 26:
            if (this.carId == 2) {
               var2 = 188;
            }

            this.realKeyLongClick1(var1, var2, var4[3]);
            break;
         case 27:
            this.realKeyLongClick1(var1, 30, var4[3]);
            break;
         case 28:
            this.realKeyLongClick1(var1, 182, var4[3]);
            break;
         case 29:
            this.realKeyLongClick1(var1, 14, var4[3]);
            break;
         case 30:
            this.realKeyLongClick1(var1, 15, var4[3]);
            break;
         case 31:
            this.realKeyLongClick1(var1, 50, var4[3]);
            break;
         case 32:
            this.realKeyLongClick1(var1, 52, var4[3]);
            break;
         case 33:
            this.realKeyLongClick1(var1, 47, var4[3]);
            break;
         case 34:
            this.realKeyLongClick1(var1, 59, var4[3]);
      }

   }

   private final void panoramicInfo(Context var1) {
      boolean var2;
      if (this.mCanBusInfoInt[2] != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.forceReverse(var1, var2);
   }

   private final void realKeyInfo(Context var1) {
      int[] var2 = this.mCanBusInfoInt;
      switch (var2[2]) {
         case 0:
            this.realKeyLongClick1(var1, 0, var2[3]);
            break;
         case 1:
            this.realKeyLongClick1(var1, 7, var2[3]);
            break;
         case 2:
            this.realKeyLongClick1(var1, 8, var2[3]);
            break;
         case 3:
            this.realKeyLongClick1(var1, 21, var2[3]);
            break;
         case 4:
            this.realKeyLongClick1(var1, 20, var2[3]);
         case 5:
         case 8:
         default:
            break;
         case 6:
            this.realKeyLongClick1(var1, 3, var2[3]);
            break;
         case 7:
            this.realKeyLongClick1(var1, 2, var2[3]);
            break;
         case 9:
            this.realKeyLongClick1(var1, 14, var2[3]);
            break;
         case 10:
            this.realKeyLongClick1(var1, 15, var2[3]);
      }

   }

   private final void rearRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private final void sendPhoneState(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte)var1});
   }

   private final void sendPhoneText(byte[] var1) {
      int var2 = this.carId;
      if (var2 == 4) {
         Intrinsics.checkNotNull(var1);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 4, 2}, var1));
      } else if (var2 == 5) {
         Intrinsics.checkNotNull(var1);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 4, 16}, var1));
      }

   }

   private final void sendTimeInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      var4 = DataHandleUtils.getIntFromByteWithBit(var4, 1, 6);
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   private final void steeringWheelAngleInfo(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      short var2 = (short)(this.getMsbLsbResult((byte)(var3[2] & 127), var3[3]) / 10);
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         GeneralParkData.trackAngle = this.culTrackAngleRight(var2);
      } else {
         GeneralParkData.trackAngle = this.culTrackAngleLeft(var2);
      }

      this.updateParkUi((Bundle)null, var1);
   }

   private final void vehicleSettingInfo() {
      List var1 = (List)(new ArrayList());
      var1.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private final void volumeKnob(Context var1, int var2, int var3) {
      byte var5 = 0;
      if (var2 == 3 && this.mVolume > 0) {
         for(int var4 = 0; var4 < var3; ++var4) {
            this.realKeyClick(var1, 8);
         }
      }

      if (var2 == 2 && this.mVolume < 40) {
         for(var2 = var5; var2 < var3; ++var2) {
            this.realKeyClick(var1, 7);
         }
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      this.carId = this.getCurrentCanDifferentId();
      GeneralDoorData.isShowMasterDriverSeatBelt = true;
      GeneralDoorData.isShowCoPilotSeatBelt = true;
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneState(5);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneState(1);
      this.sendPhoneText(var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneState(2);
      this.sendPhoneText(var1);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.sendPhoneState(4);
   }

   public final byte[] byteMerger(byte[] var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "bt1");
      Intrinsics.checkNotNullParameter(var2, "bt2");
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      this.context = var1;
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 40) {
         if (var3 != 48) {
            if (var3 != 64) {
               if (var3 != 82) {
                  switch (var3) {
                     case 32:
                        this.realKeyInfo(var1);
                        break;
                     case 33:
                        this.airInfo(var1);
                        break;
                     case 34:
                        this.rearRadarInfo(var1);
                        break;
                     case 35:
                        this.frontRadarInfo(var1);
                        break;
                     case 36:
                        this.panelButtonInfo(var1);
                  }
               } else {
                  this.vehicleSettingInfo();
               }
            } else {
               this.panoramicInfo(var1);
            }
         } else {
            this.steeringWheelAngleInfo(var1);
         }
      } else {
         this.generalInfo(var1);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      this.mVolume = var1;
      if (var2) {
         var1 |= 128;
      } else {
         var1 &= 127;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var1});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      this.sendTimeInfo(var2, var3, var4, var5, var6, var7);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      var12 = " ";
      if (var21 == null) {
         var11 = " ";
      } else {
         var11 = var21;
      }

      Charset var26 = StandardCharsets.UTF_16LE;
      Intrinsics.checkNotNullExpressionValue(var26, "UTF_16LE");
      byte[] var28 = var11.getBytes(var26);
      Intrinsics.checkNotNullExpressionValue(var28, "this as java.lang.String).getBytes(charset)");
      if (var23 == null) {
         var11 = var12;
      } else {
         var11 = var23;
      }

      Charset var25 = StandardCharsets.UTF_16LE;
      Intrinsics.checkNotNullExpressionValue(var25, "UTF_16LE");
      byte[] var27 = var11.getBytes(var25);
      Intrinsics.checkNotNullExpressionValue(var27, "this as java.lang.String).getBytes(charset)");
      (new Thread(new MsgMgr$$ExternalSyntheticLambda0(var28, var27))).start();
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      if (var2 != null) {
         byte var8;
         label42: {
            label41: {
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
                     break label41;
                  case 69707:
                     if (!var2.equals("FM2")) {
                        return;
                     }
                     break label41;
                  case 69708:
                     if (!var2.equals("FM3")) {
                        return;
                     }
                     break label41;
                  default:
                     return;
               }

               var8 = 16;
               break label42;
            }

            var8 = 0;
         }

         byte var7 = (byte)1;
         byte var6 = (byte)var8;
         byte[] var9 = new byte[5];

         for(var1 = 0; var1 < 5; ++var1) {
            var9[var1] = 0;
         }

         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -64, var7, 0, var6}, var9));
      }

   }
}
