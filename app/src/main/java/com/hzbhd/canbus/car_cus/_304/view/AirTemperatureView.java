package com.hzbhd.canbus.car_cus._304.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._304.activity.AirActivity;
import com.hzbhd.canbus.util.TimerUtil;

public class AirTemperatureView extends RelativeLayout {
   public static final int MAX_ANGLE = 160;
   public static final int MIN_ANGLE = 20;
   private final String TAG = "_304_AirTemperatureView";
   private float mCurrentAngle;
   private float mEventAngle;
   private TimerUtil mOnAdjustTimer;
   private OnTemperatureAdjustListener mOnTemperatureAdjustListener;
   private RelativeLayout mRlAirTemperature;
   private RelativeLayout mRlIndicator;
   private TextView mTvTemperature;

   public AirTemperatureView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.findViews(var1);
      this.mOnAdjustTimer = new TimerUtil();
   }

   private void findViews(Context var1) {
      LayoutInflater.from(var1).inflate(2131558503, this);
      this.mRlAirTemperature = (RelativeLayout)this.findViewById(2131363180);
      this.mRlIndicator = (RelativeLayout)this.findViewById(2131363191);
      this.mTvTemperature = (TextView)this.findViewById(2131363705);
   }

   private void refresh(float var1) {
      this.setIndicatorRotation(var1);
      int var2 = AirActivity.getGear(var1);
      String var3 = var2 + 17 + this.getContext().getString(2131770016);
      this.mTvTemperature.setText(var3);
      this.mOnTemperatureAdjustListener.onAdjust(var1);
   }

   private void setIndicatorRotation(float var1) {
      this.mEventAngle = var1;
      this.mRlIndicator.setRotation(var1);
   }

   public void initViews(OnTemperatureAdjustListener var1) {
      this.mOnTemperatureAdjustListener = var1;
      this.mRlAirTemperature.setOnTouchListener(new AirTemperatureView$$ExternalSyntheticLambda0(this));
   }

   // $FF: synthetic method
   boolean lambda$initViews$0$com_hzbhd_canbus_car_cus__304_view_AirTemperatureView(View var1, MotionEvent var2) {
      float var8 = var2.getX();
      float var9 = (float)this.mRlAirTemperature.getWidth() / 2.0F;
      float var7 = (float)this.mRlAirTemperature.getHeight() / 2.0F;
      float var10 = var2.getY();
      double var5 = Math.toDegrees(Math.atan2((double)(var8 - var9), (double)(var7 - var10)));
      double var3 = var5;
      if (var5 < 20.0) {
         var3 = 20.0;
      }

      var5 = var3;
      if (var3 > 160.0) {
         var5 = 160.0;
      }

      this.refresh((float)var5);
      return true;
   }

   // $FF: synthetic method
   void lambda$refresh$1$com_hzbhd_canbus_car_cus__304_view_AirTemperatureView(ValueAnimator var1) {
      float var2 = (Float)var1.getAnimatedValue();
      this.mCurrentAngle = var2;
      this.setIndicatorRotation(var2);
   }

   public void refresh(String var1, float var2) {
      Log.i("_304_AirTemperatureView", "refresh: \ntemperature->" + var1 + "\nangle->" + var2);
      this.mTvTemperature.setText(var1);
      ValueAnimator var5 = ValueAnimator.ofFloat(new float[]{this.mCurrentAngle, var2});
      var5.addUpdateListener(new AirTemperatureView$$ExternalSyntheticLambda1(this));
      long var3 = (long)(Math.abs(this.mEventAngle - var2) / 10.0F * 96.0F);
      Log.i("_304_AirTemperatureView", "refresh: duration - > " + var3);
      var5.setDuration(var3);
      var5.start();
   }

   public interface OnTemperatureAdjustListener {
      void onAdjust(float var1);
   }
}
