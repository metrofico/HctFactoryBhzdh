package com.hzbhd.canbus.car._369;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"},
   d2 = {"Lcom/hzbhd/canbus/car/_369/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getMAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$0_aBOhpRUDSO4m0RxqoDxydhFgw(FrontArea var0, int var1) {
      lambda_4$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$16C85CgdLeJrk_FSoYbtpSjFEXY(UiMgr var0, int var1, int var2, int var3) {
      lambda_7$lambda_6(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ScYtI9OPvPlRoqiK0dVsdWMACno(FrontArea var0, int var1) {
      lambda_4$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$oj0TkIkG6f9Iyel6Cijloo_VvHs(UiMgr var0, int var1, int var2, int var3) {
      lambda_7$lambda_5(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$wSAyIW34LKmzWhWkm0w95mzdCI0(FrontArea var0, int var1) {
      lambda_4$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$wmWuRpviXzrEFQxcDzEZzT49Olw(FrontArea var0, int var1) {
      lambda_4$lambda_0(var0, var1);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getAirUiSet(context)");
      this.mAirPageUiSet = var2;
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var3;
      FrontArea var4 = var2.getFrontArea();
      var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(var4), new UiMgr$$ExternalSyntheticLambda1(var4), new UiMgr$$ExternalSyntheticLambda2(var4), new UiMgr$$ExternalSyntheticLambda3(var4)});
      var4.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
         public void onClickLeft() {
            UiMgr.lambda_4$send0x3BData(11, 2);
         }

         public void onClickRight() {
            UiMgr.lambda_4$send0x3BData(11, 1);
         }
      }));
      var4.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            UiMgr.lambda_4$send0x3BData(12, 2);
         }

         public void onClickUp() {
            UiMgr.lambda_4$send0x3BData(12, 1);
         }
      }, null, null}));
      var4.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener() {
         public void onLeftSeatClick() {
            UiMgr.lambda_4$send0x3BData(15, 255);
         }

         public void onRightSeatClick() {
         }
      }));
      var3.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda4(this));
      var3.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda5(this));
   }

   private static final void lambda_4$lambda_0(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[0][it]");
      lambda_4$selectAirPageBtn$default(var2, 0, 2, (Object)null);
   }

   private static final void lambda_4$lambda_1(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[1][it]");
      lambda_4$selectAirPageBtn$default(var2, 0, 2, (Object)null);
   }

   private static final void lambda_4$lambda_2(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[2][it]");
      lambda_4$selectAirPageBtn$default(var2, 0, 2, (Object)null);
   }

   private static final void lambda_4$lambda_3(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[3][it]");
      lambda_4$selectAirPageBtn$default(var2, 0, 2, (Object)null);
   }

   private static final void lambda_4$selectAirPageBtn(String var0, int var1) {
      switch (var0) {
         case "front_defog":
            lambda_4$send0x3BData(5, var1);
            break;
         case "rear_defog":
            lambda_4$send0x3BData(6, var1);
            break;
         case "ac":
            lambda_4$send0x3BData(2, var1);
            break;
         case "auto":
            lambda_4$send0x3BData(4, var1);
            break;
         case "power":
            lambda_4$send0x3BData(16, var1);
            break;
         case "in_out_cycle":
            lambda_4$send0x3BData(7, var1);
      }

   }

   // $FF: synthetic method
   static void lambda_4$selectAirPageBtn$default(String var0, int var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = 255;
      }

      lambda_4$selectAirPageBtn(var0, var1);
   }

   private static final void lambda_4$send0x3BData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var0, (byte)var1});
   }

   private static final void lambda_7$lambda_5(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      lambda_7$select(var0, var1, var2, var3);
   }

   private static final void lambda_7$lambda_6(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      lambda_7$select(var0, var1, var2, var3);
   }

   private static final void lambda_7$select(UiMgr var0, int var1, int var2, int var3) {
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var4 != null) {
         var1 = var4.hashCode();
         if (var1 != 893177037) {
            switch (var1) {
               case -1395302420:
                  if (var4.equals("S369_Light_1")) {
                     lambda_7$sendItemData(108, 11, var3);
                  }
                  break;
               case -1395302419:
                  if (var4.equals("S369_Light_2")) {
                     lambda_7$sendItemData(108, 12, var3);
                  }
                  break;
               case -1395302418:
                  if (var4.equals("S369_Light_3")) {
                     lambda_7$sendItemData(108, 3, var3);
                  }
                  break;
               case -1395302417:
                  if (var4.equals("S369_Light_4")) {
                     lambda_7$sendItemData(108, 4, var3);
                  }
                  break;
               case -1395302416:
                  if (var4.equals("S369_Light_5")) {
                     lambda_7$sendItemData(108, 5, var3);
                  }
                  break;
               case -1395302415:
                  if (var4.equals("S369_Light_6")) {
                     lambda_7$sendItemData(108, 6, var3);
                  }
                  break;
               case -1395302414:
                  if (var4.equals("S369_Light_7")) {
                     lambda_7$sendItemData(108, 7, var3);
                  }
                  break;
               case -1395302413:
                  if (var4.equals("S369_Light_8")) {
                     lambda_7$sendItemData(108, 8, var3);
                  }
                  break;
               default:
                  switch (var1) {
                     case 337002528:
                        if (var4.equals("S369_Air_1")) {
                           lambda_7$sendItemData(58, 1, var3);
                        }
                        break;
                     case 337002529:
                        if (var4.equals("S369_Air_2")) {
                           lambda_7$sendItemData(58, 4, var3);
                        }
                        break;
                     case 337002530:
                        if (var4.equals("S369_Air_3")) {
                           lambda_7$sendItemData(58, 16, var3);
                        }
                        break;
                     default:
                        switch (var1) {
                           case 1921938073:
                              if (var4.equals("S369_LightTime_1")) {
                                 lambda_7$sendItemData(108, 9, var3);
                              }
                              break;
                           case 1921938074:
                              if (var4.equals("S369_LightTime_2")) {
                                 lambda_7$sendItemData(108, 10, var3);
                              }
                              break;
                           default:
                              switch (var1) {
                                 case 1948502716:
                                    if (var4.equals("S369_Door_1")) {
                                       lambda_7$sendItemData(106, 6, var3);
                                    }
                                    break;
                                 case 1948502717:
                                    if (var4.equals("S369_Door_2")) {
                                       lambda_7$sendItemData(106, 3, var3);
                                    }
                                    break;
                                 case 1948502718:
                                    if (var4.equals("S369_Door_3")) {
                                       lambda_7$sendItemData(106, 7, var3);
                                    }
                              }
                        }
                  }
            }
         } else if (var4.equals("S369_RemoteControl_1")) {
            lambda_7$sendItemData(107, 8, var3);
         }
      }

   }

   private static final void lambda_7$sendItemData(int var0, int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, (byte)var0, (byte)var1, (byte)var2});
   }

   public final AirPageUiSet getMAirPageUiSet() {
      return this.mAirPageUiSet;
   }

   public final SettingPageUiSet getMSettingPageUiSet() {
      return this.mSettingPageUiSet;
   }
}
