package com.hzbhd.canbus.car._288;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
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
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_288_LANGUAGE = "share_288_language";
   AirPageUiSet mAirPageUiSet;
   AmplifierPageUiSet mAmplifierPageUiSet;
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
         } else if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus) || "Rear CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
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
   private OnAirBtnClickListener mBtnButtomClick = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirMsg(36);
               }
            } else {
               this.this$0.sendAirMsg(18);
            }
         } else {
            this.this$0.sendAirMsg(16);
         }

      }
   };
   private OnAirBtnClickListener mBtnLeftClick = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirMsg(21);
      }
   };
   private OnAirBtnClickListener mBtnRightClick = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirMsg(20);
      }
   };
   private OnAirBtnClickListener mBtnTopClick = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirMsg(23);
               }
            } else {
               this.this$0.sendAirMsg(25);
            }
         } else {
            this.this$0.sendAirMsg(1);
         }

      }
   };
   private Context mContext;
   private Handler mHandler;
   private HandlerThread mHandlerThread = new HandlerThread(this, "MyApplication") {
      final UiMgr this$0;

      {
         this.this$0 = var1;
         this.start();
      }
   };
   private OnSettingItemSelectListener mItemSelect = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var2 != 0) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 5) {
                        this.this$0.sendSettingMsg(9, var3);
                        SharePreUtil.setIntValue(this.this$0.mContext, "_288_amplifier_round", var3);
                        this.this$0.mMsgMgr.updateAmplifier();
                     }
                  } else {
                     this.this$0.sendSettingMsg(10, var3);
                     SharePreUtil.setIntValue(this.this$0.mContext, "_288_amplifier_mute", var3);
                     this.this$0.mMsgMgr.updateAmplifier();
                  }
               } else {
                  this.this$0.sendSettingMsg(8, var3);
                  SharePreUtil.setIntValue(this.this$0.mContext, "_288_amplifier_on_off", var3);
                  this.this$0.mMsgMgr.updateAmplifier();
               }
            } else {
               this.this$0.sendSettingMsg(12, var3);
            }
         } else {
            this.this$0.sendSettingMsg(3, var3);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMsg(2);
      }

      public void onClickUp() {
         this.this$0.sendAirMsg(3);
      }
   };
   private MsgMgr mMsgMgr;
   OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private OnAmplifierPositionListener mPosition = new OnAmplifierPositionListener(this) {
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
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(7 - var2)});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMsg(4);
      }

      public void onClickUp() {
         this.this$0.sendAirMsg(5);
      }
   };
   private OnAmplifierSeekBarListener mSeekBar = new OnAmplifierSeekBarListener(this) {
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
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
         }

      }
   };
   private OnSettingPageStatusListener mSettingPage = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (this.this$0.mMsgMgr != null) {
            this.this$0.mMsgMgr.updateSettingData();
         }

      }
   };
   SettingPageUiSet mSettingPageUiSet;
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
         if (!"CD".equals(GeneralOriginalCarDeviceData.cdStatus) && !"Rear CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
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
            if (var1 == 4) {
               this.this$0.sendSourceMsg(48, 16);
               this.this$0.mMsgMgr.turnToRearCdPage();
            } else if (var1 == 5) {
               this.this$0.sendSourceMsg(48, 5);
            } else {
               this.this$0.sendSourceMsg(48, var1 + 1);
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
   private OnAirWindSpeedUpDownClickListener mWindLevChange = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirMsg(9);
      }

      public void onClickRight() {
         this.this$0.sendAirMsg(10);
      }
   };

   public UiMgr(Context var1) {
      this.mHandler = new Handler(this, this.mHandlerThread.getLooper()) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            if (var1.what == 1 && var1.obj instanceof byte[]) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };

      try {
         this.mContext = var1;
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
         this.mAmplifierPageUiSet = this.getAmplifierPageUiSet(this.mContext);
         this.mSettingPageUiSet = this.getSettingUiSet(this.mContext);
         this.mAirPageUiSet = this.getAirUiSet(this.mContext);
         this.mOriginalCarDevicePageUiSet = this.getOriginalCarDevicePageUiSet(this.mContext);
         this.mAirPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mBtnTopClick, this.mBtnLeftClick, this.mBtnRightClick, this.mBtnButtomClick});
         this.mAirPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mWindLevChange);
         this.mAirPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeft, null, this.mRight});
         this.mSettingPageUiSet.setOnSettingItemSelectListener(this.mItemSelect);
         this.mSettingPageUiSet.setOnSettingPageStatusListener(this.mSettingPage);
         this.mAmplifierPageUiSet.setOnAmplifierSeekBarListener(this.mSeekBar);
         this.mAmplifierPageUiSet.setOnAmplifierPositionListener(this.mPosition);
         this.mOriginalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mTop);
         this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mBottom);
         this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mSongList);
      } catch (Exception var2) {
         var2.printStackTrace();
         Log.d("cwh", "错误类型： " + var2);
      }

   }

   private void sendAirMsg(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      Message var3 = new Message();
      var3.what = 1;
      var3.obj = new byte[]{22, -32, var2, 0};
      this.mHandler.sendMessageDelayed(var3, 100L);
   }

   private void sendSettingMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   private void sendSourceMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }
}
