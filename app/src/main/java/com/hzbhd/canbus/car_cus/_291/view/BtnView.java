package com.hzbhd.canbus.car_cus._291.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.hzbhd.canbus.R;

public class BtnView extends ImageView {
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
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.DeZhongComBtnViewStyle);
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
      if (!this.isEnabled()) {
         return super.onTouchEvent(var1);
      } else {
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
   }

   public boolean performClick() {
      return super.performClick();
   }

   public void setIcon(int var1, int var2) {
      this.selectIcon = var1;
      this.noSelectIcon = var2;
      this.setSelect(this.isSelect);
   }

   public void setSelect(boolean var1) {
      this.isSelect = var1;
      if (var1) {
         this.setImageResource(this.selectIcon);
      } else {
         this.setImageResource(this.noSelectIcon);
      }

   }
}
