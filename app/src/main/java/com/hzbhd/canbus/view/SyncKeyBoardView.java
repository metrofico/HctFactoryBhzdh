package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SyncKeyBoardView extends LinearLayout {
   private OnKeyBoardBtnClickListener mOnKeyBoardBtnClickListener;

   public SyncKeyBoardView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public void initKeyBoard(Context var1, String[][] var2, OnKeyBoardBtnClickListener var3) {
      this.mOnKeyBoardBtnClickListener = var3;
      int var5 = var2.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         String[] var7 = var2[var4];
         SyncKeyBoardRowView var6 = new SyncKeyBoardRowView(var1);
         var6.initView(var1, var7, var3);
         this.addView(var6);
      }

   }

   public void rebuild(Context var1, String[][] var2) {
      this.initKeyBoard(var1, var2, this.mOnKeyBoardBtnClickListener);
   }

   public interface OnKeyBoardBtnClickListener {
      void onBtnClick(String var1);

      void onBtnLongClick(String var1);
   }
}
