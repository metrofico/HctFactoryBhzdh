package com.hzbhd.proxy.mcu.upgrade;

public interface IMCUUpgradeListener {
   void notifyMCUUpgradeEndCheckStatus(UpgradeConstants.UpgradeStartEndStatus var1);

   void notifyMCUUpgradeSendDataSeq(int var1);

   void notifyMCUUpgradeStartCheckStatus(boolean var1, UpgradeConstants.UpgradeStartCheckStatus var2);
}
