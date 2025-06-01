package com.hzbhd.systemstatus.proxy;

import com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback;
import com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager;
import java.util.HashMap;
import java.util.List;

public class ServiceStateManager {
   private static ServiceStateManager mServiceStateManager;
   private HashMap mServiceConnectCallbacks = new HashMap();
   private HashMap mServiceConnectListenerList = new HashMap();

   public static ServiceStateManager getInstance() {
      // $FF: Couldn't be decompiled
   }

   public String getServiceData(int var1, int var2) {
      ISystemStatusManager var3 = SystemStatusManager.getISystemStatusManager();
      if (var3 == null) {
         return null;
      } else {
         try {
            String var5 = var3.getServiceData(var1, var2);
            return var5;
         } catch (Exception var4) {
            var4.printStackTrace();
            return null;
         }
      }
   }

   public boolean registerConnectListener(int param1, IServiceConnectListener param2) {
      // $FF: Couldn't be decompiled
   }

   public void setServiceData(int var1, int var2, String var3) {
      ISystemStatusManager var4 = SystemStatusManager.getISystemStatusManager();
      if (var4 != null) {
         try {
            var4.setServiceData(var1, var2, var3);
         } catch (Exception var5) {
            var5.printStackTrace();
         }

      }
   }

   public boolean unregisterConnnectListener(int var1, IServiceConnectListener var2) {
      ISystemStatusManager var3 = SystemStatusManager.getISystemStatusManager();
      if (var3 == null) {
         return false;
      } else if (this.mServiceConnectListenerList.containsKey(var1) && ((List)this.mServiceConnectListenerList.get(var1)).contains(var2)) {
         ((List)this.mServiceConnectListenerList.get(var1)).remove(var2);
         if (((List)this.mServiceConnectListenerList.get(var1)).isEmpty()) {
            this.mServiceConnectListenerList.remove(var1);

            try {
               var3.unregisterServiceConnectCallback(var1, (IServiceConnectCallback)this.mServiceConnectCallbacks.get(var1));
               this.mServiceConnectCallbacks.remove(var1);
               return true;
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private class MyIServiceConnectCallback extends IServiceConnectCallback.Stub {
      private int mModuleId;
      final ServiceStateManager this$0;

      public MyIServiceConnectCallback(ServiceStateManager var1, int var2) {
         this.this$0 = var1;
         this.mModuleId = var2;
      }

      public void onConnected() {
         if (this.this$0.mServiceConnectListenerList.containsKey(this.mModuleId)) {
            List var2 = (List)this.this$0.mServiceConnectListenerList.get(this.mModuleId);

            for(int var1 = 0; var1 < var2.size(); ++var1) {
               ((IServiceConnectListener)var2.get(var1)).onConnected();
            }
         }

      }
   }
}
