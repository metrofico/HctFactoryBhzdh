package kotlinx.coroutines.channels;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000CB9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012 \u0010\t\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0014¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001c\u0010\u001dJ#\u0010 \u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0012H\u0014¢\u0006\u0004\b#\u0010$J\u0011\u0010%\u001a\u0004\u0018\u00010\u0017H\u0014¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u0004\u0018\u00010\u00172\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b'\u0010(J\u0019\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010+R\u001e\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u00102\u001a\u00020/8T@\u0014X\u0094\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0016\u00104\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00103R\u0016\u00105\u001a\u00020\u00128D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u00128D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b7\u00106R\u0016\u00108\u001a\u00020\u00128D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b8\u00106R\u0016\u00109\u001a\u00020\u00128D@\u0004X\u0084\u0004¢\u0006\u0006\u001a\u0004\b9\u00106R\u0016\u0010:\u001a\u00020\u00128V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u00106R\u0016\u0010;\u001a\u00020\u00128V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u00106R\u0016\u0010<\u001a\u00020\u00128V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u00106R\u001a\u0010?\u001a\u00060=j\u0002`>8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010A¨\u0006B"},
   d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", "E", "", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "currentSize", "element", "enqueueElement", "(ILjava/lang/Object;)V", "Lkotlinx/coroutines/channels/Receive;", "receive", "", "enqueueReceiveInternal", "(Lkotlinx/coroutines/channels/Receive;)Z", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "ensureCapacity", "(I)V", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "wasClosed", "onCancelIdempotent", "(Z)V", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "updateBufferSize", "(I)Lkotlinx/coroutines/internal/Symbol;", "", "buffer", "[Ljava/lang/Object;", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "I", "head", "isBufferAlwaysEmpty", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isClosedForReceive", "isEmpty", "isFull", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractChannel;"},
   k = 1,
   mv = {1, 4, 0}
)
public class ArrayChannel extends AbstractChannel {
   private Object[] buffer;
   private final int capacity;
   private int head;
   private final ReentrantLock lock;
   private final BufferOverflow onBufferOverflow;
   private volatile int size;

