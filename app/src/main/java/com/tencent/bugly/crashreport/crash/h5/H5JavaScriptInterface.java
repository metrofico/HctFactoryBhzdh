package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.bb;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

public class H5JavaScriptInterface {
   private static HashSet a = new HashSet();
   private String b = null;
   private Thread c = null;
   private String d = null;
   private Map e = null;

   private H5JavaScriptInterface() {
   }

   private static bb a(String var0) {
      if (var0 != null && var0.length() > 0) {
         Throwable var10000;
         label2036: {
            JSONObject var2;
            boolean var10001;
            bb var244;
            try {
               var2 = new JSONObject(var0);
               var244 = new bb();
               var244.a = var2.getString("projectRoot");
               if (var244.a == null) {
                  return null;
               }
            } catch (Throwable var238) {
               var10000 = var238;
               var10001 = false;
               break label2036;
            }

            try {
               var244.b = var2.getString("context");
               if (var244.b == null) {
                  return null;
               }
            } catch (Throwable var243) {
               var10000 = var243;
               var10001 = false;
               break label2036;
            }

            try {
               var244.c = var2.getString("url");
               if (var244.c == null) {
                  return null;
               }
            } catch (Throwable var237) {
               var10000 = var237;
               var10001 = false;
               break label2036;
            }

            try {
               var244.d = var2.getString("userAgent");
               if (var244.d == null) {
                  return null;
               }
            } catch (Throwable var242) {
               var10000 = var242;
               var10001 = false;
               break label2036;
            }

            try {
               var244.e = var2.getString("language");
               if (var244.e == null) {
                  return null;
               }
            } catch (Throwable var236) {
               var10000 = var236;
               var10001 = false;
               break label2036;
            }

            try {
               var244.f = var2.getString("name");
               if (var244.f == null || var244.f.equals("null")) {
                  return null;
               }
            } catch (Throwable var241) {
               var10000 = var241;
               var10001 = false;
               break label2036;
            }

            String var3;
            try {
               var3 = var2.getString("stacktrace");
            } catch (Throwable var235) {
               var10000 = var235;
               var10001 = false;
               break label2036;
            }

            if (var3 == null) {
               return null;
            }

            int var1;
            try {
               var1 = var3.indexOf("\n");
            } catch (Throwable var234) {
               var10000 = var234;
               var10001 = false;
               break label2036;
            }

            if (var1 < 0) {
               label1971:
               try {
                  al.d("H5 crash stack's format is wrong!");
                  return null;
               } catch (Throwable var230) {
                  var10000 = var230;
                  var10001 = false;
                  break label1971;
               }
            } else {
               label2039: {
                  try {
                     var244.h = var3.substring(var1 + 1);
                     var244.g = var3.substring(0, var1);
                     var1 = var244.g.indexOf(":");
                  } catch (Throwable var233) {
                     var10000 = var233;
                     var10001 = false;
                     break label2039;
                  }

                  if (var1 > 0) {
                     try {
                        var244.g = var244.g.substring(var1 + 1);
                     } catch (Throwable var232) {
                        var10000 = var232;
                        var10001 = false;
                        break label2039;
                     }
                  }

                  try {
                     var244.i = var2.getString("file");
                     if (var244.f == null) {
                        return null;
                     }
                  } catch (Throwable var240) {
                     var10000 = var240;
                     var10001 = false;
                     break label2039;
                  }

                  try {
                     var244.j = var2.getLong("lineNumber");
                     if (var244.j < 0L) {
                        return null;
                     }
                  } catch (Throwable var231) {
                     var10000 = var231;
                     var10001 = false;
                     break label2039;
                  }

                  try {
                     var244.k = var2.getLong("columnNumber");
                     if (var244.k < 0L) {
                        return null;
                     }
                  } catch (Throwable var239) {
                     var10000 = var239;
                     var10001 = false;
                     break label2039;
                  }

                  label1969:
                  try {
                     al.a("H5 crash information is following: ");
                     StringBuilder var246 = new StringBuilder("[projectRoot]: ");
                     al.a(var246.append(var244.a).toString());
                     var246 = new StringBuilder("[context]: ");
                     al.a(var246.append(var244.b).toString());
                     var246 = new StringBuilder("[url]: ");
                     al.a(var246.append(var244.c).toString());
                     var246 = new StringBuilder("[userAgent]: ");
                     al.a(var246.append(var244.d).toString());
                     var246 = new StringBuilder("[language]: ");
                     al.a(var246.append(var244.e).toString());
                     var246 = new StringBuilder("[name]: ");
                     al.a(var246.append(var244.f).toString());
                     var246 = new StringBuilder("[message]: ");
                     al.a(var246.append(var244.g).toString());
                     var246 = new StringBuilder("[stacktrace]: \n");
                     al.a(var246.append(var244.h).toString());
                     var246 = new StringBuilder("[file]: ");
                     al.a(var246.append(var244.i).toString());
                     var246 = new StringBuilder("[lineNumber]: ");
                     al.a(var246.append(var244.j).toString());
                     var246 = new StringBuilder("[columnNumber]: ");
                     al.a(var246.append(var244.k).toString());
                     return var244;
                  } catch (Throwable var229) {
                     var10000 = var229;
                     var10001 = false;
                     break label1969;
                  }
               }
            }
         }

         Throwable var245 = var10000;
         if (!al.a(var245)) {
            var245.printStackTrace();
         }
      }

      return null;
   }

