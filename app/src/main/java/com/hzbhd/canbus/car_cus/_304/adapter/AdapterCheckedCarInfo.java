package com.hzbhd.canbus.car_cus._304.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._304.bean.CheckedCarInfoBean;
import java.util.ArrayList;
import java.util.List;

public class AdapterCheckedCarInfo extends RecyclerView.Adapter {
   private final long ANIMATION_DURATION = 200L;
   private final String TAG = "_304_AdapterCheckedCarInfoljqdebug";
   private List mList = new ArrayList();
   private int mTextSizeCurrent;
   private int mTextSizeIdle;

   public AdapterCheckedCarInfo(Context var1) {
      this.mTextSizeCurrent = (int)var1.getResources().getDimension(2131169654);
      this.mTextSizeIdle = (int)var1.getResources().getDimension(2131169191);
   }

   public void addData(List var1) {
      this.mList = var1;
      if (var1.size() > 0) {
         ((CheckedCarInfoBean)this.mList.get(1)).setChecked(true);
      }

   }

   public int getItemCount() {
      return this.mList.size();
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      CheckedCarInfoBean var3 = (CheckedCarInfoBean)this.mList.get(var2);
      var1.textView.setText(var3.getTitle());
      if (var3.isChecked()) {
         var2 = this.mTextSizeCurrent;
      } else {
         var2 = this.mTextSizeIdle;
      }

      var1.playAnimation(var3.getTextSize(), var2, 200L);
      var3.setTextSize(var2);
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558502, var1, false));
   }

   public void setSelectedPosition(int var1) {
      for(int var2 = 0; var2 < this.mList.size(); ++var2) {
         ((CheckedCarInfoBean)this.mList.get(var2)).setChecked(false);
      }

      ((CheckedCarInfoBean)this.mList.get(var1)).setChecked(true);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private TextView textView;
      final AdapterCheckedCarInfo this$0;

      public ViewHolder(AdapterCheckedCarInfo var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363519);
      }

      // $FF: synthetic method
      void lambda$playAnimation$0$com_hzbhd_canbus_car_cus__304_adapter_AdapterCheckedCarInfo$ViewHolder(ValueAnimator var1) {
         this.textView.setTextSize((Float)var1.getAnimatedValue());
      }

      public void playAnimation(int var1, int var2, long var3) {
         ValueAnimator var5 = ValueAnimator.ofFloat(new float[]{(float)var1, (float)var2});
         var5.addUpdateListener(new AdapterCheckedCarInfo$ViewHolder$$ExternalSyntheticLambda0(this));
         var5.setDuration(var3);
         var5.start();
      }
   }
}
