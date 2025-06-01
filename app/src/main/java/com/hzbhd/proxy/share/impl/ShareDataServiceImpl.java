package com.hzbhd.proxy.share.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.share.aidl.IShareBundleCallback;
import com.hzbhd.proxy.share.aidl.IShareDataService;
import com.hzbhd.proxy.share.aidl.IShareIntCallback;
import com.hzbhd.proxy.share.aidl.IShareStringCallback;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;

public final class ShareDataServiceImpl extends IShareDataService.Stub {
   private static final int MSG_BUNDLE_DATA = 3;
   private static final int MSG_INT_DATA = 1;
   private static final int MSG_STRING_DATA = 2;
   private static ShareDataServiceImpl sShareDataServiceImpl;
   private HashMap mBundleData = new HashMap();
   private Handler mHandler;
   private HandlerThread mHandlerThread = new HandlerThread(this, "hzbhd.ShareDataHandlerThread") {
      final ShareDataServiceImpl this$0;

      {
         this.this$0 = var1;
         this.start();
      }
   };
   private HashMap mIShareBundleCallbackMaps = new HashMap();
   private HashMap mIShareIntCallbackMaps = new HashMap();
   private HashMap mIShareStringCallbackMaps = new HashMap();
   private HashMap mIntegerData = new HashMap();
   private ArrayList mShareListener = new ArrayList();
   private HashMap mStringData = new HashMap();

