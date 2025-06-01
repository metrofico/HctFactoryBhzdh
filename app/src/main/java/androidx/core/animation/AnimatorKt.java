package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u001a¡\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001aW\u0010\f\u001a\u00020\r*\u00020\u00022#\b\u0006\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0010\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001a2\u0010\u0012\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001a2\u0010\u0013\u001a\u00020\r*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0014\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001a2\u0010\u0015\u001a\u00020\r*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0016\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b¨\u0006\u0017"},
   d2 = {"addListener", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "animator", "", "onStart", "onCancel", "onRepeat", "addPauseListener", "Landroid/animation/Animator$AnimatorPauseListener;", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnRepeat", "doOnResume", "doOnStart", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class AnimatorKt {
   public static final Animator.AnimatorListener addListener(Animator var0, Function1 var1, Function1 var2, Function1 var3, Function1 var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$addListener");
      Intrinsics.checkParameterIsNotNull(var1, "onEnd");
      Intrinsics.checkParameterIsNotNull(var2, "onStart");
      Intrinsics.checkParameterIsNotNull(var3, "onCancel");
      Intrinsics.checkParameterIsNotNull(var4, "onRepeat");
      Animator.AnimatorListener var5 = (Animator.AnimatorListener)(new Animator.AnimatorListener(var4, var1, var3, var2) {
         final Function1 $onCancel;
         final Function1 $onEnd;
         final Function1 $onRepeat;
         final Function1 $onStart;

         public {
            this.$onRepeat = var1;
            this.$onEnd = var2;
            this.$onCancel = var3;
            this.$onStart = var4;
         }

         public void onAnimationCancel(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onCancel.invoke(var1);
         }

         public void onAnimationEnd(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onEnd.invoke(var1);
         }

         public void onAnimationRepeat(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onRepeat.invoke(var1);
         }

         public void onAnimationStart(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onStart.invoke(var1);
         }
      });
      var0.addListener(var5);
      return var5;
   }

   // $FF: synthetic method
   public static Animator.AnimatorListener addListener$default(Animator var0, Function1 var1, Function1 var2, Function1 var3, Function1 var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = (Function1)null.INSTANCE;
      }

      if ((var5 & 2) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      if ((var5 & 4) != 0) {
         var3 = (Function1)null.INSTANCE;
      }

      if ((var5 & 8) != 0) {
         var4 = (Function1)null.INSTANCE;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$addListener");
      Intrinsics.checkParameterIsNotNull(var1, "onEnd");
      Intrinsics.checkParameterIsNotNull(var2, "onStart");
      Intrinsics.checkParameterIsNotNull(var3, "onCancel");
      Intrinsics.checkParameterIsNotNull(var4, "onRepeat");
      Animator.AnimatorListener var7 = (Animator.AnimatorListener)(new Animator.AnimatorListener(var4, var1, var3, var2) {
         final Function1 $onCancel;
         final Function1 $onEnd;
         final Function1 $onRepeat;
         final Function1 $onStart;

         public {
            this.$onRepeat = var1;
            this.$onEnd = var2;
            this.$onCancel = var3;
            this.$onStart = var4;
         }

         public void onAnimationCancel(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onCancel.invoke(var1);
         }

         public void onAnimationEnd(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onEnd.invoke(var1);
         }

         public void onAnimationRepeat(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onRepeat.invoke(var1);
         }

         public void onAnimationStart(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onStart.invoke(var1);
         }
      });
      var0.addListener(var7);
      return var7;
   }

   public static final Animator.AnimatorPauseListener addPauseListener(Animator var0, Function1 var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$addPauseListener");
      Intrinsics.checkParameterIsNotNull(var1, "onResume");
      Intrinsics.checkParameterIsNotNull(var2, "onPause");
      Animator.AnimatorPauseListener var3 = (Animator.AnimatorPauseListener)(new Animator.AnimatorPauseListener(var2, var1) {
         final Function1 $onPause;
         final Function1 $onResume;

         public {
            this.$onPause = var1;
            this.$onResume = var2;
         }

         public void onAnimationPause(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onPause.invoke(var1);
         }

         public void onAnimationResume(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onResume.invoke(var1);
         }
      });
      var0.addPauseListener(var3);
      return var3;
   }

   // $FF: synthetic method
   public static Animator.AnimatorPauseListener addPauseListener$default(Animator var0, Function1 var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (Function1)null.INSTANCE;
      }

      if ((var3 & 2) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$addPauseListener");
      Intrinsics.checkParameterIsNotNull(var1, "onResume");
      Intrinsics.checkParameterIsNotNull(var2, "onPause");
      Animator.AnimatorPauseListener var5 = (Animator.AnimatorPauseListener)(new Animator.AnimatorPauseListener(var2, var1) {
         final Function1 $onPause;
         final Function1 $onResume;

         public {
            this.$onPause = var1;
            this.$onResume = var2;
         }

         public void onAnimationPause(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onPause.invoke(var1);
         }

         public void onAnimationResume(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onResume.invoke(var1);
         }
      });
      var0.addPauseListener(var5);
      return var5;
   }

   public static final Animator.AnimatorListener doOnCancel(Animator var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnCancel");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Animator.AnimatorListener var2 = (Animator.AnimatorListener)(new Animator.AnimatorListener(var1) {
         final Function1 $onCancel;

         public {
            this.$onCancel = var1;
         }

         public void onAnimationCancel(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onCancel.invoke(var1);
         }

         public void onAnimationEnd(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationRepeat(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationStart(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }
      });
      var0.addListener(var2);
      return var2;
   }

   public static final Animator.AnimatorListener doOnEnd(Animator var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnEnd");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Animator.AnimatorListener var2 = (Animator.AnimatorListener)(new Animator.AnimatorListener(var1) {
         final Function1 $onEnd;

         public {
            this.$onEnd = var1;
         }

         public void onAnimationCancel(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationEnd(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onEnd.invoke(var1);
         }

         public void onAnimationRepeat(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationStart(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }
      });
      var0.addListener(var2);
      return var2;
   }

   public static final Animator.AnimatorPauseListener doOnPause(Animator var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnPause");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Animator.AnimatorPauseListener var2 = (Animator.AnimatorPauseListener)(new Animator.AnimatorPauseListener(var1) {
         final Function1 $onPause;

         public {
            this.$onPause = var1;
         }

         public void onAnimationPause(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onPause.invoke(var1);
         }

         public void onAnimationResume(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }
      });
      var0.addPauseListener(var2);
      return var2;
   }

   public static final Animator.AnimatorListener doOnRepeat(Animator var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnRepeat");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Animator.AnimatorListener var2 = (Animator.AnimatorListener)(new Animator.AnimatorListener(var1) {
         final Function1 $onRepeat;

         public {
            this.$onRepeat = var1;
         }

         public void onAnimationCancel(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationEnd(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationRepeat(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onRepeat.invoke(var1);
         }

         public void onAnimationStart(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }
      });
      var0.addListener(var2);
      return var2;
   }

   public static final Animator.AnimatorPauseListener doOnResume(Animator var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnResume");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Animator.AnimatorPauseListener var2 = (Animator.AnimatorPauseListener)(new Animator.AnimatorPauseListener(var1) {
         final Function1 $onResume;

         public {
            this.$onResume = var1;
         }

         public void onAnimationPause(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationResume(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onResume.invoke(var1);
         }
      });
      var0.addPauseListener(var2);
      return var2;
   }

   public static final Animator.AnimatorListener doOnStart(Animator var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnStart");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      Animator.AnimatorListener var2 = (Animator.AnimatorListener)(new Animator.AnimatorListener(var1) {
         final Function1 $onStart;

         public {
            this.$onStart = var1;
         }

         public void onAnimationCancel(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationEnd(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationRepeat(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
         }

         public void onAnimationStart(Animator var1) {
            Intrinsics.checkParameterIsNotNull(var1, "animator");
            this.$onStart.invoke(var1);
         }
      });
      var0.addListener(var2);
      return var2;
   }
}
