package com.hzbhd.commontools.utils;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.util.LogUtil;
import java.io.FileInputStream;
import java.util.HashMap;

public class ConfigUtil {
   private static final String CONFIG_CLASS_NAME = "MyConfig";
   private static final String TAG = "ConfigUtil";
   private static ConfigUtil sConfigUtil;
   private final int BUILD_VERSION_CODES_N = 24;
   private final int BUILD_VERSION_CODES_N_MR1 = 25;
   private final int BUILD_VERSION_CODES_O = 26;
   private final int BUILD_VERSION_CODES_O_MR1 = 27;
   private final int BUILD_VERSION_CODES_P = 28;
   private final int BUILD_VERSION_CODES_Q = 29;
   private final String CHIPNAME_AC8227 = "8227L_demo";
   private final String CHIPNAME_AC8257 = "ac8257";
   private final String CHIPNAME_NULL = "NULL";
   private final String CHIPNAME_RK3399 = "rk3399";
   private final String CHIPNAME_T3 = "T3";
   private final String CHIPNAME_T8 = "T8";
   private String DeviceInfor = "";
   private Object clientConfig;
   private Object frameworkConfig;
   private HashMap jarConfig = new HashMap();
   private Object platformConfig;
   private int sBuildVersionSDK = SystemProperties.getInt("ro.build.version.sdk", 25);
   private String sCHIP_NAME = SystemProperties.get("ro.product.model");
   private final HARDWARD_PLATFORM_VESRION sHARDWARD_PLATFORM_VESRION;

