package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u0010\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
   d2 = {"Landroidx/lifecycle/PausingDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "dispatchQueue", "Landroidx/lifecycle/DispatchQueue;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "isDispatchNeeded", "", "lifecycle-runtime-ktx_release"},
   k = 1,
   mv = {1, 4, 1}
)
public final class PausingDispatcher extends CoroutineDispatcher {
   public final DispatchQueue dispatchQueue = new DispatchQueue();

   public void dispatch(CoroutineContext var1, Runnable var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "block");
      this.dispatchQueue.dispatchAndEnqueue(var1, var2);
   }

   public boolean isDispatchNeeded(CoroutineContext var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      return Dispatchers.getMain().getImmediate().isDispatchNeeded(var1) ? true : this.dispatchQueue.canRun() ^ true;
   }
}
