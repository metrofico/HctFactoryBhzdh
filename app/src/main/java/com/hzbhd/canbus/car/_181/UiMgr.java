package com.hzbhd.canbus.car._181;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private int eachId;
   Context mContext;
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendAirFrontMsg((byte)18);
            }
         } else {
            this.this$0.SendAirFrontMsg((byte)16);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendAirFrontMsg((byte)19);
            }
         } else {
            this.this$0.SendAirFrontMsg((byte)17);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendAirRearMsg((byte)16);
            }
         } else {
            this.this$0.SendAirRearMsg((byte)18);
         }

      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.SendAirFrontMsg((byte)36);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.SendAirFrontMsg((byte)37);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.SendAirFrontMsg((byte)34);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.SendAirFrontMsg((byte)35);
      }
   };
   private MsgMgr msgMgr;
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendAirRearMsg((byte)31);
      }

      public void onClickUp() {
         this.this$0.SendAirRearMsg((byte)30);
      }
   };
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendAirFrontMsg((byte)31);
      }

      public void onClickUp() {
         this.this$0.SendAirFrontMsg((byte)30);
      }
   };
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendAirFrontMsg((byte)33);
      }

      public void onClickUp() {
         this.this$0.SendAirFrontMsg((byte)32);
      }
   };
   OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
         this.this$0.activeRequest(55);
      }

      public void destroy() {
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
               MyLog.e("LEFT_RIGHT", var2 + "");
               this.this$0.sendAmplifierInfo(5, var2 + 10);
            }
         } else {
            MyLog.e("FRONT_REAR", var2 + "");
            this.this$0.sendAmplifierInfo(6, -var2 + 10);
         }

      }
   };
   OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         String var4 = "0x" + var2;
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     this.this$0.sendAmplifierInfo(4, Integer.parseInt(var4.replaceAll("^0[x|X]", ""), 16));
                  }
               } else {
                  this.this$0.sendAmplifierInfo(3, Integer.parseInt(var4.replaceAll("^0[x|X]", ""), 16));
               }
            } else {
               this.this$0.sendAmplifierInfo(2, Integer.parseInt(var4.replaceAll("^0[x|X]", ""), 16));
            }
         } else {
            this.this$0.sendAmplifierInfo(1, var2);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.SendAirFrontMsg((byte)29);
         }

         public void onClickRight() {
            this.this$0.SendAirFrontMsg((byte)28);
         }
      });
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         int a;
         final UiMgr this$0;

         {
            this.this$0 = var1;
            this.a = 0;
         }

         public void onLeftSeatClick() {
            int var1 = this.a;
            if (var1 == 0) {
               this.this$0.SendAirFrontMsg((byte)21);
               this.a = 1;
            } else if (var1 == 1) {
               this.this$0.SendAirFrontMsg((byte)24);
               this.a = 2;
            } else if (var1 == 2) {
               this.this$0.SendAirFrontMsg((byte)25);
               this.a = 3;
            } else if (var1 == 3) {
               this.this$0.SendAirFrontMsg((byte)26);
               this.a = 4;
            } else if (var1 == 4) {
               this.this$0.SendAirFrontMsg((byte)27);
               this.a = 0;
            }

         }

         public void onRightSeatClick() {
            int var1 = this.a;
            if (var1 == 0) {
               this.this$0.SendAirFrontMsg((byte)21);
               this.a = 1;
            } else if (var1 == 1) {
               this.this$0.SendAirFrontMsg((byte)24);
               this.a = 2;
            } else if (var1 == 2) {
               this.this$0.SendAirFrontMsg((byte)25);
               this.a = 3;
            } else if (var1 == 3) {
               this.this$0.SendAirFrontMsg((byte)26);
               this.a = 4;
            } else if (var1 == 4) {
               this.this$0.SendAirFrontMsg((byte)27);
               this.a = 0;
            }

         }
      });
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onAirTemperatureUpDownClickListenerCenter, null});
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      var2.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         int b;
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            int var1 = this.b;
            if (var1 == 0) {
               this.this$0.SendAirRearMsg((byte)24);
               this.b = 1;
            } else if (var1 == 1) {
               this.this$0.SendAirRearMsg((byte)25);
               this.b = 2;
            } else if (var1 == 2) {
               this.this$0.SendAirRearMsg((byte)26);
               this.b = 0;
            }

         }

         public void onRightSeatClick() {
            int var1 = this.b;
            if (var1 == 0) {
               this.this$0.SendAirRearMsg((byte)24);
               this.b = 1;
            } else if (var1 == 1) {
               this.this$0.SendAirRearMsg((byte)25);
               this.b = 2;
            } else if (var1 == 2) {
               this.this$0.SendAirRearMsg((byte)26);
               this.b = 0;
            }

         }
      });
      var2.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.SendAirRearMsg((byte)29);
         }

         public void onClickRight() {
            this.this$0.SendAirRearMsg((byte)28);
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var3) {
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
               case -1014842379:
                  if (var4.equals("_337_setting_10")) {
                     var5 = 0;
                  }
                  break;
               case -1014842378:
                  if (var4.equals("_337_setting_11")) {
                     var5 = 1;
                  }
                  break;
               case -1014842377:
                  if (var4.equals("_337_setting_12")) {
                     var5 = 2;
                  }
                  break;
               case -1014842376:
                  if (var4.equals("_337_setting_13")) {
                     var5 = 3;
                  }
                  break;
               case -1014842375:
                  if (var4.equals("_337_setting_14")) {
                     var5 = 4;
                  }
                  break;
               case -1014842374:
                  if (var4.equals("_337_setting_15")) {
                     var5 = 5;
                  }
                  break;
               case -1014842373:
                  if (var4.equals("_337_setting_16")) {
                     var5 = 6;
                  }
                  break;
               case -1014842370:
                  if (var4.equals("_337_setting_19")) {
                     var5 = 7;
                  }
                  break;
               case -586926182:
                  if (var4.equals("_337_setting_0")) {
                     var5 = 8;
                  }
                  break;
               case -586926181:
                  if (var4.equals("_337_setting_1")) {
                     var5 = 9;
                  }
                  break;
               case -586926180:
                  if (var4.equals("_337_setting_2")) {
                     var5 = 10;
                  }
                  break;
               case -586926178:
                  if (var4.equals("_337_setting_4")) {
                     var5 = 11;
                  }
                  break;
               case -586926176:
                  if (var4.equals("_337_setting_6")) {
                     var5 = 12;
                  }
                  break;
               case -586926175:
                  if (var4.equals("_337_setting_7")) {
                     var5 = 13;
                  }
                  break;
               case -586926174:
                  if (var4.equals("_337_setting_8")) {
                     var5 = 14;
                  }
                  break;
               case -586926173:
                  if (var4.equals("_337_setting_9")) {
                     var5 = 15;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
            }

         }
      });
      var3.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var3) {
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
            if (var5.equals("_337_drive_car_info5")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
            }

         }
      });
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(this.mContext);
      var4.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
      var4.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var4.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      this.getDriverDataPageUiSet(this.mContext);
   }

   private void SendAirFrontMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -124, var1, 0});
   }

   private void SendAirRearMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -123, var1, 0});
   }

   private void activeRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void sendAmplifierInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)var1, (byte)var2});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var5 = var6.iterator();

         while(var5.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

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
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
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
