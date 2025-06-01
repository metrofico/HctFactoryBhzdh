package com.hzbhd.canbus.car_cus._467.air.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.hzbhd.canbus.car_cus._467.air.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._467.air.view.AirView;
import com.hzbhd.canbus.util.ContextUtil;

public class AirWindow {
   public boolean addTag;
   private AirView air;
   private WindowManager.LayoutParams layoutParams;
   private WindowManager mWindowManager;
   private View view;

   private AirWindow(Context var1) {
      this.addTag = false;
      this.initWindow(var1);
   }

   // $FF: synthetic method
   AirWindow(Context var1, Object var2) {
      this(var1);
   }

   public static AirWindow getInstance() {
      return AirWindow.Holder.airWindow;
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
      View var3 = LayoutInflater.from(var1).inflate(2131558576, (ViewGroup)null);
      this.view = var3;
      AirView var4 = (AirView)var3.findViewById(2131361904);
      this.air = var4;
      var4.getAction(new ActionCallback(this) {
         final AirWindow this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (var1.equals("EXIT")) {
               this.this$0.hide();
            }

         }
      });
   }

   public boolean hide() {
      if (this.addTag) {
         this.mWindowManager.removeView(this.view);
         this.addTag = false;
         return true;
      } else {
         return false;
      }
   }

   public void setAutoExit(boolean var1) {
      this.air.setAutoExit(var1);
   }

   public boolean show() {
      if (!this.addTag) {
         this.mWindowManager.addView(this.view, this.layoutParams);
         this.addTag = true;
         this.air.initSetting();
         return true;
      } else {
         return false;
      }
   }

   private static class Holder {
      private static final AirWindow airWindow = new AirWindow(ContextUtil.getInstance().getContext());
   }
}
