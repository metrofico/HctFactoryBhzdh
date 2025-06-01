package com.hzbhd.canbus.car._144;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;

public class UiMgr extends AbstractUiMgr {
   private int currentClickFront;
   private int currentWindLv;
   private Context mContext;
   private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendAirBtnCtrl(6, GeneralAirData.rear_defog);
                  }
               } else {
                  this.this$0.sendAirBtnCtrl(5, GeneralAirData.front_defog);
               }
            } else {
               this.this$0.sendAirBtnCtrl(16, GeneralAirData.aqs);
            }
         } else {
            this.this$0.sendAirBtnCtrl(7, GeneralAirData.in_out_cycle);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirBtnCtrl(4, GeneralAirData.auto);
      }
   };
   private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirBtnCtrl(1, GeneralAirData.power);
      }
   };
   private OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         int var1 = this.this$0.currentClickFront;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.currentClickFront = 0;
                           CanbusMsgSender.sendMsg(new byte[]{22, 59, 10, 0});
                        }
                     } else {
                        this.this$0.currentClickFront = 5;
                        this.this$0.sendAirClick(10);
                     }
                  } else {
                     this.this$0.currentClickFront = 4;
                     CanbusMsgSender.sendMsg(new byte[]{22, 59, 9, 0});
                  }
               } else {
                  this.this$0.currentClickFront = 3;
                  this.this$0.sendAirClick(9);
               }
            } else {
               this.this$0.currentClickFront = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 59, 8, 0});
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(8);
         }

      }

      public void onRightSeatClick() {
         int var1 = this.this$0.currentClickFront;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.currentClickFront = 0;
                           CanbusMsgSender.sendMsg(new byte[]{22, 59, 10, 0});
                        }
                     } else {
                        this.this$0.currentClickFront = 5;
                        this.this$0.sendAirClick(10);
                     }
                  } else {
                     this.this$0.currentClickFront = 4;
                     CanbusMsgSender.sendMsg(new byte[]{22, 59, 9, 0});
                  }
               } else {
                  this.this$0.currentClickFront = 3;
                  this.this$0.sendAirClick(9);
               }
            } else {
               this.this$0.currentClickFront = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 59, 8, 0});
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(8);
         }

      }
   };
   private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     var1 = this.this$0.currentWindLv;
                     if (var1 != 0) {
                        if (var1 != 1) {
                           if (var1 == 2) {
                              this.this$0.currentWindLv = 0;
                              CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 2});
                           }
                        } else {
                           this.this$0.currentWindLv = 2;
                           CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 1});
                        }
                     } else {
                        this.this$0.currentWindLv = 1;
                        CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 0});
                     }
                  }
               } else {
                  this.this$0.sendAirBtnCtrl(2, GeneralAirData.ac);
               }
            } else {
               this.this$0.sendAirBtnCtrl(15, GeneralAirData.mono);
            }
         } else {
            this.this$0.sendAirBtnCtrl(3, GeneralAirData.ac_max);
         }

      }
   };
   private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         switch (var1) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 22, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 22, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 23, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 23, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 38, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 38, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 25, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 25, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 36, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 36, 0});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 26, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 26, 0});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 37, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 37, 0});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 24, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 24, 0});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 39, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 39, 0});
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 2});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 12, 2});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 12, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 13, 2});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 13, 1});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     if (var2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, (byte)(2 - var3)});
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte)(var3 + 1)});
                     }
                  }
               } else if (var3 < 16) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
               } else {
                  switch (var3) {
                     case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 18});
                        break;
                     case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 20});
                        break;
                     case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 22});
                        break;
                     case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 23});
                        break;
                     case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 26});
                        break;
                     case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 25});
                        break;
                     case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 28});
                        break;
                     case 23:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 29});
                        break;
                     case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 30});
                        break;
                     case 25:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 31});
                        break;
                     case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 32});
                        break;
                     case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 33});
                  }
               }
            } else {
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 8, (byte)var3});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 11, (byte)var3});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 9, (byte)var3});
                     break;
                  case 3:
                     var1 = System.getInt(this.val$context.getContentResolver(), "left0right4", 0);
                     boolean var4;
                     if (var3 == 1) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 10, (byte)DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var4), var1, 0, 7)});
                  case 4:
                  default:
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 1, (byte)var3});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 2, (byte)var3});
                     break;
                  case 7:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 12, (byte)var3});
                     break;
                  case 8:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 13, (byte)var3});
                     break;
                  case 9:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 4, (byte)var3});
                     break;
                  case 10:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 5, (byte)var3});
                     break;
                  case 11:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 6, (byte)var3});
                     break;
                  case 12:
                     CanbusMsgSender.sendMsg(new byte[]{22, 123, 3, (byte)var3});
                     Intent var5 = new Intent("REVERSING_SOUND");
                     var5.putExtra("selectPos1", var3);
                     this.this$0.mContext.sendBroadcast(var5);
               }
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 4) {
               boolean var4;
               if (System.getInt(this.val$context.getContentResolver(), "left0right3", 0) == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, 123, 10, (byte)DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var4), var3, 0, 7)});
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
      var3.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(this.mOnPanelKeyPositionListener);
   }

   private void sendAirBtnCtrl(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, this.setWidgetData(var2)});
   }

   private void sendAirClick(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, 1});
   }

   private byte setWidgetData(boolean var1) {
      return (byte)(var1 ^ 1);
   }
}
