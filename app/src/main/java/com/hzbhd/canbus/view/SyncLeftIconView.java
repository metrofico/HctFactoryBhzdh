package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.android.internal.util.ArrayUtils;

public class SyncLeftIconView extends LinearLayout {
   public SyncLeftIconView(Context var1) {
      super(var1);
   }

   public SyncLeftIconView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public void initButton(Context var1, String[] var2, OnLeftIconClickListener var3) {
      if (!ArrayUtils.isEmpty(var2)) {
         int var6 = var2.length;

         for(int var5 = 0; var5 < var6; ++var5) {
            String var8 = var2[var5];
            ImageButton var7 = new ImageButton(var1);
            var8.hashCode();
            switch (var8) {
               case "media":
                  var7.setImageResource(2131233646);
                  break;
               case "phone":
                  var7.setImageResource(2131233647);
                  break;
               case "voice":
                  var7.setImageResource(2131233648);
                  break;
               case "keyboard":
                  var7.setImageResource(2131233645);
            }

            var7.setBackgroundResource(2131234801);
            var7.setOnClickListener(new View.OnClickListener(this, var3, var8) {
               final SyncLeftIconView this$0;
               final String val$action;
               final OnLeftIconClickListener val$onLeftIconClickListener;

               {
                  this.this$0 = var1;
                  this.val$onLeftIconClickListener = var2;
                  this.val$action = var3;
               }

               public void onClick(View var1) {
                  OnLeftIconClickListener var2 = this.val$onLeftIconClickListener;
                  if (var2 != null) {
                     var2.onIconClick(this.val$action);
                  }

               }
            });
            LinearLayout.LayoutParams var9 = new LinearLayout.LayoutParams(this.getResources().getDimensionPixelOffset(2131168902), -2);
            var4 = this.getResources().getDimensionPixelOffset(2131167415);
            var9.topMargin = var4;
            var9.bottomMargin = var4;
            var7.setLayoutParams(var9);
            this.addView(var7);
         }

      }
   }

   public interface OnLeftIconClickListener {
      void onIconClick(String var1);
   }
}
