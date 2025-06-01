package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

public class ReverseMonitorView extends RelativeLayout implements ViewInterface {
   private Context mContext;
   private View mView;
   private RelativeLayout reverseRelative;

   public ReverseMonitorView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558415, this);
      this.findViews();
      this.initViews();
   }

   private void findViews() {
      this.reverseRelative = (RelativeLayout)this.mView.findViewById(2131363139);
   }

   private void initViews() {
   }

   public void addSurfaceView(SurfaceView var1) {
      this.reverseRelative.removeView(var1);
      this.reverseRelative.addView(var1);
   }

   public void onDestroy() {
   }

   public void refreshUi(Bundle var1) {
   }

   public void removeSurfaceView(SurfaceView var1) {
      this.reverseRelative.removeView(var1);
   }
}
