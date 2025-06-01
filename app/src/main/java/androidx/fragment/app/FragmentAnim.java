package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R;

class FragmentAnim {
   private FragmentAnim() {
   }

   static void animateRemoveFragment(Fragment var0, AnimationOrAnimator var1, FragmentTransition.Callback var2) {
      View var6 = var0.mView;
      ViewGroup var3 = var0.mContainer;
      var3.startViewTransition(var6);
      CancellationSignal var4 = new CancellationSignal();
      var4.setOnCancelListener(new CancellationSignal.OnCancelListener(var0) {
         final Fragment val$fragment;

         {
            this.val$fragment = var1;
         }

         public void onCancel() {
            if (this.val$fragment.getAnimatingAway() != null) {
               View var1 = this.val$fragment.getAnimatingAway();
               this.val$fragment.setAnimatingAway((View)null);
               var1.clearAnimation();
            }

            this.val$fragment.setAnimator((Animator)null);
         }
      });
      var2.onStart(var0, var4);
      if (var1.animation != null) {
         EndViewTransitionAnimation var7 = new EndViewTransitionAnimation(var1.animation, var3, var6);
         var0.setAnimatingAway(var0.mView);
         var7.setAnimationListener(new Animation.AnimationListener(var3, var0, var2, var4) {
            final FragmentTransition.Callback val$callback;
            final ViewGroup val$container;
            final Fragment val$fragment;
            final CancellationSignal val$signal;

            {
               this.val$container = var1;
               this.val$fragment = var2;
               this.val$callback = var3;
               this.val$signal = var4;
            }

            public void onAnimationEnd(Animation var1) {
               this.val$container.post(new Runnable(this) {
                  final <undefinedtype> this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void run() {
                     if (this.this$0.val$fragment.getAnimatingAway() != null) {
                        this.this$0.val$fragment.setAnimatingAway((View)null);
                        this.this$0.val$callback.onComplete(this.this$0.val$fragment, this.this$0.val$signal);
                     }

                  }
               });
            }

            public void onAnimationRepeat(Animation var1) {
            }

            public void onAnimationStart(Animation var1) {
            }
         });
         var0.mView.startAnimation(var7);
      } else {
         Animator var5 = var1.animator;
         var0.setAnimator(var1.animator);
         var5.addListener(new AnimatorListenerAdapter(var3, var6, var0, var2, var4) {
            final FragmentTransition.Callback val$callback;
            final ViewGroup val$container;
            final Fragment val$fragment;
            final CancellationSignal val$signal;
            final View val$viewToAnimate;

            {
               this.val$container = var1;
               this.val$viewToAnimate = var2;
               this.val$fragment = var3;
               this.val$callback = var4;
               this.val$signal = var5;
            }

            public void onAnimationEnd(Animator var1) {
               this.val$container.endViewTransition(this.val$viewToAnimate);
               var1 = this.val$fragment.getAnimator();
               this.val$fragment.setAnimator((Animator)null);
               if (var1 != null && this.val$container.indexOfChild(this.val$viewToAnimate) < 0) {
                  this.val$callback.onComplete(this.val$fragment, this.val$signal);
               }

            }
         });
         var5.setTarget(var0.mView);
         var5.start();
      }

   }

   private static int getNextAnim(Fragment var0, boolean var1, boolean var2) {
      if (var2) {
         return var1 ? var0.getPopEnterAnim() : var0.getPopExitAnim();
      } else {
         return var1 ? var0.getEnterAnim() : var0.getExitAnim();
      }
   }

