package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\nH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"},
   d2 = {"Lkotlinx/coroutines/scheduling/DefaultScheduler;", "Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "()V", "IO", "Lkotlinx/coroutines/CoroutineDispatcher;", "getIO", "()Lkotlinx/coroutines/CoroutineDispatcher;", "close", "", "toDebugString", "", "toString", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class DefaultScheduler extends ExperimentalCoroutineDispatcher {
   public static final DefaultScheduler INSTANCE;
   private static final CoroutineDispatcher IO;

   static {
      DefaultScheduler var0 = new DefaultScheduler();
      INSTANCE = var0;
      IO = (CoroutineDispatcher)(new LimitingDispatcher((ExperimentalCoroutineDispatcher)var0, SystemPropsKt.systemProp$default("kotlinx.coroutines.io.parallelism", RangesKt.coerceAtLeast(64, SystemPropsKt.getAVAILABLE_PROCESSORS()), 0, 0, 12, (Object)null), "Dispatchers.IO", 1));
   }

   private DefaultScheduler() {
      super(0, 0, (String)null, 7, (DefaultConstructorMarker)null);
   }

   public void close() {
      throw (Throwable)(new UnsupportedOperationException("Dispatchers.Default cannot be closed"));
   }

   public final CoroutineDispatcher getIO() {
      return IO;
   }

   public final String toDebugString() {
      return super.toString();
   }

   public String toString() {
      return "Dispatchers.Default";
   }
}
