package com.hzbhd.canbus.car._448.speech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._448.air.AirData;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0016¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f"},
   d2 = {"Lcom/hzbhd/canbus/car/_448/speech/SpeechReceive;", "", "()V", "TAG", "", "speech_rx_can", "speech_to_can", "tx_action", "type", "value", "register", "", "context", "Landroid/content/Context;", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class SpeechReceive {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final Lazy instance$delegate;
   private static IntentFilter intentFilter = new IntentFilter();
   private final String TAG = "SLDSpeech";
   private final String speech_rx_can = "speech.rx.can";
   private final String speech_to_can = "speech.tx.can";
   private final String tx_action = "tx_action";
   private final String type = "type";
   private final String value = "value";

   static {
      instance$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   public SpeechReceive() {
      intentFilter.addAction("speech.tx.can");
   }

   public final void register(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Log.d(this.TAG, "register: ");
      var1.registerReceiver((BroadcastReceiver)(new BroadcastReceiver(this) {
         final SpeechReceive this$0;

         {
            this.this$0 = var1;
         }

         public void onReceive(Context var1, Intent var2) {
            if (var2 != null && var2.getAction().equals(this.this$0.speech_to_can)) {
               String var6 = var2.getStringExtra(this.this$0.tx_action);
               Log.d(this.this$0.TAG, "onReceive: tx_action_value =" + var6);
               if (var6 != null) {
                  int var5 = var6.hashCode();
                  int var3 = 31;
                  boolean var4 = true;
                  String var7;
                  String var8;
                  CharSequence var9;
                  short var10;
                  int var11;
                  boolean var13;
                  switch (var5) {
                     case -2115768438:
                        if (var6.equals("sun.visor.open.half")) {
                           Log.d("AIR_STATE", "遮阳帘开一半");
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                        }
                        break;
                     case -1871342186:
                        if (var6.equals("rear.right.window.close")) {
                           Log.d("AIR_STATE", "关闭车窗  后右");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 108, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case -1826242962:
                        if (var6.equals("cold.close")) {
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持操作制冷");
                           Log.d("AIR_STATE", "关闭制冷");
                        }
                        break;
                     case -1391366141:
                        if (var6.equals("skylight.open")) {
                           Log.d("AIR_STATE", "打开天窗");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 64, 0, 0, 0, 0, 0, 64, 0});
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, -64, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case -1386876687:
                        if (var6.equals("front.right.window.close")) {
                           Log.d("AIR_STATE", "关闭车窗  前右");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 84, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case -1355728726:
                        if (var6.equals("dvr.close")) {
                           Log.d("AIR_STATE", "关闭行车记录仪");
                           DvrObserver.getInstance().dataChange("speech.exit.dvr");
                        }
                        break;
                     case -1326924100:
                        if (var6.equals("ac.temperature.dec")) {
                           var7 = var2.getStringExtra(this.this$0.type);
                           var8 = var2.getStringExtra(this.this$0.value);
                           if (var8 != null) {
                              var3 = Integer.parseInt(var8);
                           } else {
                              var3 = -1;
                           }

                           var9 = (CharSequence)var7;
                           if (var9 != null && var9.length() != 0) {
                              var4 = false;
                           } else {
                              var4 = true;
                           }

                           if (!var4 && var3 != -1) {
                              Intrinsics.checkNotNullExpressionValue(var7, "typeValue");
                              if (Intrinsics.areEqual((Object)var7, (Object)SpeechAction.TypeEnum.left.getValue())) {
                                 Log.d("AIR_STATE", "空调温度降低" + var3);
                                 var11 = AirData.temp_value - var3;
                                 var3 = var11;
                                 if (var11 < 16) {
                                    var3 = 16;
                                 }

                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var7, (Object)SpeechAction.TypeEnum.right.getValue())) {
                                 Log.d("AIR_STATE", "空调温度降低" + var3);
                                 var11 = AirData.temp_value - var3;
                                 var3 = var11;
                                 if (var11 < 16) {
                                    var3 = 16;
                                 }

                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var7, (Object)SpeechAction.TypeEnum.empty.getValue())) {
                                 Log.d("AIR_STATE", "空调温度降低" + var3);
                                 var11 = AirData.temp_value - var3;
                                 var3 = var11;
                                 if (var11 < 16) {
                                    var3 = 16;
                                 }

                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                              }
                           }
                        }
                        break;
                     case -1326919016:
                        if (var6.equals("ac.temperature.inc")) {
                           var7 = var2.getStringExtra(this.this$0.type);
                           var8 = var2.getStringExtra(this.this$0.value);
                           if (var8 != null) {
                              var11 = Integer.parseInt(var8);
                           } else {
                              var11 = -1;
                           }

                           var9 = (CharSequence)var7;
                           boolean var12;
                           if (var9 != null && var9.length() != 0) {
                              var12 = false;
                           } else {
                              var12 = true;
                           }

                           if (!var12 && var11 != -1) {
                              Intrinsics.checkNotNullExpressionValue(var7, "typeValue");
                              if (Intrinsics.areEqual((Object)var7, (Object)SpeechAction.TypeEnum.left.getValue())) {
                                 Log.d("AIR_STATE", "空调温度增加" + var11);
                                 var11 += AirData.temp_value;
                                 if (var11 <= 31) {
                                    var3 = var11;
                                 }

                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var7, (Object)SpeechAction.TypeEnum.right.getValue())) {
                                 Log.d("AIR_STATE", "空调温度增加" + var11);
                                 var11 += AirData.temp_value;
                                 if (var11 <= 31) {
                                    var3 = var11;
                                 }

                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var7, (Object)SpeechAction.TypeEnum.empty.getValue())) {
                                 Log.d("AIR_STATE", "空调温度增加" + var11);
                                 var11 += AirData.temp_value;
                                 if (var11 <= 31) {
                                    var3 = var11;
                                 }

                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                              }
                           }
                        }
                        break;
                     case -1226331153:
                        if (var6.equals("ac.mode")) {
                           var6 = var2.getStringExtra(this.this$0.type);
                           var9 = (CharSequence)var6;
                           var13 = var4;
                           if (var9 != null) {
                              if (var9.length() == 0) {
                                 var13 = var4;
                              } else {
                                 var13 = false;
                              }
                           }

                           if (!var13) {
                              Intrinsics.checkNotNullExpressionValue(var6, "typeValue");
                              if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.WindValueEnum.face.getValue())) {
                                 Log.d("AIR_STATE", "吹风模式 吹面");
                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 36, 0, 0, 0, 0, 0, 0, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.WindValueEnum.foot.getValue())) {
                                 Log.d("AIR_STATE", "吹风模式 吹脚");
                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 32, 0, 0, 0, 0, 0, 0, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.WindValueEnum.facefoot.getValue())) {
                                 Log.d("AIR_STATE", "吹风模式 吹面吹脚");
                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 40, 0, 0, 0, 0, 0, 0, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.WindValueEnum.defrost.getValue())) {
                                 Log.d("AIR_STATE", "吹风模式 除霜");
                                 CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 48, 0, 0, 0, 0, 0, 0, 0, 0});
                              } else if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.WindValueEnum.footdefrost.getValue())) {
                                 Log.d("AIR_STATE", "吹风模式 吹脚除霜");
                                 SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                              } else if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.WindValueEnum.auto.getValue())) {
                                 Log.d("AIR_STATE", "吹风模式 自动");
                                 SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                              }
                           }
                        }
                        break;
                     case -1226270570:
                        if (var6.equals("ac.open")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 16, 0, 2, 0, 0, 0});
                           Log.d("AIR_STATE", "打开空调");
                        }
                        break;
                     case -1151750824:
                        if (var6.equals("dvr.open")) {
                           Log.d("AIR_STATE", "打开行车记录仪");
                           DvrObserver.getInstance().dataChange("speech.start.dvr");
                        }
                        break;
                     case -964646904:
                        if (var6.equals("ac.defrost.front.max")) {
                           Log.d("AIR_STATE", "最大前除霜");
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                        }
                        break;
                     case -891288852:
                        if (var6.equals("rear.right.window.open")) {
                           Log.d("AIR_STATE", "打开车窗  后右");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 104, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case -866529054:
                        if (var6.equals("air.in.out.cycle.off")) {
                           Log.d("AIR_STATE", "切换外循环");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 3, 0, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case -776229461:
                        if (var6.equals("sun.visor.close")) {
                           Log.d("AIR_STATE", "关闭遮阳帘");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, -128, 0, -128, 0, 0, 0});
                        }
                        break;
                     case -655752154:
                        if (var6.equals("ac.windlevel.max")) {
                           Log.d("AIR_STATE", "最大风速");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, -16, 0, 0, 0, 0});
                        }
                        break;
                     case -655751916:
                        if (var6.equals("ac.windlevel.min")) {
                           Log.d("AIR_STATE", "最小风速");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, -128, 0, 0, 0, 0});
                        }
                        break;
                     case -537609520:
                        if (var6.equals("ac.left.right.sync.open")) {
                           Log.d("AIR_STATE", "打开空调同步");
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                        }
                        break;
                     case -193868961:
                        if (var6.equals("skylight.close")) {
                           Log.d("AIR_STATE", "关闭天窗");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, -128, 0, 0, 0, 0, 0, 64, 0});
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, -64, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case -64220354:
                        if (var6.equals("skylight.open.half")) {
                           Log.d("AIR_STATE", "天窗开一半");
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                        }
                        break;
                     case 21742501:
                        if (var6.equals("dvr.take.picture")) {
                           Log.d("AIR_STATE", "我要拍照");
                           DvrObserver.getInstance().dataChange("speech.take.picture");
                        }
                        break;
                     case 33987515:
                        if (var6.equals("ac.defrost.behind.close")) {
                           Log.d("AIR_STATE", "关闭后除霜");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 2, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 160790566:
                        if (var6.equals("ac.defrost.front.open")) {
                           Log.d("AIR_STATE", "打开前除霜除雾");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 48, 0, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 292772663:
                        if (var6.equals("rear.left.window.close")) {
                           Log.d("AIR_STATE", "关闭车窗  后左");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 96, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 345504582:
                        if (var6.equals("front.left.window.open")) {
                           Log.d("AIR_STATE", "打开车窗  前左");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 68, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 436792497:
                        if (var6.equals("dvr.record.stop")) {
                           Log.d("AIR_STATE", "停止录像");
                           DvrObserver.getInstance().dataChange("speech.record.stop");
                        }
                        break;
                     case 502782514:
                        if (var6.equals("ac.left.right.sync.close")) {
                           Log.d("AIR_STATE", "关闭空调同步");
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                        }
                        break;
                     case 629126444:
                        if (var6.equals("ac.close")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 32, 0, 2, 0, 0, 0});
                           Log.d("AIR_STATE", "关闭空调");
                        }
                        break;
                     case 655652243:
                        if (var6.equals("dvr.record.start")) {
                           Log.d("AIR_STATE", "开始录像");
                           DvrObserver.getInstance().dataChange("speech.record.start");
                        }
                        break;
                     case 678348700:
                        if (var6.equals("ac.defrost.front.close")) {
                           Log.d("AIR_STATE", "关闭前除霜");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 52, 0, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 919236962:
                        if (var6.equals("skylight.close.half")) {
                           Log.d("AIR_STATE", "天窗关一半");
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                        }
                        break;
                     case 925454385:
                        if (var6.equals("front.right.window.open")) {
                           Log.d("AIR_STATE", "打开车窗  前右");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 80, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 1146265120:
                        if (var6.equals("ac.windlevel.down")) {
                           Log.d("AIR_STATE", "风量减小");
                           var3 = AirData.wind_level - 1;
                           var11 = var3;
                           if (var3 < 1) {
                              var11 = 1;
                           }

                           if (var11 == 1) {
                              var10 = 128;
                           } else {
                              var10 = 0;
                           }

                           if (var11 == 2) {
                              var10 = 144;
                           }

                           if (var11 == 3) {
                              var10 = 160;
                           }

                           if (var11 == 4) {
                              var10 = 176;
                           }

                           if (var11 == 5) {
                              var10 = 192;
                           }

                           if (var11 == 6) {
                              var10 = 208;
                           }

                           if (var11 == 7) {
                              var10 = 224;
                           }

                           if (var11 == 8) {
                              var10 = 240;
                           }

                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)var10, 0, 0, 0, 0});
                        }
                        break;
                     case 1168724146:
                        if (var6.equals("ac.heat.max")) {
                           Log.d("AIR_STATE", "最大制热");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, 15, 0, 16, 0, 0});
                        }
                        break;
                     case 1225772921:
                        if (var6.equals("ac.windlevel.to")) {
                           var7 = var2.getStringExtra(this.this$0.type);
                           var8 = var2.getStringExtra(this.this$0.value);
                           if (var8 != null) {
                              var11 = Integer.parseInt(var8);
                           } else {
                              var11 = -1;
                           }

                           var9 = (CharSequence)var7;
                           if (var9 != null && var9.length() != 0) {
                              var13 = false;
                           } else {
                              var13 = true;
                           }

                           if (!var13 && var11 != -1 && var7.equals(SpeechAction.TypeEnum.absolute.getValue())) {
                              Log.d("AIR_STATE", "风量调到" + var11);
                              if (var11 == 1) {
                                 var10 = 128;
                              } else {
                                 var10 = 0;
                              }

                              if (var11 == 2) {
                                 var10 = 144;
                              }

                              if (var11 == 3) {
                                 var10 = 160;
                              }

                              if (var11 == 4) {
                                 var10 = 176;
                              }

                              if (var11 == 5) {
                                 var10 = 192;
                              }

                              if (var11 == 6) {
                                 var10 = 208;
                              }

                              if (var11 == 7) {
                                 var10 = 224;
                              }

                              if (var11 == 8) {
                                 var10 = 240;
                              }

                              CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)var10, 0, 0, 0, 0});
                           }
                        }
                        break;
                     case 1225772953:
                        if (var6.equals("ac.windlevel.up")) {
                           Log.d("AIR_STATE", "风量增大");
                           var3 = AirData.wind_level + 1;
                           var11 = var3;
                           if (var3 > 8) {
                              var11 = 8;
                           }

                           if (var11 == 1) {
                              var10 = 128;
                           } else {
                              var10 = 0;
                           }

                           if (var11 == 2) {
                              var10 = 144;
                           }

                           if (var11 == 3) {
                              var10 = 160;
                           }

                           if (var11 == 4) {
                              var10 = 176;
                           }

                           if (var11 == 5) {
                              var10 = 192;
                           }

                           if (var11 == 6) {
                              var10 = 208;
                           }

                           if (var11 == 7) {
                              var10 = 224;
                           }

                           if (var11 == 8) {
                              var10 = 240;
                           }

                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)var10, 0, 0, 0, 0});
                        }
                        break;
                     case 1358234944:
                        if (var6.equals("all.windows.close")) {
                           Log.d("AIR_STATE", "关闭车窗  全部");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 120, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 1360794679:
                        if (var6.equals("sun.visor.open")) {
                           Log.d("AIR_STATE", "打开遮阳帘");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 64, 0, -128, 0, 0, 0});
                        }
                        break;
                     case 1481217153:
                        if (var6.equals("ac.temperature.to")) {
                           var6 = var2.getStringExtra(this.this$0.type);
                           var8 = var2.getStringExtra(this.this$0.value);
                           if (var8 != null) {
                              var3 = Integer.parseInt(var8);
                           } else {
                              var3 = -1;
                           }

                           var9 = (CharSequence)var6;
                           if (var9 != null && var9.length() != 0) {
                              var4 = false;
                           } else {
                              var4 = true;
                           }

                           if (!var4 && var3 != -1) {
                              Intrinsics.checkNotNullExpressionValue(var6, "typeValue");
                              if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.TypeEnum.left.getValue())) {
                                 Log.d("AIR_STATE", "空调温度调至" + this.this$0.value);
                                 if (var3 >= 16 && var3 <= 31) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                                 } else {
                                    SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持调至该温度值");
                                 }
                              } else if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.TypeEnum.right.getValue())) {
                                 Log.d("AIR_STATE", "空调温度调至" + this.this$0.value);
                                 if (var3 >= 16 && var3 <= 31) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                                 } else {
                                    SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持调至该温度值");
                                 }
                              } else if (Intrinsics.areEqual((Object)var6, (Object)SpeechAction.TypeEnum.empty.getValue())) {
                                 Log.d("AIR_STATE", "空调温度调至" + this.this$0.value);
                                 if (var3 >= 16 && var3 <= 31) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, (byte)(var3 - 16), 0, 16, 0, 0});
                                 } else {
                                    SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持调至该温度值");
                                 }
                              }
                           }
                        }
                        break;
                     case 1496068108:
                        if (var6.equals("air.in.out.cycle.on")) {
                           Log.d("AIR_STATE", "切换内循环");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 2, 0, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 1604017940:
                        if (var6.equals("cold.open")) {
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持操作制冷");
                           Log.d("AIR_STATE", "打开制冷");
                        }
                        break;
                     case 1723129227:
                        if (var6.equals("ac.cool.max")) {
                           Log.d("AIR_STATE", "最大制冷");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 16, 0, 0});
                        }
                        break;
                     case 1745755798:
                        if (var6.equals("sun.visor.close.half")) {
                           Log.d("AIR_STATE", "遮阳帘关一半");
                           SpeechSend.sendSpeechTTsBroadcast(var1, "暂不支持此功能");
                        }
                        break;
                     case 1941120039:
                        if (var6.equals("ac.defrost.behind.open")) {
                           Log.d("AIR_STATE", "打开后除霜");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 3, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 1949467947:
                        if (var6.equals("rear.left.window.open")) {
                           Log.d("AIR_STATE", "打开车窗  后左");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 92, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 1983837698:
                        if (var6.equals("all.windows.open")) {
                           Log.d("AIR_STATE", "打开车窗  所有");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 116, 0, 0, 0, 0, 0, 0, 0});
                        }
                        break;
                     case 2109515900:
                        if (var6.equals("front.left.window.close")) {
                           Log.d("AIR_STATE", "关闭车窗  前左");
                           CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, 21, 0, 72, 0, 0, 0, 0, 0, 0, 0});
                        }
                  }
               }
            }

         }
      }), intentFilter);
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\u0004R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lcom/hzbhd/canbus/car/_448/speech/SpeechReceive$Companion;", "", "()V", "instance", "Lcom/hzbhd/canbus/car/_448/speech/SpeechReceive;", "getInstance", "()Lcom/hzbhd/canbus/car/_448/speech/SpeechReceive;", "instance$delegate", "Lkotlin/Lazy;", "intentFilter", "Landroid/content/IntentFilter;", "get", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      private final SpeechReceive getInstance() {
         return (SpeechReceive)SpeechReceive.instance$delegate.getValue();
      }

      public final SpeechReceive get() {
         return this.getInstance();
      }
   }
}
