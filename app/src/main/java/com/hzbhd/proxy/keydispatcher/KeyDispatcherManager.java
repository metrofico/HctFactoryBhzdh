package com.hzbhd.proxy.keydispatcher;

import android.os.ServiceManager;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;

public class KeyDispatcherManager {
   public static final String SERVICE_NAME_KEY_DISPATCHER = "hzbhd_KeyDispatcher";
   private static KeyDispatcherManager mKeyDispatcherManager;

   protected static IKeyDispatcherService getIKeyDispatcherService() {
      if (mKeyDispatcherManager == null) {
         mKeyDispatcherManager = new KeyDispatcherManager();
         ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.KEY_DISPATCHER.getValue(), new IServiceConnectListener() {
            public void onConnected() {
               ReceiveKeyManager.getInstance().resetIKeyDispatcherCallback();
            }
         });
      }

      return IKeyDispatcherService.Stub.asInterface(ServiceManager.getService("hzbhd_KeyDispatcher"));
   }
}
