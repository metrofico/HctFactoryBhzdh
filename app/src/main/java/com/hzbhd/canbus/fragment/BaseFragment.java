package com.hzbhd.canbus.fragment;

import android.app.Fragment;
import android.view.View;
import com.hzbhd.canbus.util.CommUtil;

public abstract class BaseFragment extends Fragment {
   protected String getStringByName(String var1) {
      return this.getString(CommUtil.getStrIdByResId(this.getActivity(), var1));
   }

   protected void showOrHide(View var1, boolean var2) {
      if (var2) {
         var1.setVisibility(0);
      } else {
         var1.setVisibility(4);
      }

   }
}
