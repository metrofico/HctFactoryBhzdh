package kotlinx.coroutines.debug.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010*:\u0003&'(B\u0011\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ\u001a\u0010\u0011\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0014\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0016\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u00002\b\u0010\u0013\u001a\u0004\u0018\u00018\u0001H\u0002¢\u0006\u0004\b\u0016\u0010\u0015J\u0019\u0010\u0017\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0017\u0010\u0012J\r\u0010\u0018\u001a\u00020\n¢\u0006\u0004\b\u0018\u0010\u000eR(\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a0\u00198V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00198V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0016\u0010#\u001a\u00020 8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010$8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%¨\u0006)"},
   d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "", "K", "V", "", "weakRefQueue", "<init>", "(Z)V", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "w", "", "cleanWeakRef", "(Lkotlinx/coroutines/debug/internal/HashedWeakRef;)V", "clear", "()V", "decrementSize", "key", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "value", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putSynchronized", "remove", "runWeakRefQueueCleaningLoopUntilInterrupted", "", "", "getEntries", "()Ljava/util/Set;", "entries", "getKeys", "keys", "", "getSize", "()I", "size", "Ljava/lang/ref/ReferenceQueue;", "Ljava/lang/ref/ReferenceQueue;", "Core", "Entry", "KeyValueSet", "kotlinx-coroutines-core", "Lkotlin/collections/AbstractMutableMap;"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ConcurrentWeakMap extends AbstractMutableMap {
   private static final AtomicIntegerFieldUpdater _size$FU = AtomicIntegerFieldUpdater.newUpdater(ConcurrentWeakMap.class, "_size");
   private volatile int _size;
   volatile Object core;
   private final ReferenceQueue weakRefQueue;

   public ConcurrentWeakMap() {
      this(false, 1, (DefaultConstructorMarker)null);
   }

   public ConcurrentWeakMap(boolean var1) {
      this._size = 0;
      this.core = new Core(this, 16);
      ReferenceQueue var2;
      if (var1) {
         var2 = new ReferenceQueue();
      } else {
         var2 = null;
      }

      this.weakRefQueue = var2;
   }

   // $FF: synthetic method
   public ConcurrentWeakMap(boolean var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      this(var1);
   }

   private final void cleanWeakRef(HashedWeakRef var1) {
      ((Core)this.core).cleanWeakRef(var1);
   }

   private final void decrementSize() {
      _size$FU.decrementAndGet(this);
   }

   private final Object putSynchronized(Object var1, Object var2) {
      synchronized(this){}

      Throwable var10000;
      label123: {
         boolean var10001;
         Core var3;
         try {
            var3 = (Core)this.core;
         } catch (Throwable var17) {
            var10000 = var17;
            var10001 = false;
            break label123;
         }

         while(true) {
            Object var4;
            Symbol var5;
            try {
               var4 = Core.putImpl$default(var3, var1, var2, (HashedWeakRef)null, 4, (Object)null);
               var5 = ConcurrentWeakMapKt.access$getREHASH$p();
            } catch (Throwable var16) {
               var10000 = var16;
               var10001 = false;
               break;
            }

            if (var4 != var5) {
               return var4;
            }

            try {
               var3 = var3.rehash();
               this.core = var3;
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break;
            }
         }
      }

      Throwable var18 = var10000;
      throw var18;
   }

   public void clear() {
      Iterator var1 = this.keySet().iterator();

      while(var1.hasNext()) {
         this.remove(var1.next());
      }

   }

   public Object get(Object var1) {
      return var1 != null ? ((Core)this.core).getImpl(var1) : null;
   }

   public Set getEntries() {
      return (Set)(new KeyValueSet(this, (Function2)null.INSTANCE));
   }

   public Set getKeys() {
      return (Set)(new KeyValueSet(this, (Function2)null.INSTANCE));
   }

   public int getSize() {
      return this._size;
   }

   public Object put(Object var1, Object var2) {
      Object var4 = Core.putImpl$default((Core)this.core, var1, var2, (HashedWeakRef)null, 4, (Object)null);
      Object var3 = var4;
      if (var4 == ConcurrentWeakMapKt.access$getREHASH$p()) {
         var3 = this.putSynchronized(var1, var2);
      }

      if (var3 == null) {
         _size$FU.incrementAndGet(this);
      }

      return var3;
   }

   public Object remove(Object var1) {
      if (var1 != null) {
         Object var3 = Core.putImpl$default((Core)this.core, var1, (Object)null, (HashedWeakRef)null, 4, (Object)null);
         Object var2 = var3;
         if (var3 == ConcurrentWeakMapKt.access$getREHASH$p()) {
            var2 = this.putSynchronized(var1, (Object)null);
         }

         if (var2 != null) {
            _size$FU.decrementAndGet(this);
         }

         return var2;
      } else {
         return null;
      }
   }

   public final void runWeakRefQueueCleaningLoopUntilInterrupted() {
      boolean var1;
      if (this.weakRefQueue != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (!var1) {
         throw (Throwable)(new IllegalStateException("Must be created with weakRefQueue = true".toString()));
      } else {
         while(true) {
            boolean var10001;
            Reference var2;
            try {
               var2 = this.weakRefQueue.remove();
            } catch (InterruptedException var5) {
               var10001 = false;
               break;
            }

            if (var2 != null) {
               try {
                  this.cleanWeakRef((HashedWeakRef)var2);
               } catch (InterruptedException var3) {
                  var10001 = false;
                  break;
               }
            } else {
               try {
                  NullPointerException var6 = new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.debug.internal.HashedWeakRef<*>");
                  throw var6;
               } catch (InterruptedException var4) {
                  var10001 = false;
                  break;
               }
            }
         }

         Thread.currentThread().interrupt();
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0082\u0004\u0018\u00002\u00020\u0018:\u0001#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\b\u001a\u00020\u00072\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ3\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00020\u0013\"\u0004\b\u0002\u0010\u00102\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0015J3\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\n\u001a\u00028\u00002\b\u0010\u0016\u001a\u0004\u0018\u00018\u00012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001c\u001a\u00120\u0000R\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010 R\u0016\u0010!\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010 R\u0016\u0010\"\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010 ¨\u0006$"},
      d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "", "allocated", "<init>", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;I)V", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "weakRef", "", "cleanWeakRef", "(Lkotlinx/coroutines/debug/internal/HashedWeakRef;)V", "key", "getImpl", "(Ljava/lang/Object;)Ljava/lang/Object;", "hash", "index", "(I)I", "E", "Lkotlin/Function2;", "factory", "", "keyValueIterator", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "value", "weakKey0", "", "putImpl", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/coroutines/debug/internal/HashedWeakRef;)Ljava/lang/Object;", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "rehash", "()Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "removeCleanedAt", "(I)V", "I", "shift", "threshold", "KeyValueIterator", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class Core {
      private static final AtomicIntegerFieldUpdater load$FU = AtomicIntegerFieldUpdater.newUpdater(Core.class, "load");
      private final int allocated;
      AtomicReferenceArray keys;
      private volatile int load;
      private final int shift;
      final ConcurrentWeakMap this$0;
      private final int threshold;
      AtomicReferenceArray values;

      public Core(ConcurrentWeakMap var1, int var2) {
         this.this$0 = var1;
         this.allocated = var2;
         this.shift = Integer.numberOfLeadingZeros(var2) + 1;
         this.threshold = var2 * 2 / 3;
         this.load = 0;
         this.keys = new AtomicReferenceArray(var2);
         this.values = new AtomicReferenceArray(var2);
      }

      private final int index(int var1) {
         return var1 * -1640531527 >>> this.shift;
      }

      // $FF: synthetic method
      public static Object putImpl$default(Core var0, Object var1, Object var2, HashedWeakRef var3, int var4, Object var5) {
         if ((var4 & 4) != 0) {
            var3 = null;
            HashedWeakRef var6 = (HashedWeakRef)null;
         }

         return var0.putImpl(var1, var2, var3);
      }

      private final void removeCleanedAt(int var1) {
         while(true) {
            Object var2 = this.values.get(var1);
            if (var2 != null) {
               if (var2 instanceof Marked) {
                  return;
               }

               if (!ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(this.values, var1, var2, (Object)null)) {
                  continue;
               }

               this.this$0.decrementSize();
            }

            return;
         }
      }

      public final void cleanWeakRef(HashedWeakRef var1) {
         int var2 = this.index(var1.hash);

         while(true) {
            HashedWeakRef var4 = (HashedWeakRef)this.keys.get(var2);
            if (var4 == null) {
               return;
            }

            if (var4 == var1) {
               this.removeCleanedAt(var2);
               return;
            }

            int var3 = var2;
            if (var2 == 0) {
               var3 = this.allocated;
            }

            var2 = var3 - 1;
         }
      }

      public final Object getImpl(Object var1) {
         int var2 = this.index(var1.hashCode());

         while(true) {
            HashedWeakRef var4 = (HashedWeakRef)this.keys.get(var2);
            if (var4 == null) {
               return null;
            }

            Object var5 = var4.get();
            if (Intrinsics.areEqual(var1, var5)) {
               var5 = this.values.get(var2);
               var1 = var5;
               if (var5 instanceof Marked) {
                  var1 = ((Marked)var5).ref;
               }

               return var1;
            }

            if (var5 == null) {
               this.removeCleanedAt(var2);
            }

            int var3 = var2;
            if (var2 == 0) {
               var3 = this.allocated;
            }

            var2 = var3 - 1;
         }
      }

      public final Iterator keyValueIterator(Function2 var1) {
         return (Iterator)(new KeyValueIterator(this, var1));
      }

      public final Object putImpl(Object var1, Object var2, HashedWeakRef var3) {
         int var4 = this.index(var1.hashCode());
         boolean var5 = false;

         while(true) {
            HashedWeakRef var7 = (HashedWeakRef)this.keys.get(var4);
            if (var7 == null) {
               if (var2 == null) {
                  return null;
               }

               boolean var9 = var5;
               if (!var5) {
                  int var8;
                  do {
                     var8 = this.load;
                     if (var8 >= this.threshold) {
                        return ConcurrentWeakMapKt.access$getREHASH$p();
                     }
                  } while(!load$FU.compareAndSet(this, var8, var8 + 1));

                  var9 = true;
               }

               var7 = var3;
               if (var3 == null) {
                  var7 = new HashedWeakRef(var1, this.this$0.weakRefQueue);
               }

               var5 = var9;
               var3 = var7;
               if (ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(this.keys, var4, (Object)null, var7)) {
                  break;
               }
            } else {
               Object var10 = var7.get();
               if (Intrinsics.areEqual(var1, var10)) {
                  if (var5) {
                     load$FU.decrementAndGet(this);
                  }
                  break;
               }

               if (var10 == null) {
                  this.removeCleanedAt(var4);
               }

               int var6 = var4;
               if (var4 == 0) {
                  var6 = this.allocated;
               }

               var4 = var6 - 1;
            }
         }

         do {
            var1 = this.values.get(var4);
            if (var1 instanceof Marked) {
               return ConcurrentWeakMapKt.access$getREHASH$p();
            }
         } while(!ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(this.values, var4, var1, var2));

         return var1;
      }

      public final Core rehash() {
         label40:
         while(true) {
            int var1 = Integer.highestOneBit(RangesKt.coerceAtLeast(this.this$0.size(), 4));
            Core var6 = this.this$0.new Core(this.this$0, var1 * 4);
            var1 = 0;

            for(int var2 = this.allocated; var1 < var2; ++var1) {
               HashedWeakRef var5 = (HashedWeakRef)this.keys.get(var1);
               Object var3;
               if (var5 != null) {
                  var3 = var5.get();
               } else {
                  var3 = null;
               }

               if (var5 != null && var3 == null) {
                  this.removeCleanedAt(var1);
               }

               Object var4;
               do {
                  var4 = this.values.get(var1);
                  if (var4 instanceof Marked) {
                     var4 = ((Marked)var4).ref;
                     break;
                  }
               } while(!ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(this.values, var1, var4, ConcurrentWeakMapKt.access$mark(var4)));

               if (var3 != null && var4 != null && var6.putImpl(var3, var4, var5) == ConcurrentWeakMapKt.access$getREHASH$p()) {
                  continue label40;
               }
            }

            return var6;
         }
      }

      @Metadata(
         bv = {1, 0, 3},
         d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\b\u0082\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\u000e\u0010\u000f\u001a\u00028\u0002H\u0096\u0002¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u00028\u0001X\u0082.¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0013"},
         d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core$KeyValueIterator;", "E", "", "factory", "Lkotlin/Function2;", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;Lkotlin/jvm/functions/Function2;)V", "index", "", "key", "Ljava/lang/Object;", "value", "findNext", "", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "kotlinx-coroutines-core"},
         k = 1,
         mv = {1, 4, 0}
      )
      private final class KeyValueIterator implements Iterator, KMutableIterator {
         private final Function2 factory;
         private int index;
         private Object key;
         final Core this$0;
         private Object value;

         public KeyValueIterator(Core var1, Function2 var2) {
            this.this$0 = var1;
            this.factory = var2;
            this.index = -1;
            this.findNext();
         }

         private final void findNext() {
            while(true) {
               int var1 = this.index + 1;
               this.index = var1;
               if (var1 < this.this$0.allocated) {
                  HashedWeakRef var2 = (HashedWeakRef)this.this$0.keys.get(this.index);
                  if (var2 == null) {
                     continue;
                  }

                  Object var4 = var2.get();
                  if (var4 == null) {
                     continue;
                  }

                  this.key = var4;
                  Object var3 = this.this$0.values.get(this.index);
                  var4 = var3;
                  if (var3 instanceof Marked) {
                     var4 = ((Marked)var3).ref;
                  }

                  if (var4 == null) {
                     continue;
                  }

                  this.value = var4;
               }

               return;
            }
         }

         public boolean hasNext() {
            boolean var1;
            if (this.index < this.this$0.allocated) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public Object next() {
            if (this.index < this.this$0.allocated) {
               Function2 var3 = this.factory;
               Object var1 = this.key;
               if (var1 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("key");
               }

               Object var2 = this.value;
               if (var2 == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("value");
               }

               var1 = var3.invoke(var1, var2);
               this.findNext();
               return var1;
            } else {
               throw (Throwable)(new NoSuchElementException());
            }
         }

         public Void remove() {
            ConcurrentWeakMapKt.access$noImpl();
            throw new KotlinNothingValueException();
         }
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0002\b\u000b\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0003¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000b\u001a\u00028\u00032\u0006\u0010\f\u001a\u00028\u0003H\u0016¢\u0006\u0002\u0010\rR\u0016\u0010\u0004\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0005\u001a\u00028\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u000e"},
      d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Entry;", "K", "V", "", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Entry implements Map.Entry, KMutableMap.Entry {
      private final Object key;
      private final Object value;

      public Entry(Object var1, Object var2) {
         this.key = var1;
         this.value = var2;
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object var1) {
         ConcurrentWeakMapKt.access$noImpl();
         throw new KotlinNothingValueException();
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010)\n\u0000\b\u0082\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\rJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\u000fH\u0096\u0002R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010"},
      d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$KeyValueSet;", "E", "Lkotlin/collections/AbstractMutableSet;", "factory", "Lkotlin/Function2;", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;Lkotlin/jvm/functions/Function2;)V", "size", "", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "iterator", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class KeyValueSet extends AbstractMutableSet {
      private final Function2 factory;
      final ConcurrentWeakMap this$0;

      public KeyValueSet(ConcurrentWeakMap var1, Function2 var2) {
         this.this$0 = var1;
         this.factory = var2;
      }

      public boolean add(Object var1) {
         ConcurrentWeakMapKt.access$noImpl();
         throw new KotlinNothingValueException();
      }

      public int getSize() {
         return this.this$0.size();
      }

      public Iterator iterator() {
         return ((Core)this.this$0.core).keyValueIterator(this.factory);
      }
   }
}
