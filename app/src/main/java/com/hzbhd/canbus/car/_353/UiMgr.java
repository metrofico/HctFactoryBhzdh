package com.hzbhd.canbus.car._353;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int eachId;
   int i = 1;
   Context mContext;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -32, 16, 1});
                  }
               } else {
                  if (this.this$0.i == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -32, 35, 1});
                     this.this$0.i = 1;
                     return;
                  }

                  if (this.this$0.i == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -32, 34, 1});
                     this.this$0.i = 0;
                     return;
                  }
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -32, 36, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 19, 1});
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
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 20, 1});
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
                  CanbusMsgSender.sendMsg(new byte[]{22, -32, 21, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -32, 1, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 23, 1});
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 39, 1});
         }

      }
   };
   private MsgMgr msgMgr;
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -32, 2, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -32, 3, 1});
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -32, 4, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -32, 5, 1});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      GeneralTireData.isHaveSpareTire = false;
      if (this.eachId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 0});
      }

      if (this.eachId == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
      }

      if (this.eachId == 3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 2});
      }

      if (this.eachId == 4) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 3});
      }

      if (this.eachId == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 4});
      }

      if (this.eachId == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 5});
      }

      if (this.eachId == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 6});
      }

      if (this.eachId == 8) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 7});
      }

      if (this.eachId == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 8});
      }

      if (this.eachId == 10) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 9});
      }

      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var2) {
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
               case -1232845427:
                  if (var4.equals("_353_setting_2_6")) {
                     var5 = 0;
                  }
                  break;
               case -1232845425:
                  if (var4.equals("_353_setting_2_8")) {
                     var5 = 1;
                  }
                  break;
               case -999746299:
                  if (var4.equals("_330_Rotating_Speed_report")) {
                     var5 = 2;
                  }
                  break;
               case 1956080709:
                  if (var4.equals("_330_car_Speed_report")) {
                     var5 = 3;
                  }
            }

            UiMgr var6;
            switch (var5) {
               case 0:
                  var6 = this.this$0;
                  if (var6.getMsgMgr(var6.mContext).speedVolume > var3) {
                     this.this$0.sendAmplifierMessage(38, 49);
                  }

                  var6 = this.this$0;
                  if (var6.getMsgMgr(var6.mContext).speedVolume < var3) {
                     this.this$0.sendAmplifierMessage(38, 33);
                  }
                  break;
               case 1:
                  Log.d("lai", "onClickItem: " + var3);
                  var6 = this.this$0;
                  var1 = var6.getMsgMgr(var6.mContext).RoundVolume;
                  var2 = var3 + 5;
                  if (var1 > var2) {
                     this.this$0.sendAmplifierMessage(40, 49);
                  }

                  var6 = this.this$0;
                  if (var6.getMsgMgr(var6.mContext).RoundVolume < var2) {
                     this.this$0.sendAmplifierMessage(40, 33);
                  }
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var3});
            }

         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var5.hashCode();
            if (!var5.equals("_353_setting_2_7")) {
               if (var5.equals("_353_setting_2_9")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)var3});
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(this.mContext);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 9, 1});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 10, 1});
         }
      });
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(this.mContext);
      var4.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            UiMgr var4;
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 == 3) {
                     var4 = this.this$0;
                     if (var4.getMsgMgr(var4.mContext).bandTreble > var2) {
                        this.this$0.sendAmplifierMessage(35, 49);
                     }

                     var4 = this.this$0;
                     if (var4.getMsgMgr(var4.mContext).bandTreble < var2) {
                        this.this$0.sendAmplifierMessage(35, 33);
                     }
                  }
               } else {
                  var4 = this.this$0;
                  if (var4.getMsgMgr(var4.mContext).bandBass > var2) {
                     this.this$0.sendAmplifierMessage(34, 49);
                  }

                  var4 = this.this$0;
                  if (var4.getMsgMgr(var4.mContext).bandBass < var2) {
                     this.this$0.sendAmplifierMessage(34, 33);
                  }
               }
            } else {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).volume > var2) {
                  this.this$0.sendAmplifierMessage(33, 49);
               }

               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).volume < var2) {
                  this.this$0.sendAmplifierMessage(33, 33);
               }
            }

         }
      });
      var4.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            UiMgr var4;
            if (var3 != 1) {
               if (var3 == 2) {
                  var4 = this.this$0;
                  if (var4.getMsgMgr(var4.mContext).frontRear > var2) {
                     this.this$0.sendAmplifierMessage(37, 33);
                  }

                  var4 = this.this$0;
                  if (var4.getMsgMgr(var4.mContext).frontRear < var2) {
                     this.this$0.sendAmplifierMessage(37, 49);
                  }

                  Log.d("lai", "FRONT_REAR_position: " + var2);
               }
            } else {
               Log.d("lai", "LEFT_RIGHT_position: " + var2);
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).leftRight > var2) {
                  this.this$0.sendAmplifierMessage(36, 49);
               }

               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).leftRight < var2) {
                  this.this$0.sendAmplifierMessage(36, 33);
               }
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(this.mContext);
      }

      return this.msgMgr;
   }

   private void sendAmplifierMessage(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
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

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void sendMediaPalyInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void sendPhoneNumber() {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 6, 1, 84, 101, 108, 32, 79, 102, 102});
   }

   public void sendPhoneNumber(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendRadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void sendSourceInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
