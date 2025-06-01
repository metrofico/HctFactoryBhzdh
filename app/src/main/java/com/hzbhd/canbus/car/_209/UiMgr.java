package com.hzbhd.canbus.car._209;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_209_FRONT_CAMERA_SWITCH = "share_209_front_camera_switch";
   static final String SHARE_209_FRONT_LINK_TURN_SIGNAL = "share_209_front_link_turn_signal";
   private AirActivity mActivity;
   private Context mContext;
   private boolean mIsSearching;
   private MsgMgr mMsgMgr;

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
      var2.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this, var2) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
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
               case 3524221:
                  if (var4.equals("scan")) {
                     var5 = 2;
                  }
                  break;
               case 1085444827:
                  if (var4.equals("refresh")) {
                     var5 = 3;
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
                  if (!GeneralOriginalCarDeviceData.scan) {
                     var2 = 1;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var2});
                  break;
               case 3:
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
      this.getMsgMgr(var1).updateSettings(6, 1, SharePreUtil.getIntValue(var1, "share_209_front_link_turn_signal", 1));
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var4) {
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
            }

         }
      });
      var4.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var4, var1) {
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
                  boolean var3 = SharePreUtil.getBoolValue(this.val$context, "share_209_front_camera_switch", false);
                  Log.i("ljq", "onConfirmClick: frontCameraSwitch -->" + var3);
                  SharePreUtil.setBoolValue(this.val$context, "share_209_front_camera_switch", var3 ^ true);
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 3});
            }

         }
      });
      var4.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var4, var1) {
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
               case -527308180:
                  if (var6.equals("_209_32_1_7")) {
                     var4 = 3;
                  }
                  break;
               case -527306258:
                  if (var6.equals("_209_32_3_7")) {
                     var4 = 4;
                  }
                  break;
               case 698580414:
                  if (var6.equals("_209_guide_tips")) {
                     var4 = 5;
                  }
                  break;
               case 868863258:
                  if (var6.equals("_41_key_remote_unlock")) {
                     var4 = 6;
                  }
                  break;
               case 935958082:
                  if (var6.equals("_209_screen_background_color")) {
                     var4 = 7;
                  }
                  break;
               case 1005119904:
                  if (var6.equals("_55_0x69_data1_bit43")) {
                     var4 = 8;
                  }
                  break;
               case 1005119968:
                  if (var6.equals("_55_0x69_data1_bit65")) {
                     var4 = 9;
                  }
                  break;
               case 1286553292:
                  if (var6.equals("_194_unlock_mode")) {
                     var4 = 10;
                  }
                  break;
               case 1298522815:
                  if (var6.equals("_55_0x68_data1_bit10")) {
                     var4 = 11;
                  }
                  break;
               case 1298522943:
                  if (var6.equals("_55_0x68_data1_bit54")) {
                     var4 = 12;
                  }
                  break;
               case 1303525778:
                  if (var6.equals("_209_screen_display")) {
                     var4 = 13;
                  }
                  break;
               case 1591925822:
                  if (var6.equals("_55_0x67_data1_bit10")) {
                     var4 = 14;
                  }
                  break;
               case 1591925886:
                  if (var6.equals("_55_0x67_data1_bit32")) {
                     var4 = 15;
                  }
                  break;
               case 1591925981:
                  if (var6.equals("_55_0x67_data1_bit64")) {
                     var4 = 16;
                  }
                  break;
               case 1594302323:
                  if (var6.equals("_55_0x65_data1_bit0")) {
                     var4 = 17;
                  }
                  break;
               case 1723385042:
                  if (var6.equals("_55_0x66_data1_bit0")) {
                     var4 = 18;
                  }
                  break;
               case 1723385043:
                  if (var6.equals("_55_0x66_data1_bit1")) {
                     var4 = 19;
                  }
                  break;
               case 1723385044:
                  if (var6.equals("_55_0x66_data1_bit2")) {
                     var4 = 20;
                  }
                  break;
               case 1723385045:
                  if (var6.equals("_55_0x66_data1_bit3")) {
                     var4 = 21;
                  }
                  break;
               case 1981550482:
                  if (var6.equals("_55_0x68_data1_bit2")) {
                     var4 = 22;
                  }
                  break;
               case 1981550483:
                  if (var6.equals("_55_0x68_data1_bit3")) {
                     var4 = 23;
                  }
                  break;
               case 2082004051:
                  if (var6.equals("_55_0x69_data0_bit3")) {
                     var4 = 24;
                  }
                  break;
               case 2110633206:
                  if (var6.equals("_55_0x69_data1_bit7")) {
                     var4 = 25;
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
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -31, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)var3});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte)var3});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, -32, (byte)var3});
            }

         }
      });
      var4.setOnSettingItemSwitchListener(new UiMgr$$ExternalSyntheticLambda0(this, var4, var1));
      DriverDataPageUiSet var3 = this.getDriverDataPageUiSet(var1);
      var3.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this, var4, var3) {
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
            CanbusMsgSender.sendMsg(new byte[]{22, 64, (byte)var1});
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

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__209_UiMgr(SettingPageUiSet var1, Context var2, int var3, int var4, int var5) {
      String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var3)).getItemList().get(var4)).getTitleSrn();
      var6.hashCode();
      if (var6.equals("_157_front_link_turn")) {
         SharePreUtil.setIntValue(var2, "share_209_front_link_turn_signal", var5);
         this.getMsgMgr(var2).updateSettings(6, 1, var5);
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
