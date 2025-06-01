package com.hzbhd.canbus.car._282;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendFrontAirCommand(3, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendFrontAirCommand(2, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendFrontAirCommand(0, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendFrontAirCommand(1, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendRearAirCommand(3, var1);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(2);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(3);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(4);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(5);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener(this) {
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

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(9);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(10);
         }
      });
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
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
               case -1498083267:
                  if (var4.equals("_282_07_0_2")) {
                     var5 = 0;
                  }
                  break;
               case -1498083266:
                  if (var4.equals("_282_07_0_3")) {
                     var5 = 1;
                  }
                  break;
               case -1498083263:
                  if (var4.equals("_282_07_0_6")) {
                     var5 = 2;
                  }
                  break;
               case -1498083262:
                  if (var4.equals("_282_07_0_7")) {
                     var5 = 3;
                  }
                  break;
               case -1467607074:
                  if (var4.equals("_282_19_0_2")) {
                     var5 = 4;
                  }
                  break;
               case -1467607073:
                  if (var4.equals("_282_19_0_3")) {
                     var5 = 5;
                  }
                  break;
               case 1013024712:
                  if (var4.equals("_282_27_0")) {
                     var5 = 6;
                  }
                  break;
               case 1013024713:
                  if (var4.equals("_282_27_1")) {
                     var5 = 7;
                  }
                  break;
               case 1013024714:
                  if (var4.equals("_282_27_2")) {
                     var5 = 8;
                  }
                  break;
               case 1013024715:
                  if (var4.equals("_282_27_3")) {
                     var5 = 9;
                  }
                  break;
               case 1013024716:
                  if (var4.equals("_282_27_4")) {
                     var5 = 10;
                  }
                  break;
               case 1013024717:
                  if (var4.equals("_282_27_5")) {
                     var5 = 11;
                  }
                  break;
               case 1013024718:
                  if (var4.equals("_282_27_6")) {
                     var5 = 12;
                  }
                  break;
               case 1013024719:
                  if (var4.equals("_282_27_7")) {
                     var5 = 13;
                  }
                  break;
               case 1013024720:
                  if (var4.equals("_282_27_8")) {
                     var5 = 14;
                  }
                  break;
               case 1013024721:
                  if (var4.equals("_282_27_9")) {
                     var5 = 15;
                  }
                  break;
               case 1338995080:
                  if (var4.equals("_282_27_11")) {
                     var5 = 16;
                  }
                  break;
               case 1338995081:
                  if (var4.equals("_282_27_12")) {
                     var5 = 17;
                  }
                  break;
               case 1748820949:
                  if (var4.equals("_282_19_0_01")) {
                     var5 = 18;
                  }
            }

            UiMgr var6;
            switch (var5) {
               case 0:
                  var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x07Data(), 121, 2, var3, 2, 1);
                  break;
               case 1:
                  var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x07Data(), 121, 2, var3, 3, 1);
                  break;
               case 2:
                  var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x07Data(), 121, 2, var3, 6, 1);
                  break;
               case 3:
                  var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x07Data(), 121, 2, var3, 7, 1);
                  break;
               case 4:
                  var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x19Data(), 130, 2, var3, 2, 1);
                  break;
               case 5:
                  var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x19Data(), 130, 2, var3, 3, 1);
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 1, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 2, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 3, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 4, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 5, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 6, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 7, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 8, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 9, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 10, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 12, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 13, (byte)var3});
                  break;
               case 18:
                  var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x19Data(), 130, 2, var3, 0, 2);
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
               case 1012996844:
                  if (var4.equals("_282_19_1")) {
                     var5 = 0;
                  }
                  break;
               case 1338995079:
                  if (var4.equals("_282_27_10")) {
                     var5 = 1;
                  }
                  break;
               case 1338995082:
                  if (var4.equals("_282_27_13")) {
                     var5 = 2;
                  }
                  break;
               case 1338995083:
                  if (var4.equals("_282_27_14")) {
                     var5 = 3;
                  }
            }

            switch (var5) {
               case 0:
                  UiMgr var6 = this.this$0;
                  var6.sendData(var6.getMsgMgr().get0x19Data(), 130, 3, var3, 0, 8);
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 11, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 14, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 117, 15, (byte)var3});
            }

         }
      });
      var3.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
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
            var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1472224677:
                  if (var3.equals("_282_14_0_4")) {
                     var4 = 0;
                  }
                  break;
               case -1472224676:
                  if (var3.equals("_282_14_0_5")) {
                     var4 = 1;
                  }
                  break;
               case -1472224674:
                  if (var3.equals("_282_14_0_7")) {
                     var4 = 2;
                  }
                  break;
               case 1013170785:
                  if (var3.equals("_282_74_1")) {
                     var4 = 3;
                  }
                  break;
               case 1013170786:
                  if (var3.equals("_282_74_2")) {
                     var4 = 4;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 116, 5});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 116, 4});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 116, 3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 116, 1});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, 116, 2});
            }

         }
      });
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      var4.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var4, var1) {
         final UiMgr this$0;
         final Context val$context;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
            this.val$context = var3;
         }

         public void addViewToWindows() {
            this.val$parkPageUiSet.setShowRadar(SharePreUtil.getBoolValue(this.val$context, "share_281_show_radar", true));
         }
      });
   }

   private MsgMgr getMsgMgr() {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(this.mContext);
      }

      return this.mMsgMgr;
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)var1, 1});
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case -1423573049:
            if (var1.equals("ac_max")) {
               var2 = 1;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
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
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(19);
            break;
         case 1:
            this.sendAirCommand(22);
            break;
         case 2:
            this.sendAirCommand(20);
            break;
         case 3:
            this.sendAirCommand(8);
            break;
         case 4:
            this.sendAirCommand(7);
            break;
         case 5:
            this.sendAirCommand(23);
            break;
         case 6:
            this.sendAirCommand(21);
            break;
         case 7:
            this.sendAirCommand(16);
            break;
         case 8:
            this.sendAirCommand(1);
            break;
         case 9:
            this.sendAirCommand(25);
      }

   }

   private void sendData(byte[] var1, int var2, int var3, int var4, int var5, int var6) {
      var1 = Arrays.copyOf(var1, var1.length);
      if (var3 < var1.length) {
         var1[0] = 22;
         var1[1] = (byte)var2;
         var1[var3] = (byte)DataHandleUtils.setIntFromByteWithBit(var1[var3], var4, var5, var6);
         CanbusMsgSender.sendMsg(var1);
      }
   }

   private void sendFrontAirCommand(int var1, int var2) {
      this.sendAirCommand(this.getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[var1][var2]);
   }

   private void sendRearAirCommand(int var1, int var2) {
      this.sendAirCommand(this.getAirUiSet(this.mContext).getRearArea().getLineBtnAction()[var1][var2]);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
