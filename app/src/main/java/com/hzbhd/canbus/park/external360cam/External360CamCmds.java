package com.hzbhd.canbus.park.external360cam;

import com.hzbhd.canbus.adapter.util.FutureUtil;

public class External360CamCmds {
   private static ItfcCmds Cmds;
   static External360CamCmds mExternal360CamCmds;

   public static External360CamCmds getInstance() {
      if (mExternal360CamCmds == null) {
         mExternal360CamCmds = new External360CamCmds();
      }

      return mExternal360CamCmds;
   }

   public ItfcCmds getCmds() {
      int var1 = FutureUtil.instance.is360External();
      ItfcCmds var2 = Cmds;
      if (var2 == null) {
         if (var1 != 1) {
            if (var1 != 2) {
               CmdsDefualt var5 = new CmdsDefualt();
               Cmds = var5;
               return var5;
            } else {
               CmdsMalaysia var4 = new CmdsMalaysia();
               Cmds = var4;
               return var4;
            }
         } else {
            CmdsHanSheng var3 = new CmdsHanSheng();
            Cmds = var3;
            return var3;
         }
      } else {
         return var2;
      }
   }
}
