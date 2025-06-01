package com.hzbhd.canbus.car._459;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._459.air.AirDataBuffer;
import com.hzbhd.canbus.car._459.air.OptionAirCmd459;
import com.hzbhd.canbus.car._459.mp5_state.TaskObserver;
import com.hzbhd.canbus.car._459.mp5_state.UDiskReceiver;
import com.hzbhd.canbus.car._459.mp5_state.WifiStateOnReceiver;
import com.hzbhd.canbus.car._459.mp5_state.WifiStateReceiver;
import com.hzbhd.canbus.car._459.mp5_time.Mp5Time;
import com.hzbhd.canbus.car._459.settings.EcoWindow;
import com.hzbhd.canbus.car._459.settings.OptionSettingsCmd459;
import com.hzbhd.canbus.car._459.settings.SettingsDataBuffer;
import com.hzbhd.canbus.car._459.settings.SharedToLauncher;
import com.hzbhd.canbus.car._459.tbox_wifi.TboxWifiState;
import com.hzbhd.canbus.car._459.tbox_wifi.WifiManager;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.config.bean.UIBean;
import com.hzbhd.config.use.UI;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgMgr extends AbstractMsgMgr {
   private HashMap dataHash;
   DecimalFormat formatDecimal0;
   DecimalFormat formatDecimal1;
   Boolean isLeft;
   Boolean isRight;
   int[] mAirData;
   int[] mCanBusInfoInt;
   private Context mContext;
   private Handler mThreadHandler;
   int[] mTurnData;
   private UiMgr mUiMgr;
   int[] mcuErrorStateInt;
   String name1;
   String name2;
   String name3;
   String name4;
   String name5;
   String password1;
   String password2;
   String password3;
   private WifiStateReceiver receiver;
   private WifiStateOnReceiver receiverOn;
   private SimpleDateFormat simpleDateFormat1;
   private TaskObserver taskObserver;
   private int[] timeCanInfo;
   private int timeSyncTag;
   private UDiskReceiver uDiskReceiver;

   public MsgMgr() {
      this.mThreadHandler = new Handler(this, BaseUtil.INSTANCE.getHandlerThread().getLooper()) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
         }
      };
      this.timeSyncTag = 11;
      this.formatDecimal1 = new DecimalFormat("###0.0");
      this.formatDecimal0 = new DecimalFormat("###0");
      this.name1 = "";
      this.name2 = "";
      this.name3 = "";
      this.name4 = "";
      this.name5 = "";
      this.password1 = "";
      this.password2 = "";
      this.password3 = "";
      Boolean var1 = false;
      this.isLeft = var1;
      this.isRight = var1;
      this.dataHash = new HashMap();
   }

   private void checkBtJson(String var1) {
      if (var1 != null) {
         boolean var10001;
         boolean var2;
         JSONObject var3;
         try {
            var3 = new JSONObject(var1);
            var2 = var3.getBoolean("ENABLE");
         } catch (JSONException var6) {
            var10001 = false;
            return;
         }

         if (!var2) {
            try {
               this.sendMp5StateData("MP5_BluetoothSts", 2);
            } catch (JSONException var5) {
               var10001 = false;
            }
         } else {
            try {
               if (var3.getBoolean("HFP_CONN")) {
                  this.sendMp5StateData("MP5_BluetoothSts", 1);
                  return;
               }
            } catch (JSONException var7) {
               var10001 = false;
               return;
            }

            try {
               this.sendMp5StateData("MP5_BluetoothSts", 0);
            } catch (JSONException var4) {
               var10001 = false;
            }
         }

      }
   }

   private String getResult(int[] var1) {
      ArrayList var3 = new ArrayList();
      int var2 = var1[8];
      if (var2 != 255) {
         var3.add(var2);
      }

      var2 = var1[9];
      if (var2 != 255) {
         var3.add(var2);
      }

      var2 = var1[10];
      if (var2 != 255) {
         var3.add(var2);
      }

      var2 = var1[11];
      if (var2 != 255) {
         var3.add(var2);
      }

      var2 = var1[12];
      if (var2 != 255) {
         var3.add(var2);
      }

      var2 = var1[13];
      if (var2 != 255) {
         var3.add(var2);
      }

      var2 = var1[14];
      if (var2 != 255) {
         var3.add(var2);
      }

      byte[] var4 = new byte[var3.size()];

      for(var2 = 0; var2 < var3.size(); ++var2) {
         var4[var2] = (byte)(Integer)var3.get(var2);
      }

      return var3.size() == 0 ? "" : new String(var4);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTurnDataChange(int[] var1) {
      if (Arrays.equals(this.mTurnData, var1)) {
         return false;
      } else {
         this.mTurnData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   // $FF: synthetic method
   static void lambda$registerUiState$1(String var0) {
      Log.d("LINY_YI", "UI_CHANGE=" + var0);
      if ("c8".equals(var0)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, -18, -18, -18, -56});
      }

      if ("c9".equals(var0)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, -18, -18, -18, -55});
      }

   }

   private void notifyChargeState(String var1, Object var2) {
      ShareDataManager.getInstance().reportString("can.bus.all.data.share", "{\"TAG\":\"" + var1 + "\",\"VALUE\":\"" + var2 + "\"}");
   }

   private void notifyOtherModule(String var1, Object var2) {
      ShareDataManager.getInstance().reportString("can.bus.all.data.share", "{\"TAG\":\"" + var1 + "\",\"VALUE\":\"" + var2 + "\"}");
   }

   private void refreshTboxTimeSwitch() {
      if ("off".equals(System.getString(BaseUtil.INSTANCE.getContext().getContentResolver(), "settings.can.tbox.time.switch.on.off"))) {
         this.timeSyncTag = 11;
      } else if (this.timeSyncTag > 10) {
         this.timeSyncTag = 0;
         int[] var1 = this.timeCanInfo;
         if (var1 != null && var1.length != 0) {
            this.setTimer0x18FEE64A(var1);
         }
      }

   }

   private void registerGpsStatusListener() {
      LocationManager var1 = (LocationManager)this.mContext.getSystemService("location");
      if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
         var1.addGpsStatusListener(new GpsStatus.Listener(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onGpsStatusChanged(int var1) {
               Log.d("CAN_MSG_GPS", "Event=" + var1);
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           Log.e("CAN_MSG_GPS", "onGpsStatusChanged status");
                        }
                     } else {
                        Log.e("CAN_MSG_GPS", "onGpsStatusChanged first fix");
                     }
                  } else {
                     Log.e("CAN_MSG_GPS", "onGpsStatusChanged stopped");
                     this.this$0.sendMp5StateData("MP5_GPSAntennaSts", 0);
                  }
               } else {
                  Log.e("CAN_MSG_GPS", "onGpsStatusChanged started");
                  this.this$0.sendMp5StateData("MP5_GPSAntennaSts", 1);
               }

            }
         });
      }

   }

   private void registerTaskObserver(Context var1) {
      TaskObserver var2 = new TaskObserver();
      this.taskObserver = var2;
      var2.setCurrStatusListener(new TaskObserver.CurrStatusListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onEasyRunningChange(boolean var1) {
            Log.d("DDDD", "onEasyRunningChange=" + var1);
            this.this$0.sendMp5StateData("MP5_PhoneConetSts", var1);
         }

         public void onEmulatedChange(float var1) {
            Log.d("DDDD", "onEmulatedChange=" + var1);
            this.this$0.sendMp5StateData("MP5_RAMSts", (int)(var1 * 100.0F));
         }

         public void onMemoryChange(float var1) {
            Log.d("DDDD", "onMemoryChange=" + var1);
            this.this$0.sendMp5StateData("MP5_ROMSts", (int)(var1 * 100.0F));
         }

         public void onMusicRunningChange(boolean var1) {
            Log.d("DDDD", "onMusicRunningChange=" + var1);
            this.this$0.sendMp5StateData("MP5_MediaSts", var1);
         }

         public void onNaviRunningChange(boolean var1) {
            Log.d("DDDD", "onNaviRunningChange=" + var1);
            this.this$0.sendMp5StateData("MP5_NavgtSts", var1);
         }

         public void onRadioRunningChange(boolean var1) {
            Log.d("DDDD", "onRadioRunningChange=" + var1);
            this.this$0.sendMp5StateData("MP5_RadioSts", var1);
         }

         public void onVideoRunningChange(boolean var1) {
            Log.d("DDDD", "onVideoRunningChange=" + var1);
            this.this$0.sendMp5StateData("MP5_VidoSts", var1);
         }
      });
   }

   private void registerTboxTimerSwitch(Context var1) {
      BaseUtil.INSTANCE.getContext().getContentResolver().registerContentObserver(System.getUriFor("settings.can.tbox.time.switch.on.off"), false, new ContentObserver(this, this.mThreadHandler) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onChange(boolean var1) {
            super.onChange(var1);
            this.this$0.refreshTboxTimeSwitch();
         }
      });
      this.refreshTboxTimeSwitch();
   }

   private void registerTboxWifi(Context var1) {
      WifiManager.getInstance().init(var1);
      WifiManager.getInstance().register();
   }

   private void registerUiState() {
      ShareDataManager.getInstance().registerShareStringListener("ui.id", new MsgMgr$$ExternalSyntheticLambda0());
   }

   private void registerUsbState(Context var1) {
      this.uDiskReceiver = new UDiskReceiver();
      IntentFilter var2 = new IntentFilter();
      var2.addAction("android.intent.action.MEDIA_MOUNTED");
      var2.addAction("android.intent.action.MEDIA_REMOVED");
      var2.addAction("android.intent.action.MEDIA_UNMOUNTED");
      var2.addDataScheme("file");
      var1.registerReceiver(this.uDiskReceiver, var2);
   }

   private void registerWifiState(Context var1) {
      this.receiver = new WifiStateReceiver();
      IntentFilter var2 = new IntentFilter();
      var2.addAction("android.net.wifi.WIFI_STATE_CHANGED");
      var1.registerReceiver(this.receiver, var2);
   }

   private void registerWifiStateOn(Context var1) {
      this.receiverOn = new WifiStateOnReceiver();
      IntentFilter var2 = new IntentFilter();
      var2.addAction("android.net.wifi.STATE_CHANGE");
      var1.registerReceiver(this.receiverOn, var2);
   }

   private void set0X0CF02FE8(int[] var1) {
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 4) != 2) {
         SettingsDataBuffer.getInstance().setCwBuffer(true);
      } else {
         SettingsDataBuffer.getInstance().setCwBuffer(false);
      }

   }

   private void set0x18F561E8(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2);
      if (var2 == 0) {
         SettingsDataBuffer.getInstance().setVdmBuffer("FAR");
      } else if (var2 == 1) {
         SettingsDataBuffer.getInstance().setVdmBuffer("MID");
      } else if (var2 == 2) {
         SettingsDataBuffer.getInstance().setVdmBuffer("NEAR");
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1[9], 2, 2) == 1) {
         if (LogUtil.log5()) {
            LogUtil.d("set0x18F561E8(): --------");
         }

         SettingsDataBuffer.getInstance().setSaBuffer(true);
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[9], 2, 2) == 0) {
         SettingsDataBuffer.getInstance().setSaBuffer(false);
      }

   }

   private void set0x18FE5BE8(int[] var1) {
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 6, 2) == 1) {
         if (LogUtil.log5()) {
            LogUtil.d("set0x18FE5BE8(): ------------");
         }

         SettingsDataBuffer.getInstance().setLdBuffer(true);
      } else {
         if (LogUtil.log5()) {
            LogUtil.d("set0x18FE5BE8(): ------------+++");
         }

         SettingsDataBuffer.getInstance().setLdBuffer(false);
      }

   }

   private void set0x18FF2221(int[] var1) {
      ContentResolver var6 = BaseUtil.INSTANCE.getContext().getContentResolver();
      boolean var5 = false;
      int var2 = System.getInt(var6, "video_did_types_of", 0);
      int var3 = DataHandleUtils.blockBit(var1[7], 0);
      var1[7] = var3;
      var3 = DataHandleUtils.blockBit(var3, 1);
      var1[7] = var3;
      var3 = DataHandleUtils.blockBit(var3, 2);
      var1[7] = var3;
      var1[7] = DataHandleUtils.blockBit(var3, 3);
      var1[8] = 0;
      var1[9] = 0;
      var1[10] = 0;
      var1[11] = 0;
      var1[12] = 0;
      var1[13] = 0;
      var1[14] = 0;
      if (this.isTurnDataChange(var1)) {
         boolean var4;
         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 2) == 1) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 6, 2) == 1) {
            var5 = true;
         }

         this.isLeft = var4;
         this.isRight = var5;
         if (!var4 && !var5) {
            BaseUtil.INSTANCE.runBackDelay(new MsgMgr$$ExternalSyntheticLambda1(this), 3000L);
         } else if (var2 != 1) {
            if (LogUtil.log5()) {
               LogUtil.d("set0x18FF2221(): --------");
            }

            ShareDataManager.getInstance().reportInt("user.Reverse", 1);
         }

         if (var2 != 1) {
            try {
               JSONObject var8 = new JSONObject();
               var8.put("TAG", "TURN_SIGNAL");
               var8.put("LEFT", var4);
               var8.put("RIGHT", var5);
               ShareDataManager.getInstance().reportString("can.bus.all.data.share", var8.toString());
            } catch (JSONException var7) {
               var7.printStackTrace();
            }
         }
      }

   }

   private void set0xCCCCCCCC(int[] var1) {
      int var2 = var1[13];
      boolean var3 = false;
      if (DataHandleUtils.getIntFromByteWithBit(var2, 0, 4) == 1) {
         var3 = true;
      }

      this.notifyChargeState("CHARGE_ACC_OPEN", var3);
   }

   private void setAir0x18F5A519(int[] var1) {
      if (this.isAirDataChange(var1)) {
         boolean var8;
         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2) != 0 && DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2) != 2) {
            var8 = false;
         } else {
            var8 = true;
         }

         ShareDataManager.getInstance().reportString("can.bus.all.data.share", "{\"TAG\":\"SHOW_AIR_WINDOW\",\"VALUE\":\"" + var8 + "\"}");
         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 2, 2) == 1) {
            var8 = true;
         } else {
            var8 = false;
         }

         if (OptionAirCmd459.getInstance().auto != var8) {
            OptionAirCmd459.getInstance().auto = var8;
         }

         boolean var9;
         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 6, 2) == 1) {
            var9 = true;
         } else {
            var9 = false;
         }

         boolean var10;
         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 2) == 2) {
            var10 = true;
         } else {
            var10 = false;
         }

         boolean var11;
         if (DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 2) == 1) {
            var11 = true;
         } else {
            var11 = false;
         }

         boolean var12;
         if (DataHandleUtils.getIntFromByteWithBit(var1[10], 0, 2) == 1) {
            var12 = true;
         } else {
            var12 = false;
         }

         boolean var13;
         if (DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 2) == 1) {
            var13 = true;
         } else {
            var13 = false;
         }

         double var4 = (double)var1[11] * 0.5;
         double var2 = var4;
         if (var4 > 32.0) {
            var2 = 32.0;
         }

         if (var2 < 17.0) {
            var2 = 17.0;
         }

         int var6 = var1[13];
         int var7 = DataHandleUtils.getIntFromByteWithBit(var1[9], 4, 4);
         String var14;
         if (var7 != 1) {
            if (var7 != 2) {
               if (var7 != 3) {
                  if (var7 != 4) {
                     if (var7 != 5) {
                        var14 = "NONE";
                     } else {
                        var14 = "FOOT_WINDOW";
                     }
                  } else {
                     var14 = "FACE_FOOT";
                  }
               } else {
                  var14 = "WINDOW";
               }
            } else {
               var14 = "FACE";
            }
         } else {
            var14 = "FOOT";
         }

         var7 = DataHandleUtils.getIntFromByteWithBit(var1[9], 0, 4);
         AirDataBuffer.getInstance().setData(var7, var14, var2, var13, var12, var11, var10, var9, var8, var6 - 40);
      }

   }

   private void setChargeData0x18C027F4(int[] var1) {
      this.notifyOtherModule("CHARGE_VOLTAGE", this.formatDecimal0.format((double)DataHandleUtils.getMsbLsbResult(var1[12], var1[11]) * 0.1) + "V");
      this.notifyOtherModule("CHARGE_CURRENT", this.formatDecimal0.format((double)DataHandleUtils.getMsbLsbResult(var1[8], var1[7]) * 0.1 - 3000.0) + "A");
      int var2 = var1[13];
      if (var2 > 100) {
         this.notifyOtherModule("CHARGE_PROGRESS", 100);
      } else {
         this.notifyOtherModule("CHARGE_PROGRESS", var2);
      }

   }

   private void setChargeData0x18C427F4(int[] var1) {
      int var2 = var1[13];
      boolean var6 = false;
      var2 = DataHandleUtils.getIntFromByteWithBit(var2, 0, 4);
      boolean var3;
      if (var2 == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.notifyOtherModule("CHARGE_STATE", var3);
      if (var2 != 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.notifyOtherModule("CHARGE_STATE_ERROR", var3);
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var4;
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 2, 2) == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var5;
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 2) == 1) {
         var5 = true;
      } else {
         var5 = false;
      }

      if (LogUtil.log5()) {
         LogUtil.d("setChargeData0x18C427F4(): -------" + var3 + "----" + var4 + "-----" + var5);
      }

      if (var3 || var4 || var5) {
         var6 = true;
      }

      this.notifyOtherModule("CHARGE_VIEW_SHOW", var6);
      this.notifyOtherModule("CHARGE_CONNECT1", var3);
      this.notifyOtherModule("CHARGE_CONNECT2", var4);
      this.notifyOtherModule("CHARGE_CONNECT3", var5);
      this.notifyOtherModule("CHARGE_TIMER", String.valueOf(var1[12] * 60 * 1000));
   }

   private void setChargeDataNone(int[] var1) {
      if (var1[7] == 1) {
         this.notifyOtherModule("CHARGE_VIEW_SHOW", false);
         this.notifyOtherModule("CHARGE_STATE", false);
      }

   }

   private void setEco0x18F60127(int[] var1) {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[8], var1[7]) / 256);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[13], 0, 1);
      if ((DataHandleUtils.getIntFromByteWithBit(var1[12], 6, 2) & 255 | (var2 & 255) << 2) == 3) {
         SettingsDataBuffer.getInstance().setSpsBuffer(true);
      } else {
         SettingsDataBuffer.getInstance().setSpsBuffer(false);
      }

   }

   private void setEco0x18F60B27(int[] var1) {
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2) == 1) {
         BaseUtil.INSTANCE.runUi(new Function0(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public Unit invoke() {
               String var1 = UI.INSTANCE.getUI(BaseUtil.INSTANCE.getContext(), UIBean.AppName.launcher);
               EcoWindow.getInstance().show(this.this$0.mContext, var1, new EcoWindow.ActionCallback(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void actionDo(String var1) {
                     if (var1.equals("countdown")) {
                        OptionSettingsCmd459.getInstance().setEcoRequestCmd(2);
                     }

                     if (var1.equals("cancel")) {
                        OptionSettingsCmd459.getInstance().setEcoRequestCmd(0);
                     }

                     if (var1.equals("ok")) {
                        OptionSettingsCmd459.getInstance().setEcoRequestCmd(1);
                     }

                  }
               });
               return null;
            }
         });
      }

   }

   private void setTimer0x18FEE64A(int[] var1) {
      if (var1[7] != 255 || var1[8] != 255 || var1[9] != 255 || var1[10] != 255 || var1[11] != 255 || var1[12] != 255) {
         this.timeCanInfo = var1;
         int var2 = this.timeSyncTag;
         if (var2 <= 10) {
            this.timeSyncTag = var2 + 1;
         }

         if (this.timeSyncTag <= 10) {
            if (this.simpleDateFormat1 == null) {
               this.simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               if (Calendar.getInstance().getTimeZone() instanceof SimpleTimeZone && ((SimpleTimeZone)Calendar.getInstance().getTimeZone()).getRawOffset() == 0) {
                  this.simpleDateFormat1.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
               }
            }

            try {
               SimpleDateFormat var4 = this.simpleDateFormat1;
               StringBuilder var3 = new StringBuilder();
               SystemClock.setCurrentTimeMillis(var4.parse(var3.append(var1[12] + 1985).append("-").append(var1[10]).append("-").append((int)((double)var1[11] * 0.25)).append(" ").append(var1[9]).append(":").append(var1[8]).append(":").append((int)((double)var1[7] * 0.25)).toString()).getTime());
               if (LogUtil.log5()) {
                  var3 = new StringBuilder();
                  LogUtil.d(var3.append("setTimer0x18FEE64A: ").append(this.simpleDateFormat1.getTimeZone()).append(",").append(var1[9]).toString());
               }

               Global.putInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "auto_time_synced", 3);
            } catch (ParseException var5) {
               Log.e("TIME_SYNC_ERROR", var5.toString());
               var5.printStackTrace();
            }

         }
      }
   }

   private void setWifi0x18F5894A() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[7];
      if (var1 == 1) {
         WifiManager.getInstance().requestAction(1);
      } else if (var1 == 16) {
         var1 = var2[8];
         var1 = var2[9];
         if (var1 == 0) {
            this.notifyOtherModule("WIFI_CONNECT", "OFF");
            TboxWifiState.connect = "OFF";
         } else if (var1 == 1) {
            this.notifyOtherModule("WIFI_CONNECT", "ON");
            TboxWifiState.connect = "ON";
         }

         var1 = this.mCanBusInfoInt[10];
         if (var1 == 0) {
            WifiManager.getInstance().requestAction(1);
         } else if (var1 != 1) {
            if (var1 == 2) {
               WifiManager.getInstance().modifyWiFiName();
            } else if (var1 == 3) {
               WifiManager.getInstance().modifyWiFiPassword();
            } else if (var1 == 4) {
               this.notifyOtherModule("WIFI_PROGRESS", false);
               WifiManager.getInstance().requestAction(1);
            }
         }
      } else if (var1 == 32) {
         this.name1 = this.getResult(var2);
      } else if (var1 == 33) {
         this.name2 = this.getResult(var2);
      } else if (var1 == 34) {
         this.name3 = this.getResult(var2);
      } else if (var1 == 35) {
         this.name4 = this.getResult(var2);
      } else if (var1 == 36) {
         this.name5 = this.getResult(var2);
         this.notifyOtherModule("WIFI_NAME", this.name1 + this.name2 + this.name3 + this.name4 + this.name5);
         TboxWifiState.name = this.name1 + this.name2 + this.name3 + this.name4 + this.name5;
      } else if (var1 == 48) {
         this.password1 = this.getResult(var2);
      } else if (var1 == 49) {
         this.password2 = this.getResult(var2);
      } else if (var1 == 50) {
         this.password3 = this.getResult(var2);
         this.notifyOtherModule("WIFI_PASSWORD", this.password1 + this.password2 + this.password3);
         TboxWifiState.password = this.password1 + this.password2 + this.password3;
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      (new CountDownTimer(this, 5000L, 1000L) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            this.this$0.shareToLauncher();
         }

         public void onTick(long var1) {
         }
      }).start();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var6 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var6;
      if (var6[1] != 255) {
         switch (this.getMsDataType(var6)) {
            case -858993460:
               this.set0xCCCCCCCC(this.mCanBusInfoInt);
               break;
            case -572662307:
               this.setChargeDataNone(this.mCanBusInfoInt);
               break;
            case 217067496:
               this.set0X0CF02FE8(this.mCanBusInfoInt);
               break;
            case 415246324:
               this.setChargeData0x18C027F4(this.mCanBusInfoInt);
               break;
            case 415508468:
               this.setChargeData0x18C427F4(this.mCanBusInfoInt);
               break;
            case 418734568:
               this.set0x18F561E8(this.mCanBusInfoInt);
               break;
            case 418744650:
               this.setWifi0x18F5894A();
               break;
            case 418751769:
               this.setAir0x18F5A519(this.mCanBusInfoInt);
               break;
            case 418775335:
               this.setEco0x18F60127(this.mCanBusInfoInt);
               break;
            case 418777895:
               this.setEco0x18F60B27(this.mCanBusInfoInt);
               break;
            case 419322856:
               this.set0x18FE5BE8(this.mCanBusInfoInt);
               break;
            case 419358282:
               this.setTimer0x18FEE64A(this.mCanBusInfoInt);
               break;
            case 419373601:
               this.set0x18FF2221(this.mCanBusInfoInt);
         }

      } else {
         byte[] var7 = new byte[17];

         for(int var3 = 0; var3 < 17; ++var3) {
            var7[var3] = var2[var3 + 2];
         }

         try {
            if (LogUtil.log5()) {
               StringBuilder var9 = new StringBuilder();
               LogUtil.d(var9.append("canbusInfoChange: VIN=").append(FgeString.bytes2BcdString(var7)).toString());
            }

            String var10 = new String(var7);
            SystemProperties.set("vendor.sys.vin", var10);
            ShareDataManager var4 = ShareDataManager.getInstance();
            StringBuilder var8 = new StringBuilder();
            var4.reportString("can.bus.all.data.share", var8.append("{\"TAG\":\"VIN\",\"VALUE\":\"").append(var10).append("\"}").toString());
         } catch (Exception var5) {
            var5.printStackTrace();
         }

      }
   }

   protected int getMsDataType(int[] var1) {
      int var2 = var1[2];
      int var4 = var1[3];
      int var3 = var1[4];
      return var1[5] & 255 | (var2 & 255) << 24 | (var4 & 255) << 16 | (var3 & 255) << 8;
   }

   public String getVersionStr(byte[] var1) {
      int var3 = var1.length;
      byte[] var4 = new byte[var3];

      for(int var2 = 0; var2 < var3; ++var2) {
         var4[var2] = var1[var2 + 2];
      }

      return new String(var4);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).registerCanBusAirControlListener();
      this.mContext = var1;
      this.registerTboxWifi(var1);
      this.registerWifiState(var1);
      this.registerWifiStateOn(var1);
      this.registerBtState();
      this.registerUsbState(var1);
      this.registerGpsStatusListener();
      this.registerTaskObserver(var1);
      this.registerTboxTimerSwitch(var1);
      Mp5Time.getInstance().start();
      this.registerUiState();
   }

   // $FF: synthetic method
   void lambda$registerBtState$0$com_hzbhd_canbus_car__459_MsgMgr(String var1) {
      this.checkBtJson(var1);
   }

   // $FF: synthetic method
   Unit lambda$set0x18FF2221$2$com_hzbhd_canbus_car__459_MsgMgr() {
      if (!this.isLeft && !this.isRight) {
         ShareDataManager.getInstance().reportInt("user.Reverse", 0);
      }

      return null;
   }

   public void launcherHandshake() {
      this.shareToLauncher();
   }

   public void mcuErrorState(Context var1, byte[] var2) {
      super.mcuErrorState(var1, var2);
      this.mcuErrorStateInt = this.getByteArrayToIntArray(var2);
      if (LogUtil.log5()) {
         LogUtil.d("mcuErrorState(): " + var2);
      }

   }

   public void mcuHandshake() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
      OptionSettingsCmd459.getInstance().initState();
   }

   public void onAccOff() {
      super.onAccOff();
      this.mContext.unregisterReceiver(this.receiver);
      Mp5Time.getInstance().stop();
   }

   public void onAccOn() {
      super.onAccOn();
      this.mcuHandshake();
      OptionSettingsCmd459 var1 = OptionSettingsCmd459.getInstance();
      Boolean var2 = true;
      var1.setER(true);
      OptionSettingsCmd459.getInstance().setLD(var2);
      OptionSettingsCmd459.getInstance().setCW(var2);
      this.shareToLauncher();
      this.registerWifiState(this.mContext);
      this.registerWifiStateOn(this.mContext);
      this.registerTboxWifi(this.mContext);
      this.refreshTboxTimeSwitch();
      Mp5Time.getInstance().start();
   }

   public void onHandshake(Context var1) {
      this.mcuHandshake();
   }

   public void registerBtState() {
      this.checkBtJson(ShareDataManager.getInstance().registerShareStringListener("bluetooth.Status", new MsgMgr$$ExternalSyntheticLambda2(this)));
   }

   public void reverseStateChange(boolean var1) {
      super.reverseStateChange(var1);
      if (LogUtil.log5()) {
         LogUtil.d("reverseStateChange(): " + var1);
      }

      ShareDataManager.getInstance().reportString("can.bus.all.data.share", "{\"TAG\":\"REVERSE_STATUS\",\"VALUE\":\"" + var1 + "\"}");
   }

   public void sendMp5StateData(String var1, int var2) {
      try {
         JSONObject var3 = new JSONObject();
         var3.put("TAG", "MP5_Sts");
         var3.put("KEY", var1);
         var3.put("VALUE", var2);
         ShareDataManager.getInstance().reportString("can.request.data.share", var3.toString());
      } catch (Exception var4) {
      }

   }

   public void shareToLauncher() {
      (new SharedToLauncher()).syncStateToLauncher();
   }

   public void systemUiHandshake() {
      HashMap var1 = this.dataHash;
      if (var1 != null && var1.size() != 0) {
         this.notifyOtherModule("AUTO", this.dataHash.get("AUTO"));
         this.notifyOtherModule("FRONT_DEFOG", this.dataHash.get("FRONT_DEFOG"));
         this.notifyOtherModule("AC", this.dataHash.get("AC"));
         this.notifyOtherModule("AC_MAX", this.dataHash.get("AC_MAX"));
         this.notifyOtherModule("PTC", this.dataHash.get("PTC"));
         this.notifyOtherModule("CYCLE", this.dataHash.get("CYCLE"));
         this.notifyOtherModule("TEMP", this.dataHash.get("TEMP"));
         this.notifyOtherModule("WIND_MODE", this.dataHash.get("WIND_MODE"));
         this.notifyOtherModule("WIND_LEVEL", this.dataHash.get("WIND_LEVEL"));
      }

   }
}
