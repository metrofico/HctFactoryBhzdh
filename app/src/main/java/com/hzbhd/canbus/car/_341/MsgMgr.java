package com.hzbhd.canbus.car._341;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0016J&\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010\u001f\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\u0018H\u0016J\u0018\u0010(\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010)\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010*\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0016J\u0018\u0010,\u001a\u00020-2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\bH\u0002JÄ\u0001\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\b2\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u0002012\u0006\u00106\u001a\u0002012\u0006\u00107\u001a\u0002012\u0006\u00108\u001a\u0002012\u0006\u00109\u001a\u0002012\u0006\u0010:\u001a\u0002012\b\u0010;\u001a\u0004\u0018\u00010\u00122\b\u0010<\u001a\u0004\u0018\u00010\u00122\b\u0010=\u001a\u0004\u0018\u00010\u00122\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020\b2\u0006\u0010B\u001a\u00020-2\u0006\u0010C\u001a\u00020?2\b\u0010D\u001a\u0004\u0018\u00010\u00122\b\u0010E\u001a\u0004\u0018\u00010\u00122\b\u0010F\u001a\u0004\u0018\u00010\u00122\u0006\u0010G\u001a\u00020-H\u0016J6\u0010H\u001a\u00020\u00182\u0006\u0010I\u001a\u00020\b2\b\u0010J\u001a\u0004\u0018\u00010\u00122\b\u0010K\u001a\u0004\u0018\u00010\u00122\b\u0010L\u001a\u0004\u0018\u00010\u00122\u0006\u0010M\u001a\u00020\bH\u0016J\u0018\u0010N\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010O\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J:\u0010P\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u00042\u0018\u0010S\u001a\u0014\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00180TH\u0002J\"\u0010U\u001a\u00020\u00182\u0006\u0010V\u001a\u00020\b2\u0006\u0010W\u001a\u00020\b2\b\b\u0002\u0010X\u001a\u00020#H\u0002J\u001a\u0010Y\u001a\u00020\u00182\u0006\u0010Z\u001a\u00020\b2\b\b\u0002\u0010[\u001a\u00020\u0012H\u0002J\u001a\u0010\\\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010]\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010^\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0010\u0010_\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010`\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010a\u001a\u00020#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006b"},
   d2 = {"Lcom/hzbhd/canbus/car/_341/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "lastArrayForFrontAirCondition", "", "lastArrayForGeneralInfo", "lastArrayForRearAirCondition", "stagedData1", "", "getStagedData1", "()Ljava/lang/Integer;", "setStagedData1", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "stagedData7", "getStagedData7", "setStagedData7", "test", "", "getTest", "()Ljava/lang/String;", "setTest", "(Ljava/lang/String;)V", "amplifierInfo", "", "mCanBusInfoInt", "auxInInfoChange", "btMusicId3InfoChange", "title", "artist", "album", "canbusInfoChange", "context", "Landroid/content/Context;", "canbusInfo", "", "culTrackAngle", "track", "", "dtvInfoChange", "frontAirConditionInfo", "frontRadarInfo", "generalInfo", "initCommand", "isOnlyChanged", "", "param", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "rearAirConditionInfo", "rearRadarInfo", "rptCmdFilter", "current", "last", "func", "Lkotlin/Function2;", "sendMediaInfo", "data0", "data1", "data2_7", "sendTextInfo", "dataType", "text", "steeringWheelAngle", "steeringWheelKeys", "systemInfo", "vehicleInfo", "versionInfo", "mCanBusInfoByte", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private int[] lastArrayForFrontAirCondition;
   private int[] lastArrayForGeneralInfo;
   private int[] lastArrayForRearAirCondition;
   private Integer stagedData1;
   private Integer stagedData7;
   public String test;

   public MsgMgr() {
      int[] var2 = new int[9];

      int var1;
      for(var1 = 0; var1 < 9; ++var1) {
         var2[var1] = 0;
      }

      this.lastArrayForFrontAirCondition = var2;
      var2 = new int[9];

      for(var1 = 0; var1 < 9; ++var1) {
         var2[var1] = 0;
      }

      this.lastArrayForRearAirCondition = var2;
      var2 = new int[4];

      for(var1 = 0; var1 < 4; ++var1) {
         var2[var1] = 0;
      }

      this.lastArrayForGeneralInfo = var2;
   }

   private final void amplifierInfo(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 4);
      byte var3 = 0;
      GeneralAmplifierData.frontRear = MsgMgrKt.reverse(DataHandleUtils.rangeNumber(var2, 0, 14)) - 7;
      GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4), 0, 14) - 7;
      GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4), 2, 12) - 2;
      GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(var1[4], 4, 4), 2, 12) - 2;
      GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(var1[5], 0, 63);
      ArrayList var4 = new ArrayList();
      byte var5;
      if (DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 4) == 1) {
         var5 = 1;
      } else {
         var5 = 0;
      }

      var4.add(new SettingUpdateEntity(1, 0, Integer.valueOf(var5)));
      var5 = var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 4) == 1) {
         var5 = 1;
      }

      var4.add(new SettingUpdateEntity(1, 1, Integer.valueOf(var5)));
      this.updateGeneralSettingData((List)var4);
      this.updateSettingActivity((Bundle)null);
      this.updateAmplifierActivity((Bundle)null);
   }

   private final int culTrackAngle(short var1) {
      return DataHandleUtils.rangeNumber((int)((float)var1 / 14.4F), -25, 25);
   }

   private final void frontAirConditionInfo(Context var1, int[] var2) {
      // $FF: Couldn't be decompiled
   }

   private final void frontRadarInfo(Context var1, int[] var2) {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private final void generalInfo(Context var1, int[] var2) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var2[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var2[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var2[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var2[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var2[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(var2[2]);
      GeneralDoorData.isLittleLightOn = DataHandleUtils.getBoolBit2(var2[3]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(var2[3]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(var2[3]);
      this.updateDoorView(var1);
   }

   private final boolean isOnlyChanged(int[] var1, int var2) {
      boolean var3;
      if (var2 >= 0 && var2 < var1.length) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         int var4;
         for(var4 = 0; var4 < var2; ++var4) {
            if (var1[var4] != this.lastArrayForFrontAirCondition[var4]) {
               return false;
            }
         }

         ++var2;

         for(var4 = var1.length; var2 < var4; ++var2) {
            if (var1[var2] != this.lastArrayForFrontAirCondition[var2]) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private final void rearAirConditionInfo(Context var1, int[] var2) {
      int var3 = var2[2];
      String var4;
      if (var3 != 16) {
         if (var3 != 80) {
            var4 = (double)var2[2] / 2.0 + " °C";
         } else {
            var4 = "HI";
         }
      } else {
         var4 = "LO";
      }

      GeneralAirData.rear_temperature = var4;
      GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(var2[4], 0, 4);
      GeneralAirData.rear_auto_wind_model = false;
      GeneralAirData.rear_left_blow_head = false;
      GeneralAirData.rear_left_blow_foot = false;
      GeneralAirData.rear_right_blow_head = false;
      GeneralAirData.rear_right_blow_foot = false;
      var3 = DataHandleUtils.getIntFromByteWithBit(var2[4], 4, 4);
      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 == 3) {
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_foot = true;
               }
            } else {
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
         GeneralAirData.rear_auto_wind_model = true;
      }

      GeneralAirData.rear_auto = DataHandleUtils.getBoolBit0(var2[6]);
      this.updateAirActivity(var1, 1002);
   }

   private final void rearRadarInfo(Context var1, int[] var2) {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private final void rptCmdFilter(Context var1, int[] var2, int[] var3, Function2 var4) {
      if (!MsgMgrKt.contentCompare(var2, var3)) {
         var4.invoke(var1, var2);
      }

      if (var2.length == var3.length) {
         MsgMgrKt.transTo(var2, var3);
      }

   }

   private final void sendMediaInfo(int var1, int var2, byte[] var3) {
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte)var1, (byte)var2}, var3));
   }

   // $FF: synthetic method
   static void sendMediaInfo$default(MsgMgr var0, int var1, int var2, byte[] var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = new byte[]{0, 0, 0, 0, 0, 0};
      }

      var0.sendMediaInfo(var1, var2, var3);
   }

   private final void sendTextInfo(int var1, String var2) {
      byte[] var3;
      if (var2.length() >= 15) {
         var3 = (StringsKt.substring(var2, new IntRange(0, 15)) + "...").getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
      } else {
         var3 = var2.getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, (byte)var1, 17}, var3));
   }

   // $FF: synthetic method
   static void sendTextInfo$default(MsgMgr var0, int var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = "Unknown";
      }

      var0.sendTextInfo(var1, var2);
   }

   private final void steeringWheelAngle(Context var1, int[] var2) {
      int var3;
      if (DataHandleUtils.getBoolBit7(var2[3])) {
         var3 = this.culTrackAngle((short)(-((var2[3] & 15) * 256 + var2[2])));
      } else {
         var3 = var2[2];
         if (var3 == 0 && var2[3] == 0) {
            var3 = 0;
         } else {
            var3 = this.culTrackAngle((short)(4096 - ((var2[3] & 15) * 256 + var3)));
         }
      }

      GeneralParkData.trackAngle = var3;
      this.updateParkUi((Bundle)null, var1);
   }

   private final void steeringWheelKeys(Context var1, int[] var2) {
      int var4 = var2[2];
      int var3 = var2[3];
      if (var4 != 0) {
         if (var4 != 1) {
            if (var4 != 2) {
               if (var4 != 3) {
                  if (var4 != 4) {
                     if (var4 != 9) {
                        if (var4 != 10) {
                           if (var4 != 136) {
                              switch (var4) {
                                 case 19:
                                    this.realKeyLongClick1(var1, 45, var3);
                                    break;
                                 case 20:
                                    this.realKeyLongClick1(var1, 46, var3);
                                    break;
                                 case 21:
                                    this.realKeyLongClick1(var1, 50, var3);
                                    break;
                                 case 22:
                                    this.realKeyLongClick1(var1, 49, var3);
                              }
                           } else {
                              this.realKeyLongClick1(var1, 2, var3);
                           }
                        } else {
                           this.realKeyLongClick1(var1, 15, var3);
                        }
                     } else {
                        this.realKeyLongClick1(var1, 14, var3);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 47, var3);
                  }
               } else {
                  this.realKeyLongClick1(var1, 48, var3);
               }
            } else {
               this.realKeyLongClick1(var1, 8, var3);
            }
         } else {
            this.realKeyLongClick1(var1, 7, var3);
         }
      } else {
         this.realKeyLongClick1(var1, 0, var3);
      }

   }

   private final void systemInfo(Context var1, int[] var2) {
      String var3 = CommUtil.getStrByResId(var1, "_341_setting_0_0_0");
      Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context, \"_341_setting_0_0_0\")");
      String var7 = CommUtil.getStrByResId(var1, "_341_setting_0_0_1");
      Intrinsics.checkNotNullExpressionValue(var7, "getStrByResId(context, \"_341_setting_0_0_1\")");
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getBoolBit6(var2[2]))));
      var6.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getBoolBit7(var2[2]))));
      ArrayList var5 = new ArrayList();
      String var4;
      if (DataHandleUtils.getBoolBit0(var2[2])) {
         var4 = var7;
      } else {
         var4 = var3;
      }

      var5.add(new DriverUpdateEntity(1, 0, var4));
      if (DataHandleUtils.getBoolBit1(var2[2])) {
         var4 = var7;
      } else {
         var4 = var3;
      }

      var5.add(new DriverUpdateEntity(1, 1, var4));
      if (DataHandleUtils.getBoolBit2(var2[2])) {
         var4 = var7;
      } else {
         var4 = var3;
      }

      var5.add(new DriverUpdateEntity(1, 2, var4));
      if (DataHandleUtils.getBoolBit3(var2[2])) {
         var3 = var7;
      }

      var5.add(new DriverUpdateEntity(1, 3, var3));
      this.updateGeneralSettingData((List)var6);
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralDriveData((List)var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void vehicleInfo(int[] var1) {
      this.updateSpeedInfo(var1[4]);
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(0, 0, MsgMgrKt.getMsbLsbResult(var1[2], var1[3]) + " r/min"));
      var2.add(new DriverUpdateEntity(0, 1, var1[4] + " km/h"));
      this.updateGeneralDriveData((List)var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void versionInfo(Context var1, byte[] var2) {
      this.updateVersionInfo(var1, this.getVersionStr(var2));
   }

   public void auxInInfoChange() {
      sendMediaInfo$default(this, 7, 0, (byte[])null, 4, (Object)null);
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      sendMediaInfo$default(this, 11, 255, (byte[])null, 4, (Object)null);
      if (var1 != null) {
         this.sendTextInfo(112, var1);
      } else {
         sendTextInfo$default(this, 112, (String)null, 2, (Object)null);
      }

      if (var2 != null) {
         this.sendTextInfo(113, var2);
      } else {
         sendTextInfo$default(this, 113, (String)null, 2, (Object)null);
      }

      if (var3 != null) {
         this.sendTextInfo(114, var3);
      } else {
         sendTextInfo$default(this, 114, (String)null, 2, (Object)null);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      if (var2 != null && var1 != null) {
         int[] var4 = this.getByteArrayToIntArray(var2);
         Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
         int var3 = var4[1];
         if (var3 != 29) {
            if (var3 != 30) {
               if (var3 != 32) {
                  if (var3 != 36) {
                     if (var3 != 41) {
                        if (var3 != 53) {
                           if (var3 != 85) {
                              if (var3 != 86) {
                                 switch (var3) {
                                    case 48:
                                       this.versionInfo(var1, var2);
                                       break;
                                    case 49:
                                       this.amplifierInfo(var4);
                                       break;
                                    case 50:
                                       this.systemInfo(var1, var4);
                                 }
                              } else {
                                 this.rptCmdFilter(var1, var4, this.lastArrayForRearAirCondition, (Function2)(new Function2(this) {
                                    public final void invoke(Context var1, int[] var2) {
                                       Intrinsics.checkNotNullParameter(var1, "p0");
                                       Intrinsics.checkNotNullParameter(var2, "p1");
                                       ((MsgMgr)this.receiver).rearAirConditionInfo(var1, var2);
                                    }
                                 }));
                              }
                           } else {
                              this.rptCmdFilter(var1, var4, this.lastArrayForFrontAirCondition, (Function2)(new Function2(this) {
                                 public final void invoke(Context var1, int[] var2) {
                                    Intrinsics.checkNotNullParameter(var1, "p0");
                                    Intrinsics.checkNotNullParameter(var2, "p1");
                                    ((MsgMgr)this.receiver).frontAirConditionInfo(var1, var2);
                                 }
                              }));
                           }
                        } else {
                           this.vehicleInfo(var4);
                        }
                     } else {
                        this.steeringWheelAngle(var1, var4);
                     }
                  } else {
                     this.rptCmdFilter(var1, var4, this.lastArrayForGeneralInfo, (Function2)(new Function2(this) {
                        public final void invoke(Context var1, int[] var2) {
                           Intrinsics.checkNotNullParameter(var1, "p0");
                           Intrinsics.checkNotNullParameter(var2, "p1");
                           ((MsgMgr)this.receiver).generalInfo(var1, var2);
                        }
                     }));
                  }
               } else {
                  this.steeringWheelKeys(var1, var4);
               }
            } else {
               this.rearRadarInfo(var1, var4);
            }
         } else {
            this.frontRadarInfo(var1, var4);
         }
      }

   }

   public void dtvInfoChange() {
      sendMediaInfo$default(this, 3, 0, (byte[])null, 4, (Object)null);
   }

   public final Integer getStagedData1() {
      return this.stagedData1;
   }

   public final Integer getStagedData7() {
      return this.stagedData7;
   }

   public final String getTest() {
      String var1 = this.test;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("test");
         return null;
      }
   }

   public void initCommand(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.initCommand(var1);
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isShowLittleLight = true;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 != 9) {
         sendMediaInfo$default(this, 8, 255, (byte[])null, 4, (Object)null);
         if (var21 != null) {
            this.sendTextInfo(112, var21);
         } else {
            sendTextInfo$default(this, 112, (String)null, 2, (Object)null);
         }

         if (var23 != null) {
            this.sendTextInfo(113, var23);
         } else {
            sendTextInfo$default(this, 113, (String)null, 2, (Object)null);
         }

         if (var22 != null) {
            this.sendTextInfo(114, var22);
         } else {
            sendTextInfo$default(this, 114, (String)null, 2, (Object)null);
         }
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      boolean var6;
      Integer var7;
      List var8;
      label160: {
         var8 = (List)(new ArrayList());
         var6 = false;
         if (var2 != null) {
            var5 = var2.hashCode();
            if (var5 != 2092) {
               if (var5 != 2247) {
                  switch (var5) {
                     case 64901:
                        if (var2.equals("AM1")) {
                           var7 = 17;
                           break label160;
                        }
                        break;
                     case 64902:
                        if (var2.equals("AM2")) {
                           var7 = 18;
                           break label160;
                        }
                        break;
                     case 64903:
                        if (var2.equals("AM3")) {
                           var7 = 19;
                           break label160;
                        }
                        break;
                     default:
                        switch (var5) {
                           case 69706:
                              if (var2.equals("FM1")) {
                                 var7 = 1;
                                 break label160;
                              }
                              break;
                           case 69707:
                              if (var2.equals("FM2")) {
                                 var7 = 2;
                                 break label160;
                              }
                              break;
                           case 69708:
                              if (var2.equals("FM3")) {
                                 var7 = 3;
                                 break label160;
                              }
                        }
                  }
               } else if (var2.equals("FM")) {
                  var7 = 0;
                  break label160;
               }
            } else if (var2.equals("AM")) {
               var7 = 16;
               break label160;
            }
         }

         var7 = null;
      }

      boolean var9;
      if ((var7 == null || var7 != 0) && (var7 == null || var7 != 1)) {
         var9 = false;
      } else {
         var9 = true;
      }

      if (!var9 && (var7 == null || var7 != 2)) {
         var9 = false;
      } else {
         var9 = true;
      }

      if (!var9 && (var7 == null || var7 != 3)) {
         var9 = false;
      } else {
         var9 = true;
      }

      int var10;
      if (var9) {
         Intrinsics.checkNotNull(var3);
         var10 = (int)(Double.parseDouble(var3) * (double)100);
         var5 = DataHandleUtils.getLsb(var10);
         var10 = DataHandleUtils.getMsb(var10);
         var8.add((byte)var7);
         var8.add((byte)var5);
         var8.add((byte)var10);
      } else {
         if (var7 != null && var7 == 16 || var7 != null && var7 == 17) {
            var9 = true;
         } else {
            var9 = false;
         }

         if (!var9 && (var7 == null || var7 != 18)) {
            var9 = false;
         } else {
            var9 = true;
         }

         label91: {
            if (!var9) {
               if (var7 == null) {
                  var9 = var6;
                  break label91;
               }

               var9 = var6;
               if (var7 != 19) {
                  break label91;
               }
            }

            var9 = true;
         }

         if (var9) {
            Intrinsics.checkNotNull(var3);
            var10 = Integer.parseInt(var3);
            var5 = DataHandleUtils.getLsb(var10);
            var10 = DataHandleUtils.getMsb(var10);
            var8.add((byte)var7);
            var8.add((byte)var5);
            var8.add((byte)var10);
         }
      }

      var8.add((byte)var1);
      this.sendMediaInfo(1, 1, CollectionsKt.toByteArray((Collection)var8));
   }

   public final void setStagedData1(Integer var1) {
      this.stagedData1 = var1;
   }

   public final void setStagedData7(Integer var1) {
      this.stagedData7 = var1;
   }

   public final void setTest(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.test = var1;
   }
}
