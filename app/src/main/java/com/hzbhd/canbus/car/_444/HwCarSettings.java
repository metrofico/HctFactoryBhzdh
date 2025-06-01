package com.hzbhd.canbus.car._444;

import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class HwCarSettings {
   public static final String ACCELERATOR_PERFORMANCE = "ACCELERATOR_PERFORMANCE";
   public static final String AUXILIARY_BRAKING = "AUXILIARY_BRAKING";
   public static final String BATTERY_WATER = "BATTERY_WATER";
   public static final String ENERGY_RECOVERY = "ENERGY_RECOVERY";
   public static final String LOCK = "LOCK";
   public static final String MOTOR_WATER = "MOTOR_WATER";
   public static final String REVERSE_SPEED = "REVERSE_SPEED";
   public static final String SPEED = "SPEED";
   public static final String VCU = "VCU";
   public static final String WARM_AIR_WATER = "WARM_AIR_WATER";
   public static final String WORK_MOTOR_WATER = "WORK_MOTOR_WATER";
   private static int acceleratorPerformance;
   private static int auxiliaryBraking;
   private static int batteryWater;
   private static int energyRecovery;
   private static int lock;
   private static int motorWater;
   private static int reverseSpeed;
   private static int sVcu;
   private static int speed;
   private static int warmAirWater;
   private static int workMotorWater;

   private void cheData(String var1, int var2) {
      var1.hashCode();
      int var4 = var1.hashCode();
      byte var3 = -1;
      switch (var4) {
         case -2063376356:
            if (var1.equals("ACCELERATOR_PERFORMANCE")) {
               var3 = 0;
            }
            break;
         case -1129806811:
            if (var1.equals("BATTERY_WATER")) {
               var3 = 1;
            }
            break;
         case -255121313:
            if (var1.equals("WORK_MOTOR_WATER")) {
               var3 = 2;
            }
            break;
         case 84808:
            if (var1.equals("VCU")) {
               var3 = 3;
            }
            break;
         case 2342187:
            if (var1.equals("LOCK")) {
               var3 = 4;
            }
            break;
         case 79104039:
            if (var1.equals("SPEED")) {
               var3 = 5;
            }
            break;
         case 96130671:
            if (var1.equals("AUXILIARY_BRAKING")) {
               var3 = 6;
            }
            break;
         case 122573676:
            if (var1.equals("ENERGY_RECOVERY")) {
               var3 = 7;
            }
            break;
         case 368052328:
            if (var1.equals("WARM_AIR_WATER")) {
               var3 = 8;
            }
            break;
         case 1783083114:
            if (var1.equals("REVERSE_SPEED")) {
               var3 = 9;
            }
            break;
         case 2032737421:
            if (var1.equals("MOTOR_WATER")) {
               var3 = 10;
            }
      }

      switch (var3) {
         case 0:
            acceleratorPerformance = var2;
            this.sendCmd();
            break;
         case 1:
            batteryWater = var2;
            this.sendCmd();
            break;
         case 2:
            workMotorWater = var2;
            this.sendCmd();
            break;
         case 3:
            sVcu = var2;
            this.sendCmd();
            break;
         case 4:
            lock = var2;
            this.sendCmd();
            break;
         case 5:
            speed = var2;
            this.sendCmd();
            break;
         case 6:
            auxiliaryBraking = var2;
            this.sendCmd();
            break;
         case 7:
            energyRecovery = var2;
            this.sendCmd();
            break;
         case 8:
            warmAirWater = var2;
            this.sendCmd();
            break;
         case 9:
            reverseSpeed = var2;
            this.sendCmd();
            break;
         case 10:
            motorWater = var2;
            this.sendCmd();
      }

   }

   private void sendCmd() {
      Log.d("SWITCH_STATE", "-----------------------------------------------------------");
      StringBuilder var15 = (new StringBuilder()).append("sVcu=");
      boolean var14;
      if (sVcu != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("acceleratorPerformance=");
      if (acceleratorPerformance != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("energyRecovery=");
      if (energyRecovery != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("auxiliaryBraking=");
      if (auxiliaryBraking != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("motorWater=");
      if (motorWater != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("batteryWater=");
      if (batteryWater != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("workMotorWater=");
      if (workMotorWater != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("warmAirWater=");
      if (warmAirWater != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("reverseSpeed=");
      if (reverseSpeed != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("speed=");
      if (speed != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      var15 = (new StringBuilder()).append("lock=");
      if (lock != -1) {
         var14 = true;
      } else {
         var14 = false;
      }

      Log.d("SWITCH_STATE", var15.append(var14).toString());
      int var6 = sVcu;
      if (var6 != -1) {
         int var9 = acceleratorPerformance;
         if (var9 != -1) {
            int var7 = energyRecovery;
            if (var7 != -1) {
               int var8 = auxiliaryBraking;
               if (var8 != -1) {
                  int var12 = motorWater;
                  if (var12 != -1) {
                     int var11 = batteryWater;
                     if (var11 != -1) {
                        int var10 = workMotorWater;
                        if (var10 != -1) {
                           int var13 = warmAirWater;
                           if (var13 != -1) {
                              int var5 = reverseSpeed;
                              if (var5 != -1 && speed != -1 && lock != -1) {
                                 var11 = DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, var13, var10, var11, var12);
                                 var12 = speed;
                                 var10 = lock;
                                 byte var2 = (byte)(var6 & 255 | (var9 & 255) << 1 | (var7 & 255) << 4 | (var8 & 255) << 6);
                                 byte var3 = (byte)((var5 & 255) << 2);
                                 byte var4 = (byte)var11;
                                 byte var1 = (byte)0;
                                 CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, -13, 23, var2, var3, var4, var1, var1, (byte)var12, (byte)var10});
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public void setData(String var1) {
      try {
         JSONObject var2 = new JSONObject(var1);
         this.cheData(var2.getString("TAG"), Integer.parseInt(var2.getString("VALUE")));
      } catch (JSONException var3) {
      }

   }
}
