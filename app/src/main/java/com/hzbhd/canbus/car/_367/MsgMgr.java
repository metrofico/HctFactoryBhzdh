package com.hzbhd.canbus.car._367;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

@Metadata(
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016JT\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u0010\u001d\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0013H\u0016JÄ\u0001\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010,2\b\u0010.\u001a\u0004\u0018\u00010,2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020!2\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u0002002\b\u00105\u001a\u0004\u0018\u00010,2\b\u00106\u001a\u0004\u0018\u00010,2\b\u00107\u001a\u0004\u0018\u00010,2\u0006\u00108\u001a\u00020\u0015H\u0016J6\u00109\u001a\u00020\n2\u0006\u0010:\u001a\u00020\u00112\b\u0010;\u001a\u0004\u0018\u00010,2\b\u0010<\u001a\u0004\u0018\u00010,2\b\u0010=\u001a\u0004\u0018\u00010,2\u0006\u0010>\u001a\u00020\u0011H\u0016J\u0010\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\u0011H\u0002J\b\u0010A\u001a\u00020\nH\u0002J\b\u0010B\u001a\u00020\nH\u0002J\b\u0010C\u001a\u00020\nH\u0002J\b\u0010D\u001a\u00020\nH\u0002J\b\u0010E\u001a\u00020\nH\u0002J\u0010\u0010F\u001a\u00020\n2\u0006\u0010G\u001a\u00020\u0013H\u0002J\u0010\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\u0015H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006J"},
   d2 = {"Lcom/hzbhd/canbus/car/_367/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "auxInInfoChange", "btMusicInfoChange", "btPhoneStatusInfoChange", "callStatus", "", "phoneNumber", "", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendSourceData", "d0", "set0x72Data", "set0x73Data", "set0x74Data", "set0x75Data", "set0x76Data", "set0xF0Data", "bytes", "sourceSwitchNoMediaInfoChange", "isPowerOff", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;

   private final void sendSourceData(int var1) {
      byte[] var2 = MsgMgrKt.restrict$default(new byte[]{(byte)var1}, 13, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -46}, var2));
   }

   private final void set0x72Data() {
      int var1;
      boolean var2;
      byte var3;
      Context var6;
      short var7;
      label116: {
         MsgMgr var5 = (MsgMgr)this;
         var6 = InitUtilsKt.getMContext();
         int[] var8 = this.getFrame();
         var3 = 4;
         var1 = var8[4];
         var2 = false;
         if (var1 != 0) {
            if (var1 == 1) {
               var7 = 7;
               break label116;
            }

            if (var1 == 2) {
               var7 = 8;
               break label116;
            }

            if (var1 == 3) {
               var7 = 3;
               break label116;
            }

            if (var1 == 5) {
               var7 = 14;
               break label116;
            }

            if (var1 == 6) {
               var7 = 15;
               break label116;
            }

            switch (var1) {
               case 13:
                  var7 = 45;
                  break label116;
               case 14:
                  var7 = 46;
                  break label116;
               case 15:
                  var7 = 49;
                  break label116;
               case 16:
                  var7 = 50;
                  break label116;
               default:
                  switch (var1) {
                     case 22:
                        var7 = 47;
                        break label116;
                     case 23:
                        var7 = 48;
                        break label116;
                     case 24:
                        var7 = 187;
                        break label116;
                  }
            }
         }

         var7 = 0;
      }

      this.realKeyClick4(var6, var7);
      int var4 = this.getFrame()[5];
      boolean var9;
      if (var4 >= 0 && var4 < 21) {
         var9 = true;
      } else {
         var9 = false;
      }

      label93: {
         byte var10;
         if (var9) {
            var10 = 1;
         } else {
            if (21 <= var4 && var4 < 41) {
               var9 = true;
            } else {
               var9 = false;
            }

            if (var9) {
               var10 = 2;
            } else {
               if (41 <= var4 && var4 < 61) {
                  var9 = true;
               } else {
                  var9 = false;
               }

               if (var9) {
                  var10 = 3;
               } else {
                  if (61 <= var4 && var4 < 81) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  if (var9) {
                     var10 = var3;
                  } else {
                     var9 = var2;
                     if (81 <= var4) {
                        var9 = var2;
                        if (var4 < 101) {
                           var9 = true;
                        }
                     }

                     if (!var9) {
                        break label93;
                     }

                     var10 = 5;
                  }
               }
            }
         }

         this.setBacklightLevel(var10);
      }

      label68: {
         var1 = this.getFrame()[6];
         if (var1 == 255) {
            var1 = this.getFrame()[7];
            if (var1 == 255) {
               break label68;
            }

            var1 = TrackInfoUtil.culTrackAngle((short)var1, 540);
         } else {
            var1 = -TrackInfoUtil.culTrackAngle((short)var1, 540);
         }

         GeneralParkData.trackAngle = var1;
      }

      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      RadarInfoUtil.setRearRadarDistanceData(this.getFrame()[8], this.getFrame()[9], this.getFrame()[10], this.getFrame()[11]);
      RadarInfoUtil.setFrontRadarDistanceData(this.getFrame()[12], this.getFrame()[13], this.getFrame()[14], this.getFrame()[15]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void set0x73Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 2);
      byte var4;
      if (var1 != 0) {
         if (var1 != 1 && var1 == 3) {
            var4 = 2;
         } else {
            var4 = 0;
         }
      } else {
         var4 = 1;
      }

      GeneralAirData.in_out_auto_cycle = var4;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      var1 = this.getFrame()[4];
      String var3 = "HIGH";
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 255) {
               var2 = var1 + " °C";
            } else {
               var2 = "HIGH";
            }
         } else {
            var2 = "LOW";
         }
      } else {
         var2 = "";
      }

      GeneralAirData.front_left_temperature = var2;
      var1 = this.getFrame()[5];
      if (var1 != 0) {
         if (var1 != 1) {
            var2 = var3;
            if (var1 != 255) {
               var2 = var1 + " °C";
            }
         } else {
            var2 = "LOW";
         }
      } else {
         var2 = "";
      }

      GeneralAirData.front_right_temperature = var2;
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.getFrame()[6]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.getFrame()[6]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.getFrame()[6]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.getFrame()[7]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.getFrame()[7]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.getFrame()[7]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 0, 4);
      if (DataHandleUtils.getBoolBit7(this.getFrame()[8])) {
         this.updateOutDoorTemp(InitUtilsKt.getMContext(), DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 0, 7) - 40 + " °C");
      }

      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
      if (DataHandleUtils.getBoolBit0(this.getFrame()[9])) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[9]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[9]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[9]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[9]);
         this.updateDoorView(InitUtilsKt.getMContext());
      }

   }

   private final void set0x74Data() {
      Context var4 = InitUtilsKt.getMContext();
      int var3 = this.getFrame()[2];
      byte var2 = 0;
      byte var1 = var2;
      switch (var3) {
         case 0:
            break;
         case 1:
         case 5:
            var1 = 45;
            break;
         case 2:
         case 6:
            var1 = 46;
            break;
         case 3:
         case 7:
            var1 = 47;
            break;
         case 4:
         case 8:
            var1 = 48;
            break;
         case 9:
            var1 = 49;
            break;
         case 10:
            var1 = 43;
            break;
         case 11:
            var1 = 50;
            break;
         default:
            var1 = var2;
      }

      DataHandleUtils.knob(var4, var1, this.getFrame()[3]);
   }

   private final void set0x75Data() {
      int var1 = this.getFrame()[2];
      String var3 = "";
      String var2;
      switch (var1) {
         case 1:
            var2 = "RADIO";
            break;
         case 2:
            var2 = "CD";
            break;
         case 3:
            var2 = "USB";
            break;
         case 4:
            var2 = "BT";
            break;
         case 5:
            var2 = "PHONE";
            break;
         case 6:
            var2 = "NAVI";
            break;
         case 7:
            var2 = "AUX IN";
            break;
         case 8:
            var2 = "OFF";
            break;
         case 9:
            var2 = "AM";
            break;
         default:
            var2 = "";
      }

      GeneralOriginalCarDeviceData.cdStatus = var2;
      var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 4);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               var2 = var3;
            } else {
               var2 = InitUtilsKt.getMContext().getString(2131755426);
            }
         } else {
            var2 = InitUtilsKt.getMContext().getString(2131755425);
         }
      } else {
         var2 = InitUtilsKt.getMContext().getString(2131755424);
      }

      GeneralOriginalCarDeviceData.runningState = var2;
      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private final void set0x76Data() {
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNull(var1);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._367.UiMgr");
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(this, (Continuation)null) {
         int label;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            return (Continuation)(new <anonymous constructor>(this.this$0, var2));
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
               if (DelayKt.delay(1000L, var4) == var3) {
                  return var3;
               }
            }

            do {
               CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte)var5.this$0.getCurrentCanDifferentId(), 0});
               var4 = (Continuation)var5;
               var5.label = 1;
            } while(DelayKt.delay(1000L, var4) != var3);

            return var3;
         }
      }), 3, (Object)null);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void auxInInfoChange() {
      this.sendSourceData(12);
   }

   public void btMusicInfoChange() {
      this.sendSourceData(11);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      this.sendSourceData(10);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 240) {
         switch (var3) {
            case 114:
               this.set0x72Data();
               break;
            case 115:
               this.set0x73Data();
               break;
            case 116:
               this.set0x74Data();
               break;
            case 117:
               this.set0x75Data();
               break;
            case 118:
               this.set0x76Data();
         }
      } else {
         this.set0xF0Data(var2);
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
         var1 = 15;
      }

      this.sendSourceData(var1);
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

         this.sendSourceData(var6);
      }

   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      this.sendSourceData(0);
   }
}
