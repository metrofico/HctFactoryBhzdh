package com.hzbhd.canbus.car._111;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import java.util.Objects;

public class UiMgr extends AbstractUiMgr {
   public UiMgr(Context var1) {
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda0());
      var2.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda1());
      this.getSettingUiSet(var1).setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda2(var1));
   }

   // $FF: synthetic method
   static void lambda$new$0(AmplifierActivity.AmplifierBand var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var0.ordinal()];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)var1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)(var1 + 1)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var1 + 1)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var1 + 1)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$1(AmplifierActivity.AmplifierPosition var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var0.ordinal()];
      if (var2 != 1) {
         if (var2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var1 + 10)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)(10 - var1)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$2(Context var0, int var1, int var2, int var3) {
      if (var1 == 0) {
         ((MsgMgr)Objects.requireNonNull(MsgMgrFactory.getCanMsgMgr(var0))).updateAmplifierSwitch(var3);
      }

   }
}
