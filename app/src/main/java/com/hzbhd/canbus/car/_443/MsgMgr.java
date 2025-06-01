package com.hzbhd.canbus.car._443;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._443.info.SystemInfo;
import com.hzbhd.canbus.car._443.speech.ISysRxListener;
import com.hzbhd.canbus.car._443.speech.SRxData;
import com.hzbhd.canbus.car._443.speech.STxData;
import com.hzbhd.canbus.car._443.speech.SysRxSpeechReceiver;
import com.hzbhd.canbus.car._443.speech.SysToSpeechReceiver;
import com.hzbhd.canbus.car._443.util.CycleRequest;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.SourceManager;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static SysToSpeechReceiver.AcCtrl sa;
   private static SysToSpeechReceiver.CarCtrl sc;
   private static SysToSpeechReceiver sysToSpeechReceiver;
   private final String PATH_VOICE = "voice/";
   private String airJsonStr;
   private SparseArray beepVoice;
   DecimalFormat df_2Integer = new DecimalFormat("00");
   private boolean handsTag = false;
   int[] mAirData;
   int[] mCanBusInfoInt;
   Context mContext;
   int[] mLightData;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int nowRadarData11 = 0;
   private int nowRadarData12 = 0;
   private SparseArray speechVoice;
   private VoiceManger voiceManger;

   private void ShareBasicInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportMsBasicInfo(this.intArrayToJsonStr(var1));
   }

   private void SharedAirData(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportMsAirInfo(this.intArrayToJsonStr(var1));
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void cycleNotifyVersionName(Context var1) {
      SystemInfo var8 = new SystemInfo();
      int var6 = var8.getYear();
      int var7 = var8.getDayOfYear();
      if (2023 == var6) {
         if (var7 < 60) {
            return;
         }

         var6 = var7 - 60;
      } else {
         if (var6 <= 2023) {
            return;
         }

         var6 = var7 + 305 + (var6 - 2024) * 365;
      }

      var7 = var6 / 180;
      byte var4 = (byte)var8.get_MMI_SWYear();
      byte var2 = (byte)var8.get_MMI_Swweek();
      byte var3 = (byte)(var7 + 1);
      byte var5 = (byte)(var6 % 180);
      CycleRequest.getInstance().start(0, new ActionCallback(this, new byte[]{22, 24, -1, -94, 40, -22, 34, var4, var2, var3, var5, 0, 0, 1}) {
         final MsgMgr this$0;
         final byte[] val$versionCmd;

         {
            this.this$0 = var1;
            this.val$versionCmd = var2;
         }

         public void toDo(Object var1) {
            CanbusMsgSender.sendMsg(this.val$versionCmd);
            CycleRequest.getInstance().reset(1000);
         }
      });
   }

   private String getMeaningStr(int[] var1) {
      int var2;
      int var3;
      String var5;
      label103: {
         var2 = var1[2];
         if (var2 == 5 && var1[3] == 113 && var1[4] == 3 && var1[5] == 67 && var1[6] == 241) {
            var3 = var1[7];
            if (var3 == 0) {
               var5 = "标中位：标定待定";
               break label103;
            }

            if (var3 == 1) {
               var5 = "标中位：标定完成";
               break label103;
            }

            if (var3 == 2) {
               var5 = "标中位：标定失败_数据检查失败";
               break label103;
            }

            if (var3 == 3) {
               var5 = "标中位：标定失败_零角检查失败";
               break label103;
            }

            if (var3 == 4) {
               var5 = "标中位：读取EEPROM错误";
               break label103;
            }
         }

         var5 = "";
      }

      String var4 = var5;
      if (var2 == 3) {
         var4 = var5;
         if (var1[3] == 127) {
            var4 = var5;
            if (var1[4] == 49) {
               var3 = var1[5];
               if (var3 != 18) {
                  if (var3 != 19) {
                     if (var3 != 34) {
                        if (var3 != 49) {
                           if (var3 != 51) {
                              if (var3 != 127) {
                                 if (var3 != 136) {
                                    if (var3 != 146) {
                                       if (var3 != 147) {
                                          var4 = var5;
                                       } else {
                                          var4 = "电压低于 18V";
                                       }
                                    } else {
                                       var4 = "电压高于 32V";
                                    }
                                 } else {
                                    var4 = "车速大于 0";
                                 }
                              } else {
                                 var4 = "在当前诊断模式下，服务器不支持客户端请求的 SID";
                              }
                           } else {
                              var4 = "服务器的安全状态处于锁定状态";
                           }
                        } else {
                           var4 = "服务器没有客户端请求的数据";
                        }
                     } else {
                        var4 = "条件不满足";
                     }
                  } else {
                     var4 = "服务器认为客户端请求报文的数据（或格式）不符合本标准";
                  }
               } else {
                  var4 = "服务器不支持客户端请求的诊断服务";
               }
            }
         }
      }

      var5 = var4;
      if (var2 == 5) {
         var5 = var4;
         if (var1[3] == 113) {
            var5 = var4;
            if (var1[4] == 3) {
               var5 = var4;
               if (var1[5] == 67) {
                  var5 = var4;
                  if (var1[6] == 240) {
                     var2 = var1[7];
                     if (var2 != 0) {
                        if (var2 != 1) {
                           if (var2 != 2) {
                              if (var2 != 3) {
                                 if (var2 != 4) {
                                    var5 = var4;
                                 } else {
                                    var5 = "清中位：读取EEPROM错误";
                                 }
                              } else {
                                 var5 = "清中位：标定失败_零角检查失败";
                              }
                           } else {
                              var5 = "清中位：标定失败_数据检查失败";
                           }
                        } else {
                           var5 = "清中位：标定完成";
                        }
                     } else {
                        var5 = "清中位：标定待定";
                     }
                  }
               }
            }
         }
      }

      return var5.equals("") ? var5 : "(" + var5 + ")";
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initRadarVoice() {
      this.voiceManger = new VoiceManger();
      SparseArray var1 = new SparseArray();
      this.speechVoice = var1;
      var1.put(1, "_434_speech_0x01_stop.mp3");
      this.speechVoice.put(2, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      this.speechVoice.put(3, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      this.speechVoice.put(4, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      this.speechVoice.put(5, "_434_speech_0x05_3second.mp3");
      this.speechVoice.put(6, "_434_speech_0x06_1second.mp3");
      this.speechVoice.put(7, "_434_speech_0x02_0x03_0x04_0x07.mp3");
      var1 = new SparseArray();
      this.beepVoice = var1;
      var1.put(1, "_434_beep_0x01_long.mp3");
      this.beepVoice.put(2, "_434_beep_0x02_6hz.mp3");
      this.beepVoice.put(3, "_434_beep_0x03_4hz.mp3");
      this.beepVoice.put(4, "_434_beep_0x04_2hz.mp3");
      this.beepVoice.put(5, "_434_speech_0x05_3second.mp3");
      this.beepVoice.put(6, "_434_speech_0x06_1second.mp3");
      this.beepVoice.put(7, "_434_beep_0x07_6beep.mp3");
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

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return true;
      }
   }

   private boolean isLightDataChange() {
      if (Arrays.equals(this.mLightData, this.mCanBusInfoInt)) {
         return false;
      } else {
         this.mLightData = this.mCanBusInfoInt;
         return true;
      }
   }

   private boolean isTrackInfoChange(byte[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return true;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void registerSanYiVoiceReceiver(Context var1) {
      SanYiVoiceControl var2 = new SanYiVoiceControl(this);
      SysRxSpeechReceiver var4 = new SysRxSpeechReceiver(this.mContext, var2);
      IntentFilter var3 = new IntentFilter();
      var3.addAction("com.hzbhd.speech.to.sys");
      var1.registerReceiver(var4, var3);
   }

   private void requestVoiceSource() {
      SourceManager.getInstance().notifyAppAudio(SourceConstantsDef.SOURCE_ID.VOICE, "com.hzbhd.canbus.car._434.impl", 3, true);
   }

   private void sendAirInfoToSpeech(byte[] var1) {
      sa.sendTemp(var1[8] / 2);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[9], 3, 3);
      if (var2 == 0) {
         sa.sendMode(SRxData.Ac.Mode.ModeEnum.face);
      } else if (var2 == 1) {
         sa.sendMode(SRxData.Ac.Mode.ModeEnum.facefoot);
      } else if (var2 == 2) {
         sa.sendMode(SRxData.Ac.Mode.ModeEnum.foot);
      } else if (var2 == 3) {
         sa.sendMode(SRxData.Ac.Mode.ModeEnum.footdefrost);
      }

      if (var2 == 4) {
         sa.sendDefrost(true);
      } else {
         sa.sendDefrost(false);
      }

      sa.sendWind(DataHandleUtils.getIntFromByteWithBit(var1[7], 5, 3) + 1);
      var2 = DataHandleUtils.getIntFromByteWithBit(var1[7], 1, 2);
      if (var2 != 1 && var2 != 3) {
         sa.sendCold(false);
      } else {
         sa.sendCold(true);
      }

      if (var2 != 2 && var2 != 3) {
         sa.sendWarm(false);
      } else {
         sa.sendWarm(true);
      }

      if (DataHandleUtils.getBoolBit0(var1[7])) {
         sa.sendAir(true);
      } else {
         sa.sendAir(false);
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 1) == 1) {
         sa.sendLoop(true);
      } else {
         sa.sendLoop(false);
      }

   }

   private void sendLightInfoToSheech(int[] var1) {
      int var2 = this.getMsDataType(this.mCanBusInfoInt);
      if (var2 != 419315745) {
         if (var2 != 419316001) {
            if (var2 == 419367713) {
               if (DataHandleUtils.getBoolBit2(var1[10])) {
                  sc.sendWiper(true);
               } else {
                  sc.sendWiper(true);
               }
            }
         } else {
            if (DataHandleUtils.getBoolBit4(var1[7])) {
               sc.sendHeadlight(true);
            } else {
               sc.sendHeadlight(false);
            }

            if (DataHandleUtils.getBoolBit6(var1[7])) {
               sc.sendHighbeam(true);
            } else {
               sc.sendHighbeam(false);
            }
         }
      } else if (DataHandleUtils.getBoolBit2(var1[7])) {
         sc.sendAlarmLight(true);
      } else {
         sc.sendAlarmLight(false);
      }

   }

   private void setRadarVoice(Context var1, int var2) {
      synchronized(this){}
      if (var2 != 0) {
         try {
            if (!TextUtils.isEmpty((CharSequence)this.speechVoice.get(var2))) {
               this.requestVoiceSource();
               this.voiceManger.setVolume(Integer.parseInt(android.provider.Settings.System.getString(this.mContext.getContentResolver(), "SHARE_MS_RADAR_VOLUME")));
               StringBuilder var3;
               VoiceManger var4;
               if (android.provider.Settings.System.getString(this.mContext.getContentResolver(), "SHARE_MS_BEEP_SPEECH").equals("BEEP")) {
                  var4 = this.voiceManger;
                  var3 = new StringBuilder();
                  var4.play(var1, var3.append("voice/").append((String)this.beepVoice.get(var2)).toString());
               } else {
                  var4 = this.voiceManger;
                  var3 = new StringBuilder();
                  var4.play(var1, var3.append("voice/").append((String)this.speechVoice.get(var2)).toString());
               }

               return;
            }
         } finally {
            ;
         }
      }

   }

   private void setRadarVoiceData(Context var1) {
      int var3 = this.nowRadarData11;
      int var2 = this.mCanBusInfoInt[13];
      if (var3 != var2) {
         this.nowRadarData11 = var2;
         this.setRadarVoice(var1, DataHandleUtils.getIntFromByteWithBit(var2, 5, 3));
      }

      var2 = this.nowRadarData12;
      var3 = this.mCanBusInfoInt[14];
      if (var2 != var3) {
         this.nowRadarData12 = var3;
         this.setRadarVoice(var1, DataHandleUtils.getIntFromByteWithBit(var3, 0, 3));
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.getUiMgr(var1).registerCanBusAirControlListener();
      this.getUiMgr(var1).registerCanBusBasicControlListener();
      this.initRadarVoice();
      this.registerSanYiVoiceReceiver(var1);
      SysToSpeechReceiver var2 = new SysToSpeechReceiver(var1);
      sysToSpeechReceiver = var2;
      sa = var2.getAc();
      sc = sysToSpeechReceiver.getCar();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mContext = var1;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      if (LogUtil.log5()) {
         LogUtil.d("canbusInfoChange(): " + this.getMsDataType(this.mCanBusInfoInt));
      }

      if (this.getMsDataType(this.mCanBusInfoInt) == 419393561) {
         this.SharedAirData(this.mCanBusInfoInt);
         if (this.handsTag && this.isAirDataChange()) {
            this.sendAirInfoToSpeech(var2);
         }
      } else if (this.getMsDataType(this.mCanBusInfoInt) == 418322406) {
         this.showRadarView();
         this.setRadarVoiceData(var1);
         this.ShareBasicInfo(this.mCanBusInfoInt);
      } else {
         int var3;
         if (this.getMsDataType(this.mCanBusInfoInt) == 218053139) {
            if (this.isTrackInfoChange(var2)) {
               return;
            }

            int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
            var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
            DataHandleUtils.getMsbLsbResult_4bit(var3, var4);
            int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4);
            int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 4);
            DataHandleUtils.getMsbLsbResult_4bit(var5, var6);
            var3 = (var4 & 255 | (var3 & 255) << 4 | (var6 & 255) << 8 | (var5 & 255) << 12) - 1040;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)DataHandleUtils.getLsb(var3), (byte)DataHandleUtils.getMsb(var3), 0, 930, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         } else if (this.getMsDataType(this.mCanBusInfoInt) == 218052363) {
            if (this.isTrackInfoChange(var2)) {
               return;
            }

            int[] var7;
            if (LogUtil.log5()) {
               StringBuilder var8 = (new StringBuilder()).append("canbusInfoChange");
               var7 = this.mCanBusInfoInt;
               LogUtil.d(var8.append((float)DataHandleUtils.getMsbLsbResult(var7[8], var7[7]) * 0.004032258F).toString());
            }

            var7 = this.mCanBusInfoInt;
            var3 = (int)((double)((float)DataHandleUtils.getMsbLsbResult(var7[8], var7[7]) * 0.004032258F) - 129.374);
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)DataHandleUtils.getLsb(var3), (byte)DataHandleUtils.getMsb(var3), 0, 31, 16);
            this.updateParkUi((Bundle)null, this.mContext);
            if (LogUtil.log5()) {
               LogUtil.d("canbusInfoChange-------(): " + GeneralParkData.trackAngle);
            }
         } else {
            if (this.getMsDataType(this.mCanBusInfoInt) == 218050571 && DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
               this.updateSpeedInfo((int)((double)this.mCanBusInfoInt[8] * 0.05625));
               if (LogUtil.log5()) {
                  LogUtil.d("updateSpeedInfo " + this.getMsDataType(this.mCanBusInfoInt));
               }
            }

            if (this.getMsDataType(this.mCanBusInfoInt) == 419361843) {
               CanbusInfoChangeListener.getInstance().reportAllCanBusData("havaTirePressure");
               if (LogUtil.log5()) {
                  LogUtil.d("havaTirePressure ");
               }
            }

            this.ShareBasicInfo(this.mCanBusInfoInt);
            if (this.isLightDataChange()) {
               this.sendLightInfoToSheech(this.mCanBusInfoInt);
            }
         }
      }

   }

   protected int getMsDataType(int[] var1) {
      int var4 = var1[2];
      int var3 = var1[3];
      int var2 = var1[4];
      return var1[5] & 255 | (var4 & 255) << 24 | (var3 & 255) << 16 | (var2 & 255) << 8;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.updateVersionInfo(var1, "CANBOX-V1.0.0");
      android.provider.Settings.System.putInt(var1.getContentResolver(), "share.out.of.misc.turn.left.or.right", 0);
      this.cycleNotifyVersionName(var1);
   }

   public void medianCalibration(Context var1, byte[] var2) {
      super.medianCalibration(var1, var2);
      String var4 = this.getMeaningStr(this.getByteArrayToIntArray(var2));
      String var6 = "";

      for(int var3 = 2; var3 < var2.length; ++var3) {
         String var5 = Integer.toHexString(var2[var3] & 255 | -256).substring(6);
         if (var5.length() == 1) {
            var6 = var6 + "    0" + var5;
         } else {
            var6 = var6 + "    " + var5;
         }
      }

      Log.d("medianCalibration", var6);
      CanbusInfoChangeListener.getInstance().reportMsBasicInfo("medianCalibration:" + var6 + "  " + var4);
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
   }

   public void onHandshake(Context var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
   }

   public void showRadarView() {
      try {
         if (android.provider.Settings.System.getString(this.mContext.getContentResolver(), "SHARE_MS_RADAR_SETTING_SHOW_RADAR_SWITCH") != null && android.provider.Settings.System.getString(this.mContext.getContentResolver(), "SHARE_MS_RADAR_SETTING_SHOW_RADAR_SWITCH").equals("ON")) {
            ComponentName var1 = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.mContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity;
            if (!var1.getClassName().trim().equals("com.hzbhd.ui.config.ui_l9.activity.MsRadarActivity") && !var1.getClassName().equals("com.hzbhd.ui.activity.VideoActivity")) {
               this.startOtherActivity(this.mContext, HzbhdComponentName.MsRadarActivity);
            }
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private class SanYiVoiceControl implements ISysRxListener {
      final MsgMgr this$0;

      private SanYiVoiceControl(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      SanYiVoiceControl(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void AcAir(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 8, 0, 0, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 4, 0, 0, 0, 0, 0, 1});
         }

      }

      public void AcAuto(boolean var1) {
      }

      public void AcCold(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 16, 0, 0, 0, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 8, 0, 0, 0, 0, 0, 0, 1});
         }

      }

      public void AcDefrost(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, -128, 2, 0, 0, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 3, 0, 0, 0, 0, 0, 1});
         }

      }

      public void AcLoop(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, -128, 0, 0, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 64, 0, 0, 0, 0, 0, 1});
         }

      }

      public void AcMode(STxData.AcCtrl.Mode.ModeEnum var1) {
         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.face)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, -128, 0, 0, 0, 0, 0, 0, 1});
         }

         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.facefoot)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 1, 0, 0, 0, 0, 0, 1});
         }

         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.foot)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, -128, 1, 0, 0, 0, 0, 0, 1});
         }

         if (var1.equals(STxData.AcCtrl.Mode.ModeEnum.footdefrost)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 2, 0, 0, 0, 0, 0, 1});
         }

      }

      public void AcTempDec(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 2, 0, 0, 0, 0, 0, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
      }

      public void AcTempInc(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 4, 0, 0, 0, 0, 0, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
      }

      public void AcTempTo(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, (byte)(var1 * 2), 0, 0, 0, 1});
      }

      public void AcWarm(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 64, 0, 0, 0, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 32, 0, 0, 0, 0, 0, 0, 1});
         }

      }

      public void AcWind(boolean var1) {
         if (var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 1, 0, 0, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, -16, 0, 0, 0, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
         }

      }

      public void AcWindTo(int var1) {
         int var2 = var1;
         if (var1 > 6) {
            var2 = 6;
         }

         var1 = var2;
         if (var2 < 1) {
            var1 = 1;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, (byte)var1, 0, 0, 0, 0, 1});
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
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, 127, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, 127, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, 127, -1, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
         }

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
         if (var1.equals(STxData.CarCtrl.WindowOpen.TypeEnum.leftfront)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
         }

         if (var1.equals(STxData.CarCtrl.WindowOpen.TypeEnum.rightfront)) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarWiper(boolean var1) {
         if (!var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
         }

      }

      public void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum var1) {
      }

      public void CarWiperMove(int var1) {
      }

      public void SystemSync() {
         this.this$0.handsTag = true;
      }
   }
}
