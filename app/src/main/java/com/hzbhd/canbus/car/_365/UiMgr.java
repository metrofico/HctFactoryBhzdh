package com.hzbhd.canbus.car._365;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/canbus/car/_365/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getMAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "selectAirConditionerBtn", "", "btn", "", "sendAirConditionerCmd", "d0", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$7ZbbGQJe82ei7HtPbcgYfVnQ9OA(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Fro_OplXHs_b0eNUOVtk5nE0LPI(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ROBZRLnMErfm_zpT3JYbquhoLS4(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$c6_kimB6GOF9Q0LDX_OFPwqbNJg(Context var0, MotionEvent var1) {
      _init_$lambda_6(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$dGeqGFG4YPmmgGiaiOmt9ppq99A(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_2(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getAirUiSet(context)");
      this.mAirPageUiSet = var2;
      FrontArea var3 = var2.getFrontArea();
      var3.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var3), new UiMgr$$ExternalSyntheticLambda1(this, var3), new UiMgr$$ExternalSyntheticLambda2(this, var3), new UiMgr$$ExternalSyntheticLambda3(this, var3)});
      var3.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirConditionerCmd(12);
         }

         public void onClickRight() {
            this.this$0.sendAirConditionerCmd(11);
         }
      }));
      var3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirConditionerCmd(14);
         }

         public void onClickUp() {
            this.this$0.sendAirConditionerCmd(13);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirConditionerCmd(16);
         }

         public void onClickUp() {
            this.this$0.sendAirConditionerCmd(15);
         }
      })});
      var3.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirConditionerCmd(19);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirConditionerCmd(20);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirConditionerCmd(23);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirConditionerCmd(24);
         }
      })});
      var3.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirConditionerCmd(17);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirConditionerCmd(18);
         }
      }));
      PanelKeyPageUiSet var4 = this.getPanelKeyPageUiSet(var1);
      var4.setOnPanelKeyPositionListener((OnPanelKeyPositionListener)(new OnPanelKeyPositionListener(var4) {
         final PanelKeyPageUiSet $this_apply;

         {
            this.$this_apply = var1;
         }

         public void click(int var1) {
            List var2 = this.$this_apply.getList();
            String var3;
            if (var2 != null) {
               var3 = (String)var2.get(var1);
            } else {
               var3 = null;
            }

            if (var3 != null) {
               var1 = var3.hashCode();
               switch (var1) {
                  case -2101038641:
                     if (var3.equals("K365_x2A_10")) {
                        UiMgr.lambda_5$sendCommand(14);
                     }
                     break;
                  case -2101038640:
                     if (var3.equals("K365_x2A_11")) {
                        UiMgr.lambda_5$sendCommand(17);
                     }
                     break;
                  case -2101038639:
                     if (var3.equals("K365_x2A_12")) {
                        UiMgr.lambda_5$sendCommand(25);
                     }
                     break;
                  case -2101038638:
                     if (var3.equals("K365_x2A_13")) {
                        UiMgr.lambda_5$sendCommand(22);
                     }
                     break;
                  case -2101038637:
                     if (var3.equals("K365_x2A_14")) {
                        UiMgr.lambda_5$sendCommand(36);
                     }
                     break;
                  case -2101038636:
                     if (var3.equals("K365_x2A_15")) {
                        UiMgr.lambda_5$sendCommand(43);
                     }
                     break;
                  case -2101038635:
                     if (var3.equals("K365_x2A_16")) {
                        UiMgr.lambda_5$sendCommand(47);
                     }
                     break;
                  case -2101038634:
                     if (var3.equals("K365_x2A_17")) {
                        UiMgr.lambda_5$sendCommand(50);
                     }
                     break;
                  case -2101038633:
                     if (var3.equals("K365_x2A_18")) {
                        UiMgr.lambda_5$sendCommand(64);
                     }
                     break;
                  case -2101038632:
                     if (var3.equals("K365_x2A_19")) {
                        UiMgr.lambda_5$sendCommand(65);
                     }
                     break;
                  default:
                     switch (var1) {
                        case -2101038610:
                           if (var3.equals("K365_x2A_20")) {
                              UiMgr.lambda_5$sendCommand(80);
                           }
                           break;
                        case -2101038609:
                           if (var3.equals("K365_x2A_21")) {
                              UiMgr.lambda_5$sendCommand(81);
                           }
                           break;
                        case -2101038608:
                           if (var3.equals("K365_x2A_22")) {
                              UiMgr.lambda_5$sendCommand(82);
                           }
                           break;
                        case -2101038607:
                           if (var3.equals("K365_x2A_23")) {
                              UiMgr.lambda_5$sendCommand(83);
                           }
                           break;
                        case -2101038606:
                           if (var3.equals("K365_x2A_24")) {
                              UiMgr.lambda_5$sendCommand(84);
                           }
                           break;
                        default:
                           switch (var1) {
                              case -1591796095:
                                 if (var3.equals("K365_x2A_1")) {
                                    UiMgr.lambda_5$sendCommand(1);
                                 }
                                 break;
                              case -1591796094:
                                 if (var3.equals("K365_x2A_2")) {
                                    UiMgr.lambda_5$sendCommand(2);
                                 }
                                 break;
                              case -1591796093:
                                 if (var3.equals("K365_x2A_3")) {
                                    UiMgr.lambda_5$sendCommand(3);
                                 }
                                 break;
                              case -1591796092:
                                 if (var3.equals("K365_x2A_4")) {
                                    UiMgr.lambda_5$sendCommand(5);
                                 }
                                 break;
                              case -1591796091:
                                 if (var3.equals("K365_x2A_5")) {
                                    UiMgr.lambda_5$sendCommand(6);
                                 }
                                 break;
                              case -1591796090:
                                 if (var3.equals("K365_x2A_6")) {
                                    UiMgr.lambda_5$sendCommand(10);
                                 }
                                 break;
                              case -1591796089:
                                 if (var3.equals("K365_x2A_7")) {
                                    UiMgr.lambda_5$sendCommand(11);
                                 }
                                 break;
                              case -1591796088:
                                 if (var3.equals("K365_x2A_8")) {
                                    UiMgr.lambda_5$sendCommand(12);
                                 }
                                 break;
                              case -1591796087:
                                 if (var3.equals("K365_x2A_9")) {
                                    UiMgr.lambda_5$sendCommand(13);
                                 }
                           }
                     }
               }
            }

         }
      }));
      this.getParkPageUiSet(var1).setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda4(var1));
   }

   private static final void _init_$lambda_6(Context var0, MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var0, "$context");
      int var2 = var0.getResources().getDisplayMetrics().widthPixels;
      int var3 = var0.getResources().getDisplayMetrics().heightPixels;
      var2 = (int)(var1.getX() * (1000.0F / (float)var2));
      var3 = (int)(var1.getY() * (1024.0F / (float)var3));
      if (var1.getAction() == 0) {
         lambda_6$send(var2, var3);
      }

   }

   private static final void lambda_4$lambda_0(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_4$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_4$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_4$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_5$sendCommand(int var0) {
      byte var1 = (byte)var0;
      CanbusMsgSender.sendMsg(new byte[]{22, 42, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 42, var1, 0});
   }

   private static final void lambda_6$send(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte)DataHandleUtils.getMsb(var0), (byte)DataHandleUtils.getLsb(var0), (byte)DataHandleUtils.getMsb(var1), (byte)DataHandleUtils.getLsb(var1), 0});
   }

   private final void selectAirConditionerBtn(String var1) {
      switch (var1) {
         case "front_defog":
            this.sendAirConditionerCmd(5);
            break;
         case "rear_defog":
            this.sendAirConditionerCmd(6);
            break;
         case "ac":
            this.sendAirConditionerCmd(2);
            break;
         case "auto":
            this.sendAirConditionerCmd(4);
            break;
         case "sync":
            this.sendAirConditionerCmd(3);
            break;
         case "power":
            this.sendAirConditionerCmd(1);
            break;
         case "in_out_cycle":
            this.sendAirConditionerCmd(7);
      }

   }

   private final void sendAirConditionerCmd(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   public final AirPageUiSet getMAirPageUiSet() {
      return this.mAirPageUiSet;
   }
}
