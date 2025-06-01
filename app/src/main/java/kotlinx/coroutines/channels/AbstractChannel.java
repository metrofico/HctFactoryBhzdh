package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancelHandlerBase;
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
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0007PQRSTUVB'\u0012 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u0016\u0010\u001b\u001a\u00020\u00062\u000e\u0010\u001c\u001a\n\u0018\u00010\u001ej\u0004\u0018\u0001`\u001fJ\u0017\u0010 \u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b!J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0004J\u0016\u0010$\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0002J\u0016\u0010'\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0014JR\u0010(\u001a\u00020\n\"\u0004\b\u0001\u0010)2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+2$\u0010,\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H)0/\u0012\u0006\u0012\u0004\u0018\u00010.0-2\u0006\u00100\u001a\u000201H\u0002ø\u0001\u0000¢\u0006\u0002\u00102J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00028\u000004H\u0086\u0002J\u0010\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\nH\u0014J\b\u00107\u001a\u00020\u0006H\u0014J\b\u00108\u001a\u00020\u0006H\u0014J\r\u00109\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010:J\n\u0010;\u001a\u0004\u0018\u00010.H\u0014J\u0016\u0010<\u001a\u0004\u0018\u00010.2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+H\u0014J\u0011\u0010%\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J\u001f\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0086@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b?\u0010=J\u0013\u0010@\u001a\u0004\u0018\u00018\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010=J\u0019\u0010A\u001a\u0004\u0018\u00018\u00002\b\u0010B\u001a\u0004\u0018\u00010.H\u0002¢\u0006\u0002\u0010CJ\u001f\u0010D\u001a\u0002H)\"\u0004\b\u0001\u0010)2\u0006\u00100\u001a\u000201H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010EJR\u0010F\u001a\u00020\u0006\"\u0004\b\u0001\u0010)2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+2\u0006\u00100\u001a\u0002012$\u0010,\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H)0/\u0012\u0006\u0012\u0004\u0018\u00010.0-H\u0002ø\u0001\u0000¢\u0006\u0002\u0010GJ \u0010H\u001a\u00020\u00062\n\u0010I\u001a\u0006\u0012\u0002\b\u00030J2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030&H\u0002J\u0010\u0010K\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010LH\u0014JX\u0010M\u001a\u00020\u0006\"\u0004\b\u0001\u0010)* \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H)0/\u0012\u0006\u0012\u0004\u0018\u00010.0-2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+2\u0006\u00100\u001a\u0002012\b\u0010N\u001a\u0004\u0018\u00010.H\u0002ø\u0001\u0000¢\u0006\u0002\u0010OR\u0014\u0010\t\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\nX¤\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0012\u0010\u000e\u001a\u00020\nX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00170\u00138Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0015R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00138F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0015\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006W"},
   d2 = {"Lkotlinx/coroutines/channels/AbstractChannel;", "E", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/Channel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "hasReceiveOrClosed", "", "getHasReceiveOrClosed", "()Z", "isBufferAlwaysEmpty", "isBufferEmpty", "isClosedForReceive", "isEmpty", "isEmptyImpl", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrClosed", "Lkotlinx/coroutines/channels/ValueOrClosed;", "getOnReceiveOrClosed", "onReceiveOrNull", "getOnReceiveOrNull", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "cancelInternal$kotlinx_coroutines_core", "describeTryPoll", "Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "enqueueReceive", "receive", "Lkotlinx/coroutines/channels/Receive;", "enqueueReceiveInternal", "enqueueReceiveSelect", "R", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "receiveMode", "", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)Z", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "onCancelIdempotent", "wasClosed", "onReceiveDequeued", "onReceiveEnqueued", "poll", "()Ljava/lang/Object;", "pollInternal", "pollSelectInternal", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrClosed", "receiveOrClosed-ZYPwvRU", "receiveOrNull", "receiveOrNullResult", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "receiveSuspend", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerSelectReceiveMode", "(Lkotlinx/coroutines/selects/SelectInstance;ILkotlin/jvm/functions/Function2;)V", "removeReceiveOnCancel", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "takeFirstReceiveOrPeekClosed", "Lkotlinx/coroutines/channels/ReceiveOrClosed;", "tryStartBlockUnintercepted", "value", "(Lkotlin/jvm/functions/Function2;Lkotlinx/coroutines/selects/SelectInstance;ILjava/lang/Object;)V", "Itr", "ReceiveElement", "ReceiveElementWithUndeliveredHandler", "ReceiveHasNext", "ReceiveSelect", "RemoveReceiveOnCancel", "TryPollDesc", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class AbstractChannel extends AbstractSendChannel implements Channel {
   public AbstractChannel(Function1 var1) {
      super(var1);
   }

   private final boolean enqueueReceive(Receive var1) {
      boolean var2 = this.enqueueReceiveInternal(var1);
      if (var2) {
         this.onReceiveEnqueued();
      }

      return var2;
   }

   private final boolean enqueueReceiveSelect(SelectInstance var1, Function2 var2, int var3) {
      ReceiveSelect var5 = new ReceiveSelect(this, var1, var2, var3);
      boolean var4 = this.enqueueReceive((Receive)var5);
      if (var4) {
         var1.disposeOnSelect((DisposableHandle)var5);
      }

      return var4;
   }

   private final Object receiveOrNullResult(Object var1) {
      if (var1 instanceof Closed) {
         Closed var2 = (Closed)var1;
         if (var2.closeCause == null) {
            return null;
         } else {
            throw StackTraceRecoveryKt.recoverStackTrace(var2.closeCause);
         }
      } else {
         return var1;
      }
   }

   private final void registerSelectReceiveMode(SelectInstance var1, int var2, Function2 var3) {
      while(!var1.isSelected()) {
         if (this.isEmptyImpl()) {
            if (this.enqueueReceiveSelect(var1, var3, var2)) {
               return;
            }
         } else {
            Object var4 = this.pollSelectInternal(var1);
            if (var4 == SelectKt.getALREADY_SELECTED()) {
               return;
            }

            if (var4 != AbstractChannelKt.POLL_FAILED && var4 != AtomicKt.RETRY_ATOMIC) {
               this.tryStartBlockUnintercepted(var3, var1, var2, var4);
            }
         }
      }

   }

   private final void removeReceiveOnCancel(CancellableContinuation var1, Receive var2) {
      var1.invokeOnCancellation((Function1)((CancelHandlerBase)(new RemoveReceiveOnCancel(this, var2))));
   }

   private final void tryStartBlockUnintercepted(Function2 var1, SelectInstance var2, int var3, Object var4) {
      boolean var5 = var4 instanceof Closed;
      ValueOrClosed.Companion var6;
      if (var5) {
         if (var3 == 0) {
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed)var4).getReceiveException());
         }

         if (var3 != 1) {
            if (var3 == 2) {
               if (!var2.trySelect()) {
                  return;
               }

               var6 = ValueOrClosed.Companion;
               UndispatchedKt.startCoroutineUnintercepted(var1, ValueOrClosed.box_impl(ValueOrClosed.constructor_impl(new ValueOrClosed.Closed(((Closed)var4).closeCause))), var2.getCompletion());
            }
         } else {
            Closed var7 = (Closed)var4;
            if (var7.closeCause != null) {
               throw StackTraceRecoveryKt.recoverStackTrace(var7.getReceiveException());
            }

            if (!var2.trySelect()) {
               return;
            }

            UndispatchedKt.startCoroutineUnintercepted(var1, (Object)null, var2.getCompletion());
         }
      } else if (var3 == 2) {
         var6 = ValueOrClosed.Companion;
         if (var5) {
            var4 = ValueOrClosed.constructor_impl(new ValueOrClosed.Closed(((Closed)var4).closeCause));
         } else {
            var4 = ValueOrClosed.constructor_impl(var4);
         }

         UndispatchedKt.startCoroutineUnintercepted(var1, ValueOrClosed.box_impl(var4), var2.getCompletion());
      } else {
         UndispatchedKt.startCoroutineUnintercepted(var1, var4, var2.getCompletion());
      }

   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public void cancel() {
      Channel.DefaultImpls.cancel(this);
   }

   public final void cancel(CancellationException var1) {
      if (var1 == null) {
         var1 = new CancellationException(DebugStringsKt.getClassSimpleName(this) + " was cancelled");
      }

      this.cancelInternal$kotlinx_coroutines_core((Throwable)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public final boolean cancel(Throwable var1) {
      return this.cancelInternal$kotlinx_coroutines_core(var1);
   }

   public final boolean cancelInternal$kotlinx_coroutines_core(Throwable var1) {
      boolean var2 = this.close(var1);
      this.onCancelIdempotent(var2);
      return var2;
   }

   protected final TryPollDesc describeTryPoll() {
      return new TryPollDesc(this.getQueue());
   }

   protected boolean enqueueReceiveInternal(Receive var1) {
      boolean var3 = this.isBufferAlwaysEmpty();
      boolean var4 = false;
      LockFreeLinkedListNode var5;
      LockFreeLinkedListNode var6;
      if (var3) {
         var5 = (LockFreeLinkedListNode)this.getQueue();

         do {
            var6 = var5.getPrevNode();
            if (!(var6 instanceof Send ^ true)) {
               var3 = var4;
               return var3;
            }
         } while(!var6.addNext((LockFreeLinkedListNode)var1, var5));
      } else {
         var5 = (LockFreeLinkedListNode)this.getQueue();
         LockFreeLinkedListNode var8 = (LockFreeLinkedListNode)var1;
         LockFreeLinkedListNode.CondAddOp var7 = (LockFreeLinkedListNode.CondAddOp)(new LockFreeLinkedListNode.CondAddOp(var8, var8, this) {
            final LockFreeLinkedListNode $node;
            final AbstractChannel this$0;

            public {
               this.$node = var1;
               this.this$0 = var3;
            }

            public Object prepare(LockFreeLinkedListNode var1) {
               Object var2;
               if (this.this$0.isBufferEmpty()) {
                  var2 = null;
               } else {
                  var2 = LockFreeLinkedListKt.getCONDITION_FALSE();
               }

               return var2;
            }
         });

         while(true) {
            var6 = var5.getPrevNode();
            if (!(var6 instanceof Send ^ true)) {
               var3 = var4;
               return var3;
            }

            int var2 = var6.tryCondAddNext(var8, var5, var7);
            if (var2 == 1) {
               break;
            }

            var3 = var4;
            if (var2 == 2) {
               return var3;
            }
         }
      }

      var3 = true;
      return var3;
   }

   protected final boolean getHasReceiveOrClosed() {
      return this.getQueue().getNextNode() instanceof ReceiveOrClosed;
   }

   public final SelectClause1 getOnReceive() {
      return (SelectClause1)(new SelectClause1(this) {
         final AbstractChannel this$0;

         {
            this.this$0 = var1;
         }

         public void registerSelectClause1(SelectInstance var1, Function2 var2) {
            AbstractChannel var3 = this.this$0;
            if (var2 != null) {
               var3.registerSelectReceiveMode(var1, 0, var2);
            } else {
               throw new NullPointerException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
            }
         }
      });
   }

   public final SelectClause1 getOnReceiveOrClosed() {
      return (SelectClause1)(new SelectClause1(this) {
         final AbstractChannel this$0;

         {
            this.this$0 = var1;
         }

         public void registerSelectClause1(SelectInstance var1, Function2 var2) {
            AbstractChannel var3 = this.this$0;
            if (var2 != null) {
               var3.registerSelectReceiveMode(var1, 2, var2);
            } else {
               throw new NullPointerException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
            }
         }
      });
   }

   public final SelectClause1 getOnReceiveOrNull() {
      return (SelectClause1)(new SelectClause1(this) {
         final AbstractChannel this$0;

         {
            this.this$0 = var1;
         }

         public void registerSelectClause1(SelectInstance var1, Function2 var2) {
            AbstractChannel var3 = this.this$0;
            if (var2 != null) {
               var3.registerSelectReceiveMode(var1, 1, var2);
            } else {
               throw new NullPointerException("null cannot be cast to non-null type suspend (kotlin.Any?) -> R");
            }
         }
      });
   }

   protected abstract boolean isBufferAlwaysEmpty();

   protected abstract boolean isBufferEmpty();

   public boolean isClosedForReceive() {
      boolean var1;
      if (this.getClosedForReceive() != null && this.isBufferEmpty()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isEmpty() {
      return this.isEmptyImpl();
   }

   protected final boolean isEmptyImpl() {
      boolean var1;
      if (!(this.getQueue().getNextNode() instanceof Send) && this.isBufferEmpty()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final ChannelIterator iterator() {
      return (ChannelIterator)(new Itr(this));
   }

   protected void onCancelIdempotent(boolean var1) {
      Closed var4 = this.getClosedForSend();
      if (var4 == null) {
         throw (Throwable)(new IllegalStateException("Cannot happen".toString()));
      } else {
         Object var3 = InlineList.constructor_impl$default((Object)null, 1, (DefaultConstructorMarker)null);

         while(true) {
            LockFreeLinkedListNode var5 = var4.getPrevNode();
            if (var5 instanceof LockFreeLinkedListHead) {
               if (var3 != null) {
                  if (!(var3 instanceof ArrayList)) {
                     ((Send)var3).resumeSendClosed(var4);
                  } else {
                     if (var3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<E> /* = java.util.ArrayList<E> */");
                     }

                     ArrayList var6 = (ArrayList)var3;

                     for(int var2 = var6.size() - 1; var2 >= 0; --var2) {
                        ((Send)var6.get(var2)).resumeSendClosed(var4);
                     }
                  }
               }

               return;
            }

            if (DebugKt.getASSERTIONS_ENABLED() && !(var5 instanceof Send)) {
               throw (Throwable)(new AssertionError());
            }

            if (!var5.remove()) {
               var5.helpRemove();
            } else {
               if (var5 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
               }

               var3 = InlineList.plus_UZ7vuAc(var3, (Send)var5);
            }
         }
      }
   }

   protected void onReceiveDequeued() {
   }

   protected void onReceiveEnqueued() {
   }

   public final Object poll() {
      Object var1 = this.pollInternal();
      if (var1 == AbstractChannelKt.POLL_FAILED) {
         var1 = null;
      } else {
         var1 = this.receiveOrNullResult(var1);
      }

      return var1;
   }

   protected Object pollInternal() {
      while(true) {
         Send var2 = this.takeFirstSendOrPeekClosed();
         if (var2 == null) {
            return AbstractChannelKt.POLL_FAILED;
         }

         Symbol var3 = var2.tryResumeSend((LockFreeLinkedListNode.PrepareOp)null);
         if (var3 != null) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               boolean var1;
               if (var3 == CancellableContinuationImplKt.RESUME_TOKEN) {
                  var1 = true;
               } else {
                  var1 = false;
               }

               if (!var1) {
                  throw (Throwable)(new AssertionError());
               }
            }

            var2.completeResumeSend();
            return var2.getPollResult();
         }

         var2.undeliveredElement();
      }
   }

   protected Object pollSelectInternal(SelectInstance var1) {
      TryPollDesc var2 = this.describeTryPoll();
      Object var3 = var1.performAtomicTrySelect((AtomicDesc)var2);
      if (var3 != null) {
         return var3;
      } else {
         ((Send)var2.getResult()).completeResumeSend();
         return ((Send)var2.getResult()).getPollResult();
      }
   }

   public final Object receive(Continuation var1) {
      Object var2 = this.pollInternal();
      return var2 != AbstractChannelKt.POLL_FAILED && !(var2 instanceof Closed) ? var2 : this.receiveSuspend(0, var1);
   }

   public final Object receiveOrClosed_ZYPwvRU(Continuation var1) {
      Object var5;
      label32: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var5 = var3;
               break label32;
            }
         }

         var5 = new ContinuationImpl(this, var1) {
            Object L$0;
            Object L$1;
            int label;
            Object result;
            final AbstractChannel this$0;

            {
               this.this$0 = var1;
            }

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return this.this$0.receiveOrClosed_ZYPwvRU(this);
            }
         };
      }

      Object var7 = ((<undefinedtype>)var5).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var5).label;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var4 = ((<undefinedtype>)var5).L$1;
         AbstractChannel var6 = (AbstractChannel)((<undefinedtype>)var5).L$0;
         ResultKt.throwOnFailure(var7);
         var5 = var7;
      } else {
         ResultKt.throwOnFailure(var7);
         var7 = this.pollInternal();
         if (var7 != AbstractChannelKt.POLL_FAILED) {
            ValueOrClosed.Companion var8;
            if (var7 instanceof Closed) {
               var8 = ValueOrClosed.Companion;
               var5 = ValueOrClosed.constructor_impl(new ValueOrClosed.Closed(((Closed)var7).closeCause));
            } else {
               var8 = ValueOrClosed.Companion;
               var5 = ValueOrClosed.constructor_impl(var7);
            }

            return var5;
         }

         ((<undefinedtype>)var5).L$0 = this;
         ((<undefinedtype>)var5).L$1 = var7;
         ((<undefinedtype>)var5).label = 1;
         var7 = this.receiveSuspend(2, (Continuation)var5);
         var5 = var7;
         if (var7 == var4) {
            return var4;
         }
      }

      return ((ValueOrClosed)var5).unbox_impl();
   }

   public final Object receiveOrNull(Continuation var1) {
      Object var2 = this.pollInternal();
      return var2 != AbstractChannelKt.POLL_FAILED && !(var2 instanceof Closed) ? var2 : this.receiveSuspend(1, var1);
   }

   // $FF: synthetic method
   final Object receiveSuspend(int var1, Continuation var2) {
      CancellableContinuationImpl var4 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(var2));
      CancellableContinuation var5 = (CancellableContinuation)var4;
      ReceiveElement var3;
      if (this.onUndeliveredElement == null) {
         if (var5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Any?>");
         }

         var3 = new ReceiveElement(var5, var1);
      } else {
         if (var5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Any?>");
         }

         var3 = (ReceiveElement)(new ReceiveElementWithUndeliveredHandler(var5, var1, this.onUndeliveredElement));
      }

      while(true) {
         Receive var6 = (Receive)var3;
         if (access$enqueueReceive(this, var6)) {
            access$removeReceiveOnCancel(this, var5, var6);
            break;
         }

         Object var7 = this.pollInternal();
         if (var7 instanceof Closed) {
            var3.resumeReceiveClosed((Closed)var7);
            break;
         }

         if (var7 != AbstractChannelKt.POLL_FAILED) {
            var5.resume(var3.resumeValue(var7), var3.resumeOnCancellationFun(var7));
            break;
         }
      }

      Object var8 = var4.getResult();
      if (var8 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var8;
   }

   protected ReceiveOrClosed takeFirstReceiveOrPeekClosed() {
      ReceiveOrClosed var1 = super.takeFirstReceiveOrPeekClosed();
      if (var1 != null && !(var1 instanceof Closed)) {
         this.onReceiveDequeued();
      }

      return var1;
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\f\u001a\u00020\rH\u0096Bø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u0011\u0010\u0010\u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0011\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\tR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "E", "Lkotlinx/coroutines/channels/ChannelIterator;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "(Lkotlinx/coroutines/channels/AbstractChannel;)V", "result", "", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasNextResult", "hasNextSuspend", "next", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Itr implements ChannelIterator {
      public final AbstractChannel channel;
      private Object result;

      public Itr(AbstractChannel var1) {
         this.channel = var1;
         this.result = AbstractChannelKt.POLL_FAILED;
      }

      private final boolean hasNextResult(Object var1) {
         if (var1 instanceof Closed) {
            Closed var2 = (Closed)var1;
            if (var2.closeCause == null) {
               return false;
            } else {
               throw StackTraceRecoveryKt.recoverStackTrace(var2.getReceiveException());
            }
         } else {
            return true;
         }
      }

      public final Object getResult() {
         return this.result;
      }

      public Object hasNext(Continuation var1) {
         if (this.result != AbstractChannelKt.POLL_FAILED) {
            return Boxing.boxBoolean(this.hasNextResult(this.result));
         } else {
            Object var2 = this.channel.pollInternal();
            this.result = var2;
            return var2 != AbstractChannelKt.POLL_FAILED ? Boxing.boxBoolean(this.hasNextResult(this.result)) : this.hasNextSuspend(var1);
         }
      }

      // $FF: synthetic method
      final Object hasNextSuspend(Continuation var1) {
         CancellableContinuationImpl var3 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(var1));
         CancellableContinuation var4 = (CancellableContinuation)var3;
         ReceiveHasNext var5 = new ReceiveHasNext(this, var4);

         Object var7;
         while(true) {
            AbstractChannel var6 = this.channel;
            Receive var2 = (Receive)var5;
            if (var6.enqueueReceive(var2)) {
               this.channel.removeReceiveOnCancel(var4, var2);
               break;
            }

            var7 = this.channel.pollInternal();
            this.setResult(var7);
            if (var7 instanceof Closed) {
               Closed var14 = (Closed)var7;
               if (var14.closeCause == null) {
                  Continuation var15 = (Continuation)var4;
                  Boolean var9 = Boxing.boxBoolean(false);
                  Result.Companion var11 = Result.Companion;
                  var15.resumeWith(Result.constructor_impl(var9));
               } else {
                  Continuation var10 = (Continuation)var4;
                  Throwable var12 = var14.getReceiveException();
                  Result.Companion var16 = Result.Companion;
                  var10.resumeWith(Result.constructor_impl(ResultKt.createFailure(var12)));
               }
               break;
            }

            if (var7 != AbstractChannelKt.POLL_FAILED) {
               Boolean var13 = Boxing.boxBoolean(true);
               Function1 var17 = this.channel.onUndeliveredElement;
               Function1 var8;
               if (var17 != null) {
                  var8 = OnUndeliveredElementKt.bindCancellationFun(var17, var7, var4.getContext());
               } else {
                  var8 = null;
               }

               var4.resume(var13, var8);
               break;
            }
         }

         var7 = var3.getResult();
         if (var7 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var1);
         }

         return var7;
      }

      public Object next() {
         Object var1 = this.result;
         if (!(var1 instanceof Closed)) {
            if (var1 != AbstractChannelKt.POLL_FAILED) {
               this.result = AbstractChannelKt.POLL_FAILED;
               return var1;
            } else {
               throw (Throwable)(new IllegalStateException("'hasNext' should be called prior to 'next' invocation"));
            }
         } else {
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed)var1).getReceiveException());
         }
      }

      // $FF: synthetic method
      @Deprecated(
         level = DeprecationLevel.HIDDEN,
         message = "Since 1.3.0, binary compatibility with versions <= 1.2.x"
      )
      public Object next(Continuation var1) {
         return DefaultImpls.next(this, var1);
      }

      public final void setResult(Object var1) {
         this.result = var1;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016J\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016J!\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000b\u001a\u00028\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "E", "Lkotlinx/coroutines/channels/Receive;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "receiveMode", "", "(Lkotlinx/coroutines/CancellableContinuation;I)V", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "resumeValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static class ReceiveElement extends Receive {
      public final CancellableContinuation cont;
      public final int receiveMode;

      public ReceiveElement(CancellableContinuation var1, int var2) {
         this.cont = var1;
         this.receiveMode = var2;
      }

      public void completeResumeReceive(Object var1) {
         this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
      }

      public void resumeReceiveClosed(Closed var1) {
         if (this.receiveMode == 1 && var1.closeCause == null) {
            Continuation var6 = (Continuation)this.cont;
            Result.Companion var7 = Result.Companion;
            var6.resumeWith(Result.constructor_impl((Object)null));
         } else {
            Continuation var2;
            if (this.receiveMode == 2) {
               var2 = (Continuation)this.cont;
               ValueOrClosed.Companion var3 = ValueOrClosed.Companion;
               ValueOrClosed var4 = ValueOrClosed.box_impl(ValueOrClosed.constructor_impl(new ValueOrClosed.Closed(var1.closeCause)));
               Result.Companion var8 = Result.Companion;
               var2.resumeWith(Result.constructor_impl(var4));
            } else {
               var2 = (Continuation)this.cont;
               Throwable var9 = var1.getReceiveException();
               Result.Companion var5 = Result.Companion;
               var2.resumeWith(Result.constructor_impl(ResultKt.createFailure(var9)));
            }
         }

      }

      public final Object resumeValue(Object var1) {
         if (this.receiveMode == 2) {
            ValueOrClosed.Companion var2 = ValueOrClosed.Companion;
            var1 = ValueOrClosed.box_impl(ValueOrClosed.constructor_impl(var1));
         }

         return var1;
      }

      public String toString() {
         return "ReceiveElement@" + DebugStringsKt.getHexAddress(this) + "[receiveMode=" + this.receiveMode + ']';
      }

      public Symbol tryResumeReceive(Object var1, PrepareOp var2) {
         CancellableContinuation var5 = this.cont;
         Object var6 = this.resumeValue(var1);
         AbstractAtomicDesc var4;
         if (var2 != null) {
            var4 = var2.desc;
         } else {
            var4 = null;
         }

         var1 = var5.tryResume(var6, var4, this.resumeOnCancellationFun(var1));
         if (var1 != null) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               boolean var3;
               if (var1 == CancellableContinuationImplKt.RESUME_TOKEN) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (!var3) {
                  throw (Throwable)(new AssertionError());
               }
            }

            if (var2 != null) {
               var2.finishPrepare();
            }

            return CancellableContinuationImplKt.RESUME_TOKEN;
         } else {
            return null;
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B;\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00028\u0001`\u000b¢\u0006\u0002\u0010\fJ#\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010R&\u0010\b\u001a\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00028\u0001`\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElementWithUndeliveredHandler;", "E", "Lkotlinx/coroutines/channels/AbstractChannel$ReceiveElement;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "receiveMode", "", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlinx/coroutines/CancellableContinuation;ILkotlin/jvm/functions/Function1;)V", "resumeOnCancellationFun", "", "value", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class ReceiveElementWithUndeliveredHandler extends ReceiveElement {
      public final Function1 onUndeliveredElement;

      public ReceiveElementWithUndeliveredHandler(CancellableContinuation var1, int var2, Function1 var3) {
         super(var1, var2);
         this.onUndeliveredElement = var3;
      }

      public Function1 resumeOnCancellationFun(Object var1) {
         return OnUndeliveredElementKt.bindCancellationFun(this.onUndeliveredElement, var1, this.cont.getContext());
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ#\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\n2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J!\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00028\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aR\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveHasNext;", "E", "Lkotlinx/coroutines/channels/Receive;", "iterator", "Lkotlinx/coroutines/channels/AbstractChannel$Itr;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/channels/AbstractChannel$Itr;Lkotlinx/coroutines/CancellableContinuation;)V", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "resumeOnCancellationFun", "Lkotlin/Function1;", "", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static class ReceiveHasNext extends Receive {
      public final CancellableContinuation cont;
      public final Itr iterator;

      public ReceiveHasNext(Itr var1, CancellableContinuation var2) {
         this.iterator = var1;
         this.cont = var2;
      }

      public void completeResumeReceive(Object var1) {
         this.iterator.setResult(var1);
         this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
      }

      public Function1 resumeOnCancellationFun(Object var1) {
         Function1 var2 = this.iterator.channel.onUndeliveredElement;
         Function1 var3;
         if (var2 != null) {
            var3 = OnUndeliveredElementKt.bindCancellationFun(var2, var1, this.cont.getContext());
         } else {
            var3 = null;
         }

         return var3;
      }

      public void resumeReceiveClosed(Closed var1) {
         Object var2;
         if (var1.closeCause == null) {
            var2 = CancellableContinuation.DefaultImpls.tryResume$default(this.cont, false, (Object)null, 2, (Object)null);
         } else {
            var2 = this.cont.tryResumeWithException(var1.getReceiveException());
         }

         if (var2 != null) {
            this.iterator.setResult(var1);
            this.cont.completeResume(var2);
         }

      }

      public String toString() {
         return "ReceiveHasNext@" + DebugStringsKt.getHexAddress(this);
      }

      public Symbol tryResumeReceive(Object var1, PrepareOp var2) {
         CancellableContinuation var5 = this.cont;
         boolean var3 = true;
         AbstractAtomicDesc var4;
         if (var2 != null) {
            var4 = var2.desc;
         } else {
            var4 = null;
         }

         var1 = var5.tryResume(true, var4, this.resumeOnCancellationFun(var1));
         if (var1 != null) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               if (var1 != CancellableContinuationImplKt.RESUME_TOKEN) {
                  var3 = false;
               }

               if (!var3) {
                  throw (Throwable)(new AssertionError());
               }
            }

            if (var2 != null) {
               var2.finishPrepare();
            }

            return CancellableContinuationImplKt.RESUME_TOKEN;
         } else {
            return null;
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\u00020\u0004BR\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b\u0012$\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\u0012\u0006\u0010\r\u001a\u00020\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0016J#\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00172\u0006\u0010\u0013\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u00122\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J!\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0013\u001a\u00028\u00022\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016¢\u0006\u0002\u0010#R3\u0010\t\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n8\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$ReceiveSelect;", "R", "E", "Lkotlinx/coroutines/channels/Receive;", "Lkotlinx/coroutines/DisposableHandle;", "channel", "Lkotlinx/coroutines/channels/AbstractChannel;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "block", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "receiveMode", "", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;I)V", "Lkotlin/jvm/functions/Function2;", "completeResumeReceive", "", "value", "(Ljava/lang/Object;)V", "dispose", "resumeOnCancellationFun", "Lkotlin/Function1;", "", "(Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/channels/Closed;", "toString", "", "tryResumeReceive", "Lkotlinx/coroutines/internal/Symbol;", "otherOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "(Ljava/lang/Object;Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;)Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class ReceiveSelect extends Receive implements DisposableHandle {
      public final Function2 block;
      public final AbstractChannel channel;
      public final int receiveMode;
      public final SelectInstance select;

      public ReceiveSelect(AbstractChannel var1, SelectInstance var2, Function2 var3, int var4) {
         this.channel = var1;
         this.select = var2;
         this.block = var3;
         this.receiveMode = var4;
      }

      public void completeResumeReceive(Object var1) {
         Function2 var3 = this.block;
         Object var4;
         if (this.receiveMode == 2) {
            ValueOrClosed.Companion var2 = ValueOrClosed.Companion;
            var4 = ValueOrClosed.box_impl(ValueOrClosed.constructor_impl(var1));
         } else {
            var4 = var1;
         }

         CancellableKt.startCoroutineCancellable(var3, var4, this.select.getCompletion(), this.resumeOnCancellationFun(var1));
      }

      public void dispose() {
         if (this.remove()) {
            this.channel.onReceiveDequeued();
         }

      }

      public Function1 resumeOnCancellationFun(Object var1) {
         Function1 var2 = this.channel.onUndeliveredElement;
         Function1 var3;
         if (var2 != null) {
            var3 = OnUndeliveredElementKt.bindCancellationFun(var2, var1, this.select.getCompletion().getContext());
         } else {
            var3 = null;
         }

         return var3;
      }

      public void resumeReceiveClosed(Closed var1) {
         if (this.select.trySelect()) {
            int var2 = this.receiveMode;
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     Function2 var4 = this.block;
                     ValueOrClosed.Companion var3 = ValueOrClosed.Companion;
                     CancellableKt.startCoroutineCancellable$default(var4, ValueOrClosed.box_impl(ValueOrClosed.constructor_impl(new ValueOrClosed.Closed(var1.closeCause))), this.select.getCompletion(), (Function1)null, 4, (Object)null);
                  }
               } else if (var1.closeCause == null) {
                  CancellableKt.startCoroutineCancellable$default(this.block, (Object)null, this.select.getCompletion(), (Function1)null, 4, (Object)null);
               } else {
                  this.select.resumeSelectWithException(var1.getReceiveException());
               }
            } else {
               this.select.resumeSelectWithException(var1.getReceiveException());
            }

         }
      }

      public String toString() {
         return "ReceiveSelect@" + DebugStringsKt.getHexAddress(this) + '[' + this.select + ",receiveMode=" + this.receiveMode + ']';
      }

      public Symbol tryResumeReceive(Object var1, PrepareOp var2) {
         return (Symbol)this.select.trySelectOther(var2);
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$RemoveReceiveOnCancel;", "Lkotlinx/coroutines/BeforeResumeCancelHandler;", "receive", "Lkotlinx/coroutines/channels/Receive;", "(Lkotlinx/coroutines/channels/AbstractChannel;Lkotlinx/coroutines/channels/Receive;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class RemoveReceiveOnCancel extends BeforeResumeCancelHandler {
      private final Receive receive;
      final AbstractChannel this$0;

      public RemoveReceiveOnCancel(AbstractChannel var1, Receive var2) {
         this.this$0 = var1;
         this.receive = var2;
      }

      public void invoke(Throwable var1) {
         if (this.receive.remove()) {
            this.this$0.onReceiveDequeued();
         }

      }

      public String toString() {
         return "RemoveReceiveOnCancel[" + this.receive + ']';
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0016\u0010\f\u001a\u0004\u0018\u00010\t2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0012"},
      d2 = {"Lkotlinx/coroutines/channels/AbstractChannel$TryPollDesc;", "E", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/channels/Send;", "Lkotlinx/coroutines/internal/RemoveFirstDesc;", "queue", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "(Lkotlinx/coroutines/internal/LockFreeLinkedListHead;)V", "failure", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "onPrepare", "prepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "Lkotlinx/coroutines/internal/PrepareOp;", "onRemoved", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   protected static final class TryPollDesc extends LockFreeLinkedListNode.RemoveFirstDesc {
      public TryPollDesc(LockFreeLinkedListHead var1) {
         super((LockFreeLinkedListNode)var1);
      }

      protected Object failure(LockFreeLinkedListNode var1) {
         if (!(var1 instanceof Closed)) {
            if (!(var1 instanceof Send)) {
               var1 = AbstractChannelKt.POLL_FAILED;
            } else {
               var1 = null;
            }
         }

         return var1;
      }

      public Object onPrepare(LockFreeLinkedListNode.PrepareOp var1) {
         LockFreeLinkedListNode var3 = var1.affected;
         if (var3 != null) {
            Symbol var4 = ((Send)var3).tryResumeSend(var1);
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
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
         }
      }

      public void onRemoved(LockFreeLinkedListNode var1) {
         if (var1 != null) {
            ((Send)var1).undeliveredElement();
         } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
         }
      }
   }
}
