package com.hzbhd.canbus.car._301;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Objects;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   protected static final String CAN_269_SAVE_AMP_BASS = "__269_SAVE_AMP_BASS";
   protected static final String CAN_269_SAVE_AMP_FR = "__269_SAVE_AMP_FR";
   protected static final String CAN_269_SAVE_AMP_LR = "__269_SAVE_AMP_LR";
   protected static final String CAN_269_SAVE_AMP_MID = "__269_SAVE_AMP_MID";
   protected static final String CAN_269_SAVE_AMP_TRE = "__269_SAVE_AMP_TRE";
   protected static final String CAN_269_SAVE_AMP_VOL = "__269_SAVE_AMP_VOL";
   protected static final String CAN_269_SAVE_AMP__HEAVY_BASS = "__269_SAVE_AMP_HEAVY_BASS";
   protected static final String CAN_269_SAVE_IS_AMP_FIRST = "CAN_269_SAVE_IS_AMP_FIRST";
   private static final String SHARE_41_CTM_SYSTEM = "share_41_ctm_system";
   static final String SHARE_41_FRONT_CAMERA_SWITCH = "share_41_front_camera_switch";
   private static final String SHARE_41_LANGUAGE = "share_41_language";
   private byte AMP_BAL_OFFSET = 0;
   private byte AMP_EQ_OFFSET = 0;
   private byte AMP_VOL_OFFSET = 0;
   private Context mContext;
   private int mDifferentId;
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private boolean mIsClickReset = false;
   private MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.sendAirKeyMsg(37);
                        }
                     } else {
                        this.this$0.sendAirKeyMsg(36);
                     }
                  } else {
                     this.this$0.sendAirKeyMsg(32);
                  }
               } else {
                  this.this$0.sendAirKeyMsg(8);
               }
            } else {
               this.this$0.sendAirKeyMsg(33);
            }
         } else {
            this.this$0.sendAirKeyMsg(7);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            byte var2 = 1;
            if (GeneralAirData.ac) {
               var2 = 2;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)var2});
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private TimerUtil mRequestTimer;
   private final MsgMgr msgMgr;
   OnAirTemperatureUpDownClickListener onUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
      }

      public void onClickUp() {
      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirKeyMsg(2);
      }

      public void onClickUp() {
         this.this$0.sendAirKeyMsg(3);
      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirKeyMsg(4);
      }

      public void onClickUp() {
         this.this$0.sendAirKeyMsg(5);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.mDifferentId = this.getCurrentCarId();
      this.initSetting(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
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
            if (!var4.equals("_55_0x69_data1_bit20")) {
               if (var4.equals("compass_zoom")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)(var3 + 5)});
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
            Objects.requireNonNull(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn());
         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var2, var1) {
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
               case -1586268672:
                  if (var4.equals("_41_default_all")) {
                     var5 = 0;
                  }
                  break;
               case -1502330376:
                  if (var4.equals("front_camera_switch")) {
                     var5 = 1;
                  }
                  break;
               case -1224494730:
                  if (var4.equals("_55_0x6E_0x06")) {
                     var5 = 2;
                  }
                  break;
               case -811374327:
                  if (var4.equals("compass_run_calibration")) {
                     var5 = 3;
                  }
                  break;
               case 1568946281:
                  if (var4.equals("_41_delete_fuel_record")) {
                     var5 = 4;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, 0});
                  break;
               case 1:
                  boolean var3 = SharePreUtil.getBoolValue(this.val$context, "share_41_front_camera_switch", false);
                  SharePreUtil.setBoolValue(this.val$context, "share_41_front_camera_switch", var3 ^ true);
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, 1});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 3});
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
               case -1904072502:
                  if (var6.equals("_301_volume_and_speed_linkage")) {
                     var4 = 3;
                  }
                  break;
               case -1400265020:
                  if (var6.equals("_163_setting_16")) {
                     var4 = 4;
                  }
                  break;
               case -722490656:
                  if (var6.equals("_41_speed_distance_units")) {
                     var4 = 5;
                  }
                  break;
               case -549818908:
                  if (var6.equals("_41_tachometer_switch")) {
                     var4 = 6;
                  }
                  break;
               case 102584022:
                  if (var6.equals("language_setup")) {
                     var4 = 7;
                  }
                  break;
               case 117616127:
                  if (var6.equals("_55_0x69_data0_bit10")) {
                     var4 = 8;
                  }
                  break;
               case 624754683:
                  if (var6.equals("_301_reverse_gear_tone")) {
                     var4 = 9;
                  }
                  break;
               case 704422172:
                  if (var6.equals("_55_0x67_data0_bit20")) {
                     var4 = 10;
                  }
                  break;
               case 775437606:
                  if (var6.equals("_301_personalized_driving_position")) {
                     var4 = 11;
                  }
                  break;
               case 868863258:
                  if (var6.equals("_41_key_remote_unlock")) {
                     var4 = 12;
                  }
                  break;
               case 1005119904:
                  if (var6.equals("_55_0x69_data1_bit43")) {
                     var4 = 13;
                  }
                  break;
               case 1005119968:
                  if (var6.equals("_55_0x69_data1_bit65")) {
                     var4 = 14;
                  }
                  break;
               case 1245017999:
                  if (var6.equals("_41_tachometer")) {
                     var4 = 15;
                  }
                  break;
               case 1285535413:
                  if (var6.equals("_41_ctm_system")) {
                     var4 = 16;
                  }
                  break;
               case 1286553292:
                  if (var6.equals("_194_unlock_mode")) {
                     var4 = 17;
                  }
                  break;
               case 1298522815:
                  if (var6.equals("_55_0x68_data1_bit10")) {
                     var4 = 18;
                  }
                  break;
               case 1298522943:
                  if (var6.equals("_55_0x68_data1_bit54")) {
                     var4 = 19;
                  }
                  break;
               case 1298523007:
                  if (var6.equals("_55_0x68_data1_bit76")) {
                     var4 = 20;
                  }
                  break;
               case 1300899316:
                  if (var6.equals("_55_0x75_data1_bit0")) {
                     var4 = 21;
                  }
                  break;
               case 1300899317:
                  if (var6.equals("_55_0x75_data1_bit1")) {
                     var4 = 22;
                  }
                  break;
               case 1436065648:
                  if (var6.equals("_301_dts_audio")) {
                     var4 = 23;
                  }
                  break;
               case 1456922374:
                  if (var6.equals("_301_four_wheel_drive_awd")) {
                     var4 = 24;
                  }
                  break;
               case 1518102037:
                  if (var6.equals("_301_seat_position_movement")) {
                     var4 = 25;
                  }
                  break;
               case 1591925822:
                  if (var6.equals("_55_0x67_data1_bit10")) {
                     var4 = 26;
                  }
                  break;
               case 1591925886:
                  if (var6.equals("_55_0x67_data1_bit32")) {
                     var4 = 27;
                  }
                  break;
               case 1591925981:
                  if (var6.equals("_55_0x67_data1_bit64")) {
                     var4 = 28;
                  }
                  break;
               case 1594302323:
                  if (var6.equals("_55_0x65_data1_bit0")) {
                     var4 = 29;
                  }
                  break;
               case 1594302326:
                  if (var6.equals("_55_0x65_data1_bit3")) {
                     var4 = 30;
                  }
                  break;
               case 1723385042:
                  if (var6.equals("_55_0x66_data1_bit0")) {
                     var4 = 31;
                  }
                  break;
               case 1723385043:
                  if (var6.equals("_55_0x66_data1_bit1")) {
                     var4 = 32;
                  }
                  break;
               case 1723385044:
                  if (var6.equals("_55_0x66_data1_bit2")) {
                     var4 = 33;
                  }
                  break;
               case 1723385045:
                  if (var6.equals("_55_0x66_data1_bit3")) {
                     var4 = 34;
                  }
                  break;
               case 1823838613:
                  if (var6.equals("_55_0x67_data0_bit3")) {
                     var4 = 35;
                  }
                  break;
               case 1981550482:
                  if (var6.equals("_55_0x68_data1_bit2")) {
                     var4 = 36;
                  }
                  break;
               case 1981550483:
                  if (var6.equals("_55_0x68_data1_bit3")) {
                     var4 = 37;
                  }
                  break;
               case 2082004050:
                  if (var6.equals("_55_0x69_data0_bit2")) {
                     var4 = 38;
                  }
                  break;
               case 2082004051:
                  if (var6.equals("_55_0x69_data0_bit3")) {
                     var4 = 39;
                  }
                  break;
               case 2082004052:
                  if (var6.equals("_55_0x69_data0_bit4")) {
                     var4 = 40;
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
                  this.this$0.sendAmpKeyMsg(7, var3);
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_41_language", var3);
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_41_ctm_system", var3);
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 25, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte)var3});
                  break;
               case 23:
                  this.this$0.sendAmpKeyMsg(8, var3);
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte)var3});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte)var3});
                  break;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 23, (byte)var3});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte)var3});
                  break;
               case 34:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
                  break;
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte)var3});
                  break;
               case 36:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                  break;
               case 37:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)var3});
                  break;
               case 38:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                  break;
               case 39:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte)var3});
                  break;
               case 40:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte)var3});
            }

         }
      });
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
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
      });
      AirPageUiSet var7 = this.getAirUiSet(var1);
      if (this.isThree()) {
         var7.getFrontArea().setShowSeatHeat(true);
         var7.getFrontArea().setShowSeatCold(true);
      }

      var7.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener(this, var7) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            byte var4;
            label41: {
               String[][] var3 = this.val$airPageUiSet.getFrontArea().getLineBtnAction();
               byte var2 = 0;
               var5.hashCode();
               switch (var5) {
                  case "blow_positive":
                     var4 = var2;
                  case "auto":
                     var4 = 1;
                     break label41;
                  case "dual":
                     var4 = 2;
                     break label41;
                  case "power":
                     var4 = 3;
                     break label41;
                  case "in_out_cycle":
                     var4 = 4;
                     break label41;
               }

               var4 = -1;
            }

            switch (var4) {
               case 0:
                  this.this$0.sendAirKeyMsg(25);
                  break;
               case 1:
                  this.this$0.sendAirKeyMsg(21);
                  break;
               case 2:
                  this.this$0.sendAirKeyMsg(16);
                  break;
               case 3:
                  this.this$0.sendAirKeyMsg(1);
                  break;
               case 4:
                  this.this$0.sendAirKeyMsg(25);
            }

         }
      }, new OnAirBtnClickListener(this, var7) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String[][] var3 = this.val$airPageUiSet.getFrontArea().getLineBtnAction();
            byte var2 = 1;
            String var5 = var3[1][var1];
            var5.hashCode();
            if (var5.equals("ac")) {
               byte var4 = var2;
               if (GeneralAirData.ac) {
                  var4 = 2;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte)var4});
            }

         }
      }, new OnAirBtnClickListener(this, var7) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            Log.d("mww", "onClickItem " + var1);
            String var2 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[2][var1];
            var2.hashCode();
            if (!var2.equals("front_defog")) {
               if (var2.equals("rear_defog")) {
                  this.this$0.sendAirKeyMsg(20);
               }
            } else {
               this.this$0.sendAirKeyMsg(19);
            }

         }
      }, new OnAirBtnClickListener(this, var7, var1) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1) {
            byte var4;
            label52: {
               String[][] var3 = this.val$airPageUiSet.getFrontArea().getLineBtnAction();
               byte var2 = 3;
               var5.hashCode();
               switch (var5) {
                  case "blow_positive":
                     var4 = 0;
                     break label52;
                  case "blow_negative":
                     var4 = 1;
                     break label52;
                  case "blow_head_foot":
                     var4 = 2;
                     break label52;
                  case "blow_foot":
                     var4 = var2;
                  case "blow_head":
                     var4 = 4;
                     break label52;
                  case "blow_window_foot":
                     var4 = 5;
                     break label52;
               }

               var4 = -1;
            }

            switch (var4) {
               case 0:
                  if (CommUtil.isAirTemperatureSwitch(this.val$context) == 1) {
                     this.this$0.sendAirKeyMsg(37);
                  } else {
                     this.this$0.sendAirKeyMsg(36);
                  }
                  break;
               case 1:
                  if (CommUtil.isAirTemperatureSwitch(this.val$context) == 1) {
                     this.this$0.sendAirKeyMsg(36);
                  } else {
                     this.this$0.sendAirKeyMsg(37);
                  }
                  break;
               case 2:
                  this.this$0.sendAirKeyMsg(33);
                  break;
               case 3:
                  this.this$0.sendAirKeyMsg(8);
                  break;
               case 4:
                  this.this$0.sendAirKeyMsg(7);
                  break;
               case 5:
                  this.this$0.sendAirKeyMsg(32);
            }

         }
      }});
      var7.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirKeyMsg(9);
         }

         public void onClickRight() {
            this.this$0.sendAirKeyMsg(10);
         }
      });
      var7.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      var7.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, this.onUpDownClickListenerCenter, this.onUpDownClickListenerRight});
      OnAirSeatHeatColdMinPlusClickListener var3 = new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirKeyMsg(11);
         }
      };
      OnAirSeatHeatColdMinPlusClickListener var6 = new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirKeyMsg(13);
         }
      };
      OnAirSeatHeatColdMinPlusClickListener var4 = new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirKeyMsg(12);
         }
      };
      OnAirSeatHeatColdMinPlusClickListener var5 = new OnAirSeatHeatColdMinPlusClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickMin() {
         }

         public void onClickPlus() {
            this.this$0.sendAirKeyMsg(14);
         }
      };
      var7.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{var3, var6, var4, var5});
      DriverDataPageUiSet var10 = this.getDriverDataPageUiSet(var1);
      int[] var9 = this.getSettingItemPosition(var2, "_41_delete_fuel_record");
      var10.setLeftPosition(var9[0]);
      var10.setRightPosition(var9[1]);
      AmplifierPageUiSet var8 = this.getAmplifierPageUiSet(var1);
      var8.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        if (var3 == 5) {
                           this.this$0.mIsClickReset = false;
                           SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_VOL", var2);
                           GeneralAmplifierData.volume = var2;
                           this.this$0.sendAmpKeyMsg(9, var2);
                        }
                     } else {
                        this.this$0.mIsClickReset = false;
                        SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_MID", var2);
                        GeneralAmplifierData.bandMiddle = var2;
                        this.this$0.sendAmpKeyMsg(4, var2);
                     }
                  } else {
                     this.this$0.mIsClickReset = false;
                     SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_TRE", var2);
                     GeneralAmplifierData.bandTreble = var2;
                     this.this$0.sendAmpKeyMsg(3, var2);
                  }
               } else {
                  this.this$0.mIsClickReset = false;
                  SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_BASS", var2);
                  GeneralAmplifierData.bandBass = var2;
                  this.this$0.sendAmpKeyMsg(5, var2);
               }
            } else {
               this.this$0.mIsClickReset = false;
               SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_HEAVY_BASS", var2);
               GeneralAmplifierData.megaBass = var2;
               this.this$0.sendAmpKeyMsg(6, var2);
            }

         }
      });
      var8.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  if (this.this$0.mIsClickReset) {
                     this.this$0.mIsClickReset = false;
                  } else {
                     SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_LR", var2);
                     GeneralAmplifierData.leftRight = var2;
                     this.this$0.sendAmpKeyMsg(2, var2 + 9);
                  }
               }
            } else if (!this.this$0.mIsClickReset) {
               SharePreUtil.setIntValue(this.this$0.mContext, "__269_SAVE_AMP_FR", var2);
               GeneralAmplifierData.frontRear = var2;
               this.this$0.sendAmpKeyMsg(1, var2 + 9);
            }

         }
      });
      var8.setOnAmplifierResetPositionListener(new OnAmplifierResetPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void resetClick() {
            this.this$0.sendAmpKeyMsg(10, 0);
            this.this$0.mIsClickReset = true;
            this.this$0.msgMgr.updateAmpUi((Context)null);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private int[] getSettingItemPosition(SettingPageUiSet var1, String var2) {
      int[] var6 = new int[]{-1, -1};

      for(int var3 = 0; var3 < var1.getList().size(); ++var3) {
         SettingPageUiSet.ListBean var5 = (SettingPageUiSet.ListBean)var1.getList().get(var3);

         for(int var4 = 0; var4 < var5.getItemList().size(); ++var4) {
            if (((SettingPageUiSet.ListBean.ItemListBean)var5.getItemList().get(var4)).getTitleSrn().equals(var2)) {
               var6[0] = var3;
               var6[1] = var4;
            }
         }
      }

      return var6;
   }

   private TimerUtil getSettingTimerUtil() {
      if (this.mRequestTimer == null) {
         this.mRequestTimer = new TimerUtil();
      }

      return this.mRequestTimer;
   }

   private void initSetting(Context var1) {
      this.getMsgMgr(var1).updateSettings(6, 1, SharePreUtil.getIntValue(var1, "share_41_ctm_system", 0));
      this.getMsgMgr(var1).updateSettings(6, 4, SharePreUtil.getIntValue(var1, "share_41_language", 0));
   }

   // $FF: synthetic method
   static void lambda$sendAirKeyMsg$0(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var0, 0});
   }

   private void requestCommand() {
      this.getSettingTimerUtil().startTimer(new TimerTask(this, new byte[][]{{22, -112, 50, 0}, {22, -112, 37, 0}, {22, -112, -46, 0}}) {
         int i;
         final UiMgr this$0;
         final byte[][] val$requesCommands;

         {
            this.this$0 = var1;
            this.val$requesCommands = var2;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$requesCommands;
            if (var1 < var2.length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.this$0.getSettingTimerUtil().stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private void sendAirKeyMsg(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 1});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda0(var1), 30L);
   }

   private void sendAmpKeyMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   public boolean isThree() {
      boolean var1;
      if (this.mDifferentId == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.mDifferentId == 2) {
         this.removeFrontAirButtonByName(var1, "blow_head");
         this.removeFrontAirButtonByName(var1, "blow_head_foot");
         this.removeFrontAirButtonByName(var1, "blow_foot");
         this.removeFrontAirButtonByName(var1, "blow_window_foot");
      } else {
         this.removeFrontAirButtonByName(var1, "blow_positive");
         this.removeFrontAirButtonByName(var1, "blow_negative");
      }

      this.requestCommand();
   }
}
