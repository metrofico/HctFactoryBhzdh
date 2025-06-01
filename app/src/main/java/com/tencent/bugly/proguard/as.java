package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class as {
   public static int a;
   private static final Map h = new HashMap() {
      {
         this.put(3, new Pair("203", "103"));
         this.put(7, new Pair("208", "108"));
         this.put(0, new Pair("200", "100"));
         this.put(1, new Pair("201", "101"));
         this.put(2, new Pair("202", "102"));
         this.put(4, new Pair("204", "104"));
         this.put(6, new Pair("206", "106"));
         this.put(5, new Pair("207", "107"));
      }
   };
   private static final ArrayList i = new ArrayList() {
      {
         this.add(new b((byte)0));
         this.add(new c((byte)0));
         this.add(new d((byte)0));
         this.add(new e((byte)0));
         this.add(new h((byte)0));
         this.add(new i((byte)0));
         this.add(new f((byte)0));
         this.add(new g((byte)0));
      }
   };
   private static final Map j = new HashMap() {
      {
         Integer var2 = 3;
         Integer var1 = 4;
         this.put(var2, var1);
         Integer var3 = 7;
         this.put(var3, var3);
         var3 = 2;
         Integer var5 = 1;
         this.put(var3, var5);
         Integer var4 = 0;
         this.put(var4, var4);
         this.put(var5, var3);
         this.put(var1, var2);
         var1 = 5;
         this.put(var1, var1);
         var1 = 6;
         this.put(var1, var1);
      }
   };
   private static final Map k = new HashMap() {
      {
         this.put(3, "BuglyAnrCrash");
         this.put(0, "BuglyJavaCrash");
         this.put(1, "BuglyNativeCrash");
      }
   };
   private static final Map l = new HashMap() {
      {
         this.put(3, "BuglyAnrCrashReport");
         this.put(0, "BuglyJavaCrashReport");
         this.put(1, "BuglyNativeCrashReport");
      }
   };
   protected final Context b;
   protected final ai c;
   protected final w d;
   protected final ac e;
   protected aw f;
   protected BuglyStrategy.a g;

   public as(Context var1, ai var2, w var3, ac var4, BuglyStrategy.a var5) {
      a = 1004;
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
      this.g = var5;
      this.f = null;
   }

   private static CrashDetailBean a(Cursor var0) {
      if (var0 == null) {
         return null;
      } else {
         Throwable var10000;
         label156: {
            boolean var10001;
            byte[] var3;
            try {
               var3 = var0.getBlob(var0.getColumnIndex("_dt"));
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label156;
            }

            if (var3 == null) {
               return null;
            }

            CrashDetailBean var16;
            long var1;
            try {
               var1 = var0.getLong(var0.getColumnIndex("_id"));
               var16 = (CrashDetailBean)ap.a(var3, CrashDetailBean.CREATOR);
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label156;
            }

            if (var16 != null) {
               try {
                  var16.a = var1;
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label156;
               }
            }

            return var16;
         }

         Throwable var17 = var10000;
         if (!al.a(var17)) {
            var17.printStackTrace();
         }

         return null;
      }
   }

   private static CrashDetailBean a(List var0, CrashDetailBean var1) {
      if (var0.isEmpty()) {
         return var1;
      } else {
         CrashDetailBean var3 = null;
         ArrayList var4 = new ArrayList(10);
         Iterator var5 = var0.iterator();

         while(var5.hasNext()) {
            ar var2 = (ar)var5.next();
            if (var2.e) {
               var4.add(var2);
            }
         }

         CrashDetailBean var6 = var3;
         if (!var4.isEmpty()) {
            List var7 = c((List)var4);
            var6 = var3;
            if (var7 != null) {
               var6 = var3;
               if (!var7.isEmpty()) {
                  Collections.sort(var7);
                  var6 = (CrashDetailBean)var7.get(0);
                  a(var6, var7);
               }
            }
         }

         var3 = var6;
         if (var6 == null) {
            var1.j = true;
            var1.t = 0;
            var1.s = "";
            var3 = var1;
         }

         b(var3, var0);
         if (var3.r != var1.r && !var3.s.contains("" + var1.r)) {
            ++var3.t;
            var3.s = var3.s + var1.r + "\n";
         }

         return var3;
      }
   }

   private static bn a(String var0, Context var1, String var2) {
      if (var2 != null && var1 != null) {
         al.c("zip %s", var2);
         File var4 = new File(var2);
         File var65 = new File(var1.getCacheDir(), var0);
         if (!ap.a(var4, var65)) {
            al.d("zip fail!");
            return null;
         } else {
            ByteArrayOutputStream var67 = new ByteArrayOutputStream();

            FileInputStream var62;
            try {
               var62 = new FileInputStream(var65);
            } finally {
               ;
            }

            bn var66;
            label646: {
               Throwable var10000;
               label636: {
                  boolean var10001;
                  byte[] var63;
                  try {
                     var63 = new byte[4096];
                  } catch (Throwable var61) {
                     var10000 = var61;
                     var10001 = false;
                     break label636;
                  }

                  while(true) {
                     int var3;
                     try {
                        var3 = var62.read(var63);
                     } catch (Throwable var59) {
                        var10000 = var59;
                        var10001 = false;
                        break;
                     }

                     if (var3 <= 0) {
                        try {
                           var63 = var67.toByteArray();
                           al.c("read bytes :%d", var63.length);
                           var66 = new bn((byte)2, var65.getName(), var63);
                           break label646;
                        } catch (Throwable var58) {
                           var10000 = var58;
                           var10001 = false;
                           break;
                        }
                     }

                     try {
                        var67.write(var63, 0, var3);
                        var67.flush();
                     } catch (Throwable var60) {
                        var10000 = var60;
                        var10001 = false;
                        break;
                     }
                  }
               }

               Throwable var64 = var10000;

               try {
                  if (!al.a(var64)) {
                     var64.printStackTrace();
                  }
               } finally {
                  if (var62 != null) {
                     try {
                        var62.close();
                     } catch (IOException var55) {
                        if (!al.a(var55)) {
                           var55.printStackTrace();
                        }
                     }
                  }

                  if (var65.exists()) {
                     al.c("del tmp");
                     var65.delete();
                  }

               }

               return null;
            }

            try {
               var62.close();
            } catch (IOException var57) {
               if (!al.a(var57)) {
                  var57.printStackTrace();
               }
            }

            if (var65.exists()) {
               al.c("del tmp");
               var65.delete();
            }

            return var66;
         }
      } else {
         al.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}");
         return null;
      }
   }

   private static bo a(Context var0, CrashDetailBean var1, aa var2) {
      ArrayList var10 = null;
      boolean var5 = false;
      if (var0 != null && var1 != null && var2 != null) {
         bo var11 = new bo();
         var11.a = e(var1);
         var11.b = var1.r;
         var11.c = var1.n;
         var11.d = var1.o;
         var11.e = var1.p;
         var11.g = var1.q;
         var11.h = var1.z;
         var11.i = var1.c;
         var11.j = null;
         var11.l = var1.m;
         var11.m = var1.e;
         var11.f = var1.B;
         var11.n = null;
         ArrayList var9 = var10;
         if (var1.h != null) {
            if (var1.h.isEmpty()) {
               var9 = var10;
            } else {
               var10 = new ArrayList(var1.h.size());
               Iterator var12 = var1.h.entrySet().iterator();

               while(true) {
                  var9 = var10;
                  if (!var12.hasNext()) {
                     break;
                  }

                  Map.Entry var13 = (Map.Entry)var12.next();
                  bl var17 = new bl();
                  var17.a = ((PlugInBean)var13.getValue()).a;
                  var17.c = ((PlugInBean)var13.getValue()).c;
                  var17.e = ((PlugInBean)var13.getValue()).b;
                  var10.add(var17);
               }
            }
         }

         var11.p = var9;
         al.c("libInfo %s", var11.o);
         var9 = new ArrayList(20);
         a(var9, var1);
         a(var9, var1.w);
         b(var9, var1.x);
         c(var9, var1.Z);
         a(var9, var1.aa, var0);
         a(var9, var1.y);
         a(var9, var1, var0);
         b(var9, var1, var0);
         a(var9, var2.L);
         b(var9, var1.Y);
         var11.q = var9;
         if (var1.j) {
            var11.k = var1.t;
         }

         var11.r = a(var1, var2);
         var11.s = new HashMap();
         if (var1.S != null && var1.S.size() > 0) {
            var11.s.putAll(var1.S);
            al.a("setted message size %d", var11.s.size());
         }

         Map var14 = var11.s;
         al.c("pss:" + var1.I + " vss:" + var1.J + " javaHeap:" + var1.K);
         var14.put("SDK_UPLOAD_U1", "" + var1.I);
         var14.put("SDK_UPLOAD_U2", "" + var1.J);
         var14.put("SDK_UPLOAD_U3", "" + var1.K);
         String var18 = var1.n;
         String var15 = var1.c;
         String var16 = var2.d();
         long var3 = (var1.r - var1.Q) / 1000L;
         boolean var8 = var1.k;
         boolean var6 = var1.R;
         boolean var7 = var1.j;
         if (var1.b == 1) {
            var5 = true;
         }

         al.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", var18, var15, var16, var3, var8, var6, var7, var5, var1.t, var1.s, var1.d, var11.r.size());
         return var11;
      } else {
         al.d("enExp args == null");
         return null;
      }
   }

   private static bp a(Context var0, List var1, aa var2) {
      if (var0 != null && var1 != null && var1.size() != 0 && var2 != null) {
         bp var3 = new bp();
         var3.a = new ArrayList();
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            CrashDetailBean var5 = (CrashDetailBean)var4.next();
            var3.a.add(a(var0, var5, var2));
         }

         return var3;
      } else {
         al.d("enEXPPkg args == null!");
         return null;
      }
   }

   public static List a() {
      StrategyBean var4 = ac.a().c();
      if (var4 == null) {
         al.d("have not synced remote!");
         return null;
      } else if (!var4.f) {
         al.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.");
         al.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.");
         return null;
      } else {
         long var0 = System.currentTimeMillis();
         long var2 = ap.b();
         List var9 = b();
         al.c("Size of crash list loaded from DB: %s", var9.size());
         if (var9 != null && var9.size() > 0) {
            ArrayList var6 = new ArrayList();
            ArrayList var5 = new ArrayList();
            var6.addAll(a(var9));
            var9.removeAll(var6);
            Iterator var7 = var9.iterator();

            while(var7.hasNext()) {
               ar var8 = (ar)var7.next();
               if (var8.b < var2 - at.j) {
                  var5.add(var8);
                  var7.remove();
                  var6.add(var8);
               } else if (var8.d) {
                  if (var8.b >= var0 - 86400000L) {
                     var7.remove();
                  } else if (!var8.e) {
                     var7.remove();
                     var6.add(var8);
                  }
               } else if ((long)var8.f >= 3L && var8.b < var0 - 86400000L) {
                  var7.remove();
                  var6.add(var8);
               }
            }

            b((List)var5);
            if (var6.size() > 0) {
               d((List)var6);
            }

            var5 = new ArrayList();
            List var11 = c(var9);
            if (var11 != null && var11.size() > 0) {
               String var10 = aa.b().o;
               var7 = var11.iterator();

               while(var7.hasNext()) {
                  CrashDetailBean var12 = (CrashDetailBean)var7.next();
                  if (!var10.equals(var12.f)) {
                     var7.remove();
                     var5.add(var12);
                  }
               }
            }

            if (var5.size() > 0) {
               e((List)var5);
            }

            return var11;
         } else {
            return null;
         }
      }
   }

   private static List a(List var0) {
      if (var0 != null && var0.size() != 0) {
         long var1 = System.currentTimeMillis();
         ArrayList var3 = new ArrayList();
         Iterator var4 = var0.iterator();

         while(var4.hasNext()) {
            ar var5 = (ar)var4.next();
            if (var5.d && var5.b <= var1 - 86400000L) {
               var3.add(var5);
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   private static Map a(CrashDetailBean var0, aa var1) {
      HashMap var4 = new HashMap(30);

      Exception var10000;
      label57: {
         StringBuilder var5;
         boolean var10001;
         try {
            var5 = new StringBuilder();
            var4.put("A9", var5.append(var0.C).toString());
            var5 = new StringBuilder();
            var4.put("A11", var5.append(var0.D).toString());
            var5 = new StringBuilder();
            var4.put("A10", var5.append(var0.E).toString());
            var5 = new StringBuilder();
            var4.put("A23", var5.append(var0.f).toString());
            var5 = new StringBuilder();
            var1.getClass();
            var4.put("A7", var5.toString());
            var5 = new StringBuilder();
            var4.put("A6", var5.append(aa.n()).toString());
            var5 = new StringBuilder();
            var4.put("A5", var5.append(var1.m()).toString());
            var5 = new StringBuilder();
            var4.put("A22", var5.append(var1.g()).toString());
            var5 = new StringBuilder();
            var4.put("A2", var5.append(var0.G).toString());
            var5 = new StringBuilder();
            var4.put("A1", var5.append(var0.F).toString());
            var5 = new StringBuilder();
            var4.put("A24", var5.append(var1.k).toString());
            var5 = new StringBuilder();
            var4.put("A17", var5.append(var0.H).toString());
            var5 = new StringBuilder();
            var4.put("A25", var5.append(var1.g()).toString());
            var5 = new StringBuilder();
            var4.put("A15", var5.append(var1.q()).toString());
            var5 = new StringBuilder();
            var4.put("A13", var5.append(var1.r()).toString());
            var5 = new StringBuilder();
            var4.put("A34", var5.append(var0.A).toString());
            if (var1.G != null) {
               var5 = new StringBuilder();
               var4.put("productIdentify", var5.append(var1.G).toString());
            }
         } catch (Exception var10) {
            var10000 = var10;
            var10001 = false;
            break label57;
         }

         int var2;
         try {
            var5 = new StringBuilder();
            var4.put("A26", var5.append(URLEncoder.encode(var0.L, "utf-8")).toString());
            var2 = var0.b;
         } catch (Exception var9) {
            var10000 = var9;
            var10001 = false;
            break label57;
         }

         boolean var3 = true;
         if (var2 == 1) {
            try {
               var5 = new StringBuilder();
               var4.put("A27", var5.append(var0.O).toString());
               var5 = new StringBuilder();
               var4.put("A28", var5.append(var0.N).toString());
               var5 = new StringBuilder();
               var4.put("A29", var5.append(var0.k).toString());
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
               break label57;
            }
         }

         label39: {
            try {
               var5 = new StringBuilder();
               var4.put("A30", var5.append(var0.P).toString());
               var5 = new StringBuilder();
               var4.put("A18", var5.append(var0.Q).toString());
               var5 = new StringBuilder();
               if (!var0.R) {
                  break label39;
               }
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break label57;
            }

            var3 = false;
         }

         try {
            var4.put("A36", var5.append(var3).toString());
            var5 = new StringBuilder();
            var4.put("F02", var5.append(var1.z).toString());
            var5 = new StringBuilder();
            var4.put("F03", var5.append(var1.A).toString());
            var5 = new StringBuilder();
            var4.put("F04", var5.append(var1.d()).toString());
            var5 = new StringBuilder();
            var4.put("F05", var5.append(var1.B).toString());
            var5 = new StringBuilder();
            var4.put("F06", var5.append(var1.y).toString());
            var5 = new StringBuilder();
            var4.put("F08", var5.append(var1.E).toString());
            var5 = new StringBuilder();
            var4.put("F09", var5.append(var1.F).toString());
            var5 = new StringBuilder();
            var4.put("F10", var5.append(var1.C).toString());
            a((Map)var4, (CrashDetailBean)var0);
            return var4;
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
         }
      }

      Exception var11 = var10000;
      var11.printStackTrace();
      al.a(var11);
      return var4;
   }

   private static void a(CrashDetailBean var0, List var1) {
      StringBuilder var5 = new StringBuilder(128);

      for(int var2 = 1; var2 < var1.size(); ++var2) {
         CrashDetailBean var6 = (CrashDetailBean)var1.get(var2);
         if (var6.s != null) {
            String[] var7 = var6.s.split("\n");
            if (var7 != null) {
               int var4 = var7.length;

               for(int var3 = 0; var3 < var4; ++var3) {
                  String var8 = var7[var3];
                  if (!var0.s.contains(var8)) {
                     ++var0.t;
                     var5.append(var8).append("\n");
                  }
               }
            }
         }
      }

      var0.s = var0.s + var5.toString();
   }

   private static void a(CrashDetailBean var0, Map var1) {
      if (var1 != null && !var1.isEmpty()) {
         var0.S = new LinkedHashMap(var1.size());
         Iterator var3 = var1.entrySet().iterator();

         while(true) {
            Map.Entry var4;
            do {
               if (!var3.hasNext()) {
                  return;
               }

               var4 = (Map.Entry)var3.next();
            } while(ap.b((String)var4.getKey()));

            String var2 = (String)var4.getKey();
            String var5 = var2;
            if (var2.length() > 100) {
               var5 = var2.substring(0, 100);
               al.d("setted key length is over limit %d substring to %s", 100, var5);
            }

            if (!ap.b((String)var4.getValue()) && ((String)var4.getValue()).length() > 100000) {
               var2 = ((String)var4.getValue()).substring(((String)var4.getValue()).length() - 100000);
               al.d("setted %s value length is over limit %d substring", var5, 100000);
            } else {
               var2 = (String)var4.getValue();
            }

            var0.S.put(var5, var2);
            al.a("add setted key %s value size:%d", var5, var2.length());
         }
      } else {
         al.d("extra map is empty. CrashBean won't have userDatas.");
      }
   }

   public static void a(String var0, String var1, String var2, String var3, String var4, CrashDetailBean var5) {
      aa var6 = aa.b();
      if (var6 != null) {
         al.e("#++++++++++Record By Bugly++++++++++#");
         al.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!");
         al.e("# PKG NAME: %s", var6.c);
         al.e("# APP VER: %s", var6.o);
         al.e("# SDK VER: %s", var6.h);
         al.e("# LAUNCH TIME: %s", ap.a(new Date(aa.b().a)));
         al.e("# CRASH TYPE: %s", var0);
         al.e("# CRASH TIME: %s", var1);
         al.e("# CRASH PROCESS: %s", var2);
         al.e("# CRASH FOREGROUND: %s", var6.a());
         al.e("# CRASH THREAD: %s", var3);
         if (var5 != null) {
            al.e("# REPORT ID: %s", var5.c);
            var1 = var6.h();
            if (var6.r()) {
               var0 = "ROOTED";
            } else {
               var0 = "UNROOT";
            }

            al.e("# CRASH DEVICE: %s %s", var1, var0);
            al.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", var5.C, var5.D, var5.E);
            al.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", var5.F, var5.G, var5.H);
            if (!ap.b(var5.O)) {
               al.e("# EXCEPTION FIRED BY %s %s", var5.O, var5.N);
            } else if (var5.b == 3) {
               if (var5.T == null) {
                  var0 = "null";
               } else {
                  var0 = (String)var5.T.get("BUGLY_CR_01");
               }

               al.e("# EXCEPTION ANR MESSAGE:\n %s", var0);
            }
         }

         if (!ap.b(var4)) {
            al.e("# CRASH STACK: ");
            al.e(var4);
         }

         al.e("#++++++++++++++++++++++++++++++++++++++++++#");
      }
   }

   private static void a(ArrayList var0, CrashDetailBean var1) {
      if (var1.j) {
         if (var1.s != null && var1.s.length() > 0) {
            try {
               bn var2 = new bn((byte)1, "alltimes.txt", var1.s.getBytes("utf-8"));
               var0.add(var2);
               return;
            } catch (Exception var3) {
               var3.printStackTrace();
               al.a(var3);
            }
         }

      }
   }

   private static void a(ArrayList var0, CrashDetailBean var1, Context var2) {
      if (var1.b == 3) {
         al.c("crashBean.anrMessages:%s", var1.T);

         Exception var10000;
         label56: {
            boolean var10001;
            label48: {
               try {
                  if (var1.T == null || !var1.T.containsKey("BUGLY_CR_01")) {
                     break label48;
                  }

                  if (!TextUtils.isEmpty((CharSequence)var1.T.get("BUGLY_CR_01"))) {
                     bn var3 = new bn((byte)1, "anrMessage.txt", ((String)var1.T.get("BUGLY_CR_01")).getBytes("utf-8"));
                     var0.add(var3);
                     al.c("attach anr message");
                  }
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label56;
               }

               try {
                  var1.T.remove("BUGLY_CR_01");
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label56;
               }
            }

            bn var9;
            try {
               if (var1.v == null) {
                  return;
               }

               var9 = a("trace.zip", var2, var1.v);
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label56;
            }

            if (var9 == null) {
               return;
            }

            try {
               al.c("attach traces");
               var0.add(var9);
               return;
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
            }
         }

         Exception var8 = var10000;
         var8.printStackTrace();
         al.a(var8);
      }
   }

   private static void a(ArrayList var0, String var1) {
      if (var1 != null) {
         try {
            bn var2 = new bn((byte)1, "log.txt", var1.getBytes("utf-8"));
            var0.add(var2);
            return;
         } catch (Exception var3) {
            var3.printStackTrace();
            al.a(var3);
         }
      }

   }

   private static void a(ArrayList var0, String var1, Context var2) {
      if (var1 != null) {
         Exception var10000;
         label27: {
            boolean var10001;
            bn var6;
            try {
               var6 = a("backupRecord.zip", var2, var1);
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
               break label27;
            }

            if (var6 != null) {
               try {
                  al.c("attach backup record");
                  var0.add(var6);
               } catch (Exception var3) {
                  var10000 = var3;
                  var10001 = false;
                  break label27;
               }
            }

            return;
         }

         Exception var5 = var10000;
         al.a(var5);
      }

   }

   private static void a(ArrayList var0, List var1) {
      if (var1 != null && !var1.isEmpty()) {
         StringBuilder var2 = new StringBuilder();
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            var2.append((String)var4.next());
         }

         try {
            bn var5 = new bn((byte)1, "martianlog.txt", var2.toString().getBytes("utf-8"));
            var0.add(var5);
            al.c("attach pageTracingList");
            return;
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   private static void a(ArrayList var0, byte[] var1) {
      if (var1 != null && var1.length > 0) {
         try {
            bn var2 = new bn((byte)2, "buglylog.zip", var1);
            al.c("attach user log");
            var0.add(var2);
            return;
         } catch (Exception var3) {
            al.a(var3);
         }
      }

   }

   // $FF: synthetic method
   static void a(List var0, boolean var1, long var2, String var4, String var5) {
      if (var0 != null && !var0.isEmpty()) {
         ArrayList var6 = new ArrayList();
         Iterator var7 = var0.iterator();

         while(var7.hasNext()) {
            CrashDetailBean var9 = (CrashDetailBean)var7.next();
            String var8 = (String)l.get(var9.b);
            if (!TextUtils.isEmpty(var8)) {
               var6.add(new ag.c(var9.c, var8, var9.r, var1, var2, var4, var5));
            }
         }

         ag.a.a().a((List)var6);
      }

   }

   private static void a(Map var0, CrashDetailBean var1) {
      if (var1.U >= 0) {
         var0.put("C01", "" + var1.U);
      }

      if (var1.V >= 0) {
         var0.put("C02", "" + var1.V);
      }

      if (var1.W != null && var1.W.size() > 0) {
         Iterator var3 = var1.W.entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry var2 = (Map.Entry)var3.next();
            var0.put("C03_" + (String)var2.getKey(), var2.getValue());
         }
      }

      if (var1.X != null && var1.X.size() > 0) {
         Iterator var5 = var1.X.entrySet().iterator();

         while(var5.hasNext()) {
            Map.Entry var4 = (Map.Entry)var5.next();
            var0.put("C04_" + (String)var4.getKey(), var4.getValue());
         }
      }

   }

   public static void a(boolean var0, List var1) {
      if (var1 != null && var1.size() > 0) {
         al.c("up finish update state %b", var0);
         Iterator var2 = var1.iterator();

         while(var2.hasNext()) {
            CrashDetailBean var3 = (CrashDetailBean)var2.next();
            al.c("pre uid:%s uc:%d re:%b me:%b", var3.c, var3.l, var3.d, var3.j);
            ++var3.l;
            var3.d = var0;
            al.c("set uid:%s uc:%d re:%b me:%b", var3.c, var3.l, var3.d, var3.j);
         }

         Iterator var5 = var1.iterator();

         while(var5.hasNext()) {
            CrashDetailBean var4 = (CrashDetailBean)var5.next();
            at.a().a(var4);
         }

         al.c("update state size %d", var1.size());
      }

      if (!var0) {
         al.b("[crash] upload fail.");
      }

   }

   private static boolean a(CrashDetailBean var0, List var1, List var2) {
      Iterator var5 = var1.iterator();
      boolean var3 = false;

      while(var5.hasNext()) {
         ar var4 = (ar)var5.next();
         if (var0.u.equals(var4.c)) {
            if (var4.e) {
               var3 = true;
            }

            var2.add(var4);
         }
      }

      return var3;
   }

   private static boolean a(String var0) {
      if (at.r != null && !at.r.isEmpty()) {
         try {
            al.c("Crash regular filter for crash stack is: %s", at.r);
            if (Pattern.compile(at.r).matcher(var0).find()) {
               al.d("This crash matches the regular filter string set. It will not be record and upload.");
               return true;
            }
         } catch (Exception var1) {
            al.a(var1);
            al.d("Failed to compile " + at.r);
         }
      }

      return false;
   }

   private static ar b(Cursor var0) {
      if (var0 == null) {
         return null;
      } else {
         Throwable var10000;
         label236: {
            int var1;
            boolean var10001;
            ar var4;
            try {
               var4 = new ar();
               var4.a = var0.getLong(var0.getColumnIndex("_id"));
               var4.b = var0.getLong(var0.getColumnIndex("_tm"));
               var4.c = var0.getString(var0.getColumnIndex("_s1"));
               var1 = var0.getInt(var0.getColumnIndex("_up"));
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label236;
            }

            boolean var3 = false;
            boolean var2;
            if (var1 == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            try {
               var4.d = var2;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label236;
            }

            var2 = var3;

            label222: {
               try {
                  if (var0.getInt(var0.getColumnIndex("_me")) != 1) {
                     break label222;
                  }
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label236;
               }

               var2 = true;
            }

            label217:
            try {
               var4.e = var2;
               var4.f = var0.getInt(var0.getColumnIndex("_uc"));
               return var4;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label217;
            }
         }

         Throwable var25 = var10000;
         if (!al.a(var25)) {
            var25.printStackTrace();
         }

         return null;
      }
   }

   private static List b() {
      ArrayList var5 = new ArrayList();
      Cursor var2 = null;

      Throwable var4;
      label1672: {
         Cursor var3;
         try {
            var3 = w.a().a("t_cr", (String[])(new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}), (String)null);
         } catch (Throwable var153) {
            var4 = var153;
            break label1672;
         }

         if (var3 == null) {
            if (var3 != null) {
               var3.close();
            }

            return null;
         }

         Throwable var10000;
         label1673: {
            int var0;
            boolean var10001;
            try {
               var0 = var3.getCount();
            } catch (Throwable var162) {
               var10000 = var162;
               var10001 = false;
               break label1673;
            }

            if (var0 <= 0) {
               if (var3 != null) {
                  var3.close();
               }

               return var5;
            }

            StringBuilder var167;
            try {
               var167 = new StringBuilder();
               var167.append("_id in (");
            } catch (Throwable var161) {
               var10000 = var161;
               var10001 = false;
               break label1673;
            }

            byte var163 = 0;

            while(true) {
               boolean var1;
               try {
                  var1 = var3.moveToNext();
               } catch (Throwable var156) {
                  var10000 = var156;
                  var10001 = false;
                  break;
               }

               if (!var1) {
                  StringBuilder var165 = var167;

                  try {
                     if (var167.toString().contains(",")) {
                        var165 = new StringBuilder(var167.substring(0, var167.lastIndexOf(",")));
                     }
                  } catch (Throwable var155) {
                     var10000 = var155;
                     var10001 = false;
                     break;
                  }

                  String var168;
                  try {
                     var165.append(")");
                     var168 = var165.toString();
                     var165.setLength(0);
                  } catch (Throwable var154) {
                     var10000 = var154;
                     var10001 = false;
                     break;
                  }

                  if (var163 > 0) {
                     try {
                        al.d("deleted %s illegal data %d", "t_cr", w.a().a("t_cr", var168));
                     } catch (Throwable var152) {
                        var10000 = var152;
                        var10001 = false;
                        break;
                     }
                  }

                  if (var3 != null) {
                     var3.close();
                  }

                  return var5;
               }

               ar var164;
               try {
                  var164 = b(var3);
               } catch (Throwable var159) {
                  var10000 = var159;
                  var10001 = false;
                  break;
               }

               if (var164 != null) {
                  try {
                     var5.add(var164);
                  } catch (Throwable var158) {
                     var10000 = var158;
                     var10001 = false;
                     break;
                  }
               } else {
                  boolean var78 = false;

                  try {
                     var78 = true;
                     var167.append(var3.getLong(var3.getColumnIndex("_id"))).append(",");
                     var78 = false;
                  } finally {
                     if (var78) {
                        try {
                           al.d("unknown id!");
                           continue;
                        } catch (Throwable var157) {
                           var10000 = var157;
                           var10001 = false;
                           break;
                        }
                     }
                  }

               }
            }
         }

         Throwable var166 = var10000;
         var4 = var166;
         var2 = var3;
      }

      try {
         if (!al.a(var4)) {
            var4.printStackTrace();
         }
      } finally {
         if (var2 != null) {
            var2.close();
         }

      }

      return var5;
   }

   private static void b(CrashDetailBean var0, List var1) {
      StringBuilder var2 = new StringBuilder(64);
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         ar var4 = (ar)var3.next();
         if (!var4.e && !var4.d && !var0.s.contains("" + var4.b)) {
            ++var0.t;
            var2.append(var4.b).append("\n");
         }
      }

      var0.s = var0.s + var2.toString();
   }

   private static void b(ArrayList var0, CrashDetailBean var1, Context var2) {
      if (var1.b == 1) {
         if (var1.v != null) {
            Exception var10000;
            label33: {
               boolean var10001;
               bn var6;
               try {
                  var6 = a("tomb.zip", var2, var1.v);
               } catch (Exception var4) {
                  var10000 = var4;
                  var10001 = false;
                  break label33;
               }

               if (var6 != null) {
                  try {
                     al.c("attach tombs");
                     var0.add(var6);
                  } catch (Exception var3) {
                     var10000 = var3;
                     var10001 = false;
                     break label33;
                  }
               }

               return;
            }

            Exception var5 = var10000;
            al.a(var5);
         }

      }
   }

   private static void b(ArrayList var0, String var1) {
      if (var1 != null) {
         try {
            bn var2 = new bn((byte)1, "jniLog.txt", var1.getBytes("utf-8"));
            var0.add(var2);
            return;
         } catch (Exception var3) {
            var3.printStackTrace();
            al.a(var3);
         }
      }

   }

   private static void b(ArrayList var0, byte[] var1) {
      if (var1 != null && var1.length > 0) {
         try {
            bn var2 = new bn((byte)1, "userExtraByteData", var1);
            var0.add(var2);
            al.c("attach extraData");
            return;
         } catch (Exception var3) {
            al.a(var3);
         }
      }

   }

   private static void b(List var0) {
      List var1 = c(var0);
      if (var1 != null && !var1.isEmpty()) {
         ArrayList var4 = new ArrayList();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            CrashDetailBean var2 = (CrashDetailBean)var3.next();
            String var5 = (String)l.get(var2.b);
            if (!TextUtils.isEmpty(var5)) {
               al.c("find expired data,crashId:%s eventType:%s", var2.c, var5);
               var4.add(new ag.c(var2.c, var5, var2.r, false, 0L, "expired", (String)null));
            }
         }

         ag.a.a().a((List)var4);
      }

   }

   private boolean b(CrashDetailBean var1, List var2, List var3) {
      int var5 = var1.b;
      boolean var4;
      if (var5 != 0 && var5 != 1) {
         var4 = false;
      } else {
         var4 = true;
      }

      boolean var14;
      if (var5 == 3) {
         var14 = true;
      } else {
         var14 = false;
      }

      boolean var6;
      if (!p.c) {
         if (!var14 && !var4) {
            var6 = true;
         } else {
            var6 = at.e;
         }
      } else {
         var6 = false;
      }

      if (!var6) {
         return false;
      } else {
         Exception var10000;
         label79: {
            ArrayList var7 = new ArrayList(10);
            boolean var10001;
            if (!a((CrashDetailBean)var1, (List)var2, (List)var7)) {
               try {
                  if (var7.size() < at.d) {
                     return false;
                  }
               } catch (Exception var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label79;
               }
            }

            Iterator var13;
            try {
               al.a("same crash occur too much do merged!");
               var1 = a((List)var7, (CrashDetailBean)var1);
               var13 = var7.iterator();
            } catch (Exception var9) {
               var10000 = var9;
               var10001 = false;
               break label79;
            }

            while(true) {
               try {
                  if (!var13.hasNext()) {
                     break;
                  }

                  ar var15 = (ar)var13.next();
                  if (var15.a != var1.a) {
                     var3.add(var15);
                  }
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label79;
               }
            }

            try {
               this.b(var1);
               d(var3);
               al.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately");
               return true;
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
            }
         }

         Exception var12 = var10000;
         al.a(var12);
         al.d("Failed to merge crash.");
         return false;
      }
   }

   private static ContentValues c(CrashDetailBean var0) {
      if (var0 == null) {
         return null;
      } else {
         Throwable var10000;
         label261: {
            boolean var10001;
            ContentValues var4;
            try {
               var4 = new ContentValues();
               if (var0.a > 0L) {
                  var4.put("_id", var0.a);
               }
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label261;
            }

            boolean var3;
            try {
               var4.put("_tm", var0.r);
               var4.put("_s1", var0.u);
               var3 = var0.d;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label261;
            }

            byte var2 = 1;
            byte var1;
            if (var3) {
               var1 = 1;
            } else {
               var1 = 0;
            }

            label249: {
               label248: {
                  try {
                     var4.put("_up", Integer.valueOf(var1));
                     if (var0.j) {
                        break label248;
                     }
                  } catch (Throwable var22) {
                     var10000 = var22;
                     var10001 = false;
                     break label261;
                  }

                  var1 = 0;
                  break label249;
               }

               var1 = var2;
            }

            label242:
            try {
               var4.put("_me", Integer.valueOf(var1));
               var4.put("_uc", var0.l);
               var4.put("_dt", ap.a((Parcelable)var0));
               return var4;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label242;
            }
         }

         Throwable var25 = var10000;
         if (!al.a(var25)) {
            var25.printStackTrace();
         }

         return null;
      }
   }

   private static List c(List var0) {
      if (var0 != null && var0.size() != 0) {
         StringBuilder var2 = new StringBuilder();
         var2.append("_id in (");
         Iterator var116 = var0.iterator();

         while(var116.hasNext()) {
            var2.append(((ar)var116.next()).a).append(",");
         }

         StringBuilder var117 = var2;
         if (var2.toString().contains(",")) {
            var117 = new StringBuilder(var2.substring(0, var2.lastIndexOf(",")));
         }

         var117.append(")");
         String var119 = var117.toString();
         var117.setLength(0);

         Cursor var3;
         try {
            var3 = w.a().a("t_cr", (String[])null, (String)var119);
         } finally {
            ;
         }

         if (var3 == null) {
            if (var3 != null) {
               var3.close();
            }

            return null;
         } else {
            ArrayList var4;
            label1354: {
               Throwable var10000;
               label1355: {
                  boolean var10001;
                  try {
                     var4 = new ArrayList();
                     var117.append("_id in (");
                  } catch (Throwable var115) {
                     var10000 = var115;
                     var10001 = false;
                     break label1355;
                  }

                  byte var1 = 0;

                  while(true) {
                     CrashDetailBean var120;
                     try {
                        if (!var3.moveToNext()) {
                           break;
                        }

                        var120 = a(var3);
                     } catch (Throwable var114) {
                        var10000 = var114;
                        var10001 = false;
                        break label1355;
                     }

                     if (var120 != null) {
                        try {
                           var4.add(var120);
                        } catch (Throwable var112) {
                           var10000 = var112;
                           var10001 = false;
                           break label1355;
                        }
                     } else {
                        boolean var65 = false;

                        try {
                           var65 = true;
                           var117.append(var3.getLong(var3.getColumnIndex("_id"))).append(",");
                           var65 = false;
                        } finally {
                           if (var65) {
                              try {
                                 al.d("unknown id!");
                                 continue;
                              } catch (Throwable var111) {
                                 var10000 = var111;
                                 var10001 = false;
                                 break label1355;
                              }
                           }
                        }

                     }
                  }

                  var2 = var117;

                  try {
                     if (var117.toString().contains(",")) {
                        var2 = new StringBuilder(var117.substring(0, var117.lastIndexOf(",")));
                     }
                  } catch (Throwable var110) {
                     var10000 = var110;
                     var10001 = false;
                     break label1355;
                  }

                  String var118;
                  try {
                     var2.append(")");
                     var118 = var2.toString();
                  } catch (Throwable var109) {
                     var10000 = var109;
                     var10001 = false;
                     break label1355;
                  }

                  if (var1 <= 0) {
                     break label1354;
                  }

                  label1308:
                  try {
                     al.d("deleted %s illegal data %d", "t_cr", w.a().a("t_cr", var118));
                     break label1354;
                  } catch (Throwable var108) {
                     var10000 = var108;
                     var10001 = false;
                     break label1308;
                  }
               }

               Throwable var121 = var10000;

               try {
                  if (!al.a(var121)) {
                     var121.printStackTrace();
                  }
               } finally {
                  if (var3 != null) {
                     var3.close();
                  }

               }

               return null;
            }

            if (var3 != null) {
               var3.close();
            }

            return var4;
         }
      } else {
         return null;
      }
   }

   private static void c(ArrayList var0, String var1) {
      if (!ap.b(var1)) {
         try {
            bn var2 = new bn((byte)1, "crashInfos.txt", var1.getBytes("utf-8"));
            al.c("attach crash infos");
            var0.add(var2);
            return;
         } catch (Exception var3) {
            var3.printStackTrace();
            al.a(var3);
         }
      }

   }

   private static void d(List var0) {
      if (var0 != null && var0.size() != 0) {
         StringBuilder var1 = new StringBuilder();
         var1.append("_id in (");
         Iterator var4 = var0.iterator();

         while(var4.hasNext()) {
            var1.append(((ar)var4.next()).a).append(",");
         }

         StringBuilder var5 = new StringBuilder(var1.substring(0, var1.lastIndexOf(",")));
         var5.append(")");
         String var6 = var5.toString();
         var5.setLength(0);

         label57:
         try {
            al.c("deleted %s data %d", "t_cr", w.a().a("t_cr", var6));
            return;
         } catch (Throwable var3) {
            if (!al.a(var3)) {
               var3.printStackTrace();
            }
            break label57;
         }
      }

   }

   private boolean d(CrashDetailBean var1) {
      Throwable var10000;
      label487: {
         StringBuilder var65;
         String var2;
         String var3;
         boolean var10001;
         try {
            al.c("save eup logs");
            aa var4 = aa.b();
            String var5 = var4.e();
            var2 = var4.o;
            var3 = var1.A;
            StringBuilder var6 = new StringBuilder("#--------\npackage:");
            var65 = var6.append(var5).append("\nversion:").append(var2).append("\nsdk:").append(var4.h).append("\nprocess:").append(var3).append("\ndate:");
            Date var68 = new Date(var1.r);
            var3 = var65.append(ap.a(var68)).append("\ntype:").append(var1.n).append("\nmessage:").append(var1.o).append("\nstack:\n").append(var1.q).append("\neupID:").append(var1.c).append("\n").toString();
         } catch (Throwable var62) {
            var10000 = var62;
            var10001 = false;
            break label487;
         }

         String var63 = null;

         label488: {
            label489: {
               try {
                  if (at.m != null) {
                     break label489;
                  }
               } catch (Throwable var61) {
                  var10000 = var61;
                  var10001 = false;
                  break label487;
               }

               try {
                  if (Environment.getExternalStorageState().equals("mounted")) {
                     var2 = Environment.getExternalStorageDirectory().getAbsolutePath();
                     StringBuilder var64 = new StringBuilder();
                     var63 = var64.append(var2).append("/Tencent/").append(this.b.getPackageName()).toString();
                  }
                  break label488;
               } catch (Throwable var60) {
                  var10000 = var60;
                  var10001 = false;
                  break label487;
               }
            }

            File var67;
            try {
               var67 = new File(at.m);
            } catch (Throwable var58) {
               var10000 = var58;
               var10001 = false;
               break label487;
            }

            File var66 = var67;

            try {
               if (var67.isFile()) {
                  var66 = var67.getParentFile();
               }
            } catch (Throwable var59) {
               var10000 = var59;
               var10001 = false;
               break label487;
            }

            try {
               var63 = var66.getAbsolutePath();
            } catch (Throwable var57) {
               var10000 = var57;
               var10001 = false;
               break label487;
            }
         }

         label458:
         try {
            var65 = new StringBuilder();
            am.a(var65.append(var63).append("/euplog.txt").toString(), var3, at.n);
            return true;
         } catch (Throwable var56) {
            var10000 = var56;
            var10001 = false;
            break label458;
         }
      }

      Throwable var69 = var10000;
      al.d("rqdp{  save error} %s", var69.toString());
      if (!al.a(var69)) {
         var69.printStackTrace();
      }

      return false;
   }

   private static String e(CrashDetailBean var0) {
      Exception var10000;
      label38: {
         Pair var1;
         boolean var10001;
         try {
            var1 = (Pair)h.get(var0.b);
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label38;
         }

         if (var1 == null) {
            try {
               al.e("crash type error! %d", var0.b);
               return "";
            } catch (Exception var2) {
               var10000 = var2;
               var10001 = false;
            }
         } else {
            label39: {
               try {
                  if (var0.j) {
                     return (String)var1.first;
                  }
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break label39;
               }

               try {
                  String var6 = (String)var1.second;
                  return var6;
               } catch (Exception var3) {
                  var10000 = var3;
                  var10001 = false;
               }
            }
         }
      }

      Exception var7 = var10000;
      al.a(var7);
      return "";
   }

   private static void e(List var0) {
      Throwable var10000;
      label400: {
         boolean var10001;
         try {
            if (var0.size() == 0) {
               return;
            }
         } catch (Throwable var44) {
            var10000 = var44;
            var10001 = false;
            break label400;
         }

         Iterator var1;
         StringBuilder var2;
         try {
            var2 = new StringBuilder();
            var1 = var0.iterator();
         } catch (Throwable var42) {
            var10000 = var42;
            var10001 = false;
            break label400;
         }

         while(true) {
            try {
               if (!var1.hasNext()) {
                  break;
               }

               CrashDetailBean var45 = (CrashDetailBean)var1.next();
               var2.append(" or _id = ").append(var45.a);
            } catch (Throwable var43) {
               var10000 = var43;
               var10001 = false;
               break label400;
            }
         }

         String var48;
         try {
            var48 = var2.toString();
         } catch (Throwable var41) {
            var10000 = var41;
            var10001 = false;
            break label400;
         }

         String var46 = var48;

         try {
            if (var48.length() > 0) {
               var46 = var48.substring(4);
            }
         } catch (Throwable var40) {
            var10000 = var40;
            var10001 = false;
            break label400;
         }

         label374:
         try {
            var2.setLength(0);
            al.c("deleted %s data %d", "t_cr", w.a().a("t_cr", var46));
            return;
         } catch (Throwable var39) {
            var10000 = var39;
            var10001 = false;
            break label374;
         }
      }

      Throwable var47 = var10000;
      if (!al.a(var47)) {
         var47.printStackTrace();
      }

   }

   public final void a(CrashDetailBean var1) {
      int var2 = var1.b;
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 == 3 && !at.a().k()) {
               return;
            }
         } else if (!at.a().j()) {
            return;
         }
      } else if (!at.a().j()) {
         return;
      }

      if (this.f != null) {
         al.c("Calling 'onCrashHandleEnd' of RQD crash listener.");
      }

   }

   public final void a(List var1, long var2, boolean var4, boolean var5, boolean var6) {
      if (!aa.a(this.b).f) {
         al.d("warn: not upload process");
      } else {
         ai var9 = this.c;
         if (var9 == null) {
            al.d("warn: upload manager is null");
         } else if (!var6 && !var9.b(at.a)) {
            al.d("warn: not crashHappen or not should upload");
         } else {
            StrategyBean var104 = this.e.c();
            if (!var104.f) {
               al.d("remote report is disable!");
               al.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it");
            } else if (var1 != null && var1.size() != 0) {
               Throwable var10000;
               label844: {
                  String var10;
                  bp var11;
                  boolean var10001;
                  String var105;
                  try {
                     var105 = var104.r;
                     var10 = StrategyBean.b;
                     var11 = a(this.b, var1, aa.b());
                  } catch (Throwable var102) {
                     var10000 = var102;
                     var10001 = false;
                     break label844;
                  }

                  if (var11 == null) {
                     label820:
                     try {
                        al.d("create eupPkg fail!");
                        return;
                     } catch (Throwable var94) {
                        var10000 = var94;
                        var10001 = false;
                        break label820;
                     }
                  } else {
                     label840: {
                        byte[] var106;
                        try {
                           var106 = ae.a((m)var11);
                        } catch (Throwable var101) {
                           var10000 = var101;
                           var10001 = false;
                           break label840;
                        }

                        if (var106 == null) {
                           label822:
                           try {
                              al.d("send encode fail!");
                              return;
                           } catch (Throwable var95) {
                              var10000 = var95;
                              var10001 = false;
                              break label822;
                           }
                        } else {
                           label836: {
                              bq var107;
                              try {
                                 var107 = ae.a(this.b, 830, var106);
                              } catch (Throwable var100) {
                                 var10000 = var100;
                                 var10001 = false;
                                 break label836;
                              }

                              if (var107 == null) {
                                 label824:
                                 try {
                                    al.d("request package is null.");
                                    return;
                                 } catch (Throwable var96) {
                                    var10000 = var96;
                                    var10001 = false;
                                    break label824;
                                 }
                              } else {
                                 label832: {
                                    ah var12;
                                    try {
                                       long var7 = System.currentTimeMillis();
                                       var12 = new ah(this, var7, var1, var4) {
                                          final long a;
                                          final List b;
                                          final boolean c;
                                          final as d;

                                          {
                                             this.d = var1;
                                             this.a = var2;
                                             this.b = var4;
                                             this.c = var5;
                                          }

                                          public final void a(boolean var1, String var2) {
                                             long var5 = System.currentTimeMillis();
                                             long var3 = this.a;
                                             List var8 = this.b;
                                             String var7;
                                             if (this.c) {
                                                var7 = "realtime";
                                             } else {
                                                var7 = "cache";
                                             }

                                             as.a(var8, var1, var5 - var3, var7, var2);
                                             as.a(var1, this.b);
                                          }
                                       };
                                    } catch (Throwable var99) {
                                       var10000 = var99;
                                       var10001 = false;
                                       break label832;
                                    }

                                    if (var4) {
                                       label826:
                                       try {
                                          this.c.a(a, var107, var105, var10, var12, var2, var5);
                                          return;
                                       } catch (Throwable var97) {
                                          var10000 = var97;
                                          var10001 = false;
                                          break label826;
                                       }
                                    } else {
                                       label828:
                                       try {
                                          this.c.a(a, var107, var105, var10, var12, false);
                                          return;
                                       } catch (Throwable var98) {
                                          var10000 = var98;
                                          var10001 = false;
                                          break label828;
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               Throwable var103 = var10000;
               al.e("req cr error %s", var103.toString());
               if (!al.b(var103)) {
                  var103.printStackTrace();
               }

            } else {
               al.d("warn: crashList is null or crashList num is 0");
            }
         }
      }
   }

   public final boolean a(CrashDetailBean var1, boolean var2) {
      if (var1 == null) {
         al.d("CrashBean is null, won't handle.");
         return true;
      } else {
         this.b(var1);
         boolean var3;
         String var6;
         if (var2 && var1 != null && (this.g != null || this.f != null)) {
            Iterator var5 = i.iterator();

            label2779: {
               while(var5.hasNext()) {
                  a var4 = (a)var5.next();
                  if (var4.a == var1.b) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  if (var3) {
                     var2 = var4.a();
                     break label2779;
                  }
               }

               var2 = false;
            }

            if (!var2) {
               al.c("Should not call back.");
            } else {
               label2770: {
                  Throwable var10000;
                  label2796: {
                     boolean var10001;
                     Map var191;
                     try {
                        var191 = j;
                        if (!var191.containsKey(var1.b)) {
                           StringBuilder var198 = new StringBuilder("Cannot get crash type for crashBean type:");
                           al.d(var198.append(var1.b).toString());
                           break label2770;
                        }
                     } catch (Throwable var189) {
                        var10000 = var189;
                        var10001 = false;
                        break label2796;
                     }

                     aw var192;
                     int var190;
                     try {
                        var190 = (Integer)var191.get(var1.b);
                        var192 = this.f;
                     } catch (Throwable var188) {
                        var10000 = var188;
                        var10001 = false;
                        break label2796;
                     }

                     Object var193;
                     label2758: {
                        var5 = null;
                        if (var192 != null) {
                           try {
                              al.c("Calling 'onCrashHandleStart' of RQD crash listener.");
                              al.c("Calling 'getCrashExtraMessage' of RQD crash listener.");
                              var6 = this.f.b();
                           } catch (Throwable var186) {
                              var10000 = var186;
                              var10001 = false;
                              break label2796;
                           }

                           if (var6 != null) {
                              try {
                                 var193 = new HashMap(1);
                                 ((Map)var193).put("userData", var6);
                                 break label2758;
                              } catch (Throwable var185) {
                                 var10000 = var185;
                                 var10001 = false;
                                 break label2796;
                              }
                           }
                        } else {
                           try {
                              if (this.g != null) {
                                 al.c("Calling 'onCrashHandleStart' of Bugly crash listener.");
                                 var193 = this.g.onCrashHandleStart(var190, var1.n, var1.o, var1.q);
                                 break label2758;
                              }
                           } catch (Throwable var187) {
                              var10000 = var187;
                              var10001 = false;
                              break label2796;
                           }
                        }

                        var193 = null;
                     }

                     byte[] var196;
                     label2794: {
                        try {
                           a((CrashDetailBean)var1, (Map)var193);
                           al.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()");
                           if (this.f != null) {
                              al.c("Calling 'getCrashExtraData' of RQD crash listener.");
                              var196 = this.f.a();
                              break label2794;
                           }
                        } catch (Throwable var184) {
                           var10000 = var184;
                           var10001 = false;
                           break label2796;
                        }

                        var196 = (byte[])var5;

                        try {
                           if (this.g != null) {
                              al.c("Calling 'onCrashHandleStart2GetExtraDatas' of Bugly crash listener.");
                              var196 = this.g.onCrashHandleStart2GetExtraDatas(var190, var1.n, var1.o, var1.q);
                           }
                        } catch (Throwable var183) {
                           var10000 = var183;
                           var10001 = false;
                           break label2796;
                        }
                     }

                     if (var196 == null) {
                        try {
                           al.d("extra user byte is null. CrashBean won't have userExtraByteDatas.");
                        } catch (Throwable var181) {
                           var10000 = var181;
                           var10001 = false;
                           break label2796;
                        }
                     } else {
                        label2803: {
                           try {
                              if (var196.length <= 100000) {
                                 var1.Y = var196;
                                 break label2803;
                              }
                           } catch (Throwable var182) {
                              var10000 = var182;
                              var10001 = false;
                              break label2796;
                           }

                           try {
                              al.d("extra bytes size %d is over limit %d will drop over part", var196.length, 100000);
                              var1.Y = Arrays.copyOf(var196, 100000);
                           } catch (Throwable var180) {
                              var10000 = var180;
                              var10001 = false;
                              break label2796;
                           }
                        }

                        try {
                           al.a("add extra bytes %d ", var196.length);
                        } catch (Throwable var179) {
                           var10000 = var179;
                           var10001 = false;
                           break label2796;
                        }
                     }

                     label2721:
                     try {
                        if (this.f != null) {
                           al.c("Calling 'onCrashSaving' of RQD crash listener.");
                           if (!this.f.c()) {
                              al.d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.");
                           }
                        }
                        break label2770;
                     } catch (Throwable var178) {
                        var10000 = var178;
                        var10001 = false;
                        break label2721;
                     }
                  }

                  Throwable var197 = var10000;
                  al.d("crash handle callback something wrong! %s", var197.getClass().getName());
                  if (!al.a(var197)) {
                     var197.printStackTrace();
                  }
               }
            }
         }

         if (!ab.r()) {
            var1.w = ap.a(at.f, at.k);
         }

         label2716: {
            String var199 = var1.q;
            if (at.q != null && !at.q.isEmpty()) {
               al.c("Crash filter for crash stack is: %s", at.q);
               if (var199.contains(at.q)) {
                  al.d("This crash contains the filter string set. It will not be record and upload.");
                  var3 = true;
                  break label2716;
               }
            }

            var3 = false;
         }

         if (var3) {
            return true;
         } else if (a(var1.q)) {
            return true;
         } else {
            if (var1.b != 2) {
               y var200 = new y();
               var200.b = 1;
               var200.c = var1.A;
               var200.d = var1.B;
               var200.e = var1.r;
               w.a().b(1);
               w.a().a(var200);
               al.b("[crash] a crash occur, handling...");
            } else {
               al.b("[crash] a caught exception occur, handling...");
            }

            List var194 = b();
            ArrayList var201 = new ArrayList(10);
            if (var194 != null && var194.size() > 0) {
               var201.addAll(a(var194));
               var194.removeAll(var201);
               if ((long)var194.size() > 20L) {
                  StringBuilder var7 = new StringBuilder();
                  var7.append("_id in (");
                  var7.append("SELECT _id FROM t_cr order by _id limit 5");
                  var7.append(")");
                  var6 = var7.toString();
                  var7.setLength(0);

                  label2700:
                  try {
                     al.c("deleted first record %s data %d", "t_cr", w.a().a("t_cr", var6));
                  } catch (Throwable var177) {
                     if (!al.a(var177)) {
                        var177.printStackTrace();
                     }
                     break label2700;
                  }
               }

               if (this.b((CrashDetailBean)var1, (List)var194, (List)var201)) {
                  return true;
               }
            }

            this.b(var1);
            String var195 = (String)k.get(var1.b);
            if (!TextUtils.isEmpty(var195)) {
               ag.a.a().a(new ag.c(var1.c, var195, var1.r, true, 0L, "realtime", (String)null));
            }

            d((List)var201);
            al.b("[crash] save crash success");
            return false;
         }
      }
   }

   public final void b(CrashDetailBean var1) {
      if (var1 != null) {
         ContentValues var4 = c(var1);
         if (var4 != null) {
            long var2 = w.a().a("t_cr", (ContentValues)var4, (v)null);
            if (var2 >= 0L) {
               al.c("insert %s success!", "t_cr");
               var1.a = var2;
            }
         }

         if (at.l) {
            this.d(var1);
         }

      }
   }

   public final void b(CrashDetailBean var1, boolean var2) {
      boolean var4 = at.o;
      boolean var3 = false;
      if (var4) {
         al.a("try to upload right now");
         ArrayList var5 = new ArrayList();
         var5.add(var1);
         if (var1.b == 7) {
            var3 = true;
         }

         this.a(var5, 3000L, var2, var3, var2);
      } else {
         al.a("do not upload spot crash right now, crash would be uploaded when app next start");
      }
   }

   abstract static class a {
      final int a;

      private a(int var1) {
         this.a = var1;
      }

      // $FF: synthetic method
      a(int var1, byte var2) {
         this(var1);
      }

      abstract boolean a();
   }

   static final class b extends a {
      private b() {
         super(3, (byte)0);
      }

      // $FF: synthetic method
      b(byte var1) {
         this();
      }

      final boolean a() {
         return at.a().k();
      }
   }

   static final class c extends a {
      private c() {
         super(7, (byte)0);
      }

      // $FF: synthetic method
      c(byte var1) {
         this();
      }

      final boolean a() {
         return true;
      }
   }

   static final class d extends a {
      private d() {
         super(2, (byte)0);
      }

      // $FF: synthetic method
      d(byte var1) {
         this();
      }

      final boolean a() {
         return true;
      }
   }

   static final class e extends a {
      private e() {
         super(0, (byte)0);
      }

      // $FF: synthetic method
      e(byte var1) {
         this();
      }

      final boolean a() {
         return at.a().j();
      }
   }

   static final class f extends a {
      private f() {
         super(5, (byte)0);
      }

      // $FF: synthetic method
      f(byte var1) {
         this();
      }

      final boolean a() {
         return (at.a().B & 2) > 0;
      }
   }

   static final class g extends a {
      private g() {
         super(6, (byte)0);
      }

      // $FF: synthetic method
      g(byte var1) {
         this();
      }

      final boolean a() {
         return (at.a().B & 1) > 0;
      }
   }

   static final class h extends a {
      private h() {
         super(1, (byte)0);
      }

      // $FF: synthetic method
      h(byte var1) {
         this();
      }

      final boolean a() {
         return at.a().j();
      }
   }

   static final class i extends a {
      private i() {
         super(4, (byte)0);
      }

      // $FF: synthetic method
      i(byte var1) {
         this();
      }

      final boolean a() {
         return (at.a().B & 4) > 0;
      }
   }
}
