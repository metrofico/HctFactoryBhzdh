package com.hzbhd.canbus.car_cus._304.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.activity.HybirdActivity;
import com.hzbhd.canbus.activity.OriginalCarDeviceActivity;
import com.hzbhd.canbus.activity.SelectCanTypeActivity;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.activity.TirePressureActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.MainLvAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.car_cus._304.activity.AirActivity;
import com.hzbhd.canbus.entity.MainListEntity;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.cantype.CanTypeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VehicleSettingsView extends RelativeLayout implements MainLvAdapter.ItemClickInterface {
   private List mList;
   private MainLvAdapter mMainLvAdapter;
   private RecyclerView mRecyclerView;

   public VehicleSettingsView(Context var1) {
      super(var1);
   }

   public VehicleSettingsView(Context var1, Activity var2) {
      this(var1);
      LayoutInflater.from(var1).inflate(2131558511, this);
      this.findViews();
      this.initViews(var2);
   }

   private void findViews() {
      this.mRecyclerView = (RecyclerView)this.findViewById(2131363220);
   }

   private void initViews(Activity var1) {
      this.mList = new ArrayList();
      this.mMainLvAdapter = new MainLvAdapter(this.mList, this);
      LinearLayoutManager var2 = new LinearLayoutManager(this.getContext());
      var2.setOrientation(0);
      this.mRecyclerView.setLayoutManager(var2);
      this.mRecyclerView.setAdapter(this.mMainLvAdapter);
      ArrayList var4 = new ArrayList();
      Iterator var3 = UiMgrFactory.getCanUiMgr(this.getContext()).getPageUiSet(this.getContext()).getMainPageUiSet().getBtnAction().iterator();

      while(var3.hasNext()) {
         var4.add(new MainListEntity((String)var3.next()));
      }

      this.mList.addAll(var4);
      this.mMainLvAdapter.notifyDataSetChanged();
      this.findViewById(2131363737).setOnLongClickListener(new VehicleSettingsView$$ExternalSyntheticLambda0(this, var1));
      ((TextView)this.findViewById(2131363715)).setText(CommUtil.getVersionName(this.getContext()));
      if (SharePreUtil.getBoolValue(this.getContext(), "share_pre_is_debug_model", false)) {
         this.findViewById(2131362776).setVisibility(0);
         this.findViewById(2131362057).setOnClickListener(new VehicleSettingsView$$ExternalSyntheticLambda1(this));
      }

   }

   // $FF: synthetic method
   boolean lambda$initViews$0$com_hzbhd_canbus_car_cus__304_view_VehicleSettingsView(Activity var1, View var2) {
      SelectCanTypeUtil.showDialogToUpdate(var1, (CanTypeAllEntity)CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(this.getContext())).getList().get(0), this.getResources().getString(2131770076));
      return true;
   }

   // $FF: synthetic method
   void lambda$initViews$1$com_hzbhd_canbus_car_cus__304_view_VehicleSettingsView(View var1) {
      this.getContext().startActivity(new Intent(this.getContext(), SelectCanTypeActivity.class));
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
         case -1202765494:
            if (var3.equals("hybird")) {
               var4 = 1;
            }
            break;
         case 3556498:
            if (var3.equals("test")) {
               var4 = 2;
            }
            break;
         case 178891557:
            if (var3.equals("tire_info")) {
               var4 = 3;
            }
            break;
         case 577205567:
            if (var3.equals("drive_data")) {
               var4 = 4;
            }
            break;
         case 1124446108:
            if (var3.equals("warning")) {
               var4 = 5;
            }
            break;
         case 1271599729:
            if (var3.equals("amplifier")) {
               var4 = 6;
            }
            break;
         case 1985941072:
            if (var3.equals("setting")) {
               var4 = 7;
            }
      }

      switch (var4) {
         case 0:
            this.getContext().startActivity(new Intent(this.getContext(), OriginalCarDeviceActivity.class));
            break;
         case 1:
            this.getContext().startActivity(new Intent(this.getContext(), HybirdActivity.class));
            break;
         case 2:
            this.getContext().startActivity(new Intent(this.getContext(), AirActivity.class));
            break;
         case 3:
            this.getContext().startActivity(new Intent(this.getContext(), TirePressureActivity.class));
            break;
         case 4:
            this.getContext().startActivity(new Intent(this.getContext(), DriveDataActivity.class));
            break;
         case 5:
            this.getContext().startActivity(new Intent(this.getContext(), WarningActivity.class));
            break;
         case 6:
            this.getContext().startActivity(new Intent(this.getContext(), AmplifierActivity.class));
            break;
         case 7:
            this.getContext().startActivity(new Intent(this.getContext(), SettingActivity.class));
      }

   }
}
