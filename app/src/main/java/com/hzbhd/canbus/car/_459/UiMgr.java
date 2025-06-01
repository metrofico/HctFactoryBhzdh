package com.hzbhd.canbus.car._459;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._459.air.AirDataBuffer;
import com.hzbhd.canbus.car._459.air.OptionAirCmd459;
import com.hzbhd.canbus.car._459.mp5_state.Mp5StateCmdOption;
import com.hzbhd.canbus.car._459.settings.OptionSettingsCmd459;
import com.hzbhd.canbus.car._459.settings.SettingsDataBuffer;
import com.hzbhd.canbus.car._459.tbox_wifi.WifiManager;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class UiMgr extends AbstractUiMgr {
   public byte[] airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
   private Context mContext;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
   }

   private void checkData(String var1, String var2) {
      int var3;
      byte var4;
      byte var5;
      byte var6;
      byte var7;
      byte var8;
      byte var9;
      byte var10;
      byte var12;
      label245: {
         var1.hashCode();
         var3 = var1.hashCode();
         var4 = -1;
         var10 = 1;
         var7 = 1;
         var9 = 1;
         var6 = 1;
         var5 = 1;
         var8 = 1;
         switch (var3) {
            case -1711148423:
               if (var1.equals("WIND_DOWN")) {
                  var12 = 0;
                  break label245;
               }
               break;
            case -1710880902:
               if (var1.equals("WIND_MODE")) {
                  var12 = 1;
                  break label245;
               }
               break;
            case -1437692969:
               if (var1.equals("HANDSHAKE")) {
                  var12 = 2;
                  break label245;
               }
               break;
            case -710102298:
               if (var1.equals("TEMP_UP")) {
                  var12 = 3;
                  break label245;
               }
               break;
            case -77813387:
               if (var1.equals("WIFI_NAME")) {
                  var12 = 4;
                  break label245;
               }
               break;
            case 2082:
               if (var1.equals("AC")) {
                  var12 = 5;
                  break label245;
               }
               break;
            case 2122:
               if (var1.equals("BL")) {
                  var12 = 6;
                  break label245;
               }
               break;
            case 2164:
               if (var1.equals("CW")) {
                  var12 = 7;
                  break label245;
               }
               break;
            case 2221:
               if (var1.equals("ER")) {
                  var12 = 8;
                  break label245;
               }
               break;
            case 2246:
               if (var1.equals("FL")) {
                  var12 = 9;
                  break label245;
               }
               break;
            case 2424:
               if (var1.equals("LD")) {
                  var12 = 10;
                  break label245;
               }
               break;
            case 2638:
               if (var1.equals("SA")) {
                  var12 = 11;
                  break label245;
               }
               break;
            case 65245:
               if (var1.equals("AWS")) {
                  var12 = 12;
                  break label245;
               }
               break;
            case 67502:
               if (var1.equals("DCM")) {
                  var12 = 13;
                  break label245;
               }
               break;
            case 79551:
               if (var1.equals("PTC")) {
                  var12 = 14;
                  break label245;
               }
               break;
            case 82326:
               if (var1.equals("SPS")) {
                  var12 = 15;
                  break label245;
               }
               break;
            case 84831:
               if (var1.equals("VDM")) {
                  var12 = 16;
                  break label245;
               }
               break;
            case 2020783:
               if (var1.equals("AUTO")) {
                  var12 = 17;
                  break label245;
               }
               break;
            case 64594118:
               if (var1.equals("CYCLE")) {
                  var12 = 18;
                  break label245;
               }
               break;
            case 66996842:
               if (var1.equals("FLWSL")) {
                  var12 = 19;
                  break label245;
               }
               break;
            case 76320997:
               if (var1.equals("POWER")) {
                  var12 = 20;
                  break label245;
               }
               break;
            case 473848965:
               if (var1.equals("WIFI_PASSWORD")) {
                  var12 = 21;
                  break label245;
               }
               break;
            case 490987053:
               if (var1.equals("TEMP_DOWN")) {
                  var12 = 22;
                  break label245;
               }
               break;
            case 1599048391:
               if (var1.equals("FRONT_DEFOG")) {
                  var12 = 23;
                  break label245;
               }
               break;
            case 1837333968:
               if (var1.equals("WIFI_REQUEST_CONNECT")) {
                  var12 = 24;
                  break label245;
               }
               break;
            case 1925676967:
               if (var1.equals("AC_MAX")) {
                  var12 = 25;
                  break label245;
               }
               break;
            case 2067491410:
               if (var1.equals("WIND_Up")) {
                  var12 = 26;
                  break label245;
               }
         }

         var12 = -1;
      }

      byte[] var11;
      switch (var12) {
         case 0:
         case 26:
            AirDataBuffer.getInstance().resetTimer();
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            OptionAirCmd459.getInstance().windLevel = Integer.parseInt(var2);
            var3 = DataHandleUtils.getMsbLsbResult_4bit(OptionAirCmd459.getInstance().windLevel, 0);
            var11 = this.airCmd;
            var11[7] = (byte)var3;
            CanbusMsgSender.sendMsg(var11);
            break;
         case 1:
            AirDataBuffer.getInstance().resetTimer();
            var2.hashCode();
            byte var13;
            switch (var2.hashCode()) {
               case -1734422544:
                  if (!var2.equals("WINDOW")) {
                     var13 = var4;
                  } else {
                     var13 = 0;
                  }
                  break;
               case 2149981:
                  if (!var2.equals("FACE")) {
                     var13 = var4;
                  } else {
                     var13 = 1;
                  }
                  break;
               case 2163822:
                  if (!var2.equals("FOOT")) {
                     var13 = var4;
                  } else {
                     var13 = 2;
                  }
                  break;
               case 709005537:
                  if (!var2.equals("FOOT_WINDOW")) {
                     var13 = var4;
                  } else {
                     var13 = 3;
                  }
                  break;
               case 1044275472:
                  if (!var2.equals("FACE_FOOT")) {
                     var13 = var4;
                  } else {
                     var13 = 4;
                  }
                  break;
               default:
                  var13 = var4;
            }

            switch (var13) {
               case 0:
                  OptionAirCmd459.getInstance().windMode = 3;
                  break;
               case 1:
                  OptionAirCmd459.getInstance().windMode = 2;
                  break;
               case 2:
                  OptionAirCmd459.getInstance().windMode = 1;
                  break;
               case 3:
                  OptionAirCmd459.getInstance().windMode = 5;
                  break;
               case 4:
                  OptionAirCmd459.getInstance().windMode = 4;
            }

            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            var3 = DataHandleUtils.getMsbLsbResult_4bit(14, OptionAirCmd459.getInstance().windMode);
            var11 = this.airCmd;
            var11[7] = (byte)var3;
            CanbusMsgSender.sendMsg(var11);
            break;
         case 2:
            if (var2.equals("SYSTEM_UI")) {
               this.getMsgMgr(this.mContext).systemUiHandshake();
            } else if (var2.equals("LAUNCHER")) {
               this.getMsgMgr(this.mContext).launcherHandshake();
            }
            break;
         case 3:
         case 22:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().temp = Double.parseDouble(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            var3 = (int)(OptionAirCmd459.getInstance().temp * 2.0);
            var11 = this.airCmd;
            var11[6] = (byte)var3;
            CanbusMsgSender.sendMsg(var11);
            break;
         case 4:
            WifiManager.getInstance().wifiName = var2;
            WifiManager.getInstance().requestAction(2);
            break;
         case 5:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().ac = Boolean.parseBoolean(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            if (OptionAirCmd459.getInstance().ac) {
               var12 = var5;
            } else {
               var12 = 2;
            }

            var11 = this.airCmd;
            var11[8] = (byte)(var12 & 255 | 0);
            CanbusMsgSender.sendMsg(var11);
            break;
         case 6:
            OptionSettingsCmd459.getInstance().setBL(Integer.parseInt(var2));
            break;
         case 7:
            SettingsDataBuffer.getInstance().resetTimer();
            if (LogUtil.log5()) {
               LogUtil.d("checkData(): --------------" + var2);
            }

            OptionSettingsCmd459.getInstance().setCW(Boolean.parseBoolean(var2));
            break;
         case 8:
            OptionSettingsCmd459.getInstance().setER(Boolean.parseBoolean(var2));
            break;
         case 9:
            if (LogUtil.log5()) {
               LogUtil.d("checkData(): -------" + var2);
            }

            OptionSettingsCmd459.getInstance().setFL(Boolean.parseBoolean(var2));
            break;
         case 10:
            SettingsDataBuffer.getInstance().resetTimer();
            OptionSettingsCmd459.getInstance().setLD(Boolean.parseBoolean(var2));
            break;
         case 11:
            SettingsDataBuffer.getInstance().resetTimer();
            OptionSettingsCmd459.getInstance().setSA(Boolean.parseBoolean(var2));
            break;
         case 12:
            if (LogUtil.log5()) {
               LogUtil.d("setAutomaticWiperSystem(): ----" + var2);
            }

            SettingsDataBuffer.getInstance().resetTimer();
            if (LogUtil.log5()) {
               LogUtil.d("checkData(): --------------" + var2);
            }

            OptionSettingsCmd459.getInstance().setAWS(Boolean.parseBoolean(var2));
            if (LogUtil.log5()) {
               LogUtil.d("setAutomaticWiperSystem(): ----" + Boolean.parseBoolean(var2));
            }
            break;
         case 13:
            OptionSettingsCmd459.getInstance().setDCM(Boolean.parseBoolean(var2));
            break;
         case 14:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().ptc = Boolean.parseBoolean(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            if (OptionAirCmd459.getInstance().ptc) {
               var12 = var6;
            } else {
               var12 = 2;
            }

            var11 = this.airCmd;
            var11[8] = (byte)((var12 & 255) << 2 | 0 | 0);
            CanbusMsgSender.sendMsg(var11);
            break;
         case 15:
            SettingsDataBuffer.getInstance().resetTimer();
            OptionSettingsCmd459.getInstance().setSPS(Boolean.parseBoolean(var2));
            break;
         case 16:
            SettingsDataBuffer.getInstance().resetTimer();
            OptionSettingsCmd459.getInstance().setVDM(var2);
            break;
         case 17:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().auto = Boolean.parseBoolean(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            var12 = OptionAirCmd459.getInstance().auto;
            var11 = this.airCmd;
            var11[5] = (byte)(var12 & 255 | 0);
            CanbusMsgSender.sendMsg(var11);
            break;
         case 18:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().cycle = Boolean.parseBoolean(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            if (OptionAirCmd459.getInstance().cycle) {
               var12 = var9;
            } else {
               var12 = 2;
            }

            var11 = this.airCmd;
            var11[8] = (byte)((var12 & 255) << 4 | 0 | 0 | 0);
            CanbusMsgSender.sendMsg(var11);
            break;
         case 19:
            if (LogUtil.log5()) {
               LogUtil.d("checkData(): +++++++++" + var2);
            }

            OptionSettingsCmd459.getInstance().setFLWSL(Boolean.parseBoolean(var2));
            break;
         case 20:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().power = Boolean.parseBoolean(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            if (OptionAirCmd459.getInstance().power) {
               var12 = var7;
            } else {
               var12 = 2;
            }

            var11 = this.airCmd;
            var11[5] = (byte)((var12 & 255) << 2 | 0);
            CanbusMsgSender.sendMsg(var11);
            break;
         case 21:
            WifiManager.getInstance().wifiPassword = var2;
            WifiManager.getInstance().requestAction(3);
            break;
         case 23:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().frontDefog = Boolean.parseBoolean(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            if (OptionAirCmd459.getInstance().frontDefog) {
               var12 = var10;
            } else {
               var12 = 2;
            }

            var11 = this.airCmd;
            var11[8] = (byte)((var12 & 255) << 6 | 0 | 0 | 0);
            CanbusMsgSender.sendMsg(var11);
            break;
         case 24:
            if (Boolean.parseBoolean(var2)) {
               WifiManager.getInstance().requestAction(1);
            } else {
               WifiManager.getInstance().requestAction(0);
            }
            break;
         case 25:
            AirDataBuffer.getInstance().resetTimer();
            OptionAirCmd459.getInstance().acMax = Boolean.parseBoolean(var2);
            this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
            if (OptionAirCmd459.getInstance().acMax) {
               var12 = var8;
            } else {
               var12 = 2;
            }

            var11 = this.airCmd;
            var11[9] = (byte)var12;
            CanbusMsgSender.sendMsg(var11);
      }

   }

   private void checkMp5State(String var1, int var2) {
      var1.hashCode();
      int var4 = var1.hashCode();
      byte var3 = -1;
      switch (var4) {
         case -2132038014:
            if (var1.equals("MP5_VoiceCtrlSts")) {
               var3 = 0;
            }
            break;
         case -2110939327:
            if (var1.equals("MP5_RAMSts")) {
               var3 = 1;
            }
            break;
         case -2098010033:
            if (var1.equals("MP5_ROMSts")) {
               var3 = 2;
            }
            break;
         case -2061902380:
            if (var1.equals("MP5_Runsts")) {
               var3 = 3;
            }
            break;
         case -2008756197:
            if (var1.equals("MP5_USBSts")) {
               var3 = 4;
            }
            break;
         case -1852016227:
            if (var1.equals("MP5_DisplaySts")) {
               var3 = 5;
            }
            break;
         case -1226429947:
            if (var1.equals("MP5_VideoStsFront")) {
               var3 = 6;
            }
            break;
         case -1215623688:
            if (var1.equals("MP5_VideoStsRight")) {
               var3 = 7;
            }
            break;
         case -1132865630:
            if (var1.equals("MP5_PhoneConetSts")) {
               var3 = 8;
            }
            break;
         case -1009529461:
            if (var1.equals("MP5_VideoStsBack")) {
               var3 = 9;
            }
            break;
         case -1009227605:
            if (var1.equals("MP5_VideoStsLeft")) {
               var3 = 10;
            }
            break;
         case -830110577:
            if (var1.equals("MP5_ExitReason")) {
               var3 = 11;
            }
            break;
         case -648776819:
            if (var1.equals("MP5_WIFlSts")) {
               var3 = 12;
            }
            break;
         case -625725346:
            if (var1.equals("MP5_StoreSts")) {
               var3 = 13;
            }
            break;
         case -592352665:
            if (var1.equals("MP5_VidoSts")) {
               var3 = 14;
            }
            break;
         case -374552335:
            if (var1.equals("MP5_BluetoothSts")) {
               var3 = 15;
            }
            break;
         case -241850993:
            if (var1.equals("MP5_NavgtSts")) {
               var3 = 16;
            }
            break;
         case 504459020:
            if (var1.equals("MP5_RunOper")) {
               var3 = 17;
            }
            break;
         case 1051309691:
            if (var1.equals("MP5_MediaSts")) {
               var3 = 18;
            }
            break;
         case 1619285917:
            if (var1.equals("MP5_RadioAntennaSts")) {
               var3 = 19;
            }
            break;
         case 1654357874:
            if (var1.equals("MP5_DispWorkSts")) {
               var3 = 20;
            }
            break;
         case 1866730060:
            if (var1.equals("MP5_GPSAntennaSts")) {
               var3 = 21;
            }
            break;
         case 1920796420:
            if (var1.equals("MP5_RadioSts")) {
               var3 = 22;
            }
      }

      switch (var3) {
         case 0:
            Mp5StateCmdOption.getInstance().data2bit6_bit7 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 1:
            Mp5StateCmdOption.getInstance().data6 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 2:
            Mp5StateCmdOption.getInstance().data7 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 3:
            Mp5StateCmdOption.getInstance().data0bit6_bit7 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 4:
            Mp5StateCmdOption.getInstance().data3bit4_bit5 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 5:
            Mp5StateCmdOption.getInstance().data0bit0_bit3 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 6:
            Mp5StateCmdOption.getInstance().data1bit2_bit3 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 7:
            Mp5StateCmdOption.getInstance().data2bit0_bit1 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 8:
            Mp5StateCmdOption.getInstance().data4bit4_bit5 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 9:
            Mp5StateCmdOption.getInstance().data1bit4_bit5 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 10:
            Mp5StateCmdOption.getInstance().data1bit6_bit7 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 11:
            Mp5StateCmdOption.getInstance().data0bit4_bit5 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 12:
            Mp5StateCmdOption.getInstance().data3bit0_bit1 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 13:
            Mp5StateCmdOption.getInstance().data1bit0_bit1 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 14:
            Mp5StateCmdOption.getInstance().data4bit6_bit7 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 15:
            Mp5StateCmdOption.getInstance().data3bit2_bit3 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 16:
            Mp5StateCmdOption.getInstance().data4bit2_bit3 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 17:
            Mp5StateCmdOption.getInstance().data2bit2_bit3 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 18:
            Mp5StateCmdOption.getInstance().data5bit0_bit1 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 19:
            Mp5StateCmdOption.getInstance().data4bit0_bit1 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 20:
            Mp5StateCmdOption.getInstance().data3bit6_bit7 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 21:
            Mp5StateCmdOption.getInstance().data2bit4_bit5 = var2;
            Mp5StateCmdOption.getInstance().send();
            break;
         case 22:
            Mp5StateCmdOption.getInstance().data5bit2_bit3 = var2;
            Mp5StateCmdOption.getInstance().send();
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (LogUtil.log5()) {
         LogUtil.d("getMsgMgrzoubuzou(): --------");
      }

      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   // $FF: synthetic method
   void lambda$registerCanBusAirControlListener$0$com_hzbhd_canbus_car__459_UiMgr(String var1) {
      if (var1 != null) {
         JSONException var10000;
         label29: {
            boolean var10001;
            boolean var2;
            JSONObject var3;
            try {
               var3 = new JSONObject(var1);
               var2 = var3.getString("TAG").equals("MP5_Sts");
            } catch (JSONException var6) {
               var10000 = var6;
               var10001 = false;
               break label29;
            }

            if (var2) {
               try {
                  this.checkMp5State(var3.getString("KEY"), Integer.parseInt(var3.getString("VALUE")));
                  return;
               } catch (JSONException var4) {
                  var10000 = var4;
                  var10001 = false;
               }
            } else {
               try {
                  this.checkData(var3.getString("TAG"), var3.getString("VALUE"));
                  return;
               } catch (JSONException var5) {
                  var10000 = var5;
                  var10001 = false;
               }
            }
         }

         JSONException var7 = var10000;
         var7.printStackTrace();
      }
   }

   public void registerCanBusAirControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("can.request.data.share", new UiMgr$$ExternalSyntheticLambda0(this));
   }
}
