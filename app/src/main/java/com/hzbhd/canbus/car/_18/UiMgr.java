package com.hzbhd.canbus.car._18;

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
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010\u001d\u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001e\u001a\u00020\u001fJ \u0010 \u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001fJ\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001fH\u0002J \u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0006H\u0002J\u0010\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0006H\u0002J\u000e\u0010/\u001a\u00020'2\u0006\u00100\u001a\u00020\u0006J\u000e\u00101\u001a\u00020'2\u0006\u00100\u001a\u00020\u0006J \u00102\u001a\u00020'2\u0006\u0010.\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006H\u0002J\u0012\u00104\u001a\u00020'2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0004R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"},
   d2 = {"Lcom/hzbhd/canbus/car/_18/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airRearWindMode", "", "getAirRearWindMode", "()I", "setAirRearWindMode", "(I)V", "airWindMode", "getAirWindMode", "setAirWindMode", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mAmplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "mMsgMgr", "Lcom/hzbhd/canbus/car/_18/MsgMgr;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getMsgMgr", "getSettingLeftIndexes", "titleSrn", "", "getSettingRightIndex", "leftTitleSrn", "rightTitleStn", "isPanoramicUseBtn", "", "isPanoramicUseCoordinate", "selectABtn", "", "btn", "selectSBtn", "leftPos", "rightPos", "param", "sendACmd", "d0", "sendCarModelData", "d1", "sendPanoramaCmd", "sendSCmd", "d2", "updateUiByDifferentCar", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private int airRearWindMode;
   private int airWindMode;
   private final AirPageUiSet mAirPageUiSet;
   private final AmplifierPageUiSet mAmplifierPageUiSet;
   private Context mContext;
   private MsgMgr mMsgMgr;
   private final ParkPageUiSet mParkPageUiSet;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$3rKr3fP7Br7pMCwUQG9c7yQP598(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$3sJl_SCYakyDCirmLM8CCgrLFvU(UiMgr var0, int var1, int var2, int var3) {
      lambda_18$lambda_15(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$9LtJrIPgabKWLGqLE5E4XeRqR4A(UiMgr var0, RearArea var1, int var2) {
      lambda_10$lambda_9(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$BKrwDAMRIzjZgioyB3CMPPc4IeY(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_4(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$O8ylY6X8vEPLsKksqh1aN3MzBGo(UiMgr var0, RearArea var1, int var2) {
      lambda_10$lambda_7(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$RExTkFgWPEtnkI5I7sKrARok6Ps(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$lKmPyaeaXz3gH2c_W9fT3g3cykg(UiMgr var0, int var1, int var2, int var3) {
      lambda_18$lambda_14(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$lmAkErZXfCWBIS_PHmKM_ot6_jw(UiMgr var0, Context var1, int var2, int var3) {
      lambda_18$lambda_17(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$luZ1l6yObVzDkCIf2kRaSYk5Rp4(UiMgr var0, Ref.IntRef var1, Ref.IntRef var2, Ref.IntRef var3, Ref.IntRef var4, Ref.IntRef var5, Context var6, MotionEvent var7) {
      lambda_13$lambda_12(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   public static void $r8$lambda$o5fh6n1ryLBUut192EHKek1ajEk(UiMgr var0, RearArea var1, int var2) {
      lambda_10$lambda_6(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$oZdgZRG6KDFE6TrrnTtJwDxc6qY(UiMgr var0, FrontArea var1, int var2) {
      lambda_5$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$qFxXYy82sQTB7AFLJowVm1Q4wC4(UiMgr var0, RearArea var1, int var2) {
      lambda_10$lambda_8(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$qhgDtqMjycUnDT_BZJOhg9TeyUc(UiMgr var0, int var1, int var2, int var3) {
      lambda_18$lambda_16(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      AirPageUiSet var5 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var5, "getAirUiSet(context)");
      this.mAirPageUiSet = var5;
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getAmplifierPageUiSet(context)");
      this.mAmplifierPageUiSet = var4;
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getParkPageUiSet(context)");
      this.mParkPageUiSet = var3;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var2;
      this.mContext = var1;
      if (var1.getResources().getConfiguration().orientation == 2) {
         RearArea var6 = var5.getRearArea();
         var6.setShowCenterWheel(false);
         var6.setShowLeftWheel(true);
         var6.setShowRightWheel(true);
         var6.setWindMaxLevel(7);
      }

      FrontArea var12 = var5.getFrontArea();
      var12.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendACmd(21);
         }

         public void onRightSeatClick() {
            this.this$0.sendACmd(22);
         }
      }));
      var12.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var12), new UiMgr$$ExternalSyntheticLambda7(this, var12), new UiMgr$$ExternalSyntheticLambda8(this, var12), new UiMgr$$ExternalSyntheticLambda9(this, var12)});
      var12.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendACmd(12);
         }

         public void onClickRight() {
            this.this$0.sendACmd(11);
         }
      }));
      var12.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendACmd(14);
         }

         public void onClickUp() {
            this.this$0.sendACmd(13);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendACmd(16);
         }

         public void onClickUp() {
            this.this$0.sendACmd(15);
         }
      })});
      var12.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendACmd(17);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendACmd(18);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendACmd(23);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendACmd(24);
         }
      })});
      RearArea var10 = var5.getRearArea();
      var10.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.getAirRearWindMode() == 0) {
               this.this$0.sendACmd(81);
               this.this$0.setAirRearWindMode(1);
            } else if (this.this$0.getAirRearWindMode() == 1) {
               this.this$0.sendACmd(82);
               this.this$0.setAirRearWindMode(2);
            } else if (this.this$0.getAirRearWindMode() == 2) {
               this.this$0.sendACmd(83);
               this.this$0.setAirRearWindMode(0);
            }

         }

         public void onRightSeatClick() {
            if (this.this$0.getAirRearWindMode() == 0) {
               this.this$0.sendACmd(81);
               this.this$0.setAirRearWindMode(1);
            } else if (this.this$0.getAirRearWindMode() == 1) {
               this.this$0.sendACmd(82);
               this.this$0.setAirRearWindMode(2);
            } else if (this.this$0.getAirRearWindMode() == 2) {
               this.this$0.sendACmd(83);
               this.this$0.setAirRearWindMode(0);
            }

         }
      }));
      var10.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda10(this, var10), new UiMgr$$ExternalSyntheticLambda11(this, var10), new UiMgr$$ExternalSyntheticLambda12(this, var10), new UiMgr$$ExternalSyntheticLambda1(this, var10)});
      var10.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendACmd(43);
         }

         public void onClickRight() {
            this.this$0.sendACmd(42);
         }
      }));
      var10.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendACmd(33);
         }

         public void onClickUp() {
            this.this$0.sendACmd(32);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendACmd(65);
         }

         public void onClickUp() {
            this.this$0.sendACmd(64);
         }
      })});
      var10.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
            this.this$0.sendACmd(69);
         }

         public void onClickPlus() {
            this.this$0.sendACmd(67);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
            this.this$0.sendACmd(70);
         }

         public void onClickPlus() {
            this.this$0.sendACmd(68);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendACmd(69);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendACmd(70);
         }
      })});
      var4.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(var2 + 7, 0, 14), 0, 2, (Object)null)});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)DataHandleUtils.rangeNumber(var2 + 7, 0, 14)});
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
               var0[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
               var0[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      var4.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var4 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var4 != 1) {
               int var3 = 5;
               if (var4 != 2) {
                  if (var4 != 3) {
                     if (var4 != 4) {
                        return;
                     }

                     var2 -= GeneralAmplifierData.volume;
                     if (var2 > 5) {
                        var2 = var3;
                     }

                     var3 = var2;
                     if (var2 < -5) {
                        var3 = -5;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var3});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)var2});
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
               var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
               var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
               var0[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      Ref.IntRef var13 = new Ref.IntRef();
      Ref.IntRef var8 = new Ref.IntRef();
      Ref.IntRef var9 = new Ref.IntRef();
      Ref.IntRef var11 = new Ref.IntRef();
      Ref.IntRef var7 = new Ref.IntRef();
      if (this.isPanoramicUseBtn() || this.isPanoramicUseCoordinate()) {
         lambda_13$initPanoramicBtnPara(var13, var8, var9, var11, var7, var1);
         var3.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda2(this, var8, var11, var9, var7, var13, var1));
      }

      var2.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda3(this));
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda4(this));
      var2.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda5(this));
      var2.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda6(this, var1));
   }

   private final MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._18.MsgMgr");
         this.mMsgMgr = (MsgMgr)var2;
      }

      return this.mMsgMgr;
   }

   private final boolean isPanoramicUseBtn() {
      boolean var1;
      if (this.getCurrentCarId() != 4 && this.getCurrentCarId() != 10) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private final boolean isPanoramicUseCoordinate() {
      boolean var1;
      if (this.getCurrentCarId() == 4 && this.getCurrentCarId() == 10) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private static final void lambda_10$lambda_6(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_10$lambda_7(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_10$lambda_8(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_10$lambda_9(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_13$initPanoramicBtnPara(Ref.IntRef var0, Ref.IntRef var1, Ref.IntRef var2, Ref.IntRef var3, Ref.IntRef var4, Context var5) {
      var0.element = var5.getResources().getDisplayMetrics().widthPixels;
      var1.element = var5.getResources().getDisplayMetrics().heightPixels;
      var2.element = CommUtil.getDimenByResId(var5, "dp140");
      var3.element = CommUtil.getDimenByResId(var5, "dp100");
      var4.element = var0.element * 3 / 4 - 20;
   }

   private static final void lambda_13$lambda_12(UiMgr var0, Ref.IntRef var1, Ref.IntRef var2, Ref.IntRef var3, Ref.IntRef var4, Ref.IntRef var5, Context var6, MotionEvent var7) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$mMaxHigh");
      Intrinsics.checkNotNullParameter(var2, "$mBtnHeight");
      Intrinsics.checkNotNullParameter(var3, "$mBtnWidth");
      Intrinsics.checkNotNullParameter(var4, "$mBtnThreeEnd");
      Intrinsics.checkNotNullParameter(var5, "$mMaxWidth");
      Intrinsics.checkNotNullParameter(var6, "$context");
      int var12 = (int)var7.getX();
      int var11 = (int)var7.getY();
      int var13 = var7.getAction();
      if (var0.isPanoramicUseBtn()) {
         if (var13 == 0 && var11 >= var1.element - var2.element && var11 <= var1.element) {
            if (var12 <= var3.element) {
               var0.sendPanoramaCmd(1);
            } else if (var12 >= var4.element - var3.element && var12 <= var4.element) {
               var0.sendPanoramaCmd(3);
            } else if (var12 >= var5.element - var3.element) {
               var0.sendPanoramaCmd(4);
            }
         }
      } else if (var13 == 1) {
         Exception var10000;
         label47: {
            boolean var10001;
            try {
               int var14 = var6.getResources().getDisplayMetrics().widthPixels;
               var13 = var6.getResources().getDisplayMetrics().heightPixels;
               var12 = var12 * 1023 / var14;
               var11 = var11 * 1000 / var13;
            } catch (Exception var16) {
               var10000 = var16;
               var10001 = false;
               break label47;
            }

            byte var8 = (byte)(var12 & 255);
            byte var10 = (byte)(var12 >> 8 & 255);
            byte var9 = (byte)(var11 & 255);

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, var10, var8, (byte)(var11 >> 8 & 255), var9, 0});
               var0.playBeep();
               return;
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
            }
         }

         Exception var17 = var10000;
         Log.e("18UI", Log.getStackTraceString((Throwable)var17));
      }

   }

   private static final void lambda_18$lambda_14(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.selectSBtn(var1, var2, var3);
   }

   private static final void lambda_18$lambda_15(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.selectSBtn(var1, var2, var3);
   }

   private static final void lambda_18$lambda_16(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.selectSBtn(var1, var2, var3);
   }

   private static final void lambda_18$lambda_17(UiMgr var0, Context var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$context");
      if (var2 == var0.getSettingLeftIndexes(var1, "S18Title1")) {
         if (var3 == var0.getSettingRightIndex(var1, "S18Title1", "_18_park_0")) {
            var0.sendSCmd(1, 10, 1);
         }

         if (var3 == var0.getSettingRightIndex(var1, "S18Title1", "_18_park_1")) {
            var0.sendSCmd(1, 10, 2);
         }
      }

      if (var2 == var0.getSettingLeftIndexes(var1, "_18_reset")) {
         Context var4;
         if (var3 == var0.getSettingRightIndex(var1, "_18_reset", "_18_reset_1")) {
            var0.sendSCmd(4, 1, 1);
            var4 = var0.mContext;
            Toast.makeText(var4, (CharSequence)var4.getString(2131758762), 0).show();
         }

         if (var3 == var0.getSettingRightIndex(var1, "_18_reset", "_18_reset_2")) {
            var0.sendSCmd(4, 2, 1);
            var4 = var0.mContext;
            Toast.makeText(var4, (CharSequence)var4.getString(2131758763), 0).show();
         }

         if (var3 == var0.getSettingRightIndex(var1, "_18_reset", "_18_reset_3")) {
            var0.sendSCmd(4, 2, 2);
            var4 = var0.mContext;
            Toast.makeText(var4, (CharSequence)var4.getString(2131758764), 0).show();
         }

         if (var3 == var0.getSettingRightIndex(var1, "_18_reset", "_18_reset_4")) {
            var0.sendSCmd(4, 4, 1);
            var4 = var0.mContext;
            Toast.makeText(var4, (CharSequence)var4.getString(2131758765), 0).show();
         }

         if (var3 == var0.getSettingRightIndex(var1, "_18_reset", "_18_reset_5")) {
            var0.sendSCmd(4, 5, 1);
            Context var5 = var0.mContext;
            Toast.makeText(var5, (CharSequence)var5.getString(2131758766), 0).show();
         }
      }

   }

   private static final void lambda_5$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_5$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_5$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectABtn(var3);
   }

   private static final void lambda_5$lambda_4(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectABtn(var3);
   }

   private final void selectABtn(String var1) {
      switch (var1) {
         case "front_defog":
            this.sendACmd(5);
            break;
         case "auto_r":
            this.sendACmd(66);
            break;
         case "clean_air":
            this.sendACmd(25);
            break;
         case "rear_auto":
            this.sendACmd(66);
            break;
         case "rear_lock":
            this.sendACmd(34);
            break;
         case "rear_defog":
            this.sendACmd(6);
            break;
         case "rear_power":
            this.sendACmd(46);
            break;
         case "ac":
            this.sendACmd(2);
            break;
         case "eco":
            this.sendACmd(35);
            break;
         case "auto":
            this.sendACmd(4);
            break;
         case "dual":
            this.sendACmd(41);
            break;
         case "sync":
            this.sendACmd(3);
            break;
         case "power":
            this.sendACmd(1);
            break;
         case "in_out_cycle":
            this.sendACmd(7);
      }

   }

   private final void selectSBtn(int var1, int var2, int var3) {
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var5 != null) {
         int var4 = var5.hashCode();
         MsgMgr var6;
         if (var4 != 748741536) {
            if (var4 != 899845314) {
               switch (var4) {
                  case -1372525502:
                     if (var5.equals("S18_lock_1")) {
                        this.sendSCmd(1, 1, var3);
                     }
                     break;
                  case -1372525501:
                     if (var5.equals("S18_lock_2")) {
                        this.sendSCmd(1, 2, var3);
                     }
                     break;
                  case -1372525500:
                     if (var5.equals("S18_lock_3")) {
                        this.sendSCmd(1, 3, var3);
                     }
                     break;
                  case -1372525499:
                     if (var5.equals("S18_lock_4")) {
                        this.sendSCmd(1, 4, var3);
                     }
                     break;
                  case -1372525498:
                     if (var5.equals("S18_lock_5")) {
                        this.sendSCmd(1, 5, var3);
                     }
                     break;
                  case -1372525497:
                     if (var5.equals("S18_lock_6")) {
                        this.sendSCmd(1, 6, var3);
                     }
                     break;
                  case -1372525496:
                     if (var5.equals("S18_lock_7")) {
                        this.sendSCmd(1, 7, var3);
                     }
                     break;
                  default:
                     switch (var4) {
                        case -1270491327:
                           if (var5.equals("S18_park_1")) {
                              this.sendSCmd(1, 8, var3);
                           }
                           break;
                        case -1270491326:
                           if (var5.equals("S18_park_2")) {
                              this.sendSCmd(1, 9, var3);
                           }
                           break;
                        case -1270491325:
                           if (var5.equals("S18_park_3")) {
                              this.sendSCmd(1, 10, 1);
                           }
                           break;
                        case -1270491324:
                           if (var5.equals("S18_park_4")) {
                              this.sendSCmd(1, 10, 2);
                           }
                           break;
                        default:
                           switch (var4) {
                              case 233233859:
                                 if (var5.equals("S18_light_1")) {
                                    this.sendSCmd(1, 11, var3);
                                 }
                                 break;
                              case 233233860:
                                 if (var5.equals("S18_light_2")) {
                                    this.sendSCmd(3, 3, var3);
                                 }
                                 break;
                              case 233233861:
                                 if (var5.equals("S18_light_3")) {
                                    this.sendSCmd(3, 2, var3);
                                 }
                                 break;
                              case 233233862:
                                 if (var5.equals("S18_light_4")) {
                                    this.sendSCmd(3, 1, var3);
                                 }
                                 break;
                              default:
                                 switch (var4) {
                                    case 776685495:
                                       if (var5.equals("S18_air_1")) {
                                          this.sendSCmd(1, 12, var3);
                                       }
                                       break;
                                    case 776685496:
                                       if (var5.equals("S18_air_2")) {
                                          this.sendSCmd(1, 13, var3);
                                       }
                                       break;
                                    case 776685497:
                                       if (var5.equals("S18_air_3")) {
                                          this.sendSCmd(1, 15, var3);
                                       }
                                       break;
                                    case 776685498:
                                       if (var5.equals("S18_air_4")) {
                                          this.sendSCmd(2, 5, var3);
                                       }
                                       break;
                                    default:
                                       switch (var4) {
                                          case 1356018684:
                                             if (var5.equals("_18_other_settings_0")) {
                                                this.sendSCmd(1, 16, var3);
                                             }
                                             break;
                                          case 1356018685:
                                             if (var5.equals("_18_other_settings_1")) {
                                                this.sendSCmd(1, 18, var3);
                                             }
                                             break;
                                          case 1356018686:
                                             if (var5.equals("_18_other_settings_2")) {
                                                this.sendSCmd(1, 17, var3);
                                             }
                                             break;
                                          case 1356018687:
                                             if (var5.equals("_18_other_settings_3")) {
                                                this.sendSCmd(1, 19, var3);
                                             }
                                             break;
                                          default:
                                             switch (var4) {
                                                case 1430441821:
                                                   if (var5.equals("S18_remote_1")) {
                                                      this.sendSCmd(2, 1, var3);
                                                   }
                                                   break;
                                                case 1430441822:
                                                   if (var5.equals("S18_remote_2")) {
                                                      this.sendSCmd(2, 4, var3);
                                                   }
                                                   break;
                                                case 1430441823:
                                                   if (var5.equals("S18_remote_3")) {
                                                      this.sendSCmd(2, 2, var3);
                                                   }
                                                   break;
                                                case 1430441824:
                                                   if (var5.equals("S18_remote_4")) {
                                                      this.sendSCmd(2, 3, var3);
                                                   }
                                                   break;
                                                case 1430441825:
                                                   if (var5.equals("S18_remote_5")) {
                                                      this.sendSCmd(1, 14, var3);
                                                   }
                                                   break;
                                                default:
                                                   switch (var4) {
                                                      case 2106546174:
                                                         if (var5.equals("S18xA61")) {
                                                            selectSBtn$send0xADData(7, var3);
                                                         }
                                                         break;
                                                      case 2106546175:
                                                         if (var5.equals("S18xA62")) {
                                                            selectSBtn$send0xADData(8, var3);
                                                         }
                                                   }
                                             }
                                       }
                                 }
                           }
                     }
               }
            } else if (var5.equals("_18_language_setting")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
               var6 = this.getMsgMgr(this.mContext);
               if (var6 != null) {
                  var6.updateSettings(var1, var2, var3);
               }
            }
         } else if (var5.equals("S18_Car_0")) {
            this.sendCarModelData(var3);
            var6 = this.getMsgMgr(this.mContext);
            if (var6 != null) {
               var6.updateSettings(var1, var2, var3);
            }
         }
      }

   }

   private static final void selectSBtn$send0xADData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte)var0, (byte)var1});
   }

   private final void sendACmd(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   private final void sendSCmd(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte)var1, (byte)var2, (byte)var3});
   }

   public final int getAirRearWindMode() {
      return this.airRearWindMode;
   }

   public final int getAirWindMode() {
      return this.airWindMode;
   }

   public final Context getMContext() {
      return this.mContext;
   }

   public final int getSettingLeftIndexes(Context var1, String var2) {
      Intrinsics.checkNotNullParameter(var2, "titleSrn");
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var6.iterator();
      int var4 = var6.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         if (Intrinsics.areEqual((Object)var2, (Object)((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   public final int getSettingRightIndex(Context var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var2, "leftTitleSrn");
      Intrinsics.checkNotNullParameter(var3, "rightTitleStn");
      List var8 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var10 = var8.iterator();
      int var6 = var8.size();

      for(int var4 = 0; var4 < var6; ++var4) {
         SettingPageUiSet.ListBean var11 = (SettingPageUiSet.ListBean)var10.next();
         if (Intrinsics.areEqual((Object)var2, (Object)var11.getTitleSrn())) {
            var8 = var11.getItemList();
            Iterator var9 = var8.iterator();
            int var7 = var8.size();

            for(int var5 = 0; var5 < var7; ++var5) {
               if (Intrinsics.areEqual((Object)var3, (Object)((SettingPageUiSet.ListBean.ItemListBean)var9.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public final void sendCarModelData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)(var1 + 128), 1});
   }

   public final void sendPanoramaCmd(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, var2, 0});
      this.playBeep();
   }

   public final void setAirRearWindMode(int var1) {
      this.airRearWindMode = var1;
   }

   public final void setAirWindMode(int var1) {
      this.airWindMode = var1;
   }

   public final void setMContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mContext = var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      int var2 = this.getCurrentCarId();
      this.removeSettingRightItemByNameList(var1, new String[]{"S18_park_3", "S18_park_4"});
      Integer var4 = 15;
      Iterable var5 = (Iterable)(new IntRange(1, 15));
      Collection var7 = (Collection)(new ArrayList());
      Iterator var8 = var5.iterator();

      int var3;
      Object var6;
      while(var8.hasNext()) {
         var6 = var8.next();
         var3 = ((Number)var6).intValue();
         if (SetsKt.setOf(new Integer[]{3, 10}).contains(var3)) {
            var7.add(var6);
         }
      }

      if (((List)var7).contains(var2)) {
         this.removeMainPrjBtnByName(var1, "air");
      }

      Collection var16 = (Collection)(new ArrayList());
      Iterator var10 = var5.iterator();

      Object var13;
      while(var10.hasNext()) {
         var13 = var10.next();
         var3 = ((Number)var13).intValue();
         if (SetsKt.setOf(new Integer[]{6, 13, 14, var4}).contains(var3) ^ true) {
            var16.add(var13);
         }
      }

      if (((List)var16).contains(var2)) {
         FrontArea var11 = this.mAirPageUiSet.getFrontArea();
         var11.setAllBtnCanClick(false);
         var11.setCanSetLeftTemp(false);
         var11.setCanSetRightTemp(false);
         var11.setCanSetWindSpeed(false);
         var11.setCanSetSeatHeat(false);
         var11.setShowSeatCold(false);
         RearArea var12 = this.mAirPageUiSet.getRearArea();
         var12.setAllBtnCanClick(false);
         var12.setCanSetTemp(false);
         var12.setCanSetWindSpeed(false);
         var12.setCanSetSeatHeat(false);
         var12.setCanSetSeatCold(false);
      }

      Collection var14 = (Collection)(new ArrayList());
      Iterator var15 = var5.iterator();

      Object var17;
      while(var15.hasNext()) {
         var17 = var15.next();
         var3 = ((Number)var17).intValue();
         if (SetsKt.setOf(3).contains(var3)) {
            var14.add(var17);
         }
      }

      if (((List)var14).contains(var2)) {
         this.mParkPageUiSet.setShowRadar(false);
      }

      var7 = (Collection)(new ArrayList());
      var10 = var5.iterator();

      while(var10.hasNext()) {
         var17 = var10.next();
         var3 = ((Number)var17).intValue();
         if (SetsKt.setOf(14).contains(var3)) {
            var7.add(var17);
         }
      }

      if (((List)var7).contains(var2)) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

      var7 = (Collection)(new ArrayList());
      var8 = var5.iterator();

      while(var8.hasNext()) {
         var6 = var8.next();
         var3 = ((Number)var6).intValue();
         if (SetsKt.setOf(new Integer[]{1, 2, 13, 14}).contains(var3) ^ true) {
            var7.add(var6);
         }
      }

      if (((List)var7).contains(var2)) {
         this.removeMainPrjBtnByName(var1, "amplifier");
      }

      var16 = (Collection)(new ArrayList());
      var15 = var5.iterator();

      while(var15.hasNext()) {
         var6 = var15.next();
         var3 = ((Number)var6).intValue();
         if (SetsKt.setOf(new Integer[]{2, 4, 6, 8, 10, 11, 13, var4}).contains(var3) ^ true) {
            var16.add(var6);
         }
      }

      if (((List)var16).contains(var2)) {
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

      var14 = (Collection)(new ArrayList());
      Iterator var9 = var5.iterator();

      while(var9.hasNext()) {
         var13 = var9.next();
         var3 = ((Number)var13).intValue();
         if (SetsKt.setOf(new Integer[]{4, 14, 5, 6, 7, 11, var4}).contains(var3) ^ true) {
            var14.add(var13);
         }
      }

      if (((List)var14).contains(var2)) {
         this.removeMainPrjBtnByName(var1, "hybird");
      }

   }
}
