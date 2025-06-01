package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;

public class LineBtnView extends LinearLayout {
   private boolean mCanClick;

   public LineBtnView(Context var1) {
      super(var1);
   }

   public LineBtnView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   private boolean isInDisableBtnArray(String[] var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            if (var1[var3].equals(var2)) {
               return true;
            }
         }

         return false;
      }
   }

   public BtnItemView getBtnItemView(int var1) {
      return (BtnItemView)this.getChildAt(var1);
   }

   public void initButton(Context var1, String[] var2, boolean var3, String[] var4, OnAirBtnClickListener var5) {
      if (var2 != null) {
         for(int var6 = 0; var6 < var2.length; ++var6) {
            if (var3) {
               if (this.isInDisableBtnArray(var4, var2[var6])) {
                  this.mCanClick = false;
               } else {
                  this.mCanClick = true;
               }
            }

            BtnItemView var7 = new BtnItemView(var1, var2[var6], this.mCanClick);
            this.addView(var7, new LinearLayout.LayoutParams(0, -1, 1.0F));
            var7.setOnItemClickListener(new BtnItemView.OnItemClickListener(this, var5, var6) {
               final LineBtnView this$0;
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
