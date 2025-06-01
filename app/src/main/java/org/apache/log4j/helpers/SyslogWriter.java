package org.apache.log4j.helpers;

import java.io.IOException;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

public class SyslogWriter extends Writer {
   static String syslogHost;
   final int SYSLOG_PORT;
   private InetAddress address;
   private DatagramSocket ds;
   private final int port;

   public SyslogWriter(String var1) {
      short var3 = 514;
      this.SYSLOG_PORT = 514;
      syslogHost = var1;
      if (var1 == null) {
         throw new NullPointerException("syslogHost");
      } else {
         int var2;
         label122: {
            String var5;
            label126: {
               if (var1.indexOf("[") == -1) {
                  var5 = var1;
                  if (var1.indexOf(58) != var1.lastIndexOf(58)) {
                     break label126;
                  }
               }

               String var4 = var1;

               MalformedURLException var10000;
               label127: {
                  URL var6;
                  boolean var10001;
                  try {
                     var6 = new URL;
                  } catch (MalformedURLException var18) {
                     var10000 = var18;
                     var10001 = false;
                     break label127;
                  }

                  var4 = var1;

                  StringBuffer var20;
                  try {
                     var20 = new StringBuffer;
                  } catch (MalformedURLException var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label127;
                  }

                  var4 = var1;

                  try {
                     var20.<init>();
                  } catch (MalformedURLException var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label127;
                  }

                  var4 = var1;

                  try {
                     var6.<init>(var20.append("http://").append(var1).toString());
                  } catch (MalformedURLException var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label127;
                  }

                  var5 = var1;
                  var4 = var1;

                  try {
                     if (var6.getHost() == null) {
                        break label126;
                     }
                  } catch (MalformedURLException var14) {
                     var10000 = var14;
                     var10001 = false;
                     break label127;
                  }

                  var4 = var1;

                  try {
                     var5 = var6.getHost();
                  } catch (MalformedURLException var13) {
                     var10000 = var13;
                     var10001 = false;
                     break label127;
                  }

                  var1 = var5;
                  var4 = var5;

                  label128: {
                     try {
                        if (!var5.startsWith("[")) {
                           break label128;
                        }
                     } catch (MalformedURLException var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label127;
                     }

                     var1 = var5;
                     var4 = var5;

                     try {
                        if (var5.charAt(var5.length() - 1) != ']') {
                           break label128;
                        }
                     } catch (MalformedURLException var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label127;
                     }

                     var4 = var5;

                     try {
                        var1 = var5.substring(1, var5.length() - 1);
                     } catch (MalformedURLException var10) {
                        var10000 = var10;
                        var10001 = false;
                        break label127;
                     }
                  }

                  var4 = var1;

                  try {
                     var2 = var6.getPort();
                     break label122;
                  } catch (MalformedURLException var9) {
                     var10000 = var9;
                     var10001 = false;
                  }
               }

               MalformedURLException var19 = var10000;
               LogLog.error("Malformed URL: will attempt to interpret as InetAddress.", var19);
               var5 = var4;
            }

            var2 = -1;
            var1 = var5;
         }

         if (var2 == -1) {
            var2 = var3;
         }

         this.port = var2;

         try {
            this.address = InetAddress.getByName(var1);
         } catch (UnknownHostException var8) {
            LogLog.error("Could not find " + var1 + ". All logging will FAIL.", var8);
         }

         try {
            DatagramSocket var21 = new DatagramSocket();
            this.ds = var21;
         } catch (SocketException var7) {
            var7.printStackTrace();
            LogLog.error("Could not instantiate DatagramSocket to " + var1 + ". All logging will FAIL.", var7);
         }

      }
   }

   public void close() {
   }

   public void flush() {
   }

   public void write(String var1) throws IOException {
      byte[] var3 = var1.getBytes();
      DatagramPacket var2 = new DatagramPacket(var3, var3.length, this.address, this.port);
      DatagramSocket var4 = this.ds;
      if (var4 != null && this.address != null) {
         var4.send(var2);
      }

   }

   public void write(char[] var1, int var2, int var3) throws IOException {
      this.write(new String(var1, var2, var3));
   }
}
