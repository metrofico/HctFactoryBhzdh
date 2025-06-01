package com.hzbhd.canbus.car._368;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lcom/hzbhd/canbus/car/_368/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$RZ7xRkzcilkh3wH8wfDW46VRMGI(Context var0, int var1, int var2, int var3) {
      lambda_4$lambda_3(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0(var1));
   }

   private static final void lambda_4$lambda_3(Context var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "$context");
      String var5 = InitUtilsKt.getSettingViewItemName(var1, var2);
      MsgMgrInterface var6;
      SettingPageUiSet.ListBean.ItemListBean var8;
      if (Intrinsics.areEqual((Object)var5, (Object)"S367_Car_0")) {
         byte var4 = 4;
         if (var3 != 1) {
            if (var3 != 2) {
               return;
            }
         } else {
            var4 = 2;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 36, var4, 13});
         var8 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S367_Car_0");
         if (var8 != null) {
            var8.setValue(var8.getValueSrnArray().get(var3));
         }

         var6 = MsgMgrFactory.getCanMsgMgr(var0);
         Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type com.hzbhd.canbus.car._368.MsgMgr");
         ((MsgMgr)var6).updateSettingActivity((Bundle)null);
      } else if (Intrinsics.areEqual((Object)var5, (Object)"S368_Color0")) {
         var8 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S368_Color0");
         if (var8 != null) {
            var8.setValue(var8.getValueSrnArray().get(var3));
         }

         var6 = MsgMgrFactory.getCanMsgMgr(var0);
         Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type com.hzbhd.canbus.car._368.MsgMgr");
         MsgMgr var7 = (MsgMgr)var6;
         var7.updateSettingActivity((Bundle)null);
         var7.setColor(var3);
      }

   }

   public final SettingPageUiSet getMSettingPageUiSet() {
      return this.mSettingPageUiSet;
   }
}
