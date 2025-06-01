package com.microntek.controlsettings;

import android.app.Application;
import android.microntek.CarManager;
import android.os.Build.VERSION;
import android.util.Log;

public class BaseApplication extends Application {
   private static BaseApplication mApplication;
   private int canbustype = 0;
   private int canbustypeCustomer = 0;
   private boolean isAirSwap;
   private boolean isFontDoorSwap;
   private boolean isOrgPanel;
   private boolean isRearDoorSwap;
   private CarManager mCarManager;
   private int mCarType = 0;
   private int mCarType2 = 0;
   private String mCustomer = "";
   private String mMcuVersion = "";

   public static BaseApplication getInstance() {
      return mApplication;
   }

   private void init() {
      String var1;
      if (VERSION.SDK_INT > 19) {
         var1 = this.getParameters("cfg_cansub_1=");

         try {
            this.mCarType = Integer.parseInt(var1);
         } catch (Exception var7) {
         }

         try {
            this.mCarType2 = Integer.parseInt(this.getParameters("cfg_cansub_10="));
         } catch (Exception var6) {
         }

         try {
            this.canbustypeCustomer = Integer.parseInt(this.getParameters("cfg_cansub_11="));
         } catch (Exception var5) {
         }

         this.isOrgPanel = "yes".equals(this.getParameters("sta_mcu_o="));
      } else {
         var1 = this.getParameters("cfg_canbus_cfg=");

         try {
            this.mCarType = Integer.parseInt(var1);
         } catch (Exception var4) {
         }

         this.mCarType >>= 4;
      }

      var1 = this.getParameters("cfg_canbus=");

      try {
         this.canbustype = Integer.parseInt(var1);
      } catch (Exception var3) {
      }

      if (this.getParameters("cfg_cansub_6=").equals("1")) {
         this.isFontDoorSwap = true;
      }

      if (this.getParameters("cfg_cansub_8=").equals("1")) {
         this.isRearDoorSwap = true;
      }

      try {
         this.isAirSwap = this.getParameters("cfg_cansub_7=").equals("1");
      } catch (Exception var2) {
      }

   }

   public int getCanbustype() {
      return this.canbustype;
   }

   public int getCanbustypeCustomer() {
      return this.canbustypeCustomer;
   }

   public byte[] getCarByteArrayState(String param1) {
      // $FF: Couldn't be decompiled
   }

   public int getCarType() {
      return this.mCarType;
   }

   public int getCarType2() {
      return this.mCarType2;
   }

   public String getCustomer() {
      if (this.mCustomer.isEmpty()) {
         this.mMcuVersion = getInstance().getParameters("sta_mcu_version=");
         this.mCustomer = RkSystemProp.get("ro.product.customer", "HCT");
      }

      StringBuilder var1 = new StringBuilder();
      var1.append(this.mMcuVersion);
      var1.append("_");
      var1.append(this.mCustomer);
      return var1.toString();
   }

   public String getParameters(String var1) {
      return this.mCarManager.getParameters(var1);
   }

   public boolean isAirSwap() {
      return this.isAirSwap;
   }

   public boolean isOrgPanel() {
      return this.isOrgPanel;
   }

   public void onCreate() {
      super.onCreate();
      mApplication = this;
      this.mCarManager = new CarManager();
      this.init();
      StringBuilder var1 = new StringBuilder();
      var1.append("ControlSettings>>BaseApplication>>>>>canbustype:");
      var1.append(this.canbustype);
      Log.i("ControlSettings", var1.toString());
   }

   public void setParameters(String var1) {
      this.mCarManager.setParameters(var1);
   }
}
