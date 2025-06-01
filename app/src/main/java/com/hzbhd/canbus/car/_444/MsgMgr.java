package com.hzbhd.canbus.car._444;

import android.content.Context;
import android.content.IntentFilter;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._444.speech.ISysRxListener;
import com.hzbhd.canbus.car._444.speech.STxData;
import com.hzbhd.canbus.car._444.speech.SysRxSpeechReceiver;
import com.hzbhd.canbus.car._444.speech.SysToSpeechReceiver;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgMgr extends AbstractMsgMgr {
   private static SysToSpeechReceiver.CarCtrl carCtrl;
   private static SysToSpeechReceiver sysToSpeechReceiver;
   private String airJsonStr;
   private int[] mCanBusInfoInt;
   int[] mLightData;
   private UiMgr mUiMgr;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String intArrayToJsonStr(int[] var1) {
      this.airJsonStr = "{";

      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var2 == var1.length - 1) {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + "}";
         } else {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + ",";
         }
      }

      return this.airJsonStr;
   }

   private boolean isLightDataChange(int[] var1) {
      if (Arrays.equals(this.mLightData, var1)) {
         return false;
      } else {
         this.mLightData = var1;
         return true;
      }
   }

   private void registerSanYiVoiceReceiver(Context var1) {
      SysRxSpeechReceiver var2 = new SysRxSpeechReceiver(var1, new VoiceControl(this));
      IntentFilter var3 = new IntentFilter();
      var3.addAction("com.hzbhd.speech.to.sys");
      var1.registerReceiver(var2, var3);
   }

   private void sendLightInfoToSheech(int[] var1) {
      int var2 = this.getMsDataType(var1);
      if (var2 != 419315745) {
         if (var2 == 419316001) {
            if (DataHandleUtils.getBoolBit4(var1[7])) {
               carCtrl.sendHeadlight(true);
            } else {
               carCtrl.sendHeadlight(false);
            }

            if (DataHandleUtils.getBoolBit6(var1[7])) {
               carCtrl.sendHighbeam(true);
            } else {
               carCtrl.sendHighbeam(false);
            }
         }
      } else if (DataHandleUtils.getBoolBit2(var1[7])) {
         carCtrl.sendAlarmLight(true);
      } else {
         carCtrl.sendAlarmLight(false);
      }

   }

   private void setTime0x18FEE6FC(int[] var1) {
      try {
         JSONObject var2 = new JSONObject();
         var2.put("format", "unknown");
         var2.put("year", var1[12] + 1985);
         var2.put("month", var1[10]);
         var2.put("day", var1[11]);
         var2.put("hour", var1[9]);
         var2.put("minute", var1[8]);
         var2.put("second", var1[7]);
         var2.put("millisecond", 0);
         ShareDataManager.getInstance().reportString("canbus.time", var2.toString());
      } catch (JSONException var3) {
         var3.printStackTrace();
      }

   }

   protected void ShareBasicInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportAllCanBusData(this.intArrayToJsonStr(var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      if (this.getMsDataType(var3) == 419358460) {
         this.setTime0x18FEE6FC(this.mCanBusInfoInt);
      } else {
         this.ShareBasicInfo(this.getByteArrayToIntArray(var2));
         if (this.isLightDataChange(this.getByteArrayToIntArray(var2))) {
            this.sendLightInfoToSheech(this.getByteArrayToIntArray(var2));
         }

      }
   }

   protected int getMsDataType(int[] var1) {
      int var3 = var1[2];
      int var4 = var1[3];
      int var2 = var1[4];
      return var1[5] & 255 | (var3 & 255) << 24 | (var4 & 255) << 16 | (var2 & 255) << 8;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).registerCanBusAirControlListener();
      this.registerSanYiVoiceReceiver(var1);
      SysToSpeechReceiver var2 = new SysToSpeechReceiver(var1);
      sysToSpeechReceiver = var2;
      carCtrl = var2.getCar();
      this.updateVersionInfo(var1, "CANBOX-V1.0.2");
      ShareDataServiceImpl.setString("can.bus.all.data.share", "REQUEST_LAUNCHER_SWITCH_STATE");
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
   }

   private class VoiceControl implements ISysRxListener {
      final MsgMgr this$0;

      private VoiceControl(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      VoiceControl(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void AcAir(boolean var1) {
      }

      public void AcAuto(boolean var1) {
      }

      public void AcCold(boolean var1) {
      }

      public void AcDefrost(boolean var1) {
      }

      public void AcLoop(boolean var1) {
      }

      public void AcMode(STxData.AcCtrl.Mode.ModeEnum var1) {
      }

      public void AcTempDec(int var1) {
      }

      public void AcTempInc(int var1) {
      }

      public void AcTempTo(int var1) {
      }

      public void AcWarm(boolean var1) {
      }

      public void AcWind(boolean var1) {
      }

      public void AcWindTo(int var1) {
      }

      public void CarAlarmlight(boolean var1) {
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 127, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 127, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 127, -1, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarClearancelamps(boolean var1) {
      }

      public void CarHeadlight(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarHighBeam(boolean var1) {
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarLock(boolean var1) {
      }

      public void CarReadinglamp(boolean var1) {
      }

      public void CarToplight(boolean var1) {
      }

      public void CarWindowClose() {
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
      }

      public void CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum var1) {
         if (var1.equals(com.hzbhd.canbus.car._434.speech.STxData.CarCtrl.WindowOpen.TypeEnum.leftfront)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
         }

         if (var1.equals(com.hzbhd.canbus.car._434.speech.STxData.CarCtrl.WindowOpen.TypeEnum.rightfront)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarWiper(boolean var1) {
      }

      public void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum var1) {
      }

      public void CarWiperMove(int var1) {
      }

      public void SystemSync() {
      }
   }
}
