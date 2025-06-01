package com.hzbhd.canbus.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.HybirdValueLvAdapter;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import java.util.ArrayList;
import java.util.List;

public class HybirdActivity extends AbstractBaseActivity {
   private ImageView mBatteryDriveMotor;
   private ImageView mBatteryLevel;
   private int[] mBatteryLevelImage = new int[]{2131232556, 2131232557, 2131232558, 2131232559, 2131232560, 2131232561, 2131232562, 2131232563};
   private ImageView mEngineDriveMotor;
   private ImageView mEngineDriveWheel;
   private HybirdValueLvAdapter mHybirdValueLvAdapter;
   private List mList;
   private ImageView mMotorDriveBattery;
   private ImageView mMotorDriveWheel;
   private OnHybirdPageStatusListener mOnHybirdPageStatusListener;
   private RecyclerView mRecyclerView;
   private TextView mTitleTv;
   private ImageView mWheelDriveMotor;

   private void findViews() {
      this.mWheelDriveMotor = (ImageView)this.findViewById(2131362090);
      this.mBatteryDriveMotor = (ImageView)this.findViewById(2131362084);
      this.mEngineDriveWheel = (ImageView)this.findViewById(2131362087);
      this.mEngineDriveMotor = (ImageView)this.findViewById(2131362086);
      this.mMotorDriveWheel = (ImageView)this.findViewById(2131362089);
      this.mMotorDriveBattery = (ImageView)this.findViewById(2131362088);
      this.mBatteryLevel = (ImageView)this.findViewById(2131362085);
      this.mRecyclerView = (RecyclerView)this.findViewById(2131363220);
      this.mTitleTv = (TextView)this.findViewById(2131363710);
   }

   private void initViews() {
      HybirdPageUiSet var1 = this.getUiMgrInterface(this).getHybirdPageUiSet(this);
      if (var1 != null) {
         OnHybirdPageStatusListener var2 = var1.getOnHybirdPageStatusListener();
         this.mOnHybirdPageStatusListener = var2;
         if (var2 != null) {
            var2.onStatusChange(1);
         }
      }

      this.mList = new ArrayList();
      this.mHybirdValueLvAdapter = new HybirdValueLvAdapter(this.mList);
      LinearLayoutManager var3 = new LinearLayoutManager(this);
      var3.setOrientation(0);
      this.mRecyclerView.setLayoutManager(var3);
      this.mRecyclerView.setAdapter(this.mHybirdValueLvAdapter);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558662);
      this.findViews();
      this.initViews();
   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         if (!TextUtils.isEmpty(GeneralHybirdData.title)) {
            this.mTitleTv.setText(GeneralHybirdData.title);
         }

         if (GeneralHybirdData.valueList != null) {
            this.mList.clear();
            this.mList.addAll(GeneralHybirdData.valueList);
            this.mHybirdValueLvAdapter.notifyDataSetChanged();
         }

         if (GeneralHybirdData.powerBatteryLevel == 0) {
            this.mBatteryLevel.setBackground((Drawable)null);
         } else {
            this.mBatteryLevel.setBackgroundResource(this.mBatteryLevelImage[GeneralHybirdData.powerBatteryLevel - 1]);
         }

         if (GeneralHybirdData.isWheelDriveMotor) {
            this.mWheelDriveMotor.setVisibility(0);
         } else {
            this.mWheelDriveMotor.setVisibility(8);
         }

         if (GeneralHybirdData.isBatteryDriveMotor) {
            this.mBatteryDriveMotor.setVisibility(0);
         } else {
            this.mBatteryDriveMotor.setVisibility(8);
         }

         if (GeneralHybirdData.isEngineDriveWheel) {
            this.mEngineDriveWheel.setVisibility(0);
         } else {
            this.mEngineDriveWheel.setVisibility(8);
         }

         if (GeneralHybirdData.isEngineDriveMotor) {
            this.mEngineDriveMotor.setVisibility(0);
         } else {
            this.mEngineDriveMotor.setVisibility(8);
         }

         if (GeneralHybirdData.isMotorDriveWheel) {
            this.mMotorDriveWheel.setVisibility(0);
         } else {
            this.mMotorDriveWheel.setVisibility(8);
         }

         if (GeneralHybirdData.isMotorDriveBattery) {
            this.mMotorDriveBattery.setVisibility(0);
         } else {
            this.mMotorDriveBattery.setVisibility(8);
         }

      }
   }
}
