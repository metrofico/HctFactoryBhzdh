package com.hzbhd.commontools;

public class SystemStatusDef {
   public static final int NOT_READY = -1;
   public static final int OFF = 0;
   public static final int ON = 1;

   public static boolean isNotReady(int var0) {
      boolean var1;
      if (var0 == -1) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static enum FAKE_POWEROFF_MODE {
      private static final FAKE_POWEROFF_MODE[] $VALUES;
      NORMAL,
      RUNNIG;

      static {
         FAKE_POWEROFF_MODE var1 = new FAKE_POWEROFF_MODE("NORMAL", 0);
         NORMAL = var1;
         FAKE_POWEROFF_MODE var0 = new FAKE_POWEROFF_MODE("RUNNIG", 1);
         RUNNIG = var0;
         $VALUES = new FAKE_POWEROFF_MODE[]{var1, var0};
      }
   }

   public static enum POWER_STATUS {
      private static final POWER_STATUS[] $VALUES;
      ACC_OFF,
      ACC_ON,
      FAKE_POWER_OFF,
      SLEEP;

      static {
         POWER_STATUS var0 = new POWER_STATUS("ACC_ON", 0);
         ACC_ON = var0;
         POWER_STATUS var2 = new POWER_STATUS("FAKE_POWER_OFF", 1);
         FAKE_POWER_OFF = var2;
         POWER_STATUS var1 = new POWER_STATUS("ACC_OFF", 2);
         ACC_OFF = var1;
         POWER_STATUS var3 = new POWER_STATUS("SLEEP", 3);
         SLEEP = var3;
         $VALUES = new POWER_STATUS[]{var0, var2, var1, var3};
      }
   }

   public static enum SLEEP_MODE {
      private static final SLEEP_MODE[] $VALUES;
      NORMAL;

      static {
         SLEEP_MODE var0 = new SLEEP_MODE("NORMAL", 0);
         NORMAL = var0;
         $VALUES = new SLEEP_MODE[]{var0};
      }
   }
}
