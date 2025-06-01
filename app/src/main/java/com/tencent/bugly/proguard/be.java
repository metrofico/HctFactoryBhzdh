package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class be {
   private static List a = new ArrayList();

   public static CrashDetailBean a(Context param0, String param1, NativeExceptionHandler param2) {
      // $FF: Couldn't be decompiled
   }

   private static CrashDetailBean a(Context var0, Map var1, NativeExceptionHandler var2) {
      boolean var3;
      String var326;
      label2752: {
         if (aa.a(var0) == null) {
            al.e("abnormal com info not created");
         } else {
            var326 = (String)var1.get("intStateStr");
            if (var326 != null && var326.trim().length() > 0) {
               var3 = true;
               break label2752;
            }

            al.e("no intStateStr");
         }

         var3 = false;
      }

      if (!var3) {
         return null;
      } else {
         Map var17 = d((String)var1.get("intStateStr"));
         if (var17 == null) {
            al.e("parse intSateMap fail", var1.size());
            return null;
         } else {
            Throwable var10000;
            label2757: {
               String var16;
               boolean var10001;
               try {
                  (Integer)var17.get("sino");
                  (Integer)var17.get("sud");
                  var16 = (String)var1.get("soVersion");
                  if (TextUtils.isEmpty(var16)) {
                     al.e("error format at version");
                     return null;
                  }
               } catch (Throwable var325) {
                  var10000 = var325;
                  var10001 = false;
                  break label2757;
               }

               String var9;
               String var10;
               String var11;
               String var12;
               try {
                  var9 = (String)a((Map)var1, (Object)"codeMsg", (Object)"unknown");
                  var10 = (String)a((Map)var1, (Object)"signalName", (Object)"unknown");
                  var1.get("errnoMsg");
                  var11 = (String)a((Map)var1, (Object)"stack", (Object)"unknown");
                  var12 = (String)var1.get("jstack");
               } catch (Throwable var324) {
                  var10000 = var324;
                  var10001 = false;
                  break label2757;
               }

               var326 = var11;
               if (var12 != null) {
                  try {
                     StringBuilder var327 = new StringBuilder();
                     var326 = var327.append(var11).append("java:\n").append(var12).toString();
                  } catch (Throwable var323) {
                     var10000 = var323;
                     var10001 = false;
                     break label2757;
                  }
               }

               Integer var331;
               try {
                  var331 = (Integer)var17.get("sico");
               } catch (Throwable var322) {
                  var10000 = var322;
                  var10001 = false;
                  break label2757;
               }

               if (var331 != null) {
                  label2724: {
                     try {
                        if (var331 <= 0) {
                           break label2724;
                        }

                        StringBuilder var332 = new StringBuilder();
                        var10 = var332.append(var10).append("(").append(var9).append(")").toString();
                     } catch (Throwable var321) {
                        var10000 = var321;
                        var10001 = false;
                        break label2757;
                     }

                     var9 = "KERNEL";
                  }
               }

               try {
                  var11 = (String)var1.get("nativeLog");
               } catch (Throwable var320) {
                  var10000 = var320;
                  var10001 = false;
                  break label2757;
               }

               byte[] var333;
               label2713: {
                  if (var11 != null) {
                     try {
                        if (!var11.isEmpty()) {
                           var333 = ap.a(var11, "BuglyNativeLog.txt");
                           break label2713;
                        }
                     } catch (Throwable var319) {
                        var10000 = var319;
                        var10001 = false;
                        break label2757;
                     }
                  }

                  var333 = null;
               }

               String var13;
               Integer var14;
               try {
                  var13 = (String)a((Map)var1, (Object)"sendingProcess", (Object)"unknown");
                  var14 = (Integer)var17.get("spd");
               } catch (Throwable var318) {
                  var10000 = var318;
                  var10001 = false;
                  break label2757;
               }

               var12 = var13;
               if (var14 != null) {
                  try {
                     StringBuilder var334 = new StringBuilder();
                     var12 = var334.append(var13).append("(").append(var14).append(")").toString();
                  } catch (Throwable var317) {
                     var10000 = var317;
                     var10001 = false;
                     break label2757;
                  }
               }

               Integer var15;
               String var336;
               try {
                  var336 = (String)a((Map)var1, (Object)"threadName", (Object)"unknown");
                  var15 = (Integer)var17.get("et");
               } catch (Throwable var316) {
                  var10000 = var316;
                  var10001 = false;
                  break label2757;
               }

               var13 = var336;
               if (var15 != null) {
                  try {
                     StringBuilder var335 = new StringBuilder();
                     var13 = var335.append(var336).append("(").append(var15).append(")").toString();
                  } catch (Throwable var315) {
                     var10000 = var315;
                     var10001 = false;
                     break label2757;
                  }
               }

               Integer var18;
               String var338;
               try {
                  var338 = (String)a((Map)var1, (Object)"processName", (Object)"unknown");
                  var18 = (Integer)var17.get("ep");
               } catch (Throwable var314) {
                  var10000 = var314;
                  var10001 = false;
                  break label2757;
               }

               var336 = var338;
               if (var18 != null) {
                  try {
                     StringBuilder var337 = new StringBuilder();
                     var336 = var337.append(var338).append("(").append(var18).append(")").toString();
                  } catch (Throwable var313) {
                     var10000 = var313;
                     var10001 = false;
                     break label2757;
                  }
               }

               CrashDetailBean var328;
               try {
                  Map var339 = a(var1);
                  long var4 = (long)(Integer)var17.get("ets");
                  long var6 = (long)(Integer)var17.get("etms") / 1000L;
                  String var340 = (String)a((Map)var1, (Object)"errorAddr", (Object)"unknown");
                  String var341 = a(var326);
                  var326 = (String)var1.get("sysLogPath");
                  String var19 = (String)var1.get("jniLogPath");
                  var328 = var2.packageCrashDatas(var336, var13, var4 * 1000L + var6, var10, var340, var341, var9, var12, (String)a((Map)var1, (Object)"tombPath", (Object)"unknown"), var326, var19, var16, var333, var339, false, false);
               } catch (Throwable var312) {
                  var10000 = var312;
                  var10001 = false;
                  break label2757;
               }

               if (var328 != null) {
                  String var329;
                  try {
                     var328.m = (String)a((Map)var1, (Object)"userId", (Object)var328.m);
                     al.c("[Native record info] userId: %s", var328.m);
                     var328.w = (String)a((Map)var1, (Object)"sysLog", (Object)var328.w);
                     var328.f = (String)a((Map)var1, (Object)"appVersion", (Object)var328.w);
                     al.c("[Native record info] appVersion: %s", var328.f);
                     var329 = (String)var1.get("isAppForeground");
                  } catch (Throwable var311) {
                     var10000 = var311;
                     var10001 = false;
                     break label2757;
                  }

                  boolean var8;
                  if (var329 != null) {
                     try {
                        al.c("[Native record info] isAppForeground: %s", var329);
                        var8 = var329.equalsIgnoreCase("true");
                     } catch (Throwable var310) {
                        var10000 = var310;
                        var10001 = false;
                        break label2757;
                     }
                  } else {
                     var8 = false;
                  }

                  try {
                     var328.R = var8;
                     var328.Q = b(var1);
                     var328.z = null;
                     var328.k = true;
                  } catch (Throwable var309) {
                     var10000 = var309;
                     var10001 = false;
                     break label2757;
                  }
               }

               return var328;
            }

            Throwable var330 = var10000;
            al.e("error format");
            var330.printStackTrace();
            return null;
         }
      }
   }

   private static Object a(Map var0, Object var1, Object var2) {
      Object var4;
      try {
         var4 = var0.get(var1);
      } catch (Exception var3) {
         al.a(var3);
         return var2;
      }

      if (var4 != null) {
         return var4;
      } else {
         return var2;
      }
   }

   private static String a(BufferedInputStream var0) throws IOException {
      ByteArrayOutputStream var2;
      try {
         var2 = new ByteArrayOutputStream(1024);
      } finally {
         ;
      }

      ByteArrayOutputStream var3;
      label352: {
         Throwable var10000;
         while(true) {
            int var1;
            boolean var10001;
            try {
               var1 = var0.read();
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               break;
            }

            var3 = var2;
            if (var1 == -1) {
               break label352;
            }

            if (var1 == 0) {
               String var35;
               try {
                  var35 = new String(var2.toByteArray(), "UTf-8");
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break;
               }

               var2.close();
               return var35;
            } else {
               try {
                  var2.write(var1);
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break;
               }
            }
         }

         Throwable var36 = var10000;
         boolean var9 = false;

         try {
            var9 = true;
            al.a(var36);
            var9 = false;
         } finally {
            if (var9) {
               if (var2 != null) {
                  var2.close();
               }

            }
         }

         if (var2 == null) {
            return null;
         }

         var3 = var2;
      }

      var3.close();
      return null;
   }

   protected static String a(String var0) {
      if (var0 == null) {
         return "";
      } else {
         String[] var4 = var0.split("\n");
         String var3 = var0;
         if (var4 != null) {
            if (var4.length == 0) {
               var3 = var0;
            } else {
               StringBuilder var5 = new StringBuilder();
               int var2 = var4.length;

               for(int var1 = 0; var1 < var2; ++var1) {
                  var3 = var4[var1];
                  if (!var3.contains("java.lang.Thread.getStackTrace(")) {
                     var5.append(var3).append("\n");
                  }
               }

               var3 = var5.toString();
            }
         }

         return var3;
      }
   }

   public static String a(String var0, int var1, String var2, boolean var3) {
      String var5 = null;
      if (var0 != null && var1 > 0) {
         File var7 = new File(var0);
         if (var7.exists() && var7.canRead()) {
            al.a("Read system log from native record file(length: %s bytes): %s", var7.length(), var7.getAbsolutePath());
            a.add(var7);
            al.c("Add this record file to list for cleaning lastly.");
            Object var109;
            if (var2 == null) {
               var109 = ap.a(new File(var0), var1, var3);
            } else {
               StringBuilder var6 = new StringBuilder();

               Object var4;
               try {
                  FileInputStream var8 = new FileInputStream(var7);
                  InputStreamReader var110 = new InputStreamReader(var8, "utf-8");
                  var4 = new BufferedReader(var110);
               } finally {
                  ;
               }

               label1039: {
                  Exception var116;
                  label1040: {
                     Throwable var10000;
                     boolean var10001;
                     while(true) {
                        try {
                           var5 = ((BufferedReader)var4).readLine();
                        } catch (Throwable var108) {
                           var10000 = var108;
                           var10001 = false;
                           break;
                        }

                        if (var5 != null) {
                           try {
                              StringBuilder var111 = new StringBuilder();
                              if (Pattern.compile(var111.append(var2).append("[ ]*:").toString()).matcher(var5).find()) {
                                 var6.append(var5).append("\n");
                              }
                           } catch (Throwable var107) {
                              var10000 = var107;
                              var10001 = false;
                              break;
                           }

                           if (var1 <= 0) {
                              continue;
                           }

                           try {
                              if (var6.length() <= var1) {
                                 continue;
                              }
                           } catch (Throwable var106) {
                              var10000 = var106;
                              var10001 = false;
                              break;
                           }

                           if (!var3) {
                              try {
                                 var6.delete(0, var6.length() - var1);
                                 continue;
                              } catch (Throwable var102) {
                                 var10000 = var102;
                                 var10001 = false;
                                 break;
                              }
                           }

                           try {
                              var6.delete(var1, var6.length());
                           } catch (Throwable var105) {
                              var10000 = var105;
                              var10001 = false;
                              break;
                           }
                        }

                        try {
                           var2 = var6.toString();
                        } catch (Throwable var104) {
                           var10000 = var104;
                           var10001 = false;
                           break;
                        }

                        var109 = var2;

                        try {
                           ((BufferedReader)var4).close();
                           break label1039;
                        } catch (Exception var103) {
                           var116 = var103;
                           var10001 = false;
                           break label1040;
                        }
                     }

                     Throwable var112 = var10000;
                     Object var113 = var4;
                     boolean var20 = false;

                     try {
                        var20 = true;
                        al.a(var112);
                        StringBuilder var115 = new StringBuilder("\n[error:");
                        var4 = var6.append(var115.append(var112.toString()).append("]").toString()).toString();
                        var20 = false;
                     } finally {
                        if (var20) {
                           if (var4 != null) {
                              try {
                                 ((BufferedReader)var113).close();
                              } catch (Exception var98) {
                                 al.a(var98);
                              }
                           }

                        }
                     }

                     var109 = var4;
                     if (var113 == null) {
                        return (String)var109;
                     }

                     var109 = var4;

                     try {
                        ((BufferedReader)var113).close();
                     } catch (Exception var101) {
                        var116 = var101;
                        var10001 = false;
                        break label1040;
                     }

                     var109 = var4;
                     return (String)var109;
                  }

                  Exception var114 = var116;
                  al.a(var114);
                  return (String)var109;
               }

               var109 = var2;
            }

            return (String)var109;
         }
      }

      return null;
   }

   public static String a(String var0, String var1) {
      if (var0 != null && var1 != null) {
         StringBuilder var2 = new StringBuilder();
         String var3 = b(var0, var1);
         if (var3 != null && !var3.isEmpty()) {
            var2.append("Register infos:\n");
            var2.append(var3);
         }

         var0 = c(var0, var1);
         if (var0 != null && !var0.isEmpty()) {
            if (var2.length() > 0) {
               var2.append("\n");
            }

            var2.append("System SO infos:\n");
            var2.append(var0);
         }

         return var2.toString();
      } else {
         return null;
      }
   }

   private static Map a(Map var0) {
      String var3 = (String)var0.get("key-value");
      if (var3 != null) {
         HashMap var5 = new HashMap();
         String[] var6 = var3.split("\n");
         int var2 = var6.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            String[] var4 = var6[var1].split("=");
            if (var4.length == 2) {
               var5.put(var4[0], var4[1]);
            }
         }

         return var5;
      } else {
         return null;
      }
   }

   public static void a(boolean var0, String var1) {
      if (var1 != null) {
         a.add(new File(var1, "rqd_record.eup"));
         a.add(new File(var1, "reg_record.txt"));
         a.add(new File(var1, "map_record.txt"));
         a.add(new File(var1, "backup_record.txt"));
         if (var0) {
            c(var1);
         }
      }

      List var3 = a;
      if (var3 != null && var3.size() > 0) {
         Iterator var2 = a.iterator();

         while(var2.hasNext()) {
            File var4 = (File)var2.next();
            if (var4.exists() && var4.canWrite()) {
               var4.delete();
               al.c("Delete record file %s", var4.getAbsoluteFile());
            }
         }
      }

   }

   private static long b(Map var0) {
      String var4 = (String)var0.get("launchTime");
      if (var4 != null) {
         al.c("[Native record info] launchTime: %s", var4);

         try {
            long var1 = Long.parseLong(var4);
            return var1;
         } catch (NumberFormatException var3) {
            if (!al.a(var3)) {
               var3.printStackTrace();
            }
         }
      }

      return -1L;
   }

   public static String b(String var0) {
      if (var0 == null) {
         return null;
      } else {
         File var1 = new File(var0, "backup_record.txt");
         return var1.exists() ? var1.getAbsolutePath() : null;
      }
   }

   private static String b(String var0, String var1) {
      BufferedReader var151 = ap.b(var0, "reg_record.txt");
      if (var151 == null) {
         return null;
      } else {
         label1188: {
            Throwable var10000;
            label1189: {
               StringBuilder var5;
               String var6;
               boolean var10001;
               try {
                  var5 = new StringBuilder();
                  var6 = var151.readLine();
               } catch (Throwable var150) {
                  var10000 = var150;
                  var10001 = false;
                  break label1189;
               }

               if (var6 == null) {
                  break label1188;
               }

               try {
                  if (!var6.startsWith(var1)) {
                     break label1188;
                  }
               } catch (Throwable var149) {
                  var10000 = var149;
                  var10001 = false;
                  break label1189;
               }

               byte var2 = 18;
               int var3 = 0;
               int var4 = 0;

               while(true) {
                  try {
                     var1 = var151.readLine();
                  } catch (Throwable var144) {
                     var10000 = var144;
                     var10001 = false;
                     break;
                  }

                  if (var1 == null) {
                     try {
                        var5.append("\n");
                        var1 = var5.toString();
                     } catch (Throwable var142) {
                        var10000 = var142;
                        var10001 = false;
                        break;
                     }

                     if (var151 != null) {
                        try {
                           var151.close();
                        } catch (Exception var139) {
                           al.a(var139);
                        }
                     }

                     return var1;
                  }

                  if (var3 % 4 == 0) {
                     if (var3 > 0) {
                        try {
                           var5.append("\n");
                        } catch (Throwable var143) {
                           var10000 = var143;
                           var10001 = false;
                           break;
                        }
                     }

                     try {
                        var5.append("  ");
                     } catch (Throwable var147) {
                        var10000 = var147;
                        var10001 = false;
                        break;
                     }
                  } else {
                     label1167: {
                        try {
                           if (var1.length() <= 16) {
                              break label1167;
                           }
                        } catch (Throwable var148) {
                           var10000 = var148;
                           var10001 = false;
                           break;
                        }

                        var2 = 28;
                     }

                     try {
                        var5.append("                ".substring(0, var2 - var4));
                     } catch (Throwable var146) {
                        var10000 = var146;
                        var10001 = false;
                        break;
                     }
                  }

                  try {
                     var4 = var1.length();
                     var5.append(var1);
                  } catch (Throwable var145) {
                     var10000 = var145;
                     var10001 = false;
                     break;
                  }

                  ++var3;
               }
            }

            Throwable var152 = var10000;

            try {
               al.a(var152);
            } finally {
               if (var151 != null) {
                  try {
                     var151.close();
                  } catch (Exception var138) {
                     al.a(var138);
                  }
               }

            }

            return null;
         }

         if (var151 != null) {
            try {
               var151.close();
            } catch (Exception var140) {
               al.a(var140);
            }
         }

         return null;
      }
   }

   private static String c(String var0, String var1) {
      BufferedReader var68 = ap.b(var0, "map_record.txt");
      if (var68 == null) {
         return null;
      } else {
         Throwable var10000;
         label543: {
            StringBuilder var2;
            String var3;
            boolean var10001;
            try {
               var2 = new StringBuilder();
               var3 = var68.readLine();
            } catch (Throwable var66) {
               var10000 = var66;
               var10001 = false;
               break label543;
            }

            label544: {
               if (var3 != null) {
                  try {
                     if (var3.startsWith(var1)) {
                        break label544;
                     }
                  } catch (Throwable var67) {
                     var10000 = var67;
                     var10001 = false;
                     break label543;
                  }
               }

               if (var68 != null) {
                  try {
                     var68.close();
                  } catch (Exception var61) {
                     al.a(var61);
                  }
               }

               return null;
            }

            while(true) {
               try {
                  var1 = var68.readLine();
               } catch (Throwable var64) {
                  var10000 = var64;
                  var10001 = false;
                  break;
               }

               if (var1 == null) {
                  try {
                     var1 = var2.toString();
                  } catch (Throwable var63) {
                     var10000 = var63;
                     var10001 = false;
                     break;
                  }

                  if (var68 != null) {
                     try {
                        var68.close();
                     } catch (Exception var60) {
                        al.a(var60);
                     }
                  }

                  return var1;
               }

               try {
                  var2.append("  ");
                  var2.append(var1);
                  var2.append("\n");
               } catch (Throwable var65) {
                  var10000 = var65;
                  var10001 = false;
                  break;
               }
            }
         }

         Throwable var69 = var10000;

         try {
            al.a(var69);
         } finally {
            if (var68 != null) {
               try {
                  var68.close();
               } catch (Exception var59) {
                  al.a(var59);
               }
            }

         }

         return null;
      }
   }

   public static void c(String var0) {
      if (var0 != null) {
         Throwable var10000;
         label230: {
            File[] var16;
            boolean var10001;
            File var3;
            try {
               var3 = new File(var0);
               if (!var3.canRead() || !var3.isDirectory()) {
                  return;
               }

               var16 = var3.listFiles();
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label230;
            }

            if (var16 == null) {
               return;
            }

            int var2;
            try {
               var2 = var16.length;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label230;
            }

            int var1 = 0;

            while(true) {
               if (var1 >= var2) {
                  return;
               }

               var3 = var16[var1];

               try {
                  if (var3.canRead() && var3.canWrite() && var3.length() == 0L) {
                     var3.delete();
                     al.c("Delete empty record file %s", var3.getAbsoluteFile());
                  }
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break;
               }

               ++var1;
            }
         }

         Throwable var17 = var10000;
         al.a(var17);
      }
   }

   private static Map d(String var0) {
      if (var0 == null) {
         return null;
      } else {
         Exception var10000;
         label46: {
            int var2;
            String[] var6;
            HashMap var7;
            boolean var10001;
            try {
               var7 = new HashMap();
               var6 = var0.split(",");
               var2 = var6.length;
            } catch (Exception var10) {
               var10000 = var10;
               var10001 = false;
               break label46;
            }

            int var1 = 0;

            while(true) {
               if (var1 >= var2) {
                  return var7;
               }

               String var5 = var6[var1];

               String[] var4;
               try {
                  var4 = var5.split(":");
                  if (var4.length != 2) {
                     al.e("error format at %s", var5);
                     return null;
                  }
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break;
               }

               try {
                  int var3 = Integer.parseInt(var4[1]);
                  var7.put(var4[0], var3);
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break;
               }

               ++var1;
            }
         }

         Exception var11 = var10000;
         al.e("error format intStateStr %s", var0);
         var11.printStackTrace();
         return null;
      }
   }
}
