package com.hzbhd.proxy.share;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.hzbhd.commontools.AppIdDef;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.share.SharedModule;
import com.hzbhd.proxy.share.aidl.IShareBundleCallback;
import com.hzbhd.proxy.share.aidl.IShareDataService;
import com.hzbhd.proxy.share.aidl.IShareIntCallback;
import com.hzbhd.proxy.share.aidl.IShareStringCallback;
import com.hzbhd.proxy.share.interfaces.IShareBundleListener;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ShareDataManager {
   private static final String TAG = "ShareDataManager";
   private static ShareDataManager mThis;
   private HashMap mIShareBundleCallbacks = new HashMap();
   private HashMap mIShareIntCallbacks = new HashMap();
   private HashMap mIShareStringCallbacks = new HashMap();
   private HashMap mReConnCallbackRegister = new HashMap();
   private HashMap mShareBundleListeners = new HashMap();
   private HashMap mShareIntListeners = new HashMap();
   private HashMap mShareStringListeners = new HashMap();

   private void checkResetCallback(SourceConstantsDef.MODULE_ID var1) {
      HashMap var3 = this.mReConnCallbackRegister;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            if (!this.mReConnCallbackRegister.containsKey(var1)) {
               this.mReConnCallbackRegister.put(var1, true);
               ServiceStateManager var4 = ServiceStateManager.getInstance();
               int var2 = var1.getValue();
               MyIServiceConnectListener var5 = new MyIServiceConnectListener(this, var1);
               var4.registerConnectListener(var2, var5);
            }
         } catch (Throwable var17) {
            var10000 = var17;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            return;
         } catch (Throwable var16) {
            var10000 = var16;
            var10001 = false;
            break label119;
         }
      }

      while(true) {
         Throwable var18 = var10000;

         try {
            throw var18;
         } catch (Throwable var15) {
            var10000 = var15;
            var10001 = false;
            continue;
         }
      }
   }

   private IShareBundleCallback getIShareBundleCallback(String var1) {
      if (!this.mIShareBundleCallbacks.containsKey(var1)) {
         this.mIShareBundleCallbacks.put(var1, new MyIShareBundleCallback(this, var1));
      }

      return (IShareBundleCallback)this.mIShareBundleCallbacks.get(var1);
   }

   private IShareDataService getIShareDataService(SourceConstantsDef.MODULE_ID var1) {
      try {
         StringBuilder var2 = new StringBuilder();
         IShareDataService var4 = IShareDataService.Stub.asInterface(ServiceManager.getService(var2.append("share_").append(var1).toString()));
         return var4;
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   private IShareDataService getIShareDataService(String var1) {
      return this.getIShareDataService(this.getModuleId(var1));
   }

   private IShareIntCallback getIShareIntCallback(String var1) {
      if (!this.mIShareIntCallbacks.containsKey(var1)) {
         this.mIShareIntCallbacks.put(var1, new MyIShareIntCallback(this, var1));
      }

      return (IShareIntCallback)this.mIShareIntCallbacks.get(var1);
   }

   private IShareStringCallback getIShareStringCallback(String var1) {
      if (!this.mIShareStringCallbacks.containsKey(var1)) {
         this.mIShareStringCallbacks.put(var1, new MyIShareStringCallback(this, var1));
      }

      return (IShareStringCallback)this.mIShareStringCallbacks.get(var1);
   }

   public static ShareDataManager getInstance() {
      // $FF: Couldn't be decompiled
   }

   private SourceConstantsDef.MODULE_ID getModuleId(String var1) {
      return SourceConstantsDef.MODULE_ID.getType((Integer)SharedModule.INSTANCE.getShared().getOrDefault(var1, 0));
   }

   public Bundle getBundle(String var1) {
      IShareDataService var2 = this.getIShareDataService(var1);
      if (var2 == null) {
         return null;
      } else {
         try {
            Bundle var4 = var2.getBundle(var1);
            return var4;
         } catch (Exception var3) {
            var3.printStackTrace();
            return null;
         }
      }
   }

   public int getInt(String var1) {
      IShareDataService var3 = this.getIShareDataService(var1);
      if (var3 == null) {
         return -1;
      } else {
         try {
            int var2 = var3.getInt(var1);
            return var2;
         } catch (Exception var4) {
            var4.printStackTrace();
            return -1;
         }
      }
   }

   public String getString(String var1) {
      IShareDataService var2 = this.getIShareDataService(var1);
      if (var2 == null) {
         return null;
      } else {
         try {
            var1 = var2.getString(var1);
            return var1;
         } catch (Exception var3) {
            var3.printStackTrace();
            return null;
         }
      }
   }

   public Bundle registerShareBundleListener(String var1, IShareBundleListener var2) {
      HashMap var3 = this.mShareBundleListeners;
      synchronized(var3){}

      label162: {
         Throwable var10000;
         boolean var10001;
         label157: {
            try {
               if (this.mShareBundleListeners.get(var1) == null) {
                  HashMap var5 = this.mShareBundleListeners;
                  ArrayList var4 = new ArrayList();
                  var5.put(var1, var4);
               }
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label157;
            }

            label154:
            try {
               ((ArrayList)this.mShareBundleListeners.get(var1)).add(var2);
               break label162;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label154;
            }
         }

         while(true) {
            Throwable var22 = var10000;

            try {
               throw var22;
            } catch (Throwable var19) {
               var10000 = var19;
               var10001 = false;
               continue;
            }
         }
      }

      IShareBundleCallback var26 = this.getIShareBundleCallback(var1);
      SourceConstantsDef.MODULE_ID var24 = this.getModuleId(var1);
      IShareDataService var25 = this.getIShareDataService(var24);
      this.checkResetCallback(var24);
      if (var25 == null) {
         return null;
      } else {
         try {
            Bundle var23 = var25.registerShareBundleCallback(var1, var26);
            return var23;
         } catch (Exception var18) {
            var18.printStackTrace();
            return null;
         }
      }
   }

   public int registerShareIntListener(String var1, IShareIntListener var2) {
      HashMap var4 = this.mShareIntListeners;
      synchronized(var4){}

      label162: {
         Throwable var10000;
         boolean var10001;
         label157: {
            try {
               if (this.mShareIntListeners.get(var1) == null) {
                  HashMap var6 = this.mShareIntListeners;
                  ArrayList var5 = new ArrayList();
                  var6.put(var1, var5);
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label157;
            }

            label154:
            try {
               ((ArrayList)this.mShareIntListeners.get(var1)).add(var2);
               break label162;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label154;
            }
         }

         while(true) {
            Throwable var23 = var10000;

            try {
               throw var23;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               continue;
            }
         }
      }

      IShareIntCallback var26 = this.getIShareIntCallback(var1);
      SourceConstantsDef.MODULE_ID var24 = this.getModuleId(var1);
      IShareDataService var25 = this.getIShareDataService(var24);
      this.checkResetCallback(var24);
      if (var25 == null) {
         return -1;
      } else {
         try {
            int var3 = var25.registerShareIntCallback(var1, var26);
            return var3;
         } catch (Exception var19) {
            var19.printStackTrace();
            return -1;
         }
      }
   }

   public String registerShareStringListener(String var1, IShareStringListener var2) {
      HashMap var3 = this.mShareStringListeners;
      synchronized(var3){}

      label162: {
         Throwable var10000;
         boolean var10001;
         label157: {
            try {
               if (this.mShareStringListeners.get(var1) == null) {
                  HashMap var4 = this.mShareStringListeners;
                  ArrayList var5 = new ArrayList();
                  var4.put(var1, var5);
               }
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label157;
            }

            label154:
            try {
               ((ArrayList)this.mShareStringListeners.get(var1)).add(var2);
               break label162;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label154;
            }
         }

         while(true) {
            Throwable var22 = var10000;

            try {
               throw var22;
            } catch (Throwable var19) {
               var10000 = var19;
               var10001 = false;
               continue;
            }
         }
      }

      IShareStringCallback var23 = this.getIShareStringCallback(var1);
      SourceConstantsDef.MODULE_ID var24 = this.getModuleId(var1);
      IShareDataService var25 = this.getIShareDataService(var24);
      this.checkResetCallback(var24);
      if (var25 == null) {
         return null;
      } else {
         try {
            var1 = var25.registerShareStringCallback(var1, var23);
            return var1;
         } catch (Exception var18) {
            var18.printStackTrace();
            return null;
         }
      }
   }

   public void reportBundle(String var1, Bundle var2) {
      IShareDataService var3 = this.getIShareDataService(var1);
      if (var3 != null) {
         try {
            var3.reportBundle(var1, var2);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

   }

   public void reportInt(String var1, int var2) {
      IShareDataService var3 = this.getIShareDataService(var1);
      if (var3 != null) {
         try {
            var3.reportInt(var1, var2);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

   }

   public void reportOtherBundle(String var1, Bundle var2, AppIdDef.APP_ID var3) {
      IShareDataService var4 = this.getIShareDataService(var1);
      if (var4 != null) {
         try {
            var4.reportOtherBundle(var1, var2, var3.ordinal());
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

   }

   public void reportOtherInt(String var1, int var2, AppIdDef.APP_ID var3) {
      IShareDataService var4 = this.getIShareDataService(var1);
      if (var4 != null) {
         try {
            var4.reportOtherInt(var1, var2, var3.ordinal());
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

   }

   public void reportOtherString(String var1, String var2, AppIdDef.APP_ID var3) {
      IShareDataService var4 = this.getIShareDataService(var1);
      if (var4 != null) {
         try {
            var4.reportOtherString(var1, var2, var3.ordinal());
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

   }

   public void reportString(String var1, String var2) {
      IShareDataService var3 = this.getIShareDataService(var1);
      if (var3 != null) {
         try {
            var3.reportString(var1, var2);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

   }

   public void resetCallbacks(SourceConstantsDef.MODULE_ID var1) {
      IShareDataService var3 = this.getIShareDataService(var1);
      if (LogUtil.log5()) {
         LogUtil.d("resetCallbacks: " + var1 + ":" + var3);
      }

      if (var3 != null) {
         Iterator var4 = this.mIShareIntCallbacks.keySet().iterator();

         String var5;
         while(var4.hasNext()) {
            var5 = (String)var4.next();

            try {
               if (this.getModuleId(var5) == var1) {
                  int var2 = var3.registerShareIntCallback(var5, this.getIShareIntCallback(var5));
                  this.getIShareIntCallback(var5).onInt(var2, -1);
               }
            } catch (Exception var9) {
               var9.printStackTrace();
            }
         }

         var4 = this.mIShareStringCallbacks.keySet().iterator();

         while(var4.hasNext()) {
            var5 = (String)var4.next();

            try {
               if (this.getModuleId(var5) == var1) {
                  String var6 = var3.registerShareStringCallback(var5, this.getIShareStringCallback(var5));
                  this.getIShareStringCallback(var5).onString(var6, -1);
               }
            } catch (Exception var8) {
               var8.printStackTrace();
            }
         }

         var4 = this.mIShareBundleCallbacks.keySet().iterator();

         while(var4.hasNext()) {
            var5 = (String)var4.next();

            try {
               if (this.getModuleId(var5) == var1) {
                  Bundle var10 = var3.registerShareBundleCallback(var5, this.getIShareBundleCallback(var5));
                  this.getIShareBundleCallback(var5).onBundle(var10, -1);
               }
            } catch (Exception var7) {
               var7.printStackTrace();
            }
         }

      }
   }

   public void unregisterShareBundleListener(String var1, IShareBundleListener var2) {
      if (this.mShareBundleListeners.get(var1) != null) {
         if (((ArrayList)this.mShareBundleListeners.get(var1)).contains(var2)) {
            ((ArrayList)this.mShareBundleListeners.get(var1)).remove(var2);
            if (((ArrayList)this.mShareBundleListeners.get(var1)).isEmpty()) {
               this.mShareBundleListeners.remove(var1);
               IShareDataService var4 = this.getIShareDataService(var1);
               if (var4 != null) {
                  try {
                     var4.unregisterShareBundleCallback(var1, (IShareBundleCallback)this.mIShareBundleCallbacks.get(var1));
                  } catch (Exception var3) {
                     var3.printStackTrace();
                  }
               }

               this.mIShareBundleCallbacks.remove(var1);
            }
         }

      }
   }

   public void unregisterShareIntListener(String var1, IShareIntListener var2) {
      if (this.mShareIntListeners.get(var1) != null) {
         if (((ArrayList)this.mShareIntListeners.get(var1)).contains(var2)) {
            ((ArrayList)this.mShareIntListeners.get(var1)).remove(var2);
            if (((ArrayList)this.mShareIntListeners.get(var1)).isEmpty()) {
               this.mShareIntListeners.remove(var1);
               IShareDataService var4 = this.getIShareDataService(var1);
               if (var4 != null) {
                  try {
                     var4.unregisterShareIntCallback(var1, (IShareIntCallback)this.mIShareIntCallbacks.get(var1));
                  } catch (Exception var3) {
                     var3.printStackTrace();
                  }
               }

               this.mIShareIntCallbacks.remove(var1);
            }
         }

      }
   }

   public void unregisterShareStringListener(String var1, IShareStringListener var2) {
      if (this.mShareStringListeners.get(var1) != null) {
         if (((ArrayList)this.mShareStringListeners.get(var1)).contains(var2)) {
            ((ArrayList)this.mShareStringListeners.get(var1)).remove(var2);
            if (((ArrayList)this.mShareStringListeners.get(var1)).isEmpty()) {
               this.mShareStringListeners.remove(var1);
               IShareDataService var4 = this.getIShareDataService(var1);
               if (var4 != null) {
                  try {
                     var4.unregisterShareStringCallback(var1, (IShareStringCallback)this.mIShareStringCallbacks.get(var1));
                  } catch (Exception var3) {
                     var3.printStackTrace();
                  }
               }

               this.mIShareStringCallbacks.remove(var1);
            }
         }

      }
   }

   private class MyIServiceConnectListener implements IServiceConnectListener {
      private SourceConstantsDef.MODULE_ID mModuleId;
      final ShareDataManager this$0;

      MyIServiceConnectListener(ShareDataManager var1, SourceConstantsDef.MODULE_ID var2) {
         this.this$0 = var1;
         this.mModuleId = var2;
      }

      public void onConnected() {
         this.this$0.resetCallbacks(this.mModuleId);
      }
   }

   private class MyIShareBundleCallback extends IShareBundleCallback.Stub {
      private String mKey;
      final ShareDataManager this$0;

      private MyIShareBundleCallback(ShareDataManager var1, String var2) {
         this.this$0 = var1;
         this.mKey = var2;
      }

      // $FF: synthetic method
      MyIShareBundleCallback(ShareDataManager var1, String var2, Object var3) {
         this(var1, var2);
      }

      public void onBundle(Bundle var1, int var2) throws RemoteException {
         ArrayList var3 = (ArrayList)this.this$0.mShareBundleListeners.get(this.mKey);
         var2 = 0;

         while(true) {
            try {
               if (var2 >= var3.size()) {
                  break;
               }

               ((IShareBundleListener)var3.get(var2)).onBundle(var1);
            } catch (Exception var4) {
               var4.printStackTrace();
               break;
            }

            ++var2;
         }

      }
   }

   private class MyIShareIntCallback extends IShareIntCallback.Stub {
      private String mKey;
      final ShareDataManager this$0;

      private MyIShareIntCallback(ShareDataManager var1, String var2) {
         this.this$0 = var1;
         this.mKey = var2;
      }

      // $FF: synthetic method
      MyIShareIntCallback(ShareDataManager var1, String var2, Object var3) {
         this(var1, var2);
      }

      public void onInt(int var1, int var2) throws RemoteException {
         ArrayList var3 = (ArrayList)this.this$0.mShareIntListeners.get(this.mKey);
         var2 = 0;

         while(true) {
            try {
               if (var2 >= var3.size()) {
                  break;
               }

               ((IShareIntListener)var3.get(var2)).onInt(var1);
            } catch (Exception var4) {
               var4.printStackTrace();
               break;
            }

            ++var2;
         }

      }
   }

   private class MyIShareStringCallback extends IShareStringCallback.Stub {
      private String mKey;
      final ShareDataManager this$0;

      private MyIShareStringCallback(ShareDataManager var1, String var2) {
         this.this$0 = var1;
         this.mKey = var2;
      }

      // $FF: synthetic method
      MyIShareStringCallback(ShareDataManager var1, String var2, Object var3) {
         this(var1, var2);
      }

      public void onString(String var1, int var2) throws RemoteException {
         ArrayList var3 = (ArrayList)this.this$0.mShareStringListeners.get(this.mKey);
         var2 = 0;

         while(true) {
            try {
               if (var2 >= var3.size()) {
                  break;
               }

               ((IShareStringListener)var3.get(var2)).onString(var1);
            } catch (Exception var4) {
               var4.printStackTrace();
               break;
            }

            ++var2;
         }

      }
   }
}
