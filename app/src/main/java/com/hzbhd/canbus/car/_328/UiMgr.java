package com.hzbhd.canbus.car._328;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"},
   d2 = {"Lcom/hzbhd/canbus/car/_328/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mMsgMgr", "Lcom/hzbhd/canbus/car/_328/MsgMgr;", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String SHARE_IS_SUPPORT_PANORAMIC = "share_is_support_panoramic";
   private static final String TAG = "_330_UiMgr";
   private static final byte[] panoramicCommand = new byte[]{22, -57, 1};
   private final MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static void $r8$lambda$9eT9VKLUV3yqFJEZJzTGzuivRpk(SettingPageUiSet var0, byte[] var1, int var2, int var3) {
      lambda_37$lambda_35(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$JGIw9_GwnEp_PO5eX7L1RYrTpls(ParkPageUiSet var0, int var1) {
      lambda_41$lambda_40(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$S5Xo_AxF6ql58LRhvVi1xANEFiM(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      lambda_37$lambda_30(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$U91y5YkcGm5kvmGGXFNTUpnsA1s(SettingPageUiSet var0, byte[] var1, Context var2, UiMgr var3, int var4, int var5, int var6) {
      lambda_37$lambda_29(var0, var1, var2, var3, var4, var5, var6);
   }

   // $FF: synthetic method
   public static void $r8$lambda$XFXJNuZWOBPNPoYgklmPU6rEvt0(ParkPageUiSet var0, Context var1) {
      lambda_41$lambda_39(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$XQenG5qjwwFjWXohMtN79t8pc5U(SettingPageUiSet var0, int var1, int var2) {
      lambda_37$lambda_36(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$b_GQWf7fq_RrMCCQUdrYXUwvq7I(SettingPageUiSet var0, int var1, int var2, int var3) {
      lambda_37$lambda_31(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._328.MsgMgr");
      this.mMsgMgr = (MsgMgr)var2;
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      byte[] var3 = new byte[]{22, -125, -1, 0};
      var4.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0(var4, var3, var1, this));
      var4.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda1(var4, this));
      var4.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda2(var4));
      var4.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda3(var4, var3));
      var4.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda4(var4));
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener() {
         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        return;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var2});
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
      var5.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener() {
         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 11)});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(11 - var2)});
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
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      var6.setOnBackCameraStatusListener(new UiMgr$$ExternalSyntheticLambda5(var6, var1));
      var6.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda6(var6));
   }

   private static final void lambda_37$lambda_29(SettingPageUiSet var0, byte[] var1, Context var2, UiMgr var3, int var4, int var5, int var6) {
      Intrinsics.checkNotNullParameter(var1, "$m0x83Command");
      Intrinsics.checkNotNullParameter(var2, "$context");
      Intrinsics.checkNotNullParameter(var3, "this$0");
      String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var4)).getItemList().get(var5)).getTitleSrn();
      if (var7 != null) {
         var5 = var7.hashCode();
         switch (var5) {
            case -1144939717:
               if (var7.equals("outlander_simple_car_set_10")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, (byte)var6});
               }
               break;
            case -1144939716:
               if (var7.equals("outlander_simple_car_set_11")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 13, (byte)var6});
               }
               break;
            case -1144939715:
               if (var7.equals("outlander_simple_car_set_12")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 14, (byte)var6});
               }
               break;
            default:
               short var8 = 128;
               switch (var5) {
                  case -1144939710:
                     if (var7.equals("outlander_simple_car_set_17")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)var6});
                     }
                     break;
                  case -78472347:
                     if (var7.equals("_328_00")) {
                        var1[2] = 0;
                        var1[3] = (byte)(var6 + 1);
                        CanbusMsgSender.sendMsg(var1);
                     }
                     break;
                  case -78472345:
                     if (var7.equals("_328_02")) {
                        if (var6 != 0) {
                           if (var6 != 1) {
                              return;
                           }

                           var8 = 192;
                        }

                        var1[3] = (byte)var8;
                        var1[2] = 2;
                        CanbusMsgSender.sendMsg(var1);
                     }
                     break;
                  case -78472265:
                     if (var7.equals("_328_1c")) {
                        var1[2] = 28;
                        var1[3] = (byte)(var6 + 1);
                        CanbusMsgSender.sendMsg(var1);
                     }
                     break;
                  case 712683749:
                     if (var7.equals("support_panorama")) {
                        SharePreUtil.setIntValue(var2, "share_is_support_panoramic", var6);
                        var3.mMsgMgr.updateSettings("support_panorama", var6);
                     }
                     break;
                  case 808378399:
                     if (var7.equals("_165_eco_mode")) {
                        var1[2] = 22;
                        var1[3] = (byte)(var6 << 4 | 128);
                        CanbusMsgSender.sendMsg(var1);
                     }
                     break;
                  default:
                     switch (var5) {
                        case -609928118:
                           if (var7.equals("_103_car_setting_title_10")) {
                              var1[2] = 10;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928117:
                           if (var7.equals("_103_car_setting_title_11")) {
                              var1[2] = 11;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928116:
                           if (var7.equals("_103_car_setting_title_12")) {
                              var1[2] = 12;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928115:
                           if (var7.equals("_103_car_setting_title_13")) {
                              var1[2] = 13;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928114:
                           if (var7.equals("_103_car_setting_title_14")) {
                              var1[2] = 14;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928113:
                           if (var7.equals("_103_car_setting_title_15")) {
                              var1[2] = 15;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928112:
                           if (var7.equals("_103_car_setting_title_16")) {
                              var1[2] = 16;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928111:
                           if (var7.equals("_103_car_setting_title_17")) {
                              var1[2] = 17;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928110:
                           if (var7.equals("_103_car_setting_title_18")) {
                              var1[2] = 18;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        case -609928109:
                           if (var7.equals("_103_car_setting_title_19")) {
                              var1[2] = 19;
                              var1[3] = (byte)(var6 << 4 | 128);
                              CanbusMsgSender.sendMsg(var1);
                           }
                           break;
                        default:
                           switch (var5) {
                              case -609928087:
                                 if (var7.equals("_103_car_setting_title_20")) {
                                    var1[2] = 20;
                                    var1[3] = (byte)(var6 << 4 | 128);
                                    CanbusMsgSender.sendMsg(var1);
                                 }
                                 break;
                              case -609928086:
                                 if (var7.equals("_103_car_setting_title_21")) {
                                    var1[2] = 21;
                                    var1[3] = (byte)(var6 << 4 | 128);
                                    CanbusMsgSender.sendMsg(var1);
                                 }
                                 break;
                              case -609928085:
                                 if (var7.equals("_103_car_setting_title_22")) {
                                    var1[2] = 23;
                                    var1[3] = (byte)(var6 << 4 | 128);
                                    CanbusMsgSender.sendMsg(var1);
                                 }
                                 break;
                              case -609928084:
                                 if (var7.equals("_103_car_setting_title_23")) {
                                    var1[2] = 24;
                                    var1[3] = (byte)(var6 << 4 | 128);
                                    CanbusMsgSender.sendMsg(var1);
                                 }
                                 break;
                              case -609928083:
                                 if (var7.equals("_103_car_setting_title_24")) {
                                    var1[2] = 25;
                                    var1[3] = (byte)(var6 << 4 | 128);
                                    CanbusMsgSender.sendMsg(var1);
                                 }
                                 break;
                              case -609928082:
                                 if (var7.equals("_103_car_setting_title_25")) {
                                    var1[2] = 26;
                                    var1[3] = (byte)(var6 << 4 | 128);
                                    CanbusMsgSender.sendMsg(var1);
                                 }
                                 break;
                              case -609928081:
                                 if (var7.equals("_103_car_setting_title_26")) {
                                    var1[2] = 27;
                                    var1[3] = (byte)(var6 << 4 | 128);
                                    CanbusMsgSender.sendMsg(var1);
                                 }
                                 break;
                              default:
                                 switch (var5) {
                                    case 118872230:
                                       if (var7.equals("_103_car_setting_title_1")) {
                                          var1[2] = 1;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    case 118872231:
                                       if (var7.equals("_103_car_setting_title_2")) {
                                          var1[2] = 3;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    case 118872232:
                                       if (var7.equals("_103_car_setting_title_3")) {
                                          var1[2] = 4;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    case 118872233:
                                       if (var7.equals("_103_car_setting_title_4")) {
                                          var1[2] = 5;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    case 118872234:
                                       if (var7.equals("_103_car_setting_title_5")) {
                                          var1[2] = 6;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    case 118872235:
                                       if (var7.equals("_103_car_setting_title_6")) {
                                          var1[2] = 7;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    case 118872236:
                                       if (var7.equals("_103_car_setting_title_7")) {
                                          var1[2] = 8;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    case 118872237:
                                       if (var7.equals("_103_car_setting_title_8")) {
                                          var1[2] = 9;
                                          var1[3] = (byte)(var6 << 4 | 128);
                                          CanbusMsgSender.sendMsg(var1);
                                       }
                                       break;
                                    default:
                                       switch (var5) {
                                          case 1902729116:
                                             if (var7.equals("outlander_simple_car_set_8")) {
                                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte)var6});
                                             }
                                             break;
                                          case 1902729117:
                                             if (var7.equals("outlander_simple_car_set_9")) {
                                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte)var6});
                                             }
                                       }
                                 }
                           }
                     }
               }
         }
      }

   }

   private static final void lambda_37$lambda_30(SettingPageUiSet var0, UiMgr var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn(), (Object)"amplifier_switch")) {
         var1.mMsgMgr.setAmplifierSwitch(var4);
      }

   }

   private static final void lambda_37$lambda_31(SettingPageUiSet var0, int var1, int var2, int var3) {
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"_103_punch")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)(var3 + 3 + 2)});
      }

   }

   private static final void lambda_37$lambda_35(SettingPageUiSet var0, byte[] var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "$m0x83Command");
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (var4 != null) {
         var2 = var4.hashCode();
         switch (var2) {
            case -1144939713:
               if (var4.equals("outlander_simple_car_set_14")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 16, 0});
               }
               break;
            case -1144939712:
               if (var4.equals("outlander_simple_car_set_15")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, 0});
               }
               break;
            case -1144939711:
               if (var4.equals("outlander_simple_car_set_16")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 18, 0});
               }
               break;
            default:
               switch (var2) {
                  case -78472264:
                     if (var4.equals("_328_1d")) {
                        var1[2] = 29;
                        var1[3] = 1;
                        CanbusMsgSender.sendMsg(var1);
                     }
                     break;
                  case -78472263:
                     if (var4.equals("_328_1e")) {
                        var1[2] = 30;
                        var1[3] = 1;
                        CanbusMsgSender.sendMsg(var1);
                     }
                     break;
                  case -78472262:
                     if (var4.equals("_328_1f")) {
                        var1[2] = 31;
                        var1[3] = 1;
                        CanbusMsgSender.sendMsg(var1);
                     }
               }
         }
      }

   }

   private static final void lambda_37$lambda_36(SettingPageUiSet var0, int var1, int var2) {
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"_23_enter_panoramic")) {
         CanbusMsgSender.sendMsg(panoramicCommand);
      }

   }

   private static final void lambda_41$lambda_39(ParkPageUiSet var0, Context var1) {
      Intrinsics.checkNotNullParameter(var1, "$context");
      boolean var2 = false;
      if (SharePreUtil.getIntValue(var1, "share_is_support_panoramic", 0) == 1) {
         var2 = true;
      }

      var0.setShowPanoramic(var2);
   }

   private static final void lambda_41$lambda_40(ParkPageUiSet var0, int var1) {
      if (Intrinsics.areEqual((Object)((ParkPageUiSet.Bean)var0.getPanoramicBtnList().get(var1)).getTitleSrn(), (Object)"_328_panoramic_switch")) {
         CanbusMsgSender.sendMsg(panoramicCommand);
      }

   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"},
      d2 = {"Lcom/hzbhd/canbus/car/_328/UiMgr$Companion;", "", "()V", "SHARE_IS_SUPPORT_PANORAMIC", "", "TAG", "panoramicCommand", "", "getPanoramicCommand", "()[B", "CanBusInfo_release"},
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

      public final byte[] getPanoramicCommand() {
         return UiMgr.panoramicCommand;
      }
   }
}
