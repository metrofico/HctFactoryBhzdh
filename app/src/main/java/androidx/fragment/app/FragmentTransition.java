package androidx.fragment.app;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition {
   private static final int[] INVERSE_OPS = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
   static final FragmentTransitionImpl PLATFORM_IMPL;
   static final FragmentTransitionImpl SUPPORT_IMPL;

   static {
      FragmentTransitionCompat21 var0;
      if (VERSION.SDK_INT >= 21) {
         var0 = new FragmentTransitionCompat21();
      } else {
         var0 = null;
      }

      PLATFORM_IMPL = var0;
      SUPPORT_IMPL = resolveSupportImpl();
   }

   private FragmentTransition() {
   }

   private static void addSharedElementsWithMatchingNames(ArrayList var0, ArrayMap var1, Collection var2) {
      for(int var3 = var1.size() - 1; var3 >= 0; --var3) {
         View var4 = (View)var1.valueAt(var3);
         if (var2.contains(ViewCompat.getTransitionName(var4))) {
            var0.add(var4);
         }
      }

   }

   private static void addToFirstInLastOut(BackStackRecord var0, FragmentTransaction.Op var1, SparseArray var2, boolean var3, boolean var4) {
      Fragment var12 = var1.mFragment;
      if (var12 != null) {
         int var9 = var12.mContainerId;
         if (var9 != 0) {
            int var5;
            if (var3) {
               var5 = INVERSE_OPS[var1.mCmd];
            } else {
               var5 = var1.mCmd;
            }

            boolean var6;
            boolean var7;
            boolean var10;
            boolean var15;
            label137: {
               label136: {
                  boolean var8;
                  label135: {
                     label134: {
                        label133: {
                           label132: {
                              label150: {
                                 var10 = false;
                                 var8 = true;
                                 if (var5 != 1) {
                                    label149: {
                                       if (var5 != 3) {
                                          if (var5 == 4) {
                                             if (var4) {
                                                if (var12.mHiddenChanged && var12.mAdded && var12.mHidden) {
                                                   break label134;
                                                }
                                             } else if (var12.mAdded && !var12.mHidden) {
                                                break label134;
                                             }
                                             break label133;
                                          }

                                          if (var5 == 5) {
                                             if (!var4) {
                                                var10 = var12.mHidden;
                                                break label135;
                                             }

                                             if (var12.mHiddenChanged && !var12.mHidden && var12.mAdded) {
                                                break label132;
                                             }
                                             break label150;
                                          }

                                          if (var5 != 6) {
                                             if (var5 != 7) {
                                                var7 = false;
                                                var15 = false;
                                                var6 = var15;
                                                break label137;
                                             }
                                             break label149;
                                          }
                                       }

                                       if (var4) {
                                          if (!var12.mAdded && var12.mView != null && var12.mView.getVisibility() == 0 && var12.mPostponedAlpha >= 0.0F) {
                                             break label134;
                                          }
                                       } else if (var12.mAdded && !var12.mHidden) {
                                          break label134;
                                       }
                                       break label133;
                                    }
                                 }

                                 if (var4) {
                                    var10 = var12.mIsNewlyAdded;
                                    break label135;
                                 }

                                 if (!var12.mAdded && !var12.mHidden) {
                                    break label132;
                                 }
                              }

                              var10 = false;
                              break label135;
                           }

                           var10 = true;
                           break label135;
                        }

                        var15 = false;
                        break label136;
                     }

                     var15 = true;
                     break label136;
                  }

                  var6 = false;
                  var7 = false;
                  var15 = var8;
                  break label137;
               }

               var6 = var15;
               var7 = true;
               var15 = false;
            }

            FragmentContainerTransition var11 = (FragmentContainerTransition)var2.get(var9);
            FragmentContainerTransition var14 = var11;
            if (var10) {
               var14 = ensureContainer(var11, var2, var9);
               var14.lastIn = var12;
               var14.lastInIsPop = var3;
               var14.lastInTransaction = var0;
            }

            if (!var4 && var15) {
               if (var14 != null && var14.firstOut == var12) {
                  var14.firstOut = null;
               }

               if (!var0.mReorderingAllowed) {
                  FragmentManager var16 = var0.mManager;
                  FragmentStateManager var13 = var16.createOrGetFragmentStateManager(var12);
                  var16.getFragmentStore().makeActive(var13);
                  var16.moveToState(var12);
               }
            }

            var11 = var14;
            if (var6) {
               label85: {
                  if (var14 != null) {
                     var11 = var14;
                     if (var14.firstOut != null) {
                        break label85;
                     }
                  }

                  var11 = ensureContainer(var14, var2, var9);
                  var11.firstOut = var12;
                  var11.firstOutIsPop = var3;
                  var11.firstOutTransaction = var0;
               }
            }

            if (!var4 && var7 && var11 != null && var11.lastIn == var12) {
               var11.lastIn = null;
            }

         }
      }
   }

   public static void calculateFragments(BackStackRecord var0, SparseArray var1, boolean var2) {
      int var4 = var0.mOps.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         addToFirstInLastOut(var0, (FragmentTransaction.Op)var0.mOps.get(var3), var1, false, var2);
      }

   }

   private static ArrayMap calculateNameOverrides(int var0, ArrayList var1, ArrayList var2, int var3, int var4) {
      ArrayMap var10 = new ArrayMap();
      --var4;

      for(; var4 >= var3; --var4) {
         BackStackRecord var11 = (BackStackRecord)var1.get(var4);
         if (var11.interactsWith(var0)) {
            boolean var7 = (Boolean)var2.get(var4);
            if (var11.mSharedElementSourceNames != null) {
               int var6 = var11.mSharedElementSourceNames.size();
               ArrayList var8;
               ArrayList var9;
               if (var7) {
                  var8 = var11.mSharedElementSourceNames;
                  var9 = var11.mSharedElementTargetNames;
               } else {
                  var9 = var11.mSharedElementSourceNames;
                  var8 = var11.mSharedElementTargetNames;
               }

               for(int var5 = 0; var5 < var6; ++var5) {
                  String var12 = (String)var9.get(var5);
                  String var13 = (String)var8.get(var5);
                  String var14 = (String)var10.remove(var13);
                  if (var14 != null) {
                     var10.put(var12, var14);
                  } else {
                     var10.put(var12, var13);
                  }
               }
            }
         }
      }

      return var10;
   }

   public static void calculatePopFragments(BackStackRecord var0, SparseArray var1, boolean var2) {
      if (var0.mManager.getContainer().onHasView()) {
         for(int var3 = var0.mOps.size() - 1; var3 >= 0; --var3) {
            addToFirstInLastOut(var0, (FragmentTransaction.Op)var0.mOps.get(var3), var1, true, var2);
         }

      }
   }

   static void callSharedElementStartEnd(Fragment var0, Fragment var1, boolean var2, ArrayMap var3, boolean var4) {
      SharedElementCallback var8;
      if (var2) {
         var8 = var1.getEnterTransitionCallback();
      } else {
         var8 = var0.getEnterTransitionCallback();
      }

      if (var8 != null) {
         ArrayList var9 = new ArrayList();
         ArrayList var7 = new ArrayList();
         int var6 = 0;
         int var5;
         if (var3 == null) {
            var5 = 0;
         } else {
            var5 = var3.size();
         }

         while(var6 < var5) {
            var7.add(var3.keyAt(var6));
            var9.add(var3.valueAt(var6));
            ++var6;
         }

         if (var4) {
            var8.onSharedElementStart(var7, var9, (List)null);
         } else {
            var8.onSharedElementEnd(var7, var9, (List)null);
         }
      }

   }

   private static boolean canHandleAll(FragmentTransitionImpl var0, List var1) {
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         if (!var0.canHandle(var1.get(var2))) {
            return false;
         }
      }

      return true;
   }

   static ArrayMap captureInSharedElements(FragmentTransitionImpl var0, ArrayMap var1, Object var2, FragmentContainerTransition var3) {
      Fragment var6 = var3.lastIn;
      View var7 = var6.getView();
      if (!var1.isEmpty() && var2 != null && var7 != null) {
         ArrayMap var5 = new ArrayMap();
         var0.findNamedViews(var5, var7);
         BackStackRecord var8 = var3.lastInTransaction;
         ArrayList var9;
         SharedElementCallback var10;
         if (var3.lastInIsPop) {
            var10 = var6.getExitTransitionCallback();
            var9 = var8.mSharedElementSourceNames;
         } else {
            var10 = var6.getEnterTransitionCallback();
            var9 = var8.mSharedElementTargetNames;
         }

         if (var9 != null) {
            var5.retainAll(var9);
            var5.retainAll(var1.values());
         }

         if (var10 != null) {
            var10.onMapSharedElements(var9, var5);

            for(int var4 = var9.size() - 1; var4 >= 0; --var4) {
               String var11 = (String)var9.get(var4);
               View var12 = (View)var5.get(var11);
               if (var12 == null) {
                  String var13 = findKeyForValue(var1, var11);
                  if (var13 != null) {
                     var1.remove(var13);
                  }
               } else if (!var11.equals(ViewCompat.getTransitionName(var12))) {
                  var11 = findKeyForValue(var1, var11);
                  if (var11 != null) {
                     var1.put(var11, ViewCompat.getTransitionName(var12));
                  }
               }
            }
         } else {
            retainValues(var1, var5);
         }

         return var5;
      } else {
         var1.clear();
         return null;
      }
   }

   private static ArrayMap captureOutSharedElements(FragmentTransitionImpl var0, ArrayMap var1, Object var2, FragmentContainerTransition var3) {
      if (!var1.isEmpty() && var2 != null) {
         Fragment var8 = var3.firstOut;
         ArrayMap var5 = new ArrayMap();
         var0.findNamedViews(var5, var8.requireView());
         BackStackRecord var6 = var3.firstOutTransaction;
         ArrayList var7;
         SharedElementCallback var9;
         if (var3.firstOutIsPop) {
            var9 = var8.getEnterTransitionCallback();
            var7 = var6.mSharedElementTargetNames;
         } else {
            var9 = var8.getExitTransitionCallback();
            var7 = var6.mSharedElementSourceNames;
         }

         if (var7 != null) {
            var5.retainAll(var7);
         }

         if (var9 != null) {
            var9.onMapSharedElements(var7, var5);

            for(int var4 = var7.size() - 1; var4 >= 0; --var4) {
               String var10 = (String)var7.get(var4);
               View var11 = (View)var5.get(var10);
               if (var11 == null) {
                  var1.remove(var10);
               } else if (!var10.equals(ViewCompat.getTransitionName(var11))) {
                  var10 = (String)var1.remove(var10);
                  var1.put(ViewCompat.getTransitionName(var11), var10);
               }
            }
         } else {
            var1.retainAll(var5.keySet());
         }

         return var5;
      } else {
         var1.clear();
         return null;
      }
   }

   private static FragmentTransitionImpl chooseImpl(Fragment var0, Fragment var1) {
      ArrayList var2 = new ArrayList();
      Object var4;
      if (var0 != null) {
         Object var3 = var0.getExitTransition();
         if (var3 != null) {
            var2.add(var3);
         }

         var3 = var0.getReturnTransition();
         if (var3 != null) {
            var2.add(var3);
         }

         var4 = var0.getSharedElementReturnTransition();
         if (var4 != null) {
            var2.add(var4);
         }
      }

      if (var1 != null) {
         var4 = var1.getEnterTransition();
         if (var4 != null) {
            var2.add(var4);
         }

         var4 = var1.getReenterTransition();
         if (var4 != null) {
            var2.add(var4);
         }

         var4 = var1.getSharedElementEnterTransition();
         if (var4 != null) {
            var2.add(var4);
         }
      }

      if (var2.isEmpty()) {
         return null;
      } else {
         FragmentTransitionImpl var5 = PLATFORM_IMPL;
         if (var5 != null && canHandleAll(var5, var2)) {
            return var5;
         } else {
            FragmentTransitionImpl var6 = SUPPORT_IMPL;
            if (var6 != null && canHandleAll(var6, var2)) {
               return var6;
            } else if (var5 == null && var6 == null) {
               return null;
            } else {
               throw new IllegalArgumentException("Invalid Transition types");
            }
         }
      }
   }

   static ArrayList configureEnteringExitingViews(FragmentTransitionImpl var0, Object var1, Fragment var2, ArrayList var3, View var4) {
      ArrayList var7;
      if (var1 != null) {
         ArrayList var5 = new ArrayList();
         View var6 = var2.getView();
         if (var6 != null) {
            var0.captureTransitioningViews(var5, var6);
         }

         if (var3 != null) {
            var5.removeAll(var3);
         }

         var7 = var5;
         if (!var5.isEmpty()) {
            var5.add(var4);
            var0.addTargets(var1, var5);
            var7 = var5;
         }
      } else {
         var7 = null;
      }

      return var7;
   }

   private static Object configureSharedElementsOrdered(FragmentTransitionImpl var0, ViewGroup var1, View var2, ArrayMap var3, FragmentContainerTransition var4, ArrayList var5, ArrayList var6, Object var7, Object var8) {
      Fragment var13 = var4.lastIn;
      Fragment var12 = var4.firstOut;
      if (var13 != null && var12 != null) {
         boolean var9 = var4.lastInIsPop;
         Object var10;
         if (var3.isEmpty()) {
            var10 = null;
         } else {
            var10 = getSharedElementTransition(var0, var13, var12, var9);
         }

         ArrayMap var14 = captureOutSharedElements(var0, var3, var10, var4);
         if (var3.isEmpty()) {
            var10 = null;
         } else {
            var5.addAll(var14.values());
         }

         if (var7 == null && var8 == null && var10 == null) {
            return null;
         } else {
            callSharedElementStartEnd(var13, var12, var9, var14, true);
            Rect var15;
            if (var10 != null) {
               Rect var11 = new Rect();
               var0.setSharedElementTargets(var10, var2, var5);
               setOutEpicenter(var0, var10, var8, var14, var4.firstOutIsPop, var4.firstOutTransaction);
               var15 = var11;
               if (var7 != null) {
                  var0.setEpicenter(var7, var11);
                  var15 = var11;
               }
            } else {
               var15 = null;
            }

            OneShotPreDrawListener.add(var1, new Runnable(var0, var3, var10, var4, var6, var2, var13, var12, var9, var5, var7, var15) {
               final Object val$enterTransition;
               final Object val$finalSharedElementTransition;
               final FragmentContainerTransition val$fragments;
               final FragmentTransitionImpl val$impl;
               final Rect val$inEpicenter;
               final Fragment val$inFragment;
               final boolean val$inIsPop;
               final ArrayMap val$nameOverrides;
               final View val$nonExistentView;
               final Fragment val$outFragment;
               final ArrayList val$sharedElementsIn;
               final ArrayList val$sharedElementsOut;

               {
                  this.val$impl = var1;
                  this.val$nameOverrides = var2;
                  this.val$finalSharedElementTransition = var3;
                  this.val$fragments = var4;
                  this.val$sharedElementsIn = var5;
                  this.val$nonExistentView = var6;
                  this.val$inFragment = var7;
                  this.val$outFragment = var8;
                  this.val$inIsPop = var9;
                  this.val$sharedElementsOut = var10;
                  this.val$enterTransition = var11;
                  this.val$inEpicenter = var12;
               }

               public void run() {
                  ArrayMap var1 = FragmentTransition.captureInSharedElements(this.val$impl, this.val$nameOverrides, this.val$finalSharedElementTransition, this.val$fragments);
                  if (var1 != null) {
                     this.val$sharedElementsIn.addAll(var1.values());
                     this.val$sharedElementsIn.add(this.val$nonExistentView);
                  }

                  FragmentTransition.callSharedElementStartEnd(this.val$inFragment, this.val$outFragment, this.val$inIsPop, var1, false);
                  Object var2 = this.val$finalSharedElementTransition;
                  if (var2 != null) {
                     this.val$impl.swapSharedElementTargets(var2, this.val$sharedElementsOut, this.val$sharedElementsIn);
                     View var3 = FragmentTransition.getInEpicenterView(var1, this.val$fragments, this.val$enterTransition, this.val$inIsPop);
                     if (var3 != null) {
                        this.val$impl.getBoundsOnScreen(var3, this.val$inEpicenter);
                     }
                  }

               }
            });
            return var10;
         }
      } else {
         return null;
      }
   }

   private static Object configureSharedElementsReordered(FragmentTransitionImpl var0, ViewGroup var1, View var2, ArrayMap var3, FragmentContainerTransition var4, ArrayList var5, ArrayList var6, Object var7, Object var8) {
      Fragment var12 = var4.lastIn;
      Fragment var11 = var4.firstOut;
      if (var12 != null) {
         var12.requireView().setVisibility(0);
      }

      if (var12 != null && var11 != null) {
         boolean var9 = var4.lastInIsPop;
         Object var10;
         if (var3.isEmpty()) {
            var10 = null;
         } else {
            var10 = getSharedElementTransition(var0, var12, var11, var9);
         }

         ArrayMap var14 = captureOutSharedElements(var0, var3, var10, var4);
         ArrayMap var13 = captureInSharedElements(var0, var3, var10, var4);
         Object var16;
         if (var3.isEmpty()) {
            if (var14 != null) {
               var14.clear();
            }

            if (var13 != null) {
               var13.clear();
            }

            var16 = null;
         } else {
            addSharedElementsWithMatchingNames(var5, var14, var3.keySet());
            addSharedElementsWithMatchingNames(var6, var13, var3.values());
            var16 = var10;
         }

         if (var7 == null && var8 == null && var16 == null) {
            return null;
         } else {
            callSharedElementStartEnd(var12, var11, var9, var14, true);
            Rect var15;
            View var17;
            if (var16 != null) {
               var6.add(var2);
               var0.setSharedElementTargets(var16, var2, var5);
               setOutEpicenter(var0, var16, var8, var14, var4.firstOutIsPop, var4.firstOutTransaction);
               var15 = new Rect();
               var17 = getInEpicenterView(var13, var4, var7, var9);
               if (var17 != null) {
                  var0.setEpicenter(var7, var15);
               }
            } else {
               var17 = null;
               var15 = null;
            }

            OneShotPreDrawListener.add(var1, new Runnable(var12, var11, var9, var13, var17, var0, var15) {
               final Rect val$epicenter;
               final View val$epicenterView;
               final FragmentTransitionImpl val$impl;
               final Fragment val$inFragment;
               final boolean val$inIsPop;
               final ArrayMap val$inSharedElements;
               final Fragment val$outFragment;

               {
                  this.val$inFragment = var1;
                  this.val$outFragment = var2;
                  this.val$inIsPop = var3;
                  this.val$inSharedElements = var4;
                  this.val$epicenterView = var5;
                  this.val$impl = var6;
                  this.val$epicenter = var7;
               }

               public void run() {
                  FragmentTransition.callSharedElementStartEnd(this.val$inFragment, this.val$outFragment, this.val$inIsPop, this.val$inSharedElements, false);
                  View var1 = this.val$epicenterView;
                  if (var1 != null) {
                     this.val$impl.getBoundsOnScreen(var1, this.val$epicenter);
                  }

               }
            });
            return var16;
         }
      } else {
         return null;
      }
   }

   private static void configureTransitionsOrdered(ViewGroup var0, FragmentContainerTransition var1, View var2, ArrayMap var3, Callback var4) {
      Fragment var11 = var1.lastIn;
      Fragment var13 = var1.firstOut;
      FragmentTransitionImpl var9 = chooseImpl(var13, var11);
      if (var9 != null) {
         boolean var6 = var1.lastInIsPop;
         boolean var5 = var1.firstOutIsPop;
         Object var10 = getEnterTransition(var9, var11, var6);
         Object var7 = getExitTransition(var9, var13, var5);
         ArrayList var15 = new ArrayList();
         ArrayList var8 = new ArrayList();
         Object var12 = configureSharedElementsOrdered(var9, var0, var2, var3, var1, var15, var8, var10, var7);
         if (var10 != null || var12 != null || var7 != null) {
            ArrayList var14 = configureEnteringExitingViews(var9, var7, var13, var15, var2);
            if (var14 == null || var14.isEmpty()) {
               var7 = null;
            }

            var9.addTarget(var10, var2);
            Object var16 = mergeTransitions(var9, var10, var7, var12, var11, var1.lastInIsPop);
            if (var13 != null && var14 != null && (var14.size() > 0 || var15.size() > 0)) {
               CancellationSignal var18 = new CancellationSignal();
               var4.onStart(var13, var18);
               var9.setListenerForTransitionEnd(var13, var16, var18, new Runnable(var4, var13, var18) {
                  final Callback val$callback;
                  final Fragment val$outFragment;
                  final CancellationSignal val$signal;

                  {
                     this.val$callback = var1;
                     this.val$outFragment = var2;
                     this.val$signal = var3;
                  }

                  public void run() {
                     this.val$callback.onComplete(this.val$outFragment, this.val$signal);
                  }
               });
            }

            if (var16 != null) {
               ArrayList var17 = new ArrayList();
               var9.scheduleRemoveTargets(var16, var10, var17, var7, var14, var12, var8);
               scheduleTargetChange(var9, var0, var11, var2, var8, var10, var17, var7, var14);
               var9.setNameOverridesOrdered(var0, var8, var3);
               var9.beginDelayedTransition(var0, var16);
               var9.scheduleNameReset(var0, var8, var3);
            }

         }
      }
   }

   private static void configureTransitionsReordered(ViewGroup var0, FragmentContainerTransition var1, View var2, ArrayMap var3, Callback var4) {
      Fragment var14 = var1.lastIn;
      Fragment var13 = var1.firstOut;
      FragmentTransitionImpl var8 = chooseImpl(var13, var14);
      if (var8 != null) {
         boolean var6 = var1.lastInIsPop;
         boolean var5 = var1.firstOutIsPop;
         ArrayList var7 = new ArrayList();
         ArrayList var11 = new ArrayList();
         Object var10 = getEnterTransition(var8, var14, var6);
         Object var9 = getExitTransition(var8, var13, var5);
         Object var16 = configureSharedElementsReordered(var8, var0, var2, var3, var1, var11, var7, var10, var9);
         if (var10 != null || var16 != null || var9 != null) {
            ArrayList var12 = configureEnteringExitingViews(var8, var9, var13, var11, var2);
            ArrayList var17 = configureEnteringExitingViews(var8, var10, var14, var7, var2);
            setViewVisibility(var17, 4);
            Object var18 = mergeTransitions(var8, var10, var9, var16, var14, var6);
            if (var13 != null && var12 != null && (var12.size() > 0 || var11.size() > 0)) {
               CancellationSignal var15 = new CancellationSignal();
               var4.onStart(var13, var15);
               var8.setListenerForTransitionEnd(var13, var18, var15, new Runnable(var4, var13, var15) {
                  final Callback val$callback;
                  final Fragment val$outFragment;
                  final CancellationSignal val$signal;

                  {
                     this.val$callback = var1;
                     this.val$outFragment = var2;
                     this.val$signal = var3;
                  }

                  public void run() {
                     this.val$callback.onComplete(this.val$outFragment, this.val$signal);
                  }
               });
            }

            if (var18 != null) {
               replaceHide(var8, var9, var13, var12);
               ArrayList var19 = var8.prepareSetNameOverridesReordered(var7);
               var8.scheduleRemoveTargets(var18, var10, var17, var9, var12, var16, var7);
               var8.beginDelayedTransition(var0, var18);
               var8.setNameOverridesReordered(var0, var11, var7, var19, var3);
               setViewVisibility(var17, 0);
               var8.swapSharedElementTargets(var16, var11, var7);
            }

         }
      }
   }

   private static FragmentContainerTransition ensureContainer(FragmentContainerTransition var0, SparseArray var1, int var2) {
      FragmentContainerTransition var3 = var0;
      if (var0 == null) {
         var3 = new FragmentContainerTransition();
         var1.put(var2, var3);
      }

      return var3;
   }

   static String findKeyForValue(ArrayMap var0, String var1) {
      int var3 = var0.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         if (var1.equals(var0.valueAt(var2))) {
            return (String)var0.keyAt(var2);
         }
      }

      return null;
   }

   private static Object getEnterTransition(FragmentTransitionImpl var0, Fragment var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else {
         Object var3;
         if (var2) {
            var3 = var1.getReenterTransition();
         } else {
            var3 = var1.getEnterTransition();
         }

         return var0.cloneTransition(var3);
      }
   }

   private static Object getExitTransition(FragmentTransitionImpl var0, Fragment var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else {
         Object var3;
         if (var2) {
            var3 = var1.getReturnTransition();
         } else {
            var3 = var1.getExitTransition();
         }

         return var0.cloneTransition(var3);
      }
   }

   static View getInEpicenterView(ArrayMap var0, FragmentContainerTransition var1, Object var2, boolean var3) {
      BackStackRecord var4 = var1.lastInTransaction;
      if (var2 != null && var0 != null && var4.mSharedElementSourceNames != null && !var4.mSharedElementSourceNames.isEmpty()) {
         String var5;
         if (var3) {
            var5 = (String)var4.mSharedElementSourceNames.get(0);
         } else {
            var5 = (String)var4.mSharedElementTargetNames.get(0);
         }

         return (View)var0.get(var5);
      } else {
         return null;
      }
   }

   private static Object getSharedElementTransition(FragmentTransitionImpl var0, Fragment var1, Fragment var2, boolean var3) {
      if (var1 != null && var2 != null) {
         Object var4;
         if (var3) {
            var4 = var2.getSharedElementReturnTransition();
         } else {
            var4 = var1.getSharedElementEnterTransition();
         }

         return var0.wrapTransitionInSet(var0.cloneTransition(var4));
      } else {
         return null;
      }
   }

   private static Object mergeTransitions(FragmentTransitionImpl var0, Object var1, Object var2, Object var3, Fragment var4, boolean var5) {
      if (var1 != null && var2 != null && var4 != null) {
         if (var5) {
            var5 = var4.getAllowReturnTransitionOverlap();
         } else {
            var5 = var4.getAllowEnterTransitionOverlap();
         }
      } else {
         var5 = true;
      }

      Object var6;
      if (var5) {
         var6 = var0.mergeTransitionsTogether(var2, var1, var3);
      } else {
         var6 = var0.mergeTransitionsInSequence(var2, var1, var3);
      }

      return var6;
   }

   private static void replaceHide(FragmentTransitionImpl var0, Object var1, Fragment var2, ArrayList var3) {
      if (var2 != null && var1 != null && var2.mAdded && var2.mHidden && var2.mHiddenChanged) {
         var2.setHideReplaced(true);
         var0.scheduleHideFragmentView(var1, var2.getView(), var3);
         OneShotPreDrawListener.add(var2.mContainer, new Runnable(var3) {
            final ArrayList val$exitingViews;

            {
               this.val$exitingViews = var1;
            }

            public void run() {
               FragmentTransition.setViewVisibility(this.val$exitingViews, 4);
            }
         });
      }

   }

   private static FragmentTransitionImpl resolveSupportImpl() {
      try {
         FragmentTransitionImpl var0 = (FragmentTransitionImpl)Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor().newInstance();
         return var0;
      } catch (Exception var1) {
         return null;
      }
   }

   static void retainValues(ArrayMap var0, ArrayMap var1) {
      for(int var2 = var0.size() - 1; var2 >= 0; --var2) {
         if (!var1.containsKey((String)var0.valueAt(var2))) {
            var0.removeAt(var2);
         }
      }

   }

   private static void scheduleTargetChange(FragmentTransitionImpl var0, ViewGroup var1, Fragment var2, View var3, ArrayList var4, Object var5, ArrayList var6, Object var7, ArrayList var8) {
      OneShotPreDrawListener.add(var1, new Runnable(var5, var0, var3, var2, var4, var6, var8, var7) {
         final Object val$enterTransition;
         final ArrayList val$enteringViews;
         final Object val$exitTransition;
         final ArrayList val$exitingViews;
         final FragmentTransitionImpl val$impl;
         final Fragment val$inFragment;
         final View val$nonExistentView;
         final ArrayList val$sharedElementsIn;

         {
            this.val$enterTransition = var1;
            this.val$impl = var2;
            this.val$nonExistentView = var3;
            this.val$inFragment = var4;
            this.val$sharedElementsIn = var5;
            this.val$enteringViews = var6;
            this.val$exitingViews = var7;
            this.val$exitTransition = var8;
         }

         public void run() {
            Object var1 = this.val$enterTransition;
            ArrayList var2;
            if (var1 != null) {
               this.val$impl.removeTarget(var1, this.val$nonExistentView);
               var2 = FragmentTransition.configureEnteringExitingViews(this.val$impl, this.val$enterTransition, this.val$inFragment, this.val$sharedElementsIn, this.val$nonExistentView);
               this.val$enteringViews.addAll(var2);
            }

            if (this.val$exitingViews != null) {
               if (this.val$exitTransition != null) {
                  var2 = new ArrayList();
                  var2.add(this.val$nonExistentView);
                  this.val$impl.replaceTargets(this.val$exitTransition, this.val$exitingViews, var2);
               }

               this.val$exitingViews.clear();
               this.val$exitingViews.add(this.val$nonExistentView);
            }

         }
      });
   }

   private static void setOutEpicenter(FragmentTransitionImpl var0, Object var1, Object var2, ArrayMap var3, boolean var4, BackStackRecord var5) {
      if (var5.mSharedElementSourceNames != null && !var5.mSharedElementSourceNames.isEmpty()) {
         String var6;
         if (var4) {
            var6 = (String)var5.mSharedElementTargetNames.get(0);
         } else {
            var6 = (String)var5.mSharedElementSourceNames.get(0);
         }

         View var7 = (View)var3.get(var6);
         var0.setEpicenter(var1, var7);
         if (var2 != null) {
            var0.setEpicenter(var2, var7);
         }
      }

   }

   static void setViewVisibility(ArrayList var0, int var1) {
      if (var0 != null) {
         for(int var2 = var0.size() - 1; var2 >= 0; --var2) {
            ((View)var0.get(var2)).setVisibility(var1);
         }

      }
   }

   static void startTransitions(Context var0, FragmentContainer var1, ArrayList var2, ArrayList var3, int var4, int var5, boolean var6, Callback var7) {
      SparseArray var11 = new SparseArray();

      int var8;
      for(var8 = var4; var8 < var5; ++var8) {
         BackStackRecord var12 = (BackStackRecord)var2.get(var8);
         if ((Boolean)var3.get(var8)) {
            calculatePopFragments(var12, var11, var6);
         } else {
            calculateFragments(var12, var11, var6);
         }
      }

      if (var11.size() != 0) {
         View var13 = new View(var0);
         int var9 = var11.size();

         for(var8 = 0; var8 < var9; ++var8) {
            int var10 = var11.keyAt(var8);
            ArrayMap var14 = calculateNameOverrides(var10, var2, var3, var4, var5);
            FragmentContainerTransition var16 = (FragmentContainerTransition)var11.valueAt(var8);
            if (var1.onHasView()) {
               ViewGroup var15 = (ViewGroup)var1.onFindViewById(var10);
               if (var15 != null) {
                  if (var6) {
                     configureTransitionsReordered(var15, var16, var13, var14, var7);
                  } else {
                     configureTransitionsOrdered(var15, var16, var13, var14, var7);
                  }
               }
            }
         }
      }

   }

   static boolean supportsTransition() {
      boolean var0;
      if (PLATFORM_IMPL == null && SUPPORT_IMPL == null) {
         var0 = false;
      } else {
         var0 = true;
      }

      return var0;
   }

   interface Callback {
      void onComplete(Fragment var1, CancellationSignal var2);

      void onStart(Fragment var1, CancellationSignal var2);
   }

   static class FragmentContainerTransition {
      public Fragment firstOut;
      public boolean firstOutIsPop;
      public BackStackRecord firstOutTransaction;
      public Fragment lastIn;
      public boolean lastInIsPop;
      public BackStackRecord lastInTransaction;
   }
}
