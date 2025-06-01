package com.hzbhd.constant.mcu;

public class McuConstants {
   public static enum SETTING_GET {
      private static final SETTING_GET[] $VALUES = $values();
      getMcuBootVersion,
      getMcuDtcMsg,
      getMcuVersion;

      // $FF: synthetic method
      private static SETTING_GET[] $values() {
         return new SETTING_GET[]{getMcuVersion, getMcuBootVersion, getMcuDtcMsg};
      }
   }

   public static enum SETTING_NOTIFY {
      private static final SETTING_NOTIFY[] $VALUES = $values();
      notifyMcuTestF0,
      notifyMcuTestF1,
      notifyMcuTestF3,
      notifyPnlSample,
      notifyPnlSettings,
      notifyReadMcuEeprom,
      notifySwcSample,
      notifySwcSettings,
      notifyWriteMcuEeprom;

      // $FF: synthetic method
      private static SETTING_NOTIFY[] $values() {
         return new SETTING_NOTIFY[]{notifySwcSettings, notifySwcSample, notifyPnlSettings, notifyPnlSample, notifyWriteMcuEeprom, notifyReadMcuEeprom, notifyMcuTestF1, notifyMcuTestF0, notifyMcuTestF3};
      }
   }

   public static enum SETTING_SET {
      private static final SETTING_SET[] $VALUES = $values();
      TMcuRecoveryMode,
      closeSWC,
      endSWCSample,
      getMcuTestInit,
      getSWCSettings,
      getSysSetting,
      openSWC,
      pnlSample,
      readMcuEeprom,
      reqMcuKey,
      reqPNLAllKeyInfo,
      requestMcuMuteAudioGpio,
      resetSWC,
      savePNLSettings,
      saveSWCSettings,
      screenOrientation,
      sendMcuTestData,
      sendTsPosition,
      setBackLightLearningData,
      setBackLightLearningState,
      setColorLed,
      setDeviceConnectStatus,
      setReverse,
      setSWCLearnLevel,
      setVcom,
      startMcuTest,
      startSWCSample,
      updateAccOnBackLightDelaySettings,
      updateFactorySettingsConfig,
      updateGeneralConfig,
      updateLcdConfig,
      updatePowerConfig,
      updateScreenSaverMode,
      updateVehicleConfig,
      updateVideoConfig,
      writeMcuEeprom;

      // $FF: synthetic method
      private static SETTING_SET[] $values() {
         return new SETTING_SET[]{getSWCSettings, saveSWCSettings, endSWCSample, startSWCSample, openSWC, closeSWC, resetSWC, setSWCLearnLevel, reqPNLAllKeyInfo, pnlSample, savePNLSettings, setColorLed, setVcom, getSysSetting, reqMcuKey, sendTsPosition, updateVideoConfig, updateFactorySettingsConfig, updateGeneralConfig, setBackLightLearningState, setBackLightLearningData, updateVehicleConfig, writeMcuEeprom, readMcuEeprom, updatePowerConfig, TMcuRecoveryMode, startMcuTest, sendMcuTestData, getMcuTestInit, updateLcdConfig, setReverse, updateScreenSaverMode, requestMcuMuteAudioGpio, setDeviceConnectStatus, screenOrientation, updateAccOnBackLightDelaySettings};
      }
   }
}
