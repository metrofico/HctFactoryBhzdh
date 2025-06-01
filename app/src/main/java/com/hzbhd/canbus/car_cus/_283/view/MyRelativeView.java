package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.bean.DriveData;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRelativeView extends RelativeLayout {
   private MySettingDialogView ACC;
   private MySettingDialogView Air_Conditioning;
   private MySettingSelectView Comfort;
   private MySettingDialogView DCC;
   private MySettingDialogView Dynamic_Bend_lighting;
   private MySettingSelectView Eco;
   private MySettingDialogView Engine;
   private MySettingSelectView Indivdual;
   private MySettingSelectView Normal;
   private MySettingSelectView Sport;
   private MySettingDialogView Steering;
   private boolean isClick = true;
   private Context mContext;
   private View mView;
   private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
   private MySettingSelectView xuedi;
   private MySettingSelectView yueye;
   private MySettingSelectView yueye_personal;

   public MyRelativeView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.initView();
      this.initData();
   }

   private void initData() {
      this.refreshUi();
   }

   private void initView() {
      this.Comfort = (MySettingSelectView)this.findViewById(2131361805);
      this.Normal = (MySettingSelectView)this.findViewById(2131361827);
      this.Sport = (MySettingSelectView)this.findViewById(2131361830);
      this.Eco = (MySettingSelectView)this.findViewById(2131361809);
      this.Indivdual = (MySettingSelectView)this.findViewById(2131361824);
      this.xuedi = (MySettingSelectView)this.findViewById(2131363801);
      this.yueye = (MySettingSelectView)this.findViewById(2131363805);
      this.yueye_personal = (MySettingSelectView)this.findViewById(2131363806);
      this.DCC = (MySettingDialogView)this.findViewById(2131361806);
      this.Dynamic_Bend_lighting = (MySettingDialogView)this.findViewById(2131361807);
      this.Engine = (MySettingDialogView)this.findViewById(2131361810);
      this.ACC = (MySettingDialogView)this.findViewById(2131361792);
      this.Air_Conditioning = (MySettingDialogView)this.findViewById(2131361803);
      this.Steering = (MySettingDialogView)this.findViewById(2131361831);
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.initView();
      this.initData();
   }

   public void refreshUi() {
      Log.d("mww", "refreshUi----------Comfort " + this.Comfort);
      this.isClick = false;
      MySettingSelectView var1 = this.Comfort;
      if (var1 != null) {
         var1.setSelect(DriveData.Comfort);
      }

      var1 = this.Normal;
      if (var1 != null) {
         var1.setSelect(DriveData.Normal);
      }

      var1 = this.Sport;
      if (var1 != null) {
         var1.setSelect(DriveData.Sport);
      }

      var1 = this.Eco;
      if (var1 != null) {
         var1.setSelect(DriveData.Eco);
      }

      var1 = this.Indivdual;
      if (var1 != null) {
         var1.setSelect(DriveData.Indivdual);
      }

      var1 = this.xuedi;
      if (var1 != null) {
         var1.setSelect(DriveData.xuedi);
      }

      var1 = this.yueye;
      if (var1 != null) {
         var1.setSelect(DriveData.yueye);
      }

      var1 = this.yueye_personal;
      if (var1 != null) {
         var1.setSelect(DriveData.yueye_personal);
      }

      MySettingDialogView var2 = this.DCC;
      if (var2 != null) {
         var2.setSelect(DriveData.DCC);
      }

      var2 = this.Dynamic_Bend_lighting;
      if (var2 != null) {
         var2.setSelect(DriveData.Dynamic_Bend_lighting);
      }

      var2 = this.Engine;
      if (var2 != null) {
         var2.setSelect(DriveData.Engine);
      }

      var2 = this.ACC;
      if (var2 != null) {
         var2.setSelect(DriveData.ACC);
      }

      var2 = this.Air_Conditioning;
      if (var2 != null) {
         var2.setSelect(DriveData.Air_Conditioning);
      }

      var2 = this.Steering;
      if (var2 != null) {
         var2.setSelect(DriveData.Steering);
      }

      this.isClick = true;
   }
}
