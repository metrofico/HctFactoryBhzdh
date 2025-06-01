package com.hzbhd.proxy.mcu.interfaces;

public interface ISerialPortDevice {
   void closeSerialDev(int var1);

   int openSerialDev(int var1);

   int readSerialData(int var1, byte[] var2, int var3, int var4);

   int setupSerialDev(int var1, int var2, int var3, char var4, int var5);

   int writeSerialData(int var1, byte[] var2, int var3);
}
