package com.hzbhd.canbus.car._55;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0017\u0010\u0018¨\u0006\u001e"},
   d2 = {"Lcom/hzbhd/canbus/car/_55/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mAmplifierHandleListener", "Lcom/hzbhd/canbus/car/_55/UiMgr$AmplifierHandleListener;", "mAmplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "mOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "kotlin.jvm.PlatformType", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_55/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "value", "", "windLevel", "setWindLevel", "(I)V", "sendAirConditionerCmd", "", "d0", "AmplifierHandleListener", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;
   private final AmplifierHandleListener mAmplifierHandleListener;
   private final AmplifierPageUiSet mAmplifierPageUiSet;
   private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private final ParkPageUiSet mParkPageUiSet;
   private final SettingHandleListener mSettingHandleListener;
   private final SettingPageUiSet mSettingPageUiSet;
   private int windLevel;

   // $FF: synthetic method
   public static void $r8$lambda$0zGH7xg6ujh5bGzS3IBHZke04jk(RearArea var0, int var1) {
      lambda_10$lambda_9$lambda_8(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$1ZY3_gwZhcHOuu6fCacwVcf0_Ao(FrontArea var0, int var1) {
      lambda_10$lambda_4$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$3Au4_QNf_XWVyRfRqVqkvcqrgkc(OriginalCarDevicePageUiSet var0, int[] var1, Ref.IntRef var2, int[] var3, Ref.IntRef var4, Ref.IntRef var5, int var6) {
      lambda_19$lambda_18(var0, var1, var2, var3, var4, var5, var6);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Dp792V_1LacWyutWYNpvy1ySEgY(OriginalCarDevicePageUiSet var0, int[] var1, Ref.IntRef var2, int[] var3, Ref.IntRef var4, Ref.IntRef var5, int var6) {
      lambda_19$lambda_17(var0, var1, var2, var3, var4, var5, var6);
   }

   // $FF: synthetic method
   public static void $r8$lambda$M4WQ5f5HCgkyfaTepCKXMCxrkq8(FrontArea var0, int var1) {
      lambda_10$lambda_4$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$RE3_Mk9UIGLKj8UBMY_3SlZ4fN8(RearArea var0, int var1) {
      lambda_10$lambda_9$lambda_6(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$TiUVKzurMSyECcvBXWcCZ0LXj5k(int var0, int var1, MotionEvent var2) {
      lambda_16$lambda_15(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$_1avZtLnhjJXX_hBBMiEV7bxN00(FrontArea var0, int var1) {
      lambda_10$lambda_4$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$_NVD2uP8pVTfkxe6V71wVf7KmCk(UiMgr var0, int var1, int var2) {
      lambda_12$lambda_11(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$aVG3Drpm_OlvQySZ3f7rHAufxMc(RearArea var0, int var1) {
      lambda_10$lambda_9$lambda_7(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$lZgJ8tQJ2bkVvsOmKHvap2LX7K8(FrontArea var0, int var1) {
      lambda_10$lambda_4$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$p967lf0eILpbd0dz0kZ5BMqu_Fc(RearArea var0, int var1) {
      lambda_10$lambda_9$lambda_5(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$yzviusG6WLiPpMVcz305Gphdyjw(int var0) {
      lambda_16$lambda_14(var0);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var8 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var8, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var8;
      AirPageUiSet var11 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var11, "getAirUiSet(context)");
      this.mAirPageUiSet = var11;
      AmplifierPageUiSet var7 = this.getAmplifierPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var7, "getAmplifierPageUiSet(context)");
      this.mAmplifierPageUiSet = var7;
      ParkPageUiSet var5 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var5, "getParkPageUiSet(context)");
      this.mParkPageUiSet = var5;
      SettingHandleListener var9 = new SettingHandleListener(var8, var1);
      this.mSettingHandleListener = var9;
      AmplifierHandleListener var6 = new AmplifierHandleListener();
      this.mAmplifierHandleListener = var6;
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(var1);
      this.mOriginalCarDevicePageUiSet = var4;
      FrontArea var10 = var11.getFrontArea();
      var10.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(var10), new UiMgr$$ExternalSyntheticLambda7(var10), new UiMgr$$ExternalSyntheticLambda8(var10), new UiMgr$$ExternalSyntheticLambda9(var10)});
      var10.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            UiMgr var1 = this.this$0;
            var1.setWindLevel(var1.windLevel - 1);
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte)var1.windLevel});
         }

         public void onClickRight() {
            UiMgr var1 = this.this$0;
            var1.setWindLevel(var1.windLevel + 1);
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte)var1.windLevel});
         }
      }));
      var10.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
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
      var10.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirConditionerCmd(17);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirConditionerCmd(18);
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
      var10.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         private int i;
         private final int[] mode;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.mode = new int[]{9, 10, 23, 24};
         }

         public final int getI() {
            return this.i;
         }

         public final int[] getMode() {
            return this.mode;
         }

         public void onLeftSeatClick() {
            UiMgr var3 = this.this$0;
            int[] var2 = this.mode;
            int var1 = this.i++;
            var3.sendAirConditionerCmd(var2[var1 % 4]);
         }

         public void onRightSeatClick() {
            UiMgr var2 = this.this$0;
            int[] var3 = this.mode;
            int var1 = this.i++;
            var2.sendAirConditionerCmd(var3[var1 % 4]);
         }

         public final void setI(int var1) {
            this.i = var1;
         }
      }));
      RearArea var17 = var11.getRearArea();
      var17.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda10(var17), new UiMgr$$ExternalSyntheticLambda11(var17), new UiMgr$$ExternalSyntheticLambda12(var17), new UiMgr$$ExternalSyntheticLambda1(var17)});
      var17.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirConditionerCmd(43);
         }

         public void onClickRight() {
            this.this$0.sendAirConditionerCmd(42);
         }
      }));
      var17.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirConditionerCmd(33);
         }

         public void onClickUp() {
            this.this$0.sendAirConditionerCmd(32);
         }
      }, null}));
      var17.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirConditionerCmd(62);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirConditionerCmd(62);
         }
      }));
      var8.setOnSettingItemSelectListener((OnSettingItemSelectListener)var9);
      var8.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var9);
      var8.setOnSettingItemSeekbarSelectListener((OnSettingItemSeekbarSelectListener)var9);
      var8.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda2(this));
      var7.setOnAmplifierPositionListener((OnAmplifierPositionListener)var6);
      var7.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)var6);
      int var2 = var1.getResources().getDisplayMetrics().widthPixels;
      int var3 = var1.getResources().getDisplayMetrics().heightPixels;
      var5.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda3());
      var5.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda4(var2, var3));
      int[] var14 = new int[]{1, 2};
      int[] var15 = new int[]{1, 2};
      Ref.IntRef var12 = new Ref.IntRef();
      Ref.IntRef var13 = new Ref.IntRef();
      Ref.IntRef var16 = new Ref.IntRef();
      var4.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda5(var4, var14, var12, var15, var13, var16));
      var4.setOnOriginalTopBtnClickListeners(new UiMgr$$ExternalSyntheticLambda6(var4, var14, var12, var15, var13, var16));
   }

   private static final void lambda_10$lambda_4$lambda_0(FrontArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_10$lambda_4$lambda_1(FrontArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_10$lambda_4$lambda_2(FrontArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_10$lambda_4$lambda_3(FrontArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_10$lambda_9$lambda_5(RearArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_10$lambda_9$lambda_6(RearArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_10$lambda_9$lambda_7(RearArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_10$lambda_9$lambda_8(RearArea var0, int var1) {
      SingletonForKt var2 = SingletonForKt.INSTANCE;
      String var3 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var2.sendAirBtnData(var3);
   }

   private static final void lambda_12$lambda_11(UiMgr var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"S55_OriginalCarScreen_17")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, -1});
         Toast.makeText(InitUtilsKt.getMContext(), (CharSequence)"SUCCESS!", 0).show();
      }

   }

   private static final void lambda_16$lambda_14(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 == 2) {
               lambda_16$sendPanoramaCmd(3);
            }
         } else {
            lambda_16$sendPanoramaCmd(2);
         }
      } else {
         lambda_16$sendPanoramaCmd(1);
      }

   }

   private static final void lambda_16$lambda_15(int var0, int var1, MotionEvent var2) {
      int var3 = (int)(var2.getX() * (1560.0F / (float)var0));
      int var4 = (int)(var2.getY() * (1900.0F / (float)var1));
      if (var2.getAction() != 0 && var2.getAction() == 1) {
         lambda_16$lambda_15$sendPanoramicItem(DataHandleUtils.getMsb(var3), DataHandleUtils.getLsb(var4), DataHandleUtils.getMsb(var4), DataHandleUtils.getLsb(var4));
      }

      Log.i("lyn", " x:" + var3 + ", y:" + var4 + " \n width:" + var0 + ", high:" + var1);
   }

   private static final void lambda_16$lambda_15$sendPanoramicItem(int var0, int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte)var0, (byte)var1, (byte)var2, (byte)var3, 0});
   }

   private static final void lambda_16$sendPanoramaCmd(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte)var0, -1});
   }

   private static final void lambda_19$lambda_17(OriginalCarDevicePageUiSet var0, int[] var1, Ref.IntRef var2, int[] var3, Ref.IntRef var4, Ref.IntRef var5, int var6) {
      Intrinsics.checkNotNullParameter(var1, "$state");
      Intrinsics.checkNotNullParameter(var2, "$i");
      Intrinsics.checkNotNullParameter(var3, "$anotherState");
      Intrinsics.checkNotNullParameter(var4, "$i1");
      Intrinsics.checkNotNullParameter(var5, "$i2");
      String var7 = var0.getRowBottomBtnAction()[var6];
      Intrinsics.checkNotNullExpressionValue(var7, "this.rowBottomBtnAction[it]");
      lambda_19$select(var1, var2, var3, var4, var5, var7);
   }

   private static final void lambda_19$lambda_18(OriginalCarDevicePageUiSet var0, int[] var1, Ref.IntRef var2, int[] var3, Ref.IntRef var4, Ref.IntRef var5, int var6) {
      Intrinsics.checkNotNullParameter(var1, "$state");
      Intrinsics.checkNotNullParameter(var2, "$i");
      Intrinsics.checkNotNullParameter(var3, "$anotherState");
      Intrinsics.checkNotNullParameter(var4, "$i1");
      Intrinsics.checkNotNullParameter(var5, "$i2");
      String var7 = var0.getRowTopBtnAction()[var6];
      Intrinsics.checkNotNullExpressionValue(var7, "this.rowTopBtnAction[it]");
      lambda_19$select(var1, var2, var3, var4, var5, var7);
   }

   private static final void lambda_19$select(int[] var0, Ref.IntRef var1, int[] var2, Ref.IntRef var3, Ref.IntRef var4, String var5) {
      int var6;
      switch (var5) {
         case "am":
            lambda_19$sendF1(3, 0);
            break;
         case "fm":
            lambda_19$sendF1(3, 1);
            break;
         case "up":
            lambda_19$send(7, 0);
            break;
         case "down":
            lambda_19$send(7, 1);
            break;
         case "scan":
            var6 = var3.element++;
            lambda_19$sendF1(5, var2[var6 % 2]);
            break;
         case "power":
            var6 = var1.element++;
            lambda_19$send$default(var0[var6 % 2], 0, 2, (Object)null);
            break;
         case "refresh":
            var6 = var4.element++;
            lambda_19$sendF1(6, var2[var6 % 2]);
      }

   }

   private static final void lambda_19$send(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -84, (byte)var0, (byte)var1});
   }

   // $FF: synthetic method
   static void lambda_19$send$default(int var0, int var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = 0;
      }

      lambda_19$send(var0, var1);
   }

   private static final void lambda_19$sendF1(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -15, (byte)var0, (byte)var1});
   }

   // $FF: synthetic method
   static void lambda_19$sendF1$default(int var0, int var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = 0;
      }

      lambda_19$sendF1(var0, var1);
   }

   private final void sendAirConditionerCmd(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   private final void setWindLevel(int var1) {
      int var2;
      if (var1 > 7) {
         var2 = 7;
      } else {
         var2 = var1;
         if (var1 < 0) {
            var2 = 0;
         }
      }

      this.windLevel = var2;
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0002¨\u0006\u0010"},
      d2 = {"Lcom/hzbhd/canbus/car/_55/UiMgr$AmplifierHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "()V", "position", "", "amplifierPosition", "Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierPosition;", "value", "", "progress", "amplifierBand", "Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierBand;", "sendAmplifierCmd", "cmd", "param", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class AmplifierHandleListener implements OnAmplifierPositionListener, OnAmplifierSeekBarListener {
      private final void sendAmplifierCmd(int var1, int var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte)var1, (byte)var2});
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
         int var3 = WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)MsgMgrKt.reverse(var2 + 9, 18)});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 9)});
         }

      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "amplifierBand");
         int var3 = WhenMappings.$EnumSwitchMapping$1[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 != 4) {
                     if (var3 != 5) {
                        return;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)var2});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)var2});
               }
            } else {
               this.sendAmplifierCmd(1, 255);
            }
         } else {
            this.sendAmplifierCmd(1, 1);
         }

      }

      @Metadata(
         k = 3,
         mv = {1, 7, 1},
         xi = 48
      )
      public final class WhenMappings {
         public static final int[] $EnumSwitchMapping$0;
         public static final int[] $EnumSwitchMapping$1;

         static {
            int[] var0 = new int[AmplifierActivity.AmplifierPosition.values().length];
            var0[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
            var0[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
            $EnumSwitchMapping$0 = var0;
            var0 = new int[AmplifierActivity.AmplifierBand.values().length];
            var0[AmplifierActivity.AmplifierBand.VOLUME_Plus.ordinal()] = 1;
            var0[AmplifierActivity.AmplifierBand.VOLUME_Min.ordinal()] = 2;
            var0[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
            var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 5;
            $EnumSwitchMapping$1 = var0;
         }
      }
   }

   @Metadata(
      d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0016J \u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"},
      d2 = {"Lcom/hzbhd/canbus/car/_55/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "context", "Landroid/content/Context;", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "progressORvalue", "onSwitch", "value", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
      private final Context context;
      private final SettingPageUiSet mSettingPageUiSet;

      public SettingHandleListener(SettingPageUiSet var1, Context var2) {
         Intrinsics.checkNotNullParameter(var1, "mSettingPageUiSet");
         Intrinsics.checkNotNullParameter(var2, "context");
         super();
         this.mSettingPageUiSet = var1;
         this.context = var2;
      }

      public final Context getContext() {
         return this.context;
      }

      public final SettingPageUiSet getMSettingPageUiSet() {
         return this.mSettingPageUiSet;
      }

      public void onClickItem(int var1, int var2, int var3) {
         SingletonForKt.INSTANCE.sendSettingsData(this.mSettingPageUiSet, var1, var2, var3);
         MsgMgrInterface var4;
         if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"language_setup")) {
            var4 = MsgMgrFactory.getCanMsgMgr(this.context);
            Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.MsgMgr");
            ((MsgMgr)var4).updateSetting();
         }

         if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"S18_Car_0")) {
            var4 = MsgMgrFactory.getCanMsgMgr(this.context);
            Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.MsgMgr");
            ((MsgMgr)var4).updateSetting();
         }

      }

      public void onSwitch(int var1, int var2, int var3) {
         SingletonForKt.INSTANCE.sendSettingsData(this.mSettingPageUiSet, var1, var2, var3);
      }
   }
}
