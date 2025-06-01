package com.hzbhd.proxy.mcu.upgrade;

public class UpgradeConstants {
   public static enum DevType {
      private static final DevType[] $VALUES;
      BOOT,
      BT,
      CANBOX,
      HDMI,
      MCU,
      MPEG;

      static {
         DevType var3 = new DevType("BOOT", 0);
         BOOT = var3;
         DevType var5 = new DevType("BT", 1);
         BT = var5;
         DevType var4 = new DevType("CANBOX", 2);
         CANBOX = var4;
         DevType var0 = new DevType("MCU", 3);
         MCU = var0;
         DevType var1 = new DevType("MPEG", 4);
         MPEG = var1;
         DevType var2 = new DevType("HDMI", 5);
         HDMI = var2;
         $VALUES = new DevType[]{var3, var5, var4, var0, var1, var2};
      }
   }

   public static enum UpgradeStartCheckStatus {
      private static final UpgradeStartCheckStatus[] $VALUES;
      FILE_TOO_LARGE,
      FILE_VERSION_ERROR,
      UNKNOWN_ERROR;

      static {
         UpgradeStartCheckStatus var1 = new UpgradeStartCheckStatus("FILE_TOO_LARGE", 0);
         FILE_TOO_LARGE = var1;
         UpgradeStartCheckStatus var2 = new UpgradeStartCheckStatus("FILE_VERSION_ERROR", 1);
         FILE_VERSION_ERROR = var2;
         UpgradeStartCheckStatus var0 = new UpgradeStartCheckStatus("UNKNOWN_ERROR", 2);
         UNKNOWN_ERROR = var0;
         $VALUES = new UpgradeStartCheckStatus[]{var1, var2, var0};
      }
   }

   public static enum UpgradeStartEndStatus {
      private static final UpgradeStartEndStatus[] $VALUES;
      CHECK_FIAL,
      FAIL,
      LOADING,
      READY,
      SUCCESS;

      static {
         UpgradeStartEndStatus var2 = new UpgradeStartEndStatus("READY", 0);
         READY = var2;
         UpgradeStartEndStatus var3 = new UpgradeStartEndStatus("LOADING", 1);
         LOADING = var3;
         UpgradeStartEndStatus var1 = new UpgradeStartEndStatus("SUCCESS", 2);
         SUCCESS = var1;
         UpgradeStartEndStatus var0 = new UpgradeStartEndStatus("FAIL", 3);
         FAIL = var0;
         UpgradeStartEndStatus var4 = new UpgradeStartEndStatus("CHECK_FIAL", 4);
         CHECK_FIAL = var4;
         $VALUES = new UpgradeStartEndStatus[]{var2, var3, var1, var0, var4};
      }
   }
}
