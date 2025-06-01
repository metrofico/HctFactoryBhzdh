package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class ap {
   private static Map a;

   public static Context a(Context var0) {
      if (var0 == null) {
         return var0;
      } else {
         Context var1 = var0.getApplicationContext();
         return var1 == null ? var0 : var1;
      }
   }

   public static SharedPreferences a(String var0, Context var1) {
      return var1 != null ? var1.getSharedPreferences(var0, 0) : null;
   }

   public static BufferedReader a(File var0) {
      if (var0.exists() && var0.canRead()) {
         try {
            FileInputStream var2 = new FileInputStream(var0);
            InputStreamReader var1 = new InputStreamReader(var2, "utf-8");
            BufferedReader var5 = new BufferedReader(var1);
            return var5;
         } catch (Throwable var4) {
            al.a(var4);
            return null;
         }
      } else {
         return null;
      }
   }

   public static Object a(String var0, String var1, Class[] var2, Object[] var3) {
      try {
         Method var5 = Class.forName(var0).getDeclaredMethod(var1, var2);
         var5.setAccessible(true);
         Object var6 = var5.invoke((Object)null, var3);
         return var6;
      } catch (Exception var4) {
         return null;
      }
   }

   public static Object a(byte[] var0, Parcelable.Creator var1) {
      Parcel var2 = Parcel.obtain();
      var2.unmarshall(var0, 0, var0.length);
      var2.setDataPosition(0);

      Object var10;
      try {
         var10 = var1.createFromParcel(var2);
      } catch (Throwable var8) {
         Throwable var9 = var8;

         try {
            var9.printStackTrace();
         } finally {
            if (var2 != null) {
               var2.recycle();
            }

         }

         return null;
      }

      if (var2 != null) {
         var2.recycle();
      }

      return var10;
   }

   public static String a() {
      return a(System.currentTimeMillis());
   }

   public static String a(int var0, String var1) {
      String[] var2;
      if (var1 == null) {
         var2 = new String[]{"logcat", "-d", "-v", "threadtime"};
      } else {
         var2 = new String[]{"logcat", "-d", "-v", "threadtime", "-s", var1};
      }

      Process var279 = null;
      StringBuilder var3 = new StringBuilder();

      Process var280;
      String var283;
      label2054: {
         Throwable var10000;
         boolean var10001;
         label2055: {
            try {
               var280 = Runtime.getRuntime().exec(var2);
            } catch (Throwable var278) {
               var10000 = var278;
               var10001 = false;
               break label2055;
            }

            var279 = var280;

            BufferedReader var4;
            try {
               var4 = new BufferedReader;
            } catch (Throwable var277) {
               var10000 = var277;
               var10001 = false;
               break label2055;
            }

            var279 = var280;

            InputStreamReader var5;
            try {
               var5 = new InputStreamReader;
            } catch (Throwable var276) {
               var10000 = var276;
               var10001 = false;
               break label2055;
            }

            var279 = var280;

            try {
               var5.<init>(var280.getInputStream());
            } catch (Throwable var275) {
               var10000 = var275;
               var10001 = false;
               break label2055;
            }

            var279 = var280;

            try {
               var4.<init>(var5);
            } catch (Throwable var273) {
               var10000 = var273;
               var10001 = false;
               break label2055;
            }

            while(true) {
               var279 = var280;

               String var284;
               try {
                  var284 = var4.readLine();
               } catch (Throwable var270) {
                  var10000 = var270;
                  var10001 = false;
                  break;
               }

               if (var284 == null) {
                  var279 = var280;

                  try {
                     var283 = var3.toString();
                     break label2054;
                  } catch (Throwable var269) {
                     var10000 = var269;
                     var10001 = false;
                     break;
                  }
               }

               var279 = var280;

               try {
                  var3.append(var284).append("\n");
               } catch (Throwable var272) {
                  var10000 = var272;
                  var10001 = false;
                  break;
               }

               if (var0 > 0) {
                  var279 = var280;

                  try {
                     if (var3.length() <= var0) {
                        continue;
                     }
                  } catch (Throwable var274) {
                     var10000 = var274;
                     var10001 = false;
                     break;
                  }

                  var279 = var280;

                  try {
                     var3.delete(0, var3.length() - var0);
                  } catch (Throwable var271) {
                     var10000 = var271;
                     var10001 = false;
                     break;
                  }
               }
            }
         }

         Throwable var281 = var10000;

         String var282;
         label2057: {
            label2004: {
               try {
                  if (!al.a(var281)) {
                     var281.printStackTrace();
                  }
               } catch (Throwable var268) {
                  var10000 = var268;
                  var10001 = false;
                  break label2004;
               }

               label2001:
               try {
                  var282 = var3.append("\n[error:").append(var281.toString()).append("]").toString();
                  break label2057;
               } catch (Throwable var267) {
                  var10000 = var267;
                  var10001 = false;
                  break label2001;
               }
            }

            var281 = var10000;
            if (var279 != null) {
               try {
                  var279.getOutputStream().close();
               } catch (IOException var260) {
                  var260.printStackTrace();
               }

               try {
                  var279.getInputStream().close();
               } catch (IOException var259) {
                  var259.printStackTrace();
               }

               try {
                  var279.getErrorStream().close();
               } catch (IOException var258) {
                  var258.printStackTrace();
               }
            }

            throw var281;
         }

         if (var279 != null) {
            try {
               var279.getOutputStream().close();
            } catch (IOException var263) {
               var263.printStackTrace();
            }

            try {
               var279.getInputStream().close();
            } catch (IOException var262) {
               var262.printStackTrace();
            }

            try {
               var279.getErrorStream().close();
            } catch (IOException var261) {
               var261.printStackTrace();
            }
         }

         return var282;
      }

      if (var280 != null) {
         try {
            var280.getOutputStream().close();
         } catch (IOException var266) {
            var266.printStackTrace();
         }

         try {
            var280.getInputStream().close();
         } catch (IOException var265) {
            var265.printStackTrace();
         }

         try {
            var280.getErrorStream().close();
         } catch (IOException var264) {
            var264.printStackTrace();
         }
      }

      return var283;
   }

   public static String a(long var0) {
      try {
         SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
         Date var3 = new Date(var0);
         String var5 = var2.format(var3);
         return var5;
      } catch (Exception var4) {
         return (new Date()).toString();
      }
   }

   public static String a(File var0, int var1, boolean var2) {
      if (var0 != null && var0.exists() && var0.canRead()) {
         StringBuilder var3;
         BufferedReader var4;
         try {
            var3 = new StringBuilder();
            FileInputStream var5 = new FileInputStream(var0);
            InputStreamReader var6 = new InputStreamReader(var5, "utf-8");
            var4 = new BufferedReader(var6);
         } finally {
            ;
         }

         String var98;
         label750: {
            Throwable var10000;
            while(true) {
               boolean var10001;
               try {
                  var98 = var4.readLine();
               } catch (Throwable var97) {
                  var10000 = var97;
                  var10001 = false;
                  break;
               }

               if (var98 != null) {
                  try {
                     var3.append(var98).append("\n");
                  } catch (Throwable var96) {
                     var10000 = var96;
                     var10001 = false;
                     break;
                  }

                  if (var1 <= 0) {
                     continue;
                  }

                  try {
                     if (var3.length() <= var1) {
                        continue;
                     }
                  } catch (Throwable var95) {
                     var10000 = var95;
                     var10001 = false;
                     break;
                  }

                  if (!var2) {
                     try {
                        var3.delete(0, var3.length() - var1);
                        continue;
                     } catch (Throwable var92) {
                        var10000 = var92;
                        var10001 = false;
                        break;
                     }
                  }

                  try {
                     var3.delete(var1, var3.length());
                  } catch (Throwable var94) {
                     var10000 = var94;
                     var10001 = false;
                     break;
                  }
               }

               try {
                  var98 = var3.toString();
                  break label750;
               } catch (Throwable var93) {
                  var10000 = var93;
                  var10001 = false;
                  break;
               }
            }

            Throwable var100 = var10000;
            BufferedReader var99 = var4;

            try {
               al.a(var100);
            } finally {
               if (var4 != null) {
                  try {
                     var99.close();
                  } catch (Exception var88) {
                     al.a(var88);
                  }
               }

            }

            return null;
         }

         try {
            var4.close();
         } catch (Exception var89) {
            al.a(var89);
         }

         return var98;
      } else {
         return null;
      }
   }

   public static String a(String var0) {
      if (var0.trim().equals("")) {
         return "";
      } else {
         try {
            if (a == null) {
               HashMap var1 = new HashMap();
               a = var1;
            }

            if (a.containsKey(var0)) {
               return (String)a.get(var0);
            } else {
               String var4 = NativeCrashHandler.getInstance().getSystemProperty(var0);
               if (!TextUtils.isEmpty(var4) && !var4.equals("fail")) {
                  a.put(var0, var4);
               }

               return var4;
            }
         } catch (Throwable var3) {
            al.b(var3);
            return "fail";
         }
      }
   }

   public static String a(Thread var0) {
      if (var0 == null) {
         return "";
      } else {
         StringBuilder var3 = new StringBuilder();
         StackTraceElement[] var4 = var0.getStackTrace();
         int var2 = var4.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            var3.append(var4[var1].toString()).append("\n");
         }

         return var3.toString();
      }
   }

   public static String a(Throwable var0) {
      if (var0 == null) {
         return "";
      } else {
         try {
            StringWriter var1 = new StringWriter();
            PrintWriter var2 = new PrintWriter(var1);
            var0.printStackTrace(var2);
            String var5 = var1.getBuffer().toString();
            return var5;
         } catch (Throwable var4) {
            if (!al.a(var4)) {
               var4.printStackTrace();
            }

            return "fail";
         }
      }
   }

   public static String a(Date var0) {
      try {
         SimpleDateFormat var1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
         String var3 = var1.format(var0);
         return var3;
      } catch (Exception var2) {
         return (new Date()).toString();
      }
   }

   public static Thread a(Runnable var0, String var1) {
      try {
         Thread var2 = new Thread(var0);
         var2.setName(var1);
         var2.start();
         return var2;
      } catch (Throwable var4) {
         al.e("[Util] Failed to start a thread to execute task with message: %s", var4.getMessage());
         return null;
      }
   }

   private static Map a(int var0) {
      HashMap var3 = new HashMap(12);
      Map var5 = Thread.getAllStackTraces();
      if (var5 == null) {
         return null;
      } else {
         Thread var4 = Looper.getMainLooper().getThread();
         if (!var5.containsKey(var4)) {
            var5.put(var4, var4.getStackTrace());
         }

         Thread.currentThread().getId();
         StringBuilder var9 = new StringBuilder();
         Iterator var6 = var5.entrySet().iterator();

         while(true) {
            int var1;
            Map.Entry var7;
            do {
               do {
                  if (!var6.hasNext()) {
                     return var3;
                  }

                  var7 = (Map.Entry)var6.next();
                  var1 = 0;
                  var9.setLength(0);
               } while(var7.getValue() == null);
            } while(((StackTraceElement[])var7.getValue()).length == 0);

            StackTraceElement[] var8 = (StackTraceElement[])var7.getValue();

            for(int var2 = var8.length; var1 < var2; ++var1) {
               StackTraceElement var10 = var8[var1];
               if (var0 > 0 && var9.length() >= var0) {
                  var9.append("\n[Stack over limit size :" + var0 + " , has been cut!]");
                  break;
               }

               var9.append(var10.toString()).append("\n");
            }

            var3.put(((Thread)var7.getKey()).getName() + "(" + ((Thread)var7.getKey()).getId() + ")", var9.toString());
         }
      }
   }

   public static Map a(Parcel var0) {
      Bundle var7 = var0.readBundle();
      HashMap var9 = null;
      if (var7 == null) {
         return null;
      } else {
         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();
         int var3 = (Integer)var7.get("pluginNum");
         byte var2 = 0;

         int var1;
         for(var1 = 0; var1 < var3; ++var1) {
            var5.add(var7.getString("pluginKey".concat(String.valueOf(var1))));
         }

         for(var1 = 0; var1 < var3; ++var1) {
            String var8 = var7.getString("pluginVal" + var1 + "plugInId");
            String var4 = var7.getString("pluginVal" + var1 + "plugInUUID");
            var6.add(new PlugInBean(var8, var7.getString("pluginVal" + var1 + "plugInVersion"), var4));
         }

         if (var5.size() == var6.size()) {
            HashMap var10 = new HashMap(var5.size());
            var1 = var2;

            while(true) {
               var9 = var10;
               if (var1 >= var5.size()) {
                  break;
               }

               var10.put(var5.get(var1), PlugInBean.class.cast(var6.get(var1)));
               ++var1;
            }
         } else {
            al.e("map plugin parcel error!");
         }

         return var9;
      }
   }

   public static Map a(boolean var0, int var1) {
      if (!var0) {
         al.c("get all thread stack not enable");
         return new HashMap();
      } else {
         Map var3 = a(var1);
         Object var2 = var3;
         if (var3 == null) {
            var2 = new HashMap();
         }

         return (Map)var2;
      }
   }

   public static void a(Parcel var0, Map var1) {
      if (var1 != null && var1.size() > 0) {
         int var2 = var1.size();
         ArrayList var6 = new ArrayList(var2);
         ArrayList var5 = new ArrayList(var2);
         Iterator var7 = var1.entrySet().iterator();

         while(var7.hasNext()) {
            Map.Entry var8 = (Map.Entry)var7.next();
            var6.add(var8.getKey());
            var5.add(var8.getValue());
         }

         Bundle var9 = new Bundle();
         var9.putInt("pluginNum", var6.size());
         byte var4 = 0;
         var2 = 0;

         while(true) {
            int var3 = var4;
            if (var2 >= var6.size()) {
               while(var3 < var6.size()) {
                  var9.putString("pluginVal" + var3 + "plugInId", ((PlugInBean)var5.get(var3)).a);
                  var9.putString("pluginVal" + var3 + "plugInUUID", ((PlugInBean)var5.get(var3)).c);
                  var9.putString("pluginVal" + var3 + "plugInVersion", ((PlugInBean)var5.get(var3)).b);
                  ++var3;
               }

               var0.writeBundle(var9);
               return;
            }

            var9.putString("pluginKey".concat(String.valueOf(var2)), (String)var6.get(var2));
            ++var2;
         }
      } else {
         var0.writeBundle((Bundle)null);
      }
   }

   public static void a(Class var0, String var1, Object var2) {
      try {
         Field var4 = var0.getDeclaredField(var1);
         var4.setAccessible(true);
         var4.set((Object)null, var2);
      } catch (Exception var3) {
      }

   }

   public static boolean a(Context var0, String var1) {
      al.c("[Util] Try to lock file:%s (pid=%d | tid=%d)", var1, android.os.Process.myPid(), android.os.Process.myTid());

      try {
         StringBuilder var2 = new StringBuilder();
         String var6 = var2.append(var0.getFilesDir()).append(File.separator).append(var1).toString();
         File var3 = new File(var6);
         if (var3.exists()) {
            if (System.currentTimeMillis() - var3.lastModified() < 10000L) {
               return false;
            }

            al.c("[Util] Lock file (%s) is expired, unlock it.", var1);
            b(var0, var1);
         }

         if (var3.createNewFile()) {
            al.c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", var1, android.os.Process.myPid(), android.os.Process.myTid());
            return true;
         } else {
            al.c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", var1, android.os.Process.myPid(), android.os.Process.myTid());
            return false;
         }
      } catch (Throwable var5) {
         al.a(var5);
         return false;
      }
   }

   public static boolean a(File param0, File param1) {
      // $FF: Couldn't be decompiled
   }

   public static boolean a(Runnable var0) {
      ak var1 = ak.a();
      if (var1 != null) {
         return var1.a(var0);
      } else {
         String[] var2 = var0.getClass().getName().split("\\.");
         return a(var0, var2[var2.length - 1]) != null;
      }
   }

   public static byte[] a(Parcelable var0) {
      Parcel var1 = Parcel.obtain();
      var0.writeToParcel(var1, 0);
      byte[] var2 = var1.marshall();
      var1.recycle();
      return var2;
   }

   public static byte[] a(String var0, String var1) {
      if (var0 != null && var0.length() != 0) {
         al.c("rqdp{  ZF start}");

         ZipOutputStream var64;
         ByteArrayInputStream var3;
         ByteArrayOutputStream var4;
         try {
            byte[] var63 = var0.getBytes("UTF-8");
            var3 = new ByteArrayInputStream(var63);
            var4 = new ByteArrayOutputStream();
            var64 = new ZipOutputStream(var4);
         } finally {
            ;
         }

         byte[] var65;
         label489: {
            Throwable var10000;
            label481: {
               boolean var10001;
               try {
                  var64.setMethod(8);
                  ZipEntry var5 = new ZipEntry(var1);
                  var64.putNextEntry(var5);
                  var65 = new byte[1024];
               } catch (Throwable var62) {
                  var10000 = var62;
                  var10001 = false;
                  break label481;
               }

               while(true) {
                  int var2;
                  try {
                     var2 = var3.read(var65);
                  } catch (Throwable var60) {
                     var10000 = var60;
                     var10001 = false;
                     break;
                  }

                  if (var2 <= 0) {
                     try {
                        var64.closeEntry();
                        var64.flush();
                        var64.finish();
                        var65 = var4.toByteArray();
                        break label489;
                     } catch (Throwable var59) {
                        var10000 = var59;
                        var10001 = false;
                        break;
                     }
                  }

                  try {
                     var64.write(var65, 0, var2);
                  } catch (Throwable var61) {
                     var10000 = var61;
                     var10001 = false;
                     break;
                  }
               }
            }

            Throwable var66 = var10000;

            try {
               if (!al.a(var66)) {
                  var66.printStackTrace();
               }
            } finally {
               if (var64 != null) {
                  try {
                     var64.close();
                  } catch (IOException var55) {
                     var55.printStackTrace();
                  }
               }

               al.c("rqdp{  ZF end}");
            }

            return null;
         }

         try {
            var64.close();
         } catch (IOException var56) {
            var56.printStackTrace();
         }

         al.c("rqdp{  ZF end}");
         return var65;
      } else {
         return null;
      }
   }

   public static byte[] a(byte[] var0) {
      if (var0 == null) {
         return var0;
      } else {
         al.c("[Util] Zip %d bytes data with type %s", var0.length, "Gzip");

         try {
            var0 = bh.a().a(var0);
            return var0;
         } catch (Throwable var2) {
            if (!al.a(var2)) {
               var2.printStackTrace();
            }

            return null;
         }
      }
   }

   public static long b() {
      int var0;
      long var1;
      try {
         var1 = (System.currentTimeMillis() + (long)TimeZone.getDefault().getRawOffset()) / 86400000L;
         var0 = TimeZone.getDefault().getRawOffset();
      } catch (Throwable var5) {
         if (!al.a(var5)) {
            var5.printStackTrace();
         }

         return -1L;
      }

      return var1 * 86400000L - (long)var0;
   }

   public static BufferedReader b(String var0, String var1) {
      if (var0 == null) {
         return null;
      } else {
         try {
            File var2 = new File(var0, var1);
            if (var2.exists() && var2.canRead()) {
               BufferedReader var4 = a(var2);
               return var4;
            } else {
               return null;
            }
         } catch (NullPointerException var3) {
            al.a(var3);
            return null;
         }
      }
   }

   public static String b(Throwable var0) {
      if (var0 == null) {
         return "";
      } else {
         StringWriter var2 = new StringWriter();
         PrintWriter var1 = new PrintWriter(var2);
         var0.printStackTrace(var1);
         var1.flush();
         return var2.toString();
      }
   }

   public static Map b(Parcel var0) {
      Bundle var2 = var0.readBundle();
      HashMap var5 = null;
      if (var2 == null) {
         return null;
      } else {
         ArrayList var3 = var2.getStringArrayList("keys");
         ArrayList var4 = var2.getStringArrayList("values");
         int var1 = 0;
         if (var3 != null && var4 != null && var3.size() == var4.size()) {
            HashMap var6 = new HashMap(var3.size());

            while(true) {
               var5 = var6;
               if (var1 >= var3.size()) {
                  break;
               }

               var6.put(var3.get(var1), var4.get(var1));
               ++var1;
            }
         } else {
            al.e("map parcel error!");
         }

         return var5;
      }
   }

   public static void b(long var0) {
      try {
         Thread.sleep(var0);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }
   }

   public static void b(Parcel var0, Map var1) {
      if (var1 != null && var1.size() > 0) {
         int var2 = var1.size();
         ArrayList var3 = new ArrayList(var2);
         ArrayList var4 = new ArrayList(var2);
         Iterator var5 = var1.entrySet().iterator();

         while(var5.hasNext()) {
            Map.Entry var6 = (Map.Entry)var5.next();
            var3.add(var6.getKey());
            var4.add(var6.getValue());
         }

         Bundle var7 = new Bundle();
         var7.putStringArrayList("keys", var3);
         var7.putStringArrayList("values", var4);
         var0.writeBundle(var7);
      } else {
         var0.writeBundle((Bundle)null);
      }
   }

   public static boolean b(Context var0, String var1) {
      al.c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", var1, android.os.Process.myPid(), android.os.Process.myTid());

      try {
         StringBuilder var2 = new StringBuilder();
         String var6 = var2.append(var0.getFilesDir()).append(File.separator).append(var1).toString();
         File var5 = new File(var6);
         if (var5.exists()) {
            if (var5.delete()) {
               al.c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", var1, android.os.Process.myPid(), android.os.Process.myTid());
               return true;
            } else {
               return false;
            }
         } else {
            return true;
         }
      } catch (Throwable var4) {
         al.a(var4);
         return false;
      }
   }

   private static boolean b(File var0, File var1) {
      if (var0 != null && var1 != null && !var0.equals(var1)) {
         if (var0.exists() && var0.canRead()) {
            label81:
            try {
               if (var1.getParentFile() != null && !var1.getParentFile().exists()) {
                  var1.getParentFile().mkdirs();
               }

               if (!var1.exists()) {
                  var1.createNewFile();
               }
            } catch (Throwable var3) {
               if (!al.a(var3)) {
                  var3.printStackTrace();
               }
               break label81;
            }

            return var1.exists() && var1.canWrite();
         } else {
            al.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}");
            return false;
         }
      } else {
         al.d("rqdp{  err ZF 1R!}");
         return false;
      }
   }

   public static boolean b(String var0) {
      return var0 == null || var0.trim().length() <= 0;
   }

   public static byte[] b(byte[] var0) {
      if (var0 == null) {
         return var0;
      } else {
         al.c("[Util] Unzip %d bytes data with type %s", var0.length, "Gzip");

         try {
            var0 = bh.a().b(var0);
            return var0;
         } catch (Throwable var2) {
            if (var2.getMessage() != null && var2.getMessage().contains("Not in GZIP format")) {
               al.d(var2.getMessage());
            } else if (!al.a(var2)) {
               var2.printStackTrace();
            }

            return null;
         }
      }
   }

   public static String c(byte[] var0) {
      if (var0 != null && var0.length != 0) {
         Throwable var10000;
         label366: {
            byte[] var3;
            boolean var10001;
            try {
               MessageDigest var2 = MessageDigest.getInstance("SHA-1");
               var2.update(var0);
               var3 = var2.digest();
            } catch (Throwable var33) {
               var10000 = var33;
               var10001 = false;
               break label366;
            }

            if (var3 == null) {
               return "";
            }

            StringBuffer var36;
            try {
               var36 = new StringBuffer();
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label366;
            }

            int var1 = 0;

            String var34;
            while(true) {
               try {
                  if (var1 >= var3.length) {
                     break;
                  }

                  var34 = Integer.toHexString(var3[var1] & 255);
                  if (var34.length() == 1) {
                     var36.append("0");
                  }
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label366;
               }

               try {
                  var36.append(var34);
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label366;
               }

               ++var1;
            }

            label341:
            try {
               var34 = var36.toString().toUpperCase();
               return var34;
            } catch (Throwable var29) {
               var10000 = var29;
               var10001 = false;
               break label341;
            }
         }

         Throwable var35 = var10000;
         if (!al.a(var35)) {
            var35.printStackTrace();
         }

         return null;
      } else {
         return "NULL";
      }
   }

   public static void c(String var0) {
      if (var0 != null) {
         File var1 = new File(var0);
         if (var1.isFile() && var1.exists() && var1.canWrite()) {
            var1.delete();
         }

      }
   }

   public static void c(String var0, String var1) {
      if (aa.b() != null && aa.b().O != null) {
         aa.b().O.edit().putString(var0, var1).apply();
      }

   }

   public static byte[] c(long var0) {
      try {
         byte[] var2 = String.valueOf(var0).getBytes("utf-8");
         return var2;
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static long d(byte[] var0) {
      if (var0 == null) {
         return -1L;
      } else {
         try {
            String var3 = new String(var0, "utf-8");
            long var1 = Long.parseLong(var3);
            return var1;
         } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
            return -1L;
         }
      }
   }

   public static String d(String var0, String var1) {
      return aa.b() != null && aa.b().O != null ? aa.b().O.getString(var0, var1) : "";
   }

   public static boolean d(String var0) {
      if (b(var0)) {
         return false;
      } else if (var0.length() > 255) {
         al.a("URL(%s)'s length is larger than 255.", var0);
         return false;
      } else if (!var0.toLowerCase().startsWith("http")) {
         al.a("URL(%s) is not start with \"http\".", var0);
         return false;
      } else {
         return true;
      }
   }
}
