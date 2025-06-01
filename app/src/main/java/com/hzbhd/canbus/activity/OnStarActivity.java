package com.hzbhd.canbus.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.hzbhd.canbus.fragment.OnStartMainFragment;
import com.hzbhd.canbus.fragment.OnStartNavigationFragment;
import com.hzbhd.canbus.fragment.OnStartPhoneFragment;
import com.hzbhd.canbus.fragment.OnStartPhoneMoreInfoFragment;
import com.hzbhd.canbus.fragment.OnStartWirelessFragment;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.commontools.SourceConstantsDef;

public class OnStarActivity extends AbstractBaseActivity {
   public static final String BUNDLE_ONSTAR_WHAT = "bundle_onstar_what";
   public static final String BUNDLE_OPEN_FRAGMENT = "bundle_open_fragment";
   public static final int ON_STAR_NAVIGATION_WHAT = 1003;
   public static final int ON_STAR_PHONE_MORE_INFO_WHAT = 1004;
   public static final int ON_STAR_PHONE_WHAT = 1001;
   public static final int ON_STAR_SIMPLE_WHAT = 1005;
   public static final int ON_STAR_WIRELESS_WHAT = 1002;
   private static final String TAG = "OnStarActivity";
   private Class mClass;
   private FragmentManager mFragmentManager;
   private FrameLayout mFrameLayout;
   private OnStartMainFragment mOnStartMainFragment;
   private OnStartNavigationFragment mOnStartNavigationFragment;
   private OnStartPhoneFragment mOnStartPhoneFragment;
   private OnStartPhoneMoreInfoFragment mOnStartPhoneMoreInfoFragment;
   private OnStartWirelessFragment mOnStartWirelessFragment;

   private void findViews() {
      this.mFrameLayout = (FrameLayout)this.findViewById(2131362252);
   }

   private void initViews() {
      this.mFragmentManager = this.getFragmentManager();
      this.mOnStartMainFragment = new OnStartMainFragment();
      this.showFragment(OnStartMainFragment.class);
   }

   private void openFragment() {
      if (this.getIntent() != null) {
         Class var1 = (Class)this.getIntent().getSerializableExtra("bundle_open_fragment");
         if (var1 != null) {
            this.showFragment(var1);
         }
      }

   }

   private void releaseAudioChannel() {
      CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
   }

   private void requestAudioChannel() {
      CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
   }

   public void hide() {
      this.mOnStartPhoneFragment.dialog.setVisibility(4);
      this.mOnStartPhoneFragment.input.setVisibility(4);
   }

   public void onBackPressed() {
      this.releaseAudioChannel();
      FragmentManager var1 = this.mFragmentManager;
      if (var1 != null) {
         if (var1.getBackStackEntryCount() == 1) {
            this.finish();
         } else {
            super.onBackPressed();
         }
      } else {
         super.onBackPressed();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558686);
      this.findViews();
      this.initViews();
      this.openFragment();
   }

   protected void onResume() {
      super.onResume();
      this.requestAudioChannel();
   }

   public void refreshUi(Bundle var1) {
      boolean var10001;
      label139: {
         label140: {
            label141: {
               label142: {
                  try {
                     switch (var1.getInt("bundle_onstar_what")) {
                        case 1001:
                           break;
                        case 1002:
                           break label141;
                        case 1003:
                           break label139;
                        case 1004:
                           break label142;
                        case 1005:
                           break label140;
                        default:
                           return;
                     }
                  } catch (Exception var12) {
                     var10001 = false;
                     return;
                  }

                  OnStartPhoneFragment var15;
                  try {
                     var15 = this.mOnStartPhoneFragment;
                  } catch (Exception var11) {
                     var10001 = false;
                     return;
                  }

                  if (var15 != null) {
                     try {
                        var15.refreshUi(var1);
                     } catch (Exception var10) {
                        var10001 = false;
                     }

                     return;
                  }

                  return;
               }

               OnStartPhoneMoreInfoFragment var2;
               try {
                  var2 = this.mOnStartPhoneMoreInfoFragment;
               } catch (Exception var5) {
                  var10001 = false;
                  return;
               }

               if (var2 != null) {
                  try {
                     var2.refreshUi(var1);
                  } catch (Exception var4) {
                     var10001 = false;
                  }

                  return;
               }

               return;
            }

            OnStartWirelessFragment var14;
            try {
               var14 = this.mOnStartWirelessFragment;
            } catch (Exception var9) {
               var10001 = false;
               return;
            }

            if (var14 != null) {
               try {
                  var14.refreshUi(var1);
               } catch (Exception var8) {
                  var10001 = false;
               }

               return;
            }

            return;
         }

         try {
            this.hide();
            this.mOnStartPhoneFragment.refreshUi(var1);
         } catch (Exception var3) {
            var10001 = false;
         }

         return;
      }

      OnStartNavigationFragment var13;
      try {
         var13 = this.mOnStartNavigationFragment;
      } catch (Exception var7) {
         var10001 = false;
         return;
      }

      if (var13 != null) {
         try {
            var13.refreshUi(var1);
         } catch (Exception var6) {
            var10001 = false;
         }
      }

   }

   public void showFragment(Class var1) {
      this.mClass = var1;
      Object var2;
      if (var1 == OnStartMainFragment.class) {
         if (this.mOnStartMainFragment == null) {
            this.mOnStartMainFragment = new OnStartMainFragment();
         }

         var2 = this.mOnStartMainFragment;
      } else if (var1 == OnStartPhoneFragment.class) {
         if (this.mOnStartPhoneFragment == null) {
            this.mOnStartPhoneFragment = new OnStartPhoneFragment();
         }

         var2 = this.mOnStartPhoneFragment;
      } else if (var1 == OnStartNavigationFragment.class) {
         if (this.mOnStartNavigationFragment == null) {
            this.mOnStartNavigationFragment = new OnStartNavigationFragment();
         }

         var2 = this.mOnStartNavigationFragment;
      } else if (var1 == OnStartWirelessFragment.class) {
         if (this.mOnStartWirelessFragment == null) {
            this.mOnStartWirelessFragment = new OnStartWirelessFragment();
         }

         var2 = this.mOnStartWirelessFragment;
      } else if (var1 == OnStartPhoneMoreInfoFragment.class) {
         if (this.mOnStartPhoneMoreInfoFragment == null) {
            this.mOnStartPhoneMoreInfoFragment = new OnStartPhoneMoreInfoFragment();
         }

         var2 = this.mOnStartPhoneMoreInfoFragment;
      } else {
         var2 = null;
      }

      if (var2 == null) {
         LogUtil.showLog("no match fragment to show");
      } else {
         FragmentTransaction var3 = this.mFragmentManager.beginTransaction();
         var3.replace(2131362252, (Fragment)var2);
         var3.addToBackStack(var1.getName());
         var3.commit();
      }
   }
}
