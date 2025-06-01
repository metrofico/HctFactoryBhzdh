package com.hzbhd.canbus.car_cus._283.utils;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.view.CarImageView;

public class Utils {
   public static int[] calculatePopWindowPos(View var0, View var1) {
      int[] var8 = new int[2];
      int[] var9 = new int[2];
      var0.getLocationOnScreen(var9);
      int var3 = var0.getHeight();
      int var2 = getScreenHeight(var0.getContext());
      int var4 = getScreenWidth(var0.getContext());
      var1.measure(0, 0);
      int var6 = var1.getMeasuredHeight();
      int var5 = var1.getMeasuredWidth();
      int var7 = var9[1];
      boolean var10;
      if (var2 - var7 - var3 < var6) {
         var10 = true;
      } else {
         var10 = false;
      }

      if (var10) {
         var8[0] = var4 - var5;
         var8[1] = var7 - var6;
      } else {
         var8[0] = var4 - var5;
         var8[1] = var7 + var3;
      }

      return var8;
   }

   public static int getScreenHeight(Context var0) {
      return var0.getResources().getDisplayMetrics().heightPixels;
   }

   public static int getScreenWidth(Context var0) {
      return var0.getResources().getDisplayMetrics().widthPixels;
   }

   private static int getStartMargin(String var0) {
      int var1;
      try {
         var1 = Integer.parseInt(var0);
      } catch (Exception var2) {
         var2.printStackTrace();
         return 30;
      }

      return var1 > 650 ? 30 : 650 - var1 + 30;
   }

   public static void setCarResidu(CarImageView var0, String var1) {
      RelativeLayout.LayoutParams var2 = (RelativeLayout.LayoutParams)var0.getLayoutParams();
      var2.leftMargin = getStartMargin(var1);
      var0.setLayoutParams(var2);
   }
}
