package com.hzbhd.canbus.car._28;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   static final String _28_AMPLIFIER_MUTE = "_28_amplifier_mute";
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.getMsgMgr(var1).updateSetting(0, 0, SharePreUtil.getIntValue(var1, "_28_amplifier_mute", 0));
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_197_amplifier_mute")) {
               SharePreUtil.setIntValue(this.val$context, "_28_amplifier_mute", var3);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte)var3});
               this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
            }

         }
      });
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda0());
      var3.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda1());
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   // $FF: synthetic method
   static void lambda$new$0(AmplifierActivity.AmplifierPosition var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var0.ordinal()];
      if (var2 != 1) {
         if (var2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var1 + 6 + 10)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var1 + 6 + 10)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$1(AmplifierActivity.AmplifierBand var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var0.ordinal()];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(var1 + 6)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(var1 + 6)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(var1 + 6)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var1});
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
