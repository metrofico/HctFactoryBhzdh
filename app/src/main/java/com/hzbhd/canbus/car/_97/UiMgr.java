package com.hzbhd.canbus.car._97;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_AIR_CONDITIONER_TYPE = "share_air_conditioner_type";
   private Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(3, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(2, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(0, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(1, var1);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(23);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(24);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(17);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(18);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(14);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(16);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(15);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      AirPageUiSet var3 = this.getAirUiSet(var1);
      FrontArea var4 = var3.getFrontArea();
      boolean var2;
      if (this.mDifferent == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setCanSetSeatCold(var2);
      var4 = var3.getFrontArea();
      if (this.mDifferent == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setShowCenterWheel(var2);
      if (this.mDifferent == 0) {
         var3.getFrontArea().setDisableBtnArray(new String[]{"steering_wheel_heating"});
      }

      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(12);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(11);
         }
      });
      this.getMsgMgr(var1).updateSetting(0, 0, SharePreUtil.getBoolValue(var1, "share_air_conditioner_type", true) ^ 1);
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var5, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var7.hashCode();
            int var5 = var7.hashCode();
            boolean var6 = true;
            byte var4 = -1;
            switch (var5) {
               case -392737078:
                  if (var7.equals("hiworld_jeep_123_0xCA_0X01")) {
                     var4 = 0;
                  }
                  break;
               case -374590123:
                  if (var7.equals("on_off_btn_txt_7")) {
                     var4 = 1;
                  }
                  break;
               case -26602129:
                  if (var7.equals("temperature_unit")) {
                     var4 = 2;
                  }
                  break;
               case 99087442:
                  if (var7.equals("_97_car")) {
                     var4 = 3;
                  }
                  break;
               case 102584022:
                  if (var7.equals("language_setup")) {
                     var4 = 4;
                  }
                  break;
               case 1712665702:
                  if (var7.equals("air_conditioner_type")) {
                     var4 = 5;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 1, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var3, 0, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 4, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.this$0.getCarCmd(var3), 3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 5, (byte)var3});
                  break;
               case 5:
                  Context var8 = this.val$context;
                  if (var3 != 0) {
                     var6 = false;
                  }

                  SharePreUtil.setBoolValue(var8, "share_air_conditioner_type", var6);
                  this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
            }

         }
      });
      var5.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
         }
      });
      var5.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == 2 && var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
            }

         }
      });
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      var6.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var6, var1) {
         final UiMgr this$0;
         final Context val$context;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
            this.val$context = var3;
         }

         public void addViewToWindows() {
            this.val$parkPageUiSet.setShowRadar(SharePreUtil.getBoolValue(this.val$context, "share_97_radar_switch", true));
         }
      });
   }

   private int getCarCmd(int var1) {
      switch (var1) {
         case 0:
            return 5;
         case 1:
            return 9;
         case 2:
         default:
            return 19;
         case 3:
            return 20;
         case 4:
            return 21;
         case 5:
            return 22;
         case 6:
            return 28;
         case 7:
            return 29;
         case 8:
            return 30;
         case 9:
            return 32;
      }
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void resolveAirBtn(int var1, int var2) {
      String var3 = this.getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[var1][var2];
      if (!TextUtils.isEmpty(var3)) {
         var3.hashCode();
         switch (var3) {
            case "steering_wheel_heating":
               this.sendAirCommand(45);
               break;
            case "ac_max":
               this.sendAirCommand(26);
               break;
            case "rear_defog":
               this.sendAirCommand(6);
               break;
            case "ac":
               this.sendAirCommand(2);
               break;
            case "auto":
               this.sendAirCommand(4);
               break;
            case "dual":
               this.sendAirCommand(3);
               break;
            case "power":
               this.sendAirCommand(1);
               break;
            case "blow_window":
               this.sendAirCommand(8);
               break;
            case "max_heat":
               this.sendAirCommand(5);
               break;
            case "in_out_cycle":
               this.sendAirCommand(7);
               break;
            case "blow_foot":
               this.sendAirCommand(10);
               break;
            case "blow_head":
               this.sendAirCommand(9);
         }

      }
   }

   private void sendAirCommand(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 0});
         }
      }).start();
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
      if (this.mDifferent == 0) {
         this.remvoeSettingLeftItemByName(var1, "car_light_set");
      }

      if (this.mDifferent != 2) {
         this.removeDriveData(this.mContext, "S97_Fuel_cons_mile_info");
         this.removeMainPrjBtnByName(this.mContext, "tire_info");
      }

   }
}
