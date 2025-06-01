package com.hzbhd.ui.util;

import android.text.TextUtils;
import com.hzbhd.config.use.UI;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bJ\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000bR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"},
   d2 = {"Lcom/hzbhd/ui/util/UiIdObserver;", "", "()V", "oldId", "", "getOldId", "()Ljava/lang/String;", "setOldId", "(Ljava/lang/String;)V", "uiChangeListeners", "Ljava/util/ArrayList;", "Lcom/hzbhd/ui/util/UiIdObserver$UIChangeListener;", "Lkotlin/collections/ArrayList;", "addUIChangeListener", "", "index", "", "uiChangeListener", "notifyUIChange", "uiid", "removeUIChangeListener", "UIChangeListener", "java-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiIdObserver {
   private String oldId;
   private final ArrayList uiChangeListeners = new ArrayList();

   // $FF: synthetic method
   public static void $r8$lambda$A7IPRj29O_LGZ2hPf00LRNWuycs(UiIdObserver var0, String var1) {
      _init_$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$R7jURDkUa5Y03urgPBG3okSfilI(String var0, UIChangeListener var1) {
      notifyUIChange$lambda_1(var0, var1);
   }

   public UiIdObserver() {
      this.oldId = UI.INSTANCE.getUIId();
      ShareDataManager.getInstance().registerShareStringListener("ui.id", new UiIdObserver$$ExternalSyntheticLambda1(this));
   }

   private static final void _init_$lambda_0(UiIdObserver var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullExpressionValue(var1, "uiid");
      var0.notifyUIChange(var1);
   }

   // $FF: synthetic method
   public static void addUIChangeListener$default(UiIdObserver var0, int var1, UIChangeListener var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = -1;
      }

      var0.addUIChangeListener(var1, var2);
   }

   private final void notifyUIChange(String var1) {
      if (LogUtil.log5()) {
         LogUtil.d("notifyUIChange: " + this.oldId + ' ' + var1 + "  " + UI.INSTANCE.getUIId() + "  " + this.uiChangeListeners.size());
      }

      if (!Intrinsics.areEqual((Object)this.oldId, (Object)var1) && !TextUtils.isEmpty((CharSequence)var1)) {
         this.oldId = var1;
         this.uiChangeListeners.stream().forEach(new UiIdObserver$$ExternalSyntheticLambda0(var1));
      }

   }

   private static final void notifyUIChange$lambda_1(String var0, UIChangeListener var1) {
      Intrinsics.checkNotNullParameter(var0, "$uiid");
      var1.onUIChange(var0);
   }

   public final void addUIChangeListener(int var1, UIChangeListener var2) {
      Intrinsics.checkNotNullParameter(var2, "uiChangeListener");
      if (!this.uiChangeListeners.contains(var2)) {
         if (var1 < 0) {
            this.uiChangeListeners.add(var2);
         } else {
            this.uiChangeListeners.add(var1, var2);
         }
      }

   }

   public final String getOldId() {
      return this.oldId;
   }

   public final void removeUIChangeListener(UIChangeListener var1) {
      Intrinsics.checkNotNullParameter(var1, "uiChangeListener");
      if (this.uiChangeListeners.contains(var1)) {
         this.uiChangeListeners.remove(var1);
      }

   }

   public final void setOldId(String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.oldId = var1;
   }

   @Metadata(
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/ui/util/UiIdObserver$UIChangeListener;", "", "onUIChange", "", "uiid", "", "java-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public interface UIChangeListener {
      void onUIChange(String var1);
   }
}
