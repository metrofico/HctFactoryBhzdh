package androidx.legacy.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;

@Deprecated
public abstract class FragmentPagerAdapter extends PagerAdapter {
   private static final boolean DEBUG = false;
   private static final String TAG = "FragmentPagerAdapter";
   private FragmentTransaction mCurTransaction = null;
   private Fragment mCurrentPrimaryItem = null;
   private final FragmentManager mFragmentManager;

   @Deprecated
   public FragmentPagerAdapter(FragmentManager var1) {
      this.mFragmentManager = var1;
   }

   private static String makeFragmentName(int var0, long var1) {
      return "android:switcher:" + var0 + ":" + var1;
   }

   @Deprecated
   public void destroyItem(ViewGroup var1, int var2, Object var3) {
      if (this.mCurTransaction == null) {
         this.mCurTransaction = this.mFragmentManager.beginTransaction();
      }

      this.mCurTransaction.detach((Fragment)var3);
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
   public long getItemId(int var1) {
      return (long)var1;
   }

   @Deprecated
   public Object instantiateItem(ViewGroup var1, int var2) {
      if (this.mCurTransaction == null) {
         this.mCurTransaction = this.mFragmentManager.beginTransaction();
      }

      long var3 = this.getItemId(var2);
      String var5 = makeFragmentName(var1.getId(), var3);
      Fragment var6 = this.mFragmentManager.findFragmentByTag(var5);
      Fragment var7;
      if (var6 != null) {
         this.mCurTransaction.attach(var6);
         var7 = var6;
      } else {
         var6 = this.getItem(var2);
         this.mCurTransaction.add(var1.getId(), var6, makeFragmentName(var1.getId(), var3));
         var7 = var6;
      }

      if (var7 != this.mCurrentPrimaryItem) {
         var7.setMenuVisibility(false);
         FragmentCompat.setUserVisibleHint(var7, false);
      }

      return var7;
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
   }

   @Deprecated
   public Parcelable saveState() {
      return null;
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
