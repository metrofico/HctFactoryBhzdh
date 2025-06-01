package com.hzbhd.canbus.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SystemButton extends RelativeLayout {
   private static final int BUTTOM = 4;
   private static final int LEFT = 0;
   private static final int RIGHT = 1;
   private static final int TOP = 3;
   private static View view;
   private int dpi;
   private boolean isScroll;
   private float mTouchStartX;
   private float mTouchStartY;
   TextView my_P_Key;
   private int screenHeight;
   private int screenWidth;
   private WindowManager wm;
   private WindowManager.LayoutParams wmParams;
   private float x;
   private float y;

   public SystemButton(Activity var1, String var2, PanoramaListener var3) {
      super(var1);
      View var4 = LayoutInflater.from(var1).inflate(2131558623, this);
      view = var4;
      TextView var7 = (TextView)var4.findViewById(2131362884);
      this.my_P_Key = var7;
      var7.setText(var2);
      this.my_P_Key.setOnClickListener(new View.OnClickListener(this, var3) {
         final SystemButton this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent();
         }
      });
      this.wm = (WindowManager)var1.getSystemService("window");
      DisplayMetrics var6 = new DisplayMetrics();
      var1.getWindowManager().getDefaultDisplay().getMetrics(var6);
      this.dpi = this.dpi(var6.densityDpi);
      this.screenWidth = this.wm.getDefaultDisplay().getWidth();
      this.screenHeight = this.wm.getDefaultDisplay().getHeight();
      WindowManager.LayoutParams var5 = new WindowManager.LayoutParams();
      this.wmParams = var5;
      var5.type = 2003;
      this.wmParams.format = 1;
      this.wmParams.gravity = 51;
      this.wmParams.flags = 8;
      this.wmParams.width = this.dpi + 90;
      this.wmParams.height = this.dpi + 50;
      this.wmParams.y = this.screenHeight - this.dpi >> 1;
      this.wm.addView(this, this.wmParams);
      this.hide();
   }

   private void autoView() {
      int[] var1 = new int[2];
      this.getLocationOnScreen(var1);
      if (var1[0] < this.screenWidth / 2 - this.getWidth() / 2) {
         this.updateViewPosition(0);
      } else {
         this.updateViewPosition(1);
      }

   }

   private int dpi(int var1) {
      if (var1 <= 120) {
         return 36;
      } else if (var1 <= 160) {
         return 48;
      } else if (var1 <= 240) {
         return 72;
      } else {
         return var1 <= 320 ? 96 : 108;
      }
   }

   private void updateViewPosition() {
      this.wmParams.x = (int)(this.x - this.mTouchStartX);
      this.wmParams.y = (int)(this.y - this.mTouchStartY - (float)(this.screenHeight / 25));
      this.wm.updateViewLayout(this, this.wmParams);
   }

   private void updateViewPosition(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 == 4) {
                  this.wmParams.y = this.screenHeight - this.dpi;
               }
            } else {
               this.wmParams.y = 0;
            }
         } else {
            var1 = this.screenWidth;
            int var2 = this.dpi;
            this.wmParams.x = var1 - var2;
         }
      } else {
         this.wmParams.x = 0;
      }

      this.wm.updateViewLayout(this, this.wmParams);
   }

   public void destory() {
      this.hide();
      this.wm.removeViewImmediate(this);
   }

   public void hide() {
      this.setVisibility(8);
   }

   public boolean onTouchEvent(MotionEvent var1) {
      this.x = var1.getRawX();
      this.y = var1.getRawY();
      int var2 = var1.getAction();
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 == 2) {
               if (this.isScroll) {
                  this.updateViewPosition();
               } else {
                  if (!(Math.abs(this.mTouchStartX - var1.getX()) > (float)(this.dpi / 3)) && !(Math.abs(this.mTouchStartY - var1.getY()) > (float)(this.dpi / 3))) {
                     return super.onTouchEvent(var1);
                  }

                  this.updateViewPosition();
               }

               this.isScroll = true;
            }
         } else {
            if (this.isScroll) {
               this.autoView();
            }

            this.isScroll = false;
            this.mTouchStartY = 0.0F;
            this.mTouchStartX = 0.0F;
         }
      } else {
         this.mTouchStartX = var1.getX();
         this.mTouchStartY = var1.getY();
      }

      return super.onTouchEvent(var1);
   }

   public void show() {
      if (!this.isShown()) {
         this.setVisibility(0);
      }
   }

   public interface PanoramaListener {
      void clickEvent();
   }
}
