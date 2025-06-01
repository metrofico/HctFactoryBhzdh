package com.hzbhd.canbus.car._335;

import android.content.Context;
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
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class UiMgr extends AbstractUiMgr {
   public static int UsbPlayPauseState;
   private int RADIO_PLAY_PAUSE = 0;
   int differentId;
   int eachId;
   private int fastBack = 0;
   private int fastForward = 0;
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
               this.this$0.sendAirData0xE0(1);
            }
         } else {
            this.this$0.sendAirData0xE0(16);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
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
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendAirData0xE0(20);
                  }
               } else {
                  this.this$0.sendAirData0xE0(21);
               }
            } else {
               this.this$0.sendAirData0xE0(25);
            }
         } else {
            this.this$0.sendAirData0xE0(23);
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
   DecimalFormat noDecimal = new DecimalFormat("00000");
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.sendAirData0xE0(36);
      }

      public void onRightSeatClick() {
         this.this$0.sendAirData0xE0(36);
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData0xE0(2);
      }

      public void onClickUp() {
         this.this$0.sendAirData0xE0(3);
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData0xE0(4);
      }

      public void onClickUp() {
         this.this$0.sendAirData0xE0(5);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirData0xE0(9);
      }

      public void onClickRight() {
         this.this$0.sendAirData0xE0(10);
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
               this.this$0.sendAmplifierInfo0x84(2, var2 + 7);
            }
         } else {
            this.this$0.sendAmplifierInfo0x84(1, -var2 + 7);
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
                     this.this$0.sendAmplifierInfo0x84(4, var2 + 2);
                  }
               } else {
                  this.this$0.sendAmplifierInfo0x84(6, var2 + 2);
               }
            } else {
               this.this$0.sendAmplifierInfo0x84(5, var2 + 2);
            }
         } else {
            this.this$0.sendAmplifierInfo0x84(7, var2);
         }

      }
   };
   OnOriginalBottomBtnClickListener onOriginalCarDeviceBackPressedListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.getMsgMgr(var2.mContext);
         String var4 = MsgMgr.mediaTag;
         UiMgr var3 = this.this$0;
         Objects.requireNonNull(var3.getMsgMgr(var3.mContext));
         if (var4.equals("USB")) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     this.this$0.sendUsbInfo0xC5(4);
                  }
               } else if (UiMgr.UsbPlayPauseState == 0) {
                  UiMgr.UsbPlayPauseState = 1;
                  this.this$0.sendUsbInfo0xC5(1);
               } else {
                  UiMgr.UsbPlayPauseState = 0;
                  this.this$0.sendUsbInfo0xC5(2);
               }
            } else {
               this.this$0.sendUsbInfo0xC5(3);
            }
         }

         var2 = this.this$0;
         var2.getMsgMgr(var2.mContext);
         var4 = MsgMgr.mediaTag;
         var3 = this.this$0;
         Objects.requireNonNull(var3.getMsgMgr(var3.mContext));
         if (var4.equals("CD/DVD")) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           if (var1 == 5) {
                              this.this$0.sendMediaInfo0x84(21, 0);
                           }
                        } else {
                           this.this$0.sendMediaInfo0x84(19, 0);
                        }
                     } else if (this.this$0.fastForward == 0) {
                        this.this$0.fastForward = 1;
                        this.this$0.sendMediaInfo0x84(24, 1);
                     } else {
                        this.this$0.fastForward = 0;
                        this.this$0.sendMediaInfo0x84(24, 0);
                     }
                  } else if (this.this$0.fastBack == 0) {
                     this.this$0.fastBack = 1;
                     this.this$0.sendMediaInfo0x84(25, 1);
                  } else {
                     this.this$0.fastBack = 0;
                     this.this$0.sendMediaInfo0x84(25, 0);
                  }
               } else {
                  this.this$0.sendMediaInfo0x84(20, 0);
               }
            } else {
               this.this$0.sendMediaInfo0x84(22, 0);
            }
         }

         var2 = this.this$0;
         var2.getMsgMgr(var2.mContext);
         String var5 = MsgMgr.mediaTag;
         var2 = this.this$0;
         Objects.requireNonNull(var2.getMsgMgr(var2.mContext));
         if (var5.equals("RADIO0")) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           this.this$0.sendMediaInfo0x84(37, 0);
                        }
                     } else {
                        this.this$0.sendMediaInfo0x84(34, 0);
                     }
                  } else if (this.this$0.RADIO_PLAY_PAUSE == 0) {
                     this.this$0.RADIO_PLAY_PAUSE = 1;
                     this.this$0.sendMediaInfo0x84(36, 1);
                  } else {
                     this.this$0.RADIO_PLAY_PAUSE = 0;
                     this.this$0.sendMediaInfo0x84(36, 0);
                  }
               } else {
                  this.this$0.sendMediaInfo0x84(35, 0);
               }
            } else {
               this.this$0.sendMediaInfo0x84(38, 0);
            }
         }

      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var3.getSettingLeftIndexes(var3.mContext, "_335_cd_data") == var1) {
            if (var2 != 0) {
               if (var2 == 1) {
                  this.this$0.sendMediaInfo0x84(16, 0);
               }
            } else {
               this.this$0.sendMediaInfo0x84(17, 0);
            }
         }

      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_335_setting_data_speed")) {
            if (var2 != 1) {
               if (var2 == 3) {
                  this.this$0.sendSpeedInfo0x89(var3);
               }
            } else {
               this.this$0.sendSpeedInfo0x8A(var3);
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
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_330_amplifier_info")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 3) {
                     if (var2 == 5) {
                        if (var3 == 0) {
                           this.this$0.sendAmplifierInfo0x84(12, 0);
                        } else if (var3 == 1) {
                           this.this$0.sendAmplifierInfo0x84(12, 1);
                        } else if (var3 == 2) {
                           this.this$0.sendAmplifierInfo0x84(12, 2);
                        } else if (var3 == 3) {
                           this.this$0.sendAmplifierInfo0x84(12, 3);
                        }
                     }
                  } else if (var3 == 0) {
                     this.this$0.sendAmplifierInfo0x84(3, 1);
                  } else {
                     this.this$0.sendAmplifierInfo0x84(3, 8);
                  }
               } else if (var3 == 0) {
                  this.this$0.sendAmplifierInfo0x84(10, 0);
               } else {
                  this.this$0.sendAmplifierInfo0x84(10, 1);
               }
            } else if (var3 == 0) {
               this.this$0.sendAmplifierInfo0x84(8, 0);
            } else {
               this.this$0.sendAmplifierInfo0x84(8, 1);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_330_Mode_selection") && var2 == 0) {
            if (var3 == 0) {
               this.this$0.sendWheelKeyControlInfo0xE3(0);
            } else {
               this.this$0.sendWheelKeyControlInfo0xE3(1);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_335_setting_car_data0") && var2 == 0) {
            this.this$0.sendCarModelInfo0xE2(var3);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var2.setOnSettingItemClickListener(this.onSettingItemClickListener);
      this.getOriginalCarDevicePageUiSet(var1).setOnOriginalBottomBtnClickListeners(this.onOriginalCarDeviceBackPressedListener);
      AirPageUiSet var4 = this.getAirUiSet(this.mContext);
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var4.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var3.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
   }

   private int getCarModelData(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  return var1 != 4 ? 81 : 80;
               } else {
                  return 49;
               }
            } else {
               return 48;
            }
         } else {
            return 33;
         }
      } else {
         return 32;
      }
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initUi() {
      if (this.eachId != 2) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_335_setting_data_speed"});
      }

      if (this.eachId == 2) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_330_Mode_selection"});
      }

   }

   private void sendAirData0xE0(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 0});
   }

   private void sendAmplifierInfo0x84(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   private void sendCarModelInfo0xE2(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)this.getCarModelData(var1)});
   }

   private void sendMediaInfo0x84(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   private void sendSpeedInfo0x89(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)Integer.parseInt(this.noDecimal.format((double)var1 * 0.01))});
   }

   private void sendSpeedInfo0x8A(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)Integer.parseInt(this.noDecimal.format((double)var1 * 0.01))});
   }

   private void sendUsbInfo0xC5(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte)var1, 0});
   }

   private void sendWheelKeyControlInfo0xE3(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -29, (byte)var1});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var5.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var5.get(var4)).getTitleSrn().equals(var2)) {
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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
   }
}
