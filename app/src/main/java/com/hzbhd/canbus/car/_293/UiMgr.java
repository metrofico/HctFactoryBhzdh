package com.hzbhd.canbus.car._293;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private AirPageUiSet mAirPageUiSet;
   private AmplifierPageUiSet mAmplifierPageUiSet;
   private OnAirBtnClickListener mBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.senAirMsg(20);
                  }
               } else {
                  this.this$0.senAirMsg(19);
               }
            } else {
               this.this$0.senAirMsg(42);
            }
         } else {
            this.this$0.senAirMsg(25);
         }

      }
   };
   private Context mContext;
   private OnAirBtnClickListener mLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
            this.this$0.senAirMsg(13);
         } else {
            this.this$0.senAirMsg(11);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mLeftTem = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.senAirMsg(2);
      }

      public void onClickUp() {
         this.this$0.senAirMsg(3);
      }
   };
   private MsgMgr mMsgMgr;
   private OnAmplifierPositionListener mPosition = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         if (this.this$0.mMsgMgr.isNeedCanCtrlAmplifier) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte)var2});
            }
         }

      }
   };
   private OnAirBtnClickListener mRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
            this.this$0.senAirMsg(11);
         } else {
            this.this$0.senAirMsg(13);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mRightTem = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.senAirMsg(4);
      }

      public void onClickUp() {
         this.this$0.senAirMsg(5);
      }
   };
   private OnAmplifierSeekBarListener mSeekBar = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         if (this.this$0.mMsgMgr.isNeedCanCtrlAmplifier) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -96, 0, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, (byte)(var2 - 9)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -96, 5, (byte)(var2 - 9)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, (byte)(var2 - 9)});
            }
         }

      }
   };
   private SettingPageUiSet mSettingPageUiSet;
   private OnAirBtnClickListener mTop = new OnAirBtnClickListener(this) {
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
                        this.this$0.senAirMsg(7);
                     }
                  } else {
                     this.this$0.senAirMsg(8);
                  }
               } else {
                  this.this$0.senAirMsg(24);
               }
            } else {
               this.this$0.senAirMsg(23);
            }
         } else {
            this.this$0.senAirMsg(1);
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener mWindLv = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.senAirMsg(9);
      }

      public void onClickRight() {
         this.this$0.senAirMsg(10);
      }
   };
   private OnSettingItemSeekbarSelectListener seekBar = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte)var3});
      }
   };

   public UiMgr(Context var1) {
      try {
         this.mContext = var1;
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
         this.mAirPageUiSet = this.getAirUiSet(this.mContext);
         this.mSettingPageUiSet = this.getSettingUiSet(this.mContext);
         AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(this.mContext);
         this.mAmplifierPageUiSet = var3;
         var3.setOnAmplifierPositionListener(this.mPosition);
         this.mAmplifierPageUiSet.setOnAmplifierSeekBarListener(this.mSeekBar);
         this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.seekBar);
      } catch (Exception var2) {
         var2.printStackTrace();
         Log.d("cwh", "error = " + var2);
      }

   }

   private void senAirMsg(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 0});
   }
}
