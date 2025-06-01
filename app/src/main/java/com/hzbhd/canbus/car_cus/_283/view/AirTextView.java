package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class AirTextView extends LinearLayout implements View.OnClickListener {
   private static final String TAG = "_283_AirTextView";
   private BtnView addBtnView;
   private Context mContext;
   private OnClickListener mOnClickListener;
   private View mView;
   private MyTextView myTextView;
   private BtnView subBtnView;

   public AirTextView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AirTextView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public AirTextView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558442, this, true);
      this.initView();
   }

   private void initView() {
      this.myTextView = (MyTextView)this.mView.findViewById(2131362883);
      this.subBtnView = (BtnView)this.mView.findViewById(2131363439);
      this.addBtnView = (BtnView)this.mView.findViewById(2131361897);
      this.subBtnView.setOnClickListener(this);
      this.addBtnView.setOnClickListener(this);
   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      OnClickListener var3;
      if (var2 != 2131361897) {
         if (var2 == 2131363439) {
            var3 = this.mOnClickListener;
            if (var3 != null) {
               var3.sub();
            }
         }
      } else {
         var3 = this.mOnClickListener;
         if (var3 != null) {
            var3.add();
         }
      }

   }

   public void setOnClickListener(OnClickListener var1) {
      this.mOnClickListener = var1;
   }

   public void setText(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         this.myTextView.setText(var1);
      } else {
         Log.d("_283_AirTextView", "设置的文本是空值");
      }

   }

   public interface OnClickListener {
      void add();

      void sub();
   }
}
