package androidx.core.util;

import android.util.SparseBooleanArray;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0086\b\u001aE\u0010\u000b\u001a\u00020\f*\u00020\u000226\u0010\r\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\f0\u000eH\u0086\b\u001a\u001d\u0010\u0011\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0006H\u0086\b\u001a#\u0010\u0013\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H\u0086\b\u001a\r\u0010\u0015\u001a\u00020\u0006*\u00020\u0002H\u0086\b\u001a\r\u0010\u0016\u001a\u00020\u0006*\u00020\u0002H\u0086\b\u001a\n\u0010\u0017\u001a\u00020\u0018*\u00020\u0002\u001a\u0015\u0010\u0019\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0086\u0002\u001a\u0012\u0010\u001b\u001a\u00020\f*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002\u001a\u001a\u0010\u001c\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0006\u001a\u001d\u0010\u001d\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0006H\u0086\n\u001a\n\u0010\u001e\u001a\u00020\u001f*\u00020\u0002\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006 "},
   d2 = {"size", "", "Landroid/util/SparseBooleanArray;", "getSize", "(Landroid/util/SparseBooleanArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "Lkotlin/collections/BooleanIterator;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SparseBooleanArrayKt {
   public static final boolean contains(SparseBooleanArray var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      boolean var2;
      if (var0.indexOfKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean containsKey(SparseBooleanArray var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsKey");
      boolean var2;
      if (var0.indexOfKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean containsValue(SparseBooleanArray var0, boolean var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsValue");
      if (var0.indexOfValue(var1) != -1) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final void forEach(SparseBooleanArray var0, Function2 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEach");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var1.invoke(var0.keyAt(var2), var0.valueAt(var2));
      }

   }

   public static final boolean getOrDefault(SparseBooleanArray var0, int var1, boolean var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrDefault");
      return var0.get(var1, var2);
   }

   public static final boolean getOrElse(SparseBooleanArray var0, int var1, Function0 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrElse");
      Intrinsics.checkParameterIsNotNull(var2, "defaultValue");
      var1 = var0.indexOfKey(var1);
      boolean var3;
      if (var1 != -1) {
         var3 = var0.valueAt(var1);
      } else {
         var3 = (Boolean)var2.invoke();
      }

      return var3;
   }

   public static final int getSize(SparseBooleanArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$size");
      return var0.size();
   }

   public static final boolean isEmpty(SparseBooleanArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isEmpty");
      boolean var1;
      if (var0.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isNotEmpty(SparseBooleanArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isNotEmpty");
      boolean var1;
      if (var0.size() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final IntIterator keyIterator(SparseBooleanArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$keyIterator");
      return (IntIterator)(new IntIterator(var0) {
         final SparseBooleanArray $this_keyIterator;
         private int index;

         {
            this.$this_keyIterator = var1;
         }

         public final int getIndex() {
            return this.index;
         }

         public boolean hasNext() {
            boolean var1;
            if (this.index < this.$this_keyIterator.size()) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public int nextInt() {
            SparseBooleanArray var2 = this.$this_keyIterator;
            int var1 = this.index++;
            return var2.keyAt(var1);
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }

   public static final SparseBooleanArray plus(SparseBooleanArray var0, SparseBooleanArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      SparseBooleanArray var2 = new SparseBooleanArray(var0.size() + var1.size());
      putAll(var2, var0);
      putAll(var2, var1);
      return var2;
   }

   public static final void putAll(SparseBooleanArray var0, SparseBooleanArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$putAll");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var0.put(var1.keyAt(var2), var1.valueAt(var2));
      }

   }

   public static final boolean remove(SparseBooleanArray var0, int var1, boolean var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$remove");
      int var3 = var0.indexOfKey(var1);
      if (var3 != -1 && var2 == var0.valueAt(var3)) {
         var0.delete(var1);
         return true;
      } else {
         return false;
      }
   }

   public static final void set(SparseBooleanArray var0, int var1, boolean var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$set");
      var0.put(var1, var2);
   }

   public static final BooleanIterator valueIterator(SparseBooleanArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$valueIterator");
      return (BooleanIterator)(new BooleanIterator(var0) {
         final SparseBooleanArray $this_valueIterator;
         private int index;

         {
            this.$this_valueIterator = var1;
         }

         public final int getIndex() {
            return this.index;
         }

         public boolean hasNext() {
            boolean var1;
            if (this.index < this.$this_valueIterator.size()) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public boolean nextBoolean() {
            SparseBooleanArray var2 = this.$this_valueIterator;
            int var1 = this.index++;
            return var2.valueAt(var1);
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }
}
