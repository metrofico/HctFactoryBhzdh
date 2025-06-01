package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;

public class OnStartWirelessFragment extends BaseFragment {
   private RelativeLayout mInfoRl;
   private TextView mInfoTv;
   private RelativeLayout mPasswordRl;
   private RelativeLayout mPointRl;
   private TextView mPointTv;
   private TextView mPswTv;
   private View mView;

   private void findViews() {
      this.mInfoTv = (TextView)this.mView.findViewById(2131363631);
      this.mPointTv = (TextView)this.mView.findViewById(2131363673);
      this.mPswTv = (TextView)this.mView.findViewById(2131363674);
      this.mInfoRl = (RelativeLayout)this.mView.findViewById(2131363192);
      this.mPointRl = (RelativeLayout)this.mView.findViewById(2131363198);
      this.mPasswordRl = (RelativeLayout)this.mView.findViewById(2131363199);
   }

   private void initViews() {
      this.refreshUi((Bundle)null);
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      if (this.mView == null) {
         this.mView = var1.inflate(2131558739, var2, false);
         this.findViews();
         this.initViews();
      }

      ViewGroup var4 = (ViewGroup)this.mView.getParent();
      if (var4 != null) {
         var4.removeView(this.mView);
      }

      return this.mView;
   }

   public void refreshUi(Bundle var1) {
      if (TextUtils.isEmpty(GeneralOnStartData.mOnStarWirelessInfo)) {
         this.mInfoRl.setVisibility(8);
      } else {
         this.mInfoRl.setVisibility(0);
      }

      if (TextUtils.isEmpty(GeneralOnStartData.mOnStarWirelessPoint)) {
         this.mPointRl.setVisibility(8);
      } else {
         this.mPointRl.setVisibility(0);
      }

      if (TextUtils.isEmpty(GeneralOnStartData.mOnStarWirelessPassword)) {
         this.mPasswordRl.setVisibility(8);
      } else {
         this.mPasswordRl.setVisibility(0);
      }

      this.mInfoTv.setText(GeneralOnStartData.mOnStarWirelessInfo);
      this.mPointTv.setText(GeneralOnStartData.mOnStarWirelessPoint);
      this.mPswTv.setText(GeneralOnStartData.mOnStarWirelessPassword);
   }
}
