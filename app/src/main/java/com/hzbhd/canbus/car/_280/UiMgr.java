package com.hzbhd.canbus.car._280;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   static final String _280_AMPLIFIER_BALANCE = "_280_amplifier_balance";
   static final String _280_AMPLIFIER_BASS = "_280_amplifier_bass";
   static final String _280_AMPLIFIER_FADE = "_280_amplifier_fade";
   static final String _280_AMPLIFIER_MIDDLE = "_280_amplifier_middle";
   static final String _280_AMPLIFIER_TREBLE = "_280_amplifier_treble";
   private final int MSG_SEND_AIR_CMD_UP = 0;
   private final int VEHICLE_TYPE_AUTO_AC = 1;
   private final int VEHICLE_TYPE_MANUAL_AC = 2;
   private int data1;
   private int data2;
   private int data3;
   private int data4;
   private int data5;
   private Context mContext;
   private int mDifferent;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1.arg1, 0});
         }

      }
   };
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  this.this$0.data2 = var2 + 10;
                  SharePreUtil.setIntValue(this.val$context, "_280_amplifier_balance", this.this$0.data2);
                  this.this$0.msgMgr.initAmplifierData(this.val$context);
               }
            } else {
               this.this$0.data1 = var2 + 10;
               SharePreUtil.setIntValue(this.val$context, "_280_amplifier_fade", this.this$0.data1);
               this.this$0.msgMgr.initAmplifierData(this.val$context);
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)this.this$0.data1, (byte)this.this$0.data2, (byte)this.this$0.data3, (byte)this.this$0.data4, (byte)this.this$0.data5});
         }
      });
      var2.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var7 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            byte var3;
            byte var4;
            if (var7 != 1) {
               byte var5;
               if (var7 != 2) {
                  if (var7 == 3) {
                     var4 = (byte)this.this$0.data1;
                     var3 = (byte)this.this$0.data2;
                     var5 = (byte)this.this$0.data3;
                     byte var6 = (byte)this.this$0.data4;
                     var2 += 10;
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, var4, var3, var5, var6, (byte)var2});
                     this.this$0.data5 = var2;
                     SharePreUtil.setIntValue(this.val$context, "_280_amplifier_bass", this.this$0.data5);
                     this.this$0.msgMgr.initAmplifierData(this.val$context);
                  }
               } else {
                  var3 = (byte)this.this$0.data1;
                  var4 = (byte)this.this$0.data2;
                  var5 = (byte)this.this$0.data3;
                  var2 += 10;
                  CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, var3, var4, var5, (byte)var2, (byte)this.this$0.data5});
                  this.this$0.data4 = var2;
                  SharePreUtil.setIntValue(this.val$context, "_280_amplifier_middle", this.this$0.data4);
                  this.this$0.msgMgr.initAmplifierData(this.val$context);
               }
            } else {
               var3 = (byte)this.this$0.data1;
               var4 = (byte)this.this$0.data2;
               var2 += 10;
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, var3, var4, (byte)var2, (byte)this.this$0.data4, (byte)this.this$0.data5});
               this.this$0.data3 = var2;
               SharePreUtil.setIntValue(this.val$context, "_280_amplifier_treble", this.this$0.data3);
               this.this$0.msgMgr.initAmplifierData(this.val$context);
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(2);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(3);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(4);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(5);
         }
      }});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(9);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(10);
         }
      });
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var3), new UiMgr$$ExternalSyntheticLambda1(this, var3), new UiMgr$$ExternalSyntheticLambda2(this, var3), new UiMgr$$ExternalSyntheticLambda3(this, var3)});
      if (this.mDifferent == 2) {
         var3.getFrontArea().setWindMaxLevel(4);
         this.removeFrontAirButtonByName(var1, "dual");
         this.removeFrontAirButtonByName(var1, "blow_positive");
         this.removeFrontAirButtonByName(var1, "auto");
      }

   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 1});
      Message var2 = Message.obtain();
      var2.what = 0;
      var2.arg1 = var1;
      this.mHandler.sendMessageDelayed(var2, 100L);
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
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 1;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 2;
            }
            break;
         case -424438238:
            if (var1.equals("blow_negative")) {
               var2 = 3;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 4;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 5;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 6;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 7;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 8;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(19);
            break;
         case 1:
            this.sendAirCommand(20);
            break;
         case 2:
            this.sendAirCommand(7);
            break;
         case 3:
            this.sendAirCommand(8);
            break;
         case 4:
            this.sendAirCommand(23);
            break;
         case 5:
            this.sendAirCommand(21);
            break;
         case 6:
            this.sendAirCommand(16);
            break;
         case 7:
            this.sendAirCommand(1);
            break;
         case 8:
            this.sendAirCommand(25);
      }

   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__280_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__280_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__280_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__280_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }
}
