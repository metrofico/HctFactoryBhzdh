package com.hzbhd.canbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.MainLvAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.car._206.MqbHybirdActivity;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.GeneralSettingsConfig;
import com.hzbhd.canbus.entity.MainListEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.ui_set.MainPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AbstractBaseActivity implements MainLvAdapter.ItemClickInterface, View.OnClickListener {
   private final String TAG = "MainActivity";
   private LinearLayout mDebugLayout;
   private List mList;
   private MainLvAdapter mMainLvAdapter;
   private RecyclerView mRecyclerView;
   private TextView mTvNoCanTips;
   private TextView mVersionTv;
   private View mViewClick;

   private void findViews() {
      this.mViewClick = this.findViewById(2131363737);
      this.mRecyclerView = (RecyclerView)this.findViewById(2131363220);
      this.mVersionTv = (TextView)this.findViewById(2131363715);
      this.mDebugLayout = (LinearLayout)this.findViewById(2131362776);
      this.mTvNoCanTips = (TextView)this.findViewById(2131363655);
   }

   private void initViews() {
      UiMgrInterface var1 = this.getUiMgrInterface(this);
      if (var1 == null) {
         this.mTvNoCanTips.setVisibility(0);
      } else {
         MainPageUiSet var2 = var1.getMainUiSet(this);
         if (var2 == null) {
            this.mTvNoCanTips.setVisibility(0);
         } else {
            this.mList = new ArrayList();
            this.mMainLvAdapter = new MainLvAdapter(this.mList, this);
            LinearLayoutManager var3 = new LinearLayoutManager(this);
            var3.setOrientation(0);
            this.mRecyclerView.setLayoutManager(var3);
            this.mRecyclerView.setAdapter(this.mMainLvAdapter);
            ArrayList var4 = new ArrayList();
            Log.i("MainActivity", "initViews: isShowApp[" + CanbusConfig.INSTANCE.isShowApp() + "]");
            if (CanbusConfig.INSTANCE.isShowApp()) {
               Iterator var5 = var2.getBtnAction().iterator();

               while(var5.hasNext()) {
                  var4.add(new MainListEntity((String)var5.next()));
               }
            }

            if (LogUtil.log5()) {
               LogUtil.d("initViews(): ----general_settings");
            }

            if (LogUtil.log5()) {
               LogUtil.d("initViews(): ----" + ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getCanSettingShow());
            }

            if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getCanSettingShow()) {
               if (LogUtil.log5()) {
                  LogUtil.d("initViews(): ----general_settings");
               }

               if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).GENERAL_SETTING_PAGE_SHOW) {
                  var4.add(new MainListEntity("general_settings"));
               }
            }

            this.mList.addAll(var4);
            this.mMainLvAdapter.notifyDataSetChanged();
            this.mViewClick.setOnLongClickListener(new View.OnLongClickListener(this) {
               final MainActivity this$0;

               {
                  this.this$0 = var1;
               }

               public boolean onLongClick(View var1) {
                  CanTypeAllEntity var2 = (CanTypeAllEntity)CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(this.this$0)).getList().get(0);
                  MainActivity var3 = this.this$0;
                  SelectCanTypeUtil.showDialogToUpdate(var3, var2, var3.getResources().getString(2131770076));
                  return true;
               }
            });
            this.mVersionTv.setText(CommUtil.getVersionName(this));
            if (SharePreUtil.getBoolValue(this, "share_pre_is_debug_model", false)) {
               this.mDebugLayout.setVisibility(0);
            }

         }
      }
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362057) {
         this.startActivity(new Intent(this, SelectCanTypeActivity.class));
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558685);
      this.findViews();
      this.initViews();
   }

   public void onItemClick(int var1) {
      String var3 = ((MainListEntity)this.mList.get(var1)).getAction();
      var3.hashCode();
      int var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -1888873393:
            if (var3.equals("original_car_device")) {
               var4 = 0;
            }
            break;
         case -1821592035:
            if (var3.equals("mqb_hybrid")) {
               var4 = 1;
            }
            break;
         case -1645844070:
            if (var3.equals("general_settings")) {
               var4 = 2;
            }
            break;
         case -1482148444:
            if (var3.equals("panel_key")) {
               var4 = 3;
            }
            break;
         case -1202765494:
            if (var3.equals("hybird")) {
               var4 = 4;
            }
            break;
         case 96586:
            if (var3.equals("air")) {
               var4 = 5;
            }
            break;
         case 3545755:
            if (var3.equals("sync")) {
               var4 = 6;
            }
            break;
         case 3556498:
            if (var3.equals("test")) {
               var4 = 7;
            }
            break;
         case 178891557:
            if (var3.equals("tire_info")) {
               var4 = 8;
            }
            break;
         case 577205567:
            if (var3.equals("drive_data")) {
               var4 = 9;
            }
            break;
         case 1124446108:
            if (var3.equals("warning")) {
               var4 = 10;
            }
            break;
         case 1271599729:
            if (var3.equals("amplifier")) {
               var4 = 11;
            }
            break;
         case 1849102850:
            if (var3.equals("on_start")) {
               var4 = 12;
            }
            break;
         case 1985941072:
            if (var3.equals("setting")) {
               var4 = 13;
            }
      }

      switch (var4) {
         case 0:
            this.startActivity(new Intent(this, OriginalCarDeviceActivity.class));
            break;
         case 1:
            this.startActivity(new Intent(this, MqbHybirdActivity.class));
            break;
         case 2:
            this.startActivity(new Intent(this, FactoryActivity.class));
            break;
         case 3:
            this.startActivity(new Intent(this, PanelKeyActivity.class));
            break;
         case 4:
            this.startActivity(new Intent(this, HybirdActivity.class));
            break;
         case 5:
            AirActivity.clickToOpenActivity(this);
            break;
         case 6:
            this.startActivity(new Intent(this, SyncActivity.class));
            break;
         case 7:
            this.startActivity(new Intent(this, TestActivity.class));
            break;
         case 8:
            this.startActivity(new Intent(this, TirePressureActivity.class));
            break;
         case 9:
            this.startActivity(new Intent(this, DriveDataActivity.class));
            break;
         case 10:
            this.startActivity(new Intent(this, WarningActivity.class));
            break;
         case 11:
            this.startActivity(new Intent(this, AmplifierActivity.class));
            break;
         case 12:
            this.startActivity(new Intent(this, OnStarActivity.class));
            break;
         case 13:
            this.startActivity(new Intent(this, SettingActivity.class));
      }

   }

   public void refreshUi(Bundle var1) {
      com.hzbhd.canbus.util.LogUtil.showLog("MainActivity refreshUi");
   }
}
