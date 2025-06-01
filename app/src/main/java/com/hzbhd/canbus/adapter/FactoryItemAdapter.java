package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.FactoryActivity;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DialogUtil;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u0016\u0017\u0018\u0019B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0016J\f\u0010\u0014\u001a\u00020\n*\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"},
   d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/hzbhd/canbus/activity/FactoryActivity$ItemUiSet;", "(Landroid/content/Context;Ljava/util/List;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "toInt", "", "Companion", "ViewHolder", "ViewHolder1", "ViewHolder4", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class FactoryItemAdapter extends RecyclerView.Adapter {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String TAG = "FactoryItemAdapter";
   private final Context context;
   private final List list;

   // $FF: synthetic method
   public static void $r8$lambda$0Nolu5_y2twqe2__Rp9SxmIwwHA(FactoryActivity.ItemUiSet var0, View var1) {
      onBindViewHolder$lambda_6$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$65QqTmyEyqzLOPn2O_MF12gmxnc(RecyclerView.ViewHolder var0, FactoryActivity.ItemUiSet var1, FactoryItemAdapter var2, View var3) {
      onBindViewHolder$lambda_6$lambda_5(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$HTuKtKaw_oCycShJmlcP76t43_g(FactoryItemAdapter var0, List var1, FactoryActivity.ItemUiSet var2, RecyclerView.ViewHolder var3, View var4) {
      onBindViewHolder$lambda_6$lambda_4$lambda_3(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$emRad018InD_O5_mOlEAHaRpshY(RecyclerView.ViewHolder var0, List var1, FactoryActivity.ItemUiSet var2, int var3) {
      onBindViewHolder$lambda_6$lambda_4$lambda_3$lambda_2(var0, var1, var2, var3);
   }

   public FactoryItemAdapter(Context var1, List var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "list");
      super();
      this.context = var1;
      this.list = var2;
   }

   private static final void onBindViewHolder$lambda_6$lambda_0(FactoryActivity.ItemUiSet var0, View var1) {
      Intrinsics.checkNotNullParameter(var0, "$this_run");
      var0.getOnClick().invoke();
   }

   private static final void onBindViewHolder$lambda_6$lambda_4$lambda_3(FactoryItemAdapter var0, List var1, FactoryActivity.ItemUiSet var2, RecyclerView.ViewHolder var3, View var4) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$this_run");
      Intrinsics.checkNotNullParameter(var2, "$this_run$1");
      Intrinsics.checkNotNullParameter(var3, "$holder");
      DialogUtil var7 = DialogUtil.getInstance();
      Context var5 = var0.context;
      Object[] var6 = ((Collection)var1).toArray(new String[0]);
      Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
      var7.showListDialog(var5, (String[])var6, ((Number)var2.getGetValue().invoke()).intValue(), new FactoryItemAdapter$$ExternalSyntheticLambda3(var3, var1, var2));
   }

   private static final void onBindViewHolder$lambda_6$lambda_4$lambda_3$lambda_2(RecyclerView.ViewHolder var0, List var1, FactoryActivity.ItemUiSet var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "$holder");
      Intrinsics.checkNotNullParameter(var1, "$this_run");
      Intrinsics.checkNotNullParameter(var2, "$this_run$1");
      ((ViewHolder1)var0).getTvValue().setText((CharSequence)var1.get(var3));
      var2.getSetValue().invoke(var3);
   }

   private static final void onBindViewHolder$lambda_6$lambda_5(RecyclerView.ViewHolder var0, FactoryActivity.ItemUiSet var1, FactoryItemAdapter var2, View var3) {
      Intrinsics.checkNotNullParameter(var0, "$holder");
      Intrinsics.checkNotNullParameter(var1, "$this_run");
      Intrinsics.checkNotNullParameter(var2, "this$0");
      ViewHolder4 var4 = (ViewHolder4)var0;
      var4.getSwStatus().setChecked(var4.getSwStatus().isChecked() ^ true);
      var1.getSetValue().invoke(var2.toInt(var4.getSwStatus().isChecked()));
   }

   private final int toInt(boolean var1) {
      return var1;
   }

   public int getItemCount() {
      return this.list.size();
   }

   public int getItemViewType(int var1) {
      return ((FactoryActivity.ItemUiSet)this.list.get(var1)).getStyle();
   }

   public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "holder");
      FactoryActivity.ItemUiSet var5 = (FactoryActivity.ItemUiSet)this.list.get(var2);
      ((ViewHolder)var1).getTvTitle().setText((CharSequence)CommUtil.getStrByResId(this.context, var5.getTitleResName()));
      int var3 = var5.getStyle();
      if (var3 != 0) {
         var2 = 0;
         boolean var4 = false;
         if (var3 != 1) {
            if (var3 != 4) {
               return;
            }

            Switch var6 = ((ViewHolder4)var1).getSwStatus();
            if (((Number)var5.getGetValue().invoke()).intValue() == 1) {
               var4 = true;
            }

            var6.setChecked(var4);
            var1.itemView.setOnClickListener(new FactoryItemAdapter$$ExternalSyntheticLambda2(var1, var5, this));
         } else {
            ViewHolder1 var8 = (ViewHolder1)var1;
            List var7 = var5.getListValues();
            if (var7 != null) {
               for(var3 = var7.size(); var2 < var3; ++var2) {
                  String var9 = CommUtil.getStrByResId(this.context, (String)var7.get(var2));
                  Intrinsics.checkNotNullExpressionValue(var9, "getStrByResId(context, get(it))");
                  var7.set(var2, var9);
               }

               var8.getTvValue().setText((CharSequence)var5.getListValues().get(((Number)var5.getGetValue().invoke()).intValue()));
               var1.itemView.setOnClickListener(new FactoryItemAdapter$$ExternalSyntheticLambda1(this, var7, var5, var1));
            }
         }
      } else {
         var1.itemView.setOnClickListener(new FactoryItemAdapter$$ExternalSyntheticLambda0(var5));
      }

   }

   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "parent");
      View var3;
      RecyclerView.ViewHolder var4;
      if (var2 != 1) {
         if (var2 != 4) {
            var3 = LayoutInflater.from(this.context).inflate(2131558777, var1, false);
            Intrinsics.checkNotNullExpressionValue(var3, "from(context).inflate(R.…ting_0_lv, parent, false)");
            var4 = (RecyclerView.ViewHolder)(new ViewHolder(var3));
         } else {
            var3 = LayoutInflater.from(this.context).inflate(2131558781, var1, false);
            Intrinsics.checkNotNullExpressionValue(var3, "from(context).inflate(R.…ting_4_lv, parent, false)");
            var4 = (RecyclerView.ViewHolder)(new ViewHolder4(var3));
         }
      } else {
         var3 = LayoutInflater.from(this.context).inflate(2131558778, var1, false);
         Intrinsics.checkNotNullExpressionValue(var3, "from(context).inflate(R.…ting_1_lv, parent, false)");
         var4 = (RecyclerView.ViewHolder)(new ViewHolder1(var3));
      }

      return var4;
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$Companion;", "", "()V", "TAG", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "tvTitle", "Landroid/widget/TextView;", "getTvTitle", "()Landroid/widget/TextView;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static class ViewHolder extends RecyclerView.ViewHolder {
      private final TextView tvTitle;

      public ViewHolder(View var1) {
         Intrinsics.checkNotNullParameter(var1, "itemView");
         super(var1);
         var1 = var1.findViewById(2131363710);
         Intrinsics.checkNotNullExpressionValue(var1, "itemView.findViewById(R.id.tv_title)");
         this.tvTitle = (TextView)var1;
      }

      public final TextView getTvTitle() {
         return this.tvTitle;
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder1;", "Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "tvValue", "Landroid/widget/TextView;", "getTvValue", "()Landroid/widget/TextView;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static class ViewHolder1 extends ViewHolder {
      private final TextView tvValue;

      public ViewHolder1(View var1) {
         Intrinsics.checkNotNullParameter(var1, "itemView");
         super(var1);
         var1 = var1.findViewById(2131363714);
         Intrinsics.checkNotNullExpressionValue(var1, "itemView.findViewById(R.id.tv_value)");
         this.tvValue = (TextView)var1;
      }

      public final TextView getTvValue() {
         return this.tvValue;
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder4;", "Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "swStatus", "Landroid/widget/Switch;", "getSwStatus", "()Landroid/widget/Switch;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static class ViewHolder4 extends ViewHolder {
      private final Switch swStatus;

      public ViewHolder4(View var1) {
         Intrinsics.checkNotNullParameter(var1, "itemView");
         super(var1);
         var1 = var1.findViewById(2131363473);
         Intrinsics.checkNotNullExpressionValue(var1, "itemView.findViewById(R.id.sw_status)");
         this.swStatus = (Switch)var1;
      }

      public final Switch getSwStatus() {
         return this.swStatus;
      }
   }
}
