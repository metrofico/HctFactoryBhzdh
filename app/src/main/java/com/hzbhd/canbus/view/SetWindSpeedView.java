package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import java.util.ArrayList;
import java.util.List;

public class SetWindSpeedView extends RelativeLayout {
   private ImageView mAutoIv;
   private ImageButton mDownIb;
   private List mImageViewList;
   private int mTotalWindLevel = 0;
   private ImageButton mUpIb;
   private float mWeight = 0.4F;
   private LinearLayout mWindSpeedLl;

   public SetWindSpeedView(Context var1) {
      super(var1);
   }

   public SetWindSpeedView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private void setTotalWindLevel(Context var1, int var2) {
      this.mImageViewList = new ArrayList();
      this.mTotalWindLevel = var2;

      for(var2 = 0; var2 < this.mTotalWindLevel; ++var2) {
         ImageView var3 = (ImageView)LayoutInflater.from(var1).inflate(2131558868, (ViewGroup)null).findViewById(2131362578);
         ((ViewGroup)var3.getParent()).removeAllViews();
         this.mWindSpeedLl.addView(var3);
         this.mImageViewList.add(var3);
      }

   }

   public void initViews(Context var1, boolean var2, int var3, OnAirWindSpeedUpDownClickListener var4) {
      View var5 = LayoutInflater.from(var1).inflate(2131558855, this);
      this.mWindSpeedLl = (LinearLayout)var5.findViewById(2131362786);
      this.mUpIb = (ImageButton)var5.findViewById(2131362425);
      this.mDownIb = (ImageButton)var5.findViewById(2131362387);
      this.mAutoIv = (ImageView)var5.findViewById(2131362533);
      this.mUpIb.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetWindSpeedView this$0;
         final OnAirWindSpeedUpDownClickListener val$onClickListener;

         {
            this.this$0 = var1;
            this.val$onClickListener = var2;
         }

         public void onClick(View var1) {
            OnAirWindSpeedUpDownClickListener var2 = this.val$onClickListener;
            if (var2 != null) {
               var2.onClickRight();
            }

         }
      });
      this.mDownIb.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetWindSpeedView this$0;
         final OnAirWindSpeedUpDownClickListener val$onClickListener;

         {
            this.this$0 = var1;
            this.val$onClickListener = var2;
         }

         public void onClick(View var1) {
            OnAirWindSpeedUpDownClickListener var2 = this.val$onClickListener;
            if (var2 != null) {
               var2.onClickLeft();
            }

         }
      });
      if (var2) {
         this.mUpIb.setVisibility(0);
         this.mDownIb.setVisibility(0);
      } else {
         this.mUpIb.setVisibility(4);
         this.mDownIb.setVisibility(4);
      }

      if (var1.getResources().getConfiguration().orientation == 1) {
         this.mWeight = 0.5F;
      }

      LinearLayout.LayoutParams var6 = new LinearLayout.LayoutParams(0, -1, (float)((int)((float)var3 * this.mWeight)));
      var6.gravity = 17;
      this.setLayoutParams(var6);
      this.setTotalWindLevel(var1, var3);
   }

   public void setAuto(boolean var1) {
      if (var1) {
         this.mWindSpeedLl.setVisibility(4);
         this.mAutoIv.setVisibility(0);
      } else {
         this.mWindSpeedLl.setVisibility(0);
         this.mAutoIv.setVisibility(4);
      }

   }

   public void setCurWindSpeed(int var1) {
      for(int var2 = 0; var2 < this.mImageViewList.size(); ++var2) {
         if (var2 < var1) {
            ((ImageView)this.mImageViewList.get(var2)).setImageResource(2131234227);
         } else {
            ((ImageView)this.mImageViewList.get(var2)).setImageResource(2131234228);
         }
      }

   }
}
