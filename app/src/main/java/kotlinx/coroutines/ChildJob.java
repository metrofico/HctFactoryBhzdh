package kotlinx.coroutines;

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
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006"},
   d2 = {"Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/Job;", "parentCancelled", "", "parentJob", "Lkotlinx/coroutines/ParentJob;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface ChildJob extends Job {
   void parentCancelled(ParentJob var1);

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
      public static void cancel(ChildJob var0) {
         Job.DefaultImpls.cancel((Job)var0);
      }

      public static Object fold(ChildJob var0, Object var1, Function2 var2) {
         return Job.DefaultImpls.fold((Job)var0, var1, var2);
      }

      public static Element get(ChildJob var0, CoroutineContext.Key var1) {
         return Job.DefaultImpls.get((Job)var0, var1);
      }

      public static CoroutineContext minusKey(ChildJob var0, CoroutineContext.Key var1) {
         return Job.DefaultImpls.minusKey((Job)var0, var1);
      }

      public static CoroutineContext plus(ChildJob var0, CoroutineContext var1) {
         return Job.DefaultImpls.plus((Job)var0, var1);
      }

      @Deprecated(
         level = DeprecationLevel.ERROR,
         message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`."
      )
      public static Job plus(ChildJob var0, Job var1) {
         return Job.DefaultImpls.plus((Job)var0, var1);
      }
   }
}
