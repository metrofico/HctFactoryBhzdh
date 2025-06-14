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
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J7\u0010\u0007\u001a\u00020\b2'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\n¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ7\u0010\u0010\u001a\u00020\b2'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\n¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ7\u0010\u0011\u001a\u00020\b2'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\n¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0003\u001a\u00020\u0004X \u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"Landroidx/lifecycle/LifecycleCoroutineScope;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle$lifecycle_runtime_ktx_release", "()Landroidx/lifecycle/Lifecycle;", "launchWhenCreated", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "launchWhenResumed", "launchWhenStarted", "lifecycle-runtime-ktx_release"},
   k = 1,
   mv = {1, 4, 1}
)
public abstract class LifecycleCoroutineScope implements CoroutineScope {
   public abstract Lifecycle getLifecycle$lifecycle_runtime_ktx_release();

   public final Job launchWhenCreated(Function2 var1) {
      Intrinsics.checkNotNullParameter(var1, "block");
      return BuildersKt.launch$default(this, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(this, var1, (Continuation)null) {
         final Function2 $block;
         int label;
         final LifecycleCoroutineScope this$0;

         {
            this.this$0 = var1;
            this.$block = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Intrinsics.checkNotNullParameter(var2, "completion");
            return new <anonymous constructor>(this.this$0, this.$block, var2);
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               Lifecycle var4 = this.this$0.getLifecycle$lifecycle_runtime_ktx_release();
               Function2 var5 = this.$block;
               this.label = 1;
               if (PausingDispatcherKt.whenCreated((Lifecycle)var4, var5, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }), 3, (Object)null);
   }

   public final Job launchWhenResumed(Function2 var1) {
      Intrinsics.checkNotNullParameter(var1, "block");
      return BuildersKt.launch$default(this, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(this, var1, (Continuation)null) {
         final Function2 $block;
         int label;
         final LifecycleCoroutineScope this$0;

         {
            this.this$0 = var1;
            this.$block = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Intrinsics.checkNotNullParameter(var2, "completion");
            return new <anonymous constructor>(this.this$0, this.$block, var2);
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               Lifecycle var5 = this.this$0.getLifecycle$lifecycle_runtime_ktx_release();
               Function2 var4 = this.$block;
               this.label = 1;
               if (PausingDispatcherKt.whenResumed((Lifecycle)var5, var4, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }), 3, (Object)null);
   }

   public final Job launchWhenStarted(Function2 var1) {
      Intrinsics.checkNotNullParameter(var1, "block");
      return BuildersKt.launch$default(this, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(this, var1, (Continuation)null) {
         final Function2 $block;
         int label;
         final LifecycleCoroutineScope this$0;

         {
            this.this$0 = var1;
            this.$block = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Intrinsics.checkNotNullParameter(var2, "completion");
            return new <anonymous constructor>(this.this$0, this.$block, var2);
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               Lifecycle var5 = this.this$0.getLifecycle$lifecycle_runtime_ktx_release();
               Function2 var4 = this.$block;
               this.label = 1;
               if (PausingDispatcherKt.whenStarted((Lifecycle)var5, var4, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }), 3, (Object)null);
   }
}
