package com.hzbhd.canbus.car._314;

import android.content.Context;
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
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

@Metadata(
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000b*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u000b*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00060\u0012R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"},
   d2 = {"Lcom/hzbhd/canbus/car/_314/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mAmplifierHandleListener", "Lcom/hzbhd/canbus/car/_314/UiMgr$AmplifierHandleListener;", "mAmplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "kotlin.jvm.PlatformType", "mContext", "mOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_314/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "selectAirConditionerBtn", "", "btn", "", "sendAirConditionerCmd", "d0", "", "AmplifierHandleListener", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;
   private final AmplifierHandleListener mAmplifierHandleListener;
   private final AmplifierPageUiSet mAmplifierPageUiSet;
   private final Context mContext;
   private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private final ParkPageUiSet mParkPageUiSet;
   private final SettingHandleListener mSettingHandleListener;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$__5qrSxlQI9JO9GvP6iCk6e5PTs(UiMgr var0, RearArea var1, int var2) {
      lambda_12$lambda_11$lambda_7(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$0A4d10Jtxhd3PlPwJOgfhdbScVw(int var0) {
      lambda_1$lambda_0(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$8O1iFJw2VW49pebGcARR_3B7vuI(UiMgr var0, RearArea var1, int var2) {
      lambda_12$lambda_11$lambda_9(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$BZx_10rXvXEB6cssvzDvcvLCeDs(UiMgr var0, FrontArea var1, int var2) {
      lambda_12$lambda_6$lambda_5(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Jcx4NuGtoSh_KNTquvCtnLYFkMc(UiMgr var0, FrontArea var1, int var2) {
      lambda_12$lambda_6$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$SDPK6bWw6DJzuRoyjH1ZZYU0XOY(UiMgr var0, RearArea var1, int var2) {
      lambda_12$lambda_11$lambda_10(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$UJ_v2VvR2EX33gPk_Z6RWPzhG5Y(UiMgr var0, FrontArea var1, int var2) {
      lambda_12$lambda_6$lambda_4(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ZigVpgHZoJBHs_zLXDi8Jp4LeuE(UiMgr var0, FrontArea var1, int var2) {
      lambda_12$lambda_6$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$rRXpYdJm95ROhNF5azlPoTaDk_E(int var0) {
      lambda_16$lambda_15(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$wHzH0M_78m20m8ciqabH6yZ0Zs0(UiMgr var0, RearArea var1, int var2) {
      lambda_12$lambda_11$lambda_8(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var6 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var6, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var6;
      AirPageUiSet var7 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var7, "getAirUiSet(context)");
      this.mAirPageUiSet = var7;
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      this.mAmplifierPageUiSet = var2;
      SettingHandleListener var4 = new SettingHandleListener(this, var6);
      this.mSettingHandleListener = var4;
      AmplifierHandleListener var3 = new AmplifierHandleListener();
      this.mAmplifierHandleListener = var3;
      ParkPageUiSet var8 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var8;
      OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(var1);
      this.mOriginalCarDevicePageUiSet = var5;
      this.mContext = var1;
      var8.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda0());
      FrontArea var11 = var7.getFrontArea();
      var11.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda1(this, var11), new UiMgr$$ExternalSyntheticLambda2(this, var11), new UiMgr$$ExternalSyntheticLambda3(this, var11), new UiMgr$$ExternalSyntheticLambda4(this, var11)});
      var11.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
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
      var11.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
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
      var11.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
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
      var11.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirConditionerCmd(21);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirConditionerCmd(36);
         }
      }));
      RearArea var10 = var7.getRearArea();
      var10.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda5(this, var10), new UiMgr$$ExternalSyntheticLambda6(this, var10), new UiMgr$$ExternalSyntheticLambda7(this, var10), new UiMgr$$ExternalSyntheticLambda8(this, var10)});
      var10.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
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
      var10.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{null, new OnAirTemperatureUpDownClickListener(this) {
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
      var10.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
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
      var6.setOnSettingItemSelectListener((OnSettingItemSelectListener)var4);
      var6.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var4);
      var6.setOnSettingItemSeekbarSelectListener((OnSettingItemSeekbarSelectListener)var4);
      var6.setOnSettingItemClickListener((OnSettingItemClickListener)var4);
      var2.setOnAmplifierPositionListener((OnAmplifierPositionListener)var3);
      var2.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)var3);
      var5.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda9());
      PanelKeyPageUiSet var9 = this.getPanelKeyPageUiSet(var1);
      var9.setOnPanelKeyPositionListener((OnPanelKeyPositionListener)(new OnPanelKeyPositionListener(var9) {
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
                  case -592252941:
                     if (var3.equals("S314_x2A_10")) {
                        this.send(23);
                     }
                     break;
                  case -592252940:
                     if (var3.equals("S314_x2A_11")) {
                        this.send(24);
                     }
                     break;
                  case -592252939:
                     if (var3.equals("S314_x2A_12")) {
                        this.send(25);
                     }
                     break;
                  case -592252938:
                     if (var3.equals("S314_x2A_13")) {
                        this.send(26);
                     }
                     break;
                  case -592252937:
                     if (var3.equals("S314_x2A_14")) {
                        this.send(27);
                     }
                     break;
                  case -592252936:
                     if (var3.equals("S314_x2A_15")) {
                        this.send(28);
                     }
                     break;
                  case -592252935:
                     if (var3.equals("S314_x2A_16")) {
                        this.send(29);
                     }
                     break;
                  case -592252934:
                     if (var3.equals("S314_x2A_17")) {
                        this.send(30);
                     }
                     break;
                  case -592252933:
                     if (var3.equals("S314_x2A_18")) {
                        this.send(36);
                     }
                     break;
                  case -592252932:
                     if (var3.equals("S314_x2A_19")) {
                        this.send(49);
                     }
                     break;
                  default:
                     switch (var1) {
                        case -592252910:
                           if (var3.equals("S314_x2A_20")) {
                              this.send(51);
                           }
                           break;
                        case -592252909:
                           if (var3.equals("S314_x2A_21")) {
                              this.send(69);
                           }
                           break;
                        case -592252908:
                           if (var3.equals("S314_x2A_22")) {
                              this.send(70);
                           }
                           break;
                        case -592252907:
                           if (var3.equals("S314_x2A_23")) {
                              this.send(77);
                           }
                           break;
                        case -592252906:
                           if (var3.equals("S314_x2A_24")) {
                              this.send(78);
                           }
                           break;
                        case -592252905:
                           if (var3.equals("S314_x2A_25")) {
                              this.send(86);
                           }
                           break;
                        case -592252904:
                           if (var3.equals("S314_x2A_26")) {
                              this.send(87);
                           }
                           break;
                        case -592252903:
                           if (var3.equals("S314_x2A_27")) {
                              this.send(88);
                           }
                           break;
                        default:
                           switch (var1) {
                              case 119442397:
                                 if (var3.equals("S314_x2A_1")) {
                                    this.send(1);
                                 }
                                 break;
                              case 119442398:
                                 if (var3.equals("S314_x2A_2")) {
                                    this.send(6);
                                 }
                                 break;
                              case 119442399:
                                 if (var3.equals("S314_x2A_3")) {
                                    this.send(16);
                                 }
                                 break;
                              case 119442400:
                                 if (var3.equals("S314_x2A_4")) {
                                    this.send(17);
                                 }
                                 break;
                              case 119442401:
                                 if (var3.equals("S314_x2A_5")) {
                                    this.send(18);
                                 }
                                 break;
                              case 119442402:
                                 if (var3.equals("S314_x2A_6")) {
                                    this.send(19);
                                 }
                                 break;
                              case 119442403:
                                 if (var3.equals("S314_x2A_7")) {
                                    this.send(20);
                                 }
                                 break;
                              case 119442404:
                                 if (var3.equals("S314_x2A_8")) {
                                    this.send(21);
                                 }
                                 break;
                              case 119442405:
                                 if (var3.equals("S314_x2A_9")) {
                                    this.send(22);
                                 }
                           }
                     }
               }
            }

         }

         public final void send(int var1) {
            BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(var1, (Continuation)null) {
               final int $d0;
               int label;

               {
                  this.$d0 = var1;
               }

               public final Continuation create(Object var1, Continuation var2) {
                  return (Continuation)(new <anonymous constructor>(this.$d0, var2));
               }

               public final Object invoke(CoroutineScope var1, Continuation var2) {
                  return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     ResultKt.throwOnFailure(var1);
                  } else {
                     ResultKt.throwOnFailure(var1);
                     CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte)this.$d0, 1});
                     Continuation var4 = (Continuation)this;
                     this.label = 1;
                     if (DelayKt.delay(500L, var4) == var3) {
                        return var3;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte)this.$d0, 0});
                  return Unit.INSTANCE;
               }
            }), 3, (Object)null);
         }
      }));
   }

   private static final void lambda_1$lambda_0(int var0) {
      switch (var0) {
         case 0:
            lambda_1$sendPanoramaCmd(1);
            break;
         case 1:
            lambda_1$sendPanoramaCmd(17);
            break;
         case 2:
            lambda_1$sendPanoramaCmd(20);
            break;
         case 3:
            lambda_1$sendPanoramaCmd(21);
            break;
         case 4:
            lambda_1$sendPanoramaCmd(22);
            break;
         case 5:
            lambda_1$sendPanoramaCmd(24);
            break;
         case 6:
            lambda_1$sendPanoramaCmd(25);
            break;
         case 7:
            lambda_1$sendPanoramaCmd(26);
            break;
         case 8:
            lambda_1$sendPanoramaCmd(27);
            break;
         case 9:
            lambda_1$sendPanoramaCmd(28);
            break;
         case 10:
            lambda_1$sendPanoramaCmd(29);
            break;
         case 11:
            lambda_1$sendPanoramaCmd(30);
            break;
         case 12:
            lambda_1$sendPanoramaCmd(31);
            break;
         case 13:
            lambda_1$sendPanoramaCmd(32);
            break;
         case 14:
            lambda_1$sendPanoramaCmd(33);
            break;
         case 15:
            lambda_1$sendPanoramaCmd(34);
            break;
         case 16:
            lambda_1$sendPanoramaCmd(35);
            break;
         case 17:
            lambda_1$sendPanoramaCmd(36);
            break;
         case 18:
            lambda_1$sendPanoramaCmd(37);
      }

   }

   private static final void lambda_1$sendPanoramaCmd(int var0) {
      byte var1 = (byte)var0;
      CanbusMsgSender.sendMsg(new byte[]{22, -3, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -3, var1, 0});
   }

   private static final void lambda_12$lambda_11$lambda_10(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_12$lambda_11$lambda_7(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_12$lambda_11$lambda_8(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_12$lambda_11$lambda_9(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_12$lambda_6$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_12$lambda_6$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_12$lambda_6$lambda_4(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_12$lambda_6$lambda_5(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_16$lambda_15(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 == 3) {
                  lambda_16$send$default(5, 0, 2, (Object)null);
               }
            } else {
               lambda_16$send(1, 1);
            }
         } else {
            lambda_16$send(1, 0);
         }
      } else {
         lambda_16$send$default(4, 0, 2, (Object)null);
      }

   }

   private static final void lambda_16$send(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -84, (byte)var0, (byte)var1});
   }

   // $FF: synthetic method
   static void lambda_16$send$default(int var0, int var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = 0;
      }

      lambda_16$send(var0, var1);
   }

   private final void selectAirConditionerBtn(String var1) {
      switch (var1) {
         case "front_defog":
            this.sendAirConditionerCmd(5);
            break;
         case "rear_auto":
            this.sendAirConditionerCmd(58);
            break;
         case "rear_lock":
            this.sendAirConditionerCmd(34);
            break;
         case "rear_defog":
            this.sendAirConditionerCmd(6);
            break;
         case "rear_power":
            this.sendAirConditionerCmd(46);
            break;
         case "ac":
            this.sendAirConditionerCmd(2);
            break;
         case "auto":
            this.sendAirConditionerCmd(4);
            break;
         case "dual":
            this.sendAirConditionerCmd(41);
            break;
         case "in_out_auto_cycle":
            this.sendAirConditionerCmd(7);
            break;
         case "power":
            this.sendAirConditionerCmd(1);
      }

   }

   private final void sendAirConditionerCmd(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0002¨\u0006\u0010"},
      d2 = {"Lcom/hzbhd/canbus/car/_314/UiMgr$AmplifierHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnAmplifierPositionListener;", "Lcom/hzbhd/canbus/interfaces/OnAmplifierSeekBarListener;", "()V", "position", "", "amplifierPosition", "Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierPosition;", "value", "", "progress", "amplifierBand", "Lcom/hzbhd/canbus/activity/AmplifierActivity$AmplifierBand;", "sendAmplifierCmd", "cmd", "param", "CanBusInfo_release"},
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
         var2 = UiMgr.AmplifierHandleListener.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     return;
                  }

                  this.sendAmplifierCmd(3, 1);
               } else {
                  this.sendAmplifierCmd(3, 255);
               }
            } else {
               this.sendAmplifierCmd(2, 1);
            }
         } else {
            this.sendAmplifierCmd(2, 255);
         }

      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "amplifierBand");
         switch (UiMgr.AmplifierHandleListener.WhenMappings.$EnumSwitchMapping$1[var1.ordinal()]) {
            case 1:
               this.sendAmplifierCmd(1, 1);
               break;
            case 2:
               this.sendAmplifierCmd(1, 255);
               break;
            case 3:
               this.sendAmplifierCmd(4, 1);
               break;
            case 4:
               this.sendAmplifierCmd(4, 255);
               break;
            case 5:
               this.sendAmplifierCmd(5, 1);
               break;
            case 6:
               this.sendAmplifierCmd(5, 255);
               break;
            case 7:
               this.sendAmplifierCmd(6, 1);
               break;
            case 8:
               this.sendAmplifierCmd(6, 255);
               break;
            default:
               return;
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
            var0[AmplifierActivity.AmplifierPosition.LEFT.ordinal()] = 1;
            var0[AmplifierActivity.AmplifierPosition.RIGHT.ordinal()] = 2;
            var0[AmplifierActivity.AmplifierPosition.REAR.ordinal()] = 3;
            var0[AmplifierActivity.AmplifierPosition.FRONT.ordinal()] = 4;
            $EnumSwitchMapping$0 = var0;
            var0 = new int[AmplifierActivity.AmplifierBand.values().length];
            var0[AmplifierActivity.AmplifierBand.VOLUME_Plus.ordinal()] = 1;
            var0[AmplifierActivity.AmplifierBand.VOLUME_Min.ordinal()] = 2;
            var0[AmplifierActivity.AmplifierBand.BASS_Plus.ordinal()] = 3;
            var0[AmplifierActivity.AmplifierBand.BASS_Min.ordinal()] = 4;
            var0[AmplifierActivity.AmplifierBand.MIDDLE_Plus.ordinal()] = 5;
            var0[AmplifierActivity.AmplifierBand.MIDDLE_Min.ordinal()] = 6;
            var0[AmplifierActivity.AmplifierBand.TREBLE_Plus.ordinal()] = 7;
            var0[AmplifierActivity.AmplifierBand.TREBLE_Min.ordinal()] = 8;
            $EnumSwitchMapping$1 = var0;
         }
      }
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J \u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0016J)\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\u0018\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001a"},
      d2 = {"Lcom/hzbhd/canbus/car/_314/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemClickListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/car/_314/UiMgr;Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "(IILjava/lang/Integer;)V", "sendAirSettingsData", "d0", "d1", "sendSettingsCmd", "cmd", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener, OnSettingItemClickListener {
      private final SettingPageUiSet mSettingPageUiSet;
      final UiMgr this$0;

      public SettingHandleListener(UiMgr var1, SettingPageUiSet var2) {
         Intrinsics.checkNotNullParameter(var2, "mSettingPageUiSet");
         this.this$0 = var1;
         super();
         this.mSettingPageUiSet = var2;
      }

      private final void selectSettingsBtn(int var1, int var2, Integer var3) {
         if (var3 != null) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            if (var4 != null) {
               var2 = var4.hashCode();
               switch (var2) {
                  case -1726079825:
                     if (var4.equals("S314_setting1_1")) {
                        this.sendSettingsCmd(4, var3);
                     }
                     break;
                  case -1726079824:
                     if (var4.equals("S314_setting1_2")) {
                        this.sendSettingsCmd(5, var3);
                     }
                     break;
                  case -1726079823:
                     if (var4.equals("S314_setting1_3")) {
                        this.sendSettingsCmd(6, var3);
                     }
                     break;
                  case -1726079822:
                     if (var4.equals("S314_setting1_4")) {
                        this.sendSettingsCmd(7, var3);
                     }
                     break;
                  default:
                     switch (var2) {
                        case -1726078864:
                           if (var4.equals("S314_setting2_1")) {
                              this.sendSettingsCmd(8, var3);
                           }
                           break;
                        case -1726078863:
                           if (var4.equals("S314_setting2_2")) {
                              this.sendSettingsCmd(10, var3);
                           }
                           break;
                        case -1726078862:
                           if (var4.equals("S314_setting2_3")) {
                              this.sendSettingsCmd(11, var3);
                           }
                           break;
                        case -1726078861:
                           if (var4.equals("S314_setting2_4")) {
                              this.sendSettingsCmd(13, var3);
                           }
                           break;
                        case -1726078860:
                           if (var4.equals("S314_setting2_5")) {
                              this.sendSettingsCmd(14, var3);
                           }
                           break;
                        case -1726078859:
                           if (var4.equals("S314_setting2_6")) {
                              this.sendSettingsCmd(15, var3);
                           }
                           break;
                        case -1726078858:
                           if (var4.equals("S314_setting2_7")) {
                              this.sendSettingsCmd(16, var3);
                           }
                           break;
                        default:
                           switch (var2) {
                              case -1726077902:
                                 if (var4.equals("S314_setting3_2")) {
                                    this.sendSettingsCmd(22, var3);
                                 }
                                 break;
                              case -1726077901:
                                 if (var4.equals("S314_setting3_3")) {
                                    this.sendSettingsCmd(23, var3);
                                 }
                                 break;
                              case -1726077900:
                                 if (var4.equals("S314_setting3_4")) {
                                    this.sendSettingsCmd(24, var3);
                                 }
                                 break;
                              case -1726077899:
                                 if (var4.equals("S314_setting3_5")) {
                                    this.sendSettingsCmd(25, var3);
                                 }
                                 break;
                              case -1726077898:
                                 if (var4.equals("S314_setting3_6")) {
                                    this.sendSettingsCmd(26, var3);
                                 }
                                 break;
                              default:
                                 switch (var2) {
                                    case -1726076942:
                                       if (var4.equals("S314_setting4_1")) {
                                          this.sendSettingsCmd(27, var3);
                                       }
                                       break;
                                    case -1726076941:
                                       if (var4.equals("S314_setting4_2")) {
                                          this.sendSettingsCmd(28, var3);
                                       }
                                       break;
                                    case -1726076940:
                                       if (var4.equals("S314_setting4_3")) {
                                          this.sendSettingsCmd(29, var3);
                                       }
                                       break;
                                    case -1726076939:
                                       if (var4.equals("S314_setting4_4")) {
                                          this.sendSettingsCmd(30, var3);
                                       }
                                       break;
                                    case -1726076938:
                                       if (var4.equals("S314_setting4_5")) {
                                          this.sendSettingsCmd(31, var3);
                                       }
                                       break;
                                    case -1726076937:
                                       if (var4.equals("S314_setting4_6")) {
                                          this.sendSettingsCmd(33, var3);
                                       }
                                       break;
                                    default:
                                       switch (var2) {
                                          case -1726075981:
                                             if (var4.equals("S314_setting5_1")) {
                                                this.sendSettingsCmd(34, var3);
                                             }
                                             break;
                                          case -1726075980:
                                             if (var4.equals("S314_setting5_2")) {
                                                this.sendSettingsCmd(35, var3);
                                             }
                                             break;
                                          case -1726075979:
                                             if (var4.equals("S314_setting5_3")) {
                                                this.sendSettingsCmd(36, var3);
                                             }
                                             break;
                                          case -1726075978:
                                             if (var4.equals("S314_setting5_4")) {
                                                this.sendSettingsCmd(37, var3);
                                             }
                                             break;
                                          default:
                                             byte var5 = 17;
                                             switch (var2) {
                                                case -984333157:
                                                   if (var4.equals("D314_Other_10")) {
                                                      selectSettingsBtn$sendOtherData(11, var3);
                                                   }
                                                   break;
                                                case -984333156:
                                                   if (var4.equals("D314_Other_11")) {
                                                      selectSettingsBtn$sendOtherData(12, var3);
                                                   }
                                                   break;
                                                case -984333155:
                                                   if (var4.equals("D314_Other_12")) {
                                                      selectSettingsBtn$sendOtherData(13, var3);
                                                   }
                                                   break;
                                                case -984333154:
                                                   if (var4.equals("D314_Other_13")) {
                                                      selectSettingsBtn$sendOtherData(14, var3);
                                                   }
                                                   break;
                                                case -984333153:
                                                   if (var4.equals("D314_Other_14")) {
                                                      selectSettingsBtn$sendOtherData(15, var3);
                                                   }
                                                   break;
                                                case -984333152:
                                                   if (var4.equals("D314_Other_15")) {
                                                      selectSettingsBtn$sendOtherData(16, var3);
                                                   }
                                                   break;
                                                case -984333151:
                                                   if (var4.equals("D314_Other_16")) {
                                                      selectSettingsBtn$sendOtherData(17, var3);
                                                   }
                                                   break;
                                                case -984333150:
                                                   if (var4.equals("D314_Other_17")) {
                                                      selectSettingsBtn$sendOtherData(18, var3);
                                                   }
                                                   break;
                                                default:
                                                   switch (var2) {
                                                      case -832009892:
                                                         if (var4.equals("S314_radar_1")) {
                                                            this.sendSettingsCmd(1, var3);
                                                         }
                                                         break;
                                                      case -832009891:
                                                         if (var4.equals("S314_radar_2")) {
                                                            this.sendSettingsCmd(2, var3);
                                                         }
                                                         break;
                                                      case -832009890:
                                                         if (var4.equals("S314_radar_3")) {
                                                            this.sendSettingsCmd(3, var3);
                                                         }
                                                         break;
                                                      case -832009889:
                                                         if (var4.equals("S314_radar_4")) {
                                                            this.sendSettingsCmd(32, var3);
                                                         }
                                                         break;
                                                      default:
                                                         switch (var2) {
                                                            case -480066374:
                                                               if (var4.equals("S314_DSPSettings_1")) {
                                                                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 10, (byte)var3});
                                                               }
                                                               break;
                                                            case -480066373:
                                                               if (var4.equals("S314_DSPSettings_2")) {
                                                                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 9, (byte)var3});
                                                               }
                                                               break;
                                                            case -480066372:
                                                               if (var4.equals("S314_DSPSettings_3")) {
                                                                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte)var3});
                                                               }
                                                               break;
                                                            default:
                                                               switch (var2) {
                                                                  case 94883461:
                                                                     if (var4.equals("S314_Language_0")) {
                                                                        var2 = var3;
                                                                        if (var2 != 1) {
                                                                           if (var2 != 2) {
                                                                              if (var2 != 3) {
                                                                                 if (var2 != 4) {
                                                                                    if (var2 != 5) {
                                                                                       var5 = 0;
                                                                                    } else {
                                                                                       var5 = 18;
                                                                                    }
                                                                                 }
                                                                              } else {
                                                                                 var5 = 3;
                                                                              }
                                                                           } else {
                                                                              var5 = 2;
                                                                           }
                                                                        } else {
                                                                           var5 = 1;
                                                                        }

                                                                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)var5});
                                                                        SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_Language_0");
                                                                        if (var7 != null) {
                                                                           var7.setValue(var7.getValueSrnArray().get(var3));
                                                                        }

                                                                        MsgMgrInterface var6 = MsgMgrFactory.getCanMsgMgr(this.this$0.mContext);
                                                                        Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type com.hzbhd.canbus.car._314.MsgMgr");
                                                                        ((MsgMgr)var6).updateSettingsData();
                                                                     }
                                                                     break;
                                                                  case 1076625973:
                                                                     if (var4.equals("D314_Other_1")) {
                                                                        selectSettingsBtn$sendOtherData(2, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625974:
                                                                     if (var4.equals("D314_Other_2")) {
                                                                        selectSettingsBtn$sendOtherData(5, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625975:
                                                                     if (var4.equals("D314_Other_3")) {
                                                                        selectSettingsBtn$sendOtherData(6, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625976:
                                                                     if (var4.equals("D314_Other_4")) {
                                                                        selectSettingsBtn$sendOtherData(7, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625977:
                                                                     if (var4.equals("D314_Other_5")) {
                                                                        selectSettingsBtn$sendOtherData(3, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625978:
                                                                     if (var4.equals("D314_Other_6")) {
                                                                        selectSettingsBtn$sendOtherData(4, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625979:
                                                                     if (var4.equals("D314_Other_7")) {
                                                                        selectSettingsBtn$sendOtherData(8, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625980:
                                                                     if (var4.equals("D314_Other_8")) {
                                                                        selectSettingsBtn$sendOtherData(9, var3);
                                                                     }
                                                                     break;
                                                                  case 1076625981:
                                                                     if (var4.equals("D314_Other_9")) {
                                                                        selectSettingsBtn$sendOtherData(10, var3);
                                                                     }
                                                                     break;
                                                                  case 1729297831:
                                                                     if (var4.equals("S314_SettingForAir_1")) {
                                                                        this.sendAirSettingsData(3, var3);
                                                                     }
                                                                     break;
                                                                  case 1729297832:
                                                                     if (var4.equals("S314_SettingForAir_2")) {
                                                                        this.sendAirSettingsData(11, var3);
                                                                     }
                                                                     break;
                                                                  case 1729297833:
                                                                     if (var4.equals("S314_SettingForAir_3")) {
                                                                        this.sendAirSettingsData(21, var3);
                                                                     }
                                                                     break;
                                                                  case 1729297834:
                                                                     if (var4.equals("S314_SettingForAir_4")) {
                                                                        this.sendAirSettingsData(22, var3);
                                                                     }
                                                                     break;
                                                                  case 1729297835:
                                                                     if (var4.equals("S314_SettingForAir_5")) {
                                                                        this.sendAirSettingsData(23, var3);
                                                                     }
                                                                     break;
                                                                  default:
                                                                     switch (var2) {
                                                                        case 746231576:
                                                                           if (var4.equals("S314_display_1")) {
                                                                              this.sendSettingsCmd(17, var3);
                                                                           }
                                                                           break;
                                                                        case 746231577:
                                                                           if (var4.equals("S314_display_2")) {
                                                                              this.sendSettingsCmd(18, var3);
                                                                           }
                                                                           break;
                                                                        case 746231578:
                                                                           if (var4.equals("S314_display_3")) {
                                                                              this.sendSettingsCmd(19, var3);
                                                                           }
                                                                           break;
                                                                        case 746231579:
                                                                           if (var4.equals("S314_display_4")) {
                                                                              this.sendSettingsCmd(20, var3);
                                                                           }
                                                                     }
                                                               }
                                                         }
                                                   }
                                             }
                                       }
                                 }
                           }
                     }
               }
            }
         } else if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"D314_Other_18")) {
            selectSettingsBtn$sendOtherData(1, 0);
            Toast.makeText(this.this$0.mContext, (CharSequence)"Success!", 0).show();
         }

      }

      // $FF: synthetic method
      static void selectSettingsBtn$default(SettingHandleListener var0, int var1, int var2, Integer var3, int var4, Object var5) {
         if ((var4 & 4) != 0) {
            var3 = null;
         }

         var0.selectSettingsBtn(var1, var2, var3);
      }

      private static final void selectSettingsBtn$sendOtherData(int var0, int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte)var0, (byte)var1, -1, -1});
      }

      private final void sendAirSettingsData(int var1, int var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, (byte)var2});
      }

      private final void sendSettingsCmd(int var1, int var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, -1, -1});
      }

      public final SettingPageUiSet getMSettingPageUiSet() {
         return this.mSettingPageUiSet;
      }

      public void onClickItem(int var1, int var2) {
         selectSettingsBtn$default(this, var1, var2, (Integer)null, 4, (Object)null);
      }

      public void onClickItem(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }

      public void onSwitch(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }
   }
}
