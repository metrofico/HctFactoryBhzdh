package com.hzbhd.canbus.car_cus._446;

import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class CanObserver {
   private static CanObserver building;
   private static List observerList = new ArrayList();

   private CanObserver() {
   }

   public static CanObserver getInstance() {
      if (building == null) {
         building = new CanObserver();
      }

      return building;
   }

   public void dataChange() {
      BaseUtil.INSTANCE.runUi(new Function0(this) {
         final CanObserver this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            Iterator var1 = CanObserver.observerList.iterator();

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
}
