package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012 \u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0014¢\u0006\u0002\u0010\u0015R\u0014\u0010\b\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0014\u0010\u000b\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\n¨\u0006\u0016"},
   d2 = {"Lkotlinx/coroutines/channels/LinkedListChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class LinkedListChannel extends AbstractChannel {
   public LinkedListChannel(Function1 var1) {
      super(var1);
   }

   protected final boolean isBufferAlwaysEmpty() {
      return true;
   }

   protected final boolean isBufferAlwaysFull() {
      return false;
   }

   protected final boolean isBufferEmpty() {
      return true;
   }

   protected final boolean isBufferFull() {
      return false;
   }

   protected Object offerInternal(Object var1) {
      while(true) {
         Object var2 = super.offerInternal(var1);
         if (var2 == AbstractChannelKt.OFFER_SUCCESS) {
            return AbstractChannelKt.OFFER_SUCCESS;
         }

         if (var2 == AbstractChannelKt.OFFER_FAILED) {
            ReceiveOrClosed var3 = this.sendBuffered(var1);
            if (var3 == null) {
               return AbstractChannelKt.OFFER_SUCCESS;
            }

            if (!(var3 instanceof Closed)) {
               continue;
            }

            return var3;
         }

         if (var2 instanceof Closed) {
            return var2;
         }

         throw (Throwable)(new IllegalStateException(("Invalid offerInternal result " + var2).toString()));
      }
   }

   protected Object offerSelectInternal(Object var1, SelectInstance var2) {
      Object var3;
      do {
         if (this.getHasReceiveOrClosed()) {
            var3 = super.offerSelectInternal(var1, var2);
         } else {
            var3 = var2.performAtomicTrySelect((AtomicDesc)this.describeSendBuffered(var1));
            if (var3 == null) {
               var3 = AbstractChannelKt.OFFER_SUCCESS;
            }
         }

         if (var3 == SelectKt.getALREADY_SELECTED()) {
            return SelectKt.getALREADY_SELECTED();
         }

         if (var3 == AbstractChannelKt.OFFER_SUCCESS) {
            return AbstractChannelKt.OFFER_SUCCESS;
         }
      } while(var3 == AbstractChannelKt.OFFER_FAILED || var3 == AtomicKt.RETRY_ATOMIC);

      if (var3 instanceof Closed) {
         return var3;
      } else {
         throw (Throwable)(new IllegalStateException(("Invalid result " + var3).toString()));
      }
   }
}
