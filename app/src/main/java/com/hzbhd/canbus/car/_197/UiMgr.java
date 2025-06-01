package com.hzbhd.canbus.car._197;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private final String TAG = "_197_UiMgr";
   private int mAirAddition = 204;
   private int mDifferent = this.getCurrentCarId();
   private MsgMgr mMsgMgr;
   private TimerUtil mTimerUtil;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               this.this$0.getTimerUtil().startTimer(new TimerTask(this) {
                  int[] dataType;
                  int i;
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                     this.dataType = new int[]{38, 49, 55};
                     this.i = 0;
                  }

                  public void run() {
                     int var1 = this.i;
                     int[] var2 = this.dataType;
                     if (var1 < var2.length) {
                        this.i = var1 + 1;
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var2[var1], 0});
                     } else {
                        this.this$1.this$0.getTimerUtil().stopTimer();
                     }

                  }
               }, 0L, 100L);
            }

         }
      });
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_197_update_history_fuel")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
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
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (!var4.equals("_55_0x67_data1_bit64")) {
               if (var4.equals("_197_lock_unlcok_feedback_tone")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
            }

         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (!var3.equals("_197_clear_15_fuel")) {
               if (var3.equals("_197_clear_history_fuel")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
            }

         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2, var1) {
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
               case -2065696359:
                  if (var6.equals("_118_setting_title_96")) {
                     var4 = 0;
                  }
                  break;
               case -1890718474:
                  if (var6.equals("_197_amplifier_mute")) {
                     var4 = 1;
                  }
                  break;
               case -1839650831:
                  if (var6.equals("_197_unlock_when_operating_key_twice")) {
                     var4 = 2;
                  }
                  break;
               case -1076352805:
                  if (var6.equals("_197_autolock_by_shift_to_p")) {
                     var4 = 3;
                  }
                  break;
               case -1075953717:
                  if (var6.equals("_197_automatic_relock_time")) {
                     var4 = 4;
                  }
                  break;
               case -688331508:
                  if (var6.equals("_197_Driver_seat_open_door_linkage_unlock")) {
                     var4 = 5;
                  }
                  break;
               case -685307461:
                  if (var6.equals("_197_rear_system")) {
                     var4 = 6;
                  }
                  break;
               case -445499202:
                  if (var6.equals("geely_daytime_running_lights")) {
                     var4 = 7;
                  }
                  break;
               case -209293330:
                  if (var6.equals("_197_rear_camera_guide")) {
                     var4 = 8;
                  }
                  break;
               case -54470183:
                  if (var6.equals("_197_linkage_between_internal_and_external_circulation_and_auto_key")) {
                     var4 = 9;
                  }
                  break;
               case 97333442:
                  if (var6.equals("amplifier_switch")) {
                     var4 = 10;
                  }
                  break;
               case 99743255:
                  if (var6.equals("_197_remote_2_press_unlock")) {
                     var4 = 11;
                  }
                  break;
               case 257294315:
                  if (var6.equals("_186_asl")) {
                     var4 = 12;
                  }
                  break;
               case 314512010:
                  if (var6.equals("_197_autolock_by_shift_from_p")) {
                     var4 = 13;
                  }
                  break;
               case 694047023:
                  if (var6.equals("_197_rear_system_lock")) {
                     var4 = 14;
                  }
                  break;
               case 1198399862:
                  if (var6.equals("_197_emergency_flashing_light_response_when_unlocking_locking")) {
                     var4 = 15;
                  }
                  break;
               case 1591434042:
                  if (var6.equals("_197_smart_car_lock_and_one_button_start")) {
                     var4 = 16;
                  }
                  break;
               case 1591925886:
                  if (var6.equals("_55_0x67_data1_bit32")) {
                     var4 = 17;
                  }
                  break;
               case 1835296229:
                  if (var6.equals("_197_smart_car_door_unlock")) {
                     var4 = 18;
                  }
                  break;
               case 1875679605:
                  if (var6.equals("_197_autolock_by_speed")) {
                     var4 = 19;
                  }
                  break;
               case 2081713660:
                  if (var6.equals("_18_vehicle_setting_item_3_43")) {
                     var4 = 20;
                  }
                  break;
               case 2098134882:
                  if (var6.equals("_197_linkage_of_air_conditioner_and_auto_key")) {
                     var4 = 21;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 0, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)((int)Math.pow(2.0, (double)(var3 * 3)))});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
            }

         }
      });
      AirPageUiSet var4 = this.getAirUiSet(var1);
      this.dealAirDifferent(var1, var4);
      String[][] var3 = var4.getFrontArea().getLineBtnAction();
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this, var3), new UiMgr$$ExternalSyntheticLambda1(this, var3), new UiMgr$$ExternalSyntheticLambda2(this, var3), new UiMgr$$ExternalSyntheticLambda3(this, var3)});
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(10);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(9);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(12);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(11);
         }
      }});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(21);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(20);
         }
      });
      var4.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(13);
         }
      }, new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirCommand(14);
         }
      }});
      var3 = var4.getRearArea().getLineBtnAction();
      var4.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener(this, var3) {
         final UiMgr this$0;
         final String[][] val$rearActions;

         {
            this.this$0 = var1;
            this.val$rearActions = var2;
         }

         public void onClickItem(int var1) {
            this.this$0.sendAirCommand(this.val$rearActions[3][var1]);
         }
      }});
      var4.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(44);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(43);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(44);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(43);
         }
      }});
      var4.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(46);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(45);
         }
      });
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 7)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var2 + 7)});
            }

         }
      });
      var5.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
            }

         }
      });
      DriverDataPageUiSet var6 = this.getDriverDataPageUiSet(var1);
      var6.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this, var1, var6) {
         final UiMgr this$0;
         final Context val$context;
         final DriverDataPageUiSet val$driverDataPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$driverDataPageUiSet = var3;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               int[] var2 = this.this$0.getMsgMgr(this.val$context).getLeftAndRight("_197_update_history_fuel");
               this.val$driverDataPageUiSet.setLeftPosition(var2[0]);
               this.val$driverDataPageUiSet.setRightPosition(var2[1]);
            }

         }
      });
   }

   private void addAirBtn(Context var1, int var2, int var3, String var4) {
      String[][] var10 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();
      int var7 = var10.length;

      for(int var5 = 0; var5 < var7; ++var5) {
         String[] var9 = var10[var5];
         int var8 = var9.length;

         for(int var6 = 0; var6 < var8; ++var6) {
            if (var9[var6].equals(var4)) {
               return;
            }
         }
      }

      ArrayList var11 = new ArrayList(Arrays.asList(var10[var2]));
      var11.add(var3, var4);
      var10[var2] = (String[])var11.toArray(new String[0]);
   }

   private void dealAirDifferent(Context var1, AirPageUiSet var2) {
      var2.setHaveRearArea(false);
      var2.getFrontArea().setShowSeatHeat(false);
      this.removeFrontAirButtonByName(var1, "pollrn_removal");
      this.removeFrontAirButtonByName(var1, "windshield_deicing");
      if ((this.mDifferent & 1) == 0) {
         this.removeFrontAirButtonByName(var1, "dual");
         this.removeFrontAirButtonByName(var1, "auto");
      }

      if (this.mDifferent == 17) {
         this.removeFrontAirButtonByName(var1, "dual");
      }

      int var3 = this.mDifferent;
      if (var3 == 32) {
         var2.getFrontArea().setWindMaxLevel(4);
      } else if (var3 == 33) {
         var2.getFrontArea().setWindMaxLevel(8);
      }

      if (this.mDifferent != 1) {
         this.removeFrontAirButtonByName(var1, "blow_negative");
      }

   }

   private int[] getAirBtnPosition(Context var1, String var2) {
      int[] var5 = new int[]{-1, -1};
      String[][] var6 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();

      for(int var3 = 0; var3 < var6.length; ++var3) {
         String[] var7 = var6[var3];

         for(int var4 = 0; var4 < var7.length; ++var4) {
            if (var7[var4].equals(var2)) {
               var5[0] = var3;
               var5[1] = var4;
            }
         }
      }

      return var5;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      Log.i("ljq", "getMsgMgr: " + this.mMsgMgr);
      return this.mMsgMgr;
   }

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   private void sendAirCommand(int var1) {
      int var2 = this.mAirAddition;
      if (var2 == 34) {
         this.mAirAddition = 204;
      } else if (var2 == 204) {
         this.mAirAddition = 34;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte)var1, (byte)this.mAirAddition, 0, 0});
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1761278488:
            if (var1.equals("pollrn_removal")) {
               var2 = 0;
            }
            break;
         case -1604835367:
            if (var1.equals("windshield_deicing")) {
               var2 = 1;
            }
            break;
         case -712865050:
            if (var1.equals("rear_lock")) {
               var2 = 2;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 3;
            }
            break;
         case -620266838:
            if (var1.equals("rear_power")) {
               var2 = 4;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 5;
            }
            break;
         case -424438238:
            if (var1.equals("blow_negative")) {
               var2 = 6;
            }
            break;
         case -246396018:
            if (var1.equals("max_front")) {
               var2 = 7;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 8;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 9;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 10;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 11;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 12;
            }
            break;
         case 1006620906:
            if (var1.equals("auto_wind_mode")) {
               var2 = 13;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(15);
            break;
         case 1:
            this.sendAirCommand(49);
            break;
         case 2:
            this.sendAirCommand(37);
            break;
         case 3:
            this.sendAirCommand(4);
            break;
         case 4:
            this.sendAirCommand(48);
            break;
         case 5:
            this.sendAirCommand(17);
            break;
         case 6:
            this.sendAirCommand(42);
            break;
         case 7:
            this.sendAirCommand(38);
            break;
         case 8:
            this.sendAirCommand(16);
            break;
         case 9:
            this.sendAirCommand(2);
            break;
         case 10:
            this.sendAirCommand(8);
            break;
         case 11:
            this.sendAirCommand(1);
            break;
         case 12:
            this.sendAirCommand(35);
            break;
         case 13:
            this.sendAirCommand(47);
      }

   }

   void initSettingPage(Context var1) {
      int[] var2 = this.getMsgMgr(var1).getLeftAndRight("");
      if (var2[0] != -1 && var2[1] != -1) {
         this.getMsgMgr(var1).updateSettings(var2[0], var2[1], 0);
      }

   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__197_UiMgr(String[][] var1, int var2) {
      this.sendAirCommand(var1[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__197_UiMgr(String[][] var1, int var2) {
      this.sendAirCommand(var1[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__197_UiMgr(String[][] var1, int var2) {
      this.sendAirCommand(var1[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__197_UiMgr(String[][] var1, int var2) {
      this.sendAirCommand(var1[3][var2]);
   }

   void updateMainActivityItem(Context var1, String var2, boolean var3) {
      List var4 = this.getMainUiSet(var1).getBtnAction();
      if (var4.contains(var2) ^ var3) {
         if (var3) {
            var4.add(var2);
         } else {
            var4.remove(var2);
         }
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (!SharePreUtil.getBoolValue(var1, "share_197_is_hybird_vehicle", true)) {
         this.removeMainPrjBtnByName(var1, "hybird");
      }

      MyApplication.IS_SET = true;
   }
}
