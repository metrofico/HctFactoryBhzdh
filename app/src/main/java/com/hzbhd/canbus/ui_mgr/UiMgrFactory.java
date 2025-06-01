package com.hzbhd.canbus.ui_mgr;

import android.content.Context;
import com.hzbhd.canbus.interfaces.UiMgrInterface;

public class UiMgrFactory {
   private static UiMgrInterfaceUtil mUiMgrInterface;

   public static UiMgrInterface getCanUiMgr(Context var0) {
      synchronized(UiMgrFactory.class){}

      UiMgrInterface var3;
      try {
         var3 = getCanUiMgrUtil(var0).getMUiMgrInterface();
      } finally {
         ;
      }

      return var3;
   }

   public static UiMgrInterfaceUtil getCanUiMgrUtil(Context var0) {
      synchronized(UiMgrFactory.class){}

      UiMgrInterfaceUtil var4;
      try {
         if (mUiMgrInterface == null) {
            UiMgrInterfaceUtil var1 = new UiMgrInterfaceUtil(var0);
            mUiMgrInterface = var1;
         }

         var4 = mUiMgrInterface;
      } finally {
         ;
      }

      return var4;
   }

   public static void setThisNull() {
      mUiMgrInterface = null;
   }
}
