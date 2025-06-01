package com.hzbhd.canbus.car._18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class P360ButtonWindow extends RelativeLayout {
   private static final int BUTTOM = 4;
   private static final int LEFT = 0;
   private static final int RIGHT = 1;
   private static final int TOP = 3;
   private static View view;
   private int dpi;
   private boolean isScroll;
   private PanoramaListener listener;
   private float mTouchStartX;
   private float mTouchStartY;
   private int screenHeight;
   private int screenWidth;
   private boolean showTag = false;
   private WindowManager windowManager;
   private WindowManager.LayoutParams wmParams;
   private float x;
   private float y;

   public P360ButtonWindow(Context var1, PanoramaListener var2) {
      super(var1);
      this.listener = var2;
      view = LayoutInflater.from(var1).inflate(2131558402, this);
      WindowManager var3 = (WindowManager)var1.getSystemService("window");
      this.windowManager = var3;
      this.dpi = 108;
      this.screenWidth = var3.getDefaultDisplay().getWidth();
      this.screenHeight = this.windowManager.getDefaultDisplay().getHeight();
      WindowManager.LayoutParams var4 = new WindowManager.LayoutParams();
      this.wmParams = var4;
      var4.type = 2010;
      this.wmParams.format = 1;
      this.wmParams.gravity = 51;
      this.wmParams.flags = 8;
      this.wmParams.width = this.dpi + 50;
      this.wmParams.height = this.dpi + 50;
      this.wmParams.y = 860 - (this.dpi + 50);
      this.wmParams.x = 1920 - (this.dpi + 50);
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

   private void updateViewPosition() {
      this.wmParams.x = (int)(this.x - this.mTouchStartX);
      this.wmParams.y = (int)(this.y - this.mTouchStartY - (float)(this.screenHeight / 25));
      this.windowManager.updateViewLayout(this, this.wmParams);
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

      this.windowManager.updateViewLayout(this, this.wmParams);
   }

   public boolean getShowTag() {
      return this.showTag;
   }

   public void hide() {
      this.showTag = false;
      this.windowManager.removeViewImmediate(this);
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
            } else {
               this.listener.clickEvent();
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
      this.showTag = true;
      this.windowManager.addView(this, this.wmParams);
   }

   public interface PanoramaListener {
      void clickEvent();
   }
}
