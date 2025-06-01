package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.R;

public class SettingProgressView extends RelativeLayout implements View.OnClickListener {
   private SeekBar bigSeekBar;
   private RelativeLayout bigSeekbarLayout;
   private float interval;
   private String mBigValue;
   private Context mContext;
   private OnItemClickListener mOnItemClickListener;
   private String mSmallValue;
   private View mView;
   private int maxProgress;
   private int minProgress;
   private RelativeLayout relativeLayout;
   private SeekBar smallSeekbar;
   private TextView tv_title;
   private TextView tv_value;
   private String unit;

   public SettingProgressView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SettingProgressView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SettingProgressView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.maxProgress = 1;
      this.minProgress = 0;
      this.interval = 1.0F;
      this.unit = "";
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558475, this, true);
      this.mBigValue = var1.getString(2131760448);
      this.mSmallValue = var1.getString(2131760754);
      this.initView();
      this.initData();
      String var4 = var1.obtainStyledAttributes(var2, R.styleable.SettingSelectStyle).getString(0);
      if (!TextUtils.isEmpty(var4)) {
         this.tv_title.setText(var4);
      }

   }

   private void initData() {
      this.bigSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final SettingProgressView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            if (this.this$0.mOnItemClickListener != null && var3) {
               OnItemClickListener var5 = this.this$0.mOnItemClickListener;
               SettingProgressView var4 = this.this$0;
               var5.onProgressChanged(var4, var4.minProgress + var2);
            }

            this.this$0.setValue(var2);
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      });
   }

   private void initView() {
      this.relativeLayout = (RelativeLayout)this.mView.findViewById(2131363090);
      this.bigSeekbarLayout = (RelativeLayout)this.mView.findViewById(2131361980);
      this.tv_title = (TextView)this.mView.findViewById(2131363710);
      this.tv_value = (TextView)this.mView.findViewById(2131363714);
      this.smallSeekbar = (SeekBar)this.mView.findViewById(2131363335);
      this.bigSeekBar = (SeekBar)this.mView.findViewById(2131361979);
      this.smallSeekbar.setEnabled(false);
      this.relativeLayout.setOnClickListener(this);
   }

   public float getInterval() {
      return this.interval;
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131363090) {
         if (this.bigSeekbarLayout.getVisibility() == 0) {
            this.bigSeekbarLayout.setVisibility(8);
            this.tv_value.setVisibility(8);
            this.smallSeekbar.setVisibility(0);
            this.smallSeekbar.setProgress(this.bigSeekBar.getProgress());
         } else {
            this.bigSeekbarLayout.setVisibility(0);
            this.tv_value.setVisibility(0);
            this.smallSeekbar.setVisibility(8);
         }
      }

   }

   public void setAll(int var1, float var2, String var3) {
      this.maxProgress = var1;
      this.interval = var2;
      this.unit = var3;
      this.smallSeekbar.setMax(var1);
      this.bigSeekBar.setMax(var1);
   }

   public void setBigAndSmallValueText(String var1, String var2) {
      this.mBigValue = var2;
      this.mSmallValue = var1;
   }

   public void setInterval(float var1) {
      this.interval = var1;
   }

   public void setMaxAndMinProgress(int var1, int var2) {
      var2 -= var1;
      this.minProgress = var1;
      this.maxProgress = var2;
      this.smallSeekbar.setMax(var2);
      this.bigSeekBar.setMax(var2);
   }

   public void setMaxProgress(int var1) {
      this.maxProgress = var1;
      this.smallSeekbar.setMax(var1);
      this.bigSeekBar.setMax(var1);
   }

   public void setMinProgress(int var1) {
      this.minProgress = var1;
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public void setProgressNoInterval(int var1) {
      this.setValue(var1);
      this.bigSeekBar.setProgress(var1);
      this.smallSeekbar.setProgress(var1);
   }

   public void setUnit(String var1) {
      this.unit = var1;
   }

   public void setValue(int var1) {
      if (var1 == 0 && !TextUtils.isEmpty(this.mSmallValue)) {
         this.tv_value.setText(this.mSmallValue);
      } else if (var1 == this.maxProgress && !TextUtils.isEmpty(this.mBigValue)) {
         this.tv_value.setText(this.mBigValue);
      } else {
         this.tv_value.setText((float)this.minProgress + (float)var1 * this.interval + this.unit);
      }
   }

   public void setValueProgress(int var1) {
      var1 = (int)((float)var1 / this.interval) - this.minProgress;
      this.setValue(var1);
      this.bigSeekBar.setProgress(var1);
      this.smallSeekbar.setProgress(var1);
   }

   public interface OnItemClickListener {
      void onProgressChanged(View var1, int var2);
   }
}
