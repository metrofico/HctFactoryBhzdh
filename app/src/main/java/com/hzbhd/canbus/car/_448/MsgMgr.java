package com.hzbhd.canbus.car._448;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._448.air.AirData;
import com.hzbhd.canbus.car._448.dvr.DvrDataKAdapter;
import com.hzbhd.canbus.car._448.speech.SpeechAction;
import com.hzbhd.canbus.car._448.speech.SpeechReceive;
import com.hzbhd.canbus.car._448.speech.SpeechSend;
import com.hzbhd.canbus.car_cus._448.DvrController;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;

public class MsgMgr extends AbstractMsgMgr implements DvrDataInterface {
   boolean bHood = false;
   boolean bLeftFront = false;
   boolean bLeftRear = false;
   boolean bRightFront = false;
   boolean bRightRear = false;
   boolean bTrunk = false;
   private Context context;
   int[] mCanBusInfoInt;
   private int speed = 0;

   private int getDataType(int[] var1) {
      int var4 = var1[2];
      int var2 = var1[3];
      int var3 = var1[4];
      return var1[5] & 255 | (var4 & 255) << 24 | (var2 & 255) << 16 | (var3 & 255) << 8;
   }

   private String getSwitch(boolean var1) {
      return var1 ? "on" : "off";
   }

   private void setAir0x30D(int[] var1) {
      StringBuilder var5 = (new StringBuilder()).append("WindLevel=");
      int var2 = var1[11];
      boolean var4 = false;
      Log.d("Air", var5.append(DataHandleUtils.getIntFromByteWithBit(var2, 0, 4)).toString());
      AirData.wind_level = DataHandleUtils.getIntFromByteWithBit(var1[11], 0, 4);
      SpeechSend.windChange(DataHandleUtils.getIntFromByteWithBit(var1[11], 0, 4));
      Log.d("Air", "Temp=" + (var1[8] + 16));
      AirData.temp_value = var1[8] + 16;
      SpeechSend.tempChange(var1[8] + 16);
      boolean var3 = DataHandleUtils.getBoolBit2(var1[7]) ^ true;
      SpeechSend.acStatus(var3);
      Log.d("Air", "Ac   isAcOn=" + var3);
      var2 = DataHandleUtils.getIntFromByteWithBit(var1[12], 5, 3);
      if (var2 == 0) {
         SpeechSend.acMode(SpeechAction.WindValueEnum.foot);
         Log.d("Air", "Mode foot");
      } else if (var2 == 1) {
         SpeechSend.acMode(SpeechAction.WindValueEnum.face);
         Log.d("Air", "Mode face");
      } else if (var2 == 2) {
         SpeechSend.acMode(SpeechAction.WindValueEnum.facefoot);
         Log.d("Air", "Mode facefoot");
      } else if (var2 == 3) {
         SpeechSend.acMode(SpeechAction.WindValueEnum.footdefrost);
         Log.d("Air", "Mode footWindow");
      } else if (var2 == 4) {
         SpeechSend.acMode(SpeechAction.WindValueEnum.defrost);
         Log.d("Air", "Mode Window");
      }

      if (var2 == 4) {
         var3 = true;
      } else {
         var3 = false;
      }

      SpeechSend.frontDefrost(var3);
      var5 = (new StringBuilder()).append("FrontDefog  isOn=");
      var3 = var4;
      if (var2 == 4) {
         var3 = true;
      }

      Log.d("Air", var5.append(var3).toString());
      var3 = DataHandleUtils.getBoolBit1(var1[7]);
      SpeechSend.behindDefrost(var3);
      Log.d("Air", "RearDefog=" + var3);
      var3 = DataHandleUtils.getBoolBit4(var1[12]) ^ true;
      SpeechSend.acLoop(var3);
      Log.d("Air", "Cycle=" + var3);
   }

