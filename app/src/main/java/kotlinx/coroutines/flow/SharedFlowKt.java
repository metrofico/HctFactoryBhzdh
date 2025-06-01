package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a6\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00060\r\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a#\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\u0010\u0017\u001a+\u0010\u0018\u001a\u00020\u0019*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010\u001b\"\u0016\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006\u001c"},
   d2 = {"NO_VALUE", "Lkotlinx/coroutines/internal/Symbol;", "getNO_VALUE$annotations", "()V", "MutableSharedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "T", "replay", "", "extraBufferCapacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "fuseSharedFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharedFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "getBufferAt", "", "", "index", "", "([Ljava/lang/Object;J)Ljava/lang/Object;", "setBufferAt", "", "item", "([Ljava/lang/Object;JLjava/lang/Object;)V", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class SharedFlowKt {
   public static final Symbol NO_VALUE = new Symbol("NO_VALUE");

   public static final MutableSharedFlow MutableSharedFlow(int var0, int var1, BufferOverflow var2) {
      boolean var4 = true;
      boolean var3;
      if (var0 >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         if (var1 >= 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            var3 = var4;
            if (var0 <= 0) {
               var3 = var4;
               if (var1 <= 0) {
                  if (var2 == BufferOverflow.SUSPEND) {
                     var3 = var4;
                  } else {
                     var3 = false;
                  }
               }
            }

            if (var3) {
               int var5 = var1 + var0;
               var1 = var5;
               if (var5 < 0) {
                  var1 = Integer.MAX_VALUE;
               }

               return (MutableSharedFlow)(new SharedFlowImpl(var0, var1, var2));
            } else {
               throw (Throwable)(new IllegalArgumentException(("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy " + var2).toString()));
            }
         } else {
            throw (Throwable)(new IllegalArgumentException(("extraBufferCapacity cannot be negative, but was " + var1).toString()));
         }
      } else {
         throw (Throwable)(new IllegalArgumentException(("replay cannot be negative, but was " + var0).toString()));
      }
   }

   // $FF: synthetic method
   public static MutableSharedFlow MutableSharedFlow$default(int var0, int var1, BufferOverflow var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var0 = 0;
      }

      if ((var3 & 2) != 0) {
         var1 = 0;
      }

      if ((var3 & 4) != 0) {
         var2 = BufferOverflow.SUSPEND;
      }

      return MutableSharedFlow(var0, var1, var2);
   }

   // $FF: synthetic method
   public static final Object access$getBufferAt(Object[] var0, long var1) {
      return getBufferAt(var0, var1);
   }

   // $FF: synthetic method
   public static final void access$setBufferAt(Object[] var0, long var1, Object var3) {
      setBufferAt(var0, var1, var3);
   }

   public static final Flow fuseSharedFlow(SharedFlow var0, CoroutineContext var1, int var2, BufferOverflow var3) {
      return (var2 == 0 || var2 == -3) && var3 == BufferOverflow.SUSPEND ? (Flow)var0 : (Flow)(new ChannelFlowOperatorImpl((Flow)var0, var1, var2, var3));
   }

   private static final Object getBufferAt(Object[] var0, long var1) {
      return var0[(int)var1 & var0.length - 1];
   }

   // $FF: synthetic method
   public static void getNO_VALUE$annotations() {
   }

   private static final void setBufferAt(Object[] var0, long var1, Object var3) {
      var0[(int)var1 & var0.length - 1] = var3;
   }
}
