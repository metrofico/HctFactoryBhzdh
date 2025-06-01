package com.hzbhd.canbus.car._206;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.HybirdValueLvAdapter;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

public class MqbHybirdActivity extends AbstractBaseActivity {
   private ImageView mBattery;
   private int[] mBatteryLevelImage = new int[]{2131234919, 2131234920, 2131234922, 2131234923, 2131234924, 2131234925, 2131234926, 2131234927, 2131234928, 2131234929, 2131234921};
   private TextView mBatteryValue;
   private ImageView mEnergyDirection;
   private HybirdValueLvAdapter mHybirdValueLvAdapter;
   private List mList;
   private ImageView mMotor;
   private OnHybirdPageStatusListener mOnHybirdPageStatusListener;
   private RecyclerView mRecyclerView;
   private ImageView mWheelTrackLeft;
   private ImageView mWheelTrackRight;

   private void findViews() {
      this.mMotor = (ImageView)this.findViewById(2131362604);
      this.mBattery = (ImageView)this.findViewById(2131362541);
      this.mEnergyDirection = (ImageView)this.findViewById(2131362567);
      this.mWheelTrackLeft = (ImageView)this.findViewById(2131362657);
      this.mWheelTrackRight = (ImageView)this.findViewById(2131362658);
      this.mRecyclerView = (RecyclerView)this.findViewById(2131363220);
      this.mBatteryValue = (TextView)this.findViewById(2131363590);
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
      if (this.getResources().getConfiguration().orientation == 2) {
         var3.setOrientation(0);
      }

      this.mRecyclerView.setLayoutManager(var3);
      this.mRecyclerView.setAdapter(this.mHybirdValueLvAdapter);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558406);
      this.findViews();
      this.initViews();
   }

   protected void onResume() {
      super.onResume();

      try {
         this.refreshUi((Bundle)null);
      } catch (Exception var2) {
         LogUtil.showLog("VehicleMonitorActivity onResume:" + var2.toString());
      }

   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         if (GeneralHybirdData.valueList != null) {
            this.mList.clear();
            this.mList.addAll(GeneralHybirdData.valueList);
            this.mHybirdValueLvAdapter.notifyDataSetChanged();
         }

         this.mBatteryValue.setVisibility(0);
         this.mBatteryValue.setText(GeneralHybirdData.powerBatteryValue + "%");
         if (GeneralHybirdData.powerBatteryLevel == 0) {
            this.mBattery.setBackgroundResource(2131234919);
         } else {
            this.mBattery.setBackgroundResource(this.mBatteryLevelImage[GeneralHybirdData.powerBatteryLevel]);
         }

         this.mEnergyDirection.setVisibility(0);
         this.mWheelTrackLeft.setVisibility(0);
         this.mWheelTrackRight.setVisibility(0);
         this.mMotor.setVisibility(0);
         this.mBattery.setVisibility(0);
         if (!GeneralHybirdData.isShowMotor) {
            this.mMotor.setVisibility(8);
         }

         if (!GeneralHybirdData.isShowBattery) {
            this.mBattery.setVisibility(8);
         }

         if (GeneralHybirdData.energyDirection == 0) {
            this.mEnergyDirection.setVisibility(8);
         } else if (GeneralHybirdData.energyDirection == 1) {
            this.mEnergyDirection.setImageResource(2131234941);
         } else {
            this.mEnergyDirection.setImageResource(2131234940);
         }

         int var2 = GeneralHybirdData.wheelTrack;
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 == 4) {
                        this.mWheelTrackLeft.setImageResource(2131234934);
                        this.mWheelTrackRight.setImageResource(2131234935);
                     }
                  } else {
                     this.mWheelTrackLeft.setImageResource(2131234936);
                     this.mWheelTrackRight.setImageResource(2131234937);
                  }
               } else {
                  this.mWheelTrackLeft.setImageResource(2131234930);
                  this.mWheelTrackRight.setImageResource(2131234931);
               }
            } else {
               this.mWheelTrackLeft.setImageResource(2131234932);
               this.mWheelTrackRight.setImageResource(2131234933);
            }
         } else {
            this.mWheelTrackLeft.setVisibility(8);
            this.mWheelTrackRight.setVisibility(8);
         }

      }
   }
}
