package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._290.MainActivity;

public class MonitorView extends RelativeLayout implements ViewInterface, View.OnClickListener {
   private View bgView;
   private Button mCenterCamera;
   private Context mContext;
   private Button mFronCamera;
   private MainActivity mMainActivity;
   private View mView;
   private RelativeLayout monitorRelative;

   public MonitorView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558413, this);
      this.findViews();
   }

   private void findViews() {
      this.monitorRelative = (RelativeLayout)this.mView.findViewById(2131362872);
      this.mFronCamera = (Button)this.mView.findViewById(2131362876);
      this.mCenterCamera = (Button)this.mView.findViewById(2131362875);
      this.bgView = this.mView.findViewById(2131362874);
      this.mFronCamera.setOnClickListener(this);
      this.mCenterCamera.setOnClickListener(this);
   }

   public void addSurfaceView(SurfaceView var1) {
      this.monitorRelative.removeView(var1);
      this.monitorRelative.addView(var1);
   }

   public void onClick(View var1) {
      this.bgView.setVisibility(0);
      this.bgView.postDelayed(new Runnable(this) {
         final MonitorView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.bgView.setVisibility(8);
         }
      }, 500L);
      switch (var1.getId()) {
         case 2131362875:
            this.mMainActivity.showCenterCamera();
            break;
         case 2131362876:
            this.mMainActivity.showFrontCamera();
      }

   }

   public void onDestroy() {
   }

   public void refreshUi(Bundle var1) {
   }

   public void removeSurfaceView(SurfaceView var1) {
      this.monitorRelative.removeView(var1);
   }

   public void setManiActivity(MainActivity var1) {
      this.mMainActivity = var1;
   }
}
