package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a/\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a)\u0010\f\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\r\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\nH\u0002¢\u0006\u0002\u0010\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"},
   d2 = {"FINISHED", "", "INTERRUPTED", "INTERRUPTING", "WORKING", "runInterruptible", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runInterruptibleInExpectedContext", "coroutineContext", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class InterruptibleKt {
   private static final int FINISHED = 1;
   private static final int INTERRUPTED = 3;
   private static final int INTERRUPTING = 2;
   private static final int WORKING = 0;

   public static final Object runInterruptible(CoroutineContext var0, Function0 var1, Continuation var2) {
      return BuildersKt.withContext(var0, (Function2)(new Function2(var1, (Continuation)null) {
         final Function0 $block;
         int label;
         private CoroutineScope p$;

         {
            this.$block = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$block, var2);
            var3.p$ = (CoroutineScope)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
               ResultKt.throwOnFailure(var1);
               return InterruptibleKt.runInterruptibleInExpectedContext(this.p$.getCoroutineContext(), this.$block);
            } else {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
         }
      }), var2);
   }

   // $FF: synthetic method
   public static Object runInterruptible$default(CoroutineContext var0, Function0 var1, Continuation var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var0 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      return runInterruptible(var0, var1, var2);
   }

   private static final Object runInterruptibleInExpectedContext(CoroutineContext var0, Function0 var1) {
      InterruptedException var10000;
      label51: {
         boolean var10001;
         ThreadState var2;
         try {
            var2 = new ThreadState(JobKt.getJob(var0));
            var2.setup();
         } catch (InterruptedException var9) {
            var10000 = var9;
            var10001 = false;
            break label51;
         }

         try {
            Object var10 = var1.invoke();
            return var10;
         } finally {
            label45:
            try {
               var2.clearInterrupt();
            } catch (InterruptedException var7) {
               var10000 = var7;
               var10001 = false;
               break label45;
            }
         }
      }

      InterruptedException var11 = var10000;
      throw (new CancellationException("Blocking call was interrupted due to parent cancellation")).initCause((Throwable)var11);
   }
}
