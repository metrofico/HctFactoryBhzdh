package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.control.air_control.AirControlHelper;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.ReceiveKeyManager;
import com.hzbhd.proxy.keydispatcher.constants.KeyDispatcherConstant;
import com.hzbhd.proxy.keydispatcher.interfaces.IHotKeyListener;
import java.util.Objects;

public class ActionControlUtil {
   private static final String TAG = "ActionControlUtil";
   private static AirControlHelper mAirControlHelper;
   private static AirPageUiSet mAirPageUiSet;
   private static UiMgrInterface mUiMgrInterface;

   public static void acControl(Context var0, String var1) {
      acControl(var0, var1, (String)null, (String)null);
   }

   public static void acControl(Context var0, String var1, String var2, String var3) {
      Log.i("ActionControlUtil", "acControl: 1");
      if (!TextUtils.isEmpty(var1)) {
         Log.i("ActionControlUtil", "acControl: 2");
         if (mUiMgrInterface == null) {
            mUiMgrInterface = UiMgrFactory.getCanUiMgr(var0);
         }

         if (mUiMgrInterface != null) {
            Log.i("ActionControlUtil", "acControl: 3");
            if (mAirPageUiSet == null) {
               mAirPageUiSet = mUiMgrInterface.getAirUiSet(var0);
            }

            if (mAirPageUiSet != null) {
               Log.i("ActionControlUtil", "acControl: 4");
               if (mAirControlHelper == null) {
                  mAirControlHelper = new AirControlHelper(var0, mAirPageUiSet);
               }

               Log.i("ActionControlUtil", "acControl: action[" + var1 + "], type[" + var2 + "], value[" + var3 + "]");
               if (CanIdSpecialConfig.isNewVoiceCanID(CanbusConfig.INSTANCE.getCanType())) {
                  VoiceControlData.voiceAction = var1;
                  VoiceControlData.voiceType = var2;
                  VoiceControlData.voiceValue = var3;

                  try {
                     MsgMgrFactory.getCanMsgMgrUtil(var0).voiceControlInfo(var1);
                  } catch (NullPointerException var5) {
                     var5.printStackTrace();
                  }

               } else {
                  AirControlHelper var8;
                  switch (var1) {
                     case "ac.windlevel.up":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getWindIncrease());
                        break;
                     case "ac.windlevel.down":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getWindDecrease());
                        break;
                     case "ac.windlevel.max":
                        var8 = mAirControlHelper;
                        var8.airControlMost(var8.getWindIncrease());
                        break;
                     case "ac.windlevel.min":
                        var8 = mAirControlHelper;
                        var8.airControlMost(var8.getWindDecrease());
                        break;
                     case "ac.windlevel.to":
                        if (var2 != null && var3 != null) {
                           var8 = mAirControlHelper;
                           var8.airControlTarget(var8.getWindTarget(), var2, var3);
                           break;
                        }

                        return;
                     case "air_left_temperature_up":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getLeftTemperatureUp());
                        break;
                     case "air_left_temperature_down":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getLeftTemperatureDown());
                        break;
                     case "air_right_temperature_up":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getRightTemperatureUp());
                        break;
                     case "air_right_temperature_down":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getRightTemperatureDown());
                        break;
                     case "ac.temperature.up":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getTemperatureUp());
                        break;
                     case "ac.temperature.down":
                        var8 = mAirControlHelper;
                        var8.airControlStep(var8.getTemperatureDown());
                        break;
                     case "ac.temperature.max":
                        var8 = mAirControlHelper;
                        var8.airControlMost(var8.getTemperatureUp());
                        break;
                     case "ac.temperature.min":
                        var8 = mAirControlHelper;
                        var8.airControlMost(var8.getTemperatureDown());
                        break;
                     case "ac.temperature.to":
                        if (var2 != null && var3 != null) {
                           var8 = mAirControlHelper;
                           var8.airControlTarget(var8.getTemperatureTarget(), var2, var3);
                           break;
                        }

                        return;
                     case "ac.open":
                     case "ac.close":
                     case "air.ac.on":
                     case "air.ac.off":
                     case "air.in.out.cycle.on":
                     case "air.in.out.cycle.off":
                     case "ac":
                     case "auto":
                     case "dual":
                     case "front_defog":
                     case "rear_defog":
                     case "in_out_cycle":
                     case "power":
                     case "blow_positive":
                        mAirControlHelper.airControlAction(var1);
                        break;
                     default:
                        NullPointerException var10000;
                        label269: {
                           MsgMgrInterfaceUtil var9;
                           boolean var10001;
                           try {
                              var9 = MsgMgrFactory.getCanMsgMgrUtil(var0);
                           } catch (NullPointerException var7) {
                              var10000 = var7;
                              var10001 = false;
                              break label269;
                           }

                           try {
                              var9.voiceControlInfo(var1);
                              return;
                           } catch (NullPointerException var6) {
                              var10000 = var6;
                              var10001 = false;
                           }
                        }

                        NullPointerException var10 = var10000;
                        var10.printStackTrace();
                  }

               }
            }
         }
      }
   }

   public static void registerHotKeyListener(Context var0) {
      ReceiveKeyManager.getInstance().registerHotKeyListener(KeyDispatcherConstant.getAppId(SourceConstantsDef.MODULE_ID.CANBUS), new IHotKeyListener(var0) {
         private Intent intent;
         final Context val$context;

         {
            this.val$context = var1;
         }

         public boolean onKeyEvent(int var1, int var2, int var3, Bundle var4) {
            if (var1 != 182) {
               if (var1 != 185 && var1 != 217) {
                  if (var1 != 218) {
                     switch (var1) {
                        case 359:
                           ActionControlUtil.acControl(this.val$context, "ac.windlevel.down");
                           break;
                        case 360:
                           ActionControlUtil.acControl(this.val$context, "ac.windlevel.up");
                           break;
                        case 361:
                           ActionControlUtil.acControl(this.val$context, "ac.temperature.down");
                           break;
                        case 362:
                           ActionControlUtil.acControl(this.val$context, "ac.temperature.up");
                           break;
                        case 363:
                           ActionControlUtil.acControl(this.val$context, "front_defog");
                           break;
                        case 364:
                           ActionControlUtil.acControl(this.val$context, "in_out_cycle");
                           break;
                        case 365:
                           ActionControlUtil.acControl(this.val$context, "auto");
                           break;
                        case 366:
                           ActionControlUtil.acControl(this.val$context, "ac");
                           break;
                        case 367:
                           ActionControlUtil.acControl(this.val$context, "power");
                           break;
                        default:
                           return false;
                     }
                  } else {
                     Intent var6 = new Intent(this.val$context, AirActivity.class);
                     this.intent = var6;
                     var6.setFlags(268435456);
                     this.val$context.startActivity(this.intent);
                  }
               } else {
                  ActivityUtils.startCanbusMainActivity(this.val$context);
               }
            } else {
               try {
                  ((MsgMgrInterfaceUtil)Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(this.val$context))).onKeyEvent(var1, var2, var3, var4);
               } catch (NullPointerException var5) {
                  var5.printStackTrace();
               }
            }

            return true;
         }
      });
   }
}
