package com.hzbhd.canbus.car._41;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private static final String SHARE_41_CTM_SYSTEM = "share_41_ctm_system";
   static final String SHARE_41_FRONT_CAMERA_SWITCH = "share_41_front_camera_switch";
   private static final String SHARE_41_LANGUAGE = "share_41_language";
   public String KEY_TEM_UNIT = "KEY_TEM_UNIT";
   private Context mContext;
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
                  if (var1 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 6});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 5});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 4});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 3});
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
   private TimerUtil mRequestTimer;

   public UiMgr(Context var1) {
      this.mContext = var1;
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
               case -404275092:
                  if (var4.equals("_41_tpms1")) {
                     var5 = 4;
                  }
                  break;
               case 1568946281:
                  if (var4.equals("_41_delete_fuel_record")) {
                     var5 = 5;
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
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, 0});
                  break;
               case 5:
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
               case -1216608962:
                  if (var6.equals("F_camera")) {
                     var4 = 3;
                  }
                  break;
               case -722490656:
                  if (var6.equals("_41_speed_distance_units")) {
                     var4 = 4;
                  }
                  break;
               case -549818908:
                  if (var6.equals("_41_tachometer_switch")) {
                     var4 = 5;
                  }
                  break;
               case 102584022:
                  if (var6.equals("language_setup")) {
                     var4 = 6;
                  }
                  break;
               case 117616127:
                  if (var6.equals("_55_0x69_data0_bit10")) {
                     var4 = 7;
                  }
                  break;
               case 207393826:
                  if (var6.equals("_41_util_tem")) {
                     var4 = 8;
                  }
                  break;
               case 704422172:
                  if (var6.equals("_55_0x67_data0_bit20")) {
                     var4 = 9;
                  }
                  break;
               case 868863258:
                  if (var6.equals("_41_key_remote_unlock")) {
                     var4 = 10;
                  }
                  break;
               case 1005119904:
                  if (var6.equals("_55_0x69_data1_bit43")) {
                     var4 = 11;
                  }
                  break;
               case 1005119968:
                  if (var6.equals("_55_0x69_data1_bit65")) {
                     var4 = 12;
                  }
                  break;
               case 1093047774:
                  if (var6.equals("_41_wheel_key_mute_home")) {
                     var4 = 13;
                  }
                  break;
               case 1245017999:
                  if (var6.equals("_41_tachometer")) {
                     var4 = 14;
                  }
                  break;
               case 1285535413:
                  if (var6.equals("_41_ctm_system")) {
                     var4 = 15;
                  }
                  break;
               case 1286553292:
                  if (var6.equals("_194_unlock_mode")) {
                     var4 = 16;
                  }
                  break;
               case 1298522815:
                  if (var6.equals("_55_0x68_data1_bit10")) {
                     var4 = 17;
                  }
                  break;
               case 1298522943:
                  if (var6.equals("_55_0x68_data1_bit54")) {
                     var4 = 18;
                  }
                  break;
               case 1298523007:
                  if (var6.equals("_55_0x68_data1_bit76")) {
                     var4 = 19;
                  }
                  break;
               case 1300899316:
                  if (var6.equals("_55_0x75_data1_bit0")) {
                     var4 = 20;
                  }
                  break;
               case 1300899317:
                  if (var6.equals("_55_0x75_data1_bit1")) {
                     var4 = 21;
                  }
                  break;
               case 1591925822:
                  if (var6.equals("_55_0x67_data1_bit10")) {
                     var4 = 22;
                  }
                  break;
               case 1591925886:
                  if (var6.equals("_55_0x67_data1_bit32")) {
                     var4 = 23;
                  }
                  break;
               case 1591925981:
                  if (var6.equals("_55_0x67_data1_bit64")) {
                     var4 = 24;
                  }
                  break;
               case 1594302323:
                  if (var6.equals("_55_0x65_data1_bit0")) {
                     var4 = 25;
                  }
                  break;
               case 1594302326:
                  if (var6.equals("_55_0x65_data1_bit3")) {
                     var4 = 26;
                  }
                  break;
               case 1723385042:
                  if (var6.equals("_55_0x66_data1_bit0")) {
                     var4 = 27;
                  }
                  break;
               case 1723385043:
                  if (var6.equals("_55_0x66_data1_bit1")) {
                     var4 = 28;
                  }
                  break;
               case 1723385044:
                  if (var6.equals("_55_0x66_data1_bit2")) {
                     var4 = 29;
                  }
                  break;
               case 1723385045:
                  if (var6.equals("_55_0x66_data1_bit3")) {
                     var4 = 30;
                  }
                  break;
               case 1823838613:
                  if (var6.equals("_55_0x67_data0_bit3")) {
                     var4 = 31;
                  }
                  break;
               case 1981550482:
                  if (var6.equals("_55_0x68_data1_bit2")) {
                     var4 = 32;
                  }
                  break;
               case 1981550483:
                  if (var6.equals("_55_0x68_data1_bit3")) {
                     var4 = 33;
                  }
                  break;
               case 2082004050:
                  if (var6.equals("_55_0x69_data0_bit2")) {
                     var4 = 34;
                  }
                  break;
               case 2082004051:
                  if (var6.equals("_55_0x69_data0_bit3")) {
                     var4 = 35;
                  }
                  break;
               case 2082004052:
                  if (var6.equals("_55_0x69_data0_bit4")) {
                     var4 = 36;
                  }
            }

            UiMgr var8;
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
                  var8 = this.this$0;
                  var8.getMsgMgr(var8.mContext).updateSettings(this.this$0.mContext, var1, var2, "F_camera", var3);
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_41_language", var3);
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                  break;
               case 8:
                  if (var3 == 0) {
                     var6 = "C";
                  } else {
                     var6 = "F";
                  }

                  SharePreUtil.setStringValue(this.this$0.mContext, this.this$0.KEY_TEM_UNIT, var6);
                  var8 = this.this$0;
                  MsgMgr var9 = var8.getMsgMgr(var8.mContext);
                  UiMgr var7 = this.this$0;
                  var1 = var7.getSettingLeftIndexes(var7.mContext, "_41_util");
                  var7 = this.this$0;
                  var9.updateSettings(var1, var7.getSettingRightIndex(var7.mContext, "_41_util", "_41_util_tem"), var3);
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
                  break;
               case 13:
                  var8 = this.this$0;
                  var8.getMsgMgr(var8.mContext).updateSettings(this.this$0.mContext, var1, var2, "ID41_K_MUTE_OR_HOME", var3);
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_41_ctm_system", var3);
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 25, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
                  break;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 23, (byte)var3});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte)var3});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte)var3});
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte)var3});
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)var3});
                  break;
               case 34:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                  break;
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte)var3});
                  break;
               case 36:
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
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, this.mOnAirBtnClickListenerLeft, null, this.mOnAirBtnClickListenerBottom});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte)DataHandleUtils.rangeNumber(GeneralAirData.front_wind_level - 1, 1, 7)});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte)DataHandleUtils.rangeNumber(GeneralAirData.front_wind_level + 1, 1, 7)});
         }
      });
      var3.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
         }
      });
      DriverDataPageUiSet var4 = this.getDriverDataPageUiSet(var1);
      int[] var5 = this.getSettingItemPosition(var2, "_41_delete_fuel_record");
      var4.setLeftPosition(var5[0]);
      var4.setRightPosition(var5[1]);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private int[] getSettingItemPosition(SettingPageUiSet var1, String var2) {
      int[] var5 = new int[]{-1, -1};

      for(int var3 = 0; var3 < var1.getList().size(); ++var3) {
         SettingPageUiSet.ListBean var6 = (SettingPageUiSet.ListBean)var1.getList().get(var3);

         for(int var4 = 0; var4 < var6.getItemList().size(); ++var4) {
            if (((SettingPageUiSet.ListBean.ItemListBean)var6.getItemList().get(var4)).getTitleSrn().equals(var2)) {
               var5[0] = var3;
               var5[1] = var4;
            }
         }
      }

      return var5;
   }

   private TimerUtil getSettingTimerUtil() {
      if (this.mRequestTimer == null) {
         this.mRequestTimer = new TimerUtil();
      }

      return this.mRequestTimer;
   }

   private void initSetting(Context var1) {
      this.getMsgMgr(var1).updateSettings(6, 1, SharePreUtil.getIntValue(var1, "share_41_ctm_system", 0));
      this.getMsgMgr(var1).updateSettings(6, 2, SharePreUtil.getIntValue(var1, "share_41_language", 0));
      byte var2 = SharePreUtil.getStringValue(this.mContext, this.KEY_TEM_UNIT, "C").equals("C");
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_41_util"), this.getSettingRightIndex(this.mContext, "_41_util", "_41_util_tem"), var2 ^ 1);
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
      this.requestCommand();
      MsgMgr var3 = this.getMsgMgr(this.mContext);
      Context var2 = this.mContext;
      var3.updateSettings(var2, this.getSettingLeftIndexes(var2, "front_camera_setup"), this.getSettingRightIndex(this.mContext, "front_camera_setup", "F_camera"), "F_camera", SharePreUtil.getIntValue(this.mContext, "F_camera", 0));
      MsgMgr var4 = this.getMsgMgr(this.mContext);
      var1 = this.mContext;
      var4.updateSettings(var1, this.getSettingLeftIndexes(var1, "_41_wheel_key"), this.getSettingRightIndex(this.mContext, "_41_wheel_key", "_41_wheel_key_mute_home"), "ID41_K_MUTE_OR_HOME", SharePreUtil.getIntValue(this.mContext, "ID41_K_MUTE_OR_HOME", 0));
   }
}
