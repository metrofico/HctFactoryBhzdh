package com.hzbhd.canbus.car._382;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int defaultTmpRight = 42;
   int defaultTmpValueLeft = 42;
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            if (GeneralAirData.power) {
               this.this$0.sendAirData0xE0(6, 0);
            } else {
               this.this$0.sendAirData0xE0(6, 1);
            }
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
            if (GeneralAirData.front_defog) {
               this.this$0.sendAirData0xE0(11, 0);
            } else {
               this.this$0.sendAirData0xE0(11, 1);
            }
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
            if (var1 == 1) {
               if (GeneralAirData.auto_wind_lv == 0) {
                  this.this$0.sendAirData0xE0(2, 1);
               } else if (GeneralAirData.auto_wind_lv == 1) {
                  this.this$0.sendAirData0xE0(2, 2);
               } else {
                  this.this$0.sendAirData0xE0(2, 0);
               }
            }
         } else if (GeneralAirData.in_out_cycle) {
            this.this$0.sendAirData0xE0(1, 1);
         } else {
            this.this$0.sendAirData0xE0(1, 0);
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
            if (GeneralAirData.steering_wheel_heating) {
               this.this$0.sendAirData0xE0(10, 0);
            } else {
               this.this$0.sendAirData0xE0(10, 1);
            }
         }

      }
   };
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequest(49);
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.windModelState == 0) {
            this.this$0.windModelState = 1;
            this.this$0.sendAirData0xE0(3, 1);
            this.this$0.sendAirData0xE0(4, 1);
            this.this$0.sendAirData0xE0(5, 1);
         } else if (this.this$0.windModelState == 1) {
            this.this$0.windModelState = 2;
            this.this$0.sendAirData0xE0(3, 1);
            this.this$0.sendAirData0xE0(4, 1);
            this.this$0.sendAirData0xE0(5, 0);
         } else if (this.this$0.windModelState == 2) {
            this.this$0.windModelState = 3;
            this.this$0.sendAirData0xE0(3, 1);
            this.this$0.sendAirData0xE0(4, 0);
            this.this$0.sendAirData0xE0(5, 0);
         } else {
            this.this$0.windModelState = 0;
            this.this$0.sendAirData0xE0(3, 0);
            this.this$0.sendAirData0xE0(4, 0);
            this.this$0.sendAirData0xE0(5, 0);
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.windModelState == 0) {
            this.this$0.windModelState = 1;
            this.this$0.sendAirData0xE0(3, 1);
            this.this$0.sendAirData0xE0(4, 1);
            this.this$0.sendAirData0xE0(5, 1);
         } else if (this.this$0.windModelState == 1) {
            this.this$0.windModelState = 2;
            this.this$0.sendAirData0xE0(3, 1);
            this.this$0.sendAirData0xE0(4, 1);
            this.this$0.sendAirData0xE0(5, 0);
         } else if (this.this$0.windModelState == 2) {
            this.this$0.windModelState = 3;
            this.this$0.sendAirData0xE0(3, 1);
            this.this$0.sendAirData0xE0(4, 0);
            this.this$0.sendAirData0xE0(5, 0);
         } else {
            this.this$0.windModelState = 0;
            this.this$0.sendAirData0xE0(3, 0);
            this.this$0.sendAirData0xE0(4, 0);
            this.this$0.sendAirData0xE0(5, 0);
         }

      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.defaultTmpValueLeft == 34) {
            this.this$0.sendAirData0xE0(8, 254);
         } else {
            UiMgr var1 = this.this$0;
            var1.defaultTmpValueLeft -= 2;
            var1 = this.this$0;
            var1.sendAirData0xE0(8, var1.defaultTmpValueLeft);
         }

      }

      public void onClickUp() {
         if (this.this$0.defaultTmpValueLeft == 62) {
            this.this$0.sendAirData0xE0(8, 255);
         } else {
            UiMgr var1 = this.this$0;
            var1.defaultTmpValueLeft += 2;
            var1 = this.this$0;
            var1.sendAirData0xE0(8, var1.defaultTmpValueLeft);
         }

      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.defaultTmpRight == 34) {
            this.this$0.sendAirData0xE0(9, 254);
         } else {
            UiMgr var1 = this.this$0;
            var1.defaultTmpRight -= 2;
            var1 = this.this$0;
            var1.sendAirData0xE0(9, var1.defaultTmpRight);
         }

      }

      public void onClickUp() {
         if (this.this$0.defaultTmpRight == 62) {
            this.this$0.sendAirData0xE0(9, 255);
         } else {
            UiMgr var1 = this.this$0;
            var1.defaultTmpRight += 2;
            var1 = this.this$0;
            var1.sendAirData0xE0(9, var1.defaultTmpRight);
         }

      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (GeneralAirData.front_wind_level == 0) {
            this.this$0.sendAirData0xE0(7, 7);
         } else {
            this.this$0.sendAirData0xE0(7, GeneralAirData.front_wind_level - 1);
         }

      }

      public void onClickRight() {
         if (GeneralAirData.front_wind_level == 7) {
            this.this$0.sendAirData0xE0(7, 0);
         } else {
            this.this$0.sendAirData0xE0(7, GeneralAirData.front_wind_level + 1);
         }

      }
   };
   OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequest(17);
      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_382_athor")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_382_athor", "_382_athor8")) {
               var3 = this.this$0;
               var3.getMsgMgr(var3.mContext).Toast("Reset success!");
               this.this$0.send0xCC(1, 1);
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
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_car_tyres")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_tyres", "_382_car_tyres2")) {
               this.this$0.send0x4BTyresInfo(3, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_car_light")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_light", "_382_car_light6")) {
               this.this$0.send0x6D(15, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_light", "_382_car_light7")) {
               this.this$0.send0x6D(17, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_parking")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_parking", "_382_parking2")) {
               this.this$0.send0x7A(1, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_parking", "_382_parking3")) {
               this.this$0.send0x7A(2, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_parking", "_382_parking4")) {
               this.this$0.send0x7A(3, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_parking", "_382_parking5")) {
               this.this$0.send0x7A(4, var3);
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
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_car_tyres")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_tyres", "_382_car_tyres1")) {
               this.this$0.send0x4BTyresInfo(2, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_car_auxiliary")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_auxiliary", "_382_car_auxiliary1")) {
               this.this$0.send0x4D(1, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_car_light")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_light", "_382_car_light1")) {
               this.this$0.send0x6D(19, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_light", "_382_car_light2")) {
               this.this$0.send0x6D(18, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_light", "_382_car_light3")) {
               this.this$0.send0x6D(16, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_light", "_382_car_light4")) {
               this.this$0.send0x6D(13, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_car_light", "_382_car_light5")) {
               this.this$0.send0x6D(14, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_window")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_window", "_382_window1")) {
               this.this$0.send0x6F(1, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_window", "_382_window2")) {
               this.this$0.send0x6F(2, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_window", "_382_window3")) {
               this.this$0.send0x6F(3, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_window", "_382_window4")) {
               this.this$0.send0x6F(5, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_window", "_382_window5")) {
               this.this$0.send0x6F(6, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_window", "_382_window6")) {
               this.this$0.send0x6F(7, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_window", "_382_window7")) {
               this.this$0.send0x6F(8, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_parking")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_parking", "_382_parking1")) {
               this.this$0.send0x7A(5, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_unit_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_unit_setting", "_382_unit_setting1")) {
               if (var3 == 0) {
                  this.this$0.send0xCA(1, 2);
               } else {
                  this.this$0.send0xCA(1, 1);
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_unit_setting", "_382_unit_setting2")) {
               if (var3 == 0) {
                  this.this$0.send0xCA(2, 2);
               } else {
                  this.this$0.send0xCA(2, 1);
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_unit_setting", "_382_unit_setting3")) {
               if (var3 == 0) {
                  this.this$0.send0xCA(3, 2);
               } else {
                  this.this$0.send0xCA(3, 1);
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_unit_setting", "_382_unit_setting4")) {
               this.this$0.send0xCA(4, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_unit_setting", "_382_unit_setting5")) {
               this.this$0.send0xCA(5, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_unit_setting", "_382_unit_setting6")) {
               this.this$0.send0xCA(6, var3 + 1);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_382_athor")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_382_athor", "_382_athor6")) {
               this.this$0.send0xCC(2, var3);
            }
         }

      }
   };
   int windModelState = 0;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var3.setOnSettingItemClickListener(this.onSettingItemClickListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
   }

   private void activeRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 1, (byte)var1});
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initUi() {
   }

   private void intiData() {
   }

   private void send0x4BTyresInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 75, (byte)var1, (byte)var2});
   }

   private void send0x4D(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 77, (byte)var1, (byte)var2});
   }

   private void send0x6D(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte)var1, (byte)var2});
   }

   private void send0x6F(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2});
   }

   private void send0x7A(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 122, (byte)var1, (byte)var2});
   }

   private void send0xCA(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte)var1, (byte)var2});
   }

   private void send0xCC(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -52, (byte)var1, (byte)var2});
   }

   private void sendAirData0xE0(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var1, (byte)var2});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var5 = var7.iterator();

         while(var5.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
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

   public boolean isLandscape() {
      boolean var1;
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void send0xCBTimeInfo(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8, (byte)var9, (byte)var10});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
      this.intiData();
   }
}
