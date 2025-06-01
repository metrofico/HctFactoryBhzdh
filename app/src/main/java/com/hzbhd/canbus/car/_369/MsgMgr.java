package com.hzbhd.canbus.car._369;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
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
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016Jp\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0012H\u0016J\b\u0010 \u001a\u00020\nH\u0002J\b\u0010!\u001a\u00020\nH\u0002J\b\u0010\"\u001a\u00020\nH\u0002J\b\u0010#\u001a\u00020\nH\u0002J\b\u0010$\u001a\u00020\nH\u0002J\b\u0010%\u001a\u00020\nH\u0002J\b\u0010&\u001a\u00020\nH\u0002J\b\u0010'\u001a\u00020\nH\u0002J\b\u0010(\u001a\u00020\nH\u0002J\b\u0010)\u001a\u00020\nH\u0002J\b\u0010*\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020\nH\u0002J\b\u0010,\u001a\u00020\nH\u0002J\b\u0010-\u001a\u00020\nH\u0002J\b\u0010.\u001a\u00020\nH\u0002J\b\u0010/\u001a\u00020\nH\u0002J\b\u00100\u001a\u00020\nH\u0002J\b\u00101\u001a\u00020\nH\u0002J\b\u00102\u001a\u00020\nH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u00063"},
   d2 = {"Lcom/hzbhd/canbus/car/_369/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "dateTimeRepCanbus", "bYearTotal", "", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "set0x11Data", "set0x12Data", "set0x21Data", "set0x31Data", "set0x32Data", "set0x33Data", "set0x34Data", "set0x35Data", "set0x36Data", "set0x39Data", "set0x3FData", "set0x41Data", "set0x65Data", "set0x66Data", "set0x67Data", "set0x70Data", "set0x77Data", "set0xC2Data", "set0xF0Data", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;

   private final void set0x11Data() {
      DriverDataPageUiSet.Page.Item var5 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S362_vehicleSpeedInfo_1");
      byte var2 = 3;
      if (var5 != null) {
         var5.setValue(this.getFrame()[3] + " km/h");
      }

      this.updateDriveDataActivity((Bundle)null);
      Context var8 = InitUtilsKt.getMContext();
      int var4 = this.getFrame()[4];
      byte var3 = 14;
      byte var1;
      if (var4 != 8) {
         if (var4 != 9) {
            if (var4 != 11) {
               if (var4 != 24) {
                  var1 = var3;
                  if (var4 != 64) {
                     var1 = var3;
                     switch (var4) {
                        case 0:
                        default:
                           var1 = 0;
                           break;
                        case 1:
                           var1 = 7;
                           break;
                        case 2:
                           var1 = 8;
                           break;
                        case 3:
                        case 4:
                           var1 = 3;
                        case 5:
                           break;
                        case 6:
                           var1 = 15;
                     }
                  }
               } else {
                  var1 = 63;
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

      this.realKeyLongClick1(var8, var1, this.getFrame()[5]);
      MsgMgr var9 = (MsgMgr)this;
      var4 = this.getFrame()[7];
      boolean var6 = true;
      boolean var7;
      if (var4 >= 0 && var4 < 21) {
         var7 = true;
      } else {
         var7 = false;
      }

      label90: {
         if (var7) {
            var1 = 1;
         } else {
            if (21 <= var4 && var4 < 41) {
               var7 = true;
            } else {
               var7 = false;
            }

            if (var7) {
               var1 = 2;
            } else {
               if (41 <= var4 && var4 < 61) {
                  var7 = true;
               } else {
                  var7 = false;
               }

               if (var7) {
                  var1 = var2;
               } else {
                  if (61 <= var4 && var4 < 81) {
                     var7 = true;
                  } else {
                     var7 = false;
                  }

                  if (var7) {
                     var1 = 4;
                  } else {
                     if (81 <= var4 && var4 < 101) {
                        var7 = var6;
                     } else {
                        var7 = false;
                     }

                     if (!var7) {
                        break label90;
                     }

                     var1 = 5;
                  }
               }
            }
         }

         this.setBacklightLevel(var1);
      }

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

   private final void set0x21Data() {
      Context var5 = InitUtilsKt.getMContext();
      int[] var4 = this.getFrame();
      byte var2 = 2;
      int var3 = var4[2];
      short var1;
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 6) {
                  if (var3 != 9) {
                     var1 = var2;
                     if (var3 != 32) {
                        if (var3 != 47) {
                           if (var3 != 43) {
                              var1 = var2;
                              if (var3 != 44) {
                                 var1 = 0;
                              }
                           } else {
                              var1 = 52;
                           }
                        } else {
                           var1 = 151;
                        }
                     }
                  } else {
                     var1 = 3;
                  }
               } else {
                  var1 = 50;
               }
            } else {
               var1 = 20;
            }
         } else {
            var1 = 21;
         }
      } else {
         var1 = 134;
      }

      this.realKeyLongClick1(var5, var1, this.getFrame()[3]);
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.getFrame()[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[3]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      int var1 = this.getFrame()[5];
      String var2;
      if (var1 != 254 && var1 != 255) {
         var2 = (double)var1 * 0.5 - (double)40 + " °C";
      } else {
         var2 = "--";
      }

      GeneralAirData.center_wheel = var2;
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      var1 = this.getFrame()[6];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     switch (var1) {
                        case 11:
                           GeneralAirData.front_left_blow_window = true;
                           break;
                        case 12:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_foot = true;
                           break;
                        case 13:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           break;
                        case 14:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
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
            GeneralAirData.front_defog = true;
         }
      } else {
         GeneralAirData.front_left_auto_wind = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      var1 = this.getFrame()[8];
      String var3 = "High";
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
      this.updateOutDoorTemp(InitUtilsKt.getMContext(), (double)this.getFrame()[13] * 0.5 - (double)40 + " °C");
      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
   }

   private final void set0x32Data() {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7]));
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_1");
      if (var2 != null) {
         var2.setValue(String.valueOf(DataHandleUtils.getBoolBit0(this.getFrame()[2])));
      }

      DriverDataPageUiSet.Page.Item var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_2");
      if (var3 != null) {
         int var1 = this.getFrame()[3];
         String var4;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        var4 = "--";
                     } else {
                        var4 = "S";
                     }
                  } else {
                     var4 = "D";
                  }
               } else {
                  var4 = "R";
               }
            } else {
               var4 = "N";
            }
         } else {
            var4 = "P";
         }

         var3.setValue(var4);
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_3");
      if (var2 != null) {
         var2.setValue(String.valueOf(this.getFrame()[4] * 256 + this.getFrame()[5]));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_4");
      if (var2 != null) {
         var2.setValue(String.valueOf(this.getFrame()[6] * 256 + this.getFrame()[7]));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_5");
      if (var2 != null) {
         var2.setValue(MsgMgrKt.accurateTo((double)this.getFrame()[8] * 0.1, 10) + " V");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_6");
      if (var2 != null) {
         var2.setValue(this.getFrame()[9] + " %");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_7");
      if (var2 != null) {
         var2.setValue(this.getFrame()[10] + " %");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_8");
      if (var2 != null) {
         var2.setValue((double)this.getFrame()[11] * 0.5 - (double)40 + " °C");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_9");
      if (var2 != null) {
         var2.setValue(this.getFrame()[12] * 256 - this.getFrame()[13] + " °C");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Data_10");
      if (var2 != null) {
         var2.setValue(String.valueOf(this.getFrame()[14] * 256 - this.getFrame()[13]));
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x33Data() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[4]);
      this.updateDoorView(InitUtilsKt.getMContext());
   }

   private final void set0x34Data() {
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_1");
      if (var2 != null) {
         var2.setValue(String.valueOf(MsgMgrKt.accurateTo((double)(this.getFrame()[6] * 256 * 256 + this.getFrame()[7] * 256 + this.getFrame()[8]) * 0.1, 10)));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_2");
      if (var2 != null) {
         var2.setValue(String.valueOf(MsgMgrKt.accurateTo((double)(this.getFrame()[9] * 256 + this.getFrame()[10]) * 0.1, 10)));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_3");
      if (var2 != null) {
         var2.setValue(String.valueOf(this.getFrame()[14] * 256 + this.getFrame()[15]));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_4");
      if (var2 != null) {
         var2.setValue("" + (this.getFrame()[19] * 256 + this.getFrame()[20]) + ' ');
      }

      DriverDataPageUiSet.Page.Item var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_5");
      String var4;
      if (var3 != null) {
         if (DataHandleUtils.getBoolBit2(this.getFrame()[24])) {
            var4 = "mile";
         } else {
            var4 = "km";
         }

         var3.setValue(var4);
      }

      var3 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_6");
      if (var3 != null) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[24], 0, 2);
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  var4 = "--";
               } else {
                  var4 = "l/100km";
               }
            } else {
               var4 = "km/l";
            }
         } else {
            var4 = "mpg";
         }

         var3.setValue(var4);
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x35Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Air_1");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 6, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Air_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Air_3");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 3, 1));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x36Data() {
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_1");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit7(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_2");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit6(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_3");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit5(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_4");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit4(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_5");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit3(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_6");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit2(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_7");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit1(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_8");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit0(this.getFrame()[2])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_9");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit7(this.getFrame()[3])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_10");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit6(this.getFrame()[3])));
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D369_Light_11");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit3(this.getFrame()[3])));
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void set0x39Data() {
   }

   private final void set0x3FData() {
   }

   private final void set0x41Data() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(7, set0x41Data$restrictValue(this.getFrame()[2]), set0x41Data$restrictValue(this.getFrame()[3]), set0x41Data$restrictValue(this.getFrame()[4]), set0x41Data$restrictValue(this.getFrame()[5]));
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

   private final void set0x65Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Door_1");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 6, 1));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Door_2");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 4, 2));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Door_3");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 2, 1)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x66Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_RemoteControl_1");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 7, 1)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x67Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_1");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(this.getFrame()[3]))));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_3");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_4");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_5");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_6");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_7");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[3])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_Light_8");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(this.getFrame()[3])));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x70Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_LightTime_1");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 4, 4)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S369_LightTime_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 4)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x77Data() {
   }

   private final void set0xC2Data() {
   }

   private final void set0xF0Data() {
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._369.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._369.UiMgr");
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCanDifferentId(), 13});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var7 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var7, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var7);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 57) {
                     if (var3 != 63) {
                        if (var3 != 65) {
                           if (var3 != 112) {
                              if (var3 != 119) {
                                 if (var3 != 194) {
                                    if (var3 != 240) {
                                       switch (var3) {
                                          case 49:
                                             this.set0x31Data();
                                             break;
                                          case 50:
                                             this.set0x32Data();
                                             break;
                                          case 51:
                                             this.set0x33Data();
                                             break;
                                          case 52:
                                             this.set0x34Data();
                                             break;
                                          case 53:
                                             this.set0x35Data();
                                             break;
                                          case 54:
                                             this.set0x36Data();
                                             break;
                                          default:
                                             switch (var3) {
                                                case 101:
                                                   this.set0x65Data();
                                                   break;
                                                case 102:
                                                   this.set0x66Data();
                                                   break;
                                                case 103:
                                                   this.set0x67Data();
                                             }
                                       }
                                    } else {
                                       this.set0xF0Data();
                                    }
                                 } else {
                                    this.set0xC2Data();
                                 }
                              } else {
                                 this.set0x77Data();
                              }
                           } else {
                              this.set0x70Data();
                           }
                        } else {
                           this.set0x41Data();
                        }
                     } else {
                        this.set0x3FData();
                     }
                  } else {
                     this.set0x39Data();
                  }
               } else {
                  byte var4 = var2[3];
                  byte var8 = MsgMgrKt.getLastKnobValue();
                  int var5 = Math.abs(var4 - var8);
                  int var6 = this.getFrame()[2];
                  if (var6 != 1) {
                     if (var6 == 2) {
                        if (var4 > var8) {
                           DataHandleUtils.knob(var1, 46, var5);
                        } else if (var4 < var8) {
                           DataHandleUtils.knob(var1, 45, var5);
                        }
                     }
                  } else if (var4 > var8) {
                     DataHandleUtils.knob(var1, 7, var5);
                  } else if (var4 < var8) {
                     DataHandleUtils.knob(var1, 8, var5);
                  }

                  MsgMgrKt.setLastKnobValue(var2[3]);
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
      byte var16 = (byte)var2;
      byte var14 = (byte)var3;
      byte var15 = (byte)var4;
      if (var10 != 0) {
         var5 = var8;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -53, var16, var14, var15, (byte)var5, (byte)var6, (byte)var10, 0, 0, 0, 0});
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

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }
}
