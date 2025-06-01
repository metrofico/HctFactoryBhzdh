package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\b\u0007\u0018\u0000 A*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000F:\u0004BACDB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00028\u0000¢\u0006\u0004\b\u0003\u0010\u0004B\u0007¢\u0006\u0004\b\u0003\u0010\u0005J?\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u00062\u0014\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0018\u00010\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u000f\u001a\u00020\u00132\u000e\u0010\r\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012H\u0016¢\u0006\u0004\b\u000f\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b\u0015\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00132\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J)\u0010\u001b\u001a\u00020\u00132\u0018\u0010\u001a\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u00130\u0018j\u0002`\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u00132\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010 \u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b \u0010!J\u0019\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b#\u0010$J\u0015\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0016¢\u0006\u0004\b&\u0010'JX\u00100\u001a\u00020\u0013\"\u0004\b\u0001\u0010(2\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00010)2\u0006\u0010\u001f\u001a\u00028\u00002(\u0010/\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000,\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010-\u0012\u0006\u0012\u0004\u0018\u00010.0+H\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101J?\u00102\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0018\u00010\u00062\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0002¢\u0006\u0004\b2\u0010\u000bJ\u001b\u00103\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b3\u00104R\u0016\u00105\u001a\u00020\u000e8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u000e8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00106R(\u0010;\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000,088V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0019\u0010\u0002\u001a\u00028\u00008F@\u0006¢\u0006\f\u0012\u0004\b>\u0010\u0005\u001a\u0004\b<\u0010=R\u0015\u0010@\u001a\u0004\u0018\u00018\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b?\u0010=\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006E"},
   d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "E", "value", "<init>", "(Ljava/lang/Object;)V", "()V", "", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "list", "subscriber", "addSubscriber", "([Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;)[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "", "cause", "", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "", "(Ljava/util/concurrent/CancellationException;)V", "close", "closeSubscriber", "(Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;)V", "Lkotlin/Function1;", "Lkotlinx/coroutines/channels/Handler;", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "invokeOnCloseHandler", "(Ljava/lang/Throwable;)V", "element", "offer", "(Ljava/lang/Object;)Z", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "offerInternal", "(Ljava/lang/Object;)Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "openSubscription", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "block", "registerSelectSend", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "removeSubscriber", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isClosedForSend", "()Z", "isFull", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "getValue", "()Ljava/lang/Object;", "getValue$annotations", "getValueOrNull", "valueOrNull", "Companion", "Closed", "State", "Subscriber", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/BroadcastChannel;"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ConflatedBroadcastChannel implements BroadcastChannel {
   private static final Closed CLOSED = new Closed((Throwable)null);
   private static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final State INITIAL_STATE;
   private static final Symbol UNDEFINED;
   private static final AtomicReferenceFieldUpdater _state$FU;
   private static final AtomicIntegerFieldUpdater _updating$FU;
   private static final AtomicReferenceFieldUpdater onCloseHandler$FU;
   private volatile Object _state;
   private volatile int _updating;
   private volatile Object onCloseHandler;

   static {
      Symbol var0 = new Symbol("UNDEFINED");
      UNDEFINED = var0;
      INITIAL_STATE = new State(var0, (Subscriber[])null);
      _state$FU = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "_state");
      _updating$FU = AtomicIntegerFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, "_updating");
      onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "onCloseHandler");
   }

   public ConflatedBroadcastChannel() {
      this._state = INITIAL_STATE;
      this._updating = 0;
      this.onCloseHandler = null;
   }

   public ConflatedBroadcastChannel(Object var1) {
      this();
      _state$FU.lazySet(this, new State(var1, (Subscriber[])null));
   }

   private final Subscriber[] addSubscriber(Subscriber[] var1, Subscriber var2) {
      if (var1 != null) {
         return (Subscriber[])ArraysKt.plus(var1, var2);
      } else {
         var1 = new Subscriber[1];

         for(int var3 = 0; var3 < 1; ++var3) {
            var1[var3] = var2;
         }

         return var1;
      }
   }

   private final void closeSubscriber(Subscriber var1) {
      while(true) {
         Object var2 = this._state;
         if (var2 instanceof Closed) {
            return;
         }

         if (var2 instanceof State) {
            State var4 = (State)var2;
            Object var3 = var4.value;
            if (var2 != null) {
               Subscriber[] var6 = var4.subscribers;
               Intrinsics.checkNotNull(var6);
               State var5 = new State(var3, this.removeSubscriber(var6, var1));
               if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var2, var5)) {
                  continue;
               }

               return;
            }

            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
         }

         throw (Throwable)(new IllegalStateException(("Invalid state " + var2).toString()));
      }
   }

   // $FF: synthetic method
   public static void getValue$annotations() {
   }

   private final void invokeOnCloseHandler(Throwable var1) {
      Object var2 = this.onCloseHandler;
      if (var2 != null && var2 != AbstractChannelKt.HANDLER_INVOKED && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(onCloseHandler$FU, this, var2, AbstractChannelKt.HANDLER_INVOKED)) {
         ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var2, 1)).invoke(var1);
      }

   }

   private final Closed offerInternal(Object var1) {
      if (!_updating$FU.compareAndSet(this, 0, 1)) {
         return null;
      } else {
         Closed var66;
         label624: {
            Throwable var10000;
            label623:
            while(true) {
               Object var4;
               boolean var10001;
               try {
                  var4 = this._state;
                  if (var4 instanceof Closed) {
                     var66 = (Closed)var4;
                     break label624;
                  }
               } catch (Throwable var61) {
                  var10000 = var61;
                  var10001 = false;
                  break;
               }

               label614: {
                  State var5;
                  try {
                     if (!(var4 instanceof State)) {
                        break label614;
                     }

                     var5 = new State;
                  } catch (Throwable var60) {
                     var10000 = var60;
                     var10001 = false;
                     break;
                  }

                  if (var4 != null) {
                     Subscriber[] var67;
                     try {
                        var5.<init>(var1, ((State)var4).subscribers);
                        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var4, var5)) {
                           continue;
                        }

                        var67 = ((State)var4).subscribers;
                     } catch (Throwable var57) {
                        var10000 = var57;
                        var10001 = false;
                        break;
                     }

                     if (var67 != null) {
                        int var3;
                        try {
                           var3 = var67.length;
                        } catch (Throwable var56) {
                           var10000 = var56;
                           var10001 = false;
                           break;
                        }

                        for(int var2 = 0; var2 < var3; ++var2) {
                           try {
                              var67[var2].offerInternal(var1);
                           } catch (Throwable var55) {
                              var10000 = var55;
                              var10001 = false;
                              break label623;
                           }
                        }
                     }

                     this._updating = 0;
                     return null;
                  } else {
                     try {
                        NullPointerException var62 = new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
                        throw var62;
                     } catch (Throwable var58) {
                        var10000 = var58;
                        var10001 = false;
                        break;
                     }
                  }
               }

               try {
                  StringBuilder var64 = new StringBuilder();
                  String var65 = var64.append("Invalid state ").append(var4).toString();
                  IllegalStateException var68 = new IllegalStateException(var65.toString());
                  throw (Throwable)var68;
               } catch (Throwable var59) {
                  var10000 = var59;
                  var10001 = false;
                  break;
               }
            }

            Throwable var63 = var10000;
            this._updating = 0;
            throw var63;
         }

         this._updating = 0;
         return var66;
      }
   }

   private final void registerSelectSend(SelectInstance var1, Object var2, Function2 var3) {
      if (var1.trySelect()) {
         Closed var4 = this.offerInternal(var2);
         if (var4 != null) {
            var1.resumeSelectWithException(var4.getSendException());
         } else {
            UndispatchedKt.startCoroutineUnintercepted(var3, this, var1.getCompletion());
         }
      }
   }

   private final Subscriber[] removeSubscriber(Subscriber[] var1, Subscriber var2) {
      int var5 = var1.length;
      int var4 = ArraysKt.indexOf(var1, var2);
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var3;
         if (var4 >= 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (!var3) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (var5 == 1) {
         return null;
      } else {
         Subscriber[] var6 = new Subscriber[var5 - 1];
         ArraysKt.copyInto$default(var1, var6, 0, 0, var4, 6, (Object)null);
         ArraysKt.copyInto$default(var1, var6, var4, var4 + 1, 0, 8, (Object)null);
         return var6;
      }
   }

   public void cancel(CancellationException var1) {
      this.close((Throwable)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public boolean cancel(Throwable var1) {
      return this.close(var1);
   }

   public boolean close(Throwable var1) {
      while(true) {
         Object var6 = this._state;
         boolean var4 = var6 instanceof Closed;
         int var2 = 0;
         if (var4) {
            return false;
         }

         if (var6 instanceof State) {
            Closed var5;
            if (var1 == null) {
               var5 = CLOSED;
            } else {
               var5 = new Closed(var1);
            }

            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var6, var5)) {
               continue;
            }

            if (var6 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
            }

            Subscriber[] var7 = ((State)var6).subscribers;
            if (var7 != null) {
               for(int var3 = var7.length; var2 < var3; ++var2) {
                  var7[var2].close(var1);
               }
            }

            this.invokeOnCloseHandler(var1);
            return true;
         }

         throw (Throwable)(new IllegalStateException(("Invalid state " + var6).toString()));
      }
   }

   public SelectClause2 getOnSend() {
      return (SelectClause2)(new SelectClause2(this) {
         final ConflatedBroadcastChannel this$0;

         {
            this.this$0 = var1;
         }

         public void registerSelectClause2(SelectInstance var1, Object var2, Function2 var3) {
            this.this$0.registerSelectSend(var1, var2, var3);
         }
      });
   }

   public final Object getValue() {
      Object var1 = this._state;
      if (!(var1 instanceof Closed)) {
         if (var1 instanceof State) {
            State var2 = (State)var1;
            if (var2.value != UNDEFINED) {
               return var2.value;
            } else {
               throw (Throwable)(new IllegalStateException("No value"));
            }
         } else {
            throw (Throwable)(new IllegalStateException(("Invalid state " + var1).toString()));
         }
      } else {
         throw ((Closed)var1).getValueException();
      }
   }

   public final Object getValueOrNull() {
      Object var3 = this._state;
      boolean var1 = var3 instanceof Closed;
      Object var2 = null;
      if (!var1) {
         if (!(var3 instanceof State)) {
            throw (Throwable)(new IllegalStateException(("Invalid state " + var3).toString()));
         }

         Symbol var4 = UNDEFINED;
         var3 = ((State)var3).value;
         if (var3 != var4) {
            var2 = var3;
         }
      }

      return var2;
   }

   public void invokeOnClose(Function1 var1) {
      AtomicReferenceFieldUpdater var3 = onCloseHandler$FU;
      if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(var3, this, (Object)null, var1)) {
         Object var4 = this.onCloseHandler;
         if (var4 == AbstractChannelKt.HANDLER_INVOKED) {
            throw (Throwable)(new IllegalStateException("Another handler was already registered and successfully invoked"));
         } else {
            throw (Throwable)(new IllegalStateException("Another handler was already registered: " + var4));
         }
      } else {
         Object var2 = this._state;
         if (var2 instanceof Closed && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(var3, this, var1, AbstractChannelKt.HANDLER_INVOKED)) {
            var1.invoke(((Closed)var2).closeCause);
         }

      }
   }

   public boolean isClosedForSend() {
      return this._state instanceof Closed;
   }

   public boolean isFull() {
      return false;
   }

   public boolean offer(Object var1) {
      Closed var2 = this.offerInternal(var1);
      if (var2 == null) {
         return true;
      } else {
         throw var2.getSendException();
      }
   }

   public ReceiveChannel openSubscription() {
      Subscriber var2 = new Subscriber(this);

      Object var1;
      State var3;
      do {
         var1 = this._state;
         if (var1 instanceof Closed) {
            var2.close(((Closed)var1).closeCause);
            return (ReceiveChannel)var2;
         }

         if (!(var1 instanceof State)) {
            throw (Throwable)(new IllegalStateException(("Invalid state " + var1).toString()));
         }

         var3 = (State)var1;
         if (var3.value != UNDEFINED) {
            var2.offerInternal(var3.value);
         }

         Object var4 = var3.value;
         if (var1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
         }

         var3 = new State(var4, this.addSubscriber(var3.subscribers, var2));
      } while(!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, var3));

      return (ReceiveChannel)var2;
   }

   public Object send(Object var1, Continuation var2) {
      Closed var3 = this.offerInternal(var1);
      if (var3 == null) {
         return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
      } else {
         throw var3.getSendException();
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\n"},
      d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "", "closeCause", "", "(Ljava/lang/Throwable;)V", "sendException", "getSendException", "()Ljava/lang/Throwable;", "valueException", "getValueException", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Closed {
      public final Throwable closeCause;

      public Closed(Throwable var1) {
         this.closeCause = var1;
      }

      public final Throwable getSendException() {
         Throwable var1 = this.closeCause;
         if (var1 == null) {
            var1 = (Throwable)(new ClosedSendChannelException("Channel was closed"));
         }

         return var1;
      }

      public final Throwable getValueException() {
         Throwable var1 = this.closeCause;
         if (var1 == null) {
            var1 = (Throwable)(new IllegalStateException("Channel was closed"));
         }

         return var1;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"},
      d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Companion;", "", "()V", "CLOSED", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Closed;", "INITIAL_STATE", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$State;", "UNDEFINED", "Lkotlinx/coroutines/internal/Symbol;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0014\u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R \u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"},
      d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$State;", "E", "", "value", "subscribers", "", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "(Ljava/lang/Object;[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;)V", "[Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class State {
      public final Subscriber[] subscribers;
      public final Object value;

      public State(Object var1, Subscriber[] var2) {
         this.value = var1;
         this.subscribers = var2;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"},
      d2 = {"Lkotlinx/coroutines/channels/ConflatedBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/channels/ConflatedChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "broadcastChannel", "Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;", "(Lkotlinx/coroutines/channels/ConflatedBroadcastChannel;)V", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "onCancelIdempotent", "", "wasClosed", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Subscriber extends ConflatedChannel implements ReceiveChannel {
      private final ConflatedBroadcastChannel broadcastChannel;

      public Subscriber(ConflatedBroadcastChannel var1) {
         super((Function1)null);
         this.broadcastChannel = var1;
      }

      public Object offerInternal(Object var1) {
         return super.offerInternal(var1);
      }

      protected void onCancelIdempotent(boolean var1) {
         if (var1) {
            this.broadcastChannel.closeSubscriber(this);
         }

      }
   }
}
