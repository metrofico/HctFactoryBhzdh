package com.hzbhd.canbus.car._322;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"Lcom/hzbhd/canbus/car/_322/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mHandler", "Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_322/MsgMgr;", "getAmplifierData", "", "getCusPanoramicView", "Landroid/view/View;", "sendAirCommand", "", "dataType", "", "command", "title", "", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final int FRONT_AIR_DATA_TYPE = 149;
   private static final int REAR_AIR_DATA_TYPE = 150;
   private static final String TAG = "_322_UiMgr";
   private final Handler mHandler;
   private final MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static void $r8$lambda$0v_CuX_saaP_QfskPe5B4JLGrKQ(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Ayx529iAnAVKj_YUA4cjXLGKv48(OriginalCarDevicePageUiSet var0, UiMgr var1, int var2) {
      lambda_34$lambda_24(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$C0ajNYreUXa4Ws5xS50dhQR2Hac(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$LiLlSWxbVSWLujyQpvNg_a0aA_k(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$NkQmwbnHfMZsyKXW67FWKv4YLos(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$PJtH8dxgPjj2Bz2kjWBphO423MI(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      lambda_19$lambda_14(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$PNg5QjcQ_SlIOTY7nJo1oqU9jFI(byte[] var0) {
      sendAirCommand$lambda_36$lambda_35(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$WS_6498tze0EOeOA0_t11hurr54(SettingPageUiSet var0, UiMgr var1, int var2, int var3) {
      lambda_19$lambda_18(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$_F20_UsVQ8wwO_wac3KNuYPVmU4(ParkPageUiSet var0, int var1) {
      lambda_21$lambda_20(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$fKmP3GB9unixnMFGJwwtL7_MkJc() {
      lambda_34$lambda_33$lambda_27();
   }

   // $FF: synthetic method
   public static void $r8$lambda$g0HIqZzVYJgm5oS4C_Axni_YOkk(UiMgr var0, RearArea var1, int var2) {
      lambda_9$lambda_8$lambda_5(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$jIIxQgzC8k2ej75vG7bj_eI3UKQ(UiMgr var0, OriginalCarDevicePageUiSet var1, Ref.BooleanRef var2, Ref.ObjectRef var3, int var4) {
      lambda_34$lambda_33(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ss27lPk4VoIQlQdoLpMXBk0GHZU() {
      lambda_34$lambda_33$lambda_29();
   }

   // $FF: synthetic method
   public static void $r8$lambda$wpxgsNk6j6PM_cnjpyqranwx5NM(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      lambda_19$lambda_15(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$yAOlgUcAwIT630kU5E2Eh1HRuLE(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      lambda_19$lambda_12(var0, var1, var2, var3, var4);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._322.MsgMgr");
      this.mMsgMgr = (MsgMgr)var2;
      this.mHandler = new Handler(Looper.getMainLooper());
      AirPageUiSet var5 = this.getAirUiSet(var1);
      FrontArea var3 = var5.getFrontArea();
      var3.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda7(this, var3), new UiMgr$$ExternalSyntheticLambda10(this, var3), new UiMgr$$ExternalSyntheticLambda11(this, var3), new UiMgr$$ExternalSyntheticLambda12(this, var3)});
      var3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(149, 18);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(149, 19);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(149, 20);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(149, 21);
         }
      })});
      var3.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(149, 16);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(149, 17);
         }
      }));
      var3.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(149, 32);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(149, 34);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(149, 33);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(149, 35);
         }
      })});
      RearArea var6 = var5.getRearArea();
      var6.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new UiMgr$$ExternalSyntheticLambda13(this, var6)});
      var6.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(150, 14);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(150, 15);
         }
      }, null}));
      var6.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(150, 12);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(150, 13);
         }
      }));
      SettingPageUiSet var7 = this.getSettingUiSet(var1);
      var7.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda14(var7, this));
      var7.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda1(var7, this));
      var7.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda2(var7, this));
      var7.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda3(var7, this));
      ParkPageUiSet var8 = this.getParkPageUiSet(var1);
      var8.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda4(var8));
      AmplifierPageUiSet var10 = this.getAmplifierPageUiSet(var1);
      var10.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            byte[] var4;
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        return;
                     }

                     var4 = this.this$0.getAmplifierData();
                     var4[7] = (byte)(var2 + 3);
                     CanbusMsgSender.sendMsg(var4);
                  } else {
                     var4 = this.this$0.getAmplifierData();
                     var4[6] = (byte)(var2 + 3);
                     CanbusMsgSender.sendMsg(var4);
                  }
               } else {
                  var4 = this.this$0.getAmplifierData();
                  var4[5] = (byte)(var2 + 3);
                  CanbusMsgSender.sendMsg(var4);
               }
            } else {
               var4 = this.this$0.getAmplifierData();
               var4[2] = (byte)DataHandleUtils.setIntFromByteWithBit(var4[2], var2, 0, 5);
               CanbusMsgSender.sendMsg(var4);
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
               var0[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
               var0[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
               var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
               var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 4;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      var10.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            byte[] var4;
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               var4 = this.this$0.getAmplifierData();
               var4[4] = (byte)(-var2 + 7 + 3);
               CanbusMsgSender.sendMsg(var4);
            } else {
               var4 = this.this$0.getAmplifierData();
               var4[3] = (byte)(var2 + 7 + 3);
               CanbusMsgSender.sendMsg(var4);
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
      OriginalCarDevicePageUiSet var9 = this.getOriginalCarDevicePageUiSet(var1);
      Ref.BooleanRef var4 = new Ref.BooleanRef();
      Ref.ObjectRef var11 = new Ref.ObjectRef();
      var11.element = new byte[0];
      var9.setOnOriginalTopBtnClickListeners(new UiMgr$$ExternalSyntheticLambda8(var9, this));
      var9.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda9(this, var9, var4, var11));
      var9.setOnOriginalSongItemClickListener((OnOriginalSongItemClickListener)(new OnOriginalSongItemClickListener() {
         public void onSongItemClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -95, 12, (byte)(var1 + 1)});
         }

         public void onSongItemLongClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -95, 11, (byte)(var1 + 1)});
         }
      }));
   }

   private final byte[] getAmplifierData() {
      byte[] var2 = (byte[])this.mMsgMgr.getMAmplifierData().clone();
      var2[0] = 22;
      var2[1] = -109;
      byte var1 = var2[8];
      var2[8] = (byte)DataHandleUtils.setIntFromByteWithBit(var1, (var1 >> 2 & 3) + 1, 2, 2);
      return var2;
   }

   private static final void lambda_19$lambda_12(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var5 != null) {
         byte[] var6;
         switch (var5) {
            case "_94_driver_cushion":
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, (byte)var4});
               break;
            case "_118_setting_title_97":
               var6 = var1.getAmplifierData();
               var6[8] = (byte)DataHandleUtils.setIntFromByteWithBit(var6[8], var4, 0, 2);
               CanbusMsgSender.sendMsg(var6);
               break;
            case "_94_temperature_unit":
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 84, (byte)var4});
               break;
            case "ford_range_unit":
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 82, (byte)(1 - var4)});
               break;
            case "_304_atoms_lamp_setup":
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 97, (byte)(var4 + 1)});
               break;
            case "language_setup":
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 83, (byte)(var4 * 7 + 1)});
               break;
            case "_94_passenger_cushion":
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 10, (byte)var4});
               break;
            case "_94_passenger_backrest":
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, (byte)var4});
               break;
            case "_94_driver_backrest":
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, (byte)var4});
               break;
            case "_279_sound":
               var6 = var1.getAmplifierData();
               var6[8] = (byte)DataHandleUtils.setIntFromByteWithBit(var6[8], var4, 2, 2);
               CanbusMsgSender.sendMsg(var6);
         }
      }

   }

   private static final void lambda_19$lambda_14(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn(), (Object)"_197_amplifier_mute")) {
         byte[] var5 = var1.getAmplifierData();
         var5[2] = (byte)DataHandleUtils.setOneBit(var5[2], 7, var4);
         CanbusMsgSender.sendMsg(var5);
      }

   }

   private static final void lambda_19$lambda_15(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var5 != null) {
         switch (var5) {
            case "_94_driver_low":
               lambda_19$sendBackrestCommand(3, var1.mMsgMgr.getDriver().getLow() - var4);
               break;
            case "_94_driver_mid":
               lambda_19$sendBackrestCommand(2, var1.mMsgMgr.getDriver().getMiddle() - var4);
               break;
            case "_94_passenger_high":
               lambda_19$sendBackrestCommand(4, var1.mMsgMgr.getPassenger().getHigh() - var4);
               break;
            case "_94_passenger_low":
               lambda_19$sendBackrestCommand(6, var1.mMsgMgr.getPassenger().getLow() - var4);
               break;
            case "_94_passenger_mid":
               lambda_19$sendBackrestCommand(5, var1.mMsgMgr.getPassenger().getMiddle() - var4);
               break;
            case "_94_driver_high":
               lambda_19$sendBackrestCommand(1, var1.mMsgMgr.getDriver().getHigh() - var4);
         }
      }

   }

   private static final void lambda_19$lambda_18(SettingPageUiSet var0, UiMgr var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      byte[] var5;
      if (Intrinsics.areEqual((Object)var4, (Object)"_322_all_reset")) {
         var5 = var1.getAmplifierData();
         var5[8] = (byte)DataHandleUtils.setOneBit(var5[8], 7, 1);
         CanbusMsgSender.sendMsg(var5);
      } else if (Intrinsics.areEqual((Object)var4, (Object)"_322_sound_reset")) {
         var5 = var1.getAmplifierData();
         var5[8] = (byte)DataHandleUtils.setOneBit(var5[8], 6, 1);
         CanbusMsgSender.sendMsg(var5);
      }

   }

   private static final void lambda_19$sendBackrestCommand(int var0, int var1) {
      byte var3 = 0;
      byte var2 = (byte)var0;
      byte var4;
      if (var1 > 0) {
         var4 = 1;
      } else {
         var4 = var3;
         if (var1 < 0) {
            var4 = 2;
         }
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, var2, (byte)var4});
   }

   private static final void lambda_21$lambda_20(ParkPageUiSet var0, int var1) {
      String var2 = ((ParkPageUiSet.Bean)var0.getPanoramicBtnList().get(var1)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var2, (Object)"_220_normal")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -105, 96, 1});
      } else if (Intrinsics.areEqual((Object)var2, (Object)"overlook")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -105, 96, 2});
      }

   }

   private static final void lambda_34$checkFastStatus(Ref.BooleanRef var0, Ref.ObjectRef var1, byte[] var2) {
      Log.i("_322_UiMgr", "2 checkFastStatus: " + var0.element);
      boolean var4 = var0.element;
      boolean var3 = false;
      if (var4) {
         var2 = (byte[])var1.element;
         var2[3] = 0;
      }

      CanbusMsgSender.sendMsg(var2);
      if (((byte[])var1.element).length == 0) {
         var3 = true;
      }

      if (var3 ^ true) {
         var0.element ^= true;
      }

      Log.i("_322_UiMgr", "1 checkFastStatus: " + var0.element);
   }

   private static final void lambda_34$lambda_24(OriginalCarDevicePageUiSet var0, UiMgr var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var3 != null) {
         var2 = var3.hashCode();
         if (var2 != 3116) {
            if (var2 != 3271) {
               byte[] var4;
               if (var2 != 112763) {
                  if (var2 != 113142) {
                     if (var2 == 3016245 && var3.equals("band")) {
                        var3 = var1.mMsgMgr.getMOriginalRadioBand();
                        switch (var3) {
                           case "AM1":
                              CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 2});
                              break;
                           case "AM2":
                              CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 1});
                              break;
                           case "FM1":
                              CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 4});
                              break;
                           case "FM2":
                              CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 5});
                              break;
                           case "FM-AST":
                              CanbusMsgSender.sendMsg(new byte[]{22, -95, 6, 3});
                        }
                     }
                  } else if (var3.equals("rpt")) {
                     if (GeneralOriginalCarDeviceData.rpt) {
                        var4 = new byte[]{22, -93, 8, 1};
                     } else {
                        var4 = new byte[]{22, -93, 6, 1};
                     }

                     CanbusMsgSender.sendMsg(var4);
                  }
               } else if (var3.equals("rdm")) {
                  if (GeneralOriginalCarDeviceData.rdm) {
                     var4 = new byte[]{22, -93, 7, 1};
                  } else {
                     var4 = new byte[]{22, -93, 5, 1};
                  }

                  CanbusMsgSender.sendMsg(var4);
               }
            } else if (var3.equals("fm")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -95, 5, 2});
            }
         } else if (var3.equals("am")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -95, 5, 1});
         }
      }

   }

   private static final void lambda_34$lambda_33(UiMgr var0, OriginalCarDevicePageUiSet var1, Ref.BooleanRef var2, Ref.ObjectRef var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$isFastStatus");
      Intrinsics.checkNotNullParameter(var3, "$fastCommand");
      int var5 = var0.mMsgMgr.getMOriginalDeviceStatus();
      if (var5 != 1) {
         if (var5 == 2) {
            String var12 = var1.getRowBottomBtnAction()[var4];
            if (var12 != null) {
               var4 = var12.hashCode();
               long var6 = 100L;
               byte[] var11;
               switch (var4) {
                  case 3739:
                     if (var12.equals("up")) {
                        if (var2.element) {
                           var11 = (byte[])var3.element;
                           var11[3] = 0;
                        } else {
                           var11 = new byte[]{22, -93, 10, 1};
                           var3.element = var11;
                        }

                        CanbusMsgSender.sendMsg(var11);
                        var2.element ^= true;
                     }
                     break;
                  case 3089570:
                     if (var12.equals("down")) {
                        if (var2.element) {
                           var11 = (byte[])var3.element;
                           var11[3] = 0;
                        } else {
                           var11 = new byte[]{22, -93, 9, 1};
                           var3.element = var11;
                        }

                        CanbusMsgSender.sendMsg(var11);
                        var2.element ^= true;
                     }
                     break;
                  case 3317767:
                     if (var12.equals("left")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -93, 1, 1});
                     }
                     break;
                  case 3443508:
                     if (var12.equals("play")) {
                        Handler var14 = var0.mHandler;
                        UiMgr$$ExternalSyntheticLambda0 var10 = new UiMgr$$ExternalSyntheticLambda0();
                        if (var2.element) {
                           byte[] var18 = (byte[])var3.element;
                           var18[3] = 0;
                           Unit var17 = Unit.INSTANCE;
                           CanbusMsgSender.sendMsg(var18);
                        } else {
                           var6 = 0L;
                        }

                        var14.postDelayed(var10, var6);
                        var2.element = false;
                     }
                     break;
                  case 106440182:
                     if (var12.equals("pause")) {
                        Handler var9 = var0.mHandler;
                        UiMgr$$ExternalSyntheticLambda6 var13 = new UiMgr$$ExternalSyntheticLambda6();
                        if (var2.element) {
                           byte[] var16 = (byte[])var3.element;
                           var16[3] = 0;
                           Unit var8 = Unit.INSTANCE;
                           CanbusMsgSender.sendMsg(var16);
                        } else {
                           var6 = 0L;
                        }

                        var9.postDelayed(var13, var6);
                        var2.element = false;
                     }
                     break;
                  case 108511772:
                     if (var12.equals("right")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -93, 2, 1});
                     }
               }
            }
         }
      } else {
         String var15 = var1.getRowBottomBtnAction()[var4];
         if (var15 != null) {
            var4 = var15.hashCode();
            if (var4 != -841905119) {
               if (var4 != 3739) {
                  if (var4 != 3089570) {
                     if (var4 == 1216748385 && var15.equals("next_disc")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -95, 3, 1});
                     }
                  } else if (var15.equals("down")) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -95, 1, 1});
                  }
               } else if (var15.equals("up")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -95, 2, 1});
               }
            } else if (var15.equals("prev_disc")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -95, 4, 1});
            }
         }
      }

   }

   private static final void lambda_34$lambda_33$lambda_27() {
      CanbusMsgSender.sendMsg(new byte[]{22, -93, 3, 1});
   }

   private static final void lambda_34$lambda_33$lambda_29() {
      CanbusMsgSender.sendMsg(new byte[]{22, -93, 4, 1});
   }

   private static final void lambda_9$lambda_4$lambda_0(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[0][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_4$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[1][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_4$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[2][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_4$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_8$lambda_5(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private final void sendAirCommand(int var1, int var2) {
      byte[] var3 = new byte[]{22, (byte)var1, (byte)var2, 1};
      CanbusMsgSender.sendMsg(var3);
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda5(var3), 100L);
   }

   private final void sendAirCommand(String var1) {
      switch (var1) {
         case "steering_wheel_heating":
            this.sendAirCommand(149, 13);
            break;
         case "front_defog":
            this.sendAirCommand(149, 5);
            break;
         case "ac_max":
            this.sendAirCommand(149, 2);
            break;
         case "rear_lock":
            this.sendAirCommand(150, 31);
            break;
         case "rear_defog":
            this.sendAirCommand(149, 6);
            break;
         case "rear_power":
            this.sendAirCommand(150, 0);
            break;
         case "max_front":
            this.sendAirCommand(149, 12);
            break;
         case "front_window_heat":
            this.sendAirCommand(149, 14);
            break;
         case "ac":
            this.sendAirCommand(149, 1);
            break;
         case "auto":
            this.sendAirCommand(149, 4);
            break;
         case "dual":
            this.sendAirCommand(149, 7);
            break;
         case "power":
            this.sendAirCommand(149, 0);
            break;
         case "in_out_cycle":
            this.sendAirCommand(149, 3);
            break;
         case "blow_foot":
            this.sendAirCommand(149, 9);
            break;
         case "blow_head":
            this.sendAirCommand(149, 8);
      }

   }

   private static final void sendAirCommand$lambda_36$lambda_35(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this_run");
      var0[3] = 0;
      CanbusMsgSender.sendMsg(var0);
   }

   public View getCusPanoramicView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      return (View)this.mMsgMgr.getActivePark(var1).getActiveParkView();
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/UiMgr$Companion;", "", "()V", "FRONT_AIR_DATA_TYPE", "", "REAR_AIR_DATA_TYPE", "TAG", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }
}
