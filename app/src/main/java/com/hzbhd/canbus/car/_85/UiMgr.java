package com.hzbhd.canbus.car._85;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import java.util.ArrayList;
import java.util.Arrays;

public class UiMgr extends AbstractUiMgr {
   private final int INVAIL_VALUE = -1;
   private final String TAG = "_85_UiMgr";
   private final int VEHICLE_TYPE_RANGER_F150 = 16;
   private int[] mAirAutoBtnPos;
   private int[] mAirDualBtnPos;
   private String[][] mDiagitalKeyboardActions;
   private int mDifferent = this.getCurrentCarId();
   private String[][] mFeaturesKeyboardActions;
   private boolean mIsFeaturesKeyboard = true;
   private MsgMgr mMsgMgr;
   private OnAirAutoManualChangeListener mOnAirAutoManualChangeListener;
   private MyPanoramicView mPanoramicView;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda0(var2));
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda5(this, var2, var1));
      var2.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda6(var2));
      AirPageUiSet var4 = this.getAirUiSet(var1);
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda7(this, var4), new UiMgr$$ExternalSyntheticLambda8(this, var4), new UiMgr$$ExternalSyntheticLambda9(this, var4), new UiMgr$$ExternalSyntheticLambda10(this, var4)});
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(35);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(36);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            if (GeneralAirData.auto_manual) {
               this.this$0.sendAirCommand(37);
            } else {
               this.this$0.sendAirCommand(35);
            }

         }

         public void onClickUp() {
            if (GeneralAirData.auto_manual) {
               this.this$0.sendAirCommand(38);
            } else {
               this.this$0.sendAirCommand(36);
            }

         }
      }});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(39);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(40);
         }
      });
      var4.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(43);
         }
      }, new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(45);
         }
      }, new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(44);
         }
      }, new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(46);
         }
      }});
      var4.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new UiMgr$$ExternalSyntheticLambda11(this, var4)});
      var4.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(19);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(20);
         }
      }, null});
      var4.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(21);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(22);
         }
      });
      this.mAirAutoBtnPos = this.getAirBtnPosition(var1, "auto");
      this.mAirDualBtnPos = this.getAirBtnPosition(var1, "dual");
      Log.i("_85_UiMgr", "UiMgr: mAirAutoBtnPos:[" + this.mAirAutoBtnPos[0] + "," + this.mAirAutoBtnPos[1] + "], mAirDualBtnPos:[" + this.mAirDualBtnPos[0] + "," + this.mAirDualBtnPos[1] + "]");
      this.mOnAirAutoManualChangeListener = new UiMgr$$ExternalSyntheticLambda1(this, var4, var1);
      SyncPageUiSet var5 = this.getSyncPageUiSet(var1);
      var5.setOnSyncStateChangeListener(new OnSyncStateChangeListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onResume() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 14, 0});
         }

         public void onStop() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
         }
      });
      var5.setOnSyncItemClickListener(new OnSyncItemClickListener(this, var5, var1) {
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
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda2());
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda3());
      var3.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda4());
   }

   private void addAirBtn(Context var1, int var2, int var3, String var4) {
      if (var2 != -1 && var3 != -1) {
         String[][] var10 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();
         int var7 = var10.length;

         for(int var5 = 0; var5 < var7; ++var5) {
            String[] var9 = var10[var5];
            int var8 = var9.length;

            for(int var6 = 0; var6 < var8; ++var6) {
               if (var9[var6].equals(var4)) {
                  return;
               }
            }
         }

         ArrayList var11 = new ArrayList(Arrays.asList(var10[var2]));
         var11.add(var3, var4);
         var10[var2] = (String[])var11.toArray(new String[0]);
      }

   }

   private int[] getAirBtnPosition(Context var1, String var2) {
      int[] var5 = new int[]{-1, -1};
      String[][] var7 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();

      for(int var3 = 0; var3 < var7.length; ++var3) {
         String[] var6 = var7[var3];

         for(int var4 = 0; var4 < var6.length; ++var4) {
            if (var6[var4].equals(var2)) {
               var5[0] = var3;
               var5[1] = var4;
            }
         }
      }

      return var5;
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
   static void lambda$new$10(AmplifierActivity.AmplifierBand var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var0.ordinal()];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -62, (byte)var1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte)var1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, (byte)var1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$11(AmplifierActivity.AmplifierPosition var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var0.ordinal()];
      if (var2 != 1) {
         if (var2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -60, (byte)(var1 + 7)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -61, (byte)(var1 + 7)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$2(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var3.hashCode();
      var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -1993091142:
            if (var3.equals("_85_driver_seat_adjust_middle_plus")) {
               var4 = 0;
            }
            break;
         case -1898218603:
            if (var3.equals("_85_passenger_seat_adjust_upper_plus")) {
               var4 = 1;
            }
            break;
         case -1659149744:
            if (var3.equals("_85_driver_seat_adjust_middle_minus")) {
               var4 = 2;
            }
            break;
         case -1618234812:
            if (var3.equals("_85_passenger_seat_adjust_middle_minus")) {
               var4 = 3;
            }
            break;
         case -1603289439:
            if (var3.equals("_85_driver_seat_adjust_upper_plus")) {
               var4 = 4;
            }
            break;
         case 703826164:
            if (var3.equals("_85_passenger_seat_adjust_lower_minus")) {
               var4 = 5;
            }
            break;
         case 992627862:
            if (var3.equals("_85_passenger_seat_adjust_lower_plus")) {
               var4 = 6;
            }
            break;
         case 1256695656:
            if (var3.equals("_85_driver_seat_adjust_lower_minus")) {
               var4 = 7;
            }
            break;
         case 1281898965:
            if (var3.equals("_85_passenger_seat_adjust_upper_minus")) {
               var4 = 8;
            }
            break;
         case 1287557026:
            if (var3.equals("_85_driver_seat_adjust_lower_plus")) {
               var4 = 9;
            }
            break;
         case 1749006662:
            if (var3.equals("_85_passenger_seat_adjust_middle_plus")) {
               var4 = 10;
            }
            break;
         case 1834768457:
            if (var3.equals("_85_driver_seat_adjust_upper_minus")) {
               var4 = 11;
            }
      }

      switch (var4) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, 2});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 2});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, 1});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 1});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, 2});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 1});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 2});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, 1});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 1});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, 2});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 2});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, 1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$9(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var0 + 18)});
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)var1});
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1786872896:
            if (var1.equals("steering_wheel_heating")) {
               var2 = 0;
            }
            break;
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 1;
            }
            break;
         case -1423573049:
            if (var1.equals("ac_max")) {
               var2 = 2;
            }
            break;
         case -712865050:
            if (var1.equals("rear_lock")) {
               var2 = 3;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 4;
            }
            break;
         case -620266838:
            if (var1.equals("rear_power")) {
               var2 = 5;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 6;
            }
            break;
         case -246396018:
            if (var1.equals("max_front")) {
               var2 = 7;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 8;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 9;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 10;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 11;
            }
            break;
         case 341572893:
            if (var1.equals("blow_window")) {
               var2 = 12;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 13;
            }
            break;
         case 1434490075:
            if (var1.equals("blow_foot")) {
               var2 = 14;
            }
            break;
         case 1434539597:
            if (var1.equals("blow_head")) {
               var2 = 15;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(42);
            break;
         case 1:
            this.sendAirCommand(41);
            break;
         case 2:
            this.sendAirCommand(4);
            break;
         case 3:
            this.sendAirCommand(18);
            break;
         case 4:
            this.sendAirCommand(6);
            break;
         case 5:
            this.sendAirCommand(17);
            break;
         case 6:
            this.sendAirCommand(7);
            break;
         case 7:
            this.sendAirCommand(5);
            break;
         case 8:
            this.sendAirCommand(2);
            break;
         case 9:
            this.sendAirCommand(23);
            break;
         case 10:
            this.sendAirCommand(24);
            break;
         case 11:
            this.sendAirCommand(1);
            break;
         case 12:
            this.sendAirCommand(32);
            break;
         case 13:
            this.sendAirCommand(3);
            break;
         case 14:
            this.sendAirCommand(34);
            break;
         case 15:
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
         String[] var1 = new String[]{"number_7", "number_8", "number_9"};
         this.mDiagitalKeyboardActions = new String[][]{{"number_1", "number_2", "number_3"}, {"number_4", "number_5", "number_6"}, var1, {"star", "number_0", "well"}, {"pickup", "hangup", "null"}};
      }

      return this.mDiagitalKeyboardActions;
   }

   public String[][] getFeaturesKeyboardActions() {
      if (this.mFeaturesKeyboardActions == null) {
         String[] var1 = new String[]{"left", "ok", "right"};
         this.mFeaturesKeyboardActions = new String[][]{{"null", "up", "null"}, var1, {"null", "down", "null"}, {"info", "menu", "device"}, {"prev", "shuff", "next"}};
      }

      return this.mFeaturesKeyboardActions;
   }

   OnAirAutoManualChangeListener getOnAirAutoManualChangeListener() {
      return this.mOnAirAutoManualChangeListener;
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__85_UiMgr(SettingPageUiSet var1, Context var2, int var3, int var4, int var5) {
      String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      var8.hashCode();
      int var7 = var8.hashCode();
      byte var6 = -1;
      switch (var7) {
         case -1555273247:
            if (var8.equals("_85_passenger_seat_massage_backrest")) {
               var6 = 0;
            }
            break;
         case -1545759403:
            if (var8.equals("_85_driver_seat_massage_backrest")) {
               var6 = 1;
            }
            break;
         case -1484591865:
            if (var8.equals("_85_driver_seat_massage_cushion")) {
               var6 = 2;
            }
            break;
         case -1037022378:
            if (var8.equals("_85_sound_mode")) {
               var6 = 3;
            }
            break;
         case -922538678:
            if (var8.equals("ford_range_unit")) {
               var6 = 4;
            }
            break;
         case -903809344:
            if (var8.equals("speed_linkage_volume_adjustment")) {
               var6 = 5;
            }
            break;
         case -873936231:
            if (var8.equals("_279_temperature_unit")) {
               var6 = 6;
            }
            break;
         case 102584022:
            if (var8.equals("language_setup")) {
               var6 = 7;
            }
            break;
         case 870405883:
            if (var8.equals("_85_passenger_seat_massage_cushion")) {
               var6 = 8;
            }
            break;
         case 1989254414:
            if (var8.equals("_176_car_setting_title_21")) {
               var6 = 9;
            }
      }

      switch (var6) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte)var5});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, (byte)var5});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, (byte)var5});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -58, (byte)(var5 + 1)});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var5 + 14)});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -59, (byte)(var5 + 1)});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte)var5});
            this.getMsgMgr(var2).updateSettings(var3, var4, var5);
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte)MsgMgr.mLanguageMapArray[var5]});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, (byte)var5});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte)var5});
      }

   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__85_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__85_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__85_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$6$com_hzbhd_canbus_car__85_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$7$com_hzbhd_canbus_car__85_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getRearArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$8$com_hzbhd_canbus_car__85_UiMgr(AirPageUiSet var1, Context var2, boolean var3) {
      Log.i("_85_UiMgr", "UiMgr: isAutoAir:" + var3);
      var1.getFrontArea().setShowSeatHeat(var3);
      var1.getFrontArea().setShowSeatCold(var3);
      if (var3) {
         int[] var4 = this.mAirAutoBtnPos;
         this.addAirBtn(var2, var4[0], var4[1], "auto");
         var4 = this.mAirDualBtnPos;
         this.addAirBtn(var2, var4[0], var4[1], "dual");
      } else {
         this.removeFrontAirButtonByName(var2, "auto");
         this.removeFrontAirButtonByName(var2, "dual");
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }

   interface OnAirAutoManualChangeListener {
      void onChange(boolean var1);
   }
}
