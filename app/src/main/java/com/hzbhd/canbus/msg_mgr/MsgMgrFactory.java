package com.hzbhd.canbus.msg_mgr;

import android.content.Context;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;

public class MsgMgrFactory {
   private static final String TAG = "MsgMgrFactory";
   public static MsgMgrInterfaceUtil mMsgMgrInterface;

   public static MsgMgrInterface getCanMsgMgr(Context var0) {
      synchronized(MsgMgrFactory.class){}

      MsgMgrInterface var3;
      try {
         var3 = getCanMsgMgrUtil(var0).getMMsgMgrInterface();
      } finally {
         ;
      }

      return var3;
   }

   public static MsgMgrInterfaceUtil getCanMsgMgrUtil(Context var0) {
      synchronized(MsgMgrFactory.class){}

      MsgMgrInterfaceUtil var4;
      try {
         if (mMsgMgrInterface == null) {
            MsgMgrInterfaceUtil var1 = new MsgMgrInterfaceUtil(var0);
            mMsgMgrInterface = var1;
         }

         var4 = mMsgMgrInterface;
      } finally {
         ;
      }

      return var4;
   }

   public static void setThisNull() {
      mMsgMgrInterface = null;
   }
}
