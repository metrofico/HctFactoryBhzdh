package com.hzbhd.canbus.car._314;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\b\u0010$\u001a\u00020\u001dH\u0002J\b\u0010%\u001a\u00020\u001dH\u0016JT\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u00162\b\u0010(\u001a\u0004\u0018\u00010\"2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00162\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00102\u001a\u00020\u001dH\u0002J\u001c\u00103\u001a\u00020\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016Jp\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00162\u0006\u00107\u001a\u00020\u00162\u0006\u00108\u001a\u00020\u00162\u0006\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u00162\u0006\u0010<\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\u00162\u0006\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020*2\u0006\u0010A\u001a\u00020\u0016H\u0016Jv\u0010B\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020\u00162\u0006\u0010E\u001a\u00020\u00162\u0006\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u00162\u0006\u0010H\u001a\u00020\u00162\u0006\u0010I\u001a\u00020\u00162\u0006\u0010J\u001a\u00020*2\u0006\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020\u00162\b\u0010M\u001a\u0004\u0018\u00010N2\b\u0010O\u001a\u0004\u0018\u00010N2\b\u0010P\u001a\u0004\u0018\u00010NH\u0016J\b\u0010Q\u001a\u00020\u001dH\u0002J\b\u0010R\u001a\u00020\u001dH\u0016JÄ\u0001\u0010S\u001a\u00020\u001d2\u0006\u0010T\u001a\u00020\u001b2\u0006\u0010U\u001a\u00020\u001b2\u0006\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u00020\u00162\u0006\u0010X\u001a\u00020\u001b2\u0006\u0010Y\u001a\u00020\u001b2\u0006\u0010Z\u001a\u00020\u001b2\u0006\u0010[\u001a\u00020\u001b2\u0006\u0010\\\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b2\b\u0010^\u001a\u0004\u0018\u00010N2\b\u0010_\u001a\u0004\u0018\u00010N2\b\u0010`\u001a\u0004\u0018\u00010N2\u0006\u0010a\u001a\u00020b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010c\u001a\u00020\u00162\u0006\u0010K\u001a\u00020*2\u0006\u0010d\u001a\u00020b2\b\u0010e\u001a\u0004\u0018\u00010N2\b\u0010f\u001a\u0004\u0018\u00010N2\b\u0010g\u001a\u0004\u0018\u00010N2\u0006\u0010h\u001a\u00020*H\u0016J\b\u0010i\u001a\u00020\u001dH\u0002J\u0010\u0010j\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010k\u001a\u00020\u001dH\u0002J6\u0010l\u001a\u00020\u001d2\u0006\u0010m\u001a\u00020\u00162\b\u0010n\u001a\u0004\u0018\u00010N2\b\u0010o\u001a\u0004\u0018\u00010N2\b\u0010p\u001a\u0004\u0018\u00010N2\u0006\u0010q\u001a\u00020\u0016H\u0016J\u001a\u0010r\u001a\u00020\u001d2\u0006\u0010s\u001a\u00020\u00162\b\b\u0002\u0010t\u001a\u00020\"H\u0002J\b\u0010u\u001a\u00020\u001dH\u0002J\u0010\u0010v\u001a\u00020\u001d2\u0006\u0010w\u001a\u00020\"H\u0002J\b\u0010x\u001a\u00020\u001dH\u0002J\b\u0010y\u001a\u00020\u001dH\u0002J\b\u0010z\u001a\u00020\u001dH\u0002J\u0010\u0010{\u001a\u00020\u001d2\u0006\u0010|\u001a\u00020*H\u0016J\b\u0010}\u001a\u00020\u001dH\u0002J\b\u0010~\u001a\u00020\u001dH\u0002J\u0006\u0010\u007f\u001a\u00020\u001dR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0080\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_314/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "mUiMgr", "Lcom/hzbhd/canbus/car/_314/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_314/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_314/UiMgr;)V", "x11D2", "", "Ljava/lang/Integer;", "x12", "x21D0", "x22D1", "", "afterServiceNormalSetting", "", "air", "airSettings", "amplifier", "canbusInfo", "", "angle", "anotherSetting", "auxInInfoChange", "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "button", "canbusInfoChange", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "discInfoChange", "playModel", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "", "album", "artist", "door", "dtvInfoChange", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panelBtn", "panelKnob", "radar", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendVoiceSource", "d0", "d1t12", "set0xA9Data", "set0xF0Data", "bytes", "setOliConsumptionData", "setOliConsumptionHistoryData", "setting", "sourceSwitchNoMediaInfoChange", "isPowerOff", "speed", "tyre", "updateSettingsData", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frame;
   public UiMgr mUiMgr;
   private Integer x11D2;
   private Integer x12;
   private Integer x21D0;
   private byte x22D1;

   private final void air() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 3);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 3, 2);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.getFrame()[3]);
      GeneralAirData.rear_lock = DataHandleUtils.getBoolBit1(this.getFrame()[3]);
      GeneralAirData.rear_auto = DataHandleUtils.getBoolBit0(this.getFrame()[3]);
      GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 3);
      boolean var2;
      if (this.getFrame()[5] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.climate = var2;
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 4, 4);
      if (var1 != 3) {
         if (var1 != 12) {
            if (var1 != 5) {
               if (var1 == 6) {
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_right_blow_foot = true;
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 0, 4);
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
      String var4 = "High";
      String var3;
      if (var1 != 254) {
         if (var1 != 255) {
            var3 = (float)var1 / 2.0F + " °C";
         } else {
            var3 = "High";
         }
      } else {
         var3 = "Low";
      }

      GeneralAirData.front_left_temperature = var3;
      var1 = this.getFrame()[9];
      if (var1 != 254) {
         if (var1 != 255) {
            var3 = (float)var1 / 2.0F + " °C";
         } else {
            var3 = "High";
         }
      } else {
         var3 = "Low";
      }

      GeneralAirData.front_right_temperature = var3;
      var1 = this.getFrame()[10];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_right_blow_head = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_right_blow_head = true;
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_right_blow_foot = true;
            }
         } else {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
         }
      } else {
         GeneralAirData.rear_auto_wind_model = true;
      }

      GeneralAirData.rear_wind_level = this.getFrame()[11];
      var1 = this.getFrame()[12];
      if (var1 != 254) {
         var3 = var4;
         if (var1 != 255) {
            var3 = (float)var1 / 2.0F + " °C";
         }
      } else {
         var3 = "Low";
      }

      GeneralAirData.rear_temperature = var3;
      this.updateAirActivity(this.getContext(), 1004);
      this.updateAirActivity(this.getContext(), 1003);
   }

   private final void airSettings() {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 4, 4);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 0, 4);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 7, 1);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 6, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 5, 1);
      SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_1");
      if (var6 != null) {
         var6.setProgress(var2 - 1);
         var6.setValue(String.valueOf(var2));
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_2");
      if (var6 != null) {
         var6.setProgress(var3 - 1);
         var6.setValue(String.valueOf(var3));
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_3");
      if (var6 != null) {
         var6.setValue(var5);
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_4");
      if (var6 != null) {
         var6.setValue(var6.getValueSrnArray().get(var1));
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_5");
      if (var6 != null) {
         var6.setValue(var4);
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void amplifier(byte[] var1) {
      int[] var6 = MsgMgrKt.toIntArray(var1);
      GeneralAmplifierData.volume = var6[2];
      GeneralAmplifierData.leftRight = var6[3];
      GeneralAmplifierData.frontRear = var6[4];
      GeneralAmplifierData.bandBass = var6[5] + 5;
      GeneralAmplifierData.bandMiddle = var6[6] + 5;
      GeneralAmplifierData.bandTreble = var6[7] + 5;
      this.updateAmplifierActivity((Bundle)null);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 4, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 3, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 0, 3);
      byte var5 = var1[9];
      SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_1");
      if (var7 != null) {
         var7.setValue(var4);
      }

      var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_2");
      if (var7 != null) {
         var7.setValue(var3);
      }

      var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_3");
      if (var7 != null) {
         var7.setProgress(var2);
         var7.setValue(String.valueOf(var2));
      }

      var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_4");
      if (var7 != null) {
         var7.setProgress(var5);
         var7.setValue(String.valueOf(var5));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void angle() {
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void anotherSetting() {
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_1");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 6, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_2");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 5, 1)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_3");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 1)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_4");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(this.getFrame()[3])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_5");
      int var1;
      if (var2 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 4, 4) - 1;
         var2.setProgress(var1);
         var2.setValue(String.valueOf(var1 * 10 + 200));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_6");
      if (var2 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 4) - 1;
         var2.setProgress(var1);
         var2.setValue(String.valueOf(var1 * 10 + 200));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_7");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_8");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_9");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_10");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_11");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit3(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_12");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit2(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_13");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_14");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(this.getFrame()[5])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_15");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[6])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_16");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(this.getFrame()[6])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("D314_Other_17");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(this.getFrame()[6])));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void button() {
      int var1 = this.getFrame()[4];
      Integer var2 = this.x11D2;
      if ((var2 == null || var1 != var2) && this.getFrame()[5] != 0) {
         var1 = this.getFrame()[4];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 != 12) {
                                 if (var1 != 15) {
                                    if (var1 == 16) {
                                       this.realKeyClick4(this.getContext(), 50);
                                    }
                                 } else {
                                    this.realKeyClick4(this.getContext(), 49);
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
      }

      this.x11D2 = this.getFrame()[4];
   }

   private final void door() {
      Integer var2 = this.x12;
      int var1 = this.getFrame()[4];
      if (var2 == null || var2 != var1) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
         this.updateDoorView(this.getContext());
      }

      this.x12 = this.getFrame()[4];
   }

   private final void panelBtn() {
      Integer var2 = this.x21D0;
      int var1 = this.getFrame()[2];
      if ((var2 == null || var2 != var1) && this.getFrame()[3] != 0) {
         label89: {
            label88: {
               var1 = this.getFrame()[2];
               if (var1 != 1) {
                  if (var1 == 6) {
                     this.realKeyClick4(this.getContext(), 50);
                     break label89;
                  }

                  if (var1 == 17) {
                     this.realKeyClick4(this.getContext(), 31);
                     break label89;
                  }

                  if (var1 == 37) {
                     break label88;
                  }

                  if (var1 == 44) {
                     this.realKeyClick4(this.getContext(), 2);
                     break label89;
                  }

                  if (var1 != 57) {
                     if (var1 == 67) {
                        this.realKeyClick4(this.getContext(), 30);
                        break label89;
                     }

                     if (var1 == 72) {
                        this.realKeyClick4(this.getContext(), 76);
                        break label89;
                     }

                     if (var1 != 32 && var1 != 33) {
                        if (var1 != 69) {
                           if (var1 != 70) {
                              if (var1 != 77) {
                                 if (var1 != 78) {
                                    switch (var1) {
                                       case 10:
                                          this.realKeyClick4(this.getContext(), 33);
                                          break label89;
                                       case 11:
                                          this.realKeyClick4(this.getContext(), 34);
                                          break label89;
                                       case 12:
                                          this.realKeyClick4(this.getContext(), 35);
                                          break label89;
                                       case 13:
                                          this.realKeyClick4(this.getContext(), 36);
                                          break label89;
                                       case 14:
                                          this.realKeyClick4(this.getContext(), 37);
                                          break label89;
                                       case 15:
                                          this.realKeyClick4(this.getContext(), 38);
                                          break label89;
                                       default:
                                          switch (var1) {
                                             case 22:
                                                this.realKeyClick4(this.getContext(), 49);
                                                break label89;
                                             case 23:
                                                this.realKeyClick4(this.getContext(), 45);
                                                break label89;
                                             case 24:
                                                this.realKeyClick4(this.getContext(), 46);
                                                break label89;
                                             case 25:
                                                this.realKeyClick4(this.getContext(), 47);
                                                break label89;
                                             case 26:
                                                this.realKeyClick4(this.getContext(), 48);
                                                break label89;
                                             case 27:
                                                this.realKeyClick4(this.getContext(), 47);
                                                this.realKeyClick4(this.getContext(), 45);
                                                break label89;
                                             case 28:
                                                this.realKeyClick4(this.getContext(), 48);
                                                this.realKeyClick4(this.getContext(), 45);
                                                break label89;
                                             case 29:
                                                this.realKeyClick4(this.getContext(), 47);
                                                this.realKeyClick4(this.getContext(), 46);
                                                break label89;
                                             case 30:
                                                this.realKeyClick4(this.getContext(), 48);
                                                this.realKeyClick4(this.getContext(), 46);
                                                break label89;
                                             default:
                                                switch (var1) {
                                                   case 63:
                                                      this.realKeyClick4(this.getContext(), 3);
                                                      break label89;
                                                   case 64:
                                                      this.realKeyClick4(this.getContext(), 19);
                                                      break label89;
                                                   case 65:
                                                      this.realKeyClick4(this.getContext(), 63);
                                                      break label89;
                                                   default:
                                                      switch (var1) {
                                                         case 86:
                                                            this.realKeyClick4(this.getContext(), 130);
                                                            break label89;
                                                         case 87:
                                                            this.realKeyClick4(this.getContext(), 48);
                                                            break label89;
                                                         case 88:
                                                            this.realKeyClick4(this.getContext(), 47);
                                                      }
                                                }
                                          }
                                    }
                                 } else {
                                    this.realKeyClick4(this.getContext(), 20);
                                 }
                              } else {
                                 this.realKeyClick4(this.getContext(), 21);
                              }
                           } else {
                              this.realKeyClick4(this.getContext(), 8);
                           }
                        } else {
                           this.realKeyClick4(this.getContext(), 7);
                        }
                        break label89;
                     }
                     break label88;
                  }
               }

               this.realKeyClick4(this.getContext(), 134);
               break label89;
            }

            this.realKeyClick4(this.getContext(), 128);
         }
      }

      this.x21D0 = this.getFrame()[2];
   }

   private final void panelKnob(byte[] var1) {
      byte var5 = var1[3];
      byte var3 = this.x22D1;
      int var2 = Math.abs(var5 - var3);
      int var4 = this.getFrame()[2];
      if (var4 != 1) {
         if (var4 == 2) {
            if (var5 > var3) {
               DataHandleUtils.knob(this.getContext(), 46, var2);
            } else if (var5 < var3) {
               DataHandleUtils.knob(this.getContext(), 45, var2);
            }
         }
      } else if (var5 > var3) {
         DataHandleUtils.knob(this.getContext(), 7, var2);
      } else if (var5 < var3) {
         DataHandleUtils.knob(this.getContext(), 8, var2);
      }

      this.x22D1 = var1[3];
   }

   private final void radar() {
      byte var1 = 1;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(4, radar$assignRadarProgress(this.getFrame()[6]), radar$assignRadarProgress(this.getFrame()[7]), radar$assignRadarProgress(this.getFrame()[8]), radar$assignRadarProgress(this.getFrame()[9]));
      RadarInfoUtil.setRearRadarLocationData(4, radar$assignRadarProgress(this.getFrame()[2]), radar$assignRadarProgress(this.getFrame()[3]), radar$assignRadarProgress(this.getFrame()[4]), radar$assignRadarProgress(this.getFrame()[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.getContext());
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_radar_4");
      if (var2 != null) {
         if (this.getFrame()[12] != 1) {
            var1 = 0;
         }

         var2.setValue(Integer.valueOf(var1));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private static final int radar$assignRadarProgress(int var0) {
      int var1 = var0;
      if (var0 == 255) {
         var1 = 0;
      }

      return var1;
   }

   private final void sendVoiceSource(int var1, byte[] var2) {
      var2 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(ArraysKt.plus(new byte[]{(byte)var1}, var2), 13, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, var2));
   }

   // $FF: synthetic method
   static void sendVoiceSource$default(MsgMgr var0, int var1, byte[] var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = new byte[0];
      }

      var0.sendVoiceSource(var1, var2);
   }

   private final void set0xA9Data() {
      OriginalCarDevicePageUiSet var3 = UiMgrFactory.getCanUiMgr(this.getContext()).getOriginalCarDevicePageUiSet(this.getContext());
      Intrinsics.checkNotNullExpressionValue(var3, "getCanUiMgr(context).get…rDevicePageUiSet(context)");
      String var2;
      if (DataHandleUtils.getBoolBit7(this.getFrame()[2])) {
         var2 = "Power on";
      } else {
         var2 = "Power off";
      }

      GeneralOriginalCarDeviceData.cdStatus = var2;
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 3);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     var2 = "";
                  } else {
                     var2 = "DVD";
                  }
               } else {
                  var2 = "AM";
               }
            } else {
               var2 = "AUX";
            }
         } else {
            var2 = "CD";
         }
      } else {
         var2 = "FM";
      }

      label56: {
         label55: {
            label54: {
               GeneralOriginalCarDeviceData.runningState = var2;
               var2 = GeneralOriginalCarDeviceData.runningState;
               if (var2 != null) {
                  var1 = var2.hashCode();
                  if (var1 != 2092) {
                     if (var1 != 2145) {
                        if (var1 != 2247) {
                           if (var1 == 68082 && var2.equals("DVD")) {
                              break label55;
                           }
                        } else if (var2.equals("FM")) {
                           break label54;
                        }
                     } else if (var2.equals("CD")) {
                        break label55;
                     }
                  } else if (var2.equals("AM")) {
                     break label54;
                  }
               }

               var3.getItems().clear();
               break label56;
            }

            set0xA9Data$setRadioPage(var3, this);
            break label56;
         }

         set0xA9Data$setCDPage(var3, this);
      }

      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private static final void set0xA9Data$setCDPage(OriginalCarDevicePageUiSet var0, MsgMgr var1) {
      var0.setRowBottomBtnAction(new String[]{"left", "play", "pause", "right"});
      List var2 = var0.getItems();
      var2.clear();
      var2.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Current_track", String.valueOf(var1.getFrame()[8])));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Total_repertoire", String.valueOf(var1.getFrame()[9])));
      var2.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_play_time", "" + DataHandleUtils.getMsbLsbResult(var1.getFrame()[6], var1.getFrame()[7]) + 's'));
   }

   private static final void set0xA9Data$setRadioPage(OriginalCarDevicePageUiSet var0, MsgMgr var1) {
      var0.setRowBottomBtnAction(new String[0]);
      List var3 = var0.getItems();
      var3.clear();
      StringBuilder var2 = (new StringBuilder()).append(DataHandleUtils.getMsbLsbResult(var1.getFrame()[3], var1.getFrame()[4]));
      String var4 = GeneralOriginalCarDeviceData.runningState;
      if (Intrinsics.areEqual((Object)var4, (Object)"FM")) {
         var4 = " MHZ";
      } else if (Intrinsics.areEqual((Object)var4, (Object)"AM")) {
         var4 = " KHZ";
      } else {
         var4 = "";
      }

      var3.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", var2.append(var4).toString()));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_preset", String.valueOf(var1.getFrame()[5])));
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   private final void setOliConsumptionData() {
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_1");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[2], this.getFrame()[3]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_2");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_3");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._361.MsgMgrKt.getAnotherMsbLsbResult(this.getFrame()[6], this.getFrame()[7], this.getFrame()[8]) + " km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_4");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[12], this.getFrame()[13]) + " km");
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setOliConsumptionHistoryData() {
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_1");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[2], this.getFrame()[3]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_2");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_3");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_4");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[9]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_5");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[11]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_6");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[12], this.getFrame()[13]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_7");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[14], this.getFrame()[15]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_8");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[16], this.getFrame()[17]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_9");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[18], this.getFrame()[19]) / 10.0F + " l/100km");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_10");
      if (var1 != null) {
         var1.setValue((float)DataHandleUtils.getMsbLsbResult(this.getFrame()[20], this.getFrame()[21]) / 10.0F + " l/100km");
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void setting() {
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_radar_1");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[2])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_radar_2");
      int var1;
      if (var2 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 4, 3);
         var2.setProgress(var1 - 1);
         var2.setValue(String.valueOf(var1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_radar_3");
      if (var2 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 1, 3);
         var2.setProgress(var1 - 1);
         var2.setValue(String.valueOf(var1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_radar_4");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(this.getFrame()[2])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting1_1");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[3])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting1_2");
      if (var2 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 4, 3);
         var2.setProgress(var1);
         var2.setValue(String.valueOf(var1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting1_3");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 1, 3)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting1_4");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(this.getFrame()[3])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting2_1");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[4])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting2_2");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(this.getFrame()[4])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting2_3");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(this.getFrame()[4])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting2_4");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 3, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting2_5");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit2(this.getFrame()[4])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting2_6");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(this.getFrame()[4])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting2_7");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(this.getFrame()[4])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_display_1");
      if (var2 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 6, 2);
         var2.setProgress(var1 - 1);
         var2.setValue(String.valueOf(var1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_display_2");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 4, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_display_3");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 2, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_display_4");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 0, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting3_1");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[6])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting3_2");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 5, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting3_3");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 3, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting3_4");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 2, 1)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting3_5");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(this.getFrame()[6])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting3_6");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(this.getFrame()[6])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting4_1");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(this.getFrame()[7])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting4_2");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(this.getFrame()[7])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting4_3");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(this.getFrame()[7])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting4_4");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(this.getFrame()[7])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting4_5");
      if (var2 != null) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 2, 2);
         var2.setProgress(var1 - 1);
         var2.setValue(String.valueOf(var1));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting4_6");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 0, 2)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting5_1");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(this.getFrame()[8])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting5_2");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 3, 1)));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting5_3");
      if (var2 != null) {
         var2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit2(this.getFrame()[8])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_setting5_4");
      if (var2 != null) {
         var2.setValue(var2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[8], 0, 2)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void speed() {
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7])));
      }

      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]));
   }

   private final void tyre() {
      List var5 = (List)(new ArrayList());
      double var3 = (double)this.getFrame()[4];
      double var1 = (double)100;
      var5.add(new TireUpdateEntity(0, 0, new String[]{String.valueOf(Math.rint(var3 * 0.1 * var1) / var1), "BAR"}));
      var5.add(new TireUpdateEntity(1, 0, new String[]{String.valueOf(Math.rint((double)this.getFrame()[5] * 0.1 * var1) / var1), "BAR"}));
      var5.add(new TireUpdateEntity(2, 0, new String[]{String.valueOf(Math.rint((double)this.getFrame()[6] * 0.1 * var1) / var1), "BAR"}));
      var5.add(new TireUpdateEntity(3, 0, new String[]{String.valueOf(Math.rint((double)this.getFrame()[7] * 0.1 * var1) / var1), "BAR"}));
      GeneralTireData.dataList = var5;
      this.updateTirePressureActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      GeneralTireData.isHaveSpareTire = false;
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._314.UiMgr");
      this.setMUiMgr((UiMgr)var2);
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)this.getMUiMgr(), (HashMap)null, 4, (Object)null);
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)this.getMUiMgr(), (HashMap)null, 4, (Object)null);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void auxInInfoChange() {
      sendVoiceSource$default(this, 12, (byte[])null, 2, (Object)null);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      sendVoiceSource$default(this, 10, (byte[])null, 2, (Object)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      if (var1 != null) {
         this.setContext(var1);
         if (var2 != null) {
            int[] var4 = this.getByteArrayToIntArray(var2);
            Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo ?: return)");
            this.setFrame(var4);
            int var3 = this.getFrame()[1];
            if (var3 != 17) {
               if (var3 != 18) {
                  if (var3 != 22) {
                     if (var3 != 23) {
                        if (var3 != 33) {
                           if (var3 != 34) {
                              if (var3 != 49) {
                                 if (var3 != 50) {
                                    if (var3 != 53) {
                                       if (var3 != 65) {
                                          if (var3 != 72) {
                                             if (var3 != 166) {
                                                if (var3 != 169) {
                                                   if (var3 != 240) {
                                                      if (var3 != 97) {
                                                         if (var3 == 98) {
                                                            this.anotherSetting();
                                                         }
                                                      } else {
                                                         this.setting();
                                                      }
                                                   } else {
                                                      this.set0xF0Data(var2);
                                                   }
                                                } else {
                                                   this.set0xA9Data();
                                                }
                                             } else {
                                                this.amplifier(var2);
                                             }
                                          } else {
                                             this.tyre();
                                          }
                                       } else {
                                          this.radar();
                                       }
                                    } else {
                                       this.airSettings();
                                    }
                                 } else {
                                    this.speed();
                                 }
                              } else {
                                 this.air();
                              }
                           } else {
                              this.panelKnob(var2);
                           }
                        } else {
                           this.panelBtn();
                        }
                     } else {
                        this.setOliConsumptionHistoryData();
                     }
                  } else {
                     this.setOliConsumptionData();
                  }
               } else {
                  this.door();
               }
            } else {
               this.button();
               this.angle();
            }

         }
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      if (var10 != 0) {
         var5 = var8;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, 0, 0, (byte)var5, (byte)var6, var10, var11, 0, 0, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      if (var11 != null) {
         byte[] var14 = var11.getBytes(Charsets.UTF_16LE);
         Intrinsics.checkNotNullExpressionValue(var14, "this as java.lang.String).getBytes(charset)");
         var14 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(var14, 32, 0, 4, (Object)null);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -88}, var14));
      }
   }

   public void dtvInfoChange() {
      sendVoiceSource$default(this, 8, (byte[])null, 2, (Object)null);
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

   public final UiMgr getMUiMgr() {
      UiMgr var1 = this.mUiMgr;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         return null;
      }
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 != 9) {
         var1 = 13;
      } else {
         var1 = 14;
      }

      sendVoiceSource$default(this, var1, (byte[])null, 2, (Object)null);
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
         this.sendVoiceSource(var6, com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(var1, var2, 8, var3));
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

   public final void setMUiMgr(UiMgr var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mUiMgr = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      sendVoiceSource$default(this, 0, (byte[])null, 2, (Object)null);
   }

   public final void updateSettingsData() {
      this.updateSettingActivity((Bundle)null);
   }
}
