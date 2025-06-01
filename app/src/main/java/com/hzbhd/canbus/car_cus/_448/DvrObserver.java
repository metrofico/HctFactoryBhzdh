package com.hzbhd.canbus.car_cus._448;

import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class DvrObserver {
   private static List observerList = new ArrayList();

   private DvrObserver() {
   }

   // $FF: synthetic method
   DvrObserver(Object var1) {
      this();
   }

   public static DvrObserver getInstance() {
      return DvrObserver.Holder.canObserver;
   }

   public void dataChange(String var1) {
      BaseUtil.INSTANCE.runUi(new Function0(this, var1) {
         final DvrObserver this$0;
         final String val$MODE_TAG;

         {
            this.this$0 = var1;
            this.val$MODE_TAG = var2;
         }

         public Unit invoke() {
            Iterator var1 = DvrObserver.observerList.iterator();

            while(var1.hasNext()) {
               ((DvrDataInterface)var1.next()).dataChange(this.val$MODE_TAG);
            }

            return null;
         }
      });
   }

   public void register(DvrDataInterface var1) {
      if (!observerList.contains(var1)) {
         observerList.add(var1);
      }

   }

   public void unRegister(DvrDataInterface var1) {
      if (!observerList.isEmpty() && observerList.contains(var1)) {
         observerList.remove(var1);
      }

   }

   private static class Holder {
      private static final DvrObserver canObserver = new DvrObserver();
   }
}
