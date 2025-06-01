package com.hzbhd.canbus.car_cus._277.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._277._277_GeneralSettingData;
import com.hzbhd.canbus.car_cus._277.adapter.VehicleMonitorRightLvAdapter;
import com.hzbhd.canbus.car_cus._277.adapter.VihicleMonitorLeftLvAdapter;
import com.hzbhd.canbus.car_cus._277.ui_set.PageUiSet;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleMonitorPageUiSet;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VehicleMonitorActivity extends AbstractBaseActivity implements VihicleMonitorLeftLvAdapter.LeftItemClickInterface, VehicleMonitorRightLvAdapter.RightItemClickInterface, VehicleMonitorRightLvAdapter.RightItemTouchInterface {
   private List dataList;
   private int mCurLeftIndex;
   private List mLeftList;
   private RecyclerView mLeftRecyclerView;
   private VihicleMonitorLeftLvAdapter mLeftSettingLvAdapter;
   private OnConfirmDialogListener mOnSettingConfirmDialogListener;
   private OnSettingItemClickListener mOnSettingItemClickListener;
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener;
   private OnSettingItemSelectListener mOnSettingItemSelectListener;
   private OnSettingItemTouchListener mOnSettingItemTouchListener;
   private OnSettingPageStatusListener mOnSettingPageStatusListener;
   private List mRightList;
   private RecyclerView mRightRecyclerView;
   private RecyclerView mRightRecyclerView1;
   private VehicleMonitorRightLvAdapter mRightSettingLvAdapter;
   private VehicleMonitorRightLvAdapter mRightSettingLvAdapter1;

   private void findViews() {
      this.mLeftRecyclerView = (RecyclerView)this.findViewById(2131363219);
      this.mRightRecyclerView = (RecyclerView)this.findViewById(2131363222);
      this.mRightRecyclerView1 = (RecyclerView)this.findViewById(2131363223);
   }

   private String getJsonContent(Context var1) {
      return CommUtil.getAssetsString(var1, "car_ui/" + SelectCanTypeUtil.getCurrentCanTypeId(var1) + ".json");
   }

   private void initViews() {
      VehicleMonitorPageUiSet var1 = ((PageUiSet)(new Gson()).fromJson(this.getJsonContent(this), PageUiSet.class)).getVehicleMonitorPageUiSet();
      this.mOnSettingItemSelectListener = var1.getOnSettingItemSelectListener();
      this.mOnSettingPageStatusListener = var1.getOnSettingPageStatusListener();
      this.mOnSettingItemSeekbarSelectListener = var1.getOnSettingItemSeekbarSelectListener();
      this.mOnSettingItemClickListener = var1.getOnSettingItemClickListener();
      this.mOnSettingItemTouchListener = var1.getmOnSettingItemTouchListener();
      this.mOnSettingConfirmDialogListener = var1.getmOnSettingConfirmDialogListener();
      this.mLeftList = new ArrayList();
      this.mLeftSettingLvAdapter = new VihicleMonitorLeftLvAdapter(this, this.mLeftList, this);
      LinearLayoutManager var2 = new LinearLayoutManager(this);
      var2.setOrientation(0);
      this.mLeftRecyclerView.setLayoutManager(var2);
      this.mLeftRecyclerView.setAdapter(this.mLeftSettingLvAdapter);
      this.mRightList = new ArrayList();
      this.mRightSettingLvAdapter = new VehicleMonitorRightLvAdapter(this, this.mRightList, this, this);
      var2 = new LinearLayoutManager(this);
      this.mRightRecyclerView.setLayoutManager(var2);
      DividerItemDecoration var5 = new DividerItemDecoration(this, 1);
      var5.setDrawable(this.getResources().getDrawable(2131232550));
      this.mRightRecyclerView.addItemDecoration(var5);
      this.mRightRecyclerView.setAdapter(this.mRightSettingLvAdapter);
      this.mRightSettingLvAdapter1 = new VehicleMonitorRightLvAdapter(this, this.mRightList, this, this);
      GridLayoutManager var6 = new GridLayoutManager(this, 2, 1, false);
      this.mRightRecyclerView1.setLayoutManager(var6);
      this.mRightRecyclerView1.setAdapter(this.mRightSettingLvAdapter1);
      this.mLeftList.addAll(var1.getList());
      Iterator var3 = this.mLeftList.iterator();

      while(var3.hasNext()) {
         ((VehicleMonitorPageUiSet.ListBean)var3.next()).setIsSelected(false);
      }

      ((VehicleMonitorPageUiSet.ListBean)this.mLeftList.get(0)).setIsSelected(true);
      this.mLeftSettingLvAdapter.notifyDataSetChanged();
      OnSettingPageStatusListener var4 = this.mOnSettingPageStatusListener;
      if (var4 != null) {
         var4.onStatusChange(0);
      } else {
         LogUtil.showLog("mOnSettingPageStatusListener==null");
      }

      this.mRightList.addAll(((VehicleMonitorPageUiSet.ListBean)this.mLeftList.get(0)).getItemList());
      this.mRightSettingLvAdapter1.notifyDataSetChanged();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558418);
      this.findViews();
      this.initViews();
   }

   public void onLeftItemClick(int var1) {
      OnSettingPageStatusListener var5 = this.mOnSettingPageStatusListener;
      if (var5 != null) {
         var5.onStatusChange(var1);
      }

      this.mCurLeftIndex = var1;

      int var2;
      for(var2 = 0; var2 < this.mLeftList.size(); ++var2) {
         VehicleMonitorPageUiSet.ListBean var6 = (VehicleMonitorPageUiSet.ListBean)this.mLeftList.get(var2);
         boolean var4;
         if (var2 == var1) {
            var4 = true;
         } else {
            var4 = false;
         }

         var6.setIsSelected(var4);
      }

      if (var1 == 0) {
         this.mRightRecyclerView1.setVisibility(0);
         this.mRightRecyclerView.setVisibility(8);
      } else {
         this.mRightRecyclerView1.setVisibility(8);
         this.mRightRecyclerView.setVisibility(0);
      }

      this.mLeftSettingLvAdapter.notifyDataSetChanged();
      this.mRightList.clear();
      int var3 = ((VehicleMonitorPageUiSet.ListBean)this.mLeftList.get(var1)).getRowNumber();
      if (var3 > 0) {
         for(var2 = 0; var2 < var3; ++var2) {
            this.mRightList.add((VehicleMonitorPageUiSet.ListBean.ItemListBean)((VehicleMonitorPageUiSet.ListBean)this.mLeftList.get(var1)).getItemList().get(0));
         }
      } else {
         this.mRightList.addAll(((VehicleMonitorPageUiSet.ListBean)this.mLeftList.get(var1)).getItemList());
      }

      if (var1 == 0) {
         this.mRightSettingLvAdapter1.notifyDataSetChanged();
      } else {
         this.mRightSettingLvAdapter.notifyDataSetChanged();
      }

      this.refreshUi((Bundle)null);
   }

   protected void onResume() {
      super.onResume();

      try {
         this.refreshUi((Bundle)null);
      } catch (Exception var2) {
         LogUtil.showLog("VehicleMonitorActivity onResume:" + var2.toString());
      }

   }

   public void onRightItemClick(int var1) {
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

         LogUtil.showLog("monitor refreshUi" + var5);
         if (this.dataList == null) {
            this.dataList = new ArrayList();
         }

         this.dataList.clear();
         this.dataList.addAll(_277_GeneralSettingData.dataList2);
         if (this.dataList != null) {
            for(int var2 = 0; var2 < this.dataList.size(); ++var2) {
               SettingUpdateEntity var6 = (SettingUpdateEntity)this.dataList.get(var2);
               if (this.mCurLeftIndex == var6.getLeftListIndex()) {
                  int var3 = ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).getStyle();
                  if (var3 != 0) {
                     String var4;
                     if (var3 != 1) {
                        if (var3 != 2) {
                           if (var3 != 4) {
                              if (var3 == 5) {
                                 ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var6.getValue());
                              }
                           } else {
                              ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var6.getValue());
                           }
                        } else {
                           var4 = String.valueOf(var6.getValue());
                           ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var4);
                           ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setProgress(var6.getProgress());
                        }
                     } else {
                        var4 = (String)((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).getValueSrnArray().get((Integer)var6.getValue());
                        ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var4);
                     }
                  } else {
                     ((VehicleMonitorPageUiSet.ListBean.ItemListBean)this.mRightList.get(var6.getRightListIndex())).setValue(var6.getValue());
                  }
               }
            }

            this.mRightSettingLvAdapter.notifyDataSetChanged();
            this.mRightSettingLvAdapter1.notifyDataSetChanged();
         }
      }
   }
}
