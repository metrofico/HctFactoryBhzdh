package com.hzbhd.canbus.car._221;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(14);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(13);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var3), new UiMgr$$ExternalSyntheticLambda1(this, var3), new UiMgr$$ExternalSyntheticLambda2(this, var3), new UiMgr$$ExternalSyntheticLambda3(this, var3)});
      FrontArea var2 = var3.getFrontArea();
      OnAirTemperatureUpDownClickListener var4 = this.mOnUpDownClickListener;
      var2.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{var4, null, var4});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(12);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(11);
         }
      });
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 1});
            }

            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 2});
            }

            if (var1 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 3});
            }

            if (var1 == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
            }

         }
      });
      PanelKeyPageUiSet var5 = this.getPanelKeyPageUiSet(var1);
      var5.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener(this, var5) {
         final UiMgr this$0;
         final PanelKeyPageUiSet val$panelKeyPageUiSet;

         {
            this.this$0 = var1;
            this.val$panelKeyPageUiSet = var2;
         }

         public void click(int var1) {
            String var2 = (String)this.val$panelKeyPageUiSet.getList().get(var1);
            var2.hashCode();
            if (var2.equals("_221_panel_btn_rear_camera_open")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
            }

         }
      });
   }

   private void sendAirCommand(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 1;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 2;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 3;
            }
            break;
         case 1018451744:
            if (var1.equals("blow_head_foot")) {
               var2 = 4;
            }
            break;
         case 1434490075:
            if (var1.equals("blow_foot")) {
               var2 = 5;
            }
            break;
         case 1434539597:
            if (var1.equals("blow_head")) {
               var2 = 6;
            }
            break;
         case 1568764496:
            if (var1.equals("blow_window_foot")) {
               var2 = 7;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(5);
            break;
         case 1:
            this.sendAirCommand(2);
            break;
         case 2:
            this.sendAirCommand(1);
            break;
         case 3:
            this.sendAirCommand(7);
            break;
         case 4:
            this.sendAirCommand(27);
            break;
         case 5:
            this.sendAirCommand(29);
            break;
         case 6:
            this.sendAirCommand(26);
            break;
         case 7:
            this.sendAirCommand(28);
      }

   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__221_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__221_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__221_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__221_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }
}
