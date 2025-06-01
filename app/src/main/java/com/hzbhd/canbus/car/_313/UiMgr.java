package com.hzbhd.canbus.car._313;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               this.this$0.sendAmplifierInfo0xC8(4, var2 + 10);
            }
         } else {
            this.this$0.sendAmplifierInfo0xC8(3, -var2 + 10);
         }

      }
   };
   OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               this.this$0.sendAmplifierInfo0xC8(1, var2);
            }
         } else {
            this.this$0.sendAmplifierInfo0xC8(2, var2);
         }

      }
   };
   OnPanelKeyPositionListener panelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAmplifierInfo0xC8(0, 1);
            }
         } else {
            this.this$0.sendAmplifierInfo0xC8(0, 0);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var2.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(this.panelKeyPositionListener);
   }

   private void sendAmplifierInfo0xC8(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var1, (byte)var2});
   }
}
