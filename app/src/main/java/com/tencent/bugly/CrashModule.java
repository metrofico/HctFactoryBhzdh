package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.aq;
import com.tencent.bugly.proguard.at;
import com.tencent.bugly.proguard.au;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.z;

public class CrashModule extends o {
   public static final int MODULE_ID = 1004;
   private static int c;
   private static CrashModule e = new CrashModule();
   private long a;
   private BuglyStrategy.a b;
   private boolean d = false;

   private void a(Context var1, BuglyStrategy var2) {
      synchronized(this){}
      if (var2 != null) {
         try {
            String var5 = var2.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty(var5)) {
               aa.a(var1).t = var5;
               al.a("setted libBugly.so file path :%s", var5);
            }

            if (var2.getCrashHandleCallback() != null) {
               this.b = var2.getCrashHandleCallback();
               al.a("setted CrashHanldeCallback");
            }

            if (var2.getAppReportDelay() > 0L) {
               long var3 = var2.getAppReportDelay();
               this.a = var3;
               al.a("setted delay: %d", var3);
            }
         } finally {
            ;
         }

      }
   }

   public static CrashModule getInstance() {
      e.id = 1004;
      return e;
   }

   public String[] getTables() {
      return new String[]{"t_cr"};
   }

   public boolean hasInitialized() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.d;
      } finally {
         ;
      }

      return var1;
   }

   public void init(Context var1, boolean var2, BuglyStrategy var3) {
      synchronized(this){}
      if (var1 != null) {
         Throwable var10000;
         label2029: {
            boolean var10001;
            try {
               if (this.d) {
                  return;
               }
            } catch (Throwable var247) {
               var10000 = var247;
               var10001 = false;
               break label2029;
            }

            int var4;
            at var251;
            try {
               al.a("Initializing crash module.");
               u var7 = u.a();
               var4 = c + 1;
               c = var4;
               var7.a(var4);
               this.d = true;
               CrashReport.setContext(var1);
               this.a(var1, var3);
               var251 = at.a(var1, var2, this.b);
               var251.t.a();
            } catch (Throwable var246) {
               var10000 = var246;
               var10001 = false;
               break label2029;
            }

            if (var3 != null) {
               label2040: {
                  try {
                     var251.B = var3.getCallBackType();
                     var251.C = var3.getCloseErrorCallback();
                     at.o = var3.isUploadSpotCrash();
                     aa.a(var1).S = var3.isEnableRecordAnrMainStack();
                     if (!var3.isEnableCatchAnrTrace()) {
                        var251.u.disableCatchAnrTrace();
                        break label2040;
                     }
                  } catch (Throwable var245) {
                     var10000 = var245;
                     var10001 = false;
                     break label2029;
                  }

                  try {
                     var251.u.enableCatchAnrTrace();
                  } catch (Throwable var244) {
                     var10000 = var244;
                     var10001 = false;
                     break label2029;
                  }
               }
            } else {
               try {
                  var251.u.enableCatchAnrTrace();
               } catch (Throwable var243) {
                  var10000 = var243;
                  var10001 = false;
                  break label2029;
               }
            }

            try {
               if (aa.b().d.equals(z.a(var251.c))) {
                  var251.u.removeEmptyNativeRecordFiles();
               }
            } catch (Throwable var242) {
               var10000 = var242;
               var10001 = false;
               break label2029;
            }

            label2038: {
               if (var3 != null) {
                  label2035: {
                     try {
                        if (var3.isEnableNativeCrashMonitor()) {
                           break label2035;
                        }
                     } catch (Throwable var241) {
                        var10000 = var241;
                        var10001 = false;
                        break label2029;
                     }

                     try {
                        al.a("[crash] Closed native crash monitor!");
                        var251.d();
                        break label2038;
                     } catch (Throwable var240) {
                        var10000 = var240;
                        var10001 = false;
                        break label2029;
                     }
                  }
               }

               try {
                  var251.e();
               } catch (Throwable var239) {
                  var10000 = var239;
                  var10001 = false;
                  break label2029;
               }
            }

            label2039: {
               if (var3 != null) {
                  label2041: {
                     label1979:
                     try {
                        if (!var3.isEnableANRCrashMonitor()) {
                           break label1979;
                        }
                        break label2041;
                     } catch (Throwable var238) {
                        var10000 = var238;
                        var10001 = false;
                        break label2029;
                     }

                     try {
                        al.a("[crash] Closed ANR monitor!");
                        var251.g();
                        break label2039;
                     } catch (Throwable var237) {
                        var10000 = var237;
                        var10001 = false;
                        break label2029;
                     }
                  }
               }

               try {
                  var251.f();
               } catch (Throwable var236) {
                  var10000 = var236;
                  var10001 = false;
                  break label2029;
               }
            }

            if (var3 != null) {
               try {
                  at.e = var3.isMerged();
               } catch (Throwable var235) {
                  var10000 = var235;
                  var10001 = false;
                  break label2029;
               }
            }

            long var5;
            if (var3 != null) {
               try {
                  var5 = var3.getAppReportDelay();
               } catch (Throwable var234) {
                  var10000 = var234;
                  var10001 = false;
                  break label2029;
               }
            } else {
               var5 = 0L;
            }

            try {
               var251.a(var5);
               var251.u.checkUploadRecordCrash();
               au.a(var1);
               aq var250 = aq.a();
               var250.a("android.net.conn.CONNECTIVITY_CHANGE");
               var250.a(var1);
               u var249 = u.a();
               var4 = c - 1;
               c = var4;
               var249.a(var4);
            } catch (Throwable var233) {
               var10000 = var233;
               var10001 = false;
               break label2029;
            }

            return;
         }

         Throwable var248 = var10000;
         throw var248;
      }
   }

   public void onServerStrategyChanged(StrategyBean var1) {
      if (var1 != null) {
         at var2 = at.a();
         if (var2 != null) {
            var2.t.a(var1);
            var2.u.onStrategyChanged(var1);
            var2.x.b();
         }

      }
   }
}
