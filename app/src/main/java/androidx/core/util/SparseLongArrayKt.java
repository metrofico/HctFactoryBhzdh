package androidx.core.util;

import android.util.SparseLongArray;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001aE\u0010\f\u001a\u00020\r*\u00020\u000226\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\r0\u000fH\u0087\b\u001a\u001d\u0010\u0012\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u000bH\u0087\b\u001a#\u0010\u0014\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015H\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u0006*\u00020\u0002H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0006*\u00020\u0002H\u0087\b\u001a\f\u0010\u0018\u001a\u00020\u0019*\u00020\u0002H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0087\u0002\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0007\u001a\u001c\u0010\u001d\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\u001d\u0010\u001e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0087\n\u001a\f\u0010\u001f\u001a\u00020 *\u00020\u0002H\u0007\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006!"},
   d2 = {"size", "", "Landroid/util/SparseLongArray;", "getSize", "(Landroid/util/SparseLongArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "Lkotlin/collections/LongIterator;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SparseLongArrayKt {
   public static final boolean contains(SparseLongArray var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      boolean var2;
      if (var0.indexOfKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean containsKey(SparseLongArray var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsKey");
      boolean var2;
      if (var0.indexOfKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean containsValue(SparseLongArray var0, long var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsValue");
      boolean var3;
      if (var0.indexOfValue(var1) != -1) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static final void forEach(SparseLongArray var0, Function2 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEach");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var1.invoke(var0.keyAt(var2), var0.valueAt(var2));
      }

   }

   public static final long getOrDefault(SparseLongArray var0, int var1, long var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrDefault");
      return var0.get(var1, var2);
   }

   public static final long getOrElse(SparseLongArray var0, int var1, Function0 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrElse");
      Intrinsics.checkParameterIsNotNull(var2, "defaultValue");
      var1 = var0.indexOfKey(var1);
      long var3;
      if (var1 != -1) {
         var3 = var0.valueAt(var1);
      } else {
         var3 = ((Number)var2.invoke()).longValue();
      }

      return var3;
   }

   public static final int getSize(SparseLongArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$size");
      return var0.size();
   }

   public static final boolean isEmpty(SparseLongArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isEmpty");
      boolean var1;
      if (var0.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isNotEmpty(SparseLongArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isNotEmpty");
      boolean var1;
      if (var0.size() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final IntIterator keyIterator(SparseLongArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$keyIterator");
      return (IntIterator)(new IntIterator(var0) {
         final SparseLongArray $this_keyIterator;
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
            SparseLongArray var2 = this.$this_keyIterator;
            int var1 = this.index++;
            return var2.keyAt(var1);
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }

   public static final SparseLongArray plus(SparseLongArray var0, SparseLongArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      SparseLongArray var2 = new SparseLongArray(var0.size() + var1.size());
      putAll(var2, var0);
      putAll(var2, var1);
      return var2;
   }

   public static final void putAll(SparseLongArray var0, SparseLongArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$putAll");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var0.put(var1.keyAt(var2), var1.valueAt(var2));
      }

   }

   public static final boolean remove(SparseLongArray var0, int var1, long var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$remove");
      var1 = var0.indexOfKey(var1);
      if (var1 != -1 && var2 == var0.valueAt(var1)) {
         var0.removeAt(var1);
         return true;
      } else {
         return false;
      }
   }

   public static final void set(SparseLongArray var0, int var1, long var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$set");
      var0.put(var1, var2);
   }

   public static final LongIterator valueIterator(SparseLongArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$valueIterator");
      return (LongIterator)(new LongIterator(var0) {
         final SparseLongArray $this_valueIterator;
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

         public long nextLong() {
            SparseLongArray var2 = this.$this_valueIterator;
            int var1 = this.index++;
            return var2.valueAt(var1);
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }
}
