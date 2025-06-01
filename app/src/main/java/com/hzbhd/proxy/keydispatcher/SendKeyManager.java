package com.hzbhd.proxy.keydispatcher;

import android.os.Bundle;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService;

public class SendKeyManager {
   public static final String BUNDLE_KEY_LONGPRESSKEY = "LongPressKey";
   private static SendKeyManager mSendKeyManager;

   public static SendKeyManager getInstance() {
      // $FF: Couldn't be decompiled
   }

   public void forceReverse(boolean var1) {
      this.setKeyEvent(4114, var1, 0, (Bundle)null);
   }

   public void playBeep(int var1) {
      this.setKeyEvent(4096, var1, 0, (Bundle)null);
   }

   public void resetMpu(HotKeyConstant.RESET_MODE var1, int var2) {
      this.setKeyEvent(4102, var1.ordinal(), var2, (Bundle)null);
   }

   public void setAppKeyEvent(int var1, int var2) {
      this.setKeyEvent(4099, var1, var2);
   }

   public void setAppMute(int var1, boolean var2) {
      this.setKeyEvent(4098, var1, var2, (Bundle)null);
   }

   public void setBacklightLevel(int var1) {
      this.setKeyEvent(4105, var1, 0, (Bundle)null);
   }

   public void setBacklightStatus(boolean var1) {
      this.setKeyEvent(4101, var1 ^ 1, 0, (Bundle)null);
   }

   public void setKeyEvent(int var1, int var2, int var3) {
      this.setKeyEvent(var1, var2, var3, (Bundle)null);
   }

   public void setKeyEvent(int var1, int var2, int var3, Bundle var4) {
      IKeyDispatcherService var5 = KeyDispatcherManager.getIKeyDispatcherService();
      if (var5 != null) {
         try {
            var5.setKeyEvent(var1, var2, var3, var4);
         } catch (Exception var6) {
            var6.printStackTrace();
         }

      }
   }

   public void setKeyEvent(int var1, HotKeyConstant.KeyState var2, boolean var3) {
      this.setKeyEvent(var1, var2.ordinal(), var3, (Bundle)null);
   }

   public void setSourceBluetooth(SourceConstantsDef.SOURCE_ID var1, HotKeyConstant.BT_ACTION var2) {
      this.setKeyEvent(4106, var1.getValue(), var2.ordinal(), (Bundle)null);
   }

   public void setSystemToast(int var1, String var2) {
      Bundle var3 = new Bundle();
      var3.putString("text", var2);
      this.setKeyEvent(4103, var1, 0, var3);
   }

   public void setVolume(int var1) {
      this.setKeyEvent(4104, var1, 0, (Bundle)null);
   }

   public void startSource(int var1) {
      this.setKeyEvent(4097, var1, 0, (Bundle)null);
   }
}
