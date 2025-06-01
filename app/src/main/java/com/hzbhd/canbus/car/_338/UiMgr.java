package com.hzbhd.canbus.car._338;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u0010\u00105\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u0018\u00106\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00107\u001a\u000204H\u0002J\u0010\u00108\u001a\u0002022\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"},
   d2 = {"Lcom/hzbhd/canbus/car/_338/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "onAirBtnClickListenerFrontLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "getOnAirBtnClickListenerFrontLeft", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "setOnAirBtnClickListenerFrontLeft", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;)V", "onAirBtnClickListenerFrontRight", "getOnAirBtnClickListenerFrontRight", "setOnAirBtnClickListenerFrontRight", "onAirBtnClickListenerFrontTop", "getOnAirBtnClickListenerFrontTop", "setOnAirBtnClickListenerFrontTop", "onAirSeatClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirSeatClickListener;", "getOnAirSeatClickListener", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirSeatClickListener;", "setOnAirSeatClickListener", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirSeatClickListener;)V", "onAirTemperatureUpDownClickListenerFrontLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "onAirWindSpeedUpDownClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "getOnAirWindSpeedUpDownClickListener", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "setOnAirWindSpeedUpDownClickListener", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;)V", "onPanoramicItemClickListener", "Lcom/hzbhd/canbus/interfaces/OnPanoramicItemClickListener;", "getOnPanoramicItemClickListener", "()Lcom/hzbhd/canbus/interfaces/OnPanoramicItemClickListener;", "setOnPanoramicItemClickListener", "(Lcom/hzbhd/canbus/interfaces/OnPanoramicItemClickListener;)V", "onSettingItemSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "getOnSettingItemSelectListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "setOnSettingItemSelectListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;)V", "parkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "sendAirData", "", "data0", "", "sendPanoramaInfo", "sendSettingInfo", "data1", "updateUiByDifferentCar", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet airPageUiSet;
   private OnAirBtnClickListener onAirBtnClickListenerFrontLeft;
   private OnAirBtnClickListener onAirBtnClickListenerFrontRight;
   private OnAirBtnClickListener onAirBtnClickListenerFrontTop;
   private OnAirSeatClickListener onAirSeatClickListener;
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerFrontLeft;
   private OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener;
   private OnPanoramicItemClickListener onPanoramicItemClickListener;
   private OnSettingItemSelectListener onSettingItemSelectListener;
   private final ParkPageUiSet parkPageUiSet;
   private final SettingPageUiSet settingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$DGlBVtYwbWkWfN9nzGmBQldo_JQ(UiMgr var0, int var1, int var2, int var3) {
      onSettingItemSelectListener$lambda_4(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$U02nrW_uP8LLT5Ss4PcLPCNUyms(UiMgr var0, int var1) {
      onAirBtnClickListenerFrontRight$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$keZKUKHjTJmqE_zdlHZG_tEn2yk(UiMgr var0, int var1) {
      onAirBtnClickListenerFrontLeft$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$l4sxqHHSAMRpWw8Onvv5Ruy8LVw(UiMgr var0, int var1) {
      onPanoramicItemClickListener$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$zf9Yq1BXPxRsIIJuN6HM400Afuk(UiMgr var0, int var1) {
      onAirBtnClickListenerFrontTop$lambda_0(var0, var1);
   }

   public UiMgr(Context var1) {
      AirPageUiSet var3 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getAirUiSet(context)");
      this.airPageUiSet = var3;
      ParkPageUiSet var2 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getParkPageUiSet(context)");
      this.parkPageUiSet = var2;
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getSettingUiSet(context)");
      this.settingPageUiSet = var4;
      this.onAirTemperatureUpDownClickListenerFrontLeft = (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirData(14);
         }

         public void onClickUp() {
            this.this$0.sendAirData(13);
         }
      });
      this.onAirBtnClickListenerFrontTop = new UiMgr$$ExternalSyntheticLambda0(this);
      this.onAirBtnClickListenerFrontLeft = new UiMgr$$ExternalSyntheticLambda1(this);
      this.onAirBtnClickListenerFrontRight = new UiMgr$$ExternalSyntheticLambda2(this);
      this.onAirSeatClickListener = (OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirData(17);
         }

         public void onRightSeatClick() {
         }
      });
      this.onAirWindSpeedUpDownClickListener = (OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirData(12);
         }

         public void onClickRight() {
            this.this$0.sendAirData(11);
         }
      });
      this.onPanoramicItemClickListener = new UiMgr$$ExternalSyntheticLambda3(this);
      this.onSettingItemSelectListener = new UiMgr$$ExternalSyntheticLambda4(this);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerFrontTop, this.onAirBtnClickListenerFrontLeft, this.onAirBtnClickListenerFrontRight, null});
      var3.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerFrontLeft, null, null});
      var2.setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
      var4.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
   }

   private static final void onAirBtnClickListenerFrontLeft$lambda_1(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirData(6);
      }

   }

   private static final void onAirBtnClickListenerFrontRight$lambda_2(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirData(23);
      }

   }

   private static final void onAirBtnClickListenerFrontTop$lambda_0(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var0.sendAirData(2);
            }
         } else {
            var0.sendAirData(1);
         }
      } else {
         var0.sendAirData(21);
      }

   }

   private static final void onPanoramicItemClickListener$lambda_3(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  var0.sendPanoramaInfo(2);
               }
            } else {
               var0.sendPanoramaInfo(3);
            }
         } else {
            var0.sendPanoramaInfo(4);
         }
      } else {
         var0.sendPanoramaInfo(1);
      }

   }

   private static final void onSettingItemSelectListener$lambda_4(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0 && var2 == 0) {
         var0.sendSettingInfo(1, var3);
      }

   }

   private final void sendAirData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)var1, 1});
   }

   private final void sendPanoramaInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte)var1});
   }

   private final void sendSettingInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   public final OnAirBtnClickListener getOnAirBtnClickListenerFrontLeft() {
      return this.onAirBtnClickListenerFrontLeft;
   }

   public final OnAirBtnClickListener getOnAirBtnClickListenerFrontRight() {
      return this.onAirBtnClickListenerFrontRight;
   }

   public final OnAirBtnClickListener getOnAirBtnClickListenerFrontTop() {
      return this.onAirBtnClickListenerFrontTop;
   }

   public final OnAirSeatClickListener getOnAirSeatClickListener() {
      return this.onAirSeatClickListener;
   }

   public final OnAirWindSpeedUpDownClickListener getOnAirWindSpeedUpDownClickListener() {
      return this.onAirWindSpeedUpDownClickListener;
   }

   public final OnPanoramicItemClickListener getOnPanoramicItemClickListener() {
      return this.onPanoramicItemClickListener;
   }

   public final OnSettingItemSelectListener getOnSettingItemSelectListener() {
      return this.onSettingItemSelectListener;
   }

   public final void setOnAirBtnClickListenerFrontLeft(OnAirBtnClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onAirBtnClickListenerFrontLeft = var1;
   }

   public final void setOnAirBtnClickListenerFrontRight(OnAirBtnClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onAirBtnClickListenerFrontRight = var1;
   }

   public final void setOnAirBtnClickListenerFrontTop(OnAirBtnClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onAirBtnClickListenerFrontTop = var1;
   }

   public final void setOnAirSeatClickListener(OnAirSeatClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onAirSeatClickListener = var1;
   }

   public final void setOnAirWindSpeedUpDownClickListener(OnAirWindSpeedUpDownClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onAirWindSpeedUpDownClickListener = var1;
   }

   public final void setOnPanoramicItemClickListener(OnPanoramicItemClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onPanoramicItemClickListener = var1;
   }

   public final void setOnSettingItemSelectListener(OnSettingItemSelectListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onSettingItemSelectListener = var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      if (this.getCurrentCarId() != 2) {
         this.parkPageUiSet.setShowPanoramic(false);
      }

   }
}
