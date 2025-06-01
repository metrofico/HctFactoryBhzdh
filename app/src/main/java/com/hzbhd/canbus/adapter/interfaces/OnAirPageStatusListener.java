package com.hzbhd.canbus.adapter.interfaces;

public interface OnAirPageStatusListener {
   int FRONT_ON_CREATE = 1;
   int REAR_ON_CREATE = 5;

   void onStatusChange(int var1);
}
