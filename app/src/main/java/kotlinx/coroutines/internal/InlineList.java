package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0080@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0016\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0002HÖ\u0003J$\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\fH\u0086\b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0012\u001a\u00028\u0000H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0017"},
   d2 = {"Lkotlinx/coroutines/internal/InlineList;", "E", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "equals", "", "other", "forEachReversed", "", "action", "Lkotlin/Function1;", "forEachReversed-impl", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "hashCode", "", "plus", "element", "plus-UZ7vuAc", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class InlineList {
   private final Object holder;

   // $FF: synthetic method
   private InlineList(Object var1) {
      this.holder = var1;
   }

   // $FF: synthetic method
   public static final InlineList box_impl(Object var0) {
      return new InlineList(var0);
   }

   public static Object constructor_impl(Object var0) {
      return var0;
   }

   // $FF: synthetic method
   public static Object constructor_impl$default(Object var0, int var1, DefaultConstructorMarker var2) {
      if ((var1 & 1) != 0) {
         var0 = null;
      }

      return constructor_impl(var0);
   }

   public static boolean equals_impl(Object var0, Object var1) {
      return var1 instanceof InlineList && Intrinsics.areEqual(var0, ((InlineList)var1).unbox_impl());
   }

   public static final boolean equals_impl0(Object var0, Object var1) {
      return Intrinsics.areEqual(var0, var1);
   }

   public static final void forEachReversed_impl(Object var0, Function1 var1) {
      if (var0 != null) {
         if (!(var0 instanceof ArrayList)) {
            var1.invoke(var0);
         } else {
            if (var0 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
            }

            ArrayList var3 = (ArrayList)var0;

            for(int var2 = var3.size() - 1; var2 >= 0; --var2) {
               var1.invoke(var3.get(var2));
            }
         }

      }
   }

   public static int hashCode_impl(Object var0) {
      int var1;
      if (var0 != null) {
         var1 = var0.hashCode();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final Object plus_UZ7vuAc(Object var0, Object var1) {
      if (DebugKt.getASSERTIONS_ENABLED() && !(var1 instanceof List ^ true)) {
         throw (Throwable)(new AssertionError());
      } else {
         if (var0 == null) {
            var0 = constructor_impl(var1);
         } else if (var0 instanceof ArrayList) {
            if (var0 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
            }

            ((ArrayList)var0).add(var1);
            var0 = constructor_impl(var0);
         } else {
            ArrayList var2 = new ArrayList(4);
            var2.add(var0);
            var2.add(var1);
            var0 = constructor_impl(var2);
         }

         return var0;
      }
   }

   public static String toString_impl(Object var0) {
      return "InlineList(holder=" + var0 + ")";
   }

   public boolean equals(Object var1) {
      return equals_impl(this.holder, var1);
   }

   public int hashCode() {
      return hashCode_impl(this.holder);
   }

   public String toString() {
      return toString_impl(this.holder);
   }

   // $FF: synthetic method
   public final Object unbox_impl() {
      return this.holder;
   }
}
