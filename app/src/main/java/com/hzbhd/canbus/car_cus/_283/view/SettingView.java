package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

public class SettingView extends RelativeLayout {
   private ImageView iv_title;
   private Context mContext;
   private View mView;
   private TextView tv_title;
   private TextView tv_value;

   public SettingView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SettingView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SettingView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558478, this, true);
      this.initView();
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.SettingSelectStyle);
      String var5 = var4.getString(0);
      String var6 = var4.getString(2);
      Drawable var7 = var4.getDrawable(1);
      if (!TextUtils.isEmpty(var5)) {
         this.tv_title.setText(var5);
      }

      this.setValue(var6);
      if (var7 != null) {
         this.iv_title.setVisibility(0);
         this.iv_title.setImageDrawable(var7);
      } else {
         this.iv_title.setVisibility(8);
         this.iv_title.setImageDrawable((Drawable)null);
      }

   }

   private void initView() {
      this.tv_title = (TextView)this.mView.findViewById(2131363710);
      this.tv_value = (TextView)this.mView.findViewById(2131363714);
      this.iv_title = (ImageView)this.mView.findViewById(2131362652);
   }

   public void setEnable(boolean var1) {
      if (var1) {
         this.tv_title.setTextColor(this.mContext.getColor(2131100061));
         this.tv_value.setTextColor(this.mContext.getColor(2131100061));
      } else {
         this.tv_title.setTextColor(this.mContext.getColor(2131099649));
         this.tv_value.setTextColor(this.mContext.getColor(2131099649));
      }

      this.setEnabled(var1);
   }

   public void setValue(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         this.tv_value.setVisibility(0);
         this.tv_value.setText(var1);
      } else {
         this.tv_value.setVisibility(8);
         this.tv_value.setText("");
      }

   }
}
