package com.hzbhd.canbus.car._459.settings;

import com.hzbhd.canbus.car._459.tbox_wifi.TboxWifiState;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;

public class SharedToLauncher {
   private void notifyOtherModule(String var1, Object var2) {
      synchronized(this){}

      try {
         CanbusInfoChangeListener var4 = CanbusInfoChangeListener.getInstance();
         StringBuilder var3 = new StringBuilder();
         var4.reportString("can.bus.all.data.share", var3.append("{\"TAG\":\"").append(var1).append("\",\"VALUE\":\"").append(var2).append("\"}").toString());
      } finally {
         ;
      }

   }

   public void syncStateToLauncher() {
      this.notifyOtherModule("BL", OptionSettingsCmd459.getInstance().getBL());
      this.notifyOtherModule("DCM", OptionSettingsCmd459.getInstance().getDCM());
      this.notifyOtherModule("FL", OptionSettingsCmd459.getInstance().getFL());
      this.notifyOtherModule("SPS", OptionSettingsCmd459.getInstance().getSPS());
      this.notifyOtherModule("ER", OptionSettingsCmd459.getInstance().getER());
      this.notifyOtherModule("SA", OptionSettingsCmd459.getInstance().getSA());
      this.notifyOtherModule("AWS", OptionSettingsCmd459.getInstance().getAWS());
      this.notifyOtherModule("LD", OptionSettingsCmd459.getInstance().getLD());
      this.notifyOtherModule("CW", OptionSettingsCmd459.getInstance().getCW());
      this.notifyOtherModule("VDM", OptionSettingsCmd459.getInstance().getVDM());
      this.notifyOtherModule("FLWSL", OptionSettingsCmd459.getInstance().getFLWSL());
      this.notifyOtherModule("WIFI_CONNECT", TboxWifiState.connect);
      this.notifyOtherModule("WIFI_NAME", TboxWifiState.name);
      this.notifyOtherModule("WIFI_PASSWORD", TboxWifiState.password);
   }
}
