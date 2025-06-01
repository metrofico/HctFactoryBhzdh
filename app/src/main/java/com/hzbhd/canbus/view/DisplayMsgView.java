package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;

public class DisplayMsgView {
   private boolean isShowing = false;
   private Context mContext;
   private LinearLayout mFloatView;
   private WindowManager.LayoutParams mLayoutParams;
   private Runnable mRunnable = new Runnable(this) {
      final DisplayMsgView this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.dismissView();
      }
   };
   private TextView mTv;
   private WindowManager mWindowManager;

   public DisplayMsgView(Context var1) {
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      this.initView();
   }

   private void dismissView() {
      WindowManager var1 = this.mWindowManager;
      if (var1 != null) {
         LinearLayout var2 = this.mFloatView;
         if (var2 != null) {
            var1.removeView(var2);
            this.isShowing = false;
         }
      }

   }

   private void initView() {
      LinearLayout var1 = (LinearLayout)LayoutInflater.from(this.mContext).inflate(2131558729, (ViewGroup)null);
      this.mFloatView = var1;
      this.mTv = (TextView)var1.findViewById(2131363614);
   }

   public void refreshUi() {
      if (GeneralDisplayMsgData.displayMsg != null) {
         this.mTv.setText(GeneralDisplayMsgData.displayMsg);
         if (this.mLayoutParams == null) {
            WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
            this.mLayoutParams = var1;
            var1.type = 2002;
            this.mLayoutParams.gravity = 17;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
         }

         if (!this.isShowing) {
            this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
            this.isShowing = true;
         }

         this.mFloatView.removeCallbacks(this.mRunnable);
         this.mFloatView.postDelayed(this.mRunnable, 2000L);
      }
   }
}
