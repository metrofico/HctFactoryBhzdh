package com.hzbhd.canbus.car._323;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private OnOriginalBottomBtnClickListener mBottom = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           this.this$0.sendSourceMsg(34, 0);
                        }
                     } else {
                        this.this$0.sendSourceMsg(37, 0);
                     }
                  } else {
                     this.this$0.sendSourceMsg(36, 0);
                  }
               } else {
                  this.this$0.sendSourceMsg(38, 0);
               }
            } else {
               this.this$0.sendSourceMsg(35, 0);
            }
         } else if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           if (var1 == 5) {
                              this.this$0.sendSourceMsg(21, 0);
                           }
                        } else {
                           this.this$0.sendSourceMsg(19, 0);
                        }
                     } else {
                        this.this$0.sendSourceMsg(24, 0);
                     }
                  } else {
                     this.this$0.sendSourceMsg(25, 0);
                  }
               } else {
                  this.this$0.sendSourceMsg(20, 0);
               }
            } else {
               this.this$0.sendSourceMsg(22, 0);
            }
         }

      }
   };
   private Context mContext;
   private int mDifferent;
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private MsgMgr mMsgMgr;
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirKeyMsg(9);
      }

      public void onClickRight() {
         this.this$0.sendAirKeyMsg(10);
      }
   };
   OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(-var2 + 7)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 7)});
         }

      }
   };
   OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
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
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
         }

      }
   };
   OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private OnOriginalSongItemClickListener mSongList = new OnOriginalSongItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onSongItemClick(int var1) {
         if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 32, (byte)(var1 + 1)});
         } else if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 23, (byte)(var1 + 1)});
         }

      }

      public void onSongItemLongClick(int var1) {
         if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 33, (byte)(var1 + 1)});
         }

      }
   };
   private OnOriginalTopBtnClickListener mTop = new OnOriginalTopBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickTopBtnItem(int var1) {
         if (!"CD".equals(GeneralOriginalCarDeviceData.cdStatus) && !"Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            if (var1 <= 5) {
               if (var1 == 4) {
                  this.this$0.sendSourceMsg(48, 16);
                  this.this$0.mMsgMgr.turnToRearCdPage();
               } else if (var1 == 5) {
                  this.this$0.sendSourceMsg(48, 5);
               } else {
                  this.this$0.sendSourceMsg(48, var1 + 1);
               }
            }
         } else if (var1 <= 5) {
            if (var1 != 4) {
               if (var1 == 5) {
                  this.this$0.sendSourceMsg(48, 5);
               } else {
                  this.this$0.sendSourceMsg(48, var1 + 1);
               }
            }
         } else if (var1 != 6) {
            if (var1 != 7) {
               if (var1 == 8) {
                  this.this$0.sendSourceMsg(26, 0);
               }
            } else {
               this.this$0.sendSourceMsg(16, 0);
            }
         } else {
            this.this$0.sendSourceMsg(17, 0);
         }

      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirKeyMsg(2);
      }

      public void onClickUp() {
         this.this$0.sendAirKeyMsg(3);
      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirKeyMsg(4);
      }

      public void onClickUp() {
         this.this$0.sendAirKeyMsg(5);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      int var2 = this.getCurrentCarId();
      this.mDifferent = var2;
      if (var2 == 1 || var2 == 18) {
         this.removeDriveData(var1, "mazda_binary_car_set78");
      }

      if (this.mDifferent == 5) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_323_setting_0x60_0"});
      }

      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(var1);
      this.mOriginalCarDevicePageUiSet = var3;
      var3.setOnOriginalTopBtnClickListeners(this.mTop);
      this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mBottom);
      this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mSongList);
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
      var5.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
      AirPageUiSet var6 = this.getAirUiSet(var1);
      var6.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener(this, var6) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var2 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[0][var1];
            var2.hashCode();
            if (!var2.equals("auto")) {
               if (var2.equals("in_out_cycle")) {
                  this.this$0.sendAirKeyMsg(25);
               }
            } else {
               this.this$0.sendAirKeyMsg(21);
            }

         }
      }, new OnAirBtnClickListener(this, var6) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var2 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[1][var1];
            var2.hashCode();
            if (var2.equals("power")) {
               this.this$0.sendAirKeyMsg(1);
            }

         }
      }, new OnAirBtnClickListener(this, var6) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var2 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[2][var1];
            var2.hashCode();
            if (var2.equals("ac")) {
               this.this$0.sendAirKeyMsg(23);
            }

         }
      }, new OnAirBtnClickListener(this, var6) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var2 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[3][var1];
            var2.hashCode();
            if (!var2.equals("rear_defog")) {
               if (var2.equals("dual")) {
                  this.this$0.sendAirKeyMsg(16);
               }
            } else {
               this.this$0.sendAirKeyMsg(20);
            }

         }
      }});
      var6.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, null, this.onUpDownClickListenerRight});
      var6.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -1231850611:
                  if (var4.equals("_323_setting_0x60_1")) {
                     var5 = 0;
                  }
                  break;
               case 599830378:
                  if (var4.equals("_323_amplifier_setting_1")) {
                     var5 = 1;
                  }
                  break;
               case 599830380:
                  if (var4.equals("_323_amplifier_setting_3")) {
                     var5 = 2;
                  }
                  break;
               case 599830382:
                  if (var4.equals("_323_amplifier_setting_5")) {
                     var5 = 3;
                  }
                  break;
               case 599830383:
                  if (var4.equals("_323_amplifier_setting_6")) {
                     var5 = 4;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -29, (byte)var3});
                  break;
               case 1:
                  UiMgr var6 = this.this$0;
                  if (var6.getMsgMgr(var6.mContext).get0x31Data()[4] == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                  }
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte)var3});
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendAirKeyMsg(int var1) {
      this.mHandler.postDelayed(new Runnable(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 1});
         }
      }, 30L);
      this.mHandler.postDelayed(new Runnable(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 0});
         }
      }, 30L);
   }

   private void sendSourceMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }
}
