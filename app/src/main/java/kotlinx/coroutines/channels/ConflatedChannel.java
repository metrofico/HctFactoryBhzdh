package kotlinx.coroutines.channels;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
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
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012 \u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0018\u001a\u00020\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0014J\u0015\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u001dJ!\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00028\u00002\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0014¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\rH\u0014J\n\u0010$\u001a\u0004\u0018\u00010\u0017H\u0014J\u0016\u0010%\u001a\u0004\u0018\u00010\u00172\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0014J\u0014\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0002R\u0014\u0010\b\u001a\u00020\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000eR\u0012\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("},
   d2 = {"Lkotlinx/coroutines/channels/ConflatedChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "onUndeliveredElement", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "(Lkotlin/jvm/functions/Function1;)V", "bufferDebugString", "", "getBufferDebugString", "()Ljava/lang/String;", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isEmpty", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "value", "", "enqueueReceiveInternal", "receive", "Lkotlinx/coroutines/channels/Receive;", "offerInternal", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "onCancelIdempotent", "wasClosed", "pollInternal", "pollSelectInternal", "updateValueLocked", "Lkotlinx/coroutines/internal/UndeliveredElementException;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class ConflatedChannel extends AbstractChannel {
   private final ReentrantLock lock = new ReentrantLock();
   private Object value;

   public ConflatedChannel(Function1 var1) {
      super(var1);
      this.value = AbstractChannelKt.EMPTY;
   }

   private final UndeliveredElementException updateValueLocked(Object var1) {
      Object var3 = this.value;
      Symbol var4 = AbstractChannelKt.EMPTY;
      UndeliveredElementException var2 = null;
      if (var3 != var4) {
         Function1 var5 = this.onUndeliveredElement;
         if (var5 != null) {
            var2 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(var5, var3, (UndeliveredElementException)null, 2, (Object)null);
         }
      }

      this.value = var1;
      return var2;
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

   protected String getBufferDebugString() {
      return "(value=" + this.value + ')';
   }

   protected final boolean isBufferAlwaysEmpty() {
      return false;
   }

   protected final boolean isBufferAlwaysFull() {
      return false;
   }

   protected final boolean isBufferEmpty() {
      boolean var1;
      if (this.value == AbstractChannelKt.EMPTY) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   protected final boolean isBufferFull() {
      return false;
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

   protected Object offerInternal(Object var1) {
      ReceiveOrClosed var3 = (ReceiveOrClosed)null;
      Lock var142 = (Lock)this.lock;
      var142.lock();

      Throwable var10000;
      label1238: {
         Closed var4;
         boolean var10001;
         try {
            var4 = this.getClosedForSend();
         } catch (Throwable var135) {
            var10000 = var135;
            var10001 = false;
            break label1238;
         }

         if (var4 != null) {
            var142.unlock();
            return var4;
         }

         label1239: {
            try {
               if (this.value != AbstractChannelKt.EMPTY) {
                  break label1239;
               }
            } catch (Throwable var136) {
               var10000 = var136;
               var10001 = false;
               break label1238;
            }

            ReceiveOrClosed var143;
            while(true) {
               try {
                  var143 = this.takeFirstReceiveOrPeekClosed();
               } catch (Throwable var133) {
                  var10000 = var133;
                  var10001 = false;
                  break label1238;
               }

               if (var143 == null) {
                  break label1239;
               }

               try {
                  if (var143 instanceof Closed) {
                     Intrinsics.checkNotNull(var143);
                     break;
                  }
               } catch (Throwable var137) {
                  var10000 = var137;
                  var10001 = false;
                  break label1238;
               }

               Symbol var5;
               try {
                  Intrinsics.checkNotNull(var143);
                  var5 = var143.tryResumeReceive(var1, (LockFreeLinkedListNode.PrepareOp)null);
               } catch (Throwable var134) {
                  var10000 = var134;
                  var10001 = false;
                  break label1238;
               }

               if (var5 != null) {
                  label1198: {
                     boolean var2;
                     label1197: {
                        label1196: {
                           try {
                              if (!DebugKt.getASSERTIONS_ENABLED()) {
                                 break label1198;
                              }

                              if (var5 != CancellableContinuationImplKt.RESUME_TOKEN) {
                                 break label1196;
                              }
                           } catch (Throwable var129) {
                              var10000 = var129;
                              var10001 = false;
                              break label1238;
                           }

                           var2 = true;
                           break label1197;
                        }

                        var2 = false;
                     }

                     if (!var2) {
                        try {
                           AssertionError var138 = new AssertionError();
                           throw (Throwable)var138;
                        } catch (Throwable var127) {
                           var10000 = var127;
                           var10001 = false;
                           break label1238;
                        }
                     }
                  }

                  try {
                     Unit var144 = Unit.INSTANCE;
                  } catch (Throwable var128) {
                     var10000 = var128;
                     var10001 = false;
                     break label1238;
                  }

                  var142.unlock();
                  Intrinsics.checkNotNull(var143);
                  var143.completeResumeReceive(var1);
                  Intrinsics.checkNotNull(var143);
                  return var143.getOfferResult();
               }
            }

            var142.unlock();
            return var143;
         }

         UndeliveredElementException var139;
         try {
            var139 = this.updateValueLocked(var1);
         } catch (Throwable var132) {
            var10000 = var132;
            var10001 = false;
            break label1238;
         }

         if (var139 == null) {
            label1202: {
               Symbol var140;
               try {
                  var140 = AbstractChannelKt.OFFER_SUCCESS;
               } catch (Throwable var130) {
                  var10000 = var130;
                  var10001 = false;
                  break label1202;
               }

               var142.unlock();
               return var140;
            }
         } else {
            label1204:
            try {
               throw (Throwable)var139;
            } catch (Throwable var131) {
               var10000 = var131;
               var10001 = false;
               break label1204;
            }
         }
      }

      Throwable var141 = var10000;
      var142.unlock();
      throw var141;
   }

   protected Object offerSelectInternal(Object var1, SelectInstance var2) {
      ReceiveOrClosed var3 = (ReceiveOrClosed)null;
      Lock var169 = (Lock)this.lock;
      var169.lock();

      Object var170;
      label1381: {
         label1380: {
            Throwable var10000;
            label1385: {
               Closed var4;
               boolean var10001;
               try {
                  var4 = this.getClosedForSend();
               } catch (Throwable var157) {
                  var10000 = var157;
                  var10001 = false;
                  break label1385;
               }

               if (var4 != null) {
                  var169.unlock();
                  return var4;
               }

               label1386: {
                  label1376: {
                     try {
                        if (this.value != AbstractChannelKt.EMPTY) {
                           break label1376;
                        }
                     } catch (Throwable var160) {
                        var10000 = var160;
                        var10001 = false;
                        break label1385;
                     }

                     while(true) {
                        TryOfferDesc var5;
                        try {
                           var5 = this.describeTryOffer(var1);
                           var170 = var2.performAtomicTrySelect((AtomicDesc)var5);
                        } catch (Throwable var156) {
                           var10000 = var156;
                           var10001 = false;
                           break label1385;
                        }

                        if (var170 == null) {
                           ReceiveOrClosed var171;
                           try {
                              var171 = (ReceiveOrClosed)var5.getResult();
                              Unit var168 = Unit.INSTANCE;
                           } catch (Throwable var150) {
                              var10000 = var150;
                              var10001 = false;
                              break label1385;
                           }

                           var169.unlock();
                           Intrinsics.checkNotNull(var171);
                           var171.completeResumeReceive(var1);
                           Intrinsics.checkNotNull(var171);
                           return var171.getOfferResult();
                        }

                        label1371:
                        try {
                           if (var170 != AbstractChannelKt.OFFER_FAILED) {
                              break label1371;
                           }
                           break;
                        } catch (Throwable var161) {
                           var10000 = var161;
                           var10001 = false;
                           break label1385;
                        }

                        try {
                           if (var170 != AtomicKt.RETRY_ATOMIC) {
                              break label1386;
                           }
                        } catch (Throwable var159) {
                           var10000 = var159;
                           var10001 = false;
                           break label1385;
                        }
                     }
                  }

                  try {
                     if (!var2.trySelect()) {
                        var1 = SelectKt.getALREADY_SELECTED();
                        break label1380;
                     }
                  } catch (Throwable var158) {
                     var10000 = var158;
                     var10001 = false;
                     break label1385;
                  }

                  UndeliveredElementException var164;
                  try {
                     var164 = this.updateValueLocked(var1);
                  } catch (Throwable var154) {
                     var10000 = var154;
                     var10001 = false;
                     break label1385;
                  }

                  if (var164 == null) {
                     Symbol var165;
                     try {
                        var165 = AbstractChannelKt.OFFER_SUCCESS;
                     } catch (Throwable var152) {
                        var10000 = var152;
                        var10001 = false;
                        break label1385;
                     }

                     var169.unlock();
                     return var165;
                  } else {
                     try {
                        throw (Throwable)var164;
                     } catch (Throwable var153) {
                        var10000 = var153;
                        var10001 = false;
                        break label1385;
                     }
                  }
               }

               try {
                  if (var170 == SelectKt.getALREADY_SELECTED() || var170 instanceof Closed) {
                     break label1381;
                  }
               } catch (Throwable var155) {
                  var10000 = var155;
                  var10001 = false;
                  break label1385;
               }

               label1330:
               try {
                  StringBuilder var162 = new StringBuilder();
                  String var163 = var162.append("performAtomicTrySelect(describeTryOffer) returned ").append(var170).toString();
                  IllegalStateException var166 = new IllegalStateException(var163.toString());
                  throw (Throwable)var166;
               } catch (Throwable var151) {
                  var10000 = var151;
                  var10001 = false;
                  break label1330;
               }
            }

            Throwable var167 = var10000;
            var169.unlock();
            throw var167;
         }

         var169.unlock();
         return var1;
      }

      var169.unlock();
      return var170;
   }

   protected void onCancelIdempotent(boolean var1) {
      UndeliveredElementException var2 = (UndeliveredElementException)null;
      Lock var7 = (Lock)this.lock;
      var7.lock();

      UndeliveredElementException var4;
      try {
         var4 = this.updateValueLocked(AbstractChannelKt.EMPTY);
         Unit var3 = Unit.INSTANCE;
      } finally {
         var7.unlock();
      }

      super.onCancelIdempotent(var1);
      if (var4 != null) {
         throw (Throwable)var4;
      }
   }

   protected Object pollInternal() {
      Lock var2 = (Lock)this.lock;
      var2.lock();

      Throwable var10000;
      label147: {
         Object var1;
         boolean var10001;
         label148: {
            try {
               if (this.value == AbstractChannelKt.EMPTY) {
                  var1 = this.getClosedForSend();
                  break label148;
               }
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label147;
            }

            try {
               var1 = this.value;
               this.value = AbstractChannelKt.EMPTY;
               Unit var3 = Unit.INSTANCE;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label147;
            }

            var2.unlock();
            return var1;
         }

         if (var1 == null) {
            try {
               var1 = AbstractChannelKt.POLL_FAILED;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label147;
            }
         }

         var2.unlock();
         return var1;
      }

      Throwable var16 = var10000;
      var2.unlock();
      throw var16;
   }

   protected Object pollSelectInternal(SelectInstance var1) {
      Lock var2 = (Lock)this.lock;
      var2.lock();

      Throwable var10000;
      label242: {
         boolean var10001;
         Object var24;
         label243: {
            try {
               if (this.value != AbstractChannelKt.EMPTY) {
                  break label243;
               }

               var24 = this.getClosedForSend();
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label242;
            }

            if (var24 == null) {
               try {
                  var24 = AbstractChannelKt.POLL_FAILED;
               } catch (Throwable var20) {
                  var10000 = var20;
                  var10001 = false;
                  break label242;
               }
            }

            var2.unlock();
            return var24;
         }

         label244: {
            try {
               if (!var1.trySelect()) {
                  var24 = SelectKt.getALREADY_SELECTED();
                  break label244;
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label242;
            }

            try {
               var24 = this.value;
               this.value = AbstractChannelKt.EMPTY;
               Unit var3 = Unit.INSTANCE;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label242;
            }

            var2.unlock();
            return var24;
         }

         var2.unlock();
         return var24;
      }

      Throwable var25 = var10000;
      var2.unlock();
      throw var25;
   }
}
