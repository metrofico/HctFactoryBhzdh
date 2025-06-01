package com.hzbhd.canbus.car._112;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.FrontArea;
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
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"},
   d2 = {"Lcom/hzbhd/canbus/car/_112/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mHandler", "Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_112/MsgMgr;", "removeSettingItems", "", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_112_UiMgr";
   private final Handler mHandler;
   private final MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static String $r8$lambda$2_w65KSrAeAenJ2EGTyLBkuvyco(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      return lambda_13$lambda_11(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Aes3d4lKdhOVPhb9txyPdWVxFF4(OriginalCarDevicePageUiSet var0, int var1) {
      lambda_17$lambda_16(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ByT7LYFqnADlnOIICoiUbAHrliQ(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_13$lambda_7(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$M2ZbIih1n2M1jQ0Oc5tSRPNaz8g(FrontArea var0, int var1) {
      lambda_6$lambda_5$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ZUpIZ4xqAQ6wi2yQMd87R9i6b6U(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      lambda_13$lambda_8(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$aJqrgaI9tTwbzmdTVtm4glVHbY8(FrontArea var0, int var1) {
      lambda_6$lambda_5$lambda_4(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$blzLKtK9uEiHayzqM7h0wD5HQWE(SettingPageUiSet var0, int var1, int var2) {
      lambda_13$lambda_10(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$cr2P4hLmGKVBC2CL8c5kuk7buM0(FrontArea var0, int var1) {
      lambda_6$lambda_5$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$gFIFEh_t1fmiBC_i5z43kx68ejc(FrontArea var0, int var1) {
      lambda_6$lambda_5$lambda_2(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$h9OszeubbfJjqYdIYtynFnwQY5E(OriginalCarDevicePageUiSet var0, int var1) {
      lambda_17$lambda_15(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ojcdBPzolZjNb9VykN5qi5iuXg4(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_13$lambda_9(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$yjbzP7LET8Zb8YvpFeIFoFw1CjY(SettingPageUiSet var0, Context var1, int var2, int var3) {
      lambda_13$lambda_12(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
      this.mMsgMgr = (MsgMgr)var2;
      this.mHandler = new Handler(Looper.getMainLooper());
      FrontArea var4 = this.getAirUiSet(var1).getFrontArea();
      var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(var4), new UiMgr$$ExternalSyntheticLambda5(var4), new UiMgr$$ExternalSyntheticLambda6(var4), new UiMgr$$ExternalSyntheticLambda7(var4)});
      var4.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            UiMgr.lambda_6$sendAirCommand(5);
         }

         public void onClickUp() {
            UiMgr.lambda_6$sendAirCommand(4);
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            UiMgr.lambda_6$sendAirCommand(21);
         }

         public void onClickUp() {
            UiMgr.lambda_6$sendAirCommand(20);
         }
      })});
      var4.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
         public void onClickLeft() {
            UiMgr.lambda_6$sendAirCommand(7);
         }

         public void onClickRight() {
            UiMgr.lambda_6$sendAirCommand(6);
         }
      }));
      var4.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{(OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener() {
         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr.lambda_6$sendAirCommand(17);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener() {
         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr.lambda_6$sendAirCommand(18);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener() {
         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr.lambda_6$sendAirCommand(22);
         }
      }), (OnAirSeatHeatColdMinPlusClickListener)(new OnAirSeatHeatColdMinPlusClickListener() {
         public void onClickMin() {
         }

         public void onClickPlus() {
            UiMgr.lambda_6$sendAirCommand(23);
         }
      })});
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda8(var5));
      var5.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda9(var5, this));
      var5.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda10(var5));
      var5.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda11(var5));
      var5.setOnSettingItemSeekbarSetTextListener(new UiMgr$$ExternalSyntheticLambda1(var5, var1));
      var5.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda2(var5, var1));
      AmplifierPageUiSet var6 = this.getAmplifierPageUiSet(var1);
      var6.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        return;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)(var2 + 1)});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 1)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 1)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)var2});
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
               var0[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
               var0[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      var6.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 10)});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)(10 - var2)});
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
      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(var1);
      var3.setOnOriginalTopBtnClickListeners(new UiMgr$$ExternalSyntheticLambda3(var3));
      var3.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda4(var3));
      var3.setOnOriginalSongItemClickListener((OnOriginalSongItemClickListener)(new OnOriginalSongItemClickListener() {
         public void onSongItemClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -56, 16, (byte)(var1 + 1)});
         }

         public void onSongItemLongClick(int var1) {
         }
      }));
   }

   private static final void lambda_13$lambda_10(SettingPageUiSet var0, int var1, int var2) {
      if (var3 != null) {
         switch (var3) {
            case "vm_golf7_vehicle_setup_factory_setup":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, -86});
               break;
            case "_112_reset_trip_a":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, 1});
               break;
            case "_112_reset_trip_b":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 1});
         }
      }

   }

   private static final String lambda_13$lambda_11(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "$context");
      String var5;
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn(), (Object)"speed_linkage_volume_adjustment")) {
         if (var4 == 0) {
            var5 = CommUtil.getStrByResId(var1, "close");
         } else {
            var5 = String.valueOf(var4);
         }
      } else {
         var5 = String.valueOf(var4);
      }

      return var5;
   }

   private static final void lambda_13$lambda_12(SettingPageUiSet var0, Context var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "$context");
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (var4 != null) {
         var2 = var4.hashCode();
         MsgMgrInterface var5;
         if (var2 != -625317761) {
            if (var2 != -217893235) {
               if (var2 == 53216746 && var4.equals("_112_xCA")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 2});
                  var5 = MsgMgrFactory.getCanMsgMgr(var1);
                  Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
                  ((MsgMgr)var5).myToast("Select car3 success!");
               }
            } else if (var4.equals("low_config")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -54, 0});
               var5 = MsgMgrFactory.getCanMsgMgr(var1);
               Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
               ((MsgMgr)var5).myToast("Select car1 success!");
            }
         } else if (var4.equals("high_config")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 1});
            var5 = MsgMgrFactory.getCanMsgMgr(var1);
            Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.MsgMgr");
            ((MsgMgr)var5).myToast("Select car2 success!");
         }
      }

   }

   private static final void lambda_13$lambda_7(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var4 != null) {
         var2 = var4.hashCode();
         byte var5 = 0;
         switch (var2) {
            case -2065696607:
               if (var4.equals("_118_setting_title_16")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte)var3});
               }

               return;
            case -2065696577:
               if (var4.equals("_118_setting_title_25")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -44, (byte)var3});
               }

               return;
            case -2065696573:
               if (var4.equals("_118_setting_title_29")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte)var3});
               }

               return;
            case -2065696547:
               if (!var4.equals("_118_setting_title_34")) {
                  return;
               }
               break;
            case -2065696395:
               if (var4.equals("_118_setting_title_81")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
               }

               return;
            case -1475383431:
               if (var4.equals("hiworld_jeep_123_0x62_data2_10")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)(var3 * 30)});
               }

               return;
            case -1475383367:
               if (var4.equals("hiworld_jeep_123_0x62_data2_32")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)(var3 * 30)});
               }

               return;
            case -1475383303:
               if (var4.equals("hiworld_jeep_123_0x62_data2_54")) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           var5 = 40;
                        }
                     } else {
                        var5 = 20;
                     }
                  } else {
                     var5 = 3;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)var5});
               }

               return;
            case -1160841604:
               if (var4.equals("remote_door_unlock")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte)var3});
               }

               return;
            case -1082386052:
               if (var4.equals("hiworld_jeep_123_0x60_data1_65")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte)var3});
               }

               return;
            case -970933316:
               if (var4.equals("_112_headunit_power_off_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte)var3});
               }

               return;
            case -910636385:
               if (var4.equals("_112_aux1_power")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte)var3});
               }

               return;
            case -556746442:
               if (var4.equals("hiworld_jeep_123_0xC1_data1")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
               }

               return;
            case -556746441:
               if (var4.equals("hiworld_jeep_123_0xC1_data2")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
               }

               return;
            case -556746440:
               if (var4.equals("hiworld_jeep_123_0xC1_data3")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
               }

               return;
            case -392737078:
               if (var4.equals("hiworld_jeep_123_0xCA_0X01")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
               }

               return;
            case -392737071:
               if (var4.equals("hiworld_jeep_123_0xCA_0X08")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
               }

               return;
            case -58415930:
               if (var4.equals("seat_auto_heat")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte)var3});
               }

               return;
            case -23132704:
               if (var4.equals("_112_aux2_power")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 100, (byte)var3});
               }

               return;
            case 102584022:
               if (var4.equals("language_setup")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)(var3 + 1)});
               }

               return;
            case 864370977:
               if (var4.equals("_112_aux3_power")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 103, (byte)var3});
               }

               return;
            case 1141851576:
               if (var4.equals("_112_offset_dist_alarm")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -98, (byte)var3});
               }

               return;
            case 1170379802:
               if (!var4.equals("hiworld_jeep_123_0x43_data3_0")) {
                  return;
               }
               break;
            case 1170379803:
               if (var4.equals("hiworld_jeep_123_0x43_data3_1")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte)var3});
               }

               return;
            case 1180290621:
               if (var4.equals("_118_setting_title_9")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte)(var3 * 30)});
               }

               return;
            case 1751874658:
               if (var4.equals("_112_aux4_power")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 106, (byte)var3});
               }

               return;
            case 1922035701:
               if (var4.equals("hiworld_jeep_123_0x43_data3_54")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, (byte)var3});
               }

               return;
            case 1922035765:
               if (var4.equals("hiworld_jeep_123_0x43_data3_76")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte)var3});
               }

               return;
            case 1922065364:
               if (var4.equals("hiworld_jeep_123_0x43_data4_10")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)var3});
               }

               return;
            case 1922095155:
               if (var4.equals("hiworld_jeep_123_0x43_data5_10")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)var3});
               }

               return;
            case 1922095219:
               if (var4.equals("hiworld_jeep_123_0x43_data5_32")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte)var3});
               }

               return;
            case 1989254415:
               if (var4.equals("_176_car_setting_title_22")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, (byte)var3});
               }

               return;
            case 2048963168:
               if (var4.equals("_112_aux1_type")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)var3});
               }

               return;
            case 2066915788:
               if (var4.equals("_112_offset_dist_alarm_vol")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -97, (byte)var3});
               }

               return;
            case 2077592319:
               if (var4.equals("_112_aux2_type")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 99, (byte)var3});
               }

               return;
            case 2106221470:
               if (var4.equals("_112_aux3_type")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 102, (byte)var3});
               }

               return;
            case 2134850621:
               if (var4.equals("_112_aux4_type")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 105, (byte)var3});
               }

               return;
            default:
               return;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, (byte)var3});
      }

   }

   private static final void lambda_13$lambda_8(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var5 != null) {
         switch (var5) {
            case "_118_setting_title_18":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte)var4});
               break;
            case "_118_setting_title_19":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var4});
               break;
            case "_118_setting_title_24":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte)var4});
               break;
            case "_118_setting_title_26":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte)var4});
               break;
            case "_118_setting_title_27":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -46, (byte)var4});
               break;
            case "_118_setting_title_28":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -45, (byte)var4});
               break;
            case "_118_setting_title_30":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte)var4});
               break;
            case "_118_setting_title_37":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, (byte)var4});
               break;
            case "_118_setting_title_38":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte)var4});
               break;
            case "_118_setting_title_65":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -99, (byte)var4});
               break;
            case "_118_setting_title_74":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)var4});
               break;
            case "_118_setting_title_96":
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte)var4});
               break;
            case "_112_rear_mirror_dimmer":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte)var4});
               break;
            case "_213_car_set14_2_0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte)var4});
               break;
            case "hiworld_jeep_123_0x62_data3_0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte)var4});
               break;
            case "hiworld_jeep_123_0x62_data3_3":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var4});
               break;
            case "hiworld_jeep_123_0x62_data3_4":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var4});
               break;
            case "_112_rear_park_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -16, (byte)var4});
               break;
            case "_112_adaptive_high_beam":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 44, (byte)var4});
               break;
            case "_112_horn_remote_start":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte)var4});
               break;
            case "amplifier_switch":
               var1.mMsgMgr.setAmplifierSwitch(var4);
               break;
            case "_112_auto_anti_glare":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte)var4});
               break;
            case "_112_aux4_recalled":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 107, (byte)var4});
               break;
            case "_112_aux3_recalled":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 104, (byte)var4});
               break;
            case "_112_aux2_recalled":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 101, (byte)var4});
               break;
            case "_112_aux1_recalled":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 98, (byte)var4});
               break;
            case "hiworld_jeep_123_0x43_data4_2":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte)var4});
               break;
            case "hiworld_jeep_123_0x43_data4_3":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte)var4});
               break;
            case "hiworld_jeep_123_0x43_data4_5":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, (byte)var4});
               break;
            case "hiworld_jeep_123_0x43_data4_6":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte)var4});
               break;
            case "hiworld_jeep_123_0x43_data5_4":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)var4});
               break;
            case "_118_setting_title_4":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte)var4});
               break;
            case "_250_welcome_light":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte)var4});
               break;
            case "_112_auto_park_assist":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -15, (byte)var4});
               break;
            case "_112_auto_suspension":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -43, (byte)var4});
               break;
            case "_112_cornering_lights":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var4});
               break;
            case "hiworld_jeep_123_0x60_data1_0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var4});
               break;
            case "hiworld_jeep_123_0x60_data1_1":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var4});
               break;
            case "hiworld_jeep_123_0x60_data1_3":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte)var4});
               break;
            case "hiworld_jeep_123_0x60_data1_4":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte)var4});
               break;
            case "_276_setting_0":
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var4});
         }
      }

   }

   private static final void lambda_13$lambda_9(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      if (var4 != null) {
         var1 = var4.hashCode();
         if (var1 != -1475383239) {
            if (var1 != -903809344) {
               if (var1 == 1508683421 && var4.equals("hiworld_jeep_123_0x62_data3_765")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte)var3});
               }
            } else if (var4.equals("speed_linkage_volume_adjustment")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
            }
         } else if (var4.equals("hiworld_jeep_123_0x62_data2_76")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte)var3});
         }
      }

   }

   private static final void lambda_17$lambda_15(OriginalCarDevicePageUiSet var0, int var1) {
      String var2 = var0.getRowTopBtnAction()[var1];
      byte var3;
      if (Intrinsics.areEqual((Object)var2, (Object)"rdm")) {
         if (GeneralOriginalCarDeviceData.rdm) {
            var3 = 10;
         } else {
            var3 = 9;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var3, 0});
      } else if (Intrinsics.areEqual((Object)var2, (Object)"rpt")) {
         if (GeneralOriginalCarDeviceData.rpt) {
            var3 = 15;
         } else {
            var3 = 14;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var3, 0});
      }

   }

   private static final void lambda_17$lambda_16(OriginalCarDevicePageUiSet var0, int var1) {
      if (var2 != null) {
         switch (var2) {
            case "up":
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 6, 0});
               break;
            case "down":
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 5, 0});
               break;
            case "left":
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 3, 0});
               break;
            case "play":
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, 0});
               break;
            case "pause":
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, 0});
               break;
            case "right":
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 4, 0});
         }
      }

   }

   private static final void lambda_6$lambda_5$lambda_1(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[0][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "lineBtnAction[0][position]");
      lambda_6$sendAirCommand_0(var2);
   }

   private static final void lambda_6$lambda_5$lambda_2(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[1][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "lineBtnAction[1][position]");
      lambda_6$sendAirCommand_0(var2);
   }

   private static final void lambda_6$lambda_5$lambda_3(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[2][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "lineBtnAction[2][position]");
      lambda_6$sendAirCommand_0(var2);
   }

   private static final void lambda_6$lambda_5$lambda_4(FrontArea var0, int var1) {
      String var2 = var0.getLineBtnAction()[3][var1];
      Intrinsics.checkNotNullExpressionValue(var2, "lineBtnAction[3][position]");
      lambda_6$sendAirCommand_0(var2);
   }

   private static final void lambda_6$sendAirCommand(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte)var0});
   }

   private static final void lambda_6$sendAirCommand_0(String var0) {
      switch (var0) {
         case "steering_wheel_heating":
            lambda_6$sendAirCommand(24);
            break;
         case "front_defog":
            lambda_6$sendAirCommand(12);
            break;
         case "ac_max":
            lambda_6$sendAirCommand(15);
            break;
         case "rear_defog":
            lambda_6$sendAirCommand(14);
            break;
         case "ac":
            lambda_6$sendAirCommand(1);
            break;
         case "auto":
            lambda_6$sendAirCommand(2);
            break;
         case "sync":
            lambda_6$sendAirCommand(13);
            break;
         case "power":
            lambda_6$sendAirCommand(16);
            break;
         case "in_out_cycle":
            lambda_6$sendAirCommand(3);
            break;
         case "blow_head_foot":
            lambda_6$sendAirCommand(9);
            break;
         case "blow_foot":
            lambda_6$sendAirCommand(10);
            break;
         case "blow_head":
            lambda_6$sendAirCommand(8);
            break;
         case "blow_window_foot":
            lambda_6$sendAirCommand(11);
      }

   }

   private final void removeSettingItems(Context var1) {
      int var2 = this.getCurrentCarId();
      Pair var51 = TuplesKt.to("language_setup", new int[]{1, 2, 3, 4, 5, 6, 7});
      Pair var34 = TuplesKt.to("hiworld_jeep_123_0xC1_data3", new int[]{1, 2, 3, 4, 6, 7});
      Pair var30 = TuplesKt.to("hiworld_jeep_123_0xCA_0X01", new int[]{1, 2, 3, 4, 5, 6, 7});
      Pair var42 = TuplesKt.to("hiworld_jeep_123_0xC1_data1", new int[]{1, 2, 3, 4, 5, 6, 7});
      Pair var17 = TuplesKt.to("hiworld_jeep_123_0xC1_data2", new int[]{1, 2, 3, 4, 5, 6, 7});
      Pair var52 = TuplesKt.to("_276_setting_0", new int[]{5});
      Pair var46 = TuplesKt.to("hiworld_jeep_123_0xCA_0X08", new int[]{2, 4, 6, 7});
      Pair var10 = TuplesKt.to("_118_setting_title_81", new int[]{6});
      Pair var6 = TuplesKt.to("_112_reset_trip_a", new int[]{3, 5});
      Pair var22 = TuplesKt.to("_112_reset_trip_b", new int[]{3, 5});
      Pair var53 = TuplesKt.to("hiworld_jeep_123_0x62_data2_10", new int[]{1, 2, 3, 4, 6, 7});
      Pair var39 = TuplesKt.to("hiworld_jeep_123_0x62_data2_32", new int[]{1, 6});
      Pair var18 = TuplesKt.to("hiworld_jeep_123_0x62_data3_4", new int[]{1, 2, 3, 4, 6, 7});
      Pair var40 = TuplesKt.to("hiworld_jeep_123_0x62_data3_3", new int[]{1, 2, 4, 5, 6, 7});
      Pair var41 = TuplesKt.to("_112_cornering_lights", new int[]{1, 2, 3, 4, 7});
      Pair var12 = TuplesKt.to("hiworld_jeep_123_0x62_data3_0", new int[]{1, 6});
      Pair var55 = TuplesKt.to("_112_rear_mirror_dimmer", new int[]{1, 6});
      Pair var32 = TuplesKt.to("_112_auto_anti_glare", new int[]{1});
      Pair var14 = TuplesKt.to("hiworld_jeep_123_0x62_data2_76", new int[]{2, 3, 4});
      Pair var25 = TuplesKt.to("_250_welcome_light", new int[]{2, 3, 4});
      Pair var20 = TuplesKt.to("hiworld_jeep_123_0x62_data3_765", new int[]{4});
      Pair var37 = TuplesKt.to("_112_adaptive_high_beam", new int[]{6});
      Pair var35 = TuplesKt.to("hiworld_jeep_123_0x60_data1_0", new int[]{1, 2, 3, 4, 6, 7});
      Pair var26 = TuplesKt.to("hiworld_jeep_123_0x60_data1_1", new int[]{1, 2, 4, 6});
      Pair var36 = TuplesKt.to("_118_setting_title_74", new int[]{1, 2, 3, 4, 6, 7});
      Pair var11 = TuplesKt.to("_118_setting_title_16", new int[]{1, 4, 6});
      Pair var28 = TuplesKt.to("_213_car_set14_2_0", new int[]{5});
      Pair var44 = TuplesKt.to("hiworld_jeep_123_0x60_data1_3", new int[]{1, 2, 4, 6, 7});
      Pair var33 = TuplesKt.to("hiworld_jeep_123_0x60_data1_65", new int[]{1, 2, 4, 6, 7});
      Pair var48 = TuplesKt.to("_118_setting_title_18", new int[]{1, 6});
      Pair var5 = TuplesKt.to("hiworld_jeep_123_0x60_data1_4", new int[]{1});
      Pair var50 = TuplesKt.to("_112_horn_remote_start", new int[]{2, 4});
      Pair var45 = TuplesKt.to("remote_door_unlock", new int[]{2, 4, 7});
      Pair var9 = TuplesKt.to("_118_setting_title_9", new int[]{1, 2, 3, 6, 7});
      Pair var31 = TuplesKt.to("hiworld_jeep_123_0x62_data2_54", new int[]{1, 6});
      Pair var8 = TuplesKt.to("_118_setting_title_19", new int[]{1, 6});
      Pair var19 = TuplesKt.to("_112_headunit_power_off_mode", new int[]{6});
      Pair var15 = TuplesKt.to("seat_auto_heat", new int[]{1, 4, 6});
      Pair var23 = TuplesKt.to("_118_setting_title_65", new int[]{6});
      Pair var54 = TuplesKt.to("_112_offset_dist_alarm", new int[]{4});
      Pair var47 = TuplesKt.to("_112_offset_dist_alarm_vol", new int[]{4});
      Pair var43 = TuplesKt.to("hiworld_jeep_123_0x43_data3_76", new int[]{1, 3, 4, 6, 7});
      Pair var49 = TuplesKt.to("hiworld_jeep_123_0x43_data5_10", new int[]{1, 6});
      Pair var7 = TuplesKt.to("hiworld_jeep_123_0x43_data5_32", new int[]{1, 3, 4, 6, 7});
      Pair var27 = TuplesKt.to("hiworld_jeep_123_0x43_data5_4", new int[]{1, 6});
      Pair var38 = TuplesKt.to("hiworld_jeep_123_0x43_data4_3", new int[]{1, 2, 3, 4, 6, 7});
      Pair var29 = TuplesKt.to("hiworld_jeep_123_0x43_data4_2", new int[]{1});
      Pair var13 = TuplesKt.to("hiworld_jeep_123_0x43_data4_6", new int[]{1, 6});
      Pair var21 = TuplesKt.to("_118_setting_title_30", new int[]{6});
      Pair var24 = TuplesKt.to("_118_setting_title_29", new int[]{6});
      Pair var16 = TuplesKt.to("_176_car_setting_title_22", new int[]{1, 4});
      String var4 = "hiworld_jeep_123_0x43_data3_0";
      Map var60 = MapsKt.mapOf(new Pair[]{var51, var34, var30, var42, var17, var52, var46, var10, var6, var22, var53, var39, var18, var40, var41, var12, var55, var32, var14, var25, var20, var37, var35, var26, var36, var11, var28, var44, var33, var48, var5, var50, var45, var9, var31, var8, var19, var15, var23, var54, var47, var43, var49, var7, var27, var38, var29, var13, var21, var24, var16, TuplesKt.to("hiworld_jeep_123_0x43_data3_0", new int[]{1, 3, 4}), TuplesKt.to("_118_setting_title_34", new int[]{1, 3, 4}), TuplesKt.to("hiworld_jeep_123_0x43_data3_1", new int[]{1, 3, 4}), TuplesKt.to("hiworld_jeep_123_0x43_data4_10", new int[]{1, 4}), TuplesKt.to("_118_setting_title_4", new int[]{1, 6}), TuplesKt.to("hiworld_jeep_123_0x43_data3_54", new int[]{1, 4}), TuplesKt.to("hiworld_jeep_123_0x43_data4_5", new int[]{1, 2, 3, 4, 6}), TuplesKt.to("_112_rear_park_assist", new int[]{1}), TuplesKt.to("_112_auto_park_assist", new int[]{1}), TuplesKt.to("_118_setting_title_37", new int[]{1, 2, 4}), TuplesKt.to("_118_setting_title_38", new int[]{1, 2, 4, 7}), TuplesKt.to("_118_setting_title_24", new int[]{1}), TuplesKt.to("_118_setting_title_26", new int[]{1, 6}), TuplesKt.to("_118_setting_title_27", new int[]{1, 6}), TuplesKt.to("_118_setting_title_28", new int[]{1, 6}), TuplesKt.to("_118_setting_title_25", new int[]{1, 6}), TuplesKt.to("_112_auto_suspension", new int[]{6})});
      Map var57 = (Map)(new LinkedHashMap());
      Iterator var63 = var60.entrySet().iterator();

      while(var63.hasNext()) {
         Map.Entry var62 = (Map.Entry)var63.next();
         if (ArraysKt.contains((int[])var62.getValue(), var2 % 10) ^ true) {
            var57.put(var62.getKey(), var62.getValue());
         }
      }

      Set var58 = var57.keySet();
      if (var58.size() > 0) {
         Object[] var59 = ((Collection)var58).toArray(new String[0]);
         Intrinsics.checkNotNull(var59, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
         this.removeSettingRightItemByNameList(var1, (String[])var59);
      }

      Unit var61 = Unit.INSTANCE;
      if (ArraysKt.contains(new int[]{1, 3, 4}, var2)) {
         var4 = "_118_setting_title_34";
      }

      this.removeSettingRightItemByNameList(var1, new String[]{var4});
      if (var2 != MsgMgr.VehicleType.JEEP_GRAND_CHEROKEE_2014.getValue()) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_112_aux_switches"});
      }

      boolean var3 = SharePreUtil.getBoolValue(var1, "share_112_is_have_amplifier", true);
      Log.i("_112_UiMgr", "removeSettingItems: SHARE_112_IS_HAVE_AMPLIFIER " + var3);
      Unit var56 = Unit.INSTANCE;
      if (!var3) {
         Log.i("_112_UiMgr", "removeSettingItems: remove");
         this.removeMainPrjBtnByName(var1, "amplifier");
         this.removeSettingLeftItemByNameList(var1, new String[]{"amplifier_setting"});
      }

   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/car/_112/UiMgr$Companion;", "", "()V", "TAG", "", "CanBusInfo_release"},
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
