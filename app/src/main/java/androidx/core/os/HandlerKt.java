package androidx.core.os;

import android.os.Handler;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b\u001a1\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b¨\u0006\f"},
   d2 = {"postAtTime", "Ljava/lang/Runnable;", "Landroid/os/Handler;", "uptimeMillis", "", "token", "", "action", "Lkotlin/Function0;", "", "postDelayed", "delayInMillis", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class HandlerKt {
   public static final Runnable postAtTime(Handler var0, long var1, Object var3, Function0 var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$postAtTime");
      Intrinsics.checkParameterIsNotNull(var4, "action");
      Runnable var5 = (Runnable)(new Runnable(var4) {
         final Function0 $action;

         public {
            this.$action = var1;
         }

         public final void run() {
            this.$action.invoke();
         }
      });
      var0.postAtTime(var5, var3, var1);
      return var5;
   }

   // $FF: synthetic method
   public static Runnable postAtTime$default(Handler var0, long var1, Object var3, Function0 var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var3 = null;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$postAtTime");
      Intrinsics.checkParameterIsNotNull(var4, "action");
      Runnable var7 = (Runnable)(new Runnable(var4) {
         final Function0 $action;

         public {
            this.$action = var1;
         }

         public final void run() {
            this.$action.invoke();
         }
      });
      var0.postAtTime(var7, var3, var1);
      return var7;
   }

   public static final Runnable postDelayed(Handler var0, long var1, Object var3, Function0 var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$postDelayed");
      Intrinsics.checkParameterIsNotNull(var4, "action");
      Runnable var5 = (Runnable)(new Runnable(var4) {
         final Function0 $action;

         public {
            this.$action = var1;
         }

         public final void run() {
            this.$action.invoke();
         }
      });
      if (var3 == null) {
         var0.postDelayed(var5, var1);
      } else {
         HandlerCompat.postDelayed(var0, var5, var3, var1);
      }

      return var5;
   }

   // $FF: synthetic method
   public static Runnable postDelayed$default(Handler var0, long var1, Object var3, Function0 var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var3 = null;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$postDelayed");
      Intrinsics.checkParameterIsNotNull(var4, "action");
      Runnable var7 = (Runnable)(new Runnable(var4) {
         final Function0 $action;

         public {
            this.$action = var1;
         }

         public final void run() {
            this.$action.invoke();
         }
      });
      if (var3 == null) {
         var0.postDelayed(var7, var1);
      } else {
         HandlerCompat.postDelayed(var0, var7, var3, var1);
      }

      return var7;
   }
}
