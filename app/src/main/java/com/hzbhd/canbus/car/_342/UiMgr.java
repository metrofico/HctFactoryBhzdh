package com.hzbhd.canbus.car._342;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               if (!GeneralAirData.in_out_cycle) {
                  this.this$0.sendAirData0xA8(5);
               } else {
                  this.this$0.sendAirData0xA8(4);
               }
            }
         } else {
            this.this$0.sendAirData0xA8(17);
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
               this.this$0.sendAirData0xA8(0);
            }
         } else {
            if (this.this$0.eachId != 5) {
               return;
            }

            this.this$0.sendAirData0xA8(21);
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
            if (var1 == 2) {
               if (this.this$0.eachId != 7) {
                  return;
               }

               this.this$0.sendAirData0xA8(2);
            }
         } else {
            this.this$0.sendAirData0xA8(1);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirData0xA8(3);
            }
         } else {
            if (this.this$0.eachId != 5) {
               return;
            }

            this.this$0.sendAirData0xA8(6);
         }

      }
   };
   MsgMgr msgMgr;
   DecimalFormat noDecimal = new DecimalFormat("000000");
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr var1 = this.this$0;
         if (var1.getMsgMgr(var1.mContext).nowTemLevel1 > 0) {
            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel1 - 1)});
         }

      }

      public void onClickUp() {
         UiMgr var1;
         if (this.this$0.eachId == 5) {
            var1 = this.this$0;
            if (var1.getMsgMgr(var1.mContext).nowTemLevel1 == 12) {
               return;
            }

            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel1 + 1)});
         } else if (this.this$0.eachId == 7) {
            var1 = this.this$0;
            if (var1.getMsgMgr(var1.mContext).nowTemLevel1 == 32) {
               return;
            }

            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel1 + 1)});
         } else {
            var1 = this.this$0;
            if (var1.getMsgMgr(var1.mContext).nowTemLevel1 == 15) {
               return;
            }

            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 19, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel1 + 1)});
         }

      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr var1 = this.this$0;
         if (var1.getMsgMgr(var1.mContext).nowTemLevel2 > 0) {
            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel2 - 1)});
         }

      }

      public void onClickUp() {
         UiMgr var1;
         if (this.this$0.eachId == 5) {
            var1 = this.this$0;
            if (var1.getMsgMgr(var1.mContext).nowTemLevel2 == 12) {
               return;
            }

            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel2 + 1)});
         } else if (this.this$0.eachId == 7) {
            var1 = this.this$0;
            if (var1.getMsgMgr(var1.mContext).nowTemLevel2 == 32) {
               return;
            }

            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel2 + 1)});
         } else {
            var1 = this.this$0;
            if (var1.getMsgMgr(var1.mContext).nowTemLevel2 == 15) {
               return;
            }

            var1 = this.this$0;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, (byte)(var1.getMsgMgr(var1.mContext).nowTemLevel2 + 1)});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (GeneralAirData.front_wind_level != 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte)(GeneralAirData.front_wind_level - 1)});
         }
      }

      public void onClickRight() {
         if (this.this$0.eachId == 5 && GeneralAirData.front_wind_level < 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte)(GeneralAirData.front_wind_level + 1)});
         } else if (this.this$0.eachId != 5 && GeneralAirData.front_wind_level < 7) {
            int var1 = GeneralAirData.front_wind_level++;
            GeneralAirData.front_wind_level = var1;
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte)(GeneralAirData.front_wind_level + 1)});
         }

      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         String var3 = ((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getTitleSrn();
         var3.hashCode();
         if (var3.equals("_342_setting_2")) {
            UiMgr var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_342_setting_2", "_342_setting_2_0")) {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).a == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 0});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_342_setting_2", "_342_setting_2_1")) {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).b == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, 0});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_342_setting_2", "_342_setting_2_2")) {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).c == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, 0});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_342_setting_2", "_342_setting_2_3")) {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).d == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, 0});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_342_setting_2", "_342_setting_2_4")) {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).e == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 0});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_342_setting_2", "_342_setting_2_5")) {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).f == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, 0});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_342_setting_2", "_342_setting_2_6")) {
               var4 = this.this$0;
               if (var4.getMsgMgr(var4.mContext).g == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
               }
            }
         }

      }
   };
   private int panoramicState = 0;
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      int var2 = this.getEachId();
      this.eachId = var2;
      if (var2 == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getCarModelData(0)});
      }

      if (this.eachId == 3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getCarModelData(1)});
      }

      if (this.eachId == 4) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getCarModelData(2)});
      }

      if (this.eachId == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getCarModelData(3)});
      }

      if (this.eachId == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getCarModelData(4)});
      }

      if (this.eachId == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getCarModelData(5)});
      }

      this.differentId = this.getCurrentCarId();
      this.settingPageUiSet = this.getSettingUiSet(this.mContext);
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemClickListener(this.onSettingItemClickListener);
      var3.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            UiMgr var2 = this.this$0;
            if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_342_setting_2")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -112, 97, 0});
            }

         }
      });
      AirPageUiSet var4 = this.getAirUiSet(this.mContext);
      if (this.eachId == 5) {
         var4.getFrontArea().setWindMaxLevel(12);
      } else {
         var4.getFrontArea().setWindMaxLevel(7);
      }

      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
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
                           CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void initUi() {
      if (this.eachId == 1) {
         this.removeMainPrjBtnByName(this.mContext, "air");
      }

      if (this.eachId != 1) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_342_setting_2"});
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

   }

   private void sendAVMData0x83(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -125, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -125, var2, 0});
   }

   private void sendAirData0xA8(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 0});
   }

   public int getCarModelData(int var1) {
      if (var1 != 0) {
         byte var2 = 1;
         if (var1 != 1) {
            var2 = 2;
            if (var1 != 2) {
               var2 = 3;
               if (var1 != 3) {
                  var2 = 4;
                  if (var1 != 4) {
                     return 5;
                  }
               }
            }
         }

         return var2;
      } else {
         return 0;
      }
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
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
   }
}
