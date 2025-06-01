package com.hzbhd.canbus.car._187;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static final String SHARE_AMPLIFIER_ASL = "share_amplifier_asl";
   private static final String SHARE_AMPLIFIER_BALANCE = "share_amplifier_balance";
   private static final String SHARE_AMPLIFIER_BASS = "share_amplifier_bass";
   private static final String SHARE_AMPLIFIER_BOSE = "share_amplifier_bose";
   private static final String SHARE_AMPLIFIER_FADE = "share_amplifier_fade";
   private static final String SHARE_AMPLIFIER_FIELD = "share_amplifier_field";
   private static final String SHARE_AMPLIFIER_SURROUND = "share_amplifier_surroubd";
   private static final String SHARE_AMPLIFIER_TREBLE = "share_amplifier_treble";
   private static final String SHARE_AMPLIFIER_VOLUME = "share_amplifier_volume";
   private static final int _187_AMPLIFIER_BAND_OFFSET = 5;
   private static final int _187_AMPLIFIER_VOLUME_OFFSET = 20;
   int mAmpAslValueNow;
   int mAmpBoseValueNow;
   int mAmpFieldValueNow;
   int mAmpSurroundValueNow;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private Timer mTimer;
   private TimerTask mTimerTask;

   private void changeAmplifierSettings() {
      Log.i("ljq", "changeAmplifierSettings: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(0, 0, this.mAmpAslValueNow)).setProgress(this.mAmpAslValueNow));
      var1.add(new SettingUpdateEntity(0, 1, this.mAmpBoseValueNow));
      var1.add((new SettingUpdateEntity(0, 2, this.mAmpSurroundValueNow)).setProgress(this.mAmpSurroundValueNow + 5));
      var1.add(new SettingUpdateEntity(0, 3, this.mAmpFieldValueNow));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void finishTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 == null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 == null) {
         var2.cancel();
         this.mTimer = null;
      }

   }

   private void initAmplifierCmd() {
      Log.i("ljq", "initAmplifierCmd: ");
      byte[] var4 = new byte[]{22, -125, 33, (byte)(GeneralAmplifierData.volume - 20)};
      byte[] var7 = new byte[]{22, -125, 34, (byte)(GeneralAmplifierData.bandBass - 5)};
      byte[] var9 = new byte[]{22, -125, 35, (byte)(GeneralAmplifierData.bandTreble - 5)};
      byte[] var8 = new byte[]{22, -125, 36, (byte)GeneralAmplifierData.leftRight};
      byte[] var5 = new byte[]{22, -125, 36, (byte)GeneralAmplifierData.leftRight};
      byte var1 = (byte)GeneralAmplifierData.frontRear;
      byte[] var10 = new byte[]{22, -125, 38, (byte)this.mAmpAslValueNow};
      byte var2 = (byte)this.mAmpBoseValueNow;
      byte var3 = (byte)this.mAmpSurroundValueNow;
      byte[] var6 = new byte[]{22, -125, 41, (byte)this.mAmpFieldValueNow};
      this.mTimerTask = new TimerTask(this, new byte[][]{var4, var7, var9, var8, var5, {22, -125, 37, var1}, var10, {22, -125, 39, var2}, {22, -125, 40, var3}, var6}) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$ampCmdArrays;

         {
            this.this$0 = var1;
            this.val$ampCmdArrays = var2;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$ampCmdArrays;
            if (var1 < var2.length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.this$0.finishTimer();
            }

         }
      };
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_amplifier_volume", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_amplifier_bass", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_amplifier_treble", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_amplifier_balance", 0);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_amplifier_fade", 0);
         this.mAmpAslValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_asl", 0);
         this.mAmpBoseValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_bose", 0);
         this.mAmpSurroundValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_surroubd", 0);
         this.mAmpFieldValueNow = SharePreUtil.getIntValue(this.mContext, "share_amplifier_field", 0);
      }

      Log.i("ljq", "initAmplifierData: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
   }

   private void realKeyClick1(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick1(var3, var1, var2[2], var2[3]);
   }

   private void realKeyControl0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        if (var1 != 96) {
                           if (var1 != 98) {
                              if (var1 != 100) {
                                 if (var1 != 102) {
                                    if (var1 != 135) {
                                       if (var1 != 9) {
                                          if (var1 != 10) {
                                             if (var1 != 21) {
                                                if (var1 != 22) {
                                                   switch (var1) {
                                                      case 32:
                                                         this.realKeyClick1(183);
                                                         break;
                                                      case 33:
                                                         this.realKeyClick1(183);
                                                         break;
                                                      case 34:
                                                         this.realKeyClick1(128);
                                                         break;
                                                      case 35:
                                                         this.realKeyClick1(128);
                                                         break;
                                                      case 36:
                                                         this.realKeyClick1(76);
                                                         break;
                                                      case 37:
                                                         this.realKeyClick1(130);
                                                         break;
                                                      case 38:
                                                         this.realKeyClick1(77);
                                                         break;
                                                      case 39:
                                                         this.realKeyClick1(33);
                                                         break;
                                                      case 40:
                                                         this.realKeyClick1(34);
                                                         break;
                                                      case 41:
                                                         this.realKeyClick1(35);
                                                         break;
                                                      case 42:
                                                         this.realKeyClick1(36);
                                                         break;
                                                      case 43:
                                                         this.realKeyClick1(37);
                                                         break;
                                                      case 44:
                                                         this.realKeyClick1(38);
                                                         break;
                                                      case 45:
                                                         this.sendKnob_1(7);
                                                         break;
                                                      case 46:
                                                         this.sendKnob_1(8);
                                                         break;
                                                      case 47:
                                                         this.sendKnob_2(47);
                                                         break;
                                                      case 48:
                                                         this.sendKnob_2(48);
                                                         break;
                                                      case 49:
                                                         this.realKeyClick1(45);
                                                         break;
                                                      case 50:
                                                         this.realKeyClick1(46);
                                                         break;
                                                      case 51:
                                                         this.realKeyClick1(17);
                                                         break;
                                                      default:
                                                         switch (var1) {
                                                            case 112:
                                                               this.realKeyClick1(49);
                                                               break;
                                                            case 113:
                                                               this.realKeyClick1(50);
                                                               break;
                                                            case 114:
                                                               this.realKeyClick1(128);
                                                               break;
                                                            case 115:
                                                               this.realKeyClick1(52);
                                                               break;
                                                            case 116:
                                                               this.sendKnob_1(7);
                                                               break;
                                                            case 117:
                                                               this.sendKnob_1(8);
                                                         }
                                                   }
                                                } else {
                                                   this.realKeyClick1(49);
                                                }
                                             } else {
                                                this.realKeyClick1(50);
                                             }
                                          } else {
                                             this.realKeyClick1(15);
                                          }
                                       } else {
                                          this.realKeyClick1(14);
                                       }
                                    } else {
                                       this.realKeyClick1(134);
                                    }
                                 } else {
                                    this.realKeyClick1(47);
                                 }
                              } else {
                                 this.realKeyClick1(46);
                              }
                           } else {
                              this.realKeyClick1(48);
                           }
                        } else {
                           this.realKeyClick1(45);
                        }
                     } else {
                        this.realKeyClick1(2);
                     }
                  } else {
                     this.realKeyClick1(46);
                  }
               } else {
                  this.realKeyClick1(45);
               }
            } else {
               this.realKeyClick1(8);
            }
         } else {
            this.realKeyClick1(7);
         }
      } else {
         this.realKeyClick1(0);
      }

   }

   private void saveAmplifierData() {
      Log.i("ljq", "saveAmplifierData: ");
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_volume", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_asl", this.mAmpAslValueNow);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_bose", this.mAmpBoseValueNow);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_surroubd", this.mAmpSurroundValueNow);
      SharePreUtil.setIntValue(this.mContext, "share_amplifier_field", this.mAmpFieldValueNow);
   }

   private void sendKnob_1(int var1) {
      this.realKeyClick3_1(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
   }

   private void sendKnob_2(int var1) {
      this.realKeyClick3_2(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
   }

   private void setAmplifierInfo0x93() {
      Log.i("ljq", "setAmplifierInfo0x93: ");
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.bandBass = this.mCanBusInfoByte[3] + 5;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[4] + 5;
      GeneralAmplifierData.leftRight = this.mCanBusInfoByte[5];
      GeneralAmplifierData.frontRear = this.mCanBusInfoByte[6];
      this.updateAmplifierActivity((Bundle)null);
      this.mAmpAslValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[7], 0, 5);
      this.mAmpBoseValueNow = this.mCanBusInfoInt[8];
      this.mAmpSurroundValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[9], -5, 5);
      this.mAmpFieldValueNow = this.mCanBusInfoInt[10];
      this.changeAmplifierSettings();
      this.saveAmplifierData();
   }

   private void setCarSpeed0x6A() {
      int[] var1 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[3], var1[2]));
   }

   private void setFrontRadar0x23() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[6], var1[7], var1[8], var1[9]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadar0x22() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1[3], var1[2], 0, 5448, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void startTimer(int var1, int var2) {
      Log.i("ljq", "startTimer: ");
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, (long)var1, (long)var2);
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 41) {
            if (var3 != 48) {
               if (var3 != 106) {
                  if (var3 != 147) {
                     if (var3 != 34) {
                        if (var3 == 35) {
                           this.setFrontRadar0x23();
                        }
                     } else {
                        this.setRearRadar0x22();
                     }
                  } else {
                     this.setAmplifierInfo0x93();
                  }
               } else {
                  this.setCarSpeed0x6A();
               }
            } else {
               this.setVersionInfo0x30();
            }
         } else {
            this.setTrackData0x29();
         }
      } else {
         this.realKeyControl0x20();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(var1);
      this.initAmplifierCmd();
      this.startTimer(0, 100);
      this.changeAmplifierSettings();
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.initAmplifierData(this.mContext);
      this.initAmplifierCmd();
   }
}
