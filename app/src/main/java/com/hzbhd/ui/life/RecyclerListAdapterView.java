package com.hzbhd.ui.life;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.ui.base.BhdViewUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001(B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001aH&J\b\u0010\u001c\u001a\u00020\u001aH&J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH&J\b\u0010 \u001a\u00020!H\u0016J\u0006\u0010\"\u001a\u00020\tJ\u001a\u0010#\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010$\u001a\u00020\u001aH&J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020'H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006)"},
   d2 = {"Lcom/hzbhd/ui/life/RecyclerListAdapterView;", "Lcom/hzbhd/ui/life/BaseLifeFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isAdd", "", "itemInfo", "Lcom/hzbhd/ui/life/RecyclerListAdapterView$ItemInfo;", "getItemInfo", "()Lcom/hzbhd/ui/life/RecyclerListAdapterView$ItemInfo;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "afterViewInit", "", "beforeViewInit", "clearDataWhenHide", "getAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getLayoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "getScrollStyle", "initAttr", "initData", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "ItemInfo", "lifeview_release"},
   k = 1,
   mv = {1, 6, 0},
   xi = 48
)
public abstract class RecyclerListAdapterView extends BaseLifeFrameLayout {
   private boolean isAdd;
   private final ItemInfo itemInfo;
   private RecyclerView recyclerView;

