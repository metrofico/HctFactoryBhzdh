package androidx.legacy.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
   private static final boolean DEBUG = false;
   private static final String TAG = "FragStatePagerAdapter";
   private FragmentTransaction mCurTransaction = null;
   private Fragment mCurrentPrimaryItem = null;
   private final FragmentManager mFragmentManager;
   private ArrayList mFragments = new ArrayList();
   private ArrayList mSavedState = new ArrayList();

   @Deprecated
   public FragmentStatePagerAdapter(FragmentManager var1) {
      this.mFragmentManager = var1;
   }

   @Deprecated
   public void destroyItem(ViewGroup var1, int var2, Object var3) {
      Fragment var4 = (Fragment)var3;
      if (this.mCurTransaction == null) {
         this.mCurTransaction = this.mFragmentManager.beginTransaction();
      }

      while(this.mSavedState.size() <= var2) {
         this.mSavedState.add((Object)null);
      }

      ArrayList var6 = this.mSavedState;
      Fragment.SavedState var5;
      if (var4.isAdded()) {
         var5 = this.mFragmentManager.saveFragmentInstanceState(var4);
      } else {
         var5 = null;
      }

      var6.set(var2, var5);
      this.mFragments.set(var2, (Object)null);
      this.mCurTransaction.remove(var4);
   }

   @Deprecated
   public void finishUpdate(ViewGroup var1) {
      FragmentTransaction var2 = this.mCurTransaction;
      if (var2 != null) {
         var2.commitAllowingStateLoss();
         this.mCurTransaction = null;
         this.mFragmentManager.executePendingTransactions();
      }

   }

   @Deprecated
   public abstract Fragment getItem(int var1);

   @Deprecated
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
      FragmentCompat.setUserVisibleHint(var3, false);
      this.mFragments.set(var2, var3);
      this.mCurTransaction.add(var1.getId(), var3);
      return var3;
   }

   @Deprecated
   public boolean isViewFromObject(View var1, Object var2) {
      boolean var3;
      if (((Fragment)var2).getView() == var1) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   @Deprecated
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

                  FragmentCompat.setMenuVisibility(var8, false);
                  this.mFragments.set(var3, var8);
               } else {
                  Log.w("FragStatePagerAdapter", "Bad fragment at key " + var4);
               }
            }
         }
      }
   }

   @Deprecated
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

   @Deprecated
   public void setPrimaryItem(ViewGroup var1, int var2, Object var3) {
      Fragment var5 = (Fragment)var3;
      Fragment var4 = this.mCurrentPrimaryItem;
      if (var5 != var4) {
         if (var4 != null) {
            var4.setMenuVisibility(false);
            FragmentCompat.setUserVisibleHint(this.mCurrentPrimaryItem, false);
         }

         if (var5 != null) {
            var5.setMenuVisibility(true);
            FragmentCompat.setUserVisibleHint(var5, true);
         }

         this.mCurrentPrimaryItem = var5;
      }

   }

   @Deprecated
   public void startUpdate(ViewGroup var1) {
      if (var1.getId() == -1) {
         throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
      }
   }
}
