package com.hzbhd.canbus.car._401;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u000fH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0019"},
   d2 = {"Lcom/hzbhd/canbus/car/_401/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x18Data", "set0x21Data", "set0x31Data", "set0x32Data", "set0x41Data", "set0xF0Data", "bytes", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;

   private final void set0x11Data() {
      Context var2;
      short var3;
      label33: {
         var2 = InitUtilsKt.getMContext();
         int var1 = this.getFrame()[4];
         if (var1 != 0) {
            if (var1 == 1) {
               var3 = 7;
               break label33;
            }

            if (var1 == 2) {
               var3 = 8;
               break label33;
            }

            if (var1 == 4) {
               var3 = 201;
               break label33;
            }

            if (var1 == 5) {
               var3 = 14;
               break label33;
            }

            switch (var1) {
               case 8:
                  var3 = 21;
                  break label33;
               case 9:
                  var3 = 20;
                  break label33;
               case 10:
                  var3 = 52;
                  break label33;
            }
         }

         var3 = 0;
      }

      this.realKeyLongClick1(var2, var3, this.getFrame()[5]);
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

   private final void set0x18Data() {
   }

   private final void set0x21Data() {
      Context var2 = InitUtilsKt.getMContext();
      short var1;
      if (this.getFrame()[2] == 1) {
         var1 = 134;
      } else {
         var1 = 0;
      }

      this.realKeyLongClick1(var2, var1, this.getFrame()[3]);
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.ion = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit0(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      MsgMgrKt.windDirectionInit();
      int var1 = this.getFrame()[6];
      if (var1 != 1) {
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
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_auto_wind = true;
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

      GeneralAirData.center_wheel = var2;
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
      RadarInfoUtil.setRearRadarLocationData(4, set0x41Data$restrictValue(this.getFrame()[2]), set0x41Data$restrictValue(this.getFrame()[3]), set0x41Data$restrictValue(this.getFrame()[4]), set0x41Data$restrictValue(this.getFrame()[5]));
      RadarInfoUtil.setFrontRadarLocationData(4, set0x41Data$restrictValue(this.getFrame()[6]), 0, 0, set0x41Data$restrictValue(this.getFrame()[9]));
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

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._401.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var7 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var7, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var7);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 24) {
               if (var3 != 65) {
                  if (var3 != 240) {
                     if (var3 != 33) {
                        if (var3 != 34) {
                           if (var3 != 49) {
                              if (var3 == 50) {
                                 this.set0x32Data();
                              }
                           } else {
                              this.set0x31Data();
                           }
                        } else {
                           byte var4 = var2[3];
                           byte var8 = com.hzbhd.canbus.car._369.MsgMgrKt.getLastKnobValue();
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

                           com.hzbhd.canbus.car._369.MsgMgrKt.setLastKnobValue(var2[3]);
                        }
                     } else {
                        this.set0x21Data();
                     }
                  } else {
                     this.set0xF0Data(var2);
                  }
               } else {
                  this.set0x41Data();
               }
            } else {
               this.set0x18Data();
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

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }
}
