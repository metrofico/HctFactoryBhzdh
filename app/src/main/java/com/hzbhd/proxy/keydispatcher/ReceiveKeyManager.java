package com.hzbhd.proxy.keydispatcher;

import android.os.Bundle;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService;
import com.hzbhd.proxy.keydispatcher.interfaces.IHotKeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ReceiveKeyManager {
   private static ReceiveKeyManager mReceiveKeyManager;
   private HashMap mHotKeyListeners = new HashMap();
   private HashMap mIKeyDispatcherCallbacks = new HashMap();
   private HashSet mThirdApps = new HashSet();

   public static ReceiveKeyManager getInstance() {
      // $FF: Couldn't be decompiled
   }

   private boolean registerKeyDispatcherCallback(int var1) {
      IKeyDispatcherService var3 = KeyDispatcherManager.getIKeyDispatcherService();
      if (var3 == null) {
         return false;
      } else if (this.mIKeyDispatcherCallbacks.containsKey(var1)) {
         return true;
      } else {
         this.mIKeyDispatcherCallbacks.put(var1, new MyIKeyDispatcherCallback(this, var1));

         try {
            boolean var2 = var3.registerKeyDispatcherCallback(var1, (IKeyDispatcherCallback)this.mIKeyDispatcherCallbacks.get(var1));
            return var2;
         } catch (Exception var4) {
            var4.printStackTrace();
            return true;
         }
      }
   }

   private boolean unregisterKeyDispatcherCallback(int var1) {
      IKeyDispatcherService var3 = KeyDispatcherManager.getIKeyDispatcherService();
      if (var3 == null) {
         return false;
      } else {
         if (this.mIKeyDispatcherCallbacks.containsKey(var1)) {
            try {
               boolean var2 = var3.unregisterKeyDispatcherCallback(var1, (IKeyDispatcherCallback)this.mIKeyDispatcherCallbacks.get(var1));
               return var2;
            } catch (Exception var4) {
               var4.printStackTrace();
               this.mIKeyDispatcherCallbacks.remove(var1);
            }
         }

         return true;
      }
   }

   public boolean registerHotKeyListener(int var1, IHotKeyListener var2) {
      this.mHotKeyListeners.put(var1, var2);
      return this.registerKeyDispatcherCallback(var1);
   }

   public boolean registerThirdApp(int var1) {
      IKeyDispatcherService var3 = KeyDispatcherManager.getIKeyDispatcherService();
      if (var3 == null) {
         return false;
      } else {
         try {
            this.mThirdApps.add(var1);
            boolean var2 = var3.registerThirdApp(var1);
            return var2;
         } catch (Exception var4) {
            var4.printStackTrace();
            return false;
         }
      }
   }

   public void resetIKeyDispatcherCallback() {
      if (!this.mIKeyDispatcherCallbacks.isEmpty()) {
         IKeyDispatcherService var2 = KeyDispatcherManager.getIKeyDispatcherService();
         if (var2 != null) {
            Iterator var3 = this.mIKeyDispatcherCallbacks.keySet().iterator();

            int var1;
            while(var3.hasNext()) {
               var1 = (Integer)var3.next();

               try {
                  var2.registerKeyDispatcherCallback(var1, (IKeyDispatcherCallback)this.mIKeyDispatcherCallbacks.get(var1));
               } catch (Exception var6) {
                  var6.printStackTrace();
               }
            }

            var3 = this.mThirdApps.iterator();

            while(var3.hasNext()) {
               var1 = (Integer)var3.next();

               try {
                  var2.registerThirdApp(var1);
               } catch (Exception var5) {
                  var5.printStackTrace();
               }
            }

         }
      }
   }

   public boolean unregisterHotKeyListener(int var1) {
      if (this.mHotKeyListeners.containsKey(var1)) {
         this.mHotKeyListeners.remove(var1);
         return this.unregisterKeyDispatcherCallback(var1);
      } else {
         return true;
      }
   }

   public boolean unregisterThirdApp(int var1) {
      IKeyDispatcherService var3 = KeyDispatcherManager.getIKeyDispatcherService();
      if (var3 == null) {
         return false;
      } else {
         try {
            this.mThirdApps.remove(var1);
            boolean var2 = var3.unregisterThirdApp(var1);
            return var2;
         } catch (Exception var4) {
            var4.printStackTrace();
            return false;
         }
      }
   }

   private class MyIKeyDispatcherCallback extends IKeyDispatcherCallback.Stub {
      private int mAppId;
      final ReceiveKeyManager this$0;

      private MyIKeyDispatcherCallback(ReceiveKeyManager var1, int var2) {
         this.this$0 = var1;
         this.mAppId = var2;
      }

      // $FF: synthetic method
      MyIKeyDispatcherCallback(ReceiveKeyManager var1, int var2, Object var3) {
         this(var1, var2);
      }

      public boolean onKeyEvent(int var1, int var2, int var3, Bundle var4) {
         return ((IHotKeyListener)this.this$0.mHotKeyListeners.get(this.mAppId)).onKeyEvent(var1, var2, var3, var4);
      }
   }
}
