package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.adapter.OnStartLvAdapter;
import com.hzbhd.canbus.entity.OnStartListEntity;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import java.util.ArrayList;
import java.util.List;

public class OnStartMainFragment extends BaseFragment implements OnStartLvAdapter.ItemClickInterface {
   private OnStarActivity mActivity;
   private List mList;
   private OnStartLvAdapter mOnStartLvAdapter;
   private OnStartPageUiSet mOnStartPageUiSet;
   private RecyclerView mRecyclerView;
   private View mView;

   private void findViews() {
      this.mRecyclerView = (RecyclerView)this.mView.findViewById(2131363220);
   }

   private void initViews() {
      OnStarActivity var3 = (OnStarActivity)this.getActivity();
      this.mActivity = var3;
      this.mOnStartPageUiSet = var3.getUiMgrInterface(this.getActivity()).getPageUiSet(this.getActivity()).getOnStartPageUiSet();
      this.mList = new ArrayList();
      this.mOnStartLvAdapter = new OnStartLvAdapter(this.mList, this);
      LinearLayoutManager var4 = new LinearLayoutManager(this.getActivity());
      var4.setOrientation(1);
      DividerItemDecoration var5 = new DividerItemDecoration(this.getActivity(), 1);
      var5.setDrawable(this.getResources().getDrawable(2131234802));
      this.mRecyclerView.addItemDecoration(var5);
      this.mRecyclerView.setLayoutManager(var4);
      this.mRecyclerView.setAdapter(this.mOnStartLvAdapter);
      ArrayList var6 = new ArrayList();
      String[] var7 = this.mActivity.getUiMgrInterface(this.getActivity()).getPageUiSet(this.getActivity()).getOnStartPageUiSet().getBtnAction();
      int var2 = var7.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         var6.add(new OnStartListEntity(var7[var1]));
      }

      this.mList.addAll(var6);
      this.mOnStartLvAdapter.notifyDataSetChanged();
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      if (this.mView == null) {
         this.mView = var1.inflate(2131558735, var2, false);
         this.findViews();
         this.initViews();
      }

      ViewGroup var4 = (ViewGroup)this.mView.getParent();
      if (var4 != null) {
         var4.removeView(this.mView);
      }

      return this.mView;
   }

   public void onItemClick(int var1) {
      String var3 = ((OnStartListEntity)this.mList.get(var1)).getAction();
      var3.hashCode();
      int var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -1664453177:
            if (var3.equals("phone_more_info")) {
               var4 = 0;
            }
            break;
         case -1000044642:
            if (var3.equals("wireless")) {
               var4 = 1;
            }
            break;
         case 106642798:
            if (var3.equals("phone")) {
               var4 = 2;
            }
            break;
         case 1862666772:
            if (var3.equals("navigation")) {
               var4 = 3;
            }
      }

      switch (var4) {
         case 0:
            this.mActivity.showFragment(OnStartPhoneMoreInfoFragment.class);
            break;
         case 1:
            this.mActivity.showFragment(OnStartWirelessFragment.class);
            this.mOnStartPageUiSet.getOnOnStartStatusChangeListener().onWirelessSwithc();
            break;
         case 2:
            this.mActivity.showFragment(OnStartPhoneFragment.class);
            break;
         case 3:
            this.mActivity.showFragment(OnStartNavigationFragment.class);
      }

   }
}
