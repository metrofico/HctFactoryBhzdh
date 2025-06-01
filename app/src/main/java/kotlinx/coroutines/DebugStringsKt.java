package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\bH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\t"},
   d2 = {"classSimpleName", "", "", "getClassSimpleName", "(Ljava/lang/Object;)Ljava/lang/String;", "hexAddress", "getHexAddress", "toDebugString", "Lkotlin/coroutines/Continuation;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class DebugStringsKt {
   public static final String getClassSimpleName(Object var0) {
      return var0.getClass().getSimpleName();
   }

   public static final String getHexAddress(Object var0) {
      return Integer.toHexString(System.identityHashCode(var0));
   }

   public static final String toDebugString(Continuation var0) {
      String var5;
      if (var0 instanceof DispatchedContinuation) {
         var5 = var0.toString();
      } else {
         Result.Companion var1;
         Object var6;
         label38:
         try {
            var1 = Result.Companion;
            StringBuilder var7 = new StringBuilder();
            var6 = Result.constructor_impl(var7.append(var0).append('@').append(getHexAddress(var0)).toString());
         } catch (Throwable var4) {
            var1 = Result.Companion;
            var6 = Result.constructor_impl(ResultKt.createFailure(var4));
            break label38;
         }

         if (Result.exceptionOrNull_impl(var6) != null) {
            var6 = var0.getClass().getName() + '@' + getHexAddress(var0);
         }

         var5 = (String)var6;
      }

      return var5;
   }
}
