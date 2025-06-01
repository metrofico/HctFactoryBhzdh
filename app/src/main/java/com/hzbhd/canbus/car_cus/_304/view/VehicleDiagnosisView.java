package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.car_cus._304.MyLinearLayoutManager;
import com.hzbhd.canbus.car_cus._304.adapter.AdapterCheckedCarInfo;
import com.hzbhd.canbus.car_cus._304.bean.CheckedCarInfoBean;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VehicleDiagnosisView extends RelativeLayout implements View.OnClickListener {
   private static final int MSG_CIRCLE_END = 2;
   private static final int MSG_CIRCLE_RUNNING = 1;
   private static final int MSG_CIRCLE_START = 0;
   private final int CHECK_RING_MAX_COUNT = 38;
   private final int SCHEDULED_DELAY = 1000;
   private String SHARE_304_LAST_CHECK_TIME = "share_304_last_check_time";
   private final String TAG = "_304_VehicleDiagnosisView";
   private List checkBeanList;
   private AdapterCheckedCarInfo mAdapterCheckedCarInfo;
   private AnimationDrawable mAnimDrawable;
   private int mCheckItemCount;
   private int mCheckItemSum;
   private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final VehicleDiagnosisView this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 == 2) {
                  this.this$0.mIsCheckFinish = true;
                  var2 = SharePreUtil.getIntValue(this.this$0.getContext(), "share_304_warn_count", 0);
                  if (var2 > 0) {
                     this.this$0.mTvCircle.setText(2131767718);
                     this.this$0.mTvCircle.setTextColor(ContextCompat.getColor(this.this$0.getContext(), 2131099653));
                  } else {
                     this.this$0.mIvCircle.setImageLevel(0);
                     this.this$0.mTvCircle.setText(2131762027);
                     this.this$0.mTvCircle.setTextColor(ContextCompat.getColor(this.this$0.getContext(), 2131099652));
                  }

                  this.this$0.stopAnimation();
                  this.this$0.mTvWarnCount.setText(Integer.toString(var2));
                  this.this$0.mTvCheckTime.setText(this.this$0.getTime());
                  this.this$0.mLlCheck.setVisibility(4);
                  this.this$0.mLlResult.setVisibility(0);
               }
            } else {
               VehicleDiagnosisView.access$408(this.this$0);
               if (this.this$0.mCheckItemCount > this.this$0.mCheckItemSum) {
                  this.this$0.mHandler.sendEmptyMessage(2);
                  this.this$0.mScheduledExecutorService.shutdownNow();
               } else {
                  this.this$0.mIvCircle.setImageLevel(this.this$0.mCheckItemCount * 38 / this.this$0.mCheckItemSum);
               }

               VehicleDiagnosisView var3 = this.this$0;
               var3.selectPosition(var3.mCheckItemCount);
            }
         } else {
            this.this$0.mLlCheck.setVisibility(0);
            this.this$0.mLlResult.setVisibility(4);
            this.this$0.mLlWarn.setVisibility(0);
            this.this$0.mIsCheckFinish = false;
            this.this$0.mCheckItemCount = 0;
            this.this$0.mTvCircle.setText(2131762026);
            this.this$0.mTvCircle.setTextColor(ContextCompat.getColor(this.this$0.getContext(), 2131099653));
            this.this$0.mScheduledExecutorService = Executors.newScheduledThreadPool(2);
            this.this$0.mScheduledExecutorService.scheduleWithFixedDelay(new VehicleDiagnosisView$1$$ExternalSyntheticLambda0(this), 1L, 1000L, TimeUnit.MILLISECONDS);
            this.this$0.mRcvCheck.scrollToPosition(0);
         }

      }

      // $FF: synthetic method
      void lambda$handleMessage$0$com_hzbhd_canbus_car_cus__304_view_VehicleDiagnosisView$1() {
         this.this$0.mHandler.sendEmptyMessage(1);
      }
   };
   private boolean mIsCheckFinish = true;
   private ImageView mIvCircle;
   private ImageView mIvCursor;
   private LinearLayout mLlCheck;
   private LinearLayout mLlResult;
   private LinearLayout mLlWarn;
   private RecyclerView mRcvCheck;
   private ScheduledExecutorService mScheduledExecutorService;
   private TextView mTvCheckTime;
   private TextView mTvCircle;
   private TextView mTvWarnCount;

   public VehicleDiagnosisView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558500, this);
      this.findViews();
      this.initDatas();
      this.initViews(var1);
   }

   // $FF: synthetic method
   static int access$408(VehicleDiagnosisView var0) {
      int var1 = var0.mCheckItemCount++;
      return var1;
   }

   private void findViews() {
      this.mTvCircle = (TextView)this.findViewById(2131363606);
      this.mTvWarnCount = (TextView)this.findViewById(2131363716);
      this.mTvCheckTime = (TextView)this.findViewById(2131363605);
      this.mIvCursor = (ImageView)this.findViewById(2131362560);
      this.mIvCircle = (ImageView)this.findViewById(2131362556);
      this.mLlCheck = (LinearLayout)this.findViewById(2131362774);
      this.mLlResult = (LinearLayout)this.findViewById(2131362797);
      this.mLlWarn = (LinearLayout)this.findViewById(2131362809);
      this.mRcvCheck = (RecyclerView)this.findViewById(2131363041);
   }

   private String getTime() {
      Calendar var1 = Calendar.getInstance();
      String var2 = var1.get(1) + "-" + this.mDecimalFormat00.format((long)var1.get(2)) + "-" + this.mDecimalFormat00.format((long)var1.get(5)) + " " + this.mDecimalFormat00.format((long)var1.get(11)) + ":" + this.mDecimalFormat00.format((long)var1.get(12));
      SharePreUtil.setStringValue(this.getContext(), this.SHARE_304_LAST_CHECK_TIME, var2);
      return var2;
   }

   private void initDatas() {
      ArrayList var1 = new ArrayList();
      this.checkBeanList = var1;
      var1.add(new CheckedCarInfoBean(" "));
      this.checkBeanList.add(new CheckedCarInfoBean("整车系统"));
      this.checkBeanList.add(new CheckedCarInfoBean("电驱动系统"));
      this.checkBeanList.add(new CheckedCarInfoBean("PDC系统"));
      this.checkBeanList.add(new CheckedCarInfoBean("安全气囊"));
      this.checkBeanList.add(new CheckedCarInfoBean("真空度"));
      this.checkBeanList.add(new CheckedCarInfoBean("制动液"));
      this.checkBeanList.add(new CheckedCarInfoBean("电机"));
      this.checkBeanList.add(new CheckedCarInfoBean("电池"));
      this.checkBeanList.add(new CheckedCarInfoBean("充电机"));
      this.checkBeanList.add(new CheckedCarInfoBean("逆变器"));
      this.checkBeanList.add(new CheckedCarInfoBean("ESC"));
      this.checkBeanList.add(new CheckedCarInfoBean("EPS"));
      this.checkBeanList.add(new CheckedCarInfoBean("压缩机"));
      this.checkBeanList.add(new CheckedCarInfoBean("转向灯"));
      this.checkBeanList.add(new CheckedCarInfoBean("制动灯"));
      this.checkBeanList.add(new CheckedCarInfoBean("雷达系统"));
      this.checkBeanList.add(new CheckedCarInfoBean("车轮胎压"));
      this.checkBeanList.add(new CheckedCarInfoBean("全景监控系统"));
      this.checkBeanList.add(new CheckedCarInfoBean(" "));
      this.mCheckItemSum = this.checkBeanList.size() - 2;
   }

   private void initViews(Context var1) {
      this.mRcvCheck.setLayoutManager(new MyLinearLayoutManager(var1));
      RecyclerView var2 = this.mRcvCheck;
      AdapterCheckedCarInfo var3 = new AdapterCheckedCarInfo(var1);
      this.mAdapterCheckedCarInfo = var3;
      var2.setAdapter(var3);
      this.mRcvCheck.addOnItemTouchListener(new RecyclerViewDisabler(this));
      this.mAdapterCheckedCarInfo.addData(this.checkBeanList);
      this.mAnimDrawable = (AnimationDrawable)var1.getDrawable(2131230927);
      this.findViewById(2131362012).setOnClickListener(this);
      this.findViewById(2131362011).setOnClickListener(this);
      this.mTvWarnCount.setText("--");
      this.mTvCheckTime.setText(SharePreUtil.getStringValue(this.getContext(), this.SHARE_304_LAST_CHECK_TIME, ""));
      this.mLlWarn.setVisibility(4);
   }

   private void selectPosition(int var1) {
      if (var1 > 0 && var1 < this.mAdapterCheckedCarInfo.getItemCount() - 1) {
         this.mAdapterCheckedCarInfo.setSelectedPosition(var1);
         this.mRcvCheck.smoothScrollToPosition(var1 + 1);
         this.mAdapterCheckedCarInfo.notifyDataSetChanged();
      }

   }

   private void startAnimation() {
      AnimationDrawable var1 = this.mAnimDrawable;
      if (var1 != null) {
         this.mIvCursor.setImageDrawable(var1);
         this.mAnimDrawable.stop();
         this.mAnimDrawable.start();
      }

   }

   private void stopAnimation() {
      AnimationDrawable var1 = this.mAnimDrawable;
      if (var1 != null) {
         var1.stop();
      }

      this.mIvCursor.setImageResource(2131230975);
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362011:
            Intent var2 = new Intent(this.getContext(), WarningActivity.class);
            var2.setFlags(268435456);
            this.getContext().startActivity(var2);
            break;
         case 2131362012:
            if (this.mIsCheckFinish) {
               this.startAnimation();
               this.mHandler.sendEmptyMessage(0);
            }
      }

   }

   private class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {
      final VehicleDiagnosisView this$0;

      private RecyclerViewDisabler(VehicleDiagnosisView var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      RecyclerViewDisabler(VehicleDiagnosisView var1, Object var2) {
         this(var1);
      }

      public boolean onInterceptTouchEvent(RecyclerView var1, MotionEvent var2) {
         return true;
      }

      public void onRequestDisallowInterceptTouchEvent(boolean var1) {
      }

      public void onTouchEvent(RecyclerView var1, MotionEvent var2) {
      }
   }
}
