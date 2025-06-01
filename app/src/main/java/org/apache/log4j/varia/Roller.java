package org.apache.log4j.varia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Roller {
   static Logger cat;
   static Class class$org$apache$log4j$varia$Roller;
   static String host;
   static int port;

   static {
      Class var1 = class$org$apache$log4j$varia$Roller;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.varia.Roller");
         class$org$apache$log4j$varia$Roller = var0;
      }

      cat = Logger.getLogger(var0);
   }

   Roller() {
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   static void init(String var0, String var1) {
      host = var0;

      try {
         port = Integer.parseInt(var1);
      } catch (NumberFormatException var2) {
         usage("Second argument " + var1 + " is not a valid integer.");
      }

   }

   public static void main(String[] var0) {
      BasicConfigurator.configure();
      if (var0.length == 2) {
         init(var0[0], var0[1]);
      } else {
         usage("Wrong number of arguments.");
      }

      roll();
   }

   static void roll() {
      try {
         Socket var2 = new Socket(host, port);
         DataOutputStream var1 = new DataOutputStream(var2.getOutputStream());
         DataInputStream var0 = new DataInputStream(var2.getInputStream());
         var1.writeUTF("RollOver");
         String var5 = var0.readUTF();
         if ("OK".equals(var5)) {
            cat.info("Roll over signal acknowledged by remote appender.");
         } else {
            Logger var4 = cat;
            StringBuffer var6 = new StringBuffer();
            var4.warn(var6.append("Unexpected return code ").append(var5).append(" from remote entity.").toString());
            System.exit(2);
         }
      } catch (IOException var3) {
         cat.error("Could not send roll signal on host " + host + " port " + port + " .", var3);
         System.exit(2);
      }

      System.exit(0);
   }

   static void usage(String var0) {
      System.err.println(var0);
      PrintStream var3 = System.err;
      StringBuffer var2 = (new StringBuffer()).append("Usage: java ");
      Class var1 = class$org$apache$log4j$varia$Roller;
      Class var4 = var1;
      if (var1 == null) {
         var4 = class$("org.apache.log4j.varia.Roller");
         class$org$apache$log4j$varia$Roller = var4;
      }

      var3.println(var2.append(var4.getName()).append("host_name port_number").toString());
      System.exit(1);
   }
}
