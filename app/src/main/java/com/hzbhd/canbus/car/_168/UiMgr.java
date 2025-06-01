package com.hzbhd.canbus.car._168;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   protected static final String CAN_1168_SAVE_LANGUAGE = "__1168_SAVE_LANGUAGE";
   protected static final int CAR_ID_EXCELLE_2015 = 251;
   protected static final int CAR_ID_GL6 = 255;
   protected static final int CAR_ID_GL8_2017 = 7;
   protected static final int CAR_ID_LACROSSE_2004 = 253;
   protected static final int CAR_ID_MALIBU_2016 = 254;
   protected static final int CAR_ID_VERANO_2015 = 252;
   protected static int mDiffid;
   private static int mFrontWindMode;
   private static int mRearWindMode;
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private FrontArea mFrontArea;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirCommand(25);
               }
            } else {
               this.this$0.sendAirCommand(35);
            }
         } else {
            this.this$0.sendAirCommand(30);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCommandSwitch(GeneralAirData.auto, 4);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     if (GeneralAirData.aqs) {
                        this.this$0.sendAirCommandSwitch(true ^ GeneralAirData.in_out_cycle, 7);
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 59, 7, 2});
                     }
                  }
               } else {
                  this.this$0.sendAirCommandSwitch(GeneralAirData.sync, 2);
               }
            } else {
               this.this$0.sendAirCommandSwitch(GeneralAirData.sync, 15);
            }
         } else {
            this.this$0.sendAirCommandSwitch(GeneralAirData.power, 1);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCommandSwitch(GeneralAirData.in_out_cycle, 7);
         }

      }
   };
   private OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         if (var1 == 9 && var2 == 1) {
            this.this$0.sendData(new byte[]{22, -102, 2, 1});
         }

      }
   };
   private OnOnStarPhoneMoreInfoClickListener mOnOnStarPhoneMoreInfoClickListener = new OnOnStarPhoneMoreInfoClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void exit() {
         CanbusMsgSender.sendMsg(new byte[]{22, -70, 8, 0});
      }

      public void handOff() {
         if (this.this$0.isCallIn()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 2, 0});
         } else if (this.this$0.isCallOut() || this.this$0.isCallTalking()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 3, 0});
         }

      }

      public void handOn(String var1) {
         if (this.this$0.isCallIn()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 1, 0});
         } else if (this.this$0.isCallNone() && var1.length() > 0) {
            byte[] var2 = this.this$0.fillSpaces(var1 + "\u0000").getBytes();
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -69}, var2));
         }

      }

      public void init() {
         CanbusMsgSender.sendMsg(new byte[]{22, -70, 8, 1});
      }

      public void mute() {
         CanbusMsgSender.sendMsg(new byte[]{22, -70, 5, 0});
      }

      public void numberClick(int var1) {
         byte var2 = this.this$0.getAsciiNum(var1);
         if (this.this$0.isCallTalking() || this.this$0.isCallOut()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 4, var2});
         }

      }

      public void reDial() {
         if (GeneralOnStartData.mOnStarStatus == 0 || GeneralOnStartData.mOnStarStatus == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 7, 0});
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         switch (var1) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)(var2 + 1), (byte)var3});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, 74, (byte)(var2 + 1), (byte)var3});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, 90, (byte)(var2 + 1), (byte)var3});
               break;
            case 3:
               if (var2 != 2 && var2 != 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte)(var2 + 1), (byte)var3});
               } else if (UiMgr.mDiffid != 251 && UiMgr.mDiffid != 252) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte)(var2 + 1), (byte)var3});
               } else if (var3 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte)(var2 + 1), (byte)(var3 + 1)});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte)(var2 + 1), (byte)var3});
               }
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, 107, (byte)(var2 + 1), (byte)var3});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, 108, (byte)(var2 + 1), (byte)var3});
            case 6:
            default:
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, 122, (byte)(var2 + 1), (byte)var3});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)(var2 + 1), (byte)var3});
               break;
            case 9:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)((byte)var3 + 1)});
                  SharePreUtil.setIntValue(this.this$0.mContext, "__1168_SAVE_LANGUAGE", var3);
                  this.this$0.mMsgMgr.setLanguage_recNull(this.this$0.mContext);
               }
               break;
            case 10:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -54, 2});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 96, 5, 1, -61});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 96, 5, 1, -62});
               }
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommandReduce(12);
      }

      public void onClickUp() {
         this.this$0.sendAirCommandPlus(12);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommandReduce(13);
      }

      public void onClickUp() {
         this.this$0.sendAirCommandPlus(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommandReduce(22);
      }

      public void onClickUp() {
         Log.e("", "");
         this.this$0.sendAirCommandPlus(22);
      }
   };
   private RearArea mRearArea;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      mDiffid = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      this.airPageUiSet = var2;
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommandReduce(11);
         }

         public void onClickRight() {
            this.this$0.sendAirCommandPlus(11);
         }
      });
      this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommandReduce(21);
         }

         public void onClickRight() {
            this.this$0.sendAirCommandPlus(21);
         }
      });
      SingletonForKt.INSTANCE.setAirListener(this.airPageUiSet);
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var3.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
      SingletonForKt.INSTANCE.setSettingsListener(var3);
      this.getOnStartPageUiSet(var1).setmOnOnStarPhoneMoreInfoClickListener(this.mOnOnStarPhoneMoreInfoClickListener);
      this.mMsgMgr.setLanguage_recNull(this.mContext);
   }

   private String fillSpaces(String var1) {
      String var4 = var1;
      if (var1.length() < 32) {
         int var3 = var1.length();
         int var2 = 0;

         while(true) {
            var4 = var1;
            if (var2 >= 32 - var3) {
               break;
            }

            var1 = var1 + " ";
            ++var2;
         }
      }

      return var4;
   }

   private byte getAsciiNum(int var1) {
      if (var1 <= 9) {
         return (byte)(var1 + 48);
      } else if (var1 == 10) {
         return 42;
      } else {
         return (byte)(var1 == 11 ? 35 : 0);
      }
   }

   private boolean isCallIn() {
      int var1 = GeneralOnStartData.mOnStarStatus;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   private boolean isCallNone() {
      boolean var1;
      if (GeneralOnStartData.mOnStarStatus != 0 && GeneralOnStartData.mOnStarStatus != 4) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private boolean isCallOut() {
      boolean var1;
      if (GeneralOnStartData.mOnStarStatus == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isCallTalking() {
      boolean var1;
      if (GeneralOnStartData.mOnStarStatus == 3) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void sendAirCommand(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   private void sendAirCommandFrontWindMode() {
      int var1 = mFrontWindMode;
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (new byte[]{5, 9, 10, 33, 34})[var1], -1});
      var1 = mFrontWindMode + 1;
      mFrontWindMode = var1;
      if (var1 >= 5) {
         mFrontWindMode = 0;
      }

   }

   private void sendAirCommandPlus(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, 1});
   }

   private void sendAirCommandRearWindMode() {
      int var1 = mRearWindMode;
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (new byte[]{17, 18, 19, 20})[var1], -1});
      var1 = mRearWindMode + 1;
      mRearWindMode = var1;
      if (var1 >= 4) {
         mRearWindMode = 0;
      }

   }

   private void sendAirCommandReduce(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, 2});
   }

   private void sendAirCommandSwitch(boolean var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var2, (byte)(var1 ^ 1)});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
