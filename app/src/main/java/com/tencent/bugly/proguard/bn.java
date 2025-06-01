package com.tencent.bugly.proguard;

public final class bn extends m implements Cloneable {
   static byte[] d;
   public byte a;
   public String b;
   public byte[] c;

   public bn() {
      this.a = 0;
      this.b = "";
      this.c = null;
   }

   public bn(byte var1, String var2, byte[] var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public final void a(k var1) {
      this.a = var1.a((byte)this.a, 0, true);
      this.b = var1.b(1, true);
      if (d == null) {
         byte[] var2 = (byte[])(new byte[1]);
         d = var2;
         ((byte[])var2)[0] = 0;
      }

      this.c = (byte[])var1.c(2, false);
   }

   public final void a(l var1) {
      var1.a((byte)this.a, 0);
      var1.a((String)this.b, 1);
      byte[] var2 = this.c;
      if (var2 != null) {
         var1.a((byte[])var2, 2);
      }

   }

   public final void a(StringBuilder var1, int var2) {
   }
}
