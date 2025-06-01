package com.hzbhd.canbus.car._349;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int eachId;
   int i;
   Context mContext;
   private String[][] mDiagitalKeyboardActions;
   private String[][] mFeaturesKeyboardActions;
   private boolean mIsFeaturesKeyboard = true;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
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
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 63, 1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 5, 1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 57, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 55, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 56, 1});
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
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 24, 1});
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
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 23, 1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 4, 1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 3, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 2, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 1, 1});
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
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 62, 1});
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 18, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 17, 1});
         }

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
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 60, 1});
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
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 61, 1});
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
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 58, 1});
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
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 59, 1});
      }
   };
   private MyPanoramicView mPanoramicView;
   private MsgMgr msgMgr;
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 50, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 49, 1});
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 52, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 51, 1});
      }
   };
   private UiMgr uiMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(108);
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
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            var2 = var6.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -1482315655:
                  if (var6.equals("ford_alert_tone")) {
                     var5 = 0;
                  }
                  break;
               case -1445075171:
                  if (var6.equals("_81_current_voice_mode")) {
                     var5 = 1;
                  }
                  break;
               case -1093208114:
                  if (var6.equals("_81_turn_signals_setup")) {
                     var5 = 2;
                  }
                  break;
               case -922538678:
                  if (var6.equals("ford_range_unit")) {
                     var5 = 3;
                  }
                  break;
               case -873936231:
                  if (var6.equals("_279_temperature_unit")) {
                     var5 = 4;
                  }
                  break;
               case 102584022:
                  if (var6.equals("language_setup")) {
                     var5 = 5;
                  }
                  break;
               case 648162385:
                  if (var6.equals("brightness")) {
                     var5 = 6;
                  }
                  break;
               case 1229285653:
                  if (var6.equals("_81_traction_control_system")) {
                     var5 = 7;
                  }
                  break;
               case 1715286958:
                  if (var6.equals("ford_message_tone")) {
                     var5 = 8;
                  }
                  break;
               case 1807262446:
                  if (var6.equals("_349_setting_2_0")) {
                     var5 = 9;
                  }
                  break;
               case 1807262447:
                  if (var6.equals("_349_setting_2_1")) {
                     var5 = 10;
                  }
                  break;
               case 1807262448:
                  if (var6.equals("_349_setting_2_2")) {
                     var5 = 11;
                  }
                  break;
               case 1807262449:
                  if (var6.equals("_349_setting_2_3")) {
                     var5 = 12;
                  }
                  break;
               case 1807262450:
                  if (var6.equals("_349_setting_2_4")) {
                     var5 = 13;
                  }
                  break;
               case 1807263407:
                  if (var6.equals("_349_setting_3_0")) {
                     var5 = 14;
                  }
                  break;
               case 1807263408:
                  if (var6.equals("_349_setting_3_1")) {
                     var5 = 15;
                  }
                  break;
               case 1807263409:
                  if (var6.equals("_349_setting_3_2")) {
                     var5 = 16;
                  }
                  break;
               case 1807263410:
                  if (var6.equals("_349_setting_3_3")) {
                     var5 = 17;
                  }
                  break;
               case 1807263411:
                  if (var6.equals("_349_setting_3_4")) {
                     var5 = 18;
                  }
                  break;
               case 1807264368:
                  if (var6.equals("_349_setting_4_0")) {
                     var5 = 19;
                  }
                  break;
               case 1807264369:
                  if (var6.equals("_349_setting_4_1")) {
                     var5 = 20;
                  }
                  break;
               case 1807264370:
                  if (var6.equals("_349_setting_4_2")) {
                     var5 = 21;
                  }
                  break;
               case 1807264371:
                  if (var6.equals("_349_setting_4_3")) {
                     var5 = 22;
                  }
                  break;
               case 1989254414:
                  if (var6.equals("_176_car_setting_title_21")) {
                     var5 = 23;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 7)});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 9)});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 3)});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 14)});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte)MsgMgr.mLanguageMapArray[var3]});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 16)});
                  break;
               case 7:
                  var1 = var3;
                  if (var3 == 0) {
                     var1 = 2;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)var1});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte)(var3 + 5)});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, 0});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, 0});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, 0});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, 0});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, 0});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, 0});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, 0});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 0});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 0});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, (byte)(var3 + 1)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 0});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -67, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte)((byte)var3 + 1)});
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            var2 = var6.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -999746299:
                  if (var6.equals("_330_Rotating_Speed_report")) {
                     var5 = 0;
                  }
                  break;
               case 1807265329:
                  if (var6.equals("_349_setting_5_0")) {
                     var5 = 1;
                  }
                  break;
               case 1956080709:
                  if (var6.equals("_330_car_Speed_report")) {
                     var5 = 2;
                  }
                  break;
               case 1989254414:
                  if (var6.equals("_176_car_setting_title_21")) {
                     var5 = 3;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte)var3});
                  SharePreUtil.setIntValue(this.this$0.mContext, "RotatingSpeed", var3);
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var3});
                  SharePreUtil.setIntValue(this.this$0.mContext, "CarSpeed", var3);
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, (byte)var3});
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(this.mContext);
      var3.setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(33);
            this.this$0.activeRequest(107);
         }
      });
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var3.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      var3.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 21, 1});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 22, 1});
         }
      });
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 54, 1});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 53, 1});
         }
      });
      SyncPageUiSet var4 = this.getSyncPageUiSet(this.mContext);
      var4.setOnSyncStateChangeListener(new OnSyncStateChangeListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onResume() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
         }

         public void onStop() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
         }
      });
      var4.setOnSyncItemClickListener(new OnSyncItemClickListener(this, var4, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SyncPageUiSet val$syncPageUiSet;

         {
            this.this$0 = var1;
            this.val$syncPageUiSet = var2;
            this.val$context = var3;
         }

         public void onKeyboardBtnClick(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case -1886439238:
                  if (var1.equals("number_0")) {
                     var2 = 0;
                  }
                  break;
               case -1886439237:
                  if (var1.equals("number_1")) {
                     var2 = 1;
                  }
                  break;
               case -1886439236:
                  if (var1.equals("number_2")) {
                     var2 = 2;
                  }
                  break;
               case -1886439235:
                  if (var1.equals("number_3")) {
                     var2 = 3;
                  }
                  break;
               case -1886439234:
                  if (var1.equals("number_4")) {
                     var2 = 4;
                  }
                  break;
               case -1886439233:
                  if (var1.equals("number_5")) {
                     var2 = 5;
                  }
                  break;
               case -1886439232:
                  if (var1.equals("number_6")) {
                     var2 = 6;
                  }
                  break;
               case -1886439231:
                  if (var1.equals("number_7")) {
                     var2 = 7;
                  }
                  break;
               case -1886439230:
                  if (var1.equals("number_8")) {
                     var2 = 8;
                  }
                  break;
               case -1886439229:
                  if (var1.equals("number_9")) {
                     var2 = 9;
                  }
                  break;
               case -1335157162:
                  if (var1.equals("device")) {
                     var2 = 10;
                  }
                  break;
               case -1224574323:
                  if (var1.equals("hangup")) {
                     var2 = 11;
                  }
                  break;
               case -988476804:
                  if (var1.equals("pickup")) {
                     var2 = 12;
                  }
                  break;
               case 3548:
                  if (var1.equals("ok")) {
                     var2 = 13;
                  }
                  break;
               case 3739:
                  if (var1.equals("up")) {
                     var2 = 14;
                  }
                  break;
               case 96964:
                  if (var1.equals("aux")) {
                     var2 = 15;
                  }
                  break;
               case 3089570:
                  if (var1.equals("down")) {
                     var2 = 16;
                  }
                  break;
               case 3237038:
                  if (var1.equals("info")) {
                     var2 = 17;
                  }
                  break;
               case 3317767:
                  if (var1.equals("left")) {
                     var2 = 18;
                  }
                  break;
               case 3347807:
                  if (var1.equals("menu")) {
                     var2 = 19;
                  }
                  break;
               case 3377907:
                  if (var1.equals("next")) {
                     var2 = 20;
                  }
                  break;
               case 3449395:
                  if (var1.equals("prev")) {
                     var2 = 21;
                  }
                  break;
               case 3540562:
                  if (var1.equals("star")) {
                     var2 = 22;
                  }
                  break;
               case 3645646:
                  if (var1.equals("well")) {
                     var2 = 23;
                  }
                  break;
               case 108511772:
                  if (var1.equals("right")) {
                     var2 = 24;
                  }
                  break;
               case 109418880:
                  if (var1.equals("shuff")) {
                     var2 = 25;
                  }
            }

            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 13});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 14});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 15});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 16});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 17});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 18});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 19});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 20});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 21});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 22});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 4});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 5});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 12});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 10});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 11});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 6});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 25});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 9});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 8});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 23});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 24});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 26});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 7});
            }

         }

         public void onKeyboardBtnLongClick(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case -1886439238:
                  if (var1.equals("number_0")) {
                     var2 = 0;
                  }
                  break;
               case -1886439237:
                  if (var1.equals("number_1")) {
                     var2 = 1;
                  }
                  break;
               case -1886439236:
                  if (var1.equals("number_2")) {
                     var2 = 2;
                  }
                  break;
               case -1886439235:
                  if (var1.equals("number_3")) {
                     var2 = 3;
                  }
                  break;
               case -1886439234:
                  if (var1.equals("number_4")) {
                     var2 = 4;
                  }
                  break;
               case -1886439233:
                  if (var1.equals("number_5")) {
                     var2 = 5;
                  }
                  break;
               case -1886439232:
                  if (var1.equals("number_6")) {
                     var2 = 6;
                  }
                  break;
               case -1886439231:
                  if (var1.equals("number_7")) {
                     var2 = 7;
                  }
                  break;
               case -1886439230:
                  if (var1.equals("number_8")) {
                     var2 = 8;
                  }
                  break;
               case -1886439229:
                  if (var1.equals("number_9")) {
                     var2 = 9;
                  }
            }

            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 48});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 49});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 50});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 51});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 52});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 53});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 54});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 55});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 56});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 57});
            }

         }

         public void onLeftIconClick(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case 103772132:
                  if (var1.equals("media")) {
                     var2 = 0;
                  }
                  break;
               case 106642798:
                  if (var1.equals("phone")) {
                     var2 = 1;
                  }
                  break;
               case 112386354:
                  if (var1.equals("voice")) {
                     var2 = 2;
                  }
                  break;
               case 503739367:
                  if (var1.equals("keyboard")) {
                     var2 = 3;
                  }
            }

            switch (var2) {
               case 0:
                  if (MsgMgr.SYNC_VERSION.VERSION_V1.equals(MsgMgr.mSyncVersion)) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -127});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                  }
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 1});
                  break;
               case 3:
                  if (this.this$0.mIsFeaturesKeyboard) {
                     this.this$0.mIsFeaturesKeyboard = false;
                     this.val$syncPageUiSet.setKeyboardActions(this.this$0.getDiagitalKeyboardActions());
                  } else {
                     this.this$0.mIsFeaturesKeyboard = true;
                     this.val$syncPageUiSet.setKeyboardActions(this.this$0.getFeaturesKeyboardActions());
                  }

                  this.this$0.getMsgMgr(this.val$context).updateSync((Bundle)null);
            }

         }

         public void onListItemClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)(var1 + 145)});
         }

         public void onSoftKeyClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte)(var1 + 28)});
         }
      });
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda0());
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(20);
            this.this$0.activeRequest(34);
            this.this$0.activeRequest(35);
            this.this$0.activeRequest(36);
            this.this$0.activeRequest(37);
            this.this$0.activeRequest(103);
            this.this$0.activeRequest(104);
            this.this$0.activeRequest(106);
         }
      });
   }

   private void activeRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 81, (byte)var1});
   }

   private void activeRequestData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   // $FF: synthetic method
   static void lambda$new$0(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte)(var0 + 18)});
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = new MyPanoramicView(var1);
      }

      return this.mPanoramicView;
   }

   public String[][] getDiagitalKeyboardActions() {
      if (this.mDiagitalKeyboardActions == null) {
         this.mDiagitalKeyboardActions = new String[][]{{"number_1", "number_2", "number_3"}, {"number_4", "number_5", "number_6"}, {"number_7", "number_8", "number_9"}, {"star", "number_0", "well"}, {"pickup", "hangup", "null"}};
      }

      return this.mDiagitalKeyboardActions;
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

   public String[][] getFeaturesKeyboardActions() {
      if (this.mFeaturesKeyboardActions == null) {
         this.mFeaturesKeyboardActions = new String[][]{{"null", "up", "null"}, {"left", "ok", "right"}, {"null", "down", "null"}, {"info", "menu", "device"}, {"prev", "shuff", "next"}};
      }

      return this.mFeaturesKeyboardActions;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
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
      this.getMsgMgr(this.mContext).updateSettings(2, 0, SharePreUtil.getIntValue(this.mContext, "RotatingSpeed", 0));
      this.getMsgMgr(this.mContext).updateSettings(2, 1, SharePreUtil.getIntValue(this.mContext, "CarSpeed", 0));
   }
}
