package com.hzbhd.canbus.car._403;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
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
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016JT\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001c\u0010&\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010'\u001a\u0004\u0018\u00010\u001dH\u0016Jp\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u0004H\u0016JÄ\u0001\u00106\u001a\u00020\u00162\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u0002082\u0006\u0010=\u001a\u0002082\u0006\u0010>\u001a\u0002082\u0006\u0010?\u001a\u0002082\u0006\u0010@\u001a\u0002082\u0006\u0010A\u001a\u0002082\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u00010C2\b\u0010E\u001a\u0004\u0018\u00010C2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u0002082\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00112\u0006\u0010K\u001a\u00020G2\b\u0010L\u001a\u0004\u0018\u00010C2\b\u0010M\u001a\u0004\u0018\u00010C2\b\u0010N\u001a\u0004\u0018\u00010C2\u0006\u0010O\u001a\u00020\u0011H\u0016J6\u0010P\u001a\u00020\u00162\u0006\u0010Q\u001a\u00020\u00042\b\u0010R\u001a\u0004\u0018\u00010C2\b\u0010S\u001a\u0004\u0018\u00010C2\b\u0010T\u001a\u0004\u0018\u00010C2\u0006\u0010U\u001a\u00020\u0004H\u0016J\u0010\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u00020\u0004H\u0002J\b\u0010X\u001a\u00020\u0016H\u0002J\b\u0010Y\u001a\u00020\u0016H\u0002J\b\u0010Z\u001a\u00020\u0016H\u0002J\b\u0010[\u001a\u00020\u0016H\u0002J\b\u0010\\\u001a\u00020\u0016H\u0002J\b\u0010]\u001a\u00020\u0016H\u0002J\b\u0010^\u001a\u00020\u0016H\u0002J\b\u0010_\u001a\u00020\u0016H\u0002J\b\u0010`\u001a\u00020\u0016H\u0002J\b\u0010a\u001a\u00020\u0016H\u0002J\u0010\u0010b\u001a\u00020\u00162\u0006\u0010c\u001a\u00020\u001dH\u0002J\u0010\u0010d\u001a\u00020\u00162\u0006\u0010e\u001a\u00020\u0011H\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006f"},
   d2 = {"Lcom/hzbhd/canbus/car/_403/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "dateFormat", "", "getDateFormat", "()Ljava/lang/Integer;", "setDateFormat", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "isReverse", "", "()Z", "setReverse", "(Z)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "auxInInfoChange", "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "", "isHfpConnected", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaSource", "d0", "set0x11Data", "set0x12Data", "set0x26Data", "set0x31Data", "set0x32Data", "set0x41Data", "set0x66Data", "set0x67Data", "set0x68Data", "set0xE8Data", "set0xF0Data", "bytes", "sourceSwitchNoMediaInfoChange", "isPowerOff", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private Integer dateFormat;
   public int[] frame;
   private boolean isReverse;

   private final void sendMediaSource(int var1) {
      byte[] var2 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(new byte[]{(byte)var1}, 13, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, var2));
   }

   private final void set0x11Data() {
      byte var1;
      Context var3;
      label35: {
         var3 = InitUtilsKt.getMContext();
         int var2 = this.getFrame()[4];
         var1 = 3;
         if (var2 != 101) {
            switch (var2) {
               case 0:
                  break;
               case 1:
                  var1 = 7;
                  break label35;
               case 2:
                  var1 = 8;
               case 3:
               case 4:
                  break label35;
               case 5:
                  var1 = 14;
                  break label35;
               case 6:
                  var1 = 15;
                  break label35;
               default:
                  switch (var2) {
                     case 8:
                        var1 = 21;
                        break label35;
                     case 9:
                        var1 = 20;
                        break label35;
                     case 10:
                        var1 = 52;
                        break label35;
                     case 11:
                        var1 = 2;
                        break label35;
                     default:
                        switch (var2) {
                           case 13:
                              var1 = 45;
                              break label35;
                           case 14:
                              var1 = 46;
                              break label35;
                           case 15:
                              var1 = 49;
                              break label35;
                           case 16:
                              var1 = 50;
                              break label35;
                        }
                  }
            }
         } else {
            this.forceReverse(InitUtilsKt.getMContext(), this.isReverse ^ true);
            this.isReverse ^= true;
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
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[4]);
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.getFrame()[4]);
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.getFrame()[4]);
      this.updateDoorView(InitUtilsKt.getMContext());
   }

   private final void set0x26Data() {
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.clean_air = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 2);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2);
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      int var1 = this.getFrame()[6];
      if (var1 != 3) {
         if (var1 != 5) {
            if (var1 != 6) {
               if (var1 != 11) {
                  if (var1 == 12) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      var1 = this.getFrame()[8];
      String var3 = "High";
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
      var1 = this.getFrame()[9];
      if (var1 != 254) {
         var2 = var3;
         if (var1 != 255) {
            var2 = (double)var1 * 0.5 + " °C";
         }
      } else {
         var2 = "Low";
      }

      GeneralAirData.front_right_temperature = var2;
      this.updateOutDoorTemp(InitUtilsKt.getMContext(), this.getFrame()[13] + " °C");
      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
   }

   private final void set0x32Data() {
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7])));
      }

      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]));
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x41Data() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(this.getFrame()[2]), set0x41Data$restrictValue(this.getFrame()[3]), set0x41Data$restrictValue(this.getFrame()[4]), set0x41Data$restrictValue(this.getFrame()[5]));
      RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(this.getFrame()[6]), set0x41Data$restrictValue(this.getFrame()[7]), set0x41Data$restrictValue(this.getFrame()[8]), set0x41Data$restrictValue(this.getFrame()[9]));
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

   private final void set0x66Data() {
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x66_1");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getBoolBit7(this.getFrame()[3]));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x66_2");
      if (var2 != null) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 4, 3);
         var2.setProgress(var1);
         var2.setValue(String.valueOf(var1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x66_3");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getBoolBit2(this.getFrame()[3]));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x66_4");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getBoolBit1(this.getFrame()[3]));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x66_5");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getBoolBit0(this.getFrame()[3]));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x67Data() {
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_1");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 5, 3)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_2");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 3)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_3");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 1, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_4");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 1)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_5");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 7, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_6");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 6, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_7");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 5, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_8");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 4, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_9");
      if (var2 != null) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 2, 2);
         var2.setProgress(var1);
         var2.setValue(String.valueOf(var1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_10");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 1, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_11");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 0, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_12");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 2, 1)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_13");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 1, 1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x67_14");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 0, 1));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x68Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x68_1");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getBoolBit5(this.getFrame()[3]));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x68_2");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getBoolBit4(this.getFrame()[3]));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x68_3");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getBoolBit3(this.getFrame()[3]));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x68_4");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getBoolBit2(this.getFrame()[3]));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("403_0x68_5");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 2)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0xE8Data() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 2);
      if (var1 != 1) {
         if (var1 != 2) {
            new PanoramicBtnUpdateEntity(5, false);
            new PanoramicBtnUpdateEntity(6, false);
         } else {
            new PanoramicBtnUpdateEntity(5, true);
            new PanoramicBtnUpdateEntity(6, true);
         }
      } else {
         new PanoramicBtnUpdateEntity(5, true);
         new PanoramicBtnUpdateEntity(6, false);
      }

      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._403.UiMgr");
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._403.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
   }

   public void auxInInfoChange() {
      this.sendMediaSource(12);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      this.sendMediaSource(10);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 38) {
               if (var3 != 65) {
                  if (var3 != 232) {
                     if (var3 != 240) {
                        if (var3 != 49) {
                           if (var3 != 50) {
                              switch (var3) {
                                 case 102:
                                    this.set0x66Data();
                                    break;
                                 case 103:
                                    this.set0x67Data();
                                    break;
                                 case 104:
                                    this.set0x68Data();
                              }
                           } else {
                              this.set0x32Data();
                           }
                        } else {
                           this.set0x31Data();
                        }
                     } else {
                        this.set0xF0Data(var2);
                     }
                  } else {
                     this.set0xE8Data();
                  }
               } else {
                  this.set0x41Data();
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

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      if (var10 != 0) {
         var5 = var8;
      }

      byte var16 = (byte)var5;
      byte var18 = (byte)var6;
      byte var15 = (byte)var10;
      byte var17 = (byte)var2;
      byte var14 = (byte)var3;
      byte var19 = (byte)var4;
      Integer var20 = this.dateFormat;
      if (var20 != null) {
         CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, var16, var18, 0, 0, var15, var17, var14, var19, (byte)var20});
      }

   }

   public final Integer getDateFormat() {
      return this.dateFormat;
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

   public final boolean isReverse() {
      return this.isReverse;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      this.sendMediaSource(var1);
      Intrinsics.checkNotNull(var21);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(146, var21, Charsets.UTF_16BE, 32, 10);
      Intrinsics.checkNotNull(var23);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(146, var23, Charsets.UTF_16BE, 32, 10);
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

         this.sendMediaSource(var6);
      }

   }

   public final void setDateFormat(Integer var1) {
      this.dateFormat = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public final void setReverse(boolean var1) {
      this.isReverse = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      this.sendMediaSource(0);
   }
}
