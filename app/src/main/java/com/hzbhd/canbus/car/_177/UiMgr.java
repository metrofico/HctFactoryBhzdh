package com.hzbhd.canbus.car._177;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   public int currentClickFront;
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private Context mContext;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontBottom[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontRight[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontLeft[var1]);
      }
   };
   OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.setFrontSearClick();
      }

      public void onRightSeatClick() {
         this.this$0.setFrontSearClick();
      }
   };
   OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         if (var1 == 1) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 5});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 4});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 1});
            }
         }

      }
   };
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 == 2 && var2 == 11) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
            }
         } else if (var2 == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
         }

      }
   };
   OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 == 2) {
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte)var3});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte)var3});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var3});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var3});
                     break;
                  case 7:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte)var3});
                     break;
                  case 8:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var3});
                     break;
                  case 9:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
                     break;
                  case 10:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)(var3 + MsgMgr.ONE)});
                  case 11:
                  default:
                     break;
                  case 12:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)var3});
                     break;
                  case 13:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)(var3 + MsgMgr.ONE)});
                     break;
                  case 14:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte)(var3 + MsgMgr.ONE)});
                     break;
                  case 15:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte)var3});
                     break;
                  case 16:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte)var3});
                     break;
                  case 17:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)(var3 + MsgMgr.ONE)});
                     break;
                  case 18:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)var3});
                     break;
                  case 19:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)var3});
               }
            }
         } else {
            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)(var3 + MsgMgr.ONE)});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)(var3 + MsgMgr.ONE)});
               case 12:
               default:
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)(var3 + MsgMgr.ONE)});
                  break;
               case 14:
                  if (var3 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, -127});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                  }
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
         this.this$0.sendAirCommand(14);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.getCurrentCarId() != 4 && this.this$0.getCurrentCarId() != 9) {
            this.this$0.sendAirCommand(16);
         }

      }

      public void onClickUp() {
         this.this$0.sendAirCommand(15);
      }
   };
   OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (GeneralAirData.front_wind_level == 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 8});
         } else if (GeneralAirData.front_wind_level > MsgMgr.WIND_LEVEL_LOW) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte)(GeneralAirData.front_wind_level - 1)});
         }

      }

      public void onClickRight() {
         if (GeneralAirData.front_wind_level < MsgMgr.WIND_LEVEL_HIGH) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte)(GeneralAirData.front_wind_level + 1)});
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, 1});
      AirPageUiSet var2 = this.getAirUiSet(var1);
      String[][] var3 = var2.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var3[0];
      this.mAirBtnListFrontLeft = var3[1];
      this.mAirBtnListFrontRight = var3[2];
      this.mAirBtnListFrontBottom = var3[3];
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
      var2.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var4.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
      var4.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
   }

   private void sendAirCommand(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 0});
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
         case -1039745817:
            if (var1.equals("normal")) {
               var2 = 1;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 2;
            }
            break;
         case -54617514:
            if (var1.equals("auto_cycle")) {
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
         case 3135580:
            if (var1.equals("fast")) {
               var2 = 7;
            }
            break;
         case 3535914:
            if (var1.equals("soft")) {
               var2 = 8;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 9;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 10;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(6);
            break;
         case 1:
            this.sendAirCommand(27);
            break;
         case 2:
            this.sendAirCommand(23);
            break;
         case 3:
            this.sendAirCommand(22);
            break;
         case 4:
            this.sendAirCommand(1);
            break;
         case 5:
            this.sendAirCommand(2);
            break;
         case 6:
            this.sendAirCommand(3);
            break;
         case 7:
            this.sendAirCommand(26);
            break;
         case 8:
            this.sendAirCommand(25);
            break;
         case 9:
            this.sendAirCommand(0);
            break;
         case 10:
            this.sendAirCommand(21);
      }

   }

   private void setFrontSearClick() {
      if (GeneralAirData.front_right_blow_window) {
         this.currentClickFront = 3;
         if (GeneralAirData.front_right_blow_foot) {
            this.currentClickFront = 4;
            if (GeneralAirData.front_right_blow_head) {
               this.currentClickFront = 5;
            }
         }
      }

      if (!GeneralAirData.front_right_blow_window) {
         this.currentClickFront = 2;
         if (!GeneralAirData.front_right_blow_foot) {
            this.currentClickFront = 1;
            if (!GeneralAirData.front_right_blow_head) {
               this.currentClickFront = 0;
            }
         }
      }

      label61: {
         int var1 = this.currentClickFront;
         if (var1 != 0) {
            if (var1 == 1) {
               break label61;
            }

            if (var1 == 2 || var1 == 3) {
               this.sendAirCommand(24);
               return;
            }

            if (var1 == 4) {
               break label61;
            }

            if (var1 != 5) {
               return;
            }
         }

         this.sendAirCommand(7);
         return;
      }

      this.sendAirCommand(9);
   }
}