   public RecyclerListAdapterView(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1);
      this.itemInfo = new ItemInfo();
   }

   public RecyclerListAdapterView(Context var1, AttributeSet var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "attrs");
      super(var1, var2);
      this.itemInfo = new ItemInfo();
      this.initAttr(var1, var2);
   }

   public RecyclerListAdapterView(Context var1, AttributeSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3);
      this.itemInfo = new ItemInfo();
      this.initAttr(var1, var2);
   }

   public RecyclerListAdapterView(Context var1, AttributeSet var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super(var1, var2, var3, var4);
      this.itemInfo = new ItemInfo();
      this.initAttr(var1, var2);
   }

   private final void initAttr(Context var1, AttributeSet var2) {
      if (var2 != null) {
         TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.recycleViewAttrs);
         this.getItemInfo().setDefItemLayout(var3.getResourceId(R.styleable.recycleViewAttrs_default_item_layout, 0));
         this.getItemInfo().setDefItemWidth(var3.getLayoutDimension(R.styleable.recycleViewAttrs_default_item_width, -2));
         this.getItemInfo().setDefItemHeight(var3.getLayoutDimension(R.styleable.recycleViewAttrs_default_item_height, -2));
         this.getItemInfo().setFirstItemLayout(var3.getResourceId(R.styleable.recycleViewAttrs_first_item_layout, 0));
         this.getItemInfo().setLastItemLayout(var3.getResourceId(R.styleable.recycleViewAttrs_last_item_layout, 0));
         this.getItemInfo().setScrollbarStyle(var3.getResourceId(R.styleable.recycleViewAttrs_scroll_style, 0));
         this.getItemInfo().setDefaultItemLayoutChild(var3.getResourceId(R.styleable.recycleViewAttrs_default_item_layout_child, 0));
         this.getItemInfo().setRecyclerViewStyle(var3.getResourceId(R.styleable.recycleViewAttrs_recyclerview_style, 0));
         var3.recycle();
      }

   }

   public abstract void afterViewInit();

   public abstract void beforeViewInit();

   public abstract void clearDataWhenHide();

   public abstract RecyclerView.Adapter getAdapter();

   public final ItemInfo getItemInfo() {
      return this.itemInfo;
   }

   public RecyclerView.LayoutManager getLayoutManager() {
      return (RecyclerView.LayoutManager)(new LinearLayoutManager(this.getContext()));
   }

   public final RecyclerView getRecyclerView() {
      return this.recyclerView;
   }

   public final int getScrollStyle() {
      return this.itemInfo.getScrollbarStyle();
   }

   public abstract void initData();

   public void onLifeCycleChange(Lifecycle.State var1) {
      Intrinsics.checkNotNullParameter(var1, "state");
      super.onLifeCycleChange(var1);
      RecyclerView var2;
      if (var1 == Lifecycle.State.RESUMED) {
         var2 = this.recyclerView;
         if (var2 != null) {
            var2.setVisibility(0);
         }

         if (!this.isAdd) {
            this.isAdd = true;
            BhdViewUtil.INSTANCE.runBack((Function0)(new Function0(this) {
               final RecyclerListAdapterView this$0;

               {
                  this.this$0 = var1;
               }

               public final void invoke() {
                  this.this$0.beforeViewInit();
                  RecyclerListAdapterView var2 = this.this$0;
                  RecyclerView var1;
                  if (var2.getItemInfo().getRecyclerViewStyle() == 0) {
                     var1 = new RecyclerView(this.this$0.getContext());
                  } else {
                     var1 = (RecyclerView)LayoutInflater.from(this.this$0.getContext()).inflate(this.this$0.getItemInfo().getRecyclerViewStyle(), (ViewGroup)null);
                  }

                  var2.setRecyclerView(var1);
                  var1 = this.this$0.getRecyclerView();
                  Intrinsics.checkNotNull(var1);
                  var1.setLayoutManager(this.this$0.getLayoutManager());
                  var1 = this.this$0.getRecyclerView();
                  Intrinsics.checkNotNull(var1);
                  var1.setAdapter(this.this$0.getAdapter());
                  var1 = this.this$0.getRecyclerView();
                  Intrinsics.checkNotNull(var1);
                  var1.setScrollBarStyle(this.this$0.getScrollStyle());
                  this.this$0.initData();
                  BhdViewUtil.INSTANCE.runUi((Function0)(new Function0(this.this$0) {
                     final RecyclerListAdapterView this$0;

                     {
                        this.this$0 = var1;
                     }

                     public final void invoke() {
                        RecyclerListAdapterView var1 = this.this$0;
                        var1.addView((View)var1.getRecyclerView());
                        this.this$0.afterViewInit();
                     }
                  }));
               }
            }));
         } else {
            this.initData();
         }
      } else {
         var2 = this.recyclerView;
         if (var2 != null) {
            var2.setVisibility(8);
         }

         this.clearDataWhenHide();
      }

   }

   public final void setRecyclerView(RecyclerView var1) {
      this.recyclerView = var1;
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"},
      d2 = {"Lcom/hzbhd/ui/life/RecyclerListAdapterView$ItemInfo;", "", "()V", "defItemHeight", "", "getDefItemHeight", "()I", "setDefItemHeight", "(I)V", "defItemLayout", "getDefItemLayout", "setDefItemLayout", "defItemWidth", "getDefItemWidth", "setDefItemWidth", "defaultItemLayoutChild", "getDefaultItemLayoutChild", "setDefaultItemLayoutChild", "firstItemLayout", "getFirstItemLayout", "setFirstItemLayout", "lastItemLayout", "getLastItemLayout", "setLastItemLayout", "recyclerViewStyle", "getRecyclerViewStyle", "setRecyclerViewStyle", "scrollbarStyle", "getScrollbarStyle", "setScrollbarStyle", "lifeview_release"},
      k = 1,
      mv = {1, 6, 0},
      xi = 48
   )
   public static final class ItemInfo {
      private int defItemHeight = -2;
      private int defItemLayout;
      private int defItemWidth = -2;
      private int defaultItemLayoutChild;
      private int firstItemLayout;
      private int lastItemLayout;
      private int recyclerViewStyle;
      private int scrollbarStyle;

      public final int getDefItemHeight() {
         return this.defItemHeight;
      }

      public final int getDefItemLayout() {
         return this.defItemLayout;
      }

      public final int getDefItemWidth() {
         return this.defItemWidth;
      }

      public final int getDefaultItemLayoutChild() {
         return this.defaultItemLayoutChild;
      }

      public final int getFirstItemLayout() {
         return this.firstItemLayout;
      }

      public final int getLastItemLayout() {
         return this.lastItemLayout;
      }

      public final int getRecyclerViewStyle() {
         return this.recyclerViewStyle;
      }

      public final int getScrollbarStyle() {
         return this.scrollbarStyle;
      }

      public final void setDefItemHeight(int var1) {
         this.defItemHeight = var1;
      }

      public final void setDefItemLayout(int var1) {
         this.defItemLayout = var1;
      }

      public final void setDefItemWidth(int var1) {
         this.defItemWidth = var1;
      }

      public final void setDefaultItemLayoutChild(int var1) {
         this.defaultItemLayoutChild = var1;
      }

      public final void setFirstItemLayout(int var1) {
         this.firstItemLayout = var1;
      }

      public final void setLastItemLayout(int var1) {
         this.lastItemLayout = var1;
      }

      public final void setRecyclerViewStyle(int var1) {
         this.recyclerViewStyle = var1;
      }

      public final void setScrollbarStyle(int var1) {
         this.scrollbarStyle = var1;
      }
   }
}
