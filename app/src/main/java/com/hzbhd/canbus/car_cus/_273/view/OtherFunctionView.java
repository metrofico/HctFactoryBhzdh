package com.hzbhd.canbus.car_cus._273.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class OtherFunctionView extends RelativeLayout implements ViewInterface {
   private Context mContext;
   private View mView;

   public OtherFunctionView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558414, this);
      this.findViews();
      this.initViews();
   }

   private void findViews() {
   }

   private void initViews() {
   }

   public void onDestroy() {
   }

   public void refreshUi(Bundle var1) {
   }
}
