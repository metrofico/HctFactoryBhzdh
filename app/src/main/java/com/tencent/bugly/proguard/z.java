package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class z {
   public static final String[] a = "@buglyAllChannel@".split(",");
   public static final String[] b = "@buglyAllChannelPriority@".split(",");

   public static String a(int var0) {
      FileReader var2;
      try {
         StringBuilder var3 = new StringBuilder("/proc/");
         var2 = new FileReader(var3.append(var0).append("/cmdline").toString());
      } finally {
         ;
      }

      String var59;
      label530: {
         Throwable var10000;
         label531: {
            boolean var10001;
            char[] var57;
            try {
               var57 = new char[512];
               var2.read(var57);
            } catch (Throwable var56) {
               var10000 = var56;
               var10001 = false;
               break label531;
            }

            int var1;
            for(var1 = 0; var1 < 512 && var57[var1] != 0; ++var1) {
            }

            label521:
            try {
               String var4 = new String(var57);
               var59 = var4.substring(0, var1);
               break label530;
            } catch (Throwable var55) {
               var10000 = var55;
               var10001 = false;
               break label521;
            }
         }

         Throwable var58 = var10000;

         try {
            if (!al.a(var58)) {
               var58.printStackTrace();
            }
         } finally {
            if (var2 != null) {
               label498:
               try {
                  var2.close();
               } finally {
                  break label498;
               }
            }

         }

         return String.valueOf(var0);
      }

      try {
         var2.close();
      } finally {
         return var59;
      }

      return var59;
   }

   public static String a(Context var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            String var3 = var0.getPackageName();
            return var3;
         } catch (Throwable var2) {
            if (!al.a(var2)) {
               var2.printStackTrace();
            }

            return "fail";
         }
      }
   }

   public static List a(Map var0) {
      if (var0 == null) {
         return null;
      } else {
         Throwable var10000;
         label342: {
            String var32;
            boolean var10001;
            try {
               var32 = (String)var0.get("BUGLY_DISABLE");
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               break label342;
            }

            label335: {
               if (var32 != null) {
                  try {
                     if (var32.length() != 0) {
                        break label335;
                     }
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break label342;
                  }
               }

               return null;
            }

            String[] var33;
            try {
               var33 = var32.split(",");
            } catch (Throwable var29) {
               var10000 = var29;
               var10001 = false;
               break label342;
            }

            int var1 = 0;

            while(true) {
               try {
                  if (var1 >= var33.length) {
                     break;
                  }

                  var33[var1] = var33[var1].trim();
               } catch (Throwable var28) {
                  var10000 = var28;
                  var10001 = false;
                  break label342;
               }

               ++var1;
            }

            label315:
            try {
               List var35 = Arrays.asList(var33);
               return var35;
            } catch (Throwable var27) {
               var10000 = var27;
               var10001 = false;
               break label315;
            }
         }

         Throwable var34 = var10000;
         if (!al.a(var34)) {
            var34.printStackTrace();
         }

         return null;
      }
   }

   public static boolean a() {
      // $FF: Couldn't be decompiled
   }

   public static boolean a(ActivityManager var0) {
      if (var0 == null) {
         al.c("is proc running, ActivityManager is null");
         return true;
      } else {
         List var2 = var0.getRunningAppProcesses();
         if (var2 == null) {
            al.c("running proc info list is empty, my proc not running.");
            return false;
         } else {
            int var1 = Process.myPid();
            Iterator var3 = var2.iterator();

            do {
               if (!var3.hasNext()) {
                  al.c("proc not in running proc info list, my proc not running.");
                  return false;
               }
            } while(((ActivityManager.RunningAppProcessInfo)var3.next()).pid != var1);

            al.c("my proc is running.");
            return true;
         }
      }
   }

   public static PackageInfo b(Context var0) {
      try {
         String var1 = a(var0);
         PackageInfo var4 = var0.getPackageManager().getPackageInfo(var1, 0);
         return var4;
      } catch (Throwable var3) {
         if (!al.a(var3)) {
            var3.printStackTrace();
         }

         return null;
      }
   }

   public static String c(Context var0) {
      if (var0 == null) {
         return null;
      } else {
         Throwable var10000;
         label165: {
            PackageManager var1;
            boolean var10001;
            ApplicationInfo var14;
            try {
               var1 = var0.getPackageManager();
               var14 = var0.getApplicationInfo();
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label165;
            }

            if (var1 == null || var14 == null) {
               return null;
            }

            CharSequence var15;
            try {
               var15 = var1.getApplicationLabel(var14);
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label165;
            }

            if (var15 == null) {
               return null;
            }

            label146:
            try {
               String var17 = var15.toString();
               return var17;
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               break label146;
            }
         }

         Throwable var16 = var10000;
         if (!al.a(var16)) {
            var16.printStackTrace();
         }

         return null;
      }
   }

   public static Map d(Context var0) {
      Object var1 = null;
      if (var0 == null) {
         return null;
      } else {
         Throwable var10000;
         label2268: {
            ApplicationInfo var2;
            boolean var10001;
            try {
               var2 = var0.getPackageManager().getApplicationInfo(var0.getPackageName(), 128);
            } catch (Throwable var308) {
               var10000 = var308;
               var10001 = false;
               break label2268;
            }

            HashMap var309 = (HashMap)var1;

            try {
               if (var2.metaData == null) {
                  return var309;
               }

               var309 = new HashMap();
               var1 = var2.metaData.get("BUGLY_DISABLE");
            } catch (Throwable var307) {
               var10000 = var307;
               var10001 = false;
               break label2268;
            }

            if (var1 != null) {
               try {
                  var309.put("BUGLY_DISABLE", var1.toString());
               } catch (Throwable var306) {
                  var10000 = var306;
                  var10001 = false;
                  break label2268;
               }
            }

            try {
               var1 = var2.metaData.get("BUGLY_APPID");
            } catch (Throwable var305) {
               var10000 = var305;
               var10001 = false;
               break label2268;
            }

            if (var1 != null) {
               try {
                  var309.put("BUGLY_APPID", var1.toString());
               } catch (Throwable var304) {
                  var10000 = var304;
                  var10001 = false;
                  break label2268;
               }
            }

            try {
               var1 = var2.metaData.get("BUGLY_APP_CHANNEL");
            } catch (Throwable var303) {
               var10000 = var303;
               var10001 = false;
               break label2268;
            }

            if (var1 != null) {
               try {
                  var309.put("BUGLY_APP_CHANNEL", var1.toString());
               } catch (Throwable var302) {
                  var10000 = var302;
                  var10001 = false;
                  break label2268;
               }
            }

            try {
               var1 = var2.metaData.get("BUGLY_APP_VERSION");
            } catch (Throwable var301) {
               var10000 = var301;
               var10001 = false;
               break label2268;
            }

            if (var1 != null) {
               try {
                  var309.put("BUGLY_APP_VERSION", var1.toString());
               } catch (Throwable var300) {
                  var10000 = var300;
                  var10001 = false;
                  break label2268;
               }
            }

            try {
               var1 = var2.metaData.get("BUGLY_ENABLE_DEBUG");
            } catch (Throwable var299) {
               var10000 = var299;
               var10001 = false;
               break label2268;
            }

            if (var1 != null) {
               try {
                  var309.put("BUGLY_ENABLE_DEBUG", var1.toString());
               } catch (Throwable var298) {
                  var10000 = var298;
                  var10001 = false;
                  break label2268;
               }
            }

            try {
               var1 = var2.metaData.get("com.tencent.rdm.uuid");
            } catch (Throwable var297) {
               var10000 = var297;
               var10001 = false;
               break label2268;
            }

            if (var1 != null) {
               try {
                  var309.put("com.tencent.rdm.uuid", var1.toString());
               } catch (Throwable var296) {
                  var10000 = var296;
                  var10001 = false;
                  break label2268;
               }
            }

            try {
               var1 = var2.metaData.get("BUGLY_APP_BUILD_NO");
            } catch (Throwable var295) {
               var10000 = var295;
               var10001 = false;
               break label2268;
            }

            if (var1 != null) {
               try {
                  var309.put("BUGLY_APP_BUILD_NO", var1.toString());
               } catch (Throwable var294) {
                  var10000 = var294;
                  var10001 = false;
                  break label2268;
               }
            }

            try {
               var1 = var2.metaData.get("BUGLY_AREA");
            } catch (Throwable var293) {
               var10000 = var293;
               var10001 = false;
               break label2268;
            }

            if (var1 == null) {
               return var309;
            }

            label2204:
            try {
               var309.put("BUGLY_AREA", var1.toString());
               return var309;
            } catch (Throwable var292) {
               var10000 = var292;
               var10001 = false;
               break label2204;
            }
         }

         Throwable var310 = var10000;
         if (!al.a(var310)) {
            var310.printStackTrace();
         }

         return null;
      }
   }
}
