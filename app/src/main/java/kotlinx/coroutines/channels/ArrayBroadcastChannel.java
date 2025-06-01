package kotlinx.coroutines.channels;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000K2\b\u0012\u0004\u0012\u00028\u00000L:\u0001IB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\t\u001a\u00020\r2\u000e\u0010\u0007\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fH\u0016¢\u0006\u0004\b\t\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\nJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\nJ\u000f\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ#\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0014¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016¢\u0006\u0004\b\"\u0010#J4\u0010'\u001a\u00020\r2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010$2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010$H\u0082\u0010¢\u0006\u0004\b'\u0010(R\u001e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0)8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010/\u001a\u00020,8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u001a\u00102\u001a\u000600j\u0002`18\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u00104\u001a\u0004\b5\u00106R$\u0010;\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00138B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b8\u0010\u0015\"\u0004\b9\u0010:R\u0016\u0010<\u001a\u00020\b8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\b8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b>\u0010=R$\u0010A\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u00028B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b?\u00106\"\u0004\b@\u0010\u0005R2\u0010D\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$0Bj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$`C8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ER$\u0010H\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00138B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bF\u0010\u0015\"\u0004\bG\u0010:¨\u0006J"},
   d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "E", "", "capacity", "<init>", "(I)V", "", "cause", "", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "", "(Ljava/util/concurrent/CancellationException;)V", "cancelInternal", "checkSubOffers", "()V", "close", "", "computeMinHead", "()J", "index", "elementAt", "(J)Ljava/lang/Object;", "element", "", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "openSubscription", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", "addSub", "removeSub", "updateHead", "(Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;)V", "", "buffer", "[Ljava/lang/Object;", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "bufferLock", "Ljava/util/concurrent/locks/ReentrantLock;", "I", "getCapacity", "()I", "value", "getHead", "setHead", "(J)V", "head", "isBufferAlwaysFull", "()Z", "isBufferFull", "getSize", "setSize", "size", "", "Lkotlinx/coroutines/internal/SubscribersList;", "subscribers", "Ljava/util/List;", "getTail", "setTail", "tail", "Subscriber", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/BroadcastChannel;"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ArrayBroadcastChannel extends AbstractSendChannel implements BroadcastChannel {
   private volatile long _head;
   private volatile int _size;
   private volatile long _tail;
   private final Object[] buffer;
   private final ReentrantLock bufferLock;
   private final int capacity;
   private final List subscribers;

   public ArrayBroadcastChannel(int var1) {
      super((Function1)null);
      this.capacity = var1;
      boolean var2 = true;
      if (var1 < 1) {
         var2 = false;
      }

      if (var2) {
         this.bufferLock = new ReentrantLock();
         this.buffer = new Object[var1];
         this._head = 0L;
         this._tail = 0L;
         this._size = 0;
         this.subscribers = ConcurrentKt.subscriberList();
      } else {
         throw (Throwable)(new IllegalArgumentException(("ArrayBroadcastChannel capacity must be at least 1, but " + var1 + " was specified").toString()));
      }
   }

   // $FF: synthetic method
   public static final void access$setTail$p(ArrayBroadcastChannel var0, long var1) {
      var0.setTail(var1);
   }

   private final boolean cancelInternal(Throwable var1) {
      boolean var2 = this.close(var1);
      Iterator var3 = this.subscribers.iterator();

      while(var3.hasNext()) {
         ((Subscriber)var3.next()).cancelInternal$kotlinx_coroutines_core(var1);
      }

      return var2;
   }

   private final void checkSubOffers() {
      Iterator var3 = this.subscribers.iterator();
      boolean var1 = false;

      boolean var2;
      for(var2 = false; var3.hasNext(); var2 = true) {
         if (((Subscriber)var3.next()).checkOffer()) {
            var1 = true;
         }
      }

      if (var1 || !var2) {
         updateHead$default(this, (Subscriber)null, (Subscriber)null, 3, (Object)null);
      }

   }

   private final long computeMinHead() {
      Iterator var3 = this.subscribers.iterator();

      long var1;
      for(var1 = Long.MAX_VALUE; var3.hasNext(); var1 = RangesKt.coerceAtMost(var1, ((Subscriber)var3.next()).getSubHead())) {
      }

      return var1;
   }

   private final Object elementAt(long var1) {
      return this.buffer[(int)(var1 % (long)this.capacity)];
   }

   private final long getHead() {
      return this._head;
   }

   private final int getSize() {
      return this._size;
   }

   private final long getTail() {
      return this._tail;
   }

   private final void setHead(long var1) {
      this._head = var1;
   }

   private final void setSize(int var1) {
      this._size = var1;
   }

   private final void setTail(long var1) {
      this._tail = var1;
   }

   private final void updateHead(Subscriber var1, Subscriber var2) {
      Throwable var10000;
      Lock var299;
      label2414:
      while(true) {
         Send var16 = (Send)null;
         var299 = (Lock)this.bufferLock;
         var299.lock();
         boolean var10001;
         if (var1 != null) {
            boolean var7;
            try {
               var1.setSubHead(this.getTail());
               var7 = this.subscribers.isEmpty();
               this.subscribers.add(var1);
            } catch (Throwable var287) {
               var10000 = var287;
               var10001 = false;
               break;
            }

            if (!var7) {
               var299.unlock();
               return;
            }
         }

         long var8;
         long var10;
         if (var2 != null) {
            try {
               this.subscribers.remove(var2);
               var10 = this.getHead();
               var8 = var2.getSubHead();
            } catch (Throwable var286) {
               var10000 = var286;
               var10001 = false;
               break;
            }

            if (var10 != var8) {
               var299.unlock();
               return;
            }
         }

         long var12;
         long var14;
         try {
            var10 = this.computeMinHead();
            var12 = this.getTail();
            var8 = this.getHead();
            var14 = RangesKt.coerceAtMost(var10, var12);
         } catch (Throwable var285) {
            var10000 = var285;
            var10001 = false;
            break;
         }

         if (var14 <= var8) {
            var299.unlock();
            return;
         }

         int var3;
         try {
            var3 = this.getSize();
         } catch (Throwable var284) {
            var10000 = var284;
            var10001 = false;
            break;
         }

         label2412:
         while(var8 < var14) {
            int var4;
            Object[] var289;
            try {
               var289 = this.buffer;
               var4 = this.capacity;
            } catch (Throwable var283) {
               var10000 = var283;
               var10001 = false;
               break label2414;
            }

            var289[(int)(var8 % (long)var4)] = null;
            boolean var6 = false;
            boolean var297;
            if (var3 >= var4) {
               var297 = true;
            } else {
               var297 = false;
            }

            var10 = var8 + 1L;

            try {
               this.setHead(var10);
            } catch (Throwable var282) {
               var10000 = var282;
               var10001 = false;
               break label2414;
            }

            int var5 = var3 - 1;

            try {
               this.setSize(var5);
            } catch (Throwable var281) {
               var10000 = var281;
               var10001 = false;
               break label2414;
            }

            var8 = var10;
            var3 = var5;
            if (var297) {
               Send var290;
               Symbol var292;
               do {
                  try {
                     var290 = this.takeFirstSendOrPeekClosed();
                  } catch (Throwable var279) {
                     var10000 = var279;
                     var10001 = false;
                     break label2414;
                  }

                  var8 = var10;
                  var3 = var5;
                  if (var290 == null) {
                     continue label2412;
                  }

                  label2406: {
                     try {
                        if (!(var290 instanceof Closed)) {
                           break label2406;
                        }
                     } catch (Throwable var288) {
                        var10000 = var288;
                        var10001 = false;
                        break label2414;
                     }

                     var8 = var10;
                     var3 = var5;
                     continue label2412;
                  }

                  try {
                     Intrinsics.checkNotNull(var290);
                     var292 = var290.tryResumeSend((LockFreeLinkedListNode.PrepareOp)null);
                  } catch (Throwable var280) {
                     var10000 = var280;
                     var10001 = false;
                     break label2414;
                  }
               } while(var292 == null);

               label2422: {
                  try {
                     if (!DebugKt.getASSERTIONS_ENABLED()) {
                        break label2422;
                     }
                  } catch (Throwable var278) {
                     var10000 = var278;
                     var10001 = false;
                     break label2414;
                  }

                  boolean var298 = var6;

                  label2362: {
                     try {
                        if (var292 != CancellableContinuationImplKt.RESUME_TOKEN) {
                           break label2362;
                        }
                     } catch (Throwable var277) {
                        var10000 = var277;
                        var10001 = false;
                        break label2414;
                     }

                     var298 = true;
                  }

                  if (!var298) {
                     try {
                        AssertionError var291 = new AssertionError();
                        throw (Throwable)var291;
                     } catch (Throwable var273) {
                        var10000 = var273;
                        var10001 = false;
                        break label2414;
                     }
                  }
               }

               Object[] var294;
               try {
                  var294 = this.buffer;
                  var3 = (int)(var12 % (long)this.capacity);
               } catch (Throwable var276) {
                  var10000 = var276;
                  var10001 = false;
                  break label2414;
               }

               if (var290 != null) {
                  try {
                     var294[var3] = var290.getPollResult();
                     this.setSize(var5 + 1);
                     this.setTail(var12 + 1L);
                     Unit var296 = Unit.INSTANCE;
                  } catch (Throwable var274) {
                     var10000 = var274;
                     var10001 = false;
                     break label2414;
                  }

                  var299.unlock();
                  Intrinsics.checkNotNull(var290);
                  var290.completeResumeSend();
                  this.checkSubOffers();
                  var1 = null;
                  var2 = null;
                  continue label2414;
               } else {
                  try {
                     NullPointerException var295 = new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.Send");
                     throw var295;
                  } catch (Throwable var275) {
                     var10000 = var275;
                     var10001 = false;
                     break label2414;
                  }
               }
            }
         }

         var299.unlock();
         return;
      }

      Throwable var293 = var10000;
      var299.unlock();
      throw var293;
   }

   // $FF: synthetic method
   static void updateHead$default(ArrayBroadcastChannel var0, Subscriber var1, Subscriber var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (Subscriber)null;
         var1 = null;
      }

      if ((var3 & 2) != 0) {
         var2 = (Subscriber)null;
         var2 = null;
      }

      var0.updateHead(var1, var2);
   }

   public void cancel(CancellationException var1) {
      this.cancelInternal((Throwable)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public boolean cancel(Throwable var1) {
      return this.cancelInternal(var1);
   }

   public boolean close(Throwable var1) {
      if (!super.close(var1)) {
         return false;
      } else {
         this.checkSubOffers();
         return true;
      }
   }

   protected String getBufferDebugString() {
      return "(buffer:capacity=" + this.buffer.length + ",size=" + this.getSize() + ')';
   }

   public final int getCapacity() {
      return this.capacity;
   }

   protected boolean isBufferAlwaysFull() {
      return false;
   }

   protected boolean isBufferFull() {
      boolean var1;
      if (this.getSize() >= this.capacity) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   protected Object offerInternal(Object var1) {
      Lock var5 = (Lock)this.bufferLock;
      var5.lock();

      Symbol var21;
      label148: {
         Throwable var10000;
         label152: {
            Closed var6;
            boolean var10001;
            try {
               var6 = this.getClosedForSend();
            } catch (Throwable var17) {
               var10000 = var17;
               var10001 = false;
               break label152;
            }

            if (var6 != null) {
               var5.unlock();
               return var6;
            }

            int var2;
            try {
               var2 = this.getSize();
               if (var2 >= this.capacity) {
                  var21 = AbstractChannelKt.OFFER_FAILED;
                  break label148;
               }
            } catch (Throwable var18) {
               var10000 = var18;
               var10001 = false;
               break label152;
            }

            try {
               long var3 = this.getTail();
               this.buffer[(int)(var3 % (long)this.capacity)] = var1;
               this.setSize(var2 + 1);
               this.setTail(var3 + 1L);
               Unit var20 = Unit.INSTANCE;
            } catch (Throwable var16) {
               var10000 = var16;
               var10001 = false;
               break label152;
            }

            var5.unlock();
            this.checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
         }

         Throwable var19 = var10000;
         var5.unlock();
         throw var19;
      }

      var5.unlock();
      return var21;
   }

   protected Object offerSelectInternal(Object var1, SelectInstance var2) {
      Lock var6 = (Lock)this.bufferLock;
      var6.lock();

      Throwable var10000;
      label251: {
         Closed var7;
         boolean var10001;
         try {
            var7 = this.getClosedForSend();
         } catch (Throwable var25) {
            var10000 = var25;
            var10001 = false;
            break label251;
         }

         if (var7 != null) {
            var6.unlock();
            return var7;
         }

         Symbol var30;
         label252: {
            int var3;
            try {
               var3 = this.getSize();
               if (var3 >= this.capacity) {
                  var30 = AbstractChannelKt.OFFER_FAILED;
                  break label252;
               }
            } catch (Throwable var27) {
               var10000 = var27;
               var10001 = false;
               break label251;
            }

            label237: {
               try {
                  if (var2.trySelect()) {
                     break label237;
                  }

                  var1 = SelectKt.getALREADY_SELECTED();
               } catch (Throwable var26) {
                  var10000 = var26;
                  var10001 = false;
                  break label251;
               }

               var6.unlock();
               return var1;
            }

            try {
               long var4 = this.getTail();
               this.buffer[(int)(var4 % (long)this.capacity)] = var1;
               this.setSize(var3 + 1);
               this.setTail(var4 + 1L);
               Unit var29 = Unit.INSTANCE;
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label251;
            }

            var6.unlock();
            this.checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
         }

         var6.unlock();
         return var30;
      }

      Throwable var28 = var10000;
      var6.unlock();
      throw var28;
   }

   public ReceiveChannel openSubscription() {
      Subscriber var1 = new Subscriber(this);
      updateHead$default(this, var1, (Subscriber)null, 2, (Object)null);
      return (ReceiveChannel)var1;
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010'2\b\u0012\u0004\u0012\u00028\u00010(B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\bJ\u0011\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0014¢\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0014¢\u0006\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00068T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\bR\u0016\u0010\u0018\u001a\u00020\u00068T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\bR\u0016\u0010\u0019\u001a\u00020\u00068T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\bR\u0016\u0010\u001a\u001a\u00020\u00068T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\bR$\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010$\u001a\u00060\"j\u0002`#8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006&"},
      d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "broadcastChannel", "<init>", "(Lkotlinx/coroutines/channels/ArrayBroadcastChannel;)V", "", "checkOffer", "()Z", "", "cause", "close", "(Ljava/lang/Throwable;)Z", "needsToCheckOfferWithoutLock", "", "peekUnderLock", "()Ljava/lang/Object;", "pollInternal", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "isBufferAlwaysEmpty", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "", "value", "getSubHead", "()J", "setSubHead", "(J)V", "subHead", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "subLock", "Ljava/util/concurrent/locks/ReentrantLock;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Subscriber extends AbstractChannel implements ReceiveChannel {
      private volatile long _subHead;
      private final ArrayBroadcastChannel broadcastChannel;
      private final ReentrantLock subLock;

      public Subscriber(ArrayBroadcastChannel var1) {
         super((Function1)null);
         this.broadcastChannel = var1;
         this.subLock = new ReentrantLock();
         this._subHead = 0L;
      }

      private final boolean needsToCheckOfferWithoutLock() {
         if (this.getClosedForReceive() != null) {
            return false;
         } else {
            return !this.isBufferEmpty() || this.broadcastChannel.getClosedForReceive() != null;
         }
      }

      private final Object peekUnderLock() {
         long var1 = this.getSubHead();
         Object var3 = this.broadcastChannel.getClosedForReceive();
         if (var1 >= this.broadcastChannel.getTail()) {
            if (var3 == null) {
               var3 = this.getClosedForReceive();
            }

            if (var3 == null) {
               var3 = AbstractChannelKt.POLL_FAILED;
            }

            return var3;
         } else {
            Object var4 = this.broadcastChannel.elementAt(var1);
            Closed var5 = this.getClosedForReceive();
            return var5 != null ? var5 : var4;
         }
      }

      public final boolean checkOffer() {
         Object var5 = null;
         Object var4 = null;
         Closed var3 = (Closed)null;
         boolean var2 = false;

         while(true) {
            var3 = (Closed)var5;
            if (!this.needsToCheckOfferWithoutLock()) {
               break;
            }

            if (!this.subLock.tryLock()) {
               var3 = (Closed)var5;
               break;
            }

            Throwable var10000;
            label947: {
               Object var6;
               Symbol var80;
               boolean var10001;
               try {
                  var6 = this.peekUnderLock();
                  var80 = AbstractChannelKt.POLL_FAILED;
               } catch (Throwable var77) {
                  var10000 = var77;
                  var10001 = false;
                  break label947;
               }

               if (var6 != var80) {
                  label945: {
                     label943: {
                        try {
                           if (var6 instanceof Closed) {
                              var3 = (Closed)var6;
                              break label943;
                           }
                        } catch (Throwable var79) {
                           var10000 = var79;
                           var10001 = false;
                           break label947;
                        }

                        ReceiveOrClosed var7;
                        try {
                           var7 = this.takeFirstReceiveOrPeekClosed();
                        } catch (Throwable var76) {
                           var10000 = var76;
                           var10001 = false;
                           break label947;
                        }

                        var3 = (Closed)var4;
                        if (var7 != null) {
                           label946: {
                              label920: {
                                 try {
                                    if (!(var7 instanceof Closed)) {
                                       break label920;
                                    }
                                 } catch (Throwable var78) {
                                    var10000 = var78;
                                    var10001 = false;
                                    break label947;
                                 }

                                 var3 = (Closed)var4;
                                 break label946;
                              }

                              try {
                                 var80 = var7.tryResumeReceive(var6, (LockFreeLinkedListNode.PrepareOp)null);
                              } catch (Throwable var75) {
                                 var10000 = var75;
                                 var10001 = false;
                                 break label947;
                              }

                              if (var80 != null) {
                                 label898: {
                                    boolean var1;
                                    label897: {
                                       label896: {
                                          try {
                                             if (!DebugKt.getASSERTIONS_ENABLED()) {
                                                break label898;
                                             }

                                             if (var80 == CancellableContinuationImplKt.RESUME_TOKEN) {
                                                break label896;
                                             }
                                          } catch (Throwable var74) {
                                             var10000 = var74;
                                             var10001 = false;
                                             break label947;
                                          }

                                          var1 = false;
                                          break label897;
                                       }

                                       var1 = true;
                                    }

                                    if (!var1) {
                                       try {
                                          AssertionError var81 = new AssertionError();
                                          throw (Throwable)var81;
                                       } catch (Throwable var72) {
                                          var10000 = var72;
                                          var10001 = false;
                                          break label947;
                                       }
                                    }
                                 }

                                 try {
                                    this.setSubHead(this.getSubHead() + 1L);
                                 } catch (Throwable var73) {
                                    var10000 = var73;
                                    var10001 = false;
                                    break label947;
                                 }

                                 this.subLock.unlock();
                                 Intrinsics.checkNotNull(var7);
                                 var7.completeResumeReceive(var6);
                                 var2 = true;
                                 continue;
                              }
                              break label945;
                           }
                        }
                     }

                     this.subLock.unlock();
                     break;
                  }
               }

               this.subLock.unlock();
               continue;
            }

            Throwable var82 = var10000;
            this.subLock.unlock();
            throw var82;
         }

         if (var3 != null) {
            this.close(var3.closeCause);
         }

         return var2;
      }

      public boolean close(Throwable var1) {
         boolean var2 = super.close(var1);
         if (var2) {
            ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, (Subscriber)null, this, 1, (Object)null);
            Lock var6 = (Lock)this.subLock;
            var6.lock();

            try {
               this.setSubHead(this.broadcastChannel.getTail());
               Unit var3 = Unit.INSTANCE;
            } finally {
               var6.unlock();
            }
         }

         return var2;
      }

      public final long getSubHead() {
         return this._subHead;
      }

      protected boolean isBufferAlwaysEmpty() {
         return false;
      }

      protected boolean isBufferAlwaysFull() {
         throw (Throwable)(new IllegalStateException("Should not be used".toString()));
      }

      protected boolean isBufferEmpty() {
         boolean var1;
         if (this.getSubHead() >= this.broadcastChannel.getTail()) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      protected boolean isBufferFull() {
         throw (Throwable)(new IllegalStateException("Should not be used".toString()));
      }

      protected Object pollInternal() {
         Lock var4 = (Lock)this.subLock;
         var4.lock();

         boolean var1;
         boolean var2;
         Object var5;
         label209: {
            label208: {
               Throwable var10000;
               label213: {
                  boolean var3;
                  boolean var10001;
                  try {
                     var5 = this.peekUnderLock();
                     var3 = var5 instanceof Closed;
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label213;
                  }

                  var2 = true;
                  if (var3) {
                     break label208;
                  }

                  try {
                     if (var5 == AbstractChannelKt.POLL_FAILED) {
                        break label208;
                     }
                  } catch (Throwable var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label213;
                  }

                  try {
                     this.setSubHead(this.getSubHead() + 1L);
                  } catch (Throwable var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label213;
                  }

                  var1 = true;
                  break label209;
               }

               Throwable var19 = var10000;
               var4.unlock();
               throw var19;
            }

            var1 = false;
         }

         var4.unlock();
         Object var18;
         if (!(var5 instanceof Closed)) {
            var18 = null;
         } else {
            var18 = var5;
         }

         Closed var20 = (Closed)var18;
         if (var20 != null) {
            this.close(var20.closeCause);
         }

         if (this.checkOffer()) {
            var1 = var2;
         }

         if (var1) {
            ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, (Subscriber)null, (Subscriber)null, 3, (Object)null);
         }

         return var5;
      }

      protected Object pollSelectInternal(SelectInstance var1) {
         Lock var6 = (Lock)this.subLock;
         var6.lock();

         boolean var2;
         boolean var3;
         Object var5;
         Object var28;
         label307: {
            label306: {
               Throwable var10000;
               label311: {
                  boolean var4;
                  boolean var10001;
                  try {
                     var5 = this.peekUnderLock();
                     var4 = var5 instanceof Closed;
                  } catch (Throwable var25) {
                     var10000 = var25;
                     var10001 = false;
                     break label311;
                  }

                  var3 = true;
                  var2 = false;
                  if (var4) {
                     var28 = var5;
                     break label307;
                  }

                  try {
                     if (var5 == AbstractChannelKt.POLL_FAILED) {
                        break label306;
                     }
                  } catch (Throwable var24) {
                     var10000 = var24;
                     var10001 = false;
                     break label311;
                  }

                  try {
                     if (!var1.trySelect()) {
                        var28 = SelectKt.getALREADY_SELECTED();
                        break label307;
                     }
                  } catch (Throwable var26) {
                     var10000 = var26;
                     var10001 = false;
                     break label311;
                  }

                  try {
                     this.setSubHead(this.getSubHead() + 1L);
                  } catch (Throwable var23) {
                     var10000 = var23;
                     var10001 = false;
                     break label311;
                  }

                  var2 = true;
                  var28 = var5;
                  break label307;
               }

               Throwable var27 = var10000;
               var6.unlock();
               throw var27;
            }

            var28 = var5;
         }

         var6.unlock();
         if (!(var28 instanceof Closed)) {
            var5 = null;
         } else {
            var5 = var28;
         }

         Closed var29 = (Closed)var5;
         if (var29 != null) {
            this.close(var29.closeCause);
         }

         if (this.checkOffer()) {
            var2 = var3;
         }

         if (var2) {
            ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, (Subscriber)null, (Subscriber)null, 3, (Object)null);
         }

         return var28;
      }

      public final void setSubHead(long var1) {
         this._subHead = var1;
      }
   }
}
