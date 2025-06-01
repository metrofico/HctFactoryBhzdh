package com.hzbhd.canbus.control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.ActionDo;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAllDataShare;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.bhdGsonUtils;
import com.hzbhd.constant.share.canbus.CanbusAirShare;
import com.hzbhd.constant.share.canbus.CanbusDoorShare;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.util.LogUtil;
import java.util.List;

public class CanbusInfoChangeListener {
   private static final String TAG = "CanbusInfoChangeListener";
   private static CanbusInfoChangeListener instance;
   private static int nowCarSpeed;
   private static int nowLeftLampState;
   private static int nowLeftRightLampState;
   private static int nowRightLampState;
   private ThisCountDownTimer leftTimer;
   private CanbusAirShare mCanbusAirShare = new CanbusAirShare();
   private CanbusDoorShare mCanbusDoorShare = new CanbusDoorShare();
   private boolean mIsLeftFrontDoorOpen;
   private boolean mIsLeftRearDoorOpen;
   private boolean mIsRightFrontDoorOpen;
   private boolean mIsRightRearDoorOpen;
   private String mVoiceBroadcastInfo = null;
   private String nowMsBasicInfoStrJson;
   private ThisCountDownTimer rightTimer;

   private CanbusInfoChangeListener() {
      this.registerSetShareListener();
   }

