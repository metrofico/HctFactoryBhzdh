package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;

public class LineSmallBtnView extends LinearLayout {
   public LineSmallBtnView(Context var1) {
      super(var1);
   }

   public LineSmallBtnView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public void initButton(Context var1, int[] var2, int var3, OnAirBtnClickListener var4) {
      if (var2 != null) {
         this.setGravity(var3);

         for(var3 = 0; var3 < var2.length; ++var3) {
            SmallBtnItemView var5 = new SmallBtnItemView(var1, var2[var3]);
            this.addView(var5, new LinearLayout.LayoutParams(-2, -1));
            var5.setOnItemClickListener(new SmallBtnItemView.OnItemClickListener(this, var4, var3) {
               final LineSmallBtnView this$0;
               final int val$finalI;
               final OnAirBtnClickListener val$onItemClickListener;

               {
                  this.this$0 = var1;
                  this.val$onItemClickListener = var2;
                  this.val$finalI = var3;
               }

               public void onClick() {
                  OnAirBtnClickListener var1 = this.val$onItemClickListener;
                  if (var1 != null) {
                     var1.onClickItem(this.val$finalI);
                  }

               }
            });
         }

      }
   }
}
