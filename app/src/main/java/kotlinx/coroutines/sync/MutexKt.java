package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u001aB\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u0016*\u00020\u00122\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00160\u001aH\u0086Hø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u001b\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\"\u0016\u0010\t\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003\"\u0016\u0010\u000f\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"},
   d2 = {"EMPTY_LOCKED", "Lkotlinx/coroutines/sync/Empty;", "getEMPTY_LOCKED$annotations", "()V", "EMPTY_UNLOCKED", "getEMPTY_UNLOCKED$annotations", "LOCKED", "Lkotlinx/coroutines/internal/Symbol;", "getLOCKED$annotations", "LOCK_FAIL", "getLOCK_FAIL$annotations", "SELECT_SUCCESS", "getSELECT_SUCCESS$annotations", "UNLOCKED", "getUNLOCKED$annotations", "UNLOCK_FAIL", "getUNLOCK_FAIL$annotations", "Mutex", "Lkotlinx/coroutines/sync/Mutex;", "locked", "", "withLock", "T", "owner", "", "action", "Lkotlin/Function0;", "(Lkotlinx/coroutines/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class MutexKt {
   private static final Empty EMPTY_LOCKED;
   private static final Empty EMPTY_UNLOCKED;
   private static final Symbol LOCKED;
   private static final Symbol LOCK_FAIL = new Symbol("LOCK_FAIL");
   private static final Symbol SELECT_SUCCESS = new Symbol("SELECT_SUCCESS");
   private static final Symbol UNLOCKED;
   private static final Symbol UNLOCK_FAIL = new Symbol("UNLOCK_FAIL");

   static {
      Symbol var0 = new Symbol("LOCKED");
      LOCKED = var0;
      Symbol var1 = new Symbol("UNLOCKED");
      UNLOCKED = var1;
      EMPTY_LOCKED = new Empty(var0);
      EMPTY_UNLOCKED = new Empty(var1);
   }

   public static final Mutex Mutex(boolean var0) {
      return (Mutex)(new MutexImpl(var0));
   }

   // $FF: synthetic method
   public static Mutex Mutex$default(boolean var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = false;
      }

      return Mutex(var0);
   }

   // $FF: synthetic method
   public static final Empty access$getEMPTY_LOCKED$p() {
      return EMPTY_LOCKED;
   }

   // $FF: synthetic method
   public static final Empty access$getEMPTY_UNLOCKED$p() {
      return EMPTY_UNLOCKED;
   }

   // $FF: synthetic method
   public static final Symbol access$getLOCKED$p() {
      return LOCKED;
   }

   // $FF: synthetic method
   public static final Symbol access$getLOCK_FAIL$p() {
      return LOCK_FAIL;
   }

   // $FF: synthetic method
   public static final Symbol access$getSELECT_SUCCESS$p() {
      return SELECT_SUCCESS;
   }

   // $FF: synthetic method
   public static final Symbol access$getUNLOCKED$p() {
      return UNLOCKED;
   }

   // $FF: synthetic method
   public static final Symbol access$getUNLOCK_FAIL$p() {
      return UNLOCK_FAIL;
   }

   // $FF: synthetic method
   private static void getEMPTY_LOCKED$annotations() {
   }

   // $FF: synthetic method
   private static void getEMPTY_UNLOCKED$annotations() {
   }

   // $FF: synthetic method
   private static void getLOCKED$annotations() {
   }

   // $FF: synthetic method
   private static void getLOCK_FAIL$annotations() {
   }

   // $FF: synthetic method
   private static void getSELECT_SUCCESS$annotations() {
   }

   // $FF: synthetic method
   private static void getUNLOCKED$annotations() {
   }

   // $FF: synthetic method
   private static void getUNLOCK_FAIL$annotations() {
   }

   public static final Object withLock(Mutex var0, Object var1, Function0 var2, Continuation var3) {
      Object var5;
      label58: {
         if (var3 instanceof <undefinedtype>) {
            var5 = (<undefinedtype>)var3;
            if ((((<undefinedtype>)var5).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)var5).label += Integer.MIN_VALUE;
               break label58;
            }
         }

         var5 = new ContinuationImpl(var3) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return MutexKt.withLock((Mutex)null, (Object)null, (Function0)null, this);
            }
         };
      }

      Object var8 = ((<undefinedtype>)var5).result;
      Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var4 = ((<undefinedtype>)var5).label;
      Object var6;
      Mutex var12;
      if (var4 != 0) {
         if (var4 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var2 = (Function0)((<undefinedtype>)var5).L$2;
         var6 = ((<undefinedtype>)var5).L$1;
         var12 = (Mutex)((<undefinedtype>)var5).L$0;
         ResultKt.throwOnFailure(var8);
      } else {
         ResultKt.throwOnFailure(var8);
         ((<undefinedtype>)var5).L$0 = var0;
         ((<undefinedtype>)var5).L$1 = var1;
         ((<undefinedtype>)var5).L$2 = var2;
         ((<undefinedtype>)var5).label = 1;
         var12 = var0;
         var6 = var1;
         if (var0.lock(var1, (Continuation)var5) == var7) {
            return var7;
         }
      }

      Object var11;
      try {
         var11 = var2.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var12.unlock(var6);
         InlineMarker.finallyEnd(1);
      }

      return var11;
   }

   private static final Object withLock$$forInline(Mutex var0, Object var1, Function0 var2, Continuation var3) {
      InlineMarker.mark(0);
      var0.lock(var1, var3);
      InlineMarker.mark(2);
      InlineMarker.mark(1);

      Object var6;
      try {
         var6 = var2.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var0.unlock(var1);
         InlineMarker.finallyEnd(1);
      }

      return var6;
   }

   // $FF: synthetic method
   public static Object withLock$default(Mutex var0, Object var1, Function0 var2, Continuation var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = null;
      }

      InlineMarker.mark(0);
      var0.lock(var1, var3);
      InlineMarker.mark(2);
      InlineMarker.mark(1);

      Object var8;
      try {
         var8 = var2.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var0.unlock(var1);
         InlineMarker.finallyEnd(1);
      }

      return var8;
   }
}
