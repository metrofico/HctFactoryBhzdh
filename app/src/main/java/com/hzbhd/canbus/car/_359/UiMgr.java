package com.hzbhd.canbus.car._359;

import android.content.Context;
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
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private int EachID;
   private int RADIO_PLAY_PAUSE = 0;
   private AirPageUiSet airPageUiSet;
   private int fastBack = 0;
   private int fastForward = 0;
   private Context mContext;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 3) {
               this.this$0.sendAirData(25);
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
         if (var1 == 0) {
            this.this$0.sendAirData(40);
         }

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
               if (var1 == 2) {
                  this.this$0.sendAirData(21);
               }
            } else {
               this.this$0.sendAirData(1);
            }
         } else {
            this.this$0.sendAirData(23);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirData(20);
         }

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
   private MsgMgr msgMgr;
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
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         var4.getMsgMgr(var4.mContext).updateSetting(var1, var2, var3);
         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_330_amplifier_info")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 3) {
                     if (var3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                     }
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

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_330_Mode_selection") && var2 == 0) {
            if (var3 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -29, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -29, 1});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_330_setting_info") && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)this.this$0.getCarModelData(var3)});
         }

      }
   };
   private int play_pause_state = 0;
   private SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var4.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(var1);
      var5.setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
      var5.setOnOriginalCarDeviceBackPressedListener(this.onOriginalCarDeviceBackPressedListener);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerFront);
      var3.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
   }

   private int getCarModelData(int var1) {
      return var1 == 0 ? 32 : 33;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void sendAirData(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -32, var2, 0});
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
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
