package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005¨\u0006\u0006"},
   d2 = {"Lkotlinx/coroutines/JobCancellingNode;", "J", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/JobNode;", "job", "(Lkotlinx/coroutines/Job;)V", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class JobCancellingNode extends JobNode {
   public JobCancellingNode(Job var1) {
      super(var1);
   }
}