   public static H5JavaScriptInterface getInstance(CrashReport.a var0) {
      String var2 = null;
      if (var0 != null && !a.contains(var0.hashCode())) {
         H5JavaScriptInterface var3 = new H5JavaScriptInterface();
         a.add(var0.hashCode());
         Thread var4 = Thread.currentThread();
         var3.c = var4;
         if (var4 != null) {
            StringBuilder var6 = new StringBuilder();
            var6.append("\n");

            for(int var1 = 2; var1 < var4.getStackTrace().length; ++var1) {
               StackTraceElement var5 = var4.getStackTrace()[var1];
               if (!var5.toString().contains("crashreport")) {
                  var6.append(var5.toString()).append("\n");
               }
            }

            var2 = var6.toString();
         }

         var3.d = var2;
         HashMap var7 = new HashMap();
         var7.put("[WebView] ContentDescription", "" + var0.c());
         var3.e = var7;
         return var3;
      } else {
         return null;
      }
   }

   @JavascriptInterface
   public void printLog(String var1) {
      al.d("Log from js: %s", var1);
   }

   @JavascriptInterface
   public void reportJSException(String var1) {
      if (var1 == null) {
         al.d("Payload from JS is null.");
      } else {
         String var3 = ap.c(var1.getBytes());
         String var2 = this.b;
         if (var2 != null && var2.equals(var3)) {
            al.d("Same payload from js. Please check whether you've injected bugly.js more than one times.");
         } else {
            this.b = var3;
            al.d("Handling JS exception ...");
            bb var5 = a(var1);
            if (var5 == null) {
               al.d("Failed to parse payload.");
            } else {
               LinkedHashMap var4 = new LinkedHashMap();
               LinkedHashMap var6 = new LinkedHashMap();
               if (var5.a != null) {
                  var6.put("[JS] projectRoot", var5.a);
               }

               if (var5.b != null) {
                  var6.put("[JS] context", var5.b);
               }

               if (var5.c != null) {
                  var6.put("[JS] url", var5.c);
               }

               if (var5.d != null) {
                  var6.put("[JS] userAgent", var5.d);
               }

               if (var5.i != null) {
                  var6.put("[JS] file", var5.i);
               }

               if (var5.j != 0L) {
                  var6.put("[JS] lineNumber", Long.toString(var5.j));
               }

               var4.putAll(var6);
               var4.putAll(this.e);
               var4.put("Java Stack", this.d);
               Thread var7 = this.c;
               if (var5 != null) {
                  InnerApi.postH5CrashAsync(var7, var5.f, var5.g, var5.h, var4);
               }

            }
         }
      }
   }
}
