package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\u0014\u0010\f\u001a\u00020\u00062\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u0015"},
   d2 = {"Lkotlinx/coroutines/channels/SendElement;", "E", "Lkotlinx/coroutines/channels/Send;", "pollResult", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "getPollResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "completeResumeSend", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class SendElement extends Send {
   public final CancellableContinuation cont;
   private final Object pollResult;

   public SendElement(Object var1, CancellableContinuation var2) {
      this.pollResult = var1;
      this.cont = var2;
   }

   public void completeResumeSend() {
      this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
   }

   public Object getPollResult() {
      return this.pollResult;
   }

   public void resumeSendClosed(Closed var1) {
      Continuation var2 = (Continuation)this.cont;
      Throwable var3 = var1.getSendException();
      Result.Companion var4 = Result.Companion;
      var2.resumeWith(Result.constructor_impl(ResultKt.createFailure(var3)));
   }

   public String toString() {
      return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + '(' + this.getPollResult() + ')';
   }

   public Symbol tryResumeSend(PrepareOp var1) {
      CancellableContinuation var4 = this.cont;
      Unit var5 = Unit.INSTANCE;
      AbstractAtomicDesc var3;
      if (var1 != null) {
         var3 = var1.desc;
      } else {
         var3 = null;
      }

      Object var6 = var4.tryResume(var5, var3);
      if (var6 != null) {
         if (DebugKt.getASSERTIONS_ENABLED()) {
            boolean var2;
            if (var6 == CancellableContinuationImplKt.RESUME_TOKEN) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (!var2) {
               throw (Throwable)(new AssertionError());
            }
         }

         if (var1 != null) {
            var1.finishPrepare();
         }

         return CancellableContinuationImplKt.RESUME_TOKEN;
      } else {
         return null;
      }
   }
}
