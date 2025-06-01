package androidx.fragment.app;

import android.util.Log;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class FragmentStore {
   private static final String TAG = "FragmentManager";
   private final HashMap mActive = new HashMap();
   private final ArrayList mAdded = new ArrayList();
   private FragmentManagerViewModel mNonConfig;

   void addFragment(Fragment param1) {
      // $FF: Couldn't be decompiled
   }

   void burpActive() {
      this.mActive.values().removeAll(Collections.singleton((Object)null));
   }

   boolean containsActiveFragment(String var1) {
      boolean var2;
      if (this.mActive.get(var1) != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   void dispatchStateChange(int var1) {
      Iterator var2 = this.mActive.values().iterator();

      while(var2.hasNext()) {
         FragmentStateManager var3 = (FragmentStateManager)var2.next();
         if (var3 != null) {
            var3.setFragmentManagerState(var1);
         }
      }

   }

   void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      String var7 = var1 + "    ";
      if (!this.mActive.isEmpty()) {
         var3.print(var1);
         var3.println("Active Fragments:");
         Iterator var8 = this.mActive.values().iterator();

         while(var8.hasNext()) {
            FragmentStateManager var9 = (FragmentStateManager)var8.next();
            var3.print(var1);
            if (var9 != null) {
               Fragment var11 = var9.getFragment();
               var3.println(var11);
               var11.dump(var7, var2, var3, var4);
            } else {
               var3.println("null");
            }
         }
      }

      int var6 = this.mAdded.size();
      if (var6 > 0) {
         var3.print(var1);
         var3.println("Added Fragments:");

         for(int var5 = 0; var5 < var6; ++var5) {
            Fragment var10 = (Fragment)this.mAdded.get(var5);
            var3.print(var1);
            var3.print("  #");
            var3.print(var5);
            var3.print(": ");
            var3.println(var10.toString());
         }
      }

   }

   Fragment findActiveFragment(String var1) {
      FragmentStateManager var2 = (FragmentStateManager)this.mActive.get(var1);
      return var2 != null ? var2.getFragment() : null;
   }

   Fragment findFragmentById(int var1) {
      for(int var2 = this.mAdded.size() - 1; var2 >= 0; --var2) {
         Fragment var3 = (Fragment)this.mAdded.get(var2);
         if (var3 != null && var3.mFragmentId == var1) {
            return var3;
         }
      }

      Iterator var5 = this.mActive.values().iterator();

      while(var5.hasNext()) {
         FragmentStateManager var4 = (FragmentStateManager)var5.next();
         if (var4 != null) {
            Fragment var6 = var4.getFragment();
            if (var6.mFragmentId == var1) {
               return var6;
            }
         }
      }

      return null;
   }

   Fragment findFragmentByTag(String var1) {
      if (var1 != null) {
         for(int var2 = this.mAdded.size() - 1; var2 >= 0; --var2) {
            Fragment var3 = (Fragment)this.mAdded.get(var2);
            if (var3 != null && var1.equals(var3.mTag)) {
               return var3;
            }
         }
      }

      if (var1 != null) {
         Iterator var5 = this.mActive.values().iterator();

         while(var5.hasNext()) {
            FragmentStateManager var4 = (FragmentStateManager)var5.next();
            if (var4 != null) {
               Fragment var6 = var4.getFragment();
               if (var1.equals(var6.mTag)) {
                  return var6;
               }
            }
         }
      }

      return null;
   }

   Fragment findFragmentByWho(String var1) {
      Iterator var2 = this.mActive.values().iterator();

      while(var2.hasNext()) {
         FragmentStateManager var3 = (FragmentStateManager)var2.next();
         if (var3 != null) {
            Fragment var4 = var3.getFragment().findFragmentByWho(var1);
            if (var4 != null) {
               return var4;
            }
         }
      }

      return null;
   }

   int findFragmentIndexInContainer(Fragment var1) {
      ViewGroup var5 = var1.mContainer;
      if (var5 == null) {
         return -1;
      } else {
         int var4 = this.mAdded.indexOf(var1);
         int var3 = var4 - 1;

         while(true) {
            int var2 = var4;
            if (var3 < 0) {
               do {
                  ++var2;
                  if (var2 >= this.mAdded.size()) {
                     return -1;
                  }

                  var1 = (Fragment)this.mAdded.get(var2);
               } while(var1.mContainer != var5 || var1.mView == null);

               return var5.indexOfChild(var1.mView);
            }

            var1 = (Fragment)this.mAdded.get(var3);
            if (var1.mContainer == var5 && var1.mView != null) {
               return var5.indexOfChild(var1.mView) + 1;
            }

            --var3;
         }
      }
   }

   int getActiveFragmentCount() {
      return this.mActive.size();
   }

   List getActiveFragmentStateManagers() {
      ArrayList var3 = new ArrayList();
      Iterator var1 = this.mActive.values().iterator();

      while(var1.hasNext()) {
         FragmentStateManager var2 = (FragmentStateManager)var1.next();
         if (var2 != null) {
            var3.add(var2);
         }
      }

      return var3;
   }

   List getActiveFragments() {
      ArrayList var2 = new ArrayList();
      Iterator var1 = this.mActive.values().iterator();

      while(var1.hasNext()) {
         FragmentStateManager var3 = (FragmentStateManager)var1.next();
         if (var3 != null) {
            var2.add(var3.getFragment());
         } else {
            var2.add((Object)null);
         }
      }

      return var2;
   }

   FragmentStateManager getFragmentStateManager(String var1) {
      return (FragmentStateManager)this.mActive.get(var1);
   }

   List getFragments() {
      // $FF: Couldn't be decompiled
   }

   FragmentManagerViewModel getNonConfig() {
      return this.mNonConfig;
   }

   void makeActive(FragmentStateManager var1) {
      Fragment var2 = var1.getFragment();
      if (!this.containsActiveFragment(var2.mWho)) {
         this.mActive.put(var2.mWho, var1);
         if (var2.mRetainInstanceChangedWhileDetached) {
            if (var2.mRetainInstance) {
               this.mNonConfig.addRetainedFragment(var2);
            } else {
               this.mNonConfig.removeRetainedFragment(var2);
            }

            var2.mRetainInstanceChangedWhileDetached = false;
         }

         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + var2);
         }

      }
   }

   void makeInactive(FragmentStateManager var1) {
      Fragment var2 = var1.getFragment();
      if (var2.mRetainInstance) {
         this.mNonConfig.removeRetainedFragment(var2);
      }

      if ((FragmentStateManager)this.mActive.put(var2.mWho, (Object)null) != null) {
         if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + var2);
         }

      }
   }

   void moveToExpectedState() {
      Iterator var2 = this.mAdded.iterator();

      while(var2.hasNext()) {
         Fragment var3 = (Fragment)var2.next();
         FragmentStateManager var6 = (FragmentStateManager)this.mActive.get(var3.mWho);
         if (var6 != null) {
            var6.moveToExpectedState();
         }
      }

      Iterator var7 = this.mActive.values().iterator();

      while(true) {
         FragmentStateManager var5;
         do {
            if (!var7.hasNext()) {
               return;
            }

            var5 = (FragmentStateManager)var7.next();
         } while(var5 == null);

         var5.moveToExpectedState();
         Fragment var4 = var5.getFragment();
         boolean var1;
         if (var4.mRemoving && !var4.isInBackStack()) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            this.makeInactive(var5);
         }
      }
   }

   void removeFragment(Fragment param1) {
      // $FF: Couldn't be decompiled
   }

   void resetActiveFragments() {
      this.mActive.clear();
   }

   void restoreAddedFragments(List var1) {
      this.mAdded.clear();
      Fragment var4;
      if (var1 != null) {
         for(Iterator var2 = var1.iterator(); var2.hasNext(); this.addFragment(var4)) {
            String var3 = (String)var2.next();
            var4 = this.findActiveFragment(var3);
            if (var4 == null) {
               throw new IllegalStateException("No instantiated fragment for (" + var3 + ")");
            }

            if (FragmentManager.isLoggingEnabled(2)) {
               Log.v("FragmentManager", "restoreSaveState: added (" + var3 + "): " + var4);
            }
         }
      }

   }

   ArrayList saveActiveFragments() {
      ArrayList var3 = new ArrayList(this.mActive.size());
      Iterator var1 = this.mActive.values().iterator();

      while(var1.hasNext()) {
         FragmentStateManager var4 = (FragmentStateManager)var1.next();
         if (var4 != null) {
            Fragment var2 = var4.getFragment();
            FragmentState var5 = var4.saveState();
            var3.add(var5);
            if (FragmentManager.isLoggingEnabled(2)) {
               Log.v("FragmentManager", "Saved state of " + var2 + ": " + var5.mSavedFragmentState);
            }
         }
      }

      return var3;
   }

   ArrayList saveAddedFragments() {
      ArrayList var1 = this.mAdded;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label313: {
         try {
            if (this.mAdded.isEmpty()) {
               return null;
            }
         } catch (Throwable var35) {
            var10000 = var35;
            var10001 = false;
            break label313;
         }

         ArrayList var3;
         Iterator var4;
         try {
            var3 = new ArrayList(this.mAdded.size());
            var4 = this.mAdded.iterator();
         } catch (Throwable var33) {
            var10000 = var33;
            var10001 = false;
            break label313;
         }

         while(true) {
            try {
               if (!var4.hasNext()) {
                  break;
               }

               Fragment var5 = (Fragment)var4.next();
               var3.add(var5.mWho);
               if (FragmentManager.isLoggingEnabled(2)) {
                  StringBuilder var2 = new StringBuilder();
                  Log.v("FragmentManager", var2.append("saveAllState: adding fragment (").append(var5.mWho).append("): ").append(var5).toString());
               }
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               break label313;
            }
         }

         label291:
         try {
            return var3;
         } catch (Throwable var32) {
            var10000 = var32;
            var10001 = false;
            break label291;
         }
      }

      while(true) {
         Throwable var36 = var10000;

         try {
            throw var36;
         } catch (Throwable var31) {
            var10000 = var31;
            var10001 = false;
            continue;
         }
      }
   }

   void setNonConfig(FragmentManagerViewModel var1) {
      this.mNonConfig = var1;
   }
}
