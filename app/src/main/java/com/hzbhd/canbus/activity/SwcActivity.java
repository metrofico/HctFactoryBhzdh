package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.SwcKeyAdapter;
import com.hzbhd.canbus.config.CustomKeyConfig;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.view.SwcLongClickDialog;
import com.hzbhd.canbus.view.SwcResetDialog;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.ToastUtls;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 $2\u00020\u0001:\u0002$%B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u000bJ\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0019H\u0014J\b\u0010 \u001a\u00020\u0019H\u0014J\b\u0010!\u001a\u00020\u0019H\u0002J\b\u0010\"\u001a\u00020\u0019H\u0002J\b\u0010#\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006&"},
   d2 = {"Lcom/hzbhd/canbus/activity/SwcActivity;", "Landroid/app/Activity;", "()V", "btnReset", "Landroid/widget/Button;", "btnSave", "keyConfigList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "keyLearnList", "learningPosition", "", "rvKeys", "Landroidx/recyclerview/widget/RecyclerView;", "swcLongClickDialog", "Lcom/hzbhd/canbus/view/SwcLongClickDialog;", "swcResetDialog", "Lcom/hzbhd/canbus/view/SwcResetDialog;", "tvCurrentKey", "Landroid/widget/TextView;", "getString", "", "resId", "(Ljava/lang/Integer;)Ljava/lang/String;", "initView", "", "learnKey", "key", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "reset", "save", "updateCurrentKey", "Companion", "KeyUiEntity", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SwcActivity extends Activity {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "KeyLearnActivity";
   private static final List keyUiList = CollectionsKt.listOf(new KeyUiEntity[]{new KeyUiEntity(52, 2131234862, 2131770046), new KeyUiEntity(3, 2131234866, 2131770051), new KeyUiEntity(7, 2131234881, 2131770068), new KeyUiEntity(8, 2131234880, 2131770069), new KeyUiEntity(2, 2131234876, 2131770049), new KeyUiEntity(95, 2131234870, 2131770055), new KeyUiEntity(21, 2131234872, 2131770057), new KeyUiEntity(20, 2131234868, 2131770053), new KeyUiEntity(50, 2131234851, 2131770035), new KeyUiEntity(4, 2131234860, 2131770044), new KeyUiEntity(128, 2131234867, 2131770052), new KeyUiEntity(14, 2131234850, 2131770033), new KeyUiEntity(15, 2131234861, 2131770045), new KeyUiEntity(129, 2131234873, 2131770058), new KeyUiEntity(134, 2131234871, 2131770056), new KeyUiEntity(140, 2131234856, 2131770038), new KeyUiEntity(45, 2131234878, 2131770065), new KeyUiEntity(46, 2131234857, 2131770041), new KeyUiEntity(47, 2131234863, 2131770047), new KeyUiEntity(48, 2131234874, 2131770059), new KeyUiEntity(49, 2131234869, 2131770043), new KeyUiEntity(59, 2131234865, 2131770050), new KeyUiEntity(61, 2131234879, 2131770066), new KeyUiEntity(57, 2131234864, 2131770036), new KeyUiEntity(187, 2131234877, 2131770063), new KeyUiEntity(198, 2131234875, 2131770062)});
   public Map _$_findViewCache;
   private Button btnReset;
   private Button btnSave;
   private final List keyConfigList;
   private List keyLearnList;
   private int learningPosition;
   private RecyclerView rvKeys;
   private SwcLongClickDialog swcLongClickDialog;
   private SwcResetDialog swcResetDialog;
   private TextView tvCurrentKey;

   // $FF: synthetic method
   public static void $r8$lambda$I37K_WZZ6hJGUbPd4X_zmaAHZgQ(SwcActivity var0, View var1) {
      initView$lambda_6$lambda_5(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$rdS6_6F30xPrJVMw9KWEkzb9MYg(SwcActivity var0, View var1) {
      initView$lambda_4$lambda_3(var0, var1);
   }

   public SwcActivity() {
      this._$_findViewCache = (Map)(new LinkedHashMap());
      super();
      List var6 = CustomKeyConfig.INSTANCE.getKeyList();
      List var5 = var6;
      if (var6 == null) {
         var5 = CollectionsKt.emptyList();
      }

      this.keyConfigList = var5;
      int var3 = keyUiList.size();
      ArrayList var7 = new ArrayList(var3);

      for(int var1 = 0; var1 < var3; ++var1) {
         int var4 = ((KeyUiEntity)keyUiList.get(var1)).getKey();
         Iterator var9 = ((Iterable)this.keyConfigList).iterator();

         Object var8;
         label34: {
            while(var9.hasNext()) {
               var8 = var9.next();
               boolean var2;
               if (((CustomKeyConfig.KeyMap)var8).getOutput().getShort() == var4) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  break label34;
               }
            }

            var8 = null;
         }

         CustomKeyConfig.KeyMap var10 = (CustomKeyConfig.KeyMap)var8;
         CustomKeyConfig.KeyMap var11 = var10;
         if (var10 == null) {
            var11 = new CustomKeyConfig.KeyMap(0, new CustomKeyConfig.Key(var4, 0, 2, (DefaultConstructorMarker)null), 1, (DefaultConstructorMarker)null);
         }

         var7.add(var11);
      }

      this.keyLearnList = (List)var7;
   }

   private final String getString(Integer var1) {
      String var3;
      if (var1 != null) {
         ((Number)var1).intValue();
         var3 = this.getString(var1);
      } else {
         var3 = null;
      }

      String var2 = var3;
      if (var3 == null) {
         var2 = "";
      }

      return var2;
   }

   private final void initView() {
      Context var3 = (Context)this;
      this.swcLongClickDialog = new SwcLongClickDialog(var3, (Function2)(new Function2(this) {
         final SwcActivity this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke(int var1, int var2) {
            Iterator var8 = ((Iterable)this.this$0.keyLearnList).iterator();

            boolean var3;
            byte var4;
            Object var6;
            Object var7;
            do {
               boolean var5 = var8.hasNext();
               var4 = 0;
               var7 = null;
               if (!var5) {
                  var6 = null;
                  break;
               }

               var6 = var8.next();
               if (((CustomKeyConfig.KeyMap)var6).getOutput().getShort() == var1) {
                  var3 = true;
               } else {
                  var3 = false;
               }
            } while(!var3);

            CustomKeyConfig.KeyMap var12 = (CustomKeyConfig.KeyMap)var6;
            if (var12 != null) {
               SwcActivity var9 = this.this$0;
               CustomKeyConfig.Key var10 = var12.getOutput();
               if (var12.getOutput().getLong() == ((KeyUiEntity)SwcActivity.Companion.getKeyUiList().get(var2)).getKey()) {
                  var1 = var4;
               } else {
                  var1 = ((KeyUiEntity)SwcActivity.Companion.getKeyUiList().get(var2)).getKey();
               }

               var10.setLong(var1);
               if (var12.getInput() != 0) {
                  var9.save();
               }

               if (var9.keyLearnList.indexOf(var12) == var9.learningPosition) {
                  var9.updateCurrentKey();
               }
            }

            RecyclerView var11 = this.this$0.rvKeys;
            if (var11 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
               var11 = (RecyclerView)var7;
            }

            RecyclerView.Adapter var13 = var11.getAdapter();
            if (var13 != null) {
               var13.notifyDataSetChanged();
            }

         }
      }));
      this.swcResetDialog = new SwcResetDialog(var3, (Function0)(new Function0(this) {
         final SwcActivity this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke() {
            this.this$0.reset();
         }
      }));
      View var4 = this.findViewById(2131363218);
      RecyclerView var2 = (RecyclerView)var4;
      byte var1;
      if (var2.getContext().getResources().getConfiguration().orientation == 1) {
         var1 = 4;
      } else {
         var1 = 7;
      }

      var2.setLayoutManager((RecyclerView.LayoutManager)(new GridLayoutManager(this, var1) {
         public boolean canScrollVertically() {
            return false;
         }
      }));
      var2.setAdapter((RecyclerView.Adapter)(new SwcKeyAdapter(var3, this.keyLearnList, (Function1)(new Function1(this) {
         final SwcActivity this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke(int var1) {
            this.this$0.learningPosition = var1;
            this.this$0.updateCurrentKey();
         }
      }), (Function1)(new Function1(this) {
         final SwcActivity this$0;

         {
            this.this$0 = var1;
         }

         public final void invoke(int var1) {
            int var2 = ((CustomKeyConfig.KeyMap)this.this$0.keyLearnList.get(var1)).getOutput().getShort();
            if (var2 != 7 && var2 != 8) {
               SwcLongClickDialog var4 = this.this$0.swcLongClickDialog;
               SwcLongClickDialog var3 = var4;
               if (var4 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("swcLongClickDialog");
                  var3 = null;
               }

               var3.show(var2, ((CustomKeyConfig.KeyMap)this.this$0.keyLearnList.get(var1)).getOutput().getLong());
            } else {
               ToastUtls.getToast((Context)this.this$0, 2131770072, 0).show();
            }

         }
      }))));
      Intrinsics.checkNotNullExpressionValue(var4, "findViewById<RecyclerVie…             })\n        }");
      this.rvKeys = var2;
      View var5 = this.findViewById(2131363610);
      Intrinsics.checkNotNullExpressionValue(var5, "findViewById(R.id.tv_current_key)");
      this.tvCurrentKey = (TextView)var5;
      this.updateCurrentKey();
      View var7 = this.findViewById(2131362052);
      Button var6 = (Button)var7;
      var6.setOnClickListener(new SwcActivity$$ExternalSyntheticLambda0(this));
      Intrinsics.checkNotNullExpressionValue(var7, "findViewById<Button>(R.i…ckListener { finish() } }");
      this.btnSave = var6;
      var5 = this.findViewById(2131362045);
      Button var8 = (Button)var5;
      var8.setOnClickListener(new SwcActivity$$ExternalSyntheticLambda1(this));
      Intrinsics.checkNotNullExpressionValue(var5, "findViewById<Button>(R.i…swcResetDialog.show() } }");
      this.btnReset = var8;
   }

   private static final void initView$lambda_4$lambda_3(SwcActivity var0, View var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.finish();
   }

   private static final void initView$lambda_6$lambda_5(SwcActivity var0, View var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      SwcResetDialog var3 = var0.swcResetDialog;
      SwcResetDialog var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("swcResetDialog");
         var2 = null;
      }

      var2.show();
   }

   private final void reset() {
      Iterator var2 = ((Iterable)this.keyLearnList).iterator();

      while(var2.hasNext()) {
         CustomKeyConfig.KeyMap var1 = (CustomKeyConfig.KeyMap)var2.next();
         var1.setInput(0);
         var1.getOutput().setLong(0);
      }

      RecyclerView var3 = this.rvKeys;
      var2 = null;
      RecyclerView var4 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
         var4 = null;
      }

      RecyclerView.Adapter var5 = var4.getAdapter();
      Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type com.hzbhd.canbus.adapter.SwcKeyAdapter");
      ((SwcKeyAdapter)var5).setSelected(0);
      this.learningPosition = 0;
      this.updateCurrentKey();
      var4 = this.rvKeys;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
         var4 = var2;
      }

      var5 = var4.getAdapter();
      if (var5 != null) {
         var5.notifyDataSetChanged();
      }

      this.save();
   }

   private final void save() {
      CustomKeyConfig var3 = CustomKeyConfig.INSTANCE;
      Iterable var4 = (Iterable)this.keyLearnList;
      Collection var2 = (Collection)(new ArrayList());
      Iterator var6 = var4.iterator();

      while(var6.hasNext()) {
         Object var5 = var6.next();
         boolean var1;
         if (((CustomKeyConfig.KeyMap)var5).getInput() != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            var2.add(var5);
         }
      }

      var3.setKeyList((List)var2);
   }

   private final void updateCurrentKey() {
      CustomKeyConfig.Key var5 = ((CustomKeyConfig.KeyMap)this.keyLearnList.get(this.learningPosition)).getOutput();
      TextView var3 = this.tvCurrentKey;
      Object var4 = null;
      TextView var2 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("tvCurrentKey");
         var2 = null;
      }

      StringBuilder var6 = new StringBuilder();
      Iterator var7 = ((Iterable)keyUiList).iterator();

      boolean var1;
      Object var9;
      do {
         if (!var7.hasNext()) {
            var9 = null;
            break;
         }

         var9 = var7.next();
         if (((KeyUiEntity)var9).getKey() == var5.getShort()) {
            var1 = true;
         } else {
            var1 = false;
         }
      } while(!var1);

      KeyUiEntity var10 = (KeyUiEntity)var9;
      Integer var11;
      if (var10 != null) {
         var11 = var10.getStringResId();
      } else {
         var11 = null;
      }

      var6 = var6.append(this.getString(var11));
      String var13;
      if (var5.getLong() == 0) {
         var13 = "";
      } else {
         StringBuilder var14 = (new StringBuilder()).append(" / ");
         Iterator var8 = ((Iterable)keyUiList).iterator();

         label45: {
            while(var8.hasNext()) {
               var9 = var8.next();
               if (((KeyUiEntity)var9).getKey() == var5.getLong()) {
                  var1 = true;
               } else {
                  var1 = false;
               }

               if (var1) {
                  break label45;
               }
            }

            var9 = null;
         }

         KeyUiEntity var12 = (KeyUiEntity)var9;
         var11 = (Integer)var4;
         if (var12 != null) {
            var11 = var12.getStringResId();
         }

         var13 = var14.append(this.getString(var11)).toString();
      }

      var2.setText((CharSequence)var6.append(var13).toString());
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

   public final void learnKey(int var1) {
      Iterator var6 = ((Iterable)this.keyLearnList).iterator();

      boolean var2;
      Object var4;
      Object var5;
      do {
         boolean var3 = var6.hasNext();
         var5 = null;
         if (!var3) {
            var4 = null;
            break;
         }

         var4 = var6.next();
         if (((CustomKeyConfig.KeyMap)var4).getInput() == var1) {
            var2 = true;
         } else {
            var2 = false;
         }
      } while(!var2);

      CustomKeyConfig.KeyMap var7 = (CustomKeyConfig.KeyMap)var4;
      Unit var8;
      if (var7 != null) {
         this.keyLearnList.indexOf(var7);
         var7.setInput(0);
         if (!var7.equals(this.keyLearnList.get(this.learningPosition))) {
            ((CustomKeyConfig.KeyMap)this.keyLearnList.get(this.learningPosition)).setInput(var1);
         }

         var8 = Unit.INSTANCE;
      } else {
         var8 = null;
      }

      if (var8 == null) {
         ((CustomKeyConfig.KeyMap)this.keyLearnList.get(this.learningPosition)).setInput(var1);
      }

      RecyclerView var9 = this.rvKeys;
      if (var9 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
         var9 = (RecyclerView)var5;
      }

      RecyclerView.Adapter var10 = var9.getAdapter();
      if (var10 != null) {
         var10.notifyDataSetChanged();
      }

      SendKeyManager.getInstance().playBeep(0);
      this.save();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558695);
      StringBuilder var3 = (new StringBuilder()).append("onCreate: ");
      Object[] var2 = ((Collection)this.keyLearnList).toArray(new CustomKeyConfig.KeyMap[0]);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
      String var4 = Arrays.toString(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "toString(this)");
      Log.i("KeyLearnActivity", var3.append(var4).toString());
      this.initView();
   }

   protected void onPause() {
      super.onPause();
      RealKeyUtil.setSwcActivity((SwcActivity)null);
   }

   protected void onResume() {
      super.onResume();
      RealKeyUtil.setSwcActivity(this);
   }

   @Metadata(
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"},
      d2 = {"Lcom/hzbhd/canbus/activity/SwcActivity$Companion;", "", "()V", "TAG", "", "keyUiList", "", "Lcom/hzbhd/canbus/activity/SwcActivity$KeyUiEntity;", "getKeyUiList", "()Ljava/util/List;", "CanBusInfo_release"},
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

      public final List getKeyUiList() {
         return SwcActivity.keyUiList;
      }
   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"},
      d2 = {"Lcom/hzbhd/canbus/activity/SwcActivity$KeyUiEntity;", "", "key", "", "drawableResId", "stringResId", "(III)V", "getDrawableResId", "()I", "getKey", "getStringResId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class KeyUiEntity {
      private final int drawableResId;
      private final int key;
      private final int stringResId;

      public KeyUiEntity(int var1, int var2, int var3) {
         this.key = var1;
         this.drawableResId = var2;
         this.stringResId = var3;
      }

      // $FF: synthetic method
      public static KeyUiEntity copy$default(KeyUiEntity var0, int var1, int var2, int var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = var0.key;
         }

         if ((var4 & 2) != 0) {
            var2 = var0.drawableResId;
         }

         if ((var4 & 4) != 0) {
            var3 = var0.stringResId;
         }

         return var0.copy(var1, var2, var3);
      }

      public final int component1() {
         return this.key;
      }

      public final int component2() {
         return this.drawableResId;
      }

      public final int component3() {
         return this.stringResId;
      }

      public final KeyUiEntity copy(int var1, int var2, int var3) {
         return new KeyUiEntity(var1, var2, var3);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof KeyUiEntity)) {
            return false;
         } else {
            KeyUiEntity var2 = (KeyUiEntity)var1;
            if (this.key != var2.key) {
               return false;
            } else if (this.drawableResId != var2.drawableResId) {
               return false;
            } else {
               return this.stringResId == var2.stringResId;
            }
         }
      }

      public final int getDrawableResId() {
         return this.drawableResId;
      }

      public final int getKey() {
         return this.key;
      }

      public final int getStringResId() {
         return this.stringResId;
      }

      public int hashCode() {
         return (Integer.hashCode(this.key) * 31 + Integer.hashCode(this.drawableResId)) * 31 + Integer.hashCode(this.stringResId);
      }

      public String toString() {
         return "KeyUiEntity(key=" + this.key + ", drawableResId=" + this.drawableResId + ", stringResId=" + this.stringResId + ')';
      }
   }
}
