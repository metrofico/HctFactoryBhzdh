package kotlinx.coroutines.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0002\u0018\u0002\b\u0017\u0018\u0000*\u0012\b\u0000\u0010\u0003*\u00020\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\u000602j\u0002`3B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00028\u0000H\u0001¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\tJ.\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00028\u00002\u0014\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\f0\u000bH\u0086\b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007¢\u0006\u0004\b\u0010\u0010\u0005J\u0011\u0010\u0011\u001a\u0004\u0018\u00018\u0000H\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0019H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ&\u0010\u001e\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u000bH\u0086\b¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b \u0010\u0012J\u0018\u0010\"\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0019H\u0082\u0010¢\u0006\u0004\b\"\u0010#J\u0018\u0010$\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0019H\u0082\u0010¢\u0006\u0004\b$\u0010#J\u001f\u0010&\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u0019H\u0002¢\u0006\u0004\b&\u0010'R \u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0013\u0010*\u001a\u00020\f8F@\u0006¢\u0006\u0006\u001a\u0004\b*\u0010+R$\u00100\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u00198F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u0010#¨\u00061"},
   d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "T", "<init>", "()V", "node", "", "addImpl", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "addLast", "Lkotlin/Function1;", "", "cond", "addLastIf", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function1;)Z", "clear", "firstImpl", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "peek", "", "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "", "index", "removeAtImpl", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "predicate", "removeFirstIf", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "i", "siftDownFrom", "(I)V", "siftUpFrom", "j", "swap", "(II)V", "a", "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "isEmpty", "()Z", "value", "getSize", "()I", "setSize", "size", "kotlinx-coroutines-core", "", "Lkotlinx/coroutines/internal/SynchronizedObject;"},
   k = 1,
   mv = {1, 4, 0}
)
public class ThreadSafeHeap {
   private volatile int _size = 0;
   private ThreadSafeHeapNode[] a;

   private final ThreadSafeHeapNode[] realloc() {
      ThreadSafeHeapNode[] var2 = this.a;
      ThreadSafeHeapNode[] var1;
      if (var2 == null) {
         var1 = new ThreadSafeHeapNode[4];
         this.a = var1;
      } else {
         var1 = var2;
         if (this.getSize() >= var2.length) {
            Object[] var3 = Arrays.copyOf(var2, this.getSize() * 2);
            Intrinsics.checkNotNullExpressionValue(var3, "java.util.Arrays.copyOf(this, newSize)");
            var1 = (ThreadSafeHeapNode[])var3;
            this.a = var1;
         }
      }

      return var1;
   }

   private final void setSize(int var1) {
      this._size = var1;
   }

   private final void siftDownFrom(int var1) {
      int var2 = var1;

      while(true) {
         int var3 = var2 * 2 + 1;
         if (var3 >= this.getSize()) {
            return;
         }

         ThreadSafeHeapNode[] var5 = this.a;
         Intrinsics.checkNotNull(var5);
         int var4 = var3 + 1;
         var1 = var3;
         ThreadSafeHeapNode var6;
         Comparable var9;
         if (var4 < this.getSize()) {
            var6 = var5[var4];
            Intrinsics.checkNotNull(var6);
            var9 = (Comparable)var6;
            ThreadSafeHeapNode var7 = var5[var3];
            Intrinsics.checkNotNull(var7);
            var1 = var3;
            if (var9.compareTo(var7) < 0) {
               var1 = var4;
            }
         }

         var6 = var5[var2];
         Intrinsics.checkNotNull(var6);
         var9 = (Comparable)var6;
         ThreadSafeHeapNode var8 = var5[var1];
         Intrinsics.checkNotNull(var8);
         if (var9.compareTo(var8) <= 0) {
            return;
         }

         this.swap(var2, var1);
         var2 = var1;
      }
   }

   private final void siftUpFrom(int var1) {
      while(var1 > 0) {
         ThreadSafeHeapNode[] var4 = this.a;
         Intrinsics.checkNotNull(var4);
         int var2 = (var1 - 1) / 2;
         ThreadSafeHeapNode var3 = var4[var2];
         Intrinsics.checkNotNull(var3);
         Comparable var5 = (Comparable)var3;
         ThreadSafeHeapNode var6 = var4[var1];
         Intrinsics.checkNotNull(var6);
         if (var5.compareTo(var6) <= 0) {
            return;
         }

         this.swap(var1, var2);
         var1 = var2;
      }

   }

   private final void swap(int var1, int var2) {
      ThreadSafeHeapNode[] var3 = this.a;
      Intrinsics.checkNotNull(var3);
      ThreadSafeHeapNode var4 = var3[var2];
      Intrinsics.checkNotNull(var4);
      ThreadSafeHeapNode var5 = var3[var1];
      Intrinsics.checkNotNull(var5);
      var3[var1] = var4;
      var3[var2] = var5;
      var4.setIndex(var1);
      var5.setIndex(var2);
   }

   public final void addImpl(ThreadSafeHeapNode var1) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var2;
         if (var1.getHeap() == null) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      ThreadSafeHeap var3 = (ThreadSafeHeap)this;
      var1.setHeap(this);
      ThreadSafeHeapNode[] var5 = this.realloc();
      int var4 = this.getSize();
      this.setSize(var4 + 1);
      var5[var4] = var1;
      var1.setIndex(var4);
      this.siftUpFrom(var4);
   }

   public final void addLast(ThreadSafeHeapNode var1) {
      synchronized(this){}

      try {
         this.addImpl(var1);
         Unit var4 = Unit.INSTANCE;
      } finally {
         ;
      }

   }

   public final boolean addLastIf(ThreadSafeHeapNode var1, Function1 var2) {
      synchronized(this){}
      boolean var5 = false;

      boolean var3;
      label42: {
         label41: {
            try {
               var5 = true;
               if ((Boolean)var2.invoke(this.firstImpl())) {
                  this.addImpl(var1);
                  var5 = false;
                  break label41;
               }

               var5 = false;
            } finally {
               if (var5) {
                  InlineMarker.finallyStart(1);
                  InlineMarker.finallyEnd(1);
               }
            }

            var3 = false;
            break label42;
         }

         var3 = true;
      }

      InlineMarker.finallyStart(1);
      InlineMarker.finallyEnd(1);
      return var3;
   }

   public final void clear() {
      synchronized(this){}

      Throwable var10000;
      label116: {
         ThreadSafeHeapNode[] var1;
         boolean var10001;
         try {
            var1 = this.a;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label116;
         }

         if (var1 != null) {
            try {
               ArraysKt.fill$default(var1, (Object)null, 0, 0, 6, (Object)null);
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label116;
            }
         }

         label104:
         try {
            this._size = 0;
            Unit var15 = Unit.INSTANCE;
            return;
         } catch (Throwable var11) {
            var10000 = var11;
            var10001 = false;
            break label104;
         }
      }

      Throwable var14 = var10000;
      throw var14;
   }

   public final ThreadSafeHeapNode firstImpl() {
      ThreadSafeHeapNode[] var1 = this.a;
      ThreadSafeHeapNode var2;
      if (var1 != null) {
         var2 = var1[0];
      } else {
         var2 = null;
      }

      return var2;
   }

   public final int getSize() {
      return this._size;
   }

   public final boolean isEmpty() {
      boolean var1;
      if (this.getSize() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final ThreadSafeHeapNode peek() {
      synchronized(this){}

      ThreadSafeHeapNode var1;
      try {
         var1 = this.firstImpl();
      } finally {
         ;
      }

      return var1;
   }

   public final boolean remove(ThreadSafeHeapNode var1) {
      synchronized(this){}

      Throwable var10000;
      label228: {
         ThreadSafeHeap var5;
         boolean var10001;
         try {
            var5 = var1.getHeap();
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label228;
         }

         boolean var4 = true;
         boolean var2 = false;
         if (var5 == null) {
            var4 = false;
         } else {
            int var3;
            label226: {
               try {
                  var3 = var1.getIndex();
                  if (!DebugKt.getASSERTIONS_ENABLED()) {
                     break label226;
                  }
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label228;
               }

               if (var3 >= 0) {
                  var2 = true;
               }

               if (!var2) {
                  try {
                     AssertionError var26 = new AssertionError();
                     throw (Throwable)var26;
                  } catch (Throwable var22) {
                     var10000 = var22;
                     var10001 = false;
                     break label228;
                  }
               }
            }

            try {
               this.removeAtImpl(var3);
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label228;
            }
         }

         return var4;
      }

      Throwable var27 = var10000;
      throw var27;
   }

   public final ThreadSafeHeapNode removeAtImpl(int var1) {
      boolean var4 = DebugKt.getASSERTIONS_ENABLED();
      boolean var3 = false;
      if (var4) {
         boolean var2;
         if (this.getSize() > 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      ThreadSafeHeapNode[] var5 = this.a;
      Intrinsics.checkNotNull(var5);
      this.setSize(this.getSize() - 1);
      ThreadSafeHeapNode var6;
      if (var1 < this.getSize()) {
         label35: {
            this.swap(var1, this.getSize());
            int var10 = (var1 - 1) / 2;
            if (var1 > 0) {
               var6 = var5[var1];
               Intrinsics.checkNotNull(var6);
               Comparable var7 = (Comparable)var6;
               var6 = var5[var10];
               Intrinsics.checkNotNull(var6);
               if (var7.compareTo(var6) < 0) {
                  this.swap(var1, var10);
                  this.siftUpFrom(var10);
                  break label35;
               }
            }

            this.siftDownFrom(var1);
         }
      }

      var6 = var5[this.getSize()];
      Intrinsics.checkNotNull(var6);
      ThreadSafeHeap var11;
      if (DebugKt.getASSERTIONS_ENABLED()) {
         ThreadSafeHeap var8 = var6.getHeap();
         var11 = (ThreadSafeHeap)this;
         boolean var9 = var3;
         if (var8 == this) {
            var9 = true;
         }

         if (!var9) {
            throw (Throwable)(new AssertionError());
         }
      }

      var11 = (ThreadSafeHeap)null;
      var6.setHeap((ThreadSafeHeap)null);
      var6.setIndex(-1);
      var1 = this.getSize();
      ThreadSafeHeapNode var12 = (ThreadSafeHeapNode)null;
      var5[var1] = null;
      return var6;
   }

   public final ThreadSafeHeapNode removeFirstIf(Function1 var1) {
      synchronized(this){}

      ThreadSafeHeapNode var2;
      label86: {
         Throwable var10000;
         label90: {
            boolean var10001;
            ThreadSafeHeapNode var3;
            try {
               var3 = this.firstImpl();
            } catch (Throwable var9) {
               var10000 = var9;
               var10001 = false;
               break label90;
            }

            var2 = null;
            if (var3 == null) {
               InlineMarker.finallyStart(2);
               InlineMarker.finallyEnd(2);
               return null;
            }

            label81:
            try {
               if ((Boolean)var1.invoke(var3)) {
                  var2 = this.removeAtImpl(0);
               }
               break label86;
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label81;
            }
         }

         Throwable var10 = var10000;
         InlineMarker.finallyStart(1);
         InlineMarker.finallyEnd(1);
         throw var10;
      }

      InlineMarker.finallyStart(1);
      InlineMarker.finallyEnd(1);
      return var2;
   }

   public final ThreadSafeHeapNode removeFirstOrNull() {
      synchronized(this){}
      boolean var3 = false;

      ThreadSafeHeapNode var1;
      try {
         var3 = true;
         if (this.getSize() > 0) {
            var1 = this.removeAtImpl(0);
            var3 = false;
            return var1;
         }

         var3 = false;
      } finally {
         if (var3) {
            ;
         }
      }

      var1 = null;
      return var1;
   }
}
