package com.hzbhd.canbus.car._340;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
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
   d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010A\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0002J\u0018\u0010F\u001a\u00020C2\u0006\u0010G\u001a\u00020E2\u0006\u0010H\u001a\u00020IH\u0002J\u0018\u0010J\u001a\u00020C2\u0006\u0010G\u001a\u00020E2\u0006\u0010H\u001a\u00020IH\u0002J\u0018\u0010K\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010L\u001a\u00020EH\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@¨\u0006M"},
   d2 = {"Lcom/hzbhd/canbus/car/_340/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "setAirPageUiSet", "(Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "mMsgMgr", "Lcom/hzbhd/canbus/car/_340/MsgMgr;", "getMMsgMgr", "()Lcom/hzbhd/canbus/car/_340/MsgMgr;", "setMMsgMgr", "(Lcom/hzbhd/canbus/car/_340/MsgMgr;)V", "onAirBtnClickListenerFrontBottom", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "onAirBtnClickListenerFrontLeft", "onAirBtnClickListenerFrontRight", "onAirBtnClickListenerFrontTop", "onAirTemperatureUpDownClickListenerFrontLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "onAirTemperatureUpDownClickListenerFrontRight", "onAirWindSpeedUpDownClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "getOnAirWindSpeedUpDownClickListener", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "setOnAirWindSpeedUpDownClickListener", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;)V", "onSettingItemClickListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemClickListener;", "getOnSettingItemClickListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemClickListener;", "setOnSettingItemClickListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemClickListener;)V", "onSettingItemSeekbarSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "getOnSettingItemSeekbarSelectListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "setOnSettingItemSeekbarSelectListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;)V", "onSettingItemSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "getOnSettingItemSelectListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "setOnSettingItemSelectListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;)V", "onSettingItemSwitchListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "getOnSettingItemSwitchListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "setOnSettingItemSwitchListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;)V", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "setSettingPageUiSet", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMsgMgr", "sendAirData", "", "data0", "", "sendAirLeftTemData", "cmd", "isUpAction", "", "sendAirRightTemData", "sendSettingData", "data1", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private MsgMgr mMsgMgr;
   private OnAirBtnClickListener onAirBtnClickListenerFrontBottom;
   private OnAirBtnClickListener onAirBtnClickListenerFrontLeft;
   private OnAirBtnClickListener onAirBtnClickListenerFrontRight;
   private OnAirBtnClickListener onAirBtnClickListenerFrontTop;
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerFrontLeft;
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerFrontRight;
   private OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener;
   private OnSettingItemClickListener onSettingItemClickListener;
   private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
   private OnSettingItemSelectListener onSettingItemSelectListener;
   private OnSettingItemSwitchListener onSettingItemSwitchListener;
   private SettingPageUiSet settingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$_NKDUfWJw5C9KirGWY9DCTQV0vQ(UiMgr var0, int var1) {
      onAirBtnClickListenerFrontLeft$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$388_mzyCeuRW21UvuhKt_qcEEUk(UiMgr var0, int var1, int var2, int var3) {
      onSettingItemSwitchListener$lambda_7(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$INbTA_F0fCzO_gHQnVwjqkItcdI(UiMgr var0, int var1, int var2, int var3) {
      onSettingItemSeekbarSelectListener$lambda_4(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$KZiEijKZVD_64yx8KdpxJNhPPHk(UiMgr var0, int var1, int var2, int var3) {
      onSettingItemSelectListener$lambda_6(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$_s_HgEpcBx4xJ9tD0y1V43vp9V0(UiMgr var0, int var1) {
      onAirBtnClickListenerFrontBottom$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$pin39ViVbPdBbHMvxhpk84Y9bAs(UiMgr var0, int var1) {
      onAirBtnClickListenerFrontTop$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$uMbyPeN5gSk_09G59bv4bTzwSFc(UiMgr var0, int var1) {
      onAirBtnClickListenerFrontRight$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$v_JD1u_newXtQe5mUwewlein68c(UiMgr var0, int var1, int var2) {
      onSettingItemClickListener$lambda_5(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.settingPageUiSet = var2;
      this.onAirBtnClickListenerFrontTop = new UiMgr$$ExternalSyntheticLambda0(this);
      this.onAirBtnClickListenerFrontLeft = new UiMgr$$ExternalSyntheticLambda1(this);
      this.onAirBtnClickListenerFrontRight = new UiMgr$$ExternalSyntheticLambda2(this);
      this.onAirBtnClickListenerFrontBottom = new UiMgr$$ExternalSyntheticLambda3(this);
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
      this.onAirTemperatureUpDownClickListenerFrontLeft = (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirLeftTemData(19, false);
         }

         public void onClickUp() {
            this.this$0.sendAirLeftTemData(19, true);
         }
      });
      this.onAirTemperatureUpDownClickListenerFrontRight = (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirRightTemData(20, false);
         }

         public void onClickUp() {
            this.this$0.sendAirRightTemData(20, true);
         }
      });
      this.onSettingItemSeekbarSelectListener = new UiMgr$$ExternalSyntheticLambda4(this);
      this.onSettingItemClickListener = new UiMgr$$ExternalSyntheticLambda5(this);
      this.onSettingItemSelectListener = new UiMgr$$ExternalSyntheticLambda6(this);
      this.onSettingItemSwitchListener = new UiMgr$$ExternalSyntheticLambda7(this);
      this.settingPageUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      this.settingPageUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
      this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      this.settingPageUiSet.setOnSettingItemSwitchListener(this.onSettingItemSwitchListener);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getAirUiSet(context)");
      this.airPageUiSet = var3;
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerFrontTop, this.onAirBtnClickListenerFrontLeft, this.onAirBtnClickListenerFrontRight, this.onAirBtnClickListenerFrontBottom});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerFrontLeft, null, this.onAirTemperatureUpDownClickListenerFrontRight});
      this.airPageUiSet.getFrontArea().setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener() {
         private int i;
         private final byte[] mode = new byte[]{1, 2, 3, 4};

         public final int getI() {
            return this.i;
         }

         public final byte[] getMode() {
            return this.mode;
         }

         public void onLeftSeatClick() {
            byte[] var2 = this.mode;
            int var1 = this.i++;
            this.sendAirConditionerModeData(var2[var1 % 4]);
         }

         public void onRightSeatClick() {
            byte[] var2 = this.mode;
            int var1 = this.i++;
            this.sendAirConditionerModeData(var2[var1 % 4]);
         }

         public final void sendAirConditionerModeData(byte var1) {
            BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(var1, (Continuation)null) {
               final byte $d1;
               int label;

               {
                  this.$d1 = var1;
               }

               public final Continuation create(Object var1, Continuation var2) {
                  return (Continuation)(new <anonymous constructor>(this.$d1, var2));
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
                     CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 9, this.$d1});
                     Continuation var4 = (Continuation)this;
                     this.label = 1;
                     if (DelayKt.delay(200L, var4) == var3) {
                        return var3;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 9, 0});
                  return Unit.INSTANCE;
               }
            }), 3, (Object)null);
         }

         public final void setI(int var1) {
            this.i = var1;
         }
      }));
      if (this.getCurrentCarId() != 8 && this.getCurrentCarId() != 9) {
         this.removeMainPrjBtnByName(var1, "air");
         this.removeMainPrjBtnByName(var1, "setting");
      }

   }

   private final MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._340.MsgMgr");
         this.mMsgMgr = (MsgMgr)var2;
      }

      return this.mMsgMgr;
   }

   private static final void onAirBtnClickListenerFrontBottom$lambda_3(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var0.sendAirData(3);
            }
         } else {
            var0.sendAirData(2);
         }
      } else {
         var0.sendAirData(21);
      }

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
      if (var1 == 0) {
         var0.sendAirData(1);
      }

   }

   private static final void onSettingItemClickListener$lambda_5(UiMgr var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         if (var2 != 0) {
            if (var2 == 2) {
               var0.sendSettingData(2, 1);
            }
         } else {
            var0.sendSettingData(0, 1);
         }
      }

   }

   private static final void onSettingItemSeekbarSelectListener$lambda_4(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var1 == 0 && Intrinsics.areEqual((Object)var4, (Object)"_340_adaptive_cruise_speed_setting")) {
         var0.sendSettingData(18, var3);
      }

   }

   private static final void onSettingItemSelectListener$lambda_6(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         if (var2 != 13) {
            if (var2 != 20) {
               if (var2 != 15) {
                  if (var2 != 16) {
                     switch (var2) {
                        case 8:
                           var0.sendSettingData(8, var3 + 1);
                           break;
                        case 9:
                           var0.sendSettingData(9, var3 + 1);
                           break;
                        case 10:
                           var0.sendSettingData(10, var3 + 1);
                     }
                  } else {
                     var0.sendSettingData(16, var3 + 1);
                  }
               } else {
                  var0.sendSettingData(15, var3 + 1);
               }
            } else {
               var0.sendSettingData(20, var3 + 1);
            }
         } else {
            var0.sendSettingData(13, var3 + 1);
         }
      }

   }

   private static final void onSettingItemSwitchListener$lambda_7(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         if (var2 != 1) {
            if (var2 != 14) {
               if (var2 != 17) {
                  if (var2 != 19) {
                     if (var2 != 21) {
                        if (var2 != 3) {
                           if (var2 != 4) {
                              if (var2 != 5) {
                                 if (var2 != 6) {
                                    if (var2 != 7) {
                                       if (var2 != 11) {
                                          if (var2 == 12) {
                                             var0.sendSettingData(12, var3 + 1);
                                          }
                                       } else {
                                          var0.sendSettingData(11, var3 + 1);
                                       }
                                    } else {
                                       var0.sendSettingData(7, var3 + 1);
                                    }
                                 } else {
                                    var0.sendSettingData(6, var3 + 1);
                                 }
                              } else {
                                 var0.sendSettingData(5, var3 + 1);
                              }
                           } else {
                              var0.sendSettingData(4, var3 + 1);
                           }
                        } else {
                           var0.sendSettingData(3, var3 + 1);
                        }
                     } else {
                        var0.sendSettingData(21, var3 + 1);
                     }
                  } else {
                     var0.sendSettingData(19, var3 + 1);
                  }
               } else {
                  var0.sendSettingData(17, var3 + 1);
               }
            } else {
               var0.sendSettingData(14, var3 + 1);
            }
         } else {
            var0.sendSettingData(1, var3 + 1);
         }
      }

   }

   private final void sendAirData(int var1) {
      BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(var1, (Continuation)null) {
         final int $data0;
         int label;

         {
            this.$data0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            return (Continuation)(new <anonymous constructor>(this.$data0, var2));
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
               CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)this.$data0, 1});
               Continuation var4 = (Continuation)this;
               this.label = 1;
               if (DelayKt.delay(200L, var4) == var3) {
                  return var3;
               }
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)this.$data0, 0});
            return Unit.INSTANCE;
         }
      }), 3, (Object)null);
   }

   private final void sendAirLeftTemData(int var1, boolean var2) {
      Context var10 = this.mContext;
      byte var4 = 40;
      int var3 = var4;
      if (var10 != null) {
         MsgMgr var5 = this.getMsgMgr(var10);
         boolean var11;
         if (var5 != null && var5.nowLeftValue == 254) {
            var11 = true;
         } else {
            var11 = false;
         }

         MsgMgr var8 = null;
         Integer var12 = null;
         MsgMgr var7 = null;
         StringBuilder var6 = null;
         MsgMgr var9 = null;
         if (var11) {
            var6 = (new StringBuilder()).append("空调温度：最高");
            var7 = this.getMsgMgr(var10);
            var12 = var9;
            if (var7 != null) {
               var12 = var7.nowLeftValue;
            }

            MyLog.temporaryTracking(var6.append(var12).toString());
            if (!var2) {
               return;
            }

            var3 = 37;
         } else {
            var9 = this.getMsgMgr(var10);
            if (var9 != null && var9.nowLeftValue == 255) {
               var11 = true;
            } else {
               var11 = false;
            }

            if (var11) {
               var6 = (new StringBuilder()).append("空调温度：最低");
               var7 = this.getMsgMgr(var10);
               var12 = var8;
               if (var7 != null) {
                  var12 = var7.nowLeftValue;
               }

               MyLog.temporaryTracking(var6.append(var12).toString());
               if (var2) {
                  return;
               }

               var3 = 62;
            } else {
               var8 = this.getMsgMgr(var10);
               if (var8 != null && var8.nowLeftValue == 0) {
                  var11 = true;
               } else {
                  var11 = false;
               }

               if (var11) {
                  var6 = (new StringBuilder()).append("空调温度：异常");
                  var7 = this.getMsgMgr(var10);
                  if (var7 != null) {
                     var12 = var7.nowLeftValue;
                  }

                  MyLog.temporaryTracking(var6.append(var12).toString());
                  var3 = var4;
               } else {
                  StringBuilder var14 = (new StringBuilder()).append("空调温度：常值");
                  var5 = this.getMsgMgr(var10);
                  if (var5 != null) {
                     var12 = var5.nowLeftValue;
                  } else {
                     var12 = null;
                  }

                  MyLog.temporaryTracking(var14.append(var12).append('1').toString());
                  if (var2) {
                     MsgMgr var13 = this.getMsgMgr(var10);
                     var12 = var7;
                     if (var13 != null) {
                        var12 = var13.nowLeftValue;
                     }

                     Intrinsics.checkNotNull(var12);
                     var3 = var12 + 1;
                  } else {
                     var7 = this.getMsgMgr(var10);
                     var12 = var6;
                     if (var7 != null) {
                        var12 = var7.nowLeftValue;
                     }

                     Intrinsics.checkNotNull(var12);
                     var3 = var12 - 1;
                  }
               }
            }
         }
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)var1, (byte)var3});
   }

   private final void sendAirRightTemData(int var1, boolean var2) {
      Context var7 = this.mContext;
      byte var4 = 40;
      int var3 = var4;
      if (var7 != null) {
         MsgMgr var5 = this.getMsgMgr(var7);
         boolean var8;
         if (var5 != null && var5.nowRightValue == 254) {
            var8 = true;
         } else {
            var8 = false;
         }

         if (var8) {
            if (!var2) {
               return;
            }

            var3 = 37;
         } else {
            var5 = this.getMsgMgr(var7);
            if (var5 != null && var5.nowRightValue == 255) {
               var8 = true;
            } else {
               var8 = false;
            }

            if (var8) {
               if (var2) {
                  return;
               }

               var3 = 62;
            } else {
               var5 = this.getMsgMgr(var7);
               if (var5 != null && var5.nowRightValue == 0) {
                  var8 = true;
               } else {
                  var8 = false;
               }

               if (var8) {
                  var3 = var4;
               } else {
                  Integer var9 = null;
                  MsgMgr var6 = null;
                  if (var2) {
                     MsgMgr var10 = this.getMsgMgr(var7);
                     var9 = var6;
                     if (var10 != null) {
                        var9 = var10.nowRightValue;
                     }

                     Intrinsics.checkNotNull(var9);
                     var3 = var9 + 1;
                  } else {
                     var6 = this.getMsgMgr(var7);
                     if (var6 != null) {
                        var9 = var6.nowRightValue;
                     }

                     Intrinsics.checkNotNull(var9);
                     var3 = var9 - 1;
                  }
               }
            }
         }
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)var1, (byte)var3});
   }

   private final void sendSettingData(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   public final AirPageUiSet getAirPageUiSet() {
      return this.airPageUiSet;
   }

   public final Context getMContext() {
      return this.mContext;
   }

   public final MsgMgr getMMsgMgr() {
      return this.mMsgMgr;
   }

   public final OnAirWindSpeedUpDownClickListener getOnAirWindSpeedUpDownClickListener() {
      return this.onAirWindSpeedUpDownClickListener;
   }

   public final OnSettingItemClickListener getOnSettingItemClickListener() {
      return this.onSettingItemClickListener;
   }

   public final OnSettingItemSeekbarSelectListener getOnSettingItemSeekbarSelectListener() {
      return this.onSettingItemSeekbarSelectListener;
   }

   public final OnSettingItemSelectListener getOnSettingItemSelectListener() {
      return this.onSettingItemSelectListener;
   }

   public final OnSettingItemSwitchListener getOnSettingItemSwitchListener() {
      return this.onSettingItemSwitchListener;
   }

   public final SettingPageUiSet getSettingPageUiSet() {
      return this.settingPageUiSet;
   }

   public final void setAirPageUiSet(AirPageUiSet var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.airPageUiSet = var1;
   }

   public final void setMContext(Context var1) {
      this.mContext = var1;
   }

   public final void setMMsgMgr(MsgMgr var1) {
      this.mMsgMgr = var1;
   }

   public final void setOnAirWindSpeedUpDownClickListener(OnAirWindSpeedUpDownClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onAirWindSpeedUpDownClickListener = var1;
   }

   public final void setOnSettingItemClickListener(OnSettingItemClickListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onSettingItemClickListener = var1;
   }

   public final void setOnSettingItemSeekbarSelectListener(OnSettingItemSeekbarSelectListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onSettingItemSeekbarSelectListener = var1;
   }

   public final void setOnSettingItemSelectListener(OnSettingItemSelectListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onSettingItemSelectListener = var1;
   }

   public final void setOnSettingItemSwitchListener(OnSettingItemSwitchListener var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.onSettingItemSwitchListener = var1;
   }

   public final void setSettingPageUiSet(SettingPageUiSet var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.settingPageUiSet = var1;
   }
}
