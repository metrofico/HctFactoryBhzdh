package com.hzbhd.canbus.car._407;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"},
   d2 = {"Lcom/hzbhd/canbus/car/_407/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   // $FF: synthetic method
   public static void $r8$lambda$1PaExV_chOcMkVGDipX7xtCuts0(RearArea var0, int var1) {
      lambda_11$lambda_10$lambda_7(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$4v_NMHlwzlU_bGdJbAQdYnkLE28(FrontArea var0, int var1) {
      lambda_11$lambda_5$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$CMxz7f3AAY8U3obYgPfS4vsPWlI(int var0) {
      lambda_13$lambda_12(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$CaWPn2dB88_lW2xheBzq2j60DVg(FrontArea var0, int var1) {
      lambda_11$lambda_5$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$RrP05G8TTg_cm8ZO0fEN7C_pTlc(FrontArea var0, int var1) {
      lambda_11$lambda_5$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$dd3HvgTYvcCzcCmMR4yNveDdEZY(RearArea var0, int var1) {
      lambda_11$lambda_10$lambda_6(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$gYO2Up5zOc5F2MZVu0SLoZ1b7mk(RearArea var0, int var1) {
      lambda_11$lambda_10$lambda_8(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$mcd1_LNkCUph8RFVYzwAXL_BAkE(RearArea var0, int var1) {
      lambda_11$lambda_10$lambda_9(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$n9pZxAxaC4_l6lg3IiWexuI8Mm4(FrontArea var0, int var1) {
      lambda_11$lambda_5$lambda_4(var0, var1);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "this");
      SettingHandleListener var2 = new SettingHandleListener(var3);
      var3.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var2);
      var3.setOnSettingItemSelectListener((OnSettingItemSelectListener)var2);
      var3.setOnSettingItemSeekbarSelectListener((OnSettingItemSeekbarSelectListener)var2);
      AirPageUiSet var6 = this.getAirUiSet(var1);
      FrontArea var4 = var6.getFrontArea();
      var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(var4), new UiMgr$$ExternalSyntheticLambda1(var4), new UiMgr$$ExternalSyntheticLambda2(var4), new UiMgr$$ExternalSyntheticLambda3(var4)});
      var4.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
         public void onClickLeft() {
            UiMgr.lambda_11$sendAirConditionerCmd(12);
         }

         public void onClickRight() {
            UiMgr.lambda_11$sendAirConditionerCmd(11);
         }
      }));
      var4.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            UiMgr.lambda_11$sendAirConditionerCmd(14);
         }

         public void onClickUp() {
            UiMgr.lambda_11$sendAirConditionerCmd(13);
         }
      }, null, null}));
      var4.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener() {
         public void onLeftSeatClick() {
            UiMgr.lambda_11$sendAirConditionerCmd(21);
         }

         public void onRightSeatClick() {
         }
      }));
      RearArea var5 = var6.getRearArea();
      var5.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda4(var5), new UiMgr$$ExternalSyntheticLambda5(var5), new UiMgr$$ExternalSyntheticLambda6(var5), new UiMgr$$ExternalSyntheticLambda7(var5)});
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda8());
   }

   private static final void lambda_11$lambda_10$lambda_6(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[0][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$lambda_10$lambda_7(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[1][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$lambda_10$lambda_8(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[2][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$lambda_10$lambda_9(RearArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[3][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$lambda_5$lambda_1(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[0][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$lambda_5$lambda_2(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[1][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$lambda_5$lambda_3(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[2][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$lambda_5$lambda_4(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[3][it]");
      lambda_11$selectAirConditionerBtn(var2);
   }

   private static final void lambda_11$selectAirConditionerBtn(String var0) {
      switch (var0) {
         case "front_defog":
            lambda_11$sendAirConditionerCmd(5);
            break;
         case "ac_max":
            lambda_11$sendAirConditionerCmd(30);
            break;
         case "rear_defog":
            lambda_11$sendAirConditionerCmd(6);
            break;
         case "rear_power":
            lambda_11$sendAirConditionerCmd(46);
            break;
         case "ac":
            lambda_11$sendAirConditionerCmd(2);
            break;
         case "auto":
            lambda_11$sendAirConditionerCmd(4);
            break;
         case "power":
            lambda_11$sendAirConditionerCmd(1);
            break;
         case "in_out_cycle":
            lambda_11$sendAirConditionerCmd(7);
      }

   }

   private static final void lambda_11$sendAirConditionerCmd(int var0) {
      byte var1 = (byte)var0;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
   }

   private static final void lambda_13$lambda_12(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 == 2) {
               lambda_13$sendPanoramaCmd(2);
            }
         } else {
            lambda_13$sendPanoramaCmd(1);
         }
      } else {
         lambda_13$sendPanoramaCmd(0);
      }

   }

   private static final void lambda_13$sendPanoramaCmd(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, 74, 1, (byte)var0});
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J \u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"},
      d2 = {"Lcom/hzbhd/canbus/car/_407/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
      private final SettingPageUiSet mSettingPageUiSet;

      public SettingHandleListener(SettingPageUiSet var1) {
         Intrinsics.checkNotNullParameter(var1, "mSettingPageUiSet");
         super();
         this.mSettingPageUiSet = var1;
      }

      private final void selectSettingsBtn(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         if (var4 != null) {
            var1 = var4.hashCode();
            switch (var1) {
               case -768171052:
                  if (var4.equals("S407_x78_1")) {
                     selectSettingsBtn$sendSettingsCmd(30, var3);
                  }
                  break;
               case -768171051:
                  if (var4.equals("S407_x78_2")) {
                     selectSettingsBtn$sendSettingsCmd(31, var3);
                  }
                  break;
               case -768171050:
                  if (var4.equals("S407_x78_3")) {
                     selectSettingsBtn$sendSettingsCmd(32, var3);
                  }
                  break;
               case -768171049:
                  if (var4.equals("S407_x78_4")) {
                     selectSettingsBtn$sendSettingsCmd(26, var3);
                  }
                  break;
               case -768171048:
                  if (var4.equals("S407_x78_5")) {
                     selectSettingsBtn$sendSettingsCmd(27, var3);
                  }
                  break;
               case -768171047:
                  if (var4.equals("S407_x78_6")) {
                     selectSettingsBtn$sendSettingsCmd(28, var3);
                  }
                  break;
               case -768171046:
                  if (var4.equals("S407_x78_7")) {
                     selectSettingsBtn$sendSettingsCmd(29, var3);
                  }
                  break;
               default:
                  switch (var1) {
                     case -768142222:
                        if (var4.equals("S407_x87_1")) {
                           selectSettingsBtn$sendSettingsCmd(5, var3);
                        }
                        break;
                     case -768142221:
                        if (var4.equals("S407_x87_2")) {
                           selectSettingsBtn$sendSettingsCmd(4, var3);
                        }
                        break;
                     case -768142220:
                        if (var4.equals("S407_x87_3")) {
                           selectSettingsBtn$sendSettingsCmd(3, var3);
                        }
                        break;
                     case -768142219:
                        if (var4.equals("S407_x87_4")) {
                           selectSettingsBtn$sendSettingsCmd(2, var3);
                        }
                        break;
                     case -768142218:
                        if (var4.equals("S407_x87_5")) {
                           selectSettingsBtn$sendSettingsCmd(1, var3);
                        }
                        break;
                     case -768142217:
                        if (var4.equals("S407_x87_6")) {
                           selectSettingsBtn$sendSettingsCmd(16, var3);
                        }
                        break;
                     case -768142216:
                        if (var4.equals("S407_x87_7")) {
                           selectSettingsBtn$sendSettingsCmd(9, var3);
                        }
                        break;
                     case -768142215:
                        if (var4.equals("S407_x87_8")) {
                           selectSettingsBtn$sendSettingsCmd(8, var3);
                        }
                        break;
                     case -768142214:
                        if (var4.equals("S407_x87_9")) {
                           selectSettingsBtn$sendSettingsCmd(7, var3);
                        }
                        break;
                     default:
                        switch (var1) {
                           case -767753017:
                              if (var4.equals("S407_xE9_1")) {
                                 selectSettingsBtn$sendAnotherSettingsCmd(1, var3);
                              }
                              break;
                           case -767753016:
                              if (var4.equals("S407_xE9_2")) {
                                 selectSettingsBtn$sendAnotherSettingsCmd(2, var3);
                              }
                              break;
                           case -767753015:
                              if (var4.equals("S407_xE9_3")) {
                                 selectSettingsBtn$sendAnotherSettingsCmd(3, var3);
                              }
                              break;
                           default:
                              switch (var1) {
                                 case 1957394942:
                                    if (var4.equals("S407_x87_10")) {
                                       selectSettingsBtn$sendSettingsCmd(6, var3);
                                    }
                                    break;
                                 case 1957394943:
                                    if (var4.equals("S407_x87_11")) {
                                       selectSettingsBtn$sendSettingsCmd(13, var3);
                                    }
                                    break;
                                 case 1957394944:
                                    if (var4.equals("S407_x87_12")) {
                                       selectSettingsBtn$sendSettingsCmd(11, var3);
                                    }
                                    break;
                                 case 1957394945:
                                    if (var4.equals("S407_x87_13")) {
                                       selectSettingsBtn$sendSettingsCmd(18, var3);
                                    }
                                    break;
                                 case 1957394946:
                                    if (var4.equals("S407_x87_14")) {
                                       selectSettingsBtn$sendSettingsCmd(19, var3);
                                    }
                                    break;
                                 case 1957394947:
                                    if (var4.equals("S407_x87_15")) {
                                       selectSettingsBtn$sendSettingsCmd(20, var3);
                                    }
                                    break;
                                 case 1957394948:
                                    if (var4.equals("S407_x87_16")) {
                                       selectSettingsBtn$sendSettingsCmd(23, var3);
                                    }
                                    break;
                                 case 1957394949:
                                    if (var4.equals("S407_x87_17")) {
                                       selectSettingsBtn$sendSettingsCmd(24, var3);
                                    }
                                    break;
                                 case 1957394950:
                                    if (var4.equals("S407_x87_18")) {
                                       selectSettingsBtn$sendSettingsCmd(15, var3);
                                    }
                                    break;
                                 case 1957394951:
                                    if (var4.equals("S407_x87_19")) {
                                       selectSettingsBtn$sendSettingsCmd(14, var3);
                                    }
                                    break;
                                 default:
                                    switch (var1) {
                                       case 1957394973:
                                          if (var4.equals("S407_x87_20")) {
                                             selectSettingsBtn$sendSettingsCmd(21, var3);
                                          }
                                          break;
                                       case 1957394974:
                                          if (var4.equals("S407_x87_21")) {
                                             selectSettingsBtn$sendSettingsCmd(22, var3);
                                          }
                                    }
                              }
                        }
                  }
            }
         }

      }

      private static final void selectSettingsBtn$sendAnotherSettingsCmd(int var0, int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -4, (byte)var0, (byte)var1});
      }

      private static final void selectSettingsBtn$sendSettingsCmd(int var0, int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)var0, (byte)var1});
      }

      public final SettingPageUiSet getMSettingPageUiSet() {
         return this.mSettingPageUiSet;
      }

      public void onClickItem(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }

      public void onSwitch(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }
   }
}
