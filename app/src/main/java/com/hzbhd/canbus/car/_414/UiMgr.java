package com.hzbhd.canbus.car._414;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
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
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirInfo0x3D(2);
            }
         } else {
            this.this$0.sendAirInfo0x3D(7);
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
            this.this$0.sendAirInfo0x3D(6);
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
               this.this$0.sendAirInfo0x3D(4);
            }
         } else {
            this.this$0.sendAirInfo0x3D(1);
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
            this.this$0.sendAirInfo0x3D(5);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirInfo0x3D(14);
      }

      public void onClickUp() {
         this.this$0.sendAirInfo0x3D(13);
      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirInfo0x3D(14);
      }

      public void onClickUp() {
         this.this$0.sendAirInfo0x3D(13);
      }
   };
   OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirInfo0x3D(12);
      }

      public void onClickRight() {
         this.this$0.sendAirInfo0x3D(11);
      }
   };
   private int nowModel = 0;
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.nowModel == 0) {
            this.this$0.sendAirInfo0x3D(26);
            this.this$0.nowModel = 1;
         } else if (this.this$0.nowModel == 1) {
            this.this$0.sendAirInfo0x3D(27);
            this.this$0.nowModel = 2;
         } else if (this.this$0.nowModel == 2) {
            this.this$0.sendAirInfo0x3D(28);
            this.this$0.nowModel = 3;
         } else if (this.this$0.nowModel == 3) {
            this.this$0.sendAirInfo0x3D(29);
            this.this$0.nowModel = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.nowModel == 0) {
            this.this$0.sendAirInfo0x3D(26);
            this.this$0.nowModel = 1;
         } else if (this.this$0.nowModel == 1) {
            this.this$0.sendAirInfo0x3D(27);
            this.this$0.nowModel = 2;
         } else if (this.this$0.nowModel == 2) {
            this.this$0.sendAirInfo0x3D(28);
            this.this$0.nowModel = 3;
         } else if (this.this$0.nowModel == 3) {
            this.this$0.sendAirInfo0x3D(29);
            this.this$0.nowModel = 0;
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest0x6A(49);
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_414_setting")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting0")) {
                  this.this$0.sendCarSetting0x6F(1, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting1")) {
                  this.this$0.sendCarSetting0x6F(2, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting2")) {
                  this.this$0.sendCarSetting0x6F(3, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting3")) {
                  this.this$0.sendCarSetting0x6F(6, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting4")) {
                  this.this$0.sendCarSetting0x6F(4, var3 + 1, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting5")) {
                  this.this$0.sendCarSetting0x6F(5, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting6")) {
                  this.this$0.sendCarSetting0x6F(7, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting8")) {
                  this.this$0.sendCarSetting0x6F(9, var3 + 1, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting9")) {
                  this.this$0.sendCarSetting0x6F(16, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting10")) {
                  this.this$0.sendCarSetting0x6F(17, var3, 255, 255);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_setting", "_414_setting11")) {
                  this.this$0.sendCarSetting0x6F(19, var3, 255, 255);
               }
            }

            var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_414_panoramic")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_panoramic", "_414_panoramic0")) {
                  if (var3 == 1) {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).showButton();
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
                  } else {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).hideButton();
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
                  }
               }
            }

            var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_414_language")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_414_language", "_414_language")) {
                  this.this$0.send0x9ALanguageInfo(var3 + 1);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }
            }

         }
      });
      var3.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            UiMgr var3 = this.this$0;
            if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_414_setting")) {
               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "_414_setting", "_414_setting7")) {
                  this.this$0.sendCarSetting0x6F(8, 1, 255, 255);
               }
            }

         }
      });
      var3.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest0x6A(97);
         }
      });
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 == 0) {
               this.this$0.sendPanoramicInfo0xFD();
            }

         }
      });
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest0x6A(50);
            this.this$0.activeRequest0x6A(19);
         }
      });
   }

   private void activeRequest0x6A(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
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

   private void send0x9ALanguageInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)var1});
   }

   private void sendAirInfo0x3D(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   private void sendCarSetting0x6F(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
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

   public void sendCarType() {
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getEachId(), 38});
   }

   public void sendPanoramicInfo0xFD() {
      CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
