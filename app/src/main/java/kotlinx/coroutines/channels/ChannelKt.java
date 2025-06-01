package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0007\u001a>\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b¨\u0006\n"},
   d2 = {"Channel", "Lkotlinx/coroutines/channels/Channel;", "E", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "onUndeliveredElement", "Lkotlin/Function1;", "", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ChannelKt {
   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.4.0, binary compatibility with earlier versions"
   )
   public static final Channel Channel(int var0) {
      return Channel$default(var0, (BufferOverflow)null, (Function1)null, 6, (Object)null);
   }

   public static final Channel Channel(int var0, BufferOverflow var1, Function1 var2) {
      byte var3 = 1;
      boolean var4 = true;
      Channel var7;
      if (var0 != -2) {
         if (var0 != -1) {
            AbstractChannel var6;
            if (var0 != 0) {
               if (var0 != Integer.MAX_VALUE) {
                  if (var0 == 1 && var1 == BufferOverflow.DROP_OLDEST) {
                     var6 = (AbstractChannel)(new ConflatedChannel(var2));
                  } else {
                     var6 = (AbstractChannel)(new ArrayChannel(var0, var1, var2));
                  }

                  var7 = (Channel)var6;
               } else {
                  var7 = (Channel)(new LinkedListChannel(var2));
               }
            } else {
               if (var1 == BufferOverflow.SUSPEND) {
                  var6 = (AbstractChannel)(new RendezvousChannel(var2));
               } else {
                  var6 = (AbstractChannel)(new ArrayChannel(1, var1, var2));
               }

               var7 = (Channel)var6;
            }
         } else {
            boolean var5;
            if (var1 == BufferOverflow.SUSPEND) {
               var5 = var4;
            } else {
               var5 = false;
            }

            if (!var5) {
               throw (Throwable)(new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString()));
            }

            var7 = (Channel)(new ConflatedChannel(var2));
         }
      } else {
         var0 = var3;
         if (var1 == BufferOverflow.SUSPEND) {
            var0 = Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core();
         }

         var7 = (Channel)(new ArrayChannel(var0, var1, var2));
      }

      return var7;
   }

   // $FF: synthetic method
   public static Channel Channel$default(int var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = 0;
      }

      return Channel(var0);
   }

   // $FF: synthetic method
   public static Channel Channel$default(int var0, BufferOverflow var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var0 = 0;
      }

      if ((var3 & 2) != 0) {
         var1 = BufferOverflow.SUSPEND;
      }

      if ((var3 & 4) != 0) {
         var2 = null;
         Function1 var5 = (Function1)null;
      }

      return Channel(var0, var1, var2);
   }
}
