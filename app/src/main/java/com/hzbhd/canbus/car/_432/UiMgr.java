package com.hzbhd.canbus.car._432;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0010\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"},
   d2 = {"Lcom/hzbhd/canbus/car/_432/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "syncPageUiSet", "Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;", "getSyncPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;", "setSyncPageUiSet", "(Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;)V", "getDrivingItemIndexes", "", "titleSrn", "", "getDrivingPageIndexes", "headTitleSrn", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private SyncPageUiSet syncPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$BsZ9PpAKL9LuZl5mE55dcL07JOM(int var0) {
      _init_$lambda_0(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$ffQ85VO6gslfpagsNL8lHBCHkGA() {
      _init_$lambda_1();
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SyncPageUiSet var2 = this.getSyncPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getSyncPageUiSet(context)");
      this.syncPageUiSet = var2;
      DriverDataPageUiSet var4 = this.getDriverDataPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "getDriverDataPageUiSet(context)");
      var4.setOnDriveDataPageStatusListener(new UiMgr$$ExternalSyntheticLambda0());
      AirPageUiSet var3 = this.getAirUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getAirUiSet(context)");
      var3.setOnAirInitListener(new UiMgr$$ExternalSyntheticLambda1());
   }

   private static final void _init_$lambda_0(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 96, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 97, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 98, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 99, 0});
   }

   private static final void _init_$lambda_1() {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
   }

   public final int getDrivingItemIndexes(Context var1, String var2) {
      Intrinsics.checkNotNullParameter(var2, "titleSrn");
      List var9 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();
      int var5 = var9.size();

      for(int var3 = 0; var3 < var5; ++var3) {
         Iterator var7 = var9.iterator();

         while(var7.hasNext()) {
            List var8 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();
            int var6 = var8.size();

            for(int var4 = 0; var4 < var6; ++var4) {
               if (Intrinsics.areEqual((Object)((DriverDataPageUiSet.Page.Item)var8.get(var4)).getTitleSrn(), (Object)var2)) {
                  return var4;
               }
            }
         }
      }

      return 404;
   }

   public final int getDrivingPageIndexes(Context var1, String var2) {
      Intrinsics.checkNotNullParameter(var2, "headTitleSrn");
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();
      int var4 = var5.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         if (Intrinsics.areEqual((Object)((DriverDataPageUiSet.Page)var5.get(var3)).getHeadTitleSrn(), (Object)var2)) {
            return var3;
         }
      }

      return 404;
   }

   public final SyncPageUiSet getSyncPageUiSet() {
      return this.syncPageUiSet;
   }

   public final void setSyncPageUiSet(SyncPageUiSet var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.syncPageUiSet = var1;
   }
}
