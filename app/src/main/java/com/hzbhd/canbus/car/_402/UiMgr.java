package com.hzbhd.canbus.car._402;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"},
   d2 = {"Lcom/hzbhd/canbus/car/_402/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   // $FF: synthetic method
   public static void $r8$lambda$Ok_HhCbP5JdDT7k8jFvVLdl_DJo(FrontArea var0, int var1) {
      lambda_4$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$dGt_BmN4Fw3L9emQGkhAQwhqJCU(FrontArea var0, int var1) {
      lambda_4$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$hQBVgYUb0ZENgxt8HN1wVZwZug4(FrontArea var0, int var1) {
      lambda_4$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$vQZVd21LY9GOoa_h9I_9vzpzFas(FrontArea var0, int var1) {
      lambda_4$lambda_0(var0, var1);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      FrontArea var3 = this.getAirUiSet(var1).getFrontArea();
      var3.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(var3), new UiMgr$$ExternalSyntheticLambda1(var3), new UiMgr$$ExternalSyntheticLambda2(var3), new UiMgr$$ExternalSyntheticLambda3(var3)});
      var3.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
         public void onClickLeft() {
            UiMgr.lambda_4$send(12);
         }

         public void onClickRight() {
            UiMgr.lambda_4$send(11);
         }
      }));
      var3.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            UiMgr.lambda_4$send(14);
         }

         public void onClickUp() {
            UiMgr.lambda_4$send(13);
         }
      }, null, null}));
      Ref.ObjectRef var2 = new Ref.ObjectRef();
      var2.element = new int[]{26, 27, 28, 29};
      var3.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(var2, new Ref.IntRef()) {
         final Ref.IntRef $i;
         final Ref.ObjectRef $mode;

         {
            this.$mode = var1;
            this.$i = var2;
         }

         public void onLeftSeatClick() {
            int[] var2 = (int[])this.$mode.element;
            int var1 = this.$i.element++;
            UiMgr.lambda_4$send(var2[var1 % 4]);
         }

         public void onRightSeatClick() {
            int[] var2 = (int[])this.$mode.element;
            int var1 = this.$i.element++;
            UiMgr.lambda_4$send(var2[var1 % 4]);
         }
      }));
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
         case "power":
            lambda_4$send(1);
            break;
         case "in_out_cycle":
            lambda_4$send(7);
      }

   }

   private static final void lambda_4$send(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)var0, 1});
   }
}
