package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class WindSpeedView extends LinearLayout {
   private List mImageViewList;
   private int mTotalWindLevel = 0;

   public WindSpeedView(Context var1) {
      super(var1);
      this.setOrientation(0);
      this.setGravity(17);
   }

   public WindSpeedView(Context var1, AttributeSet var2) {
      super(var1, var2);
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

   public void setTotalWindLevel(Context var1, int var2) {
      this.mImageViewList = new ArrayList();
      this.mTotalWindLevel = var2;
      float var3 = (float)this.mTotalWindLevel;
      var2 = 0;
      LinearLayout.LayoutParams var4 = new LinearLayout.LayoutParams(0, -1, var3);
      var4.gravity = 17;
      this.setLayoutParams(var4);

      while(var2 < this.mTotalWindLevel) {
         ImageView var5 = (ImageView)LayoutInflater.from(var1).inflate(2131558868, (ViewGroup)null).findViewById(2131362578);
         ((ViewGroup)var5.getParent()).removeAllViews();
         this.addView(var5);
         this.mImageViewList.add(var5);
         ++var2;
      }

   }
}
