package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SyncSoftKeyView extends LinearLayout {
   public SyncSoftKeyView(Context var1) {
      super(var1);
   }

   public SyncSoftKeyView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   // $FF: synthetic method
   static void lambda$initButton$0(OnSoftKeyClickListener var0, int var1) {
      if (var0 != null) {
         var0.onSoftKeyClick(var1);
      }

   }

   public SyncBtnItemView getItem(int var1) {
      return (SyncBtnItemView)this.getChildAt(var1);
   }

   public void initButton(Context var1, int var2, OnSoftKeyClickListener var3) {
      for(int var4 = 0; var4 < var2; ++var4) {
         SyncBtnItemView var5 = new SyncBtnItemView(var1);
         this.addView(var5, new LinearLayout.LayoutParams(0, -1, 1.0F));
         var5.setOnClickListener(new SyncSoftKeyView$$ExternalSyntheticLambda0(var3, var4));
      }

   }

   public interface OnSoftKeyClickListener {
      void onSoftKeyClick(int var1);
   }
}
