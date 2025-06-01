package com.hzbhd.canbus.car._394;

import com.hzbhd.canbus.util.DataHandleUtils;

public class NewUtil {
   public static int getBoolBit0(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 0, 1);
   }

   public static int getBoolBit1(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 1, 1);
   }

   public static int getBoolBit2(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 2, 1);
   }

   public static int getBoolBit3(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 3, 1);
   }

   public static int getBoolBit4(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 4, 1);
   }

   public static int getBoolBit5(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 5, 1);
   }

   public static int getBoolBit6(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 6, 1);
   }

   public static int getBoolBit7(int var0) {
      return DataHandleUtils.getIntFromByteWithBit(var0, 7, 1);
   }
}
