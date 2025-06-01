package com.hzbhd.canbus.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.hzbhd.canbus.park.radar.PKeyRadarView;

public class PKeyUtil {
   private static PKeyUtil pKeyUtil;
   private boolean addTag = false;
   private WindowManager.LayoutParams layoutParams;
   private Context mContext;
   private WindowManager mWindowManager;
   private PKeyRadarView pKeyRadarView;
   private View view;

   private PKeyUtil(Context var1) {
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var2 = new WindowManager.LayoutParams();
      this.layoutParams = var2;
      var2.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.type = 2003;
      this.layoutParams.flags = 16777512;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.width = -1;
      this.layoutParams.height = -1;
      View var3 = LayoutInflater.from(var1).inflate(2131558864, (ViewGroup)null);
      this.view = var3;
      this.pKeyRadarView = (PKeyRadarView)var3.findViewById(2131362953);
   }

   public static PKeyUtil getInstance(Context var0) {
      if (pKeyUtil == null) {
         pKeyUtil = new PKeyUtil(var0);
      }

      return pKeyUtil;
   }

   public void hide() {
      if (this.addTag) {
         this.mWindowManager.removeView(this.view);
         this.addTag = false;
      }

   }

   public void refreshFrontRadar(int var1, int var2, int var3, int var4, int var5) {
      PKeyRadarView var6 = this.pKeyRadarView;
      if (var6 != null) {
         var6.refreshFrontRadar(var1, var2, var3, var4, var5);
      }

   }

   public void refreshRearRadar(int var1, int var2, int var3, int var4, int var5) {
      PKeyRadarView var6 = this.pKeyRadarView;
      if (var6 != null) {
         var6.refreshRearRadar(var1, var2, var3, var4, var5);
      }

   }

   public void show() {
      if (!this.addTag) {
         this.mWindowManager.addView(this.view, this.layoutParams);
         this.addTag = true;
      }

   }
}
