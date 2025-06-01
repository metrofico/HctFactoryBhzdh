package com.hzbhd.canbus.car._68;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MyPanoramicView extends RelativeLayout {
   int mRearCarStatus;
   private ImageView mRearCarWarnLeft1;
   private ImageView mRearCarWarnLeft2;
   private ImageView mRearCarWarnRight1;
   private ImageView mRearCarWarnRight2;

   public MyPanoramicView(Context var1) {
      super(var1);
      View var2 = LayoutInflater.from(var1).inflate(2131558807, this);
      this.mRearCarWarnLeft1 = (ImageView)var2.findViewById(2131362474);
      this.mRearCarWarnLeft2 = (ImageView)var2.findViewById(2131362477);
      this.mRearCarWarnRight1 = (ImageView)var2.findViewById(2131362473);
      this.mRearCarWarnRight2 = (ImageView)var2.findViewById(2131362475);
   }

   void updatePanoramicView() {
      this.mRearCarWarnLeft1.setVisibility(4);
      this.mRearCarWarnLeft2.setVisibility(4);
      this.mRearCarWarnRight1.setVisibility(4);
      this.mRearCarWarnRight2.setVisibility(4);
      int var1 = this.mRearCarStatus;
      if (var1 == 1) {
         this.mRearCarWarnLeft1.setVisibility(0);
         this.mRearCarWarnLeft2.setVisibility(0);
      } else if (var1 == 2) {
         this.mRearCarWarnRight1.setVisibility(0);
         this.mRearCarWarnRight2.setVisibility(0);
      } else if (var1 == 3) {
         this.mRearCarWarnLeft1.setVisibility(0);
         this.mRearCarWarnLeft2.setVisibility(0);
         this.mRearCarWarnRight1.setVisibility(0);
         this.mRearCarWarnRight2.setVisibility(0);
      }

   }
}
