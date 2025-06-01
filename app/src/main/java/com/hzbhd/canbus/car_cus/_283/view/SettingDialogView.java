package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import java.util.List;

public class SettingDialogView extends RelativeLayout implements View.OnClickListener {
   protected int id;
   private ImageView iv_title;
   private View lineView;
   protected List lists;
   public Context mContext;
   private OnItemClickListener mOnItemClickListener;
   private View mView;
   private RelativeLayout relativeLayout;
   private ImageView rightImageView;
   protected LinearLayout rightLinear;
   private TextView tv_title;
   private TextView tv_value;

   public SettingDialogView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SettingDialogView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SettingDialogView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558474, this, true);
      this.initView();
      TypedArray var5 = var1.obtainStyledAttributes(var2, R.styleable.SettingSelectStyle);
      String var4 = var5.getString(0);
      Drawable var6 = var5.getDrawable(1);
      if (var6 != null) {
         this.iv_title.setVisibility(0);
         this.iv_title.setImageDrawable(var6);
      } else {
         this.iv_title.setVisibility(8);
         this.iv_title.setImageDrawable((Drawable)null);
      }

      if (!TextUtils.isEmpty(var4)) {
         this.tv_title.setText(var4);
      }

   }

   private void initView() {
      this.relativeLayout = (RelativeLayout)this.mView.findViewById(2131363090);
      this.rightLinear = (LinearLayout)this.mView.findViewById(2131363151);
      this.tv_title = (TextView)this.mView.findViewById(2131363710);
      this.tv_value = (TextView)this.mView.findViewById(2131363714);
      this.iv_title = (ImageView)this.mView.findViewById(2131362652);
      this.rightImageView = (ImageView)this.mView.findViewById(2131363150);
      this.lineView = this.mView.findViewById(2131362757);
      this.relativeLayout.setOnClickListener(this);
   }

   private void setTextValue(int var1, String var2) {
      this.tv_value.setText(var2 + ((SettingDialogBean)this.lists.get(var1)).getUnit());
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131363090 && this.lists != null) {
         this.setSelect();
         DialogUtils.showSettingDialog(this.mContext, this.rightLinear, this.lists, this.id, new SettingDialogAdapter.OnItemClickListener(this) {
            final SettingDialogView this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(int var1) {
               this.this$0.id = var1;
               if (this.this$0.mOnItemClickListener != null) {
                  this.this$0.mOnItemClickListener.onClick(this.this$0, var1);
               }

            }
         });
      }

   }

   public void setEnable(boolean var1) {
      if (var1) {
         this.tv_title.setTextColor(this.mContext.getColor(2131100061));
         this.tv_value.setTextColor(this.mContext.getColor(2131100061));
         this.rightImageView.setImageResource(2131233196);
         this.lineView.setBackgroundResource(2131233191);
      } else {
         this.tv_title.setTextColor(this.mContext.getColor(2131099649));
         this.tv_value.setTextColor(this.mContext.getColor(2131099649));
         this.rightImageView.setImageResource(2131233198);
         this.lineView.setBackgroundResource(2131233197);
      }

      this.relativeLayout.setEnabled(var1);
   }

   public void setList(List var1) {
      this.lists = var1;
      if (var1 != null && var1.size() > 0) {
         for(int var2 = 0; var2 < var1.size(); ++var2) {
            if (((SettingDialogBean)var1.get(var2)).isSelect()) {
               this.id = var2;
               this.setTextValue(var2, ((SettingDialogBean)var1.get(var2)).getValue());
               return;
            }
         }
      }

   }

   public void setListFirst(List var1) {
      this.lists = var1;
      this.id = 0;
      this.setTextValue(0, ((SettingDialogBean)var1.get(0)).getValue());
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   protected void setSelect() {
      for(int var1 = 0; var1 < this.lists.size(); ++var1) {
         if (var1 == this.id) {
            ((SettingDialogBean)this.lists.get(var1)).setSelect(true);
         } else {
            ((SettingDialogBean)this.lists.get(var1)).setSelect(false);
         }
      }

   }

   public void setSelect(int var1) {
      this.id = var1;
      List var2 = this.lists;
      if (var2 != null && var2.size() > 0 && var1 >= 0 && var1 < this.lists.size()) {
         this.setTextValue(var1, ((SettingDialogBean)this.lists.get(var1)).getValue());
      }

   }

   public void setSelectOnValue(String var1) {
      List var3 = this.lists;
      if (var3 != null && var3.size() > 0) {
         for(int var2 = 0; var2 < this.lists.size(); ++var2) {
            if (((SettingDialogBean)this.lists.get(var2)).getValue().equals(var1)) {
               this.id = var2;
               this.setTextValue(var2, ((SettingDialogBean)this.lists.get(var2)).getValue());
               return;
            }
         }
      }

   }

   public interface OnItemClickListener {
      void onClick(View var1, int var2);
   }
}
