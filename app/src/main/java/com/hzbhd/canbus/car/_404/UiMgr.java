package com.hzbhd.canbus.car._404;

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
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"},
   d2 = {"Lcom/hzbhd/canbus/car/_404/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "kotlin.jvm.PlatformType", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_404/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;
   private final ParkPageUiSet mParkPageUiSet;
   private final SettingHandleListener mSettingHandleListener;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$1U2xb0xJQbMpMzCwDWmsF1_yNAQ(FrontArea var0, int var1) {
      lambda_4$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$fS7XgWY2sPN4f063MQ6OR8w2qOQ(FrontArea var0, int var1) {
      lambda_4$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$pResT943e4DJA5t75zezlqz4W20(FrontArea var0, int var1) {
      lambda_4$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$uG1Kk8BecZJFmm_jL7d2sUyRPBI(Context var0, MotionEvent var1) {
      _init_$lambda_6(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$wrjy1OyREMWPNhrAPo4Z4V7rLTw(FrontArea var0, int var1) {
      lambda_4$lambda_1(var0, var1);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var4;
      SettingHandleListener var3 = new SettingHandleListener(this, var4);
      this.mSettingHandleListener = var3;
      AirPageUiSet var5 = this.getAirUiSet(var1);
      this.mAirPageUiSet = var5;
      ParkPageUiSet var2 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var2;
      FrontArea var6 = var5.getFrontArea();
      var6.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(var6), new UiMgr$$ExternalSyntheticLambda1(var6), new UiMgr$$ExternalSyntheticLambda2(var6), new UiMgr$$ExternalSyntheticLambda3(var6)});
      var6.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
         public void onClickLeft() {
            UiMgr.lambda_4$send(12);
         }

         public void onClickRight() {
            UiMgr.lambda_4$send(11);
         }
      }));
      var6.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            UiMgr.lambda_4$send(14);
         }

         public void onClickUp() {
            UiMgr.lambda_4$send(13);
         }
      }, null, null}));
      var6.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener() {
         public void onLeftSeatClick() {
            UiMgr.lambda_4$send(21);
         }

         public void onRightSeatClick() {
            UiMgr.lambda_4$send(22);
         }
      }));
      var6.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener() {
         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr.lambda_4$send(17);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener() {
         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr.lambda_4$send(18);
         }
      }), null, null});
      var2.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda4(var1));
      var4.setOnSettingItemSelectListener((OnSettingItemSelectListener)var3);
      var4.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var3);
      var4.setOnSettingItemSeekbarSelectListener((OnSettingItemSeekbarSelectListener)var3);
   }

   private static final void _init_$lambda_6(Context var0, MotionEvent var1) {
      Intrinsics.checkNotNullParameter(var0, "$context");
      int var3 = var0.getResources().getDisplayMetrics().widthPixels;
      int var2 = var0.getResources().getDisplayMetrics().heightPixels;
      var3 = (int)(var1.getX() * (160.0F / (float)var3));
      var2 = (int)(var1.getY() * (90.0F / (float)var2));
      if (var1.getAction() == 0) {
         lambda_6$send_5(var3, var2);
      }

   }

   private static final void lambda_4$lambda_0(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[0][it]");
      lambda_4$selectAirPageBtn(var2);
   }

   private static final void lambda_4$lambda_1(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[1][it]");
      lambda_4$selectAirPageBtn(var2);
   }

   private static final void lambda_4$lambda_2(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[2][it]");
      lambda_4$selectAirPageBtn(var2);
   }

   private static final void lambda_4$lambda_3(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "this.lineBtnAction[3][it]");
      lambda_4$selectAirPageBtn(var2);
   }

   private static final void lambda_4$selectAirPageBtn(String var0) {
      switch (var0) {
         case "front_defog":
            lambda_4$send(5);
            break;
         case "rear_defog":
            lambda_4$send(6);
            break;
         case "ac":
            lambda_4$send(2);
            break;
         case "auto":
            lambda_4$send(4);
            break;
         case "in_out_cycle":
            lambda_4$send(7);
      }

   }

   private static final void lambda_4$send(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)var0, 1});
   }

   private static final void lambda_6$send_5(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte)DataHandleUtils.getMsb(var0), (byte)DataHandleUtils.getLsb(var0), (byte)DataHandleUtils.getMsb(var1), (byte)DataHandleUtils.getLsb(var1), 0});
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"},
      d2 = {"Lcom/hzbhd/canbus/car/_404/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/car/_404/UiMgr;Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "value", "onSwitch", "selectSettingsBtn", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
      private final SettingPageUiSet mSettingPageUiSet;
      final UiMgr this$0;

      public SettingHandleListener(UiMgr var1, SettingPageUiSet var2) {
         Intrinsics.checkNotNullParameter(var2, "mSettingPageUiSet");
         this.this$0 = var1;
         super();
         this.mSettingPageUiSet = var2;
      }

      private final void selectSettingsBtn(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         if (var4 != null) {
            var1 = var4.hashCode();
            switch (var1) {
               case 75675904:
                  if (var4.equals("S404_0x61_d0_1")) {
                     selectSettingsBtn$send(1, var3);
                  }
                  break;
               case 75675905:
                  if (var4.equals("S404_0x61_d0_2")) {
                     selectSettingsBtn$send(2, var3);
                  }
                  break;
               case 75675906:
                  if (var4.equals("S404_0x61_d0_3")) {
                     selectSettingsBtn$send(3, var3);
                  }
                  break;
               case 75675907:
                  if (var4.equals("S404_0x61_d0_4")) {
                     selectSettingsBtn$send(4, var3);
                  }
                  break;
               default:
                  switch (var1) {
                     case 75676865:
                        if (var4.equals("S404_0x61_d1_1")) {
                           selectSettingsBtn$send(5, var3);
                        }
                        break;
                     case 75676866:
                        if (var4.equals("S404_0x61_d1_2")) {
                           selectSettingsBtn$send(6, var3);
                        }
                        break;
                     case 75676867:
                        if (var4.equals("S404_0x61_d1_3")) {
                           selectSettingsBtn$send(7, var3);
                        }
                        break;
                     default:
                        switch (var1) {
                           case 75677826:
                              if (var4.equals("S404_0x61_d2_1")) {
                                 selectSettingsBtn$send(8, var3);
                              }
                              break;
                           case 75677827:
                              if (var4.equals("S404_0x61_d2_2")) {
                                 selectSettingsBtn$send(9, var3);
                              }
                        }
                  }
            }
         }

      }

      private static final void selectSettingsBtn$send(int var0, int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var0, (byte)var1, -1, -1});
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
