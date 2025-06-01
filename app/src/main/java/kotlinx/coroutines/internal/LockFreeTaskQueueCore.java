package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.DebugKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0016\b\u0000\u0018\u0000 /*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002/0B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0011\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0005¢\u0006\u0004\b\u0012\u0010\u0013J3\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000e2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\t\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0005¢\u0006\u0004\b\u0017\u0010\u0013J-\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b\"\u0004\b\u0001\u0010\u00182\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\"\u0010#J3\u0010&\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000e2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003H\u0002¢\u0006\u0004\b&\u0010'R\u0016\u0010\u0004\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010(R\u0013\u0010)\u001a\u00020\u00058F@\u0006¢\u0006\u0006\u001a\u0004\b)\u0010\u0013R\u0016\u0010*\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010(R\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010+R\u0013\u0010.\u001a\u00020\u00038F@\u0006¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u00061"},
   d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "", "E", "", "capacity", "", "singleConsumer", "<init>", "(IZ)V", "element", "addLast", "(Ljava/lang/Object;)I", "", "state", "Lkotlinx/coroutines/internal/Core;", "allocateNextCopy", "(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "allocateOrGetNextCopy", "close", "()Z", "index", "fillPlaceholder", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "isClosed", "R", "Lkotlin/Function1;", "transform", "", "map", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "markFrozen", "()J", "next", "()Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "removeFirstOrNull", "()Ljava/lang/Object;", "oldHead", "newHead", "removeSlowPath", "(II)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "I", "isEmpty", "mask", "Z", "getSize", "()I", "size", "Companion", "Placeholder", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class LockFreeTaskQueueCore {
   public static final int ADD_CLOSED = 2;
   public static final int ADD_FROZEN = 1;
   public static final int ADD_SUCCESS = 0;
   public static final int CAPACITY_BITS = 30;
   public static final long CLOSED_MASK = 2305843009213693952L;
   public static final int CLOSED_SHIFT = 61;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final long FROZEN_MASK = 1152921504606846976L;
   public static final int FROZEN_SHIFT = 60;
   public static final long HEAD_MASK = 1073741823L;
   public static final int HEAD_SHIFT = 0;
   public static final int INITIAL_CAPACITY = 8;
   public static final int MAX_CAPACITY_MASK = 1073741823;
   public static final int MIN_ADD_SPIN_CAPACITY = 1024;
   public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
   public static final long TAIL_MASK = 1152921503533105152L;
   public static final int TAIL_SHIFT = 30;
   private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");
   private static final AtomicLongFieldUpdater _state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");
   private volatile Object _next;
   private volatile long _state;
   private AtomicReferenceArray array;
   private final int capacity;
   private final int mask;
   private final boolean singleConsumer;

   public LockFreeTaskQueueCore(int var1, boolean var2) {
      this.capacity = var1;
      this.singleConsumer = var2;
      int var5 = var1 - 1;
      this.mask = var5;
      this._next = null;
      this._state = 0L;
      this.array = new AtomicReferenceArray(var1);
      boolean var4 = false;
      boolean var3;
      if (var5 <= 1073741823) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         var3 = var4;
         if ((var1 & var5) == 0) {
            var3 = true;
         }

         if (!var3) {
            throw (Throwable)(new IllegalStateException("Check failed.".toString()));
         }
      } else {
         throw (Throwable)(new IllegalStateException("Check failed.".toString()));
      }
   }

   private final LockFreeTaskQueueCore allocateNextCopy(long var1) {
      LockFreeTaskQueueCore var7 = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
      int var3 = (int)((1073741823L & var1) >> 0);
      int var4 = (int)((1152921503533105152L & var1) >> 30);

      while(true) {
         int var5 = this.mask;
         if ((var3 & var5) == (var4 & var5)) {
            var7._state = Companion.wo(var1, 1152921504606846976L);
            return var7;
         }

         Object var6 = this.array.get(var5 & var3);
         if (var6 == null) {
            var6 = new Placeholder(var3);
         }

         var7.array.set(var7.mask & var3, var6);
         ++var3;
      }
   }

   private final LockFreeTaskQueueCore allocateOrGetNextCopy(long var1) {
      while(true) {
         LockFreeTaskQueueCore var3 = (LockFreeTaskQueueCore)this._next;
         if (var3 != null) {
            return var3;
         }

         AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, (Object)null, this.allocateNextCopy(var1));
      }
   }

   private final LockFreeTaskQueueCore fillPlaceholder(int var1, Object var2) {
      Object var3 = this.array.get(this.mask & var1);
      if (var3 instanceof Placeholder && ((Placeholder)var3).index == var1) {
         this.array.set(var1 & this.mask, var2);
         return this;
      } else {
         return null;
      }
   }

   private final long markFrozen() {
      long var1;
      long var3;
      do {
         var3 = this._state;
         if ((var3 & 1152921504606846976L) != 0L) {
            return var3;
         }

         var1 = var3 | 1152921504606846976L;
      } while(!_state$FU.compareAndSet(this, var3, var1));

      return var1;
   }

   private final LockFreeTaskQueueCore removeSlowPath(int var1, int var2) {
      int var4;
      long var5;
      Companion var7;
      do {
         var5 = this._state;
         var7 = Companion;
         boolean var3 = false;
         var4 = (int)((1073741823L & var5) >> 0);
         if (DebugKt.getASSERTIONS_ENABLED()) {
            if (var4 == var1) {
               var3 = true;
            }

            if (!var3) {
               throw (Throwable)(new AssertionError());
            }
         }

         if ((1152921504606846976L & var5) != 0L) {
            return this.next();
         }
      } while(!_state$FU.compareAndSet(this, var5, var7.updateHead(var5, var2)));

      this.array.set(this.mask & var4, (Object)null);
      return null;
   }

   public final int addLast(Object var1) {
      while(true) {
         long var5 = this._state;
         if ((3458764513820540928L & var5) != 0L) {
            return Companion.addFailReason(var5);
         }

         Companion var7 = Companion;
         int var3 = (int)((1073741823L & var5) >> 0);
         int var2 = (int)((1152921503533105152L & var5) >> 30);
         int var4 = this.mask;
         if ((var2 + 2 & var4) == (var3 & var4)) {
            return 1;
         }

         if (!this.singleConsumer && this.array.get(var2 & var4) != null) {
            var4 = this.capacity;
            if (var4 < 1024 || (var2 - var3 & 1073741823) > var4 >> 1) {
               return 1;
            }
         } else if (_state$FU.compareAndSet(this, var5, var7.updateTail(var5, var2 + 1 & 1073741823))) {
            this.array.set(var2 & var4, var1);
            LockFreeTaskQueueCore var8 = (LockFreeTaskQueueCore)this;
            var8 = this;

            while((var8._state & 1152921504606846976L) != 0L) {
               var8 = var8.next().fillPlaceholder(var2, var1);
               if (var8 == null) {
                  break;
               }
            }

            return 0;
         }
      }
   }

   public final boolean close() {
      long var1;
      do {
         var1 = this._state;
         if ((var1 & 2305843009213693952L) != 0L) {
            return true;
         }

         if ((1152921504606846976L & var1) != 0L) {
            return false;
         }
      } while(!_state$FU.compareAndSet(this, var1, var1 | 2305843009213693952L));

      return true;
   }

   public final int getSize() {
      long var2 = this._state;
      int var1 = (int)((1073741823L & var2) >> 0);
      return (int)((var2 & 1152921503533105152L) >> 30) - var1 & 1073741823;
   }

   public final boolean isClosed() {
      boolean var1;
      if ((this._state & 2305843009213693952L) != 0L) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean isEmpty() {
      long var1 = this._state;
      boolean var3 = false;
      if ((int)((1073741823L & var1) >> 0) == (int)((var1 & 1152921503533105152L) >> 30)) {
         var3 = true;
      }

      return var3;
   }

   public final List map(Function1 var1) {
      ArrayList var7 = new ArrayList(this.capacity);
      long var5 = this._state;
      int var2 = (int)((1073741823L & var5) >> 0);
      int var3 = (int)((var5 & 1152921503533105152L) >> 30);

      while(true) {
         int var4 = this.mask;
         if ((var2 & var4) == (var3 & var4)) {
            return (List)var7;
         }

         Object var8 = this.array.get(var4 & var2);
         if (var8 != null && !(var8 instanceof Placeholder)) {
            var7.add(var1.invoke(var8));
         }

         ++var2;
      }
   }

   public final LockFreeTaskQueueCore next() {
      return this.allocateOrGetNextCopy(this.markFrozen());
   }

   public final Object removeFirstOrNull() {
      while(true) {
         long var4 = this._state;
         if ((1152921504606846976L & var4) != 0L) {
            return REMOVE_FROZEN;
         }

         Companion var6 = Companion;
         int var1 = (int)((1073741823L & var4) >> 0);
         int var2 = (int)((1152921503533105152L & var4) >> 30);
         int var3 = this.mask;
         if ((var2 & var3) == (var1 & var3)) {
            return null;
         }

         Object var7 = this.array.get(var3 & var1);
         if (var7 == null) {
            if (this.singleConsumer) {
               return null;
            }
         } else {
            if (var7 instanceof Placeholder) {
               return null;
            }

            var2 = var1 + 1 & 1073741823;
            if (_state$FU.compareAndSet(this, var4, var6.updateHead(var4, var2))) {
               this.array.set(this.mask & var1, (Object)null);
               return var7;
            }

            if (this.singleConsumer) {
               LockFreeTaskQueueCore var8 = (LockFreeTaskQueueCore)this;
               var8 = this;

               do {
                  var8 = var8.removeSlowPath(var1, var2);
               } while(var8 != null);

               return var7;
            }
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0016\u001a\u00020\u0004*\u00020\tJ\u0012\u0010\u0017\u001a\u00020\t*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0004J\u0012\u0010\u0019\u001a\u00020\t*\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0004JP\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0001\u0010\u001c*\u00020\t26\u0010\u001d\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u001c0\u001eH\u0086\b¢\u0006\u0002\u0010#J\u0015\u0010$\u001a\u00020\t*\u00020\t2\u0006\u0010%\u001a\u00020\tH\u0086\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006&"},
      d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;", "", "()V", "ADD_CLOSED", "", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "MIN_ADD_SPIN_CAPACITY", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "TAIL_MASK", "TAIL_SHIFT", "addFailReason", "updateHead", "newHead", "updateTail", "newTail", "withState", "T", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "head", "tail", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "wo", "other", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final int addFailReason(long var1) {
         byte var3;
         if ((var1 & 2305843009213693952L) != 0L) {
            var3 = 2;
         } else {
            var3 = 1;
         }

         return var3;
      }

      public final long updateHead(long var1, int var3) {
         Companion var4 = (Companion)this;
         return this.wo(var1, 1073741823L) | (long)var3 << 0;
      }

      public final long updateTail(long var1, int var3) {
         Companion var4 = (Companion)this;
         return this.wo(var1, 1152921503533105152L) | (long)var3 << 30;
      }

      public final Object withState(long var1, Function2 var3) {
         return var3.invoke((int)((1073741823L & var1) >> 0), (int)((var1 & 1152921503533105152L) >> 30));
      }

      public final long wo(long var1, long var3) {
         return var1 & ~var3;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;", "", "index", "", "(I)V", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Placeholder {
      public final int index;

      public Placeholder(int var1) {
         this.index = var1;
      }
   }
}
