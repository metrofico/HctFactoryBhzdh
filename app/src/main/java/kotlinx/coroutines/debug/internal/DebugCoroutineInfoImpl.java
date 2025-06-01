package kotlinx.coroutines.debug.internal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\b\u0010$\u001a\u00020\u000eH\u0016J!\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020\u000e2\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(H\u0000¢\u0006\u0002\b)J%\u0010*\u001a\u00020&*\b\u0012\u0004\u0012\u00020\u00150+2\b\u0010'\u001a\u0004\u0018\u00010\fH\u0082Pø\u0001\u0000¢\u0006\u0002\u0010,R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\f8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"},
   d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "sequenceNumber", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/debug/internal/StackTraceFrame;J)V", "_context", "Ljava/lang/ref/WeakReference;", "_lastObservedFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "_state", "", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getCreationStackBottom", "()Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "value", "lastObservedFrame", "getLastObservedFrame$kotlinx_coroutines_core", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "setLastObservedFrame$kotlinx_coroutines_core", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "lastObservedThread", "Ljava/lang/Thread;", "state", "getState", "()Ljava/lang/String;", "lastObservedStackTrace", "toString", "updateState", "", "frame", "Lkotlin/coroutines/Continuation;", "updateState$kotlinx_coroutines_core", "yieldFrames", "Lkotlin/sequences/SequenceScope;", "(Lkotlin/sequences/SequenceScope;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class DebugCoroutineInfoImpl {
   private final WeakReference _context;
   private WeakReference _lastObservedFrame;
   private String _state;
   private final StackTraceFrame creationStackBottom;
   public Thread lastObservedThread;
   public final long sequenceNumber;

   public DebugCoroutineInfoImpl(CoroutineContext var1, StackTraceFrame var2, long var3) {
      this.creationStackBottom = var2;
      this.sequenceNumber = var3;
      this._context = new WeakReference(var1);
      this._state = "CREATED";
   }

   private final List creationStackTrace() {
      StackTraceFrame var1 = this.creationStackBottom;
      return var1 != null ? SequencesKt.toList(SequencesKt.sequence((Function2)(new Function2(this, var1, (Continuation)null) {
         final StackTraceFrame $bottom;
         Object L$0;
         int label;
         private SequenceScope p$;
         final DebugCoroutineInfoImpl this$0;

         {
            this.this$0 = var1;
            this.$bottom = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.this$0, this.$bottom, var2);
            var3.p$ = (SequenceScope)var1;
            return var3;
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

               SequenceScope var7 = (SequenceScope)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               SequenceScope var4 = this.p$;
               DebugCoroutineInfoImpl var5 = this.this$0;
               CoroutineStackFrame var6 = this.$bottom.getCallerFrame();
               this.L$0 = var4;
               this.label = 1;
               if (var5.yieldFrames(var4, var6, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }))) : CollectionsKt.emptyList();
   }

   public final CoroutineContext getContext() {
      return (CoroutineContext)this._context.get();
   }

   public final StackTraceFrame getCreationStackBottom() {
      return this.creationStackBottom;
   }

   public final List getCreationStackTrace() {
      return this.creationStackTrace();
   }

   public final CoroutineStackFrame getLastObservedFrame$kotlinx_coroutines_core() {
      WeakReference var1 = this._lastObservedFrame;
      CoroutineStackFrame var2;
      if (var1 != null) {
         var2 = (CoroutineStackFrame)var1.get();
      } else {
         var2 = null;
      }

      return var2;
   }

   public final String getState() {
      return this._state;
   }

   public final List lastObservedStackTrace() {
      CoroutineStackFrame var1 = this.getLastObservedFrame$kotlinx_coroutines_core();
      if (var1 != null) {
         ArrayList var2;
         for(var2 = new ArrayList(); var1 != null; var1 = var1.getCallerFrame()) {
            StackTraceElement var3 = var1.getStackTraceElement();
            if (var3 != null) {
               var2.add(var3);
            }
         }

         return (List)var2;
      } else {
         return CollectionsKt.emptyList();
      }
   }

   public final void setLastObservedFrame$kotlinx_coroutines_core(CoroutineStackFrame var1) {
      WeakReference var2;
      if (var1 != null) {
         var2 = new WeakReference(var1);
      } else {
         var2 = null;
      }

      this._lastObservedFrame = var2;
   }

   public String toString() {
      return "DebugCoroutineInfo(state=" + this.getState() + ",context=" + this.getContext() + ')';
   }

   public final void updateState$kotlinx_coroutines_core(String var1, Continuation var2) {
      if (!Intrinsics.areEqual((Object)this._state, (Object)var1) || !Intrinsics.areEqual((Object)var1, (Object)"SUSPENDED") || this.getLastObservedFrame$kotlinx_coroutines_core() == null) {
         this._state = var1;
         boolean var3 = var2 instanceof CoroutineStackFrame;
         Object var4 = null;
         if (!var3) {
            var2 = null;
         }

         this.setLastObservedFrame$kotlinx_coroutines_core((CoroutineStackFrame)var2);
         Thread var5 = (Thread)var4;
         if (Intrinsics.areEqual((Object)var1, (Object)"RUNNING")) {
            var5 = Thread.currentThread();
         }

         this.lastObservedThread = var5;
      }
   }

   // $FF: synthetic method
   final Object yieldFrames(SequenceScope var1, CoroutineStackFrame var2, Continuation var3) {
      Object var15;
      label33: {
         if (var3 instanceof <undefinedtype>) {
            <undefinedtype> var5 = (<undefinedtype>)var3;
            if ((var5.label & Integer.MIN_VALUE) != 0) {
               var5.label += Integer.MIN_VALUE;
               var15 = var5;
               break label33;
            }
         }

         var15 = new RestrictedContinuationImpl(this, var3) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            Object result;
            final DebugCoroutineInfoImpl this$0;

            {
               this.this$0 = var1;
            }

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.yieldFrames((SequenceScope)null, (CoroutineStackFrame)null, this);
            }
         };
      }

      Object var6 = ((<undefinedtype>)var15).result;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var4 = ((<undefinedtype>)var15).label;
      DebugCoroutineInfoImpl var13;
      SequenceScope var17;
      SequenceScope var18;
      if (var4 != 0) {
         if (var4 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         StackTraceElement var12 = (StackTraceElement)((<undefinedtype>)var15).L$3;
         CoroutineStackFrame var16 = (CoroutineStackFrame)((<undefinedtype>)var15).L$2;
         SequenceScope var14 = (SequenceScope)((<undefinedtype>)var15).L$1;
         var13 = (DebugCoroutineInfoImpl)((<undefinedtype>)var15).L$0;
         ResultKt.throwOnFailure(var6);
         var18 = var14;
         var2 = var16.getCallerFrame();
         if (var2 == null) {
            return Unit.INSTANCE;
         }

         var15 = var15;
         var13 = var13;
         var17 = var18;
         if (var2 == null) {
            return Unit.INSTANCE;
         }
      } else {
         ResultKt.throwOnFailure(var6);
         var17 = var1;
         var13 = this;
         if (var2 == null) {
            return Unit.INSTANCE;
         }
      }

      do {
         StackTraceElement var11 = var2.getStackTraceElement();
         Object var9 = var15;
         DebugCoroutineInfoImpl var8 = var13;
         var18 = var17;
         CoroutineStackFrame var7 = var2;
         if (var11 != null) {
            ((<undefinedtype>)var15).L$0 = var13;
            ((<undefinedtype>)var15).L$1 = var17;
            ((<undefinedtype>)var15).L$2 = var2;
            ((<undefinedtype>)var15).L$3 = var11;
            ((<undefinedtype>)var15).label = 1;
            if (var17.yield(var11, (Continuation)var15) == var10) {
               return var10;
            }

            var7 = var2;
            var18 = var17;
            var8 = var13;
            var9 = var15;
         }

         var2 = var7.getCallerFrame();
         if (var2 == null) {
            return Unit.INSTANCE;
         }

         var15 = var9;
         var13 = var8;
         var17 = var18;
      } while(var2 != null);

      return Unit.INSTANCE;
   }
}
