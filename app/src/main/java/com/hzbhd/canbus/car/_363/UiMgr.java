package com.hzbhd.canbus.car._363;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"},
   d2 = {"Lcom/hzbhd/canbus/car/_363/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$DCh8PdLOQtk5qxA_mmphZ_FT7Ec(UiMgr var0, int var1, int var2, int var3) {
      lambda_1$lambda_0(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var2;
      var2.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda0(this));
   }

   private static final void lambda_1$lambda_0(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (Intrinsics.areEqual((Object)((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn(), (Object)"_340_automatic_folding")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte)var3});
      }

   }
}
