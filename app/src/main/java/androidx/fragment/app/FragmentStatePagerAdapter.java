package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
   public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
   @Deprecated
   public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;
   private static final boolean DEBUG = false;
   private static final String TAG = "FragmentStatePagerAdapt";
   private final int mBehavior;
   private FragmentTransaction mCurTransaction;
   private Fragment mCurrentPrimaryItem;
   private boolean mExecutingFinishUpdate;
   private final FragmentManager mFragmentManager;
   private ArrayList mFragments;
   private ArrayList mSavedState;

   @Deprecated
   public FragmentStatePagerAdapter(FragmentManager var1) {
      this(var1, 0);
   }

   public FragmentStatePagerAdapter(FragmentManager var1, int var2) {
      this.mCurTransaction = null;
      this.mSavedState = new ArrayList();
      this.mFragments = new ArrayList();
      this.mCurrentPrimaryItem = null;
      this.mFragmentManager = var1;
      this.mBehavior = var2;
   }

   public void destroyItem(ViewGroup var1, int var2, Object var3) {
      Fragment var6 = (Fragment)var3;
      if (this.mCurTransaction == null) {
         this.mCurTransaction = this.mFragmentManager.beginTransaction();
      }

      while(this.mSavedState.size() <= var2) {
         this.mSavedState.add((Object)null);
      }

      ArrayList var4 = this.mSavedState;
      Fragment.SavedState var5;
      if (var6.isAdded()) {
         var5 = this.mFragmentManager.saveFragmentInstanceState(var6);
      } else {
         var5 = null;
      }

      var4.set(var2, var5);
      this.mFragments.set(var2, (Object)null);
      this.mCurTransaction.remove(var6);
      if (var6.equals(this.mCurrentPrimaryItem)) {
         this.mCurrentPrimaryItem = null;
      }

   }

   public void finishUpdate(ViewGroup var1) {
      FragmentTransaction var4 = this.mCurTransaction;
      if (var4 != null) {
         if (!this.mExecutingFinishUpdate) {
            try {
               this.mExecutingFinishUpdate = true;
               var4.commitNowAllowingStateLoss();
            } finally {
               this.mExecutingFinishUpdate = false;
            }
         }

         this.mCurTransaction = null;
      }

   }

   public abstract Fragment getItem(int var1);

   public Object instantiateItem(ViewGroup var1, int var2) {
      Fragment var3;
      if (this.mFragments.size() > var2) {
         var3 = (Fragment)this.mFragments.get(var2);
         if (var3 != null) {
            return var3;
         }
      }

      if (this.mCurTransaction == null) {
         this.mCurTransaction = this.mFragmentManager.beginTransaction();
      }

      var3 = this.getItem(var2);
      if (this.mSavedState.size() > var2) {
         Fragment.SavedState var4 = (Fragment.SavedState)this.mSavedState.get(var2);
         if (var4 != null) {
            var3.setInitialSavedState(var4);
         }
      }

      while(this.mFragments.size() <= var2) {
         this.mFragments.add((Object)null);
      }

      var3.setMenuVisibility(false);
      if (this.mBehavior == 0) {
         var3.setUserVisibleHint(false);
      }

      this.mFragments.set(var2, var3);
      this.mCurTransaction.add(var1.getId(), var3);
      if (this.mBehavior == 1) {
         this.mCurTransaction.setMaxLifecycle(var3, Lifecycle.State.STARTED);
      }

      return var3;
   }

   public boolean isViewFromObject(View var1, Object var2) {
      boolean var3;
      if (((Fragment)var2).getView() == var1) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public void restoreState(Parcelable var1, ClassLoader var2) {
      if (var1 != null) {
         Bundle var6 = (Bundle)var1;
         var6.setClassLoader(var2);
         Parcelable[] var7 = var6.getParcelableArray("states");
         this.mSavedState.clear();
         this.mFragments.clear();
         int var3;
         if (var7 != null) {
            for(var3 = 0; var3 < var7.length; ++var3) {
               this.mSavedState.add((Fragment.SavedState)var7[var3]);
            }
         }

         Iterator var5 = var6.keySet().iterator();

         while(true) {
            while(true) {
               String var4;
               do {
                  if (!var5.hasNext()) {
                     return;
                  }

                  var4 = (String)var5.next();
               } while(!var4.startsWith("f"));

               var3 = Integer.parseInt(var4.substring(1));
               Fragment var8 = this.mFragmentManager.getFragment(var6, var4);
               if (var8 != null) {
                  while(this.mFragments.size() <= var3) {
                     this.mFragments.add((Object)null);
                  }

                  var8.setMenuVisibility(false);
                  this.mFragments.set(var3, var8);
               } else {
                  Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + var4);
               }
            }
         }
      }
   }

   public Parcelable saveState() {
      Bundle var3;
      if (this.mSavedState.size() > 0) {
         var3 = new Bundle();
         Fragment.SavedState[] var2 = new Fragment.SavedState[this.mSavedState.size()];
         this.mSavedState.toArray(var2);
         var3.putParcelableArray("states", var2);
      } else {
         var3 = null;
      }

      Bundle var5;
      for(int var1 = 0; var1 < this.mFragments.size(); var3 = var5) {
         Fragment var4 = (Fragment)this.mFragments.get(var1);
         var5 = var3;
         if (var4 != null) {
            var5 = var3;
            if (var4.isAdded()) {
               var5 = var3;
               if (var3 == null) {
                  var5 = new Bundle();
               }

               String var6 = "f" + var1;
               this.mFragmentManager.putFragment(var5, var6, var4);
            }
         }

         ++var1;
      }

      return var3;
   }

   public void setPrimaryItem(ViewGroup var1, int var2, Object var3) {
      Fragment var4 = (Fragment)var3;
      Fragment var5 = this.mCurrentPrimaryItem;
      if (var4 != var5) {
         if (var5 != null) {
            var5.setMenuVisibility(false);
            if (this.mBehavior == 1) {
               if (this.mCurTransaction == null) {
                  this.mCurTransaction = this.mFragmentManager.beginTransaction();
               }

               this.mCurTransaction.setMaxLifecycle(this.mCurrentPrimaryItem, Lifecycle.State.STARTED);
            } else {
               this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }
         }

         var4.setMenuVisibility(true);
         if (this.mBehavior == 1) {
            if (this.mCurTransaction == null) {
               this.mCurTransaction = this.mFragmentManager.beginTransaction();
            }

            this.mCurTransaction.setMaxLifecycle(var4, Lifecycle.State.RESUMED);
         } else {
            var4.setUserVisibleHint(true);
         }

         this.mCurrentPrimaryItem = var4;
      }

   }

   public void startUpdate(ViewGroup var1) {
      if (var1.getId() == -1) {
         throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
      }
   }
}
