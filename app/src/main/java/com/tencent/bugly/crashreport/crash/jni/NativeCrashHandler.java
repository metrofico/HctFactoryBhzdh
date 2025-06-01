package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.as;
import com.tencent.bugly.proguard.at;
import com.tencent.bugly.proguard.bd;
import com.tencent.bugly.proguard.be;
import com.tencent.bugly.proguard.q;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class NativeCrashHandler implements q {
   static String a;
   private static NativeCrashHandler b;
   private static int c;
   private static boolean n;
   private final Context d;
   private final aa e;
   private final ak f;
   private NativeExceptionHandler g;
   private final boolean h;
   private boolean i = false;
   private boolean j = false;
   private boolean k = false;
   private boolean l = false;
   private as m;

   private NativeCrashHandler(Context var1, aa var2, as var3, ak var4, boolean var5, String var6) {
      this.d = ap.a(var1);
      if (ap.b(a)) {
         boolean var9 = false;

         String var7;
         label35:
         try {
            var9 = true;
            if (ap.b(var6)) {
               var7 = var1.getDir("bugly", 0).getAbsolutePath();
               var9 = false;
            } else {
               var9 = false;
            }
         } finally {
            if (var9) {
               var6 = aa.a(var1).c;
               var7 = "/data/data/" + var6 + "/app_bugly";
               break label35;
            }
         }

         a = var7;
      }

      this.m = var3;
      this.e = var2;
      this.f = var4;
      this.h = var5;
      this.g = new bd(var1, var2, var3, ac.a());
   }

   private void a(boolean var1) {
      synchronized(this){}

      Throwable var10000;
      label1803: {
         boolean var10001;
         label1798: {
            try {
               if (!this.k) {
                  break label1798;
               }

               al.d("[Native] Native crash report has already registered.");
            } catch (Throwable var190) {
               var10000 = var190;
               var10001 = false;
               break label1803;
            }

            return;
         }

         boolean var4;
         try {
            var4 = this.j;
         } catch (Throwable var189) {
            var10000 = var189;
            var10001 = false;
            break label1803;
         }

         String var5;
         if (var4) {
            label1804: {
               label1805: {
                  try {
                     var5 = this.regist(a, var1, c);
                  } catch (Throwable var188) {
                     var10001 = false;
                     break label1805;
                  }

                  if (var5 == null) {
                     break label1804;
                  }

                  try {
                     al.a("[Native] Native Crash Report enable.");
                     this.e.u = var5;
                     var5 = "-".concat(this.e.u);
                     if (!at.b && !this.e.h.contains(var5)) {
                        aa var192 = this.e;
                        var192.h = var192.h.concat("-").concat(this.e.u);
                     }
                  } catch (Throwable var187) {
                     var10001 = false;
                     break label1805;
                  }

                  try {
                     al.a("comInfo.sdkVersion %s", this.e.h);
                     this.k = true;
                     var5 = this.getRunningCpuAbi();
                     if (!TextUtils.isEmpty(var5)) {
                        this.e.e(var5);
                     }
                  } catch (Throwable var180) {
                     var10001 = false;
                     break label1805;
                  }

                  return;
               }

               try {
                  al.c("[Native] Failed to load Bugly SO file.");
               } catch (Throwable var182) {
                  var10000 = var182;
                  var10001 = false;
                  break label1803;
               }
            }
         } else {
            try {
               var4 = this.i;
            } catch (Throwable var181) {
               var10000 = var181;
               var10001 = false;
               break label1803;
            }

            if (var4) {
               label1806: {
                  Class var6;
                  String var7;
                  Class var8;
                  try {
                     var8 = Integer.TYPE;
                     var6 = Integer.TYPE;
                     var7 = a;
                     var5 = ab.d();
                  } catch (Throwable var186) {
                     var10001 = false;
                     break label1806;
                  }

                  byte var3 = 5;
                  int var2;
                  if (var1) {
                     var2 = 1;
                  } else {
                     var2 = 5;
                  }

                  String var193;
                  try {
                     var193 = (String)ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler2", new Class[]{String.class, String.class, var8, var6}, new Object[]{var7, var5, var2, 1});
                  } catch (Throwable var185) {
                     var10001 = false;
                     break label1806;
                  }

                  var5 = var193;
                  Class var194;
                  if (var193 == null) {
                     try {
                        var194 = Integer.TYPE;
                        var193 = a;
                        var7 = ab.d();
                        aa.b();
                        var2 = aa.B();
                        var5 = (String)ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", new Class[]{String.class, String.class, var194}, new Object[]{var193, var7, var2});
                     } catch (Throwable var184) {
                        var10001 = false;
                        break label1806;
                     }
                  }

                  if (var5 != null) {
                     label1807: {
                        try {
                           this.k = true;
                           this.e.u = var5;
                           var194 = Boolean.TYPE;
                           Boolean var195 = Boolean.TRUE;
                           ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", new Class[]{var194}, new Object[]{var195});
                        } catch (Throwable var183) {
                           var10001 = false;
                           break label1807;
                        }

                        byte var191 = var3;
                        if (var1) {
                           var191 = 1;
                        }

                        try {
                           ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(var191)});
                           var5 = this.getRunningCpuAbi();
                           if (!TextUtils.isEmpty(var5)) {
                              this.e.e(var5);
                           }
                        } catch (Throwable var179) {
                           var10001 = false;
                           break label1807;
                        }

                        return;
                     }
                  }
               }
            }
         }

         try {
            this.j = false;
            this.i = false;
         } catch (Throwable var178) {
            var10000 = var178;
            var10001 = false;
            break label1803;
         }

         return;
      }

      Throwable var196 = var10000;
      throw var196;
   }

   private boolean a(int var1, String var2) {
      if (!this.j) {
         return false;
      } else {
         label44:
         try {
            try {
               this.setNativeInfo(var1, var2);
               return true;
            } catch (UnsatisfiedLinkError var5) {
            }
         } catch (Throwable var6) {
            if (!al.a(var6)) {
               var6.printStackTrace();
            }
            break label44;
         }

         return false;
      }
   }

   // $FF: synthetic method
   static boolean a(NativeCrashHandler var0, String var1) {
      return var0.a(999, var1);
   }

   private static boolean a(String var0, boolean var1) {
      boolean var2 = true;

      label197: {
         Throwable var10000;
         label185: {
            boolean var10001;
            try {
               al.a("[Native] Trying to load so: %s", var0);
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label185;
            }

            if (var1) {
               label179:
               try {
                  System.load(var0);
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label179;
               }
            } else {
               label181:
               try {
                  System.loadLibrary(var0);
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label181;
               }
            }
            break label197;
         }

         Throwable var3 = var10000;
         var1 = false;
         al.d(var3.getMessage());
         al.d("[Native] Failed to load so: %s", var0);
         return var1;
      }

      try {
         al.a("[Native] Successfully loaded SO: %s", var0);
      } finally {
         ;
      }

      var1 = var2;
      return var1;
   }

   private void b(boolean var1) {
      synchronized(this){}
      Throwable var10000;
      boolean var10001;
      if (var1) {
         label55: {
            try {
               this.startNativeMonitor();
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label55;
            }

            return;
         }
      } else {
         label58: {
            try {
               this.c();
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label58;
            }

            return;
         }
      }

      Throwable var2 = var10000;
      throw var2;
   }

   private void c() {
      synchronized(this){}

      Throwable var10000;
      label321: {
         boolean var10001;
         try {
            if (!this.k) {
               al.d("[Native] Native crash report has already unregistered.");
               return;
            }
         } catch (Throwable var34) {
            var10000 = var34;
            var10001 = false;
            break label321;
         }

         boolean var24 = false;

         label322: {
            try {
               var24 = true;
               if (this.unregist() != null) {
                  al.a("[Native] Successfully closed native crash report.");
                  this.k = false;
                  var24 = false;
                  break label322;
               }

               var24 = false;
            } finally {
               if (var24) {
                  label301:
                  try {
                     al.c("[Native] Failed to close native crash report.");
                     break label301;
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label321;
                  }
               }
            }

            boolean var13 = false;

            try {
               var13 = true;
               Class var2 = Boolean.TYPE;
               Boolean var1 = Boolean.FALSE;
               ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", new Class[]{var2}, new Object[]{var1});
               this.k = false;
               al.a("[Native] Successfully closed native crash report.");
               var13 = false;
            } finally {
               if (var13) {
                  try {
                     al.c("[Native] Failed to close native crash report.");
                     this.j = false;
                     this.i = false;
                  } catch (Throwable var30) {
                     var10000 = var30;
                     var10001 = false;
                     break label321;
                  }

                  return;
               }
            }

         }

      }

      Throwable var35 = var10000;
      throw var35;
   }

   private void c(boolean var1) {
      synchronized(this){}

      try {
         if (this.l != var1) {
            al.a("user change native %b", var1);
            this.l = var1;
         }
      } finally {
         ;
      }

   }

   public static String getDumpFilePath() {
      synchronized(NativeCrashHandler.class){}

      String var0;
      try {
         var0 = a;
      } finally {
         ;
      }

      return var0;
   }

   public static NativeCrashHandler getInstance() {
      synchronized(NativeCrashHandler.class){}

      NativeCrashHandler var0;
      try {
         var0 = b;
      } finally {
         ;
      }

      return var0;
   }

   public static NativeCrashHandler getInstance(Context var0, aa var1, as var2, ac var3, ak var4, boolean var5, String var6) {
      synchronized(NativeCrashHandler.class){}

      NativeCrashHandler var9;
      try {
         if (b == null) {
            NativeCrashHandler var10 = new NativeCrashHandler(var0, var1, var2, var4, var5, var6);
            b = var10;
         }

         var9 = b;
      } finally {
         ;
      }

      return var9;
   }

   private native String getProperties(String var1);

   private native String getSoCpuAbi();

   public static boolean isShouldHandleInJava() {
      return n;
   }

   public static void setDumpFilePath(String var0) {
      synchronized(NativeCrashHandler.class){}

      try {
         a = var0;
      } finally {
         ;
      }

   }

   public static void setShouldHandleInJava(boolean var0) {
      n = var0;
      NativeCrashHandler var1 = b;
      if (var1 != null) {
         var1.a(999, String.valueOf(var0));
      }

   }

   public boolean appendLogToNative(String param1, String param2, String param3) {
      // $FF: Couldn't be decompiled
   }

   protected native boolean appendNativeLog(String var1, String var2, String var3);

   protected native boolean appendWholeNativeLog(String var1);

   public void checkUploadRecordCrash() {
      this.f.a(new Runnable(this) {
         final NativeCrashHandler a;

         {
            this.a = var1;
         }

         public final void run() {
            if (!ap.a(this.a.d, "native_record_lock")) {
               al.a("[Native] Failed to lock file for handling native crash record.");
            } else {
               if (!NativeCrashHandler.n) {
                  NativeCrashHandler.a(this.a, "false");
               }

               CrashDetailBean var17 = be.a(this.a.d, NativeCrashHandler.a, this.a.g);
               if (var17 != null) {
                  al.a("[Native] Get crash from native record.");
                  if (!this.a.m.a(var17, true)) {
                     this.a.m.b(var17, false);
                  }

                  be.a(false, NativeCrashHandler.a);
               }

               NativeCrashHandler var110 = this.a;
               long var11 = ap.b();
               long var13 = at.j;
               long var9 = ap.b();
               File var18 = new File(NativeCrashHandler.a);
               if (var18.exists() && var18.isDirectory()) {
                  label1110: {
                     Throwable var10000;
                     label1119: {
                        boolean var10001;
                        File[] var113;
                        try {
                           var113 = var18.listFiles();
                        } catch (Throwable var109) {
                           var10000 = var109;
                           var10001 = false;
                           break label1119;
                        }

                        if (var113 == null) {
                           break label1110;
                        }

                        try {
                           if (var113.length == 0) {
                              break label1110;
                           }
                        } catch (Throwable var108) {
                           var10000 = var108;
                           var10001 = false;
                           break label1119;
                        }

                        try {
                           Comparator var19 = new Comparator(var110) {
                              final NativeCrashHandler a;

                              {
                                 this.a = var1;
                              }

                              // $FF: synthetic method
                              public final int compare(Object var1, Object var2) {
                                 File var3 = (File)var1;
                                 return Long.compare(((File)var2).lastModified(), var3.lastModified());
                              }
                           };
                           Arrays.sort(var113, var19);
                        } catch (Throwable var107) {
                           var10000 = var107;
                           var10001 = false;
                           break label1119;
                        }

                        long var7 = 0L;

                        int var2;
                        try {
                           var2 = var113.length;
                        } catch (Throwable var106) {
                           var10000 = var106;
                           var10001 = false;
                           break label1119;
                        }

                        int var4 = 0;
                        int var5 = 0;

                        int var1;
                        int var3;
                        for(var1 = var5; var4 < var2; var1 = var3) {
                           File var111 = var113[var4];

                           long var15;
                           try {
                              var15 = var111.lastModified();
                              var7 += var111.length();
                           } catch (Throwable var103) {
                              var10000 = var103;
                              var10001 = false;
                              break label1119;
                           }

                           label1127: {
                              if (var15 >= var11 - var13 && var15 < var9 + 86400000L) {
                                 label1126: {
                                    try {
                                       if (var7 >= at.i) {
                                          break label1126;
                                       }
                                    } catch (Throwable var105) {
                                       var10000 = var105;
                                       var10001 = false;
                                       break label1119;
                                    }

                                    var3 = var1;
                                    break label1127;
                                 }
                              }

                              try {
                                 al.a("[Native] Delete record file: %s", var111.getAbsolutePath());
                              } catch (Throwable var102) {
                                 var10000 = var102;
                                 var10001 = false;
                                 break label1119;
                              }

                              int var6 = var5 + 1;
                              var5 = var6;
                              var3 = var1;

                              try {
                                 if (!var111.delete()) {
                                    break label1127;
                                 }
                              } catch (Throwable var104) {
                                 var10000 = var104;
                                 var10001 = false;
                                 break label1119;
                              }

                              var3 = var1 + 1;
                              var5 = var6;
                           }

                           ++var4;
                        }

                        label1064:
                        try {
                           al.c("[Native] Number of record files overdue: %d, has deleted: %d", var5, var1);
                           break label1110;
                        } catch (Throwable var101) {
                           var10000 = var101;
                           var10001 = false;
                           break label1064;
                        }
                     }

                     Throwable var112 = var10000;
                     al.a(var112);
                  }
               }

               ap.b(this.a.d, "native_record_lock");
            }
         }
      });
   }

   public void disableCatchAnrTrace() {
      if (VERSION.SDK_INT > 19) {
         c = 1;
      }

   }

   public void dumpAnrNativeStack() {
      this.a(19, "1");
   }

   public void enableCatchAnrTrace() {
      if (VERSION.SDK_INT > 19) {
         c |= 2;
      }

   }

   public boolean filterSigabrtSysLog() {
      return this.a(998, "true");
   }

   public String getLogFromNative() {
      if (!this.i && !this.j) {
         return null;
      } else {
         label58:
         try {
            try {
               if (this.j) {
                  return this.getNativeLog();
               }

               String var1 = (String)ap.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", (Class[])null, (Object[])null);
               return var1;
            } catch (UnsatisfiedLinkError var4) {
            }
         } catch (Throwable var5) {
            if (!al.a(var5)) {
               var5.printStackTrace();
            }
            break label58;
         }

         return null;
      }
   }

   public NativeExceptionHandler getNativeExceptionHandler() {
      return this.g;
   }

   protected native String getNativeKeyValueList();

   protected native String getNativeLog();

   public String getRunningCpuAbi() {
      try {
         String var1 = this.getSoCpuAbi();
         return var1;
      } finally {
         al.d("get so cpu abi failed，please upgrade bugly so version");
         return "";
      }
   }

   public String getSystemProperty(String var1) {
      return !this.j && !this.i ? "fail" : this.getProperties(var1);
   }

   public boolean isEnableCatchAnrTrace() {
      return (c & 2) == 2;
   }

   public boolean isUserOpened() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.l;
      } finally {
         ;
      }

      return var1;
   }

   public void onStrategyChanged(StrategyBean var1) {
      Throwable var10000;
      label172: {
         synchronized(this){}
         boolean var10001;
         if (var1 != null) {
            try {
               if (var1.f != this.k) {
                  al.d("server native changed to %b", var1.f);
               }
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label172;
            }
         }

         boolean var2;
         label164: {
            label163: {
               try {
                  if (ac.a().c().f && this.l) {
                     break label163;
                  }
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label172;
               }

               var2 = false;
               break label164;
            }

            var2 = true;
         }

         label154:
         try {
            if (var2 != this.k) {
               al.a("native changed to %b", var2);
               this.b(var2);
            }

            return;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            break label154;
         }
      }

      Throwable var15 = var10000;
      throw var15;
   }

   public boolean putKeyValueToNative(String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   protected native boolean putNativeKeyValue(String var1, String var2);

   protected native String regist(String var1, boolean var2, int var3);

   public void removeEmptyNativeRecordFiles() {
      be.c(a);
   }

   protected native String removeNativeKeyValue(String var1);

   public void resendSigquit() {
      this.a(20, "");
   }

   public boolean setNativeAppChannel(String var1) {
      return this.a(12, var1);
   }

   public boolean setNativeAppPackage(String var1) {
      return this.a(13, var1);
   }

   public boolean setNativeAppVersion(String var1) {
      return this.a(10, var1);
   }

   protected native void setNativeInfo(int var1, String var2);

   public boolean setNativeIsAppForeground(boolean var1) {
      String var2;
      if (var1) {
         var2 = "true";
      } else {
         var2 = "false";
      }

      return this.a(14, var2);
   }

   public boolean setNativeLaunchTime(long var1) {
      try {
         boolean var3 = this.a(15, String.valueOf(var1));
         return var3;
      } catch (NumberFormatException var5) {
         if (!al.a(var5)) {
            var5.printStackTrace();
         }

         return false;
      }
   }

   public boolean setNativeUserId(String var1) {
      return this.a(11, var1);
   }

   public void setUserOpened(boolean var1) {
      synchronized(this){}

      Throwable var10000;
      label168: {
         boolean var10001;
         boolean var2;
         ac var3;
         try {
            this.c(var1);
            var2 = this.isUserOpened();
            var3 = ac.a();
         } catch (Throwable var15) {
            var10000 = var15;
            var10001 = false;
            break label168;
         }

         var1 = var2;
         if (var3 != null) {
            label158: {
               label157: {
                  if (var2) {
                     try {
                        if (var3.c().f) {
                           break label157;
                        }
                     } catch (Throwable var14) {
                        var10000 = var14;
                        var10001 = false;
                        break label168;
                     }
                  }

                  var1 = false;
                  break label158;
               }

               var1 = true;
            }
         }

         label150:
         try {
            if (var1 != this.k) {
               al.a("native changed to %b", var1);
               this.b(var1);
            }

            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label150;
         }
      }

      Throwable var16 = var10000;
      throw var16;
   }

   public void startNativeMonitor() {
      synchronized(this){}

      Throwable var10000;
      label1440: {
         boolean var10001;
         label1444: {
            label1433:
            try {
               if (!this.j && !this.i) {
                  break label1433;
               }
               break label1444;
            } catch (Throwable var160) {
               var10000 = var160;
               var10001 = false;
               break label1440;
            }

            boolean var1;
            label1422: {
               label1421: {
                  try {
                     if (!ap.b(this.e.t)) {
                        break label1421;
                     }
                  } catch (Throwable var158) {
                     var10000 = var158;
                     var10001 = false;
                     break label1440;
                  }

                  var1 = false;
                  break label1422;
               }

               var1 = true;
            }

            label1442: {
               String var3;
               label1443: {
                  try {
                     if (at.b) {
                        break label1443;
                     }
                  } catch (Throwable var157) {
                     var10000 = var157;
                     var10001 = false;
                     break label1440;
                  }

                  String var4 = "Bugly_Native";

                  try {
                     var3 = this.e.t;
                  } catch (Throwable var153) {
                     var10000 = var153;
                     var10001 = false;
                     break label1440;
                  }

                  if (!var1) {
                     try {
                        this.e.getClass();
                     } catch (Throwable var152) {
                        var10000 = var152;
                        var10001 = false;
                        break label1440;
                     }

                     var3 = var4;
                  }

                  try {
                     this.j = a(var3, var1);
                     break label1442;
                  } catch (Throwable var151) {
                     var10000 = var151;
                     var10001 = false;
                     break label1440;
                  }
               }

               var3 = "Bugly_Native";
               if (var1) {
                  try {
                     var3 = this.e.t;
                  } catch (Throwable var156) {
                     var10000 = var156;
                     var10001 = false;
                     break label1440;
                  }
               }

               boolean var2;
               try {
                  var2 = a(var3, var1);
                  this.j = var2;
               } catch (Throwable var155) {
                  var10000 = var155;
                  var10001 = false;
                  break label1440;
               }

               if (!var2 && !var1) {
                  try {
                     this.i = a("NativeRQD", false);
                  } catch (Throwable var154) {
                     var10000 = var154;
                     var10001 = false;
                     break label1440;
                  }
               }
            }

            label1389: {
               try {
                  if (this.j) {
                     break label1389;
                  }

                  var1 = this.i;
               } catch (Throwable var150) {
                  var10000 = var150;
                  var10001 = false;
                  break label1440;
               }

               if (!var1) {
                  return;
               }
            }

            try {
               this.a(this.h);
               this.setNativeAppVersion(this.e.o);
               this.setNativeAppChannel(this.e.s);
               this.setNativeAppPackage(this.e.c);
               this.setNativeUserId(this.e.f());
               this.setNativeIsAppForeground(this.e.a());
               this.setNativeLaunchTime(this.e.a);
            } catch (Throwable var149) {
               var10000 = var149;
               var10001 = false;
               break label1440;
            }

            return;
         }

         try {
            this.a(this.h);
         } catch (Throwable var159) {
            var10000 = var159;
            var10001 = false;
            break label1440;
         }

         return;
      }

      Throwable var161 = var10000;
      throw var161;
   }

   protected native void testCrash();

   public void testNativeCrash() {
      if (!this.j) {
         al.d("[Native] Bugly SO file has not been load.");
      } else {
         this.testCrash();
      }
   }

   public void testNativeCrash(boolean var1, boolean var2, boolean var3) {
      this.a(16, String.valueOf(var1));
      this.a(17, String.valueOf(var2));
      this.a(18, String.valueOf(var3));
      this.testNativeCrash();
   }

   public void unBlockSigquit(boolean var1) {
      if (var1) {
         this.a(21, "true");
      } else {
         this.a(21, "false");
      }
   }

   protected native String unregist();
}
