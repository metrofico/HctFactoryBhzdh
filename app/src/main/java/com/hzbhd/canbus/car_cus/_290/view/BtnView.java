package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.LogUtil;

public class BtnView extends ImageButton {
   private boolean isSelect;
   private int noSelectIcon;
   private int selectIcon;

   public BtnView(Context var1) {
      super(var1);
   }

   public BtnView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public BtnView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.ChengWeiComBtnViewStyle);
      this.selectIcon = var4.getResourceId(1, 0);
      this.noSelectIcon = var4.getResourceId(0, 0);
      this.initView();
   }

   private void initView() {
      this.setImageResource(this.noSelectIcon);
   }

   public int getNoSelectIcon() {
      return this.noSelectIcon;
   }

   public int getSelectIcon() {
      return this.selectIcon;
   }

   public boolean isSelect() {
      return this.isSelect;
   }

   public boolean onTouchEvent(MotionEvent var1) {
      int var2 = var1.getAction();
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 3) {
               return true;
            }
         } else {
            this.performClick();
         }

         if (!this.isSelect) {
            this.setImageResource(this.noSelectIcon);
         }
      } else {
         this.setImageResource(this.selectIcon);
      }

      return true;
   }

   public boolean performClick() {
      return super.performClick();
   }

   public void setNoSelectIcon(int var1) {
      this.noSelectIcon = var1;
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
      LogUtil.showLog("this:" + this);
      LogUtil.showLog("this.isSelect:" + this.isSelect);
      LogUtil.showLog("selectIcon:" + this.selectIcon);
      LogUtil.showLog("noSelectIcon:" + this.noSelectIcon);
      if (this.isSelect) {
         this.setImageResource(this.selectIcon);
      } else {
         this.setImageResource(this.noSelectIcon);
      }

   }

   public void setSelectIcon(int var1) {
      this.selectIcon = var1;
   }
}
