package com.hzbhd.canbus.car._479;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         String var3 = var2.getAirUiSet(var2.mContext).getFrontArea().getLineBtnAction()[3][var1];
         var3.hashCode();
         if (!var3.equals("front_defog")) {
            if (var3.equals("rear_defog")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 8, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 8, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 7, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 7, 0});
         }

      }
   };
   OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 0});
      }
   };
   Context mContext;
   private MsgMgr mMsgMgr;
   OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 4, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 4, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 0});
      }
   };
   OnAirSeatClickListener seatClick = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 13, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 13, 0});
      }

      public void onRightSeatClick() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 13, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 13, 0});
      }
   };
   OnSettingItemSelectListener settingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var4.getSettingUiSet(var4.mContext).getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var5.hashCode();
         if (!var5.equals("_479_car_set_1")) {
            if (var5.equals("_479_car_set_2")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, (byte)var3});
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
         }

      }
   };
   OnAirBtnClickListener topBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var4;
         label36: {
            UiMgr var3 = this.this$0;
            String[][] var5 = var3.getAirUiSet(var3.mContext).getFrontArea().getLineBtnAction();
            byte var2 = 0;
            var6.hashCode();
            switch (var6) {
               case "ac":
                  var4 = var2;
               case "auto":
                  var4 = 1;
                  break label36;
               case "dual":
                  var4 = 2;
                  break label36;
               case "in_out_cycle":
                  var4 = 3;
                  break label36;
            }

            var4 = -1;
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 11, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 11, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 5, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 5, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 14, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 14, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 12, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 12, 0});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 9, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 9, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 10, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 10, 0});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(var1);
      var2.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickTopBtnItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -56, 7, 2});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 7, 1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -56, 7, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 6, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 6, 0});
            }

         }
      });
      var2.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this, var2) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
         }

         public void onClickBottomBtnItem(int var1) {
            String var3 = this.val$originalCarDevicePageUiSet.getRowBottomBtnAction()[var1];
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -841905119:
                  if (var3.equals("prev_disc")) {
                     var4 = 0;
                  }
                  break;
               case 3739:
                  if (var3.equals("up")) {
                     var4 = 1;
                  }
                  break;
               case 3089570:
                  if (var3.equals("down")) {
                     var4 = 2;
                  }
                  break;
               case 3317767:
                  if (var3.equals("left")) {
                     var4 = 3;
                  }
                  break;
               case 3443508:
                  if (var3.equals("play")) {
                     var4 = 4;
                  }
                  break;
               case 106440182:
                  if (var3.equals("pause")) {
                     var4 = 5;
                  }
                  break;
               case 108511772:
                  if (var3.equals("right")) {
                     var4 = 6;
                  }
                  break;
               case 1216748385:
                  if (var3.equals("next_disc")) {
                     var4 = 7;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 9, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 3, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 4, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, 0});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, 0});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 5, 0});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 8, 0});
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, null, null, this.bottomBtn});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
      var3.getFrontArea().setOnAirSeatClickListener(this.seatClick);
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(this.settingItemSelectListener);
      this.getDriverDataPageUiSet(var1);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
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
