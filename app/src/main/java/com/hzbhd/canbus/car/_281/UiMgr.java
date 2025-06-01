package com.hzbhd.canbus.car._281;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"},
   d2 = {"Lcom/hzbhd/canbus/car/_281/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getMAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "selectABtn", "", "btn", "", "sendACmd", "d0", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$OuXu_ZXa2FQYbHNkfQNQC55um14(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Ueer5jx6sEiGQSZHjjMkb_bgLTE(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$YrdDMGnBKJ9WnQnV70WvGGH3pvo(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$qZo1I2pZujVWQKA_yNs4tZkUveA(UiMgr var0, FrontArea var1, int var2) {
      lambda_4$lambda_3(var0, var1, var2);
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
            this.this$0.sendACmd(14);
         }

         public void onClickRight() {
            this.this$0.sendACmd(13);
         }
      }));
      var3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendACmd(2);
         }

         public void onClickUp() {
            this.this$0.sendACmd(1);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendACmd(4);
         }

         public void onClickUp() {
            this.this$0.sendACmd(3);
         }
      })});
      var3.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendACmd(11);
         }

         public void onRightSeatClick() {
            this.this$0.sendACmd(11);
         }
      }));
   }

   private static final void lambda_4$lambda_0(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_4$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_4$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_4$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectABtn(var3);
   }

   private final void selectABtn(String var1) {
      switch (var1) {
         case "pollrn_removal":
            this.sendACmd(16);
            return;
         case "front_defog":
            this.sendACmd(18);
            return;
         case "clean_air":
            this.sendACmd(10);
            return;
         case "max_front":
            this.sendACmd(8);
            return;
         case "ac":
            this.sendACmd(17);
            return;
         case "auto":
            this.sendACmd(6);
            return;
         case "dual":
            this.sendACmd(12);
            return;
         case "rear":
            this.sendACmd(9);
            return;
         case "power":
            this.sendACmd(5);
            return;
         case "swing":
            this.sendACmd(15);
            return;
         case "cycle_in_out_close":
            this.sendACmd(7);
            return;
      }

      this.sendACmd(0);
   }

   private final void sendACmd(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -31, (byte)var1, 1});
   }

   public final AirPageUiSet getMAirPageUiSet() {
      return this.mAirPageUiSet;
   }
}
