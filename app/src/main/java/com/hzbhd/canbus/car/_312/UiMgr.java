package com.hzbhd.canbus.car._312;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;

public class UiMgr extends AbstractUiMgr {
   private final String TAG = "_306_UiMgr";
   protected AmplifierParams mAmplifierParams;
   private int mDifferent = this.getCurrentCarId();
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0(var2));
      var2.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda1(var2));
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda2(this));
      var3.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda3(this));
      this.initAmplifierData(var3);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initAmplifierData(AmplifierPageUiSet var1) {
      if (DataHandleUtils.getIntFromByteWithBit(this.mDifferent, 0, 4) == 1) {
         this.mAmplifierParams = new AmplifierParams(63, new AmplifierParams.ItemParams(3, 10, 17), new AmplifierParams.ItemParams(5, 10, 15));
      } else {
         this.mAmplifierParams = new AmplifierParams(38, new AmplifierParams.ItemParams(1, 10, 19), new AmplifierParams.ItemParams(1, 10, 19));
      }

      var1.setSeekBarMax(this.mAmplifierParams.getSeekBarMax());
      var1.setBandRange(this.mAmplifierParams.getBandRange());
      var1.setBalanceRange(this.mAmplifierParams.getBalanceRange());
      var1.setSeekBarVolumeMax(this.mAmplifierParams.getVolumeMax());
   }

   // $FF: synthetic method
   static void lambda$new$0(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var4.hashCode();
      var2 = var4.hashCode();
      byte var5 = -1;
      switch (var2) {
         case -2015109519:
            if (var4.equals("_312_interior_light")) {
               var5 = 0;
            }
            break;
         case -1151002481:
            if (var4.equals("_312_auto_door_unlock")) {
               var5 = 1;
            }
            break;
         case 97333442:
            if (var4.equals("amplifier_switch")) {
               var5 = 2;
            }
            break;
         case 478007159:
            if (var4.equals("_312_hazard_warning_flasher")) {
               var5 = 3;
            }
            break;
         case 618726802:
            if (var4.equals("_312_one_touch_lane_changer")) {
               var5 = 4;
            }
            break;
         case 779337659:
            if (var4.equals("_41_park_assist")) {
               var5 = 5;
            }
            break;
         case 952478912:
            if (var4.equals("_312_security_re_locking")) {
               var5 = 6;
            }
            break;
         case 1236522294:
            if (var4.equals("_312_auto_door_lock")) {
               var5 = 7;
            }
            break;
         case 1351425770:
            if (var4.equals("_312_wheel_control_type")) {
               var5 = 8;
            }
            break;
         case 1828219305:
            if (var4.equals("_312_rab")) {
               var5 = 9;
            }
            break;
         case 1840031169:
            if (var4.equals("_312_defogger")) {
               var5 = 10;
            }
      }

      switch (var5) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)var3});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
      }

   }

   // $FF: synthetic method
   static void lambda$new$1(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var3.hashCode();
      if (var3.equals("vm_golf7_vehicle_setup_factory_setup")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, -86});
      }

   }

   int getBalanceMid() {
      return this.mAmplifierParams.getBalance().mid;
   }

   int getBandMin() {
      return this.mAmplifierParams.getBand().min;
   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__312_UiMgr(AmplifierActivity.AmplifierPosition var1, int var2) {
      int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
      if (var3 != 1) {
         if (var3 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)this.mAmplifierParams.sendBalance(var2)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)this.mAmplifierParams.sendBalance(var2)});
      }

   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__312_UiMgr(AmplifierActivity.AmplifierBand var1, int var2) {
      int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)this.mAmplifierParams.sendBand(var2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)this.mAmplifierParams.sendBand(var2)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)this.mAmplifierParams.sendBand(var2)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)var2});
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }

   protected static class AmplifierParams {
      private final ItemParams balance;
      private final ItemParams band;
      private final int volumeMax;

      public AmplifierParams(int var1, ItemParams var2, ItemParams var3) {
         this.volumeMax = var1;
         this.balance = var2;
         this.band = var3;
      }

      public ItemParams getBalance() {
         return this.balance;
      }

      public int getBalanceRange() {
         return this.balance.max - this.balance.min;
      }

      public ItemParams getBand() {
         return this.band;
      }

      public int getBandRange() {
         return this.band.mid - this.band.min;
      }

      public int getSeekBarMax() {
         return this.band.max - this.band.min;
      }

      public int getVolumeMax() {
         return this.volumeMax;
      }

      public int sendBalance(int var1) {
         return var1 + this.balance.mid;
      }

      public int sendBand(int var1) {
         return var1 + this.band.min;
      }

      private static class ItemParams {
         private final int max;
         private final int mid;
         private final int min;

         public ItemParams(int var1, int var2, int var3) {
            this.min = var1;
            this.mid = var2;
            this.max = var3;
         }
      }
   }
}
