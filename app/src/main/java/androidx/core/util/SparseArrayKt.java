package androidx.core.util;

import android.util.SparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0000\u001a!\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a!\u0010\t\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\u0001H\u0086\b\u001a&\u0010\n\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u000b\u001a\u0002H\u0002H\u0086\b¢\u0006\u0002\u0010\f\u001aQ\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000326\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0086\b\u001a.\u0010\u0013\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002H\u0002H\u0086\b¢\u0006\u0002\u0010\u0015\u001a4\u0010\u0016\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0017H\u0086\b¢\u0006\u0002\u0010\u0018\u001a\u0019\u0010\u0019\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\b\u001a\u0019\u0010\u001a\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\b\u001a\u0016\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a-\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002\u001a$\u0010\u001f\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a+\u0010 \u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u0002H\u0002¢\u0006\u0002\u0010!\u001a.\u0010\"\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u0002H\u0002H\u0086\n¢\u0006\u0002\u0010#\u001a\u001c\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020%\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\"\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006&"},
   d2 = {"size", "", "T", "Landroid/util/SparseArray;", "getSize", "(Landroid/util/SparseArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "(Landroid/util/SparseArray;Ljava/lang/Object;)Z", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Landroid/util/SparseArray;ILjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroid/util/SparseArray;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "(Landroid/util/SparseArray;ILjava/lang/Object;)Z", "set", "(Landroid/util/SparseArray;ILjava/lang/Object;)V", "valueIterator", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SparseArrayKt {
   public static final boolean contains(SparseArray var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      boolean var2;
      if (var0.indexOfKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean containsKey(SparseArray var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsKey");
      boolean var2;
      if (var0.indexOfKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean containsValue(SparseArray var0, Object var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsValue");
      boolean var2;
      if (var0.indexOfValue(var1) != -1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final void forEach(SparseArray var0, Function2 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEach");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var1.invoke(var0.keyAt(var2), var0.valueAt(var2));
      }

   }

   public static final Object getOrDefault(SparseArray var0, int var1, Object var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrDefault");
      Object var3 = var0.get(var1);
      if (var3 != null) {
         var2 = var3;
      }

      return var2;
   }

   public static final Object getOrElse(SparseArray var0, int var1, Function0 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrElse");
      Intrinsics.checkParameterIsNotNull(var2, "defaultValue");
      Object var3 = var0.get(var1);
      if (var3 == null) {
         var3 = var2.invoke();
      }

      return var3;
   }

   public static final int getSize(SparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$size");
      return var0.size();
   }

   public static final boolean isEmpty(SparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isEmpty");
      boolean var1;
      if (var0.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isNotEmpty(SparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isNotEmpty");
      boolean var1;
      if (var0.size() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final IntIterator keyIterator(SparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$keyIterator");
      return (IntIterator)(new IntIterator(var0) {
         final SparseArray $this_keyIterator;
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
            SparseArray var2 = this.$this_keyIterator;
            int var1 = this.index++;
            return var2.keyAt(var1);
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }

   public static final SparseArray plus(SparseArray var0, SparseArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      SparseArray var2 = new SparseArray(var0.size() + var1.size());
      putAll(var2, var0);
      putAll(var2, var1);
      return var2;
   }

   public static final void putAll(SparseArray var0, SparseArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$putAll");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var0.put(var1.keyAt(var2), var1.valueAt(var2));
      }

   }

   public static final boolean remove(SparseArray var0, int var1, Object var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$remove");
      var1 = var0.indexOfKey(var1);
      if (var1 != -1 && Intrinsics.areEqual(var2, var0.valueAt(var1))) {
         var0.removeAt(var1);
         return true;
      } else {
         return false;
      }
   }

   public static final void set(SparseArray var0, int var1, Object var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$set");
      var0.put(var1, var2);
   }

   public static final Iterator valueIterator(SparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$valueIterator");
      return (Iterator)(new Iterator(var0) {
         final SparseArray $this_valueIterator;
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

         public Object next() {
            SparseArray var2 = this.$this_valueIterator;
            int var1 = this.index++;
            return var2.valueAt(var1);
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }
}
