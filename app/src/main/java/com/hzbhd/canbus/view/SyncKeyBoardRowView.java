package com.hzbhd.canbus.view;

import android.content.Context;
import android.widget.LinearLayout;

public class SyncKeyBoardRowView extends LinearLayout {
   public SyncKeyBoardRowView(Context var1) {
      super(var1);
   }

   public void initView(Context var1, String[] var2, SyncKeyBoardView.OnKeyBoardBtnClickListener var3) {
      int var5 = var1.getResources().getDimensionPixelOffset(2131170676);
      int var4 = 0;
      LinearLayout.LayoutParams var6 = new LinearLayout.LayoutParams(0, var5, 1.0F);

      for(var5 = var2.length; var4 < var5; ++var4) {
         String var7 = var2[var4];
         SyncKeyBoardItemView var8 = new SyncKeyBoardItemView(var1);
         var8.initView(var7, new SyncKeyBoardItemView.OnClickListener(this, var3, var7) {
            final SyncKeyBoardRowView this$0;
            final String val$action;
            final SyncKeyBoardView.OnKeyBoardBtnClickListener val$onRowBtnClickListener;

            {
               this.this$0 = var1;
               this.val$onRowBtnClickListener = var2;
               this.val$action = var3;
            }

            public void onClick() {
               SyncKeyBoardView.OnKeyBoardBtnClickListener var1 = this.val$onRowBtnClickListener;
               if (var1 != null) {
                  var1.onBtnClick(this.val$action);
               }

            }

            public void onLongClick() {
               SyncKeyBoardView.OnKeyBoardBtnClickListener var1 = this.val$onRowBtnClickListener;
               if (var1 != null) {
                  var1.onBtnLongClick(this.val$action);
               }

            }
         });
         var8.setLayoutParams(var6);
         this.addView(var8);
      }

   }
}
