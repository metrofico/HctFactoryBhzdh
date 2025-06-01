package com.hzbhd.canbus.car_cus._467.smartPanel.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.hzbhd.canbus.comm.ScreenConfig;
import com.hzbhd.canbus.util.ContextUtil;

public class PanelWindow {
   public boolean addTag;
   private WindowManager.LayoutParams layoutParams;
   private WindowManager mWindowManager;
   private View view;

   private PanelWindow(Context var1) {
      this.addTag = false;
      this.initWindow(var1);
   }

   // $FF: synthetic method
   PanelWindow(Context var1, Object var2) {
      this(var1);
   }

   public static PanelWindow getInstance() {
      return PanelWindow.Holder.airWindow;
   }

   private void initWindow(Context var1) {
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var2 = new WindowManager.LayoutParams(2010);
      this.layoutParams = var2;
      var2.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.flags = 1024;
      this.layoutParams.systemUiVisibility = 5380;
      if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
         this.view = LayoutInflater.from(var1).inflate(2131558574, (ViewGroup)null);
      } else {
         this.view = LayoutInflater.from(var1).inflate(2131558575, (ViewGroup)null);
      }

   }

   public void hide() {
      if (this.addTag) {
         this.mWindowManager.removeView(this.view);
         this.addTag = false;
      }

   }

   public void show() {
      if (!this.addTag) {
         this.mWindowManager.addView(this.view, this.layoutParams);
         this.addTag = true;
      }

   }

   private static class Holder {
      private static final PanelWindow airWindow = new PanelWindow(ContextUtil.getInstance().getContext());
   }
}
