package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R;

class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
   private static final String TAG = "FragmentManager";
   final FragmentManager mFragmentManager;

   FragmentLayoutInflaterFactory(FragmentManager var1) {
      this.mFragmentManager = var1;
   }

   public View onCreateView(View var1, String var2, Context var3, AttributeSet var4) {
      if (FragmentContainerView.class.getName().equals(var2)) {
         return new FragmentContainerView(var3, var4, this.mFragmentManager);
      } else {
         boolean var8 = "fragment".equals(var2);
         Fragment var13 = null;
         if (!var8) {
            return null;
         } else {
            String var9 = var4.getAttributeValue((String)null, "class");
            TypedArray var12 = var3.obtainStyledAttributes(var4, R.styleable.Fragment);
            String var10 = var9;
            if (var9 == null) {
               var10 = var12.getString(R.styleable.Fragment_android_name);
            }

            int var7 = var12.getResourceId(R.styleable.Fragment_android_id, -1);
            String var11 = var12.getString(R.styleable.Fragment_android_tag);
            var12.recycle();
            if (var10 != null && FragmentFactory.isFragmentClass(var3.getClassLoader(), var10)) {
               int var5;
               if (var1 != null) {
                  var5 = var1.getId();
               } else {
                  var5 = 0;
               }

               if (var5 == -1 && var7 == -1 && var11 == null) {
                  throw new IllegalArgumentException(var4.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + var10);
               } else {
                  if (var7 != -1) {
                     var13 = this.mFragmentManager.findFragmentById(var7);
                  }

                  Fragment var16 = var13;
                  if (var13 == null) {
                     var16 = var13;
                     if (var11 != null) {
                        var16 = this.mFragmentManager.findFragmentByTag(var11);
                     }
                  }

                  var13 = var16;
                  if (var16 == null) {
                     var13 = var16;
                     if (var5 != -1) {
                        var13 = this.mFragmentManager.findFragmentById(var5);
                     }
                  }

                  FragmentStateManager var14;
                  Fragment var15;
                  FragmentStateManager var17;
                  if (var13 == null) {
                     var13 = this.mFragmentManager.getFragmentFactory().instantiate(var3.getClassLoader(), var10);
                     var13.mFromLayout = true;
                     int var6;
                     if (var7 != 0) {
                        var6 = var7;
                     } else {
                        var6 = var5;
                     }

                     var13.mFragmentId = var6;
                     var13.mContainerId = var5;
                     var13.mTag = var11;
                     var13.mInLayout = true;
                     var13.mFragmentManager = this.mFragmentManager;
                     var13.mHost = this.mFragmentManager.getHost();
                     var13.onInflate(this.mFragmentManager.getHost().getContext(), var4, var13.mSavedFragmentState);
                     var17 = this.mFragmentManager.addFragment(var13);
                     var15 = var13;
                     var14 = var17;
                     if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Fragment " + var13 + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(var7));
                        var15 = var13;
                        var14 = var17;
                     }
                  } else {
                     if (var13.mInLayout) {
                        throw new IllegalArgumentException(var4.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(var7) + ", tag " + var11 + ", or parent id 0x" + Integer.toHexString(var5) + " with another fragment for " + var10);
                     }

                     var13.mInLayout = true;
                     var13.mFragmentManager = this.mFragmentManager;
                     var13.mHost = this.mFragmentManager.getHost();
                     var13.onInflate(this.mFragmentManager.getHost().getContext(), var4, var13.mSavedFragmentState);
                     var17 = this.mFragmentManager.createOrGetFragmentStateManager(var13);
                     var15 = var13;
                     var14 = var17;
                     if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Retained Fragment " + var13 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(var7));
                        var14 = var17;
                        var15 = var13;
                     }
                  }

                  var15.mContainer = (ViewGroup)var1;
                  var14.moveToExpectedState();
                  var14.ensureInflatedView();
                  if (var15.mView != null) {
                     if (var7 != 0) {
                        var15.mView.setId(var7);
                     }

                     if (var15.mView.getTag() == null) {
                        var15.mView.setTag(var11);
                     }

                     var15.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener(this, var14) {
                        final FragmentLayoutInflaterFactory this$0;
                        final FragmentStateManager val$fragmentStateManager;

                        {
                           this.this$0 = var1;
                           this.val$fragmentStateManager = var2;
                        }

                        public void onViewAttachedToWindow(View var1) {
                           Fragment var2 = this.val$fragmentStateManager.getFragment();
                           this.val$fragmentStateManager.moveToExpectedState();
                           SpecialEffectsController.getOrCreateController((ViewGroup)var2.mView.getParent(), this.this$0.mFragmentManager).forceCompleteAllOperations();
                        }

                        public void onViewDetachedFromWindow(View var1) {
                        }
                     });
                     return var15.mView;
                  } else {
                     throw new IllegalStateException("Fragment " + var10 + " did not create a view.");
                  }
               }
            } else {
               return null;
            }
         }
      }
   }

   public View onCreateView(String var1, Context var2, AttributeSet var3) {
      return this.onCreateView((View)null, var1, var2, var3);
   }
}
