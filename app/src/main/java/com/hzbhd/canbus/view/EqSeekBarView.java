package com.hzbhd.canbus.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.R;

public class EqSeekBarView extends RelativeLayout {
   private TextView mChannelTv;
   private TextView mChannelValueTv;
   private ImageView mIvMin;
   private ImageView mIvPlus;
   private OnMinPlusClickListener mOnMinAddClickListener;
   private SeekBar mSeekBar;

   public EqSeekBarView(Context var1) {
      super(var1);
   }

   public EqSeekBarView(Context var1, AttributeSet var2) {
      super(var1, var2);
      View var3 = LayoutInflater.from(var1).inflate(2131558859, this);
      this.mIvMin = (ImageView)var3.findViewById(2131362611);
      this.mIvPlus = (ImageView)var3.findViewById(2131362531);
      this.mSeekBar = (SeekBar)var3.findViewById(2131363304);
      this.mChannelTv = (TextView)var3.findViewById(2131363601);
      this.mChannelValueTv = (TextView)var3.findViewById(2131363602);
      TypedArray var5 = var1.obtainStyledAttributes(var2, R.styleable.EqSeekBarStyle);
      String var4 = var5.getString(0);
      var5.recycle();
      this.mChannelTv.setText(var4);
      this.mIvMin.setOnClickListener(new View.OnClickListener(this) {
         final EqSeekBarView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnMinAddClickListener != null) {
               this.this$0.mOnMinAddClickListener.onClickMin(this.this$0.mSeekBar);
            }

         }
      });
      this.mIvPlus.setOnClickListener(new View.OnClickListener(this) {
         final EqSeekBarView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnMinAddClickListener != null) {
               this.this$0.mOnMinAddClickListener.onClickPlus(this.this$0.mSeekBar);
            }

         }
      });
   }

   public EqSeekBarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public SeekBar getSeekBar() {
      return this.mSeekBar;
   }

   public void setCanMinPlus(boolean var1) {
      if (var1) {
         this.mIvMin.setVisibility(0);
         this.mIvPlus.setVisibility(0);
      } else {
         this.mIvMin.setVisibility(8);
         this.mIvPlus.setVisibility(8);
      }

   }

   public void setEnabled(boolean var1) {
      super.setEnabled(var1);
      this.mSeekBar.setEnabled(var1);
   }

   public void setOnPlusMinClickListener(OnMinPlusClickListener var1) {
      this.mOnMinAddClickListener = var1;
   }

   public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener var1) {
      this.mSeekBar.setOnSeekBarChangeListener(var1);
   }

   public void setProgress(int var1) {
      this.mSeekBar.setProgress(var1);
   }

   public void setSeekBarTouchable(boolean var1) {
      if (!var1) {
         this.mSeekBar.setOnTouchListener(new View.OnTouchListener(this) {
            final EqSeekBarView this$0;

            {
               this.this$0 = var1;
            }

            public boolean onTouch(View var1, MotionEvent var2) {
               return true;
            }
         });
      }

   }

   public void setTitle(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         this.mChannelTv.setText(var1);
      }
   }

   public void setValue(String var1) {
      this.mChannelValueTv.setText(var1);
   }

   public interface OnMinPlusClickListener {
      void onClickMin(SeekBar var1);

      void onClickPlus(SeekBar var1);
   }
}
