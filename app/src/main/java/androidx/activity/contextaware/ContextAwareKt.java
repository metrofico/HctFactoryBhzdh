package androidx.activity.contextaware;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00010\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"},
   d2 = {"withContextAvailable", "R", "Landroidx/activity/contextaware/ContextAware;", "onContextAvailable", "Lkotlin/Function1;", "Landroid/content/Context;", "(Landroidx/activity/contextaware/ContextAware;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "activity-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class ContextAwareKt {
   public static final Object withContextAvailable(ContextAware var0, Function1 var1, Continuation var2) {
      Context var3 = var0.peekAvailableContext();
      Object var6;
      if (var3 != null) {
         var6 = var1.invoke(var3);
      } else {
         CancellableContinuationImpl var5 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var2), 1);
         var5.initCancellability();
         CancellableContinuation var8 = (CancellableContinuation)var5;
         OnContextAvailableListener var4 = new OnContextAvailableListener(var8, var0, var1) {
            final CancellableContinuation $co;
            final Function1 $onContextAvailable$inlined;
            final ContextAware $this_withContextAvailable$inlined;

            public {
               this.$co = var1;
               this.$this_withContextAvailable$inlined = var2;
               this.$onContextAvailable$inlined = var3;
            }

            public void onContextAvailable(Context var1) {
               Intrinsics.checkNotNullParameter(var1, "context");
               CancellableContinuation var2 = this.$co;

               Result.Companion var3;
               Object var6;
               label24:
               try {
                  var3 = Result.Companion;
                  <undefinedtype> var7 = (<undefinedtype>)this;
                  var6 = Result.constructor_impl(this.$onContextAvailable$inlined.invoke(var1));
               } catch (Throwable var5) {
                  var3 = Result.Companion;
                  var6 = Result.constructor_impl(ResultKt.createFailure(var5));
                  break label24;
               }

               var2.resumeWith(var6);
            }
         };
         var0.addOnContextAvailableListener((OnContextAvailableListener)var4);
         var8.invokeOnCancellation((Function1)(new Function1(var4, var0, var1) {
            final <undefinedtype> $listener;
            final Function1 $onContextAvailable$inlined;
            final ContextAware $this_withContextAvailable$inlined;

            public {
               this.$listener = var1;
               this.$this_withContextAvailable$inlined = var2;
               this.$onContextAvailable$inlined = var3;
            }

            public final void invoke(Throwable var1) {
               this.$this_withContextAvailable$inlined.removeOnContextAvailableListener((OnContextAvailableListener)this.$listener);
            }
         }));
         Object var7 = var5.getResult();
         var6 = var7;
         if (var7 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var2);
            var6 = var7;
         }
      }

      return var6;
   }

   private static final Object withContextAvailable$$forInline(ContextAware var0, Function1 var1, Continuation var2) {
      Context var3 = var0.peekAvailableContext();
      Object var6;
      if (var3 != null) {
         var6 = var1.invoke(var3);
      } else {
         InlineMarker.mark(0);
         CancellableContinuationImpl var7 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var2), 1);
         var7.initCancellability();
         CancellableContinuation var5 = (CancellableContinuation)var7;
         OnContextAvailableListener var4 = new OnContextAvailableListener(var5, var0, var1) {
            final CancellableContinuation $co;
            final Function1 $onContextAvailable$inlined;
            final ContextAware $this_withContextAvailable$inlined;

            public {
               this.$co = var1;
               this.$this_withContextAvailable$inlined = var2;
               this.$onContextAvailable$inlined = var3;
            }

            public void onContextAvailable(Context var1) {
               Intrinsics.checkNotNullParameter(var1, "context");
               CancellableContinuation var2 = this.$co;

               Result.Companion var3;
               Object var6;
               label24:
               try {
                  var3 = Result.Companion;
                  <undefinedtype> var7 = (<undefinedtype>)this;
                  var6 = Result.constructor_impl(this.$onContextAvailable$inlined.invoke(var1));
               } catch (Throwable var5) {
                  var3 = Result.Companion;
                  var6 = Result.constructor_impl(ResultKt.createFailure(var5));
                  break label24;
               }

               var2.resumeWith(var6);
            }
         };
         var0.addOnContextAvailableListener((OnContextAvailableListener)var4);
         var5.invokeOnCancellation((Function1)(new Function1(var4, var0, var1) {
            final <undefinedtype> $listener;
            final Function1 $onContextAvailable$inlined;
            final ContextAware $this_withContextAvailable$inlined;

            public {
               this.$listener = var1;
               this.$this_withContextAvailable$inlined = var2;
               this.$onContextAvailable$inlined = var3;
            }

            public final void invoke(Throwable var1) {
               this.$this_withContextAvailable$inlined.removeOnContextAvailableListener((OnContextAvailableListener)this.$listener);
            }
         }));
         var6 = var7.getResult();
         if (var6 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var2);
         }

         InlineMarker.mark(1);
      }

      return var6;
   }
}
