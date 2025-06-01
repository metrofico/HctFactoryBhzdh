package com.hzbhd.canbus.car._434.speech;

public interface ISysRxListener {
   void AcAir(boolean var1);

   void AcAuto(boolean var1);

   void AcCold(boolean var1);

   void AcDefrost(boolean var1);

   void AcLoop(boolean var1);

   void AcMode(STxData.AcCtrl.Mode.ModeEnum var1);

   void AcTempDec(int var1);

   void AcTempInc(int var1);

   void AcTempTo(int var1);

   void AcWarm(boolean var1);

   void AcWind(boolean var1);

   void AcWindTo(int var1);

   void CarAlarmlight(boolean var1);

   void CarClearancelamps(boolean var1);

   void CarHeadlight(boolean var1);

   void CarHighBeam(boolean var1);

   void CarLock(boolean var1);

   void CarReadinglamp(boolean var1);

   void CarToplight(boolean var1);

   void CarWindowClose();

   void CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum var1);

   void CarWiper(boolean var1);

   void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum var1);

   void CarWiperMove(int var1);

   void SystemSync();
}
