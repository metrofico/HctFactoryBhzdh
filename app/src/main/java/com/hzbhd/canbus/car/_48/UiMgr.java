package com.hzbhd.canbus.car._48;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private static final String SHARE_48_FRONT_CAMERA_SWITCH = "share_48_front_camera_switch";
   private Context mContext;
   private boolean mIsSearching;
   private MsgMgr mMsgMgr;
   private TimerUtil mTimerUtil;

   public UiMgr(Context var1) {
      this.mContext = var1;
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(var1);
      var2.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 34, 0, 0, 0, 0, 0, 0, 0});
         }
      });
      var2.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onSongItemClick(int var1) {
            int var2 = this.this$0.getMsgMgr(this.val$context).getPresetListSize();
            if (var1 > 0 && var1 <= var2 - 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte)(var1 - 1)});
            } else if (var1 > var2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte)(var1 - 1 - var2)});
            }

         }

         public void onSongItemLongClick(int var1) {
            int var2 = this.this$0.getMsgMgr(this.val$context).getPresetListSize();
            if (var1 > 0 && var1 <= var2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)(var1 - 1)});
            }

         }
      });
      var2.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this, var2, var1) {
         final UiMgr this$0;
         final Context val$context;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickTopBtnItem(int var1) {
            String var4 = this.val$originalCarDevicePageUiSet.getRowTopBtnAction()[var1];
            var4.hashCode();
            int var3 = var4.hashCode();
            byte var2 = 2;
            byte var5 = -1;
            switch (var3) {
               case 3116:
                  if (var4.equals("am")) {
                     var5 = 0;
                  }
                  break;
               case 3271:
                  if (var4.equals("fm")) {
                     var5 = 1;
                  }
                  break;
               case 96964:
                  if (var4.equals("aux")) {
                     var5 = 2;
                  }
                  break;
               case 3524221:
                  if (var4.equals("scan")) {
                     var5 = 3;
                  }
                  break;
               case 1085444827:
                  if (var4.equals("refresh")) {
                     var5 = 4;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, 2});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, 1});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, 3});
                  this.this$0.getMsgMgr(this.val$context).onAuxClick();
                  break;
               case 3:
                  if (!GeneralOriginalCarDeviceData.scan) {
                     var2 = 1;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var2});
                  break;
               case 4:
                  if (!GeneralOriginalCarDeviceData.refresh) {
                     var2 = 1;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte)var2});
            }

         }
      });
      var2.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this, var2) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
         }

         public void onClickBottomBtnItem(int var1) {
            String var4 = this.val$originalCarDevicePageUiSet.getRowBottomBtnAction()[var1];
            var4.hashCode();
            int var3 = var4.hashCode();
            byte var2 = 2;
            byte var5 = -1;
            switch (var3) {
               case -841905119:
                  if (var4.equals("prev_disc")) {
                     var5 = 0;
                  }
                  break;
               case 3739:
                  if (var4.equals("up")) {
                     var5 = 1;
                  }
                  break;
               case 3089570:
                  if (var4.equals("down")) {
                     var5 = 2;
                  }
                  break;
               case 3317767:
                  if (var4.equals("left")) {
                     var5 = 3;
                  }
                  break;
               case 108511772:
                  if (var4.equals("right")) {
                     var5 = 4;
                  }
                  break;
               case 1216748385:
                  if (var4.equals("next_disc")) {
                     var5 = 5;
                  }
            }

            UiMgr var6;
            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, 2});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, 2});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, 1});
                  break;
               case 3:
                  var5 = var2;
                  if (this.this$0.mIsSearching) {
                     var5 = 3;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var5});
                  var6 = this.this$0;
                  var6.mIsSearching = var6.mIsSearching ^ true;
                  break;
               case 4:
                  if (this.this$0.mIsSearching) {
                     var5 = 3;
                  } else {
                     var5 = 1;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var5});
                  var6 = this.this$0;
                  var6.mIsSearching = var6.mIsSearching ^ true;
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, 1});
            }

         }
      });
      var2.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               this.this$0.getTimerUtil().startTimer(new TimerTask(this) {
                  int[] dataTypeArray;
                  int i;
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                     this.dataTypeArray = new int[]{50, 211, 212};
                     this.i = 0;
                  }

                  public void run() {
                     int var1 = this.i;
                     int[] var2 = this.dataTypeArray;
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
      var3.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var3, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("compass_run_calibration")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)(1 ^ this.this$0.getMsgMgr(this.val$context).mCalibrationStatus)});
            }

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
               case -566947070:
                  if (var4.equals("contrast")) {
                     var5 = 0;
                  }
                  break;
               case -230491182:
                  if (var4.equals("saturation")) {
                     var5 = 1;
                  }
                  break;
               case 648162385:
                  if (var4.equals("brightness")) {
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
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte)(var3 + 5)});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, (byte)(var3 + 5)});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte)(var3 + 5)});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)(var3 + 5)});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte)var3});
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
               case 1568946281:
                  if (var4.equals("_41_delete_fuel_record")) {
                     var5 = 3;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, 0});
                  break;
               case 1:
                  boolean var3 = SharePreUtil.getBoolValue(this.val$context, "share_48_front_camera_switch", false);
                  SharePreUtil.setBoolValue(this.val$context, "share_48_front_camera_switch", var3 ^ true);
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 3});
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
               case -2080004357:
                  if (var6.equals("_48_forward_collision_warning_distance")) {
                     var4 = 3;
                  }
                  break;
               case -527308180:
                  if (var6.equals("_209_32_1_7")) {
                     var4 = 4;
                  }
                  break;
               case -527306258:
                  if (var6.equals("_209_32_3_7")) {
                     var4 = 5;
                  }
                  break;
               case -423658649:
                  if (var6.equals("_48_bsm_reference_line")) {
                     var4 = 6;
                  }
                  break;
               case 487681582:
                  if (var6.equals("_48_memory_position_link")) {
                     var4 = 7;
                  }
                  break;
               case 868863258:
                  if (var6.equals("_41_key_remote_unlock")) {
                     var4 = 8;
                  }
                  break;
               case 1005119904:
                  if (var6.equals("_55_0x69_data1_bit43")) {
                     var4 = 9;
                  }
                  break;
               case 1005119968:
                  if (var6.equals("_55_0x69_data1_bit65")) {
                     var4 = 10;
                  }
                  break;
               case 1286553292:
                  if (var6.equals("_194_unlock_mode")) {
                     var4 = 11;
                  }
                  break;
               case 1303525778:
                  if (var6.equals("_209_screen_display")) {
                     var4 = 12;
                  }
                  break;
               case 1591925822:
                  if (var6.equals("_55_0x67_data1_bit10")) {
                     var4 = 13;
                  }
                  break;
               case 1591925886:
                  if (var6.equals("_55_0x67_data1_bit32")) {
                     var4 = 14;
                  }
                  break;
               case 1591925981:
                  if (var6.equals("_55_0x67_data1_bit64")) {
                     var4 = 15;
                  }
                  break;
               case 1594302323:
                  if (var6.equals("_55_0x65_data1_bit0")) {
                     var4 = 16;
                  }
                  break;
               case 1723385042:
                  if (var6.equals("_55_0x66_data1_bit0")) {
                     var4 = 17;
                  }
                  break;
               case 1723385044:
                  if (var6.equals("_55_0x66_data1_bit2")) {
                     var4 = 18;
                  }
                  break;
               case 1723385045:
                  if (var6.equals("_55_0x66_data1_bit3")) {
                     var4 = 19;
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
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSetttings(var1, var2, var3);
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
            }

         }
      });
      DriverDataPageUiSet var4 = this.getDriverDataPageUiSet(var1);
      var4.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this, var3, var4) {
         final UiMgr this$0;
         final DriverDataPageUiSet val$driverDataPageUiSet;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$driverDataPageUiSet = var3;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               int[] var2 = this.this$0.getSettingItemPosition(this.val$settingPageUiSet, "_41_delete_fuel_record");
               this.val$driverDataPageUiSet.setLeftPosition(var2[0]);
               this.val$driverDataPageUiSet.setRightPosition(var2[1]);
            }

         }
      });
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte)var1});
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

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
