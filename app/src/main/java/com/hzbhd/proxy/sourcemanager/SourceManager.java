package com.hzbhd.proxy.sourcemanager;

import android.os.Bundle;
import android.os.RemoteException;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceService;
import java.util.HashMap;
import java.util.Iterator;

public class SourceManager {
   public static final int ERR_ACT_FAIL = -2;
   public static final int ERR_NO_SERVICE = -1;
   public static final int ERR_SOURCE_NOT_DEFINED = -3;
   private static SourceManager mSourceManager;
   private HashMap mDispSourceCallbacks = new HashMap();
   private HashMap mDispSourceListeners = new HashMap();
   private HashMap mSourceBluetoothCallbacks = new HashMap();
   private HashMap mSourceBluetoothListeners = new HashMap();

   public static SourceManager getInstance() {
      // $FF: Couldn't be decompiled
   }

   private void removeISourceCallback(int var1) {
      if (this.mDispSourceCallbacks.containsKey(var1)) {
         this.mDispSourceCallbacks.remove(var1);
      }

   }

   public SourceConstantsDef.SOURCE_ID getCurrentAudioSource(SourceConstantsDef.DISP_ID var1) {
      ISourceService var4 = SourceServiceManager.getISourceService();
      if (var4 == null) {
         return SourceConstantsDef.SOURCE_ID.NULL;
      } else {
         byte var3 = 0;

         int var2;
         try {
            var2 = var4.getCurrentAudioSource(var1.ordinal());
         } catch (RemoteException var5) {
            var5.printStackTrace();
            var2 = var3;
         }

         return SourceConstantsDef.SOURCE_ID.values()[var2];
      }
   }

   public void notifyAppAudio(SourceConstantsDef.SOURCE_ID var1, String var2, int var3, boolean var4) {
      ISourceService var5 = SourceServiceManager.getISourceService();
      if (var5 != null) {
         try {
            var5.notifyAppAudio(var1.getValue(), var2, var3, var4);
         } catch (Exception var6) {
            var6.printStackTrace();
         }

      }
   }

   public boolean registerSourceListener(SourceConstantsDef.SOURCE_ID var1, SourceConstantsDef.DISP_ID var2, ISourceListener var3) {
      int var4 = SourceUtils.getDispSource(var1.ordinal(), var2.ordinal());
      this.mDispSourceListeners.put(var4, var3);
      if (!this.mDispSourceCallbacks.containsKey(var4)) {
         this.mDispSourceCallbacks.put(var4, new MyISourceCallback(this, var4));
      }

      ISourceService var7 = SourceServiceManager.getISourceService();
      if (var7 == null) {
         return false;
      } else {
         boolean var5;
         try {
            var5 = var7.registerSourceCallback(var4, (ISourceCallback)this.mDispSourceCallbacks.get(var4));
         } catch (Exception var6) {
            var6.printStackTrace();
            var5 = false;
         }

         if (!var5) {
            this.removeISourceCallback(var4);
            return false;
         } else {
            return true;
         }
      }
   }

   public int releaseAudioChannel(SourceConstantsDef.SOURCE_ID var1, SourceConstantsDef.DISP_ID var2) {
      ISourceService var4 = SourceServiceManager.getISourceService();
      if (var4 == null) {
         return -1;
      } else {
         try {
            int var3 = var4.releaseAudioChannel(var1.ordinal(), var2.ordinal());
            return var3;
         } catch (Exception var5) {
            var5.printStackTrace();
            return -2;
         }
      }
   }

