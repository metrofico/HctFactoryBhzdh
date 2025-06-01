package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

public class SettingSelectView extends RelativeLayout implements View.OnClickListener {
   private boolean isSelect;
   private ImageView iv_select;
   private Context mContext;
   private OnItemClickListener mOnItemClickListener;
   private View mView;
   private RelativeLayout relativeLayout;
   private TextView tv_title;

   public SettingSelectView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SettingSelectView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SettingSelectView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558476, this, true);
      this.initView();
      String var4 = var1.obtainStyledAttributes(var2, R.styleable.SettingSelectStyle).getString(0);
      if (!TextUtils.isEmpty(var4)) {
         this.tv_title.setText(var4);
      }

   }

   private void initView() {
      this.relativeLayout = (RelativeLayout)this.mView.findViewById(2131363090);
      this.iv_select = (ImageView)this.mView.findViewById(2131362640);
      this.tv_title = (TextView)this.mView.findViewById(2131363710);
      this.relativeLayout.setOnClickListener(this);
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131363090) {
         OnItemClickListener var2 = this.mOnItemClickListener;
         if (var2 != null) {
            var2.onClick(this, this.isSelect);
         }
      }

   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
      if (var1) {
         this.iv_select.setImageResource(2131233195);
      } else {
         this.iv_select.setImageResource(2131233194);
      }

   }

   public interface OnItemClickListener {
      void onClick(View var1, boolean var2);
   }
}
