package com.hzbhd.canbus.car._266;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private OnAirBtnClickListener mFrontBottombtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.SendAirMessage(7);
               }
            } else {
               this.this$0.SendAirMessage(1);
            }
         } else {
            this.this$0.SendAirMessage(2);
         }

      }
   };
   private OnAirBtnClickListener mFrontTopbtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendAirMessage(6);
            }
         } else {
            this.this$0.SendAirMessage(5);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendAirMessage(14);
      }

      public void onClickUp() {
         this.this$0.SendAirMessage(13);
      }
   };
   private OnAirBtnClickListener mRearBtnListnener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SendAirMessage(19);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendAirMessage(16);
      }

      public void onClickUp() {
         this.this$0.SendAirMessage(15);
      }
   };
   private MsgMgr msgMgr;
   private ParkPageUiSet parkPageUiSet;
   int windDirection = 1;

   public UiMgr(Context var1) {
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.airPageUiSet = this.getAirUiSet(var1);
      this.parkPageUiSet = this.getParkPageUiSet(var1);
      this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopbtnListener, null, null, this.mFrontBottombtnListener});
      this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mRearBtnListnener});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
      this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.getEachId() == 3) {
               this.this$0.SendAirMessage(21);
            }
         }

         public void onRightSeatClick() {
            if (this.this$0.getEachId() == 3) {
               this.this$0.SendAirMessage(21);
            }
         }
      });
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.SendAirMessage(12);
         }

         public void onClickRight() {
            this.this$0.SendAirMessage(11);
         }
      });
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 7, (byte)var3});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 6, (byte)var3});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 5, (byte)var3});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 4, (byte)var3});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 3, (byte)var3});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 2, (byte)var3});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 1, (byte)var3});
                     break;
                  case 7:
                     CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)var3});
                     this.this$0.msgMgr.languageSet(var3);
               }
            }

         }
      });
   }

   private void SendAirMessage(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getEachId(), 33});
      if (this.getCurrentCarId() == 2) {
         this.removeMainPrjBtn(this.mContext, 0, 1);
      }

      if (this.getEachId() != 3) {
         this.parkPageUiSet.setShowRadar(false);
         this.airPageUiSet.getFrontArea().setAllBtnCanClick(false);
         this.airPageUiSet.getFrontArea().setCanSetWindSpeed(false);
         this.airPageUiSet.getFrontArea().setCanSetLeftTemp(false);
         this.airPageUiSet.getFrontArea().setCanSetRightTemp(false);
         this.airPageUiSet.getRearArea().setAllBtnCanClick(false);
      }

   }
}
