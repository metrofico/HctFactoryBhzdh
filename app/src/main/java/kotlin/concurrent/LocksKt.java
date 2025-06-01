package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u001a6\u0010\u0006\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\b\u001a6\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"},
   d2 = {"read", "T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class LocksKt {
   private static final Object read(ReentrantReadWriteLock var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      ReentrantReadWriteLock.ReadLock var4 = var0.readLock();
      var4.lock();

      Object var5;
      try {
         var5 = var1.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var4.unlock();
         InlineMarker.finallyEnd(1);
      }

      return var5;
   }

   private static final Object withLock(Lock var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      var0.lock();

      Object var4;
      try {
         var4 = var1.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var0.unlock();
         InlineMarker.finallyEnd(1);
      }

      return var4;
   }

   private static final Object write(ReentrantReadWriteLock var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      ReentrantReadWriteLock.ReadLock var6 = var0.readLock();
      int var2 = var0.getWriteHoldCount();
      byte var5 = 0;
      byte var4 = 0;
      if (var2 == 0) {
         var2 = var0.getReadHoldCount();
      } else {
         var2 = 0;
      }

      int var3;
      for(var3 = 0; var3 < var2; ++var3) {
         var6.unlock();
      }

      ReentrantReadWriteLock.WriteLock var10 = var0.writeLock();
      var10.lock();
      boolean var8 = false;

      Object var11;
      try {
         var8 = true;
         var11 = var1.invoke();
         var8 = false;
      } finally {
         if (var8) {
            InlineMarker.finallyStart(1);

            for(var3 = var5; var3 < var2; ++var3) {
               var6.lock();
            }

            var10.unlock();
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);

      for(var3 = var4; var3 < var2; ++var3) {
         var6.lock();
      }

      var10.unlock();
      InlineMarker.finallyEnd(1);
      return var11;
   }
}
