package com.hzbhd.canbus.car._308;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private AmplifierPageUiSet mAmplifierPageUiSet;
   OnAirBtnClickListener mOnAirBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         String var2 = this.this$0.mAmplifierPageUiSet.getLineBtnAction()[var1];
         var2.hashCode();
         if (var2.equals("bose_center")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 15, 0});
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontBottom[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontRight[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontLeft[var1]);
      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         UiMgr.sendAirCmd(9);
      }

      public void onClickRight() {
         UiMgr.sendAirCmd(10);
      }
   };
   OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         if (var1 == AmplifierActivity.AmplifierPosition.LEFT) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 8, 0});
         } else if (var1 == AmplifierActivity.AmplifierPosition.RIGHT) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 7, 0});
         } else if (var1 == AmplifierActivity.AmplifierPosition.FRONT) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 9, 0});
         } else if (var1 == AmplifierActivity.AmplifierPosition.REAR) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 10, 0});
         }

      }
   };
   OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         if (var1 == AmplifierActivity.AmplifierBand.VOLUME_Min) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 2, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.BASS_Min) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 4, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE_Min) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 6, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.VOLUME_Plus) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 1, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.BASS_Plus) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 3, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE_Plus) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 5, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.CUSTOM_BASS_Min) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 14, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.CUSTOM_BASS_Plus) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 13, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Min) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 12, 0});
         } else if (var1 == AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Plus) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, 11, 0});
         }

      }
   };
   OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var3.hashCode();
         if (var3.equals("_308_title_18")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -105, 8, 1});
         }

      }
   };
   OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         String var3 = this.this$0.mOriginalCarDevicePageUiSet.getRowBottomBtnAction()[var1];
         var3.hashCode();
         int var2 = var3.hashCode();
         byte var4 = -1;
         switch (var2) {
            case 3739:
               if (var3.equals("up")) {
                  var4 = 0;
               }
               break;
            case 3089570:
               if (var3.equals("down")) {
                  var4 = 1;
               }
               break;
            case 3443508:
               if (var3.equals("play")) {
                  var4 = 2;
               }
               break;
            case 3540994:
               if (var3.equals("stop")) {
                  var4 = 3;
               }
               break;
            case 106440182:
               if (var3.equals("pause")) {
                  var4 = 4;
               }
         }

         switch (var4) {
            case 0:
               this.this$0.sendOnOriginalCmd(5);
               break;
            case 1:
               this.this$0.sendOnOriginalCmd(4);
               break;
            case 2:
               this.this$0.sendOnOriginalCmd(1);
               break;
            case 3:
               this.this$0.sendOnOriginalCmd(3);
               break;
            case 4:
               this.this$0.sendOnOriginalCmd(2);
         }

      }
   };
   OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onBackPressed() {
         this.this$0.sendOnOriginalCmd(0);
      }
   };
   OnOriginalCarDevicePageStatusListener mOnOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.sendOnOriginalCmd(128);
      }
   };
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         if (!var4.equals("_308_title_12")) {
            if (var4.equals("_308_title_14")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 3, (byte)var3});
            }
         } else {
            var1 = var3 - GeneralAmplifierData.custom2Bass;
            Log.d("scyscyscy", "---------->" + var1);
            if (var1 > 0) {
               this.this$0.seekbarSpeed(11, var1);
            } else {
               this.this$0.seekbarSpeed(12, -var1);
            }
         }

      }
   };
   OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         var2 = var4.hashCode();
         byte var5 = -1;
         switch (var2) {
            case -68888180:
               if (var4.equals("_308_title_13")) {
                  var5 = 0;
               }
            case -68888179:
            default:
               break;
            case -68888178:
               if (var4.equals("_308_title_15")) {
                  var5 = 1;
               }
               break;
            case -68888177:
               if (var4.equals("_308_title_16")) {
                  var5 = 2;
               }
               break;
            case -68888176:
               if (var4.equals("_308_title_17")) {
                  var5 = 3;
               }
         }

         switch (var5) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 2, (byte)var3});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 4, (byte)var3});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 5, (byte)var3});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 7, (byte)var3});
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr.sendAirCmd(11);
      }

      public void onClickUp() {
         UiMgr.sendAirCmd(12);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr.sendAirCmd(13);
      }

      public void onClickUp() {
         UiMgr.sendAirCmd(14);
      }
   };
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SettingPageUiSet mSettingPageUiSet;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.mSettingPageUiSet = var2;
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      this.mSettingPageUiSet.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
      this.mSettingPageUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      AirPageUiSet var5 = this.getAirUiSet(var1);
      String[][] var3 = var5.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var3[0];
      this.mAirBtnListFrontLeft = var3[1];
      this.mAirBtnListFrontRight = var3[2];
      this.mAirBtnListFrontBottom = var3[3];
      var5.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var5.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var5.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      AmplifierPageUiSet var6 = this.getAmplifierPageUiSet(var1);
      this.mAmplifierPageUiSet = var6;
      var6.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
      this.mAmplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
      this.mAmplifierPageUiSet.setOnAirBtnClickListeners(this.mOnAirBtnClickListener);
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(var1);
      this.mOriginalCarDevicePageUiSet = var4;
      var4.setOnOriginalCarDevicePageStatusListener(this.mOnOriginalCarDevicePageStatusListener);
      this.mOriginalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.mOnOriginalCarDeviceBackPressedListener);
      this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
   }

   private void seekbarSpeed(int var1, int var2) {
      for(int var3 = 0; var3 < var2; ++var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte)var1, 0});
      }

   }

   public static void sendAirCmd(int var0) {
      (new Thread(var0) {
         final int val$data0;

         {
            this.val$data0 = var1;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte)this.val$data0, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte)this.val$data0, 0});
         }
      }).start();
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
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 1;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 2;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 3;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 4;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 5;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 6;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 7;
            }
      }

      switch (var2) {
         case 0:
            sendAirCmd(5);
            break;
         case 1:
            sendAirCmd(6);
            break;
         case 2:
            sendAirCmd(8);
            break;
         case 3:
            sendAirCmd(1);
            break;
         case 4:
            sendAirCmd(4);
            break;
         case 5:
            sendAirCmd(7);
            break;
         case 6:
            sendAirCmd(0);
            break;
         case 7:
            if (!GeneralAirData.in_out_cycle) {
               sendAirCmd(3);
            } else {
               sendAirCmd(2);
            }
      }

   }

   private void sendOnOriginalCmd(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte)this.val$cmd, 0});
         }
      }).start();
   }
}
