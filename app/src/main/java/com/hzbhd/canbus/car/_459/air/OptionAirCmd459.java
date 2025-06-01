package com.hzbhd.canbus.car._459.air;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;

public class OptionAirCmd459 {
   public boolean ac;
   public boolean acMax;
   public byte[] airCmd;
   public boolean auto;
   public boolean cycle;
   public boolean frontDefog;
   private boolean isEnable;
   public boolean power;
   public boolean ptc;
   public double temp;
   public int windLevel;
   public int windMode;

   private OptionAirCmd459() {
      this.airCmd = new byte[]{22, 12, -11, 112, 40, 0, 0, 0, 0, 0, 0, 0, 0, 1};
      this.auto = false;
      this.frontDefog = false;
      this.ac = false;
      this.power = false;
      this.acMax = false;
      this.ptc = false;
      this.cycle = false;
      this.temp = 18.0;
      this.windMode = 0;
      this.windLevel = 0;
      this.isEnable = false;
   }

   // $FF: synthetic method
   OptionAirCmd459(Object var1) {
      this();
   }

   public static OptionAirCmd459 getInstance() {
      return OptionAirCmd459.Holder.INSTANCE;
   }

   private void optionCmdAndSend() {
      byte var7 = this.auto;
      boolean var10 = this.power;
      byte var6 = 1;
      byte var1;
      if (var10) {
         var1 = 1;
      } else {
         var1 = 2;
      }

      int var8 = (int)(this.temp * 2.0);
      int var2 = this.windMode;
      int var9 = DataHandleUtils.getMsbLsbResult_4bit(this.windLevel, var2);
      byte var12;
      if (this.ac) {
         var12 = 1;
      } else {
         var12 = 2;
      }

      byte var3;
      if (this.ptc) {
         var3 = 1;
      } else {
         var3 = 2;
      }

      byte var4;
      if (this.cycle) {
         var4 = 1;
      } else {
         var4 = 2;
      }

      byte var5;
      if (this.frontDefog) {
         var5 = 1;
      } else {
         var5 = 2;
      }

      if (!this.acMax) {
         var6 = 2;
      }

      byte[] var11 = this.airCmd;
      var11[5] = (byte)(var7 & 255 | (var1 & 255) << 2);
      var11[6] = (byte)var8;
      var11[7] = (byte)var9;
      var11[8] = (byte)(var12 & 255 | (var3 & 255) << 2 | (var4 & 255) << 4 | (var5 & 255) << 6);
      var11[9] = (byte)var6;
      CanbusMsgSender.sendMsg(var11);
   }

   public void controlOptionCmd() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         boolean var1;
         boolean var10001;
         try {
            var1 = this.isEnable;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label78;
         }

         if (!var1) {
            return;
         }

         try {
            this.optionCmdAndSend();
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         return;
      }

      Throwable var2 = var10000;
      throw var2;
   }

   public void enableCycle(boolean var1) {
      this.isEnable = var1;
      this.controlOptionCmd();
   }

   private static class Holder {
      private static final OptionAirCmd459 INSTANCE = new OptionAirCmd459();
   }
}
