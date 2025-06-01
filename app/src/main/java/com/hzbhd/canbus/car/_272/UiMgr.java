package com.hzbhd.canbus.car._272;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private int mCurrentAutoStore = 0;
   private int mCurrentBand = 0;
   private int mCurrentPlayStatusCd = 0;
   private int mCurrentPlayStatusUsb = 0;
   private int mCurrentPresetScan = 0;
   private int mCurrentRptCd = 0;
   private int mCurrentRptUsb = 0;
   private int mCurrentSRdmCd = 0;
   private int mCurrentSRdmUsb = 0;
   private int mCurrentScanCd = 0;
   private int mCurrentScanStutas = 0;
   private int mCurrentScanUsb = 0;
   OnAirBtnClickListener onAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendAirCommand(10);
                  }
               } else {
                  this.this$0.sendAirCommand(15);
               }
            } else {
               this.this$0.sendAirCommand(18);
            }
         } else {
            this.this$0.sendAirCommand(16);
         }

      }
   };
   OnAirBtnClickListener onAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCommand(20);
         } else if (var1 == 1) {
            this.this$0.sendAirCommand(19);
         }

      }
   };
   OnAirBtnClickListener onAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCommand(8);
         } else if (var1 == 1) {
            this.this$0.sendAirCommand(9);
         }

      }
   };
   OnAirBtnClickListener onAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
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
                        this.this$0.sendAirCommand(6);
                     }
                  } else {
                     this.this$0.sendAirCommand(12);
                  }
               } else {
                  this.this$0.sendAirCommand(7);
               }
            } else {
               this.this$0.sendAirCommand(17);
            }
         } else {
            this.this$0.sendAirCommand(5);
         }

      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(2);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(1);
      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(4);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(3);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -8, 1, 0});
      MsgMgr var3 = (MsgMgr)MsgMgrFactory.getCanMsgMgr(this.mContext);
      this.getSettingUiSet(this.mContext).setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -16, 7, (byte)var3});
               } else if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -16, 8, (byte)var3});
               } else if (var2 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -16, 9, (byte)var3});
               }
            }

         }
      });
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerTop, this.onAirBtnClickListenerLeft, this.onAirBtnClickListenerRight, this.onAirBtnClickListenerBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, null, this.onUpDownClickListenerRight});
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirCommand(11);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirCommand(11);
         }
      });
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            if (GeneralAirData.front_wind_level != 0) {
               this.this$0.sendAirCommand(14);
            }

         }

         public void onClickRight() {
            if (GeneralAirData.front_wind_level < 7) {
               this.this$0.sendAirCommand(13);
            }

         }
      });
      OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var5.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickTopBtnItem(int var1) {
            if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
               this.this$0.sendSourceMsg(var1 + 1);
            } else if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
               if (var1 <= 6) {
                  this.this$0.sendSourceMsg(var1 + 1);
               } else {
                  switch (var1) {
                     case 7:
                        if (this.this$0.mCurrentScanCd == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, 1});
                           this.this$0.mCurrentScanCd = 1;
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, 0});
                           this.this$0.mCurrentScanCd = 0;
                        }
                        break;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, 2});
                        break;
                     case 9:
                        if (this.this$0.mCurrentRptCd == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 1});
                           this.this$0.mCurrentRptCd = 1;
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 0});
                           this.this$0.mCurrentRptCd = 0;
                        }
                        break;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 2});
                        break;
                     case 11:
                        if (this.this$0.mCurrentSRdmCd == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 1});
                           this.this$0.mCurrentSRdmCd = 1;
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 0});
                           this.this$0.mCurrentSRdmCd = 0;
                        }
                        break;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 2});
                  }
               }
            } else if ("USB".equals(GeneralOriginalCarDeviceData.cdStatus)) {
               if (var1 < 7) {
                  this.this$0.sendSourceMsg(var1 + 1);
               } else {
                  switch (var1) {
                     case 7:
                        if (this.this$0.mCurrentScanUsb == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 4, 1});
                           this.this$0.mCurrentScanUsb = 1;
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 4, 0});
                           this.this$0.mCurrentScanUsb = 0;
                        }
                        break;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 4, 2});
                        break;
                     case 9:
                        if (this.this$0.mCurrentRptUsb == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 3, 1});
                           this.this$0.mCurrentRptUsb = 1;
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 3, 0});
                           this.this$0.mCurrentRptUsb = 0;
                        }
                        break;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 3, 2});
                        break;
                     case 11:
                        if (this.this$0.mCurrentSRdmUsb == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 5, 1});
                           this.this$0.mCurrentSRdmUsb = 1;
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 5, 0});
                           this.this$0.mCurrentSRdmUsb = 0;
                        }
                        break;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 5, 2});
                  }
               }
            } else if ("AUX/Navi".equals(GeneralOriginalCarDeviceData.cdStatus)) {
               if (var1 == 4) {
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131770003), 0).show();
               }

               this.this$0.sendSourceMsg(var1 + 1);
            } else {
               this.this$0.sendSourceMsg(var1 + 1);
            }

         }
      });
      var5.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this, var5, var3) {
         final UiMgr this$0;
         final MsgMgr val$msgMgr;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
            this.val$msgMgr = var3;
         }

         public void onClickBottomBtnItem(int var1) {
            if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
               switch (var1) {
                  case 0:
                     if (this.this$0.mCurrentBand == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 1});
                        this.this$0.toastMsg(2131760176);
                        this.this$0.mCurrentBand = 1;
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 0});
                        this.this$0.toastMsg(2131760186);
                        this.this$0.mCurrentBand = 0;
                     }
                     break;
                  case 1:
                     if (this.this$0.mCurrentScanStutas == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 1});
                        this.this$0.toastMsg(2131760187);
                        this.this$0.mCurrentScanStutas = 1;
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 0});
                        this.this$0.toastMsg(2131760188);
                        this.this$0.mCurrentScanStutas = 0;
                     }
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 1});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 1});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 0});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 0});
                     break;
                  case 6:
                     if (this.this$0.mCurrentPresetScan == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 6, 1});
                        this.this$0.toastMsg(2131760189);
                        this.this$0.mCurrentPresetScan = 1;
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 6, 0});
                        this.this$0.toastMsg(2131760190);
                        this.this$0.mCurrentPresetScan = 0;
                     }
                     break;
                  case 7:
                     if (this.this$0.mCurrentAutoStore == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 7, 1});
                        this.this$0.toastMsg(2131760191);
                        this.this$0.mCurrentAutoStore = 1;
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 7, 0});
                        this.this$0.toastMsg(2131760192);
                        this.this$0.mCurrentAutoStore = 0;
                     }
               }
            } else {
               Bundle var2;
               if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                  switch (var1) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
                        break;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
                        break;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
                        break;
                     case 3:
                        if (this.this$0.mCurrentPlayStatusCd == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 1, 0});
                           this.val$originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"prev_disc", "left", "up", "pause", "down", "right", "next_disc"});
                           this.this$0.mCurrentPlayStatusCd = 1;
                           var2 = new Bundle();
                           var2.putBoolean("bundle_key_orinal_init_view", true);
                           this.val$msgMgr.updateOriginalCarDevice(var2);
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 2, 0});
                           this.val$originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"prev_disc", "left", "up", "play", "down", "right", "next_disc"});
                           this.this$0.mCurrentPlayStatusCd = 0;
                           var2 = new Bundle();
                           var2.putBoolean("bundle_key_orinal_init_view", true);
                           this.val$msgMgr.updateOriginalCarDevice(var2);
                        }
                        break;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
                        break;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
                        break;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
                  }
               } else if ("USB".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                  switch (var1) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 6, 1});
                        break;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 7, 1});
                        break;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 8, 1});
                        break;
                     case 3:
                        if (this.this$0.mCurrentPlayStatusUsb == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 1, 0});
                           this.val$originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"prev_disc", "left", "up", "pause", "down", "right", "next_disc"});
                           this.this$0.mCurrentPlayStatusUsb = 1;
                           var2 = new Bundle();
                           var2.putBoolean("bundle_key_orinal_init_view", true);
                           this.val$msgMgr.updateOriginalCarDevice(var2);
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -12, 2, 0});
                           this.val$originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"prev_disc", "left", "up", "play", "down", "right", "next_disc"});
                           this.this$0.mCurrentPlayStatusUsb = 0;
                           var2 = new Bundle();
                           var2.putBoolean("bundle_key_orinal_init_view", true);
                           this.val$msgMgr.updateOriginalCarDevice(var2);
                        }
                        break;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 8, 0});
                        break;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 7, 0});
                        break;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 6, 0});
                  }
               }
            }

         }
      });
      var5.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener(this, var3) {
         final UiMgr this$0;
         final MsgMgr val$msgMgr;

         {
            this.this$0 = var1;
            this.val$msgMgr = var2;
         }

         public void onSongItemClick(int var1) {
            if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
               CanbusMsgSender.sendMsg(new byte[]{22, -15, 8, (byte)this.val$msgMgr.currentPtore});
            }

         }

         public void onSongItemLongClick(int var1) {
            if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
               CanbusMsgSender.sendMsg(new byte[]{22, -15, 4, (byte)this.val$msgMgr.currentPtore});
            }

         }
      });
      var5.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onBackPressed() {
            this.this$0.sendSourceMsg(5);
         }
      });
      var5.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
         }
      });
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(this.mContext);
      var4.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Log.i("ljq", "position: " + var1.name() + ": " + var2);
            if (var1 == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 2, (byte)(var2 + 1 + 15)});
            } else if (var1 == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 3, (byte)(var2 + 1 + 15)});
            }

         }
      });
      var4.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierBand.BASS) {
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 4, (byte)Math.abs(var2 + 1)});
            } else if (var1 == AmplifierActivity.AmplifierBand.MIDDLE) {
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 5, (byte)Math.abs(var2 + 1)});
            } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE) {
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 6, (byte)Math.abs(var2 + 1)});
            }

         }
      });
   }

   private void sendAirBtnCtrl(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, this.setWidgetData(var2)});
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 0});
   }

   private void sendSourceMsg(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -13, 1, (byte)var1});
   }

   private byte setWidgetData(boolean var1) {
      return (byte)(var1 ^ 1);
   }

   private void toastMsg(int var1) {
      Toast.makeText(this.mContext, var1, 0).show();
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
