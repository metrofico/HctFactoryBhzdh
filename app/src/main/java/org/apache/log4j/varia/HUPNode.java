package org.apache.log4j.varia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class HUPNode implements Runnable {
   DataInputStream dis;
   DataOutputStream dos;
   ExternallyRolledFileAppender er;
   Socket socket;

   public HUPNode(Socket var1, ExternallyRolledFileAppender var2) {
      this.socket = var1;
      this.er = var2;

      try {
         DataInputStream var4 = new DataInputStream(var1.getInputStream());
         this.dis = var4;
         DataOutputStream var5 = new DataOutputStream(var1.getOutputStream());
         this.dos = var5;
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void run() {
      // $FF: Couldn't be decompiled
   }
}
