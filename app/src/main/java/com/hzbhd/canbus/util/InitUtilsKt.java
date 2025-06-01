package com.hzbhd.canbus.util;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u001a\u0016\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b\u001a\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0014\u001a>\u0010!\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u00142$\b\u0002\u0010\"\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t`\n\u001aF\u0010#\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u00142,\b\u0002\u0010\"\u001a&\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u0007j\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010`\n\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"6\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\">\u0010\u000f\u001a&\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u0007j\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000e\"\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006$"},
   d2 = {"mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mDrivingItemIndex", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "getMDrivingItemIndex", "()Ljava/util/HashMap;", "setMDrivingItemIndex", "(Ljava/util/HashMap;)V", "mSettingItemIndex", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "getMSettingItemIndex", "setMSettingItemIndex", "mUiMgr", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;)V", "getSettingViewItemName", "left", "", "right", "init", "", "context", "uiMgr", "initDrivingItemsIndexHashMap", "hashMap", "initSettingItemsIndexHashMap", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class InitUtilsKt {
   public static Context mContext;
   private static HashMap mDrivingItemIndex = new HashMap();
   private static HashMap mSettingItemIndex = new HashMap();
   public static AbstractUiMgr mUiMgr;

   public static final Context getMContext() {
      Context var0 = mContext;
      if (var0 != null) {
         return var0;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         return null;
      }
   }

   public static final HashMap getMDrivingItemIndex() {
      return mDrivingItemIndex;
   }

   public static final HashMap getMSettingItemIndex() {
      return mSettingItemIndex;
   }

   public static final AbstractUiMgr getMUiMgr() {
      AbstractUiMgr var0 = mUiMgr;
      if (var0 != null) {
         return var0;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         return null;
      }
   }

   public static final String getSettingViewItemName(int var0, int var1) {
      String var2 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)getMUiMgr().getSettingUiSet(getMContext()).getList().get(var0)).getItemList().get(var1)).getTitleSrn();
      Intrinsics.checkNotNullExpressionValue(var2, "mUiMgr.getSettingUiSet(m….itemList[right].titleSrn");
      return var2;
   }

   public static final void init(Context var0, AbstractUiMgr var1) {
      Intrinsics.checkNotNullParameter(var0, "context");
      Intrinsics.checkNotNullParameter(var1, "uiMgr");
      setMUiMgr(var1);
      setMContext(var0);
   }

   public static final void initDrivingItemsIndexHashMap(Context var0, AbstractUiMgr var1, HashMap var2) {
      Intrinsics.checkNotNullParameter(var1, "uiMgr");
      Intrinsics.checkNotNullParameter(var2, "hashMap");
      Intrinsics.checkNotNull(var0);
      init(var0, var1);
      List var6 = getMUiMgr().getDriverDataPageUiSet(var0).getList();
      Iterator var5 = var6.iterator();

      for(int var3 = 0; var5.hasNext(); ++var3) {
         Iterator var9 = ((DriverDataPageUiSet.Page)var5.next()).getItemList().iterator();

         for(int var4 = 0; var9.hasNext(); ++var4) {
            DriverDataPageUiSet.Page.Item var7 = (DriverDataPageUiSet.Page.Item)var9.next();
            Map var10 = (Map)var2;
            String var8 = var7.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var8, "item.titleSrn");
            Object var11 = ((DriverDataPageUiSet.Page)var6.get(var3)).getItemList().get(var4);
            Intrinsics.checkNotNullExpressionValue(var11, "this[pageIndex].itemList[itemIndex]");
            var10.put(var8, var11);
         }
      }

   }

   // $FF: synthetic method
   public static void initDrivingItemsIndexHashMap$default(Context var0, AbstractUiMgr var1, HashMap var2, int var3, Object var4) {
      if ((var3 & 4) != 0) {
         var2 = mDrivingItemIndex;
      }

      initDrivingItemsIndexHashMap(var0, var1, var2);
   }

   public static final void initSettingItemsIndexHashMap(Context var0, AbstractUiMgr var1, HashMap var2) {
      Intrinsics.checkNotNullParameter(var1, "uiMgr");
      Intrinsics.checkNotNullParameter(var2, "hashMap");
      Intrinsics.checkNotNull(var0);
      init(var0, var1);
      List var10 = getMUiMgr().getSettingUiSet(var0).getList();
      Iterator var6 = var10.iterator();

      for(int var3 = 0; var6.hasNext(); ++var3) {
         Iterator var5 = ((SettingPageUiSet.ListBean)var6.next()).getItemList().iterator();

         for(int var4 = 0; var5.hasNext(); ++var4) {
            SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var5.next();
            Map var9 = (Map)var2;
            String var8 = var7.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var8, "itemListBean.titleSrn");
            Object var11 = ((SettingPageUiSet.ListBean)var10.get(var3)).getItemList().get(var4);
            Intrinsics.checkNotNullExpressionValue(var11, "this[left].itemList[right]");
            var9.put(var8, var11);
         }
      }

   }

   // $FF: synthetic method
   public static void initSettingItemsIndexHashMap$default(Context var0, AbstractUiMgr var1, HashMap var2, int var3, Object var4) {
      if ((var3 & 4) != 0) {
         var2 = mSettingItemIndex;
      }

      initSettingItemsIndexHashMap(var0, var1, var2);
   }

   public static final void setMContext(Context var0) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      mContext = var0;
   }

   public static final void setMDrivingItemIndex(HashMap var0) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      mDrivingItemIndex = var0;
   }

   public static final void setMSettingItemIndex(HashMap var0) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      mSettingItemIndex = var0;
   }

   public static final void setMUiMgr(AbstractUiMgr var0) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      mUiMgr = var0;
   }
}
