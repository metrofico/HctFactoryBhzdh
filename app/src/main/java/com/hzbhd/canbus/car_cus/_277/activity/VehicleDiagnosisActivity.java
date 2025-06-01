package com.hzbhd.canbus.car_cus._277.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._277.DialogUtil;
import com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisLeftLvAdapter;
import com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisiRightLvAdapter;
import com.hzbhd.canbus.car_cus._277.ui_set.PageUiSet;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleDiagnosisPageUiSet;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VehicleDiagnosisActivity extends AbstractBaseActivity implements VehicleDiagnosisLeftLvAdapter.LeftItemClickInterface, VehicleDiagnosisiRightLvAdapter.RightItemClickInterface, VehicleDiagnosisiRightLvAdapter.RightItemTouchInterface {
   private List dataList;
   private int mCurLeftIndex;
   private List mLeftList;
   private RecyclerView mLeftRecyclerView;
   private VehicleDiagnosisLeftLvAdapter mLeftSettingLvAdapter;
   private OnConfirmDialogListener mOnSettingConfirmDialogListener;
   private OnSettingItemClickListener mOnSettingItemClickListener;
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener;
   private OnSettingItemSelectListener mOnSettingItemSelectListener;
   private OnSettingItemTouchListener mOnSettingItemTouchListener;
   private OnSettingPageStatusListener mOnSettingPageStatusListener;
   private List mRightList;
   private RecyclerView mRightRecyclerView;
   private VehicleDiagnosisiRightLvAdapter mRightSettingLvAdapter;
   String mTopPageValue;

   private void findViews() {
      this.mLeftRecyclerView = (RecyclerView)this.findViewById(2131363219);
      this.mRightRecyclerView = (RecyclerView)this.findViewById(2131363222);
   }

   private String getJsonContent(Context var1) {
      return CommUtil.getAssetsString(var1, "car_ui/" + SelectCanTypeUtil.getCurrentCanTypeId(var1) + ".json");
   }

   private void goToThePosition(Intent var1) {
      int var3 = var1.getIntExtra("left_index ", 0);
      int var2 = var1.getIntExtra("right_index", 0);
      this.onLeftItemClick(var3);
      this.mLeftRecyclerView.scrollToPosition(var3);
      this.mRightRecyclerView.scrollToPosition(var2);
   }

   private void initViews() {
      VehicleDiagnosisPageUiSet var1 = ((PageUiSet)(new Gson()).fromJson(this.getJsonContent(this), PageUiSet.class)).getVehicleDiagnosisPageUiSet();
      this.mOnSettingItemSelectListener = var1.getOnSettingItemSelectListener();
      this.mOnSettingPageStatusListener = var1.getOnSettingPageStatusListener();
      this.mOnSettingItemSeekbarSelectListener = var1.getOnSettingItemSeekbarSelectListener();
      this.mOnSettingItemClickListener = var1.getOnSettingItemClickListener();
      this.mOnSettingItemTouchListener = var1.getmOnSettingItemTouchListener();
      this.mOnSettingConfirmDialogListener = var1.getmOnSettingConfirmDialogListener();
      this.mLeftList = new ArrayList();
      this.mLeftSettingLvAdapter = new VehicleDiagnosisLeftLvAdapter(this, this.mLeftList, this);
      LinearLayoutManager var2 = new LinearLayoutManager(this);
      DividerItemDecoration var3 = new DividerItemDecoration(this, 1);
      var3.setDrawable(this.getResources().getDrawable(2131232550));
      this.mLeftRecyclerView.setLayoutManager(var2);
      this.mLeftRecyclerView.addItemDecoration(var3);
      this.mLeftRecyclerView.setAdapter(this.mLeftSettingLvAdapter);
      this.mRightList = new ArrayList();
      this.mRightSettingLvAdapter = new VehicleDiagnosisiRightLvAdapter(this, this.mRightList, this, this);
      var2 = new LinearLayoutManager(this);
      this.mRightRecyclerView.setLayoutManager(var2);
      DividerItemDecoration var6 = new DividerItemDecoration(this, 1);
      var6.setDrawable(this.getResources().getDrawable(2131232550));
      this.mRightRecyclerView.addItemDecoration(var6);
      this.mRightRecyclerView.setAdapter(this.mRightSettingLvAdapter);
      this.mLeftList.addAll(var1.getList());
      Iterator var4 = this.mLeftList.iterator();

      while(var4.hasNext()) {
         ((VehicleDiagnosisPageUiSet.ListBean)var4.next()).setIsSelected(true);
      }

      this.mLeftSettingLvAdapter.notifyDataSetChanged();
      OnSettingPageStatusListener var5 = this.mOnSettingPageStatusListener;
      if (var5 != null) {
         var5.onStatusChange(0);
      } else {
         LogUtil.showLog("mOnSettingPageStatusListener==null");
      }

      this.mRightSettingLvAdapter.notifyDataSetChanged();
   }

   private void setRightList(int var1) {
      this.mRightList.clear();
      this.mRightList.addAll(((VehicleDiagnosisPageUiSet.ListBean)this.mLeftList.get(var1)).getItemList());
   }

   private void setTopPageValue() {
      Iterator var3 = this.mLeftList.iterator();

      while(var3.hasNext()) {
         VehicleDiagnosisPageUiSet.ListBean var1 = (VehicleDiagnosisPageUiSet.ListBean)var3.next();
         this.mTopPageValue = "geely_e6_item_0";
         Iterator var4 = var1.getItemList().iterator();

         while(var4.hasNext()) {
            VehicleDiagnosisPageUiSet.ListBean.ItemListBean var2 = (VehicleDiagnosisPageUiSet.ListBean.ItemListBean)var4.next();
            if (!"geely_e6_diagnosis_item_0_70".equals(var2.getTitleSrn()) && !"_268_diagnosis_page1_item42".equals(var2.getTitleSrn()) && !"null_value".equals(var2.getValue()) && !"geely_e6_item_0".equals(var2.getValue())) {
               this.mTopPageValue = "geely_e6_item_4";
               break;
            }
         }

         if (var1.setValue(this.mTopPageValue)) {
            Log.i("ljq", "setTopPageValue: left notifyDataSetChanged");
            this.mLeftSettingLvAdapter.notifyDataSetChanged();
         }
      }

   }

   public void onBackPressed() {
      Log.d("sswang", "onBackPressed: ");
      if (this.mLeftRecyclerView.getVisibility() == 8) {
         this.mLeftRecyclerView.setVisibility(0);
         this.mRightRecyclerView.setVisibility(8);
      } else {
         super.onBackPressed();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558419);
      this.findViews();
      this.initViews();
      Intent var2 = this.getIntent();
      if (var2 != null && "setting_open_target_page".equals(var2.getAction())) {
         this.goToThePosition(var2);
      }

   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      if (var1 == 4) {
         this.onBackPressed();
         return true;
      } else {
         return super.onKeyDown(var1, var2);
      }
   }

   public void onLeftItemClick(int var1) {
      Log.i("ljq", "onLeftItemClick: ");
      OnSettingPageStatusListener var2 = this.mOnSettingPageStatusListener;
      if (var2 != null) {
         var2.onStatusChange(var1);
      }

      this.mCurLeftIndex = var1;
      this.setRightList(var1);
      this.mRightSettingLvAdapter.notifyDataSetChanged();
      this.mRightRecyclerView.setVisibility(0);
      this.mLeftRecyclerView.setVisibility(8);
      this.refreshUi((Bundle)null);
   }

   protected void onNewIntent(Intent var1) {
      super.onNewIntent(var1);
      if ("setting_open_target_page".equals(var1.getAction())) {
         this.goToThePosition(var1);
      }

   }

   protected void onResume() {
      super.onResume();

      try {
         this.refreshUi((Bundle)null);
      } catch (Exception var2) {
         LogUtil.showLog("VehicleDiagnosisActivity onResume:" + var2.toString());
      }

   }

   public void onRightItemClick(int var1) {
      OnSettingItemClickListener var3 = this.mOnSettingItemClickListener;
      if (var3 != null) {
         var3.onClickItem(this.mCurLeftIndex, var1);
      }

      if (((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getStyle() == 1) {
         List var4 = ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getValueSrnArray();
         int var2 = 0;

         String[] var5;
         for(var5 = (String[])var4.toArray(new String[0]); var2 < var5.length; ++var2) {
            var5[var2] = this.getString(CommUtil.getStrIdByResId(this, var5[var2]));
         }

         DialogUtil.getInstance().showListDialog(this, var5, new DialogUtil.ListDialogCallBak(this, var1) {
            final VehicleDiagnosisActivity this$0;
            final int val$rightPosition;

            {
               this.this$0 = var1;
               this.val$rightPosition = var2;
            }

            public void callBack(int var1) {
               this.this$0.mOnSettingItemSelectListener.onClickItem(this.this$0.mCurLeftIndex, this.val$rightPosition, var1);
            }
         });
      }

   }

   public void onRightItemTouch(int var1, View var2, MotionEvent var3) {
      OnSettingItemTouchListener var4 = this.mOnSettingItemTouchListener;
      if (var4 != null) {
         var4.onTouchItem(this.mCurLeftIndex, var1, var2, var3);
      }

   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         String var5;
         if (var1 != null && var1.containsKey("head")) {
            var5 = " head: " + var1.getString("head");
         } else {
            var5 = "";
         }

         LogUtil.showLog("diagnosis refreshUi" + var5);
         if (this.dataList == null) {
            this.dataList = new ArrayList();
         }

         this.dataList.clear();
         this.dataList.addAll(GeneralSettingData.dataList);
         if (this.dataList != null) {
            for(int var2 = 0; var2 < this.dataList.size(); ++var2) {
               SettingUpdateEntity var6 = (SettingUpdateEntity)this.dataList.get(var2);
               this.setRightList(var6.getLeftListIndex());
               if (this.mRightList.size() == 0) {
                  return;
               }

               int var3 = ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).getStyle();
               if (var3 != 0) {
                  String var4;
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var6.getValue());
                        }
                     } else {
                        var4 = String.valueOf(var6.getValue());
                        ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var4);
                        ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setProgress(var6.getProgress());
                     }
                  } else {
                     var4 = (String)((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).getValueSrnArray().get((Integer)var6.getValue());
                     ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var4);
                  }
               } else {
                  ((VehicleDiagnosisPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var6.getValue());
               }
            }

            this.setTopPageValue();
            this.setRightList(this.mCurLeftIndex);
            this.mRightSettingLvAdapter.notifyDataSetChanged();
         }
      }
   }
}
