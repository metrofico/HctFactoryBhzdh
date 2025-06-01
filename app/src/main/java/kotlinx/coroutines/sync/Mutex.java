package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0001H&J\u001d\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001H¦@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001H&J\u0014\u0010\u000f\u001a\u00020\f2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R \u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00000\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"},
   d2 = {"Lkotlinx/coroutines/sync/Mutex;", "", "isLocked", "", "()Z", "onLock", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "holdsLock", "owner", "lock", "", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "unlock", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface Mutex {
   SelectClause2 getOnLock();

   boolean holdsLock(Object var1);

   boolean isLocked();

   Object lock(Object var1, Continuation var2);

   boolean tryLock(Object var1);

   void unlock(Object var1);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      public static Object lock$default(Mutex var0, Object var1, Continuation var2, int var3, Object var4) {
         if (var4 == null) {
            if ((var3 & 1) != 0) {
               var1 = null;
            }

            return var0.lock(var1, var2);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lock");
         }
      }

      // $FF: synthetic method
      public static boolean tryLock$default(Mutex var0, Object var1, int var2, Object var3) {
         if (var3 == null) {
            if ((var2 & 1) != 0) {
               var1 = null;
            }

            return var0.tryLock(var1);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryLock");
         }
      }

      // $FF: synthetic method
      public static void unlock$default(Mutex var0, Object var1, int var2, Object var3) {
         if (var3 == null) {
            if ((var2 & 1) != 0) {
               var1 = null;
            }

            var0.unlock(var1);
         } else {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: unlock");
         }
      }
   }
}
