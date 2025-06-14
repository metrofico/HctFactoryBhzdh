package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001a@\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0087\b\u001a\u001c\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a-\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001a@\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"},
   d2 = {"check", "", "value", "", "lazyMessage", "Lkotlin/Function0;", "", "checkNotNull", "T", "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "error", "", "message", "require", "requireNotNull", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/PreconditionsKt"
)
class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
   public PreconditionsKt__PreconditionsKt() {
   }

   private static final void check(boolean var0) {
      if (!var0) {
         throw new IllegalStateException("Check failed.".toString());
      }
   }

   private static final void check(boolean var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "lazyMessage");
      if (!var0) {
         throw new IllegalStateException(var1.invoke().toString());
      }
   }

   private static final Object checkNotNull(Object var0) {
      if (var0 != null) {
         return var0;
      } else {
         throw new IllegalStateException("Required value was null.".toString());
      }
   }

   private static final Object checkNotNull(Object var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "lazyMessage");
      if (var0 != null) {
         return var0;
      } else {
         throw new IllegalStateException(var1.invoke().toString());
      }
   }

   private static final Void error(Object var0) {
      Intrinsics.checkNotNullParameter(var0, "message");
      throw new IllegalStateException(var0.toString());
   }

   private static final void require(boolean var0) {
      if (!var0) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      }
   }

   private static final void require(boolean var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "lazyMessage");
      if (!var0) {
         throw new IllegalArgumentException(var1.invoke().toString());
      }
   }

   private static final Object requireNotNull(Object var0) {
      if (var0 != null) {
         return var0;
      } else {
         throw new IllegalArgumentException("Required value was null.".toString());
      }
   }

   private static final Object requireNotNull(Object var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "lazyMessage");
      if (var0 != null) {
         return var0;
      } else {
         throw new IllegalArgumentException(var1.invoke().toString());
      }
   }
}
