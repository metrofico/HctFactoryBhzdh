package com.hzbhd.canbus.car._330;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   String L3R0_300 = "L3R0_300";
   String L3R1_300 = "L3R1_300";
   private int RADIO_PLAY_PAUSE = 0;
   private int eachCanId;
   private int fastBack = 0;
   private int fastForward = 0;
   private Context mContext;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     this.this$0.sendAirData(20);
                  }
               } else {
                  this.this$0.sendAirData(58);
               }
            } else {
               this.this$0.sendAirData(39);
            }
         } else {
            this.this$0.sendAirData(16);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirData(40);
            }
         } else {
            this.this$0.sendAirData(23);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     this.this$0.sendAirData(18);
                  }
               } else {
                  this.this$0.sendAirData(57);
               }
            } else {
               this.this$0.sendAirData(1);
            }
         } else {
            this.this$0.sendAirData(25);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirData(60);
            }
         } else {
            this.this$0.sendAirData(21);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.this$0.sendAirData(59);
            }
         } else {
            this.this$0.sendAirData(52);
         }

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
         this.this$0.sendAirData(11);
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
         this.this$0.sendAirData(13);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatRearLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirData(12);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatRearRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirData(14);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(2);
      }

      public void onClickUp() {
         this.this$0.sendAirData(3);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(4);
      }

      public void onClickUp() {
         this.this$0.sendAirData(5);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(41);
      }

      public void onClickUp() {
         this.this$0.sendAirData(42);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearMiddle = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         Toast.makeText(this.this$0.mContext, "暂不支持切换此状态", 0).show();
      }

      public void onClickUp() {
         Toast.makeText(this.this$0.mContext, "暂不支持切换此状态", 0).show();
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(50);
      }

      public void onClickUp() {
         this.this$0.sendAirData(51);
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.sendAirData(36);
      }

      public void onRightSeatClick() {
         this.this$0.sendAirData(37);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirData(9);
      }

      public void onClickRight() {
         this.this$0.sendAirData(10);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirData(48);
      }

      public void onClickRight() {
         this.this$0.sendAirData(49);
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
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 7)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(-var2 + 7)});
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
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var2});
         }

      }
   };
   OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var3.getLeftIndexes(var3.mContext, "_330_Information_reset") == var1 && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -27, 0, 0});
            Toast.makeText(this.this$0.mContext, "重设成功", 0).show();
         }

      }
   };
   OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.getMsgMgr(var2.mContext);
         if (MsgMgr.mediaTag.equals("USB")) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -59, 4});
                  }
               } else if (this.this$0.play_pause_state == 0) {
                  this.this$0.play_pause_state = 1;
                  CanbusMsgSender.sendMsg(new byte[]{22, -59, 1});
               } else {
                  this.this$0.play_pause_state = 0;
                  CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 3});
            }
         }

         var2 = this.this$0;
         var2.getMsgMgr(var2.mContext);
         if (MsgMgr.mediaTag.equals("CD")) {
            switch (var1) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 22, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 20, 0});
                  break;
               case 2:
                  if (this.this$0.fastBack == 0) {
                     this.this$0.fastBack = 1;
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 25, 1});
                  } else {
                     this.this$0.fastBack = 0;
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 25, 0});
                  }
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 16, 0});
                  Toast.makeText(this.this$0.mContext, "随机播放", 0).show();
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, 0});
                  Toast.makeText(this.this$0.mContext, "循环播放", 0).show();
                  break;
               case 5:
                  if (this.this$0.fastForward == 0) {
                     this.this$0.fastForward = 1;
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 24, 1});
                  } else {
                     this.this$0.fastForward = 0;
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 24, 0});
                  }
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 19, 0});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 21, 0});
            }
         }

         var2 = this.this$0;
         var2.getMsgMgr(var2.mContext);
         if (MsgMgr.mediaTag.equals("RADIO")) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -124, 37, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 34, 0});
                     }
                  } else if (this.this$0.RADIO_PLAY_PAUSE == 0) {
                     this.this$0.RADIO_PLAY_PAUSE = 1;
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 36, 1});
                  } else {
                     this.this$0.RADIO_PLAY_PAUSE = 0;
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 36, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 35, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 38, 0});
            }
         }

      }
   };
   OnOriginalCarDeviceBackPressedListener onOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onBackPressed() {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 5});
      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_330_other_settings")) {
            if (var2 != 0) {
               if (var2 == 1) {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.L3R1_300, var1, var2, var3 * 100, "");
                  CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var3});
               }
            } else {
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.L3R0_300, var1, var2, var3 * 100, "");
               CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)var3});
            }
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
         if (var1 == var5.getLeftIndexes(var5.mContext, "_330_amplifier_info")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 3) {
                     if (var2 == 5) {
                        if (var3 == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, 0});
                        } else if (var3 == 1) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, 1});
                        } else if (var3 == 2) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, 2});
                        } else if (var3 == 3) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, 3});
                        }
                     }
                  } else if (var3 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                  }
               } else if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 1});
               }
            } else if (var3 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, 1});
            }
         }

         var5 = this.this$0;
         if (var1 == var5.getLeftIndexes(var5.mContext, "_330_Mode_selection") && var2 == 0) {
            if (var3 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -29, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -29, 1});
            }
         }

         var5 = this.this$0;
         if (var1 == var5.getLeftIndexes(var5.mContext, "_330_setting_info") && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)this.this$0.getCarModelData(var3)});
         }

         var5 = this.this$0;
         if (var1 == var5.getLeftIndexes(var5.mContext, "_330_media_Language_settings")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     if (var3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 0});
                        return;
                     }

                     if (var3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 1});
                        return;
                     }

                     if (var3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 2});
                        return;
                     }

                     if (var3 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 3});
                        return;
                     }

                     if (var3 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 4});
                        return;
                     }

                     if (var3 == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 5});
                        return;
                     }
                  }
               } else {
                  if (var3 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 0});
                     return;
                  }

                  if (var3 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 1});
                     return;
                  }

                  if (var3 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 2});
                     return;
                  }

                  if (var3 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 3});
                     return;
                  }

                  if (var3 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 4});
                     return;
                  }

                  if (var3 == 5) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 5});
                     return;
                  }
               }
            } else {
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 0});
                  return;
               }

               if (var3 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 1});
                  return;
               }

               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 2});
                  return;
               }

               if (var3 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 3});
                  return;
               }

               if (var3 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 4});
                  return;
               }

               if (var3 == 5) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 5});
                  return;
               }
            }
         }

         var5 = this.this$0;
         int var4 = var5.getLeftIndexes(var5.mContext, "_330_unit_selection");
         if (var1 == var4) {
            if (var2 != 0) {
               if (var2 == 1) {
                  var5 = this.this$0;
                  var5.getMsgMgr(var5.mContext).updateSettings(this.this$0.mContext, "C" + this.this$0.getCurrentCarId() + "M" + this.this$0.getEachId() + "L" + var1 + "R" + 1, var4, 1, var3);
                  var5 = this.this$0;
                  var5.getMsgMgr(var5.mContext).uadateTrafficInfo();
                  Intent var6 = new Intent();
                  var6.setAction("CAN_ID330_GX470_UNIT_TYPE");
                  var6.putExtra("TYPE", var3);
                  this.this$0.mContext.sendBroadcast(var6);
               }
            } else {
               var5 = this.this$0;
               var5.getMsgMgr(var5.mContext).updateSettings(this.this$0.mContext, "C" + this.this$0.getCurrentCarId() + "M" + this.this$0.getEachId() + "L" + var1 + "R" + 0, var4, 0, var3);
               var5 = this.this$0;
               var5.getMsgMgr(var5.mContext).updateAirInfo();
               this.this$0.sendAirData(3);
            }
         }

      }
   };
   OnPanelKeyPositionListener panelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 16});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 5});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 4});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 1});
         }

      }
   };
   private int play_pause_state = 0;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachCanId = this.getEachId();
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var2.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var3.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(var1);
      var4.setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
      var4.setOnOriginalCarDeviceBackPressedListener(this.onOriginalCarDeviceBackPressedListener);
      DriverDataPageUiSet var5 = this.getDriverDataPageUiSet(var1);
      var5.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this, var5, var1) {
         final UiMgr this$0;
         final Context val$context;
         final DriverDataPageUiSet val$driverDataPageUiSet;

         {
            this.this$0 = var1;
            this.val$driverDataPageUiSet = var2;
            this.val$context = var3;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 50, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 22, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 80, 0});
            if (var1 == -1) {
               this.val$driverDataPageUiSet.setLeftPosition(this.this$0.getLeftIndexes(this.val$context, "_330_Information_reset"));
               this.val$driverDataPageUiSet.setRightPosition(0);
            }

         }
      });
      AirPageUiSet var6 = this.getAirUiSet(var1);
      var6.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var6.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var6.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerFront);
      var6.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var6.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerHeatRearLeft, this.mOnMinPlusClickListenerHeatRearRight});
      var6.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerRearLeft, this.mOnUpDownClickListenerRearMiddle, this.mOnUpDownClickListenerRearRight});
      var6.getRearArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerRear);
      var6.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(this.panelKeyPositionListener);
      this.getMsgMgr(this.mContext).SendBroadcast();
   }

   private int getCarModelData(int var1) {
      switch (var1) {
         case 0:
            return 32;
         case 1:
            return 33;
         case 2:
            return 48;
         case 3:
            return 49;
         case 4:
            return 64;
         case 5:
            return 65;
         case 6:
            return 80;
         case 7:
            return 81;
         case 8:
            return 96;
         case 9:
            return 97;
         case 10:
            return 98;
         case 11:
            return 99;
         case 12:
            return 100;
         default:
            return 101;
      }
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initSettingsData(Context var1) {
      int var2 = this.eachCanId;
      if (var2 == 2 || var2 == 3 || var2 == 4 || var2 == 6 || var2 == 7 || var2 == 10 || var2 == 11) {
         if (var2 != 2 && var2 != 4 && var2 != 7) {
            MsgMgr var5 = this.getMsgMgr(this.mContext);
            var1 = this.mContext;
            var5.updateSettings(var1, this.L3R0_300, this.getLeftIndexes(var1, "_330_other_settings"), 0, SharePreUtil.getIntValue(this.mContext, this.L3R0_300, 10000), "");
            if (SharePreUtil.getIntValue(this.mContext, this.L3R0_300, 10000) == 1000) {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, 100});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)(SharePreUtil.getIntValue(this.mContext, this.L3R0_300, 10000) / 100)});
            }
         } else {
            if (SharePreUtil.getIntValue(this.mContext, this.L3R0_300, 10000) == 10000) {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, 100});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)(SharePreUtil.getIntValue(this.mContext, this.L3R0_300, 10000) / 100)});
            }

            if (SharePreUtil.getIntValue(this.mContext, this.L3R1_300, 10000) == 10000) {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 100});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)(SharePreUtil.getIntValue(this.mContext, this.L3R1_300, 10000) / 100)});
            }

            MsgMgr var4 = this.getMsgMgr(this.mContext);
            Context var3 = this.mContext;
            var4.updateSettings(var3, this.L3R0_300, this.getLeftIndexes(var3, "_330_other_settings"), 0, SharePreUtil.getIntValue(this.mContext, this.L3R0_300, 10000), "");
            Log.e("DODO", "_________L3R0_300___________" + SharePreUtil.getIntValue(this.mContext, this.L3R0_300, 10000) + "_________");
            var4 = this.getMsgMgr(this.mContext);
            var3 = this.mContext;
            var4.updateSettings(var3, this.L3R1_300, this.getLeftIndexes(var3, "_330_other_settings"), 1, SharePreUtil.getIntValue(this.mContext, this.L3R1_300, 10000), "");
            Log.e("DODO", "_________L3R1_300___________" + SharePreUtil.getIntValue(this.mContext, this.L3R1_300, 10000) + "_________");
         }
      }

      var2 = this.getLeftIndexes(this.mContext, "_330_unit_selection");
      this.getMsgMgr(this.mContext).updateSettings(this.mContext, "C" + this.getCurrentCarId() + "M" + this.getEachId() + "L" + var2 + "R" + 0, var2, 0, SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCarId() + "M" + this.getEachId() + "L" + var2 + "R" + 0, 0));
      this.getMsgMgr(this.mContext).updateSettings(this.mContext, "C" + this.getCurrentCarId() + "M" + this.getEachId() + "L" + var2 + "R" + 1, var2, 1, SharePreUtil.getIntValue(this.mContext, "C" + this.getCurrentCarId() + "M" + this.getEachId() + "L" + var2 + "R" + 1, 0));
   }

   private void initSettingsUi(Context var1) {
      int var2 = this.eachCanId;
      if (var2 == 1 || var2 == 2 || var2 == 4 || var2 == 5 || var2 == 6 || var2 == 7 || var2 == 11) {
         this.getParkPageUiSet(var1).setShowRadar(true);
      }

      var2 = this.eachCanId;
      if (var2 != 2 && var2 != 4 && var2 != 11) {
         this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_driver_data_speed"), 6);
      }

      var2 = this.eachCanId;
      if (var2 != 3 && var2 != 6 && var2 != 7 && var2 != 8 && var2 != 9 && var2 != 10 && var2 != 11) {
         if (var2 != 2 && var2 != 4 && var2 != 11) {
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Instantaneous_fuel_consumption"), 5);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Average_fuel_consumption_after_refueling"), 4);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Mileage_you_can_drive"), 3);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_average_velocity"), 2);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Mileage"), 1);
         } else {
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Instantaneous_fuel_consumption"), 6);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Average_fuel_consumption_after_refueling"), 5);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Mileage_you_can_drive"), 4);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_average_velocity"), 3);
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_Mileage"), 2);
         }
      }

      var2 = this.eachCanId;
      if (var2 != 3 && var2 != 6 && var2 != 7 && var2 != 8 && var2 != 9 && var2 != 10 && var2 != 11) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_330_Information_reset"});
      }

      var2 = this.eachCanId;
      if (var2 != 2 && var2 != 3 && var2 != 4 && var2 != 6 && var2 != 7 && var2 != 10 && var2 != 11) {
         if (var2 != 1 && var2 != 5) {
            this.removeDrivigDateItem(var1, this.getDrivingPageIndexes(var1, "_330_traffic_information"), this.getDrivingItemIndexes(var1, "_330_rotate_speed"), 5);
         } else {
            this.removeDriveDataPagesByHeadTitles(var1, new String[]{"_330_traffic_information"});
         }
      }

      var2 = this.eachCanId;
      if (var2 != 1 && var2 != 3 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 8 && var2 != 11) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_330_Mode_selection"});
      }

      var2 = this.eachCanId;
      if (var2 != 1 && var2 != 3 && var2 != 4 && var2 != 6 && var2 != 8 && var2 != 9 && var2 != 11) {
         this.removeDriveDataPagesByHeadTitles(var1, new String[]{"_330_Multimedia_global_state"});
      }

      var2 = this.eachCanId;
      if (var2 != 1 && var2 != 2 && var2 != 3 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 8 && var2 != 9 && var2 != 11) {
         this.removeMainPrjBtnByName(var1, "original_car_device");
      }

      var2 = this.eachCanId;
      if (var2 != 2 && var2 != 3 && var2 != 4 && var2 != 6 && var2 != 7 && var2 != 10 && var2 != 11) {
         this.removeSettingRightItem(var1, this.getLeftIndexes(var1, "_330_other_settings"), 0, 1);
      }

      var2 = this.eachCanId;
      if (var2 != 2 && var2 != 4 && var2 != 7 && var2 != 11) {
         if (var2 != 3 && var2 != 6 && var2 != 10) {
            this.removeSettingLeftItemByNameList(var1, new String[]{"_330_other_settings"});
         } else {
            this.removeSettingRightItem(var1, this.getLeftIndexes(var1, "_330_other_settings"), 1, 1);
         }
      }

      var2 = this.eachCanId;
      if (var2 != 1 && var2 != 2 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 11) {
         this.removeDriveDataPagesByHeadTitles(var1, new String[]{"_330_Radar_setting_status"});
      }

      var2 = this.eachCanId;
      if (var2 == 3 || var2 == 6 || var2 == 7 || var2 == 8 || var2 == 9 || var2 == 10 || var2 == 11) {
         this.getDriverDataPageUiSet(this.mContext).setHaveBtn(true);
      }

   }

   private void sendAirData(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 0});
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

   protected int getLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initSettingsUi(var1);
      this.initSettingsData(var1);
   }
}
