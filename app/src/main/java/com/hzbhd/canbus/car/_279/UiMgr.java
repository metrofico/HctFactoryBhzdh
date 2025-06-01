package com.hzbhd.canbus.car._279;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Timer;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private boolean isFf = false;
   private boolean isFr = false;
   private Context mContext;
   private int mCurrentCarId;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(3, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(2, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(0, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(1, var1);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(14);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(16);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(15);
      }
   };
   private Timer mTimer;
   private TimerTask mTimerTask;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mCurrentCarId = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2) {
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
               case -873936231:
                  if (var4.equals("_279_temperature_unit")) {
                     var5 = 0;
                  }
                  break;
               case -742624649:
                  if (var4.equals("_279_aud_obc")) {
                     var5 = 1;
                  }
                  break;
               case -614358364:
                  if (var4.equals("_279_distance_unit")) {
                     var5 = 2;
                  }
                  break;
               case 168170114:
                  if (var4.equals("_279_language")) {
                     var5 = 3;
                  }
                  break;
               case 327727460:
                  if (var4.equals("_279_memo")) {
                     var5 = 4;
                  }
                  break;
               case 393952956:
                  if (var4.equals("_279_speed_limit_activation")) {
                     var5 = 5;
                  }
                  break;
               case 651004575:
                  if (var4.equals("_279_oil_consumption_unit")) {
                     var5 = 6;
                  }
                  break;
               case 1456389983:
                  if (var4.equals("_279_vehicle_config")) {
                     var5 = 7;
                  }
                  break;
               case 1575463461:
                  if (var4.equals("_279_sound")) {
                     var5 = 8;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3, 0});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var3, 0});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3, 0});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3, 0});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 5, (byte)var3});
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
            if (!var4.equals("_279_speed_limit")) {
               if (var4.equals("_279_distance")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)(var3 >> 8 & 255), (byte)(var3 & 255)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)(var3 >> 8 & 255), (byte)(var3 & 255)});
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
            var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1749678073:
                  if (var3.equals("_279_oil_consumption_2_reset")) {
                     var4 = 0;
                  }
                  break;
               case 1372084209:
                  if (var3.equals("_279_restore_factory_default")) {
                     var4 = 1;
                  }
                  break;
               case 1657785542:
                  if (var3.equals("_279_oil_consumption_1_reset")) {
                     var4 = 2;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, 0, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 0, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 0, 0});
            }

         }
      });
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, (byte)var2});
            }

         }
      });
      var4.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -56, 4, (byte)(var2 + 10)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 3, (byte)(var2 + 10)});
            }

         }
      });
      PanelKeyPageUiSet var5 = this.getPanelKeyPageUiSet(var1);
      var5.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener(this, var5) {
         final UiMgr this$0;
         final PanelKeyPageUiSet val$panelKeyPageUiSet;

         {
            this.this$0 = var1;
            this.val$panelKeyPageUiSet = var2;
         }

         public void click(int var1) {
            String var3 = (String)this.val$panelKeyPageUiSet.getList().get(var1);
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1452640399:
                  if (var3.equals("_279_panel_am")) {
                     var4 = 0;
                  }
                  break;
               case -1452640361:
                  if (var3.equals("_279_panel_bt")) {
                     var4 = 1;
                  }
                  break;
               case -1452640244:
                  if (var3.equals("_279_panel_fm")) {
                     var4 = 2;
                  }
                  break;
               case -122728948:
                  if (var3.equals("_279_panel_left")) {
                     var4 = 3;
                  }
                  break;
               case -122689624:
                  if (var3.equals("_279_panel_mode")) {
                     var4 = 4;
                  }
                  break;
               case -122653840:
                  if (var3.equals("_279_panel_num1")) {
                     var4 = 5;
                  }
                  break;
               case -122653839:
                  if (var3.equals("_279_panel_num2")) {
                     var4 = 6;
                  }
                  break;
               case -122653838:
                  if (var3.equals("_279_panel_num3")) {
                     var4 = 7;
                  }
                  break;
               case -122653837:
                  if (var3.equals("_279_panel_num4")) {
                     var4 = 8;
                  }
                  break;
               case -122653836:
                  if (var3.equals("_279_panel_num5")) {
                     var4 = 9;
                  }
                  break;
               case -122653835:
                  if (var3.equals("_279_panel_num6")) {
                     var4 = 10;
                  }
                  break;
               case 484052844:
                  if (var3.equals("_279_panel_eject")) {
                     var4 = 11;
                  }
                  break;
               case 494377888:
                  if (var3.equals("_279_panel_power")) {
                     var4 = 12;
                  }
                  break;
               case 496030903:
                  if (var3.equals("_279_panel_right")) {
                     var4 = 13;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 5, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 0, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 7, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 3, 0});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 1});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 2});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 4});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 5});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 6});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 6, 0});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 4, 0});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -55, 8, 0});
            }

         }
      });
      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(var1);
      var3.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this, var3) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
         }

         public void onClickTopBtnItem(int var1) {
            String var2 = this.val$originalCarDevicePageUiSet.getRowTopBtnAction()[var1];
            var2.hashCode();
            if (!var2.equals("rdm")) {
               if (var2.equals("scan")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte)(GeneralOriginalCarDeviceData.scan ^ 1)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -54, 6, (byte)(GeneralOriginalCarDeviceData.rdm ^ 1)});
            }

         }
      });
      var3.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this, var3) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
         }

         public void onClickBottomBtnItem(int var1) {
            String var3 = this.val$originalCarDevicePageUiSet.getRowBottomBtnAction()[var1];
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
               case 3317767:
                  if (var3.equals("left")) {
                     var4 = 2;
                  }
                  break;
               case 3443508:
                  if (var3.equals("play")) {
                     var4 = 3;
                  }
                  break;
               case 3540994:
                  if (var3.equals("stop")) {
                     var4 = 4;
                  }
                  break;
               case 108511772:
                  if (var3.equals("right")) {
                     var4 = 5;
                  }
            }

            UiMgr var5;
            switch (var4) {
               case 0:
                  if (this.this$0.isFr) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 0});
                  } else if (this.this$0.isFf) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 0});
                     this.this$0.isFf = false;
                     this.this$0.mTimerTask = new TimerTask(this) {
                        final <undefinedtype> this$1;

                        {
                           this.this$1 = var1;
                        }

                        public void run() {
                           CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 1});
                           this.this$1.this$0.stopTimer();
                        }
                     };
                     this.this$0.startTimer(100L);
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 1});
                  }

                  var5 = this.this$0;
                  var5.isFr = var5.isFr ^ true;
                  break;
               case 1:
                  if (this.this$0.isFf) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 0});
                  } else if (this.this$0.isFr) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 0});
                     this.this$0.isFr = false;
                     this.this$0.mTimerTask = new TimerTask(this) {
                        final <undefinedtype> this$1;

                        {
                           this.this$1 = var1;
                        }

                        public void run() {
                           CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 1});
                           this.this$1.this$0.stopTimer();
                        }
                     };
                     this.this$0.startTimer(100L);
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 1});
                  }

                  var5 = this.this$0;
                  var5.isFf = var5.isFf ^ true;
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 7, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 0, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, 0});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, 8, 0});
            }

         }
      });
      var3.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onSongItemClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 2, (byte)(var1 + 1)});
         }

         public void onSongItemLongClick(int var1) {
         }
      });
      var3.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 12, 0, 0, 0, 0});
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
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
            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   private void sendAirCommand(int var1, int var2) {
      String var3 = this.getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[var1][var2];
      if (!TextUtils.isEmpty(var3)) {
         var3.hashCode();
         switch (var3) {
            case "front_defog":
               this.sendAirCommand(5);
               break;
            case "rear_defog":
               this.sendAirCommand(6);
               break;
            case "auto":
               this.sendAirCommand(4);
               break;
            case "sync":
               this.sendAirCommand(3);
               break;
            case "power":
               this.sendAirCommand(1);
               break;
            case "in_out_cycle":
               this.sendAirCommand(7);
         }

      }
   }

   private void startTimer(long var1) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1);
      }
   }

   private void startTimer(long var1, long var3) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1, var3);
      }
   }

   private void stopTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
