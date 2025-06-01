package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;

public class RowBottomBtnView extends LinearLayout {
   public RowBottomBtnView(Context var1) {
      super(var1);
   }

   public RowBottomBtnView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public OriginalBottomBtnItemView getBtnItemView(int var1) {
      return (OriginalBottomBtnItemView)this.getChildAt(var1);
   }

   public void initButton(Context var1, String[] var2, OnOriginalBottomBtnClickListener var3) {
      if (var2 != null) {
         this.removeAllViews();

         for(int var4 = 0; var4 < var2.length; ++var4) {
            OriginalBottomBtnItemView var5 = new OriginalBottomBtnItemView(var1, var2[var4]);
            this.addView(var5, new LinearLayout.LayoutParams(0, -1, 1.0F));
            var5.setOnItemClickListener(new OriginalBottomBtnItemView.OnItemClickListener(this, var3, var4) {
               final RowBottomBtnView this$0;
               final int val$finalI;
               final OnOriginalBottomBtnClickListener val$onItemClickListener;

               {
                  this.this$0 = var1;
                  this.val$onItemClickListener = var2;
                  this.val$finalI = var3;
               }

               public void onClick() {
                  OnOriginalBottomBtnClickListener var1 = this.val$onItemClickListener;
                  if (var1 != null) {
                     var1.onClickBottomBtnItem(this.val$finalI);
                  }

               }
            });
         }

      }
   }
}