   public ArrayChannel(int var1, BufferOverflow var2, Function1 var3) {
      super(var3);
      this.capacity = var1;
      this.onBufferOverflow = var2;
      boolean var4 = true;
      if (var1 < 1) {
         var4 = false;
      }

      if (var4) {
         this.lock = new ReentrantLock();
         Object[] var6 = new Object[Math.min(var1, 8)];
         ArraysKt.fill$default(var6, AbstractChannelKt.EMPTY, 0, 0, 6, (Object)null);
         Unit var5 = Unit.INSTANCE;
         this.buffer = var6;
         this.size = 0;
      } else {
         throw (Throwable)(new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + var1 + " was specified").toString()));
      }
   }

   private final void enqueueElement(int var1, Object var2) {
      Object[] var4;
      if (var1 < this.capacity) {
         this.ensureCapacity(var1);
         var4 = this.buffer;
         var4[(this.head + var1) % var4.length] = var2;
      } else {
         if (DebugKt.getASSERTIONS_ENABLED()) {
            boolean var3;
            if (this.onBufferOverflow == BufferOverflow.DROP_OLDEST) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (!var3) {
               throw (Throwable)(new AssertionError());
            }
         }

         var4 = this.buffer;
         int var5 = this.head;
         var4[var5 % var4.length] = null;
         var4[(var1 + var5) % var4.length] = var2;
         this.head = (var5 + 1) % var4.length;
      }

   }

   private final void ensureCapacity(int var1) {
      Object[] var4 = this.buffer;
      if (var1 >= var4.length) {
         int var3 = Math.min(var4.length * 2, this.capacity);
         var4 = new Object[var3];

         for(int var2 = 0; var2 < var1; ++var2) {
            Object[] var5 = this.buffer;
            var4[var2] = var5[(this.head + var2) % var5.length];
         }

         ArraysKt.fill(var4, AbstractChannelKt.EMPTY, var1, var3);
         this.buffer = var4;
         this.head = 0;
      }

   }

   private final Symbol updateBufferSize(int var1) {
      int var2 = this.capacity;
      Symbol var3 = null;
      if (var1 < var2) {
         this.size = var1 + 1;
         return null;
      } else {
         BufferOverflow var4 = this.onBufferOverflow;
         var1 = ArrayChannel$WhenMappings.$EnumSwitchMapping$0[var4.ordinal()];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  throw new NoWhenBranchMatchedException();
               }
            } else {
               var3 = AbstractChannelKt.OFFER_SUCCESS;
            }
         } else {
            var3 = AbstractChannelKt.OFFER_FAILED;
         }

         return var3;
      }
   }

   protected boolean enqueueReceiveInternal(Receive var1) {
      Lock var3 = (Lock)this.lock;
      var3.lock();

      boolean var2;
      try {
         var2 = super.enqueueReceiveInternal(var1);
      } finally {
         var3.unlock();
      }

      return var2;
   }

   protected Object enqueueSend(Send var1) {
      Lock var2 = (Lock)this.lock;
      var2.lock();

      Object var5;
      try {
         var5 = super.enqueueSend(var1);
      } finally {
         var2.unlock();
      }

      return var5;
   }

   protected String getBufferDebugString() {
      return "(buffer:capacity=" + this.capacity + ",size=" + this.size + ')';
   }

   protected final boolean isBufferAlwaysEmpty() {
      return false;
   }

   protected final boolean isBufferAlwaysFull() {
      return false;
   }

   protected final boolean isBufferEmpty() {
      boolean var1;
      if (this.size == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   protected final boolean isBufferFull() {
      boolean var1;
      if (this.size == this.capacity && this.onBufferOverflow == BufferOverflow.SUSPEND) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isClosedForReceive() {
      Lock var2 = (Lock)this.lock;
      var2.lock();

      boolean var1;
      try {
         var1 = super.isClosedForReceive();
      } finally {
         var2.unlock();
      }

      return var1;
   }

   public boolean isEmpty() {
      Lock var3 = (Lock)this.lock;
      var3.lock();

      boolean var1;
      try {
         var1 = this.isEmptyImpl();
      } finally {
         var3.unlock();
      }

      return var1;
   }

   public boolean isFull() {
      Lock var3 = (Lock)this.lock;
      var3.lock();

      boolean var1;
      try {
         var1 = this.isFullImpl();
      } finally {
         var3.unlock();
      }

      return var1;
   }

   protected Object offerInternal(Object var1) {
      ReceiveOrClosed var4 = (ReceiveOrClosed)null;
      Lock var100 = (Lock)this.lock;
      var100.lock();

      Throwable var10000;
      label1005: {
         int var3;
         Closed var5;
         boolean var10001;
         try {
            var3 = this.size;
            var5 = this.getClosedForSend();
         } catch (Throwable var95) {
            var10000 = var95;
            var10001 = false;
            break label1005;
         }

         if (var5 != null) {
            var100.unlock();
            return var5;
         }

         Symbol var101;
         try {
            var101 = this.updateBufferSize(var3);
         } catch (Throwable var94) {
            var10000 = var94;
            var10001 = false;
            break label1005;
         }

         if (var101 != null) {
            var100.unlock();
            return var101;
         }

         if (var3 == 0) {
            label1004: {
               ReceiveOrClosed var102;
               while(true) {
                  try {
                     var102 = this.takeFirstReceiveOrPeekClosed();
                  } catch (Throwable var92) {
                     var10000 = var92;
                     var10001 = false;
                     break label1005;
                  }

                  if (var102 == null) {
                     break label1004;
                  }

                  try {
                     if (var102 instanceof Closed) {
                        this.size = var3;
                        Intrinsics.checkNotNull(var102);
                        break;
                     }
                  } catch (Throwable var96) {
                     var10000 = var96;
                     var10001 = false;
                     break label1005;
                  }

                  Symbol var6;
                  try {
                     Intrinsics.checkNotNull(var102);
                     var6 = var102.tryResumeReceive(var1, (LockFreeLinkedListNode.PrepareOp)null);
                  } catch (Throwable var93) {
                     var10000 = var93;
                     var10001 = false;
                     break label1005;
                  }

                  if (var6 != null) {
                     label961: {
                        boolean var2;
                        label960: {
                           label959: {
                              try {
                                 if (!DebugKt.getASSERTIONS_ENABLED()) {
                                    break label961;
                                 }

                                 if (var6 == CancellableContinuationImplKt.RESUME_TOKEN) {
                                    break label959;
                                 }
                              } catch (Throwable var90) {
                                 var10000 = var90;
                                 var10001 = false;
                                 break label1005;
                              }

                              var2 = false;
                              break label960;
                           }

                           var2 = true;
                        }

                        if (!var2) {
                           try {
                              AssertionError var97 = new AssertionError();
                              throw (Throwable)var97;
                           } catch (Throwable var88) {
                              var10000 = var88;
                              var10001 = false;
                              break label1005;
                           }
                        }
                     }

                     try {
                        this.size = var3;
                        Unit var103 = Unit.INSTANCE;
                     } catch (Throwable var89) {
                        var10000 = var89;
                        var10001 = false;
                        break label1005;
                     }

                     var100.unlock();
                     Intrinsics.checkNotNull(var102);
                     var102.completeResumeReceive(var1);
                     Intrinsics.checkNotNull(var102);
                     return var102.getOfferResult();
                  }
               }

               var100.unlock();
               return var102;
            }
         }

         Symbol var99;
         try {
            this.enqueueElement(var3, var1);
            var99 = AbstractChannelKt.OFFER_SUCCESS;
         } catch (Throwable var91) {
            var10000 = var91;
            var10001 = false;
            break label1005;
         }

         var100.unlock();
         return var99;
      }

      Throwable var98 = var10000;
      var100.unlock();
      throw var98;
   }

   protected Object offerSelectInternal(Object var1, SelectInstance var2) {
      ReceiveOrClosed var4 = (ReceiveOrClosed)null;
      Lock var145 = (Lock)this.lock;
      var145.lock();

      Throwable var10000;
      label1361: {
         int var3;
         Closed var5;
         boolean var10001;
         try {
            var3 = this.size;
            var5 = this.getClosedForSend();
         } catch (Throwable var135) {
            var10000 = var135;
            var10001 = false;
            break label1361;
         }

         if (var5 != null) {
            var145.unlock();
            return var5;
         }

         Symbol var146;
         try {
            var146 = this.updateBufferSize(var3);
         } catch (Throwable var134) {
            var10000 = var134;
            var10001 = false;
            break label1361;
         }

         if (var146 != null) {
            var145.unlock();
            return var146;
         }

         if (var3 == 0) {
            while(true) {
               TryOfferDesc var6;
               Object var147;
               try {
                  var6 = this.describeTryOffer(var1);
                  var147 = var2.performAtomicTrySelect((AtomicDesc)var6);
               } catch (Throwable var133) {
                  var10000 = var133;
                  var10001 = false;
                  break label1361;
               }

               if (var147 == null) {
                  ReceiveOrClosed var148;
                  try {
                     this.size = var3;
                     var148 = (ReceiveOrClosed)var6.getResult();
                     Unit var144 = Unit.INSTANCE;
                  } catch (Throwable var128) {
                     var10000 = var128;
                     var10001 = false;
                     break label1361;
                  }

                  var145.unlock();
                  Intrinsics.checkNotNull(var148);
                  var148.completeResumeReceive(var1);
                  Intrinsics.checkNotNull(var148);
                  return var148.getOfferResult();
               }

               label1344:
               try {
                  if (var147 != AbstractChannelKt.OFFER_FAILED) {
                     break label1344;
                  }
                  break;
               } catch (Throwable var138) {
                  var10000 = var138;
                  var10001 = false;
                  break label1361;
               }

               try {
                  if (var147 == AtomicKt.RETRY_ATOMIC) {
                     continue;
                  }
               } catch (Throwable var137) {
                  var10000 = var137;
                  var10001 = false;
                  break label1361;
               }

               label1360: {
                  try {
                     if (var147 != SelectKt.getALREADY_SELECTED() && !(var147 instanceof Closed)) {
                        break label1360;
                     }
                  } catch (Throwable var131) {
                     var10000 = var131;
                     var10001 = false;
                     break label1361;
                  }

                  try {
                     this.size = var3;
                  } catch (Throwable var130) {
                     var10000 = var130;
                     var10001 = false;
                     break label1361;
                  }

                  var145.unlock();
                  return var147;
               }

               try {
                  StringBuilder var140 = new StringBuilder();
                  String var141 = var140.append("performAtomicTrySelect(describeTryOffer) returned ").append(var147).toString();
                  IllegalStateException var142 = new IllegalStateException(var141.toString());
                  throw (Throwable)var142;
               } catch (Throwable var129) {
                  var10000 = var129;
                  var10001 = false;
                  break label1361;
               }
            }
         }

         label1329: {
            try {
               if (var2.trySelect()) {
                  break label1329;
               }

               this.size = var3;
               var1 = SelectKt.getALREADY_SELECTED();
            } catch (Throwable var136) {
               var10000 = var136;
               var10001 = false;
               break label1361;
            }

            var145.unlock();
            return var1;
         }

         Symbol var139;
         try {
            this.enqueueElement(var3, var1);
            var139 = AbstractChannelKt.OFFER_SUCCESS;
         } catch (Throwable var132) {
            var10000 = var132;
            var10001 = false;
            break label1361;
         }

         var145.unlock();
         return var139;
      }

      Throwable var143 = var10000;
      var145.unlock();
      throw var143;
   }

   protected void onCancelIdempotent(boolean var1) {
      Function1 var7 = this.onUndeliveredElement;
      UndeliveredElementException var4 = null;
      UndeliveredElementException var5 = (UndeliveredElementException)null;
      Lock var6 = (Lock)this.lock;
      var6.lock();

      label325: {
         Throwable var10000;
         label329: {
            int var3;
            boolean var10001;
            try {
               var3 = this.size;
            } catch (Throwable var38) {
               var10000 = var38;
               var10001 = false;
               break label329;
            }

            for(int var2 = 0; var2 < var3; var4 = var5) {
               Object var8;
               try {
                  var8 = this.buffer[this.head];
               } catch (Throwable var37) {
                  var10000 = var37;
                  var10001 = false;
                  break label329;
               }

               var5 = var4;
               if (var7 != null) {
                  var5 = var4;

                  try {
                     if (var8 != AbstractChannelKt.EMPTY) {
                        var5 = OnUndeliveredElementKt.callUndeliveredElementCatchingException(var7, var8, var4);
                     }
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label329;
                  }
               }

               try {
                  this.buffer[this.head] = AbstractChannelKt.EMPTY;
                  this.head = (this.head + 1) % this.buffer.length;
               } catch (Throwable var35) {
                  var10000 = var35;
                  var10001 = false;
                  break label329;
               }

               ++var2;
            }

            label306:
            try {
               this.size = 0;
               Unit var40 = Unit.INSTANCE;
               break label325;
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               break label306;
            }
         }

         Throwable var39 = var10000;
         var6.unlock();
         throw var39;
      }

      var6.unlock();
      super.onCancelIdempotent(var1);
      if (var4 != null) {
         throw (Throwable)var4;
      }
   }

   protected Object pollInternal() {
      Send var6 = null;
      Send var7 = (Send)null;
      Lock var9 = (Lock)this.lock;
      var9.lock();

      Throwable var10000;
      label1854: {
         int var4;
         boolean var10001;
         try {
            var4 = this.size;
         } catch (Throwable var220) {
            var10000 = var220;
            var10001 = false;
            break label1854;
         }

         if (var4 == 0) {
            label1858: {
               Object var222;
               try {
                  var222 = this.getClosedForSend();
               } catch (Throwable var208) {
                  var10000 = var208;
                  var10001 = false;
                  break label1858;
               }

               if (var222 == null) {
                  try {
                     var222 = AbstractChannelKt.POLL_FAILED;
                  } catch (Throwable var207) {
                     var10000 = var207;
                     var10001 = false;
                     break label1858;
                  }
               }

               var9.unlock();
               return var222;
            }
         } else {
            label1859: {
               int var1;
               Object[] var223;
               try {
                  var223 = this.buffer;
                  var1 = this.head;
               } catch (Throwable var219) {
                  var10000 = var219;
                  var10001 = false;
                  break label1859;
               }

               Object var10 = var223[var1];
               var223[var1] = null;

               int var5;
               Symbol var8;
               try {
                  this.size = var4 - 1;
                  var8 = AbstractChannelKt.POLL_FAILED;
                  var5 = this.capacity;
               } catch (Throwable var218) {
                  var10000 = var218;
                  var10001 = false;
                  break label1859;
               }

               boolean var2 = false;
               boolean var3 = false;
               Object var224 = var8;
               boolean var221 = var2;
               if (var4 == var5) {
                  var6 = null;

                  while(true) {
                     try {
                        var7 = this.takeFirstSendOrPeekClosed();
                     } catch (Throwable var213) {
                        var10000 = var213;
                        var10001 = false;
                        break label1859;
                     }

                     if (var7 == null) {
                        var221 = var2;
                        var224 = var8;
                        break;
                     }

                     Symbol var225;
                     try {
                        Intrinsics.checkNotNull(var7);
                        var225 = var7.tryResumeSend((LockFreeLinkedListNode.PrepareOp)null);
                     } catch (Throwable var215) {
                        var10000 = var215;
                        var10001 = false;
                        break label1859;
                     }

                     if (var225 != null) {
                        label1862: {
                           try {
                              if (!DebugKt.getASSERTIONS_ENABLED()) {
                                 break label1862;
                              }
                           } catch (Throwable var216) {
                              var10000 = var216;
                              var10001 = false;
                              break label1859;
                           }

                           var221 = var3;

                           label1829: {
                              try {
                                 if (var225 != CancellableContinuationImplKt.RESUME_TOKEN) {
                                    break label1829;
                                 }
                              } catch (Throwable var217) {
                                 var10000 = var217;
                                 var10001 = false;
                                 break label1859;
                              }

                              var221 = true;
                           }

                           if (!var221) {
                              try {
                                 AssertionError var227 = new AssertionError();
                                 throw (Throwable)var227;
                              } catch (Throwable var209) {
                                 var10000 = var209;
                                 var10001 = false;
                                 break label1859;
                              }
                           }
                        }

                        Object var226;
                        try {
                           Intrinsics.checkNotNull(var7);
                           var226 = var7.getPollResult();
                        } catch (Throwable var212) {
                           var10000 = var212;
                           var10001 = false;
                           break label1859;
                        }

                        var221 = true;
                        var6 = var7;
                        var224 = var226;
                        break;
                     }

                     try {
                        Intrinsics.checkNotNull(var7);
                        var7.undeliveredElement();
                     } catch (Throwable var214) {
                        var10000 = var214;
                        var10001 = false;
                        break label1859;
                     }

                     var6 = var7;
                  }
               }

               try {
                  if (var224 != AbstractChannelKt.POLL_FAILED && !(var224 instanceof Closed)) {
                     this.size = var4;
                     Object[] var228 = this.buffer;
                     var228[(this.head + var4) % var228.length] = var224;
                  }
               } catch (Throwable var211) {
                  var10000 = var211;
                  var10001 = false;
                  break label1859;
               }

               try {
                  this.head = (this.head + 1) % this.buffer.length;
                  Unit var230 = Unit.INSTANCE;
               } catch (Throwable var210) {
                  var10000 = var210;
                  var10001 = false;
                  break label1859;
               }

               var9.unlock();
               if (var221) {
                  Intrinsics.checkNotNull(var6);
                  var6.completeResumeSend();
               }

               return var10;
            }
         }
      }

      Throwable var229 = var10000;
      var9.unlock();
      throw var229;
   }

   protected Object pollSelectInternal(SelectInstance var1) {
      Send var4 = null;
      Send var5 = (Send)null;
      Lock var7 = (Lock)this.lock;
      var7.lock();

      Object var255;
      label2189: {
         Object var259;
         label2188: {
            Throwable var10000;
            label2193: {
               int var3;
               boolean var10001;
               try {
                  var3 = this.size;
               } catch (Throwable var244) {
                  var10000 = var244;
                  var10001 = false;
                  break label2193;
               }

               if (var3 == 0) {
                  label2194: {
                     try {
                        var255 = this.getClosedForSend();
                     } catch (Throwable var236) {
                        var10000 = var236;
                        var10001 = false;
                        break label2194;
                     }

                     if (var255 == null) {
                        try {
                           var255 = AbstractChannelKt.POLL_FAILED;
                        } catch (Throwable var235) {
                           var10000 = var235;
                           var10001 = false;
                           break label2194;
                        }
                     }

                     var7.unlock();
                     return var255;
                  }
               } else {
                  label2198: {
                     Object[] var258;
                     int var2;
                     try {
                        var258 = this.buffer;
                        var2 = this.head;
                     } catch (Throwable var243) {
                        var10000 = var243;
                        var10001 = false;
                        break label2198;
                     }

                     Object var8 = var258[var2];
                     var258[var2] = null;

                     label2195: {
                        boolean var253;
                        label2181: {
                           Symbol var6;
                           label2180: {
                              try {
                                 this.size = var3 - 1;
                                 var6 = AbstractChannelKt.POLL_FAILED;
                                 if (var3 != this.capacity) {
                                    break label2180;
                                 }
                              } catch (Throwable var249) {
                                 var10000 = var249;
                                 var10001 = false;
                                 break label2198;
                              }

                              while(true) {
                                 TryPollDesc var9;
                                 try {
                                    var9 = this.describeTryPoll();
                                    var259 = var1.performAtomicTrySelect((AtomicDesc)var9);
                                 } catch (Throwable var242) {
                                    var10000 = var242;
                                    var10001 = false;
                                    break label2198;
                                 }

                                 if (var259 == null) {
                                    try {
                                       var4 = (Send)var9.getResult();
                                       Intrinsics.checkNotNull(var4);
                                       var259 = var4.getPollResult();
                                    } catch (Throwable var241) {
                                       var10000 = var241;
                                       var10001 = false;
                                       break label2198;
                                    }

                                    var253 = true;
                                    break label2181;
                                 }

                                 try {
                                    if (var259 == AbstractChannelKt.POLL_FAILED) {
                                       break;
                                    }
                                 } catch (Throwable var248) {
                                    var10000 = var248;
                                    var10001 = false;
                                    break label2198;
                                 }

                                 label2169:
                                 try {
                                    if (var259 != AtomicKt.RETRY_ATOMIC) {
                                       break label2169;
                                    }
                                    continue;
                                 } catch (Throwable var247) {
                                    var10000 = var247;
                                    var10001 = false;
                                    break label2198;
                                 }

                                 try {
                                    if (var259 == SelectKt.getALREADY_SELECTED()) {
                                       this.size = var3;
                                       this.buffer[this.head] = var8;
                                       break label2188;
                                    }
                                 } catch (Throwable var240) {
                                    var10000 = var240;
                                    var10001 = false;
                                    break label2198;
                                 }

                                 try {
                                    if (!(var259 instanceof Closed)) {
                                       break label2195;
                                    }

                                    var4 = (Send)var259;
                                 } catch (Throwable var246) {
                                    var10000 = var246;
                                    var10001 = false;
                                    break label2198;
                                 }

                                 var253 = true;
                                 break label2181;
                              }
                           }

                           var253 = false;
                           var259 = var6;
                        }

                        label2200: {
                           try {
                              if (var259 != AbstractChannelKt.POLL_FAILED && !(var259 instanceof Closed)) {
                                 this.size = var3;
                                 Object[] var252 = this.buffer;
                                 var252[(this.head + var3) % var252.length] = var259;
                                 break label2200;
                              }
                           } catch (Throwable var245) {
                              var10000 = var245;
                              var10001 = false;
                              break label2198;
                           }

                           try {
                              if (!var1.trySelect()) {
                                 this.size = var3;
                                 this.buffer[this.head] = var8;
                                 var255 = SelectKt.getALREADY_SELECTED();
                                 break label2189;
                              }
                           } catch (Throwable var239) {
                              var10000 = var239;
                              var10001 = false;
                              break label2198;
                           }
                        }

                        try {
                           this.head = (this.head + 1) % this.buffer.length;
                           Unit var254 = Unit.INSTANCE;
                        } catch (Throwable var238) {
                           var10000 = var238;
                           var10001 = false;
                           break label2198;
                        }

                        var7.unlock();
                        if (var253) {
                           Intrinsics.checkNotNull(var4);
                           var4.completeResumeSend();
                        }

                        return var8;
                     }

                     label2128:
                     try {
                        StringBuilder var250 = new StringBuilder();
                        String var251 = var250.append("performAtomicTrySelect(describeTryOffer) returned ").append(var259).toString();
                        IllegalStateException var257 = new IllegalStateException(var251.toString());
                        throw (Throwable)var257;
                     } catch (Throwable var237) {
                        var10000 = var237;
                        var10001 = false;
                        break label2128;
                     }
                  }
               }
            }

            Throwable var256 = var10000;
            var7.unlock();
            throw var256;
         }

         var7.unlock();
         return var259;
      }

      var7.unlock();
      return var255;
   }
}
