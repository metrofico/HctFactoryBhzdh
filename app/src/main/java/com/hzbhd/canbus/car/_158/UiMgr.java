package com.hzbhd.canbus.car._158;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class UiMgr extends AbstractUiMgr {
   private static final String SHARE_157_AVERAGE_SPEED = "share_157_average_speed";
   private static final String SHARE_157_BSM_LINE = "share_157_bsm_line";
   private static final String SHARE_157_CTM_SYSTEM = "share_157_ctm_system";
   private static final String SHARE_157_DISPLAY = "share_157_display";
   private static final String SHARE_157_ENDURANCE_DISTANCE = "share_157_endurance_distance";
   static final String SHARE_157_FRONT_CAMERA_SWITCH = "share_157_front_camera_switch";
   static final String SHARE_157_FRONT_LINK_TURN_SIGNAL = "share_157_front_link_turn_signal";
   private static final String SHARE_157_LANGUAGE = "share_157_language";
   private static final String SHARE_157_NAVIGATION = "share_157_navigation";
   private static final String SHARE_157_PHONE = "share_157_phone";
   private static final String SHARE_157_SMS_EMAIL = "share_157_sms_email";
   private static final String SHARE_157_SOUND = "share_157_sound";
   private static final String SHARE_157_TURBINE = "share_157_turbine";
   private final int MSG_SEND_0xE0_AIR_COMMAND_UP = 1;
   private final String TAG = "_157_UiMgr";
   private int mDifferentId = this.getCurrentCarId();
   private int mEachId;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (Byte)var1.obj, 0});
         }

      }
   };
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      Log.i("_157_UiMgr", "UiMgr: ");
      if (!this.getMsgMgr(var1).isShowOriginalRadio()) {
         this.removeMainPrjBtnByName(var1, "original_car_device");
      }

      this.initSetting(var1);
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      int var2 = this.mDifferentId;
      ArrayList var4;
      if (var2 == 35) {
         var4 = new ArrayList();
         var4.add("_55_0x68_data0_bit31_item1");
         var4.add("_55_0x68_data0_bit31_item2");
         var4.add("_55_0x68_data0_bit31_item3");
         var4.add("_55_0x68_data0_bit31_item4");
         ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var3.getList().get(5)).getItemList().get(5)).setValueSrnArray(var4);
      } else if (var2 == 36) {
         var4 = new ArrayList();
         var4.add("_55_0x68_data0_bit31_item1");
         var4.add("_55_0x68_data1_bit54_item2");
         var4.add("_55_0x68_data0_bit31_item3");
         var4.add("_157_narrow");
         ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var3.getList().get(5)).getItemList().get(5)).setValueSrnArray(var4);
      } else if (var2 == 64) {
         var4 = new ArrayList();
         var4.add("_157_charge");
         var4.add("_55_0x69_data1_bit65_item2");
         var4.add("_55_0x69_data1_bit65_item3");
         ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var3.getList().get(0)).getItemList().get(0)).setValueSrnArray(var4);
         ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var3.getList().get(0)).getItemList().get(1)).setValueSrnArray(var4);
      }

      var3.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var3) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            Objects.requireNonNull(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn());
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
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -817088739:
                  if (var4.equals("_157_saturation")) {
                     var5 = 0;
                  }
                  break;
               case 61564828:
                  if (var4.equals("_157_brightness")) {
                     var5 = 1;
                  }
                  break;
               case 777692429:
                  if (var4.equals("_157_contrast")) {
                     var5 = 2;
                  }
                  break;
               case 1005119839:
                  if (var4.equals("_55_0x69_data1_bit20")) {
                     var5 = 3;
                  }
                  break;
               case 1401027296:
                  if (var4.equals("compass_zoom")) {
                     var5 = 4;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, (byte)(var3 + 5)});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte)(var3 + 5)});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte)(var3 + 5)});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)(var3 + 5)});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte)var3});
            }

         }
      });
      var3.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var3, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onConfirmClick(int var1, int var2) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -1857179533:
                  if (var4.equals("_157_delete_all_icons")) {
                     var5 = 0;
                  }
                  break;
               case -1586268672:
                  if (var4.equals("_41_default_all")) {
                     var5 = 1;
                  }
                  break;
               case -1502330376:
                  if (var4.equals("front_camera_switch")) {
                     var5 = 2;
                  }
                  break;
               case -1224494730:
                  if (var4.equals("_55_0x6E_0x06")) {
                     var5 = 3;
                  }
                  break;
               case -811374327:
                  if (var4.equals("compass_run_calibration")) {
                     var5 = 4;
                  }
                  break;
               case 1161474262:
                  if (var4.equals("_157_tmps_correction")) {
                     var5 = 5;
                  }
                  break;
               case 1214731670:
                  if (var4.equals("_157_amplifier_reset")) {
                     var5 = 6;
                  }
                  break;
               case 2107640043:
                  if (var4.equals("_157_panoramic_reset")) {
                     var5 = 7;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -10, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, 0});
                  break;
               case 2:
                  boolean var3 = SharePreUtil.getBoolValue(this.val$context, "share_157_front_camera_switch", false);
                  SharePreUtil.setBoolValue(this.val$context, "share_157_front_camera_switch", var3 ^ true);
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, 1});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, 0});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 0});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, 0});
            }

         }
      });
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var3, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            int var5 = var6.hashCode();
            byte var4 = -1;
            switch (var5) {
               case -2116235428:
                  if (var6.equals("_55_0x65_data1_bit21")) {
                     var4 = 0;
                  }
                  break;
               case -2116235332:
                  if (var6.equals("_55_0x65_data1_bit54")) {
                     var4 = 1;
                  }
                  break;
               case -2116235268:
                  if (var6.equals("_55_0x65_data1_bit76")) {
                     var4 = 2;
                  }
                  break;
               case -2114831402:
                  if (var6.equals("_157_rear_entertainment_system")) {
                     var4 = 3;
                  }
                  break;
               case -1904072502:
                  if (var6.equals("_301_volume_and_speed_linkage")) {
                     var4 = 4;
                  }
                  break;
               case -1880694973:
                  if (var6.equals("_157_phone")) {
                     var4 = 5;
                  }
                  break;
               case -1877710108:
                  if (var6.equals("_157_sound")) {
                     var4 = 6;
                  }
                  break;
               case -1832488345:
                  if (var6.equals("_55_0xE8_data5")) {
                     var4 = 7;
                  }
                  break;
               case -1546031052:
                  if (var6.equals("_157_back_mirror_fold")) {
                     var4 = 8;
                  }
                  break;
               case -1388813448:
                  if (var6.equals("_157_endurance_distance")) {
                     var4 = 9;
                  }
                  break;
               case -1194354345:
                  if (var6.equals("_157_display")) {
                     var4 = 10;
                  }
                  break;
               case -1018854949:
                  if (var6.equals("_157_trunk_sense_switch")) {
                     var4 = 11;
                  }
                  break;
               case -722490656:
                  if (var6.equals("_41_speed_distance_units")) {
                     var4 = 12;
                  }
                  break;
               case -625706385:
                  if (var6.equals("_157_auto_trunk")) {
                     var4 = 13;
                  }
                  break;
               case -549818908:
                  if (var6.equals("_41_tachometer_switch")) {
                     var4 = 14;
                  }
                  break;
               case -176819061:
                  if (var6.equals("_157_sms_email")) {
                     var4 = 15;
                  }
                  break;
               case -81334456:
                  if (var6.equals("_157_backlight_color")) {
                     var4 = 16;
                  }
                  break;
               case 102584022:
                  if (var6.equals("language_setup")) {
                     var4 = 17;
                  }
                  break;
               case 117616127:
                  if (var6.equals("_55_0x69_data0_bit10")) {
                     var4 = 18;
                  }
                  break;
               case 463009380:
                  if (var6.equals("_157_turbine")) {
                     var4 = 19;
                  }
                  break;
               case 511896724:
                  if (var6.equals("_157_revers_mode")) {
                     var4 = 20;
                  }
                  break;
               case 683897978:
                  if (var6.equals("_157_average_speed")) {
                     var4 = 21;
                  }
                  break;
               case 704422172:
                  if (var6.equals("_55_0x67_data0_bit20")) {
                     var4 = 22;
                  }
                  break;
               case 868863258:
                  if (var6.equals("_41_key_remote_unlock")) {
                     var4 = 23;
                  }
                  break;
               case 1005119904:
                  if (var6.equals("_55_0x69_data1_bit43")) {
                     var4 = 24;
                  }
                  break;
               case 1005119968:
                  if (var6.equals("_55_0x69_data1_bit65")) {
                     var4 = 25;
                  }
                  break;
               case 1203911228:
                  if (var6.equals("_157_memory_position")) {
                     var4 = 26;
                  }
                  break;
               case 1245017999:
                  if (var6.equals("_41_tachometer")) {
                     var4 = 27;
                  }
                  break;
               case 1276069215:
                  if (var6.equals("_157_navigation")) {
                     var4 = 28;
                  }
                  break;
               case 1285535413:
                  if (var6.equals("_41_ctm_system")) {
                     var4 = 29;
                  }
                  break;
               case 1286553292:
                  if (var6.equals("_194_unlock_mode")) {
                     var4 = 30;
                  }
                  break;
               case 1287717922:
                  if (var6.equals("_157_turn_guide_signal")) {
                     var4 = 31;
                  }
                  break;
               case 1298522815:
                  if (var6.equals("_55_0x68_data1_bit10")) {
                     var4 = 32;
                  }
                  break;
               case 1298522943:
                  if (var6.equals("_55_0x68_data1_bit54")) {
                     var4 = 33;
                  }
                  break;
               case 1298523007:
                  if (var6.equals("_55_0x68_data1_bit76")) {
                     var4 = 34;
                  }
                  break;
               case 1300899316:
                  if (var6.equals("_55_0x75_data1_bit0")) {
                     var4 = 35;
                  }
                  break;
               case 1300899317:
                  if (var6.equals("_55_0x75_data1_bit1")) {
                     var4 = 36;
                  }
                  break;
               case 1417574351:
                  if (var6.equals("_55_0xE8_data0_bit10")) {
                     var4 = 37;
                  }
                  break;
               case 1417574415:
                  if (var6.equals("_55_0xE8_data0_bit32")) {
                     var4 = 38;
                  }
                  break;
               case 1436065648:
                  if (var6.equals("_301_dts_audio")) {
                     var4 = 39;
                  }
                  break;
               case 1493848755:
                  if (var6.equals("_55_0x64_data2_bit0")) {
                     var4 = 40;
                  }
                  break;
               case 1591925822:
                  if (var6.equals("_55_0x67_data1_bit10")) {
                     var4 = 41;
                  }
                  break;
               case 1591925886:
                  if (var6.equals("_55_0x67_data1_bit32")) {
                     var4 = 42;
                  }
                  break;
               case 1591925981:
                  if (var6.equals("_55_0x67_data1_bit64")) {
                     var4 = 43;
                  }
                  break;
               case 1594302323:
                  if (var6.equals("_55_0x65_data1_bit0")) {
                     var4 = 44;
                  }
                  break;
               case 1594302326:
                  if (var6.equals("_55_0x65_data1_bit3")) {
                     var4 = 45;
                  }
                  break;
               case 1723385042:
                  if (var6.equals("_55_0x66_data1_bit0")) {
                     var4 = 46;
                  }
                  break;
               case 1723385043:
                  if (var6.equals("_55_0x66_data1_bit1")) {
                     var4 = 47;
                  }
                  break;
               case 1723385044:
                  if (var6.equals("_55_0x66_data1_bit2")) {
                     var4 = 48;
                  }
                  break;
               case 1723385045:
                  if (var6.equals("_55_0x66_data1_bit3")) {
                     var4 = 49;
                  }
                  break;
               case 1746333974:
                  if (var6.equals("_157_bsm_reference_line")) {
                     var4 = 50;
                  }
                  break;
               case 1823838613:
                  if (var6.equals("_55_0x67_data0_bit3")) {
                     var4 = 51;
                  }
                  break;
               case 1846843524:
                  if (var6.equals("_55_0xE8_data0_bit4")) {
                     var4 = 52;
                  }
                  break;
               case 1889251442:
                  if (var6.equals("_157_seat_auto_move")) {
                     var4 = 53;
                  }
                  break;
               case 1937336606:
                  if (var6.equals("_157_backlight_mode")) {
                     var4 = 54;
                  }
                  break;
               case 1952921329:
                  if (var6.equals("_55_0x68_data0_bit0")) {
                     var4 = 55;
                  }
                  break;
               case 1981550482:
                  if (var6.equals("_55_0x68_data1_bit2")) {
                     var4 = 56;
                  }
                  break;
               case 1981550483:
                  if (var6.equals("_55_0x68_data1_bit3")) {
                     var4 = 57;
                  }
                  break;
               case 2018618426:
                  if (var6.equals("_55_0xE8_data6_bit0")) {
                     var4 = 58;
                  }
                  break;
               case 2018618427:
                  if (var6.equals("_55_0xE8_data6_bit1")) {
                     var4 = 59;
                  }
                  break;
               case 2018618428:
                  if (var6.equals("_55_0xE8_data6_bit2")) {
                     var4 = 60;
                  }
                  break;
               case 2018618429:
                  if (var6.equals("_55_0xE8_data6_bit3")) {
                     var4 = 61;
                  }
                  break;
               case 2018618430:
                  if (var6.equals("_55_0xE8_data6_bit4")) {
                     var4 = 62;
                  }
                  break;
               case 2018618431:
                  if (var6.equals("_55_0xE8_data6_bit5")) {
                     var4 = 63;
                  }
                  break;
               case 2082004050:
                  if (var6.equals("_55_0x69_data0_bit2")) {
                     var4 = 64;
                  }
                  break;
               case 2082004051:
                  if (var6.equals("_55_0x69_data0_bit3")) {
                     var4 = 65;
                  }
                  break;
               case 2082004052:
                  if (var6.equals("_55_0x69_data0_bit4")) {
                     var4 = 66;
                  }
                  break;
               case 2082004054:
                  if (var6.equals("_55_0x69_data0_bit6")) {
                     var4 = 67;
                  }
                  break;
               case 2110633206:
                  if (var6.equals("_55_0x69_data1_bit7")) {
                     var4 = 68;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -11, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_phone", var3);
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -12, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_sound", var3);
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 47, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 71, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -16, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_endurance_distance", var3);
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -14, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_display", var3);
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -9, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_sms_email", var3);
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_language", var3);
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -13, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_turbine", var3);
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -15, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_average_speed", var3);
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
                  break;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte)var3});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte)var3});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -8, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_navigation", var3);
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_ctm_system", var3);
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 25, (byte)var3});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 72, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte)var3});
                  break;
               case 34:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var3});
                  break;
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var3});
                  break;
               case 36:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte)var3});
                  break;
               case 37:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte)var3});
                  break;
               case 38:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte)var3});
                  break;
               case 39:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var3});
                  break;
               case 40:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte)var3});
                  break;
               case 41:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
                  break;
               case 42:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                  break;
               case 43:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                  break;
               case 44:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
                  break;
               case 45:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 23, (byte)var3});
                  break;
               case 46:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte)var3});
                  break;
               case 47:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte)var3});
                  break;
               case 48:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte)var3});
                  break;
               case 49:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
                  break;
               case 50:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 112, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_157_bsm_line", var3);
                  break;
               case 51:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte)var3});
                  break;
               case 52:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte)var3});
                  break;
               case 53:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte)var3});
                  break;
               case 54:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var3});
                  break;
               case 55:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte)var3});
                  break;
               case 56:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                  break;
               case 57:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)var3});
                  break;
               case 58:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte)var3});
                  break;
               case 59:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 44, (byte)var3});
                  break;
               case 60:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 45, (byte)var3});
                  break;
               case 61:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 46, (byte)var3});
                  break;
               case 62:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte)var3});
                  break;
               case 63:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte)var3});
                  break;
               case 64:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                  break;
               case 65:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte)var3});
                  break;
               case 66:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte)var3});
                  break;
               case 67:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)var3});
                  break;
               case 68:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte)var3});
            }

         }
      });
      var3.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda0(this, var3, var1));
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda9());
      var5.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda10());
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      var6.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda11());
      var6.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda12(var1.getResources().getDisplayMetrics().widthPixels, var1.getResources().getDisplayMetrics().heightPixels));
      OriginalCarDevicePageUiSet var7 = this.getOriginalCarDevicePageUiSet(var1);
      var7.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this, var7) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
         }

         public void onClickTopBtnItem(int var1) {
            String var2 = this.val$originalCarDevicePageUiSet.getRowTopBtnAction()[var1];
            var2.hashCode();
            if (!var2.equals("scan")) {
               if (var2.equals("refresh")) {
                  if (GeneralOriginalCarDeviceData.refresh) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, 1});
                  }
               }
            } else if (GeneralOriginalCarDeviceData.scan) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 1});
            }

         }
      });
      var7.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this, var7) {
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
               case 3016245:
                  if (var3.equals("band")) {
                     var4 = 2;
                  }
                  break;
               case 3089570:
                  if (var3.equals("down")) {
                     var4 = 3;
                  }
                  break;
               case 3317767:
                  if (var3.equals("left")) {
                     var4 = 4;
                  }
                  break;
               case 108511772:
                  if (var3.equals("right")) {
                     var4 = 5;
                  }
                  break;
               case 1216748385:
                  if (var3.equals("next_disc")) {
                     var4 = 6;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, 0});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, 0});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, 0});
            }

         }
      });
      var7.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onSongItemClick(int var1) {
            if (var1 != 0) {
               int var2 = this.this$0.getMsgMgr(this.val$context).getOriginalRadioPresetSize();
               int var3 = var2 + 1;
               if (var1 != var3) {
                  if (var1 > var3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)(var1 - var2 - 1 - 1)});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)(var1 - 1)});
                  }

               }
            }
         }

         public void onSongItemLongClick(int var1) {
            if (var1 > 0 && var1 <= this.this$0.getMsgMgr(this.val$context).getOriginalRadioPresetSize()) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var1});
            }

         }
      });
      AirPageUiSet var8 = this.getAirUiSet(var1);
      if (var1.getResources().getConfiguration().orientation == 1) {
         var8.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda13(this, var8), new UiMgr$$ExternalSyntheticLambda1(this, var8), new UiMgr$$ExternalSyntheticLambda2(this, var8), new UiMgr$$ExternalSyntheticLambda3(this, var8)});
         var8.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.send0xE0AirCommand(2);
            }

            public void onClickUp() {
               this.this$0.send0xE0AirCommand(3);
            }
         }, null, new OnAirTemperatureUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickDown() {
               this.this$0.send0xE0AirCommand(4);
            }

            public void onClickUp() {
               this.this$0.send0xE0AirCommand(5);
            }
         }});
         var8.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickLeft() {
               this.this$0.send0xE0AirCommand(9);
            }

            public void onClickRight() {
               this.this$0.send0xE0AirCommand(10);
            }
         });
         var8.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new UiMgr$$ExternalSyntheticLambda4(this, var8)});
         if (this.mDifferentId == 33) {
            this.removeFrontAirButtonByName(var1, "blow_head");
            this.removeFrontAirButtonByName(var1, "blow_foot");
            this.removeFrontAirButtonByName(var1, "blow_window_foot");
            this.removeFrontAirButtonByName(var1, "blow_head_foot");
            var8.getFrontArea().setCanSetSeatHeat(true);
            var8.getFrontArea().setCanSetSeatCold(true);
         } else {
            this.removeFrontAirButtonByName(var1, "blow_positive");
            this.removeFrontAirButtonByName(var1, "blow_negative");
         }
      } else {
         var8.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda5(this, var8), new UiMgr$$ExternalSyntheticLambda6(this, var8), new UiMgr$$ExternalSyntheticLambda7(this, var8), new UiMgr$$ExternalSyntheticLambda8(this, var8)});
         var8.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this, var8) {
            final UiMgr this$0;
            final AirPageUiSet val$airPageUiSet;

            {
               this.this$0 = var1;
               this.val$airPageUiSet = var2;
            }

            public void onClickLeft() {
               if (GeneralAirData.front_wind_level != 1) {
                  this.this$0.send0xC6AirCommand(173, GeneralAirData.front_wind_level - 1);
               }

            }

            public void onClickRight() {
               if (GeneralAirData.front_wind_level != this.val$airPageUiSet.getFrontArea().getWindMaxLevel()) {
                  this.this$0.send0xC6AirCommand(173, GeneralAirData.front_wind_level + 1);
               }

            }
         });
         var8.getFrontArea().setCanSetLeftTemp(false);
         var8.getFrontArea().setCanSetRightTemp(false);
         var8.getFrontArea().setDisableBtnArray(new String[]{"power", "in_out_cycle", "sync", "auto", "front_defog", "rear_defog", "dual", "rear"});
         var8.getRearArea().setAllBtnCanClick(false);
         this.removeFrontAirButtonByName(var1, "blow_positive");
         this.removeFrontAirButtonByName(var1, "blow_negative");
      }

      if (this.mDifferentId == 33) {
         var8.getFrontArea().setShowSeatHeat(true);
         var8.getFrontArea().setShowSeatCold(true);
         var8.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.send0xE0AirCommand(11);
            }
         }, new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.send0xE0AirCommand(13);
            }
         }, new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.send0xE0AirCommand(12);
            }
         }, new OnAirSeatHeatColdMinPlusClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickMin() {
            }

            public void onClickPlus() {
               this.this$0.send0xE0AirCommand(14);
            }
         }});
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initSetting(Context var1) {
      this.getMsgMgr(var1).updateSettings(8, 1, SharePreUtil.getIntValue(var1, "share_157_ctm_system", 0));
      this.getMsgMgr(var1).updateSettings(8, 2, SharePreUtil.getIntValue(var1, "share_157_language", 0));
      this.getMsgMgr(var1).updateSettings(11, 0, SharePreUtil.getIntValue(var1, "share_157_endurance_distance", 0));
      this.getMsgMgr(var1).updateSettings(11, 1, SharePreUtil.getIntValue(var1, "share_157_average_speed", 0));
      this.getMsgMgr(var1).updateSettings(11, 2, SharePreUtil.getIntValue(var1, "share_157_display", 0));
      this.getMsgMgr(var1).updateSettings(11, 3, SharePreUtil.getIntValue(var1, "share_157_turbine", 0));
      this.getMsgMgr(var1).updateSettings(11, 4, SharePreUtil.getIntValue(var1, "share_157_sound", 0));
      this.getMsgMgr(var1).updateSettings(11, 5, SharePreUtil.getIntValue(var1, "share_157_phone", 0));
      this.getMsgMgr(var1).updateSettings(11, 6, SharePreUtil.getIntValue(var1, "share_157_sms_email", 0));
      this.getMsgMgr(var1).updateSettings(11, 7, SharePreUtil.getIntValue(var1, "share_157_navigation", 0));
      this.getMsgMgr(var1).updateSettings(13, 0, SharePreUtil.getIntValue(var1, "share_157_bsm_line", 0));
      this.getMsgMgr(var1).updateSettings(13, 2, SharePreUtil.getIntValue(var1, "share_157_front_link_turn_signal", 1));
   }

   // $FF: synthetic method
   static void lambda$new$1(AmplifierActivity.AmplifierPosition var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var0.ordinal()];
      if (var2 != 1) {
         if (var2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var1 + 9)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var1 + 9)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$2(AmplifierActivity.AmplifierBand var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var0.ordinal()];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 4) {
                  if (var2 == 5) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)var1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)var1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)var1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)var1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$3(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, 0});
      }

   }

   // $FF: synthetic method
   static void lambda$new$4(int var0, int var1, MotionEvent var2) {
      byte var7;
      if (var2.getAction() == 0) {
         var7 = 1;
      } else {
         if (var2.getAction() != 1) {
            return;
         }

         var7 = 0;
      }

      var0 = (int)var2.getX() * 1023 / var0;
      var1 = (int)var2.getY() * 1000 / var1;
      byte var5 = (byte)(var0 & 255);
      byte var3 = (byte)(var0 >> 8 & 255);
      byte var6 = (byte)(var1 & 255);
      byte var4 = (byte)(var1 >> 8 & 255);
      CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte)var7, var3, var5, var4, var6, 0});
   }

   private void send0xC6AirCommand(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2});
   }

   private void send0xE0AirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 1});
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 3106:
            if (var1.equals("ac")) {
               var2 = 0;
            }
            break;
         case 1018451744:
            if (var1.equals("blow_head_foot")) {
               var2 = 1;
            }
            break;
         case 1434490075:
            if (var1.equals("blow_foot")) {
               var2 = 2;
            }
            break;
         case 1434539597:
            if (var1.equals("blow_head")) {
               var2 = 3;
            }
            break;
         case 1568764496:
            if (var1.equals("blow_window_foot")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
            if (GeneralAirData.ac) {
               this.send0xC6AirCommand(172, 2);
            } else {
               this.send0xC6AirCommand(172, 1);
            }
            break;
         case 1:
            this.send0xC6AirCommand(172, 4);
            break;
         case 2:
            this.send0xC6AirCommand(172, 5);
            break;
         case 3:
            this.send0xC6AirCommand(172, 3);
            break;
         case 4:
            this.send0xC6AirCommand(172, 6);
      }

   }

   private void sendPortAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 1;
            }
            break;
         case -620266838:
            if (var1.equals("rear_power")) {
               var2 = 2;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 3;
            }
            break;
         case -424438238:
            if (var1.equals("blow_negative")) {
               var2 = 4;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 5;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 6;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 7;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 8;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 9;
            }
            break;
         case 1018451744:
            if (var1.equals("blow_head_foot")) {
               var2 = 10;
            }
            break;
         case 1434490075:
            if (var1.equals("blow_foot")) {
               var2 = 11;
            }
            break;
         case 1434539597:
            if (var1.equals("blow_head")) {
               var2 = 12;
            }
            break;
         case 1568764496:
            if (var1.equals("blow_window_foot")) {
               var2 = 13;
            }
      }

      switch (var2) {
         case 0:
            this.send0xE0AirCommand(19);
            break;
         case 1:
            this.send0xE0AirCommand(20);
            break;
         case 2:
            this.send0xE0AirCommand(42);
            break;
         case 3:
            this.send0xE0AirCommand(36);
            break;
         case 4:
            this.send0xE0AirCommand(37);
            break;
         case 5:
            this.send0xE0AirCommand(23);
            break;
         case 6:
            this.send0xE0AirCommand(21);
            break;
         case 7:
            this.send0xE0AirCommand(16);
            break;
         case 8:
            this.send0xE0AirCommand(1);
            break;
         case 9:
            this.send0xE0AirCommand(25);
            break;
         case 10:
            this.send0xE0AirCommand(33);
            break;
         case 11:
            this.send0xE0AirCommand(8);
            break;
         case 12:
            this.send0xE0AirCommand(7);
            break;
         case 13:
            this.send0xE0AirCommand(32);
      }

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

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__158_UiMgr(SettingPageUiSet var1, Context var2, int var3, int var4, int var5) {
      String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      var6.hashCode();
      if (var6.equals("_157_front_link_turn")) {
         SharePreUtil.setIntValue(var2, "share_157_front_link_turn_signal", var5);
         this.getMsgMgr(var2).updateSettings(13, 2, var5);
      }

   }

   // $FF: synthetic method
   void lambda$new$10$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$11$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$12$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$13$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendPortAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$6$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendPortAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$7$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendPortAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$8$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendPortAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$9$com_hzbhd_canbus_car__158_UiMgr(AirPageUiSet var1, int var2) {
      this.sendPortAirCommand(var1.getRearArea().getLineBtnAction()[3][var2]);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