   private void ShareLampState() {
      int var1 = DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, 0, nowRightLampState, nowLeftLampState);
      nowLeftRightLampState = var1;
      ShareDataServiceImpl.setInt("canbus.lamp.turn.left.right.state", var1);
   }

   private void checkDoorSwitch(Context var1) {
      if (!((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getDoorSwapFront()) {
         this.mIsLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
         this.mIsRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
      } else {
         this.mIsLeftFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
         this.mIsRightFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
      }

      if (!((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getDoorSwapRear()) {
         this.mIsLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
         this.mIsRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
      } else {
         this.mIsLeftRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
         this.mIsRightRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
      }

   }

   public static CanbusInfoChangeListener getInstance() {
      if (instance == null) {
         instance = new CanbusInfoChangeListener();
      }

      return instance;
   }

   private void registerSetShareListener() {
      ShareDataServiceImpl.init(SourceConstantsDef.MODULE_ID.CANBUS);
      ShareDataServiceImpl.addShareListeners(new ShareDataServiceImpl.ShareListeners(this) {
         final CanbusInfoChangeListener this$0;

         {
            this.this$0 = var1;
         }

         public int getInt(String var1) {
            byte var2;
            label28: {
               var1.hashCode();
               switch (var1) {
                  case "canbus.lamp.turn.left.right.state":
                     var2 = 0;
                     break label28;
                  case "canbus.speedInfo":
                     var2 = 1;
                     break label28;
                  case "canbus.Angle":
                     var2 = 2;
                     break label28;
               }

               var2 = -1;
            }

            switch (var2) {
               case 0:
                  return CanbusInfoChangeListener.nowLeftRightLampState;
               case 1:
                  return CanbusInfoChangeListener.nowCarSpeed;
               case 2:
                  return GeneralParkData.trackAngle;
               default:
                  return -1;
            }
         }

         public String getString(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case -1479829393:
                  if (var1.equals("canbus.radarLeft")) {
                     var2 = 0;
                  }
                  break;
               case -1465152947:
                  if (var1.equals("canbus.ms.basic.json.string")) {
                     var2 = 1;
                  }
                  break;
               case -1238372634:
                  if (var1.equals("canbus.canVersion")) {
                     var2 = 2;
                  }
                  break;
               case -936718623:
                  if (var1.equals("canbus.RadarFront")) {
                     var2 = 3;
                  }
                  break;
               case -766218184:
                  if (var1.equals("can.request.data.share")) {
                     var2 = 4;
                  }
                  break;
               case 421249466:
                  if (var1.equals("canbus.AirInfo")) {
                     var2 = 5;
                  }
                  break;
               case 430645453:
                  if (var1.equals("canbus.VoiceBroadcast")) {
                     var2 = 6;
                  }
                  break;
               case 524317164:
                  if (var1.equals("canbus.RadarRear")) {
                     var2 = 7;
                  }
                  break;
               case 545518356:
                  if (var1.equals("can.bus.all.data.share")) {
                     var2 = 8;
                  }
                  break;
               case 1375590068:
                  if (var1.equals("canbus.radarRight")) {
                     var2 = 9;
                  }
                  break;
               case 1717379542:
                  if (var1.equals("canbus.outdoorTemperature")) {
                     var2 = 10;
                  }
                  break;
               case 2088232218:
                  if (var1.equals("canbus.DoorInfo")) {
                     var2 = 11;
                  }
            }

            switch (var2) {
               case 0:
                  return RadarInfoUtil.getRadarLeftShareInfo();
               case 1:
                  return this.this$0.nowMsBasicInfoStrJson;
               case 2:
                  return GeneralData.INSTANCE.getCanVersion();
               case 3:
                  return RadarInfoUtil.getRadarFrontShareInfo();
               case 4:
                  return "[]";
               case 5:
                  return bhdGsonUtils.toJson(this.this$0.mCanbusAirShare);
               case 6:
                  return this.this$0.mVoiceBroadcastInfo;
               case 7:
                  return RadarInfoUtil.getRadarRearShareInfo();
               case 8:
                  return GeneralAllDataShare.canJsonData;
               case 9:
                  return RadarInfoUtil.getRadarRightShareInfo();
               case 10:
                  return GeneralAirData.outdoorTemperature;
               case 11:
                  return bhdGsonUtils.toJson(this.this$0.mCanbusDoorShare);
               default:
                  return null;
            }
         }
      });
   }

   public void reportAirInfo(Context var1) {
      int var2 = CommUtil.isAirTemperatureSwitch(var1);
      this.mCanbusAirShare.setWind_speed(GeneralAirData.front_wind_level);
      CanbusAirShare var3 = this.mCanbusAirShare;
      String var4;
      if (var2 == 1) {
         var4 = GeneralAirData.front_right_temperature;
      } else {
         var4 = GeneralAirData.front_left_temperature;
      }

      var3.setLeft_temperature(var4);
      var3 = this.mCanbusAirShare;
      if (var2 == 1) {
         var4 = GeneralAirData.front_left_temperature;
      } else {
         var4 = GeneralAirData.front_right_temperature;
      }

      var3.setRight_temperature(var4);
      this.mCanbusAirShare.setBlow_window(GeneralAirData.front_left_blow_window);
      this.mCanbusAirShare.setBlow_head(GeneralAirData.front_left_blow_head);
      this.mCanbusAirShare.setBlow_foot(GeneralAirData.front_left_blow_foot);
      this.mCanbusAirShare.setFront_defog(GeneralAirData.front_defog);
      this.mCanbusAirShare.setAc(GeneralAirData.ac);
      this.mCanbusAirShare.setDual(GeneralAirData.dual);
      this.mCanbusAirShare.setAuto(GeneralAirData.auto);
      this.mCanbusAirShare.setRear_defog(GeneralAirData.rear_defog);
      this.mCanbusAirShare.setPower(GeneralAirData.power);
      this.mCanbusAirShare.setIn_out_cycle(GeneralAirData.in_out_cycle);
      var4 = bhdGsonUtils.toJson(this.mCanbusAirShare);
      if (LogUtil.log5()) {
         LogUtil.d("<reportAirInfo> " + var4);
      }

      ShareDataServiceImpl.setString("canbus.AirInfo", var4);
   }

   public void reportAllCanBusData(String var1) {
      GeneralAllDataShare.canJsonData = var1;
      ShareDataServiceImpl.setString("can.bus.all.data.share", var1);
   }

   public void reportDoorInfo(Context var1) {
      this.checkDoorSwitch(var1);
      this.mCanbusDoorShare.setDoor_left_front(this.mIsLeftFrontDoorOpen);
      this.mCanbusDoorShare.setDoor_right_front(this.mIsRightFrontDoorOpen);
      this.mCanbusDoorShare.setDoor_left_rear(this.mIsLeftRearDoorOpen);
      this.mCanbusDoorShare.setDoor_right_rear(this.mIsRightRearDoorOpen);
      this.mCanbusDoorShare.setDoor_front(GeneralDoorData.isFrontOpen);
      this.mCanbusDoorShare.setDoor_back(GeneralDoorData.isBackOpen);
      String var2 = bhdGsonUtils.toJson(this.mCanbusDoorShare);
      if (LogUtil.log5()) {
         LogUtil.d("<reportDoorInfo> " + var2);
      }

      ShareDataServiceImpl.setString("canbus.DoorInfo", var2);
   }

   public void reportLeftLampInfo(boolean var1) {
      if (this.leftTimer == null) {
         this.leftTimer = new ThisCountDownTimer(this, new ActionDo(this) {
            final CanbusInfoChangeListener this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusInfoChangeListener.nowLeftLampState = 0;
               this.this$0.ShareLampState();
               Log.e("LampState", "exit left lamp");
            }
         });
      }

      if (var1) {
         this.leftTimer.remove(699050);
         nowLeftLampState = 1;
         this.ShareLampState();
         Log.e("LampState", "turn left lamp");
      } else {
         this.leftTimer.start(699050, 1500);
      }

   }

   public void reportMsAirInfo(String var1) {
      ShareDataServiceImpl.setString("canbus.ms.air.json.string", var1);
   }

   public void reportMsBasicInfo(String var1) {
      this.nowMsBasicInfoStrJson = var1;
      ShareDataServiceImpl.setString("canbus.ms.basic.json.string", var1);
   }

   public void reportOutdoorTemperature() {
      this.reportOutdoorTemperature(GeneralAirData.outdoorTemperature);
   }

   public void reportOutdoorTemperature(String var1) {
      ShareDataServiceImpl.setString("canbus.outdoorTemperature", CommUtil.temperatureUnitSwitch(var1));
   }

   public void reportRightLampInfo(boolean var1) {
      if (this.rightTimer == null) {
         this.rightTimer = new ThisCountDownTimer(this, new ActionDo(this) {
            final CanbusInfoChangeListener this$0;

            {
               this.this$0 = var1;
            }

            public void todo(List var1) {
               CanbusInfoChangeListener.nowRightLampState = 0;
               this.this$0.ShareLampState();
               Log.e("LampState", "exit right lamp");
            }
         });
      }

      if (var1) {
         this.rightTimer.remove(48059);
         nowRightLampState = 1;
         this.ShareLampState();
         Log.e("LampState", "turn right lamp");
      } else {
         this.rightTimer.start(48059, 1500);
      }

   }

   public void reportSpeedInfo(int var1) {
      nowCarSpeed = var1;
      ShareDataServiceImpl.setInt("canbus.speedInfo", var1);
   }

   public void reportString(String var1, String var2) {
      ShareDataServiceImpl.setString(var1, var2);
   }

   public void reportVoiceBroadcast(String var1) {
      if (LogUtil.log5()) {
         LogUtil.d("<reportVoiceBroadcast> " + var1);
      }

      this.mVoiceBroadcastInfo = var1;
      ShareDataServiceImpl.setString("canbus.VoiceBroadcast", var1);
   }

   public void setOutdoorTemperatureVisible(boolean var1) {
      if (var1) {
         this.reportOutdoorTemperature();
      } else {
         this.reportOutdoorTemperature("");
      }

   }

   public class ThisCountDownTimer {
      private int ACTION_TAG;
      private Handler mHandler;
      final CanbusInfoChangeListener this$0;
      private ActionDo thisActionDo;

      public ThisCountDownTimer(CanbusInfoChangeListener var1, ActionDo var2) {
         this.this$0 = var1;
         this.ACTION_TAG = 255;
         this.mHandler = new Handler(this, Looper.getMainLooper()) {
            final ThisCountDownTimer this$1;

            {
               this.this$1 = var1;
            }

            public void handleMessage(Message var1) {
               super.handleMessage(var1);
               if (var1.what == this.this$1.ACTION_TAG) {
                  this.this$1.thisActionDo.todo((List)null);
               }

            }
         };
         this.thisActionDo = var2;
      }

      public void remove(int var1) {
         this.ACTION_TAG = var1;
         this.mHandler.removeMessages(var1);
      }

      public void start(int var1, int var2) {
         this.ACTION_TAG = var1;
         this.mHandler.obtainMessage().what = this.ACTION_TAG;
         this.mHandler.sendEmptyMessageDelayed(this.ACTION_TAG, (long)var2);
      }
   }
}