   public ShareDataServiceImpl() {
      this.mHandler = new Handler(this, this.mHandlerThread.getLooper()) {
         final ShareDataServiceImpl this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message param1) {
            // $FF: Couldn't be decompiled
         }
      };
   }

   // $FF: synthetic method
   static HashMap access$000(ShareDataServiceImpl var0) {
      return var0.mIShareIntCallbackMaps;
   }

   // $FF: synthetic method
   static HashMap access$100(ShareDataServiceImpl var0) {
      return var0.mIShareStringCallbackMaps;
   }

   // $FF: synthetic method
   static HashMap access$200(ShareDataServiceImpl var0) {
      return var0.mIShareBundleCallbackMaps;
   }

   public static ShareDataServiceImpl addShareListeners(ShareListener var0) {
      if (!getInstance().mShareListener.contains(var0)) {
         getInstance().mShareListener.add(var0);
      }

      return getInstance();
   }

   public static ShareDataServiceImpl getInstance() {
      synchronized(ShareDataServiceImpl.class){}

      Throwable var10000;
      boolean var10001;
      label131: {
         try {
            if (sShareDataServiceImpl == null) {
               ShareDataServiceImpl var0 = new ShareDataServiceImpl();
               sShareDataServiceImpl = var0;
            }
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            break label131;
         }

         label128:
         try {
            return sShareDataServiceImpl;
         } catch (Throwable var11) {
            var10000 = var11;
            var10001 = false;
            break label128;
         }
      }

      while(true) {
         Throwable var13 = var10000;

         try {
            throw var13;
         } catch (Throwable var10) {
            var10000 = var10;
            var10001 = false;
            continue;
         }
      }
   }

   public static void init(SourceConstantsDef.MODULE_ID var0) {
      ServiceManager.addService("share_" + var0, getInstance());
   }

   public static void setBundle(String var0, Bundle var1) {
      getInstance().reportBundle(var0, var1);
   }

   public static void setInt(String var0, int var1) {
      getInstance().reportInt(var0, var1);
   }

   public static void setString(String var0, String var1) {
      getInstance().reportString(var0, var1);
   }

   public Bundle getBundle(String var1) {
      if (this.mBundleData.containsKey(var1)) {
         Bundle var4 = (Bundle)this.mBundleData.get(var1);
         if (LogUtil.log5()) {
            LogUtil.d("getBundle: " + var1 + " = " + var4);
         }

         return var4;
      } else {
         int var3 = this.mShareListener.size();
         int var2 = 0;
         if (var3 == 1) {
            return ((ShareListener)this.mShareListener.get(0)).getBundle(var1);
         } else {
            while(var2 < this.mShareListener.size()) {
               if (((ShareListener)this.mShareListener.get(var2)).getBundle(var1) != null) {
                  return ((ShareListener)this.mShareListener.get(var2)).getBundle(var1);
               }

               ++var2;
            }

            return null;
         }
      }
   }

   public int getInt(String var1) {
      int var2;
      if (this.mIntegerData.containsKey(var1)) {
         var2 = (Integer)this.mIntegerData.get(var1);
         if (LogUtil.log5()) {
            LogUtil.d("getInt: " + var1 + " = " + var2);
         }

         return var2;
      } else {
         int var3 = this.mShareListener.size();
         var2 = 0;
         if (var3 == 1) {
            return ((ShareListener)this.mShareListener.get(0)).getInt(var1);
         } else {
            while(var2 < this.mShareListener.size()) {
               if (((ShareListener)this.mShareListener.get(var2)).getInt(var1) != -2) {
                  return ((ShareListener)this.mShareListener.get(var2)).getInt(var1);
               }

               ++var2;
            }

            return -1;
         }
      }
   }

   public String getString(String var1) {
      if (this.mStringData.containsKey(var1)) {
         String var4 = (String)this.mStringData.get(var1);
         if (LogUtil.log5()) {
            LogUtil.d("getString: " + var1 + " = " + var4);
         }

         return var4;
      } else {
         int var3 = this.mShareListener.size();
         int var2 = 0;
         if (var3 == 1) {
            return ((ShareListener)this.mShareListener.get(0)).getString(var1);
         } else {
            while(var2 < this.mShareListener.size()) {
               if (((ShareListener)this.mShareListener.get(var2)).getString(var1) != null) {
                  return ((ShareListener)this.mShareListener.get(var2)).getString(var1);
               }

               ++var2;
            }

            return null;
         }
      }
   }

   public void notifyShareBundle(String var1, Bundle var2, int var3) {
      Message var5 = new Message();
      var5.what = 3;
      var5.arg2 = var3;
      Bundle var4 = new Bundle();
      var4.putString("key", var1);
      var4.putBundle("value", var2);
      var5.obj = var4;
      this.mHandler.sendMessage(var5);
   }

   public void notifyShareInt(String var1, int var2, int var3) {
      Message var4 = new Message();
      var4.what = 1;
      var4.arg1 = var2;
      var4.arg2 = var3;
      var4.obj = var1;
      this.mHandler.sendMessage(var4);
   }

   public void notifyShareString(String var1, String var2, int var3) {
      Message var4 = new Message();
      var4.what = 2;
      var4.arg2 = var3;
      Bundle var5 = new Bundle();
      var5.putString("key", var1);
      var5.putString("value", var2);
      var4.obj = var5;
      this.mHandler.sendMessage(var4);
   }

   public Bundle registerShareBundleCallback(String var1, IShareBundleCallback var2) throws RemoteException {
      if (LogUtil.log5()) {
         LogUtil.d("<registerShareBundleCallback> " + var1 + ": " + var2);
      }

      HashMap var3 = this.mIShareBundleCallbackMaps;
      synchronized(var3){}

      label249: {
         Throwable var10000;
         boolean var10001;
         label250: {
            try {
               if (!this.mIShareBundleCallbackMaps.containsKey(var1)) {
                  HashMap var4 = this.mIShareBundleCallbackMaps;
                  HashMap var5 = new HashMap();
                  var4.put(var1, var5);
               }
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               break label250;
            }

            try {
               if (!((HashMap)this.mIShareBundleCallbackMaps.get(var1)).containsKey(var2.asBinder())) {
                  ((HashMap)this.mIShareBundleCallbackMaps.get(var1)).put(var2.asBinder(), var2);
                  IBinder var29 = var2.asBinder();
                  MyShareBundleDeathRecipient var28 = new MyShareBundleDeathRecipient(this, var1, var2);
                  var29.linkToDeath(var28, 0);
               }
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label250;
            }

            label236:
            try {
               break label249;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label236;
            }
         }

         while(true) {
            Throwable var26 = var10000;

            try {
               throw var26;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               continue;
            }
         }
      }

      Bundle var27 = this.getBundle(var1);
      if (LogUtil.log5()) {
         LogUtil.d("registerShareBundleCallback: " + var1 + " :: " + var27);
      }

      return var27;
   }

   public int registerShareIntCallback(String var1, IShareIntCallback var2) throws RemoteException {
      if (LogUtil.log5()) {
         LogUtil.d("<registerShareIntCallback> " + var1 + ": " + var2);
      }

      HashMap var4 = this.mIShareIntCallbackMaps;
      synchronized(var4){}

      label249: {
         Throwable var10000;
         boolean var10001;
         label250: {
            try {
               if (!this.mIShareIntCallbackMaps.containsKey(var1)) {
                  HashMap var5 = this.mIShareIntCallbackMaps;
                  HashMap var6 = new HashMap();
                  var5.put(var1, var6);
               }
            } catch (Throwable var26) {
               var10000 = var26;
               var10001 = false;
               break label250;
            }

            try {
               if (!((HashMap)this.mIShareIntCallbackMaps.get(var1)).containsKey(var2.asBinder())) {
                  ((HashMap)this.mIShareIntCallbackMaps.get(var1)).put(var2.asBinder(), var2);
                  IBinder var29 = var2.asBinder();
                  MyShareIntDeathRecipient var28 = new MyShareIntDeathRecipient(this, var1, var2);
                  var29.linkToDeath(var28, 0);
               }
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               break label250;
            }

            label236:
            try {
               break label249;
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label236;
            }
         }

         while(true) {
            Throwable var27 = var10000;

            try {
               throw var27;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               continue;
            }
         }
      }

      int var3 = this.getInt(var1);
      if (LogUtil.log5()) {
         LogUtil.d("registerShareIntCallback: " + var1 + " :: " + var3);
      }

      return var3;
   }

   public String registerShareStringCallback(String var1, IShareStringCallback var2) throws RemoteException {
      if (LogUtil.log5()) {
         LogUtil.d("<registerShareStringCallback> " + var1 + ": " + var2);
      }

      HashMap var3 = this.mIShareStringCallbackMaps;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label230: {
         try {
            if (!this.mIShareStringCallbackMaps.containsKey(var1)) {
               HashMap var4 = this.mIShareStringCallbackMaps;
               HashMap var5 = new HashMap();
               var4.put(var1, var5);
            }
         } catch (Throwable var25) {
            var10000 = var25;
            var10001 = false;
            break label230;
         }

         try {
            if (!((HashMap)this.mIShareStringCallbackMaps.get(var1)).containsKey(var2.asBinder())) {
               ((HashMap)this.mIShareStringCallbackMaps.get(var1)).put(var2.asBinder(), var2);
               IBinder var28 = var2.asBinder();
               MyShareStringDeathRecipient var27 = new MyShareStringDeathRecipient(this, var1, var2);
               var28.linkToDeath(var27, 0);
            }
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label230;
         }

         label216:
         try {
            return this.getString(var1);
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label216;
         }
      }

      while(true) {
         Throwable var26 = var10000;

         try {
            throw var26;
         } catch (Throwable var22) {
            var10000 = var22;
            var10001 = false;
            continue;
         }
      }
   }

   public void reportBundle(String var1, Bundle var2) {
      this.mBundleData.put(var1, var2);
      this.notifyShareBundle(var1, var2, 0);
   }

   public void reportInt(String var1, int var2) {
      this.mIntegerData.put(var1, var2);
      this.notifyShareInt(var1, var2, 0);
   }

   public void reportOtherBundle(String var1, Bundle var2, int var3) {
      this.mBundleData.put(var1, var2);
      this.reportOtherBundle(var1, var2, var3);
   }

   public void reportOtherInt(String var1, int var2, int var3) {
      this.mIntegerData.put(var1, var2);
      this.notifyShareInt(var1, var2, var3);
   }

   public void reportOtherString(String var1, String var2, int var3) {
      this.mStringData.put(var1, var2);
      this.notifyShareString(var1, var2, var3);
   }

   public void reportString(String var1, String var2) {
      this.mStringData.put(var1, var2);
      this.notifyShareString(var1, var2, 0);
   }

   public void unregisterShareBundleCallback(String var1, IShareBundleCallback var2) {
      if (LogUtil.log5()) {
         LogUtil.d("<unregisterShareBundleCallback> " + var1 + ": " + var2);
      }

      HashMap var3 = this.mIShareBundleCallbackMaps;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label227: {
         try {
            if (!this.mIShareBundleCallbackMaps.containsKey(var1)) {
               return;
            }
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label227;
         }

         try {
            if (((HashMap)this.mIShareBundleCallbackMaps.get(var1)).containsKey(var2.asBinder())) {
               ((HashMap)this.mIShareBundleCallbackMaps.get(var1)).remove(var2.asBinder());
               if (((HashMap)this.mIShareBundleCallbackMaps.get(var1)).isEmpty()) {
                  this.mIShareBundleCallbackMaps.remove(var1);
               }
            }
         } catch (Throwable var22) {
            var10000 = var22;
            var10001 = false;
            break label227;
         }

         label215:
         try {
            return;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            break label215;
         }
      }

      while(true) {
         Throwable var24 = var10000;

         try {
            throw var24;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            continue;
         }
      }
   }

   public void unregisterShareIntCallback(String var1, IShareIntCallback var2) {
      if (LogUtil.log5()) {
         LogUtil.d("<unregisterShareIntCallback> " + var1 + ": " + var2);
      }

      HashMap var3 = this.mIShareIntCallbackMaps;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label227: {
         try {
            if (!this.mIShareIntCallbackMaps.containsKey(var1)) {
               return;
            }
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label227;
         }

         try {
            if (((HashMap)this.mIShareIntCallbackMaps.get(var1)).containsKey(var2.asBinder())) {
               ((HashMap)this.mIShareIntCallbackMaps.get(var1)).remove(var2.asBinder());
               if (((HashMap)this.mIShareIntCallbackMaps.get(var1)).isEmpty()) {
                  this.mIShareIntCallbackMaps.remove(var1);
               }
            }
         } catch (Throwable var22) {
            var10000 = var22;
            var10001 = false;
            break label227;
         }

         label215:
         try {
            return;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            break label215;
         }
      }

      while(true) {
         Throwable var24 = var10000;

         try {
            throw var24;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            continue;
         }
      }
   }

   public void unregisterShareStringCallback(String var1, IShareStringCallback var2) {
      if (LogUtil.log5()) {
         LogUtil.d("<unregisterShareStringCallback> " + var1 + ": " + var2);
      }

      HashMap var3 = this.mIShareStringCallbackMaps;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label227: {
         try {
            if (!this.mIShareStringCallbackMaps.containsKey(var1)) {
               return;
            }
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label227;
         }

         try {
            if (((HashMap)this.mIShareStringCallbackMaps.get(var1)).containsKey(var2.asBinder())) {
               ((HashMap)this.mIShareStringCallbackMaps.get(var1)).remove(var2.asBinder());
               if (((HashMap)this.mIShareStringCallbackMaps.get(var1)).isEmpty()) {
                  this.mIShareStringCallbackMaps.remove(var1);
               }
            }
         } catch (Throwable var22) {
            var10000 = var22;
            var10001 = false;
            break label227;
         }

         label215:
         try {
            return;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            break label215;
         }
      }

      while(true) {
         Throwable var24 = var10000;

         try {
            throw var24;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            continue;
         }
      }
   }

   private class MyShareBundleDeathRecipient implements DeathRecipient {
      private IShareBundleCallback mDiedCallback;
      private String mKey;
      final ShareDataServiceImpl this$0;

      public MyShareBundleDeathRecipient(ShareDataServiceImpl var1, String var2, IShareBundleCallback var3) {
         this.this$0 = var1;
         this.mKey = var2;
         this.mDiedCallback = var3;
      }

      public void binderDied() {
         this.this$0.unregisterShareBundleCallback(this.mKey, this.mDiedCallback);
      }
   }

   private class MyShareIntDeathRecipient implements DeathRecipient {
      private IShareIntCallback mDiedCallback;
      private String mKey;
      final ShareDataServiceImpl this$0;

      public MyShareIntDeathRecipient(ShareDataServiceImpl var1, String var2, IShareIntCallback var3) {
         this.this$0 = var1;
         this.mKey = var2;
         this.mDiedCallback = var3;
      }

      public void binderDied() {
         this.this$0.unregisterShareIntCallback(this.mKey, this.mDiedCallback);
      }
   }

   private class MyShareStringDeathRecipient implements DeathRecipient {
      private IShareStringCallback mDiedCallback;
      private String mKey;
      final ShareDataServiceImpl this$0;

      public MyShareStringDeathRecipient(ShareDataServiceImpl var1, String var2, IShareStringCallback var3) {
         this.this$0 = var1;
         this.mKey = var2;
         this.mDiedCallback = var3;
      }

      public void binderDied() {
         this.this$0.unregisterShareStringCallback(this.mKey, this.mDiedCallback);
      }
   }

   public interface ShareListener {
      Bundle getBundle(String var1);

      int getInt(String var1);

      String getString(String var1);
   }

   public static class ShareListeners implements ShareListener {
      public Bundle getBundle(String var1) {
         return null;
      }

      public int getInt(String var1) {
         return 0;
      }

      public String getString(String var1) {
         return null;
      }
   }
}
