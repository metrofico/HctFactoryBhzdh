package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.selects.SelectClause0;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u0000 (2\u00020\u0001:\u0001(J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H'J\b\u0010\u0013\u001a\u00020\u0014H\u0017J\u0014\u0010\u0013\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H'J\u001a\u0010\u0013\u001a\u00020\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0018\u00010\u0017j\u0004\u0018\u0001`\u0018H&J\f\u0010\u0019\u001a\u00060\u0017j\u0002`\u0018H'JE\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00072'\u0010\u001e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00140\u001fj\u0002`\"H'J1\u0010\u001a\u001a\u00020\u001b2'\u0010\u001e\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00140\u001fj\u0002`\"H&J\u0011\u0010#\u001a\u00020\u0014H¦@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0011\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0097\u0002J\b\u0010'\u001a\u00020\u0007H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"},
   d2 = {"Lkotlinx/coroutines/Job;", "Lkotlin/coroutines/CoroutineContext$Element;", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "isActive", "", "()Z", "isCancelled", "isCompleted", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getCancellationException", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "plus", "other", "start", "Key", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface Job extends CoroutineContext.Element {
   Key Key = Job.Key.$$INSTANCE;

   ChildHandle attachChild(ChildJob var1);

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   void cancel();

   void cancel(CancellationException var1);

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   boolean cancel(Throwable var1);

   CancellationException getCancellationException();

   Sequence getChildren();

   SelectClause0 getOnJoin();

   DisposableHandle invokeOnCompletion(Function1 var1);

   DisposableHandle invokeOnCompletion(boolean var1, boolean var2, Function1 var3);

   boolean isActive();

   boolean isCancelled();

   boolean isCompleted();

   Object join(Continuation var1);

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`."
   )
   Job plus(Job var1);

   boolean start();

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      @Deprecated(
         level = DeprecationLevel.HIDDEN,
         message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
      )
      public static void cancel(Job var0) {
         var0.cancel((CancellationException)null);
      }

      // $FF: synthetic method
      public static void cancel$default(Job var0, CancellationException var1, int var2, Object var3) {
         if (var3 == null) {
            if ((var2 & 1) != 0) {
               var1 = null;
               CancellationException var4 = (CancellationException)null;
            }

            var0.cancel(var1);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
         }
      }

      // $FF: synthetic method
      public static boolean cancel$default(Job var0, Throwable var1, int var2, Object var3) {
         if (var3 == null) {
            if ((var2 & 1) != 0) {
               var1 = null;
               Throwable var4 = (Throwable)null;
            }

            return var0.cancel(var1);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
         }
      }

      public static Object fold(Job var0, Object var1, Function2 var2) {
         return Element.DefaultImpls.fold((Element)var0, var1, var2);
      }

      public static Element get(Job var0, CoroutineContext.Key var1) {
         return Element.DefaultImpls.get((Element)var0, var1);
      }

      // $FF: synthetic method
      public static DisposableHandle invokeOnCompletion$default(Job var0, boolean var1, boolean var2, Function1 var3, int var4, Object var5) {
         if (var5 == null) {
            if ((var4 & 1) != 0) {
               var1 = false;
            }

            if ((var4 & 2) != 0) {
               var2 = true;
            }

            return var0.invokeOnCompletion(var1, var2, var3);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invokeOnCompletion");
         }
      }

      public static CoroutineContext minusKey(Job var0, CoroutineContext.Key var1) {
         return Element.DefaultImpls.minusKey((Element)var0, var1);
      }

      public static CoroutineContext plus(Job var0, CoroutineContext var1) {
         return Element.DefaultImpls.plus((Element)var0, var1);
      }

      @Deprecated(
         level = DeprecationLevel.ERROR,
         message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`."
      )
      public static Job plus(Job var0, Job var1) {
         return var1;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"},
      d2 = {"Lkotlinx/coroutines/Job$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/Job;", "()V", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Key implements CoroutineContext.Key {
      static final Key $$INSTANCE = new Key();

      static {
         CoroutineExceptionHandler.Key var0 = CoroutineExceptionHandler.Key;
      }

      private Key() {
      }
   }
}
