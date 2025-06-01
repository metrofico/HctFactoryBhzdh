package com.hzbhd.canbus.car._444.speech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SysRxSpeechReceiver extends BroadcastReceiver {
   private static final String TAG = "SysRxSpeechReceiver";
   private Context mContext;
   private ISysRxListener mListener;

   public SysRxSpeechReceiver(Context var1, ISysRxListener var2) {
      this.mContext = var1;
      this.mListener = var2;
   }

   public void onReceive(Context var1, Intent var2) {
      Log.d("SysRxSpeechReceiver", "onReceive: ActionX=" + var4);
      if ("com.hzbhd.speech.to.sys".equals(var4)) {
         var4 = var2.getStringExtra("tx_type");
         Log.d("SysRxSpeechReceiver", "onReceive: txType=" + var4);
         if (var4 != null) {
            int var3;
            label273: {
               var4.hashCode();
               switch (var4) {
                  case "highbeam":
                     var3 = 0;
                     break label273;
                  case "tempdec":
                     var3 = 1;
                     break label273;
                  case "tempinc":
                     var3 = 2;
                     break label273;
                  case "toplight":
                     var3 = 3;
                     break label273;
                  case "alarmlight":
                     var3 = 4;
                     break label273;
                  case "system":
                     var3 = 5;
                     break label273;
                  case "tempto":
                     var3 = 6;
                     break label273;
                  case "windto":
                     var3 = 7;
                     break label273;
                  case "readinglamp":
                     var3 = 8;
                     break label273;
                  case "headlight":
                     var3 = 9;
                     break label273;
                  case "air":
                     var3 = 10;
                     break label273;
                  case "auto":
                     var3 = 11;
                     break label273;
                  case "cold":
                     var3 = 12;
                     break label273;
                  case "lock":
                     var3 = 13;
                     break label273;
                  case "loop":
                     var3 = 14;
                     break label273;
                  case "mode":
                     var3 = 15;
                     break label273;
                  case "warm":
                     var3 = 16;
                     break label273;
                  case "wind":
                     var3 = 17;
                     break label273;
                  case "wiper":
                     var3 = 18;
                     break label273;
                  case "clearancelamps":
                     var3 = 19;
                     break label273;
                  case "defrost":
                     var3 = 20;
                     break label273;
                  case "wiperctrl":
                     var3 = 21;
                     break label273;
                  case "wipermove":
                     var3 = 22;
                     break label273;
                  case "windowopen":
                     var3 = 23;
                     break label273;
                  case "windowclose":
                     var3 = 24;
                     break label273;
               }

               var3 = -1;
            }

            String var5;
            switch (var3) {
               case 0:
                  var4 = var2.getStringExtra("action");
                  if ("car.high.beam.open".equals(var4)) {
                     this.mListener.CarHighBeam(true);
                  } else if ("car.high.beam.close".equals(var4)) {
                     this.mListener.CarHighBeam(false);
                  }
                  break;
               case 1:
                  var4 = var2.getStringExtra("action");
                  var3 = var2.getIntExtra("number", -1);
                  if ("ac.temp.dec".equals(var4) && var3 != -1) {
                     this.mListener.AcTempDec(var3);
                  }
                  break;
               case 2:
                  var4 = var2.getStringExtra("action");
                  var3 = var2.getIntExtra("number", -1);
                  if ("ac.temp.inc".equals(var4) && var3 != -1) {
                     this.mListener.AcTempInc(var3);
                  }
                  break;
               case 3:
                  var4 = var2.getStringExtra("action");
                  if ("car.toplight.open".equals(var4)) {
                     this.mListener.CarToplight(true);
                  } else if ("car.toplight.close".equals(var4)) {
                     this.mListener.CarToplight(false);
                  }
                  break;
               case 4:
                  var4 = var2.getStringExtra("action");
                  if ("car.alarm.light.open".equals(var4)) {
                     this.mListener.CarAlarmlight(true);
                  } else if ("car.alarm.light.close".equals(var4)) {
                     this.mListener.CarAlarmlight(false);
                  }
                  break;
               case 5:
                  if ("system.status.sync".equals(var2.getStringExtra("action"))) {
                     this.mListener.SystemSync();
                  }
                  break;
               case 6:
                  var4 = var2.getStringExtra("action");
                  var3 = var2.getIntExtra("number", -1);
                  if ("ac.temp.to".equals(var4) && var3 != -1) {
                     this.mListener.AcTempTo(var3);
                  }
                  break;
               case 7:
                  var4 = var2.getStringExtra("action");
                  var3 = var2.getIntExtra("number", -1);
                  if ("ac.wind.to".equals(var4) && var3 != -1) {
                     this.mListener.AcWindTo(var3);
                  }
                  break;
               case 8:
                  var4 = var2.getStringExtra("action");
                  if ("car.reading.lamp.open".equals(var4)) {
                     this.mListener.CarReadinglamp(true);
                  } else if ("car.indoor.lamp.close".equals(var4)) {
                     this.mListener.CarReadinglamp(false);
                  }
                  break;
               case 9:
                  var4 = var2.getStringExtra("action");
                  if ("car.dipped.headlight.open".equals(var4)) {
                     this.mListener.CarHeadlight(true);
                  } else if ("car.dipped.headlight.close".equals(var4)) {
                     this.mListener.CarHeadlight(false);
                  }
                  break;
               case 10:
                  var4 = var2.getStringExtra("action");
                  if ("ac.air.open".equals(var4)) {
                     this.mListener.AcAir(true);
                  } else if ("ac.air.close".equals(var4)) {
                     this.mListener.AcAir(false);
                  }
                  break;
               case 11:
                  var4 = var2.getStringExtra("action");
                  if ("ac.auto.open".equals(var4)) {
                     this.mListener.AcAuto(true);
                  } else if ("ac.auto.close".equals(var4)) {
                     this.mListener.AcAuto(false);
                  }
                  break;
               case 12:
                  var4 = var2.getStringExtra("action");
                  if ("ac.cold.open".equals(var4)) {
                     this.mListener.AcCold(true);
                  } else if ("ac.cold.close".equals(var4)) {
                     this.mListener.AcCold(false);
                  }
                  break;
               case 13:
                  var4 = var2.getStringExtra("action");
                  if ("car.lock.open".equals(var4)) {
                     this.mListener.CarLock(true);
                  } else if ("car.lock.close".equals(var4)) {
                     this.mListener.CarLock(false);
                  }
                  break;
               case 14:
                  var4 = var2.getStringExtra("action");
                  if ("ac.loop.inside".equals(var4)) {
                     this.mListener.AcLoop(true);
                  } else if ("ac.loop.outside".equals(var4)) {
                     this.mListener.AcLoop(false);
                  }
                  break;
               case 15:
                  var4 = var2.getStringExtra("action");
                  var5 = var2.getStringExtra("mode");
                  if ("ac.mode".equals(var4)) {
                     if ("face".equals(var5)) {
                        this.mListener.AcMode(STxData.AcCtrl.Mode.ModeEnum.face);
                     } else if ("foot".equals(var5)) {
                        this.mListener.AcMode(STxData.AcCtrl.Mode.ModeEnum.foot);
                     } else if ("face.foot".equals(var5)) {
                        this.mListener.AcMode(STxData.AcCtrl.Mode.ModeEnum.facefoot);
                     } else if ("foot.defrost".equals(var5)) {
                        this.mListener.AcMode(STxData.AcCtrl.Mode.ModeEnum.footdefrost);
                     }
                  }
                  break;
               case 16:
                  var4 = var2.getStringExtra("action");
                  if ("ac.warm.open".equals(var4)) {
                     this.mListener.AcWarm(true);
                  } else if ("ac.warm.close".equals(var4)) {
                     this.mListener.AcWarm(false);
                  }
                  break;
               case 17:
                  var4 = var2.getStringExtra("action");
                  if ("ac.wind.up".equals(var4)) {
                     this.mListener.AcWind(true);
                  } else if ("ac.wind.down".equals(var4)) {
                     this.mListener.AcWind(false);
                  }
                  break;
               case 18:
                  var4 = var2.getStringExtra("action");
                  if ("car.wiper.open".equals(var4)) {
                     this.mListener.CarWiper(true);
                  } else if ("car.wiper.close".equals(var4)) {
                     this.mListener.CarWiper(false);
                  }
                  break;
               case 19:
                  var4 = var2.getStringExtra("action");
                  if ("car.clearance.lamps.open".equals(var4)) {
                     this.mListener.CarClearancelamps(true);
                  } else if ("car.clearance.lamp.close".equals(var4)) {
                     this.mListener.CarClearancelamps(false);
                  }
                  break;
               case 20:
                  var4 = var2.getStringExtra("action");
                  if ("ac.defrost.open".equals(var4)) {
                     this.mListener.AcDefrost(true);
                  } else if ("ac.defrost.close".equals(var4)) {
                     this.mListener.AcDefrost(false);
                  }
                  break;
               case 21:
                  var4 = var2.getStringExtra("action");
                  var5 = var2.getStringExtra("mode");
                  if ("car.wiper".equals(var4)) {
                     if ("intermittent".equals(var5)) {
                        this.mListener.CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum.intermittent);
                     } else if ("speed.low".equals(var5)) {
                        this.mListener.CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum.speedlow);
                     } else if ("speed.high".equals(var5)) {
                        this.mListener.CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum.speedhigh);
                     } else if ("wash".equals(var5)) {
                        this.mListener.CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum.wash);
                     }
                  }
                  break;
               case 22:
                  var4 = var2.getStringExtra("action");
                  var3 = var2.getIntExtra("number", 1);
                  if ("car.wiper.move".equals(var4)) {
                     this.mListener.CarWiperMove(var3);
                  }
                  break;
               case 23:
                  var4 = var2.getStringExtra("action");
                  var5 = var2.getStringExtra("type");
                  if ("car.window.open".equals(var4)) {
                     if ("left.front".equals(var5)) {
                        this.mListener.CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum.leftfront);
                     } else if ("right.front".equals(var5)) {
                        this.mListener.CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum.rightfront);
                     }
                  }
                  break;
               case 24:
                  if ("car.window.close".equals(var2.getStringExtra("action"))) {
                     this.mListener.CarWindowClose();
                  }
            }
         }
      }

   }
}
