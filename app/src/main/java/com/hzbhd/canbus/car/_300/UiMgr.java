package com.hzbhd.canbus.car._300;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private final int MSG_AIR_COMMAND_UP = 0;
   private int mDifferent = this.getCurrentCarId();
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1.arg1, 0});
         }

      }
   };
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)var3});
            }

         }
      });
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == 1 && var2 == 0) {
               this.this$0.sendAirCommand(16);
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        this.this$0.sendAirCommand(8);
                     }
                  } else {
                     this.this$0.sendAirCommand(3);
                  }
               } else {
                  this.this$0.sendAirCommand(2);
               }
            } else {
               this.this$0.sendAirCommand(10);
            }

         }
      }, new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 == 1) {
                  this.this$0.sendAirCommand(5);
               }
            } else {
               this.this$0.sendAirCommand(4);
            }

         }
      }, new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 == 1) {
                  this.this$0.sendAirCommand(1);
               }
            } else {
               this.this$0.sendAirCommand(11);
            }

         }
      }, new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           this.this$0.sendAirCommand(9);
                        }
                     } else {
                        this.this$0.sendAirCommand(36);
                     }
                  } else {
                     this.this$0.sendAirCommand(35);
                  }
               } else {
                  this.this$0.sendAirCommand(34);
               }
            } else {
               this.this$0.sendAirCommand(33);
            }

         }
      }});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(13);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(12);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(15);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(14);
         }
      }});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(7);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(6);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, 1});
      Message var2 = Message.obtain(this.mHandler);
      var2.what = 0;
      var2.arg1 = var1;
      this.mHandler.sendMessageDelayed(var2, 100L);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
