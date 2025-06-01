package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class r {
   private static boolean e;
   private Context a;
   private long b;
   private int c;
   private boolean d;

   public r(Context var1, boolean var2) {
      this.a = var1;
      this.d = var2;
   }

   private static int a(List var0) {
      long var2 = System.currentTimeMillis();
      Iterator var4 = var0.iterator();
      int var1 = 0;

      while(true) {
         UserInfoBean var5;
         do {
            do {
               if (!var4.hasNext()) {
                  return var1;
               }

               var5 = (UserInfoBean)var4.next();
            } while(var5.e <= var2 - 600000L);
         } while(var5.b != 1 && var5.b != 4 && var5.b != 3);

         ++var1;
      }
   }

   private static UserInfoBean a(Cursor var0) {
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

            UserInfoBean var16;
            long var1;
            try {
               var1 = var0.getLong(var0.getColumnIndex("_id"));
               var16 = (UserInfoBean)ap.a(var3, UserInfoBean.CREATOR);
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

   public static List a(String var0) {
      Cursor var117;
      Throwable var118;
      label1137: {
         Throwable var10000;
         boolean var10001;
         label1133: {
            label1132: {
               label1131: {
                  label1130: {
                     try {
                        if (ap.b(var0)) {
                           break label1130;
                        }
                     } catch (Throwable var116) {
                        var10000 = var116;
                        var10001 = false;
                        break label1132;
                     }

                     try {
                        StringBuilder var3 = new StringBuilder("_pc = '");
                        var0 = var3.append(var0).append("'").toString();
                        break label1131;
                     } catch (Throwable var115) {
                        var10000 = var115;
                        var10001 = false;
                        break label1132;
                     }
                  }

                  var0 = null;
               }

               label1123:
               try {
                  var117 = w.a().a("t_ui", (String[])null, (String)var0);
                  break label1133;
               } catch (Throwable var114) {
                  var10000 = var114;
                  var10001 = false;
                  break label1123;
               }
            }

            var118 = var10000;
            var117 = null;
            break label1137;
         }

         if (var117 == null) {
            if (var117 != null) {
               var117.close();
            }

            return null;
         }

         label1116: {
            StringBuilder var4;
            ArrayList var119;
            try {
               var4 = new StringBuilder();
               var119 = new ArrayList();
            } catch (Throwable var111) {
               var10000 = var111;
               var10001 = false;
               break label1116;
            }

            while(true) {
               UserInfoBean var5;
               label1139: {
                  try {
                     if (var117.moveToNext()) {
                        var5 = a(var117);
                        break label1139;
                     }
                  } catch (Throwable var113) {
                     var10000 = var113;
                     var10001 = false;
                     break;
                  }

                  try {
                     String var120 = var4.toString();
                     if (var120.length() > 0) {
                        var120 = var120.substring(4);
                        al.d("[Database] deleted %s error data %d", "t_ui", w.a().a("t_ui", var120));
                     }
                  } catch (Throwable var108) {
                     var10000 = var108;
                     var10001 = false;
                     break;
                  }

                  if (var117 != null) {
                     var117.close();
                  }

                  return var119;
               }

               if (var5 != null) {
                  try {
                     var119.add(var5);
                  } catch (Throwable var110) {
                     var10000 = var110;
                     var10001 = false;
                     break;
                  }
               } else {
                  boolean var46 = false;

                  try {
                     var46 = true;
                     long var1 = var117.getLong(var117.getColumnIndex("_id"));
                     var4.append(" or _id = ").append(var1);
                     var46 = false;
                  } finally {
                     if (var46) {
                        try {
                           al.d("[Database] unknown id.");
                           continue;
                        } catch (Throwable var109) {
                           var10000 = var109;
                           var10001 = false;
                           break;
                        }
                     }
                  }
               }
            }
         }

         var118 = var10000;
      }

      try {
         if (!al.a(var118)) {
            var118.printStackTrace();
         }
      } finally {
         if (var117 != null) {
            var117.close();
         }

      }

      return null;
   }

   // $FF: synthetic method
   static void a(UserInfoBean var0) {
      if (var0 != null) {
         aa var1 = aa.b();
         if (var1 != null) {
            var0.j = var1.d();
         }
      }

   }

   private void a(UserInfoBean var1, boolean var2) {
      if (var1 != null) {
         if (!var2 && var1.b != 1) {
            List var5 = a(aa.a(this.a).d);
            if (var5 != null && var5.size() >= 20) {
               al.a("[UserInfo] There are too many user info in local: %d", var5.size());
               return;
            }
         }

         ContentValues var6 = b(var1);
         long var3 = w.a().a("t_ui", (ContentValues)var6, (v)null);
         if (var3 >= 0L) {
            al.c("[Database] insert %s success with ID: %d", "t_ui", var3);
            var1.a = var3;
         }

      }
   }

   private static void a(List var0, List var1) {
      int var6 = var0.size() - 20;
      if (var6 > 0) {
         byte var5 = 0;
         int var2 = 0;

         while(true) {
            int var3 = var5;
            if (var2 >= var0.size() - 1) {
               while(var3 < var6) {
                  var1.add(var0.get(var3));
                  ++var3;
               }
               break;
            }

            var3 = var2 + 1;

            for(int var4 = var3; var4 < var0.size(); ++var4) {
               if (((UserInfoBean)var0.get(var2)).e > ((UserInfoBean)var0.get(var4)).e) {
                  UserInfoBean var7 = (UserInfoBean)var0.get(var2);
                  var0.set(var2, var0.get(var4));
                  var0.set(var4, var7);
               }
            }

            var2 = var3;
         }
      }

   }

   private void a(List var1, boolean var2) {
      if (!this.b(var2)) {
         long var4 = System.currentTimeMillis();
         Iterator var13 = var1.iterator();

         while(var13.hasNext()) {
            UserInfoBean var17 = (UserInfoBean)var13.next();
            var17.f = var4;
            this.a(var17, true);
         }

         al.d("uploadCheck failed");
      } else {
         byte var3;
         if (this.c == 1) {
            var3 = 1;
         } else {
            var3 = 2;
         }

         String var7 = null;
         bv var6 = var7;
         if (var1 != null) {
            if (var1.size() == 0) {
               var6 = var7;
            } else {
               aa var8 = aa.b();
               if (var8 == null) {
                  var6 = var7;
               } else {
                  var8.o();
                  var6 = new bv();
                  var6.b = var8.d;
                  var6.c = var8.g();
                  ArrayList var9 = new ArrayList();
                  Iterator var11 = var1.iterator();

                  while(var11.hasNext()) {
                     bu var10 = ae.a((UserInfoBean)var11.next());
                     if (var10 != null) {
                        var9.add(var10);
                     }
                  }

                  var6.d = var9;
                  var6.e = new HashMap();
                  Map var23 = var6.e;
                  StringBuilder var20 = new StringBuilder();
                  var8.getClass();
                  var23.put("A7", var20.toString());
                  var6.e.put("A6", aa.n());
                  var6.e.put("A5", var8.m());
                  var6.e.put("A2", "" + var8.k());
                  var6.e.put("A1", "" + var8.k());
                  var6.e.put("A24", var8.k);
                  var6.e.put("A17", "" + var8.l());
                  var6.e.put("A15", var8.q());
                  var6.e.put("A13", "" + var8.r());
                  var6.e.put("F08", var8.E);
                  var6.e.put("F09", var8.F);
                  Map var16 = var8.y();
                  if (var16 != null && var16.size() > 0) {
                     Iterator var18 = var16.entrySet().iterator();

                     while(var18.hasNext()) {
                        Map.Entry var21 = (Map.Entry)var18.next();
                        var6.e.put("C04_" + (String)var21.getKey(), var21.getValue());
                     }
                  }

                  if (var3 != 1) {
                     if (var3 != 2) {
                        al.e("unknown up type %d ", Integer.valueOf(var3));
                        var6 = var7;
                     } else {
                        var6.a = 2;
                     }
                  } else {
                     var6.a = 1;
                  }
               }
            }
         }

         if (var6 == null) {
            al.d("[UserInfo] Failed to create UserInfoPackage.");
         } else {
            byte[] var14 = ae.a((m)var6);
            if (var14 == null) {
               al.d("[UserInfo] Failed to encode data.");
            } else {
               bq var15 = ae.a(this.a, 840, var14);
               if (var15 == null) {
                  al.d("[UserInfo] Request package is null.");
               } else {
                  ah var19 = new ah(this, var1) {
                     final List a;
                     final r b;

                     {
                        this.b = var1;
                        this.a = var2;
                     }

                     public final void a(boolean var1, String var2) {
                        if (var1) {
                           al.c("[UserInfo] Successfully uploaded user info.");
                           long var3 = System.currentTimeMillis();
                           Iterator var5 = this.a.iterator();

                           while(var5.hasNext()) {
                              UserInfoBean var6 = (UserInfoBean)var5.next();
                              var6.f = var3;
                              this.b.a(var6, true);
                           }
                        }

                     }
                  };
                  var7 = ac.a().c().q;
                  String var12 = StrategyBean.a;
                  ai var22 = ai.a();
                  if (this.c == 1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  var22.a(1001, var15, var7, var12, var19, var2);
               }
            }
         }
      }
   }

   private void a(boolean var1) {
      synchronized(this){}

      Throwable var10000;
      label1475: {
         boolean var2;
         boolean var10001;
         label1462: {
            label1468: {
               try {
                  if (!this.d) {
                     break label1468;
                  }
               } catch (Throwable var161) {
                  var10000 = var161;
                  var10001 = false;
                  break label1475;
               }

               ai var4;
               try {
                  var4 = ai.a();
               } catch (Throwable var160) {
                  var10000 = var160;
                  var10001 = false;
                  break label1475;
               }

               if (var4 != null) {
                  ac var5;
                  try {
                     var5 = ac.a();
                  } catch (Throwable var159) {
                     var10000 = var159;
                     var10001 = false;
                     break label1475;
                  }

                  if (var5 != null) {
                     label1473: {
                        label1459: {
                           boolean var3;
                           try {
                              if (!var5.b()) {
                                 break label1459;
                              }

                              var3 = var4.b(1001);
                           } catch (Throwable var162) {
                              var10000 = var162;
                              var10001 = false;
                              break label1475;
                           }

                           if (!var3) {
                              break label1473;
                           }
                        }

                        var2 = true;
                        break label1462;
                     }
                  }
               }
            }

            var2 = false;
         }

         if (!var2) {
            return;
         }

         ArrayList var6;
         List var166;
         try {
            String var164 = aa.a(this.a).d;
            var6 = new ArrayList();
            var166 = a(var164);
         } catch (Throwable var158) {
            var10000 = var158;
            var10001 = false;
            break label1475;
         }

         Object var165;
         label1434: {
            if (var166 != null) {
               int var163;
               try {
                  a(var166, var6);
                  b(var166, var6);
                  var163 = a(var166);
               } catch (Throwable var157) {
                  var10000 = var157;
                  var10001 = false;
                  break label1475;
               }

               var165 = var166;
               if (var163 > 15) {
                  try {
                     al.d("[UserInfo] Upload user info too many times in 10 min: %d", var163);
                  } catch (Throwable var156) {
                     var10000 = var156;
                     var10001 = false;
                     break label1475;
                  }

                  var2 = false;
                  var165 = var166;
                  break label1434;
               }
            } else {
               try {
                  var165 = new ArrayList();
               } catch (Throwable var155) {
                  var10000 = var155;
                  var10001 = false;
                  break label1475;
               }
            }

            var2 = true;
         }

         try {
            if (var6.size() > 0) {
               b((List)var6);
            }
         } catch (Throwable var154) {
            var10000 = var154;
            var10001 = false;
            break label1475;
         }

         if (var2) {
            label1474: {
               try {
                  if (((List)var165).size() == 0) {
                     break label1474;
                  }
               } catch (Throwable var153) {
                  var10000 = var153;
                  var10001 = false;
                  break label1475;
               }

               try {
                  al.c("[UserInfo] Upload user info(size: %d)", ((List)var165).size());
                  this.a((List)var165, var1);
               } catch (Throwable var151) {
                  var10000 = var151;
                  var10001 = false;
                  break label1475;
               }

               return;
            }
         }

         try {
            al.c("[UserInfo] There is no user info in local database.");
         } catch (Throwable var152) {
            var10000 = var152;
            var10001 = false;
            break label1475;
         }

         return;
      }

      Throwable var167 = var10000;
      throw var167;
   }

   private static ContentValues b(UserInfoBean var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            ContentValues var1 = new ContentValues();
            if (var0.a > 0L) {
               var1.put("_id", var0.a);
            }

            var1.put("_tm", var0.e);
            var1.put("_ut", var0.f);
            var1.put("_tp", var0.b);
            var1.put("_pc", var0.c);
            var1.put("_dt", ap.a((Parcelable)var0));
            return var1;
         } catch (Throwable var3) {
            if (!al.a(var3)) {
               var3.printStackTrace();
            }

            return null;
         }
      }
   }

   private static void b(List var0) {
      if (var0.size() != 0) {
         StringBuilder var3 = new StringBuilder();

         for(int var1 = 0; var1 < var0.size() && var1 < 50; ++var1) {
            UserInfoBean var2 = (UserInfoBean)var0.get(var1);
            var3.append(" or _id = ").append(var2.a);
         }

         String var7 = var3.toString();
         String var6 = var7;
         if (var7.length() > 0) {
            var6 = var7.substring(4);
         }

         var3.setLength(0);

         try {
            al.c("[Database] deleted %s data %d", "t_ui", w.a().a("t_ui", var6));
         } catch (Throwable var5) {
            if (!al.a(var5)) {
               var5.printStackTrace();
            }

            return;
         }
      }
   }

   private static void b(List var0, List var1) {
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         UserInfoBean var3 = (UserInfoBean)var2.next();
         if (var3.f != -1L) {
            var2.remove();
            if (var3.e < ap.b()) {
               var1.add(var3);
            }
         }
      }

   }

   private boolean b(boolean var1) {
      boolean var6 = e;
      boolean var3 = true;
      boolean var4 = true;
      boolean var5 = true;
      if (!var6) {
         return true;
      } else {
         File var12 = new File(this.a.getFilesDir(), "bugly_last_us_up_tm");
         long var7 = System.currentTimeMillis();
         if (var1) {
            am.a(var12, String.valueOf(var7), 1024L, false);
            return true;
         } else {
            if (!var12.exists()) {
               am.a(var12, String.valueOf(var7), 1024L, false);
               var3 = var4;
            } else {
               label419: {
                  Exception var40;
                  label405: {
                     BufferedReader var11 = ap.a(var12);
                     var1 = var3;
                     boolean var10001;
                     if (var11 != null) {
                        label404: {
                           label420: {
                              Throwable var10000;
                              label408: {
                                 long var9;
                                 try {
                                    var9 = Long.valueOf(var11.readLine().trim());
                                 } catch (Throwable var38) {
                                    var10000 = var38;
                                    var10001 = false;
                                    break label408;
                                 }

                                 boolean var2;
                                 if (var7 >= var9 && var7 - var9 <= 86400000L) {
                                    var2 = true;
                                 } else {
                                    var2 = false;
                                 }

                                 if (var2 && var7 - var9 < 300000L) {
                                    var1 = false;
                                    break label404;
                                 }

                                 label381:
                                 try {
                                    am.a(var12, String.valueOf(var7), 1024L, false);
                                    break label420;
                                 } catch (Throwable var37) {
                                    var10000 = var37;
                                    var10001 = false;
                                    break label381;
                                 }
                              }

                              Throwable var13 = var10000;
                              boolean var20 = false;

                              try {
                                 var20 = true;
                                 al.b(var13);
                                 am.a(var12, String.valueOf(var7), 1024L, false);
                                 var20 = false;
                              } finally {
                                 if (var20) {
                                    if (var11 != null) {
                                       try {
                                          var11.close();
                                       } catch (Exception var33) {
                                          al.a(var33);
                                       }
                                    }

                                 }
                              }

                              var3 = var4;
                              if (var11 == null) {
                                 return var3;
                              }

                              var3 = var5;

                              try {
                                 var11.close();
                              } catch (Exception var35) {
                                 var40 = var35;
                                 var10001 = false;
                                 break label405;
                              }

                              var3 = var4;
                              return var3;
                           }

                           var1 = var3;
                        }
                     }

                     var3 = var1;
                     if (var11 == null) {
                        return var3;
                     }

                     var3 = var1;

                     try {
                        var11.close();
                        break label419;
                     } catch (Exception var36) {
                        var40 = var36;
                        var10001 = false;
                     }
                  }

                  Exception var39 = var40;
                  al.a(var39);
                  return var3;
               }

               var3 = var1;
            }

            return var3;
         }
      }
   }

   public final void a() {
      this.b = ap.b() + 86400000L;
      ak.a().a(new b(this), this.b - System.currentTimeMillis() + 5000L);
   }

   public final void a(int var1, boolean var2) {
      ac var4 = ac.a();
      byte var3 = 0;
      if (var4 != null && !var4.c().g && var1 != 1 && var1 != 3) {
         al.e("UserInfo is disable");
      } else {
         if (var1 == 1 || var1 == 3) {
            ++this.c;
         }

         aa var5 = aa.a(this.a);
         UserInfoBean var6 = new UserInfoBean();
         var6.b = var1;
         var6.c = var5.d;
         var6.d = var5.f();
         var6.e = System.currentTimeMillis();
         var6.f = -1L;
         var6.n = var5.o;
         if (var1 == 1) {
            var3 = 1;
         }

         var6.o = var3;
         var6.l = var5.a();
         var6.m = var5.y;
         var6.g = var5.z;
         var6.h = var5.A;
         var6.i = var5.B;
         var6.k = var5.C;
         var6.r = var5.t();
         var6.s = var5.y();
         var6.p = var5.z();
         var6.q = var5.x;
         ak.a().a(new a(this, var6, var2), 0L);
      }
   }

   public final void a(long var1) {
      ak.a().a(new c(this, var1), var1);
   }

   public final void b() {
      ak var1 = ak.a();
      if (var1 != null) {
         var1.a(new Runnable(this) {
            final boolean a;
            final r b;

            {
               this.b = var1;
               this.a = false;
            }

            public final void run() {
               try {
                  this.b.a(this.a);
               } catch (Throwable var3) {
                  al.a(var3);
                  return;
               }
            }
         });
      }

   }

   final class a implements Runnable {
      final r a;
      private boolean b;
      private UserInfoBean c;

      public a(r var1, UserInfoBean var2, boolean var3) {
         this.a = var1;
         this.c = var2;
         this.b = var3;
      }

      public final void run() {
         if (this.a.d) {
            Throwable var10000;
            label148: {
               UserInfoBean var1;
               boolean var10001;
               try {
                  var1 = this.c;
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label148;
               }

               if (var1 != null) {
                  try {
                     r.a(var1);
                     al.c("[UserInfo] Record user info.");
                     this.a.a(this.c, false);
                  } catch (Throwable var12) {
                     var10000 = var12;
                     var10001 = false;
                     break label148;
                  }
               }

               label136:
               try {
                  if (this.b) {
                     this.a.b();
                  }

                  return;
               } catch (Throwable var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label136;
               }
            }

            Throwable var14 = var10000;
            if (!al.a(var14)) {
               var14.printStackTrace();
            }

         }
      }
   }

   final class b implements Runnable {
      final r a;

      b(r var1) {
         this.a = var1;
      }

      public final void run() {
         long var1 = System.currentTimeMillis();
         if (var1 < this.a.b) {
            ak.a().a(this.a.new b(this.a), this.a.b - var1 + 5000L);
         } else {
            this.a.a(3, false);
            this.a.a();
         }
      }
   }

   final class c implements Runnable {
      final r a;
      private long b;

      public c(r var1, long var2) {
         this.a = var1;
         this.b = var2;
      }

      public final void run() {
         this.a.b();
         this.a.a(this.b);
      }
   }
}
