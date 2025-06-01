package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¨\u0006\t"},
   d2 = {"lazy", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "lock", "", "mode", "Lkotlin/LazyThreadSafetyMode;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/LazyKt"
)
class LazyKt__LazyJVMKt {
   public LazyKt__LazyJVMKt() {
   }

   public static final Lazy lazy(Object var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "initializer");
      return (Lazy)(new SynchronizedLazyImpl(var1, var0));
   }

   public static final Lazy lazy(LazyThreadSafetyMode var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "mode");
      Intrinsics.checkNotNullParameter(var1, "initializer");
      int var2 = WhenMappings.$EnumSwitchMapping$0[var0.ordinal()];
      Lazy var3;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               throw new NoWhenBranchMatchedException();
            }

            var3 = (Lazy)(new UnsafeLazyImpl(var1));
         } else {
            var3 = (Lazy)(new SafePublicationLazyImpl(var1));
         }
      } else {
         var3 = (Lazy)(new SynchronizedLazyImpl(var1, (Object)null, 2, (DefaultConstructorMarker)null));
      }

      return var3;
   }

   public static final Lazy lazy(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "initializer");
      return (Lazy)(new SynchronizedLazyImpl(var0, (Object)null, 2, (DefaultConstructorMarker)null));
   }

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class WhenMappings {
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[LazyThreadSafetyMode.values().length];
         var0[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
         var0[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
         var0[LazyThreadSafetyMode.NONE.ordinal()] = 3;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
