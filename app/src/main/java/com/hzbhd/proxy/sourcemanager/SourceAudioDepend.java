package com.hzbhd.proxy.sourcemanager;

import android.os.RemoteException;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceService;
import java.util.HashMap;
import java.util.Iterator;

public class SourceAudioDepend {
   private static SourceAudioDepend mAudioDepend;
   private HashMap mDispAudioCallbacks = new HashMap();
   private HashMap mDispAudioListeners = new HashMap();

   private IAudioCallback getIAudioCallback(int var1) {
      if (!this.mDispAudioCallbacks.containsKey(var1)) {
         this.mDispAudioCallbacks.put(var1, new MyIAudioCallback(this, var1));
      }

      return (IAudioCallback)this.mDispAudioCallbacks.get(var1);
   }

   public static SourceAudioDepend getInstance() {
      // $FF: Couldn't be decompiled
   }

   public int registerAudioListener(SourceConstantsDef.DISP_ID var1, IAudioDependListener var2) {
      ISourceService var4 = SourceServiceManager.getISourceService();
      if (var4 == null) {
         return -1;
      } else {
         int var3 = var1.ordinal();
         this.mDispAudioListeners.put(var3, var2);

         try {
            var3 = var4.registerAudioCallback(var3, this.getIAudioCallback(var3));
            return var3;
         } catch (Exception var5) {
            var5.printStackTrace();
            return -2;
         }
      }
   }

   public void resetAudioCallback() {
      if (!this.mDispAudioCallbacks.isEmpty()) {
         ISourceService var2 = SourceServiceManager.getISourceService();
         Iterator var3 = this.mDispAudioCallbacks.keySet().iterator();

         while(var3.hasNext()) {
            int var1 = (Integer)var3.next();

            try {
               var2.registerAudioCallback(var1, (IAudioCallback)this.mDispAudioCallbacks.get(var1));
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

      }
   }

   public boolean unregisterAudioListener(int var1, IAudioDependListener var2) {
      ISourceService var3 = SourceServiceManager.getISourceService();
      if (var3 == null) {
         return false;
      } else {
         if (this.mDispAudioListeners.containsKey(var1) && this.mDispAudioListeners.get(var1) == var2) {
            this.mDispAudioListeners.remove(var1);

            try {
               var3.unregisterAudioCallback(var1, this.getIAudioCallback(var1));
            } catch (RemoteException var4) {
               var4.printStackTrace();
            }

            this.mDispAudioCallbacks.remove(var1);
         }

         return true;
      }
   }

   private class MyIAudioCallback extends IAudioCallback.Stub {
      private int mDispId;
      final SourceAudioDepend this$0;

      public MyIAudioCallback(SourceAudioDepend var1, int var2) {
         this.this$0 = var1;
         this.mDispId = var2;
      }

      public void requestAudio(int var1, int var2, boolean var3) throws RemoteException {
         if (this.this$0.mDispAudioListeners.containsKey(this.mDispId)) {
            ((IAudioDependListener)this.this$0.mDispAudioListeners.get(this.mDispId)).requestAudio(var1, var2, var3);
         }

      }

      public void setNavi(int var1, int var2) throws RemoteException {
         if (this.this$0.mDispAudioListeners.containsKey(this.mDispId)) {
            ((IAudioDependListener)this.this$0.mDispAudioListeners.get(this.mDispId)).setNavi(var1, var2);
         }

      }
   }
}
