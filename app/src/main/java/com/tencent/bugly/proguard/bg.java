package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

public final class bg extends Thread {
   public bf a;
   public boolean b = true;
   private boolean c = false;
   private boolean d = true;
   private boolean e = false;
   private int f = 1;
   private a g;

   private void a(bf var1) {
      synchronized(this){}

      Throwable var10000;
      label96: {
         boolean var10001;
         boolean var2;
         try {
            var2 = this.d;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label96;
         }

         if (var2) {
            return;
         }

         label87:
         try {
            if (this.e && !var1.a()) {
               al.c("Restart getting main stack trace.");
               this.d = true;
               this.e = false;
            }

            return;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label87;
         }
      }

      Throwable var9 = var10000;
      throw var9;
   }

   public final boolean a() {
      this.c = true;
      if (!this.isAlive()) {
         return false;
      } else {
         try {
            this.interrupt();
         } catch (Exception var2) {
            al.b(var2);
         }

         al.d("MainHandlerChecker is reset to null.");
         this.a = null;
         return true;
      }
   }

   public final boolean b() {
      Handler var3 = new Handler(Looper.getMainLooper());
      bf var4 = this.a;
      if (var4 != null) {
         var4.b = 5000L;
      } else {
         this.a = new bf(var3, var3.getLooper().getThread().getName());
      }

      boolean var2 = this.isAlive();
      boolean var1 = false;
      if (var2) {
         return false;
      } else {
         try {
            this.start();
         } catch (Exception var5) {
            al.b(var5);
            return var1;
         }

         var1 = true;
         return var1;
      }
   }

   public final void c() {
      synchronized(this){}

      try {
         this.d = false;
         al.c("Record stack trace is disabled.");
      } finally {
         ;
      }

   }

   public final void d() {
      synchronized(this){}

      try {
         this.e = true;
      } finally {
         ;
      }

   }

   public final void run() {
      long var6 = System.currentTimeMillis();

      while(!this.c) {
         Exception var38;
         label144: {
            OutOfMemoryError var10000;
            label143: {
               bf var10;
               boolean var10001;
               try {
                  var10 = this.a;
               } catch (Exception var33) {
                  var38 = var33;
                  var10001 = false;
                  break label144;
               } catch (OutOfMemoryError var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label143;
               }

               boolean var3 = false;
               if (var10 == null) {
                  try {
                     al.c("Main handler checker is null. Stop thread monitor.");
                     return;
                  } catch (Exception var11) {
                     var38 = var11;
                     var10001 = false;
                     break label144;
                  } catch (OutOfMemoryError var12) {
                     var10000 = var12;
                     var10001 = false;
                  }
               } else {
                  label150: {
                     try {
                        if (var10.c) {
                           var10.c = false;
                           var10.d = SystemClock.uptimeMillis();
                           var10.a.post(var10);
                        }
                     } catch (Exception var31) {
                        var38 = var31;
                        var10001 = false;
                        break label144;
                     } catch (OutOfMemoryError var32) {
                        var10000 = var32;
                        var10001 = false;
                        break label150;
                     }

                     boolean var5;
                     try {
                        this.a(var10);
                        var5 = this.b;
                     } catch (Exception var29) {
                        var38 = var29;
                        var10001 = false;
                        break label144;
                     } catch (OutOfMemoryError var30) {
                        var10000 = var30;
                        var10001 = false;
                        break label150;
                     }

                     boolean var2 = true;
                     boolean var4 = true;
                     boolean var1 = var3;
                     if (var5) {
                        label131: {
                           label151: {
                              try {
                                 if (!this.d) {
                                    break label151;
                                 }
                              } catch (Exception var27) {
                                 var38 = var27;
                                 var10001 = false;
                                 break label144;
                              } catch (OutOfMemoryError var28) {
                                 var10000 = var28;
                                 var10001 = false;
                                 break label150;
                              }

                              long var8;
                              try {
                                 var8 = var10.b();
                              } catch (Exception var25) {
                                 var38 = var25;
                                 var10001 = false;
                                 break label144;
                              } catch (OutOfMemoryError var26) {
                                 var10000 = var26;
                                 var10001 = false;
                                 break label150;
                              }

                              var1 = var3;
                              if (var8 > 1510L) {
                                 if (var8 >= 199990L) {
                                    var1 = var3;
                                 } else if (var8 <= 5010L) {
                                    try {
                                       this.f = 1;
                                       al.c("timeSinceMsgSent in [2s, 5s], record stack");
                                    } catch (Exception var23) {
                                       var38 = var23;
                                       var10001 = false;
                                       break label144;
                                    } catch (OutOfMemoryError var24) {
                                       var10000 = var24;
                                       var10001 = false;
                                       break label150;
                                    }

                                    var1 = var4;
                                 } else {
                                    int var35;
                                    try {
                                       var35 = this.f + 1;
                                       this.f = var35;
                                    } catch (Exception var21) {
                                       var38 = var21;
                                       var10001 = false;
                                       break label144;
                                    } catch (OutOfMemoryError var22) {
                                       var10000 = var22;
                                       var10001 = false;
                                       break label150;
                                    }

                                    if ((var35 & var35 - 1) != 0) {
                                       var2 = false;
                                    }

                                    var1 = var2;
                                    if (var2) {
                                       try {
                                          al.c("timeSinceMsgSent in (5s, 200s), should record stack:true");
                                       } catch (Exception var19) {
                                          var38 = var19;
                                          var10001 = false;
                                          break label144;
                                       } catch (OutOfMemoryError var20) {
                                          var10000 = var20;
                                          var10001 = false;
                                          break label150;
                                       }

                                       var1 = var2;
                                    }
                                 }
                              }
                              break label131;
                           }

                           var1 = var3;
                        }
                     }

                     if (var1) {
                        try {
                           var10.d();
                        } catch (Exception var17) {
                           var38 = var17;
                           var10001 = false;
                           break label144;
                        } catch (OutOfMemoryError var18) {
                           var10000 = var18;
                           var10001 = false;
                           break label150;
                        }
                     }

                     try {
                        if (this.g != null && this.d) {
                           var10.a();
                           var10.b();
                        }
                     } catch (Exception var15) {
                        var38 = var15;
                        var10001 = false;
                        break label144;
                     } catch (OutOfMemoryError var16) {
                        var10000 = var16;
                        var10001 = false;
                        break label150;
                     }

                     try {
                        ap.b(500L - (System.currentTimeMillis() - var6) % 500L);
                        continue;
                     } catch (Exception var13) {
                        var38 = var13;
                        var10001 = false;
                        break label144;
                     } catch (OutOfMemoryError var14) {
                        var10000 = var14;
                        var10001 = false;
                     }
                  }
               }
            }

            OutOfMemoryError var36 = var10000;
            al.b(var36);
            continue;
         }

         Exception var37 = var38;
         al.b(var37);
      }

   }

   public interface a {
   }
}
