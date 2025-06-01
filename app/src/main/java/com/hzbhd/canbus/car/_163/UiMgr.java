package com.hzbhd.canbus.car._163;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"},
   d2 = {"Lcom/hzbhd/canbus/car/_163/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mContext", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "kotlin.jvm.PlatformType", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_163/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "selectAirConditionerBtn", "", "btn", "", "sendAirConditionerCmd", "d0", "", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final AirPageUiSet mAirPageUiSet;
   private final Context mContext;
   private final ParkPageUiSet mParkPageUiSet;
   private final SettingHandleListener mSettingHandleListener;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$7_WhtSWn_mmKK9JplVAGEZeV_Rg(int var0) {
      _init_$lambda_7(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$KEwfHBCJKy6ZVhFZL_51FxtqHgk(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$bscx_7a7etk1kGhSiWZS8IC5j3Y(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$eoT_KSLfqKDYQqjqFUCBZeoAsS8(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$r_IOBWqoPeXaxrN_DSagt7yu1kc(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_4(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var3;
      AirPageUiSet var4 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getAirUiSet(context)");
      this.mAirPageUiSet = var4;
      ParkPageUiSet var2 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var2;
      this.mContext = var1;
      SettingHandleListener var5 = new SettingHandleListener(this);
      this.mSettingHandleListener = var5;
      var3.setOnSettingItemSelectListener((OnSettingItemSelectListener)var5);
      var3.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var5);
      var3.setOnSettingItemSeekbarSelectListener((OnSettingItemSeekbarSelectListener)var5);
      var3.setOnSettingConfirmDialogListener((OnConfirmDialogListener)var5);
      FrontArea var6 = var4.getFrontArea();
      var6.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var6), new UiMgr$$ExternalSyntheticLambda1(this, var6), new UiMgr$$ExternalSyntheticLambda2(this, var6), new UiMgr$$ExternalSyntheticLambda3(this, var6)});
      var6.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
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
      var6.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
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
      var6.setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirConditionerCmd(21);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirConditionerCmd(22);
         }
      }));
      var2.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda4());
   }

   private static final void _init_$lambda_7(int var0) {
      byte var1;
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 != 3) {
                  return;
               }

               var1 = 8;
            } else {
               var1 = 7;
            }
         } else {
            var1 = 6;
         }
      } else {
         var1 = 5;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -14, 16, (byte)var1});
   }

   private static final void lambda_6$lambda_5$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[0][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_6$lambda_5$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[1][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_6$lambda_5$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[2][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private static final void lambda_6$lambda_5$lambda_4(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "this.lineBtnAction[3][it]");
      var0.selectAirConditionerBtn(var3);
   }

   private final void selectAirConditionerBtn(String var1) {
      switch (var1) {
         case "front_defog":
            this.sendAirConditionerCmd(5);
            break;
         case "rear_defog":
            this.sendAirConditionerCmd(6);
            break;
         case "auto_cycle":
            this.sendAirConditionerCmd(59);
            break;
         case "ac":
            this.sendAirConditionerCmd(2);
            break;
         case "auto":
            this.sendAirConditionerCmd(4);
            break;
         case "fast":
            this.sendAirConditionerCmd(61);
            break;
         case "rear":
            this.sendAirConditionerCmd(46);
            break;
         case "soft":
            this.sendAirConditionerCmd(60);
            break;
         case "sync":
            this.sendAirConditionerCmd(3);
            break;
         case "power":
            this.sendAirConditionerCmd(1);
            break;
         case "in_out_cycle":
            this.sendAirConditionerCmd(7);
      }

   }

   private final void sendAirConditionerCmd(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   @Metadata(
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0016J \u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0016J)\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u0016R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"},
      d2 = {"Lcom/hzbhd/canbus/car/_163/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnConfirmDialogListener;", "(Lcom/hzbhd/canbus/car/_163/UiMgr;)V", "language", "", "", "", "getLanguage", "()Ljava/util/Map;", "onClickItem", "", "leftPos", "rightPos", "progressORvalue", "onConfirmClick", "onSwitch", "value", "selectSettingsBtn", "param", "(IILjava/lang/Integer;)V", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener, OnConfirmDialogListener {
      private final Map language;
      final UiMgr this$0;

      public SettingHandleListener(UiMgr var1) {
         this.this$0 = var1;
         this.language = MapsKt.mapOf(new Pair[]{TuplesKt.to("English", 1), TuplesKt.to("简体中文", 2), TuplesKt.to("Deutsch", 3), TuplesKt.to("Italiano", 4), TuplesKt.to("Francais", 5), TuplesKt.to("Svenska", 6), TuplesKt.to("Espanol", 7), TuplesKt.to("Nederlands", 8), TuplesKt.to("Portugues", 9), TuplesKt.to("Norsk", 11), TuplesKt.to("Suomi", 12), TuplesKt.to("Dansk", 13), TuplesKt.to("Eyynvlka", 14), TuplesKt.to("auell", 15), TuplesKt.to("Turkce", 16), TuplesKt.to("Pyccknn", 18), TuplesKt.to("Romana", 20), TuplesKt.to("Ykpaihcbka", 22), TuplesKt.to("Polski", 23), TuplesKt.to("Slovencia", 24), TuplesKt.to("Cesky", 25), TuplesKt.to("Magyar", 26), TuplesKt.to("Srpski", 28), TuplesKt.to("PortuguesdoBrasil", 29), TuplesKt.to("Hrvatski", 31), TuplesKt.to("bbnrapckn", 32), TuplesKt.to("Eesti", 33), TuplesKt.to("Vlaams", 34), TuplesKt.to("NNNy", 35), TuplesKt.to("印度语", 36), TuplesKt.to("uule", 37), TuplesKt.to("Latviesu", 38), TuplesKt.to("Lietuviu", 39), TuplesKt.to("Slovenscina", 40), TuplesKt.to("America_latina", 41)});
      }

      private final void selectSettingsBtn(int var1, int var2, Integer var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         if (var4 != null) {
            var1 = var4.hashCode();
            switch (var1) {
               case -1400265026:
                  if (var4.equals("_163_setting_10")) {
                     selectSettingsBtn$sendSettingsCmd(10, var3);
                  }
                  break;
               case -1400265025:
                  if (var4.equals("_163_setting_11")) {
                     Intrinsics.checkNotNull(var3);
                     selectSettingsBtn$sendSettingsCmd(11, var3 + 2);
                  }
                  break;
               case -1400265024:
                  if (var4.equals("_163_setting_12")) {
                     selectSettingsBtn$sendSettingsCmd(12, var3);
                  }
                  break;
               case -1400265023:
                  if (var4.equals("_163_setting_13")) {
                     selectSettingsBtn$sendSettingsCmd(13, var3);
                  }
                  break;
               case -1400265022:
                  if (var4.equals("_163_setting_14")) {
                     selectSettingsBtn$sendSettingsCmd(14, var3);
                  }
                  break;
               case -1400265021:
                  if (var4.equals("_163_setting_15")) {
                     selectSettingsBtn$sendSettingsCmd(15, var3);
                  }
                  break;
               case -1400265020:
                  if (var4.equals("_163_setting_16")) {
                     selectSettingsBtn$sendSettingsCmd(16, var3);
                  }
                  break;
               case -1400265019:
                  if (var4.equals("_163_setting_17")) {
                     selectSettingsBtn$sendSettingsCmd(17, var3);
                  }
                  break;
               case -1400265018:
                  if (var4.equals("_163_setting_18")) {
                     selectSettingsBtn$sendSettingsCmd(18, var3);
                  }
                  break;
               case -1400265017:
                  if (var4.equals("_163_setting_19")) {
                     selectSettingsBtn$sendSettingsCmd(19, var3);
                  }
                  break;
               default:
                  switch (var1) {
                     case -1400264995:
                        if (var4.equals("_163_setting_20")) {
                           selectSettingsBtn$sendSettingsCmd(20, var3);
                        }
                        break;
                     case -1400264994:
                        if (var4.equals("_163_setting_21")) {
                           selectSettingsBtn$sendSettingsCmd(21, var3);
                        }
                        break;
                     case -1400264993:
                        if (var4.equals("_163_setting_22")) {
                           selectSettingsBtn$sendSettingsCmd(22, var3);
                        }
                        break;
                     case -1400264992:
                        if (var4.equals("_163_setting_23")) {
                           selectSettingsBtn$sendSettingsCmd(23, var3);
                        }
                        break;
                     case -1400264991:
                        if (var4.equals("_163_setting_24")) {
                           selectSettingsBtn$sendSettingsCmd(24, var3);
                        }
                        break;
                     case -1400264990:
                        if (var4.equals("_163_setting_25")) {
                           selectSettingsBtn$sendSettingsCmd(25, var3);
                        }
                        break;
                     case -1400264989:
                        if (var4.equals("_163_setting_26")) {
                           selectSettingsBtn$sendSettingsCmd(26, var3);
                        }
                        break;
                     case -1400264988:
                        if (var4.equals("_163_setting_27")) {
                           selectSettingsBtn$sendSettingsCmd(27, var3);
                        }
                        break;
                     case -1400264987:
                        if (var4.equals("_163_setting_28")) {
                           selectSettingsBtn$sendSettingsCmd(28, var3);
                        }
                        break;
                     case -1400264986:
                        if (var4.equals("_163_setting_29")) {
                           selectSettingsBtn$sendSettingsCmd(29, var3);
                        }
                        break;
                     default:
                        switch (var1) {
                           case -1400264964:
                              if (var4.equals("_163_setting_30")) {
                                 selectSettingsBtn$sendSettingsCmd(30, var3);
                              }
                              break;
                           case -1324002571:
                              if (var4.equals("S163_x2A_1")) {
                                 Intrinsics.checkNotNull(var3);
                                 CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte)var3, 0});
                              }
                              break;
                           case -490097043:
                              if (var4.equals("S163_Language_0")) {
                                 MsgMgrInterface var7 = MsgMgrFactory.getCanMsgMgr(this.this$0.mContext);
                                 Intrinsics.checkNotNull(var7, "null cannot be cast to non-null type com.hzbhd.canbus.car._163.MsgMgr");
                                 MsgMgr var8 = (MsgMgr)var7;
                                 Intrinsics.checkNotNull(var3);
                                 SettingPageUiSet.ListBean.ItemListBean var5 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S163_Language_0");
                                 if (var5 != null) {
                                    var5.setValue(var5.getValueSrnArray().get(var3));
                                    Object var6 = this.language.get(var5.getValue());
                                    Intrinsics.checkNotNull(var6);
                                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)((Number)var6).intValue()});
                                 }

                                 var8.updateSettingData();
                              }
                              break;
                           case 376901766:
                              if (var4.equals("_163_drive_data5")) {
                                 CanbusMsgSender.sendMsg(new byte[]{22, 27, 2, 2, 1, -1});
                                 Toast.makeText(this.this$0.mContext, (CharSequence)"Success!", 0).show();
                              }
                              break;
                           case 748741536:
                              if (var4.equals("S18_Car_0")) {
                                 Intrinsics.checkNotNull(var3);
                                 CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)(var3 + 16), 49});
                              }
                              break;
                           default:
                              switch (var1) {
                                 case -1391072538:
                                    if (var4.equals("S163_CarInfo2_1")) {
                                       selectSettingsBtn$sendSettingsCmd(53, var3);
                                    }
                                    break;
                                 case -1391072537:
                                    if (var4.equals("S163_CarInfo2_2")) {
                                       selectSettingsBtn$sendSettingsCmd(54, var3);
                                    }
                                    break;
                                 case -1391072536:
                                    if (var4.equals("S163_CarInfo2_3")) {
                                       selectSettingsBtn$sendSettingsCmd(55, var3);
                                    }
                                    break;
                                 case -1391072535:
                                    if (var4.equals("S163_CarInfo2_4")) {
                                       selectSettingsBtn$sendSettingsCmd(56, var3);
                                    }
                                    break;
                                 case -1391072534:
                                    if (var4.equals("S163_CarInfo2_5")) {
                                       selectSettingsBtn$sendSettingsCmd(57, var3);
                                    }
                                    break;
                                 case -1391072533:
                                    if (var4.equals("S163_CarInfo2_6")) {
                                       selectSettingsBtn$sendSettingsCmd(58, var3);
                                    }
                                    break;
                                 case -1391072532:
                                    if (var4.equals("S163_CarInfo2_7")) {
                                       selectSettingsBtn$sendSettingsCmd(59, var3);
                                    }
                                    break;
                                 case -1391072531:
                                    if (var4.equals("S163_CarInfo2_8")) {
                                       selectSettingsBtn$sendSettingsCmd(60, var3);
                                    }
                                    break;
                                 case -1391072530:
                                    if (var4.equals("S163_CarInfo2_9")) {
                                       Intrinsics.checkNotNull(var3);
                                       selectSettingsBtn$sendSettingsCmd(60, var3 * 5);
                                    }
                                    break;
                                 default:
                                    switch (var1) {
                                       case -1323878602:
                                          if (var4.equals("S163_x6F_1")) {
                                             selectSettingsBtn$sendSettingsCmd(25, 1);
                                          }
                                          break;
                                       case -1323878601:
                                          if (var4.equals("S163_x6F_2")) {
                                             selectSettingsBtn$sendSettingsCmd(31, 1);
                                          }
                                          break;
                                       case -1323878600:
                                          if (var4.equals("S163_x6F_3")) {
                                             selectSettingsBtn$sendSettingsCmd(48, 1);
                                          }
                                          break;
                                       case -1323878599:
                                          if (var4.equals("S163_x6F_4")) {
                                             selectSettingsBtn$sendSettingsCmd(49, 1);
                                          }
                                          break;
                                       case -1323878598:
                                          if (var4.equals("S163_x6F_5")) {
                                             selectSettingsBtn$sendSettingsCmd(50, 1);
                                          }
                                          break;
                                       case -1323878597:
                                          if (var4.equals("S163_x6F_6")) {
                                             selectSettingsBtn$sendSettingsCmd(51, 1);
                                          }
                                          break;
                                       case -1323878596:
                                          if (var4.equals("S163_x6F_7")) {
                                             selectSettingsBtn$sendSettingsCmd(52, 1);
                                          }
                                          break;
                                       default:
                                          switch (var1) {
                                             case -1015001166:
                                                if (var4.equals("_163_setting_1")) {
                                                   selectSettingsBtn$sendSettingsCmd(1, var3);
                                                }
                                                break;
                                             case -1015001165:
                                                if (var4.equals("_163_setting_2")) {
                                                   selectSettingsBtn$sendSettingsCmd(2, var3);
                                                }
                                                break;
                                             case -1015001164:
                                                if (var4.equals("_163_setting_3")) {
                                                   selectSettingsBtn$sendSettingsCmd(3, var3);
                                                }
                                                break;
                                             case -1015001163:
                                                if (var4.equals("_163_setting_4")) {
                                                   selectSettingsBtn$sendSettingsCmd(4, var3);
                                                }
                                                break;
                                             case -1015001162:
                                                if (var4.equals("_163_setting_5")) {
                                                   selectSettingsBtn$sendSettingsCmd(5, var3);
                                                }
                                                break;
                                             case -1015001161:
                                                if (var4.equals("_163_setting_6")) {
                                                   selectSettingsBtn$sendSettingsCmd(6, var3);
                                                }
                                                break;
                                             case -1015001160:
                                                if (var4.equals("_163_setting_7")) {
                                                   selectSettingsBtn$sendSettingsCmd(7, var3);
                                                }
                                                break;
                                             case -1015001159:
                                                if (var4.equals("_163_setting_8")) {
                                                   selectSettingsBtn$sendSettingsCmd(8, var3);
                                                }
                                                break;
                                             case -1015001158:
                                                if (var4.equals("_163_setting_9")) {
                                                   selectSettingsBtn$sendSettingsCmd(9, var3);
                                                }
                                                break;
                                             default:
                                                switch (var1) {
                                                   case -173575668:
                                                      if (var4.equals("S163_CarInfo2_12")) {
                                                         selectSettingsBtn$sendSettingsCmd(61, var3);
                                                      }
                                                      break;
                                                   case -173575667:
                                                      if (var4.equals("S163_CarInfo2_13")) {
                                                         selectSettingsBtn$sendSettingsCmd(62, var3);
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

      // $FF: synthetic method
      static void selectSettingsBtn$default(SettingHandleListener var0, int var1, int var2, Integer var3, int var4, Object var5) {
         if ((var4 & 4) != 0) {
            var3 = null;
         }

         var0.selectSettingsBtn(var1, var2, var3);
      }

      private static final void selectSettingsBtn$sendSettingsCmd(int var0, Integer var1) {
         byte var2 = (byte)var0;
         if (var1 != null) {
            CanbusMsgSender.sendMsg(new byte[]{22, 111, var2, (byte)var1, -1, -1});
         }

      }

      public final Map getLanguage() {
         return this.language;
      }

      public void onClickItem(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }

      public void onConfirmClick(int var1, int var2) {
         selectSettingsBtn$default(this, var1, var2, (Integer)null, 4, (Object)null);
      }

      public void onSwitch(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }
   }
}
