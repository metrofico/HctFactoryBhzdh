package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"},
   d2 = {"synchronized", "R", "lock", "", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/StandardKt"
)
class StandardKt__SynchronizedKt extends StandardKt__StandardKt {
   public StandardKt__SynchronizedKt() {
   }

   private static final Object synchronized(Object var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "lock");
      Intrinsics.checkNotNullParameter(var1, "block");
      synchronized(var0){}

      Object var4;
      try {
         var4 = var1.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         InlineMarker.finallyEnd(1);
      }

      return var4;
   }
}
