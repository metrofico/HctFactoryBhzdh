package com.hzbhd.canbus.car._168;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._369.MsgMgrKt;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""},
   d2 = {"Lcom/hzbhd/canbus/car/_168/SingletonForKt;", "", "()V", "lastKnobValue", "", "init", "", "context", "Landroid/content/Context;", "uiMgr", "Lcom/hzbhd/canbus/car/_168/UiMgr;", "sendCarData", "d0", "", "set0x11Data", "frame", "", "mgr", "Lcom/hzbhd/canbus/car/_168/MsgMgr;", "set0x12Data", "set0x21Data", "set0x22Data", "canbusInfo", "", "set0x31Data", "set0x35Data", "set0x56Data", "set0x67Data", "setAirListener", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "setSettingsListener", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SingletonForKt {
   public static final SingletonForKt INSTANCE = new SingletonForKt();
   private static byte lastKnobValue;

   // $FF: synthetic method
   public static void $r8$lambda$3u19OSqVHAGrt0cilhLPltzC_M8(RearArea var0, int var1) {
      setAirListener$lambda_15$lambda_14$lambda_11(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$3u_yLR9Lz0byNNoqkccnXOMMfPg(SettingPageUiSet var0, int var1, int var2, int var3) {
      setSettingsListener$lambda_1(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$7KZ7OIP_E9_M8zeqCJI_tcBkAhI(FrontArea var0, int var1) {
      setAirListener$lambda_15$lambda_9$lambda_6(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$CbbqnXFg3JH6ArfQRrBtXBYpEsc(RearArea var0, int var1) {
      setAirListener$lambda_15$lambda_14$lambda_13(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$DOS08EzR2JcYWFfpOYK4lcxsWvY(RearArea var0, int var1) {
      setAirListener$lambda_15$lambda_14$lambda_10(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$PPBTmEdLscGmjSk4ak_tb3BtYYA(FrontArea var0, int var1) {
      setAirListener$lambda_15$lambda_9$lambda_5(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$VlJZWGjMn77mXr5Qt5YHdAES2v0(FrontArea var0, int var1) {
      setAirListener$lambda_15$lambda_9$lambda_7(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$cV5dCuIBuvHYJFOs0X0Q6__aLrE(RearArea var0, int var1) {
      setAirListener$lambda_15$lambda_14$lambda_12(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$kzH41fD60bOTlBOowaInAtBBYbY(FrontArea var0, int var1) {
      setAirListener$lambda_15$lambda_9$lambda_8(var0, var1);
   }

   private SingletonForKt() {
   }

   private static final String set0x12Data$setOnOff(boolean var0) {
      String var1;
      if (var0) {
         var1 = "On";
      } else {
         var1 = "Off";
      }

      return var1;
   }

   private static final void setAirListener$lambda_15$lambda_14$lambda_10(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[0][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$lambda_15$lambda_14$lambda_11(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[1][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$lambda_15$lambda_14$lambda_12(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[2][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$lambda_15$lambda_14$lambda_13(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[3][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$lambda_15$lambda_9$lambda_5(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[0][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$lambda_15$lambda_9$lambda_6(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[1][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$lambda_15$lambda_9$lambda_7(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[2][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$lambda_15$lambda_9$lambda_8(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[3][it]");
      setAirListener$select(var2);
   }

   private static final void setAirListener$select(String var0) {
      switch (var0) {
         case "ac_auto":
            setAirListener$send_4$default(4, 0, 2, (Object)null);
            break;
         case "rear_lock":
            setAirListener$send_4$default(24, 0, 2, (Object)null);
            break;
         case "rear_sync":
            setAirListener$send_4$default(25, 0, 2, (Object)null);
            break;
         case "rear_power":
            setAirListener$send_4$default(23, 0, 2, (Object)null);
            break;
         case "auto_cycle":
            setAirListener$send_4(7, 2);
            break;
         case "power":
            setAirListener$send_4(1, GeneralAirData.power ^ 1);
            break;
         case "in_out_cycle":
            setAirListener$send_4(7, GeneralAirData.in_out_cycle ^ 1);
            break;
         case "hybrid_ac":
            setAirListener$send_4$default(2, 0, 2, (Object)null);
      }

   }

   private static final void setAirListener$send_4(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var0, (byte)var1});
   }

   // $FF: synthetic method
   static void setAirListener$send_4$default(int var0, int var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = 255;
      }

      setAirListener$send_4(var0, var1);
   }

   private static final void setSettingsListener$lambda_1(SettingPageUiSet var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "$mSettingPageUiSet");
      if (var4 != null) {
         switch (var4) {
            case "S168_x35_1":
               setSettingsListener$lambda_1$anotherSend(var3, 15);
               break;
            case "S168_x56_1":
               setSettingsListener$lambda_1$send(var3, 8);
               break;
            case "S168_x56_2":
               setSettingsListener$lambda_1$send(var3, 9);
         }
      }

   }

   private static final void setSettingsListener$lambda_1$anotherSend(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var1, (byte)var0});
   }

   private static final void setSettingsListener$lambda_1$send(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 90, (byte)var1, (byte)var0});
   }

   public final void init(Context var1, UiMgr var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "uiMgr");
      AbstractUiMgr var3 = (AbstractUiMgr)var2;
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, var3, (HashMap)null, 4, (Object)null);
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, var3, (HashMap)null, 4, (Object)null);
   }

   public final void sendCarData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte)var1, 0});
   }

   public final void set0x11Data(int[] var1, MsgMgr var2) {
      Intrinsics.checkNotNullParameter(var1, "frame");
      Intrinsics.checkNotNullParameter(var2, "mgr");
      SingletonForKt var7 = (SingletonForKt)this;
      int var6 = var1[7];
      byte var5 = 1;
      boolean var4 = true;
      boolean var3;
      if (var6 >= 0 && var6 < 21) {
         var3 = true;
      } else {
         var3 = false;
      }

      byte var8;
      if (var3) {
         var8 = var5;
      } else {
         if (21 <= var6 && var6 < 41) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            var8 = 2;
         } else {
            if (41 <= var6 && var6 < 61) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (var3) {
               var8 = 3;
            } else {
               if (61 <= var6 && var6 < 81) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (var3) {
                  var8 = 4;
               } else {
                  if (81 <= var6 && var6 < 101) {
                     var3 = var4;
                  } else {
                     var3 = false;
                  }

                  if (!var3) {
                     return;
                  }

                  var8 = 5;
               }
            }
         }
      }

      var2.setBacklightLevel(var8);
   }

   public final void set0x12Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "frame");
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("instaneous_fuel_consumption");
      if (var2 != null) {
         var2.setValue("" + var1[5] + '.' + var1[6]);
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("right_rear_window");
      if (var2 != null) {
         var2.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit3(var1[8])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("left_rear_window");
      if (var2 != null) {
         var2.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit2(var1[8])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("left_front_window");
      if (var2 != null) {
         var2.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit1(var1[8])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("right_front_window");
      if (var2 != null) {
         var2.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit0(var1[8])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("battery_voltage");
      if (var2 != null) {
         var2.setValue(MsgMgrKt.accurateTo((double)var1[9] * 0.1, 10) + " V");
      }

   }

   public final int set0x21Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "frame");
      short var2 = 2;
      switch (var1[2]) {
         case 1:
            var2 = 1;
            break;
         case 2:
         case 27:
            var2 = 21;
            break;
         case 3:
         case 30:
            var2 = 20;
            break;
         case 4:
            var2 = 185;
            break;
         case 5:
            var2 = 187;
            break;
         case 6:
            var2 = 50;
            break;
         case 7:
            var2 = 62;
            break;
         case 8:
         case 31:
            var2 = 141;
            break;
         case 9:
            var2 = 3;
            break;
         case 10:
            var2 = 33;
            break;
         case 11:
            var2 = 34;
            break;
         case 12:
            var2 = 35;
            break;
         case 13:
            var2 = 36;
            break;
         case 14:
            var2 = 37;
            break;
         case 15:
            var2 = 38;
            break;
         case 16:
            var2 = 95;
            break;
         case 17:
            var2 = 31;
            break;
         case 18:
            var2 = 182;
            break;
         case 19:
            var2 = 196;
            break;
         case 20:
            var2 = 5;
            break;
         case 21:
            var2 = 75;
            break;
         case 22:
         case 42:
            var2 = 49;
            break;
         case 23:
            var2 = 45;
            break;
         case 24:
            var2 = 46;
            break;
         case 25:
            var2 = 47;
            break;
         case 26:
            var2 = 48;
            break;
         case 28:
            var2 = 66;
            break;
         case 29:
            var2 = 65;
            break;
         case 32:
            var2 = 128;
            break;
         case 33:
            var2 = 130;
            break;
         case 34:
            var2 = 150;
            break;
         case 35:
            var2 = 58;
            break;
         case 36:
            var2 = 4113;
            break;
         case 37:
            var2 = 27;
            break;
         case 38:
            var2 = 53;
            break;
         case 39:
            var2 = 148;
            break;
         case 40:
            var2 = 188;
            break;
         case 41:
            var2 = 94;
            break;
         case 43:
            var2 = 52;
         case 44:
            break;
         case 45:
            var2 = 151;
            break;
         default:
            var2 = 0;
      }

      return var2;
   }

   public final void set0x22Data(byte[] var1, Context var2) {
      Intrinsics.checkNotNullParameter(var1, "canbusInfo");
      Intrinsics.checkNotNullParameter(var2, "context");
      byte var5 = var1[3];
      byte var3 = lastKnobValue;
      int var6 = Math.abs(var5 - var3);
      byte var4 = var1[2];
      if (var4 == 1) {
         if (var5 > var3) {
            DataHandleUtils.knob(var2, 7, var6);
         } else if (var5 < var3) {
            DataHandleUtils.knob(var2, 8, var6);
         }
      } else if (var4 == 2) {
         if (var5 > var3) {
            DataHandleUtils.knob(var2, 46, var6);
         } else if (var5 < var3) {
            DataHandleUtils.knob(var2, 45, var6);
         }
      }

      lastKnobValue = var1[3];
   }

   public final void set0x31Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "frame");
      GeneralAirData.power = DataHandleUtils.getBoolBit6(var1[2]);
      GeneralAirData.ac_auto = DataHandleUtils.getBoolBit4(var1[2]);
      boolean var4;
      if (DataHandleUtils.getIntFromByteWithBit(var1[2], 2, 2) == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      GeneralAirData.sync = var4;
      GeneralAirData.hybrid_ac = DataHandleUtils.getBoolBit0(var1[2]);
      if (DataHandleUtils.getBoolBit7(var1[3])) {
         GeneralAirData.right_set_seat_cold = true;
         GeneralAirData.right_set_seat_heat = false;
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 2, 2);
         GeneralAirData.front_right_seat_heat_level = 0;
      } else {
         GeneralAirData.right_set_seat_heat = true;
         GeneralAirData.right_set_seat_cold = false;
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 2, 2);
         GeneralAirData.front_right_seat_cold_level = 0;
      }

      if (DataHandleUtils.getBoolBit6(var1[3])) {
         GeneralAirData.left_set_seat_cold = true;
         GeneralAirData.left_set_seat_heat = false;
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 2);
         GeneralAirData.front_left_seat_heat_level = 0;
      } else {
         GeneralAirData.left_set_seat_heat = true;
         GeneralAirData.left_set_seat_cold = false;
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 2);
         GeneralAirData.front_left_seat_cold_level = 0;
      }

      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(var1[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(var1[3]) ^ true;
      GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(var1[3]);
      GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(var1[4]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(var1[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var1[4]);
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      int var2 = var1[6];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 5) {
                  if (var2 != 6) {
                     switch (var2) {
                        case 11:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           break;
                        case 12:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_foot = true;
                           break;
                        case 13:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_head = true;
                           break;
                        case 14:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
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
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
         }
      } else {
         GeneralAirData.front_left_auto_wind = true;
         GeneralAirData.front_right_auto_wind = true;
      }

      int var3 = var1[7];
      boolean var8;
      if (var3 >= 0 && var3 < 19) {
         var8 = true;
      } else {
         var8 = false;
      }

      if (var8) {
         GeneralAirData.front_wind_level = var3;
         GeneralAirData.front_auto_wind_speed = false;
      } else {
         GeneralAirData.front_auto_wind_speed = true;
      }

      var2 = var1[8];
      String var6 = "High";
      String var5;
      if (var2 != 254) {
         if (var2 != 255) {
            var5 = (double)var1[8] * 0.5 + " °C";
         } else {
            var5 = "High";
         }
      } else {
         var5 = "Low";
      }

      GeneralAirData.front_left_temperature = var5;
      var2 = var1[9];
      if (var2 != 254) {
         var5 = var6;
         if (var2 != 255) {
            var5 = (double)var1[9] * 0.5 + " °C";
         }
      } else {
         var5 = "Low";
      }

      GeneralAirData.front_right_temperature = var5;
      GeneralAirData.rear_power = DataHandleUtils.getBoolBit5(var1[2]);
      GeneralAirData.rear_lock = DataHandleUtils.getBoolBit1(var1[3]);
      GeneralAirData.rear_sync = DataHandleUtils.getBoolBit0(var1[3]);
      var2 = var1[10];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
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
         GeneralAirData.rear_left_auto_wind = true;
         GeneralAirData.rear_right_auto_wind = true;
      }

      GeneralAirData.rear_wind_level = var1[11];
      if (var1[11] == 32) {
         var4 = true;
      } else {
         var4 = false;
      }

      GeneralAirData.rear_auto_wind_speed = var4;
      var2 = var1[12];
      String var7;
      if (var2 != 254) {
         if (var2 != 255) {
            var7 = (double)var2 * 0.5 + " °C";
         } else {
            var7 = "HIGH";
         }
      } else {
         var7 = "LOW";
      }

      GeneralAirData.rear_left_temperature = var7;
   }

   public final void set0x35Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "frame");
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S168_x35_1");
      if (var2 != null) {
         var2.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(var1[6])));
      }

   }

   public final void set0x56Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "frame");
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S168_x56_2");
      if (var2 != null) {
         var2.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(var1[3])));
      }

   }

   public final void set0x67Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "frame");
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S168_x67_1");
      if (var2 != null) {
         var2.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(var1[3])));
      }

      var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S168_x67_2");
      if (var2 != null) {
         var2.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(var1[3])));
      }

   }

   public final void setAirListener(AirPageUiSet var1) {
      Intrinsics.checkNotNullParameter(var1, "airPageUiSet");
      FrontArea var2 = var1.getFrontArea();
      var2.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new SingletonForKt$$ExternalSyntheticLambda0(var2), new SingletonForKt$$ExternalSyntheticLambda1(var2), new SingletonForKt$$ExternalSyntheticLambda2(var2), new SingletonForKt$$ExternalSyntheticLambda3(var2)});
      RearArea var3 = var1.getRearArea();
      var3.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new SingletonForKt$$ExternalSyntheticLambda4(var3), new SingletonForKt$$ExternalSyntheticLambda5(var3), new SingletonForKt$$ExternalSyntheticLambda6(var3), new SingletonForKt$$ExternalSyntheticLambda7(var3)});
      var3.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            SingletonForKt.setAirListener$send_4(22, 2);
         }

         public void onClickUp() {
            SingletonForKt.setAirListener$send_4(22, 1);
         }
      }, null, null}));
      var3.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
         public void onClickLeft() {
            SingletonForKt.setAirListener$send_4(21, 2);
         }

         public void onClickRight() {
            SingletonForKt.setAirListener$send_4(21, 1);
         }
      }));
   }

   public final void setSettingsListener(SettingPageUiSet var1) {
      Intrinsics.checkNotNullParameter(var1, "mSettingPageUiSet");
      var1.setOnSettingItemSwitchListener(new SingletonForKt$$ExternalSyntheticLambda8(var1));
   }
}
