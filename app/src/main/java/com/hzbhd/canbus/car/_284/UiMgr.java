package com.hzbhd.canbus.car._284;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_284_SUPPORT_PANORAMIC = "share_284_support_panoramic";
   static final String SHARE_284_SUPPORT_RIGHTVIEW = "share_284_support_rightview";
   private final int MSG_SEND_AIR_COMMAND_UP = 16;
   private byte[] m0x85Data = new byte[]{22, -123, 0, 0};
   private byte[] m0x86Data = new byte[]{22, -122, 0, 0, 0, 0, 0, 0};
   private Context mContext;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 16) {
            CanbusMsgSender.sendMsg(this.this$0.m0x86Data);
         }

      }
   };
   private MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(3, var1);
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(1, var1);
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(2, var1);
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(0, var1);
      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         byte[] var1 = Arrays.copyOf(this.this$0.m0x86Data, this.this$0.m0x86Data.length);
         var1[5] = (byte)DataHandleUtils.setIntByteWithBit(0, 0, true);
         this.this$0.sendAirCommand(var1);
      }

      public void onClickUp() {
         byte[] var1 = Arrays.copyOf(this.this$0.m0x86Data, this.this$0.m0x86Data.length);
         var1[5] = (byte)DataHandleUtils.setIntByteWithBit(0, 1, true);
         this.this$0.sendAirCommand(var1);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      Log.i("ljq", "UiMgr: ");
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
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
            if (!var3.equals("_55_0xE8_data4")) {
               if (var3.equals("_284_open_right_view")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, this.this$0.m0x85Data[2], (byte)(this.this$0.m0x85Data[3] | 32)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, this.this$0.m0x85Data[2], (byte)(this.this$0.m0x85Data[3] | 64)});
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
            if (!var4.equals("_250_ambient_light_brightness")) {
               if (var4.equals("_284_setting_item_29")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte)(var3 + 10)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)var3});
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
            if (!var3.equals("_284_tire_pressure_reset")) {
               if (var3.equals("_284_vehicle_setting_default")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, 1});
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
            String var9 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var9.hashCode();
            int var5 = var9.hashCode();
            boolean var8 = true;
            boolean var7 = true;
            boolean var6 = true;
            byte var4 = -1;
            switch (var5) {
               case -2080242761:
                  if (var9.equals("_284_85_1_7")) {
                     var4 = 0;
                  }
                  break;
               case -1839027678:
                  if (var9.equals("_194_window")) {
                     var4 = 1;
                  }
                  break;
               case -1720286544:
                  if (var9.equals("_220_steering_modes")) {
                     var4 = 2;
                  }
                  break;
               case -1613767836:
                  if (var9.equals("seatDriveProfile")) {
                     var4 = 3;
                  }
                  break;
               case -1590596702:
                  if (var9.equals("_250_automatic_folding_mirror")) {
                     var4 = 4;
                  }
                  break;
               case -1438897752:
                  if (var9.equals("_284_support_right_view")) {
                     var4 = 5;
                  }
                  break;
               case -1313213960:
                  if (var9.equals("auto_lock_when_stop_car")) {
                     var4 = 6;
                  }
                  break;
               case -1241372009:
                  if (var9.equals("_284_85_0_title")) {
                     var4 = 7;
                  }
                  break;
               case -1015001166:
                  if (var9.equals("_163_setting_1")) {
                     var4 = 8;
                  }
                  break;
               case -1015001161:
                  if (var9.equals("_163_setting_6")) {
                     var4 = 9;
                  }
                  break;
               case -43540113:
                  if (var9.equals("_284_setting_item_02")) {
                     var4 = 10;
                  }
                  break;
               case -43540112:
                  if (var9.equals("_284_setting_item_03")) {
                     var4 = 11;
                  }
                  break;
               case -43540109:
                  if (var9.equals("_284_setting_item_06")) {
                     var4 = 12;
                  }
                  break;
               case -43540108:
                  if (var9.equals("_284_setting_item_07")) {
                     var4 = 13;
                  }
                  break;
               case -43540107:
                  if (var9.equals("_284_setting_item_08")) {
                     var4 = 14;
                  }
                  break;
               case -43540106:
                  if (var9.equals("_284_setting_item_09")) {
                     var4 = 15;
                  }
                  break;
               case -43540098:
                  if (var9.equals("_284_setting_item_0A")) {
                     var4 = 16;
                  }
                  break;
               case -43540097:
                  if (var9.equals("_284_setting_item_0B")) {
                     var4 = 17;
                  }
                  break;
               case -43540095:
                  if (var9.equals("_284_setting_item_0D")) {
                     var4 = 18;
                  }
                  break;
               case -43540094:
                  if (var9.equals("_284_setting_item_0E")) {
                     var4 = 19;
                  }
                  break;
               case -43540093:
                  if (var9.equals("_284_setting_item_0F")) {
                     var4 = 20;
                  }
                  break;
               case -43540083:
                  if (var9.equals("_284_setting_item_11")) {
                     var4 = 21;
                  }
                  break;
               case -43540082:
                  if (var9.equals("_284_setting_item_12")) {
                     var4 = 22;
                  }
                  break;
               case -43540081:
                  if (var9.equals("_284_setting_item_13")) {
                     var4 = 23;
                  }
                  break;
               case -43540080:
                  if (var9.equals("_284_setting_item_14")) {
                     var4 = 24;
                  }
                  break;
               case -43540079:
                  if (var9.equals("_284_setting_item_15")) {
                     var4 = 25;
                  }
                  break;
               case -43540078:
                  if (var9.equals("_284_setting_item_16")) {
                     var4 = 26;
                  }
                  break;
               case -43540077:
                  if (var9.equals("_284_setting_item_17")) {
                     var4 = 27;
                  }
                  break;
               case -43540066:
                  if (var9.equals("_284_setting_item_1B")) {
                     var4 = 28;
                  }
                  break;
               case -43540065:
                  if (var9.equals("_284_setting_item_1C")) {
                     var4 = 29;
                  }
                  break;
               case -43540064:
                  if (var9.equals("_284_setting_item_1D")) {
                     var4 = 30;
                  }
                  break;
               case -43540063:
                  if (var9.equals("_284_setting_item_1E")) {
                     var4 = 31;
                  }
                  break;
               case -43540053:
                  if (var9.equals("_284_setting_item_20")) {
                     var4 = 32;
                  }
                  break;
               case -43540052:
                  if (var9.equals("_284_setting_item_21")) {
                     var4 = 33;
                  }
                  break;
               case -43540051:
                  if (var9.equals("_284_setting_item_22")) {
                     var4 = 34;
                  }
                  break;
               case -43540050:
                  if (var9.equals("_284_setting_item_23")) {
                     var4 = 35;
                  }
                  break;
               case -43540049:
                  if (var9.equals("_284_setting_item_24")) {
                     var4 = 36;
                  }
                  break;
               case -43540048:
                  if (var9.equals("_284_setting_item_25")) {
                     var4 = 37;
                  }
                  break;
               case -43540047:
                  if (var9.equals("_284_setting_item_26")) {
                     var4 = 38;
                  }
                  break;
               case -43540045:
                  if (var9.equals("_284_setting_item_28")) {
                     var4 = 39;
                  }
                  break;
               case -43540036:
                  if (var9.equals("_284_setting_item_2A")) {
                     var4 = 40;
                  }
                  break;
               case -43540035:
                  if (var9.equals("_284_setting_item_2B")) {
                     var4 = 41;
                  }
                  break;
               case -43540034:
                  if (var9.equals("_284_setting_item_2C")) {
                     var4 = 42;
                  }
                  break;
               case -43540033:
                  if (var9.equals("_284_setting_item_2D")) {
                     var4 = 43;
                  }
                  break;
               case -43540032:
                  if (var9.equals("_284_setting_item_2E")) {
                     var4 = 44;
                  }
                  break;
               case -43540031:
                  if (var9.equals("_284_setting_item_2F")) {
                     var4 = 45;
                  }
                  break;
               case -43540022:
                  if (var9.equals("_284_setting_item_30")) {
                     var4 = 46;
                  }
                  break;
               case -43540021:
                  if (var9.equals("_284_setting_item_31")) {
                     var4 = 47;
                  }
                  break;
               case -43540019:
                  if (var9.equals("_284_setting_item_33")) {
                     var4 = 48;
                  }
                  break;
               case -43540018:
                  if (var9.equals("_284_setting_item_34")) {
                     var4 = 49;
                  }
                  break;
               case -43540017:
                  if (var9.equals("_284_setting_item_35")) {
                     var4 = 50;
                  }
                  break;
               case -43540016:
                  if (var9.equals("_284_setting_item_36")) {
                     var4 = 51;
                  }
                  break;
               case -43540015:
                  if (var9.equals("_284_setting_item_37")) {
                     var4 = 52;
                  }
                  break;
               case -43540014:
                  if (var9.equals("_284_setting_item_38")) {
                     var4 = 53;
                  }
                  break;
               case -43540005:
                  if (var9.equals("_284_setting_item_3A")) {
                     var4 = 54;
                  }
                  break;
               case -43540004:
                  if (var9.equals("_284_setting_item_3B")) {
                     var4 = 55;
                  }
                  break;
               case -43540003:
                  if (var9.equals("_284_setting_item_3C")) {
                     var4 = 56;
                  }
                  break;
               case -43540002:
                  if (var9.equals("_284_setting_item_3D")) {
                     var4 = 57;
                  }
                  break;
               case -43540001:
                  if (var9.equals("_284_setting_item_3E")) {
                     var4 = 58;
                  }
                  break;
               case -43540000:
                  if (var9.equals("_284_setting_item_3F")) {
                     var4 = 59;
                  }
                  break;
               case -43539988:
                  if (var9.equals("_284_setting_item_43")) {
                     var4 = 60;
                  }
                  break;
               case -43539987:
                  if (var9.equals("_284_setting_item_44")) {
                     var4 = 61;
                  }
                  break;
               case -43539986:
                  if (var9.equals("_284_setting_item_45")) {
                     var4 = 62;
                  }
                  break;
               case -43539985:
                  if (var9.equals("_284_setting_item_46")) {
                     var4 = 63;
                  }
                  break;
               case -43539984:
                  if (var9.equals("_284_setting_item_47")) {
                     var4 = 64;
                  }
                  break;
               case -43539983:
                  if (var9.equals("_284_setting_item_48")) {
                     var4 = 65;
                  }
                  break;
               case -43539982:
                  if (var9.equals("_284_setting_item_49")) {
                     var4 = 66;
                  }
                  break;
               case 712683749:
                  if (var9.equals("support_panorama")) {
                     var4 = 67;
                  }
                  break;
               case 943720037:
                  if (var9.equals("auto_door_unlock")) {
                     var4 = 68;
                  }
                  break;
               case 957932200:
                  if (var9.equals("light_ctrl_3")) {
                     var4 = 69;
                  }
                  break;
               case 958464329:
                  if (var9.equals("auto_lock_when_drive")) {
                     var4 = 70;
                  }
                  break;
               case 1235727081:
                  if (var9.equals("_250_language")) {
                     var4 = 71;
                  }
                  break;
               case 1720570364:
                  if (var9.equals("geely_emergency_brake_auto")) {
                     var4 = 72;
                  }
                  break;
               case 1989254418:
                  if (var9.equals("_176_car_setting_title_25")) {
                     var4 = 73;
                  }
            }

            MsgMgr var10;
            switch (var4) {
               case 0:
                  byte[] var11 = this.this$0.m0x85Data;
                  var4 = this.this$0.m0x85Data[3];
                  if (var3 == 1) {
                     var6 = var7;
                  } else {
                     var6 = false;
                  }

                  var11[3] = (byte)DataHandleUtils.setIntByteWithBit(var4, 7, var6);
                  CanbusMsgSender.sendMsg(this.this$0.m0x85Data);
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 65, (byte)(var3 + 1)});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 64, (byte)(var3 + 1)});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 66, (byte)(var3 + 1)});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)(var3 + 1)});
                  break;
               case 5:
                  SharePreUtil.setIntValue(this.val$context, "share_284_support_rightview", var3);
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  var10 = this.this$0.getMsgMgr(this.val$context);
                  if (var3 == 1) {
                     var6 = var8;
                  } else {
                     var6 = false;
                  }

                  var10.updateRightViewItem(var6);
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)(var3 + 1)});
                  break;
               case 7:
                  this.this$0.m0x85Data[2] = (byte)(var3 + 1);
                  CanbusMsgSender.sendMsg(this.this$0.m0x85Data);
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte)(var3 + 1)});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)(var3 + 1)});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)(var3 + 1)});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)(var3 + 1)});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)(var3 + 1)});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)(var3 + 1)});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)(var3 + 1)});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)(var3 + 1)});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)(var3 + 1)});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)(var3 + 1)});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)(var3 + 1)});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)(var3 + 1)});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)(var3 + 1)});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)(var3 + 1)});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)(var3 + 1)});
                  break;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)(var3 + 1)});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)(var3 + 1)});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)(var3 + 1)});
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte)var3});
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte)(var3 + 1)});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte)(var3 + 1)});
                  break;
               case 34:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)(var3 + 1)});
                  break;
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)(var3 + 1)});
                  break;
               case 36:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)(var3 + 1)});
                  break;
               case 37:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)(var3 + 1)});
                  break;
               case 38:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)(var3 + 1)});
                  break;
               case 39:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte)var3});
                  break;
               case 40:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 42, (byte)(var3 + 1)});
                  break;
               case 41:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 43, (byte)(var3 + 1)});
                  break;
               case 42:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 44, (byte)(var3 + 1)});
                  break;
               case 43:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 45, (byte)(var3 + 1)});
                  break;
               case 44:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 46, (byte)(var3 + 1)});
                  break;
               case 45:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte)(var3 + 1)});
                  break;
               case 46:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte)(var3 + 1)});
                  break;
               case 47:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)(var3 + 1)});
                  break;
               case 48:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 51, (byte)(var3 + 1)});
                  break;
               case 49:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 52, (byte)(var3 + 1)});
                  break;
               case 50:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 53, (byte)(var3 + 1)});
                  break;
               case 51:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 54, (byte)(var3 + 1)});
                  break;
               case 52:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte)(var3 + 1)});
                  break;
               case 53:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 56, (byte)(var3 + 1)});
                  break;
               case 54:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 58, (byte)(var3 + 1)});
                  break;
               case 55:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 59, (byte)(var3 + 1)});
                  break;
               case 56:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 60, (byte)(var3 + 1)});
                  break;
               case 57:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 61, (byte)(var3 + 1)});
                  break;
               case 58:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 62, (byte)(var3 + 1)});
                  break;
               case 59:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 63, (byte)(var3 + 1)});
                  break;
               case 60:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 67, (byte)(var3 + 1)});
                  break;
               case 61:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 68, (byte)(var3 + 1)});
                  break;
               case 62:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 69, (byte)(var3 + 1)});
                  break;
               case 63:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 70, (byte)(var3 + 1)});
                  break;
               case 64:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 71, (byte)(var3 + 1)});
                  break;
               case 65:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 72, (byte)(var3 + 1)});
                  break;
               case 66:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 73, (byte)(var3 + 1)});
                  break;
               case 67:
                  SharePreUtil.setIntValue(this.val$context, "share_284_support_panoramic", var3);
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  var10 = this.this$0.getMsgMgr(this.val$context);
                  if (var3 != 1) {
                     var6 = false;
                  }

                  var10.updatePanoramicItem(var6);
                  break;
               case 68:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)(var3 + 1)});
                  break;
               case 69:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)(var3 + 1)});
                  break;
               case 70:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)(var3 + 1)});
                  break;
               case 71:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 57, (byte)(var3 + 1)});
                  break;
               case 72:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte)(var3 + 1)});
                  break;
               case 73:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)(var3 + 1)});
            }

         }
      });
      ParkPageUiSet var5 = this.getParkPageUiSet(var1);
      var5.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var5) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var3 = ((ParkPageUiSet.Bean)this.val$parkPageUiSet.getPanoramicBtnList().get(var1)).getTitleSrn();
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1676175341:
                  if (var3.equals("_253_exit_panoramic")) {
                     var4 = 0;
                  }
                  break;
               case -278535229:
                  if (var3.equals("_284_panoramic_status_10")) {
                     var4 = 1;
                  }
                  break;
               case 2069224973:
                  if (var3.equals("_284_panoramic_status_1")) {
                     var4 = 2;
                  }
                  break;
               case 2069224974:
                  if (var3.equals("_284_panoramic_status_2")) {
                     var4 = 3;
                  }
                  break;
               case 2069224975:
                  if (var3.equals("_284_panoramic_status_3")) {
                     var4 = 4;
                  }
                  break;
               case 2069224976:
                  if (var3.equals("_284_panoramic_status_4")) {
                     var4 = 5;
                  }
                  break;
               case 2069224978:
                  if (var3.equals("_284_panoramic_status_6")) {
                     var4 = 6;
                  }
                  break;
               case 2069224979:
                  if (var3.equals("_284_panoramic_status_7")) {
                     var4 = 7;
                  }
                  break;
               case 2069224981:
                  if (var3.equals("_284_panoramic_status_9")) {
                     var4 = 8;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, this.this$0.m0x85Data[2], (byte)(this.this$0.m0x85Data[3] | 64)});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 16});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 1});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 4});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 2});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 17});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 5});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 18});
            }

         }
      });
      var5.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1, var5) {
         final UiMgr this$0;
         final Context val$context;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$parkPageUiSet = var3;
         }

         public void addViewToWindows() {
            boolean var1 = CommUtil.isPanoramic(this.val$context);
            this.val$parkPageUiSet.setShowRadar(var1 ^ true);
            this.val$parkPageUiSet.setShowPanoramic(var1);
         }
      });
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      FrontArea var4 = var3.getFrontArea();
      OnAirTemperatureUpDownClickListener var6 = this.mOnUpDownClickListener;
      var4.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{var6, null, var6});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            byte[] var1 = Arrays.copyOf(this.this$0.m0x86Data, this.this$0.m0x86Data.length);
            var1[3] = (byte)DataHandleUtils.setIntByteWithBit(0, 0, true);
            this.this$0.sendAirCommand(var1);
         }

         public void onClickRight() {
            byte[] var1 = Arrays.copyOf(this.this$0.m0x86Data, this.this$0.m0x86Data.length);
            var1[3] = (byte)DataHandleUtils.setIntByteWithBit(0, 1, true);
            this.this$0.sendAirCommand(var1);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendAirCommand(int var1, int var2) {
      String var4 = this.getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[var1][var2];
      byte[] var5 = this.m0x86Data;
      var5 = Arrays.copyOf(var5, var5.length);
      var4.hashCode();
      int var3 = var4.hashCode();
      byte var7 = 3;
      byte var6 = -1;
      switch (var3) {
         case -1470045433:
            if (var4.equals("front_defog")) {
               var6 = 0;
            }
            break;
         case -1423573049:
            if (var4.equals("ac_max")) {
               var6 = 1;
            }
            break;
         case -631663038:
            if (var4.equals("rear_defog")) {
               var6 = 2;
            }
            break;
         case -424438238:
            if (var4.equals("blow_negative")) {
               var6 = 3;
            }
            break;
         case 3106:
            if (var4.equals("ac")) {
               var6 = 4;
            }
            break;
         case 3005871:
            if (var4.equals("auto")) {
               var6 = 5;
            }
            break;
         case 3496356:
            if (var4.equals("rear")) {
               var6 = 6;
            }
            break;
         case 3545755:
            if (var4.equals("sync")) {
               var6 = 7;
            }
            break;
         case 106858757:
            if (var4.equals("power")) {
               var6 = 8;
            }
            break;
         case 756225563:
            if (var4.equals("in_out_cycle")) {
               var6 = 9;
            }
            break;
         case 1018451744:
            if (var4.equals("blow_head_foot")) {
               var6 = 10;
            }
            break;
         case 1434490075:
            if (var4.equals("blow_foot")) {
               var6 = 11;
            }
            break;
         case 1434539597:
            if (var4.equals("blow_head")) {
               var6 = 12;
            }
            break;
         case 1568764496:
            if (var4.equals("blow_window_foot")) {
               var6 = 13;
            }
      }

      switch (var6) {
         case 0:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 4, true);
            break;
         case 1:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 0, true);
            break;
         case 2:
            var5[3] = (byte)DataHandleUtils.setIntByteWithBit(0, 2, true);
            break;
         case 3:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 6, true);
            break;
         case 4:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 1, true);
            break;
         case 5:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 5, true);
            break;
         case 6:
            var5[7] = (byte)DataHandleUtils.setIntByteWithBit(0, 2, true);
            break;
         case 7:
            var5[3] = (byte)DataHandleUtils.setIntByteWithBit(0, 3, true);
            break;
         case 8:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 7, true);
            break;
         case 9:
            var6 = var7;
            if (GeneralAirData.in_out_cycle) {
               var6 = 2;
            }

            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, var6, true);
            break;
         case 10:
            var5[4] = (byte)DataHandleUtils.setIntByteWithBit(0, 2, true);
            break;
         case 11:
            var5[4] = (byte)DataHandleUtils.setIntByteWithBit(0, 3, true);
            break;
         case 12:
            var5[4] = (byte)DataHandleUtils.setIntByteWithBit(0, 1, true);
            break;
         case 13:
            var5[4] = (byte)DataHandleUtils.setIntByteWithBit(0, 4, true);
      }

      this.sendAirCommand(var5);
   }

   private void sendAirCommand(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   void initSettingItem() {
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.mContext = var1;
      Log.i("ljq", "updateUiByDifferentCar: ");
   }
}
