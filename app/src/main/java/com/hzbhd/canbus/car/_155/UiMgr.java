package com.hzbhd.canbus.car._155;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;

public class UiMgr extends AbstractUiMgr {
   public UiMgr(Context var1) {
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(var1);
      var2.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this, var2) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
         }

         public void onClickBottomBtnItem(int var1) {
            String var3 = this.val$originalCarDevicePageUiSet.getRowBottomBtnAction()[var1];
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case 3317767:
                  if (var3.equals("left")) {
                     var4 = 0;
                  }
                  break;
               case 3443508:
                  if (var3.equals("play")) {
                     var4 = 1;
                  }
                  break;
               case 3540994:
                  if (var3.equals("stop")) {
                     var4 = 2;
                  }
                  break;
               case 108511772:
                  if (var3.equals("right")) {
                     var4 = 3;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, 4});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, -127});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, -128});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -110, 3});
            }

         }
      });
   }
}
