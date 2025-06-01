package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001aA\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a+\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a+\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a+\u0010\u0010\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a+\u0010\u0010\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a+\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a+\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a3\u0010\u0012\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a3\u0010\u0012\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a3\u0010\u0015\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0081Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"},
   d2 = {"suspendWithStateAtLeastUnchecked", "R", "Landroidx/lifecycle/Lifecycle;", "state", "Landroidx/lifecycle/Lifecycle$State;", "dispatchNeeded", "", "lifecycleDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "block", "Lkotlin/Function0;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;ZLkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withCreated", "(Landroidx/lifecycle/Lifecycle;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withResumed", "withStarted", "withStateAtLeast", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withStateAtLeastUnchecked", "lifecycle-runtime-ktx_release"},
   k = 2,
   mv = {1, 4, 1}
)
public final class WithLifecycleStateKt {
   public static final Object suspendWithStateAtLeastUnchecked(Lifecycle var0, Lifecycle.State var1, boolean var2, CoroutineDispatcher var3, Function0 var4, Continuation var5) {
      CancellableContinuationImpl var6 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var5), 1);
      var6.initCancellability();
      CancellableContinuation var8 = (CancellableContinuation)var6;
      LifecycleEventObserver var7 = new LifecycleEventObserver(var8, var0, var1, var4, var2, var3) {
         final Function0 $block$inlined;
         final CancellableContinuation $co;
         final boolean $dispatchNeeded$inlined;
         final CoroutineDispatcher $lifecycleDispatcher$inlined;
         final Lifecycle.State $state$inlined;
         final Lifecycle $this_suspendWithStateAtLeastUnchecked$inlined;

         {
            this.$co = var1;
            this.$this_suspendWithStateAtLeastUnchecked$inlined = var2;
            this.$state$inlined = var3;
            this.$block$inlined = var4;
            this.$dispatchNeeded$inlined = var5;
            this.$lifecycleDispatcher$inlined = var6;
         }

         public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
            Intrinsics.checkNotNullParameter(var1, "source");
            Intrinsics.checkNotNullParameter(var2, "event");
            Result.Companion var7;
            if (var2 == Lifecycle.Event.upTo(this.$state$inlined)) {
               this.$this_suspendWithStateAtLeastUnchecked$inlined.removeObserver((LifecycleObserver)this);
               CancellableContinuation var8 = this.$co;
               Function0 var3 = this.$block$inlined;

               Object var6;
               label36:
               try {
                  var7 = Result.Companion;
                  var6 = Result.constructor_impl(var3.invoke());
               } catch (Throwable var5) {
                  Result.Companion var10 = Result.Companion;
                  var6 = Result.constructor_impl(ResultKt.createFailure(var5));
                  break label36;
               }

               var8.resumeWith(var6);
            } else if (var2 == Lifecycle.Event.ON_DESTROY) {
               this.$this_suspendWithStateAtLeastUnchecked$inlined.removeObserver((LifecycleObserver)this);
               Continuation var11 = (Continuation)this.$co;
               Throwable var9 = (Throwable)(new LifecycleDestroyedException());
               var7 = Result.Companion;
               var11.resumeWith(Result.constructor_impl(ResultKt.createFailure(var9)));
            }

         }
      };
      if (var2) {
         var3.dispatch((CoroutineContext)EmptyCoroutineContext.INSTANCE, (Runnable)(new Runnable(var7, var0, var1, var4, var2, var3) {
            final Function0 $block$inlined;
            final boolean $dispatchNeeded$inlined;
            final CoroutineDispatcher $lifecycleDispatcher$inlined;
            final <undefinedtype> $observer;
            final Lifecycle.State $state$inlined;
            final Lifecycle $this_suspendWithStateAtLeastUnchecked$inlined;

            {
               this.$observer = var1;
               this.$this_suspendWithStateAtLeastUnchecked$inlined = var2;
               this.$state$inlined = var3;
               this.$block$inlined = var4;
               this.$dispatchNeeded$inlined = var5;
               this.$lifecycleDispatcher$inlined = var6;
            }

            public final void run() {
               this.$this_suspendWithStateAtLeastUnchecked$inlined.addObserver((LifecycleObserver)this.$observer);
            }
         }));
      } else {
         var0.addObserver((LifecycleObserver)var7);
      }

      var8.invokeOnCancellation((Function1)(new Function1(var7, var0, var1, var4, var2, var3) {
         final Function0 $block$inlined;
         final boolean $dispatchNeeded$inlined;
         final CoroutineDispatcher $lifecycleDispatcher$inlined;
         final <undefinedtype> $observer;
         final Lifecycle.State $state$inlined;
         final Lifecycle $this_suspendWithStateAtLeastUnchecked$inlined;

         {
            this.$observer = var1;
            this.$this_suspendWithStateAtLeastUnchecked$inlined = var2;
            this.$state$inlined = var3;
            this.$block$inlined = var4;
            this.$dispatchNeeded$inlined = var5;
            this.$lifecycleDispatcher$inlined = var6;
         }

         public final void invoke(Throwable var1) {
            if (this.$lifecycleDispatcher$inlined.isDispatchNeeded((CoroutineContext)EmptyCoroutineContext.INSTANCE)) {
               this.$lifecycleDispatcher$inlined.dispatch((CoroutineContext)EmptyCoroutineContext.INSTANCE, (Runnable)(new Runnable(this) {
                  final <undefinedtype> this$0;

                  {
                     this.this$0 = var1;
                  }

                  public final void run() {
                     this.this$0.$this_suspendWithStateAtLeastUnchecked$inlined.removeObserver((LifecycleObserver)this.this$0.$observer);
                  }
               }));
            } else {
               this.$this_suspendWithStateAtLeastUnchecked$inlined.removeObserver((LifecycleObserver)this.$observer);
            }

         }
      }));
      Object var9 = var6.getResult();
      if (var9 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var5);
      }

      return var9;
   }

   public static final Object withCreated(Lifecycle var0, Function0 var1, Continuation var2) {
      Lifecycle.State var4 = Lifecycle.State.CREATED;
      MainCoroutineDispatcher var5 = Dispatchers.getMain().getImmediate();
      boolean var3 = var5.isDispatchNeeded(var2.getContext());
      Object var6;
      if (!var3) {
         if (var0.getCurrentState() == Lifecycle.State.DESTROYED) {
            throw (Throwable)(new LifecycleDestroyedException());
         }

         if (var0.getCurrentState().compareTo((Enum)var4) >= 0) {
            var6 = var1.invoke();
            return var6;
         }
      }

      var6 = suspendWithStateAtLeastUnchecked(var0, var4, var3, (CoroutineDispatcher)var5, (Function0)(new Function0(var1) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public final Object invoke() {
            return this.$block.invoke();
         }
      }), var2);
      return var6;
   }

   public static final Object withCreated(LifecycleOwner var0, Function0 var1, Continuation var2) {
      Lifecycle var5 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var5, "lifecycle");
      Lifecycle.State var4 = Lifecycle.State.CREATED;
      MainCoroutineDispatcher var6 = Dispatchers.getMain().getImmediate();
      boolean var3 = var6.isDispatchNeeded(var2.getContext());
      Object var7;
      if (!var3) {
         if (var5.getCurrentState() == Lifecycle.State.DESTROYED) {
            throw (Throwable)(new LifecycleDestroyedException());
         }

         if (var5.getCurrentState().compareTo((Enum)var4) >= 0) {
            var7 = var1.invoke();
            return var7;
         }
      }

      var7 = suspendWithStateAtLeastUnchecked(var5, var4, var3, (CoroutineDispatcher)var6, (Function0)(new Function0(var1) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public final Object invoke() {
            return this.$block.invoke();
         }
      }), var2);
      return var7;
   }

   private static final Object withCreated$$forInline(Lifecycle var0, Function0 var1, Continuation var2) {
      Lifecycle.State var3 = Lifecycle.State.CREATED;
      Dispatchers.getMain().getImmediate();
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   private static final Object withCreated$$forInline(LifecycleOwner var0, Function0 var1, Continuation var2) {
      Intrinsics.checkNotNullExpressionValue(var0.getLifecycle(), "lifecycle");
      Lifecycle.State var3 = Lifecycle.State.CREATED;
      Dispatchers.getMain().getImmediate();
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   public static final Object withResumed(Lifecycle var0, Function0 var1, Continuation var2) {
      Lifecycle.State var4 = Lifecycle.State.RESUMED;
      MainCoroutineDispatcher var5 = Dispatchers.getMain().getImmediate();
      boolean var3 = var5.isDispatchNeeded(var2.getContext());
      Object var6;
      if (!var3) {
         if (var0.getCurrentState() == Lifecycle.State.DESTROYED) {
            throw (Throwable)(new LifecycleDestroyedException());
         }

         if (var0.getCurrentState().compareTo((Enum)var4) >= 0) {
            var6 = var1.invoke();
            return var6;
         }
      }

      var6 = suspendWithStateAtLeastUnchecked(var0, var4, var3, (CoroutineDispatcher)var5, (Function0)(new Function0(var1) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public final Object invoke() {
            return this.$block.invoke();
         }
      }), var2);
      return var6;
   }

   public static final Object withResumed(LifecycleOwner var0, Function0 var1, Continuation var2) {
      Lifecycle var6 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var6, "lifecycle");
      Lifecycle.State var4 = Lifecycle.State.RESUMED;
      MainCoroutineDispatcher var5 = Dispatchers.getMain().getImmediate();
      boolean var3 = var5.isDispatchNeeded(var2.getContext());
      Object var7;
      if (!var3) {
         if (var6.getCurrentState() == Lifecycle.State.DESTROYED) {
            throw (Throwable)(new LifecycleDestroyedException());
         }

         if (var6.getCurrentState().compareTo((Enum)var4) >= 0) {
            var7 = var1.invoke();
            return var7;
         }
      }

      var7 = suspendWithStateAtLeastUnchecked(var6, var4, var3, (CoroutineDispatcher)var5, (Function0)(new Function0(var1) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public final Object invoke() {
            return this.$block.invoke();
         }
      }), var2);
      return var7;
   }

   private static final Object withResumed$$forInline(Lifecycle var0, Function0 var1, Continuation var2) {
      Lifecycle.State var3 = Lifecycle.State.RESUMED;
      Dispatchers.getMain().getImmediate();
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   private static final Object withResumed$$forInline(LifecycleOwner var0, Function0 var1, Continuation var2) {
      Intrinsics.checkNotNullExpressionValue(var0.getLifecycle(), "lifecycle");
      Lifecycle.State var3 = Lifecycle.State.RESUMED;
      Dispatchers.getMain().getImmediate();
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   public static final Object withStarted(Lifecycle var0, Function0 var1, Continuation var2) {
      Lifecycle.State var5 = Lifecycle.State.STARTED;
      MainCoroutineDispatcher var4 = Dispatchers.getMain().getImmediate();
      boolean var3 = var4.isDispatchNeeded(var2.getContext());
      Object var6;
      if (!var3) {
         if (var0.getCurrentState() == Lifecycle.State.DESTROYED) {
            throw (Throwable)(new LifecycleDestroyedException());
         }

         if (var0.getCurrentState().compareTo((Enum)var5) >= 0) {
            var6 = var1.invoke();
            return var6;
         }
      }

      var6 = suspendWithStateAtLeastUnchecked(var0, var5, var3, (CoroutineDispatcher)var4, (Function0)(new Function0(var1) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public final Object invoke() {
            return this.$block.invoke();
         }
      }), var2);
      return var6;
   }

   public static final Object withStarted(LifecycleOwner var0, Function0 var1, Continuation var2) {
      Lifecycle var5 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var5, "lifecycle");
      Lifecycle.State var4 = Lifecycle.State.STARTED;
      MainCoroutineDispatcher var6 = Dispatchers.getMain().getImmediate();
      boolean var3 = var6.isDispatchNeeded(var2.getContext());
      Object var7;
      if (!var3) {
         if (var5.getCurrentState() == Lifecycle.State.DESTROYED) {
            throw (Throwable)(new LifecycleDestroyedException());
         }

         if (var5.getCurrentState().compareTo((Enum)var4) >= 0) {
            var7 = var1.invoke();
            return var7;
         }
      }

      var7 = suspendWithStateAtLeastUnchecked(var5, var4, var3, (CoroutineDispatcher)var6, (Function0)(new Function0(var1) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public final Object invoke() {
            return this.$block.invoke();
         }
      }), var2);
      return var7;
   }

   private static final Object withStarted$$forInline(Lifecycle var0, Function0 var1, Continuation var2) {
      Lifecycle.State var3 = Lifecycle.State.STARTED;
      Dispatchers.getMain().getImmediate();
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   private static final Object withStarted$$forInline(LifecycleOwner var0, Function0 var1, Continuation var2) {
      Intrinsics.checkNotNullExpressionValue(var0.getLifecycle(), "lifecycle");
      Lifecycle.State var3 = Lifecycle.State.STARTED;
      Dispatchers.getMain().getImmediate();
      InlineMarker.mark(3);
      throw new NullPointerException();
   }

   public static final Object withStateAtLeast(Lifecycle var0, Lifecycle.State var1, Function0 var2, Continuation var3) {
      boolean var4;
      if (var1.compareTo((Enum)Lifecycle.State.CREATED) >= 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw (Throwable)(new IllegalArgumentException(("target state must be CREATED or greater, found " + var1).toString()));
      } else {
         MainCoroutineDispatcher var6 = Dispatchers.getMain().getImmediate();
         boolean var5 = var6.isDispatchNeeded(var3.getContext());
         Object var7;
         if (!var5) {
            if (var0.getCurrentState() == Lifecycle.State.DESTROYED) {
               throw (Throwable)(new LifecycleDestroyedException());
            }

            if (var0.getCurrentState().compareTo((Enum)var1) >= 0) {
               var7 = var2.invoke();
               return var7;
            }
         }

         var7 = suspendWithStateAtLeastUnchecked(var0, var1, var5, (CoroutineDispatcher)var6, (Function0)(new Function0(var2) {
            final Function0 $block;

            public {
               this.$block = var1;
            }

            public final Object invoke() {
               return this.$block.invoke();
            }
         }), var3);
         return var7;
      }
   }

   public static final Object withStateAtLeast(LifecycleOwner var0, Lifecycle.State var1, Function0 var2, Continuation var3) {
      Lifecycle var7 = var0.getLifecycle();
      Intrinsics.checkNotNullExpressionValue(var7, "lifecycle");
      boolean var4;
      if (var1.compareTo((Enum)Lifecycle.State.CREATED) >= 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw (Throwable)(new IllegalArgumentException(("target state must be CREATED or greater, found " + var1).toString()));
      } else {
         MainCoroutineDispatcher var6 = Dispatchers.getMain().getImmediate();
         boolean var5 = var6.isDispatchNeeded(var3.getContext());
         Object var8;
         if (!var5) {
            if (var7.getCurrentState() == Lifecycle.State.DESTROYED) {
               throw (Throwable)(new LifecycleDestroyedException());
            }

            if (var7.getCurrentState().compareTo((Enum)var1) >= 0) {
               var8 = var2.invoke();
               return var8;
            }
         }

         var8 = suspendWithStateAtLeastUnchecked(var7, var1, var5, (CoroutineDispatcher)var6, (Function0)(new Function0(var2) {
            final Function0 $block;

            public {
               this.$block = var1;
            }

            public final Object invoke() {
               return this.$block.invoke();
            }
         }), var3);
         return var8;
      }
   }

   private static final Object withStateAtLeast$$forInline(Lifecycle var0, Lifecycle.State var1, Function0 var2, Continuation var3) {
      boolean var4;
      if (var1.compareTo((Enum)Lifecycle.State.CREATED) >= 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw (Throwable)(new IllegalArgumentException(("target state must be CREATED or greater, found " + var1).toString()));
      } else {
         Dispatchers.getMain().getImmediate();
         InlineMarker.mark(3);
         throw new NullPointerException();
      }
   }

   private static final Object withStateAtLeast$$forInline(LifecycleOwner var0, Lifecycle.State var1, Function0 var2, Continuation var3) {
      Intrinsics.checkNotNullExpressionValue(var0.getLifecycle(), "lifecycle");
      boolean var4;
      if (var1.compareTo((Enum)Lifecycle.State.CREATED) >= 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw (Throwable)(new IllegalArgumentException(("target state must be CREATED or greater, found " + var1).toString()));
      } else {
         Dispatchers.getMain().getImmediate();
         InlineMarker.mark(3);
         throw new NullPointerException();
      }
   }

   public static final Object withStateAtLeastUnchecked(Lifecycle var0, Lifecycle.State var1, Function0 var2, Continuation var3) {
      MainCoroutineDispatcher var5 = Dispatchers.getMain().getImmediate();
      boolean var4 = var5.isDispatchNeeded(var3.getContext());
      if (!var4) {
         if (var0.getCurrentState() == Lifecycle.State.DESTROYED) {
            throw (Throwable)(new LifecycleDestroyedException());
         }

         if (var0.getCurrentState().compareTo((Enum)var1) >= 0) {
            return var2.invoke();
         }
      }

      return suspendWithStateAtLeastUnchecked(var0, var1, var4, (CoroutineDispatcher)var5, (Function0)(new Function0(var2) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public final Object invoke() {
            return this.$block.invoke();
         }
      }), var3);
   }

   private static final Object withStateAtLeastUnchecked$$forInline(Lifecycle var0, Lifecycle.State var1, Function0 var2, Continuation var3) {
      Dispatchers.getMain().getImmediate();
      InlineMarker.mark(3);
      throw new NullPointerException();
   }
}
