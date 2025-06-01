package com.hzbhd.canbus.car._364;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"},
   d2 = {"Lcom/hzbhd/canbus/car/_364/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mContext", "getMContext", "()Landroid/content/Context;", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_364/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final Context mContext;
   private final SettingHandleListener mSettingHandleListener;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$uLIZA42Pva1T1vCnOIRuY3BFDmQ(UiMgr var0, int var1, int var2) {
      lambda_1$lambda_0(var0, var1, var2);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var2;
      SettingHandleListener var3 = new SettingHandleListener(this, var2);
      this.mSettingHandleListener = var3;
      var2.setOnSettingItemSelectListener((OnSettingItemSelectListener)var3);
      var2.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var3);
      var2.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda0(this));
   }

   private static final void lambda_1$lambda_0(UiMgr var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"_396_setting54")) {
         CanbusMsgSender.sendMsg(new byte[]{22, 26, 0});
      }

   }

   public final Context getMContext() {
      return this.mContext;
   }

   public final SettingPageUiSet getMSettingPageUiSet() {
      return this.mSettingPageUiSet;
   }

   @Metadata(
      d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016J \u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0016J \u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fH\u0002J\u0018\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"},
      d2 = {"Lcom/hzbhd/canbus/car/_364/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/car/_364/UiMgr;Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "map", "", "", "", "getMap", "()Ljava/util/Map;", "onClickItem", "", "leftPos", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "sendAirSettingsCmd", "d0", "d1", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
      private final SettingPageUiSet mSettingPageUiSet;
      private final Map map;
      final UiMgr this$0;

      public SettingHandleListener(UiMgr var1, SettingPageUiSet var2) {
         Intrinsics.checkNotNullParameter(var2, "mSettingPageUiSet");
         this.this$0 = var1;
         super();
         this.mSettingPageUiSet = var2;
         this.map = MapsKt.mapOf(new Pair[]{TuplesKt.to("S364_Language_1", 1), TuplesKt.to("S364_Language_2", 2), TuplesKt.to("S364_Language_3", 3), TuplesKt.to("S364_Language_4", 4), TuplesKt.to("S364_Language_5", 5), TuplesKt.to("S364_Language_6", 6), TuplesKt.to("S364_Language_7", 7), TuplesKt.to("S364_Language_8", 15), TuplesKt.to("S364_Language_9", 8), TuplesKt.to("S364_Language_10", 9), TuplesKt.to("S364_Language_11", 10), TuplesKt.to("S364_Language_12", 11), TuplesKt.to("S364_Language_13", 12), TuplesKt.to("S364_Language_14", 13), TuplesKt.to("S364_Language_15", 14), TuplesKt.to("S364_Language_16", 16)});
      }

      private final void selectSettingsBtn(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         if (var4 != null) {
            var1 = var4.hashCode();
            if (var1 != 94883461) {
               switch (var1) {
                  case 193467355:
                     if (var4.equals("S364_AIR_1")) {
                        this.sendAirSettingsCmd(1, var3);
                     }
                     break;
                  case 193467356:
                     if (var4.equals("S364_AIR_2")) {
                        this.sendAirSettingsCmd(2, var3);
                     }
                     break;
                  case 193467357:
                     if (var4.equals("S364_AIR_3")) {
                        this.sendAirSettingsCmd(3, var3);
                     }
                     break;
                  case 193467358:
                     if (var4.equals("S364_AIR_4")) {
                        this.sendAirSettingsCmd(4, var3);
                     }
                     break;
                  case 193467359:
                     if (var4.equals("S364_AIR_5")) {
                        this.sendAirSettingsCmd(5, var3);
                     }
                     break;
                  case 193467360:
                     if (var4.equals("S364_AIR_6")) {
                        this.sendAirSettingsCmd(6, var3);
                     }
                     break;
                  case 193467361:
                     if (var4.equals("S364_AIR_7")) {
                        this.sendAirSettingsCmd(7, var3);
                     }
                     break;
                  case 193467362:
                     if (var4.equals("S364_AIR_8")) {
                        this.sendAirSettingsCmd(8, var3);
                     }
                     break;
                  case 193467363:
                     if (var4.equals("S364_AIR_9")) {
                        this.sendAirSettingsCmd(9, var3);
                     }
                     break;
                  default:
                     switch (var1) {
                        case 1702520757:
                           if (var4.equals("S364_AIR_10")) {
                              this.sendAirSettingsCmd(10, var3);
                           }
                           break;
                        case 1702520758:
                           if (var4.equals("S364_AIR_11")) {
                              this.sendAirSettingsCmd(11, var3);
                           }
                           break;
                        case 1702520759:
                           if (var4.equals("S364_AIR_12")) {
                              this.sendAirSettingsCmd(12, var3);
                           }
                           break;
                        case 1702520760:
                           if (var4.equals("S364_AIR_13")) {
                              this.sendAirSettingsCmd(13, var3);
                           }
                     }
               }
            } else if (var4.equals("S314_Language_0")) {
               Map var5 = this.map;
               Object var6 = InitUtilsKt.getMSettingItemIndex().get("S314_Language_0");
               Intrinsics.checkNotNull(var6);
               var6 = var5.get(((SettingPageUiSet.ListBean.ItemListBean)var6).getValueSrnArray().get(var3));
               Intrinsics.checkNotNull(var6);
               CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)((Number)var6).intValue()});
               SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_Language_0");
               if (var7 != null) {
                  var7.setValue(var7.getValueSrnArray().get(var3));
               }

               MsgMgrInterface var8 = MsgMgrFactory.getCanMsgMgr(this.this$0.getMContext());
               Intrinsics.checkNotNull(var8, "null cannot be cast to non-null type com.hzbhd.canbus.car._364.MsgMgr");
               ((MsgMgr)var8).updateSettingActivity((Bundle)null);
            }
         }

      }

      private final void sendAirSettingsCmd(int var1, int var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var1, (byte)var2});
      }

      public final SettingPageUiSet getMSettingPageUiSet() {
         return this.mSettingPageUiSet;
      }

      public final Map getMap() {
         return this.map;
      }

      public void onClickItem(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }

      public void onSwitch(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }
   }
}
