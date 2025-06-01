package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

public class TipsTextView extends RelativeLayout {
   private TextView tv_content;
   private TextView tv_title;

   public TipsTextView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public TipsTextView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public TipsTextView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558480, this, true);
      this.tv_title = (TextView)var4.findViewById(2131363710);
      this.tv_content = (TextView)var4.findViewById(2131363608);
      String var5 = var1.obtainStyledAttributes(var2, R.styleable.SettingSelectStyle, 0, 0).getString(0);
      if (!TextUtils.isEmpty(var5)) {
         this.tv_title.setText(var5);
      }

   }

   public void setContent(String var1) {
      this.tv_content.setText(var1);
   }
}
