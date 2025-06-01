package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import java.io.PrintWriter;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManager.OpGenerator {
   private static final String TAG = "FragmentManager";
   boolean mCommitted;
   int mIndex;
   final FragmentManager mManager;

   BackStackRecord(FragmentManager var1) {
      FragmentFactory var3 = var1.getFragmentFactory();
      ClassLoader var2;
      if (var1.getHost() != null) {
         var2 = var1.getHost().getContext().getClassLoader();
      } else {
         var2 = null;
      }

      super(var3, var2);
      this.mIndex = -1;
      this.mManager = var1;
   }

   private static boolean isFragmentPostponed(FragmentTransaction.Op var0) {
      Fragment var2 = var0.mFragment;
      boolean var1;
      if (var2 != null && var2.mAdded && var2.mView != null && !var2.mDetached && !var2.mHidden && var2.isPostponed()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   void bumpBackStackNesting(int var1) {
      if (this.mAddToBackStack) {
         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Bump nesting in " + this + " by " + var1);
         }

         int var3 = this.mOps.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            FragmentTransaction.Op var5 = (FragmentTransaction.Op)this.mOps.get(var2);
            if (var5.mFragment != null) {
               Fragment var4 = var5.mFragment;
               var4.mBackStackNesting += var1;
               if (FragmentManager.isLoggingEnabled(2)) {
                  Log.v("FragmentManager", "Bump nesting of " + var5.mFragment + " to " + var5.mFragment.mBackStackNesting);
               }
            }
         }

      }
   }

   public int commit() {
      return this.commitInternal(false);
   }

   public int commitAllowingStateLoss() {
      return this.commitInternal(true);
   }

   int commitInternal(boolean var1) {
      if (!this.mCommitted) {
         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter var2 = new PrintWriter(new LogWriter("FragmentManager"));
            this.dump("  ", var2);
            var2.close();
         }

         this.mCommitted = true;
         if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex();
         } else {
            this.mIndex = -1;
         }

         this.mManager.enqueueAction(this, var1);
         return this.mIndex;
      } else {
         throw new IllegalStateException("commit already called");
      }
   }

   public void commitNow() {
      this.disallowAddToBackStack();
      this.mManager.execSingleAction(this, false);
   }

   public void commitNowAllowingStateLoss() {
      this.disallowAddToBackStack();
      this.mManager.execSingleAction(this, true);
   }

   public FragmentTransaction detach(Fragment var1) {
      if (var1.mFragmentManager != null && var1.mFragmentManager != this.mManager) {
         throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + var1.toString() + " is already attached to a FragmentManager.");
      } else {
         return super.detach(var1);
      }
   }

   void doAddOp(int var1, Fragment var2, String var3, int var4) {
      super.doAddOp(var1, var2, var3, var4);
      var2.mFragmentManager = this.mManager;
   }

   public void dump(String var1, PrintWriter var2) {
      this.dump(var1, var2, true);
   }

   public void dump(String var1, PrintWriter var2, boolean var3) {
      if (var3) {
         var2.print(var1);
         var2.print("mName=");
         var2.print(this.mName);
         var2.print(" mIndex=");
         var2.print(this.mIndex);
         var2.print(" mCommitted=");
         var2.println(this.mCommitted);
         if (this.mTransition != 0) {
            var2.print(var1);
            var2.print("mTransition=#");
            var2.print(Integer.toHexString(this.mTransition));
         }

         if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
            var2.print(var1);
            var2.print("mEnterAnim=#");
            var2.print(Integer.toHexString(this.mEnterAnim));
            var2.print(" mExitAnim=#");
            var2.println(Integer.toHexString(this.mExitAnim));
         }

         if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
            var2.print(var1);
            var2.print("mPopEnterAnim=#");
            var2.print(Integer.toHexString(this.mPopEnterAnim));
            var2.print(" mPopExitAnim=#");
            var2.println(Integer.toHexString(this.mPopExitAnim));
         }

         if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
            var2.print(var1);
            var2.print("mBreadCrumbTitleRes=#");
            var2.print(Integer.toHexString(this.mBreadCrumbTitleRes));
            var2.print(" mBreadCrumbTitleText=");
            var2.println(this.mBreadCrumbTitleText);
         }

         if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
            var2.print(var1);
            var2.print("mBreadCrumbShortTitleRes=#");
            var2.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
            var2.print(" mBreadCrumbShortTitleText=");
            var2.println(this.mBreadCrumbShortTitleText);
         }
      }

      if (!this.mOps.isEmpty()) {
         var2.print(var1);
         var2.println("Operations:");
         int var5 = this.mOps.size();

         for(int var4 = 0; var4 < var5; ++var4) {
            FragmentTransaction.Op var7 = (FragmentTransaction.Op)this.mOps.get(var4);
            String var6;
            switch (var7.mCmd) {
               case 0:
                  var6 = "NULL";
                  break;
               case 1:
                  var6 = "ADD";
                  break;
               case 2:
                  var6 = "REPLACE";
                  break;
               case 3:
                  var6 = "REMOVE";
                  break;
               case 4:
                  var6 = "HIDE";
                  break;
               case 5:
                  var6 = "SHOW";
                  break;
               case 6:
                  var6 = "DETACH";
                  break;
               case 7:
                  var6 = "ATTACH";
                  break;
               case 8:
                  var6 = "SET_PRIMARY_NAV";
                  break;
               case 9:
                  var6 = "UNSET_PRIMARY_NAV";
                  break;
               case 10:
                  var6 = "OP_SET_MAX_LIFECYCLE";
                  break;
               default:
                  var6 = "cmd=" + var7.mCmd;
            }

            var2.print(var1);
            var2.print("  Op #");
            var2.print(var4);
            var2.print(": ");
            var2.print(var6);
            var2.print(" ");
            var2.println(var7.mFragment);
            if (var3) {
               if (var7.mEnterAnim != 0 || var7.mExitAnim != 0) {
                  var2.print(var1);
                  var2.print("enterAnim=#");
                  var2.print(Integer.toHexString(var7.mEnterAnim));
                  var2.print(" exitAnim=#");
                  var2.println(Integer.toHexString(var7.mExitAnim));
               }

               if (var7.mPopEnterAnim != 0 || var7.mPopExitAnim != 0) {
                  var2.print(var1);
                  var2.print("popEnterAnim=#");
                  var2.print(Integer.toHexString(var7.mPopEnterAnim));
                  var2.print(" popExitAnim=#");
                  var2.println(Integer.toHexString(var7.mPopExitAnim));
               }
            }
         }
      }

   }

   void executeOps() {
      int var2 = this.mOps.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         FragmentTransaction.Op var3 = (FragmentTransaction.Op)this.mOps.get(var1);
         Fragment var4 = var3.mFragment;
         if (var4 != null) {
            var4.setPopDirection(false);
            var4.setNextTransition(this.mTransition);
            var4.setSharedElementNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames);
         }

         switch (var3.mCmd) {
            case 1:
               var4.setAnimations(var3.mEnterAnim, var3.mExitAnim, var3.mPopEnterAnim, var3.mPopExitAnim);
               this.mManager.setExitAnimationOrder(var4, false);
               this.mManager.addFragment(var4);
               break;
            case 2:
            default:
               throw new IllegalArgumentException("Unknown cmd: " + var3.mCmd);
            case 3:
               var4.setAnimations(var3.mEnterAnim, var3.mExitAnim, var3.mPopEnterAnim, var3.mPopExitAnim);
               this.mManager.removeFragment(var4);
               break;
            case 4:
               var4.setAnimations(var3.mEnterAnim, var3.mExitAnim, var3.mPopEnterAnim, var3.mPopExitAnim);
               this.mManager.hideFragment(var4);
               break;
            case 5:
               var4.setAnimations(var3.mEnterAnim, var3.mExitAnim, var3.mPopEnterAnim, var3.mPopExitAnim);
               this.mManager.setExitAnimationOrder(var4, false);
               this.mManager.showFragment(var4);
               break;
            case 6:
               var4.setAnimations(var3.mEnterAnim, var3.mExitAnim, var3.mPopEnterAnim, var3.mPopExitAnim);
               this.mManager.detachFragment(var4);
               break;
            case 7:
               var4.setAnimations(var3.mEnterAnim, var3.mExitAnim, var3.mPopEnterAnim, var3.mPopExitAnim);
               this.mManager.setExitAnimationOrder(var4, false);
               this.mManager.attachFragment(var4);
               break;
            case 8:
               this.mManager.setPrimaryNavigationFragment(var4);
               break;
            case 9:
               this.mManager.setPrimaryNavigationFragment((Fragment)null);
               break;
            case 10:
               this.mManager.setMaxLifecycle(var4, var3.mCurrentMaxState);
         }

         if (!this.mReorderingAllowed && var3.mCmd != 1 && var4 != null && !FragmentManager.USE_STATE_MANAGER) {
            this.mManager.moveFragmentToExpectedState(var4);
         }
      }

      if (!this.mReorderingAllowed && !FragmentManager.USE_STATE_MANAGER) {
         FragmentManager var5 = this.mManager;
         var5.moveToState(var5.mCurState, true);
      }

   }

   void executePopOps(boolean var1) {
      for(int var2 = this.mOps.size() - 1; var2 >= 0; --var2) {
         FragmentTransaction.Op var4 = (FragmentTransaction.Op)this.mOps.get(var2);
         Fragment var3 = var4.mFragment;
         if (var3 != null) {
            var3.setPopDirection(true);
            var3.setNextTransition(FragmentManager.reverseTransit(this.mTransition));
            var3.setSharedElementNames(this.mSharedElementTargetNames, this.mSharedElementSourceNames);
         }

         switch (var4.mCmd) {
            case 1:
               var3.setAnimations(var4.mEnterAnim, var4.mExitAnim, var4.mPopEnterAnim, var4.mPopExitAnim);
               this.mManager.setExitAnimationOrder(var3, true);
               this.mManager.removeFragment(var3);
               break;
            case 2:
            default:
               throw new IllegalArgumentException("Unknown cmd: " + var4.mCmd);
            case 3:
               var3.setAnimations(var4.mEnterAnim, var4.mExitAnim, var4.mPopEnterAnim, var4.mPopExitAnim);
               this.mManager.addFragment(var3);
               break;
            case 4:
               var3.setAnimations(var4.mEnterAnim, var4.mExitAnim, var4.mPopEnterAnim, var4.mPopExitAnim);
               this.mManager.showFragment(var3);
               break;
            case 5:
               var3.setAnimations(var4.mEnterAnim, var4.mExitAnim, var4.mPopEnterAnim, var4.mPopExitAnim);
               this.mManager.setExitAnimationOrder(var3, true);
               this.mManager.hideFragment(var3);
               break;
            case 6:
               var3.setAnimations(var4.mEnterAnim, var4.mExitAnim, var4.mPopEnterAnim, var4.mPopExitAnim);
               this.mManager.attachFragment(var3);
               break;
            case 7:
               var3.setAnimations(var4.mEnterAnim, var4.mExitAnim, var4.mPopEnterAnim, var4.mPopExitAnim);
               this.mManager.setExitAnimationOrder(var3, true);
               this.mManager.detachFragment(var3);
               break;
            case 8:
               this.mManager.setPrimaryNavigationFragment((Fragment)null);
               break;
            case 9:
               this.mManager.setPrimaryNavigationFragment(var3);
               break;
            case 10:
               this.mManager.setMaxLifecycle(var3, var4.mOldMaxState);
         }

         if (!this.mReorderingAllowed && var4.mCmd != 3 && var3 != null && !FragmentManager.USE_STATE_MANAGER) {
            this.mManager.moveFragmentToExpectedState(var3);
         }
      }

      if (!this.mReorderingAllowed && var1 && !FragmentManager.USE_STATE_MANAGER) {
         FragmentManager var5 = this.mManager;
         var5.moveToState(var5.mCurState, true);
      }

   }

   Fragment expandOps(ArrayList var1, Fragment var2) {
      int var4 = 0;

      Fragment var9;
      for(var9 = var2; var4 < this.mOps.size(); var9 = var2) {
         int var3;
         label57: {
            FragmentTransaction.Op var10 = (FragmentTransaction.Op)this.mOps.get(var4);
            var3 = var10.mCmd;
            if (var3 != 1) {
               if (var3 == 2) {
                  Fragment var11 = var10.mFragment;
                  int var8 = var11.mContainerId;
                  int var5 = var1.size() - 1;
                  boolean var6 = false;
                  var3 = var4;

                  boolean var7;
                  for(var2 = var9; var5 >= 0; var6 = var7) {
                     Fragment var12 = (Fragment)var1.get(var5);
                     var9 = var2;
                     var4 = var3;
                     var7 = var6;
                     if (var12.mContainerId == var8) {
                        if (var12 == var11) {
                           var7 = true;
                           var9 = var2;
                           var4 = var3;
                        } else {
                           var9 = var2;
                           var4 = var3;
                           if (var12 == var2) {
                              this.mOps.add(var3, new FragmentTransaction.Op(9, var12));
                              var4 = var3 + 1;
                              var9 = null;
                           }

                           FragmentTransaction.Op var13 = new FragmentTransaction.Op(3, var12);
                           var13.mEnterAnim = var10.mEnterAnim;
                           var13.mPopEnterAnim = var10.mPopEnterAnim;
                           var13.mExitAnim = var10.mExitAnim;
                           var13.mPopExitAnim = var10.mPopExitAnim;
                           this.mOps.add(var4, var13);
                           var1.remove(var12);
                           ++var4;
                           var7 = var6;
                        }
                     }

                     --var5;
                     var2 = var9;
                     var3 = var4;
                  }

                  if (var6) {
                     this.mOps.remove(var3);
                     --var3;
                  } else {
                     var10.mCmd = 1;
                     var1.add(var11);
                  }
                  break label57;
               }

               if (var3 == 3 || var3 == 6) {
                  var1.remove(var10.mFragment);
                  var2 = var9;
                  var3 = var4;
                  if (var10.mFragment == var9) {
                     this.mOps.add(var4, new FragmentTransaction.Op(9, var10.mFragment));
                     var3 = var4 + 1;
                     var2 = null;
                  }
                  break label57;
               }

               if (var3 != 7) {
                  if (var3 != 8) {
                     var2 = var9;
                     var3 = var4;
                  } else {
                     this.mOps.add(var4, new FragmentTransaction.Op(9, var9));
                     var3 = var4 + 1;
                     var2 = var10.mFragment;
                  }
                  break label57;
               }
            }

            var1.add(var10.mFragment);
            var3 = var4;
            var2 = var9;
         }

         var4 = var3 + 1;
      }

      return var9;
   }

   public boolean generateOps(ArrayList var1, ArrayList var2) {
      if (FragmentManager.isLoggingEnabled(2)) {
         Log.v("FragmentManager", "Run: " + this);
      }

      var1.add(this);
      var2.add(false);
      if (this.mAddToBackStack) {
         this.mManager.addBackStackState(this);
      }

      return true;
   }

   public CharSequence getBreadCrumbShortTitle() {
      return this.mBreadCrumbShortTitleRes != 0 ? this.mManager.getHost().getContext().getText(this.mBreadCrumbShortTitleRes) : this.mBreadCrumbShortTitleText;
   }

   public int getBreadCrumbShortTitleRes() {
      return this.mBreadCrumbShortTitleRes;
   }

   public CharSequence getBreadCrumbTitle() {
      return this.mBreadCrumbTitleRes != 0 ? this.mManager.getHost().getContext().getText(this.mBreadCrumbTitleRes) : this.mBreadCrumbTitleText;
   }

   public int getBreadCrumbTitleRes() {
      return this.mBreadCrumbTitleRes;
   }

   public int getId() {
      return this.mIndex;
   }

   public String getName() {
      return this.mName;
   }

   public FragmentTransaction hide(Fragment var1) {
      if (var1.mFragmentManager != null && var1.mFragmentManager != this.mManager) {
         throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + var1.toString() + " is already attached to a FragmentManager.");
      } else {
         return super.hide(var1);
      }
   }

   boolean interactsWith(int var1) {
      int var4 = this.mOps.size();

      for(int var2 = 0; var2 < var4; ++var2) {
         FragmentTransaction.Op var5 = (FragmentTransaction.Op)this.mOps.get(var2);
         int var3;
         if (var5.mFragment != null) {
            var3 = var5.mFragment.mContainerId;
         } else {
            var3 = 0;
         }

         if (var3 != 0 && var3 == var1) {
            return true;
         }
      }

      return false;
   }

   boolean interactsWith(ArrayList var1, int var2, int var3) {
      if (var3 == var2) {
         return false;
      } else {
         int var9 = this.mOps.size();
         int var6 = -1;

         int var7;
         for(int var5 = 0; var5 < var9; var6 = var7) {
            FragmentTransaction.Op var11 = (FragmentTransaction.Op)this.mOps.get(var5);
            int var4;
            if (var11.mFragment != null) {
               var4 = var11.mFragment.mContainerId;
            } else {
               var4 = 0;
            }

            var7 = var6;
            if (var4 != 0) {
               var7 = var6;
               if (var4 != var6) {
                  var6 = var2;

                  while(true) {
                     if (var6 >= var3) {
                        var7 = var4;
                        break;
                     }

                     BackStackRecord var13 = (BackStackRecord)var1.get(var6);
                     int var10 = var13.mOps.size();

                     for(var7 = 0; var7 < var10; ++var7) {
                        FragmentTransaction.Op var12 = (FragmentTransaction.Op)var13.mOps.get(var7);
                        int var8;
                        if (var12.mFragment != null) {
                           var8 = var12.mFragment.mContainerId;
                        } else {
                           var8 = 0;
                        }

                        if (var8 == var4) {
                           return true;
                        }
                     }

                     ++var6;
                  }
               }
            }

            ++var5;
         }

         return false;
      }
   }

   public boolean isEmpty() {
      return this.mOps.isEmpty();
   }

   boolean isPostponed() {
      for(int var1 = 0; var1 < this.mOps.size(); ++var1) {
         if (isFragmentPostponed((FragmentTransaction.Op)this.mOps.get(var1))) {
            return true;
         }
      }

      return false;
   }

   public FragmentTransaction remove(Fragment var1) {
      if (var1.mFragmentManager != null && var1.mFragmentManager != this.mManager) {
         throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + var1.toString() + " is already attached to a FragmentManager.");
      } else {
         return super.remove(var1);
      }
   }

   public void runOnCommitRunnables() {
      if (this.mCommitRunnables != null) {
         for(int var1 = 0; var1 < this.mCommitRunnables.size(); ++var1) {
            ((Runnable)this.mCommitRunnables.get(var1)).run();
         }

         this.mCommitRunnables = null;
      }

   }

   public FragmentTransaction setMaxLifecycle(Fragment var1, Lifecycle.State var2) {
      if (var1.mFragmentManager == this.mManager) {
         if (var2 == Lifecycle.State.INITIALIZED && var1.mState > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + var2 + " after the Fragment has been created");
         } else if (var2 != Lifecycle.State.DESTROYED) {
            return super.setMaxLifecycle(var1, var2);
         } else {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + var2 + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
         }
      } else {
         throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.mManager);
      }
   }

   void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener var1) {
      for(int var2 = 0; var2 < this.mOps.size(); ++var2) {
         FragmentTransaction.Op var3 = (FragmentTransaction.Op)this.mOps.get(var2);
         if (isFragmentPostponed(var3)) {
            var3.mFragment.setOnStartEnterTransitionListener(var1);
         }
      }

   }

   public FragmentTransaction setPrimaryNavigationFragment(Fragment var1) {
      if (var1 != null && var1.mFragmentManager != null && var1.mFragmentManager != this.mManager) {
         throw new IllegalStateException("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment " + var1.toString() + " is already attached to a FragmentManager.");
      } else {
         return super.setPrimaryNavigationFragment(var1);
      }
   }

   public FragmentTransaction show(Fragment var1) {
      if (var1.mFragmentManager != null && var1.mFragmentManager != this.mManager) {
         throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + var1.toString() + " is already attached to a FragmentManager.");
      } else {
         return super.show(var1);
      }
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder(128);
      var1.append("BackStackEntry{");
      var1.append(Integer.toHexString(System.identityHashCode(this)));
      if (this.mIndex >= 0) {
         var1.append(" #");
         var1.append(this.mIndex);
      }

      if (this.mName != null) {
         var1.append(" ");
         var1.append(this.mName);
      }

      var1.append("}");
      return var1.toString();
   }

   Fragment trackAddedFragmentsInPop(ArrayList var1, Fragment var2) {
      for(int var3 = this.mOps.size() - 1; var3 >= 0; --var3) {
         FragmentTransaction.Op var5;
         label40: {
            var5 = (FragmentTransaction.Op)this.mOps.get(var3);
            int var4 = var5.mCmd;
            if (var4 != 1) {
               if (var4 == 3) {
                  break label40;
               }

               switch (var4) {
                  case 6:
                     break label40;
                  case 7:
                     break;
                  case 8:
                     var2 = null;
                     continue;
                  case 9:
                     var2 = var5.mFragment;
                     continue;
                  case 10:
                     var5.mCurrentMaxState = var5.mOldMaxState;
                  default:
                     continue;
               }
            }

            var1.remove(var5.mFragment);
            continue;
         }

         var1.add(var5.mFragment);
      }

      return var2;
   }
}