   public ConfigUtil() {
      if (this.sCHIP_NAME.contains("T3") && this.sBuildVersionSDK == 25) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.T3N_MR1;
      } else if (this.sCHIP_NAME.contains("T3") && this.sBuildVersionSDK == 27) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.T3O_MR1;
      } else if (this.sCHIP_NAME.contains("T8") && this.sBuildVersionSDK == 27) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.T8O_MR1;
      } else if (this.sCHIP_NAME.contains("rk3399") && this.sBuildVersionSDK == 27) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.RK3399O_MR1;
      } else if (this.sCHIP_NAME.contains("rk3399") && this.sBuildVersionSDK == 28) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.RK3399P;
      } else if (this.sCHIP_NAME.contains("ac8257") && this.sBuildVersionSDK == 28) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.AC8257P;
      } else if (this.sCHIP_NAME.contains("8227L_demo") && this.sBuildVersionSDK == 27) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.AC8227O;
      } else if (this.sCHIP_NAME.contains("T3") && this.sBuildVersionSDK == 29) {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.T3Q_MR1;
      } else {
         this.sHARDWARD_PLATFORM_VESRION = ConfigUtil.HARDWARD_PLATFORM_VESRION.T3N_MR1;
      }

   }

   public static Object getClientConfig(Class param0) {
      // $FF: Couldn't be decompiled
   }

   private Object getConfig(Class var1, String var2, String var3) {
      Object var12;
      label65: {
         label61: {
            boolean var10001;
            String var14;
            try {
               StringBuilder var4 = new StringBuilder();
               var14 = var4.append(var1.getPackage().getName()).append(".").append(var2.toLowerCase()).append(".").toString();
            } catch (Exception var11) {
               var10001 = false;
               break label61;
            }

            StringBuilder var5;
            Object var15;
            if (var3 == null) {
               label66: {
                  try {
                     if (LogUtil.log5()) {
                        var5 = new StringBuilder();
                        LogUtil.d(var5.append("getConfig: ").append(var14).append("MyConfig").toString());
                     }
                  } catch (Exception var8) {
                     var10001 = false;
                     break label66;
                  }

                  try {
                     var5 = new StringBuilder();
                     var15 = Class.forName(var5.append(var14).append("MyConfig").toString()).newInstance();
                  } catch (Exception var7) {
                     var10001 = false;
                     break label66;
                  }

                  var12 = var15;
               }
            } else {
               label67: {
                  try {
                     if (LogUtil.log5()) {
                        var5 = new StringBuilder();
                        LogUtil.d(var5.append("getConfig: ").append(var14).append(var3.toLowerCase()).append(".").append("MyConfig").toString());
                     }
                  } catch (Exception var10) {
                     var10001 = false;
                     break label67;
                  }

                  try {
                     var5 = new StringBuilder();
                     var15 = Class.forName(var5.append(var14).append(var3.toLowerCase()).append(".").append("MyConfig").toString()).newInstance();
                  } catch (Exception var9) {
                     var10001 = false;
                     break label67;
                  }

                  var12 = var15;
               }
            }
            break label65;
         }

         if (LogUtil.log5()) {
            LogUtil.d("getConfig not found: " + var2 + "." + var3);
         }

         var12 = null;
      }

      Object var13 = var12;
      if (var12 == null) {
         try {
            var13 = var1.newInstance();
         } catch (Exception var6) {
            var6.printStackTrace();
            var13 = var12;
         }
      }

      return var13;
   }

   public static String getDeviceId() {
      // $FF: Couldn't be decompiled
   }

   private String getDeviceInfo() {
      if (TextUtils.isEmpty(this.DeviceInfor)) {
         try {
            String var1 = this.getfactory_hardware_info_path();
            FileInputStream var2 = new FileInputStream(var1);
            byte[] var4 = new byte[var2.available()];
            var2.read(var4);
            var2.close();
            String var6 = new String(var4);
            this.DeviceInfor = var6;
            StringBuilder var5 = new StringBuilder();
            Log.i("getDeviceInfo", var5.append("Current device infor is =").append(this.DeviceInfor).toString());
         } catch (Exception var3) {
            var3.printStackTrace();
            Log.e("getDeviceInfo", "getDeviceInfo: error = " + var3.getMessage());
            this.DeviceInfor = "";
         }
      }

      return this.DeviceInfor;
   }

   public static Object getFrameworkConfig(Class param0) {
      // $FF: Couldn't be decompiled
   }

   private static ConfigUtil getInstance() {
      if (sConfigUtil == null) {
         sConfigUtil = new ConfigUtil();
      }

      return sConfigUtil;
   }

   public static Object getJarConfig(Class param0) {
      // $FF: Couldn't be decompiled
   }

   public static Object getPlatformConfig(Class param0) {
      // $FF: Couldn't be decompiled
   }

   private String getfactory_hardware_info_path() {
      synchronized(this){}

      try {
         if (this.sHARDWARD_PLATFORM_VESRION == ConfigUtil.HARDWARD_PLATFORM_VESRION.T3N_MR1) {
            return "/bootloader/factory_hardware_info";
         }

         if (this.sHARDWARD_PLATFORM_VESRION == ConfigUtil.HARDWARD_PLATFORM_VESRION.T8O_MR1) {
            return "/bootloader/factory_hardware_info";
         }

         if (this.sHARDWARD_PLATFORM_VESRION == ConfigUtil.HARDWARD_PLATFORM_VESRION.T3O_MR1) {
            return "/bootloader/factory_hardware_info";
         }

         if (this.sHARDWARD_PLATFORM_VESRION == ConfigUtil.HARDWARD_PLATFORM_VESRION.RK3399O_MR1) {
            return "/system/etc/factory_hardware_info";
         }

         if (this.sHARDWARD_PLATFORM_VESRION == ConfigUtil.HARDWARD_PLATFORM_VESRION.RK3399P) {
            return "/system/etc/factory_hardware_info";
         }

         if (this.sHARDWARD_PLATFORM_VESRION != ConfigUtil.HARDWARD_PLATFORM_VESRION.AC8257P) {
            if (this.sHARDWARD_PLATFORM_VESRION == ConfigUtil.HARDWARD_PLATFORM_VESRION.AC8227O) {
               return "/bhd/factory_hardware_info";
            }

            if (this.sHARDWARD_PLATFORM_VESRION == ConfigUtil.HARDWARD_PLATFORM_VESRION.T3Q_MR1) {
               return "/system/etc/factory_hardware_info";
            }

            return "/bootloader/factory_hardware_info";
         }
      } finally {
         ;
      }

      return "/bhd/factory_hardware_info";
   }

   private static enum HARDWARD_PLATFORM_VESRION {
      private static final HARDWARD_PLATFORM_VESRION[] $VALUES;
      AC8227O,
      AC8257P,
      RK3399O_MR1,
      RK3399P,
      T3N_MR1,
      T3O_MR1,
      T3Q_MR1,
      T8O_MR1;

      static {
         HARDWARD_PLATFORM_VESRION var0 = new HARDWARD_PLATFORM_VESRION("T3N_MR1", 0);
         T3N_MR1 = var0;
         HARDWARD_PLATFORM_VESRION var2 = new HARDWARD_PLATFORM_VESRION("T8O_MR1", 1);
         T8O_MR1 = var2;
         HARDWARD_PLATFORM_VESRION var1 = new HARDWARD_PLATFORM_VESRION("T3O_MR1", 2);
         T3O_MR1 = var1;
         HARDWARD_PLATFORM_VESRION var6 = new HARDWARD_PLATFORM_VESRION("RK3399O_MR1", 3);
         RK3399O_MR1 = var6;
         HARDWARD_PLATFORM_VESRION var5 = new HARDWARD_PLATFORM_VESRION("RK3399P", 4);
         RK3399P = var5;
         HARDWARD_PLATFORM_VESRION var4 = new HARDWARD_PLATFORM_VESRION("T3Q_MR1", 5);
         T3Q_MR1 = var4;
         HARDWARD_PLATFORM_VESRION var3 = new HARDWARD_PLATFORM_VESRION("AC8257P", 6);
         AC8257P = var3;
         HARDWARD_PLATFORM_VESRION var7 = new HARDWARD_PLATFORM_VESRION("AC8227O", 7);
         AC8227O = var7;
         $VALUES = new HARDWARD_PLATFORM_VESRION[]{var0, var2, var1, var6, var5, var4, var3, var7};
      }
   }
}
