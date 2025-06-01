package com.hzbhd.canbus.car._278;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   protected static final String CAN_1168_SAVE_LANGUAGE = "__1168_SAVE_LANGUAGE";
   protected static final int CAR_ID_EXCELLE_2015 = 251;
   protected static final int CAR_ID_GL6 = 255;
   protected static final int CAR_ID_GL8_2017 = 7;
   protected static final int CAR_ID_H2S_RED_BLUE_2017_2018 = 4;
   protected static final int CAR_ID_H2_2014_2016 = 1;
   protected static final int CAR_ID_H2_BULE_2017_2018 = 2;
   protected static final int CAR_ID_H2_RED_2017_2018 = 3;
   protected static final int CAR_ID_H6_BLUE_RED_2017_2018 = 12;
   protected static final int CAR_ID_H6_BLUE_RED_CHANGE_2017_2018 = 12;
   protected static final int CAR_ID_H6_CHANGE_2019 = 12;
   protected static final int CAR_ID_H6_COUPLE_2016 = 10;
   protected static final int CAR_ID_H6_COUPLE_2019_2020 = 11;
   protected static final int CAR_ID_H6_COUPLE_BLUE_2017 = 7;
   protected static final int CAR_ID_H6_COUPLE_RED_2017 = 6;
   protected static final int CAR_ID_H6_COUPLE_TOP = 11;
   protected static final int CAR_ID_H6_SPORT_2013_2017 = 5;
   protected static final int CAR_ID_H6_SPORT_2018 = 8;
   protected static final int CAR_ID_H9_2015_2018 = 9;
   protected static final int CAR_ID_LACROSSE_2004 = 253;
   protected static final int CAR_ID_MALIBU_2016 = 254;
   protected static final int CAR_ID_VERANO_2015 = 252;
   protected static int mDiffid;
   private static int mFrontWindMode;
   private static int mRearWindMode;
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private int mFlSeatCold = 0;
   private int mFlSeatHeat = 0;
   private int mFrSeatCold = 0;
   private int mFrSeatHeat = 0;
   private FrontArea mFrontArea;
   private MsgMgr mMsgMgr;
   private View mMyPanoramicView;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirCommand(25);
               }
            } else {
               this.this$0.sendAirCommand(35);
            }
         } else {
            this.this$0.sendAirCommand(30);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCommand(35);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirCommand(34);
               }
            } else {
               this.this$0.sendAirCommand(17);
            }
         } else {
            this.this$0.sendAirCommand(16);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCommand(19);
         }

      }
   };
   private OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         if (var1 == 9 && var2 == 1) {
            this.this$0.sendData(new byte[]{22, -102, 2, 1});
         }

      }
   };
   private OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         if (2 == var1) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 == 3) {
                        this.this$0.sendAirCommand(43);
                     }
                  } else {
                     this.this$0.sendAirCommand(42);
                  }
               } else {
                  this.this$0.sendAirCommand(41);
               }
            } else {
               this.this$0.sendAirCommand(40);
            }
         }

         if (var1 == 0 && var2 == 19) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 1});
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         Log.i("onClickItem", "onClickItem leftPos=" + var1);
         boolean var5 = false;
         if (var1 != 0) {
            if (var1 == 1) {
               if (var2 != 0) {
                  if (var2 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -122, 8, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -122, 7, (byte)((byte)var3 + 1)});
               }
            }
         } else {
            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)((byte)var3 + 1)});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)((byte)var3 + 1)});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)((byte)var3 + 1)});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)((byte)var3 + 1)});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)((byte)var3 + 1)});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)var3});
               case 19:
               default:
                  break;
               case 20:
                  SharePreUtil.setIntValue(this.this$0.mContext, "__278_SAVE_RADAR_DISP", var3);
                  this.this$0.mMsgMgr.initRadarDisp(this.this$0.mContext);
                  UiMgr var6 = this.this$0;
                  ParkPageUiSet var7 = var6.getParkPageUiSet(var6.mContext);
                  boolean var4;
                  if (var3 == 1) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  var7.setShowRadar(var4);
                  var4 = var5;
                  if (var3 == 0) {
                     var4 = true;
                  }

                  var7.setShowCusPanoramicView(var4);
            }
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(31);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(30);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(33);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(32);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommandReduce(22);
      }

      public void onClickUp() {
         Log.e("", "");
         this.this$0.sendAirCommandPlus(22);
      }
   };
   private RearArea mRearArea;
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontLeftSeatHeatDn();
         } else {
            this.this$0.sendAirCommand(36);
         }

      }

      public void onClickPlus() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontLeftSeatHeatUp();
         } else {
            this.this$0.sendAirCommand(36);
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeftCold = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontLeftSeatColdDn();
         } else {
            this.this$0.sendAirCommand(38);
         }

      }

      public void onClickPlus() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontLeftSeatColdUp();
         } else {
            this.this$0.sendAirCommand(38);
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontRightSeatHeatDn();
         } else {
            this.this$0.sendAirCommand(37);
         }

      }

      public void onClickPlus() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontRightSeatHeatUp();
         } else {
            this.this$0.sendAirCommand(37);
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRightCold = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontRightSeatColdDn();
         } else {
            this.this$0.sendAirCommand(39);
         }

      }

      public void onClickPlus() {
         if (UiMgr.mDiffid == 11) {
            this.this$0.setFrontRightSeatColdUp();
         } else {
            this.this$0.sendAirCommand(39);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      mDiffid = this.getCurrentCarId();
      AirPageUiSet var3 = this.getAirUiSet(var1);
      this.airPageUiSet = var3;
      this.mFrontArea = var3.getFrontArea();
      this.mRearArea = this.airPageUiSet.getRearArea();
      ParkPageUiSet var5 = this.getParkPageUiSet(var1);
      boolean var2;
      if (SharePreUtil.getIntValue(var1, "__278_SAVE_RADAR_DISP", 0) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var5.setShowRadar(var2);
      if (SharePreUtil.getIntValue(var1, "__278_SAVE_RADAR_DISP", 0) == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var5.setShowCusPanoramicView(var2);
      this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight, this.onMinPlusClickListenerLeftCold, this.onMinPlusClickListenerRightCold});
      this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(29);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(28);
         }
      });
      this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommandReduce(21);
         }

         public void onClickRight() {
            this.this$0.sendAirCommandPlus(21);
         }
      });
      this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            UiMgr.sendAirCommandFrontWindMode();
         }

         public void onRightSeatClick() {
            UiMgr.sendAirCommandFrontWindMode();
         }
      });
      this.airPageUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirCommandRearWindMode();
         }

         public void onRightSeatClick() {
            this.this$0.sendAirCommandRearWindMode();
         }
      });
      SettingPageUiSet var6 = this.getSettingUiSet(var1);
      var6.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var6.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
      var6.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
      this.mMsgMgr.initRadarDisp(this.mContext);
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, (byte)(var2 + 0)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -122, 2, (byte)(var2 + 0)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 4, (byte)(var2 + 0)});
            }

         }
      });
      var4.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -122, 5, (byte)(var2 + 10)});
               }
            } else {
               Log.i("ljq", "position: value = " + var2);
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 6, (byte)(var2 + 10)});
            }

         }
      });
      this.mMsgMgr.initRadarDisp(this.mContext);
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
            CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   protected static void sendAirCommandFrontWindMode() {
      byte[] var1 = new byte[]{21, 24, 25, 26, 27};
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var1[mFrontWindMode], 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var1[mFrontWindMode], 0});
      int var0 = mFrontWindMode + 1;
      mFrontWindMode = var0;
      if (var0 >= 5) {
         mFrontWindMode = 0;
      }

   }

   private void sendAirCommandPlus(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, 1});
   }

   private void sendAirCommandRearWindMode() {
      int var1 = mRearWindMode;
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (new byte[]{17, 18, 19, 20})[var1], -1});
      var1 = mRearWindMode + 1;
      mRearWindMode = var1;
      if (var1 >= 4) {
         mRearWindMode = 0;
      }

   }

   private void sendAirCommandReduce(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, 2});
   }

   private void sendAirCommandSwitch(boolean var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var2, (byte)(var1 ^ 1)});
   }

   private void setFrontLeftSeatColdDn() {
      this.mFlSeatCold = GeneralAirData.front_left_seat_cold_level;
      if (GeneralAirData.front_left_seat_cold_level > 0) {
         int var1 = this.mFlSeatCold - 1;
         this.mFlSeatCold = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte)var1});
      } else {
         this.mFlSeatCold = 3;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte)3});
      }

   }

   private void setFrontLeftSeatColdUp() {
      this.mFlSeatCold = GeneralAirData.front_left_seat_cold_level;
      if (GeneralAirData.front_left_seat_cold_level < 3) {
         int var1 = this.mFlSeatCold + 1;
         this.mFlSeatCold = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte)var1});
      } else {
         this.mFlSeatCold = 0;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte)0});
      }

   }

   private void setFrontLeftSeatHeatDn() {
      this.mFlSeatHeat = GeneralAirData.front_left_seat_heat_level;
      if (GeneralAirData.front_left_seat_heat_level > 0) {
         int var1 = this.mFlSeatHeat - 1;
         this.mFlSeatHeat = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte)var1});
      } else {
         this.mFlSeatHeat = 3;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte)3});
      }

   }

   private void setFrontLeftSeatHeatUp() {
      this.mFlSeatHeat = GeneralAirData.front_left_seat_heat_level;
      if (GeneralAirData.front_left_seat_heat_level < 3) {
         int var1 = this.mFlSeatHeat + 1;
         this.mFlSeatHeat = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte)var1});
      } else {
         this.mFlSeatHeat = 0;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte)0});
      }

   }

   private void setFrontRightSeatColdDn() {
      this.mFrSeatCold = GeneralAirData.front_right_seat_cold_level;
      if (GeneralAirData.front_right_seat_cold_level > 0) {
         int var1 = this.mFrSeatCold - 1;
         this.mFrSeatCold = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte)var1});
      } else {
         this.mFrSeatCold = 3;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte)3});
      }

   }

   private void setFrontRightSeatColdUp() {
      this.mFrSeatCold = GeneralAirData.front_right_seat_cold_level;
      if (GeneralAirData.front_right_seat_cold_level < 3) {
         int var1 = this.mFrSeatCold + 1;
         this.mFrSeatCold = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte)var1});
      } else {
         this.mFrSeatCold = 0;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte)0});
      }

   }

   private void setFrontRightSeatHeatDn() {
      this.mFrSeatHeat = GeneralAirData.front_right_seat_heat_level;
      if (GeneralAirData.front_right_seat_heat_level > 0) {
         int var1 = this.mFrSeatHeat - 1;
         this.mFrSeatHeat = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte)var1});
      } else {
         this.mFrSeatHeat = 3;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte)3});
      }

   }

   private void setFrontRightSeatHeatUp() {
      this.mFrSeatHeat = GeneralAirData.front_right_seat_heat_level;
      if (GeneralAirData.front_right_seat_heat_level < 3) {
         int var1 = this.mFrSeatHeat + 1;
         this.mFrSeatHeat = var1;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte)var1});
      } else {
         this.mFrSeatHeat = 0;
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte)0});
      }

   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1);
      }

      return this.mMyPanoramicView;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.getCurrentCarId()});
      if (this.getCurrentCarId() != 3 && this.getCurrentCarId() != 1 && this.getCurrentCarId() != 2 && this.getCurrentCarId() != 4) {
         MsgMgr.maxRadarLen = 7;
      } else {
         MsgMgr.maxRadarLen = 4;
      }

      if (this.getCurrentCarId() != 4 && this.getCurrentCarId() != 8 && this.getCurrentCarId() != 12) {
         MsgMgr.trackType = 0;
      } else {
         MsgMgr.trackType = 1;
      }

   }
}
