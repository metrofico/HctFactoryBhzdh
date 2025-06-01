package com.hzbhd.midware.jni;

public class SerialPort {
   static {
      System.loadLibrary("SerialPort");
   }

   public static native void jni_serial_close(int var0);

   public static native int jni_serial_open(int var0);

   public static native int jni_serial_read_data(int var0, byte[] var1, int var2, int var3);

   public static native int jni_serial_setup(int var0, int var1, int var2, char var3, int var4);

   public static native int jni_serial_write_data(int var0, byte[] var1, int var2);
}
