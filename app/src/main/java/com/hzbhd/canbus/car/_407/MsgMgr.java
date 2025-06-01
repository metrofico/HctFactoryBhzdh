package com.hzbhd.canbus.car._407;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
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
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016JÄ\u0001\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u001e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\"2\b\u0010(\u001a\u0004\u0018\u00010\u001e2\b\u0010)\u001a\u0004\u0018\u00010\u001e2\b\u0010*\u001a\u0004\u0018\u00010\u001e2\u0006\u0010+\u001a\u00020&H\u0016J6\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\u00152\b\u0010.\u001a\u0004\u0018\u00010\u001e2\b\u0010/\u001a\u0004\u0018\u00010\u001e2\b\u00100\u001a\u0004\u0018\u00010\u001e2\u0006\u00101\u001a\u00020\u0015H\u0016J\b\u00102\u001a\u00020\nH\u0002J\b\u00103\u001a\u00020\nH\u0002J\b\u00104\u001a\u00020\nH\u0002J\b\u00105\u001a\u00020\nH\u0002J\b\u00106\u001a\u00020\nH\u0002J\b\u00107\u001a\u00020\nH\u0002J\b\u00108\u001a\u00020\nH\u0002J\b\u00109\u001a\u00020\nH\u0002J\b\u0010:\u001a\u00020\nH\u0002J\b\u0010;\u001a\u00020\nH\u0002J\b\u0010<\u001a\u00020\nH\u0002J\b\u0010=\u001a\u00020\nH\u0002J\u0010\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u000fH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006@"},
   d2 = {"Lcom/hzbhd/canbus/car/_407/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "set0x11Data", "set0x12Data", "set0x21Data", "set0x31Data", "set0x32Data", "set0x41Data", "set0x45Data", "set0x48Data", "set0x78Data", "set0x87Data", "set0xE8Data", "set0xE9Data", "set0xF0Data", "bytes", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;

   private static final byte[] radioInfoChange$radioAscii(int var0, String var1, int var2, String var3) {
      byte var4;
      byte var5;
      int var6;
      byte[] var9;
      byte[] var10;
      label62: {
         label63: {
            int var7 = var1.hashCode();
            var6 = 0;
            var5 = 0;
            switch (var7) {
               case 64901:
                  if (!var1.equals("AM1")) {
                     break label63;
                  }
                  break;
               case 64902:
                  if (!var1.equals("AM2")) {
                     break label63;
                  }
                  break;
               case 69706:
                  if (var1.equals("FM1")) {
                     break label62;
                  }
                  break label63;
               case 69707:
                  if (var1.equals("FM2")) {
                     break label62;
                  }
                  break label63;
               case 69708:
                  if (var1.equals("FM3")) {
                     break label62;
                  }
               default:
                  break label63;
            }

            var9 = var3.getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(var9, "this as java.lang.String).getBytes(charset)");
            var4 = (byte)var0;
            var10 = new byte[1];

            for(var0 = var6; var0 < 1; ++var0) {
               var10[var0] = 32;
            }

            var9 = ArraysKt.plus(ArraysKt.plus(new byte[]{0, var4}, var10), var9);
            return MsgMgrKt.restrict(var9, var2, 32);
         }

         var9 = new byte[0];
         return MsgMgrKt.restrict(var9, var2, 32);
      }

      Double var8 = StringsKt.toDoubleOrNull(var3);
      Intrinsics.checkNotNull(var8);
      var9 = String.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(var8, 10)).getBytes(Charsets.US_ASCII);
      Intrinsics.checkNotNullExpressionValue(var9, "this as java.lang.String).getBytes(charset)");
      var4 = (byte)var0;
      var6 = var2 - 2 - 4 - var9.length;
      var10 = new byte[var6];

      for(var0 = var5; var0 < var6; ++var0) {
         var10[var0] = 32;
      }

      var9 = ArraysKt.plus(ArraysKt.plus(new byte[]{0, var4}, var10), var9);
      return MsgMgrKt.restrict(var9, var2, 32);
   }

   private final void set0x11Data() {
      int[] var3 = this.getFrame();
      short var1 = 3;
      this.updateSpeedInfo(var3[3]);
      Context var4 = InitUtilsKt.getMContext();
      int var2 = this.getFrame()[4];
      if (var2 != 8) {
         if (var2 != 9) {
            if (var2 != 12) {
               if (var2 != 24) {
                  switch (var2) {
                     case 1:
                        var1 = 7;
                        break;
                     case 2:
                        var1 = 8;
                     case 3:
                        break;
                     case 4:
                        var1 = 187;
                        break;
                     case 5:
                        var1 = 14;
                        break;
                     case 6:
                        var1 = 15;
                        break;
                     default:
                        var1 = 0;
                  }
               } else {
                  var1 = 151;
               }
            } else {
               var1 = 2;
            }
         } else {
            var1 = 20;
         }
      } else {
         var1 = 21;
      }

      this.realKeyLongClick1(var4, var1, this.getFrame()[5]);
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
      boolean var1 = true;
      GeneralDoorData.isShowSeatBelt = true;
      if (com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[4])) != 1) {
         var1 = false;
      }

      GeneralDoorData.isSeatBeltTie = var1;
      this.updateDoorView(InitUtilsKt.getMContext());
   }

   private final void set0x21Data() {
      Context var4 = InitUtilsKt.getMContext();
      int[] var3 = this.getFrame();
      short var1 = 2;
      int var2 = var3[2];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 43) {
                  if (var2 != 45) {
                     if (var2 != 69) {
                        if (var2 != 70) {
                           if (var2 != 95) {
                              if (var2 != 96) {
                                 var1 = 0;
                              } else {
                                 var1 = 198;
                              }
                           }
                        } else {
                           var1 = 8;
                        }
                     } else {
                        var1 = 7;
                     }
                  } else {
                     var1 = 59;
                  }
               } else {
                  var1 = 52;
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

      this.realKeyLongClick1(var4, var1, this.getFrame()[3]);
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.getFrame()[2]);
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
      int var1 = this.getFrame()[2];
      boolean var2 = false;
      boolean var4;
      if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 2) == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      GeneralAirData.ac = var4;
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      var1 = this.getFrame()[6];
      if (var1 != 2) {
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  if (var1 == 12) {
                     GeneralAirData.front_left_blow_window = true;
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
         GeneralAirData.front_left_blow_window = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      int var3 = this.getFrame()[8];
      String var5;
      if (var3 == 254) {
         var5 = "LOW_TEMP";
      } else if (var3 == 255) {
         var5 = "HIGH_TEMP";
      } else {
         boolean var6;
         if (1 <= var3 && var3 < 17) {
            var6 = true;
         } else {
            var6 = false;
         }

         if (var6) {
            var5 = "Level " + var3;
         } else {
            var6 = var2;
            if (36 <= var3) {
               var6 = var2;
               if (var3 < 65) {
                  var6 = true;
               }
            }

            if (var6) {
               var5 = (double)var3 * 0.5 + " °C";
            } else {
               var5 = "--";
            }
         }
      }

      GeneralAirData.front_left_temperature = var5;
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

   private final void set0x45Data() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 2);
      Integer var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = null;
            } else {
               var2 = 2;
            }
         } else {
            var2 = 1;
         }
      } else {
         var2 = 0;
      }

      ArrayList var4 = new ArrayList(3);

      for(var1 = 0; var1 < 3; ++var1) {
         PanoramicBtnUpdateEntity var3;
         if (var2 != null && var1 == var2) {
            var3 = new PanoramicBtnUpdateEntity(var1, true);
         } else {
            var3 = new PanoramicBtnUpdateEntity(var1, false);
         }

         var4.add(var3);
      }

      GeneralParkData.dataList = (List)var4;
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void set0x48Data() {
      GeneralTireData.isHaveSpareTire = false;
      List var1 = (List)(new ArrayList());
      var1.add(new TireUpdateEntity(0, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)this.getFrame()[4] * 0.1, 10) + " bar", this.getFrame()[9] - 40 + " °C"}));
      var1.add(new TireUpdateEntity(1, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)this.getFrame()[5] * 0.1, 10) + " bar", this.getFrame()[10] - 40 + " °C"}));
      var1.add(new TireUpdateEntity(2, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)this.getFrame()[6] * 0.1, 10) + " bar", this.getFrame()[11] - 40 + " °C"}));
      var1.add(new TireUpdateEntity(3, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo((double)this.getFrame()[7] * 0.1, 10) + " bar", this.getFrame()[12] - 40 + " °C"}));
      GeneralTireData.dataList = var1;
      this.updateTirePressureActivity((Bundle)null);
   }

   private final void set0x78Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x78_1");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(this.getFrame()[5])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x78_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 5, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x78_3");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 3, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x78_4");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x78_5");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x78_6");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 4, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x78_7");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 2, 2)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x87Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_1");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 6, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 3, 3)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_3");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(this.getFrame()[5])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_4");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[5])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_5");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(this.getFrame()[5]))));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_6");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_7");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_8");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_9");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_10");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_11");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 6, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_12");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 2, 3)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_13");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_14");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_15");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_16");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_17");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_18");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_19");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_20");
      if (var1 != null) {
         var1.setProgress(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[9], 4, 4));
         var1.setValue(String.valueOf(var1.getProgress()));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_x87_21");
      if (var1 != null) {
         var1.setProgress(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[9], 0, 4));
         var1.setValue(String.valueOf(var1.getProgress()));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0xE8Data() {
      Context var4 = InitUtilsKt.getMContext();
      int var1 = this.getFrame()[2];
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      if (var2) {
         this.enterAuxIn2();
      } else {
         this.exitAuxIn2();
      }

      Unit var3 = Unit.INSTANCE;
      this.forceReverse(var4, var2);
   }

   private final void set0xE9Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_xE9_1");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(this.getFrame()[4])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_xE9_2");
      if (var1 != null) {
         var1.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(this.getFrame()[4])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S407_xE9_3");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 4, 2)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._407.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._407.UiMgr");
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCanDifferentId(), 19});
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
                  if (var3 != 69) {
                     if (var3 != 72) {
                        if (var3 != 120) {
                           if (var3 != 135) {
                              if (var3 != 240) {
                                 if (var3 != 49) {
                                    if (var3 != 50) {
                                       if (var3 != 232) {
                                          if (var3 == 233) {
                                             this.set0xE9Data();
                                          }
                                       } else {
                                          this.set0xE8Data();
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
                              this.set0x87Data();
                           }
                        } else {
                           this.set0x78Data();
                        }
                     } else {
                        this.set0x48Data();
                     }
                  } else {
                     this.set0x45Data();
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
      Intrinsics.checkNotNull(var21);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(146, var21, Charsets.UTF_16BE, 32, 10);
      Intrinsics.checkNotNull(var22);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(147, var22, Charsets.UTF_16BE, 32, 10);
      Intrinsics.checkNotNull(var23);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(148, var23, Charsets.UTF_16BE, 32, 10);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var6 = 3;
      if (var2 != null) {
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
               if (!var2.equals("FM3")) {
                  return;
               }
               break;
            default:
               return;
         }

         var2 = this.getCurrentBand();
         Intrinsics.checkNotNullExpressionValue(var2, "currentBand");
         Intrinsics.checkNotNull(var3);
         byte[] var7 = radioInfoChange$radioAscii(var1, var2, 12, var3);
         CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111, var6}, var7));
      }

   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }
}
