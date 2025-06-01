package com.hzbhd.canbus.car._436;

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
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   OnAirBtnClickListener bottomAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               }
            } else {
               this.this$0.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            }
         } else {
            this.this$0.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   OnAirTemperatureUpDownClickListener centerTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }

      public void onClickUp() {
         this.this$0.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }
   };
   int differentId;
   int eachId;
   OnAirBtnClickListener leftAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   Context mContext;
   MsgMgr mMsgMgr;
   byte noneData = 0;
   byte noneDataAlr = 0;
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.windModeTag == 0) {
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 1;
         } else if (this.this$0.windModeTag == 1) {
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 2;
         } else if (this.this$0.windModeTag == 2) {
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 3;
         } else if (this.this$0.windModeTag == 3) {
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.windModeTag == 0) {
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 1;
         } else if (this.this$0.windModeTag == 1) {
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 2;
         } else if (this.this$0.windModeTag == 2) {
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 3;
         } else if (this.this$0.windModeTag == 3) {
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 0;
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var5 = this.this$0;
         if (var1 == var5.getSettingLeftIndexes(var5.mContext, "_436_Setting_function")) {
            var5 = this.this$0;
            if (var2 == var5.getSettingRightIndex(var5.mContext, "_436_Setting_function", "_436_Setting_function_1")) {
               byte var4 = (byte)(var3 + 248);
               CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, var4, -1, -1, -1, -1, -128});
               CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, var4, -1, -1, -1, -1, -128});
            }

            var5 = this.this$0;
            if (var2 == var5.getSettingRightIndex(var5.mContext, "_436_Setting_function", "_436_Setting_function_2")) {
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, 63, -1, -1, -1, -1, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, 63, -1, -1, -1, -1, -128});
               }

               if (var3 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, 127, -1, -1, -1, -1, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -106, -1, -1, -1, 127, -1, -1, -1, -1, -128});
               }
            }

            var5 = this.this$0;
            if (var2 == var5.getSettingRightIndex(var5.mContext, "_436_Setting_function", "_436_Setting_function_3")) {
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, 127, 8, 0, -74, 8, -128});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, 127, 8, 0, -74, 8, -128});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, 127, 8, 0, -74, 8, -128});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, 127, 8, 0, -74, 8, -128});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, 127, 8, 0, -74, 8, -128});
                        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 24, -1, 15, 127, 8, 0, -74, 8, -128});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 16, -1, 15, 127, 8, 0, -74, 8, -128});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 8, -1, 15, 127, 8, 0, -74, 8, -128});
               }
            }

            var5 = this.this$0;
            if (var2 == var5.getSettingRightIndex(var5.mContext, "_436_Setting_function", "_436_Setting_function_4")) {
               if (var3 != 0) {
                  if (var3 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 4, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 4, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 4, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 4, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 4, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 4, 0, -74, 8, -128});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 0, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 0, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 0, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 0, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 0, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 0, 0, -74, 8, -128});
               }
            }

            var5 = this.this$0;
            if (var2 == var5.getSettingRightIndex(var5.mContext, "_436_Setting_function", "_436_Setting_function_4")) {
               if (var3 != 0) {
                  if (var3 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, 127, 8, 0, -74, 8, -128});
                     CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -6, 15, 127, 8, 0, -74, 8, -128});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, 127, 8, 0, -74, 8, -128});
                  CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -8, 15, 127, 8, 0, -74, 8, -128});
               }
            }
         }

      }
   };
   DecimalFormat oneDecimal = new DecimalFormat("###0.0");
   OnAirBtnClickListener rightAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   DecimalFormat timeFormat = new DecimalFormat("00");
   OnAirBtnClickListener topAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               }
            } else {
               this.this$0.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            }
         } else {
            this.this$0.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   DecimalFormat towDecimal = new DecimalFormat("###0.00");
   private int windModeTag = 0;
   OnAirWindSpeedUpDownClickListener windSpeedControl = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }

      public void onClickRight() {
         this.this$0.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.getSettingUiSet(this.mContext).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topAir, this.leftAir, this.rightAir, this.bottomAir});
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.centerTemp, null});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.windSpeedControl);
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
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

   public void sendAir(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 3, -108, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8, -64});
   }

   public void sendAirCmd0x394(int var1, int var2, int var3, int var4) {
      byte[] var5 = new byte[8];

      for(var3 = 0; var3 < 8; ++var3) {
         if (var3 == var1) {
            var5[var1] = (byte)(var4 << var2);
         } else {
            var5[var3] = this.noneDataAlr;
         }
      }

      var5 = this.SplicingByte(var5, new byte[]{(byte)(var1 + 5 << 4)});
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, 0, 0, 3, -108}, var5));
   }

   public void sendMediaInfo(int var1, int var2, int var3, int var4, byte[] var5) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, 0, 0, 3, -106, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5.length}, var5));
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
