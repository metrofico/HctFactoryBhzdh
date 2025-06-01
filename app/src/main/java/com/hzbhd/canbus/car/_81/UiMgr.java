package com.hzbhd.canbus.car._81;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private AirPageUiSet mAirPageUiSet;
   private String[][] mDiagitalKeyboardActions;
   private String[][] mFeaturesKeyboardActions;
   private boolean mIsFeaturesKeyboard = true;
   private MsgMgr mMsgMgr;
   private MyPanoramicView mPanoramicView;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda0(var2));
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda1(this, var2, var1));
      AirPageUiSet var3 = this.getAirUiSet(var1);
      this.mAirPageUiSet = var3;
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda2(this), new UiMgr$$ExternalSyntheticLambda3(this), new UiMgr$$ExternalSyntheticLambda4(this), new UiMgr$$ExternalSyntheticLambda5(this)});
      this.mAirPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(27);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(26);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(29);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(28);
         }
      }});
      this.mAirPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(31);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(30);
         }
      });
      SyncPageUiSet var4 = this.getSyncPageUiSet(var1);
      var4.setOnSyncStateChangeListener(new OnSyncStateChangeListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onResume() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
         }

         public void onStop() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
         }
      });
      var4.setOnSyncItemClickListener(new OnSyncItemClickListener(this, var4, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SyncPageUiSet val$syncPageUiSet;

         {
            this.this$0 = var1;
            this.val$syncPageUiSet = var2;
            this.val$context = var3;
         }

         public void onKeyboardBtnClick(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case -1886439238:
                  if (var1.equals("number_0")) {
                     var2 = 0;
                  }
                  break;
               case -1886439237:
                  if (var1.equals("number_1")) {
                     var2 = 1;
                  }
                  break;
               case -1886439236:
                  if (var1.equals("number_2")) {
                     var2 = 2;
                  }
                  break;
               case -1886439235:
                  if (var1.equals("number_3")) {
                     var2 = 3;
                  }
                  break;
               case -1886439234:
                  if (var1.equals("number_4")) {
                     var2 = 4;
                  }
                  break;
               case -1886439233:
                  if (var1.equals("number_5")) {
                     var2 = 5;
                  }
                  break;
               case -1886439232:
                  if (var1.equals("number_6")) {
                     var2 = 6;
                  }
                  break;
               case -1886439231:
                  if (var1.equals("number_7")) {
                     var2 = 7;
                  }
                  break;
               case -1886439230:
                  if (var1.equals("number_8")) {
                     var2 = 8;
                  }
                  break;
               case -1886439229:
                  if (var1.equals("number_9")) {
                     var2 = 9;
                  }
                  break;
               case -1335157162:
                  if (var1.equals("device")) {
                     var2 = 10;
                  }
                  break;
               case -1224574323:
                  if (var1.equals("hangup")) {
                     var2 = 11;
                  }
                  break;
               case -988476804:
                  if (var1.equals("pickup")) {
                     var2 = 12;
                  }
                  break;
               case 3548:
                  if (var1.equals("ok")) {
                     var2 = 13;
                  }
                  break;
               case 3739:
                  if (var1.equals("up")) {
                     var2 = 14;
                  }
                  break;
               case 96964:
                  if (var1.equals("aux")) {
                     var2 = 15;
                  }
                  break;
               case 3089570:
                  if (var1.equals("down")) {
                     var2 = 16;
                  }
                  break;
               case 3237038:
                  if (var1.equals("info")) {
                     var2 = 17;
                  }
                  break;
               case 3317767:
                  if (var1.equals("left")) {
                     var2 = 18;
                  }
                  break;
               case 3347807:
                  if (var1.equals("menu")) {
                     var2 = 19;
                  }
                  break;
               case 3377907:
                  if (var1.equals("next")) {
                     var2 = 20;
                  }
                  break;
               case 3449395:
                  if (var1.equals("prev")) {
                     var2 = 21;
                  }
                  break;
               case 3540562:
                  if (var1.equals("star")) {
                     var2 = 22;
                  }
                  break;
               case 3645646:
                  if (var1.equals("well")) {
                     var2 = 23;
                  }
                  break;
               case 108511772:
                  if (var1.equals("right")) {
                     var2 = 24;
                  }
                  break;
               case 109418880:
                  if (var1.equals("shuff")) {
                     var2 = 25;
                  }
            }

            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 13});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 14});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 15});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 16});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 17});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 18});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 19});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 20});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 21});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 22});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 4});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 5});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 12});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 10});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 11});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 6});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 25});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 9});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 8});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 23});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 24});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 26});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 7});
            }

         }

         public void onKeyboardBtnLongClick(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case -1886439238:
                  if (var1.equals("number_0")) {
                     var2 = 0;
                  }
                  break;
               case -1886439237:
                  if (var1.equals("number_1")) {
                     var2 = 1;
                  }
                  break;
               case -1886439236:
                  if (var1.equals("number_2")) {
                     var2 = 2;
                  }
                  break;
               case -1886439235:
                  if (var1.equals("number_3")) {
                     var2 = 3;
                  }
                  break;
               case -1886439234:
                  if (var1.equals("number_4")) {
                     var2 = 4;
                  }
                  break;
               case -1886439233:
                  if (var1.equals("number_5")) {
                     var2 = 5;
                  }
                  break;
               case -1886439232:
                  if (var1.equals("number_6")) {
                     var2 = 6;
                  }
                  break;
               case -1886439231:
                  if (var1.equals("number_7")) {
                     var2 = 7;
                  }
                  break;
               case -1886439230:
                  if (var1.equals("number_8")) {
                     var2 = 8;
                  }
                  break;
               case -1886439229:
                  if (var1.equals("number_9")) {
                     var2 = 9;
                  }
            }

            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 48});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 49});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 50});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 51});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 52});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 53});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 54});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 55});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 56});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 57});
            }

         }

         public void onLeftIconClick(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case 103772132:
                  if (var1.equals("media")) {
                     var2 = 0;
                  }
                  break;
               case 106642798:
                  if (var1.equals("phone")) {
                     var2 = 1;
                  }
                  break;
               case 112386354:
                  if (var1.equals("voice")) {
                     var2 = 2;
                  }
                  break;
               case 503739367:
                  if (var1.equals("keyboard")) {
                     var2 = 3;
                  }
            }

            switch (var2) {
               case 0:
                  if (MsgMgr.SYNC_VERSION.VERSION_V1.equals(MsgMgr.mSyncVersion)) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -127});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                  }
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 1});
                  break;
               case 3:
                  if (this.this$0.mIsFeaturesKeyboard) {
                     this.this$0.mIsFeaturesKeyboard = false;
                     this.val$syncPageUiSet.setKeyboardActions(this.this$0.getDiagitalKeyboardActions());
                  } else {
                     this.this$0.mIsFeaturesKeyboard = true;
                     this.val$syncPageUiSet.setKeyboardActions(this.this$0.getFeaturesKeyboardActions());
                  }

                  this.this$0.getMsgMgr(this.val$context).updateSync((Bundle)null);
            }

         }

         public void onListItemClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)(var1 + 145)});
         }

         public void onSoftKeyClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)(var1 + 28)});
         }
      });
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda6());
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   // $FF: synthetic method
   static void lambda$new$0(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var3.hashCode();
      if (var3.equals("_81_tyre_monitor")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, 1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$6(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte)(var0 + 18)});
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)var1});
   }

   private void sendAirCommand(int var1, int var2) {
      String var3 = this.mAirPageUiSet.getFrontArea().getLineBtnAction()[var1][var2];
      var3.hashCode();
      var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -1423573049:
            if (var3.equals("ac_max")) {
               var4 = 0;
            }
            break;
         case -246396018:
            if (var3.equals("max_front")) {
               var4 = 1;
            }
            break;
         case 3106:
            if (var3.equals("ac")) {
               var4 = 2;
            }
            break;
         case 3005871:
            if (var3.equals("auto")) {
               var4 = 3;
            }
            break;
         case 3094652:
            if (var3.equals("dual")) {
               var4 = 4;
            }
            break;
         case 106858757:
            if (var3.equals("power")) {
               var4 = 5;
            }
            break;
         case 341572893:
            if (var3.equals("blow_window")) {
               var4 = 6;
            }
            break;
         case 756225563:
            if (var3.equals("in_out_cycle")) {
               var4 = 7;
            }
            break;
         case 1434490075:
            if (var3.equals("blow_foot")) {
               var4 = 8;
            }
            break;
         case 1434539597:
            if (var3.equals("blow_head")) {
               var4 = 9;
            }
      }

      switch (var4) {
         case 0:
            this.sendAirCommand(4);
            break;
         case 1:
            this.sendAirCommand(25);
            break;
         case 2:
            this.sendAirCommand(2);
            break;
         case 3:
            this.sendAirCommand(23);
            break;
         case 4:
            this.sendAirCommand(24);
            break;
         case 5:
            this.sendAirCommand(1);
            break;
         case 6:
            this.sendAirCommand(32);
            break;
         case 7:
            this.sendAirCommand(3);
            break;
         case 8:
            this.sendAirCommand(34);
            break;
         case 9:
            this.sendAirCommand(33);
      }

   }

   public View getCusPanoramicView(Context var1) {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = new MyPanoramicView(var1);
      }

      return this.mPanoramicView;
   }

   public String[][] getDiagitalKeyboardActions() {
      if (this.mDiagitalKeyboardActions == null) {
         String[] var2 = new String[]{"number_1", "number_2", "number_3"};
         String[] var1 = new String[]{"number_7", "number_8", "number_9"};
         this.mDiagitalKeyboardActions = new String[][]{var2, {"number_4", "number_5", "number_6"}, var1, {"star", "number_0", "well"}, {"pickup", "hangup", "null"}};
      }

      return this.mDiagitalKeyboardActions;
   }

   public String[][] getFeaturesKeyboardActions() {
      if (this.mFeaturesKeyboardActions == null) {
         this.mFeaturesKeyboardActions = new String[][]{{"null", "up", "null"}, {"left", "ok", "right"}, {"null", "down", "null"}, {"info", "menu", "device"}, {"prev", "shuff", "next"}};
      }

      return this.mFeaturesKeyboardActions;
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__81_UiMgr(SettingPageUiSet var1, Context var2, int var3, int var4, int var5) {
      int var6 = var5;
      String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      var8.hashCode();
      int var7 = var8.hashCode();
      byte var9 = -1;
      switch (var7) {
         case -2000321219:
            if (var8.equals("_81_hill_start_assist")) {
               var9 = 0;
            }
            break;
         case -1482315655:
            if (var8.equals("ford_alert_tone")) {
               var9 = 1;
            }
            break;
         case -1445075171:
            if (var8.equals("_81_current_voice_mode")) {
               var9 = 2;
            }
            break;
         case -1093208114:
            if (var8.equals("_81_turn_signals_setup")) {
               var9 = 3;
            }
            break;
         case -922538678:
            if (var8.equals("ford_range_unit")) {
               var9 = 4;
            }
            break;
         case -873936231:
            if (var8.equals("_279_temperature_unit")) {
               var9 = 5;
            }
            break;
         case -18891618:
            if (var8.equals("_81_rain_sensor")) {
               var9 = 6;
            }
            break;
         case 102584022:
            if (var8.equals("language_setup")) {
               var9 = 7;
            }
            break;
         case 648162385:
            if (var8.equals("brightness")) {
               var9 = 8;
            }
            break;
         case 1229285653:
            if (var8.equals("_81_traction_control_system")) {
               var9 = 9;
            }
            break;
         case 1567327775:
            if (var8.equals("parking_assistance")) {
               var9 = 10;
            }
            break;
         case 1708038769:
            if (var8.equals("_81_park_lock_ctrl")) {
               var9 = 11;
            }
            break;
         case 1715286958:
            if (var8.equals("ford_message_tone")) {
               var9 = 12;
            }
            break;
         case 1959314388:
            if (var8.equals("_81_interior_lighting")) {
               var9 = 13;
            }
            break;
         case 1989254414:
            if (var8.equals("_176_car_setting_title_21")) {
               var9 = 14;
            }
      }

      switch (var9) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte)var6});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var6 + 7)});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var6 + 9)});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var6 + 3)});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var6 + 14)});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte)var6});
            this.getMsgMgr(var2).updateSettings(var3, var4, var6);
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte)var6});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte)MsgMgr.mLanguageMapArray[var6]});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var6 + 16)});
            break;
         case 9:
            var3 = var6;
            if (var6 == 0) {
               var3 = 2;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)var3});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte)var6});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte)var6});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var6 + 5)});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte)var6});
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte)var6});
      }

   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__81_UiMgr(int var1) {
      this.sendAirCommand(0, var1);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__81_UiMgr(int var1) {
      this.sendAirCommand(1, var1);
   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__81_UiMgr(int var1) {
      this.sendAirCommand(2, var1);
   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__81_UiMgr(int var1) {
      this.sendAirCommand(3, var1);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
