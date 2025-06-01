package com.hzbhd.canbus.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.hzbhd.canbus.adapter.DriveDataPvAdapter;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DriveDataActivity extends AbstractBaseActivity implements View.OnClickListener {
   public static final int INVALID_INDEX = -1;
   private int leftPosition;
   private Button mBtn;
   private DriveDataPvAdapter mDriveDataPvAdapter;
   private List mList;
   private OnDriveDataPageStatusListener mOnDriveDataPageStatusListener;
   private TextView mTitleTv;
   private ViewPager mViewPager;
   private int rightPosition;

   private void findViews() {
      this.mViewPager = (ViewPager)this.findViewById(2131363784);
      this.mTitleTv = (TextView)this.findViewById(2131363710);
      this.mBtn = (Button)this.findViewById(2131362383);
   }

   private void goToTheItem(Intent var1) {
      int var2 = var1.getIntExtra("current_item", 0);
      this.mViewPager.setCurrentItem(var2);
   }

   private void initViews() {
      DriverDataPageUiSet var1 = this.getUiMgrInterface(this).getDriverDataPageUiSet(this);
      OnDriveDataPageStatusListener var2 = var1.getOnDriveDataPageStatusListener();
      this.mOnDriveDataPageStatusListener = var2;
      if (var2 != null) {
         var2.onStatusChange(-1);
      } else {
         LogUtil.showLog("mOnDriveDataPageStatusListener==null");
      }

      this.mList = new ArrayList();
      this.mDriveDataPvAdapter = new DriveDataPvAdapter(this, this.mList);
      this.mList.addAll(var1.getList());
      this.mViewPager.setAdapter(this.mDriveDataPvAdapter);
      this.mTitleTv.setText(CommUtil.getStrIdByResId(this, ((DriverDataPageUiSet.Page)this.mList.get(0)).getHeadTitleSrn()));
      this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(this) {
         final DriveDataActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onPageScrollStateChanged(int var1) {
         }

         public void onPageScrolled(int var1, float var2, int var3) {
         }

         public void onPageSelected(int var1) {
            TextView var2 = this.this$0.mTitleTv;
            DriveDataActivity var3 = this.this$0;
            var2.setText(CommUtil.getStrIdByResId(var3, ((DriverDataPageUiSet.Page)var3.mList.get(var1)).getHeadTitleSrn()));
         }
      });
      this.mBtn.setText(CommUtil.getStrIdByResId(this, var1.getButtonText()));
      if (var1.isHaveBtn()) {
         this.mBtn.setVisibility(0);
         this.leftPosition = var1.getLeftPosition();
         this.rightPosition = var1.getRightPosition();
      } else {
         this.mBtn.setVisibility(8);
      }

   }

   private void startSettingActivity(Context var1, int var2, int var3) {
      Intent var4 = new Intent(var1, SettingActivity.class);
      var4.setAction("setting_open_target_page");
      var4.setFlags(268435456);
      var4.putExtra("left_index ", var2);
      var4.putExtra("right_index", var3);
      var1.startActivity(var4);
   }

   public void onClick(View var1) {
      int var3 = this.mViewPager.getCurrentItem();
      int var2 = var1.getId();
      if (var2 != 2131362383) {
         if (var2 != 2131362401) {
            if (var2 == 2131362408) {
               if (var3 != this.mList.size() - 1) {
                  this.mViewPager.setCurrentItem(var3 + 1);
               } else {
                  this.mViewPager.setCurrentItem(0);
               }
            }
         } else if (var3 != 0) {
            this.mViewPager.setCurrentItem(var3 - 1);
         } else {
            try {
               this.mViewPager.setCurrentItem(this.mList.size() - 1);
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }
      } else {
         this.startSettingActivity(this, this.leftPosition, this.rightPosition);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558658);
      this.findViews();
      this.initViews();
      Intent var2 = this.getIntent();
      if (var2 != null && "drive_data_open_target_page".equals(var2.getAction())) {
         this.goToTheItem(var2);
      }

   }

   protected void onDestroy() {
      super.onDestroy();
      OnDriveDataPageStatusListener var1 = this.mOnDriveDataPageStatusListener;
      if (var1 != null) {
         var1.onStatusChange(-2);
      }

   }

   protected void onNewIntent(Intent var1) {
      super.onNewIntent(var1);
      if ("drive_data_open_target_page".equals(var1.getAction())) {
         this.goToTheItem(var1);
      }

   }

   protected void onResume() {
      super.onResume();
      this.refreshUi((Bundle)null);
   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         LogUtil.showLog("DriveData refreshUi:" + GeneralDriveData.dataList.size());
         List var3 = GeneralDriveData.dataList;
         if (var3 != null) {
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
               DriverUpdateEntity var2 = (DriverUpdateEntity)var4.next();
               if (var2.getPage() != -1 && var2.getIndex() != -1 && var2.getPage() < this.mList.size() && var2.getIndex() < ((DriverDataPageUiSet.Page)this.mList.get(var2.getPage())).getItemList().size()) {
                  ((DriverDataPageUiSet.Page.Item)((DriverDataPageUiSet.Page)this.mList.get(var2.getPage())).getItemList().get(var2.getIndex())).setValue(var2.getValue());
               }
            }

            this.mDriveDataPvAdapter.notifyDataSetChanged();
         }
      }
   }
}
