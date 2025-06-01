package com.hzbhd.canbus.car._161;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SetTimeView;
import com.hzbhd.canbus.util.SharePreUtil;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(
   d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000 ,2\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\fH\u0002J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0006H\u0002J\u001d\u0010\u0017\u001a\u00020\u00062\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0019H\u0002¢\u0006\u0002\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0016\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006J\u0018\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0002J\u0010\u0010'\u001a\u00020#2\u0006\u0010*\u001a\u00020!H\u0002J\u0010\u0010+\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0006H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"},
   d2 = {"Lcom/hzbhd/canbus/car/_161/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "Data0", "", "getData0", "()I", "setData0", "(I)V", "m0x82Command", "", "mDifferent", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "mMsgMgr", "Lcom/hzbhd/canbus/car/_161/MsgMgr;", "get0x88Command", "get0x89Command", "item", "getDecimalFrom8Bit", "bit", "", "([Ljava/lang/Integer;)I", "getMsgMgr", "getOpenOrClose", "boolean", "", "getSettingLeftIndexes", "titleSrn", "", "removeSettingItemWithDifferent", "", "resolvedata", "a", "value", "sendAirCommand", "command", "param", "title", "sendAirCommandUpDown", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private static final int AIR_COMMAND_DATA_TYPE = 138;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_1161_UiMgr";
   private static final String _161_AMPLIFIER_DATA = "_161_amplifier_data";
   public static final int _161_AMPLIFIER_DATA_MID = 9;
   public static final String _161_LCD_MODE_LEFT = "_161_lcd_mode_left";
   public static final String _161_LCD_MODE_RIGHT = "_161_lcd_mode_right";
   private int Data0;
   private final byte[] m0x82Command;
   private final int mDifferent;
   private final Handler mHandler;
   private MsgMgr mMsgMgr;

   // $FF: synthetic method
   public static String $r8$lambda$_ZaXojKYb1gy8X3CnuF3iGs0r0Q(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      return lambda_59$lambda_54(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$1BQP_HfIXSXVn8fkIZtDg4vm58A(int var0) {
      lambda_59$lambda_50$lambda_42(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$AJTEyoAG_AX7EpXQWwqo4DidBgQ(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4) {
      lambda_59$lambda_57(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$DZs3e9xrdHJLOfVodkLy1C00CWY(ParkPageUiSet var0, int var1) {
      lambda_62$lambda_60(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$DhKKyGVCiJZ9tnB5Zeb0CpAiF2s(SettingPageUiSet var0, UiMgr var1, int var2, int var3) {
      lambda_59$lambda_53(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ElnzIAyFBLWuzErceFz48U8FQ8c(int var0) {
      lambda_59$lambda_58(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$G2_rwfbQ3dc94Ep9jU1FTLbjRVQ(int var0) {
      lambda_59$lambda_50$lambda_32(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Ln_NVU7k7ag1q0tD49p19Q621xI(int var0) {
      lambda_59$lambda_50$lambda_30(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$NJ_qvRvAL2CBBe2_Xf5Ro9xZ62E(int var0) {
      lambda_59$lambda_50$lambda_46(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Pupmqg__JwXDb88vQs5lorIyPQ0(int var0) {
      lambda_59$lambda_50$lambda_26(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$RHcSf6HrFFz16h6MLUI_X8UltHI(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_2(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$RfB1ymLYRqAkWadPFXc66ynLO8o(UiMgr var0, int var1, int var2, int var3, int var4, int var5) {
      lambda_59$lambda_57$lambda_56(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static void $r8$lambda$U6idS9Q46ijJgq9LhF4Ms_EniQc(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_1(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$VbOpZN5LlsrE6T7GbIgKBoiyfKI(int var0, int var1, int var2, int var3, int var4) {
      lambda_59$lambda_57$lambda_55(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$XwvFhW29j_Wmzaw7PzrFkvW_1J8(int var0) {
      lambda_59$lambda_50$lambda_28(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$YWK7Vw2RmQlsyDdH1KWizgkXxMk(int var0) {
      lambda_59$lambda_50$lambda_40(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ZbA6_qd5o1K_nLVanx13eWh8Dlc(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$_GW_kW6qYJciONUCtg_MDeIO_z0(int var0) {
      lambda_6$lambda_5$lambda_4(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$hqEO8AcoonDA2YBPP03zBTO_Kvk(int var0) {
      lambda_59$lambda_50$lambda_44(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$irPwiQ7zKudIn2JLRmSC1BQzQAw(int var0) {
      lambda_65$lambda_64(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$m6j_pbwQLBAkPr3wXAHdspHLQC4(int var0) {
      lambda_59$lambda_50$lambda_36(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$o5N6bUD0SRKKxXuMrLBg2J_eJIU(SettingPageUiSet var0, UiMgr var1, byte[] var2, Context var3, Charset var4, int var5, int var6, int var7) {
      lambda_59$lambda_19(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   public static void $r8$lambda$pLGy0PtLeGlaaFy7zkaEjLAolWc(UiMgr var0, FrontArea var1, int var2) {
      lambda_6$lambda_5$lambda_3(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$pRR1QwLgKXwwdaSHaIDfhQRZtqo(SettingPageUiSet var0, UiMgr var1, Context var2, byte[] var3, Charset var4, int var5, int var6, int var7) {
      lambda_59$lambda_12(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   public static void $r8$lambda$qNQfUqtUvO2tGL5TdTl26OUpFWA(int var0) {
      lambda_59$lambda_50$lambda_34(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$rcRIW_L1IPfJ1TAAnxjTM9RfnoY(int var0) {
      lambda_59$lambda_50$lambda_48(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ud_imU9XcyjK11i4WGfXz0Fw5XY(int var0) {
      lambda_59$lambda_50$lambda_38(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$vX0VYvaNeAyE_2DXNPjoJ3i1e2c() {
      lambda_62$lambda_61();
   }

   // $FF: synthetic method
   public static void $r8$lambda$vov8XV889DpfX6I_SDL_ouk_S88(byte[] var0) {
      sendAirCommandUpDown$lambda_67$lambda_66(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$xu41pR75UmZbByrLAYunYudWQ3I(SettingPageUiSet var0, UiMgr var1, byte[] var2, Context var3, Charset var4, int var5, int var6, int var7) {
      lambda_59$lambda_50(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public UiMgr(Context var1) {
      Object var5;
      Charset var6;
      byte[] var12;
      label33: {
         Intrinsics.checkNotNullParameter(var1, "context");
         super();
         this.mHandler = new Handler(Looper.getMainLooper());
         MsgMgrInterface var3 = MsgMgrFactory.getCanMsgMgr(var1);
         Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.MsgMgr");
         this.mMsgMgr = (MsgMgr)var3;
         this.m0x82Command = new byte[]{22, -126, 0, 0, 0};
         this.mDifferent = this.getCurrentCarId();
         FrontArea var4 = this.getAirUiSet(var1).getFrontArea();
         var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var4), new UiMgr$$ExternalSyntheticLambda26(this, var4), new UiMgr$$ExternalSyntheticLambda27(this, var4), new UiMgr$$ExternalSyntheticLambda28(this, var4)});
         OnAirTemperatureUpDownClickListener var9 = (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.sendAirCommand(4, 2);
            }

            public void onClickUp() {
               this.this$0.sendAirCommand(4, 1);
            }
         });
         var5 = null;
         var4.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{var9, null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.sendAirCommand(5, 2);
            }

            public void onClickUp() {
               this.this$0.sendAirCommand(5, 1);
            }
         })});
         var4.setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickLeft() {
               this.this$0.sendAirCommand(10, 2);
            }

            public void onClickRight() {
               this.this$0.sendAirCommand(10, 1);
            }
         }));
         var4.setOnAirPageStatusListener(new UiMgr$$ExternalSyntheticLambda29());
         new SetTimeView();
         var6 = Charset.forName("UTF-16");
         String var10 = SharePreUtil.getStringValue(var1, "_161_amplifier_data", (String)null);
         if (var10 != null) {
            Intrinsics.checkNotNullExpressionValue(var6, "charset");
            byte[] var11 = var10.getBytes(var6);
            Intrinsics.checkNotNullExpressionValue(var11, "this as java.lang.String).getBytes(charset)");
            var11 = ArraysKt.copyOfRange(var11, 2, var11.length);
            var12 = var11;
            if (var11 != null) {
               break label33;
            }
         }

         var12 = new byte[]{22, -59, 9, 9, 9, 9, 9, 0, 0, 2};
      }

      AmplifierPageUiSet var13 = this.getAmplifierPageUiSet(var1);
      var13.setOnAmplifierPositionListener((OnAmplifierPositionListener)(new OnAmplifierPositionListener(var12, var1, var6, this) {
         final byte[] $amplifierCommand;
         final Charset $charset;
         final Context $context;
         final UiMgr this$0;

         {
            this.$amplifierCommand = var1;
            this.$context = var2;
            this.$charset = var3;
            this.this$0 = var4;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierPosition");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  return;
               }

               UiMgr._init_$sendAmplifierCommand(this.$amplifierCommand, this.$context, this.$charset, this.this$0, (Function1)(new Function1(var2) {
                  final int $value;

                  {
                     this.$value = var1;
                  }

                  public final void invoke(byte[] var1) {
                     Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                     var1[3] = (byte)(this.$value + 9);
                  }
               }));
            } else {
               UiMgr._init_$sendAmplifierCommand(this.$amplifierCommand, this.$context, this.$charset, this.this$0, (Function1)(new Function1(var2) {
                  final int $value;

                  {
                     this.$value = var1;
                  }

                  public final void invoke(byte[] var1) {
                     Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                     var1[2] = (byte)(this.$value + 9);
                  }
               }));
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
      var13.setOnAmplifierSeekBarListener((OnAmplifierSeekBarListener)(new OnAmplifierSeekBarListener(var12, var1, var6, this) {
         final byte[] $amplifierCommand;
         final Charset $charset;
         final Context $context;
         final UiMgr this$0;

         {
            this.$amplifierCommand = var1;
            this.$context = var2;
            this.$charset = var3;
            this.this$0 = var4;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "amplifierBand");
            int var3 = null.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     return;
                  }

                  UiMgr._init_$sendAmplifierCommand(this.$amplifierCommand, this.$context, this.$charset, this.this$0, (Function1)(new Function1(var2) {
                     final int $progress;

                     {
                        this.$progress = var1;
                     }

                     public final void invoke(byte[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                        var1[6] = (byte)this.$progress;
                     }
                  }));
               } else {
                  UiMgr._init_$sendAmplifierCommand(this.$amplifierCommand, this.$context, this.$charset, this.this$0, (Function1)(new Function1(var2) {
                     final int $progress;

                     {
                        this.$progress = var1;
                     }

                     public final void invoke(byte[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                        var1[5] = (byte)this.$progress;
                     }
                  }));
               }
            } else {
               UiMgr._init_$sendAmplifierCommand(this.$amplifierCommand, this.$context, this.$charset, this.this$0, (Function1)(new Function1(var2) {
                  final int $progress;

                  {
                     this.$progress = var1;
                  }

                  public final void invoke(byte[] var1) {
                     Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                     var1[4] = (byte)this.$progress;
                  }
               }));
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
               $EnumSwitchMapping$0 = var0;
            }
         }
      }));
      SettingPageUiSet var7 = this.getSettingUiSet(var1);
      SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var7.getList().get(0)).getItemList().get(27);
      int var2 = this.mMsgMgr.getMDifferent();
      List var14;
      if (var2 != 4) {
         if (var2 != 24) {
            if (var2 != 18) {
               if (var2 != 19) {
                  var14 = (List)CollectionsKt.arrayListOf(new String[]{"_161_sapphire_blue", "_161_agate_red", "_161_jade_green", "_161_crimson", "_161_sky_blue", "_161_bright_brown", "_161_deerskin_brown"});
               } else {
                  var14 = (List)CollectionsKt.arrayListOf(new String[]{"_161_sapphire_blue", "_161_agate_red", "_161_jade_green"});
               }
            } else {
               var14 = (List)CollectionsKt.arrayListOf(new String[]{"_161_sapphire_blue", "_161_deerskin_brown"});
            }
         } else {
            var14 = (List)CollectionsKt.arrayListOf(new String[]{"_161_sapphire_blue", "_161_deerskin_brown"});
         }
      } else {
         var14 = (List)CollectionsKt.arrayListOf(new String[]{"_161_crimson", "_161_sky_blue", "_161_bright_brown"});
      }

      var8.setValueSrnArray(var14);
      this.removeSettingItemWithDifferent(var1);
      var7.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda1(var7, this, var1, var12, var6));
      var7.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda2(var7, this, var12, var1, var6));
      var7.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda3(var7, this, var12, var1, var6));
      var7.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda4(var7, this));
      var7.setOnSettingItemSeekbarSetTextListener(new UiMgr$$ExternalSyntheticLambda5(var7, var1));
      var7.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda11(var7, this, var1));
      var7.setOnSettingPageStatusListener(new UiMgr$$ExternalSyntheticLambda22());
      ParkPageUiSet var15 = this.getParkPageUiSet(var1);
      var2 = this.mMsgMgr.getMDifferent();
      if (var2 != 16) {
         if (var2 != 17) {
            var15.setShowPanoramic(false);
            this.removeMainPrjBtnByName(var1, "panel_key");
            var14 = (List)null;
            var14 = (List)var5;
         } else {
            var14 = (List)CollectionsKt.arrayListOf(new ParkPageUiSet.Bean[]{new ParkPageUiSet.Bean(0, "_161_standard_view", ""), new ParkPageUiSet.Bean(0, "_161_automatic_view", ""), new ParkPageUiSet.Bean(0, "_161_enlarged_view", ""), new ParkPageUiSet.Bean(0, "_161_180_view", ""), new ParkPageUiSet.Bean(0, "_250_exit", "")});
         }
      } else {
         var14 = (List)CollectionsKt.arrayListOf(new ParkPageUiSet.Bean[]{new ParkPageUiSet.Bean(0, "_189_front_view", ""), new ParkPageUiSet.Bean(0, "_161_360_view", ""), new ParkPageUiSet.Bean(0, "_189_rear_view", ""), new ParkPageUiSet.Bean(0, "_250_exit", "")});
      }

      var15.setPanoramicBtnList(var14);
      var15.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda23(var15));
      var15.setOnBackCameraStatusListener(new UiMgr$$ExternalSyntheticLambda24());
      PanelKeyPageUiSet var16 = this.getPanelKeyPageUiSet(var1);
      var16.setOnPanelKeyPositionListener((OnPanelKeyPositionListener)(new OnPanelKeyPositionListener(var16) {
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

            if (Intrinsics.areEqual((Object)var3, (Object)"_161_panoramic")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 1});
            }

         }
      }));
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new UiMgr$$ExternalSyntheticLambda25());
   }

   private static final void _init_$sendAmplifierCommand(byte[] var0, Context var1, Charset var2, UiMgr var3, Function1 var4) {
      var4.invoke(var0);
      CanbusMsgSender.sendMsg(var0);
      Intrinsics.checkNotNullExpressionValue(var2, "charset");
      SharePreUtil.setStringValue(var1, "_161_amplifier_data", new String(var0, var2));
      var3.mMsgMgr.canbusInfoChange(var1, var0);
   }

   private final byte[] get0x88Command() {
      byte[] var1 = (byte[])this.mMsgMgr.getM0x3BDatas().clone();
      var1[0] = 22;
      var1[1] = -120;
      var1[2] = (byte)(var1[2] >> 1 & 127);
      return var1;
   }

   private final byte[] get0x89Command(int var1) {
      byte[] var2 = new byte[]{22, -119, 0, 0, 0, 0, 0, 0, 0};
      var2[2] = (byte)DataHandleUtils.setIntFromByteWithBit(16, var1, 6, 2);
      System.arraycopy(this.mMsgMgr.getM0x3DDatas(), (var1 - 1) * 6 + 2, var2, 3, 6);
      Log.i("_1161_UiMgr", "getTest: " + DataHandleUtils.bytes2HexString((byte[])var2, 9));
      return var2;
   }

   private final int getDecimalFrom8Bit(Integer[] var1) {
      int var2 = 7;

      String var3;
      for(var3 = ""; var2 >= 0; --var2) {
         var3 = var3 + var1[var2] + "";
      }

      return Integer.parseInt(var3, CharsKt.checkRadix(2));
   }

   private final MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.MsgMgr");
         this.mMsgMgr = (MsgMgr)var2;
      }

      return this.mMsgMgr;
   }

   private final int getOpenOrClose(boolean var1) {
      return var1 ^ 1;
   }

   private static final void lambda_59$lambda_12(SettingPageUiSet var0, UiMgr var1, Context var2, byte[] var3, Charset var4, int var5, int var6, int var7) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$context");
      Intrinsics.checkNotNullParameter(var3, "$amplifierCommand");
      String var9 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var5)).getItemList().get(var6)).getTitleSrn();
      if (var9 != null) {
         int var8 = var9.hashCode();
         byte var13 = 1;
         var5 = 2;
         byte[] var11;
         switch (var8) {
            case -2099991896:
               if (var9.equals("_161_emergency_braking")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 24, (byte)var7});
               }
               break;
            case -1466184070:
               if (var9.equals("_143_0x76_d0_b45")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 8, (byte)var7});
               }
               break;
            case -1465260677:
               if (var9.equals("_143_0x76_d1_b01")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 7, (byte)var7});
               }
               break;
            case -1449161616:
               if (var9.equals("_161_Power_Reserve")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, 1, (byte)var7});
               }
               break;
            case -1380258404:
               if (var9.equals("_220_language_settings")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 11, (byte)var7});
               }
               break;
            case -1144939710:
               if (var9.equals("outlander_simple_car_set_17")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 9, (byte)(var7 + 1)});
               }
               break;
            case -581860367:
               if (var9.equals("geely_theme_colors")) {
                  var6 = var1.mMsgMgr.getMDifferent();
                  if (var6 != 4) {
                     if (var6 != 24 && var6 != 18) {
                        if (var6 != 19) {
                           return;
                        }

                        if (var7 != 0) {
                           if (var7 != 1) {
                              if (var7 != 2) {
                                 return;
                              }

                              var5 = 3;
                           }
                        } else {
                           var5 = 0;
                        }
                     } else {
                        var5 = var7;
                     }
                  } else if (var7 != 0) {
                     if (var7 != 1) {
                        if (var7 != 2) {
                           return;
                        }

                        var5 = 8;
                     } else {
                        var5 = 7;
                     }
                  } else {
                     var5 = 6;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 23, (byte)var5});
               }
               break;
            case -312967110:
               if (var9.equals("_161_driver_massage_intensity")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)var7});
               }
               break;
            case -26602129:
               if (var9.equals("temperature_unit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 20, (byte)var7});
               }
               break;
            case 25902792:
               if (var9.equals("_161_passenger_massage_intensity")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)var7});
               }
               break;
            case 56040248:
               if (var9.equals("_161_door_opening_options")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 15, (byte)var7});
               }
               break;
            case 194708627:
               if (var9.equals("_29_right_side")) {
                  SharePreUtil.setIntValue(var2, "_161_lcd_mode_right", var7);
                  CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)SharePreUtil.getIntValue(var2, "_161_lcd_mode_left", 0), (byte)var7});
                  var1.mMsgMgr.updateSettingActivity("_29_right_side", var7);
               }
               break;
            case 305842844:
               if (var9.equals("_161_driveInfo_0_3_0")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var7});
               }
               break;
            case 635184807:
               if (var9.equals("_161_current_page")) {
                  var11 = var1.m0x82Command;
                  var11[2] = (byte)DataHandleUtils.setIntFromByteWithBit(var11[2], var7, 0, 2);
                  CanbusMsgSender.sendMsg(var11);
                  var1.mMsgMgr.updateSettingActivity("_161_current_page", var7);
               }
               break;
            case 700453485:
               if (var9.equals("fuel_unit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 10, (byte)var7});
               }
               break;
            case 961699958:
               if (var9.equals("_29_left_side")) {
                  SharePreUtil.setIntValue(var2, "_161_lcd_mode_left", var7);
                  CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)var7, (byte)SharePreUtil.getIntValue(var2, "_161_lcd_mode_right", 0)});
                  var1.mMsgMgr.updateSettingActivity("_29_left_side", var7);
               }
               break;
            case 993561070:
               if (var9.equals("_161_passenger_massage_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var7 + 1)});
               }
               break;
            case 1013079559:
               if (var9.equals("_334_amp_state3")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var7});
               }
               break;
            case 1051598680:
               if (var9.equals("_161_set_sche_mile")) {
                  var11 = var1.m0x82Command;
                  var11[2] = (byte)DataHandleUtils.setOneBit(var11[2], 7, var7);
                  CanbusMsgSender.sendMsg(var11);
                  var1.mMsgMgr.updateSettingActivity("_161_set_sche_mile", var7);
               }
               break;
            case 1054135456:
               if (var9.equals("_161_start_stop_status")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 13, (byte)var7});
                  var1.mMsgMgr.updateSettingActivity("_161_start_stop_status", var7);
                  System.exit(0);
               }
               break;
            case 1285394221:
               if (var9.equals("_143_0xAD_setting7")) {
                  _init_$sendAmplifierCommand(var3, var2, var4, var1, (Function1)(new Function1(var7) {
                     final int $selectPos;

                     {
                        this.$selectPos = var1;
                     }

                     public final void invoke(byte[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                        var1[7] = (byte)DataHandleUtils.setIntFromByteWithBit(var1[7], this.$selectPos, 0, 4);
                     }
                  }));
               }
               break;
            case 1338026300:
               if (var9.equals("_161_driver_massage_mode")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)(var7 + 1)});
               }
               break;
            case 1456389983:
               if (var9.equals("_279_vehicle_config")) {
                  SharePreUtil.setIntValue(var2, "_161_vehicle_config", var7);
                  var8 = var1.mDifferent;
                  byte var12 = var13;
                  if (var8 != 13) {
                     if (var8 != 32) {
                        if (var8 != 33) {
                           var12 = var13;
                           label151:
                           switch (var8) {
                              case 16:
                                 var12 = 0;
                              case 17:
                                 break;
                              case 18:
                                 var12 = 2;
                                 break;
                              default:
                                 var12 = var13;
                                 switch (var8) {
                                    case 22:
                                    case 23:
                                       break label151;
                                    case 24:
                                       break;
                                    case 25:
                                       var12 = 4;
                                       break label151;
                                    default:
                                       var12 = 3;
                                       break label151;
                                 }
                              case 15:
                                 var12 = 8;
                           }
                        } else {
                           var12 = 5;
                        }
                     } else {
                        var12 = 80;
                     }
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte)var12, (byte)(var7 + 1), 0});
                  MsgMgr var10 = var1.getMsgMgr(var2);
                  if (var10 != null) {
                     var10.updateSettingActivity("_279_vehicle_config", var7);
                  }
               }
               break;
            case 1479314213:
               if (var9.equals("_161_Time_Interval")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var7});
               }
         }
      }

   }

   private static final void lambda_59$lambda_19(SettingPageUiSet var0, UiMgr var1, byte[] var2, Context var3, Charset var4, int var5, int var6, int var7) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$amplifierCommand");
      Intrinsics.checkNotNullParameter(var3, "$context");
      String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var5)).getItemList().get(var6)).getTitleSrn();
      if (var8 != null) {
         byte[] var9;
         switch (var8.hashCode()) {
            case -2130367498:
               if (var8.equals("_94_blind_spot_monitoring")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 12, (byte)var7});
               }
               break;
            case -2065696610:
               if (var8.equals("_118_setting_title_13")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 18, (byte)var7});
               }
               break;
            case -2065696606:
               if (var8.equals("_118_setting_title_17")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 33, (byte)var7});
               }
               break;
            case -1970344807:
               if (var8.equals("_161_speed_3_enable")) {
                  var9 = var1.get0x88Command();
                  var9[2] = (byte)DataHandleUtils.setOneBit(var9[2], 3, var7);
                  CanbusMsgSender.sendMsg(var9);
               }
               break;
            case -1931498484:
               if (var8.equals("_161_Thursday")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(3, var7)});
               }
               break;
            case -1635857494:
               if (var8.equals("_161_start_stop_disabled")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 13, (byte)var7});
               }
               break;
            case -1322788373:
               if (var8.equals("_161_Saturday")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(1, var7)});
               }
               break;
            case -1273166600:
               if (var8.equals("_143_setting_5")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 14, (byte)var7});
               }
               break;
            case -1273166598:
               if (var8.equals("_143_setting_7")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 21, (byte)var7});
               }
               break;
            case -1273166597:
               if (var8.equals("_143_setting_8")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 28, (byte)var7});
               }
               break;
            case -1160998181:
               if (var8.equals("_161_speed_1_enable")) {
                  var9 = var1.get0x88Command();
                  var9[2] = (byte)DataHandleUtils.setOneBit(var9[2], 5, var7);
                  CanbusMsgSender.sendMsg(var9);
               }
               break;
            case -835843419:
               if (var8.equals("_161_decoder_voice")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 35, (byte)var7});
               }
               break;
            case -823723268:
               var8.equals("_161_door_auto_lock");
               break;
            case -813458981:
               if (var8.equals("_143_setting_20")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 26, (byte)var7});
               }
               break;
            case -813458980:
               if (var8.equals("_143_setting_21")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 27, (byte)var7});
               }
               break;
            case -585729983:
               if (var8.equals("parkingAssist")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 1, (byte)var7});
               }
               break;
            case -578117088:
               if (var8.equals("_283_car_setting_light_4")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 3, (byte)var7});
               }
               break;
            case -227534472:
               if (var8.equals("_161_speed_4_enable")) {
                  var9 = var1.get0x88Command();
                  var9[2] = (byte)DataHandleUtils.setOneBit(var9[2], 2, var7);
                  CanbusMsgSender.sendMsg(var9);
               }
               break;
            case -120530469:
               if (var8.equals("_161_Tuesday")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(5, var7)});
               }
               break;
            case -43540035:
               if (var8.equals("_284_setting_item_2B")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 31, (byte)var7});
               }
               break;
            case 66177732:
               if (var8.equals("_161_light_assist")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 34, (byte)var7});
               }
               break;
            case 157539172:
               if (var8.equals("_161_Wednesday")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(4, var7)});
               }
               break;
            case 581812154:
               if (var8.equals("_161_speed_2_enable")) {
                  var9 = var1.get0x88Command();
                  var9[2] = (byte)DataHandleUtils.setOneBit(var9[2], 4, var7);
                  CanbusMsgSender.sendMsg(var9);
               }
               break;
            case 851049255:
               if (var8.equals("_161_anti_collision")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 32, (byte)var7});
               }
               break;
            case 1177900689:
               if (var8.equals("_161_disable_rear_mirror")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 25, (byte)var7});
               }
               break;
            case 1229285653:
               if (var8.equals("_81_traction_control_system")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 22, (byte)var7});
               }
               break;
            case 1255205905:
               if (var8.equals("_161_Friday")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(2, var7)});
               }
               break;
            case 1285394219:
               if (var8.equals("_143_0xAD_setting5")) {
                  _init_$sendAmplifierCommand(var2, var3, var4, var1, (Function1)(new Function1(var7) {
                     final int $value;

                     {
                        this.$value = var1;
                     }

                     public final void invoke(byte[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                        var1[7] = (byte)DataHandleUtils.setOneBit(var1[7], 7, this.$value);
                     }
                  }));
               }
               break;
            case 1285394220:
               if (var8.equals("_143_0xAD_setting6")) {
                  _init_$sendAmplifierCommand(var2, var3, var4, var1, (Function1)(new Function1(var7) {
                     final int $value;

                     {
                        this.$value = var1;
                     }

                     public final void invoke(byte[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                        var1[7] = (byte)DataHandleUtils.setOneBit(var1[7], 6, this.$value);
                     }
                  }));
               }
               break;
            case 1452988354:
               if (var8.equals("_161_Monday")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(6, var7)});
               }
               break;
            case 1508778175:
               if (var8.equals("_161_passenger_massage_switch")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)var7});
               }
               break;
            case 1515275863:
               if (var8.equals("_161_speed_5_enable")) {
                  var9 = var1.get0x88Command();
                  var9[2] = (byte)DataHandleUtils.setOneBit(var9[2], 1, var7);
                  CanbusMsgSender.sendMsg(var9);
               }
               break;
            case 1541392785:
               if (var8.equals("_161_Preset")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(7, var7)});
               }
               break;
            case 1547973850:
               if (var8.equals("_161_only_unlock_trunk")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 19, (byte)var7});
               }
               break;
            case 1630304386:
               if (var8.equals("_161_Sunday")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1.resolvedata(0, var7)});
               }
               break;
            case 1703657495:
               if (var8.equals("_161_speed_remember")) {
                  var9 = var1.get0x88Command();
                  var9[2] = (byte)DataHandleUtils.setIntFromByteWithBit(var9[2], var7, 6, 2);
                  CanbusMsgSender.sendMsg(var9);
               }
               break;
            case 1827382413:
               if (var8.equals("_161_driver_massage_switch")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)var7});
               }
               break;
            case 1965173886:
               if (var8.equals("_220_driving_assistance")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 17, (byte)var7});
               }
               break;
            case 2007067540:
               if (var8.equals("_161_reversing_radar")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 6, (byte)var7});
               }
               break;
            case 2026482754:
               if (var8.equals("_161_rear_wiper")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 2, (byte)var7});
               }
               break;
            case 2042061586:
               if (var8.equals("_161_rearview_mirror_adaptive")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 30, (byte)var7});
               }
               break;
            case 2060120402:
               if (var8.equals("_161_buzzer")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 39, (byte)var7});
               }
               break;
            case 2105117362:
               if (var8.equals("_283_car_setting_pa_5")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 29, (byte)var7});
               }
         }
      }

   }

   private static final void lambda_59$lambda_50(SettingPageUiSet var0, UiMgr var1, byte[] var2, Context var3, Charset var4, int var5, int var6, int var7) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$amplifierCommand");
      Intrinsics.checkNotNullParameter(var3, "$context");
      String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var5)).getItemList().get(var6)).getTitleSrn();
      if (var8 != null) {
         var5 = var8.hashCode();
         byte[] var9;
         switch (var5) {
            case -860854301:
               if (var8.equals("_324_centrik_speaker")) {
                  _init_$sendAmplifierCommand(var2, var3, var4, var1, (Function1)(new Function1(var7) {
                     final int $progress;

                     {
                        this.$progress = var1;
                     }

                     public final void invoke(byte[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                        var1[8] = (byte)DataHandleUtils.setIntFromByteWithBit(var1[8], this.$progress + 3, 4, 4);
                     }
                  }));
               }
               break;
            case -374590123:
               if (var8.equals("on_off_btn_txt_7")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 5, (byte)var7});
               }
               break;
            case -208886217:
               if (var8.equals("light_info")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -115, (byte)var7});
               }
               break;
            case 747713920:
               if (var8.equals("_55_0xa6_setting_1")) {
                  _init_$sendAmplifierCommand(var2, var3, var4, var1, (Function1)(new Function1(var7) {
                     final int $progress;

                     {
                        this.$progress = var1;
                     }

                     public final void invoke(byte[] var1) {
                        Intrinsics.checkNotNullParameter(var1, "$this$sendAmplifierCommand");
                        var1[8] = (byte)DataHandleUtils.setIntFromByteWithBit(var1[8], this.$progress + 3, 0, 4);
                     }
                  }));
               }
               break;
            case 889438709:
               if (var8.equals("_161_sche_mile")) {
                  var9 = var1.m0x82Command;
                  var9[3] = (byte)(var7 >> 8 & 255);
                  var9[4] = (byte)(var7 & 255);
                  CanbusMsgSender.sendMsg(var9);
                  var1.mMsgMgr.updateSettingActivity("_161_sche_mile", var7);
               }
               break;
            default:
               switch (var5) {
                  case 216860881:
                     if (var8.equals("_161_speed_cruise_1")) {
                        var9 = var1.get0x89Command(1);
                        var9[3] = (byte)var7;
                        CanbusMsgSender.sendMsg(var9);
                        var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda7(var7), 120L);
                     }
                     break;
                  case 216860882:
                     if (var8.equals("_161_speed_cruise_2")) {
                        var9 = var1.get0x89Command(1);
                        var9[4] = (byte)var7;
                        CanbusMsgSender.sendMsg(var9);
                        var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda10(var7), 120L);
                     }
                     break;
                  case 216860883:
                     if (var8.equals("_161_speed_cruise_3")) {
                        var9 = var1.get0x89Command(1);
                        var9[5] = (byte)var7;
                        CanbusMsgSender.sendMsg(var9);
                        var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda12(var7), 120L);
                     }
                     break;
                  case 216860884:
                     if (var8.equals("_161_speed_cruise_4")) {
                        var9 = var1.get0x89Command(1);
                        var9[6] = (byte)var7;
                        CanbusMsgSender.sendMsg(var9);
                        var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda13(var7), 120L);
                     }
                     break;
                  case 216860885:
                     if (var8.equals("_161_speed_cruise_5")) {
                        var9 = var1.get0x89Command(1);
                        var9[7] = (byte)var7;
                        CanbusMsgSender.sendMsg(var9);
                        var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda14(var7), 120L);
                     }
                     break;
                  case 216860886:
                     if (var8.equals("_161_speed_cruise_6")) {
                        var9 = var1.get0x89Command(1);
                        var9[8] = (byte)var7;
                        CanbusMsgSender.sendMsg(var9);
                        var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda15(var7), 120L);
                     }
                     break;
                  default:
                     switch (var5) {
                        case 525017571:
                           if (var8.equals("_161_speed_limit_1")) {
                              var9 = var1.get0x89Command(2);
                              var9[3] = (byte)var7;
                              CanbusMsgSender.sendMsg(var9);
                              var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda16(var7), 120L);
                           }
                           break;
                        case 525017572:
                           if (var8.equals("_161_speed_limit_2")) {
                              var9 = var1.get0x89Command(2);
                              var9[4] = (byte)var7;
                              CanbusMsgSender.sendMsg(var9);
                              var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda17(var7), 120L);
                           }
                           break;
                        case 525017573:
                           if (var8.equals("_161_speed_limit_3")) {
                              var9 = var1.get0x89Command(2);
                              var9[5] = (byte)var7;
                              CanbusMsgSender.sendMsg(var9);
                              var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda18(var7), 120L);
                           }
                           break;
                        case 525017574:
                           if (var8.equals("_161_speed_limit_4")) {
                              var9 = var1.get0x89Command(2);
                              var9[6] = (byte)var7;
                              CanbusMsgSender.sendMsg(var9);
                              var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda19(var7), 120L);
                           }
                           break;
                        case 525017575:
                           if (var8.equals("_161_speed_limit_5")) {
                              var9 = var1.get0x89Command(2);
                              var9[7] = (byte)var7;
                              CanbusMsgSender.sendMsg(var9);
                              var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda8(var7), 120L);
                           }
                           break;
                        case 525017576:
                           if (var8.equals("_161_speed_limit_6")) {
                              var9 = var1.get0x89Command(2);
                              var9[8] = (byte)var7;
                              CanbusMsgSender.sendMsg(var9);
                              var1.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda9(var7), 120L);
                           }
                           break;
                        default:
                           switch (var5) {
                              case 832315913:
                                 if (var8.equals("_161_speed_remember_1")) {
                                    var9 = var1.get0x88Command();
                                    var9[3] = (byte)var7;
                                    CanbusMsgSender.sendMsg(var9);
                                 }
                                 break;
                              case 832315914:
                                 if (var8.equals("_161_speed_remember_2")) {
                                    var9 = var1.get0x88Command();
                                    var9[4] = (byte)var7;
                                    CanbusMsgSender.sendMsg(var9);
                                 }
                                 break;
                              case 832315915:
                                 if (var8.equals("_161_speed_remember_3")) {
                                    var9 = var1.get0x88Command();
                                    var9[5] = (byte)var7;
                                    CanbusMsgSender.sendMsg(var9);
                                 }
                                 break;
                              case 832315916:
                                 if (var8.equals("_161_speed_remember_4")) {
                                    var9 = var1.get0x88Command();
                                    var9[6] = (byte)var7;
                                    CanbusMsgSender.sendMsg(var9);
                                 }
                                 break;
                              case 832315917:
                                 if (var8.equals("_161_speed_remember_5")) {
                                    var9 = var1.get0x88Command();
                                    var9[7] = (byte)var7;
                                    CanbusMsgSender.sendMsg(var9);
                                 }
                           }
                     }
               }
         }
      }

   }

   private static final void lambda_59$lambda_50$lambda_26(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, 65, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_28(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, 66, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_30(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, 67, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_32(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, 68, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_34(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, 69, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_36(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, 70, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_38(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, -127, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_40(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, -126, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_42(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, -125, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_44(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, -124, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_46(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, -123, (byte)var0});
   }

   private static final void lambda_59$lambda_50$lambda_48(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, -122, (byte)var0});
   }

   private static final void lambda_59$lambda_53(SettingPageUiSet var0, UiMgr var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      if (var4 != null) {
         byte[] var5;
         switch (var4) {
            case "_143_setting_3":
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 16, 1});
               break;
            case "_161_clear_page_1":
               var5 = var1.m0x82Command;
               var5 = Arrays.copyOf(var5, var5.length);
               Intrinsics.checkNotNullExpressionValue(var5, "copyOf(this, size)");
               var5[2] = (byte)(var5[2] | 64);
               CanbusMsgSender.sendMsg(var5);
               break;
            case "_161_clear_page_2":
               var5 = var1.m0x82Command;
               var5 = Arrays.copyOf(var5, var5.length);
               Intrinsics.checkNotNullExpressionValue(var5, "copyOf(this, size)");
               var5[2] = (byte)(var5[2] | 32);
               CanbusMsgSender.sendMsg(var5);
               break;
            case "_161_speed_cruise_reset":
               CanbusMsgSender.sendMsg(new byte[]{22, -119, 96, 0, 0, 0, 0, 0, 0});
               break;
            case "_161_speed_limit_reset":
               CanbusMsgSender.sendMsg(new byte[]{22, -119, -96, 0, 0, 0, 0, 0, 0});
               break;
            case "_220_reset":
               CanbusMsgSender.sendMsg(new byte[]{22, -120, -128, 0, 0, 0, 0, 0});
         }
      }

   }

   private static final String lambda_59$lambda_54(SettingPageUiSet var0, Context var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "$context");
      String var5;
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn(), (Object)"on_off_btn_txt_7")) {
         if (var4 == 0) {
            var5 = CommUtil.getStrByResId(var1, "close");
         } else {
            var5 = String.valueOf(var4 - 1);
         }
      } else {
         var5 = String.valueOf(var4);
      }

      return var5;
   }

   private static final void lambda_59$lambda_57(SettingPageUiSet var0, UiMgr var1, Context var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      Intrinsics.checkNotNullParameter(var2, "$context");
      UiMgr$$ExternalSyntheticLambda20 var7 = new UiMgr$$ExternalSyntheticLambda20();
      UiMgr$$ExternalSyntheticLambda21 var8 = new UiMgr$$ExternalSyntheticLambda21(var1);
      String var9 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      boolean var5 = Intrinsics.areEqual((Object)var9, (Object)"_161_Delay_Charge");
      SetTimeView var6 = null;
      Activity var10 = null;
      MsgMgr var11;
      if (var5) {
         var6 = new SetTimeView();
         var11 = var1.getMsgMgr(var2);
         if (var11 != null) {
            var10 = var11.getCurrentActivity();
         }

         var6.showDialog((Context)var10, CommUtil.getStrByResId(var2, "_161_Delay_Charge"), false, false, false, true, true, var7);
      } else if (Intrinsics.areEqual((Object)var9, (Object)"_161_Time")) {
         SetTimeView var12 = new SetTimeView();
         var11 = var1.getMsgMgr(var2);
         var10 = var6;
         if (var11 != null) {
            var10 = var11.getCurrentActivity();
         }

         var12.showDialog((Context)var10, CommUtil.getStrByResId(var2, "_161_Time"), false, false, false, true, true, var8);
      }

   }

   private static final void lambda_59$lambda_57$lambda_55(int var0, int var1, int var2, int var3, int var4) {
      var0 = var3 * 60 + var4;
      CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)(var0 / 256), (byte)(var0 % 256)});
   }

   private static final void lambda_59$lambda_57$lambda_56(UiMgr var0, int var1, int var2, int var3, int var4, int var5) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var1 = var4 * 60 + var5;
      var2 = var1 / 256;
      CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var0.Data0, (byte)var2, (byte)(var1 % 256)});
   }

   private static final void lambda_59$lambda_58(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 34});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 59});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 61});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 63});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 65});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 80});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 81});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 82});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 83});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 39});
   }

   private static final void lambda_6$lambda_5$lambda_0(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[0][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[0][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_6$lambda_5$lambda_1(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[1][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[1][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_6$lambda_5$lambda_2(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[2][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[2][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_6$lambda_5$lambda_3(UiMgr var0, FrontArea var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      String var3 = var1.getLineBtnAction()[3][var2];
      Intrinsics.checkNotNullExpressionValue(var3, "lineBtnAction[3][position]");
      var0.sendAirCommand(var3);
   }

   private static final void lambda_6$lambda_5$lambda_4(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 33});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 54});
   }

   private static final void lambda_62$lambda_60(ParkPageUiSet var0, int var1) {
      if (var2 != null) {
         switch (var2) {
            case "_161_standard_view":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 2});
               break;
            case "_250_exit":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 0});
               break;
            case "_189_front_view":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 6});
               break;
            case "_161_automatic_view":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 5});
               break;
            case "_189_rear_view":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 8});
               break;
            case "_161_180_view":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 3});
               break;
            case "_161_enlarged_view":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 4});
               break;
            case "_161_360_view":
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 7});
         }
      }

   }

   private static final void lambda_62$lambda_61() {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 64});
   }

   private static final void lambda_65$lambda_64(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 51});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 52});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 53});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 59});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 61});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 63});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 65});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 80});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 81});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 82});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 83});
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 39});
   }

   private final void removeSettingItemWithDifferent(Context var1) {
      if (this.mMsgMgr.getMDifferent() == 32) {
         this.removeMainPrjBtnByName(var1, "air");
      }

      if (this.mMsgMgr.getMDifferent() != 16) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_284_setting_item_2B", "_161_anti_collision", "_161_decoder_voice"});
      }

      if (this.mMsgMgr.getMDifferent() != 19) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_118_setting_title_17", "_161_light_assist"});
      }

      int var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{7, 13, 16, 18, 19, 24, 25}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_rear_wiper"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{8, 16}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_220_driving_assistance"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{18, 19, 23, 24, 25}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"temperature_unit"});
      }

      if (this.mMsgMgr.getMDifferent() != 7) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_door_auto_lock"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{7, 9, 13, 20}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"parkingAssist"});
      }

      if (this.mMsgMgr.getMDifferent() != 7) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_central_control_door_lock"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{1, 9, 12, 13, 16, 17, 19, 23, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_283_car_setting_light_4"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{16, 17, 19, 23, 24, 25}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"fuel_unit"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{19, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_emergency_braking"});
      }

      if (this.mMsgMgr.getMDifferent() != 25) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_118_setting_title_13"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{17, 18, 19, 23, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"on_off_btn_txt_7"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{18, 23, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_only_unlock_trunk"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{1, 12, 13, 18, 19, 23, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_reversing_radar"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{1, 12, 17, 18, 19, 23, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_143_0x76_d1_b01"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{17, 19, 23, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_143_0x76_d0_b45"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{17, 19}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"outlander_simple_car_set_17"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{19, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_disable_rear_mirror"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{1, 17, 23}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_94_blind_spot_monitoring"});
      }

      if (this.mMsgMgr.getMDifferent() != 17) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_start_stop_disabled"});
      }

      if (this.mMsgMgr.getMDifferent() != 17) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_143_setting_5"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{16, 17, 18, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_161_door_opening_options"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{18, 19, 24, 25}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_143_setting_7"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{18, 19, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_81_traction_control_system"});
      }

      var2 = this.mMsgMgr.getMDifferent();
      if (!ArraysKt.contains(new Integer[]{4, 18, 19, 24}, var2)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"geely_theme_colors"});
      }

      if (this.mMsgMgr.getMDifferent() != 19) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_143_setting_8"});
      }

   }

   private final void sendAirCommand(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var1, (byte)var2});
   }

   private final void sendAirCommand(String var1) {
      int var3 = var1.hashCode();
      byte var2 = 2;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               this.sendAirCommand(17, this.getOpenOrClose(GeneralAirData.max_front));
               GeneralAirData.max_front ^= true;
            }
            break;
         case -1423573049:
            if (var1.equals("ac_max")) {
               this.sendAirCommand(3, this.getOpenOrClose(GeneralAirData.ac_max));
            }
            break;
         case -825767279:
            if (var1.equals("auto_wind_lv")) {
               var3 = GeneralAirData.auto_wind_lv;
               if (var3 != 0) {
                  if (var3 != 1) {
                     var2 = 0;
                  }
               } else {
                  var2 = 1;
               }

               this.sendAirCommand(9, var2);
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               this.sendAirCommandUpDown(18);
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               this.sendAirCommand(2, this.getOpenOrClose(GeneralAirData.ac));
            }
            break;
         case 96835:
            if (var1.equals("aqs")) {
               this.sendAirCommand(13, this.getOpenOrClose(GeneralAirData.aqs));
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               this.sendAirCommand(1, this.getOpenOrClose(GeneralAirData.auto));
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               this.sendAirCommand(11, this.getOpenOrClose(GeneralAirData.dual));
            }
            break;
         case 3496356:
            if (var1.equals("rear")) {
               this.sendAirCommand(15, this.getOpenOrClose(GeneralAirData.rear));
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               this.sendAirCommandUpDown(12);
            }
            break;
         case 341572893:
            if (var1.equals("blow_window")) {
               this.sendAirCommand(7, this.getOpenOrClose(GeneralAirData.front_left_blow_window));
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               this.sendAirCommandUpDown(14);
            }
            break;
         case 1006620906:
            if (var1.equals("auto_wind_mode")) {
               this.sendAirCommandUpDown(19);
            }
            break;
         case 1434490075:
            if (var1.equals("blow_foot")) {
               this.sendAirCommand(8, this.getOpenOrClose(GeneralAirData.front_left_blow_foot));
            }
            break;
         case 1434539597:
            if (var1.equals("blow_head")) {
               this.sendAirCommand(6, this.getOpenOrClose(GeneralAirData.front_left_blow_head));
            }
      }

   }

   private final void sendAirCommandUpDown(int var1) {
      byte[] var2 = new byte[]{22, -118, (byte)var1, 1};
      CanbusMsgSender.sendMsg(var2);
      var2[3] = 0;
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda6(var2), 100L);
   }

   private static final void sendAirCommandUpDown$lambda_67$lambda_66(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this_run");
      CanbusMsgSender.sendMsg(var0);
   }

   public final int getData0() {
      return this.Data0;
   }

   public final Handler getMHandler() {
      return this.mHandler;
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

   public final int resolvedata(int var1, int var2) {
      Integer[] var6;
      label25: {
         var6 = new Integer[8];
         byte var5 = 0;
         byte var4 = 0;
         Integer var7 = 0;
         int var3 = var5;
         if (var1 == 7) {
            var3 = var5;
            if (var2 == 0) {
               var1 = var4;

               while(true) {
                  if (var1 >= 8) {
                     break label25;
                  }

                  var6[var1] = var7;
                  ++var1;
               }
            }
         }

         while(true) {
            if (var3 >= 8) {
               var6[var1] = var2;
               var6[7] = 1;
               break;
            }

            var6[var3] = var7;
            ++var3;
         }
      }

      var1 = this.getDecimalFrom8Bit(var6);
      this.Data0 = var1;
      return var1;
   }

   public final void setData0(int var1) {
      this.Data0 = var1;
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"},
      d2 = {"Lcom/hzbhd/canbus/car/_161/UiMgr$Companion;", "", "()V", "AIR_COMMAND_DATA_TYPE", "", "TAG", "", "_161_AMPLIFIER_DATA", "_161_AMPLIFIER_DATA_MID", "_161_LCD_MODE_LEFT", "_161_LCD_MODE_RIGHT", "CanBusInfo_release"},
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
