package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class u {
   public static final long a = System.currentTimeMillis();
   private static u b;
   private Context c;
   private String d;
   private Map e;
   private SharedPreferences f;

   private u(Context var1) {
      this.c = var1;
      this.e = new HashMap();
      this.d = aa.b().d;
      this.f = var1.getSharedPreferences("crashrecord", 0);
   }

   public static u a() {
      synchronized(u.class){}

      u var0;
      try {
         var0 = b;
      } finally {
         ;
      }

      return var0;
   }

   public static u a(Context var0) {
      synchronized(u.class){}

      u var4;
      try {
         if (b == null) {
            u var1 = new u(var0);
            b = var1;
         }

         var4 = b;
      } finally {
         ;
      }

      return var4;
   }

   private void a(int param1, List param2) {
      // $FF: Couldn't be decompiled
   }

   // $FF: synthetic method
   static boolean a(t var0, t var1) {
      return var0.g == var1.g && var0.b != null && var0.b.equalsIgnoreCase(var1.b);
   }

   // $FF: synthetic method
   static boolean b(t var0, t var1) {
      return var0.e != null && !var0.e.equalsIgnoreCase(var1.e) || var0.f != null && !var0.f.equalsIgnoreCase(var1.f) || var0.d <= 0;
   }

   private boolean c(int param1) {
      // $FF: Couldn't be decompiled
   }

   private List d(int param1) {
      // $FF: Couldn't be decompiled
   }

   public final void a(int var1) {
      ak.a().a(new Runnable(this, var1) {
         final int a;
         final int b;
         final u c;

         {
            this.c = var1;
            this.a = 1004;
            this.b = var2;
         }

         public final void run() {
            label124: {
               boolean var10001;
               try {
                  if (TextUtils.isEmpty(this.c.d)) {
                     return;
                  }
               } catch (Exception var21) {
                  var10001 = false;
                  break label124;
               }

               List var4;
               try {
                  var4 = this.c.d(this.a);
               } catch (Exception var20) {
                  var10001 = false;
                  break label124;
               }

               Object var3 = var4;
               if (var4 == null) {
                  try {
                     var3 = new ArrayList();
                  } catch (Exception var19) {
                     var10001 = false;
                     break label124;
                  }
               }

               try {
                  if (this.c.e.get(this.a) == null) {
                     HashMap var22 = new HashMap();
                     this.c.e.put(this.a, var22);
                  }
               } catch (Exception var18) {
                  var10001 = false;
                  break label124;
               }

               t var23;
               label127: {
                  try {
                     if (((Map)this.c.e.get(this.a)).get(this.c.d) == null) {
                        var23 = new t();
                        var23.a = (long)this.a;
                        var23.g = u.a;
                        var23.b = this.c.d;
                        var23.f = aa.b().o;
                        var23.e = aa.b().h;
                        var23.c = System.currentTimeMillis();
                        var23.d = this.b;
                        ((Map)this.c.e.get(this.a)).put(this.c.d, var23);
                        break label127;
                     }
                  } catch (Exception var17) {
                     var10001 = false;
                     break label124;
                  }

                  try {
                     var23 = (t)((Map)this.c.e.get(this.a)).get(this.c.d);
                     var23.d = this.b;
                  } catch (Exception var16) {
                     var10001 = false;
                     break label124;
                  }
               }

               ArrayList var5;
               Iterator var7;
               try {
                  var5 = new ArrayList();
                  var7 = ((List)var3).iterator();
               } catch (Exception var15) {
                  var10001 = false;
                  break label124;
               }

               boolean var1 = false;

               while(true) {
                  t var6;
                  try {
                     if (!var7.hasNext()) {
                        break;
                     }

                     var6 = (t)var7.next();
                  } catch (Exception var13) {
                     var10001 = false;
                     break label124;
                  }

                  boolean var2 = var1;

                  label126: {
                     try {
                        if (!u.a(var6, var23)) {
                           break label126;
                        }
                     } catch (Exception var14) {
                        var10001 = false;
                        break label124;
                     }

                     var2 = true;

                     try {
                        var6.d = var23.d;
                     } catch (Exception var11) {
                        var10001 = false;
                        break label124;
                     }
                  }

                  var1 = var2;

                  try {
                     if (!u.b(var6, var23)) {
                        continue;
                     }

                     var5.add(var6);
                  } catch (Exception var12) {
                     var10001 = false;
                     break label124;
                  }

                  var1 = var2;
               }

               try {
                  ((List)var3).removeAll(var5);
               } catch (Exception var10) {
                  var10001 = false;
                  break label124;
               }

               if (!var1) {
                  try {
                     ((List)var3).add(var23);
                  } catch (Exception var9) {
                     var10001 = false;
                     break label124;
                  }
               }

               try {
                  this.c.a(this.a, (List)var3);
                  return;
               } catch (Exception var8) {
                  var10001 = false;
               }
            }

            al.e("saveCrashRecord failed");
         }
      });
   }

   public final boolean b(int param1) {
      // $FF: Couldn't be decompiled
   }
}
