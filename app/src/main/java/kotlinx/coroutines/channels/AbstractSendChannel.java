package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u000005:\u0004abcdB)\u0012 \u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0013\u001a\u000e\u0012\u0002\b\u00030\u0011j\u0006\u0012\u0002\b\u0003`\u00122\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001f\u001a\u00020\u00032\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J#\u0010!\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\b!\u0010\"J)\u0010%\u001a\u00020\u00032\u0018\u0010$\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`#H\u0016¢\u0006\u0004\b%\u0010\u0007J\u0019\u0010&\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b&\u0010'J\u0015\u0010(\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00028\u0000H\u0014¢\u0006\u0004\b*\u0010+J#\u0010.\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010-\u001a\u0006\u0012\u0002\b\u00030,H\u0014¢\u0006\u0004\b.\u0010/J\u0017\u00101\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u000200H\u0014¢\u0006\u0004\b1\u00102JX\u00108\u001a\u00020\u0003\"\u0004\b\u0001\u001032\f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00010,2\u0006\u0010\u0010\u001a\u00028\u00002(\u00107\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000005\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000106\u0012\u0006\u0012\u0004\u0018\u00010\u001a04H\u0002ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001b\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010:J\u001d\u0010<\u001a\b\u0012\u0002\b\u0003\u0018\u00010;2\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0004\b<\u0010=J\u001b\u0010>\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b>\u0010:J\u0017\u0010?\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010;H\u0014¢\u0006\u0004\b?\u0010@J\u0011\u0010A\u001a\u0004\u0018\u00010\u0018H\u0004¢\u0006\u0004\bA\u0010BJ\u000f\u0010D\u001a\u00020CH\u0016¢\u0006\u0004\bD\u0010EJ+\u0010F\u001a\u00020\u0003*\u0006\u0012\u0002\b\u0003062\u0006\u0010\u0010\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002¢\u0006\u0004\bF\u0010GR\u0016\u0010I\u001a\u00020C8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\bH\u0010ER\u001c\u0010L\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u001c\u0010N\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bM\u0010KR\u0016\u0010O\u001a\u00020\n8$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020\n8$@$X¤\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010PR\u0013\u0010R\u001a\u00020\n8F@\u0006¢\u0006\u0006\u001a\u0004\bR\u0010PR\u0016\u0010S\u001a\u00020\n8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010PR\u0016\u0010T\u001a\u00020\n8D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\bT\u0010PR%\u0010X\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000050U8F@\u0006¢\u0006\u0006\u001a\u0004\bV\u0010WR0\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00048\u0004@\u0005X\u0085\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010YR\u001c\u0010[\u001a\u00020Z8\u0004@\u0004X\u0084\u0004¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^R\u0016\u0010`\u001a\u00020C8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b_\u0010E\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006e"},
   d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel;", "E", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "", "countQueueSize", "()I", "element", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/internal/AddLastDesc;", "describeSendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "describeTryOffer", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Closed;", "closed", "helpClose", "(Lkotlinx/coroutines/channels/Closed;)V", "helpCloseAndGetSendException", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)Ljava/lang/Throwable;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendBuffered", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ReceiveOrClosed;", "sendSuspend", "takeFirstReceiveOrPeekClosed", "()Lkotlinx/coroutines/channels/ReceiveOrClosed;", "takeFirstSendOrPeekClosed", "()Lkotlinx/coroutines/channels/Send;", "", "toString", "()Ljava/lang/String;", "helpCloseAndResumeWithSendException", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlinx/coroutines/channels/Closed;)V", "getBufferDebugString", "bufferDebugString", "getClosedForReceive", "()Lkotlinx/coroutines/channels/Closed;", "closedForReceive", "getClosedForSend", "closedForSend", "isBufferAlwaysFull", "()Z", "isBufferFull", "isClosedForSend", "isFull", "isFullImpl", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getQueueDebugStateString", "queueDebugStateString", "SendBuffered", "SendBufferedDesc", "SendSelect", "TryOfferDesc", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class AbstractSendChannel implements SendChannel {
   private static final AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
   private volatile Object onCloseHandler;
   protected final Function1 onUndeliveredElement;
   private final LockFreeLinkedListHead queue;

   public AbstractSendChannel(Function1 var1) {
      this.onUndeliveredElement = var1;
      this.queue = new LockFreeLinkedListHead();
      this.onCloseHandler = null;
   }

   // $FF: synthetic method
   public static final void access$helpCloseAndResumeWithSendException(AbstractSendChannel var0, Continuation var1, Object var2, Closed var3) {
      var0.helpCloseAndResumeWithSendException(var1, var2, var3);
   }

   private final int countQueueSize() {
      LockFreeLinkedListHead var4 = this.queue;
      Object var3 = var4.getNext();
      if (var3 != null) {
         LockFreeLinkedListNode var5 = (LockFreeLinkedListNode)var3;

         int var1;
         int var2;
         for(var2 = 0; Intrinsics.areEqual((Object)var5, (Object)var4) ^ true; var2 = var1) {
            var1 = var2;
            if (var5 instanceof LockFreeLinkedListNode) {
               var1 = var2 + 1;
            }

            var5 = var5.getNextNode();
         }

         return var2;
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      }
   }

   private final String getQueueDebugStateString() {
      LockFreeLinkedListNode var3 = this.queue.getNextNode();
      if (var3 == this.queue) {
         return "EmptyQueue";
      } else {
         String var1;
         if (var3 instanceof Closed) {
            var1 = var3.toString();
         } else if (var3 instanceof Receive) {
            var1 = "ReceiveQueued";
         } else if (var3 instanceof Send) {
            var1 = "SendQueued";
         } else {
            var1 = "UNEXPECTED:" + var3;
         }

         LockFreeLinkedListNode var4 = this.queue.getPrevNode();
         String var2 = var1;
         if (var4 != var3) {
            var1 = var1 + ",queueSize=" + this.countQueueSize();
            var2 = var1;
            if (var4 instanceof Closed) {
               var2 = var1 + ",closedForSend=" + var4;
            }
         }

         return var2;
      }
   }

   private final void helpClose(Closed var1) {
      Object var3 = InlineList.constructor_impl$default((Object)null, 1, (DefaultConstructorMarker)null);

      while(true) {
         LockFreeLinkedListNode var5 = var1.getPrevNode();
         LockFreeLinkedListNode var4 = var5;
         if (!(var5 instanceof Receive)) {
            var4 = null;
         }

         Receive var7 = (Receive)var4;
         if (var7 == null) {
            if (var3 != null) {
               if (!(var3 instanceof ArrayList)) {
                  ((Receive)var3).resumeReceiveClosed(var1);
               } else {
                  if (var3 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
                  }

                  ArrayList var6 = (ArrayList)var3;

                  for(int var2 = var6.size() - 1; var2 >= 0; --var2) {
                     ((Receive)var6.get(var2)).resumeReceiveClosed(var1);
                  }
               }
            }

            this.onClosedIdempotent((LockFreeLinkedListNode)var1);
            return;
         }

         if (!var7.remove()) {
            var7.helpRemove();
         } else {
            var3 = InlineList.plus_UZ7vuAc(var3, var7);
         }
      }
   }

   private final Throwable helpCloseAndGetSendException(Object var1, Closed var2) {
      this.helpClose(var2);
      Function1 var3 = this.onUndeliveredElement;
      if (var3 != null) {
         UndeliveredElementException var4 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(var3, var1, (UndeliveredElementException)null, 2, (Object)null);
         if (var4 != null) {
            Throwable var5 = (Throwable)var4;
            ExceptionsKt.addSuppressed(var5, var2.getSendException());
            throw var5;
         }
      }

      return var2.getSendException();
   }

   private final void helpCloseAndResumeWithSendException(Continuation var1, Object var2, Closed var3) {
      this.helpClose(var3);
      Throwable var8 = var3.getSendException();
      Function1 var4 = this.onUndeliveredElement;
      if (var4 != null) {
         UndeliveredElementException var5 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(var4, var2, (UndeliveredElementException)null, 2, (Object)null);
         if (var5 != null) {
            Throwable var7 = (Throwable)var5;
            ExceptionsKt.addSuppressed(var7, var8);
            Result.Companion var9 = Result.Companion;
            var1.resumeWith(Result.constructor_impl(ResultKt.createFailure(var7)));
            return;
         }
      }

      Result.Companion var6 = Result.Companion;
      var1.resumeWith(Result.constructor_impl(ResultKt.createFailure(var8)));
   }

   private final void invokeOnCloseHandler(Throwable var1) {
      Object var2 = this.onCloseHandler;
      if (var2 != null && var2 != AbstractChannelKt.HANDLER_INVOKED && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(onCloseHandler$FU, this, var2, AbstractChannelKt.HANDLER_INVOKED)) {
         ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var2, 1)).invoke(var1);
      }

   }

   private final void registerSelectSend(SelectInstance var1, Object var2, Function2 var3) {
      Object var6;
      do {
         if (var1.isSelected()) {
            return;
         }

         if (this.isFullImpl()) {
            SendSelect var4 = new SendSelect(var2, this, var1, var3);
            Object var5 = this.enqueueSend((Send)var4);
            if (var5 == null) {
               var1.disposeOnSelect((DisposableHandle)var4);
               return;
            }

            if (var5 instanceof Closed) {
               throw StackTraceRecoveryKt.recoverStackTrace(this.helpCloseAndGetSendException(var2, (Closed)var5));
            }

            if (var5 != AbstractChannelKt.ENQUEUE_FAILED && !(var5 instanceof Receive)) {
               throw (Throwable)(new IllegalStateException(("enqueueSend returned " + var5 + ' ').toString()));
            }
         }

         var6 = this.offerSelectInternal(var2, var1);
         if (var6 == SelectKt.getALREADY_SELECTED()) {
            return;
         }
      } while(var6 == AbstractChannelKt.OFFER_FAILED || var6 == AtomicKt.RETRY_ATOMIC);

      if (var6 == AbstractChannelKt.OFFER_SUCCESS) {
         UndispatchedKt.startCoroutineUnintercepted(var3, this, var1.getCompletion());
      } else if (var6 instanceof Closed) {
         throw StackTraceRecoveryKt.recoverStackTrace(this.helpCloseAndGetSendException(var2, (Closed)var6));
      } else {
         throw (Throwable)(new IllegalStateException(("offerSelectInternal returned " + var6).toString()));
      }
   }

   public boolean close(Throwable var1) {
      Closed var4 = new Closed(var1);
      LockFreeLinkedListNode var5 = (LockFreeLinkedListNode)this.queue;

      boolean var2;
      LockFreeLinkedListNode var6;
      do {
         var6 = var5.getPrevNode();
         boolean var3 = var6 instanceof Closed;
         var2 = true;
         if (!(var3 ^ true)) {
            var2 = false;
            break;
         }
      } while(!var6.addNext((LockFreeLinkedListNode)var4, var5));

      if (!var2) {
         LockFreeLinkedListNode var7 = this.queue.getPrevNode();
         if (var7 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Closed<*>");
         }

         var4 = (Closed)var7;
      }

      this.helpClose(var4);
      if (var2) {
         this.invokeOnCloseHandler(var1);
      }

      return var2;
   }

   protected final LockFreeLinkedListNode.AddLastDesc describeSendBuffered(Object var1) {
      return (LockFreeLinkedListNode.AddLastDesc)(new SendBufferedDesc(this.queue, var1));
   }

   protected final TryOfferDesc describeTryOffer(Object var1) {
      return new TryOfferDesc(var1, this.queue);
   }

   protected Object enqueueSend(Send var1) {
      LockFreeLinkedListNode var4;
      LockFreeLinkedListNode var5;
      if (this.isBufferAlwaysFull()) {
         var5 = (LockFreeLinkedListNode)this.queue;

         do {
            var4 = var5.getPrevNode();
            if (var4 instanceof ReceiveOrClosed) {
               return var4;
            }
         } while(!var4.addNext((LockFreeLinkedListNode)var1, var5));
      } else {
         var4 = (LockFreeLinkedListNode)this.queue;
         var5 = (LockFreeLinkedListNode)var1;
         LockFreeLinkedListNode.CondAddOp var7 = (LockFreeLinkedListNode.CondAddOp)(new LockFreeLinkedListNode.CondAddOp(var5, var5, this) {
            final LockFreeLinkedListNode $node;
            final AbstractSendChannel this$0;

            public {
               this.$node = var1;
               this.this$0 = var3;
            }

            public Object prepare(LockFreeLinkedListNode var1) {
               Object var2;
               if (this.this$0.isBufferFull()) {
                  var2 = null;
               } else {
                  var2 = LockFreeLinkedListKt.getCONDITION_FALSE();
               }

               return var2;
            }
         });

         boolean var2;
         while(true) {
            LockFreeLinkedListNode var6 = var4.getPrevNode();
            if (var6 instanceof ReceiveOrClosed) {
               return var6;
            }

            int var3 = var6.tryCondAddNext(var5, var4, var7);
            var2 = true;
            if (var3 == 1) {
               break;
            }

            if (var3 == 2) {
               var2 = false;
               break;
            }
         }

         if (!var2) {
            return AbstractChannelKt.ENQUEUE_FAILED;
         }
      }

      return null;
   }

   protected String getBufferDebugString() {
      return "";
   }

   protected final Closed getClosedForReceive() {
      LockFreeLinkedListNode var2 = this.queue.getNextNode();
      boolean var1 = var2 instanceof Closed;
      Object var3 = null;
      if (!var1) {
         var2 = null;
      }

      Closed var4 = (Closed)var2;
      Closed var5 = (Closed)var3;
      if (var4 != null) {
         this.helpClose(var4);
         var5 = var4;
      }

      return var5;
   }

   protected final Closed getClosedForSend() {
      LockFreeLinkedListNode var2 = this.queue.getPrevNode();
      boolean var1 = var2 instanceof Closed;
      Object var3 = null;
      if (!var1) {
         var2 = null;
      }

      Closed var4 = (Closed)var2;
      Closed var5 = (Closed)var3;
      if (var4 != null) {
         this.helpClose(var4);
         var5 = var4;
      }

      return var5;
   }

   public final SelectClause2 getOnSend() {
      return (SelectClause2)(new SelectClause2(this) {
         final AbstractSendChannel this$0;

         {
            this.this$0 = var1;
         }

         public void registerSelectClause2(SelectInstance var1, Object var2, Function2 var3) {
            this.this$0.registerSelectSend(var1, var2, var3);
         }
      });
   }

   protected final LockFreeLinkedListHead getQueue() {
      return this.queue;
   }

   public void invokeOnClose(Function1 var1) {
      AtomicReferenceFieldUpdater var2 = onCloseHandler$FU;
      if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(var2, this, (Object)null, var1)) {
         Object var4 = this.onCloseHandler;
         if (var4 == AbstractChannelKt.HANDLER_INVOKED) {
            throw (Throwable)(new IllegalStateException("Another handler was already registered and successfully invoked"));
         } else {
            throw (Throwable)(new IllegalStateException("Another handler was already registered: " + var4));
         }
      } else {
         Closed var3 = this.getClosedForSend();
         if (var3 != null && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(var2, this, var1, AbstractChannelKt.HANDLER_INVOKED)) {
            var1.invoke(var3.closeCause);
         }

      }
   }

   protected abstract boolean isBufferAlwaysFull();

   protected abstract boolean isBufferFull();

   public final boolean isClosedForSend() {
      boolean var1;
      if (this.getClosedForSend() != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isFull() {
      return this.isFullImpl();
   }

   protected final boolean isFullImpl() {
      boolean var1;
      if (!(this.queue.getNextNode() instanceof ReceiveOrClosed) && this.isBufferFull()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean offer(Object var1) {
      Object var2 = this.offerInternal(var1);
      if (var2 == AbstractChannelKt.OFFER_SUCCESS) {
         return true;
      } else if (var2 == AbstractChannelKt.OFFER_FAILED) {
         Closed var3 = this.getClosedForSend();
         if (var3 == null) {
            return false;
         } else {
            throw StackTraceRecoveryKt.recoverStackTrace(this.helpCloseAndGetSendException(var1, var3));
         }
      } else if (var2 instanceof Closed) {
         throw StackTraceRecoveryKt.recoverStackTrace(this.helpCloseAndGetSendException(var1, (Closed)var2));
      } else {
         throw (Throwable)(new IllegalStateException(("offerInternal returned " + var2).toString()));
      }
   }

   protected Object offerInternal(Object var1) {
      while(true) {
         ReceiveOrClosed var4 = this.takeFirstReceiveOrPeekClosed();
         if (var4 != null) {
            Symbol var3 = var4.tryResumeReceive(var1, (LockFreeLinkedListNode.PrepareOp)null);
            if (var3 == null) {
               continue;
            }

            if (DebugKt.getASSERTIONS_ENABLED()) {
               boolean var2;
               if (var3 == CancellableContinuationImplKt.RESUME_TOKEN) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (!var2) {
                  throw (Throwable)(new AssertionError());
               }
            }

            var4.completeResumeReceive(var1);
            return var4.getOfferResult();
         }

         return AbstractChannelKt.OFFER_FAILED;
      }
   }

   protected Object offerSelectInternal(Object var1, SelectInstance var2) {
      TryOfferDesc var3 = this.describeTryOffer(var1);
      Object var4 = var2.performAtomicTrySelect((AtomicDesc)var3);
      if (var4 != null) {
         return var4;
      } else {
         ReceiveOrClosed var5 = (ReceiveOrClosed)var3.getResult();
         var5.completeResumeReceive(var1);
         return var5.getOfferResult();
      }
   }

   protected void onClosedIdempotent(LockFreeLinkedListNode var1) {
   }

   public final Object send(Object var1, Continuation var2) {
      if (this.offerInternal(var1) == AbstractChannelKt.OFFER_SUCCESS) {
         return Unit.INSTANCE;
      } else {
         var1 = this.sendSuspend(var1, var2);
         return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
      }
   }

   protected final ReceiveOrClosed sendBuffered(Object var1) {
      LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)this.queue;
      LockFreeLinkedListNode var3 = (LockFreeLinkedListNode)(new SendBuffered(var1));

      LockFreeLinkedListNode var4;
      do {
         var4 = var2.getPrevNode();
         if (var4 instanceof ReceiveOrClosed) {
            return (ReceiveOrClosed)var4;
         }
      } while(!var4.addNext(var3, var2));

      return null;
   }

   // $FF: synthetic method
   final Object sendSuspend(Object var1, Continuation var2) {
      CancellableContinuationImpl var4 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(var2));
      CancellableContinuation var5 = (CancellableContinuation)var4;

      while(true) {
         if (this.isFullImpl()) {
            SendElement var3;
            if (this.onUndeliveredElement == null) {
               var3 = new SendElement(var1, var5);
            } else {
               var3 = (SendElement)(new SendElementWithUndeliveredHandler(var1, var5, this.onUndeliveredElement));
            }

            Object var6 = this.enqueueSend((Send)var3);
            if (var6 == null) {
               CancellableContinuationKt.removeOnCancellation(var5, (LockFreeLinkedListNode)var3);
               break;
            }

            if (var6 instanceof Closed) {
               access$helpCloseAndResumeWithSendException(this, (Continuation)var5, var1, (Closed)var6);
               break;
            }

            if (var6 != AbstractChannelKt.ENQUEUE_FAILED && !(var6 instanceof Receive)) {
               throw (Throwable)(new IllegalStateException(("enqueueSend returned " + var6).toString()));
            }
         }

         Object var8 = this.offerInternal(var1);
         if (var8 == AbstractChannelKt.OFFER_SUCCESS) {
            Continuation var10 = (Continuation)var5;
            Unit var9 = Unit.INSTANCE;
            Result.Companion var7 = Result.Companion;
            var10.resumeWith(Result.constructor_impl(var9));
            break;
         }

         if (var8 != AbstractChannelKt.OFFER_FAILED) {
            if (!(var8 instanceof Closed)) {
               throw (Throwable)(new IllegalStateException(("offerInternal returned " + var8).toString()));
            }

            access$helpCloseAndResumeWithSendException(this, (Continuation)var5, var1, (Closed)var8);
            break;
         }
      }

      var1 = var4.getResult();
      if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var1;
   }

   protected ReceiveOrClosed takeFirstReceiveOrPeekClosed() {
      LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)this.queue;

      LockFreeLinkedListNode var4;
      while(true) {
         Object var1 = var2.getNext();
         if (var1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
         }

         var4 = (LockFreeLinkedListNode)var1;
         if (var4 == var2 || !(var4 instanceof ReceiveOrClosed)) {
            var4 = null;
            break;
         }

         if ((ReceiveOrClosed)var4 instanceof Closed && !var4.isRemoved()) {
            break;
         }

         LockFreeLinkedListNode var3 = var4.removeOrNext();
         if (var3 == null) {
            break;
         }

         var3.helpRemovePrev();
      }

      return (ReceiveOrClosed)var4;
   }

   protected final Send takeFirstSendOrPeekClosed() {
      LockFreeLinkedListNode var2 = (LockFreeLinkedListNode)this.queue;

      LockFreeLinkedListNode var4;
      while(true) {
         Object var1 = var2.getNext();
         if (var1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
         }

         var4 = (LockFreeLinkedListNode)var1;
         if (var4 == var2 || !(var4 instanceof Send)) {
            var4 = null;
            break;
         }

         if ((Send)var4 instanceof Closed && !var4.isRemoved()) {
            break;
         }

         LockFreeLinkedListNode var3 = var4.removeOrNext();
         if (var3 == null) {
            break;
         }

         var3.helpRemovePrev();
      }

      return (Send)var4;
   }

   public String toString() {
      return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) + '{' + this.getQueueDebugStateString() + '}' + this.getBufferDebugString();
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0012\u0010\u0003\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class SendBuffered extends Send {
      public final Object element;

      public SendBuffered(Object var1) {
         this.element = var1;
      }

      public void completeResumeSend() {
      }

      public Object getPollResult() {
         return this.element;
      }

      public void resumeSendClosed(Closed var1) {
      }

      public String toString() {
         return "SendBuffered@" + DebugStringsKt.getHexAddress(this) + '(' + this.element + ')';
      }

      public Symbol tryResumeSend(PrepareOp var1) {
         Symbol var2 = CancellableContinuationImplKt.RESUME_TOKEN;
         if (var1 != null) {
            var1.finishPrepare();
         }

         return var2;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendBufferedDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static class SendBufferedDesc extends LockFreeLinkedListNode.AddLastDesc {
      public SendBufferedDesc(LockFreeLinkedListHead var1, Object var2) {
         super((LockFreeLinkedListNode)var1, (LockFreeLinkedListNode)(new SendBuffered(var2)));
      }

      protected Object failure(LockFreeLinkedListNode var1) {
         if (!(var1 instanceof Closed)) {
            if (var1 instanceof ReceiveOrClosed) {
               var1 = AbstractChannelKt.OFFER_FAILED;
            } else {
               var1 = null;
            }
         }

         return var1;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u00032\u00020\u0004BV\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t\u0012(\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u00020\u00152\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0015H\u0016R7\u0010\n\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$SendSelect;", "E", "R", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/DisposableHandle;", "pollResult", "channel", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/AbstractSendChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getPollResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "completeResumeSend", "", "dispose", "resumeSendClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeSend", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "undeliveredElement", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class SendSelect extends Send implements DisposableHandle {
      public final Function2 block;
      public final AbstractSendChannel channel;
      private final Object pollResult;
      public final SelectInstance select;

      public SendSelect(Object var1, AbstractSendChannel var2, SelectInstance var3, Function2 var4) {
         this.pollResult = var1;
         this.channel = var2;
         this.select = var3;
         this.block = var4;
      }

      public void completeResumeSend() {
         CancellableKt.startCoroutineCancellable$default(this.block, this.channel, this.select.getCompletion(), (Function1)null, 4, (Object)null);
      }

      public void dispose() {
         if (this.remove()) {
            this.undeliveredElement();
         }
      }

      public Object getPollResult() {
         return this.pollResult;
      }

      public void resumeSendClosed(Closed var1) {
         if (this.select.trySelect()) {
            this.select.resumeSelectWithException(var1.getSendException());
         }

      }

      public String toString() {
         return "SendSelect@" + DebugStringsKt.getHexAddress(this) + '(' + this.getPollResult() + ")[" + this.channel + ", " + this.select + ']';
      }

      public Symbol tryResumeSend(PrepareOp var1) {
         return (Symbol)this.select.trySelectOther(var1);
      }

      public void undeliveredElement() {
         Function1 var1 = this.channel.onUndeliveredElement;
         if (var1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(var1, this.getPollResult(), this.select.getCompletion().getContext());
         }

      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003`\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016R\u0012\u0010\u0005\u001a\u00028\u00018\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0012"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   protected static final class TryOfferDesc extends LockFreeLinkedListNode.RemoveFirstDesc {
      public final Object element;

      public TryOfferDesc(Object var1, LockFreeLinkedListHead var2) {
         super((LockFreeLinkedListNode)var2);
         this.element = var1;
      }

      protected Object failure(LockFreeLinkedListNode var1) {
         if (!(var1 instanceof Closed)) {
            if (!(var1 instanceof ReceiveOrClosed)) {
               var1 = AbstractChannelKt.OFFER_FAILED;
            } else {
               var1 = null;
            }
         }

         return var1;
      }

      public Object onPrepare(LockFreeLinkedListNode.PrepareOp var1) {
         LockFreeLinkedListNode var3 = var1.affected;
         if (var3 != null) {
            Symbol var4 = ((ReceiveOrClosed)var3).tryResumeReceive(this.element, var1);
            if (var4 != null) {
               if (var4 == AtomicKt.RETRY_ATOMIC) {
                  return AtomicKt.RETRY_ATOMIC;
               } else {
                  if (DebugKt.getASSERTIONS_ENABLED()) {
                     boolean var2;
                     if (var4 == CancellableContinuationImplKt.RESUME_TOKEN) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     if (!var2) {
                        throw (Throwable)(new AssertionError());
                     }
                  }

                  return null;
               }
            } else {
               return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
         } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveOrClosed<E>");
         }
      }
   }
}
