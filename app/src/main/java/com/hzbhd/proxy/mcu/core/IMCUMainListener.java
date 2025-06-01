package com.hzbhd.proxy.mcu.core;

public interface IMCUMainListener {
   void mcuInit(byte var1, boolean var2, boolean var3);

   void notifyCanboxVersion(String var1);

   void notifyHardwareVersion(String var1, String var2, String var3, String var4);

   void notifyMCUVersion(String var1, String var2, String var3);

   void notifyPowerStatus(int var1);

   void notifyScreenVersion(String var1);
}
