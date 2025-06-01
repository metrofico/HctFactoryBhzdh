package com.hzbhd.canbus.car._380;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      PanelKeyPageUiSet var2 = this.getPanelKeyPageUiSet(var1);
      var2.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener(this, var2) {
         final UiMgr this$0;
         final PanelKeyPageUiSet val$panelKeyPageUiSet;

         {
            this.this$0 = var1;
            this.val$panelKeyPageUiSet = var2;
         }

         public void click(int var1) {
            String var3 = (String)this.val$panelKeyPageUiSet.getList().get(var1);
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case 803183608:
                  if (var3.equals("_380_item_1")) {
                     var4 = 0;
                  }
                  break;
               case 803183609:
                  if (var3.equals("_380_item_2")) {
                     var4 = 1;
                  }
                  break;
               case 803183610:
                  if (var3.equals("_380_item_3")) {
                     var4 = 2;
                  }
                  break;
               case 803183611:
                  if (var3.equals("_380_item_4")) {
                     var4 = 3;
                  }
                  break;
               case 803183612:
                  if (var3.equals("_380_item_5")) {
                     var4 = 4;
                  }
            }

            switch (var4) {
               case 0:
                  this.this$0.sendMessage(1);
                  break;
               case 1:
                  this.this$0.sendMessage(2);
                  break;
               case 2:
                  this.this$0.sendMessage(3);
                  break;
               case 3:
                  this.this$0.sendMessage(4);
                  break;
               case 4:
                  this.this$0.sendMessage(5);
            }

         }
      });
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void sendMessage(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte)var1, 1});
   }

   public void sendMediaInfo0x91(int var1, byte[] var2) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -111, 0, 0}, var2));
   }
}
