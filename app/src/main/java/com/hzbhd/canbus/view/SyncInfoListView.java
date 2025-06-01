package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SyncInfoListView extends LinearLayout {
   public SyncInfoListView(Context var1) {
      super(var1);
   }

   public SyncInfoListView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   // $FF: synthetic method
   static void lambda$initItem$0(OnListItemClickListener var0, int var1) {
      if (var0 != null) {
         var0.onItemClick(var1);
      }

   }

   public SyncListItemView getItem(int var1) {
      return (SyncListItemView)this.getChildAt(var1);
   }

   public void initItem(Context var1, int var2, OnListItemClickListener var3) {
      for(int var4 = 0; var4 < var2; ++var4) {
         SyncListItemView var5 = new SyncListItemView(var1);
         this.addView(var5, new LinearLayout.LayoutParams(-1, -2));
         var5.setOnClickListener(new SyncInfoListView$$ExternalSyntheticLambda0(var3, var4));
      }

   }

   public interface OnListItemClickListener {
      void onItemClick(int var1);
   }
}
