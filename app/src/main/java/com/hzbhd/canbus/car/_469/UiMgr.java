package com.hzbhd.canbus.car._469;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._469.air.OptionAirCmd469;
import com.hzbhd.canbus.car._469.impl.MsAbstractUiMgr;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.LogUtil;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class UiMgr extends MsAbstractUiMgr {
   private byte[] calibrationInfoByte = new byte[2];
   private int[] calibrationInfoInt = new int[2];
   private int[] canBusInfo = new int[14];
   private byte[] canBusInfoByte = new byte[14];
   private SharedPreferences.Editor editor;
   private byte[] lastCmd;
   private Context mContext;
   private SharedPreferences sharedPreferences;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SharedPreferences var2 = var1.getSharedPreferences("_s9_prefs", 0);
      this.sharedPreferences = var2;
      this.editor = var2.edit();
   }

   private void checkData(String var1, double var2) {
      if (LogUtil.log5()) {
         LogUtil.d("<UiMgr>: event: " + var1 + " value:" + var2);
      }

      var1.hashCode();
      float var4;
      OptionAirCmd469 var6;
      switch (var1) {
         case "systemOperationStatusControl":
            var6 = OptionAirCmd469.getInstance();
            var5 = (int)var2;
            var6.sysRunCtrl = var5;
            OptionAirCmd469.getInstance().controlOptionCmd();
            this.editor.putInt("sysRunCtrl", var5);
            this.editor.apply();
            break;
         case "settingTemperature":
            var6 = OptionAirCmd469.getInstance();
            var4 = (float)var2;
            var6.setTemp = var4;
            this.editor.putFloat("settingTemp", var4);
            this.editor.apply();
            OptionAirCmd469.getInstance().controlOptionCmd();
            break;
         case "timeSettingForColdMachineDefrostingProcess":
            var6 = OptionAirCmd469.getInstance();
            var5 = (int)var2;
            var6.defrstTimeSet = var5;
            this.editor.putInt("value4", var5);
            this.editor.apply();
            OptionAirCmd469.getInstance().controlOptionCmd();
            break;
         case "bootCommand":
            OptionAirCmd469.getInstance().longRangeCtrl = (int)var2;
            OptionAirCmd469.getInstance().controlOptionCmd();
            break;
         case "coldMachineDefrostingTerminationTemperatureSetting":
            var6 = OptionAirCmd469.getInstance();
            var4 = (float)var2;
            var6.defrstStopTempSet = var4;
            this.editor.putFloat("value2", var4);
            this.editor.apply();
            OptionAirCmd469.getInstance().controlOptionCmd();
            break;
         case "defrostingIntervalControlForColdMachines":
            var6 = OptionAirCmd469.getInstance();
            var5 = (int)var2;
            var6.defrstTimeCtrl = var5;
            OptionAirCmd469.getInstance().controlOptionCmd();
            this.editor.putInt("value1", var5);
            this.editor.apply();
            break;
         case "compressorOperationStatus":
            var6 = OptionAirCmd469.getInstance();
            var5 = (int)var2;
            var6.compRunSt = var5;
            OptionAirCmd469.getInstance().controlOptionCmd();
            this.editor.putInt("compRunSt", var5);
            this.editor.apply();
            break;
         case "startingTemperatureSettingForColdMachineDefrosting":
            var6 = OptionAirCmd469.getInstance();
            var4 = (float)var2;
            var6.defrstStartTempSet = var4;
            this.editor.putFloat("value3", var4);
            this.editor.apply();
            OptionAirCmd469.getInstance().controlOptionCmd();
            break;
         case "temperatureControlReturnDifferenceSetting":
            var6 = OptionAirCmd469.getInstance();
            var4 = (float)var2;
            var6.tempCtrlBckPoorSet = var4;
            this.editor.putFloat("value5", var4);
            this.editor.apply();
            OptionAirCmd469.getInstance().controlOptionCmd();
      }

      this.setCmd(OptionAirCmd469.getInstance().airCmd);
   }

   private void sendAirControlCmd(int[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         this.canBusInfoByte[var2] = (byte)var1[var2];
      }

      CanbusMsgSender.sendMsg(this.canBusInfoByte);
   }

   private void sendCalibrationControlCmd(int[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         this.calibrationInfoByte[var2] = (byte)var1[var2];
      }

      CanbusMsgSender.sendMsg(this.calibrationInfoByte);
   }

   // $FF: synthetic method
   void lambda$registerCanBusAirControlListener$0$com_hzbhd_canbus_car__469_UiMgr(String var1) {
      if (var1 != null && !var1.equals("[]")) {
         JSONException var10000;
         label42: {
            boolean var10001;
            JSONObject var3;
            try {
               var3 = new JSONObject(var1);
            } catch (JSONException var7) {
               var10000 = var7;
               var10001 = false;
               break label42;
            }

            int var2 = 0;

            while(true) {
               try {
                  if (var2 >= var3.length()) {
                     break;
                  }

                  int[] var8 = this.canBusInfo;
                  StringBuilder var4 = new StringBuilder();
                  var8[var2] = (Integer)var3.get(var4.append("Data").append(var2).toString());
               } catch (JSONException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label42;
               }

               ++var2;
            }

            try {
               this.sendAirControlCmd(this.canBusInfo);
               return;
            } catch (JSONException var5) {
               var10000 = var5;
               var10001 = false;
            }
         }

         JSONException var9 = var10000;
         var9.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$registerCanBusBasicControlListener$1$com_hzbhd_canbus_car__469_UiMgr(String var1) {
      if (var1 != null && !var1.equals("[]")) {
         JSONException var10000;
         label64: {
            int var4;
            JSONObject var5;
            boolean var10001;
            try {
               var5 = new JSONObject(var1);
               var4 = (Integer)var5.get("Data0");
            } catch (JSONException var11) {
               var10000 = var11;
               var10001 = false;
               break label64;
            }

            int var2 = 0;
            byte var3 = 0;
            int[] var6;
            StringBuilder var12;
            if (var4 == 34) {
               label65: {
                  var2 = var3;

                  while(true) {
                     try {
                        if (var2 >= var5.length()) {
                           break;
                        }

                        var6 = this.calibrationInfoInt;
                        var12 = new StringBuilder();
                        var6[var2] = (Integer)var5.get(var12.append("Data").append(var2).toString());
                     } catch (JSONException var8) {
                        var10000 = var8;
                        var10001 = false;
                        break label65;
                     }

                     ++var2;
                  }

                  try {
                     this.sendCalibrationControlCmd(this.calibrationInfoInt);
                     return;
                  } catch (JSONException var7) {
                     var10000 = var7;
                     var10001 = false;
                  }
               }
            } else {
               label66: {
                  while(true) {
                     try {
                        if (var2 >= var5.length()) {
                           break;
                        }

                        var6 = this.canBusInfo;
                        var12 = new StringBuilder();
                        var6[var2] = (Integer)var5.get(var12.append("Data").append(var2).toString());
                     } catch (JSONException var10) {
                        var10000 = var10;
                        var10001 = false;
                        break label66;
                     }

                     ++var2;
                  }

                  try {
                     this.sendAirControlCmd(this.canBusInfo);
                     return;
                  } catch (JSONException var9) {
                     var10000 = var9;
                     var10001 = false;
                  }
               }
            }
         }

         JSONException var13 = var10000;
         var13.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$registerCanBusBasicControlListener$2$com_hzbhd_canbus_car__469_UiMgr(String var1) {
      if (var1 != null) {
         try {
            JSONObject var2 = new JSONObject(var1);
            this.checkData(var2.getString("TAG"), var2.getDouble("VALUE"));
         } catch (JSONException var3) {
            var3.printStackTrace();
         }

      }
   }

   public void registerCanBusAirControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.air.control.json.string", new UiMgr$$ExternalSyntheticLambda0(this));
   }

   public void registerCanBusBasicControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.basic.control.json.string", new UiMgr$$ExternalSyntheticLambda1(this));
      ShareDataManager.getInstance().registerShareStringListener("can.request.data.share", new UiMgr$$ExternalSyntheticLambda2(this));
   }

   public void setCmd(byte[] var1) {
      String var2 = Base64.encodeToString(var1, 0);
      if (LogUtil.log5()) {
         LogUtil.d("<set_last_cmd>: " + Arrays.toString(Base64.decode(var2, 0)));
      }

      this.editor.putString("last_cmd", var2);
      this.editor.apply();
   }
}
