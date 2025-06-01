package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\n\u0010\u000b\u001a\u0004\u0018\u00010\u0000H\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0010\u0010\b\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"},
   d2 = {"Lkotlinx/coroutines/JobCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lkotlinx/coroutines/CopyableThrowable;", "message", "", "cause", "", "job", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Ljava/lang/Throwable;Lkotlinx/coroutines/Job;)V", "createCopy", "equals", "", "other", "", "fillInStackTrace", "hashCode", "", "toString", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class JobCancellationException extends CancellationException implements CopyableThrowable {
   public final Job job;

   public JobCancellationException(String var1, Throwable var2, Job var3) {
      super(var1);
      this.job = var3;
      if (var2 != null) {
         this.initCause(var2);
      }

   }

   public JobCancellationException createCopy() {
      if (DebugKt.getDEBUG()) {
         String var1 = this.getMessage();
         Intrinsics.checkNotNull(var1);
         return new JobCancellationException(var1, (Throwable)this, this.job);
      } else {
         return null;
      }
   }

   public boolean equals(Object var1) {
      boolean var2;
      label31: {
         JobCancellationException var3 = (JobCancellationException)this;
         if (var1 != this) {
            if (!(var1 instanceof JobCancellationException)) {
               break label31;
            }

            JobCancellationException var4 = (JobCancellationException)var1;
            if (!Intrinsics.areEqual((Object)var4.getMessage(), (Object)this.getMessage()) || !Intrinsics.areEqual((Object)var4.job, (Object)this.job) || !Intrinsics.areEqual((Object)var4.getCause(), (Object)this.getCause())) {
               break label31;
            }
         }

         var2 = true;
         return var2;
      }

      var2 = false;
      return var2;
   }

   public Throwable fillInStackTrace() {
      if (DebugKt.getDEBUG()) {
         return super.fillInStackTrace();
      } else {
         this.setStackTrace(new StackTraceElement[0]);
         return (Throwable)this;
      }
   }

   public int hashCode() {
      String var4 = this.getMessage();
      Intrinsics.checkNotNull(var4);
      int var2 = var4.hashCode();
      int var3 = this.job.hashCode();
      Throwable var5 = this.getCause();
      int var1;
      if (var5 != null) {
         var1 = var5.hashCode();
      } else {
         var1 = 0;
      }

      return (var2 * 31 + var3) * 31 + var1;
   }

   public String toString() {
      return super.toString() + "; job=" + this.job;
   }
}
