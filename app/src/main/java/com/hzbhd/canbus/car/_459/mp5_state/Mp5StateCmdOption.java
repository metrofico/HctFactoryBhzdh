package com.hzbhd.canbus.car._459.mp5_state;

import com.hzbhd.canbus.CanbusMsgSender;

public class Mp5StateCmdOption {
   public int data0bit0_bit3;
   public int data0bit4_bit5;
   public int data0bit6_bit7;
   public int data1bit0_bit1;
   public int data1bit2_bit3;
   public int data1bit4_bit5;
   public int data1bit6_bit7;
   public int data2bit0_bit1;
   public int data2bit2_bit3;
   public int data2bit4_bit5;
   public int data2bit6_bit7;
   public int data3bit0_bit1;
   public int data3bit2_bit3;
   public int data3bit4_bit5;
   public int data3bit6_bit7;
   public int data4bit0_bit1;
   public int data4bit2_bit3;
   public int data4bit4_bit5;
   public int data4bit6_bit7;
   public int data5bit0_bit1;
   public int data5bit2_bit3;
   public int data5bit4_bit5;
   public int data5bit6_bit7;
   public int data6;
   public int data7;

   private Mp5StateCmdOption() {
      this.data0bit0_bit3 = 0;
      this.data0bit4_bit5 = 3;
      this.data0bit6_bit7 = 3;
      this.data1bit0_bit1 = 3;
      this.data1bit2_bit3 = 3;
      this.data1bit4_bit5 = 3;
      this.data1bit6_bit7 = 3;
      this.data2bit0_bit1 = 3;
      this.data2bit2_bit3 = 3;
      this.data2bit4_bit5 = 2;
      this.data2bit6_bit7 = 2;
      this.data3bit0_bit1 = 0;
      this.data3bit2_bit3 = 0;
      this.data3bit4_bit5 = 0;
      this.data3bit6_bit7 = 3;
      this.data4bit0_bit1 = 3;
      this.data4bit2_bit3 = 3;
      this.data4bit4_bit5 = 3;
      this.data4bit6_bit7 = 3;
      this.data5bit0_bit1 = 3;
      this.data5bit2_bit3 = 3;
      this.data5bit4_bit5 = 0;
      this.data5bit6_bit7 = 0;
      this.data6 = 255;
      this.data7 = 255;
   }

   // $FF: synthetic method
   Mp5StateCmdOption(Object var1) {
      this();
   }

   public static Mp5StateCmdOption getInstance() {
      return Mp5StateCmdOption.Holder.INSTANCE;
   }

   public void send() {
      int var16 = this.data0bit6_bit7;
      int var18 = this.data0bit4_bit5;
      int var17 = this.data0bit0_bit3;
      int var13 = this.data1bit6_bit7;
      int var4 = this.data1bit4_bit5;
      int var9 = this.data1bit2_bit3;
      int var21 = this.data1bit0_bit1;
      int var1 = this.data2bit6_bit7;
      int var5 = this.data2bit4_bit5;
      int var6 = this.data2bit2_bit3;
      int var23 = this.data2bit0_bit1;
      int var22 = this.data3bit6_bit7;
      int var14 = this.data3bit4_bit5;
      int var10 = this.data3bit2_bit3;
      int var3 = this.data3bit0_bit1;
      int var19 = this.data4bit6_bit7;
      int var15 = this.data4bit4_bit5;
      int var11 = this.data4bit2_bit3;
      int var8 = this.data4bit0_bit1;
      int var2 = this.data5bit6_bit7;
      int var12 = this.data5bit4_bit5;
      int var20 = this.data5bit2_bit3;
      int var7 = this.data5bit0_bit1;
      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 113, 40, (byte)((var16 & 255) << 6 | (var18 & 255) << 4 | var17 & 255), (byte)((var13 & 255) << 6 | (var4 & 255) << 4 | (var9 & 255) << 2 | var21 & 255), (byte)((var1 & 255) << 6 | (var5 & 255) << 4 | (var6 & 255) << 2 | var23 & 255), (byte)((var22 & 255) << 6 | (var14 & 255) << 4 | (var10 & 255) << 2 | var3 & 255), (byte)((var19 & 255) << 6 | (var15 & 255) << 4 | (var11 & 255) << 2 | var8 & 255), (byte)((var2 & 255) << 6 | (var12 & 255) << 4 | (var20 & 255) << 2 | var7 & 255), (byte)this.data6, (byte)this.data7});
   }

   private static class Holder {
      private static final Mp5StateCmdOption INSTANCE = new Mp5StateCmdOption();
   }
}
