package com.hzbhd.canbus.car._315;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;

public class UiMgr extends AbstractUiMgr {
   private byte[] m0xC7Data = new byte[]{22, -57, 0, 0, 0, 0, 0, 0};
   private Context mContext;
   private Handler mHandler = new Handler(Looper.getMainLooper());
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
         byte[] var1 = Arrays.copyOf(this.this$0.m0xC7Data, this.this$0.m0xC7Data.length);
         var1[5] = (byte)DataHandleUtils.setIntByteWithBit(0, 0, true);
         this.this$0.sendAirCommand(var1);
      }

      public void onClickUp() {
         byte[] var1 = Arrays.copyOf(this.this$0.m0xC7Data, this.this$0.m0xC7Data.length);
         var1[5] = (byte)DataHandleUtils.setIntByteWithBit(0, 1, true);
         this.this$0.sendAirCommand(var1);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var2, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -751197907:
                  if (var4.equals("_315_light_dynamic_setup")) {
                     var5 = 0;
                  }
                  break;
               case 44965632:
                  if (var4.equals("_315_seat_courtesy_switch")) {
                     var5 = 1;
                  }
                  break;
               case 1930813626:
                  if (var4.equals("_304_atoms_lamp_control")) {
                     var5 = 2;
                  }
            }

            switch (var5) {
               case 0:
                  this.this$0.set0xC8Data(this.val$context, 154, var3);
                  break;
               case 1:
                  this.this$0.set0xC8Data(this.val$context, 148, var3);
                  break;
               case 2:
                  this.this$0.set0xC8Data(this.val$context, 147, var3);
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
               case -1971209028:
                  if (var6.equals("_315_light_static_setup")) {
                     var4 = 0;
                  }
                  break;
               case -1617319113:
                  if (var6.equals("_315_light_dynamic_sampling")) {
                     var4 = 1;
                  }
                  break;
               case -873936231:
                  if (var6.equals("_279_temperature_unit")) {
                     var4 = 2;
                  }
                  break;
               case 957932202:
                  if (var6.equals("light_ctrl_5")) {
                     var4 = 3;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -105, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -101, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -110, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettingItem(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_pre_is_use_f_unit", var3);
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -106, (byte)var3});
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
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -792513678:
                  if (var4.equals("_250_ambient_light_brightness")) {
                     var5 = 0;
                  }
                  break;
               case -750885129:
                  if (var4.equals("_315_light_dynamic_speed")) {
                     var5 = 1;
                  }
                  break;
               case 1715173718:
                  if (var4.equals("_315_light_static_sampling_bgr")) {
                     var5 = 2;
                  }
                  break;
               case 1715173997:
                  if (var4.equals("_315_light_static_sampling_bpr")) {
                     var5 = 3;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -107, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -100, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -104, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, -103, (byte)var3});
            }

         }
      });
      AirPageUiSet var4 = this.getAirUiSet(var1);
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      FrontArea var3 = var4.getFrontArea();
      OnAirTemperatureUpDownClickListener var5 = this.mOnUpDownClickListener;
      var3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{var5, null, var5});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            byte[] var1 = Arrays.copyOf(this.this$0.m0xC7Data, this.this$0.m0xC7Data.length);
            var1[3] = (byte)DataHandleUtils.setIntByteWithBit(0, 0, true);
            this.this$0.sendAirCommand(var1);
         }

         public void onClickRight() {
            byte[] var1 = Arrays.copyOf(this.this$0.m0xC7Data, this.this$0.m0xC7Data.length);
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
      byte[] var5 = this.m0xC7Data;
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
         case -631663038:
            if (var4.equals("rear_defog")) {
               var6 = 1;
            }
            break;
         case 3106:
            if (var4.equals("ac")) {
               var6 = 2;
            }
            break;
         case 106858757:
            if (var4.equals("power")) {
               var6 = 3;
            }
            break;
         case 341572893:
            if (var4.equals("blow_window")) {
               var6 = 4;
            }
            break;
         case 756225563:
            if (var4.equals("in_out_cycle")) {
               var6 = 5;
            }
            break;
         case 1018451744:
            if (var4.equals("blow_head_foot")) {
               var6 = 6;
            }
            break;
         case 1434490075:
            if (var4.equals("blow_foot")) {
               var6 = 7;
            }
            break;
         case 1434539597:
            if (var4.equals("blow_head")) {
               var6 = 8;
            }
            break;
         case 1568764496:
            if (var4.equals("blow_window_foot")) {
               var6 = 9;
            }
      }

      switch (var6) {
         case 0:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 4, true);
            break;
         case 1:
            var5[3] = (byte)DataHandleUtils.setIntByteWithBit(0, 2, true);
            break;
         case 2:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 1, true);
            Log.i("cbc", "sendAirCommand: setup ");
            break;
         case 3:
            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 7, true);
            break;
         case 4:
            var5[4] = (byte)DataHandleUtils.setIntFromByteWithBit(0, 5, 5, 3);
            break;
         case 5:
            var6 = var7;
            if (GeneralAirData.in_out_cycle) {
               var6 = 2;
            }

            var5[2] = (byte)DataHandleUtils.setIntByteWithBit(0, var6, true);
            break;
         case 6:
            var5[4] = (byte)DataHandleUtils.setIntFromByteWithBit(0, 2, 5, 3);
            break;
         case 7:
            var5[4] = (byte)DataHandleUtils.setIntFromByteWithBit(0, 3, 5, 3);
            break;
         case 8:
            var5[4] = (byte)DataHandleUtils.setIntFromByteWithBit(0, 1, 5, 3);
            break;
         case 9:
            var5[4] = (byte)DataHandleUtils.setIntFromByteWithBit(0, 4, 5, 3);
      }

      this.sendAirCommand(var5);
   }

   private void sendAirCommand(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   private void set0xC8Data(Context var1, int var2, int var3) {
      byte[] var4 = this.getMsgMgr(var1).get0x41Data();
      var4[0] = 22;
      var4[1] = -56;
      var4[2] = (byte)var2;
      var4[3] = (byte)var3;
      CanbusMsgSender.sendMsg(var4);
   }
}
