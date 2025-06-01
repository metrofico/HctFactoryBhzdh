package com.hzbhd.canbus.car._467;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car_cus._467.air.data.AirData;
import com.hzbhd.canbus.car_cus._467.air.observer.AirBuilder;
import com.hzbhd.canbus.car_cus._467.air.window.AirWindow;
import com.hzbhd.canbus.car_cus._467.drive.data.DriveData;
import com.hzbhd.canbus.car_cus._467.drive.util.NotifyDriveDate;
import com.hzbhd.canbus.car_cus._467.smartPanel.NotifyPanel;
import com.hzbhd.canbus.car_cus._467.smartPanel.PanelState;
import com.hzbhd.canbus.car_cus._467.smartPanel.window.PanelButton;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.ui.util.BaseUtil;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class MsgMgr extends AbstractMsgMgr {
   public static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
   public static final String SYSTEM_DIALOG_REASON_KEY = "reason";
   private final String QU_LI_TAG = "DWS.ID439.TROQUE.EXTRATION";
   private AlertWindowView alertWindowView;
   int differentId;
   int eachId;
   private boolean firstTag = true;
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         switch (var1.what) {
            case 252:
               if (System.getInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_467", 0) == 1 && !this.this$0.panelButton.getShowTag()) {
                  this.this$0.panelButton.show();
               }
               break;
            case 253:
               if (this.this$0.panelButton != null && this.this$0.panelButton.getShowTag()) {
                  this.this$0.panelButton.hide();
               }
               break;
            case 254:
               if (AirWindow.getInstance().addTag) {
                  AirWindow.getInstance().hide();
               }
               break;
            case 255:
               if (!AirWindow.getInstance().addTag) {
                  AirWindow.getInstance().setAutoExit(false);
                  AirWindow.getInstance().show();
               }
         }

      }
   };
   private HomeWatcherReceiver mHomeKeyReceiver;
   int[] mPanelData;
   int[] mRepairData;
   int nowError = 0;
   PanelButton panelButton;
   private SimpleDateFormat simpleDateFormat1;
   private int timeSyncTag = 0;

   private void checkCarConfig(int[] var1) {
      PanelState.carConfig = var1[8];
      BaseUtil.INSTANCE.runUi(new Function0(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            if (PanelState.carConfig == 0) {
               if (this.this$0.panelButton != null) {
                  this.this$0.panelButton.hide();
               }
            } else if (this.this$0.panelButton != null) {
               this.this$0.panelButton.show();
            }

            return null;
         }
      });
   }

   private AlertWindowView getAlertView() {
      if (this.alertWindowView == null) {
         this.alertWindowView = new AlertWindowView();
      }

      return this.alertWindowView;
   }

   private boolean isAirDataNoChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return true;
      } else {
         this.mAirData = var1;
         return false;
      }
   }

   private boolean isPanelDataNoChange(int[] var1) {
      if (Arrays.equals(this.mPanelData, var1)) {
         return true;
      } else {
         this.mPanelData = var1;
         return false;
      }
   }

   private boolean isRepairDataNoChange(int[] var1) {
      if (Arrays.equals(this.mRepairData, var1)) {
         return true;
      } else {
         this.mRepairData = var1;
         return false;
      }
   }

   private void set0x37() {
      if (!this.isRepairDataNoChange(this.mCanBusInfoInt)) {
         StringBuilder var2 = new StringBuilder();
         int[] var1 = this.mCanBusInfoInt;
         DriveData.dataA2 = var2.append(DataHandleUtils.getMsbLsbResult(var1[2], var1[3])).append("").toString();
         DriveData.dataB2 = this.mCanBusInfoInt[4] + "";
         DriveData.dataC2 = this.mCanBusInfoInt[5] - 40 + "";
         DriveData.dataD2 = this.mCanBusInfoInt[6] * 4 + "";
         DriveData.dataA5 = this.formatDecimal1.format((double)((float)this.mCanBusInfoInt[7] * 0.4F)) + "";
         DriveData.dataB5 = this.formatDecimal1.format((double)((float)this.mCanBusInfoInt[11] * 0.4F)) + "";
         DriveData.dataC5 = this.formatDecimal1.format((double)((float)this.mCanBusInfoInt[8] * 0.1F)) + "";
         DriveData.dataD5 = this.formatDecimal1.format((double)((float)this.mCanBusInfoInt[9] * 0.2F)) + "";
         System.putInt(this.mContext.getContentResolver(), "DWS.ID439.TROQUE.EXTRATION", this.mCanBusInfoInt[10]);
         NotifyDriveDate.getInstance().update();
      }
   }

   private void setAir0x21(int[] var1) {
      this.updateOutDoorTemp(this.mContext, var1[7] - 40 + this.getTempUnitC(this.mContext));
      this.setAirAlert(var1[9]);
      DriveData.tempOut = var1[7] - 40 + this.getTempUnitC(this.mContext);
      DriveData.tempIn = var1[6] - 40 + this.getTempUnitC(this.mContext);
      DriveData.tempEvaporation = var1[5] - 40 + this.getTempUnitC(this.mContext);
      NotifyDriveDate.getInstance().update();
      var1[7] = 0;
      var1[9] = 0;
      var1[6] = 0;
      var1[5] = 0;
      if (!this.isAirDataNoChange(var1)) {
         AirData.power = DataHandleUtils.getBoolBit7(var1[2]);
         AirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         AirData.in_out_cycle = DataHandleUtils.getBoolBit5(var1[2]);
         AirData.auto = DataHandleUtils.getBoolBit4(var1[2]);
         AirData.defog = DataHandleUtils.getBoolBit2(var1[2]);
         AirData.heat = DataHandleUtils.getBoolBit3(var1[2]);
         int var2 = var1[4];
         boolean var3;
         if (var2 != 4 && var2 != 5) {
            var3 = false;
         } else {
            var3 = true;
         }

         AirData.wind_window = var3;
         var2 = var1[4];
         if (var2 != 1 && var2 != 2) {
            var3 = false;
         } else {
            var3 = true;
         }

         AirData.wind_body = var3;
         var2 = var1[4];
         if (var2 != 2 && var2 != 3 && var2 != 4) {
            var3 = false;
         } else {
            var3 = true;
         }

         AirData.wind_foot = var3;
         AirData.temp = var1[8];
         AirData.wind_level = var1[3];
         AirData.warm_level = var1[10];
         AirData.appointmentTime = var1[11];
         if (this.firstTag) {
            this.firstTag = false;
         } else {
            if (!this.getReverseState() && !AirWindow.getInstance().addTag) {
               AirWindow.getInstance().setAutoExit(true);
               AirWindow.getInstance().show();
            }

            AirBuilder.getInstance().dataChange();
         }
      }
   }

   private void setAirAlert(int var1) {
      if (this.nowError != var1) {
         this.nowError = var1;
         if (var1 != 0) {
            String var2;
            switch (var1) {
               case 1:
                  var2 = this.mContext.getString(2131765956);
                  break;
               case 2:
                  var2 = this.mContext.getString(2131765967);
                  break;
               case 3:
                  var2 = this.mContext.getString(2131765975);
                  break;
               case 4:
                  var2 = this.mContext.getString(2131765976);
                  break;
               case 5:
                  var2 = this.mContext.getString(2131765977);
                  break;
               case 6:
                  var2 = this.mContext.getString(2131765978);
                  break;
               case 7:
               case 9:
               case 10:
               case 15:
               case 16:
               case 17:
               case 20:
               case 23:
               case 24:
               case 28:
               default:
                  var2 = "DEFAULT";
                  break;
               case 8:
                  var2 = this.mContext.getString(2131765979);
                  break;
               case 11:
                  var2 = this.mContext.getString(2131765980);
                  break;
               case 12:
                  var2 = this.mContext.getString(2131765981);
                  break;
               case 13:
                  var2 = this.mContext.getString(2131765982);
                  break;
               case 14:
                  var2 = this.mContext.getString(2131765983);
                  break;
               case 18:
                  var2 = this.mContext.getString(2131765957);
                  break;
               case 19:
                  var2 = this.mContext.getString(2131765958);
                  break;
               case 21:
                  var2 = this.mContext.getString(2131765959);
                  break;
               case 22:
                  var2 = this.mContext.getString(2131765960);
                  break;
               case 25:
                  var2 = this.mContext.getString(2131765961);
                  break;
               case 26:
                  var2 = this.mContext.getString(2131765962);
                  break;
               case 27:
                  var2 = this.mContext.getString(2131765963);
                  break;
               case 29:
                  var2 = this.mContext.getString(2131765964);
                  break;
               case 30:
                  var2 = this.mContext.getString(2131765965);
                  break;
               case 31:
                  var2 = this.mContext.getString(2131765966);
                  break;
               case 32:
                  var2 = this.mContext.getString(2131765968);
                  break;
               case 33:
                  var2 = this.mContext.getString(2131765969);
                  break;
               case 34:
                  var2 = this.mContext.getString(2131765970);
                  break;
               case 35:
                  var2 = this.mContext.getString(2131765971);
                  break;
               case 36:
                  var2 = this.mContext.getString(2131765972);
                  break;
               case 37:
                  var2 = this.mContext.getString(2131765973);
                  break;
               case 38:
                  var2 = this.mContext.getString(2131765974);
            }

            if (!var2.equals("DEFAULT")) {
               this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var2) {
                  final MsgMgr this$0;
                  final String val$alertContent;

                  {
                     this.this$0 = var1;
                     this.val$alertContent = var2;
                  }

                  public void callback() {
                     try {
                        this.this$0.getAlertView().hide();
                        AlertWindowView var2 = this.this$0.getAlertView();
                        Context var1 = this.this$0.mContext;
                        StringBuilder var3 = new StringBuilder();
                        var2.show(var1, var3.append(this.this$0.mContext.getString(2131765954)).append(":").append(this.val$alertContent).toString());
                     } catch (Exception var4) {
                        Log.d("Exception", "Window overlap conflict does not affect normal use" + var4);
                     }

                  }
               });
            }
         }
      }
   }

   private void setPanel0x22(int[] var1) {
      if (!this.isPanelDataNoChange(var1)) {
         PanelState.sp1 = DataHandleUtils.getBoolBit7(var1[2]);
         PanelState.sp2 = DataHandleUtils.getBoolBit6(var1[2]);
         PanelState.sp3 = DataHandleUtils.getBoolBit5(var1[2]);
         PanelState.sp4 = DataHandleUtils.getBoolBit4(var1[2]);
         PanelState.sp6 = DataHandleUtils.getBoolBit3(var1[2]);
         PanelState.sp7 = DataHandleUtils.getBoolBit2(var1[2]);
         PanelState.sp16 = DataHandleUtils.getBoolBit1(var1[2]);
         PanelState.sp17 = DataHandleUtils.getBoolBit0(var1[2]);
         PanelState.sp14 = var1[3];
         PanelState.sp11 = DataHandleUtils.getBoolBit7(var1[4]);
         PanelState.sp12 = DataHandleUtils.getBoolBit6(var1[4]);
         PanelState.sp13 = DataHandleUtils.getBoolBit5(var1[4]);
         PanelState.sp15 = DataHandleUtils.getBoolBit4(var1[4]);
         PanelState.sp10 = DataHandleUtils.getBoolBit3(var1[4]);
         PanelState.sp5 = DataHandleUtils.getBoolBit2(var1[4]);
         PanelState.sp9 = DataHandleUtils.getBoolBit0(var1[4]);
         PanelState.sp8 = DataHandleUtils.getBoolBit1(var1[4]);
         PanelState.ultraVires = DataHandleUtils.getBoolBit0(var1[5]);
         PanelState.retarder = DataHandleUtils.getBoolBit1(var1[5]);
         PanelState.abs = DataHandleUtils.getBoolBit2(var1[5]);
         PanelState.sp18 = DataHandleUtils.getBoolBit3(var1[5]);
         this.setBacklightLevel((var1[6] + 1) / 2);
         NotifyPanel.getInstance().update();
      }
   }

   private void setSwc0x20() {
      int[] var1 = this.mCanBusInfoInt;
      switch (var1[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 207, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 206, var1[3]);
            break;
         case 5:
            this.realKeyLongClick1(this.mContext, 3, var1[3]);
         case 6:
         default:
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
            break;
         case 9:
            if (!AirWindow.getInstance().hide()) {
               this.realKeyLongClick1(this.mContext, 50, this.mCanBusInfoInt[3]);
            }
      }

   }

   private void setTime0x36() {
      int var1 = this.timeSyncTag + 1;
      this.timeSyncTag = var1;
      if (var1 <= 10) {
         if (this.simpleDateFormat1 == null) {
            this.simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
         }

         try {
            SimpleDateFormat var3 = this.simpleDateFormat1;
            StringBuilder var2 = new StringBuilder();
            SystemClock.setCurrentTimeMillis(var3.parse(var2.append(this.mCanBusInfoInt[2] + 2000).append("-").append(this.mCanBusInfoInt[3]).append("-").append(this.mCanBusInfoInt[4]).append(" ").append(this.mCanBusInfoInt[5]).append(":").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).toString()).getTime());
         } catch (ParseException var4) {
            Log.e("TIME_SYNC_ERROR", var4.toString());
            var4.printStackTrace();
         }

      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 48) {
         if (var3 != 54) {
            if (var3 != 55) {
               switch (var3) {
                  case 32:
                     this.setSwc0x20();
                     break;
                  case 33:
                     this.setAir0x21(var4);
                     break;
                  case 34:
                     this.setPanel0x22(var4);
               }
            } else {
               this.set0x37();
            }
         } else {
            this.setTime0x36();
            this.checkCarConfig(this.mCanBusInfoInt);
         }
      } else {
         this.updateVersionInfo(var1, this.getVersionStr(var2));
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      AirData.exitTime = SharePreUtil.getIntValue(var1, "key.air.exit.time", 10000);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
      this.registerAirControlListener();
      this.registerHomeKey(var1);
      if (System.getInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_467", 0) == 1) {
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
            final MsgMgr this$0;
            final Context val$context;

            {
               this.this$0 = var1;
               this.val$context = var2;
            }

            public void callback() {
               if (this.this$0.panelButton == null) {
                  this.this$0.panelButton = new PanelButton(this.val$context, new PanelButton.PanoramaListener(this) {
                     final <undefinedtype> this$1;

                     {
                        this.this$1 = var1;
                     }

                     public void clickEvent() {
                        this.this$1.this$0.startOtherActivity(this.this$1.val$context, HzbhdComponentName.ID439PanelActivity1280x720);
                     }
                  });
               }

               this.this$0.panelButton.show();
            }
         });
      }

   }

   // $FF: synthetic method
   void lambda$registerAirControlListener$0$com_hzbhd_canbus_car__467_MsgMgr(String var1) {
      if (var1.equals("SHOW.ID439.AIR.PAGE")) {
         Message var2 = new Message();
         var2.what = 255;
         this.mHandler.sendMessage(var2);
      }

   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
      this.timeSyncTag = 0;
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void registerAirControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("can.request.data.share", new MsgMgr$$ExternalSyntheticLambda0(this));
   }

   public void registerHomeKey(Context var1) {
      this.mHomeKeyReceiver = new HomeWatcherReceiver(this);
      IntentFilter var2 = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
      var1.registerReceiver(this.mHomeKeyReceiver, var2);
   }

   public void reverseStateChange(boolean var1) {
      super.reverseStateChange(var1);
      Message var2;
      if (var1) {
         var2 = new Message();
         var2.what = 254;
         this.mHandler.sendMessage(var2);
      }

      if (var1) {
         var2 = new Message();
         var2.what = 253;
         this.mHandler.sendMessage(var2);
      } else {
         var2 = new Message();
         var2.what = 252;
         this.mHandler.sendMessage(var2);
      }

   }

   private class HomeWatcherReceiver extends BroadcastReceiver {
      final MsgMgr this$0;

      private HomeWatcherReceiver(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      HomeWatcherReceiver(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void onReceive(Context var1, Intent var2) {
         if (var2.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && "homekey".equals(var2.getStringExtra("reason"))) {
            Message var3 = new Message();
            var3.what = 254;
            this.this$0.mHandler.sendMessage(var3);
         }

      }
   }
}
