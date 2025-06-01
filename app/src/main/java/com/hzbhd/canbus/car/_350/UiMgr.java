package com.hzbhd.canbus.car._350;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0018\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"},
   d2 = {"Lcom/hzbhd/canbus/car/_350/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "amplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getAmplifierPageUiSet", "()Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "parkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getParkPageUiSet", "()Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "sendAFrame", "", "d0", "", "d1", "sendAirConditionerFrame", "sendPFrame", "sendSFrame", "updateUiByDifferentCar", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet airPageUiSet;
   private final AmplifierPageUiSet amplifierPageUiSet;
   private final ParkPageUiSet parkPageUiSet;
   private final SettingPageUiSet settingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$2gIQMmDYISTHRyWdPvmWOY0zvTY(UiMgr var0, int var1) {
      _init_$lambda_8(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$4kt5sYDdJR_etTPfvpp_UnVprjI(UiMgr var0, int var1, int var2, int var3) {
      _init_$lambda_1(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Ex7yy_QyThjSyFpCxNToHzcMjNs(int var0, int var1, UiMgr var2, MotionEvent var3) {
      _init_$lambda_9(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$JGwF1z7uBxPVNVC8su9xqj9qn7o(UiMgr var0, int var1) {
      _init_$lambda_7(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$KU9j7i_da9rn4N8AtbMHz0cw5eg(UiMgr var0, int var1, int var2, int var3) {
      _init_$lambda_0(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$RCFHhd4fb0L7i6xgsqykHM5QciA(UiMgr var0, int var1, int var2, int var3) {
      _init_$lambda_2(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$UPlI8PhVlncUjxKySIXVFZ5Yujg(UiMgr var0, int var1) {
      _init_$lambda_6(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$_HwLem4JS_OEiWL_iie5jxwqUbQ(UiMgr var0, int var1) {
      _init_$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$iIgomInRhIkg2yz_m_EAbPVpTGo(UiMgr var0, int var1) {
      _init_$lambda_5(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$k9w3drJU6O7Oebiu8KzQyjGxA3w(UiMgr var0, int var1) {
      _init_$lambda_4(var0, var1);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.settingPageUiSet = var2;
      AirPageUiSet var5 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var5, "getAirUiSet(context)");
      this.airPageUiSet = var5;
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getAmplifierPageUiSet(context)");
      this.amplifierPageUiSet = var3;
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getParkPageUiSet(context)");
      this.parkPageUiSet = var4;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      var2.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda0(this));
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda1(this));
      var2.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda2(this));
      var5.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda3(this), new UiMgr$$ExternalSyntheticLambda4(this), new UiMgr$$ExternalSyntheticLambda5(this), new UiMgr$$ExternalSyntheticLambda6(this)});
      var5.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirConditionerFrame(2);
         }

         public void onClickUp() {
            this.this$0.sendAirConditionerFrame(3);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirConditionerFrame(4);
         }

         public void onClickUp() {
            this.this$0.sendAirConditionerFrame(5);
         }
      })});
      var5.getFrontArea().setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirConditionerFrame(9);
         }

         public void onClickRight() {
            this.this$0.sendAirConditionerFrame(10);
         }
      }));
      var5.getFrontArea().setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirConditionerFrame(36);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirConditionerFrame(37);
         }
      }));
      var5.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda7(this), null, null, new UiMgr$$ExternalSyntheticLambda8(this)});
      var5.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirConditionerFrame(38);
         }

         public void onClickUp() {
            this.this$0.sendAirConditionerFrame(39);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirConditionerFrame(46);
         }

         public void onClickUp() {
            this.this$0.sendAirConditionerFrame(47);
         }
      })});
      var5.getRearArea().setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirConditionerFrame(40);
         }

         public void onClickRight() {
            this.this$0.sendAirConditionerFrame(41);
         }
      }));
      var5.getRearArea().setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirConditionerFrame(43);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirConditionerFrame(43);
         }
      }));
      var3.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = DataHandleUtils.rangeNumber(var2 + 7, 0, 14);
            var2 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var2 != 1) {
               if (var2 == 2) {
                  this.this$0.sendAFrame(2, var3);
               }
            } else {
               this.this$0.sendAFrame(1, MsgMgrKt.reverse$default(var3, 0, 2, (Object)null));
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
      var3.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = DataHandleUtils.rangeNumber(var2 + 2, 2, 12);
            var2 = DataHandleUtils.rangeNumber(var2, 0, 63);
            int var4 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var4 != 1) {
               if (var4 != 2) {
                  if (var4 != 3) {
                     if (var4 == 4) {
                        this.this$0.sendAFrame(7, var2);
                     }
                  } else {
                     this.this$0.sendAFrame(6, var3);
                  }
               } else {
                  this.this$0.sendAFrame(5, var3);
               }
            } else {
               this.this$0.sendAFrame(4, var3);
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
      var4.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda9(var1.getResources().getDisplayMetrics().widthPixels, var1.getResources().getDisplayMetrics().heightPixels, this));
   }

   private static final void _init_$lambda_0(UiMgr var0, int var1, int var2, int var3) {
      // $FF: Couldn't be decompiled
   }

   private static final void _init_$lambda_1(UiMgr var0, int var1, int var2, int var3) {
      // $FF: Couldn't be decompiled
   }

   private static final void _init_$lambda_2(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var5 = ((SettingPageUiSet.ListBean)var0.settingPageUiSet.getList().get(var1)).getTitleSrn();
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var5 != null) {
         switch (var5.hashCode()) {
            case 1940889716:
               if (var5.equals("_350_s_0") && Intrinsics.areEqual((Object)var4, (Object)"_350_s_0_1")) {
                  var0.sendSFrame(6, var3);
               }
               break;
            case 1940889717:
               if (var5.equals("_350_s_1") && Intrinsics.areEqual((Object)var4, (Object)"_350_s_1_4")) {
                  var0.sendSFrame(5, var3);
               }
            case 1940889718:
            case 1940889719:
            default:
               break;
            case 1940889720:
               if (var5.equals("_350_s_4") && Intrinsics.areEqual((Object)var4, (Object)"_350_s_4_1")) {
                  var0.sendSFrame(38, var3);
               }
               break;
            case 1940889721:
               if (var5.equals("_350_s_5") && Intrinsics.areEqual((Object)var4, (Object)"_350_s_5_3")) {
                  var0.sendSFrame(21, var3);
               }
         }
      }

   }

   private static final void _init_$lambda_3(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               if (GeneralAirData.power) {
                  var0.sendAirConditionerFrame(1);
               } else {
                  var0.sendAirConditionerFrame(10);
               }
            }
         } else {
            var0.sendAirConditionerFrame(28);
         }
      } else {
         var0.sendAirConditionerFrame(25);
      }

   }

   private static final void _init_$lambda_4(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirConditionerFrame(26);
      }

   }

   private static final void _init_$lambda_5(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirConditionerFrame(32);
      }

   }

   private static final void _init_$lambda_6(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 4) {
                  var0.sendAirConditionerFrame(27);
               }
            } else {
               var0.sendAirConditionerFrame(16);
            }
         } else {
            var0.sendAirConditionerFrame(21);
         }
      } else {
         var0.sendAirConditionerFrame(23);
      }

   }

   private static final void _init_$lambda_7(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirConditionerFrame(42);
      }

   }

   private static final void _init_$lambda_8(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirConditionerFrame(45);
      }

   }

   private static final void _init_$lambda_9(int var0, int var1, UiMgr var2, MotionEvent var3) {
      Intrinsics.checkNotNullParameter(var2, "this$0");
      int var5 = (int)(var3.getX() * (255.0F / (float)var0));
      int var4 = (int)(var3.getY() * (255.0F / (float)var1));
      if (var3.getAction() == 0) {
         var2.sendPFrame(var5, var4);
      } else if (var3.getAction() == 1) {
         var2.sendPFrame(0, 0);
      }

      Log.i("lyn", " x:" + var5 + ", y:" + var4 + " \n width:" + var0 + ", high:" + var1);
   }

   private final void sendPFrame(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)var1, (byte)var2});
      Log.i("lyn", var1 + "  " + var2);
   }

   private final void sendSFrame(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   public final AirPageUiSet getAirPageUiSet() {
      return this.airPageUiSet;
   }

   public final AmplifierPageUiSet getAmplifierPageUiSet() {
      return this.amplifierPageUiSet;
   }

   public final ParkPageUiSet getParkPageUiSet() {
      return this.parkPageUiSet;
   }

   public final SettingPageUiSet getSettingPageUiSet() {
      return this.settingPageUiSet;
   }

   public final void sendAFrame(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   public final void sendAirConditionerFrame(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 0});
   }

   public void updateUiByDifferentCar(Context var1) {
      this.removeMainPrjBtnByName(var1, "original_car_device");
      this.removeSettingRightItemByNameList(var1, new String[]{"_350_s_3_3", "_350_s_4_0", "_350_s_5_2"});
      this.removeSettingLeftItemByNameList(var1, new String[]{"_350_s_7"});
   }
}
