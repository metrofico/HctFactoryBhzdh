package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.util.CommUtil;

public class TempSetView extends RelativeLayout {
   private ImageView mIvDown;
   private ImageView mIvUp;
   private OnAirTempTouchListener mOnAirTempTouchListener;
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListener;
   private MarqueeTextView mTvValue;

   public TempSetView(Context var1) {
      super(var1);
   }

   public TempSetView(Context var1, AttributeSet var2) {
      super(var1, var2);
      View var3 = LayoutInflater.from(var1).inflate(2131558857, this);
      this.mIvUp = (ImageView)var3.findViewById(2131362659);
      this.mIvDown = (ImageView)var3.findViewById(2131362563);
      this.mTvValue = (MarqueeTextView)var3.findViewById(2131363714);
      this.mIvUp.setOnClickListener(new View.OnClickListener(this) {
         final TempSetView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnUpDownClickListener != null) {
               this.this$0.mOnUpDownClickListener.onClickUp();
            }

         }
      });
      this.mIvDown.setOnClickListener(new View.OnClickListener(this) {
         final TempSetView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnUpDownClickListener != null) {
               this.this$0.mOnUpDownClickListener.onClickDown();
            }

         }
      });
      this.mIvUp.setOnTouchListener(new View.OnTouchListener(this) {
         final TempSetView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (this.this$0.mOnAirTempTouchListener != null) {
               this.this$0.mOnAirTempTouchListener.onTouchUp(var2);
            }

            return false;
         }
      });
      this.mIvDown.setOnTouchListener(new View.OnTouchListener(this) {
         final TempSetView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (this.this$0.mOnAirTempTouchListener != null) {
               this.this$0.mOnAirTempTouchListener.onTouchDown(var2);
            }

            return false;
         }
      });
   }

   public TempSetView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public void setControllable(boolean var1) {
      if (var1) {
         this.mIvUp.setVisibility(0);
         this.mIvDown.setVisibility(0);
      } else {
         this.mIvUp.setVisibility(8);
         this.mIvDown.setVisibility(8);
      }

   }

   public void setOnUpDownClickListener(OnAirTemperatureUpDownClickListener var1) {
      this.mOnUpDownClickListener = var1;
   }

   public void setOnUpDownTouchListener(OnAirTempTouchListener var1) {
      this.mOnAirTempTouchListener = var1;
   }

   public void setValue(String var1) {
      var1 = CommUtil.temperatureUnitSwitch(var1);
      this.mTvValue.setText(var1);
   }
}
