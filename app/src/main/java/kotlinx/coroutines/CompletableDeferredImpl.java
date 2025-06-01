package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u000f\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000f\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\r\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018JH\u0010\u0019\u001a\u00020\u001a\"\u0004\b\u0001\u0010\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001d2\"\u0010\u001e\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0 \u0012\u0006\u0012\u0004\u0018\u00010!0\u001fH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\"R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"},
   d2 = {"Lkotlinx/coroutines/CompletableDeferredImpl;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlinx/coroutines/selects/SelectClause1;", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "onAwait", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "onCancelComplete", "", "getOnCancelComplete$kotlinx_coroutines_core", "()Z", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "getCompleted", "()Ljava/lang/Object;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class CompletableDeferredImpl extends JobSupport implements CompletableDeferred, SelectClause1 {
   public CompletableDeferredImpl(Job var1) {
      super(true);
      this.initParentJobInternal$kotlinx_coroutines_core(var1);
   }

   public Object await(Continuation var1) {
      Object var5;
      label23: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var5 = var3;
               break label23;
            }
         }

         var5 = new ContinuationImpl(this, var1) {
            Object L$0;
            int label;
            Object result;
            final CompletableDeferredImpl this$0;

            {
               this.this$0 = var1;
            }

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.await(this);
            }
         };
      }

      Object var7 = ((<undefinedtype>)var5).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var5).label;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         CompletableDeferredImpl var6 = (CompletableDeferredImpl)((<undefinedtype>)var5).L$0;
         ResultKt.throwOnFailure(var7);
         var5 = var7;
      } else {
         ResultKt.throwOnFailure(var7);
         ((<undefinedtype>)var5).L$0 = this;
         ((<undefinedtype>)var5).label = 1;
         var7 = this.awaitInternal$kotlinx_coroutines_core((Continuation)var5);
         var5 = var7;
         if (var7 == var4) {
            return var4;
         }
      }

      return var5;
   }

   public boolean complete(Object var1) {
      return this.makeCompleting$kotlinx_coroutines_core(var1);
   }

   public boolean completeExceptionally(Throwable var1) {
      return this.makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(var1, false, 2, (DefaultConstructorMarker)null));
   }

   public Object getCompleted() {
      return this.getCompletedInternal$kotlinx_coroutines_core();
   }

   public SelectClause1 getOnAwait() {
      return (SelectClause1)this;
   }

   public boolean getOnCancelComplete$kotlinx_coroutines_core() {
      return true;
   }

   public void registerSelectClause1(SelectInstance var1, Function2 var2) {
      this.registerSelectClause1Internal$kotlinx_coroutines_core(var1, var2);
   }
}
