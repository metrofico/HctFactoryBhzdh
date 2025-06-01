package com.tencent.bugly.proguard;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.net.Proxy.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class af {
   static af a;
   protected Context b;
   public Map c = null;

   af(Context var1) {
      this.b = var1;
   }

   private static HttpURLConnection a(String var0, String var1) {
      Throwable var10000;
      label235: {
         boolean var10001;
         HttpURLConnection var25;
         label236: {
            URL var2;
            try {
               var2 = new URL(var1);
               if (an.a != null) {
                  var25 = (HttpURLConnection)var2.openConnection(an.a);
                  break label236;
               }
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label235;
            }

            if (var0 != null) {
               try {
                  if (var0.toLowerCase(Locale.US).contains("wap")) {
                     var1 = System.getProperty("http.proxyHost");
                     String var3 = System.getProperty("http.proxyPort");
                     InetSocketAddress var24 = new InetSocketAddress(var1, Integer.parseInt(var3));
                     Proxy var27 = new Proxy(Type.HTTP, var24);
                     var25 = (HttpURLConnection)var2.openConnection(var27);
                     break label236;
                  }
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label235;
               }
            }

            try {
               var25 = (HttpURLConnection)var2.openConnection();
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label235;
            }
         }

         label210:
         try {
            var25.setConnectTimeout(30000);
            var25.setReadTimeout(10000);
            var25.setDoOutput(true);
            var25.setDoInput(true);
            var25.setRequestMethod("POST");
            var25.setUseCaches(false);
            var25.setInstanceFollowRedirects(false);
            return var25;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            break label210;
         }
      }

      Throwable var26 = var10000;
      if (!al.a(var26)) {
         var26.printStackTrace();
      }

      return null;
   }

   private static HttpURLConnection a(String var0, byte[] var1, String var2, Map var3) {
      if (var0 == null) {
         al.e("destUrl is null.");
         return null;
      } else {
         HttpURLConnection var61 = a(var2, var0);
         if (var61 == null) {
            al.e("Failed to get HttpURLConnection object.");
            return null;
         } else {
            Throwable var10000;
            label528: {
               boolean var10001;
               try {
                  var61.setRequestProperty("wup_version", "3.0");
               } catch (Throwable var60) {
                  var10000 = var60;
                  var10001 = false;
                  break label528;
               }

               if (var3 != null) {
                  label517: {
                     Iterator var64;
                     try {
                        if (var3.size() <= 0) {
                           break label517;
                        }

                        var64 = var3.entrySet().iterator();
                     } catch (Throwable var59) {
                        var10000 = var59;
                        var10001 = false;
                        break label528;
                     }

                     while(true) {
                        try {
                           if (!var64.hasNext()) {
                              break;
                           }

                           Map.Entry var4 = (Map.Entry)var64.next();
                           var61.setRequestProperty((String)var4.getKey(), URLEncoder.encode((String)var4.getValue(), "utf-8"));
                        } catch (Throwable var58) {
                           var10000 = var58;
                           var10001 = false;
                           break label528;
                        }
                     }
                  }
               }

               OutputStream var63;
               try {
                  var61.setRequestProperty("A37", URLEncoder.encode(var2, "utf-8"));
                  var61.setRequestProperty("A38", URLEncoder.encode(var2, "utf-8"));
                  var63 = var61.getOutputStream();
               } catch (Throwable var57) {
                  var10000 = var57;
                  var10001 = false;
                  break label528;
               }

               if (var1 == null) {
                  try {
                     var63.write(0);
                  } catch (Throwable var56) {
                     var10000 = var56;
                     var10001 = false;
                     break label528;
                  }
               } else {
                  try {
                     var63.write(var1);
                  } catch (Throwable var55) {
                     var10000 = var55;
                     var10001 = false;
                     break label528;
                  }
               }

               label498:
               try {
                  return var61;
               } catch (Throwable var54) {
                  var10000 = var54;
                  var10001 = false;
                  break label498;
               }
            }

            Throwable var62 = var10000;
            if (!al.a(var62)) {
               var62.printStackTrace();
            }

            al.e("Failed to upload, please check your network.");
            return null;
         }
      }
   }

   private static Map a(HttpURLConnection var0) {
      HashMap var1 = new HashMap();
      Map var5 = var0.getHeaderFields();
      if (var5 != null && var5.size() != 0) {
         Iterator var2 = var5.keySet().iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            List var4 = (List)var5.get(var3);
            if (var4.size() > 0) {
               var1.put(var3, var4.get(0));
            }
         }

         return var1;
      } else {
         return null;
      }
   }

   private static byte[] b(HttpURLConnection var0) {
      if (var0 == null) {
         return null;
      } else {
         BufferedInputStream var3;
         try {
            var3 = new BufferedInputStream(var0.getInputStream());
         } finally {
            ;
         }

         byte[] var90;
         label689: {
            Throwable var10000;
            label682: {
               byte[] var2;
               boolean var10001;
               ByteArrayOutputStream var88;
               try {
                  var88 = new ByteArrayOutputStream();
                  var2 = new byte[1024];
               } catch (Throwable var87) {
                  var10000 = var87;
                  var10001 = false;
                  break label682;
               }

               while(true) {
                  int var1;
                  try {
                     var1 = var3.read(var2);
                  } catch (Throwable var85) {
                     var10000 = var85;
                     var10001 = false;
                     break;
                  }

                  if (var1 <= 0) {
                     try {
                        var88.flush();
                        var90 = var88.toByteArray();
                        break label689;
                     } catch (Throwable var84) {
                        var10000 = var84;
                        var10001 = false;
                        break;
                     }
                  }

                  try {
                     var88.write(var2, 0, var1);
                  } catch (Throwable var86) {
                     var10000 = var86;
                     var10001 = false;
                     break;
                  }
               }
            }

            Throwable var91 = var10000;
            BufferedInputStream var89 = var3;

            try {
               if (!al.a(var91)) {
                  var91.printStackTrace();
               }
            } finally {
               if (var3 != null) {
                  label657:
                  try {
                     var89.close();
                  } catch (Throwable var81) {
                     var81.printStackTrace();
                     break label657;
                  }
               }

            }

            return null;
         }

         try {
            var3.close();
         } catch (Throwable var83) {
            var83.printStackTrace();
            return var90;
         }

         return var90;
      }
   }

   public final byte[] a(String param1, byte[] param2, aj param3, Map param4) {
      // $FF: Couldn't be decompiled
   }
}
