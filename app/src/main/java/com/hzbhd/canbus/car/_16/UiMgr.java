package com.hzbhd.canbus.car._16;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private int can_different_id;
   private int each_can_id;
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private String[] mAirBtnListRearBottom;
   private Context mContext;
   private int mHistoricalLeft = 6;
   private int mMinuteFuelLeft = 5;
   MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontBottom[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontRight[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         Log.d("ZhouRui", "拿到的索引值=" + var1);
         Log.d("ZhouRui", "拿到的功能值=" + this.this$0.mAirBtnListFrontTop[var1]);
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontLeft[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendRearAirCommand(var2.mAirBtnListRearBottom[var1]);
      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendAirCommand(9);
         } else {
            this.this$0.sendAirCommandC7(1, 0);
         }

      }

      public void onClickRight() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendAirCommand(10);
         } else {
            this.this$0.sendAirCommandC7(1, 1);
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirCommand(40);
      }

      public void onClickRight() {
         this.this$0.sendAirCommand(41);
      }
   };
   OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         if (var1 == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 7)});
         } else if (var1 == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(-var2 + 7)});
         }

      }
   };
   OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         if (var1 == AmplifierActivity.AmplifierBand.VOLUME) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var2});
         } else if (var1 == AmplifierActivity.AmplifierBand.BASS) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
         } else if (var1 == AmplifierActivity.AmplifierBand.MIDDLE) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
         } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
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
         this.this$0.sendAirCommand(12);
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
         this.this$0.sendAirCommand(14);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendAirCommand(12);
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(11);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendAirCommand(14);
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      static void lambda$onClickBottomBtnItem$0() {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 10, 1});

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var1) {
            var1.printStackTrace();
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -123, 10, 0});
      }

      // $FF: synthetic method
      static void lambda$onClickBottomBtnItem$1() {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 9, 1});

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var1) {
            var1.printStackTrace();
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -123, 9, 0});
      }

      public void onClickBottomBtnItem(int var1) {
         String var3 = this.this$0.mOriginalCarDevicePageUiSet.getRowBottomBtnAction()[var1];
         var3.hashCode();
         int var2 = var3.hashCode();
         byte var4 = -1;
         switch (var2) {
            case -938285885:
               if (var3.equals("random")) {
                  var4 = 0;
               }
               break;
            case -934531685:
               if (var3.equals("repeat")) {
                  var4 = 1;
               }
               break;
            case -841905119:
               if (var3.equals("prev_disc")) {
                  var4 = 2;
               }
               break;
            case 3739:
               if (var3.equals("up")) {
                  var4 = 3;
               }
               break;
            case 3089570:
               if (var3.equals("down")) {
                  var4 = 4;
               }
               break;
            case 3317767:
               if (var3.equals("left")) {
                  var4 = 5;
               }
               break;
            case 3357091:
               if (var3.equals("mode")) {
                  var4 = 6;
               }
               break;
            case 3540994:
               if (var3.equals("stop")) {
                  var4 = 7;
               }
               break;
            case 95131878:
               if (var3.equals("cycle")) {
                  var4 = 8;
               }
               break;
            case 108511772:
               if (var3.equals("right")) {
                  var4 = 9;
               }
               break;
            case 1216748385:
               if (var3.equals("next_disc")) {
                  var4 = 10;
               }
               break;
            case 1922620715:
               if (var3.equals("play_pause")) {
                  var4 = 11;
               }
         }

         switch (var4) {
            case 0:
               if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(1, 0);
               } else if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, 0});
               }
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, 0});
               break;
            case 2:
               if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 7, 0});
               } else if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(7, 0);
               }
               break;
            case 3:
               if (MsgMgr.mOriginalInt == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 22, 0});
               } else if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 5, 0});
               } else if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(5, 0);
               }
               break;
            case 4:
               if (MsgMgr.mOriginalInt == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 21, 0});
               } else if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 4, 0});
               } else if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(4, 0);
               }
               break;
            case 5:
               if (MsgMgr.mOriginalInt == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 18, 0});
               } else if (MsgMgr.mOriginalInt == 2) {
                  (new Thread(new UiMgr$8$$ExternalSyntheticLambda0())).start();
               } else if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(10, 0);
               }
               break;
            case 6:
               if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 98, 1});
               }
               break;
            case 7:
               if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 99, 1});
               }
               break;
            case 8:
               if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(2, 0);
               }
               break;
            case 9:
               if (MsgMgr.mOriginalInt == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 19, 0});
               } else if (MsgMgr.mOriginalInt == 2) {
                  (new Thread(new UiMgr$8$$ExternalSyntheticLambda1())).start();
               } else if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(9, 0);
               }
               break;
            case 10:
               if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 6, 0});
               } else if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(6, 0);
               }
               break;
            case 11:
               if (MsgMgr.mOriginalInt == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 20, 0});
               } else if (MsgMgr.mOriginalInt == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 3, 0});
               } else if (MsgMgr.mOriginalInt == 3) {
                  this.this$0.sendUsbCmd(3, 0);
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 100, 1});
               }
         }

      }
   };
   OnOriginalSongItemClickListener mOnOriginalSongItemClickListener = new OnOriginalSongItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onSongItemClick(int var1) {
         if (MsgMgr.mOriginalInt == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 16, (byte)(var1 + 1)});
         } else if (MsgMgr.mOriginalInt == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 8, (byte)(var1 + 1)});
         }

      }

      public void onSongItemLongClick(int var1) {
         if (MsgMgr.mOriginalInt == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 17, (byte)(var1 + 1)});
         }

      }
   };
   OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickTopBtnItem(int var1) {
         String var3 = this.this$0.mOriginalCarDevicePageUiSet.getRowTopBtnAction()[var1];
         var3.hashCode();
         int var2 = var3.hashCode();
         byte var4 = -1;
         switch (var2) {
            case -938285885:
               if (var3.equals("random")) {
                  var4 = 0;
               }
               break;
            case -934531685:
               if (var3.equals("repeat")) {
                  var4 = 1;
               }
               break;
            case 3327275:
               if (var3.equals("lock")) {
                  var4 = 2;
               }
               break;
            case 106858757:
               if (var3.equals("power")) {
                  var4 = 3;
               }
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, 0});
               break;
            case 2:
               if (MsgMgr.DeviceRearLock) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 97, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 97, 1});
               }
               break;
            case 3:
               if (MsgMgr.DevicePower) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 96, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 96, 1});
               }
         }

      }
   };
   OnPanoramicItemTouchListener mOnPanoramicItemTouchListener = new OnPanoramicItemTouchListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onTouchItem(MotionEvent var1) {
         int var6 = (int)var1.getX();
         int var7 = (int)var1.getY();
         var6 = var6 * 1023 / this.this$0.screenWidth;
         var7 = var7 * 1000 / this.this$0.screenHeight;
         byte var3 = (byte)(var6 & 255);
         byte var4 = (byte)(var6 >> 8 & 255);
         byte var5 = (byte)(var7 & 255);
         byte var2 = (byte)(var7 >> 8 & 255);
         if (var1.getAction() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -104, 1, var4, var3, var2, var5, 0});
         } else if (var1.getAction() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, var4, var3, var2, var5, 0});
         }

      }
   };
   OnAirSeatClickListener mOnRearAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.sendAirCommand(43);
      }

      public void onRightSeatClick() {
         this.this$0.sendAirCommand(43);
      }
   };
   OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         String var3 = ((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getTitleSrn();
         var4.hashCode();
         if (!var4.equals("clear_data")) {
            if (var4.equals("update_data")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
            }
         } else {
            var3.hashCode();
            if (!var3.equals("_55_0x6A_0x04_0x02")) {
               if (var3.equals("_16_title_17")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
            }
         }

      }
   };
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         if (!var4.equals("hiworld_jeep_123_0x60_data1_65")) {
            if (var4.equals("radar_volume")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
         }

      }
   };
   OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var7.hashCode();
         int var5 = var7.hashCode();
         byte var4 = -1;
         switch (var5) {
            case -2116235428:
               if (var7.equals("_55_0x65_data1_bit21")) {
                  var4 = 0;
               }
               break;
            case -1830876700:
               if (var7.equals("front_radar_distance")) {
                  var4 = 1;
               }
               break;
            case -1091778295:
               if (var7.equals("rear_radar_distance")) {
                  var4 = 2;
               }
               break;
            case -902681199:
               if (var7.equals("_18_vehicle_setting_item_1_0")) {
                  var4 = 3;
               }
               break;
            case -902681198:
               if (var7.equals("_18_vehicle_setting_item_1_1")) {
                  var4 = 4;
               }
               break;
            case -902681197:
               if (var7.equals("_18_vehicle_setting_item_1_2")) {
                  var4 = 5;
               }
               break;
            case -902681196:
               if (var7.equals("_18_vehicle_setting_item_1_3")) {
                  var4 = 6;
               }
               break;
            case -902681195:
               if (var7.equals("_18_vehicle_setting_item_1_4")) {
                  var4 = 7;
               }
               break;
            case -902681194:
               if (var7.equals("_18_vehicle_setting_item_1_5")) {
                  var4 = 8;
               }
               break;
            case -902680234:
               if (var7.equals("_18_vehicle_setting_item_2_4")) {
                  var4 = 9;
               }
               break;
            case -902680233:
               if (var7.equals("_18_vehicle_setting_item_2_5")) {
                  var4 = 10;
               }
               break;
            case -902680232:
               if (var7.equals("_18_vehicle_setting_item_2_6")) {
                  var4 = 11;
               }
               break;
            case -902680231:
               if (var7.equals("_18_vehicle_setting_item_2_7")) {
                  var4 = 12;
               }
               break;
            case -796889975:
               if (var7.equals("radar_display")) {
                  var4 = 13;
               }
               break;
            case -723657951:
               if (var7.equals("_11_0x26_data2_bit20")) {
                  var4 = 14;
               }
               break;
            case -556746441:
               if (var7.equals("hiworld_jeep_123_0xC1_data2")) {
                  var4 = 15;
               }
               break;
            case 97333442:
               if (var7.equals("amplifier_switch")) {
                  var4 = 16;
               }
               break;
            case 102584022:
               if (var7.equals("language_setup")) {
                  var4 = 17;
               }
               break;
            case 163845763:
               if (var7.equals("_11_0x26_data3_bit32")) {
                  var4 = 18;
               }
               break;
            case 426787759:
               if (var7.equals("_16_Seat_sliding")) {
                  var4 = 19;
               }
               break;
            case 957932200:
               if (var7.equals("light_ctrl_3")) {
                  var4 = 20;
               }
               break;
            case 957932201:
               if (var7.equals("light_ctrl_4")) {
                  var4 = 21;
               }
               break;
            case 1051349540:
               if (var7.equals("_11_0x26_data4_bit65")) {
                  var4 = 22;
               }
               break;
            case 1475566907:
               if (var7.equals("_55_0x65_data1_bit54_item1")) {
                  var4 = 23;
               }
               break;
            case 1591925886:
               if (var7.equals("_55_0x67_data1_bit32")) {
                  var4 = 24;
               }
               break;
            case 1845541289:
               if (var7.equals("_16_add_setting_1")) {
                  var4 = 25;
               }
               break;
            case 1845541290:
               if (var7.equals("_16_add_setting_2")) {
                  var4 = 26;
               }
               break;
            case 1845541291:
               if (var7.equals("_16_add_setting_3")) {
                  var4 = 27;
               }
               break;
            case 1845541292:
               if (var7.equals("_16_add_setting_4")) {
                  var4 = 28;
               }
               break;
            case 1845541293:
               if (var7.equals("_16_add_setting_5")) {
                  var4 = 29;
               }
               break;
            case 2015341706:
               if (var7.equals("_16_title_19")) {
                  var4 = 30;
               }
               break;
            case 2015341728:
               if (var7.equals("_16_title_20")) {
                  var4 = 31;
               }
               break;
            case 2015341730:
               if (var7.equals("_16_title_22")) {
                  var4 = 32;
               }
               break;
            case 2015341731:
               if (var7.equals("_16_title_23")) {
                  var4 = 33;
               }
               break;
            case 2081713660:
               if (var7.equals("_18_vehicle_setting_item_3_43")) {
                  var4 = 34;
               }
               break;
            case 2081713724:
               if (var7.equals("_18_vehicle_setting_item_3_65")) {
                  var4 = 35;
               }
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var3});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)(var3 + 2)});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
               break;
            case 9:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
               break;
            case 10:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var3});
               break;
            case 11:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
               break;
            case 12:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
               break;
            case 13:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var3});
               break;
            case 14:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)var3});
               break;
            case 15:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)var3});
               break;
            case 16:
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var3});
               break;
            case 17:
               if (var3 <= 10) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)var3});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)(var3 + 5)});
               }
               break;
            case 18:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var3});
               break;
            case 19:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte)var3});
               break;
            case 20:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
               break;
            case 21:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
               break;
            case 22:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)var3});
               break;
            case 23:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var3});
               break;
            case 24:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var3});
               break;
            case 25:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte)var3});
               break;
            case 26:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
               break;
            case 27:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)(var3 + 3)});
               break;
            case 28:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)(var3 + 3)});
               break;
            case 29:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)(var3 + 1)});
               break;
            case 30:
               boolean var6;
               if (var3 == 1) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte)DataHandleUtils.setIntByteWithBit(0, 7, var6)});
               break;
            case 31:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte)var3});
               break;
            case 32:
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
               } else if (var3 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
               }
               break;
            case 33:
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
               break;
            case 34:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
               break;
            case 35:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)(var3 + 4)});
         }

         UiMgr var8 = this.this$0;
         if (var1 == var8.getSettingLeftIndexes(var8.mContext, "_16_title_21")) {
            var8 = this.this$0;
            if (var2 == var8.getSettingRightIndex(var8.mContext, "_16_title_21", "_16_ampl_settings_Position")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, (byte)var3});
            }
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendAirCommand(2);
         } else {
            this.this$0.sendAirCommandC7(3, 0);
         }

      }

      public void onClickUp() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendAirCommand(3);
         } else {
            this.this$0.sendAirCommandC7(3, 1);
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendAirCommand(4);
         } else {
            this.this$0.sendAirCommandC7(4, 0);
         }

      }

      public void onClickUp() {
         if (this.this$0.isPortrait()) {
            this.this$0.sendAirCommand(5);
         } else {
            this.this$0.sendAirCommandC7(4, 1);
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(38);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(39);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(65);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(66);
      }
   };
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SettingPageUiSet mSettingPageUiSet;
   private int screenHeight;
   private int screenWidth;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.initUI(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.mSettingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      this.mSettingPageUiSet.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
      this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      this.mSettingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(50, 0);
            this.this$0.activeRequest(49, 0);
            this.this$0.activeRequest(80, 0);
            this.this$0.activeRequest(30, 0);
            this.this$0.activeRequest(38, 0);
            this.this$0.activeRequest(82, 0);
            this.this$0.activeRequest(80, 0);
            this.this$0.activeRequest(35, 0);
         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      String[][] var4 = var3.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var4[0];
      this.mAirBtnListFrontLeft = var4[1];
      this.mAirBtnListFrontRight = var4[2];
      this.mAirBtnListFrontBottom = var4[3];
      this.mAirBtnListRearBottom = var3.getRearArea().getLineBtnAction()[3];
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      var3.getRearArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerRear);
      var3.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerRearLeft, null, this.mOnUpDownClickListenerRearRight});
      var3.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      var3.getRearArea().setOnAirSeatClickListener(this.mOnRearAirSeatClickListener);
      var3.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(40, 0);
            this.this$0.activeRequest(88, 0);
         }
      });
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
      var5.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
      var5.setOnAmplifierCreateAndDestroyListener(new OnAmplifierCreateAndDestroyListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void create() {
            this.this$0.activeRequest(49, 0);
         }

         public void destroy() {
         }
      });
      OriginalCarDevicePageUiSet var6 = this.getOriginalCarDevicePageUiSet(var1);
      this.mOriginalCarDevicePageUiSet = var6;
      var6.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
      this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
      this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mOnOriginalSongItemClickListener);
      this.screenWidth = var1.getResources().getDisplayMetrics().widthPixels;
      this.screenHeight = var1.getResources().getDisplayMetrics().heightPixels;
      this.getParkPageUiSet(var1).setOnPanoramicItemTouchListener(this.mOnPanoramicItemTouchListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(34, 0);
            this.this$0.activeRequest(33, 0);
            this.this$0.activeRequest(43, 0);
            this.this$0.activeRequest(39, 0);
            this.this$0.activeRequest(36, 0);
            this.this$0.activeRequest(35, 0);
         }
      });
      this.getHybirdPageUiSet(var1).setOnHybirdPageStatusListener(new OnHybirdPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 31, 0});
         }
      });
   }

   private void activeRequest(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1, (byte)var2});
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private boolean hasOriginalCarDevice() {
      int var1 = this.can_different_id;
      boolean var2;
      if (var1 != 4 && var1 != 9 && this.each_can_id != 33) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private void initUI(Context var1) {
      this.can_different_id = this.getCurrentCarId();
      this.each_can_id = ((MsgMgr)MsgMgrFactory.getCanMsgMgr(var1)).getEachCanId();
      if (!this.isCHR_EV()) {
         this.removeDriveData(var1, "_16_min_title_2");
      }

      int var2 = this.can_different_id;
      if (var2 != 4 && var2 != 5) {
         if (var2 == 6 || var2 == 7 || var2 == 8) {
            this.removeDriveDateItemForTitles(var1, new String[]{"_16_parking_brake", "_16_ig", "_16_door_move"});
         }
      } else {
         this.removeMainPrjBtnByName(var1, "drive_data");
      }

      var2 = this.can_different_id;
      if (var2 == 4 || var2 == 6 || var2 == 7 || var2 == 8) {
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

      if (!this.hasAmplifier()) {
         this.removeMainPrjBtnByName(var1, "amplifier");
         this.removeSettingLeftItemByNameList(var1, new String[]{"_16_title_21"});
      }

      var2 = this.can_different_id;
      if (var2 == 4 || var2 == 5 || var2 == 6 || var2 == 7 || var2 == 8) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"light_settings", "lock_settings", "airSetting", "other_set", "radar_settings"});
      }

      if (!this.isHybrid()) {
         this.removeMainPrjBtnByName(var1, "hybird");
      }

      if (!this.hasOriginalCarDevice()) {
         this.removeMainPrjBtnByName(var1, "original_car_device");
      }

      if (this.can_different_id == 5) {
         this.removeMainPrjBtnByName(var1, "air");
      }

      var2 = this.can_different_id;
      if (var2 != 3 && var2 != 4 && var2 != 9 && this.each_can_id != 58) {
         Log.d("ZhouRui", "移除negative_ion");
         this.getAirUiSet(var1).getFrontArea().setShowSeatHeat(false);
         this.getAirUiSet(var1).getFrontArea().setShowSeatCold(false);
         this.removeFrontAirButtonByName(var1, "negative_ion");
         this.removeFrontAirButtonByName(var1, "swing");
         this.removeFrontAirButtonByName(var1, "pollrn_removal");
         this.removeFrontAirButtonByName(var1, "front_window_heat");
      }

      if (this.can_different_id == 3) {
         this.getAirUiSet(var1).getFrontArea().setDisableBtnArray(new String[]{"negative_ion", "pollrn_removal"});
      }

      if (this.can_different_id != 4) {
         this.getAirUiSet(var1).getFrontArea().setDisableBtnArray(new String[]{"swing", "front_window_heat"});
      }

      if (this.each_can_id == 58) {
         this.getAirUiSet(var1).getFrontArea().setDisableBtnArray(new String[]{"negative_ion", "pollrn_removal", "swing", "front_window_heat"});
      }

      if (this.can_different_id != 9) {
         this.removeFrontAirButtonByName(var1, "econ");
         this.removeRearAirButtonByName(var1, "rear");
      }

   }

   private boolean isCHR_EV() {
      boolean var1;
      if (this.can_different_id == 3 && this.each_can_id == 19) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isHybrid() {
      int var1 = this.each_can_id;
      boolean var3 = true;
      boolean var2 = var3;
      if (var1 != 30) {
         var2 = var3;
         if (var1 != 31) {
            var2 = var3;
            if (var1 != 32) {
               var2 = var3;
               if (var1 != 19) {
                  var2 = var3;
                  if (var1 != 1) {
                     if (var1 == 67) {
                        var2 = var3;
                     } else {
                        var2 = false;
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
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
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   private void sendAirCommand(String var1) {
      byte var2;
      label122: {
         Log.d("ZhouRui", "点击功能=" + var1);
         switch (var1) {
            case "pollrn_removal":
               var2 = 11;
               break label122;
            case "front_defog":
               var2 = 4;
               break label122;
            case "rear_defog":
               var2 = 5;
               break label122;
            case "blow_positive":
               var2 = 1;
               break label122;
            case "blow_negative":
               var2 = 2;
               break label122;
            case "front_window_heat":
               var2 = 9;
               break label122;
            case "ac":
               var2 = 7;
               break label122;
            case "dual":
               var2 = 3;
               break label122;
            case "econ":
               var2 = 13;
               break label122;
            case "power":
               var2 = 0;
               break label122;
            case "swing":
               var2 = 10;
               break label122;
            case "negative_ion":
               var2 = 12;
               break label122;
            case "in_out_cycle":
               var2 = 8;
               break label122;
            case "auto_wind_light":
               var2 = 14;
               break label122;
            case "auto_wind_strong":
               var2 = 6;
               break label122;
         }

         var2 = -1;
      }

      switch (var2) {
         case 0:
            if (this.isPortrait()) {
               this.sendAirCommand(1);
            } else {
               this.sendAirCommandC7(0, 7);
            }
            break;
         case 1:
            if (this.isPortrait()) {
               if (this.isLanKu()) {
                  this.sendAirCommand(68);
               } else {
                  this.sendAirCommand(8);
               }
            } else {
               this.sendAirCommandC7(0, 6);
            }
            break;
         case 2:
            if (this.isPortrait()) {
               if (this.isLanKu()) {
                  this.sendAirCommand(68);
               } else {
                  this.sendAirCommand(7);
               }
            } else {
               this.sendAirCommandC7(0, 6);
            }
            break;
         case 3:
            if (this.isPortrait()) {
               this.sendAirCommand(16);
            } else {
               this.sendAirCommandC7(1, 3);
            }
            break;
         case 4:
            if (this.isPortrait()) {
               this.sendAirCommand(19);
            } else {
               this.sendAirCommandC7(0, 4);
            }
            break;
         case 5:
            if (this.isPortrait()) {
               this.sendAirCommand(20);
            } else {
               this.sendAirCommandC7(1, 2);
            }
            break;
         case 6:
            if (this.isPortrait()) {
               this.sendAirCommand(21);
            } else {
               this.sendAirCommandC7(0, 5);
            }
            break;
         case 7:
            if (this.isPortrait()) {
               this.sendAirCommand(23);
            } else {
               this.sendAirCommandC7(0, 1);
            }
            break;
         case 8:
            if (this.isPortrait()) {
               this.sendAirCommand(25);
            } else {
               this.sendAirCommandC7(2, 0);
            }
            break;
         case 9:
            if (this.isPortrait()) {
               this.sendAirCommand(72);
            } else {
               this.sendAirCommandC7(1, 4);
            }
            break;
         case 10:
            if (this.isPortrait()) {
               this.sendAirCommand(80);
            } else {
               this.sendAirCommandC7(1, 6);
            }
            break;
         case 11:
            if (this.isPortrait()) {
               this.sendAirCommand(32);
            } else {
               this.sendAirCommandC7(1, 5);
            }
            break;
         case 12:
            if (this.isPortrait()) {
               this.sendAirCommand(33);
            } else {
               this.sendAirCommandC7(1, 7);
            }
            break;
         case 13:
            this.sendAirCommandC7(4, 2);
      }

   }

   private void sendAirCommandC7(int var1, int var2) {
      int var3;
      int var4;
      int var5;
      int var6;
      int var7;
      int var8;
      int var9;
      int var10;
      label33: {
         label32: {
            label31: {
               if (var1 != 0) {
                  if (var1 == 1) {
                     var3 = DataHandleUtils.setIntByteWithBit(0, var2, true);
                     var8 = DataHandleUtils.setIntByteWithBit(0, var2, false);
                     var2 = 0;
                     var9 = 0;
                     break label31;
                  }

                  if (var1 == 2) {
                     var4 = DataHandleUtils.setIntByteWithBit(0, var2, true);
                     var7 = DataHandleUtils.setIntByteWithBit(0, var2, false);
                     var2 = 0;
                     var3 = 0;
                     var9 = var3;
                     var8 = var3;
                     var1 = var3;
                     break label32;
                  }

                  if (var1 == 3) {
                     var5 = DataHandleUtils.setIntByteWithBit(0, var2, true);
                     int var11 = DataHandleUtils.setIntByteWithBit(0, var2, false);
                     var2 = 0;
                     var3 = 0;
                     var4 = var3;
                     var6 = var3;
                     var9 = var3;
                     var8 = var3;
                     var10 = var3;
                     var7 = var3;
                     var1 = var11;
                     break label33;
                  }

                  if (var1 == 4) {
                     var6 = DataHandleUtils.setIntByteWithBit(0, var2, true);
                     var10 = DataHandleUtils.setIntByteWithBit(0, var2, false);
                     var2 = 0;
                     var3 = 0;
                     var4 = var3;
                     var5 = var3;
                     var9 = var3;
                     var8 = var3;
                     var7 = var3;
                     var1 = var3;
                     break label33;
                  }

                  var2 = 0;
                  var9 = 0;
               } else {
                  var1 = DataHandleUtils.setIntByteWithBit(0, var2, true);
                  var9 = DataHandleUtils.setIntByteWithBit(0, var2, false);
                  var2 = var1;
               }

               var8 = 0;
               var3 = 0;
            }

            var7 = 0;
            var4 = 0;
            var1 = 0;
         }

         var6 = 0;
         var5 = 0;
         var10 = var1;
      }

      (new Thread(this, var2, var3, var4, var5, var6, var9, var8, var7, var1, var10) {
         final UiMgr this$0;
         final int val$finalData;
         final int val$finalData0_false;
         final int val$finalData1;
         final int val$finalData1_false;
         final int val$finalData2;
         final int val$finalData2_false;
         final int val$finalData3;
         final int val$finalData3_false;
         final int val$finalData4;
         final int val$finalData4_false;

         {
            this.this$0 = var1;
            this.val$finalData = var2;
            this.val$finalData1 = var3;
            this.val$finalData2 = var4;
            this.val$finalData3 = var5;
            this.val$finalData4 = var6;
            this.val$finalData0_false = var7;
            this.val$finalData1_false = var8;
            this.val$finalData2_false = var9;
            this.val$finalData3_false = var10;
            this.val$finalData4_false = var11;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)this.val$finalData, (byte)this.val$finalData1, (byte)this.val$finalData2, (byte)this.val$finalData3, (byte)this.val$finalData4});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)this.val$finalData0_false, (byte)this.val$finalData1_false, (byte)this.val$finalData2_false, (byte)this.val$finalData3_false, (byte)this.val$finalData4_false});
         }
      }).start();
   }

   private void sendRearAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -713186454:
            if (var1.equals("rear_auto")) {
               var2 = 0;
            }
            break;
         case -620266838:
            if (var1.equals("rear_power")) {
               var2 = 1;
            }
            break;
         case 3496356:
            if (var1.equals("rear")) {
               var2 = 2;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(45);
            break;
         case 1:
            this.sendAirCommand(44);
            break;
         case 2:
            this.sendAirCommandC7(4, 3);
      }

   }

   private void sendUsbCmd(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)var1, (byte)var2});
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
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

      return 404;
   }

   public boolean hasAmplifier() {
      int var1 = this.each_can_id;
      boolean var3;
      if (var1 != 6 && var1 != 12 && var1 != 14) {
         int var2 = this.can_different_id;
         if (var2 != 4 && var2 != 6 && var2 != 7 && var2 != 8 && var2 != 9 && var1 != 67) {
            var3 = false;
            return var3;
         }
      }

      var3 = true;
      return var3;
   }

   public boolean hasAmplifierNoSettings() {
      int var1 = this.can_different_id;
      boolean var2;
      if (var1 != 4 && var1 != 6 && var1 != 7 && var1 != 8) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public boolean isLanKu() {
      boolean var1;
      if (this.each_can_id == 51) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (!SharePreUtil.getBoolValue(this.mContext, "share_16_is_suppot_tire", true)) {
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

   }
}
