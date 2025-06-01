package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aD\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001aD\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\n2'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aD\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001aD\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\n2'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aD\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001aD\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\n2'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aL\u0010\u000e\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004¢\u0006\u0002\b\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"whenCreated", "T", "Landroidx/lifecycle/Lifecycle;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/lifecycle/Lifecycle;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "whenResumed", "whenStarted", "whenStateAtLeast", "minState", "Landroidx/lifecycle/Lifecycle$State;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lifecycle-runtime-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class PausingDispatcherKt {
   public static final Object whenCreated(Lifecycle var0, Function2 var1, Continuation var2) {
      return whenStateAtLeast(var0, Lifecycle.State.CREATED, var1, var2);
   }

   public static final Object whenCreated(LifecycleOwner var0, Function2 var1, Continuation var2) {
      Lifecycle var3 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var3, "lifecycle");
      return whenCreated(var3, var1, var2);
   }

   public static final Object whenResumed(Lifecycle var0, Function2 var1, Continuation var2) {
      return whenStateAtLeast(var0, Lifecycle.State.RESUMED, var1, var2);
   }

   public static final Object whenResumed(LifecycleOwner var0, Function2 var1, Continuation var2) {
      Lifecycle var3 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var3, "lifecycle");
      return whenResumed(var3, var1, var2);
   }

   public static final Object whenStarted(Lifecycle var0, Function2 var1, Continuation var2) {
      return whenStateAtLeast(var0, Lifecycle.State.STARTED, var1, var2);
   }

   public static final Object whenStarted(LifecycleOwner var0, Function2 var1, Continuation var2) {
      Lifecycle var3 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var3, "lifecycle");
      return whenStarted(var3, var1, var2);
   }

   public static final Object whenStateAtLeast(Lifecycle var0, Lifecycle.State var1, Function2 var2, Continuation var3) {
      return BuildersKt.withContext((CoroutineContext)Dispatchers.getMain().getImmediate(), (Function2)(new Function2(var0, var1, var2, (Continuation)null) {
         final Function2 $block;
         final Lifecycle.State $minState;
         final Lifecycle $this_whenStateAtLeast;
         private Object L$0;
         int label;

         {
            this.$this_whenStateAtLeast = var1;
            this.$minState = var2;
            this.$block = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Intrinsics.checkNotNullParameter(var2, "completion");
            Function2 var3 = new <anonymous constructor>(this.$this_whenStateAtLeast, this.$minState, this.$block, var2);
            var3.L$0 = var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            LifecycleController var3;
            label114: {
               Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               int var2 = this.label;
               Object var16;
               LifecycleController var13;
               if (var2 != 0) {
                  if (var2 != 1) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var3 = (LifecycleController)this.L$0;

                  try {
                     ResultKt.throwOnFailure(var1);
                  } finally {
                     break label114;
                  }

               } else {
                  ResultKt.throwOnFailure(var1);
                  Job var12 = (Job)((CoroutineScope)this.L$0).getCoroutineContext().get((CoroutineContext.Key)Job.Key);
                  if (var12 == null) {
                     throw (Throwable)(new IllegalStateException("when[State] methods should have a parent job".toString()));
                  }

                  PausingDispatcher var14 = new PausingDispatcher();
                  var13 = new LifecycleController(this.$this_whenStateAtLeast, this.$minState, var14.dispatchQueue, var12);

                  try {
                     CoroutineContext var15 = (CoroutineContext)var14;
                     Function2 var5 = this.$block;
                     this.L$0 = var13;
                     this.label = 1;
                     var16 = BuildersKt.withContext(var15, var5, this);
                  } catch (Throwable var10) {
                     var3 = var13;
                     var1 = var10;
                     break label114;
                  }

                  if (var16 == var4) {
                     return var4;
                  }
               }

               var13.finish();
               return var16;
            }

            var3.finish();
            throw var1;
         }
      }), var3);
   }
}
