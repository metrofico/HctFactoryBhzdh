package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import java.util.ArrayList;

@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
   private boolean mAttached;
   private int mContainerId;
   private Context mContext;
   private FragmentManager mFragmentManager;
   private TabInfo mLastTab;
   private TabHost.OnTabChangeListener mOnTabChangeListener;
   private FrameLayout mRealTabContent;
   private final ArrayList mTabs = new ArrayList();

   @Deprecated
   public FragmentTabHost(Context var1) {
      super(var1, (AttributeSet)null);
      this.initFragmentTabHost(var1, (AttributeSet)null);
   }

   @Deprecated
   public FragmentTabHost(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.initFragmentTabHost(var1, var2);
   }

   private FragmentTransaction doTabChanged(String var1, FragmentTransaction var2) {
      TabInfo var3 = this.getTabInfoForTag(var1);
      FragmentTransaction var4 = var2;
      if (this.mLastTab != var3) {
         var4 = var2;
         if (var2 == null) {
            var4 = this.mFragmentManager.beginTransaction();
         }

         TabInfo var5 = this.mLastTab;
         if (var5 != null && var5.fragment != null) {
            var4.detach(this.mLastTab.fragment);
         }

         if (var3 != null) {
            if (var3.fragment == null) {
               var3.fragment = this.mFragmentManager.getFragmentFactory().instantiate(this.mContext.getClassLoader(), var3.clss.getName());
               var3.fragment.setArguments(var3.args);
               var4.add(this.mContainerId, var3.fragment, var3.tag);
            } else {
               var4.attach(var3.fragment);
            }
         }

         this.mLastTab = var3;
      }

      return var4;
   }

   private void ensureContent() {
      if (this.mRealTabContent == null) {
         FrameLayout var1 = (FrameLayout)this.findViewById(this.mContainerId);
         this.mRealTabContent = var1;
         if (var1 == null) {
            throw new IllegalStateException("No tab content FrameLayout found for id " + this.mContainerId);
         }
      }

   }

   private void ensureHierarchy(Context var1) {
      if (this.findViewById(16908307) == null) {
         LinearLayout var2 = new LinearLayout(var1);
         var2.setOrientation(1);
         this.addView(var2, new FrameLayout.LayoutParams(-1, -1));
         TabWidget var3 = new TabWidget(var1);
         var3.setId(16908307);
         var3.setOrientation(0);
         var2.addView(var3, new LinearLayout.LayoutParams(-1, -2, 0.0F));
         FrameLayout var5 = new FrameLayout(var1);
         var5.setId(16908305);
         var2.addView(var5, new LinearLayout.LayoutParams(0, 0, 0.0F));
         FrameLayout var4 = new FrameLayout(var1);
         this.mRealTabContent = var4;
         var4.setId(this.mContainerId);
         var2.addView(var4, new LinearLayout.LayoutParams(-1, 0, 1.0F));
      }

   }

   private TabInfo getTabInfoForTag(String var1) {
      int var3 = this.mTabs.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         TabInfo var4 = (TabInfo)this.mTabs.get(var2);
         if (var4.tag.equals(var1)) {
            return var4;
         }
      }

      return null;
   }

   private void initFragmentTabHost(Context var1, AttributeSet var2) {
      TypedArray var3 = var1.obtainStyledAttributes(var2, new int[]{16842995}, 0, 0);
      this.mContainerId = var3.getResourceId(0, 0);
      var3.recycle();
      super.setOnTabChangedListener(this);
   }

   @Deprecated
   public void addTab(TabHost.TabSpec var1, Class var2, Bundle var3) {
      var1.setContent(new DummyTabFactory(this.mContext));
      String var4 = var1.getTag();
      TabInfo var5 = new TabInfo(var4, var2, var3);
      if (this.mAttached) {
         var5.fragment = this.mFragmentManager.findFragmentByTag(var4);
         if (var5.fragment != null && !var5.fragment.isDetached()) {
            FragmentTransaction var6 = this.mFragmentManager.beginTransaction();
            var6.detach(var5.fragment);
            var6.commit();
         }
      }

      this.mTabs.add(var5);
      this.addTab(var1);
   }

   @Deprecated
   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      String var5 = this.getCurrentTabTag();
      int var2 = this.mTabs.size();
      FragmentTransaction var3 = null;

      FragmentTransaction var4;
      for(int var1 = 0; var1 < var2; var3 = var4) {
         TabInfo var6 = (TabInfo)this.mTabs.get(var1);
         var6.fragment = this.mFragmentManager.findFragmentByTag(var6.tag);
         var4 = var3;
         if (var6.fragment != null) {
            var4 = var3;
            if (!var6.fragment.isDetached()) {
               if (var6.tag.equals(var5)) {
                  this.mLastTab = var6;
                  var4 = var3;
               } else {
                  var4 = var3;
                  if (var3 == null) {
                     var4 = this.mFragmentManager.beginTransaction();
                  }

                  var4.detach(var6.fragment);
               }
            }
         }

         ++var1;
      }

      this.mAttached = true;
      var3 = this.doTabChanged(var5, var3);
      if (var3 != null) {
         var3.commit();
         this.mFragmentManager.executePendingTransactions();
      }

   }

   @Deprecated
   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.mAttached = false;
   }

   @Deprecated
   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.setCurrentTabByTag(var2.curTab);
      }
   }

   @Deprecated
   protected Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      var1.curTab = this.getCurrentTabTag();
      return var1;
   }

   @Deprecated
   public void onTabChanged(String var1) {
      if (this.mAttached) {
         FragmentTransaction var2 = this.doTabChanged(var1, (FragmentTransaction)null);
         if (var2 != null) {
            var2.commit();
         }
      }

      TabHost.OnTabChangeListener var3 = this.mOnTabChangeListener;
      if (var3 != null) {
         var3.onTabChanged(var1);
      }

   }

   @Deprecated
   public void setOnTabChangedListener(TabHost.OnTabChangeListener var1) {
      this.mOnTabChangeListener = var1;
   }

   @Deprecated
   public void setup() {
      throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
   }

   @Deprecated
   public void setup(Context var1, FragmentManager var2) {
      this.ensureHierarchy(var1);
      super.setup();
      this.mContext = var1;
      this.mFragmentManager = var2;
      this.ensureContent();
   }

   @Deprecated
   public void setup(Context var1, FragmentManager var2, int var3) {
      this.ensureHierarchy(var1);
      super.setup();
      this.mContext = var1;
      this.mFragmentManager = var2;
      this.mContainerId = var3;
      this.ensureContent();
      this.mRealTabContent.setId(var3);
      if (this.getId() == -1) {
         this.setId(16908306);
      }

   }

   static class DummyTabFactory implements TabHost.TabContentFactory {
      private final Context mContext;

      public DummyTabFactory(Context var1) {
         this.mContext = var1;
      }

      public View createTabContent(String var1) {
         View var2 = new View(this.mContext);
         var2.setMinimumWidth(0);
         var2.setMinimumHeight(0);
         return var2;
      }
   }

   static class SavedState extends View.BaseSavedState {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      String curTab;

      SavedState(Parcel var1) {
         super(var1);
         this.curTab = var1.readString();
      }

      SavedState(Parcelable var1) {
         super(var1);
      }

      public String toString() {
         return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.curTab + "}";
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeString(this.curTab);
      }
   }

   static final class TabInfo {
      final Bundle args;
      final Class clss;
      Fragment fragment;
      final String tag;

      TabInfo(String var1, Class var2, Bundle var3) {
         this.tag = var1;
         this.clss = var2;
         this.args = var3;
      }
   }
}
