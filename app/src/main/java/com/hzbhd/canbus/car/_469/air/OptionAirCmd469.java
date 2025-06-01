package com.hzbhd.canbus.car._469.air;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;

public class OptionAirCmd469 {
   public byte[] airCmd;
   public int compRunSt;
   public float defrstStartTempSet;
   public float defrstStopTempSet;
   public int defrstTimeCtrl;
   public int defrstTimeSet;
   public int longRangeCtrl;
   public float setTemp;
   public int sysRunCtrl;
   public float tempCtrlBckPoorSet;

   private OptionAirCmd469() {
      this.airCmd = new byte[]{22, 24, -1, -88, -5, 0, 0, 0, 0, 0, 0, 0, 0, 1};
      this.setTemp = 0.0F;
      this.compRunSt = 0;
      this.sysRunCtrl = 0;
      this.defrstTimeCtrl = 0;
      this.defrstStopTempSet = 0.0F;
      this.defrstStartTempSet = 0.0F;
      this.defrstTimeSet = 0;
      this.tempCtrlBckPoorSet = 2.0F;
      this.longRangeCtrl = 0;
   }

   // $FF: synthetic method
   OptionAirCmd469(Object var1) {
      this();
   }

   public static OptionAirCmd469 getInstance() {
      return OptionAirCmd469.Holder.INSTANCE;
   }

   private void optionCmdAndSend() {
      int var2 = (int)((this.setTemp + 30.0F) * 2.0F);
      int var3 = this.compRunSt;
      int var1 = 0;
      var3 = DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntFromByteWithBit(0, var3, 0, 2), this.sysRunCtrl, 6, 2);
      int var4 = this.defrstTimeCtrl;
      if (var4 != 0) {
         var1 = (var4 - 15) / 15;
      }

      int var6 = (int)((this.defrstStopTempSet + 30.0F) * 2.0F);
      int var5 = (int)((this.defrstStartTempSet + 30.0F) * 2.0F);
      int var8 = this.defrstTimeSet;
      var4 = (int)this.tempCtrlBckPoorSet;
      int var7 = this.longRangeCtrl;
      byte[] var9 = this.airCmd;
      var9[5] = (byte)var2;
      var9[6] = (byte)var3;
      var9[7] = (byte)var1;
      var9[8] = (byte)var6;
      var9[9] = (byte)var5;
      var9[10] = (byte)(var8 - 3);
      var9[11] = (byte)var4;
      var9[12] = (byte)var7;
      CanbusMsgSender.sendMsg(var9);
   }

   public void controlOptionCmd() {
      synchronized(this){}

      try {
         this.optionCmdAndSend();
      } finally {
         ;
      }

   }

   private static class Holder {
      private static final OptionAirCmd469 INSTANCE = new OptionAirCmd469();
   }
}
