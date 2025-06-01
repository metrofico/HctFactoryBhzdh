package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B2\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012!\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\u0002\u0010\nJ\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÆ\u0003J$\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004HÆ\u0003J:\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012#\b\u0002\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R+\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"Lkotlinx/coroutines/CompletedWithCancellation;", "", "result", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class CompletedWithCancellation {
   public final Function1 onCancellation;
   public final Object result;

   public CompletedWithCancellation(Object var1, Function1 var2) {
      this.result = var1;
      this.onCancellation = var2;
   }

   // $FF: synthetic method
   public static CompletedWithCancellation copy$default(CompletedWithCancellation var0, Object var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.result;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.onCancellation;
      }

      return var0.copy(var1, var2);
   }

   public final Object component1() {
      return this.result;
   }

   public final Function1 component2() {
      return this.onCancellation;
   }

   public final CompletedWithCancellation copy(Object var1, Function1 var2) {
      return new CompletedWithCancellation(var1, var2);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (!(var1 instanceof CompletedWithCancellation)) {
            return false;
         }

         CompletedWithCancellation var2 = (CompletedWithCancellation)var1;
         if (!Intrinsics.areEqual(this.result, var2.result) || !Intrinsics.areEqual((Object)this.onCancellation, (Object)var2.onCancellation)) {
            return false;
         }
      }

      return true;
   }

   public int hashCode() {
      Object var3 = this.result;
      int var2 = 0;
      int var1;
      if (var3 != null) {
         var1 = var3.hashCode();
      } else {
         var1 = 0;
      }

      Function1 var4 = this.onCancellation;
      if (var4 != null) {
         var2 = var4.hashCode();
      }

      return var1 * 31 + var2;
   }

   public String toString() {
      return "CompletedWithCancellation(result=" + this.result + ", onCancellation=" + this.onCancellation + ")";
   }
}
