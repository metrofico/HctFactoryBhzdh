package com.hzbhd.canbus.car._341;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"},
   d2 = {"Lcom/hzbhd/canbus/car/_341/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "amplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getAmplifierPageUiSet", "()Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet airPageUiSet;
   private final AmplifierPageUiSet amplifierPageUiSet;
   private final SettingPageUiSet settingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$0WII_Xsv2VR4QgaBrYTTf1DmGEI(int var0) {
      _init_$lambda_1(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$NYigxVp1KmAZi3VJpQh3yBdHQOk(int var0) {
      _init_$lambda_5(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$TtN48FTVOBNyVnlRYVlZsuLCCjw(int var0) {
      _init_$lambda_2(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$XHaZo0gRdz1KqCX7WfvMqc70Jlw(int var0, int var1, int var2) {
      _init_$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$cT9Bq30hG478eWdr1EqrMNe1JK8(int var0) {
      _init_$lambda_3(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$sqWYlFdwPe3uAX2u6iRnqrmI0x0(int var0) {
      _init_$lambda_4(var0);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      AirPageUiSet var3 = this.getAirUiSet(var1);
      this.airPageUiSet = var3;
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      this.amplifierPageUiSet = var4;
      UiMgrKt.setParam(0);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, -124});
      if (var2 != null) {
         var2.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda0());
      }

      var2 = null;
      FrontArea var5;
      if (var3 != null) {
         var5 = var3.getFrontArea();
      } else {
         var5 = null;
      }

      if (var5 != null) {
         var5.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda1(), new UiMgr$$ExternalSyntheticLambda2(), new UiMgr$$ExternalSyntheticLambda3(), new UiMgr$$ExternalSyntheticLambda4()});
      }

      if (var3 != null) {
         var5 = var3.getFrontArea();
      } else {
         var5 = null;
      }

      if (var5 != null) {
         var5.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
            public void onClickLeft() {
               UiMgrKt.access$sendAirConditionData(21);
            }

            public void onClickRight() {
               UiMgrKt.access$sendAirConditionData(20);
            }
         }));
      }

      if (var3 != null) {
         var5 = var3.getFrontArea();
      } else {
         var5 = null;
      }

      if (var5 != null) {
         var5.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener() {
            public void onClickDown() {
               UiMgrKt.access$sendAirConditionData(10);
            }

            public void onClickUp() {
               UiMgrKt.access$sendAirConditionData(9);
            }
         }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener() {
            public void onClickDown() {
               UiMgrKt.access$sendAirConditionData(12);
            }

            public void onClickUp() {
               UiMgrKt.access$sendAirConditionData(11);
            }
         })});
      }

      if (var3 != null) {
         var5 = var3.getFrontArea();
      } else {
         var5 = null;
      }

      if (var5 != null) {
         var5.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener() {
            public void onLeftSeatClick() {
               UiMgrKt.access$sendAirConditionData(17);
            }

            public void onRightSeatClick() {
               UiMgrKt.access$sendAirConditionData(42);
            }
         }));
      }

      RearArea var6;
      if (var3 != null) {
         var6 = var3.getRearArea();
      } else {
         var6 = null;
      }

      if (var6 != null) {
         var6.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda5(), null, null, null});
      }

      if (var3 != null) {
         var6 = var3.getRearArea();
      } else {
         var6 = null;
      }

      if (var6 != null) {
         var6.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
            public void onClickLeft() {
               UiMgrKt.access$sendAirConditionData(46);
            }

            public void onClickRight() {
               UiMgrKt.access$sendAirConditionData(45);
            }
         }));
      }

      if (var3 != null) {
         var6 = var3.getRearArea();
      } else {
         var6 = null;
      }

      if (var6 != null) {
         var6.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{null, new OnAirTemperatureUpDownClickListener() {
            public void onClickDown() {
               UiMgrKt.access$sendAirConditionData(44);
            }

            public void onClickUp() {
               UiMgrKt.access$sendAirConditionData(43);
            }
         }, null}));
      }

      var6 = var2;
      if (var3 != null) {
         var6 = var3.getRearArea();
      }

      if (var6 != null) {
         var6.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener() {
            public void onLeftSeatClick() {
               UiMgrKt.access$sendAirConditionData(47);
            }

            public void onRightSeatClick() {
               UiMgrKt.access$sendAirConditionData(47);
            }
         }));
      }

      if (var4 != null) {
         var4.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
            public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
               Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
               Log.i("lyn", "" + ' ' + var2);
               var2 = DataHandleUtils.rangeNumber(var2 + 7, 0, 14);
               int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
               if (var3 != 1) {
                  if (var3 == 2) {
                     UiMgrKt.access$sendSettingsData(2, var2);
                  }
               } else {
                  UiMgrKt.access$sendSettingsData(1, MsgMgrKt.reverse(var2));
               }

            }

            @Metadata(
               k = 3,
               mv = {1, 7, 1},
               xi = 48
            )
            public final class WhenMappings {
               public static final int[] $EnumSwitchMapping$0;

               static {
                  int[] var0 = new int[AmplifierActivity.AmplifierPosition.values().length];
                  var0[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
                  var0[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
                  $EnumSwitchMapping$0 = var0;
               }
            }
         }));
      }

      if (var4 != null) {
         var4.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
            public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
               Intrinsics.checkNotNullParameter(var1, "amplifierBand");
               Log.i("lyn", "" + ' ' + var2);
               int var3 = DataHandleUtils.rangeNumber(var2 + 2, 2, 12);
               var2 = DataHandleUtils.rangeNumber(var2, 0, 63);
               int var4 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
               if (var4 != 1) {
                  if (var4 != 2) {
                     if (var4 != 3) {
                        if (var4 == 4) {
                           UiMgrKt.access$sendSettingsData(7, var2);
                        }
                     } else {
                        UiMgrKt.access$sendSettingsData(6, var3);
                     }
                  } else {
                     UiMgrKt.access$sendSettingsData(5, var3);
                  }
               } else {
                  UiMgrKt.access$sendSettingsData(4, var3);
               }

            }

            @Metadata(
               k = 3,
               mv = {1, 7, 1},
               xi = 48
            )
            public final class WhenMappings {
               public static final int[] $EnumSwitchMapping$0;

               static {
                  int[] var0 = new int[AmplifierActivity.AmplifierBand.values().length];
                  var0[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
                  var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
                  var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
                  var0[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
                  $EnumSwitchMapping$0 = var0;
               }
            }
         }));
      }

   }

   private static final void _init_$lambda_0(int var0, int var1, int var2) {
      if (var0 != 0) {
         if (var0 == 1) {
            if (var1 != 0) {
               if (var1 == 1) {
                  UiMgrKt.access$sendSettingsData(9, var2);
               }
            } else if (var2 == 0) {
               UiMgrKt.access$sendSettingsData(3, 1);
            } else {
               UiMgrKt.access$sendSettingsData(3, 8);
            }
         }
      } else if (var1 != 0) {
         if (var1 == 1) {
            UiMgrKt.access$sendSettingsData(10, var2);
         }
      } else {
         UiMgrKt.access$sendSettingsData(8, var2);
      }

   }

   private static final void _init_$lambda_1(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 != 3) {
                  if (var0 == 4) {
                     if (GeneralAirData.power) {
                        UiMgrKt.access$sendAirConditionData(1);
                     } else {
                        UiMgrKt.access$sendAirConditionData(20);
                     }
                  }
               } else {
                  UiMgrKt.access$sendAirConditionData(37);
               }
            } else {
               UiMgrKt.access$sendAirConditionData(50);
            }
         } else {
            UiMgrKt.access$sendAirConditionData(15);
         }
      } else {
         UiMgrKt.access$sendAirConditionData(35);
      }

   }

   private static final void _init_$lambda_2(int var0) {
      if (var0 == 0) {
         UiMgrKt.access$sendAirConditionData(38);
      }

   }

   private static final void _init_$lambda_3(int var0) {
      if (var0 == 0) {
         UiMgrKt.access$sendAirConditionData(4);
      }

   }

   private static final void _init_$lambda_4(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 == 2) {
               UiMgrKt.access$sendAirConditionData(16);
            }
         } else {
            UiMgrKt.access$sendAirConditionData(8);
         }
      } else {
         UiMgrKt.access$sendAirConditionData(2);
      }

   }

   private static final void _init_$lambda_5(int var0) {
      if (var0 == 0) {
         UiMgrKt.access$sendAirConditionData(49);
      }

   }

   public final AirPageUiSet getAirPageUiSet() {
      return this.airPageUiSet;
   }

   public final AmplifierPageUiSet getAmplifierPageUiSet() {
      return this.amplifierPageUiSet;
   }

   public final SettingPageUiSet getSettingPageUiSet() {
      return this.settingPageUiSet;
   }
}
