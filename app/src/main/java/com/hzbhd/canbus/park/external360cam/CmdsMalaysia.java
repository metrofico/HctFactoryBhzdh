package com.hzbhd.canbus.park.external360cam;

import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.util.CommUtil;

public class CmdsMalaysia implements ItfcCmds {
   public void allFrontLeftRight() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 19, -5});
   }

   public void allRearLeftRight() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 21, -5});
   }

   public void enter() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 3, -5});
   }

   public void exit() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 2, -5});
   }

   public void fourRegion() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 20, -5});
   }

   public void frontAll() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 5, -5});
   }

   public void frontOnly() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 9, -5});
   }

   public void leftAll() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 7, -5});
   }

   public void leftOnly() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 17, -5});
   }

   public void rearAll() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 6, -5});
   }

   public void rearOnly() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 16, -5});
   }

   public void rightAll() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 8, -5});
   }

   public void rightOnly() {
      FutureUtil.instance.reqMcuKey(new byte[]{-56, 5, 0, -1, 18, -5});
   }

   public void sndCoord(int var1, int var2) {
      CommUtil.playBeep();
      FutureUtil.instance.sendPositionExtra(5, new byte[]{-69, 102}, var1, var2);
   }
}
