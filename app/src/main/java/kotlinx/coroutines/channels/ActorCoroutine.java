package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.ExceptionsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0014¨\u0006\u0011"},
   d2 = {"Lkotlinx/coroutines/channels/ActorCoroutine;", "E", "Lkotlinx/coroutines/channels/ChannelCoroutine;", "Lkotlinx/coroutines/channels/ActorScope;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "channel", "Lkotlinx/coroutines/channels/Channel;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;Z)V", "handleJobException", "exception", "", "onCancelling", "", "cause", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
class ActorCoroutine extends ChannelCoroutine implements ActorScope {
   public ActorCoroutine(CoroutineContext var1, Channel var2, boolean var3) {
      super(var1, var2, var3);
   }

   protected boolean handleJobException(Throwable var1) {
      CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), var1);
      return true;
   }

   protected void onCancelling(Throwable var1) {
      Channel var4 = this.get_channel();
      CancellationException var2 = null;
      Object var3 = null;
      if (var1 != null) {
         Throwable var5;
         if (!(var1 instanceof CancellationException)) {
            var5 = (Throwable)var3;
         } else {
            var5 = var1;
         }

         var2 = (CancellationException)var5;
         if (var2 == null) {
            var2 = ExceptionsKt.CancellationException(DebugStringsKt.getClassSimpleName(this) + " was cancelled", var1);
         }
      }

      var4.cancel(var2);
   }
}
