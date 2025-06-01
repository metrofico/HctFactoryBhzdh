package com.hzbhd.canbus.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.PanelKeyLvAdapter;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012H\u0016R\"\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"},
   d2 = {"Lcom/hzbhd/canbus/activity/PanelKeyActivity;", "Lcom/hzbhd/canbus/activity/AbstractBaseActivity;", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter$ItemClickInterface;", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter$ItemLongClickInterface;", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter$ItemTouchInterface;", "()V", "mLeftList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "mPanelKeyLvAdapter", "Lcom/hzbhd/canbus/adapter/PanelKeyLvAdapter;", "mSet", "Lcom/hzbhd/canbus/ui_set/PanelKeyPageUiSet;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "position", "", "onItemLongClick", "onItemTouch", "event", "Landroid/view/MotionEvent;", "refreshUi", "bundle", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class PanelKeyActivity extends AbstractBaseActivity implements PanelKeyLvAdapter.ItemClickInterface, PanelKeyLvAdapter.ItemLongClickInterface, PanelKeyLvAdapter.ItemTouchInterface {
   public Map _$_findViewCache;
   private ArrayList mLeftList;
   private PanelKeyLvAdapter mPanelKeyLvAdapter;
   private PanelKeyPageUiSet mSet;

   public PanelKeyActivity() {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super();
   }

   private final void initView() {
      Context var1 = (Context)this;
      this.mSet = this.getUiMgrInterface(var1).getPanelKeyPageUiSet(var1);
      this.mLeftList = new ArrayList();
      PanelKeyPageUiSet var2 = this.mSet;
      if (var2 != null) {
         List var3 = var2.getList();
         if (var3 != null) {
            ArrayList var4 = this.mLeftList;
            Intrinsics.checkNotNull(var4);
            var4.addAll((Collection)var3);
         }
      }

      this.mPanelKeyLvAdapter = new PanelKeyLvAdapter(var1, this.mLeftList, (PanelKeyLvAdapter.ItemClickInterface)this, (PanelKeyLvAdapter.ItemTouchInterface)this, (PanelKeyLvAdapter.ItemLongClickInterface)this);
      RecyclerView var5 = (RecyclerView)this._$_findCachedViewById(R.id.rv_list);
      PanelKeyPageUiSet var6 = this.mSet;
      Intrinsics.checkNotNull(var6);
      var5.setLayoutManager((RecyclerView.LayoutManager)(new GridLayoutManager(var1, var6.getCount(), 1, false)));
      ((RecyclerView)this._$_findCachedViewById(R.id.rv_list)).setAdapter((RecyclerView.Adapter)this.mPanelKeyLvAdapter);
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

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558689);
      this.initView();
   }

   public void onItemClick(int var1) {
      PanelKeyPageUiSet var2 = this.mSet;
      if (var2 != null) {
         OnPanelKeyPositionListener var3 = var2.getOnPanelKeyPositionListener();
         if (var3 != null) {
            var3.click(var1);
         }
      }

   }

   public void onItemLongClick(int var1) {
      PanelKeyPageUiSet var2 = this.mSet;
      if (var2 != null) {
         OnPanelLongKeyPositionListener var3 = var2.getOnPanelLongKeyPositionListener();
         if (var3 != null) {
            var3.longClick(var1);
         }
      }

   }

   public void onItemTouch(int var1, MotionEvent var2) {
      Intrinsics.checkNotNullParameter(var2, "event");
      PanelKeyPageUiSet var3 = this.mSet;
      if (var3 != null) {
         OnPanelKeyPositionTouchListener var4 = var3.getOnPanelKeyPositionTouchListener();
         if (var4 != null) {
            var4.onTouch(var1, var2);
         }
      }

   }

   public void refreshUi(Bundle var1) {
   }
}
