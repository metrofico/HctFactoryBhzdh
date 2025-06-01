package com.hzbhd.canbus.car._165;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
   private static final String TAG = "_165_MyPanoramicView";
   private ImageButton mIbEndBottom;
   private ImageButton mIbEndTop;
   private ImageButton mIbMidBottom;
   private ImageButton mIbStartBottom;
   private ImageButton mIbStartTop;
   private OnBtnClickListener mOnBtnClickListener;

   public MyPanoramicView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558792, this);
      this.findView();
      this.initView();
   }

   private void findView() {
      this.mIbStartTop = (ImageButton)this.findViewById(2131362423);
      this.mIbStartBottom = (ImageButton)this.findViewById(2131362422);
      this.mIbMidBottom = (ImageButton)this.findViewById(2131362407);
      this.mIbEndTop = (ImageButton)this.findViewById(2131362389);
      this.mIbEndBottom = (ImageButton)this.findViewById(2131362388);
   }

   private void initView() {
      this.mIbStartTop.setOnClickListener(this);
      this.mIbStartBottom.setOnClickListener(this);
      this.mIbMidBottom.setOnClickListener(this);
      this.mIbEndTop.setOnClickListener(this);
      this.mIbEndBottom.setOnClickListener(this);
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
            case 2131362407:
               this.mOnBtnClickListener.onMidBottomClick();
               break;
            case 2131362422:
               this.mOnBtnClickListener.onStartBottomClick();
               break;
            case 2131362423:
               this.mOnBtnClickListener.onStartTopClick();
         }

      }
   }

   public void setOnBtnClickListener(OnBtnClickListener var1) {
      this.mOnBtnClickListener = var1;
   }

   interface OnBtnClickListener {
      void onEndBottomClick();

      void onEndTopClick();

      void onMidBottomClick();

      void onStartBottomClick();

      void onStartTopClick();
   }
}
