package com.hzbhd.canbus.car._165;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Objects;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_165_LANGUAGE = "share_165_language";
   private final int MSG_SEND_AIR_COMMAND_UP = 0;
   private final String TAG = "_165_UiMgr";
   private BtnGroup mBtnGroup;
   private int mDifferent = this.getCurrentCarId();
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
   private MsgMgr mMsgMgr;
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(12);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(14);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(11);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
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
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
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
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(38);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(39);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.mDifferent == 33) {
            this.this$0.sendAirCommand(65);
         } else {
            this.this$0.mOnUpDownClickListenerRearLeft.onClickDown();
         }

      }

      public void onClickUp() {
         if (this.this$0.mDifferent == 33) {
            this.this$0.sendAirCommand(66);
         } else {
            this.this$0.mOnUpDownClickListenerRearLeft.onClickUp();
         }

      }
   };
   private TimerUtil mTimerUtil;
   private MyPanoramicView myPanoramicView;

   public UiMgr(Context var1) {
      this.initPanoramicBtnGroup();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setShowSeatHeat(false);
      var2.getFrontArea().setShowSeatCold(this.isSupportSeatCold());
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var2), new UiMgr$$ExternalSyntheticLambda11(this, var2), new UiMgr$$ExternalSyntheticLambda12(this, var2), new UiMgr$$ExternalSyntheticLambda13(this, var2)});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
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
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new UiMgr$$ExternalSyntheticLambda14(this, var2)});
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerRearLeft, null, this.mOnUpDownClickListenerRearRight});
      var2.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(40);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(41);
         }
      });
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda1(this, var5, var1));
      var5.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda2(var5));
      var5.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda3(var5));
      AmplifierPageUiSet var6 = this.getAmplifierPageUiSet(var1);
      var6.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda4());
      var6.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda5());
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               (new Thread(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void run() {
                     super.run();

                     try {
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
                        sleep(100L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
                        sleep(100L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 35, 0});
                        sleep(100L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 39, 0});
                     } catch (Exception var2) {
                        var2.printStackTrace();
                     }

                  }
               }).start();
            }

         }
      });
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      var3.setShowCusPanoramicView(this.isSupportPanoramic());
      var3.setOnBackCameraStatusListener(new UiMgr$$ExternalSyntheticLambda6(this, var1));
      var3.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda7());
      if (this.mDifferent == 49) {
         DisplayMetrics var7 = new DisplayMetrics();
         ((WindowManager)Objects.requireNonNull(var1.getSystemService("window"))).getDefaultDisplay().getRealMetrics(var7);
         var3.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda8(this, var7, var1));
      }

      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(var1);
      var4.setOnOriginalTopBtnClickListeners(new UiMgr$$ExternalSyntheticLambda9(var4));
      var4.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda10(var4));
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   private void initPanoramicBtnGroup() {
      int var1 = this.mDifferent;
      if ((var1 & 240) == 32) {
         this.mBtnGroup = new BtnGroup(this, new OnPanoramicBtnCllick(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onStartBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
            }

            public void onStartTopClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
            }
         }, new OnPanoramicBtnCllick(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onEndBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
            }

            public void onEndTopClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 6});
            }

            public void onStartBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
            }
         });
      } else if (var1 == 1) {
         this.mBtnGroup = new BtnGroup(this, new OnPanoramicBtnCllick(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onMidBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
            }

            public void onStartBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
            }
         }, new OnPanoramicBtnCllick(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onEndBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
            }

            public void onMidBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 7});
            }

            public void onStartBottomClick() {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
            }
         });
      }

   }

   private boolean isSupportPanoramic() {
      int var1 = this.mDifferent;
      if ((var1 & 240) == 32) {
         return true;
      } else {
         return var1 == 1;
      }
   }

   private boolean isSupportSeatCold() {
      return this.mDifferent == 19;
   }

   // $FF: synthetic method
   static void lambda$new$11(int var0) {
      if (var0 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, 0});
      }

   }

   // $FF: synthetic method
   static void lambda$new$13(OriginalCarDevicePageUiSet var0, int var1) {
      String var2 = var0.getRowTopBtnAction()[var1];
      var2.hashCode();
      if (!var2.equals("lock")) {
         if (var2.equals("power")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 96, (byte)(GeneralOriginalCarDeviceData.power ^ 1)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 97, (byte)(GeneralOriginalCarDeviceData.lock ^ 1)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$14(OriginalCarDevicePageUiSet var0, int var1) {
      String var3 = var0.getRowBottomBtnAction()[var1];
      var3.hashCode();
      int var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -39978415:
            if (var3.equals("auto_store")) {
               var4 = 0;
            }
            break;
         case 3443508:
            if (var3.equals("play")) {
               var4 = 1;
            }
            break;
         case 3540994:
            if (var3.equals("stop")) {
               var4 = 2;
            }
      }

      switch (var4) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 98, 1});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 100, 1});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 99, 1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$6(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var3.hashCode();
      var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -1871457942:
            if (var3.equals("_165_clear_data")) {
               var4 = 0;
            }
            break;
         case -733291940:
            if (var3.equals("clear_data")) {
               var4 = 1;
            }
            break;
         case -573930144:
            if (var3.equals("update_data")) {
               var4 = 2;
            }
            break;
         case 63:
            if (var3.equals("?")) {
               var4 = 3;
            }
      }

      switch (var4) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, 0});
      }

   }

   // $FF: synthetic method
   static void lambda$new$7(SettingPageUiSet var0, int var1, int var2, int var3) {
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var4.hashCode();
      if (!var4.equals("hiworld_jeep_123_0x60_data1_65")) {
         if (var4.equals("radar_volume")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var3});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
      }

   }

   // $FF: synthetic method
   static void lambda$new$8(AmplifierActivity.AmplifierBand var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var0.ordinal()];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var1 + 2)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var1 + 2)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var1 + 2)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$9(AmplifierActivity.AmplifierPosition var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var0.ordinal()];
      if (var2 != 1) {
         if (var2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var1 + 7)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var1 + 7)});
      }

   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 1});
      Message var2 = new Message();
      var2.what = 0;
      var2.arg1 = var1;
      this.mHandler.sendMessageDelayed(var2, 100L);
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1761278488:
            if (var1.equals("pollrn_removal")) {
               var2 = 0;
            }
            break;
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 1;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 2;
            }
            break;
         case -620266838:
            if (var1.equals("rear_power")) {
               var2 = 3;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 4;
            }
            break;
         case -424438238:
            if (var1.equals("blow_negative")) {
               var2 = 5;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 6;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 7;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 8;
            }
            break;
         case 3496356:
            if (var1.equals("rear")) {
               var2 = 9;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 10;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 11;
            }
            break;
         case 1006620906:
            if (var1.equals("auto_wind_mode")) {
               var2 = 12;
            }
            break;
         case 1568764496:
            if (var1.equals("blow_window_foot")) {
               var2 = 13;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(33);
            break;
         case 1:
            this.sendAirCommand(19);
            break;
         case 2:
            this.sendAirCommand(20);
            break;
         case 3:
            this.sendAirCommand(44);
            break;
         case 4:
            if (this.mDifferent == 33) {
               this.sendAirCommand(67);
            } else {
               this.sendAirCommand(8);
            }
            break;
         case 5:
            if (this.mDifferent == 33) {
               this.sendAirCommand(69);
            } else {
               this.sendAirCommand(7);
            }
            break;
         case 6:
            this.sendAirCommand(23);
            break;
         case 7:
            this.sendAirCommand(21);
            break;
         case 8:
            this.sendAirCommand(16);
            break;
         case 9:
            this.sendAirCommand(42);
            break;
         case 10:
            this.sendAirCommand(1);
            break;
         case 11:
            this.sendAirCommand(25);
            break;
         case 12:
            this.sendAirCommand(32);
            break;
         case 13:
            this.sendAirCommand(68);
      }

   }

   public View getCusPanoramicView(Context var1) {
      if (this.myPanoramicView == null) {
         this.myPanoramicView = new MyPanoramicView(var1);
      }

      return this.myPanoramicView;
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__165_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__165_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$10$com_hzbhd_canbus_car__165_UiMgr(Context var1) {
      if (this.mBtnGroup != null) {
         ((MyPanoramicView)this.getCusPanoramicView(var1)).setOnBtnClickListener(this.mBtnGroup.getBtnClick(var1));
      }

   }

   // $FF: synthetic method
   void lambda$new$12$com_hzbhd_canbus_car__165_UiMgr(DisplayMetrics var1, Context var2, MotionEvent var3) {
      boolean var10 = CommUtil.isMiscReverse();
      if ((var3.getAction() == 0 || var3.getAction() == 1) && var10) {
         byte var7;
         if (var3.getAction() == 0) {
            var7 = 1;
         } else {
            var7 = 0;
         }

         int var8 = var1.widthPixels;
         int var9 = CommUtil.getDimenByResId(var2, "video_height");
         var8 = (int)(var3.getX() / (float)var8 * 255.0F);
         var9 = (int)(var3.getY() / (float)var9 * 255.0F);
         byte var6 = (byte)var8;
         byte var4 = (byte)var9;
         byte var5 = (byte)var7;
         this.getTimerUtil().stopTimer();
         this.getTimerUtil().startTimer(new TimerTask(this, new byte[]{22, -123, var6, var4, var5}) {
            final UiMgr this$0;
            final byte[] val$bytes;

            {
               this.this$0 = var1;
               this.val$bytes = var2;
            }

            public void run() {
               if (CommUtil.isMiscReverse()) {
                  CanbusMsgSender.sendMsg(this.val$bytes);
               } else {
                  this.this$0.getTimerUtil().stopTimer();
               }

            }
         }, 0L, 1000L);
      }

   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__165_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__165_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__165_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getRearArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__165_UiMgr(SettingPageUiSet var1, Context var2, int var3, int var4, int var5) {
      String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      var8.hashCode();
      int var7 = var8.hashCode();
      byte var6 = -1;
      switch (var7) {
         case -2116235428:
            if (var8.equals("_55_0x65_data1_bit21")) {
               var6 = 0;
            }
            break;
         case -1595525509:
            if (var8.equals("_283_rear_radar_alert_distance")) {
               var6 = 1;
            }
            break;
         case -1069474199:
            if (var8.equals("_165_sterring_column")) {
               var6 = 2;
            }
            break;
         case -902681199:
            if (var8.equals("_18_vehicle_setting_item_1_0")) {
               var6 = 3;
            }
            break;
         case -902681198:
            if (var8.equals("_18_vehicle_setting_item_1_1")) {
               var6 = 4;
            }
            break;
         case -902681197:
            if (var8.equals("_18_vehicle_setting_item_1_2")) {
               var6 = 5;
            }
            break;
         case -902681196:
            if (var8.equals("_18_vehicle_setting_item_1_3")) {
               var6 = 6;
            }
            break;
         case -902681195:
            if (var8.equals("_18_vehicle_setting_item_1_4")) {
               var6 = 7;
            }
            break;
         case -902681194:
            if (var8.equals("_18_vehicle_setting_item_1_5")) {
               var6 = 8;
            }
            break;
         case -902680234:
            if (var8.equals("_18_vehicle_setting_item_2_4")) {
               var6 = 9;
            }
            break;
         case -902680233:
            if (var8.equals("_18_vehicle_setting_item_2_5")) {
               var6 = 10;
            }
            break;
         case -902680232:
            if (var8.equals("_18_vehicle_setting_item_2_6")) {
               var6 = 11;
            }
            break;
         case -902680231:
            if (var8.equals("_18_vehicle_setting_item_2_7")) {
               var6 = 12;
            }
            break;
         case -829800413:
            if (var8.equals("_165_ev_mode")) {
               var6 = 13;
            }
            break;
         case -723657951:
            if (var8.equals("_11_0x26_data2_bit20")) {
               var6 = 14;
            }
            break;
         case 97333442:
            if (var8.equals("amplifier_switch")) {
               var6 = 15;
            }
            break;
         case 102584022:
            if (var8.equals("language_setup")) {
               var6 = 16;
            }
            break;
         case 108612084:
            if (var8.equals("_18_vehicle_setting_item_3_210")) {
               var6 = 17;
            }
            break;
         case 163845763:
            if (var8.equals("_11_0x26_data3_bit32")) {
               var6 = 18;
            }
            break;
         case 257294315:
            if (var8.equals("_186_asl")) {
               var6 = 19;
            }
            break;
         case 423136024:
            if (var8.equals("_283_front_radar_alert_distance")) {
               var6 = 20;
            }
            break;
         case 808378399:
            if (var8.equals("_165_eco_mode")) {
               var6 = 21;
            }
            break;
         case 957932200:
            if (var8.equals("light_ctrl_3")) {
               var6 = 22;
            }
            break;
         case 1051349540:
            if (var8.equals("_11_0x26_data4_bit65")) {
               var6 = 23;
            }
            break;
         case 1475566907:
            if (var8.equals("_55_0x65_data1_bit54_item1")) {
               var6 = 24;
            }
            break;
         case 1591925886:
            if (var8.equals("_55_0x67_data1_bit32")) {
               var6 = 25;
            }
            break;
         case 1888217751:
            if (var8.equals("_18_surround")) {
               var6 = 26;
            }
            break;
         case 2081713660:
            if (var8.equals("_18_vehicle_setting_item_3_43")) {
               var6 = 27;
            }
      }

      switch (var6) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var5});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var5});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)var5});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var5});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var5});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var5});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var5});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var5});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var5});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var5});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var5});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var5});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var5});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, 51, 65});
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)(var5 + 1)});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var5});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)var5});
            this.getMsgMgr(var2).updateSetting(var3, var4, var5);
            SharePreUtil.setIntValue(var2, "share_165_language", var5);
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var5});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var5});
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)(1 << var5 * 3)});
            break;
         case 20:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)var5});
            break;
         case 21:
            CanbusMsgSender.sendMsg(new byte[]{22, 51, 64});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var5});
            break;
         case 23:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)var5});
            break;
         case 24:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var5});
            break;
         case 25:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var5});
            break;
         case 26:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var5});
            break;
         case 27:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var5});
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (!SharePreUtil.getBoolValue(var1, "share_165_is_suppot_tire", true)) {
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

      if (!SharePreUtil.getBoolValue(var1, "share_165_is_suppot_hybrid", true)) {
         this.removeMainPrjBtnByName(var1, "hybird");
      }

      if (this.mDifferent != 17) {
         this.remvoeSettingLeftItemByName(var1, "_11_0x26_data3_bit32");
      }

      if (this.mDifferent != 49) {
         this.remvoeSettingLeftItemByName(var1, "_55_0x65_data1_bit21");
         this.remvoeSettingLeftItemByName(var1, "_11_0x26_data4_bit65");
         this.remvoeSettingLeftItemByName(var1, "_165_sterring_column");
      }

      if (this.mDifferent != 1) {
         this.remvoeSettingLeftItemByName(var1, "language_setup");
      }

      if ((this.mDifferent & 240) != 48) {
         this.removeFrontAirButtonByName(var1, "pollrn_removal");
      }

      if (this.mDifferent != 33) {
         this.removeFrontAirButtonByName(var1, "blow_window_foot");
      }

      if (this.mDifferent != 50) {
         this.removeRearAirButtonByName(var1, "rear_auto");
      }

      MyApplication.IS_SET = true;
   }

   private class BtnGroup {
      MyPanoramicView.OnBtnClickListener onNonRevBtnClick;
      MyPanoramicView.OnBtnClickListener onRevBtnClick;
      final UiMgr this$0;

      public BtnGroup(UiMgr var1, MyPanoramicView.OnBtnClickListener var2, MyPanoramicView.OnBtnClickListener var3) {
         this.this$0 = var1;
         this.onNonRevBtnClick = var2;
         this.onRevBtnClick = var3;
      }

      public MyPanoramicView.OnBtnClickListener getBtnClick(Context var1) {
         boolean var2 = CommUtil.isBackCamera(var1);
         boolean var3 = CommUtil.isPanoramic(var1);
         if (var2) {
            return this.onRevBtnClick;
         } else {
            return var3 ? this.onNonRevBtnClick : null;
         }
      }
   }

   private class OnPanoramicBtnCllick implements MyPanoramicView.OnBtnClickListener {
      final UiMgr this$0;

      private OnPanoramicBtnCllick(UiMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      OnPanoramicBtnCllick(UiMgr var1, Object var2) {
         this(var1);
      }

      public void onEndBottomClick() {
         Log.i("_165_UiMgr", "onEndBottomClick: ");
      }

      public void onEndTopClick() {
         Log.i("_165_UiMgr", "onEndTopClick: ");
      }

      public void onMidBottomClick() {
         Log.i("_165_UiMgr", "onMidBottomClick: ");
      }

      public void onStartBottomClick() {
         Log.i("_165_UiMgr", "onStartBottomClick: ");
      }

      public void onStartTopClick() {
         Log.i("_165_UiMgr", "onStartTopClick: ");
      }
   }
}
