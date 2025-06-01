package com.hzbhd.canbus.car._325;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private MsgMgr mMsgMgr;
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         Context var1 = this.this$0.mContext;
         this.this$0.mMsgMgr;
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 7, (byte)(SharePreUtil.getIntValue(var1, "is_air_right_temp", 0) - 1)});
      }

      public void onClickRight() {
         Context var1 = this.this$0.mContext;
         this.this$0.mMsgMgr;
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 7, (byte)(SharePreUtil.getIntValue(var1, "is_air_right_temp", 0) + 1)});
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         Context var1 = this.this$0.mContext;
         this.this$0.mMsgMgr;
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte)(SharePreUtil.getIntValue(var1, "is_air_left_temp", 0) - 1)});
      }

      public void onClickUp() {
         Context var1 = this.this$0.mContext;
         this.this$0.mMsgMgr;
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte)(SharePreUtil.getIntValue(var1, "is_air_left_temp", 0) + 1)});
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         Context var1 = this.this$0.mContext;
         this.this$0.mMsgMgr;
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte)(SharePreUtil.getIntValue(var1, "is_air_right_temp", 0) - 1)});
      }

      public void onClickUp() {
         Context var1 = this.this$0.mContext;
         this.this$0.mMsgMgr;
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte)(SharePreUtil.getIntValue(var1, "is_air_right_temp", 0) + 1)});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener(this, var2) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            byte var4;
            label54: {
               String[][] var3 = this.val$airPageUiSet.getFrontArea().getLineBtnAction();
               byte var2 = 0;
               var5.hashCode();
               switch (var5) {
                  case "steering_wheel_heating":
                     var4 = var2;
                  case "in_out_auto_cycle":
                     var4 = 1;
                     break label54;
                  case "auto_1_2":
                     var4 = 2;
                     break label54;
               }

               var4 = -1;
            }

            switch (var4) {
               case 0:
                  if (GeneralAirData.steering_wheel_heating) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 1});
                  }
                  break;
               case 1:
                  if (GeneralAirData.in_out_auto_cycle == 0 || GeneralAirData.in_out_auto_cycle == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 1});
                  }

                  if (GeneralAirData.in_out_auto_cycle == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 49, 1, 0});
                  }
                  break;
               case 2:
                  if (GeneralAirData.auto_1_2 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 1});
                  }

                  if (GeneralAirData.auto_1_2 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 2});
                  }

                  if (GeneralAirData.auto_1_2 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 0});
                  }
            }

         }
      }, new OnAirBtnClickListener(this, var2) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var2 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[1][var1];
            var2.hashCode();
            if (var2.equals("power")) {
               if (GeneralAirData.power) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 1});
               }
            }

         }
      }, new OnAirBtnClickListener(this, var2) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var2 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[2][var1];
            var2.hashCode();
            if (var2.equals("front_defog")) {
               if (GeneralAirData.front_defog) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 11, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 11, 1});
               }
            }

         }
      }, new OnAirBtnClickListener(this, var2) {
         final UiMgr this$0;
         final AirPageUiSet val$airPageUiSet;

         {
            this.this$0 = var1;
            this.val$airPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var3 = this.val$airPageUiSet.getFrontArea().getLineBtnAction()[3][var1];
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case 341572893:
                  if (var3.equals("blow_window")) {
                     var4 = 0;
                  }
                  break;
               case 1434490075:
                  if (var3.equals("blow_foot")) {
                     var4 = 1;
                  }
                  break;
               case 1434539597:
                  if (var3.equals("blow_head")) {
                     var4 = 2;
                  }
            }

            switch (var4) {
               case 0:
                  if (GeneralAirData.front_left_blow_window) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 3, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 3, 1});
                  }
                  break;
               case 1:
                  if (GeneralAirData.front_left_blow_foot) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 5, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 5, 1});
                  }
                  break;
               case 2:
                  if (GeneralAirData.front_left_blow_head) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 4, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 4, 1});
                  }
            }

         }
      }});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var3) {
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
               case -1419761354:
                  if (var4.equals("_303_setting_content_3")) {
                     var5 = 0;
                  }
                  break;
               case -1419761353:
                  if (var4.equals("_303_setting_content_4")) {
                     var5 = 1;
                  }
                  break;
               case -1419761352:
                  if (var4.equals("_303_setting_content_5")) {
                     var5 = 2;
                  }
                  break;
               case -1419761351:
                  if (var4.equals("_303_setting_content_6")) {
                     var5 = 3;
                  }
                  break;
               case -1419761349:
                  if (var4.equals("_303_setting_content_8")) {
                     var5 = 4;
                  }
                  break;
               case -1419761348:
                  if (var4.equals("_303_setting_content_9")) {
                     var5 = 5;
                  }
                  break;
               case -1417073477:
                  if (var4.equals("_325_setting_2")) {
                     var5 = 6;
                  }
                  break;
               case -1062929027:
                  if (var4.equals("_303_setting_content_11")) {
                     var5 = 7;
                  }
                  break;
               case -1062929026:
                  if (var4.equals("_303_setting_content_12")) {
                     var5 = 8;
                  }
                  break;
               case -1062929025:
                  if (var4.equals("_303_setting_content_13")) {
                     var5 = 9;
                  }
                  break;
               case -1062929024:
                  if (var4.equals("_303_setting_content_14")) {
                     var5 = 10;
                  }
                  break;
               case -1062929023:
                  if (var4.equals("_303_setting_content_15")) {
                     var5 = 11;
                  }
                  break;
               case -1062929022:
                  if (var4.equals("_303_setting_content_16")) {
                     var5 = 12;
                  }
                  break;
               case -1062929021:
                  if (var4.equals("_303_setting_content_17")) {
                     var5 = 13;
                  }
                  break;
               case -1062928995:
                  if (var4.equals("_303_setting_content_22")) {
                     var5 = 14;
                  }
                  break;
               case -1062928994:
                  if (var4.equals("_303_setting_content_23")) {
                     var5 = 15;
                  }
                  break;
               case -1062928993:
                  if (var4.equals("_303_setting_content_24")) {
                     var5 = 16;
                  }
                  break;
               case -1062928992:
                  if (var4.equals("_303_setting_content_25")) {
                     var5 = 17;
                  }
                  break;
               case -1062928991:
                  if (var4.equals("_303_setting_content_26")) {
                     var5 = 18;
                  }
                  break;
               case -1062928990:
                  if (var4.equals("_303_setting_content_27")) {
                     var5 = 19;
                  }
                  break;
               case -1062928989:
                  if (var4.equals("_303_setting_content_28")) {
                     var5 = 20;
                  }
                  break;
               case -1062928966:
                  if (var4.equals("_303_setting_content_30")) {
                     var5 = 21;
                  }
                  break;
               case -1062928959:
                  if (var4.equals("_303_setting_content_37")) {
                     var5 = 22;
                  }
                  break;
               case 102584022:
                  if (var4.equals("language_setup")) {
                     var5 = 23;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 19, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 18, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 16, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 13, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 14, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, 75, 2, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 7, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 8, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, 122, 5, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, (byte)(var3 + 1)});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 2, (byte)(var3 + 1)});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, (byte)(var3 + 1)});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, (byte)(var3 + 1)});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte)(var3 + 1)});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 6, (byte)(var3 + 1)});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -52, 2, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 1, (byte)(var3 + 1)});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
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
               case -2085025792:
                  if (var4.equals("_325_light_1")) {
                     var5 = 0;
                  }
                  break;
               case -1417073476:
                  if (var4.equals("_325_setting_3")) {
                     var5 = 1;
                  }
                  break;
               case -1062929028:
                  if (var4.equals("_303_setting_content_10")) {
                     var5 = 2;
                  }
                  break;
               case -1062929020:
                  if (var4.equals("_303_setting_content_18")) {
                     var5 = 3;
                  }
                  break;
               case -1062929019:
                  if (var4.equals("_303_setting_content_19")) {
                     var5 = 4;
                  }
                  break;
               case -1062928997:
                  if (var4.equals("_303_setting_content_20")) {
                     var5 = 5;
                  }
                  break;
               case -1062928996:
                  if (var4.equals("_303_setting_content_21")) {
                     var5 = 6;
                  }
                  break;
               case -1062928958:
                  if (var4.equals("_303_setting_content_38")) {
                     var5 = 7;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 15, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 75, 3, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 17, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 122, 1, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, 122, 2, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, 122, 3, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, 122, 4, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 2, (byte)var3});
            }

         }
      });
      var3.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var3) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_303_setting_content_29")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -52, 1, 1});
            }

         }
      });
   }
}