   public boolean releaseBluetooth(SourceConstantsDef.SOURCE_ID var1) {
      ISourceService var3 = SourceServiceManager.getISourceService();
      if (var3 == null) {
         return false;
      } else {
         int var2 = var1.getValue();
         if (this.mSourceBluetoothListeners.containsKey(var2)) {
            this.mSourceBluetoothListeners.remove(var2);
            this.mSourceBluetoothCallbacks.remove(var2);

            try {
               var3.releaseBluetooth(var2);
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }

         return true;
      }
   }

   public int requestAudioChannel(SourceConstantsDef.SOURCE_ID var1, SourceConstantsDef.DISP_ID var2) {
      return this.requestAudioChannel(var1, var2, (Bundle)null);
   }

   public int requestAudioChannel(SourceConstantsDef.SOURCE_ID var1, SourceConstantsDef.DISP_ID var2, Bundle var3) {
      ISourceService var5 = SourceServiceManager.getISourceService();
      if (var5 == null) {
         return -1;
      } else {
         try {
            int var4 = var5.requestAudioChannel(var1.ordinal(), var2.ordinal(), var3);
            return var4;
         } catch (Exception var6) {
            var6.printStackTrace();
            return -2;
         }
      }
   }

   public int requestAudioChannel(SourceConstantsDef.SOURCE_ID var1, SourceConstantsDef.DISP_ID var2, String var3, String var4) {
      Bundle var5 = new Bundle();
      var5.putString("pkg_name", var3);
      var5.putString("cls_name", var3);
      return this.requestAudioChannel(var1, var2, var5);
   }

   public boolean requestBluetooth(SourceConstantsDef.SOURCE_ID var1) {
      ISourceService var3 = SourceServiceManager.getISourceService();
      if (var3 == null) {
         return false;
      } else {
         try {
            boolean var2 = var3.requestBluetooth(var1.getValue(), (ISourceBluetoothCallback)null);
            return var2;
         } catch (Exception var4) {
            var4.printStackTrace();
            return true;
         }
      }
   }

   public boolean requestBluetooth(SourceConstantsDef.SOURCE_ID var1, ISourceBluetoothListener var2) {
      ISourceService var5 = SourceServiceManager.getISourceService();
      if (var5 == null) {
         return false;
      } else {
         int var3 = var1.getValue();
         this.mSourceBluetoothListeners.put(var3, var2);
         if (this.mSourceBluetoothCallbacks.containsKey(var3)) {
            return true;
         } else {
            this.mSourceBluetoothCallbacks.put(var3, new MyISourceBluetoothCallback(this, var3));

            try {
               boolean var4 = var5.requestBluetooth(var3, (ISourceBluetoothCallback)this.mSourceBluetoothCallbacks.get(var3));
               return var4;
            } catch (Exception var6) {
               var6.printStackTrace();
               return true;
            }
         }
      }
   }

   public int requestSource(SourceConstantsDef.SOURCE_ID var1, SourceConstantsDef.DISP_ID var2, Bundle var3) {
      Bundle var4 = new Bundle();
      var4.putBundle("start", var3);
      return this.requestAudioChannel(var1, var2, var4);
   }

   public void resetSourceBluetoothCallbacks() {
      if (!this.mSourceBluetoothCallbacks.isEmpty()) {
         ISourceService var4 = SourceServiceManager.getISourceService();
         Iterator var3 = this.mSourceBluetoothCallbacks.keySet().iterator();

         while(var3.hasNext()) {
            int var1 = (Integer)var3.next();

            try {
               var4.requestBluetooth(var1, (ISourceBluetoothCallback)this.mSourceBluetoothCallbacks.get(var1));
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

      }
   }

   public void resetSourceCallback() {
      if (!this.mDispSourceCallbacks.isEmpty()) {
         ISourceService var2 = SourceServiceManager.getISourceService();
         Iterator var3 = this.mDispSourceCallbacks.keySet().iterator();

         while(var3.hasNext()) {
            int var1 = (Integer)var3.next();

            try {
               var2.registerSourceCallback(var1, (ISourceCallback)this.mDispSourceCallbacks.get(var1));
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

      }
   }

   public boolean unregisterSourceListener(SourceConstantsDef.SOURCE_ID var1, SourceConstantsDef.DISP_ID var2) {
      int var3 = SourceUtils.getDispSource(var1.ordinal(), var2.ordinal());
      boolean var5 = this.mDispSourceListeners.containsKey(var3);
      boolean var4 = false;
      if (!var5) {
         return false;
      } else {
         this.mDispSourceListeners.remove(var3);
         ISourceService var7 = SourceServiceManager.getISourceService();

         label15: {
            try {
               var5 = var7.unregisterSourceCallback(var3);
            } catch (Exception var6) {
               var6.printStackTrace();
               break label15;
            }

            var4 = var5;
         }

         this.removeISourceCallback(var3);
         return var4;
      }
   }

   private class MyISourceBluetoothCallback extends ISourceBluetoothCallback.Stub {
      private int mSourceId;
      final SourceManager this$0;

      public MyISourceBluetoothCallback(SourceManager var1, int var2) {
         this.this$0 = var1;
         this.mSourceId = var2;
      }

      public void onState(int var1) throws RemoteException {
         if (this.this$0.mSourceBluetoothListeners.containsKey(this.mSourceId)) {
            ((ISourceBluetoothListener)this.this$0.mSourceBluetoothListeners.get(this.mSourceId)).onState(var1);
         }

      }
   }

   private class MyISourceCallback extends ISourceCallback.Stub {
      private int mDispSource;
      final SourceManager this$0;

      public MyISourceCallback(SourceManager var1, int var2) {
         this.this$0 = var1;
         this.mDispSource = var2;
      }

      public void onAction(int var1, Bundle var2) throws RemoteException {
         if (this.this$0.mDispSourceListeners.containsKey(this.mDispSource)) {
            SourceConstantsDef.SOURCE_ACTION[] var3 = SourceConstantsDef.SOURCE_ACTION.values();
            ((ISourceListener)this.this$0.mDispSourceListeners.get(this.mDispSource)).onAction(var3[var1], var2);
         }
      }
   }
}
