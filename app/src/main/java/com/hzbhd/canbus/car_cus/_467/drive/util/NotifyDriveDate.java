package com.hzbhd.canbus.car_cus._467.drive.util;

import com.hzbhd.canbus.interfaces.ActionDo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotifyDriveDate {
   private static NotifyDriveDate notifyPanel;
   List list = new ArrayList();

   private NotifyDriveDate() {
   }

   public static NotifyDriveDate getInstance() {
      if (notifyPanel == null) {
         notifyPanel = new NotifyDriveDate();
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