   private void setDoor0x392(int[] var1) {
      this.bLeftFront = DataHandleUtils.getBoolBit4(var1[8]);
      this.bRightFront = DataHandleUtils.getBoolBit5(var1[8]);
      this.bLeftRear = DataHandleUtils.getBoolBit6(var1[8]);
      this.bRightRear = DataHandleUtils.getBoolBit7(var1[8]);
      this.bTrunk = DataHandleUtils.getBoolBit0(var1[9]);
      this.shareDoor();
      boolean var2 = DataHandleUtils.getBoolBit0(var1[7]);
      boolean var3 = DataHandleUtils.getBoolBit3(var1[8]);
      String var5 = "none";
      String var4;
      if (var2 && var3) {
         var4 = "double.flash";
      } else {
         var4 = "none";
      }

      String var6 = var4;
      if (!var2) {
         var6 = var4;
         if (var3) {
            var6 = "turn.right";
         }
      }

      var4 = var6;
      if (var2) {
         var4 = var6;
         if (!var3) {
            var4 = "turn.left";
         }
      }

      if (!var2 && !var3) {
         var6 = var5;
      } else {
         var6 = var4;
      }

      ShareDataServiceImpl.setString("canbus.sld.turn.signals", var6);
   }

   private void setEsp0x220(byte[] var1) {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[9], var1[10], 0, 780, 16);
      ShareDataServiceImpl.setString("canbus.sld.esp.angle", String.valueOf(GeneralParkData.trackAngle));
   }

   private void setHood0x365(int[] var1) {
      this.bHood = DataHandleUtils.getBoolBit6(var1[11]);
      this.shareDoor();
   }

   private void setRadar0x384(int[] var1) {
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[7], 3, 5);
      int var4 = (DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 1) & 255) << 4 | DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 4) & 255;
      byte var6 = 10;
      int var9 = DataHandleUtils.getIntFromByteWithBit(var1[10], 2, 5);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[9], 0, 4);
      int var8 = DataHandleUtils.getIntFromByteWithBit(var1[10], 7, 1) & 255 | (var2 & 255) << 1;
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[11], 0, 5);
      var2 = DataHandleUtils.getIntFromByteWithBit(var1[10], 0, 2);
      int var7 = DataHandleUtils.getIntFromByteWithBit(var1[11], 5, 3) & 255 | (var2 & 255) << 3;
      var2 = var3;
      if (var3 != 0) {
         var2 = var3 / 3 + 1;
      }

      var3 = var2;
      if (var2 > 10) {
         var3 = 10;
      }

      var2 = var4;
      if (var4 != 0) {
         var2 = var4 / 3 + 1;
      }

      var4 = var2;
      if (var2 > 10) {
         var4 = 10;
      }

      ShareDataServiceImpl.setString("canbus.sld.front.radar.json", "{\"left\"=" + var3 + ",\"leftMid\"=0,\"rightMid\"=0,\"right\"=" + var4 + "}");
      var2 = var9;
      if (var9 != 0) {
         var2 = var9 / 3 + 1;
      }

      var3 = var2;
      if (var2 > 10) {
         var3 = 10;
      }

      var2 = var8;
      if (var8 != 0) {
         var2 = var8 / 3 + 1;
      }

      var4 = var2;
      if (var2 > 10) {
         var4 = 10;
      }

      var2 = var5;
      if (var5 != 0) {
         var2 = var5 / 3 + 1;
      }

      var5 = var2;
      if (var2 > 10) {
         var5 = 10;
      }

      var2 = var7;
      if (var7 != 0) {
         var2 = var7 / 3 + 1;
      }

      if (var2 > 10) {
         var2 = var6;
      }

      ShareDataServiceImpl.setString("canbus.sld.rear.radar.json", "{\"left\"=" + var4 + ",\"leftMid\"=" + var3 + ",\"rightMid\"=" + var2 + ",\"right\"=" + var5 + "}");
   }

   private void setSpeedInfo(int[] var1) {
      if (this.speed != DataHandleUtils.getIntFromByteWithBit(var1[10], 4, 4)) {
         int var2 = DataHandleUtils.getIntFromByteWithBit(var1[10], 4, 4);
         this.speed = var2;
         ShareDataServiceImpl.setString("canbus.sld.car.speed", String.valueOf((double)var2 * 0.05625));
      }

   }

   private void shareDoor() {
      ShareDataServiceImpl.setString("canbus.sld.door.info.json", "{\"leftFront\":\"" + this.getSwitch(this.bLeftFront) + "\",\"rightFront\":\"" + this.getSwitch(this.bRightFront) + "\",\"leftRear\":\"" + this.getSwitch(this.bLeftRear) + "\",\"rightRear\":\"" + this.getSwitch(this.bRightRear) + "\",\"trunk\":\"" + this.getSwitch(this.bTrunk) + "\",\"hood\":\"" + this.getSwitch(this.bHood) + "\"}");
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = this.getDataType(var4);
      if (var3 != 417) {
         if (var3 != 544) {
            if (var3 != 781) {
               if (var3 != 869) {
                  if (var3 != 900) {
                     if (var3 == 914) {
                        this.setDoor0x392(this.mCanBusInfoInt);
                     }
                  } else {
                     this.setRadar0x384(this.mCanBusInfoInt);
                  }
               } else {
                  this.setHood0x365(this.mCanBusInfoInt);
               }
            } else {
               this.setAir0x30D(this.mCanBusInfoInt);
            }
         } else {
            this.setEsp0x220(var2);
         }
      } else {
         this.setSpeedInfo(this.mCanBusInfoInt);
      }

   }

   public void dataChange(String var1) {
      if (var1.equals("speech.start.dvr") && this.isNotDvrActivity()) {
         this.startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
      }

      if (var1.equals("speech.take.picture")) {
         if (this.isNotDvrActivity()) {
            this.startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
         }

         if (DvrData.systemMode == 1) {
            if (DvrData.videoStateIcon == 3 || DvrData.systemMode == 1 && DvrData.videoStateIcon == 4) {
               DvrSender.send(new byte[]{64, 35});
               DvrSender.send(new byte[]{64, 35});
               DvrSender.send(new byte[]{92, -1});
            } else {
               DvrSender.send(new byte[]{64, 35});
               DvrSender.send(new byte[]{92, -1});
            }

            DvrData.systemMode = 2;
            DvrObserver.getInstance().dataChange("video.state.mode");
            DvrSender.send(new byte[]{64, 37});
         }
      }

      if (var1.equals("speech.record.start")) {
         if (this.isNotDvrActivity()) {
            this.startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
         }

         if (DvrData.systemMode == 1) {
            if (DvrData.videoStateIcon == 3 || DvrData.systemMode == 1 && DvrData.videoStateIcon == 4) {
               DvrSender.send(new byte[]{64, 35});
               DvrSender.send(new byte[]{64, 35});
               DvrSender.send(new byte[]{92, -1});
            } else {
               DvrSender.send(new byte[]{64, 35});
               DvrSender.send(new byte[]{92, -1});
            }

            if (DvrData.videoStateIcon != 1) {
               DvrSender.send(new byte[]{64, 36});
            }
         }
      }

      if (var1.equals("speech.record.stop")) {
         if (this.isNotDvrActivity()) {
            this.startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
         }

         if (DvrData.videoStateIcon == 3 || DvrData.systemMode == 1 && DvrData.videoStateIcon == 4) {
            DvrSender.send(new byte[]{64, 35});
            DvrSender.send(new byte[]{64, 35});
            DvrSender.send(new byte[]{92, -1});
         } else {
            DvrSender.send(new byte[]{64, 35});
            DvrSender.send(new byte[]{92, -1});
         }

         if (DvrData.videoStateIcon == 1) {
            DvrSender.send(new byte[]{64, 36});
         }
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      DvrSender.send(new byte[]{88, 1, (byte)(var1 - 2000), (byte)var3, (byte)var4});
      DvrSender.send(new byte[]{89, 1, (byte)var5, (byte)var6, (byte)var7});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.context = var1;
      DvrDataKAdapter.INSTANCE.registerOnDataReadCallback(new DvrDataKAdapter.OnDvrDataReadListener(this, var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onDataRead(byte[] var1) {
            DvrController.getInstance().setData(this.val$context, this.this$0.getByteArrayToIntArray(var1));
         }
      });
      SpeechReceive.Companion.get().register(var1);
      DvrObserver.getInstance().register(new MsgMgr$$ExternalSyntheticLambda0(this));
   }

   public boolean isNotDvrActivity() {
      return !((ActivityManager.RunningTaskInfo)((ActivityManager)this.context.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getClassName().equals("com.hzbhd.canbus.car_cus._448.activity.DvrActivity");
   }
}
