package com.hzbhd.canbus.interfaces;

public interface AirControlInterface {
   void action(String var1);

   boolean isComplete();

   void most();

   void reset();

   void step();

   void target(String var1, String var2);
}
