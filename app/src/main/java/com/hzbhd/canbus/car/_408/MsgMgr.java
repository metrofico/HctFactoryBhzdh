package com.hzbhd.canbus.car._408;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016JÄ\u0001\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$2\b\u0010&\u001a\u0004\u0018\u00010$2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020(2\b\u0010.\u001a\u0004\u0018\u00010$2\b\u0010/\u001a\u0004\u0018\u00010$2\b\u00100\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020,H\u0016J6\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u001b2\b\u00104\u001a\u0004\u0018\u00010$2\b\u00105\u001a\u0004\u0018\u00010$2\b\u00106\u001a\u0004\u0018\u00010$2\u0006\u00107\u001a\u00020\u001bH\u0016J\u0010\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u001bH\u0002J\b\u0010:\u001a\u00020\u0010H\u0002J\b\u0010;\u001a\u00020\u0010H\u0002J\b\u0010<\u001a\u00020\u0010H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006="},
   d2 = {"Lcom/hzbhd/canbus/car/_408/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "array", "", "getArray", "()[Z", "setArray", "([Z)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "send0xD2Data", "d0", "set0x72Data", "set0x73Data", "set0x74Data", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private boolean[] array;
   public int[] frame;

   public MsgMgr() {
      this.array = new boolean[]{GeneralDoorData.isLeftFrontDoorOpen, GeneralDoorData.isRightFrontDoorOpen, GeneralDoorData.isLeftRearDoorOpen, GeneralDoorData.isRightRearDoorOpen, GeneralDoorData.isBackOpen};
   }

   private final void send0xD2Data(int var1) {
      byte[] var2 = MsgMgrKt.restrict$default(new byte[]{(byte)var1}, 13, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -46}, var2));
   }

   private final void set0x72Data() {
      MsgMgr var3 = (MsgMgr)this;
      int var2 = this.getFrame()[7];
      boolean var1;
      if (var2 >= 0 && var2 < 21) {
         var1 = true;
      } else {
         var1 = false;
      }

      label63: {
         byte var4;
         if (var1) {
            var4 = 1;
         } else {
            if (21 <= var2 && var2 < 41) {
               var1 = true;
            } else {
               var1 = false;
            }

            if (var1) {
               var4 = 2;
            } else {
               if (41 <= var2 && var2 < 61) {
                  var1 = true;
               } else {
                  var1 = false;
               }

               if (var1) {
                  var4 = 3;
               } else {
                  if (61 <= var2 && var2 < 81) {
                     var1 = true;
                  } else {
                     var1 = false;
                  }

                  if (var1) {
                     var4 = 4;
                  } else {
                     if (81 <= var2 && var2 < 101) {
                        var1 = true;
                     } else {
                        var1 = false;
                     }

                     if (!var1) {
                        break label63;
                     }

                     var4 = 5;
                  }
               }
            }
         }

         this.setBacklightLevel(var4);
      }

      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(15, set0x72Data$restrictValue(this.getFrame()[8]), 0, 0, set0x72Data$restrictValue(this.getFrame()[11]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private static final int set0x72Data$restrictValue(int var0) {
      int var1 = var0;
      if (var0 == 255) {
         var1 = 0;
      }

      return var1;
   }

   private final void set0x73Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.getFrame()[2]) ^ true;
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.front_left_temperature = "Level " + this.getFrame()[4];
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      int var1 = this.getFrame()[6];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralAirData.front_left_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[8]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[8]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[8]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[8]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[8]);
      boolean[] var2 = new boolean[]{DataHandleUtils.getBoolBit7(this.getFrame()[8]), DataHandleUtils.getBoolBit6(this.getFrame()[8]), DataHandleUtils.getBoolBit5(this.getFrame()[8]), DataHandleUtils.getBoolBit4(this.getFrame()[8]), DataHandleUtils.getBoolBit3(this.getFrame()[8])};
      if (!Arrays.equals(var2, this.array)) {
         this.updateDoorView(InitUtilsKt.getMContext());
         this.array = var2;
      }

      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
   }

   private final void set0x74Data() {
      Context var2 = InitUtilsKt.getMContext();
      int var1;
      switch (this.getFrame()[2]) {
         case 1:
            var1 = 1;
            break;
         case 2:
            var1 = 21;
            break;
         case 3:
            var1 = 20;
            break;
         case 4:
            var1 = 2;
            break;
         case 5:
            var1 = 76;
            break;
         case 6:
            var1 = 4;
            break;
         case 7:
            var1 = 30;
            break;
         case 8:
            var1 = 151;
            break;
         case 9:
            var1 = 3;
            break;
         case 10:
            var1 = 33;
            break;
         case 11:
            var1 = 34;
            break;
         case 12:
            var1 = 35;
            break;
         case 13:
            var1 = 36;
            break;
         case 14:
            var1 = 37;
            break;
         case 15:
            var1 = 38;
            break;
         case 16:
            var1 = 128;
            break;
         case 17:
            var1 = 31;
            break;
         default:
            var1 = 0;
      }

      this.realKeyClick4(var2, var1);
      var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 6, 2);
      if (var1 != 1) {
         if (var1 == 2) {
            DataHandleUtils.knob(InitUtilsKt.getMContext(), 8, DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 4));
         }
      } else {
         DataHandleUtils.knob(InitUtilsKt.getMContext(), 7, DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 4));
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNull(var1);
      InitUtilsKt.setMContext(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var3);
      switch (this.getFrame()[1]) {
         case 114:
            this.set0x72Data();
            break;
         case 115:
            this.set0x73Data();
            break;
         case 116:
            this.set0x74Data();
      }

   }

   public final boolean[] getArray() {
      return this.array;
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

      this.send0xD2Data(var1);
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

         this.send0xD2Data(var6);
      }

   }

   public final void setArray(boolean[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.array = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }
}
