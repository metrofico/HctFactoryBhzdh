package com.hzbhd.canbus.car_cus._439.air.observer;

import com.hzbhd.canbus.car_cus._439.air.Interface.AirInfoObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AirBuilder {
   private static AirBuilder building;
   private static List observerList = new ArrayList();

   private AirBuilder() {
   }

   public static AirBuilder getInstance() {
      if (building == null) {
         building = new AirBuilder();
      }

      return building;
   }

   public void dataChange() {
      Iterator var1 = observerList.iterator();

      while(var1.hasNext()) {
         ((AirInfoObserver)var1.next()).infoChange();
      }

   }

   public void register(AirInfoObserver var1) {
      if (!observerList.contains(var1)) {
         observerList.add(var1);
      }

   }

   public void unRegister(AirInfoObserver var1) {
      if (!observerList.isEmpty() && observerList.contains(var1)) {
         observerList.remove(var1);
      }

   }
}
