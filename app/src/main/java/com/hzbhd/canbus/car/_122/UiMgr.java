package com.hzbhd.canbus.car._122;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierBand.VOLUME) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var2});
            } else if (var1 == AmplifierActivity.AmplifierBand.BASS) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(var2 + 1)});
            } else if (var1 == AmplifierActivity.AmplifierBand.MIDDLE) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(var2 + 1)});
            } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(var2 + 1)});
            }

         }
      });
      var2.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 1 + 9)});
            } else if (var1 == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var2 + 1 + 9)});
            }

         }
      });
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
