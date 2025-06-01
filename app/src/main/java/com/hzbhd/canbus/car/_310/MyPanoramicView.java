package com.hzbhd.canbus.car._310;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
   private static final String TAG = "_310_MyPanoramicView";
   private OnBtnClickListener mOnBtnClickListener;

   public MyPanoramicView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558792, this);
      this.initView();
   }

   private void initView() {
      this.findViewById(2131362423).setOnClickListener(this);
      this.findViewById(2131362422).setOnClickListener(this);
      this.findViewById(2131362389).setOnClickListener(this);
      this.findViewById(2131362388).setOnClickListener(this);
   }

   public void onClick(View var1) {
      if (this.mOnBtnClickListener != null) {
         switch (var1.getId()) {
            case 2131362388:
               this.mOnBtnClickListener.onEndBottomClick();
               break;
            case 2131362389:
               this.mOnBtnClickListener.onEndTopClick();
               break;
            case 2131362422:
               this.mOnBtnClickListener.onStartBottomClick();
               break;
            case 2131362423:
               this.mOnBtnClickListener.onStartTopClick();
         }

      }
   }

   public void setmOnBtnClickListener(OnBtnClickListener var1) {
      this.mOnBtnClickListener = var1;
   }

   interface OnBtnClickListener {
      void onEndBottomClick();

      void onEndTopClick();

      void onStartBottomClick();

      void onStartTopClick();
   }
}
