package com.hzbhd.canbus.car._331;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"},
   d2 = {"Lcom/hzbhd/canbus/car/_331/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mMyPanoramicView", "Lcom/hzbhd/canbus/car/_331/CusPanoramicView;", "getCusPanoramicView", "Landroid/view/View;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private CusPanoramicView mMyPanoramicView;

   public UiMgr(Context var1) {
   }

   public View getCusPanoramicView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new CusPanoramicView(var1, (AttributeSet)null, 0, 6, (DefaultConstructorMarker)null);
      }

      CusPanoramicView var2 = this.mMyPanoramicView;
      Intrinsics.checkNotNull(var2);
      return (View)var2;
   }
}
