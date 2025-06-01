package com.hzbhd.canbus.media.aux2.manager;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.media.aux2.action.ActionCallback;
import com.hzbhd.canbus.media.aux2.action.ActionTag;

public class Aux2Manager {
   private ActionCallback actionCallback;
   private int cameraFlag;

   private Aux2Manager() {
      this.cameraFlag = 6;
   }

   // $FF: synthetic method
   Aux2Manager(Object var1) {
      this();
   }

   public static Aux2Manager getInstance() {
      return Aux2Manager.Holder.INSTANCE;
   }

   public void exitAux2(ActionTag.TAG var1, String var2) {
      ActionCallback var3 = this.actionCallback;
      if (var3 != null) {
         var3.actionDo(var1, var2);
      }

   }

   public int getCameraFlag() {
      return this.cameraFlag;
   }

   public void openAux2(Context var1, int var2, int var3) {
      Intent var4 = new Intent();
      var4.setComponent(HzbhdComponentName.Aux2Activity);
      var4.setFlags(268435456);
      var4.putExtra("0xFFFF", var2);
      var4.putExtra("0xDDDD", var3);
      var1.startActivity(var4);
   }

   public void registerExitListener(ActionCallback var1) {
      this.actionCallback = var1;
   }

   public void setCameraFlag(int var1) {
      this.cameraFlag = var1;
   }

   public void unRegisterExitListener() {
      this.actionCallback = null;
   }

   private static class Holder {
      private static final Aux2Manager INSTANCE = new Aux2Manager();
   }
}
