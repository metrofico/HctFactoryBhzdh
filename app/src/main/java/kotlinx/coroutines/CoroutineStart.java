package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JC\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\n2\u001c\u0010\u000b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0087\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\\\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\n2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0012¢\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u0002H\u00112\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0087\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0007j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"},
   d2 = {"Lkotlinx/coroutines/CoroutineStart;", "", "(Ljava/lang/String;I)V", "isLazy", "", "isLazy$annotations", "()V", "()Z", "invoke", "", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "DEFAULT", "LAZY", "ATOMIC", "UNDISPATCHED", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public enum CoroutineStart {
   private static final CoroutineStart[] $VALUES;
   ATOMIC,
   DEFAULT,
   LAZY,
   UNDISPATCHED;

   static {
      CoroutineStart var3 = new CoroutineStart("DEFAULT", 0);
      DEFAULT = var3;
      CoroutineStart var0 = new CoroutineStart("LAZY", 1);
      LAZY = var0;
      CoroutineStart var1 = new CoroutineStart("ATOMIC", 2);
      ATOMIC = var1;
      CoroutineStart var2 = new CoroutineStart("UNDISPATCHED", 3);
      UNDISPATCHED = var2;
      $VALUES = new CoroutineStart[]{var3, var0, var1, var2};
   }

   // $FF: synthetic method
   public static void isLazy$annotations() {
   }

   public final void invoke(Function1 var1, Continuation var2) {
      int var3 = CoroutineStart$WhenMappings.$EnumSwitchMapping$0[this.ordinal()];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 4) {
                  throw new NoWhenBranchMatchedException();
               }
            } else {
               UndispatchedKt.startCoroutineUndispatched(var1, var2);
            }
         } else {
            ContinuationKt.startCoroutine(var1, var2);
         }
      } else {
         CancellableKt.startCoroutineCancellable(var1, var2);
      }

   }

   public final void invoke(Function2 var1, Object var2, Continuation var3) {
      int var4 = CoroutineStart$WhenMappings.$EnumSwitchMapping$1[this.ordinal()];
      if (var4 != 1) {
         if (var4 != 2) {
            if (var4 != 3) {
               if (var4 != 4) {
                  throw new NoWhenBranchMatchedException();
               }
            } else {
               UndispatchedKt.startCoroutineUndispatched(var1, var2, var3);
            }
         } else {
            ContinuationKt.startCoroutine(var1, var2, var3);
         }
      } else {
         CancellableKt.startCoroutineCancellable$default(var1, var2, var3, (Function1)null, 4, (Object)null);
      }

   }

   public final boolean isLazy() {
      CoroutineStart var2 = (CoroutineStart)this;
      boolean var1;
      if (this == LAZY) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }
}
