package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class DefaultSpecialEffectsController extends SpecialEffectsController {
   DefaultSpecialEffectsController(ViewGroup var1) {
      super(var1);
   }

   private void startAnimations(List var1, List var2, boolean var3, Map var4) {
      ViewGroup var8 = this.getContainer();
      Context var7 = var8.getContext();
      ArrayList var9 = new ArrayList();
      Iterator var14 = var1.iterator();
      boolean var5 = false;

      while(var14.hasNext()) {
         AnimationInfo var10 = (AnimationInfo)var14.next();
         if (var10.isVisibilityUnchanged()) {
            var10.completeSpecialEffect();
         } else {
            FragmentAnim.AnimationOrAnimator var11 = var10.getAnimation(var7);
            if (var11 == null) {
               var10.completeSpecialEffect();
            } else {
               Animator var22 = var11.animator;
               if (var22 == null) {
                  var9.add(var10);
               } else {
                  SpecialEffectsController.Operation var12 = var10.getOperation();
                  Fragment var13 = var12.getFragment();
                  if (Boolean.TRUE.equals(var4.get(var12))) {
                     if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Ignoring Animator set on " + var13 + " as this Fragment was involved in a Transition.");
                     }

                     var10.completeSpecialEffect();
                  } else {
                     boolean var6;
                     if (var12.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                        var6 = true;
                     } else {
                        var6 = false;
                     }

                     if (var6) {
                        var2.remove(var12);
                     }

                     View var23 = var13.mView;
                     var8.startViewTransition(var23);
                     var22.addListener(new AnimatorListenerAdapter(this, var8, var23, var6, var12, var10) {
                        final DefaultSpecialEffectsController this$0;
                        final AnimationInfo val$animationInfo;
                        final ViewGroup val$container;
                        final boolean val$isHideOperation;
                        final SpecialEffectsController.Operation val$operation;
                        final View val$viewToAnimate;

                        {
                           this.this$0 = var1;
                           this.val$container = var2;
                           this.val$viewToAnimate = var3;
                           this.val$isHideOperation = var4;
                           this.val$operation = var5;
                           this.val$animationInfo = var6;
                        }

                        public void onAnimationEnd(Animator var1) {
                           this.val$container.endViewTransition(this.val$viewToAnimate);
                           if (this.val$isHideOperation) {
                              this.val$operation.getFinalState().applyState(this.val$viewToAnimate);
                           }

                           this.val$animationInfo.completeSpecialEffect();
                        }
                     });
                     var22.setTarget(var23);
                     var22.start();
                     var10.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener(this, var22) {
                        final DefaultSpecialEffectsController this$0;
                        final Animator val$animator;

                        {
                           this.this$0 = var1;
                           this.val$animator = var2;
                        }

                        public void onCancel() {
                           this.val$animator.end();
                        }
                     });
                     var5 = true;
                  }
               }
            }
         }
      }

      Iterator var16 = var9.iterator();

      while(var16.hasNext()) {
         AnimationInfo var15 = (AnimationInfo)var16.next();
         SpecialEffectsController.Operation var17 = var15.getOperation();
         Fragment var19 = var17.getFragment();
         if (var3) {
            if (FragmentManager.isLoggingEnabled(2)) {
               Log.v("FragmentManager", "Ignoring Animation set on " + var19 + " as Animations cannot run alongside Transitions.");
            }

            var15.completeSpecialEffect();
         } else if (var5) {
            if (FragmentManager.isLoggingEnabled(2)) {
               Log.v("FragmentManager", "Ignoring Animation set on " + var19 + " as Animations cannot run alongside Animators.");
            }

            var15.completeSpecialEffect();
         } else {
            View var20 = var19.mView;
            Animation var21 = (Animation)Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator)Preconditions.checkNotNull(var15.getAnimation(var7))).animation);
            if (var17.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
               var20.startAnimation(var21);
               var15.completeSpecialEffect();
            } else {
               var8.startViewTransition(var20);
               FragmentAnim.EndViewTransitionAnimation var18 = new FragmentAnim.EndViewTransitionAnimation(var21, var8, var20);
               var18.setAnimationListener(new Animation.AnimationListener(this, var8, var20, var15) {
                  final DefaultSpecialEffectsController this$0;
                  final AnimationInfo val$animationInfo;
                  final ViewGroup val$container;
                  final View val$viewToAnimate;

                  {
                     this.this$0 = var1;
                     this.val$container = var2;
                     this.val$viewToAnimate = var3;
                     this.val$animationInfo = var4;
                  }

                  public void onAnimationEnd(Animation var1) {
                     this.val$container.post(new Runnable(this) {
                        final <undefinedtype> this$1;

                        {
                           this.this$1 = var1;
                        }

                        public void run() {
                           this.this$1.val$container.endViewTransition(this.this$1.val$viewToAnimate);
                           this.this$1.val$animationInfo.completeSpecialEffect();
                        }
                     });
                  }

                  public void onAnimationRepeat(Animation var1) {
                  }

                  public void onAnimationStart(Animation var1) {
                  }
               });
               var20.startAnimation(var18);
            }

            var15.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener(this, var20, var8, var15) {
               final DefaultSpecialEffectsController this$0;
               final AnimationInfo val$animationInfo;
               final ViewGroup val$container;
               final View val$viewToAnimate;

               {
                  this.this$0 = var1;
                  this.val$viewToAnimate = var2;
                  this.val$container = var3;
                  this.val$animationInfo = var4;
               }

               public void onCancel() {
                  this.val$viewToAnimate.clearAnimation();
                  this.val$container.endViewTransition(this.val$viewToAnimate);
                  this.val$animationInfo.completeSpecialEffect();
               }
            });
         }
      }

   }

   private Map startTransitions(List var1, List var2, boolean var3, SpecialEffectsController.Operation var4, SpecialEffectsController.Operation var5) {
      DefaultSpecialEffectsController var12 = this;
      SpecialEffectsController.Operation var13 = var4;
      SpecialEffectsController.Operation var14 = var5;
      HashMap var16 = new HashMap();
      Iterator var15 = var1.iterator();
      FragmentTransitionImpl var10 = null;

      while(var15.hasNext()) {
         TransitionInfo var17 = (TransitionInfo)var15.next();
         if (!var17.isVisibilityUnchanged()) {
            FragmentTransitionImpl var11 = var17.getHandlingImpl();
            if (var10 == null) {
               var10 = var11;
            } else if (var11 != null && var10 != var11) {
               throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + var17.getOperation().getFragment() + " returned Transition " + var17.getTransition() + " which uses a different Transition  type than other Fragments.");
            }
         }
      }

      boolean var9 = false;
      if (var10 == null) {
         Iterator var31 = var1.iterator();

         while(var31.hasNext()) {
            TransitionInfo var33 = (TransitionInfo)var31.next();
            var16.put(var33.getOperation(), false);
            var33.completeSpecialEffect();
         }

         return var16;
      } else {
         View var22 = new View(this.getContainer().getContext());
         Rect var21 = new Rect();
         ArrayList var49 = new ArrayList();
         ArrayList var55 = new ArrayList();
         ArrayMap var20 = new ArrayMap();
         Iterator var23 = var1.iterator();
         boolean var6 = false;
         Object var18 = null;
         View var41 = null;
         FragmentTransitionImpl var19 = var10;

         ArrayList var24;
         View var40;
         SpecialEffectsController.Operation var52;
         for(var40 = var22; var23.hasNext(); var16 = var16) {
            TransitionInfo var61 = (TransitionInfo)var23.next();
            if (var61.hasSharedElementTransition() && var13 != null && var14 != null) {
               Object var63 = var19.wrapTransitionInSet(var19.cloneTransition(var61.getSharedElementTransition()));
               ArrayList var46 = var5.getFragment().getSharedElementSourceNames();
               ArrayList var57 = var4.getFragment().getSharedElementSourceNames();
               ArrayList var47 = var4.getFragment().getSharedElementTargetNames();

               int var7;
               int var8;
               for(var7 = 0; var7 < var47.size(); ++var7) {
                  var8 = var46.indexOf(var47.get(var7));
                  if (var8 != -1) {
                     var46.set(var8, var57.get(var7));
                  }
               }

               var24 = var5.getFragment().getSharedElementTargetNames();
               SharedElementCallback var48;
               SharedElementCallback var58;
               if (!var3) {
                  var58 = var4.getFragment().getExitTransitionCallback();
                  var48 = var5.getFragment().getEnterTransitionCallback();
               } else {
                  var58 = var4.getFragment().getEnterTransitionCallback();
                  var48 = var5.getFragment().getExitTransitionCallback();
               }

               var7 = var46.size();

               for(var8 = 0; var8 < var7; ++var8) {
                  var20.put((String)var46.get(var8), (String)var24.get(var8));
               }

               ArrayMap var25 = new ArrayMap();
               var12.findNamedViews(var25, var4.getFragment().mView);
               var25.retainAll(var46);
               String var26;
               if (var58 != null) {
                  var58.onMapSharedElements(var46, var25);

                  for(var7 = var46.size() - 1; var7 >= 0; --var7) {
                     var26 = (String)var46.get(var7);
                     View var59 = (View)var25.get(var26);
                     if (var59 == null) {
                        var20.remove(var26);
                     } else if (!var26.equals(ViewCompat.getTransitionName(var59))) {
                        var26 = (String)var20.remove(var26);
                        var20.put(ViewCompat.getTransitionName(var59), var26);
                     }
                  }
               } else {
                  var20.retainAll(var25.keySet());
               }

               ArrayMap var60 = new ArrayMap();
               var12.findNamedViews(var60, var5.getFragment().mView);
               var60.retainAll(var24);
               var60.retainAll(var20.values());
               if (var48 != null) {
                  var48.onMapSharedElements(var24, var60);

                  for(var7 = var24.size() - 1; var7 >= 0; --var7) {
                     var26 = (String)var24.get(var7);
                     View var51 = (View)var60.get(var26);
                     if (var51 == null) {
                        String var53 = FragmentTransition.findKeyForValue(var20, var26);
                        if (var53 != null) {
                           var20.remove(var53);
                        }
                     } else if (!var26.equals(ViewCompat.getTransitionName(var51))) {
                        var26 = FragmentTransition.findKeyForValue(var20, var26);
                        if (var26 != null) {
                           var20.put(var26, ViewCompat.getTransitionName(var51));
                        }
                     }
                  }
               } else {
                  FragmentTransition.retainValues(var20, var60);
               }

               var12.retainMatchingViews(var25, var20.keySet());
               var12.retainMatchingViews(var60, var20.values());
               if (var20.isEmpty()) {
                  var49.clear();
                  var55.clear();
                  var18 = null;
                  var9 = false;
                  var13 = var5;
                  var14 = var4;
               } else {
                  FragmentTransition.callSharedElementStartEnd(var5.getFragment(), var4.getFragment(), var3, var25, true);
                  ViewGroup var44 = this.getContainer();
                  OneShotPreDrawListener.add(var44, new Runnable(this, var5, var4, var3, var60) {
                     final DefaultSpecialEffectsController this$0;
                     final SpecialEffectsController.Operation val$firstOut;
                     final boolean val$isPop;
                     final SpecialEffectsController.Operation val$lastIn;
                     final ArrayMap val$lastInViews;

                     {
                        this.this$0 = var1;
                        this.val$lastIn = var2;
                        this.val$firstOut = var3;
                        this.val$isPop = var4;
                        this.val$lastInViews = var5;
                     }

                     public void run() {
                        FragmentTransition.callSharedElementStartEnd(this.val$lastIn.getFragment(), this.val$firstOut.getFragment(), this.val$isPop, this.val$lastInViews, false);
                     }
                  });
                  var49.addAll(var25.values());
                  if (!var46.isEmpty()) {
                     var41 = (View)var25.get((String)var46.get(0));
                     var19.setEpicenter(var63, var41);
                  }

                  var9 = false;
                  var55.addAll(var60.values());
                  if (!var24.isEmpty()) {
                     View var45 = (View)var60.get((String)var24.get(0));
                     if (var45 != null) {
                        OneShotPreDrawListener.add(this.getContainer(), new Runnable(this, var19, var45, var21) {
                           final DefaultSpecialEffectsController this$0;
                           final FragmentTransitionImpl val$impl;
                           final Rect val$lastInEpicenterRect;
                           final View val$lastInEpicenterView;

                           {
                              this.this$0 = var1;
                              this.val$impl = var2;
                              this.val$lastInEpicenterView = var3;
                              this.val$lastInEpicenterRect = var4;
                           }

                           public void run() {
                              this.val$impl.getBoundsOnScreen(this.val$lastInEpicenterView, this.val$lastInEpicenterRect);
                           }
                        });
                        var6 = true;
                     }
                  }

                  var12 = this;
                  var19.setSharedElementTargets(var63, var40, var49);
                  var19.scheduleRemoveTargets(var63, (Object)null, (ArrayList)null, (Object)null, (ArrayList)null, var63, var55);
                  var14 = var4;
                  var16.put(var4, true);
                  var13 = var5;
                  var16.put(var5, true);
                  var18 = var63;
               }
            } else {
               SpecialEffectsController.Operation var62 = var14;
               var14 = var13;
               var13 = var62;
            }

            var52 = var13;
            var13 = var14;
            var14 = var52;
         }

         var3 = var9;
         View var54 = var40;
         ArrayList var68 = new ArrayList();
         Iterator var66 = var1.iterator();
         var10 = null;
         var24 = null;
         View var56 = var41;
         HashMap var50 = var16;
         Object var38 = var24;
         Object var35 = var10;
         ArrayList var42 = var55;
         ArrayList var43 = var49;

         while(true) {
            while(var66.hasNext()) {
               TransitionInfo var67 = (TransitionInfo)var66.next();
               if (var67.isVisibilityUnchanged()) {
                  var50.put(var67.getOperation(), var3);
                  var67.completeSpecialEffect();
               } else {
                  Object var69 = var19.cloneTransition(var67.getTransition());
                  SpecialEffectsController.Operation var27 = var67.getOperation();
                  if (var18 == null || var27 != var13 && var27 != var14) {
                     var9 = var3;
                  } else {
                     var9 = true;
                  }

                  if (var69 == null) {
                     if (!var9) {
                        var50.put(var27, var3);
                        var67.completeSpecialEffect();
                     }
                  } else {
                     var24 = new ArrayList();
                     var12.captureTransitioningViews(var24, var27.getFragment().mView);
                     if (var9) {
                        if (var27 == var13) {
                           var24.removeAll(var43);
                        } else {
                           var24.removeAll(var42);
                        }
                     }

                     if (var24.isEmpty()) {
                        var19.addTarget(var69, var54);
                     } else {
                        var19.addTargets(var69, var24);
                        var19.scheduleRemoveTargets(var69, var69, var24, (Object)null, (ArrayList)null, (Object)null, (ArrayList)null);
                        if (var27.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                           var2.remove(var27);
                           ArrayList var28 = new ArrayList(var24);
                           var28.remove(var27.getFragment().mView);
                           var19.scheduleHideFragmentView(var69, var27.getFragment().mView, var28);
                           OneShotPreDrawListener.add(this.getContainer(), new Runnable(var12, var24) {
                              final DefaultSpecialEffectsController this$0;
                              final ArrayList val$transitioningViews;

                              {
                                 this.this$0 = var1;
                                 this.val$transitioningViews = var2;
                              }

                              public void run() {
                                 FragmentTransition.setViewVisibility(this.val$transitioningViews, 4);
                              }
                           });
                        }
                     }

                     if (var27.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        var68.addAll(var24);
                        if (var6) {
                           var19.setEpicenter(var69, var21);
                        }
                     } else {
                        var19.setEpicenter(var69, var56);
                     }

                     var50.put(var27, true);
                     if (var67.isOverlapAllowed()) {
                        var38 = var19.mergeTransitionsTogether(var38, var69, (Object)null);
                     } else {
                        var35 = var19.mergeTransitionsTogether(var35, var69, (Object)null);
                     }
                  }

                  var3 = false;
               }
            }

            Object var32 = var19.mergeTransitionsInSequence(var38, var35, var18);
            Iterator var39 = var1.iterator();

            while(true) {
               Object var29;
               TransitionInfo var37;
               do {
                  do {
                     if (!var39.hasNext()) {
                        if (!ViewCompat.isLaidOut(this.getContainer())) {
                           return var50;
                        }

                        FragmentTransition.setViewVisibility(var68, 4);
                        ArrayList var30 = var19.prepareSetNameOverridesReordered(var42);
                        var19.beginDelayedTransition(this.getContainer(), var32);
                        var19.setNameOverridesReordered(this.getContainer(), var43, var42, var30, var20);
                        FragmentTransition.setViewVisibility(var68, 0);
                        var19.swapSharedElementTargets(var18, var43, var42);
                        return var50;
                     }

                     var37 = (TransitionInfo)var39.next();
                  } while(var37.isVisibilityUnchanged());

                  var29 = var37.getTransition();
                  var52 = var37.getOperation();
                  if (var18 == null || var52 != var13 && var52 != var14) {
                     var6 = false;
                  } else {
                     var6 = true;
                  }
               } while(var29 == null && !var6);

               if (!ViewCompat.isLaidOut(this.getContainer())) {
                  if (FragmentManager.isLoggingEnabled(2)) {
                     Log.v("FragmentManager", "SpecialEffectsController: Container " + this.getContainer() + " has not been laid out. Completing operation " + var52);
                  }

                  var37.completeSpecialEffect();
               } else {
                  var19.setListenerForTransitionEnd(var37.getOperation().getFragment(), var32, var37.getSignal(), new Runnable(var12, var37) {
                     final DefaultSpecialEffectsController this$0;
                     final TransitionInfo val$transitionInfo;

                     {
                        this.this$0 = var1;
                        this.val$transitionInfo = var2;
                     }

                     public void run() {
                        this.val$transitionInfo.completeSpecialEffect();
                     }
                  });
               }
            }
         }
      }
   }

   void applyContainerChanges(SpecialEffectsController.Operation var1) {
      View var2 = var1.getFragment().mView;
      var1.getFinalState().applyState(var2);
   }

   void captureTransitioningViews(ArrayList var1, View var2) {
      if (var2 instanceof ViewGroup) {
         ViewGroup var5 = (ViewGroup)var2;
         if (ViewGroupCompat.isTransitionGroup(var5)) {
            if (!var1.contains(var2)) {
               var1.add(var5);
            }
         } else {
            int var4 = var5.getChildCount();

            for(int var3 = 0; var3 < var4; ++var3) {
               var2 = var5.getChildAt(var3);
               if (var2.getVisibility() == 0) {
                  this.captureTransitioningViews(var1, var2);
               }
            }
         }
      } else if (!var1.contains(var2)) {
         var1.add(var2);
      }

   }

   void executeOperations(List var1, boolean var2) {
      Iterator var8 = var1.iterator();
      SpecialEffectsController.Operation var6 = null;
      SpecialEffectsController.Operation var5 = null;

      while(true) {
         while(var8.hasNext()) {
            SpecialEffectsController.Operation var7 = (SpecialEffectsController.Operation)var8.next();
            SpecialEffectsController.Operation.State var9 = SpecialEffectsController.Operation.State.from(var7.getFragment().mView);
            int var3 = null.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[var7.getFinalState().ordinal()];
            if (var3 != 1 && var3 != 2 && var3 != 3) {
               if (var3 == 4 && var9 != SpecialEffectsController.Operation.State.VISIBLE) {
                  var5 = var7;
               }
            } else if (var9 == SpecialEffectsController.Operation.State.VISIBLE && var6 == null) {
               var6 = var7;
            }
         }

         ArrayList var16 = new ArrayList();
         ArrayList var17 = new ArrayList();
         ArrayList var15 = new ArrayList(var1);
         Iterator var10 = var1.iterator();

         while(var10.hasNext()) {
            boolean var4;
            CancellationSignal var11;
            SpecialEffectsController.Operation var12;
            label43: {
               var12 = (SpecialEffectsController.Operation)var10.next();
               var11 = new CancellationSignal();
               var12.markStartedSpecialEffect(var11);
               var16.add(new AnimationInfo(var12, var11, var2));
               var11 = new CancellationSignal();
               var12.markStartedSpecialEffect(var11);
               var4 = false;
               if (var2) {
                  if (var12 != var6) {
                     break label43;
                  }
               } else if (var12 != var5) {
                  break label43;
               }

               var4 = true;
            }

            var17.add(new TransitionInfo(var12, var11, var2, var4));
            var12.addCompletionListener(new Runnable(this, var15, var12) {
               final DefaultSpecialEffectsController this$0;
               final List val$awaitingContainerChanges;
               final SpecialEffectsController.Operation val$operation;

               {
                  this.this$0 = var1;
                  this.val$awaitingContainerChanges = var2;
                  this.val$operation = var3;
               }

               public void run() {
                  if (this.val$awaitingContainerChanges.contains(this.val$operation)) {
                     this.val$awaitingContainerChanges.remove(this.val$operation);
                     this.this$0.applyContainerChanges(this.val$operation);
                  }

               }
            });
         }

         Map var13 = this.startTransitions(var17, var15, var2, var6, var5);
         this.startAnimations(var16, var15, var13.containsValue(true), var13);
         Iterator var14 = var15.iterator();

         while(var14.hasNext()) {
            this.applyContainerChanges((SpecialEffectsController.Operation)var14.next());
         }

         var15.clear();
         return;
      }
   }

   void findNamedViews(Map var1, View var2) {
      String var5 = ViewCompat.getTransitionName(var2);
      if (var5 != null) {
         var1.put(var5, var2);
      }

      if (var2 instanceof ViewGroup) {
         ViewGroup var6 = (ViewGroup)var2;
         int var4 = var6.getChildCount();

         for(int var3 = 0; var3 < var4; ++var3) {
            var2 = var6.getChildAt(var3);
            if (var2.getVisibility() == 0) {
               this.findNamedViews(var1, var2);
            }
         }
      }

   }

   void retainMatchingViews(ArrayMap var1, Collection var2) {
      Iterator var3 = var1.entrySet().iterator();

      while(var3.hasNext()) {
         if (!var2.contains(ViewCompat.getTransitionName((View)((Map.Entry)var3.next()).getValue()))) {
            var3.remove();
         }
      }

   }

   private static class AnimationInfo extends SpecialEffectsInfo {
      private FragmentAnim.AnimationOrAnimator mAnimation;
      private boolean mIsPop;
      private boolean mLoadedAnim = false;

      AnimationInfo(SpecialEffectsController.Operation var1, CancellationSignal var2, boolean var3) {
         super(var1, var2);
         this.mIsPop = var3;
      }

      FragmentAnim.AnimationOrAnimator getAnimation(Context var1) {
         if (this.mLoadedAnim) {
            return this.mAnimation;
         } else {
            Fragment var3 = this.getOperation().getFragment();
            boolean var2;
            if (this.getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
               var2 = true;
            } else {
               var2 = false;
            }

            FragmentAnim.AnimationOrAnimator var4 = FragmentAnim.loadAnimation(var1, var3, var2, this.mIsPop);
            this.mAnimation = var4;
            this.mLoadedAnim = true;
            return var4;
         }
      }
   }

   private static class SpecialEffectsInfo {
      private final SpecialEffectsController.Operation mOperation;
      private final CancellationSignal mSignal;

      SpecialEffectsInfo(SpecialEffectsController.Operation var1, CancellationSignal var2) {
         this.mOperation = var1;
         this.mSignal = var2;
      }

      void completeSpecialEffect() {
         this.mOperation.completeSpecialEffect(this.mSignal);
      }

      SpecialEffectsController.Operation getOperation() {
         return this.mOperation;
      }

      CancellationSignal getSignal() {
         return this.mSignal;
      }

      boolean isVisibilityUnchanged() {
         SpecialEffectsController.Operation.State var2 = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
         SpecialEffectsController.Operation.State var3 = this.mOperation.getFinalState();
         boolean var1;
         if (var2 == var3 || var2 != SpecialEffectsController.Operation.State.VISIBLE && var3 != SpecialEffectsController.Operation.State.VISIBLE) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }
   }

   private static class TransitionInfo extends SpecialEffectsInfo {
      private final boolean mOverlapAllowed;
      private final Object mSharedElementTransition;
      private final Object mTransition;

      TransitionInfo(SpecialEffectsController.Operation var1, CancellationSignal var2, boolean var3, boolean var4) {
         super(var1, var2);
         Object var6;
         if (var1.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
            if (var3) {
               var6 = var1.getFragment().getReenterTransition();
            } else {
               var6 = var1.getFragment().getEnterTransition();
            }

            this.mTransition = var6;
            boolean var5;
            if (var3) {
               var5 = var1.getFragment().getAllowReturnTransitionOverlap();
            } else {
               var5 = var1.getFragment().getAllowEnterTransitionOverlap();
            }

            this.mOverlapAllowed = var5;
         } else {
            if (var3) {
               var6 = var1.getFragment().getReturnTransition();
            } else {
               var6 = var1.getFragment().getExitTransition();
            }

            this.mTransition = var6;
            this.mOverlapAllowed = true;
         }

         if (var4) {
            if (var3) {
               this.mSharedElementTransition = var1.getFragment().getSharedElementReturnTransition();
            } else {
               this.mSharedElementTransition = var1.getFragment().getSharedElementEnterTransition();
            }
         } else {
            this.mSharedElementTransition = null;
         }

      }

      private FragmentTransitionImpl getHandlingImpl(Object var1) {
         if (var1 == null) {
            return null;
         } else if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(var1)) {
            return FragmentTransition.PLATFORM_IMPL;
         } else if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(var1)) {
            return FragmentTransition.SUPPORT_IMPL;
         } else {
            throw new IllegalArgumentException("Transition " + var1 + " for fragment " + this.getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
         }
      }

      FragmentTransitionImpl getHandlingImpl() {
         FragmentTransitionImpl var2 = this.getHandlingImpl(this.mTransition);
         FragmentTransitionImpl var1 = this.getHandlingImpl(this.mSharedElementTransition);
         if (var2 != null && var1 != null && var2 != var1) {
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
         } else {
            if (var2 != null) {
               var1 = var2;
            }

            return var1;
         }
      }

      public Object getSharedElementTransition() {
         return this.mSharedElementTransition;
      }

      Object getTransition() {
         return this.mTransition;
      }

      public boolean hasSharedElementTransition() {
         boolean var1;
         if (this.mSharedElementTransition != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean isOverlapAllowed() {
         return this.mOverlapAllowed;
      }
   }
}
