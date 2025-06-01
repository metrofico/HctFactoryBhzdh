package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.WarningLvAdapter;
import com.hzbhd.canbus.interfaces.OnWarningStatusChangeListener;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.WarningPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

public class WarningActivity extends AbstractBaseActivity {
   public static final int STATUS_ON_RESUME = 4;
   public static final int STATUS_ON_STOP = 6;
   private List mList;
   private OnWarningStatusChangeListener mOnWarningStatusChangeListener;
   private RecyclerView mRecyclerView;
   private WarningPageUiSet mSet;
   private TextView mTisTv;
   private WarningLvAdapter mWarningLvAdapter;

   private void findViews() {
      this.mRecyclerView = (RecyclerView)this.findViewById(2131363220);
      this.mTisTv = (TextView)this.findViewById(2131363709);
   }

   private void initViews() {
      WarningPageUiSet var1 = UiMgrFactory.getCanUiMgr(this).getWarningPageUiSet(this);
      this.mSet = var1;
      if (var1 != null) {
         this.mOnWarningStatusChangeListener = var1.getOnWarningStatusChangeListener();
      }

      this.mList = new ArrayList();
      this.mWarningLvAdapter = new WarningLvAdapter(this, this.mList);
      LinearLayoutManager var2 = new LinearLayoutManager(this);
      this.mRecyclerView.setLayoutManager(var2);
      DividerItemDecoration var3 = new DividerItemDecoration(this, 1);
      var3.setDrawable(this.getResources().getDrawable(2131234802));
      this.mRecyclerView.addItemDecoration(var3);
      this.mRecyclerView.setAdapter(this.mWarningLvAdapter);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558701);
      this.findViews();
      this.initViews();
   }

   protected void onResume() {
      super.onResume();
      OnWarningStatusChangeListener var1 = this.mOnWarningStatusChangeListener;
      if (var1 != null) {
         var1.onStatusChange(4);
      }

      this.refreshUi((Bundle)null);
   }

   protected void onStop() {
      super.onStop();
      OnWarningStatusChangeListener var1 = this.mOnWarningStatusChangeListener;
      if (var1 != null) {
         var1.onStatusChange(6);
      }

   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         LogUtil.showLog("Warning refreshUi");
         List var2 = GeneralWarningDataData.dataList;
         if (var2 != null && var2.size() != 0) {
            this.mTisTv.setVisibility(8);
         } else {
            this.mTisTv.setVisibility(0);
         }

         if (var2 != null) {
            this.mList.clear();
            this.mList.addAll(var2);
            this.mWarningLvAdapter.notifyDataSetChanged();
         }
      }
   }
}
