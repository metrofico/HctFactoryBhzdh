package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0017J\u0014\u0010\u0015\u001a\u00020\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018H'J\u001a\u0010\u0015\u001a\u00020\u00162\u0010\b\u0002\u0010\u0017\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aH&J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH¦\u0002J\u000f\u0010\u001d\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u001eJ\u0011\u0010\u001f\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH§@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010 J\u0013\u0010#\u001a\u0004\u0018\u00018\u0000H§@ø\u0001\u0000¢\u0006\u0002\u0010 R\u001a\u0010\u0003\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0006\u001a\u0004\b\b\u0010\u0007R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR)\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000b8&X§\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0010\u0010\u0006\u001a\u0004\b\u0011\u0010\rR\"\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b8&X§\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0006\u001a\u0004\b\u0014\u0010\r\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006$"},
   d2 = {"Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isEmpty", "isEmpty$annotations", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrClosed", "Lkotlinx/coroutines/channels/ValueOrClosed;", "getOnReceiveOrClosed$annotations", "getOnReceiveOrClosed", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrClosed", "receiveOrClosed-ZYPwvRU", "receiveOrNull", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface ReceiveChannel {
   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   void cancel();

   void cancel(CancellationException var1);

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   boolean cancel(Throwable var1);

   SelectClause1 getOnReceive();

   SelectClause1 getOnReceiveOrClosed();

   SelectClause1 getOnReceiveOrNull();

   boolean isClosedForReceive();

   boolean isEmpty();

   ChannelIterator iterator();

   Object poll();

   Object receive(Continuation var1);

   Object receiveOrClosed_ZYPwvRU(Continuation var1);

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Deprecated in favor of receiveOrClosed and receiveOrNull extension",
      replaceWith = @ReplaceWith(
   expression = "receiveOrNull",
   imports = {"kotlinx.coroutines.channels.receiveOrNull"}
)
   )
   Object receiveOrNull(Continuation var1);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      @Deprecated(
         level = DeprecationLevel.HIDDEN,
         message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
      )
      public static void cancel(ReceiveChannel var0) {
         var0.cancel((CancellationException)null);
      }

      // $FF: synthetic method
      public static void cancel$default(ReceiveChannel var0, CancellationException var1, int var2, Object var3) {
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
      public static boolean cancel$default(ReceiveChannel var0, Throwable var1, int var2, Object var3) {
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

      // $FF: synthetic method
      public static void getOnReceiveOrClosed$annotations() {
      }

      // $FF: synthetic method
      @Deprecated(
         level = DeprecationLevel.WARNING,
         message = "Deprecated in favor of onReceiveOrClosed and onReceiveOrNull extension",
         replaceWith = @ReplaceWith(
   expression = "onReceiveOrNull",
   imports = {"kotlinx.coroutines.channels.onReceiveOrNull"}
)
      )
      public static void getOnReceiveOrNull$annotations() {
      }

      // $FF: synthetic method
      public static void isClosedForReceive$annotations() {
      }

      // $FF: synthetic method
      public static void isEmpty$annotations() {
      }
   }
}
