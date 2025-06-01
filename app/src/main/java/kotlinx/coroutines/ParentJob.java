package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Deprecated(
   level = DeprecationLevel.ERROR,
   message = "This is internal API and may be removed in the future releases"
)
@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\f\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H'¨\u0006\u0005"},
   d2 = {"Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/Job;", "getChildJobCancellationCause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface ParentJob extends Job {
   CancellationException getChildJobCancellationCause();

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      @Deprecated(
         level = DeprecationLevel.HIDDEN,
         message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
      )
      public static void cancel(ParentJob var0) {
         Job.DefaultImpls.cancel((Job)var0);
      }

      public static Object fold(ParentJob var0, Object var1, Function2 var2) {
         return Job.DefaultImpls.fold((Job)var0, var1, var2);
      }

      public static Element get(ParentJob var0, CoroutineContext.Key var1) {
         return Job.DefaultImpls.get((Job)var0, var1);
      }

      public static CoroutineContext minusKey(ParentJob var0, CoroutineContext.Key var1) {
         return Job.DefaultImpls.minusKey((Job)var0, var1);
      }

      public static CoroutineContext plus(ParentJob var0, CoroutineContext var1) {
         return Job.DefaultImpls.plus((Job)var0, var1);
      }

      @Deprecated(
         level = DeprecationLevel.ERROR,
         message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`."
      )
      public static Job plus(ParentJob var0, Job var1) {
         return Job.DefaultImpls.plus((Job)var0, var1);
      }
   }
}
