package com.hzbhd.setting.proxy;

import android.os.RemoteException;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.setting.proxy.aidl.IModuleCallBack;
import com.hzbhd.setting.proxy.aidl.ISettingServiceManager;
import java.util.HashMap;
import java.util.Iterator;

public class ModuleManager {
   private static final String TAG = "ModuleManager";
   private static ModuleManager mModuleManager;
   private HashMap mIModuleCallBacks = new HashMap();
   private HashMap mModuleListeners = new HashMap();

   public static ModuleManager getInstance() {
      // $FF: Couldn't be decompiled
   }

   public void notifyBytes(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4, byte[] var5) throws RemoteException {
      ISettingServiceManager var6 = SettingServiceManager.getISettingServiceManager();
      if (var6 != null) {
         var6.notifyBytes(SettingServiceManager.getKey(var1, var2), var3, var4, var5);
      }
   }

   public void notifyInt(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4, int var5) throws RemoteException {
      ISettingServiceManager var6 = SettingServiceManager.getISettingServiceManager();
      if (var6 != null) {
         var6.notifyInt(SettingServiceManager.getKey(var1, var2), var3, var4, var5);
      }
   }

   public void notifyString(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, int var3, int var4, String var5) throws RemoteException {
      ISettingServiceManager var6 = SettingServiceManager.getISettingServiceManager();
      if (var6 != null) {
         var6.notifyString(SettingServiceManager.getKey(var1, var2), var3, var4, var5);
      }
   }

   public void registerModuleListener(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, IModuleListener var3) {
      int var4 = SettingServiceManager.getKey(var1, var2);
      this.mModuleListeners.put(var4, var3);
      if (!this.mIModuleCallBacks.containsKey(var4)) {
         this.mIModuleCallBacks.put(var4, new MyIModuleCallBack(this, var4));
         ISettingServiceManager var6 = SettingServiceManager.getISettingServiceManager();
         if (var6 != null) {
            try {
               var6.registerModuleListener(var4, (IModuleCallBack)this.mIModuleCallBacks.get(var4));
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

      }
   }

   protected void resetModuleCallbacks() {
      if (!this.mIModuleCallBacks.isEmpty()) {
         Iterator var1 = this.mIModuleCallBacks.keySet().iterator();

         while(var1.hasNext()) {
            Integer var2 = (Integer)var1.next();
            ISettingServiceManager var3 = SettingServiceManager.getISettingServiceManager();
            if (var3 != null) {
               try {
                  var3.registerModuleListener(var2, (IModuleCallBack)this.mIModuleCallBacks.get(var2));
               } catch (Exception var4) {
                  var4.printStackTrace();
               }
            }
         }

      }
   }

   public void unregisterModuleListener(SourceConstantsDef.MODULE_ID var1, BodaSysContant.TypeDef var2, IModuleListener var3) {
      int var4 = SettingServiceManager.getKey(var1, var2);
      if (this.mModuleListeners.containsKey(var4)) {
         this.mModuleListeners.remove(var4);
         ISettingServiceManager var6 = SettingServiceManager.getISettingServiceManager();
         if (var6 != null) {
            try {
               var6.unregisterModuleListener(var4, (IModuleCallBack)this.mIModuleCallBacks.get(var4));
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

         this.mIModuleCallBacks.remove(var4);
      }
   }

   private class MyIModuleCallBack extends IModuleCallBack.Stub {
      private int mKey;
      final ModuleManager this$0;

      private MyIModuleCallBack(ModuleManager var1, int var2) {
         this.this$0 = var1;
         this.mKey = var2;
      }

      // $FF: synthetic method
      MyIModuleCallBack(ModuleManager var1, int var2, Object var3) {
         this(var1, var2);
      }

      public byte[] getModuleBytes(int var1, int var2) throws RemoteException {
         return this.this$0.mModuleListeners.get(this.mKey) == null ? new byte[0] : (byte[])((IModuleListener)this.this$0.mModuleListeners.get(this.mKey)).getData(var1, var2);
      }

      public int getModuleInt(int var1, int var2) throws RemoteException {
         return this.this$0.mModuleListeners.get(this.mKey) == null ? -1 : (Integer)((IModuleListener)this.this$0.mModuleListeners.get(this.mKey)).getData(var1, var2);
      }

      public String getModuleString(int var1, int var2) throws RemoteException {
         return this.this$0.mModuleListeners.get(this.mKey) == null ? null : (String)((IModuleListener)this.this$0.mModuleListeners.get(this.mKey)).getData(var1, var2);
      }

      public void onModuleBytes(int var1, int var2, byte[] var3) throws RemoteException {
         if (this.this$0.mModuleListeners.get(this.mKey) != null) {
            ((IModuleListener)this.this$0.mModuleListeners.get(this.mKey)).onReceived(var1, var2, var3);
         }
      }

      public void onModuleInt(int var1, int var2, int var3) throws RemoteException {
         if (this.this$0.mModuleListeners.get(this.mKey) != null) {
            ((IModuleListener)this.this$0.mModuleListeners.get(this.mKey)).onReceived(var1, var2, var3);
         }
      }

      public void onModuleString(int var1, int var2, String var3) throws RemoteException {
         if (this.this$0.mModuleListeners.get(this.mKey) != null) {
            ((IModuleListener)this.this$0.mModuleListeners.get(this.mKey)).onReceived(var1, var2, var3);
         }
      }
   }
}