   static AnimationOrAnimator loadAnimation(Context var0, Fragment var1, boolean var2, boolean var3) {
      int var7 = var1.getNextTransition();
      int var5 = getNextAnim(var1, var2, var3);
      boolean var6 = false;
      var1.setAnimations(0, 0, 0, 0);
      if (var1.mContainer != null && var1.mContainer.getTag(R.id.visible_removing_fragment_view_tag) != null) {
         var1.mContainer.setTag(R.id.visible_removing_fragment_view_tag, (Object)null);
      }

      if (var1.mContainer != null && var1.mContainer.getLayoutTransition() != null) {
         return null;
      } else {
         Animation var8 = var1.onCreateAnimation(var7, var2, var5);
         if (var8 != null) {
            return new AnimationOrAnimator(var8);
         } else {
            Animator var17 = var1.onCreateAnimator(var7, var2, var5);
            if (var17 != null) {
               return new AnimationOrAnimator(var17);
            } else {
               int var4 = var5;
               if (var5 == 0) {
                  var4 = var5;
                  if (var7 != 0) {
                     var4 = transitToAnimResourceId(var7, var2);
                  }
               }

               Resources.NotFoundException var10000;
               label83: {
                  if (var4 != 0) {
                     var2 = "anim".equals(var0.getResources().getResourceTypeName(var4));
                     boolean var21 = var6;
                     boolean var10001;
                     AnimationOrAnimator var19;
                     if (var2) {
                        label99: {
                           label94: {
                              Animation var18;
                              try {
                                 var18 = AnimationUtils.loadAnimation(var0, var4);
                              } catch (Resources.NotFoundException var13) {
                                 var10000 = var13;
                                 var10001 = false;
                                 break label83;
                              } catch (RuntimeException var14) {
                                 var10001 = false;
                                 break label94;
                              }

                              if (var18 == null) {
                                 var21 = true;
                                 break label99;
                              }

                              try {
                                 var19 = new AnimationOrAnimator(var18);
                                 return var19;
                              } catch (Resources.NotFoundException var11) {
                                 var10000 = var11;
                                 var10001 = false;
                                 break label83;
                              } catch (RuntimeException var12) {
                                 var10001 = false;
                              }
                           }

                           var21 = var6;
                        }
                     }

                     if (!var21) {
                        RuntimeException var22;
                        label96: {
                           try {
                              var17 = AnimatorInflater.loadAnimator(var0, var4);
                           } catch (RuntimeException var10) {
                              var22 = var10;
                              var10001 = false;
                              break label96;
                           }

                           if (var17 == null) {
                              return null;
                           }

                           try {
                              var19 = new AnimationOrAnimator(var17);
                              return var19;
                           } catch (RuntimeException var9) {
                              var22 = var9;
                              var10001 = false;
                           }
                        }

                        RuntimeException var20 = var22;
                        if (var2) {
                           throw var20;
                        }

                        Animation var16 = AnimationUtils.loadAnimation(var0, var4);
                        if (var16 != null) {
                           return new AnimationOrAnimator(var16);
                        }
                     }
                  }

                  return null;
               }

               Resources.NotFoundException var15 = var10000;
               throw var15;
            }
         }
      }
   }

   private static int transitToAnimResourceId(int var0, boolean var1) {
      if (var0 != 4097) {
         if (var0 != 4099) {
            if (var0 != 8194) {
               var0 = -1;
            } else if (var1) {
               var0 = R.animator.fragment_close_enter;
            } else {
               var0 = R.animator.fragment_close_exit;
            }
         } else if (var1) {
            var0 = R.animator.fragment_fade_enter;
         } else {
            var0 = R.animator.fragment_fade_exit;
         }
      } else if (var1) {
         var0 = R.animator.fragment_open_enter;
      } else {
         var0 = R.animator.fragment_open_exit;
      }

      return var0;
   }

   static class AnimationOrAnimator {
      public final Animation animation;
      public final Animator animator;

      AnimationOrAnimator(Animator var1) {
         this.animation = null;
         this.animator = var1;
         if (var1 == null) {
            throw new IllegalStateException("Animator cannot be null");
         }
      }

      AnimationOrAnimator(Animation var1) {
         this.animation = var1;
         this.animator = null;
         if (var1 == null) {
            throw new IllegalStateException("Animation cannot be null");
         }
      }
   }

   static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
      private boolean mAnimating = true;
      private final View mChild;
      private boolean mEnded;
      private final ViewGroup mParent;
      private boolean mTransitionEnded;

      EndViewTransitionAnimation(Animation var1, ViewGroup var2, View var3) {
         super(false);
         this.mParent = var2;
         this.mChild = var3;
         this.addAnimation(var1);
         var2.post(this);
      }

      public boolean getTransformation(long var1, Transformation var3) {
         this.mAnimating = true;
         if (this.mEnded) {
            return this.mTransitionEnded ^ true;
         } else {
            if (!super.getTransformation(var1, var3)) {
               this.mEnded = true;
               OneShotPreDrawListener.add(this.mParent, this);
            }

            return true;
         }
      }

      public boolean getTransformation(long var1, Transformation var3, float var4) {
         this.mAnimating = true;
         if (this.mEnded) {
            return this.mTransitionEnded ^ true;
         } else {
            if (!super.getTransformation(var1, var3, var4)) {
               this.mEnded = true;
               OneShotPreDrawListener.add(this.mParent, this);
            }

            return true;
         }
      }

      public void run() {
         if (!this.mEnded && this.mAnimating) {
            this.mAnimating = false;
            this.mParent.post(this);
         } else {
            this.mParent.endViewTransition(this.mChild);
            this.mTransitionEnded = true;
         }

      }
   }
}
