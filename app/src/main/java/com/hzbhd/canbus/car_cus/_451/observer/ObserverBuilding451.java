package com.hzbhd.canbus.car_cus._451.observer;

import com.hzbhd.canbus.car_cus._451.Interface.CanInfoObserver;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class ObserverBuilding451 {
   private static ObserverBuilding451 building;
   private static List observerList = new ArrayList();

   private ObserverBuilding451() {
   }

   // $FF: synthetic method
   ObserverBuilding451(Object var1) {
      this();
   }

   public static ObserverBuilding451 getInstance() {
      return ObserverBuilding451.Holder.INSTANCE;
   }

   public void dataChange() {
      BaseUtil.INSTANCE.runUi(new Function0(this) {
         final ObserverBuilding451 this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            Iterator var1 = ObserverBuilding451.observerList.iterator();

            while(var1.hasNext()) {
               ((CanInfoObserver)var1.next()).dataChange();
            }

            return null;
         }
      });
   }

   public void register(CanInfoObserver var1) {
      if (!observerList.contains(var1)) {
         observerList.add(var1);
      }

   }

   public void unRegister(CanInfoObserver var1) {
      if (!observerList.isEmpty() && observerList.contains(var1)) {
         observerList.remove(var1);
      }

   }

   private static class Holder {
      private static final ObserverBuilding451 INSTANCE = new ObserverBuilding451();
   }
}
