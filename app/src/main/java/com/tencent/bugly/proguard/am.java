package com.tencent.bugly.proguard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class am {
   public static long a(String var0, String var1, String var2) {
      if (var0 == null) {
         al.d("File name is null.");
         return -1L;
      } else {
         try {
            if (var0.startsWith(var1) && var0.endsWith(var2)) {
               long var3 = Long.parseLong(var0.substring(var1.length(), var0.indexOf(var2)));
               return var3;
            }
         } catch (Throwable var6) {
            al.a(var6);
            return -1L;
         }

         return -1L;
      }
   }

   private static List a(File[] var0, String var1, String var2, long var3) {
      ArrayList var10 = new ArrayList();
      int var6 = var0.length;

      for(int var5 = 0; var5 < var6; ++var5) {
         File var9 = var0[var5];
         long var7 = a(var9.getName(), var1, var2);
         if (var7 >= 0L && 0L <= var7 && var7 <= var3) {
            var10.add(var9);
         }
      }

      return var10;
   }

   public static void a(String var0, String var1, String var2, long var3) {
      List var18 = b(var0, var1, var2, var3);

      Throwable var10000;
      label141: {
         boolean var10001;
         Iterator var19;
         try {
            var19 = var18.iterator();
         } catch (Throwable var17) {
            var10000 = var17;
            var10001 = false;
            break label141;
         }

         int var5 = 0;

         while(true) {
            try {
               if (!var19.hasNext()) {
                  break;
               }

               File var21 = (File)var19.next();
               al.c("File %s is to be deleted.", var21.getName());
               if (!var21.delete()) {
                  continue;
               }
            } catch (Throwable var16) {
               var10000 = var16;
               var10001 = false;
               break label141;
            }

            ++var5;
         }

         label124:
         try {
            al.c("Number of overdue trace files that has deleted: ".concat(String.valueOf(var5)));
            return;
         } catch (Throwable var15) {
            var10000 = var15;
            var10001 = false;
            break label124;
         }
      }

      Throwable var20 = var10000;
      al.a(var20);
   }

   public static boolean a(File var0, String var1, long var2, boolean var4) {
      try {
         FileWriter var8 = new FileWriter(var0, var4);
         BufferedWriter var7 = new BufferedWriter(var8);
         long var5 = var0.length();
         var4 = a(var7, var1.toCharArray(), var1.length(), var5, var2);
         var7.close();
         return var4;
      } catch (Throwable var10) {
         al.a(var10);
         return false;
      }
   }

   private static boolean a(Writer var0, char[] var1, int var2, long var3, long var5) {
      if (var3 >= var5) {
         return false;
      } else {
         IOException var10000;
         label29: {
            boolean var10001;
            if ((long)(var2 * 2) + var3 <= var5) {
               try {
                  var0.write(var1, 0, var2);
               } catch (IOException var9) {
                  var10000 = var9;
                  var10001 = false;
                  break label29;
               }
            } else {
               try {
                  var0.write(var1, 0, (int)((var5 - var3) / 2L));
               } catch (IOException var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label29;
               }
            }

            try {
               var0.flush();
               return true;
            } catch (IOException var7) {
               var10000 = var7;
               var10001 = false;
            }
         }

         IOException var10 = var10000;
         al.a(var10);
         return false;
      }
   }

   public static boolean a(String var0, String var1, int var2) {
      boolean var3 = true;
      al.c("rqdp{  sv sd start} %s", var0);
      if (var1 != null && var1.trim().length() > 0) {
         File var26 = new File(var0);

         Throwable var10000;
         label276: {
            boolean var10001;
            label266: {
               try {
                  if (var26.exists()) {
                     break label266;
                  }

                  if (var26.getParentFile() != null) {
                     var26.getParentFile().mkdirs();
                  }
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label276;
               }

               try {
                  var26.createNewFile();
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label276;
               }
            }

            long var4 = (long)var2;

            label256: {
               try {
                  if (var26.length() < var4) {
                     break label256;
                  }
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label276;
               }

               var3 = false;
            }

            label249:
            try {
               var3 = a(var26, var1, var4, var3);
               return var3;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label249;
            }
         }

         Throwable var27 = var10000;
         if (!al.a(var27)) {
            var27.printStackTrace();
         }
      }

      return false;
   }

   private static List b(String var0, String var1, String var2, long var3) {
      ArrayList var7 = new ArrayList();
      if (var1 != null && var2 != null) {
         long var5 = System.currentTimeMillis();
         File var8 = new File(var0);
         Object var21 = var7;
         if (var8.exists()) {
            var21 = var7;
            if (var8.isDirectory()) {
               Throwable var10000;
               label187: {
                  boolean var10001;
                  File[] var23;
                  try {
                     FilenameFilter var22 = new FilenameFilter(var1, var2) {
                        final String a;
                        final String b;

                        {
                           this.a = var1;
                           this.b = var2;
                        }

                        public final boolean accept(File var1, String var2) {
                           boolean var4 = false;
                           if (var2 == null) {
                              return false;
                           } else {
                              boolean var3 = var4;
                              if (var2.startsWith(this.a)) {
                                 var3 = var4;
                                 if (var2.endsWith(this.b)) {
                                    var3 = true;
                                 }
                              }

                              return var3;
                           }
                        }
                     };
                     var23 = var8.listFiles(var22);
                  } catch (Throwable var20) {
                     var10000 = var20;
                     var10001 = false;
                     break label187;
                  }

                  if (var23 == null) {
                     return var7;
                  }

                  try {
                     if (var23.length == 0) {
                        return var7;
                     }
                  } catch (Throwable var19) {
                     var10000 = var19;
                     var10001 = false;
                     break label187;
                  }

                  label171:
                  try {
                     var21 = a(var23, var1, var2, var5 - var3);
                     return (List)var21;
                  } catch (Throwable var18) {
                     var10000 = var18;
                     var10001 = false;
                     break label171;
                  }
               }

               Throwable var24 = var10000;
               al.a(var24);
               var21 = var7;
            }
         }

         return (List)var21;
      } else {
         al.d("prefix %s and/or postfix %s is null.", var1, var2);
         return var7;
      }
   }
}
