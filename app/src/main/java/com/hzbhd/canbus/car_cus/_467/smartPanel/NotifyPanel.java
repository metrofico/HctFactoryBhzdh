package com.hzbhd.canbus.car_cus._467.smartPanel;

import com.hzbhd.canbus.interfaces.ActionDo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotifyPanel {
   private static NotifyPanel notifyPanel;
   List list = new ArrayList();

   private NotifyPanel() {
   }

   public static NotifyPanel getInstance() {
      if (notifyPanel == null) {
         notifyPanel = new NotifyPanel();
      }

      return notifyPanel;
   }

   public void register(ActionDo var1) {
      this.list.add(var1);
   }

   public void unregister(ActionDo var1) {
      if (!this.list.isEmpty() && this.list.contains(var1)) {
         this.list.remove(var1);
      }

   }

   public void update() {
      Iterator var1 = this.list.iterator();

      while(var1.hasNext()) {
         ((ActionDo)var1.next()).todo((List)null);
      }

   }
}
