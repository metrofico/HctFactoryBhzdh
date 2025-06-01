package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00060\u0002j\u0002`\u00032\u00020\u0004B%\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0011\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0000H\u0096\u0002J\b\u0010\u0005\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"},
   d2 = {"Lkotlinx/coroutines/test/TimedRunnableObsolete;", "", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "run", "count", "", "time", "(Ljava/lang/Runnable;JJ)V", "heap", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class TimedRunnableObsolete implements Comparable, Runnable, ThreadSafeHeapNode {
   private final long count;
   private ThreadSafeHeap heap;
   private int index;
   private final Runnable run;
   public final long time;

   public TimedRunnableObsolete(Runnable var1, long var2, long var4) {
      this.run = var1;
      this.count = var2;
      this.time = var4;
   }

   // $FF: synthetic method
   public TimedRunnableObsolete(Runnable var1, long var2, long var4, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 2) != 0) {
         var2 = 0L;
      }

      if ((var6 & 4) != 0) {
         var4 = 0L;
      }

      this(var1, var2, var4);
   }

   public int compareTo(TimedRunnableObsolete var1) {
      long var8 = this.time;
      long var6 = var1.time;
      long var4 = var8;
      long var2 = var6;
      if (var8 == var6) {
         var4 = this.count;
         var2 = var1.count;
      }

      long var10;
      return (var10 = var4 - var2) == 0L ? 0 : (var10 < 0L ? -1 : 1);
   }

   public ThreadSafeHeap getHeap() {
      return this.heap;
   }

   public int getIndex() {
      return this.index;
   }

   public void run() {
      this.run.run();
   }

   public void setHeap(ThreadSafeHeap var1) {
      this.heap = var1;
   }

   public void setIndex(int var1) {
      this.index = var1;
   }

   public String toString() {
      return "TimedRunnable(time=" + this.time + ", run=" + this.run + ')';
   }
}
