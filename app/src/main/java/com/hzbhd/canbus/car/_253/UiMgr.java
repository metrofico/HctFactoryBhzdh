package com.hzbhd.canbus.car._253;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int a = 0;
   private int eachId = this.getEachId();
   private AirPageUiSet mAirPageUiSet;
   private int[] mCanBusInfoInt;
   private FrontArea mFrontArea;
   private MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, 1});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            if (GeneralAirData.aqs) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, 1});
            } else if (!GeneralAirData.aqs) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, 1});
            }
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, 1});
         }

         if (var1 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, 1});
         }

         if (var1 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, 1});
         }

         if (var1 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 1});
         }

         if (var1 == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 1});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 13, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.eachId == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 16, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 1});
         }

      }

      public void onClickUp() {
         if (this.this$0.eachId == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 15, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 13, 1});
         }

      }
   };
   private ParkPageUiSet mParkPageUiSet;
   private UiMgr mUiMgr;
   private int mWindModle = 7;

   public UiMgr(Context var1) {
      AirPageUiSet var3 = this.getAirUiSet(var1);
      this.mAirPageUiSet = var3;
      FrontArea var4 = var3.getFrontArea();
      this.mFrontArea = var4;
      var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
      this.mFrontArea.setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)UiMgr.access$008(this.this$0), 1});
            if (this.this$0.mWindModle > 10) {
               this.this$0.mWindModle = 7;
            }

         }

         public void onRightSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)UiMgr.access$008(this.this$0), 1});
            if (this.this$0.mWindModle > 10) {
               this.this$0.mWindModle = 7;
            }

         }
      });
      this.mFrontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 12, 1});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 11, 1});
         }
      });
      if (this.eachId == 3) {
         this.mAirPageUiSet.getFrontArea().setWindMaxLevel(6);
      }

      int var2 = this.eachId;
      if (var2 != 3 && var2 != 12 && var2 != 13) {
         this.mFrontArea.setAllBtnCanClick(false);
         this.mFrontArea.setCanSetRightTemp(false);
         this.mFrontArea.setCanSetLeftTemp(false);
         this.mFrontArea.setCanSetWindSpeed(false);
      } else {
         this.mFrontArea.setAllBtnCanClick(true);
         this.mFrontArea.setCanSetRightTemp(true);
         this.mFrontArea.setCanSetLeftTemp(true);
         this.mFrontArea.setCanSetWindSpeed(true);
         this.mFrontArea.setDisableBtnArray(new String[]{"ion", "max_front", "eco", "ac_max", "rear"});
      }

      if (this.eachId == 11) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, -96, 2});
      }

      if (this.eachId == 12) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, -96, 3});
      }

      if (this.eachId == 13) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, -96, 4});
      }

      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1, var5) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$settingPageUiSet = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
            var5.hashCode();
            if (var5.equals("car_set")) {
               byte var6;
               label118: {
                  var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var5.hashCode();
                  switch (var5) {
                     case "_253_front_radar_switch":
                        var6 = 0;
                        break label118;
                     case "_253_Tire_Pressure_Monitoring":
                        var6 = 1;
                        break label118;
                     case "_253_Ambient_light_mode":
                        var6 = 2;
                        break label118;
                     case "_253_Rearview_mirror_backlight_brightness":
                        var6 = 3;
                        break label118;
                     case "_253_Automatic_folding_Rearview_mirror":
                        var6 = 4;
                        break label118;
                     case "_253_Parking_unlocked":
                        var6 = 5;
                        break label118;
                     case "_253_Unit_of_speed_and_fuel_consumption":
                        var6 = 6;
                        break label118;
                     case "_253_Blind_spot_warning":
                        var6 = 7;
                        break label118;
                     case "_253_Ambient_light_Lightness":
                        var6 = 8;
                        break label118;
                     case "_253_Lane_deviation_warning":
                        var6 = 9;
                        break label118;
                     case "_253_Remote_lock_reminder":
                        var6 = 10;
                        break label118;
                     case "_253_Language":
                        var6 = 11;
                        break label118;
                     case "_253_Rearview_mirror_automatically_anti_glare":
                        var6 = 12;
                        break label118;
                     case "_253_HeadLight_delay":
                        var6 = 13;
                        break label118;
                     case "_253_Theme":
                        var6 = 14;
                        break label118;
                     case "_253_Temperature_unit":
                        var6 = 15;
                        break label118;
                     case "_253_initial_perspective":
                        var6 = 16;
                        break label118;
                     case "_253_rear_radar_switch":
                        var6 = 17;
                        break label118;
                  }

                  var6 = -1;
               }

               boolean var4;
               Context var7;
               switch (var6) {
                  case 0:
                     var7 = this.val$context;
                     if (var3 == 1) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     SharePreUtil.setBoolValue(var7, "share_key_253_front_radar_enable", var4);
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 3, (byte)var3});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 13, (byte)var3});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 16, (byte)var3});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 17, (byte)var3});
                  case 12:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 18, (byte)var3});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 14, (byte)var3});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 15, (byte)var3});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, (byte)var3});
                     break;
                  case 7:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte)var3});
                     break;
                  case 8:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 12, (byte)var3});
                     break;
                  case 9:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte)var3});
                     break;
                  case 10:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 11, (byte)var3});
                     break;
                  case 11:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 6, (byte)var3});
                     break;
                  case 13:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 10, (byte)var3});
                     break;
                  case 14:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 9, (byte)var3});
                     break;
                  case 15:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 8, (byte)var3});
                     break;
                  case 16:
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 2, (byte)(var3 + 1)});
                     break;
                  case 17:
                     var7 = this.val$context;
                     if (var3 == 1) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     SharePreUtil.setBoolValue(var7, "share_key_253_rear_radar_enable", var4);
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 4, (byte)var3});
               }
            }

         }
      });
      var5.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var1, var5) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$settingPageUiSet = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
            if (var1 == 0) {
               String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var4.hashCode();
               if (var4.equals("_253_Rearview_mirror_backlight_brightness")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 17, (byte)var3});
               }
            }

         }
      });
      var5.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "enter_panorama") && var2 == this.this$0.getSettingRightIndex(this.val$context, "enter_panorama", "_253_click_into_the_panorama")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, 2});
            }

         }
      });
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var6;
      var6.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void addViewToWindows() {
            this.this$0.getMsgMgr(this.val$context).initRadar();
         }
      });
      this.mParkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            switch (var1) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 1});
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var1 + 1)});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 0, 0});
            }

         }
      });
      this.mParkPageUiSet.setShowRadar(this.isSupportRadar());
      this.mParkPageUiSet.setShowPanoramic(this.isSupportPanoramic());
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(new OnPanelKeyPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void click(int var1) {
            if (var1 != 0) {
               if (var1 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 2});
            }

         }
      });
   }

   // $FF: synthetic method
   static int access$008(UiMgr var0) {
      int var1 = var0.mWindModle++;
      return var1;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private UiMgr getmUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isSupportPanoramic() {
      int var1 = this.eachId;
      boolean var2;
      if (var1 != 9 && var1 != 10) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private boolean isSupportRadar() {
      switch (this.eachId) {
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
            return true;
         default:
            return false;
      }
   }

   private boolean isSupportTrack() {
      int var1 = this.eachId;
      if (var1 != 1 && var1 != 2 && var1 != 3) {
         switch (var1) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
               break;
            default:
               return false;
         }
      }

      return true;
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

   public void sendVoiceControlInfo0x01(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -87, 1, (byte)var1});
   }

   public void sendVoiceControlInfoAir(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -87, 1, (byte)var1, (byte)var2});
   }

   public void sendVoiceControlInfoAir(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -87, (byte)var1, (byte)var2, (byte)var3});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.eachId;
      if (var2 == 2 || var2 == 8) {
         this.removeMainPrjBtnByName(var1, "air");
      }

      var2 = this.eachId;
      if (var2 != 9 && var2 != 10) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"enter_panorama"});
      }

      if (this.eachId != 10) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Lane_deviation_warning"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Blind_spot_warning"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_front_radar_switch"});
      }

      switch (this.eachId) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
            this.removeMainPrjBtnByName(var1, "setting");
      }

      if (this.eachId == 9) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"car_set"});
      }

      var2 = this.eachId;
      if (var2 != 8 && var2 != 12 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_initial_perspective"});
      }

      var2 = this.eachId;
      if (var2 != 10 && var2 != 12 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_rear_radar_switch"});
      }

      var2 = this.eachId;
      if (var2 != 11 && var2 != 12 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Language"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Theme"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Temperature_unit"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Unit_of_speed_and_fuel_consumption"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Tire_Pressure_Monitoring"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Parking_unlocked"});
      }

      var2 = this.eachId;
      if (var2 != 12 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_HeadLight_delay"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Remote_lock_reminder"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Ambient_light_Lightness"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Automatic_folding_Rearview_mirror"});
      }

      if (this.eachId != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Ambient_light_mode"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Rearview_mirror_backlight_brightness"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_Rearview_mirror_automatically_anti_glare"});
      }

      if (this.getSettingUiSet(var1).getList().size() > 0 && "car_set".equals(((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getTitleSrn())) {
         for(var2 = 0; var2 < ((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getItemList().size(); ++var2) {
            if ("_253_front_radar_switch".equals(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getItemList().get(var2)).getTitleSrn())) {
               this.getMsgMgr(var1).updateSetting(0, var2, SharePreUtil.getBoolValue(var1, "share_key_253_front_radar_enable", true));
            } else if ("_253_rear_radar_switch".equals(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getItemList().get(var2)).getTitleSrn())) {
               this.getMsgMgr(var1).updateSetting(0, var2, SharePreUtil.getBoolValue(var1, "share_key_253_rear_radar_enable", true));
            }
         }
      }

   }
}
