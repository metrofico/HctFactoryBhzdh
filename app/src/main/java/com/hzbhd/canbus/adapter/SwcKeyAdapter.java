package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.SwcActivity;
import com.hzbhd.canbus.config.CustomKeyConfig;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fBC\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\u0010\rJ\b\u0010\u000f\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016J \u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "},
   d2 = {"Lcom/hzbhd/canbus/adapter/SwcKeyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "onItemClick", "Lkotlin/Function1;", "", "", "onItemLongClick", "(Landroid/content/Context;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "selectedIndex", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setKeyUi", "key", "icon", "Landroid/widget/ImageView;", "name", "Landroid/widget/TextView;", "setSelected", "index", "ViewHolder", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SwcKeyAdapter extends RecyclerView.Adapter {
   private final Context context;
   private List list;
   private final Function1 onItemClick;
   private final Function1 onItemLongClick;
   private int selectedIndex;

   // $FF: synthetic method
   public static void $r8$lambda$0puxPfoFg3astkMkWYKJyyDYEns(SwcKeyAdapter var0, int var1, View var2) {
      onBindViewHolder$lambda_3$lambda_2$lambda_0(var0, var1, var2);
   }

   // $FF: synthetic method
   public static boolean $r8$lambda$LH3U_gPKq3PqFqS5_g0lVD6McaA(SwcKeyAdapter var0, int var1, View var2) {
      return onBindViewHolder$lambda_3$lambda_2$lambda_1(var0, var1, var2);
   }

   public SwcKeyAdapter(Context var1, List var2, Function1 var3, Function1 var4) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "list");
      Intrinsics.checkNotNullParameter(var3, "onItemClick");
      Intrinsics.checkNotNullParameter(var4, "onItemLongClick");
      super();
      this.context = var1;
      this.list = var2;
      this.onItemClick = var3;
      this.onItemLongClick = var4;
   }

   private static final void onBindViewHolder$lambda_3$lambda_2$lambda_0(SwcKeyAdapter var0, int var1, View var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.onItemClick.invoke(var1);
      var0.setSelected(var1);
   }

   private static final boolean onBindViewHolder$lambda_3$lambda_2$lambda_1(SwcKeyAdapter var0, int var1, View var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.onItemLongClick.invoke(var1);
      return true;
   }

   private final void setKeyUi(int var1, ImageView var2, TextView var3) {
      Iterator var6 = ((Iterable)SwcActivity.Companion.getKeyUiList()).iterator();

      boolean var4;
      Object var5;
      do {
         if (!var6.hasNext()) {
            var5 = null;
            break;
         }

         var5 = var6.next();
         if (((SwcActivity.KeyUiEntity)var5).getKey() == var1) {
            var4 = true;
         } else {
            var4 = false;
         }
      } while(!var4);

      SwcActivity.KeyUiEntity var7 = (SwcActivity.KeyUiEntity)var5;
      if (var7 != null) {
         var2.setImageResource(var7.getDrawableResId());
         var3.setText(var7.getStringResId());
      }

   }

   public int getItemCount() {
      return SwcActivity.Companion.getKeyUiList().size();
   }

   public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "holder");
      ViewHolder var6 = (ViewHolder)var1;
      CustomKeyConfig.KeyMap var5 = (CustomKeyConfig.KeyMap)this.list.get(var2);
      this.setKeyUi(var5.getOutput().getShort(), var6.getIvShortIcon(), var6.getTvShortName());
      int var3 = var5.getOutput().getLong();
      boolean var4 = false;
      if (var3 == 0) {
         var6.getIvDivider().setVisibility(8);
         var6.getIvLongIcon().setVisibility(8);
         var6.getTvLongName().setVisibility(8);
      } else {
         this.setKeyUi(var5.getOutput().getLong(), var6.getIvLongIcon(), var6.getTvLongName());
         var6.getIvDivider().setVisibility(0);
         var6.getIvLongIcon().setVisibility(0);
         var6.getTvLongName().setVisibility(0);
      }

      ImageView var9 = var6.getIvIndicator();
      byte var8;
      if (var5.getInput() == 0) {
         var8 = 8;
      } else {
         var8 = 0;
      }

      var9.setVisibility(var8);
      View var7 = var1.itemView;
      var7.setPadding(8, 8, 8, 10);
      if (this.selectedIndex == var2) {
         var4 = true;
      }

      var7.setSelected(var4);
      var7.setOnClickListener(new SwcKeyAdapter$$ExternalSyntheticLambda0(this, var2));
      var7.setOnLongClickListener(new SwcKeyAdapter$$ExternalSyntheticLambda1(this, var2));
   }

   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "parent");
      View var3 = LayoutInflater.from(this.context).inflate(2131558816, var1, false);
      Intrinsics.checkNotNullExpressionValue(var3, "from(context).inflate(R.…_key_item, parent, false)");
      return (RecyclerView.ViewHolder)(new ViewHolder(var3));
   }

   public final void setSelected(int var1) {
      this.selectedIndex = var1;
      this.notifyDataSetChanged();
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u0015"},
      d2 = {"Lcom/hzbhd/canbus/adapter/SwcKeyAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "ivDivider", "Landroid/widget/ImageView;", "getIvDivider", "()Landroid/widget/ImageView;", "ivIndicator", "getIvIndicator", "ivLongIcon", "getIvLongIcon", "ivShortIcon", "getIvShortIcon", "tvLongName", "Landroid/widget/TextView;", "getTvLongName", "()Landroid/widget/TextView;", "tvShortName", "getTvShortName", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class ViewHolder extends RecyclerView.ViewHolder {
      private final ImageView ivDivider;
      private final ImageView ivIndicator;
      private final ImageView ivLongIcon;
      private final ImageView ivShortIcon;
      private final TextView tvLongName;
      private final TextView tvShortName;

      public ViewHolder(View var1) {
         Intrinsics.checkNotNullParameter(var1, "view");
         super(var1);
         View var2 = var1.findViewById(2131362642);
         Intrinsics.checkNotNullExpressionValue(var2, "view.findViewById(R.id.iv_short_icon)");
         this.ivShortIcon = (ImageView)var2;
         var2 = var1.findViewById(2131362561);
         Intrinsics.checkNotNullExpressionValue(var2, "view.findViewById(R.id.iv_divider)");
         this.ivDivider = (ImageView)var2;
         var2 = var1.findViewById(2131362602);
         Intrinsics.checkNotNullExpressionValue(var2, "view.findViewById(R.id.iv_long_icon)");
         this.ivLongIcon = (ImageView)var2;
         var2 = var1.findViewById(2131363702);
         Intrinsics.checkNotNullExpressionValue(var2, "view.findViewById(R.id.tv_short_name)");
         this.tvShortName = (TextView)var2;
         var2 = var1.findViewById(2131363649);
         Intrinsics.checkNotNullExpressionValue(var2, "view.findViewById(R.id.tv_long_name)");
         this.tvLongName = (TextView)var2;
         var1 = var1.findViewById(2131362577);
         Intrinsics.checkNotNullExpressionValue(var1, "view.findViewById(R.id.iv_indicator)");
         this.ivIndicator = (ImageView)var1;
      }

      public final ImageView getIvDivider() {
         return this.ivDivider;
      }

      public final ImageView getIvIndicator() {
         return this.ivIndicator;
      }

      public final ImageView getIvLongIcon() {
         return this.ivLongIcon;
      }

      public final ImageView getIvShortIcon() {
         return this.ivShortIcon;
      }

      public final TextView getTvLongName() {
         return this.tvLongName;
      }

      public final TextView getTvShortName() {
         return this.tvShortName;
      }
   }
}
