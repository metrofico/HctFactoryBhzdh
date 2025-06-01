package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0005\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0018\u0010\u000b\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0001\u001a\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0080\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"unsafeFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "checkContext", "Lkotlinx/coroutines/flow/internal/SafeCollector;", "currentContext", "Lkotlin/coroutines/CoroutineContext;", "transitiveCoroutineParent", "Lkotlinx/coroutines/Job;", "collectJob", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class SafeCollector_commonKt {
   public static final void checkContext(SafeCollector var0, CoroutineContext var1) {
      if (((Number)var1.fold(0, (Function2)(new Function2(var0) {
         final SafeCollector $this_checkContext;

         {
            this.$this_checkContext = var1;
         }

         public final int invoke(int var1, CoroutineContext.Element var2) {
            CoroutineContext.Key var3 = var2.getKey();
            CoroutineContext.Element var4 = this.$this_checkContext.collectContext.get(var3);
            if (var3 != Job.Key) {
               if (var2 != var4) {
                  var1 = Integer.MIN_VALUE;
               } else {
                  ++var1;
               }

               return var1;
            } else {
               Job var6 = (Job)var4;
               if (var2 != null) {
                  Job var5 = SafeCollector_commonKt.transitiveCoroutineParent((Job)var2, var6);
                  if (var5 == var6) {
                     if (var6 != null) {
                        ++var1;
                     }

                     return var1;
                  } else {
                     throw (Throwable)(new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n" + "\t\tChild of " + var5 + ", expected child of " + var6 + ".\n" + "\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n" + "\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString()));
                  }
               } else {
                  throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.Job");
               }
            }
         }
      }))).intValue() != var0.collectContextSize) {
         throw (Throwable)(new IllegalStateException(("Flow invariant is violated:\n" + "\t\tFlow was collected in " + var0.collectContext + ",\n" + "\t\tbut emission happened in " + var1 + ".\n" + "\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString()));
      }
   }

   public static final Job transitiveCoroutineParent(Job var0, Job var1) {
      while(var0 != null) {
         if (var0 == var1) {
            return var0;
         }

         if (!(var0 instanceof ScopeCoroutine)) {
            return var0;
         }

         var0 = ((ScopeCoroutine)var0).getParent$kotlinx_coroutines_core();
      }

      return null;
   }

   public static final Flow unsafeFlow(Function2 var0) {
      return (Flow)(new Flow(var0) {
         final Function2 $block;

         public {
            this.$block = var1;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = this.$block.invoke(var1, var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            this.$block.invoke(var1, var2);
            return Unit.INSTANCE;
         }
      });
   }
}
