package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._304.bean.MainPageEntity;
import java.util.List;

public class MainView extends LinearLayout {
   private List mList;
   private LinearLayout mLlTitles;
   private RelativeLayout mRlContent;

   public MainView(Context var1, AttributeSet var2) {
      super(var1, var2);
      LayoutInflater.from(var1).inflate(2131558504, this);
      this.findViews();
   }

   private void findViews() {
      this.mLlTitles = (LinearLayout)this.findViewById(2131362808);
      this.mRlContent = (RelativeLayout)this.findViewById(2131363188);
   }

   private Button getButton(Context var1) {
      LinearLayout.LayoutParams var3 = new LinearLayout.LayoutParams(0, -1, 1.0F);
      Button var2 = new Button(var1);
      var2.setBackgroundResource(2131231166);
      var2.setTextSize(var1.getResources().getDimension(2131169566));
      var2.setTextColor(var1.getColor(2131100061));
      var2.setLayoutParams(var3);
      return var2;
   }

   private ImageView getDivider(Context var1) {
      LinearLayout.LayoutParams var2 = new LinearLayout.LayoutParams(-2, -2);
      var2.bottomMargin = 2;
      var2.setMarginEnd(2);
      ImageView var3 = new ImageView(var1);
      var3.setImageResource(2131231063);
      var3.setLayoutParams(var2);
      return var3;
   }

   private void refreshTopView(int var1) {
      int var4 = this.mLlTitles.getChildCount();
      byte var3 = 0;
      int var2 = var1;

      for(var1 = var3; var1 < var4; ++var1) {
         View var6 = this.mLlTitles.getChildAt(var1);
         if (var6 instanceof Button) {
            Button var7 = (Button)var6;
            boolean var5;
            if (var1 == var2) {
               var5 = true;
            } else {
               var5 = false;
            }

            var7.setSelected(var5);
         } else {
            ++var2;
         }
      }

   }

   public void initView(Context var1, List var2) {
      this.mList = var2;
      int var4 = var2.size();

      for(int var3 = 0; var3 < var2.size(); ++var3) {
         MainPageEntity var5 = (MainPageEntity)var2.get(var3);
         Button var6 = this.getButton(var1);
         var6.setText(var5.getTitle());
         var6.setOnClickListener(new MainView$$ExternalSyntheticLambda0(this, var3, var5));
         this.mLlTitles.addView(var6);
         if (var3 != var4 - 1) {
            this.mLlTitles.addView(this.getDivider(var1));
         }
      }

      this.reset();
   }

   // $FF: synthetic method
   void lambda$initView$0$com_hzbhd_canbus_car_cus__304_view_MainView(int var1, MainPageEntity var2, View var3) {
      this.refreshTopView(var1);
      this.mRlContent.removeAllViews();
      this.mRlContent.addView(var2.getView());
   }

   public void reset() {
      this.mRlContent.removeAllViews();
      this.refreshTopView(0);
      this.mRlContent.addView(((MainPageEntity)this.mList.get(0)).getView());
   }
}
