package com.hzbhd.canbus.vm.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.hzbhd.canbus.vm.binding.ReversePanoramicCommonBinding;
import com.hzbhd.ui.life.BaseFrameLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"},
   d2 = {"Lcom/hzbhd/canbus/vm/view/main/ReversePanoramicCommonView;", "Lcom/hzbhd/ui/life/BaseFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "getBinding", "Lcom/hzbhd/canbus/vm/binding/ReversePanoramicCommonBinding;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ReversePanoramicCommonView extends BaseFrameLayout {
   public Map _$_findViewCache;

   public ReversePanoramicCommonView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1);
   }

   public ReversePanoramicCommonView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2);
   }

   public ReversePanoramicCommonView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2, var3);
   }

   public ReversePanoramicCommonView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2, var3, var4);
   }

   public void _$_clearFindViewByIdCache() {
      this._$_findViewCache.clear();
   }

   public View _$_findCachedViewById(int var1) {
      Map var4 = this._$_findViewCache;
      View var3 = (View)var4.get(var1);
      View var2 = var3;
      if (var3 == null) {
         var2 = this.findViewById(var1);
         if (var2 != null) {
            var4.put(var1, var2);
         } else {
            var2 = null;
         }
      }

      return var2;
   }

   public ReversePanoramicCommonBinding getBinding() {
      Context var1 = this.getContext();
      Intrinsics.checkNotNullExpressionValue(var1, "context");
      return new ReversePanoramicCommonBinding(var1);
   }
}
