package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u001a\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0014H\u0002\u001a6\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0019*\u00020\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001bH\u0086Hø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u001c\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\"\u0016\u0010\t\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"},
   d2 = {"BROKEN", "Lkotlinx/coroutines/internal/Symbol;", "getBROKEN$annotations", "()V", "CANCELLED", "getCANCELLED$annotations", "MAX_SPIN_CYCLES", "", "getMAX_SPIN_CYCLES$annotations", "PERMIT", "getPERMIT$annotations", "SEGMENT_SIZE", "getSEGMENT_SIZE$annotations", "TAKEN", "getTAKEN$annotations", "Semaphore", "Lkotlinx/coroutines/sync/Semaphore;", "permits", "acquiredPermits", "createSegment", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "id", "", "prev", "withPermit", "T", "action", "Lkotlin/Function0;", "(Lkotlinx/coroutines/sync/Semaphore;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class SemaphoreKt {
   private static final Symbol BROKEN = new Symbol("BROKEN");
   private static final Symbol CANCELLED = new Symbol("CANCELLED");
   private static final int MAX_SPIN_CYCLES = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, (Object)null);
   private static final Symbol PERMIT = new Symbol("PERMIT");
   private static final int SEGMENT_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, (Object)null);
   private static final Symbol TAKEN = new Symbol("TAKEN");

   public static final Semaphore Semaphore(int var0, int var1) {
      return (Semaphore)(new SemaphoreImpl(var0, var1));
   }

   // $FF: synthetic method
   public static Semaphore Semaphore$default(int var0, int var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = 0;
      }

      return Semaphore(var0, var1);
   }

   // $FF: synthetic method
   public static final SemaphoreSegment access$createSegment(long var0, SemaphoreSegment var2) {
      return createSegment(var0, var2);
   }

   // $FF: synthetic method
   public static final Symbol access$getBROKEN$p() {
      return BROKEN;
   }

   // $FF: synthetic method
   public static final Symbol access$getCANCELLED$p() {
      return CANCELLED;
   }

   // $FF: synthetic method
   public static final int access$getMAX_SPIN_CYCLES$p() {
      return MAX_SPIN_CYCLES;
   }

   // $FF: synthetic method
   public static final Symbol access$getPERMIT$p() {
      return PERMIT;
   }

   // $FF: synthetic method
   public static final int access$getSEGMENT_SIZE$p() {
      return SEGMENT_SIZE;
   }

   // $FF: synthetic method
   public static final Symbol access$getTAKEN$p() {
      return TAKEN;
   }

   private static final SemaphoreSegment createSegment(long var0, SemaphoreSegment var2) {
      return new SemaphoreSegment(var0, var2, 0);
   }

   // $FF: synthetic method
   private static void getBROKEN$annotations() {
   }

   // $FF: synthetic method
   private static void getCANCELLED$annotations() {
   }

   // $FF: synthetic method
   private static void getMAX_SPIN_CYCLES$annotations() {
   }

   // $FF: synthetic method
   private static void getPERMIT$annotations() {
   }

   // $FF: synthetic method
   private static void getSEGMENT_SIZE$annotations() {
   }

   // $FF: synthetic method
   private static void getTAKEN$annotations() {
   }

   public static final Object withPermit(Semaphore var0, Function0 var1, Continuation var2) {
      Object var4;
      label58: {
         if (var2 instanceof <undefinedtype>) {
            var4 = (<undefinedtype>)var2;
            if ((((<undefinedtype>)var4).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)var4).label += Integer.MIN_VALUE;
               break label58;
            }
         }

         var4 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return SemaphoreKt.withPermit((Semaphore)null, (Function0)null, this);
            }
         };
      }

      Object var6 = ((<undefinedtype>)var4).result;
      Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var4).label;
      Semaphore var10;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var1 = (Function0)((<undefinedtype>)var4).L$1;
         var10 = (Semaphore)((<undefinedtype>)var4).L$0;
         ResultKt.throwOnFailure(var6);
      } else {
         ResultKt.throwOnFailure(var6);
         ((<undefinedtype>)var4).L$0 = var0;
         ((<undefinedtype>)var4).L$1 = var1;
         ((<undefinedtype>)var4).label = 1;
         var10 = var0;
         if (var0.acquire((Continuation)var4) == var5) {
            return var5;
         }
      }

      Object var9;
      try {
         var9 = var1.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var10.release();
         InlineMarker.finallyEnd(1);
      }

      return var9;
   }

   private static final Object withPermit$$forInline(Semaphore var0, Function0 var1, Continuation var2) {
      InlineMarker.mark(0);
      var0.acquire(var2);
      InlineMarker.mark(2);
      InlineMarker.mark(1);

      Object var5;
      try {
         var5 = var1.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var0.release();
         InlineMarker.finallyEnd(1);
      }

      return var5;
   }
}
