package com.hzbhd.canbus.car._361;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0012\u0010\u0012\u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\u0013"},
   d2 = {"Lcom/hzbhd/canbus/car/_361/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sendAFrame", "", "d0", "", "d1", "sendAirConditionData", "data0", "sendAnotherAirConditionData", "sendCarFrame", "i", "sendSFrame", "sendSettingsData", "data1", "updateUiByDifferentCar", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   // $FF: synthetic method
   public static void $r8$lambda$0FZ4tAX6jucqyvjwxKB0fW3kY2o(UiMgr var0, int var1) {
      _init_$lambda_5(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$3s0ESc4GbuA0okvcOzJ55_CgIeU(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      _init_$lambda_1(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$QPfn9vw2rBjvl9IG8U7633V7fYs(UiMgr var0, int var1) {
      _init_$lambda_4(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$QUzHBi_QAliX3Q_ATb0eGUnS4T4(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      _init_$lambda_2(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$q4K89bA6JtQVRIVEMzUk8F5fTdo(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4) {
      _init_$lambda_3(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$u4F2ai8CFVEEOE3eLuLpjQuCtpY(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      _init_$lambda_0(var0, var1, var2, var3, var4);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getAmplifierPageUiSet(context)");
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getSettingUiSet(context)");
      AirPageUiSet var2 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getAirUiSet(context)");
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
                  this.this$0.sendSettingsData(2, var3);
               }
            } else {
               this.this$0.sendSettingsData(1, com.hzbhd.canbus.car._350.MsgMgrKt.reverse$default(var3, 0, 2, (Object)null));
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
            int var4 = DataHandleUtils.rangeNumber(var2, 0, 63);
            var2 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 == 4) {
                        this.this$0.sendSettingsData(7, var4);
                     }
                  } else {
                     this.this$0.sendSettingsData(6, var3);
                  }
               } else {
                  this.this$0.sendSettingsData(5, var3);
               }
            } else {
               this.this$0.sendSettingsData(4, var3);
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
      var4.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda0(var4, this));
      var4.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda1(var4, this));
      var4.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda2(var4, this));
      var4.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda3(var4, this, var1));
      FrontArea var5 = var2.getFrontArea();
      if (var5 != null) {
         var5.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda4(this), null, null, new UiMgr$$ExternalSyntheticLambda5(this)});
      }

      var5 = var2.getFrontArea();
      if (var5 != null) {
         var5.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickLeft() {
               this.this$0.sendAirConditionData(2);
            }

            public void onClickRight() {
               this.this$0.sendAirConditionData(1);
            }
         }));
      }

      var5 = var2.getFrontArea();
      if (var5 != null) {
         var5.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.sendAirConditionData(13);
            }

            public void onClickUp() {
               this.this$0.sendAirConditionData(12);
            }
         }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.sendAirConditionData(17);
            }

            public void onClickUp() {
               this.this$0.sendAirConditionData(16);
            }
         })});
      }

      var5 = var2.getFrontArea();
      if (var5 != null) {
         var5.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onLeftSeatClick() {
               this.this$0.sendAirConditionData(22);
            }

            public void onRightSeatClick() {
               this.this$0.sendAirConditionData(22);
            }
         }));
      }

      var5 = var2.getFrontArea();
      if (var5 != null) {
         var5.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.sendAnotherAirConditionData(3);
            }
         }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.sendAnotherAirConditionData(4);
            }
         })});
      }

   }

   private static final void _init_$lambda_0(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "$settingPageUiSet");
      Intrinsics.checkNotNullParameter(var1, "this$0");
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (var5 != null) {
         var3 = var5.hashCode();
         byte var6 = 1;
         switch (var3) {
            case -1517428852:
               if (var5.equals("S361_2_d2b0t3")) {
                  if (var4 == 1) {
                     var6 = 8;
                  }

                  var1.sendAFrame(3, var6);
               }
               break;
            case 331802606:
               if (var5.equals("S361_1_d2b0")) {
                  var1.sendSFrame(37, var4);
               }
               break;
            case 331802607:
               if (var5.equals("S361_1_d2b1")) {
                  var1.sendSFrame(38, var4);
               }
               break;
            case 360433679:
               if (var5.equals("S361_2_d4b0")) {
                  var1.sendAFrame(9, var4);
               }
               break;
            case 1168298533:
               if (var5.equals("S361_d0b7")) {
                  var1.sendSFrame(4, var4);
               }
               break;
            case 1168299491:
               if (var5.equals("S361_d1b4")) {
                  var1.sendSFrame(3, var4);
               }
               break;
            case 1168299492:
               if (var5.equals("S361_d1b5")) {
                  var1.sendSFrame(2, var4);
               }
               break;
            case 1168299493:
               if (var5.equals("S361_d1b6")) {
                  var1.sendSFrame(1, var4);
               }
               break;
            case 1168299494:
               if (var5.equals("S361_d1b7")) {
                  var1.sendSFrame(0, var4);
               }
               break;
            case 1168300451:
               if (var5.equals("S361_d2b3")) {
                  var1.sendSFrame(17, var4);
               }
               break;
            case 1168300452:
               if (var5.equals("S361_d2b4")) {
                  var1.sendSFrame(16, var4);
               }
               break;
            case 1168300454:
               if (var5.equals("S361_d2b6")) {
                  var1.sendSFrame(14, var4);
               }
               break;
            case 1168300455:
               if (var5.equals("S361_d2b7")) {
                  var1.sendSFrame(13, var4);
               }
               break;
            case 1168301415:
               if (var5.equals("S361_d3b6")) {
                  var1.sendSFrame(19, var4);
               }
               break;
            case 1168301416:
               if (var5.equals("S361_d3b7")) {
                  var1.sendSFrame(18, var4);
               }
         }
      }

   }

   private static final void _init_$lambda_1(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "$settingPageUiSet");
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var5 != null) {
         switch (var5) {
            case "S361_1_d1b0":
               var1.sendSFrame(12, var4);
               break;
            case "S361_d2b5":
               var1.sendSFrame(15, var4);
               break;
            case "S361_d0b0t1":
               var1.sendSFrame(7, var4);
               break;
            case "S361_d0b2t3":
               var1.sendSFrame(12, var4);
               break;
            case "S361_d4b0t3":
               var1.sendSFrame(21, var4 + 1);
               break;
            case "S361_d4b4t5":
               var1.sendSFrame(22, var4 + 1);
         }
      }

   }

   private static final void _init_$lambda_2(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "$settingPageUiSet");
      Intrinsics.checkNotNullParameter(var1, "this$0");
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var5, (Object)"S361_d1b0t2")) {
         var1.sendSFrame(5, var4);
      } else if (Intrinsics.areEqual((Object)var5, (Object)"S361_d0b6t4")) {
         var1.sendSFrame(6, var4);
      }

   }

   private static final void _init_$lambda_3(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "$settingPageUiSet");
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$context");
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      if (var5 != null) {
         var3 = var5.hashCode();
         switch (var3) {
            case 1765565998:
               if (var5.equals("S361_car_10")) {
                  var1.sendCarFrame(12);
               }
               break;
            case 1765565999:
               if (var5.equals("S361_car_11")) {
                  var1.sendCarFrame(16);
               }
               break;
            case 1765566000:
               if (var5.equals("S361_car_12")) {
                  var1.sendCarFrame(17);
               }
               break;
            case 1765566001:
               if (var5.equals("S361_car_13")) {
                  var1.sendCarFrame(18);
               }
               break;
            case 1765566002:
               if (var5.equals("S361_car_14")) {
                  var1.sendCarFrame(80);
               }
               break;
            default:
               switch (var3) {
                  case 1858069057:
                     if (var5.equals("S361_car_0")) {
                        var1.sendCarFrame(0);
                     }
                     break;
                  case 1858069058:
                     if (var5.equals("S361_car_1")) {
                        var1.sendCarFrame(1);
                     }
                     break;
                  case 1858069059:
                     if (var5.equals("S361_car_2")) {
                        var1.sendCarFrame(2);
                     }
                     break;
                  case 1858069060:
                     if (var5.equals("S361_car_3")) {
                        var1.sendCarFrame(3);
                     }
                     break;
                  case 1858069061:
                     if (var5.equals("S361_car_4")) {
                        var1.sendCarFrame(5);
                     }
                     break;
                  case 1858069062:
                     if (var5.equals("S361_car_5")) {
                        var1.sendCarFrame(6);
                     }
                     break;
                  case 1858069063:
                     if (var5.equals("S361_car_6")) {
                        var1.sendCarFrame(8);
                     }
                     break;
                  case 1858069064:
                     if (var5.equals("S361_car_7")) {
                        var1.sendCarFrame(9);
                     }
                     break;
                  case 1858069065:
                     if (var5.equals("S361_car_8")) {
                        var1.sendCarFrame(10);
                     }
                     break;
                  case 1858069066:
                     if (var5.equals("S361_car_9")) {
                        var1.sendCarFrame(11);
                     }
               }
         }
      }

      var1.removeSettingLeftItemByNameList(var2, new String[]{"S361_car_title"});
      MsgMgrInterface var6 = MsgMgrFactory.getCanMsgMgr(var2);
      Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type com.hzbhd.canbus.car._361.MsgMgr");
      ((MsgMgr)var6).returnClick();
   }

   private static final void _init_$lambda_4(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 4) {
               var0.sendAirConditionData(19);
            }
         } else {
            var0.sendAirConditionData(25);
         }
      } else {
         var0.sendAirConditionData(4);
      }

   }

   private static final void _init_$lambda_5(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 1) {
         if (var1 == 2) {
            var0.sendAirConditionData(3);
         }
      } else {
         var0.sendAirConditionData(23);
      }

   }

   private final void sendAFrame(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   private final void sendAirConditionData(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -117, var2, 1});
      Thread.sleep(100L);
      CanbusMsgSender.sendMsg(new byte[]{22, -117, var2, 0});
   }

   private final void sendAnotherAirConditionData(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -116, var2, 1});
      Thread.sleep(100L);
      CanbusMsgSender.sendMsg(new byte[]{22, -116, var2, 0});
   }

   private final void sendCarFrame(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -115, (byte)var1});
   }

   private final void sendSFrame(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   private final void sendSettingsData(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      this.removeSettingRightItemByNameList(var1, new String[]{"S361_d30t1", "S361_2_d4b0"});
      UiMgrKt.setRemove0(true);
      Set var2 = (Set)(new LinkedHashSet());
      CollectionsKt.addAll((Collection)var2, new Integer[]{2, 3});
      if (!var2.contains(this.getCurrentCarId())) {
         this.removeSettingRightItemByNameList(var1, new String[]{"S361_d1b7", "S361_d1b6", "S361_d1b5", "S361_d1b4", "S361_d2b6"});
         UiMgrKt.setRemove1(true);
      }

      if (this.getCurrentCarId() != 1) {
         this.removeSettingRightItemByNameList(var1, new String[]{"S361_d0b7", "S361_d1b0t2", "S361_d0b0t1", "S361_d2b7", "S361_d2b5", "S361_1_d2b0", "S361_1_d2b1"});
         UiMgrKt.setRemove2(true);
      }

      if (this.getCurrentCarId() != 7) {
         this.removeSettingRightItemByNameList(var1, new String[]{"S361_1_d1b0"});
         UiMgrKt.setRemove3(true);
      }

      if (this.getCurrentCarId() != 3) {
         this.removeSettingRightItemByNameList(var1, new String[]{"S361_d4b0t3", "S361_d4b4t5"});
         UiMgrKt.setRemove4(true);
      }

   }
}
