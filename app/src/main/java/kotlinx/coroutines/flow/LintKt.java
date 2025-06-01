package kotlinx.coroutines.flow;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.CoroutineContext;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\"\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u00022\u0010\b\u0002\u0010\r\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fH\u0007\u001a\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0007\u001a\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0007\u001a\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0007\u001a&\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010\u0018\u001a\u00020\u0001H\u0007\"\"\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\"\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\u0007\u0010\n¨\u0006\u0019"},
   d2 = {"coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/flow/FlowCollector;", "getCoroutineContext$annotations", "(Lkotlinx/coroutines/flow/FlowCollector;)V", "getCoroutineContext", "(Lkotlinx/coroutines/flow/FlowCollector;)Lkotlin/coroutines/CoroutineContext;", "isActive", "", "isActive$annotations", "(Lkotlinx/coroutines/flow/FlowCollector;)Z", "cancel", "", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancellable", "Lkotlinx/coroutines/flow/Flow;", "T", "Lkotlinx/coroutines/flow/SharedFlow;", "conflate", "Lkotlinx/coroutines/flow/StateFlow;", "distinctUntilChanged", "flowOn", "context", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class LintKt {
   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "cancel() is resolved into the extension of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext().cancel() instead or specify the receiver of cancel() explicitly",
      replaceWith = @ReplaceWith(
   expression = "currentCoroutineContext().cancel(cause)",
   imports = {}
)
   )
   public static final void cancel(FlowCollector var0, CancellationException var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   // $FF: synthetic method
   public static void cancel$default(FlowCollector var0, CancellationException var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         CancellationException var4 = (CancellationException)null;
      }

      cancel(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Applying 'cancellable' to a SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.",
      replaceWith = @ReplaceWith(
   expression = "this",
   imports = {}
)
   )
   public static final Flow cancellable(SharedFlow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Applying 'conflate' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.",
      replaceWith = @ReplaceWith(
   expression = "this",
   imports = {}
)
   )
   public static final Flow conflate(StateFlow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Applying 'distinctUntilChanged' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.",
      replaceWith = @ReplaceWith(
   expression = "this",
   imports = {}
)
   )
   public static final Flow distinctUntilChanged(StateFlow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Applying 'flowOn' to SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.",
      replaceWith = @ReplaceWith(
   expression = "this",
   imports = {}
)
   )
   public static final Flow flowOn(SharedFlow var0, CoroutineContext var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   public static final CoroutineContext getCoroutineContext(FlowCollector var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "coroutineContext is resolved into the property of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext() instead or specify the receiver of coroutineContext explicitly",
      replaceWith = @ReplaceWith(
   expression = "currentCoroutineContext()",
   imports = {}
)
   )
   public static void getCoroutineContext$annotations(FlowCollector var0) {
   }

   public static final boolean isActive(FlowCollector var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "isActive is resolved into the extension of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext().isActive or cancellable() operator instead or specify the receiver of isActive explicitly. Additionally, flow {} builder emissions are cancellable by default.",
      replaceWith = @ReplaceWith(
   expression = "currentCoroutineContext().isActive",
   imports = {}
)
   )
   public static void isActive$annotations(FlowCollector var0) {
   }
}
