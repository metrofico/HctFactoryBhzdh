package androidx.core.util;

import android.util.LongSparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0000\u001a!\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0087\n\u001a!\u0010\n\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0087\b\u001a&\u0010\u000b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\f\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\r\u001aQ\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000326\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000f0\u0011H\u0087\b\u001a.\u0010\u0014\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0016\u001a4\u0010\u0017\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0018H\u0087\b¢\u0006\u0002\u0010\u0019\u001a\u0019\u0010\u001a\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a\u0019\u0010\u001b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a\u0018\u0010\u001c\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a-\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\u0002\u001a&\u0010 \u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a-\u0010!\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\"\u001a.\u0010#\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010$\u001a\u001e\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00020&\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\"\"\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006'"},
   d2 = {"size", "", "T", "Landroid/util/LongSparseArray;", "getSize", "(Landroid/util/LongSparseArray;)I", "contains", "", "key", "", "containsKey", "containsValue", "value", "(Landroid/util/LongSparseArray;Ljava/lang/Object;)Z", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroid/util/LongSparseArray;JLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/LongIterator;", "plus", "other", "putAll", "remove", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Z", "set", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)V", "valueIterator", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class LongSparseArrayKt {
   public static final boolean contains(LongSparseArray var0, long var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      boolean var3;
      if (var0.indexOfKey(var1) >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static final boolean containsKey(LongSparseArray var0, long var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsKey");
      boolean var3;
      if (var0.indexOfKey(var1) >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static final boolean containsValue(LongSparseArray var0, Object var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$containsValue");
      boolean var2;
      if (var0.indexOfValue(var1) != -1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final void forEach(LongSparseArray var0, Function2 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEach");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var1.invoke(var0.keyAt(var2), var0.valueAt(var2));
      }

   }

   public static final Object getOrDefault(LongSparseArray var0, long var1, Object var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrDefault");
      Object var4 = var0.get(var1);
      if (var4 != null) {
         var3 = var4;
      }

      return var3;
   }

   public static final Object getOrElse(LongSparseArray var0, long var1, Function0 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getOrElse");
      Intrinsics.checkParameterIsNotNull(var3, "defaultValue");
      Object var4 = var0.get(var1);
      if (var4 == null) {
         var4 = var3.invoke();
      }

      return var4;
   }

   public static final int getSize(LongSparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$size");
      return var0.size();
   }

   public static final boolean isEmpty(LongSparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isEmpty");
      boolean var1;
      if (var0.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isNotEmpty(LongSparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isNotEmpty");
      boolean var1;
      if (var0.size() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final LongIterator keyIterator(LongSparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$keyIterator");
      return (LongIterator)(new LongIterator(var0) {
         final LongSparseArray $this_keyIterator;
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

         public long nextLong() {
            LongSparseArray var2 = this.$this_keyIterator;
            int var1 = this.index++;
            return var2.keyAt(var1);
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }

   public static final LongSparseArray plus(LongSparseArray var0, LongSparseArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      LongSparseArray var2 = new LongSparseArray(var0.size() + var1.size());
      putAll(var2, var0);
      putAll(var2, var1);
      return var2;
   }

   public static final void putAll(LongSparseArray var0, LongSparseArray var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$putAll");
      Intrinsics.checkParameterIsNotNull(var1, "other");
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         var0.put(var1.keyAt(var2), var1.valueAt(var2));
      }

   }

   public static final boolean remove(LongSparseArray var0, long var1, Object var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$remove");
      int var4 = var0.indexOfKey(var1);
      if (var4 != -1 && Intrinsics.areEqual(var3, var0.valueAt(var4))) {
         var0.removeAt(var4);
         return true;
      } else {
         return false;
      }
   }

   public static final void set(LongSparseArray var0, long var1, Object var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$set");
      var0.put(var1, var3);
   }

   public static final Iterator valueIterator(LongSparseArray var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$valueIterator");
      return (Iterator)(new Iterator(var0) {
         final LongSparseArray $this_valueIterator;
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
            LongSparseArray var2 = this.$this_valueIterator;
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
