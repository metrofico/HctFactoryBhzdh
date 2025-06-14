package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'J\u001a\u0010\u0003\u001a\u00020\u00072\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tH&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH&¨\u0006\f"},
   d2 = {"Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "cancel", "", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface BroadcastChannel extends SendChannel {
   void cancel(CancellationException var1);

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Binary compatibility only"
   )
   boolean cancel(Throwable var1);

   ReceiveChannel openSubscription();

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      public static void cancel$default(BroadcastChannel var0, CancellationException var1, int var2, Object var3) {
         if (var3 == null) {
            if ((var2 & 1) != 0) {
               var1 = null;
               CancellationException var4 = (CancellationException)null;
            }

            var0.cancel(var1);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
         }
      }

      // $FF: synthetic method
      public static boolean cancel$default(BroadcastChannel var0, Throwable var1, int var2, Object var3) {
         if (var3 == null) {
            if ((var2 & 1) != 0) {
               var1 = null;
               Throwable var4 = (Throwable)null;
            }

            return var0.cancel(var1);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
         }
      }
   }
}
