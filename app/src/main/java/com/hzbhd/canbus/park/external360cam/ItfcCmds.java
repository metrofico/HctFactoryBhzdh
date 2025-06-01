package com.hzbhd.canbus.park.external360cam;

public interface ItfcCmds {
   void allFrontLeftRight();

   void allRearLeftRight();

   void enter();

   void exit();

   void fourRegion();

   void frontAll();

   void frontOnly();

   void leftAll();

   void leftOnly();

   void rearAll();

   void rearOnly();

   void rightAll();

   void rightOnly();

   void sndCoord(int var1, int var2);
}
