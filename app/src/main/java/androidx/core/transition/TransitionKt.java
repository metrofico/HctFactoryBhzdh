package androidx.core.transition;

import android.transition.Transition;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u001aÆ\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\r\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u000f\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0010\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0011\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0012\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b¨\u0006\u0013"},
   d2 = {"addListener", "Landroid/transition/Transition$TransitionListener;", "Landroid/transition/Transition;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "transition", "", "onStart", "onCancel", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnResume", "doOnStart", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class TransitionKt {
   public static final Transition.TransitionListener addListener(Transition var0, Function1 var1, Function1 var2, Function1 var3, Function1 var4, Function1 var5) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$addListener");
      Intrinsics.checkParameterIsNotNull(var1, "onEnd");
      Intrinsics.checkParameterIsNotNull(var2, "onStart");
      Intrinsics.checkParameterIsNotNull(var3, "onCancel");
      Intrinsics.checkParameterIsNotNull(var4, "onResume");
      Intrinsics.checkParameterIsNotNull(var5, "onPause");
      Transition.TransitionListener var6 = (Transition.TransitionListener)(new Transition.TransitionListener(var1, var4, var5, var3, var2) {
         final Function1 $onCancel;
         final Function1 $onEnd;
         final Function1 $onPause;
         final Function1 $onResume;
         final Function1 $onStart;

         public {
            this.$onEnd = var1;
            this.$onResume = var2;
            this.$onPause = var3;
            this.$onCancel = var4;
            this.$onStart = var5;
         }

         public void onTransitionCancel(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onCancel.invoke(var1);
         }

         public void onTransitionEnd(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onEnd.invoke(var1);
         }

         public void onTransitionPause(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onPause.invoke(var1);
         }

         public void onTransitionResume(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onResume.invoke(var1);
         }

         public void onTransitionStart(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onStart.invoke(var1);
         }
      });
      var0.addListener(var6);
      return var6;
   }

   // $FF: synthetic method
   public static Transition.TransitionListener addListener$default(Transition var0, Function1 var1, Function1 var2, Function1 var3, Function1 var4, Function1 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = (Function1)null.INSTANCE;
      }

      if ((var6 & 2) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      if ((var6 & 4) != 0) {
         var3 = (Function1)null.INSTANCE;
      }

      if ((var6 & 8) != 0) {
         var4 = (Function1)null.INSTANCE;
      }

      if ((var6 & 16) != 0) {
         var5 = (Function1)null.INSTANCE;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$addListener");
      Intrinsics.checkParameterIsNotNull(var1, "onEnd");
      Intrinsics.checkParameterIsNotNull(var2, "onStart");
      Intrinsics.checkParameterIsNotNull(var3, "onCancel");
      Intrinsics.checkParameterIsNotNull(var4, "onResume");
      Intrinsics.checkParameterIsNotNull(var5, "onPause");
      Transition.TransitionListener var8 = (Transition.TransitionListener)(new Transition.TransitionListener(var1, var4, var5, var3, var2) {
         final Function1 $onCancel;
         final Function1 $onEnd;
         final Function1 $onPause;
         final Function1 $onResume;
         final Function1 $onStart;

         public {
            this.$onEnd = var1;
            this.$onResume = var2;
            this.$onPause = var3;
            this.$onCancel = var4;
            this.$onStart = var5;
         }

         public void onTransitionCancel(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onCancel.invoke(var1);
         }

         public void onTransitionEnd(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onEnd.invoke(var1);
         }

         public void onTransitionPause(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onPause.invoke(var1);
         }

         public void onTransitionResume(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onResume.invoke(var1);
         }

         public void onTransitionStart(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onStart.invoke(var1);
         }
      });
      var0.addListener(var8);
      return var8;
   }

   public static final Transition.TransitionListener doOnCancel(Transition var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnCancel");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Transition.TransitionListener var2 = (Transition.TransitionListener)(new Transition.TransitionListener(var1) {
         final Function1 $onCancel;

         public {
            this.$onCancel = var1;
         }

         public void onTransitionCancel(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onCancel.invoke(var1);
         }

         public void onTransitionEnd(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionPause(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionResume(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionStart(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }
      });
      var0.addListener(var2);
      return var2;
   }

   public static final Transition.TransitionListener doOnEnd(Transition var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnEnd");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Transition.TransitionListener var2 = (Transition.TransitionListener)(new Transition.TransitionListener(var1) {
         final Function1 $onEnd;

         public {
            this.$onEnd = var1;
         }

         public void onTransitionCancel(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionEnd(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onEnd.invoke(var1);
         }

         public void onTransitionPause(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionResume(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionStart(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }
      });
      var0.addListener(var2);
      return var2;
   }

   public static final Transition.TransitionListener doOnPause(Transition var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnPause");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Transition.TransitionListener var2 = (Transition.TransitionListener)(new Transition.TransitionListener(var1) {
         final Function1 $onPause;

         public {
            this.$onPause = var1;
         }

         public void onTransitionCancel(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionEnd(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionPause(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onPause.invoke(var1);
         }

         public void onTransitionResume(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionStart(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }
      });
      var0.addListener(var2);
      return var2;
   }

   public static final Transition.TransitionListener doOnResume(Transition var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnResume");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Transition.TransitionListener var2 = (Transition.TransitionListener)(new Transition.TransitionListener(var1) {
         final Function1 $onResume;

         public {
            this.$onResume = var1;
         }

         public void onTransitionCancel(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionEnd(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionPause(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionResume(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onResume.invoke(var1);
         }

         public void onTransitionStart(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }
      });
      var0.addListener(var2);
      return var2;
   }

   public static final Transition.TransitionListener doOnStart(Transition var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnStart");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Transition.TransitionListener var2 = (Transition.TransitionListener)(new Transition.TransitionListener(var1) {
         final Function1 $onStart;

         public {
            this.$onStart = var1;
         }

         public void onTransitionCancel(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionEnd(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionPause(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionResume(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
         }

         public void onTransitionStart(Transition var1) {
            Intrinsics.checkParameterIsNotNull(var1, "transition");
            this.$onStart.invoke(var1);
         }
      });
      var0.addListener(var2);
      return var2;
   }
}
