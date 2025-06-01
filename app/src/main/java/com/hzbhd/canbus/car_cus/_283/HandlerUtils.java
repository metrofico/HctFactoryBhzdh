package com.hzbhd.canbus.car_cus._283;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HandlerUtils {
   private static HandlerUtils mHandlerUtils;
   private final MyHandler mHandler = new MyHandler(this, Looper.getMainLooper());
   List mIFreshUICallback = new ArrayList();

   private HandlerUtils() {
   }

   public static HandlerUtils getInstance() {
      synchronized(HandlerUtils.class){}

      HandlerUtils var0;
      try {
         if (mHandlerUtils == null) {
            var0 = new HandlerUtils();
            mHandlerUtils = var0;
         }

         var0 = mHandlerUtils;
      } finally {
         ;
      }

      return var0;
   }

   public void refreshUI() {
      this.mHandler.sendEmptyMessage(1);
   }

   public void registerCallBack(IFreshUICallback var1) {
      if (var1 != null) {
         this.mIFreshUICallback.add(var1);
      }

   }

   public void unregisterCallBack(IFreshUICallback var1) {
      if (var1 != null) {
         this.mIFreshUICallback.remove(var1);
      }

   }

   public interface IFreshUICallback {
      void callback();
   }

   class MyHandler extends Handler {
      final HandlerUtils this$0;

      public MyHandler(HandlerUtils var1, Looper var2) {
         super(var2);
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         if (this.this$0.mIFreshUICallback != null) {
            Iterator var2 = this.this$0.mIFreshUICallback.iterator();

            while(var2.hasNext()) {
               ((IFreshUICallback)var2.next()).callback();
            }
         }

      }
   }
}
