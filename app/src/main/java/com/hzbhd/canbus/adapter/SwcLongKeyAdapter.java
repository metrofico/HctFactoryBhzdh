package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.SwcActivity;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"},
   d2 = {"Lcom/hzbhd/canbus/adapter/SwcLongKeyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "onItemClick", "Lkotlin/Function1;", "", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "selectedKey", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSelectedKey", "key", "ViewHolder", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SwcLongKeyAdapter extends RecyclerView.Adapter {
   private final Context context;
   private final Function1 onItemClick;
   private int selectedKey;

   // $FF: synthetic method
   public static void $r8$lambda$IliUaOdsatDW5jQzWGEnp6RdAMA(SwcLongKeyAdapter var0, int var1, View var2) {
      onBindViewHolder$lambda_2$lambda_1$lambda_0(var0, var1, var2);
   }

   public SwcLongKeyAdapter(Context var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "onItemClick");
      super();
      this.context = var1;
      this.onItemClick = var2;
   }

   private static final void onBindViewHolder$lambda_2$lambda_1$lambda_0(SwcLongKeyAdapter var0, int var1, View var2) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.onItemClick.invoke(var1);
   }

   public int getItemCount() {
      return SwcActivity.Companion.getKeyUiList().size();
   }

   public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "holder");
      ViewHolder var5 = (ViewHolder)var1;
      SwcActivity.KeyUiEntity var4 = (SwcActivity.KeyUiEntity)SwcActivity.Companion.getKeyUiList().get(var2);
      var5.getIvKeyIcon().setImageResource(var4.getDrawableResId());
      var5.getTvKeyName().setText(var4.getStringResId());
      View var6 = var1.itemView;
      boolean var3 = false;
      var6.setPadding(8, 0, 4, 0);
      if (this.selectedKey == var4.getKey()) {
         var3 = true;
      }

      var6.setSelected(var3);
      var6.setOnClickListener(new SwcLongKeyAdapter$$ExternalSyntheticLambda0(this, var2));
   }

   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "parent");
      View var3 = LayoutInflater.from(this.context).inflate(2131558818, var1, false);
      Intrinsics.checkNotNullExpressionValue(var3, "from(context).inflate(R.…lick_item, parent, false)");
      return (RecyclerView.ViewHolder)(new ViewHolder(var3));
   }

   public final void setSelectedKey(int var1) {
      this.selectedKey = var1;
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"},
      d2 = {"Lcom/hzbhd/canbus/adapter/SwcLongKeyAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "ivKeyIcon", "Landroid/widget/ImageView;", "getIvKeyIcon", "()Landroid/widget/ImageView;", "tvKeyName", "Landroid/widget/TextView;", "getTvKeyName", "()Landroid/widget/TextView;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class ViewHolder extends RecyclerView.ViewHolder {
      private final ImageView ivKeyIcon;
      private final TextView tvKeyName;

      public ViewHolder(View var1) {
         Intrinsics.checkNotNullParameter(var1, "view");
         super(var1);
         View var2 = var1.findViewById(2131362579);
         Intrinsics.checkNotNullExpressionValue(var2, "view.findViewById(R.id.iv_key_icon)");
         this.ivKeyIcon = (ImageView)var2;
         var1 = var1.findViewById(2131363635);
         Intrinsics.checkNotNullExpressionValue(var1, "view.findViewById(R.id.tv_key_name)");
         this.tvKeyName = (TextView)var1;
      }

      public final ImageView getIvKeyIcon() {
         return this.ivKeyIcon;
      }

      public final TextView getTvKeyName() {
         return this.tvKeyName;
      }
   }
}
