package com.hzbhd.proxy.mcu.core;

import android.os.Bundle;
import com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback;
import com.hzbhd.proxy.mcu.base.IMCUBaseManager;
import com.hzbhd.proxy.mcu.constant.MCU_MESSAGE_PEER;

public interface IMCUMainManager extends IMCUBaseManager {
   void clearMessage(MCU_MESSAGE_PEER var1);

   void closeDevice();

   int getCarBackState();

   int getHardwareReset();

   int getMcuhardware();

   int getPowerOnType();

   int getPowerStatus();

   byte getProcotolVersion();

   void getVersion();

   Bundle initMCU();

   void initSystemReady();

   boolean isInitMCU();

   void registerMCUCanboxListener(IMCUCanBoxControlListener var1);

   void registerMCUMainListener(IMCUMainListener var1);

   void registerMcuMsgListener(IMCUMsgCallback var1);

   void requestPowerStatus(int var1);

   void sendMCUCanboxData(int var1, byte[] var2);

   void sendMCUDebugData(byte[] var1);

   void sendTestCmd(byte[] var1);

   void setSendSleepStatus(boolean var1);

   void setUpgradeStatus(boolean var1);

   void startSendHeartBeatMsg();

   void stopSendHeartBeatMsg();

   void unRegisterMCUMainListener(IMCUMainListener var1);

   void unregisterMCUCanboxListener(IMCUCanBoxControlListener var1);

   void unregisterMcuMsgListener(IMCUMsgCallback var1);

   byte[] updateFlashData(byte[] var1);
}
