package com.hzbhd.canbus.car._331;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"},
   d2 = {"Lcom/hzbhd/canbus/car/_331/CusPanoramicView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mRadarView", "Lcom/hzbhd/canbus/car/_331/RadarView;", "refreshUi", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CusPanoramicView extends RelativeLayout {
   public Map _$_findViewCache;
   private final RadarView mRadarView;

   public CusPanoramicView(Context var1) {
      this(var1, (AttributeSet)null, 0, 6, (DefaultConstructorMarker)null);
   }

   public CusPanoramicView(Context var1, AttributeSet var2) {
      this(var1, var2, 0, 4, (DefaultConstructorMarker)null);
   }

   public CusPanoramicView(Context var1, AttributeSet var2, int var3) {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558806, (ViewGroup)this).findViewById(2131363007);
      Intrinsics.checkNotNullExpressionValue(var4, "view.findViewById(R.id.radarView)");
      this.mRadarView = (RadarView)var4;
   }

   // $FF: synthetic method
   public CusPanoramicView(Context var1, AttributeSet var2, int var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      this(var1, var2, var3);
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

   public final void refreshUi() {
      this.mRadarView.updateUi();
   }
}
