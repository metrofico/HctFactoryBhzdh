package com.hzbhd.canbus.car._423;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

public class UiMgr extends AbstractUiMgr {
   AirPageUiSet airPageUiSet;
   DriverDataPageUiSet driverDataPageUiSet;
   int i = 26;
   Context mContext;
   private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(7);
         }

      }
   };
   private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(5);
         }

      }
   };
   private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(6);
         }

      }
   };
   private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(1);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(14);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(13);
      }
   };
   private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(14);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(13);
      }
   };
   private MsgMgr msgMgr;
   ParkPageUiSet parkPageUiSet;

   public UiMgr(Context var1) {
      this.airPageUiSet = this.getAirUiSet(var1);
      this.parkPageUiSet = this.getParkPageUiSet(var1);
      this.driverDataPageUiSet = this.getDriverDataPageUiSet(var1);
      this.mContext = var1;
      this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            UiMgr var1;
            switch (this.this$0.i) {
               case 26:
               case 27:
               case 28:
                  var1 = this.this$0;
                  ++var1.i;
                  break;
               case 29:
                  this.this$0.i = 26;
            }

            var1 = this.this$0;
            var1.sendAirMessage(var1.i);
         }

         public void onRightSeatClick() {
            UiMgr var1;
            switch (this.this$0.i) {
               case 26:
               case 27:
               case 28:
                  var1 = this.this$0;
                  ++var1.i;
                  break;
               case 29:
                  this.this$0.i = 26;
            }

            var1 = this.this$0;
            var1.sendAirMessage(var1.i);
         }
      });
      this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirMessage(12);
         }

         public void onClickRight() {
            this.this$0.sendAirMessage(11);
         }
      });
      this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1) {
            if (var1 == 0) {
               byte var2;
               if (this.this$0.getMsgMgr(this.val$context).PanoramicVideo) {
                  var2 = 3;
               } else {
                  var2 = 7;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, (byte)var2});
            }

         }
      });
      this.airPageUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.ActiveRequest(49);
         }
      });
      this.driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.ActiveRequest(26);
         }
      });
   }

   private void ActiveRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void sendAirMessage(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   public void sendPanoramicInfo0xFD() {
      byte var1;
      if (this.getMsgMgr(this.mContext).PanoramicVideo) {
         var1 = 3;
      } else {
         var1 = 7;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, (byte)var1});
   }
}
