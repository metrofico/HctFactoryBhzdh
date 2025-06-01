package com.hzbhd.canbus.car._373;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int AmpData0 = 0;
   int AmpData1 = 0;
   int AmpData2 = 0;
   int AmpData3 = 0;
   int AmpData4 = 0;
   int AmpData5 = 0;
   int AmpData6 = 0;
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.send0x95AirInfo(3);
            }
         } else {
            this.this$0.send0x95AirInfo(7);
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
            this.this$0.send0x95AirInfo(4);
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
                  this.this$0.send0x95AirInfo(36);
               }
            } else {
               this.this$0.send0x95AirInfo(1);
            }
         } else {
            this.this$0.send0x95AirInfo(0);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.send0x96RearAirInfo(31);
               }
            } else {
               this.this$0.send0x96RearAirInfo(4);
            }
         } else {
            this.this$0.send0x96RearAirInfo(0);
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.send0x95AirInfo(33);
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
         this.this$0.send0x95AirInfo(35);
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
         this.this$0.send0x95AirInfo(32);
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
         this.this$0.send0x95AirInfo(34);
      }
   };
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.requestDate(5);
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.windState == 0) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 1;
         } else if (this.this$0.windState == 1) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 2;
         } else if (this.this$0.windState == 2) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 3;
         } else if (this.this$0.windState == 3) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.windState == 0) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 1;
         } else if (this.this$0.windState == 1) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 2;
         } else if (this.this$0.windState == 2) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 3;
         } else if (this.this$0.windState == 3) {
            this.this$0.send0x95AirInfo(8);
            this.this$0.windState = 0;
         }

      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.send0x95AirInfo(14);
      }

      public void onClickUp() {
         this.this$0.send0x95AirInfo(15);
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.send0x95AirInfo(16);
      }

      public void onClickUp() {
         this.this$0.send0x95AirInfo(17);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.send0x95AirInfo(12);
      }

      public void onClickRight() {
         this.this$0.send0x95AirInfo(13);
      }
   };
   OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
      }

      public void destroy() {
      }
   };
   OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               this.this$0.AmpData1 = var2 + 10;
               this.this$0.send0x93AmpInfo();
               AmpUtil.saveAmpSendValue(this.this$0.mContext, 6, this.this$0.AmpData1);
            }
         } else {
            this.this$0.AmpData2 = -var2 + 10;
            this.this$0.send0x93AmpInfo();
            AmpUtil.saveAmpSendValue(this.this$0.mContext, 5, this.this$0.AmpData2);
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
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     this.this$0.AmpData5 = var2 + 1;
                     this.this$0.send0x93AmpInfo();
                     AmpUtil.saveAmpSendValue(this.this$0.mContext, 2, this.this$0.AmpData5);
                  }
               } else {
                  this.this$0.AmpData4 = var2 + 1;
                  this.this$0.send0x93AmpInfo();
                  AmpUtil.saveAmpSendValue(this.this$0.mContext, 3, this.this$0.AmpData4);
               }
            } else {
               this.this$0.AmpData3 = var2 + 1;
               this.this$0.send0x93AmpInfo();
               AmpUtil.saveAmpSendValue(this.this$0.mContext, 4, this.this$0.AmpData3);
            }
         } else {
            this.this$0.AmpData0 = var2;
            this.this$0.send0x93AmpInfo();
            AmpUtil.saveAmpSendValue(this.this$0.mContext, 1, this.this$0.AmpData0);
         }

      }
   };
   OnAirSeatClickListener onRearAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.rearSeatState == 0) {
            this.this$0.send0x96RearAirInfo(8);
            this.this$0.rearSeatState = 1;
         } else if (this.this$0.rearSeatState == 1) {
            this.this$0.send0x96RearAirInfo(9);
            this.this$0.rearSeatState = 2;
         } else if (this.this$0.rearSeatState == 2) {
            this.this$0.send0x96RearAirInfo(10);
            this.this$0.rearSeatState = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.rearSeatState == 0) {
            this.this$0.send0x96RearAirInfo(8);
            this.this$0.rearSeatState = 1;
         } else if (this.this$0.rearSeatState == 1) {
            this.this$0.send0x96RearAirInfo(9);
            this.this$0.rearSeatState = 2;
         } else if (this.this$0.rearSeatState == 2) {
            this.this$0.send0x96RearAirInfo(10);
            this.this$0.rearSeatState = 0;
         }

      }
   };
   OnAirTemperatureUpDownClickListener onRearAirTemperatureUpDownClickListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.send0x96RearAirInfo(14);
      }

      public void onClickUp() {
         this.this$0.send0x96RearAirInfo(15);
      }
   };
   OnAirWindSpeedUpDownClickListener onRearAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.send0x96RearAirInfo(12);
      }

      public void onClickRight() {
         this.this$0.send0x96RearAirInfo(13);
      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_373_setting_car_control")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control1")) {
               this.this$0.send0x97CarControlInfo(1, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control2")) {
               this.this$0.send0x97CarControlInfo(39, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control_add_function")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           this.this$0.send0x97CarControlInfo(17, 90);
                        }
                     } else {
                        this.this$0.send0x97CarControlInfo(17, 60);
                     }
                  } else {
                     this.this$0.send0x97CarControlInfo(17, 30);
                  }
               } else {
                  this.this$0.send0x97CarControlInfo(17, 0);
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control3")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           this.this$0.send0x97CarControlInfo(18, 90);
                        }
                     } else {
                        this.this$0.send0x97CarControlInfo(18, 60);
                     }
                  } else {
                     this.this$0.send0x97CarControlInfo(18, 30);
                  }
               } else {
                  this.this$0.send0x97CarControlInfo(18, 0);
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control4")) {
               this.this$0.send0x97CarControlInfo(19, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control5")) {
               this.this$0.send0x97CarControlInfo(21, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control6")) {
               this.this$0.send0x97CarControlInfo(34, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control7")) {
               this.this$0.send0x97CarControlInfo(36, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control8")) {
               this.this$0.send0x97CarControlInfo(37, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_setting_car_control", "_373_setting_car_control9")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           this.this$0.send0x97CarControlInfo(50, 40);
                        }
                     } else {
                        this.this$0.send0x97CarControlInfo(50, 20);
                     }
                  } else {
                     this.this$0.send0x97CarControlInfo(50, 3);
                  }
               } else {
                  this.this$0.send0x97CarControlInfo(50, 0);
               }
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_373_other_seting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_other_seting", "_373_amp_seting1")) {
               if (var3 == 0) {
                  this.this$0.AmpData6 = 0;
               } else if (var3 == 1) {
                  this.this$0.AmpData6 = 2;
               } else if (var3 == 2) {
                  this.this$0.AmpData6 = 4;
               } else if (var3 == 3) {
                  this.this$0.AmpData6 = 6;
               }

               this.this$0.send0x93AmpInfo();
               AmpUtil.saveAmpSendValue(this.this$0.mContext, 7, this.this$0.AmpData6);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_other_seting", "_373_amp_seting2")) {
               this.this$0.send0x97CarControlInfo(82, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_373_other_seting", "_373_amp_seting3")) {
               this.this$0.send0x97CarControlInfo(82, var3 + 1);
            }
         }

      }
   };
   DecimalFormat oneDecimal = new DecimalFormat("###0.0");
   int rearSeatState = 0;
   DecimalFormat timeFormat = new DecimalFormat("00");
   DecimalFormat towDecimal = new DecimalFormat("###0.00");
   int windState = 0;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var2.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      var2.getRearArea().setSetWindSpeedViewOnClickListener(this.onRearAirWindSpeedUpDownClickListener);
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onRearAirTemperatureUpDownClickListener, null});
      var2.getRearArea().setOnAirSeatClickListener(this.onRearAirSeatClickListener);
      this.getSettingUiSet(this.mContext).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(this.mContext);
      var3.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var3.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      var3.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initAmp(Context var1) {
      (new Thread(new Runnable(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            (new AmpUtil()).initAmpData(this.this$0.mContext);
         }
      })).start();
      (new AmpUtil()).intiAmpUi(var1);
   }

   private void send0x93AmpInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte)this.AmpData0, (byte)this.AmpData1, (byte)this.AmpData2, (byte)this.AmpData3, (byte)this.AmpData4, (byte)this.AmpData5, (byte)this.AmpData6});
   }

   private void send0x95AirInfo(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -107, var2, 1});

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -107, var2, 0});
   }

   private void send0x96RearAirInfo(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -106, var2, 1});

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -106, var2, 0});
   }

   private void send0x97CarControlInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -105, (byte)var1, (byte)var2});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var7.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var8 = var7.getItemList();
            Iterator var10 = var8.iterator();

            for(int var5 = 0; var5 < var8.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var10.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public boolean isLandscape() {
      boolean var1;
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void requestDate(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -15, (byte)var1, 0});
   }

   public void send0x81MakeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void send0x90MediaInfo(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void send0x98TimeInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void send0x99CompassInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -103, (byte)var1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initAmp(var1);
   }
}
