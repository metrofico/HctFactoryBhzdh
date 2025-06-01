package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SyncTopIconIconView extends LinearLayout {
   public SyncTopIconIconView(Context var1) {
      super(var1);
   }

   public SyncTopIconIconView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public ImageView getItem(int var1) {
      return (ImageView)this.getChildAt(var1);
   }

   public void initIcon(Context var1, int var2) {
      for(int var3 = 0; var3 < var2; ++var3) {
         ImageView var4 = new ImageView(var1);
         var4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0F));
         this.addView(var4);
      }

   }
}
