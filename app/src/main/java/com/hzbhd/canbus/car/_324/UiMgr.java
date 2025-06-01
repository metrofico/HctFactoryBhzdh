package com.hzbhd.canbus.car._324;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
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
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"},
   d2 = {"Lcom/hzbhd/canbus/car/_324/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mHandler", "Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_324/MsgMgr;", "sendAirCommand", "", "dataType", "", "command", "title", "", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final int DATATYPE_FRONTAIR = 113;
   private static final int DATATYPE_REARAIR = 114;
   private static final String TAG = "_324_UiMgr";
   private final Handler mHandler;
   private final MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static void $r8$lambda$2FBTHfgIvYOVTMcZvVM1PkhBV5Q(SettingPageUiSet var0, int var1, int var2) {
      lambda_13$lambda_12(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$DWN1Ez4uiJb4N6Eht5Mq5bRB_Xo() {
      lambda_21$lambda_16$lambda_15();
   }

   // $FF: synthetic method
   public static void $r8$lambda$GvPMwQLINIubjQDH6Tf6GYII6is(UiMgr var0, int var1) {
      lambda_21$lambda_16(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$H8d5DmkbQrgBWGPhTmFm99LkIRo(byte[] var0) {
      sendAirCommand$lambda_26$lambda_25(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$J7CS2IA4AI5gR_kzjOB0XXcgx6Y(OriginalCarDevicePageUiSet var0, Object var1, int var2) {
      lambda_21$lambda_20(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$TiBekg1_IcJjY6cl0X_SwTfE7fk(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$YfLoNwW869EIlRyJFoL_ZqHIIro() {
      lambda_21$lambda_17();
   }

   // $FF: synthetic method
   public static void $r8$lambda$eszcmylP4uji4nKY0CmHH6g_AHw(OriginalCarDevicePageUiSet var0, int var1) {
      lambda_21$lambda_18(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$iHtJpzsekT6yBUEEtUV523g5dUY(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_13$lambda_10(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$jx4xLwebef7lq_vLYEbeC0i_oLo(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$lzIhzwI1NePBUKHBcALZ6I_egtY(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$t7ivvzJ3YPjn8leNBVXN2xelltE(UiMgr var0, RearArea var1, int var2) {
      lambda_9$lambda_8$lambda_5(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$tmpz3EvrVUxd9yEhFalG7p8J5As(UiMgr var0, RearArea var1, int var2) {
      lambda_9$lambda_8$lambda_6(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$wnAlkPqUJKQHP_Z6ySmC_S0aPjU(UiMgr var0, FrontArea var1, int var2) {
      lambda_9$lambda_4$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$yNnog4YfG1y0Y7eWovyHIMVkr5o(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_13$lambda_11(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$zlG_XaUI9B6yCBCtlMDHblhs2ms(byte[] var0) {
      lambda_24$sendCommand$lambda_23$lambda_22(var0);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._324.MsgMgr");
      this.mMsgMgr = (MsgMgr)var2;
      this.mHandler = new Handler(Looper.getMainLooper());
      AirPageUiSet var5 = this.getAirUiSet(var1);
      FrontArea var3 = var5.getFrontArea();
      var3.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda8(this, var3), new UiMgr$$ExternalSyntheticLambda12(this, var3), new UiMgr$$ExternalSyntheticLambda13(this, var3), new UiMgr$$ExternalSyntheticLambda14(this, var3)});
      var3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(113, 13);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(113, 12);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(113, 15);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(113, 14);
         }
      })});
      var3.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(113, 7);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(113, 6);
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
            this.this$0.sendAirCommand(113, 36);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(113, 37);
         }
      }), null, null});
      RearArea var6 = var5.getRearArea();
      var6.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda15(this, var6), null, null, new UiMgr$$ExternalSyntheticLambda1(this, var6)});
      var6.setTempSetViewOnUpDownClickListeners((OnAirTemperatureUpDownClickListener[])(new <undefinedtype>[]{null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(114, 68);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(114, 67);
         }
      }, null}));
      var6.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(114, 70);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(114, 69);
         }
      }));
      var6.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(114, 80);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(114, 81);
         }
      }), null, null});
      SettingPageUiSet var7 = this.getSettingUiSet(var1);
      var7.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda2(var7));
      var7.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda3(var7));
      var7.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda4(var7));
      AmplifierPageUiSet var8 = this.getAmplifierPageUiSet(var1);
      var8.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        return;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var2});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
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
               var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
               var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
               var0[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      var8.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 7)});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(7 - var2)});
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
               var0[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
               var0[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      OriginalCarDevicePageUiSet var9 = this.getOriginalCarDevicePageUiSet(var1);
      var9.setOnOriginalCarDevicePageStatusListener(new UiMgr$$ExternalSyntheticLambda5(this));
      var9.setOnOriginalCarDeviceBackPressedListener(new UiMgr$$ExternalSyntheticLambda9());
      var9.setOnOriginalTopBtnClickListeners(new UiMgr$$ExternalSyntheticLambda10(var9));
      var9.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda11(var9, (<undefinedtype>)(new Object() {
         private byte[] command = new byte[]{22, -118, 0, 0};
         private boolean isFasting;

         public final byte[] getCommand() {
            return this.command;
         }

         public final boolean isFasting() {
            return this.isFasting;
         }

         public final void setCommand(byte[] var1) {
            Intrinsics.checkNotNullParameter(var1, "<set-?>");
            this.command = var1;
         }

         public final void setFasting(boolean var1) {
            this.isFasting = var1;
         }
      })));
      var9.setOnOriginalSongItemClickListener((OnOriginalSongItemClickListener)(new OnOriginalSongItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onSongItemClick(int var1) {
            int var3 = this.this$0.mMsgMgr.getMOrigiSource();
            int var4 = MsgMgr.OriginalSource.TUNER.getValue();
            byte var2 = 4;
            if (var3 == var4) {
               if (this.this$0.mMsgMgr.getMOrigiRadioBand().isFm()) {
                  var2 = 5;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var2, (byte)(var1 + 1)});
            } else if (var3 == MsgMgr.OriginalSource.DISC.getValue()) {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, (byte)(var1 + 1)});
            }

         }

         public void onSongItemLongClick(int var1) {
            if (this.this$0.mMsgMgr.getMOrigiSource() == MsgMgr.OriginalSource.TUNER.getValue()) {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, (byte)(var1 + 1)});
            }

         }
      }));
      PanelKeyPageUiSet var4 = this.getPanelKeyPageUiSet(var1);
      var4.setOnPanelKeyPositionListener((OnPanelKeyPositionListener)(new OnPanelKeyPositionListener(var4, this) {
         final PanelKeyPageUiSet $this_apply;
         final UiMgr this$0;

         {
            this.$this_apply = var1;
            this.this$0 = var2;
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
               if (var1 != 3524221) {
                  switch (var1) {
                     case -1629801603:
                        if (var3.equals("_309_panel_11")) {
                           UiMgr.lambda_24$sendCommand(this.this$0, 16);
                        }
                        break;
                     case -1629801602:
                        if (var3.equals("_309_panel_12")) {
                           UiMgr.lambda_24$sendCommand(this.this$0, 17);
                        }
                        break;
                     default:
                        switch (var1) {
                           case -1299500236:
                              if (var3.equals("_309_panel_1")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 1);
                              }
                              break;
                           case -1299500235:
                              if (var3.equals("_309_panel_2")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 2);
                              }
                              break;
                           case -1299500234:
                              if (var3.equals("_309_panel_3")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 3);
                              }
                              break;
                           case -1299500233:
                              if (var3.equals("_309_panel_4")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 4);
                              }
                              break;
                           case -1299500232:
                              if (var3.equals("_309_panel_5")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 5);
                              }
                              break;
                           case -1299500231:
                              if (var3.equals("_309_panel_6")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 6);
                              }
                              break;
                           case -1299500230:
                              if (var3.equals("_309_panel_7")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 7);
                              }
                              break;
                           case -1299500229:
                              if (var3.equals("_309_panel_8")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 8);
                              }
                              break;
                           case -1299500228:
                              if (var3.equals("_309_panel_9")) {
                                 UiMgr.lambda_24$sendCommand(this.this$0, 9);
                              }
                        }
                  }
               } else if (var3.equals("scan")) {
                  UiMgr.lambda_24$sendCommand(this.this$0, 18);
               }
            }

         }
      }));
   }

   private static final void lambda_13$lambda_10(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var4, (Object)"_143_0xAD_setting7")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, (byte)var3});
      } else if (Intrinsics.areEqual((Object)var4, (Object)"_143_0xAD_setting4")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 18, (byte)var3});
      }

   }

   private static final void lambda_13$lambda_11(SettingPageUiSet var0, int var1, int var2, int var3) {
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"_186_asl")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)(var3 * 7 + 1)});
      }

   }

   private static final void lambda_13$lambda_12(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var3, (Object)"reset")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 19, 1});
      } else if (Intrinsics.areEqual((Object)var3, (Object)"_324_reset_mileage")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, 1});
      }

   }

   private static final void lambda_21$lambda_16(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
         var0.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda0(), 100L);
      }

   }

   private static final void lambda_21$lambda_16$lambda_15() {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 1, 0});
   }

   private static final void lambda_21$lambda_17() {
      CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
   }

   private static final void lambda_21$lambda_18(OriginalCarDevicePageUiSet var0, int var1) {
      if (var2 != null) {
         switch (var2) {
            case "rdm_disc":
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, (byte)(GeneralOriginalCarDeviceData.rdm_disc ^ 1)});
               break;
            case "disc_scan":
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 10, (byte)(GeneralOriginalCarDeviceData.disc_scan ^ 1)});
               break;
            case "rdm":
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 12, (byte)(GeneralOriginalCarDeviceData.rdm ^ 1)});
               break;
            case "rpt":
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 11, (byte)(GeneralOriginalCarDeviceData.rpt ^ 1)});
               break;
            case "scan":
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 13, (byte)(GeneralOriginalCarDeviceData.scan ^ 1)});
               break;
            case "rpt_disc":
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, (byte)(GeneralOriginalCarDeviceData.rpt_disc ^ 1)});
         }
      }

   }

   private static final void lambda_21$lambda_20(OriginalCarDevicePageUiSet var0, Object var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "$fastParams");
      String var3 = var0.getRowBottomBtnAction()[var2];
      if (Intrinsics.areEqual((Object)var3, (Object)"down")) {
         lambda_21$sendFastCommand(var1, 14);
      } else if (Intrinsics.areEqual((Object)var3, (Object)"up")) {
         lambda_21$sendFastCommand(var1, 15);
      }

   }

   private static final void lambda_21$sendFastCommand(Object var0, int var1) {
      byte[] var2 = var0.getCommand();
      if (var0.isFasting()) {
         var2[3] = 0;
      } else {
         var2[2] = (byte)var1;
         var2[3] = 1;
      }

      CanbusMsgSender.sendMsg(var2);
      var0.setFasting(var0.isFasting() ^ true);
   }

   private static final void lambda_24$sendCommand(UiMgr var0, int var1) {
      byte[] var2 = new byte[]{22, 116, (byte)var1, 1};
      CanbusMsgSender.sendMsg(var2);
      var0.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda7(var2), 100L);
   }

   private static final void lambda_24$sendCommand$lambda_23$lambda_22(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this_run");
      var0[3] = 0;
      CanbusMsgSender.sendMsg(var0);
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
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[0][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_9$lambda_8$lambda_6(UiMgr var0, RearArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private final void sendAirCommand(int var1, int var2) {
      byte[] var3 = new byte[]{22, (byte)var1, (byte)var2, 1};
      CanbusMsgSender.sendMsg(var3);
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda6(var3), 100L);
   }

   private final void sendAirCommand(String var1) {
      switch (var1) {
         case "clean_air":
            this.sendAirCommand(113, 38);
            break;
         case "rear_blow_head_foot":
            this.sendAirCommand(114, 73);
            break;
         case "rear_auto":
            this.sendAirCommand(114, 65);
            break;
         case "rear_lock":
            this.sendAirCommand(114, 64);
            break;
         case "rear_power":
            this.sendAirCommand(114, 66);
            break;
         case "blow_positive":
            this.sendAirCommand(113, 16);
            break;
         case "rear_blow_foot":
            this.sendAirCommand(114, 74);
            break;
         case "rear_blow_head":
            this.sendAirCommand(114, 72);
            break;
         case "blow_negative":
            this.sendAirCommand(113, 17);
            break;
         case "max_front":
            this.sendAirCommand(113, 4);
            break;
         case "front_window_heat":
            this.sendAirCommand(113, 41);
            break;
         case "ac":
            this.sendAirCommand(113, 8);
            break;
         case "auto":
            this.sendAirCommand(113, 1);
            break;
         case "dual":
            this.sendAirCommand(113, 2);
            break;
         case "rear":
            this.sendAirCommand(113, 5);
            break;
         case "power":
            this.sendAirCommand(113, 10);
            break;
         case "swing":
            this.sendAirCommand(113, 40);
            break;
         case "blow_window":
            this.sendAirCommand(113, 33);
            break;
         case "negative_ion":
            this.sendAirCommand(113, 39);
            break;
         case "in_out_cycle":
            this.sendAirCommand(113, 3);
            break;
         case "auto_wind_mode":
            this.sendAirCommand(113, 9);
            break;
         case "blow_head_foot":
            this.sendAirCommand(113, 18);
            break;
         case "blow_foot":
            this.sendAirCommand(113, 35);
            break;
         case "blow_head":
            this.sendAirCommand(113, 34);
            break;
         case "blow_window_foot":
            this.sendAirCommand(113, 19);
      }

   }

   private static final void sendAirCommand$lambda_26$lambda_25(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this_run");
      var0[3] = 0;
      CanbusMsgSender.sendMsg(var0);
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"},
      d2 = {"Lcom/hzbhd/canbus/car/_324/UiMgr$Companion;", "", "()V", "DATATYPE_FRONTAIR", "", "DATATYPE_REARAIR", "TAG", "", "CanBusInfo_release"},
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
