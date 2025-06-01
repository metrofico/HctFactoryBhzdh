package com.hzbhd.canbus.car._29;

public class CarState {
   private static boolean ACCState;
   private static boolean ILLState;
   private static boolean PARKState;
   private static boolean REVState;

   public static boolean isACCState() {
      return ACCState;
   }

   public static boolean isILLState() {
      return ILLState;
   }

   public static boolean isPARKState() {
      return PARKState;
   }

   public static boolean isREVState() {
      return REVState;
   }

   public static void setACCState(boolean var0) {
      ACCState = var0;
   }

   public static void setILLState(boolean var0) {
      ILLState = var0;
   }

   public static void setPARKState(boolean var0) {
      PARKState = var0;
   }

   public static void setREVState(boolean var0) {
      REVState = var0;
   }
}
