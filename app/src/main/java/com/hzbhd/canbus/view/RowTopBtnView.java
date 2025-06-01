package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;

public class RowTopBtnView extends LinearLayout {
   public RowTopBtnView(Context var1) {
      super(var1);
   }

   public RowTopBtnView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public void clean() {
      this.removeAllViews();
   }

   public OriginalTopBtnItemView getBtnItemView(int var1) {
      return (OriginalTopBtnItemView)this.getChildAt(var1);
   }

   public void initButton(Context var1, String[] var2, boolean var3, OnOriginalTopBtnClickListener var4) {
      if (var2 != null) {
         for(int var5 = 0; var5 < var2.length; ++var5) {
            OriginalTopBtnItemView var6 = new OriginalTopBtnItemView(var1, var2[var5]);
            this.addView(var6, new LinearLayout.LayoutParams(0, -1, 1.0F));
            var6.setOnItemClickListener(new OriginalTopBtnItemView.OnItemClickListener(this, var4, var5) {
               final RowTopBtnView this$0;
               final int val$finalI;
               final OnOriginalTopBtnClickListener val$onItemClickListener;

               {
                  this.this$0 = var1;
                  this.val$onItemClickListener = var2;
                  this.val$finalI = var3;
               }

               public void onClick() {
                  OnOriginalTopBtnClickListener var1 = this.val$onItemClickListener;
                  if (var1 != null) {
                     var1.onClickTopBtnItem(this.val$finalI);
                  }

               }
            });
         }

      }
   }
}
