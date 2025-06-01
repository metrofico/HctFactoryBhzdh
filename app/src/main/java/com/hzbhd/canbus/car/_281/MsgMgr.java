package com.hzbhd.canbus.car._281;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.ui.util.BaseUtil;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010%\u001a\u00020&2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010'\u001a\u00020&H\u0002J\u0018\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u001dH\u0002J\b\u0010+\u001a\u00020&H\u0016J\u000e\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\u001dJ\u001c\u0010.\u001a\u00020&2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u000e\u00101\u001a\u00020&2\u0006\u00102\u001a\u00020\u001dJ\u0012\u00103\u001a\u00020&2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016JÄ\u0001\u00104\u001a\u00020&2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00102\b\u0010?\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010@2\b\u0010B\u001a\u0004\u0018\u00010@2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u00102\u0006\u0010F\u001a\u00020\u001d2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020D2\b\u0010J\u001a\u0004\u0018\u00010@2\b\u0010K\u001a\u0004\u0018\u00010@2\b\u0010L\u001a\u0004\u0018\u00010@2\u0006\u0010M\u001a\u00020HH\u0016J\b\u0010N\u001a\u00020&H\u0002J\u0010\u0010O\u001a\u00020&2\u0006\u0010/\u001a\u000200H\u0002J\b\u0010P\u001a\u00020&H\u0002J\b\u0010Q\u001a\u00020&H\u0002J6\u0010R\u001a\u00020&2\u0006\u0010S\u001a\u00020\u001d2\b\u0010T\u001a\u0004\u0018\u00010@2\b\u0010U\u001a\u0004\u0018\u00010@2\b\u0010V\u001a\u0004\u0018\u00010@2\u0006\u0010W\u001a\u00020\u001dH\u0016J\u0012\u0010X\u001a\u00020&2\b\b\u0002\u00102\u001a\u00020\u001dH\u0002J\b\u0010Y\u001a\u00020&H\u0002J\b\u0010Z\u001a\u00020&H\u0002J\b\u0010[\u001a\u00020&H\u0002J\b\u0010\\\u001a\u00020&H\u0002J\b\u0010]\u001a\u00020&H\u0002J\u0098\u0001\u0010^\u001a\u00020&2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\b\u0010<\u001a\u0004\u0018\u00010@2\u0006\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00102\b\u0010?\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010@2\b\u0010B\u001a\u0004\u0018\u00010@2\u0006\u0010C\u001a\u00020\u001d2\u0006\u0010_\u001a\u00020\u00102\u0006\u0010G\u001a\u00020H2\u0006\u0010`\u001a\u00020\u001dH\u0016J\u000e\u0010a\u001a\u00020&2\u0006\u0010b\u001a\u00020\u001dR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010 \u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010!\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010\"\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010#\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010$\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001e¨\u0006c"},
   d2 = {"Lcom/hzbhd/canbus/car/_281/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastKnobValue", "", "Ljava/lang/Byte;", "mOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "getMOriginalCarDevicePageUiSet", "()Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "uiMgr", "Lcom/hzbhd/canbus/car/_281/UiMgr;", "getUiMgr", "()Lcom/hzbhd/canbus/car/_281/UiMgr;", "setUiMgr", "(Lcom/hzbhd/canbus/car/_281/UiMgr;)V", "x1AD1", "", "Ljava/lang/Integer;", "x1AD6", "x1AD7", "x1AD8", "x21", "x81D4", "x81D5", "afterServiceNormalSetting", "", "air", "anotherWheelAngle", "d6", "d7", "auxInInfoChange", "button", "d4", "canbusInfoChange", "canbusInfo", "", "door", "d1", "initCommand", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panelBtn", "panelKnob", "panorama", "popAirActivity", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "send0xF3Data", "set0x13Data", "set0x31Data", "set0x83Data", "set0x84Data", "set0x86Data", "videoInfoChange", "playMode", "duration", "wheelAngle", "d5", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frame;
   private Byte lastKnobValue;
   private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   public UiMgr uiMgr;
   private Integer x1AD1;
   private Integer x1AD6;
   private Integer x1AD7;
   private Integer x1AD8;
   private Integer x21;
   private Integer x81D4;
   private Integer x81D5;

   public MsgMgr() {
      OriginalCarDevicePageUiSet var1 = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getOriginalCarDevicePageUiSet(BaseUtil.INSTANCE.getContext());
      Intrinsics.checkNotNullExpressionValue(var1, "getCanUiMgr(BaseUtil.con…geUiSet(BaseUtil.context)");
      this.mOriginalCarDevicePageUiSet = var1;
   }

   private final void air() {
      if (DataHandleUtils.getBoolBit7(this.getFrame()[2])) {
         this.popAirActivity();
      }

      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.cycle_in_out_close = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 2);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
      GeneralAirData.swing = DataHandleUtils.getBoolBit1(this.getFrame()[2]);
      GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.getFrame()[2]);
      GeneralAirData.front_left_temperature = air$temp(this.getFrame()[3]);
      GeneralAirData.front_right_temperature = air$temp(this.getFrame()[4]);
      MsgMgrKt.windDirectionInit();
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 4, 4);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 0, 4);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[6]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.getFrame()[6]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit2(this.getFrame()[6]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.getFrame()[6]);
      GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit0(this.getFrame()[6]);
      this.updateAirActivity(this.getContext(), 1001);
   }

   private static final String air$temp(int var0) {
      String var1;
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 255) {
               var1 = (double)var0 / 2.0 + " °C";
            } else {
               var1 = "HI";
            }
         } else {
            var1 = "LO";
         }
      } else {
         var1 = "  ";
      }

      return var1;
   }

   private final void anotherWheelAngle(int var1, int var2) {
      label17: {
         Integer var3 = this.x1AD6;
         if (var3 != null && var1 == var3) {
            var3 = this.x1AD7;
            if (var3 != null && var2 == var3) {
               break label17;
            }
         }

         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var2, (byte)var1, 0, 540, 16);
         this.updateParkUi((Bundle)null, this.getContext());
      }

      this.x1AD6 = var1;
      this.x1AD7 = var2;
   }

   private final void panelBtn() {
      int var1 = this.getFrame()[2];
      Integer var2 = this.x21;
      if (var2 == null || var2 != var1) {
         if (var1 != 1) {
            if (var1 != 32) {
               if (var1 != 36) {
                  if (var1 != 57) {
                     if (var1 != 66) {
                        if (var1 != 47) {
                           if (var1 == 48) {
                              this.realKeyClick4(this.getContext(), 68);
                           }
                        } else {
                           this.realKeyClick4(this.getContext(), 151);
                        }
                     } else {
                        this.realKeyClick4(this.getContext(), 4);
                     }
                  } else {
                     this.realKeyClick4(this.getContext(), 182);
                  }
               } else {
                  this.realKeyClick4(this.getContext(), 2);
               }
            } else {
               this.realKeyClick4(this.getContext(), 128);
            }
         } else {
            this.realKeyClick4(this.getContext(), 134);
         }
      }

      this.x21 = var1;
   }

   private final void panelKnob(byte[] var1) {
      byte var3 = var1[3];
      Byte var6 = this.lastKnobValue;
      byte var2;
      if (var6 != null) {
         var2 = var6;
      } else {
         var2 = var3;
      }

      int var4 = Math.abs(var3 - var2);
      int var5 = this.getFrame()[2];
      if (var5 != 1) {
         if (var5 == 2) {
            if (var3 > var2) {
               DataHandleUtils.knob(this.getContext(), 46, var4);
            } else if (var3 < var2) {
               DataHandleUtils.knob(this.getContext(), 45, var4);
            }
         }
      } else if (var3 > var2) {
         DataHandleUtils.knob(this.getContext(), 7, var4);
      } else if (var3 < var2) {
         DataHandleUtils.knob(this.getContext(), 8, var4);
      }

      this.lastKnobValue = var1[3];
   }

   private final void panorama() {
      Integer var2 = this.x1AD8;
      int var1 = this.getFrame()[10];
      if (var2 == null || var2 != var1) {
         if (this.getFrame()[10] == 1) {
            this.forceReverse(this.getContext(), true);
         } else {
            this.forceReverse(this.getContext(), false);
         }
      }

      this.x1AD8 = this.getFrame()[10];
   }

   private final void popAirActivity() {
      if (SystemUtil.isForeground(this.getContext(), AirActivity.class.getName())) {
         this.realKeyClick4(this.getContext(), 50);
      } else {
         this.updateAirActivity(this.getContext(), 1001);
      }

   }

   private final void send0xF3Data(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -13, 1, (byte)var1});
   }

   // $FF: synthetic method
   static void send0xF3Data$default(MsgMgr var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 5;
      }

      var0.send0xF3Data(var1);
   }

   private final void set0x13Data() {
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_6");
      if (var2 != null) {
         var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[2], this.getFrame()[3]) / 10.0));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_7");
      if (var2 != null) {
         var2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_8");
      if (var2 != null) {
         var2.setValue(String.valueOf((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]) / 10.0));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_9");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[9]) + " Min");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_10");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[11]) + " Km/H");
      }

      DriverDataPageUiSet.Page.Item var4 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_11");
      String var3 = "--";
      int var1;
      String var5;
      if (var4 != null) {
         var1 = this.getFrame()[12];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  var5 = "--";
               } else {
                  var5 = "L/100KM";
               }
            } else {
               var5 = "KM/L";
            }
         } else {
            var5 = "MPG";
         }

         var4.setValue(var5);
      }

      var4 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_12");
      if (var4 != null) {
         var1 = this.getFrame()[13];
         if (var1 != 0) {
            if (var1 != 1) {
               var5 = var3;
            } else {
               var5 = "MILE";
            }
         } else {
            var5 = "KM";
         }

         var4.setValue(var5);
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("_2_setting_10_6");
      if (var2 != null) {
         var2.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[16], this.getFrame()[17]) / 10.0, 10) + " Km");
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x31Data() {
      this.updateOutDoorTemp(this.getContext(), (double)this.getFrame()[13] * 0.5 - (double)40 + " °C");
   }

   private final void set0x83Data() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 4);
      if (var1 == 0 || var1 == 1) {
         this.enterAuxIn2();
      }
   }

   private final void set0x84Data() {
      int var1 = this.getFrame()[2];
      String var3 = "INVALID";
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        var2 = "INVALID";
                     } else {
                        var2 = "LW";
                     }
                  } else {
                     var2 = "MW";
                  }
               } else {
                  var2 = "FM2";
               }
            } else {
               var2 = "FM1";
            }
         } else {
            var2 = "AM";
         }
      } else {
         var2 = "FM";
      }

      GeneralOriginalCarDeviceData.cdStatus = var2;
      switch (this.getFrame()[7]) {
         case 1:
            var2 = "SEEK +";
            break;
         case 2:
            var2 = "SEEK -";
            break;
         case 3:
            var2 = "Auto Seek";
            break;
         case 4:
            var2 = "Tune +";
            break;
         case 5:
            var2 = "Tune -";
            break;
         case 6:
            var2 = "SCAN";
            break;
         default:
            var2 = var3;
      }

      GeneralOriginalCarDeviceData.runningState = var2;
      List var4 = this.mOriginalCarDevicePageUiSet.getItems();
      var4.clear();
      StringBuilder var5 = (new StringBuilder()).append(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[3])).append(' ');
      var2 = GeneralOriginalCarDeviceData.cdStatus;
      Intrinsics.checkNotNullExpressionValue(var2, "cdStatus");
      if (StringsKt.contains$default((CharSequence)var2, (CharSequence)"FM", false, 2, (Object)null)) {
         var2 = "MHz";
      } else {
         var2 = GeneralOriginalCarDeviceData.cdStatus;
         Intrinsics.checkNotNullExpressionValue(var2, "cdStatus");
         if (StringsKt.contains$default((CharSequence)var2, (CharSequence)"AM", false, 2, (Object)null)) {
            var2 = "KHz";
         } else {
            var2 = "";
         }
      }

      var4.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_frequency", var5.append(var2).toString()));
      var4.add(new OriginalCarDevicePageUiSet.Item("music_music", "S281_0x84_1", String.valueOf(this.getFrame()[5])));
      Bundle var6 = new Bundle();
      var6.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var6);
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
         case 0:
            var4 = "Reading TOC";
            break;
         case 1:
            var4 = "Pause";
            break;
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
            var4 = "Disc changing (User)";
            break;
         case 11:
            var4 = "Disc changing (Internal)";
            break;
         case 12:
            var4 = "Eject";
            break;
         default:
            var4 = "INVALID";
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

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._281.UiMgr");
      this.setUiMgr((UiMgr)var2);
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)this.getUiMgr(), (HashMap)null, 4, (Object)null);
   }

   public void auxInInfoChange() {
      send0xF3Data$default(this, 0, 1, (Object)null);
   }

   public final void button(int var1) {
      Integer var2 = this.x81D4;
      if (var2 == null || var1 != var2) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 6) {
                           if (var1 != 8) {
                              if (var1 != 9) {
                                 if (var1 != 12) {
                                    if (var1 == 32) {
                                       this.popAirActivity();
                                    }
                                 } else {
                                    this.realKeyClick4(this.getContext(), 2);
                                 }
                              } else {
                                 this.realKeyClick4(this.getContext(), 20);
                              }
                           } else {
                              this.realKeyClick4(this.getContext(), 21);
                           }
                        } else {
                           this.realKeyClick4(this.getContext(), 15);
                        }
                     } else {
                        this.realKeyClick4(this.getContext(), 14);
                     }
                  } else {
                     this.realKeyClick4(this.getContext(), 187);
                  }
               } else {
                  this.realKeyClick4(this.getContext(), 8);
               }
            } else {
               this.realKeyClick4(this.getContext(), 7);
            }
         } else {
            this.realKeyClick4(this.getContext(), 0);
         }

         this.x81D4 = var1;
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      if (var1 != null && var2 != null) {
         this.setContext(var1);
         int[] var4 = this.getByteArrayToIntArray(var2);
         Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
         this.setFrame(var4);
         int var3 = this.getFrame()[1];
         if (var3 != 19) {
            if (var3 != 26) {
               if (var3 != 49) {
                  if (var3 != 134) {
                     if (var3 != 33) {
                        if (var3 != 34) {
                           switch (var3) {
                              case 129:
                                 this.button(this.getFrame()[6]);
                                 this.wheelAngle(this.getFrame()[7]);
                                 break;
                              case 130:
                                 this.air();
                                 break;
                              case 131:
                                 this.set0x83Data();
                                 break;
                              case 132:
                                 this.set0x84Data();
                           }
                        } else {
                           this.panelKnob(var2);
                        }
                     } else {
                        this.panelBtn();
                     }
                  } else {
                     this.set0x86Data();
                  }
               } else {
                  this.set0x31Data();
               }
            } else {
               this.door(this.getFrame()[3]);
               this.anotherWheelAngle(this.getFrame()[8], this.getFrame()[9]);
               this.panorama();
            }
         } else {
            this.set0x13Data();
         }
      }

   }

   public final void door(int var1) {
      Integer var2 = this.x1AD1;
      if (var2 == null || var1 != var2) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(var1);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(var1);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(var1);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1);
         this.updateDoorView(this.getContext());
      }

      this.x1AD1 = var1;
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
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      send0xF3Data$default(this, 0, 1, (Object)null);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      send0xF3Data$default(this, 0, 1, (Object)null);
   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public final void setUiMgr(UiMgr var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.uiMgr = var1;
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      send0xF3Data$default(this, 0, 1, (Object)null);
   }

   public final void wheelAngle(int var1) {
      Integer var2 = this.x81D5;
      if (var2 == null || var1 != var2) {
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1((byte)var1, (byte)0, 0, 127, 8);
         this.updateParkUi((Bundle)null, this.getContext());
      }

      this.x81D5 = var1;
   }
}
