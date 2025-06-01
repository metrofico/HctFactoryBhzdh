package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BZ\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012%\b\u0002\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003J&\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J`\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042%\b\u0002\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u001a\u0010\u001d\u001a\u00020\u000b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010\n\u001a\u00020\u0007J\t\u0010 \u001a\u00020!HÖ\u0001R\u0012\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\f\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\""},
   d2 = {"Lkotlinx/coroutines/CompletedContinuation;", "", "result", "cancelHandler", "Lkotlinx/coroutines/CancelHandler;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "idempotentResume", "cancelCause", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelled", "", "getCancelled", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "invokeHandlers", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class CompletedContinuation {
   public final Throwable cancelCause;
   public final CancelHandler cancelHandler;
   public final Object idempotentResume;
   public final Function1 onCancellation;
   public final Object result;

   public CompletedContinuation(Object var1, CancelHandler var2, Function1 var3, Object var4, Throwable var5) {
      this.result = var1;
      this.cancelHandler = var2;
      this.onCancellation = var3;
      this.idempotentResume = var4;
      this.cancelCause = var5;
   }

   // $FF: synthetic method
   public CompletedContinuation(Object var1, CancelHandler var2, Function1 var3, Object var4, Throwable var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 2) != 0) {
         var2 = (CancelHandler)null;
         var2 = null;
      }

      if ((var6 & 4) != 0) {
         var3 = (Function1)null;
         var3 = null;
      }

      if ((var6 & 8) != 0) {
         var4 = null;
      }

      if ((var6 & 16) != 0) {
         var5 = (Throwable)null;
         var5 = null;
      }

      this(var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static CompletedContinuation copy$default(CompletedContinuation var0, Object var1, CancelHandler var2, Function1 var3, Object var4, Throwable var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = var0.result;
      }

      if ((var6 & 2) != 0) {
         var2 = var0.cancelHandler;
      }

      if ((var6 & 4) != 0) {
         var3 = var0.onCancellation;
      }

      if ((var6 & 8) != 0) {
         var4 = var0.idempotentResume;
      }

      if ((var6 & 16) != 0) {
         var5 = var0.cancelCause;
      }

      return var0.copy(var1, var2, var3, var4, var5);
   }

   public final Object component1() {
      return this.result;
   }

   public final CancelHandler component2() {
      return this.cancelHandler;
   }

   public final Function1 component3() {
      return this.onCancellation;
   }

   public final Object component4() {
      return this.idempotentResume;
   }

   public final Throwable component5() {
      return this.cancelCause;
   }

   public final CompletedContinuation copy(Object var1, CancelHandler var2, Function1 var3, Object var4, Throwable var5) {
      return new CompletedContinuation(var1, var2, var3, var4, var5);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (!(var1 instanceof CompletedContinuation)) {
            return false;
         }

         CompletedContinuation var2 = (CompletedContinuation)var1;
         if (!Intrinsics.areEqual(this.result, var2.result) || !Intrinsics.areEqual((Object)this.cancelHandler, (Object)var2.cancelHandler) || !Intrinsics.areEqual((Object)this.onCancellation, (Object)var2.onCancellation) || !Intrinsics.areEqual(this.idempotentResume, var2.idempotentResume) || !Intrinsics.areEqual((Object)this.cancelCause, (Object)var2.cancelCause)) {
            return false;
         }
      }

      return true;
   }

   public final boolean getCancelled() {
      boolean var1;
      if (this.cancelCause != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public int hashCode() {
      Object var6 = this.result;
      int var5 = 0;
      int var1;
      if (var6 != null) {
         var1 = var6.hashCode();
      } else {
         var1 = 0;
      }

      CancelHandler var7 = this.cancelHandler;
      int var2;
      if (var7 != null) {
         var2 = var7.hashCode();
      } else {
         var2 = 0;
      }

      Function1 var8 = this.onCancellation;
      int var3;
      if (var8 != null) {
         var3 = var8.hashCode();
      } else {
         var3 = 0;
      }

      var6 = this.idempotentResume;
      int var4;
      if (var6 != null) {
         var4 = var6.hashCode();
      } else {
         var4 = 0;
      }

      Throwable var9 = this.cancelCause;
      if (var9 != null) {
         var5 = var9.hashCode();
      }

      return (((var1 * 31 + var2) * 31 + var3) * 31 + var4) * 31 + var5;
   }

   public final void invokeHandlers(CancellableContinuationImpl var1, Throwable var2) {
      CancelHandler var3 = this.cancelHandler;
      if (var3 != null) {
         var1.callCancelHandler(var3, var2);
      }

      Function1 var4 = this.onCancellation;
      if (var4 != null) {
         var1.callOnCancellation(var4, var2);
      }

   }

   public String toString() {
      return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=" + this.idempotentResume + ", cancelCause=" + this.cancelCause + ")";
   }
}
