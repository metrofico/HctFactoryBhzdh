package com.hzbhd.canbus.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.R;

public class BcSettingSeekBarView extends RelativeLayout {
   private SeekBar mSeekBar;
   private TextView mTitleTv;
   private TextView mValueTv;

   public BcSettingSeekBarView(Context var1) {
      super(var1);
   }

   public BcSettingSeekBarView(Context var1, AttributeSet var2) {
      super(var1, var2);
      View var3 = LayoutInflater.from(var1).inflate(2131558858, this);
      this.mSeekBar = (SeekBar)var3.findViewById(2131363304);
      this.mTitleTv = (TextView)var3.findViewById(2131363710);
      this.mValueTv = (TextView)var3.findViewById(2131363714);
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.BcSettingSeekBarStyle);
      String var5 = var4.getString(0);
      var4.recycle();
      this.mTitleTv.setText(var5);
   }

   public BcSettingSeekBarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public int getProgress() {
      return this.mSeekBar.getProgress();
   }

   public SeekBar getSeekbar() {
      return this.mSeekBar;
   }

   public void setMax(int var1) {
      this.mSeekBar.setMax(var1);
   }

   public void setMin(int var1) {
      this.mSeekBar.setMax(var1);
   }

   public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener var1) {
      this.mSeekBar.setOnSeekBarChangeListener(var1);
   }

   public void setProgress(int var1) {
      this.mSeekBar.setProgress(var1);
      this.mValueTv.setText(String.valueOf(var1));
   }

   public void setValue(int var1) {
      this.mValueTv.setText(String.valueOf(var1));
   }

   public void setValue(String var1) {
      this.mValueTv.setText(var1);
   }
}
