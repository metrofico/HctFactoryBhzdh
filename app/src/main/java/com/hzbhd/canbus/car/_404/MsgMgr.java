package com.hzbhd.canbus.car._404;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016Jp\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\nH\u0016JÄ\u0001\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001002\b\u00102\u001a\u0004\u0018\u0001002\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020%2\u0006\u00106\u001a\u00020\n2\u0006\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u0002042\b\u00109\u001a\u0004\u0018\u0001002\b\u0010:\u001a\u0004\u0018\u0001002\b\u0010;\u001a\u0004\u0018\u0001002\u0006\u0010<\u001a\u00020\u001fH\u0016J \u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u0002002\u0006\u0010@\u001a\u000200H\u0002J6\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\n2\b\u0010C\u001a\u0004\u0018\u0001002\b\u0010D\u001a\u0004\u0018\u0001002\b\u0010E\u001a\u0004\u0018\u0001002\u0006\u0010F\u001a\u00020\nH\u0016J&\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\n2\b\b\u0002\u0010I\u001a\u00020\n2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010K\u001a\u00020\u000eH\u0002J\b\u0010L\u001a\u00020\u000eH\u0002J\b\u0010M\u001a\u00020\u000eH\u0002J\b\u0010N\u001a\u00020\u000eH\u0002J\b\u0010O\u001a\u00020\u000eH\u0002J\b\u0010P\u001a\u00020\u000eH\u0002J\b\u0010Q\u001a\u00020\u000eH\u0002J\b\u0010R\u001a\u00020\u000eH\u0002J\u0010\u0010S\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u0013H\u0002J\u0010\u0010U\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\u001fH\u0016J\u0098\u0001\u0010W\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\b\u0010,\u001a\u0004\u0018\u0001002\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001002\b\u00102\u001a\u0004\u0018\u0001002\u0006\u00103\u001a\u00020\n2\u0006\u0010X\u001a\u00020%2\u0006\u00107\u001a\u00020\u001f2\u0006\u0010Y\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006Z"},
   d2 = {"Lcom/hzbhd/canbus/car/_404/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastD0", "", "Ljava/lang/Integer;", "lastD1", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioAscii", "index", "band", "freq", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaSource", "d0", "d1", "d1t12", "set0x11Data", "set0x12Data", "set0x21Data", "set0x31Data", "set0x32Data", "set0x41Data", "set0x48Data", "set0x61Data", "set0xF0Data", "bytes", "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoInfoChange", "playMode", "duration", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;
   private Integer lastD0;
   private Integer lastD1;

   private final byte[] radioAscii(int var1, String var2, String var3) {
      byte[] var9;
      label62: {
         byte var4;
         int var5;
         int var6;
         byte[] var10;
         label63: {
            int var7 = var2.hashCode();
            var6 = 0;
            var5 = 0;
            switch (var7) {
               case 64901:
                  if (!var2.equals("AM1")) {
                     break label62;
                  }
                  break;
               case 64902:
                  if (!var2.equals("AM2")) {
                     break label62;
                  }
                  break;
               case 69706:
                  if (!var2.equals("FM1")) {
                     break label62;
                  }
                  break label63;
               case 69707:
                  if (!var2.equals("FM2")) {
                     break label62;
                  }
                  break label63;
               case 69708:
                  if (!var2.equals("FM3")) {
                     break label62;
                  }
                  break label63;
               default:
                  break label62;
            }

            var9 = var3.getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(var9, "this as java.lang.String).getBytes(charset)");
            var4 = (byte)var1;
            var5 = 10 - var9.length;
            var10 = new byte[var5];

            for(var1 = var6; var1 < var5; ++var1) {
               var10[var1] = 32;
            }

            var9 = ArraysKt.plus(ArraysKt.plus(new byte[]{0, var4}, var10), var9);
            return var9;
         }

         Double var8 = StringsKt.toDoubleOrNull(var3);
         Intrinsics.checkNotNull(var8);
         var9 = String.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(var8, 10)).getBytes(Charsets.US_ASCII);
         Intrinsics.checkNotNullExpressionValue(var9, "this as java.lang.String).getBytes(charset)");
         var4 = (byte)var1;
         var6 = 10 - var9.length;
         var10 = new byte[var6];

         for(var1 = var5; var1 < var6; ++var1) {
            var10[var1] = 32;
         }

         var9 = ArraysKt.plus(ArraysKt.plus(new byte[]{0, var4}, var10), var9);
         return var9;
      }

      var9 = new byte[0];
      return var9;
   }

   private final void sendMediaSource(int var1, int var2, byte[] var3) {
      label20: {
         Integer var4 = this.lastD0;
         if (var4 != null && var4 == var1) {
            var4 = this.lastD1;
            if (var4 != null && var4 == var2) {
               break label20;
            }
         }

         if (var3 == null) {
            var3 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(new byte[]{(byte)var1, (byte)var2}, 13, 0, 4, (Object)null);
            CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, var3));
         } else {
            var3 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(ArraysKt.plus(new byte[]{(byte)var1}, var3), 13, 0, 4, (Object)null);
            CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, var3));
         }
      }

      this.lastD0 = var1;
      this.lastD1 = var2;
   }

   // $FF: synthetic method
   static void sendMediaSource$default(MsgMgr var0, int var1, int var2, byte[] var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = null;
      }

      var0.sendMediaSource(var1, var2, var3);
   }

   private final void set0x11Data() {
      byte var1;
      Context var3;
      label40: {
         var3 = InitUtilsKt.getMContext();
         int var2 = this.getFrame()[4];
         var1 = 2;
         if (var2 != 0) {
            if (var2 == 1) {
               var1 = 7;
               break label40;
            }

            if (var2 == 2) {
               var1 = 8;
               break label40;
            }

            if (var2 == 4) {
               var1 = 3;
               break label40;
            }

            if (var2 == 5) {
               var1 = 14;
               break label40;
            }

            if (var2 == 6) {
               var1 = 15;
               break label40;
            }

            if (var2 == 8) {
               var1 = 21;
               break label40;
            }

            if (var2 == 9) {
               var1 = 20;
               break label40;
            }

            if (var2 == 11) {
               break label40;
            }
         }

         var1 = 0;
      }

      this.realKeyLongClick1(var3, var1, this.getFrame()[5]);
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void set0x12Data() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      this.updateDoorView(InitUtilsKt.getMContext());
   }

   private final void set0x21Data() {
      Context var3 = InitUtilsKt.getMContext();
      int[] var4 = this.getFrame();
      short var1 = 2;
      int var2 = var4[2];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 9) {
                  if (var2 != 32) {
                     if (var2 != 43) {
                        if (var2 != 51) {
                           if (var2 != 62) {
                              if (var2 != 69) {
                                 if (var2 != 70) {
                                    var1 = 0;
                                 } else {
                                    var1 = 8;
                                 }
                              } else {
                                 var1 = 7;
                              }
                           } else {
                              var1 = 49;
                           }
                        }
                     } else {
                        var1 = 52;
                     }
                  } else {
                     var1 = 128;
                  }
               } else {
                  var1 = 3;
               }
            } else {
               var1 = 20;
            }
         } else {
            var1 = 21;
         }
      } else {
         var1 = 1;
      }

      this.realKeyLongClick1(var3, var1, this.getFrame()[3]);
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 2);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2);
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      int var1 = this.getFrame()[6];
      if (var1 != 3) {
         if (var1 != 12) {
            if (var1 != 5) {
               if (var1 == 6) {
                  GeneralAirData.front_left_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      var1 = this.getFrame()[8];
      String var2;
      if (var1 != 254) {
         if (var1 != 255) {
            var2 = (double)var1 * 0.5 + " °C";
         } else {
            var2 = "High";
         }
      } else {
         var2 = "Low";
      }

      GeneralAirData.front_left_temperature = var2;
      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
   }

   private final void set0x32Data() {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]));
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_1");
      if (var2 != null) {
         String var1;
         if (this.getFrame()[3] == 0) {
            var1 = "电量充足";
         } else {
            var1 = "电量不足";
         }

         var2.setValue(var1);
      }

      DriverDataPageUiSet.Page.Item var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_2");
      if (var3 != null) {
         var3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5])));
      }

      var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_3");
      if (var3 != null) {
         var3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7])));
      }

      var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_4");
      if (var3 != null) {
         var3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[9])));
      }

      var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_5");
      if (var3 != null) {
         var3.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[11]) * 0.1, 10) + " L/100Km");
      }

      var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_6");
      if (var3 != null) {
         var3.setValue(String.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)com.hzbhd.canbus.car._361.MsgMgrKt.getAnotherMsbLsbResult(this.getFrame()[12], this.getFrame()[13], this.getFrame()[14]) * 0.1, 10)));
      }

      var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_7");
      if (var3 != null) {
         var3.setValue(String.valueOf(this.getFrame()[15]));
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x41Data() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(this.getFrame()[2]), set0x41Data$restrictValue(this.getFrame()[3]), set0x41Data$restrictValue(this.getFrame()[4]), set0x41Data$restrictValue(this.getFrame()[5]));
      RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(this.getFrame()[6]), 0, 0, set0x41Data$restrictValue(this.getFrame()[9]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private static final int set0x41Data$restrictValue(int var0) {
      int var1 = var0;
      if (var0 == 255) {
         var1 = 0;
      }

      return var1;
   }

   private final void set0x48Data() {
      List var2 = (List)(new ArrayList());

      for(int var1 = 0; var1 < 4; ++var1) {
         var2.add(new TireUpdateEntity(var1, 0, new String[]{this.getFrame()[var1 + 2] + " °C", com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)this.getFrame()[var1 + 4 + 2] * 0.1, 10) + " Bar"}));
      }

      GeneralTireData.dataList = var2;
      GeneralTireData.isHaveSpareTire = false;
      this.updateTirePressureActivity((Bundle)null);
   }

   private final void set0x61Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_1");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(this.getFrame()[2])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_2");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(this.getFrame()[2])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_3");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(this.getFrame()[2]))));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_4");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(this.getFrame()[2]))));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d1_1");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d1_2");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d1_3");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 3, 3)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d2_1");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 5, 3)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d2_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(this.getFrame()[4]))));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._404.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._404.UiMgr");
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 65) {
                  if (var3 != 72) {
                     if (var3 != 97) {
                        if (var3 != 240) {
                           if (var3 != 49) {
                              if (var3 == 50) {
                                 this.set0x32Data();
                              }
                           } else {
                              this.set0x31Data();
                           }
                        } else {
                           this.set0xF0Data(var2);
                        }
                     } else {
                        this.set0x61Data();
                     }
                  } else {
                     this.set0x48Data();
                  }
               } else {
                  this.set0x41Data();
               }
            } else {
               this.set0x21Data();
            }
         } else {
            this.set0x12Data();
         }
      } else {
         this.set0x11Data();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      if (var10) {
         var5 = var8;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, 0, 0, 0, 0, 0});
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

      sendMediaSource$default(this, var1, var18 ^ 1, (byte[])null, 4, (Object)null);
      Intrinsics.checkNotNull(var21);
      MsgMgrKt.sendTextInfo(146, var21, Charsets.UTF_16LE, 32, 10);
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

         Intrinsics.checkNotNull(var3);
         sendMediaSource$default(this, var6, 0, this.radioAscii(var1, var2, var3), 2, (Object)null);
      }

   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      sendMediaSource$default(this, 0, 0, (byte[])null, 6, (Object)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      sendMediaSource$default(this, var1, var16 ^ 1, (byte[])null, 4, (Object)null);
   }
}
