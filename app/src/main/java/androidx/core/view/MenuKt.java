package androidx.core.view;

import android.view.Menu;
import android.view.MenuItem;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010)\n\u0002\b\u0002\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0086\u0002\u001a0\u0010\r\u001a\u00020\u000e*\u00020\u00032!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0010H\u0086\b\u001aE\u0010\u0013\u001a\u00020\u000e*\u00020\u000326\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0014H\u0086\b\u001a\u0015\u0010\u0016\u001a\u00020\u0002*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H\u0086\n\u001a\r\u0010\u0017\u001a\u00020\u000b*\u00020\u0003H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u000b*\u00020\u0003H\u0086\b\u001a\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a*\u00020\u0003H\u0086\u0002\u001a\u0015\u0010\u001b\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0086\n\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001c"},
   d2 = {"children", "Lkotlin/sequences/Sequence;", "Landroid/view/MenuItem;", "Landroid/view/Menu;", "getChildren", "(Landroid/view/Menu;)Lkotlin/sequences/Sequence;", "size", "", "getSize", "(Landroid/view/Menu;)I", "contains", "", "item", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function2;", "index", "get", "isEmpty", "isNotEmpty", "iterator", "", "minusAssign", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class MenuKt {
   public static final boolean contains(Menu var0, MenuItem var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      Intrinsics.checkParameterIsNotNull(var1, "item");
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         if (Intrinsics.areEqual((Object)var0.getItem(var2), (Object)var1)) {
            return true;
         }
      }

      return false;
   }

   public static final void forEach(Menu var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEach");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         MenuItem var4 = var0.getItem(var2);
         Intrinsics.checkExpressionValueIsNotNull(var4, "getItem(index)");
         var1.invoke(var4);
      }

   }

   public static final void forEachIndexed(Menu var0, Function2 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEachIndexed");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         MenuItem var4 = var0.getItem(var2);
         Intrinsics.checkExpressionValueIsNotNull(var4, "getItem(index)");
         var1.invoke(var2, var4);
      }

   }

   public static final MenuItem get(Menu var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$get");
      MenuItem var2 = var0.getItem(var1);
      Intrinsics.checkExpressionValueIsNotNull(var2, "getItem(index)");
      return var2;
   }

   public static final Sequence getChildren(Menu var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$children");
      return (Sequence)(new Sequence(var0) {
         final Menu $this_children;

         {
            this.$this_children = var1;
         }

         public Iterator iterator() {
            return MenuKt.iterator(this.$this_children);
         }
      });
   }

   public static final int getSize(Menu var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$size");
      return var0.size();
   }

   public static final boolean isEmpty(Menu var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isEmpty");
      boolean var1;
      if (var0.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isNotEmpty(Menu var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isNotEmpty");
      boolean var1;
      if (var0.size() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final Iterator iterator(Menu var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$iterator");
      return (Iterator)(new Iterator(var0) {
         final Menu $this_iterator;
         private int index;

         {
            this.$this_iterator = var1;
         }

         public boolean hasNext() {
            boolean var1;
            if (this.index < this.$this_iterator.size()) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public MenuItem next() {
            Menu var2 = this.$this_iterator;
            int var1 = this.index++;
            MenuItem var3 = var2.getItem(var1);
            if (var3 != null) {
               return var3;
            } else {
               throw (Throwable)(new IndexOutOfBoundsException());
            }
         }

         public void remove() {
            Menu var2 = this.$this_iterator;
            int var1 = this.index - 1;
            this.index = var1;
            var2.removeItem(var1);
         }
      });
   }

   public static final void minusAssign(Menu var0, MenuItem var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minusAssign");
      Intrinsics.checkParameterIsNotNull(var1, "item");
      var0.removeItem(var1.getItemId());
   }
}
