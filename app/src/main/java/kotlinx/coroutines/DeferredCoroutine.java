package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\r\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\r\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010JH\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0001\u0010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\u00152\"\u0010\u0016\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001aR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"},
   d2 = {"Lkotlinx/coroutines/DeferredCoroutine;", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/Deferred;", "Lkotlinx/coroutines/selects/SelectClause1;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Z)V", "onAwait", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "registerSelectClause1", "", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
class DeferredCoroutine extends AbstractCoroutine implements Deferred, SelectClause1 {
   public DeferredCoroutine(CoroutineContext var1, boolean var2) {
      super(var1, var2);
   }

   // $FF: synthetic method
   static Object await$suspendImpl(DeferredCoroutine var0, Continuation var1) {
      Object var6;
      label23: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var6 = var3;
               break label23;
            }
         }

         var6 = new ContinuationImpl(var0, var1) {
            Object L$0;
            int label;
            Object result;
            final DeferredCoroutine this$0;

            {
               this.this$0 = var1;
            }

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return DeferredCoroutine.await$suspendImpl(this.this$0, this);
            }
         };
      }

      Object var7 = ((<undefinedtype>)var6).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var6).label;
      Object var5;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (DeferredCoroutine)((<undefinedtype>)var6).L$0;
         ResultKt.throwOnFailure(var7);
         var5 = var7;
      } else {
         ResultKt.throwOnFailure(var7);
         ((<undefinedtype>)var6).L$0 = var0;
         ((<undefinedtype>)var6).label = 1;
         var6 = var0.awaitInternal$kotlinx_coroutines_core((Continuation)var6);
         var5 = var6;
         if (var6 == var4) {
            return var4;
         }
      }

      return var5;
   }

   public Object await(Continuation var1) {
      return await$suspendImpl(this, var1);
   }

   public Object getCompleted() {
      return this.getCompletedInternal$kotlinx_coroutines_core();
   }

   public SelectClause1 getOnAwait() {
      return (SelectClause1)this;
   }

   public void registerSelectClause1(SelectInstance var1, Function2 var2) {
      this.registerSelectClause1Internal$kotlinx_coroutines_core(var1, var2);
   }
}
