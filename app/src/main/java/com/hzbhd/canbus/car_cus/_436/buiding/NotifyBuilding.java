package com.hzbhd.canbus.car_cus._436.buiding;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotifyBuilding {
   private static final Integer UI_UPDATE_TAG = 9090;
   private static NotifyBuilding notifyBuilding;
   private Handler handler = new Handler(this, Looper.getMainLooper()) {
      final NotifyBuilding this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.arg1 == NotifyBuilding.UI_UPDATE_TAG) {
            Iterator var2 = this.this$0.list.iterator();

            while(var2.hasNext()) {
               ((MdNotifyListener)var2.next()).updateUi();
            }
         }

      }
   };
   private List list = new ArrayList();

   private NotifyBuilding() {
   }

   public static NotifyBuilding getInstance() {
      if (notifyBuilding == null) {
         notifyBuilding = new NotifyBuilding();
      }

      return notifyBuilding;
   }

   public void register(MdNotifyListener var1) {
      this.list.add(var1);
   }

   public void unRegister(MdNotifyListener var1) {
      if (!this.list.isEmpty()) {
         this.list.remove(var1);
      }

   }

   public void updateUi() {
      Message var1 = new Message();
      var1.arg1 = UI_UPDATE_TAG;
      this.handler.sendMessage(var1);
   }
}
