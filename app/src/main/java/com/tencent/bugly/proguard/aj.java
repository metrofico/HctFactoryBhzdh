package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Pair;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public final class aj implements Runnable {
   protected int a;
   protected long b;
   protected long c;
   private int d;
   private int e;
   private final Context f;
   private final int g;
   private final byte[] h;
   private final aa i;
   private final ac j;
   private final af k;
   private final ai l;
   private final int m;
   private final ah n;
   private final ah o;
   private String p;
   private final String q;
   private final Map r;
   private boolean s;

   public aj(Context var1, int var2, int var3, byte[] var4, String var5, String var6, ah var7, int var8, int var9, boolean var10) {
      this.d = 2;
      this.e = 30000;
      this.p = null;
      this.a = 0;
      this.b = 0L;
      this.c = 0L;
      this.s = false;
      this.f = var1;
      this.i = aa.a(var1);
      this.h = var4;
      this.j = ac.a();
      if (af.a == null) {
         af.a = new af(var1);
      }

      this.k = af.a;
      ai var11 = ai.a();
      this.l = var11;
      this.m = var2;
      this.p = var5;
      this.q = var6;
      this.n = var7;
      this.o = var11.a;
      this.g = var3;
      if (var8 > 0) {
         this.d = var8;
      }

      if (var9 > 0) {
         this.e = var9;
      }

      this.s = var10;
      this.r = null;
   }

   public aj(Context var1, int var2, int var3, byte[] var4, String var5, String var6, ah var7, boolean var8) {
      this(var1, var2, var3, var4, var5, var6, var7, 2, 30000, var8);
   }

   private Pair a(Map var1) {
      boolean var2;
      label198: {
         if (var1 != null && var1.size() != 0) {
            if (!var1.containsKey("status")) {
               al.d("[Upload] Headers does not contain %s", "status");
            } else if (!var1.containsKey("Bugly-Version")) {
               al.d("[Upload] Headers does not contain %s", "Bugly-Version");
            } else {
               String var4 = (String)var1.get("Bugly-Version");
               if (var4.contains("bugly")) {
                  al.c("[Upload] Bugly version from headers is: %s", var4);
                  var2 = true;
                  break label198;
               }

               al.d("[Upload] Bugly version is not valid: %s", var4);
            }
         } else {
            al.d("[Upload] Headers is empty.");
         }

         var2 = false;
      }

      if (!var2) {
         al.c("[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d).", Process.myPid(), Process.myTid());
         a("[Upload] Failed to upload for no status header.");
         if (var1 != null) {
            Iterator var12 = var1.entrySet().iterator();

            while(var12.hasNext()) {
               Map.Entry var14 = (Map.Entry)var12.next();
               al.c(String.format("[key]: %s, [value]: %s", var14.getKey(), var14.getValue()));
            }
         }

         al.c("[Upload] Failed to upload for no status header.");
         return new Pair(Boolean.FALSE, Boolean.TRUE);
      } else {
         int var13 = -1;

         int var3;
         label179: {
            label196: {
               boolean var10001;
               try {
                  var3 = Integer.parseInt((String)var1.get("status"));
               } catch (Throwable var10) {
                  var10001 = false;
                  break label196;
               }

               var13 = var3;

               label174:
               try {
                  al.c("[Upload] Status from server is %d (pid=%d | tid=%d).", var3, Process.myPid(), Process.myTid());
                  break label179;
               } catch (Throwable var9) {
                  var10001 = false;
                  break label174;
               }
            }

            a("[Upload] Failed to upload for format of status header is invalid: " + Integer.toString(var13));
            return new Pair(Boolean.FALSE, Boolean.TRUE);
         }

         Boolean var11;
         if (var3 != 0) {
            this.a(false, 1, "status of server is ".concat(String.valueOf(var3)));
            var11 = Boolean.FALSE;
            return new Pair(var11, var11);
         } else {
            var11 = Boolean.TRUE;
            return new Pair(var11, var11);
         }
      }
   }

   private Pair a(byte[] var1, Map var2) {
      if (var1 == null) {
         a("Failed to upload for no response!");
         return new Pair(Boolean.FALSE, Boolean.TRUE);
      } else {
         al.c("[Upload] Received %d bytes", var1.length);
         Boolean var4;
         if (var1.length != 0) {
            var4 = Boolean.TRUE;
            return new Pair(var4, var4);
         } else {
            this.a(false, 1, "response data from server is empty");
            if (var2 != null) {
               Iterator var3 = var2.entrySet().iterator();

               while(var3.hasNext()) {
                  Map.Entry var5 = (Map.Entry)var3.next();
                  al.c("[Upload] HTTP headers from server: key = %s, value = %s", var5.getKey(), var5.getValue());
               }
            }

            var4 = Boolean.FALSE;
            return new Pair(var4, var4);
         }
      }
   }

   private static void a(String var0) {
      al.e("[Upload] Failed to upload(%d): %s", 1, var0);
   }

   private void a(boolean var1, int var2, String var3) {
      String var11;
      label37: {
         label36: {
            int var4 = this.g;
            if (var4 != 630) {
               if (var4 == 640) {
                  break label36;
               }

               if (var4 != 830) {
                  if (var4 != 840) {
                     var11 = String.valueOf(var4);
                     break label37;
                  }
                  break label36;
               }
            }

            var11 = "crash";
            break label37;
         }

         var11 = "userinfo";
      }

      if (var1) {
         al.a("[Upload] Success: %s", var11);
      } else {
         al.e("[Upload] Failed to upload(%d) %s: %s", var2, var11, var3);
      }

      if (this.b + this.c > 0L) {
         long var7 = this.l.a(this.s);
         long var9 = this.b;
         long var5 = this.c;
         this.l.a(var7 + var9 + var5, this.s);
      }

      ah var12 = this.n;
      if (var12 != null) {
         var12.a(var1, var3);
      }

      var12 = this.o;
      if (var12 != null) {
         var12.a(var1, var3);
      }

   }

   private static boolean a(br var0, aa var1, ac var2) {
      if (var0 == null) {
         al.d("resp == null!");
         return false;
      } else if (var0.a != 0) {
         al.e("resp result error %d", var0.a);
         return false;
      } else {
         label67:
         try {
            if (!ap.b(var0.g) && !aa.b().i().equals(var0.g)) {
               w.a().a(ac.a, "device", var0.g.getBytes("UTF-8"), true);
               var1.d(var0.g);
            }
         } catch (Throwable var5) {
            al.a(var5);
            break label67;
         }

         var1.m = var0.e;
         if (var0.b == 510) {
            if (var0.c == null) {
               al.e("[Upload] Strategy data is null. Response cmd: %d", var0.b);
               return false;
            }

            bt var6 = (bt)ae.a(var0.c, bt.class);
            if (var6 == null) {
               al.e("[Upload] Failed to decode strategy from server. Response cmd: %d", var0.b);
               return false;
            }

            var2.a(var6);
         }

         return true;
      }
   }

   private static String b(String var0) {
      if (ap.b(var0)) {
         return var0;
      } else {
         try {
            String var1 = String.format("%s?aid=%s", var0, UUID.randomUUID().toString());
            return var1;
         } catch (Throwable var3) {
            al.a(var3);
            return var0;
         }
      }
   }

   public final void a(long var1) {
      ++this.a;
      this.b += var1;
   }

   public final void b(long var1) {
      this.c += var1;
   }

   public final void run() {
      Throwable var10000;
      label6135: {
         String var5;
         byte[] var713;
         boolean var10001;
         label6150: {
            label6133: {
               try {
                  this.a = 0;
                  this.b = 0L;
                  this.c = 0L;
                  if (ab.c(this.f) != null) {
                     break label6133;
                  }
               } catch (Throwable var712) {
                  var10000 = var712;
                  var10001 = false;
                  break label6135;
               }

               var5 = "network is not available";
               break label6150;
            }

            try {
               var713 = this.h;
            } catch (Throwable var707) {
               var10000 = var707;
               var10001 = false;
               break label6135;
            }

            if (var713 != null) {
               label6147: {
                  try {
                     if (var713.length == 0) {
                        break label6147;
                     }
                  } catch (Throwable var711) {
                     var10000 = var711;
                     var10001 = false;
                     break label6135;
                  }

                  label6140: {
                     ac var714;
                     try {
                        if (this.f == null || this.i == null) {
                           break label6140;
                        }

                        var714 = this.j;
                     } catch (Throwable var710) {
                        var10000 = var710;
                        var10001 = false;
                        break label6135;
                     }

                     if (var714 != null) {
                        label6148: {
                           try {
                              if (this.k == null) {
                                 break label6148;
                              }
                           } catch (Throwable var709) {
                              var10000 = var709;
                              var10001 = false;
                              break label6135;
                           }

                           label6105: {
                              try {
                                 if (var714.c() == null) {
                                    break label6105;
                                 }
                              } catch (Throwable var708) {
                                 var10000 = var708;
                                 var10001 = false;
                                 break label6135;
                              }

                              var5 = null;
                              break label6150;
                           }

                           var5 = "illegal local strategy";
                           break label6150;
                        }
                     }
                  }

                  var5 = "illegal access error";
                  break label6150;
               }
            }

            var5 = "request package is empty!";
         }

         if (var5 != null) {
            label6016:
            try {
               this.a(false, 0, var5);
               return;
            } catch (Throwable var687) {
               var10000 = var687;
               var10001 = false;
               break label6016;
            }
         } else {
            label6096: {
               byte[] var9;
               try {
                  var9 = ap.a(this.h);
               } catch (Throwable var706) {
                  var10000 = var706;
                  var10001 = false;
                  break label6096;
               }

               if (var9 == null) {
                  label6018:
                  try {
                     this.a(false, 0, "failed to zip request body");
                     return;
                  } catch (Throwable var688) {
                     var10000 = var688;
                     var10001 = false;
                     break label6018;
                  }
               } else {
                  label6142: {
                     HashMap var8;
                     Map var715;
                     try {
                        var8 = new HashMap(10);
                        var8.put("tls", "1");
                        var8.put("prodId", this.i.e());
                        var8.put("bundleId", this.i.c);
                        var8.put("appVer", this.i.o);
                        var715 = this.r;
                     } catch (Throwable var705) {
                        var10000 = var705;
                        var10001 = false;
                        break label6142;
                     }

                     if (var715 != null) {
                        try {
                           var8.putAll(var715);
                        } catch (Throwable var704) {
                           var10000 = var704;
                           var10001 = false;
                           break label6142;
                        }
                     }

                     try {
                        var8.put("cmd", Integer.toString(this.g));
                        var8.put("platformId", Byte.toString((byte)1));
                        var8.put("sdkVer", this.i.h);
                        var8.put("strategylastUpdateTime", Long.toString(this.j.c().o));
                        this.l.a(this.m, System.currentTimeMillis());
                        var5 = this.p;
                        this.j.c();
                     } catch (Throwable var703) {
                        var10000 = var703;
                        var10001 = false;
                        break label6142;
                     }

                     int var2 = 0;
                     int var1 = 0;

                     while(true) {
                        int var3 = var2 + 1;

                        try {
                           if (var2 >= this.d) {
                              break;
                           }
                        } catch (Throwable var697) {
                           var10000 = var697;
                           var10001 = false;
                           break label6142;
                        }

                        String var6 = var5;
                        if (var3 > 1) {
                           try {
                              al.d("[Upload] Failed to upload last time, wait and try(%d) again.", var3);
                              ap.b((long)this.e);
                           } catch (Throwable var696) {
                              var10000 = var696;
                              var10001 = false;
                              break label6142;
                           }

                           var6 = var5;

                           try {
                              if (var3 == this.d) {
                                 al.d("[Upload] Use the back-up url at the last time: %s", this.q);
                                 var6 = this.q;
                              }
                           } catch (Throwable var695) {
                              var10000 = var695;
                              var10001 = false;
                              break label6142;
                           }
                        }

                        boolean var4;
                        label6151: {
                           Pair var7;
                           Map var10;
                           try {
                              al.c("[Upload] Send %d bytes", var9.length);
                              var6 = b(var6);
                              al.c("[Upload] Upload to %s with cmd %d (pid=%d | tid=%d).", var6, this.g, Process.myPid(), Process.myTid());
                              var713 = this.k.a(var6, var9, (aj)this, var8);
                              var10 = this.k.c;
                              var7 = this.a(var713, var10);
                              if (!(Boolean)var7.first) {
                                 var4 = (Boolean)var7.second;
                                 break label6151;
                              }
                           } catch (Throwable var702) {
                              var10000 = var702;
                              var10001 = false;
                              break label6142;
                           }

                           try {
                              var7 = this.a(var10);
                              if (!(Boolean)var7.first) {
                                 var4 = (Boolean)var7.second;
                                 break label6151;
                              }
                           } catch (Throwable var701) {
                              var10000 = var701;
                              var10001 = false;
                              break label6142;
                           }

                           byte[] var716;
                           try {
                              var716 = ap.b(var713);
                           } catch (Throwable var694) {
                              var10000 = var694;
                              var10001 = false;
                              break label6142;
                           }

                           if (var716 != null) {
                              var713 = var716;
                           }

                           br var718;
                           try {
                              var718 = ae.a(var713);
                           } catch (Throwable var693) {
                              var10000 = var693;
                              var10001 = false;
                              break label6142;
                           }

                           Pair var720;
                           if (var718 == null) {
                              try {
                                 this.a(false, 1, "failed to decode response package");
                                 Boolean var717 = Boolean.FALSE;
                                 var720 = new Pair(var717, var717);
                              } catch (Throwable var692) {
                                 var10000 = var692;
                                 var10001 = false;
                                 break label6142;
                              }
                           } else {
                              label6152: {
                                 label6058: {
                                    label6057: {
                                       try {
                                          var2 = var718.b;
                                          if (var718.c == null) {
                                             break label6057;
                                          }
                                       } catch (Throwable var700) {
                                          var10000 = var700;
                                          var10001 = false;
                                          break label6142;
                                       }

                                       try {
                                          var1 = var718.c.length;
                                          break label6058;
                                       } catch (Throwable var691) {
                                          var10000 = var691;
                                          var10001 = false;
                                          break label6142;
                                       }
                                    }

                                    var1 = 0;
                                 }

                                 Boolean var719;
                                 try {
                                    al.c("[Upload] Response cmd is: %d, length of sBuffer is: %d", var2, var1);
                                    if (!a(var718, this.i, this.j)) {
                                       this.a(false, 2, "failed to process response package");
                                       var719 = Boolean.FALSE;
                                       var720 = new Pair(var719, var719);
                                       break label6152;
                                    }
                                 } catch (Throwable var699) {
                                    var10000 = var699;
                                    var10001 = false;
                                    break label6142;
                                 }

                                 try {
                                    this.a(true, 2, "successfully uploaded");
                                    var719 = Boolean.TRUE;
                                    var720 = new Pair(var719, var719);
                                 } catch (Throwable var690) {
                                    var10000 = var690;
                                    var10001 = false;
                                    break label6142;
                                 }
                              }
                           }

                           try {
                              if (!(Boolean)var720.first) {
                                 var4 = (Boolean)var720.second;
                                 break label6151;
                              }
                           } catch (Throwable var698) {
                              var10000 = var698;
                              var10001 = false;
                              break label6142;
                           }

                           var4 = false;
                        }

                        if (!var4) {
                           return;
                        }

                        var1 = 1;
                        var2 = var3;
                        var5 = var6;
                     }

                     label6020:
                     try {
                        this.a(false, var1, "failed after many attempts");
                        return;
                     } catch (Throwable var689) {
                        var10000 = var689;
                        var10001 = false;
                        break label6020;
                     }
                  }
               }
            }
         }
      }

      Throwable var721 = var10000;
      if (!al.a(var721)) {
         var721.printStackTrace();
      }

   }
}
