package com.hzbhd.proxy.mcu.upgrade;

import com.hzbhd.proxy.mcu.base.IMCUBaseManager;

public interface IMCUUpgradeManager extends IMCUBaseManager {
   void registerMCUUpgradeListener(IMCUUpgradeListener var1);

   int reqUpgrade(UpgradeConstants.DevType var1);

   int sendUpgradeData(byte[] var1, int var2, int var3, int var4);

   void unRegisterMCUUpgradeListener(IMCUUpgradeListener var1);

   byte[] updateFlashData(byte[] var1);

   int upgradeEnd(int var1);

   boolean upgradeStart(boolean var1, boolean var2, int var3, int var4, int var5, int var6);
}
